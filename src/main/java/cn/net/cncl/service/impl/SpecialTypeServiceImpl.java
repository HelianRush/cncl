package cn.net.cncl.service.impl;

import java.util.Date;
import java.util.List;

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
import cn.net.cncl.entity.Images;
import cn.net.cncl.entity.Special;
import cn.net.cncl.entity.SpecialType;
import cn.net.cncl.mapper.ImagesMapper;
import cn.net.cncl.mapper.SpecialMapper;
import cn.net.cncl.mapper.SpecialTypeMapper;
import cn.net.cncl.service.SpecialTypeService;

@Service
@Transactional
public class SpecialTypeServiceImpl implements SpecialTypeService {

	private static Logger logger = LoggerFactory.getLogger(SpecialTypeServiceImpl.class);

	@Autowired
	private SpecialTypeMapper specialTypeMapper;

	@Autowired
	private SpecialMapper specialMapper;

	@Autowired
	private ImagesMapper imagesMapper;

	// main
	public static void main(String[] args) {

	}

	// 查询 专题类别 列表 分页
	@Override
	public PageInfo<SpecialType> selectSpecialType(Integer pageNum) {
		PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
		List<SpecialType> list = specialTypeMapper.selectSpecialType();
		PageInfo<SpecialType> pageInfo = new PageInfo<SpecialType>(list);
		return pageInfo;
	}

	// 根据ID查询
	@Override
	public SpecialType queryById(Long specialTypeId) {
		return specialTypeMapper.selectByPrimaryKey(specialTypeId);
	}

	// 编辑
	@Override
	public int editSpecialType(SpecialType specialType) {
		int flag = 0;
		// 如果没有ID，新增，如果有ID，修改
		if (null == specialType.getSpecialTypeId() || specialType.getSpecialTypeId().equals(null)) {
			// 新增
			specialType.setSpecialTypeId(new Date().getTime());
			flag = specialTypeMapper.insert(specialType);
		} else {
			// 修改
			flag = specialTypeMapper.updateByPrimaryKeySelective(specialType);
		}
		return flag;
	}

	// 删除
	@Override
	public int deleteSpeciaType(Long specialTypeId) {
		return specialTypeMapper.deleteByPrimaryKey(specialTypeId);
	}

	// 查询全部
	@Override
	public List<SpecialType> querySpecialTypeAll() {
		return specialTypeMapper.selectSpecialType();
	}

	// ========== ========== ========== ========== ========== //

	// 专题分类接口
	@Override
	public JSONObject querytSpecialType() {
		JSONObject body = new JSONObject();
		JSONArray dataList = new JSONArray();
		List<SpecialType> specialTypeList = specialTypeMapper.selectSpecialType();

		for (SpecialType specialType : specialTypeList) {

			List<Special> list = specialMapper.apiTopSpecialList(specialType.getSpecialTypeId());

			Long imageIdFk = null;

			for (Special special : list) {
				// clear
				imageIdFk = null;
				// set type
				special.setSpecialType(specialType);
				// set image
				imageIdFk = special.getImageIdFk();
				Images image = imagesMapper.queryImageById(imageIdFk);
				special.setImage(image);
			}
			specialType.setSpecials(list);

			// add list
			// String jsonString = JSONObject.toJSONString(specialType,
			// SerializerFeature.DisableCircularReferenceDetect);
			// SpecialType temp = JSONObject.parseObject(jsonString, SpecialType.class);
			dataList.add(specialType);
		}
		body.put("dataList", dataList);
		return body;
	}

}
