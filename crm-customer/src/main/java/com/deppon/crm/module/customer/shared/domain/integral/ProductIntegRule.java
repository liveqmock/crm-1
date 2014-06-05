package com.deppon.crm.module.customer.shared.domain.integral;

/**
 * 
 * <p>
 * 产品积分规则<br />
 * </p>
 * 
 * @title ProjectIntegRule.java
 * @package com.deppon.crm.module.customer.shared.domain
 * @author bxj
 * @version 0.2 2012-4-19
 */
@SuppressWarnings("serial")
public class ProductIntegRule extends IntegRule {
	// 产品
	private String product;
	// 创建人
	private String cname;
	// 修改人
	private String mname;
	/**
	 * <p>
	 * Description:product<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getProduct() {
		return product;
	}
	/**
	 * <p>
	 * Description:product<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setProduct(String product) {
		this.product = product;
	}
	/**
	 * <p>
	 * Description:cname<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCname() {
		return cname;
	}
	/**
	 * <p>
	 * Description:cname<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}
	/**
	 * <p>
	 * Description:mname<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getMname() {
		return mname;
	}
	/**
	 * <p>
	 * Description:mname<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMname(String mname) {
		this.mname = mname;
	}
}
