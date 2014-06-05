package com.deppon.crm.module.marketing.server.action;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.manager.IBusinessOpportunityManager;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportunity;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportunityCondition;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

public class BusinessOpportunityAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IBusinessOpportunityManager businessOpportunityManager;

	public void setBusinessOpportunityManager(
			IBusinessOpportunityManager businessOpportunityManager) {
		this.businessOpportunityManager = businessOpportunityManager;
	}

	private IDepartmentService departmentService;

	/**
	 * @param departmentService
	 *            the departmentService to set
	 */
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	// 总共有多少条数据
	private Long totalCount;
	// 查询得到的商界
	private List<BusinessOpportunity> businessOpportunityList;
	// 部门信息
	private Department dept;
	// ------
	// 分页查询，每页个数限制
	private int limit;
	// 分页查询的起始页
	private int start;
	// 查看商机详情，商机ID
	private String businessOpportunityId;
	// 部门id
	private String deptId;
	// ------
	private BusinessOpportunityCondition businessOpportunityCondition;
	// 商机类
	private BusinessOpportunity businessOpportunity;

	/**
	 * @param deptId
	 *            the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return the dept
	 */
	public Department getDept() {
		return dept;
	}

	/**
	 * @return the totalCount
	 */
	public Long getTotalCount() {
		return totalCount;
	}

	/**
	 * @return the businessOpportunityList
	 */
	public List<BusinessOpportunity> getBusinessOpportunityList() {
		return businessOpportunityList;
	}

	/**
	 * @param limit
	 *            the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * @param businessOpportunityCondition
	 *            the businessOpportunityCondition to set
	 */
	public void setBusinessOpportunityCondition(
			BusinessOpportunityCondition businessOpportunityCondition) {
		this.businessOpportunityCondition = businessOpportunityCondition;
	}

	/**
	 * @return the businessOpportunityCondition
	 */
	public BusinessOpportunityCondition getBusinessOpportunityCondition() {
		return businessOpportunityCondition;
	}

	/**
	 * @param businessOpportunity
	 *            the businessOpportunity to set
	 */
	public void setBusinessOpportunity(BusinessOpportunity businessOpportunity) {
		this.businessOpportunity = businessOpportunity;
	}

	/**
	 * @return the businessOpportunity
	 */
	public BusinessOpportunity getBusinessOpportunity() {
		return businessOpportunity;
	}

	/**
	 * @param businessOpportunityId
	 *            the businessOpportunityId to set
	 */
	public void setBusinessOpportunityId(String businessOpportunityId) {
		this.businessOpportunityId = businessOpportunityId;
	}

	/**
	 * .
	 * <p>
	 * 根据相关信息查询商机列表<br/>
	 * 方法名：searchBusinessOpportunityList
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2014-3-9
	 * @since JDK1.6
	 */
	@JSON
	public String searchBusinessOpportunityList() {
		User user = (User) UserContext.getCurrentUser();
		String currentDept = user.getEmpCode().getDeptId().getDeptName();
		// 设置start和limit
		businessOpportunityCondition.setStart(start);
		businessOpportunityCondition.setLimit(limit);
		if (currentDept.indexOf("营销管理") >= 0) {
			businessOpportunityCondition.setDeptType(1);
			businessOpportunityCondition.setDeptId(user.getEmpCode()
					.getDeptId().getId());
			// 商机信息
			businessOpportunityList = businessOpportunityManager
					.queryBusinessOpportunityByCondition(businessOpportunityCondition);
			totalCount = businessOpportunityManager
					.countBusinessOpportunityByCondition(businessOpportunityCondition);
		} else if (currentDept.indexOf("大客户") >= 0) {
			businessOpportunityCondition.setDeptType(0);
			// 商机信息
			businessOpportunityList = businessOpportunityManager
					.queryBusinessOpportunityByCondition(businessOpportunityCondition);
			totalCount = businessOpportunityManager
					.countBusinessOpportunityByCondition(businessOpportunityCondition);
		} else {
			businessOpportunityList = new ArrayList<BusinessOpportunity>();
			totalCount = 0L;
		}
		if (totalCount == null || totalCount == 0) {
			totalCount = (long) 1;
		}
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 保存相关信息查询商机列表<br/>
	 * 方法名：saveBusinessOpportunity
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2014-3-11
	 * @since JDK1.6
	 */
	@JSON
	public String createBusinessOpportunity() {
		User user = (User) UserContext.getCurrentUser();
		businessOpportunity.setCreator(user.getEmpCode());
		businessOpportunity.setModifier(user.getEmpCode());
		businessOpportunity.setDeptId(user.getEmpCode().getDeptId().getId());
		businessOpportunityManager
				.createBusinessOpportunity(businessOpportunity);
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 查看商机详情<br/>
	 * 方法名：queryBusinessOpportunity
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2014-3-12
	 * @since JDK1.6
	 */
	@JSON
	public String queryBusinessOpportunity() {
		businessOpportunity = businessOpportunityManager
				.queryBusinessOpportunityById(businessOpportunityId);
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 查看根据ID部门详细信息<br/>
	 * 方法名：queryDeptById
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2014-3-12
	 * @since JDK1.6
	 */
	@JSON
	public String queryDeptById() {
		dept = departmentService.queryById(deptId);
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 查看根据ID部门详细信息<br/>
	 * 方法名：queryDeptById
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2014-3-12
	 * @since JDK1.6
	 */
	@JSON
	public String saveBusinessOpportunity() {
		User user = (User) UserContext.getCurrentUser();
		
//		businessOpportunity.setOperator(user.getEmpCode());
		businessOpportunity.setModifier(user.getEmpCode());
		businessOpportunityManager.saveBusinessOpportunity(businessOpportunity);
		return SUCCESS;

	}
}
