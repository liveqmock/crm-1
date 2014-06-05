package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;

/**
 * <p>
 * Description: 计划查询条件<br />
 * </p>
 * @title PlanDevelopCondition.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-3-30
 */
public class PlanDevelopCondition {
	// 计划类型
	private String type;

	//计划名称
	private String planName;

	//计划制定者
	private String planMaker;

	//执行人员
	private String executor;

	//执行部门
	private String executeDept;

	//开发方式
	private String developMode;

	//计划开始时间
	private Date planStartDate;

	//计划结束时间
	private Date planOverDate;
	
	// 执行部门IDS
	private String[] executeDepts;
	
	//计划类别
	private String projectType;
	//问卷ID
	private String surveyId;

	/**
	 * @return type : return the property type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type : set the property type.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return planName : return the property planName.
	 */
	public String getPlanName() {
		return planName;
	}

	/**
	 * @param planName : set the property planName.
	 */
	public void setPlanName(String planName) {
		this.planName = planName;
	}

	/**
	 * @return planMaker : return the property planMaker.
	 */
	public String getPlanMaker() {
		return planMaker;
	}

	/**
	 * @param planMaker : set the property planMaker.
	 */
	public void setPlanMaker(String planMaker) {
		this.planMaker = planMaker;
	}

	/**
	 * @return executor : return the property executor.
	 */
	public String getExecutor() {
		return executor;
	}

	/**
	 * @param executor : set the property executor.
	 */
	public void setExecutor(String executor) {
		this.executor = executor;
	}

	/**
	 * @return executeDept : return the property executeDept.
	 */
	public String getExecuteDept() {
		return executeDept;
	}

	/**
	 * @param executeDept : set the property executeDept.
	 */
	public void setExecuteDept(String executeDept) {
		this.executeDept = executeDept;
	}

	/**
	 * @return developMode : return the property developMode.
	 */
	public String getDevelopMode() {
		return developMode;
	}

	/**
	 * @param developMode : set the property developMode.
	 */
	public void setDevelopMode(String developMode) {
		this.developMode = developMode;
	}

	/**
	 * @return planStartDate : return the property planStartDate.
	 */
	public Date getPlanStartDate() {
		return planStartDate;
	}

	/**
	 * @param planStartDate : set the property planStartDate.
	 */
	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}

	/**
	 * @return planOverDate : return the property planOverDate.
	 */
	public Date getPlanOverDate() {
		return planOverDate;
	}

	/**
	 * @param planOverDate : set the property planOverDate.
	 */
	public void setPlanOverDate(Date planOverDate) {
		this.planOverDate = planOverDate;
	}

	/**
	 * @return executeDepts : return the property executeDepts.
	 */
	public String[] getExecuteDepts() {
		return executeDepts;
	}

	/**
	 * @param executeDepts : set the property executeDepts.
	 */
	public void setExecuteDepts(String[] executeDepts) {
		this.executeDepts = executeDepts;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-1-16
	 */
	public String getProjectType() {
		return projectType;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-1-16
	 * @param projectType the projectType to set
	 */
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}

}
