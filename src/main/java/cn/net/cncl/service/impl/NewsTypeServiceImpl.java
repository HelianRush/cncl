package cn.net.cncl.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.net.cncl.common.Constant;
import cn.net.cncl.entity.NewsType;
import cn.net.cncl.mapper.NewsTypeMapper;
import cn.net.cncl.service.NewsTypeService;

@Service
@Transactional
public class NewsTypeServiceImpl implements NewsTypeService {

	private static Logger logger = LoggerFactory.getLogger(NewsTypeServiceImpl.class);

	@Autowired
	private NewsTypeMapper newsTypeMapper;

	/**
	 * 资讯 查询
	 */
	@Override
	public PageInfo<NewsType> selectNewsType(Integer pageNum) {
		// 分页
		PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
		List<NewsType> list = newsTypeMapper.selectNews();
		PageInfo<NewsType> pageInfo = new PageInfo<NewsType>(list);
		return pageInfo;
	}

	/**
	 * 资讯类别新增&编辑
	 */
	@Override
	public int editNewsType(NewsType newsType) {
		int flag = 0;
		if (null == newsType.getNewsTypeId() || 0 == newsType.getNewsTypeId()) {
			newsType.setNewsTypeId(new Date().getTime());

			flag = newsTypeMapper.insert(newsType);
		} else {
			flag = newsTypeMapper.updateNewsType(newsType);
		}
		return flag;
	}

	/**
	 * 资讯类别 删除
	 */
	@Override
	public int deleteNewsType(Long newsTypeId) {
		return newsTypeMapper.deleteByPrimaryKey(newsTypeId);
	}

	/**
	 * 查询编码
	 */
	public int selectByCode(String newsTypeCode) {
		return newsTypeMapper.selectByCode(newsTypeCode);
	}

	/**
	 * 查询名称
	 */
	public int selectByName(String newsTypeName) {
		return newsTypeMapper.selectByName(newsTypeName);
	}

	/**
	 * 查询全部
	 */
	@Override
	public List<NewsType> queryNewsTypeAll() {
		return newsTypeMapper.selectNews();
	}

}
