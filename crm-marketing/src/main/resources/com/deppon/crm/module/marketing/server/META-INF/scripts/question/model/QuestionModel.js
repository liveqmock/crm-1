/**
 * @description 问题model
 * @author 盛诗庆
 * @date 2014-03-05
 * @revision none
 */
Ext.define('QuestionModel',{
	extend : 'Ext.data.Model',
	fields : [{
		name : 'id' //问题id
	},{
		name : 'questionCode'//问题编码
	},{
		name : 'questionTitle' //问题标题
	},{
		name : 'useRange' //问题适用范围
	},{
		name : 'questionType' //问题类型
	},{
		name : 'createDate', //问题创建时间
		type: 'Date',
		defaultValue:null,
		convert: DpUtil.changeLongToDate
	},{
		name : 'createUser' //问题创建人姓名
	},{
		name : 'createUserId' //创建人工号
	},{
		name : 'modifyUser'//修改人姓名
	},{
		name : 'lastModifyUserId'//修改人工号
	},{
		name : 'modifyDate',//修改时间
		type: 'Date',
		defaultValue:null,
		convert: DpUtil.changeLongToDate
	},{
		name : 'elseOption' //是否允许填空
	},{
		name : 'questionContent' //问题说明
	},{
		name : 'frequency' //采用次数
	},{//选项集合
		name : 'options',
		type : 'Array'
	}]
});
/**
 * @description 问题选项model，针对单选或者多选
 * @author 盛诗庆
 * @date 2014-03-05
 * @revision none
 */
Ext.define('OptionModel',{
		extend : 'Ext.data.Model',
		fields :[{
			name : 'id',//选项id
		},{
			name : 'questionFid',//所属问题FID
		},{
			name : 'optionDes',//选项描述，选项的具体内容
		},{
			name : 'creatorId',//创建人工号
		},{
			name : 'createDate',//创建时间
		},{
			name : 'modifierId',//修改人工号
		},{
			name : 'modifyDate',//最后修改日期
		},{
			name : 'optionSeq' //选项顺序
		}]
});
/**
 * @description 问题查询条件model
 * @author 盛诗庆
 * @date 2014-03-07
 * @revision none
 */
Ext.define('QuestionQueryCondition',{
		extend : 'Ext.data.Model',
		fields :[{
			name : 'questionTitle',//问题标题
		},{
			name : 'useRange',//适用范围
		},{
			name : 'questionType',//问题类型
		},{
			name : 'createStartDate',//开始创建时间
		},{
			name : 'createEndDate',//结束创建时间
		}]
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
	},{//创建时间
		name : 'createDate',
		type: 'Date',
		defaultValue:null,
		convert: DButil.changeLongToDate
	},{// 最后修改人
		name : 'modifyUser',
		type : 'string'
	},{
		name : 'createUser' //问卷创建人姓名
	}]
});
