package com.deppon.crm.module.marketing.server.action;

import java.util.HashMap;
import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.manager.impl.MonitorPlanManager;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlan;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlanDetail;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

public class MonitorPlanAction extends AbstractAction {
	// 监控开发Manage
	private MonitorPlanManager monitorPlanManager;
	/**
	 * @param monitorPlanManager : set the property monitorPlanManager
	 */
	public void setMonitorPlanManager(MonitorPlanManager monitorPlanManager) {
		this.monitorPlanManager = monitorPlanManager;
	}

	// 监控开发查询条件
	private MonitorPlanQueryCondition monitorPlanQueryCondition;
	/**
	 * @return monitorPlanManager : get the property monitorPlanManager
	 */
	public MonitorPlanQueryCondition getMonitorPlanQueryCondition() {
		return monitorPlanQueryCondition;
	}
	/**
	 * @param monitorPlanQueryCondition : set the property monitorPlanQueryCondition
	 */
	public void setMonitorPlanQueryCondition(
			MonitorPlanQueryCondition monitorPlanQueryCondition) {
		this.monitorPlanQueryCondition = monitorPlanQueryCondition;
	}

	// 监控计划
	private List<MonitorPlan> monitorPlanList;
	/**
	 * @return monitorPlanList : get the property monitorPlanList
	 */
	public List<MonitorPlan> getMonitorPlanList() {
		return monitorPlanList;
	}
	/**
	 * @param monitorPlanList : set the property monitorPlanList
	 */
	public void setMonitorPlanList(List<MonitorPlan> monitorPlanList) {
		this.monitorPlanList = monitorPlanList;
	}

	// 监控计划详细
	private List<MonitorPlanDetail> monitorPlanDetailList;
	/**
	 * @return monitorPlanDetailList : get the property monitorPlanDetailList
	 */
	public List<MonitorPlanDetail> getMonitorPlanDetailList() {
		return monitorPlanDetailList;
	}
	/**
	 * @param monitorPlanDetailList : set the property monitorPlanDetailList
	 */
	public void setMonitorPlanDetailList(
			List<MonitorPlanDetail> monitorPlanDetailList) {
		this.monitorPlanDetailList = monitorPlanDetailList;
	}

	// 起始页
	private int start;
	/**
	 * @param start : set the property start
	 */
	public void setStart(int start) {
		this.start = start;
	}

	// 每页显示条数
	private int limit;
	/**
	 * @param limit : set the property limit
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	//总条数
	private Long totalCount;
	/**
	 * @param totalCount : set the property totalCount
	 */
	public Long getTotalCount() {
		return totalCount;
	}
	/**
	 * @return totalCount : get the property totalCount
	 */
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	// 获取当前用户
	private User getCurrentUser(){
		return (User) UserContext.getCurrentUser();
	}
	/**
	 * Description:查询监控计划
	 * @author 张登
	 * @version 0.1 2012-4-26
	 * @param 
	 * @return
	 */
	@JSON
	public String searchMonitorList() {
		//查询监控计划列表
		monitorPlanList = monitorPlanManager
				.searchMonitorList(monitorPlanQueryCondition, this.getCurrentUser());
		return SUCCESS;
	}

	/**
	 * Description:监控计划详细
	 * @author 张登
	 * @version 0.1 2012-4-26
	 * @param 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String searchMonitorDetail() {
		//分页查询
		HashMap<String, Object> map = monitorPlanManager.getMonitorDetail(
				monitorPlanQueryCondition, start, limit, this.getCurrentUser());
		//监控计划列表
		monitorPlanDetailList = (List<MonitorPlanDetail>) map.get("mplist");
		//总数
		totalCount = Long.valueOf( map.get("mpcount").toString());
		return SUCCESS;
	}
}
