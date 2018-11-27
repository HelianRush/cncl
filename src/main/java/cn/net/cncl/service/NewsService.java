package cn.net.cncl.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.net.cncl.entity.News;

public interface NewsService {

	/**
	 * 资讯 查询
	 */
	PageInfo<News> selectNews(Integer pageNum);

	/**
	 * 资讯编辑 新增
	 */
	int addNews(News news);

	/**
	 * 资讯编辑 修改
	 */
	int editNews(News news);

	/**
	 * 资讯编辑 删除
	 */
	int deleteNews(Long newsId);

	/**
	 * 根据ID查询
	 */
	public News queryNewsByID(Long newsId);

	/********************************************************************************
	 *********************************** API 接口 ***********************************
	 ********************************************************************************/

	/**
	 * 根据标题 模糊查询
	 */
	public List<News> queryNewsByName(Map<String, Object> params);

	/**
	 * 获取推荐资讯列表
	 */
	JSONObject getTopNews(Map<String, Object> params);

	/**
	 * 资讯列表
	 */
	JSONObject newsList(Map<String, Object> params);

	/**
	 * 单条资讯
	 */
	JSONObject apiQueryNewsById(Map<String, Object> params);

}
