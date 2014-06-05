package com.deppon.crm.module.custrepeat.server.service;

/***********************************************************************
 * Module:  IRepeatedCustService.java
 * Author:  120750
 * Purpose: Defines the Interface IRepeatedCustService
 ***********************************************************************/

import java.util.*;

import com.deppon.crm.module.custrepeat.shared.domain.RepeatedCustomer;
import com.deppon.crm.module.custrepeat.shared.domain.RepetitiveCustWorkFlowInfo;
import com.deppon.crm.module.custrepeat.shared.domain.SearchCondition;
import com.deppon.crm.module.organization.shared.domain.Department;

/** @pdOid 9153aa3c-d261-47fe-b0db-b98911e1d9b5 */
public interface IRepeatedCustService {
   /** 
    * 查询主客户集合
    * 
    * @param searchCondition 疑似客户查询条件
    * List<RepeatedCustomer>
    * @pdOid 205fe3e8-4699-4cc4-87ea-36cd0af11ef3 */
   List<RepeatedCustomer> searchRepeatCustList(SearchCondition searchCondition);
   
   /**
    * 查询客户信息
    * @param searchCondition 查询条件
    * @return
    */
   List<RepeatedCustomer> searchCustomerList(SearchCondition searchCondition);
   
   /**
    * 查询疑似重复客户集合统计-分页使用
    * @param searchCondition
    * @return
    */
   long searchRepeatCustCount(SearchCondition searchCondition);
   /**
    * 批量标记疑似重复客户
    * @param custList 要标记的客户集合
    * @return
    */
   void batchMarkCustRepeat(List<RepeatedCustomer> custList);
   
   /**
    * 批量添加疑似重复客户数据至疑似重复客户历史表
    * @param custList 疑似重复数据
    * @param isAdd true 新增的，false 疑似重复客户表数据本身存在的
    */
   void bactchAddRepeatCustToHistory(List<RepeatedCustomer> custList);
   
   /**
    * 添加疑似重复客户数据
    * @param repeatCust 疑似重复数据
    */
   void bactchAddRepeatCust(List<RepeatedCustomer> custList);
   
   /**
    * 更新疑似重复客户 是否为主客户，
    * 更新主客户ID
    * @param custList
    */
   void bactchUpdateRepeatCustStatus(List<RepeatedCustomer> custList);
   
   /**
    * 删除疑似重复客户集合
    * @param custList
    * @return
    */
   int deleteRepeatCustList(List<RepeatedCustomer> custList);

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
    *	Description:保存疑似重复客户工作流信息
    * </p> 
    * @author LiuY
    * @date 2014-3-8
    * @param info
    * void
    */
   void saveWorkFlowInfo(RepetitiveCustWorkFlowInfo info);
   
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
	   * Description:这里写描述<br />
	   * </p>
	   * @author 106138
	   * @version 0.1 2014-3-12
	   * @param bizCode
	   * @return
	   * RepetitiveCustWorkFlowInfo
	   */
	RepetitiveCustWorkFlowInfo findRepetitiveCustWorkFlowInfoByWorkNo(String bizCode);

	/**
	 * <p>
	 *	Description: 审批完成后，更新工作流状态
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-19
	 * @param info
	 * void
	 */
	void updateRepetitiveCustWorkFlowInfo(RepetitiveCustWorkFlowInfo info);
	
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
	List<Department> searchRepeatCustDeptList(SearchCondition searchCondition);
	
	/**
	 * 查询当前登录人 的 部门数据 count
	 * @param deptName
	 * @param start
	 * @param limit
	 * @return
	 */
	int searchRepeatCustDeptCount(SearchCondition searchCondition);
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