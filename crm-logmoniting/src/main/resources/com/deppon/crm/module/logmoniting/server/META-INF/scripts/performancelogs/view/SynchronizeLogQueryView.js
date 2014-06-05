
Ext.onReady(function(){
/**
 * 同步日志管理主面板
 * @author 陈爱春
 */
Ext.define('SynchronizeLogManagerPanel',{
	extend:'BasicPanel',
	searchCondition:null, // 查询条件
	searchResult:null, // 查询结果
	synchronizeLogData:null,//数据data
	autoScroll:true,
	layout:{
		type:'vbox',
		align:'stretch'
	},
	initComponent:function(){
		var me = this;
		me.searchCondition = Ext.create('SynchronizeLogSearchForm');
		me.searchResult = Ext.create('SynLogListGrid',{
			'synchronizeLogData':me.synchronizeLogData
		});
		me.buttonBar = me.getButtonBar();
		me.items = me.getItems();
		this.callParent();
	},
	getButtonBar:function(){
		var me = this;
		return new SynLogButtonPanel({'parent':me});
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
Ext.define('SynchronizeLogSearchForm',{
	extend:'SearchFormPanel',
	items:null,
	parent:null,
	record:null,
	layout:'fit',
	id:'synLog_searchPanel_id',
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
			fieldLabel:i18n('i18n.Logcontrol.tableName'),//表名TABLE_NAME
			name:'tableName',
			maxLength:40,
			maxLengthText:i18n('i18n.Logcontrol.maxLength40'),
			allowBlank:false
		},{
			xtype:'textfield',
			fieldLabel:i18n('i18n.Logcontrol.keyWord'),//关键字KEY_WORD
			name:'keyWord',
			maxLength:40,
			maxLengthText:i18n('i18n.Logcontrol.maxLength40'),
			allowBlank:false
		},{
			xtype:'combo',
			fieldLabel:i18n('i18n.Logcontrol.handleType'),//HANDLE_TYPE
			name:'handleType',
			store:{fields:['abbr','name'],
    			data:[
    			   {'abbr':'M','name':'M'},
    			   {'abbr':'N','name':'N'}
           	       ]
    		
    	},
    	queryMode:'local',
    	//value:'',         //默认值
    	forceSelection:true,
    	displayField:'name',
		valueField:'abbr',
		listeners:{
			change:function(field,newValue){
				var me = this;
				if(Ext.isEmpty(newValue)){
					field.setValue(null);
				}
			}
		}
		
		},{
			xtype:'combo',
			fieldLabel:i18n('i18n.Logcontrol.synStatus'),//状态 STATUS
			name:'synStatus',
        	store:{fields:['abbr','name'],
        			data:[
        			   {'abbr':'U','name':'U'},
        			   {'abbr':'D','name':'D'}
               	       ]
        		
        	},
        	queryMode:'local',
        	//value:'U',         //默认值
        	forceSelection:true,
        	displayField:'name',
			valueField:'abbr',
			listeners:{
				change:function(field,newValue){
					var me = this;
					if(Ext.isEmpty(newValue)){
						field.setValue(null);
					}
				}
			}
		},{
			xtype:'datetimefield',
			fieldLabel:i18n('i18n.Logcontrol.synStartTime'),//同步开始时间START_TIME
			format: 'Y-m-d H:00:00',
			id:'synStartTime_id',
			name:'synStartTime',
			allowBlank:false,
			forceSelection:true
		},{
			xtype:'datetimefield',
			fieldLabel:i18n('i18n.Logcontrol.synFinishTime'),//同步结束时间FINISH_TIME
			format: 'Y-m-d H:00:00',
			id:'synFinishTime_id',
			name:'synFinishTime',
			allowBlank:false,
			forceSelection:true
		}];
	}
});
/**
 * 操作按钮面板（）
 * @author 陈爱春
 */
Ext.define('SynLogButtonPanel',{
	extend:'NormalButtonPanel',
	parent:null,
	items:null,
	monthlyTimePillarStore:null,
	id:'SynLogButtonPanel_id',
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
				xtype:'button',
				text:i18n('i18n.Logcontrol.update'),//修改',
				id:'updButton_id',
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
					me.searchSynLogResult();
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
	 * 同步日志的查询操作
	 */
	searchSynLogResult:function(){
		var me = this;
		//校验查询参数是否完整(不完整的时候给出相应提示)
		if(!me.parent.searchCondition.getForm().isValid()){
			MessageUtil.showErrorMes(i18n('i18n.Logcontrol.m_must_fillForm'));//请确认数据是否完整
			return false;
		}else{//通过校验
			me.parent.synchronizeLogData.getSynLogResultStore().loadPage(1);
			return true;
		}
		
	},
	/**
	 * 同步日志的修改操作
	 */
	updateShowWindow:function(button){
		var grid = Ext.getCmp('searchSynLogGrid_id');
		var selection = grid.getSelectionModel().getSelection();
		button.setDisabled(true);
		if(selection.length  != 1){
			MessageUtil.showErrorMes(i18n('i18n.Logcontrol.isChooseOne'));//请选择一条数据
			button.setDisabled(false);
			return;
		}else {
			var record = new SynchronizeLogInfoModel(selection[0].data);
			var synLogUpdPanel = Ext.create('UpdSynchronizeLogWindow',{
				record:record
			}).show();
			var sysLogUpdForm = synLogUpdPanel.down('panel').getForm();
			sysLogUpdForm.loadRecord(record);
		}
		button.setDisabled(false);
	}
});
/**
 * 同步日志查询结果列表
 * @author 陈爱春
 */
Ext.define('SynLogListGrid',{
	extend:'BasicPanel',
	store:null,
	items:null,
	synchronizeLogData:null,
	autoScroll:true,
	parent:null,
	initComponent:function(){
		var me = this;
		me.store = me.synchronizeLogData.initSynLogResultStore(me.beforeLoadTransactionFn);
		me.items = me.getItems();
		me.dockedItems = me.getMyDockedItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [ {
			xtype : 'searchgridpanel',
			id:'searchSynLogGrid_id',
			store:me.store,
			selModel:Ext.create('Ext.selection.CheckboxModel'),
			emptyText:i18n('i18n.Logcontrol.noResult'),//没有查询到的信息
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
				header:i18n('i18n.Logcontrol.handleType'),//handleType
				width:120,
				dataIndex:'handleType'
			},{
				header:i18n('i18n.Logcontrol.synStatus'),//synStatus
				width:120,
				dataIndex:'synStatus'
			},{
				header:i18n('i18n.Logcontrol.synStartTime'),//'同步开始时间'
				width:168,
				dataIndex:'synStartTime',
				renderer:function(value){
					return DpUtil.renderTime(value);
				}
			},{
				header:i18n('i18n.Logcontrol.synFinishTime'),//'同步结束时间'
				width:168,
				dataIndex:'synFinishTime',
				renderer:function(value){
					return DpUtil.renderTime(value);
				}
			},{
				header:i18n('i18n.Logcontrol.synTime'),//'同步时间'
				width:168,
				dataIndex:'synTime',
				renderer:function(value){
					return DpUtil.renderTime(value);
				}
			},]
		}];
		
	},
	beforeLoadTransactionFn:function(store, operation, eOpts){
		var record = new SynchronizeLogInfoModel();
		var synLogSearchForm = Ext.getCmp('synLog_searchPanel_id');//获取form
		if(!Ext.isEmpty(synLogSearchForm)){
			synLogSearchForm.getForm().updateRecord(record);
			//查询参数（form表单里面的数据）
			var searchSysLogCondition = record.data;
			var searchParams = {//对应SynLogSearchCondition.java实体类
					'synchronizeLogInfo.tableName':searchSysLogCondition.tableName,
					'synchronizeLogInfo.keyWord':searchSysLogCondition.keyWord,
					'synchronizeLogInfo.handleType':searchSysLogCondition.handleType,
					'synchronizeLogInfo.synStatus':searchSysLogCondition.synStatus,
					'synchronizeLogInfo.synStartTime':searchSysLogCondition.synStartTime,
					'synchronizeLogInfo.synFinishTime':searchSysLogCondition.synFinishTime
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

	Ext.create('Ext.Viewport', {
		layout : {
			type : 'fit'
		},
		items : [ Ext.create('SynchronizeLogManagerPanel',{'synchronizeLogData':new SynchronizeLogData()}) ]
	});
});