package cn.net.cncl.common;

public enum Constant {

	// cncl.common.Constant

	SUCCESS("200", "成功"),

	ERROR("404", "错误"),

	IS_HAVE("300", "已存在"),

	NAME_IS_NULL("L0001", "名称不能为空"),

	PASSWORD_IS_NULL("L0002", "密码不能为空"),

	CAPTCHA_IS_NULL("L0003", "验证码不能为空"),

	NAME_ERROR("L0004", "名称错误或不存在"),

	PASSWORD_ERROR("L0005", "密码错误"),

	CAPTCHA_ERROR("L0006", "验证码错误"),

	LOGIN_DEFEATED("L0404", "登录失败"),

	ADMIN_USER_NAME_IS_HAVE("A0001", "已存在"), // 用户名已存在

	SYSTEM_ERROR("SYSTEM_ERROR", "系统错误")

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
	public static final Integer PAGE_SIZE = 15;
	public static final int API_PAGE_SIZE = 20;

	/**
	 * pageStatus
	 */
	public static final String PAGE_PLUS = "+page";
	public static final String PAGE_SUBTRACT = "-page";
	public static final String PAGE_NOW = "page";
	public static final String ADMIN_USER_ID_KEY = "fc1b41257caae21b";
	public static final String ADMIN_USER_KEY = "c744bd39b5126deb";

	/**
	 * 图片类型
	 */
	public static final String IMAGE_PNG = "image/png";
	public static final String IMAGE_BMP = "image/bmp";
	public static final String IMAGE_GIF = "image/gif";
	public static final String IMAGE_JPG_JPEG = "image/jpeg";

	/**
	 * 图片路径
	 */
	public static final String STATIC_PATH = "/static/";
	public static final String STATIC_FILE_PATH = "/imageFileUpload/";
	public static final String STATIC_FILE_PATH2 = "/UMEupload/";
	public static final String STATIC_FILE_PATH3 = "/images/";

	/**
	 * 接口名称
	 */
	// 网站基本信息
	public static final String API_WEB_INFO = "webInfo";
	// 名人库精确搜索
	public static final String API_QUERY_BY_CLELBRITY = "queryByCelebrity";
	// 站内搜索
	public static final String API_QUERY_ALL = "queryAll";
	// 滚动图片展示
	public static final String API_TOP_IMAGES = "topImages";
	// 资讯菜单
	public static final String API_NEWS_TYPE_LIST = "newsTypeList";
	// 资讯推荐列表
	public static final String API_TOP_NEWS = "topNews";
	// 名人库推荐列表
	public static final String API_TOP_CELEBRITY = "topCelebrity";
	// 名人库查询
	public static final String API_QUERY_CELEBRITY_BY_NAME = "queryCelebrityByName";
	// 名人库列表
	public static final String API_CELEBRITY_LIST = "celebrityList";
	// 单条名人库
	public static final String API_QUERY_CELEBRITY_BY_ID = "queryCelebrityById";
	// 资讯列表
	public static final String API_NEWS_LIST = "newsList";
	// 单条资讯
	public static final String API_QUERY_NEWS_BY_ID = "queryNewsByID";
	// 首页专题推荐8个
	public static final String API_TOP_SPECIAL_LIST = "topSpecial";
	// 专题页 首页
	public static final String API_SPECIAL_INDEX = "querySpecialIndex";
	// 专题页 展示类别
	public static final String API__SPECIAL_TYPE = "querytSpecialType";
	// 专题页 展示列表 分页
	public static final String API__SPECIAL_LIST_BY_TYPE = "querySpecialByType";
	// 专题 单个查询
	public static final String API_QUERY_SPECIAL_BY_ID = "querySpecialById";
	// 入驻页
	public static final String API_QUERY_COOPERATION = "queryCooperation";

}
