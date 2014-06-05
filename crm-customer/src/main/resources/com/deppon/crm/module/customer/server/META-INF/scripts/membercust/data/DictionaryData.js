// 判断是否导入测试数据
(function() {
	var dictionaryDataTest = "../scripts/membercust/test/DictionaryDataTest.js";
	if (CONFIG.get('TEST')) {
		document.write('<script type="text/javascript" src="'
				+ dictionaryDataTest + '"></script>');
	}
})();

/**
 * 数据字典模型model
 */
Ext.define('DictionaryModel', {
	extend : 'Ext.data.Model',
	fields : [ 'code', 'codeDesc', 'id', 'parentId', 'parentCode',
			'invalidTime' ]
});

/**
 * 数据字典详情 store
 */
Ext.define('DictionaryStore', {
	extend : 'Ext.data.Store',
	model : 'DictionaryModel',
	autoLoad : false,
	proxy : {
		type : 'ajax',
		url : 'searchDictionary.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			root : 'list'
		}
	}
});

Ext.define('DictionaryManagerData',{
	workFlowManagerStore:null,
	workFlowNameStore:null,//工作流名称
	workFlowStateStore:null,//流程状态
	approveDataStore:null,//审批数据修改对象	Store
	examineRecordStore:null,//审批历史记录	Store
	//工作流查询结果store
	getMyWorkFlowDealManagerStore: function() {
		return this.workFlowManagerStore;
	},
	//查询数据字典
	searchDictionary: function(params,successFn,failFn){
		var searchDictionaryUrl='searchDictionary.action';
		DpUtil.requestJsonAjax(searchDictionaryUrl,params,successFn,failFn);
	},
	//保存二级行业
	saveSecondTrade: function(params,successFn,failFn){
		var saveSecondTrade='saveSecondTrade.action';
		DpUtil.requestJsonAjax(saveSecondTrade,params,successFn,failFn);
	},
	//保存二级行业
	searchSecondTradeByParentId: function(params,successFn,failFn){
		var searchSecondTradeByParentId='searchSecondTradeByParentId.action';
		DpUtil.requestJsonAjax(searchSecondTradeByParentId,params,successFn,failFn);
	},
	// 客户行业
	getTradeStore : function() {
		if (this.tradeStore == null) {
			this.tradeStore = getDataDictionaryByName(DataDictionary,'TRADE');
//			this.tradeStore = Ext.create('TradeStore', {
//				data : DataDictionary.TRADE
//			});
		}
		return this.tradeStore;
	},
	//获得当前登录用户
	getCurrentUser:function(){
		Ext.Ajax.request({
			url:'../common/queryUserInfo.action',
			async:false,
			jsonData:{},
			success:function(response){
				var result = Ext.decode(response.responseText);
				if(result.success){
					CurrentUser = result.user;
				}else{
					Ext.Msg.alert('温馨提示',result.message)
				}
			},
			failure:function(response){
				var result = Ext.decode(response.responseText);
				Ext.Msg.alert('温馨提示',result.message)
			}
		});
	}
});
