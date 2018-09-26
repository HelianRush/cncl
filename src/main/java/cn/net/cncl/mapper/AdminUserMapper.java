package cn.net.cncl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.net.cncl.entity.AdminUser;

@Mapper
public interface AdminUserMapper {
	int deleteByPrimaryKey(Long adminUserId);

	int insert(AdminUser record);

	int insertSelective(AdminUser record);

	AdminUser selectByPrimaryKey(Long adminUserId);

	int updateByPrimaryKeySelective(AdminUser record);

	int updateByPrimaryKey(AdminUser record);

	/**
	 * 根据用户名查询是否存在
	 */
	int selectByNameHave(String adminUserName);

	/**
	 * 验证用户登录
	 */

	// @Param("adminUserName")
	// @Param("password")
	AdminUser checkAdminUserLogin(@Param("adminUserName") String adminUserName, @Param("password") String password);
}