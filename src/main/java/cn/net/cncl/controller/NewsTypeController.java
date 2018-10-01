package cn.net.cncl.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.net.cncl.common.Constant;
import cn.net.cncl.entity.NewsType;
import cn.net.cncl.service.NewsTypeService;

@Controller
@RequestMapping(value = "/newsTypeController")
public class NewsTypeController {

	private static Logger logger = LoggerFactory.getLogger(NewsTypeController.class);

	@Autowired
	private NewsTypeService newsTypeService;

	/**
	 * 资讯类别查询
	 */
	@RequestMapping(value = "/queryNewsType")
	public String queryNewsType(HttpServletRequest request, Model model, @RequestParam(defaultValue = "1") Integer pageNum) {
		PageInfo<NewsType> pageList = newsTypeService.selectNewsType(pageNum);
		model = this.pageModel(model, pageList);
		// 当前列表
		model.addAttribute("list", pageList.getList());
		return "/manager_news_type";
	}

	/**
	 * 检查 编码唯一性
	 */
	@ResponseBody
	@RequestMapping(value = "/checkCode")
	public String checkCode(@RequestParam String code) {
		int flag = newsTypeService.selectByCode(code);
		if (0 < flag) {
			return Constant.IS_HAVE.getMessage();
		} else {
			return Constant.SUCCESS.getCode();
		}
	}

	/**
	 * 检查 名称唯一性
	 */
	@ResponseBody
	@RequestMapping(value = "/checkName")
	public String checkName(@RequestParam String name) {
		int flag = newsTypeService.selectByName(name);
		if (0 < flag) {
			return Constant.IS_HAVE.getMessage();
		} else {
			return Constant.SUCCESS.getCode();
		}
	}

	/**
	 * 资讯类别新增&编辑
	 */
	@RequestMapping(value = "/editNewsType")
	public String editNewsType(Model model, NewsType newsType) {
		int flag = newsTypeService.editNewsType(newsType);
		Integer pageNum = 1;
		PageInfo<NewsType> pageList = newsTypeService.selectNewsType(pageNum);
		model = this.pageModel(model, pageList);
		// 当前列表
		model.addAttribute("list", pageList.getList());
		return "/manager_news_type";
	}

	/**
	 * 资讯类别 删除
	 */
	@RequestMapping(value = "/removeNewsType")
	public String removeNewsType(Model model, @RequestParam Long newsTypeId) {

		int flag = newsTypeService.deleteNewsType(newsTypeId);

		Integer pageNum = 1;
		PageInfo<NewsType> pageList = newsTypeService.selectNewsType(pageNum);
		model = this.pageModel(model, pageList);
		// 当前列表
		model.addAttribute("list", pageList.getList());
		return "/manager_news_type";
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
