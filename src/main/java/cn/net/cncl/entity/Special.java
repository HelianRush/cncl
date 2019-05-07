package cn.net.cncl.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Special {

	// Beans

	private Long specialId;

	private String specialTitle;

	private Long imageIdFk;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;

	private Integer browseCount;

	private Long specialTypeIdFk;

	private String specialContent;

	private String videoCode;

	private Images image;

	private SpecialType specialType;

	// 概述
	private String specialOutline;

	// Getter & Setter

	public String getSpecialOutline() {
		return specialOutline;
	}

	public void setSpecialOutline(String specialOutline) {
		this.specialOutline = specialOutline;
	}

	public SpecialType getSpecialType() {
		return specialType;
	}

	public Images getImage() {
		return image;
	}

	public void setImage(Images image) {
		this.image = image;
	}

	public void setSpecialType(SpecialType specialType) {
		this.specialType = specialType;
	}

	public String getSpecialContent() {
		return specialContent;
	}

	public void setSpecialContent(String specialContent) {
		this.specialContent = specialContent == null ? null : specialContent.trim();
	}

	public String getVideoCode() {
		return videoCode;
	}

	public void setVideoCode(String videoCode) {
		this.videoCode = videoCode == null ? null : videoCode.trim();
	}

	public Long getSpecialId() {
		return specialId;
	}

	public void setSpecialId(Long specialId) {
		this.specialId = specialId;
	}

	public String getSpecialTitle() {
		return specialTitle;
	}

	public void setSpecialTitle(String specialTitle) {
		this.specialTitle = specialTitle == null ? null : specialTitle.trim();
	}

	public Long getImageIdFk() {
		return imageIdFk;
	}

	public void setImageIdFk(Long imageIdFk) {
		this.imageIdFk = imageIdFk;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getBrowseCount() {
		return browseCount;
	}

	public void setBrowseCount(Integer browseCount) {
		this.browseCount = browseCount;
	}

	public Long getSpecialTypeIdFk() {
		return specialTypeIdFk;
	}

	public void setSpecialTypeIdFk(Long specialTypeIdFk) {
		this.specialTypeIdFk = specialTypeIdFk;
	}

	// toString
	@Override
	public String toString() {
		return "Special [specialId=" + specialId + ", specialTitle=" + specialTitle + ", imageIdFk=" + imageIdFk + ", createTime=" + createTime + ", browseCount=" + browseCount + ", specialTypeIdFk=" + specialTypeIdFk + ", specialContent=" + specialContent + ", videoCode=" + videoCode + ", image=" + image + ", specialType=" + specialType + ", specialOutline=" + specialOutline + "]";
	}

}