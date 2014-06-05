package com.deppon.crm.module.client.workflow.domain;

public enum ContractApplyType {
	// 合同新增
	NEW,
	// 合同修改
	UPDATE,
	// 绑定部门新增
	ADD_BELONGDEPT,
	// 绑定部门修改
	CONVERT_BELONGDEPT,
	// 合同终止
	CANCEL,
	//非纯零担合同新增
	EX_NEW,
	//非纯零担合同修改
	EX_UPDATE,
	//非纯零担绑定部门新增
	EX_ADD_BELONGDEPT,
	//非纯零担绑定部门修改
	EX_CONVERT_BELONGDEPT,
	//非纯零担合同终止
	EX_CANCEL
}
