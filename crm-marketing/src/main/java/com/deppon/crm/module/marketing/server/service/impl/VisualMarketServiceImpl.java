package com.deppon.crm.module.marketing.server.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.server.dao.ILadingstationDepartmentDao;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.marketing.server.dao.IVisualMarketDao;
import com.deppon.crm.module.marketing.server.service.IVisualMarketService;
import com.deppon.crm.module.marketing.server.utils.VisualMarketUtils;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustMonthlyArriveIncome;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustomerAddressInfo;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustomerMarketInfo;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.QueryCondition;
/**
 * 
 * <p>
 * 可视化营销之五公里地图模块
 * </p>
 * @title VisualMarketServiceImpl.java
 * @package com.deppon.crm.module.marketing.server.service.impl
 * @author 唐亮
 * @version 0.1 2013-4-20
 */
public class VisualMarketServiceImpl implements IVisualMarketService{
	//可视化营销五公里地图查询客户信息接口
	private IVisualMarketDao visualMarketDao;
	private ILadingstationDepartmentDao ladingstationDepartmentDao;
	
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
	public List<CustomerMarketInfo> searchPCOrSCMarketInfoList(QueryCondition condition,int start, int limit){
	    VisualMarketUtils.pagingParamSet(condition,start,limit);
	    return visualMarketDao.searchPCOrSCMarketInfoList(condition);
	}
	
	/**
	 * 
	 * @Title: searchCustMarketInfoList
	 *  <p>
	 * @Description: 查询固定客户信息列表
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-20
	 * @param condition 查询条件
     * @param start 记录开始条数
     * @param limit 查询记录总计条数
     * @return
     * List<CustomerMarketInfo> 固定客户信息
	 */
	@Override
	public List<CustomerMarketInfo> searchMemberMarketInfoList(
			QueryCondition condition,int start, int limit) {
	    VisualMarketUtils.pagingParamSet(condition,start,limit);
	    return visualMarketDao.searchMemberMarketInfoList(condition);
	}
	@Override
	public BussinessDept getBusDeptByDeptId(String deptId) {
		return ladingstationDepartmentDao.getBusDeptByDeptId(deptId);
	}
	public void setVisualMarketDao(IVisualMarketDao visualMarketDao) {
		this.visualMarketDao = visualMarketDao;
	}
	public void setLadingstationDepartmentDao(
			ILadingstationDepartmentDao ladingstationDepartmentDao) {
		this.ladingstationDepartmentDao = ladingstationDepartmentDao;
	}
	
	
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
	@Override
	public int countCustMarketInfoList(QueryCondition condition) {
		return visualMarketDao.countMemberCustMarketInfoList(condition);
	}
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
    public int countPSCustMarketInfoList(QueryCondition condition){
        return visualMarketDao.countPSCustMarketInfoList(condition);
    }
	@Override
	public List<CustMonthlyArriveIncome> searchHalfYearIncomeList(String custId) {
		// TODO Auto-generated method stub
		return visualMarketDao.searchHalfYearIncomeList(custId);
	}
	@Override
	public String searchVisitStatusByCustId(String id, String custType) {
		// TODO Auto-generated method stub
		return visualMarketDao.searchVisitStatusByCustId(id, custType);
	}
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
    @Override
    public CustomerMarketInfo searchMemberById(QueryCondition condition) {
        return visualMarketDao.searchMemberMarketInfo(condition);
    }
    
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
    @Override
    public CustomerMarketInfo searchPSById(QueryCondition condition) {
        return visualMarketDao.searchPCOrSCMarketInfo(condition);
    }
    /**
	 * <p>
	 * Description:从多个用户中返回正在回访的客户集合<br />
	 * @author zhangzhenwei
	 * @param id
	 * @return
	 * @version V0.1 
	 * @Date 2013-5-6
	 */
    public List searchVisitStatusByCustId(String[] ids, String custType){
    	Map<String,Object> para=new HashMap<String,Object>();
		para.put("ids", ids);
		para.put("custType", custType);
    	return visualMarketDao.searchVisitStatusByCustId(para);
    }

    /**
     * 
     * <p>
     * Description:通过客户id查询固定客户地址<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-6-14
     * @param custId
     * @return
     * String
     */
    @Override
    public CustomerAddressInfo searchMemberAddressByCustId(String custId) {
        return visualMarketDao.searchMemberAddressByCustId(custId);
    }

	/**
	 * <p>
	 * Description:<br />
	 * @author xiaohongye
	 * @param custId
	 * @return
	 * @version V0.1 
	 * @Date 2013-7-3
	 */
	@Override
	public CustomerAddressInfo searchPCCustomerAddressByCustId(String custId) {
		// TODO Auto-generated method stub
		return visualMarketDao.searchPCCustomerAddressByCustId(custId);
	}

}
