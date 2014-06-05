package com.deppon.crm.module.marketing.server.manager.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.marketing.server.manager.ICustomerGroupManager;
import com.deppon.crm.module.marketing.server.manager.ICycleManager;
import com.deppon.crm.module.marketing.server.manager.IPlanManager;
import com.deppon.crm.module.marketing.server.service.ICycleService;
import com.deppon.crm.module.marketing.server.utils.CycleValidateUtils;
import com.deppon.crm.module.marketing.shared.domain.CustGroupDayDetail;
import com.deppon.crm.module.marketing.shared.domain.CustGroupDayDetailCondition;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.domain.PathAnalysisInfo;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.marketing.shared.domain.ScheduleQueryResultObject;
import com.deppon.crm.module.marketing.shared.exception.CycleException;
import com.deppon.crm.module.marketing.shared.exception.CycleExceptionType;
import com.deppon.foss.framework.exception.BusinessException;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title CycleManager.java
 * @package com.deppon.crm.module.marketing.server.manager.impl
 * @author 苏玉军
 * @version 0.1 2012-4-13
 */

public class CycleManager implements ICycleManager {
	//标记
	private static final String MARK_COLOR_YES = "1";
	//不标记
	private static final String MARK_COLOR_NO = "0";
	
	//发货
	private static final String CUSTOMER_LEAVE = "0";
	//到货
	private static final String CUSTOMER_ARRIVED = "1";
	// 客户分组接口
	private ICustomerGroupManager customerGroupManager;
	//发货周期service
	private ICycleService cycleService;
	//计划Manager
	private IPlanManager planManager;
	// 与外部交互的客户相关的操作接口
//	private ICustomerOperate customerOperate;
//
//	public ICustomerOperate getCustomerOperate() {
//		return customerOperate;
//	}
//
//	public void setCustomerOperate(ICustomerOperate customerOperate) {
//		this.customerOperate = customerOperate;
//	}

	/**
	 * @param customerGroupManager : set the property customerGroupManager.
	 */
	public void setCustomerGroupManager(ICustomerGroupManager customerGroupManager) {
		this.customerGroupManager = customerGroupManager;
	}

	/**
	 * @param cycleService : set the property cycleService.
	 */
	public void setCycleService(ICycleService cycleService) {
		this.cycleService = cycleService;
	}

	public void setPlanManager(IPlanManager planManager) {
		this.planManager = planManager;
	}

	/**
	 * 
	 * <p>
	 * 发到货周期查询，在dao调用朱俊勇customerGroupManager中的功能，将参数出入到存储过程中<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-4-13
	 * @param condition
	 * @return List<Object>
	 * @see com.deppon.crm.module.marketing.server.manager.ICycleManager#searchCycleList(com.deppon.crm.module.marketing.shared.domain.CycleCondition)
	 */
	@SuppressWarnings({ "unchecked", "serial" })
	@Override
	public Map<String, Object> searchCycleList(CustomerGroupDetail condition,
			int start, int limit,User user) {
		try {
			//查询条件验证
			if (CycleValidateUtils.isConditonNull(condition)) {
				//条件未选择异常
				throw new CycleException(CycleExceptionType.oneConditionIsMust);
			}
			condition.setCyclePage(true);
			if(!StringUtils.isEmpty(condition.getDeptId())){
				//根据所选部门的id查询权限下部门id
//				List<String> depts = planManager.
//						searchAuthBussinessDeptByParentId(condition.getDeptId(), user.getEmpCode().getEmpCode());
//				condition.setDeptIds(depts);
				//由于大区、营业区查询速度缓慢，暂时不做处理by肖红叶
				List<String> deptIds = new ArrayList<String>();
				deptIds.add(condition.getDeptId());
				condition.setDeptIds(deptIds);
			}
			//查询客户发货周期
			 Map<String,Object> map = customerGroupManager.getCustomerGroupDetailListByCondition(condition, start, limit);
//			 获得数据
//			 List<CustomerGroupDetail> details = (List<CustomerGroupDetail>) map.get("data");
			 //当前时间
//			 Date current = new Date();
//			 //遍历查询结果
//			 for(CustomerGroupDetail detail : details){
//				 //默认不标记
//				 detail.setIsMarkColor(MARK_COLOR_NO);
//				 //客户编码
//				 String custNum = detail.getCustNumber();
//				 //部门ID
//				 String deptId =  detail.getDeptId();
//				 //查询日期
//				 Date queryDate = condition.getQueryDate();
//				 if(StringUtils.isNotEmpty(detail.getCustNumber())){
//					 //查询该客户的最后一次发货时间
//					 Date lastDate = cycleService.queryLastTimeByCustNum(custNum,deptId,queryDate);
//					 //计算是否标记
//					 if(lastDate!=null && detail.getSendGoodCount()>0 && 
//							 (current.getTime()-lastDate.getTime())/24/60/60/1000-3>30/detail.getSendGoodCount()){
//						 //标记
//						 detail.setIsMarkColor(MARK_COLOR_YES);
//					 }
//				 }
//			 }
			 //返回结果
			 return map;
		} catch (BusinessException e) {
			//异常转换
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	}

	/**
	 * 
	 * <p>
	 * 联系人回访记录查询<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-4-13
	 * @param custId
	 *            ，客户Id contactId关联联系人Id
	 * @return List<ReturnVisit>
	 * @see com.deppon.crm.module.marketing.server.manager.ICycleManager#searchReturnVisitRecords(java.lang.String)
	 */
	@Override
	public List<ReturnVisit> searchReturnVisitRecords(String custId) {
		//查询回访记录
		return cycleService.searchReturnVisitRecords(custId);
	}

	/**
	 * 
	 * <p>
	 * 线路分析数据查询，调用接口<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-4-16
	 * @param memberNumber
	 *            客户编码
	 * @param endDate
	 *            结束日期
	 * @return List<PathAnalysisInfo>
	 * @throws CrmBusinessException
	 * @see com.deppon.crm.module.marketing.server.manager.ICycleManager#searPathAnalysisInfos(java.lang.String,
	 *      java.util.Date)
	 */
	@SuppressWarnings("serial")
	@Override
	public List<PathAnalysisInfo> searPathAnalysisInfos(String memberNumber,Date endDate,String type,String sendGoodsType){
		try {	
			String analysisCustomerType = null;
			//客户编码为空
			if(StringUtils.isEmpty(memberNumber)){
				//客户为空异常
				throw new CycleException(CycleExceptionType.custNumberIsNull);
			}
			//结束时间为空
			if(endDate==null){
				//查询日期为空异常
				throw new CycleException(CycleExceptionType.queryDateIsNull);
			}
			if(StringUtils.isEmpty(type)){
				//出发到达类型为空
				throw new CycleException(CycleExceptionType.leaveArriveTypeIsNull);
			}
			
			//线路分析数据
			List<PathAnalysisInfo> list=new ArrayList<PathAnalysisInfo>();
			//查询日期
			int days = this.getDateDay(endDate);
			//某日查询不到，则向前推一天继续查询
			for(int i = 0 ; i< days ; i++){
				//0 为发货线路分析
				if(type.equals("0")){
					//零担
					if(MarketingConstance.SEND_TRUCKLOAD.equals(sendGoodsType)){
						list = cycleService.queryDeliverPathAnalysis(memberNumber, endDate);
					}else{
						list = cycleService.queryExpressDeliverPathAnalysis(memberNumber, endDate);
					}
				}else{
					//其他为到货线路分析
					//analysisCustomerType = CUSTOMER_ARRIVED;
					if(MarketingConstance.SEND_TRUCKLOAD.equals(sendGoodsType)){
						list = cycleService.queryReceivePathAnalysis(memberNumber, endDate);
					}else{
						list = cycleService.queryExpressReceivePathAnalysis(memberNumber, endDate);
					}
				}
				//查询线路分析数据
				//list = customerOperate.queryPathAnalysis(memberNumber, endDate, analysisCustomerType);
//				list = cycleService.queryPathAnalysis(memberNumber, endDate, analysisCustomerType);
				//值不为空，停止
				if(list!=null && list.size()!=0){
					break;
				}
				//否则日期更改
				endDate=changeDate(endDate);
			}				
			//返回结果
			return list;
		}catch(BusinessException e){
			//业务异常
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,e.getErrorArguments()) {
			};
		}
	}
	/**
	 * 
	 * <p>
	 * 更改日期，让begindate - 1<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-28
	 * @param beginDate
	 * @return
	 * Date
	 */
	public Date changeDate(Date beginDate){
		//实例
		Calendar date = Calendar.getInstance();
		//设置时间
		date.setTime(beginDate);
		//日期减一
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		//获得时间
		Date d=date.getTime();
		//返回
		return d;
	}
	/**
	 * 
	 * <p>
	 * 获取给定日期月的天数
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-28
	 * @param date
	 * @return
	 * int
	 */
	public int getDateDay(Date date){
		//日期实例
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			//设置时间
			calendar.setTime(date);
		}
		//返回天数
		return  calendar.get(Calendar.DATE);
	}
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
	 * @see com.deppon.crm.module.marketing.server.manager.ICycleManager#createScheduleFromCycle(java.lang.String[])
	 */
	@SuppressWarnings("serial")
	@Override
	public List<ScheduleQueryResultObject> searchCustFromCycle(List<String> ids,int start,int limit,User user) {
		try {
			// 合法性检验
			if(ids==null || ids.size()<1){
				//未选择客户异常
				throw new CycleException(CycleExceptionType.noCustIdHaveSelected);
			}
			//结果定义
			List<ScheduleQueryResultObject> allObjs = new ArrayList<ScheduleQueryResultObject>();
			//部门ID
			String deptId = user.getEmpCode().getDeptId().getId();
			//分页查询
			allObjs = cycleService.searchCustFromCycle(ids,start,limit,deptId);
			//返回对象
		    return allObjs;
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,e.getErrorArguments()) {
			};
		}
		
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.ICycleManager#countForSearchCustFromCycle(java.util.List, com.deppon.crm.module.authorization.shared.domain.User)
	 */
	@Override
	public int countForSearchCustFromCycle(List<String> ids, User user) {
		//部门ID
		String deptId = user.getEmpCode().getDeptId().getId();
		//总数
		return cycleService.countForSearchCustFromCycle(ids,deptId);
	}
	 /**
	 * 
	 * <p>
	 * 查询发到货周期表的客户详情<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-11-26
	 * @param condition
	 * @return List<CustomerGroupDetail>
	 */
	 public List<CustGroupDayDetail> searchCustomerGroupDetailDayAmount(CustGroupDayDetailCondition condition){
		 return customerGroupManager.searchCustomerGroupDetailDayAmount(condition);
	 }
}
