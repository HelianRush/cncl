package cn.net.cncl.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;

import cn.net.cncl.entity.News;
import cn.net.cncl.entity.NewsType;
import cn.net.cncl.service.NewsService;

@Controller
@RequestMapping("/NewsController")
public class NewsController {
	private static Logger logger = LoggerFactory.getLogger(NewsController.class);

	@Autowired
	private NewsService newsService;

	/**
	 * 资讯 查询
	 */
	@RequestMapping(value = "/selectNews")
	public String selectNews(HttpServletRequest request, Model model, @RequestParam(defaultValue = "1") Integer pageNum) {

		PageInfo<News> pageList = newsService.selectNews(pageNum);

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
		// 当前列表
		model.addAttribute("list", pageList.getList());
		return "/manager_news";
	}

	/**
	 * 资讯新增&编辑
	 */
	@RequestMapping(value = "/editNewsType")
	public String editNews(NewsType newsType) {
		return null;
	}

	/**
	 * 资讯 删除
	 */
	@RequestMapping(value = "removeNews")
	public String removeNews(@RequestParam long newsId) {
		return null;
	}

}