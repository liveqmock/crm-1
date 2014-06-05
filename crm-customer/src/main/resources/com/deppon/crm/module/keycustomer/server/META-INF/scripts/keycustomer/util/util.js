 var KcUtil = function(){
}
/**
 * 客户360视图button 展示window方法
 * @type 
 */
KcUtil.cust360View = function(custNumber){
	//调用customer目录下的JS方法
	CustviewUtil.openSimpleCustview(custNumber);
}
/**
*将查询条件中的form数据转换成params
*/
KcUtil.transform = function(form,type){
	var belongDept;
	if(form.findField('belongDept')){
		belongDept = form.findField('belongDept').getValue();
	}
	var custName;
	if(form.findField('custName')){
		custName = form.findField('custName').getValue();
	}
	var contactName;
	if(form.findField('contactName')){
		contactName = form.findField('contactName').getValue();
	}
	var custNum;
	if(form.findField('custNum')){
		custNum = form.findField('custNum').getValue();
	}
	var contactNum;
	if(form.findField('contactNum')){
		contactNum = form.findField('contactNum').getValue();
	}
	var approvalStatus;
	if(form.findField('approvalStatus')){
		approvalStatus = form.findField('approvalStatus').getValue();
	}
	var startDate;
	if(form.findField('startDate')){
		startDate = form.findField('startDate').getValue();
	}
	var endDate;
	if(form.findField('endDate')){
		endDate = form.findField('endDate').getValue();
	}
	var limit;
	if(form.findField('limit')){
		limit = form.findField('limit').getValue();
	}
	var start;
	if(form.findField('start')){
		start = form.findField('start').getValue();
	}
	return {
		'condition.belongDept':belongDept
		,'condition.custNum':custNum
		,'condition.custName':custName
		,'condition.contactName':contactName
		,'condition.contactNum':contactNum
		,'condition.approvalStatus':approvalStatus
		,'condition.startDate':startDate
		,'condition.endDate':endDate
		,'condition.limit':limit
		,'condition.start':start
		,'condition.type':type
	}
}
/**
*ajax请求
*/
KcUtil.requestJsonAjax = function(url,params,successFn,failFn,async)
{
	if(async===undefined){
		async=true;
	}
	Ext.Ajax.request({
		url:url,
		async:async,
		jsonData:params,
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				successFn(result);
			}else{
				failFn(result);
			}
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
			failFn(result);
		}
	});
};
KcUtil.requestAjax = function(url,params,successFn,failFn,async)
{
	if(async===undefined){
		async=true;
	}
	Ext.Ajax.request({
		url:url,
		params:params,
		async:async,
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				successFn(result);
			}else{
				failFn(result);
			}
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
			failFn(result);
		}
	});
};
/**
 * 申请工作流
 */
KcUtil.applyWorkflow = function(params,successFn,failFn,async){
	var url = '../keycustomer/applyWorlflow.action'
	KcUtil.requestJsonAjax(url,params,successFn,failFn,async);
}
/**
*获取数据字典的store
*/
KcUtil.getStore = function(storeId,fields,data) {
	var store = Ext.data.StoreManager.lookup(storeId);
	if(Ext.isEmpty(data)){
		data = [];
	}
	if(!Ext.isEmpty(fields)){
		if(store===undefined){
			store = Ext.create('Ext.data.Store', {
				storeId:storeId,
			    fields:fields,
			    data:data
		     });
		}
	}
	return store;
};
KcUtil.verifyKeyStatus = function(params,successFn,failFn){
	var url = '../keycustomer/verifyKeyStatus.action';
	KcUtil.requestJsonAjax(url,params,successFn,failFn);
}
KcUtil.exitHadlle = function(params,successFn,failFn){
	var url = '../keycustomer/exitHadlle.action';
	KcUtil.requestJsonAjax(url,params,successFn,failFn);
}
KcUtil.changeCodeToCodeDesc = function(value, dataDictionary) {
	if (value != null && dataDictionary != null) {
		for ( var i = 0; i < dataDictionary.length; i++) {
			if (value == dataDictionary[i].code) {
			     return dataDictionary[i].codeDesc;
			}
		}
	} else {
	   return null;
	}
};
KcUtil.verifyRole = function(curruser){
	if(Ext.Array.contains(curruser.roleids,'1004')){
		return true;
	}else{
		return false;
	}
}
/**
 * 解析近三个月发货金额是否有小于10000,全大于10000返回false
 * @param {} num
 */
KcUtil.decideAmountOfSignMent = function(num){
	if(!num){
		return true;
	}
	var temp = false;
	for(var i=0;i<num.split('\\').length;i++){
		if(Number(num.split('\\')[0])<1000){
			return true;
		}
	}
	return temp;
}