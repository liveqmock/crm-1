/**
 * 处理结果基础资料主页面
 */
Ext.onReady(function(){	
	//初始化提示信息条
	Ext.QuickTips.init();
	
	/**
	 *基础资料查询条件输入面板form
	 */
	Ext.define('BasicInfoQueryPanel',{
		extend:'SearchFormPanel', 
		items:null,
		border:0,
		layout:{
			type:'table',
			columns:3
		},
		initComponent:function(){			
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [
			    {//查询条件选择框
			    	xtype:'combobox',
			    	labelSeparator:'',
			    	margin:'0 0 5 10',
			    	fieldLabel:'',
			    	labelWidth:0,
			    	width:90,
			    	store:Ext.create('Ext.data.Store', {
			    	    fields: ['code', 'codeDesc'],
			    	    data : [
			    	        {"code":"001", "codeDesc":"业务项"},
			    	        {"code":"002", "codeDesc":"业务范围"},
			    	        {"code":"003", "codeDesc":"业务类型"}
			    	    ]
			    	}),
			    	queryMode:'local',
			    	editable:false,
			    	displayField:'codeDesc',
					valueField:'code',
					value:'001',
					margin:'0 10 0 0',
					name:'basicType'
				},{//查询条件内容输入
					xtype:'textfield',
					labelSeparator:'',
					fieldLabel:'',
					labelWidth:2,
			    	width:180,
			    	margin:'0 10 0 0',
			    	maxLength:20,
			    	maxLengthText:i18n('i18n.complaint.newBasicInfo.queryConditionTooLong'),
			    	allowBlank:false,
					name:'basicContent'
				},{//查询按钮
					xtype:'button',
					text:i18n('i18n.complaint.newBasicInfo.search'),
					handler:function(btn){
						btn.disable();
						setTimeout(function(){
							btn.blur();
							btn.enable();
						},2000);
						if(!(Ext.getCmp('basicInfoQueryPanelId').getForm().findField('basicContent').isValid())){
							btn.enable();
							MessageUtil.showErrorMes(i18n('i18n.complaint.newBasicInfo.invalidQueryCondition'));
			            	return false;
						}
						
						//执行查询操作
    					var successFn = function(json){
    						var recordCount = json.recordCount;
    						if(recordCount === '0'){
    							btn.enable();
        				    	MessageUtil.showErrorMes(i18n('i18n.complaint.newBasicInfo.noResult'));
        				    	return false;
    						}
    						else{
    							btn.enable();
    							Ext.getCmp('basicInfoResultGridId').store.loadPage(1);
    						}
    					}
    					var failureFn = function(json){
    						btn.enable();
    				    	MessageUtil.showErrorMes(json.message);
    				    };
    				    //封装查询条件传递给action
					    var basicInfoQueryForm = Ext.getCmp("basicInfoQueryPanelId");
					    var basicType = basicInfoQueryForm.getForm().findField('basicType').getValue();
					    var basicContent = basicInfoQueryForm.getForm().findField('basicContent').getValue();
    					var param = {
							'bsc.basicType':basicType,
							'bsc.basicContent':basicContent,
							'start':'0',
							'limit':'20'
    				    };
    					BasicInfoStore.prototype.searchBasicInfo(param,successFn,failureFn);
					}
				}
		    ];
		}
	});	
	
	//基础数据管理按钮面板
	Ext.define('BasicInfoButtonPanel',{
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
				width:430,
				items:[
				  {//查看详情
					  xtype:'button',
					  text:i18n('i18n.complaint.newBasicInfo.detail'),
					  handler:function(){
						var selection = Ext.getCmp('basicInfoResultGridId').getSelectionModel().getSelection();
			            var basicInfoResultStore = Ext.getCmp('basicInfoResultGridId').getStore();
			            if (selection.length === 0) {
			            	MessageUtil.showErrorMes(i18n('i18n.complaint.newBasicInfo.selectOne'));
			            	return false;
			            }
			            
			            if (selection.length > 1) {
			            	MessageUtil.showErrorMes(i18n('i18n.complaint.newBasicInfo.onlySelectOne'));
			            	return false;
			            }
			            
			            if (selection.length === 1) {
			            	var basicInfoDetailForm = Ext.getCmp('basicInfoDetailPanelId').getForm();
			            	//为表单中的字段设值之前首先重置表单
			            	basicInfoDetailForm.reset();
			            	if(selection[0].get('reportType') === 'COMPLAINT'){
			            		basicInfoDetailForm.findField('reportType').setValue(i18n('i18n.complaint.newBasicInfo.complaint'));
			            	}
			            	else if(selection[0].get('reportType') === 'UNUSUAL'){
			            		basicInfoDetailForm.findField('reportType').setValue(i18n('i18n.complaint.newBasicInfo.unusual'));
			            	}
			            	else{
			            		basicInfoDetailForm.findField('reportType').setValue('');
			            	}
			            	basicInfoDetailForm.findField('busItemName').setValue(selection[0].get('busItemName'));
			            	basicInfoDetailForm.findField('busScopeName').setValue(selection[0].get('busScopeName'));
			            	basicInfoDetailForm.findField('busTypeName').setValue(selection[0].get('busTypeName'));
			            	basicInfoDetailForm.findField('dealLanguage').setValue(selection[0].get('dealLanguage'));
			            	if(!Ext.isEmpty(selection[0].get('procLimit'))){
			            		basicInfoDetailForm.findField('procLimit').setValue(selection[0].get('procLimit')+'小时');
			            	}
			            	if(!Ext.isEmpty(selection[0].get('feedbackLimit'))){
			            		basicInfoDetailForm.findField('feedbackLimit').setValue(selection[0].get('feedbackLimit')+'分钟');
			            	}
			            	basicInfoDetailWindow.show();
			            } 
					  }
				  },{//业务项设置
					  xtype:'button',
					  text:i18n('i18n.complaint.newBasicInfo.businessItemSetting'),
					  handler:function(){
						  Ext.getCmp("businessItemsGridId").store.load();
						  Ext.getCmp("exepBusinessItemsGridId").store.load();
						  
						  businessItemsSettingWindow.show();
					  }
				  },{//基础资料设置
					  xtype:'button',
					  text:i18n('i18n.complaint.newBasicInfo.basicInfoSetting'),
					  handler:function(){
						var selection = Ext.getCmp('basicInfoResultGridId').getSelectionModel().getSelection();
			            //基础资料新增
						if (selection.length === 0) {
							basicInfoSettingOperation = 'ADD';
							//打开基础资料设置页面之前，首先清空处理结果基础资料设置页面中的所有内容
			            	var basicInfoSettingForm = Ext.getCmp('basicInfoSettingFormId').getForm();
			            	basicInfoSettingForm.reset();
			            	Ext.getCmp('basicInfoSettingGridId').getStore().removeAll();
			            	
							Ext.getCmp('basicInfoSettingFormId').remove(basicInfoSettingForm.findField('busItemName'));
			            	//业务项选择下拉菜单
							Ext.getCmp('basicInfoSettingFormId').insert(1,Ext.create('Ext.form.field.ComboBox',{
			            		fieldLabel:'<span style="color:#ff0000">*</span>'+i18n('i18n.complaint.newBasicInfo.businessItems'),
			    		    	width:200,
			    		    	allowBlank:false,
			    		    	blankText:i18n('i18n.complaint.newBasicInfo.blankBusItems'),
			    		    	labelWidth:60,
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
			            	}));
							
			    		    Ext.getCmp('basicInfoSettingFormId').remove(basicInfoSettingForm.findField('type'));
			            	Ext.getCmp('basicInfoSettingFormId').insert(0,Ext.create('Ext.form.RadioGroup',{
			            		fieldLabel: i18n('i18n.complaint.newBasicInfo.reportType'),
			    		        width:180,
			    		        labelWidth:60,
			    		        columns: 2,
			    		        items: [
			    		            { 
			    		            	boxLabel: i18n('i18n.complaint.newBasicInfo.complaint'), 
			    		            	name: 'lx', 
			    		            	inputValue: 'COMPLAINT', 
			    		            	checked:true,
			    		            	id:'COMPLAINT',
			    		            	listeners:{
					    					change:function(th,newValue,oldValue){
					    						Ext.getCmp("addBusItemName").setValue('');
					    						Ext.getCmp("addBusItemName").store.removeAll();
					    						if(th.getValue()){
					    							Ext.getCmp("addBusItemName").store.load({params:{'reportType':'COMPLAINT'}});
					    						}
					    						else{
					    							Ext.getCmp("addBusItemName").store.load({params:{'reportType':'UNUSUAL'}});
					    						}
					    					}
					    				}
			    		            },
			    		            { 
			    		            	boxLabel: i18n('i18n.complaint.newBasicInfo.unusual'), 
			    		            	name: 'lx', 
			    		            	inputValue: 'UNUSUAL',
			    		            	id:'UNUSUAL'
			    		            }
			    		        ],
			    		    	name:'type',
			    		    	id:'reportTypeNew'
			            	}));
			            	basicInfoSettingWindows.setTitle(i18n('i18n.complaint.newBasicInfo.addBasicInfo'));
			            	Ext.getCmp("addBusItemName").store.load({params:{'reportType':Ext.getCmp('reportTypeNew').getValue().lx}});
			            	basicInfoSettingWindows.show();
			            }
			            
						if (selection.length > 1) {
			            	MessageUtil.showErrorMes(i18n('i18n.complaint.newBasicInfo.selectOneToModify'));
			            	return false;
			            }
			            //基础资料修改
			            if (selection.length === 1) {
			            	basicInfoSettingOperation = 'MODIFY';
			            	//打开基础资料设置页面之前，首先清空处理结果基础资料设置页面中的所有内容
			            	var basicInfoSettingForm = Ext.getCmp('basicInfoSettingFormId').getForm();
			            	basicInfoSettingForm.reset();
			    		    Ext.getCmp('basicInfoSettingGridId').getStore().removeAll();
			            	Ext.getCmp('basicInfoSettingFormId').remove(basicInfoSettingForm.findField('busItemName'));
			            	Ext.getCmp('basicInfoSettingFormId').insert(1,Ext.create('Ext.form.field.Text',{
			            		fieldLabel:'<span style="color:#ff0000">*</span>'+i18n('i18n.complaint.newBasicInfo.businessItems'),
			    		    	width:200,
			    		    	labelWidth:60,
			    		    	readOnly:true,
			    		    	allowBlank:false,
			    		    	blankText:i18n('i18n.complaint.newBasicInfo.blankBusItems'),
			    		    	maxLength:20,
			    		    	maxLengthText:i18n('i18n.complaint.newBasicInfo.queryConditionTooLong'),
			    				name:'busItemName'
			            	}));
			            	
			    		    basicInfoSettingForm.findField('busItemName').setValue(selection[0].get('busItemName'));
			    		    basicInfoSettingForm.findField('busItemId').setValue(selection[0].get('busItemId'));
			    		    basicInfoSettingForm.findField('busScopeName').setValue(selection[0].get('busScopeName'));
			    		    basicInfoSettingForm.findField('busScopeId').setValue(selection[0].get('busScopeId'));
			    		    basicInfoSettingForm.findField('type').setValue(selection[0].get('reportType'));
			    		    if(selection[0].get('reportType')=='UNUSUAL'){
			    		    	Ext.getCmp('basicInfoSettingFormId').remove(Ext.getCmp('basicInfoSettingFormId').getForm().findField('type'));
				            	Ext.getCmp('basicInfoSettingFormId').insert(0,Ext.create('Ext.form.RadioGroup',{
				            		fieldLabel:i18n('i18n.complaint.newBasicInfo.reportType'),
				    		        width:180,
				    		        labelWidth:60,
				    		        columns: 2,
//				    		        disabled:true,
				    		        items: [
				    		            { 
				    		            	boxLabel: i18n('i18n.complaint.newBasicInfo.complaint'), 
				    		            	name: 'lx', 
				    		            	readOnly:true,
				    		            	inputValue: 'COMPLAINT'
				    		            },
				    		            { 
				    		            	boxLabel: i18n('i18n.complaint.newBasicInfo.unusual'), 
				    		            	name: 'lx', 
				    		            	inputValue: 'UNUSUAL',
				    		            	readOnly:true,
				    		            	checked:true
				    		            }
				    		        ],
				    		    	name:'type'
				            	}));
			    		    }
			    		    else{
			    		    	Ext.getCmp('basicInfoSettingFormId').remove(Ext.getCmp('basicInfoSettingFormId').getForm().findField('type'));
				            	Ext.getCmp('basicInfoSettingFormId').insert(0,Ext.create('Ext.form.RadioGroup',{
				            		fieldLabel: i18n('i18n.complaint.newBasicInfo.reportType'),
				    		        width:180,
				    		        labelWidth:60,
//				    		        disabled:true,
				    		        columns: 2,
				    		        items: [
				    		            { 
				    		            	boxLabel: i18n('i18n.complaint.newBasicInfo.complaint'), 
				    		            	name: 'lx', 
				    		            	inputValue: 'COMPLAINT', 
				    		            	readOnly:true,
				    		            	checked:true
				    		            },
				    		            { 
				    		            	boxLabel: i18n('i18n.complaint.newBasicInfo.unusual'), 
				    		            	name: 'lx', 
				    		            	readOnly:true,
				    		            	inputValue: 'UNUSUAL'
				    		            }
				    		        ],
				    		    	name:'type'
				            	}));
			    		    }
			    		    //为全局变量赋初值，用以跟踪修改时是否修改了上报类型、业务项名称或是业务范围名称
			    		    typeMark = selection[0].get('reportType');
			    		    busItemNameMark  =  selection[0].get('busItemName');
			    		    busScopeNameMark  =  selection[0].get('busScopeName');
			    		    Ext.getCmp('basicInfoSettingGridId').store.load();
			    		    basicInfoSettingWindows.setTitle(i18n('i18n.complaint.newBasicInfo.modifyBasicInfo'));
			            	basicInfoSettingWindows.show();
			            } 
					  }
				  },{//删除
					  xtype:'button',
					  text:i18n('i18n.complaint.newBasicInfo.delete'),
					  handler:function(){
						//删除按钮功能实现区
						var selection = Ext.getCmp('basicInfoResultGridId').getSelectionModel().getSelection();
			            var basicInfoResultStore = Ext.getCmp('basicInfoResultGridId').getStore();
			            if (selection.length === 0) {
			            	MessageUtil.showErrorMes(i18n('i18n.complaint.newBasicInfo.selectToDelete'));
			            	return false;
			            }
			            //判断所选的记录中是否有业务类型ID为空的记录，如果有，则不允许在此页面进行删除
			            for(var i = 0;i<selection.length;i++){
			            	if(Ext.isEmpty(selection[i].get('busTypeId'))){
			            		MessageUtil.showErrorMes(i18n('i18n.complaint.newBasicInfo.canNotDeleteBasicInfo'));
			            		return false;
			            	}
			            }
			            
			            if (selection.length >= 1) {
			            	MessageUtil.showQuestionMes(i18n('i18n.complaint.newBasicInfo.sureToDeleteOne')+selection.length+i18n('i18n.complaint.newBasicInfo.sureToDeleteTwo'), function(e) {
			            		if (e == 'yes') {
			            			//调用后台删除基础资料的action
	            					var successFn = function(json){
	            						basicInfoResultStore.remove(selection);
	            						Ext.getCmp('basicInfoResultGridId').store.loadPage(1);
	            						MessageUtil.showInfoMes(i18n('i18n.complaint.newBasicInfo.deleteSuccess'));
	            					}
	            					
	            					var failureFn = function(json){
	            				    	MessageUtil.showErrorMes(json.message);
	            				    };
	            				    //待删除的基础资料列表
	            				    var basicInfoArray = new Array();
	            				    //将投诉业务项列表表格中的数据放入到业务项列表数组中
	            					for(var i = 0;i < selection.length;i++){
	            						basicInfoArray.push(selection[i].data);
	            				    }
	            					var param = {
	            				    	'basicInfoList':basicInfoArray
	            				    };
	            					BasicInfoStore.prototype.deleteBasicInfo(param,successFn,failureFn);	
			            		}
			            	});
			            }
					  }
				  }
				]
			   },{
				 xtype:'middlebuttonpanel' 
			   }];
		}
	});
	
	/**
	 * 基础资料查询结果列表
	 */
	Ext.define('BasicInfoResultGrid',{
		extend:'SearchGridPanel',
		defaults:{
			align:'center'
		},
		autoScroll:true,
		selModel:Ext.create('Ext.selection.CheckboxModel'),
		initComponent:function(){ 
			var me = this;
			var store = Ext.create('BasicInfoResultStore',{id:'basicInfoResultStoreId'});
			store.on('beforeload',function(store,operation,e){					
			  //封装查询条件传递给action
			  var basicInfoQueryForm = Ext.getCmp("basicInfoQueryPanelId");
			  var basicType = basicInfoQueryForm.getForm().findField('basicType').getValue();
			  var basicContent = basicInfoQueryForm.getForm().findField('basicContent').getValue();
			  var searchParams = {
						'bsc.basicType':basicType,
						'bsc.basicContent':basicContent
				};
				Ext.apply(operation,{
						params : searchParams
				});
			});		
			me.store = store;
			me.columns = [
			    {//上报类型
					header:i18n('i18n.complaint.newBasicInfo.reportType'),
					width:80,
					dataIndex:'reportType',
					renderer:function(val){
						if(val==='UNUSUAL'){
							return '异常';
						}else if(val==='COMPLAINT'){
							return '投诉';
						}else{
							return '';
						}
					}
			    },{ //业务项
					header :i18n('i18n.complaint.newBasicInfo.businessItems'),
					dataIndex:'busItemName',
					width:120
			    },{//业务范围
					header :i18n('i18n.complaint.newBasicInfo.busScopeName'),
					dataIndex:'busScopeName',
					width:120
			    },{//业务类型
					header :i18n('i18n.complaint.newBasicInfo.busTypeName'),
					dataIndex:'busTypeName',
					width:120
			    },{//处理语言
					header :i18n('i18n.complaint.newBasicInfo.dealLanguage'),
					dataIndex:'dealLanguage',
					width:200,
					maxLength:300,
					renderer:function(value){
						if(!Ext.isEmpty(value)){
							return '<span data-qtip="'+value+'">'+value+'</span>';
						}
					}
			    },{//反馈时限
					header :i18n('i18n.complaint.newBasicInfo.feedbackLimit'),
					dataIndex:'feedbackLimit',
					align:'center',
					width:110
			    },{//处理时限
					header :i18n('i18n.complaint.newBasicInfo.procLimit'),
					dataIndex:'procLimit',
					align:'center',
					width:110
			    }
		    ];
			me.dockedItems=[{
				xtype:'pagingtoolbar',
				cls:'pagingtoolbar',
				store:store,
				dock:'bottom',
				displayInfo : true,
				autoScroll : true,
				items:[{//每页显示
					text: i18n('i18n.complaint.newBasicInfo.itemPerPage'),
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
					text: i18n('i18n.complaint.newBasicInfo.item'),
					xtype: 'tbtext'
	           }]
		}];
		//默认显示所有基础资料查询列表
		store.loadPage(1);
	    this.callParent();
	   }
	});
	
	/*
	 * 新建一个viewport，用于显示处理结果基础资料主页面
	 * 肖红叶
	 */
	var viewport=Ext.create('Ext.Viewport',{
		layout : 'border',
		items:[
	        {//基础资料查询条件输入面板
	        	xtype:'container',
				region:'north',
				layout:'fit',
				items:[Ext.create('BasicInfoQueryPanel',{id:'basicInfoQueryPanelId'})]
			},{
				xtype:'container',
				region:'center',
				layout:'border',
				items:[
					{//按钮面板
						xtype:'container',
						region:'north',
					    layout:'fit',
					    items:[Ext.create('BasicInfoButtonPanel',{id:'basicInfoButtonPanelId'})]
					},{//基础资料查询结果表格
						xtype:'container',
						region:'center',
			    	    layout:'fit',
			    	    items:[ Ext.create('BasicInfoResultGrid',{id:'basicInfoResultGridId'})]
			        }	
			   ]
			}
	    ]
	});
	
	viewport.setAutoScroll(false);	
	viewport.doLayout();
	
	/**
	 *基础资料查看详情panel
	 */
	Ext.define('BasicInfoDetailPanel',{
		extend:'PopTitleFormPanel', 
		items:null,
		border:0,
		layout:{
			type:'table',
			columns:3
		},
		defaultType:'readonlytextfield',
		defaults:{
			labelWidth:60,
			labelAlign:'right',
			width:240,
			margin:'0 5 5 0'
		},
		initComponent:function(){			
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [
				{//上报类型
					fieldLabel : i18n('i18n.complaint.newBasicInfo.reportType'),
					name : 'reportType'	
				},{//业务项
					fieldLabel : i18n('i18n.complaint.newBasicInfo.businessItems'),
					name : 'busItemName'
				},{//业务范围
					fieldLabel : i18n('i18n.complaint.newBasicInfo.busScopeName'),
					name : 'busScopeName'
				},{//业务类型
					fieldLabel : i18n('i18n.complaint.newBasicInfo.busTypeName'),
					name : 'busTypeName'
				},{//反馈时限
					fieldLabel : i18n('i18n.complaint.newBasicInfo.feedbackLimitWithOutUnit'),
					name : 'feedbackLimit'
				},{//处理时限
					fieldLabel : i18n('i18n.complaint.newBasicInfo.procLimitWithOutUnit'),
					name : 'procLimit'
				},{//处理语言
					xtype:'readonlytextarea',
					width:485,
					colspan:2,
					fieldLabel:i18n('i18n.complaint.newBasicInfo.dealLanguage'),
					height:90,
					name:'dealLanguage'
				}
		    ];
		}
	});	


	/**
	 *  单击“查看详情”按钮，弹出的查看详情窗口
	 * xiaohongye
	*/
	Ext.define('BasicInfoDetailWindow',{
		extend:'PopWindow',
		title:i18n('i18n.complaint.newBasicInfo.detailTitle'),
		alias : 'widget.basicwindow',
		width:780,
		height:240,
		modal:true,
		layout:'fit',
		closeAction:'hide',
		items:[Ext.create('BasicInfoDetailPanel',{id:'basicInfoDetailPanelId'})],
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
				{//关闭
					xtype:'button',
					text:i18n('i18n.complaint.newBasicInfo.close'),
					handler:function(){
						var basicInfoDetailForm = Ext.getCmp('basicInfoDetailPanelId').getForm().reset();
						basicInfoDetailWindow.close();
					}
			}]
		}]
	});

	var basicInfoDetailWindow = Ext.getCmp("basicInfoDetailWindowId");//获取工单基础资料查看详情窗口
	if(!basicInfoDetailWindow){
		basicInfoDetailWindow = Ext.create('BasicInfoDetailWindow',{id:'basicInfoDetailWindowId'});
	}
	
});