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
import cn.net.cncl.entity.Images;
import cn.net.cncl.entity.News;
import cn.net.cncl.mapper.ImagesMapper;
import cn.net.cncl.mapper.NewsMapper;
import cn.net.cncl.service.NewsService;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

	private static Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);

	@Autowired
	private NewsMapper newsMapper;

	@Autowired
	private ImagesMapper imagesMapper;

	/**
	 * 资讯 查询
	 */
	@Override
	public PageInfo<News> selectNews(Integer pageNum) {
		PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
		List<News> list = newsMapper.selectNews();
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
		news.setAdminUserIdFk(10000000000000001L);
		news.setCeateTime(new Date());
		news.setBrowseCount(0);
		int flag = newsMapper.insertNews(news);

		// 更新资源图片信息
		if (0 < flag && null != news.getImageIdFk()) {
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
		int flag = newsMapper.updateNews(news);
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
					images.setResourceBy(0l);
					images.setResourceByType("");
					imagesOld.setImageTitle("");
					imagesOld.setImageContent("");
					imagesOld.setDescription("");
					imagesMapper.editImage(imagesOld);
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
		for (News news : list) {
			Images image = imagesMapper.selectImageById(news.getImageIdFk());
			news.setImage(image);
			dataList.add(news);
		}
		body.put("dataList", dataList);
		return body;
	}

	/**
	 * 资讯列表
	 */
	@Override
	public JSONObject newsList(Map<String, Object> params) {
		JSONObject body = new JSONObject();
		int pageNum = 1;
		if (params.containsKey("pageNum")) {
			pageNum = Integer.parseInt(String.valueOf(params.get("pageNum")));
		}
		PageHelper.startPage(pageNum, Constant.API_PAGE_SIZE);
		List<News> list = newsMapper.queryNews(params);
		for (News news : list) {
			Images image = imagesMapper.selectImageById(news.getImageIdFk());
			news.setImage(image);
		}
		PageInfo<News> pageInfo = new PageInfo<News>(list);
		body.put("dataList", pageInfo);
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

		Images image = imagesMapper.selectImageById(news.getImageIdFk());
		news.setImage(image);

		body.put("news", news);
		return body;
	}

}
