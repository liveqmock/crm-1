package com.deppon.crm.module.customer.shared.domain.integral;

import java.util.Date;

/**
 * 
 * <p>
 * 已积分运单<br />
 * </p>
 * @title IntegralWaybill.java
 * @package com.deppon.crm.module.customer.shared.domain.integral 
 * @author bxj
 * @version 0.2 2012-4-25
 */
public class WaybillIntegral extends Integral{
	/** 
	 * @fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = 1L;
	//单号
	private String number;
	//收货日期
	private Date bizDate;
	//出发部门
	private String leavedeptName;
	//达到部门
	private String ladingstationName;
	//运输类型
	private String transType;
	//付款方式
	private String payMent;
	//订单渠道
	private String ditch;
	//总费用
	private String totalMoney;
	//积分率
	private String rate;
	//积分角色
	private String role;
	//积分描述
	private String pointdesc;
	//积分日期
	private Date integDate;
	//李学兴增加，防止编译不通过
	private String status;
	/**
	 * <p>
	 * Description:number<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * <p>
	 * Description:number<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * <p>
	 * Description:bizDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getBizDate() {
		return bizDate;
	}
	/**
	 * <p>
	 * Description:bizDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}
	/**
	 * <p>
	 * Description:leavedeptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLeavedeptName() {
		return leavedeptName;
	}
	/**
	 * <p>
	 * Description:leavedeptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLeavedeptName(String leavedeptName) {
		this.leavedeptName = leavedeptName;
	}
	/**
	 * <p>
	 * Description:ladingstationName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLadingstationName() {
		return ladingstationName;
	}
	/**
	 * <p>
	 * Description:ladingstationName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLadingstationName(String ladingstationName) {
		this.ladingstationName = ladingstationName;
	}
	/**
	 * <p>
	 * Description:transType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getTransType() {
		return transType;
	}
	/**
	 * <p>
	 * Description:transType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTransType(String transType) {
		this.transType = transType;
	}
	/**
	 * <p>
	 * Description:payMent<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getPayMent() {
		return payMent;
	}
	/**
	 * <p>
	 * Description:payMent<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setPayMent(String payMent) {
		this.payMent = payMent;
	}
	/**
	 * <p>
	 * Description:ditch<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getDitch() {
		return ditch;
	}
	/**
	 * <p>
	 * Description:ditch<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDitch(String ditch) {
		this.ditch = ditch;
	}
	/**
	 * <p>
	 * Description:totalMoney<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getTotalMoney() {
		return totalMoney;
	}
	/**
	 * <p>
	 * Description:totalMoney<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	/**
	 * <p>
	 * Description:rate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getRate() {
		return rate;
	}
	/**
	 * <p>
	 * Description:rate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}
	/**
	 * <p>
	 * Description:role<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getRole() {
		return role;
	}
	/**
	 * <p>
	 * Description:role<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * <p>
	 * Description:pointdesc<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getPointdesc() {
		return pointdesc;
	}
	/**
	 * <p>
	 * Description:pointdesc<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setPointdesc(String pointdesc) {
		this.pointdesc = pointdesc;
	}
	/**
	 * <p>
	 * Description:integDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getIntegDate() {
		return integDate;
	}
	/**
	 * <p>
	 * Description:integDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIntegDate(Date integDate) {
		this.integDate = integDate;
	}
	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * <p>
	 * Description:serialversionuid<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
