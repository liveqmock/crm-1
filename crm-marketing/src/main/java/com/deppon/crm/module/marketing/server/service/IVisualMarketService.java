package com.deppon.crm.module.marketing.server.service;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustMonthlyArriveIncome;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustomerAddressInfo;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustomerMarketInfo;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.QueryCondition;

/**
 * 
 * <p>
 * 可视化营销之五公里地图模块
 * </p>
 * @title IVisualMarketService.java
 * @package com.deppon.crm.module.marketing.server.service
 * @author 唐亮
 * @version 0.1 2013-4-20
 */
public interface IVisualMarketService {
    
    /**
     * <p>
     * Description:查询潜散客户信息列表<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-6
     * @param condition 查询条件
     * @param start 记录开始条数
     * @param limit 查询记录总计条数
     * @return
     * List<CustomerMarketInfo> 查询潜散客户信息
     */
    List<CustomerMarketInfo> searchPCOrSCMarketInfoList(QueryCondition condition,int start, int limit);
    
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
	List<CustomerMarketInfo> searchMemberMarketInfoList(QueryCondition condition, int start, int limit);
	/**
	 * 
	 * @Title: getBusDeptById
	 *  <p>
	 * @Description: 根据部门ID查询该部门实体
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-24
	 * @return BussinessDept
	 * @throws
	 */
	BussinessDept getBusDeptByDeptId(String deptId);
	/**
	 * 
	 * @Title: countCustMarketInfoList
	 *  <p>
	 * @Description: 统计客户信息总条目数
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
	 * Description:统计潜散客信息总条目数<br />
	 * </p>
	 * @author 石应华
	 * @version 0.1 2013-5-11
	 * @param condition 
	 * @return
	 * int
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
	List searchVisitStatusByCustId(String[] ids, String custType);

	   /**
     * <p>
     * Description:通过客户id查询固定客户信息<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-9
     * @param condition 查询条件
     * @return
     * CustomerMarketInfo 客户信息
     */
    CustomerMarketInfo searchMemberById(QueryCondition condition);

    /**
     * <p>
     * Description:通过客户id查询潜散客信息<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-9
     * @param condition 查询条件
     * @return
     * CustomerMarketInfo 客户信息
     */
    CustomerMarketInfo searchPSById(QueryCondition condition);

    /**
     * 
     * <p>
     * Description:通过固定客户id查询固定客户地址和城市<br />
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
