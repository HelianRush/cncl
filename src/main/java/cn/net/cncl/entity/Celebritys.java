package cn.net.cncl.entity;

import java.util.Date;

public class Celebritys {
	private Long celebrityId;

	private String celebrityName;

	private String anotherName;

	private String foreignName;

	private String nationality;

	private String nation;

	private String constellation;

	private Integer bloodType;

	private Integer height;

	private String birthplace;

	private String nativePlace;

	private String ancestralHome;

	private String identityCard;

	private String certificateCode;

	private String certificateName;

	private String profession;

	private String company;

	private String representativeName;

	private String achievement;

	private Date birthday;

	private Date deathday;

	private Long videoIdFk;

	private Long imageIdFk;

	private Integer browseCount;

	private String outline;

	private String introduce;

	public Long getCelebrityId() {
		return celebrityId;
	}

	public void setCelebrityId(Long celebrityId) {
		this.celebrityId = celebrityId;
	}

	public String getCelebrityName() {
		return celebrityName;
	}

	public void setCelebrityName(String celebrityName) {
		this.celebrityName = celebrityName == null ? null : celebrityName.trim();
	}

	public String getAnotherName() {
		return anotherName;
	}

	public void setAnotherName(String anotherName) {
		this.anotherName = anotherName == null ? null : anotherName.trim();
	}

	public String getForeignName() {
		return foreignName;
	}

	public void setForeignName(String foreignName) {
		this.foreignName = foreignName == null ? null : foreignName.trim();
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality == null ? null : nationality.trim();
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation == null ? null : nation.trim();
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation == null ? null : constellation.trim();
	}

	public Integer getBloodType() {
		return bloodType;
	}

	public void setBloodType(Integer bloodType) {
		this.bloodType = bloodType;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace == null ? null : birthplace.trim();
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace == null ? null : nativePlace.trim();
	}

	public String getAncestralHome() {
		return ancestralHome;
	}

	public void setAncestralHome(String ancestralHome) {
		this.ancestralHome = ancestralHome == null ? null : ancestralHome.trim();
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard == null ? null : identityCard.trim();
	}

	public String getCertificateCode() {
		return certificateCode;
	}

	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode == null ? null : certificateCode.trim();
	}

	public String getCertificateName() {
		return certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName == null ? null : certificateName.trim();
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession == null ? null : profession.trim();
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company == null ? null : company.trim();
	}

	public String getRepresentativeName() {
		return representativeName;
	}

	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName == null ? null : representativeName.trim();
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement == null ? null : achievement.trim();
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getDeathday() {
		return deathday;
	}

	public void setDeathday(Date deathday) {
		this.deathday = deathday;
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

	public Integer getBrowseCount() {
		return browseCount;
	}

	public void setBrowseCount(Integer browseCount) {
		this.browseCount = browseCount;
	}

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	@Override
	public String toString() {
		return "Celebritys [celebrityId=" + celebrityId + ", celebrityName=" + celebrityName + ", anotherName=" + anotherName + ", foreignName=" + foreignName + ", nationality=" + nationality + ", nation=" + nation + ", constellation=" + constellation + ", bloodType=" + bloodType + ", height=" + height + ", birthplace=" + birthplace + ", nativePlace=" + nativePlace + ", ancestralHome=" + ancestralHome + ", identityCard=" + identityCard + ", certificateCode=" + certificateCode + ", certificateName=" + certificateName + ", profession=" + profession + ", company=" + company + ", representativeName=" + representativeName + ", achievement=" + achievement + ", birthday=" + birthday + ", deathday=" + deathday + ", videoIdFk=" + videoIdFk + ", imageIdFk=" + imageIdFk + ", browseCount=" + browseCount + ", outline=" + outline + ", introduce=" + introduce + ", getCelebrityId()=" + getCelebrityId() + ", getCelebrityName()=" + getCelebrityName() + ", getAnotherName()=" + getAnotherName()
				+ ", getForeignName()=" + getForeignName() + ", getNationality()=" + getNationality() + ", getNation()=" + getNation() + ", getConstellation()=" + getConstellation() + ", getBloodType()=" + getBloodType() + ", getHeight()=" + getHeight() + ", getBirthplace()=" + getBirthplace() + ", getNativePlace()=" + getNativePlace() + ", getAncestralHome()=" + getAncestralHome() + ", getIdentityCard()=" + getIdentityCard() + ", getCertificateCode()=" + getCertificateCode() + ", getCertificateName()=" + getCertificateName() + ", getProfession()=" + getProfession() + ", getCompany()=" + getCompany() + ", getRepresentativeName()=" + getRepresentativeName() + ", getAchievement()=" + getAchievement() + ", getBirthday()=" + getBirthday() + ", getDeathday()=" + getDeathday() + ", getVideoIdFk()=" + getVideoIdFk() + ", getImageIdFk()=" + getImageIdFk() + ", getBrowseCount()=" + getBrowseCount() + ", getOutline()=" + getOutline() + ", getIntroduce()=" + getIntroduce() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}