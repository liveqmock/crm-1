package com.deppon.crm.module.customer.shared.domain.integral;
/**
 * 
 * <p>
 * Description:联系人会员积分<br />
 * </p>
 * @title ContactMemberIntegral.java
 * @package com.deppon.crm.module.customer.shared.domain.integral 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class ContactMemberIntegral {
	//会员积分
	private MemberIntegral memberIntegral;
	//联系人积分
	private ContactIntegral contactIntegral;
	/**
	 * 
	 * constructer
	 * @param memberIntegral
	 * @param contactIntegral
	 */
	public ContactMemberIntegral(MemberIntegral memberIntegral,
			ContactIntegral contactIntegral) {
		this.memberIntegral = memberIntegral;
		this.contactIntegral = contactIntegral;
	}
	/**
	 * <p>
	 * Description:memberIntegral<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public MemberIntegral getMemberIntegral() {
		return memberIntegral;
	}
	/**
	 * <p>
	 * Description:memberIntegral<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMemberIntegral(MemberIntegral memberIntegral) {
		this.memberIntegral = memberIntegral;
	}
	/**
	 * <p>
	 * Description:contactIntegral<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public ContactIntegral getContactIntegral() {
		return contactIntegral;
	}
	/**
	 * <p>
	 * Description:contactIntegral<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactIntegral(ContactIntegral contactIntegral) {
		this.contactIntegral = contactIntegral;
	}
}
