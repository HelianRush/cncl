package cn.net.cncl.entity;

import java.util.Date;

public class Images {
	private Long imageId;

	private String imageCode;

	private Long resourceBy;

	private String resourceByType;

	private String imageName;

	private String extensionName;

	private String imageTitle;

	private String imagePaths;

	private String imagePath;

	private Long imageSize;

	private Date createTime;

	private String topStatus;

	private String imageContent;

	private String description;

	/*
	 * getter setter
	 */

	public String getResourceByType() {
		return resourceByType;
	}

	public void setResourceByType(String resourceByType) {
		this.resourceByType = resourceByType;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode == null ? null : imageCode.trim();
	}

	public Long getResourceBy() {
		return resourceBy;
	}

	public void setResourceBy(Long resourceBy) {
		this.resourceBy = resourceBy;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName == null ? null : imageName.trim();
	}

	public String getExtensionName() {
		return extensionName;
	}

	public void setExtensionName(String extensionName) {
		this.extensionName = extensionName == null ? null : extensionName.trim();
	}

	public String getImageTitle() {
		return imageTitle;
	}

	public void setImageTitle(String imageTitle) {
		this.imageTitle = imageTitle == null ? null : imageTitle.trim();
	}

	public String getImagePaths() {
		return imagePaths;
	}

	public void setImagePaths(String imagePaths) {
		this.imagePaths = imagePaths == null ? null : imagePaths.trim();
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath == null ? null : imagePath.trim();
	}

	public Long getImageSize() {
		return imageSize;
	}

	public void setImageSize(Long imageSize) {
		this.imageSize = imageSize;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTopStatus() {
		return topStatus;
	}

	public void setTopStatus(String topStatus) {
		this.topStatus = topStatus == null ? null : topStatus.trim();
	}

	public String getImageContent() {
		return imageContent;
	}

	public void setImageContent(String imageContent) {
		this.imageContent = imageContent == null ? null : imageContent.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	@Override
	public String toString() {
		return "Images [imageId=" + imageId + ", imageCode=" + imageCode + ", resourceBy=" + resourceBy + ", imageName=" + imageName + ", extensionName=" + extensionName + ", imageTitle=" + imageTitle + ", imagePaths=" + imagePaths + ", imagePath=" + imagePath + ", imageSize=" + imageSize + ", createTime=" + createTime + ", topStatus=" + topStatus + ", imageContent=" + imageContent + ", description=" + description + "]";
	}

}