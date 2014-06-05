/**
 * @description
 * @author 赵斌
 * @2012-3-30
 * @return
 */
package com.deppon.crm.module.customer.shared.domain;

import java.util.Date;

import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @作者：赵斌
 * @时间：2012-3-30
 * @描述：合同对应部门
 * */
public class ContractDept extends BaseEntity {
	private static final long serialVersionUID = 3616431856969705607L;

	// 合同主体
	private Contract contract = new Contract();
	// 合同部门
	private Department contractDept = new Department();
	// 合同绑定时间
	private Date boundTime;
	// 合同解绑时间
	private Date removeTime;
	// 是否归属部门
	private boolean isDept;
	// 合同状态 1正常，0已作废
	private boolean state;
	// 版本号
	private Integer versionNumber;
	/**
	 * <p>
	 * Description:contract<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Contract getContract() {
		return contract;
	}
	/**
	 * <p>
	 * Description:contract<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	/**
	 * <p>
	 * Description:contractDept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Department getContractDept() {
		return contractDept;
	}
	/**
	 * <p>
	 * Description:contractDept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContractDept(Department contractDept) {
		this.contractDept = contractDept;
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
