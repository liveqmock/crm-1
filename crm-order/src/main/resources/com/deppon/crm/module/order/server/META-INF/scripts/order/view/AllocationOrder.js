/**
 * .
 * <p>
 * 将CommonOrderView中的元素组合成分配订单页面<br/>
 * 订单分配页面。
 * </p>
 * 
 * @author 张登
 * @时间 2012-03-08
 *     </p>
 */
// 设置Data层对象
var orderbaseDataControl =  (ORDERCONFIG.get("TEST"))? new OrderDataTest():new OrderData();
var IsSearch = false;
/**
 * .
 * <p>
 * 获取业务字典数据</br>
 * </p>
 * 
 * @author 张登
 * @时间 2012-03-13
 */
var param = {'dataDictionaryKeys':[
                                   // 签收单
	                                 'SIGNBILL',
	                               // 送货方式
	                                 'PICKGOODTYPE',
	                                 // 运输类型
	                                 'TRANS_TYPE',
	                                 // 付款方式
			                          'PAY_WAY',
										// 反馈信息
										'FEEDBACK_INFO',
										// 订单状态
										'ORDER_STATUS',
										// 订单来源
										'ORDER_SOURCE',
										// 操作类别
									    'ORDER_OPERATION',
		                                 //AL会员类型
		                                 'ALIBABA_MEMBERTYPE'
										]};

var successFn = function(json){
	 dataDictionary = json.dataDictionary;
	 /**
		 * .
		 * <p>
		 * 订单分配主页面</br>
		 * </p>
		 * 
		 * @author 张登
		 * @时间 2012-03-19
		 */
	 Ext.onReady(function() {
	    if(Ext.toolbar.Paging){
	        Ext.apply(Ext.PagingToolbar.prototype, {
	           beforePageText : "第",//update
	           afterPageText  : "页,共 {0} 页",//update
	           firstText      : "第一页",
	           prevText       : "上一页",//update
	           nextText       : "下一页",
	           lastText       : "最后页",
	           refreshText    : "刷新",
	           displayMsg     : "显示 {0} - {1}条，共 {2} 条",//update
	           emptyMsg       : '没有数据'
	        });
	     }
		 Ext.QuickTips.init();    
	 	 new AllocationOrder();
	 	 DButil.refreshGird();
	 	 initDeptAndUserInfo();
	 });
};
var failureFn = function(json){
	MessageUtil.showErrorMes(json.message);
};
DButil.getBusinessDictionary(param,successFn,failureFn);

//加载订单来源
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

/**
 * .
 * <p>
 * 受理分配页面</br>
 * </p>
 * 
 * @author 张登
 * @时间 2012-03-20
 */
Ext.define('AllocationOrder',{
 		extend:'Ext.container.Viewport',
 		autoDestroy : true,
 		autoScroll:true,
 		getItems:function(){
 			var me = this;
 			var field = ['code','codeDesc'];
 			// 创建gird的columns
 			var columns = [{
 		        xtype: 'rownumberer',
 		        width: 35,
 		        text     : i18n('i18n.order.serialNumber')
 		        //sortable: false
 		    },{
 		        text     : i18n('i18n.order.orderNum'),
 		        //sortable : false,
 		        width:108,
 		        dataIndex: ORDERNAME.get('orderNum')
 		    },{
 	            text     : i18n('i18n.order.orderResource'),
 	            //sortable : false,
 	           width:78,
 	            dataIndex: ORDERNAME.get('resource'),
 	           renderer : function(value){
 	        	   return OrderNumberRuleAction.renderResource(value);
	            }
 	        },{
 	            text     : i18n('i18n.order.pressOrderNum'),
 	            //sortable : true,
 	           width:65,
 	            dataIndex: ORDERNAME.get('hastenCount')
 	        },
 	        {
 	            text     : i18n('i18n.order.lastHastenTime'),
 	            //sortable : true,
 	            dataIndex: ORDERNAME.get('lastHastenTime'),
 	            renderer : function(value){
 	            	if(!Ext.isEmpty(value)){
 	            		return value.format('yyyy-MM-dd hh:mm');
 	            	}
	            }
 	        },{
 	            text     : i18n('i18n.order.storageAndTransportationMatters'),
 	            //sortable : true,
 	            dataIndex: ORDERNAME.get('storageAndTransportationMatters')
 	        },{
 	            text     : i18n('i18n.order.comeFromPoint'),
 	            //sortable : true,
 	           width:120,
 	            dataIndex: ORDERNAME.get('startStationName')
 	        },{
 	            text     : i18n('i18n.order.feedbackInfo'),
 	            width:120,
 	            //sortable : true,
 	            dataIndex: 'feedbackInfo',
	            renderer : function(value){
	            	return DButil.changeDictionaryCodeToDescrip(value,dataDictionary.FEEDBACK_INFO);
	            }
 	        },{
 	            text     : i18n('i18n.order.acceptDept'),
  	           width:100,
  	            //sortable : true,
  	            dataIndex: ORDERNAME.get('acceptDeptName')
  	        },{
 	            text     : i18n('i18n.order.orderStatus'),
 	            //sortable : true,
 	           width:65,
 	            dataIndex: ORDERNAME.get('orderStatus'),
 	            renderer : function(value){
 	            	return DButil.changeDictionaryCodeToDescrip(value,dataDictionary.ORDER_STATUS);
 	            }
 	        },
 	        {
 	            text     : i18n('i18n.order.deliveryContactName'),
 	            //sortable : true,
 	            dataIndex: ORDERNAME.get('consignorName')
 	        },
 	        {
 	            text     : i18n('i18n.order.deliveryContactMobilePhone'),
 	            //sortable : true,
 	            dataIndex: ORDERNAME.get('consignorMobilePhone')
 	        },{
 	            text     : i18n('i18n.order.goodsName'),
 	            //sortable : true,
 	            dataIndex: ORDERNAME.get('goodsName')
 	        },{
 	            text     : i18n('i18n.order.weight'),
 	            //sortable : true,
 	            dataIndex: ORDERNAME.get('goodsTotalWeight')
// 	            renderer : function(value){
//	            	return value+i18n('i18n.order.kg');
//	            }
 	        },{
 	            text     : i18n('i18n.order.totalVolume'),
 	            //sortable : true,
 	            dataIndex: ORDERNAME.get('goodsTotalVolume')
// 	            renderer : function(value){
//	            	return value+i18n('i18n.order.ccubicMetre');
//	            }
 	       }, {
	        	text     : i18n('i18n.order.memberType'),
//	        	sortable : true,
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
//	            	return value+i18n('i18n.order.parts');
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
 			/**			
			    * 修改人：张斌
				*修改时间：2013-7-27 11:20
				*原有内容：
				*return [new OrderQueryForm({'isInitPointToSearch':false,'storeId':'noAllocationOrderStore','defaultValue':['ALL','WAIT_ALLOT'],width:791,height:70}),
				*修改原因：修改form的高度为100，以适应新增查询条件
			*/
 			//begin
 			return [new OrderQueryForm({'isInitPointToSearch':false,'storeId':'noAllocationOrderStore','defaultValue':['ALL','WAIT_ALLOT'],width:791,height:100}),
 			//end
 			        new SearchOrderButtonPanel({width:791}),
			 		new OrderGrid({'store':orderbaseDataControl.getNoAllocationOrderStore(),'columns':columns,'storeId':'noAllocationOrderStore'}),
 					new AllocationBottomPanel()];
 		},
 	    initComponent:function(){
 	    	var me = this;
 	    	me.items = me.getItems();
 	    	Ext.getCmp('orderQuery_Form').getForm().findField(ORDERNAME.get('createUser')).hide();// 制单员
            Ext.getCmp('orderQuery_Form').getForm().findField(ORDERNAME.get('consignorComeFromPoint')).hide();// 始发网点
 	    	Ext.data.StoreManager.lookup(ORDERNAME.get('returnInfo')).add({'code':'KONG','codeDesc':i18n('i18n.order.blank')});
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
 	    	this.callParent();
 	    }
 	});




Ext.define('AllocationBottomPanel',{
	extend:'BasicPanel',
	layout:{
		type:'hbox'
	},
	width:'100%',
	heigth:400,
	initComponent:function(){
		var me=this;
		me.items=[
				new AllocationOrderTabPanel(),
				{width:5},new AllocationBtnPanel({margin:'14 0 0 0'})
				]
		this.callParent();
	}
});



Ext.define('AllocationBtnPanel',{
	extend:'OperBtnFormPanel',
	height:395,
	width:175,
	autoScroll:false,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me=this;
		var field = ['code','codeDesc'];
		return[{
			layout:{
				type:'table',
				columns:1
			},
			width:170,
			defaultType:'textfield',
			defaults:{
				width:150
			},
		     items: [{
			    	 xtype:'titledisplayfield',
					 style:'padding-top:0',
			    	 value:i18n('i18n.order.comeFromPoint')
		    	 },{
                        width:150,
			  			xtype: 'combo',
			  			labelSeparator:'',
			  			maxLength:50,
			  			ladStationDept:null,
			  			id:ORDERNAME.get('consignorComeFromPoint'),
			            name:ORDERNAME.get('consignorComeFromPoint'),
//			  	        fieldLabel: i18n('i18n.order.comeFromPoint'),
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
			  	        	minWidth :260,
			  	            getInnerTpl: function() {
			  	            	 return '{'+ORDERNAME.get('pointName')+'}';
			  	            }
			  	        },
			  	        pageSize: 10,
			  	        listeners:{
			  	        	change:function(comb){
			  	        		//comb.store.removeAll();
			  	        		comb.store.removeAll();
			  	        		if(Ext.isEmpty(comb.getValue())){
			  	        			comb.setValue("");
			  	        	    }
			  	        	},
			  	        	/**			
				  			    * 修改人：张斌
				  			*修改时间：2013-7-30 22:30
				  			*原有内容：无（新增）
				  			*修改原因：增加select时间，将选择的网点设置到属性中
				  		     */
			  			    //begin
			  	        	select:function(th,records){
			  	        		th.ladStationDept = records[0];
			  	        	},
			  	        	//end
		                  	expand:function(){
		                    	//消除火狐浏览器出险combox与弹出来的信息错位的BUG
		                    		document.body.scrollLeft=0;
		                			document.body.scrollTop=0;
		                			document.getElementsByTagName("html")[0].style.overflowY="hidden";
		                			document.getElementsByTagName("html")[0].style.overflowX="hidden";
		                			//viewport.doLayout();
		                  	}
			  	        }
		    		 
		    	   /*queryMode: 'local',
			        labelSeparator:'',
		        	xtype:'combo',
		        	forceSelection: true,
		        	store:DButil.getStore(ORDERNAME.get('consignorComeFromPoint'),'PointModel',null,[]),
			             id:ORDERNAME.get('consignorComeFromPoint'),
			             name:ORDERNAME.get('consignorComeFromPoint'),
			             displayField:ORDERNAME.get('pointName'),
			             valueField:ORDERNAME.get('id'),
			         listeners:{
			            	specialkey:function(th,e){
			            		var successFn = function(json){
				         		    Ext.data.StoreManager.lookup(ORDERNAME.get('consignorComeFromPoint')).removeAll();
				     			    Ext.data.StoreManager.lookup(ORDERNAME.get('consignorComeFromPoint')).add(json.orderSearchView.bussinessDepts);
				     			    Ext.getCmp(ORDERNAME.get('consignorComeFromPoint')).expand( );
				     		    };
				     		    var param  = {'orderSearchView':{'deptSearchCondition':{'deptName':e.target.value,'ifLeave':true}}};
			            		DButil.keyPress(e,ORDERNAME.get('consignorComeFromPoint'),orderbaseDataControl.getBussinessDepts,param,3,successFn);
			            	}
			        } */
		         },    
		         {xtype: 'titledisplayfield',style:'padding-top:0',value:i18n('i18n.order.selectFeedbackInfo')},
					{
// width:150,
		             xtype:          'combo',
		             mode:           'local',
		             triggerAction:  'all',
		             id:ORDERNAME.get('returnInfo'),
		             forceSelection: true,
		             editable:       false,
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
		         },
		        {
		        	 xtype:'titledisplayfield',
					 style:'padding-top:0',
		        	 value:i18n('i18n.order.userDefined')},
		        {
// width:160,
		             xtype: 'textfield',
		             disabled:true,
		             id:'otherReason'
		         },{
		        	 xtype:'basicvboxcenterpanel',
		        	 height:220,
		        	 autoScroll:false,
		        	 items:[
		        	        {
		   		             xtype:'button',
		   		             text: i18n('i18n.order.assignOrder'),//分配订单
		   		             handler:function(btn){
		   		             	var record = Ext.getCmp('orderGrid').getSelectionModel().getSelection();
		   		    			if(record.length==0){
		   		    				MessageUtil.showMessage(i18n('i18n.order.pleaseSelectOne'));
		   		    				return false;
		   		    			}
		   		    			if(Ext.isEmpty(Ext.getCmp(ORDERNAME.get('consignorComeFromPoint')).getRawValue())){
		   		    				MessageUtil.showMessage(i18n('i18n.order.consignorComeFromPoint'));
		   		    				return false;
		   		    			}
		   		    			var newAcceptId = Ext.getCmp(ORDERNAME.get('consignorComeFromPoint')).getValue();
		   		    			var newAcceptName = Ext.getCmp(ORDERNAME.get('consignorComeFromPoint')).getRawValue();
				  	        	/**			
					  			    * 修改人：张斌
					  			*修改时间：2013-7-30 22:30
					  			*原有内容：无（新增）
					  			*修改原因：获取分配网点的省市区县名称
					  		     */
				  			    //begin
		   		    			var contactProvince = null;
		   		    			var contactCity = null;
		   		    			var contactArea = null;
		   		    			if(!Ext.isEmpty(Ext.getCmp(ORDERNAME.get('consignorComeFromPoint')).ladStationDept)){
			   		    			 contactProvince = Ext.getCmp(ORDERNAME.get('consignorComeFromPoint')).ladStationDept.get('provinceName');
			   		    			 contactCity = Ext.getCmp(ORDERNAME.get('consignorComeFromPoint')).ladStationDept.get('cityName');
			   		    			 contactArea = Ext.getCmp(ORDERNAME.get('consignorComeFromPoint')).ladStationDept.get('regionName');
		   		    			}
		   		    			//end
		   		    			var successFn = function(json){
		   		    				btn.setDisabled(false);
		   		    				MessageUtil.showInfoMes(i18n('i18n.order.assignOrderSuccess'));
		   		            		Ext.getCmp('orderGrid').getStore().load();
		   		            		if(!Ext.isEmpty(Ext.getCmp('assignOrderMessageWindow'))){
		   		            			Ext.getCmp('assignOrderMessageWindow').hide();
		   		            		}
		   		    			};
		   		    			var failureFn = function(json){
		   		    				btn.setDisabled(false);
		   		    				MessageUtil.showErrorMes(json.message);
		   		    			};
		   		    			var orderIds=new Array();
		   						for(var i=0;i<record.length;i++){
		   							orderIds[i]=record[i].data.id;
		   						}	
				  	        	/**			
					  			    * 修改人：张斌
					  			*修改时间：2013-7-30 22:30
					  			*原有内容：var param = {'orderView':{orderIds:orderIds,'order':{'startStation':newAcceptId
		   		    				,'startStationName':newAcceptNam}}};
					  			*修改原因：将省市区县设置到传给后台的参数中
					  		     */
		   						//begin
		   		    			var param = {'orderView':{orderIds:orderIds,'order':{'startStation':newAcceptId
		   		    				,'startStationName':newAcceptName,'contactProvince':contactProvince,'contactCity':contactCity,'contactArea':contactArea}}};
		   		    			//end
		   		    			var message = '';
		   		    			for(var i=0;i<record.length;i++){
		   		    				var oldAcceptId = record[i].get(ORDERNAME.get('consignorComeFromPoint'));
		   		    				var oldAcceptName = record[i].get(ORDERNAME.get('startStationName'));
		   		    				var orderNumber = record[i].get(ORDERNAME.get('orderNum'));
		   		    				if(!Ext.isEmpty(oldAcceptId)){
		   		    					if(oldAcceptId!=newAcceptId){
		   		    						if(!Ext.isEmpty(oldAcceptName)){
		   		    							message = message+orderNumber+':'+i18n('i18n.order.insureTodo')+oldAcceptName+i18n('i18n.order.acceptPointUpdate')+newAcceptName+i18n('i18n.order.acceptPoint')+'\n';
		   		    						}
		   		    					}
		   		    				}
		   		    			}
		   		    			if(!Ext.isEmpty(message)){//@Update
		   		    				if(Ext.isEmpty(Ext.getCmp('assignOrderMessageWindow'))){
		   		    					new AssignOrderMessageWindow({'param':param,'successFn':successFn,'failureFn':failureFn}).show();
		   		    				}else{
		   		    					Ext.getCmp('assignOrderMessageWindow').show();
		   		    					Ext.getCmp('assignOrderMessageWindow').param = param;
		   		    					Ext.getCmp('assignOrderMessageWindow').successFn = successFn;
		   		    					Ext.getCmp('assignOrderMessageWindow').failureFn = failureFn;
		   		    				}
		   		    				Ext.getCmp('assignOrderMessageArea').setValue(message);
		   		    			}else{
		   		    				btn.setDisabled(true);
		   		    				orderbaseDataControl.assignOrder(param,successFn,failureFn);
		   		    			}
		   		    		}
		   		    			
		   		         },{
		 	                    xtype:'button',
		 	                    hidden:!isPermission('/order/updateOrderCallCenter.action'),
		 	                    text: i18n('i18n.order.updateOrder'),//修改订单
		 	                    handler:function(btn){
		 	                    	if(Ext.getCmp('orderGrid').getSelectionModel( ).getSelection().length==0){
		 	            				MessageUtil.showMessage(i18n('i18n.order.pleaseSelectOne'));
		 	            				return;
		 	            			}
		 	            			var orderSource = Ext.getCmp('orderGrid').getSelectionModel( ).getSelection()[0].get(ORDERNAME.get('resource'));
		 	            			//只能呼叫中心/呼叫中心合肥的订单
		 	            			//修改人：张斌    修改时间2103-11-11
		 	            			if(orderSource!='CALLCENTER'&&orderSource!='CALLCENTER-HF'){
		 	            				MessageUtil.showMessage(i18n('il8n.order.onlyUpdateOrderCallCenter'));
		 	            				return;
		 	            			}
		 	            			var successFn = function(json){
		 	            				btn.setDisabled(false);
		 	            				var orderModel = new OrderModel(json.orderView.order);
		 	            				//如果到达网点是偏线， 则运输方式只为精准空运和汽运偏线
			 	            	        if(!Ext.isEmpty(json.orderView.endDept)){
		 	            					var dept = new PointModel(json.orderView.endDept);
		 	            					Ext.getCmp(ORDERNAME.get('receivingToPoint')).getStore().add(dept);
		 	            	 				Ext.getCmp(ORDERNAME.get('receivingToPoint')).setValue(json.orderView.endDept.id);
		 	            	 				/*if(dept.get(ORDERNAME.get('ifOutward'))){ifOutward这个字段是营业部信息中的是派送
		 	            	 					Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().removeAll();
		 	            	 					Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().add({'code':'JZKY','codeDesc':i18n('i18n.order.playTranceJZ')});
		 	            	 					Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().add({'code':'AGENT_VEHICLE','codeDesc':i18n('i18n.order.carTrance')});
		 	            	 					Ext.getCmp(ORDERNAME.get('modeOfTransportation')).setValue('');
		 	            	 				}else{
		 	            	 					Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().removeAll();
		 	            	 					Ext.getCmp(ORDERNAME.get('modeOfTransportation')).getStore().add(dataDictionary.TRANS_TYPE);
		 	            	 				}*/
		 	            				}
		 	            				var beginDeptIsHashGoodsType = null;
		 	            				if(!Ext.isEmpty(json.orderView.beginDept)){
		 	            					beginDeptIsHashGoodsType = json.orderView.beginDept.isHashGoodsType;
		 	            				}/*else{
		 	            					MessageUtil.showErrorMes(i18n('i18n.order.noHaveBegionDept'));
		 	            					return ;
		 	            				}*/
		 	            				var updateOrderWindow = Ext.getCmp('updateOrderWindow');
		 	            				if (Ext.isEmpty(updateOrderWindow)) {
		 	            					new UpdateOrderWindow({'data':orderModel,'beginDeptIsHashGoodsType':beginDeptIsHashGoodsType}).show();
			 	           				}else{
			 	           					updateOrderWindow.show();
			 	           				}
		 	            				//设置普通元素的初始值
		 	            	 			Ext.getCmp('transportationAndService_form').getForm().loadRecord(orderModel);
		 	            	 			Ext.getCmp('goodsInfo_form').getForm().loadRecord(orderModel);
		 	            	 			Ext.getCmp('consignor_form').getForm().loadRecord(orderModel);
		 	            	 			Ext.getCmp('receiving_form').getForm().loadRecord(orderModel);
		 	            	 			Ext.getCmp('basicInfo_form_callcar').getForm().loadRecord(orderModel);
		 	            	 			var recevingOfferTel = orderModel.get(ORDERNAME.get('receivingPhone'));
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
		 	            					consignorCounty = orderModel.get(ORDERNAME.get('consignorCounty'));
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
		 	            					receivingCounty = orderModel.get(ORDERNAME.get('receivingCounty'));
		 	            				}
		 	            				var receivingAreas = receivingProvince+receivingCity+receivingCounty;
		 	            				/*var receivingAreas = orderModel.get(ORDERNAME.get('receivingProvince'))+'-'+
		 	            				orderModel.get(ORDERNAME.get('receivingCity'))+'-'+orderModel.get(ORDERNAME.get('receivingCounty'));*/
		 	            				Ext.getCmp('receivingAreaSelect').setRawValue(receivingAreas);
		 	            			};
		 	            			var failureFn = function(json){
		 	            				btn.setDisabled(false);
		 	            				MessageUtil.showErrorMes(json.message);
		 	            			};
		 	            			var orderId = Ext.getCmp('orderGrid').getSelectionModel( ).getSelection()[0].data.id;
		 	            			var param = {'orderView':{'orderId':orderId}};
		 	            			btn.setDisabled(true);
		 	            			orderbaseDataControl.isUpdateOrderable(param,successFn,failureFn);          	  	
		 	                    }
		 	                },{
		   		             xtype:'button',
		   		             text: i18n('i18n.order.queryMap'),//查看地图
		   		             handler:function(){
				            		var record = Ext.getCmp('orderGrid').getSelectionModel().getSelection();
			   		    			if(record.length!=1){
			   		    				MessageUtil.showMessage(i18n('i18n.order.pleaseSelectOne'));
			   		    				return false;
			   		    			}
				            	 var consignorProvince = Ext.isEmpty(record[0].get(ORDERNAME.get('consignorProvince')))?'':record[0].get(ORDERNAME.get('consignorProvince'));
				            	 var consignorCity = Ext.isEmpty(record[0].get(ORDERNAME.get('consignorCity')))?'':record[0].get(ORDERNAME.get('consignorCity'));
				            	 var consignorCounty = Ext.isEmpty(record[0].get(ORDERNAME.get('consignorCounty')))?'':record[0].get(ORDERNAME.get('consignorCounty'));
				            	 var consignorAddress = Ext.isEmpty(record[0].get(ORDERNAME.get('consignorAddress')))?'':record[0].get(ORDERNAME.get('consignorAddress'));
			   		    			
				            	 
				            	 var successFn = function(json){
									if(!Ext.isEmpty(json.orderView.beginDept)){
											Ext.data.StoreManager.lookup(ORDERNAME.get('consignorComeFromPoint'))
						    				.add({'id':json.orderView.beginDept.id,'deptName':json.orderView.beginDept.deptName});
	        		       					Ext.getCmp(ORDERNAME.get('consignorComeFromPoint')).setValue(json.orderView.beginDept.id);
									}
								} 
			        	        CommonOrderView.mapShow({
				            		 'proCityArea':consignorProvince +'-'+ consignorCity +'-'+ consignorCounty,
				            		 'otherAddress':consignorAddress,
				            		 'callBack':function(dept){
										OrderData.prototype.changePointStandardcode({'orderView':{'standardCode':dept['deptNo']}},successFn,function(json){});
		        	    				Ext.data.StoreManager.lookup(ORDERNAME.get('consignorComeFromPoint')).removeAll();
				            		 }
				            	 },'leave');
		   		             }
		   		         },
		   		         {
		   		             xtype:'button',
		   		             text: i18n('i18n.order.refuseOrder'),//拒绝订单
		   		             handler:function(btn){
		   		             	DButil.commonOperator(orderbaseDataControl.refuseOrder,true,true,btn);           
		   		             }
		   		         },{
		 	                    xtype:'button',
		 	                    text: i18n('i18n.order.failOrder'),//揽货失败
		 	                    handler:function(btn){
		 	                    	DButil.commonOperator(orderbaseDataControl.failOrder,true,true,btn);          	
		 	                    }
		 	            },{
		   		             xtype:'button',
		   		             text: i18n('i18n.order.cancelOrder'),
		   		             handler:function(btn){
		   		            	 //判断是否选择了数据
		 	                    	var selection = Ext.getCmp('orderGrid').getSelectionModel( ).getSelection()[0];
		 	                    	if(Ext.getCmp('orderGrid').getSelectionModel( ).getSelection().length!=1){
		 	                        	MessageUtil.showMessage(i18n('i18n.order.pleaseSelectOne'));//请选择一条记录进行操作！
		 	                    		return;
		 	                    	}
		   		             	// 只能撤销营业部和呼叫中心下的单
		   		             	var orderSource = Ext.getCmp('orderGrid').getSelectionModel( ).getSelection()[0].get(ORDERNAME.get('resource'));
		   		           		if (!Ext.Array.contains(User.roleids,'4008302')) {
		   		           	        //增加呼叫中心合肥
		 	            			//修改人：张斌    修改时间2103-11-11
		   		             		if(orderSource!='CALLCENTER'&&orderSource!='BUSINESS_DEPT'&&orderSource!='CALLCENTER-HF'){
		   		             		MessageUtil.showMessage(i18n('il8n.order.onlyCancelOrder'));
		   		     					return;
		   		     				}
		   		             	}
		   		           		if(Ext.Array.contains(User.roleids,'4008302')){
		   		           		if(orderSource!='CALLCENTER'&&orderSource!='CALLCENTER-HF'){
		   		             		MessageUtil.showMessage(i18n('il8n.order.onlyCancelCallCenterOrder'));
		   		     					return;
		   		     				}
		   		           		}
		   		             	DButil.commonOperator(orderbaseDataControl.cancelOrder,true,true,btn);          	
		   		             }
		   		         },
		   		         {
		   		             xtype:'button',
		   		             text: i18n('i18n.order.pressOrder'),//催促办理
		   		             handler:function(btn){
		   		             	DButil.commonOperator(orderbaseDataControl.pressOrder,false,true,btn);   	
		   		             }
		   		         }]
		         }
		     
		 	]
		}]
	}
});
/**.
 * <p>
 * 分配提示信息{AssignOrderMessageWindow}</br>
 * </p>
 * @author  张斌
 * @时间    2012-07-15
 */
Ext.define('AssignOrderMessageWindow',{
    extend:'PopWindow', 
    //title: i18n('i18n.order.updateCallCar'),
    height: 250,
	width: 700,
	resizable:false,
    autoScroll:true,
    modal:true,
    data:null,
    closeAction:'hide',
    id:'assignOrderMessageWindow',
    getItems:function(){
    	return [{
    		xtype     : 'textareafield',
    		id        : 'assignOrderMessageArea',
            autoScroll: true,
            width:650,
            height:150
    	}];
    },
    getFbar:function(){
    	return [{
			xtype:'button',
		    text: i18n('i18n.SearchMember.determine'),
		    id:'assignOrderYes',
		    handler: function(){
		    	var param = Ext.getCmp('assignOrderMessageWindow').param;
		    	var successFn = Ext.getCmp('assignOrderMessageWindow').successFn;
		    	var failureFn = Ext.getCmp('assignOrderMessageWindow').failureFn;
		    	orderbaseDataControl.assignOrder(param,successFn,failureFn);
			}
		},{
			xtype:'button',
			id:'assignOrderNo',
		    text: i18n('i18n.order.cancel'),
		    handler: function() {
		    	Ext.getCmp('assignOrderMessageWindow').hide();
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
             Ext.getCmp('assignOrderMessageArea').reset();		
    	}
    }
});	
	
Ext.define('AllocationOrderTabPanel',{
	   extend:'NormalTabPanel',
	   flex:1,
	   height:447,
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

Ext.define('OrderInfoTabPanel',{
	extend:'TabContentPanel',
	title : i18n('i18n.order.orderInfo'),
	items:null,
	flex:1,
	layout:{
		type:'vbox',
		align:'stretch'
	},
	getItems:function(){
		return [new SendGoodForm(),new jg(), new ReceiveGoodForm(),new jg(),new BasicInfoForm()];
	},
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
	}
});
Ext.define('jg',{
	extend:'Ext.panel.Panel',
	padding:'5 0 0 0'
});
Ext.define('OperationInfoTabPanel',{
	extend:'TabContentPanel',
	flex:1,
	layout:'fit',
	title :i18n('i18n.order.operationLog'),
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

