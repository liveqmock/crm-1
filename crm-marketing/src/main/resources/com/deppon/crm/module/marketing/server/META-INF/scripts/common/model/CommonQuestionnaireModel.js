/**
 * 20140306
 * 问题选项model
 * 肖红叶
 */
Ext.define('QestionOptionModel', {
    extend: 'Ext.data.Model',
    fields: [{// 选项id
		name : 'id',
		type : 'string'
	},{//选项编号
    	name: 'optionSeq',      
    	type: 'int'
    },{//选项内容描述
    	name: 'optionDes',
    	type:'string'
    },{//选中占比
    	name: 'percentage',    
    	type: 'float'
    },{//是否被选中
    	name:'isSelected',
    	type:'string'
    },{//简答题答案或者其他选项答案
    	name : 'answer',
    	type : 'string'
    }]
});

/**
 * 20140306
 * 问题信息model
 * 肖红叶
 */
Ext.define('QuestionInfoModel', {
    extend: 'Ext.data.Model',
    fields: [{// 问题id
		name : 'id',
		type : 'string'
	},{//问题序号
    	name: 'questionSeq',   
    	type: 'string'
    },{//问题标题
    	name: 'questionTitle', 
    	type: 'string'
    },{//适用范围
    	name: 'useRange', 
    	type: 'string'
    },{//问题类型
    	name: 'questionType', 
    	type: 'string'
    },{//是否有其他选项
    	name: 'elseOption', 
    	type: 'string'
    },{//受访总人数
    	name: 'respondents', 
    	type: 'int'
    }],
    // 引入另外一个model，实现一对多关联
    hasMany: {model: 'QestionOptionModel', name: 'options'}
});

/**
 * 20140306
 * 问卷信息查询结果model
 * 肖红叶
 */
Ext.define('QuestionnaireInfoModel', {
	extend : 'Ext.data.Model',
	fields : [{// 问卷编号
		name : 'questionnaireCode',
		type : 'string'
	},{// 问卷id
		name : 'id',
		type : 'string'
	},{// 问卷名称
		name : 'questionnaireName',
		type : 'string'
	},{// 问卷描述
		name : 'desc',
		type : 'string'
	},{// 适用范围
		name : 'useRange',
		type : 'string'
	},{// 问卷状态
		name : 'status',
		type : 'string'
	},{// 采用此数据
		name : 'useTimes',
		type : 'string'
	},{//创建时间
		name : 'createDate',
		type: 'Date',
		defaultValue:null,
		convert: DButil.changeLongToDate
	},{
		name : 'createUser' //问卷创建人姓名
	},{
		name : 'modifyDate'//修改时间
	},{// 最后修改人
		name : 'modifyUser',
		type : 'string'
	},{//生效时间
		name : 'effectiveTime',
		type: 'Date',
		defaultValue:null,
		convert: DButil.changeLongToDate
	},{//失效时间
		name : 'invalidTime',
		type: 'Date',
		defaultValue:null,
		convert: DButil.changeLongToDate
	},{//计划主题
		name : 'planTopic',
		type : 'string'
	},{//回访id
		name : 'visitId',
		type : 'string'
	},{//联系人姓名
		name : 'linkManName',
		type : 'string'
	},{//营销方式
		name : 'marketingWay',
		type : 'string'
	},{//营销人姓名
		name : 'executorName',
		type : 'string'
	},{//营销人时间
		name : 'marketingDate',
		type: 'Date',
		defaultValue:null,
		convert: DButil.changeLongToDate
	}]
});

/**
 * 20140329
 * 回访问卷客户答案明细
 * 肖红叶
 */
Ext.define('AnswerDetailInfoModel', {
    extend: 'Ext.data.Model',
    fields: [{// 答案明细id
		name : 'id',
		type : 'string'
	},{//问卷答案信息主表ID
    	name: 'answerMainInfoId',      
    	type: 'string'
    },{//选项id
    	name: 'answer',
    	type:'string'
    },{//答案备注
    	name: 'answerRemark',    
    	type: 'string'
    }]
});

/**
 * 20140329
 * 回访问卷客户答案主信息
 * 肖红叶
 */
Ext.define('AnswerMainInfoModel', {
    extend: 'Ext.data.Model',
    fields: [{// 答案主信息id
		name : 'id',
		type : 'string'
	},{//问卷id
    	name: 'questionnaireId',   
    	type: 'string'
    },{//客户id
    	name: 'custId', 
    	type: 'string'
    },{//问题id
    	name: 'questionId', 
    	type: 'string'
    },{//回访id
    	name: 'visitId', 
    	type: 'string'
    }],
    // 答案明细
    hasMany: {model: 'AnswerDetailInfoModel', name: 'answerList'}
});
