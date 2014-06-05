package com.deppon.crm.module.custrepeat.server.action;

/***********************************************************************
 * Module:  RepeatedCustAction.java
 * Author:  120750
 * Purpose: Defines the Class RepeatedCustAction
 ***********************************************************************/

import java.util.*;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.custrepeat.server.manager.IRepeatedCustManager;
import com.deppon.crm.module.custrepeat.shared.domain.RepeatedCustomer;
import com.deppon.crm.module.custrepeat.shared.domain.SearchCondition;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;

/** @pdOid 5b351af6-b4ee-4670-b93b-6d9ae9f89b3a */
public class RepeatedCustAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IRepeatedCustManager repeatedCustManager;
	/**
	 * 查询条件
	 * 
	 * @pdOid 508284fb-43e3-49f9-b927-439ba3fd579d
	 */
	private SearchCondition searchCondition;
	
	/**
	 * 疑似重复列表
	 */
	private List<RepeatedCustomer> repeatCustList;
	/**
	 * 所辖部门集合
	 */
	private List<Department> currentUserDeptList;

	private String disposeOpinion;
	/**
	 * 查询主客户集合
	 */
	@SuppressWarnings("unchecked")
	public String searchMainRepeatCustList() {
		searchCondition.setStart(start);
		searchCondition.setLimit(limit);
		Map<String,Object> resultMap = repeatedCustManager.searchMainCustomerMap(searchCondition);
		
		repeatCustList = (List<RepeatedCustomer>)resultMap.get("repeatCustList");
		totalCount = Long.valueOf(resultMap.get("totalCount").toString());
		
		searchCondition = null;//查询参数设置为空
		return SUCCESS;
	}
	
	/**
	 * 查询疑似重复的客户组-根据主客户ID
	 */
	public String searchGroupRepeatCustList() {
		repeatCustList = repeatedCustManager.searchRepeatedCustList(searchCondition);
		searchCondition = null;//查询参数设置为空
		return SUCCESS;
	}

	/**
	 * 查询客户信息
	 */
	public String searchCustomerList() {
		repeatCustList = repeatedCustManager.searchCustomerList(searchCondition);
		searchCondition = null;//查询参数设置为空
		return SUCCESS;
	}
	
	
	/**
	 * 查询所选部门是否有效
	 * 
	 * @pdOid 6fd53662-a45d-4d6c-8b4b-3d4c72dd8aa8
	 */
	public String verifyDepttValid() {
		// TODO: implement
		return SUCCESS;
	}


	/**
	 * 确定疑似重复客户
	 * 
	 * @pdOid 9fe217f1-0126-4fc0-a509-a82a51a9b9b4
	 */
	public String confirmRepeat() {
		// TODO: implement
		User user = (User) (UserContext.getCurrentUser());
		String disposeOpinion = this.disposeOpinion;
		repeatedCustManager.confirmRepeat(user, repeatCustList,disposeOpinion);
		repeatCustList = null;
		return SUCCESS;
	}

	/**
	 * 全部做不重复处理
	 * 
	 * @pdOid 96ff7077-8752-4f91-b693-eca348ef5fda
	 */
	public String confirmNotRepeat() {
		// TODO: implement
		repeatedCustManager.confirmNotRepeat(repeatCustList);
		repeatCustList = null;
		return SUCCESS;
	}
	
	/**
	 * @param searchCondition
	 */
	public void setSearchCondition(SearchCondition searchCondition) {
		this.searchCondition = searchCondition;
	}

	/**
	 * @param repeatedCustManager the repeatedCustManager to set
	 */
	public void setRepeatedCustManager(IRepeatedCustManager repeatedCustManager) {
		this.repeatedCustManager = repeatedCustManager;
	}

	/**
	 * @return the repeatCustList
	 */
	public List<RepeatedCustomer> getRepeatCustList() {
		return repeatCustList;
	}

	/**
	 * @param repeatCustList the repeatCustList to set
	 */
	public void setRepeatCustList(List<RepeatedCustomer> repeatCustList) {
		this.repeatCustList = repeatCustList;
	}

	/**
	 * @return the searchCondition
	 */
	public SearchCondition getSearchCondition() {
		return searchCondition;
	}
	
	/**
	 * @return the currentUserDeptList
	 */
	public List<Department> getCurrentUserDeptList() {
		return currentUserDeptList;
	}

	/**
	 * @return the disposeOpinion
	 */
	public String getDisposeOpinion() {
		return disposeOpinion;
	}

	/**
	 * @param disposeOpinion the disposeOpinion to set
	 */
	public void setDisposeOpinion(String disposeOpinion) {
		this.disposeOpinion = disposeOpinion;
	}

	
}