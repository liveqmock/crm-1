activitiesClientBaseReportStore = function(){
	
};

/**
 * 客户群报表查询结果表格Store

 */
Ext.define('ClientBaseDetailModel',{
    extend : 'Ext.data.Store',
    model : 'ClientBaseDetailInfoModel',
    pageSize : 10,
    proxy : {
        type : 'ajax',
        url : '../marketingReport/searchClientBaseDetail.action',
        actionMethods : 'post',
        reader : {
            type : 'json',
            root : 'clientBaseDetailList',
            totalProperty : 'totalCount'
        }
    }
});
/**
 * 模糊查询营销活动
 */
Ext.define('ActivitInfoStore',{
    extend : 'Ext.data.Store',
    model : 'ClientBaseDetailInfoModel',
    pageSize : 10,
    proxy : {
        type : 'ajax',
        url : '../marketingReport/searchActivityInfo.action',
        actionMethods : 'post',
        reader : {
            type : 'json',
            root : 'clientBaseDetailList',
            totalProperty : 'totalCount'
       
        }
    }
});
/**
 * 模糊联动查询客户群
 */
Ext.define('ClientBaseInfoStore',{
    extend : 'Ext.data.Store',
    model : 'ClientBaseDetailInfoModel',
    pageSize : 10,
    proxy : {
        type : 'ajax',
        url : '../marketingReport/searchClientBaseInfo.action',
        actionMethods : 'post',
        reader : {
            type : 'json',
            root : 'clientBaseDetailList',
            totalProperty : 'totalCount'
       
        }
    }
});
/**
 * 模糊联动客户归属部门
 */
Ext.define('DeptInfoStore',{
    extend : 'Ext.data.Store',
    model : 'ClientBaseDetailInfoModel',
    pageSize : 10,
    proxy : {
        type : 'ajax',
        url : '../marketingReport/searchDeptInfo.action',
        actionMethods : 'post',
        reader : {
            type : 'json',
            root : 'clientBaseDetailList',
       		 totalProperty : 'totalCount'
        }
    }
});
/**
 * 报表创建
 */
activitiesClientBaseReportStore.prototype.creatClientBaseReportExcel = function(param,successFn,failureFn){
	var url = '../marketingReport/creatClientBaseReportExcel.action';
	Ext.Ajax.request({
		url:url,
		params:param,
		//设置超时时间为8分钟
		timeout: 480000,
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
