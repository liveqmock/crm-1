/**
 * 下拉框Model
 */
Ext.define('ComboModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		// 下拉框中传到后台中的值
		name : 'code',
		type : 'string'
	}, {
		// 下拉框显示的值
		name : 'codeDesc',
		type : 'string'
	}]
});

//处理结果 model
Ext.define('DutyProcessResultDetailModel',{
	extend:'Ext.data.Model'
	,fields:[
	     {name:'fid',type:'number',defaultValue:0}/*编号*/
	     ,{name:'dealmatters',type:'string'}/*处理事项*/
	     ,{name:'taskpartmentid',type:'number',defaultValue:0}/*任务部门Id*/
	     ,{name:'ftaskDeptName',type:'string'}/*任务部门*/
	     ,{name:'taskproperties',type:'string',defaultValue:'feedback_people'}/*任务属性- 默认反馈人*/
	     ,{name:'results',type:'string'}/*处理结果*/
	     ,{name:'prorecord',type:'string'}/*处理记录*/
	     ,{name:'feedtimelimit',type:'number',defaultValue:0}/*反馈时限(分钟)*/
	     ,{name:'processtimelimit',type:'number',defaultValue:0}/*处理时限(小时)*/
	     ,{name:'ifovertime',type:'string'}/*是否超时*/
	     ,{name:'ifmaturity',type:'string'}/*是否到期*/
	     ,{name:'lastprocessltimeimit',type:'number',defaultValue:0}////剩余处理时限
	     ,{name:'lastfeedtimelimit',type:'number',defaultValue:0}//剩余反馈时限
	     ,{name:'stat',type:'string'}//处理结果状态
	     ,{name:'dealType',type:'string',defaultValue:'department'}//处理类型 个人 或 部门
	     ,{name:'old_dealType',type:'string'}//处理类型 个人 或 部门 辅助字段 表示选择之前的类型
	     ,{name:'old_ftaskDeptName',type:'string'}//处理类型 个人 或 部门 的值 辅助字段 表示选择之前的类型
	     ,{name:'jobCode',type:'string'}//处理类型 个人 或 部门 的值 辅助字段 表示选择之前的类型
	   ]
});
/**
 * 附件 model
 */
Ext.define('FeedAttachModel',{
	extend:'Ext.data.Model',
	fields:[
	    {name:'id'},/*id*/
		{name:'feedId'},/*责任反馈ID*/
		{name:'attachPath'},/*附件路径*/
		{name:'attachName'},/*附件名称*/
		{name:'fileSize'},/*附件大小*/
		{name:'describe'}/*附件描述*/
	]
});
/**
 * 附件 model
 */
Ext.define('ComplaintDetailSuggestModel',{
	extend:'Ext.data.Model',
	fields:[
	    {name:'id'},/*id*/
		{name:'suggestion'},/*调查建议*/
		{name:'operatorName'},/*操作人*/
		{name:'operatorTime'}/*操作时间*/
	]
});

/**
 * 处理结果基础资料设置页面列表grid的model
 */
Ext.define('BasicInfoSettingModel',{
	extend:'Ext.data.Model',
	fields:[
	    {name:'id'},/*业务类型*/
		{name:'busType'},/*业务类型*/
		{name:'levelId'},/*业务类型ID*/
		{name:'dealLanguage'},/*处理语言*/
		{name:'reworpusType'},/*奖罚类型*/
		{name:'feedbackLimit'}/*反馈时限*/
	]
});




/**
 * 工单责任 - 查看详情 model
 */
Ext.define('DutySearchDetailModel',{
	extend:'Ext.data.Model',
	fields:[
		 {name:'id',type:'String'},/*工单ID -责任ID*/
		 {name:'voucherNumber',type:'String'}/*凭证号*/
	     ,{name:'tel',type:'String'}/*联系电话*/
	     ,{name:'complaintid',type:'String'}/*工单ID*/
		 ,{name:'caller',type:'String'}/*来电人类型*/
		 ,{name:'sendOrReceive',type:'string'}/*来电人*/
		 ,{name:'reportType',type:'String'}/*上报类型*/
		 ,{name:'complaintCust',type:'String'}/*来电客户*/
		 ,{name:'custLevel',type:'String'}/*客户等级*/
		 ,{name:'custType',type:'String'}/*客户类型*/
		 ,{name:'complaintSource',type:'string'}//工单来源
		 ,{name:'relatCus',type:'string'}//相关客户
	     ,{name:'relatCusLevel',type:'string'}//相关客户等级
	     ,{name:'timeLimit',type:'string'}//期望时限
	     ,{name:'pririty',type:'string'}//优先级别
	     ,{name:'rebindNo',type:'string'}//重复工单码
	     ,{name:'dealNumber',type:'string'}//处理编号
	     ,{name:'receiveTime',type:'Date',convert:DpUtil.changeLongToDate}
	     ,{name:'receiveUser',type:'string'}
	     ,{name:'reportTime',type:'Date',convert:DpUtil.changeLongToDate}//上报时间
	     ,{name:'reporter',type:'string'}//上报人
	     ,{name:'reportContent',type:'string'}//上报内容
	     ,{name:'custreQuire',type:'string'}//客户要求
	     ,{name:'status',type:'string'}//责任状态
	     ,{name:'surveyResult',type:'string'}//调查结果
	     ,{name:'proStatus',type:'string'}//处理结果
	     ,{name:'businessModel',type:'string'}//业务模式
	]
});
/**
 * 工单详情 - 业务范围 model
 */
Ext.define('ComplaintDetailBusinessModel',{
	extend:'Ext.data.Model',
	fields:[
		 {name:'parabasilevelid',type:'String'},/*业务范围*/
		 {name:'basiclevelid',type:'String'}/*业务类型*/
	     ,{name:'complevel',type:'String'}/*投诉级别*/
	]
});
/**
 * 工单责任查询 - 处理经过 model
 */
Ext.define('DutyDealProcedureModel',{
	extend:'Ext.data.Model',
	fields:[
	      {name:'dutyId',type:'String'}/*工单id*/
		 ,{name:'treatProcess',type:'String'}/*处理经过*/
	     ,{name:'opratorName',type:'String'}/*操作人*/
		 ,{name:'operatorTime'}/*操作时间*/
	]
});




/** 
 * 工单责任查询 - 责任划分结果 store
 */
Ext.define('DutyAllocationResultDeptModel',{
	extend:'Ext.data.Model',
	fields:[
	     {name:'dutyId',type:'String'}/*工单责任id*/
	     ,{name:'busAspectId',type:'String'}/*业务项*/
		 ,{name:'busScope',type:'String'}/*业务范围*/
	     ,{name:'busType',type:'String'}/*业务类型*/
	     ,{name:'businessLink',type:'String'}/*业务环节*/
	     ,{name:'dutyStatus',type:'String'}/*责任状态*/
		 ,{name:'dealLanguage',type:'String'}/*处理语言*/
		 ,{name:'divideType',type:'String'}/*类型*/
	     ,{name:'dutyPerName',type:'String'}/*责任部门*/
		 ,{name:'reworpusType',type:'String'}/*奖罚类型*/
		 ,{name:'dutyDeadLine'}/*反馈时限*/
	     ,{name:'surveyResult',type:'String'}/*调查结果*/
	]
});

/**
 * 工单责任查询和工单责任审批页面中 - 责任反馈记录表格的MODEL
 */
Ext.define('DutyFeedbackRecordListModel',{
	extend:'Ext.data.Model',
	fields:[{//责任反馈ID
		name:'feedbackId'
	},{//责任划分结果ID
		name:'detailId'
	},{//责任ID
		name:'dutyId'
	},{//反馈人
		name:'feedUserName'
	},{//反馈部门
		name:'feedDeptName'
	},{//反馈时间
		name:'feedBackTime',
		type : 'Date',
		defaultValue : null,
		convert : DpUtil.changeLongToDate
	},{//责任反馈内容
		name:'describe'
	},{//判断返回的反馈记录是否有附件
		name:'haveFeedAttach'
	},{//责任状态
		name:'status'
	},{//大区审批人
		name:'statUserName'
	},{//呼叫中心审批人
		name:'callCenUser'
	},{//大区审批意见
		name:'statDecision'
	},{//呼叫中心审批意见
		name:'callCenDescision'
	},{//审批依据
		name:'according'
	},{//大区审批时间
		name:'statTime',
		type : 'Date',
		defaultValue : null,
		convert : DpUtil.changeLongToDate
	},{//呼叫中心审批时间
		name:'callcenterTime',
		type : 'Date',
		defaultValue:null,convert:DpUtil.changeLongToDate
	}
	]
});
/**
 * 责任查询展开结果列表model
 */
Ext.define('ExpandDutyQueryResultModel', {
	extend : 'Ext.data.Model',
	fields : [{
		name:'dutyId'
	},
	{
		//责任部门
		name : 'appDutyDept'
	},{
		//反馈期限
		name : 'dutyDeadLine'
	},{
		//责任状态
		name : 'dutyStatus'
	},{
		//调查结果
		name : 'surveyResult'
	},{
		//反馈人
		name : 'feedUser'
	},{
		//反馈时间
		name : 'feedTime'
	}]
});
/**
 * 工单详情-退回结果model
 */
Ext.define('ComplaintDetailOpeRecordModel', {
	extend : 'Ext.data.Model',
	fields : [{
		name:'dutyId'
	},
	{
		//类型
		name : 'recordtype'
	},{
		//解决情况
		name : 'revisitresult'
	},{
		//反馈内容
		name : 'wailbillcontent'
	},{
		//客户满意度
		name : 'custsatisfy'
	},{
		//反馈人
		name : 'recordman'
	},{
		//组织
		name : 'recordpartermentid'
	},{
		//时间
		name : 'recordtime'
	}]
});

