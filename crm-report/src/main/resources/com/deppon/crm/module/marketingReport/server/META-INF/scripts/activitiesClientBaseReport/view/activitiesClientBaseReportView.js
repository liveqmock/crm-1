Ext.onReady(function(){

	
Ext.define('searchConditionPanel',{
		extend:'SearchFormPanel',
		id:'searchCustcoditionPanelId',
		items:null,
		border:0,
		layout:{
			type : 'table',
			columns : 3
		},
		defaults:{
			labelWidth:80,
			labelAlign:'right',
			margin:'20 5 20 5'
		},
		initComponent:function(){			
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return[{
					xtype:'combo',
					name:'activityName',
					id:'activityNameId',
					fieldLabel :"营销活动名称",
					allowBlank:false,
					blankText:"营销活动名称不能为空",
					minChars:2,
            		queryDelay:800,
            		forceSelection:true,
            		pageSize: 10,
            		onPageChange: function (toolbar, newPage){
            			
            		},
					store:Ext.create('ActivitInfoStore',{
	                listeners:{
	                    beforeload:function(store, operation, eOpts){
	                        Ext.apply(operation,{
	                            params : {
	                                    'clientBaseDetail.activityName':Ext.getCmp("activityNameId").getRawValue().trim()
	                                }
	                            }
	                        );  
	                    }
	                }
	            }),
	            displayField:'activityName',
            	valueField:'activityId',
            	 listConfig: {
               	 minWidth :300,
               	 pageSize: 10,
                getInnerTpl: function() {
                     return '{activityName}';
                }
            	},
            	listeners:{
                change:function(combo){
                	combo.expand();
                    if(Ext.isEmpty(combo.getValue())){
                        combo.setValue("");
	                    }
	                },
	                expand:function(combo){
				    	if(Ext.isEmpty(combo.getValue())){
				    		combo.getStore().load();
				    	}
				    		
				    },
	            	collapse : function(combo){
	                    clientBase = Ext.getCmp('clientBaseId');
	                    clientBase.reset();
	                    clientBase.getStore().load();
	                    Ext.getCmp('clientDeptId').reset();
	                    Ext.getCmp('clientDeptId').getStore().removeAll();
                	}    
	            }
				},
				{
					xtype:'combo',
					name:'clientBaseName',
					id:'clientBaseId',
					fieldLabel :"客户群名称",
					allowBlank:false,
					forceSelection:true,
					blankText:"客户群名称不能为空",
					minChars:2,
					queryDelay:800,
					store:Ext.create('ClientBaseInfoStore',{
	                listeners:{
		                    beforeload:function(store, operation, eOpts){
		                        Ext.apply(operation,{
		                            params : {
		                                    'clientBaseDetail.activityId':Ext.getCmp("activityNameId").getValue(),
		                                    'clientBaseDetail.clientBaseName':Ext.getCmp("clientBaseId").getRawValue().trim()
		                                }
		                            }
		                        );  
		                    }
		                }
		            }), 
				    displayField:'clientBaseName',
				    valueField:'clientBaseId',
				    listConfig: {
//				    	minWidth :300,
				        loadMask:false
				    }
				},{
					xtype:'combo',
					name:'clientDept',
					id:'clientDeptId',
					fieldLabel :"客户归属部门",
					forceSelection:true,
					minChars:0,
					queryDelay:500,
					pageSize: 10,
					onPageChange: function (toolbar, newPage){
            			Ext.getCmp("clientDeptId").setRawValue(null);
            		},
					store:Ext.create('DeptInfoStore',{
	                listeners:{
		                    beforeload:function(store, operation, eOpts){
		                        Ext.apply(operation,{
		                            params : {
		                                    'clientBaseDetail.activityId':Ext.getCmp("activityNameId").getValue(),
		                                     'clientBaseDetail.deptName':Ext.getCmp("clientDeptId").getRawValue().trim()
		                                    
		                                }
		                            }
		                        );  
		                    }
		                }
		            }),
				    displayField:'deptName',
				    valueField:'deptId',
				    listConfig: {
				    	minWidth :300
//				        loadMask:false
				    },
				    listeners:{
				      change:function(combo){
				      	Ext.getCmp("clientDeptId").expand();
	                    if(Ext.isEmpty(combo.getValue())){
	                        combo.setValue("");
		                    }
	                },
				    expand:function(combo){
				    	if(Ext.isEmpty(combo.getValue())){
				    		combo.getStore().load();
				    	}
				    		
				    }
			    }
			}]
		}
	});	
	
	
	/**
	 * 按钮面板
	 * 
	 */
		Ext.define('ConditionBtnPanel',{
		extend:'NormalButtonPanel',
		border:false,
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();		
		},
		getItems:function(){
			var me = this;
			return [{
				xtype:'leftbuttonpanel',
				width:450,
				items:[{//导出全部
					  text:"导出",
					  xtype:'button',
					  handler:function(btn){
						  btn.disable();
						  if(!Ext.getCmp("searchCustcoditionPanelId").getForm().isValid()){
				    		MessageUtil.showInfoMes("请选择查询条件")
				    		btn.enable();
				    		return
				    	}							 
						  
						  //进度条
						  Ext.MessageBox.show({
								title:"导出",
							    msg:"导出中......",
							    wait:true
						  });
						  var successFn = function(json){
							 window.location.href = "../marketingReport/exportClientBaseReportExcel.action?fileName="
								 +json.fileName+"&filePath="+json.filePath;
							 btn.enable();
							 //隐藏进度条
							 Ext.MessageBox.hide();
							 MessageUtil.showInfoMes(i18n('i18n.coupon.successToExportCouponList'));
						  }
						//客户群名称ID
				    	var cilentBaseId = Ext.getCmp('clientBaseId').getValue();
				    	//营销活动名称
				    	var activityId =  Ext.getCmp('activityNameId').getValue();
				    	//归属部门
				    	var deptId = Ext.getCmp('clientDeptId').getValue();
						  var param = {
						  		'clientBaseDetail.clientBaseId':cilentBaseId,
				    			'clientBaseDetail.activityId':activityId,
				    			'clientBaseDetail.deptId':deptId
						  };
						 var failureFn=function(json){
							btn.enable();
							//隐藏进度条
							Ext.MessageBox.hide();
							if(Ext.isEmpty(json)){
								MessageUtil.showErrorMes('抱歉，导出超时！');
							}
							else{
								MessageUtil.showErrorMes(json.message);
							}

						 }
						 activitiesClientBaseReportStore.prototype.creatClientBaseReportExcel(param,successFn,failureFn);							 			  
					  }
					 }
				  ]
			   },{
				 xtype:'middlebuttonpanel' 
			   },{
				xtype : 'rightbuttonpanel',
				items : [{//查询
				    text:"查询",
				    xtype:'button',
				    handler:function(btn){
				    	if(!Ext.getCmp("searchCustcoditionPanelId").getForm().isValid()){
				    		return
				    	}
				    	Ext.getCmp('ClientBaseDetailInfoGridId').store.loadPage(1);
				    }
				 },{
					 xtype:'button',
					 text:"重置",
					 handler:function(){
						 //重置查询条件
						 Ext.getCmp('searchCustcoditionPanelId').getForm().reset();
					 }
				 }]
			   }];
		}
	});

/**
 * 显示表格
 */

Ext.define('ClientBaseDetailInfoGrid',{
		extend:'SearchGridPanel',
		id:'ClientBaseDetailInfoGridId',
		columns:null,
		autoScroll:true,
		store:null,
		defaults:{
			align:'center',
			width:90
		},
		selModel : Ext.create('Ext.selection.CheckboxModel',{
			mode:'SINGLE',
			allowDeselect:true,
			listeners:{
				select:function(th,record,index){
					//查询详情启用
					Ext.getCmp('detailBtnId').enable();
					if(User.deptId==record.data.clientDeptId&&record.data.clientBaseStatus=='UNUSED'){
						if(User.userId==record.data.createUserId||User.empCode==record.data.principalId){
							Ext.getCmp('updateBtnId').enable();
							Ext.getCmp('deleteBtnId').enable();
						}
					}				
				},
				deselect:function(th,record,index){
					Ext.getCmp('detailBtnId').disable();
					Ext.getCmp('updateBtnId').disable();
					Ext.getCmp('deleteBtnId').disable();
				}
			}
		}),
		initComponent:function(){             
			var me = this;
			var store = Ext.create('ClientBaseDetailModel');
			store.on('beforeload',function(store,operation,e){
				    	//客户群名称ID
				    	var cilentBaseId = Ext.getCmp('clientBaseId').getValue();
				    	//营销活动名称
				    	var activityId =  Ext.getCmp('activityNameId').getValue();
				    	//归属部门
				    	var deptId = Ext.getCmp('clientDeptId').getValue();
				    	searchParams = {
				    			'clientBaseDetail.clientBaseId':cilentBaseId,
				    			'clientBaseDetail.activityId':activityId,
				    			'clientBaseDetail.deptId':deptId
				    		};
				Ext.apply(operation,{
					params :searchParams
				});
			
			});
			me.store = store;
			me.columns = [{//序号
				xtype:'rownumberer',
				width:40,
				align:'center',
				header:"序号"
			},{
				header : "客户归属部门",
				dataIndex:'deptName',
				renderer:function(val,metaData,record){
					if(!Ext.isEmpty(val)){
						var content = val;
						if(val.length >= 21){
							val=val.substr(0,20)+'...';
						}else{
							val= val;
						} 
						return '<a title="'+content+'">'+val+'</a>';
					}			
				}
			},{
				header : "营销活动名称", 
				dataIndex:'clientBaseName',
				renderer:function(val,metaData,record){
					if(!Ext.isEmpty(val)){
						var content = val;
						if(val.length >= 21){
							val=val.substr(0,20)+'...';
						}else{
							val= val;
						} 
						return '<a title="'+content+'">'+val+'</a>';
					}			
				}
			},{
				header : "营销活动客户群名称",
				width:140, 
				dataIndex:'activityName',
				renderer:function(val,metaData,record){
					if(!Ext.isEmpty(val)){
						var content = val;
						if(val.length >= 21){
							val=val.substr(0,20)+'...';
						}else{
							val= val;
						} 
						return '<a title="'+content+'">'+val+'</a>';
					}			
				}
			},{
				header : "客户编码",
				width:70, 
				dataIndex:'clientNum'
			},{
				header : "客户名称",
				width:70, 
				dataIndex:'clientName',
				renderer:function(val,metaData,record){
					if(!Ext.isEmpty(val)){
						var content = val;
						if(val.length >= 21){
							val=val.substr(0,20)+'...';
						}else{
							val= val;
						} 
						return '<a title="'+content+'">'+val+'</a>';
					}			
				}
			},{
				header : "客户类型",
				width:70, 
				dataIndex:'clientType'
			},{
				header : "客户等级",
				width:70, 
				dataIndex:'clientLevel'
			},{
				header : "客户行业",
				width:70, 
				dataIndex:'clientTrade',
				renderer:function(val,metaData,record){
					if(!Ext.isEmpty(val)){
						var content = val;
						if(val.length >= 21){
							val=val.substr(0,20)+'...';
						}else{
							val= val;
						} 
						return '<a title="'+content+'">'+val+'</a>';
					}			
				}
			},{
				header : "精准卡航发货金额",
				width:130, 
				dataIndex:'airCardAmount'
			},{
				header : "德邦快递发货金额",
				width:130,  
				dataIndex:'expressAmount'
			},{
				header : "精准汽运（长）发货金额",
				width:170,  
				dataIndex:'longAmount'
			},{
				header : "精准汽运（短）发货金额",
				width:170, 
				dataIndex:'shortAmount'
			},{
				header : "精准空运发货金额",
				width:130, 
				dataIndex:'airAmount'
			},{
				header : "精准城运发货金额",
				width:130, 
				dataIndex:'cityRanAmount'
			},{
				header : "汽运偏线发货金额",
				width:130, 
				dataIndex:'partialLineAmount'
			}
			];
			me.dockedItems=[{
				xtype:'pagingtoolbar',
				cls:'pagingtoolbar',
				store:store,
				dock:'bottom',
				displayInfo : true,
				autoScroll : true,
				items:[{//每页显示
					text: i18n('i18n.PotentialCustManagerView.SearchResultGrid.page_count'),
					xtype: 'tbtext'
				},Ext.create('Ext.form.ComboBox', {
	               width:window.screen.availWidth*0.0391,
	               triggerAction:'all',
	               forceSelection: true,
	               value:'20',
	               editable:false,
	               name:'comboItem',
	               displayField:'value',
	               valueField:'value',
	               queryMode:'local',
	               store : Ext.create('Ext.data.Store',{
	                   fields : ['value'],
	                   data   : [
	                       {'value':'10'},
	                       {'value':'20'},
	                       {'value':'25'},
	                       {'value':'40'},
	                       {'value':'100'}
	                   ]
	               }),
	               listeners:{
						select : {
				            fn: function(_field,_value){
				            	var pageSize = store.pageSize;
				            	var newPageSize = parseInt(_field.value);
				            	if(pageSize!=newPageSize){
				            		store.pageSize = newPageSize;
				            		this.up('pagingtoolbar').moveFirst();
				            	}
				            }
				        }
	               }
	           }),{//条
					text: i18n('i18n.PotentialCustManagerView.SearchResultGrid.number'),
					xtype: 'tbtext'
	           }]
			}];
			this.callParent();
		}
	});	
	
var viewport=Ext.create('Ext.Viewport',{
	     layout : 'border',
	     autoScroll:true,
	     items:[Ext.create('searchConditionPanel',{
	         region:'north'
	     }),{
	         xtype:'container',
	         region:'center',
	         layout:'border',
	         items:[Ext.create('ConditionBtnPanel',{
	             region:'north'
	         }),Ext.create('ClientBaseDetailInfoGrid',{
	             region:'center'
	         })
	         ]
	     }]
	 });
 
	 viewport.setAutoScroll(false);
	 viewport.doLayout();
	
})