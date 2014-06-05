/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PlanVO.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-3-30
 */
package com.deppon.crm.module.marketing.shared.domain;

/**   
 * <p>
 * Description: 计划VO<br />
 * </p>
 * @title PlanList.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-3-30
 */

public class PlanList extends Plan{
	// 执行部门名称
	private String execdeptName;
	
	// 执行人员名
	private String execdeptUserName;
	
	// 创建人名
	private String createUserName;
	
	// 最后修改人名
	private String modifyUserName;
	
	// 计划是否可更改标志
	private int running;
	
	// 计划时限
	private String timelimit;
	//问卷名称
	private String surveyName;
	/**
	 * @return timelimit : return the property timelimit.
	 */
	public String getTimelimit() {
		return timelimit;
	}

	/**
	 * @param timelimit : set the property timelimit.
	 */
	public void setTimelimit(String timelimit) {
		this.timelimit = timelimit;
	}

	/**
	 * @return running : return the property running.
	 */
	public int getRunning() {
		return running;
	}

	/**
	 * @param running : set the property running.
	 */
	public void setRunning(int running) {
		this.running = running;
	}

	/**
	 * @return execdeptName : return the property execdeptName.
	 */
	public String getExecdeptName() {
		return execdeptName;
	}

	/**
	 * @param execdeptName : set the property execdeptName.
	 */
	public void setExecdeptName(String execdeptName) {
		this.execdeptName = execdeptName;
	}

	/**
	 * @return execdeptUserName : return the property execdeptUserName.
	 */
	public String getExecdeptUserName() {
		return execdeptUserName;
	}

	/**
	 * @param execdeptUserName : set the property execdeptUserName.
	 */
	public void setExecdeptUserName(String execdeptUserName) {
		this.execdeptUserName = execdeptUserName;
	}

	/**
	 * @return createUserName : return the property createUserName.
	 */
	public String getCreateUserName() {
		return createUserName;
	}

	/**
	 * @param createUserName : set the property createUserName.
	 */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	/**
	 * @return modifyUserName : return the property modifyUserName.
	 */
	public String getModifyUserName() {
		return modifyUserName;
	}

	/**
	 * @param modifyUserName : set the property modifyUserName.
	 */
	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	public String getSurveyName() {
		return surveyName;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}

}
