package com.deppon.crm.module.customer.shared.domain.integral;

import com.deppon.crm.module.customer.shared.domain.Contact;

/**
 * 
 * <p>
 * 调整积分<br />
 * </p>
 * 
 * @title AdjustIntegral.java
 * @package com.deppon.crm.module.customer.shared.domain
 * @author Administrator
 * @version 0.1 2012-4-20
 */
@SuppressWarnings("serial")
public class AdjustIntegral extends Integral{

	// 调整类型
	private String adjustType;
	// 调整去向
	private Contact contactFrom;
	// 调整来源
	private Contact contactTo;
	/**
	 * <p>
	 * Description:adjustType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getAdjustType() {
		return adjustType;
	}
	/**
	 * <p>
	 * Description:adjustType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}
	/**
	 * <p>
	 * Description:contactFrom<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Contact getContactFrom() {
		return contactFrom;
	}
	/**
	 * <p>
	 * Description:contactFrom<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactFrom(Contact contactFrom) {
		this.contactFrom = contactFrom;
	}
	/**
	 * <p>
	 * Description:contactTo<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Contact getContactTo() {
		return contactTo;
	}
	/**
	 * <p>
	 * Description:contactTo<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactTo(Contact contactTo) {
		this.contactTo = contactTo;
	}
}
