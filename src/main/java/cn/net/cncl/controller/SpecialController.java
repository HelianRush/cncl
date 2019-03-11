package cn.net.cncl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.net.cncl.common.Constant;
import cn.net.cncl.entity.Images;
import cn.net.cncl.entity.Special;
import cn.net.cncl.entity.SpecialIndex;
import cn.net.cncl.entity.SpecialType;
import cn.net.cncl.mapper.ImagesMapper;
import cn.net.cncl.service.ImagesService;
import cn.net.cncl.service.SpecialIndexService;
import cn.net.cncl.service.SpecialService;
import cn.net.cncl.service.SpecialTypeService;

@Controller
@RequestMapping("/SpecialCtl")
public class SpecialController {

	private static Logger logger = LoggerFactory.getLogger(SpecialController.class);

	/**
	 * 专题 T_SPECIAL t_special
	 */
	@Autowired
	private SpecialService specialService;

	/**
	 * 专题类别 T_SPECIAL_TYPE t_special_type
	 */
	@Autowired
	private SpecialTypeService specialTypeService;

	/**
	 * 专题主页 T_SPECIAL_INDEX t_special_index
	 */
	@Autowired
	private SpecialIndexService specialIndexService;

	/**
	 * 图片
	 */
	@Autowired
	private ImagesService imagesService;

	@Autowired
	private ImagesMapper imagesMapper;

	// TODO
	/**
	 * 菜单页面跳转
	 */
	// 专题主页编辑
	@RequestMapping(value = "/showSpecialIndexEdit")
	public String showSpecialIndexEdit(HttpServletRequest request) {
		return "manager_specialIndexEdit";
	}

	// 专题类别
	// 专题管理 - 专题类别 查询 列表 分页
	@RequestMapping(value = "/showSpecialType")
	public String showSpecialType(HttpServletRequest request, Model model, @RequestParam(defaultValue = "1") Integer pageNum) {
		PageInfo<SpecialType> pageList = specialTypeService.selectSpecialType(pageNum);
		model = this.pageModel(model, pageList);
		// 当前列表
		model.addAttribute("list", pageList.getList());
		return "manager_specialType";
	}

	// 专题列表
	@RequestMapping(value = "/showSpecial")
	public String showSpecial(HttpServletRequest request, Model model, @RequestParam(defaultValue = "1") Integer pageNum) {
		PageInfo<Special> pageList = specialService.selectNews(pageNum);
		this.pageModel(model, pageList);
		// 当前列表
		model.addAttribute("list", pageList.getList());
		return "manager_special";
	}

	// manager_specialEdit.html
	// 访问专题编辑页面
	@RequestMapping(value = "/showSpecialEdit")
	public String showSpecialEdit(HttpServletRequest request, Special special, Model model, HttpSession session) {
		session.removeAttribute("imageArray");
		session.setAttribute("imageArray", "");
		List<SpecialType> specialTypes = specialTypeService.querySpecialTypeAll();
		model.addAttribute("specialTypes", specialTypes);
		return "manager_specialEdit";
	}

	// TODO
	/**
	 * 专题管理 - 专题主页
	 */
	// 获取专题主页数据
	@ResponseBody
	@RequestMapping(value = "/getSpecialIndexObj")
	public SpecialIndex getSpecialIndexObj(HttpServletRequest request) {
		return specialIndexService.getSpecialIndexObj();
	}

	// 获取专题主页编辑
	@RequestMapping(value = "/specialIndexEdit")
	public String specialIndexEdit(HttpServletRequest request, SpecialIndex specialIndex) {
		int flag = 0;
		if (null == specialIndexService.getSpecialIndexObj() || specialIndexService.getSpecialIndexObj().equals(null)) {
			flag = specialIndexService.insetSpecialIndex(specialIndex);
		} else {
			flag = specialIndexService.updateSpecialIndex(specialIndex);
		}
		if (flag > 0) {
			Images image = imagesService.queryImageById(specialIndex.getImageIdFk());
			image.setResourceByType("special");
			image.setImageTitle("专题主页标题图片");
			image.setImageContent("需要自定义");
			image.setDescription("专题主页标题图片");
			int i = imagesService.updateImage(image);
		}
		return "manager_specialIndexEdit";
	}

	// TODO
	/**
	 * 专题管理 - 专题类别
	 */
	// 专题管理 - 专题类别 编辑 新增、修改
	@RequestMapping(value = "/editSpeciaType")
	public String editSpeciaType(Model model, SpecialType specialType) {
		// Long specialTypeId = specialType.getSpecialTypeId();
		specialTypeService.editSpecialType(specialType);
		Integer pageNum = 1;
		PageInfo<SpecialType> pageList = specialTypeService.selectSpecialType(pageNum);
		model = this.pageModel(model, pageList);
		// 当前列表
		model.addAttribute("list", pageList.getList());
		return "manager_specialType";
	}

	// 专题管理 - 专题类别 编辑 回显
	@ResponseBody
	@PostMapping(value = "/showEditSpeciaType")
	public SpecialType showEditSpeciaType(@RequestParam Long id) {
		SpecialType specialType = specialTypeService.queryById(id);
		return specialType;
	}

	// 专题管理 - 专题类别 删除
	@ResponseBody
	@PostMapping(value = "/removeSpeciaType")
	public String removeSpeciaType(Model model, @RequestParam Long id) {
		int flag = specialTypeService.deleteSpeciaType(id);
		if (0 < flag) {
			return Constant.SUCCESS.getCode();
		} else {
			return Constant.ERROR.getCode();
		}
	}

	// TODO
	/**
	 * 专题管理 - 专题
	 */
	// 专题查询
	@RequestMapping(value = "/querySpecial")
	public String querySpecial(HttpServletRequest request, Model model, @RequestParam(defaultValue = "1") Integer pageNum) {
		PageInfo<Special> pageList = specialService.selectNews(pageNum);
		this.pageModel(model, pageList);
		// 当前列表
		model.addAttribute("list", pageList.getList());
		return "manager_special";
	}

	// 专题管理 - 根据ID查询
	@ResponseBody
	@RequestMapping(value = "/getSpecialById")
	public Special getSpecialById(HttpSession session) {
		Long specialId = (Long) session.getAttribute("specialId");
		Special special = null;
		if (null != specialId) {
			special = specialService.querySpecialById(specialId);
		}
		session.removeAttribute("specialId");
		return special;
	}

	// 专题管理 - 点击修改弹出编辑页面
	@RequestMapping(value = "/showEditToSpecialEdit")
	public String showEditToSpecialEdit(HttpSession session, Model model, @RequestParam Long specialId) {
		Special special = specialService.querySpecialById(specialId);
		List<SpecialType> specialTypes = specialTypeService.querySpecialTypeAll();
		model.addAttribute("specialTypes", specialTypes);
		model.addAttribute("special", special);
		session.setAttribute("specialId", specialId);
		session.removeAttribute("imageArray");
		session.setAttribute("imageArray", "");
		return "manager_specialEdit";
	}

	// 专题编辑 - 新增、修改
	@RequestMapping(value = "/editSpecial")
	public String editSpecial(HttpServletRequest request, HttpSession session, Special special, Model model) {

		Long specialId = special.getSpecialId();

		int flag = 0;

		if (null == specialId) {
			// 新增
			flag = specialService.addSpecial(special);
		} else {
			// 修改
			flag = specialService.editSpecial(special);
		}
		if (0 < flag) {
			PageInfo<Special> pageList = specialService.selectNews(1);
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
					image.setResourceBy(special.getSpecialId());
					image.setResourceByType("special");
					image.setImageTitle("《" + special.getSpecialTitle() + "》");
					image.setImageContent("需要自定义");
					image.setDescription("《" + special.getSpecialTitle() + "》文章的标题图片。");

					imagesMapper.editImage(image);

					if (i == (imgArray.length - 1)) {
						special.setImageIdFk(Long.valueOf(imgId));
						flag = specialService.editSpecial(special);
					}
				}

				// 删除session
				session.removeAttribute("imageArray");
			}

			return "manager_special";
		} else {
			return "manager_specialEdit";
		}
	}

	// 专题编辑 - 删除
	@ResponseBody
	@PostMapping(value = "/deleteSpecial")
	public String deleteSpecial(@RequestParam long id) {
		int flag = specialService.deleteSpecial(id);
		if (0 < flag) {
			// 删除所属图片
			int flag_img = imagesMapper.delByResourceBy(id);
			return Constant.SUCCESS.getCode();
		} else {
			return Constant.ERROR.getCode();
		}
	}

	// ---------- main ----------
	public static void main(String[] args) {
	}
	// ---------- main ----------

	// 保证分页Model
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
