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
import cn.net.cncl.entity.Images;
import cn.net.cncl.mapper.CelebritysMapper;
import cn.net.cncl.mapper.ImagesMapper;
import cn.net.cncl.service.CelebritysService;

@Service
@Transactional
public class CelebritysServiceImpl implements CelebritysService {

	private static Logger logger = LoggerFactory.getLogger(CelebritysServiceImpl.class);

	@Autowired
	public CelebritysMapper celebritysMapper;

	@Autowired
	private ImagesMapper imagesMapper;

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
		celebritys.setBrowseCount(0);
		int flag = celebritysMapper.insertCelebritys(celebritys);

		// 更新资源图片信息
		if (0 < flag && null != celebritys.getImageIdFk()) {
			Images images = imagesMapper.selectImageById(celebritys.getImageIdFk());
			Long resourceBy = images.getResourceBy();
			// 如果资源图片没有资源所属，或者资源所属相同，才能更改图片资源细信息
			if (null == resourceBy || resourceBy.equals(null) || celebritys.getImageIdFk().equals(resourceBy)) {
				images.setResourceBy(celebritys.getCelebrityId());
				images.setResourceByType("celebrity");
				images.setImageTitle("《" + celebritys.getCelebrityName() + "》");
				images.setImageContent("需要自定义");
				images.setDescription("《" + celebritys.getCelebrityName() + "》文章的标题图片。");
				int i = imagesMapper.editImage(images);
				if (0 < i) {
					System.out.println(images.toString() + "资源更新成功！");
				}
			}
		}
		return flag;
	}

	/**
	 * 修改名人库
	 */
	@Override
	public int updateCelebritys(Celebritys celebritys) {

		// 获取历史数据
		Celebritys temp = celebritysMapper.selectCelebritysByID(celebritys.getCelebrityId());

		// 更新数据
		int flag = celebritysMapper.updateCelebritys(celebritys);
		if (0 < flag && null != celebritys.getImageIdFk()) {

			// 准备进行数据更新的Images对象
			Images images = imagesMapper.selectImageById(celebritys.getImageIdFk());

			Long imageIdNew = celebritys.getImageIdFk();
			Long imageIdOld = temp.getImageIdFk();

			// 如果图片没有变化，直接更新图片数据
			if (imageIdNew.equals(imageIdOld)) {
				images.setImageTitle("《" + celebritys.getCelebrityName() + "》");
				images.setDescription("《" + celebritys.getCelebrityName() + "》文章的标题图片。");
				imagesMapper.editImage(images);
			}
			// 如果标题图片更改，先获取原始图片数据，将历史内容清空。
			else {
				if (null != imageIdOld) {
					Images imagesOld = imagesMapper.selectImageById(imageIdOld);
					images.setResourceBy(0l);
					images.setResourceByType("");
					imagesOld.setImageTitle("");
					imagesOld.setImageContent("");
					imagesOld.setDescription("");
					imagesMapper.editImage(imagesOld);
				}
				// 然后更改新的图片数据
				images.setResourceBy(celebritys.getCelebrityId());
				images.setResourceByType("celebrity");
				images.setImageTitle("《" + celebritys.getCelebrityName() + "》");
				images.setImageContent("需要自定义");
				images.setDescription("《" + celebritys.getCelebrityName() + "》文章的标题图片。");
				imagesMapper.editImage(images);
			}
		}
		return flag;
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

		String celebrityName = null;
		if (params.containsKey("celebrityName")) {
			celebrityName = params.get("celebrityName").toString();
			if (celebrityName.equals(null) || celebrityName.equals(""))
				celebrityName = null;
		}

		String identityCard = null;
		if (params.containsKey("identityCard")) {
			identityCard = params.get("identityCard").toString();
			if (identityCard.equals(null) || identityCard.equals(""))
				identityCard = null;
		}

		String certificateCode = null;
		if (params.containsKey("certificateCode")) {
			certificateCode = params.get("certificateCode").toString();
			if (certificateCode.equals(null) || certificateCode.equals(""))
				certificateCode = null;
		}

		if (0 == params.size()) {
			list = new ArrayList<Celebritys>();
		} else {
			list = celebritysMapper.queryByClelbrity(params);
		}

		for (Celebritys celebrity : list) {

			Images image = imagesMapper.selectImageById(celebrity.getImageIdFk());
			celebrity.setImage(image);

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
				if (celebrityName.equals(null) || celebrityName.equals(""))
					celebrityName = null;
			}
			list = celebritysMapper.queryClelbrityByName(celebrityName);

			for (Celebritys celebrity : list) {
				Images image = imagesMapper.selectImageById(celebrity.getImageIdFk());
				celebrity.setImage(image);
			}
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
			Images image = imagesMapper.selectImageById(celebrity.getImageIdFk());
			celebrity.setImage(image);
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
				if (celebrityName.equals(null) || celebrityName.equals(""))
					celebrityName = null;
			}
			list = celebritysMapper.queryClelbrityByName(celebrityName);
		}

		for (Celebritys celebrity : list) {
			Images image = imagesMapper.selectImageById(celebrity.getImageIdFk());
			celebrity.setImage(image);
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

		int pageNum = 1;
		if (params.containsKey("pageNum")) {
			pageNum = Integer.parseInt(String.valueOf(params.get("pageNum")));
		}

		PageHelper.startPage(pageNum, Constant.API_PAGE_SIZE);
		List<Celebritys> list = celebritysMapper.queryCelebritys();
		for (Celebritys celebrity : list) {
			Images image = imagesMapper.selectImageById(celebrity.getImageIdFk());
			celebrity.setImage(image);
		}

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
		Images image = imagesMapper.selectImageById(celebrity.getImageIdFk());
		celebrity.setImage(image);
		body.put("celebrity", celebrity);
		return body;
	}

};