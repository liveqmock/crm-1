package com.deppon.crm.module.client.order.domain;

import java.util.Date;

/**
 * @作者：罗典
 * @描述：ERP提醒实体
 * @时间：2012-10-25
 * */
public class ErpRemind {
	// 子系统模块：订单受理
	public static final String ORDER_ACCEPT_MODULE="订单受理";
	// 子系统模块：订单分配
	public static final String ORDER_ALLOT_MODULE="订单分配";
	// 子模块编码：订单受理
	public static final int ORDER_ACCEPT = 14;
	// 子模块编码：订单分配
	public static final int ORDER_ALLOT = 15;
	// 其它说明:  请在CRM系统中及时受理订单
	public static final String ORDER_ACCEPT_DESC ="请在CRM系统中及时受理订单";
	// 其它说明: 请在CRM系统中及时分配订单
	public static final String ORDER_ALLOT_DESC="请在CRM系统中及时分配订单";
	
	// 其它说明
	private String fdescription;
	// 最后修改时间
	private Date flastupdateDate;
	// 消息数
	private int fmsgNum;
	// 部门标杆编码
	private String fstandardcode;
	// 子系统模块
	private String fsubModule;
	// 子模块编码
	private int fsubModuleNum;
	// 子系统
	private String fsubSys="CRM系统";

	public String getFdescription() {
		return fdescription;
	}

	public void setFdescription(String fdescription) {
		this.fdescription = fdescription;
	}

	public Date getFlastupdateDate() {
		return flastupdateDate;
	}

	public void setFlastupdateDate(Date flastupdateDate) {
		this.flastupdateDate = flastupdateDate;
	}

	public int getFmsgNum() {
		return fmsgNum;
	}

	public void setFmsgNum(int fmsgNum) {
		this.fmsgNum = fmsgNum;
	}

	public String getFstandardcode() {
		return fstandardcode;
	}

	public void setFstandardcode(String fstandardcode) {
		this.fstandardcode = fstandardcode;
	}

	public String getFsubModule() {
		return fsubModule;
	}

	public void setFsubModule(String fsubModule) {
		this.fsubModule = fsubModule;
	}

	public int getFsubModuleNum() {
		return fsubModuleNum;
	}

	public void setFsubModuleNum(int fsubModuleNum) {
		this.fsubModuleNum = fsubModuleNum;
	}

	public String getFsubSys() {
		return fsubSys;
	}

	public void setFsubSys(String fsubSys) {
		this.fsubSys = fsubSys;
	}
}
