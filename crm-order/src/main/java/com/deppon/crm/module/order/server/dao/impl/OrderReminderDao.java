package com.deppon.crm.module.order.server.dao.impl;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;

import com.deppon.crm.module.order.server.dao.IOrderReminderDao;
import com.deppon.crm.module.order.shared.domain.OrderAcceptConfig;
import com.deppon.crm.module.order.shared.domain.OrderAcceptInfo;
import com.deppon.crm.module.order.shared.domain.OrderReminder;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * 
 * <p>
 * Description:订单通知Dao<br />
 * 本次修改只负责注释代码
 * </p>
 * @title OrderReminderDao.java
 * @package com.deppon.crm.module.order.server.dao.impl 
 * @author zouming
 * @version 0.1 2013-1-22上午10:38:19
 */
public class OrderReminderDao extends iBatis3DaoImpl implements
		IOrderReminderDao {
	
	//mabatis空间
	private static final String NAMESPACE_ORDERREMINDER = "com.deppon.crm.module.order.shared.domain.OrderReminder.";
	//插入订单提醒SQL的id
	private static final String INSERT_ORDERREMINDER = "insertOrderReminder";
	//查询所有的订单提醒
	private static final String SELECT_ORDERREMINDER_ALL = "selectOrderReminderAll";
	//清除订单提醒
	private static final String CLEAR_ORDERREMINDER = "clearOrderReminder";
	//查询未分配订单的提醒
	private static final String SELECT_UNASSIGN_ORDERREMINDER = "selectUnassignOrderReminder";
	//查询未受理订单的提醒
	private static final String SELECT_UNACCEPT_ORDERREMINDER = "selectUnacceptOrderReminder";
	//查询已受理订单的提醒
	private static final String SELECT_ACCEPTED_ORDERREMINDER = "selectAcceptedOrderReminder";
	//查询FOSS提示时间配置
	private static final String SELECT_ACCEPT_CONFIG = "getOrderAcceptConfig";
	
	/**
	 * 
	 * <p>
	 * Description:获取SqlSession<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22上午10:33:33
	 * @param executorType
	 * @return
	 * SqlSession
	 * @update 2013-1-22上午10:33:33
	 */
	private SqlSession getSqlSession(ExecutorType executorType) {
		SqlSessionFactory factory = ((SqlSessionTemplate) getSqlSession())
				.getSqlSessionFactory();
		return new SqlSessionTemplate(factory, executorType);
	}

	/**
	 * 
	 * <p>
	 * Description:获取自增序列<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22上午10:43:58
	 * @return
	 * String
	 * @update 2013-1-22上午10:43:58
	 */
	private String getSequence() {
		SqlSession session = this.getSqlSession(ExecutorType.SIMPLE);
		return session.selectOne(NAMESPACE_ORDERREMINDER + "getSeqId")
				.toString();
	}
	
	/**
	 * 
	 * <p>
	 * Description:保存订单提醒<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22上午10:48:28
	 * @param orderReminder
	 * @return	OrderReminder
	 * @update 2013-1-22上午10:48:28
	 */
	@Override
	public OrderReminder saveOrderReminder(OrderReminder orderReminder) {
		SqlSession session = this.getSqlSession(ExecutorType.SIMPLE);
		orderReminder.setId(getSequence());
		session.insert(NAMESPACE_ORDERREMINDER + INSERT_ORDERREMINDER,
				orderReminder);
		return orderReminder;
	}
	
	/**
	 * 
	 * <p>
	 * Description:保存订单提醒列表<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22上午10:49:10
	 * @param orderReminderList
	 * @update 2013-1-22上午10:49:10
	 */
	@Override
	public void saveOrderReminderList(List<OrderReminder> orderReminderList) {
		SqlSession session = this.getSqlSession(ExecutorType.SIMPLE);
		for (OrderReminder orderReminder : orderReminderList) {
			orderReminder.setId(getSequence());
			session.insert(NAMESPACE_ORDERREMINDER + INSERT_ORDERREMINDER,
					orderReminder);
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:获得所有的订单提醒<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22上午10:51:03
	 * @return
	 * @update 2013-1-22上午10:51:03
	 */
	@Override
	public List<OrderReminder> getOrderReminderAll() {
		SqlSession session = this.getSqlSession(ExecutorType.SIMPLE);
		return session.selectList(NAMESPACE_ORDERREMINDER
				+ SELECT_ORDERREMINDER_ALL);
	}
	/**
	 * 
	 * <p>
	 * Description:清除订单提醒<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22上午10:51:19
	 * @update 2013-1-22上午10:51:19
	 */
	@Override
	public void clearOrderReminder() {
		SqlSession session = this.getSqlSession(ExecutorType.SIMPLE);
		session.delete(NAMESPACE_ORDERREMINDER+ CLEAR_ORDERREMINDER);
	}
	
	/**
	 * 
	 * <p>
	 * Description:获得未分配的订单提醒<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22上午10:51:34
	 * @param statuses
	 * @return
	 * @update 2013-1-22上午10:51:34
	 */
	@Override
	public List<OrderReminder> getUnassignOrderReminder(List<String> statuses) {
		SqlSession session = this.getSqlSession(ExecutorType.SIMPLE);
		return session.selectList(NAMESPACE_ORDERREMINDER
				+ SELECT_UNASSIGN_ORDERREMINDER, statuses);
	}
	/**
	 * 
	 * <p>
	 * Description:获取未受理的订单提醒<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22上午10:52:09
	 * @param statuses
	 * @return
	 * @update 2013-1-22上午10:52:09
	 */
	@Override
	public List<OrderReminder> getUnacceptOrderReminder(List<String> statuses) {
		SqlSession session = this.getSqlSession(ExecutorType.SIMPLE);
		return session.selectList(NAMESPACE_ORDERREMINDER
				+ SELECT_UNACCEPT_ORDERREMINDER, statuses);
	}
	/**
	 * 
	 * <p>
	 * Description:获取已受理的订单提醒<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-22上午10:52:24
	 * @param statuses
	 * @return
	 * @update 2013-1-22上午10:52:24
	 */
	@Override
	public List<OrderReminder> getAcceptedOrderReminder(List<String> statuses) {
		SqlSession session = this.getSqlSession(ExecutorType.SIMPLE);
		return session.selectList(NAMESPACE_ORDERREMINDER
				+ SELECT_ACCEPTED_ORDERREMINDER, statuses);
	}

	/**
	 * 
	 * @Title: getOrderAcceptConfig
	 * @Description: 查询待受理订单统计参数
	 */
	@Override
	public OrderAcceptConfig getOrderAcceptConfig() {
		return (OrderAcceptConfig) getSqlSession(ExecutorType.SIMPLE).selectOne(SELECT_ACCEPT_CONFIG);
	}

	/**
	 * 
	 * @Title: addOrderAcceptDept
	 * @Description: 增加待受理订单部门
	 */
	@Override
	public void addOrderAcceptDept() {
		SqlSession session = this.getSqlSession(ExecutorType.SIMPLE);
		session.insert(NAMESPACE_ORDERREMINDER + "addOrderAcceptDept");

	}

	/**
	 * 
	 * @Title: generateOrderAcceptInfo
	 * @Description: 统计待受理订单的部门和订单数量
	 */
	@Override
	public List<OrderAcceptInfo> generateOrderAcceptInfo(OrderAcceptConfig oac) {
		oac.setStandardCode(null);
		SqlSession session = this.getSqlSession(ExecutorType.SIMPLE);
		return session.selectList(NAMESPACE_ORDERREMINDER
				+ "generateOrderAcceptInfo", oac);
	}

	/**
	 * 
	 * @Title: getOrderAcceptInfoByDept
	 * @Description: 查询制定部门的待受理订单数量
	 */
	@Override
	public OrderAcceptInfo getOrderAcceptInfoByDept(OrderAcceptConfig oac,
			String standardCode) {
		oac.setStandardCode(standardCode);
		SqlSession session = this.getSqlSession(ExecutorType.SIMPLE);
		return (OrderAcceptInfo) session.selectOne(NAMESPACE_ORDERREMINDER
				+ "generateOrderAcceptInfo", oac);
	}

}
