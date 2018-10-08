package cn.net.cncl.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.net.cncl.entity.NewsType;

public interface NewsTypeService {

	/**
	 * 资讯类别查询
	 */
	PageInfo<NewsType> selectNewsType(Integer pageNum);

	/**
	 * 资讯类别新增&编辑
	 */
	int editNewsType(NewsType newsType);

	/**
	 * 资讯类别 删除
	 */
	int deleteNewsType(Long newsTypeId);

	/**
	 * 查询编码
	 */
	public int selectByCode(String newsTypeCode);

	/**
	 * 查询名称
	 */
	public int selectByName(String newsTypeName);

	/**
	 * 查询全部
	 */
	List<NewsType> queryNewsTypeAll();

	/**
	 * 编辑显示
	 */
	NewsType queryById(Long id);

}
