/**
 * 节假日维护页面
 */
Ext.onReady(function(){	
	//初始化提示信息条
	Ext.QuickTips.init();
	
	/**
	 *工作日维护查询条件输入面板form
	 */
	Ext.define('DutyWorkdaySearchPanel',{
		extend:'SearchFormPanel', 
		items:null,
		border:0,
		layout:{
			type:'table',
			columns:3
		},
		defaultType:'datefield',
		initComponent:function(){			
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [
			    {//日期范围
			    	fieldLabel : i18n('i18n.DutyWorkdayMaintainView.dateRange'),
			    	editable:false,
			    	labelWidth:60,
			    	width:240,
			    	margin:'0 5 0 0',
			    	format:'Y-m-d',
			    	id:'',
					name : ''
			    },{//日期范围到
			    	editable:false,
			    	labelWidth:30,
			    	width:240,
			    	margin:'0 10 0 0',
			    	fieldLabel:i18n('i18n.Duty.DutyApproval.FeedbackTimeEnd'),
			    	format:'Y-m-d',
					name:'',
					id:''
				},{//查询按钮
					xtype:'button',
					text:i18n('i18n.duty.search'),
					handler:function(){
						
					}
				}
		    ];
		}
	});	
	
	//工作日维护操作按钮面板
	Ext.define('DutyWorkdayButtonPanel',{
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
				//width:330,
				items:[
				  {//新增节假日
					  xtype:'button',
					  text:i18n('i18n.DutyWorkdayMaintainView.addFestival'),
					  handler:function(){
						  dutyAddWeekendWindow.show();
					  }
				  },{//删除节假日
					  xtype:'button',
					  text:i18n('i18n.DutyWorkdayMaintainView.deleteFestive'),
					  handler:function(){
						  MessageUtil.showQuestionMes(i18n('i18n.DutyWorkdayMaintainView.sure')+'<span style = "color:#ff0000">datetime</span>'+i18n('i18n.DutyWorkdayMaintainView.sureDayChangeToworkday'), function(e) {
				        		if (e == 'yes') {
				        			MessageUtil.showInfoMes(i18n('i18n.DutyWorkdayMaintainView.deleteFestivalSuccess')+'<span style = "color:#ff0000">datetime</span>'+i18n('i18n.DutyWorkdayMaintainView.dayChangeToworkday'));
				        		}
				        	});
					  }
				  }
				]
			   },{
				 xtype:'middlebuttonpanel' 
			   }];
		}
	});
	
	/**
	 * 工作日维护查询结果表格
	 */
	Ext.define('DutyWorkdayResultGrid',{
		extend:'SearchGridPanel',
		defaults:{
			align:'center'
		},
		autoScroll:true,
		selModel:Ext.create('Ext.selection.CheckboxModel'),
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
			    },{//日期
					header:i18n('i18n.DutyWorkdayMaintainView.date'),
					renderer : DutyUtil.renderDateTime,
					flex:0.4,
					dataIndex:''
			    },{ //星期
					header :i18n('i18n.DutyWorkdayMaintainView.week'),
					dataIndex:'',
					flex:0.3
			    },{//日期类型
					header :i18n('i18n.DutyWorkdayMaintainView.weekType'),
					dataIndex:'',
					flex:0.3
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
	    this.callParent();
	   }
	});

	
	/*
	 * 新建一个viewport，用于显示工作日维护界面
	 * 肖红叶
	 */
	var viewport=Ext.create('Ext.Viewport',{
		layout : 'border',
		items:[
	        {//工作日维护查询条件输入面板
	        	xtype:'container',
				region:'north',
				layout:'fit',
				items:[Ext.create('DutyWorkdaySearchPanel',{id:'dutyWorkdaySearchPanelId'})]
			},{
				xtype:'container',
				region:'center',
				layout:'border',
				items:[
					{//按钮面板
						xtype:'container',
						region:'north',
					    layout:'fit',
					    items:[Ext.create('DutyWorkdayButtonPanel',{id:'dutyWorkdayButtonPanelId'})]
					},{//工作日维护查询结果表格
						xtype:'container',
						region:'center',
			    	    layout:'fit',
			    	    items:[ Ext.create('DutyWorkdayResultGrid',{id:'dutyWorkdayResultGridId'})]
			        }	
			   ]
			}
	    ]
	});
	
	viewport.setAutoScroll(false);	
	viewport.doLayout();
	
	/**
	 * 单击”新增节假日“按钮出现的设置节假日页面整体布局panel
	 */
	Ext.define('DutyAddWeekendListPanel', {
		extend : 'BasicFormPanel',
		items:[{
			xtype:'datefield',
			width:240,
			fieldLabel:i18n('i18n.DutyWorkdayMaintainView.addFestival'),
			labelWidth:80,
			format:'Y-m-d',
			editable:false,
			id:'',
			name:'addWeekend'
		}]		  
	});	
	
	/**
	 * 新增节假日弹出窗口
	 */
	Ext.define('DutyAddWeekendWindow',{
		extend:'PopWindow',
		title:i18n('i18n.DutyWorkdayMaintainView.festivalSet'),
		alias : 'widget.basicwindow',
		width:260,
		height:120,
		modal:true,
		layout:'fit',
		closeAction:'hide',
		items:[Ext.create('DutyAddWeekendListPanel',{'id':'dutyAddWeekendListPanelId'})],
		listeners:{
			hide:function(){
				document.body.scrollLeft=0;
				document.body.scrollTop=0;
				document.getElementsByTagName("html")[0].style.overflowY="hidden";
				document.getElementsByTagName("html")[0].style.overflowX="hidden";
			}
		},
		dockedItems: [{
		    xtype: 'toolbar',
		    dock: 'bottom',
		    ui: 'footer',
		    items: [
				'->',
				{//保存
					xtype:'button',
					text:i18n('i18n.DutyWorkdayMaintainView.save'),
					handler:function(){
						var dateTime = Ext.getCmp('dutyAddWeekendListPanelId').getForm().findField('addWeekend');
						if(!Ext.isEmpty(dateTime.getValue())){
							MessageUtil.showInfoMes(i18n('i18n.DutyWorkdayMaintainView.addFestivalSuccess')+'<span style = "color:#ff0000">datetime</span>'+i18n('i18n.DutyWorkdayMaintainView.dayChangeToFestival'));
							Ext.getCmp('dutyAddWeekendListPanelId').getForm().findField('addWeekend').setValue('');
						}
						else{
							MessageUtil.showErrorMes(i18n('i18n.DutyWorkdayMaintainView.selectDate'));
							return false;
						}
						
					}
				},{//取消
					xtype:'button',
					text:i18n('i18n.duty.cancel'),
					handler:function(){
						dutyAddWeekendWindow.close();
					}
			}]
		}]
	});

	var dutyAddWeekendWindow = Ext.getCmp("dutyAddWeekendWindowId");//获取工单详情窗口
	if(!dutyAddWeekendWindow){
		dutyAddWeekendWindow = Ext.create('DutyAddWeekendWindow',{id:'dutyAddWeekendWindowId'});
	}
});

