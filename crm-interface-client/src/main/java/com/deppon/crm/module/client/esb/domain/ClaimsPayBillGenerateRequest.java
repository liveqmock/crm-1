package com.deppon.crm.module.client.esb.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClaimsPayBillGenerateRequest", propOrder = { "claimType",
	    "claimWay",
	    "businessType",
	    "deptNo",
	    "custNo",
	    "claimMoney",
	    "billNo",
	    "creatorNo",
	    "responsibilityInfos",
	    "bankPayInfo","payBillLastTime","paymentCategory" })
/**
 * @作者：罗典
 * @描述：FOSS应付单申请实体信息
 * @时间：2012-11-22
 * */
public class ClaimsPayBillGenerateRequest {
	// 理赔类型： 1,异常签收理赔、2,丢货理赔,3，无
	protected int claimType;
	// 理赔方式: 1，正常理赔,2，快速理赔,3，在线理赔，4，无
	protected int claimWay;
	// 业务类型: 1，理赔、2，退运费、3，服务补救
	protected int businessType;
	// 部门标杆编码
	@XmlElement(required = true)
	protected String deptNo;
	// 客户编码
	@XmlElement(required = true)
	protected String custNo;
	// 金额
	@XmlElement(required = true)
	protected BigDecimal claimMoney;
	// 运单号
	@XmlElement(required = true)
	protected String billNo;
	// 创建人工号
	@XmlElement(required = true)
	protected String creatorNo;
	// 责任信息
	@XmlElement(required = true)
	protected List<ResponsibilityInfo> responsibilityInfos ;
	// 银行账户信息
	protected BankPayInfo bankPayInfo;
	// 最迟汇款日期
	@XmlSchemaType(name = "dateTime")
	protected Date payBillLastTime;
	
	//支付类别     现金：CH 电汇：TT  核销：W 核销后现金:WCH 核销后电汇:WTT
	@XmlElement(required = true)
	protected String paymentCategory;
	
	public void setResponsibilityInfos(
			List<ResponsibilityInfo> responsibilityInfos) {
		this.responsibilityInfos = responsibilityInfos;
	}

	/**
	 *@return  paymentCategory;
	 */
	public String getPaymentCategory() {
		return paymentCategory;
	}

	/**
	 * @param paymentCategory : set the property paymentCategory.
	 */
	public void setPaymentCategory(String paymentCategory) {
		this.paymentCategory = paymentCategory;
	}

	/**
	 * ��ȡclaimType���Ե�ֵ��
	 * 
	 */
	public int getClaimType() {
		return claimType;
	}

	/**
	 * ����claimType���Ե�ֵ��
	 * 
	 */
	public void setClaimType(int value) {
		this.claimType = value;
	}

	public Date getPayBillLastTime() {
		return payBillLastTime;
	}

	public void setPayBillLastTime(Date payBillLastTime) {
		this.payBillLastTime = payBillLastTime;
	}

	/**
	 * ��ȡclaimWay���Ե�ֵ��
	 * 
	 */
	public int getClaimWay() {
		return claimWay;
	}

	/**
	 * ����claimWay���Ե�ֵ��
	 * 
	 */
	public void setClaimWay(int value) {
		this.claimWay = value;
	}

	/**
	 * ��ȡbusinessType���Ե�ֵ��
	 * 
	 */
	public int getBusinessType() {
		return businessType;
	}

	/**
	 * ����businessType���Ե�ֵ��
	 * 
	 */
	public void setBusinessType(int value) {
		this.businessType = value;
	}

	/**
	 * ��ȡdeptNo���Ե�ֵ��
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDeptNo() {
		return deptNo;
	}

	/**
	 * ����deptNo���Ե�ֵ��
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDeptNo(String value) {
		this.deptNo = value;
	}

	/**
	 * ��ȡcustNo���Ե�ֵ��
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCustNo() {
		return custNo;
	}

	/**
	 * ����custNo���Ե�ֵ��
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCustNo(String value) {
		this.custNo = value;
	}

	/**
	 * ��ȡclaimMoney���Ե�ֵ��
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getClaimMoney() {
		return claimMoney;
	}

	/**
	 * ����claimMoney���Ե�ֵ��
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setClaimMoney(BigDecimal value) {
		this.claimMoney = value;
	}

	/**
	 * ��ȡbillNo���Ե�ֵ��
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBillNo() {
		return billNo;
	}

	/**
	 * ����billNo���Ե�ֵ��
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBillNo(String value) {
		this.billNo = value;
	}

	/**
	 * ��ȡcreatorNo���Ե�ֵ��
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreatorNo() {
		return creatorNo;
	}

	/**
	 * ����creatorNo���Ե�ֵ��
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreatorNo(String value) {
		this.creatorNo = value;
	}

	/**
	 * Gets the value of the responsibilityInfos property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the responsibilityInfos property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getResponsibilityInfos().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link ResponsibilityInfo }
	 * 
	 * 
	 */
	public List<ResponsibilityInfo> getResponsibilityInfos() {
		if (responsibilityInfos == null) {
			responsibilityInfos = new ArrayList<ResponsibilityInfo>();
		}
		return this.responsibilityInfos;
	}

	public BankPayInfo getBankPayInfo() {
		return bankPayInfo;
	}

	public void setBankPayInfo(BankPayInfo bankPayInfo) {
		this.bankPayInfo = bankPayInfo;
	}

}
