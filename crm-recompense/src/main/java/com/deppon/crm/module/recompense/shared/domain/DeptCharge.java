package com.deppon.crm.module.recompense.shared.domain;

/**
 * 
 * <p>
 * Description:入部门费用<br />
 * </p>
 * @title DeptCharge.java
 * @package com.deppon.crm.module.recompense.shared.domain 
 * @author roy
 * @version 0.1 2013-1-21
 */
public class DeptCharge  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -408161502308717787L;
	//编号
	private String id;
	// 理赔单关联Id
	private String recompenseId;
	//金额
	private Double amount;
	//关联部门ID
	private String deptId;
	//关联部门名称
	private String deptName;
	//关联部门标杆编码
	private String deptCode;
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
	 * Description:amount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * <p>
	 * Description:amount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
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



	

	/**
	 * @return deptCode : return the property deptCode.
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * @param deptCode : set the property deptCode.
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
}
