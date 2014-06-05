/**   
 * <p>
 * 针对CC营销信息推送实体<br />
 * </p>
 * @title CCMarketingInfo.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author 043260
 * @version 0.1 2014年4月6日
 */
package com.deppon.crm.module.marketing.shared.domain;

import java.io.Serializable;
import java.util.Date;

/**   
 * <p>
 * 针对CC营销信息推送实体<br />
 * </p>
 * @title CCMarketingInfo.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author 043260
 * @version 0.1 2014年4月6日
 */

public class CCPushMarketingInfo implements Serializable{

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 2958424932989583466L;
	//客户编码
	private String custNumber;
	//客户名称
	private String custName;
	//手机
	private String cellphone;
	//固话
	private String telephone;
	//需求分类
	private String needType;
	//需求问题
	private String needQuestion;
	//问题解决方案
	private String solution;
	//营销人工号
	private String marketPerson;
	//营销部门
	private String marketDept;
	//营销时间
	private Date marketTime;
	/**
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
	 * @return cellphone : return the property cellphone.  
	 */
	public String getCellphone() {
		return cellphone;
	}
	/**
	 * @param cellphone : set the property cellphone. 
	 */
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	/**
	 * @return telephone : return the property telephone.  
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone : set the property telephone. 
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return needType : return the property needType.  
	 */
	public String getNeedType() {
		return needType;
	}
	/**
	 * @param needType : set the property needType. 
	 */
	public void setNeedType(String needType) {
		this.needType = needType;
	}
	/**
	 * @return needQuestion : return the property needQuestion.  
	 */
	public String getNeedQuestion() {
		return needQuestion;
	}
	/**
	 * @param needQuestion : set the property needQuestion. 
	 */
	public void setNeedQuestion(String needQuestion) {
		this.needQuestion = needQuestion;
	}
	/**
	 * @return solution : return the property solution.  
	 */
	public String getSolution() {
		return solution;
	}
	/**
	 * @param solution : set the property solution. 
	 */
	public void setSolution(String solution) {
		this.solution = solution;
	}
	/**
	 * @return marketPerson : return the property marketPerson.  
	 */
	public String getMarketPerson() {
		return marketPerson;
	}
	/**
	 * @param marketPerson : set the property marketPerson. 
	 */
	public void setMarketPerson(String marketPerson) {
		this.marketPerson = marketPerson;
	}
	/**
	 * @return marketDept : return the property marketDept.  
	 */
	public String getMarketDept() {
		return marketDept;
	}
	/**
	 * @param marketDept : set the property marketDept. 
	 */
	public void setMarketDept(String marketDept) {
		this.marketDept = marketDept;
	}
	/**
	 * @return marketTime : return the property marketTime.  
	 */
	public Date getMarketTime() {
		return marketTime;
	}
	/**
	 * @param marketTime : set the property marketTime. 
	 */
	public void setMarketTime(Date marketTime) {
		this.marketTime = marketTime;
	}
	
}
