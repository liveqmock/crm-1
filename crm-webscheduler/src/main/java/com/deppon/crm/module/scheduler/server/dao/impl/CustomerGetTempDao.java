package com.deppon.crm.module.scheduler.server.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;

import com.deppon.crm.module.customer.shared.domain.PotentialCustomer;
import com.deppon.crm.module.customer.shared.domain.ScatterCustomer;
import com.deppon.crm.module.scheduler.server.dao.ICustomerGetTempDao;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class CustomerGetTempDao extends iBatis3DaoImpl implements
		ICustomerGetTempDao {
	private final String NAMESPACE_CUSTOMER = "com.deppon.crm.module.scheduler.shared.domain.CustomerTemp.";
	// 查询到达客户信息
	private final String QUERY_ARRCUST = "queryArrPC_ByDate";
	// 根据时间查询到达客户信息
	private final String Query_ARRCUST_DATE = "queryArrSC_ByDate";
	// 根据中间表创建潜在客户信息
	private final String INSERT_POTENTIALCUST_DATE = "insertPCByWaybillClient";
	// 根据中间表创建潜在客户信息
	private final String UPDATEWAYBILLCLIENT = "updateWaybillClient";
	/**
	 * @作者 罗典
	 * @参数 潜客信息
	 * @时间 2012-3-6
	 * @功能 查询达到客户信息
	 * */
	@Override
	public List<PotentialCustomer> getArrivalCustomerList(Date date,int num) {
		/**
		 * @修改描述：根据时间，查询每隔两个小时的到达客户数据
		 * @author 李盛
		 * @since 2012-10-10
		 */
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("date", date);
		map.put("num", num);
		return (List<PotentialCustomer>) this.getSqlSession().selectList(
				NAMESPACE_CUSTOMER + QUERY_ARRCUST, map);
	}

	/**
	 * @作者 罗典
	 * @参数 潜客信息
	 * @时间 2012-3-6
	 * @功能 根据时间查询达到客户信息
	 * */
	public List<ScatterCustomer> getScatterCustomerListByDate(Date date) {
		return (List<ScatterCustomer>) this.getSqlSession().selectList(
				NAMESPACE_CUSTOMER + Query_ARRCUST_DATE, date);
	}

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

	@Override
	public int createPotentialCustByWaybillClient(Date date,int num) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("date", date);
		map.put("num", num);
		return this.getSqlSession().insert(
				NAMESPACE_CUSTOMER + INSERT_POTENTIALCUST_DATE, map);
	
	}
	@Override
	public void batchUpdateWaybillClient(List<PotentialCustomer> custs) {
		SqlSession sqlSession= this.getSqlSession();
		for(PotentialCustomer cust: custs){
            HashMap<String,Object> map=new HashMap<String,Object>();
            map.put("id",cust.getId());
            map.put("deptId", cust.getDeptId());
            map.put("cityId", cust.getCity());
            sqlSession.update(NAMESPACE_CUSTOMER + UPDATEWAYBILLCLIENT, map);
        }
	}

}
