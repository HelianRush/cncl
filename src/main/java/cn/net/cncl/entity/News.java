package cn.net.cncl.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class News {
	private Long newsId;

	private Long newsTypeFk;

	private String newsTitel;

	private Long videoIdFk;

	private Long imageIdFk;

	private Long adminUserIdFk;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ceateTime;

	private Integer browseCount;

	private String newsOutline;

	private String newsContent;

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public Long getNewsTypeFk() {
		return newsTypeFk;
	}

	public void setNewsTypeFk(Long newsTypeFk) {
		this.newsTypeFk = newsTypeFk;
	}

	public String getNewsTitel() {
		return newsTitel;
	}

	public void setNewsTitel(String newsTitel) {
		this.newsTitel = newsTitel == null ? null : newsTitel.trim();
	}

	public Long getVideoIdFk() {
		return videoIdFk;
	}

	public void setVideoIdFk(Long videoIdFk) {
		this.videoIdFk = videoIdFk;
	}

	public Long getImageIdFk() {
		return imageIdFk;
	}

	public void setImageIdFk(Long imageIdFk) {
		this.imageIdFk = imageIdFk;
	}

	public Long getAdminUserIdFk() {
		return adminUserIdFk;
	}

	public void setAdminUserIdFk(Long adminUserIdFk) {
		this.adminUserIdFk = adminUserIdFk;
	}

	public Date getCeateTime() {
		return ceateTime;
	}

	public void setCeateTime(Date ceateTime) {
		this.ceateTime = ceateTime;
	}

	public Integer getBrowseCount() {
		return browseCount;
	}

	public void setBrowseCount(Integer browseCount) {
		this.browseCount = browseCount;
	}

	public String getNewsOutline() {
		return newsOutline;
	}

	public void setNewsOutline(String newsOutline) {
		this.newsOutline = newsOutline;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	@Override
	public String toString() {
		return "News [newsId=" + newsId + ", newsTypeFk=" + newsTypeFk + ", newsTitel=" + newsTitel + ", videoIdFk=" + videoIdFk + ", imageIdFk=" + imageIdFk + ", adminUserIdFk=" + adminUserIdFk + ", ceateTime=" + ceateTime + ", browseCount=" + browseCount + ", newsOutline=" + newsOutline + ", newsContent=" + newsContent + "]";
	}

}