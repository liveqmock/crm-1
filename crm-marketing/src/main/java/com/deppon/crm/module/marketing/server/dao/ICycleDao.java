package com.deppon.crm.module.marketing.server.dao;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.marketing.shared.domain.PathAnalysisInfo;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.marketing.shared.domain.ScheduleQueryResultObject;

/**
 * 
 * <p>
 * 发到货周期表操作封装
 * </p>
 * @title ICycleDao.java
 * @package com.deppon.crm.module.marketing.server.dao 
 * @author 苏玉军
 * @version 0.1 2013-1-28
 */
public interface ICycleDao {

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
	 */
	 List<ReturnVisit> searchReturnVisitRecords(String custId);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-23
	 * @param ids
	 * @return
	 * List<ScheduleQueryResultObject>
	 */
	 List<ScheduleQueryResultObject> searchCustFromCycle(List<String> ids,int start,int limit,String deptId);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-23
	 * @param ids
	 * @param deptId
	 * @return
	 * int
	 */
	 int countForSearchCustFromCycle(List<String> ids, String deptId);

	/**
	 * 查询最后一次的发货时间
	 * @author 苏玉军
	 * @version 0.1 2012-10-30
	 * @param custNum
	 * @return
	 * Date
	 */
	 Date queryLastTimeByCustNum(String custNum,String deptId,Date queryDate);

	/**
	 * 
	 * <p>
	 * 查询客户线路分析数据<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-3-25
	 * @param memberNumber
	 * @param endDate
	 * @param analysisCustomerType
	 * @return List<PathAnalysisInfo>
	 */
//	List<PathAnalysisInfo> queryPathAnalysis(String memberNumber,
//			Date endDate, String analysisCustomerType);
//	
	/**
	 * 
	 * <p>
	 * 发货线路分析查询<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-5-6
	 * @param memberNumber
	 * @param endDate
	 * @return
	 * List<PathAnalysisInfo>
	 */
	List<PathAnalysisInfo> queryDeliverPathAnalysis(String memberNumber,Date endDate);
	
	/**
	 * 
	 * <p>
	 * 到货线路分析查询<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-5-6
	 * @param memberNumber
	 * @param endDate
	 * @return
	 * List<PathAnalysisInfo>
	 */
	List<PathAnalysisInfo> queryReceivePathAnalysis(String memberNumber,Date endDate);
	/**
	 * 
	 * <p>
	 * 查询快递发货线路分析<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014-3-17
	 * @param memberNumber
	 * @param endDate
	 * @return
	 * List<PathAnalysisInfo>
	 */
	List<PathAnalysisInfo> queryExpressDeliverPathAnalysis(String memberNumber,
			Date endDate);

	List<PathAnalysisInfo> queryExpressReceivePathAnalysis(String memberNumber,
			Date endDate);
}
