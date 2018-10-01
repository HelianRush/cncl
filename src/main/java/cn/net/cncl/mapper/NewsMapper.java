package cn.net.cncl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.net.cncl.entity.News;

@Mapper
public interface NewsMapper {
	/**
	 * 资讯 查询
	 */
	List<News> selectNews();

	/**
	 * 资讯编辑 新增
	 */
	int insertNews(News news);

	/**
	 * 资讯编辑 修改
	 */
	int updateNews(News news);
}