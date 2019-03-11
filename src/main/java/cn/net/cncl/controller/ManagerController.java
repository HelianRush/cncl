package cn.net.cncl.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.util.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.net.cncl.common.Constant;
import cn.net.cncl.entity.News;
import cn.net.cncl.entity.NewsType;
import cn.net.cncl.entity.WebInfo;
import cn.net.cncl.service.AdminUserService;
import cn.net.cncl.service.CelebritysService;
import cn.net.cncl.service.ImagesService;
import cn.net.cncl.service.LoginService;
import cn.net.cncl.service.ManagerService;
import cn.net.cncl.service.NewsService;
import cn.net.cncl.service.NewsTypeService;

/**
 * 后台管理 Controoler
 */
@Controller
@RequestMapping("/ManagerCtl")
public class ManagerController {

	private static Logger logger = LoggerFactory.getLogger(ManagerController.class);

	@Autowired
	private LoginService loginService;

	@Autowired
	private AdminUserService adminUserService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private CelebritysService celebritysService;

	@Autowired
	private NewsService newsService;

	@Autowired
	private NewsTypeService newsTypeService;

	@Autowired
	private ImagesService imagesService;

	/**
	 * Error Page
	 */
	@RequestMapping(value = "/404")
	public String show404() {
		return "404";
	}

	/**
	 * Error Page
	 */
	@RequestMapping(value = "/500")
	public String show500() {
		return "500";
	}

	/**
	 * Error Page
	 */
	@RequestMapping(value = "/300")
	public String show300() {
		return "300";
	}

	/**
	 * 查询 网站信息
	 */
	@ResponseBody
	@RequestMapping(value = "/getWebInfoObject")
	public WebInfo getWebInfoObject(HttpServletRequest request) {
		return managerService.getWebInfoObject();
	}

	/**
	 * 编辑网站信息
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/updateWebInfo")
	public String updateWebInfo(WebInfo webInfo, MultipartFile uploadLogo, RedirectAttributes redirectAttributes) throws IOException {

		if (null != uploadLogo) {
			String contentType = uploadLogo.getContentType();
			String imgType = null;
			if (contentType.equals(Constant.IMAGE_PNG))
				imgType = ".png";
			else if (contentType.equals(Constant.IMAGE_GIF))
				imgType = ".gif";
			else if (contentType.equals(Constant.IMAGE_JPG_JPEG))
				imgType = ".jpg";
			else if (contentType.equals(Constant.IMAGE_BMP))
				imgType = ".bmp";

			String fileName = "logo1" + imgType;
			String path = ResourceUtils.getURL("classpath:").getPath() + Constant.STATIC_PATH + "/images/" + fileName;

			BufferedInputStream in = new BufferedInputStream(uploadLogo.getInputStream());
			File file = new File(path);
			FileOutputStream out = new FileOutputStream(file);
			BufferedOutputStream output = new BufferedOutputStream(out);
			Streams.copy(in, output, true);

			String setPath = "/images/" + fileName;
		}

		int flag = managerService.updateWebInfo(webInfo);

		if (0 < flag) {
			return "manager_web";
		} else {
			return "manager_web_edit";
		}
	}

	/**
	 * @Title showManagerWeb
	 * @author Jianfei Yu
	 * @version 1.0.0
	 * @parameter null
	 * @throws null
	 *             访问manager_web.html<br>
	 *             网站信息
	 */
	@RequestMapping(value = "/showManagerWeb")
	public String showManagerWeb(HttpServletRequest request) {
		return "manager_web";
	}

	/**
	 * @Title showManagerWebEdit
	 * @author Jianfei Yu
	 * @version 1.0.0
	 * @parameter null
	 * @throws null
	 *             访问manager_web_edit.html<br>
	 *             网站信息编辑
	 */
	@RequestMapping(value = "/showManagerWebEdit")
	public String showManagerWebEdit(HttpServletRequest request) {
		return "manager_web_edit";
	}

	/**
	 * @Title showManagerMenus
	 * @author Jianfei Yu
	 * @version 1.0.0
	 * @parameter null
	 * @throws null
	 *             访问manager_menus.html<br>
	 *             导航
	 */
	@RequestMapping(value = "/showManagerMenus")
	public String showManagerMenus(HttpServletRequest request) {
		return "manager_menus";
	}

	/**
	 * @Title showManagerAdminUsers
	 * @author Jianfei Yu
	 * @version 1.0.0
	 * @parameter null
	 * @throws null
	 *             访问manager_admin_users.html<br>
	 *             管理员信息
	 */
	@RequestMapping(value = "/showManagerAdminUsers")
	public String showManagerAdminUsers(HttpServletRequest request) {
		return "manager_admin_users";
	}

	/**
	 * @Title showManagerCelebritysEdit
	 * @author Jianfei Yu
	 * @version 1.0.0
	 * @parameter null
	 * @throws null
	 *             访问manager_celebritys_edit.html<br>
	 *             名人库编辑
	 */
	@RequestMapping(value = "/showManagerCelebritysEdit")
	public String showManagerCelebritysEdit(HttpServletRequest request, HttpSession session) {
		session.removeAttribute("imageArray");
		session.setAttribute("imageArray", "");
		return "manager_celebritys_edit";
	}

	/**
	 * @Title showManagerCelebritys
	 * @author Jianfei Yu
	 * @version 1.0.0
	 * @parameter null
	 * @throws null
	 *             访问manager_celebritys.html<br>
	 *             名人库展示
	 */
	@RequestMapping(value = "/showManagerCelebritys")
	public String showManagerCelebritys(HttpServletRequest request) {
		return "manager_celebritys";
	}

	/**
	 * @Title showNewsEdit
	 * @author Jianfei Yu
	 * @version 1.0.0
	 * @parameter null
	 * @throws null
	 *             访问manager_news_edit.html<br>
	 *             资讯新增&编辑
	 */
	@RequestMapping(value = "/showNewsEdit")
	public String showNewsEdit(HttpServletRequest request, News news, Model model, HttpSession session) {
		session.removeAttribute("imageArray");
		session.setAttribute("imageArray", "");
		List<NewsType> newsTypeList = newsTypeService.queryNewsTypeAll();
		model.addAttribute("newsTypeList", newsTypeList);
		return "manager_news_edit";
	}

	/**
	 * @Title showNews
	 * @author Jianfei Yu
	 * @version 1.0.0
	 * @parameter null
	 * @throws null
	 *             访问showNews.html<br>
	 *             资讯
	 */
	@RequestMapping(value = "/showNews")
	public String showNews(HttpServletRequest request) {
		return "manager_news";
	}

	/**
	 * 编辑专题
	 */
	@RequestMapping(value = "/showManagerSpecialTopicEdit")
	public String showManagerSpecialTopicEdit() {
		return "201";
	}

	/**
	 * 编辑入驻
	 */
	@RequestMapping(value = "/showManagerEnterEdit")
	public String showManagerEnterEdit() {
		return "manager_coopeerationEdit";
	}

	/**
	 * 图片上传
	 */
	@RequestMapping(value = "/showManagerImagesUpload")
	public String showManagerImagesUpload() {
		return "manager_images_upload";
	}

	/**
	 * 视频管理
	 */
	@RequestMapping(value = "/showManagerVides")
	public String showManagerVides() {
		return "201";
	}

	/**
	 * 滚动图片
	 */
	@RequestMapping(value = "/topImages")
	public String showTopImages() {
		return "manager_topImages";
	}

	/**
	 * 图片展示
	 */
	@RequestMapping(value = "/showManagerEditImage")
	public String showManagerEditImage(HttpSession session, @RequestParam Long imageId) {
		session.setAttribute("imageId", imageId);
		return "manager_images_edit";
	}

}
