package com.deppon.crm.module.scheduler.server.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.customer.shared.domain.PotentialCustomer;
import com.deppon.crm.module.customer.shared.domain.ScatterCustomer;

/**
 * @作者：罗典
 * @时间：2012-3-14
 * @功能：获取临时客户信息接口
 * */
public interface ICustomerGetTempDao {
	/**
	 * @Descript 获得到达客户
	 * @author 罗典
	 * @version 0.1 2012-3-14
	 * @param 无
	 */
	public List<PotentialCustomer> getArrivalCustomerList(Date date,int num);
	
	/**
	 * @Descript 根据传入时间获得到达客户
	 * @author 罗典
	 * @version 0.1 2012-3-16
	 * @param 无
	 */
	public List<ScatterCustomer> getScatterCustomerListByDate(Date date);
	
	public void callStoredProcedure(String storedName);
	
	public int createPotentialCustByWaybillClient(Date date,int num);
	public void batchUpdateWaybillClient(List<PotentialCustomer> custs);
}
