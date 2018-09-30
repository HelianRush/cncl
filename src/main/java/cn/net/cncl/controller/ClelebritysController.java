package cn.net.cncl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

}
