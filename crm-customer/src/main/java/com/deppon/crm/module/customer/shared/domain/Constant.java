package com.deppon.crm.module.customer.shared.domain;

import java.util.HashMap;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.ExpressPosition;

public class Constant {

	/**
	 *	快递市场营销组标杆编码
	 */
	public static final String EXPRESS_MARKETING_GROUP_STANDARDCODE ="DP11921";
	//快递岗位集合
	public static final Map<String,String> EXPRESS_POSITIONS = new HashMap<String, String>();
	static{
		EXPRESS_POSITIONS.put("点部经理", ExpressPosition.EXPRESS_POINT_MANAGER_POSITION);
		EXPRESS_POSITIONS.put("分部高级经理", ExpressPosition.EXPRESS_DIVISIONS_SENIOR_MANAGER_POSITION);
		EXPRESS_POSITIONS.put("快递大区总经理", ExpressPosition.EXPRESS_REGION_GENERAL_MANAGER);
		EXPRESS_POSITIONS.put("快递市场营销组总监", ExpressPosition.EXPRESS_GROUP_DIRECTOR_MARKETING);
	}
//*********************************潜散客常量***********************

	//潜客升级
	public static final String PotentialCustomer_Upgrade = "潜客->散客";
	//潜散客升级会员
	public static final String Customer_Upgrade_Member = "潜散客->会员";
	//升级会员
	public static final String Upgrade_Member = "升级会员";
	
	//潜客失效
	public static final int PotentialCustomer_Cancel = 1;
	
	//查询日期范围
	public static final int PERIOD= 12 ;
	//客户来源为揽货失败
	public static final String ORDER_CUSTOMER="ORDER_CUSTOMER";
	//客户来源为陌生来电
	public static final String CUST_SOURCE_STRANGER = "STRANGER_CALLS";
	//潜客类型
	public static final String CRM_POTENTIAL = "PC_CUSTOMER";
	
	
//**********************************会员常量****************************************	
	
	//个人会员
	public static final String Person_Member = "PERSONAL";
	//企业会员
	public static final String Enterprise_Member ="ENTERPRISE";
	//重要联系人
	public static final boolean MainLinkMan = true;
	
	public static final boolean NormalLinkMan = false;
	
	//特殊会员
	public static final int Special_Member = 0;
	//审批状态
	public static final String Status_Examine = "1";
	//正常状态
	public static final String Status_Normal = "0";
	//失效状态
	public static final String Status_NoEfficacy = "2";
	
	//普通会员标准
	public static final double Normol_Money = 600;
	//黄金会员标准
	public static final double Gold_Money = 3000;
	//铂金会员标准
	public static final double Platinum_Money = 10000;
	//钻石会员标准
	public static final double Diamond_Money = 40000;
	//不能升级会员
	public static final String CustLevel_Fail ="Fail";
	//已降级会员
	public static final String CustLevel_Demotion = "Demotion";
	//普通会员
	public static final String CustLevel_Normol = "NORMAL";
	//普通会员级别
	public static final int CustLevel_Normol_Level = 1;
	//黄金会员
	public static final String CustLevel_Gold = "GOLD";
	//黄金会员级别
	public static final int CustLevel_Gold_Level = 2;
	//铂金会员
	public static final String CustLevel_Platinum  = "PLATINUM";
	//铂金会员级别
	public static final int CustLevel_Platinum_Level = 3;
	//砖石会员
	public static final String CustLevel_Diamond = "DIAMOND";
	//砖石会员级别
	public static final int CustLevel_Diamond_Level = 4;
	//出发客户
	public static final String LeaveCustomer = "LEAVE_CUSTOMER";
	//到达客户
	public static final String ArriveCustomer = "ARRIVE_CUSTOMER";
	//发货到底散客
	public static final String LEAVE_ARRIVE_CUSTOMER = "LEAVE_ARRIVE_CUSTOMER"; 
	//出发客户的系数
	public static final double LeaveCustomerRatio = 1;
	//到达客户的系数
	public static final double ArriveCustomerRatio = 2.0/3;
	//失效
	public static final int LoseEffect = 1;
	//生效
	public static final int TakeEffect = 0;
	//存于custLabel中的固定客户类型
	public static final String MEMBERTYPE_IN_CUSTLABEL = "MEMBER";
	
	
//**********************************接送货地址类型*******************************************************	
	//接货地址
	public static final String RECEIVE_GOODS = "RECEIVE_GOODS";
	public static final String RECEIVE_SEND_GOODS = "RECEIVE_SEND_GOODS";
	public static final String SEND_GOODS = "SEND_GOODS";
	
//**********************************工作流使用的常量*******************************************************	
	//工作流查询-我的工作流
	public static final String SerachButton_APPLIED ="APPLIED";
	//工作流查询-我的已处理的工作流
	public static final String SerachButton_APPROVED ="APPROVED";
	//月发月送增值折扣-工作流名称
	public static final String ContractAddWK_Name ="contractAdd";
	//月发月送增值折扣-启动工作流action
	public static final int ContractAddWK_StartAction =0;
	//月发月送增值折扣同意
	public static final int ContractAddWK_Pass = 10;
	//月发月送增值折扣不同意
	public static final int ContractAddWK_NoPass = 14;
	//联系人变更审批同意
	public static final int ContactVaryWK_Pass = 10;
	//联系人变更审批拒绝
	public static final int ContactVaryWK_NoPass = 14;
	//联系人工作流启动action id
	public static final int ContactVaryWK_StartAction = 0;
	//联系人变更工作流
	public static final String ContactVaryWorkFlowName = "contactVary";
	
	public static final String ModifyMemberWorkflowName = "memberModify";
	//表示当前角色是任意部门的都可以
	public static final String AnyDept = "anyDept";
	//会员基础数据审批节点
	public static final int BaseDataStep = 2;
	//会员账号数据审批节点
	public static final int AccountDataStep = 1;
	//会员维护工作流开始action
	public static final int MemberModifyWK_StartAction = 0;
	//会员维护工作流账号修改通过
	public static final int MemberModifyWK_AccountPass = 10;
	//会员维护工作流账号修改拒绝
	public static final int MemberModifyWK_AccountNoPass = 14;
	//会员维护工作流基本数据修改通过
	public static final int MemberModifyWK_BasePass = 8;
	//会员维护工作流基本数据修改拒绝
	public static final int MemberModifyWK_BaseNoPass = 12;
	//特殊会员创建审批通过
	public static final int CreateSepMemberWK_Pass = 10;
	//特殊会员创建审批拒绝
	public static final int CreateSepMemberWK_NoPass = 14;
	//会员修改基础资料类型
	public static final String BaseDateType = "baseDateType";
	//会员修改账号资料类型
	public static final String AccountDateType = "accountDateType";
	//特殊会员工作流启动action id
	public static final int SepCreateWK_StartAction = 0;
	//特殊会员工作流名称
	public static final String SepCreateMemberWorkflowName = "createSpecialMember";
	//营业部经理
	public static final String BizManager = "1004";
	//市场营销专员
	public static final String CrDept = "1002";
	//客户管理员
	public static final String Marketing = "1003";
	//客户管理经理
	public static final String MarketingManager = "1008";
	//大区总
	public static final String Region = "1005";
	//小区总
	public static final String Neighborhood = "1006";
	//事业部总裁
	public static final String Cause = "1007";
	//CRM推进小组id
	public static final String MarketingId ="464233";
	//客户管理组部门id
	public static final String CustomerManage ="180773";
	
	//**************工作流状态
	//未审批
	public static final String UNAPPROVED = "UNAPPROVED";
	//审批中
	public static final String APPROVING = "APPROVING";
	//已同意
	public static final String AGREED = "AGREED";
	//未同意
	public static final String DISAGREE = "DISAGREE";
	//********************************积分常量***********************
	//渠道规则
	public static final String ChannelRule = "渠道规则";
	//产品规则
	public static final String ProductRule = "产品规则";
	//线路规则
	public static final String WiringRule = "线路规则";
	//积分规则
	public static final String RewardIntegRule ="RewardIntegRule";
	
	//积分类型
	//调整积分
	public static final String AdjustIntegral = "AdjustIntegral";
	//手动奖励积分
	public static final String HandRewardIntegral = "HandRewardIntegral";
	//兑换积分
	public static final String IntegralConvertGift = "IntegralConvertGift";
	//****************************数据状态*************************
	//失效
	public static final String Lose = "2";
	//生效
	public static final String Effective = "1";
	//*******************散客客户状态
	//审批中
	public static final String SCATTERACCOUNT_INPROCEE = "1";
	//正常：0
	public static final String SCATTERACCOUNT_EFFECTIVE = "0";
	//无效 ：2
	public static final String SCATTERACCOUNT_LOSE = "2";
	
	//*******************散客账户是否是默认账户 1为是，0为否
	public static final String SCATTERACCOUNT_DEFAULT = "1";
	public static final String SCATTERACCOUNT_NOT_DEFAULT = "0";
	
	//***************************附件***********************
	public static final String ContactVaryFileInfo = "ContactVaryFileInfo";
	//***************************奖励类型
	public static final String RULETYPE_SCORE = "SCORE";
	public static final String RULETYPE_RATIO = "RATIO";
	//***************************发送状态
	//未审批
	public static final String INTEG_CHANGE_NOAPPROVE = "NOAPPROVE";
	// 积分兑换状态--审批中
	public static final String INTEG_CHANGE_APPROVALING = "APPROVALING";
	// 积分兑换状态--审批通过
	public static final String INTEG_CHANGE_PASS = "PASS";
	// 积分兑换状态--审批拒绝
	public static final String INTEG_CHANGE_REJECT = "REJECT";
	// 积分兑换状态--已发送
	public static final String INTEG_CHANGE_TRANSMITTED = "TRANSMITTED";
	// 文件类型---合同
	public static final String FileSourceType_Contract = "CONTRACT";
	// 文件类型---合同历史操作记录
	public static final String FileSourceType_ContractOperatorLog = "CONTRACTOPERATORLOG";
	
	//***************************商机状态
	//定位目标
	public static final String FIXED_POSITION = "FIXED_POSITION";
	//接触客户
	public static final String CONTACT_CUSTOMER = "CONTACT_CUSTOMER";
	//开始发货
	public static final String START_SHIPMENT = "START_SHIPMENT";
	//升级会员
	public static final String PREMIUM_MEMBER = "PREMIUM_MEMBER";
	
	//**************************联系人类型
	//联系人类型为空的值
	public static final String defaultLinkManType ="0,0,0,0,0"; 
	//财务联系人
	public static final int ContactType_Finance =0;
	//业务联系人
	public static final int ContactType_Business = 1;
	//发货联系人
	public static final int ContactType_Shipments = 2;
	//收货联系人
	public static final int ContactType_Delivery = 3;
	//协议联系人
	public static final int ContactType_Treaty = 4;
	
	public static final String contactType_True = "1";
	
	public static final String contactType_False = "0";
	//会员变更联系人工作流名称
	public static final String ChangeMemberDeptWorkFlowName = "changeMemberDept";
	//会员变更联系人工作流----启动actionId
	public static final int ChangeMemberDeptWK_StartAction = 0;
	//审批工作流成功
	public static final int ChangeMemberDeptWK_ExamineSuccess = 10;
	//审批工作流失败
	public static final int ChangeMemberDeptWK_ExamineFail = 14;
	//超级管理员的userId
	public static final String SuperManUserId = "86301";
	//超级管理员的userId
	public static final String SuperManUserName = "系统管理员";

	
	//添加散客账户信息工作流名称
	public static final String AddScatterCustAccountWorkFlowName = "satterCustAccount";
	//添加散客账户信息工作流----启动actionId
	public static final int AddScatterCustAccountWK_StartAction = 0;
	/*
	 * 合同状态
	 */
	//审批中
	public static final String CONTRACT_STATUS_INPROCESS="0";
	public static final String CONTRACT_STATUS_INPROCESS_CN="审批中";
	//有效
	public static final String CONTRACT_STATUS_EFFECT="1";
	public static final String CONTRACT_STATUS_EFFECT_CN="有效";
	//无效
	public static final String CONTRACT_STATUS_UNEFFECT="2";
	public static final String CONTRACT_STATUS_UNEFFECT_CN="无效";
	//无效
	public static final String CONTRACT_STATUS_WAIT_EFFECT="3";
	public static final String CONTRACT_STATUS_WAIT_EFFECT_CN="待生效";
	//不同意
	public static final String CONTRACT_STATUS_DISAGREE="4";
	public static final String CONTRACT_STATUS_DISAGREE_CN="未同意";
	/*
	 * 付款方式
	 */
	//解款方式
	public static final String CONTRACT_PAYWAY_NOT_MONTH_END="NOT_MONTH_END";
	public static final String CONTRACT_PAYWAY_NOT_MONTH="MONTH_END";
	public static final String CONTRACT_PAYWAY_HALF_MONTH="HALF_MONTH_END";
	/*
	 * 优惠方式
	 */
	//优惠方式
	public static final String CONTRACT_PREFERENTIALTYPE_MONTH_SEND="MONTH_SEND";
	public static final String CONTRACT_PREFERENTIALTYPE_MONTH_REBATE="MONTH_REBATE";
	public static final String CONTRACT_PREFERENTIALTYPE_PRICE_REBATE="PRICE_REBATE";
	public static final String CONTRACT_PREFERENTIALTYPE_NOT_PREFERENTIAL="NOT_PREFERENTIAL";
	
	/**
	 *合同类型常量
	 */
	public static final String CONTRACT_TYPE_LINGDAN="LTT";
	public static final String CONTRACT_TYPE_EXPRESS="EXPRESS";
	public static final String CONTRACT_TYPE_EXANDLD="EL";
	
	/**
	 * 订单来源（绑定联系人用）
	 */
	public static final String ORDER_SOURCE_ONLINE = "ONLINE";
	public static final String ORDER_SOURCE_TAOBAO = "TAOBAO";
	public static final String ORDER_SOURCE_ALIBABA = "ALIBABA";
	public static final String ORDER_SOURCE_YOUSHANG = "YOUSHANG";
	public static final String ORDER_SOURCE_SHANGCHENG = "SHANGCHENG";
	
	/**
	 * 付款方式
	 */
	public static final String ORDER_PAYMENT_PAY_ONLINE="PAY_ONLINE";
	public static final String ORDER_PAYMENT_PAY_ONLINE_INTEG="10";
	public static final String ORDER_PAYMENT_CASH="CASH";
	public static final String ORDER_PAYMENT_CASH_INTEG="0";
	public static final String ORDER_PAYMENT_MONTH_PAY="MONTH_PAY";
	public static final String ORDER_PAYMENT_MONTH_PAY_INTEG="2";
	public static final String ORDER_PAYMENT_PAY_ARIIVE="PAY_ARIIVE";
	public static final String ORDER_PAYMENT_PAY_ARIIVE_INTEG="1";
	
	/**
	 * 合同工作流状态
	 */
	public static final String CONTRACT_APPROVING = "1";
	public static final String CONTRACT_AGREED = "2";
	public static final String CONTRACT_DISAGREE = "3";
	
	/**
	 * 合同发货金额
	 */
	public static final String deliverMoney = "DELIVERMONEY";
	public static final String year = "YEAR";
	public static final String month = "MONTH";
	/**
	 * 合同修改类型
	 */
	//修改零担折扣
	public static final String CONTRACT_MODIFY_TYPE_LTT_DISCOUNT = "ltt_discount";
	//修改快递折扣
	public static final String CONTRACT_MODIFY_TYPE_EXPRESS_DISCOUNT = "express_discount";
	//修改快递、零担折扣
	public static final String CONTRACT_MODIFY_TYPE_LE_DISCOUNT = "le_discount";
	//修改零担折扣、结算限额
	public static final String CONTRACT_MODIFY_TYPE_LTT_OTHER = "ltt_other";
	//修改快递折扣、结算限额
	public static final String CONTRACT_MODIFY_TYPE_EXPRESS_OTHER = "express_other";
//	public static final String CONTRACT_MODIFY_TYPE_DISCOUNT="discount";
	//修改结算限额
	public static final String CONTRACT_MODIFY_TYPE_BUDGET="budget";
	//修改了快递、零担折扣，修改结算限额
	public static final String CONTRACT_MODIFY_TYPE_OTHER="other";
	
	/**
	 * 散客类型
	 */
	//普通散客
	public static final String ORDINARY_SCATTER="ORDINARY_SCATTER";
	//临欠散客
	public static final String FOSS_SCATTER="FOSS_SCATTER";
	public static final String CRM_SCATTER="SC_CUSTOMER";
	
	/**
	 * 散客的默认版本号
	 */
	public static final String SCATTERCUST_VERSION = "1";
	/**
	 * ApproveData操作类型
	 */
	//新增
	public static final Integer APPROVEDATA_INSERT = 1;
	//修改
	public static final Integer APPROVEDATA_UPDATE = 2;
	//删除
	public static final Integer APPROVEDATA_DELETE = 3;
	//绑定
	public static final Integer APPROVEDATA_BIND = 4;
	//归属部门变跟
	public static final Integer APPROVEDATA_CHANGECONTRACTDEPT = 5;
	
	/**
	 * 修改类型
	 */
	public static final String MODIFYTYPE_PHONE="1";
	public static final String MODIFYTYPE_MOBILE="2";
	public static final String MODIFYTYPE_BOTH="3";
	public static final String MODIFYTYPE_NONE="0";
	
	/**
	 * 合同欠款剩余天数为1
	 */
	public static final int CONTRACTDEBTDAY_FOR_ONE = 1;
	//系统管理员
	public static final String SYSTEM_ID = "86301";
	//合同月结天数
	public static final String CONTRACTDEBT_DAY = "合同月结天数";
	//合同可使用额度 --0d
	public static final double USERAMOUNT_DEFAULT = 0d;
	/**
	 * 标识符--客户管理小组相当于全公司权限
	 */
	public static final String CUSTOMER_ALLAUTHORIZE_DEPARTMENT = "CUSTOMER_ALLAUTHORIZE_DEPARTMENT";
	
	/**
	 *香港登记证号码的长度 
	 */
	public static final int registration_defaultLength= 11;
	
	//是香港的标识
	public static final String ISHONGKONG_STRING = "1";
	//大陆的标识
	public static final String ISMAINLAND_STRING = "0";
	//应收账款管理组
	public static final String DEBT_STARDCODE = "DP00114";
	//德邦公司---用于合同
	public static final String DEPPON_COMPANY = "德邦物流股份有限公司";
	//合同绑定
	public static final String BOUNDING_CONTRACT = "bingding";
	//合同归属变更
	public static final String CHANGEBELONGDEPT_CONTRACT = "changeBelongDept";
	 //400订单管理小组 标杆编码
	public static final String ORDER_MANAGER_DEPARTMENT_STANDARDCODE = "DP00059";
	//生效
	public static final String EFFECT = "1";
	//有效
	public static final String UNEFFECT = "0";
	public static final String BOUNDING_CONTACT = "1";
	public static final String UNBOUNDING_CONTACT = "0";
	public static final String DEPPON = "DP00003";
	public static final String INVOICE_TYPE_01="INVOICE_TYPE_01";
	public static final String INVOICE_TYPE_02="INVOICE_TYPE_02";
	//来源渠道
	//官网
	public static final String CUST_SOURCE_OWS = "ONLINEHALL";
	//CC
	public static final String CUST_SOURCE_CC = "CALLCENTER";
	//营业厅
	public static final String CUST_SOURCE_Hall = "HALL";
	//到达散客 
	public static final String ARRIVE_SCATTER = "ARRIVE_CUSTOMER";
	//联系人状态
	//有效
	public static final String CONTACT_STATUS_EFFECT = "0";
	//审批中
	public static final String CONTACT_STATUS_WORKFLOW = "1";
	//无效
	public static final String CONTACT_STATUS_UNEFFECT = "2";
	//匿名联系人
	public static final String CONTACT_NONAME = "匿名联系人";
	//地址状态
	//有效
	public static final String ADDRESS_STATUS_EFFECT = "0";
	//审批中
	public static final String ADDRESS_STATUS_WORKFLOW = "1";
	//无效
	public static final String ADDRESS_STATUS_UNEFFECT = "2";
	
	/**
	 *客户业务类别
	 */
	public static final String CUST_BUSTYPE_LINGDAN="LTT";
	public static final String CUST_BUSTYPE_EXPRESS="EXPRESS";
	public static final String CUST_BUSTYPE_EXANDLD="EL";
	//客户状态
	//有效
	public static final String Cust_STATUS_EFFECT = "0";
	//审批中
	public static final String Cust_STATUS_WORKFLOW = "1";
	//无效
	public static final String Cust_STATUS_UNEFFECT = "2";
	
	//客户操作日志 操作类型
	public static final String OPERATOR_TYPE_INSERT = "INSERT";
	public static final String OPERATOR_TYPE_UPDATE = "UPDATE";
	public static final String OPERATOR_TYPE_DELETE = "DELETE";
	//电销管理小组 部门标杆编码
	public static final String CALL_SALE_STANDCODE = "DP09787";
	//电销管理小组 部门标杆编码
	public static final String CALL_SALE_ID = "408217";
	
	//固定客户
	public static final String  CRM_MEMBER= "RC_CUSTOMER";
	
	//潜客PC_CUSTOMER 散客SC_CUSTOMER 固定客户RC_CUSTOMER ';
	/*客户属性*/
	public static final String  CUST_GROUP_PC_CUSTOMER="PC_CUSTOMER";
	public static final String  CUST_GROUP_SC_CUSTOMER="SC_CUSTOMER";
	public static final String  CUST_GROUP_RC_CUSTOMER="RC_CUSTOMER";
	
	public static final String  CUST_SEX_NAM="MAN";
	public static final String  CUST_CARDTYPECON_OFFICER="OFFICER_IDCARD";
	public static final String HAS_ASSOCITED = "HAS_ASSOCITED";
	public static final String ALL_STATUS = "ALL_STATUS";
	public static final String NOT_ASSOCITED = "NOT_ASSOCITED";
	public static final String CUST_OPERATETYPE_SCATTERUPGRADE = "SCATTERUPGRADE";

	//客户开发阶段常量
	public static final String IDENTIFY_NEEDS = "IDENTIFY_NEEDS";
	public static final String INTENTION_DISCUSS = "INTENTION_DISCUSS";
	public static final String CONTINUING_TRAINING = "CONTINUING_TRAINING";
	public static final String BEGAN_SHIPPING = "BEGAN_SHIPPING";
	
	public static final String HIGN = "HIGH";
	public static final String OPERATOR_TYPE_POTOSC = "POTENTOSCATTER";
	
	/**			
	    * 修改人：唐亮
		*修改时间：2014-5-20 11:40
		*原有内容：无（新增）
		*修改原因：商机阶段
	 */
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
}
