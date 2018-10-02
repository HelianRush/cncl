package cn.net.cncl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.net.cncl.common.Constant;
import cn.net.cncl.common.SessionUser;
import cn.net.cncl.entity.News;
import cn.net.cncl.entity.NewsType;
import cn.net.cncl.service.AdminUserService;
import cn.net.cncl.service.CelebritysService;
import cn.net.cncl.service.NewsService;
import cn.net.cncl.service.NewsTypeService;

@Controller
public class ShowManagerController {

	private static Logger logger = LoggerFactory.getLogger(ShowManagerController.class);

	@Autowired
	private AdminUserService adminUserService;

	@Autowired
	private CelebritysService celebritysService;

	@Autowired
	private NewsService newsService;

	@Autowired
	private NewsTypeService newsTypeService;

	/**
	 * @Title showIndex
	 * @author Jianfei Yu
	 * @version 1.0.0
	 * @parameter null
	 * @throws null
	 *             访问Index.html<br>
	 *             首页
	 */
	@RequestMapping(value = "/index")
	public String showIndex() {
		return "index";
	}

	/**
	 * @Title showLogin
	 * @author Jianfei Yu
	 * @version 1.0.0
	 * @parameter null
	 * @throws null
	 *             访问login.html<br>
	 *             登录
	 */
	@RequestMapping(value = "/login")
	public String showLogin() {
		return "login";
	}

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
		String flag = this.getLogin(request);
		if (flag.equals(Constant.SUCCESS.getCode()))
			return "manager_web";
		else
			return flag;
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
		String flag = this.getLogin(request);
		if (flag.equals(Constant.SUCCESS.getCode()))
			return "manager_web_edit";
		else
			return flag;
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
		String flag = this.getLogin(request);
		if (flag.equals(Constant.SUCCESS.getCode()))
			return "manager_admin_users";
		else
			return flag;
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
		String flag = this.getLogin(request);
		if (flag.equals(Constant.SUCCESS.getCode()))
			return "manager_celebritys_edit";
		else
			return flag;
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
		String flag = this.getLogin(request);
		if (flag.equals(Constant.SUCCESS.getCode()))
			return "manager_celebritys";
		else
			return flag;
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
		String flag = this.getLogin(request);
		if (flag.equals(Constant.SUCCESS.getCode())) {
			List<NewsType> newsTypeList = newsTypeService.queryNewsTypeAll();
			model.addAttribute("newsTypeList", newsTypeList);
			return "manager_news_edit";
		} else
			return flag;
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
		String flag = this.getLogin(request);
		if (flag.equals(Constant.SUCCESS.getCode()))
			return "manager_news";
		else
			return flag;
	}

	/**
	 * 判断是否登录
	 */
	private String getLogin(HttpServletRequest request) {
		boolean flag = SessionUser.getUserStatus(request);
		if (flag) {
			return Constant.SUCCESS.getCode();
		} else {
			return "300";
		}
	}
}
