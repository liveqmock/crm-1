/**
 *问卷管理
 */
QuestionnaireStore = function(){
	
};



/**
 * Description:根据问卷状态和问卷id数组删除问卷信息
 * @author 肖红叶
 * @date 2013-03-12
 * @return
 */
QuestionnaireStore.prototype.deleteQuestionnaire = function(param,successFn,failureFn){
	var url = '../marketing/deleteQuestionnaire.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};

/**
 * <p>
 * 根据条件查询问题列表
 * <p>
 * @author 盛诗庆
 * @时间 2014-3-7
 */
Ext.define('QuestionGridStore',{
	extend:'Ext.data.Store',
	model:'QuestionModel',
	pageSize:10,
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
 * 根据问卷id查询已选问题列表store
 * <p>
 * @author 肖红叶
 * @时间 2014-3-12
 */
Ext.define('ChooseQuestionGridStore',{
	extend:'Ext.data.Store',
	model:'QuestionModel',
	proxy:{
		type:'ajax',
		url:'../marketing/searchQuestionListByQuestionnaireId.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'questionList'
		}
	}
});

/**
 * Description:根据问卷id查询已选问题列表
 * @author 肖红叶
 * @date 2013-03-12
 * @return
 */
QuestionnaireStore.prototype.searchQuestionListByQuestionnaireId = function(param,successFn,failureFn){
	var url = '../marketing/searchQuestionListByQuestionnaireId.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};

/**
 * Description:新增问卷
 * @author 肖红叶
 * @date 2013-03-12
 * @return
 */
QuestionnaireStore.prototype.createQuestionnaire = function(param,successFn,failureFn){
	var url = '../marketing/createQuestionnaire.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * Description:修改问卷
 * @author 肖红叶
 * @date 2013-03-12
 * @return
 */
QuestionnaireStore.prototype.updateQuestionnaire = function(param,successFn,failureFn){
	var url = '../marketing/updateQuestionnaire.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * Description:根据问题id列表查询问题信息及选项信息
 * @author 肖红叶
 * @date 2013-03-12
 * @return
 */
QuestionnaireStore.prototype.searchQuestionListByQuestionIdList = function(param,successFn,failureFn){
	var url = '../marketing/searchQuestionListByQuestionIdList.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};
/**
 * Description:服务器端创建问卷统计结果
 * @author 盛诗庆
 * @date 2013-03-12
 * @return
 */
QuestionnaireStore.prototype.createStaticsExcel = function(param,successFn,failureFn){
	var url = '../marketing/createStaticsExcel.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};
/**
 * Description:服务器端创建问卷详细结果
 * @author 盛诗庆
 * @date 2013-03-12
 * @return
 */
QuestionnaireStore.prototype.createQuestionnaireDetailExcel = function(param,successFn,failureFn){
	var url = '../marketing/createQuestionnaireDetailExcel.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};

/**
 * 选项Store
 * 肖红叶
 * 20140313
 */
Ext.define('QestionOptionStore',{
	extend:'Ext.data.Store',
	model:'QestionOptionModel',
	data:null
});

/**
 * 查看问卷统计结果Store
 * 肖红叶
 * 20140321
 */
Ext.define('StatisticsQuestionInfoListStore',{
	extend:'Ext.data.Store',
	model:'QuestionInfoModel',
	proxy:{
		type:'ajax',
		url:'../marketing/staticsQuestionnaireResultBySurveyId.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'questionList'
		}
	}
});