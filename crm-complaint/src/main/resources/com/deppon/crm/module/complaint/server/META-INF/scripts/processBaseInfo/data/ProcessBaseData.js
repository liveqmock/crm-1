ProcessBaseData = function() {

};

/**
 * 基础层级Model
 */
Ext.define('BasciLevelViewModel', {
	extend : 'Ext.data.Model',
	fields : [{
		// id
		name : 'fatherId',
		type : 'int'
	},{
		// 父id
		name : 'childId',
		type : 'int'
	},{
		//范围名称
		name : 'fatherBLName',
		type : 'string'
	},{
		// 类型名称
		name : 'chiledBLName',
		type : 'string'
	},{
		// 上报类型
		name : 'type',
		type : 'string'
	}]
});

/**
 * 基础层级Model
 */
Ext.define('BasciLevelModel', {
	extend : 'Ext.data.Model',
	fields : [{
		// id
		name : 'fid',
		type : 'int'
	},{
		// 名称
		name : 'bascilevelname',
		type : 'string'
	},{
		// 上报类型
		name : 'type',
		type : 'string'
	}]
});

/**
 * 基础资料处理结果表Model
 */
Ext.define('ProcResultModel', {
	extend : 'Ext.data.Model',
	fields : [{
		// id
		name : 'fid',
		type : 'int'
	},{
		//反馈时限
		name : 'feedbacklimit',
		type : 'number'
	},{
		//处理时限
		name : 'proclimit',
		type : 'string'
	},{
		//处理事项
		name : 'deallan',
		type : 'string'
	}]
});

/**.
 * <p>
 * 投诉
 * <p>
 * @author 张登
 * @时间 2012-3-26
 */
Ext.define('SearchComplaintDataStore',{
	extend:'Ext.data.Store',
	model:'BasciLevelViewModel',
	proxy:{
		type:'ajax',
		url:'../complaint/searchComplaintBasciLevel.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'complaintBasciLevelList'
		}
	}
});

/**.
 * <p>
 * 异常
 * <p>
 * @author 张登
 * @时间 2012-3-26
 */
Ext.define('SearchCxceptionDataStore',{
	extend:'Ext.data.Store',
	model:'BasciLevelViewModel',
	proxy:{
		type:'ajax',
		url:'../complaint/searchExceptionBasciLevel.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'exceptionBasciLevelList'
		}
	}
});


/**.
 * <p>
 * 查询、修改业务类型
 * <p>
 * @author 张登
 * @时间 2012-3-26
 */
Ext.define('SearchFoundationData',{
	extend:'Ext.data.Store',
	model:'ProcResultModel',
	proxy:{
		type:'ajax',
		url:'../complaint/searchFoundationData.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'procResult'
		}
	}
});

/**.
 * <p>
 * 查询业务范围store
 * <p>
 * @author 张登
 * @时间 2012-3-26
 */
Ext.define('BasciLevelStore',{
	extend:'Ext.data.Store',
	model:'BasciLevelModel',
	proxy:{
		type:'ajax',
		url:'../complaint/searchBasciLevel.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'listBasciLevel'
		}
	}
});

/**.
 * <p>
 * 投诉
 * <p>
 * @author 张登
 * @时间 2012-3-26
 */
Ext.define('SearchWinComplaintDataStore',{
	extend:'Ext.data.Store',
	model:'BasciLevelModel'
});

/**.
 * <p>
 * 异常
 * <p>
 * @author 张登
 * @时间 2012-3-26
 */
Ext.define('SearchWinCxceptionDataStore',{
	extend:'Ext.data.Store',
	model:'BasciLevelModel'
});


/**.
 * <p>
 * 保存处理结果
 * <p>
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-6
 */
ProcessBaseData.prototype.saveFoundation = function(param,successFn,failureFn){
	var url = '../complaint/saveFoundation.action';
	DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 保存业务范围
 * <p>
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-6
 */
ProcessBaseData.prototype.saveBasciLevel = function(param,successFn,failureFn){
	var url = '../complaint/saveBasciLevel.action';
	DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * 基础资料处理结果表之业务范围Model
 */
Ext.define('BasciLevelModel', {
	extend : 'Ext.data.Model', 
	fields : [
		{name:'fid',type:'number',defaultValue:0}/*编号*/
		,{name:'bascilevelname',type:'String'}/*层级名称*/
		,{name :'type',type : 'string'}/*上报类型*/
	]
});

/**.
 * <p>
 * 基础资料处理结果表之业务范围Modelstore
 * <p>
 * @时间 2012-7-9
 */
Ext.define('BasciLevelScopeStore',{
	extend:'Ext.data.Store',
	model:'BasciLevelModel',
	proxy:{
		type:'ajax'
		,url:'searchBusinessScopeList.action'
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'basciLevelList'
		}
	}
});