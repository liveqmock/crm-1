package com.deppon.crm.module.uums.shared.domain;

import java.util.Date;
/**
 * 
 * @description 接口传输参数基类
 * @author zzw
 * @version 0.1 2013-11-25
 */
public class BaseUUEntity {
	//变动ID 无实际意义，uums进行记录
	protected  String theChangeId;
	//变更数据中的编码（员工编码，部门编码，用户编码）
	protected  String theMainCode;
	//变更数据中的ID
	protected String theMainId;
	// 变动类型(新增、修改、异动、待撤销、已撤销:转换为)
	protected  String  changeType;
	//变动时间
	protected  Date changeDate;
	public String getTheChangeId() {
		return theChangeId;
	}
	public void setTheChangeId(String theChangeId) {
		this.theChangeId = theChangeId;
	}
	public String getChangeType() {
		return changeType;
	}
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
	public Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	public String getTheMainCode() {
		return theMainCode;
	}
	public void setTheMainCode(String theMainCode) {
		this.theMainCode = theMainCode;
	}
	public String getTheMainId() {
		return theMainId;
	}
	public void setTheMainId(String theMainId) {
		this.theMainId = theMainId;
	}
	
}
