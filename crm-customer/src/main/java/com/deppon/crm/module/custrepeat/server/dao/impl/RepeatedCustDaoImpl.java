package com.deppon.crm.module.custrepeat.server.dao.impl;

/***********************************************************************
 * Module:  RepeatedCustDaoImpl.java
 * Author:  120750
 * Purpose: Defines the Class RepeatedCustDaoImpl
 ***********************************************************************/

import java.util.*;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.custrepeat.server.dao.IRepeatedCustDao;
import com.deppon.crm.module.custrepeat.shared.domain.RepeatedCustomer;
import com.deppon.crm.module.custrepeat.shared.domain.RepetitiveCustWorkFlowInfo;
import com.deppon.crm.module.custrepeat.shared.domain.SearchCondition;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/** 
 * 疑似重复客户Dao 层 实现
 * @pdOid 00b3d130-d33a-4217-8989-77bf98abeb61 */
public class RepeatedCustDaoImpl extends iBatis3DaoImpl implements IRepeatedCustDao {
	/**
	 * 疑似重复:
	 * 疑似重复实体path
	 */
	private static final String CUSTREPEAT_NAMESPACE = "com.deppon.crm.module.custrepeat.shared.domain.RepeatedCustomer.";
	/**
	 * 疑似重复:
	 * 查询集合
	 */
	private static final String SEARCH_REPAETCUST_LIST = "searchRepeatCustList";
	/**
	 * 疑似重复:
	 * 查询count
	 */
	private static final String SEARCH_REPAETCUST_COUNT = "searchRepeatCustCount";
	/**
	 * 疑似重复:
	 * 标记客户
	 */
	private static final String MARK_CUSTREPEAT = "markCustRepeat";
	/**
	 * 疑似重复:
	 * 添加新的疑似重复客户
	 */
	private static final String ADDNEW_REPEATCUST = "AddNew_RepeatCust";
	/**
	 * 疑似重复:
	 * 把疑似重复数据添加至历史表中
	 */
	private static final String ADDREPEATCUST_TO_HISTORY = "AddRepeatCust_toHistory";
	/**
	 * 疑似重复:
	 * 删除疑似重复数据
	 */
	private static final String DELETE_REPEATCUST_LIST = "deleteRepeatCustList";
	/**
	 * 疑似重复:
	 * 查询客户数据集合
	 */
	private static final String SEARCH_CUSTOMER_LIST = "searchCustomerList";
	/**
	 * 疑似重复:
	 * 查询客户是否在疑似重复列表中
	 */
	private static final String CUST_ISEXISTS_REPEAT = "custIsExistsRepeat";
	/**
	 * 疑似重复:
	 * 保存工作流
	 */
	private static final String SAVEWORKFLOWINFO = "saveWorkFlowInfo";
	/**
	 * 疑似重复:
	 * 疑似重复数据准备定时间
	 */
	private static final String DOSPOSE_REPEATCUSTDATA = "disposeRepeatCustData";
	/**
	 * 疑似重复:
	 * 清空所有疑似重复标记状态
	 */
	private static final String CLEARALL_REPEATCUSTMARK = "clearALLRepeatCustMark";
	
	private static final String FINDREPEATWORKFLOWBYWORKNO = "findRepetitiveCustWorkFlowInfoByWorkNo";
	
	private static final String UPDATEREPEATWORKFLOWBYWORKNO="updateRepetitiveCustWorkFlowInfo";
	/**
	 * 疑似重复:
	 * 删除疑似重复状态标记根据客户ID
	 */
	private static final String DELETE_MARKCUST_BYCUSTID="deleteMarkCustByCustId";
	/**
	 * 疑似重复:
	 * 查询疑似重复客户所在部门集合
	 */
	private static final String searchRepeatCustDeptList = "searchRepeatCustDeptList";
	/**
	 * 疑似重复:
	 * 查询疑似重复客户所在部门count
	 */
	private static final String searchRepeatCustDeptCount = "searchRepeatCustDeptCount";
	/**
	 * 疑似重复:
	 * 修改疑似重复主客户ID
	 */
	private static final String UPDATE_REPEATCUSTSTATUS = "updateRepeatCustStatus";
	
	private static final String SELECTCUSTISREPEATMARKED ="selectCustIsRepeatMarked";
	
	private static final String SELECWORKFLOWBYCUSTCODE = "selectWorkFlowNoByCustId";
	/** 
    * 查询疑似重复列表集合
    * @param searchCondition
    * List<RepeatedCustomer>
    * @pdOid 1d6b0586-2b61-438a-a79d-e98c81eabec5 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RepeatedCustomer> searchRepeatCustList(SearchCondition searchCondition) {
		//判断是否进行分页
		if(null == searchCondition.getLimit() || null == searchCondition.getStart()){
			//不进行分页
			return getSqlSession().selectList(CUSTREPEAT_NAMESPACE + SEARCH_REPAETCUST_LIST, searchCondition);
		}
		//进行分页
		RowBounds rowBounds = new RowBounds(searchCondition.getStart(),searchCondition.getLimit());
		//查询疑似重复客户集合
		return getSqlSession().selectList(CUSTREPEAT_NAMESPACE + SEARCH_REPAETCUST_LIST, searchCondition, rowBounds);
	}
	
	/**
	 * 标记疑似重复客户
	 * @param custId 要标记的客户ID
	 * @return
	 */
	@Override
	public int markCustRepeat(String custId) {
		//标记疑似重复客户
		return getSqlSession().insert(CUSTREPEAT_NAMESPACE+MARK_CUSTREPEAT,custId);
	}
	
	/**
    * 查询疑似重复客户集合统计-分页使用
    * @param searchCondition
    * @return
    */
	@Override
	public long searchRepeatCustCount(SearchCondition searchCondition) {
		//查询疑似重复客户集合统计-分页使用
		return (Long)getSqlSession().selectOne(CUSTREPEAT_NAMESPACE + SEARCH_REPAETCUST_COUNT, searchCondition);
	}
   /**
    * 添加疑似重复客户数据
    * @param repeatCust 疑似重复数据
    */
	@Override
	public int addRepeatCust(RepeatedCustomer repeatCust) {
		//添加疑似重复客户数据
		return getSqlSession().insert(CUSTREPEAT_NAMESPACE+ADDNEW_REPEATCUST,repeatCust);
	}

	/**
    * 添加疑似重复客户数据至疑似重复客户历史表
    * @param repeatCust 疑似重复数据
    */
	@Override
	public int addRepeatCustToHistory(RepeatedCustomer repeatCust) {
		//添加疑似重复客户数据至疑似重复客户历史表
		return getSqlSession().insert(CUSTREPEAT_NAMESPACE+ADDREPEATCUST_TO_HISTORY,repeatCust);
	}
	
	/**
	    * 删除疑似重复客户集合
	    * @param custList
	    * @return
	    */
	@Override
	public int deleteRepeatCustList(List<RepeatedCustomer> custList) {
		//删除疑似重复客户集合
		return getSqlSession().delete(CUSTREPEAT_NAMESPACE+DELETE_REPEATCUST_LIST,custList);
	}

	/**
	 * 查询客户信息
	 * @param searchCondition
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RepeatedCustomer> searchCustomerList(
			SearchCondition searchCondition) {
		// TODO Auto-generated method stub
		//查询客户信息
		return getSqlSession().selectList(CUSTREPEAT_NAMESPACE + SEARCH_CUSTOMER_LIST, searchCondition);
	}
	
	/**
	 * <p>
	 * Description:根据客户id查询客户是否处于疑似重复列表<br />
	 * </p>
	 * @author 120750
	 * @version 0.1 2014-3-8
	 * @return
	 * boolean
	 */
	@Override
	public boolean isCustExistsRepeat(String custId) {
		// TODO Auto-generated method stub
		//根据客户id查询客户是否处于疑似重复列表
		Integer count = (Integer)getSqlSession().selectOne(CUSTREPEAT_NAMESPACE+CUST_ISEXISTS_REPEAT,custId);
		//是否存在
		return (count == null || count == 0)?false:true;
	}
	
	/**
    * <p>
    *	Description:保存疑似重复客户工作流信息
    * </p> 
    * @author LiuY
    * @date 2014-3-8
    * @param info
    * void
    */
	@Override
	public int saveWorkFlowInfo(RepetitiveCustWorkFlowInfo info){
		//保存疑似重复客户工作流信息
		return getSqlSession().insert(CUSTREPEAT_NAMESPACE+SAVEWORKFLOWINFO,info);
	}
	/**
	 * 定时器：
	 * 检索、赛选疑似重复客户数据，
	 * 并找出每组数据的主客户
	 */
	@Override
	public void disposeRepeatCustData() {
		// TODO Auto-generated method stub
		//检索、赛选疑似重复客户数据，并找出每组数据的主客户
		this.getSqlSession().selectOne(CUSTREPEAT_NAMESPACE+DOSPOSE_REPEATCUSTDATA);
	}
	/**
	 * 定时器：
	 * 清除疑似重复标记状态 - 清除数据
	 */
	@Override
	public void clearALLRepeatCustMark() {
		// TODO Auto-generated method stub
		//清除疑似重复标记状态 - 清除数据
		this.getSqlSession().selectOne(CUSTREPEAT_NAMESPACE+CLEARALL_REPEATCUSTMARK);
	}
	/**
	 * 
	 * <p>
	 * Description:查找工作流<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-12
	 * @param bizCode
	 * @return
	 * RepetitiveCustWorkFlowInfo
	 */
	@Override
	public RepetitiveCustWorkFlowInfo findRepetitiveCustWorkFlowInfoByWorkNo(String bizCode) {
		//查找工作流
		 return (RepetitiveCustWorkFlowInfo)this.getSqlSession().selectOne(CUSTREPEAT_NAMESPACE+FINDREPEATWORKFLOWBYWORKNO, bizCode);
	}
	/**
	 * <p>
	 *	Description: 审批完成后，更新工作流状态
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-19
	 * @param info
	 * void
	 */
	@Override
	public void updateRepetitiveCustWorkFlowInfo(RepetitiveCustWorkFlowInfo info){
		//审批完成后，更新工作流状态
		this.getSqlSession().update(CUSTREPEAT_NAMESPACE+UPDATEREPEATWORKFLOWBYWORKNO, info);
	}
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
	@Override
	public int deleteMarkCustByCustId(String custId) {
		// TODO Auto-generated method stub
		//根据客户编号删除已标记的疑似客户
		return this.getSqlSession().delete(CUSTREPEAT_NAMESPACE+DELETE_MARKCUST_BYCUSTID, custId);
	}
	/**
	 * 查询当前登录人 的 部门数据
	 * @param deptName
	 * @param start
	 * @param limit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Department> searchRepeatCustDeptList(
			SearchCondition searchCondition) {
		//分页
		RowBounds rowBounds = new RowBounds(searchCondition.getStart(),searchCondition.getLimit());
		//查询当前登录人 的 部门数据
		return getSqlSession().selectList(CUSTREPEAT_NAMESPACE + searchRepeatCustDeptList, searchCondition, rowBounds);
	}
	/**
	 * 查询当前登录人 的 部门数据 count
	 * @param deptName
	 * @param start
	 * @param limit
	 * @return
	 */
	@Override
	public int searchRepeatCustDeptCount(SearchCondition searchCondition) {
		// TODO Auto-generated method stub
		//查询当前登录人 的 部门数据 count
		return (Integer)getSqlSession().selectOne(CUSTREPEAT_NAMESPACE + searchRepeatCustDeptCount, searchCondition);
	}
	/**
	 * 更新疑似重复客户 是否为主客户， 更新主客户ID
	 * 
	 * @param cust
	 */
	@Override
	public void updateRepeatCustStatus(RepeatedCustomer cust) {
		// TODO Auto-generated method stub
		this.getSqlSession().update(CUSTREPEAT_NAMESPACE+UPDATE_REPEATCUSTSTATUS, cust);
	}
	/**
	 * 
	* @Title: selectCustIsMarked 
	* @Description: 根据客户ID判断客户是否已经被标记
	* @author LiuY 
	* @param custId
	* @return 
	* @throws
	 */
	@Override
	public int selectCustIsMarked(String custId){
		return (Integer)this.getSqlSession().selectOne(CUSTREPEAT_NAMESPACE+SELECTCUSTISREPEATMARKED,custId);
	}
	/**
	 * 
	* @Title: searchWorkFlowNoByCustId 
	* @Description: 根据客户ID查询工作流号
	* @author LiuY 
	* @param custCode
	* @return String
	* @throws
	 */
	@Override
	public String searchWorkFlowNoByCustId(String custCode){
		return (String)this.getSqlSession().selectOne(CUSTREPEAT_NAMESPACE+SELECWORKFLOWBYCUSTCODE,custCode);
	}
}
