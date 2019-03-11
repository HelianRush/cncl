package cn.net.cncl.entity;

public class SpecialIndex {

	private Long specialIndexId;

	private Long imageIdFk;

	private String specialIndexContent;

	private String videoCode;

	private Images image;

	public Images getImage() {
		return image;
	}

	public void setImage(Images image) {
		this.image = image;
	}

	public String getSpecialIndexContent() {
		return specialIndexContent;
	}

	public void setSpecialIndexContent(String specialIndexContent) {
		this.specialIndexContent = specialIndexContent == null ? null : specialIndexContent.trim();
	}

	public String getVideoCode() {
		return videoCode;
	}

	public void setVideoCode(String videoCode) {
		this.videoCode = videoCode == null ? null : videoCode.trim();
	}

	public Long getSpecialIndexId() {
		return specialIndexId;
	}

	public void setSpecialIndexId(Long specialIndexId) {
		this.specialIndexId = specialIndexId;
	}

	public Long getImageIdFk() {
		return imageIdFk;
	}

	public void setImageIdFk(Long imageIdFk) {
		this.imageIdFk = imageIdFk;
	}

	@Override
	public String toString() {
		return "SpecialIndex [specialIndexId=" + specialIndexId + ", imageIdFk=" + imageIdFk + ", specialIndexContent=" + specialIndexContent + ", videoCode=" + videoCode + ", image=" + image + "]";
	}

}