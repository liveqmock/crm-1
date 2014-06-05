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
	},{//问题序号
    	name: 'questionSeq',   
    	type: 'int'
    },{
		name : 'questionCode'//问题编码
	},{
		name : 'questionTitle' //问题标题
	},{
		name : 'useRange' //问题适用范围
	},{
		name : 'questionType' //问题类型
	},{
		name : 'createDate' //问题创建时间
	},{
		name : 'createUser' //问题创建人姓名
	},{
		name : 'createUserId' //创建人工号
	},{
		name : 'modifyUser'//修改人姓名
	},{
		name : 'lastModifyUserId'//修改人工号
	},{
		name : 'modifyDate'//修改时间
	},{
		name : 'elseOption' //是否允许填空
	},{
		name : 'questionContent' //问题说明
	},{
		name : 'frequency' //采用次数
	}]
});

/**
 * @description 问题问卷映射model
 * @author 肖红叶
 * @date 2014-03-05
 * @revision none
 */
Ext.define('QuestionMapperModel',{
	extend : 'Ext.data.Model',
	fields : [{
		name : 'id' //id
	},{
		name : 'questionnaireId' //问卷id
	},{
		name : 'questionId'//问题id
	},{
		name : 'questionSeq',   
    	type: 'int' //问题序号
	}]
});