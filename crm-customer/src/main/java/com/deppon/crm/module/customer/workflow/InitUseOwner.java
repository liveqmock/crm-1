package com.deppon.crm.module.customer.workflow;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.server.manager.IBaseDataManager;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.MemberUtil;
import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.exception.WorkFlowException;
import com.deppon.crm.module.customer.shared.exception.WorkFlowExceptionType;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;
/**
 * 
 * <p>
 * Description:初始化用户角色<br />
 * </p>
 * @title InitUseOwner.java
 * @package com.deppon.crm.module.customer.workflow 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class InitUseOwner implements FunctionProvider{
	/**
	 * 基础数据manager
	 */
	private IBaseDataManager baseDataManager ;
	/**
	 * 会员修改manager
	 */
	private IAlterMemberManager alterMemberManager;
	public void setBaseDataManager(IBaseDataManager baseDataManager) {
		this.baseDataManager = baseDataManager;
	}
	/**
	 * @param alterMemberManager : set the property alterMemberManager.
	 */
	public void setAlterMemberManager(IAlterMemberManager alterMemberManager) {
		this.alterMemberManager = alterMemberManager;
	}
	/**
	 * 
	 * <p>
	 * Description:初始化用户角色<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-11
	 * @param transientVars
	 * @param args
	 * @param ps
	 * @return
	 *  
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void execute(Map transientVars, Map args, PropertySet ps)
			throws WorkflowException {
		String nextRole = (String)args.get("roles");
		String isNeedPs =(String) args.get("isNeedPs");
		List<ApproveData> appDatas = (List<ApproveData>) ps.getObject("appData");
		if(nextRole != null && nextRole.length() > 0){
			String[] ns = nextRole.split(",");
			for(String role: ns){
				String department = getDepartment(role,ps.getString("appId"),appDatas);
				String roleId = getRoleId(role);
				if("true".equals(isNeedPs)){
					ps.setString(role, department+"_"+roleId);
				}else{
					transientVars.put(role, department+"_"+roleId);
				}
			}
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:h获取角色id<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-11
	 * @param transientVars
	 * @param args
	 * @param ps
	 * @return
	 *  
	 */
	private String getRoleId(String role) {
		if(role.equals("bizManager")){
			return Constant.BizManager;
		}else if(role.equals("marketing")){
			return Constant.Marketing;
		}else if(role.equals("customerManager")){
			return Constant.Marketing;
		}
		
		throw new WorkFlowException(WorkFlowExceptionType.NoRole,new Object[]{role});

	}

	/**
	 * 
	 * <p>
	 * Description:获取审批工作流部门<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-11
	 * @param transientVars
	 * @param args
	 * @param ps
	 * @return
	 *  
	 */
	public String getDepartment(String role,String appId,List<ApproveData> appDatas){
		//有合同的客户--基础资料审批是--部门为"客户关系部"
		//没有合同的客户--基础资料审批是--营销专员审批（营销专员的部门，申请人的部门应该属于对应的营销部门审批）//更具修改人查询出该营销部门
		if(role.equals("creator")){
			return ContextUtil.getCurrentUserDeptId();
		}else if(role.equals("bizManager")){
			Member member =alterMemberManager.getMemberById(appId);
			//纯新增账号和纯修改临欠额度由修改人经理审批
			if (MemberUtil.isEmergencyUpdate(appDatas)&&!Constant.CUST_GROUP_RC_CUSTOMER.equals(member.getCustGroup())) {
				return ContextUtil.getCurrentUserDeptId();
			}
			return member.getDeptId();
		}else if(role.equals("marketing")){
			//得到客户关系部门id
			Department depart = null;
			depart = baseDataManager.getCustRelationsDepartment();
			return depart.getId();
		}else if ("customerManager".equals(role)) {
			//得到客户管理组
			Department depart = null;
			depart = baseDataManager.getCustManageDepartment();
			return depart.getId();
		}
		throw new WorkFlowException(WorkFlowExceptionType.NoRoleUserDepartment,new Object[]{role});
	}
}
