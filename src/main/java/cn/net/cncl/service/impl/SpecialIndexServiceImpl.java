package cn.net.cncl.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import cn.net.cncl.entity.Images;
import cn.net.cncl.entity.SpecialIndex;
import cn.net.cncl.mapper.ImagesMapper;
import cn.net.cncl.mapper.SpecialIndexMapper;

@Service
@Transactional
public class SpecialIndexServiceImpl implements cn.net.cncl.service.SpecialIndexService {

	private static Logger logger = LoggerFactory.getLogger(SpecialIndexServiceImpl.class);

	@Autowired
	private SpecialIndexMapper specialIndexMapper;

	@Autowired
	private ImagesMapper imagesMapper;

	// main
	public static void main(String[] args) {
	}

	/**
	 * 获取 专题主页 内容
	 */
	@Override
	public SpecialIndex getSpecialIndexObj() {
		return specialIndexMapper.getSpecialIndexObj();
	}

	/**
	 * 新增
	 */
	@Override
	public int insetSpecialIndex(SpecialIndex specialIndex) {
		// specialIndex.setImageIdFk(10000000000000001L);
		return specialIndexMapper.insetSpecialIndex(specialIndex);
	}

	/**
	 * 更新
	 */
	@Override
	public int updateSpecialIndex(SpecialIndex specialIndex) {

		// 获取 OLD SpecialIndex
		SpecialIndex oldObject = specialIndexMapper.getSpecialIndexObj();

		Images image = imagesMapper.queryImageById(oldObject.getImageIdFk());
		image.setResourceByType("special");
		image.setResourceBy(0L);
		image.setImageTitle("-");
		image.setImageContent("-");
		image.setDescription("-");
		imagesMapper.editImage(image);

		return specialIndexMapper.updateSpecialIndex(specialIndex);
	}

	// ========== ========== ========== ========== ========== //

	// 专题页 首页
	@Override
	public JSONObject querySpecialIndex() {
		JSONObject body = new JSONObject();
		SpecialIndex specialIndex = specialIndexMapper.getSpecialIndexObj();
		Images image = imagesMapper.selectImageById(specialIndex.getImageIdFk());
		specialIndex.setImage(image);
		body.put("specialIndex", specialIndex);
		return body;
	}

}
