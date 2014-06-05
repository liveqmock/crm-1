
/**
 * 部门列表Model
 */
Ext.define('DeptListModel', {
	extend : 'Ext.data.Model',
	fields : [{
		// 部门名称
		name : 'deptName'
	},{
		// 部门ID
		name : 'id'
	}]
});
/**
 * 客户开发查询结果表格model
 */
Ext.define('CRM.Report.BO', {
	extend : 'Ext.data.Model',
	fields : [{
		name:'divisionId'//经营本部ID
	},{
		// 经营本部name
		name : 'divisionName;'
	},{
		// // 事业部
		name : 'bizId'
	},{
		// bizName
		name : 'bizName'
	},{
		// // 事业部
		name : 'deptId'
	},{
		// bizName
		name : 'deptName'
	},{
		// 新建商机数量
		name:'createNum'
	}, {
		// 当前进行中商机数
		name : 'ongoingNum'
	},{
		// 当前超期进行中商机数
		name : 'extendedNum'
	}, {
		// 当前已休眠商机数
		name : 'dormantNum',
		type:'int'
	},{
		// 商机预计发货总金额
		name : 'expectDeliveryAmount'
	}, {
		// 当前初步接触阶段商机数
		name : 'contactNum',
		type:'int'
	},{
		// 本月初步接触阶段商机回访次数
		name : 'contactReturnVisitNum',
		type:'int'
	}, {
		// 本月初步接触阶段转化商机数
		name : 'contactTransNum',
		type:'int'
	},{
		// 初步接触阶段转化率
		name : 'contactTransRate'
	}, {
		// 当前需求分析阶段商机数
		name : 'analyzeNum',
		type:'int'
	},{
		// 本月需求分析阶段商机回访次数
		name : 'analyzeReturnVisitNum'
	}, {
		// 本月需求分析阶段转化商机数
		name : 'analyzeTransNum',
		type:'int'
	},{
		// 需求分析阶段转化率
		name : 'analyzeTransRate'
	}, {
		// 当前制定方案阶段商机数
		name : 'schemeNum'
	}, {
		// 本月制定方案阶段商机回访次数
		name : 'schemeReturnVisitNum'
	}, {
		// 本月制定方案阶段转化商机数
		name : 'schemeTransNum'
	}, {
		// 制定方案阶段转化率
		name : 'schemeTransRate'
	}, {
		// 当前报价/竞标阶段商机数
		name : 'offerNum'
	}, {
		// 本月商机日程完成率
		name : 'performRate'
	}, {
		// 本月内实际执行的商机日程数量
		name : 'performNum'
	}, {
		// 本月应执行的商机日程数量
		name : 'scheduleNum'
	}, {
		// 开发成功商机对应客户发货量
		name : 'deliverAmount'
	}, {
		// 商机成功率
		name : 'successRate'
	}, {
		// 失败关闭商机数
		name : 'failureNum'
	}, {
		// 成功关闭商机数
		name : 'successNum'
	}, {
		// 持续发货阶段转化率
		name : 'deliverTransRate'
	}, {
		// 本月持续发货阶段转化商机数
		name : 'deliverTransNum'
	}, {
		// 本月持续发货阶段商机回访次数
		name : 'deliverReturnVisitNum'
	}, {
		// 当前持续发货阶段商机数
		name : 'deliverNum'
	}, {
		// 报价/竞标阶段转化率
		name : 'offerTransRate'
	}, {
		// 本月报价/竞标阶段转化商机数
		name : 'offerTransNum'
	}, {
		// 本月报价/竞标阶段商机回访次数
		name : 'offerReturnVisitNum'
	}]
});