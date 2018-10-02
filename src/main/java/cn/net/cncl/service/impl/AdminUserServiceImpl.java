package cn.net.cncl.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.net.cncl.common.Constant;
import cn.net.cncl.common.GetIP;
import cn.net.cncl.entity.AdminUser;
import cn.net.cncl.mapper.AdminUserMapper;
import cn.net.cncl.service.AdminUserService;

@Service
@Transactional
public class AdminUserServiceImpl implements AdminUserService {

	private static Logger logger = LoggerFactory.getLogger(AdminUserServiceImpl.class);

	@Autowired
	private AdminUserMapper adminUserMapper;

	/**
	 * 管理员信息<br>
	 * 查询
	 * 
	 * @param AdminUser.AdminUserName
	 */
	@Override
	public List<AdminUser> selectAdminUserByName(AdminUser adminUser) {
		String adminUserName = adminUser.getAdminUserName();
		List<AdminUser> list = adminUserMapper.selectAdminUserByName(adminUserName);
		return list;
	}

	/**
	 * 管理员信息<br>
	 * 查询
	 * 
	 * @param AdminUser.AdminUserName
	 */
	@Override
	public List<AdminUser> selectAdminUserByName(String adminUserName) {
		List<AdminUser> list = adminUserMapper.selectAdminUserByName(adminUserName);
		return list;
	}

	/**
	 * 管理员信息<br>
	 * 查询 分页
	 * 
	 * @param AdminUser.AdminUserName
	 * @param pageNum
	 *            当前页
	 * @param pageSize
	 *            每页显示条数
	 */
	@Override
	public PageInfo<AdminUser> selectAdminUserByName(String adminUserName, int pageNum) {
		// 分页
		// Page<Object> page =
		PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
		List<AdminUser> list = adminUserMapper.selectAdminUserByName(adminUserName);
		PageInfo<AdminUser> pageInfo = new PageInfo<AdminUser>(list);
		return pageInfo;
	}

	/**
	 * 添加 管理员信息
	 */
	@Override
	public int addAdminUser(HttpServletRequest request, AdminUser adminUser) {

		if (null == adminUser.getAdminUserName() || null == adminUser.getPassword()) {
			return 1;
		}

		adminUser.setAdminUserId(new Date().getTime());
		adminUser.setCreateTime(new Date());
		adminUser.setLastLoginTime(new Date());
		adminUser.setLastLoginIp(GetIP.getIpAddr(request));
		return adminUserMapper.insert(adminUser);
	}

	/**
	 * 新增管理员 登录名验证
	 * 
	 * @param @RequestParam
	 *            String adminUserName
	 */
	@Override
	public String checkAdminUserName(String adminUserName) {
		int flag = adminUserMapper.checkAdminUserName(adminUserName);

		if (0 < flag) {
			return Constant.ADMIN_USER_NAME_IS_HAVE.getCode();
		} else {
			return Constant.SUCCESS.getCode();
		}
	}

	/**
	 * 删除
	 */
	@Override
	public int deleteAdminUser(Long id) {
		return adminUserMapper.deleteByPrimaryKey(id);
	}

}
