package cn.net.cncl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import cn.net.cncl.entity.AdminUser;
import cn.net.cncl.entity.Images;
import cn.net.cncl.entity.News;
import cn.net.cncl.entity.NewsType;
import cn.net.cncl.mapper.AdminUserMapper;
import cn.net.cncl.mapper.ImagesMapper;
import cn.net.cncl.mapper.NewsMapper;
import cn.net.cncl.mapper.NewsTypeMapper;
import cn.net.cncl.service.NewsService;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

	private static Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);

	@Autowired
	private NewsMapper newsMapper;

	@Autowired
	private ImagesMapper imagesMapper;

	@Autowired
	private NewsTypeMapper newsTypeMapper;

	@Autowired
	private AdminUserMapper adminUserMapper;

	/**
	 * 资讯 查询
	 */
	@Override
	public PageInfo<News> selectNews(Integer pageNum) {
		PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
		List<News> list = newsMapper.selectNews();
		for (News temp : list) {
			AdminUser user = adminUserMapper.selectByPrimaryKey(temp.getAdminUserIdFk());
			temp.setUser(user);
			NewsType newsType = newsTypeMapper.selectByPrimaryKey(temp.getNewsTypeFk());
			temp.setNewsType(newsType);
			Images image = imagesMapper.selectImageById(temp.getImageIdFk());
			temp.setImage(image);
		}
		PageInfo<News> pageInfo = new PageInfo<News>(list);
		return pageInfo;
	}

	/**
	 * 资讯编辑 新增
	 */
	@Override
	public int addNews(News news) {
		news.setNewsId(new Date().getTime());
		// getUser
		// news.setAdminUserIdFk(10000000000000001L);
		news.setCeateTime(new Date());
		news.setBrowseCount(0);
		news.setImageIdFk(100001l);

		String newsOutline = news.getNewsOutline();
		if (StringUtils.isBlank(newsOutline)) {
			news.setNewsOutline(news.getNewsTitel() + "... ...");
		}

		int flag = newsMapper.insertNews(news);

		// 更新资源图片信息
		if (0 < flag && null != news.getImageIdFk() && 100001l != news.getImageIdFk()) {
			Images images = imagesMapper.selectImageById(news.getImageIdFk());
			Long resourceBy = images.getResourceBy();
			// 如果资源图片没有资源所属，或者资源所属相同，才能更改图片资源细信息
			if (null == resourceBy || resourceBy.equals(null) || news.getImageIdFk().equals(resourceBy)) {
				images.setResourceBy(news.getNewsId());
				images.setResourceByType("news");
				images.setImageTitle("《" + news.getNewsTitel() + "》");
				images.setImageContent("需要自定义");
				images.setDescription("《" + news.getNewsTitel() + "》文章的标题图片。");
				int i = imagesMapper.editImage(images);
				if (0 < i) {
					System.out.println(images.toString() + "资源更新成功！");
				}
			}
		}
		return flag;
	}

	/**
	 * 资讯编辑 修改
	 */
	@Override
	public int editNews(News news) {

		// 获取历史数据
		News temp = newsMapper.selectNewsByID(news.getNewsId());

		// 更新数据
		String newsOutline = news.getNewsOutline();
		if (StringUtils.isBlank(newsOutline)) {
			news.setNewsOutline(news.getNewsTitel() + "... ...");
		}

		int flag = newsMapper.updateNews(news);

		// 图片
		if (0 < flag && null != news.getImageIdFk()) {

			// 准备进行数据更新的Images对象
			Images images = imagesMapper.selectImageById(news.getImageIdFk());

			Long imageIdNew = news.getImageIdFk();
			Long imageIdOld = temp.getImageIdFk();

			// 如果图片没有变化，直接更新图片数据
			if (imageIdNew.equals(imageIdOld)) {
				images.setImageTitle("《" + news.getNewsTitel() + "》");
				images.setDescription("《" + news.getNewsTitel() + "》文章的标题图片。");
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
				images.setResourceBy(news.getNewsId());
				images.setResourceByType("news");
				images.setImageTitle("《" + news.getNewsTitel() + "》");
				images.setImageContent("需要自定义");
				images.setDescription("《" + news.getNewsTitel() + "》文章的标题图片。");
				imagesMapper.editImage(images);
			}
		}
		return flag;
	}

	/**
	 * 资讯编辑 删除
	 */
	@Override
	public int deleteNews(Long newsId) {
		return newsMapper.deleteByPrimaryKey(newsId);
	}

	/**
	 * 根据ID查询
	 */
	@Override
	public News queryNewsByID(Long newsId) {
		return newsMapper.selectNewsByID(newsId);
	}

	/********************************************************************************
	 *********************************** API 接口 ***********************************
	 ********************************************************************************/

	/**
	 * 根据标题 模糊查询
	 */
	@Override
	public List<News> queryNewsByName(Map<String, Object> params) {
		String newsTitel = null;
		List<News> list = null;
		if (0 == params.size()) {
			list = new ArrayList<News>();
		} else {
			if (params.containsKey("keyword")) {
				newsTitel = params.get("keyword").toString();
				if (newsTitel.equals(null) || newsTitel.equals(""))
					newsTitel = null;
			}

			list = newsMapper.queryNewsByName(newsTitel);

			for (News news : list) {
				// AdminUser user = adminUserMapper.selectByPrimaryKey(news.getAdminUserIdFk());
				// news.setUser(user);

				NewsType newsType = newsTypeMapper.selectByPrimaryKey(news.getNewsTypeFk());
				news.setNewsType(newsType);

				Images image = imagesMapper.selectImageById(news.getImageIdFk());
				news.setImage(image);
			}
		}
		return list;
	}

	/**
	 * 获取推荐资讯列表
	 */
	@Override
	public JSONObject getTopNews(Map<String, Object> params) {
		JSONObject body = new JSONObject();
		JSONArray dataList = new JSONArray();
		List<News> list = newsMapper.getTopNews();
		NewsType newsType = null;

		List<NewsType> newsTypeList = newsTypeMapper.selectNews();
		Map<Long, String> newsTypeMap = new HashMap<Long, String>();
		for (NewsType temp : newsTypeList) {
			newsTypeMap.put(temp.getNewsTypeId(), temp.getNewsTypeName());
		}
		for (News news : list) {
			NewsType type = new NewsType();
			type.setNewsTypeId(news.getNewsTypeFk());
			type.setNewsTypeName(newsTypeMap.get(news.getNewsTypeFk()));
			news.setNewsType(type);
			Images image = imagesMapper.selectImageById(news.getImageIdFk());
			news.setImage(image);
			// add list
			String jsonString = JSONObject.toJSONString(news, SerializerFeature.DisableCircularReferenceDetect);
			JSONObject parseObject = JSONObject.parseObject(jsonString);
			dataList.add(parseObject);
		}
		body.put("dataList", dataList);
		return body;
	}

	/**
	 * 资讯列表
	 */
	@Override
	public JSONObject newsList(Map<String, Object> params) {
		int pageNum = 1;
		if (params.containsKey("pageNum")) {
			pageNum = Integer.parseInt(String.valueOf(params.get("pageNum")));
		}
		PageHelper.startPage(pageNum, Constant.API_PAGE_SIZE);
		List<News> list = newsMapper.queryNews(params);
		for (News news : list) {
			// AdminUser user = adminUserMapper.selectByPrimaryKey(news.getAdminUserIdFk());
			// news.setUser(user);
			NewsType newsType = newsTypeMapper.selectByPrimaryKey(news.getNewsTypeFk());
			news.setNewsType(newsType);
			Images image = imagesMapper.selectImageById(news.getImageIdFk());
			news.setImage(image);
		}
		PageInfo<News> pageInfo = new PageInfo<News>(list);
		//
		JSONObject parseObject = new JSONObject();
		parseObject.put("dataList", pageInfo);
		String jsonString = JSONObject.toJSONString(parseObject, SerializerFeature.DisableCircularReferenceDetect);
		JSONObject body = JSONObject.parseObject(jsonString);
		return body;
	}

	/**
	 * 单条资讯
	 */
	@Override
	public JSONObject apiQueryNewsById(Map<String, Object> params) {
		JSONObject body = new JSONObject();
		long newsId = Long.valueOf(params.get("newsId").toString());
		News news = newsMapper.selectNewsByID(newsId);

		// 热度+1
		news.setBrowseCount(news.getBrowseCount() + 1);
		newsMapper.updateNews(news);

		// 填装作者
		// AdminUser user = adminUserMapper.selectByPrimaryKey(news.getAdminUserIdFk());
		// news.setUser(user);

		// 填装类型
		NewsType newsType = newsTypeMapper.selectByPrimaryKey(news.getNewsTypeFk());
		news.setNewsType(newsType);

		// 填装图片
		Images image = imagesMapper.selectImageById(news.getImageIdFk());
		news.setImage(image);

		body.put("news", news);
		return body;
	}

}
