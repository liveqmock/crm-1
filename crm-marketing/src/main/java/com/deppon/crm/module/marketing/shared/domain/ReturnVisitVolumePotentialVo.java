/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReturnVisitVolumePotentialVo.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author Administrator
 * @version 0.1 2012-11-12
 */
package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;

/**   
 * <p>
 * 营销历史查询-<br />
 * </p>
 * @title ReturnVisitVolumePotentialVo.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author 苏玉军
 * @version 0.1 2012-11-12
 */

public class ReturnVisitVolumePotentialVo extends ReturnVisitVolumePotential {
	private static final long serialVersionUID = -158003291716837497L;
	//联系人名称
	private String custlinkManName;
	//营销方式
	private String marketingMethod;
	//营销人
	private String marketingPerson;
	//营销时间
	private Date marketingDate;
	/**
	 * @return custlinkManName : return the property custlinkManName.
	 */
	public String getCustlinkManName() {
		return custlinkManName;
	}
	/**
	 * @param custlinkManName : set the property custlinkManName.
	 */
	public void setCustlinkManName(String custlinkManName) {
		this.custlinkManName = custlinkManName;
	}
	/**
	 * @return marketingMethod : return the property marketingMethod.
	 */
	public String getMarketingMethod() {
		return marketingMethod;
	}
	/**
	 * @param marketingMethod : set the property marketingMethod.
	 */
	public void setMarketingMethod(String marketingMethod) {
		this.marketingMethod = marketingMethod;
	}
	/**
	 * @return marketingPerson : return the property marketingPerson.
	 */
	public String getMarketingPerson() {
		return marketingPerson;
	}
	/**
	 * @param marketingPerson : set the property marketingPerson.
	 */
	public void setMarketingPerson(String marketingPerson) {
		this.marketingPerson = marketingPerson;
	}
	/**
	 * @return marketingDate : return the property marketingDate.
	 */
	public Date getMarketingDate() {
		return marketingDate;
	}
	/**
	 * @param marketingDate : set the property marketingDate.
	 */
	public void setMarketingDate(Date marketingDate) {
		this.marketingDate = marketingDate;
	}
}
