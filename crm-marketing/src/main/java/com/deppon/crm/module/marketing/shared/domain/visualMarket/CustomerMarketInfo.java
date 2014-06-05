/**
 * <p>
 * Description:<br />
 * </p>
 * @title CustomerMarketInfo.java
 * @package com.deppon.crm.module.marketing.shared.domain.visualMarket
 * @author xiaohongye
 * @version V0.1 
 * @Date 2013-4-18
 */
package com.deppon.crm.module.marketing.shared.domain.visualMarket;

import java.util.Date;

/**
 * <p>
 * Description:查询结果信息<br />
 * </p>
 * @title CustomerMarketInfo.java
 * @package com.deppon.crm.module.marketing.shared.domain.visualMarket
 * @author xiaohongye
 * @version V0.1 
 * @Date 2013-4-18
 */
public class CustomerMarketInfo {
	//固定客户编码
	private String custNumber;
	//固定客户id
	private String custId;
	//潜散客id
	private String psCustId;
	//客户名称
	private String custName;
	//主联系人编码（固定客户）
	private String linkManNumber;
	//主联系人id
	private String linkManId;
	//主联系人姓名(固定客户)
	private String mainLinkManName;
	//联系人姓名（潜散客）
	private String linkManName;
	//手机
	private String mobile;
	//固定电话
	private String telephone;
	//地址（固定客户：主联系人的主偏好地址；潜散客：客户地址）
	private String address;
	//地址经度
	private String longitude;
	//地址纬度
	private String latitude;
	//月均收入（固定客户）
	private String aveMonthlyIncome;
	//当月货量
	private String monthlyGoodsNum;
	//交易时间（固定客户）
	private Date dealTime;
	//回访时间
	private Date returnVisitTime;
	//创建时间
	private Date createTime;
	//回访状态
	private String returnVisitStatus;
	//归属部门id
	private String deptId;
	//部门网点id(部门服务区坐标编号)
	private String depCoordinate;
	//客户类型(固定客户：MEMBER;潜客;散客)
	private String custType;
	//固定客户客户等级
	private String custDegree;
	//上月发货周期
	private int sendGoodsCycle;
	/**
	 * @param setLinkManId the setLinkManId to get
	 */
	public String getLinkManId() {
	    //返回linkManId
		return linkManId;
	}
	/**
	 * @param setLinkManId the setLinkManId to set
	 */
	public void setLinkManId(String linkManId) {
	    //设置linkManId
		this.linkManId = linkManId;
	}
	/**
	 * @param custId the custId to get
	 */
	public String getCustId() {
	    //返回custId
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(String custId) {
	    //custId设置
		this.custId = custId;
	}
	/**
	 * @param custType the custType to get
	 */
	public String getCustType() {
	    //返回custType
		return custType;
	}
	/**
	 * @param custType the custType to set
	 */
	public void setCustType(String custType) {
	    //custType设置
		this.custType = custType;
	}
	/**
	 * @param custNumber the custNumber to get
	 */
	public String getCustNumber() {
	    //返回custNumber
		return custNumber;
	}
	/**
	 * @param custNumber the custNumber to set
	 */
	public void setCustNumber(String custNumber) {
	    //custNumber设置
		this.custNumber = custNumber;
	}
	/**
	 * @param psCustId the psCustId to get
	 */
	public String getPsCustId() {
	    //返回psCustId
		return psCustId;
	}
	/**
	 * @param psCustId the psCustId to set
	 */
	public void setPsCustId(String psCustId) {
	    //psCustId设置
		this.psCustId = psCustId;
	}
	/**
	 * @param custName the custName to get
	 */
	public String getCustName() {
	    //返回custName
		return custName;
	}
	/**
	 * @param custName the custName to set
	 */
	public void setCustName(String custName) {
	    //custName设置
		this.custName = custName;
	}
	/**
	 * @param mainLinkManName the mainLinkManName to get
	 */
	public String getMainLinkManName() {
	    //返回mainLinkManName
		return mainLinkManName;
	}
	/**
	 * @param mainLinkManName the mainLinkManName to set
	 */
	public void setMainLinkManName(String mainLinkManName) {
	    //mainLinkManName设置
		this.mainLinkManName = mainLinkManName;
	}
	/**
	 * @param linkManName the linkManName to get
	 */
	public String getLinkManName() {
	    //返回linkManName
		return linkManName;
	}
	/**
	 * @param linkManName the linkManName to set
	 */
	public void setLinkManName(String linkManName) {
	    //linkManName设置
		this.linkManName = linkManName;
	}
	/**
	 * @param mobile the mobile to get
	 */
	public String getMobile() {
	    //返回mobile
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
	    //mobile设置
		this.mobile = mobile;
	}
	/**
	 * @param telephone the telephone to get
	 */
	public String getTelephone() {
	    //返回telephone
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
	    //telephone设置
		this.telephone = telephone;
	}
	/**
	 * @param address the address to get
	 */
	public String getAddress() {
	    //返回address
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
	    //address设置
		this.address = address;
	}
	/**
	 * @param longitude the longitude to get
	 */
	public String getLongitude() {
	    //返回longitude
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
	    //longitude设置
		this.longitude = longitude;
	}
	/**
	 * @param latitude the latitude to get
	 */
	public String getLatitude() {
	    //返回latitude
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
	    //latitude设置
		this.latitude = latitude;
	}
	/**
	 * @param aveMonthlyIncome the aveMonthlyIncome to get
	 */
	public String getAveMonthlyIncome() {
	    //返回aveMonthlyIncome
		return aveMonthlyIncome;
	}
	/**
	 * @param aveMonthlyIncome the aveMonthlyIncome to set
	 */
	public void setAveMonthlyIncome(String aveMonthlyIncome) {
	    //aveMonthlyIncome设置
		this.aveMonthlyIncome = aveMonthlyIncome;
	}
	/**
	 * @param monthlyGoodsNum the monthlyGoodsNum to get
	 */
	public String getMonthlyGoodsNum() {
	    //返回monthlyGoodsNum
		return monthlyGoodsNum;
	}
	/**
	 * @param monthlyGoodsNum the monthlyGoodsNum to set
	 */
	public void setMonthlyGoodsNum(String monthlyGoodsNum) {
	    //monthlyGoodsNum设置
		this.monthlyGoodsNum = monthlyGoodsNum;
	}
	/**
	 * @param dealTime the dealTime to get
	 */
	public Date getDealTime() {
	    //返回dealTime
		return dealTime;
	}
	/**
	 * @param dealTime the dealTime to set
	 */
	public void setDealTime(Date dealTime) {
	    //dealTime设置
		this.dealTime = dealTime;
	}
	/**
	 * @param returnVisitTime the returnVisitTime to get
	 */
	public Date getReturnVisitTime() {
	    //返回returnVisitTime
		return returnVisitTime;
	}
	/**
	 * @param returnVisitTime the returnVisitTime to set
	 */
	public void setReturnVisitTime(Date returnVisitTime) {
	    //returnVisitTime设置
		this.returnVisitTime = returnVisitTime;
	}
	/**
	 * @param createTime the createTime to get
	 */
	public Date getCreateTime() {
	    //返回createTime
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
	    //createTime设置
		this.createTime = createTime;
	}
	/**
	 * @param returnVisitStatus the returnVisitStatus to get
	 */
	public String getReturnVisitStatus() {
	    //返回returnVisitStatus
		return returnVisitStatus;
	}
	/**
	 * @param returnVisitStatus the returnVisitStatus to set
	 */
	public void setReturnVisitStatus(String returnVisitStatus) {
	    //returnVisitStatus设置
		this.returnVisitStatus = returnVisitStatus;
	}
	/**
	 * @param deptId the deptId to get
	 */
	public String getDeptId() {
	    //返回deptId
		return deptId;
	}
	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(String deptId) {
	    //deptId设置
		this.deptId = deptId;
	}
	/**
	 * @param depCoordinate the depCoordinate to get
	 */
	public String getDepCoordinate() {
	    //返回depCoordinate
		return depCoordinate;
	}
	/**
	 * @param depCoordinate the depCoordinate to set
	 */
	public void setDepCoordinate(String depCoordinate) {
	    //depCoordinate设置
		this.depCoordinate = depCoordinate;
	}
	/**
	 * @param custDegree the custDegree to get
	 */
	public String getCustDegree() {
	    //返回custDegree
		return custDegree;
	}
	/**
	 * @param custDegree the custDegree to set
	 */
	public void setCustDegree(String custDegree) {
	    //custDegree设置
		this.custDegree = custDegree;
	}
	/**
	 * @param sendGoodsCycle the sendGoodsCycle to get
	 */
	public int getSendGoodsCycle() {
	    //返回sendGoodsCycle
		return sendGoodsCycle;
	}
	/**
	 * @param sendGoodsCycle the sendGoodsCycle to set
	 */
	public void setSendGoodsCycle(int sendGoodsCycle) {
	    //sendGoodsCycle设置
		this.sendGoodsCycle = sendGoodsCycle;
	}
	/**
	 * @param linkManNumber the linkManNumber to get
	 */
	public String getLinkManNumber() {
	    //返回linkManNumber
		return linkManNumber;
	}
	/**
	 * @param linkManNumber the linkManNumber to set
	 */
	public void setLinkManNumber(String linkManNumber) {
	    //linkManNumber设置
		this.linkManNumber = linkManNumber;
	}
}
