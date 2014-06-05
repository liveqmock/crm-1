package com.deppon.crm.module.customer.shared.domain.integral;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>积分规则操作对象<br />
 * </p>
 * @title IntegralOperation.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author Administrator
 * @version 0.1 2012-4-20
 */
public class IntegralOperation {
	private List<ContactIntegral> contactIntegral = new ArrayList<ContactIntegral>();//联系人积分集合
	private List<Integral> integral = new ArrayList<Integral>();//积分集合
	private List<MemberIntegral> memberIntegral = new ArrayList<MemberIntegral>();//会员积分
	/**
	 * <p>
	 * Description:contactIntegral<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public List<ContactIntegral> getContactIntegral() {
		return contactIntegral;
	}
	/**
	 * <p>
	 * Description:contactIntegral<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactIntegral(List<ContactIntegral> contactIntegral) {
		this.contactIntegral = contactIntegral;
	}
	/**
	 * <p>
	 * Description:integral<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public List<Integral> getIntegral() {
		return integral;
	}
	/**
	 * <p>
	 * Description:integral<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIntegral(List<Integral> integral) {
		this.integral = integral;
	}
	/**
	 * <p>
	 * Description:memberIntegral<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public List<MemberIntegral> getMemberIntegral() {
		return memberIntegral;
	}
	/**
	 * <p>
	 * Description:memberIntegral<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMemberIntegral(List<MemberIntegral> memberIntegral) {
		this.memberIntegral = memberIntegral;
	}
}
