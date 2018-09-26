package cn.net.cncl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShowController {

	private static Logger logger = LoggerFactory.getLogger(ShowController.class);

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
	 * @Title showManagerWeb
	 * @author Jianfei Yu
	 * @version 1.0.0
	 * @parameter null
	 * @throws null
	 *             访问manager_web.html<br>
	 *             网站信息
	 */
	@RequestMapping(value = "/showManagerWeb")
	public String showManagerWeb() {
		return "manager_web";
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
	public String showManagerMenus() {
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
	public String showManagerAdminUsers() {
		return "manager_admin_users";
	}

}
