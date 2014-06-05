/**   
 * @title ContactWorkflowData.java
 * @package com.deppon.crm.module.customer.shared.domain
 * @description what to do
 * @author 潘光均
 * @update 2012-7-16 上午10:32:31
 * @version V1.0   
 */
package com.deppon.crm.module.customer.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @description 联系人处于审批中的信息实体
 * @author 潘光均
 * @version 0.1 2012-7-16
 * @date 2012-7-16
 */

public class ApprovingWorkflowData extends BaseEntity {
	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 3071017415308091762L;
	// 联系人姓名
	private String contactName;
	// 联系人电话
	private String contactTel;
	// 联系人手机
	private String contactMobile;
	// 证件类型
	private String credentialsType;
	// 联系人身份证号
	private String idCardNo;
	// 数据工作流审批情况
	private Boolean status;
	// 联系人id
	private Contact contactId;
	// 工作流id
	private String workflowId;
	// 联系人编码
	private String contactNum;
	// 客户税务登记号
	private String taxregNumber;

	
	/**
	 * <p>
	 * Description:contactName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * <p>
	 * Description:contactName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * <p>
	 * Description:contactTel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactTel() {
		return contactTel;
	}

	/**
	 * <p>
	 * Description:contactTel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	/**
	 * <p>
	 * Description:contactMobile<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactMobile() {
		return contactMobile;
	}

	/**
	 * <p>
	 * Description:contactMobile<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	/**
	 * <p>
	 * Description:credentialsType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCredentialsType() {
		return credentialsType;
	}
	/**
	 * <p>
	 * Description:credentialsType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCredentialsType(String credentialsType) {
		this.credentialsType = credentialsType;
	}
	/**
	 * <p>
	 * Description:idCardNo<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getIdCardNo() {
		return idCardNo;
	}

	/**
	 * <p>
	 * Description:idCardNo<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * <p>
	 * Description:contactId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Contact getContactId() {
		return contactId;
	}

	/**
	 * <p>
	 * Description:contactId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactId(Contact contactId) {
		this.contactId = contactId;
	}

	/**
	 * <p>
	 * Description:workflowId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getWorkflowId() {
		return workflowId;
	}

	/**
	 * <p>
	 * Description:workflowId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}

	/**
	 * <p>
	 * Description:contactNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactNum() {
		return contactNum;
	}
	/**
	 * <p>
	 * Description:contactNum<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	/**
	 * <p>
	 * Description:taxregNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getTaxregNumber() {
		return taxregNumber;
	}

	/**
	 * <p>
	 * Description:taxregNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTaxregNumber(String taxregNumber) {
		this.taxregNumber = taxregNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((contactId == null) ? 0 : contactId.getId().hashCode());
		result = prime * result
				+ ((contactMobile == null) ? 0 : contactMobile.hashCode());
		result = prime * result
				+ ((contactName == null) ? 0 : contactName.hashCode());
		result = prime * result
				+ ((contactNum == null) ? 0 : contactNum.hashCode());
		result = prime * result
				+ ((contactTel == null) ? 0 : contactTel.hashCode());
		result = prime * result
				+ ((credentialsType == null) ? 0 : credentialsType.hashCode());
		result = prime * result
				+ ((idCardNo == null) ? 0 : idCardNo.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((taxregNumber == null) ? 0 : taxregNumber.hashCode());
		result = prime * result
				+ ((workflowId == null) ? 0 : workflowId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ApprovingWorkflowData other = (ApprovingWorkflowData) obj;
		if (contactId == null) {
			if (other.contactId != null) {
				return false;
			}
		} else if (!contactId.getId().equals(other.contactId.getId())) {
			return false;
		}
		if (contactMobile == null) {
			if (other.contactMobile != null) {
				return false;
			}
		} else if (!contactMobile.equals(other.contactMobile)) {
			return false;
		}
		if (contactName == null) {
			if (other.contactName != null) {
				return false;
			}
		} else if (!contactName.equals(other.contactName)) {
			return false;
		}
		if (contactNum == null) {
			if (other.contactNum != null) {
				return false;
			}
		} else if (!contactNum.equals(other.contactNum)) {
			return false;
		}
		if (contactTel == null) {
			if (other.contactTel != null) {
				return false;
			}
		} else if (!contactTel.equals(other.contactTel)) {
			return false;
		}
		if (credentialsType == null) {
			if (other.credentialsType != null) {
				return false;
			}
		} else if (!credentialsType.equals(other.credentialsType)) {
			return false;
		}
		if (idCardNo == null) {
			if (other.idCardNo != null) {
				return false;
			}
		} else if (!idCardNo.equals(other.idCardNo)) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		if (taxregNumber == null) {
			if (other.taxregNumber != null) {
				return false;
			}
		} else if (!taxregNumber.equals(other.taxregNumber)) {
			return false;
		}
		if (workflowId == null) {
			if (other.workflowId != null) {
				return false;
			}
		} else if (!workflowId.equals(other.workflowId)) {
			return false;
		}
		return true;
	}

}