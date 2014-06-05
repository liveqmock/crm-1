package com.deppon.crm.module.marketing.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.dao.IVisualMarketDao;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustMonthlyArriveIncome;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustomerAddressInfo;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustomerMarketInfo;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.QueryCondition;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
/**
 * 可视化营销之五公里地图模块
 * <p>
 * TODO<br />
 * </p>
 * @title VisualMarketDaoImpl.java
 * @package com.deppon.crm.module.marketing.server.dao.impl
 * @author 唐亮
 * @version 0.1 2013-4-20
 */
public class VisualMarketDaoImpl extends iBatis3DaoImpl implements IVisualMarketDao{
	private static final String NAMESPACE_VISUALMARKET = "com.deppon.crm.module.marketing.shared.domain.VisualMarket.";
	//查询固定客户信息列表
	private static final String  SEARCH_MEMBERINFO_LIST= "searchMemberList";
	//查询潜散客信息列表
	private static final String  SEARCH_PCORSCINFO_LIST= "selectPsList";
	//统计固定客户信息条目总数
	private static final String  COUNTMEMBERINFO = "countMemberCustInfo";
	//统计潜散客信息条目总数
	private static final String  COUNTPSINFO = "countPSCustInfo";
	//获得客户半年的收入信息 ZZW
	private static final String  HELFYEARINCOME="halfYearIncome";
	//客户当前的回访状态 	ZZW
	private static final String RETUENSTATUSOFCUST="returnstatusofcust";
	//查询固定客户主偏好地址和城市信息
	private static final String SEARCH_ADDRESS = "selectAddressById";
	//查询潜散客地址和城市信息
	private static final String SEARCH_PC_ADDRESS = "selectPCAddressById";
	private static final String THECUSTOFDOINGRETURN="ThecustOfdoingReturn";
	
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
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerMarketInfo> searchMemberMarketInfoList(
			QueryCondition condition) {
		return this.getSqlSession().selectList(NAMESPACE_VISUALMARKET+SEARCH_MEMBERINFO_LIST,condition);
	}
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
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerMarketInfo> searchPCOrSCMarketInfoList(
			QueryCondition condition) {
		return this.getSqlSession().selectList(NAMESPACE_VISUALMARKET+SEARCH_PCORSCINFO_LIST,condition);
	}
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
	@Override
	public int countMemberCustMarketInfoList(QueryCondition condition) {
		return (Integer) this.getSqlSession().selectOne(NAMESPACE_VISUALMARKET+COUNTMEMBERINFO,condition);
	}
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
	@Override
	public int countPSCustMarketInfoList(QueryCondition condition) {
		return (Integer) this.getSqlSession().selectOne(NAMESPACE_VISUALMARKET+COUNTPSINFO, condition);
	}	/**
	 * <p>
	 * Description:固定客户近半年出发收入查询<br />
	 * @author zhangzhenwei
	 * @param custId
	 * @return
	 * @version V0.1 
	 * @Date 2013-4-18
	 */
	@Override
	public List<CustMonthlyArriveIncome> searchHalfYearIncomeList(String custId) {
		return getSqlSession().selectList(NAMESPACE_VISUALMARKET+HELFYEARINCOME, custId);	
	}/**
	 * <p>
	 * Description:根据登陆用户的部门id查询营销区域<br />
	 * @author zhangzhenwei
	 * @param user
	 * @return
	 * @version V0.1 
	 * @Date 2013-4-18
	 */
	@Override
	public String searchMarketScopeByDeptId(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * <p>
	 * Description:根据客户id实时查询单个客户的回访状态<br />
	 * @author zhangzhenwei
	 * @param id
	 * @return
	 * @version V0.1 
	 * @Date 2013-4-18
	 */
	@Override
	public String searchVisitStatusByCustId(String id, String custType) {
		Map<String,String> para=new HashMap<String,String>();
		para.put("id", id);
		para.put("custType", custType);
		return (String) this.getSqlSession().selectOne(NAMESPACE_VISUALMARKET+RETUENSTATUSOFCUST, para);
	}
	/**
	 * <p>
	 * Description:多个用户中返回正在回访的客户集合<br />
	 * @author zhangzhenwei
	 * @param id
	 * @return
	 * @version V0.1 
	 * @Date 2013-5-6
	 */
	@SuppressWarnings("unchecked")
	public List<String> searchVisitStatusByCustId(Map<String,Object> para) {
		return this.getSqlSession().selectList(NAMESPACE_VISUALMARKET+THECUSTOFDOINGRETURN, para);
	}
	
	/**
     * 
     * @Title: searchMemberMarketInfo
     *  <p>
     * @Description: 查询固定客户信息列表
     * </p>
     * @author 石应华
     * @version 0.1 2013-4-22
     * @return List<CustomerMarketInfo>
     * @throws
     */
    public CustomerMarketInfo searchMemberMarketInfo(
            QueryCondition condition) {
        return (CustomerMarketInfo)this.getSqlSession().selectOne(NAMESPACE_VISUALMARKET+SEARCH_MEMBERINFO_LIST,condition);
    }
    /**
     * 
     * @Title: searchPCOrSCMarketInfo
     *  <p>
     * @Description: 查询潜散客客户信息列表
     * </p>
     * @author 石应华
     * @version 0.1 2013-4-22
     * @return searchPCOrSCMarketInfo
     * @throws
     */
    @Override
    public CustomerMarketInfo searchPCOrSCMarketInfo(QueryCondition condition) {
        return (CustomerMarketInfo)this.getSqlSession().selectOne(NAMESPACE_VISUALMARKET+SEARCH_PCORSCINFO_LIST,condition);
    }
    
    /**
     * 
     * <p>
     * Description:通过固定客户id查询固定客户地址和城市信息<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-6-14
     * @param condition
     * @return
     * String
     */
    @Override
    public CustomerAddressInfo searchMemberAddressByCustId(String custId) {
        return (CustomerAddressInfo)this.getSqlSession().selectOne(NAMESPACE_VISUALMARKET+SEARCH_ADDRESS,custId);
    }
    
    /**
     * 
     * <p>
     * Description:通过潜散客id查询潜散客地址和城市信息<br />
     * </p>
     * @author xiaohongye
     * @version 0.1 2013-7-3
     * @param condition
     * @return
     * String
     */
    @Override
    public CustomerAddressInfo searchPCCustomerAddressByCustId(String custId) {
        return (CustomerAddressInfo)this.getSqlSession().selectOne(NAMESPACE_VISUALMARKET+SEARCH_PC_ADDRESS,custId);
    }
    

}
