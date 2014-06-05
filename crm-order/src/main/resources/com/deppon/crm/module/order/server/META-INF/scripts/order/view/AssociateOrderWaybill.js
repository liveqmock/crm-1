/**.
 * <p>
 * 将CommonOrderView中的元素组合成订单页面<br/>
 * </p>
 * @author  张斌
 * @时间    2012-03-08
 * </p>
 */

//设置Data层对象
var orderbaseDataControl =  (ORDERCONFIG.get("TEST"))? new OrderDataTest():new OrderData();
Ext.define('AssociateOrderWaybillButtonPanel',{
	extend:'PopButtonPanel',
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
			items:[{
		        xtype:'button',
		        id:'againRelation',
		        text: i18n('i18n.order.againRelation'),
		        handler:function(){
//		        	alert(Ext.getCmp(ORDERNAME.get('orderNum')).getValue()+"|"+Ext.getCmp(ORDERNAME.get('consignorMobilePhone')).getValue());
		        	if((!Ext.isEmpty(Ext.getCmp(ORDERNAME.get('orderNum')).getValue())) && (!Ext.isEmpty(Ext.getCmp('newWaybillNum').getValue()))){
		        		if(Ext.isEmpty(Ext.getCmp("orderNumberId").getValue())){
		        			MessageUtil.showMessage(i18n('i18n.order.orderInfoIsNull'));
		        			return;
		        		}
		        		if(Ext.isEmpty(Ext.getCmp("orderShipper").getValue())){
		        			MessageUtil.showMessage(i18n('i18n.order.wayllInfoIsNull'));
		        			return;
		        		}
		        		var isSuccessFn = function(json){//成功之后会先判断运单是否关联对应的订单
		        			var orderRecord=new OrderModel(json.orderView.order);
		        			if(Ext.isEmpty(orderRecord.get("id"))){//如果没有对应的关联就直接调用关联的方法
		        				var successFn = function(json){
		        					MessageUtil.showInfoMes(json.message);
		                  		};
		                  		var param = {'orderView':{
		                  						'waybill':{
		                  							'waybillNumber':Ext.getCmp('newWaybillNum').getValue()
		                  						},
		                  						'order':{
		                  							'orderNumber':Ext.getCmp(ORDERNAME.get('orderNum')).getValue()
		                  						}
		                  					  }
		                  					};
		                  		var failureFn = function(json){
		                  			MessageUtil.showErrorMes(json.message);
		                  		};
		                		orderbaseDataControl.associateOrderAndWaybill(param,successFn,failureFn);
		        			}else{//否则提示已经有对应的关联是否继续
		        				MessageUtil.showQuestionMes(i18n('i18n.order.confirmInfo'), function(btn){
		        				 if(btn=='yes'){
		        					 var successFn = function(json){
		        						 MessageUtil.showInfoMes(json.message);
			                      		};
			                      		var param = {'orderView':{
			                      						'waybill':{
			                      							'waybillNumber':Ext.getCmp('newWaybillNum').getValue()
			                      						},
			                      						'order':{
			                      							'orderNumber':Ext.getCmp(ORDERNAME.get('orderNum')).getValue()
			                      						}
			                      					  }
			                      					};
			                      		var failureFn = function(json){
			                      			MessageUtil.showErrorMes(json.message);
			                      		};
			                    		orderbaseDataControl.associateOrderAndWaybill(param,successFn,failureFn);
		        				 }
		        			 });
		        			}
		          		};
		          		var waybillparam = {'orderView':{'waybill':{'waybillNumber':Ext.getCmp('newWaybillNum').getValue()}}};
		          		var isFailureFn = function(json){
		          			MessageUtil.showErrorMes(json.message);
		          		};
		        		orderbaseDataControl.orderWaybillNum(waybillparam,isSuccessFn,isFailureFn);
		        	}else{//判断定运单不能为空结束
		        		Ext.getCmp(ORDERNAME.get('orderNum')).isValid();
		        		Ext.getCmp('newWaybillNum').isValid();
		        	}
		        }
		    },{
		        xtype:'button',
		        text: i18n('i18n.order.reset'),
		        id:'reset',
		        handler:function(){
		        	Ext.getCmp("orderQuery_Form").getForm().reset();
		        	Ext.getCmp("orderBisicInfo").getForm().reset();
		        	Ext.getCmp("wayBillBisicInfo").getForm().reset();
		        }
		    }]
		}];
	}
});
/**.
 * <p>
 * 订单查询FORM<br/>
 * </p>
 * @returns OrderQueryForm 订单查询FORM	的EXT对象
 * @author  张登
 * @时间    2012-03-19
 */
Ext.define('OrderQueryForm', {
		extend:'SearchFormPanel',
		id : 'orderQuery_Form',
		getItems:function(){
			var me = this;
			return [{
				layout:{
					type:'table',
					columns:3
				},
				defaultType:'textfield',
				defaults:{
					labelWidth:90,
					width:210
				},
                items:[{
	                xtype: 'textfield',
                    id:           ORDERNAME.get('orderNum'),
                    name:         ORDERNAME.get('orderNum'),
                    fieldLabel: i18n('i18n.order.orderNum'),
                    allowBlank:false,
                    blankText:i18n('i18n.order.orderNumberNotNull'),
                    listeners:{
                    	blur:function(th){
                    		if(!Ext.isEmpty(th.getValue()) && th.isValid()){//根据订单号查询订单
	                    		var successFn = function(json){
	                      			Ext.getCmp('againRelation').setDisabled(false);
	                        		Ext.getCmp('reset').setDisabled(false);
	                    			//如果为空，清空数据，设置按钮可用，提示
	                    			if (Ext.isEmpty(json.orderView.order)) {
	                    				MessageUtil.showMessage(i18n('i18n.order.err.orderNotExist'));
	                    				Ext.getCmp("orderBisicInfo").getForm().reset();
		                        		return;
	                    			}
	                    			var orderRecord=new OrderModel(json.orderView.order);		                    			
	                    			Ext.getCmp('orderBisicInfo').getForm().loadRecord(orderRecord);
	                    			var city = orderRecord.get(ORDERNAME.get('consignorCity'));
	                    			var province = orderRecord.get(ORDERNAME.get('consignorProvince'));
	                    			var county = orderRecord.get(ORDERNAME.get('consignorCounty'));
	                    			var address = orderRecord.get(ORDERNAME.get('consignorAddress'));
	                    			if(Ext.isEmpty(city)){
	                    				city = '';
	                    			}
	                    			if(Ext.isEmpty(province)){
	                    				province = '';
	                    			}
	                    			if(Ext.isEmpty(county)){
	                    				county = '';
	                    			}
	                    			if(Ext.isEmpty(address)){
	                    				address = '';
	                    			}
	                    			var allAddress = province + '-' + city + '-' + county + '-' + address;
	                    			Ext.getCmp('allConsignorAddress').setValue(allAddress);
//	                    			if(!Ext.isEmpty(orderRecord.get("waybillNumber"))){//如果运单号不为空的话显示运单号
	                    				Ext.getCmp(ORDERNAME.get('waybillNum')).setValue(orderRecord.get("waybillNumber"));	
//	                    			}
	                      		};
	                      		var param = {'orderView':{'order':{'orderNumber':th.getValue()}}};
	                      		var failureFn = function(json){
	                      			MessageUtil.showErrorMes(json.message);
	                      			Ext.getCmp("orderBisicInfo").getForm().reset();
	                      			Ext.getCmp('againRelation').setDisabled(false);
	                        		Ext.getCmp('reset').setDisabled(false);
	                      		};
	                    		orderbaseDataControl.getOrderByOrderNumber(param,successFn,failureFn);
                    		}
                    	},
                    	focus:function(th){//聚焦的时候，关联重置不可用
                    		Ext.getCmp('againRelation').setDisabled(true);
                    		Ext.getCmp('reset').setDisabled(true);
                    	}
                    }
                },{
                xtype: 'textfield',
                disabled:true,
                id:           ORDERNAME.get('waybillNum'),
                name:         ORDERNAME.get('waybillNum'),
                fieldLabel: i18n('i18n.order.orginWayBillNum')
            },{
                xtype: 'textfield',
                id:'newWaybillNum',
                name:'newWaybillNum',
                allowBlank:false,
                blankText:i18n('i18n.order.wayllNumberNotNull'),
                fieldLabel: i18n('i18n.order.newWayBillNum'),
                listeners:{
                	blur:function(th){
                		if(!Ext.isEmpty(th.getValue()) && th.isValid()){//根据运单号显示运单信息
                    		var successFn = function(json){
                      			Ext.getCmp('againRelation').setDisabled(false);
                        		Ext.getCmp('reset').setDisabled(false);
                    			//如果为空，清空数据，设置按钮可用，提示
                    			if (Ext.isEmpty(json.orderView.waybill)) {
                    				MessageUtil.showMessage(i18n('i18n.order.err.waybillNotExist'));
                    				Ext.getCmp("wayBillBisicInfo").getForm().reset();
                    				return;
                    			}
                    			var orderRecord=new WaybillModel(json.orderView.waybill);	
                    			Ext.getCmp('wayBillBisicInfo').getForm().loadRecord(orderRecord);
                      		};
                      		var param = {'orderView':{'waybill':{'waybillNumber':th.getValue()}}};
                      		var failureFn = function(json){
                      			MessageUtil.showMessage(json.message);
                      			Ext.getCmp("wayBillBisicInfo").getForm().reset();
                      			Ext.getCmp('againRelation').setDisabled(false);
                        		Ext.getCmp('reset').setDisabled(false);
                      		};
                    		orderbaseDataControl.waybillNum(param,successFn,failureFn);
                		}
                	},
                	focus:function(th){//聚焦的时候，关联重置不可用
                		Ext.getCmp('againRelation').setDisabled(true);
                		Ext.getCmp('reset').setDisabled(true);
                	}
                }
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
 * 订单基本信息<br/>
 * </p>
 * @author  张登
 * @时间    2012-03-13
 */
Ext.define('ReceiveGoodForm', {
		extend:'TitleFormPanel',
		items:null,
		id : 'orderBisicInfo',
		/**.
		    * <p>
		    * 获取ITEMS属性
		    * </br>
		    * </p>
		    * @author  张登
		    * @时间    2012-03-13
		    */
		getItems:function(){
			return [{
			    xtype:'basicfiledset',
				title:i18n('i18n.order.orderBasicInfo'),
				layout:{
					type:'table',
					columns:3
				},
				defaultType: 'textfield',
				defaults:{
					labelWidth:90,
					readOnly:true,
					width:210
				},
				items:[{
                    name : ORDERNAME.get('consignorName'),
                    fieldLabel: i18n('i18n.order.deliveryContactName')
                },{
                    name : ORDERNAME.get('consignorMobilePhone'),
                    fieldLabel: i18n('i18n.order.deliveryContactMobilePhone')
                },{
                    name : ORDERNAME.get('consignorPhone'),
                    fieldLabel: i18n('i18n.order.deliveryContactPhone')
                },{
                    id : 'allConsignorAddress',
                    colspan: 3,
                    width:630,
                    fieldLabel: i18n('i18n.order.receiveMoneyAddress')
                },{
                    xtype: 'hiddenfield',
                    id:'orderNumberId',
                    name: 'orderNumber'
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
 * 运单基本信息FORM<br/>
 * </p>
 * @author  张登
 * @时间    2012-03-13
 */
Ext.define('SendGoodForm', {
		extend:'TitleFormPanel',
		items:null,
		id : 'wayBillBisicInfo',
		/**.
		    * <p>
		    * 获取ITEMS属性
		    * </br>
		    * </p>
		    * @author  张登
		    * @时间    2012-03-13
		    */
		getItems:function(){
			return [{
			    xtype:'basicfiledset',
				title:i18n('i18n.order.wayBillBasicInfo'),
				layout:{
					type:'table',
					columns:3
				},
				defaultType: 'textfield',
				defaults:{
					labelWidth:90,
					readOnly:true,
					width:210
				},
				items:[{
                    name : 'shipper',//联系人
                    id:'orderShipper',
                    fieldLabel: i18n('i18n.order.deliveryContactName')
                },{
                    name : 'shipperPhone',//手机
                    fieldLabel: i18n('i18n.order.deliveryContactMobilePhone')
                },{
                    name : 'shipperTelephone',//电话
                    fieldLabel: i18n('i18n.order.deliveryContactPhone')
                },{
                	width:630,
                	fieldLabel:i18n('i18n.order.sendAddress'),
                	colspan: 3,
                	name: 'shipperAddress' //详细地址
                },{
                    xtype: 'hiddenfield',
                    id:'waybillNumberId',
                    name: 'waybillNumber'
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
 * 订运单关联页面</br>
 * </p>
 * @author  张登
 * @时间    2012-03-20
 */
Ext.onReady(function() {
    Ext.QuickTips.init();   
    Ext.create(
		'Ext.container.Viewport',{
			autoDestroy : true,
		items : [new OrderQueryForm(),new AssociateOrderWaybillButtonPanel(),new ReceiveGoodForm(),new SendGoodForm()]
	});
});
