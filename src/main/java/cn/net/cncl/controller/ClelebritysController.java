package cn.net.cncl.controller;

import javax.servlet.http.HttpServletRequest;

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
import cn.net.cncl.service.AdminUserService;
import cn.net.cncl.service.CelebritysService;

@Controller
@RequestMapping("/ClelebritysController")
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

	/**
	 * 名人库信息 新增&编辑
	 */
	@RequestMapping(value = "/editCelebritys", method = RequestMethod.GET)
	public String editCelebritys(Celebritys celebrity) {
		int flag = 0;
		if (null == celebrity.getCelebrityId()) {
			flag = celebritysService.insertCelebritys(celebrity);
		} else {
			flag = celebritysService.updateCelebritys(celebrity);
		}
		if (0 < flag)
			return "manager_celebritys";
		else
			return "manager_celebritys_edit";
	}

	/**
	 * 名人库信息 查询
	 */
	@RequestMapping(value = "/showCelebritysList")
	public String showCelebritysList(HttpServletRequest request, Model model, @RequestParam(defaultValue = "1") Integer pageNum) {
		PageInfo<Celebritys> pageList = celebritysService.showCelebritysList(pageNum);
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
		// 当前列表
		model.addAttribute("list", pageList.getList());
		return "manager_celebritys";
	}

	/**
	 * 修改
	 */

	/**
	 * 删除
	 */
	/**
	 * 删除
	 */
	@ResponseBody
	@PostMapping(value = "/deleteCelebritys")
	public String deleteCelebritys(@RequestParam Long id) {
		int flag = celebritysService.deleteCelebritys(id);
		if (0 < flag) {
			return Constant.SUCCESS.getCode();
		} else {
			return Constant.DEFEAT.getCode();
		}
	}
}
