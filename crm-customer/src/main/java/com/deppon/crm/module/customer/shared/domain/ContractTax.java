package com.deppon.crm.module.customer.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
* @ClassName: ContractTax 发票标记信息
* @Description: 添加合同中签署合同公司和发票标记两个字段
* @author chenaichun
* @date 2013-11-15 上午9:57:08
*
 */
public class ContractTax extends BaseEntity {

	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = 1L;

	//对应合同ID
	private String contractId;

	//签署合同公司
	private String signCompany;
	//发票标记
	private String invoiceType;
	//发票开始时间
	private Date beginTime;
	//发票结束时间
	private Date endTime;
	
	/**
	 *@author chenaichun
	 * @date 2013-11-15 下午5:24:17 
	 *@return the contractId
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 *@author chenaichun
	 * @date 2013-11-15 下午5:24:17 
	 * @param contractId the contractId to set
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 *@author chenaichun
	 * @date 2013-11-15 上午10:00:16 
	 *@return the signCompany
	 */
	public String getSignCompany() {
		return signCompany;
	}
	/**
	 *@author chenaichun
	 * @date 2013-11-15 上午10:00:16 
	 * @param signCompany the signCompany to set
	 */
	public void setSignCompany(String signCompany) {
		this.signCompany = signCompany;
	}
	/**
	 *@author chenaichun
	 * @date 2013-11-15 上午10:00:16 
	 *@return the invoiceType
	 */
	public String getInvoiceType() {
		return invoiceType;
	}
	/**
	 *@author chenaichun
	 * @date 2013-11-15 上午10:00:16 
	 * @param invoiceType the invoiceType to set
	 */
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	/**
	 *@author chenaichun
	 * @date 2013-11-15 上午10:00:16 
	 *@return the beginTime
	 */
	public Date getBeginTime() {
		return beginTime;
	}
	/**
	 *@author chenaichun
	 * @date 2013-11-15 上午10:00:16 
	 * @param beginTime the beginTime to set
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 *@author chenaichun
	 * @date 2013-11-15 上午10:00:16 
	 *@return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 *@author chenaichun
	 * @date 2013-11-15 上午10:00:16 
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 *@author chenaichun
	 * @date 2013-11-15 上午10:00:16 
	 *@return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @description:hashcode重写
	 *@author chenaichun
	 * @date 2013-11-15 上午10:00:16 
	 *@return the serialversionuid
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((beginTime == null) ? 0 : beginTime.hashCode());
		result = prime * result + ((contractId == null) ? 0 : contractId.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((invoiceType == null) ? 0 : invoiceType.hashCode());
		result = prime * result + ((signCompany == null) ? 0 : signCompany.hashCode());
		return result;
	}
	/**
	 * @description:equals重写
	 *@author chenaichun
	 * @date 2013-11-15 上午10:00:16 
	 *@return the serialversionuid
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContractTax other = (ContractTax) obj;
		if (beginTime == null) {
			if (other.beginTime != null)
				return false;
		} else if (!beginTime.equals(other.beginTime))
			return false;
		if (contractId == null) {
			if (other.contractId != null)
				return false;
		} else if (!contractId.equals(other.contractId))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (invoiceType == null) {
			if (other.invoiceType != null)
				return false;
		} else if (!invoiceType.equals(other.invoiceType))
			return false;
		if (signCompany == null) {
			if (other.signCompany != null)
				return false;
		} else if (!signCompany.equals(other.signCompany))
			return false;
		return true;
	}
}
