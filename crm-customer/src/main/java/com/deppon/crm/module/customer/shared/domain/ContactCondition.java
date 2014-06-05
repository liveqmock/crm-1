package com.deppon.crm.module.customer.shared.domain;
/**
 * 
 * <p>
 * 联系人积分查询实体<br />
 * </p>
 * @title ContactCondition.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author bxj
 * @version 0.2 2012-5-3
 */
public class ContactCondition {
	//会员编码
	private String memberNum;
	//会员名称
	private String memberName;
	//联系人名称
	private String contactName;
	//联系人编码
	private String contactNum;
	//联系人手机
	private String mobile;
	//联系人固话
	private String phone;
	/**
	 * <p>
	 * Description:memberNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getMemberNum() {
		return memberNum;
	}
	/**
	 * <p>
	 * Description:memberNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}
	/**
	 * <p>
	 * Description:memberName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getMemberName() {
		return memberName;
	}
	/**
	 * <p>
	 * Description:memberName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	/**
	 * <p>
	 * Description:contactName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * <p>
	 * Description:contactName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * <p>
	 * Description:contactNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactNum() {
		return contactNum;
	}
	/**
	 * <p>
	 * Description:contactNum<br />
	 * </p>
	 * @version 0.1 2013-2-27
	 */
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	/**
	 *@user pgj
	 *2014-3-6下午6:18:18
	 * @return mobile : return the property mobile.
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile : set the property mobile.
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 *@user pgj
	 *2014-3-6下午6:18:18
	 * @return phone : return the property phone.
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone : set the property phone.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
