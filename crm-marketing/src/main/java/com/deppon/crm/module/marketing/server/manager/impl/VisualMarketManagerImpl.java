/**
 * <p>
 * Description:可视化营销之五公里地图模块<br />
 * </p>
 * @title VisualMarketManagerImpl.java
 * @package com.deppon.crm.module.marketing.server.manager.impl
 * @author xiaohongye
 * @version V0.1 
 * @Date 2013-4-18
 */
package com.deppon.crm.module.marketing.server.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.server.manager.ICustLabelManager;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.CustomerLocation;
import com.deppon.crm.module.customer.shared.domain.Label;
import com.deppon.crm.module.marketing.server.manager.ICustomerCallManager;
import com.deppon.crm.module.marketing.server.manager.IVisualMarketManager;
import com.deppon.crm.module.marketing.server.service.IVisualMarketService;
import com.deppon.crm.module.marketing.server.utils.VisualMarketUtils;
import com.deppon.crm.module.marketing.server.utils.VisualMarketValidator;
import com.deppon.crm.module.marketing.shared.domain.CustomerCallVO;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinionVo;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustIntegrationInfoVO;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustMonthlyArriveIncome;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustomerAddressInfo;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustomerMarketInfo;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.QueryCondition;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.VisualMarketConstance;
import com.deppon.foss.framework.exception.BusinessException;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.sso.util.StringUtil;

/**
 * <p>
 * Description:可视化营销之五公里地图模块<br />
 * </p>
 * @title VisualMarketManagerImpl.java
 * @package com.deppon.crm.module.marketing.server.manager.impl
 * @author xiaohongye
 * @version V0.1 
 * @Date 2013-4-18
 */
public class VisualMarketManagerImpl implements IVisualMarketManager{
    //查询客户回访信息时需要使用的接口
    private ICustomerCallManager customerCallManager;
    /*2013-04-19 唐亮开始添加*/
    //查询该部门下标签时需要使用的接口
    private ICustLabelManager custLabelManager;
    //查询客户信息列表时需要的接口 
    private IVisualMarketService visualMarketService;
    //修改客户标注信息
    private IAlterMemberManager memberManager;
    
	/**
     * <p>
     * Description: 查询已在地图上标记的客户
     * </p>
     * @author 石应华
     * @param condition
     * @return List<CustomerMarketInfo>    
     * @version
     * @date 2013-4-25 上午10:55:43
     */
    public List<CustomerMarketInfo> searchMarkCustInfoList(
            QueryCondition condition,User user) {
        //判断部门id是否为空
        VisualMarketValidator.checkDeptId(condition.getDeptId());
        //通过部门id查询部门
        BussinessDept dept = visualMarketService.getBusDeptByDeptId(condition.getDeptId());
        //判断部门是否为营业部
        if(!VisualMarketUtils.isBussinessDept(dept)){
            return null;
        }
        //如果客户查询未标记客户
        //且客户编码和客户id都为空
        //则返回为空
        if(VisualMarketConstance.MAP_UNMAKER.equals(condition.getMapMakerStatus()) && (StringUtils.isEmpty(condition.getCustNumber()) && StringUtils.isEmpty(condition.getCustId()))){
            //返回空
            return null;
        }
        //设置查询标记客户
        condition.setMapMakerStatus(VisualMarketConstance.MAP_MAKED);
        //返回查询数据
        return searchCustMarketInfoList(condition,user,VisualMarketConstance.FIRST_COL,VisualMarketConstance.RECORD_NUM);
    }
    
    /**
     * <p>
     * Description:查询客户信息列表<br />
     * @author tangliang
     * @param condition
     * @param user
     * @param start
     * @param limit
     * @return List<CustomerMarketInfo>
     * @version V0.1 
     * @Date 2013-4-18
     */
    @Override
    public List<CustomerMarketInfo> searchCustMarketInfoList(
            QueryCondition condition, User user, int start, int limit) {
        //校验可视化视图查询查询条件
        VisualMarketValidator.checkSearchCustMarketInfo(condition);
        //处理查询不限的情况
        VisualMarketUtils.searchAllOperation(condition);
        //数组转list
        VisualMarketUtils.arr2List(condition);
        //如果是根据客户编码唯一查询
        if(!StringUtils.isEmpty(condition.getCustNumber())){
            //设置不查询当天新增客户
            condition.setSearchTodayCust(false);
            return visualMarketService.searchMemberMarketInfoList(condition, start, limit);
        }
        
        //如果客户类型是潜散客
        if (Constant.CRM_POTENTIAL.equals(condition.getPsCustType())
                ||Constant.CRM_SCATTER.equals(condition.getPsCustType())) {
            //如果是潜客
            if(Constant.CRM_POTENTIAL.equals(condition.getPsCustType())){
                //清除散客的查询条件
                VisualMarketUtils.clearScatterQueryCondition(condition);
                if(VisualMarketUtils.arraysContains(condition.getGoodsPotential(),"<600")){
                    condition.setSearchGoodsPotentialNull(true);
                }
                //设置要查询当天新增客户
                condition.setSearchTodayCust(true);
            //如果是散客
            }else{
                //清除潜客的查询条件
                VisualMarketUtils.clearPotentialQueryCondition(condition);
                //设置要查询当天新增客户
                condition.setSearchTodayCust(VisualMarketUtils.isSearchTodayScatter(condition));
            }
            //返回数据
            return visualMarketService.searchPCOrSCMarketInfoList(condition, start, limit);
        //如果客户类型是会员
        }else {
            //设置是否查询当天新增客户
            condition.setSearchTodayCust(VisualMarketUtils.isSearchTodayMember(condition));
            //返回数据
            return visualMarketService.searchMemberMarketInfoList(condition, start, limit);
        }
        
    }
    
    /**
     * 
     * @Title: countCustMarketInfoList
     *  <p>
     * @Description:统计客户信息总条数
     * </p>
     * @author 唐亮
     * @version 0.1 2013-4-25
     * @return int
     */
    @Override
    public int countCustMarketInfoList(QueryCondition condition) {
      //校验可视化视图查询查询条件
        VisualMarketValidator.checkSearchCustMarketInfo(condition);
        //处理查询不限的情况
        VisualMarketUtils.searchAllOperation(condition);
        //数组转list
        VisualMarketUtils.arr2List(condition);
        //如果是潜散客
        if (Constant.CRM_POTENTIAL.equals(condition.getPsCustType())
                ||Constant.CRM_SCATTER.equals(condition.getPsCustType())){
            //如果是潜客
            if(Constant.CRM_POTENTIAL.equals(condition.getPsCustType())){
                if(VisualMarketUtils.arraysContains(condition.getGoodsPotential(),"<600")){
                    condition.setSearchGoodsPotentialNull(true);
                }
                //清除散客的查询条件
                VisualMarketUtils.clearScatterQueryCondition(condition);
            //如果是散客    
            }else{
              //清除潜客的查询条件
                VisualMarketUtils.clearPotentialQueryCondition(condition);
            }
            //返回潜散客信息
            return visualMarketService.countPSCustMarketInfoList(condition);
            //如果是固定客户
        }else{
            //返回固定客户信息-
            return visualMarketService.countCustMarketInfoList(condition);
        }
    }

	/**
	 * <p>
	 * Description:查询单个客户的浮窗信息：包括回访信息页签、客户基本信息页签和近半年出发收入页签<br />
	 * @author xiaohongye
	 * @param custMarketInfo
	 * @param user
	 * @return CustIntegrationInfoVO
	 * @version V0.1 
	 * @Date 2013-4-18
	 */
	@Override
	public CustIntegrationInfoVO searchCustIntegrationInfoVO(
			CustomerMarketInfo custMarketInfo, User user) {
		//浮窗中弹出的客户信息，包括客户信息页签、交易信息页签（固定客户）、回访信息页签
		CustIntegrationInfoVO custInteInfoVO = new CustIntegrationInfoVO();
		//查询回访信息实体：需要客户的类型和客户id,其中固定客户的客户类型为MEMBER
		CustomerCallVO customerCallVo = new CustomerCallVO();
		//设置客户id
		customerCallVo.setId(custMarketInfo.getCustId());
		//设置客户类型
		customerCallVo.setContactType(custMarketInfo.getCustType());
		//查询数据
		Map<String,Object> rvOpinionMap = customerCallManager.queryRvOptionByMarketingIds(customerCallVo,user, 0, 10);
		@SuppressWarnings("unchecked")
		//得到data
		List<ReturnVisitOpinionVo> rvOpinionList = (List<ReturnVisitOpinionVo>)rvOpinionMap.get("data");
		//设置RvOpinionList
		custInteInfoVO.setRvOpinionList(rvOpinionList);
		//查询固定客户近半年出发收入实体
		if(VisualMarketConstance.CUST_MEMBER.equals(custMarketInfo.getCustType())){
			custInteInfoVO.setArriveIncomeList(this.searchHalfYearIncomeList(custMarketInfo.getCustId()));
		}
		//查询该客户基本信息
		CustomerMarketInfo customerMarketInfo = null;
		if(VisualMarketConstance.CUST_MEMBER.equals(custMarketInfo.getCustType())){
			//通过id查询客户
		    customerMarketInfo = this.searchCustById(custMarketInfo.getCustId(), custMarketInfo.getCustType(),custMarketInfo.getDeptId());
		}
		else{
		    //通过id查询客户
			customerMarketInfo = this.searchCustById(custMarketInfo.getPsCustId(), custMarketInfo.getCustType(),custMarketInfo.getDeptId());
		}
		//设置客户基本信息
		custInteInfoVO.setCustomerMarketInfo(customerMarketInfo);
		//返回数据
		return custInteInfoVO;
	}
	
	/**
	 * <p>
	 * Description:查询单个客户回访信息列表<br />
	 * @author xiaohongye
	 * @param custMarketInfo
	 * @param user
	 * @return
	 * @version V0.1 
	 * @Date 2013-4-18
	 */
	@Override
	public List<ReturnVisitOpinionVo> searchReturnVisitInfoList(String custId,String custType,User user) {
		//查询回访信息实体：需要客户的类型和客户id,其中固定客户的客户类型为MEMBER
		CustomerCallVO customerCallVo = new CustomerCallVO();
		//设置客户id
		customerCallVo.setId(custId);
		//设置客户类型
		customerCallVo.setContactType(custType);
		//查询数据
		Map<String,Object> rvOpinionMap = customerCallManager.queryRvOptionByMarketingIds(customerCallVo,user, VisualMarketConstance.PAGING_START, VisualMarketConstance.PAGING_RETURN_VISIT);
		//返回数据
		return (List<ReturnVisitOpinionVo>)rvOpinionMap.get("data");
	}

    /**
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
        //获得某客户近半年的收入情况（不含本月）
        return visualMarketService.searchHalfYearIncomeList(custId);
    }
    /**
     * <p>
     * Description:根据登陆用户USER的部门id查询客户标签<br />
     * @author tangliang
     * @param deptId
     * @return
     * @version V0.1 
     * @Date 2013-4-18
     */
    @Override
    public Map<String, Object> searchCustLabelByDeptId(User user,String deptId) {
        Map<String, Object> map = new HashMap<String, Object>();
        //定义客户标签
        List<Label> labelList = null;
        //是否营业部的标志位，0代表不是营业部，1代表是营业部，默认为0
        String isBusinessDept = "0";        
        /*校验部门ID是否为营业部
		        如果是营业部，则到数据库中查询该部门下的客户标签集
		        并将标志位设置为1
		*/
        //判断部门id是否为空
        VisualMarketValidator.checkDeptId(deptId);
        //通过部门id查询部门
        BussinessDept dept = visualMarketService.getBusDeptByDeptId(deptId);
        //判断部门是否为营业部
        if (VisualMarketUtils.isBussinessDept(dept)) {
            //设置客户标签
            labelList = custLabelManager.searchLabel(deptId);
            //设置是营业部
            isBusinessDept = "1";
        }
        //将结果放进返回的map中
        map.put("isBusinessDept", isBusinessDept);
        //设置返回结果
        map.put("labelList", labelList);
        return map;
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
	@SuppressWarnings("unchecked")
	@Override
	public List<String> searchVisitStatusByCustId(String[] ids, String custType){
	    //返回正在回访的客户集合
	   return visualMarketService.searchVisitStatusByCustId(ids, custType);
	}
	
	/**
	 * <p>
	 * Description:返回可以制定计划的客户信息列表和不能制定计划的客户数量<br />
	 * @author xiaohongye
	 * @param List<String> custType：客户类型 (mat  dev)  user:操作用户
	 * @return
	 * @version V0.1 
	 * @Date 2013-4-18
	 */
	@Override
	public Map<String, Object> searchCustsToCreatePlan(
		List<String> ids ,String custType,User user) {
		//最终的返回结果
		Map<String, Object> result = new HashMap<String, Object>();
		//按回访状态查询后被剔除的客户信息列表
		List<String> invalidInfoList = new ArrayList<String>();
		//客户id
		int size=ids.size();
		//转化为数组类型
		String[] idArray = new String[size];
		for(int i=0;i<size;i++){
			idArray[i]=ids.get(i);
		}
		//得到回访状态为"回访中"的客户信息集合
		List<String>  theCustOfDoingReturn = this.searchVisitStatusByCustId(idArray, custType);
		//提起可制订计划 与不可制订计划的集合
		if(theCustOfDoingReturn.size()>=1){
			for(String custid:theCustOfDoingReturn){
				invalidInfoList.add(custid);
				ids.remove(custid);
			}
		}
		//得到回访状态为"回访中"的客户信息集合长度
	    int	invalidCount = invalidInfoList.size();
	    //保持可制定计划的客户信息的映射
		result.put("ids", ids);
		//给返回结果赋值
		result.put("invalidCount", invalidCount);
		return result;
	}
	
    /**
     * 
     * <p>
     * Description:通过客户id查询客户信息<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-4
     * @param custId 客户id
     * @param custType 客户类型
     * @return
     * CustomerMarketInfo 客户信息
     */
    public CustomerMarketInfo searchCustById(String custId, String custType,String deptId) {
        VisualMarketValidator.checkSearchCustById(custId,custType);
        //如果客户类型是潜散客
        QueryCondition condition = new QueryCondition();
        //设置客户id
        condition.setCustId(custId);
        //设置部门id
        condition.setDeptId(deptId);
        //设置客户类型
        condition.setPsCustType(custType);
        //设置是否查询今天客户
        condition.setSearchTodayCust(true);
        //对分页参数进行设置
        VisualMarketUtils.pagingParamSet(condition,VisualMarketConstance.PAGING_START,VisualMarketConstance.PAGING_LIMIT);
        //如果是潜散客
        if (Constant.CRM_POTENTIAL.equals(custType)
                ||Constant.CRM_SCATTER.equals(custType)) {
            //返回结果
            return visualMarketService.searchPSById(condition);
        //如果是固定客户
        }else{
            //返回结果
            return visualMarketService.searchMemberById(condition);
        }
    }

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
	public List<Boolean> markCustomerLocation(
			List<CustomerLocation> customerLocationList,
			User user) {
		try {
			List<Boolean> result = new ArrayList<Boolean>();
				for (CustomerLocation c : customerLocationList) {
					boolean bool;
					//跨模块调用memberManager修改客户标注
					bool=memberManager.updateCustCoordinates(c);
					result.add(bool);
				}
			return result;
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	}
	
    /**
     * 
     * <p>
     * Description:根据固定客户id查询客户地址和城市信息<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-6-14
     * @param customerMarketInfo
     * @return
     * String 客户地址
     */
    public CustomerAddressInfo searchCustomerAddressByCustId(String custId,String custType) {
    	CustomerAddressInfo addressInfo = new CustomerAddressInfo();
        //判断客户id或客户类型是否为空
    	VisualMarketValidator.checkSearchCustById(custId,custType);
        if(custType.equals(VisualMarketConstance.CUST_MEMBER)){
            //查询固定客户主联系人主偏好地址和城市信息
        	addressInfo =  visualMarketService.searchMemberAddressByCustId(custId);
        }
        else{
            //查询潜散客地址和城市信息
        	addressInfo =  visualMarketService.searchPCCustomerAddressByCustId(custId);
        }
        return addressInfo;
    }
    
    /*2013-04-19 唐亮添加完毕*/
    /**
     * <p>
     * Description:setCustLabelManager<br />
     * </p>
     * @author tl
     * @version 0.1 2013-4-22
     */
    public void setCustLabelManager(ICustLabelManager custLabelManager) {
        //设置custLabelManager
        this.custLabelManager = custLabelManager;
    }
    /**
     * <p>2013-05-28
     * Description:setMemberManager<br />
     * </p>
     * @author tl
     * @version  2013-05-28
     */
    public void setMemberManager(IAlterMemberManager memberManager) {
        //设置memberManager
        this.memberManager = memberManager;
    }
       
    /**
     * @param customerCallManager the customerCallManager to set
     */
    public void setCustomerCallManager(ICustomerCallManager customerCallManager) {
        //设置customerCallManager
        this.customerCallManager = customerCallManager;
    }
    /**
     * <p>
     * Description:visualMarketService<br />
     * </p>
     * @author roy
     * @version 0.1 2013-4-23
     */
    public void setVisualMarketService(IVisualMarketService visualMarketService) {
        //设置visualMarketService
        this.visualMarketService = visualMarketService;
    }
}
