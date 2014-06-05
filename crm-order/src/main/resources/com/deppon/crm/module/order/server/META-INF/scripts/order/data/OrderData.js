/**.
 * <p>
 * 订单模块中要用到的DATA<br/>
 * <p>
 * @author 张斌
 * @时间 2012-3-8
 */

/**.
 * <p>
 * 判断是否导入测试数据<br/>
 * <p>
 * @author 张斌
 * @时间 2012-3-8
 */
(function() {
	var customerDataTest = "../scripts/order/test/OrderDataTest.js";
    if(ORDERCONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + customerDataTest + '"></script>');
	}
})();

OrderData = function(){
};
//-----------------------------MODEL------------------------------------------

//------------------------------------------------------------------------

/**.
 * <p>
 * 获取行政区域数据的store<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @param  storeId  STORE 的ID
 * @level     行政区域等级，1、省2、市3、区县，对应不同的URL
 * @returns regionStore
 * @author 张斌
 * @时间 2012-3-9
 */
OrderData.prototype.getRegionsStore = function(storeId,level,param,ssuccessFn,failureFn){
	var url  = null;
	if(level == 1){
		url = "../order/initCreatePageData.action";
	}else if(level == 2){
		url = "../order/searchCityByProvince.action";
	}else if(level == 3){
		url = "../order/searchDistrictByCity.action";
	}
	var regionStore = Ext.data.StoreManager.lookup(storeId);
		if(!regionStore){
			    regionStore = Ext.create('Ext.data.Store', {
				storeId:storeId,
			    model: 'RegionModel'
			});
			if(level != 1){
				return regionStore;
			}
	}
	DButil.requestJsonAjax(url,param,ssuccessFn,failureFn);
	return regionStore;
};

/**.
 * <p>
 * 通过手机号获取客户数据的store<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-3-9
 */
OrderData.prototype.getCustomerInfoByMobile = function(param,successFn,failureFn){
	 var url ='../order/searchCustomerByCellNum.action';
	 DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 通过手机号获取客户数据的store<br/>
 * <p>
 * @param  storeId  storeid
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-3-9
 */
OrderData.prototype.getCustomerInfoByPhone = function(param,storeId,successFn,failureFn){
	var customerStore = Ext.data.StoreManager.lookup(storeId);
	if(!customerStore){
		customerStore = Ext.create('Ext.data.Store', {
		storeId:storeId,
	    fields:['id','custName','custNumber','contactList','connectPhone']
	});
   }else{
	   var url = '../order/searchCustomerByPhone.action';
		DButil.requestJsonAjax(url,param,successFn,failureFn);
   }
	return customerStore;
};


/**.
 * <p>
 * 通过客户名称获取客户数据的store<br/>
 * <p>
 * @param  storeId  storeid
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @returns customerStore
 * @author 张斌
 * @时间 2012-3-9
 */
OrderData.prototype.getCustomerStoreByCustName = function(param,storeId,successFn,failureFn){
	var customerStore = Ext.data.StoreManager.lookup(storeId);
	if(!customerStore){
		customerStore = Ext.create('Ext.data.Store', {
		storeId:storeId,
	    fields:['id','custName','custNumber','contactList']
	});
    }else{
	   if(Ext.isEmpty(param)){
		   return;
	   }
	   var url = '../order/searchCustomerByName.action';
		 DButil.requestJsonAjax(url,param,successFn,failureFn);
   }
	return customerStore;
};
/**.
 * <p>
 * 提交订单数据<br/>
 * <p>
 * @param param  订单创建提交的数据
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-3-9
 */
OrderData.prototype.commitOrder = function(param,successFn,failureFn){
	var url = '../order/createOrder.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 判断网点所在城市是否在大小城市资料表中<br/>
 * <p>
 * @param param  网点所在城市的ID
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-3-15
 */
OrderData.prototype.isBasicCity = function(param,successFn,failureFn){
	var url = '../common/isBacikCity.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 使用部门为营业部时，始发网点默认为本部门<br/>
 * <p>
 * @param param  订单创建提交的数据
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-3-15
 */
OrderData.prototype.initPoint = function(param,storeId,successFn,failureFn){
	var url = '../order/initPoint.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};


OrderData.prototype.changePointId = function(param,param,successFn,failureFn){
	var url = '../order/changePointId.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * 查询营业部门
 * @param {} param 查询参数
 * @param {} successFn 成功回调
 * @param {} failureFn 失败回调
 */
OrderData.prototype.changePointStandardcode = function(param,successFn,failureFn){
	var url = '../order/changePointStandardcode.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 受理订单<br/>
 * <p>
 * @param param  受理订单提交的数据
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-3-19
 */
OrderData.prototype.processOrder = function(param,successFn,failureFn){
	var url = '../order/processOrder.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 打回订单<br/>
 * <p>
 * @param param  打回订单提交的数据
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-3-19
 */
OrderData.prototype.returnOrder = function(param,successFn,failureFn){
	var url = '../order/returnOrder.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * 订单受理页面查询store
 */
OrderData.prototype.getProcessStore = function(){
	Ext.define('ProcessOrderStore',{
		pageSize : 10,
		extend:'Ext.data.Store',
		model:'OrderModel',
		//autoLoad:true,
		storeId:'processOrderStore',
		proxy:{
			type:'ajax',
			url:'../order/searchOrderList.action',
			actionMethods:'POST',
			timeout:100000,
			reader:{
				type:'json',
				root:'orderList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new ProcessOrderStore();
};
/**.
 * <p>
 * 订单分配<br/>
 * <p>
 * @returns assignOrder
 * @author 张登
 * @时间 2012-3-11
 */
OrderData.prototype.assignOrder = function(param,successFn,failureFn){
	var url = '../order/assignOrder.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 订单拒绝<br/>
 * <p>
 * @returns refuseOrder
 * @author 张登
 * @时间 2012-3-11
 */
OrderData.prototype.refuseOrder = function(param,successFn,failureFn){
	var url = '../order/refuseOrder.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 取消订单<br/>
 * <p>
 * @returns refuseOrder
 * @author 张登
 * @时间 2012-3-11
 */
OrderData.prototype.cancelOrder = function(param,successFn,failureFn){
	var url = '../order/cancelOrder.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};


/**.
 * <p>
 * 根据订单号查询订单<br/>
 * <p>
 * @returns getOrderWaybill
 * @author 张登
 * @时间 2012-3-11
 */
OrderData.prototype.getOrderByOrderNumber = function(param,successFn,failureFn){
	var url = '../order/orderByOrderNumber.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 根据运单号查询运单<br/>
 * <p>
 * @returns getWaybillByNumber
 * @author 张登
 * @时间 2012-3-11
 */
OrderData.prototype.waybillNum = function(param,successFn,failureFn){
	var url = '../order/waybillNum.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 根据运单号查询订单信息<br/>
 * <p>
 * @returns orderWaybillNum
 * @author 张登
 * @时间 2012-3-11
 */
OrderData.prototype.orderWaybillNum = function(param,successFn,failureFn){
	var url = '../order/orderWaybillNum.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 订运单关联<br/>
 * <p>
 * @returns getWaybillByNumber
 * @author 张登
 * @时间 2012-3-11
 */
OrderData.prototype.associateOrderAndWaybill = function(param,successFn,failureFn){
	var url = '../order/associateOrderAndWaybill.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 保存备注
 * <p>
 * @returns getWaybillByNumber
 * @author 张登
 * @时间 2012-3-11
 */
OrderData.prototype.saveOrderRemark = function(param,successFn,failureFn){
	var url = '../order/saveOrderRemark.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};

/**
 * 订单分配页面查询store
 */
OrderData.prototype.getNoAllocationOrderStore = function(){
	Ext.define('NoAllocationOrderStore',{
		extend:'Ext.data.Store',
		model:'OrderModel',
		//autoLoad:true,
		pageSize : 10,
		storeId:'noAllocationOrderStore',
		proxy:{
			type:'ajax',
			url:'../order/searchAssignAndRefuseOrders.action',
			actionMethods:'POST',
			timeout:100000,
			reader:{
				type:'json',
				root:'orderList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new NoAllocationOrderStore();
};

/**
 * 订单分配页面查询用户操作记录store
 */
OrderData.prototype.getOrderOperationLogStore = function(){
	Ext.define('OrderOperationLogStore',{
		extend:'Ext.data.Store',
		model:'OrderOperationLogModel',
		storeId:'orderOperationLogStore',
		proxy:{
			type:'ajax',
			url:'../order/getOrderOperationLogList.action',
			actionMethods:'POST',
			timeout:100000,
			reader:{
				type:'json',
				root:'orderOperationList'
			}
		}
	});
	return new OrderOperationLogStore();
};



/**.
 * <p>
 * 验证是否可约车<br/>
 * 方法名：isCallCar<br/>
 * <p>
 * @returns isCallCar
 * @author 张斌
 * @时间 2012-3-21
 */
OrderData.prototype.isCallCar = function(param,successFn,failureFn){
	//@TODO 修改URL
	var url = '../order/bookVehicleable.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 验证是否可修改<br/>
 * 方法名：isUpdateOrderable<br/>
 * <p>
 * @returns isUpdateOrderable
 * @author 张斌
 * @时间 2012-3-21
 */
OrderData.prototype.isUpdateOrderable = function(param,successFn,failureFn){
	//@TODO 修改URL
	var url = '../order/updateOrderable.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 提交延时发货信息
 * 方法名：delayOrder<br/>
 * <p>
 * @ updateOrder
 * @author 李春雨
 * @时间 2013-09-03
 */
OrderData.prototype.delayOrder = function(param,successFn,failureFn){
	//@TODO 修改URL
	var url = '../order/delayOrder.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};




/**.
 * <p>
 * 提交约车
 * 方法名：bookVehicle<br/>
 * <p>
 * @ bookVehicle
 * @author 张斌
 * @时间 2012-3-21
 */
OrderData.prototype.bookVehicle = function(param,successFn,failureFn){
	//@TODO 修改URL
	var url = '../order/bookVehicle.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 提交修改订单信息
 * 方法名：updateOrder<br/>
 * <p>
 * @ updateOrder
 * @author 张斌
 * @时间 2012-3-21
 */
OrderData.prototype.updateOrder = function(param,successFn,failureFn){
	//@TODO 修改URL
	var url = '../order/updateOrder.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 催单操作
 * 方法名：pressOrder<br/>
 * <p>
 * @ pressOrder
 * @author 张斌
 * @时间 2012-3-21
 */
OrderData.prototype.pressOrder = function(param,successFn,failureFn){
	//@TODO 修改URL
	var url = '../order/pressOrder.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 揽货失败操作
 * 方法名：pressOrder<br/>
 * <p>
 * @ failOrder
 * @author 张斌
 * @时间 2012-3-21
 */
OrderData.prototype.failOrder = function(param,successFn,failureFn){
	//@TODO 修改URL
	var url = '../order/failOrder.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 通过网点名称获取提货网点数据的store(到达网点)<br/>
 * <p>
 * @param  storeId  storeid
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @returns store
 * @author 张斌
 * @时间 2012-3-20
 */
OrderData.prototype.getBussinessDepts = function(param,storeId,successFn,failureFn){
	var store = Ext.data.StoreManager.lookup(storeId);
	if(!store){
		store = Ext.create('Ext.data.Store', {
		storeId:storeId,
	    model:'PointModel'
	});
   }else{
	   if(Ext.isEmpty(param)){
		   return;
	   }
	   var url = '../order/searchBussinessDepts.action';
		 DButil.requestJsonAjax(url,param,successFn,failureFn);
   }
	return store;
};
/**.
 * <p>
 * 通过网点名称获取提货网点数据的store(到达网点)<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-3-20
 */
OrderData.prototype.getPrintOrderData = function(param,successFn,failureFn){
	var url = '../order/printOrder.action';
		DButil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 使用部门为400服务中心时，选择客户后，默认始发网点为该客户最近一次发货的始发网点<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-3-31
 */
OrderData.prototype.init400Point = function(param,successFn,failureFn){
	var url = '../order/init400Point.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * <p>
 * 根据联系人手机查询联系人信息<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张登
 * @时间 2012-6-16
 */
OrderData.prototype.searchMemberInfoByPhone = function(param,successFn,failureFn){
	var url = '../order/searchMemberInfoByPhone.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 使用部门为400服务中心时，选择客户后，默认始发网点为该客户最近一次发货的始发网点<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-3-31
 */
OrderData.prototype.printOrder = function(){
	window.open('../order/printOrderNow.action','printOrderPage', 'top=0,left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,channelmode=yes,fullscreen=yes,titlebar=no');
};
//------------------------------------------
/**
 * 始发网点store
 */
OrderData.prototype.getNetPointStore = function(){
	Ext.define('ConsignorComeFromPointStore',{
		pageSize : 5,
		extend:'Ext.data.Store',
		model:'PointModel',
		storeId:ORDERNAME.get('consignorComeFromPoint'),
		proxy:{
			type:'ajax',
			url:'../order/searchBussinessDepts.action',
			actionMethods:'POST',
			timeout:100000,
			reader:{
				type:'json',
				root:'orderSearchView.bussinessDepts',
				totalProperty : 'totalCount'
			}
		},
	   listeners:{
			beforeload:function(store, operation, eOpts){
				Ext.apply(operation,{
					params : {
						'orderSearchView.deptSearchCondition.deptName':Ext.isEmpty(Ext.getCmp(ORDERNAME.get('consignorComeFromPoint')))?Ext.getCmp('orderQuery_Form').getForm().findField(ORDERNAME.get('consignorComeFromPoint')).getRawValue():Ext.getCmp(ORDERNAME.get('consignorComeFromPoint')).getRawValue(),
						'orderSearchView.deptSearchCondition.ifLeave':true
						}
					}
				);	
			}
		}
	});
	return new ConsignorComeFromPointStore();
};


/**
 * 到达网点store
 */
OrderData.prototype.getArriveNetPointStore = function(){
	Ext.define('ConsignorComeToPointStore',{
		pageSize : 5,
		extend:'Ext.data.Store',
		model:'PointModel',
		storeId:ORDERNAME.get('receivingToPoint'),
		proxy:{
			type:'ajax',
			url:'../order/searchBussinessDepts.action',
			actionMethods:'POST',
			timeout:100000,
			reader:{
				type:'json',
				root:'orderSearchView.bussinessDepts',
				totalProperty : 'totalCount'
			}
		},
	   listeners:{
			beforeload:function(store, operation, eOpts){
				Ext.apply(operation,{
					params : {
						'orderSearchView.deptSearchCondition.deptName':Ext.getCmp(ORDERNAME.get('receivingToPoint')).getRawValue(),
						'orderSearchView.deptSearchCondition.ifArrive':true
						}
					}
				);	
			}
		}
	});
	return new ConsignorComeToPointStore();
};

/**
 * 用车部门store
 */
OrderData.prototype.getUserCarPointStore = function(){
	Ext.define('UserCarPointStore',{
		pageSize : 5,
		extend:'Ext.data.Store',
		model:'PointModel',
		storeId:ORDERNAME.get('useVehicleDept'),
		proxy:{
			type:'ajax',
			url:'../order/searchBussinessDepts.action',
			actionMethods:'POST',
			timeout:100000,
			reader:{
				type:'json',
				root:'orderSearchView.bussinessDepts',
				totalProperty : 'totalCount'
			}
		},
	   listeners:{
			beforeload:function(store, operation, eOpts){
				Ext.apply(operation,{
					params : {
						'orderSearchView.deptSearchCondition.deptName':Ext.getCmp(ORDERNAME.get('useVehicleDept')).getRawValue()
						}
					}
				);	
			}
		}
	});
	return new UserCarPointStore();
};

/**
 * 用车部门store
 */
OrderData.prototype.getUserCarPointStoreByAddOrder = function(){
	Ext.define('UserCarPointStore',{
		pageSize : 5,
		extend:'Ext.data.Store',
		model:'PointModel',
		storeId:ORDERNAME.get('useVehicleDept'),
		proxy:{
			type:'ajax',
			url:'../order/searchBussinessDepts.action',
			actionMethods:'POST',
			timeout:100000,
			reader:{
				type:'json',
				root:'orderSearchView.bussinessDepts',
				totalProperty : 'totalCount'
			}
		},
	   listeners:{
			beforeload:function(store, operation, eOpts){
				Ext.apply(operation,{
					params : {
						'orderSearchView.deptSearchCondition.deptName':Ext.getCmp('addUseVehicleDept').getRawValue()
						}
					}
				);	
			}
		}
	});
	return new UserCarPointStore();
};

/**
 * 派车车队store
 */
OrderData.prototype.getVehicleTeamPointStore = function(){
	Ext.define('VehicleTeamPointStore',{
		pageSize : 5,
		extend:'Ext.data.Store',
		model:'PointModel',
		storeId:ORDERNAME.get('vehicleTeam'),
		proxy:{
			type:'ajax',
			url:'../order/searchBussinessDepts.action',
			actionMethods:'POST',
			timeout:100000,
			reader:{
				type:'json',
				root:'orderSearchView.bussinessDepts',
				totalProperty : 'totalCount'
			}
		},
	   listeners:{
			beforeload:function(store, operation, eOpts){
				Ext.apply(operation,{
					params : {
						'orderSearchView.deptSearchCondition.deptName':Ext.getCmp(ORDERNAME.get('vehicleTeam')).getRawValue(),
						'orderSearchView.deptSearchCondition.ifVehicleTem':true
						}
					}
				);	
			}
		}
	});
	return new VehicleTeamPointStore();
};