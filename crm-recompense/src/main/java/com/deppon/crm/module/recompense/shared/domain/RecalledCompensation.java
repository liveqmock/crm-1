package com.deppon.crm.module.recompense.shared.domain;

import java.util.Date;

/**
 * 
 * <p>
 * Description:追偿信息<br />
 * </p>
 * @title RecalledCompensation.java
 * @package com.deppon.crm.module.recompense.shared.domain 
 * @author roy
 * @version 0.1 2013-1-21
 */
public class RecalledCompensation {
	// 唯一标示
	private String id;
	// 理赔单关联Id
	private String recompenseId;
	// 追偿日期
	private Date recoveryTime;
	// 追偿部门id
	private String deptId;
	//追偿部门名称
	private String deptName;
	// 暂扣金额
	private Double suspendedAmount;
	// 赔回金额
	private Double compensateBackAmount;
	// 赔回日期
	private Date compensateBackTime;
	// 返回暂扣款
	private Double returnDeductions;
	// 考核奖励
	private Double assessmentReward;
	/**
	 * <p>
	 * Description:id<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getId() {
		return id;
	}
	/**
	 * <p>
	 * Description:id<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setId(String id) {
		this.id = id;
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
	 * Description:recoveryTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getRecoveryTime() {
		return recoveryTime;
	}
	/**
	 * <p>
	 * Description:recoveryTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecoveryTime(Date recoveryTime) {
		this.recoveryTime = recoveryTime;
	}
	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * <p>
	 * Description:deptName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * <p>
	 * Description:deptName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * <p>
	 * Description:suspendedAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Double getSuspendedAmount() {
		return suspendedAmount;
	}
	/**
	 * <p>
	 * Description:suspendedAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setSuspendedAmount(Double suspendedAmount) {
		this.suspendedAmount = suspendedAmount;
	}
	/**
	 * <p>
	 * Description:compensateBackAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Double getCompensateBackAmount() {
		return compensateBackAmount;
	}
	/**
	 * <p>
	 * Description:compensateBackAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCompensateBackAmount(Double compensateBackAmount) {
		this.compensateBackAmount = compensateBackAmount;
	}
	/**
	 * <p>
	 * Description:compensateBackTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getCompensateBackTime() {
		return compensateBackTime;
	}
	/**
	 * <p>
	 * Description:compensateBackTime<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCompensateBackTime(Date compensateBackTime) {
		this.compensateBackTime = compensateBackTime;
	}
	/**
	 * <p>
	 * Description:returnDeductions<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Double getReturnDeductions() {
		return returnDeductions;
	}
	/**
	 * <p>
	 * Description:returnDeductions<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setReturnDeductions(Double returnDeductions) {
		this.returnDeductions = returnDeductions;
	}
	/**
	 * <p>
	 * Description:assessmentReward<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Double getAssessmentReward() {
		return assessmentReward;
	}
	/**
	 * <p>
	 * Description:assessmentReward<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setAssessmentReward(Double assessmentReward) {
		this.assessmentReward = assessmentReward;
	}




}
