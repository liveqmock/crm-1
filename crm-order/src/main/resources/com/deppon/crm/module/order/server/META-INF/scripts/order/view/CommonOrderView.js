/**
 * .
 * <p>
 * 订单模块中要用到的重复的页面块<br/>
 * </p>
 * 
 * @author 张斌
 * @时间 2012-03-08
 */
CommonOrderView = function() {
};
CommonOrderView.refreshTiHuoFangShi = function(){
	var combo = Ext.getCmp(ORDERNAME.get('modeOfTransportation'));
	var yunshufangshi = combo.getSubmitValue();
	var combo2 = Ext.getCmp(ORDERNAME.get('receivingToPoint'));
	var data;
	if(combo2.selectedRecord){
		data = combo2.selectedRecord.data;
	}
	Ext.getCmp(ORDERNAME.get('deliveryWay')).getStore().removeAll(false);
	if(yunshufangshi == 'JZKY'){
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
			Ext.getCmp(ORDERNAME.get('deliveryWay')).getStore().add(
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
	else if(yunshufangshi=='PACKAGE'){//包裹
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
     else{
		if(data == null || (data.ifHomeDelivery && data.ifSelfDelivery)){
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
				Ext.getCmp(ORDERNAME.get('deliveryWay')).getStore().add(
					{
						'code':'FREE_DELIVERY','codeDesc':i18n('i18n.order.FREEDELIVERY')//'免费送货'
					}
				);
			}
			Ext.getCmp(ORDERNAME.get('deliveryWay')).setValue('PICKSELF');
		}else if(data.ifSelfDelivery){
			Ext.getCmp(ORDERNAME.get('deliveryWay')).getStore().add(
				{
					'code':'PICKSELF','codeDesc':i18n('i18n.order.PICKUP')//'自提'
				},{
					'code':'INNER_PICKUP','codeDesc':i18n('i18n.order.INNERPICKUP')//'内部带货自提'
				}
			);
			if(isContractMember){
				Ext.getCmp(ORDERNAME.get('deliveryWay')).getStore().add(
					{
						'code':'FREE_DELIVERY','codeDesc':i18n('i18n.order.FREEDELIVERY')//'免费送货'
					}
				);
			}
			Ext.getCmp(ORDERNAME.get('deliveryWay')).setValue('PICKSELF');
		}else if(data.ifHomeDelivery){
			Ext.getCmp(ORDERNAME.get('deliveryWay')).getStore().add(
				{
					'code':'DELIVERY_STOCK','codeDesc':i18n('i18n.order.DELIVERYSTOCK')//'送货进仓'
				},{
					'code':'PICKUPSTAIRS','codeDesc':i18n('i18n.order.DELIVERYHOME')//'送货上楼'
				},{
					'code':'PICKNOTUPSTAIRS','codeDesc':i18n('i18n.order.DELIVERY')//'送货(不含上楼)'
				}
			);
			if(isContractMember){
				Ext.getCmp(ORDERNAME.get('deliveryWay')).getStore().add(
					{
						'code':'FREE_DELIVERY','codeDesc':i18n('i18n.order.FREEDELIVERY')//'免费送货'
					}
				);
			}
			Ext.getCmp(ORDERNAME.get('deliveryWay')).setValue('PICKNOTUPSTAIRS');
		}else{
			Ext.getCmp(ORDERNAME.get('deliveryWay')).setValue(null);
		}
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
			//end
		}
	}
}
//是否为合同客户
var isContractMember=false;
/**
 * 电子地图调用方法
 * @param {} opt
 */
CommonOrderView.mapShow = function(opt, deliveryType) {
	String.prototype.replaceAll = function(ost, nst) {
		raRegExp = new RegExp(ost, "g");
		return this.replace(raRegExp, nst);
	}
	var newProCityArea = opt.proCityArea.replaceAll('#', '');
	var newOtherAddress = opt.otherAddress.replaceAll('#', '');
	var addressArray = newProCityArea.split('-');
	var parmArray = new Array();
	if (addressArray.length > 2) {
		parmArray.push('county=' + addressArray[2]);
	}
	if (addressArray.length > 1) {
		parmArray.push('city=' + addressArray[1]);
	}
	parmArray.push('province=' + addressArray[0]);
	parmArray.push('otherAddress=' + newOtherAddress);

	var checkForMessages = function() {
		if (window.returnValue) {
			clearInterval(sii);
			var resultArray = window.returnValue.substring(1).split('&');
			var result = {};
			for ( var val in resultArray) {
				var temp = resultArray[val].split('=');
				result[temp[0]] = temp[1];
			}
			if (result && opt.callBack) {
				opt.callBack(result);
			}
		}
	}
	var sii = setInterval(checkForMessages, 200);

	// window.showModalDialog("http://192.168.10.43/gis-biz/biz-destination/stationSearch.action?"
	// + parmArray.join('&'),self, 
	// 'dialogHeight:700px;dialogWidth:1100px;dialogTop:300px;dialogLeft:120px;edge:Raised;center:Yes;help:no;status:no;scroll:yes;resizable:no;');
	window
			.showModalDialog(
					"../order/map.action?" + parmArray.join('&')
							+ '&deliveryType=' + deliveryType,
					self,
					'dialogHeight:930px;dialogWidth:1300px;dialogTop:0px;dialogLeft:0px;edge:Raised;center:Yes;help:no;status:no;scroll:no;resizable:no;');				
};
// 设置Data层对象
var orderbaseDataControl =  (ORDERCONFIG.get("TEST"))? new OrderDataTest():new OrderData();
var isBookVehicle="0";//是否约车 0 不是约车
var fChange=false;//发货方电话是否改变
var sChange=false;//收货方电话是否改变
var isPageLoad = true;//是否页面加载
var regObject = {/* 正则对象集 */
	'multiMobileNumber':/^1\d{10}$|^\d{8}$/ /* 11位手机、8位手机*/
};
	
/*	var param = {'dataDictionaryKeys':[
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
		 };
		 var failureFn = function(json){
			 MessageUtil.showErrorMes(json.message);
		 };
	DButil.getBusinessDictionary(param,successFn,failureFn);*/	
	
	var keys=[
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
  	];
	//初始数据字典
	initDataDictionary(keys);
	
	var dataDictionary = DataDictionary;
	
// -------------------------------------界面待完成-----------------------------------------------------------------
/**
 * .
 * <p>
 * 运输及服务FORM<br/>
 * </p>
 * 
 * @returns TransportationAndService 运输及服务FORM 的EXT对象
 * {工单中有相同界面元素，如果订单中，该界面元素进行了修改，同时要修改工单的}
 * @author 张斌
 * @时间 2012-03-08
 */
Ext.define('TransportationAndServiceFrom', {
		extend:'TitleFormPanel',
		orderData : null,// 连接ACTION的对象
		items:null,
		id:'transportationAndService_form',
		/**
		 * .
		 * <p>
		 * 获取ITEMS属性 </br>
		 * </p>
		 * 
		 * @author 张斌
		 * @时间 2012-03-13
		 */
		getItems:function(){
			var me = this;
			var field = ['code','codeDesc'];
			return [{xtype: 'basicfiledset',
				    title: i18n('i18n.order.transportationAndService'),
				    layout:{
				    	type:'table',
				    	columns:4
				    },
				    defaults:{
				    		labelWidth:60,
					        width:180,
					        labelSeparator:''
			           },
				    items:[{
									 xtype: 'numberfield',
									decimalPrecision:2,
									minValue:0,
									maxValue:99999999.99,
									value:0,
									fieldLabel:i18n('i18n.order.insuerAmount'),//报价金额
									id:ORDERNAME.get('insuerAmount'),
									name: ORDERNAME.get('insuerAmount')
								},{
								   	xtype: 'numberfield',
									decimalPrecision:0,
									minValue:10,
									maxValue:9999999999999,
									width:160,
									value:null,
									fieldLabel:i18n('i18n.order.cod'),//代收货款
									labelWidth:60,
//									style:'margin-left:-25px;',
//									allowBlank:false,
									//disabled:true,
									readOnly:true,
									cls:'readonly',
									id:ORDERNAME.get('codAmount'),
									name: ORDERNAME.get('codAmount')
								},{
								      xtype: 'radiogroup',
								      vertical: true,
								      id:ORDERNAME.get('sendDay'),
								      //style:'margin-left:-25px;',
								      name: ORDERNAME.get('sendDay'),
								      //style:'margin-left:25px;',
								      width:160,
								      //padding:'0 0 0 25',
								      items: [{
								          inputValue: 'INTRADAY',
								          name: ORDERNAME.get('sendDay'),
								          boxLabel: i18n('i18n.order.nowReturn')//即日退
								      }, {
								          inputValue: 'NORMAL',
								          name: ORDERNAME.get('sendDay'),
								          boxLabel:i18n('i18n.order.threeDayReturn')//三日退
								      }, {
								          inputValue: 'NOTHING',
								          width:30,
								          name: ORDERNAME.get('sendDay'),
								          checked:true,
								          boxLabel:i18n('i18n.order.haveNull'),//无
								          listeners:{
								       	   change:function(th,newValue,oldvalue){
								       		   if(th.getValue()){
								       			   Ext.getCmp(ORDERNAME.get('codAmount')).setReadOnly(true);
								       			   Ext.getCmp(ORDERNAME.get('codAmount')).addCls('readonly');
								       			   Ext.getCmp(ORDERNAME.get('codAmount')).setValue(null);
								       		   }else{
								       			   Ext.getCmp(ORDERNAME.get('codAmount')).setReadOnly(false);
								       			   Ext.getCmp(ORDERNAME.get('codAmount')).removeCls('readonly');
								       		   }
								       	   }
								          }
								      }]
								   },{
										 xtype:'combo',
										 labelWidth:47,
										 style:'margin-left:-15px;',
								            fieldLabel: i18n('i18n.order.signDocuments'),//签收单
								            id:ORDERNAME.get('signDocuments'),
								            store: DButil.getStore(ORDERNAME.get('signDocuments'),null,field,dataDictionary.SIGNBILL),
								            name: ORDERNAME.get('signDocuments'),
								            queryMode: 'local',
								            forceSelection: true,
								            displayField: 'codeDesc',
								            valueField: 'code',
								            listConfig: {
								  	        	minWidth :190
								  	        }   
									},{
								   	 xtype:'combo',
								        fieldLabel: i18n('i18n.order.modeOfTransportation'),
								        store: DButil.getStore('normalModeOfTransportation',null,field,dataDictionary.TRANS_TYPE),
								        id:ORDERNAME.get('modeOfTransportation'),
								        name: ORDERNAME.get('modeOfTransportation'),
								        queryMode: 'local',
								        value: 'JZKH',
								        displayField: 'codeDesc',
								        valueField: 'code',
								        allowBlank: false,
								        forceSelection: true,
								        listeners:{
								        	select: function(me){
								        				CommonOrderView.refreshTiHuoFangShi();
								        				if(me.value!='JZKY'&& me.value!='PACKAGE'){
									        				if(!Ext.isEmpty(Ext.getCmp('callCarsID'))){
									        					Ext.getCmp('callCarsID').setDisabled(false);
									        				}
								        				}else if(me.value == 'PACKAGE'){//包裹
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
								        				}else if(me.value=='JZKY'){
								        					if(!Ext.isEmpty(Ext.getCmp('callCarsID'))){
								        						Ext.getCmp('callCarsID').setDisabled(false);
								        					}
								        				}
								        	}
								        }
								     },{ 
								  	 xtype:'combo',
								  	 width:220,
								  	 colspan:2,
								  	 fieldLabel:i18n('i18n.order.deliveryWay'),
								        store: DButil.getStore(ORDERNAME.get('deliveryWay'),null,field,dataDictionary.PICKGOODTYPE),
								        id:ORDERNAME.get('deliveryWay'),
								        name: ORDERNAME.get('deliveryWay'),
								        value:'PICKUP',
								        queryMode: 'local',
								        displayField: 'codeDesc',
								        valueField: 'code',
								        allowBlank:false,
									    forceSelection: true,
									    listeners:{
									    	added:function(me){
									    		me.store.removeAll(false);
									    		if(Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getValue()=='JZKY'){
									    			me.store.add(
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
									    		}else{
										    			me.store.add(
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
									    		}
									    		me.setValue('PICKSELF');
									    	}
									    }
								 },{
										 xtype:'combo',
										 style:'margin-left:-16px;',
								        fieldLabel: i18n('i18n.order.paymentWay'),
								        store: DButil.getStore(ORDERNAME.get('paymentWay'),null,field,dataDictionary.PAY_WAY),
								        id:ORDERNAME.get('paymentWay'),
								        name: ORDERNAME.get('paymentWay'),
								        forceSelection: true,
								        queryMode: 'local',
								        displayField: 'codeDesc',
								        valueField: 'code'
								},{
								      xtype: 'radiogroup',
								      vertical: true,
								      //allowBlank:false,
								      fieldLabel: i18n('i18n.order.textMessagesNotice'),
//								      style:'margin-right:18px;',
								      id:ORDERNAME.get('textMessagesNotice'),
								      defaults: {
								   	   name: ORDERNAME.get('textMessagesNotice')
//								          margin: '0 5 0 0'
								      },
								      items: [{
								          inputValue: true,
								          boxLabel:  i18n('i18n.order.yes')
								      }, {
								          inputValue: false,
								          checked:true,
								          boxLabel:  i18n('i18n.order.no')
								      }]
								   },{
								  	xtype: 'textfield',
									name: ORDERNAME.get('storageAndTransportationMatters'),
									width:485,
									colspan:3,
									maxLength:150,
									labelSeparator:'',
								   fieldLabel:i18n('i18n.order.storageAndTransportationMatters')
//								   style:'margin-right:20px;'
								}
				           ]
						}];
		},
		initComponent:function()
		{
			var me = this;
			me.items = me.getItems();
			this.callParent();
			var store=Ext.getCmp(ORDERNAME.get('deliveryWay')).store;
			store.remove(store.findRecord("code","PICKFOOR"));
		}
});

/**
 * .
 * <p>
 * 货物信息FORM<br/>
 * </p>
 * 
 * @returns GoodsInfoForm 货物信息FORM 的EXT对象
 * {工单中有相同界面元素，如果订单中，该界面元素进行了修改，同时要修改工单的}
 * @author 张斌
 * @时间 2012-03-08
 */
Ext.define('GoodsInfoForm', {
		extend:'TitleFormPanel',
		orderData : null,
		 id:'goodsInfo_form',   
		/**
		 * .
		 * <p>
		 * 获取ITEMS属性 </br>
		 * </p>
		 * 
		 * @author 张斌
		 * @时间 2012-03-13
		 */
		getItems:function(){
			var me = this;
			return [{xtype: 'basicfiledset',
	            title: i18n('i18n.order.goodsInfo'),
	            layout: {type:'table',
	            	columns:3
	            },
	            defaults:{
	            	labelWidth:60,
	            	width:180,
			        labelSeparator:''
                },
                items:[
                 	   {
    						xtype: 'textfield',
    						id:ORDERNAME.get('goodsName'),
    						name: ORDERNAME.get('goodsName'),
    						labelSeparator:'',
    				        fieldLabel:i18n('i18n.order.goodsName')+'<font color=\"red\">*</font>',
    				        allowBlank:false,
    				        maxLength:30
    					}, {
    						xtype: 'textfield',
    						id:ORDERNAME.get('goodsPackagingMaterials'),
    						name: ORDERNAME.get('goodsPackagingMaterials'),
    						maxLength:30,
    				        fieldLabel:i18n('i18n.order.packagingMaterials')
		                   }, {
		                	   
		                	   id:ORDERNAME.get('goodsTotalNumber'),
		                	   name: ORDERNAME.get('goodsTotalNumber'),					
		                	   fieldLabel:i18n('i18n.order.totalNumber'),
		                	   xtype: 'numberfield',
		                	   decimalPrecision:0,
		                	   minValue:1,
		                	   maxValue:999999999
//      						value:0
    				 },{
 						xtype: 'numberfield',
						labelWidth:85,
//						width:233,
//						style:'margin-left:-15px',
						decimalPrecision:1,
						minValue:0,
						negativeText:i18n('i18n.order.notNegtive'),//不能输入负数
//						allowBlank:false,
						maxValue:999999999.9,
						id:ORDERNAME.get('goodsTotalWeight'),
						name: ORDERNAME.get('goodsTotalWeight'),
						fieldLabel:i18n('i18n.order.totalWeight')
//						value:0
					},{
 		            	   xtype: 'numberfield',
							decimalPrecision:2,
//							allowBlank:false,
							labelWidth:85,
//							style:'margin-left:-10px',
							minValue:0,
							negativeText:i18n('i18n.order.notNegtive'),//不能输入负数
//							value:0,
//							width:233,
							maxValue:999999999.99,
							id:ORDERNAME.get('goodsTotalVolume'),
							name: ORDERNAME.get('goodsTotalVolume'),
					        fieldLabel:i18n('i18n.order.totalVolume')
		                   },
//    					{
//    						id:'count',
//    						xtype: 'label',
//    						width:10,
//    				        text:i18n('i18n.order.parts'),
//    				        style:'margin-right:20px;margin-top:3px;'
//    					},
//    					{
//    						xtype: 'label',
//    						width:25,
//    						id:'kg',
//    						style:'margin-right:20px;margin-top:3px;',
//    				        text:i18n('i18n.order.kg')        
//    					},
//    					{
//    								xtype: 'label',
//    								width:40,
//    								id:'m3',
//    								style:'margin-right:20px;margin-top:3px;',
//    						        text:i18n('i18n.order.ccubicMetre')        
//    							},
    		   				{
    		                    xtype: 'radiogroup',
    		                    vertical: true,
    		                    blankText:i18n('i18n.order.mustSelectOneItems'),
    		                    fieldLabel:i18n('i18n.order.goodsType'),
    		                    id:ORDERNAME.get('goodsType'),              
    		                    defaults: {
    		                    	name: ORDERNAME.get('goodsType'),
    		                        margin: '0 5 0 0'
    		                    },
    		                    items: [{
    		                    	id:'goodsTypeA',
    		                    	disabled:true,
    		                        inputValue: 'A',
    		                        boxLabel: 'A'
    		                    }, {
    		                    	id:'goodsTypeB',
    		                    	disabled:true,
    		                        inputValue: 'B',
    		                        boxLabel: 'B'
    		                    }]
    		                  }
                       
                       
				   ]
			}];
		},
		initComponent:function()
		{
			var me = this;
			me.items = me.getItems();
			this.callParent();
		}
});
/**
 * .
 * <p>
 * 发货方FORM</br>
 * </p>
 * 
 * @returns ConsignorForm 发货方信息FORM 的EXT对象
 * {工单中有相同界面元素，如果订单中，该界面元素进行了修改，同时要修改工单的}
 * @author 张斌
 * @时间 2012-02-23
 */
Ext.define('ConsignorForm', {
		extend:'TitleFormPanel',
		orderData : null,
		hasGoodsType:null,
		falg:null,// 当不同的页面对一个地方要有不不同的操作时，则传一个falg（比如400界面和营业部界面）
		items:null,
		 id:'consignor_form',
		/**
		 * .
		 * <p>
		 * 选择网点时做的操作（当始发网点或到达网点所在城市包含在大小城市基础资料表中时，货物类型为必填项，且只能选择其中一种，否则货物类型为空且不可编辑；）</br>
		 * </p>
		 * 
		 * @param records
		 *            网点数据
		 * @author 张斌
		 * @时间 2012-03-14
		 */
		selectPoint:function(record){
			var me= this;
            if(record.get(ORDERNAME.get('ifHashGoodsType'))||Ext.getCmp('receiving_form').hasGoodsType){
            	Ext.getCmp('goodsTypeA').setDisabled(false);
    			Ext.getCmp('goodsTypeB').setDisabled(false);
    			Ext.getCmp(ORDERNAME.get('goodsType')).allowBlank = false;
            }else{
            	Ext.getCmp('goodsTypeA').setDisabled(true);
    			Ext.getCmp('goodsTypeB').setDisabled(true);
    			Ext.getCmp(ORDERNAME.get('goodsType')).allowBlank = true;
    			Ext.getCmp(ORDERNAME.get('goodsType')).reset();
            }
            me.hasGoodsType = record.get(ORDERNAME.get('ifHashGoodsType'));
		},
/*		*//**
		 * .
		 * <p>
		 * 选择地址，对地址进行解析，然后设置相应的值</br>
		 * </p>
		 * 
		 * @author 张斌
		 * @时间 2012-03-24
		 *//*
		selectAddress:function(th,record){
			var me = this;
			var addressList = record.data.address.split('-');
			if(addressList.length==4){
				var areas = addressList[0]+'-'+addressList[1]+'-'+addressList[2];
				Ext.getCmp('consignorAreaSelect').setValue(areas);
				th.setValue(addressList[3]);
			}else{
				return;
			}
		},*/
		/**
		 * .
		 * <p>
		 * 使用部门为营业部时，始发网点默认为本部门</br>
		 * </p>
		 * 
		 * @author 张斌
		 * @时间 2012-03-15
		 */
		initNetPoint:function(){
			var me = this;
			var successFn = function(json){
				if(!Ext.isEmpty(json.orderView.beginDept)){
					if(!Ext.isEmpty(Ext.getCmp(ORDERNAME.get('consignorComeFromPoint')))){
						Ext.data.StoreManager.lookup(ORDERNAME.get('consignorComeFromPoint')).add([json.orderView.beginDept]);
		       			Ext.getCmp(ORDERNAME.get('consignorComeFromPoint')).setValue(json.orderView.beginDept.id);
		       		
		       		//获取省市区初始值
		       			var pcr = '';
		       			if(Ext.isEmpty(json.orderView.beginDept.province)
		       					||Ext.isEmpty(json.orderView.beginDept.city)
		       					||Ext.isEmpty(json.orderView.beginDept.region)){
		       				
		       			}else{
		       				pcr=json.orderView.beginDept.province.name
		       				+'-'
		       				+json.orderView.beginDept.city.name
		       				+'-'
		       				+json.orderView.beginDept.region.name;
		       			    //设置
		       				Ext.getCmp('consignorAreaSelect').forceSelection  = false;
			       			Ext.getCmp('consignorAreaSelect').setValue(pcr);
			       			Ext.getCmp('consignorAreaSelect').forceSelection  = true;
		       			}
//		       			var deptAddress=json.orderView.beginDept.deptAddress;
//		       			//设置地址	
//		       			Ext.getCmp(ORDERNAME.get('consignorAddress')).setValue(deptAddress);
		       			
		       			if(json.orderView.beginDept.ifHashGoodsType){
		       				Ext.getCmp('goodsTypeA').setDisabled(false);
		        			Ext.getCmp('goodsTypeB').setDisabled(false);
		        			Ext.getCmp(ORDERNAME.get('goodsType')).allowBlank = false;
		       			}
				    }
					me.hasGoodsType = json.orderView.beginDept.ifHashGoodsType;
				}else{
					return ;
				}
			};
			var failureFn = function(){
				return ;
			};
			me.orderData.initPoint(null,ORDERNAME.get('consignorComeFromPoint'),successFn,failureFn);
		},
		/**
		 * .
		 * <p>
		 * 获取ITEMS属性 </br>
		 * </p>
		 * 
		 * @author 张斌
		 * @时间 2012-03-12
		 */
		getItems:function(){
			var me = this;
			var addressField = ['address','city','county','province'];
			return  [{xtype: 'basicfiledset',
				    title: i18n('i18n.order.consignor'),
				    defaults:{
				    	labelWidth:60,
				    	width:180,
				        xtype: 'textfield'
				    },
				    layout:{
				    	type:'table',
				    	columns:4
				    },
				    items:[
						{
							//colspan:1,
							id: ORDERNAME.get('consignorMobilePhone'),
							name: ORDERNAME.get('consignorMobilePhone'),
							labelSeparator:'',
						    fieldLabel:i18n('i18n.order.mobilePhoneNum*'),
						    regex:regObject.multiMobileNumber/*hbf*/,
						    regexText:i18n('i18n.order.correctMobilePhone'),
					        listeners:{
					        	blur:function(t){//失去焦点时触发
					        		if(t.isValid()){//判断文本框是否有效
					        			if(fChange){
						        			var successFn = function(json){
						        				if(!Ext.isEmpty(json.member)){
						        					var custNumber=json.member.custNumber;//客户编码
						        					var custName=json.member.custName;//客户名称
						        					var contactId=json.member.contactId;//主要联系人ID
						        					var custId=json.member.id;//客户Id
						        					
						        					Ext.getCmp(ORDERNAME.get('consignorCustCode')).setValue(custNumber);
													Ext.getCmp(ORDERNAME.get('consignorCustName')).setValue(custName);
													
													Ext.getCmp(ORDERNAME.get('shipperId')).setValue(custId);
													Ext.getCmp(ORDERNAME.get('contactManId')).setValue(contactId);
													
													var url='../order/isContractMember.action';
													var params={};
													params['custNumber']=custNumber;
													var successFn = function(response){
														isContractMember=response.isContractMember;
														if(isContractMember){
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
									        					},{
									        						'code':'FREE_DELIVERY','codeDesc':i18n('i18n.order.FREEDELIVERY')//'免费送货'
									        					}
									        				);
									        				Ext.getCmp(ORDERNAME.get('modeOfTransportation')).setValue('JZKH');
									        				Ext.getCmp(ORDERNAME.get('deliveryWay')).setValue('PICKSELF');
									        			}else if(!isContractMember){
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
									        				Ext.getCmp(ORDERNAME.get('modeOfTransportation')).setValue('JZKH');
									        				Ext.getCmp(ORDERNAME.get('deliveryWay')).setValue('PICKSELF');
									        			}
													};
													var failFn = function(response){
														
													};
													DButil.requestJsonAjax(url,params,successFn,failFn);
													
						        					if(json.member.contactList.length!=0){
						        						var name=json.member.contactList[0].name;//联系人名称
						        						Ext.getCmp(ORDERNAME.get('consignorName')).setValue(name);
							        					var offerTel=json.member.contactList[0].telPhone;//固定电话
							        					if(!Ext.isEmpty(offerTel)){
															offerTel = DButil.replacePhone(offerTel);
															var tels = offerTel.split('-');
															if(tels.length>2){
																// 转换电话号码格式
																Ext.getCmp(ORDERNAME.get('consignorExtension')).setValue(tels[2]);
																Ext.getCmp(ORDERNAME.get('consignorAreaCode')).setValue(tels[0]);
																Ext.getCmp(ORDERNAME.get('consignorPhone')).setValue(tels[1]);
															}else if (tels.length==2) {
																if(tels[0].length>tels[1].length){
																	Ext.getCmp(ORDERNAME.get('consignorPhone')).setValue(tels[0]);
																	Ext.getCmp(ORDERNAME.get('consignorExtension')).setValue(tels[1]);
																}
																if(tels[1].length>tels[0].length){
																	Ext.getCmp(ORDERNAME.get('consignorPhone')).setValue(tels[1]);
																	Ext.getCmp(ORDERNAME.get('consignorAreaCode')).setValue(tels[0]);
																}
															}else{
																Ext.getCmp(ORDERNAME.get('consignorPhone')).setValue(tels[0]);
															}
														}else{
															Ext.getCmp(ORDERNAME.get('consignorExtension')).setValue("");
															Ext.getCmp(ORDERNAME.get('consignorAreaCode')).setValue("");
															Ext.getCmp(ORDERNAME.get('consignorPhone')).setValue("");
														}
							        					/**
							        					 * 如果地址有多个，选择其中一个填充
							        					 */
							        					//TODO
							        					if(json.member.contactList[0].preferenceAddressList.length!=0 ){
							        						if(json.member.contactList[0].preferenceAddressList.length==1){
							        							var address=json.member.contactList[0].preferenceAddressList[0].address;//地址
							        							var addr = new Array();
												        		addr = address.split('-');
												        		
												        		if(addr.length>=4){
												        			Ext.getCmp('consignorAreaSelect').forceSelection  = false;
												        			Ext.getCmp('consignorAreaSelect').setValue(addr[0]+'-'+addr[1]+'-'+addr[2]);
												        			Ext.getCmp('consignorAreaSelect').forceSelection  = true;
												        			Ext.getCmp(ORDERNAME.get('consignorAddress')).setValue(addr[3]);
												        		}else if(addr.length==3){
												        			Ext.getCmp('consignorAreaSelect').forceSelection  = false;
												        			Ext.getCmp('consignorAreaSelect').setValue(addr[0]+'-'+addr[1]+'-'+addr[2]);
												        			Ext.getCmp('consignorAreaSelect').forceSelection  = true;
												        		}else if(addr.length==1){
												        			Ext.getCmp(ORDERNAME.get('consignorAddress')).setValue(address);
												        		}
							        						}else{//如果为多条
							        							var store=Ext.create('Ext.data.Store', {
																     fields: [{name: 'address'}]
																});
																for(var i=0;i<json.member.contactList[0].preferenceAddressList.length;i++){
																	store.add(json.member.contactList[0].preferenceAddressList[i]);	
																}
																var win=Ext.create('Ext.window.Window', {
																    title: '请双击选择其中一个地址',
																    height: 200,
																    width: 400,
																    modal:true,
																    y:120,
																    layout: 'fit',
																    items: {
																        xtype: 'grid',
																        cls : 'popup_grid',
																        columns: [{ text: '<center>详细地址</center>',dataIndex: 'address' , flex: 1,sortable:false}],                // One header just for show. There's no data,
																        store: store,
																        listeners:{
																        	itemdblclick:function(th,record){
																        		var addr = new Array();
																        		addr = record.get("address").split('-');
																        		if(addr.length>=4){
																        			Ext.getCmp('consignorAreaSelect').forceSelection  = false;
																        			Ext.getCmp('consignorAreaSelect').setValue(addr[0]+'-'+addr[1]+'-'+addr[2]);
																        			Ext.getCmp('consignorAreaSelect').forceSelection  = true;
																        			Ext.getCmp(ORDERNAME.get('consignorAddress')).setValue(addr[3]);
																        		}else if(addr.length==3){
																        			Ext.getCmp('consignorAreaSelect').forceSelection  = false;
																        			Ext.getCmp('consignorAreaSelect').setValue(addr[0]+'-'+addr[1]+'-'+addr[2]);
																        			Ext.getCmp('consignorAreaSelect').forceSelection  = true;
																        		}else if(addr.length==1){
																        			Ext.getCmp(ORDERNAME.get('consignorAddress')).setValue(address);
																        		}
																        		win.close();
																        	}
																        }
																    }
																}).show();
							        						}
														}else{//清空
															Ext.getCmp(ORDERNAME.get('consignorAddress')).setValue("").clearInvalid();
														}
						        					}
						        				}else{
						        					Ext.getCmp(ORDERNAME.get('consignorCustCode')).setValue("");
													Ext.getCmp(ORDERNAME.get('consignorCustName')).setValue("");
													Ext.getCmp(ORDERNAME.get('shipperId')).setValue("");
													Ext.getCmp(ORDERNAME.get('contactManId')).setValue("");
													Ext.getCmp(ORDERNAME.get('consignorExtension')).setValue("");
													Ext.getCmp(ORDERNAME.get('consignorAreaCode')).setValue("");
													Ext.getCmp(ORDERNAME.get('consignorPhone')).setValue("");
													Ext.getCmp(ORDERNAME.get('consignorName')).setValue("").clearInvalid();
													Ext.getCmp(ORDERNAME.get('consignorAddress')).setValue("").clearInvalid();
													isContractMember=false;
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
									        		Ext.getCmp(ORDERNAME.get('modeOfTransportation')).setValue('JZKH');
									        		Ext.getCmp(ORDERNAME.get('deliveryWay')).setValue('PICKSELF');
						        				}
						        				fChange=false;
						        			}
						        			var param = {'phone':t.getValue()};
						        			var failureFn=function(){
						        				
						        			}
						        			orderbaseDataControl.searchMemberInfoByPhone(param,successFn,failureFn);
					        			}
					        		}
					        	},
					        	change:function(t,newValue,oldValue){
					        		if(newValue==oldValue){
					        			fChange=false;
					        		}else{
					        			fChange=true;
					        		}
					        	}
					        }
						},{
							xtype:'container',
							layout:'hbox',
							defaultType:'textfield',
							width:420,
							id:'telContainer',
							margin:'0 -200 0 0',
							colspan:3,
							items:[{
								id: ORDERNAME.get('consignorAreaCode'),
								name: ORDERNAME.get('consignorAreaCode'),
								width:140,
								labelWidth:80,
								labelSeparator:'',
								regex:/^[0-9]{3,4}$/,
						        regexText:i18n('i18n.order.correctAreaCode'),
						        fieldLabel:i18n('i18n.order.phoneNum'),
						        emptyText:i18n('i18n.order.areaCode')
							},{ 
								id:ORDERNAME.get('consignorPhone'),
								name: ORDERNAME.get('consignorPhone'),
								width:120,
								labelWidth:5,
								labelSeparator:'',
						        fieldLabel:'-',
						        regex:/^[0-9]{7,8}$/,
						        regexText:i18n('i18n.order.correctPhone')
							},{
								id: ORDERNAME.get('consignorExtension'),
								name: ORDERNAME.get('consignorExtension'),
						        fieldLabel:'-',
						        width:60,
						        labelWidth:5,
						        labelSeparator:'',
						        emptyText:i18n('i18n.order.extension')
							}]
						},
						{
							xtype:'readonlytextfield',
							id:ORDERNAME.get('consignorCustCode'),
							name: ORDERNAME.get('consignorCustCode'),
						   // disabled:true,
							colspan:1,
							readOnly:true,
							fieldLabel:i18n('i18n.order.customerNum'),
							labelSeparator:''
						},
				           {
				    	xtype:'membersearchcombox',
					   colspan:1,
					   id: ORDERNAME.get('consignorCustName'),
						name: ORDERNAME.get('consignorCustName'),
				        fieldLabel:i18n('i18n.order.custName'),
				        maxLength:30,
				        addressType:'SEND_GOODS',
				        labelSeparator:'',
						setValueComeBack:function(memberRecord,addressRecord){
							var url='../order/isContractMember.action';
							var params={};
							params['memberId']=memberRecord.get('custId');
							var successFn = function(response){
								isContractMember=response.isContractMember;
								if(isContractMember){
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
			        					},{
			        						'code':'FREE_DELIVERY','codeDesc':i18n('i18n.order.FREEDELIVERY')//'免费送货'
			        					}
			        				);
			        				Ext.getCmp(ORDERNAME.get('modeOfTransportation')).setValue('JZKH');
			        				Ext.getCmp(ORDERNAME.get('deliveryWay')).setValue('PICKSELF');
			        			}else if(!isContractMember){
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
			        				Ext.getCmp(ORDERNAME.get('modeOfTransportation')).setValue('JZKH');
			        				Ext.getCmp(ORDERNAME.get('deliveryWay')).setValue('PICKSELF');
			        			}
							};
							var failFn = function(response){
								
							};
							DButil.requestJsonAjax(url,params,successFn,failFn);
							Ext.getCmp(ORDERNAME.get('consignorCustCode')).setValue(memberRecord.get('custNum'));
							Ext.getCmp(ORDERNAME.get('shipperId')).setValue(memberRecord.get('custId'));
							Ext.getCmp(ORDERNAME.get('consignorName')).setValue(memberRecord.get('contactName'));
							Ext.getCmp(ORDERNAME.get('consignorMobilePhone')).setValue(memberRecord.get('mobileNum'));
							Ext.getCmp(ORDERNAME.get('contactManId')).setValue(memberRecord.get('contactId'));
							var offerTel = memberRecord.get('telNum');
							if(!Ext.isEmpty(offerTel)){
								offerTel = DButil.replacePhone(offerTel);
								var tels = offerTel.split('-');
									if(tels.length>2){
										// 转换电话号码格式
										Ext.getCmp(ORDERNAME.get('consignorExtension')).setValue(tels[2]);
										Ext.getCmp(ORDERNAME.get('consignorAreaCode')).setValue(tels[0]);
										Ext.getCmp(ORDERNAME.get('consignorPhone')).setValue(tels[1]);
									}else if (tels.length==2) {
										if(tels[0].length>tels[1].length){
											Ext.getCmp(ORDERNAME.get('consignorPhone')).setValue(tels[0]);
											Ext.getCmp(ORDERNAME.get('consignorExtension')).setValue(tels[1]);
										}
										if(tels[1].length>tels[0].length){
											Ext.getCmp(ORDERNAME.get('consignorPhone')).setValue(tels[1]);
											Ext.getCmp(ORDERNAME.get('consignorAreaCode')).setValue(tels[0]);
										}
									}else{
										Ext.getCmp(ORDERNAME.get('consignorPhone')).setValue(tels[0]);
									}
							}
							if(!Ext.isEmpty(addressRecord)){
								var addressList = addressRecord.get('address').split('-');
								if(addressList.length==4){
									var areas = addressList[0]+'-'+addressList[1]+'-'+addressList[2];
									Ext.getCmp('consignorAreaSelect').forceSelection  = false;
									Ext.getCmp('consignorAreaSelect').setValue(areas);
									Ext.getCmp('consignorAreaSelect').forceSelection  = true;
									Ext.getCmp(ORDERNAME.get('consignorAddress')).setValue(addressList[3]);
								}else if(addressList.length==3){
									var areas = addressList[0]+'-'+addressList[1]+'-'+addressList[2];
									Ext.getCmp('consignorAreaSelect').forceSelection  = false;
									Ext.getCmp('consignorAreaSelect').setValue(areas);
									Ext.getCmp('consignorAreaSelect').forceSelection  = true;
								}else if(addressList.length==1){
									Ext.getCmp(ORDERNAME.get('consignorAddress')).setValue(addressList[0]);
								}else{
									Ext.getCmp(ORDERNAME.get('consignorAddress')).setValue(addressRecord.get('address'));
								}
							}
						},
						setValueNull:function(memberSearchCombox){
							Ext.getCmp(ORDERNAME.get('consignorCustCode')).setValue(null);
							Ext.getCmp(ORDERNAME.get('shipperId')).setValue(null);
							Ext.getCmp(ORDERNAME.get('consignorName')).setValue(null);
							Ext.getCmp(ORDERNAME.get('consignorMobilePhone')).setValue(null);
							Ext.getCmp(ORDERNAME.get('contactManId')).setValue(null);
							Ext.getCmp(ORDERNAME.get('consignorExtension')).setValue(null);
							Ext.getCmp(ORDERNAME.get('consignorAreaCode')).setValue(null);
							Ext.getCmp(ORDERNAME.get('consignorPhone')).setValue(null);
							if(!Ext.isEmpty(memberSearchCombox.addressRecord)){
								Ext.getCmp('consignorAreaSelect').setValue(null);
								Ext.getCmp(ORDERNAME.get('consignorAddress')).setValue(null);
							}
							memberSearchCombox.addressRecord = null;
							memberSearchCombox.memberRecord = null;
							isContractMember=false;
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
			        		Ext.getCmp(ORDERNAME.get('modeOfTransportation')).setValue('JZKH');
			    			Ext.getCmp(ORDERNAME.get('deliveryWay')).setValue('PICKSELF');
						}
				    },
				    
				    {
						queryMode: 'local',
						xtype:'textfield',
						colspan:1,
				        allowBlank:false,
				        // 别忘了给空值
				        maxLength:30,
						name: ORDERNAME.get('consignorName'),
						id: ORDERNAME.get('consignorName'),
				        fieldLabel:i18n('i18n.order.connectMan')+'<font color=\"red\">*</font>',
				        labelSeparator:''
					},{
						width:0,
						id:'consignorEmpty',
						hidden:true
					},
					//是否接货、接货地址等信息不再显示
//						{
//				            xtype: 'radiogroup',
//				            allowBlank:false,
//				            fieldLabel:i18n('i18n.order.isCargo'),
//				            id: ORDERNAME.get('consignorIsCargo'),
//				            defaults: {
//				                margin: '0 5 0 0'
//				            },
//				            items: [{
//				                inputValue: true,
//				                checked:true,
//				                name: ORDERNAME.get('consignorIsCargo'),
//				                id:'isReciveGoodsYes',
//				                boxLabel: i18n('i18n.order.yes'),
//				                listeners:{
//				                	change:function(th,newValue,oldValue){
//				                		var startTimeExt = Ext.getCmp(ORDERNAME.get('consignorCargoTime'));
//				                	    var endTimeExt = Ext.getCmp(ORDERNAME.get('consignorToCargoTime'));
//				                		if(newValue){
//				                			// 制空数据
//				                			startTimeExt.reset();
//				                			endTimeExt.reset();
//				                			// 设置为不可为空
//				                			Ext.getCmp('consignorAreaSelect').allowBlank=false;
//				        					Ext.getCmp(ORDERNAME.get('consignorAddress')).allowBlank=false;
//				                			startTimeExt.allowBlank = false,
//				                			endTimeExt.allowBlank = false,
//				                			// 设置为可用
//				                			startTimeExt.setDisabled(false);
//				                			endTimeExt.setDisabled(false);
//				                		}else{
//				                			// 制空数据
//				                			//startTimeExt.reset();
//				                			//endTimeExt.reset();
//				                			// 设置为可为空
//				                			Ext.getCmp('consignorAreaSelect').allowBlank=true;
//				                			Ext.getCmp('consignorAreaSelect').clearInvalid();
//				        					Ext.getCmp(ORDERNAME.get('consignorAddress')).allowBlank=true;
//				        					Ext.getCmp(ORDERNAME.get('consignorAddress')).clearInvalid();
//				        					startTimeExt.setValue("");
//				                			startTimeExt.allowBlank = true,
//				                			startTimeExt.clearInvalid( );// 去除红色标记
//				                			endTimeExt.setValue("")
//				                			endTimeExt.allowBlank = true,
//				                			endTimeExt.clearInvalid( );
//				                			
//				                			// 设置为不可用
//				                			startTimeExt.setDisabled(true);
//				                			endTimeExt.setDisabled(true);
//				                		}
//				                		
//				                	}
//				                }
//				            }, {
//				            	inputValue: false,
//				            	name: ORDERNAME.get('consignorIsCargo'),
//				                boxLabel: i18n('i18n.order.no')
//				            }]
//				         },{
//							xtype: 'datetimefield',
//							editable:false,
//							allowBlank:false,
//							format:'Y-m-d H:i',
//							width:215,
//							id:ORDERNAME.get('consignorCargoTime'),
//							labelWidth:70,
//							name: ORDERNAME.get('consignorCargoTime'),
//					        fieldLabel:i18n('i18n.order.cargoTime')+"&nbsp;从",
//					        value:new Date(new Date().setHours(new Date().getHours()+1)),
//					        labelSeparator:'',
//					        listeners:{
//				            	select:function(th){
//				            		var fromDate  = th.getValue();
//				            		var toDate = Ext.getCmp(ORDERNAME.get('consignorToCargoTime')).getValue();
//				            		if(!DButil.isValidStartDataAndEndDate(fromDate,toDate)){
//				            			MessageUtil.showMessage(i18n('i18n.order.endSmallerThanStart'));
//				            			th.reset();
//				            			Ext.getCmp(ORDERNAME.get('consignorToCargoTime')).reset();
//				            		}
//				            	}
//				            }
//						},{
//							xtype: 'datetimefield',
//							colspan:2,
////							labelWidth:50,
//							format:'Y-m-d H:i',
//							editable:false,
//							allowBlank:false,
//							id:ORDERNAME.get('consignorToCargoTime'),
//							name: ORDERNAME.get('consignorToCargoTime'),
//							labelWidth:35,
//					        fieldLabel:"--"+i18n('i18n.order.to')+'--',
//					        labelSeparator:'',
//					        value:new Date(new Date().setHours(new Date().getHours()+3)),
//					        listeners:{
//				            	select:function(th){
//				            		var toDate  = th.getValue();
//				            		var fromDate = Ext.getCmp(ORDERNAME.get('consignorCargoTime')).getValue();
//				            		if(!DButil.isValidStartDataAndEndDate(fromDate,toDate)){
//				            			MessageUtil.showMessage(i18n('i18n.order.endSmallerThanStart'));
//				            			th.reset();
//				            			Ext.getCmp(ORDERNAME.get('consignorCargoTime')).reset();
//				            		}
//				            	}
//				            }
//						},
						{
							height:30,
							xtype:'basicpanel',
							style:'margin-bottom:-2px',
					    	labelWidth:60,
					    	width:210,
							items:[new AreaAddressCombox({
								'id':'consignorAreaSelect',
								'allowBlank':false,
								'labelWidth':60,
								'width':210,
								fieldLabel:i18n('i18n.order.receiveMoneyAddressSapn'),
								'editable':true,
								'forceSelection':true})]
						}, {
						        labelSeparator:'',
				        	     xtype:'textfield',
				        	     allowBlank:false,
				        	     style:'margin-left:1px',
					             id:ORDERNAME.get('consignorAddress'),
					             name:ORDERNAME.get('consignorAddress'),
					             labelWidth:2,
					             maxLength:50,
					             emptyText:i18n('i18n.order.detailedAddress')
					   },{
						   name:ORDERNAME.get('consignorComeFromPoint'),
				  			xtype: 'combo',
				  			labelSeparator:'',
				  			width:240,
				  			maxLength:50,
				  			id:ORDERNAME.get('consignorComeFromPoint'),
				  	        allowBlank : false,
				  	        fieldLabel: i18n('i18n.order.comeFromPoint'),
				  	        store:me.orderData.getNetPointStore(),
				  	        //显示的字段
				  	        displayField:ORDERNAME.get('pointName'),
				  			//提交时的字段
				  	        valueField:ORDERNAME.get('id'),
				  			//查询依据的字段
				  			queryParam:ORDERNAME.get('pointName'),
				  			minChars:1,
			            	forceSelection: true,
				  	        typeAhead: false,
				  	        hideTrigger:false,
				  	        listConfig: {
				  	        	minWidth :300,
				  	            getInnerTpl: function() {
				  	            	 return '{'+ORDERNAME.get('pointName')+'}';
				  	            }
				  	        },
				  	       listeners:{
					        	select:function(com,records){
					        		me.selectPoint(records[0]);
					        	},
				  	        	change:function(comb){
				  	        		//comb.store.removeAll();
				  	        		if(Ext.isEmpty(comb.getValue())){
				  	        			 if(Ext.getCmp('receiving_form').hasGoodsType){
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
				  	        		Ext.getCmp('consignor_form').hasGoodsType = false;
				  	        	}
				  	       },
				  	        pageSize: 10
				           },{
				        	     xtype:'button',
				        	     width:100,
				        	     style:'margin-bottom:5px;margin-left:8px',
				        	     id:'searchElectronicMap_consignor',
						             text: i18n('i18n.order.searchElectronicMap'),
						             handler:function(){
						                 //@TODO调用电子地图接口
						               	var proCityArea = Ext.getCmp('consignorAreaSelect').getRawValue();
										if (Ext.isEmpty(proCityArea)) {
						             		   MessageUtil.showMessage(i18n('i18n.CommonOrderView.pleaseSelectAddress'));
											   return;
										}
										
										CommonOrderView.mapShow({
						            		 'proCityArea':proCityArea,
						            		 'otherAddress':Ext.getCmp(ORDERNAME.get('consignorAddress')).getValue(),
						            		 'callBack':function(dept){
				        	    				Ext.data.StoreManager.lookup(ORDERNAME.get('consignorComeFromPoint')).removeAll();
				        		       			me.changePointStandardcode(dept['deptNo']);
						            		 }
						            	 },'leave');
						             }
				             },{
									hidden:true,
									id: ORDERNAME.get('shipperId'),
									name: ORDERNAME.get('shipperId'),
						        	xtype:'textfield' 
						        },{
									hidden:true,
									id: ORDERNAME.get('contactManId'),
									name: ORDERNAME.get('contactManId'),
						        	xtype:'textfield' 
						        }]
						}
	    ]},
		changePointStandardcode:function(deptId){
			var me =this;
			var successFn = function(json){
				if(!Ext.isEmpty(json.orderView.beginDept)){
						Ext.data.StoreManager.lookup(ORDERNAME.get('consignorComeFromPoint'))
	    				.add({'id':json.orderView.beginDept.id,'deptName':json.orderView.beginDept.deptName,'ifHashGoodsType':json.orderView.beginDept.ifHashGoodsType});
		       			Ext.getCmp(ORDERNAME.get('consignorComeFromPoint')).expand();			
				}
			}
			var param = {'orderView':{'standardCode':deptId}};
			
			var failureFn=function(json){}
			me.orderData.changePointStandardcode(param,successFn,failureFn);
		},
		initComponent:function(){
			var me = this;
		    me.items = me.getItems();
		    if(me.isInitPoint){
		    	 me.initNetPoint();
		    }
			this.callParent();
	  }
});


top.Ext.define('MapWindow',{
    extend:'PopWindow',
    width:950,
    height:'95%',
    //height:600,
    //width:800,
    layout:'fit',
    //autoScroll:true,
    id:'mapWindow',
    address:null,
    initComponent:function(){
    	var me = this;
//    	me.items=me.getItems();
    	this.callParent();
    }
});
/**
 * .
 * <p>
 * 收货方FORM</br>
 * </p>
 * 
 * @returns ReceivingForm 收货方信息FORM 的EXT对象
 * {工单中有相同界面元素，如果订单中，该界面元素进行了修改，同时要修改工单的}
 * @author 张斌
 * @时间 2012-02-23
 */
Ext.define('ReceivingForm', {
		extend:'TitleFormPanel',
		hasGoodsType:null,
		orderData : null,
		falg:null,// 当不同的页面对一个地方要有不不同的操作时，则传一个falg（比如400界面和营业部界面）
		items:null,
		id:'receiving_form',
		/**
		 * .
		 * <p>
		 * 选择网点时做的操作（当始发网点或到达网点所在城市包含在大小城市基础资料表中时，货物类型为必填项，且只能选择其中一种，否则货物类型为空且不可编辑；）</br>
		 * </p>
		 * 
		 * @param records
		 *            网点数据
		 * @author 张斌
		 * @时间 2012-03-14
		 */
		selectPoint:function(record){
			var me= this;
//			if(record.get(ORDERNAME.get('ifOutward'))){
//				Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().removeAll();
//				Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().add({'code':'JZKY','codeDesc':i18n('i18n.order.playTranceJZ')});
//				Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().add({'code':'AGENT_VEHICLE','codeDesc':i18n('i18n.order.carTrance')});
//				Ext.getCmp(ORDERNAME.get('modeOfTransportation')).setValue('');
//			}else{
//				Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().removeAll();
//				Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().add(dataDictionary.TRANS_TYPE);
//			}
			if(record.get(ORDERNAME.get('ifHashGoodsType'))||Ext.getCmp('consignor_form').hasGoodsType){
	           	Ext.getCmp('goodsTypeA').setDisabled(false);
	   			Ext.getCmp('goodsTypeB').setDisabled(false);
	   			Ext.getCmp(ORDERNAME.get('goodsType')).allowBlank = false;
           }else{
	           	Ext.getCmp('goodsTypeA').setDisabled(true);
	   			Ext.getCmp('goodsTypeB').setDisabled(true);
	   			Ext.getCmp(ORDERNAME.get('goodsType')).allowBlank = true;
	   			Ext.getCmp(ORDERNAME.get('goodsType')).reset();
           }
			me.hasGoodsType = record.get(ORDERNAME.get('ifHashGoodsType'));
		},
		/**
		 * .
		 * <p>
		 * 选择地址，对地址进行解析，然后设置相应的值</br>
		 * </p>
		 * 
		 * @author 张斌
		 * @时间 2012-03-24
		 *//*
		selectAddress:function(th,record){
			var me = this;
			var addressList = record.data.address.split('-');
			if(addressList.length==4){
				var areas = addressList[0]+'-'+addressList[1]+'-'+addressList[2];
				Ext.getCmp('receivingAreaSelect').setValue(areas);
				th.setValue(addressList[3]);
			}else{
				return;
			}
		},*/
		/**
		 * .
		 * <p>
		 * 获取ITEMS属性 </br>
		 * </p>
		 * 
		 * @author 张斌
		 * @时间 2012-03-12
		 */
		getItems:function(){
			var me = this;
			var addressField = ['address','city','county','province'];
			return  [{xtype: 'basicfiledset',
	            title: i18n('i18n.order.receiving'),
	            layout:{
			    	type:'table',
			    	columns:4
			    },
	            defaults:{
	            	labelWidth:60,
	            	width:170
                },
                items:[
                       {
							xtype: 'textfield',
							id: ORDERNAME.get('receivingMobilePhone'),
							name: ORDERNAME.get('receivingMobilePhone'),
							labelSeparator:'',
						    fieldLabel:i18n('i18n.order.mobilePhoneNum'), 
						    regex:regObject.multiMobileNumber/*hbf*/,
						    regexText:i18n('i18n.order.correctMobilePhone'),
					        listeners:{
					        	blur:function(t){
					        		if(t.isValid()){
					        			if(sChange){
						        			var successFn = function(json){
						        				if(!Ext.isEmpty(json.member)){
						        					var custNumber=json.member.custNumber;//客户编码
						        					var custName=json.member.custName;//客户名称
						        					var receiverCustId=json.member.id;//客户Id
						        					Ext.getCmp(ORDERNAME.get('receivingCustCode')).setValue(custNumber);
													Ext.getCmp(ORDERNAME.get('receivingCustName')).setValue(custName);
													Ext.getCmp(ORDERNAME.get('receiverCustId')).setValue(receiverCustId);
													//Ext.getCmp(ORDERNAME.get('contactManId')).setValue(contactId);
													
													if(json.member.contactList.length!=0){
														var name=json.member.contactList[0].name;//联系人名称
														Ext.getCmp(ORDERNAME.get('receivingName')).setValue(name);
							        					var offerTel=json.member.contactList[0].telPhone;//固定电话
							        					if(!Ext.isEmpty(offerTel)){
															offerTel = DButil.replacePhone(offerTel);
															var tels = offerTel.split('-');
															if(tels.length>2){
																// 转换电话号码格式
																Ext.getCmp(ORDERNAME.get('receivingExtension')).setValue(tels[2]);
																Ext.getCmp(ORDERNAME.get('receivingAreaCode')).setValue(tels[0]);
																Ext.getCmp(ORDERNAME.get('receivingPhone')).setValue(tels[1]);
															}else if (tels.length==2) {
																if(tels[0].length>tels[1].length){
																	Ext.getCmp(ORDERNAME.get('receivingExtension')).setValue(tels[1]);
																	Ext.getCmp(ORDERNAME.get('receivingPhone')).setValue(tels[0]);
																}
																if(tels[1].length>tels[0].length){
																	Ext.getCmp(ORDERNAME.get('receivingAreaCode')).setValue(tels[0]);
																	Ext.getCmp(ORDERNAME.get('receivingPhone')).setValue(tels[1]);
																}
																Ext.getCmp(ORDERNAME.get('receivingAreaCode')).setValue(tels[0]);
																Ext.getCmp(ORDERNAME.get('receivingPhone')).setValue(tels[1]);
															}else{
																Ext.getCmp(ORDERNAME.get('receivingPhone')).setValue(tels[0]);
															}
														}else{
															Ext.getCmp(ORDERNAME.get('receivingExtension')).setValue("");
															Ext.getCmp(ORDERNAME.get('receivingAreaCode')).setValue("");
															Ext.getCmp(ORDERNAME.get('receivingPhone')).setValue("");
														}
							        					
							        					//TODO
							        					/**
							        					 * 收货方地址设置
							        					 */
							        					if(json.member.contactList[0].preferenceAddressList.length!=0 ){
							        						
							        						if(json.member.contactList[0].preferenceAddressList.length==1){
							        							var address=json.member.contactList[0].preferenceAddressList[0].address;//地址
							        							var addr = new Array();
												        		addr = address.split('-');
												        		if(addr.length>=4){
												        			Ext.getCmp('receivingAreaSelect').forceSelection  = false;
												        			Ext.getCmp('receivingAreaSelect').setValue(addr[0]+'-'+addr[1]+'-'+addr[2]);
												        			Ext.getCmp('receivingAreaSelect').forceSelection  = true;
												        			Ext.getCmp(ORDERNAME.get('receivingAddress')).setValue(addr[3]);
												        		}else if(addr.length==3){
												        			Ext.getCmp('receivingAreaSelect').forceSelection  = false;
												        			Ext.getCmp('receivingAreaSelect').setValue(addr[0]+'-'+addr[1]+'-'+addr[2]);
												        			Ext.getCmp('receivingAreaSelect').forceSelection  = true;
												        		}else if(addr.length==1){
												        			Ext.getCmp(ORDERNAME.get('receivingAddress')).setValue(address);
												        		}
							        						}else{//如果为多条
							        							var store=Ext.create('Ext.data.Store', {
																     fields: [{name: 'address'}]
																});
																for(var i=0;i<json.member.contactList[0].preferenceAddressList.length;i++){
																	store.add(json.member.contactList[0].preferenceAddressList[i]);	
																}
																var win=Ext.create('Ext.window.Window', {
																    title: '请双击选择其中一个地址',
																    height: 200,
																    width: 400,
																    modal:true,
																    y:240,
																    layout: 'fit',
																    items: {
																        xtype: 'grid',
																        cls : 'popup_grid',
																        columns: [{ text: '<center>详细地址</center>',dataIndex: 'address' , flex: 1,sortable:false}],                // One header just for show. There's no data,
																        store: store,
																        listeners:{
																        	itemdblclick:function(th,record){
																        		var addr = new Array();
																        		addr = record.get("address").split('-');
																        		if(addr.length>=4){
																        			Ext.getCmp('receivingAreaSelect').forceSelection  = false;
																        			Ext.getCmp('receivingAreaSelect').setValue(addr[0]+'-'+addr[1]+'-'+addr[2]);
																        			Ext.getCmp('receivingAreaSelect').forceSelection  = true;
																        			Ext.getCmp(ORDERNAME.get('receivingAddress')).setValue(addr[3]);
																        		}else if(addr.length==3){
																        			Ext.getCmp('receivingAreaSelect').forceSelection  = false;
																        			Ext.getCmp('receivingAreaSelect').setValue(addr[0]+'-'+addr[1]+'-'+addr[2]);
																        			Ext.getCmp('receivingAreaSelect').forceSelection  = true;
																        		}else if(addr.length==1){
																        			Ext.getCmp(ORDERNAME.get('receivingAddress')).setValue(address);
																        		}
																        		win.close();
																        	}
																        }
																    }
																}).show();
							        						}
							        						
							        						
														}else{
															Ext.getCmp(ORDERNAME.get('receivingAddress')).setValue("").clearInvalid();
														}
						        					}
						        				}else{
						        					Ext.getCmp(ORDERNAME.get('receivingCustCode')).setValue("");
													Ext.getCmp(ORDERNAME.get('receivingCustName')).setValue("");
													Ext.getCmp(ORDERNAME.get('receiverCustId')).setValue("");
													Ext.getCmp(ORDERNAME.get('receivingExtension')).setValue("");
													Ext.getCmp(ORDERNAME.get('receivingAreaCode')).setValue("");
													Ext.getCmp(ORDERNAME.get('receivingPhone')).setValue("");
													Ext.getCmp(ORDERNAME.get('receivingName')).setValue("").clearInvalid();
													Ext.getCmp(ORDERNAME.get('receivingAddress')).setValue("").clearInvalid();
						        				}
						        				sChange=false;
						        			}
						        			var param = {'phone':t.getValue()};
						        			var failureFn=function(){
						        				
						        			}
						        			orderbaseDataControl.searchMemberInfoByPhone(param,successFn,failureFn);
					        			}
					        		}
					        	},
					        	change:function(t,newValue,oldValue){
					        		if(newValue==oldValue){
					        			sChange=false;
					        		}else{
					        			sChange=true;
					        		}
					        	}
					        }
						},{
							xtype:'container',
							colspan:3,
							layout:'hbox',
							margin:'0 -200 0 0',
							width:420,
							defaultType:'textfield',
							items:[{
								id: ORDERNAME.get('receivingAreaCode'),
								name: ORDERNAME.get('receivingAreaCode'),
								width:140,
								labelWidth:60,
								labelSeparator:'',
							    fieldLabel:i18n('i18n.order.phoneNum'),
							    emptyText:i18n('i18n.order.areaCode'),
							    style:'margin-right:4px;',				        
							    regex:/^[0-9]{3,4}$/,
							    regexText:i18n('i18n.order.correctAreaCode')
							},{ 
								id:ORDERNAME.get('receivingPhone'),
								name: ORDERNAME.get('receivingPhone'),
								width:120,
								labelWidth:5,
								labelSeparator:'',
								style:'margin-right:4px;',
							    fieldLabel:'-',
							    regex:/^[0-9]{7,8}$/,
							    regexText:i18n('i18n.order.correctPhone')
							},{
								id: ORDERNAME.get('receivingExtension'),
								name: ORDERNAME.get('receivingExtension'),
							    fieldLabel:'-',
							    width:60,
							    labelWidth:5,
							    labelSeparator:'',
							    emptyText:i18n('i18n.order.extension'),
							    minLength: 0,
							    maxLength: 8, 
							    enforceMaxLength: true,
							    maskRe: /\d/
							}]
						},
						
						{
							xtype: 'textfield',
							id:ORDERNAME.get('receivingCustCode'),
							name: ORDERNAME.get('receivingCustCode'),
							readOnly:true,
							colspan:1,
//							 disabled:true,
						    fieldLabel:i18n('i18n.order.customerNum'),
						    labelSeparator:'' 
						},
						{
							   xtype:'membersearchcombox',
							   colspan:1,
							   id: ORDERNAME.get('receivingCustName'),
								name: ORDERNAME.get('receivingCustName'),
						        fieldLabel:i18n('i18n.order.custName'),
						        maxLength:30,
						        addressType:'RECEIVE_GOODS',
						        labelSeparator:'',
						        setValueComeBack:function(memberRecord,addressRecord){
									Ext.getCmp(ORDERNAME.get('receivingCustCode')).setValue(memberRecord.get('custNum'));
									Ext.getCmp(ORDERNAME.get('receiverCustId')).setValue(memberRecord.get('custId'));
									Ext.getCmp(ORDERNAME.get('receivingName')).setValue(memberRecord.get('contactName'));
									Ext.getCmp(ORDERNAME.get('receivingMobilePhone')).setValue(memberRecord.get('mobileNum'));
									var offerTel = memberRecord.get('telNum');
									if(!Ext.isEmpty(offerTel)){
										offerTel = DButil.replacePhone(offerTel);
										var tels = offerTel.split('-');
										if(tels.length>2){
											// 转换电话号码格式
											Ext.getCmp(ORDERNAME.get('receivingExtension')).setValue(tels[2]);
											Ext.getCmp(ORDERNAME.get('receivingAreaCode')).setValue(tels[0]);
											Ext.getCmp(ORDERNAME.get('receivingPhone')).setValue(tels[1]);
										}else if (tels.length==2) {
											if(tels[0].length>tels[1].length){
												Ext.getCmp(ORDERNAME.get('receivingExtension')).setValue(tels[1]);
												Ext.getCmp(ORDERNAME.get('receivingPhone')).setValue(tels[0]);
											}
											if(tels[1].length>tels[0].length){
												Ext.getCmp(ORDERNAME.get('receivingAreaCode')).setValue(tels[0]);
												Ext.getCmp(ORDERNAME.get('receivingPhone')).setValue(tels[1]);
											}
											Ext.getCmp(ORDERNAME.get('receivingAreaCode')).setValue(tels[0]);
											Ext.getCmp(ORDERNAME.get('receivingPhone')).setValue(tels[1]);
										}else{
											Ext.getCmp(ORDERNAME.get('receivingPhone')).setValue(tels[0]);
										}
									}
									if(!Ext.isEmpty(addressRecord)){
										var addressList = addressRecord.get('address').split('-');
										if(addressList.length==4){
											var areas = addressList[0]+'-'+addressList[1]+'-'+addressList[2];
											Ext.getCmp('receivingAreaSelect').forceSelection  = false;
											Ext.getCmp('receivingAreaSelect').setValue(areas);
											Ext.getCmp('receivingAreaSelect').forceSelection  = true;
											Ext.getCmp(ORDERNAME.get('receivingAddress')).setValue(addressList[3]);
										}else if(addressList.length==1){
											Ext.getCmp(ORDERNAME.get('receivingAddress')).setValue(addressList[0]);
										}else if(addressList.length==3){
											var areas = addressList[0]+'-'+addressList[1]+'-'+addressList[2];
											Ext.getCmp('receivingAreaSelect').forceSelection  = false;
											Ext.getCmp('receivingAreaSelect').setValue(areas);
											Ext.getCmp('receivingAreaSelect').forceSelection  = true;
										}else{
											Ext.getCmp(ORDERNAME.get('receivingAddress')).setValue(addressRecord.get('address'));
										}
									}
								},
								setValueNull:function(memberSearchCombox){
									Ext.getCmp(ORDERNAME.get('receivingCustCode')).setValue(null);
									Ext.getCmp(ORDERNAME.get('receiverCustId')).setValue(null);
									Ext.getCmp(ORDERNAME.get('receivingName')).setValue(null);
									Ext.getCmp(ORDERNAME.get('receivingMobilePhone')).setValue(null);
									Ext.getCmp(ORDERNAME.get('receivingExtension')).setValue(null);
									Ext.getCmp(ORDERNAME.get('receivingAreaCode')).setValue(null);
									Ext.getCmp(ORDERNAME.get('receivingPhone')).setValue(null);
									if(!Ext.isEmpty(memberSearchCombox.addressRecord)){
										Ext.getCmp('receivingAreaSelect').setValue(null);
										Ext.getCmp(ORDERNAME.get('receivingAddress')).setValue(null);
									}
									memberSearchCombox.addressRecord = null;
									memberSearchCombox.memberRecord = null;
								}
						  },
						{
							queryMode: 'local',
							xtype:'textfield',
							colspan:1,
						    maxLength: 30, 
						    id:ORDERNAME.get('receivingName'),
							name: ORDERNAME.get('receivingName'),
						    fieldLabel:i18n('i18n.order.connectMan1'),
						    labelSeparator:''
						},{
							width:0,
							id:'receivingEmpty',
							hidden:true
						},
						{height:30,
							xtype:'basicpanel',
							style:'margin-bottom:-2px',
					    	labelWidth:60,
					    	width:210,
							items:[new AreaAddressCombox({
										'labelWidth':60,
										'width':210,
										'fieldLabel':i18n('i18n.order.procityarea')+'<font color=\"red\">*</font>',
										'id':'receivingAreaSelect',
										'allowBlank':false,
										'editable':true,
										'forceSelection':true})]
						},{
							    labelSeparator:'',
							    xtype:'textfield',
						        id:ORDERNAME.get('receivingAddress'),
						        name:ORDERNAME.get('receivingAddress'),
						        labelWidth:2,
						        maxLength:50,
						        width:160,
//						        colspan:2,
						        style:'margin-left:1px',
						        emptyText:i18n('i18n.order.detailedAddress')
						},{
							name:ORDERNAME.get('receivingToPoint'),
				  			xtype: 'combo',
				  			labelSeparator:'',
				  			maxLength:50,
				  			width:220,
				  			id:ORDERNAME.get('receivingToPoint'),
					        fieldLabel: i18n('i18n.order.toPoint'),
				  	        store:me.orderData.getArriveNetPointStore(),
				  	        //显示的字段
				  	        displayField:ORDERNAME.get('pointName'),
				  			//提交时的字段
				  	        valueField:ORDERNAME.get('id'),
				  			//查询依据的字段
				  			queryParam:ORDERNAME.get('pointName'),
				  			minChars:1,
			            	forceSelection: true,
				  	        typeAhead: false,
				  	        hideTrigger:false,
				  	        listConfig: {
				  	        	minWidth :300,
				  	            getInnerTpl: function() {
				  	            	 return '{'+ORDERNAME.get('pointName')+'}';
				  	            }
				  	        },
				  	       listeners:{
					        	select:function(com,records){
					        		me.selectPoint(records[0]);
					        	},
				  	        	change:function(comb){
				  	        		//comb.store.removeAll();
				  	        		if(Ext.isEmpty(comb.getValue())){
				  	        			if(Ext.getCmp('consignor_form').hasGoodsType){
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
				  	        		Ext.getCmp('receiving_form').hasGoodsType = false;
				  	        	}

				  	       },
				  	        pageSize: 10
					   },{
							     xtype:'button',
							     width:100,
							     style:'margin-bottom:5px;margin-left:8px',
							     id:'searchElectronicMap_receiving',
						            text: i18n('i18n.order.searchElectronicMap'),
						            handler:function(){
						           		//@ TODO调用电子地图接口
						            	var proCityArea = Ext.getCmp('receivingAreaSelect').getRawValue();
						             	if (Ext.isEmpty(proCityArea)) {
						             		MessageUtil.showMessage(i18n('i18n.CommonOrderView.pleaseSelectAddress'));
											return;
										}
										 
						            	 CommonOrderView.mapShow({
						            		 'proCityArea':proCityArea,
						            		 'otherAddress':Ext.getCmp(ORDERNAME.get('receivingAddress')).getValue(),
						            		 'callBack':function(dept){
				        	    				Ext.data.StoreManager.lookup(ORDERNAME.get('receivingToPoint')).removeAll();
				        		       			me.changePointStandardcode(dept['deptNo']);
						            		 }
						            	 },'pickup');
						            }
						},{
							hidden:true,
							id: ORDERNAME.get('receiverCustId'),
							name: ORDERNAME.get('receiverCustId'),
							xtype:'textfield' 
						   }
                       ]
			}];
		},
		changePointStandardcode:function(deptId){
			var me =this;
			var successFn = function(json){
				if(!Ext.isEmpty(json.orderView.beginDept)){
						Ext.data.StoreManager.lookup(ORDERNAME.get('receivingToPoint'))
	    				.add({'id':json.orderView.beginDept.id,'deptName':json.orderView.beginDept.deptName,'ifHashGoodsType':json.orderView.beginDept.ifHashGoodsType,'ifOutward':json.orderView.beginDept.ifOutward});
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
			me.callParent();
	  }
});

/**
 * .
 * <p>
 * 订单查询FORM<br/>
 * </p>
 * 
 * @returns OrderQueryForm 订单查询FORM 的EXT对象
 * @author 张斌
 * @时间 2012-03-19
 */
Ext.define('OrderQueryForm', {
		extend:'SearchFormPanel',
		OrderData : null,// 连接ACTION的对象
		storeId:null,
	    /**
		 * .
		 * <p>
		 * 获取ITEMS属性 </br>
		 * </p>
		 * 
		 * @author 张斌
		 * @时间 2012-03-19
		 */
	    searchButtonClick:function(){
	    	var me = this;
	    	if(me.getForm().isValid()){
//	    		IsAutoSearch = false;
	    		var startDate = Ext.getCmp(ORDERNAME.get('startDate')).getValue();
	        	var endDate =Ext.getCmp(ORDERNAME.get('endDate')).getValue();
	        	var orderNum = me.getForm().findField(ORDERNAME.get('orderNum')).getValue();
	        	var waybillNum = me.getForm().findField(ORDERNAME.get('waybillNum')).getValue();
	        	var consignorMobilePhone = me.getForm().findField(ORDERNAME.get('consignorMobilePhone')).getValue();
	        	var contactPhone = me.getForm().findField('contactPhone').getValue();
	        	if(Ext.isEmpty(orderNum)
	        			&&Ext.isEmpty(waybillNum)&&Ext.isEmpty(consignorMobilePhone)
	        			&&Ext.isEmpty(contactPhone)){//当输入订单号，运单号，手机号和固定电话号码之后不进行校验
	        		if(Validator.isDateMoreThirtyDays(startDate,endDate)==1){
		        		MessageUtil.showMessage(i18n('i18n.order.dateMoreThirtyDays'));
		        	}else if(Validator.isDateMoreThirtyDays(startDate,endDate)==2){
		        		MessageUtil.showMessage(i18n('i18n.order.endSmallerThanStart'));
		        	}else{
		        		/* isPageLoad=false 表示 是手动点击查询进行的 */
			        	isPageLoad = false;
		        		Ext.data.StoreManager.lookup(me.storeId).loadPage(1);
		        	}
	        	}else{
	        		isPageLoad = false;
	        		Ext.data.StoreManager.lookup(me.storeId).loadPage(1);
	        	}
	    	}
	    },
		id : 'orderQuery_Form',
		/**
		 * .
		 * <p>
		 * 获取ITEMS属性 </br>
		 * </p>
		 * 
		 * @author 张斌
		 * @时间 2012-03-19
		 */
		getItems:function(){
			var me = this;
			var field = ['code','codeDesc'];
	      /**			
		    * 修改人：张斌
			*修改时间：2013-7-27 10:17
			*原有内容：无（新增）
			*修改原因：给运输方式数据字典中加入“全部”
		  */
			//begin
			var TRANS_TYPE = new Array();//创建新的运输方式数据字典
			var all = {code:'ALL',codeDesc:i18n('i18n.order.all')};//“全部”类型
			TRANS_TYPE.push(all);
			for(var i= 0;i<dataDictionary.TRANS_TYPE.length;i++){
					TRANS_TYPE.push(Ext.clone(dataDictionary.TRANS_TYPE[i]));//用Ext的clone方法，防止影响原有数据
			}
			//end			
			return [{
	            	xtype: 'fieldcontainer',
		            defaultType: 'textfield',
		            layout:{
						type:'table',
						columns:4
					},
					defaultType:'textfield',
					defaults:{
						enableKeyEvents:true,
						labelSeparator:'',
						labelWidth:60,
						width:200,
						listeners:{
							scope : me,						// 作用域修改为整个form
							keypress : me.keypressEvent
						}
					},
		            items: [{
	            	 	labelWidth:90,
		                xtype: 'textfield',
	                    name:         ORDERNAME.get('orderNum'),
	                    fieldLabel: i18n('i18n.order.orderNumOrChannelNumber')//订单获渠道编号
	                },{
	                    xtype: 'textfield',
	                    name:         ORDERNAME.get('waybillNum'),//运单号
	                    fieldLabel: i18n('i18n.order.wayBillNum')
	                },{
	                    xtype: 'textfield',
	                    colspan:1,
	                    regex:regObject.multiMobileNumber/*hbf*/,
	                    regexText:i18n('i18n.order.correctMobilePhone'),
	                    name:         ORDERNAME.get('consignorMobilePhone'),
	                    fieldLabel: i18n('i18n.order.deliveryContactMobilePhone')//发货人手机
		             },{
	                    xtype: 'textfield',
	                    colspan:1,
	                    labelWidth:25,
	                    width:155,
	                    minLength:4,
	                    name:'contactPhone',
	                    fieldLabel: i18n('i18n.order.phone')//电话
		             },{
		            	labelWidth:90,
	                    xtype:          'combo',
	                    mode:           'local',
	                    value:          me.defaultValue[1],
	                    triggerAction:  'all',
	                    editable:false,
	                    forceSelection: true,
	                    fieldLabel:     i18n('i18n.order.orderStatus'),//订单状态
	                    id:ORDERNAME.get('orderStatus'),
	                    name:ORDERNAME.get('orderStatus'),
	                    displayField:   'codeDesc',
	                    valueField:     'code',
	                    queryMode: 'local',
	                    store:DButil.getStore(ORDERNAME.get('orderStatus'),null,field,Ext.isEmpty(me.orderStatus)?dataDictionary.ORDER_STATUS:me.orderStatus)
	                },{
	                    xtype:          'combo',
	                    mode:           'local',
	                    triggerAction:  'all',
	                    forceSelection: true,
	                    editable:false,
	                    fieldLabel:     i18n('i18n.order.orderResource'),//订单来源
	                    id:           ORDERNAME.get('resource'),
	                    name:         ORDERNAME.get('resource'),
	                    displayField:   'codeDesc',
	                    valueField:     'code',
	                    value:me.defaultValue[0],
	                    queryMode: 'local',
	                    store: Ext.create('Ext.data.Store', {
	                    	storeId: 'resource',
	                    	fields: [{
	                    		name: 'codeDesc', mapping: 'name'
	                    	},{
	                    		name: 'code', mapping: 'resource'
	                    	}],
							proxy:{
								type: 'ajax',
								url: '../order/allResources.action',
								reader: {
									type: 'json',
									root: 'resources'
								}
							},
							autoLoad: true,
							listeners: {
								load: function(store){
									store.add({
										codeDesc: '全部',
										code: 'ALL'
									});
									Ext.getCmp('resource').select('ALL');
								}
							}
	                    })
	                },{
		                xtype     : 'datetimefield',
		                id:           ORDERNAME.get('startDate'),
	                    name:         ORDERNAME.get('startDate'),
	                    value:new Date((new Date).getFullYear(),(new Date).getMonth(),(new Date).getDate(),0,0,0),
		                fieldLabel: i18n('i18n.order.createTime'),//创建时间
		                editable:false,
		                format: 'Y-m-d H:i',
						defaultTime:[0,0,0],
						labelWidth:70,
						width:210
		            }, {
		            	style:'margin-left:2px;',
		            	labelWidth:5,
		            	width:150,
		                xtype     : 'datetimefield',
		                id:           ORDERNAME.get('endDate'),
	                    name:         ORDERNAME.get('endDate'),
		                fieldLabel: '-',
		                value:new Date((new Date).getFullYear(),(new Date).getMonth(),(new Date).getDate(),23,59,59),
		                editable:       false,
		                format: 'Y-m-d H:i',
						defaultTime:[23,59,59],
		                labelSeparator:'',
						labelWidth:6,
						width:144
		            }
		            
		            /**			
					    * 修改人：张斌
						*修改时间：2013-7-27 10:50
						*原有内容：无（新增）
						*修改原因：增加运输方式查询条件
					 */
		            //begin
		            ,{
					   	 xtype:'combo',
				        fieldLabel: i18n('i18n.order.modeOfTransportation'),
				        store: DButil.getStore(ORDERNAME.get('modeOfTransportation'),null,field,TRANS_TYPE),
				        name: ORDERNAME.get('modeOfTransportation'),
				        queryMode: 'local',
				        editable:false,
				        labelWidth:90,
				        value:me.defaultValue[0],
				        displayField: 'codeDesc',
				        valueField: 'code',
				        allowBlank:false,
				        forceSelection: true
				    }
		          //end
		            , {
		                xtype     : 'textfield',
		                //labelWidth:90,
	                    name:         ORDERNAME.get('createUser'),
		                fieldLabel: i18n('i18n.order.documentationCclerk')
		            },{
						   name:ORDERNAME.get('consignorComeFromPoint'),
				  			xtype: 'combo',
				  			readOnly:!isPermission('/order/satrtStationSearch.action'),
				  			cls:isPermission('/order/satrtStationSearch.action')?'':'readonly',
				  			labelSeparator:'',
				  			maxLength:50,
				  	        fieldLabel: i18n('i18n.order.comeFromPoint'),
				  	        store:orderbaseDataControl.getNetPointStore(),
				  	        //显示的字段
				  	        displayField:ORDERNAME.get('pointName'),
				  			//提交时的字段
				  	        valueField:ORDERNAME.get('id'),
				  			//查询依据的字段
				  			queryParam:ORDERNAME.get('pointName'),
				  			minChars:1,
			            	forceSelection: true,
				  	        typeAhead: false,
				  	        hideTrigger:false,
				  	        listConfig: {
				  	        	minWidth :300,
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
				  	        	}
				  	       },
				  	        pageSize: 10
				           }]
			}];
		},
		keypressEvent:function(field,event){
			var me = this;
			if(event.getKey() == Ext.EventObject.ENTER){
		    	if(me.getForm().isValid()){
//		    		IsAutoSearch = false;
		    		var startDate = Ext.getCmp(ORDERNAME.get('startDate')).getValue();
		        	var endDate =Ext.getCmp(ORDERNAME.get('endDate')).getValue();
		        	if(Validator.isDateMoreThirtyDays(startDate,endDate)==1){
		        		MessageUtil.showMessage(i18n('i18n.order.dateMoreThirtyDays'));
		        	}else if(Validator.isDateMoreThirtyDays(startDate,endDate)==2){
		        		MessageUtil.showMessage(i18n('i18n.order.endSmallerThanStart'));
		        	}else{
		        		/* isPageLoad=false 表示 查询条件 有时间范围限制 */
		        		isPageLoad = false;
		        		Ext.data.StoreManager.lookup(me.storeId).loadPage(1);
		        	}
		    	}
		    }
		},
		initComponent:function()
		{
			var me = this;
			me.items = me.getItems();
			this.callParent();
		}
});

/*
 * 查询订单的按钮面板，共分为三个部分，分别是左侧的按钮面板、中间的按钮面板和右侧的按钮面板，
 * 通过css文件来控制按钮面板的显示样式
 */
Ext.define('SearchOrderButtonPanel',{
	extend:'NormalButtonPanel',
	items:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'leftbuttonpanel', 
			items:[{
				xtype:'button',
				text:'添加备注',
				handler:function(){
					var grid = Ext.getCmp('orderGrid');
					var selection=grid.getSelectionModel().getSelection();
//					//判断是否选中行
					if (selection.length == 0) {
						MessageUtil.showMessage("请选择订单~");
						return false;
					}
					if (selection.length != 1) {
						MessageUtil.showMessage("只能选择一条记录");
						return false;
					}
					var record=selection[0];
					Ext.MessageBox.prompt("提示","请输入备注", function(e,text){
						if(e == 'ok'){
							if(!Ext.isEmpty(text)){
								if(text.length>40){
									MessageUtil.showMessage("组名不能长度不能大于40个字符~");
									return false;
								}
								var successFn = function(json){//													Ext.getCmp("CustomerGroupFormPanelId").getForm().findField('empName').store.load();
									MessageUtil.showInfoMes("添加成功");
									
								};
								var failureFn = function(json){
									MessageUtil.showErrorMes(json.message);
								};
								var contactName=Ext.isEmpty(record.get(ORDERNAME.get('consignorName')))==true?'':record.get(ORDERNAME.get('consignorName'));
								var contactPhone=Ext.isEmpty(record.get(ORDERNAME.get('consignorPhone')))==true?'':record.get(ORDERNAME.get('consignorPhone'));
								var contactNameAndPhone=contactName;//默认为名字
								if(!Ext.isEmpty(contactPhone)){
									contactNameAndPhone=contactName+contactPhone;
								}
								var param={
									'orderOperationLog.orderId':record.get("id"),
									'orderOperationLog.contactName':contactNameAndPhone,
									'orderOperationLog.operatorContent':text
								};
								orderbaseDataControl.saveOrderRemark(param, successFn, failureFn);
		            		}else{
		            			MessageUtil.showMessage(i18n('i18n.customerGroup.insertName'));
		            		}
						}
	            	});
				}
			}]
		},{
			xtype:'middlebuttonpanel'
		},{
			xtype:'rightbuttonpanel',
			width:260,
			items:[{
	            xtype:'button',
	            text: i18n('i18n.order.search'),
	            handler:function(){
	            	Ext.getCmp('orderQuery_Form').searchButtonClick();
	            }
	        },{
	            xtype:'button',
	            text: i18n('i18n.order.reset'),
	            handler:function(){
	            	Ext.getCmp("orderQuery_Form").getForm().reset();
	            }
	        }
// ,{
// xtype:'button',
// text: i18n('i18n.order.refresh'),
// handler:function(){
// Ext.getCmp("orderQuery_Form").getForm().reset();
// orderQuery_Form.searchButtonClick();
// }
// }
	        ]
		}];
	}
});
/*
 * getFbar:function(){ var me = this; return []; },
 */
/**
 * .
 * <p>
 * 订单查询GIRD<br/>
 * </p>
 * 
 * @returns OrderGrid 订单查询GIRD 的EXT对象
 * @author 张斌
 * @时间 2012-03-19
 */
Ext.define('OrderGrid',{
	extend:'SearchGridPanel',
	id:'orderGrid',
	storeId:null,
	height: 314,
	model:'MULTI',
	listeners: {
    	scrollershow: function(scroller) {
    		if (scroller && scroller.scrollEl) {
    				scroller.clearManagedListeners(); 
    				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
    		}
    	}
    },
    //sortableColumns:false,//不能排序
    //enableColumnHide:false,//不能隐藏
    //enableColumnMove:false,//不能移动
	getSelModel:function(){
		var me = this;
		return Ext.create('Ext.selection.CheckboxModel',{
				mode:me.model,
				listeners:{
					select:function(th,record,index,eOpts){
						Ext.data.StoreManager.lookup('orderOperationLogStore').load();
						//dataDictionary.TRANS_TYPE;运输方式
						DButil.refreshView(record,dataDictionary.TRANS_TYPE);
						if(!Ext.isEmpty(Ext.getCmp(ORDERNAME.get('returnInfo')))){
							Ext.getCmp(ORDERNAME.get('returnInfo')).reset();
							if(!Ext.isEmpty(Ext.getCmp('otherReason'))){
								Ext.getCmp('otherReason').setValue('');
								Ext.getCmp('otherReason').setDisabled(true);
							}
						}
					}
				}
			});
	},
    getBBar:function(){
    	var me = this;
    	return Ext.create('Ext.toolbar.Paging', {
			id : 'pagingToolbar',
			store : me.store,
			displayMsg : i18n('i18n.order.displayMsg'),
			displayInfo : true
			/*
			 * items:[ '-',{ text: i18n('i18m.order.page_count'), xtype:
			 * 'tbtext' },Ext.create('Ext.form.ComboBox', { width: 50, value:
			 * '10', triggerAction: 'all', forceSelection: true, editable:
			 * false, name: 'comboItem', displayField: 'value', valueField:
			 * 'value', queryMode: 'local', store :
			 * Ext.create('Ext.data.Store',{ fields : ['value'], data : [
			 * {'value':'10'}, {'value':'15'}, {'value':'20'}, {'value':'25'},
			 * {'value':'40'}, {'value':'100'} ] }), listeners:{ select : {
			 * scope : this, fn: function(_field,_value){ var pageSize =
			 * Ext.data.StoreManager.lookup(me.storeId).pageSize; var
			 * newPageSize = parseInt(_field.value); if(pageSize!=newPageSize){
			 * Ext.data.StoreManager.lookup(me.storeId).pageSize = newPageSize;
			 * Ext.getCmp('pagingToolbar').moveFirst(); } } } } }),{ text:
			 * i18n('i18n.order.number'), xtype: 'tbtext' }]
			 */
		});
    },
    initComponent:function(){
		var me = this;
		me.bbar = me.getBBar();
		me.selModel = me.getSelModel();
		// 增加store的beforeload方法
		Ext.data.StoreManager.lookup(me.storeId).on('beforeload',function(NoAllocationOrderStore,operation,e){
			var searchParams=null;
			if(isPageLoad === false){/* 是点击查询按钮 */
				top.orderClickSource = null;/* 初始化 */
			}
			if(
				(top.Condition && top.Condition != null && top.Condition != '')
				&& (top.orderClickSource && top.orderClickSource==='click_business_backlog')
			){
				searchParams = {'orderSearchView.orderSearchCondition.orderStatus':top.Condition,
						'orderSearchView.orderSearchCondition.startDate':new Date(new Date().setDate(new Date().getDate()-30)),
				        'orderSearchView.orderSearchCondition.endDate':new Date()};
			}else{
				if(!Ext.isEmpty(Ext.getCmp('orderQuery_Form').getForm().findField(ORDERNAME.get('orderNum')).getValue())){// 订单号不为空
	        		searchParams = {
	    				'orderSearchView.orderSearchCondition.orderNum':Ext.getCmp('orderQuery_Form').getForm().findField(ORDERNAME.get('orderNum')).getValue().trim()
	        	  	};
	        	}else if(!Ext.isEmpty(Ext.getCmp('orderQuery_Form').getForm().findField(ORDERNAME.get('waybillNum')).getValue())){// 运单号不为空
	        		searchParams = {
	    				'orderSearchView.orderSearchCondition.waybillNum':Ext.getCmp('orderQuery_Form').getForm().findField(ORDERNAME.get('waybillNum')).getValue().trim()
	        	  	};
	        	}else if(!Ext.isEmpty(Ext.getCmp('orderQuery_Form').getForm().findField(ORDERNAME.get('consignorMobilePhone')).getValue())){// 手机号不为空
	        		searchParams = {
	    				'orderSearchView.orderSearchCondition.contactMobile':Ext.getCmp('orderQuery_Form').getForm().findField(ORDERNAME.get('consignorMobilePhone')).getValue().trim()
	        	  	};
	        	}else if(!Ext.isEmpty(Ext.getCmp('orderQuery_Form').getForm().findField('contactPhone').getValue())){// 手机号不为空
	        		searchParams = {
		    				'orderSearchView.orderSearchCondition.contactPhone':Ext.getCmp('orderQuery_Form').getForm().findField('contactPhone').getValue().trim()
		        	  	};
		        }else if(!(Ext.isEmpty(Ext.getCmp(ORDERNAME.get('startDate')).getValue()) && Ext.isEmpty(Ext.getCmp(ORDERNAME.get('endDate')).getValue()))){
			        	var resource = Ext.getCmp(ORDERNAME.get('resource')).getValue();
						if(resource == null){
							resource = 'ALL';
						}else{
							resource = resource.trim();
						}
						if(isPageLoad && isPageLoad===true){
					    	searchParams = {
		            				'orderSearchView.orderSearchCondition.startDate':new Date(new Date().setDate(new Date().getDate()-30)),
		            	  			'orderSearchView.orderSearchCondition.endDate':new Date(),
		            	  			'orderSearchView.orderSearchCondition.resource':resource,// 订单来源
		            	  			'orderSearchView.orderSearchCondition.orderStatus':Ext.getCmp(ORDERNAME.get('orderStatus')).getValue().trim(),// 订单状态
		            	  			'orderSearchView.orderSearchCondition.createEmpName':Ext.getCmp('orderQuery_Form').getForm().findField(ORDERNAME.get('createUser')).getValue().trim(),// 制单员
		        		            /**			
		     					    * 修改人：张斌
		     						*修改时间：2013-7-27 11:20
		     						*原有内容：
		     						*'orderSearchView.orderSearchCondition.startStationId':Ext.getCmp('orderQuery_Form').getForm().findField(ORDERNAME.get('consignorComeFromPoint')).getValue()// 始发网点
		     						*修改原因：store向后台发送穿请求时增加“运输方式”查询条件
		     					    */
		     		                //begin
		            	  			'orderSearchView.orderSearchCondition.startStationId':Ext.getCmp('orderQuery_Form').getForm().findField(ORDERNAME.get('consignorComeFromPoint')).getValue(),// 始发网点
		            	  			'orderSearchView.orderSearchCondition.transportMode':Ext.getCmp('orderQuery_Form').getForm().findField(ORDERNAME.get('modeOfTransportation')).getValue()// 运输方式
		            	  		    //end
		            	   };
		        		   
		        	    }else{
		        	    	searchParams = {
		        	    			'orderSearchView.orderSearchCondition.startDate':Ext.getCmp(ORDERNAME.get('startDate')).getValue(),
		            	  			'orderSearchView.orderSearchCondition.endDate':Ext.getCmp(ORDERNAME.get('endDate')).getValue(),
		            	  			'orderSearchView.orderSearchCondition.resource':resource,// 订单来源
		            	  			'orderSearchView.orderSearchCondition.orderStatus':Ext.getCmp(ORDERNAME.get('orderStatus')).getValue().trim(),// 订单状态
		            	  			'orderSearchView.orderSearchCondition.createEmpName':Ext.getCmp('orderQuery_Form').getForm().findField(ORDERNAME.get('createUser')).getValue().trim(),// 制单员
		        		            /**			
		     					    * 修改人：张斌
		     						*修改时间：2013-7-27 11:20
		     						*原有内容：
		     						*'orderSearchView.orderSearchCondition.startStationId':Ext.getCmp('orderQuery_Form').getForm().findField(ORDERNAME.get('consignorComeFromPoint')).getValue()// 始发网点
		     						*修改原因：store向后台发送穿请求时增加“运输方式”查询条件
		     					    */
		     		                //begin
		            	  			'orderSearchView.orderSearchCondition.startStationId':Ext.getCmp('orderQuery_Form').getForm().findField(ORDERNAME.get('consignorComeFromPoint')).getValue(),// 始发网点
		            	  			'orderSearchView.orderSearchCondition.transportMode':Ext.getCmp('orderQuery_Form').getForm().findField(ORDERNAME.get('modeOfTransportation')).getValue()// 运输方式
		            	  		    //end
		            	   };
		        	    }
	            }
			}
  			Ext.apply(operation,{
				params : searchParams
			});
  		});
		this.callParent();
	}
});
// ---------------------------------------界面已完成--------------------------------------------------------------------
/**
 * .
 * <p>
 * 订单信息查看页面收货方FORM<br/>
 * </p>
 * 
 * @returns ReceiveGoodForm 订单分配页面收货方FORM 的EXT对象
 * @author 张登
 * @时间 2012-03-13
 */
Ext.define('ReceiveGoodForm', {
		extend:'Ext.form.Panel',
		cls:'formpaneltest',
		items:null,
		title: i18n('i18n.order.receiving'),
		height:96,
		id : 'receiveGoodForm',
		layout:{
			type:'table',
			columns:3
		},
        defaults:{
        	xtype:'readonlytextfield',
        	readOnly:true,
        	labelWidth:60,
        	width:190
        },
		getItems:function(){
			return [{
                name : ORDERNAME.get('receivingName'),
                fieldLabel: i18n('i18n.order.connectMan')
            },{
                name : ORDERNAME.get('receivingMobilePhone'),
                fieldLabel: i18n('i18n.order.mobilePhoneNum')
            },{
            	width:180,
                name : ORDERNAME.get('receivingPhone'),
                fieldLabel: i18n('i18n.order.phoneNum')
            },{  xtype: 'container',
	            layout: 'hbox',
	            width:'100%',
	            colspan:2,
	            defaultType: 'readonlytextfield',
	            items: [{
	                 labelWidth:60,
                    name : ORDERNAME.get('receivingProvince'),
                    fieldLabel: i18n('i18n.order.receiveAddress')
                },{
                	width:86,
                	style:'margin-left:1px',
                    name : ORDERNAME.get('receivingCity')
                },{
                	width:86,
                	style:'margin-left:1px',
                    name : ORDERNAME.get('receivingCounty')
                }]
        }, {
        	width:180,
        	style:'margin-left:1px',
        	name: ORDERNAME.get('receivingAddress')
        }];
		},
		initComponent:function()
		{
			var me = this;
			me.items = me.getItems();
			this.callParent();
		}
});


/**
 * .
 * <p>
 * 订单分配页面发货方FORM<br/>
 * </p>
 * 
 * @returns SendGoodForm 订单分配页面收货方FORM 的EXT对象
 * @author 张登
 * @时间 2012-03-13
 */
Ext.define('SendGoodForm', {
//		extend:'TitleFormPanel',
		extend:'Ext.form.Panel',
//		cls:'form_fieldset',
		cls:'formpaneltest',
		title: i18n('i18n.order.consignor'),
		height:95,
		layout:{
			type:'table',
			columns:3
		},
		defaults:{
        	xtype:'readonlytextfield',
        	readOnly:true,
        	labelWidth:60,
        	width:190
        },
//		flex:1,
		items:null,
		id : 'sendGoodForm',
		getItems:function(){
			return [{
                name : ORDERNAME.get('consignorName'),
                fieldLabel: i18n('i18n.order.connectMan')
            },{
                name : ORDERNAME.get('consignorMobilePhone'),
                fieldLabel: i18n('i18n.order.mobilePhoneNum')
            },{
                name : ORDERNAME.get('consignorPhone'),
                fieldLabel: i18n('i18n.order.phoneNum'),
                width:180
            },{
            xtype: 'container',
            layout: 'hbox',
            width:'100%',
            colspan:2,
            defaultType: 'readonlytextfield',
            items: [{
                labelWidth:60,
                name : ORDERNAME.get('consignorProvince'),
                fieldLabel: i18n('i18n.order.receiveMoneyAddress')
            },{
                width:86,
                style:'margin-left:1px',
                name : ORDERNAME.get('consignorCity')
            },{
                width:86,
                style:'margin-left:1px',
                name : ORDERNAME.get('consignorCounty')
            }]
        },{
        	width:180,
        	style:'margin-left:1px',
        	name:ORDERNAME.get('consignorAddress')
        }];
		},
		initComponent:function()
		{
			var me = this;
			me.items = me.getItems();
			this.callParent();
		}
});

/**
 * .
 * <p>
 * 订单分配页面基本信息FORM<br/>
 * </p>
 * 
 * @returns BasicInfoForm 订单分配页面基本信息FORM 的EXT对象
 * @author 张登
 * @时间 2012-03-13
 */
Ext.define('BasicInfoForm', {
		extend:'TitleFormPanel',
		items:null,
		height:200,
//		flex:2,
        title: i18n('i18n.order.basicInfo'),
		extend:'Ext.form.Panel',
		cls:'formpaneltest',
		id : 'basicInfoForm',
		layout:{
        	type:'table',
        	columns:3
        },
        defaults:{
        	width:190,
        	labelWidth:80,
        	xtype:'readonlytextfield'
        },
		/**
		 * .
		 * <p>
		 * 获取ITEMS属性 </br>
		 * </p>
		 * 
		 * @author 张登
		 * @时间 2012-03-13
		 */
		getItems:function(){
			return [{
                name : ORDERNAME.get('orderNum'),
                fieldLabel: i18n('i18n.order.orderNum')
            },{
                name : ORDERNAME.get('channelNumber'),
                fieldLabel: i18n('i18n.order.channelNumber')
            },{
            	width:180,
            	labelWidth:60,
                name : ORDERNAME.get('waybillNum'),
                fieldLabel: i18n('i18n.order.wayBillNum')
            },{
                name : ORDERNAME.get('goodsName'),
                fieldLabel: i18n('i18n.order.goodsName')
            },{
                name : ORDERNAME.get('insuerAmount'),
                fieldLabel: i18n('i18n.order.insureValue')
            },{
            	width:180,
            	labelWidth:60,
                name : ORDERNAME.get('codAmount'),
                fieldLabel: i18n('i18n.order.cod')
            },{
                name : ORDERNAME.get('goodsPackagingMaterials'),
                fieldLabel: i18n('i18n.order.packagingMaterials')
            },{
                name : ORDERNAME.get('consignorIsCargo'),
                fieldLabel: i18n('i18n.order.whetherheGoods')
            },{
            	width:180,
            	labelWidth:60,
                name : ORDERNAME.get('createUser'),
                fieldLabel: i18n('i18n.order.documentationCclerk')
            },{
                name : ORDERNAME.get('consignorCargoTime'),
                fieldLabel: i18n('i18n.order.startReceiveGoodsTime')
            },{
                name : ORDERNAME.get('consignorToCargoTime'),
                fieldLabel: i18n('i18n.order.endReceiveGoodsTime')
            },{
            	labelWidth:60,
            	width:180,
                name : ORDERNAME.get('createDate'),
                fieldLabel: i18n('i18n.order.createTime')
            },{
                name : 'channelCustId',// 下单人
                fieldLabel: i18n('i18n.order.placeAnOrder')
            },{
                width:370,
                colspan:2,
                name : ORDERNAME.get('storageAndTransportationMatters'),
                fieldLabel: i18n('i18n.order.storageAndTransportationMatters')
            },{
                name : ORDERNAME.get('modeOfTransportation'),// 运输方式
                fieldLabel: i18n('i18n.order.modeOfTransportation')
            },{
                width:370,
                colspan:2,
                name : ORDERNAME.get('goodsTotalNumber'),
                fieldLabel:'重/体/件'
            }];
		},
		initComponent:function()
		{
			var me = this;
			me.items = me.getItems();
			this.callParent();
		}
});
/**
 * .
 * <p>
 * 订单查询GIRD<br/>
 * </p>
 * 
 * @returns OrderGrid 订单查询GIRD 的EXT对象
 * @author 张斌
 * @时间 2012-03-19
 */
Ext.define('OrderOperationGrid',{
	extend:'PopupGridPanel',
	flex:1,
	id:'orderOperationGrid',
	store:null,
    getColumns:function(){
    	var me = this;
    	return [{
            xtype: 'rownumberer',
            width: 45,
            text     : i18n('i18n.order.serialNumber'),
            sortable: false
        },{
            text     : i18n('i18n.order.operatorPerson'),
            sortable : false,
            width:120,
            dataIndex: ORDERNAME.get('operatorId')
        },{
            text     : i18n('i18n.order.operationTime'),
            sortable : true,
            width:120,
            renderer : function(value){
            	var date = new Date(value);
            	return date.format('yyyy-MM-dd hh:mm');
            },
            dataIndex: ORDERNAME.get('operatorTime')
        },{
            text     : i18n('i18n.order.operationOfTheContents'),
            sortable : true,
            dataIndex: ORDERNAME.get('operatorContent')
        },{
            text     : i18n('i18n.order.operatorType'),
            sortable : true,
            dataIndex: ORDERNAME.get('operatorType'),
            renderer : function(value){
            	return DButil.changeDictionaryCodeToDescrip(value,me.dataDictionary.ORDER_OPERATION);
            }
        },{
            text     : i18n('i18n.order.operatorDept'),
            sortable : true,
            dataIndex: ORDERNAME.get('operatorOrgId')
        }];
    },
    initComponent:function(){
		var me = this;
		me.columns = me.getColumns();
		// 增加store的beforeload方法
		Ext.data.StoreManager.lookup('orderOperationLogStore').on('beforeload',function(noAllocationOrderStore,operation,e){
			var orderId = Ext.getCmp('orderGrid').getSelectionModel( ).getSelection()[0].data.id;
  			Ext.apply(operation,{
				params : {'orderId':orderId}
			});
  		});
		this.callParent();
		me.getView().on('render', function(view) {
		    view.tip = Ext.create('Ext.tip.ToolTip', {
		        target: view.el,
		        delegate: view.itemSelector,
		        trackMouse: true,
		        listeners: {
		            beforeshow: function updateTipBody(tip) {
		            	var info = null;
		            	if(Ext.isEmpty(view.getRecord(tip.triggerElement).get(ORDERNAME.get('operatorContent')))){
		            		info = i18n('i18n.order.haveNull');
		            	}else{
		            		info = view.getRecord(tip.triggerElement).get(ORDERNAME.get('operatorContent'));
		            	}
		                tip.update(i18n('i18n.order.operationOfTheContents')+':'+info);
		            }
		        }
		    });
		});
	}
});



/**.
 * <p>
 * 订单创建界面</br>
 * </p>
 * @returns CreateOrderView 订单创建界面的EXT对象
 * @author  张斌
 * @时间    2012-03-8
 */
Ext.define('CreateOrderView', {
	extend:'BasicFormPanel',
	autoScroll : true,
	consignorForm:null,//发货方form
	receivingForm:null,//收货方form
	goodsInfoForm:null,//货物信息FORM
	transportationAndServiceFrom:null,//运输及服务FORM
	createOrderButtonPanel:null,
//	recevicGoodsInfo:null,//约车form
	isReadOnly:null,//form中元素是否可读
	hidden:null,//form中元素是否隐藏
	orderData:null,
//	layout:{type:'table',
//		columns:1,
//	},
	id:'createOrderView',
	width:800,
    /**.
    * <p>
    * 订单数据提交进行的操作
    * </br>
    * </p>
    * @author  张斌
    * @时间    2012-03-13
    */
    commitOrder:function(){
    	var me = this;
    	//是否提示约车 isBookVehicle=="1" 约车
    	var msg = (isBookVehicle=="1"?'isSureCommitOrder':'isCommitOrder');
    	MessageUtil.showQuestionMes(i18n('i18n.order.'+msg),
				function(e){
					if (e == 'yes') {
						var successFn = function(json){
							   Ext.getCmp('commitOrderId').setDisabled(false);
				    		   Ext.getCmp('transportationAndService_form').getForm().reset();
				    		   Ext.getCmp('goodsInfo_form').getForm().reset();
				    		   Ext.getCmp('receiving_form').getForm().reset();
				    		   Ext.getCmp('consignor_form').getForm().reset();
				    		   Ext.getCmp('consignor_form').initNetPoint();
				    		   //设置AB货是否可用和必填
				    		   Ext.getCmp('goodsTypeA').setDisabled(true);
								Ext.getCmp('goodsTypeB').setDisabled(true);
								Ext.getCmp(ORDERNAME.get('goodsType')).allowBlank = true;
								Ext.getCmp(ORDERNAME.get('goodsType')).reset();
								//提货方式和签收单还原
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
			     				Ext.getCmp(ORDERNAME.get('deliveryWay')).setValue('PICKSELF');//默认值
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
			     				if(!Ext.isEmpty(Ext.getCmp('callCarsID'))){
			    					Ext.getCmp('callCarsID').setDisabled(false);
			    				}
								MessageUtil.showInfoMes(json.message);
				    	};
				    	var failureFn = function(json){
				    		Ext.getCmp('commitOrderId').setDisabled(false);
				    		MessageUtil.showErrorMes(json.message);
				    	};
				    	if(me.getForm().isValid()){
				    		if (isBookVehicle == "1") {// 如果约车
						var startDateTime = Ext.getCmp('addCallCarFromDate')
								.getValue().getTime();
						var endDateTime = Ext.getCmp('addCallCarToDate')
								.getValue().getTime();
						var date = new Date();
						var newDate = new Date(date.getFullYear(),date.getMonth(),date.getDate(),date.getHours(),date.getMinutes(),0);
							if (startDateTime < newDate.getTime()) {
								MessageUtil
										.showMessage(i18n("i18n.order.mustStartTimeThanNow"))
								Ext.getCmp('addCallCarFromDate').setValue();
								return;
							}
							if ((endDateTime - startDateTime) > 3 * 24 * 60 * 60
									* 1000) {
								MessageUtil
										.showMessage(i18n('i18n.order.bettewCannotBiggerThanThree'));
								Ext.getCmp('addCallCarToDate').setValue();
								return;
							}
							if ((endDateTime - startDateTime) < 2.5 * 60 * 60* 1000) {
								MessageUtil
										.showMessage(i18n("i18n.order.endtimeSmallThanStartTime2.5"));
								Ext.getCmp('addCallCarToDate').setValue();
								return;
							}
				    		}
				    		var orderModel = new OrderModel();
					 	    me.getForm().updateRecord(orderModel);
					 	    var startStation = Ext.getCmp(ORDERNAME.get('consignorComeFromPoint')).getValue();
					 	   var startStationName = Ext.getCmp(ORDERNAME.get('consignorComeFromPoint')).getRawValue();
					 	   if(startStation==startStationName){
				    			MessageUtil.showMessage(i18n('i18n.order.mustStartStationSelectDept'));
				    			Ext.getCmp(ORDERNAME.get('consignorComeFromPoint')).setValue('');
								return ;
				    		}
					 	   var endStation = Ext.getCmp(ORDERNAME.get('receivingToPoint')).getValue();
					 	   var endStationName = Ext.getCmp(ORDERNAME.get('receivingToPoint')).getRawValue();
					 	   if(endStation==endStationName){
				    			MessageUtil.showMessage(i18n('i18n.order.mustEndStationSelectDept'));
				    			Ext.getCmp(ORDERNAME.get('receivingToPoint')).setValue('');
								return ;
				    		}
					 	    var receivingExtension = Ext.getCmp(ORDERNAME.get('receivingExtension')).getValue();
							var receivingAreaCode= Ext.getCmp(ORDERNAME.get('receivingAreaCode')).getValue();
							var receivPhone = Ext.getCmp(ORDERNAME.get('receivingPhone')).getValue();
							//如果自己不为空，且前一个不为空，才回家上'-'
							if((!Ext.isEmpty(receivingAreaCode))&&(!Ext.isEmpty(receivPhone))){
								receivPhone = '-'+receivPhone;
							}
							if((!Ext.isEmpty(receivPhone))&&(!Ext.isEmpty(receivingExtension))){
								receivingExtension = '-'+receivingExtension;
							}
							var receivingPhone = receivingAreaCode+receivPhone+receivingExtension;
							var consignorExtension = Ext.getCmp(ORDERNAME.get('consignorExtension')).getValue();
							var consignorAreaCode= Ext.getCmp(ORDERNAME.get('consignorAreaCode')).getValue();
							var consignorPhone = Ext.getCmp(ORDERNAME.get('consignorPhone')).getValue();
							//如果自己不为空，且前一个不为空，才回家上'-'
							if((!Ext.isEmpty(consignorAreaCode))&&(!Ext.isEmpty(consignorAreaCode))){
								consignorPhone = '-'+consignorPhone;
							}
							if((!Ext.isEmpty(consignorPhone))&&(!Ext.isEmpty(consignorExtension))){
								consignorExtension = '-'+consignorExtension;
							}
							var contactPhone =consignorAreaCode+consignorPhone+consignorExtension;
							if(Ext.isEmpty(consignorPhone)){
								contactPhone = null;
							}
							if(Ext.isEmpty(receivPhone)){
								receivingPhone = null;
							}
							if(contactPhone=='--'){
								contactPhone = null;
							}
							if(receivingPhone=='--'){
								receivingPhone = null;
							}
							var consignorAreaAddress = Ext.getCmp(ORDERNAME.get('consignorPhone')).getValue();
							//发货方联系人手机号
							var consignorMobilePhone = Ext.getCmp(ORDERNAME.get('consignorMobilePhone')).getValue();
							//收货方联系人手机号
							var receivingMobilePhone = Ext.getCmp(ORDERNAME.get('receivingMobilePhone')).getValue();
							//发货方、收货方固定电话和手机必填其一
//       	                var isReceiveGoods = Ext.getCmp('isReciveGoodsYes').getValue();
							
							var recevingAreaAddress = Ext.getCmp('receivingAreaSelect').getRawValue();
							var recevingAreas = recevingAreaAddress.split('-');
							var consignorAreaAddress = Ext.getCmp('consignorAreaSelect').getRawValue();
							var consignorAreas = consignorAreaAddress.split('-');
							var consignorDetailAddress= Ext.getCmp(ORDERNAME.get('consignorAddress')).getValue();
							
//                        	if (isReceiveGoods) {
								if(consignorAreas.length<3){
									MessageUtil.showMessage(i18n('i18n.order.areaAll'));
									return ;
								}else if(DButil.isHaveEmpty(consignorAreas)){
									MessageUtil.showMessage(i18n('i18n.order.areaAll'));
									return ;
								}
//		                    }
							if(recevingAreas.length<3){
								MessageUtil.showMessage(i18n('i18n.order.recevingAreaAll'));
								return ;
							}else if(DButil.isHaveEmpty(recevingAreas)){
								MessageUtil.showMessage(i18n('i18n.order.recevingAreaAll'));
								return ;
							}
							if(Validator.isEmptyPhoneAndMobilePhone(contactPhone,consignorMobilePhone)){
								MessageUtil.showMessage(i18n('i18n.order.consignorPhoneAndMobilePhone'));
								  return ;
							}
//							if(Validator.isEmptyPhoneAndMobilePhone(receivingPhone,receivingMobilePhone)){
//								DButil.showMessage(i18n('i18n.order.receivingPhoneAndMobilePhone'));
//								  return ;
//							}
							//设置省市区
							
							orderModel.set(ORDERNAME.get('createUser'),User.userName);
							orderModel.set(ORDERNAME.get('receivingProvince'),recevingAreas[0]);
							orderModel.set(ORDERNAME.get('receivingCity'),recevingAreas[1]);
							orderModel.set(ORDERNAME.get('receivingCounty'),recevingAreas[2]);
							orderModel.set(ORDERNAME.get('consignorProvince'),consignorAreas[0]);
							orderModel.set(ORDERNAME.get('consignorCity'),consignorAreas[1]);
							orderModel.set(ORDERNAME.get('consignorCounty'),consignorAreas[2]);
							//设置电话号码
							orderModel.set(ORDERNAME.get('consignorPhone'),contactPhone);
							orderModel.set(ORDERNAME.get('receivingPhone'),receivingPhone);
							orderModel.set(ORDERNAME.get('resource'),'BUSINESS_DEPT');
							var startStationName = Ext.getCmp(ORDERNAME.get('consignorComeFromPoint')).getRawValue();
							orderModel.set(ORDERNAME.get('startStationName'),startStationName);
							var param =null;
							if(isBookVehicle=="1"){//如果是约车
								var startTime = orderModel.get(ORDERNAME.get('consignorCargoTime'));
								var endTime = orderModel.get(ORDERNAME.get('consignorToCargoTime'));
								if(!Ext.isEmpty(startTime)){
									if(startTime>endTime){
										MessageUtil.showMessage(i18n('i18n.order.startTimeMoreEndTime'));
										return ;
									}
								}
					    		var bookInfoform=Ext.getCmp("recevicGoodsInfoID").getForm();
					    		var vehicleTeam = bookInfoform.findField(ORDERNAME.get('vehicleTeam')).getValue();
					    		var vehicleTeamName = bookInfoform.findField(ORDERNAME.get('vehicleTeam')).getRawValue();
					    		if(vehicleTeam==vehicleTeamName){
					    			MessageUtil.showMessage(i18n('i18n.order.mustTeamSelectDept'));
					    			bookInfoform.findField(ORDERNAME.get('vehicleTeam')).setValue('');
									return ;
					    		}
					    		var useVehicleDept = bookInfoform.findField(ORDERNAME.get('useVehicleDept')).getValue();
					    		var useVehicleDeptName = bookInfoform.findField(ORDERNAME.get('useVehicleDept')).getRawValue();
					    		if(useVehicleDept==useVehicleDeptName){
					    			MessageUtil.showMessage(i18n('i18n.order.mustUseDeptSelectDept'));
					    			bookInfoform.findField(ORDERNAME.get('useVehicleDept')).setValue('');
									return ;
					    		}
					    		var isTailBoard = bookInfoform.findField(ORDERNAME.get('isTailBoard')).getValue();
//					    		orderModel.set(ORDERNAME.get('vehicleTeam'),vehicleTeam);
//					    		orderModel.set(ORDERNAME.get('useVehicleDept'),useVehicleDept);
//					    		orderModel.set(ORDERNAME.get('isTailBoard'),isTailBoard);
					    		param = {//如果是约车会多传几个参数的
							 			 'orderView':{
							 			     'order':orderModel.data,
							 			     'useVehicleDept':useVehicleDept,
								 			 'vehicleTeam':vehicleTeam,
								 			 'isTailBoard':isTailBoard
							 			 },
							 			 'vehicleHistory':{
							 				 'vehicleTeamName':vehicleTeamName
							 			 },
							 			 'isBookVehicle':'1'
							 	  };
							}else{
								param = {
									'orderView':{'order':orderModel.data}
							 	};
							}
							Ext.getCmp('commitOrderId').setDisabled(true);
							orderbaseDataControl.commitOrder(param,successFn,failureFn);
				    	}
					}
		});
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
    	return  [ me.consignorForm,me.receivingForm,
    	          me.goodsInfoForm,me.transportationAndServiceFrom,
    	          Ext.create('AddOrderRecevicGoodsInfo',{disabled:true,hidden:true,id:'recevicGoodsInfoID'}),
    	          me.createOrderButtonPanel
//    	          {
//			id:'consignorForm',
//			columnWidth: 1,
//			border: false,
//			items : [me.consignorForm]
//		},{
//			id:'receivingForm',
//			columnWidth: 1,
//			border: false,
//			items : [me.receivingForm]
//		},{
//			id:'goodsInfoForm',
//			columnWidth: 1,
//			border: false,
//			items : [me.goodsInfoForm]
//		},{
//			id:'transportationAndServiceFrom',
//			columnWidth: 1,
//			border: false,
//			items : [me.transportationAndServiceFrom]
//		},{
//			columnWidth: 1,
//			border: false,
//			items : [new CreateOrderButtonPanel()]
//		}
		];
    },
    initComponent:function(){
    	var me = this;
    	if(Ext.isEmpty(dataDictionary)){
    		MessageUtil.showErrorMes(i18n('i18n.order.dataDictionaryIsNull'));
    	}
    	me.consignorForm = new ConsignorForm({'height':120,'orderData':orderbaseDataControl,'isInitPoint':true});
//    	me.recevicGoodsInfo = new AddOrderRecevicGoodsInfo({'height':92,'orderData':orderbaseDataControl});
    	me.receivingForm = new ReceivingForm({'height':118,'orderData':orderbaseDataControl});
    	me.goodsInfoForm = new GoodsInfoForm({'height':92,'orderData':orderbaseDataControl});
    	me.createOrderButtonPanel= new CreateOrderButtonPanel();
    	me.transportationAndServiceFrom = new TransportationAndServiceFrom({'height':120,'orderData':orderbaseDataControl});
    	//Ext.getCmp(ORDERNAME.get('deliveryWay')).setValue('PICKSELF');
    	//Ext.getCmp(ORDERNAME.get('modeOfTransportation')).setValue('JZKH');
    	me.items = me.getItems();
    	this.callParent();
    		if(me.isReadOnly){			 
	    		me.createOrderButtonPanel.hide();
	    		//锁定界面所有控件
					var consignorFormFormField = me.consignorForm.getForm();
					Ext.getCmp('consignorAreaSelect').setDisabled(true);
					consignorFormFormField.getFields().each(function(field){
						field.setReadOnly(true);
						field.allowBlank=true;
					});
					me.consignorForm.doLayout();
					var receivingFormField = me.receivingForm.getForm();
					Ext.getCmp('receivingAreaSelect').setDisabled(true);
					receivingFormField.getFields().each(function(field){
						field.setReadOnly(true);
					    field.allowBlank=true;
					});
					me.receivingForm.doLayout();
					var goodsInfoFormField = me.goodsInfoForm.getForm();
					goodsInfoFormField.getFields().each(function(field){
						field.setReadOnly(true);
						field.allowBlank=true;	
					});
					me.goodsInfoForm.doLayout();
					var transportationAndServiceFromField = me.transportationAndServiceFrom.getForm();
					transportationAndServiceFromField.getFields().each(function(field){
						field.setReadOnly(true);
						field.allowBlank=true;
					});
					me.transportationAndServiceFrom.doLayout();
					var consignorAreas = me.orderData.get(ORDERNAME.get('consignorProvince'))+'-'+
					me.orderData.get(ORDERNAME.get('consignorCity'))+'-'+me.orderData.get(ORDERNAME.get('consignorCounty'));
					Ext.getCmp('consignorAreaSelect').forceSelection  = false;
	 				Ext.getCmp('consignorAreaSelect').setRawValue(consignorAreas);
	 				Ext.getCmp('consignorAreaSelect').forceSelection  = true;
	 				var receivingAreas = me.orderData.get(ORDERNAME.get('receivingProvince'))+'-'+
	 				me.orderData.get(ORDERNAME.get('receivingCity'))+'-'+me.orderData.get(ORDERNAME.get('receivingCounty'));
	 				Ext.getCmp('receivingAreaSelect').forceSelection  = false;
	 				Ext.getCmp('receivingAreaSelect').setRawValue(receivingAreas);
	 				Ext.getCmp('receivingAreaSelect').forceSelection  = true;
    	}
    },
    /**.
     * <p>
     * 重置方法，对表单中的数据全部重置</br>
     * </p>
     * @author  张斌
     * @时间    2012-03-8
     */
   resetForm:function(){
	   Ext.getCmp('transportationAndService_form').getForm().reset();
	   Ext.getCmp('goodsInfo_form').getForm().reset();
	   Ext.getCmp('receiving_form').getForm().reset();
	   Ext.getCmp('consignor_form').getForm().reset();
	   Ext.getCmp('consignor_form').initNetPoint();
	 //设置AB货是否可用和必填
	   Ext.getCmp('goodsTypeA').setDisabled(true);
		Ext.getCmp('goodsTypeB').setDisabled(true);
		Ext.getCmp(ORDERNAME.get('goodsType')).allowBlank = true;
   }
});

Ext.define('CreateOrderButtonPanel',{
	extend:'PopButtonPanel',
	flex:1,
	//width:500,
	items:null,
	border:false,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'middlebuttonpanel'
		},{
			xtype:'poprightbuttonpanel',
			width:500,
			items:[{
				xtype:'button',
				id:'commitOrderId',
			    text: i18n('i18n.order.commit'),
			    handler: function(){
			        Ext.getCmp('createOrderView').commitOrder();
				}
			},{
				xtype:'button',
				id:'callCarsID',
			    text: "约车",
			    handler: function(){
			        //实现在新建订单的同时进行约车的功能
			    	if(Ext.getCmp('callCarsID').getText()=='约车'){
				    	Ext.getCmp('recevicGoodsInfoID').enable();
				    	Ext.getCmp('recevicGoodsInfoID').show();
				    	Ext.getCmp('callCarsID').setText('取消约车');
				    	Ext.getCmp('commitOrderId').setText('提交并约车');
				    	isBookVehicle="1";//约车
				    }else {
				    	Ext.getCmp('recevicGoodsInfoID').disable();
				    	Ext.getCmp('recevicGoodsInfoID').hide();
				    	Ext.getCmp('callCarsID').setText('约车');
				    	Ext.getCmp('commitOrderId').setText(i18n('i18n.order.commit'));
				    	isBookVehicle="0";//取消约车
				    };
				    
				}
			},{
				xtype:'button',
			    text: i18n('i18n.order.reset'),
			    handler: function() {
			    	Ext.getCmp('createOrderView').resetForm();
			    	//提货方式和签收单还原
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
     				Ext.getCmp(ORDERNAME.get('deliveryWay')).setValue('PICKSELF');//默认值
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
     				if(!Ext.isEmpty(Ext.getCmp('callCarsID'))){
    					Ext.getCmp('callCarsID').setDisabled(false);
    				}
			    }
			}]
		}];
	}
});
//订单修改---------------
/**.
 * <p>
 * 修改订单WINDOW{UpdateOrderWindow}</br>
 * </p>
 * @author  张斌
 * @时间    2012-03-21
 */
Ext.define('UpdateOrderWindow',{
    extend:'PopWindow', 
	resizable:false,
    title: i18n('i18n.order.updateOrder'),
    border:false,
    height: 520,
    width: 810,
    autoScroll:true,
    data:null,
    basicInfoFromCallCar:null,
    id:'updateOrderWindow',
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
    },
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
 		if(me.beginDeptIsHashGoodsType||record.get(ORDERNAME.get('ifHashGoodsType'))){
 			Ext.getCmp('goodsTypeA').setDisabled(false);
 			Ext.getCmp('goodsTypeB').setDisabled(false);
 			Ext.getCmp(ORDERNAME.get('goodsType')).allowBlank = false;
 		}else{
 			Ext.getCmp('goodsTypeA').setDisabled(true);
 			Ext.getCmp('goodsTypeB').setDisabled(true);
 			Ext.getCmp(ORDERNAME.get('goodsType')).allowBlank = true;
 		}
 	},
    updateOrder:function(){
    	var me = this;
    	//更新数据
    	var data = me.data;
    	//@TODO这几个数据改变了，要将状态修改
    	var totalWeight = data.totalWeight;//重量
    	var totalVolume = data.totalVolume;//体积
    	var contactProvince  = data.contactProvince;//发货人省份
    	var contactCity = data.contactCity;//发货人市
    	var contactArea = data.contactArea;//发货人区域
    	var contactAddress = data.contactAddress;//发货人详细地址
    	Ext.getCmp('transportationAndService_form').getForm().updateRecord(data);
		Ext.getCmp('goodsInfo_form').getForm().updateRecord(data);
		Ext.getCmp('consignor_form').getForm().updateRecord(data);
		Ext.getCmp('receiving_form').getForm().updateRecord(data);
    	var successFn = function(json){
    		Ext.getCmp('updateOrderButton').setDisabled(false);
    		Ext.data.StoreManager.lookup('orderOperationLogStore').loadData(json.orderView.order.operationLogs);
    		DButil.refreshView(new OrderModel(json.orderView.order),dataDictionary.TRANS_TYPE);
    		Ext.getCmp('orderGrid').getStore().loadPage(1);
            Ext.getCmp('updateOrderWindow').close( );
            MessageUtil.showInfoMes(json.message);
    	};
    	var failureFn = function(json){
    		Ext.getCmp('updateOrderButton').setDisabled(false);
    		//TODO刷新数据
    		MessageUtil.showErrorMes(json.message);
    	};
    	//对DATA进行操作
    	if(Ext.getCmp('transportationAndService_form').getForm().isValid()&&Ext.getCmp('goodsInfo_form').getForm().isValid()&&Ext.getCmp('consignor_form').getForm().isValid()&&Ext.getCmp('receiving_form').getForm().isValid()){
    		var endStation = Ext.getCmp(ORDERNAME.get('receivingToPoint')).getValue();
		 	var endStationName = Ext.getCmp(ORDERNAME.get('receivingToPoint')).getRawValue();
		 	if(endStation==endStationName){
	    			MessageUtil.showMessage(i18n('i18n.order.mustEndStationSelectDept'));
	    			Ext.getCmp(ORDERNAME.get('receivingToPoint')).setValue('');
					return ;
	    	}
    		//获取电话
    		var receivingExtension = Ext.getCmp(ORDERNAME.get('receivingExtension')).getValue();
    		var receivingAreaCode= Ext.getCmp(ORDERNAME.get('receivingAreaCode')).getValue();
    		var receivPhone = Ext.getCmp(ORDERNAME.get('receivingPhone')).getValue();
    		if((!Ext.isEmpty(receivingAreaCode))&&(!Ext.isEmpty(receivPhone))){
				receivPhone = '-'+receivPhone;
			}
			if((!Ext.isEmpty(receivPhone))&&(!Ext.isEmpty(receivingExtension))){
				receivingExtension = '-'+receivingExtension;
			}
    		var receivingPhone = receivingAreaCode + receivPhone + receivingExtension;
    		var consignorExtension = Ext.getCmp(ORDERNAME.get('consignorExtension')).getValue();
    		var consignorAreaCode= Ext.getCmp(ORDERNAME.get('consignorAreaCode')).getValue();
    		var consignorPhone = Ext.getCmp(ORDERNAME.get('consignorPhone')).getValue();
    		if((!Ext.isEmpty(consignorAreaCode))&&(!Ext.isEmpty(consignorPhone))){
    			consignorPhone = '-'+consignorPhone;
			}
			if((!Ext.isEmpty(consignorPhone))&&(!Ext.isEmpty(consignorExtension))){
				consignorExtension = '-'+consignorExtension;
			}
    		var contactPhone = consignorAreaCode + consignorPhone + consignorExtension;
    		//发货方联系人手机号
    		var consignorMobilePhone = Ext.getCmp(ORDERNAME.get('consignorMobilePhone')).getValue();
    		//收货方联系人手机号
    		var receivingMobilePhone = Ext.getCmp(ORDERNAME.get('receivingMobilePhone')).getValue();
    		//发货方、收货方固定电话和手机必填其一
    		if(contactPhone=='--'){
				contactPhone = "";
			}//主拨号码为空，则相当于电话号码没有填写
    		if(Ext.isEmpty(consignorPhone)){
				contactPhone = "";
			}
    		if(receivingPhone=='--'){
				receivingPhone = "";
			}
    		if(Ext.isEmpty(receivPhone)){
				receivingPhone = "";
			}
    		if(Validator.isEmptyPhoneAndMobilePhone(contactPhone,consignorMobilePhone)){
    			MessageUtil.showMessage(i18n('i18n.order.consignorPhoneAndMobilePhone'));
    			  return ;
    		}
//    		if(Validator.isEmptyPhoneAndMobilePhone(receivingPhone,receivingMobilePhone)){
//    			DButil.showMessage(i18n('i18n.order.receivingPhoneAndMobilePhone'));
//    			  return ;
//    		}
    		//获取省市区数据
    		var recevingAreaAddress = Ext.getCmp('receivingAreaSelect').getRawValue();
			var recevingAreas = recevingAreaAddress.split('-');
			var consignorAreaAddress = Ext.getCmp('consignorAreaSelect').getRawValue();
			var consignorAreas = consignorAreaAddress.split('-');
			if(recevingAreas.length<3){
				MessageUtil.showMessage(i18n('i18n.order.recevingAreaAll'));
				return ;
			}else if(DButil.isHaveEmpty(recevingAreas)){
				MessageUtil.showMessage(i18n('i18n.order.recevingAreaAll'));
				return ;
			}
			if(consignorAreas.length<3){
				MessageUtil.showMessage(i18n('i18n.order.areaAll'));
				return ;
			}else if(DButil.isHaveEmpty(consignorAreas)){
				MessageUtil.showMessage(i18n('i18n.order.areaAll'));
				return ;
			}
			//设置省市区
			if(Ext.isEmpty(recevingAreas[0])){
				data.set(ORDERNAME.get('receivingProvince'),'');
			}else{
				data.set(ORDERNAME.get('receivingProvince'),recevingAreas[0]);
			}
			if(Ext.isEmpty(recevingAreas[1])){
				data.set(ORDERNAME.get('receivingCity'),'');
			}else{
				data.set(ORDERNAME.get('receivingCity'),recevingAreas[1]);
			}
			if(Ext.isEmpty(recevingAreas[2])){
				data.set(ORDERNAME.get('receivingCounty'),'');
			}else{
				data.set(ORDERNAME.get('receivingCounty'),recevingAreas[2]);
			}
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
			if(Ext.isEmpty(contactPhone)){
				data.set(ORDERNAME.get('consignorPhone'),'');
			}else{
				data.set(ORDERNAME.get('consignorPhone'),contactPhone);
			}
			if(Ext.isEmpty(receivingPhone)){
				data.set(ORDERNAME.get('receivingPhone'),'');
			}else{
				data.set(ORDERNAME.get('receivingPhone'),receivingPhone);
			}
    		var param = {'orderView':{'order':data.data}};
    		Ext.getCmp('updateOrderButton').setDisabled(true);
    		orderbaseDataControl.updateOrder(param,successFn,failureFn);
    	}
    },
    getItems:function(){
    	var me = this;
    	return [{
			columnWidth: 1,
			border: false,
			items : [me.basicInfoFromCallCar]
		},{
			columnWidth: 1,
			border: false,
			items : [me.consignorForm]
		},{
			border: false,
			items : [me.receivingForm]
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
//			items : [me.recevicGoodsInfo]
//			
//		}
		];
    },
    getFbar:function(){
    	return [{
				xtype:'button',
			    text: i18n('i18n.order.commit'),
			    id:'updateOrderButton',
			    handler: function(){
			        Ext.getCmp('updateOrderWindow').updateOrder();
				}
			},{
				xtype:'button',
				id:'hideButton',
			    text: i18n('i18n.order.cancel'),
			    handler: function() {
			    	Ext.getCmp('updateOrderWindow').close();
			    }
			}];
    },
    initComponent:function(){
    	var me = this;
    	me.fbar= me.getFbar();
    	me.basicInfoFromCallCar = new BasicInfoFromCallCar();
      	me.consignorForm = new ConsignorForm({'orderData':orderbaseDataControl,'isInitPoint':false});//是否需要初始化始发网点
      	me.receivingForm = new ReceivingForm({'orderData':orderbaseDataControl});
    	me.goodsInfoForm = new GoodsInfoForm({'orderData':orderbaseDataControl});
    	me.transportationAndServiceFrom = new TransportationAndServiceFrom({'extend':'PopTitleFormPanel','orderData':orderbaseDataControl});
    	me.consignorForm.alias = 'widget.poptitleformpanel';
    	me.consignorForm.cls = 'popup_form_feildset';
    	me.receivingForm.alias = 'widget.poptitleformpanel';
    	me.receivingForm.cls = 'popup_form_feildset';
    	me.goodsInfoForm.alias = 'widget.poptitleformpanel';
    	me.goodsInfoForm.cls = 'popup_form_feildset';
//    	me.recevicGoodsInfo.alias = 'widget.poptitleformpanel';
//    	me.recevicGoodsInfo.cls = 'popup_form_feildset';
    	me.transportationAndServiceFrom.alias = 'widget.poptitleformpanel';
    	me.transportationAndServiceFrom.cls = 'popup_form_feildset';
    	
    	me.transportationAndServiceFrom.alias = 'widget.poptitleformpanel';
    	me.transportationAndServiceFrom.cls = 'popup_form_feildset';
    	me.items = me.getItems();
    	//@TODO 设置一些元素为readOnly
    	DButil.setHiddenAndDestroy([ORDERNAME.get('consignorCustName'),ORDERNAME.get('receivingCustName'),
    	                            ORDERNAME.get('receivingCustCode'),ORDERNAME.get('consignorCustCode'),
    	                            ORDERNAME.get('consignorComeFromPoint'), 'consignorEmpty','receivingEmpty',
    	                            'searchElectronicMap_receiving','searchElectronicMap_consignor']);
    	DButil.setReadOnly([ORDERNAME.get('receivingName'),ORDERNAME.get('consignorName')]);
    	
    	if (me.data.get(ORDERNAME.get('resource'))=='ONLINE') {
    		Ext.getCmp(ORDERNAME.get('insuerAmount')).setDisabled(true);
		}
    	if (me.data.get(ORDERNAME.get('resource'))=='WEIXIN'||me.data.get(ORDERNAME.get('resource'))=="HAOBAI"||me.data.get(ORDERNAME.get('resource'))=="XIAOMI"||me.data.get(ORDERNAME.get('resource'))=="HUAQIANGBAO") {
    		Ext.getCmp(ORDERNAME.get('receivingName')).setReadOnly(false);
		}
    	Ext.getCmp(ORDERNAME.get('textMessagesNotice')).setDisabled(true);
    	DButil.clearListeners([ORDERNAME.get('receivingMobilePhone'),ORDERNAME.get('consignorMobilePhone')]);
    	Ext.getCmp('receivingToPoint').on('select',function(com,records){
    		me.selectPoint(records[0]);
    	});
    	Ext.getCmp('telContainer').margin = '0 -200 0 -30';
    	this.callParent();
    }
});
/**.
 * <p>
 * 基础信息FORM<br/>
 * </p>
 * @returns BasicInfo 基础信息FORM	的EXT对象
 * @author  张斌
 * @时间    2012-03-15
 */
Ext.define('BasicInfoFromCallCar', {
		extend:'PopTitleFormPanel',
		items:null,
//		width:750,
		id : 'basicInfo_form_callcar',
		/**.
		    * <p>
		    * 获取ITEMS属性
		    * </br>
		    * </p>
		    * @author  张斌
		    * @时间    2012-03-15
		    */
		getItems:function(){
			var me = this;
			return [{xtype: 'basicfiledset',
//							width:750,
							title: i18n('i18n.order.basicInfo'),
							layout:{
								type:'table',
								columns:5
							},
						    defaults:{
						    		labelWidth:50,
							        width:180,
							        labelSeparator:'',
							        xtype: 'readonlytextfield'
					           },
					    items:[{
								fieldLabel:i18n('i18n.order.orderNum'),
								id:ORDERNAME.get('orderNum'),
								name: ORDERNAME.get('orderNum')
							},{
								fieldLabel:i18n('i18n.order.channelNumber'),
								id:ORDERNAME.get('channelNumber'),
								name: ORDERNAME.get('channelNumber')
							},{
								fieldLabel:i18n('i18n.order.wayBillNum'),
								id:ORDERNAME.get('waybillNum'),
								name: ORDERNAME.get('waybillNum')
							}]
						}];
			},
		initComponent:function()
		{
			var me = this;
			me.items = me.getItems();
			this.callParent();
		}
});







/**.
 * <p>
 * 在创建订单页面点击约车按钮后出现的接货信息FORM<br/>
 * </p>
 * @returns RecevicGoodsInfo 接货信息FORM	的EXT对象
 * @author  肖红叶
 * @时间    2012-10-12
 */
Ext.define('AddOrderRecevicGoodsInfo', {
		extend:'PopTitleFormPanel',
		items:null,
		id : 'addRecevicGoodsInfo_form',
		initUserDept:function(){
			var me = this;
			var successFn = function(json){
				if(!Ext.isEmpty(json.orderView.beginDept)){
					Ext.data.StoreManager.lookup(ORDERNAME.get('useVehicleDept')).add([json.orderView.beginDept]);
	       			Ext.getCmp('addUseVehicleDept').setValue(json.orderView.beginDept.deptCode);
				}else{
					return ;
				}
				if(!Ext.isEmpty(json.vehicleHistory)){
					var pointModel=new PointModel();
					pointModel.set(ORDERNAME.get('pointCode'),json.vehicleHistory.vehicleTeamCode);
					pointModel.set(ORDERNAME.get('pointName'),json.vehicleHistory.vehicleTeamName);
					Ext.getCmp('addVehicleTeam').store.add(pointModel);
	       			Ext.getCmp('addVehicleTeam').setValue(json.vehicleHistory.vehicleTeamCode);
//	       			Ext.getCmp('addVehicleTeam').setRawValue(json.vehicleHistory.vehicleTeamName);
				}else{
					return ;
				}
			};
			var failureFn = function(){
				return ;
			};
			orderbaseDataControl.initPoint({'isBookVehicle':'1'},ORDERNAME.get('useVehicleDept'),successFn,failureFn);
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
			var me = this;
			var useViecheDept=null;
			var endDept=null;
			var defaultDate=Ext.Date.add(new Date(), Ext.Date.MINUTE, 3);
			return [{xtype: 'basicfiledset',
					width:'100%',
				    title: i18n('i18n.order.receiveInfo'),
				    layout: {type:'table',
				    columns:3
				    },
				    defaults:{
			           	labelWidth:80,
			            width:310,
					        labelSeparator:''
			           },
				    items:[{
				    	xtype     : 'datetimefield',
	                    name:         ORDERNAME.get('consignorCargoTime'),
		                id:'addCallCarFromDate',
		                fieldLabel: i18n('i18n.order.startReceiveGoodsTime'),
		                editable:       false,
		                allowBlank:false,
		                value :defaultDate,
		                format: 'Y-m-d H:i',
		                listeners : {
							select : function(th) {
							var fromDate = th.getValue();
							if (!DButil.ValidStartData(fromDate, defaultDate)) {
								th.setValue();
							}
						//结束时间自动增加2.5H
						Ext.getCmp('addCallCarToDate').setValue(Ext.Date.add(fromDate,Ext.Date.MINUTE,150));
							}
						}
					},{
						xtype     : 'datetimefield',
	                    name:         ORDERNAME.get('consignorToCargoTime'),
	                    id:'addCallCarToDate',
		                fieldLabel: i18n('i18n.order.endReceiveGoodsTime'),
		                editable:       false,
		                allowBlank:false,
		                value : Ext.Date.add(new Date(), Ext.Date.MINUTE, 153),
		                format: 'Y-m-d H:i',
		              	listeners : {
							select : function(th) {
								var fromDate = Ext.getCmp('addCallCarFromDate').getValue();
								var toDate = th.getValue();
								if (!DButil.ValidStartDataAndEndDate(fromDate, toDate)) {
									MessageUtil.showMessage(i18n("i18n.order.endtimeSmallThanStartTime2.5"));
											th.setValue();
								}
							}
						}
			        },{
						xtype: 'checkboxgroup',
				        vertical: true,
				        style:'margin-right:0px;',
				        items: [
				            { boxLabel: i18n('i18n.order.tailBoard'), name:ORDERNAME.get('isTailBoard'), inputValue:true,style:'margin-right:0px;' }
					    ]
					}
					,{
			  			xtype: 'combo',
			  			labelSeparator:'',
			  			maxLength:50,
			  			id:'addUseVehicleDept',
   			            fieldLabel: i18n('i18n.order.useVehicleDept'),
   			            name:ORDERNAME.get('useVehicleDept'),
			  	        store:orderbaseDataControl.getUserCarPointStoreByAddOrder(),
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
//			  	        		comb.store.removeAll();
			  	        		if(Ext.isEmpty(comb.getValue())){
			  	        			comb.setValue("");
			  	        	    }
			  	        	},
			  	        	select:function(th,records){
			  	        		var deptCode = records[0].get('deptCode');
			  	        		if(Ext.isEmpty(deptCode)){
			  	        			MessageUtil.showMessage(i18n('i18n.order.useDeptStandardCodeNUll'));
			  	          		    return;
			  	        		}
			  	        	}
			  	       },
			  	        pageSize: 10
					},
					{
						xtype: 'combo',
			  			labelSeparator:'',
			  			maxLength:50,
			  			id:'addVehicleTeam',
   			            fieldLabel: i18n('i18n.order.vehicleTeam'),
   			            name:ORDERNAME.get('vehicleTeam'),
			  	        store:Ext.create('AddOrderVehicleTeamPointStore',{
			  	        	listeners:{
				  	  			beforeload:function(store, operation, eOpts){
				  	  				Ext.apply(operation,{
				  	  					params : {
				  	  						'orderSearchView.deptSearchCondition.deptName':Ext.getCmp('addVehicleTeam').getRawValue(),
				  	  						'orderSearchView.deptSearchCondition.ifVehicleTem':true
				  	  						}
				  	  					}
				  	  				);	
				  	  			}
				  	  		}
			  	        }),
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
//			  	        		comb.store.removeAll();
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
			    }
					]
			}];
		},
		initComponent:function()
		{
			var me = this;
			me.items = me.getItems();
			me.initUserDept();			
			this.callParent();
//			var store=Ext.getCmp('addVehicleTeam').store;
//			store.on('beforeload',function(store, operation, eOpts){
//				Ext.apply(operation,{
//					params : {
//						'orderSearchView.deptSearchCondition.deptName':Ext.getCmp('addVehicleTeam').getRawValue(),
//						'orderSearchView.deptSearchCondition.ifVehicleTem':true
//						}
//					}
//				);	
//			});
		}
});

/**
 * <p>
 * 延迟发货弹出窗口</br>
 * </p>
 * @author 李春雨
 * @时间    2013-09-03
 */
Ext.define('DelayOrderWindow',{
	extend:'PopWindow',
	title:i18n('i18n.order.delayOrder'),//订单延迟
	id:'delayOrderWindow',
	getItems:function(){
		var me = this;
		var items = [new DelayOrderForm()];
		return items;
	},
	//处理火狐显示时间控件异常问题
	listeners : {
		'beforeshow' : function(){
			document.body.scrollTop=0;	//把滚动条设置到开头
		}
	},
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
	}
});

/**
 * <p>
 * 延迟发货弹出窗口中form</br>
 * </p>
 * @author 李春雨
 * @时间    2013-09-03
 */
Ext.define('DelayOrderForm', {
	extend:'PopTitleFormPanel',
	id : 'delayOrderForm',
	OrderData : null,// 连接ACTION的对象
	submitClick:function(){
	    	var me = this;
	    	var form  = Ext.getCmp('delayOrderForm');
	    	//后台返回成功
	    	var successFn = function(json){
	    		Ext.getCmp('delayOrderWindow').close();
	            MessageUtil.showInfoMes(json.message);
	            Ext.getCmp('orderGrid').getStore().loadPage(1);
	    	};
	    	//后台返回失败
	    	var failureFn = function(json){
	    		//TODO刷新数据
	    		MessageUtil.showErrorMes(json.message);
	    		Ext.getCmp('delayOrderWindow').close();
	    	};
	    	//订单是否有效
	    	if(form.getForm().isValid()){
	    		var startDate = new Date();
	    		var endDate =Ext.getCmp('delayEndDateField').getValue();
	    		if(Validator.isDateLessSevenDays(startDate,endDate)==1){
	    			MessageUtil.showMessage(i18n('i18n.order.dateMoreSevenDays'));//在线提示时间不得晚于当前时间7天
	    		}else if(Validator.isDateLessSevenDays(startDate,endDate)==2){
	    			MessageUtil.showMessage(i18n('i18n.order.endSmallerThanStart'));//起始时间不得晚于截止时间
	    		}else{
	    			//传递到后台
	    			var feedbackInfo = Ext.getCmp(ORDERNAME.get('returnInfo')).getRawValue();
    				if(Ext.getCmp(ORDERNAME.get('returnInfo')).getValue()=='USER_DEFINED'){
    					feedbackInfo = Ext.getCmp('otherReason').getValue();
    				}
    				if(Ext.isEmpty(feedbackInfo)){
    					MessageUtil.showMessage(i18n('i18n.order.plaeasSelectFeedbackInfo'));//请选择反馈信息！
    					return;
    				}
	    			var orderId = Ext.getCmp('orderGrid').getSelectionModel( ).getSelection()[0].data.id;
	    			endDate = DButil.changeLongToDate(endDate);
	    			var param = {'orderView':{'orderId':orderId,'delayTime':endDate,'feedbackInfo':feedbackInfo}};
	    			orderbaseDataControl.delayOrder(param,successFn,failureFn);
	    		}
	    	}

	    },
	getItems:function(){
		var me = this;
		var items = [{
            xtype     : 'datetimefield',
            id : 'delayEndDateField',
            value:new Date(),
            fieldLabel: i18n('i18n.order.delayOrderTime'),
            editable:false,
            format: 'Y-m-d H:i',
			defaultTime:[0,0,0],
			allowBlank:false,
			labelWidth:70,
			width:210
        },{
        	xtype : 'button',
        	text : i18n('i18n.order.commit'),
        	scope : 'DelayOrderForm',
        	handler : me.submitClick
        }]
		return items;
	},
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
	}
})


