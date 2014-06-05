/**
 * @description
 * @author 赵斌
 * @2012-5-2
 * @return
 */
package com.deppon.crm.module.customer.shared.domain;

/**
 * @author 赵斌
 *
 */
public class ContactView 
{
	//联系人信息
	private Contact contact;
	// 客户姓名
	private String custName;
	// 客户等级
	private String degree;
	//客户编码
	private String custNumber;
	
	public String getCustNumber() {
		return custNumber;
	}
	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}
	/**
	 * <p>
	 * Description:contact<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Contact getContact() {
		return contact;
	}
	/**
	 * <p>
	 * Description:contact<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	/**
	 * <p>
	 * Description:custName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * <p>
	 * Description:custName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * <p>
	 * Description:degree<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getDegree() {
		return degree;
	}
	/**
	 * <p>
	 * Description:degree<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}
	
}
