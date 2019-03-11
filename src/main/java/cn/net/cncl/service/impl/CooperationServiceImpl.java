package cn.net.cncl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import cn.net.cncl.entity.Cooperation;
import cn.net.cncl.mapper.CooperationMapper;
import cn.net.cncl.service.CooperationService;

@Service
@Transactional
public class CooperationServiceImpl implements CooperationService {

	private static Logger logger = LoggerFactory.getLogger(CooperationServiceImpl.class);

	/**
	 * CooperationMapper
	 */
	@Autowired
	private CooperationMapper cooperationMapper;

	// mains
	public static void main(String[] args) {

	}

	// ========== ========== ========== ========== ========== //

	// 查询
	@Override
	public List<Cooperation> selectCooperation() {
		return cooperationMapper.selectCoperation();
	}

	// 新增
	@Override
	public int insertCoperation(Cooperation cooperation) {
		return cooperationMapper.insertCoperation(cooperation);
	}

	// 修改
	@Override
	public int updateCoperation(Cooperation cooperation) {
		return cooperationMapper.updateCoperation(cooperation);
	}

	// 根据ID查询
	@Override
	public Cooperation queryCooperationById(Long cooperationId) {
		return cooperationMapper.selectCoperationById(cooperationId);
	}

	// ========== ========== ========== ========== ========== //

	// 入驻页
	@Override
	public JSONObject queryCooperation() {
		JSONObject body = new JSONObject();
		Cooperation cooperation = cooperationMapper.selectCoperationById(1000000000000001L);
		body.put("cooperation", cooperation);
		return body;
	}

}
