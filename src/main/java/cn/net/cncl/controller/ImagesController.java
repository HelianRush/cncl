package cn.net.cncl.controller;

import java.util.Map;

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
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.net.cncl.common.Constant;
import cn.net.cncl.entity.Images;
import cn.net.cncl.service.ImagesService;

@Controller
@RequestMapping(value = "/ImagesCtl")
public class ImagesController {

	private static Logger logger = LoggerFactory.getLogger(ImagesController.class);

	@Autowired
	private ImagesService ImagesService;

	/**
	 * 图片上传
	 */
	@ResponseBody
	@PostMapping(value = "/fileUpload")
	public String fileUpload(MultipartFile files, HttpSession session) throws Exception {

		String url = session.getServletContext().getRealPath("/");
		System.out.println(url);

		return ImagesService.addImage(files);
	}

	/**
	 * 查询图片
	 */
	@RequestMapping(value = "/showImages")
	public String showImages(HttpServletRequest request, Model model, @RequestParam(defaultValue = "1") Integer pageNum) {
		PageInfo<Images> pageList = ImagesService.queryImages(pageNum);
		this.pageModel(model, pageList);
		// 当前列表
		model.addAttribute("list", pageList.getList());
		return "manager_images";
	}

	/**
	 * 获取 滚动图片ID
	 */
	@ResponseBody
	@RequestMapping(value = "/getTopImages")
	public String getTopImages() {
		Map<String, String> images = ImagesService.getTopImages();
		return JSONObject.toJSONString(images);
		// return JSONObject.parseObject(JSONObject.toJSONString(images));
	}

	/**
	 * 设置滚动图片
	 */
	@RequestMapping(value = "/editTopImages")
	public String editTopImages(HttpServletRequest request) {
		int flag = ImagesService.editTopImages(request);
		return "manager_topImages";

		// 1538734038995 1538734039081 1538734039153 1538734039166 1538734039219
	}

	/**
	 * 编辑
	 */
	public String editImage() {
		return null;
	}

	/**
	 * 删除
	 */
	@ResponseBody
	@PostMapping(value = "/removeImage")
	public String removeImage(@RequestParam Long id) {
		int flag = ImagesService.deleteImage(id);
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
