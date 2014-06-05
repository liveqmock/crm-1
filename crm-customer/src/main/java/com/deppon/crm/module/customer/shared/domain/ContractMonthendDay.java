package com.deppon.crm.module.customer.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:通用管理中，合同月结天数管理  类
 * 	使用于合同月结天数<br />
 * </p>
 * @title ContractDebtDay.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author 李国文
 * @version 0.1 2013-1-7
 */
public class ContractMonthendDay extends BaseEntity{

	//通用管理合同月结天数
	private int defaultDebtDays;
	//合同月结天数 名字---
	private String contractDebtDayName;
	//数据库还没有添加有效无效这个字段
	//1有效 无效0
	private String status;
	/**
	 * 
	 * Description:defaultDebtDays<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public int getDefaultDebtDays() {
		return defaultDebtDays;
	}
	/**
	 * 
	 * Description:defaultDebtDays<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public void setDefaultDebtDays(int defaultDebtDays) {
		this.defaultDebtDays = defaultDebtDays;
	}
	/**
	 * 
	 * Description:contractDebtDayName<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public String getContractDebtDayName() {
		return contractDebtDayName;
	}
	/**
	 * 
	 * Description:contractDebtDayName<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public void setContractDebtDayName(String contractDebtDayName) {
		this.contractDebtDayName = contractDebtDayName;
	}
	/**
	 * 
	 * Description:status<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 
	 * Description:status<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
