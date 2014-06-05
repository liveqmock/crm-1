/**
 * 
 * <p>
 * Description:可视化营销data<br />
 * </p>
 * @title VisualMarketingModel.js
 * @author roy
 * @version 0.1 2013-4-20
 */

var visualMarketingData  = function(){

};

Ext.define('CRM.marketing.CustomerMarketInfoStore',{
	extend:'Ext.data.Store',
	model:'CRM.marketing.CustomerMarketInfoModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		url:'../marketing/searchCustomerMarketInfoList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'customerMarketInfoList',
			totalProperty : 'totalCount'
		}
	}
});


/**
 * 根据客户id查询客户地址
 * @param {} param
 * @param {} successFn
 * @param {} failureFn
 */
visualMarketingData.prototype.findCustomerAddressByCustId = function(param,successFn,failureFn){
	var url='../marketing/serachCustomerAddressByCustId.action';
	DButil.requestAjax(url,param,successFn,failureFn);
}

/**
 * 根据部门id查询客户标签
 * @param {} param
 * @param {} successFn
 * @param {} failureFn
 */
visualMarketingData.prototype.findCustomerLabelByDeptId = function(param,successFn,failureFn){
	var url='../marketing/serachCustomerlabelByDeptId.action';
	DButil.requestAjax(url,param,successFn,failureFn);
}
/**
 * 根据部门id查询营销区域
 * @param {} param
 * @param {} successFn
 * @param {} failureFn
 */
visualMarketingData.prototype.searchMarkCustInfoList = function(param,successFn,failureFn){
	var url='../marketing/searchMarkCustInfoList.action';
	DButil.requestAjax(url,param,successFn,failureFn);
}

visualMarketingData.prototype.searchRegionByDeptId = function(param,successFn,failureFn){
	var url = '../marketing/searchRegionByDeptId.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};