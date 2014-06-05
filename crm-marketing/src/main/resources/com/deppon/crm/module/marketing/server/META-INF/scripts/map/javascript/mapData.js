/**
 * 回访
 */
var mapData  = function(){

};
/**
 * 客户地址模型
 */
Ext.define('MapCustomerGridModel', {
	extend : 'Ext.data.Model',
	fields : [{
		//客户名称
		name : 'customerName',
		type : 'string'
	}, {
		//地址
		name : 'linkmanPreferAddress',
		type : 'string'
	},{
		//行业
		name : 'industry',
		type : 'string'
	},{
		//二级行业
		name : 'secondTrade',
		type : 'string'
	},{
		//联系人姓名
		name : 'linkmanName',
		type : 'string'
	},{
		//手机
		name : 'linkmanMobile',
		type : 'string'
	},{
		//固定电话
		name : 'linkmanPhone',
		type : 'string'
	},{
		//城市
		name : 'city',
		type : 'string'
	},{
		//客户类型
		name : 'customerType',
		type : 'string'
	},{
		//客户id
		name : 'customerId',
		type : 'string'
	},{
		name:'lat',
		type:'string'
	},{
		name:'lng',
		type:'string'
	},{
		//联系人id
		name:'linkmanId',
		type:'string'
	}]
});
/**
 * 客户地址信息Store
 */
Ext.define('MapCustomerGridStore',{
	extend:'Ext.data.Store',
	model:'MapCustomerGridModel',
	pageSize : 20,
	proxy : {
		type : 'ajax',
		url : '../marketing/searchCustomerList.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'mapViewList',
			totalProperty : 'totalCount'
		}
	}
});
/**
 * 隐藏详细地址信息Model
 */
Ext.define('MapAddressListModel', {
	extend : 'Ext.data.Model',
	fields : [{
		name : 'address',
		type : 'string'
	},{
		name:'point',
		type:'string'
	}]
});

/**
 * 隐藏详细地址信息Store
 */
Ext.define('MapAddressListStore',{
	extend:'Ext.data.Store',
	model:'MapAddressListModel',
	data : []
});


/**
 * 查看回访明细
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 毛建强
 * @时间 2012-4-6
 */
mapData.prototype.markCustomerLocation = function(param,successFn,failureFn){
	var url = '../marketing/markCustomerLocation.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 根据条件查询客户列表
 * <p>
 * @author 张登
 * @时间 2012-3-26
 */
Ext.define('DeliverySearchConditionStore',{
	extend:'Ext.data.Store',
	model:'SearchConditionModel',
	pageSize:10,
	proxy:{
		type:'ajax',
		url:'../marketing/searchDevelopPopDataList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'customerInfoList',
			totalProperty : 'totalCount'
		}
	}
});

/**.
 * <p>
 * 根据计划id查询关联客户
 * <p>
 * @author 张登
 * @时间 2012-3-26
 */
Ext.define('DeliverySearchCustbyIdStore',{
	extend:'Ext.data.Store',
	model:'SearchConditionModel'
});