BasicInfoStore = function(){
};

/**
 *  投诉业务项添加列表grid的Store
*/
Ext.define('ComplaintBusinessItemsStore',{
	extend:'Ext.data.Store',
	model:'BusinessItemsModel',
	proxy : {
		type : 'ajax',
		url : '../complaint/showBusItemByType.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'complaintBusinessItemList'
		}
	}
});

/**
 *  异常业务项添加列表grid的Store
*/
Ext.define('UnusualBusinessItemsStore',{
	extend:'Ext.data.Store',
	model:'BusinessItemsModel',
	proxy : {
		type : 'ajax',
		url : '../complaint/showBusItemByType.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'unusualBusinessItemList'
		}
	}
});

/**
 *  处理结果基础资料查询主页面列表grid的Store
*/
Ext.define('BasicInfoResultStore',{
	extend:'Ext.data.Store',
	model:'BasicInfoResultModel',
	pageSize : 20,
	proxy : {
		type : 'ajax',
		url : '../complaint/searchBasicInfo.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'basicInfoList',
			totalProperty : 'totalCount'
		}
	}
});

/**
 *  处理结果基础资料设置页面列表grid的Store
*/
Ext.define('BasicInfoSettingStore',{
	extend:'Ext.data.Store',
	model:'BasicInfoSettingModel',
	proxy : {
		type : 'ajax',
		url : '../complaint/loadBasicInfo.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'busTypes'
		}
	}
});

/**
 * 根据选定的业务类型，查询出相应的业务项的Store
 */
Ext.define('SelectBusinessItemsStore',{
	extend:'Ext.data.Store',
	model:'BasicInfoResultModel',
	proxy : {
		type : 'ajax',
		url : '../complaint/loadBusinessItems.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'basicInfoList'
		}
	}
});

/**
 * 业务项设置界面：点击提交按钮，向数据库中新增投诉和异常的业务项
 */
BasicInfoStore.prototype.addBusinessItems = function(param,successFn,failureFn){
	var url = '../complaint/addBusinessItems.action';
	DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * 处理结果基础资料设置界面：新基础资料--业务项修改,业务范围新增与修改,业务类型新增修改删除
 */
BasicInfoStore.prototype.operateBasicBusInfo = function(param,successFn,failureFn){
	var url = '../complaint/operateBasicBusInfo.action';
	DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * 处理结果基础资料查询主界面：点击查询按钮，根据返回的totalcount判断是否load表格数据
 */
BasicInfoStore.prototype.searchBasicInfo = function(param,successFn,failureFn){
	var url = '../complaint/searchBasicInfo.action';
	DpUtil.requestAjax(url,param,successFn,failureFn);
};

/**
 * 业务项设置界面：删除业务项
 */
BasicInfoStore.prototype.deleteBusItemByIds = function(param,successFn,failureFn){
	var url = '../complaint/deleteBusItemByIds.action';
	DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * 基础资料查询主界面：点击删除按钮，删除选中的基础资料
 */
BasicInfoStore.prototype.deleteBasicInfo = function(param,successFn,failureFn){
	var url = '../complaint/deleteBasicInfo.action';
	DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * 新基础资料：删除业务类型在操作基础资料页面中使用
 */
BasicInfoStore.prototype.deleteBusTypeByIdsInBusScopeVO = function(param,successFn,failureFn){
	var url = '../complaint/deleteBusTypeByIdsInBusScopeVO.action';
	DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};