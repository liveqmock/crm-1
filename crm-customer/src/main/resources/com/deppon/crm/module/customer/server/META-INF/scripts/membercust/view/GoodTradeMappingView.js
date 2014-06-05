/**
 * 行业品名映射管理
 */
Ext.onReady(function(){
	var keys =['TRADE',
				'SECOND_TRADE',//客户行业
			'ASSOCITION_STATUS'		
	        	];
	initDeptAndUserInfo();
	initDataDictionary(keys);
	Ext.define('SearchForm',{
		extend:'SearchFormPanel',
		items:null,
		defaults:{
			labelWidth:60,
			width:200
		},
		layout:{
			type:'table',
			columns:4
		}, 
		initComponent:function(){
			var me=this;
			me.items=me.getItems();
			this.callParent();
		},
		getItems:function(){
			var me =this;
			return [{
				xtype:'textfield',
				name:'articleName',
				maxLength:100,
				fieldLabel:'品名'
			},{
					xtype:'customertrade',
					width:390,
//					margin:'0 0 0 10',
					height:22,
					trade_labelWidth:45,
					trade_width:175,
					userType:'SEARCH',
					trade_margin:'-10 0 0 10',
					second_trade_margin:'-10 0 0 0',
					trade_name:'firstTradeCode',
					trade_fieldname:i18n('i18n.PotentialCustManagerView.industry'),
					second_trade_labelWidth:65,
					second_trade_width:195,
					second_trade_name:'secondTradeCode',
					second_trade_forceSelect:false,
					trade_forceSelect:false
				},{
					xtype:'combo',
					fieldLabel:'关联状态',
					name:'associatedStatus',
					allowBlank:false,
					store:GTUtil.getStore('associatedStatusId',['code','codeDesc']
						,DataDictionary.ASSOCITION_STATUS),
					queryMode: 'local',
				    displayField: 'codeDesc',
				    editable:false,
				    valueField: 'code'
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
					text:'关联',
					id:'associateBtn',
					handler:function(){
						var selectData = Ext.getCmp('SearchGrid').getSelectionModel().getSelection();
						if(selectData&&selectData.length==1){
							//打开方法
							showWindow('modify');
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
					id:'viewDetailBtn',
					handler:function(){
						showWindow('view');
					}
				}]
			},{
				xtype : 'middlebuttonpanel'
			},{
				xtype:'rightbuttonpanel',
				items:[{
					xtype:'button',
					text:'查询',
					handler:function(){
							Ext.getCmp('SearchGrid').getStore().load();
					}
				},{
					xtype:'button',
					text:'重置',
					handler:function(){
						Ext.getCmp('SearchForm').getForm().reset();
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
				var params = transform(form);
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
				header:'待映射品名',
				align:'center',
				dataIndex:'articleName'
			},{
				header:'发货票数占比',
				align:'center',
				dataIndex:'delayaccounting',
				renderer:function(val){
					return val?parseFloat(val)*100+'%':null;
				}
			},{
				header:'一级行业',
				align:'center',
				dataIndex:'firstTradeCode',
				renderer:function(value){
			        	 return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.TRADE);
			         }
			},{
				header:'二级行业',
				align:'center',
				dataIndex:'secondTradeCode',
				renderer:function(value){
			        	 return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.SECOND_TRADE);
			         }
			},{
				header:'关联人',
				align:'center',
				dataIndex:'associatedPerson'
			},{
				header:'关联时间',
				align:'center',
				dataIndex:'associatedTime',
				renderer:function(val){
					if(val){
						return Ext.Date.format(new Date(val),'y-m-d h:m:s').toString();
					}
					return null;
					
				}
			},
				{
				header:'关联状态',
				width:120,
				align:'center',
				dataIndex:'associatedStatus'
				,renderer:function(value){
			        	 return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.ASSOCITION_STATUS);
			         }
			},{
				header:'备注',
				width:120,
				align:'center',
				dataIndex:'remark'
			}]
		},
		listeners:{
			//双击事件
			itemdblclick : function(grid,row){  
				//
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
			if(!Ext.isEmpty(Ext.data.StoreManager.lookup('goodTradeMappingStoreId'))){
				return Ext.data.StoreManager.lookup('goodTradeMappingStoreId');
			}
			return Ext.create('GoodTradeMappingStore',{
				storeId:'goodTradeMappingStoreId',
				autoLoad:true
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
});


//行业品名关联window
Ext.define('GoodTradeMappingWindow',{
	extend:'PopWindow',
	id:'GoodTradeMappingWindow',
	width:600,
	resizable:false,
	height:300,
	closeAction:'hide',
	title:'行业品名映射',
	layout : {
			type : 'vbox',
			align : 'stretch'
	},
	initComponent:function(){
		var me=this;
		me.items=me.getItems();
		this.callParent();
	},
	operationType:null,//modify view
	getItems:function(){
		var me =this;
		return [
			{
				xtype:'form',
				layout:{
					type:'table',
					columns:2
				},
				defaults:{
					labelWidth:80,
					width:200
				},
				id:'goodtradeMappingFrom',
				items:[{
					xtype:'textfield',
					fieldLabel:'品名',
					name:'articleName',
					disabled:true,
					allowBlank:false
				},{
					xtype:'textfield',
					fieldLabel:'发货票数占比',
					name:'delayaccounting',
					disabled:true,
					allowBlank:false,
					renderer:function(obj,val){
						if(val){
							return val?parseFloat(val)*100+'%':null;
							}
					}
				},{
					xtype:'textfield',
					fieldLabel:'关联人',
					name:'associatedPerson',
					disabled:true
				},{
					xtype:'textfield',
					fieldLabel:'关联时间',
					name:'associatedTime',
					disabled:true,
					renderer:function(obj){
						if(val){
							return '123';
							}
					}
				},{
					xtype:'customertrade',
					name:'customertrade_name',
					width:600,
//					margin:'0 0 0 10',
					height:30,
					trade_labelWidth:80,
					trade_width:200,
					trade_margin:'0 0 0 0',
					second_trade_margin:'0 0 0 110',
					trade_name:'firstTradeCode',
					trade_fieldname:i18n('i18n.PotentialCustManagerView.industry'),
					second_trade_labelWidth:65,
					second_trade_width:195,
					second_trade_name:'secondTradeCode',
					second_trade_forceSelect:false,
					trade_forceSelect:false,
					colspan:2
				},{
					xtype:'textarea',
					fieldLabel:'备注',
					name:'remark',
					height:100,
					width:460,
					maxLength:400,
					colspan:2
				},{
					xtype:'hidden',//客户ID'
					name:'id'
				}]
			},{//
				xtype:'panel',
				height:50,
				border:false,
				items:[{
					xtype:'button',
					text:'提交',
					id:'applyBtn',
					margin:'20 0 0 450',
					handler:function(me){
						var goodtradeMappingFrom =Ext.getCmp('goodtradeMappingFrom').getForm();
						if(!goodtradeMappingFrom.isValid()){
							return;	
						}
						var wolkflowType = me.up('window').operationType;
							msg='您将该品名映射到此行业，请确认是否提交?';
						
						var chooseFn = function(e){
							if(e=='yes'){
								var params = {
									'goodTradeMapping':{
									'articleName':goodtradeMappingFrom.findField('articleName').getValue()
									,'delayaccounting':goodtradeMappingFrom.findField('delayaccounting').getValue()
									,'firstTradeCode':goodtradeMappingFrom.findField('firstTradeCode').getValue()
									,'secondTradeCode':goodtradeMappingFrom.findField('secondTradeCode')
										.getValue()
									,'remark':goodtradeMappingFrom.findField('remark').getValue(),
										'id':goodtradeMappingFrom.findField('id').getValue()
									}
								}
								var successFn = function(json){
									Ext.getCmp('GoodTradeMappingWindow').hide();
									MessageUtil.showInfoMes('映射成功!<BR/>');
									Ext.data.StoreManager.lookup('goodTradeMappingStoreId').load();
								}
								var failFn = function(json){
									MessageUtil.showErrorMes(json.message)
								}
								GTUtil.goodTradeMapping(params,successFn,failFn);
							}
						}
						MessageUtil.showQuestionMes(msg,chooseFn);
					}
				},{
					xtype:'button',
					text:'取消',
					margin:'20 0 0 10',
					handler:function(me){
						Ext.getCmp('goodtradeMappingFrom').getForm().reset();
						me.up('window').hide();
					}
				}]
			}
		]
	}
});
/**
 * 映射窗口
 * @param {} 
 */
var showWindow = function(operationType){
	if(Ext.getCmp('SearchGrid').getSelectionModel()
				.getSelection().length<1){
		MessageUtil.showMessage('至少选择一条记录');
		return;
	}
	if(Ext.getCmp('SearchGrid').getSelectionModel()
				.getSelection().length>1){
		MessageUtil.showMessage('只能选择一条记录');
		return;
	}
	if(Ext.getCmp('GoodTradeMappingWindow')){
		Ext.getCmp('GoodTradeMappingWindow').operationType = operationType;
		Ext.getCmp('GoodTradeMappingWindow').show();
	}else{
		Ext.create('GoodTradeMappingWindow',{
			operationType:operationType,
			listeners:{
				beforeshow:function(me){
					var selectData = Ext.getCmp('SearchGrid')
							.getSelectionModel().getSelection();
					Ext.getCmp('goodtradeMappingFrom').getForm()
						.loadRecord(selectData[0]);
					if(!Ext.isEmpty(selectData[0].get('associatedTime'))){
					Ext.getCmp('goodtradeMappingFrom').getForm().findField('associatedTime').setValue(Ext.Date.format(new Date(selectData[0].get('associatedTime')),'y-m-d h:m:s'));
					}
					
					var ary = Ext.getCmp('goodtradeMappingFrom').down('combo').lastSelection;
					if(ary.length>0){
						Ext.getCmp('goodtradeMappingFrom').down('form').searchSecondTrade(Ext.getCmp('goodtradeMappingFrom').down('combo').lastSelection[0].getId(),false);
						//TODO:
					}
					setSecondTradeValue(Ext.getCmp('goodtradeMappingFrom').down('form').getForm().findField('secondTradeCode'),selectData[0]);
					
					if(!Ext.isEmpty(selectData[0].get('delayaccounting'))){
					Ext.getCmp('goodtradeMappingFrom').getForm().findField('delayaccounting').setValue(parseFloat(selectData[0].get('delayaccounting'))*100+'%');
					}
					if(me.operationType=='modify'){ 
//						setSecondTradeValue(Ext.getCmp('goodtradeMappingFrom').down('form').getForm().findField('secondTradeCode'),selectData[0]);
						Ext.getCmp('goodtradeMappingFrom').down('form').getForm().findField('firstTradeCode').setDisabled(false);
						Ext.getCmp('goodtradeMappingFrom').down('form').getForm().findField('secondTradeCode').setDisabled(false);
						Ext.getCmp('goodtradeMappingFrom').getForm()
							.findField('remark')
							.setDisabled(false);
						Ext.getCmp('applyBtn').setDisabled(false);

					}else{
//						setSecondTradeValue(Ext.getCmp('goodtradeMappingFrom').down('form').getForm().findField('secondTradeCode'),selectData[0]);
						Ext.getCmp('goodtradeMappingFrom').down('form').getForm().findField('firstTradeCode').setDisabled(true);
						Ext.getCmp('goodtradeMappingFrom').down('form').getForm().findField('secondTradeCode').setDisabled(true);
						Ext.getCmp('goodtradeMappingFrom').getForm()
							.findField('remark')
							.setDisabled(true);
						Ext.getCmp('applyBtn').setDisabled(true);
					}
				},
				beforehide:function(me){
					Ext.getCmp('goodtradeMappingFrom').getForm().reset();
				}
			}
		}).show();
	}
};
var transform = function(form){
	var articleName;
	if(form.findField('articleName')){
		articleName = form.findField('articleName').getValue();
	}
	var firstTradeCode;
	if(form.findField('firstTradeCode')){
		firstTradeCode = form.findField('firstTradeCode').getValue();
	}
	var secondTradeCode; 
	if(form.findField('secondTradeCode')){
		secondTradeCode = form.findField('secondTradeCode').getValue();
	}
	var associatedStatus;
	if(form.findField('associatedStatus')){
		associatedStatus = form.findField('associatedStatus').getValue();
	}
	
	return {
		'condition.articleName':articleName
		,'condition.firstTradeCode':firstTradeCode
		,'condition.secondTradeCode':secondTradeCode
		,'condition.associatedStatus':associatedStatus
	}
}
var setSecondTradeValue=function(field,value){
	var codeDesc = DpUtil.changeDictionaryCodeToDescrip(value.get('secondTradeCode'),DataDictionary.SECOND_TRADE);
	if (Ext.isEmpty(codeDesc)) {
		var params =['SECOND_TRADE'];
		 initDataDictionary(params); 
		 codeDesc=DpUtil.changeDictionaryCodeToDescrip(value.get('secondTradeCode'),DataDictionary.SECOND_TRADE);
	}
	field.store.add({'code':value.get('secondTradeCode'),'codeDesc':codeDesc});
	field.setValue(value.get('secondTradeCode'));
}