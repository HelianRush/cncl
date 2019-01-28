package cn.net.cncl.entity;

public class WebInfo {
	private Integer webId;

	private String webName;

	private String domainName;

	private String recordsCode;

	private String company;

	private String legalPerson;

	private String imageLogo;

	private String logoPath;

	private String webEmail;

	public WebInfo() {
	}

	public WebInfo(Integer webId, String webName, String domainName, String recordsCode, String company, String legalPerson, String imageLogo, String logoPath, String webEmail) {
		super();
		this.webId = webId;
		this.webName = webName;
		this.domainName = domainName;
		this.recordsCode = recordsCode;
		this.company = company;
		this.legalPerson = legalPerson;
		this.imageLogo = imageLogo;
		this.logoPath = logoPath;
		this.webEmail = webEmail;
	}

	/* Getter & Setter */
	public Integer getWebId() {
		return webId;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public void setWebId(Integer webId) {
		this.webId = webId;
	}

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getRecordsCode() {
		return recordsCode;
	}

	public void setRecordsCode(String recordsCode) {
		this.recordsCode = recordsCode;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getImageLogo() {
		return imageLogo;
	}

	public void setImageLogo(String imageLogo) {
		this.imageLogo = imageLogo;
	}

	public String getWebEmail() {
		return webEmail;
	}

	public void setWebEmail(String webEmail) {
		this.webEmail = webEmail;
	}

	@Override
	public String toString() {
		return "WebInfo [webId=" + webId + ", webName=" + webName + ", domainName=" + domainName + ", recordsCode=" + recordsCode + ", company=" + company + ", legalPerson=" + legalPerson + ", imageLogo=" + imageLogo + ", logoPath=" + logoPath + ", webEmail=" + webEmail + "]";
	}

}