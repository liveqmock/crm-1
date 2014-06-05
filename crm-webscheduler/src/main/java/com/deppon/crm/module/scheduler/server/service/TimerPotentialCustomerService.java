
package com.deppon.crm.module.scheduler.server.service;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.customer.shared.domain.PotentialCustomer;
import com.deppon.crm.module.customer.shared.domain.ScatterCustomer;
import com.deppon.crm.module.scheduler.server.dao.ICustomerGetTempDao;
import com.deppon.crm.module.scheduler.server.dao.impl.CustomerGetTempDao;
/**   
 * <p>
 * 单独查询中间表的service，不需要通过Manager就可以直接调用<br />
 * </p>
 * @title TimerPotentialCustomerService.java
 * @package com.deppon.crm.module.scheduler.server.service 
 * @author 苏玉军
 * @version 0.1 2012-3-14
 */

public class TimerPotentialCustomerService implements ITimerPotentialCustomerService{

	private ICustomerGetTempDao custDao;
	
	public void setCustDao(CustomerGetTempDao custDao) {
		this.custDao = custDao;
	}
	
	public List<PotentialCustomer> getArrivalCustomerList(Date date){
		return custDao.getArrivalCustomerList(date,Integer.MAX_VALUE);
	}

	public List<ScatterCustomer> getScatterCustomerListByDate(Date date) {
		return custDao.getScatterCustomerListByDate(date);
	}
	
	public void callStoredProcedure(String storedName,Object pro){
		custDao.callStoredProcedure(storedName);
	}
	public List<PotentialCustomer> getArrivalCustomerList(Date date, int num) {
		return custDao.getArrivalCustomerList(date,num);
	}
	
	
	@Override
	public void batchUpdateWaybillClient(List<PotentialCustomer> custs) {
		custDao.batchUpdateWaybillClient(custs);
	}
	
	@Override
	public int createPotentialCustByWaybillClient(Date date,int num) {
		return custDao.createPotentialCustByWaybillClient(date,num);
		
	}

}
