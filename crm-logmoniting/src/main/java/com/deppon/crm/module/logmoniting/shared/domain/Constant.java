package com.deppon.crm.module.logmoniting.shared.domain;

/**   
 * @Description:常量<br />
 * @title Constant.java
 * @package com.deppon.crm.module.logmoniting.shared.domain 
 * @author CoCo
 * @version 0.1 2013-6-21
 */
public class Constant {
	// boolean--true
	public static final boolean TrueValue = true;
	// boolean--false
	public static final boolean FalseValue = false;
	
	/**
	 * 统计频率
	 */
	//统计频率--年
	public static final String STATISTICALFREQUENCY_YEAR = "year";
	//统计频率--月
	public static final String STATISTICALFREQUENCY_MONTH = "month";
	//统计频率--日
	public static final String STATISTICALFREQUENCY_DAY = "day";
	//统计频率--时
	public static final String STATISTICALFREQUENCY_HOUR = "hour";
	
	/**
	 * 统计方式
	 */
	//统计方式 请求时间统计
	public static final String STATISTICALMETHODS_REQUESTTIME = "requestTime";
	//统计方式 请求次数统计
	public static final String STATISTICALMETHODS_REQUESTCOUNT = "requestNumber";
	
	//一个小时毫秒的常量
	public static final long OneHourForLongMills = 3600000l;
	//星期一
	public static final int Monday = 1;
	//星期二
	public static final int Tuesday = 2;
	//星期三
	public static final int Wednesday = 3;
	//星期四
	public static final int Thursday = 4;
	//星期五
	public static final int Friday = 5;
	//星期六
	public static final int Saturday = 6;
	//星期日
	public static final int Sunday  = 7;
	
	/**
	 * 通过标识是哪一个定时器去定义附件表头以及封装什么样的数据
	 */
	//一小时发送一次邮件的标识--昨天与今天同一个小时action预警信息标识
	public static  final String LOGININFO_ERROR_ONEHOUR = "1";
	//每天发送一次邮件标示---前天与昨天前十action进行比较
	public static final String TOPTEN_LOGINFO_ERROR_ONEDAY = "2";
	//每天发送一次邮件标示---今日统计平均信息与基础信息的最大值比较
	public static final String AVERAGE_LOGINFO_ERROR_ONEDAY = "3";
	
	//系统管理员
	public static final String SYSTEM_USER = "86301";
	
	//是否分页--标识
	public static final String WHETHER_PAGING = "Whether_Paging";
	
	/**
	 * 日志级别
	 */
	public static final String WARN_LOG = "WARN";
	public static final String ERROR_LOG = "ERROR";
	public static final String INFO_LOG	= "INFO";
	public static final String WARNING_LOG = "WARNING";
	
	/**
	 * 操作类型
	 */
	public static final String ADD = "add";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
	
	/**
	 * 所有发送的邮箱。现已写死--客户关系和客户营销
	 */
	public static final String MAIL_PERSON = "W01010602@deppon.com;W01000301040505@deppon";
	public static final String MAILPERSON = "liguowen@deppon.com;dphzm@deppon.com;" +
			"lichunyu002@deppon.com;huaoguo@deppon.com;dpsyj@deppon.com;zhupeijun@deppon.com;dppgj@deppon.com;dpzhangd@deppon.com;tangliang006@deppon.com;"
			+"zhouyuan003@deppon.com;liyi013@deppon.com;xuhualong@deppon.com";
}
