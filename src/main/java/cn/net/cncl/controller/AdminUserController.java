package cn.net.cncl.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.net.cncl.common.Constant;
import cn.net.cncl.entity.AdminUser;
import cn.net.cncl.service.AdminUserService;

@Controller
@RequestMapping("/AdminCtl")
public class AdminUserController {

	private static Logger logger = LoggerFactory.getLogger(AdminUserController.class);

	@Autowired
	private AdminUserService adminUserService;

	/**
	 * 管理员信息<br>
	 * 查询
	 * 
	 * @param AdminUser.AdminUserName
	 *            HttpServletRequest request,
	 */
	@RequestMapping(value = "/selectAdminUser")
	public String selectAdminUser(HttpServletRequest request, Model model, @RequestParam(defaultValue = "1") Integer pageNum) {

		String adminUserName = request.getParameter("adminUserName");

		PageInfo<AdminUser> pageList = adminUserService.selectAdminUserByName(adminUserName, pageNum);

		// 保证分页Model
		this.pageModel(model, pageList);
		// 当前列表
		model.addAttribute("list", pageList.getList());
		return "manager_admin_users";
	}

	// TODO
	/**
	 * 新增管理员 登录名验证
	 * 
	 * @param @RequestParam
	 *            String adminUserName
	 */
	@ResponseBody
	@RequestMapping(value = "/checkAdminUserName")
	public String checkAdminUserName(@RequestParam String adminUserName) {

		if (StringUtils.isBlank(adminUserName)) {
			return "";
		}
		String flag = adminUserService.checkAdminUserName(adminUserName);

		return flag;
	}

	// TODO
	/**
	 * 管理员信息 新增
	 */
	@RequestMapping(value = "/addAdminUser")
	public String addAdminUser(HttpServletRequest request, AdminUser adminUser, Model model, @RequestParam(defaultValue = "1") Integer pageNum) {

		int flag = 0;
		if (null == adminUser.getAdminUserId()) {
			flag = adminUserService.addAdminUser(request, adminUser);
		} else {
			flag = adminUserService.editAdminUser(request, adminUser);
		}

		PageInfo<AdminUser> pageList = adminUserService.selectAdminUserByName(null, pageNum);

		// 保证分页Model
		this.pageModel(model, pageList);
		// 当前列表
		model.addAttribute("list", pageList.getList());

		if (0 < flag) {
			return "manager_admin_users";
		} else
			return "manager_admin_users";
	}

	// 编辑显示
	@ResponseBody
	@PostMapping(value = "/showEdit")
	public AdminUser showEdit(@RequestParam Long id) {
		AdminUser adminUser = adminUserService.queryById(id);
		return adminUser;
	}

	/**
	 * 删除
	 */
	@ResponseBody
	@PostMapping(value = "/deleteAdminUser")
	public String deleteAdminUser(@RequestParam Long id) {
		int flag = adminUserService.deleteAdminUser(id);
		if (0 < flag) {
			return Constant.SUCCESS.getCode();
		} else {
			return Constant.ERROR.getCode();
		}
	}

	/**
	 * 保证分页Model
	 */
	private Model pageModel(Model model, PageInfo pageList) {
		// 获得当前页
		model.addAttribute("pageNum", pageList.getPageNum());
		// 获得一页显示的条数
		model.addAttribute("pageSize", pageList.getPageSize());
		// 是否是第一页
		model.addAttribute("isFirstPage", pageList.isIsFirstPage());
		// 获得总页数
		model.addAttribute("totalPages", pageList.getPages());
		// 是否是最后一页
		model.addAttribute("isLastPage", pageList.isIsLastPage());
		return model;
	}

}