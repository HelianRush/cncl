package cn.net.cncl.service;

import com.alibaba.fastjson.JSONObject;

import cn.net.cncl.entity.WebInfo;

public interface ManagerService {
	/**
	 * 查询 网站信息
	 */
	public JSONObject getWebInfo();

	/**
	 * 查询 网站信息
	 */
	public WebInfo getWebInfoObject();

	/**
	 * 编辑 网站信息
	 */
	public int updateWebInfo(WebInfo webInfo);

}
