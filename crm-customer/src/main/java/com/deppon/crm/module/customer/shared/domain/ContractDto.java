/**   
 * @title ContractDto.java
 * @package com.deppon.crm.module.customer.shared.domain
 * @description 封装合同数据和合同中客户近三个月发货金额  
 * @author 潘光均
 * @update 2013-3-12 上午9:25:06
 * @version V1.0   
 */
package com.deppon.crm.module.customer.shared.domain;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ContractDto.
 *
 * @description 合同dto，主要用户封装合同数据和合同中客户近三个月发货金额
 * @author 潘光均
 * @version 0.1 2013-3-12
 * @date 2013-3-12
 */

public class ContractDto extends Contract {
	
	/** The Constant serialVersionUID. @fields serialVersionUID */ 
	
	private static final long serialVersionUID = -4119170531723694010L;
	//客户发货金额月汇总
	/** The month sums. */
	private List<CustMonthSum> monthSums;
	//快递月发货金额汇总
	private List<CustMonthSum> exMonthSums;
	/**
	 * Gets the month sums.
	 *
	 * @return the month sums
	 */
	public List<CustMonthSum> getMonthSums() {
		return monthSums;
	}

	/**
	 * Sets the month sums.
	 *
	 * @param monthSums the new month sums
	 */
	public void setMonthSums(List<CustMonthSum> monthSums) {
		this.monthSums = monthSums;
	}

	/**
	 *@user pgj
	 *2013-9-22下午4:14:37
	 * @return exMonthSums : return the property exMonthSums.
	 */
	public List<CustMonthSum> getExMonthSums() {
		return exMonthSums;
	}

	/**
	 * @param exMonthSums : set the property exMonthSums.
	 */
	public void setExMonthSums(List<CustMonthSum> exMonthSums) {
		this.exMonthSums = exMonthSums;
	} 
}
