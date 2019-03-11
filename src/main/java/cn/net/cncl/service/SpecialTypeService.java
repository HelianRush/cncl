package cn.net.cncl.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.net.cncl.entity.SpecialType;

// 专题类别 T_SPECIAL_TYPE t_special_type
public interface SpecialTypeService {

	// 查询 专题类别 列表 分页
	PageInfo<SpecialType> selectSpecialType(Integer pageNum);

	// 根据ID查询
	SpecialType queryById(Long id);

	// 查询全部
	List<SpecialType> querySpecialTypeAll();

	// 编辑
	int editSpecialType(SpecialType specialType);

	// 删除
	int deleteSpeciaType(Long id);

	// ========== ========== ========== ========== ========== //

	// 专题分类接口
	JSONObject querytSpecialType();

}
