/**
 * 工单责任划分页面
 */
Ext.onReady(function(){	
	//初始化提示信息条
	Ext.QuickTips.init();
	
	/**
	 *工单责任划分经理查询条件输入面板form
	 */
	Ext.define('DutyAllocationQueryPanel',{
		extend:'SearchFormPanel', 
		items:null,
		border:0,
		layout:{
			type:'table',
			columns:4
		},
		defaults:{
			labelWidth:70,
			labelAlign:'right',
			width:240,
			margin:'0 5 5 0'
		},
		defaultType:'textfield',
		initComponent:function(){			
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [
			    {//待划分投诉/异常数
			    	xtype:'displayfield',
			    	fieldLabel : i18n('i18n.DutyAllocationView.waitToDivideDuty')+i18n('i18n.DutyAllocationView.spanType')+i18n('i18n.DutyAllocationView.numColon')+'<span style="color: #FF0000">number</span>',
			    	//i18n('i18n.DutyAllocationView.waitToDivideDuty')+i18n('i18n.DutyAllocationView.spanType')+i18n('i18n.DutyAllocationView.numColon')+'<span style="color: #FF0000">number</span>'
					labelWidth:200,
					labelSeparator:'',
					labelAlign:'left',
			    	id:'',
					name : ''
			    },{//编号类型选择
			    	xtype:'combobox',
			    	labelSeparator:'',
			    	fieldLabel:'',
			    	labelWidth:0,
			    	width:120,
			    	store:Ext.create('Ext.data.Store', {
			    	    fields: ['code', 'codeDesc'],
			    	    data : [
			    	        {"code":"001", "codeDesc":i18n('i18n.Duty.resultDutyPanel.dealNumber')},//处理编号
			    	        {"code":"002", "codeDesc":i18n('i18n.Duty.resultDutyPanel.voucherNumber')},//凭证号
			    	        {"code":"003", "codeDesc":i18n('i18n.Duty.resultDutyPanel.rebindNo')}//重复工单码
			    	    ]
			    	}),
			    	queryMode:'local',
			    	editable:false,
			    	displayField:'codeDesc',
					valueField:'code',
					name:'',
					id:''
				},{//编号内容输入
					labelSeparator:'',
					fieldLabel:'',
					labelWidth:2,
			    	width:160,
					name:'',
					id:''
				},{//接入人
					fieldLabel:i18n('i18n.Duty.resultDutyPanel.receiveUser'),//接入人
					name:'',
					id:''
				},{//是否暂存
					xtype:'combobox',
					fieldLabel:i18n('i18n.DutyAllocationView.isTemporary'),//是否暂存
					store:Ext.create('Ext.data.Store', {
			    	    fields: ['code', 'codeDesc'],
			    	    data : [
			    	        {"code":"1", "codeDesc":i18n('i18n.DutyAllocationView.yes')},//是
			    	        {"code":"0", "codeDesc":i18n('i18n.DutyAllocationView.no')}//否
			    	    ]
			    	}),
			    	queryMode:'local',
			    	editable:false,
			    	displayField:'codeDesc',
					valueField:'code',
					name:'',
					id:''
				},{//接入时间label
					xtype:'displayfield',
					fieldLabel:i18n('i18n.Duty.searchDutyPanel.receiveTime'),//接入时间
					labelWidth:115,
			    	width:120
				},{//接入时间combo
					xtype:'datetimefield',
					name:'',
					id:'',
					fieldLabel:'',
					labelSeparator:'',
					format:'Y-m-d H:i:s',
					editable:false,
					labelWidth:2,
			    	width:160
				},{//接入时间到
					xtype:'datetimefield',
					fieldLabel:i18n('i18n.Duty.DutyApproval.FeedbackTimeEnd'),//到
					format:'Y-m-d H:i:s',
					editable:false,
					name:'',
					id:''
				}
		    ];
		}
	});	
	
	//操作按钮面板
	Ext.define('DutyAllocationButtonPanel',{
		extend:'NormalButtonPanel',
		border:false,
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [
			   {
				xtype:'leftbuttonpanel',
				width:330,
				items:[
				  {//责任划分
					  xtype:'button',
					  text:i18n('i18n.Duty.receiveDutyPanel.allocatDuty'),//责任划分
					  handler:function(){
						  //责任划分功能实现区
						  
					  }
				  }
				]
			   },{
				 xtype:'middlebuttonpanel' 
			   },{
				xtype : 'rightbuttonpanel',
				items : [{//查询
				    text:i18n('i18n.duty.search'),
				    xtype:'button',
				    handler:function(){
					  //查询按钮功能实现区
				    		  
				  }
				 }]
			}];
		}
	});
	
	
	/**
	 * 工单责任划分查询结果表格
	 */
	Ext.define('DutyAllocationResultGrid',{
		extend:'SearchGridPanel',
		defaults:{
			align:'center'
		},
		autoScroll:true,
		selModel:Ext.create('Ext.selection.CheckboxModel',{mode:'SIGLE',id:'selModelId'}),
		initComponent:function(){ 
			var me = this;
			//var store = Ext.create('CouponQueryListStore',{id:'couponQueryListStoreId'});
//			store.on('beforeload',function(store,operation,e){					
//			  
//				
//			});	
//			me.store = store;
			me.columns = [
			    {//序号
			    	xtype:'rownumberer',
					header:i18n('i18n.Duty.resultDutyPanel.rownumberer'),
					width:40
			    },{//接入时间
					header:i18n('i18n.Duty.searchDutyPanel.receiveTime'),
					renderer : DpUtil.renderDateTime,
					width:120,
					dataIndex:''
			    },{ //接入人
					header :i18n('i18n.Duty.resultDutyPanel.receiveUser'),
					dataIndex:'',
					width:80
			    },{//凭证号
					header :i18n('i18n.Duty.resultDutyPanel.voucherNumber'),
					dataIndex:'',
					width:100
			    },{//处理编号
					header :i18n('i18n.Duty.resultDutyPanel.dealNumber'),
					dataIndex:'',
					width:100
			    },{//重复工单码
					header :i18n('i18n.Duty.resultDutyPanel.rebindNo'),
					dataIndex:'',
					width:100
			    },{//上报类型
					header :i18n('i18n.duty.reportType'),
					dataIndex:'',
					width:80
			   },{//来电人
					header :i18n('i18n.Duty.resultDutyPanel.caller'),
					dataIndex:'',
					width:80
			    },{//优先级别
			    	header :i18n('i18n.Duty.resultDutyPanel.pririty'),
					dataIndex:'',
					width:80
			    },{//处理状态
			    	header :i18n('i18n.Duty.resultDutyPanel.proStatus'),
					width:100,
					dataIndex:''
			    },{ //上报人
			    	header :i18n('i18n.Duty.resultDutyPanel.reporter'),
					dataIndex:'',
					width:80
			    },{//上报时间
			    	header :i18n('i18n.Duty.resultDutyPanel.reportTime'),
					dataIndex:'',
					renderer : DpUtil.renderDate,
					width:100
			    },{//调查结果
			    	header :i18n('i18n.duty.result.surveyResult'),
					dataIndex:'',
					width:80
			    }
		    ];
			me.dockedItems=[{
				xtype:'pagingtoolbar',
				cls:'pagingtoolbar',
				//store:store,
				dock:'bottom',
				displayInfo : true,
				autoScroll : true,
				items:[{//每页显示
					text: i18n('i18n.pager.prefixText'),
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
	                       {'value':'1'},
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
					text: i18n('i18n.Duty.resultDutyPanel.numPerPageNum'),
					xtype: 'tbtext'
	           }]
		}];
		//添加双击事件
		me.on('itemdblclick',function(th,record,item,index,e,eOpts){
			showDetail();
		});
	    this.callParent();
	   }
	});
	
	/*
	 * 新建一个viewport，用于显示工单责任划分界面
	 * 肖红叶
	 */
	var viewport=Ext.create('Ext.Viewport',{
		layout : 'border',
		items:[
	        {//责任划分查询条件输入面板
	        	xtype:'container',
				region:'north',
				layout:'fit',
				items:[Ext.create('DutyAllocationQueryPanel',{id:'dutyAllocationQueryPanelId'})]
			},{
				xtype:'container',
				region:'center',
				layout:'border',
				items:[
					{//按钮面板
						xtype:'container',
						region:'north',
					    layout:'fit',
					    items:[Ext.create('DutyAllocationButtonPanel',{id:'dutyAllocationButtonPanelId'})]
					},{//责任划分查询结果表格
						xtype:'container',
						region:'center',
			    	    layout:'fit',
			    	    items:[ Ext.create('DutyAllocationResultGrid',{id:'dutyAllocationResultGridId'})]
			        }	
			   ]
			}
	    ]
	});
	
	viewport.setAutoScroll(false);	
	viewport.doLayout();
	
});


