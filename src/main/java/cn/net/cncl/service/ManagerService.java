package cn.net.cncl.service;

import cn.net.cncl.entity.WebInfo;

public interface ManagerService {
	/**
	 * 查询 网站信息
	 */
	public String getWebInfo();

	/**
	 * 查询 网站信息
	 */
	public WebInfo getWebInfoObject();
}
