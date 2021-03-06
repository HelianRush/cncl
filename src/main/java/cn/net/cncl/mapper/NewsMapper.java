package cn.net.cncl.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

	/**
	 * 资讯编辑 修改
	 */
	int deleteByPrimaryKey(Long newsId);

	/**
	 * 根据ID查询
	 */
	News selectNewsByID(Long newsId);

	/********************************************************************************
	 *********************************** API 接口 ***********************************
	 ********************************************************************************/

	/**
	 * 根据标题 模糊查询
	 */
	List<News> queryNewsByName(@Param("newsTitel") String newsTitel);

	/**
	 * 获取推荐资讯列表
	 */
	List<News> getTopNews();

	/**
	 * 资讯列表
	 */
	List<News> queryNews(Map<String, Object> params);
}