package cn.net.cncl.common;

import javax.servlet.http.HttpServletRequest;

import cn.net.cncl.entity.AdminUser;

public class SessionUser {

	// private static Logger logger = LoggerFactory.getLogger(SessionUser.class);

	/**
	 * 获取用户登录状态
	 */
	public static boolean getUserStatus(HttpServletRequest request) {
		Long adminUserId = (Long) request.getSession().getAttribute(Constant.ADMIN_USER_ID_KEY);
		System.out.println("[登录用户] cn.net.cncl.common.SessionUser.getUserStatus() = " + adminUserId);
		if (null != adminUserId)
			return true;
		else
			return false;
	}

	/**
	 * 获取登录用户
	 */
	public static AdminUser getUser(HttpServletRequest request) {
		return (AdminUser) request.getSession().getAttribute(Constant.ADMIN_USER_KEY);
	}

	/**
	 * Session中添加用户
	 */
	public static void setAdminUser(HttpServletRequest request, AdminUser adminUser) {
		request.getSession().setAttribute(Constant.ADMIN_USER_ID_KEY, adminUser.getAdminUserId());
		request.getSession().setAttribute("user", adminUser.getAdminUserName());
		request.getSession().setAttribute("name", adminUser.getName());
		request.getSession().setAttribute("nickName", adminUser.getNickName());
		request.getSession().setAttribute(Constant.ADMIN_USER_KEY, adminUser);
		System.out.println("[创建Session] cn.net.cncl.common.SessionUser.setAdminUser() = " + adminUser);
	}

	/**
	 * 清除Session
	 */
	public static void clear(HttpServletRequest request) {
		request.getSession().invalidate();
		System.out.println("[清空Session] cn.net.cncl.common.SessionUser.clear() = " + "清空");
	}

	public static void main(String[] args) {
	}

}
