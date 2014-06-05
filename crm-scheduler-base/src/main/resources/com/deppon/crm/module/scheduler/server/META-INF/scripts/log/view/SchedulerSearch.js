/**.
 * <p>
 * 任务调度监控查询界面
 * </p>
 * @author  张斌
 * @时间    2012-08-08
 * </p>
 */
//设置Data层对象
var schedulerData =  new SchedulerData();
var data = [{'key':0,'value':i18n('i18n.scheduler.unOp')},{'key':1,'value':i18n('i18n.scheduler.opSuccess')}
,{'key':2,'value':i18n('i18n.scheduler.opException')}];
 /**.
  * <p>
  * 订单受理主页面</br>
  * </p>
  * @author  张斌
  * @时间    2012-03-19
  */
 Ext.onReady(function() {
     Ext.QuickTips.init();    
     new ProcessOrderView();
 });
Ext.form.Field.prototype.msgTarget='side';

/**.
 * <p>
 * 受理订单页面</br>
 * </p>
 * @author  张斌
 * @时间    2012-03-20
 */
Ext.define('ProcessOrderView',{
 		extend:'Ext.container.Viewport',
 		autoDestroy : true,
// 		layout:{
//				type:'vbox',
//				align:'stretch',
//			},
 		autoScroll:true,
 		id:'processOrderView',
 		/**.
 		 * <p>
 		 * 获取受理界面元素</br>
 		 * </p>
 		 * @author  张斌
 		 * @时间    2012-03-19
 		 */
 		getItems:function(){
 			var me = this;
 			var field = ['code','codeDesc'];
 			//创建gird的columns
 			var columns = [{
 		        xtype: 'rownumberer',
 		        width: 35,
 		        text     : i18n('i18n.scheduler.serialNumber')
 		    },{
 		        text     : i18n('i18n.scheduler.tableName'),
 		        dataIndex:'tableName',
 		        width: 100,
 	            renderer : function(value,index,record){
 	            	var state = record.get('state');
 	            	if(state=='2'||state==2){
 	            		return '<span style="color:red;">'+value+'</span>';
 	            	}else{
 	            		return value;
 	            	}
	            }
 		    },{
 	            text     : i18n('i18n.scheduler.reportTime'),
 	            dataIndex:'reportTime',
 	           width: 135,
 	            renderer : function(value,index,record){
 	            	var date = new Date(value);
 	            	var value = date.format('yyyy-MM-dd hh:mm');
 	            	var state = record.get('state');
 	            	if(state=='2'||state==2){
 	            		return '<span style="color:red;">'+value+'</span>';
 	            	}else{
 	            		return value;
 	            	}
	            }
 	        },
 	        {
 	            text     : i18n('i18n.scheduler.buzDate'),
 	           width: 115,
 	            dataIndex:'buzDate',
 	            renderer : function(value,index,record){
 	            	var date = new Date(value);
 	            	var value =  date.format('yyyy-MM-dd');
 	            	var state = record.get('state');
 	            	if(state=='2'||state==2){
 	            		return '<span style="color:red;">'+value+'</span>';
 	            	}else{
 	            		return value;
 	            	}
	            }
 	        },
 	        {
 	            text     : i18n('i18n.scheduler.crmTime'),
 	            dataIndex:'crmTime',
 	           width: 125,
 	            renderer : function(value,index,record){
 	            	var date = new Date(value);
 	            	var value =  date.format('yyyy-MM-dd hh:mm:ss');
 	            	var state = record.get('state');
 	            	if(state=='2'||state==2){
 	            		return '<span style="color:red;">'+value+'</span>';
 	            	}else{
 	            		return value;
 	            	}
	            }
 	        },
 	        {
 	            text     : i18n('i18n.scheduler.crmEndTime'),
 	            dataIndex: 'crmEndTime',
 	           width: 135,
 	            renderer : function(value,index,record){
 	            	var date = new Date(value);
 	            	var value =  date.format('yyyy-MM-dd hh:mm:ss');
 	            	var state = record.get('state');
 	            	if(state=='2'||state==2){
 	            		return '<span style="color:red;">'+value+'</span>';
 	            	}else{
 	            		return value;
 	            	}
	            }
 	        },
 	        {
 	            text     : i18n('i18n.scheduler.state'),
 	            dataIndex: 'state',
 	           width: 135,
 	            renderer : function(value,index,record){
 	            	if(value=='1'||value==1){
 	            		return i18n('i18n.scheduler.opSuccess');
 	            	}else if(value=='2'||value==2){
 	            		return '<span style="color:red;">'+i18n('i18n.scheduler.opException')+'</sapn>';
 	            	}else{
 	            		return i18n('i18n.scheduler.unOp');
 	            	}
	            }
 	        }];
 			return [new SchedulerQueryForm({'storeId':'schedulerSearchStore'}),
 			        new SearchSchedulerButtonPanel({width:791}),
			 		new SchedulerGrid({'store':schedulerData.getSchedulerSearchStore(),'columns':columns,'storeId':'schedulerSearchStore','model':'MULTI'})]
 		},
 	    initComponent:function(){
 	    	var me = this;
 	    	me.items = me.getItems();
 	    	this.callParent();
 	    }
 	});
/**
 * .
 * <p>
 * 订单查询FORM<br/>
 * </p>
 * 
 * @returns OrderQueryForm 订单查询FORM 的EXT对象
 * @author 张斌
 * @时间 2012-03-19
 */
Ext.define('SchedulerQueryForm', {
		extend:'SearchFormPanel',
		OrderData : null,// 连接ACTION的对象
		storeId:null,
	    /**
		 * .
		 * <p>
		 * 获取ITEMS属性 </br>
		 * </p>
		 * 
		 * @author 张斌
		 * @时间 2012-03-19
		 */
	    searchButtonClick:function(){
	    	var me = this;
	        Ext.data.StoreManager.lookup(me.storeId).loadPage(1);
	    },
		id : 'schedulerQuery_Form',
		/**
		 * .
		 * <p>
		 * 获取ITEMS属性 </br>
		 * </p>
		 * 
		 * @author 张斌
		 * @时间 2012-03-19
		 */
		getItems:function(){
			var me = this;
			var field = ['code','codeDesc'];
			return [{
	            	xtype: 'fieldcontainer',
		            defaultType: 'textfield',
		            layout:{
						type:'table',
						columns:4
					},
					defaultType:'textfield',
					defaults:{
						enableKeyEvents:true,
						labelSeparator:'',
						labelWidth:60,
						width:200
					},
		            items: [{
		                xtype: 'textfield',
	                    name:        'tableName',
	                    fieldLabel: i18n('i18n.scheduler.tableName')
	                }, {
	                    name:        'state',
	                    labelWidth:30,
	                    width:180,
	                    fieldLabel: i18n('i18n.scheduler.state'),
	                    xtype:          'combo',
	                    mode:           'local',
	                    triggerAction:  'all',
	                    forceSelection: true,
	                    editabled:false,
	                    value:2,
	                    displayField:   'value',
	                    valueField:     'key',
	                    queryMode: 'local',
	                    store:DButil.getStore('state',null,['key','value'],data)
		            },{
	                    xtype: 'datefield',
	                    name:         'reportTime',
	                    //value:new Date((new Date).getFullYear(),(new Date).getMonth(),(new Date).getDate(),0,0,0),
	                    format: 'Y-m-d',
	                    labelWidth:80,
	                    fieldLabel: i18n('i18n.scheduler.reportTime')
	                },{
		                xtype     : 'datefield',
	                    name:   'reportEndTime',
	                    //value:new Date((new Date).getFullYear(),(new Date).getMonth(),(new Date).getDate(),0,0,0),
	                    fieldLabel: '-',
		                editable:false,
		                format: 'Y-m-d',
		                width:140,
						labelWidth:5
		            },{
	                    xtype: 'datefield',
	                    name:         'buzDate',
	                    //value:new Date((new Date).getFullYear(),(new Date).getMonth(),(new Date).getDate(),0,0,0),
	                    format: 'Y-m-d',
	                    labelWidth:80,
	                    fieldLabel: i18n('i18n.scheduler.buzDate')
	                },{
		                xtype     : 'datefield',
	                    name:   'buzEndDate',
	                    //value:new Date((new Date).getFullYear(),(new Date).getMonth(),(new Date).getDate(),0,0,0),
	                    fieldLabel: '-',
		                editable:false,
		                format: 'Y-m-d',
		                width:140,
						labelWidth:5
		            },{
	                    xtype: 'datefield',
	                    name:         'crmTime',
	                    //value:new Date((new Date).getFullYear(),(new Date).getMonth(),(new Date).getDate(),0,0,0),
	                    format: 'Y-m-d',
	                    labelWidth:80,
	                    fieldLabel: i18n('i18n.scheduler.reportTime')
	                },{
		                xtype     : 'datefield',
	                    name:   'crmEndTime',
	                    //value:new Date((new Date).getFullYear(),(new Date).getMonth(),(new Date).getDate(),0,0,0),
	                    fieldLabel: '-',
		                editable:false,
		                format: 'Y-m-d',
		                width:140,
						labelWidth:5
		            }]
			}];
		},
		initComponent:function()
		{
			var me = this;
			me.items = me.getItems();
			this.callParent();
		}
});
Ext.define('SearchSchedulerButtonPanel',{
	extend:'NormalButtonPanel',
	items:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'middlebuttonpanel'
		},{
			xtype:'rightbuttonpanel',
			width:260,
			items:[{
	            xtype:'button',
	            text: i18n('i18n.scheduler.search'),
	            handler:function(){
	            	Ext.getCmp('schedulerQuery_Form').searchButtonClick();
	            }
	        },{
	            xtype:'button',
	            text: i18n('i18n.scheduler.reset'),
	            handler:function(){
	            	Ext.getCmp("schedulerQuery_Form").getForm().reset();
	            }
	        }
	        ]
		}];
	}
});
/**
 * .
 * <p>
 * 订单查询GIRD<br/>
 * </p>
 * 
 * @returns OrderGrid 订单查询GIRD 的EXT对象
 * @author 张斌
 * @时间 2012-03-19
 */
Ext.define('SchedulerGrid',{
	extend:'SearchGridPanel',
	id:'schedulerGrid',
	storeId:null,
	height: 600,
	model:'MULTI',
	listeners: {
    	scrollershow: function(scroller) {
    		if (scroller && scroller.scrollEl) {
    				scroller.clearManagedListeners(); 
    				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
    		}
    	}
    },
    //sortableColumns:false,//不能排序
    //enableColumnHide:false,//不能隐藏
    //enableColumnMove:false,//不能移动
	getSelModel:function(){
		var me = this;
		return Ext.create('Ext.selection.CheckboxModel',{
				mode:me.model,
			});
	},
    getBBar:function(){
    	var me = this;
    	return Ext.create('Ext.toolbar.Paging', {
			id : 'pagingToolbar',
			store : me.store,
			displayMsg : i18n('i18n.scheduler.displayMsg'),
			displayInfo : true,
			items:[
		            '-',{
							text: i18n('i18n.scheduler.page_count'),
							xtype: 'tbtext'
						},Ext.create('Ext.form.ComboBox', {
		                   width:          50,
		                   value:          '10',
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
						            	var pageSize = Ext.getCmp('schedulerGrid').store.pageSize;
						            	var newPageSize = parseInt(_field.value);
						            	if(pageSize!=newPageSize){
						            		Ext.getCmp('schedulerGrid').store.pageSize = newPageSize;
						            		Ext.getCmp('pagingToolbar').moveFirst();
						            	}
						            }
						        }
		                   }
		               }),{
							text: i18n('i18n.scheduler.pageNumber'),
							xtype: 'tbtext'
		               }]
		});
    },
    initComponent:function(){
		var me = this;
		me.bbar = me.getBBar();
		me.selModel = me.getSelModel();
		// 增加store的beforeload方法
		Ext.data.StoreManager.lookup(me.storeId).on('beforeload',function(NoAllocationOrderStore,operation,e){
			var searchParams=null;
    		searchParams = {
    				'schedulingControl.tableName':Ext.getCmp('schedulerQuery_Form').getForm().findField('tableName').getValue().trim(),// 表名
    	  			'schedulingControl.state':Ext.getCmp('schedulerQuery_Form').getForm().findField('state').getValue(),// 状态
    	  			'schedulingControl.reportTime':Ext.getCmp('schedulerQuery_Form').getForm().findField('reportTime').getValue(),//报表完成起始时间
    	  			'schedulingControl.buzDate':Ext.getCmp('schedulerQuery_Form').getForm().findField('buzDate').getValue(),// 业务起始时间
    	  			'schedulingControl.crmTime':Ext.getCmp('schedulerQuery_Form').getForm().findField('crmTime').getValue(),//  CRM执行起始时间
    	  			'schedulingControl.crmEndTime':Ext.getCmp('schedulerQuery_Form').getForm().findField('crmEndTime').getValue(),//CRM执行结束时间
    	  			'buzEndDate':Ext.getCmp('schedulerQuery_Form').getForm().findField('buzEndDate').getValue(),//业务结束时间
    	  			'reportEndTime':Ext.getCmp('schedulerQuery_Form').getForm().findField('reportEndTime').getValue()//报表完成结束时间
    	  	};
  			Ext.apply(operation,{
				params : searchParams
			});
  		});
		this.callParent();
	}
});

