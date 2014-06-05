var mustFill = '<span style="color:red">*</span>';
var pnDataControl =  new PlanningData();
/**
 * 
 * <p>
 * Description:查询组件 formPanel<br />
 * </p>
 * @title SearchPlanningFormPanel
 * @author 侯斌飞
 * @version 0.1 2012-12-26
 */
Ext.define('SearchPlanningFormPanel',{
	extend:'SimpBGroundSearchPanel',
	layout:{
		type:'table',columns:2
	},
	defaultType:'textfield',
	defaults:{labelWidth:60,width:240},
	initComponent:function(){
		var me = this;
		Ext.applyIf(me,{
			items:[
				{
					fieldLabel:i18n('i18n.scheduler.pn.instanceId')/*实例组*/,
					id:'instanceId'
				},
				{
					xtype:'btnpanel',
					width:80,
					items:[{
						xtype:'button',scope:me,
						text:i18n('i18n.scheduler.search'),/*查询*/
						handler:me.searchPlanning
					}]
				}
			]
		});
		this.callParent(arguments);
	},
	searchPlanning:function(){
		Ext.getCmp('planningResultGridId').getStore().loadPage(1);
	}
});

/**
 * 
 * <p>
 * Description:操作按钮组件<br />
 * </p>
 * @title OperateButtonPanel
 * @author 侯斌飞
 * @version 0.1 2012-12-26
 */
Ext.define('OperateButtonPanel',{
	extend:'NormalButtonPanel',
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [
			{
				xtype:'leftbuttonpanel',
				items:[
					{xtype:'button',scope:me,handler:me.operate,text:i18n('i18n.scheduler.add')/*新增*/}
					,{xtype:'button',scope:me,handler:me.operate,text:i18n('i18n.scheduler.update')/*修改*/}
				]
			},
			{xtype:'middlebuttonpanel'}
		]
	},
	operate:function(obj){
		var isUpdate = (obj.text === i18n('i18n.scheduler.update'));
		var planning = null;
		/* 修改操作  */
		if(isUpdate){
			var grid = Ext.getCmp('planningResultGridId');
			var selection = grid.getSelectionModel().getSelection();
			if(DpUtil.isEmpty(selection) || selection.length==0){
				MessageUtil.showMessage(i18n('i18n.scheduler.msg_data_select'));return;
			}
			planning = selection[0].data;
		}
		
		var processWindow = Ext.create('ProcessWindow',{
			isUpdate:isUpdate,planning:planning
		});
		/* 显示操作 Window */
		processWindow.show();
	}
});


/**
 * 
 * <p>
 * Description:操作按钮组件<br />
 * </p>
 * @title PlanningResultGrid
 * @author 侯斌飞
 * @version 0.1 2012-12-26
 */
Ext.define('PlanningResultGrid',{
	extend:'SearchGridPanel',autoScroll:true,
	id:'planningResultGridId'
	,initComponent:function(){
		var me = this;
		if(!Ext.isEmpty(pnDataControl)){
			me.store = pnDataControl.initSearchStore(function(store, operation, eOpts){
				var searchParams = {
					'planningCondition.instanceId':Ext.getCmp('instanceId').getValue()
				};
				Ext.apply(operation,{params : searchParams});
			});
		}
		me.dockedItems = me.getMyDockedItems();
		me.columns = me.getColumns();
		me.callParent(arguments);
	}
	,selModel:new Ext.selection.CheckboxModel({mode:'single'})
	,getColumns:function(){
		var me = this;
		return [
			{xtype:'rownumberer',header:i18n('i18n.scheduler.serial'/*序号*/),width:40},
			{header:i18n('i18n.scheduler.pn.instanceId'/*实例组*/),flex:1,dataIndex:'instanceId'},
			{
				header:i18n('i18n.scheduler.pn.scopeType'/*类型*/),width:110,dataIndex:'scopeType',
				renderer:function(value){
					if(Ext.isEmpty(value)){return null;}
					return value==='0'?'任务名称':'任务组';
				}
			},
			{
				header:i18n('i18n.scheduler.pn.accessRule'/*规则*/),width:110,dataIndex:'accessRule',
				renderer:function(value){
					if(Ext.isEmpty(value)){return null;}
					return value==='0'?'拒绝':'执行';
				}
			},
			{header:i18n('i18n.scheduler.pn.scopeName'/*目标*/),width:110,dataIndex:'scopeName'}
		];
	}
	//分页条
	,getMyDockedItems :function(){ 
		var me = this;
		return [{
			xtype : 'pagingtoolbar'
			,plugins:[Ext.create('Ext.ux.PageComboResizer')]
			,store : me.store,dock : 'bottom',displayInfo : true
		}];
	}
});

/**
 * 程序入口
 */
Ext.onReady(function(){
	var viewport = Ext.create('Ext.container.Viewport',{
		padding:'5',
		layout:'border',items:[
			Ext.create('SearchPlanningFormPanel',{region:'north'}),
			{
				xtype:'basicpanel',
				layout:'border',
				region:'center',
				items:[
					Ext.create('OperateButtonPanel',{region:'north'}),
					Ext.create('PlanningResultGrid',{region:'center'})
				]
			}
		]
	});
});

/**
 * 
 * <p>
 * Description规则 处理弹出框 声明<br />
 * </p>
 * @title ProcessWindow
 * @author 侯斌飞
 * @version 0.1 2012-12-26
 */
Ext.define('ProcessWindow', {
    extend:'PopWindow',//x:1
    isUpdate:false,
    width:600,height:150,
    layout:'border',modal:true,
    'closeAction':'destroy',
    initComponent: function() {
        var me = this;
		Ext.applyIf(me,{
    		items:[
    			{
    				xtype:'basicformpanel',
    				region:'center',
    				id:'operateFormId',
    				layout:'column',
					defaultType:'textfield',
					defaults:{labelWidth:60,width:270,allowBlank:false},
    				items:[
						{fieldLabel:mustFill+i18n('i18n.scheduler.pn.instanceId')/*实例组*/,name:'instanceId'},
						
						{
							fieldLabel:mustFill+i18n('i18n.scheduler.pn.accessRule')/*规则*/,name:'accessRule',
							editable:false,xtype:'combobox',store:{
								xtype:'store',
								fields: ['key', 'value'],
							    data : [
							        {'key':'0', 'value':'拒绝'},
							        {'key':'1', 'value':'执行'}
							    ]
							},
						    displayField: 'value',
						    valueField: 'key'
						},{
							fieldLabel:mustFill+i18n('i18n.scheduler.pn.scopeType')/*类型*/,name:'scopeType',
							editable:false,xtype:'combobox',store:{
								xtype:'store',
								fields: ['key', 'value'],
							    data : [
							        {'key':'0', 'value':'任务名称'},
							        {'key':'1', 'value':'任务组'}
							    ]
							},
						    displayField: 'value',
						    valueField: 'key'
						},{
							fieldLabel:mustFill+i18n('i18n.scheduler.pn.scopeName')/*目标*/,name:'scopeName'
						}
					]
    			}
    		],
    		title:i18n('i18n.scheduler.pn.title'+(me.isUpdate===true?'.update':'.add')),
    		buttons:me.getButtons()
    	});
        me.callParent(arguments);
        if(me.isUpdate && me.isUpdate === true){
        	Ext.getCmp('operateFormId').getForm().loadRecord(new PlanningModel(me.planning));
        }
    },
    getButtons:function(){
    	var me = this;
    	return [
    		{
    			text:i18n('i18n.scheduler.delete')/*删除*/,scope:me,
    			hidden:!(me.isUpdate===true),
    			handler:me.fnDelete
    		},{
	    		text:i18n('i18n.scheduler.save')/*保存*/,scope:me,
	    		handler:me.fnSave
	    	},{
	    		text:i18n('i18n.scheduler.cancel')/*取消*/,
	    		listeners:{scope:me,click:function(){me.close();}}
	    	}
    	];
    },
    fnDelete:function(){
    	var me = this;
    	var operateForm = Ext.getCmp('operateFormId').getForm();
    	var params = {
    		'planningCondition.id':me.planning.id
    	};
    	var store = Ext.getCmp('planningResultGridId').getStore();
    	PlanningData.planningDelete(params,function(){
    		me.close();
    		store.remove(store.findRecord('id',params['planningCondition.id']));
    	},function(){});
    }
    ,fnSave:function(){
    	var me = this;
    	var operateForm = Ext.getCmp('operateFormId').getForm();
    	if(!operateForm.isValid()){return;}
    	var params = {
	    		'planning.id':(me.planning?me.planning.id:''),
	    		'planning.instanceId':operateForm.findField('instanceId').getValue(),
	    		'planning.scopeType':operateForm.findField('scopeType').getValue(),
	    		'planning.scopeName':operateForm.findField('scopeName').getValue(),
	    		'planning.accessRule':operateForm.findField('accessRule').getValue()
    	};
    	
    	var store = Ext.getCmp('planningResultGridId').getStore();
    	function successFn(){
    		me.close();
    		Ext.getCmp('planningResultGridId').getStore().loadPage(1);
    	}
    	
    	function failFn(){}
    	if(me.isUpdate && me.isUpdate===true){
    		PlanningData.planningUpdate(params,successFn,failFn);
    		return;
    	}
    	
    	delete params['planning.fid'];
    	PlanningData.planningSave(params,successFn,failFn);
    }
});