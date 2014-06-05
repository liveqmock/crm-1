/**
*会员升级Data层
*/

//判断是否导入测试数据
(function() {
	var workFlowManagerDataTest = "../scripts/membercust/test/WorkFlowManagerDataTest.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + workFlowManagerDataTest + '"></script>');
	}
})();
//获得当前用户
var CurrentUser = {};

//工作流查询条件model
Ext.define('WorkflowConditionModel',{
	extend:'Ext.data.Model',
	fields:[
		// 流程序号
		{name:'workflowId',  type: 'number'},
		// 流程名称
		{name:'workflowName',  type: 'string'},
		// 流程状态
		{name:'applicationStatus',  type: 'string'},
		// 所属部门
		{name:'deptId',  type: 'number'},
		// 申请人
		{name:'createUser',  type: 'string'},
		// 申请开始时间
		{name:'startDate',  type: 'date',convert: DpUtil.changeLongToDate,defaultValue:(new Date().setDate(new Date().getDate()-30))},
		// 申请结束时间
		{name:'endDate',  type: 'date',convert: DpUtil.changeLongToDate,defaultValue:new Date()}]
});

//工作流查询结果model
Ext.define('WorkflowResultModel',{
	extend:'Ext.data.Model',
	fields:[// 流程序号
		{name:'workflowId',  type: 'number'},
		// 流程名称
		{name:'workflowName',  type: 'string'},
		// 流程状态
		{name:'status',  type: 'string'},
		// 申请人
		{name:'createUser',  type: 'string'},
		// 申请开始时间
		{name:'createDate',  type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null},
		// 所属部门
		{name:'deptId',  type: 'number'},
		// 所属部门
		{name:'deptName',  type: 'string'},
		
		{name:'userName',type: 'string'},
		{name:'applicationId',type: 'string'},
		{name:'applicationName',type: 'string'},
		{name:'applicationDesc',type: 'string'},
		{name:'applicationStatusDesc',type: 'string'},
		{name:'url',type: 'string'},
		{name:'roleId',type: 'string'},
		{name:'roleName',type: 'string'},
		{name:'todoFlag',type: 'string'}]
});
/**
 * 工作流查询结果store
 */
Ext.define('WorkflowConditionStore',{
	extend:'Ext.data.Store',
	model:'WorkflowResultModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		api:{
			read:'searchWorkflowList.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'workflowSearchResult',
			totalProperty:'totalCount'
		}
	}
});
//工作流查看详情 model
Ext.define('MemberExaminViewModel',{
	extend:'Ext.data.Model',
	fields:[
		//审批的会员对象
		{name:'member'},
		//审批数据修改对象
		{name:'appList'},
		//审批历史记录	
		{name:'examineRecordList'}]
});
//审批数据修改对象
Ext.define('ApproveDataModel',{
	extend:'Ext.data.Model',
	fields:[
		{name:'id',type: 'string'},
		// 实体类名
		{name:'className',type: 'string'},
		// 实体类ID
		{name:'classId',type: 'string'},
		// 字段名
		{name:'fieldName',type: 'string'},
		// 新值
		{name:'newValue'},
		// 旧值
		{name:'oldValue'},
		// 工作流id
		{name:'workFlowId',type: 'string'},
		// 操作类型 1为新增，2为修改,3为删除，
		{name:'handleType',type: 'number'}]
}); 
//审批数据修改对象	Store
Ext.define('ApproveDataStore',{
	extend:'Ext.data.Store',
	model:'ApproveDataModel',
	proxy:{
		type:'memory'
	}
});
//审批历史记录	
Ext.define('ExamineRecordModel',{
	extend:'Ext.data.Model',
	fields:[
		//审批意见
		{name:'opinion',type: 'string'},
		//处理结果
		{name:'result',type: 'boolean'},
		//权责
		{name:'rights',type: 'string'},
		//处理人 
		{name:'disposeUserId',type: 'string'},
		//工作流id
		{name:'workFlowId',type: 'number'},
		//处理时间
		{name:'modifyDate',type:'data',convert: DpUtil.changeLongToDate,defaultValue:null}]
});
//审批历史记录	Store
Ext.define('ExamineRecordStore',{
	extend:'Ext.data.Store',
	model:'ExamineRecordModel',
	proxy:{
		type:'memory'
	}
});
//
Ext.define('MyWorkFlowDealManagerData',{
	extend:'MemberCustBasicData',
	workFlowManagerStore:null,
	workFlowNameStore:null,//工作流名称
	workFlowStateStore:null,//流程状态
	approveDataStore:null,//审批数据修改对象	Store
	examineRecordStore:null,//审批历史记录	Store
	//工作流查询结果store
	getMyWorkFlowDealManagerStore: function() {
		return this.workFlowManagerStore;
	},
	//初始化会员升级列表查询结果store
	initMyWorkFlowDealManagerStore: function(beforeLoadTransactionFn) {
		if(this.workFlowManagerStore == null){
			if(beforeLoadTransactionFn != null){
				this.workFlowManagerStore = Ext.create('WorkflowConditionStore',{
					listeners:{
						beforeload:beforeLoadTransactionFn
					}
				});
			}else{
				this.workFlowManagerStore = Ext.create('WorkflowConditionStore');
			}
		}
		return this.workFlowManagerStore;
	},
	//查看工作流信息 特殊会员 进行审批
	acquireSepMemberExaminView: function(params,successFn,failFn){
		var acquireSepMemberExaminViewUrl='acquireSepMemberExaminView.action';
		DpUtil.requestJsonAjax(acquireSepMemberExaminViewUrl,params,successFn,failFn);
	},
	//查看工作流信息 会员信息维护 进行审批
	acquireModifyMemberExaminView: function(params,successFn,failFn){
		var acquireModifyMemberExaminViewUrl='acquireModifyMemberExaminView.action';
		DpUtil.requestJsonAjax(acquireModifyMemberExaminViewUrl,params,successFn,failFn);
	},
	//工作流进行审批创建特殊会员
	commitSepCreateMemberExamin: function(params,successFn,failFn){
		var commitSepCreateMemberExaminUrl = 'commitSepCreateMemberExamin.action';
		DpUtil.requestJsonAjax(commitSepCreateMemberExaminUrl,params,successFn,failFn);
	},
	//工作流进行审批修改会员信息
	commitModifyMemberExamin: function(params,successFn,failFn){
		var commitModifyMemberExaminUrl = 'commitModifyMemberExamin.action';
		DpUtil.requestJsonAjax(commitModifyMemberExaminUrl,params,successFn,failFn);
	},
	//工作流进行审批联系人挂靠
	commitMemberContactRalationExamin: function(params,successFn,failFn){
		var commitMemberContactRalationExamin = 'commitMemberContactRalationExamin.action';
		DpUtil.requestJsonAjax(commitMemberContactRalationExamin,params,successFn,failFn);
	},
	//获得当前登录用户
	getCurrentUser:function(){
		Ext.Ajax.request({
			url:'../common/queryUserInfo.action',
			async:false,
			jsonData:{},
			success:function(response){
				var result = Ext.decode(response.responseText);
				if(result.success){
					CurrentUser = result.user;
				}else{
					Ext.Msg.alert('温馨提示',result.message)
				}
			},
			failure:function(response){
				var result = Ext.decode(response.responseText);
				Ext.Msg.alert('温馨提示',result.message)
			}
		});
	},
	//工作流名称
	getWorkFlowName : function() {
		if (this.workFlowNameStore == null) {
			this.workFlowNameStore = getDataDictionaryByName(DataDictionary,'WORKFLOW_NAME'); 
		}
		return this.workFlowNameStore;
	},
	//流程状态
	getWorkFlowState : function() {
		if (this.workFlowStateStore == null) {
			this.workFlowStateStore = getDataDictionaryByName(DataDictionary,'WORKFLOW_STATE'); 
		}
		return this.workFlowStateStore;
	},
	//审批 记录
	getExamineRecordStore:function() {
		if (this.examineRecordStore == null) {
			this.examineRecordStore = Ext.create('ExamineRecordStore');
		}
		return this.examineRecordStore;
	},
	//审批修改对象数据
	getApproveDataStore:function() {
		if (this.approveDataStore == null) {
			this.approveDataStore = Ext.create('ApproveDataStore'); 
		}
		return this.approveDataStore;
	},
	//变更联系人 处理
	acquireContactVary: function(params,successFn,failFn){
		DpUtil.requestJsonAjax('acquireContactVary.action',params,successFn,failFn);
	},
	//查看工作流信息 会员部门变更
	acquireChangeMemberDept: function(params,successFn,failFn){
		DpUtil.requestJsonAjax('acquireChangeMemberDept.action',params,successFn,failFn);
	},
	//处理工作流信息 会员部门变更
	commitChangeMemberDeptExamin: function(params,successFn,failFn){
		DpUtil.requestJsonAjax('commitChangeMemberDeptExamin.action',params,successFn,failFn);
	},
	//查看工作流信息 合同新增
	acquireContractAdd: function(params,successFn,failFn){
		DpUtil.requestJsonAjax('acquireContractAdd.action',params,successFn,failFn);
	},
	//处理工作流信息 合同新增
	commitContractAddExamin: function(params,successFn,failFn){
		DpUtil.requestJsonAjax('commitContractAddExamin.action',params,successFn,failFn);
	},
	//处理工作流信息 散客账户审批  从后台调数据到前端
	viewLoadScatterAccount: function(params,successFn,failFn){
		DpUtil.requestJsonAjax('viewLoadScatterAccount.action',params,successFn,failFn);
	},
	//处理工作流信息 散客账户维护
	commitSCAccountAlterExamin: function(params,successFn,failFn){
		DpUtil.requestJsonAjax('commitSCAccountAlterExamin.action',params,successFn,failFn);
	}
});