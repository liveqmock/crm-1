/**
 * 发送详情列表Model
 * 肖红叶
 * 20130928
 */
Ext.define('SendDetailInfoModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		// 手机号码
		name : 'phone',
		type : 'string'
	}, {
		// 发送状态
		name : 'status',
		type : 'string'  
	} , {
		//是否无效
		name : 'valid',
		type : 'string'  
	}, {
		//发送时间
		name : 'sendDate',
		type : 'Date',
		defaultValue : null,
		convert : DButil.changeLongToDate 
	}]
});

/**
 * 手机号文件列表Model
 * 肖红叶
 * 20130928
 */
Ext.define('FileInfoModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		// 文件名称
		name : 'fileName',
		type : 'string'
	}, {
		// 文件发送状态
		name : 'status',
		type : 'string'  
	} , {
		//发送开始时间
		name : 'beginDate',
		type : 'Date',
		defaultValue : null,
		convert : DButil.changeLongToDate  
	}, {
		//发送结束时间
		name : 'endDate',
		type : 'Date',
		defaultValue : null,
		convert : DButil.changeLongToDate 
	},{//任务ID
		name:'id',
		type:'string'
	}]
});