package cn.net.cncl.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.net.cncl.entity.Images;

public interface ImagesService {

	/**
	 * 图片上传
	 * 
	 * @throws Exception
	 */
	String addImage(MultipartFile files) throws Exception;

	/**
	 * 查询
	 */
	PageInfo<Images> queryImages(Integer pageNum);

	/**
	 * 删除
	 */
	int deleteImage(Long id);

	/**
	 * 获取 滚动图片ID
	 */
	Map<String, String> getTopImages();

	/**
	 * 设置滚动图片
	 */
	int editTopImages(HttpServletRequest request);

	/**
	 * 根据ID获取图片对象
	 */
	Images queryImageById(Long imageId);

	/**
	 * 图片编辑
	 */
	int updateImage(Images image);

	/********************************************************************************
	 *********************************** API 接口 ***********************************
	 ********************************************************************************/
	/**
	 * 获取 滚动图片
	 */
	JSONObject getApiTopImages();

}
