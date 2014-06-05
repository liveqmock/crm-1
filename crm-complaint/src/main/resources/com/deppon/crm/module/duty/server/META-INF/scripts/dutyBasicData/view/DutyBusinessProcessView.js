/**
 * 工单责任业务项设置页面
 */


/**
 * 业务项设置增加、删除按钮面板
 */
Ext.define('DutyBasalProcessBtnPanel',{
	extend:'TopPanel',
	reportType:ReportType.complaint,
	gridId:'dutyBusinessProcessGridId',
	initComponent : function() {
		this.items = this.getItems();
		this.callParent(arguments);
	},
	getItems : function() {
		var me = this;
		return [//异常业务项设置
			{
				xtype:'displayfield',width:120,
				value:(function(reportType){
					var flag = (reportType && reportType ==ReportType.complaint);
					return '<span style="color:#FF0000">'+(flag?i18n('i18n.Duty.dutyType.complaint'):i18n('i18n.Duty.dutyType.unusual'))+i18n('i18n.DutyBusinessProcessView.spanBusinessLinksSet');
				})(me.reportType)
			},{ 
				xtype:'btnpanel',  
				defaultType:'button',
				items:[{//增加
					text:i18n('i18n.duty.add'),
					handler:function(){
						//增加按钮
						var gridStore = Ext.getCmp(me.gridId).getStore();
						gridStore.add(new BusinessItemsModel({
							'reportType':me.reportType
						}));													
					}
				   },{//删除
					text:i18n('i18n.duty.delete'),
					handler:function(){
						//删除按钮
						var grid = Ext.getCmp(me.gridId);
						var selection = grid.getSelectionModel().getSelection();
						if(Ext.isEmpty(selection) || selection.length==0){
							MessageUtil.showMessage(i18n('i18n.DutyBasicDataSettingView.selectDate'));return;//请选择数据
						}
						MessageUtil.showQuestionMes(i18n('i18n.DutyBusinessProcessView.areYouSureDeletePartBusiness'),function(e){//您是否确定删除该业务项
							if(e != 'yes'){return;}
							var parmArray = new Array();
							var selectionArray = new Array();
							Ext.each(selection,function(record){
								if(Ext.isEmpty(record.get('busItemId')) || '0' == record.get('busItemId')){
									//删除前端新增的
									grid.getStore().remove(record);
								}else{
									parmArray.push(record.data);
									selectionArray.push(record);
								}
							});
							if(parmArray.length == 0){return;}
						
							var params = {
								'basicInfoList':parmArray
							};
							var successFn = function(){
								MessageUtil.showMessage(i18n('i18n.DutyBasicDataSettingView.deleteDataSuccess'));//删除数据成功
								Ext.each(selectionArray,function(record){
									//删除前端新增的
									grid.getStore().remove(record);
								});
							};
							var failFn = function(){
								MessageUtil.showMessage(i18n('i18n.DutyBasicDataSettingView.deleteDataFailure'));//删除数据失败
							};
							ActionFunction.basalDataList_deleteByIdArray(params,successFn,failFn);
						});
			      	}
				}]
		}]; 
	}
});

/**
 * 业务项列表
 */
Ext.define('DutyBusinessProcessGrid',{
	extend:'PopupGridPanel',
	defaults:{align:'center'},
	autoScroll:true,
	initComponent:function(){ 
		var me = this;
		me.columns = me.getColumns();
		this.callParent(arguments);
   },
   getColumns:function(){
   		var me = this;
   		return [
		    {xtype:'rownumberer',header:i18n('i18n.Duty.resultDutyPanel.rownumberer'),width:40},//序号
		    {header:i18n('i18n.duty.busItemName'),flex:1,dataIndex:'busItemName',editor:{xtype:'textfield',maxLength:20,allowBlank:false}} 
	    ];
   }
});

/**
 * 工单责任业务项设置页面整体布局panel
 */
Ext.define('DutyBusinessProcessPanel', {
	extend : 'BasicPanel',
	layout : 'border',
	items :[
	  {//投诉业务项设置
		xtype:'container',
		region:'west',
		layout:'border',
		width:300,
		items:[
		  {//按钮
			xtype:'container',//container可以去除panel的边框
			layout:'fit',
			region:'north',
			margin:'5 0 0 5',
			items:[
				Ext.create('DutyBasalProcessBtnPanel',{
					id:'dutyBusinessProcessBtnPanelId'
				})]	  
		  },{//投诉业务项列表
			xtype:'container',//container可以去除panel的边框
			layout:'fit',
			region:'center',
			margin:'5 0 5 5',
			items:[
				Ext.create('DutyBusinessProcessGrid',{
					id:'dutyBusinessProcessGridId',
					store:Ext.create('BusinessItemsStore',{
						listeners:{
							beforeload:function(store, operation, eOpts){
								Ext.apply(operation,{params:{
									'basicSearchCondition.reportType':ReportType.complaint
								}});	
							}
						}
					}),
					selModel:Ext.create('Ext.selection.CheckboxModel'),
		    	   	plugins:Ext.create('Ext.grid.plugin.CellEditing', {clicksToEdit :2})
				})
			]	  
		  }      
		]
	  },{//异常业务项设置
		xtype:'container',
		layout:'border',
		region:'center',
		items:[
		  {//按钮面板
			xtype:'container',//container可以去除panel的边框
			layout:'fit',
			margin:'5 5 0 10',
			region:'north',
			items:[
				Ext.create('DutyBasalProcessBtnPanel',{
					id:'dutyExcepBusinessProcessBtnPanelId',
					reportType:ReportType.unusual,
					gridId:'dutyExcepBusinessProcessGridId'
				})]	  
		  },{//异常业务项列表
			xtype:'container',//container可以去除panel的边框
			layout:'fit',
			margin:'5 5 5 10',
			region:'center',
			items:[Ext.create('DutyBusinessProcessGrid',{
				id:'dutyExcepBusinessProcessGridId',
				store:Ext.create('BusinessItemsStore',{
						listeners:{
							beforeload:function(store, operation, eOpts){
								Ext.apply(operation,{params:{
									'basicSearchCondition.reportType':ReportType.unusual
								}});	
							}
						}
				}),
				selModel:Ext.create('Ext.selection.CheckboxModel'),
				plugins:Ext.create('Ext.grid.plugin.CellEditing', {clicksToEdit :2})
			})]	  
		  }      
		]
	  }
	]
});	


/**
 * 单击业务项设置按钮，弹出的业务项设置窗口
 * xiaohongye
*/
Ext.define('DutyBusinessProcessWindow',{
	extend:'PopWindow',
	title:i18n('i18n.duty.busItemNameSet'),//业务项设置
	alias : 'widget.basicwindow',
	width:650,
	height:400,
	modal:true,
	layout:'fit',
	closeAction:'hide',
	items:[Ext.create('DutyBusinessProcessPanel',{'id':'dutyBusinessProcessPanelId'})],
	listeners:{
		hide:function(){
			document.body.scrollLeft=0;
			document.body.scrollTop=0;
			document.getElementsByTagName("html")[0].style.overflowY="hidden";
			document.getElementsByTagName("html")[0].style.overflowX="hidden";
		},
		beforeclose:function(){
			Ext.getCmp('dutyBusinessProcessGridId').getStore().removeAll();
			Ext.getCmp('dutyExcepBusinessProcessGridId').getStore().removeAll();
			//加载 分页集合
			Ext.getCmp('dutyBasicDataResultGridId').getStore().load();
		},
		show:function(){
			Ext.getCmp('dutyBusinessProcessGridId').getStore().load();
			Ext.getCmp('dutyExcepBusinessProcessGridId').getStore().load();
		}
	},
	buttons: [{//提交
			xtype:'button',
			text:i18n('i18n.duty.submit'),
			handler:function(){
				var businessStore = Ext.getCmp('dutyBusinessProcessGridId').getStore();
				var unusualStore = Ext.getCmp('dutyExcepBusinessProcessGridId').getStore();
				if(
					(!businessStore || businessStore == null || businessStore.count() ==0) && 
					(!unusualStore || unusualStore == null || unusualStore.count() ==0)){
					MessageUtil.showMessage(i18n('i18n.DutyBusinessProcessView.businessLinksNotAllNull'));return;//业务项不能都为空
				}
				var basicInfoList = new Array();
				var check_result = {'flag':true};
				businessStore.each(function(record){
					if(Ext.isEmpty(record.get('busItemName'))){
		    			businessStore.remove(record);
		    		}else{
		    			basicInfoList.push(record.data);
		    		}
				});
				unusualStore.each(function(record){
					if(Ext.isEmpty(record.get('busItemName'))){
		    			unusualStore.remove(record);
		    		}else{
		    			basicInfoList.push(record.data);
		    		}
				});
				if(basicInfoList.length == 0){//验证表格数据是否为空
		    		MessageUtil.showMessage(i18n('i18n.DutyBusinessProcessView.submittedDataNotEmpty'));
		    	}
		    	var successFn = function(){
		    		//加载分页集合
		    		Ext.getCmp("dutyBusinessProcessWindowId").close();
					MessageUtil.showInfoMes(i18n('i18n.DutyBasicDataSettingView.saveSuccess'));//保存成功
		    	};
		    	var failFn = function(){};
		    	ActionFunction.basalData_saveList({'basicInfoList':basicInfoList},successFn,failFn);
			}
	},{//取消
		xtype:'button',
		text:i18n('i18n.duty.cancel'),
		handler:function(){
			Ext.getCmp("dutyBusinessProcessWindowId").close();
		}
	}]
});

