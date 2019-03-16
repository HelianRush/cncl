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
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.net.cncl.common.Constant;
import cn.net.cncl.entity.Images;
import cn.net.cncl.entity.Special;
import cn.net.cncl.entity.SpecialType;
import cn.net.cncl.mapper.ImagesMapper;
import cn.net.cncl.mapper.SpecialMapper;
import cn.net.cncl.mapper.SpecialTypeMapper;
import cn.net.cncl.service.SpecialService;

@Service
@Transactional
public class SpecialServiceImpl implements SpecialService {

	private static Logger logger = LoggerFactory.getLogger(SpecialServiceImpl.class);

	@Autowired
	private SpecialMapper specialMapper;

	@Autowired
	private SpecialTypeMapper specialTypeMapper;

	@Autowired
	private ImagesMapper imagesMapper;

	// mains
	public static void main(String[] args) {

	}

	// ========== ========== ========== ========== ========== //

	// 查询 分页
	@Override
	public PageInfo<Special> selectNews(Integer pageNum) {
		PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
		List<Special> list = specialMapper.selectSpecials();
		for (Special special : list) {
			SpecialType specialType = specialTypeMapper.selectByPrimaryKey(special.getSpecialTypeIdFk());
			special.setSpecialType(specialType);
			Images image = imagesMapper.selectImageById(special.getImageIdFk());
			special.setImage(image);
		}
		PageInfo<Special> pageInfo = new PageInfo<Special>(list);
		return pageInfo;
	}

	// 新增
	@Override
	public int addSpecial(Special special) {

		special.setSpecialId(new Date().getTime());
		special.setCreateTime(new Date());
		special.setBrowseCount(0);
		special.setImageIdFk(100001l);

		int flag = 0;
		flag = specialMapper.insertSpecial(special);

		// 更新资源图片信息
		if (0 < flag && null != special.getImageIdFk() && 100001l != special.getImageIdFk()) {
			Images images = imagesMapper.selectImageById(special.getImageIdFk());
			Long resourceBy = images.getResourceBy();
			// 如果资源图片没有资源所属，或者资源所属相同，才能更改图片资源细信息
			if (null == resourceBy || resourceBy.equals(null) || special.getImageIdFk().equals(resourceBy)) {
				images.setResourceBy(special.getSpecialId());
				images.setResourceByType("special");
				images.setImageTitle("《" + special.getSpecialTitle() + "》");
				images.setImageContent("需要自定义");
				images.setDescription("《" + special.getSpecialTitle() + "》文章的标题图片。");
				int i = imagesMapper.editImage(images);
				if (0 < i) {
					System.out.println(images.toString() + "资源更新成功！");
				}
			}
		}

		return flag;
	}

	// 修改
	@Override
	public int editSpecial(Special special) {
		// 获取历史数据
		Special temp = specialMapper.selectByPrimaryKey(special.getSpecialId());
		int flag = specialMapper.updateByPrimaryKeySelective(special);

		// 图片
		if (0 < flag && null != special.getImageIdFk()) {

			// 准备进行数据更新的Images对象
			Images images = imagesMapper.selectImageById(special.getImageIdFk());

			Long imageIdNew = special.getImageIdFk();
			Long imageIdOld = temp.getImageIdFk();

			// 如果图片没有变化，直接更新图片数据
			if (imageIdNew.equals(imageIdOld)) {
				images.setImageTitle("《" + special.getSpecialTitle() + "》");
				images.setDescription("《" + special.getSpecialTitle() + "》文章的标题图片。");
				imagesMapper.editImage(images);
			}
			// 如果标题图片更改，先获取原始图片数据，将历史内容清空。
			else {
				if (null != imageIdOld) {
					Images imagesOld = imagesMapper.selectImageById(imageIdOld);
					if (null != imagesOld) {
						imagesOld.setResourceBy(0L); // 资源所属
						imagesOld.setResourceByType("-"); // 资源类别
						imagesOld.setImageTitle("-");// 资源标题
						imagesOld.setImageContent("-");// 资源介绍
						imagesOld.setDescription("-");// 描述
						imagesMapper.editImage(imagesOld);
					}
				}
				// 然后更改新的图片数据
				images.setResourceBy(special.getSpecialId());
				images.setResourceByType("special");
				images.setImageTitle("《" + special.getSpecialTitle() + "》");
				images.setImageContent("需要自定义");
				images.setDescription("《" + special.getSpecialTitle() + "》文章的标题图片。");
				int i = imagesMapper.editImage(images);
			}
		}

		return flag;
	}

	// 根据ID查询
	@Override
	public Special querySpecialById(Long specialId) {
		return specialMapper.selectByPrimaryKey(specialId);
	}

	// 删除
	@Override
	public int deleteSpecial(long specialId) {
		return specialMapper.deleteByPrimaryKey(specialId);
	}

	// ========== ========== ========== ========== ========== //

	// API 首页专题推荐8个 根据类别返回列表
	@Override
	public JSONObject apiTopSpecialList(Map<String, Object> params) {
		JSONObject body = new JSONObject();
		JSONArray dataList = new JSONArray();
		if (params.containsKey("specialTypeId")) {
			List<Special> list = specialMapper.apiTopSpecialList(Long.valueOf(String.valueOf(params.get("specialTypeId"))));
			Long specialTypeIdFk = null;
			Long imageIdFk = null;
			for (Special special : list) {
				// clear
				specialTypeIdFk = null;
				imageIdFk = null;
				// set type
				specialTypeIdFk = special.getSpecialTypeIdFk();
				SpecialType specialType = specialTypeMapper.selectByPrimaryKey(specialTypeIdFk);
				special.setSpecialType(specialType);
				// set image
				imageIdFk = special.getImageIdFk();
				Images image = imagesMapper.queryImageById(imageIdFk);
				special.setImage(image);
				// add list
				String jsonString = JSONObject.toJSONString(special, SerializerFeature.DisableCircularReferenceDetect);
				Special temp = JSONObject.parseObject(jsonString, Special.class);
				dataList.add(temp);
			}
			// put json list
			body.put("dataList", dataList);
			return body;
		}
		return null;
	}

	// 专题页 展示列表 根据类别 全部 分页
	@Override
	public JSONObject querySpecialByType(Map<String, Object> params) {
		int pageNum = 1;
		if (params.containsKey("pageNum")) {
			pageNum = Integer.parseInt(String.valueOf(params.get("pageNum")));
		}
		Long specialTypeId = null;
		if (params.containsKey("specialTypeId")) {
			specialTypeId = Long.valueOf(String.valueOf(params.get("specialTypeId")));
		} else {
			return null;
		}

		PageHelper.startPage(pageNum, Constant.API_PAGE_SIZE);
		List<Special> list = specialMapper.selectSpecialsByParams(specialTypeId);

		Long specialTypeIdFk = null;
		Long imageIdFk = null;
		for (Special temp : list) {
			// clear
			specialTypeIdFk = null;
			imageIdFk = null;
			// set type
			specialTypeIdFk = temp.getSpecialTypeIdFk();
			SpecialType specialType = specialTypeMapper.selectByPrimaryKey(specialTypeIdFk);
			temp.setSpecialType(specialType);
			// set image
			imageIdFk = temp.getImageIdFk();
			Images image = imagesMapper.queryImageById(imageIdFk);
			temp.setImage(image);
		}
		PageInfo<Special> pageInfo = new PageInfo<Special>(list);
		//
		JSONObject parseObject = new JSONObject();
		parseObject.put("dataList", pageInfo);
		String jsonString = JSONObject.toJSONString(parseObject, SerializerFeature.DisableCircularReferenceDetect);
		JSONObject body = JSONObject.parseObject(jsonString);
		return body;
	}

	// 专题 单个查询 by ID
	@Override
	public JSONObject querySpecialById(Map<String, Object> params) {
		JSONObject body = new JSONObject();
		long specialId = Long.valueOf(params.get("specialId").toString());
		Special special = specialMapper.selectByPrimaryKey(specialId);
		// 热度+1
		special.setBrowseCount(special.getBrowseCount() + 1);
		specialMapper.updateByPrimaryKeySelective(special);
		// set type
		SpecialType specialType = specialTypeMapper.selectByPrimaryKey(special.getSpecialTypeIdFk());
		special.setSpecialType(specialType);
		// set image
		Images image = imagesMapper.queryImageById(special.getImageIdFk());
		special.setImage(image);
		// put body
		body.put("special", special);
		return body;
	}

	// API 模糊查询
	@Override
	public List<Special> querytSpecialByName(Map<String, Object> params) {
		String specialTitle = null;
		List<Special> list = null;

		if (0 == params.size()) {
			list = new ArrayList<Special>();
		} else {
			if (params.containsKey("keyword")) {
				specialTitle = params.get("keyword").toString();
				if (specialTitle.equals(null) || specialTitle.equals(""))
					specialTitle = null;
			}

			list = specialMapper.querytSpecialByName(specialTitle);

			Long specialTypeIdFk = null;
			Long imageIdFk = null;

			for (Special temp : list) {
				// clear
				specialTypeIdFk = null;
				imageIdFk = null;
				// set type
				specialTypeIdFk = temp.getSpecialTypeIdFk();
				SpecialType specialType = specialTypeMapper.selectByPrimaryKey(specialTypeIdFk);
				temp.setSpecialType(specialType);
				// set image
				imageIdFk = temp.getImageIdFk();
				Images image = imagesMapper.queryImageById(imageIdFk);
				temp.setImage(image);
			}
		}
		return list;
	}

}
