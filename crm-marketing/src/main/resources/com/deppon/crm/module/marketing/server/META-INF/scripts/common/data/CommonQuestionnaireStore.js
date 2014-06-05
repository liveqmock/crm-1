/**
 * 问卷通用store
 */
var CommonQuestionnaireStore  = function(){

};

/**
 * 根据问卷id，查询问卷信息
 * @author 肖红叶
 * @时间 2014-4-09
 */
CommonQuestionnaireStore.prototype.searchQuestionnaireInfoList = function(param,successFn,failureFn){
	var url = '../marketing/searchQuestionnaireInfoList.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};

/**
 * 问卷问题信息列表Store
 * 肖红叶
 * 20140306
 */
Ext.define('QuestionInfoListStore',{
	extend:'Ext.data.Store',
	model:'QuestionInfoModel',
	proxy:{
		type:'ajax',
		url:'../marketing/searchQuestionListByQuestionIdList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'questionList'
		}
	}
});

/**
 * 营销历史记录中查看客户问卷答案列表Store
 * 肖红叶
 * 20140306
 */
Ext.define('QuestionAndAnswerInfoListStore',{
	extend:'Ext.data.Store',
	model:'QuestionInfoModel',
	proxy:{
		type:'ajax',
		url:'../marketing/viewQuestionnaireByVisitId.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'questionList'
		}
	}
});

/**
 * 问卷管理主页面查询结果表格Store
 * 肖红叶
 * 20140306
 */
Ext.define('QuestionnaireInfoListStore',{
	extend:'Ext.data.Store',
	model:'QuestionnaireInfoModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		url:'../marketing/searchQuestionnaireInfoList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'questionnaireList',
			totalProperty : 'totalCount'
		}
	}
});