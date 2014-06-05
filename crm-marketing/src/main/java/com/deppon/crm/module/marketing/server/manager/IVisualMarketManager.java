/**
 * <p>
 * Description:可视化营销之五公里地图模块<br />
 * </p>
 * @title IVisualMarketManager.java
 * @package com.deppon.crm.module.marketing.server.manager
 * @author xiaohongye
 * @version V0.1 
 * @Date 2013-4-18
 */
package com.deppon.crm.module.marketing.server.manager;

import java.util.List;
import java.util.Map;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.shared.domain.CustomerLocation;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinionVo;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustIntegrationInfoVO;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustMonthlyArriveIncome;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustomerAddressInfo;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustomerMarketInfo;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.QueryCondition;

/**
 * <p>
 * Description:可视化营销之五公里地图模块<br />
 * </p>
 * @title IVisualMarketManager.java
 * @package com.deppon.crm.module.marketing.server.manager
 * @author xiaohongye
 * @version V0.1 
 * @Date 2013-4-18
 */
public interface IVisualMarketManager {
	
	/**
	 * <p>
	 * Description:查询客户信息列表<br />
	 * @author xiaohongye
	 * @param condition
	 * @param user
	 * @param start
	 * @param limit
	 * @return
	 * @version V0.1 
	 * @Date 2013-4-18
	 */
	List<CustomerMarketInfo> searchCustMarketInfoList(QueryCondition condition,User user, int start, int limit);
	
	/**
     * <p>
     * Description: 查询已在地图上标记的客户
     * </p>
     * @author 石应华
     * @param condition
     * @return 
     * @return List<CustomerMarketInfo>    
     * @version
     * @date 2013-4-25 上午10:55:43
     */
    List<CustomerMarketInfo> searchMarkCustInfoList(QueryCondition condition,User user);
    
	/**
	 * <p>
	 * Description:查询单个客户的浮窗信息：包括回访信息页签、客户基本信息页签和近半年出发收入页签<br />
	 * @author xiaohongye
	 * @param custMarketInfo
	 * @param user
	 * @return
	 * @version V0.1 
	 * @Date 2013-4-18
	 */
	CustIntegrationInfoVO searchCustIntegrationInfoVO(CustomerMarketInfo custMarketInfo,User user);
	
	/**
	 * <p>
	 * Description:固定客户近半年出发收入查询<br />
	 * @author xiaohongye
	 * @param custId
	 * @return
	 * @version V0.1 
	 * @Date 2013-4-18
	 */
	List<CustMonthlyArriveIncome> searchHalfYearIncomeList(String custId);
	
	/**
	 * <p>
	 * Description:根据登陆用户USER的部门id查询客户标签<br />
	 * @author xiaohongye
	 * @param deptId
	 * @return
	 * @version V0.1 
	 * @Date 2013-4-18
	 */
	Map<String,Object> searchCustLabelByDeptId(User user,String deptId);
	
	
	/**
	 * 
	 * @Title: countCustMarketInfoList
	 *  <p>
	 * @Description:统计客户信息条数
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-25
	 * @return int
	 * @throws
	 */
	int countCustMarketInfoList(QueryCondition condition);
	
	/**
	 * 
	 * <p>
	 * Description:根据客户id查询客户类型<br />
	 * </p>
	 * @author 石应华
	 * @version 0.1 2013-5-4
	 * @param custId 客户id
	 * @param custType 客户类型
	 * @return
	 * CustomerMarketInfo 客户信息
	 */
	CustomerMarketInfo searchCustById(String custId,String custType,String deptId);
	  /**
		 * <p>
		 * Description:从多个用户中返回正在回访的客户集合<br />
		 * @author zhangzhenwei
		 * @param id
		 * @return
		 * @version V0.1 
		 * @Date 2013-5-6
		 */
    List<String> searchVisitStatusByCustId(String[] ids, String custType);
    
	/**
	 * <p>
	 * Description:返回可以制定计划的客户信息列表和不能制定计划的客户数量<br />
	 * @author xiaohongye
	 * @param customerMarketInfoList
	 * @return
	 * @version V0.1 
	 * @Date 2013-4-18
	 */
    Map<String, Object> searchCustsToCreatePlan(List<String> ids ,String custType,User user);
    
	/**
	 * <p>
	 * Description:查询单个客户回访信息列表<br />
	 * @author xiaohongye
	 * @param customerMarketInfoList
	 * @return
	 * @version V0.1 
	 * @Date 2013-4-18
	 */
    List<ReturnVisitOpinionVo> searchReturnVisitInfoList(String custId,String custType,User user);
    
    /**
     * 
     * <p>
     * Description:修改客户标识<br />
     * </p>
     * @author 张振伟
     * @version 0.1 2013-5-28
     * @param CustomerLocation 客户标注信息
     * @param User 操作用户
     * @return
     * CustomerLocation 客户标注信息
     */
	List<Boolean> markCustomerLocation(List<CustomerLocation> customerLocationList,User user);

	/**
	 * 
	 * <p>
	 * Description:根据客户id查询客户地址和城市信息<br />
	 * </p>
	 * @author 石应华
	 * @version 0.1 2013-6-14
	 * @param custId
	 * @return
	 * String 客户地址
	 */
	CustomerAddressInfo searchCustomerAddressByCustId(String custId,String custType);
}
