/**   
 * Description:回访action
 * @title ReturnVisitAction.java
 * @package com.deppon.crm.module.marketing.server.action 
 * @author 毛建强
 * @version 0.1 2012-4-9
 */
package com.deppon.crm.module.marketing.server.action;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.manager.IReturnVisitManager;
import com.deppon.crm.module.marketing.server.manager.IWarnLostCustManager;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinion;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotential;
import com.deppon.crm.module.marketing.shared.domain.WarnStatusUpdateInfo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.AnswerMainInfo;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**   
 * Description:这里写描述<br />
 * @title ReturnVisitAction.java
 * @package com.deppon.crm.module.marketing.server.action 
 * @author 毛建强
 * @version 0.1 2012-4-9
 */

public class ReturnVisitAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	// 监控计划Manager
	private IReturnVisitManager returnVisitManager;
	//流失预警manager
	private IWarnLostCustManager warnLostCustManager;
	// 起始页
	private int start;
	// 每页显示条数
	private int limit;
	//分页总条数
	private Long totalCount;
	//回访实体
	private ReturnVisit returnVisit;
	//回访查询条件
	private  ReturnVisitQueryCondition returnVisitQueryCondition;
	//回访查询列表
	private List<ReturnVisit> returnVisitList;	
	//客户意见列表
	private List<ReturnVisitOpinion> returnVisitOpinionList;
	//发货潜力列表
	private List<ReturnVisitVolumePotential> returnVisitVolumePotentialList;
	//客户已经列表
	private List<ReturnVisitOpinion> opinionList=null;
	//客户id
	private int custId;
	//客户流失预警信息
	private WarnStatusUpdateInfo lostWarnInfo;
	//货量潜力
	private List<ReturnVisitVolumePotential> potentialList=null;
	//问卷回访客户答案信息实体列表
	private List<AnswerMainInfo> answerMainInfoList;
	/**
	 * @param lostWarnInfo the lostWarnInfo to get
	 */
	public WarnStatusUpdateInfo getLostWarnInfo() {
		return lostWarnInfo;
	}

	/**
	 * @param lostWarnInfo the lostWarnInfo to set
	 */
	public void setLostWarnInfo(WarnStatusUpdateInfo lostWarnInfo) {
		this.lostWarnInfo = lostWarnInfo;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setCustId(int custId) {
		this.custId = custId;
	}

	//回访列表
	public List<ReturnVisitOpinion> getOpinionList() {
		return opinionList;
	}
	
	/**
	 * @param warnLostCustManager the warnLostCustManager to set
	 */
	public void setWarnLostCustManager(IWarnLostCustManager warnLostCustManager) {
		this.warnLostCustManager = warnLostCustManager;
	}

	/**
	 * @param opinionList ： set the property opinionList
	 */
	public void setOpinionList(List<ReturnVisitOpinion> opinionList) {
		this.opinionList = opinionList;
	}
	/**
	 * @return potentialList ： get the property potentialList
	 */
	public List<ReturnVisitVolumePotential> getPotentialList() {
		return potentialList;
	}
	/**
	 * @param potentialList ： set the property potentialList
	 */
	public void setPotentialList(List<ReturnVisitVolumePotential> potentialList) {
		this.potentialList = potentialList;
	}
	
	/**
	 * @param answerMainInfoList the answerMainInfoList to get
	 */
	public List<AnswerMainInfo> getAnswerMainInfoList() {
		return answerMainInfoList;
	}

	/**
	 * @param answerMainInfoList the answerMainInfoList to set
	 */
	public void setAnswerMainInfoList(List<AnswerMainInfo> answerMainInfoList) {
		this.answerMainInfoList = answerMainInfoList;
	}


	
	/**
	 * Description:保存操作
	 * @author 毛建强
	 * @version 0.1 2012-4-13
	 * @param 
	 * @return
	 */
	@JSON
	public String saveReturnVisit(){
		//获取客户意见列表
//		List<ReturnVisitOpinion> OpinionList = JsonUtil.getList4Json(returnVisitOpinion,ReturnVisitOpinion.class);
		//获取发货潜力列表
//		List<ReturnVisitVolumePotential> potentialList = JsonUtil.getList4Json(returnVisitVolumePotential,ReturnVisitVolumePotential.class);
		returnVisitManager.createReturnvisit(returnVisit, opinionList, potentialList,answerMainInfoList,lostWarnInfo,this.getCurrentUser());
		return SUCCESS;
	}
	
	/**
	 * Description:查询回访记录列表（回访查询）
	 * @author 毛建强
	 * @version 0.1 2012-4-13
	 * @param 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String searchReturnVisitList(){
		//调用manager
		User user=(User)UserContext.getCurrentUser();
		//查询结果
		Map<String, Object> map = returnVisitManager.getReturnVisitList(returnVisitQueryCondition, start, limit, user);
		//获取记录列表
		returnVisitList = (List<ReturnVisit>)map.get("data");
		//获取分页总条数
		totalCount = Long.valueOf( map.get("count").toString());
		return SUCCESS;
	}

	/**
	 * Description:双击查询回访明细<br />
	 * @author 毛建强
	 * @version 0.1 2012-4-13
	 * @param 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String searchReturnVisit(){
		//获取回访id
		int id = 0;
		if(returnVisit!=null && returnVisit.getId()!=null){
			id =Integer.parseInt(returnVisit.getId());
		}
		//调用manager，获取回访明细
		Map<String, Object> result = returnVisitManager.getReturnVisit(id);
		//查询结果
		returnVisit = (ReturnVisit) result.get("returnVisit");
		//客户意见列表
		returnVisitOpinionList = (List<ReturnVisitOpinion>) result.get("returnVisitOpinion");
		//走货潜力列表
		returnVisitVolumePotentialList = (List<ReturnVisitVolumePotential>) result.get("returnVisitVolumePotential");
		return SUCCESS;
	}

	/**
	 * Description:初始化回访录入界面
	 * @author 毛建强
	 * @version 0.1 2012-4-12
	 * @param 
	 * @return
	 */
	@JSON
	public String initCreateReturnvisitPage(){
		User user=(User)UserContext.getCurrentUser();
		//调用manager层，获取初始化数据
		this.returnVisit = this.returnVisitManager.initCreateReturnvisitPage(returnVisit,user);
		return SUCCESS;
	}
	
	/**
	 * Description:初始化回访录入界面
	 * @author 毛建强
	 * @version 0.1 2012-4-12
	 * @param 
	 * @return
	 */
	@JSON
	public String initCreateReturnvisitPageByCust(){
		User user=(User)UserContext.getCurrentUser();
		//调用manager层，获取初始化数据
		this.returnVisit = this.returnVisitManager.initCreateReturnvisitPageByCust(returnVisit,user);
		return SUCCESS;
	}
	
	/**
	 * Description:查看对应客户的回访记录
	 * @author 毛建强
	 * @version 0.1 2012-4-18
	 * @param 
	 * @return
	 */
	@JSON
	public String searchReturnVisitLog(){
		//条件查询
		returnVisitList = returnVisitManager.getReturnVisitLog(returnVisitQueryCondition);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:根据客户id查询流失预警客户信息<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-25
	 */
	@JSON
	public String searchWarnLostCustInfo(){
		lostWarnInfo = warnLostCustManager.searchWarnLostCustInfo(custId,null);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:用户在回访页面手动更新流失预警客户信息<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2014-3-25
	 */
	@JSON
	public String updateLostWarnCustInfo(){
		warnLostCustManager.updateWarnInfo(lostWarnInfo);
		return SUCCESS;
	}
	/**
	 * @return returnVisit : get the property returnVisit
	 */
	public ReturnVisit getReturnVisit() {
		return returnVisit;
	}
	/**
	 * @param returnVisit : set the property returnVisit
	 */
	public void setReturnVisit(ReturnVisit returnVisit) {
		this.returnVisit = returnVisit;
	}
	/**
	 * @return totalCount : get the property totalCount
	 */
	public Long getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount : get the property totalCount
	 */
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return returnVisitQueryCondition : get the property returnVisitQueryCondition
	 */
	public ReturnVisitQueryCondition getReturnVisitQueryCondition() {
		return returnVisitQueryCondition;
	}
	/**
	 * @param returnVisitQueryCondition : get the property returnVisitQueryCondition
	 */
	public void setReturnVisitQueryCondition(
			ReturnVisitQueryCondition returnVisitQueryCondition) {
		this.returnVisitQueryCondition = returnVisitQueryCondition;
	}
	/**
	 * @return returnVisitList : get the property returnVisitList
	 */
	public List<ReturnVisit> getReturnVisitList() {
		return returnVisitList;
	}
	/**
	 * @param returnVisitManager : set the property returnVisitManager.
	 */
	public void setReturnVisitManager(IReturnVisitManager returnVisitManager) {
		this.returnVisitManager = returnVisitManager;
	}
	/**
	 * @param start : set the property start.
	 */
	public void setStart(int start) {
		this.start = start;
	}
	/**
	 * @param limit : set the property limit.
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	/**
	 * @return returnVisitOpinionList : get the property returnVisitOpinionList
	 */
	public List<ReturnVisitOpinion> getReturnVisitOpinionList() {
		return returnVisitOpinionList;
	}
	/**
	 * @return returnVisitVolumePotentialList : get the property returnVisitVolumePotentialList
	 */
	public List<ReturnVisitVolumePotential> getReturnVisitVolumePotentialList() {
		return returnVisitVolumePotentialList;
	}
	/**
	 * @return User
	 */
	private User getCurrentUser(){
		return (User) UserContext.getCurrentUser();
	}
}
