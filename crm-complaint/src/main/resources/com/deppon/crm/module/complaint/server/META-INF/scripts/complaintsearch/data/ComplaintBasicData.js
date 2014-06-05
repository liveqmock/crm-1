
//客户model
Ext.define('ComplaintModel',{
	extend:'Ext.data.Model',
	fields:[
	     {name:'fid',type:'number',defaultValue:0}/*编号*/
	     ,{name:'businessModel',type:'tring'}//业务类型
	     ,{name:'bill',type:'string'}/*凭证号*/ 
	     ,{name:'dealbill',type:'string'}/*处理编号*/
	     ,{name:'recomcode',type:'string'}/*重复工单码*/
	     ,{name:'reporttype',type:'string'}/*上报类型*/
	     ,{name:'compman',type:'string'}/*来电人*/
	     ,{name:'pririty',type:'string'}/*优先级别*/
	     ,{name:'complevel',type:'string'}/*投诉级别*/
	     ,{name:'prostatus',type:'string'}/*处理状态*/
	     ,{name:'recordTime',type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null}/*反馈时间*/
	     ,{name:'timelimit',type:'number',defaultValue:0}/*期望时限*/
	     ,{name:'tilimitcycle',type:'string'}/*期望时限周期*/
	     ,{name:'dealstatus',type:'string'}/*解决情况*/
	     
	     ,{name:'custlevel',type:'string'}/*来电客户等级*/
	     ,{name:'custtype',type:'string'}/*客户类型*/
	     ,{name:'relatcus',type:'string'}/*相关客户*/
	     ,{name:'relatcuslevel',type:'string'}/*相关客户等级*/
	     ,{name:'reportcontent',type:'string'}/*上报内容*/
	     ,{name:'custrequire',type:'string'}/*客户要求*/
	     ,{name:'recommendation',type:'string'}/*调查建议*/
	     ,{name:'feedbackrecode',type:'string'}/*反馈记录*/
	     ,{name:'feedback',type:'number',defaultValue:0}/*最终反馈*/
	     ,{name:'feedbackreason',type:'string'}/*退回原因*/
	     ,{name:'complaintsource',type:'string'}/*工单来源*/
	     ,{name:'dealtime',type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null}/*处理时间*/
	     //新处理结果基础资料
	     ,{name:'parabasilevelid',type:'number',defaultValue:0}/*业务项Id*/
	     ,{name:'basiclevelid',type:'number',defaultValue:0}/*业务范围ID*/
	     ,{name:'busTypeId'}/*业务类型ID*/
	     ,{name:'busItemName'}/*业务项名称*/
	     ,{name:'busScopeName'}/*业务范围名称*/
	     ,{name:'busTypeName'}/*业务类型名称*/
	     ,{name:'complainLink'}/*业务环节*/
	     ,{name:'businessType',type:'String'} //业务类型
	     ,{name:'busSceneId'}/*业务场景id*/
	     ,{name:'busSceneName'}/*业务场景名称*/
	     ,{name:'sceneRessonId'}/*场景原因id*/
	     ,{name:'sceneRessonName'}/*场景原因名称*/
	      
	     ,{name:'rebindno',type:'string'}/*重复工单绑定码*/
	     ,{name:'complaincust',type:'string'}/*来电客户*/
	     ,{name:'tel',type:'string'}/*联系电话*/
	     ,{name:'reporter',type:'string'}/*上报人*/
	     ,{name:'reporttime',type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null}/*上报时间*/
	     ,{name:'operatoername',type:'string'}/*操作人（提交人）*/
	     ,{name:'lastupdatetime',type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null}/*最后操作时间（提交时间）*/
	     
	     ,{name:'feedtimelimit',type:'number',defaultValue:0}/*反馈时限(分钟)*/
	     ,{name:'processtimelimit',type:'number',defaultValue:0}/*处理时限(小时) 后台没有*/
	     ,{name:'recordpartmentname',type:'string'}/*反馈部门*/
	     
	     ,{name:'deptname',type:'string'}/*反馈部门recordpartmentname的别名*/
	     ,{name:'sameBill',type:'number',defaultValue:0}/*是否为同单号投诉(只适合异常)*/
	     ,{name:'postponeCount',type:'string'}/*现有部门延时申请数目*/
	     ,{name:'returnBackCount',type:'string'}/*现有部门退回数目*/
	     ,{name:'numberOfLock',type:'number'}/*接入锁定次数*/
	     ,{name:'lockingUserId',type:'number'}/*锁定人ID（Employee id）*/
	     ,{name:'lockingTime',type: 'date',convert: DpUtil.changeLongToDate}/*接入到期时间*/
	     ,{name:'sendorreceive',type:'string'}/*来电人类型*/
	     //投诉客户ID
	     ,{name:'complainid',type:'string'}
	     //相关客户ID
	     ,{name:'relatcusid',type:'string'},	     
	     //上报人
	     {name:'reporter',type:'string'}
	     ,	     
	     //出发部门
	     {name:'leaveDept',type:'string'}
	     ,	     
	     //出发部门名字
	     {name:'leaveDeptName',type:'string'}
	   ]
});
//处理结果 model
Ext.define('ProcessResultModel',{
	extend:'Ext.data.Model'
	,fields:[
	     {name:'fid',type:'number',defaultValue:0}/*编号*/
	     ,{name:'dealmatters',type:'string'}/*处理事项*/
	     ,{name:'taskpartmentid',type:'number',defaultValue:0}/*任务部门Id*/
	     ,{name:'ftaskDeptName',type:'string'}/*任务部门*/
	     ,{name:'leaveDept'}/*出发部门*/
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
	     ,{name:'procStandard',type:'string'}//处理标准
	   ]
});

//处理记录(反馈记录 and 退回原因) model
Ext.define('ProcessRecordModel',{
	extend:'Ext.data.Model'
	,fields:[
	     {name:'fid',type:'number',defaultValue:0}/*编号*/
	     ,{name:'wailbillnunber',type:'string'}//凭单号
	     ,{name:'recordtype',type:'string'}/*类型*/
	     ,{name:'wailbillcontent',type:'string'}/*解决情况*/
	     ,{name:'revisitresult',type:'string'}//反馈内容
	     ,{name:'custsatisfy',type:'string'}//客户满意度
	     ,{name:'recordman',type:'string'}/*反馈人*/
	     ,{name:'recordpartmentname',type:'string'}/*组织*/
	     ,{name:'recordtime',type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null}/*时间*/
	 ]
});

//搜索客户model
Ext.define('SearchCustConditionModel',{
	extend:'Ext.data.Model'
	,fields:[
	     {name:'memberId',type:'string'}/*会员id*/
	 ]
});

/**
 * 客户工单 重复工单 结果 store
 */
Ext.define('RepeatComplaintStore',{
	extend:'Ext.data.Store'
	,model:'ComplaintModel'
	,autoLoad:true
	,proxy:{
		type:'ajax'
		,url:'searchRepeatComplaintListByRecomcode.action'
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'complaintList'
			,totalProperty:'totalCount'
		}
	}
});

/**
 * 客户工单 (投诉、异常)结果 store
 */
Ext.define('ComplaintProcessStore',{
	extend:'Ext.data.Store'
	,model:'ComplaintModel'
	,proxy:{
		type:'ajax'
		,url:'searchComplaints3ByReportType.action'
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'complaintList'
		}
	}
});

/**
 *  ProcessResultStore
	,searchResultList:null //处理结果数据
 */
Ext.define('ProcessResultStore',{
	extend:'Ext.data.Store'
	,model:'ProcessResultModel'
	,autoLoad:true
	,proxy:{
		type:'ajax'
		,url:'searchProcessResultListByCompId.action'
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'searchResultList'
		}
	} 
});


/**
 *  ReturnProcessResultStore
	,searchResultList:null //处理结果数据(部门退回的处理结果)
 */
Ext.define('ReturnProcessResultStore',{
	extend:'Ext.data.Store'
	,model:'ProcessResultModel'
	,autoLoad:true
	,proxy:{
		type:'ajax'
		,url:'searchReturnResultListByCompId.action'
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'searchResultList'
		}
	}
});

/**
 * 	 反馈记录
 *   ProcessRecordStore
	,searchResultList:null 
 */
Ext.define('ProcessRecordStore',{
	extend:'Ext.data.Store'
	,model:'ProcessRecordModel'
	,autoLoad:true
	,proxy:{
		type:'ajax'
		,url:'searchReturnedRecordListByCompId.action'
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'searchResultList'
		}
	}
});


/**
 *  BusinessScopeStoreStore
 *  ,basciLevelList:null //业务项
 */
Ext.define('BusinessScopeStore',{
	extend:'Ext.data.Store'
	,fields: [
		{name:'fid',type:'number',defaultValue:0}/*编号*/
		,{name:'bascilevelname',type:'String'}/*层级名称*/
	]
	,proxy:{
		type:'ajax'
		,url:'searchBusinessScopeList.action'
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'basciLevelList'
		}
	}
});
/**
 *  DepartmentStore 部门 store
 *  ,departmentList:null //部门集合
 */
Ext.define('DepartmentStore',{
	extend:'Ext.data.Store'
	,fields: [
		{name:'id',type:'String'}/*编号*/
		,{name:'deptName',type:'String'}/*部门名称*/
	]
	,pageSize:40
	,autoLoad:true
	,proxy:{
		type:'ajax'
		,url:'searchDepartmentList.action'
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'departmentList'
		}
	}
});

/**
 *  新增的业务范围的Model
 *  肖红叶
 */
Ext.define('BusinessTypeForNewModel', {
	extend : 'Ext.data.Model',
	fields : [{
		// 业务范围ID
		name : 'fid',type:'number',defaultValue:0
	},{
		//业务范围名称
		name : 'bascilevelname'
	}]
});

/**
 *  BusinessTypeStore
 *  ,basciLevelList:null //业务范围
 */
Ext.define('BusinessTypeStore',{
	extend:'Ext.data.Store'
	,fields: [
		{name:'fid',type:'number',defaultValue:0}/*编号*/
		,{name:'bascilevelname',type:'String'}/*层级名称*/
	]
	,data:[]
});

/**
 *  新增的业务类型的Model
 *  肖红叶
 */
Ext.define('BasicBusinessTypeNewModel', {
	extend : 'Ext.data.Model',
	fields : [{
		// 业务类型ID
		name : 'busTypeId'
	},{
		//业务类型名称
		name : 'busTypeName'
	},{
		//上报类型
		name : 'reportType'
	},{
		//处理语言
		name : 'dealLanguage'
	},{
		//处理时限
		name : 'procLimit'
	},{
		//反馈时限
		name : 'feedbackLimit'
	}]
});

/**
 *  新增的业务类型的store，根据业务范围ID，查询出业务类型内容
 *  肖红叶
 */
Ext.define('BasicBusinessTypeNewStore',{
	extend:'Ext.data.Store',
	model:'BasicBusinessTypeNewModel',
	proxy : {
		type : 'ajax',
		url : '../complaint/searchBusTypeByBusScope.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'basicInfoList'
		}
	}
});


/**
 * 员工对象 store
 */
Ext.define('EmployeeStore',{
	extend:'Ext.data.Store'
	,fields:[
		 {name:'empCode',type:'String'}/*员工编号*/
		 ,{name:'empName',type:'String'}/*员工姓名id*/
		 ,{name:'mobileNumber',type:'number',defaultValue:0}/*联系电话*/
		 ,{name:'deptId'}/*部门实体*/
	]
	,data:[]
});


/**
 * 通知对象 store
 */
Ext.define('BulletinStore',{
	extend:'Ext.data.Store'
	,fields:[
		 {name:'fid',type:'number',defaultValue:0}/*主键*/
	     ,{name:'bulletinid',type:'number',defaultValue:0}/*通知对象和编号*/
		 ,{name:'bulletinJobId',type:'String'}/*通知对象工号*/
		 ,{name:'bulletinname',type:'String'}/*通知对象名称*/
		 ,{name:'bulletinTel',type:'number',defaultValue:0}/*联系电话*/
		 ,{name:'bulletinDeptName',type:'String'}/*部门*/
		 ,{name:'is_manual_add',type:'number',defaultValue:0}/*辅助属性，默认非手动添加*/
		 ,{name:'dealType',type:'string'}//处理类型 个人 或 部门  默认个人
		 ,{name:'position',type:'string'}//职位
	     ,{name:'jobCode',type:'string'}//处理类型 个人 或 部门 的值 辅助字段 表示选择之前的类型
	     ,{name:'processtimelimit',type:'number'}//剩余处理时限
	     ,{name:'feedtimelimit',type:'number'}//剩余反馈时限
	]
	,proxy:{
		type:'ajax'
		,url:'searchBulletinListToProByCompId.action'
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'bulletinList'
		}
	}
});

//操作记录  - 调查建议 model
Ext.define('WorkOrderModel',{
	extend:'Ext.data.Model'
	,fields:[
	     {name:'fid',type:'number',defaultValue:0}/*编号*/
//	     ,{name:'complaintId',type:'number'}//工单编号
//	     ,{name:'operatorId',type:'number'}/*操作人Id*/
//	     ,{name:'operatorRoleId',type:'number'}/*操作人角色Id*/
	     ,{name:'operatorName',type:'string'}/*操作人名称*/
//	     ,{name:'operatorType',type:'string'}//操作类型
//	     ,{name:'operatorRecord',type:'string'}//操作记录(动作)
//	     ,{name:'currentState',type:'string'}/*当前状态*/
//	     ,{name:'previousState',type:'string'}/*前一状态*/
	     ,{name:'suggestion',type:'string'}/*调查建议*/
	     ,{name:'operatorTime',type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null}/*操作时间*/
	 ]
});


/**
 * 操作记录  - 调查建议 store
 */
Ext.define('SurveySuggestStore',{
	extend:'Ext.data.Store'
	,model:'WorkOrderModel'
	,proxy:{
		type:'ajax'
		,url:'searchSurveySuggestList.action'
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'workOrderList'
		}
	}
});

