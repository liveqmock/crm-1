/**
 * 初始化订单来源
 */
var orderResources = null;
function initOrderSources(){
	Ext.Ajax.request({
		url: '../order/allResources.action',
		async:false,
		success: function(res){
			var data = Ext.decode(res.responseText);
			if(data.isException){
				MessageUtil.showErrorMes(data.message,function(){
					e.record.reject();
				});
			}else{
				orderResources = data.resources;
			}
		},
		failure: function(){
			MessageUtil.showErrorMes("加载订单来源失败");
		}
	})
};
//获得订单来源的Store
function getOrderResourcesStore(){
	if(!Ext.isEmpty(orderResources)){
		var json={
			fields: [{
				name: 'codeDesc', mapping: 'name'
			},{
				name: 'code', mapping: 'resource'
			}],
		    data : orderResources
		};
		return Ext.create('Ext.data.Store',json);
	}
	else{
		return null;
	}
};