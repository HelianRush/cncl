package cn.net.cncl.api.entity;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class CnclData {
	/**
	 * CnclData<br>
	 * -- Head 请求头 <br>
	 * -- Query 查询条件 <br>
	 * -- body 返回数据<>
	 */
	private Head head;
	private Map<String, Object> query;
	private JSONObject body;

	/* setter getter */
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

	public JSONObject getBody() {
		return body;
	}

	public void setBody(JSONObject body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "CnclData [head=" + head + ", query=" + query + ", body=" + body + "]";
	}

}
