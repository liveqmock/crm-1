package com.deppon.crm.module.customer.shared.domain.integral;

/**
 * 
 * <p>
 * 积分奖励规则<br />
 * </p>
 * 
 * @title RewardIntegral.java
 * @package com.deppon.crm.module.customer.shared.domain
 * @author bxj
 * @version 0.2 2012-4-20
 */
public class RewardIntegRule extends IntegRule {
	/** 
	 * @fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = 1L;
	// 奖励积分数值
	private int pointvalue;
	// 奖励类型
	private String ruletype;
	// 积分类型
	private String integType;
	// 奖励名称
	private String rulename;
	// 会员等级限制
	private String memlevelrest;
	// 奖励人数限制
	private String limitperson;
	// 奖励人数限制频率
	private String limitfrequency;
	// 奖励人数限制频率单位
	private String limitfreunit;
	// 限制奖励次数
	private String limittimes;
	// 奖励次数限制频率
	private String limitrestfreq;
	// 奖励次数限制频率单位
	private String limittimeunit;
	// 创建人
	private String cname;
	// 修改人
	private String mname;
	/**
	 * <p>
	 * Description:pointvalue<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public int getPointvalue() {
		return pointvalue;
	}
	/**
	 * <p>
	 * Description:pointvalue<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setPointvalue(int pointvalue) {
		this.pointvalue = pointvalue;
	}
	/**
	 * <p>
	 * Description:ruletype<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getRuletype() {
		return ruletype;
	}
	/**
	 * <p>
	 * Description:ruletype<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setRuletype(String ruletype) {
		this.ruletype = ruletype;
	}
	/**
	 * <p>
	 * Description:integType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getIntegType() {
		return integType;
	}
	/**
	 * <p>
	 * Description:integType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIntegType(String integType) {
		this.integType = integType;
	}
	/**
	 * <p>
	 * Description:rulename<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getRulename() {
		return rulename;
	}
	/**
	 * <p>
	 * Description:rulename<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setRulename(String rulename) {
		this.rulename = rulename;
	}
	/**
	 * <p>
	 * Description:memlevelrest<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getMemlevelrest() {
		return memlevelrest;
	}
	/**
	 * <p>
	 * Description:memlevelrest<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMemlevelrest(String memlevelrest) {
		this.memlevelrest = memlevelrest;
	}
	/**
	 * <p>
	 * Description:limitperson<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLimitperson() {
		return limitperson;
	}
	/**
	 * <p>
	 * Description:limitperson<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLimitperson(String limitperson) {
		this.limitperson = limitperson;
	}
	/**
	 * <p>
	 * Description:limitfrequency<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLimitfrequency() {
		return limitfrequency;
	}
	/**
	 * <p>
	 * Description:limitfrequency<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLimitfrequency(String limitfrequency) {
		this.limitfrequency = limitfrequency;
	}
	/**
	 * <p>
	 * Description:limitfreunit<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLimitfreunit() {
		return limitfreunit;
	}
	/**
	 * <p>
	 * Description:limitfreunit<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLimitfreunit(String limitfreunit) {
		this.limitfreunit = limitfreunit;
	}
	/**
	 * <p>
	 * Description:limittimes<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLimittimes() {
		return limittimes;
	}
	/**
	 * <p>
	 * Description:limittimes<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLimittimes(String limittimes) {
		this.limittimes = limittimes;
	}
	/**
	 * <p>
	 * Description:limitrestfreq<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLimitrestfreq() {
		return limitrestfreq;
	}
	/**
	 * <p>
	 * Description:limitrestfreq<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLimitrestfreq(String limitrestfreq) {
		this.limitrestfreq = limitrestfreq;
	}
	/**
	 * <p>
	 * Description:limittimeunit<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLimittimeunit() {
		return limittimeunit;
	}
	/**
	 * <p>
	 * Description:limittimeunit<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLimittimeunit(String limittimeunit) {
		this.limittimeunit = limittimeunit;
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
