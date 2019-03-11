package cn.net.cncl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.net.cncl.entity.Special;

@Mapper
public interface SpecialMapper {

	// 查询 全部
	List<Special> selectSpecials();

	// 查询 by ID
	Special selectByPrimaryKey(Long specialId);

	// 新增
	int insertSpecial(Special special);

	// 修改
	int updateByPrimaryKeySelective(Special special);

	// 删除
	int deleteByPrimaryKey(Long specialId);

	// ========== ========== ========== ========== ========== //

	// API 首页专题推荐8个 根据类别返回列表
	List<Special> apiTopSpecialList(Long specialTypeId);

	// 专题页 展示列表 根据类别 全部 分页
	List<Special> selectSpecialsByParams(Long specialTypeId);

	// API 模糊查询
	List<Special> querytSpecialByName(@Param("specialTitle") String specialTitle);

	//
	// int insert(SpecialWithBLOBs record);
	//
	// int insertSelective(SpecialWithBLOBs record);
	//
	// SpecialWithBLOBs selectByPrimaryKey(Long specialId);
	//
	// int updateByPrimaryKeyWithBLOBs(SpecialWithBLOBs record);
	//
	// int updateByPrimaryKey(Special record);
}