package cn.net.cncl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.net.cncl.entity.Images;

@Mapper
public interface ImagesMapper {

	// int insert(ImagesWithBLOBs record);

	// int insertSelective(ImagesWithBLOBs record);

	// ImagesWithBLOBs selectByPrimaryKey(Long imageId);

	// int updateByPrimaryKeySelective(ImagesWithBLOBs record);

	// int updateByPrimaryKeyWithBLOBs(ImagesWithBLOBs record);

	int updateByPrimaryKey(Images record);

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
}