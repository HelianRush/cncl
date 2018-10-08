package cn.net.cncl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.net.cncl.entity.Celebritys;

@Mapper
public interface CelebritysMapper {
	/**
	 * 添加名人库
	 */
	int insertCelebritys(Celebritys celebritys);

	/**
	 * 查询名人库
	 */
	List<Celebritys> queryCelebritys();

	/**
	 * 修改
	 */
	int updateCelebritys(Celebritys celebritys);

	/**
	 * 删除
	 */
	int deleteByPrimaryKey(Long celebrityId);

	/**
	 * 根据ID查询
	 */
	Celebritys selectCelebritysByID(Long celebrityId);

}