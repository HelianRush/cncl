package cn.net.cncl.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.net.cncl.common.Captcha;
import cn.net.cncl.common.Constant;
import cn.net.cncl.common.SessionUser;
import cn.net.cncl.entity.AdminUser;
import cn.net.cncl.service.AdminUserService;
import cn.net.cncl.service.CelebritysService;
import cn.net.cncl.service.LoginService;
import cn.net.cncl.service.NewsService;
import cn.net.cncl.service.NewsTypeService;

/**
 * 登录
 */
@Controller
@RequestMapping(value = "/Login")
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	private List<Integer> captchaList = null;

	@Autowired
	public LoginService loginService;

	@Autowired
	private AdminUserService adminUserService;

	@Autowired
	private CelebritysService celebritysService;

	@Autowired
	private NewsService newsService;

	@Autowired
	private NewsTypeService newsTypeService;

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
	 * 获取验证码
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/getCaptcha", method = RequestMethod.GET)
	public ServletOutputStream getCaptcha(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		captchaList = Captcha.getCaptchaNumber();
		BufferedImage captchaImage = Captcha.getCaptchaImage(captchaList);
		ServletOutputStream osOutputStream = resp.getOutputStream();
		ImageIO.write(captchaImage, "jpg", osOutputStream);
		return osOutputStream;
	}

	/**
	 * 获取验证码
	 */
	@ResponseBody
	@RequestMapping(value = "/getCtlCaptcha", method = RequestMethod.POST)
	public String getCtlCaptcha(HttpServletRequest request) {
		String validCode = request.getParameter("validCode");
		if (StringUtils.isBlank(validCode)) {
			return Constant.CAPTCHA_IS_NULL.getMessage();
		}
		// 校验验证码
		StringBuffer sbCapth = new StringBuffer();
		for (Integer getList : captchaList) {
			sbCapth.append(getList.toString());
		}
		return sbCapth.toString();
	}

	/**
	 * 登录
	 */
	@PostMapping("/adminLogion")
	public String login(HttpServletRequest request, AdminUser adminUser) {

		String adminUserName = adminUser.getAdminUserName();
		String password = adminUser.getAdminUserName();

		// 校验用户名和密码
		adminUser = loginService.verificationLogin(adminUserName, password);

		String flag = "";
		if (null != adminUser.getAdminUserId())
			flag = Constant.SUCCESS.getCode();

		if (flag.equals(Constant.SUCCESS.getCode())) {
			// 添加Session
			SessionUser.setAdminUser(request, adminUser);
			return "manager_index";
		} else
			return "login";
	}

	/**
	 * 退出
	 * 
	 * 清空Session
	 */
	@ResponseBody
	@PostMapping("/clearSession")
	public String clearSession(HttpServletRequest request, Model model) {
		SessionUser.clear(request);
		return Constant.SUCCESS.getCode();
	}

}
