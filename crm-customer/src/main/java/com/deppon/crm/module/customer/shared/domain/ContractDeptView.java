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
public class ContractDeptView extends BaseEntity{
	
	private static final long serialVersionUID = 1237800844187951037L;
	//部门名称
	private String deptName;
	//绑定时间
	private Date boundTime;
	// 合同解绑时间
	private Date removeTime;
	//合同部门ID
	private String contractDeptId;
	//是否归属部门
	private boolean isDept;
	// 合同状态 1正常，0已作废
	private boolean state;
	// 版本号
	private Integer versionNumber;
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
	 * Description:removeTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getRemoveTime() {
		return removeTime;
	}
	/**
	 * <p>
	 * Description:removeTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setRemoveTime(Date removeTime) {
		this.removeTime = removeTime;
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
	 * Description:versionNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Integer getVersionNumber() {
		return versionNumber;
	}
	/**
	 * <p>
	 * Description:versionNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setVersionNumber(Integer versionNumber) {
		this.versionNumber = versionNumber;
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
