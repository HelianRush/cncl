package cn.net.cncl.api.entity;

import java.util.Map;

public class RequestData {

	/**
	 * RequestData<br>
	 * -- Head 请求头 <br>
	 * -- Query 查询条件 <br>
	 */

	private Head head;
	private Map<String, Object> query;

	/*
	 * Getter Setter
	 */
	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public Map<String, Object> getQuery() {
		return query;
	}

	public void setQuery(Map<String, Object> query) {
		this.query = query;
	}

}
