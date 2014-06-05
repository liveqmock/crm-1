Ext.onReady(function(){
	//初始化提示信息条
	Ext.QuickTips.init();
	/**
	 * 主页面查询条件面板
	 * 2013-11-26
	 * 肖红叶
	 */
	Ext.define('PositionRoleResearchPanel',{
	    extend:'SearchFormPanel',
        layout : {
            type : 'table',
            columns : 8
        },
        defaultType : 'textfield',
        defaults : {
        	margin:'0 5 0 0',
            labelWidth : 80,
            labelAlign: 'right',
            width : 120
        },
	    items:null,
	    initComponent:function(){
	        var me = this;
	        me.items = me.getItems();
	        this.callParent();
	    },
	    getItems:function(){
	        var me = this;
	        return [{//虚拟职位名称输入框
        		fieldLabel:i18n('i18n.vPositionRole.virtualPositionName'),
        		name:'virtualPositionName',
        		width:240,
        		colspan:2
        	},{//查询按钮
        		xtype:'button',
        		text:i18n('i18n.authorization.find'),
        		width:60,
        		colspan:2,
        		handler:function(){
        			//设置查询类型为按虚拟职位名称查询
        			var positionRoleResearchForm = Ext.getCmp('positionRoleResearchPanelId').getForm();
        			positionRoleResearchForm.findField('searchType').setValue('VPOSITION');
        			Ext.getCmp('positionRoleGridPanelId').store.loadPage(1);
        		}
        	},{//角色名称输入框
        		fieldLabel:i18n('i18n.vPositionRole.roleName'),
        		name:'roles',
        		width:280,
        		labelWidth : 120,
        		colspan:2
        	},{//查询按钮
        		xtype:'button',
        		text:i18n('i18n.authorization.find'),
        		width:60,
        		colspan:2,
        		handler:function(){
        			//设置查询类型为按角色名称查询
        			var positionRoleResearchForm = Ext.getCmp('positionRoleResearchPanelId').getForm();
        			positionRoleResearchForm.findField('searchType').setValue('ROLENAME');
        			Ext.getCmp('positionRoleGridPanelId').store.loadPage(1);
        		}
        	},{//隐藏域，用以记录查询的类型,nothing表示初始化查询，vposition表示按虚拟岗位查询，rolename表示按照角色名称查询
        		xtype:'hiddenfield',
        		name:'searchType',
        		value:'NOTHING'
        	}];
	    }
	});
	
  	/**
  	 * 新增、修改等操作按钮区
  	 * 20131126
  	 * 肖红叶
  	 */
  	Ext.define('OperationButtonPanel',{
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
  				width:420,
  				items:[{//新增按钮
  					xtype:'button',
  					id:'addBtnId',
  	                text : i18n('i18n.authorization.add'),
  	                handler : function(){
  	                	var positionRoleAddAndUpdatePopWindow = Ext.getCmp('positionRoleAddAndUpdatePopWindowId');
  	                	if(Ext.isEmpty(positionRoleAddAndUpdatePopWindow)){
  	                		positionRoleAddAndUpdatePopWindow = Ext.create('PositionRoleAddAndUpdatePopWindow',{
  	  	                		id:'positionRoleAddAndUpdatePopWindowId'
  	  	                	});
  	                	}
  	                	positionRoleAddAndUpdatePopWindow.setTitle(i18n('i18n.authorization.add')+i18n('i18n.vPositionRole.vPositionRoleRelationship'));
  	                	positionRoleAddAndUpdatePopWindow.show(); 
  	                	buttonControl('ADD');
  	                	//初始化虚拟职位下拉框和虚拟职位描述
  	                	Ext.getCmp('virtualPositionSelectFromId').getForm().reset();
  	                	Ext.getCmp('wholeRoleSelectFormId').getForm().reset();
                		//加载待分配角色列表
                		Ext.getCmp('wholeRoleSelectGridId').store.load({
                			params:{
                				'virtualPositionId':'',
                				'roleName':'',
                				'isMap':'NO'
                			}
                		});
                		//初始化待分配角色列表
                		Ext.getCmp('wholeRoleSelectGridId').store.removeAll();
  	               }
  	            }, {//修改按钮
  					xtype:'button',
  					id:'updateBtnId',
  	                text : i18n('i18n.authorization.update'),
  	                disabled:true,
  	                handler : function() {
  	                	//获取选中的记录
  	                	var selection = Ext.getCmp('positionRoleGridPanelId').getSelectionModel().getSelection();
  	                	if(selection.length != 1){
  	                		MessageUtil.showErrorMes(i18n('i18n.authorization.operation_message'));
            				return false;
  	                	}
  	                	//虚拟职位id
  	                	var virtualPositionId = selection[0].get('virtualPositionId');
  	                	if(Ext.isEmpty(virtualPositionId)){
  	                		MessageUtil.showErrorMes(i18n('i18n.vPositionRole.nullVirtualPositionId'));
            				return false;
  	                	}
  	                	var positionRoleAddAndUpdatePopWindow = Ext.getCmp('positionRoleAddAndUpdatePopWindowId');
  	                	if(Ext.isEmpty(positionRoleAddAndUpdatePopWindow)){
  	                		positionRoleAddAndUpdatePopWindow = Ext.create('PositionRoleAddAndUpdatePopWindow',{
  	  	                		id:'positionRoleAddAndUpdatePopWindowId'
  	  	                	});
  	                	}
  	                	positionRoleAddAndUpdatePopWindow.setTitle(i18n('i18n.authorization.update')+i18n('i18n.vPositionRole.vPositionRoleRelationship'));
  	                	positionRoleAddAndUpdatePopWindow.show();
  	                	//初始化角色查询form
  	                	Ext.getCmp('wholeRoleSelectFormId').getForm().reset();
  	                	buttonControl('UPDATE');
  	                	//为虚拟职位下拉框和虚拟职位描述赋初值
  	                	var virtualPositionSelectFrom = Ext.getCmp('virtualPositionSelectFromId').getForm();
  	                	//为虚拟职位和虚拟职位描述赋初值
    					virtualPositionSelectFrom.findField('virtualPositionName').store.add({
    						virtualPositionId:selection[0].data.virtualPositionId,
    						virtualPositionName:selection[0].data.virtualPositionName
    					});
    					virtualPositionSelectFrom.findField('virtualPositionName').setValue(selection[0].get('virtualPositionId'));
    					virtualPositionSelectFrom.findField('desc').setValue(selection[0].get('virtualPositionDesc'));
    					//加载待分配角色列表
                		Ext.getCmp('wholeRoleSelectGridId').store.load({
                			params:{
                				'virtualPositionId':virtualPositionId,
                				'roleName':'',
                				'isMap':'NO'
                			}
                		});
                		//加载已分配角色列表
                		Ext.getCmp('selectedRoleGridId').store.load({
                			params:{
                				'virtualPositionId':virtualPositionId,
                				'roleName':'',
                				'isMap':'YES'
                			}
                		});
  	                }
  	            }, {//删除按钮
  					xtype:'button',
  					id:'deleteBtnId',
  	                text : i18n('i18n.authorization.delete'),
  	                disabled:true,
  	                handler : function(t) {
  	                	//获取选中的记录
  	                	var selection = Ext.getCmp('positionRoleGridPanelId').getSelectionModel().getSelection();
  	                	if(selection.length != 1){
  	                		MessageUtil.showErrorMes(i18n('i18n.authorization.operation_message'));
            				return false;
  	                	}
  	                	//待删除的虚拟职位ID列表
	  	  				var virtualPositionId = selection[0].get('virtualPositionId');
  	                	if(Ext.isEmpty(virtualPositionId)){
  	                		MessageUtil.showErrorMes(i18n('i18n.vPositionRole.nullVirtualPositionId'));
            				return false;
  	                	}
	  	  				MessageUtil.showQuestionMes(i18n('i18n.vPositionRole.isSureToDelete'), function(e) {
							if (e == 'yes') {
								var successFn = function(json){
			  	  					t.enable();
			  	  					Ext.getCmp('positionRoleGridPanelId').store.loadPage(1);
			  	  		    		MessageUtil.showInfoMes(i18n('i18n.authorization.deleteSuccess'));
			  	  		    	};
			  	  		    	var failureFn = function(json){
			  	  		    		t.enable();
			  	  		    		if(Ext.isEmpty(json)){
			  	  		    			MessageUtil.showErrorMes(i18n('i18n.authorization.deleteFailed'));
			  	  		    		}
			  	  		    		else{
			  	  		    			MessageUtil.showErrorMes(json.message);
			  	  		    		}
			  	  		    	};
			  	  		    	var param = {
			  	  		    		'virtualPositionId':virtualPositionId//待删除虚拟职位的id
			  	      			};
			  	  		    	t.disable();
			  	  		    	VirtualPositionToRoleData.prototype.deleteVPositionAndRoleRelationship(param, successFn, failureFn); 
							}
	  	  				});
  	               }
  	            }, {//查看详情按钮
  					xtype:'button',
  					id:'detailBtnId',
  					disabled:true,
  	                text : i18n('i18n.authorization.searchInfoAll'),//查看详情
  	                handler : function() {
  	                	//获取选中的记录
  	                	var selection = Ext.getCmp('positionRoleGridPanelId').getSelectionModel().getSelection();
  	                	if(selection.length != 1){
  	                		MessageUtil.showErrorMes(i18n('i18n.authorization.operation_message'));
            				return false;
  	                	}
  	                	//待查看的虚拟职位ID
	  	  				var virtualPositionId = selection[0].get('virtualPositionId');
  	                	if(Ext.isEmpty(virtualPositionId)){
  	                		MessageUtil.showErrorMes(i18n('i18n.vPositionRole.nullVirtualPositionId'));
            				return false;
  	                	}
  	                	var positionRoleAddAndUpdatePopWindow = Ext.getCmp('positionRoleAddAndUpdatePopWindowId');
  	                	if(Ext.isEmpty(positionRoleAddAndUpdatePopWindow)){
  	                		positionRoleAddAndUpdatePopWindow = Ext.create('PositionRoleAddAndUpdatePopWindow',{
  	  	                		id:'positionRoleAddAndUpdatePopWindowId'
  	  	                	});
  	                	}
  	                	positionRoleAddAndUpdatePopWindow.setTitle(i18n('i18n.vPositionRole.detail')+i18n('i18n.vPositionRole.vPositionRoleRelationship'));
  	                	positionRoleAddAndUpdatePopWindow.show();
  	                	buttonControl('WATCHONLY');
  	                	//为虚拟职位下拉框和虚拟职位描述赋初值
  	                	var virtualPositionSelectFrom = Ext.getCmp('virtualPositionSelectFromId').getForm();
    					virtualPositionSelectFrom.findField('virtualPositionName').store.add({
    						virtualPositionId:selection[0].data.virtualPositionId,
    						virtualPositionName:selection[0].data.virtualPositionName
    					});
    					virtualPositionSelectFrom.findField('virtualPositionName').setValue(selection[0].get('virtualPositionId'));
    					virtualPositionSelectFrom.findField('desc').setValue(selection[0].get('virtualPositionDesc'));
    					//加载已分配角色列表
                		Ext.getCmp('selectedRoleGridId').store.load({
                			params:{
                				'virtualPositionId':virtualPositionId,
                				'roleName':'',
                				'isMap':'YES'
                			}
                		});
                		//初始化未分配角色列表
                		Ext.getCmp('wholeRoleSelectGridId').store.removeAll();
  	                }
  	            },{//刷新按钮
  					xtype:'button',
  					text:i18n('i18n.vPositionRole.refresh'),
  					disabled:true,
  					id:'refreshBtnId',
  					handler:function(t){
  						//获取选中的记录
  	                	var selection = Ext.getCmp('positionRoleGridPanelId').getSelectionModel().getSelection();
  	                	if(selection.length != 1){
  	                		MessageUtil.showErrorMes(i18n('i18n.authorization.operation_message'));
            				return false;
  	                	}
  	                	//待查看的虚拟职位ID
	  	  				var virtualPositionId = selection[0].get('virtualPositionId');
  	                	if(Ext.isEmpty(virtualPositionId)){
  	                		MessageUtil.showErrorMes(i18n('i18n.vPositionRole.nullVirtualPositionId'));
            				return false;
  	                	}
  	                	MessageUtil.showQuestionMes(i18n('i18n.vPositionRole.isSureToRefresh')+i18n('i18n.vPositionRole.timeMaybeLong'), function(e) {
							if (e == 'yes') {
								var successFn = function(json){
			  	  					t.enable();
			  	  					if(json.refreshResult == 'YES'){
			  	  						MessageUtil.showInfoMes(i18n('i18n.vPositionRole.refreshSuccess'));
			  	  					}
			  	  					else{
			  	  						MessageUtil.showErrorMes(i18n('i18n.vPositionRole.refreshFailure'));	
									}
			  	  		    	};
			  	  		    	var failureFn = function(json){
			  	  		    		t.enable();
			  	  		    		if(Ext.isEmpty(json)){
			  	  		    			MessageUtil.showErrorMes(i18n('i18n.vPositionRole.refreshFailure'));
			  	  		    		}
			  	  		    		else{
			  	  		    			MessageUtil.showErrorMes(json.message);
			  	  		    		}
			  	  		    	};
			  	  		    	var param = {
			  	  		    		'virtualPositionId':virtualPositionId//待删除虚拟职位的id
			  	      			};
			  	  		    	t.disable();
			  	  		    	VirtualPositionToRoleData.prototype.refreshUserRole(param, successFn, failureFn);
							}}
  	                	);
  						
  					}
  	            }]
  			},{
  				xtype:'middlebuttonpanel' 
  			}];
  		}
  	});
  	
  	/**
  	 * 虚拟职位、角色对应关系列表
  	 * 20131126
  	 * 肖红叶
  	 */
  	Ext.define('PositionRoleGridPanel', {
  	    extend:'SearchGridPanel',
  	    store :null,
  	    columnLines:true,
  	    plugins:null,
  	    selModel : Ext.create('Ext.selection.CheckboxModel',{
  	    	mode:'SINGLE',
			allowDeselect:true,
			listeners:{
				select:function(th,record,index){
					Ext.getCmp('updateBtnId').enable();
					Ext.getCmp('detailBtnId').enable();
					Ext.getCmp('deleteBtnId').enable();
					Ext.getCmp('refreshBtnId').enable();
				},
				deselect:function(th,record,index){
					Ext.getCmp('updateBtnId').disable();
					Ext.getCmp('detailBtnId').disable();
					Ext.getCmp('deleteBtnId').disable();
					Ext.getCmp('refreshBtnId').disable();
				}
			}
  	    }),
  	    columns:null,
  	    viewConfig: { 
  	        forceFit:true 
  	    }, 
  	    initComponent:function(){
  	        var me = this;
  	        var store = Ext.create('VirtualPositionToRoleStore',{
  	        	listeners:{
					beforeload:function(store,operation,e){
						var positionRoleResearchForm = Ext.getCmp('positionRoleResearchPanelId').getForm();
	        			var roles = positionRoleResearchForm.findField('roles').getValue();
	        			var virtualPositionName = positionRoleResearchForm.findField('virtualPositionName').getValue();
	        			var searchType = positionRoleResearchForm.findField('searchType').getValue();
	        			var searchParams = {};
	        			if(searchType === 'VPOSITION'){
	        				if(!Ext.isEmpty(virtualPositionName)){
	                			//查询条件除空格
	        					virtualPositionName = Ext.String.trim(virtualPositionName);
	            			}
	        				searchParams = {
	        						'virtualPositionName':virtualPositionName
	        				};
	        			}
	        			else if(searchType === 'ROLENAME'){
	        				if(!Ext.isEmpty(roles)){
	                			//查询条件除空格
	        					roles = Ext.String.trim(roles);
	            			}
	        				var searchParams = {
	        						'roleName':roles
	        				};
	        			}
	    				Ext.apply(operation,{
	    					params : searchParams
	    				});
					}
				}
  	        });
  	        me.store = store;
  	        this.columns = [{//虚拟职位名称
  	            text : i18n('i18n.vPositionRole.virtualPositionName'),
  	            flex:0.3,
  	            dataIndex : 'virtualPositionName'
  	        },{//对应角色
  	            text : i18n('i18n.vPositionRole.relatedRole'),
  	            flex:0.7,
  	            dataIndex : 'roleDesc',
  	            renderer:function(value){
					if(!Ext.isEmpty(value)){
						return '<span data-qtip="'+value+'">'+value+'</span>';
	            	}
				}
  	        }];
  	        this.dockedItems=[{
  	            xtype:'pagingtoolbar',
  	            store:me.store,
  	            dock:'bottom',
  	            displayInfo : true,
  	            autoScroll : true,
  	            items:[{
  	                text: i18n('i18n.authorization.roleGrid.page_count'),
  	                xtype: 'tbtext'
  	            },Ext.create('Ext.form.ComboBox', {
  	               width:          window.screen.availWidth*0.0391,
  	               value:          '20',
  	               triggerAction:  'all',
  	               forceSelection: true,
  	               editable:       false,
  	               name:           'comboItem',
  	               displayField:   'value',
  	               valueField:     'value',
  	               queryMode:      'local',
  	               store : Ext.create('Ext.data.Store',{
  	                   fields : ['value'],
  	                   data   : [
  	                       {'value':'10'},
  	                       {'value':'15'},
  	                       {'value':'20'},
  	                       {'value':'25'},
  	                       {'value':'40'},
  	                       {'value':'100'}
  	                   ]
  	               }),
  	               listeners:{
  	                    select : {
  	                        fn: function(_field,_value){
  	                            var pageSize = me.store.pageSize;
  	                            var newPageSize = parseInt(_field.value);
  	                            if(pageSize!=newPageSize){
  	                                me.store.pageSize = newPageSize;
  	                                this.up('pagingtoolbar').moveFirst();
  	                            }
  	                        }
  	                    }
  	               }
  	           }),{
  	                text: i18n('i18n.authorization.roleGrid.number'),
  	                xtype: 'tbtext'
  	           }]
  	        }];
  	        this.callParent();
  	        store.loadPage(1);
  	    }
  	});
	
	
    /**
     *将界面显示到viewport中 
     */
    var viewport=Ext.create('Ext.Viewport',{
        layout : 'border',
        autoScroll:true,
        items:[Ext.create('PositionRoleResearchPanel',{
        	id:'positionRoleResearchPanelId',
        	region:'north'
        }),{
        	xtype:'container',
        	region:'center',
        	layout:'border',
        	items:[Ext.create('OperationButtonPanel',{
               	region:'north'
            }),Ext.create('PositionRoleGridPanel',{
            	id:'positionRoleGridPanelId',
        		region:'center'
        	})
        	]
        }]
    });
    viewport.setAutoScroll(false);
    viewport.doLayout();
    
    
/** -----------   新增、修改虚拟职位、角色弹出框开始---------------------*/
    /**
     *虚拟岗位选择框
     *20131126
     *肖红叶
     */
    Ext.define('VirtualPositionSelectFrom',{
    	extend:'TitleFormPanel',  
    	items:null,
    	initComponent:function(){
    		this.items = this.getItems(); 
    		this.callParent();
    	},
    	getItems:function(){
    		var me = this;
    		return [{
    			xtype:'basicfiledset', 
    			layout:{
    				type:'table',
    				columns:3
    			},
    			defaults:{
    				labelWidth : 100,
    		        labelAlign: 'right',
    				width : 280
    			},
    			items:[{//虚拟职位名称选择框
                    xtype: 'queryCombox',
                    name:'virtualPositionName',
                    fieldLabel: i18n('i18n.vPositionRole.virtualPositionName'),
                    queryMode : "local",
                    store:Ext.create('Ext.data.Store',{
                    	model:'VirtualPositionModel'
                    }),
        			editable:false,
        			valueField:'virtualPositionId',
        			displayField:'virtualPositionName',
        			listeners:{
        				expand:function(){
      	                	var virtualPositionSelectWindow = Ext.getCmp('virtualPositionSelectWindowId');
      	                	if(Ext.isEmpty(virtualPositionSelectWindow)){
      	                		virtualPositionSelectWindow = Ext.create('VirtualPositionSelectWindow',{
      	  	                		id:'virtualPositionSelectWindowId'
      	  	                	});
      	                	}
      	                	virtualPositionSelectWindow.show(); 
      	                	Ext.getCmp('virtualPositionSelectGridId').store.loadPage(1);
        				}
        			}
                },{//虚拟职位描述
                	xtype:'displayfield',
                	fieldLabel:i18n('i18n.vPositionRole.virtualPositionDesc'),
                	name:'desc',
                	width:450,
                	colspan:2,
                	cls:'displayField_left',
                	align:'left',
                }]		
    		}];
    	}
    }); 
    
    /**
     *角色查询+候选区
     *20131126
     *肖红叶
     */
    Ext.define('WholeRoleSelectPanel',{
    	extend:'TitleFormPanel', 
    	title:i18n('i18n.vPositionRole.notAllocatedRole'),
    	layout:'border',
    	items:null,
    	initComponent:function(){
    		this.items = this.getItems(); 
    		this.callParent();
    	},
    	getItems:function(){
    		var me = this;
    		return [{
    			xtype:'basicsearchformpanel',
    			id:'wholeRoleSelectFormId',
    			region:'north',
    			layout:{
    				type:'table',
    				columns:2
    			},
    			items:[{
                    xtype: 'textfield',
                    name:'roleName',
                    fieldLabel: i18n('i18n.vPositionRole.roleName'),
                    labelWidth:65,
                    width:180
                },{
                	xtype:'button',
                	margin:'0 0 0 10',
                	text:i18n('i18n.authorization.find'),
                	width:50,
                	handler:function(){
                		var roleName = Ext.getCmp('wholeRoleSelectFormId').getForm().findField('roleName').getValue();
                		if(!Ext.isEmpty(roleName)){
                			//查询条件除空格
            				roleName = Ext.String.trim(roleName);
            			}
                		//加载待分配角色列表
                		var virtualPositionSelectFrom = Ext.getCmp("virtualPositionSelectFromId").getForm();
            			var virtualPositionId = virtualPositionSelectFrom.findField('virtualPositionName').getValue();
                		Ext.getCmp('wholeRoleSelectGridId').store.load({
                			params:{
                				'virtualPositionId':'',
                				'roleName':roleName,
                				'isMap':'NO'
                			},callback: function(records, operation, success){//回调函数
                				if(!Ext.isEmpty(records) && records.length > 0){
                					//已分配角色grid
            						var selectedRoleGrid = Ext.getCmp("selectedRoleGridId");
            						//已分配角色的store
            						var chooseStore = selectedRoleGrid.store;
                					if(!Ext.isEmpty(chooseStore) && chooseStore.getCount() > 0){
                						for(var i=0;i < records.length;i++){//遍历所选记录
                							//如果已分配角色列表中已包含查询结果记录，则待分配角色列表中移除该角色记录
                						    if(chooseStore.find("id",records[i].data.id,0,false,true,true) != -1){
                						    	Ext.getCmp('wholeRoleSelectGridId').store.remove(records[i]);
                						    }
                						}
                					}
                				}
	                		}}
                		);
                	}
                }]		
    		},{
    			xtype:'popupgridpanel',
    			id:'wholeRoleSelectGridId',
    			region:'center',
    			selModel : Ext.create('Ext.selection.CheckboxModel',{mode:'MULTI'}),
    			store:Ext.create('RoleStore'),
    			columns:[{//角色名称
      	            text : i18n('i18n.vPositionRole.roleName'),
      	            flex:0.3,
      	            dataIndex : 'roleName'
      	        },{//角色描述
      	            text : i18n('i18n.vPositionRole.roleDesc'),
      	            flex:0.7,
      	            dataIndex : 'roleDesc'
      	        }]
    		}];
    	}
    });
    
    /**
     *已分配角色区
     *20131126
     *肖红叶
     */
    Ext.define('SelectedRolePanel',{
    	extend:'TitleFormPanel', 
    	title:i18n('i18n.vPositionRole.allocatedRole'),
    	layout:'fit',
    	items:null,
    	initComponent:function(){
    		this.items = this.getItems(); 
    		this.callParent();
    	},
    	getItems:function(){
    		var me = this;
    		return [{//已分配角色列表
    			xtype:'popupgridpanel',
    			id:'selectedRoleGridId',
    			region:'center',
    			selModel : Ext.create('Ext.selection.CheckboxModel',{mode:'MULTI'}),
    			store:Ext.create('RoleStore'),
    			columns:[{
      	            text : i18n('i18n.vPositionRole.roleName'),
      	            flex:0.3,
      	            dataIndex : 'roleName'
      	        },{
      	            text : i18n('i18n.vPositionRole.roleDesc'),
      	            flex:0.7,
      	            dataIndex : 'roleDesc'
      	        }]
    		}];
    	}
    });
    
    /**
     * 新增、修改虚拟岗位、角色映射关系弹出框的移动按钮面板
     * 20131126
     * 肖红叶
     */
    Ext.define('MoveButtonPanel',{
		extend :'PopRightButtonPanel',
		buttonAlign : 'center',
		layout : 'column',
		items : [ {
			columnWidth : 1,
			height : 0,
			xtype : 'container',
			style : 'padding-top:60px;border:none',
			border : false,
			width : '100%',
			hide:true
		},{//选择全部
			columnWidth : 1,
			xtype: 'container',
			style : 'padding-top:60px;border:none',
			border : false,
			width : '100%',
			items : [ {
				xtype : 'button',
				id:'selectAllFromLeftToRightId',
				text : '&gt;&gt;',
				width : '80%',
				handler : function(){
					//待分配角色grid
					var wholeRoleSelectGrid = Ext.getCmp("wholeRoleSelectGridId");
					//得到选中的角色
					var selection = wholeRoleSelectGrid.getSelectionModel().getSelection();
					//已分配角色grid
					var selectedRoleGrid = Ext.getCmp("selectedRoleGridId");
					//待分配角色的store
					var store = wholeRoleSelectGrid.store;
					//已分配角色的store
					var chooseStore = selectedRoleGrid.store;
					for(var i=0;i<store.getCount();i++){//遍历待分配角色的store
						//如果待分配角色列表的store中的记录已经包含在已分配角色列表中
					    if(chooseStore.find("id",store.getAt(i).data.id,0,false,true,true) != -1){
					    	store.remove(store.getAt(i));
					    }
					}
					//向右侧store添加数据
					chooseStore.add(store.data.items);
					if(selection.length > 0){
						//取消表格全选
						wholeRoleSelectGrid.getSelectionModel().deselect(selection[0]);
					}
					store.removeAll();
				}
			} ]
		}, {//选择选中的
			columnWidth : 1,
			border : false,
			width : '100%',
			xtype: 'container',
			style : 'padding-top:20px;border:none',
			items : [ {
				xtype : 'button',
				text : '&nbsp;&gt;&nbsp;',
				id:'selectSomeFromLeftToRightId',
				width : '80%',
				handler : function(){
					//待分配角色grid
					var wholeRoleSelectGrid = Ext.getCmp("wholeRoleSelectGridId");
					//已分配角色grid
					var selectedRoleGrid = Ext.getCmp("selectedRoleGridId");
					//得到选中的角色
					var selection = wholeRoleSelectGrid.getSelectionModel().getSelection();
					//已分配角色的store
					var chooseStore = selectedRoleGrid.store;
					for(var i=0;i<selection.length;i++){//遍历所选记录
					    if(chooseStore.find("id",selection[i].get("id"),0,false,true,true) != -1){//判断是否有重复
					    	MessageUtil.showErrorMes(selection[i].get("roleName")+i18n('i18n.vPositionRole.haveInRight'));
						    return false;
					    }
					}
					//将选中的记录从左边移至右边
					chooseStore.add(selection);
					
					if(selection.length > 0){
						//取消表格全选
						wholeRoleSelectGrid.getSelectionModel().deselect(selection[0]);
						//移除左侧选中的记录
						wholeRoleSelectGrid.store.remove(selection);
					}
				}
			} ]
		}, {//取消选中的
			columnWidth : 1,
			border : false,
			id:'selectSomeFromRightToLeftId',
			width : '100%',
			xtype: 'container',
			style : 'padding-top:20px;border:none',
			items : [ {
				xtype : 'button',
				text : '&nbsp;&lt;&nbsp;',
				width : '80%',
				handler : function(){
					//待分配角色grid
					var wholeRoleSelectGrid = Ext.getCmp("wholeRoleSelectGridId");
					//已分配角色grid
					var selectedRoleGrid = Ext.getCmp("selectedRoleGridId");
					//得到选中的角色
					var selection = selectedRoleGrid.getSelectionModel().getSelection();
					//待分配角色的store
					var chooseStore = wholeRoleSelectGrid.store;
					for(var j=0;j<selection.length;j++){//遍历所选中的角色
						if(chooseStore.find("id",selection[j].get("id"),0,false,true,true) != -1){//判断是否有重复
							selectedRoleGrid.getSelectionModel().deselect(selection[j]);
							selectedRoleGrid.store.remove(selection[j]);
					    }
					}
					//将选中的记录从右边移至左边
					chooseStore.add(selection);
					if(selection.length > 0){
						//取消表格全选
						selectedRoleGrid.getSelectionModel().deselect(selection[0]);
						//移除左侧选中的记录
						selectedRoleGrid.store.remove(selection);
					}
				}
			} ]
		}, {//取消所有选中的
			columnWidth : 1,
			border : false,
			width : '100%',
			xtype: 'container',
			style : 'padding-top:20px;border:none',
			items : [ {
				xtype : 'button',
				id:'selectAllFromRightToLeftId',
				text : '&lt;&lt;',
				width : '80%',
				handler : function(){
					//待分配角色grid
					var wholeRoleSelectGrid = Ext.getCmp("wholeRoleSelectGridId");
					//已分配角色grid
					var selectedRoleGrid = Ext.getCmp("selectedRoleGridId");
					//待分配角色的store
					var store = wholeRoleSelectGrid.store;
					//已分配角色的store
					var chooseStore = selectedRoleGrid.store;
					for(var i=0;i<chooseStore.getCount();i++){//遍历已分配角色的store
					    if(store.find("id",chooseStore.getAt(i).data.id,0,false,true,true) != -1){//判断是否有重复
					    	chooseStore.remove(chooseStore.getAt(i));
					    }
					}
					//向左侧store添加数据
					store.add(chooseStore.data.items);
					//得到选中的角色
					var selection = selectedRoleGrid.getSelectionModel().getSelection();
					if(selection.length > 0){
						//取消表格全选
						selectedRoleGrid.getSelectionModel().deselect(selection[0]);
					}
					chooseStore.removeAll();
				}
			} ]
		}]
	});
    
    /**
     * 新增、修改虚拟岗位、角色映射关系的弹出框
     * 20131126
     * 肖红叶
     */
    Ext.define('PositionRoleAddAndUpdatePopWindow',{
    	extend:'PopWindow',
    	alias : 'widget.basicwindow',
    	cls:'popup',
    	width:820,
    	height:500,
    	modal:true,
    	layout:'border',
    	closeAction:'hide',
    	items:[Ext.create('VirtualPositionSelectFrom',{//创建虚拟职位选择窗体
    		id:'virtualPositionSelectFromId',
    		region:'north'
    	}),{//创建待分配角色panel
    		xtype:'container',
    		region:'center',
    		layout:'border',
    		items:[Ext.create('WholeRoleSelectPanel',{
    			region:'west',
    			width:370
    		}),{//创建移动按钮panel
    			xtype:'container',
    			region:'center',
    			layout:'border',
    			items:[Ext.create('MoveButtonPanel',{
    				region:'west',
    				width:50
    			}),Ext.create('SelectedRolePanel',{//创建已分配角色grid
    				region:'center'
    			})]
    		}]
    	}],
    	buttons:[{//提交按钮
    		text:i18n('i18n.vPositionRole.submit'),
    		id:'submitBtnId',
    		handler:function(t){
    			//虚拟职位选择窗体
    			var virtualPositionSelectFrom = Ext.getCmp("virtualPositionSelectFromId").getForm();
    			var virtualPositionName = virtualPositionSelectFrom.findField('virtualPositionName').getValue();
    			//校验虚拟职位是否非空
    			if(Ext.isEmpty(virtualPositionName)){
    				MessageUtil.showErrorMes(i18n('i18n.virtualPosition.NotSelectVirtualPosition'));
					return false;
    			}
    			//已分配角色列表的store
				var store=Ext.getCmp('selectedRoleGridId').store;
				if(store.getCount()===0){
					MessageUtil.showErrorMes(i18n('i18n.vPositionRole.notSelectRoleList'));
					return false;
				}
				//已分配的角色ID列表
				var roleIdList = new Array();
				if(store.getCount()!= 0){
					for(var i=0;i<store.getCount();i++){//获取已经选择的角色id
						roleIdList[i]=store.getAt(i).data.id;
					}
				}
				var successFn = function(json){
					t.enable();
					virtualPositionSelectFrom.reset();
					Ext.getCmp('wholeRoleSelectFormId').getForm().reset();
		    		t.up('window').hide();
		    		Ext.getCmp('positionRoleGridPanelId').store.loadPage(1);
		    		MessageUtil.showInfoMes(i18n('i18n.authorization.saveSuccess'));
		    	};
		    	var failureFn = function(json){
		    		t.enable();
		    		if(Ext.isEmpty(json)){
		    			MessageUtil.showErrorMes(i18n('i18n.authorization.saveFailed'));
		    		}
		    		else{
		    			MessageUtil.showErrorMes(json.message);
		    		}
		    	};
		    	var param = {
		    		'virtualPositionId':virtualPositionSelectFrom.findField('virtualPositionName').getValue(),//虚拟岗位id
    			    'roleIds':roleIdList//已分配角色id列表
    			};
		    	t.disable();
		    	VirtualPositionToRoleData.prototype.saveVPositionAndRoleRelationship(param, successFn, failureFn);
    		}
    	},{//取消按钮
    		text:i18n('i18n.vPositionRole.cancel'),
    		id:'cancelBtnId',
    		handler:function(t){
    			Ext.getCmp("virtualPositionSelectFromId").getForm().reset();
    			Ext.getCmp('wholeRoleSelectFormId').getForm().reset();
    			Ext.getCmp('selectedRoleGridId').store.removeAll();
    			Ext.getCmp('wholeRoleSelectGridId').store.removeAll();
    			t.up('window').hide();
    		}
    	}],
    	listeners:{
    		hide:function(){
    			Ext.getCmp("virtualPositionSelectFromId").getForm().reset();
    			Ext.getCmp('wholeRoleSelectFormId').getForm().reset();
    			Ext.getCmp('selectedRoleGridId').store.removeAll();
    			Ext.getCmp('wholeRoleSelectGridId').store.removeAll();
    			document.body.scrollLeft=0;
    			document.body.scrollTop=0;
    			document.getElementsByTagName("html")[0].style.overflowY="hidden";
    			document.getElementsByTagName("html")[0].style.overflowX="hidden";
    		}
    	}
    });
    
/** -----------   新增、修改虚拟职位、角色弹出框结束---------------------*/   
/** -----------   虚拟职位名称弹出框开始---------------------*/    
    /**
     *虚拟职位选择区
     *20131126
     *肖红叶
     */
    var virtualPositionForSelectStore = Ext.create('VirtualPositionStore',{
		listeners:{
			beforeload:function(store,operation,e){
				var virtualPositionName = Ext.getCmp('virtualPositionSelectFormId').getForm().findField('virtualPositionName').getValue();
				if(!Ext.isEmpty(virtualPositionName)){
        			//查询条件除空格
					virtualPositionName = Ext.String.trim(virtualPositionName);
    			}
				var searchParams = {
    					'virtualPositionName' : virtualPositionName
    					};
    				Ext.apply(operation,{
    					params : searchParams
    				});
			}
		}
	});
    Ext.define('VirtualPositionSelectPanel',{
    	extend:'TitleFormPanel', 
    	layout:'border',
    	items:null,
    	initComponent:function(){
    		this.items = this.getItems(); 
    		this.callParent();
    	},
    	getItems:function(){
    		var me = this;
    		return [{
    			xtype:'basicsearchformpanel',
    			id:'virtualPositionSelectFormId',
    			region:'north',
    			layout:{
    				type:'table',
    				columns:2
    			},
    			items:[{//虚拟职位名称
                    xtype: 'textfield',
                    fieldLabel: i18n('i18n.vPositionRole.virtualPositionName'),
                    name:'virtualPositionName',
                    labelWidth:80,
                    width:300
                },{//查询按钮
                	xtype:'button',
                	margin:'0 0 0 10',
                	text:i18n('i18n.authorization.find'),
                	width:50,
                	handler:function(){
            			Ext.getCmp('virtualPositionSelectGridId').store.loadPage(1);
                	}
                }]		
    		},{//虚拟职位查询结果列表
    			xtype:'popupgridpanel',
    			id:'virtualPositionSelectGridId',
    			region:'center',
    			store:virtualPositionForSelectStore,
    			listeners:{
    				//虚拟职位列表的双击事件
    				'itemdblclick':function(th,record){
    					var virtualPositionSelectFrom = Ext.getCmp('virtualPositionSelectFromId').getForm();
    					virtualPositionSelectFrom.loadRecord(record);
    					virtualPositionSelectFrom.findField('virtualPositionName').store.add({
    						virtualPositionId:record.data.id,
    						virtualPositionName:record.data.virtualPositionName
    					});
    					virtualPositionSelectFrom.findField('virtualPositionName').setValue(record.data.id);
    					Ext.getCmp('virtualPositionSelectFormId').getForm().reset();
    					this.up('window').hide();
    				}
    			},
    			columns:[{
    				xtype:'rownumberer',
    				header:i18n('i18n.vPositionRole.numberer'),
    				width:40
    			},{
    				header : i18n('i18n.vPositionRole.virtualPositionName'),
      	            flex:0.3,
      	            dataIndex : 'virtualPositionName'
      	        },{
      	        	header : i18n('i18n.vPositionRole.virtualPositionDesc'),
      	            flex:0.7,
      	            dataIndex : 'desc'
      	        }],
      	        dockedItems:[{
					xtype:'pagingtoolbar',
					store:virtualPositionForSelectStore,
					dock:'bottom',
					displayInfo : true,
					autoScroll : true
				}]
    		}];
    	}
    });
    
    /**
     * 虚拟职位选择区弹出框
     * 20131126
     * 肖红叶
     */
    Ext.define('VirtualPositionSelectWindow',{
    	extend:'PopWindow',
    	alias : 'widget.basicwindow',
    	cls:'popup',
    	width:600,
    	height:400,
    	modal:true,
    	layout:'fit',
    	title:i18n('i18n.vPositionRole.vPositionSelect'),
    	closeAction:'hide',
    	items:[Ext.create('VirtualPositionSelectPanel')],
    	listeners:{
    		hide:function(){
    			Ext.getCmp('virtualPositionSelectFormId').getForm().reset();
    			document.body.scrollLeft=0;
    			document.body.scrollTop=0;
    			document.getElementsByTagName("html")[0].style.overflowY="hidden";
    			document.getElementsByTagName("html")[0].style.overflowX="hidden";
    		}
    	}
    });
/** -----------   虚拟职位名称弹出框结束---------------------*/ 
    /**
     * 控制查看详情、修改、新增时弹出框中的按钮等显示隐藏情况
     */
    function buttonControl(order){
    	var virtualPositionSelectFrom = Ext.getCmp('virtualPositionSelectFromId').getForm();
    	if(order === 'WATCHONLY'){
        	//禁用虚拟岗位选择
    		virtualPositionSelectFrom.findField('virtualPositionName').disable();
			virtualPositionSelectFrom.findField('desc').disable();
    		//禁用移动按钮
          	Ext.getCmp('selectAllFromRightToLeftId').hide();
          	Ext.getCmp('selectSomeFromRightToLeftId').hide();
          	Ext.getCmp('selectAllFromLeftToRightId').hide();
          	Ext.getCmp('selectSomeFromLeftToRightId').hide();
          	//隐藏提交、取消按钮
          	Ext.getCmp('submitBtnId').hide();
          	Ext.getCmp('cancelBtnId').hide();
          	//隐藏弹出框中的虚拟职位查询form
          	Ext.getCmp('wholeRoleSelectFormId').hide();
          	Ext.getCmp('wholeRoleSelectFormId').setHeight(0);
    	}
    	else if(order === 'UPDATE'){
    		//禁用虚拟岗位选择
    		virtualPositionSelectFrom.findField('virtualPositionName').disable();
			virtualPositionSelectFrom.findField('desc').disable();
    		//显示移动按钮
          	Ext.getCmp('selectAllFromRightToLeftId').show();
          	Ext.getCmp('selectSomeFromRightToLeftId').show();
          	Ext.getCmp('selectAllFromLeftToRightId').show();
          	Ext.getCmp('selectSomeFromLeftToRightId').show();
          	//显示提交、取消按钮
          	Ext.getCmp('submitBtnId').show();
          	Ext.getCmp('cancelBtnId').show();
          //显示弹出框中的虚拟职位查询form
          	Ext.getCmp('wholeRoleSelectFormId').show();
          	Ext.getCmp('wholeRoleSelectFormId').setHeight(50);
    	}
    	else{
        	//启用虚拟岗位选择
    		virtualPositionSelectFrom.findField('virtualPositionName').enable();
			virtualPositionSelectFrom.findField('desc').enable();
    		//显示移动按钮
          	Ext.getCmp('selectAllFromRightToLeftId').show();
          	Ext.getCmp('selectSomeFromRightToLeftId').show();
          	Ext.getCmp('selectAllFromLeftToRightId').show();
          	Ext.getCmp('selectSomeFromLeftToRightId').show();
          	//显示提交、取消按钮
          	Ext.getCmp('submitBtnId').show();
          	Ext.getCmp('cancelBtnId').show();
          //显示弹出框中的虚拟职位查询form
          	Ext.getCmp('wholeRoleSelectFormId').show();
          	Ext.getCmp('wholeRoleSelectFormId').setHeight(50);
    	}
    }
});