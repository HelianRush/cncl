package cn.net.cncl.service;

import com.alibaba.fastjson.JSONObject;

import cn.net.cncl.entity.SpecialIndex;

// 专题首页 T_SPECIAL_INDEX t_special_index
public interface SpecialIndexService {

	/**
	 * 获取 专题主页 内容
	 */
	SpecialIndex getSpecialIndexObj();

	/**
	 * 新增
	 */
	int insetSpecialIndex(SpecialIndex specialIndex);

	/**
	 * 更新
	 */
	int updateSpecialIndex(SpecialIndex specialIndex);

	// ========== ========== ========== ========== ========== //
	// 专题页 首页
	JSONObject querySpecialIndex();

}
