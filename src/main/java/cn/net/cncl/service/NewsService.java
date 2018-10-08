package cn.net.cncl.service;

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
	News queryNewsByID(Long newsId);

}
