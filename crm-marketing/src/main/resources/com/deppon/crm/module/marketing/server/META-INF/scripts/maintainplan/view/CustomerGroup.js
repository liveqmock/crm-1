var maintainpData =  new MaintainpData();//初始化计划新增修改data
var customerDevelopPlane =  new CustomerDevelopPlaneData();//初始化计划新增修改data
/**
 * 客户分组管理
 */
Ext.onReady(function(){	
	var addCustomerAry = new Ext.util.MixedCollection();
	var removeCustomerAry = new Ext.util.MixedCollection();
	var updateCustomerAry = new Ext.util.MixedCollection();
	var initStoreAry = new Ext.util.MixedCollection();
	var customerGroupId;
	initDeptAndUserInfo();
	/**
	 * 上面的按钮
	 */
	Ext.define('CustomerGroupButtonPanel',{
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
				width:380,
				items:[{xtype:'button',text:i18n('i18n.DevelopManageView.find'),
					handler:function(){
						var selection=Ext.getCmp("customerGroupGridId").getSelectionModel().getSelection();
						//判断是否选中行
						//判断是否选中行
						if (selection.length == 0) {
							MessageUtil.showMessage(i18n('i18n.developPlan.choice'));
							return false;
						}
						if (selection.length != 1) {
							MessageUtil.showMessage(i18n('i18n.developPlan.chooseOne'));
							return false;
						}
						customerGroupId=selection[0].data.id;						
						Ext.getCmp("DetailGridId").setTitle(selection[0].data.groupName+" 的客户信息如下");
						detailWin.show();
  						var params = {
  							'condition.queryDate' : new Date(),
  							'condition.fcAnalyseType' : "true"
  						};
  						Ext.getCmp("DetailGridId").store.load();
  						var successFn = function(json){
  							//获取数据
  							var detailGrid = Ext.getCmp("DetailGridId");
  							var detailStore = detailGrid.store;
  							//显示数据
  							detailGrid.reconfigure(detailStore,json.columModel);
  							detailGrid.render();  
  						};
  						var failureFn = function(json){
  							MessageUtil.showErrorMes(json.message);
  						};
  						//获取列头
  						maintainpData.queryCycleListHeader(params,successFn,failureFn);
					}},{
					text:i18n('i18n.developPlan.add'),
					xtype:'button',
					handler:function(){
						Ext.MessageBox.prompt(i18n('i18n.customerGroup.addGroup'),i18n('i18n.customerGroup.insertGroupName'), function(e,text){
							if(e == 'ok'){
								if(!Ext.isEmpty(text)){
									if(text.length>20){
										MessageUtil.showMessage("组名不能长度不能大于20个字符~");
										return false;
									}
									var successFn = function(json){
										Ext.getCmp("customerGroupGridId").store.load();
										Ext.getCmp("formGroupId").store.load();
//										Ext.getCmp("CustomerGroupFormPanelId").getForm().findField('empName').store.load();
										MessageUtil.showInfoMes(i18n('i18n.customerGroup.saveSuccess'));
									};
									var failureFn = function(json){
										MessageUtil.showErrorMes(json.message);
									};
									var param={'customerGroup.groupName':text};
									maintainpData.saveCustomerGroup(param, successFn, failureFn);
			            		}else{
			            			MessageUtil.showMessage(i18n('i18n.customerGroup.insertName'));
			            		}
							}
		            	});
					}
				},{
					text:i18n('i18n.developPlan.update'),
					xtype:'button',
					handler:function(){
						var selection=Ext.getCmp("customerGroupGridId").getSelectionModel().getSelection();
						//判断是否选中行
						if (selection.length != 1) {
							MessageUtil.showMessage(i18n('i18n.customerGroup.mustChoiceOne'));
							return false;
						}
						Ext.MessageBox.prompt(i18n('i18n.customerGroup.updateGroupName'), i18n('i18n.customerGroup.insertGroupName'), function(e,text){
							if(e == 'ok'){
								if(!Ext.isEmpty(text)){
									if(text.length>20){
										MessageUtil.showInfoMes("组名不能长度不能大于20个字符！");
										return false;
									}
									var successFn = function(json){
										Ext.getCmp("customerGroupGridId").store.load();
										Ext.getCmp("formGroupId").store.load();
//										Ext.getCmp("CustomerGroupFormPanelId").getForm().findField('empName').store.load();
										MessageUtil.showInfoMes(i18n('i18n.developSchedule.updateSuccess'));
									};
									var failureFn = function(json){
										MessageUtil.showErrorMes(json.message);
									};
									var param={'customerGroup.groupName':text,'customerGroup.id':selection[0].data.id};
									maintainpData.updateCustomerGroup(param, successFn, failureFn);
			            		}else{
			            			MessageUtil.showInfoMes(i18n('i18n.customerGroup.insertName'));
			            		}
							}
		            	});
					}
				},{
					text:i18n('i18n.developPlan.delete'),
					xtype:'button',
					handler:function(){
						var selection=Ext.getCmp("customerGroupGridId").getSelectionModel().getSelection();
						//判断是否选中行
						if (selection.length != 1) {
							MessageUtil.showMessage(i18n('i18n.customerGroup.mustChoiceOne'));
							return false;
						}
						//删除弹出提示，是否删除
						MessageUtil.showQuestionMes(i18n('i18n.developPlan.isdelete'), function(e){	
							if (e == 'yes') {
								//保存成功回调函数
								var delSuccessFn = function(result){
									var store=Ext.getCmp("customerGroupGridId").store;
									//删除grid中数据
									store.remove(selection[0]);
									MessageUtil.showInfoMes(i18n('i18n.developPlan.deleteSuccess'));
								};
								//保存失败回调函数
								var delFailFn = function(result){
									MessageUtil.showErrorMes(result.message);
								};
								var params={'customerGroupId':selection[0].get("id")};
								maintainpData.deleteCustomerGroup(params,delSuccessFn,delFailFn);
							}
						});
					}
				},{
					text:i18n('i18n.customerGroup.manage'),
					xtype:'button',
					handler:function(){
						var selection=Ext.getCmp("customerGroupGridId").getSelectionModel().getSelection();
						//判断是否选中行
						if (selection.length != 1) {
							MessageUtil.showMessage(i18n('i18n.customerGroup.mustChoiceOne'));
							return false;
						}
						var form=Ext.getCmp("CustomerGroupFormPanelId").getForm();
  						form.findField('groupId').setValue("");
  						form.findField('empName').setValue("");
  						form.findField('custName').setValue("");
  						form.findField('custNumber').setValue("");
						customerGroupId=selection[0].get("id");
						Ext.getCmp("groupName").setValue(selection[0].get("groupName"));
						win.show();
						document.getElementsByTagName("html")[0].style.overflowY="auto";
						document.getElementsByTagName("html")[0].style.overflowX="auto";
						viewport.doLayout();
						
						var customerInfoFormPanel =  Ext.getCmp('CustomerGroupFormPanelId');
  						var params = {
  							'condition.queryDate' : customerInfoFormPanel.getForm().findField('queryDate').getValue()
  						};
//  						Ext.getCmp("cycleListGridId").store.loadPage(1);
  						if(Ext.getCmp("cycleListGridId").store.getCount()>0){
  							Ext.getCmp("cycleListGridId").store.removeAll();
  						}
  						initStoreAry.clear();
  						Ext.getCmp("chooseCycleListGridId").store.load();
  						var successFn = function(json){
  							var cycleListGrid=Ext.getCmp("cycleListGridId");
  							var cycleStore=cycleListGrid.store;
  							cycleListGrid.reconfigure(cycleStore,json.columModel);
  							cycleListGrid.render(); 
  							
  							//获取数据
  							var chooseCycleListGrid = Ext.getCmp("chooseCycleListGridId");
  							var chooseCycleStore = chooseCycleListGrid.store;
//  							chooseCycleStore.load();
  							//显示数据
  							chooseCycleListGrid.reconfigure(chooseCycleStore,json.columModel);
  							chooseCycleListGrid.render();  
  							
  							
  						};
  						var failureFn = function(json){
  							MessageUtil.showErrorMes(json.message);
  						};
  						//获取列头
  						maintainpData.queryCycleListHeader(params,successFn,failureFn);
					}
				}]
			},{
				xtype:'middlebuttonpanel' 
			}]
		}
	});
	
	/**
	 *定义整个界面布局 
	 */
	Ext.define('CustomerGroupPanel',{
		extend:'BasicPanel',
		layout:'border',
		getColumns:function(){
		return [{
			xtype:'rownumberer',
			width:40,
			align:'center',
			header:i18n('i18n.Cycle.rownum')
		},{
				header:i18n('i18n.customerGroup.groupName'),
				dataIndex : 'groupName',
				width:400,
				align : 'left',
				sortable : true
			},{
				header:i18n('i18n.customerGroup.peopleCount'),
				dataIndex : 'count',
				width:120,
				align : 'left',
				sortable : true
			}];
		},
		initComponent:function(){
			var me = this;
			var customerGroupStore =  Ext.create('CustomerGroupStore',{'id':'CustomerGroupStoreId'});
			customerGroupStore.load();
			var selModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE',id:'selModelId'});
			me.items =[{
				xtype:'basicpanel',
				region:'north',
				height:36,
				items:[Ext.create('CustomerGroupButtonPanel')]
			},{
				xtype:'basicpanel',
				region:'center',
				layout:'fit',
				items:[
				    Ext.create('SearchGridPanel',{
				    	id:'customerGroupGridId',
				    	store:customerGroupStore,
				    	columns:me.getColumns(),
				    	selModel:selModel,
				    	viewConfig:{
		  					forceFit:true
		  				},listeners:{
		  					itemdblclick:function(th,record,item,index,e,eOpts){
								customerGroupId=record.get("id");
//								Ext.getCmp("groupName").setValue(selection[0].get("groupName"));
								
								detailWin.show();
		  						var params = {
		  							'condition.queryDate' : new Date(),
		  							'condition.fcAnalyseType' : "true"
		  						};
		  						Ext.getCmp("DetailGridId").store.load();
		  						var successFn = function(json){
		  							//获取数据
		  							var detailGrid = Ext.getCmp("DetailGridId");
		  							var detailStore = detailGrid.store;
		  							//显示数据
		  							detailGrid.reconfigure(detailStore,json.columModel);
		  							detailGrid.render();  
		  							Ext.getCmp("DetailGridId").setTitle(record.get("groupName")+" 的客户信息如下");
		  						};
		  						var failureFn = function(json){
		  							MessageUtil.showErrorMes(json.message);
		  						};
		  						//获取列头
		  						maintainpData.queryCycleListHeader(params,successFn,failureFn);
		  					}
		  				}
					})
				]
			}];
			this.callParent();
		}
	});
	
	/**
  	 * 查询按钮
  	 */
  	Ext.define('RightSearchButtonPanel',{
  		extend:'NormalButtonPanel', 
  		items:null,
  		region:'south',
  		weight:400,
  		initComponent:function(){
  			this.items = this.getItems();
  			this.callParent();
  		},
  		getItems:function(){
  			var me = this;
  			return [{
  				xtype:'middlebuttonpanel' 
  			},{
  				xtype:'rightbuttonpanel',  
  				items : [{
  					xtype : 'button',
  					text : i18n('i18n.PotentialCustManagerView.search'),
  					width : 70,
  					handler : function(){
  						Ext.getCmp("cycleListGridId").store.loadPage(1);
  					}
  				},{
  					xtype : 'button',
  					text : i18n('i18n.developPlan.reset'),
  					width : 70,
  					handler : function(){
  						var form=Ext.getCmp("CustomerGroupFormPanelId").getForm();
  						form.findField('groupId').setValue("");
  						form.findField('empName').setValue("");
  						form.findField('custName').setValue("");
  						form.findField('custNumber').setValue("");
  					}
  				}]
  			}];
  		}
  	});
	  	
  	/**
  	 * 制定开发计划按钮
  	 */
  	Ext.define('RightDownButtonPanel',{
  		extend:'BasicHboxFormPanel', 
  		items:null,
  		defaults : {
			margins : '10 0 0 0'
		},
  		initComponent:function(){
  			this.items = this.getItems();
  			this.callParent();
  		},
  		getItems:function(){
  			var me = this;
  			return [{
  				xtype:'basicformpanel',  
				layout:{
					type:'table',
					labelAlign:'left',
					align:'left',
					columns:6
				},
				defaultType:'textfield',
				defaults:{
					labelWidth:70,
					width:220
				},
				items:[{
					fieldLabel:i18n('i18n.customerGroup.group'),
//					value:i18n('i18n.CustomerGroup.testAAA'),
					id:'groupName',
					disabled:true,
					labelWidth:60
				},{
					fieldLabel:i18n('i18n.customerGroup.choiceCount'),
					width:90,
					disabled:true,
					id:'chooseCount',
					labelWidth:60
				},{
					fieldLabel:i18n('i18n.customerGroup.canChoiceCount'),
					width:90,
					id:'mayCount',
					disabled:true,
					labelWidth:60
				},{
	            	width:220,
	                xtype:          'combo',
	                mode:           'local',
	                triggerAction:  'all',
	                fieldLabel: i18n('i18n.customerGroup.userId'),
	                name: 'userId',
	                id:'fUserId',
					displayField:'empName',
					valueField:'id',
	                queryMode: 'local', 
					forceSelection :true,
					listeners:{
						change:DButil.comboSelsct
					},
	                store:Ext.create('UserStore',{}),
	                listeners:{
	                	change:function(t){
	                		if(Ext.isEmpty(t.getValue())){
	                			t.setValue("");
	                		}
	                	}
	                }
	            },{
					labelWidth:60,
					xtype:'displayfield',
					width:10
				}]
			},{
				xtype:'btnpanel',  
				items:[{
					xtype:'button',
					text:i18n('i18n.returnVisit.save'),
  					handler:function(){
  						if(Ext.isEmpty(Ext.getCmp("fUserId").getRawValue())){
  							MessageUtil.showMessage(i18n('i18n.customerGroup.mustChoiceUser'));
  							return false;
  						}
  						
						var store=Ext.getCmp("chooseCycleListGridId").store;
						if(store.getCount()>50){
							MessageUtil.showErrorMes(i18n('i18n.customerGroup.choiceCountLess50'));
  							return false;
						}
						var userId=Ext.getCmp("fUserId").getValue();
						var updatedList = new Array();
						store.each(function(record){
							if(!Ext.isEmpty(record.get("custId"))){
								if(Ext.isEmpty(initStoreAry.getByKey(record.get("custId")))){//如果当前的id存于初始化数据中
									addCustomerAry.add(record.get("custId"),record.data);
								}else{
									var record1=new OperatorCustomerModel();
		  							record1.set("deptId",record.get("deptId"));
		  							record1.set("userId",userId);
		  							record1.set("custId",record.get("custId"));
		  							updatedList.push(record1.data);
								}
							}
						});
						
  					    //客户意见
  					    var createdList = new Array();
  						var deletedList = new Array();
  						
  						
  						for(var i=0;i<addCustomerAry.getCount();i++){
  							var record=new OperatorCustomerModel();
  							record.set("deptId",addCustomerAry.getAt(i).deptId);
  							record.set("userId",userId);
  							record.set("custId",addCustomerAry.getAt(i).custId);
  							createdList.push(record.data);
  						}
  						for(var i=0;i<removeCustomerAry.getCount();i++){
  							var record=new OperatorCustomerModel();
  							record.set("deptId",removeCustomerAry.getAt(i).deptId);
  							record.set("userId",userId);
  							record.set("custId",removeCustomerAry.getAt(i).custId);
  							deletedList.push(record.data);
  						}
//  						for(var i=0;i<updateCustomerAry.getCount();i++){
//  							var record=new OperatorCustomerModel();
//  							record.set("deptId",updateCustomerAry.getAt(i).deptId);
//  							record.set("userId",userId);
//  							record.set("custId",updateCustomerAry.getAt(i).custId);
//  							updatedList.push(record.data);
//  						}
  						var params = {
  							'createdListDetail' : createdList,
  							'deletedListDetail' : deletedList,
  							'updatedListDetail' : updatedList,
  							'customerGroupId':customerGroupId
  						};
  						var successFn = function(json){
  							addCustomerAry.clear();
  	  						removeCustomerAry.clear();
  	  						updateCustomerAry.clear();
  	  						initStoreAry.clear();
	  	  					var customerInfoFormPanel =  Ext.getCmp('CustomerGroupFormPanelId');
	  						var params1 = {
	  							'condition.queryDate' : customerInfoFormPanel.getForm().findField('queryDate').getValue()
	  						};
	  						
	  						var successFn1 = function(json){
//	  							var cycleListGrid=Ext.getCmp("cycleListGridId");
//	  							var cycleStore=cycleListGrid.store;
//	  							cycleListGrid.reconfigure(cycleStore,json.columModel);
//	  							cycleListGrid.render(); 
//	  							Ext.getCmp("cycleListGridId").store.loadPage(1);
	  							MessageUtil.showInfoMes("保存成功！"); 							
	  							//获取数据
	  							var chooseCycleListGrid = Ext.getCmp("chooseCycleListGridId");
	  							var chooseCycleStore = chooseCycleListGrid.store;
	  							//显示数据
//	  							chooseCycleListGrid.reconfigure(chooseCycleStore,json.columModel);
//	  							chooseCycleListGrid.render();
	  							Ext.getCmp("CustomerGroupFormPanelId").getForm().findField('empName').store.load();
	  							chooseCycleStore.load();
	  							
	  						};
	  						var failureFn1 = function(json){
	  							MessageUtil.showErrorMes(json.message);
	  						};
	  						//获取列头
	  						maintainpData.queryCycleListHeader(params1,successFn1,failureFn1);
  	  						
  							MessageUtil.showInfoMes(i18n('i18n.PotentialCustEditView.saveSuccess'));
  						};
  						var failureFn = function(json){
  							MessageUtil.showErrorMes(json.message);
  						};
  						//保存
  						maintainpData.saveAllCustomerGroup(params,successFn,failureFn);
  					}
				},{
					xtype:'button',
					text:i18n('i18n.DevelopManageView.close'),
					handler:function(){
						win.close();
					}
				}]
			}]
  		}
  	});
	
  //操作按钮面板
  	Ext.define('ButtonDemoPanel',{
  		extend:'NormalButtonPanel', 
  		items:null,
  		region:'center',
  		initComponent:function(){
  			this.items = this.getItems();
  			this.callParent();
  		},
  		getItems:function(){
  			var me = this;
  			return [{
  				xtype:'leftbuttonpanel', 
  				width:140,
  				items:[{
  					xtype:'button',   
  					text:i18n('i18n.customerGroup.moveToAbove'),
  					handler:function(){
  						//得到已选客户
  	  					var selection=Ext.getCmp("chooseCycleListGridId").getSelectionModel().getSelection();
  	  					if(selection.length==0){
  	  						MessageUtil.showInfoMes("请先选择再进行操作!");
  	  						return false;
  	  					}
  	  					//待选择客户store
  	  					var store=Ext.getCmp("cycleListGridId").store;
  	  					for(var j=0;j<selection.length;j++){//遍历所选客户
		    				Ext.getCmp("chooseCycleListGridId").store.remove(selection[j]);
		    				if(Ext.isEmpty(store.findRecord("custId",selection[j].get("custId")))){//判断是否有重复
		    					//添加到已选择客户store里
		  	  					store.insert(j,selection[j]);
		    				}
  	  					}
  	  					setCount(Ext.getCmp("chooseCycleListGridId").store.getCount());
  					}
  				}]
  			},{
  				xtype:'middlebuttonpanel'
  			},{
  				xtype:'rightbuttonpanel',
  				width:140,
  				items:[{
  					xtype:'button',    
  					text:i18n('i18n.customerGroup.moveToBelow'),
  					handler:function(){
  						//得到所选客户
  	  					var selection=Ext.getCmp("cycleListGridId").getSelectionModel().getSelection();
  	  					if(selection.length==0){
	  						MessageUtil.showInfoMes("请先选择再进行操作!");
	  						return false;
	  					}
  	  					//已选择客户store
  	  					var chooseStore=Ext.getCmp("chooseCycleListGridId").store;
  	  					for(var i=0;i<selection.length;i++){//遍历所选客户
	  	    		    	if(!Ext.isEmpty(chooseStore.findRecord("custId",selection[i].get("custId")))){//判断是否有重复
	  	    		    		MessageUtil.showMessage(i18n('i18n.customerGroup.custName')+':'+ selection[i].get("custName") +i18n('i18n.customerGroup.existInList'));
	  	    		    		return false;
	  	    				}else{
	    					    Ext.getCmp("cycleListGridId").store.remove(selection[i]);
	    					    //添加到已选择客户store里
	    					    chooseStore.insert(i,selection[i]);
	  	  					}
  	  					}
  	  					setCount(chooseStore.getCount());
  					}
  				}]
  			}];
  		}
  	});
  	
  	/**.
  	 * <p>
  	 * 管理分组Panel
  	 * <p>
  	 * @author 张登
  	 * @时间 2012-3-26
  	 */
  	Ext.define('CustomerDevelopPlanePanel',{
  		extend:'BasicPanel',
  		searchCondition:null, //查询条件From
  		searchUpResult:null, //查询客户列表（左边Grid）
  		searchDownResult:null, //已选择客户列表（右边Grid）
  		items:null,
  		layout:'border',
  		initComponent:function(){
  			var me = this;
  			//查询条件From
  			me.searchCondition = Ext.create('CustomerGroupFormPanel',{id:'CustomerGroupFormPanelId'});
  			Ext.getCmp("formGroupId").store.load();
  			//查询客户列表store
//  			var store=Ext.create('SearchCustomerStore',{id:'SearchCustomerStoreId'});
  			
  			var cycleListStore = Ext.create('CycleListStore',{id:'cycleListStore'});
  			//给store添加事件
  			cycleListStore.on('beforeload',function(store,operation,e){
  				var customerInfoFormPanel =  Ext.getCmp('CustomerGroupFormPanelId');
  				var searchParams = {
  					'customerGroupDetail.deptId' : customerInfoFormPanel.getForm().findField('deptId').getValue(),
  					'customerGroupDetail.queryDate' : customerInfoFormPanel.getForm().findField('queryDate').getValue(),
  					'customerGroupDetail.groupId' : customerInfoFormPanel.getForm().findField('groupId').getValue(),
  					'customerGroupDetail.custName' : customerInfoFormPanel.getForm().findField('custName').getValue(),
  					'customerGroupDetail.custNumber' : customerInfoFormPanel.getForm().findField('custNumber').getValue(),
  					'customerGroupDetail.userId' : customerInfoFormPanel.getForm().findField('empName').getValue(),
  					'customerGroupDetail.sendGoodsType':'LESS_THAN_TRUCKLOAD'
  				};
  				Ext.apply(operation,{
  					params : searchParams
  				});
  			});
  			var selModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SIMPLE',id:'selModelId'});
  			//管理上面的grid
  			var cycleListGrid = Ext.create('CycleListGrid',{
  				id:'cycleListGridId',
  				selModel:selModel,
  				store:cycleListStore,
  				dockedItems:[{
  					xtype:'pagingtoolbar',
  					store:cycleListStore,
  					dock:'bottom',
  					displayInfo : true,
  					autoScroll : true
  				}],
  			    listeners: {
  			    	scrollershow: function(scroller) {
  			    		if (scroller && scroller.scrollEl) {
  			    				scroller.clearManagedListeners(); 
  			    				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
  			    		}
  			    	}
  			    }
  			});
  			me.searchUpResult =  cycleListGrid;
  			
  			var chooseCycleListStore = Ext.create('CycleListByGroupIdStore',{id:'chooseCycleListStoreId'});
  			//给store添加事件
  			chooseCycleListStore.on('beforeload',function(store,operation,e){
  				var searchParams = {
  					'customerGroupId' : customerGroupId,
  					'customerGroupDetail.queryDate' : new Date()
  				};
  				Ext.apply(operation,{
  					params : searchParams
  				});
  			});
  			
   			chooseCycleListStore.on('load',function(store,records){
   				for(var i=0;i<records.length;i++){//把原始数据加载到集合中
   					var record=new OperatorCustomerModel();
   					record.set("deptId",records[i].get("deptId"));
					record.set("userId",records[i].get("userId"));
					record.set("custId",records[i].get("custId"));
   					initStoreAry.add(record.get("custId"),record.data);
   					if(i==0){
   	   					Ext.getCmp("fUserId").setValue(records[0].get("userId"));
   	   				}
   				}
   				setCount(initStoreAry.getCount());
  			});
//  		    //给store添加事件
  			chooseCycleListStore.on('add',function(store,record,index,ops){
  				var initRecord=initStoreAry.getByKey(record[0].get("custId"))
				if(!Ext.isEmpty(initRecord)){//如果删除客户存在与添加集合里
					var removeRecord=removeCustomerAry.getByKey(record[0].get("custId"));
					if(!Ext.isEmpty(removeRecord)){
//						if(initRecord.userId!=removeRecord.userId){
//							updateCustomerAry.add(record[0].get("custId"),record[0].data);
//						}
						removeCustomerAry.removeAtKey(record[0].get("custId"));
					}
				}
  			});
  			
  		    //给store添加事件
  			chooseCycleListStore.on('remove',function(store,record,index,ops){
				//判断删除的
				if(!Ext.isEmpty(initStoreAry.getByKey(record.get("custId")))){//如果删除客户存在与添加集合里
					if(Ext.isEmpty(removeCustomerAry.getByKey(record.get("custId")))){
						removeCustomerAry.add(record.get("custId"),record.data);
//						updateCustomerAry.removeAtKey(record.get("custId"));
					}
				}
  			});
  			
  		    //给store添加事件
//  			chooseCycleListStore.on('update',function(store,record,index,ops){
//				if(!Ext.isEmpty(initStoreAry.getByKey(record.get("custId")))){
//					if(Ext.isEmpty(updateCustomerAry.getByKey(record.get("custId")))){
//						updateCustomerAry.add(record.get("custId"),record.data);
//					}
//				}
//  			});
  			
  			var chooseSelModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SIMPLE',id:'chooseSelModelId'});
  			
  			//管理下面的grid
  			var chooseCycleListGrid = Ext.create('CycleListGrid',{
  				id:'chooseCycleListGridId',
  				selModel:chooseSelModel,
  				store:chooseCycleListStore,
  			    listeners: {
  			    	scrollershow: function(scroller) {
  			    		if (scroller && scroller.scrollEl) {
  			    				scroller.clearManagedListeners(); 
  			    				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
  			    		}
  			    	}
  			    }
  			});
  			me.searchDownResult =  chooseCycleListGrid;
  			
  			//设置items
  			me.items = me.getItems();
  			Ext.getCmp("fUserId").store.load();
  			//var successFn = function(json){
			//	Ext.getCmp("deptNameId").setValue(json.user.deptName);
			//};
			//获取列头
			//maintainpData.queryUserInfo(null,successFn,null);
  			this.callParent();
  			
  			var deptModel=new DeptModel();
			deptModel.set("id",User.deptId);
			deptModel.set("deptName",User.deptName);
			Ext.getCmp("CustomerGroupFormPanelId").getForm().findField("deptId").store.add(deptModel);
			Ext.getCmp("CustomerGroupFormPanelId").getForm().findField("deptId").setValue(User.deptId);
  		},
  		getItems:function(){//整体布局
  			var me = this;
  			return [{
  				region:'north',
  				xtype:'basicpanel',
  				height:110,
  				layout:'border',
  				items:[{
  					region:'center',
  					xtype:'basicpanel',
  					layout:'fit',
  					items:[me.searchCondition]
  				},
  				Ext.create('RightSearchButtonPanel')]//border布局下面查询按钮
  			},{
  				region:'center',
  				xtype:'basicpanel',
  				layout:'border',
  				items:[{
  					region:'center',
  					xtype:'basicpanel',
  					layout:'border',
  					items:[{
  						region:'center',
  						xtype:'basicpanel',
  						layout:'fit',
  						flex:1,
  						items:[
  						    me.searchUpResult
  						]
  					},{
  						region:'south',
  						xtype:'basicpanel',
  						layout:'border',
  						flex:1,
  						items:[{
								region:'center',
								xtype:'basicpanel',
								layout:'fit',
								items:[me.searchDownResult]
							},{
								region:'north',
								xtype:'basicpanel',
								layout:'fit',
								height:36,
								items:[Ext.create('ButtonDemoPanel')] 
							}
						]
  					}]
  				},{
  					region:'south',
  					xtype:'basicpanel',
  					layout:'fit',
  					height:36,
  					items:[
  					    Ext.create('RightDownButtonPanel')
  					]//border布局下面制定开发计划按钮
  				}]
  			}];
  		}
  	});
  	
  	/**
	 * 显示制定、修改计划
	 */
	Ext.define('CreateDevelopPopWindow',{
		extend:'PopWindow',
		alias : 'widget.basicwindow',
		width:780,//************
		y:20,
		height:600,
		modal:true,
		title:i18n('i18n.customerGroup.manage'),
		layout:'fit',
		closeAction:'hide',
		listeners:{
			hide:function(){
				Ext.getCmp("customerGroupGridId").store.load();
				document.body.scrollLeft=0;
				document.body.scrollTop=0;
				document.getElementsByTagName("html")[0].style.overflowY="hidden";
				document.getElementsByTagName("html")[0].style.overflowX="hidden";
				viewport.doLayout();
			}
		},
		items:[Ext.create('CustomerDevelopPlanePanel',{'id':'customerDevelopPlanePanel'})]
	});
	var win=Ext.getCmp("CreateDevelopPopWindowId");//获取win
	if(!win){
		win=Ext.create('CreateDevelopPopWindow',{id:'CreateDevelopPopWindowId'});
	}
	
	/**
	 * 显示制定、修改计划
	 */
	Ext.define('GroupDetailWin',{
		extend:'PopWindow',
		alias : 'widget.basicwindow',
		width:800,//************
//		y:20,
		height:400,
		modal:true,
		layout:'fit',
		closeAction:'hide',
		items:[
		    Ext.create('PopupGridPanel',{
  			id:'DetailGridId',
  			columns:[],
  			store:
  			Ext.create('CycleListByGroupIdStore',{
  				id:'DetailStoreId',
  				listeners:{
  					beforeload:function(store,operation,e){
  						var searchParams = {
							'customerGroupId' : customerGroupId,
							'customerGroupDetail.queryDate' : new Date()
						};
						Ext.apply(operation,{
							params : searchParams
						});
  					}
  				}
  			})
  		})],
  		buttons:[{
			xtype:'button',
			text:i18n('i18n.DevelopManageView.close'),
			handler:function(){
				Ext.getCmp("GroupDetailWinId").close();
			}
		}]
	});
	var detailWin=Ext.getCmp("GroupDetailWinId");//获取win
	if(!detailWin){
		detailWin=Ext.create('GroupDetailWin',{id:'GroupDetailWinId'});
	}
	
	/**
	 *将界面显示到viewport中 
	 */
	var viewport=Ext.create('Ext.Viewport',{
		layout : 'border',
		autoScroll:true,
		items:[Ext.create('CustomerGroupPanel',{region:'center'})]
	});
	viewport.setAutoScroll(false);
	viewport.doLayout();
});

function setCount(count){
	Ext.getCmp("chooseCount").setValue(count);
	Ext.getCmp("mayCount").setValue(50-count);
}

