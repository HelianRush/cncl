package cn.net.cncl.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.net.cncl.entity.NewsType;

@Mapper
public interface NewsTypeMapper {
	int deleteByPrimaryKey(Long newsTypeId);

	int insert(NewsType record);

	int insertSelective(NewsType record);

	NewsType selectByPrimaryKey(Long newsTypeId);

	int updateByPrimaryKeySelective(NewsType record);

	int updateByPrimaryKey(NewsType record);
}