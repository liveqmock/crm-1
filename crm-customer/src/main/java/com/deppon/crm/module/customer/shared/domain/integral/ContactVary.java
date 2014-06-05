package com.deppon.crm.module.customer.shared.domain.integral;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * 联系人变更实体<br />
 * </p>
 * @title ContactVary.java
 * @package com.deppon.crm.module.customer.shared.domain.integral 
 * @author bxj
 * @version 0.2 2012-4-28
 */
public class ContactVary extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//变更联系人编码
	private String contactNumber;
	//被挂的会员编码
	private String memberNumber;
	//被移动的会员积分
	private MemberIntegral fromMemberIntegral;
	//目标的会员积分
	private MemberIntegral toMemberIntegral;
	//工作流id
	private long workflowId;
	/**
	 * <p>
	 * Description:contactNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactNumber() {
		return contactNumber;
	}
	/**
	 * <p>
	 * Description:contactNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	/**
	 * <p>
	 * Description:memberNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getMemberNumber() {
		return memberNumber;
	}
	/**
	 * <p>
	 * Description:memberNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	/**
	 * <p>
	 * Description:fromMemberIntegral<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public MemberIntegral getFromMemberIntegral() {
		return fromMemberIntegral;
	}
	/**
	 * <p>
	 * Description:fromMemberIntegral<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setFromMemberIntegral(MemberIntegral fromMemberIntegral) {
		this.fromMemberIntegral = fromMemberIntegral;
	}
	/**
	 * <p>
	 * Description:toMemberIntegral<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public MemberIntegral getToMemberIntegral() {
		return toMemberIntegral;
	}
	/**
	 * <p>
	 * Description:toMemberIntegral<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setToMemberIntegral(MemberIntegral toMemberIntegral) {
		this.toMemberIntegral = toMemberIntegral;
	}
	/**
	 * <p>
	 * Description:workflowId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public long getWorkflowId() {
		return workflowId;
	}
	/**
	 * <p>
	 * Description:workflowId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setWorkflowId(long workflowId) {
		this.workflowId = workflowId;
	}
	/**
	 * <p>
	 * Description:serialversionuid<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
