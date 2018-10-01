package cn.net.cncl.service;

import com.github.pagehelper.PageInfo;

import cn.net.cncl.entity.News;

public interface NewsService {

	/**
	 * 资讯 查询
	 */
	PageInfo<News> selectNews(Integer pageNum);

	/**
	 * 资讯编辑
	 */
}
