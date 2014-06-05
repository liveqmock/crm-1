package com.deppon.crm.module.authorization.shared.exception;             

public enum VirtualPositionExceptionType {
	//已存在该职位，请核实
	ExistVirtualPostionName("i18n.virtualPosition.existVirtualPostionName"),
	//虚拟岗位名称不能为空
	VirtualPostionNameIsNull("i18n.virtualPosition.VirtualPostionNameIsNull"),
	//虚拟岗位有对应角色
	HasRelationForVirtualPositionRole("i18n.virtualPosition.HasRelationForVirtualPositionRole"),
	FailureRefresh("i18n.virtualPosition.FailureRefresh"), 
	NotSelectVirtualPostion("i18n.virtualPosition.NotSelectVirtualPosition");
	
	private String errCode;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	private VirtualPositionExceptionType(String errorCode) {
		this.errCode = errorCode;
	}
}
