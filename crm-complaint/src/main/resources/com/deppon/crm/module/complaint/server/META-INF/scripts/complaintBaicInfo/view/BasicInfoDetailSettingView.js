//定义一个全局变量，用以标记打开此页面的目的是为了新增还是为了修改,默认取值为新增
var basicInfoSettingOperation = 'ADD';
//定义一个全局变量，用以标记是否修改了上报类型
var typeMark = null;
//定义一个全局变量，用以标记修改基础资料时是否修改了业务项
var busItemNameMark = null;
//定义一个全局变量，用以标记修改基础资料时是否修改了业务范围
var busScopeNameMark = null;
/**
 *新增基础资料包括业务范围、业务类型设置
 */
Ext.define('BasicInfoSettingForm',{
	extend:'SearchFormPanel', 
	items:null,
	border:0,
	layout:{
		type:'table',
		columns:5
	},
	defaults:{
		labelWidth:60,
		margin:'0 5 5 0'
	},
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		me.callParent();
	},
	getItems:function(){
		var me = this;
		return [
		    {//上报类型
		    	xtype: 'radiogroup',
		        fieldLabel: i18n('i18n.complaint.newBasicInfo.reportType'),
		        width:180,
		        columns: 2,
		        items: [
		            { boxLabel: i18n('i18n.complaint.newBasicInfo.complaint'), name: 'lx', inputValue: 'COMPLAINT', checked:true},
		            { boxLabel: i18n('i18n.complaint.newBasicInfo.unusual'), name: 'lx', inputValue: 'UNUSUAL'}
		        ],
		    	name:'type',
		    	id:'reportTypeNew'
		    },{//业务项
		    	xtype:'combobox',
		    	fieldLabel:'<span style="color:#ff0000">*</span>'+i18n('i18n.complaint.newBasicInfo.businessItems'),
		    	width:200,
		    	labelWidth:60,
		    	allowBlank:false,
		    	blankText:i18n('i18n.complaint.newBasicInfo.blankBusItems'),
				//剔出朦层
    			listConfig: {
      		        loadMask:false
    			},
		    	queryMode:'local',
		    	forceSelection :true,
		    	store:Ext.create('SelectBusinessItemsStore'),
		    	displayField:'busItemName',
				valueField:'busItemId',
				name:'busItemName',
				id:'addBusItemName'
			},{//业务范围
		    	xtype:'textfield',
		    	fieldLabel:'<span style="color:#ff0000">*</span>'+i18n('i18n.complaint.newBasicInfo.busScopeName'),
		    	width:200,
		    	margin:'0 0 0 10',
		    	maxLength:20,
		    	maxLengthText:i18n('i18n.complaint.newBasicInfo.queryConditionTooLong'),
		    	allowBlank:false,
		    	blankText:i18n('i18n.complaint.newBasicInfo.blankBusScope'),
				name:'busScopeName',
				listeners:{
					blur:function(th){
						if(Ext.isEmpty(th.getValue())){
							Ext.getCmp("basicInfoSettingGridId").store.removeAll();
						}
					}
				}
			},{//隐藏文本框，用以记录业务项ID
		    	xtype:'textfield',
		    	width:10,
		    	margin:'0 0 0 10',
				name:'busItemId',
				hidden:true
			},{//隐藏文本框，用以记录业务范围ID
		    	xtype:'textfield',
		    	width:10,
				name:'busScopeId',
				margin:'0 0 0 10',
				hidden:true
			}
	    ];
	}
});	

/**
 * 基础资料设置增加、删除按钮Panel
 */
Ext.define('BasicInfoSetAddAndDelPanel',{
	extend:'TopPanel',
	margin:'5 5 0 5',
	items : null,
	initComponent : function() {
		this.items = this.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [{
			xtype:'btnpanel',  
			defaultType:'button',
			items:[{//增加
				text:i18n('i18n.complaint.newBasicInfo.add'),
				handler:function(){	
					//只有当业务范围不为空的时候，才能进行增加操作
					var busScopeName = Ext.getCmp('basicInfoSettingFormId').getForm().findField('busScopeName').getValue();
					if(Ext.isEmpty(busScopeName)){
						MessageUtil.showErrorMes(i18n('i18n.complaint.newBasicInfo.inputBusScopeFirst'));
		            	return false;
					}

					var basicInfoSettingStore = Ext.getCmp('basicInfoSettingGridId').getStore();
               	 	var basicInfoSettingModel = new BasicInfoSettingModel();
               	 	basicInfoSettingStore.add(basicInfoSettingModel);	
               	 	basicInfoSettingModel.commit();
				}
			   },{//删除
				text:i18n('i18n.complaint.newBasicInfo.delete'),
				handler:function(btn){
					btn.disable();
					setTimeout(function(){
						btn.blur();
						btn.enable();
					},2000);
					//删除按钮功能实现区
					var selection = Ext.getCmp('basicInfoSettingGridId').getSelectionModel().getSelection();
		            var basicInfoSettingStore = Ext.getCmp('basicInfoSettingGridId').getStore();
					if (selection.length === 0) {
		            	MessageUtil.showErrorMes(i18n('i18n.complaint.newBasicInfo.selectToDelete'));
		            	btn.enable();
		            	return false;
		            }
		            
		            if (selection.length >= 1) {
		            	MessageUtil.showQuestionMes(i18n('i18n.complaint.newBasicInfo.sureToDeleteOne')
		            			+selection.length+i18n('i18n.complaint.newBasicInfo.sureToDeleteTwo'), 
		            			function(e) {
		            		if (e == 'yes') {
		            			basicInfoSettingStore.remove(selection);
		            			if(basicInfoSettingOperation === 'MODIFY'){
		            				for(var i = 0;i < selection.length;i++){
		            					//业务类型列表
		            					var basicInfoSettingArray = new Array();
			            				//当业务类型id不为空时，调用相应的action，删除此条业务类型
			            				if(!Ext.isEmpty(selection[i].get('id'))){
			            				    //将选中要删除的业务类型列表的数据放入到业务类型列表数组中
			            				    basicInfoSettingArray.push(selection[i].data);
			            				}
			            				if(basicInfoSettingArray.length >= 1){
			            					//执行删除操作
			            					var successFn = function(json){
			            						btn.enable();
			            						MessageUtil.showInfoMes(i18n('i18n.complaint.newBasicInfo.deleteSuccess'));
			            					}
			            					
			            					var failureFn = function(json){
			            						btn.enable();
			            				    	MessageUtil.showErrorMes(json.message);
			            				    };
			            					
			            					var param = {
			            				    	'busTypes':basicInfoSettingArray
			            				    };
			            					BasicInfoStore.prototype.deleteBusTypeByIdsInBusScopeVO(param,successFn,failureFn);
			            				}
			            				else{
			            					btn.enable();
			            				}
			            			}
		            			}
		            			else{
		            				btn.enable();
		            			}
		            		}
		            		else{
		            			btn.enable();
		            		}
		            	});
		            }
					
		      	}
			}]
		}]; 
	}
});

/**
 * 基础资料设置列表
 */
Ext.define('BasicInfoSettingGrid',{
	extend:'SearchGridPanel',
	defaults:{
		align:'center'
	},
	autoScroll:true,
	selModel:Ext.create('Ext.selection.CheckboxModel'),
	initComponent:function(){ 
		var me = this;
		var store = Ext.create('BasicInfoSettingStore',{id:'basicInfoSettingStoreId'});
		store.on('beforeload',function(store,operation,e){					
			//封装查询条件传递给action
			var basicInfoSettingForm = Ext.getCmp('basicInfoSettingFormId').getForm();
			var busItemId =  basicInfoSettingForm.findField('busItemId').getValue();
			var busScopeId = basicInfoSettingForm.findField('busScopeId').getValue();
			var searchParams = {
				'businessType.busItemId':busItemId,
				'businessType.busScopeId':busScopeId
			};
			Ext.apply(operation,{
					params : searchParams
			});
		});			
		me.store = store;
		me.columns = [{//序号
	    	xtype:'rownumberer',
			header:i18n('i18n.complaint.serial_number'),
			width:40
	    },{//业务类型
			header:i18n('i18n.complaint.newBasicInfo.busTypeName'),
			width:100,
			dataIndex:'busType',
			editor:{
				xtype:'textfield',
		    	maxLength:20,
		    	maxLengthText:i18n('i18n.complaint.newBasicInfo.queryConditionTooLong')
			}
	    },{//处理语言
			header:i18n('i18n.complaint.newBasicInfo.dealLanguage'),
			flex:1,
			dataIndex:'dealLanguage',
			editor:{
				xtype:'textfield',
		    	maxLength:300,
		    	maxLengthText:'最多可输入300个字符!'
			},renderer:function(value) {
				if(!Ext.isEmpty(value)){
					var val = '<span data-qtip="'+value+'">'+value+'</span>';
					return val;
				}
			}
	    },{//反馈时限
			header:i18n('i18n.complaint.newBasicInfo.feedbackLimit'),
			width:110,
			dataIndex:'feedbackLimit',
			editor:{
				xtype:'numberfield',
				minText:'反馈时限大于0！',
				minValue:0,
		    	maxLength:10,
		    	maxLengthText:'最多可输入10个字符!'
			}
	    },{//处理时限
			header:i18n('i18n.complaint.newBasicInfo.procLimit'),
			width:110,
			dataIndex:'procLimit',
			editor:{
				xtype:'numberfield',
				minText:'处理时限大于0！',
				minValue:0,
		    	maxLength:20,
		    	maxLengthText:'最多可输入20个字符!'
			}
	    }];
		this.callParent();
   }
});

/**
 * 单击”基础资料设置“按钮出现的设置页面整体布局panel
 */
Ext.define('BasicInfoSettingPanel', {
	extend : 'BasicPanel',
	layout : 'border',
	items :[
	  {//业务项选择、业务范围设置
		xtype:'container',
		region:'north',
		layout:'fit',
		items:[Ext.create('BasicInfoSettingForm',{id:'basicInfoSettingFormId'})]
	  },{
		xtype:'container',
		layout:'border',
		region:'center',
		items:[{//按钮
			xtype:'container',//container可以去除panel的边框
			layout:'fit',
			region:'north',
			height:45,
			items:[Ext.create('BasicInfoSetAddAndDelPanel',{id:'basicInfoSetAddAndDelPanelId'})]	  
		  },{//基础资料设置列表
			xtype : 'container',
			layout:'fit',
			region:'center',
			items:[Ext.create('BasicInfoSettingGrid',{
				id:'basicInfoSettingGridId',
				plugins:Ext.create('Ext.grid.plugin.CellEditing', {clicksToEdit : 2})
			})]
		  }]
	  }
	]
});	

/**
 * 单击基础资料设置按钮，弹出的基础资料设置窗口
 * xiaohongye
*/
Ext.define('BasicInfoSettingWindows',{
	extend:'PopWindow',
	alias : 'widget.basicwindow',
	width:700,
	height:500,
	modal:true,
	layout:'fit',
	closeAction:'hide',
	items:[Ext.create('BasicInfoSettingPanel',{'id':'basicInfoSettingPanelId'})],
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
				//业务范围、业务项选择、输入form
				var basicInfoSettingForm = Ext.getCmp('basicInfoSettingFormId').getForm()
				if(!(basicInfoSettingForm.findField('busItemName').isValid())){
					btn.enable();
					MessageUtil.showErrorMes(i18n('i18n.complaint.newBasicInfo.wrongBusItems'));
					return false;
				}
				if(!(basicInfoSettingForm.findField('busScopeName').isValid())){
					btn.enable();
					MessageUtil.showErrorMes(i18n('i18n.complaint.newBasicInfo.wrongBusScope'));
					return false;
				}
				//获取处理结果基础资料录入表格
				var basicInfoSettingGrid = Ext.getCmp('basicInfoSettingGridId');
				var basicInfoSettingGridStore = basicInfoSettingGrid.store;
				
				//跟踪数据是否需要修改后再提交
				var canNotSubmit = false;
				//设置变量，记录基础资料录入表格的行号
				var lineNumberOne = 1;
				//删除空数据，统计校验数据的长度是否有效
				basicInfoSettingGridStore.each(function(record){										
					if(Ext.isEmpty(record.data.busType) && Ext.isEmpty(record.data.dealLanguage)
							&& Ext.isEmpty(record.data.feedbackLimit)
							&& Ext.isEmpty(record.data.procLimit)){
						basicInfoSettingGridStore.remove(record);
					}
					else if(Ext.isEmpty(record.data.busType)){
						btn.enable();
						MessageUtil.showErrorMes(i18n('i18n.complaint.newBasicInfo.submitErrorOne')
								+lineNumberOne+i18n('i18n.complaint.newBasicInfo.submitErrorTwo')+
								i18n('i18n.complaint.newBasicInfo.busTypeName')+
								i18n('i18n.complaint.newBasicInfo.submitErrorThree'));
						canNotSubmit=true;
						return false;
					}
					else if(record.data.busType.length>20){
						btn.enable();
						MessageUtil.showErrorMes(i18n('i18n.complaint.newBasicInfo.submitErrorOne')
								+lineNumberOne+i18n('i18n.complaint.newBasicInfo.submitErrorTwo')+
								i18n('i18n.complaint.newBasicInfo.busTypeName')+
								i18n('i18n.complaint.newBasicInfo.submitErrorFour')+20
										+i18n('i18n.complaint.newBasicInfo.submitErrorFive'));
						canNotSubmit=true;
						return false;
					}
					else if(!Ext.isEmpty(record.data.dealLanguage) && (record.data.dealLanguage.length>1000)){
						btn.enable();
						MessageUtil.showErrorMes(i18n('i18n.complaint.newBasicInfo.submitErrorOne')+
								lineNumberOne+i18n('i18n.complaint.newBasicInfo.submitErrorTwo')+
								i18n('i18n.complaint.newBasicInfo.dealLanguage')+
								i18n('i18n.complaint.newBasicInfo.submitErrorFour')
								+1000+i18n('i18n.complaint.newBasicInfo.submitErrorFive'));
						canNotSubmit=true;
						return false;
					}
					else if(!Ext.isEmpty(record.data.feedbackLimit) && (record.data.feedbackLimit.length>20)){
						btn.enable();
						MessageUtil.showErrorMes(i18n('i18n.complaint.newBasicInfo.submitErrorOne')+
								lineNumberOne+i18n('i18n.complaint.newBasicInfo.submitErrorTwo')+
								i18n('i18n.complaint.newBasicInfo.feedbackLimitWithOutUnit')+
								i18n('i18n.complaint.newBasicInfo.submitErrorFour')
								+20+i18n('i18n.complaint.newBasicInfo.submitErrorFive'));
						canNotSubmit=true;
						return false;
					}
					else if(!Ext.isEmpty(record.data.procLimit) && (record.data.procLimit.length>10)){
						btn.enable();
						MessageUtil.showErrorMes(i18n('i18n.complaint.newBasicInfo.submitErrorOne')+
								lineNumberOne+i18n('i18n.complaint.newBasicInfo.submitErrorTwo')+
								i18n('i18n.complaint.newBasicInfo.procLimitWithOutUnit')+i18n('i18n.complaint.newBasicInfo.submitErrorFour')
								+10+i18n('i18n.complaint.newBasicInfo.submitErrorFive'));
						canNotSubmit=true;
						return false;
					}
					else{
						lineNumberOne++;
					}	
				});
				if(canNotSubmit){
					btn.enable();
					return false;
				}
				//处理结果基础资料设置列表
			    var basicInfoSettingArray = new Array();
			    //将处理结果基础资料设置列表表格中的数据放入到处理结果基础资料设置列表数组中
			    basicInfoSettingGridStore.each(function(record){
			    	basicInfoSettingArray.push(record.data);
			    });
			    
			    //执行新增操作
			    if(basicInfoSettingOperation === 'ADD'){
			    	var successFn = function(json){
						btn.enable();
						//清空处理结果基础资料设置页面中的所有内容
					    Ext.getCmp('basicInfoSettingFormId').getForm().reset();
					    basicInfoSettingGrid.getStore().removeAll();
						btn.enable();
						Ext.getCmp('basicInfoResultGridId').store.loadPage(1);
						basicInfoSettingWindows.close();
						MessageUtil.showInfoMes(i18n('i18n.complaint.newBasicInfo.addBasicInfoSuccess'));
					}
					
					var failureFn = function(json){
						btn.enable();
				    	MessageUtil.showErrorMes(json.message);
				    };
				    //打开基础资料设置页面之前，首先清空处理结果基础资料设置页面中的所有内容
	            	var basicInfoSettingForm = Ext.getCmp('basicInfoSettingFormId').getForm();
	            	var param={
	            		'businessType':{
	            			'busTypes':basicInfoSettingArray,
	            			'busItemId':basicInfoSettingForm.findField('busItemName').getValue(),
	            			'busScopeName':basicInfoSettingForm.findField('busScopeName').getValue(),
					    	'busItemName':basicInfoSettingForm.findField('busItemName').getRawValue(),
					    	'busScopeId':'',
					    	'reportType':basicInfoSettingForm.findField('type').getValue().lx
	            		}
	            	};
					BasicInfoStore.prototype.operateBasicBusInfo(param,successFn,failureFn);
			    }
			    
			    //执行修改操作
			    else if(basicInfoSettingOperation === 'MODIFY'){
			    	if((basicInfoSettingForm.findField('busItemName').getValue() === busItemNameMark)&&
			    		(basicInfoSettingForm.findField('type').getValue().lx === typeMark) &&
			    		(basicInfoSettingForm.findField('busScopeName').getValue() === busScopeNameMark) &&
			    		(basicInfoSettingArray.length === 0)){
			    		btn.enable();
						MessageUtil.showErrorMes(i18n('i18n.complaint.newBasicInfo.noBasicInfoToSubmit'));
						return false;
			    	}
			    	var successFn = function(json){
						btn.enable();
						//清空处理结果基础资料设置页面中的所有内容
					    Ext.getCmp('basicInfoSettingFormId').getForm().reset();
					    basicInfoSettingGrid.getStore().removeAll();
						btn.enable();
						basicInfoSettingWindows.close();
						Ext.getCmp('basicInfoResultGridId').store.loadPage(1);
						MessageUtil.showInfoMes(i18n('i18n.complaint.newBasicInfo.modifyBasicInfoSuccess'));
					}
					
					var failureFn = function(json){
						btn.enable();
				    	MessageUtil.showErrorMes(json.message);
				    };
				    //打开基础资料设置页面之前，首先清空处理结果基础资料设置页面中的所有内容
	            	var basicInfoSettingForm = Ext.getCmp('basicInfoSettingFormId').getForm();
	            	var busItemName = '';
	            	var reportType = '';
	            	var busScopeName = '';
	            	if(basicInfoSettingForm.findField('busItemName').getValue() !== busItemNameMark){
	            		busItemName  = basicInfoSettingForm.findField('busItemName').getValue();
	            	}
	            	if(basicInfoSettingForm.findField('type').getValue().lx !== typeMark){
	            		reportType  = basicInfoSettingForm.findField('type').getValue().lx;
	            	}
	            	if(basicInfoSettingForm.findField('busScopeName').getValue() !== busScopeNameMark){
	            		busScopeName  = basicInfoSettingForm.findField('busScopeName').getValue();
	            	}
	            	
	            	var param={
	            		'businessType':{
	            			'busTypes':basicInfoSettingArray,
	            			'busItemName':busItemName,
					    	'busItemId':basicInfoSettingForm.findField('busItemId').getValue(),
					    	'busScopeName':busScopeName,
					    	'busScopeId':basicInfoSettingForm.findField('busScopeId').getValue(),
					    	'reportType':reportType
	            		}
		            };
					BasicInfoStore.prototype.operateBasicBusInfo(param,successFn,failureFn);
			    }	    			    
			}
	},{//取消
		xtype:'button',
		text:i18n('i18n.complaint.newBasicInfo.cancel'),
		handler:function(){
			//清空处理结果基础资料设置页面中的所有内容
		    Ext.getCmp('basicInfoSettingFormId').getForm().reset();
		    Ext.getCmp('basicInfoSettingGridId').getStore().removeAll();
		    Ext.getCmp('basicInfoResultGridId').store.loadPage(1);
			basicInfoSettingWindows.close();
		}
	}]
});
var basicInfoSettingWindows = Ext.getCmp("basicInfoSettingWindowsId");//获取处理结果基础资料设置窗口
if(!basicInfoSettingWindows){
	basicInfoSettingWindows = Ext.create('BasicInfoSettingWindows',{id:'basicInfoSettingWindowsId'});
}