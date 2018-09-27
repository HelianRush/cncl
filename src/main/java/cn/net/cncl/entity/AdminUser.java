package cn.net.cncl.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AdminUser {

	private Long adminUserId;

	private String adminUserName;

	private String password;

	private String name;

	private String nickName;

	private String mobilePhone;

	private String telephone;

	private String email;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastLoginTime;

	private String lastLoginIp;

	public AdminUser() {
	}

	public AdminUser(Long adminUserId, String adminUserName, String password, String name, String nickName, String mobilePhone, String telephone, String email, Date createTime, Date lastLoginTime, String lastLoginIp) {
		super();
		this.adminUserId = adminUserId;
		this.adminUserName = adminUserName;
		this.password = password;
		this.name = name;
		this.nickName = nickName;
		this.mobilePhone = mobilePhone;
		this.telephone = telephone;
		this.email = email;
		this.createTime = createTime;
		this.lastLoginTime = lastLoginTime;
		this.lastLoginIp = lastLoginIp;
	}

	/* Getter & Setter */
	public Long getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(Long adminUserId) {
		this.adminUserId = adminUserId;
	}

	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	@Override
	public String toString() {
		return "AdminUser [adminUserId=" + adminUserId + ", adminUserName=" + adminUserName + ", password=" + password + ", name=" + name + ", nickName=" + nickName + ", mobilePhone=" + mobilePhone + ", telephone=" + telephone + ", email=" + email + ", createTime=" + createTime + ", lastLoginTime=" + lastLoginTime + ", lastLoginIp=" + lastLoginIp + "]";
	}

}