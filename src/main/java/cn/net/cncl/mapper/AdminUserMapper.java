package cn.net.cncl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.net.cncl.entity.AdminUser;

@Mapper
public interface AdminUserMapper {
	int deleteByPrimaryKey(Long adminUserId);

	int insert(AdminUser adminUser);

	int insertSelective(AdminUser adminUser);

	AdminUser selectByPrimaryKey(Long adminUserId);

	int updateByPrimaryKeySelective(AdminUser adminUser);

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

	/**
	 * 管理员信息<br>
	 * 查询
	 * 
	 * @param AdminUser.AdminUserName
	 */
	List<AdminUser> selectAdminUserByName(@Param("adminUserName") String adminUserName);

	/**
	 * 新增管理员 登录名验证
	 * 
	 * @param @RequestParam
	 *            String adminUserName
	 */
	int checkAdminUserName(String adminUserName);

}