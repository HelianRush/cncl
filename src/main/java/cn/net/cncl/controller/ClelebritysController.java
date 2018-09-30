package cn.net.cncl.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;

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
		return "/manager_celebritys";
	}

	/**
	 * 测试
	 */
	// @RequestMapping(value = "/addTest")
	// public void addTest() {
	// Celebritys adminUser = null;
	// for (int i = 0; i < 30; i++) {
	// adminUser = new Celebritys();
	// adminUser.setCelebrityId(new Date().getTime());
	// adminUser.setCelebrityName("测试用户" + String.valueOf(i + 10));
	// adminUser.setAnotherName("用户" + String.valueOf(i + 10));
	// adminUser.setForeignName("XioaMing" + String.valueOf(i + 10));
	// adminUser.setBirthday(new Date());
	// adminUser.setDeathday(new Date());
	// System.out.println(adminUser);
	// celebritysService.insertCelebritys(adminUser);
	// }
	// }

}
