package com.deppon.crm.module.customer.workflow;

import java.util.Map;

import com.deppon.crm.module.customer.server.manager.IBaseDataManager;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;
/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title InitContactVaryOnwer.java
 * @package com.deppon.crm.module.customer.workflow 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class InitContactVaryOnwer implements FunctionProvider{
	
	//基础权限manager
	private IBaseDataManager baseDataManager ;
	
	/**
	 * 
	 * <p>
	 * Description:setBaseDataManager<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-11
	 * @param baseDataManager
	 * void
	 */
	public void setBaseDataManager(IBaseDataManager baseDataManager) {
		this.baseDataManager = baseDataManager;
	}

	/**
	 * @author 078823
	 * @date 2014-04-11
	 * @description:初始化联系人挂靠关系变更参与者</br>
	 * 主要用于action请求日志
	 * *
	 */	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Map transientVars, Map args, PropertySet ps)
			throws WorkflowException {
		//
		String fromDeptId = (String) transientVars.get("fromDeptId");
		String toDeptId = (String) transientVars.get("toDeptId");

		String owner = getNextOwner(fromDeptId,toDeptId);
		
		transientVars.put("contactOwner",owner);
	}
	
	/**
	 * 
	 * <p>
	 * Description:获取下一个审批人<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-11
	 * @param fromDeptId
	 * @param toDeptId
	 * @return
	 * String
	 */
	public String getNextOwner(String fromDeptId,String toDeptId){
		
		//加一个验证
		//获取变更部门小区总
		Department fromNeighDept = baseDataManager.getNeighborhoodDepartment(fromDeptId);
		//获取被变更部门小区总
		Department toNeighDept = baseDataManager.getNeighborhoodDepartment(toDeptId);
		//构造使用数据
		if(fromNeighDept != null && fromNeighDept.getId().equals(toNeighDept.getId())){
			return fromNeighDept.getId()+"_"+Constant.Neighborhood;
		}
		
		//获取大区总
		Department fromRegionDept = baseDataManager.getRegionDepartment(fromDeptId);
		//获取大区总
		Department toRegionDept = baseDataManager.getRegionDepartment(toDeptId);
		//构造使用数据
		if(fromRegionDept != null &&fromRegionDept.getId().equals(toRegionDept.getId())){
			return fromRegionDept.getId()+"_"+Constant.Region;
		}
		//获取事业部总裁
		Department fromCauseDept = baseDataManager.getCauseDepartment(fromDeptId);
		//获取事业部总裁
		Department toCauseDept = baseDataManager.getCauseDepartment(toDeptId);
		//构造使用数据
		if(fromCauseDept != null &&fromCauseDept.getId().equals(toCauseDept.getId())){
			return fromCauseDept.getId()+"_"+Constant.Cause;
		}
		//构造使用数据
		return baseDataManager.getCustRelationsDepartment().getId()+"_"+Constant.Marketing;
	}


}
