package cn.net.cncl.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.net.cncl.entity.Special;

// 专题 T_SPECIAL t_special
public interface SpecialService {

	// 查询
	PageInfo<Special> selectNews(Integer pageNum);

	// 新增
	int addSpecial(Special special);

	// 修改
	int editSpecial(Special special);

	// 根据ID查询
	Special querySpecialById(Long specialId);

	// 删除
	int deleteSpecial(long id);

	// ========== ========== ========== ========== ========== //

	// API 首页专题推荐8个 根据类别返回列表
	JSONObject apiTopSpecialList(Map<String, Object> params);

	// 专题页 展示列表 根据类别 全部 分页
	JSONObject querySpecialByType(Map<String, Object> params);

	// 专题 单个查询 by ID
	JSONObject querySpecialById(Map<String, Object> params);

	// 模糊查询
	List<Special> querytSpecialByName(Map<String, Object> params);

}
