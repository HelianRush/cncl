package cn.net.cncl.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;

import cn.net.cncl.entity.AdminUser;

public interface AdminUserService {

	/**
	 * 管理员信息<br>
	 * 查询
	 * 
	 * @param AdminUser.AdminUserName
	 */
	List<AdminUser> selectAdminUserByName(AdminUser adminUser);

	/**
	 * 管理员信息<br>
	 * 查询 分页
	 * 
	 * @param AdminUser.AdminUserName
	 */
	List<AdminUser> selectAdminUserByName(String adminUserName);

	PageInfo<AdminUser> selectAdminUserByName(String adminUserName, int pageNum);

	/**
	 * 添加 管理员信息
	 */
	int addAdminUser(HttpServletRequest request, AdminUser adminUser);

}
