package cn.net.cncl.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

	/********************************************************************************
	 *********************************** API 接口 ***********************************
	 ********************************************************************************/

	/**
	 * 根据姓名、身份证、证书编号 精确查询
	 */
	List<Celebritys> queryByClelbrity(Map<String, Object> params);

	/**
	 * 根据姓名 模糊查询
	 */
	List<Celebritys> queryClelbrityByName(@Param("celebrityName") String celebrityName);

	/**
	 * 名人库推荐列表
	 */
	List<Celebritys> getTopCelebrity();

}