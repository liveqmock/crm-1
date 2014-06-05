 /**
 * 预退出大客户列表界面
 */
Ext.onReady(function(){
	var first = true;
	var keys =['COOPERATION_INTENTION'//意愿程度
				,'MEMBER_GRADE'//客户等级
	        	];
	initDeptAndUserInfo();
	initDataDictionary(keys);
	Ext.define('SearchForm',{
		extend:'SearchFormPanel',
		items:null,
		defaults:{
			labelWidth:90,
			width:240
		},
		layout:{
			type:'table',
			columns:3
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
				id:'belongDept',
//				allowBlank:false,
				forceSelection:true,
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
				fieldLabel:'主联系人编码'
			},{
				xtype:'textfield',
				name:'contactName',
				maxLength:100,
				fieldLabel:'主联系人姓名'
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
					text:'退出',
					id:'exitApplyButtonId',
					handler:function(){
						var selectData = Ext.getCmp('SearchGrid').getSelectionModel().getSelection();
						if(selectData&&selectData.length==1){
							if(!KcUtil.verifyRole(User)){
								MessageUtil.showMessage('非营业部经理,无法进行准入申请操作');
								return;
							}
							if(Ext.getCmp('SearchGrid').getSelectionModel().getSelection()[0]
								.get('deptId')!=User.deptId){
								MessageUtil.showMessage('非本部门营业部经理,无法进行准入申请操作');
								return;
							}
							var msg ='是否取消该客户的大客户标记资格？';
							var chooseFn = function(e){
								if(e=='yes'){
									var params = {
										custId:selectData[0].get('custId'),
										deptId:selectData[0].get('deptId')
									}
									var successFn = function(json){
										MessageUtil.showInfoMes('取消成功!');
										Ext.data.StoreManager.lookup('keycustomerStoreId').load();
									}
									var failFn = function(json){
										MessageUtil.showMessage(json.message);
									}
									KcUtil.exitHadlle(params,successFn,failFn);
								}
							}
							MessageUtil.showQuestionMes(msg,chooseFn);
						}else if(selectData&&selectData.length<1){
							MessageUtil.showMessage('请选择一条记录');
							return;
						}else if(selectData&&selectData.length>1){
							MessageUtil.showMessage('请选择一条记录');
							return;
						}
					}
				},{
					xtype:'button',
					text:'保留申请',
					id:'applyWorkFlowButton',
					handler:function(){
						showWorkFlowWindow('out');
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
						btn.setDisabled(true);
						setTimeout(function(){
							btn.setDisabled(false);
						},2000);
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
				var params = KcUtil.transform(form,'out');
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
				header:'客户名称',
				align:'center',
				dataIndex:'customerName'
			},{
				header:'客户编码',
				align:'center',
				dataIndex:'customerNum'
			},{
				header:'主联系人姓名',
				align:'center',
				dataIndex:'contactName'
			},{
				header:'主联系人编码',
				align:'center',
				dataIndex:'contactNum'
			},{
				header:'近三个月发货金额',
				width:120,
				align:'center',
				dataIndex:'amountOfSignMent'
			},{
				header:'客户信用预警次数',
				width:120,
				align:'center',
				dataIndex:'creditTimes'
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
				mode:'SINGLE'
			})
		},
		//获取数据
		getGridStore:function(){
			if(!Ext.isEmpty(Ext.data.StoreManager.lookup('keycustomerStoreId'))){
				return Ext.data.StoreManager.lookup('keycustomerStoreId');
			}
			return Ext.create('KeycustomerStore',{
				storeId:'keycustomerStoreId'
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
					            	var pageSize = Ext.getCmp('SearchGrid').store.pageSize;
					            	var newPageSize = parseInt(_field.value);
					            	if(pageSize!=newPageSize){
					            		Ext.getCmp('SearchGrid').store.pageSize = newPageSize;
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
		Ext.getCmp('exitApplyButtonId').setDisabled(true);
	}
});