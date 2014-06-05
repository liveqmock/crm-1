/**
 * @description
 * @author 赵斌
 * @2012-4-14
 * @return
 */
package com.deppon.crm.module.customer.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @author 赵斌
 *
 */
public class ContractDepart extends BaseEntity{
	
	private static final long serialVersionUID = 1237800844187951037L;
	//部门名称
	private String deptName;
	//绑定时间
	private Date boundTime;
	//合同部门ID
	private String contractDeptId;
	//是否归属部门
	private boolean isDept;
	//是否归属部门
	private boolean state;
	/**
	 * <p>
	 * Description:deptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * <p>
	 * Description:deptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * <p>
	 * Description:boundTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getBoundTime() {
		return boundTime;
	}
	/**
	 * <p>
	 * Description:boundTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBoundTime(Date boundTime) {
		this.boundTime = boundTime;
	}
	/**
	 * <p>
	 * Description:contractDeptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContractDeptId() {
		return contractDeptId;
	}
	/**
	 * <p>
	 * Description:contractDeptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContractDeptId(String contractDeptId) {
		this.contractDeptId = contractDeptId;
	}
	/**
	 * <p>
	 * Description:isDept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public boolean isDept() {
		return isDept;
	}
	/**
	 * <p>
	 * Description:isDept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDept(boolean isDept) {
		this.isDept = isDept;
	}
	/**
	 * <p>
	 * Description:state<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public boolean isState() {
		return state;
	}
	/**
	 * <p>
	 * Description:state<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setState(boolean state) {
		this.state = state;
	}
	/**
	 * <p>
	 * Description:serialversionuid<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
