package cn.net.cncl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.net.cncl.entity.SpecialType;

@Mapper
public interface SpecialTypeMapper {

	int deleteByPrimaryKey(Long specialTypeId);

	int insert(SpecialType record);

	int insertSelective(SpecialType record);

	SpecialType selectByPrimaryKey(Long specialTypeId);

	int updateByPrimaryKeySelective(SpecialType record);

	int updateByPrimaryKey(SpecialType record);

	List<SpecialType> selectSpecialType();

}