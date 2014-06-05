


/**
 * 业务项添加列表grid的model
 */
Ext.define('BusinessItemsModel',{
	extend:'Ext.data.Model',
	fields:[
		{name:'busItemName'},/*业务项*/
		{name:'reportType'},/*上报类型*/
		{name:'busItemId'}/*业务项ID*/
	]
});

/**
 * 处理结果基础资料查询主页面列表grid的model
 */
Ext.define('BasicInfoResultModel',{
	extend:'Ext.data.Model',
	fields:[
		{name:'reportType'},/*上报类型*/
		{name:'busItemId'},/*业务项ID*/
		{name:'busScopeId'},/*业务范围ID*/
		{name:'busTypeId'},/*业务类型ID*/
		{name:'busItemName'},/*业务项*/
		{name:'busScopeName'},/*业务范围*/
		{name:'busTypeName'},/*业务类型*/
		{name:'dealLanguage'},/*处理语言*/
		{name:'reworpusType'},/*奖罚类型*/
		{name:'feedbackLimit'}/*反馈时限*/
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
 * 工单责任- 基础资料 model
 */
Ext.define('DutyBasicLevelModel',{
	extend:'Ext.data.Model',
	fields:[
	      {name:'id'}/*工单id*/
		 ,{name:'basciLevelName',type:'String'}/*名称*/
	     ,{name:'type',type:'String'}/*上报类型*/
	]
});