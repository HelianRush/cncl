package cn.net.cncl.service;

import org.springframework.web.multipart.MultipartFile;

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

}
