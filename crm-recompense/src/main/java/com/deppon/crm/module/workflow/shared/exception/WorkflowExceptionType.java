package com.deppon.crm.module.workflow.shared.exception;

/**
 * @description 工作流异常
 * @author andy
 * @version 0.1 
 * @date 2013-8-13
 */
public enum WorkflowExceptionType {
	// 用户未登录
	RECOMPENSE_USER_NOTLOGIN("i18n.recompense.err.userNotLogin"),
	// 请检查新增配置规则
	WORKFLOW_CREATEAMOUNTCONFIG_FAIL("i18n.workflow.createAmountConfig.fail"),
	// 流程已审批，请勿重复提交！
	BPMS_APPROVE_WORKFLOW_FAIL("i18n.workflow.approveWorkflow.fail"),
	// 短信通知快递业务管理部负责人失败
	WORKFLOW_SENDMSG_FAIL("i18n.workflow.sendMsg.fail");

	private String errorCode;

	private WorkflowExceptionType(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public static String getValue(WorkflowExceptionType type) {
		switch (type) {
			case WORKFLOW_CREATEAMOUNTCONFIG_FAIL:
				return WORKFLOW_CREATEAMOUNTCONFIG_FAIL.getErrorCode();
			case BPMS_APPROVE_WORKFLOW_FAIL:
				return BPMS_APPROVE_WORKFLOW_FAIL.getErrorCode();
			case WORKFLOW_SENDMSG_FAIL:
				return WORKFLOW_SENDMSG_FAIL.getErrorCode();
			default:
				return RECOMPENSE_USER_NOTLOGIN.getErrorCode();
		}
	}
}