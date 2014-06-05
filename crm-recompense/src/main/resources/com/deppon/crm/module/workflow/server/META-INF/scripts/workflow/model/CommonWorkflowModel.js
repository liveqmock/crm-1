/**
 * 工作流列表Model
 */
Ext.define('WorkflowModel', {
	extend : 'Ext.data.Model',
	fields : [{
				name : 'processinstid' //工作流号
			},{
				name: 'busino'
			}, {
				name : 'processinstname'//工作流名称
			}, {
				name : 'condition'//工作流状态
			}, {
				name : 'appler'//申请人
			}, {
				name: 'appdate'//申请时间
			},{
				name:'apppost' //申请人职位
			},{
				name:'department' //所属部门
			},{
				name:'workItemId' //工作项id
			},{
				name:'processinstidEnc' //工作项id
			},{
				name:'workItemIdEnc' //工作项id
			},{
				name:'processCHName' //工作流中文名
			},{
				name:'processDefName' //流程定义名称
			}]
});

/**
 * 工作流审批记录Model
 */
Ext.define('ApprovalInfoModel', {
	extend : 'Ext.data.Model',
	fields : [{
				name : 'approvedate' //审批时间
			}, {
				name : 'busino'//审批人职位
			}, {
				name : 'approver'//审批人
			}, {
				name : 'isagree'//审批决策
			}, {
				name: 'approvever'//审批意见
			}]
});