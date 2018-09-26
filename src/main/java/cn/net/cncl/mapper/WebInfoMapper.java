package cn.net.cncl.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.net.cncl.entity.WebInfo;

@Mapper
public interface WebInfoMapper {
	int deleteByPrimaryKey(Integer webId);

	int insert(WebInfo record);

	int insertSelective(WebInfo record);

	WebInfo selectByPrimaryKey(Integer webId);

	int updateByPrimaryKeySelective(WebInfo record);

	int updateByPrimaryKey(WebInfo record);

	/**
	 * 查询 网站信息
	 */
	Map<String, Object> getWebInfo();

	/**
	 * 查询 网站信息
	 */
	WebInfo getWebInfoObject();
}