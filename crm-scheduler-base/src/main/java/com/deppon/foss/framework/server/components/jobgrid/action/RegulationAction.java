package com.deppon.foss.framework.server.components.jobgrid.action;

import java.util.Date;
import java.util.List;



import com.deppon.foss.framework.server.components.jobgrid.JobGridManager;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobPlanning;
import com.deppon.foss.framework.server.components.jobgrid.domain.PlanningCondition;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.message.IMessageBundle;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

public class RegulationAction extends AbstractAction {
	
	

	// -----------------只需要get的数据---------------------------------------------

	// 提示信息
	private String message;

	public String getMessage() {
		return message;
	}

	// 总共有多少条数据
//	private int totalCount;
//
//	public int getTotalCount() {
//		return totalCount;
//	}
	
	//规则集合
	
	private List<JobPlanning> pnList;
	public List<JobPlanning> getPnList() {
		return pnList;
	}
	
	// -----------------只需要set的数据---------------------------------------------



	

	// BEAN获得国际化对象
	private IMessageBundle messageBundle;

	public void setMessageBundle(IMessageBundle messageBundle) {
		this.messageBundle = messageBundle;
	}
	
	//任务调度
	private JobGridManager job ;
	public void setJob(JobGridManager job) {
		this.job = job;
	}

	// 分页查询，每页个数限制
	private int limit;

	public void setLimit(int limit) {
		this.limit = limit;
	}

	// 分页查询的起始页
	private int start = 1;

	public void setStart(int start) {
		this.start = start;
	}
	
	
	
	
	// 业务结束时间
	private Date buzEndDate;

	public void setBuzEndDate(Date buzEndDate) {
		this.buzEndDate = buzEndDate;
	}

	

	// -----------------需要get和set的数据---------------------------------------------
	// 查询参数类
	private PlanningCondition planningCondition;
	
	
	public PlanningCondition getPlanningCondition() {
		return planningCondition;
	}
	
	public void setPlanningCondition(PlanningCondition planningCondition) {
		this.planningCondition = planningCondition;
	}
	
	//规则实体
	private JobPlanning planning;
	public JobPlanning getPlanning() {
		return planning;
	}
	public void setPlanning(JobPlanning planning) {
		this.planning = planning;
	}

	// -----------------------------ACTION方法-------------------------

	
	/**
	 * 
	 * <p>
	 * Description:分页查询 规则集合<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-28
	 * @return
	 * String
	 */
	@JSON
	public String ruleSearchListPager() {
		pnList = job.queryJobPlanning(planningCondition.getInstanceId(),start,limit);
		
		totalCount = Long.valueOf(job.queryJobPlanningCount(planningCondition.getInstanceId()));
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:规则添加<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-28
	 * @return
	 * String
	 */
	@JSON
	public String ruleAdd() {
		job.saveJobPlanning(planning);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:规则 修改<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-28
	 * @return
	 * String
	 */
	@JSON
	public String ruleUpdate() {
		job.saveJobPlanning(planning);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:规则 删除<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2012-12-28
	 * @return
	 * String
	 */
	@JSON
	public String ruleDelete() {
		job.deleteJobPlanningById(planningCondition.getId());
		return SUCCESS;
	}
	
}
