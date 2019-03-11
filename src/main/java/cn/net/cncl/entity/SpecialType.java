package cn.net.cncl.entity;

public class SpecialType {

	private Long specialTypeId;

	private String specialTypeName;

	public Long getSpecialTypeId() {
		return specialTypeId;
	}

	public void setSpecialTypeId(Long specialTypeId) {
		this.specialTypeId = specialTypeId;
	}

	public String getSpecialTypeName() {
		return specialTypeName;
	}

	public void setSpecialTypeName(String specialTypeName) {
		this.specialTypeName = specialTypeName == null ? null : specialTypeName.trim();
	}

	@Override
	public String toString() {
		return "SpecialType [specialTypeId=" + specialTypeId + ", specialTypeName=" + specialTypeName + "]";
	}

}