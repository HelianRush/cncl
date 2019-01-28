package cn.net.cncl.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.net.cncl.common.Constant;
import cn.net.cncl.entity.Celebritys;
import cn.net.cncl.entity.Images;
import cn.net.cncl.mapper.ImagesMapper;
import cn.net.cncl.service.AdminUserService;
import cn.net.cncl.service.CelebritysService;

@Controller
@RequestMapping("/ClelebritysCtl")
public class ClelebritysController {

	private static Logger logger = LoggerFactory.getLogger(ClelebritysController.class);

	/**
	 * 管理员
	 */
	@Autowired
	private AdminUserService adminUserService;

	/**
	 * 名人库
	 */
	@Autowired
	private CelebritysService celebritysService;

	@Autowired
	private ImagesMapper imagesMapper;

	/**
	 * 名人库信息 新增&编辑
	 */
	@RequestMapping(value = "/editCelebritys", method = RequestMethod.GET)
	public String editCelebritys(HttpServletRequest request, HttpSession session, Celebritys celebrity, Model model) {

		int flag = 0;

		if (null == celebrity.getCelebrityId()) {
			// 新增
			flag = celebritysService.insertCelebritys(celebrity);
		} else {
			// 修改
			flag = celebritysService.updateCelebritys(celebrity);
		}
		if (0 < flag) {
			PageInfo<Celebritys> pageList = celebritysService.showCelebritysList(1);
			this.pageModel(model, pageList);
			// 当前列表
			model.addAttribute("list", pageList.getList());

			// 保存成功，编辑图片信息
			String images = (String) session.getAttribute("imageArray");
			String[] imgArray = images.split(",");

			if ("" != images && !images.equals("") && null != images && !images.equals(null)) {
				for (int i = 0; i < imgArray.length; i++) {
					String imgId = imgArray[i];
					Images image = imagesMapper.queryImageById(Long.valueOf(imgId));
					image.setResourceBy(celebrity.getCelebrityId());
					image.setResourceByType("celebrity");
					image.setImageTitle("《" + celebrity.getCelebrityName() + "》");
					image.setImageContent("需要自定义");
					image.setDescription("《" + celebrity.getCelebrityName() + "》文章的标题图片。");

					imagesMapper.editImage(image);

					if (i == (imgArray.length - 1)) {
						celebrity.setImageIdFk(Long.valueOf(imgId));
						flag = celebritysService.updateCelebritys(celebrity);
					}
				}

				// 删除session
				session.removeAttribute("imageArray");
			}

			return "manager_celebritys";
		} else {
			return "manager_celebritys_edit";
		}
	}

	/**
	 * 名人库信息 查询
	 */
	@RequestMapping(value = "/showCelebritysList")
	public String showCelebritysList(HttpServletRequest request, Model model, @RequestParam(defaultValue = "1") Integer pageNum) {
		PageInfo<Celebritys> pageList = celebritysService.showCelebritysList(pageNum);
		this.pageModel(model, pageList);
		// 当前列表
		model.addAttribute("list", pageList.getList());
		return "manager_celebritys";
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "/showManagerCelebritysEdit")
	public String showManagerCelebritysEdit(HttpSession session, @RequestParam Long celebrityId) {
		session.setAttribute("celebrityId", celebrityId);
		session.removeAttribute("imageArray");
		session.setAttribute("imageArray", "");
		return "manager_celebritys_edit";
	}

	@ResponseBody
	@RequestMapping(value = "/getCelebrityById")
	public Celebritys getCelebrityById(HttpSession session) {
		Long celebrityId = (Long) session.getAttribute("celebrityId");
		Celebritys celebrity = null;
		if (null != celebrityId) {
			celebrity = celebritysService.queryCelebritysByID(celebrityId);
		}
		session.removeAttribute("celebrityId");
		return celebrity;
	}

	/**
	 * 删除
	 */
	@ResponseBody
	@PostMapping(value = "/deleteCelebritys")
	public String deleteCelebritys(@RequestParam Long id) {
		int flag = celebritysService.deleteCelebritys(id);
		if (0 < flag) {
			// 删除所属图片
			int flag_img = imagesMapper.delByResourceBy(id);

			return Constant.SUCCESS.getCode();
		} else {
			return Constant.ERROR.getCode();
		}
	}

	/**
	 * 保证分页Model
	 */
	private Model pageModel(Model model, PageInfo pageList) {
		// 获得当前页
		model.addAttribute("pageNum", pageList.getPageNum());
		// 获得一页显示的条数
		model.addAttribute("pageSize", pageList.getPageSize());
		// 是否是第一页
		model.addAttribute("isFirstPage", pageList.isIsFirstPage());
		// 获得总页数
		model.addAttribute("totalPages", pageList.getPages());
		// 是否是最后一页
		model.addAttribute("isLastPage", pageList.isIsLastPage());
		return model;
	}

}
