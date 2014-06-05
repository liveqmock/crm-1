/**
 * 客户开发查询结果表格model
 */
Ext.define('DevEffectEvaluationListModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name:'id'
	},{
		// 部门Id
		name : 'deptId'
	},{
		// 经营本部--部门显示(把部门id和部门名称一起传过来)
		name : 'deptName'
	},{
		// 上级部门ID
		name : 'parentId'
	},{
		name:'parentName'//上级部门名称
	}, {
		//本月新增潜客数量-会展
		name : 'exposend'
	},{
		// 本月新增潜客数量-黄页
		name : 'yellowPages'
	}, {
		// 派单
		name : 'dispatchList'
	},{
		// 阿里巴巴
		name : 'alibaba'
	}, {
		// 陌生来电
		name : 'strangerCalls'
	},{
		// 订单潜客
		name : 'orderCust'
	}, {
		//到达散客
		name : 'arriveCust'
	},{
		// 计划回访数
		name : 'planVisitNum'
	}, {
		// 实际回访数
		name : 'actualVisitNum'
	},{
		// 计划内回访率
		name : 'planVisitRate'
	}, {
		// 新增数量
		name : 'nowPotentialNum'
	},{
		// 新增潜客回访数
		name : 'nowPotentialVisitNum'
	}, {
		// 本月新增潜客回访率
		name : 'nowPotentialVisitRate'
	},{
		// 近三个月回访潜客数
		name : 'beforePotentialNum'
	},{
		// 近三个月回访潜客本月发货数
		name : 'nowPotentialDeleviryNum'
	}, {
		// 潜客开发成功率
		name : 'potentialDevSuccessRate'
	},{//CRM二期对报表所做的改造
		// 新增潜客数
		name : 'newCreateNum'
	}, {
		// 转化为散客数
		name : 'turnToScNum'
	}, {
		// 转化为固定客户数
		name : 'turnToRcNum'
	}, {
		// 当前需求确认阶段客户数
		name : 'nowIdentifyNeedsCustNum'
	}, {
		// 需求确认阶段转化客户数
		name : 'identifyNeedsOutCustNum'
	}, {
		// 需求确认阶段转化率
		name : 'identifyNeedsCustRate'
	}, {
		// 当前意向洽谈阶段客户数
		name : 'nowIntentionDiscussCustNum'
	}, {
		// 意向洽谈阶段转化客户数
		name : 'intentionDiscussOutCustNum'
	}, {
		// 意向洽谈阶段转化率
		name : 'intentionDiscussCustRate'
	}, {
		// 当前持续培养阶段客户数
		name : 'nowContinuingTrainingCustNum'
	}, {
		// 持续培养阶段转化客户数
		name : 'continuingTrainingOutCustNum'
	}, {
		// 持续培养阶段转化率
		name : 'continuingTrainingCustRate'
	}, {
		// 零担发货金额
		name : 'lttDelCustAmount'
	}, {
		// 快递发货金额
		name : 'expDelCustAmount'
	}]
});