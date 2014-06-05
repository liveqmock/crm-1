package com.deppon.crm.module.customer.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @作者：罗典
 * @时间：2012-3-21
 * @描述：会员升级列表
 * */
public class MemberLiftList extends BaseEntity{
	private static final long serialVersionUID = 1L;
	// 客户ID
	private String custId;
	// 目前级别
	private String currentLevel;
	// 目标级别
	private String targetLevel;
	// 状态
	private String status;
	// 批次
	private String batch;
	// 升降级类型
	private String liftType;
	/**
	 * <p>
	 * Description:custId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * <p>
	 * Description:custId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	/**
	 * <p>
	 * Description:currentLevel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCurrentLevel() {
		return currentLevel;
	}
	/**
	 * <p>
	 * Description:currentLevel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCurrentLevel(String currentLevel) {
		this.currentLevel = currentLevel;
	}
	/**
	 * <p>
	 * Description:targetLevel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getTargetLevel() {
		return targetLevel;
	}
	/**
	 * <p>
	 * Description:targetLevel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTargetLevel(String targetLevel) {
		this.targetLevel = targetLevel;
	}
	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * <p>
	 * Description:batch<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getBatch() {
		return batch;
	}
	/**
	 * <p>
	 * Description:batch<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBatch(String batch) {
		this.batch = batch;
	}
	/**
	 * <p>
	 * Description:liftType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLiftType() {
		return liftType;
	}
	/**
	 * <p>
	 * Description:liftType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLiftType(String liftType) {
		this.liftType = liftType;
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
