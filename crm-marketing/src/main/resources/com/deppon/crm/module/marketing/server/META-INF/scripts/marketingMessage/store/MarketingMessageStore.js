/**
 *营销短信
 */
MarketingMessageStore = function(){
};

/**
 * 手机号文件列表store
 * 肖红叶
 * 20130928
 */
Ext.define('FileInfoStore', {
	extend : 'Ext.data.Store',
	model : 'FileInfoModel',
//	data:[{
//		fileName:'红叶1.doc',status:'0',beginDate:new Date(),endDate:new Date()
//	},{
//		fileName:'红叶2.doc',status:'1',beginDate:new Date(),endDate:new Date()
//	},{
//		fileName:'红叶3.doc',status:'2',beginDate:new Date(),endDate:new Date()
//	},{
//		fileName:'红叶4.doc',status:'3',beginDate:new Date(),endDate:new Date()
//	}]
	pageSize : 20,
	proxy : {
		type : 'ajax',
		url : '../marketing/searchTaskList.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'taskList',
			totalProperty : 'totalCount'
		}
	}
});
/**
 * 发送详情列表Model
 * 肖红叶
 * 20130928
 */
Ext.define('SendDetailInfoStore', {
	extend : 'Ext.data.Store',
	model : 'SendDetailInfoModel',
//	data:[{
//		phone:'159215934',status:'0',valid:'0',sendDate:new Date()
//	},{
//		phone:'15921593412',status:'1',valid:'1',sendDate:new Date()
//	},{
//		phone:'15921593413',status:'2',valid:'1',sendDate:new Date()
//	}]
	pageSize : 20,
	proxy : {
		type : 'ajax',
		url : '../marketing/searchPhoneList.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'phoneList',
			totalProperty : 'totalCount'
		}
	}
});

/**
 * Description:保存上传的文件名、文件路径、短信内容
 * @author 肖红叶
 * @date 2013-09-28
 * @return
 */
MarketingMessageStore.prototype.saveMessageFile = function(param,successFn,failureFn){
	var url = '../marketing/saveMessageFile.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};