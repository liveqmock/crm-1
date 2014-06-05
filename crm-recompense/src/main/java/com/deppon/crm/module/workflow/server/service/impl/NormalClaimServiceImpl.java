package com.deppon.crm.module.workflow.server.service.impl;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.recompense.shared.domain.AwardItem;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.IssueItem;
import com.deppon.crm.module.recompense.shared.domain.ResponsibleDept;
import com.deppon.crm.module.workflow.server.dao.INormalClaimDao;
import com.deppon.crm.module.workflow.server.service.INormalClaimService;
import com.deppon.crm.module.workflow.shared.domain.ClaimNoSuffixEntity;
import com.deppon.crm.module.workflow.shared.domain.NormalClaim;

/**
 * 
 * <p>
 * Description:理赔工作流服务类<br />
 * </p>
 * @title NormalClaimServiceImpl.java
 * @package com.deppon.crm.module.workflow.server.service.impl 
 * @author liuHuan
 * @version 0.1 2013-7-29
 */
public class NormalClaimServiceImpl implements INormalClaimService{

	
	private INormalClaimDao normalClaimDao;
	
	
	
	public INormalClaimDao getNormalClaimDao() {
		return normalClaimDao;
	}



	public void setNormalClaimDao(INormalClaimDao normalClaimDao) {
		this.normalClaimDao = normalClaimDao;
	}


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
	@Override
	public NormalClaim getNormalClaim(Long processInstId) {
		return normalClaimDao.getNormalClaim(processInstId);
	}


	/**
	 * 获取业务编码6位数字
	 * @return
	 */
	@Override
	public int getNextSuffix() {
		return normalClaimDao.getNextSuffix();
	}



	@Override
	public void updateClaimNoSuffix(int nextSuffix) {
		normalClaimDao.updateClaimNoSuffix(nextSuffix);
	}



	@Override
	public void insertClaimNoSuffix(int nextSuffix) {
		normalClaimDao.insertClaimNoSuffix(nextSuffix);
	}


	/**
	 * 理赔工作流详情
	 * @param workflowNo CRM流程号
	 * @return
	 */
	@Override
	public NormalClaim getNormalClaimByWorkflowNo(String workflowNo) {
		return normalClaimDao.getNormalClaimByWorkflowNo(workflowNo);
	}
	
	/**
	 * 入部门费用
	 * @param processInstId 流程实例ID
	 * @return
	 */
	@Override
	public List<DeptCharge> getDeptChargeByProcessinstId(
			long processInstId) {
		List<DeptCharge> deptChargeList = normalClaimDao.getDeptChargeByProcessinstId(processInstId);
		return deptChargeList;
	}
	
	/**
	 * 出险信息
	 * @param processInstId 流程实例ID
	 * @return
	 */
	@Override
	public List<IssueItem> getIssueItemByProcessinstId(
			long processInstId) {
		List<IssueItem> issueItemList = normalClaimDao.getIssueItemByProcessinstId(processInstId);
		return issueItemList;
	}
	
	/**
	 * 奖罚明细
	 * @param processInstId 流程实例ID
	 * @return
	 */
	@Override
	public List<AwardItem> getAwardItemByProcessinstId(
			long processInstId) {
		List<AwardItem> awardItemList = normalClaimDao.getAwardItemByProcessinstId(processInstId);
		return awardItemList;
	}
	
	/**
	 * 责任部门
	 * @param processInstId 流程实例ID
	 * @return
	 */
	@Override
	public List<ResponsibleDept> getResponsibleDeptByProcessinstId(
			long processInstId) {
		List<ResponsibleDept> responsibleDeptList = normalClaimDao.getResponsibleDeptByProcessinstId(processInstId);
		return responsibleDeptList;
	}


	/**
	 * 添加理赔工作流信息
	 * @param normalClaim 
	 */
	@Override
	public void insertNormalClaim(NormalClaim normalClaim) {
		normalClaimDao.insertNormalClaim(normalClaim);
	}


	/**
	 * 修改理赔工作流信息
	 * @param normalClaim 
	 */
	@Override
	public void updateNormalClaim(NormalClaim normalClaim) {
		normalClaimDao.updateNormalClaim(normalClaim);
	}


	/**
	 * 获取角色ID
	 * @param userId
	 * @return
	 */
	@Override
	public List<String> getRoleIdByUserId(String userId) {
		return normalClaimDao.getRoleIdByUserId(userId);
	}

}
