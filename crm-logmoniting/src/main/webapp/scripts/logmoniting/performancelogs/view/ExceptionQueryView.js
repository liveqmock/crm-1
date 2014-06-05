var exceptionData = new ExceptionBasicData();

/**
 * 异常信息设置主Panel
 */
Ext.define('ExceptionManagerPanel',{
	extend:'BasicPanel',
	searchCondition:null, // 查询条件
	searchResult:null, // 查询结果
	exceptionData:null,//数据data
	autoScroll:true,
	layout:{
		type:'vbox',
		align:'stretch'
	},
	initComponent:function(){
		var me = this;
		me.searchCondition = Ext.create('ExceptionSearchForm');
		me.searchResult = Ext.create('SearchResultGrid',{
			'exceptionData':me.exceptionData
		});
		me.buttonBar = me.getButtonBar();
		me.items = me.getItems();
		this.callParent();
	},
	getButtonBar:function(){
		var me = this;
		return new ExceptionButtonPanel({'parent':me});
	},
	getItems:function(){
		var me = this;
		return[{
				xtype:'basicpanel',
				height:70,
				items:[me.searchCondition]
			},{
				xtype:'basicpanel',
				height:36,
				items:[me.buttonBar]
			},{
				xtype:'basicpanel',
				flex:1,
				items:[me.searchResult]
			}];
	}
	});
/**
 * 查询条件面板
 */
Ext.define('ExceptionSearchForm',{
	extend:'SearchFormPanel',
	items:null,
	parent:null,
	record:null,
	layout:'fit',
	id:'excep_searchPanel_id',
	layout : {
		type : 'table',
		columns : 3
	},
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return[{
			xtype:'textfield',
			fieldLabel:i18n('i18n.Logcontrol.moduleName'),//模块名称
			name:'moduleName'
		},{
			xtype:'textfield',
			fieldLabel:i18n('i18n.Logcontrol.errorCode'),//异常编码',
			name:'errorCode'
		},{
			xtype:'textfield',
			fieldLabel:i18n('i18n.Logcontrol.exceptionInfo'),//fieldLabel:'异常信息',
			name:'exceptionInfo'
		}];
	}
});
/**
 * 操作按钮面板（新增，修改，删除）
 */
Ext.define('ExceptionButtonPanel',{
	extend:'NormalButtonPanel',
	parent:null,
	items:null,
	monthlyTimePillarStore:null,
	id:'ExceptionButtonPanel_id',
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'leftbuttonpanel',
			width:430,
			items:[ {
				text:i18n('i18n.Logcontrol.insert'),//新增',
				xtype:'button',
				id:'addButton_id',
				handler:function(button){
					button.setDisabled(true);
					var grid = Ext.getCmp('searchExceptionResultGrid_id');
						Ext.create('AddExceptionWindow',{
							handType:'new',
						}).show();
						button.setDisabled(false);
					
				}
			}, {
				text:i18n('i18n.Logcontrol.delete'),//删除',
				xtype:'button',
				id:'delButton_id',
				handler:function(button){
					me.delExceptionErrorCode(button);
				}
			}, {
				text:i18n('i18n.Logcontrol.update'),//修改',
				id:'updButton_id',
				xtype:'button',
				handler:function(button){
					me.updateShowWindow(button);
				}
			}]
		},{
			xtype:'middlebuttonpanel'
		},{
			xtype:'rightbuttonpanel',
			width:220,
			items:[{
				xtype : 'button',
				text : i18n('i18n.Logcontrol.search'),//查询
				width : 70,
				handler :function(){
					me.searchExceptionResult();
				}
			},{
				xtype : 'button',
				text : i18n('i18n.Logcontrol.reset'),//重置
				width : 70,
			    handler :function(){
			    	me.parent.searchCondition.getForm().reset();
			    }
			}]
		}];
	},
	/**
	 * 异常信息的查询操作
	 */
	searchExceptionResult:function(){
		var me = this;
			me.parent.exceptionData.getExceptionResultStore().loadPage(1);
	},
	/**
	 * 异常信息的删除操作
	 */
	delExceptionErrorCode:function(button){
		var me = this;
		var grid = Ext.getCmp('searchExceptionResultGrid_id');
		button.setDisabled(true);
		var selection = grid.getSelectionModel().getSelection();
		var param = null;
		if(selection.length!= 1){
			MessageUtil.showErrorMes(i18n('i18n.Logcontrol.isChooseOne'));//请选择一条数据'
			button.setDisabled(false);
			return ;
		}else if(selection.length==1){
			var record = new ExceptionResultModel(selection[0].data);
			param = {'id':selection[0].data.id};
		}
		MessageUtil.showQuestionMes(i18n('i18n.Logcontrol.isConfirmDelete'),function(e){//确认删除
			if(e=='yes'){
				var successFn = function(result){
					button.setDisabled(false);
					MessageUtil.showInfoMes(i18n('i18n.Logcontrol.del_success'));//删除成功
					//重新执行查询命令
					Ext.getCmp('ExceptionButtonPanel_id').searchExceptionResult();
				 }
				var failFn = function(result){
					if(!Ext.isEmpty(result.message)){
						MessageUtil.showErrorMes(result.message);
					}else{
						MessageUtil.showErrorMes(i18n('i18n.Logcontrol.getInfo_failed'));//=获取信息失败
					}
					button.setDisabled(false);
				}
				ExceptionErrorCodeControl.delExceptionErrorCode(param,successFn,failFn);
			}
			button.setDisabled(false);
		});
	},
	/**
	 * 异常信息的修改操作
	 */
	updateShowWindow:function(button){
		var grid = Ext.getCmp('searchExceptionResultGrid_id');
		var selection = grid.getSelectionModel().getSelection();
		button.setDisabled(true);
		if(selection.length  != 1){
			MessageUtil.showErrorMes(i18n('i18n.Logcontrol.isChooseOne'));//=请选择一条数据
			button.setDisabled(false);
			return;
		}else if(selection.length == 1){
			var record = new ExceptionResultModel(selection[0].data);
			var exceptionUpdPanel = Ext.create('AddExceptionWindow',{
				record:record,
				handType:'update',
			}).show();
			var actionForm = exceptionUpdPanel.down('panel').getForm();
			actionForm.loadRecord(record);
		}
		
		button.setDisabled(false);
	}
});
/**
 * 异常信息列表查询结果(陈爱春)
 */
Ext.define('SearchResultGrid',{
	extend:'BasicPanel',
	store:null,
	items:null,
	exceptionData:null,
	autoScroll:true,
	parent:null,
	initComponent:function(){
		var me = this;
		me.store = me.exceptionData.initExceptionResultStore(me.beforeLoadTransactionFn);
		me.items = me.getItems();
		me.dockedItems = me.getMyDockedItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [ {
			xtype : 'searchgridpanel',
			id:'searchExceptionResultGrid_id',
			store:me.store,
			selModel:Ext.create('Ext.selection.CheckboxModel'),
			emptyText:i18n('i18n.Logcontrol.noResult'),//=没有查询到的信息
			autoScroll :true,
			columns : [Ext.create('Ext.grid.RowNumberer',{
				width:40,
				text:i18n('i18n.Logcontrol.infoId')//'序号'
					}),
			{
				header:'id',
				dataIndex:'id',
				hidden:true
			},{
				header:i18n('i18n.Logcontrol.createTime'),//'创建时间'
				width:150,
				dataIndex:'createTime',
				renderer:function(value){
					return DpUtil.renderTime(value);
				}
			},{
				header:i18n('i18n.Logcontrol.moduleName'),//'模块名称',
				width:110,
				dataIndex:'moduleName'
			},{
				header:i18n('i18n.Logcontrol.errorCode'),//'异常编码',
				width:110,
				dataIndex:'errorCode'
			},{
				header:i18n('i18n.Logcontrol.exceptionInfo'),//'异常信息',
				width:365,
				dataIndex:'exceptionInfo'
			}]
		}];
		
	},
	beforeLoadTransactionFn:function(store, operation, eOpts){
		var record = new SearchExceptionSetModel();
		var actionListSearchForm = Ext.getCmp('excep_searchPanel_id');//
		if(!Ext.isEmpty(actionListSearchForm)){
			actionListSearchForm.getForm().updateRecord(record);
			//查询参数（模块名称，异常编码和异常信息）
			var searchActionListCondition = record.data;
			var searchParams = {
					'errorCodeCondition.moduleName':searchActionListCondition.moduleName,
					'errorCodeCondition.errorCode':searchActionListCondition.errorCode,
					'errorCodeCondition.exceptionInfo':searchActionListCondition.exceptionInfo,
					};
			Ext.apply(operation,{
				params : searchParams
				}
			);
		}
	},
	//分页条
	getMyDockedItems :function(){ 
		var me = this;
		return [ {
			xtype : 'pagingtoolbar',
			store : me.store,
			dock : 'bottom',
			displayInfo : true
		} ];
	}
});
Ext.onReady(function(){
	Ext.create('Ext.Viewport', {
		layout : {
			type : 'fit'
		},
		items : [ Ext.create('ExceptionManagerPanel',{'exceptionData':exceptionData}) ]
	});
});