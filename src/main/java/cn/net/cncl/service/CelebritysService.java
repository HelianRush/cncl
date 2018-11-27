package cn.net.cncl.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.net.cncl.entity.Celebritys;

public interface CelebritysService {

	/**
	 * 添加名人库
	 * 
	 * @param Celebritys
	 */
	public int insertCelebritys(Celebritys celebritys);

	/**
	 * 修改名人库
	 * 
	 * @param Celebritys
	 */
	public int updateCelebritys(Celebritys celebritys);

	/**
	 * 名人库列表
	 */
	public PageInfo<Celebritys> showCelebritysList(int pageNum);

	/**
	 * 删除
	 */
	public int deleteCelebritys(Long id);

	/**
	 * 根据ID查询
	 */
	public Celebritys queryCelebritysByID(Long id);

	/********************************************************************************
	 *********************************** API 接口 ***********************************
	 ********************************************************************************/

	/**
	 * 根据姓名、身份证、证书编号 精确查询
	 */
	public JSONObject queryByClelbrity(Map<String, Object> params);

	/**
	 * 根据标题 模糊查询
	 */
	public List<Celebritys> queryClelbrityByName(Map<String, Object> params);

	/**
	 * 名人库推荐列表
	 */
	public JSONObject getTopCelebrity();

	/**
	 * 名人库查询
	 */
	public JSONObject queryClelbrityByNames(Map<String, Object> params);

	/**
	 * 名人库列表
	 */
	public JSONObject celebrityList(Map<String, Object> params);

	/**
	 * 单条名人库
	 */
	public JSONObject apiQueryCelebritysByID(Map<String, Object> params);
}
