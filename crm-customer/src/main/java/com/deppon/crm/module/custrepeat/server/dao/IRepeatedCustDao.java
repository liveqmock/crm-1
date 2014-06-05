package com.deppon.crm.module.custrepeat.server.dao;

/***********************************************************************
 * Module:  IRepeatedCustDao.java
 * Author:  120750
 * Purpose: Defines the Interface IRepeatedCustDao
 ***********************************************************************/

import java.util.*;

import com.deppon.crm.module.custrepeat.shared.domain.RepeatedCustomer;
import com.deppon.crm.module.custrepeat.shared.domain.RepetitiveCustWorkFlowInfo;
import com.deppon.crm.module.custrepeat.shared.domain.SearchCondition;
import com.deppon.crm.module.organization.shared.domain.Department;

/**
 * 疑似重复客户Dao 层接口
 * 
 * @pdOid 172d182d-188f-499e-836f-303173945972
 */
public interface IRepeatedCustDao {
	/**
	 * 查询疑似重复列表集合
	 * 
	 * @param searchCondition
	 *            List<RepeatedCustomer>
	 * @pdOid 1d6b0586-2b61-438a-a79d-e98c81eabec5
	 */
	List<RepeatedCustomer> searchRepeatCustList(SearchCondition searchCondition);

	/**
	 * 查询客户信息
	 * 
	 * @param searchCondition
	 */
	List<RepeatedCustomer> searchCustomerList(SearchCondition searchCondition);

	/**
	 * 查询疑似重复客户集合统计-分页使用
	 * 
	 * @param searchCondition
	 * @return
	 */
	long searchRepeatCustCount(SearchCondition searchCondition);

	/**
	 * 标记疑似重复客户
	 * 
	 * @param custId
	 *            要标记的客户ID
	 * @return
	 */
	int markCustRepeat(String custId);

	/**
	 * 添加疑似重复客户数据
	 * 
	 * @param repeatCust
	 *            疑似重复数据
	 */
	int addRepeatCust(RepeatedCustomer repeatCust);

	/**
	 * 添加疑似重复客户数据至疑似重复客户历史表
	 * 
	 * @param repeatCust
	 *            疑似重复数据
	 */
	int addRepeatCustToHistory(RepeatedCustomer repeatCust);

	/**
	 * 删除疑似重复客户集合
	 * 
	 * @param custList
	 * @return
	 */
	int deleteRepeatCustList(List<RepeatedCustomer> custList);

	/**
	 * <p>
	 * Description:根据客户id查询客户是否处于疑似重复列表<br />
	 * </p>
	 * 
	 * @author 120750
	 * @version 0.1 2014-3-8
	 * @return boolean
	 */
	boolean isCustExistsRepeat(String custId);

	/**
	 * <p>
	 * Description:保存疑似重复客户工作流信息
	 * </p>
	 * 
	 * @author LiuY
	 * @date 2014-3-8
	 * @param info
	 *            void
	 */
	int saveWorkFlowInfo(RepetitiveCustWorkFlowInfo info);

	/**
	 * 定时器： 检索、赛选疑似重复客户数据， 并找出每组数据的主客户
	 */
	void disposeRepeatCustData();

	/**
	 * 定时器： 清除疑似重复标记状态 - 清除数据
	 */
	void clearALLRepeatCustMark();

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-12
	 * @param bizCode
	 * @return RepetitiveCustWorkFlowInfo
	 */
	RepetitiveCustWorkFlowInfo findRepetitiveCustWorkFlowInfoByWorkNo(
			String bizCode);

	/**
	 * <p>
	 * Description: 审批完成后，更新工作流状态
	 * </p>
	 * 
	 * @author LiuY
	 * @date 2014-3-19
	 * @param info
	 *            void
	 */
	void updateRepetitiveCustWorkFlowInfo(RepetitiveCustWorkFlowInfo info);

	/**
	 * <p>
	 * Description: 根据客户编号删除已标记的疑似客户
	 * </p>
	 * 
	 * @param custId
	 *            客户编号
	 * @author hpf
	 * @date 2014-3-19
	 * @param info
	 *            void
	 */
	int deleteMarkCustByCustId(String custId);

	/**
	 * 查询当前登录人 的 部门数据
	 * 
	 * @param deptName
	 * @param start
	 * @param limit
	 * @return
	 */
	List<Department> searchRepeatCustDeptList(SearchCondition searchCondition);

	/**
	 * 查询当前登录人 的 部门数据 count
	 * 
	 * @param deptName
	 * @param start
	 * @param limit
	 * @return
	 */
	int searchRepeatCustDeptCount(SearchCondition searchCondition);

	/**
	 * 更新疑似重复客户 是否为主客户， 更新主客户ID
	 * 
	 * @param cust
	 */
	void updateRepeatCustStatus(RepeatedCustomer cust);
	/**
	 * 
	* @Title: selectCustIsMarked 
	* @Description: 根据客户ID判断客户是否已经被标记
	* @author LiuY 
	* @param custId
	* @return int
	* @throws
	 */
	int selectCustIsMarked(String custId);
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
