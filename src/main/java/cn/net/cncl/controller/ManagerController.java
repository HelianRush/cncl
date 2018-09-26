package cn.net.cncl.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.net.cncl.entity.WebInfo;
import cn.net.cncl.service.CelebritysService;
import cn.net.cncl.service.LoginService;
import cn.net.cncl.service.ManagerService;

/**
 * 后台管理 Controoler
 */
@Controller
@RequestMapping("/managerController")
public class ManagerController {

	private static Logger logger = LoggerFactory.getLogger(ManagerController.class);

	@Autowired
	private LoginService loginService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private CelebritysService celebritysService;

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
	// @GetMapping(value = "/getWebInfoObject")
	// @PostMapping(value = "/getWebInfoObject")
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

}
