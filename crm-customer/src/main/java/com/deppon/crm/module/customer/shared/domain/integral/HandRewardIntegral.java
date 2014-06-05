package com.deppon.crm.module.customer.shared.domain.integral;

import java.util.Date;


/**
 * 
 * <p>手动奖励积分<br />
 * </p>
 * @title HandRewardIntegral.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author Administrator
 * @version 0.1 2012-4-20
 */
public class HandRewardIntegral extends Integral{
	private static final long serialVersionUID = 1L;
	//奖励规则
	private RewardIntegRule rewardIntegral = new RewardIntegRule();
	//积分基数
	private Integer integralBasicNumber;
	//积分奖励时间
	private Date rewardDate;
	//创建者
	private String createName;
	//创建部门
	private String deptName;
	/**
	 * <p>
	 * Description:rewardIntegral<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public RewardIntegRule getRewardIntegral() {
		return rewardIntegral;
	}
	/**
	 * <p>
	 * Description:rewardIntegral<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setRewardIntegral(RewardIntegRule rewardIntegral) {
		this.rewardIntegral = rewardIntegral;
	}
	/**
	 * <p>
	 * Description:integralBasicNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Integer getIntegralBasicNumber() {
		return integralBasicNumber;
	}
	/**
	 * <p>
	 * Description:integralBasicNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIntegralBasicNumber(Integer integralBasicNumber) {
		this.integralBasicNumber = integralBasicNumber;
	}
	/**
	 * <p>
	 * Description:rewardDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getRewardDate() {
		return rewardDate;
	}
	/**
	 * <p>
	 * Description:rewardDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setRewardDate(Date rewardDate) {
		this.rewardDate = rewardDate;
	}
	/**
	 * <p>
	 * Description:createName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCreateName() {
		return createName;
	}
	/**
	 * <p>
	 * Description:createName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	/**
	 * <p>
	 * Description:deptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * <p>
	 * Description:deptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
