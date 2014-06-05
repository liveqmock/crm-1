package com.deppon.crm.module.client.common.util;

import javax.xml.namespace.QName;

import com.deppon.foss.express.SyncExpressDeptRelationResponse;

public class Constant {

	public static final String INTERFACE_DEFAULT_FILE_PATH = "interfaceInvokeConfig.xml";
	public static final String XML_XMLNS = "xmlns";
	//修改了快递优惠信息
	public static final String CONTRACT_MODIFY_EXPRESS="EXPRESS";
	//修改了零担优惠信息
	public static final String CONTRACT_MODIFY_LTT="LTT";
	//修改了快递和零担优惠信息
	public static final String CONTRACT_MODIFY_EL="EL";
	// 合同新签
	public static final String CONTRACT_NEW = "WFS_XZ_HT_009";
	// 合同修改
	public static final String CONTRACT_UPDATE = "WFS_XZ_HT_005";
	// 合同终止
	public static final String CONTRACT_CANCEL = "WFS_XZ_HT_006";
	// 合同绑定
	public static final String CONTRACT_ADD_BELONGDEPT = "WFS_XZ_HT_007";
	// 合同归属
	public static final String CONTRACT_CONVERT_BELONGDEPT = "WFS_XZ_HT_008";
	// 合同新签
	public static final String EX_CONTRACT_NEW = "WFS_XZ_HT_014";
	// 合同修改
	public static final String EX_CONTRACT_UPDATE = "WFS_XZ_HT_013";
	// 合同终止
	public static final String EX_CONTRACT_CANCEL = "WFS_XZ_HT_012";
	// 合同绑定
	public static final String EX_CONTRACT_ADD_BELONGDEPT = "WFS_XZ_HT_011";
	// 合同归属
	public static final String EX_CONTRACT_CONVERT_BELONGDEPT = "WFS_XZ_HT_010";
	// 合同新签
	public static final String CONTRACT_NEW_CONTENT = "合同新签";
	// 合同修改
	public static final String CONTRACT_UPDATE_CONTENT = "合同修改";
	// 合同终止
	public static final String CONTRACT_CANCEL_CONTENT = "合同终止";
	// 合同绑定
	public static final String CONTRACT_ADD_BELONGDEPT_CONTENT = "合同绑定";
	// 合同归属
	public static final String CONTRACT_CONVERT_BELONGDEPT_CONTENT = "合同归属变更";

	// 服务补救工作流申请
	public static final String SERVICE_RECOVERY = "WFS_CW_002";
	// 退运费工作流申请
	public static final String BACK_FREIGHT = "WFS_CW_001";
	//查询部门坐标
	public static final String ESB_GIS_DPETCOORDINATES="ESB_CRM2ESB_QUERY_DPETCOORDINATES";

	public static final String RECOMPENSE_NORMAL = "WFS_LP_001";
	public static final String RECOMPENSE_MUCH = "WFS_LP_002";
	public static final String SYSTEM_NAME = "CRM";

	public static final String SMS_NAME = "crmsms";

	public static final String ESB2ERP_POINTMENTCAR = "CRM2ESB_ADDRECGOODSBILL";
	public static final String ESB2EBM_ORDERSTATUS = "CRM2ESB_UPDATEORDERSTATUS";
	public static final String ESB2OA_WORKFLOWAPPLY = "CRM2ESB_WORKFLOWAPPLY";

	public static final String ESB2ERP_CUSTOMERDATASYNC = "CRM2ESB_CUSTOMERDATASYNC";
	// --短信--
	// 业务类型编码
	// 理赔管理
	public static final String SMS_RECOMPENSE_CODE = "YWLX20121105094635";
	// 工单管理
	public static final String SMS_COMPlAINT_CODE = "YWLX20121105095042";
	// 优惠券管理
	public static final String SMS_COUPON_CODE = "YWLX20121105095210";
	// 订单管理
	public static final String SMS_ORDER_CODE = "YWLX20121105095127";
	// 联系人编码绑定/解绑
	public static final String SMS_CONTACT_CODE = "YWLX20121105095319";
	//营销管理
	public static final String SMS_MARKETING_CODE = "YWLX20131008110636";

	/****ESB服务编码***/
	// FOSS运单查询的CRM
	public static final String WAYBILL_DETAIL = "ESB_COMMON2ESB_FOSS_QUERY_WAYBILL_DETAIL";
	// 生成理赔应付单同步接口
	public static final String CLAIMSAPBILL = "ESB_CRM2ESB_GENERATE_CLAIMSAPBILL";
	// 同步客户信息接口
	public static final String CUSTOMERINFOETC = "ESB_CRM2ESB_RECIEVE_CUSTOMERINFOETC";
	// 同步临欠散客信息接口
	public static final String NONFIXEDCUSTOMER = "ESB_CRM2ESB_RECIEVE_NONFIXEDCUSTOMER";
	// 同步客户数据到网点规划系统
	public static final String SYNC_CUSTMOTER_TO_WDGH = "ESB_CRM2ESB_SYNC_CUSTINFO";
	// 客户发货到货金额查询
	public static final String QUERYMONEY = "ESB_CRM2ESB_QUERYMONEY";
	// 接收约车订单接口
	public static final String VEHICLE = "ESB_CRM2ESB_VEHICLE";
	// FOSS订单撤销
	public static final String FOSS_CANCEL_ORDER ="ESB_CRM2ESB_CANCEL_ORDER";
	// 查询未签收运单
	public static final String UNSIGNED = "ESB_CRM2ESB_UNSIGNED";
	// 接收订单-运单关联
	public static final String BINDORDER = "ESB_CRM2ESB_BINDORDER";
	// 通知理赔支付状态
	public static final String NOTIFY_CLAIMS_STATE = "CRM_ESB2CRM_NOTIFY_CLAIMS_STATE";
	// 查询订单接口
	public static final String QUERYORDER = "CRM_ESB2CRM_QUERYORDER";
	// 推送订单锁屏信息
	public static final String SYNC_ORDER_LOCK_INFO ="ESB_CRM2ESB_SYNC_ORDER_LOCK_INFO";
	//订单下单失败状态返回
	public static final String ORDER_STATUE_COUPON_BACK = "ESB_CRM2ESB_GAIN_COUPON_BACK";
	// 查询订单锁屏信息
	public static final String ORDER_LOCK_INFO = "CRM_ESB2CRM_MODIFY_ORDER_LOCK_INFO";

	// 导入订单开单接口
	public static final String IMPORT = "CRM_ESB2CRM_IMPORT";
	// CRM订单状态修改接口
	public static final String MODIFYORDER = "CRM_ESB2CRM_MODIFYORDER";
	// 开单权限同步接口
	public static final String ORDER_RIGHT = "CRM_ESB2CRM_ORDER_RIGHT";
	// 网点同步接口
	public static final String RECEIVE_SITE = "CRM_ESB2CRM_RECEIVE_SITE";
	// 月结合同欠款同步接口
	public static final String RECEIVE_CREDITUSED = "CRM_ESB2CRM_RECEIVE_CREDITUSED";
	// 劳务费状态校验
	public static final String SVCCHARGE_STATUS_UPDATE = "ESB_CRM2ESB_SVCCHARGE_STATUS_UPDATE";
	//查询客户作废
	public static final String CRM2ESB_ISCUSTOMERBLANKOUT = "ESB_CRM2ESB_ISCUSTOMERBLANKOUT";
	// FOSS营业部组织信息同步
	public static final String SYNC_ORGANIZAITION="CRM_ESB2CRM_SYNC_ORGANIZATION";
	// FOSS营业部信息同步
	public static final String SYNC_SALES_DEPARTMENT="CRM_ESB2CRM_SYNC_SALES_DEPARTMENT";
	//FOSS试点城市、落地配城市同步接口
	//public static final String SYNC_EXPRESS_CITY="ESB_FOSS2ESB_SYNC_EXPRESS_CITY";
	public static final String SYNC_EXPRESS_CITY="CRM_ESB2CRM_SYNC_EXPRESS_CITY";
	//FOSS营业部-快递点部映射关系同步接口
	public static final String SYNC_EXPRESS_DEPT= "CRM_ESB2CRM_EXPRESS_DEPT";
	// FOSS行政区域信息同步
	public static final String SYNC_DISTRICT="CRM_ESB2CRM_SYNC_DISTRICT";
	// FOSS车队信息同步
	public static final String SYNC_MOTORCADEINFO="CRM_ESB2CRM_SYNC_MOTORCADEINFO";
	// 银企银行支行基础信息同步
	public static final String RECEIVE_BANK = "CRM_ESB2CRM_RECEIVE_BANK";
	// 银企省份城市基础信息同步
	public static final String RECEIVE_PROVINCECITY = "CRM_ESB2CRM_RECEIVE_PROVINCECITY";
	//UUMS职位信息同步
	public static final String POSITION_TO_CRM = "CRM_ESB2CRM_SEND_POSITION_CRM";
	//UUMS职员信息同步
	public static final String EMPLOYEE_TO_CRM = "CRM_ESB2CRM_SEND_EMPLOYEE_CRM";
	//UUMS用户信息同步
	public static final String USERINFO_TO_CRM = "CRM_ESB2CRM_SEND_USERINFO_CRM";
	//UUMS行政组织信息同步
	public static final String ADMINORG_TO_CRM = "CRM_ESB2CRM_SEND_ADMINORG_CRM";
    /***********************CRM二期CC接口服务编码**************************/
	//客户信息创建
    public static final String CUSTINFO_TO_CRM="CRM_ESB2CRM_CREATE_CUSTOMERINFO";
    //客户信息修改
    public static final String CUSTINFOUPDATE_TO_CRM="CRM_ESB2CRM_UPDATE_CUSTOMERINFO";
    //客户营销信息添加
    public static final String CUSTMARKET_TO_CRM="CRM_ESB2CRM_SEND_MARKETINGINFO";
    //客户营销信息查询
    public static final String CUSTMARKET_QUERY="CRM_ESB2CRM_QUERY_MARKETINGINFO";
    //客户营销详细信息查询
    public static final String CUSTMARKETDETAIL_QUERY="CRM_ESB2CRM_QUERY_MARKETINGDETAILINFO";
    //推送营销任务
    public static final String CUSTMARKETTASK_TO_CRM="CRM_ESB2CRM_SEND_RECALLPLAN";
    //商机信息判断
    public static final String BUSINESSOPPORTUNITY_VALIDATE_IFEXIST="CRM_ESB2CRM_VALIDATE_BUSINESSOPPORTUNITY";
    /***********************CRM二期CC接口服务编码**************************/

    /***********************CRM二期FOSS接口服务编码**************************/
    //客户运单查询
    public static final String CUST_WAYBILL="ESB_CRM2ESB_QUERY_ACCT_INFO";
    //市场推广活动推送
    public static final String MARKETING_ACTIVITY="ESB_CRM2ESB_SEND_MARKETPROMOTION";
    //FOSS散客创建
    public static final String SCATTER_CREATE="CRM_ESB2CRM_RECIEVE_NONFIXEDCUSTOMER";
    /***********************CRM二期FOSS接口服务编码**************************/
    /***********************CRM二期官网接口服务编码**************************/
    public static final String CUST_OWS_CREATE="CRM_ESB2CRM_SEND_CUSTOMER";
    /***********************CRM二期官网接口服务编码**************************/
	// 服务消费端发送请求消息前（Sent）
	public static final String STATUS_CLIENT_SENT = "ST_108";
	// 服务提供端接收到请求消息后
	public static final String STATUS_SERVER_RECEIVED = "ST_302";
	// 服务提供端开始处理请求消息时
	public static final String STATUS_SERVER_PROCESS = "ST_305";
	// 服务提供端发送响应消息前
	public static final String STATUS_SERVER_SENT = "ST_308";
	// 服务消费端接收到响应消息后
	public static final String STATUS_CLIENT_RECEIVED = "ST_502";
	// 完成处理后，标志整个调用完成
	public static final String STATUS_COMPLETED = "ST_999";

	// 客户数据同步FOSS CODE
	public static final int FOSS_CODE = 1;
	public static final int CC_CODE = 2;
	public static final int WDGH_CODE = 4;
	public static final String FOSS_CODE_DESC = "FOSS";
	public static final String CC_CODE_DESC = "CC";
	public static final String WDGH_CODE_DESC = "WDGH";

	//UUMS
	public static final int INSERT = 1;//新增
	public static final int UPDATE = 2;//修改
	public static final int DELETE = 3;//删除
	public static final int SUCCESS = 1;//成功标志
	public static final String UUMS_EXCEPTION = "同步失败，未捕获到异常信息，请尽快联系相关开发员协助处理！";
	//官网客户同步给CRM
	public final static QName _CreateOwsCustomerResponse_QNAME = new QName(
			"http://www.deppon.com/crm/remote/ows/domain/entity",
			"createOwsCustomerResponse");
	// FOSS散客同步给CRM
	public final static QName _CreateScatterResponse_QNAME = new QName(
			"http://www.deppon.com/esb/inteface/domain/crm",
			"createScatterResponse");
	// 同步推广活动
	public final static QName _MarketingActivityRequest_QNAME = new QName(
			"http://www.deppon.com/esb/inteface/domain/crm",
			"marketingActivityRequest");
	// 散客同步QName;
	public final static QName _OrigCustSyncRequest_QNAME = new QName(
			"http://www.deppon.com/esb/inteface/domain/crm",
			"OrigCustSyncRequest");
	// 应付单申请QName;
	public final static QName _ClaimsPayBill_QNAME = new QName(
			"http://www.deppon.com/esb/inteface/domain/crm",
			"claimsPayBillGenerateRequest");
	// 订单状态更新响应QName
	public final static QName _UpdateOrderResponse_QNAME = new QName(
			"http://www.deppon.com/crm/inteface/foss/domain",
			"updateOrderResponse");
	// 合同月结天数响应QName
	public final static QName _ReceiveCreditUsedResponse_QNAME = new QName(
			"http://www.deppon.com/crm/inteface/foss/domain",
			"ReceiveCreditUsedResponse");
	// 部门组织信息相应QName
	public final static QName _SyncOrganizationResponse_QNAME = new QName(
			"http://www.deppon.com/crm/inteface/foss/domain",
			"SyncOrganizationResponse");
	// 试点城市、落地配城市相应QName
	public final static QName _SyncExpressCityResponse_QNAME = new QName(
				"http://www.deppon.com/ows/inteface/domain/",
				"SyncExpressCityResponse");
	//FOSS营业部-快递点部映射关系对应QName
	public final static QName _SyncExpressDeptResponse_QNAME=new QName(
			"http://www.deppon.com/ows/inteface/domain/",
			"SyncExpressDeptRelationResponse");
	// 部门营业部信息对应QName
	public final static QName _SyncSalesDepartmentResponse_QNAME = new QName(
			"http://www.deppon.com/crm/inteface/foss/domain",
			"SyncSalesDepartmentResponse");
	// 行政组织信息对应QName
	public final static QName _SyncDistrictResponse_QNAME = new QName(
			"http://www.deppon.com/crm/inteface/foss/domain",
			"SyncDistrictResponse");
	// 异常消息QName
	public final static QName _CommException_QNAME = new QName(
			"http://www.deppon.com/esb/exception",
			"commonExceptionInfo");
	// 银企同步银行支行QName
	public final static QName _BankInfoNotificationResponse_QNAME = new QName(
			"http://www.deppon.com/esb/inteface/domain/payment",
			"bankInfoNotificationResponse");
	// 银企同步省份城市QName
	public final static QName _ProvinceCityInfoNotificationRequest_QNAME = new QName(
			"http://www.deppon.com/esb/inteface/domain/payment",
			"provinceCityInfoNotificationRequest");
	//uums同步职位信息QName
	public final static QName _SendPositionResponse_QNAME = new QName(
			"http://www.deppon.com/uums/inteface/domain/usermanagement", "SendPositionResponse");
	//uums同步职员信息QName
		public final static QName _SendEmployeeResponse_QNAME = new QName(
			"http://www.deppon.com/uums/inteface/domain/usermanagement", "SendEmployeeResponse");
	//uums行政组织信息QName
	public final static QName _SendAdminOrgResponse_QNAME = new QName(
			"http://www.deppon.com/uums/inteface/domain/usermanagement", "SendAdminOrgResponse");
	//uums同步用户信息QName
	public final static QName _SendUserInfoDeptAllResponse_QNAME = new QName(
			"http://www.deppon.com/uums/inteface/domain/usermanagement", "SendUserInfoDeptAllResponse");
}
