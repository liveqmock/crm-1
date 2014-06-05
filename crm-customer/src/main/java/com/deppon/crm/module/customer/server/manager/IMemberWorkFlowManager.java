package com.deppon.crm.module.customer.server.manager;

import java.util.List;
import java.util.Set;

import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.ExamineRecord;
/**
 * 
 * <p>
 * Description:IMemberWorkFlowManager<br />
 * </p>
 * @title IMemberWorkFlowManager.java
 * @package com.deppon.crm.module.customer.server.manager 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public interface IMemberWorkFlowManager {
	/**
	 * 
	 * <p>
	 * 启动一个会员修改工作流<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-11
	 * @param dataTypes
	 * @return
	 * String
	 */
	public long createNewModifyMemberWorkFlow(Set<String> dataTypes,String memberId,List<ApproveData> appDatas);
	/**
	 * 
	 * <p>
	 * 启动一个合同新增--月发月送增值折扣 --工作流<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-8-3
	 * @param contractId
	 * @return
	 * long
	 */
	public long createContractAddWorkFLow(String contractId);
	/**
	 * 
	 * <p>
	 * 审批合同新增--月发月送增值折扣 --工作流<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-8-3
	 * @param record
	 * void
	 */
	public void commitContractAddExamin(ExamineRecord record);
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-12
	 * void
	 */
	public void commitModifyMemberExamin(ExamineRecord record);
	/**
	 * 
	 * <p>
	 * 创建特殊会员工作流<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-14
	 * @return
	 * long
	 */
	public long createSepCreateMemberWorkFlow(String memberId);
	/**
	 * 
	 * <p>
	 * 特殊会员创建代办<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-14
	 * @param record
	 * void
	 */
	public void commitSepCreateMemberExamin(ExamineRecord record);
	/**
	 * 
	 * <p>
	 * 创建联系人变更工作流<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-14
	 * @param string 
	 * @return
	 * long
	 */
	public long createContactVaryWorkFlow(String appId, String memberId,String fromDeptId,String toDeptId);
	/**
	 * 
	 * <p>
	 * 联系人变更审批提交<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-14
	 * @param record
	 * void
	 */
	public void commitContactVaryExamin(ExamineRecord record);
	/**
	 * 
	 * <p>
	 * 创建一个会员归属部门变更工作流<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-6
	 * @param appId
	 * @param formDeptId
	 * @return
	 * long
	 */
	public long createChangeMemberDeptWorkFlow(String appId,String formDeptId);
	/**
	 * 
	 * <p>
	 * 会员归属部门变更提交<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-6
	 * @param record
	 * void
	 */
	public void commitChangeMemberDeptExamin(ExamineRecord record);
	
}
