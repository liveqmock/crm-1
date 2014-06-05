/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MonitorPlan.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-3-27
 */
package com.deppon.crm.module.marketing.shared.domain;

/**   
 * <p>
 * Description: 监控计划VO<br />
 * </p>
 * @title MonitorPlan.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-3-27
 */

public class MonitorPlan {

	// 部门ID
	private String departId;
	
	// 部门名称
	private String departName;
	
	// 执行人
	private String execusername;
	
	// 计划完成率
	private String completionRate;
		
	// 计划总数
	private int total;

	// 执行中计划数量
	private int execute;
	
	// 未执行计划数量
	private int noExecute;
	
	// 已完成计划数量
	private int finished;
	
	// 已过期计划数量
	private int overdue;

	/**
	 * @return departId : return the property departId.
	 */
	public String getDepartId() {
		return departId;
	}

	/**
	 * @param departId : set the property departId.
	 */
	public void setDepartId(String departId) {
		this.departId = departId;
	}

	/**
	 * @return departName : return the property departName.
	 */
	public String getDepartName() {
		return departName;
	}

	/**
	 * @param departName : set the property departName.
	 */
	public void setDepartName(String departName) {
		this.departName = departName;
	}

	/**
	 * @return execusername : return the property execusername.
	 */
	public String getExecusername() {
		return execusername;
	}

	/**
	 * @param execusername : set the property execusername.
	 */
	public void setExecusername(String execusername) {
		this.execusername = execusername;
	}

	/**
	 * @return completionRate : return the property completionRate.
	 */
	public String getCompletionRate() {
		return completionRate;
	}

	/**
	 * @param completionRate : set the property completionRate.
	 */
	public void setCompletionRate(String completionRate) {
		this.completionRate = completionRate;
	}

	/**
	 * @return execute : return the property execute.
	 */
	public int getExecute() {
		return execute;
	}

	/**
	 * @param execute : set the property execute.
	 */
	public void setExecute(int execute) {
		this.execute = execute;
	}

	/**
	 * @return noExecute : return the property noExecute.
	 */
	public int getNoExecute() {
		return noExecute;
	}

	/**
	 * @param noExecute : set the property noExecute.
	 */
	public void setNoExecute(int noExecute) {
		this.noExecute = noExecute;
	}

	/**
	 * @return finished : return the property finished.
	 */
	public int getFinished() {
		return finished;
	}

	/**
	 * @param finished : set the property finished.
	 */
	public void setFinished(int finished) {
		this.finished = finished;
	}

	/**
	 * @return overdue : return the property overdue.
	 */
	public int getOverdue() {
		return overdue;
	}

	/**
	 * @param overdue : set the property overdue.
	 */
	public void setOverdue(int overdue) {
		this.overdue = overdue;
	}

	/**
	 * @return total : return the property total.
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total : set the property total.
	 */
	public void setTotal(int total) {
		this.total = total;
	}

}
