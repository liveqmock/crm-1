package com.deppon.crm.module.custrepeat.server.manager;

/***********************************************************************
 * Module:  IRepeatedCustManager.java
 * Author:  120750
 * Purpose: Defines the Interface IRepeatedCustManager
 ***********************************************************************/

import java.util.*;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.custrepeat.shared.domain.RepeatedCustomer;
import com.deppon.crm.module.custrepeat.shared.domain.RepetitiveCustWorkFlowInfo;
import com.deppon.crm.module.custrepeat.shared.domain.SearchCondition;
import com.deppon.crm.module.organization.shared.domain.Department;

/** @pdOid 2f7b3858-9b53-40f2-96de-802e4526ddc3 */
public interface IRepeatedCustManager {
	/**
	 * 查询主客户集合
	 * 
	 * @param searchCondition
	 *            查询条件 List<RepeatedCustomer>
	 * @pdOid 0a90e3bc-f410-4d9e-9d57-61c7ffe88d5b
	 */
	Map<String,Object> searchMainCustomerMap(SearchCondition searchCondition);

	/**
	 * 查询疑似客户集合 通过主客户信息
	 * 
	 * @param searchCondition 
	 * @pdOid c75d5fbb-d818-4cfb-bc7b-746d9544fd38
	 */
	List<RepeatedCustomer> searchRepeatedCustList(SearchCondition searchCondition);

	/**
	 * 查询客户信息
	 * @param searchCondition
	 */
	List<RepeatedCustomer> searchCustomerList(SearchCondition searchCondition);
	
	
	/**
	 * 确定疑似重复客户
	 * 
	 * @param user 当前登录人
	 * @param custList 全部的客户集合
	 */
	void confirmRepeat(User user,List<RepeatedCustomer> custList,String disposeOpinion);

	/**
	 * 确认不重复
	 * 
	 * @param user 登录用户
	 * @param custList 所有客户集合
	 */
	void confirmNotRepeat(List<RepeatedCustomer> custList);
	
	/**
	 * 定时器：检索、赛选疑似重复客户数据，并找出每组数据的主客户
	 */
	void disposeRepeatCustData();
	
	/**
	 * 定时器：清除疑似重复标记状态 - 清除数据
	 */
	void clearALLRepeatCustMark();
	
	/**
	 * 
	 * <p>
	 * Description:根据工作流号查询疑似客户工作流信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-12
	 * @param bizCode
	 * @param processDefName
	 * @return
	 * RepetitiveCustWorkFlowInfo
	 */
	RepetitiveCustWorkFlowInfo findRepetitiveCustWorkFlowInfoByWorkNo(
			String bizCode, String processDefName);
	/**
	 * <p>
	 *	Description: 疑似重复客户工作流审批
	 * </p> 
	 * @author LiuY 
	 * @date 2014-3-19
	 * @param busino
	 * @param wkStatus
	 * @param wkMan
	 * @param approveDate
	 * @param approvalSuggestin
	 * void
	 */
	void workflowApprove(String busino,Boolean wkStatus,String wkMan,Date approveDate,String approvalSuggestin);
	
	/**
	 * <p>
	 * Description:根据客户id查询客户是否处于疑似重复列表<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-3-8
	 * @return
	 * boolean
	 */
	boolean isCustExistsRepeat(String memberId);
	
	/**
	 * <p>
	 *	Description: 根据客户编号删除已标记的疑似客户
	 * </p> 
	 * @param custId 客户编号
	 * @author hpf
	 * @date 2014-3-19
	 * @param info
	 * void
	 */
	int deleteMarkCustByCustId(String custId);
	
	/**
	 * 查询当前登录人 的 部门数据
	 * @param deptName
	 * @param start
	 * @param limit
	 * @return
	 */
	List<Department> searchRepeatCustDeptList(String deptName, int start, int limit);
	
	/**
	 * 查询当前登录人 的 部门数据 count
	 * @param deptName
	 * @param start
	 * @param limit
	 * @return
	 */
	int searchRepeatCustDeptCount(String deptName);
	
	/**
	 * 查询客户是否存在疑似重复列表中，
	 * 若存在返回其主客户所在部门
	 * @param custCode 客户编码（主客户，此客户）
	 * @return 部门名称，不存在返回 "",存在返回所在主客户部门名称
	 */
	String searchMainCustDeptName(String cusCode);
	/**
	 * 
	* @Title: searchWorkFlowNoByCustId 
	* @Description: 根据客户ID查询工作流号
	* @author LiuY 
	* @param custCode
	* @return String
	* @throws
	 */
	String searchWorkFlowNoByCustId(String custCode);
}