package com.deppon.crm.module.keycustomer.server.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.keycustomer.server.dao.IKeyCustomerDao;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomer;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerSearchCondition;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerVO;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerWorkflowInfo;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * 
 * <p>
 * Description:大客户资格管理dao<br />
 * </p>
 *  
 * @title KeyCustomerDao.java
 * @package com.deppon.crm.module.keycustomer.server.dao.impl
 * @author 106138
 * @version 0.1 2014-3-4
 */

public class KeyCustomerDao extends iBatis3DaoImpl implements IKeyCustomerDao {
	// 命名空间
	private static final String NAMESPACE = "com.deppon.crm.module.keycustomer.shared.domain.KeyCustomer.";
	// 根据条件查询大客户列表
	private static final String SELECTKEYCUSTOMERLISTBYCONDITION = "selectKeyCustomerListByCondition";
	// 根据条件统计大客户条数
	private static final String COUNTKEYCUSTOMERLISTBYCONDITION = "countKeyCustomerListByCondition";
	// 根据条件查询大客户审批历史
	private static final String SELECTKEYCUSTOMERWORKFLOWINFOBYCONDITION = "selectKeyCustomerWorkflowInfoByCondition";
	// 根据条件统计大客户审批历史
	private static final String COUNTKEYCUSTOMERWORKFLOWINFOBYCONDITION = "countKeyCustomerWorkflowInfoByCondition";
	// 根据id查询大客户工作流历史
	private static final String SELECTKEYCUSTOMERWORKFLOWINFOBYCUSTID = "selectKeyCustomerWorkflowInfoByCustId";
	// 根据id查询大客户工作流历史条数
	private static final String COUNTKEYCUSTOMERWORKFLOWINFOBYCUSTID = "countKeyCustomerWorkflowInfoByCustId";
	// 插入大客户工作流记录
	private static final String INSERTKEYCUSTOMERWORKFLOWINFO = "insertKeyCustomerWorkflowInfo";
	// 插入大客户工信息
	private static final String INSERTKEYCUSTOMER = "insertKeyCustomer";
	// 更新大客户工作流信息
	private static final String UPDATEKEYCUSTOMERWORKINFO = "updateKeyCustomerWorkInfo";
	// 更新大客户工作流信息
	private static final String UPDATEKEYLISTSTATUS = "updateKeyListStatus";
	// 查询有效的大客户列表
	private static final String SELECTKEYCUSTOMERLISTBYCUSTID = "selectKeyCustomerListByCustId";
	// 根据工作流号查询工作流审批信息
	private static final String FINDWORKFLOWINFOBYBUSINO = "findWorkflowInfoByBusino";
	// 查询大客户信息
	private static final String UPDATEKEYCUSTOMER = "updateKeyCustomer";
	// 更新大客户信息
	private static final String SELECTKEYCUSTOMER = "selectKeyCustomer";

	/**
	 * 
	 * <p>
	 * Description:根据查询条件查询对应的大客户准入或退出列表<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param condition
	 * @return List<KeyCustomerList>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<KeyCustomer> searchKeyCustomerList(
			KeyCustomerSearchCondition condition) {
		// 设置分页
		RowBounds bounds = new RowBounds(condition.getStart(),
				condition.getLimit());
		List<KeyCustomer> list = (List<KeyCustomer>) getSqlSession()
				.selectList(NAMESPACE + SELECTKEYCUSTOMERLISTBYCONDITION,
						condition, bounds);
		return list;
	}

	/**
	 * 
	 * <p>
	 * Description:根据查询条件統計 对应的大客户准入或退出列表条数<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param condition
	 * @return List<KeyCustomerList>
	 */
	@Override
	public int countKeyCustomerList(KeyCustomerSearchCondition condition) {
		return (Integer) this.getSqlSession().selectOne(
				NAMESPACE + COUNTKEYCUSTOMERLISTBYCONDITION, condition);
	}

	/**
	 * 
	 * <p>
	 * Description:根据条件查询大客户的工作流历史<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param condtion
	 * @return List<KeyCustomerWorkflowInfo>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<KeyCustomerWorkflowInfo> searchWorkflowList(
			KeyCustomerSearchCondition condition) {
		// 设置分页
		RowBounds bounds = new RowBounds(condition.getStart(),
				condition.getLimit());
		List<KeyCustomerWorkflowInfo> list = (List<KeyCustomerWorkflowInfo>) getSqlSession()
				.selectList(
						NAMESPACE + SELECTKEYCUSTOMERWORKFLOWINFOBYCONDITION,
						condition, bounds);
		return list;
	}

	/**
	 * 
	 * <p>
	 * Description:根据条件统计工作流历史记录条数<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param condtion
	 * @return
	 * 
	 */
	@Override
	public int countWorkflowList(KeyCustomerSearchCondition condition) {
		return (Integer) this.getSqlSession().selectOne(
				NAMESPACE + COUNTKEYCUSTOMERWORKFLOWINFOBYCONDITION, condition);
	}

	/**
	 * 
	 * <p>
	 * Description:根据客户id统计工作流历史记录条数<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param custId
	 * @return
	 * 
	 */
	@Override
	public int countWorkflowListByCustId(String custId) {
		return (Integer) this.getSqlSession().selectOne(
				NAMESPACE + COUNTKEYCUSTOMERWORKFLOWINFOBYCUSTID, custId);
	}

	/**
	 * 
	 * <p>
	 * Description:根据客户id查询客户的审批历史<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param custId
	 * @return List<KeyCustomerWorkflowInfo>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<KeyCustomerWorkflowInfo> findWorkflowListByCustId(
			String custId, int start, int limit) {
		List<KeyCustomerWorkflowInfo> list = null;
		if (limit == 0 && start == 0) {
			list = (List<KeyCustomerWorkflowInfo>) getSqlSession().selectList(
					NAMESPACE + SELECTKEYCUSTOMERWORKFLOWINFOBYCUSTID, custId);
		} else {
			RowBounds bounds = new RowBounds(start, limit);
			list = (List<KeyCustomerWorkflowInfo>) getSqlSession().selectList(
					NAMESPACE + SELECTKEYCUSTOMERWORKFLOWINFOBYCUSTID, custId,
					bounds);
		}

		return list;
	}

	/**
	 * 
	 * <p>
	 * Description:保存工作流审批信息<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param info
	 *            void
	 * @return
	 */
	@Override
	public KeyCustomerWorkflowInfo saveWorkflowInfo(KeyCustomerWorkflowInfo info) {
		if (null == info) {
			return null;
		}
		if (null == info.getId() || "".equals(info.getId().trim())) {
			this.getSqlSession().insert(
					NAMESPACE + INSERTKEYCUSTOMERWORKFLOWINFO, info);
			return info;
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * Description:根据客户id查询大客户列表<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param custId
	 * @return KeyCustomerList
	 */
	@Override
	public List<KeyCustomer> findKeyCustomerListByCustId(String custId) {

		@SuppressWarnings("unchecked")
		List<KeyCustomer> keyCustomers = getSqlSession().selectList(
				NAMESPACE + SELECTKEYCUSTOMERLISTBYCUSTID, custId);

		return keyCustomers;
	}

	/**
	 * 
	 * <p>
	 * Description:更新大客户工作流信息<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param info
	 * 
	 */
	@Override
	public void updateKeyCustomerWorkflowInfo(KeyCustomerWorkflowInfo info) {
		if (null != info) {
			getSqlSession().update(NAMESPACE + UPDATEKEYCUSTOMERWORKINFO, info);
		}
	}

	/**
	 * 
	 * <p>
	 * Description:更新大客户列表的状态<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param custId
	 *            void
	 */
	@Override
	public void updateKeyListStatus(String custId, String status) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("custId", custId);
		map.put("status", status);
		if (null != custId) {
			getSqlSession().update(NAMESPACE + UPDATEKEYLISTSTATUS, map);
		}
	}

	/**
	 * 
	 * <p>
	 * Description:根据工作流号查询对于的大客户审批信息<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-7
	 * @param busino
	 * @return KeyCustomerWorkflowInfo
	 */
	@Override
	public KeyCustomerWorkflowInfo findWorkflowInfoByBusino(String busino) {
		return (KeyCustomerWorkflowInfo) getSqlSession().selectOne(
				NAMESPACE + FINDWORKFLOWINFOBYBUSINO, busino);
	}

	/**
	 * 
	 * <p>
	 * Description:保存大客户信息<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-7
	 * @param keyCustomerVO
	 * 
	 */
	@Override
	public void saveKeyCustomer(KeyCustomerVO keyCustomerVO) {
		this.getSqlSession().insert(NAMESPACE + INSERTKEYCUSTOMER,
				keyCustomerVO);
	}

	/**
	 * 
	 * <p>
	 * Description:更新大客户信息<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-7
	 * @param keyCustomerVO
	 * 
	 */
	@Override
	public void updateKeyCustomer(KeyCustomerVO keyCustomerVO) {
		this.getSqlSession().update(NAMESPACE + UPDATEKEYCUSTOMER,
				keyCustomerVO);
	}

	/**
	 * 
	 * <p>
	 * Description:查询大客户信息<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-7
	 * @param keyCustomerVO
	 * 
	 */
	@Override
	public KeyCustomerVO selectKeyCustomer(String custId) {
		return (KeyCustomerVO) this.getSqlSession().selectOne(
				NAMESPACE + SELECTKEYCUSTOMER, custId);
	}
	/**
	 * 
	 * <p>
	 * Description:调用存储过程<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-11
	 * @param storedName
	 * void
	 */
	@Override
	public void callStoredProcedure(String storedName){
		Connection conn = null;
		CallableStatement proc = null;
		try {
			conn = this.getSqlSession().getConfiguration().getEnvironment().getDataSource().getConnection();  
			proc = conn.prepareCall("{call "+storedName+"}");
			proc.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
		}
		
	}

}
