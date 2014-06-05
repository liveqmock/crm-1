package com.deppon.crm.module.authorization.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * 虚拟岗位管理实体类<br />
 * </p>
 * 
 * @title VirtualPosition.java
 * @package com.deppon.crm.module.authorization.shared.domain
 * @author suyujun
 * @version 0.1 2013-11-26
 */
public class VirtualPosition extends BaseEntity {
	private static final long serialVersionUID = -1399797907159883676L;

	private String virtualPositionName;

	private String desc;

	/**
	 * @return virtualPositionName : return the property virtualPositionName.
	 */
	public String getVirtualPositionName() {
		return virtualPositionName;
	}

	/**
	 * @param virtualPositionName : set the property virtualPositionName.
	 */
	public void setVirtualPositionName(String virtualPositionName) {
		this.virtualPositionName = virtualPositionName;
	}

	/**
	 * @return desc : return the property desc.
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 
	 * @param desc : set the property desc.
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VirtualPosition [virtualPositionName=" + virtualPositionName
				+ ", desc=" + desc + "]";
	}
}
