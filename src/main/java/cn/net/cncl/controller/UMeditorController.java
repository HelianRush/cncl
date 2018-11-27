package cn.net.cncl.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.net.cncl.common.Uploader;

@Controller
public class UMeditorController {

	@ResponseBody
	@RequestMapping(value = "/uploadUEImage", method = RequestMethod.POST)
	public String uploadUEImage(MultipartFile upfile, HttpServletRequest request) throws Exception {
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
		if (callback == null) {
			return result;
		} else {
			return "<script>" + callback + "(" + result + ")</script>";
		}
	}

}
