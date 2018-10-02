package cn.net.cncl.service;

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

}
