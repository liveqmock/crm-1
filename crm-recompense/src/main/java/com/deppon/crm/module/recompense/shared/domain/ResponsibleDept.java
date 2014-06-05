package com.deppon.crm.module.recompense.shared.domain;

/**
 * 
 * <p>
 * Description:责任部门<br />
 * </p>
 * 
 * @title ResponsibleDept.java
 * @package com.deppon.crm.module.recompense.shared.domain
 * @author roy
 * @version 0.1 2013-1-22
 */
public class ResponsibleDept {
	// 编号
	private String id;
	// 理赔单关联Id
	private String recompenseId;
	// 责任部门ID
	private String deptId;
	// 责任部门名称
	private String deptName;
	/**
	 * <p>
	 * Description:id<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getId() {
		return id;
	}
	/**
	 * <p>
	 * Description:id<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * <p>
	 * Description:recompenseId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getRecompenseId() {
		return recompenseId;
	}
	/**
	 * <p>
	 * Description:recompenseId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecompenseId(String recompenseId) {
		this.recompenseId = recompenseId;
	}
	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * <p>
	 * Description:deptName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * <p>
	 * Description:deptName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}




}
