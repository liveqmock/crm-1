package com.deppon.crm.module.customer.shared.domain;

import java.util.List;


/**
 * 
 * <p>
 * 散客升级实体查询条件<br />
 * </p>
 * @title UpGradeCustomerCondition.java
 * @package com.deppon.crm.module.customer.shared.domain 
 * @author bxj
 * @version 0.2 2012-3-28
 */
public class UpGradeCustomerCondition {
	// 所属部门
	private String dept;
	// 客户姓名
	private String custName;
	// 联系人姓名
	private String contactName;
	// 联系人手机
	private String phone;
	// 联系人电话
	private String tel;
	// 目前等级
	private String nowLevel;
	// 目标级别
	private String targetLevel;
	// 统计时间 数据格式 2012-01
	private String statisticsTime;
	// 数据权限部门
	private List<String> depts;
	//客户编码
	private String custNumber;
	/**
	 * <p>
	 * Description:dept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getDept() {
		return dept;
	}
	/**
	 * <p>
	 * Description:dept<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDept(String dept) {
		this.dept = dept;
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
	 * Description:phone<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * <p>
	 * Description:phone<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * <p>
	 * Description:tel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * <p>
	 * Description:tel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * <p>
	 * Description:nowLevel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getNowLevel() {
		return nowLevel;
	}
	/**
	 * <p>
	 * Description:nowLevel<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setNowLevel(String nowLevel) {
		this.nowLevel = nowLevel;
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
	 * Description:depts<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public List<String> getDepts() {
		return depts;
	}
	/**
	 * <p>
	 * Description:depts<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDepts(List<String> depts) {
		this.depts = depts;
	}
	/**
	 *@user pgj
	 *2014-4-3上午8:20:28
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
