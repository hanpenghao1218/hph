package com.bestinfo.canal.client.req;

public class StationReq extends BaseReq {
	/** 上报类型：1—建站，2—变更，3—启用，4—停用，5—注销，默认 1 */
	public int reportType = 1;
	/** 站点编号 */
	public String stationCode = "";
	/** 地市销售机构编码 */
	public String insCode = "";
	/** 站点设立时间 */
	public String stationSetTime = "";
	/** 站点撤销时间 */
	public String stationCanelTime = "";
	/** 彩票经营种类：1—福彩，2—双彩，默认 1 */
	public int manageType = 1;
	/** 营业类型：1—专营，2—兼营，默认 1 */
	public int businessType = 1;
	/** 站点星级，1-5星，数字越大，星级越高，默认0 */
	public int stationRate = 0;
	/**
	 * 站点地址属性（默认06）01—站主自有居民区内小型店面，02—便利店、超市等，03—酒店、宾馆等娱乐场所，04—火车站、长途客车站，05—机场，06—专营店，07—其他
	 */
	public int addrProperty = 6;
	/** 站点地址 */
	public String stationAddr = "";
	/** 站点邮政编码 */
	public int postcode;
	/** 站主身份证号 */
	public String idCard = "";
	/** 站主姓名 */
	public String userName = "";
	/** 站主联系电话 */
	public String userMobile = "";
	/** 站主学历（默认 6）：1—小学，2—初中，3—高中，4—中专，5—大专，6—本科，7—硕士，8—博士 */
	public int userEducation;
	/** 站主邮箱 */
	public String userEmail = "";
	/** 身份证地址 */
	public String idcardAddr = "";
	/** 居住地址 */
	public String userAddr = "";

	public int getReportType() {
		return reportType;
	}

	public void setReportType(int reportType) {
		this.reportType = reportType;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getInsCode() {
		return insCode;
	}

	public void setInsCode(String insCode) {
		this.insCode = insCode;
	}

	public String getStationSetTime() {
		return stationSetTime;
	}

	public void setStationSetTime(String stationSetTime) {
		this.stationSetTime = stationSetTime;
	}

	public String getStationCanelTime() {
		return stationCanelTime;
	}

	public void setStationCanelTime(String stationCanelTime) {
		this.stationCanelTime = stationCanelTime;
	}

	public int getManageType() {
		return manageType;
	}

	public void setManageType(int manageType) {
		this.manageType = manageType;
	}

	public int getBusinessType() {
		return businessType;
	}

	public void setBusinessType(int businessType) {
		this.businessType = businessType;
	}

	public int getAddrProperty() {
		return addrProperty;
	}

	public void setAddrProperty(int addrProperty) {
		this.addrProperty = addrProperty;
	}

	public String getStationAddr() {
		return stationAddr;
	}

	public void setStationAddr(String stationAddr) {
		this.stationAddr = stationAddr;
	}

	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public int getUserEducation() {
		return userEducation;
	}

	public void setUserEducation(int userEducation) {
		this.userEducation = userEducation;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getIdcardAddr() {
		return idcardAddr;
	}

	public void setIdcardAddr(String idcardAddr) {
		this.idcardAddr = idcardAddr;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	public int getStationRate() {
		return stationRate;
	}

	public void setStationRate(int stationRate) {
		this.stationRate = stationRate;
	}
}