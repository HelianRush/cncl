package cn.net.cncl.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.net.cncl.entity.SpecialIndex;

@Mapper
public interface SpecialIndexMapper {

	SpecialIndex getSpecialIndexObj();

	int insetSpecialIndex(SpecialIndex specialIndex);

	int updateSpecialIndex(SpecialIndex specialIndex);

	// int deleteByPrimaryKey(Long specialIndexId);
	// int insert(SpecialIndexWithBLOBs record);
	// int insertSelective(SpecialIndexWithBLOBs record);
	// SpecialIndexWithBLOBs selectByPrimaryKey(Long specialIndexId);
	// int updateByPrimaryKeySelective(SpecialIndexWithBLOBs record);
	// int updateByPrimaryKeyWithBLOBs(SpecialIndexWithBLOBs record);
	// int updateByPrimaryKey(SpecialIndex record);
}