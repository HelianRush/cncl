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
import cn.net.cncl.entity.News;
import cn.net.cncl.entity.NewsType;
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
	@SuppressWarnings("unused")
	@RequestMapping(value = "/editNews")
	public String editNews(HttpServletRequest request, News news, Model model) {
		Long newsId = news.getNewsId();
		int flag = 0;
		if (null == newsId) {
			// news.setAdminUserIdFk(SessionUser.getUser(request).getAdminUserId());
			flag = newsService.addNews(news);
		} else {
			flag = newsService.editNews(news);
		}
		PageInfo<News> pageList = newsService.selectNews(1);
		this.pageModel(model, pageList);
		// 当前列表
		model.addAttribute("list", pageList.getList());
		return "manager_news";
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
