var processBaseData =  new ProcessBaseData();
Ext.onReady(function() {
	var COMPLAINT="COMPLAINT";
	var EXCEPTION="UNUSUAL";
	 
	/**
	 * 查询条件form
	 */
	Ext.define('SearchFormPanel', {
		extend : 'SearchFormPanel',
		height : 200,
		width : 470,
		initComponent : function() {
			var me = this
			me.items = me.getItems();
			this.callParent();
		},
		getItems : function() {
			var me = this;
			return [ {
				xtype : 'basicformpanel',
				layout : {
					type : 'table',
					columns : 4
				},
				defaultType : 'textfield',
				items : [{
	            	width:160,
	                xtype:          'combo',
	                mode:           'local',
	                triggerAction:  'all',
	                labelSeparator:"",
	                name:'ifparent',
					displayField:'value',
					valueField:'name',
					forceSelection :true,
	                store:Ext.create('Ext.data.Store', {
	                    fields : ['name', 'value'],
	                    data   : [
	                        {name : '0',   value: i18n('i18n.BaseHierarchyView.bascilevelname')},
	                        {name : '1',  value:i18n('i18n.BaseHierarchyView.basciType')}
	                    ]
	                }),
	                listeners:{
	                	change:function(th){
	                		if(Ext.isEmpty(th.getValue())){
	                			th.setValue("");
	                		}
	                	}
	                }
	            },{
					fieldLabel : ' ',
					labelWidth : 10,
					width:350,
					id:'bascilevelnameId',
					name:'bascilevelname',
					labelSeparator:""
				},{
					xtype:'hiddenfield',
					width:15
				},{
					xtype:'button',
					width:60,
					text:i18n('i18n.complaint.search'),
					handler : function(){
						var rightGrid=Ext.getCmp("RightGridId");
  						var leftGrid=Ext.getCmp("LeftGridId");
  						rightGrid.store.load();
  						leftGrid.store.load();
					}
				}]
			}]
		}
	});
	
	
	/**
	 * 操作按钮
	 */
	Ext.define('RightSearchButtonPanel',{
		extend:'PopButtonPanel', 
		items:null,
		region:'south',
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [{
				xtype:'popleftbuttonpanel',  
				width:500,
				items : [{
  					xtype : 'button',
  					text : i18n('i18n.complaint.look_up'),
  					handler : function(){
  						var rightGrid=Ext.getCmp("RightGridId");
  						var leftGrid=Ext.getCmp("LeftGridId");
  						if(rightGrid.selModel.getCount()==0 && leftGrid.selModel.getCount()==0){
  							MessageUtil.showMessage(i18n("i18n.baseLevel.please_choise"));
  						}else{
  							var record=null;
  							if(rightGrid.selModel.getCount()>0 && leftGrid.selModel.getCount()==0){//异常
  								record=Ext.getCmp("RightGridId").selModel.getSelection();
  							}else if(rightGrid.selModel.getCount()==0 && leftGrid.selModel.getCount()>0){//投诉
  								record=Ext.getCmp("LeftGridId").selModel.getSelection();
  							}
  							if(record[0].get("type")==COMPLAINT){//投诉
  								Ext.getCmp("complaintRadio").setValue(true);
  							}
  							if(record[0].get("type")==EXCEPTION){//异常
  								Ext.getCmp("exceptionRadio").setValue(true);
  							}
  							Ext.getCmp("basciLeveLComboxId").setValue(record[0].get("fatherId"));
  			    			Ext.getCmp("bTypeId").setValue(record[0].get("chiledBLName"));
  			    			Ext.getCmp("AddWindowGridId").store.load({
  			    				params:{
  			    					'basciLevel.type':record[0].get("type"),
  			    					'basciLevel.fid':record[0].get("childId")
  			    				}
  			    			});
  			    			Ext.getCmp("basciLeveLComboxId").disable();
  			    			Ext.getCmp("complaintRadio").disable();
  			    			Ext.getCmp("exceptionRadio").disable();
  			    			Ext.getCmp("bTypeId").disable();
  			    			Ext.getCmp("addResetId").hide();
  			    			Ext.getCmp("saveButtonId").hide();
  			    			Ext.getCmp("addProcResultId").hide();
  			    			Ext.getCmp("delProcResultId").hide();
  			    			
  			    			Ext.getCmp("AddWindowGridId").on('beforeedit', me.beforeEditEvent);
  							addWindow.show();
  							
  						}
  					}
  				},{
					xtype : 'button',
					text : i18n('i18n.complaint.btn.add'),
					handler : function(){
						Ext.getCmp("form").getForm().reset();
						Ext.getCmp("basciLeveLComboxId").store.removeAll();
		    			Ext.getCmp("basciLeveLComboxId").setValue("");
		    			Ext.getCmp("bTypeId").setValue("");
		    			Ext.getCmp("AddWindowGridId").store.removeAll();
		    			Ext.getCmp("basciLeveLComboxId").enable();
		    			Ext.getCmp("complaintRadio").enable();
		    			Ext.getCmp("exceptionRadio").enable();
		    			Ext.getCmp("bTypeId").enable();
		    			Ext.getCmp("addResetId").show();
		    			Ext.getCmp("saveButtonId").show();
		    			Ext.getCmp("addProcResultId").show();
			    		Ext.getCmp("delProcResultId").show();
			    		Ext.getCmp("hiddenId").setValue("");
			    		Ext.getCmp("hiddenId").disable();
			    		
			    		Ext.getCmp("AddWindowGridId").un('beforeedit', me.beforeEditEvent);
						addWindow.show();
					}
				},{
  					xtype : 'button',
  					text : i18n('i18n.comp.btn.update'),
  					handler : function(){
  						var rightGrid=Ext.getCmp("RightGridId");
  						var leftGrid=Ext.getCmp("LeftGridId");
  						if(rightGrid.selModel.getCount()==0 && leftGrid.selModel.getCount()==0){
  							MessageUtil.showMessage(i18n('i18n.comp.msg.no_select_one'));
  						}else{
  							var record=null;
  							if(rightGrid.selModel.getCount()>0 && leftGrid.selModel.getCount()==0){//异常
  								record=Ext.getCmp("RightGridId").selModel.getSelection();
  							}else if(rightGrid.selModel.getCount()==0 && leftGrid.selModel.getCount()>0){//投诉
  								record=Ext.getCmp("LeftGridId").selModel.getSelection();
  							}
  							if(record[0].get("type")==COMPLAINT){//投诉
  								Ext.getCmp("complaintRadio").setValue(true);
  							}
  							if(record[0].get("type")==EXCEPTION){//异常
  								Ext.getCmp("exceptionRadio").setValue(true);
  							}
  							Ext.getCmp("basciLeveLComboxId").setValue(record[0].get("fatherId"));
  			    			Ext.getCmp("bTypeId").setValue(record[0].get("chiledBLName"));
  			    			Ext.getCmp("AddWindowGridId").store.load({
  			    				params:{
  			    					'basciLevel.type':record[0].get("type"),
  			    					'basciLevel.fid':record[0].get("childId")
  			    				}
  			    			});
  			    			Ext.getCmp("hiddenId").enable();
  			    			Ext.getCmp("hiddenId").setValue(record[0].get("childId"));
  			    			Ext.getCmp("basciLeveLComboxId").disable();
  			    			Ext.getCmp("complaintRadio").disable();
  			    			Ext.getCmp("exceptionRadio").disable();
  			    			Ext.getCmp("bTypeId").enable();
  			    			Ext.getCmp("addResetId").hide();
  			    			Ext.getCmp("saveButtonId").show();
  			    			Ext.getCmp("addProcResultId").show();
  			    			Ext.getCmp("delProcResultId").show();
				    		Ext.getCmp("AddWindowGridId").un('beforeedit', me.beforeEditEvent);  			    			
  							addWindow.show();
  						}
  					}
  				},{
  					xtype : 'button',
  					text : i18n('i18n.comp.btn.yewu_scope_set'),
  					handler : function(){
//  						//投诉
//  						Ext.getCmp("LeftWindowGridId").store.removeAll();
//  						//异常
//  						Ext.getCmp("RightWindowGridId").store.removeAll();
//  						businessScopeSetWindow.show();
  						Ext.create('BasciLevelScopeSettingWindow').show();
  					}
  				}
  				,{
  					xtype : 'button',
  					text : i18n('i18n.comp.btn.yewu_type_delete')/*业务类型删除*/,
  					handler : function(){
  						var rightGrid=Ext.getCmp("RightGridId");
  						var leftGrid=Ext.getCmp("LeftGridId");
  						if(rightGrid.selModel.getCount()==0 && leftGrid.selModel.getCount()==0){
  							MessageUtil.showMessage(i18n('i18n.comp.msg.no_select_one'));
  							return;
  						}
  						var deleteArray = new Array();
  						var leftSelection=null,rightSelection=null;
  						
						if(leftGrid.selModel.getCount()>0){//投诉
							leftSelection=leftGrid.selModel.getSelection();
							for(var i=0;i<leftSelection.length;i++){
								var record = leftSelection[i];
								deleteArray.push(Ext.clone(record.data));
							}
						}
						if(rightGrid.selModel.getCount()>0){//异常
							rightSelection=rightGrid.selModel.getSelection();
							for(var i=0;i<rightSelection.length;i++){
								var record = rightSelection[i];
								deleteArray.push(Ext.clone(record.data));
							}
						}
						var params = {'basciLevelList':deleteArray}
						//执行成功
						var successFn = function(response){
							MessageUtil.showMessage(i18n('i18n.PotentialCustManagerView.deleteSuccess'));
							if(leftGrid.selModel.getCount()>0){//投诉
								leftGrid.getStore().remove(leftSelection);
							}
							if(rightGrid.selModel.getCount()>0){//异常
								rightGrid.getStore().remove(rightSelection);
							}
						};
						
						//执行失败
						var failFn = function(response){MessageUtil.showErrorMes(response.message);};
						MessageUtil.showQuestionMes(i18n('i18n.alert.msg.confirm_delete')/*你确认要删除吗？*/,function(e){
							if(e=='yes'){
								DpUtil.requestJsonAjax('basciLevelTypeDelelteList.action',params,successFn,failFn);
							}
						});
  					}
  				}
  			]
			},{
				xtype:'middlebuttonpanel' 
			}];
		},
		beforeEditEvent:function(){
			return false;	
		}
	});
	
	
	//投诉
	Ext.define('LeftGrid',{
		extend:'BasicVboxPanel', 
		searchLeftResult:null,
		items : null,
		initComponent : function() {
			var me = this;
			
			var store=Ext.create('SearchComplaintDataStore',{id:'SearchComplaintDataStoreId'});
  			store.on('beforeload',function(store,operation,e){
  				var searchScatterForm = Ext.getCmp("SearchConditionFormId");
  				
  				//设置请求参数
  				var searchParams = { 
  					//类型
  					'complaintCondition.ifparent':searchScatterForm.getForm().findField('ifparent').getValue(),
  					'complaintCondition.type':COMPLAINT,
  					//名字
  					'complaintCondition.bascilevelname':Ext.getCmp('bascilevelnameId').getValue()
  				};
  				Ext.apply(operation,{
  					params : searchParams
  				});
  			});
			store.load();	
			//查询结果Grid
			me.searchLeftResult =  Ext.create('SearchGridPanel',{
				id:'LeftGridId',
				store:store,
				columns:me.getColumns(),
				selModel:new Ext.selection.CheckboxModel(),
				listeners:{
					select:function(){
						if(Ext.getCmp("RightGridId").selModel.getCount()){
							Ext.getCmp("RightGridId").selModel.deselectAll();
						}
					}
				}
			});
			
			this.items = this.getItems();
			this.callParent();
		},
		getItems : function() {
			var me = this;
			return [
			{
				xtype:'toppanel',   
				items:[{
					xtype:'titlepanel',    
					flex:1,
					items:[{
						xtype:'displayfield',
						value:i18n('i18n.comp.comp_process.baseInformation')
					}]
				}]
			},{
				xtype:'basicpanel',    
				flex:1,
				items:[me.searchLeftResult]
			}
		  ];
		},
		getColumns:function(){//查询结果列
			return [{
  				header : i18n('i18n.BaseHierarchyView.bascilevelname'),
  				flex:0.5,
  				dataIndex : 'fatherBLName'
  			},{
  				header :i18n('i18n.BaseHierarchyView.basciType'),
  				flex:0.5,
  				dataIndex : 'chiledBLName'
  			}]
		}
	});
	
	//异常
	Ext.define('RightGrid',{
		extend:'BasicVboxPanel', 
		searchLeftResult:null,
		items : null,
		initComponent : function() {
			var me = this;
			var store = Ext.create('SearchCxceptionDataStore', {
				id:'SearchCxceptionDataStoreId'
			});
			store.on('beforeload',function(store,operation,e){
  				var searchScatterForm = Ext.getCmp("SearchConditionFormId");
  				//设置请求参数
  				var searchParams = { 
  					//类型
  					'exceptionCondition.ifparent':searchScatterForm.getForm().findField('ifparent').getValue(),
  					'exceptionCondition.type':EXCEPTION,
  					//名字
  					'exceptionCondition.bascilevelname':Ext.getCmp('bascilevelnameId').getValue()
  				};
  				Ext.apply(operation,{
  					params : searchParams
  				});
  			});
			store.load();
			//查询结果Grid
			me.searchLeftResult =  Ext.create('SearchGridPanel',{
				id:'RightGridId',
				store:store,
				selModel:new Ext.selection.CheckboxModel(),
				columns:me.getColumns(),
				listeners:{
					select:function(){
						if(Ext.getCmp("LeftGridId").selModel.getCount()){
							Ext.getCmp("LeftGridId").selModel.deselectAll();
						}
					}
				}
			});
			this.items = this.getItems();
			this.callParent();
		},
		getItems : function() {
			var me = this;
			return [
			{
				xtype:'toppanel',   
				items:[{
					xtype:'titlepanel',    
					flex:1,
					items:[{
						xtype:'displayfield',
						value:i18n('i18n.comp.exception_process.baseInformation')
					}]
				}]
			},{
				xtype:'basicpanel',    
				flex:1,
				items:[me.searchLeftResult]
			}
		  ];
		},
		getColumns:function(){//查询结果列
  			return [{
  				header : i18n('i18n.BaseHierarchyView.bascilevelname'),
  				flex:0.5,
  				dataIndex : 'fatherBLName'
  			},{
  				header :i18n('i18n.BaseHierarchyView.basciType'),
  				flex:0.5,
  				dataIndex : 'chiledBLName'
  			}]
		}
	});
	
	/**.
	 * <p>
	 * 主Panel
	 * <p>
	 * @author 张登
	 * @时间 2012-3-26
	 */
	Ext.define('MainPanel',{
		extend:'BasicPanel',
		searchCondition:null, //查询条件From
		items:null,
		layout:'border',
		initComponent:function(){
			var me = this;
			//查询条件From
			me.searchCondition = Ext.create('SearchFormPanel',{id:'SearchConditionFormId'});
			//设置items
			me.items = me.getItems();
			this.callParent();
		},
		getItems:function(){//整体布局
			var me = this;
			return [{
				region:'north',
				xtype:'basicpanel',
				height:80,
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
				bodyStyle:'padding:5px 0px 0px 0px',
				items:[{
					region:'center',
					xtype:'basicpanel',
					layout:'border',
					cls : 'formpanel',
					flex:1,
					items:[{
						region:'center',
						xtype:'basicpanel',
						layout:'fit',
						flex:0.4,
						bodyStyle:'padding:5px',
						items:[Ext.create('LeftGrid')]//异常
					},{
						region:'east',
						xtype:'basicpanel',
						layout:'fit',
						bodyStyle:'padding:5px',
						flex:0.4,
						items:[Ext.create('RightGrid')]//投诉
					}]
				}]
			}];
		}
	});	
	
	//投诉
	Ext.define('LeftWindowGrid',{
		extend:'BasicVboxPanel', 
		searchLeftResult:null,
		items : null,
		initComponent : function() {
			var me = this;
			var store = Ext.create('SearchWinComplaintDataStore', {
			    id:'SearchWinComplaintDataStoreId'
			});
			
			store.on('beforeload',function(store,operation,e){
  				//设置请求参数
  				var searchParams = { 
  					//id
  					'basciLevel.fid':searchScatterForm.getForm().findField('ifparent').getValue(),
  					//type
  					'basciLevel.type':Ext.getCmp('bascilevelnameId').getValue()
  				};
  				Ext.apply(operation,{
  					params : searchParams
  				});
  			});
			
			//查询结果Grid
			me.searchLeftResult =  Ext.create('PopupGridPanel',{
				id:'LeftWindowGridId',
				plugins:Ext.create('Ext.grid.plugin.CellEditing', {  
				  	clicksToEdit : 1
				}),
				store:store,
				columns:me.getColumns()
			});
			this.items = this.getItems();
			this.callParent();
		},
		getItems : function() {
			var me = this;
			return [{
				xtype:'toppanel',   
				items:[{
					xtype:'titlepanel',  
					flex:1,
					items:[{
						xtype:'displayfield',
						value:i18n('i18n.comp.complaint_yewu_scope')
					}]
				},{
					xtype:'btnpanel',  
					defaultType:'button',
					items:[{
						text:i18n('i18n.complaint.btn.add'),
						handler:function(){
							var m=new BasciLevelModel();
							Ext.getCmp("LeftWindowGridId").store.add(m);
						}
					},{
						text:i18n('i18n.complaint.btn.delete'),
						handler:function(){
							var m=new ProcResultModel();
							if(Ext.getCmp("LeftWindowGridId").selModel.getCount()>0){
								Ext.getCmp("LeftWindowGridId").store.remove(Ext.getCmp("LeftWindowGridId").selModel.getSelection());	 
							}
						}
					}]
				}] 
			},{
				xtype:'basicpanel',    
				flex:1,
				items:[me.searchLeftResult]
			}];
		},
		getColumns:function(){//查询结果列
			return [{
  				header :i18n('i18n.BaseHierarchyView.bascilevelname'),
  				flex:0.5,
  				dataIndex : 'bascilevelname',
  	            editor: {
  	                
  	            }
  			}]
		}
	});

	//异常
	Ext.define('RightWindowGrid',{
		extend:'BasicVboxPanel', 
		searchLeftResult:null,
		items : null,
		initComponent : function() {
			var me = this;
			var store = Ext.create('SearchWinCxceptionDataStore', {
			    id:'SearchWinCxceptionDataStoreId'
			});
			
			//查询结果Grid
			me.searchLeftResult =  Ext.create('PopupGridPanel',{
				id:'RightWindowGridId',
				plugins:Ext.create('Ext.grid.plugin.CellEditing', {  
				  	clicksToEdit : 1
				}),
				store:store,
				columns:me.getColumns()
			});
			this.items = this.getItems();
			this.callParent();
		},
		getItems : function() {
			var me = this;
			return [{
				xtype:'toppanel',   
				items:[{
					xtype:'titlepanel',  
					flex:1,
					items:[{
						xtype:'displayfield',
						value:i18n('i18n.comp.exception_yewu_scope')
					}]
				},{
					xtype:'btnpanel',  
					defaultType:'button',
					items:[{
						text:i18n('i18n.complaint.btn.add'),
						handler:function(){
							var m=new BasciLevelModel();
							Ext.getCmp("RightWindowGridId").store.add(m);
						}
					},{
						text:i18n('i18n.complaint.btn.delete'),
						handler:function(){
							var m=new ProcResultModel();
							if(Ext.getCmp("RightWindowGridId").selModel.getCount()>0){
								Ext.getCmp("RightWindowGridId").store.remove(Ext.getCmp("RightWindowGridId").selModel.getSelection());	 
							}
						}
					}]
				}] 
			},{
				xtype:'basicpanel',    
				flex:1,
				items:[me.searchLeftResult]
			}];
		},
		getColumns:function(){
			return [{
  				header :i18n('i18n.BaseHierarchyView.bascilevelname'),
  				flex:0.5,
  				dataIndex : 'bascilevelname',
  	            editor: {
  	                
  	            }
  			}]
		}
	});
		
	/**.
	 * <p>
	 * 开发计划新增、修改主Panel
	 * <p>
	 * @author 张登
	 * @时间 2012-3-26
	 */
	Ext.define('MainWindowPanel',{
		extend:'BasicPanel',
		searchCondition:null, //查询条件From
		items:null,
		layout:'fit',
		initComponent:function(){
			var me = this;
			me.items = me.getItems();
			this.callParent();
		},
		getItems:function(){//整体布局
			var me = this;
			return [{
				region:'center',
				xtype:'basicpanel',
				layout:'border',
				items:[{
					region:'center',
					xtype:'basicpanel',
					layout:'border',
					flex:1,
					items:[{
						region:'center',
						xtype:'basicpanel',
						layout:'fit',
						flex:0.4,
						bodyStyle:'padding:5px',
						items:[Ext.create('LeftWindowGrid')]
					},{
						region:'east',
						xtype:'basicpanel',
						layout:'fit',
						bodyStyle:'padding:5px',
						flex:0.4,
						items:[Ext.create('RightWindowGrid')]
					}]
				}]
			}];
		}
	});
		
	/**
	 * 显示制定、修改计划
	 */
	Ext.define('BusinessScopeSetWindow',{
		extend:'PopWindow',
		width:600,
		height:400,
		modal:true,
		title:i18n('i18n.comp.win_title.yewu_scope_add')/*业务范围新增*/,
		layout:'fit',
		closeAction:'hide',
		items:[Ext.create('MainWindowPanel',{'id':'mainWindowPanelId'})],
		buttons:[{
			xtype:'button',
			text:i18n('i18n.comp.btn.save'),
			handler:function(t){
				t.disable();
				var complaintAry = new Array();
				var exceptionAry = new Array();
				var bj=false;
				//投诉
				Ext.getCmp("LeftWindowGridId").store.each(function(record){
					if(!Ext.isEmpty(record.get("bascilevelname"))){
						if(record.get("bascilevelname").length>20){
							MessageUtil.showMessage(i18n('i18n.comp.baseInfor.msg.comp_yw_scope_budayu_20'));
							bj=true;
							t.enable();
						}
						record.set("type",COMPLAINT);
						complaintAry.push(record.data);
					}else{
						Ext.getCmp("LeftWindowGridId").store.remove(record);
						t.enable();
					}
				});
				//异常
				Ext.getCmp("RightWindowGridId").store.each(function(record){
					if(!Ext.isEmpty(record.get("bascilevelname"))){
						if(record.get("bascilevelname").length>20){
							MessageUtil.showMessage(i18n('i18n.comp.baseInfor.msg.exce_yw_scope_budayu_20'));
							bj=true;
							t.enable();
						}
						record.set("type",EXCEPTION);
						exceptionAry.push(record.data);
					}else{
						Ext.getCmp("RightWindowGridId").store.remove(record);
						t.enable();
					}
				});
				if(bj){
					return false;
				}
				var params = {
					'complaintListBasciLevel' : complaintAry,
					'exceptionListBasciLevel' : exceptionAry
				};
				var successFn = function(json){
					//投诉
					Ext.getCmp("LeftWindowGridId").store.each(function(record){
						record.commit();
					});
					//异常
					Ext.getCmp("RightWindowGridId").store.each(function(record){
						record.commit();
					});
					MessageUtil.showInfoMes(i18n('i18n.comp.msg.save_succeed'));
					t.enable();
					businessScopeSetWindow.hide();
					//刷新业务范围设置
					Ext.getCmp('complaintGridId').store.load();
					Ext.getCmp('exceptionGridId').store.load();
				}
				var failureFn = function(json){
					MessageUtil.showErrorMes(json.message);t.enable();
				};
				if(!Ext.isEmpty(complaintAry) || !Ext.isEmpty(exceptionAry)){
					//保存
					processBaseData.saveBasciLevel(params,successFn,failureFn);
				}else{
					MessageUtil.showMessage(i18n('i18n.comp.msg.no_save_data'));
					t.enable();
				}
			}},{
	    	xtype:'button',
		    text:i18n('i18n.complaintReport.btn_close'),
		    handler:function(){
		    	businessScopeSetWindow.hide();
			}
        }]
	});
	var businessScopeSetWindow=Ext.getCmp("businessScopeSetWindowId");//获取win
	if(!businessScopeSetWindow){
		businessScopeSetWindow=Ext.create('BusinessScopeSetWindow',{id:'businessScopeSetWindowId'});
	}
	
	Ext.define('AddWindowGrid',{
		extend:'BasicVboxPanel', 
		searchLeftResult:null,
		items : null,
		initComponent : function() {
			var me = this;
			
			var store = Ext.create('SearchFoundationData', {
				id:'SearchFoundationDataId'
			});
			
			//查询结果Grid
			me.searchLeftResult =  Ext.create('PopupGridPanel',{
				id:'AddWindowGridId',
				store:store,
				plugins:Ext.create('Ext.grid.plugin.CellEditing', {  
				  	clicksToEdit : 1
				}),
				columns:me.getColumns()
			});			
			
			this.items = this.getItems();
			this.callParent();
		},
		getItems : function() {
			var me = this;
			return [{
				xtype:'toppanel',   
				items:[{
					xtype:'titlepanel',  
					flex:1,
					items:[{
						xtype:'displayfield',
						value:i18n('i18n.comp.value.processResult_baseInfo')
					}]
				}] 
			},{
				xtype:'basicpanel',
				layout:'border',
				flex:1,
				items:[{
					region:'center',
					xtype:'basicpanel',
					layout:'fit',
					flex:1,
					items:[me.searchLeftResult]
				},{
					region:'north',
					xtype:'toppanel',
					height:36,
					items:[{
						xtype : 'basicformpanel',
						layout : {
							type : 'table',
							columns : 4
						},
						defaultType : 'textfield',
						items : [{
				        	width:220,
				            xtype:          'combo',
				            mode:           'local',
				            queryMode: 'local',
				            fieldLabel :i18n('i18n.BaseHierarchyView.bascilevelname'),
				            labelWidth : 60,
				            triggerAction:  'all',
				            id:'basciLeveLComboxId',
				            name:'bascilevelname',
							displayField:'bascilevelname',
							valueField:'fid',
							forceSelection :true,
				            store:Ext.create('BasciLevelStore', {id:'BasciLevelStoreId'}),
				            listConfig: {
				                loadMask:false
				            },
				            listeners:{
				            	change:function(t){
				            		if(Ext.isEmpty(t.getValue())){
				            			t.setValue("");
				            		}
				            	}
				            }
				        },{
							fieldLabel : i18n('i18n.BaseHierarchyView.basciType'),
							labelWidth : 60,
							width:180,
							id:'bTypeId'
						},{
							xtype:'hiddenfield',
							id:'hiddenId',
							width:15
						}]
					},{
						xtype:'btnpanel',  
						defaultType:'button',
						items:[{
							text:i18n('i18n.complaint.btn.add'),
							id:'addProcResultId',
							handler:function(){
								var m=new ProcResultModel();
								Ext.getCmp("AddWindowGridId").store.add(m);
							}
						},{
							text:i18n('i18n.complaint.btn.delete'),
							id:'delProcResultId',
							handler:function(){
								var m=new ProcResultModel();
								if(Ext.getCmp("AddWindowGridId").selModel.getCount()>0){
									Ext.getCmp("AddWindowGridId").store.remove(Ext.getCmp("AddWindowGridId").selModel.getSelection());	 
								}
							}
						}]
					}]
				}]
			}];
		},
		getColumns:function(){
			return [{
  				header : i18n('i18n.complaint.process_result.feedtimeLimit'),
  				flex:0.3,
  				dataIndex : 'feedbacklimit',
  				editor:{}
  			},{
  				header : i18n('i18n.complaint.process_result.processtimeLimit'),
  				flex:0.3,
  				dataIndex : 'proclimit',
  				editor:{}
  			},{
  				header : i18n('i18n.complaint.process_result.dealmatters'),
  				flex:0.3,dataIndex : 'deallan',editor:{
					xtype:'textfield',listeners:{
					change:function(combo,newValue,oldValue){
						if(newValue.length>200){combo.setValue(oldValue);}
					}}
				}
  			}]
		}
	});
	
	
	
	/**.
	 * <p>
	 * 开发计划新增、修改主Panel
	 * <p>
	 * @author 张登
	 * @时间 2012-3-26
	 */
	Ext.define('AddMainWindowPanel',{
		extend:'BasicPanel',
		searchCondition:null, //查询条件From
		items:null,
		layout:'border',
		initComponent:function(){
			var me = this;
			me.items = me.getItems();
			this.callParent();
		},
		getItems:function(){//整体布局
			var me = this;
			return [{
				xtype:'form',  
				region:'north',
				border:false,
				id:'form',
				items: [{
		            xtype: 'radiogroup',
		            width:220,
		            labelStyle : "text-align:left;width:60px;",  
		            fieldLabel: i18n('i18n.complaint.comp_reportType'),
		            id:'typeRadioId',
		            items: [
		                {boxLabel: i18n('i18n.complaintReport.complaint'), name: 'type', id:'complaintRadio',inputValue: COMPLAINT,listeners:{
		                	change:function(t,p1){
		                		if(t.getValue()){
		                			Ext.getCmp("basciLeveLComboxId").clearValue();
		                			Ext.getCmp("basciLeveLComboxId").store.load({params:{
		                				'exceptionCondition.type':COMPLAINT
		                				,'exceptionCondition.feedBack':'1'
		                			}});
		                		}
		                	}
		                }},
		                {boxLabel: i18n('i18n.complaintReport.unusual'), name: 'type', id:'exceptionRadio',inputValue: EXCEPTION,listeners:{
		                	change:function(t,p1){
		                		if(t.getValue()){
		                			Ext.getCmp("basciLeveLComboxId").clearValue();
		                			Ext.getCmp("basciLeveLComboxId").store.load({params:{
		                				'exceptionCondition.type':EXCEPTION
		                				,'exceptionCondition.feedBack':'1'
		                			}});
		                		}
		                	}
		                }}
		            ]
		        }]
			},{
				region:'center',
				xtype:'basicpanel',
				layout:'fit',
				items:[Ext.create('AddWindowGrid',{id:'addWindowGridId'})]
			}];
		}
	});
	
	/**
	 * 显示制定、修改计划
	 */
	Ext.define('AddWindow',{
		extend:'PopWindow',
		width:650,
		height:450,
		modal:true,
		title:i18n('i18n.comp.win_title.processResult_set'),
		layout:'fit',
		closeAction:'hide',
		items:[Ext.create('AddMainWindowPanel',{id:'addMainWindowPanelId'})],
		buttons:[{
			xtype:'button',
			text:i18n('i18n.comp.btn.save'),
			id:'saveButtonId',
			handler:function(t){
				t.disable();
//				Ext.getCmp("complaintRadio").setValue(true);
				if(Ext.isEmpty(Ext.getCmp("typeRadioId").getValue().type)){
					MessageUtil.showMessage(i18n('i18n.complaintReport.validate.empty_reportType'));
					t.enable();
					return false;
				}
				
				if(Ext.isEmpty(Ext.getCmp("basciLeveLComboxId").getValue())){
					MessageUtil.showMessage(i18n('i18n.BaseHierarchyView.msg.bascilevelname'));
					t.enable();
					return false;
				}
				if(Ext.isEmpty(Ext.getCmp("bTypeId").getValue())){
					MessageUtil.showMessage(i18n('i18n.comp.msg.print_wy_type'));
					t.enable();
					return false;
				}
				if(!Ext.isEmpty(Ext.getCmp("bTypeId").getValue()) && Ext.getCmp("bTypeId").getValue().length>20){
					MessageUtil.showMessage(i18n('i18n.comp.msg.wy_type_dayu_20'));
					t.enable();
					return false;
				}
				//投诉
				var i=1;
				var procResultAry=new Array();
				var bj=false;
				Ext.getCmp("AddWindowGridId").store.each(function(record){
					if(Ext.isEmpty(record.get("feedbacklimit")) && Ext.isEmpty(record.get("proclimit")) && Ext.isEmpty(record.get("deallan"))){
						Ext.getCmp("AddWindowGridId").store.remove(record);
					}else{
						if(Ext.isEmpty(record.get("feedbacklimit"))){
							MessageUtil.showMessage(i18n("i18n.baseLevel.the")+i+i18n("i18n.baseLevel.row")+"，"+i18n('i18n.comp.msg.not_null_feedbacklimit'));
							bj=true;
							t.enable();
							return false;
						}
						if(!Ext.isNumber(record.get("feedbacklimit"))){
							MessageUtil.showMessage(i18n("i18n.baseLevel.the")+i+i18n("i18n.baseLevel.row")+"，"+i18n('i18n.comp.msg.isNumber_feedbacklimit'));
							bj=true;
							t.enable();
							return false;
						}
						if(record.get("feedbacklimit")>9999999999){
							MessageUtil.showMessage(i18n("i18n.baseLevel.the")+i+i18n("i18n.baseLevel.row")+"，"+i18n('i18n.comp.msg.feedbacklimit_budayu_10'));
							bj=true;
							t.enable();
							return false;
						}
						if(Ext.isEmpty(record.get("proclimit"))){
							MessageUtil.showMessage(i18n("i18n.baseLevel.the")+i+i18n("i18n.baseLevel.row")+"，"+i18n('i18n.comp.msg.not_null_proclimit'));
							bj=true;
							t.enable();
							return false;
						}
						if(record.get("proclimit").length>10){
							MessageUtil.showMessage(i18n("i18n.baseLevel.the")+i+i18n("i18n.baseLevel.row")+"，"+i18n('i18n.comp.msg.proclimit_budayu_10'));
							bj=true;
							t.enable();
							return false;
						}
						if(Ext.isEmpty(record.get("deallan"))){
							MessageUtil.showMessage(i18n("i18n.baseLevel.the")+i+i18n("i18n.baseLevel.row")+"，"+i18n('i18n.comp.msg.not_null_deallan'));
							bj=true;
							t.enable();
							return false;
						}
						if(record.get("deallan").length>200){
							MessageUtil.showMessage(i18n("i18n.baseLevel.the")+i+i18n("i18n.baseLevel.row")+"，"+i18n('i18n.comp.msg.deallan_budayu_200'));
							bj=true;
							t.enable();
							return false;
						}
						procResultAry.push(record.data);
					}
					i++;
				});
				if(bj){
					return false;
				}
				var params = {
					'basciLevel':{
						'parentid' : Ext.getCmp("basciLeveLComboxId").getValue(),
						'bascilevelname' : Ext.getCmp("bTypeId").getValue(),
						'fid' : Ext.isEmpty(Ext.getCmp("hiddenId").getValue())?0:parseInt(Ext.getCmp("hiddenId").getValue()),
						'type' : Ext.getCmp("typeRadioId").getValue().type
					},
					'procResultList' : procResultAry
				};
				var successFn = function(json){
					//处理结果
					Ext.getCmp("AddWindowGridId").store.each(function(record){
						record.commit();
					});
					t.enable();
					addWindow.hide();
					MessageUtil.showInfoMes(i18n('i18n.comp.msg.save_succeed'))
					var rightGrid=Ext.getCmp("RightGridId");
					var leftGrid=Ext.getCmp("LeftGridId");
					rightGrid.store.load();
					leftGrid.store.load();
				}
				var failureFn = function(json){
					MessageUtil.showErrorMes(json.message);t.enable();
				};
				//保存
				processBaseData.saveFoundation(params,successFn,failureFn);
			}
		},{
	    	xtype:'button',
	    	id:'addResetId',
		    text:i18n('i18n.complaintReport.btn_reset'),
		    handler:function(){
		    	MessageUtil.showQuestionMes(i18n('i18n.baseLevel.reset_allInfo'), function(e){
		    		if(e=='yes'){
		    			Ext.getCmp("form").getForm().reset();
		    			Ext.getCmp("basciLeveLComboxId").setValue("");
		    			Ext.getCmp("bTypeId").setValue("");
		    			Ext.getCmp("AddWindowGridId").store.removeAll();
		    		}
		    	});
			}
        },{
	    	xtype:'button',
		    text:i18n('i18n.complaintReport.btn_close'),
		    handler:function(){
		    	addWindow.hide();
			}
        }]
	});
	var addWindow=Ext.getCmp("addWindowId");//获取win
	if(!addWindow){
		addWindow=Ext.create('AddWindow',{id:'addWindowId'});
	}
	
	/**
	 *将界面显示到viewport中 
	 */
	Ext.create('Ext.Viewport',{
		layout : 'fit',
		items:[Ext.create('MainPanel')]
	});
	/* ========================业务范围设置弹出框  begin =========================================== */
	//searchBusinessScopeAll.action   basciLevelScopeList
	/** 
	 * 业务范围设置弹出框 声明
	 */ 
	Ext.define('BasciLevelScopeSettingWindow', {
	    extend:'PopWindow'
		,complaintGrid:null /* 投诉范围记录*/
		,exceptionGrid:null /* 异常范围记录*/
		,complaintTitleAndOperate:null /* 投诉范围 标题 and 操作*/
		,exceptionTitleAndOperate:null /* 异常范围标题 and 操作*/
		,width:750,height:500
	    ,layout:{align:'stretch',type:'hbox'},modal:true
		,title:i18n('i18n.comp.win_title.yewu_scope_set')/*业务范围设置*/
	    ,initComponent: function() {
	        var me = this;
	      	me.complaintGrid = Ext.create('BasciLevelScopeGridPanel',{
	      		id:'complaintGridId',store:Ext.create('BasciLevelScopeStore',{
	      			listeners:{
						beforeload:function(store, operation, eOpts){
							var	searchParams = {
								'exceptionCondition.type':i18n('i18n.complaint.fpririty_complaint')
								,'exceptionCondition.useYn':'1'
							};
							Ext.apply(operation,{params:searchParams});	
						}
					}
	      		})
	      	});
	      	me.exceptionGrid = Ext.create('BasciLevelScopeGridPanel',{
	      		id:'exceptionGridId',store:Ext.create('BasciLevelScopeStore',{
	      			listeners:{
						beforeload:function(store, operation, eOpts){
							var	searchParams = {
								'exceptionCondition.type':i18n('i18n.complaint.fpririty_exception')
								,'exceptionCondition.useYn':'1'
							};
							Ext.apply(operation,{params:searchParams});	
						}
					}
	      		})
	      	});
	      	me.complaintTitleAndOperate = Ext.create('TitleAndOperate',{
	      		'title_show':i18n('i18n.comp.complaint_yewu_scope')/*投诉业务范围*/
	      		,region:'north','callBack_delete':function(){
	      			if(me.complaintGrid.selModel.getCount()==0){
						MessageUtil.showMessage(i18n('i18n.comp.msg.no_select_one'));
						return;
					}
					
	      			var selection = me.complaintGrid.selModel.getSelection();
	      			var params = {'basciLevel':Ext.clone(selection[0].data)}
	      			var successFn = function(response){
	      				processingMask.hide();
						MessageUtil.showMessage(i18n('i18n.PotentialCustManagerView.deleteSuccess'));
						me.complaintGrid.getStore().remove(selection[0]);
					};
					//执行失败
					var failFn = function(response){processingMask.hide();MessageUtil.showErrorMes(response.message);};
					MessageUtil.showQuestionMes(i18n('i18n.alert.msg.confirm_delete')/*你确认要删除吗？*/,function(e){
						if(e=='yes'){
							processingMask.show();
							DpUtil.requestJsonAjax('basciLevelScopeDelelte.action',params,successFn,failFn);
						}
					});
	      		}
	      	});
	      	me.exceptionTitleAndOperate = Ext.create('TitleAndOperate',{
	      		'title_show':i18n('i18n.comp.exception_yewu_scope')/*异常业务范围*/
	      		,region:'north','callBack_delete':function(){
	      			if(me.exceptionGrid.selModel.getCount()==0){
						MessageUtil.showMessage(i18n('i18n.comp.msg.no_select_one'));
						return;
					}
					
	      			var selection = me.exceptionGrid.selModel.getSelection();
	      			var params = {'basciLevel':Ext.clone(selection[0].data)}
	      			var successFn = function(response){
	      				processingMask.hide();
						MessageUtil.showMessage(i18n('i18n.PotentialCustManagerView.deleteSuccess'));
						me.exceptionGrid.getStore().remove(selection[0]);
					};
					//执行失败
					var failFn = function(response){processingMask.hide();MessageUtil.showErrorMes(response.message);};
					MessageUtil.showQuestionMes(i18n('i18n.alert.msg.confirm_delete')/*你确认要删除吗？*/,function(e){
						if(e=='yes'){
							processingMask.show();
							DpUtil.requestJsonAjax('basciLevelScopeDelelte.action',params,successFn,failFn);
						}
					});
	      		}
	      	});
			Ext.applyIf(me,{
				items:[
					{xtype:'basicpanel',flex:0.5,layout:'border',items:[
						me.complaintTitleAndOperate,me.complaintGrid
					]}
					,{xtype:'basicpanel',width:10}/*间隙*/
					,{xtype:'basicpanel',flex:0.5,layout:'border',items:[
						me.exceptionTitleAndOperate,me.exceptionGrid
					]}
				]
				,buttons:me.getButtons()
			})
	        me.callParent(arguments);
	        
	        //加载数据
	        me.complaintGrid.store.load();
	        me.exceptionGrid.store.load();
	    }
	    ,getButtons:function(){
	    	var me = this;
	    	return [
	    		{text:i18n('i18n.comp.btn.yewu_scope_add')/*新增业务范围*/,handler:function(){
	    			Ext.getCmp("LeftWindowGridId").store.removeAll();
					//异常
					Ext.getCmp("RightWindowGridId").store.removeAll();
					businessScopeSetWindow.show();
	    		}}
	    		,{text:i18n('i18n.complaintReport.btn_close'),scope:me,handler:me.close}
	    	];
	    }
	});

	/**
	 * 标题  和 操作
	 */
	Ext.define('TitleAndOperate',{
		extend:'BasicPanel'
		,'title_show':'这是标题'
		,'callBack_delete':function(){}
		,height:25
		,layout:{align:'stretch',type:'hbox'}
		,initComponent:function(){
			var me = this;
			Ext.applyIf(me, {
	            items: [
	                {xtype:'titlepanel',flex:1,items:[{xtype:'displayfield',value:me.title_show}]},
	                {xtype:'btnpanel',items:[{xtype:'button',text:i18n('i18n.complaint.btn.delete')/*删除业务范围*/,handler:me.callBack_delete}]}
	            ]
	        });
	        me.callParent(arguments);
		}
	});
	/**
	 * 业务范围设置列表
	 */
	Ext.define('BasciLevelScopeGridPanel',{
		extend:'PopupGridPanel',region:'center'
  		,initComponent:function(){
			var me = this;
			Ext.applyIf(me, {
				'selModel':new Ext.selection.CheckboxModel({mode:'single'})
				,'columns':[{header:i18n('i18n.BaseHierarchyView.bascilevelname')/*业务范围*/,flex:1,dataIndex:'bascilevelname'}]
	        });
	        me.callParent(arguments);
		}
	});
/* ========================业务范围设置弹出框  begin =========================================== */
});