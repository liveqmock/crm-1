/**
 * 我要处理的工作流
 */
//判断是否导入测试数据
(function() {
	var workFlowManagerDataTest = "../scripts/membercust/test/WorkFlowManagerDataTest.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + workFlowManagerDataTest + '"></script>');
	}
})();
//工作流查看详情 model
Ext.define('WorkFlowModel',{
	extend:'Ext.data.Model',
	fields:[
		//工作流申请人id
		{name:'userId',type: 'string'},
		//工作流申请人name
		{name:'userName',type: 'string'},
		//工作流状态
		{name:'status',type: 'string'},
		//工作流id
		{name:'workFlowId',type: 'number'},
		//工作流名称
		{name:'workFLowName',type: 'string'},
		//所属部门id
		{name:'deptId',type: 'string'},
		//所属部门
		{name:'deptName',type: 'string'},
		//业务id
		{name:'appId',type: 'string'},
		{name:'createUser',type:'string'}, 
		{name:'createDate', type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null}, 
		{name:'modifyDate', type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null},
		{name:'modifyUser',type:'string'}]
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
Ext.define('WorkFlowStore',{
	extend:'Ext.data.Store',
	model:'WorkFlowModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		url:'searchWorkFLow.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'workFlowList',
			totalProperty:'totalCount'
		}
	}
});
Ext.define('WorkFlowManagerData',{
	extend:'MyWorkFlowDealManagerData',
	workFlowNameStore:null,//工作流名称
	workFlowStateStore:null,//流程状态
	workFlowManagerStore:null,
	//我的工作流 查询结果store
	getWorkFlowManagerStore: function() {
		return this.workFlowManagerStore;
	},
	//初始化我的工作流 查询结果store
	initWorkFlowManagerStore: function(beforeLoadFn) {
		if(this.workFlowManagerStore == null){
			if(beforeLoadFn != null){
				this.workFlowManagerStore = Ext.create('WorkFlowStore',{
					listeners:{
						beforeload:beforeLoadFn
					}
				});
			}else{
				this.workFlowManagerStore = Ext.create('WorkFlowStore');
			}
		}
		return this.workFlowManagerStore;
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
	//查看工作流信息 特殊会员 
	acquireSepMemberExaminView: function(params,successFn,failFn){
		var acquireSepMemberExaminViewUrl='viewSepMemberExaminView.action';
		DpUtil.requestJsonAjax(acquireSepMemberExaminViewUrl,params,successFn,failFn);
	},
	//查看工作流信息 会员信息维护 
	acquireModifyMemberExaminView: function(params,successFn,failFn){
		var acquireModifyMemberExaminViewUrl='viewModifyMemberExaminView.action';
		DpUtil.requestJsonAjax(acquireModifyMemberExaminViewUrl,params,successFn,failFn);
	},
	//变更联系人 处理
	acquireContactVary: function(params,successFn,failFn){
		DpUtil.requestJsonAjax('viewContactVary.action',params,successFn,failFn);
	},
	//查看工作流信息 会员部门变更
	viewChangeMemberDept: function(params,successFn,failFn){
		DpUtil.requestJsonAjax('viewChangeMemberDept.action',params,successFn,failFn);
	},
	//查看工作流信息 合同新增
	viewContractAddExamin: function(params,successFn,failFn){
		DpUtil.requestJsonAjax('viewContractAddExamin.action',params,successFn,failFn);
	},
	//查看工作流信息 合同新增
	viewScatterAccountExamin: function(params,successFn,failFn){
		DpUtil.requestJsonAjax('viewScatterAccountExamin.action',params,successFn,failFn);
	}
});