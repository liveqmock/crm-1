package com.deppon.crm.module.interfaces.customer.domain;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * 会员操作记录<br />
 * </p>
 * @title MemberOperationLog.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author bxj
 * @version 0.2 2012-7-19
 */
public class MemberOperationLog extends BaseEntity{
	//修改人姓名
	private String modifyUserName;
	//修改人id
	private String modifyUserId;
	//修改部门
	private Department department;
	//修改时间
	private Date modifyDate;
	//工作流号
	private String workflowId;
	//修改会员
	private Member member;
	//是否生效
	private boolean isEffect;
	//修改信息的详细内容
	private List<ApproveData> context;
	/**
	 * <p>
	 * Description:modifyUserName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getModifyUserName() {
		return modifyUserName;
	}
	/**
	 * <p>
	 * Description:modifyUserName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}
	/**
	 * <p>
	 * Description:modifyUserId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}
	/**
	 * <p>
	 * Description:modifyUserId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	/**
	 * <p>
	 * Description:department<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Department getDepartment() {
		return department;
	}
	/**
	 * <p>
	 * Description:department<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	/**
	 * <p>
	 * Description:modifyDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getModifyDate() {
		return modifyDate;
	}
	/**
	 * <p>
	 * Description:modifyDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	/**
	 * <p>
	 * Description:workflowId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getWorkflowId() {
		return workflowId;
	}
	/**
	 * <p>
	 * Description:workflowId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}
	/**
	 * <p>
	 * Description:member<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Member getMember() {
		return member;
	}
	/**
	 * <p>
	 * Description:member<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMember(Member member) {
		this.member = member;
	}
	/**
	 * <p>
	 * Description:isEffect<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public boolean isEffect() {
		return isEffect;
	}
	/**
	 * <p>
	 * Description:isEffect<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setEffect(boolean isEffect) {
		this.isEffect = isEffect;
	}
	/**
	 * <p>
	 * Description:context<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public List<ApproveData> getContext() {
		return context;
	}
	/**
	 * <p>
	 * Description:context<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContext(List<ApproveData> context) {
		this.context = context;
	}
	
}
