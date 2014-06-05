package com.deppon.crm.module.custview.shared.domain;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.integral.ContactIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.MemberIntegral;

/**
 * 
 * @description 客户积分
 * @author 安小虎
 * @version 0.1 2012-4-23
 * @date 2012-4-23
 */
public class MemberPointsView {
	// 所有联系人积分集合
	private List<ContactIntegral> contactIntegralList;
	// 总积分
	private MemberIntegral memberIntegral;
	/**
	 * @return the contactIntegralList
	 * @author 潘光均
	 * @version v0.1
	 */
	public List<ContactIntegral> getContactIntegralList() {
		return contactIntegralList;
	}
	/**
	 * @param contactIntegralList the contactIntegralList to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setContactIntegralList(List<ContactIntegral> contactIntegralList) {
		this.contactIntegralList = contactIntegralList;
	}
	/**
	 * @return the memberIntegral
	 * @author 潘光均
	 * @version v0.1
	 */
	public MemberIntegral getMemberIntegral() {
		return memberIntegral;
	}
	/**
	 * @param memberIntegral the memberIntegral to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setMemberIntegral(MemberIntegral memberIntegral) {
		this.memberIntegral = memberIntegral;
	}
}
