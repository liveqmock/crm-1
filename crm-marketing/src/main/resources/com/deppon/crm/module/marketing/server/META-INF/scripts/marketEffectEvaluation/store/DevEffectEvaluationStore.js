/**
 * 营销开发效果评估
 */
DevEffectEvaluationStore = function(){
	
};

/**
 * 客户开发查询结果表格Store
 */
Ext.define('DevEffectEvaluationListStore',{
	extend:'Ext.data.Store',
	model:'DevEffectEvaluationListModel',
	pageSize:20,
//	sorters: [{
//        property : 'deptId',
//        direction: 'DESC'
//	}],
	proxy:{
		type:'ajax',
		url:'../marketing/queryDevEffectResultByCondition.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'custDevAssessList',
			totalProperty : 'totalCount'
		}
	}
});

/**
 * 开发：导出权限下所有数据到服务器端生成一个Excel报表文件
 */
DevEffectEvaluationStore.prototype.creatDevMarketEffectReportExcel = function(param,successFn,failureFn){
	var url = '../marketing/creatDevMarketEffectReportExcel.action';
	Ext.Ajax.request({
		url:url,
		params:param,
		//设置超时时间为5分钟
		timeout: 300000,
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				successFn(result);
			}else{
				failureFn(result);
			}
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
			failureFn(result);
		}
	});
};

/**
 * 开发：导出权限下所有数据到服务器端生成一个Excel报表文件
 */
DevEffectEvaluationStore.prototype.creatDevReportExcelByCondition = function(param,successFn,failureFn){
	var url = '../marketing/creatDevReportExcelByCondition.action';
	Ext.Ajax.request({
		url:url,
		params:param,
		//设置超时时间为5分钟
		timeout: 300000,
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				successFn(result);
			}else{
				failureFn(result);
			}
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
			failureFn(result);
		}
	});
};

/**
 * 开发：查询合计
 */
DevEffectEvaluationStore.prototype.searchSumCustDevByAuth = function(param,successFn,failureFn){
	var url = '../marketing/searchSumCustDevByAuth.action';
	Ext.Ajax.request({
		url:url,
		params:param,
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				successFn(result);
			}else{
				failureFn(result);
			}
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
			failureFn(result);
		}
	});
};