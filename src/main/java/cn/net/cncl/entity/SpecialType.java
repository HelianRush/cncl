package cn.net.cncl.entity;

import java.util.List;

public class SpecialType {

	private Long specialTypeId;

	private String specialTypeName;

	private List<Special> specials;

	public List<Special> getSpecials() {
		return specials;
	}

	public void setSpecials(List<Special> specials) {
		this.specials = specials;
	}

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
		return "SpecialType [specialTypeId=" + specialTypeId + ", specialTypeName=" + specialTypeName + ", specials=" + specials + "]";
	}

}