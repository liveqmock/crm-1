Ext.onReady(function(){
	
	function getUrlVars(v){  
	    var params = {}, hash;  
	    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');  
	    for(var i = 0; i < hashes.length; i++){  
	        hash = hashes[i].split('=');  
	        params[hash[0]] = hash[1];  
	    }  
	    return params[v];  
	}  
	
	Ext.create('Ext.container.Viewport', {
		items:[],
		listeners:{
			'afterrender':function(t){
				var params = {'orderView':{'order':{'orderNumber':getUrlVars('orderNumber')}}};
				processingMask.show();//显示进度条信息
				// 查询成功回调函数
				var searchSuccess = function(response) {
					processingMask.hide();//显示进度条信息
					var order=response.orderView.order;
					if(null!=order){
						var orderModel = new OrderModel(order);
				        var orderview=Ext.create('CreateOrderView',{isReadOnly:true,'y':0,'orderData':orderModel});	
						orderview.consignorForm.loadRecord(orderModel);
					    orderview.receivingForm.loadRecord(orderModel);
						orderview.goodsInfoForm.loadRecord(orderModel);
						orderview.transportationAndServiceFrom.loadRecord(orderModel);        
				        t.add(orderview);
				        if(Ext.getCmp('searchElectronicMap_consignor')){
				        	Ext.getCmp('searchElectronicMap_consignor').hide();
				        }
				        if(Ext.getCmp('searchElectronicMap_receiving')){
				        	Ext.getCmp('searchElectronicMap_receiving').hide();
				        }
				        t.doLayout();
					}else{
						MessageUtil.showErrorMes(i18n('i18n.alert_message_dataNotExist'),function(){window.parent.Ext.getCmp('orderDetailView').close()});
					}
				};
				// 查询失败调函数
				var searchFail = function(response) {
					processingMask.hide();//显示进度条信息
					MessageUtil.showErrorMes(response.message);
				};
				var orderUrl='../order/orderByOrderNumber.action';
				DButil.requestJsonAjax(orderUrl,params,searchSuccess,searchFail);
			}
		}
	});
});