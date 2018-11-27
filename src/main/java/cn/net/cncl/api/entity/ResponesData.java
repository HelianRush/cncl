package cn.net.cncl.api.entity;

public class ResponesData {
	/**
	 * ResponesData<br>
	 * -- Head 请求头 <br>
	 * -- Body 结果集
	 */

	private Head head;
	private Object body;

	/*
	 * Getter Setter
	 */
	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

}
