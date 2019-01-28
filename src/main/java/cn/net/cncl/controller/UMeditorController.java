package cn.net.cncl.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.net.cncl.common.MD5Tool;
import cn.net.cncl.common.Uploader;
import cn.net.cncl.entity.Images;
import cn.net.cncl.service.ImagesService;

@Controller
public class UMeditorController {

	private static Logger logger = LoggerFactory.getLogger(UMeditorController.class);

	@Autowired
	private ImagesService imagesService;

	@ResponseBody
	@RequestMapping(value = "/uploadUEImage", method = RequestMethod.POST)
	public String uploadUEImage(MultipartFile upfile, HttpServletRequest request, HttpSession session) throws Exception {
		Uploader up = new Uploader(request);

		// FileUploadPath.getFileUploadPath();

		up.setSavePath("/UMEupload");
		String[] fileType = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
		up.setAllowFiles(fileType);
		up.setMaxSize(102400); // 单位KB
		up.upload(upfile);

		String callback = request.getParameter("callback");
		String result = "{\"name\":\"" + up.getFileName() + "\", \"originalName\": \"" + up.getOriginalName() + "\", \"size\": " + up.getSize() + ", \"state\": \"" + up.getState() + "\", \"type\": \"" + up.getType() + "\", \"url\": \"" + up.getUrl() + "\"}";

		// System.out.println(result);

		result = result.replaceAll("\\\\", "\\\\");

		System.out.println(result);

		// 图片入库
		Images image = new Images();
		image.setImageId(new Date().getTime()); // ID
		image.setImageCode(MD5Tool.getMD5(image.getImageId().toString() + up.getOriginalName()));// CODE
		image.setResourceBy(null); // 资源所属,关联资源ID
		image.setImageName(up.getOriginalName()); // 图片名称
		image.setExtensionName(up.getType()); // 扩展名
		image.setImageTitle(null); // 图片标题
		image.setImagePaths(up.getUrl());// 相对路径
		image.setImagePath("-"); // 图片地址
		image.setImageSize(up.getSize()); // 文件大小
		image.setCreateTime(new Date());
		image.setImageContent(null); // 图片配文
		image.setDescription(null); // 描述
		image.setTopStatus(null);
		imagesService.addImage(image);

		// ID加Session
		String attribute = (String) session.getAttribute("imageArray");
		// id + , + attribute
		session.setAttribute("imageArray", image.getImageId() + "," + attribute);

		String temp = session.getAttribute("imageArray").toString();
		System.out.println(temp);

		if (callback == null) {
			return result;
		} else {
			return "<script>" + callback + "(" + result + ")</script>";
		}
	}

}
