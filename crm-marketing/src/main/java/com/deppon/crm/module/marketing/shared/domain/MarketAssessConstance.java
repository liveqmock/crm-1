package com.deppon.crm.module.marketing.shared.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class MarketAssessConstance {
	//无权限
	public static final String AUTHOR_NOAUTHOR = "0";
	/**
	 * 权限部门所属层级
	 * 		经营本部、事业部、大区、营业区、营业部
	 */
	//经营本部
	public static final String AUTHOR_SERCENTER = "5";
	//事业部
	public static final String AUTHOR_BUDEPT = "4";
	//大区
	public static final String AUTHOR_REGION = "3";
	//营业区
	public static final String AUTHOR_BUQUARTER = "2";
	//营业部
	public static final String AUTHOR_SALDEPT = "1";
	/**
	 * 判断层级关系中权限部门的关键字
	 */
	//经营本部
	public static final String AUTHOR_SERCENTER_STR = "经营本部";
	//事业部
	public static final String AUTHOR_BUDEPT_STR = "事业部";
	//大区
	public static final String AUTHOR_REGION_STR = "大区";
	//营业区
	public static final String AUTHOR_BUQUARTER_STR = "营业区";
	//营业部
	public static final String AUTHOR_SALDEPT_STR = "营业部";
	/**
	 * 权限ID
	 */
	//全国
	public static final String AUTHOR_NATION_ID = "400000147";
	//经营本部
	public static final String AUTHOR_SERCENTER_ID = "400000127";
	//事业部
	public static final String AUTHOR_BUDEPT_ID = "400000146";
	//大区
	public static final String AUTHOR_REGION_ID = "400000145";
	//营业区
	public static final String AUTHOR_BUQUARTER_ID = "400000126";
	/**
	 * AuthAllSalCondition中对应权限定义
	 * 		经营本部、事业部、大区、营业区、营业部
	 */
	//经营本部
	public static final String AUTHSAL_AUTHOR_SERCENTER = "5";
	//事业部
	public static final String AUTHSAL_AUTHOR_BUDEPT = "4";
	//大区
	public static final String AUTHSAL_AUTHOR_REGION = "3";
	//营业区
	public static final String AUTHSAL_AUTHOR_BUQUARTER = "2";
	//营业部
	public static final String AUTHSAL_AUTHOR_SALDEPT = "1";
	/**
	 * 是否有对应层级权限
	 */
	//有权
	public static final String DEPT_AUTHOR_Y = "Y";
	//无权
	public static final String DEPT_AUTHOR_N = "N";
	//导出客户开发表excel标题
	public static final Map<String,String> devTitle;
	//导出客户维护表excel标题
	public static final Map<String,String> matTitle;
	static{
		devTitle = new LinkedHashMap<String,String>();
		devTitle.put("营运本部", "managerDeptName");
		devTitle.put("事业部", "orgDeptName");
		devTitle.put("大区", "bigDeptName");
		devTitle.put("小区", "smaDeptName");
		devTitle.put("营业部", "salDeptName");
		devTitle.put("新增潜客数", "newCreateNum");
		devTitle.put("转化为散客数", "turnToScNum");
		devTitle.put("转化为固定客户数", "turnToRcNum");
		devTitle.put("计划回访数", "planVisitNum");
		devTitle.put("实际回访数", "actualVisitNum");
		devTitle.put("计划内回访率", "planVisitRate");
		devTitle.put("当前需求确认阶段客户数", "nowIdentifyNeedsCustNum");
		devTitle.put("需求确认阶段转化客户数", "identifyNeedsOutCustNum");
		devTitle.put("需求确认阶段转化率", "identifyNeedsCustRate");
		devTitle.put("当前意向洽谈阶段客户数", "nowIntentionDiscussCustNum");
		devTitle.put("意向洽谈阶段转化客户数", "intentionDiscussOutCustNum");
		devTitle.put("意向洽谈阶段转化率", "intentionDiscussCustRate");
		devTitle.put("当前持续培养阶段客户数", "nowContinuingTrainingCustNum");
		devTitle.put("持续培养阶段转化客户数", "continuingTrainingOutCustNum");
		devTitle.put("持续培养阶段转化率", "continuingTrainingCustRate");
		devTitle.put("近三个月计划内回访潜客数", "beforePotentialNum");
		devTitle.put("近三个月计划内回访潜客发货数", "nowPotentialDeleviryNum");
		devTitle.put("开发成功率", "potentialDevSuccessRate");
		devTitle.put("开发成功客户零担发货金额", "lttDelCustAmount");
		devTitle.put("开发成功客户快递发货金额", "expDelCustAmount");
		

		matTitle = new LinkedHashMap<String,String>();
		matTitle.put("营运本部", "managerDeptName");
		matTitle.put("事业部", "orgDeptName");
		matTitle.put("大区", "bigDeptName");
		matTitle.put("小区", "smaDeptName");
		matTitle.put("营业部", "salDeptName");
		matTitle.put("上月投诉理赔客户数", "recompenseCustNum");
		matTitle.put("上月零担投诉理赔的客户数", "lttCompCustNum");
		matTitle.put("上月快递投诉理赔的客户数", "expCompCustNum");
		matTitle.put("上月投诉理赔本月回访数", "nowVisitNum");
		matTitle.put("上月零担投诉理赔的客户本月回访数", "lttVisitNum");
		matTitle.put("上月快递投诉理赔的客户本月回访数", "expVisitNum");
		matTitle.put("投诉理赔回访率", "nowVisitRate");
		matTitle.put("零担投诉理赔客户回访率", "lttVisitRate");
		matTitle.put("快递投诉理赔客户回访率", "expVisitRate");
		matTitle.put("上上月发货固定客户数", "memberDeleviryNum");
		matTitle.put("上月流失客户数", "lostCustNum");
		matTitle.put("客户流失率", "lostCustRate");
		matTitle.put("本月回访流失客户", "lostCustVisitNum");
		matTitle.put("流失回访率", "lostVisitRate");
		matTitle.put("挽回客户数", "saveCustNum");
		matTitle.put("客户挽回率", "saveCustRate");
		
	}
}
