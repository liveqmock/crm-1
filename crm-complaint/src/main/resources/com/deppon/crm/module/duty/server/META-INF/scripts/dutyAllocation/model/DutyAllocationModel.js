/**
 * 工单责任划分查询结果Model
 */
Ext.define('DutyAllocationResultModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		//接入时间
		name : 'receiveTime',
		convert: DpUtil.changeLongToDate,
		type:'Date'
	}, {
		//接入人
		name : 'receiveUser'
	}, {
		//凭证号
		name : 'voucherNumber'
	}, {
		//处理编号
		name : 'dealNumber'
	}, {
		//重复工单码
		name : 'rebindNo'
	}, {
		//上报类型
		name : 'reportType'
	}, {
		//来电人
		name : 'caller'
	}, {
		//优先级别
		name : 'pririty'
	}, {
		//处理状态
		name : 'proStatus'
	}, {
		//上报人
		name : 'reporter'
	}, {
		//上报时间
		name : 'reportTime',
		convert: DpUtil.changeLongToDate,
		type:'Date'
	}, {
		//调查结果
		name : 'surveyResult'
	}]
});

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
Ext.define('ProcResultModel',{
	extend:'Ext.data.Model',
	fields:[
		{name:'fid'},/*上报类型*/
		{name:'busItemName'},/*业务项*/
		{name:'busScopeName'},/*业务范围*/
		{name:'busType'},/*业务类型*/
		{name:'deallan'},/*处理语言*/
		{name:'reworpusType'},/*奖罚类型*/
		{name:'feedbacklimit'}/*反馈时限*/
	]
});

/**
 * 工单责任处理结果 model
 */
Ext.define('DutyResultModel',{
	extend:'Ext.data.Model',
	fields:[
		{name:'id'},/*业务类型*/
	    {name:'dutyId'},/*工单责任ID*/
	    {name:'divideType'},/*划分类型*/
	    {name:'busAspectId'},/*业务项*/
	    {name:'busScopeId'},/*业务范围*/
	    {name:'virtualCode',type:'String'},/*虚拟ID*/
		{name:'busAspect'},/*业务项*/
	    {name:'busScope'},/*业务范围*/
	    {name:'busTypeId'},/*业务类型*/
		{name:'busType'},/*业务类型*/
		{name:'dealLanguage'},/*处理语言*/
		{name:'dutyPerId'},/* 部门类型-个人、部门*/
		{name:'dutyPerName'},/*责任人名称*/
		{name:'reworpusType'},/*奖罚类型*/
		{name:'dutyDeadLine',convert:DpUtil.changeLongToDate,type:'Date'},/*反馈时限*/
		{name:'surveyResult'},/*调查结果*/
		{name:'dutyPerName'},/*任务部门*/
		{name:'dutyStatus',type:'string',defaultValue:null}/*责任状态*/,
	    {name:'old_dealType',type:'string'},//处理类型 个人 或 部门 辅助字段 表示选择之前的类型
	    {name:'old_dutyPerId',type:'string'},/*辅助的 部门类型-个人、部门*/
	    {name:'jobCode',type:'string'},//处理类型 个人 或 部门 的值 辅助字段 表示选择之前的类型
	    {name:'dutyDepartmentCC',type:'string'},//是否为 400 责任部门，0 普通部门，1特殊部门。-1  三个经营本部外的部门
	    {name:'dutyDeptId',type:'string'},//责任部门编号/个人所在责任部门编号
	    {name:'businessLink',type:'string'} /*业务环节*/
	]
});


/**
 * 工单责任 - 通知对象 model
 */
Ext.define('DutyBulletinModel',{
	extend:'Ext.data.Model',
	fields:[
		 {name:'id',type:'String'},/*主键*/
		 {name:'virtualCode',type:'String'},/*虚拟ID*/
	     {name:'userNo',type:'String'},/*通知对象工号*/
		 {name:'userName',type:'String'},/*通知对象名称*/
		 {name:'userContact',type:'String'},/*联系电话*/
		 {name:'deptId',type:'String'},/*部门ID*/
		 {name:'deptName',type:'String'},/*部门*/
		 {name:'busType',type:'String',defaultValue:''},/*业务部门-默认为手动添加*/
		 {name:'divideType',type:'string'},//处理类型 个人 或 部门  默认个人
		 {name:'userPosition',type:'string'},//职位
	     {name:'jobCode',type:'string'}//处理类型 个人 或 部门 的值 辅助字段 表示选择之前的类型
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