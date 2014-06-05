package com.deppon.crm.module.customer.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * 会员归属部门变更实体<br />
 * </p>
 * @title ChangeMemberDept.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author bxj
 * @version 0.2 2012-6-5
 */
public class ChangeMemberDept extends BaseEntity{
	private static final long serialVersionUID = 8482118918159646495L;
	//会员id
	private String memberId;
	//会员编码
	private String memberNumber;
	//所属部门id
	private String fromDeptId;
	//所属部门姓名
	private String fromDeptName;
	//变更部门id
	private String toDeptId;
	//变更部门名称
	private String toDeptName;
	//申请原因
	private String reason;
	//工作流id
	private long workFlowId;
	/**
	 * <p>
	 * Description:memberId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getMemberId() {
		return memberId;
	}
	/**
	 * <p>
	 * Description:memberId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
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
	 * Description:fromDeptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getFromDeptId() {
		return fromDeptId;
	}
	/**
	 * <p>
	 * Description:fromDeptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setFromDeptId(String fromDeptId) {
		this.fromDeptId = fromDeptId;
	}
	/**
	 * <p>
	 * Description:fromDeptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getFromDeptName() {
		return fromDeptName;
	}
	/**
	 * <p>
	 * Description:fromDeptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setFromDeptName(String fromDeptName) {
		this.fromDeptName = fromDeptName;
	}
	/**
	 * <p>
	 * Description:toDeptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getToDeptId() {
		return toDeptId;
	}
	/**
	 * <p>
	 * Description:toDeptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setToDeptId(String toDeptId) {
		this.toDeptId = toDeptId;
	}
	/**
	 * <p>
	 * Description:toDeptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getToDeptName() {
		return toDeptName;
	}
	/**
	 * <p>
	 * Description:toDeptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setToDeptName(String toDeptName) {
		this.toDeptName = toDeptName;
	}
	/**
	 * <p>
	 * Description:reason<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * <p>
	 * Description:reason<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * <p>
	 * Description:workFlowId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public long getWorkFlowId() {
		return workFlowId;
	}
	/**
	 * <p>
	 * Description:workFlowId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setWorkFlowId(long workFlowId) {
		this.workFlowId = workFlowId;
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
