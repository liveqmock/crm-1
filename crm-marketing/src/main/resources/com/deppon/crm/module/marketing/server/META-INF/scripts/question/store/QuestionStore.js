/**
 * <p>
 * 根据条件查询问题列表
 * <p>
 * @author 盛诗庆
 * @时间 2014-3-7
 */
QuestionStore = function(){
	
};
Ext.define('QuestionGridStore',{
	extend:'Ext.data.Store',
	model:'QuestionModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		url:'../marketing/searchQuestionsByCondition.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'questionList',
			totalProperty : 'totalCount'
		}
	}
});
/**
 * <p>
 * 根据问题id查询问题选项
 * <p>
 * @author 盛诗庆
 * @时间 2014-3-7
 */
Ext.define('OptionsRemoteStore',{
	extend:'Ext.data.Store',
	model:'OptionModel',
	proxy:{
		type:'ajax',
		url:'../marketing/searchOptionsByQuestion.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'OptionList',
			totalProperty : 'totalCount'
		}
	}
});
/**
 * <p>
 * 加载本地问题选项数据
 * <p>
 * @author 盛诗庆
 * @时间 2014-3-12
 */
Ext.define('OptionsLocalStore',{
	extend:'Ext.data.Store',
	model:'OptionModel',
	sortInfo:{field: 'optionSeq', direction: 'ASC'},//按照选项序号升序排序
});
/**
 * <p>
 * 保存问题
 * <p>
 * @author 盛诗庆
 * @时间 2014-3-12
 */
QuestionStore.prototype.createQuestion = function(param,successFn,failureFn){
	var url = '../marketing/createQuestion.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};
/**
 * <p>
 * 修改问题
 * <p>
 * @author 盛诗庆
 * @时间 2014-3-12
 */
QuestionStore.prototype.modifyQuestion = function(param,successFn,failureFn){
	var url = '../marketing/updateQuestion.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};
QuestionStore.prototype.sendMail = function(param,successFn,failureFn){
	var url = '../marketing/sendMail.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};
/**
 * <p>
 * 删除问题
 * <p>
 * @author 盛诗庆
 * @时间 2014-3-12
 */
QuestionStore.prototype.deleteQuestion = function(param,successFn,failureFn){
	var url = '../marketing/deleteQuestion.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};
/**
 * <p>
 * 根据问题id查询引用该问题问卷详情
 * <p>
 * @author 盛诗庆
 * @时间 2014-3-12
 */
QuestionStore.prototype.searchQuestionnaireByQuestionId = function(param,successFn,failureFn){
	var url = '../marketing/searchQuestionnaireByQuestionId.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};
/**
 * 问卷store
 * 盛诗庆
 * 20140313
 */
Ext.define('QuestionnaireStore',{
	extend:'Ext.data.Store',
	model:'QuestionnaireInfoModel',
	pageSize : 10
});
