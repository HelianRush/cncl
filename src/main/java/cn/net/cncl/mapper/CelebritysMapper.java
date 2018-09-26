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

}