package cn.net.cncl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.net.cncl.entity.Images;

@Mapper
public interface ImagesMapper {

	/*
	 * 自动生成
	 */
	// int deleteByPrimaryKey(Long imageId);
	// int updateByPrimaryKey(Images record);

	/**
	 * 图片上传<br>
	 */
	int inputImage(Images image);

	/**
	 * 查询图片
	 */
	List<Images> selectImages();

	/**
	 * 查询图片 By ID
	 */
	Images selectImageById(Long imageId);

	/**
	 * 删除
	 */
	int deleteByPrimaryKey(Long imageId);

	/**
	 * 获取 滚动图片ID
	 */
	List<String> getTopImages();

	/**
	 * 设置滚动图片
	 */
	int editTopImages(List<String> editList);

	int editTopImagesByNull(List<String> editList);

	/********************************************************************************
	 *********************************** API 接口 ***********************************
	 ********************************************************************************/
	/**
	 * 获取 滚动图片
	 */
	List<Images> getApiTopImages();

}