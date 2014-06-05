/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MiddleEntity.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author Administrator
 * @version 0.1 2012-3-24
 */
package com.deppon.crm.module.marketing.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * <p>
 * 中间实体<br />
 * </p>
 * 
 * @title MiddleEntity.java
 * @package com.deppon.crm.module.marketing.shared.domain
 * @author 苏玉军
 * @version 0.1 2012-3-24
 */

public class PlanCustEntity extends BaseEntity {
	/** 
	 * @fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = -3280513896584413289L;
	//计划Id
	private String planId;
	//客户Id
	private String  custId;
	//客户类型
	private String custType;
	/**
	 * @return planId : return the property planId.
	 */
	public String getPlanId() {
		return planId;
	}
	/**
	 * @param planId : set the property planId.
	 */
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	/**
	 * @return custId : return the property custId.
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * @param custId : set the property custId.
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	/**
	 * @return custType : return the property custType.
	 */
	public String getCustType() {
		return custType;
	}
	/**
	 * @param custType : set the property custType.
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}
	
}
