package cn.net.cncl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import cn.net.cncl.entity.Images;
import cn.net.cncl.entity.News;
import cn.net.cncl.entity.NewsType;
import cn.net.cncl.mapper.ImagesMapper;
import cn.net.cncl.service.NewsService;
import cn.net.cncl.service.NewsTypeService;

@Controller
@RequestMapping("/NewsCtl")
public class NewsController {
	private static Logger logger = LoggerFactory.getLogger(NewsController.class);

	@Autowired
	private NewsService newsService;

	@Autowired
	private NewsTypeService newsTypeService;

	@Autowired
	private ImagesMapper imagesMapper;

	/**
	 * 资讯 查询
	 */
	@RequestMapping(value = "/selectNews")
	public String selectNews(HttpServletRequest request, Model model, @RequestParam(defaultValue = "1") Integer pageNum) {
		PageInfo<News> pageList = newsService.selectNews(pageNum);
		this.pageModel(model, pageList);
		// 当前列表
		model.addAttribute("list", pageList.getList());
		return "manager_news";
	}

	/**
	 * 资讯新增&编辑
	 */
	@RequestMapping(value = "/editNews")
	public String editNews(HttpServletRequest request, HttpSession session, News news, Model model) {

		AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");
		if (null == adminUser || adminUser.equals(null)) {
			news.setAdminUserIdFk(10000000000000001L);
		} else {
			news.setAdminUserIdFk(adminUser.getAdminUserId());
		}

		Long newsId = news.getNewsId();
		int flag = 0;
		if (null == newsId) {
			// 新增
			flag = newsService.addNews(news);
		} else {
			// 修改
			flag = newsService.editNews(news);
		}

		if (0 < flag) {
			PageInfo<News> pageList = newsService.selectNews(1);
			this.pageModel(model, pageList);
			// 当前列表
			model.addAttribute("list", pageList.getList());

			// 保存成功，编辑图片信息
			String images = (String) session.getAttribute("imageArray");
			String[] imgArray = images.split(",");

			if ("" != images && !images.equals("") && null != images && !images.equals(null)) {
				for (int i = 0; i < imgArray.length; i++) {
					String imgId = imgArray[i];
					Images image = imagesMapper.queryImageById(Long.valueOf(imgId));
					image.setResourceBy(news.getNewsId());
					image.setResourceByType("news");
					image.setImageTitle("《" + news.getNewsTitel() + "》");
					image.setImageContent("需要自定义");
					image.setDescription("《" + news.getNewsTitel() + "》文章的标题图片。");

					imagesMapper.editImage(image);

					if (i == (imgArray.length - 1)) {
						news.setImageIdFk(Long.valueOf(imgId));
						flag = newsService.editNews(news);
					}
				}

				// 删除session
				session.removeAttribute("imageArray");
			}

			return "manager_news";
		} else {
			return "manager_news_edit";
		}

	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "/showManagerNewsEdit")
	public String showManagerNewsEdit(HttpSession session, Model model, @RequestParam Long newsId) {
		// 根据ID查询
		News news = newsService.queryNewsByID(newsId);
		List<NewsType> newsTypeList = newsTypeService.queryNewsTypeAll();
		model.addAttribute("newsTypeList", newsTypeList);
		model.addAttribute("news", news);
		session.setAttribute("newsId", newsId);
		session.removeAttribute("imageArray");
		session.setAttribute("imageArray", "");
		return "manager_news_edit";
	}

	@ResponseBody
	@RequestMapping(value = "/getNewsById")
	public News getNewsById(HttpSession session) {
		Long newsId = (Long) session.getAttribute("newsId");
		News news = null;
		if (null != newsId) {
			news = newsService.queryNewsByID(newsId);
		}
		session.removeAttribute("newsId");
		return news;
	}

	/**
	 * 资讯 删除
	 */
	@ResponseBody
	@PostMapping(value = "/deleteNews")
	public String deleteNews(@RequestParam long id) {
		int flag = newsService.deleteNews(id);
		if (0 < flag) {
			// 删除所属图片
			int flag_img = imagesMapper.delByResourceBy(id);

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
