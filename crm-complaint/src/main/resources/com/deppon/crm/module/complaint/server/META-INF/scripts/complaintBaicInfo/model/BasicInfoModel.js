/**
 * 业务项添加列表grid的model
 */
Ext.define('BusinessItemsModel',{
	extend:'Ext.data.Model',
	fields:[{
		// 业务项
		name:'busItemName'
	},{
		// 上报类型
		name:'reportType'
	},{
		// 业务项ID
		name:'busItemId'
	}]
});

/**
 * 处理结果基础资料查询主页面列表grid的model
 */
Ext.define('BasicInfoResultModel',{
	extend:'Ext.data.Model',
	fields:[{
		// 上报类型
		name:'reportType'
	},{
		// 业务项ID
		name:'busItemId'
	},{
		// 业务范围ID
		name:'busScopeId'
	},{
		// 业务类型ID
		name:'busTypeId'
	},{
		// 业务项
		name:'busItemName'
	},{
		// 业务范围
		name:'busScopeName'
	},{
		// 业务类型
		name:'busTypeName'
	},{
		// 处理语言
		name:'dealLanguage'
	},{
		// 处理时限
		name:'procLimit',
		type:'float'
	},{
		// 反馈时限
		name:'feedbackLimit',
		type:'float'
	}]
});

/**
 * 处理结果基础资料设置页面列表grid的model
 */
Ext.define('BasicInfoSettingModel',{
	extend:'Ext.data.Model',
	fields:[{
		// 业务类型
		name:'busType'
	},{
		// 业务类型ID
		name:'id'
	},{
		// 处理语言
		name:'dealLanguage'
	},{
		// 处理时限
		name:'procLimit',
		type:'float'
	},{
		// 反馈时限
		name:'feedbackLimit',
		type:'float'
	}]
});