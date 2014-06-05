 /**
 * 大客户审批管理界面
 */
 //大客户审批历史window的展示方法
 var showDetails = function(record){
 	Ext.data.StoreManager.lookup('KeyCustHistoryWorkflowStoreId')
			.on('beforeload',function(store, operation, eOpts){
		Ext.apply(operation,{
			params : {custId:Ext.getCmp('histpryApproveWindowId').custId}
		});
	});
 	if(Ext.getCmp('histpryApproveWindowId')){
 		Ext.getCmp('histpryApproveWindowId').custId=record.get('custId');
		Ext.getCmp('histpryApproveWindowId').show();
	}else{
		Ext.create('HistpryApproveWindow',{
			id:'histpryApproveWindowId',
			custId:record.get('custId'),
			listeners:{
				show:function(){
					var formData = Ext.getCmp('SearchGrid').getSelectionModel()
						.getSelection()[0];
					Ext.getCmp('historyWorkFlowFormId').getForm().loadRecord(formData);
					Ext.data.StoreManager.lookup('KeyCustHistoryWorkflowStoreId').load();
				}
			}
		}).show();
	}
 }
Ext.onReady(function(){
	var first = true;
	var keys =['COOPERATION_INTENTION'//意愿程度
		,'MEMBER_GRADE'//客户等级
		,'CONTRACT_WORKFLOW_STATUS'//工作流状态
		,'KEYCUST_WORKFLOW_TYPE'//工作流类型
	];
	initDeptAndUserInfo();
	initDataDictionary(keys);
	 /**
	  * 大客户审批管理界面的查询from
	  */
	Ext.define('SearchForm',{
		extend:'SearchFormPanel',
		items:null,
		defaults:{
			labelWidth:70,
			width:195
		},
		layout:{
			type:'table',
			columns:4
		},
		initComponent:function(){
			var me=this;
			me.items=me.getItems();
			this.callParent();
			Ext.getCmp('belongDept').getStore().on('load',function(){
				if(first&&Ext.Array.contains(User.roleids,'1004')){
					Ext.getCmp('belongDept').setValue(User.deptId);
					if(Ext.getCmp('belongDept').getValue()){
						Ext.getCmp('SearchGrid').getStore().load();
					}
				}
				first = false;
			});
			//如果角色包含营业部经理
			if(Ext.Array.contains(User.roleids,'1004')){
				me.getForm().findField('belongDept').setDisabled(true);
			}else{
				me.getForm().findField('belongDept').setDisabled(false);
			}
		},
		getItems:function(){
			var me =this;
			return [{
				xtype:'belongdeptcombox',
				fieldLabel:'所属部门',
				maxLength:100,
				name:'belongDept',
				id:'belongDept',
				forceSelection:true,
				//allowBlank:false,
				listeners:{
					change:function(cbx,newValue){
						
					}
				}
			},{
				xtype:'textfield',
				name:'custNum',
				maxLength:50,
				fieldLabel:'客户编码'
			},{
				xtype:'textfield',
				maxLength:100,
				name:'custName',
				fieldLabel:'客户名称'
			},{
				xtype:'textfield',
				name:'contactNum',
				maxLength:50,
				labelWidth:85,
				fieldLabel:'主联系人编码'
			},{
				xtype:'textfield',
				name:'contactName',
				maxLength:100,
				labelWidth:85,
				fieldLabel:'主联系人姓名'
			},{
				xtype:'combo',
				fieldLabel:'审批状态',
				name:'approvalStatus',
				store:KcUtil.getStore('approvalStatusStoreId',['code','codeDesc']
							,DataDictionary.CONTRACT_WORKFLOW_STATUS),
				queryMode: 'local',
			    displayField: 'codeDesc',
			    valueField: 'code',
			    editable:false
			},{
				xtype:'datefield',
				fieldLabel:'申请时间',
				name:'startDate',
				format:'Y-m-d',
				id:'startDateId'
			},{
				xtype:'datefield',
				fieldLabel:'到',
				name:'endDate',
				id:'endDateId',
				format:'Y-m-d'
			}]
		}
	});
	Ext.define('SearchButtons',{
		extend:'NormalButtonPanel',
		items:null,
		initComponent:function(){
			var me=this;
			me.items=me.getItems();
			this.callParent();
		},
		getItems:function(){
			var me=this;
			return [{
				xtype:'leftbuttonpanel',
				width:320,
				items:[{
					xtype:'button',
					text:'申请',
					id:'applyWorkFlowButton',
					handler:function(){
						if(Ext.getCmp('SearchGrid').getSelectionModel()
									.getSelection().length<1){
							showWorkFlowWindow('special',null,'special');
							return;
						}
						if(Ext.getCmp('SearchGrid').getSelectionModel()
									.getSelection().length>1){
							MessageUtil.showMessage('只能选择一条记录');
							return;
						}
						var successFn = function(json){
							if(json.keyStatusVO.fisKeyCutomer){
								MessageUtil.showMessage('已经成为大客户 ，不能再次提交大客户工作流申请!');
								return;
							}
							if(json.keyStatusVO.fIsInCustomerapproved){
								MessageUtil.showMessage('该客户存在审批中的客户工作流，无法进行大客户工作流申请');
								return;
							}
							if(json.keyStatusVO.fisapproved){
								MessageUtil.showMessage('该客户存在审批中的大客户工作流，无法进行大客户准入申请');
								return;
							}
							if(json.keyStatusVO.fisListIn){
								MessageUtil.showMessage('客户存在大客户待准入列表中，无法进行大客户特殊申请');
								return;
							}else if(json.keyStatusVO.fisListOut){
								MessageUtil.showMessage('客户存在大客户预退出列表中，无法进行大客户特殊申请');
								return;
							}else if(json.keyStatusVO.fisCustExistsRepeat){
								MessageUtil.showMessage('该客户是疑似重复客户，无法进行大客户准入申请');
								return;
							}else{
								if(!json.keyStatusVO.fisAmountAchieve){
									var msg ='该客户在统计周期内不符合大客户的准入和保留工作流申请,是否进行特殊申请?';
									var chooseFn = function(e){
										if(e=='yes'){
											showWorkFlowWindow('special',json.keyStatusVO.amountOfSignMent);
										}
									}
									MessageUtil.showQuestionMes(msg,chooseFn);
								}else{
									showWorkFlowWindow('special',json.keyStatusVO.amountOfSignMent);
								}
							}
						}
						var failFn = function(json){
							MessageUtil.showMessage(json.message)
						}
						params = {custId:Ext.getCmp('SearchGrid').getSelectionModel()
									.getSelection()[0].get('custId')}
						KcUtil.verifyKeyStatus(params,successFn,failFn)
					}
				},{
					xtype:'button',
					text:'客户360视图',
					handler:function(){
						var selectData = Ext.getCmp('SearchGrid').getSelectionModel().getSelection();
						if(selectData&&selectData.length==1){
							KcUtil.cust360View(selectData[0].get('customerNum'));
						}else if(selectData&&selectData.length<1){
							MessageUtil.showMessage('请选择一条记录');
							return;
						}else if(selectData&&selectData.length>1){
							MessageUtil.showMessage('只能选择一条记录');
							return;
						}
					}
				},{
					xtype:'button',
					text:'查看详情',
					handler:function(){
						var selectData = Ext.getCmp('SearchGrid').getSelectionModel().getSelection();
						if(selectData&&selectData.length==1){
							showDetails(selectData[0]);
						}else if(selectData&&selectData.length<1){
							MessageUtil.showMessage('请选择一条记录');
							return;
						}else if(selectData&&selectData.length>1){
							MessageUtil.showMessage('只能选择一条记录');
							return;
						}
					}
				}]
			},{
				xtype : 'middlebuttonpanel'
			},{
				xtype:'rightbuttonpanel',
				items:[{
					xtype:'button',
					text:'查询',
					handler:function(btn){
						if(!Ext.getCmp('SearchForm').getForm().isValid()){
							return;
						}
						var startDate = Ext.getCmp('startDateId').getValue();
						var endDate = Ext.getCmp('endDateId').getValue();
						if(Ext.isEmpty(startDate)&&Ext.isEmpty(endDate)){
						
						}else if(!Ext.isEmpty(startDate)&&!Ext.isEmpty(endDate)){
							if(startDate>endDate){
								MessageUtil.showMessage('申请时间的起始时间不能大于结束时间');
								return;
							}else if(endDate-startDate>90*24*60*60*1000){
								MessageUtil.showMessage('申请时间的起始时间和结束时间不能超过90天');
								return;
							}
						}else{
							MessageUtil.showMessage('申请时间的起始时间和结束时间必须同时输入');
							return;
						}
						var items = Ext.getCmp('SearchForm').getForm().getFields().items;
						var isAllEmpty = true;
						//验证查询条件是否全部为空
						for(var i=0;i<items.length;i++){
							if(items[i].name=='start'||items[i].name=='limit'){
								continue;
							}
							if(!Ext.isEmpty(items[i].getValue())){
								isAllEmpty = false;
								break;
							}
						}
						if(isAllEmpty){
						MessageUtil.showMessage('查询条件不能全部为空');
							return;
						}
						btn.setDisabled(true);
						setTimeout(function(){
							btn.setDisabled(false);
						},2000);
//						if(Ext.isEmpty(Ext.getCmp('SearchForm').getForm()
//								.findField('belongDept').getValue())){
//							MessageUtil.showMessage('所属部门不能为空');
//							return;
//						}
						Ext.getCmp('SearchGrid').getStore().load();
					}
				},{
					xtype:'button',
					text:'重置',
					handler:function(){
						Ext.getCmp('SearchForm').getForm().reset();
						if(Ext.Array.contains(User.roleids,'1004')){
							Ext.getCmp('belongDept').setValue(User.deptId);
						}
					}
				}]
			}]
		}
	});
	Ext.define('SearchGrid',{
		extend:'SearchGridPanel',
		store : null,
		flex:7,
		autoScroll:true,
		initComponent : function() {
			var me = this;
			me.columns = me.getColumns();
			me.selModel = me.getSelModel();
			me.store = me.getGridStore();
			me.bbar = me.getBBar();
			this.callParent();
			me.store.on('beforeload',function(store, operation, eOpts){
				var form = Ext.getCmp('SearchForm').getForm();
				var params = KcUtil.transform(form,'special');
				params['condition.start'] = store.currentPage*store.pageSize-store.pageSize;
				params['condition.limit'] = store.pageSize;
				Ext.apply(operation,{
					params : params
				});
			});
		},
		//获取表头
		getColumns:function(){
			var me = this;
			return [{
				xtype:'rownumberer',
				width:42,
				align:'center',
				text:'序号'
			},{
				header:'所属部门',
				align:'center',
				dataIndex:'deptName'
			},{
				header:'客户编码',
				align:'center',
				dataIndex:'customerNum'
			},{
				header:'客户名称',
				align:'center',
				dataIndex:'customerName'
			},{
				header:'审批状态',
				align:'center',
				dataIndex:'status',
				renderer:function(value){
					return KcUtil.changeCodeToCodeDesc(value,
							DataDictionary.CONTRACT_WORKFLOW_STATUS);
				}
			},{
				header:'申请时间',
				align:'center',
				dataIndex:'createDate',
				renderer:function(value){
					if(!Ext.isEmpty(value)){
		        		return Ext.Date.format(new Date(value),'Y-m-d')
	            	}
	            	return value;
				}
			},{
				header:'审批完成时间',
				align:'center',
				dataIndex:'approvalTime',
				renderer:function(value){
					if(!Ext.isEmpty(value)){
		        		return Ext.Date.format(new Date(value),'Y-m-d')
	            	}
	            	return value;
				}
			},{
				header:'最后更新时间',
				align:'center',
				dataIndex:'modifyDate',
				renderer:function(value){
					if(!Ext.isEmpty(value)){
		        		return Ext.Date.format(new Date(value),'Y-m-d')
	            	}
	            	return value;
				}
			}]
		},
		listeners:{
			//双击事件
			itemdblclick : function(grid,row){  
				KcUtil.cust360View(row.get('customerNum'));
			} 
		},
		//获取选择框
		getSelModel:function() {
			return  Ext.create('Ext.selection.CheckboxModel', {
//				mode:'SINGLE'
			})
		},
		//获取数据
		getGridStore:function(){
			if(!Ext.isEmpty(Ext.data.StoreManager.lookup('keyCustWorkflowStoreId'))){
				return Ext.data.StoreManager.lookup('keyCustWorkflowStoreId');
			}
			return Ext.create('KeyCustWorkflowStore',{
				storeId:'keyCustWorkflowStoreId'
			});
		},
		//获取bbar
		getBBar:function(){
			var me = this;
			return Ext.create('Ext.toolbar.Paging',{
				displayMsg : '共<font color=\"green\"> {2} </font>条记录,当前页记录索引<font color=\"green\"> {0} - {1}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
				displayInfo : true,
				id:'pagingToolbar',
				store:me.store,
				items:[
		            '-',
		        	{
						text: '每页',
						xtype: 'tbtext'
					},Ext.create('Ext.form.ComboBox', {
		               width:          50,
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
								scope : this,
					            fn: function(_field,_value){
					            	var pageSize = Ext.getCmp('monitoringGridPanel').store.pageSize;
					            	var newPageSize = parseInt(_field.value);
					            	if(pageSize!=newPageSize){
					            		Ext.getCmp('monitoringGridPanel').store.pageSize = newPageSize;
					            		Ext.getCmp('pagingToolbar').moveFirst();
					            	}
					            }
					        }
		               }
		           }),{
						text: '条',
						xtype: 'tbtext'
		           }]
			});
		}
	});
	/**
	 * 大客户审批管理界面的大客户审批历史window
	 */
	Ext.define('HistpryApproveWindow',{
		extend:'PopWindow',
		width:755,
		resizable:false,
		closeAction:'hide',
		custId:null,
		title:'大客户审批历史',
		height:600,
		layout : {
				type : 'vbox',
				align : 'stretch'
		},
		items:[{
			xtype:'form',
			margin:'-10 0 0 0',
			height:75,
			id:'historyWorkFlowFormId',
			layout:{
				type:'table',
				columns:3
			},
			defaults:{
				labelWidth:70,
				width:236,
				disabled:true,
				margin:'10 0 0 0'
			},
			items:[{
				xtype:'textfield',
				fieldLabel:'所属部门',
				maxLength:100,
				name:'deptName'
			},{
				xtype:'textfield',
				name:'customerNum',
				maxLength:100,
				fieldLabel:'客户编码'
			},{
				xtype:'textfield',
				maxLength:100,
				name:'customerName',
				fieldLabel:'客户名称'
			},{
				xtype:'combo',
				fieldLabel:'客户等级',
				hideTrigger:true,
				name:'custDegree',
				store:getDataDictionaryByName(DataDictionary,'MEMBER_GRADE'),
				queryMode: 'local',
			    displayField: 'codeDesc',
			    valueField: 'code'
			},{
				xtype:'textfield',
				name:'contactNum',
				maxLength:100,
				fieldLabel:'主联系人编码'
			},{
				xtype:'textfield',
				name:'contactName',
				maxLength:100,
				fieldLabel:'主联系人姓名'
			}]
		},{
			xtype:'grid',
			store : Ext.create('KeyCustWorkflowStore',{
						storeId:'KeyCustHistoryWorkflowStoreId',
						autoLoad:false
					}),
			flex:8,
			autoScroll:true,
			columns:[{
				xtype:'rownumberer',
				width:42,
				align:'center',
				text:'序号'
			},{
				header:'工作流类型',
				align:'center',
				width:70,
				dataIndex:'workFlowType',
				renderer:function(value){
					return KcUtil.changeCodeToCodeDesc(value,
							DataDictionary.KEYCUST_WORKFLOW_TYPE);
				}
			},{
				header:'工作流号',
				align:'center',
				width:150,
				dataIndex:'busino'
			},{
				header:'是否特殊申请',
				align:'center',
				dataIndex:'isSpecialKeyCustomer',
				renderer:function(value){
					if(value){
						return '是'
					}else{
						return '否'
					}
				}
			},{
				header:'审批状态',
				align:'center',
				width:60,
				dataIndex:'status',
				renderer:function(value){
					return KcUtil.changeCodeToCodeDesc(value,
							DataDictionary.CONTRACT_WORKFLOW_STATUS);
				}
			},{
				header:'申请时间',
				align:'center',
				width:80,
				dataIndex:'createDate',
				renderer:function(value){
					if(!Ext.isEmpty(value)){
		        		return Ext.Date.format(new Date(value),'Y-m-d')
	            	}
	            	return value;
				}
			},{
				header:'审批完成时间',
				align:'center',
				dataIndex:'approvalTime',
				renderer:function(value){
					if(!Ext.isEmpty(value)){
		        		return Ext.Date.format(new Date(value),'Y-m-d')
	            	}
	            	return value;
				}
			},{
				header:'最后更新时间',
				width:120,
				align:'center',
				dataIndex:'modifyDate',
				renderer:function(value){
					if(!Ext.isEmpty(value)){
		        		return Ext.Date.format(new Date(value),'Y-m-d')
	            	}
	            	return value;
				}
			}]
		}]
	});
	var SearchForm = Ext.create('SearchForm',{
		id:'SearchForm'
	});
	var SearchButtons = Ext.create('SearchButtons',{
		id:'SearchButtons'
	});
	var SearchGrid = Ext.create('SearchGrid',{
		id:'SearchGrid'
	});
	var view = Ext.create('Ext.container.Viewport',{
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		items:[SearchForm,SearchButtons,SearchGrid]
	});
	if(!Ext.Array.contains(User.roleids,'1004')){
		Ext.getCmp('applyWorkFlowButton').setDisabled(true);
	}
});