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

	/* 图片对象 */
	private Images image;

	/* 资讯类别 */
	private NewsType newsType;

	/* 作者信息 */
	private AdminUser user;

	private String videoCode;

	/* ************** */
	/* Getter Setter */
	/* ************** */

	public NewsType getNewsType() {
		return newsType;
	}

	public String getVideoCode() {
		return videoCode;
	}

	public void setVideoCode(String videoCode) {
		this.videoCode = videoCode;
	}

	public AdminUser getUser() {
		return user;
	}

	public void setUser(AdminUser user) {
		this.user = user;
	}

	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}

	public Images getImage() {
		return image;
	}

	public void setImage(Images image) {
		this.image = image;
	}

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
		return "News [newsId=" + newsId + ", newsTypeFk=" + newsTypeFk + ", newsTitel=" + newsTitel + ", videoIdFk=" + videoIdFk + ", imageIdFk=" + imageIdFk + ", adminUserIdFk=" + adminUserIdFk + ", ceateTime=" + ceateTime + ", browseCount=" + browseCount + ", newsOutline=" + newsOutline + ", newsContent=" + newsContent + ", image=" + image + ", newsType=" + newsType + ", user=" + user + ", videoCode=" + videoCode + "]";
	}

}