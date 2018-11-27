package cn.net.cncl.api.entity;

public class Head {

	/**
	 * Head<br>
	 * -- apiCode 接口编码<br>
	 * -- code 状态码<br>
	 * -- msg 消息<br>
	 */
	private String apiCode;
	private String code;
	private String msg;

	/*
	 * Getter Setter
	 */
	public String getApiCode() {
		return apiCode;
	}

	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Head [apiCode=" + apiCode + ", code=" + code + ", msg=" + msg + "]";
	}

}
