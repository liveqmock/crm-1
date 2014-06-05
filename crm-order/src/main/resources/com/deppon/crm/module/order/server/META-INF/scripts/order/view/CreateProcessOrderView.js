/**.
 * <p>
 * 将CommonOrderView中的元素组合成订单页面<br/>
 * 订单受理页面
 * </p>
 * @author  张斌
 * @时间    2012-03-08
 * </p>
 */
//设置Data层对象

var orderbaseDataControl =  (ORDERCONFIG.get("TEST"))? new OrderDataTest():new OrderData();
var IsSearch = false;
var IsAutoSearch = true;
/**.
 * <p>
 * 获取业务字典数据</br>
 * </p>
 * @author  张斌
 * @时间    2012-03-13
 */
var param = {'dataDictionaryKeys':[
     //签收单
     'SIGNBILL',
     //反馈信息
     'FEEDBACK_INFO',
     //送货方式
     'PICKGOODTYPE',
     //运输类型
     'TRANS_TYPE',
     //订单状态
 	 'ORDER_STATUS',
 	 //订单来源
 	 'ORDER_SOURCE',
     //付款方式
     'PAY_WAY',
     //用车车型
     'TRUCK_TYPE',
     //操作类别
     'ORDER_OPERATION',
     //AL会员类型
     'ALIBABA_MEMBERTYPE',
     //即日退、三日退
     'REFUND_TYPE'
]};
 var successFn = function(json){
	 dataDictionary = json.dataDictionary;
	 /**.
	  * <p>
	  * 订单受理主页面</br>
	  * </p>
	  * @author  张斌
	  * @时间    2012-03-19
	  */
	 Ext.onReady(function() {
	     Ext.QuickTips.init();    
	 	 new ProcessOrderView();
	 	 DButil.refreshGird();
	 	 initDeptAndUserInfo();
	 });
 };
 var failureFn = function(json){
	 MessageUtil.showErrorMes(json.message);
 };
DButil.getBusinessDictionary(param,successFn,failureFn);
Ext.form.Field.prototype.msgTarget='side';

// 加载订单来源
var OrderNumberRuleAction = {
		resources: null,
		renderResource: function(value){
			for(var i=0;i<this.resources.length;i++){
				var r = this.resources[i];
				if(r.resource == value){
					return r.name;
				}
			}
			return value;
		}
};
Ext.Ajax.request({
	url: '../order/allResources.action',
	success: function(res){
		var data = Ext.decode(res.responseText);
		if(data.isException){
			MessageUtil.showErrorMes(data.message,function(){
				e.record.reject();
			});
		}else{
			OrderNumberRuleAction.resources = data.resources;
		}
	},
	failure: function(){
		MessageUtil.showErrorMes("加载订单来源失败");
	}
})


Ext.define('OrderInfoTabPanel',{
	extend:'TabContentPanel',
	id:'orderInfoTabPanel',
	title : i18n('i18n.order.orderInfo'),			//订单详情
	flex:1,
	layout:{
		type:'vbox',
		align:'stretch'
	},
	items:null,
	getItems:function(){
		return [new SendGoodForm(),new ReceiveGoodForm(),new BasicInfoForm()];
	},
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
	}
});

Ext.define('OperationInfoTabPanel',{
	extend:'TabContentPanel',
	flex:1,
	layout:'fit',
	title :i18n('i18n.order.operationLog'),			//操作记录
	items:null,
	getItems:function(){
		return [new OrderOperationGrid({'store':orderbaseDataControl.getOrderOperationLogStore(),'dataDictionary':dataDictionary})];
	},
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
	}
});

Ext.define('ProcessOrderTabPanel',{					//left：tab
		extend : 'NormalTabPanel',
		flex:1,
		 height:437,
	   	test1:null,
	   	test2:null,
	   	items:null,
	   	initComponent:function(){
			var me = this;
			me.test1 = new OrderInfoTabPanel();
			me.test2 = new OperationInfoTabPanel();
			me.items = [me.test1,
		       me.test2],
			this.callParent();
	   	}
});
/**.
 * <p>
 * 受理订单页面</br>
 * </p>
 * @author  张斌
 * @时间    2012-03-20
 */
Ext.define('ProcessOrderView',{
 		extend:'Ext.container.Viewport',
 		autoDestroy : true,
// 		layout:{
//				type:'vbox',
//				align:'stretch',
//			},
 		autoScroll:true,
 		id:'processOrderView',
 		/**.
 		 * <p>
 		 * 约车操作</br>
 		 * </p>
 		 * @author  张斌
 		 * @时间    2012-03-21
 		 */
 		callCar:function(btn){
 			var successFn = function(json){
 				btn.setDisabled(false);
 				//@TODO将到达网点的数据放到store里面，并选择
 				var orderModel = new OrderModel(json.orderView.order);
 				//始发网点是否有AB货
 				var beginDeptIsHashGoodsType = null;
 				if(!Ext.isEmpty(json.orderView.beginDept)){
 					//@TODO判断始发网点所在城市是否在大小城市基础资料表中，并将值放到自己/CallCar的一个属性中(1、始发网点在大小城市基础资料表中，
 					//那么A/B货可选2、只有到达网点在大小城市基础资料表中工，选择到达网点之后判断，新的到达网点是否在大小城市基础资料表中)
 					beginDeptIsHashGoodsType = json.orderView.beginDept.ifHashGoodsType;
 				}/*else{
 					DButil.showMessage(i18n('i18n.order.noHaveBegionDept'));
 					return ;
 				}*/
 				var endDeptIsHashGoodsType = null;
 				if(!Ext.isEmpty(json.orderView.endDept)){
 					endDeptIsHashGoodsType = json.orderView.endDept.ifHashGoodsType;
 				}
 				var callCarWindow = Ext.getCmp('callCarWindow');
 				if (Ext.isEmpty(callCarWindow)) {
 					new CallCarWindow({'data':orderModel,'beginDeptIsHashGoodsType':beginDeptIsHashGoodsType}).show();
				}else{
					callCarWindow.data = orderModel;
					callCarWindow.beginDeptIsHashGoodsType = beginDeptIsHashGoodsType;
					callCarWindow.show();
				}
				isContractMember=json.isContractMember;
				if(json.orderView.order.transportMode!='JZKY'&&json.orderView.order.transportMode!='PACKAGE'){
    				if(!Ext.isEmpty(Ext.getCmp('callCarsID'))){
    					Ext.getCmp('callCarsID').setDisabled(false);
    				}
    				Ext.getCmp(ORDERNAME.get('deliveryWay')).getStore().removeAll(false);
    				Ext.getCmp(ORDERNAME.get('deliveryWay')).getStore().add(
    					{
    						'code':'PICKSELF','codeDesc':i18n('i18n.order.PICKUP')//'自提'
    					},{
    						'code':'DELIVERY_STOCK','codeDesc':i18n('i18n.order.DELIVERYSTOCK')//'送货进仓'
    					},{
    						'code':'PICKUPSTAIRS','codeDesc':i18n('i18n.order.DELIVERYHOME')//'送货上楼'
    					},{
    						'code':'PICKNOTUPSTAIRS','codeDesc':i18n('i18n.order.DELIVERY')//'送货(不含上楼)'
    					},{
    						'code':'INNER_PICKUP','codeDesc':i18n('i18n.order.INNERPICKUP')//'内部带货自提'
    					}
    				);
     				if(isContractMember){
     					var deliveryWayStore=Ext.getCmp(ORDERNAME.get('deliveryWay')).getStore();
     					deliveryWayStore.add(
     						{
    							'code':'FREE_DELIVERY','codeDesc':i18n('i18n.order.FREEDELIVERY')//'免费送货'
    						}
     					);
     				}
    				Ext.getCmp(ORDERNAME.get('deliveryWay')).setValue('PICKSELF');//默认值
    			}
    		     /**			
				    * 修改人：张斌
					*修改时间：2013-7-27 10:17
					*原有内容：无（新增）
					*修改原因：选择包裹“签收单”和“提货方式”改变
				  */
					//begin
    			else if(json.orderView.order.transportMode=='PACKAGE'){//包裹
    				if(Ext.isEmpty(Ext.getCmp('recevicGoodsInfoID'))
    						||Ext.isEmpty(Ext.getCmp('callCarsID'))
    						||Ext.isEmpty(Ext.getCmp('commitOrderId'))){
    					
    				}else{
    					Ext.getCmp('recevicGoodsInfoID').disable();
				    	Ext.getCmp('recevicGoodsInfoID').hide();
				    	Ext.getCmp('callCarsID').setText('约车');
				    	Ext.getCmp('commitOrderId').setText(i18n('i18n.order.commit'));
				    	isBookVehicle="0";//取消约车
				    	Ext.getCmp('callCarsID').setDisabled(true);
    				}
    				Ext.getCmp(ORDERNAME.get('deliveryWay')).getStore().removeAll(false);//提货方式
    				Ext.getCmp(ORDERNAME.get('deliveryWay')).getStore().add(
    						{
    							'code':'PICKSELF','codeDesc':i18n('i18n.order.PICKUP')//'自提'
    						},{
    							'code':'DELIVER_UP','codeDesc':i18n('i18n.order.DELIVER_UP')//'送货'
    						},{
        						'code':'INNER_PICKUP','codeDesc':i18n('i18n.order.INNERPICKUP')//'内部带货自提'
        					}
        				);
    				Ext.getCmp(ORDERNAME.get('deliveryWay')).setValue('DELIVER_UP');//默认送货
    			}
    			//end
    			else if(json.orderView.order.transportMode=='JZKY'){
    				if(!Ext.isEmpty(Ext.getCmp('callCarsID'))){
    					Ext.getCmp('callCarsID').setDisabled(false);
    				}
    				Ext.getCmp(ORDERNAME.get('deliveryWay')).getStore().removeAll(false);
    				Ext.getCmp(ORDERNAME.get('deliveryWay')).getStore().add(
    						{
    							'code':'SELF_PICKUP','codeDesc':i18n('i18n.order.SELFPICKUP')//'自提(不含机场提货费)'
    						},{
    							'code':'FREE_PICKUP','codeDesc':i18n('i18n.order.FREEPICKUP')//'免费自提'
    						},{
    							'code':'PICKONAIEPORT','codeDesc':i18n('i18n.order.AIRPORTPICKUP')//'机场自提'
    						},{
    							'code':'DELIVERY_STOCK','codeDesc':i18n('i18n.order.DELIVERYSTOCK')//'送货进仓'
    						},{
    							'code':'PICKUPSTAIRS','codeDesc':i18n('i18n.order.DELIVERYHOME')//'送货上楼'
    						},{
    							'code':'PICKNOTUPSTAIRS','codeDesc':i18n('i18n.order.DELIVERY')//'送货(不含上楼)'
    						}
        				);
     				if(isContractMember){
     					var deliveryWayStore=Ext.getCmp(ORDERNAME.get('deliveryWay')).getStore();
     					deliveryWayStore.add(
     						{
    							'code':'FREE_DELIVERY','codeDesc':i18n('i18n.order.FREEDELIVERY')//'免费送货'
    						}
     					);
     				}
    				Ext.getCmp(ORDERNAME.get('deliveryWay')).setValue('SELF_PICKUP');
    			};
 				//如果到达网点是偏线， 则运输方式只为精准空运和汽运偏线
 				if(!Ext.isEmpty(json.orderView.endDept)){
 					var dept = new PointModel(json.orderView.endDept);
 					Ext.getCmp(ORDERNAME.get('receivingToPoint')).getStore().add(dept);
 	 				Ext.getCmp(ORDERNAME.get('receivingToPoint')).setValue(json.orderView.endDept.id);
 	 				/*if(dept.get(ORDERNAME.get('ifOutward'))){
 	 					Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().removeAll();
 	 					Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().add({'code':'JZKY','codeDesc':i18n('i18n.order.playTranceJZ')});
 	 					Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().add({'code':'AGENT_VEHICLE','codeDesc':i18n('i18n.order.carTrance')});
 	 					Ext.getCmp(ORDERNAME.get('modeOfTransportation')).setValue('');
 	 				}else{
 	 					Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().removeAll();
 	 					Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().add(dataDictionary.TRANS_TYPE);
 	 				}*/
 				}
 				//设置普通元素的初始值
 				Ext.getCmp('callCarView_form').getForm().loadRecord(orderModel);
 				var consignorProvince = '';
 				if(!Ext.isEmpty(orderModel.get(ORDERNAME.get('consignorProvince')))){
 					consignorProvince = orderModel.get(ORDERNAME.get('consignorProvince'))+'-';
 				}
 				var consignorCity = '';
 				if(!Ext.isEmpty(orderModel.get(ORDERNAME.get('consignorCity')))){
 					consignorCity = orderModel.get(ORDERNAME.get('consignorCity'))+'-';
 				}
 				var consignorCounty = '';
 				if(!Ext.isEmpty(orderModel.get(ORDERNAME.get('consignorCounty')))){
 					consignorCounty = orderModel.get(ORDERNAME.get('consignorCounty'))
 				}
 				var consignorAreas = consignorProvince+consignorCity+consignorCounty;
 				Ext.getCmp('consignorAreaSelect').setRawValue(consignorAreas);
 				var goodsType = orderModel.get(ORDERNAME.get('goodsType'));
 			//	if(!orderModel.get(ORDERNAME.get('consignorIsCargo'))){
 					var startDate = new Date();
 					startDate.setMinutes(new Date().getMinutes()+3);
 					var enddate = new Date();
 					enddate.setMinutes(new Date().getMinutes()+153);
 					Ext.getCmp('callCarFromDate').setValue(startDate);
 					Ext.getCmp('callCarToDate').setValue(enddate);
 					
 			//	}
 				//设置如果原来选择了A、B这里也可以选择
 	 			if(goodsType=='A'||goodsType=='B'){
 	 				Ext.getCmp('goodsTypeA').setDisabled(false);
 	 				Ext.getCmp('goodsTypeB').setDisabled(false);
 	 				Ext.getCmp(ORDERNAME.get('goodsType')).allowBlank = false;
 	 			}
 	 			if(beginDeptIsHashGoodsType||beginDeptIsHashGoodsType){
 	 				Ext.getCmp('goodsTypeA').setDisabled(false);
 	 				Ext.getCmp('goodsTypeB').setDisabled(false);
 	 				Ext.getCmp(ORDERNAME.get('goodsType')).allowBlank = false;
 	 			}
 				var offerTel = orderModel.get(ORDERNAME.get('consignorPhone'));
 				if(!Ext.isEmpty(offerTel)){
 					offerTel = DButil.replacePhone(offerTel);
 					if(offerTel.split('-').length == 1){
 						Ext.getCmp(ORDERNAME.get('consignorPhone')).setValue(offerTel.split('-')[0]);
 					}else if(offerTel.split('-').length == 2){
 						var offerTelArray = offerTel.split('-');
 						if(offerTelArray[0].length>offerTelArray[1].length){
 							Ext.getCmp(ORDERNAME.get('consignorPhone')).setValue(offerTel.split('-')[0]);
 							Ext.getCmp(ORDERNAME.get('consignorExtension')).setValue(offerTel.split('-')[1]);
 						}
 						if(offerTelArray[1].length>offerTelArray[0].length){
 							Ext.getCmp(ORDERNAME.get('consignorPhone')).setValue(offerTel.split('-')[1]);
 							Ext.getCmp(ORDERNAME.get('consignorAreaCode')).setValue(offerTel.split('-')[0]);
 						}
 					}else{
 						Ext.getCmp(ORDERNAME.get('consignorExtension')).setValue(offerTel.split('-')[2]);
 						Ext.getCmp(ORDERNAME.get('consignorAreaCode')).setValue(offerTel.split('-')[0]);
 						Ext.getCmp(ORDERNAME.get('consignorPhone')).setValue(offerTel.split('-')[1]);
 					}
				}
 			};
 			var failureFn = function(json){
 				btn.setDisabled(false);
 				MessageUtil.showErrorMes(json.message);
 			};
 			var orderId = Ext.getCmp('orderGrid').getSelectionModel( ).getSelection()[0].data.id;
 			var custNumber = Ext.getCmp('orderGrid').getSelectionModel().getSelection()[0].data.shipperNumber;
 			var param = {'orderView':{'orderId':orderId},'custNumber':custNumber};
 			btn.setDisabled(true);
 			orderbaseDataControl.isCallCar(param,successFn,failureFn);
 		},
 		/**.
 		 * <p>
 		 * 修改订单操作</br>
 		 * </p>
 		 * @author  张斌
 		 * @时间    2012-03-21
 		 */
 		updateOrder:function(btn){
 			if(Ext.getCmp('orderGrid').getSelectionModel( ).getSelection().length==0){
 				MessageUtil.showMessage(i18n('i18n.order.pleaseSelectOne'));
 				return;
 			}
 			var orderSource = Ext.getCmp('orderGrid').getSelectionModel( ).getSelection()[0].get(ORDERNAME.get('resource'));
 			/**			
			    * 修改人：kuang
				*修改时间：2013-11-28 15:50
				*内容：
				*增加判断微信订单的逻辑
				*原因：营业部需修改补全微信订单
			*/
  			//营业部可修改微信订单
 			//只能修改营业部和呼叫中心下的单
 			if(orderSource!='BUSINESS_DEPT' && orderSource!='CALLCENTER' && orderSource!='CALLCENTER-HF'){
 				if(!isPermission('/order/compleWeixinOrder.action')){
 				MessageUtil.showMessage(i18n('il8n.order.onlyUpdateOrder'));
 				return;
 			}else if(isPermission('/order/compleWeixinOrder.action')&&orderSource!='WEIXIN'&&orderSource!="HUAQIANGBAO"&&orderSource!="HAOBAI"&&orderSource!="XIAOMI"){
 				MessageUtil.showMessage(i18n('i18n.updateOrder.modifyWeixinOrCallcenter'));
 				return;
 			}
 			}
 			
 			
 			
 			
// 			if(orderSource!='BUSINESS_DEPT' && orderSource!='CALLCENTER'){
// 				MessageUtil.showMessage(i18n('il8n.order.onlyUpdateOrder'));
// 				return;
// 			}
 			
 			
 			/**
 			 * 1.  当订单来源不属于以下情况是不可修改订单{BUSINESS_DEPT, CALLCENTER, WEIXIN}
 			 * 2.  微信单只有拥有权限（/order/compleWeixinOrder.action）的人才能改
// 			 **/
// 			var flag = 0;
// 			if(orderSource!='BUSINESS_DEPT' && orderSource!='CALLCENTER' && orderSource!='WEIXIN' ){
// 				MessageUtil.showMessage(i18n('il8n.order.onlyUpdateOrder'));
// 				return;
// 			}else if(orderSource=='WEIXIN' && isPermission('/order/compleWeixinOrder.action')){
// 				flag = 1;
// 				MessageUtil.showMessage(i18n('i18n.order.compleWeixinWarn'), function(){
// 		 			orderbaseDataControl.isUpdateOrderable(param,successFn,failureFn);
// 				});
// 	 		}else{
// 				flag=1;
// 			}
// 			if(flag=1){
// 				
// 			}
// 			flag=0;			
 			var successFn = function(json){
 				btn.setDisabled(false);
 				isContractMember=json.isContractMember;
 				var orderModel = new OrderModel(json.orderView.order);
 				//始发网点是否有AB货
 				var beginDeptIsHashGoodsType = null;
 				if(!Ext.isEmpty(json.orderView.beginDept)){
 					beginDeptIsHashGoodsType = json.orderView.beginDept.ifHashGoodsType;
 				}
 				//可以修改的
 				/*else{
 					MessageUtil.showErrorMes(i18n('i18n.order.noHaveBegionDept'));
 					return ;
 				}*/
 				var updateOrderWindow = Ext.getCmp('updateOrderWindow');
 				if (Ext.isEmpty(updateOrderWindow)) {
 					new UpdateOrderWindow({'data':orderModel,'beginDeptIsHashGoodsType':beginDeptIsHashGoodsType}).show();
				}else{
					updateOrderWindow.data = orderModel;
					updateOrderWindow.beginDeptIsHashGoodsType = beginDeptIsHashGoodsType;
					updateOrderWindow.show();
				}
				if(json.orderView.order.transportMode!='JZKY'&&json.orderView.order.transportMode!='PACKAGE'){
    				if(!Ext.isEmpty(Ext.getCmp('callCarsID'))){
    					Ext.getCmp('callCarsID').setDisabled(false);
    				}
    				Ext.getCmp(ORDERNAME.get('deliveryWay')).getStore().removeAll(false);
    				Ext.getCmp(ORDERNAME.get('deliveryWay')).getStore().add(
    					{
    						'code':'PICKSELF','codeDesc':i18n('i18n.order.PICKUP')//'自提'
    					},{
    						'code':'DELIVERY_STOCK','codeDesc':i18n('i18n.order.DELIVERYSTOCK')//'送货进仓'
    					},{
    						'code':'PICKUPSTAIRS','codeDesc':i18n('i18n.order.DELIVERYHOME')//'送货上楼'
    					},{
    						'code':'PICKNOTUPSTAIRS','codeDesc':i18n('i18n.order.DELIVERY')//'送货(不含上楼)'
    					},{
    						'code':'INNER_PICKUP','codeDesc':i18n('i18n.order.INNERPICKUP')//'内部带货自提'
    					}
    				);
     				if(isContractMember){
     					var deliveryWayStore=Ext.getCmp(ORDERNAME.get('deliveryWay')).getStore();
     					deliveryWayStore.add(
     						{
    							'code':'FREE_DELIVERY','codeDesc':i18n('i18n.order.FREEDELIVERY')//'免费送货'
    						}
     					);
     				}
    				Ext.getCmp(ORDERNAME.get('deliveryWay')).setValue('PICKSELF');//默认值
    			      /**			
    				    * 修改人：张斌
    					*修改时间：2013-7-27 10:17
    					*原有内容：无（新增）
    					*修改原因：设置回原始的签收单
    				  */
    					//begin
    				if(!Ext.isEmpty(Ext.getCmp(ORDERNAME.get('signDocuments')))){
        				Ext.getCmp(ORDERNAME.get('signDocuments')).getStore().removeAll(false);//签收单
        				Ext.getCmp(ORDERNAME.get('signDocuments')).getStore().add(
        						{
        							'code':'NO_RETURN_SIGNED','codeDesc':i18n('i18n.order.NO_RETURN_SIGNED')//'无需返单'
        						},{
        							'code':'CUSTOMER_SIGNED_ORIGINAL','codeDesc':i18n('i18n.order.CUSTOMER_SIGNED_ORIGINAL')//'客户签收单原件返回'
        						},{
        							'code':'CUSTOMER_SIGNED_FAX','codeDesc':i18n('i18n.order.CUSTOMER_SIGNED_FAX')//'客户签收单传真返回'
        						},{
        							'code':'BILL_SIGNED_FAX','codeDesc':i18n('i18n.order.BILL_SIGNED_FAX')//'运单到达联传真返回'
        						}
            				);
        				Ext.getCmp(ORDERNAME.get('signDocuments')).setValue('');//默认空
    				}

    				//end
    			}
    		     /**			
				    * 修改人：张斌
					*修改时间：2013-7-27 10:17
					*原有内容：无（新增）
					*修改原因：选择包裹“签收单”和“提货方式”改变
				  */
					//begin
    			else if(json.orderView.order.transportMode=='PACKAGE'){//包裹
    				if(Ext.isEmpty(Ext.getCmp('recevicGoodsInfoID'))
    						||Ext.isEmpty(Ext.getCmp('callCarsID'))
    						||Ext.isEmpty(Ext.getCmp('commitOrderId'))){
    					
    				}else{
    					Ext.getCmp('recevicGoodsInfoID').disable();
				    	Ext.getCmp('recevicGoodsInfoID').hide();
				    	Ext.getCmp('callCarsID').setText('约车');
				    	Ext.getCmp('commitOrderId').setText(i18n('i18n.order.commit'));
				    	isBookVehicle="0";//取消约车
				    	Ext.getCmp('callCarsID').setDisabled(true);
    				}
    				Ext.getCmp(ORDERNAME.get('deliveryWay')).getStore().removeAll(false);//提货方式
    				Ext.getCmp(ORDERNAME.get('deliveryWay')).getStore().add(
    						{
    							'code':'PICKSELF','codeDesc':i18n('i18n.order.PICKUP')//'自提'
    						},{
    							'code':'DELIVER_UP','codeDesc':i18n('i18n.order.DELIVER_UP')//'送货'
    						},{
        						'code':'INNER_PICKUP','codeDesc':i18n('i18n.order.INNERPICKUP')//'内部带货自提'
        					}
        				);
    				Ext.getCmp(ORDERNAME.get('deliveryWay')).setValue('DELIVER_UP');//默认送货
    				if(!Ext.isEmpty(Ext.getCmp(ORDERNAME.get('signDocuments')))){
    					Ext.getCmp(ORDERNAME.get('signDocuments')).getStore().removeAll(false);//签收单
        				Ext.getCmp(ORDERNAME.get('signDocuments')).getStore().add(
        						{
        							'code':'NO_RETURN_SIGNED','codeDesc':i18n('i18n.order.NO_RETURN_SIGNED')//'无需返单'
        						},{
        							'code':'CUSTOMER_SIGNED_ORIGINAL','codeDesc':i18n('i18n.order.CUSTOMER_SIGNED_ORIGINAL')//'客户签收单原件返回'
        						},{
        							'code':'CUSTOMER_SIGNED_FAX','codeDesc':i18n('i18n.order.CUSTOMER_SIGNED_FAX')//'客户签收单传真返回'
        						}
            				);
        				Ext.getCmp(ORDERNAME.get('signDocuments')).reset();//默认为空
    				}
    			}
    			//end
    			else if(json.orderView.order.transportMode=='JZKY'){
    				if(!Ext.isEmpty(Ext.getCmp('callCarsID'))){
    					Ext.getCmp('callCarsID').setDisabled(false);
    				}
    				Ext.getCmp(ORDERNAME.get('deliveryWay')).getStore().removeAll(false);
    				Ext.getCmp(ORDERNAME.get('deliveryWay')).getStore().add(
    						{
    							'code':'SELF_PICKUP','codeDesc':i18n('i18n.order.SELFPICKUP')//'自提(不含机场提货费)'
    						},{
    							'code':'FREE_PICKUP','codeDesc':i18n('i18n.order.FREEPICKUP')//'免费自提'
    						},{
    							'code':'PICKONAIEPORT','codeDesc':i18n('i18n.order.AIRPORTPICKUP')//'机场自提'
    						},{
    							'code':'DELIVERY_STOCK','codeDesc':i18n('i18n.order.DELIVERYSTOCK')//'送货进仓'
    						},{
    							'code':'PICKUPSTAIRS','codeDesc':i18n('i18n.order.DELIVERYHOME')//'送货上楼'
    						},{
    							'code':'PICKNOTUPSTAIRS','codeDesc':i18n('i18n.order.DELIVERY')//'送货(不含上楼)'
    						}
        				);
     				if(isContractMember){
     					var deliveryWayStore=Ext.getCmp(ORDERNAME.get('deliveryWay')).getStore();
     					deliveryWayStore.add(
     						{
    							'code':'FREE_DELIVERY','codeDesc':i18n('i18n.order.FREEDELIVERY')//'免费送货'
    						}
     					);
     				}
    				Ext.getCmp(ORDERNAME.get('deliveryWay')).setValue('SELF_PICKUP');
        		     /**			
    				    * 修改人：张斌
    					*修改时间：2013-7-27 10:17
    					*原有内容：无（新增）
    					*修改原因：设置回原始的签收单
    				  */
    				if(!Ext.isEmpty(Ext.getCmp(ORDERNAME.get('signDocuments')))){
        				Ext.getCmp(ORDERNAME.get('signDocuments')).getStore().removeAll(false);//签收单
        				Ext.getCmp(ORDERNAME.get('signDocuments')).getStore().add(
        						{
        							'code':'NO_RETURN_SIGNED','codeDesc':i18n('i18n.order.NO_RETURN_SIGNED')//'无需返单'
        						},{
        							'code':'CUSTOMER_SIGNED_ORIGINAL','codeDesc':i18n('i18n.order.CUSTOMER_SIGNED_ORIGINAL')//'客户签收单原件返回'
        						},{
        							'code':'CUSTOMER_SIGNED_FAX','codeDesc':i18n('i18n.order.CUSTOMER_SIGNED_FAX')//'客户签收单传真返回'
        						},{
        							'code':'BILL_SIGNED_FAX','codeDesc':i18n('i18n.order.BILL_SIGNED_FAX')//'运单到达联传真返回'
        						}
            				);
        				Ext.getCmp(ORDERNAME.get('signDocuments')).setValue('');//默认空
    				}
    			}
 				//如果到达网点是偏线， 则运输方式只为精准空运和汽运偏线
 				if(!Ext.isEmpty(json.orderView.endDept)){
 					var dept = new PointModel(json.orderView.endDept);
 					Ext.getCmp(ORDERNAME.get('receivingToPoint')).getStore().add(dept);
 	 				Ext.getCmp(ORDERNAME.get('receivingToPoint')).setValue(json.orderView.endDept.deptCode);
 	 				/*if(dept.get(ORDERNAME.get('ifOutward'))){
 	 					Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().removeAll();
 	 					Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().add({'code':'JZKY','codeDesc':i18n('i18n.order.playTranceJZ')});
 	 					Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().add({'code':'AGENT_VEHICLE','codeDesc':i18n('i18n.order.carTrance')});
 	 					Ext.getCmp(ORDERNAME.get('modeOfTransportation')).setValue('');
 	 				}else{
 	 					Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().removeAll();
 	 					Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().add(dataDictionary.TRANS_TYPE);
 	 				}*/
 				}
 				//设置普通元素的初始值
 	 			Ext.getCmp('transportationAndService_form').getForm().loadRecord(orderModel);
 	 			Ext.getCmp('goodsInfo_form').getForm().loadRecord(orderModel);
 	 			Ext.getCmp('consignor_form').getForm().loadRecord(orderModel);
 	 			Ext.getCmp('receiving_form').getForm().loadRecord(orderModel);
 	 			Ext.getCmp('basicInfo_form_callcar').getForm().loadRecord(orderModel);
 	 			if(orderModel.get(ORDERNAME.get('sendDay'))=='NOTHING'){
 	 				Ext.getCmp(ORDERNAME.get('codAmount')).setValue(null);
 	 			}
 	 		    //设置如果原来选择了A、B这里也可以选择
 	 			var goodsType = orderModel.get(ORDERNAME.get('goodsType'))
 	 			if(goodsType=='A'||goodsType=='B'){
 	 				Ext.getCmp('goodsTypeA').setDisabled(false);
 	 				Ext.getCmp('goodsTypeB').setDisabled(false);
 	 				Ext.getCmp(ORDERNAME.get('goodsType')).allowBlank = false;
 	 			}
 	 			if(beginDeptIsHashGoodsType||beginDeptIsHashGoodsType){
 	 				Ext.getCmp('goodsTypeA').setDisabled(false);
 	 				Ext.getCmp('goodsTypeB').setDisabled(false);
 	 				Ext.getCmp(ORDERNAME.get('goodsType')).allowBlank = false;
 	 			}
 	 			//如果原来没有，但是
 	 			var recevingOfferTel = orderModel.get(ORDERNAME.get('receivingPhone'));
 	 		    //将收货方联系人电话号码拆分
 	 			if(!Ext.isEmpty(recevingOfferTel)){
 	 				recevingOfferTel = DButil.replacePhone(recevingOfferTel);
 	 				if(recevingOfferTel.split('-').length == 1){
 						Ext.getCmp(ORDERNAME.get('receivingPhone')).setValue(recevingOfferTel.split('-')[0]);
 					}else if(recevingOfferTel.split('-').length == 2){
 						var offerTelArray = recevingOfferTel.split('-');
 						if(offerTelArray[0].length>offerTelArray[1].length){
 							Ext.getCmp(ORDERNAME.get('receivingPhone')).setValue(recevingOfferTel.split('-')[0]);
 							Ext.getCmp(ORDERNAME.get('receivingExtension')).setValue(recevingOfferTel.split('-')[1]);
 						}
 						if(offerTelArray[1].length>offerTelArray[0].length){
 							Ext.getCmp(ORDERNAME.get('receivingPhone')).setValue(recevingOfferTel.split('-')[1]);
 							Ext.getCmp(ORDERNAME.get('receivingAreaCode')).setValue(recevingOfferTel.split('-')[0]);
 						}
 					}else{
 						Ext.getCmp(ORDERNAME.get('receivingExtension')).setValue(recevingOfferTel.split('-')[2]);
 						Ext.getCmp(ORDERNAME.get('receivingAreaCode')).setValue(recevingOfferTel.split('-')[0]);
 						Ext.getCmp(ORDERNAME.get('receivingPhone')).setValue(recevingOfferTel.split('-')[1]);
 					}
				}
 	 			var consignorOfferTel = orderModel.get(ORDERNAME.get('consignorPhone'));
 	 			 //将发货方联系人电话号码拆分
 				if(!Ext.isEmpty(consignorOfferTel)){
 					consignorOfferTel = DButil.replacePhone(consignorOfferTel);
 					if(consignorOfferTel.split('-').length == 1){
 						Ext.getCmp(ORDERNAME.get('consignorPhone')).setValue(consignorOfferTel.split('-')[0]);
 					}else if(consignorOfferTel.split('-').length == 2){
 						var offerTelArray = consignorOfferTel.split('-');
 						if(offerTelArray[0].length>offerTelArray[1].length){
 							Ext.getCmp(ORDERNAME.get('consignorPhone')).setValue(consignorOfferTel.split('-')[0]);
 							Ext.getCmp(ORDERNAME.get('consignorExtension')).setValue(consignorOfferTel.split('-')[1]);
 						}
 						if(offerTelArray[1].length>offerTelArray[0].length){
 							Ext.getCmp(ORDERNAME.get('consignorPhone')).setValue(consignorOfferTel.split('-')[1]);
 							Ext.getCmp(ORDERNAME.get('consignorAreaCode')).setValue(consignorOfferTel.split('-')[0]);
 						}
 					}else{
 						Ext.getCmp(ORDERNAME.get('consignorExtension')).setValue(consignorOfferTel.split('-')[2]);
 						Ext.getCmp(ORDERNAME.get('consignorAreaCode')).setValue(consignorOfferTel.split('-')[0]);
 						Ext.getCmp(ORDERNAME.get('consignorPhone')).setValue(consignorOfferTel.split('-')[1]);
 					}
				}
 				//设置地址
 				var consignorProvince = '';
 				if(!Ext.isEmpty(orderModel.get(ORDERNAME.get('consignorProvince')))){
 					consignorProvince = orderModel.get(ORDERNAME.get('consignorProvince'))+'-';
 				}
 				var consignorCity = '';
 				if(!Ext.isEmpty(orderModel.get(ORDERNAME.get('consignorCity')))){
 					consignorCity = orderModel.get(ORDERNAME.get('consignorCity'))+'-';
 				}
 				var consignorCounty = '';
 				if(!Ext.isEmpty(orderModel.get(ORDERNAME.get('consignorCounty')))){
 					consignorCounty = orderModel.get(ORDERNAME.get('consignorCounty'))
 				}
 				var consignorAreas = consignorProvince+consignorCity+consignorCounty;
 				Ext.getCmp('consignorAreaSelect').setRawValue(consignorAreas);
 				var receivingProvince = '';
 				if(!Ext.isEmpty(orderModel.get(ORDERNAME.get('receivingProvince')))){
 					receivingProvince = orderModel.get(ORDERNAME.get('receivingProvince'))+'-';
 				}
 				var receivingCity = '';
 				if(!Ext.isEmpty(orderModel.get(ORDERNAME.get('receivingCity')))){
 					receivingCity = orderModel.get(ORDERNAME.get('receivingCity'))+'-';
 				}
 				var receivingCounty = '';
 				if(!Ext.isEmpty(orderModel.get(ORDERNAME.get('receivingCounty')))){
 					receivingCounty = orderModel.get(ORDERNAME.get('receivingCounty'))
 				}
 				var receivingAreas = receivingProvince+receivingCity+receivingCounty;
 				Ext.getCmp('receivingAreaSelect').setRawValue(receivingAreas);
 				
 				if(orderSource=='WEIXIN' && isPermission('/order/compleWeixinOrder.action')){
 	 				MessageUtil.showMessage(i18n('i18n.order.compleWeixinWarn'));
 	 			}
 			};
 			var failureFn = function(json){
 				btn.setDisabled(false);
 				MessageUtil.showErrorMes(json.message);
 			};
 			var orderId = Ext.getCmp('orderGrid').getSelectionModel().getSelection()[0].data.id;
 			var custNumber = Ext.getCmp('orderGrid').getSelectionModel().getSelection()[0].data.shipperNumber;
 			var param = {'orderView':{'orderId':orderId},'custNumber':custNumber};
// 			if(orderSource=='WEIXIN' && isPermission('/order/compleWeixinOrder.action')){
// 				MessageUtil.showMessage(i18n('i18n.order.compleWeixinWarn'),function(){
// 					btn.setDisabled(true);
// 		 			orderbaseDataControl.isUpdateOrderable(param,successFn,failureFn);
// 				});
// 			}else{
//	 			btn.setDisabled(true);
//	 			orderbaseDataControl.isUpdateOrderable(param,successFn,failureFn);
//	 		}
 			btn.setDisabled(true);
 			orderbaseDataControl.isUpdateOrderable(param,successFn,failureFn);
 			
 		},
 		
 		
 		
 		/**.
 		 * <p>
 		 * 延迟发货操作</br>
 		 * </p>
 		 * @author  李春雨
 		 * @时间    2013-09-03
 		 */
 		delayOrder:function(){
 			//获取订单时间开始时间
            var startDate = Ext.getCmp('orderGrid').getSelectionModel( ).getSelection()[0].data.createDate;
            if(null==startDate){
            	MessageUtil.showMessage(i18n('i18n.order.hasNOTime'));
            	return;
            }
            //获取现在时间
            var endDate = new Date();
            var temp = (endDate.getTime()-startDate.getTime())/(24*60*60*1000);
            if(temp>3){
            	MessageUtil.showMessage(i18n('i18n.order.moreThanThreeDays'));
            	return;
            }
        	//创建点单延时操作窗口
            if(Ext.isEmpty(Ext.getCmp('delayOrderWindow'))){
            	new DelayOrderWindow().show();
            }else{
            	Ext.getCmp('delayOrderWindow').show();
            }
 		},
 		
 		
 		
 		
 		
 		/**.
 		 * <p>
 		 * 获取受理界面元素</br>
 		 * </p>
 		 * @author  张斌
 		 * @时间    2012-03-19
 		 */
 		getItems:function(orderStatus){
 			var me = this;
 			var field = ['code','codeDesc'];
 			//创建gird的columns
 			var columns = [{
 		        xtype: 'rownumberer',
 		        width: 35,
 		        text     : i18n('i18n.order.serialNumber')
 		        //sortable: false
 		    },{
 		        text     : i18n('i18n.order.pressOrderNum'),
 		        //sortable : false,
 		        width:65,
 		        dataIndex: ORDERNAME.get('hastenCount')
 		    }, //增加列"下单时间"，用于显示列表中订单最后一个提交保存的时间 		  
 		   {
 		        text     : i18n('i18n.order.createOrderTime'),
 		        //sortable : false,
 		        dataIndex: ORDERNAME.get('orderTime'),
 		       renderer : function(value){
	            	if(!Ext.isEmpty(value)){
	            		return value.format('yyyy-MM-dd hh:mm');
	            	}
	            },
 		        width:120
 		        
 		    },{
 	            text     : i18n('i18n.order.whetherheGoods'),
 	            width:65,
 	            //sortable : false,
 	            dataIndex: ORDERNAME.get('consignorIsCargo'),
 	            renderer : DButil.changeTrueAndFalse
 	        },
 	        {
 	            text     : i18n('i18n.order.orderResource'),
 	            //sortable : true,
 	           width:65,
 	            dataIndex: ORDERNAME.get('resource'),
 	            renderer : function(value){
 	            	return OrderNumberRuleAction.renderResource(value);
	            }
 	        },
 	        {
 	            text     : i18n('i18n.order.comeFromPoint'),
 	           width:65,
 	            //sortable : true,
 	            dataIndex: ORDERNAME.get('startStationName')
 	        },
 	        {
 	            text     : i18n('i18n.order.feedbackInfo'),
 	            width:65,
 	            //sortable : true,
 	            dataIndex: 'feedbackInfo',
	            renderer : function(value){
	            	return DButil.changeDictionaryCodeToDescrip(value,dataDictionary.FEEDBACK_INFO);
	            }
 	        },
 	        {
 	            text     : i18n('i18n.order.toPoint'),
 	           width:65,
 	            //sortable : true,
 	            dataIndex: 'receivingToPointName'
 	        },
 	        {
 	            text     : i18n('i18n.order.acceptDept'),
 	           width:65,
 	            //sortable : true,
 	            dataIndex: ORDERNAME.get('acceptDeptName')
 	        },
 	        {
 	            text     : i18n('i18n.order.orderStatus'),
 	            //sortable : true,
 	           width:65,
 	            dataIndex: ORDERNAME.get('orderStatus'),
 	            renderer : function(value){
 	            	return DButil.changeDictionaryCodeToDescrip(value,dataDictionary.ORDER_STATUS);
 	            }
 	        },
 	        {
 	            text     : i18n('i18n.order.consignorName'),
 	            //sortable : true,
 	           width:80,
 	            dataIndex: ORDERNAME.get('consignorName')
 	        },
 	        {
 	            text     : i18n('i18n.order.comeToCity'),
 	            //sortable : true,
 	           width:65,
 	            dataIndex: ORDERNAME.get('receivingCity')
 	        },
 	        {
 	            text     : i18n('i18n.order.deliveryContactPhone'),
 	            //sortable : true,
 	           width:80,
 	            dataIndex: ORDERNAME.get('consignorPhone')
 	        },{
 	            text     : i18n('i18n.order.goodsName'),
 	            //sortable : true,
 	           width:65,
 	            dataIndex: ORDERNAME.get('goodsName')
 	        },{
 	            text     : i18n('i18n.order.weight'),
 	            //sortable : true,
 	            dataIndex: ORDERNAME.get('goodsTotalWeight')
// 	            renderer : function(value){
////	            	return value+i18n('i18n.order.kg');
// 	            	if (value==0) {
// 	            		return null;
//					}
//	            }
 	        },{
 	            text     : i18n('i18n.order.totalVolume'),
 	            //sortable : true,
 	            dataIndex: ORDERNAME.get('goodsTotalVolume')
// 	            renderer : function(value){
////	            	return value+i18n('i18n.order.ccubicMetre');
// 	            	if (value==0) {
// 	            		return null;
//					}
//	            }
 	        }, {
 	        	text     : i18n('i18n.order.memberType'),
 	        	//sortable : true,
 	        	dataIndex: ORDERNAME.get('memberType'),
 	        	renderer : function(value){
 	        		if (!Ext.isEmpty(value)) {
						return DButil.changeDictionaryCodeToDescrip(value,dataDictionary.ALIBABA_MEMBERTYPE);
					}
 	        	}
 	        },{
 	            text     : i18n('i18n.order.totalNumber'),
 	            //sortable : true,
 	            dataIndex: ORDERNAME.get('goodsTotalNumber')
// 	           renderer : function(value){
////	            	return value+i18n('i18n.order.parts');
// 	        	  if (value==0) {
//	            		return null;
//					}
//	            }
 	        },{
 	        	text     : i18n('i18n.order.modeOfTransportation'),
 	        	//sortable : true,
 	        	dataIndex: ORDERNAME.get('modeOfTransportation'),
 	        	renderer : function(value){
 	        		if (!Ext.isEmpty(value)) {
						return DButil.changeDictionaryCodeToDescrip(value,dataDictionary.TRANS_TYPE);
					}
 	        	}
 	        },{
 	        	text     : i18n('i18n.order.signDocuments1'),
 	        	//sortable : true,
 	        	dataIndex: ORDERNAME.get('signDocuments'),
 	        	renderer : function(value){
 	        		if (!Ext.isEmpty(value)) {
						return DButil.changeDictionaryCodeToDescrip(value,dataDictionary.SIGNBILL);
					}
 	        	}
 	        },{
 	        	text     : i18n('i18n.order.paymentWay'),
 	        	//sortable : true,
 	        	dataIndex: ORDERNAME.get('paymentWay'),
 	        	renderer : function(value){
 	        		if (!Ext.isEmpty(value)) {
						return DButil.changeDictionaryCodeToDescrip(value,dataDictionary.PAY_WAY);
					}
 	        	}
 	        },{
 	        	text     : i18n('i18n.order.deliveryWay'),
 	        	//sortable : true,
 	        	dataIndex: ORDERNAME.get('deliveryWay'),
 	        	renderer : function(value){
 	        		if (!Ext.isEmpty(value)) {
						return DButil.changeDictionaryCodeToDescrip(value,dataDictionary.PICKGOODTYPE);
					}
 	        	}
 	        }];
 			return [new OrderQueryForm({'isInitPointToSearch':true,'storeId':'processOrderStore','defaultValue':['ALL','WAIT_ACCEPT'],'orderStatus':orderStatus,width:791,height:95}),
 			        new SearchOrderButtonPanel({width:791}),
			 		new OrderGrid({
			 				'store':orderbaseDataControl.getProcessStore(),
			 				'columns':columns,
			 				'storeId':'processOrderStore',
			 				'model':'MULTI'}),
			 		new BottomPanle()]
 		},
 	    initComponent:function(){
 	    	var me = this;
 	    	var ORDER_STATUS = new Array();
 	    	var orderStatus = dataDictionary.ORDER_STATUS;
 	    	for(var i=0;i<orderStatus.length;i++){
 	    		if(orderStatus[i].code!='WAIT_ALLOT'){
 	    			ORDER_STATUS.push(orderStatus[i])
 	    		}
 	    	}
 	    	me.items = me.getItems(ORDER_STATUS);
 	    	  Ext.getCmp('orderGrid').getStore().on('load',function(s,records){
 	    		         var girdcount=0;
 	    		         s.each(function(r){
 	    		             if(r.get('orderStatus')=='WAIT_ACCEPT'){
 	    		               var cells =  Ext.getCmp('orderGrid').getView().getNodes()[girdcount].children;
 	    		               for(var i= 0;i<cells.length;i++){
 	    		            	  cells[i].style.backgroundColor='#FFFFcd';
 	    		               }
 	    		             }else if(r.get('orderStatus')=='ACCEPT'){
 	    		                  var cells =  Ext.getCmp('orderGrid').getView().getNodes()[girdcount].children;
					    		  for(var i= 0;i<cells.length;i++){
					            	  cells[i].style.backgroundColor='#FFcdcd';
					               }
 	    		             }else if(r.get('orderStatus')=='GOBACK'){
 	    			 				var cells =  Ext.getCmp('orderGrid').getView().getNodes()[girdcount].children;
 	    			 				for(var i= 0;i<cells.length;i++){
 	    			 					cells[i].style.backgroundColor='#c7edcc';
 	    			 				}
 	    			 		 }else if(r.get('orderStatus')=='WAIT_ALLOT'&&!Ext.isEmpty(r.get('startStation'))){
 	    			 			 var cells =  Ext.getCmp('orderGrid').getView().getNodes()[girdcount].children;
 	    			 			 for(var i= 0;i<cells.length;i++){
 	    			 				 cells[i].style.backgroundColor='#FF0000';
 	    			 			 }
 	    			 		 }
 	    		             girdcount=girdcount+1;
 	    		         });
 	    	});
 	    	Ext.data.StoreManager.lookup(ORDERNAME.get('returnInfo')).add({'code':'KONG','codeDesc':i18n('i18n.order.blank')});
 	    	this.callParent();
 	    }
 	});

Ext.define('BottomPanle',{				//tab的父级panel
	extend:'BasicPanel',
	id:'bottomPanle',
	processOrderTabPanel:null,
	operFormPanel:null,
	autoScroll:true,
	layout:{type:'hbox'
	},
//	layout:'column',
	flex:'All even',
	width:'100%',
	heigth:400,
	initComponent:function(){
		var me=this;
		me.items=[
					new ProcessOrderTabPanel(),
					{width:5},new OperFormPanel({margin:'14 0 0 0'})
					];
		this.callParent();
	}
})

Ext.define('OperFormPanel',{
	extend:'OperBtnFormPanel',
	items:null,
	width:175,
	height:395,
	autoScroll:false,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		var field = ['code','codeDesc'];
		return [{
			layout:{
				type:'table',
				columns:1
			},
			defaultType:'textfield',
			defaults:{
				width:150
			},
			items:[{
				   xtype:'titledisplayfield',
				   style:'padding-top:0',
				   value:i18n('il8n.order.pleaseInputFeedbackInfo')
			},{
                  xtype:          'combo',
                  mode:           'local',
                  triggerAction:  'all',
                  id:ORDERNAME.get('returnInfo'),
                  forceSelection: true,
                  editable:false,
                  name:           ORDERNAME.get('returnInfo'),
                  displayField:   'codeDesc',
                  valueField:     'code',
                  queryMode: 'local',
                  store:DButil.getStore(ORDERNAME.get('returnInfo'),null,field,dataDictionary.FEEDBACK_INFO),
                  listeners:{
                  	select:function(th,record){
                  		if(th.getValue()=='KONG'){
                  			Ext.getCmp(ORDERNAME.get('returnInfo')).reset();
                  		}
                  		if(th.getValue()=='USER_DEFINED'){
                  			Ext.getCmp('otherReason').setDisabled(false);
                  		}else{
                  			Ext.getCmp('otherReason').setDisabled(true);
                  		}
                  	},
                  	expand:function(){
                    	//消除火狐浏览器出险combox与弹出来的信息错位的BUG
                    		document.body.scrollLeft=0;
                			document.body.scrollTop=0;
                			document.getElementsByTagName("html")[0].style.overflowY="hidden";
                			document.getElementsByTagName("html")[0].style.overflowX="hidden";
                			//viewport.doLayout();
                  	}
                  	
                  }
              },{xtype: 'titledisplayfield',style:'padding-top:0',value:i18n('i18n.order.userDefined')},
               {
//                   xtype:  'textarea',
                   disabled:true,
                   maxLength:1000,
                   id:'otherReason'
               },{
	 	          	xtype:'basicvboxcenterpanel',
					height:220,
					autoScroll:false,
					items:[ {
		 	                    xtype:'button',
		 	                    text: i18n('i18n.order.processOrder'),//受理订单
		 	                    handler:function(btn){
		 	                    	//判断是否选择了一条数据
		 	                   	    var selection = Ext.getCmp('orderGrid').getSelectionModel( ).getSelection()[0];
		 	                       if(Ext.getCmp('orderGrid').getSelectionModel( ).getSelection().length!=1){
//		 	                       	DButil.showMessage(i18n('i18n.order.pleaseSelectOne'));
		 	                       	MessageUtil.showMessage(i18n('i18n.order.pleaseSelectOne'));
		 	                   			return;
		 	                   		}
		 	                   		var successFn = function(json){
		 	                   			btn.setDisabled(false);
		 	                   			MessageUtil.showInfoMes(json.message);
	 	                   				Ext.getCmp('orderGrid').getStore().loadPage(1);
	 	                   				if(!Ext.isEmpty(json.orderView.order.operationLogs)){
	 	                   				    Ext.data.StoreManager.lookup('orderOperationLogStore').loadData(json.orderView.order.operationLogs);
	 	                   				}
		 	                   			if(!Ext.isEmpty(json.orderView.order)){
		 	                   			    DButil.updateOrderModel(selection,new OrderModel(json.orderView.order));
		 	                   			    DButil.refreshView(new OrderModel(json.orderView.order));
		 	                   			}
		 	                   		};
		 	                   		var failureFn = function(json){
		 	                   			btn.setDisabled(false);
		 	                   			MessageUtil.showMessage(json.message);
		 	                   		};
		 	                   		var orderId = selection.data.id;
		 	                   		var feedbackInfo = Ext.getCmp(ORDERNAME.get('returnInfo')).getRawValue();
	 	                   			if(Ext.getCmp(ORDERNAME.get('returnInfo')).getValue()=='USER_DEFINED'){
	 	                   				feedbackInfo = Ext.getCmp('otherReason').getValue();
	 	                   			}
	 	                   			var param = {'orderView':{'orderId':orderId,'feedbackInfo':feedbackInfo}};
	 	                   			btn.setDisabled(true);
	 	                   		    orderbaseDataControl.processOrder(param,successFn,failureFn);
		 	                    }
		 	                },{
		 	                    xtype:'button',
		 	                    text: i18n('i18n.order.returnOrder'),//打会订单
		 	                    handler:function(btn){
		 	                    	DButil.commonOperator(orderbaseDataControl.returnOrder,true,true,btn);
		 	                    }
		 	                },{
		 	                    xtype:'button',
		 	                    text: i18n('i18n.order.updateOrder'),//修改订单
		 	                    handler:function(btn){
		 	                    	Ext.getCmp('processOrderView').updateOrder(btn);          	  	
		 	                    }
		 	                },{
		 	                    xtype:'button',
		 	                    text: i18n('i18n.order.failOrder'),//揽货失败
		 	                    handler:function(btn){
		 	                    	DButil.commonOperator(orderbaseDataControl.failOrder,true,true,btn);          	
		 	                    }
		 	                },{
		 	                    xtype:'button',
		 	                    text: i18n('i18n.order.printOrder'),//打印订单
		 	                    handler:function(){
		 	                    	var successFn = function( json ){
		 	                    		var orderModel = new OrderModel(json.order);
		 	                    		var deliveryWay = DButil.changeDictionaryCodeToDescrip(orderModel.get(ORDERNAME.get('deliveryWay')),dataDictionary.PICKGOODTYPE);
		 	                    		var paymentWay = DButil.changeDictionaryCodeToDescrip(orderModel.get(ORDERNAME.get('paymentWay')),dataDictionary.PAY_WAY);
		 	                    		var modeOfTransportation = DButil.changeDictionaryCodeToDescrip(orderModel.get(ORDERNAME.get('modeOfTransportation')),dataDictionary.TRANS_TYPE);
		 	                    		var signDocuments = DButil.changeDictionaryCodeToDescrip(orderModel.get(ORDERNAME.get('signDocuments')),dataDictionary.SIGNBILL);
		 	                    		var consignorIsCargo = DButil.changeTrueAndFalse(orderModel.get(ORDERNAME.get('consignorIsCargo')));
		 	                    		var sendDay = orderModel.get(ORDERNAME.get('sendDay'))
		 	                    		orderModel.set(ORDERNAME.get('deliveryWay'),deliveryWay);
		 	                    		orderModel.set(ORDERNAME.get('paymentWay'),paymentWay);
		 	                    		orderModel.set(ORDERNAME.get('modeOfTransportation'),modeOfTransportation);
		 	                    		orderModel.set(ORDERNAME.get('signDocuments'),signDocuments);
		 	                    		orderModel.set(ORDERNAME.get('consignorIsCargo'),consignorIsCargo);
		 	                    		//TODO:data.reciveLoanType需要转换
		 	                    		orderModel.set('reciveLoanType',DButil.changeDictionaryCodeToDescrip(orderModel.data.reciveLoanType,dataDictionary.REFUND_TYPE));
//		 	                    		orderModel.data.reciveLoanType = DButil.changeDictionaryCodeToDescrip(orderModel.data.reciveLoanType,dataDictionary.REFUND_TYPE);
	                            		this.printData = orderModel.data;
	                            		orderbaseDataControl.printOrder();
	                            	};
	                                var failureFn = function(json){
	                                	MessageUtil.showErrorMes(json.message);
	                                };
	                                if(Ext.getCmp('orderGrid').getSelectionModel( ).getSelection().length!=1){
	                                	MessageUtil.showMessage(i18n('i18n.order.pleaseSelectOne'));
	                            			return;
	                            	}
	                                var selection = Ext.getCmp('orderGrid').getSelectionModel( ).getSelection()[0];
	                                var id = selection.data.id;
		 	                    	var param = {'orderId':id};
		 	                    	orderbaseDataControl.getPrintOrderData(param,successFn,failureFn);
		 	                    }
		 	                },{
		 	                    xtype:'button',
		 	                    text: i18n('i18n.order.updateCallCar'),//导入约车
		 	                    handler:function(btn){
		 	            			if(Ext.getCmp('orderGrid').getSelectionModel( ).getSelection().length==0){
		 	            				MessageUtil.showMessage(i18n('i18n.order.pleaseSelectOne'));
		 	            				return;
		 	            			}
		 	            			var isAcceptGoods = Ext.getCmp('orderGrid').getSelectionModel().getSelection()[0].get(ORDERNAME.get('consignorIsCargo'));
		 	            			if(isAcceptGoods){
		 	            				Ext.getCmp('processOrderView').callCar(btn);
		 	            			}else{
		 	            				MessageUtil.showQuestionMes(i18n('i18n.order.orderisNotAcceptIsSureAccept'),
		 	            						function(e){
		 	            							if (e == 'yes') {
		 	            								Ext.getCmp('processOrderView').callCar(btn);
		 	            							}
		 	            				});
		 	            			}
		 	                    	
		 	                    }
		 	                },
		 	               /**
				   		        * <p>
								* 延迟发货按钮</br>
								* </p>
				   		        * @author 李春雨
				   		        * @时间    2013-09-03
				   		        */
				   		         {
			 	             		xtype:'button',
			 	                    text: i18n('i18n.order.delayOrder'),
			 	                    handler:function(){
			 	                    	//判断是否选择了数据
			 	                    	var selection = Ext.getCmp('orderGrid').getSelectionModel( ).getSelection()[0];
			 	                    	
			 	                    	if(Ext.getCmp('orderGrid').getSelectionModel( ).getSelection().length!=1){
			 	                        	MessageUtil.showMessage(i18n('i18n.order.pleaseSelectOne'));//请选择一条记录进行操作！
			 	                    		return;
			 	                    	}
			 	                    	var orderSource = selection.get(ORDERNAME.get('resource'));
				   		           		if (Ext.Array.contains(User.roleids,'4008303')) {
				   		             		if(orderSource!='CALLCENTER'&&orderSource!='CALLCENTER-HF'){
				   		             		MessageUtil.showMessage(i18n('i18n.delayOrder.cannotDelay'));
				   		     					return;
				   		     				}
				   		             	}
			 	                    	
			 	                    	//判断反馈信息是否为空
			 	                    	var feedbackInfo = Ext.getCmp(ORDERNAME.get('returnInfo')).getRawValue();
			 	                    	if(Ext.getCmp(ORDERNAME.get('returnInfo')).getValue()=='USER_DEFINED'){
			 	                    		feedbackInfo = Ext.getCmp('otherReason').getValue();
			 	                    		if(Ext.isEmpty(feedbackInfo)){
				 	                    		MessageUtil.showMessage(i18n('i18n.order.plaeasSelectFeedbackInfo'));//请选择反馈信息！
				 	                    		return;
				 	                    	}
			 	                    	}
			 	                    	if(Ext.isEmpty(feedbackInfo)){
			 	                    		//设置反馈信息
			 	                    		Ext.getCmp(ORDERNAME.get('returnInfo')).setRawValue(i18n('i18n.order.userRequest'));//用户要求延时揽货
			 	                    	}
				 	                      //是否延迟发货
				 	                    MessageUtil.showQuestionMes(i18n('i18n.order.isDelayOrder'),//是否延时发货
				 	                    	function(e){
			 	            					if (e == 'yes') {
			 	            						Ext.getCmp('processOrderView').delayOrder();
			 	            					}
				 	                    	}
				 	                    )
			 	                    }
					 	         }
			 	                
		 	                
//		 	                ,{
//		 	                    xtype:'button',
//		 	                    text: i18n('i18n.order.pressOrder'),
//		 	                    handler:function(){
//		 	                    	DButil.commonOperator(orderbaseDataControl.pressOrder,false,true);          	
//		 	                    }
//		 	                }
		 	             ]   
			}
			]		
		}];
	}
});

//---------------------------------约车------------


/**.
 * <p>
 * 接货信息FORM<br/>
 * </p>
 * @returns RecevicGoodsInfo 接货信息FORM	的EXT对象
 * @author  张斌
 * @时间    2012-03-15
 */
Ext.define('RecevicGoodsInfo', {
		extend:'PopTitleFormPanel',
		items:null,
		id : 'recevicGoodsInfo_form',
		/**.
	    * <p>
	    *选择网点时做的操作（当始发网点或到达网点所在城市包含在大小城市基础资料表中时，货物类型为必填项，且只能选择其中一种，否则货物类型为空且不可编辑；）</br>
	    * </p>
	    * @param records 网点数据
	    * @author  张斌
	    * @时间    2012-03-14
	    */
		selectPoint:function(record){
			var me= this;
			/*if(record.get(ORDERNAME.get('ifOutward'))){
				Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().removeAll();
				Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().add({'code':'JZKY','codeDesc':i18n('i18n.order.playTranceJZ')});
				Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().add({'code':'AGENT_VEHICLE','codeDesc':i18n('i18n.order.carTrance')});
				Ext.getCmp(ORDERNAME.get('modeOfTransportation')).setValue('');
			}else{
				Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().removeAll();
				Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().add(dataDictionary.TRANS_TYPE);
			}*/
			CommonOrderView.refreshTiHuoFangShi();
			if(Ext.getCmp('callCarWindow').beginDeptIsHashGoodsType||record.get(ORDERNAME.get('ifHashGoodsType'))){
				Ext.getCmp('goodsTypeA').setDisabled(false);
				Ext.getCmp('goodsTypeB').setDisabled(false);
				Ext.getCmp(ORDERNAME.get('goodsType')).allowBlank = false;
			}else{
				Ext.getCmp('goodsTypeA').setDisabled(true);
				Ext.getCmp('goodsTypeB').setDisabled(true);
				Ext.getCmp(ORDERNAME.get('goodsType')).allowBlank = true;
				Ext.getCmp(ORDERNAME.get('goodsType')).reset();
			}
		},	
		initUserDept:function(){
			var me = this;
			var successFn = function(json){
				if(!Ext.isEmpty(json.orderView.beginDept)){
					Ext.data.StoreManager.lookup(ORDERNAME.get('useVehicleDept')).add([json.orderView.beginDept]);
	       			Ext.getCmp(ORDERNAME.get('useVehicleDept')).setValue(json.orderView.beginDept.deptCode);
				}else{
					return ;
				}
			};
			var failureFn = function(){
				return ;
			};
			me.orderData.initPoint(null,ORDERNAME.get('useVehicleDept'),successFn,failureFn);
		},
        initVehicleTeam:function(){
            Ext.Ajax.request({
                url: '../order/vehicleHistory.action',
                success: function(response){
                    var data = Ext.decode(response.responseText).vehicleHistory;
                    if(data == null){
                        return;
                    }
                    var combo = Ext.getCmp('vehicleTeam');
                    var store = combo.getStore();
                    store.add({
                        deptCode: data.vehicleTeamCode,
                        deptName: data.vehicleTeamName
                    });
                    combo.setValue(data.vehicleTeamCode);
                }
            });
        },
		/**.
		    * <p>
		    * 获取ITEMS属性
		    * </br>
		    * </p>
		    * @author  张斌
		    * @时间    2012-03-15
		    */
		getItems:function(){
			var data = this.data.data;
			var address = data.receiverCustProvince + '-' + data.receiverCustCity + '-' + data.receiverCustArea;
			var address2 = data.receiverCustAddress;
			var me = this;
			var useViecheDept=null;
			var endDept=null;
			var defaultDate=Ext.Date.add(new Date(), Ext.Date.MINUTE, 3);
			return [{
				xtype: 'basicfiledset',
				width:'100%',
			    title: i18n('i18n.order.receiveInfo'),
			    layout: {type:'table',
			    	columns:4
			    },
			    defaults:{
		           	labelWidth:80,
		            width:210,
				        labelSeparator:''
		           },
			    items:[{
			    	xtype     : 'datetimefield',
                    name:         ORDERNAME.get('consignorCargoTime'),
	                id:'callCarFromDate',
	                fieldLabel: i18n('i18n.order.startReceiveGoodsTime'),
	                editable:       false,
	                allowBlank:false,
	                format: 'Y-m-d H:i',
	                value:defaultDate,
	                listeners : {
					select : function(th) {
						var fromDate = th.getValue();
						if (!DButil.ValidStartData(fromDate, defaultDate)) {
							th.setValue();
						}
						//结束时间自动增加2.5H
						Ext.getCmp('callCarToDate').setValue(Ext.Date.add(fromDate,Ext.Date.MINUTE,150));
					}
				}
				},{
					xtype     : 'datetimefield',
                    name:         ORDERNAME.get('consignorToCargoTime'),
                    id:'callCarToDate',
	                fieldLabel: i18n('i18n.order.endReceiveGoodsTime'),
	                editable:       false,
	                allowBlank:false,
	                format: 'Y-m-d H:i',
	               	listeners : {
					select : function(th) {
						var fromDate = Ext.getCmp('callCarFromDate').getValue();
						var toDate = th.getValue();
						if (!DButil.ValidStartDataAndEndDate(fromDate, toDate)) {
							MessageUtil.showMessage(i18n("i18n.order.endtimeSmallThanStartTime2.5"))
							th.reset();
						}
					}
				}
		        },{
                    xtype:          'combo',
                    mode:           'local',
                    triggerAction:  'all',
                    labelWidth:50,
                    width:180,
                    forceSelection: true,
                    fieldLabel: i18n('i18n.order.vehicleType'),
                    id:ORDERNAME.get('vehicleType'),
                    name:ORDERNAME.get('vehicleType'),
                    displayField:   'codeDesc',
                    valueField:     'code',
                    queryMode: 'local',
                    store:DButil.getStore(ORDERNAME.get('vehicleType'),null,['code','codeDesc'],dataDictionary.TRUCK_TYPE),
                    listeners:{
		  	        	change:function(comb){
		  	        		DButil.notSelectSetEmpty(comb);
		  	        	}
		  	       }
				},{
					xtype: 'checkboxgroup',
			        vertical: true,
			        style:'margin-right:0px;',
			        items: [
			            { boxLabel: i18n('i18n.order.tailBoard'), name:ORDERNAME.get('isTailBoard'), inputValue:true,style:'margin-right:0px;' }
				    ]
				},{
		  			xtype: 'combo',
		  			labelSeparator:'',
		  			maxLength:50,
		  			id:ORDERNAME.get('useVehicleDept'),
		            fieldLabel: i18n('i18n.order.useVehicleDept'),
		            name:ORDERNAME.get('useVehicleDept'),
		  	        store:me.orderData.getUserCarPointStore(),
		  	        //显示的字段
		  	        displayField:ORDERNAME.get('pointName'),
		  			//提交时的字段
		  	        valueField:ORDERNAME.get('pointCode'),
		  			//查询依据的字段
		  			queryParam:ORDERNAME.get('pointName'),
		  			minChars:1,
	            	forceSelection: false,
		  	        typeAhead: false,
		  	        hideTrigger:false,
		  	        allowBlank:false,
		  	        listConfig: {
		  	        	minWidth :280,
		  	            getInnerTpl: function() {
		  	            	 return '{'+ORDERNAME.get('pointName')+'}';
		  	            }
		  	        },
		  	        listeners:{
		  	        	change: function(comb){
		  	        		comb.store.removeAll();
		  	        		if(Ext.isEmpty(comb.getValue())){
		  	        			comb.setValue("");
		  	        	    }
		  	        	},
		  	        	select: function(th,records){
		  	        		var deptCode = records[0].get('deptCode');
		  	        		if(Ext.isEmpty(deptCode)){
		  	        			MessageUtil.showMessage(i18n('i18n.order.useDeptStandardCodeNUll'));
		  	          		    return;
		  	        		}
		  	        	},
		  	        	render: function(combo){
		  	        		Ext.Ajax.request({
		  	        			url: '../common/queryUserInfo.action',
		  	        			success: function(response){
		  	        				var result = Ext.decode(response.responseText);
		  	        				User=result.user;
		  	        				combo.getStore().add({
		  	        					'deptName': User.deptName,
		  	        					'deptCode': User.standardCode
		  	        				});
		  	        				combo.setValue(User.standardCode);
		  	        			}
		  	        		});
		  	        	}
		  	       },
		  	       pageSize: 10
				},{
					xtype: 'combo',
		  			labelSeparator:'',
		  			maxLength:50,
		  			id:ORDERNAME.get('vehicleTeam'),
		            fieldLabel: i18n('i18n.order.vehicleTeam'),
		            name:ORDERNAME.get('vehicleTeam'), //派车车队
		  	        store:me.orderData.getVehicleTeamPointStore(),
		  	        //显示的字段
		  	        displayField:ORDERNAME.get('pointName'),
		  			//提交时的字段
		  	        valueField:ORDERNAME.get('pointCode'),
		  			//查询依据的字段
		  			queryParam:ORDERNAME.get('pointName'),
		  			minChars:1,
		  			forceSelection: true,
		  	        typeAhead: false,
		  	        hideTrigger:false,
		  	        allowBlank:false,
		  	        listConfig: {
		  	        	minWidth :280,
		  	            getInnerTpl: function() {
		  	            	 return '{'+ORDERNAME.get('pointName')+'}';
		  	            }
		  	        },
		  	       listeners:{
		  	        	change:function(comb){
		  	        		comb.store.removeAll();
		  	        		if(Ext.isEmpty(comb.getValue())){
		  	        			comb.setValue("");
		  	        	    }
		  	        	},
		  	        	select:function(th,records){
		  	        		var deptCode = records[0].get('deptCode');
		  	        		if(Ext.isEmpty(deptCode)){
		  	        			MessageUtil.showMessage(i18n('i18n.order.teamStandardCodeNUll'));
		  	          		    return;
		  	        		}
		  	        	}
		  	        },
		  	        pageSize: 10
			    },{
			    	 colspan: 2,
                	 labelSeparator:'',
                	 xtype:'textfield',
                	 allowBlank:false,
                	 labelWidth:55,
                	 name:ORDERNAME.get('goodsPackagingMaterials'),
					 maxLength:30,
					 fieldLabel:i18n('i18n.order.packagingMaterialsSpan')//包装材料
                 },{
	            	 xtype: 'textfield',
	            	 fieldLabel: '收货人地址',
	            	 value: address,
	            	 readOnly: true
	             },{
	            	 xtype: 'textfield',
	            	 fieldLabel: '',
	            	 value : address2,
	            	 readOnly: true
	             },{
			    	name:ORDERNAME.get('receivingToPoint'),
		  			xtype: 'combo',
		  			labelSeparator:'',
		  			maxLength:50,
		  			width:180,
		  			labelWidth:55,
		  			id:ORDERNAME.get('receivingToPoint'),
		  			fieldLabel: i18n('i18n.order.eliveryOutlets'),
		  	        store:me.orderData.getArriveNetPointStore(),
		  	        //显示的字段
		  	        displayField:ORDERNAME.get('pointName'),
		  			//提交时的字段
		  	        valueField:'id',
		  			//查询依据的字段
		  			queryParam:ORDERNAME.get('pointName'),
		  			minChars:1,
	            	forceSelection: true,
		  	        typeAhead: false,
		  	        hideTrigger:false,
		  	        allowBlank: false,
		  	        listConfig: {
		  	        	minWidth :280,
		  	            getInnerTpl: function() {
		  	            	 return '{'+ORDERNAME.get('pointName')+'}';
		  	            }
		  	        },
		  	       listeners:{
			        	select:function(comb,records){
			        		comb.selectedRecord = records[0];
			        		me.selectPoint(records[0]);
			        	},
		  	        	change:function(comb){
		  	        		//comb.store.removeAll();
		  	        		if(Ext.isEmpty(comb.getValue())){
		  	        			 if(Ext.getCmp('callCarWindow').beginDeptIsHashGoodsType){
			  	                   	Ext.getCmp('goodsTypeA').setDisabled(false);
			  	           			Ext.getCmp('goodsTypeB').setDisabled(false);
			  	           			Ext.getCmp(ORDERNAME.get('goodsType')).allowBlank = false;
		  	                   }else{
			  	                   	Ext.getCmp('goodsTypeA').setDisabled(true);
			  	           			Ext.getCmp('goodsTypeB').setDisabled(true);
			  	           			Ext.getCmp(ORDERNAME.get('goodsType')).allowBlank = true;
			  	           		    Ext.getCmp(ORDERNAME.get('goodsType')).reset();
		  	                   }
		  	        	    }
		  	        		comb.store.removeAll();
		  	        		if(Ext.isEmpty(comb.getValue())){
		  	        			comb.setValue("");
		  	        	    }
		  	        	}
		  	       },
		  	       pageSize: 10
                 },{
                	 text: '收货人电子地图',
                	 style: {
                		 marginLeft: '10px'
                	 },
                	 width: 110,
                	 xtype: 'button',
                	 handler: function(){
                		 CommonOrderView.mapShow({
		            		 'proCityArea': address,
		            		 'otherAddress': address2 == null ? '' : address2,
		            		 'callBack':function(dept){
        	    				Ext.data.StoreManager.lookup(ORDERNAME.get('receivingToPoint')).removeAll();
        		       			me.changePointStandardcode(dept['deptNo']);
		            		 }
		            	 },'pickup&deliveryType=deliver');
                	 }
                 }]
			}];
		},
		changePointStandardcode:function(deptId){
			var me =this;
			var successFn = function(json){
				var beginDept = json.orderView.beginDept;
				if(!Ext.isEmpty(beginDept)){
						Ext.data.StoreManager.lookup(ORDERNAME.get('receivingToPoint')).add({
	    					'id':beginDept.id,
	    					'deptName':beginDept.deptName,
	    					'ifHashGoodsType':beginDept.ifHashGoodsType,
	    					'ifLeave':beginDept.ifLeave,
	    					'ifHomeDelivery':beginDept.ifHomeDelivery,
	    					'ifSelfDelivery':beginDept.ifSelfDelivery
	    				});
		       			Ext.getCmp(ORDERNAME.get('receivingToPoint')).expand();			
				}
			}
			var param = {'orderView':{'standardCode':deptId}};
			
			var failureFn=function(json){}
			me.orderData.changePointStandardcode(param,successFn,failureFn);
		},
		initComponent:function(){
			var me = this;
			me.items = me.getItems();
			me.initUserDept();
            me.initVehicleTeam();
			this.callParent();
		}
});
/**.
 * <p>
 * 约车界面</br>
 * </p>
 * @returns CallCarView 约车界面的EXT对象
 * @author  张斌
 * @时间    2012-03-15
 */
Ext.define('CallCarView', {
	extend:'BasicFormPanel',
	consignorForm:null,//发货方form
	basicInfoFrom:null,
	goodsInfoForm:null,//货物信息FORM
	transportationAndServiceFrom:null,//运输及服务FORM
	recevicGoodsInfo:null,//接货信息
	layout:'column',
	border:false,
	data:null,//后台传来的数据
    id:'callCarView_form',
    /**.
    * <p>
    * 约车数据提交进行的操作
    * </br>
    * </p>
    * @author  张斌
    * @时间    2012-03-13
    */
    commitCallCar:function(commitButton){
    	var me = this;
    	var vehicleModel = new VehicleModel();
    	var data = me.data 
    	Ext.getCmp('callCarView_form').getForm().updateRecord(data);
    	Ext.getCmp('callCarView_form').getForm().updateRecord(vehicleModel);
    	var successFn = function(json){
    		commitButton.setDisabled(false);
    		Ext.getCmp('callCarWindow').close();
    		MessageUtil.showInfoMes(json.message);
    		Ext.getCmp('orderGrid').store.loadPage(1);
//    		alert(22);
//    		var selection = Ext.getCmp('orderGrid').getSelectionModel( ).getSelection()[0];
//    		if(!Ext.isEmpty(json.orderView.order)){
//    			DButil.updateOrderModel(selection,new OrderModel(json.orderView.order));
//        		DButil.refreshView(new OrderModel(json.orderView.order));
//    		}
    		if(!Ext.isEmpty(json.orderView.order.operationLogs)){
    			Ext.data.StoreManager.lookup('orderOperationLogStore').loadData(json.orderView.order.operationLogs);
    		}
    	};
    	var failureFn = function(json){
    		commitButton.setDisabled(false);
    		if(Ext.isEmpty(json)){
    			MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
    		}else{
    			MessageUtil.showErrorMes(json.message);
    		}
    	};
    	//对DATA的值进行修改
    	if(Ext.getCmp('callCarView_form').getForm().isValid()){
    		var endStation = Ext.getCmp(ORDERNAME.get('receivingToPoint')).getValue();
		    var endStationName = Ext.getCmp(ORDERNAME.get('receivingToPoint')).getRawValue();
	 	    if(endStation==endStationName){
    			MessageUtil.showMessage(i18n('i18n.order.mustPickSattionSelectDept'));
    			Ext.getCmp(ORDERNAME.get('receivingToPoint')).setValue('');
				return ;
	    	}
	 	    var vehicleTeam = Ext.getCmp(ORDERNAME.get('vehicleTeam')).getValue();
	   		var vehicleTeamName = Ext.getCmp(ORDERNAME.get('vehicleTeam')).getRawValue();
	   		if(vehicleTeam==vehicleTeamName){
	   			MessageUtil.showMessage(i18n('i18n.order.mustTeamSelectDept'));
	   			Ext.getCmp(ORDERNAME.get('vehicleTeam')).setValue('');
					return ;
	   		}
	   		var useVehicleDept = Ext.getCmp(ORDERNAME.get('useVehicleDept')).getValue();
	   		var useVehicleDeptName = Ext.getCmp(ORDERNAME.get('useVehicleDept')).getRawValue();
	   		if(useVehicleDept==useVehicleDeptName){
	   			MessageUtil.showMessage(i18n('i18n.order.mustUseDeptSelectDept'));
	   			Ext.getCmp(ORDERNAME.get('useVehicleDept')).setValue('');
					return ;
	   		}
    		var consignorExtension = Ext.getCmp(ORDERNAME.get('consignorExtension')).getValue();//分机号
    		var consignorAreaCode= Ext.getCmp(ORDERNAME.get('consignorAreaCode')).getValue();//区号
    		var consignorPhone = Ext.getCmp(ORDERNAME.get('consignorPhone')).getValue();//主拨号码
    		if((!Ext.isEmpty(consignorAreaCode))&&(!Ext.isEmpty(consignorPhone))){
    			consignorPhone = '-'+consignorPhone;
			}
			if((!Ext.isEmpty(consignorPhone))&&(!Ext.isEmpty(consignorExtension))){
				consignorExtension = '-'+consignorExtension;
			}
    		var contactPhone = consignorAreaCode + consignorPhone + consignorExtension;
    		if(contactPhone=='--'){
				contactPhone = null;
			}
    		if(Ext.isEmpty(consignorPhone)){
				contactPhone = null;
			}
    		var startDate = Ext.getCmp('callCarFromDate').getValue().getTime();
    		var endDate = Ext.getCmp('callCarToDate').getValue().getTime();
    		var date = new Date();
			var newDate = new Date(date.getFullYear(),date.getMonth(),date.getDate(),date.getHours(),date.getMinutes(),0);
			if (startDate  < newDate.getTime()) {
				MessageUtil.showMessage(i18n("i18n.order.mustStartTimeThanNow"));
				Ext.getCmp('callCarFromDate').setValue();
				return;
			}
//    		if ((endDate-startDate)>3*24*60*60*1000) {
//    			DButil.showMessage(i18n('i18n.order.bettewCannotBiggerThanThree'));
//    			return;
//    		}
			if ((endDate - startDate) < 2.5 * 60 * 60 * 1000) {
				MessageUtil.showMessage(i18n("i18n.order.endtimeSmallThanStartTime2.5"));
				Ext.getCmp('callCarToDate').setValue();
				return;
			}
			if ((endDate - startDate) > 3 * 24 * 60 * 60 * 1000) {
				MessageUtil.showMessage(i18n('i18n.order.bettewCannotBiggerThanThree'));
				Ext.getCmp('callCarToDate').setValue();
				return;
			}
    		//发货方联系人手机号
    		var consignorMobilePhone = Ext.getCmp(ORDERNAME.get('consignorMobilePhone')).getValue();
    		//发货方固定电话和手机必填其一
    		if(Validator.isEmptyPhoneAndMobilePhone(contactPhone,consignorMobilePhone)){
    			MessageUtil.showMessage(i18n('i18n.order.consignorPhoneAndMobilePhone'));
    			  return ;
    		}
    		//获取省市区数据
			var consignorAreaAddress = Ext.getCmp('consignorAreaSelect').getRawValue();
			var consignorAreas = consignorAreaAddress.split('-');
			if(consignorAreas.length<3){
				MessageUtil.showMessage(i18n('i18n.order.areaAll'));
				return ;
			}else if(DButil.isHaveEmpty(consignorAreas)){
				MessageUtil.showMessage(i18n('i18n.order.areaAll'));
				return ;
			}
			//设置省市区
			if(Ext.isEmpty(consignorAreas[0])){
				data.set(ORDERNAME.get('consignorProvince'),'');
			}else{
				data.set(ORDERNAME.get('consignorProvince'),consignorAreas[0]);
			}
			if(Ext.isEmpty(consignorAreas[1])){
				data.set(ORDERNAME.get('consignorCity'),'');
			}else{
				data.set(ORDERNAME.get('consignorCity'),consignorAreas[1]);
			}
			if(Ext.isEmpty(consignorAreas[2])){
				data.set(ORDERNAME.get('consignorCounty'),'');
			}else{
				data.set(ORDERNAME.get('consignorCounty'),consignorAreas[2]);
			}
			if(Ext.isEmpty(Ext.getCmp(ORDERNAME.get('receivingToPoint')).getRawValue())){
				data.set(ORDERNAME.get('startStationName'),'');
			}else{
				data.set(ORDERNAME.get('startStationName'),Ext.getCmp(
						ORDERNAME.get('receivingToPoint')).getRawValue());
			}
			if(Ext.isEmpty(contactPhone)){
				data.set(ORDERNAME.get('consignorPhone'),'');
			}else{
				data.set(ORDERNAME.get('consignorPhone'),contactPhone);
			}
    		var param = {'orderView':{'order':data.data,'bookVehicleInfo':vehicleModel.data}};
    		commitButton.setDisabled(true);
    		orderbaseDataControl.bookVehicle(param,successFn,failureFn);
    	}
    },
    /**.
	    * <p>
	    * 获取ITEMS属性
	    * </br>
	    * </p>
	    * @author  张斌
	    * @时间    2012-03-13
	    */
    getItems:function(){
    	var me = this;
    	return  [ {
			columnWidth: 1,
			border: false,
			items : [me.basicInfoFrom]
		},{
			columnWidth: 1,
			border: false,
			items : [me.recevicGoodsInfo]
		},{
			columnWidth: 1,
			border: false,
			items : [me.consignorForm]
		},{
			columnWidth: 1,
			border: false,
			items : [me.goodsInfoForm]
		},{
			columnWidth: 1,
			border: false,
			items : [me.transportationAndServiceFrom]
		}
//		,{
//			columnWidth: 1,
//			border: false,
//			items : [new CallCarButtonPanel()]
//		}
		];
    },
    initComponent:function(){
    	var me = this;
    	me.recevicGoodsInfo = new RecevicGoodsInfo({'orderData':orderbaseDataControl,'data': me.data});
    	me.basicInfoFrom = new BasicInfoFromCallCar();
    	me.consignorForm = new ConsignorForm({'orderData':orderbaseDataControl,'extend':'PopTitleFormPanel','isInitPoint':false});
    	me.goodsInfoForm = new GoodsInfoForm({'orderData':orderbaseDataControl,'extend':'PopTitleFormPanel'});
    	me.transportationAndServiceFrom = new TransportationAndServiceFrom({'extend':'PopTitleFormPanel','orderData':orderbaseDataControl});
    	me.consignorForm.alias = 'widget.poptitleformpanel';
    	me.consignorForm.cls = 'popup_form_feildset';
    	me.goodsInfoForm.alias = 'widget.poptitleformpanel';
    	me.goodsInfoForm.cls = 'popup_form_feildset';
    	me.transportationAndServiceFrom.alias = 'widget.poptitleformpanel';
    	me.transportationAndServiceFrom.cls = 'popup_form_feildset';
    	me.items = me.getItems();
    	//设置哪些元素为hidden
    	DButil.setHiddenAndDestroy([
//        ORDERNAME.get('consignorIsCargo'),ORDERNAME.get('consignorCargoTime'),ORDERNAME.get('consignorToCargoTime'),
        ORDERNAME.get('consignorComeFromPoint'),'searchElectronicMap_consignor',ORDERNAME.get('goodsPackagingMaterials'),
        ORDERNAME.get('sendDay'),ORDERNAME.get('insuerAmount'),ORDERNAME.get('signDocuments'),
        ORDERNAME.get('codAmount'),ORDERNAME.get('waybillNum'),ORDERNAME.get('channelNumber'),ORDERNAME.get('textMessagesNotice')]);
    	//Ext.getCmp(ORDERNAME.get('textMessagesNotice')).setDisabled(true);    	
    	//@TODO 设置一些元素为readOnly
    	DButil.setReadOnly([ORDERNAME.get('consignorCustName')]);
    	DButil.clearListeners([ORDERNAME.get('consignorMobilePhone')]);
    	this.callParent();
    }
});
/**.
 * <p>
 * 导入约车WINDOW{CallCarWindow}</br>
 * </p>
 * @author  张斌
 * @时间    2012-03-15
 */
Ext.define('CallCarWindow',{
    extend:'PopWindow', 
    title: i18n('i18n.order.updateCallCar'),
    //height: 450,
	width: 805,
	resizable:false,
    autoScroll:true,
    modal:true,
    data:null,
    closeAction:'destroy',
    id:'callCarWindow',
    getItems:function(){
    	var me = this;
    	return [new CallCarView({'data':me.data})];
    },
    getFbar:function(){
    	return [{
			xtype:'button',
		    text: i18n('i18n.order.commit'),
		    id:'updateOrderButton',
		    handler: function(){
		    	var me = this;
	    		Ext.getCmp('callCarView_form').commitCallCar(me);
			}
		},{
			xtype:'button',
			id:'hideButton',
		    text: i18n('i18n.order.cancel'),
		    handler: function() {
		    	Ext.getCmp('callCarView_form').up().close();
		    }
		}]
    },
    initComponent:function(){
    	var me = this;
    	me.items = me.getItems();
    	me.fbar=me.getFbar();
    	this.callParent();
    },
    listeners:{
    	beforehide:function(){
    		Ext.data.StoreManager.removeAtKey(ORDERNAME.get('receivingToPoint'));
    		if(!Ext.isEmpty(Ext.getCmp('consignorAreaSelectAreaTabWindow'))){
    			Ext.getCmp('consignorAreaSelectAreaTabWindow').hide();
    		}
    		if(!Ext.isEmpty(Ext.getCmp('receivingAreaSelectAreaTabWindow'))){
    			Ext.getCmp('receivingAreaSelectAreaTabWindow').hide();
    		}
    		
    	},
    	//消除火狐浏览器出险combox与弹出来的信息错位的BUG
    	beforeshow:function(){
    		document.body.scrollLeft=0;
			document.body.scrollTop=0;
			document.getElementsByTagName("html")[0].style.overflowY="hidden";
			document.getElementsByTagName("html")[0].style.overflowX="hidden";
			//viewport.doLayout();
    	}
    }
});
9
