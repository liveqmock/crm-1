package com.deppon.crm.module.recompense.shared.domain;

/**
 * 
 * <p>
 * Description:付款单<br />
 * </p>
 * 
 * @title PaymentBill.java
 * @package com.deppon.crm.module.recompense.shared.domain
 * @author roy
 * @version 0.1 2013-1-21
 */
public class PaymentBill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4689042023732658501L;
	// 编号
	private String id;
	// 理赔单关联Id
	private String recompenseId;
	// 所属财务部（不需要）
	private Finance belongfinance;
	// 冲应收
	private boolean receivable;
	// 冲应金额
	private Double receivableAmount;
	// 冲到付
	private boolean freightCollect;
	// 冲到金额
	private Double freightCollectAmount;
	// 付款方式
	private String paymentType;
	// 付款金额
	private Double paymentAmount;
	// 银行账户信息
	private BankAccount bankAccount = new BankAccount();
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
	 * Description:belongfinance<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Finance getBelongfinance() {
		return belongfinance;
	}
	/**
	 * <p>
	 * Description:belongfinance<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setBelongfinance(Finance belongfinance) {
		this.belongfinance = belongfinance;
	}
	/**
	 * <p>
	 * Description:receivable<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public boolean isReceivable() {
		return receivable;
	}
	/**
	 * <p>
	 * Description:receivable<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setReceivable(boolean receivable) {
		this.receivable = receivable;
	}
	/**
	 * <p>
	 * Description:receivableAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Double getReceivableAmount() {
		return receivableAmount;
	}
	/**
	 * <p>
	 * Description:receivableAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setReceivableAmount(Double receivableAmount) {
		this.receivableAmount = receivableAmount;
	}
	/**
	 * <p>
	 * Description:freightCollect<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public boolean isFreightCollect() {
		return freightCollect;
	}
	/**
	 * <p>
	 * Description:freightCollect<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setFreightCollect(boolean freightCollect) {
		this.freightCollect = freightCollect;
	}
	/**
	 * <p>
	 * Description:freightCollectAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Double getFreightCollectAmount() {
		return freightCollectAmount;
	}
	/**
	 * <p>
	 * Description:freightCollectAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setFreightCollectAmount(Double freightCollectAmount) {
		this.freightCollectAmount = freightCollectAmount;
	}
	/**
	 * <p>
	 * Description:paymentType<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getPaymentType() {
		return paymentType;
	}
	/**
	 * <p>
	 * Description:paymentType<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	/**
	 * <p>
	 * Description:paymentAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Double getPaymentAmount() {
		return paymentAmount;
	}
	/**
	 * <p>
	 * Description:paymentAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	/**
	 * <p>
	 * Description:bankAccount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public BankAccount getBankAccount() {
		return bankAccount;
	}
	/**
	 * <p>
	 * Description:bankAccount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}


}
