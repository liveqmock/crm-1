package com.deppon.crm.module.recompense.shared.domain;

import java.util.Date;
/**
 * 
 * <p>
 * Description:理赔查询条件<br />
 * </p>
 * @title RecompenseSearchView.java
 * @package com.deppon.crm.module.recompense.shared.domain 
 * @author roy
 * @version 0.1 2013-1-21
 */
public class RecompenseSearchView {
	//用户id
	private String userId;
	//凭证号
	private String voucherNo;
	//客户编号
	private String custNum;
	//部门
	private String insurDept;
	//区域
	private String ownarea;
	//客户名称
	private String custName;
	//凭证编号
	private String voucherNum;
	//开始修改时间
	private Date modifyStartTime;
	//到修改时间
	private Date modifyEndTime;
	//开始时间
	private Date insurStartTime;
	//结束时间
	private Date insurEndTime;
	//理赔类型
	private String recompenseType;
	//理赔方式
	private String recompenseMethod;
	//理赔状态
	private String recompenseState;
	/**
	 * <p>
	 * Description:userId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * <p>
	 * Description:userId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * <p>
	 * Description:voucherNo<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getVoucherNo() {
		return voucherNo;
	}
	/**
	 * <p>
	 * Description:voucherNo<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}
	/**
	 * <p>
	 * Description:custNum<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getCustNum() {
		return custNum;
	}
	/**
	 * <p>
	 * Description:custNum<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCustNum(String custNum) {
		this.custNum = custNum;
	}
	/**
	 * <p>
	 * Description:insurDept<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getInsurDept() {
		return insurDept;
	}
	/**
	 * <p>
	 * Description:insurDept<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setInsurDept(String insurDept) {
		this.insurDept = insurDept;
	}
	/**
	 * <p>
	 * Description:ownarea<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getOwnarea() {
		return ownarea;
	}
	/**
	 * <p>
	 * Description:ownarea<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setOwnarea(String ownarea) {
		this.ownarea = ownarea;
	}
	/**
	 * <p>
	 * Description:custName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * <p>
	 * Description:custName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * <p>
	 * Description:voucherNum<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getVoucherNum() {
		return voucherNum;
	}
	/**
	 * <p>
	 * Description:voucherNum<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setVoucherNum(String voucherNum) {
		this.voucherNum = voucherNum;
	}
	/**
	 * <p>
	 * Description:modifyStartTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getModifyStartTime() {
		return modifyStartTime;
	}
	/**
	 * <p>
	 * Description:modifyStartTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setModifyStartTime(Date modifyStartTime) {
		this.modifyStartTime = modifyStartTime;
	}
	/**
	 * <p>
	 * Description:modifyEndTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getModifyEndTime() {
		return modifyEndTime;
	}
	/**
	 * <p>
	 * Description:modifyEndTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setModifyEndTime(Date modifyEndTime) {
		this.modifyEndTime = modifyEndTime;
	}
	/**
	 * <p>
	 * Description:insurStartTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getInsurStartTime() {
		return insurStartTime;
	}
	/**
	 * <p>
	 * Description:insurStartTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setInsurStartTime(Date insurStartTime) {
		this.insurStartTime = insurStartTime;
	}
	/**
	 * <p>
	 * Description:insurEndTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getInsurEndTime() {
		return insurEndTime;
	}
	/**
	 * <p>
	 * Description:insurEndTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setInsurEndTime(Date insurEndTime) {
		this.insurEndTime = insurEndTime;
	}
	/**
	 * <p>
	 * Description:recompenseType<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getRecompenseType() {
		return recompenseType;
	}
	/**
	 * <p>
	 * Description:recompenseType<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecompenseType(String recompenseType) {
		this.recompenseType = recompenseType;
	}
	/**
	 * <p>
	 * Description:recompenseMethod<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getRecompenseMethod() {
		return recompenseMethod;
	}
	/**
	 * <p>
	 * Description:recompenseMethod<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecompenseMethod(String recompenseMethod) {
		this.recompenseMethod = recompenseMethod;
	}
	/**
	 * <p>
	 * Description:recompenseState<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getRecompenseState() {
		return recompenseState;
	}
	/**
	 * <p>
	 * Description:recompenseState<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecompenseState(String recompenseState) {
		this.recompenseState = recompenseState;
	}

	
}
