/**
 * 散客升级列表界面
 */
var dictionaryDataControl = (CONFIG.get("TEST")) ? new DictionaryManagerData()
		: new DictionaryManagerData();
// var myMorkFlowManagerDataControl = new MyWorkFlowDealManagerData();
Ext.define('SecondTradeManagerView', {
	extend : 'BasicPanel',
	dictionarySearchForm : null, // 查询条件
	tradeResultGrid : null, // 客户行业查询
	secondTradeGrid : null,
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	initComponent : function() {
		var me = this;
		me.dictionarySearchForm = Ext.create('DictionarySearchForm', {
			'parent' : me
		});
		me.tradeResultGrid = Ext.create('TradeResultGrid', {
			'parent' : me
		});
		me.secondTradeGrid = Ext.create('SecondTradeGrid', {
			'parent' : me
		});
		me.items = [ {
			xtype : 'basicpanel',
			height : 28,
			items : [ me.dictionarySearchForm ]
		}, {
			xtype : 'basicpanel',
			id:'customerIndustry',
			layout : {
				type : 'hbox',
				align : 'stretch'
			},
			flex : 1,
			items : [ me.tradeResultGrid, me.secondTradeGrid ]
		} ];
		me.fbar = me.getFbar();
		me.callParent();
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.PotentialCustEditView.save'),
			id:'save_id',
			scope:me,
			handler:function(btn){
				var udpateData = me.down('searchgridpanel').loadUpdateData();
				var addRecord = udpateData.addRecord;
				var updateRecord=udpateData.updateRecord;
				var deleteRecord=udpateData.deleteRecord;
				if (!Ext.isEmpty(addRecord)||!Ext.isEmpty(updateRecord)
						||!Ext.isEmpty(deleteRecord)) {
					MessageUtil.showQuestionMes(i18n('i18n.secondTrade.insureSave'),function(e){
						if(e == 'yes'){
							btn.setDisabled(true);
							me.down('searchgridpanel').saveSecondTrade(addRecord,updateRecord,deleteRecord);
						}else{
							btn.setDisabled(false);
						}
					},function(){
						btn.setDisabled(false);
					});
				}else{
					Ext.getCmp('trade_grid_panel_id').setButtonDisabled();
					MessageUtil.showErrorMes(i18n('i18n.secondTrade.cannotSubmit'));
					btn.setDisabled(false);
				}
			}
		}];
	}

});
/**
 * 工作流查询条件
 */
Ext.define('DictionarySearchForm', {
	extend : 'SearchFormPanel',
	id : 'myWorkFlowSearchFormId',
	defaultType : 'dptextfield',
	cls:'whitePanel',
	border : false,
	record : null,
	parent : null,
	layout : 'column',
	initComponent : function() {
		var me = this;
		me.items = me.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [ {
			xtype : 'textfield',
			name : 'secondTrade',
			emptyText:i18n('i18n.secondTrade.secondTradeEmtpyText'),
			minLength:1,
			maxLength:20
		}, {
			xtype : 'button',
			text : i18n('i18n.PotentialCustManagerView.search'),
			handler : me.searchDictionay
		} ];
	},
	// 监听按键事件
	searchDictionay : function(field, event) {
		var me = this;
		var text = me.up('form').getForm().findField('secondTrade').getValue();
		if (text.length>20||text.length<1) {
			MessageUtil.showErrorMes(i18n('i18n.secondTrade.secondSearchLength'));
			return;
		}
		var param = {'condition':{'codeDesc':text}};
		var successFn = function(json){
			Ext.getCmp('trade_grid_panel_id').getStore().loadData(json.tradeView);
			Ext.getCmp('secondTradeGrid_id').getStore().removeAll();
			if (!Ext.isEmpty(json.tradeView[0])) {
				Ext.getCmp('secondTradeGrid_id').getStore().loadData(json.tradeView[0].secondTrade);
			}
		};
		var failFn = function(json){
			MessageUtil.showErrorMes(json.message);
		};
		dictionaryDataControl.searchDictionary(param,successFn,failFn);
		Ext.getCmp('trade_grid_panel_id').setButtonDisabled();
	}
});
/**
 * 按钮面板
 */
Ext.define('TradeResultGrid', {
	extend : 'SearchGridPanel',
	id:'trade_grid_panel_id',
	title : i18n('i18n.secondTrade.trade'),
	flex : 1,
	initComponent : function() {
		var me = this;
		me.columns = me.getColumns();
		me.store = dictionaryDataControl.getTradeStore();
		me.listeners=me.getMyListeners();
		me.tbar = me.getTbar();
		this.callParent();
	},
	getTbar : function() {
		var me = this;
		return [ {
			text : i18n('i18n.secondTrade.refresh'),
			xtype : 'button',
			handler : function() {
				me.store.removeAll();
				var params = [ 'TRADE' ];
				initDataDictionary(params);
				me.store.loadData(getDataDictionaryByName(DataDictionary,
						'TRADE').data.items);
				var tradeView = Ext.getCmp('trade_grid_panel_id');
				tradeView.loadSecondTrade(tradeView.getStore().data.items[0]);
				me.setButtonDisabled();
			}
		} ]
	},
	setButtonDisabled:function(){
		Ext.getCmp('secondTradeGrid_id').down('button').setDisabled(true);
		Ext.getCmp('secondTrade_delete_id').setDisabled(true);
	},
	//单击一级行业展示二级行业
	getMyListeners:function(){
		var me = this;
		return {
			itemclick:function(grid,record){
				var me = this;
				var udpateData = me.loadUpdateData();
				var addRecord = udpateData.addRecord;
				var updateRecord=udpateData.updateRecord;
				var deleteRecord=udpateData.deleteRecord;
				if (!Ext.isEmpty(addRecord)||!Ext.isEmpty(updateRecord)
						||!Ext.isEmpty(deleteRecord)) {
					MessageUtil.showQuestionMes(i18n('i18n.secondTrade.insureSave'),function(e){
						if(e == 'yes'){
							me.saveSecondTrade(addRecord,updateRecord,deleteRecord);
						}else{
							me.loadSecondTrade(record);
						}
					},function(){
						me.loadSecondTrade(record);
					});
				}else{
					me.loadSecondTrade(record);
				}
			}	
		}
	},
	loadUpdateData:function(){
		var me = this;
		var secondStoreData = Ext.getCmp('secondTradeGrid_id').getStore();
		for ( var i = secondStoreData.data.items.length-1; i >=0; i--) {
			if (Ext.isEmpty(secondStoreData.data.items[i].get('codeDesc'))||
					Ext.isEmpty(secondStoreData.data.items[i].get('codeDesc').trim())) {
				secondStoreData.remove(secondStoreData.data.items[i]);
			}
		}
		Ext.getCmp('secondTradeGrid_id').down('button').setDisabled(false);
		Ext.getCmp('secondTrade_delete_id').setDisabled(false);
		
		var addRecord = [];
		var updateRecord=[];
		var deleteRecord=Ext.getCmp('secondTradeGrid_id').deleteSecondTrade;
		var afterModifyData = Ext.getCmp('secondTradeGrid_id').getStore().data.items;
		var beforeModifyData=Ext.getCmp('secondTradeGrid_id').beforeModifyData;
		if (!Ext.isEmpty(afterModifyData)) {
			for ( var i = 0; i < afterModifyData.length; i++) {
				if (Ext.isEmpty(afterModifyData[i].get('id'))) {
					addRecord.push(afterModifyData[i].data);
				}
				if (!Ext.isEmpty(beforeModifyData)) {
					for ( var j = 0; j < beforeModifyData.length; j++) {
						if (afterModifyData[i].get('id')==beforeModifyData[j].id
								&&afterModifyData[i].get('codeDesc')!=beforeModifyData[j].codeDesc) {
							updateRecord.push(afterModifyData[i].data);
						}
					}
				}
			}
		}
		return {
				'addRecord' : addRecord,
				'updateRecord' : updateRecord,
				'deleteRecord' : deleteRecord
		};
	},
	saveSecondTrade:function(addRecord,updateRecord,deleteRecord){
		var me = this;
		me.setButtonDisabled();
		var successFn=function(response){
			MessageUtil.showInfoMes(i18n('i18n.PotentialCustEditView.saveSuccess'));
			var rows = me.getSelectionModel().getSelection();
			me.loadSecondTrade(rows[0]);
			Ext.getCmp('save_id').setDisabled(false);
		}
		var failFn=function(response){
			MessageUtil.showErrorMes(response.message);
			Ext.getCmp('save_id').setDisabled(false);
		}
		
		for ( var i = 0; i < addRecord.length; i++) {
			var dictionaryModel = new DictionaryModel(addRecord[i]);
		}

		var secondTradeView = {
			'updateSecondTrade' : updateRecord,
			'deleteSecondTrade' : deleteRecord,
			'addSecondTrade' : addRecord
		};
		
		dictionaryDataControl.saveSecondTrade(secondTradeView,successFn,failFn);
	
	},
	loadSecondTrade:function(record){
		var param = {'condition':{'parentId':record.get('id'),'status':true}};
		var successFn=function(response){
			Ext.getCmp('secondTradeGrid_id').getStore().loadData(response.results);
			Ext.getCmp('secondTradeGrid_id').beforeModifyData=response.results;
			beforeModifyData=Ext.getCmp('secondTradeGrid_id').deleteSecondTrade=[];
		};
		var failFn=function(response){
			beforeModifyData=Ext.getCmp('secondTradeGrid_id').deleteSecondTrade=[];
			MessageUtil.showErrorMes(response.message);
		}
		
		dictionaryDataControl.searchSecondTradeByParentId(param,successFn,failFn);
	},
	getColumns : function() {
		var me = this;
		return [ {
			dataIndex : 'codeDesc',
			header :i18n('i18n.secondTrade.trade'),
			flex : 1
		// 后台查询 查看数据需要
		}, {
			header : '',
			hidden : true,
			dataIndex : 'id'
		}, {
			header : '',
			hidden : true,
			dataIndex : 'parentId'
		}, {
			header : '',
			hidden : true,
			dataIndex : 'code'
		} ];
	}

});

/**
 * 二级行业grid
 */
Ext.define('SecondTradeGrid', {
	extend : 'SearchGridPanel',
	parent : null,
	title : i18n('i18n.secondTrade.secondTrade'),
	id:'secondTradeGrid_id',
	beforeModifyData:null,
	deleteSecondTrade:null,
	addSecondTrade:null,
	updateSecondTrade:null,
	flex : 1,
	listeners:{
		afterrender:function(ths, eOpts){
			var tradeView = Ext.getCmp('trade_grid_panel_id');
			tradeView.loadSecondTrade(tradeView.getStore().data.items[0]);
		}
	},
	initComponent : function() {
		var me = this;
		me.columns = me.getColumns();
		me.store = new DictionaryStore();
		me.cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
			clicksToEdit : 2
		});
		me.tbar = me.getTbar();
		me.plugins = [ me.cellEditing ];
		this.callParent();
	},
	getTbar : function() {
		var me = this;
		var store = me.store;
		return [ {
			text : i18n('i18n.PotentialCustManagerView.add'),
			disabled:true,
			handler : function() {
				var rows=Ext.getCmp('trade_grid_panel_id').getSelectionModel().getSelection();
				// Create a model instance
				var r = Ext.create('DictionaryModel', {
					id : '',
					parentId : rows[0].get('id'),
					code : '',
					codeDesc : ' '
				});
				me.store.insert(store.data.length, r);
			}
		}, {
			text : i18n('i18n.PotentialCustManagerView.delete'),
			disabled:true,
			id:'secondTrade_delete_id',
			handler : function() {
				me.deleteRow();
			}
		}, {
			value : '<span style="color:red;">'+i18n('i18n.secondTrade.selectOneTrade')+'</span>',
			disabled:true,
			xtype:'displayfield'
		} ]
	},
	deleteRow:function(){
		var me =this;
		var row = me.getSelectionModel().getSelection();
		if (!Ext.isEmpty(row[0].get('id'))) {
			me.deleteSecondTrade.push(row[0].data);
		}
		me.store.remove(row);
	},
	getColumns : function() {
		var me = this;
		return [ {
			dataIndex : 'codeDesc',
			header : i18n('i18n.secondTrade.secondTrade'),
			flex : 1,
			editor : {
				maxLength : 9,
				xtype : 'textfield',
				hideTrigger : true,
//				readOnly:true,
				id : 'codeDesc_id',
				maxLength : 20,
				allowBlank : false,
				listeners:{
					blur:function(ths,obj){
						if (Ext.isEmpty(ths.getValue())) {
							me.deleteRow();
						}
					},
					change:function(ths,newValue,oldValue,eOpts ){
						var rows = Ext.getCmp('trade_grid_panel_id').getSelectionModel().getSelection();
						if (Ext.isEmpty(rows)||0>=rows.length) {
							MessageUtil.showErrorMes(i18n('i18n.secondTrade.choseTradeFirst'));
						}
					}
				}
			}
		}, {
			header : '',
			hidden : true,
			dataIndex : 'id'
		}, {
			header : '',
			hidden : true,
			dataIndex : 'parentId'
		}, {
			header : '',
			hidden : true,
			dataIndex : 'code'
		} ];
	}
});

Ext.onReady(function() {
	var params = [ 'TRADE'// 客户行业
	];
	initDataDictionary(params);
	Ext.create('Ext.container.Viewport', {
		layout : 'fit',
		items : [ Ext.create('SecondTradeManagerView') ]
	});
});
