package cn.net.cncl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.net.cncl.entity.NewsType;

@Mapper
public interface NewsTypeMapper {

	/**
	 * 资讯类别 查询
	 */
	List<NewsType> selectNews();

	/**
	 * 资讯类别 新增
	 */
	int insert(NewsType record);

	/**
	 * .资讯类别 更新
	 */
	int updateNewsType(NewsType newsType);

	/**
	 * 资讯类别 删除
	 */
	int deleteByPrimaryKey(Long newsTypeId);

	/**
	 * Orders
	 */

	int insertSelective(NewsType record);

	/**
	 * 根据ID查询
	 */
	NewsType selectByPrimaryKey(Long newsTypeId);

	int updateByPrimaryKey(NewsType record);

	/**
	 * 查询编码
	 */
	int selectByCode(String newsTypeCode);

	/**
	 * 查询名称
	 */
	int selectByName(String newsTypeName);

}