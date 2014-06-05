ClientBaseStore = function(){
	
};

/**
 * 客户群主页面查询结果表格Store

 */
Ext.define('clientBaseInfoListStore',{
	extend:'Ext.data.Store',
	model:'ClientBaseInfoModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		url:'../marketing/searchClientBaseByCondition.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'clientBaseList',
			totalProperty : 'totalCount'
		}
	}
});
 
/**
 * 二级行业store
 */
Ext.define('SecondTradeStore', {
    extend : 'Ext.data.Store',
    model : 'SecondTradeModel',
    proxy: {
        type : 'ajax',
        url : '../marketing/searchSecondTradesByTradesList.action',
        actionMethods : 'post',
        reader : {
            type : 'json',
            root : 'detailList'
        }
    }
});

/**
 * 查询部门
 */
Ext.define('WalkGoodDeptStore',{
    extend : 'Ext.data.Store',
    model : 'DeptModel',
    pageSize : 10,
    proxy : {
        type : 'ajax',
        url : '../marketing/searchDeptListByName.action',
        actionMethods : 'post',
        reader : {
            type : 'json',
            root : 'departmentList',
            totalProperty : 'totalCount'
        }
    }
});

/**
 * 部门线路
 */
Ext.define('UseWalkGoodsLineStore',{
    extend:'Ext.data.Store',
    model:'LineDeptModel'
});

/**
 * 部门树store
 */
Ext.define('DeptTreeStore', {
    extend : 'Ext.data.TreeStore'

});


/**
 * 部门树数据加载
 */
ClientBaseStore.prototype.loadLineDeptTree=function(param,successFn,failureFn){
      Ext.Ajax.request({
		url:'../marketing/loadDeliverOrArriveTree.action',
		params:param,
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
	
}
/**
 * 根据Id查询客户群

 */

ClientBaseStore.prototype.searchClientBaseById = function(param,successFn,failureFn){
    var url = '../marketing/searchClientBaseById.action';
    DButil.requestAjax(url,param,successFn,failureFn);
};

/**
 * 删除客户群

 */
ClientBaseStore.prototype.deleteClientBaseById = function(param,successFn,failureFn){
    var url = '../marketing/deleteClientBaseById.action';
    DButil.requestAjax(url,param,successFn,failureFn);
};

/**
 * 客户名称是否重复

 */
ClientBaseStore.prototype.checkClientBase = function(param,successFn,failureFn){
    var url = '../marketing/checkClientBase.action';
    DButil.requestAjax(url,param,successFn,failureFn);
};

/**
 * 客户群保存

 */
ClientBaseStore.prototype.saveClientBase = function(param,successFn,failureFn){
    var url = '../marketing/saveClientBase.action';
    DButil.requestJsonAjax(url,param,successFn,failureFn);
};
	
/**
 * 客户群更新

 */
ClientBaseStore.prototype.updateClientBase = function(param,successFn,failureFn){
    var url = '../marketing/updateClientBase.action';
    DButil.requestJsonAjax(url,param,successFn,failureFn);
};	
/**
 * 查询详情用查询二级行业

 */
ClientBaseStore.prototype.searchSecondTradesToString = function(param,successFn,failureFn){
    var url = '../marketing/searchSecondTradesToString.action';
    DButil.requestJsonAjax(url,param,successFn,failureFn);
};	

