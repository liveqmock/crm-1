/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MarketingConstance.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author Administrator
 * @version 0.1 2012-3-26
 */
package com.deppon.crm.module.marketing.shared.domain;

import java.util.HashMap;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.ExpressPosition;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MarketingConstance.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author 苏玉军
 * @version 0.1 2012-3-26
 */

public class MarketingConstance {
	/**
	 *	快递市场营销组标杆编码
	 */
	public static final String EXPRESS_MARKETING_GROUP_STANDARDCODE ="DP11921";
	//快递岗位集合
	public static final Map<String,String> EXPRESS_POSITIONS = new HashMap<String, String>();
	static{
		EXPRESS_POSITIONS.put("快递员", ExpressPosition.EXPRESS_COURIER_POSTION);
		EXPRESS_POSITIONS.put("点部经理", ExpressPosition.EXPRESS_POINT_MANAGER_POSITION);
		EXPRESS_POSITIONS.put("分部高级经理", ExpressPosition.EXPRESS_DIVISIONS_SENIOR_MANAGER_POSITION);
		EXPRESS_POSITIONS.put("快递大区总经理", ExpressPosition.EXPRESS_REGION_GENERAL_MANAGER);
		EXPRESS_POSITIONS.put("快递市场营销组总监", ExpressPosition.EXPRESS_GROUP_DIRECTOR_MARKETING);
	}
	//日程来源，商机
	public static final String RES_SCHEDULE_BUSINESS = "BUSINESS";
	//回访方式
	public static final String RETURNVISIT_WAY_PHONE = "PHONE";
	// 类型-开发
	public static final String DEVELOP_TYPE = "dev";
	
	// 类型-维护
	public static final String MAINTAIN_TYPE = "mat";
	
	// 类型-每月固定计划
	public static final String FIXEDPLAN_MONTHLY_TYPE = "monthly";

	// 类型-每日固定计划
//	public static final String FIXEDPLAN_DAILY_TYPE = "daily";
	// 类型-每周固定计划
	public static final String FIXEDPLAN_WEEKLY_TYPE = "weekly";
	// 市场推广活动
	public static final String FIXEDPLAN_ACTIVITY_TYPE = "activity";
	
	// 分组客户数限制
	public static final int GROUPCUSTOMER_MAX = 50;
	// 发到货周期页面
	public static final String PAGE_TYPE_CYCLE = "1";
	// 五公里-潜散客
	public static final String PAGE_TYPE_PS = "2";
	// 五公里-会员
	public static final String PAGE_TYPE_ME = "3";

	//定位目标
	public static final String FIXED_POSITION = "FIXED_POSITION";
	//接触客户
	public static final String CONTACT_CUSTOMER = "CONTACT_CUSTOMER";
	//开始发货
	public static final String START_SHIPMENT = "START_SHIPMENT";
	//升级会员
	public static final String PREMIUM_MEMBER = "PREMIUM_MEMBER";

	/**
	 * 客户等级
	 */
	//普通
	public static final String CUSTOMER_DEGREE_NORMAL = "NORMAL";
	//黄金
	public static final String CUSTOMER_DEGREE_GOLD = "GOLD";
	//铂金
	public static final String CUSTOMER_DEGREE_PLATINUM = "PLATINUM";
	//钻石
	public static final String CUSTOMER_DEGREE_DIAMOND = "DIAMOND";
	
	
	/**
	 * 客户类型
	 */
	//潜客
	public static final String POTENTIAL_CUSTOMER="PC_CUSTOMER";
	//散客
	public static final String SCATTER_CUSTOMER="SC_CUSTOMER";
	public static final String REGULAR_CUSTOMER = "RC_CUSTOMER";
	/**
	 * @author 苏玉军
	 * @date 2014/03/05
	 * @description 增加客户类别，发货类别
	 */
	public static final String SEND_EXPRESS = "EXPRESS";
	public static final String SEND_TRUCKLOAD = "LESS_THAN_TRUCKLOAD";
	
	
	/**
	 * 客户类别 
	 */
	//潜散客
	public static final String PC_SC 	= "PC_SC";
	//会员
	
	
	/**
	 * 定时任务userId
	 */
	//超级管理员
	public static final String TIMERUSERID = "86301";
	//超级管理员
	public static final String SYSTEM_ADMIN = "86301";
	
	/**
	 * 计划状态
	 */
	//未执行
	public static final String NOTEXECUTE="10";
	
	//执行中
	public static final String EXECUTING="20";
	
	//已完成
	public static final String FINISHED="30";
	
	// 已过期
	public static final String OVERDUE="40";
	/**
	 * 开发状态
	 */
	//已指派
	public static final String SCHEDULE_ASSIGNED="10";
	
	//已制定
	public static final String SCHEDULE_FORMULATE="15";
	
	//执行中
	public static final String SCHEDULE_EXECUTING="20";
	
	//已完成
	public static final String SCHEDULE_FINISH="30";
	
	//已过期
	public static final String SCHEDULE_OVERDUE="40";
	
	/**
	 * 营销方式
	 */
	//陌生来电
	public static final String MARKETING_WAY_STRANGER = "STRANGER";
	//已有客户来电
	public static final String MARKETING_WAY_EXISTCUST = "EXISTCUST";
	
	//开发/维护计划查询部门约束个数
    public static final int DEPT_NUM_LIMIT = 800;
    
    /**
     * 合作意向
     */
    //高
    public static final String COOPINTENTION_HIGH = "HIGH";
    //中
    public static final String COOPINTENTION_MIDDLE = "MIDDLE";
    //底
    public static final String COOPINTENTION_LOW = "LOW";
}
