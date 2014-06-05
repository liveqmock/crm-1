/**   
 * <p>
 * 发到货管理<br />
 * </p>
 * @title ICycleManager.java
 * @package com.deppon.crm.module.marketing.server.manager 
 * @author 苏玉军
 * @version 0.1 2012-4-12
 */
package com.deppon.crm.module.marketing.server.manager;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.shared.domain.CustGroupDayDetail;
import com.deppon.crm.module.marketing.shared.domain.CustGroupDayDetailCondition;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail;
import com.deppon.crm.module.marketing.shared.domain.PathAnalysisInfo;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.marketing.shared.domain.ScheduleQueryResultObject;
/**   
 * <p>
 * 发货周期表、到货周期表Manager<br />
 * </p>
 * @title ICycleManager.java
 * @package com.deppon.crm.module.marketing.server.manager 
 * @author 苏玉军
 * @version 0.1 2012-4-12
 */

public interface ICycleManager {
	/**
	 * 
	 * <p>
	 * 发到货周期查询，在dao调用朱俊勇分组模块功能，将参数出入到存储过程中<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-13
	 * @param condition
	 * @return
	 * List<Object>
	 */
	 Map<String,Object> searchCycleList(CustomerGroupDetail condition,int start,int limit,User user);
	
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
	 * 
	 * <p>
	 * 线路分析数据查询，调用接口<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-16
	 * @param memberNumber 客户编码
	 * @param endDate 结束日期
	 * @return
	 * List<PathAnalysisInfo>
	 */
	 List<PathAnalysisInfo> searPathAnalysisInfos(String memberNumber,Date endDate,String type,String sendGoodsType);
	
	/**
	 * 
	 * <p>
	 * 从收发货周期表功能制定日程，<br />
	 * 制定的日程是针对会员的维护日程。
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-23
	 * @param custIds
	 * @return
	 * boolean
	 */
	 List<ScheduleQueryResultObject> searchCustFromCycle(List<String> ids,int start,int limit,User user);
	
	/**
	 * 
	 * <p>
	 * 查询总数<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-23
	 * @param ids
	 * @param user
	 * @return
	 * int
	 */
	 int countForSearchCustFromCycle(List<String> ids,User user);
	 /**
	 * 
	 * <p>
	 * 查询发到货周期表的客户详情<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-11-26
	 * @param condition
	 * @return List<CustGroupDayDetail>
	 */
	 List<CustGroupDayDetail> searchCustomerGroupDetailDayAmount(CustGroupDayDetailCondition condition);
}
