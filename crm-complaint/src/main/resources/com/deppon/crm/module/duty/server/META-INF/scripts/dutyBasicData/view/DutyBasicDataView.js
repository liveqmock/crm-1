var loadTypeIsAutoLoad = true; //默认加载 自动 

/**
 * 基础数据管理页面
 */
Ext.onReady(function(){	
	//初始化提示信息条
	Ext.QuickTips.init();
	
	var keys=[
		'REPORT_TYPE' /*上报类型*/	
  	];
	//初始数据字典
	initDataDictionary(keys);
	
	/**
	 *基础资料查询条件输入面板form
	 */
	Ext.define('DutyBasicDataQueryPanel',{
		extend:'SearchFormPanel', 
		items:null,
		border:0,
		layout:{
			type:'table',
			columns:3
		},
		initComponent:function(){			
			this.items = this.getItems();
			this.callParent(arguments);
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
			    	        {"code":"001", "codeDesc":i18n('i18n.duty.busItemName')},//业务项
			    	        {"code":"002", "codeDesc":i18n('i18n.duty.busScopeName')},//业务范围
			    	        {"code":"003", "codeDesc":i18n('i18n.duty.result.busType')}//业务类型
			    	    ]
			    	}),
			    	queryMode:'local',
			    	editable:false,displayField:'codeDesc',
			    	value:'001',
					valueField:'code',margin:'0 10 0 0',
					id:'fieldName'
				},{//查询条件内容输入
					xtype:'textfield',
					fieldLabel:'',labelWidth:2,
			    	width:180,margin:'0 10 0 0',
					id:'fieldValue'
				},{//查询按钮
					xtype:'button',
					text:i18n('i18n.duty.search'),//查询
					handler:function(){
						var fieldName = Ext.getCmp('fieldName').getValue();
						var fieldValue = Ext.getCmp('fieldValue').getValue();
						if(Ext.isEmpty(fieldName) ||　Ext.isEmpty(fieldValue)){
							MessageUtil.showMessage(i18n('i18n.DutyBasicDataView.pleaseInputSearchCondition'));return;//请输入查询条件
						}
						var params = {
								'basicSearchCondition.basicType':fieldName,
								'basicSearchCondition.basicContent':fieldValue
						};
						var successFn = function(data){
							if(!data.totalCount || data.totalCount=='0'){
								MessageUtil.showMessage(i18n('i18n.DutyBasicDataView.notComplyWithTheConditionsOfTheQuery'));return;//没有符合该查询条件的内容
							}
							loadTypeIsAutoLoad = false;/* 手动点击查询按钮 */
							Ext.getCmp('dutyBasicDataResultGridId').getStore().loadPage(1);
						};
						var failFn = function(){
						};
						ActionFunction.basicInfo_searchBasicInfoDetails(params,successFn,failFn);
					}
				}
		    ];
		}
	});	
	
	//基础数据管理按钮面板
	Ext.define('DutyBasicDataButtonPanel',{
		extend:'NormalButtonPanel',
		border:false,
		initComponent:function(){
			this.items = this.getItems();
			this.callParent(arguments);
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
					  text:i18n('i18n.duty.look_up'),
					  handler:function(){
					  	//验证显示数据
						var selection  = Ext.getCmp('dutyBasicDataResultGridId').getSelectionModel().getSelection();
						if(Ext.isEmpty(selection) || selection.length!=1){
							MessageUtil.showMessage(i18n('i18n.DutyAllocationSearchView.pleaseSelectOneData'));return;
						}
						var dutyBasicDataDetailWindow = Ext.getCmp("dutyBasicDataDetailWindowId");//获取工单详情窗口
						if(!dutyBasicDataDetailWindow){
							dutyBasicDataDetailWindow = Ext.create('DutyBasicDataDetailWindow',{
								id:'dutyBasicDataDetailWindowId',
								listeners:{
									show:function(){
										//form 显示数据
										var selection  = Ext.getCmp('dutyBasicDataResultGridId').getSelectionModel().getSelection();
										Ext.getCmp('basicDataDetailPanelId').getForm().loadRecord(new BasicInfoResultModel(selection[0].data));
									}
								}
							});
						}
						dutyBasicDataDetailWindow.show();
					  }
				  },{//业务项设置
					  xtype:'button',
					  text:i18n('i18n.DutyBasicDataView.businessLinksSet'),//业务项设置
					  handler:function(){
						var dutyBusinessProcessWindow = Ext.getCmp("dutyBusinessProcessWindowId");//获取工单详情窗口
						if(!dutyBusinessProcessWindow){
							dutyBusinessProcessWindow = Ext.create('DutyBusinessProcessWindow',{id:'dutyBusinessProcessWindowId'});
						}
						dutyBusinessProcessWindow.show();
					  }
				  },{//基础资料设置
					  xtype:'button',
					  text:i18n('i18n.DutyBasicDataSettingView.basicSet'),//基础资料设置
					  handler:function(){
					  	var selection  = Ext.getCmp('dutyBasicDataResultGridId').getSelectionModel().getSelection();
						if(!Ext.isEmpty(selection) && selection.length > 1){
							MessageUtil.showMessage(i18n('i18n.DutyAllocationSearchView.pleaseSelectOneData'));return;//请选择数据一条数据
						}
						var basicInfo = null;
						if(!Ext.isEmpty(selection) && selection.length == 1){
							basicInfo = Ext.clone(selection[0].data);
						}
					  	var dutyBasicDataSettingWindows = Ext.create('DutyBasicDataSettingWindows',{
							id:'dutyBasicDataSettingWindowsId',isUpdate:!Ext.isEmpty(basicInfo),
							items:[Ext.create('DutyBasicDataSettingPanel',{
								'id':'dutyBasicDataSettingPanelId',
								isUpdate:!Ext.isEmpty(basicInfo)
							})],
							listeners:{
								beforeclose:function(){
									//加载 分页集合
									Ext.getCmp('dutyBasicDataResultGridId').getStore().load();
								},
								show:function(){
									if(Ext.isEmpty(basicInfo)){return;}
									
									{//基础信息
										var formPanel = Ext.getCmp('dutyBasicDataSettingFormId');
										formPanel.getForm().loadRecord(new BasicInfoResultModel(basicInfo));
										if(basicInfo.reportType === ReportType.unusual){
											Ext.getCmp('unusualReportTypeId').setValue(true);
										}
									}
									{//load 基础数据
										var params = {
											'basicBusScopeVO.busItemId':basicInfo.busItemId,
											'basicBusScopeVO.busScopeId':basicInfo.busScopeId
										};
										//执行成功
										var successFn = function(response){
											var store = Ext.getCmp('dutyBasicDataSettingGridId').getStore();
											store.loadData(response.basicBusScopeVO.busTypes);
										}
										//执行失败
										var failFn = function(response){
											MessageUtil.showErrorMes(response.message);
										}
										ActionFunction.basicInfo_searchBusTypeList(params,successFn,failFn)
									}
								}
							}
						});
						dutyBasicDataSettingWindows.show();
					  }
				  },{//删除
					  xtype:'button',
					  text:i18n('i18n.duty.delete'),
					  handler:function(){
							var grid  = Ext.getCmp('dutyBasicDataResultGridId');
							var selection = grid.getSelectionModel().getSelection();
							if(Ext.isEmpty(selection) || selection.length==0){
								MessageUtil.showMessage(i18n('i18n.DutyBasicDataSettingView.selectDate'));return;//请选择数据
							}
						
							var parmArray = new Array();
							var check_result = {'flag':true};
							Ext.each(selection,function(record){
								if(Ext.isEmpty(record.get('busTypeName'))){
					    			check_result = {
					    				'flag':false,'msg':i18n('i18n.DutyBasicDataView.dataYouChoosePleaseRelect')//您选择的数据有问题，请重新选择~
					    			};return false;
					    		}
								parmArray.push(record.data);
							});
							if(!check_result.flag){//验证表格数据是否为空
					    		MessageUtil.showMessage(check_result.msg);return;
					    	}
							MessageUtil.showQuestionMes(i18n('i18n.DutyFeedbackView.youSureDeleteSelected')+selection.length+i18n('i18n.DutyBasicDataSettingView.tiaoRecord'),function(e){//您确定删除所选的//条记录
								if(e != 'yes'){return;}
								var params = {
									'basicInfoList':parmArray
								};
								var successFn = function(){
									MessageUtil.showInfoMes(i18n('i18n.DutyBasicDataSettingView.deleteDataSuccess'));//删除数据成功
									grid.getStore().loadPage(1);
								};
								var failFn = function(){
									MessageUtil.showMessage(i18n('i18n.DutyBasicDataSettingView.deleteDataFailure'));//删除数据失败
								};
								ActionFunction.basicInfo_deleteListBusTypeByIds(params,successFn,failFn);
							});
					  }
				  }
				]
			   },{xtype:'middlebuttonpanel'}
			   ];
		}
	});
	
	/**
	 * 基础资料查询结果列表
	 */
	Ext.define('DutyBasicDataResultGrid',{
		extend:'SearchGridPanel',
		defaults:{align:'center'},
		autoScroll:true,
		selModel:Ext.create('Ext.selection.CheckboxModel'),
		initComponent:function(){ 
			var me = this;
			me.store = Ext.create('BasicInfoResultStore',{
				listeners:{
					beforeload:function(store, operation, eOpts){
						if(!loadTypeIsAutoLoad || loadTypeIsAutoLoad===false){
							Ext.apply(operation,{params:{
								'basicSearchCondition.basicType':Ext.getCmp('fieldName').getValue(),
								'basicSearchCondition.basicContent':Ext.getCmp('fieldValue').getValue()
							}});
						}
					}
				}
			});
			me.columns = me.getColumns();
			me.dockedItems= me.getMyDockedItems();
	    	this.callParent(arguments);
	   },
	   getColumns:function(){
	   		var me = this;
	   		return [
			   {xtype:'rownumberer',header:i18n('i18n.Duty.resultDutyPanel.rownumberer'),width:40/*序号*/},
			   {
					header:i18n('i18n.duty.reportType')/*上报类型*/,width:100,dataIndex:'reportType'
					,renderer:function(value){
						return rendererDictionary(value,DataDictionary.REPORT_TYPE);
					}
			   },
			   {header :i18n('i18n.duty.busItemName'),dataIndex:'busItemName',width:100/*业务项*/},
			   {header :i18n('i18n.duty.busScopeName')/*业务范围*/,dataIndex:'busScopeName',width:100/*业务范围*/},
			   {header :i18n('i18n.duty.result.busType'),dataIndex:'busTypeName',width:100/*业务类型*/},
			   {header :i18n('i18n.duty.result.deallan'),dataIndex:'dealLanguage',flex:1,renderer:function(value) {
					if(!Ext.isEmpty(value)){
						var val = '<span data-qtip="'+value+'">'+value+'</span>';
						return val;
					}
				}/*处理语言*/},
			   {
			   		header :i18n('i18n.duty.result.reworpusType'),dataIndex:'reworpusType',width:100,//奖罚类型
			   		renderer:function(value){
						return ReworpusType.getReworpusTypeName(value);
					}/*奖罚类型*/
			   },
			   {header :i18n('i18n.duty.result.feedtimeLimit'),dataIndex:'feedbackLimit',width:120/*反馈时限*/}
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
	
	/*
	 * 新建一个viewport，用于显示工单责任基础资料界面
	 * 肖红叶
	 */
	var viewport=Ext.create('Ext.Viewport',{
		layout : 'border',
		items:[
	        {//工单责任基础资料查询条件输入面板
	        	xtype:'container',
				region:'north',
				layout:'fit',
				items:[Ext.create('DutyBasicDataQueryPanel',{id:'dutyBasicDataQueryPanelId'})]
			},{
				xtype:'container',
				region:'center',
				layout:'border',
				items:[
					{//按钮面板
						xtype:'container',
						region:'north',
					    layout:'fit',
					    items:[Ext.create('DutyBasicDataButtonPanel',{id:'dutyBasicDataButtonPanelId'})]
					},{//基础资料查询结果表格
						xtype:'container',
						region:'center',
			    	    layout:'fit',
			    	    items:[ Ext.create('DutyBasicDataResultGrid',{id:'dutyBasicDataResultGridId'})]
			        }	
			   ]
			}
	    ]
	});
	
	viewport.setAutoScroll(false);	
	viewport.doLayout();
	
	/**
	 *基础资料详情panel
	 */
	Ext.define('DutyBasicDataDetailPanel',{
		extend:'SearchFormPanel', 
		items:null,
		border:0,
		layout:{type:'table',columns:3},
		defaultType:'readonlytextfield',
		defaults:{
			labelWidth:60,
			labelAlign:'right',
			width:240,
			margin:'0 5 5 0'
		},
		initComponent:function(){
			var me = this;
			me.items = me.getItems();
			this.callParent(arguments);
		},
		getItems:function(){
			var me = this;
			return [
				{/*上报类型*/
					fieldLabel:i18n('i18n.duty.reportType')/*上报类型*/,name:'reportType',
					xtype:'combobox',readOnly:true,cls:'readonly',
					displayField:'codeDesc',valueField:'code',
					//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
					store:getDataDictionaryByName(DataDictionary,'REPORT_TYPE')	
				},/*上报类型*/
				{fieldLabel:i18n('i18n.duty.busItemName'),name:'busItemName'},/*业务项*/
				{fieldLabel:i18n('i18n.duty.busScopeName')/*业务范围*/,name:'busScopeName'},/*业务范围*/
				{fieldLabel:i18n('i18n.duty.result.busType'),name:'busTypeName'},/*业务类型*/
				{
					fieldLabel:i18n('i18n.duty.result.reworpusType'),name:'reworpusType',
					xtype:'combobox',readOnly:true,cls:'readonly',
					displayField:'codeDesc',valueField:'code',
					//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
					store:{
	                	xtype:'store',fields:['code','codeDesc'],
	                	data:ReworpusType.list
	                }
				},/*奖罚类型*/
				{fieldLabel:i18n('i18n.duty.result.feedtimeLimit'),name:'feedbackLimit'},/*反馈时限*/
				{
					xtype:'readonlytextarea',width:485,colspan:2,
					fieldLabel:i18n('i18n.duty.result.deallan'),
					height:70,name:'dealLanguage'
				}/*处理语言*/
		    ];
		}
	});	


	/**
	 *  单击“查看详情”按钮，弹出的查看详情窗口
	 * xiaohongye
	*/
	Ext.define('DutyBasicDataDetailWindow',{
		extend:'PopWindow',
		title:i18n('i18n.DutyBasicDataView.workOrderDivisionOfResponsibilitiesFoundationDetails'),//工单责任划分基础资料详情
		width:780,
		height:240,
		modal:true,
		layout:'fit',
		closeAction:'hide',
		items:[Ext.create('DutyBasicDataDetailPanel',{id:'basicDataDetailPanelId'})],
		listeners:{
			hide:function(){
				document.body.scrollLeft=0;
				document.body.scrollTop=0;
				document.getElementsByTagName("html")[0].style.overflowY="hidden";
				document.getElementsByTagName("html")[0].style.overflowX="hidden";
			}
		},
		initComponent:function(){ 
			var me = this;
			me.buttons = [{
				xtype:'button',text:i18n('i18n.duty.cancel'),//取消
				handler:function(){me.close();}
			}];
			this.callParent(arguments);
	   }
	});
});


