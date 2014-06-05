package com.deppon.crm.module.workflow.server.dao.impl;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.recompense.shared.domain.AwardItem;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.IssueItem;
import com.deppon.crm.module.recompense.shared.domain.ResponsibleDept;
import com.deppon.crm.module.workflow.server.dao.INormalClaimDao;
import com.deppon.crm.module.workflow.shared.domain.ClaimNoSuffixEntity;
import com.deppon.crm.module.workflow.shared.domain.NormalClaim;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
import com.deppon.foss.framework.shared.util.string.StringUtil;

/**   
 * <p>
 * Description:理赔工作流Dao<br />
 * </p>
 * @title NormalClaimDaoImpl.java
 * @package com.deppon.crm.module.workflow.server.dao.impl 
 * @author liuHuan
 * @version 0.1 2013-7-29
 */
public class NormalClaimDaoImpl extends iBatis3DaoImpl implements INormalClaimDao{

	//命名空间
	private static final String NAMESPACE = "com.deppon.crm.module.workflow.server.dao.impl.NormalClaimDaoImpl.";

	//添加理赔工作流
	public static final String INSERT_NORMALCLAIM = "addNormalClaim";
	
	//通过工作流号查询
	public static final String GET_NORMALCLAIM = "getNormalClaim";
	
	//查询业务编码6位数字
	public static final String GET_NEXT_SUFFIX = "getNextSuffix";
	public static final String INSERT_NEXT_SUFFIX = "insertNextSuffix";
	public static final String UPDATE_NEXT_SUFFIX = "updateNextSuffix";
	private static final String INSERT_NORMAL_CLAIM = "insertNormalClaim";
	private static final String UPDATE_NORMAL_CLAIM = "updateNormalClaim";
	private static final String GETNORMALCLAIMBYWORKFLOWNO = "getNormalClaimByWorkflowNo";
	private static final String GETDEPTCHARGEBYPROCESSINSTID = "getDeptChargeByProcessinstId";
	private static final String GETISSUEITEMBYPROCESSINSTID = "getIssueItemByProcessinstId";
	private static final String GETAWARDITEMBYPROCESSINSTID = "getAwardItemByProcessinstId";
	private static final String GETRESPONSIBLEDEPTBYPROCESSINSTID = "getResponsibleDeptByProcessinstId";
	private static final String GETROLEIDBYUSERID = "getRoleIdByUserId";
	
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
	@Override
	public void save(NormalClaim obj) {
		this.getSqlSession().insert(NAMESPACE + INSERT_NORMALCLAIM, obj);
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
		return (NormalClaim) this.getSqlSession().selectOne(
					NAMESPACE + GET_NORMALCLAIM, processInstId);
	}

	/**
	 * 获取业务编码6位数字
	 * @return
	 */
	@Override
	public int getNextSuffix() {
		ClaimNoSuffixEntity nextsuffix = (ClaimNoSuffixEntity)this.getSqlSession().selectOne(NAMESPACE + GET_NEXT_SUFFIX);
		if(nextsuffix == null) {
			return 0;
		}
		return Integer.parseInt(nextsuffix.getNextSuffix());
	}

	@Override
	public void updateClaimNoSuffix(int nextSuffix) {
		this.getSqlSession().update(NAMESPACE + UPDATE_NEXT_SUFFIX, nextSuffix+"");
	}

	@Override
	public void insertClaimNoSuffix(int nextSuffix) {
		this.getSqlSession().insert(NAMESPACE + INSERT_NEXT_SUFFIX, nextSuffix+"");
	}

	/**
	 * 添加理赔工作流信息
	 * @param normalClaim 
	 */
	@Override
	public void insertNormalClaim(NormalClaim normalClaim) {
		if(normalClaim != null && !"".equals(normalClaim.getProcessinstId())) {
			this.getSqlSession().insert(NAMESPACE + INSERT_NORMAL_CLAIM, normalClaim);
		}
	}

	/**
	 * 理赔工作流详情
	 * @param workflowNo CRM流程号
	 * @return
	 */
	@Override
	public NormalClaim getNormalClaimByWorkflowNo(String workflowNo) {
		if(!"".equals(workflowNo)) {
			return (NormalClaim)this.getSqlSession().selectOne(NAMESPACE + GETNORMALCLAIMBYWORKFLOWNO, workflowNo + "");
		}
		return null;
	}
	
	/**
	 * 入部门费用
	 * @param processInstId 流程实例ID
	 * @return
	 */
	@Override
	public List<DeptCharge> getDeptChargeByProcessinstId(
			long processInstId) {
		List<DeptCharge> deptChargeList = this.getSqlSession().selectList(NAMESPACE + GETDEPTCHARGEBYPROCESSINSTID, processInstId);
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
		List<IssueItem> issueItemList = this.getSqlSession().selectList(NAMESPACE + GETISSUEITEMBYPROCESSINSTID, processInstId);
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
		List<AwardItem> awardItemList = this.getSqlSession().selectList(NAMESPACE + GETAWARDITEMBYPROCESSINSTID, processInstId);
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
		List<ResponsibleDept> responsibleDeptList = this.getSqlSession().selectList(NAMESPACE + GETRESPONSIBLEDEPTBYPROCESSINSTID, processInstId);
		return responsibleDeptList;
	}

	/**
	 * 修改理赔工作流信息
	 * @param normalClaim 
	 */
	@Override
	public void updateNormalClaim(NormalClaim normalClaim) {
		if(normalClaim != null && !"".equals(normalClaim.getProcessinstId())) {
			this.getSqlSession().insert(NAMESPACE + UPDATE_NORMAL_CLAIM, normalClaim);
		}
	}

	/**
	 * 获取角色ID
	 * @param userId
	 * @return
	 */
	@Override
	public List<String> getRoleIdByUserId(String userId) {
		return this.getSqlSession().selectList(NAMESPACE + GETROLEIDBYUSERID, userId);
	}
}
