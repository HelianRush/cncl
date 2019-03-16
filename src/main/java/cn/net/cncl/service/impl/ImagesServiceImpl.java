package cn.net.cncl.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.net.cncl.common.Constant;
import cn.net.cncl.common.FileUploadPath;
import cn.net.cncl.common.MD5Tool;
import cn.net.cncl.entity.Images;
import cn.net.cncl.mapper.ImagesMapper;
import cn.net.cncl.service.ImagesService;

@Service
@Transactional
public class ImagesServiceImpl implements ImagesService {

	private static Logger logger = LoggerFactory.getLogger(ImagesServiceImpl.class);

	@Autowired
	private ImagesMapper imagesMapper;

	/**
	 * 图片上传<br>
	 * 获取文件类型名称<br>
	 * String name = files.getName();<br>
	 * 获取文件名称<br>
	 * String originalFilename = files.getOriginalFilename();<br>
	 * 获取文件类别<br>
	 * String contentType = files.getContentType();<br>
	 * 获取文件大小<br>
	 * long size = files.getSize();<br>
	 * 
	 * @throws Exception
	 */
	@Override
	public String addImage(MultipartFile files) throws Exception {

		Images image = new Images();
		image.setImageId(new Date().getTime()); // ID
		image.setImageCode(MD5Tool.getMD5(image.getImageId().toString() + files.getOriginalFilename()));// CODE
		image.setResourceBy(null); // 资源所属,关联资源ID
		image.setImageName(files.getOriginalFilename()); // 图片名称
		image.setExtensionName(files.getContentType()); // 扩展名
		image.setImageTitle(null); // 图片标题
		image.setImagePaths(null);// 相对路径
		image.setImagePath(null); // 图片地址
		image.setImageSize(files.getSize()); // 文件大小
		image.setCreateTime(new Date());
		image.setImageContent(null); // 图片配文
		image.setDescription(null); // 描述
		image.setTopStatus(null);
		// System.out.println(image);

		/*
		 * 1. 上传图片
		 */
		// 获取跟目录
		File path = FileUploadPath.getFileUploadPath();
		// 重命名图片
		String imageName = String.valueOf(image.getImageId());
		// 获取图片类型
		String type = FileUploadPath.getType(files.getContentType());
		// 构建路径文件
		File uploadFile = new File(path.getAbsolutePath(), imageName + type);
		// 相对路径
		image.setImagePaths(Constant.STATIC_FILE_PATH + imageName + type);
		// 图片地址
		image.setImagePath(uploadFile.getAbsolutePath());
		// 文件保存
		files.transferTo(uploadFile);

		/*
		 * 存入数据库
		 */
		int flag = imagesMapper.inputImage(image);

		if (0 < flag)
			return Constant.SUCCESS.getCode();
		else
			return null; // Constant.ERROR.getCode();
	}

	/**
	 * 图片入库
	 */
	@Override
	public String addImage(Images image) {
		/*
		 * 存入数据库
		 */
		int flag = imagesMapper.inputImage(image);

		if (0 < flag)
			return Constant.SUCCESS.getCode();
		else
			return null; // Constant.ERROR.getCode();
	}

	/**
	 * 查询图片
	 */
	@Override
	public PageInfo<Images> queryImages(Integer pageNum) {
		PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
		List<Images> list = imagesMapper.selectImages();
		PageInfo<Images> pageInfo = new PageInfo<Images>(list);
		return pageInfo;
	}

	/**
	 * 删除
	 */
	@Override
	public int deleteImage(Long id) {

		Images image = imagesMapper.selectImageById(id);
		Long resourceBy = image.getResourceBy();

		if (100001L == image.getImageId())
			return 0;

		if (null == resourceBy || 0L == resourceBy) {

			// 删除文件
			String imagePath = image.getImagePath();
			File file = new File(imagePath);
			if (file.exists())
				file.delete();

			// 删除数据
			if (!file.exists())
				return imagesMapper.deleteByPrimaryKey(id);

			// return
			return 1;
		} else
			return 0;
	}

	/**
	 * 获取 滚动图片ID
	 */
	@Override
	public Map<String, String> getTopImages() {
		List<String> topImages = imagesMapper.getTopImages();
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < topImages.size(); i++) {
			map.put("topImage" + (i + 1), topImages.get(i));
		}
		return map;
	}

	/**
	 * 设置滚动图片
	 */
	@Override
	public int editTopImages(HttpServletRequest request) {

		String topImage1 = request.getParameter("topImage1");
		String topImage2 = request.getParameter("topImage2");
		String topImage3 = request.getParameter("topImage3");
		String topImage4 = request.getParameter("topImage4");
		String topImage5 = request.getParameter("topImage5");

		List<String> editList = new ArrayList<String>();
		editList.add(topImage1);
		editList.add(topImage2);
		editList.add(topImage3);
		editList.add(topImage4);
		editList.add(topImage5);

		imagesMapper.editTopImagesByNull(editList);
		return imagesMapper.editTopImages(editList);
	}

	/**
	 * 根据ID获取图片对象
	 */
	@Override
	public Images queryImageById(Long imageId) {
		return imagesMapper.queryImageById(imageId);
	}

	/**
	 * 图片编辑
	 */
	@Override
	public int updateImage(Images image) {
		return imagesMapper.editImage(image);
	}

	/********************************************************************************
	 *********************************** API 接口 ***********************************
	 ********************************************************************************/
	/**
	 * 获取 滚动图片
	 */
	public JSONObject getApiTopImages() {

		JSONObject body = new JSONObject();
		JSONArray dataList = new JSONArray();

		List<Images> topImages = imagesMapper.getApiTopImages();
		if (0 == topImages.size()) {
			topImages = new ArrayList<Images>();
		}

		for (Images img : topImages) {
			dataList.add(img);
		}

		body.put("dataList", dataList);
		return body;
	}

}
