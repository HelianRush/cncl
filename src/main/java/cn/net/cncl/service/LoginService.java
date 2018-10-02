package cn.net.cncl.service;

import cn.net.cncl.entity.AdminUser;

public interface LoginService {
	/**
	 * 登录查询
	 */
	public AdminUser verificationLogin(String adminUserName, String password);

}
