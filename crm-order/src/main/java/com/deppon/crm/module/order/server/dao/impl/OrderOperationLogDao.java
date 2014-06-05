/**   
qx * @title OrderOperationLogDao.java
 * @package com.deppon.crm.module.order.server.dao.impl
 * @description what to do
 * @author 安小虎
 * @update 2012-3-13 上午8:57:25
 * @version V1.0   
 */
package com.deppon.crm.module.order.server.dao.impl;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;

import com.deppon.crm.module.order.server.dao.IOrderOperationLogDao;
import com.deppon.crm.module.order.server.dao.ISequenceTool;
import com.deppon.crm.module.order.shared.domain.OrderOperationLog;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * @description 订单操作记录DAO实现
 * @author 安小虎
 * @version 0.1 2012-3-13
 * @date 2012-3-13
 */

public class OrderOperationLogDao extends iBatis3DaoImpl implements
		IOrderOperationLogDao {
	//Mabitis配置空间
	private static final String NAMESPACE_ORDEROPERATIONLOG = "com.deppon.crm.module.order.shared.domain.OrderOperationLog.";
	//订单操作日志序列
	private static final String SEQ_ID_ORDEROPERATIONLOG = "seq_id_orderOperationLog";
	//插入订单操作日志
	private static final String INSERT_ORDEROPERATIONLOG = "insertOrderOperationLog";
	//按照订单ID查询List
	private static final String SELECT_ORDEROPERATIONLOGLISTBYORDERID = "selectListByOrderId";
	//按照输入条件查询订单列表
	private static final String SELECT_LISTBYCONDION = "selectListByCondion";
	//按照订单ID查询数量
	private static final String SEARCH_COUNTBYORDERID = "searchCountByOrderId";

	// 通用序列工具
	private ISequenceTool sequenceTool;
	
	/**
	 *@return  sequenceTool;
	 */
	public ISequenceTool getSequenceTool() {
		return sequenceTool;
	}

	/**
	 * @param sequenceTool : set the property sequenceTool.
	 */
	public void setSequenceTool(ISequenceTool sequenceTool) {
		this.sequenceTool = sequenceTool;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午11:28:07
	 * @param orderOperationLog
	 * @return
	 * @update 2013-1-28上午11:28:07
	 */
	@Override
	public OrderOperationLog saveOrderOperationLog(
			OrderOperationLog orderOperationLog) {
		orderOperationLog.setId(sequenceTool
				.getSequence(SEQ_ID_ORDEROPERATIONLOG));
		this.getSqlSession().insert(
				NAMESPACE_ORDEROPERATIONLOG + INSERT_ORDEROPERATIONLOG,
				orderOperationLog);
		return orderOperationLog;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午11:28:19
	 * @param orderID
	 * @return
	 * @update 2013-1-28上午11:28:19
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderOperationLog> getListByOrderId(
			String orderID) {
		return this.getSqlSession().selectList(
				NAMESPACE_ORDEROPERATIONLOG
						+ SELECT_ORDEROPERATIONLOGLISTBYORDERID, orderID);
	}

	/**
	 * 
	 * @description 批量操作时调用此方法，为了事务统一.
	 * @author 安小虎
	 * @version 0.1 2012-3-13
	 * @param 操作类型
	 * @date 2012-3-13
	 * @return SqlSession
	 * @update 2012-3-13 下午2:01:40
	 */
	private SqlSession getSqlSession(ExecutorType executorType) {
		SqlSession sqlSession = SqlSessionUtils.getSqlSession(
				((SqlSessionTemplate) getSqlSession()).getSqlSessionFactory(),
				executorType, null);
		return sqlSession;
	}

	/**
	 * 
	 * @description 根据订单ID获得订单操作记录条数.
	 * @author 安小虎
	 * @version 0.1 2012-3-22
	 * @param 订单ID
	 * @date 2012-3-22
	 * @return 订单操作记录条数
	 * @update 2012-3-22 上午11:44:23
	 * 
	 * @see com.deppon.crm.module.order.server.dao.IOrderOperationLogDao#
	 *      searchCountByOrderId(java.lang.String)
	 */
	@Override
	public Long searchCountByOrderId(String orderId) {
		return (Long) this.getSqlSession().selectOne(
				NAMESPACE_ORDEROPERATIONLOG + SEARCH_COUNTBYORDERID, orderId);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午11:28:32
	 * @param list
	 * @return
	 * @update 2013-1-28上午11:28:32
	 */
	@Override
	public boolean batchSaveOrderOperationLog(List<OrderOperationLog> list) {
		SqlSession session = this.getSqlSession(ExecutorType.BATCH);
		for (OrderOperationLog ool : list) {
			// 设置订单操作记录ID
			ool.setId(sequenceTool.getSequence(SEQ_ID_ORDEROPERATIONLOG));
			session.insert(NAMESPACE_ORDEROPERATIONLOG
					+ INSERT_ORDEROPERATIONLOG, ool);
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-28上午11:28:37
	 * @param orderOperationLog
	 * @return
	 * @update 2013-1-28上午11:28:37
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderOperationLog> getListByCondion(
			OrderOperationLog orderOperationLog) {
		return this.getSqlSession()
				.selectList(
						NAMESPACE_ORDEROPERATIONLOG
								+ SELECT_LISTBYCONDION,
						orderOperationLog);
	}

}
