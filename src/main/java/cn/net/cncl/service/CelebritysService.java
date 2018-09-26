package cn.net.cncl.service;

import java.util.List;

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
	public List<Celebritys> showCelebritysList();

}
