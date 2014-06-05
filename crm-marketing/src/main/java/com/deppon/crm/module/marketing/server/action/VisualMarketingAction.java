package com.deppon.crm.module.marketing.server.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.customer.shared.domain.CustomerLocation;
import com.deppon.crm.module.customer.shared.domain.Label;
import com.deppon.crm.module.marketing.server.manager.IRegionPartitionManager;
import com.deppon.crm.module.marketing.server.manager.IVisualMarketManager;
import com.deppon.crm.module.marketing.server.utils.VisualMarketValidator;
import com.deppon.crm.module.marketing.shared.domain.RegionPartition;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinionVo;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustIntegrationInfoVO;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustMonthlyArriveIncome;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustomerAddressInfo;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustomerMarketInfo;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.QueryCondition;
import com.deppon.foss.framework.exception.BusinessException;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.sso.util.StringUtil;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;
import com.deppon.crm.module.authorization.shared.domain.User;

/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title VisualMarketingAction.java
 * @package com.deppon.crm.module.marketing.server.action
 * @author roy
 * @version 0.1 2013-4-20
 */
public class VisualMarketingAction extends AbstractAction {
	//需要使用的manager
	private IVisualMarketManager visualMarketManager;
	private IRegionPartitionManager regionPartitionManager;
	//分页要用参数
	private int start;
	//每页记录数
	private int limit;
	//总行数
	private Long totalCount;
	//查询出的客户信息列表
	private List<CustomerMarketInfo> customerMarketInfoList;
	//查询出的标签列表
	private List<Label> labelList;
	//显示营销区域
	private List<RegionPartition> regionPartitionList;
	//查询条件实体
	private QueryCondition queryCondition;
	//查询出的客户标注信息实体
	private List<CustomerMarketInfo>   markCustInfos;
	//部门ID
	private String deptId;
	// 查询出的客户基本营销信息
	private CustomerMarketInfo customerMarketInfo;
	// 根据客户类型和客户id查询出的回访信息列表
	private List<ReturnVisitOpinionVo> rvOpinionList;
	// 根据固定客户客户id查询出的近半年出发收入列表
	private List<CustMonthlyArriveIncome> halfYearIncomeList;
	// 浮窗中弹出的客户信息，包括客户信息页签、交易信息页签（固定客户）、回访信息页签
	private CustIntegrationInfoVO custInteInfoVO;
	//可以制定计划的客户信息列表
	private List<CustomerMarketInfo> custListToCreatePlanList;
	//要制定计划的客户id列表
	private List<String> custIdList;
	//要制定计划的客户类型
	private String custType;
	//判断某个部门是否营业部
	private String isbusinessDept;
	//单个客户的回访信息列表
	private List<ReturnVisitOpinionVo> returnVisitInfoList;
	//单个客户的id
	private String custId;
	//单个客户的城市名称
	private String cityName;
	//客户坐标
	private CustomerLocation location;
	//客户地址
	private String custAddress;
	/**
	 * 
	 * <p>
	 * Description:根据部门id查询部门营销范围<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-5-2
	 * @return String
	 */
	@JSON
	public String findRegionPartitionListByDeptId() {
		regionPartitionList = regionPartitionManager
				.searchRegionByDeptId(deptId);
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:查询客户信息列表<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-5-2
	 * @return String
	 */
	@JSON
	public String searchCustomerMarketInfoList() {
		User user = (User) UserContext.getCurrentUser();
		//验证查询条件为空的情况
		VisualMarketValidator.checkCondition(queryCondition);
		//克隆一个查询条件，以防和标注点查询条件产生影响
		QueryCondition customerQueryCondition=(QueryCondition) queryCondition.clone();
		customerMarketInfoList = visualMarketManager.searchCustMarketInfoList(
				customerQueryCondition, user, start, limit);
		if (null != customerMarketInfoList) {
			for (int i = 0; i < customerMarketInfoList.size(); i++) {
				String address=customerMarketInfoList.get(i).getAddress();
				if(!StringUtil.isNull(address)){
				String newAddress=address.replace("-", "");
				customerMarketInfoList.get(i).setAddress(newAddress);
				}
			}

		}
		//如果第一页查询出来的条数小于分页数，则总数为查询出来的记录条数
		if(start == 0 && customerMarketInfoList.size() < limit){
		    totalCount = Long.valueOf(customerMarketInfoList.size());
		//如果第一页查询出来的条数不小于分页数，则查询数据库
		}else{
		    totalCount = Long.valueOf(visualMarketManager
				.countCustMarketInfoList(queryCondition));
		}
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:根据部门iD查询客户标签<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-4-20
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String serachCustomerlabelByDeptId() {
		User user = (User) UserContext.getCurrentUser();
		//根据部门查询客户标签
		Map<String, Object> map = visualMarketManager.searchCustLabelByDeptId(
				user, deptId);
		//设置返回客户标签
		labelList = (List<Label>) map.get("labelList");
		//设置是否为营业部
		isbusinessDept=(String) map.get("isBusinessDept");
		return SUCCESS;
	}

	/**
	 * <p>
	 * Description:返回可以制定计划的客户信息列表和不能制定计划的客户数量<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2013-5-8
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String searchCustListToCreatePlan(){
		User user = (User) UserContext.getCurrentUser();
		//查询户信息列表和不能制定计划的客户
		Map<String,Object> map = visualMarketManager.searchCustsToCreatePlan(custIdList, custType, user);
		//设置客户id
		custIdList = (List<String>) map.get("ids");
		//设置总数
		totalCount = Long.valueOf( map.get("invalidCount").toString());
		return SUCCESS;
	}

	/**
	 * <p>
	 * Description:查询单个客户的浮窗信息：包括回访信息页签、客户基本信息页签和近半年出发收入页签<br />
	 * 
	 * @author xiaohongye
	 * @version V0.1
	 * @Date 2013-4-28
	 */
	@JSON
	public String searchCustIntegrationInfoVO() {
		User user = (User) UserContext.getCurrentUser();
		//查询客户信息
		custInteInfoVO = visualMarketManager.searchCustIntegrationInfoVO(
				customerMarketInfo, user);
		//查询回访信息
		rvOpinionList = custInteInfoVO.getRvOpinionList();
		//设置半年收入
		halfYearIncomeList = custInteInfoVO.getArriveIncomeList();
		//设置客户信息
		customerMarketInfo = custInteInfoVO.getCustomerMarketInfo();
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:查询已经标注客户<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-5-3
	 * @return
	 * String
	 */
	@JSON
	public String searchMarkCustInfoList() {
		User user = (User) UserContext.getCurrentUser();
		//验证查询条件为空的情况
        VisualMarketValidator.checkCondition(queryCondition);
        //查询已经标注客户
		markCustInfos = visualMarketManager.searchMarkCustInfoList(
				queryCondition, user);
		//去除客户地址中的"-"
		if (null != markCustInfos) {
			for (int i = 0; i < markCustInfos.size(); i++) {
				String address=markCustInfos.get(i).getAddress();
				//如果地址中存在"-"则替换为空
				if(!StringUtil.isNull(address)){
					String newAddress=address.replace("-", "");
					markCustInfos.get(i).setAddress(newAddress);
				}
			}

		}
		return SUCCESS;
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
	public String markCustomerLocation() {
		try {
			//声明customerLocationList
			List<CustomerLocation> customerLocationList = new ArrayList<CustomerLocation>();
			//添加坐标
			customerLocationList.add(location);
			User user = (com.deppon.crm.module.authorization.shared.domain.User) UserContext.getCurrentUser();
					//调用visualMarketManager修改客户标注方法
					visualMarketManager.markCustomerLocation(customerLocationList, user);
			return SUCCESS;
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	}
	/**
	 * 
	 * <p>
	 * Description:查询单个客户的回访信息列表<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2013-5-14
	 */
	@JSON
	public String searchReturnVisitInfoList(){
		User user = (User) UserContext.getCurrentUser();
		//查询单个客户的回访信息列表
		returnVisitInfoList = visualMarketManager.searchReturnVisitInfoList(custId, custType, user);
		return SUCCESS;
	}
	

	/**
     * 
     * <p>
     * Description:根据客户id查询客户地址和城市名称<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-6-14
     * @return
     * String
     */
    @JSON
    public String serachCustomerAddressByCustId(){
    	CustomerAddressInfo addressInfo = new CustomerAddressInfo();
        addressInfo = visualMarketManager.searchCustomerAddressByCustId(custId,custType);
        if(addressInfo != null){
        	custAddress = addressInfo.getAddress();
        	cityName = addressInfo.getCityName();
        }
        if(StringUtil.notNull(custAddress)){
            custAddress = custAddress.replace("-", "");
        }
        return  SUCCESS;
    }
/*-------------------------------------get、set方法区------------------------------------------------------------*/
	/**
	 * <p>
	 * Description:queryCondition<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-4-22
	 */
	public QueryCondition getQueryCondition() {
		return queryCondition;
	}

	/**
	 * <p>
	 * Description:queryCondition<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-4-22
	 */
	public void setQueryCondition(QueryCondition queryCondition) {
		this.queryCondition = queryCondition;
	}
	/**
	 * <p>
	 * Description:markCustInfos<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-5-3
	 */
	public List<CustomerMarketInfo> getMarkCustInfos() {
		return markCustInfos;
	}
	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * <p>
	 * Description:labelList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-4-22
	 */
	public List<Label> getLabelList() {
		return labelList;
	}

	/**
	 * <p>
	 * Description:customerMarketInfoList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-4-20
	 */
	public List<CustomerMarketInfo> getCustomerMarketInfoList() {
		return customerMarketInfoList;
	}

	/**
	 * <p>
	 * Description:visualMarketManager<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-4-20
	 */
	public void setVisualMarketManager(IVisualMarketManager visualMarketManager) {
		this.visualMarketManager = visualMarketManager;
	}

	/**
	 * <p>
	 * Description:start<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-4-20
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * <p>
	 * Description:limit<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-4-20
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * <p>
	 * Description:totalCount<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-4-20
	 */
	public Long getTotalCount() {
		return totalCount;
	}

	/**
	 * <p>
	 * Description:totalCount<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-4-20
	 */
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * <p>
	 * Description:regionPartitionList<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public List<RegionPartition> getRegionPartitionList() {
		return regionPartitionList;
	}

	/**
	 * <p>
	 * Description:regionPartitionManager<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-4-25
	 */
	public void setRegionPartitionManager(
			IRegionPartitionManager regionPartitionManager) {
		this.regionPartitionManager = regionPartitionManager;
	}


	/**
	 * @param halfYearIncomeList the halfYearIncomeList to get
	 */
	public List<CustMonthlyArriveIncome> getHalfYearIncomeList() {
		return halfYearIncomeList;
	}
	/**
	 * @param custInteInfoVO
	 *            the custInteInfoVO to get
	 */
	public CustIntegrationInfoVO getCustInteInfoVO() {
		return custInteInfoVO;
	}

	/**
	 * @param rvOpinionList
	 *            the rvOpinionList to get
	 */
	public List<ReturnVisitOpinionVo> getRvOpinionList() {
		return rvOpinionList;
	}

	/**
	 * @param customerMarketInfo
	 *            the customerMarketInfo to get
	 */
	public CustomerMarketInfo getCustomerMarketInfo() {
		return customerMarketInfo;
	}

	/**
	 * @param customerMarketInfo
	 *            the customerMarketInfo to set
	 */
	public void setCustomerMarketInfo(CustomerMarketInfo customerMarketInfo) {
		this.customerMarketInfo = customerMarketInfo;
	}
	
	/**
	 * @param customerMarketInfoList the customerMarketInfoList to set
	 */
	public void setCustomerMarketInfoList(
			List<CustomerMarketInfo> customerMarketInfoList) {
		this.customerMarketInfoList = customerMarketInfoList;
	}
	/**
	 * @param custListToCreatePlanList the custListToCreatePlanList to get
	 */
	public List<CustomerMarketInfo> getCustListToCreatePlanList() {
		return custListToCreatePlanList;
	}
	/**
	 * @param custIdList the custIdList to get
	 */
	public List<String> getCustIdList() {
		return custIdList;
	}
	/**
	 * @param custIdList the custIdList to set
	 */
	public void setCustIdList(List<String> custIdList) {
		this.custIdList = custIdList;
	}
	/**
	 * @param custType the custType to set
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}

	/**
	 * <p>
	 * Description:isbusinessDept<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-5-11
	 */
	public String getIsbusinessDept() {
		return isbusinessDept;
	}

	/**
	 * <p>
	 * Description:isbusinessDept<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-5-11
	 */
	public void setIsbusinessDept(String isbusinessDept) {
		this.isbusinessDept = isbusinessDept;
	}
	
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}

	/**
	 * @param returnVisitInfoList the returnVisitInfoList to get
	 */
	public List<ReturnVisitOpinionVo> getReturnVisitInfoList() {
		return returnVisitInfoList;
	}

	public CustomerLocation getLocation() {
		return location;
	}

	public void setLocation(CustomerLocation location) {
		this.location = location;
	}

    public String getCustAddress() {
        return custAddress;
    }
    /**
	 * @param cityName the cityName to get
	 */
	public String getCityName() {
		return cityName;
	}
}
