/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReturnVisitManager.java
 * @package com.deppon.crm.module.marketing.server.manager.impl 
 * @author ZhuPJ
 * @version 0.1 2012-3-24
 */
package com.deppon.crm.module.marketing.server.manager.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.MemberExtend;
import com.deppon.crm.module.custrepeat.server.manager.IRepeatedCustManager;
import com.deppon.crm.module.marketing.server.manager.IBusinessOpportunityManager;
import com.deppon.crm.module.marketing.server.manager.IPlanManager;
import com.deppon.crm.module.marketing.server.manager.IQuestionnaireManager;
import com.deppon.crm.module.marketing.server.manager.IReturnVisitManager;
import com.deppon.crm.module.marketing.server.manager.IScheduleManager;
import com.deppon.crm.module.marketing.server.manager.IWarnLostCustManager;
import com.deppon.crm.module.marketing.server.service.IReturnVisitService;
import com.deppon.crm.module.marketing.server.utils.ReturnVisitValidateUtils;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.domain.PlanDevelopCondition;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinion;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinionVo;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotential;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotentialVo;
import com.deppon.crm.module.marketing.shared.domain.Schedule;
import com.deppon.crm.module.marketing.shared.domain.WarnStatusUpdateInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.AnswerMainInfo;
import com.deppon.crm.module.marketing.shared.exception.ReturnVisitException;
import com.deppon.crm.module.marketing.shared.exception.ReturnVisitExceptionType;
import com.deppon.foss.framework.exception.BusinessException;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportuntiyConstants;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReturnVisitManager.java
 * @package com.deppon.crm.module.marketing.server.manager.impl 
 * @author ZhuPJ
 * @version 0.1 2012-3-24
 */

public class ReturnVisitManager implements IReturnVisitManager {
	// 回访Service
	private IReturnVisitService rvService = null;
	//计划manager
	private IPlanManager planManager = null;

	//日程Manager
	private IScheduleManager scheduleManager;
	//客户manager
	private IMemberManager memberManager;
	//流失预警manager
	private IWarnLostCustManager warnLostCustManagerImp;
	//问卷管理mananger
	private IQuestionnaireManager questionnaireManagerImpl;
	//疑重客户管理
	private IRepeatedCustManager repeatedCustManager;
	//商机管理
	private IBusinessOpportunityManager businessOpportunityManager;
	public void setQuestionnaireManagerImpl(
			IQuestionnaireManager questionnaireManagerImpl) {
		this.questionnaireManagerImpl = questionnaireManagerImpl;
	}

	public void setWarnLostCustManagerImp(
			IWarnLostCustManager warnLostCustManagerImp) {
		this.warnLostCustManagerImp = warnLostCustManagerImp;
	}

	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}

	/**
	 * @param scheduleManager : set the property scheduleManager.
	 */
	public void setScheduleManager(IScheduleManager scheduleManager) {
		this.scheduleManager = scheduleManager;
	}

	/**
	 * @param rvService : set the property rvService.
	 */
	public void setRvService(IReturnVisitService rvService) {
		this.rvService = rvService;
	}
	
	/**
	 * @param planManager : set the property planManager.
	 */
	public void setPlanManager(IPlanManager planManager) {
		this.planManager = planManager;
	}	

	/**
	 * @param repeatedCustManager : set the property repeatedCustManager. 
	 */
	public void setRepeatedCustManager(IRepeatedCustManager repeatedCustManager) {
		this.repeatedCustManager = repeatedCustManager;
	}

	/**
	 * @param businessOpportunityManager : set the property businessOpportunityManager. 
	 */
	public void setBusinessOpportunityManager(
			IBusinessOpportunityManager businessOpportunityManager) {
		this.businessOpportunityManager = businessOpportunityManager;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IReturnVisitManager#getReturnVisitList()
	 */
	@SuppressWarnings("serial")
	@Override
	public Map<String, Object> getReturnVisitList(ReturnVisitQueryCondition condition, int start, int limit, User user) {
		try {
			//返回结果声明
			Map<String, Object> result = new HashMap<String, Object>();
			//部门ID为空
			if(StringUtils.isEmpty(condition.getDeptId())){
				//必须选择部门异常
				throw new ReturnVisitException(ReturnVisitExceptionType.mustSelectDept);
			}else{
				//20131226如果查询部门不为空，则调用方法查询其权限部门下所有部门
				List<String> deptIds = planManager.
						searchAuthBussinessDeptByParentId(condition.getDeptId(), user.getEmpCode().getEmpCode());
				//将部门编码列表赋给查询条件
				condition.setDeptIds(deptIds);
			}
			//验证查询条件是否合法
			if (ReturnVisitValidateUtils.canGetReturnVisitList(condition)){
				//客户类型
//				String custType = condition.getCustomerType();
//				//客户类型不为空
//				if (StringUtils.isNotEmpty(custType)){
//					// 根据客户类别确定回访类型
//					if (condition.getCustomerType().equals(MarketingConstance.POTENTIAL_CUSTOMER) || 
//							condition.getCustomerType().equals(MarketingConstance.SCATTER_CUSTOMER)){
//						//若是潜散客，则设置开发
//						condition.setScheType(MarketingConstance.DEVELOP_TYPE);
//					}else if (condition.getCustomerType().equals(MarketingConstance.MEMBER)){
//						//若是固定客户，设置维护
//						condition.setScheType(MarketingConstance.MAINTAIN_TYPE);
//					}
//				}
				//联系人姓名不空
				if (StringUtils.isNotEmpty(condition.getLinkName())) {
					//设置模糊查询
					condition.setLinkName("%" + condition.getLinkName() + "%");
				}
				//权限部门
				List<String> deptIds= new ArrayList<String>();  
//				deptIds.addAll(user.getDeptids());
//				condition.setDeptIds(deptIds);
				//查询结果
				List<ReturnVisit> list = null;
				//总数
				int count = 0;
				// set scheduleIds
				if (StringUtils.isNotBlank(condition.getPlanName())) {
					deptIds.add(condition.getDeptId());
					// 输入计划主题时，根据计划主题查找日程
					String[] depts = new String[deptIds.size()];
					//转换
					deptIds.toArray(depts);
					//查询条件
					PlanDevelopCondition plancon = new PlanDevelopCondition();
					//设置计划名称
					plancon.setPlanName(condition.getPlanName());
					//plancon.setExecuteDepts(depts);
					//计划开始时间
					plancon.setPlanStartDate(condition.getBeginTime());
					//计划结束时间
					plancon.setPlanOverDate(condition.getEndTime());
					//执行部门
					plancon.setExecuteDepts(depts);
					//查询日程ID
					List<String> scheIds = planManager.searchSchedulesByPlanNameDim(plancon);
					//查询结果不空
					if(CollectionUtils.isEmpty(scheIds)){
						// 未找到匹配计划时，返回空
						condition.setScheduleIds(null);
						//封装结果
						result.put("data", list);
						result.put("count", count);
						//返回结果
						return result;
					}else{
						//设置日程ID
						condition.setScheduleIds(scheIds);
					}
				}
				//查询营销记录
				list = rvService.getReturnVisitList(condition, start, limit);
				//查询营销总数
				count = rvService.getReturnVisitCount(condition);
				//封装结果到Map
				result.put("data", list);
				//封装结果到Map
				result.put("count", count);
			}
			return result;
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,e.getErrorArguments()) {
			};
		}
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IReturnVisitManager#getReturnVisit(int)
	 */
	@Override
	public Map<String, Object> getReturnVisit(int id) {
		//根据ID查询营销记录
		ReturnVisit returnVisit = rvService.getReturnVisit(id);
		//根据ID查询客户意见列表
		List<ReturnVisitOpinion> returnVisitOpinionList = rvService.getReturnVisitOpinionList(id);
		//根据ID查询客户发货潜力
		List<ReturnVisitVolumePotential> returnVisitVolumePotentialList = rvService.getReturnVisitVolumePotential(id);
		//结果声明
		Map<String, Object> result = new HashMap<String, Object>();
		//封装营销主记录
		result.put("returnVisit", returnVisit);
		//封装客户意见
		result.put("returnVisitOpinion", returnVisitOpinionList);
		//封装发货潜力
		result.put("returnVisitVolumePotential", returnVisitVolumePotentialList);
		//返回结果
		return result;
	}

	/**
	 * 
	 * <p>
	 * 创建回访记录<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-29
	 * @param rv
	 * @param rvoList
	 * @param rvvpList
	 * @param user
	 * @return boolean
	 */
	@SuppressWarnings("serial")
	@Transactional
	public boolean createReturnvisit(ReturnVisit rv,
			List<ReturnVisitOpinion> rvoList,
			List<ReturnVisitVolumePotential> rvvpList,
			List<AnswerMainInfo> answerMainInfoList,
			WarnStatusUpdateInfo warnInfo,User user) {
		//执行结果定义
		boolean rs = false;
		try {
			//日程
			Schedule sche = null;
			//营销记录列表
			List<ReturnVisit> list = new ArrayList<ReturnVisit>();
			//回返日程不为空
			if (StringUtils.isNotEmpty(rv.getScheduleId())){
				ReturnVisitQueryCondition condition = this.fetchReturnVisitQueryCondition(rv);
				//返回查询结果
				list = rvService.getReturnVisitList(condition, 0, 10);
			}
			// 数据验证
			ReturnVisitValidateUtils.canCreateReturnVisit(rv, list, rvoList,answerMainInfoList);
			//保存营销记录
			rs = saveChooseForReturnVisit(rv, rvoList, rvvpList,answerMainInfoList,warnInfo,user);
			// 更新日程状态——已完成
			sche = scheduleManager.getScheduleById(rv.getScheduleId());
			//日程不为空
			updateScheduleStatus(sche);
			// 创建跟踪日程
			createTrackSchdule(rv, user);
			// 回访同步
			synFixPlanReturnVisit(rv,rvoList,rvvpList);
			if(sche!=null && ! BusinessOpportuntiyConstants.BO_COME_BUSINESS.equals(sche.getComeForm())){
				//更新客户扩展信息
				updateMemberExtend(rv, rvvpList);
			}
			//返回执行结果
			return rs;
		} catch (BusinessException e) {
			//异常转换
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,e.getErrorArguments()) {
			};
		}
	}
	private void updateScheduleStatus(Schedule sche){
		//日程不为空
		if(sche!=null){
			//如果是有计划日程，且日程未完成或者过期
			if (StringUtils.isEmpty(sche.getPlanId())
					|| (!sche.getStatus().equals(
							MarketingConstance.SCHEDULE_FINISH) && !sche
							.getStatus().equals(
									MarketingConstance.SCHEDULE_OVERDUE))){
				// 更新无计划 或 有计划（未完成，未过期）的日程
				sche.setStatus(MarketingConstance.SCHEDULE_FINISH);
				//更新日程状态
				scheduleManager.updateScheduleStatus(sche);
			}					
		}
	}
	/**
	 * 
	 * <p>
	 * Description:回访记录保存方法抽取<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014年3月27日
	 * @param rv
	 * @param rvoList
	 * @param rvvpList
	 * @param dto
	 * @param warnInfo
	 * @return
	 * ReturnVisit
	 */
	private boolean saveChooseForReturnVisit(ReturnVisit rv,
			List<ReturnVisitOpinion> rvoList,
			List<ReturnVisitVolumePotential> rvvpList,
			List<AnswerMainInfo> answerMainInfoList,
			WarnStatusUpdateInfo warnInfo,User user){
		//回访类型不为空且日程类型不为空
		if (StringUtils.isEmpty(rv.getReturnVisitType()) && StringUtils.isNotEmpty(rv.getScheType())) {
			//设置回访类型
			rv.setReturnVisitType(rv.getScheType());
		}
		 boolean rs = rvService.saveReturnVisit(rv, rvoList, rvvpList);
		 if(rs){
			 //存在问卷
			 if(null!=answerMainInfoList && answerMainInfoList.size() >0 && StringUtils.isNotEmpty(answerMainInfoList.get(0).getQuestionnaireId())){
				 //为问卷回访的答案信息实体设置回访id
				 for(int i = 0; i < answerMainInfoList.size(); i++){
					 answerMainInfoList.get(i).setVisitId(rv.getId());
				 }
				 questionnaireManagerImpl.saveCustAnswer(answerMainInfoList);
			 }else if(warnInfo!=null){
				 //设置预警回访ID
				 warnInfo.setrVid(Integer.valueOf(rv.getId()));
				 warnInfo.setModifyEmpId(Integer.parseInt(user.getEmpCode().getId()));
				 warnLostCustManagerImp.updateWarnInfo(warnInfo);
			 }
		 }
		 return rs;
	}
	/**
	 * 
	 * <p>
	 * 创建客户跟踪日程<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014年3月26日
	 * @param rv
	 * @param user
	 * void
	 */
	private void createTrackSchdule(ReturnVisit rv,User user){
		if (ReturnVisitValidateUtils.canCreateSchedule(rv)){
			//创建日程
			scheduleManager.createSchedule(createScheduleObj(rv),user);				
		}
	}
	/**
	 * 
	 * <p>
	 * 更新客户货量潜力<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014年3月26日
	 * @param rv
	 * @param vpList
	 * void
	 */
	private void updateMemberExtend(ReturnVisit rv,List<ReturnVisitVolumePotential> vpList){
		//客户扩展属性
		if(null!=vpList && vpList.size()>0){
			MemberExtend orignalEx = memberManager.searchMemberExtendByID(rv.getMemberId());
			if(orignalEx ==null){
				orignalEx = new MemberExtend();
			}
			String orignalCoopIntention  = orignalEx.getCoopIntention();
			String orignalAccord = 	orignalEx.getIsAccord();
			//1.客户Id
			if(vpList.size() < 2){
				String volumePotential = vpList.get(0).getVolumePotential();
				String recentCoop = vpList.get(0).getCompanyId();
				String isAccord = vpList.get(0).getAccord();
				String newCooperate = vpList.get(0).getCooperate();
				//设置货量潜力，每次回访录入都可以覆盖
				if(StringUtils.isNotEmpty(volumePotential)){
					orignalEx.setVolumePotential(volumePotential);
				}
				//设置合作物流公司，每次回访录入都可以覆盖
				if(StringUtils.isNotEmpty(recentCoop)){
					String[] coops = StringUtils.isNotEmpty(orignalEx.getRecentCoop()) ? orignalEx.getRecentCoop().split(",") : new String[]{};
					List<String> coopList = new ArrayList(Arrays.asList(coops));
					coopList.add(recentCoop);
					orignalEx.setRecentCoop(getCoopString(coopList));
				}
				//设置合作意向，若原来为高，则不修改
				if(StringUtils.isNotEmpty(newCooperate) 
						&& !MarketingConstance.COOPINTENTION_HIGH.equals(orignalCoopIntention)){
					orignalEx.setCoopIntention(vpList.get(0).getCooperate());
				}
				//设置适合我司承运,若客户业务信息适合我司承运字段已经是是，则不覆盖
				if(StringUtils.isNotEmpty(isAccord) && !"1".equals(orignalAccord)){
					orignalEx.setIsAccord(isAccord);
				}
			}else{
				List<String> tempVpList = new ArrayList<String>();
				List<String> tempCoopList = new ArrayList<String>();
				List<String> tempCoopIntention = new ArrayList<String>();
				List<String> tempAccord = new ArrayList<String>();
				//将多条货量潜力提取到 临时list中
				for(ReturnVisitVolumePotential vp:vpList){
					tempVpList.add(vp.getVolumePotential());
					tempCoopList.add(vp.getCompanyId());
					tempCoopIntention.add(vp.getCooperate());
					tempAccord.add(vp.getAccord());
				}
				//2.货量潜力：每次回访录入都可以覆盖之前的货量潜力字段，如果一次回访录入多条回访信息，
				//则取多条回访信息中，货量潜力最高值覆盖原有字段；
				//从大到小进行匹配，设置最大的货量潜力
				orignalEx.setVolumePotential(getMaxVolumePotential(tempVpList));
				//3.合作物流公司：每次回访录入都可以覆盖，若一次回访录入多条回访信息，并且回访信息中合作物流公司字段不一致，
				//则将出现的物流公司都覆盖到客户业务信息中，客户业务信息中的合作物流公司字段为多选
				String[] coops =orignalEx.getRecentCoop() == null ? new String[]{""} :orignalEx.getRecentCoop().split(",");
				List<String> coopList = new ArrayList(Arrays.asList(coops));
				coopList.addAll(tempCoopList);
				orignalEx.setRecentCoop(getCoopString(coopList));
				//设置合作意向
				//4.合作意向：如客户业务信息中合作意向为高，则合作意向字段锁定，不做覆盖；若客户业务信息中合作意向不为高，
				//则将回访信息中的合作意向覆盖至业务信息中；若有多条回访信息，合作意向优先级为：高>中>低，
				//即，多条回访记录中，只要有高，则将业务信息覆盖为高；若没有高，只要有中，则将业务信息覆盖为中；
				if(!MarketingConstance.COOPINTENTION_HIGH.equals(orignalCoopIntention)){
					orignalEx.setCoopIntention(getHigherCoopIntention(rv, tempCoopIntention));
				}
				//5.适合我司承运：若客户业务信息适合我司承运字段已经是是，则不覆盖；若不是，
				//则将回访信息中的适合我司承运字段覆盖至客户业务信息中，若出现多条回访信息，
				//只要其中有一个选择是，则将客户业务信息中适合我司承运字段覆盖为是，并锁定该字段，不可修改
				if(!"1".equals(orignalAccord)){
					orignalEx.setIsAccord(getAccord(rv,tempAccord));
				}
			}
			//6.继续营销：每次回访都覆盖原有的信息，继续营销默认为是
			orignalEx.setIsContinueMark(rv.getContinueMarket());
			if(StringUtils.isEmpty(orignalEx.getCustId())){
				orignalEx.setCustId(rv.getMemberId());
				//memberManager.createMemberExtendInfo(orignalEx);
				memberManager.updateExtendAndLog(null,orignalEx,Constant.OPERATOR_TYPE_INSERT);	
			}else{
				memberManager.updateExtendAndLog(null,orignalEx,Constant.OPERATOR_TYPE_UPDATE);				
			}
		}
	}
	
	/**
	 * 
	 * <p>
	 * 从多条适合我司承运中查找是否选择了是<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014年3月28日
	 * @param rv
	 * @param tempAccord
	 * @return
	 * boolean
	 */
	private String getAccord(ReturnVisit rv,List<String> tempAccord){
		if(tempAccord.contains("1")){
			return "1";
		}
		return "0";
	}
	
	/**
	 * 
	 * <p>
	 * 多条合作意向中区最大的<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014年3月27日
	 * @param rv
	 * @param coopIntentionList
	 * @return
	 * String
	 */
	private String getHigherCoopIntention(ReturnVisit rv,List<String> coopIntentionList){
		if(coopIntentionList!=null && coopIntentionList.size() == 1){
			return coopIntentionList.get(0);
		}else{
			if(coopIntentionList.contains(MarketingConstance.COOPINTENTION_HIGH)){
				return MarketingConstance.COOPINTENTION_HIGH;
			}else if(coopIntentionList.contains(MarketingConstance.COOPINTENTION_MIDDLE)){
				return MarketingConstance.COOPINTENTION_MIDDLE;
			}else{
				return MarketingConstance.COOPINTENTION_LOW;
			}
		}
	}
	/**
	 * 
	 * <p>
	 * 多记录中查找最大的货量潜力<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014年3月27日
	 * @param tempVpList
	 * @return
	 * String
	 */
	private String getMaxVolumePotential(List<String> tempVpList){
		//从大到小进行匹配
		if(tempVpList.contains(">20000")){
			return ">20000";
		}else if(tempVpList.contains("15000-20000")){
			return "15000-20000";
		}else if(tempVpList.contains("10000-15000")){
			return "10000-15000";
		}else if(tempVpList.contains("5000-10000")){
			return "5000-10000";
		}else if(tempVpList.contains("3000-5000")){
			return "3000-5000";
		}else if(tempVpList.contains("600-3000")){
			return "600-3000";
		}else{
			return "<600";
		}
	}
	/**
	 * 
	 * <p>
	 * Description:去重<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014年3月27日
	 * @param tempCoopList
	 * @return
	 * List<String>
	 */
	public String getCoopString(List<String> tempCoopList){
		StringBuffer sb = new StringBuffer();
		if(!tempCoopList.isEmpty()){
			HashSet<String> hashSet = new HashSet<String>(tempCoopList);
			tempCoopList.clear();
			tempCoopList.addAll(hashSet);
			for(int i = 0; i < tempCoopList.size(); i++){
				sb.append(tempCoopList.get(i));
				if(i == tempCoopList.size() -1){
					sb.append("");
				}else{
					sb.append(",");
				}
			}
		}
		return sb.toString();
	}
	/**
	 * 
	 * <p>
	 * 根据ReturnVisit确定查询条件<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014年3月26日
	 * @param rv
	 * @return
	 * ReturnVisitQueryCondition
	 */
	private ReturnVisitQueryCondition fetchReturnVisitQueryCondition(ReturnVisit rv){
		// 根据日程ID和客户ID信息查询回访记录
		ReturnVisitQueryCondition condition = new ReturnVisitQueryCondition();
		//设置联系人ID
		condition.setLinkManId(rv.getLinkManId());
		//设置客户ID
		condition.setMemberId(rv.getMemberId());
		//日程列表
		List<String> ids = new ArrayList<String>();
		//添加到日程列表
		ids.add(rv.getScheduleId());
		//设置到查询条件
		condition.setScheduleIds(ids);
		//设置日程类型
		condition.setScheType(rv.getScheType());
		return condition;
	}
	/**
	 * <p>
	 * Description: 创建跟踪日程<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-4-11
	 * @param rv
	 * @return
	 * Schedule
	 */
	private Schedule createScheduleObj(ReturnVisit rv){	
		//将回访信息装换为日程信息
		Schedule newSchedule = new Schedule();
		//设置客户ID
		newSchedule.setCustId(rv.getMemberId());
		//日程类型为开发
		if (MarketingConstance.DEVELOP_TYPE.equals(rv.getScheType())){
			//设置日程类型
			newSchedule.setType(MarketingConstance.DEVELOP_TYPE);
			//设置客户ID
		}else{
			//否则设置为维护类型
			newSchedule.setType(MarketingConstance.MAINTAIN_TYPE);
		}
		//设置联系人ID
		newSchedule.setContactId(rv.getLinkManId());
		//设置计划ID
		newSchedule.setPlanId(null);
		//设置日程时间
		newSchedule.setTime(rv.getSchedule());
		//设置执行人
		newSchedule.setExeManId(rv.getExecutorId());
		//设置执行人
		newSchedule.setExeDeptId(rv.getExecuteDeptId());
		//日程状态 已制定
		newSchedule.setStatus(MarketingConstance.SCHEDULE_FORMULATE);
		//返回构造对象
		return newSchedule;
	}

	/**
	 * <p>
	 * Description: 录入页面初始化<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-4-12
	 * @param ReturnVisit rv
	 * @return ReturnVisit
	 * @see com.deppon.crm.module.marketing.server.manager.IReturnVisitManager#initCreateReturnvisitPage(com.deppon.crm.module.marketing.shared.domain.ReturnVisit)
	 */
	@SuppressWarnings("serial")
	@Override
	public ReturnVisit initCreateReturnvisitPage(ReturnVisit rv, User user) {
		try {
			//回访列表
			List<ReturnVisit> list = null;
			//日程
			Schedule sche = null;
			//回访有日程ID
			if (StringUtils.isNotEmpty(rv.getScheduleId())){
				// 根据日程ID和客户ID信息查询回访记录
				ReturnVisitQueryCondition condition = new ReturnVisitQueryCondition();
				//设置联系人ID
				condition.setLinkManId(rv.getLinkManId());
				//设置客户ID
				condition.setMemberId(rv.getMemberId());
				//日程ID集合
				List<String> ids = new ArrayList<String>();
				//添加日程ID
				ids.add(rv.getScheduleId());
				//设置日程Id
				condition.setScheduleIds(ids);
				//设置日程类型
				condition.setScheType(rv.getScheType());
				//分页查询营销信息
				list = rvService.getReturnVisitList(condition, 0, 10);
				//查询日程
				sche = scheduleManager.getScheduleById(rv.getScheduleId());
			}
			// 未设置日程-回访数据异常，无法正常执行。
			if (sche == null){
				throw new ReturnVisitException(
						ReturnVisitExceptionType.noPermissions);
			}
			ReturnVisit rs = null;
			// 验证该日程是否可回访
			ReturnVisitValidateUtils.canReturnVisit(sche, user);	
			//页面初始化数据校验
			if (ReturnVisitValidateUtils.canInitCreatePageData(rv, list, user)){
				// 获得回访客户信息
				rs =  rvService.initCreateReturnvisitPage(rv.getScheduleId(), rv.getScheType());
			}
			//返回执行结果
			return rs;
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,e.getErrorArguments()) {
			};
		}
	}

	@SuppressWarnings("serial")
	@Override
	public ReturnVisit initCreateReturnvisitPageByCust(ReturnVisit rv, User user){
		try {
			// 根据客户进行回访
			ReturnVisit rs = null;
//			初始化数据
			rs =  rvService.initCreateReturnvisitPageByCust(rv.getLinkManId(), rv.getScheType());				
			boolean isExist = !businessOpportunityManager.queryBusinessOpportunityActiveByCustId(rv.getMemberId()).isEmpty();
			String deptName = repeatedCustManager.searchMainCustDeptName(rs.getCustNumber());
			if(StringUtils.isNotEmpty(deptName) || isExist){
				ReturnVisitValidateUtils.validateRepeatAndBo(isExist,rs,deptName);				
			}
			//设置执行部门ID
			rs.setExecuteDeptId(user.getEmpCode().getDeptId().getId());
			//设置部门名称
			rs.setDepartName(user.getEmpCode().getDeptId().getDeptName());
			//设置执行人ID
			rs.setExecutorId(user.getEmpCode().getId());
			//设置用户名 
			rs.setUserName(user.getEmpCode().getEmpName());
			//返回结果
			return rs;
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,e.getErrorArguments()) {
			};
		}
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IReturnVisitManager#getReturnVisitLog(com.deppon.crm.module.marketing.shared.domain.ReturnVisit)
	 */
	@Override
	public List<ReturnVisit> getReturnVisitLog(ReturnVisitQueryCondition condition) {
		//查询营销日志
		return rvService.getReturnVisitLog(condition);
	}

	/**
	 * <p>
	 * 根据客户Id查询该客户下所有联系人的回访ID集合<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-11-9
	 * @param custId
	 * @return
	 * List<String>
	 * @see com.deppon.crm.module.marketing.server.manager.IReturnVisitManager#queryContactIdListByCustId(java.lang.String)
	 */
	@Override
	public List<String> queryRvListByCustId(Map map) {
		//通过客户ID查询回访ID
		return rvService.queryRvListByCustId(map);
	}

	/**
	 * <p>
	 * 分页查询客户意见<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-11-9
	 * @param marketingIds
	 * @param start
	 * @param limit
	 * @see com.deppon.crm.module.marketing.server.manager.IReturnVisitManager#queryRvOptionByMarketingIds(java.util.List, int, int)
	 */
	@Override
	public List<ReturnVisitOpinionVo> queryRvOptionByMarketingIds(
			List<String> marketingIds, int start, int limit) {
		return rvService.queryRvOptionByMarketingIds(marketingIds,start,limit);
	}

	/**
	 * 
	 * <p>
	 * 分页查询货量潜力<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-11-9
	 * @param marketingIds
	 * @param start
	 * @param limit
	 * @return
	 * List<ReturnVisitVolumePotential>
	 * @see com.deppon.crm.module.marketing.server.manager.IReturnVisitManager#queryRvPotentialByMarketingIds(java.util.List, int, int)
	 */
	@Override
	public List<ReturnVisitVolumePotentialVo> queryRvPotentialByMarketingIds(
			List<String> marketingIds, int start, int limit) {
		return rvService.queryRvPotentialByMarketingIds(marketingIds,start,limit);
	}

	/* 统计回访意见总记录数
	 * @see com.deppon.crm.module.marketing.server.manager.IReturnVisitManager#countRvOptionByMarketingIds(java.util.List)
	 */
	@Override
	public int countRvOptionByMarketingIds(List<String> marketingIds) {
		return rvService.countRvOptionByMarketingIds(marketingIds);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IReturnVisitManager#countRvPotentialByMarketingIds(java.util.List)
	 */
	@Override
	public int countRvPotentialByMarketingIds(List<String> marketingIds) {
		return rvService.countRvPotentialByMarketingIds(marketingIds);
	}
	/**
	 * 
	 * <p>
	 * 固定计划回访同步,固定计划只针对 维护计划<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-03-26
	 * @param limit
	 * @return
	 */
	public boolean synFixPlanReturnVisit(ReturnVisit rv,List<ReturnVisitOpinion> rvoList,
			List<ReturnVisitVolumePotential> rvvpList){
		if(null == rv){
			return false;
		}
		if(StringUtils.isEmpty(rv.getMemberId())&&StringUtils.isEmpty(rv.getLinkManId())&&
				StringUtils.isEmpty(rv.getExecuteDeptId())){
			return false;
		}
		Schedule sche = scheduleManager.getScheduleById(rv.getScheduleId());
		if(sche == null || StringUtils.isEmpty(sche.getType())){
			return false;
		}
		if(MarketingConstance.MAINTAIN_TYPE.equals(rv.getScheType())){
			// 计划id
			String planId = rv.getScheduleId();
			// 日程id
			String scheId = rv.getScheduleId();
			// 计划类型
			String planType = sche.getType();
			// 如果此记录的计划id不为空，且是固定计划的话
			if(StringUtils.isNotEmpty(planId)&&StringUtils.isNotEmpty(scheId)&&
					MarketingConstance.FIXEDPLAN_MONTHLY_TYPE.equals(planType)){
				// 在部门寻找  是否有相同会员的固定计划
				List<Schedule> listSche = rvService.synFixPlanReturnVisit(rv);
				if(null != listSche && 0 != listSche.size()){
					for(Schedule sc : listSche){
						// 更新（未完成，未过期）的日程
						sc.setStatus(MarketingConstance.SCHEDULE_FINISH);
						sc.setTime(sche.getTime());
						sc.setExeManId(sche.getExeManId());
						scheduleManager.updateScheduleStatus(sc);
					}
				}
			}
			
		}
		return true;
	}
}
