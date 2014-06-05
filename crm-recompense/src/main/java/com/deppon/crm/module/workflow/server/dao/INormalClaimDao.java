package com.deppon.crm.module.workflow.server.dao;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.recompense.shared.domain.AwardItem;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.IssueItem;
import com.deppon.crm.module.recompense.shared.domain.ResponsibleDept;
import com.deppon.crm.module.workflow.shared.domain.ClaimNoSuffixEntity;
import com.deppon.crm.module.workflow.shared.domain.NormalClaim;

/**
 * <p>
 * Description:理赔工作流Dao接口<br />
 * </p>
 * @title INormalClaimDao.java
 * @package com.deppon.crm.module.workflow.server.dao 
 * @author liuHuan
 * @version 0.1 2013-7-29
 */
public interface INormalClaimDao {
	
	/**
	 * 
	 * <p>
	 * Description:插入理赔工作流<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-7-29
	 * @param obj
	 * void
	 */
	void save(NormalClaim obj);
	
	/**
	 * 
	 * <p>
	 * Description:根据工作流号查询<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-7-29
	 * @param processInstId
	 * @return
	 * NormalClaim
	 */
	NormalClaim getNormalClaim(Long processInstId);
	
	/**
	 * 获取业务编码6位数字
	 * @return
	 */
	public int getNextSuffix();
	
	void updateClaimNoSuffix(int nextSuffix);
	
	void insertClaimNoSuffix(int nextSuffix);
	
	/**
	 * 添加理赔工作流信息
	 * @param normalClaim 
	 */
	public void insertNormalClaim(NormalClaim normalClaim);
	
	/**
	 * 理赔工作流详情
	 * @param workflowNo CRM流程号
	 * @return
	 */
	NormalClaim getNormalClaimByWorkflowNo(
			String workflowNo);
	
	/**
	 * 入部门费用
	 * @param processInstId 流程实例ID
	 * @return
	 */
	List<DeptCharge> getDeptChargeByProcessinstId(
			long processInstId);
	
	/**
	 * 出险信息
	 * @param processInstId 流程实例ID
	 * @return
	 */
	List<IssueItem> getIssueItemByProcessinstId(
			long processInstId);
	
	/**
	 * 奖罚明细
	 * @param processInstId 流程实例ID
	 * @return
	 */
	List<AwardItem> getAwardItemByProcessinstId(
			long processInstId);
	
	/**
	 * 责任部门
	 * @param processInstId 流程实例ID
	 * @return
	 */
	List<ResponsibleDept> getResponsibleDeptByProcessinstId(
			long processInstId);
	
	/**
	 * 修改理赔工作流信息
	 * @param normalClaim 
	 */
	void updateNormalClaim(NormalClaim normalClaim);
	
	/**
	 * 获取角色ID
	 * @param userId
	 * @return
	 */
	List<String> getRoleIdByUserId(String userId);

}
