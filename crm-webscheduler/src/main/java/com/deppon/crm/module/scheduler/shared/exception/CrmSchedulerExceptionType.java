package com.deppon.crm.module.scheduler.shared.exception;

/**
 * @description 定时器异常类型
 * @author 黄展明
 * @version 0.1 2012-3-30
 * @date 2012-3-30
 */

public enum CrmSchedulerExceptionType {

	// 表达式格式错误
	SCHEDULER_CRON_EXPRESSION_ERROR("i18n.scheduler.cronexpression.error"),
	// 查询异常
	SCHEDULER_QUERY_ERROR("i18n.scheduler.query.error"),
	// 删除异常
	SCHEDULER_DELETE_ERROR("i18n.scheduler.delete.error"),
	// 类未找到
	SCHEDULER_CLASS_NOT_FOUND_ERROR("i18n.scheduler.classnotfound.error"),
	// 保存异常
	SCHEDULER_SAVE_ERROR("i18n.scheduler.save.error"),
	// 停止异常
	SCHEDULER_STOP_ERROR("i18n.scheduler.stop.error"),
	// 开始异常
	SCHEDULER_START_ERROR("i18n.scheduler.start.error"),
	// 执行异常
	SCHEDULER_EXECUTE_ERROR("i18n.scheduler.execute.error"),
	// 表单操作异常
	SCHEDULER_DEFAULT_ERROR("i18n.scheduler.default.error");

	private String errorCode;

	private CrmSchedulerExceptionType(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public static String getValue(CrmSchedulerExceptionType type) {
		switch (type) {
		case SCHEDULER_CRON_EXPRESSION_ERROR:
			return SCHEDULER_CRON_EXPRESSION_ERROR.getErrorCode();
		case SCHEDULER_QUERY_ERROR:
			return SCHEDULER_QUERY_ERROR.getErrorCode();
		case SCHEDULER_DELETE_ERROR:
			return SCHEDULER_DELETE_ERROR.getErrorCode();
		case SCHEDULER_CLASS_NOT_FOUND_ERROR:
			return SCHEDULER_CLASS_NOT_FOUND_ERROR.getErrorCode();
		case SCHEDULER_SAVE_ERROR:
			return SCHEDULER_SAVE_ERROR.getErrorCode();
		case SCHEDULER_STOP_ERROR:
			return SCHEDULER_STOP_ERROR.getErrorCode();
		case SCHEDULER_START_ERROR:
			return SCHEDULER_START_ERROR.getErrorCode();
		case SCHEDULER_EXECUTE_ERROR:
			return SCHEDULER_EXECUTE_ERROR.getErrorCode();
		default:
			return SCHEDULER_DEFAULT_ERROR.getErrorCode();
		}
	}
}