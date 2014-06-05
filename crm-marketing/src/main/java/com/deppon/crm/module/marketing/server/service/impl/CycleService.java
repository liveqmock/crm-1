/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CycleService.java
 * @package com.deppon.crm.module.marketing.server.service.impl 
 * @author Administrator
 * @version 0.1 2012-4-13
 */
package com.deppon.crm.module.marketing.server.service.impl;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.marketing.server.dao.ICycleDao;
import com.deppon.crm.module.marketing.server.service.ICycleService;
import com.deppon.crm.module.marketing.shared.domain.PathAnalysisInfo;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.marketing.shared.domain.ScheduleQueryResultObject;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CycleService.java
 * @package com.deppon.crm.module.marketing.server.service.impl 
 * @author 苏玉军
 * @version 0.1 2012-4-13
 */

public class CycleService implements ICycleService {
	//发到货dao封装
	private ICycleDao cycleDao;
	/**
	 * @return cycleDao : return the property cycleDao.
	 */
	public ICycleDao getCycleDao() {
		return cycleDao;
	}
	/**
	 * @param cycleDao : set the property cycleDao.
	 */
	public void setCycleDao(ICycleDao cycleDao) {
		this.cycleDao = cycleDao;
	}


	/**
	 * 
	 * <p>
	 * 联系人回访记录查询<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-13
	 * @param custId ，客户Id contactId关联联系人Id
	 * @return
	 * List<ReturnVisit>
	 * @see com.deppon.crm.module.marketing.server.service.ICycleService#searchReturnVisitRecords(java.lang.String)
	 */
	@Override
	public List<ReturnVisit> searchReturnVisitRecords(String custId) {
		return cycleDao.searchReturnVisitRecords(custId);
	}


	/**
	 * 分页查询客户
	 * @see com.deppon.crm.module.marketing.server.service.ICycleService#searchCustFromCycle(java.util.List)
	 */
	@Override
	public List<ScheduleQueryResultObject> searchCustFromCycle(List<String> ids,int start,int limit,String deptId) {
		return cycleDao.searchCustFromCycle(ids,start,limit,deptId);
	}


	/**
	 * 分页查询总数
	 * @see com.deppon.crm.module.marketing.server.service.ICycleService#countForSearchCustFromCycle(java.util.List, java.lang.String)
	 */
	@Override
	public int countForSearchCustFromCycle(List<String> ids, String deptId) {
		return cycleDao.countForSearchCustFromCycle(ids,deptId);
	}


	/**
	 * 查询最后一次发货时间
	 * @see com.deppon.crm.module.marketing.server.service.ICycleService#queryLastTimeByCustNum(java.lang.String)
	 */
	@Override
	public Date queryLastTimeByCustNum(String custNum,String deptId,Date queryDate) {
		return cycleDao.queryLastTimeByCustNum(custNum,deptId,queryDate);
	}
	
	/**
	 * 查询客户发到货线路分析
	 * @param memberNumber 客户编码
	 * @param endDate 查询日期
	 * @param analysisCustomerType 发到货类型
	 * @return List<PathAnalysisInfo>
	 **/
//	@Override
//	public List<PathAnalysisInfo> queryPathAnalysis(String memberNumber,
//			Date endDate, String analysisCustomerType) {
//		return cycleDao.queryPathAnalysis(memberNumber,endDate,analysisCustomerType);
//	}
	@Override
	public List<PathAnalysisInfo> queryDeliverPathAnalysis(String memberNumber,
			Date endDate) {
		return cycleDao.queryDeliverPathAnalysis(memberNumber, endDate);
	}
	@Override
	public List<PathAnalysisInfo> queryReceivePathAnalysis(String memberNumber,
			Date endDate) {
		return cycleDao.queryReceivePathAnalysis(memberNumber, endDate);
	}
	@Override
	public List<PathAnalysisInfo> queryExpressDeliverPathAnalysis(
			String memberNumber, Date endDate) {
		return cycleDao.queryExpressDeliverPathAnalysis(memberNumber, endDate);
	}
	@Override
	public List<PathAnalysisInfo> queryExpressReceivePathAnalysis(
			String memberNumber, Date endDate) {
		return cycleDao.queryExpressReceivePathAnalysis(memberNumber, endDate);
	}

}
