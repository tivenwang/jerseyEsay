package com.tivenwang.entities;

// Generated 2014-3-4 10:36:55 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * TtUser generated by hbm2java
 */
@JsonAutoDetect
@JsonIgnoreProperties(value={"id","pwd","userMileage","loginStatus","status","cardholder","bankcardNumber","alipayUsername","alipayNumber"})
public class User{

	/**
	 * 
	 */
	protected Integer id;
	protected String phoneNumber;
	protected String userName;
	protected String pwd;
	protected String email;
	protected Integer point;
	protected BigDecimal userMileage;
	protected Integer userLevel;
	protected Integer integrity;
	protected Date lastLoginTime;
	protected Date signTime;
	protected Byte loginStatus;
	protected Byte status;
	protected String iconUrl;
	protected BigDecimal totalFee;
	protected Byte walletType;
	protected Byte bankcardType;
	protected String cardholder;
	protected String bankcardNumber;
	protected String alipayUsername;
	protected String alipayNumber;
	protected String bankNum;
	public User() {
	}

	public User(String phoneNumber, String userName, String pwd, Integer point,
			BigDecimal userMileage, Integer userLevel, Integer integrity,
			Date lastLoginTime, Date signTime, Byte loginStatus, Byte status,
			String iconUrl, BigDecimal totalFee, Byte walletType) {
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.pwd = pwd;
		this.point = point;
		this.userMileage = userMileage;
		this.userLevel = userLevel;
		this.integrity = integrity;
		this.lastLoginTime = lastLoginTime;
		this.signTime = signTime;
		this.loginStatus = loginStatus;
		this.status = status;
		this.iconUrl = iconUrl;
		this.totalFee = totalFee;
		this.walletType = walletType;
	}

	public User(String phoneNumber, String userName, String pwd,
			String email, Integer point, BigDecimal userMileage, Integer userLevel,
			Integer integrity, Date lastLoginTime, Date signTime, Byte loginStatus,
			Byte status, String iconUrl, BigDecimal totalFee, Byte walletType,
			Byte bankcardType, String cardholder, String bankcardNumber,
			String alipayUsername, String alipayNumber) {
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.pwd = pwd;
		this.email = email;
		this.point = point;
		this.userMileage = userMileage;
		this.userLevel = userLevel;
		this.integrity = integrity;
		this.lastLoginTime = lastLoginTime;
		this.signTime = signTime;
		this.loginStatus = loginStatus;
		this.status = status;
		this.iconUrl = iconUrl;
		this.totalFee = totalFee;
		this.walletType = walletType;
		this.bankcardType = bankcardType;
		this.cardholder = cardholder;
		this.bankcardNumber = bankcardNumber;
		this.alipayUsername = alipayUsername;
		this.alipayNumber = alipayNumber;
	}

	public Integer getId() {
		return this.id;
	}

	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public BigDecimal getUserMileage() {
		return this.userMileage;
	}

	public void setUserMileage(BigDecimal userMileage) {
		this.userMileage = userMileage;
	}

	public Integer getUserLevel() {
		return this.userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public Integer getIntegrity() {
		return this.integrity;
	}

	public void setIntegrity(Integer integrity) {
		this.integrity = integrity;
	}

	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getSignTime() {
		return this.signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public Byte getLoginStatus() {
		return this.loginStatus;
	}

	public void setLoginStatus(Byte loginStatus) {
		this.loginStatus = loginStatus;
	}

	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getIconUrl() {
		return this.iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public BigDecimal getTotalFee() {
		return this.totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public Byte getWalletType() {
		return this.walletType;
	}

	public void setWalletType(Byte walletType) {
		this.walletType = walletType;
	}

	public Byte getBankcardType() {
		return this.bankcardType;
	}

	public void setBankcardType(Byte bankcardType) {
		this.bankcardType = bankcardType;
	}

	public String getCardholder() {
		return this.cardholder;
	}

	public void setCardholder(String cardholder) {
		this.cardholder = cardholder;
	}

	public String getBankcardNumber() {
		return this.bankcardNumber;
	}

	public void setBankcardNumber(String bankcardNumber) {
		this.bankcardNumber = bankcardNumber;
	}

	public String getAlipayUsername() {
		return this.alipayUsername;
	}

	public void setAlipayUsername(String alipayUsername) {
		this.alipayUsername = alipayUsername;
	}

	public String getAlipayNumber() {
		return this.alipayNumber;
	}

	public void setAlipayNumber(String alipayNumber) {
		this.alipayNumber = alipayNumber;
	}

}
