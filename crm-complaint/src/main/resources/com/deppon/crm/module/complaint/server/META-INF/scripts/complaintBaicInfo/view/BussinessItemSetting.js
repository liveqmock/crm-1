/**
 * 工单业务项设置页面
 */

/**
 * 投诉业务项设置增加、删除按钮面板
 */
Ext.define('BusinessItemsBtnPanel',{
	extend:'TopPanel',
	items : null,
	initComponent : function() {
		this.items = this.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [{//投诉业务项设置
			xtype:'displayfield',
			value:'<span style="color:#FF0000">'+i18n('i18n.complaint.newBasicInfo.complaint')+
			'</span>'+i18n('i18n.complaint.newBasicInfo.businessItemSetting'),
			width:120
		},{
			xtype:'btnpanel',  
			defaultType:'button',
			items:[{//增加
				text:i18n('i18n.complaint.newBasicInfo.add'),
				handler:function(){
					//增加按钮
					var businessItemsStore = Ext.getCmp('businessItemsGridId').getStore();
               	 	var businessItemsModel = new BusinessItemsModel();
               	 	businessItemsModel.set('reportType','COMPLAINT');
               	 	businessItemsStore.insert(0,businessItemsModel);	
               	 	businessItemsModel.commit();
				}
			   },{//删除
				text:i18n('i18n.complaint.newBasicInfo.delete'),
				id:'',
				handler:function(){
					//删除按钮功能实现区
					var selection = Ext.getCmp('businessItemsGridId').getSelectionModel().getSelection();
		            var businessItemsStore = Ext.getCmp('businessItemsGridId').getStore();
		            if (selection.length === 0) {
		            	MessageUtil.showErrorMes(i18n('i18n.complaint.newBasicInfo.selectBusItemsToDelete'));
		            	return false;
		            }
		            
		            if (selection.length >= 1) {
		            	MessageUtil.showQuestionMes(i18n('i18n.complaint.newBasicInfo.sureToDeleteOne')
		            			+selection.length+i18n('i18n.complaint.newBasicInfo.sureToDeleteBusItems'), 
		            			function(e) {
		            		if (e == 'yes') {
		            			//创建一个业务项列表，用以存放已具有业务项ID的业务项
		            			var businessItemsArray = new Array();
		            			for(var i = 0;i<selection.length;i++){
		            				if(!Ext.isEmpty(selection[i].get('busItemId'))){
		            					businessItemsArray.push(selection[i].data);
		            				}
		            			}
		            			if(businessItemsArray.length >= 1){
		            				//执行删除操作
	            					var successFn = function(json){
	            						businessItemsStore.remove(selection);
	    		            			MessageUtil.showInfoMes(i18n('i18n.complaint.newBasicInfo.deleteSuccess'));
	            					}
	            					var failureFn = function(json){
	            				    	MessageUtil.showErrorMes(json.message);
	            				    };
	            					
	            					var param = {
	            				    	'basicInfoList':businessItemsArray
	            				    };
	            					BasicInfoStore.prototype.deleteBusItemByIds(param,successFn,failureFn);
		            			}
		            			else{
		            				businessItemsStore.remove(selection);
    		            			MessageUtil.showInfoMes(i18n('i18n.complaint.newBasicInfo.deleteSuccess'));
		            			}
		            		}
		            	});
		            }
		      	}
			}]
		}]; 
	}
});

/**
 * 异常业务项设置增加、删除按钮面板
 */
Ext.define('ExcepBusinessItemsBtnPanel',{
	extend:'TopPanel',
	items : null,
	initComponent : function() {
		this.items = this.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [//异常业务项设置
			{
				xtype:'displayfield',
				value:'<span style="color:#FF0000">'+i18n('i18n.complaint.newBasicInfo.unusual')+
				'</span>'+i18n('i18n.complaint.newBasicInfo.businessItemSetting'),
				width:120
			},{
				xtype:'btnpanel',  
				defaultType:'button',
				items:[{//增加
					text:i18n('i18n.complaint.newBasicInfo.add'),
					handler:function(){
						//增加按钮
						var exepBusinessItemsStore = Ext.getCmp('exepBusinessItemsGridId').getStore();
	               	 	var exepBusinessItemsModel = new BusinessItemsModel();
	               	 	exepBusinessItemsModel.set('reportType','UNUSUAL');
	               	 	exepBusinessItemsStore.insert(0,exepBusinessItemsModel);
	               	 	exepBusinessItemsModel.commit();
					}
				   },{//删除
					text:i18n('i18n.complaint.newBasicInfo.delete'),
					id:'',
					handler:function(){
						//删除按钮功能实现区
						var selection = Ext.getCmp('exepBusinessItemsGridId').getSelectionModel().getSelection();
			            var exepBusinessItemsStore = Ext.getCmp('exepBusinessItemsGridId').getStore();
						if (selection.length === 0) {
			            	MessageUtil.showErrorMes(i18n('i18n.complaint.newBasicInfo.selectBusItemsToDelete'));
			            	return false;
			            }
			            
			            if (selection.length >= 1) {
			            	MessageUtil.showQuestionMes(i18n('i18n.complaint.newBasicInfo.sureToDeleteOne')
			            			+selection.length+i18n('i18n.complaint.newBasicInfo.sureToDeleteBusItems'), function(e) {
			            		if (e == 'yes') {
			            			
			            			//创建一个业务项列表，用以存放已具有业务项ID的业务项
			            			var exepBusinessItemsArray = new Array();
			            			for(var i = 0;i<selection.length;i++){
			            				if(!Ext.isEmpty(selection[i].get('busItemId'))){
			            					exepBusinessItemsArray.push(selection[i].data);
			            				}
			            			}
			            			if(exepBusinessItemsArray.length >= 1){
			            				//执行删除操作
		            					var successFn = function(json){
		            						exepBusinessItemsStore.remove(selection);
					            			MessageUtil.showInfoMes(i18n('i18n.complaint.newBasicInfo.deleteSuccess'));
		            					}
		            					
		            					var failureFn = function(json){
		            				    	MessageUtil.showErrorMes(json.message);
		            				    };
		            					
		            					var param = {
		            				    	'basicInfoList':exepBusinessItemsArray
		            				    };
		            					BasicInfoStore.prototype.deleteBusItemByIds(param,successFn,failureFn);
			            			}
			            		}
			            	});
			            }
			      	}
				}]
		}]; 
	}
});

/**
 * 投诉业务项列表
 */
Ext.define('BusinessItemsGrid',{
	extend:'PopupGridPanel',
	defaults:{
		align:'center'
	},
	autoScroll:true,	
	initComponent:function(){ 
		var me = this;
		me.columns = [
		    {//序号
		    	xtype:'rownumberer',
				header:i18n('i18n.complaint.serial_number'),
				width:60
		    },{//业务项
				header:i18n('i18n.complaint.newBasicInfo.businessItems'),
				flex:1,
				dataIndex:'busItemName',
				editor:{
					xtype:'textfield',
			    	maxLength:20,
			    	maxLengthText:i18n('i18n.complaint.newBasicInfo.queryConditionTooLong')
				}
		    }
	    ];
		this.callParent();
   }
});

/**
 * 工单业务项设置页面整体布局panel
 */
Ext.define('BusinessItemSettingPanel', {
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
			items:[Ext.create('BusinessItemsBtnPanel',{id:'businessItemsBtnPanelId'})]	  
		  },{//投诉业务项列表
			xtype:'container',//container可以去除panel的边框
			layout:'fit',
			region:'center',
			margin:'5 0 5 5',
			items:[
		       Ext.create('BusinessItemsGrid',{
		    	   store:Ext.create('ComplaintBusinessItemsStore'),
		    	   id:'businessItemsGridId',
		    	   selModel:Ext.create('Ext.selection.CheckboxModel'),
		    	   plugins:Ext.create('Ext.grid.plugin.CellEditing', {clicksToEdit : 2})
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
			items:[Ext.create('ExcepBusinessItemsBtnPanel',{id:'excepBusinessItemsBtnPanelId'})]	  
		  },{//异常业务项列表
			xtype:'container',//container可以去除panel的边框
			layout:'fit',
			margin:'5 5 5 10',
			region:'center',
			items:[
		       Ext.create('BusinessItemsGrid',{
		    	   store:Ext.create('UnusualBusinessItemsStore'),
		    	   id:'exepBusinessItemsGridId',
		    	   selModel:Ext.create('Ext.selection.CheckboxModel'),
		    	   plugins:Ext.create('Ext.grid.plugin.CellEditing', {clicksToEdit : 2})
		       })
			]	  
		  }      
		]
	  }
	]
});	


/**
 * 单击业务项设置按钮，弹出的业务项设置窗口
 * xiaohongye
*/
Ext.define('BusinessItemsSettingWindow',{
	extend:'PopWindow',
	title:i18n('i18n.complaint.newBasicInfo.businessItemSetting'),
	alias : 'widget.basicwindow',
	width:650,
	height:400,
	modal:true,
	layout:'fit',
	closeAction:'hide',
	items:[Ext.create('BusinessItemSettingPanel',{'id':'businessItemSettingPanelId'})],
	listeners:{
		hide:function(){
			document.body.scrollLeft=0;
			document.body.scrollTop=0;
			document.getElementsByTagName("html")[0].style.overflowY="hidden";
			document.getElementsByTagName("html")[0].style.overflowX="hidden";
		}
	},
	buttons: [{//提交
			xtype:'button',
			text:i18n('i18n.complaint.newBasicInfo.submit'),
			handler:function(btn){	
				btn.disable();
				//获取投诉业务项录入表格
				var businessItemsGrid = Ext.getCmp('businessItemsGridId');
				var businessItemsGridStore = businessItemsGrid.store;
				//获取异常业务项录入表格
				var exepBusinessItemsGrid = Ext.getCmp('exepBusinessItemsGridId');
				var exepBusinessItemsGridStore = exepBusinessItemsGrid.store;
				
				//跟踪数据是否需要修改后再提交
				var canNotSubmit = false;
				//设置变量，记录投诉业务项录入表格的行号
				var lineNumberOne = 1;
				//设置变量，记录异常业务项录入表格的行号
				var lineNumberTwo = 1;
				//删除投诉空数据，统计校验数据的长度是否有效
				businessItemsGridStore.each(function(record){										
					if(Ext.isEmpty(record.data.busItemName)){
						businessItemsGridStore.remove(record);
					}
					else if(record.data.busItemName.length>20){
						btn.enable();
						MessageUtil.showErrorMes(i18n('i18n.complaint.newBasicInfo.complaint')+
								i18n('i18n.complaint.newBasicInfo.busItemsSetGrid')+lineNumberOne+
								i18n('i18n.complaint.newBasicInfo.submitErrorTwo')+
								i18n('i18n.complaint.newBasicInfo.businessItems')+
								i18n('i18n.complaint.newBasicInfo.submitErrorFour')+
								20+i18n('i18n.complaint.newBasicInfo.submitErrorFive'));
						canNotSubmit=true;
						return false;
					}
					else{
						lineNumberOne++;
					}	
				});
				
				//删除异常空数据，统计校验数据的长度是否有效
				exepBusinessItemsGridStore.each(function(record){										
					if(Ext.isEmpty(record.data.busItemName)){
						exepBusinessItemsGridStore.remove(record);
					}
					else if(record.data.busItemName.length>20){
						btn.enable();
						MessageUtil.showErrorMes(i18n('i18n.complaint.newBasicInfo.unusual')+
								i18n('i18n.complaint.newBasicInfo.busItemsSetGrid')
								+lineNumberTwo+i18n('i18n.complaint.newBasicInfo.submitErrorTwo')+
								i18n('i18n.complaint.newBasicInfo.businessItems')+
								i18n('i18n.complaint.newBasicInfo.submitErrorFour')+
								20+i18n('i18n.complaint.newBasicInfo.submitErrorFive'));
						canNotSubmit=true;
						return false;
					}
					else{
						lineNumberTwo++;
					}	
				});
				if(canNotSubmit){
					return false;
				}
				
				//校验投诉和异常录入的业务项，至少添加一条业务项
				if(businessItemsGridStore.data.length==0 && exepBusinessItemsGridStore.data.length==0){
					btn.enable();
					MessageUtil.showErrorMes(i18n('i18n.complaint.newBasicInfo.canNotSubmitBusItems'));
					return false;																					
				}
				
				//业务项列表
			    var businessItemsArray = new Array();
			    //将投诉业务项列表表格中的数据放入到业务项列表数组中
				businessItemsGrid.store.each(function(record){
					businessItemsArray.push(record.data);
			    });
				
				//将异常业务项列表表格中的数据放入到业务项列表数组中
				exepBusinessItemsGrid.store.each(function(record){
					businessItemsArray.push(record.data);
			    });
				
				if(businessItemsArray.length === 0){
					btn.enable();
					MessageUtil.showErrorMes(i18n('i18n.complaint.newBasicInfo.noRecordToSubmit'));
					return false;
				}
				
				//执行保存操作
				var successFn = function(json){
					//移除投诉和异常业务项列表中的所有内容
					Ext.getCmp('businessItemsGridId').getStore().removeAll();
					Ext.getCmp('exepBusinessItemsGridId').getStore().removeAll();
					btn.enable();
					businessItemsSettingWindow.close();
					Ext.getCmp('basicInfoResultGridId').store.loadPage(1);
					MessageUtil.showInfoMes(i18n('i18n.complaint.newBasicInfo.saveBusItemsSuccess'));
				}
				
				var failureFn = function(json){
					btn.enable();
			    	MessageUtil.showErrorMes(json.message);
			    };
				
				var param = {
			    	'basicInfoList':businessItemsArray
			    };
				BasicInfoStore.prototype.addBusinessItems(param,successFn,failureFn);	
			}
	},{//取消
		xtype:'button',
		text:i18n('i18n.complaint.newBasicInfo.cancel'),
		handler:function(){
			//移除投诉和异常业务项列表中的所有内容
			Ext.getCmp('businessItemsGridId').getStore().removeAll();
			Ext.getCmp('exepBusinessItemsGridId').getStore().removeAll();
			Ext.getCmp('basicInfoResultGridId').store.loadPage(1);
			businessItemsSettingWindow.close();
		}
	}]
});

var businessItemsSettingWindow = Ext.getCmp("businessItemsSettingWindowId");//获取业务项设置窗口
if(!businessItemsSettingWindow){
	businessItemsSettingWindow = Ext.create('BusinessItemsSettingWindow',{id:'businessItemsSettingWindowId'});
}