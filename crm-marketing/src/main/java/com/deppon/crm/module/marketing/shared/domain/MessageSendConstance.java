package com.deppon.crm.module.marketing.shared.domain;

public class MessageSendConstance {
	/**
	 * 营销短信发送手机号码有效状态(0,1)无效 有效
	 */
	//有效
	public static final String PHONE_VALIDATE_VALIDITY = "1";
	//无效
	public static final String PHONE_VALIDATE_INVALID = "0";
	/**
	 * 营销短信发送详情状态(0,1,2)未发送 已发送 发送失败
	 */
	//未发送
	public static final String PHONE_SEND_UNSEND = "0";
	//发送成功
	public static final String PHONE_SEND_SENDED = "1";
	//发送失败
	public static final String PHONE_SEND_FAILE = "2";
	/**
	 * 营销短信发送任务状态  (0,1,2,3)未执行 执行中 执行完成 执行失败 
	 */
	//未执行
	public static final String TASK_STATUS_NOTSTART = "0";
	//执行中
	public static final String TASK_STATUS_STARTED = "1";
	//执行成功
	public static final String TASK_STATUS_SUCCESS = "2";
	//执行失败
	public static final String TASK_STATUS_FAIL = "3";
	/**
	 * 任务开始读取到行数
	 */
	public static final int TASK_START_ROWNUM = 1;
	/**
	 * 每次读取的行数
	 */
	public static final int READ_MAX_COUNT = 999;
	/**
	 * 短信最大字数
	 */
	public static final int MAX_MSGCONTENT_LENGTH = 140;
	/**
	 * 失败是否因为短信接口不通
	 */
	//接口通
	public static final String IF_INTERFACE_ON = "0";
	//接口不通
	public static final String IF_INTERFACE_OFF = "1";
	/**
	 * 营销短信业务类型
	 */
	public static final String MSG_MARKETING_CODE = "YWLX20131008110636";
	/**
	 * 短信发送部门记到IT服务部
	 */
	public static final String MSG_SEND_DEPT = "DP08068";
}
