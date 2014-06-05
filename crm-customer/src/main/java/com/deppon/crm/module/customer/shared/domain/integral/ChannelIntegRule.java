package com.deppon.crm.module.customer.shared.domain.integral;

/**
 * 
 * <p>
 * 渠道规则<br />
 * </p>
 * 
 * @title ChannelIntegRule.java
 * @package com.deppon.crm.module.customer.shared.domain
 * @author bxj
 * @version 0.2 2012-4-19
 */
public class ChannelIntegRule extends IntegRule {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 9008709881802196206L;
	// 订单渠道
	private String channeltype;
	// 创建人
	private String cname;
	// 修改人
	private String mname;
	/**
	 * <p>
	 * Description:channeltype<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getChanneltype() {
		return channeltype;
	}
	/**
	 * <p>
	 * Description:channeltype<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setChanneltype(String channeltype) {
		this.channeltype = channeltype;
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
