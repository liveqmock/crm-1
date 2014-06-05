package com.deppon.crm.module.customer.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @作者：罗典
 * @时间：2012-3-21
 * @描述：潜散客升级列表
 * */
public class UpGradeCustomer extends BaseEntity {

	private static final long serialVersionUID = -28382782741824598L;
	// 所属部门ID
	private String belongDeptId;
	// 目前级别--为散客
	private String currentlevel;
	// 备注信息
	private String remark;
	// 状态
	private String status;
	/*李志国添加开始**/
	//所属部门
	private String belongdeptName;
	//客户名称
	private String custName;
	//联系人姓名
	private String contactName;
	//联系人手机
	private String contactPhone;
	//联系人电话
	private String contactTel;
	//目标级别
	private String targetLevel;
	//统计时间
	private String statisticsTime;
	//客户编码
	private String custNumber;
	/*李志国添加结束**/
	/**
	 * <p>
	 * Description:belongDeptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getBelongDeptId() {
		return belongDeptId;
	}
	/**
	 * <p>
	 * Description:belongDeptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBelongDeptId(String belongDeptId) {
		this.belongDeptId = belongDeptId;
	}
	/**
	 * <p>
	 * Description:currentlevel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCurrentlevel() {
		return currentlevel;
	}
	/**
	 * <p>
	 * Description:currentlevel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCurrentlevel(String currentlevel) {
		this.currentlevel = currentlevel;
	}
	/**
	 * <p>
	 * Description:remark<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * <p>
	 * Description:remark<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
	 * Description:belongdeptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getBelongdeptName() {
		return belongdeptName;
	}
	/**
	 * <p>
	 * Description:belongdeptName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setBelongdeptName(String belongdeptName) {
		this.belongdeptName = belongdeptName;
	}
	/**
	 * <p>
	 * Description:custName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * <p>
	 * Description:custName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * <p>
	 * Description:contactName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * <p>
	 * Description:contactName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * <p>
	 * Description:contactPhone<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactPhone() {
		return contactPhone;
	}
	/**
	 * <p>
	 * Description:contactPhone<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	/**
	 * <p>
	 * Description:contactTel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactTel() {
		return contactTel;
	}
	/**
	 * <p>
	 * Description:contactTel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
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
	 * Description:statisticsTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getStatisticsTime() {
		return statisticsTime;
	}
	/**
	 * <p>
	 * Description:statisticsTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setStatisticsTime(String statisticsTime) {
		this.statisticsTime = statisticsTime;
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
	/**
	 *@user pgj
	 *2014-4-3上午8:18:55
	 * @return custNumber : return the property custNumber.
	 */
	public String getCustNumber() {
		return custNumber;
	}
	/**
	 * @param custNumber : set the property custNumber.
	 */
	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}
}
