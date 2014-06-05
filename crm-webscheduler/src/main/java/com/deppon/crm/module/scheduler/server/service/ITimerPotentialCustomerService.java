package com.deppon.crm.module.scheduler.server.service;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.customer.shared.domain.PotentialCustomer;

public interface ITimerPotentialCustomerService {
	/**
	 * 
	 * <p>
	 * Description:2012-10-10修改描述：增加num，用于控制查询时间间隔，查询间隔两个小时的达到客户数据<br />
	 * </p>
	 * @author 李盛
	 * @version 0.1 2012-10-10
	 * @param date 查询日期
	 * @param num 为1-12之间整数
	 * @return 
	 * List<PotentialCustomer> 间隔两个小时的到达客户数据
	 */
	public List<PotentialCustomer> getArrivalCustomerList(Date date,int num);
	
	public void batchUpdateWaybillClient(List<PotentialCustomer> custs);
	
	public int createPotentialCustByWaybillClient(Date date,int num);
}
