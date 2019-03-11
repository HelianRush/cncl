package cn.net.cncl.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import cn.net.cncl.entity.Cooperation;

public interface CooperationService {

	// 查询
	List<Cooperation> selectCooperation();

	// 修改
	int updateCoperation(Cooperation cooperation);

	// 新增
	int insertCoperation(Cooperation cooperation);

	// 根据ID查询
	Cooperation queryCooperationById(Long cooperationId);

	// ========== ========== ========== ========== ========== //

	// 入驻页
	JSONObject queryCooperation();

}
