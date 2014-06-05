/**
 * 营销维护效果评估
 */
MaintainEffectEvaluationStore = function(){
	
};


/**
 * 客户维护查询结果表格Store
 */
Ext.define('MaintainEffectEvaluationListStore',{
	extend:'Ext.data.Store',
	model:'MaintainEffectEvaluationListModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		url:'../marketing/queryMaintainEffectResultByCondition.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'custMatAssessList',
			totalProperty : 'totalCount'
		}
	}
});

/**
 * 维护：导出权限下所有数据到服务器端生成一个Excel报表文件
 */
MaintainEffectEvaluationStore.prototype.creatMaintainMarketEffectReportExcel = function(param,successFn,failureFn){
	var url = '../marketing/creatMaintainMarketEffectReportExcel.action';
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
 * 导出当前查询条件下所有数据到服务器端生成一个Excel报表文件
 */
MaintainEffectEvaluationStore.prototype.creatMaintainReportExcelByCondition = function(param,successFn,failureFn){
	var url = '../marketing/creatMaintainReportExcelByCondition.action';
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
 * 维护：查询合计
 */
MaintainEffectEvaluationStore.prototype.searchSumCustMatByAuth = function(param,successFn,failureFn){
	var url = '../marketing/searchSumCustMatByAuth.action';
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