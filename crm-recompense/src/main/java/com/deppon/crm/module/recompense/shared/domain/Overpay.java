package com.deppon.crm.module.recompense.shared.domain;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.entity.BaseEntity;
/**
 * 
 * <p>
 * Description:多赔<br />
 * </p>
 * @title Overpay.java
 * @package com.deppon.crm.module.recompense.shared.domain 
 * @author roy
 * @version 0.1 2013-1-21
 */
@SuppressWarnings("serial")
public class Overpay extends BaseEntity {
	// 理赔单ID
	private String recompenseId;
	// 多赔金额
	private Double overpayAmount;
	// 多赔后的总金额
	private Double totalAmount;
	// 应收账款是否回收
	private boolean recoverYszk;
	// 多赔所属事业部
	private Department overpayBu;
	// 多赔原因
	private String overpayReason;
	// 多赔的工作流号
	private String workNumber;
	// 多赔部门会计
	private User deptAccount;
	// 标杆编码
	private String applyStandardCode;
	// 申请部门
	private String applyDeptId;
	
	private String workflowNo;

	//运单号
	private String waybillNumber;
	//运输类型
	private String  transType;
	//申请人
	private String crateUserName;
	//理赔类型
	private String recompenseType;
	//实际理赔金额
	private Double realAmount;
	/**
	 * constructer
	 */
	public Overpay() {
	}

	/**
	 * constructer
	 * 
	 * @param reocmpenseId
	 * @param wbNum
	 * @param overpayAmount
	 * @param totalAmount
	 * @param recoverYszk
	 * @param overpayAccountDeptId
	 * @param overpayBuId
	 * @param overpayReason
	 * @param workNumber
	 * @param bizCode
	 * @param applyStandardCode
	 * @param applyDeptId
	 */
	public Overpay(String recompenseId, Double overpayAmount,
			Double totalAmount, boolean recoverYszk,
			Department overpayAccountDept, Department overpayBu,
			String overpayReason, String workNumber, 
			String bizCode, String applyStandardCode, String applyDeptId) {
		super();
		this.recompenseId = recompenseId;
		this.overpayAmount = overpayAmount;
		this.totalAmount = totalAmount;
		this.recoverYszk = recoverYszk;
		this.overpayBu = overpayBu;
		this.overpayReason = overpayReason;
		this.workNumber = workNumber;
		this.applyStandardCode = applyStandardCode;
		this.applyDeptId = applyDeptId;
	}

	/**
	 * <p>
	 * Description:recompenseId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getRecompenseId() {
		return recompenseId;
	}

	/**
	 * <p>
	 * Description:recompenseId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecompenseId(String recompenseId) {
		this.recompenseId = recompenseId;
	}

	/**
	 * <p>
	 * Description:overpayAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Double getOverpayAmount() {
		return overpayAmount;
	}

	/**
	 * <p>
	 * Description:overpayAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setOverpayAmount(Double overpayAmount) {
		this.overpayAmount = overpayAmount;
	}

	/**
	 * <p>
	 * Description:totalAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Double getTotalAmount() {
		return totalAmount;
	}

	/**
	 * <p>
	 * Description:totalAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * <p>
	 * Description:recoverYszk<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public boolean isRecoverYszk() {
		return recoverYszk;
	}

	/**
	 * <p>
	 * Description:recoverYszk<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecoverYszk(boolean recoverYszk) {
		this.recoverYszk = recoverYszk;
	}

	/**
	 * <p>
	 * Description:overpayBu<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Department getOverpayBu() {
		return overpayBu;
	}

	/**
	 * <p>
	 * Description:overpayBu<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setOverpayBu(Department overpayBu) {
		this.overpayBu = overpayBu;
	}

	/**
	 * <p>
	 * Description:overpayReason<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getOverpayReason() {
		return overpayReason;
	}

	/**
	 * <p>
	 * Description:overpayReason<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setOverpayReason(String overpayReason) {
		this.overpayReason = overpayReason;
	}

	/**
	 * <p>
	 * Description:workNumber<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getWorkNumber() {
		return workNumber;
	}

	/**
	 * <p>
	 * Description:workNumber<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}

	/**
	 * <p>
	 * Description:deptAccount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public User getDeptAccount() {
		return deptAccount;
	}

	/**
	 * <p>
	 * Description:deptAccount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setDeptAccount(User deptAccount) {
		this.deptAccount = deptAccount;
	}

	public String getApplyStandardCode() {
		return applyStandardCode;
	}

	public void setApplyStandardCode(String applyStandardCode) {
		this.applyStandardCode = applyStandardCode;
	}

	public String getApplyDeptId() {
		return applyDeptId;
	}

	public void setApplyDeptId(String applyDeptId) {
		this.applyDeptId = applyDeptId;
	}

	public String getWorkflowNo() {
		return workflowNo;
	}

	public void setWorkflowNo(String workflowNo) {
		this.workflowNo = workflowNo;
	}

	public String getWaybillNumber() {
		return waybillNumber;
	}

	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getCrateUserName() {
		return crateUserName;
	}

	public void setCrateUserName(String crateUserName) {
		this.crateUserName = crateUserName;
	}

	public String getRecompenseType() {
		return recompenseType;
	}

	public void setRecompenseType(String recompenseType) {
		this.recompenseType = recompenseType;
	}

	public Double getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(Double realAmount) {
		this.realAmount = realAmount;
	}

	
		
}
