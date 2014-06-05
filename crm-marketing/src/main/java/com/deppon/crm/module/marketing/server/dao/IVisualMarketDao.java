package com.deppon.crm.module.marketing.server.dao;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustMonthlyArriveIncome;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustomerAddressInfo;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustomerMarketInfo;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.QueryCondition;

/**
 * 
 * <p>
 * 可视化营销之五公里地图模块
 * </p>
 * @title IVisualMarketDao.java
 * @package com.deppon.crm.module.marketing.server.dao
 * @author 唐亮
 * @version 0.1 2013-4-20
 */
public interface IVisualMarketDao {
	/**
	 * 
	 * @Title: searchCustMarketInfoList
	 *  <p>
	 * @Description: 查询固定客户信息列表
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-20
	 * @return List<CustomerMarketInfo>
	 * @throws
	 */
	List<CustomerMarketInfo> searchMemberMarketInfoList(QueryCondition condition);
	/**
     * 
     * @Title: searchCustMarketInfo
     *  <p>
     * @Description: 查询固定客户信息列表
     * </p>
     * @author 石应华
     * @version 0.1 2013-4-20
     * @return CustomerMarketInfo
     * @throws
     */
	CustomerMarketInfo searchMemberMarketInfo(QueryCondition condition);

	/**
	 * 
	 * @Title: searchPCOrSCMarketInfoList
	 *  <p>
	 * @Description: 查询潜散客客户信息列表
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-22
	 * @return List<CustomerMarketInfo>
	 * @throws
	 */
	List<CustomerMarketInfo> searchPCOrSCMarketInfoList(QueryCondition condition);
	
	/**
     * 
     * @Title: searchPCOrSCMarketInfo
     *  <p>
     * @Description: 查询潜散客客户信息列表
     * </p>
     * @author 石应华
     * @version 0.1 2013-4-22
     * @return CustomerMarketInfo
     * @throws
     */
    CustomerMarketInfo searchPCOrSCMarketInfo(QueryCondition condition);

	/**
	 * 
	 * @Title: countCustMarketInfoList
	 *  <p>
	 * @Description: 统计固定客户信息总条目数
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-25
	 * @return int
	 * @throws
	 */
	int countMemberCustMarketInfoList(QueryCondition condition);
	/**
	 * 
	 * @Title: countPSCustMarketInfoList
	 *  <p>
	 * @Description: 统计潜散客信息总条目数
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-25
	 * @return int
	 * @throws
	 */
	int countPSCustMarketInfoList(QueryCondition condition);
	/**
	 * <p>
	 * Description:固定客户近半年出发收入查询<br />
	 * @author zhangzhenwei
	 * @param custId
	 * @return
	 * @version V0.1 
	 * @Date 2013-4-18
	 */
	List<CustMonthlyArriveIncome> searchHalfYearIncomeList(String custId) ;
	/**
	 * <p>
	 * Description:根据登陆用户的部门id查询营销区域<br />
	 * @author zhangzhenwei
	 * @param user
	 * @return
	 * @version V0.1 
	 * @Date 2013-4-18
	 */
	String searchMarketScopeByDeptId(User user);
	/**
	 * <p>
	 * Description:根据客户id实时查询当个客户的回访状态<br />
	 * @author zhangzhenwei
	 * @param id
	 * @return
	 * @version V0.1 
	 * @Date 2013-4-18
	 */
	String searchVisitStatusByCustId(String id,String custType);
	/**
	 * <p>
	 * Description:从多个用户中返回正在回访的客户集合<br />
	 * @author zhangzhenwei
	 * @param id
	 * @return
	 * @version V0.1 
	 * @Date 2013-5-6
	 */
	List<String> searchVisitStatusByCustId(Map<String,Object> para);
	
	/**
	 * 
	 * <p>
	 * Description:通过固定客户id查询固定客户地址和城市信息<br />
	 * </p>
	 * @author 石应华
	 * @version 0.1 2013-6-14
	 * @param custId
	 * @return
	 * String
	 */
	CustomerAddressInfo searchMemberAddressByCustId(String custId);
    
    /**
	 * 
	 * <p>
	 * Description:通过潜散客id查询潜散客地址和城市信息<br />
	 * </p>
	 * @author xiaohongye
	 * @version 0.1 2013-7-3
	 * @param custId
	 * @return
	 * String
	 */
	CustomerAddressInfo searchPCCustomerAddressByCustId(String custId);
    
}
