package cn.net.cncl.service;

import cn.net.cncl.entity.AdminUser;

public interface LoginService {
	/**
	 * 登录查询
	 */
	public AdminUser verificationLogin(String adminUserName, String password);

	/**
	 * 验证 用户名
	 */
	public String checkAdminUserName(String adminUserName);

	/**
	 * 验证 密码
	 */
	public String checkPassword(String adminUserName, String password);

}
