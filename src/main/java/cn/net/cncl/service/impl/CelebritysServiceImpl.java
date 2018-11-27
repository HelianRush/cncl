package cn.net.cncl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.net.cncl.common.Constant;
import cn.net.cncl.entity.Celebritys;
import cn.net.cncl.mapper.CelebritysMapper;
import cn.net.cncl.service.CelebritysService;

@Service
@Transactional
public class CelebritysServiceImpl implements CelebritysService {

	private static Logger logger = LoggerFactory.getLogger(CelebritysServiceImpl.class);

	@Autowired
	public CelebritysMapper celebritysMapper;

	/**
	 * 名人库列表
	 */
	@Override
	public PageInfo<Celebritys> showCelebritysList(int pageNum) {
		PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
		List<Celebritys> list = celebritysMapper.queryCelebritys();
		PageInfo<Celebritys> pageInfo = new PageInfo<Celebritys>(list);
		return pageInfo;
	}

	/**
	 * 添加名人库
	 */
	@Override
	public int insertCelebritys(Celebritys celebritys) {
		celebritys.setCelebrityId(new Date().getTime());
		// celebritys.setVideoIdFk(0L);
		// celebritys.setImageIdFk(0L);
		celebritys.setBrowseCount(0);
		return celebritysMapper.insertCelebritys(celebritys);
	}

	/**
	 * 修改名人库
	 */
	@Override
	public int updateCelebritys(Celebritys celebritys) {
		return celebritysMapper.updateCelebritys(celebritys);
	}

	/**
	 * 删除
	 */
	@Override
	public int deleteCelebritys(Long id) {
		return celebritysMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据ID查询
	 */
	@Override
	public Celebritys queryCelebritysByID(Long celebrityId) {
		return celebritysMapper.selectCelebritysByID(celebrityId);
	}

	/********************************************************************************
	 *********************************** API 接口 ***********************************
	 ********************************************************************************/

	/**
	 * 根据姓名、身份证、证书编号 精确查询
	 */
	@Override
	public JSONObject queryByClelbrity(Map<String, Object> params) {

		JSONObject body = new JSONObject();
		JSONArray dataList = new JSONArray();
		List<Celebritys> list = null;

		String celebrityName = params.get("celebrityName").toString();
		String identityCard = params.get("identityCard").toString();
		String certificateCode = params.get("certificateCode").toString();

		if (celebrityName.equals(null) || celebrityName.equals(""))
			celebrityName = null;

		if (identityCard.equals(null) || identityCard.equals(""))
			identityCard = null;

		if (certificateCode.equals(null) || certificateCode.equals(""))
			certificateCode = null;

		if (0 == params.size()) {
			list = new ArrayList<Celebritys>();
		} else {
			list = celebritysMapper.queryByClelbrity(params);
		}

		for (Celebritys celebrity : list) {
			dataList.add(celebrity);
		}

		body.put("dataList", dataList);
		return body;
	}

	/**
	 * 根据姓名 模糊查询
	 */
	@Override
	public List<Celebritys> queryClelbrityByName(Map<String, Object> params) {
		String celebrityName = null;
		List<Celebritys> list = null;
		if (0 == params.size()) {
			list = new ArrayList<Celebritys>();
		} else {
			if (params.containsKey("keyword")) {
				celebrityName = params.get("keyword").toString();
			}
			list = celebritysMapper.queryClelbrityByName(celebrityName);
		}
		return list;
	}

	/**
	 * 名人库推荐列表
	 */
	@Override
	public JSONObject getTopCelebrity() {

		JSONObject body = new JSONObject();
		JSONArray dataList = new JSONArray();
		List<Celebritys> list = celebritysMapper.getTopCelebrity();

		for (Celebritys celebrity : list) {
			dataList.add(celebrity);
		}
		body.put("dataList", dataList);
		return body;
	}

	/**
	 * 名人库查询
	 */
	@Override
	public JSONObject queryClelbrityByNames(Map<String, Object> params) {
		JSONObject body = new JSONObject();
		JSONArray dataList = new JSONArray();

		String celebrityName = null;
		List<Celebritys> list = null;

		if (0 == params.size()) {
			list = new ArrayList<Celebritys>();
		} else {
			if (params.containsKey("keyword")) {
				celebrityName = params.get("keyword").toString();
			}
			list = celebritysMapper.queryClelbrityByName(celebrityName);
		}

		for (Celebritys celebrity : list) {
			dataList.add(celebrity);
		}

		body.put("dataList", dataList);
		return body;
	}

	/**
	 * 名人库列表
	 */
	@Override
	public JSONObject celebrityList(Map<String, Object> params) {
		JSONObject body = new JSONObject();
		int pageNum = Integer.parseInt(String.valueOf(params.get("pageNum")));
		PageHelper.startPage(pageNum, Constant.API_PAGE_SIZE);
		List<Celebritys> list = celebritysMapper.queryCelebritys();
		PageInfo<Celebritys> pageInfo = new PageInfo<Celebritys>(list);
		body.put("dataList", pageInfo);
		return body;
	}

	/**
	 * 单条名人库
	 */
	@Override
	public JSONObject apiQueryCelebritysByID(Map<String, Object> params) {
		JSONObject body = new JSONObject();
		long celebrityId = Long.valueOf(params.get("celebrityId").toString());
		Celebritys celebrity = celebritysMapper.selectCelebritysByID(celebrityId);
		body.put("celebrity", celebrity);
		return body;
	}

};