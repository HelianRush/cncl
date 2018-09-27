package cn.net.cncl.common;

public enum Constant {

	// cncl.common.Constant

	SUCCESS("200", "成功"),

	DEFEAT("404", "成功"),

	NAME_IS_NULL("L0001", "名称不能为空"),

	PASSWORD_IS_NULL("L0002", "密码不能为空"),

	CAPTCHA_IS_NULL("L0003", "验证码不能为空"),

	NAME_ERROR("L0004", "名称错误或不存在"),

	PASSWORD_ERROR("L0005", "密码错误"),

	CAPTCHA_ERROR("L0006", "验证码错误"),

	LOGIN_DEFEATED("L0404", "登录失败"),

	SYSTEM_ERROR("SYSTEM_ERROR", "系统错误"),

	;

	private String code;
	private String message;

	private Constant() {
	}

	private Constant(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 每页显示条数
	 */
	public static final Integer PAGE_SIZE = 13;

	/**
	 * pageStatus
	 */
	public static final String PAGE_PLUS = "+page";
	public static final String PAGE_SUBTRACT = "-page";
	public static final String PAGE_NOW = "page";

}
