package cn.net.cncl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.net.cncl.entity.News;
import cn.net.cncl.entity.NewsType;
import cn.net.cncl.entity.WebInfo;
import cn.net.cncl.service.AdminUserService;
import cn.net.cncl.service.CelebritysService;
import cn.net.cncl.service.LoginService;
import cn.net.cncl.service.ManagerService;
import cn.net.cncl.service.NewsService;
import cn.net.cncl.service.NewsTypeService;

/**
 * 后台管理 Controoler
 */
@Controller
@RequestMapping("/ManagerCtl")
public class ManagerController {

	private static Logger logger = LoggerFactory.getLogger(ManagerController.class);

	@Autowired
	private LoginService loginService;

	@Autowired
	private AdminUserService adminUserService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private CelebritysService celebritysService;

	@Autowired
	private NewsService newsService;

	@Autowired
	private NewsTypeService newsTypeService;

	/**
	 * Error Page
	 */
	@RequestMapping(value = "/404")
	public String show404() {
		return "404";
	}

	/**
	 * Error Page
	 */
	@RequestMapping(value = "/500")
	public String show500() {
		return "500";
	}

	/**
	 * Error Page
	 */
	@RequestMapping(value = "/300")
	public String show300() {
		return "300";
	}

	/**
	 * 查询 网站信息
	 */
	@ResponseBody
	@GetMapping(value = "/getWebInfo")
	public String getWebInfo(HttpServletRequest request) {
		return managerService.getWebInfo();
	}

	/**
	 * 查询 网站信息
	 */
	@ResponseBody
	@RequestMapping(value = "/getWebInfoObject")
	public WebInfo getWebInfoObject(HttpServletRequest request) {
		return managerService.getWebInfoObject();
	}

	/**
	 * 编辑网站信息
	 */
	@RequestMapping(value = "/updateWebInfo")
	public String updateWebInfo(WebInfo webInfo) {
		int flag = managerService.updateWebInfo(webInfo);
		if (0 < flag) {
			return "manager_web";
		} else {
			return "manager_web_edit";
		}
	}

	/**
	 * @Title showManagerWeb
	 * @author Jianfei Yu
	 * @version 1.0.0
	 * @parameter null
	 * @throws null
	 *             访问manager_web.html<br>
	 *             网站信息
	 */
	@RequestMapping(value = "/showManagerWeb")
	public String showManagerWeb(HttpServletRequest request) {
		return "manager_web";
	}

	/**
	 * @Title showManagerWebEdit
	 * @author Jianfei Yu
	 * @version 1.0.0
	 * @parameter null
	 * @throws null
	 *             访问manager_web_edit.html<br>
	 *             网站信息编辑
	 */
	@RequestMapping(value = "/showManagerWebEdit")
	public String showManagerWebEdit(HttpServletRequest request) {
		return "manager_web_edit";
	}

	/**
	 * @Title showManagerMenus
	 * @author Jianfei Yu
	 * @version 1.0.0
	 * @parameter null
	 * @throws null
	 *             访问manager_menus.html<br>
	 *             导航
	 */
	@RequestMapping(value = "/showManagerMenus")
	public String showManagerMenus(HttpServletRequest request) {
		return "manager_menus";
	}

	/**
	 * @Title showManagerAdminUsers
	 * @author Jianfei Yu
	 * @version 1.0.0
	 * @parameter null
	 * @throws null
	 *             访问manager_admin_users.html<br>
	 *             管理员信息
	 */
	@RequestMapping(value = "/showManagerAdminUsers")
	public String showManagerAdminUsers(HttpServletRequest request) {
		return "manager_admin_users";
	}

	/**
	 * @Title showManagerCelebritysEdit
	 * @author Jianfei Yu
	 * @version 1.0.0
	 * @parameter null
	 * @throws null
	 *             访问manager_celebritys_edit.html<br>
	 *             名人库编辑
	 */
	@RequestMapping(value = "/showManagerCelebritysEdit")
	public String showManagerCelebritysEdit(HttpServletRequest request) {
		return "manager_celebritys_edit";
	}

	/**
	 * @Title showManagerCelebritys
	 * @author Jianfei Yu
	 * @version 1.0.0
	 * @parameter null
	 * @throws null
	 *             访问manager_celebritys.html<br>
	 *             名人库展示
	 */
	@RequestMapping(value = "/showManagerCelebritys")
	public String showManagerCelebritys(HttpServletRequest request) {
		return "manager_celebritys";
	}

	/**
	 * @Title showNewsEdit
	 * @author Jianfei Yu
	 * @version 1.0.0
	 * @parameter null
	 * @throws null
	 *             访问manager_news_edit.html<br>
	 *             资讯新增&编辑
	 */
	@RequestMapping(value = "/showNewsEdit")
	public String showNewsEdit(HttpServletRequest request, News news, Model model) {
		List<NewsType> newsTypeList = newsTypeService.queryNewsTypeAll();
		model.addAttribute("newsTypeList", newsTypeList);
		return "manager_news_edit";
	}

	/**
	 * @Title showNews
	 * @author Jianfei Yu
	 * @version 1.0.0
	 * @parameter null
	 * @throws null
	 *             访问showNews.html<br>
	 *             资讯
	 */
	@RequestMapping(value = "/showNews")
	public String showNews(HttpServletRequest request) {
		return "manager_news";
	}

	/**
	 * 编辑专题
	 */
	@RequestMapping(value = "/showManagerSpecialTopicEdit")
	public String showManagerSpecialTopicEdit() {
		return "201";
	}

	/**
	 * 编辑入驻
	 */
	@RequestMapping(value = "/showManagerEnterEdit")
	public String showManagerEnterEdit() {
		return "201";
	}

	/**
	 * 图片管理
	 */
	@RequestMapping(value = "/showManagerImages")
	public String showManagerImages() {
		return "201";
	}

	/**
	 * 视频管理
	 */
	@RequestMapping(value = "/showManagerVides")
	public String showManagerVides() {
		return "201";
	}

}
