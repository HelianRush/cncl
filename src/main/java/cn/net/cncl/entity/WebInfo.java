package cn.net.cncl.entity;

public class WebInfo {
	private Integer webId;

	private String webName;

	private String domainName;

	private String recordsCode;

	private String company;

	private String legalPerson;

	private String imageLogo;

	private String webEmail;

	public Integer getWebId() {
		return webId;
	}

	public void setWebId(Integer webId) {
		this.webId = webId;
	}

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName == null ? null : webName.trim();
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName == null ? null : domainName.trim();
	}

	public String getRecordsCode() {
		return recordsCode;
	}

	public void setRecordsCode(String recordsCode) {
		this.recordsCode = recordsCode == null ? null : recordsCode.trim();
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company == null ? null : company.trim();
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson == null ? null : legalPerson.trim();
	}

	public String getImageLogo() {
		return imageLogo;
	}

	public void setImageLogo(String imageLogo) {
		this.imageLogo = imageLogo == null ? null : imageLogo.trim();
	}

	public String getWebEmail() {
		return webEmail;
	}

	public void setWebEmail(String webEmail) {
		this.webEmail = webEmail == null ? null : webEmail.trim();
	}

	@Override
	public String toString() {
		return "WebInfo [webId=" + webId + ", webName=" + webName + ", domainName=" + domainName + ", recordsCode=" + recordsCode + ", company=" + company + ", legalPerson=" + legalPerson + ", imageLogo=" + imageLogo + ", webEmail=" + webEmail + "]";
	}

}