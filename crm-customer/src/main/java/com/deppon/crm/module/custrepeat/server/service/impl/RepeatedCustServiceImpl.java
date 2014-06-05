package com.deppon.crm.module.custrepeat.server.service.impl;

/***********************************************************************
 * Module:  RepeatedCustServiceImpl.java
 * Author:  120750
 * Purpose: Defines the Class RepeatedCustServiceImpl
 ***********************************************************************/

import java.util.*;

import com.deppon.crm.module.custrepeat.server.dao.IRepeatedCustDao;
import com.deppon.crm.module.custrepeat.server.service.IRepeatedCustService;
import com.deppon.crm.module.custrepeat.shared.domain.RepeatedCustomer;
import com.deppon.crm.module.custrepeat.shared.domain.RepetitiveCustWorkFlowInfo;
import com.deppon.crm.module.custrepeat.shared.domain.SearchCondition;
import com.deppon.crm.module.organization.shared.domain.Department;

/** @pdOid c391b681-193c-4caa-98cb-dcc8b5f9d583 */
public class RepeatedCustServiceImpl implements IRepeatedCustService {
	/**
	 * dao 层疑重复客户操作类
	 * 
	 * @pdOid badfef54-579a-4062-a779-0a44f1396fef
	 */
	private IRepeatedCustDao repeatedCustDao;

	/**
	 * 查询主客户集合
	 * 
	 * @param user
	 *            当前登录人
	 * @param searchCondition
	 *            疑似客户查询条件
	 * @pdOid 0cb49a26-e533-485f-a15f-0f4b03fea86d
	 */
	@Override
	public List<RepeatedCustomer> searchRepeatCustList(
			SearchCondition searchCondition) {
		// TODO: implement
		return repeatedCustDao.searchRepeatCustList(searchCondition);
	}

	/**
	 * 疑似冲客户dao层 set
	 * 
	 * @param repeatedCustDao
	 *            the repeatedCustDao to set
	 */
	public void setRepeatedCustDao(IRepeatedCustDao repeatedCustDao) {
		this.repeatedCustDao = repeatedCustDao;
	}

	/**
	 * 批量标记疑似重复客户
	 * 
	 * @param custList
	 *            要标记的客户集合
	 * @return
	 */
	@Override
	public void batchMarkCustRepeat(List<RepeatedCustomer> custList) {
		if (null == custList) {
			return;
		}
		for (RepeatedCustomer cust : custList) {
			if(repeatedCustDao.selectCustIsMarked(cust.getCustId()) == 0){
				repeatedCustDao.markCustRepeat(cust.getCustId());
			}
		}
	}

	/**
	 * 查询疑似重复客户集合统计-分页使用
	 * 
	 * @param searchCondition
	 * @return
	 */
	@Override
	public long searchRepeatCustCount(SearchCondition searchCondition) {
		return repeatedCustDao.searchRepeatCustCount(searchCondition);
	}

	/**
	 * 批量添加疑似重复客户数据至疑似重复客户历史表
	 * 
	 * @param custList
	 *            疑似重复数据
	 * @param isAdd
	 *            true 新增的，false 疑似重复客户表数据本身存在的
	 */
	@Override
	public void bactchAddRepeatCustToHistory(List<RepeatedCustomer> custList) {
		if (null == custList) {
			return;
		}
		for (RepeatedCustomer cust : custList) {
			repeatedCustDao.addRepeatCustToHistory(cust);
		}
	}

	/**
	 * 批量添加疑似重复客户数据至疑似重复客户历史表
	 * 
	 * @param custList
	 *            疑似重复数据
	 * @param isAdd
	 *            true 新增的，false 疑似重复客户表数据本身存在的
	 */
	@Override
	public void bactchAddRepeatCust(List<RepeatedCustomer> custList) {
		if (null == custList) {
			return;
		}
		for (RepeatedCustomer cust : custList) {
			repeatedCustDao.addRepeatCust(cust);
		}
	}

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
	@Override
	public void saveWorkFlowInfo(RepetitiveCustWorkFlowInfo info) {
		repeatedCustDao.saveWorkFlowInfo(info);
	}

	/**
	 * 删除疑似重复客户集合
	 * 
	 * @param custList
	 * @return
	 */
	@Override
	public int deleteRepeatCustList(List<RepeatedCustomer> custList) {
		// 判断客户集合不为空
		if (null == custList) {
			return 0;
		}
		// 删除疑似重复客户集合
		return repeatedCustDao.deleteRepeatCustList(custList);
	}

	/**
	 * 查询客户信息
	 * 
	 * @param searchCondition
	 *            查询条件
	 * @return
	 */
	@Override
	public List<RepeatedCustomer> searchCustomerList(
			SearchCondition searchCondition) {
		// 查询客户信息
		return repeatedCustDao.searchCustomerList(searchCondition);
	}

	/**
	 * <p>
	 * Description:根据客户id查询客户是否处于疑似重复列表<br />
	 * </p>
	 * 
	 * @author pgj
	 * @version 0.1 2014-3-8
	 * @return boolean
	 */
	@Override
	public boolean isCustExistsRepeat(String memberId) {
		// TODO Auto-generated method stub
		// 在验证客户编码是否为null
		if (null == memberId || "".equals(memberId.trim())) {
			return false;
		}
		// 根据客户id查询客户是否处于疑似重复列表
		return this.repeatedCustDao.isCustExistsRepeat(memberId.trim());
	}

	/**
	 * 定时器：检索、赛选疑似重复客户数据，并找出每组数据的主客户
	 */
	@Override
	public void disposeRepeatCustData() {
		// TODO Auto-generated method stub
		// 定时器：检索、赛选疑似重复客户数据，并找出每组数据的主客户
		this.repeatedCustDao.disposeRepeatCustData();
	}

	/**
	 * 
	 * <p>
	 * Description:查找工作流<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-12
	 * @param bizCode
	 * @return RepetitiveCustWorkFlowInfo
	 */
	@Override
	public RepetitiveCustWorkFlowInfo findRepetitiveCustWorkFlowInfoByWorkNo(
			String bizCode) {
		// 查找工作流
		return repeatedCustDao.findRepetitiveCustWorkFlowInfoByWorkNo(bizCode);
	}

	/**
	 * 定时器：清除疑似重复标记状态 - 清除数据
	 */
	@Override
	public void clearALLRepeatCustMark() {
		// TODO Auto-generated method stub
		// 定时器：清除疑似重复标记状态 - 清除数据
		this.repeatedCustDao.clearALLRepeatCustMark();
	}

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
	@Override
	public void updateRepetitiveCustWorkFlowInfo(RepetitiveCustWorkFlowInfo info) {
		// 审批完成后，更新工作流状态
		repeatedCustDao.updateRepetitiveCustWorkFlowInfo(info);
	}

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
	@Override
	public int deleteMarkCustByCustId(String custId) {
		// 验证客户ID不为空
		if (null == custId || "".equals(custId)) {
			return 0;
		}
		// 根据客户编号删除已标记的疑似客户
		return repeatedCustDao.deleteMarkCustByCustId(custId);
	}

	/**
	 * 查询当前登录人 的 部门数据
	 * 
	 * @param deptName
	 * @param start
	 * @param limit
	 * @return
	 */
	@Override
	public List<Department> searchRepeatCustDeptList(
			SearchCondition searchCondition) {
		// TODO Auto-generated method stub
		// 查询当前登录人 的 部门数据
		return repeatedCustDao.searchRepeatCustDeptList(searchCondition);
	}

	/**
	 * 查询当前登录人 的 部门数据 count
	 * 
	 * @param deptName
	 * @param start
	 * @param limit
	 * @return
	 */
	@Override
	public int searchRepeatCustDeptCount(SearchCondition searchCondition) {
		// TODO Auto-generated method stub
		// 查询当前登录人 的 部门数据 count
		return repeatedCustDao.searchRepeatCustDeptCount(searchCondition);
	}

	/**
	 * 更新疑似重复客户 是否为主客户， 更新主客户ID
	 * 
	 * @param custList
	 */
	@Override
	public void bactchUpdateRepeatCustStatus(List<RepeatedCustomer> custList) {
		// TODO Auto-generated method stub
		for (RepeatedCustomer cust : custList) {
			repeatedCustDao.updateRepeatCustStatus(cust);
		}
	}

	/** 
	* @Title: searchWorkFlowNoByCustId 
	* @Description: 根据客户编码查询疑似重复工作流号 
	* @author LiuY 
	* @param custCode
	* @return 
	* @throws 
	*/
	@Override
	public String searchWorkFlowNoByCustId(String custCode) {
		return repeatedCustDao.searchWorkFlowNoByCustId(custCode);
	}
	
}