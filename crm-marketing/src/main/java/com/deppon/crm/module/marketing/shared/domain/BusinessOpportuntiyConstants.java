package com.deppon.crm.module.marketing.shared.domain;

public class BusinessOpportuntiyConstants {
	// 商机状态(BUSINESS_OPPORTUNITY_STATUS):进行中、超期进行中、已休眠、成功关闭、失败关闭
	// 商机状态(BUSINESS_OPPORTUNITY_STATUS):进行中
	public static final String BO_STATUS_ONGOING = "ONGOING";
	// 商机状态(BUSINESS_OPPORTUNITY_STATUS):超期进行中
	public static final String BO_STATUS_EXTENDED = "EXTENDED";
	// 商机状态(BUSINESS_OPPORTUNITY_STATUS):成功关闭
	public static final String BO_STATUS_SUCCESS = "SUCCESS";
	// 商机状态(BUSINESS_OPPORTUNITY_STATUS):失败关闭
	public static final String BO_STATUS_FAILURE = "FAILURE";
	// 商机状态(BUSINESS_OPPORTUNITY_STATUS):已休眠
	public static final String BO_STATUS_DORMANT = "DORMANT";

	// 商机阶段(BUSINESS_OPPORTUNITY_STEP):初步接触、需求分析、制定方案、报价/竞标、持续发货
	// 商机阶段(BUSINESS_OPPORTUNITY_STEP):初步接触
	public static final String BO_STEP_CONTACT = "CONTACT";
	// 商机阶段(BUSINESS_OPPORTUNITY_STEP):需求分析
	public static final String BO_STEP_ANALYZE = "ANALYZE";
	// 商机阶段(BUSINESS_OPPORTUNITY_STEP):制定方案
	public static final String BO_STEP_SCHEME = "SCHEME";
	// 商机阶段(BUSINESS_OPPORTUNITY_STEP):报价/竞标
	public static final String BO_STEP_OFFER = "OFFER";
	// 商机阶段(BUSINESS_OPPORTUNITY_STEP):持续发货
	public static final String BO_STEP_DELIVER = "DELIVER";

	// 商机关闭原因(BO_CLOSE_REASON):发货潜力达不到大客户标准；价格需求不能满足；时效需求不能满足；安全需求不能满足；服务需求不能满足；增值服务需求不能满足；无走货意向；其他；到期关闭；休眠关闭
	// 商机关闭原因(BO_CLOSE_REASON):发货潜力达不到大客户标准
	public static final String BO_CLOSE_REASON_STANDARD = "STANDARD";
	// 商机关闭原因(BO_CLOSE_REASON):价格需求不能满足
	public static final String BO_CLOSE_REASON_PRICE = "PRICE";
	// 商机关闭原因(BO_CLOSE_REASON):时效需求不能满足
	public static final String BO_CLOSE_REASON_TIMELINESS = "TIMELINESS";
	// 商机关闭原因(BO_CLOSE_REASON):安全需求不能满足
	public static final String BO_CLOSE_REASON_SAFETY = "SAFETY";
	// 商机关闭原因(BO_CLOSE_REASON):服务需求不能满足
	public static final String BO_CLOSE_REASON_SERVICES = "SERVICES";
	// 商机关闭原因(BO_CLOSE_REASON):增值服务需求不能满足
	public static final String BO_CLOSE_REASON_ADDED = "ADDED";
	// 商机关闭原因(BO_CLOSE_REASON):无走货意向
	public static final String BO_CLOSE_REASON_INTENTION = "INTENTION";
	// 商机关闭原因(BO_CLOSE_REASON):到期关闭
	public static final String BO_CLOSE_REASON_EXPIRE = "EXPIRE";
	// 商机关闭原因(BO_CLOSE_REASON):休眠关闭
	public static final String BO_CLOSE_REASON_DORMANT = "DORMANT";
	// 商机关闭原因(BO_CLOSE_REASON):其他
	public static final String BO_CLOSE_REASON_OTHER = "OTHER";

	// 事业部模糊查询内容
	public static final String BIZ_DEPT_CONDITION = "%事业部";

	// 商机来源
	public static final String BO_COME_BUSINESS = "BUSINESS";

	// 待办事宜消息类型
	public static final String TASK_TYPE_MESSAGE = "BO_MESSAGE";

	// 创建商机提醒
	public static final String CREATE_MESSAGE = "{0}客户（{1}）已被创建商机";

	// 创建商机提醒
	public static final String TODO_MESSAGE = "您商机名称为“{0}”的商机，商机编码为“{1}”还有1个月即将关闭，请周知。";

}
