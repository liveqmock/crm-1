package com.deppon.crm.module.order.shared.domain;

import java.io.Serializable;
import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;
/**
 * 
 * <p>
 * Description:订单提醒实体<br />
 * </p>
 * @title OrderReminder.java
 * @package com.deppon.crm.module.order.shared.domain 
 * @author zouming
 * @version 0.1 2013-1-22上午11:14:14
 */
public class OrderReminder extends BaseEntity implements Serializable {
	//序列化对象的标识
	private static final long serialVersionUID = 3909055470968586031L;
	//x
	private Integer orderQty;
	//提醒类型
	private String remindType;
	//提醒时间
	private Date remindTime;
	//部门ID
	private String deptId;
	//读取标识
	private String readFlag;
	/**
	 *@return  orderQty;
	 */
	public Integer getOrderQty() {
		return orderQty;
	}
	/**
	 * @param orderQty : set the property orderQty.
	 */
	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}
	/**
	 *@return  remindType;
	 */
	public String getRemindType() {
		return remindType;
	}
	/**
	 * @param remindType : set the property remindType.
	 */
	public void setRemindType(String remindType) {
		this.remindType = remindType;
	}
	/**
	 *@return  remindTime;
	 */
	public Date getRemindTime() {
		return remindTime;
	}
	/**
	 * @param remindTime : set the property remindTime.
	 */
	public void setRemindTime(Date remindTime) {
		this.remindTime = remindTime;
	}
	/**
	 *@return  deptId;
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * @param deptId : set the property deptId.
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 *@return  readFlag;
	 */
	public String getReadFlag() {
		return readFlag;
	}
	/**
	 * @param readFlag : set the property readFlag.
	 */
	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}
}
