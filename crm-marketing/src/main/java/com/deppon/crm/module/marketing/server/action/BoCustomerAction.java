package com.deppon.crm.module.marketing.server.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.util.DataDictionaryUtil;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.marketing.server.manager.IBoCustomerManager;
import com.deppon.crm.module.marketing.shared.domain.BoCustomerCondition;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.shared.util.string.StringUtil;

public class BoCustomerAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3176438376001368923L;
	// 会员信息弹出框查询条件
	private BoCustomerCondition searchCustCondition = new BoCustomerCondition();
	// 会员信息列表
	private List<MemberResult> memberResultList = new ArrayList<MemberResult>();

	private IBoCustomerManager boCustomerManager;// 弹出框查会员manage

	public BoCustomerCondition getSearchCustCondition() {
		return searchCustCondition;
	}

	public void setSearchCustCondition(BoCustomerCondition searchCustCondition) {
		this.searchCustCondition = searchCustCondition;
	}

	public List<MemberResult> getMemberResultList() {
		return memberResultList;
	}

	public void setMemberResultList(List<MemberResult> memberResultList) {
		this.memberResultList = memberResultList;
	}

	public void setBoCustomerManager(IBoCustomerManager boCustomerManager) {
		this.boCustomerManager = boCustomerManager;
	}

	public String searchMemberInfoListForBo() {
		User user = (User) UserContext.getCurrentUser();
		String currentDept = user.getEmpCode().getDeptId().getDeptName();

		// -1 标识不用分页
		searchCustCondition.setLimit(-1);
		// 客户查询结果列表
		List<MemberResult> memberResultList;
		if (currentDept.indexOf("营销管理") >= 0) {
			String bizDept = boCustomerManager.queryBizDeptBySubDept(user
					.getEmpCode().getDeptId().getId());
			searchCustCondition.setParentId(bizDept);
			searchCustCondition.setDeptType(1);
			memberResultList = boCustomerManager
					.queryMemberByConditionForBo(searchCustCondition);
		} else if (currentDept.indexOf("大客户") >= 0) {
			searchCustCondition.setDeptType(0);
			memberResultList = boCustomerManager
					.queryMemberByConditionForBo(searchCustCondition);
		} else {
			memberResultList = new ArrayList<MemberResult>();
		}
		this.memberResultList = searchMemberList(memberResultList);
		return SUCCESS;
	}

	private List<MemberResult> searchMemberList(
			List<MemberResult> memberResultList) {
		for (int i = 0; i < memberResultList.size(); i++) {
			// 会员编码
			String custNum = memberResultList.get(i).getCustNum();
			// 会员名称
			String custName = memberResultList.get(i).getCustName();
			// 会员等级 数据字典转换
			String custGrade = DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.MEMBER_GRADE, memberResultList.get(i)
							.getCustGrade());
			if (custGrade == null) {
				custGrade = "";
			}
			memberResultList.get(i).setCustGroup(
					getCustGroup(custNum, custName, custGrade));
		}
		return memberResultList;
	}

	private String getCustGroup(String custNum, String custName,
			String custGrade) {
		String custNumView = "客户编码:" + custNum;
		String custGradeView = "客户等级:" + getNbsp(custGrade, 15);
		String custNameView = "客户名称:" + custName;
		return custNumView + "," + custGradeView + "," + custNameView;
	}

	private String getNbsp(String name, int length) {
		return StringUtils.rightPad(name, length, ' ')
				.replaceAll(" ", "&nbsp;");
	}

	private String deptName;
	// 查询出的部门列表
	private List<Department> deptList;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<Department> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<Department> deptList) {
		this.deptList = deptList;
	}

	public String searchDeptByDeptName() {
		User user = (User) UserContext.getCurrentUser();
		String currentDept = user.getEmpCode().getDeptId().getDeptName();
		if (StringUtil.isEmpty(deptName)) {
			deptList = new ArrayList<Department>();
			totalCount = (long) 1;
		} else {
			String bizDept = null;
			if (currentDept.indexOf("营销管理") >= 0) {
				bizDept = boCustomerManager.queryBizDeptBySubDept(user
						.getEmpCode().getDeptId().getId());
				deptList = boCustomerManager.queryDeptByParentDept(deptName,
						bizDept, start, limit);
				totalCount = boCustomerManager.countDeptByParentDept(deptName,
						bizDept);
			} else if (currentDept.indexOf("大客户") >= 0) {
				deptList = boCustomerManager.queryDeptByParentDept(deptName,
						null, start, limit);
				totalCount = boCustomerManager.countDeptByParentDept(deptName,
						null);
			} else {
				deptList = new ArrayList<Department>();
				totalCount = (long) 1;
			}
		}
		if (totalCount == null || totalCount == 0) {
			totalCount = (long) 1;
		}
		return SUCCESS;
	}
}
