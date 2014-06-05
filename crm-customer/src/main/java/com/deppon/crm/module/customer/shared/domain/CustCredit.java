package com.deppon.crm.module.customer.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @作者：andy
 * @时间：2014-3-7
 * @功能：客户信用
 * @修改：
 * @功能：客户信用信息
 * @时间：2014-3-7
 * 
 */
public class CustCredit extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3986492456262754555L;
	
	// id
	private String id;
	
	// 客户id
	private String custId;
	
	// 客户名称
	private String custName;
	
	// 客户编码
	private String custNumber;
	
	// 经营本部id
	private String managementDeptId;
	
	// 经营本部名称
	private String managementDeptName;
	
	// 事业部id
	private String busDeptId;
	
	// 事业部名称
	private String busDeptName;
	
	// 零担大区id
	private String bigAreaDeptId;
	
	// 零担大区名称
	private String bigAreaDeptName;
	
	// 所属营业区id
	private String areaDeptId;
	
	// 所属营业区名称
	private String areaDeptName;
	
	// 归属部门id
	private String attributionDeptId;
	
	// 归属部门名称
	private String attributionDeptName;
	
	// 责任部门id
	private String responsibilityDeptId;
	
	// 责任部门名称
	private String responsibilityDeptName;
	
	// 是否月结(1-是,0-否)
	private byte isMonthlyCust;
	
	// 零担结款方式
	private String payway;
	
	// 快递结款方式
	private String exPayWay;
	
	// 未还款类型
	private String norepaymentType;
	
	// 未还款类型名称
	private String norepaymentTypeName;
	
	// 未还款类型(1,2,3,4,5)
	private String norepaymentTypeOne;
	private String norepaymentTypeTwo;
	private String norepaymentTypeThree;
	private String norepaymentTypeFour;
	private String norepaymentTypeFive;
	
	// 合同结款日期 
	private int repaymentDate;
	
	// 月结天数
	private int debtdays;
	
	// 临欠信用额度
	private double overdraftCreditAmount;
	
	// 月结信用额度
	private double monthCreditAmount;
	
	// 临欠信用额度余额
	private double overdraftCreditBalanceAmount;
		
	// 月结信用额度余额
	private double monthCreditBalanceAmount;
	
	// 临欠当期应收金额
	private double overdrftRecivableAmount;
	
	// 月结当期应收金额
	private double lttRecivableAmount;
	
	// 临欠应收总额
	private double tdreceivableAmount;
	
	// 月结应收总额
	private double monReceivableAmount;
	
	// 临欠信用额度使用率
	private String overdraftCreditUserate;
		
	// 月结信用额度使用率
	private String monthCreditUserate;
	
	// 应收总额
	private double totalReceivableAmount;
	
	// 部门临欠额度
	private double deptCredit;
	
	// 部门应收总额
	private double receivableAmount;
	
	// 部门当期临欠应收总额
	private double deptCurdebtReceivableAmount;
	
	// 信用预警次数
	private int creditTimes;
	
	// 最后一次预警时间
	private Date lastWarnTime;
	
	// 是否信用较差客户(1-是    0-否)
	private byte isPoorCredit;
	
	// 最早一笔未还临欠开单时间
	private Date temporarydebitDate;

	// 最早一笔未还月结开单时间
	private Date nomonthlyDate;
	
	// 创建时间
	private Date createTime;
	
	// 创建人
	private String createUserId;
	
	// 最后一次修改时间
	private Date lastUpdateTime;
	
	// 最后一次修改人
	private String lastUpdateUserId;
	
	// 本月15号日期
	private Date excelDay;
	
	// 客户信用报表查询开始日期
	private String beginDate;
	
	// 客户信用报表查询结束日期
	private String endDate;


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = prime * result
				+ ((areaDeptId == null) ? 0 : areaDeptId.hashCode());
		result = prime * result
				+ ((areaDeptName == null) ? 0 : areaDeptName.hashCode());
		result = prime
				* result
				+ ((attributionDeptId == null) ? 0 : attributionDeptId
						.hashCode());
		result = prime
				* result
				+ ((attributionDeptName == null) ? 0 : attributionDeptName
						.hashCode());
		result = prime * result
				+ ((bigAreaDeptId == null) ? 0 : bigAreaDeptId.hashCode());
		result = prime * result
				+ ((bigAreaDeptName == null) ? 0 : bigAreaDeptName.hashCode());
		result = prime * result
				+ ((busDeptId == null) ? 0 : busDeptId.hashCode());
		result = prime * result
				+ ((busDeptName == null) ? 0 : busDeptName.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result
				+ ((createUserId == null) ? 0 : createUserId.hashCode());
		result = prime * result + creditTimes;
		result = prime * result + ((custId == null) ? 0 : custId.hashCode());
		result = prime * result
				+ ((custName == null) ? 0 : custName.hashCode());
		result = prime * result
				+ ((custNumber == null) ? 0 : custNumber.hashCode());
		long temp;
		temp = Double.doubleToLongBits(deptCredit);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(deptCurdebtReceivableAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((exPayWay == null) ? 0 : exPayWay.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + isMonthlyCust;
		result = prime * result + isPoorCredit;
		result = prime * result
				+ ((lastUpdateTime == null) ? 0 : lastUpdateTime.hashCode());
		result = prime
				* result
				+ ((lastUpdateUserId == null) ? 0 : lastUpdateUserId.hashCode());
		result = prime * result
				+ ((lastWarnTime == null) ? 0 : lastWarnTime.hashCode());
		temp = Double.doubleToLongBits(lttRecivableAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime
				* result
				+ ((managementDeptId == null) ? 0 : managementDeptId.hashCode());
		result = prime
				* result
				+ ((managementDeptName == null) ? 0 : managementDeptName
						.hashCode());
		temp = Double.doubleToLongBits(monReceivableAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(monthCreditAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(monthCreditBalanceAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime
				* result
				+ ((monthCreditUserate == null) ? 0 : monthCreditUserate
						.hashCode());
		result = prime * result
				+ ((nomonthlyDate == null) ? 0 : nomonthlyDate.hashCode());
		temp = Double.doubleToLongBits(overdraftCreditAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(overdraftCreditBalanceAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime
				* result
				+ ((overdraftCreditUserate == null) ? 0
						: overdraftCreditUserate.hashCode());
		temp = Double.doubleToLongBits(overdrftRecivableAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((payway == null) ? 0 : payway.hashCode());
		temp = Double.doubleToLongBits(receivableAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + repaymentDate;
		result = prime
				* result
				+ ((responsibilityDeptId == null) ? 0 : responsibilityDeptId
						.hashCode());
		result = prime
				* result
				+ ((responsibilityDeptName == null) ? 0
						: responsibilityDeptName.hashCode());
		temp = Double.doubleToLongBits(tdreceivableAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime
				* result
				+ ((temporarydebitDate == null) ? 0 : temporarydebitDate
						.hashCode());
		temp = Double.doubleToLongBits(totalReceivableAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustCredit other = (CustCredit) obj;
		if (areaDeptId == null) {
			if (other.areaDeptId != null)
				return false;
		} else if (!areaDeptId.equals(other.areaDeptId))
			return false;
		if (areaDeptName == null) {
			if (other.areaDeptName != null)
				return false;
		} else if (!areaDeptName.equals(other.areaDeptName))
			return false;
		if (attributionDeptId == null) {
			if (other.attributionDeptId != null)
				return false;
		} else if (!attributionDeptId.equals(other.attributionDeptId))
			return false;
		if (attributionDeptName == null) {
			if (other.attributionDeptName != null)
				return false;
		} else if (!attributionDeptName.equals(other.attributionDeptName))
			return false;
		if (bigAreaDeptId == null) {
			if (other.bigAreaDeptId != null)
				return false;
		} else if (!bigAreaDeptId.equals(other.bigAreaDeptId))
			return false;
		if (bigAreaDeptName == null) {
			if (other.bigAreaDeptName != null)
				return false;
		} else if (!bigAreaDeptName.equals(other.bigAreaDeptName))
			return false;
		if (busDeptId == null) {
			if (other.busDeptId != null)
				return false;
		} else if (!busDeptId.equals(other.busDeptId))
			return false;
		if (busDeptName == null) {
			if (other.busDeptName != null)
				return false;
		} else if (!busDeptName.equals(other.busDeptName))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (createUserId == null) {
			if (other.createUserId != null)
				return false;
		} else if (!createUserId.equals(other.createUserId))
			return false;
		if (creditTimes != other.creditTimes)
			return false;
		if (custId == null) {
			if (other.custId != null)
				return false;
		} else if (!custId.equals(other.custId))
			return false;
		if (custName == null) {
			if (other.custName != null)
				return false;
		} else if (!custName.equals(other.custName))
			return false;
		if (custNumber == null) {
			if (other.custNumber != null)
				return false;
		} else if (!custNumber.equals(other.custNumber))
			return false;
		if (Double.doubleToLongBits(deptCredit) != Double
				.doubleToLongBits(other.deptCredit))
			return false;
		if (Double.doubleToLongBits(deptCurdebtReceivableAmount) != Double
				.doubleToLongBits(other.deptCurdebtReceivableAmount))
			return false;
		if (exPayWay == null) {
			if (other.exPayWay != null)
				return false;
		} else if (!exPayWay.equals(other.exPayWay))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isMonthlyCust != other.isMonthlyCust)
			return false;
		if (isPoorCredit != other.isPoorCredit)
			return false;
		if (lastUpdateTime == null) {
			if (other.lastUpdateTime != null)
				return false;
		} else if (!lastUpdateTime.equals(other.lastUpdateTime))
			return false;
		if (lastUpdateUserId == null) {
			if (other.lastUpdateUserId != null)
				return false;
		} else if (!lastUpdateUserId.equals(other.lastUpdateUserId))
			return false;
		if (lastWarnTime == null) {
			if (other.lastWarnTime != null)
				return false;
		} else if (!lastWarnTime.equals(other.lastWarnTime))
			return false;
		if (Double.doubleToLongBits(lttRecivableAmount) != Double
				.doubleToLongBits(other.lttRecivableAmount))
			return false;
		if (managementDeptId == null) {
			if (other.managementDeptId != null)
				return false;
		} else if (!managementDeptId.equals(other.managementDeptId))
			return false;
		if (managementDeptName == null) {
			if (other.managementDeptName != null)
				return false;
		} else if (!managementDeptName.equals(other.managementDeptName))
			return false;
		if (Double.doubleToLongBits(monReceivableAmount) != Double
				.doubleToLongBits(other.monReceivableAmount))
			return false;
		if (Double.doubleToLongBits(monthCreditAmount) != Double
				.doubleToLongBits(other.monthCreditAmount))
			return false;
		if (Double.doubleToLongBits(monthCreditBalanceAmount) != Double
				.doubleToLongBits(other.monthCreditBalanceAmount))
			return false;
		if (monthCreditUserate == null) {
			if (other.monthCreditUserate != null)
				return false;
		} else if (!monthCreditUserate.equals(other.monthCreditUserate))
			return false;
		if (nomonthlyDate == null) {
			if (other.nomonthlyDate != null)
				return false;
		} else if (!nomonthlyDate.equals(other.nomonthlyDate))
			return false;
		if (Double.doubleToLongBits(overdraftCreditAmount) != Double
				.doubleToLongBits(other.overdraftCreditAmount))
			return false;
		if (Double.doubleToLongBits(overdraftCreditBalanceAmount) != Double
				.doubleToLongBits(other.overdraftCreditBalanceAmount))
			return false;
		if (overdraftCreditUserate == null) {
			if (other.overdraftCreditUserate != null)
				return false;
		} else if (!overdraftCreditUserate.equals(other.overdraftCreditUserate))
			return false;
		if (Double.doubleToLongBits(overdrftRecivableAmount) != Double
				.doubleToLongBits(other.overdrftRecivableAmount))
			return false;
		if (payway == null) {
			if (other.payway != null)
				return false;
		} else if (!payway.equals(other.payway))
			return false;
		if (Double.doubleToLongBits(receivableAmount) != Double
				.doubleToLongBits(other.receivableAmount))
			return false;
		if (repaymentDate != other.repaymentDate)
			return false;
		if (responsibilityDeptId == null) {
			if (other.responsibilityDeptId != null)
				return false;
		} else if (!responsibilityDeptId.equals(other.responsibilityDeptId))
			return false;
		if (responsibilityDeptName == null) {
			if (other.responsibilityDeptName != null)
				return false;
		} else if (!responsibilityDeptName.equals(other.responsibilityDeptName))
			return false;
		if (Double.doubleToLongBits(tdreceivableAmount) != Double
				.doubleToLongBits(other.tdreceivableAmount))
			return false;
		if (temporarydebitDate == null) {
			if (other.temporarydebitDate != null)
				return false;
		} else if (!temporarydebitDate.equals(other.temporarydebitDate))
			return false;
		if (Double.doubleToLongBits(totalReceivableAmount) != Double
				.doubleToLongBits(other.totalReceivableAmount))
			return false;
		return true;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:39
	 * @return id : return the property id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id : set the property id.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:39
	 * @return custId : return the property custId.
	 */
	public String getCustId() {
		return custId;
	}

	/**
	 * @param custId : set the property custId.
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:39
	 * @return custName : return the property custName.
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * @param custName : set the property custName.
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:39
	 * @return custNumber : return the property custNumber.
	 */
	public String getCustNumber() {
		return custNumber;
	}

	/**
	 * @param custNumber : set the property custNumber.
	 */
	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:39
	 * @return managementDeptId : return the property managementDeptId.
	 */
	public String getManagementDeptId() {
		return managementDeptId;
	}

	/**
	 * @param managementDeptId : set the property managementDeptId.
	 */
	public void setManagementDeptId(String managementDeptId) {
		this.managementDeptId = managementDeptId;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:39
	 * @return managementDeptName : return the property managementDeptName.
	 */
	public String getManagementDeptName() {
		return managementDeptName;
	}

	/**
	 * @param managementDeptName : set the property managementDeptName.
	 */
	public void setManagementDeptName(String managementDeptName) {
		this.managementDeptName = managementDeptName;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:39
	 * @return busDeptId : return the property busDeptId.
	 */
	public String getBusDeptId() {
		return busDeptId;
	}

	/**
	 * @param busDeptId : set the property busDeptId.
	 */
	public void setBusDeptId(String busDeptId) {
		this.busDeptId = busDeptId;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:39
	 * @return busDeptName : return the property busDeptName.
	 */
	public String getBusDeptName() {
		return busDeptName;
	}

	/**
	 * @param busDeptName : set the property busDeptName.
	 */
	public void setBusDeptName(String busDeptName) {
		this.busDeptName = busDeptName;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:39
	 * @return bigAreaDeptId : return the property bigAreaDeptId.
	 */
	public String getBigAreaDeptId() {
		return bigAreaDeptId;
	}

	/**
	 * @param bigAreaDeptId : set the property bigAreaDeptId.
	 */
	public void setBigAreaDeptId(String bigAreaDeptId) {
		this.bigAreaDeptId = bigAreaDeptId;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:39
	 * @return bigAreaDeptName : return the property bigAreaDeptName.
	 */
	public String getBigAreaDeptName() {
		return bigAreaDeptName;
	}

	/**
	 * @param bigAreaDeptName : set the property bigAreaDeptName.
	 */
	public void setBigAreaDeptName(String bigAreaDeptName) {
		this.bigAreaDeptName = bigAreaDeptName;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:39
	 * @return areaDeptId : return the property areaDeptId.
	 */
	public String getAreaDeptId() {
		return areaDeptId;
	}

	/**
	 * @param areaDeptId : set the property areaDeptId.
	 */
	public void setAreaDeptId(String areaDeptId) {
		this.areaDeptId = areaDeptId;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:39
	 * @return areaDeptName : return the property areaDeptName.
	 */
	public String getAreaDeptName() {
		return areaDeptName;
	}

	/**
	 * @param areaDeptName : set the property areaDeptName.
	 */
	public void setAreaDeptName(String areaDeptName) {
		this.areaDeptName = areaDeptName;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:39
	 * @return attributionDeptId : return the property attributionDeptId.
	 */
	public String getAttributionDeptId() {
		return attributionDeptId;
	}

	/**
	 * @param attributionDeptId : set the property attributionDeptId.
	 */
	public void setAttributionDeptId(String attributionDeptId) {
		this.attributionDeptId = attributionDeptId;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return attributionDeptName : return the property attributionDeptName.
	 */
	public String getAttributionDeptName() {
		return attributionDeptName;
	}

	/**
	 * @param attributionDeptName : set the property attributionDeptName.
	 */
	public void setAttributionDeptName(String attributionDeptName) {
		this.attributionDeptName = attributionDeptName;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return responsibilityDeptId : return the property responsibilityDeptId.
	 */
	public String getResponsibilityDeptId() {
		return responsibilityDeptId;
	}

	/**
	 * @param responsibilityDeptId : set the property responsibilityDeptId.
	 */
	public void setResponsibilityDeptId(String responsibilityDeptId) {
		this.responsibilityDeptId = responsibilityDeptId;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return responsibilityDeptName : return the property responsibilityDeptName.
	 */
	public String getResponsibilityDeptName() {
		return responsibilityDeptName;
	}

	/**
	 * @param responsibilityDeptName : set the property responsibilityDeptName.
	 */
	public void setResponsibilityDeptName(String responsibilityDeptName) {
		this.responsibilityDeptName = responsibilityDeptName;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return isMonthlyCust : return the property isMonthlyCust.
	 */
	public byte getIsMonthlyCust() {
		return isMonthlyCust;
	}

	/**
	 * @param isMonthlyCust : set the property isMonthlyCust.
	 */
	public void setIsMonthlyCust(byte isMonthlyCust) {
		this.isMonthlyCust = isMonthlyCust;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return payway : return the property payway.
	 */
	public String getPayway() {
		return payway;
	}

	/**
	 * @param payway : set the property payway.
	 */
	public void setPayway(String payway) {
		this.payway = payway;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return exPayWay : return the property exPayWay.
	 */
	public String getExPayWay() {
		return exPayWay;
	}

	/**
	 * @param exPayWay : set the property exPayWay.
	 */
	public void setExPayWay(String exPayWay) {
		this.exPayWay = exPayWay;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return norepaymentType : return the property norepaymentType.
	 */
	public String getNorepaymentType() {
		return norepaymentType;
	}

	/**
	 * @param norepaymentType : set the property norepaymentType.
	 */
	public void setNorepaymentType(String norepaymentType) {
		this.norepaymentType = norepaymentType;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return norepaymentTypeName : return the property norepaymentTypeName.
	 */
	public String getNorepaymentTypeName() {
		return norepaymentTypeName;
	}

	/**
	 * @param norepaymentTypeName : set the property norepaymentTypeName.
	 */
	public void setNorepaymentTypeName(String norepaymentTypeName) {
		this.norepaymentTypeName = norepaymentTypeName;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return repaymentDate : return the property repaymentDate.
	 */
	public int getRepaymentDate() {
		return repaymentDate;
	}

	/**
	 * @param repaymentDate : set the property repaymentDate.
	 */
	public void setRepaymentDate(int repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return debtdays : return the property debtdays.
	 */
	public int getDebtdays() {
		return debtdays;
	}

	/**
	 * @param debtdays : set the property debtdays.
	 */
	public void setDebtdays(int debtdays) {
		this.debtdays = debtdays;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return overdraftCreditAmount : return the property overdraftCreditAmount.
	 */
	public double getOverdraftCreditAmount() {
		return overdraftCreditAmount;
	}

	/**
	 * @param overdraftCreditAmount : set the property overdraftCreditAmount.
	 */
	public void setOverdraftCreditAmount(double overdraftCreditAmount) {
		this.overdraftCreditAmount = overdraftCreditAmount;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return monthCreditAmount : return the property monthCreditAmount.
	 */
	public double getMonthCreditAmount() {
		return monthCreditAmount;
	}

	/**
	 * @param monthCreditAmount : set the property monthCreditAmount.
	 */
	public void setMonthCreditAmount(double monthCreditAmount) {
		this.monthCreditAmount = monthCreditAmount;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return overdraftCreditBalanceAmount : return the property overdraftCreditBalanceAmount.
	 */
	public double getOverdraftCreditBalanceAmount() {
		return overdraftCreditBalanceAmount;
	}

	/**
	 * @param overdraftCreditBalanceAmount : set the property overdraftCreditBalanceAmount.
	 */
	public void setOverdraftCreditBalanceAmount(double overdraftCreditBalanceAmount) {
		this.overdraftCreditBalanceAmount = overdraftCreditBalanceAmount;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return monthCreditBalanceAmount : return the property monthCreditBalanceAmount.
	 */
	public double getMonthCreditBalanceAmount() {
		return monthCreditBalanceAmount;
	}

	/**
	 * @param monthCreditBalanceAmount : set the property monthCreditBalanceAmount.
	 */
	public void setMonthCreditBalanceAmount(double monthCreditBalanceAmount) {
		this.monthCreditBalanceAmount = monthCreditBalanceAmount;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return overdrftRecivableAmount : return the property overdrftRecivableAmount.
	 */
	public double getOverdrftRecivableAmount() {
		return overdrftRecivableAmount;
	}

	/**
	 * @param overdrftRecivableAmount : set the property overdrftRecivableAmount.
	 */
	public void setOverdrftRecivableAmount(double overdrftRecivableAmount) {
		this.overdrftRecivableAmount = overdrftRecivableAmount;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return lttRecivableAmount : return the property lttRecivableAmount.
	 */
	public double getLttRecivableAmount() {
		return lttRecivableAmount;
	}

	/**
	 * @param lttRecivableAmount : set the property lttRecivableAmount.
	 */
	public void setLttRecivableAmount(double lttRecivableAmount) {
		this.lttRecivableAmount = lttRecivableAmount;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return tdreceivableAmount : return the property tdreceivableAmount.
	 */
	public double getTdreceivableAmount() {
		return tdreceivableAmount;
	}

	/**
	 * @param tdreceivableAmount : set the property tdreceivableAmount.
	 */
	public void setTdreceivableAmount(double tdreceivableAmount) {
		this.tdreceivableAmount = tdreceivableAmount;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return monReceivableAmount : return the property monReceivableAmount.
	 */
	public double getMonReceivableAmount() {
		return monReceivableAmount;
	}

	/**
	 * @param monReceivableAmount : set the property monReceivableAmount.
	 */
	public void setMonReceivableAmount(double monReceivableAmount) {
		this.monReceivableAmount = monReceivableAmount;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return overdraftCreditUserate : return the property overdraftCreditUserate.
	 */
	public String getOverdraftCreditUserate() {
		return overdraftCreditUserate;
	}

	/**
	 * @param overdraftCreditUserate : set the property overdraftCreditUserate.
	 */
	public void setOverdraftCreditUserate(String overdraftCreditUserate) {
		this.overdraftCreditUserate = overdraftCreditUserate;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return monthCreditUserate : return the property monthCreditUserate.
	 */
	public String getMonthCreditUserate() {
		return monthCreditUserate;
	}

	/**
	 * @param monthCreditUserate : set the property monthCreditUserate.
	 */
	public void setMonthCreditUserate(String monthCreditUserate) {
		this.monthCreditUserate = monthCreditUserate;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return totalReceivableAmount : return the property totalReceivableAmount.
	 */
	public double getTotalReceivableAmount() {
		return totalReceivableAmount;
	}

	/**
	 * @param totalReceivableAmount : set the property totalReceivableAmount.
	 */
	public void setTotalReceivableAmount(double totalReceivableAmount) {
		this.totalReceivableAmount = totalReceivableAmount;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return deptCredit : return the property deptCredit.
	 */
	public double getDeptCredit() {
		return deptCredit;
	}

	/**
	 * @param deptCredit : set the property deptCredit.
	 */
	public void setDeptCredit(double deptCredit) {
		this.deptCredit = deptCredit;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return receivableAmount : return the property receivableAmount.
	 */
	public double getReceivableAmount() {
		return receivableAmount;
	}

	/**
	 * @param receivableAmount : set the property receivableAmount.
	 */
	public void setReceivableAmount(double receivableAmount) {
		this.receivableAmount = receivableAmount;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return deptCurdebtReceivableAmount : return the property deptCurdebtReceivableAmount.
	 */
	public double getDeptCurdebtReceivableAmount() {
		return deptCurdebtReceivableAmount;
	}

	/**
	 * @param deptCurdebtReceivableAmount : set the property deptCurdebtReceivableAmount.
	 */
	public void setDeptCurdebtReceivableAmount(double deptCurdebtReceivableAmount) {
		this.deptCurdebtReceivableAmount = deptCurdebtReceivableAmount;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return creditTimes : return the property creditTimes.
	 */
	public int getCreditTimes() {
		return creditTimes;
	}

	/**
	 * @param creditTimes : set the property creditTimes.
	 */
	public void setCreditTimes(int creditTimes) {
		this.creditTimes = creditTimes;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return lastWarnTime : return the property lastWarnTime.
	 */
	public Date getLastWarnTime() {
		return lastWarnTime;
	}

	/**
	 * @param lastWarnTime : set the property lastWarnTime.
	 */
	public void setLastWarnTime(Date lastWarnTime) {
		this.lastWarnTime = lastWarnTime;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return isPoorCredit : return the property isPoorCredit.
	 */
	public byte getIsPoorCredit() {
		return isPoorCredit;
	}

	/**
	 * @param isPoorCredit : set the property isPoorCredit.
	 */
	public void setIsPoorCredit(byte isPoorCredit) {
		this.isPoorCredit = isPoorCredit;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return temporarydebitDate : return the property temporarydebitDate.
	 */
	public Date getTemporarydebitDate() {
		return temporarydebitDate;
	}

	/**
	 * @param temporarydebitDate : set the property temporarydebitDate.
	 */
	public void setTemporarydebitDate(Date temporarydebitDate) {
		this.temporarydebitDate = temporarydebitDate;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return nomonthlyDate : return the property nomonthlyDate.
	 */
	public Date getNomonthlyDate() {
		return nomonthlyDate;
	}

	/**
	 * @param nomonthlyDate : set the property nomonthlyDate.
	 */
	public void setNomonthlyDate(Date nomonthlyDate) {
		this.nomonthlyDate = nomonthlyDate;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return createTime : return the property createTime.
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime : set the property createTime.
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return createUserId : return the property createUserId.
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId : set the property createUserId.
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return lastUpdateTime : return the property lastUpdateTime.
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**
	 * @param lastUpdateTime : set the property lastUpdateTime.
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return lastUpdateUserId : return the property lastUpdateUserId.
	 */
	public String getLastUpdateUserId() {
		return lastUpdateUserId;
	}

	/**
	 * @param lastUpdateUserId : set the property lastUpdateUserId.
	 */
	public void setLastUpdateUserId(String lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return excelDay : return the property excelDay.
	 */
	public Date getExcelDay() {
		return excelDay;
	}

	/**
	 * @param excelDay : set the property excelDay.
	 */
	public void setExcelDay(Date excelDay) {
		this.excelDay = excelDay;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return beginDate : return the property beginDate.
	 */
	public String getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate : set the property beginDate.
	 */
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:21:40
	 * @return endDate : return the property endDate.
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate : set the property endDate.
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the norepaymentTypeOne
	 */
	public String getNorepaymentTypeOne() {
		return norepaymentTypeOne;
	}

	/**
	 * @param norepaymentTypeOne the norepaymentTypeOne to set
	 */
	public void setNorepaymentTypeOne(String norepaymentTypeOne) {
		this.norepaymentTypeOne = norepaymentTypeOne;
	}

	/**
	 * @return the norepaymentTypeTwo
	 */
	public String getNorepaymentTypeTwo() {
		return norepaymentTypeTwo;
	}

	/**
	 * @param norepaymentTypeTwo the norepaymentTypeTwo to set
	 */
	public void setNorepaymentTypeTwo(String norepaymentTypeTwo) {
		this.norepaymentTypeTwo = norepaymentTypeTwo;
	}

	/**
	 * @return the norepaymentTypeThree
	 */
	public String getNorepaymentTypeThree() {
		return norepaymentTypeThree;
	}

	/**
	 * @param norepaymentTypeThree the norepaymentTypeThree to set
	 */
	public void setNorepaymentTypeThree(String norepaymentTypeThree) {
		this.norepaymentTypeThree = norepaymentTypeThree;
	}

	/**
	 * @return the norepaymentTypeFour
	 */
	public String getNorepaymentTypeFour() {
		return norepaymentTypeFour;
	}

	/**
	 * @param norepaymentTypeFour the norepaymentTypeFour to set
	 */
	public void setNorepaymentTypeFour(String norepaymentTypeFour) {
		this.norepaymentTypeFour = norepaymentTypeFour;
	}

	/**
	 * @return the norepaymentTypeFive
	 */
	public String getNorepaymentTypeFive() {
		return norepaymentTypeFive;
	}

	/**
	 * @param norepaymentTypeFive the norepaymentTypeFive to set
	 */
	public void setNorepaymentTypeFive(String norepaymentTypeFive) {
		this.norepaymentTypeFive = norepaymentTypeFive;
	}
	
}
