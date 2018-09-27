package cn.net.cncl.entity;

public class NewsType {
	private Long newsTypeId;

	private String newsTypeCode;

	private String newsTypeName;

	public Long getNewsTypeId() {
		return newsTypeId;
	}

	public void setNewsTypeId(Long newsTypeId) {
		this.newsTypeId = newsTypeId;
	}

	public String getNewsTypeCode() {
		return newsTypeCode;
	}

	public void setNewsTypeCode(String newsTypeCode) {
		this.newsTypeCode = newsTypeCode == null ? null : newsTypeCode.trim();
	}

	public String getNewsTypeName() {
		return newsTypeName;
	}

	public void setNewsTypeName(String newsTypeName) {
		this.newsTypeName = newsTypeName == null ? null : newsTypeName.trim();
	}
}