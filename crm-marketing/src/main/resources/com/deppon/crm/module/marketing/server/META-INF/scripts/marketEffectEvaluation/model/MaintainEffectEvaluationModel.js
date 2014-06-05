/**
 * 客户维护查询结果表格model
 */
Ext.define('MaintainEffectEvaluationListModel', {
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
		// 客户数
		name : 'recompenseCustNum',
		type:'int'
	},{
        // 零担客户数
        name : 'lttCompCustNum',
        type:'int'
    },{
        // 快递客户数
        name : 'expCompCustNum',
        type:'int'
    }, {
		// 本月回访数
		name : 'nowVisitNum',
		type:'int'
	},{
        // 零担本月回访数
        name : 'lttVisitNum',
        type:'int'
    },{
        // 快递本月回访数
        name : 'expVisitNum',
        type:'int'
    },{
		// 回访率
		name : 'nowVisitRate'
	},{
        // 零担回访率
        name : 'lttVisitRate'
    },{
        // 快递回访率
        name : 'expVisitRate'
    }, {
		//流失客户数
		name : 'lostCustNum',
		type:'int'
	},{
		// 流失率
		name : 'lostCustRate'
	},{
		// 本月回访流失客户数
		name : 'lostCustVisitNum',
		type:'int'
	}, {
		// 流失回访率
		name : 'lostVisitRate'
	}, {
		//挽回客户数
		name : 'saveCustNum',
		type:'int'
	},{
		// 客户挽回率
		name : 'saveCustRate'
	}, {
		// 上上月固定客户发货数
		name : 'memberDeleviryNum',
		type:'int'
	}]
});