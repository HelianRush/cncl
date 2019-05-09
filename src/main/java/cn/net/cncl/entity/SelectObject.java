package cn.net.cncl.entity;

public class SelectObject {

	/**
	 * 站内查询封装数据用<br>
	 * 没有数据库
	 */

	// Beans
	private Long resourceId;
	private String resourceType;
	private String titel;
	private String outline;
	private Images image;

	// Constructor
	public SelectObject() {
		super();
	}

	public SelectObject(Long resourceId, String resourceType, String titel, String outline, Images image) {
		super();
		this.resourceId = resourceId;
		this.resourceType = resourceType;
		this.titel = titel;
		this.outline = outline;
		this.image = image;
	}

	// Getter Setter
	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public Images getImage() {
		return image;
	}

	public void setImage(Images image) {
		this.image = image;
	}

	// toString
	@Override
	public String toString() {
		return "SelectObject [resourceId=" + resourceId + ", resourceType=" + resourceType + ", titel=" + titel + ", outline=" + outline + ", image=" + image + "]";
	}

	// Main
	// public static void main(String[] args) {
	// }

}
