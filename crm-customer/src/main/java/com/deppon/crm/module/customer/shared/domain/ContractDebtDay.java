package com.deppon.crm.module.customer.shared.domain;

import java.util.Date;

/**   
 * <p>
 * Description:合同欠款天数<br />
 * </p>
 * @title ContractDebtDay.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author 李国文
 * @version 0.1 2013-1-9
 */
public class ContractDebtDay {

	//主键
	private String id;
	//客户编码
	private String custNum;
	//客户名字
	private String custName;
	//实际使用额度
	private Double usedAmount;
	//最早账龄发生日期
	private Date longestDebtDate;
	//创建时间
	private Date createDate;
	//最近更新时间
	private Date modifyDate;
	//最后修改人
	private String modifyUser;
	//剩余天数
	private int remainDays;
	/**
	 * 
	 * Description:id<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public String getId() {
		return id;
	}
	/**
	 * 
	 * Description:id<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 
	 * Description:custNum<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public String getCustNum() {
		return custNum;
	}
	/**
	 * 
	 * Description:custNum<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public void setCustNum(String custNum) {
		this.custNum = custNum;
	}
	/**
	 * 
	 * Description:custName<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * 
	 * Description:custName<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * 
	 * Description:usedAmount<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public Double getUsedAmount() {
		return usedAmount;
	}
	/**
	 * 
	 * Description:usedAmount<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public void setUsedAmount(Double usedAmount) {
		this.usedAmount = usedAmount;
	}
	/**
	 * 
	 * Description:longestDebtDate<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public Date getLongestDebtDate() {
		return longestDebtDate;
	}
	/**
	 * 
	 * Description:longestDebtDate<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public void setLongestDebtDate(Date longestDebtDate) {
		this.longestDebtDate = longestDebtDate;
	}
	/**
	 * 
	 * Description:createDate<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 
	 * Description:createDate<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 
	 * Description:modifyDate<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public Date getModifyDate() {
		return modifyDate;
	}
	/**
	 * 
	 * Description:modifyDate<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	/**
	 * 
	 * Description:modifyUser<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public String getModifyUser() {
		return modifyUser;
	}
	/**
	 * 
	 * Description:modifyUser<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	/**
	 * 
	 * Description:remainDays<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public int getRemainDays() {
		return remainDays;
	}
	/**
	 * 
	 * Description:remainDays<br />
	 * @author 唐亮
	 * @version 0.1 2013-5-27
	 */
	public void setRemainDays(int remainDays) {
		this.remainDays = remainDays;
	}
	
	
	
	
}
