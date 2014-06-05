package com.deppon.crm.module.recompense.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * Description:冲账信息<br />
 * </p>
 * @title Balance.java
 * @package com.deppon.crm.module.recompense.shared.domain 
 * @author roy
 * @version 0.1 2013-1-21
 */
public class Balance extends BaseEntity {

	// 理赔单关联Id
	private String recompenseId;
	// 单据号
	private String billNum;
	// 未核销金额
	private Double usableAmount;
	// 冲账金额
	private Double balanceAmount;
	// 剩余金额
	private Double surplusAmount;
	// 客户编码（无需持久化）
	private String customerNum;
	// 客户名称（无需持久化）
	private String customerName;

	/**
	 * constructer
	 */
	public Balance() {
	}

	/**
	 * constructer
	 * 
	 * @param recompenseId
	 * @param billNum
	 * @param usableAmount
	 * @param balanceAmount
	 * @param surplusAmount
	 */
	public Balance(String recompenseId, String billNum, Double usableAmount,
			Double balanceAmount, Double surplusAmount) {
		this.recompenseId = recompenseId;
		this.billNum = billNum;
		this.usableAmount = usableAmount;
		this.balanceAmount = balanceAmount;
		this.surplusAmount = surplusAmount;
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
	 * Description:billNum<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getBillNum() {
		return billNum;
	}

	/**
	 * <p>
	 * Description:billNum<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}

	/**
	 * <p>
	 * Description:usableAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Double getUsableAmount() {
		return usableAmount;
	}

	/**
	 * <p>
	 * Description:usableAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setUsableAmount(Double usableAmount) {
		this.usableAmount = usableAmount;
	}

	/**
	 * <p>
	 * Description:balanceAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Double getBalanceAmount() {
		return balanceAmount;
	}

	/**
	 * <p>
	 * Description:balanceAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	/**
	 * <p>
	 * Description:surplusAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Double getSurplusAmount() {
		return surplusAmount;
	}

	/**
	 * <p>
	 * Description:surplusAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setSurplusAmount(Double surplusAmount) {
		this.surplusAmount = surplusAmount;
	}

	/**
	 * <p>
	 * Description:customerNum<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getCustomerNum() {
		return customerNum;
	}

	/**
	 * <p>
	 * Description:customerNum<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	/**
	 * <p>
	 * Description:customerName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * <p>
	 * Description:customerName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



}
