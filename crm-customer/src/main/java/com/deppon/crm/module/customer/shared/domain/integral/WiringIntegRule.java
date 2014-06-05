package com.deppon.crm.module.customer.shared.domain.integral;


/**
 * 
 * <p>
 * 线路积分规则<br />
 * </p>
 * @title WiringIntegRule.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author bxj
 * @version 0.2 2012-4-19
 */
public class WiringIntegRule extends IntegRule{
	/** 
	 * @fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = 1L;
	//出发城市
	private String leadept;
	//到达城市
	private String arrdept;
	//出发城市名称
	private String leadeptName;
	//到达城市名称
	private String arrdeptName;
	//创建人
	private String cname;
	//修改人
	private String mname;
	/**
	 * <p>
	 * Description:leadept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLeadept() {
		return leadept;
	}
	/**
	 * <p>
	 * Description:leadept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLeadept(String leadept) {
		this.leadept = leadept;
	}
	/**
	 * <p>
	 * Description:arrdept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getArrdept() {
		return arrdept;
	}
	/**
	 * <p>
	 * Description:arrdept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setArrdept(String arrdept) {
		this.arrdept = arrdept;
	}
	/**
	 * <p>
	 * Description:leadeptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLeadeptName() {
		return leadeptName;
	}
	/**
	 * <p>
	 * Description:leadeptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLeadeptName(String leadeptName) {
		this.leadeptName = leadeptName;
	}
	/**
	 * <p>
	 * Description:arrdeptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getArrdeptName() {
		return arrdeptName;
	}
	/**
	 * <p>
	 * Description:arrdeptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setArrdeptName(String arrdeptName) {
		this.arrdeptName = arrdeptName;
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
