package com.deppon.crm.module.authorization.shared.domain;

public class ExpressPosition {
	/**
	 * 新增 快递点部经理，快递分部高级经理，快递大区总经理 ，快递市场营销组总监 岗位名称
	 * @author suyjun
	 * @Date 2014/01/10
	 * @function 用户数据权限部门加载，快递架构不规则，无法根据事业部所在的城市
	 * 判断架构类型，且组织架构不稳定
	 */
	//快递员
	public static final String EXPRESS_COURIER_POSTION = "express_courier";
	//快递点部经理
	public static final String EXPRESS_POINT_MANAGER_POSITION = "express_point_manager";
	//快递分部高级经理
	public static final String EXPRESS_DIVISIONS_SENIOR_MANAGER_POSITION = "express_divisions_senior_manager";
	//快递大区总经理
	public static final String EXPRESS_REGION_GENERAL_MANAGER = "express_region_general_manager"; 
	//快递市场营销组总监
	public static final String EXPRESS_GROUP_DIRECTOR_MARKETING = "express_group_director_marketing";
}
