//工单回访管理界面
var complaintVerifyTaskDataControl = (CONFIG.get("TEST"))
		? new ComplaintVerifyTaskDataTest()
		: new ComplaintVerifyTaskData();
var viewport = null;/* 视图容器 */
var defaultSearchVerify=true;//默认查询当前用户回访

/**
 * 工单回访按钮点击事件
 */
var compVerifyRegister_btnClick = function(btn){
	btn.setDisabled(true);processingMask.show();
	
	var sm = Ext.getCmp('ComplaintVerifyTaskResultGridId').getSelectionModel();
	var record = sm.getSelection()[0];
	//执行成功
	var successFn = function(response){
		processingMask.hide();//隐藏进度条信息
		response.otherComplint['complaint'] = response.complaint;
		// 调用客户工单详情 弹出框
		Ext.create('VerifyTaskRegisterWindow',{
			'complaint':response.complaint,
			listeners:{
				destroy:function(){
					btn.setDisabled(false);
				}
			}
		}).show();
	}

	//执行失败
	var failFn = function(){
		btn.setDisabled(false);processingMask.hide();//隐藏进度条信息
		MessageUtil.showErrorMes(i18n('i18n.alert_message_dataNotExist'));
	}
	var params={
		'fid' : record.get('fid')
	};
	DpUtil.requestJsonAjax('searchComplaintAndOther.action',params,successFn,failFn);
};

 
Ext.define('complaintVerifyTaskPanel', {
			extend : 'BasicPanel',
			parent : null,
			complaintVerifyTaskConditionForm : null,// 查询条件
			complaintVerifyPanel:null,
			complaintVerifyTaskData : null,// 数据Data
			layout:'border',
			initComponent : function() {
				var me = this;
				var record = new ComplaintVerifyTaskModel();
				me.complaintVerifyTaskConditionForm = Ext.create(
						'ComplaintVerifyTaskConditionForm', {
							'id':'complaintVerifyTaskConditionFormId',
							'parent' : me,
							'record' : record,
							'complaintVerifyTaskData' : me.complaintVerifyTaskData,
							'region':'north'
						});
						
				me.complaintVerifyPanel = Ext.create('ComplaintVerifyPanel',{
					'region':'center','parent':me,'complaintVerifyTaskData':me.complaintVerifyTaskData
				});
				Ext.applyIf(me,{
					'items':[me.complaintVerifyTaskConditionForm,me.complaintVerifyPanel]
				});		
				me.callParent(arguments);
			}
		});

Ext.define('ComplaintVerifyPanel',{
	extend:'BasicPanel'
	,parent:null // 父级
	,complaintVerifyTaskResultGrid:null  //查询结果
	,complaintVerifyBtnPanel:null//查询按钮面板
	,complaintVerifyTaskData:null
	,layout:'border'
	,initComponent:function(){
		var me = this;
				me.complaintVerifyBtnPanel=Ext.create(
					'ComplaintVerifyBtnPanel',{region:'north'}
				)						
				me.complaintVerifyTaskResultGrid = Ext.create(
						'ComplaintVerifyTaskResultGrid', {region:'center',
							'parent' : me,
							'complaintVerifyTaskData' : me.complaintVerifyTaskData
						});
						
				me.complaintVerifyTaskResultGrid.on('cellclick', function(thiz, row, col, e) {
					var fieldName=thiz.getHeaderCt().getHeaderAtIndex(col).dataIndex;
	   				if(fieldName=="recomcode"){//重复工单码
	   					var complaintRecord = e.data;
			   		    if(!DpUtil.isEmpty(complaintRecord.recomcode)){//加载重复工单码查询
							//查询重复工单 点击事件
							Ext.create('RepeatComplaintWindow',{
								'complaint':complaintRecord
							}).show();
						}
	   				}
					if (fieldName == "compman") {//显示来电人信息
							show_comp_detailsWin(e.get('fid'));/* 调用工单详情-弹出框 */
					}
				});				
		Ext.applyIf(me,{
			'items':[me.complaintVerifyBtnPanel,me.complaintVerifyTaskResultGrid]
		});
		me.callParent(arguments);		
	}
});
		
Ext.onReady(function() {
		//页面需要加载的数据字典数组
			var keys=[
				//解决情况
			    'RESOLVE_CASE',
			    //客户满意度
			    'SATISFACTION_DEGREE',		
			    //上报类型
			    'REPORT_TYPE',
			    //优先级别
			    'PRIORITY_RATING',
			    //工单来源
			    'WORK_SINGLE_SOURCE',
			     //延迟时间
			    'DELAY_APPLY',
			    //工单模块处理状态
			    'COMPLAINT_PROCESS_STATUS',
			    'COMPLAINT_RECORD_TYPE'//工单模块反馈类型
			    //期望时限    
			    ,'EXPECTED_TIME_LIMIT'
			     //投诉级别
			    ,'COMPLAINT_LEVEL'
			    ,'COMPLAINT_LINK',	/* 业务环节 */
		         // 客户类型
		      	'CUSTOMER_TYPE',
		  		// 目标级别',目前级别,客户等级',会员等级
		  		'MEMBER_GRADE',
		        //来电 人类型
			    'CALLER_TYPE',
			     // 业务类型
      			'COMPLAINT_BUSINESS_MODEL'
		  	];
			//初始数据字典
			initDataDictionary(keys);
		
			var chooseSelModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE',id:'chooseSelModelId',allowDeselect:true});
			
			/**
			 * 工单回访查询结果
			 */
			Ext.define('ComplaintVerifyTaskResultGrid', {
				extend : 'SearchGridPanel',
				parent : null,
				id:'ComplaintVerifyTaskResultGridId',
				complaintVerifyTaskData : null,// 数据Data
				selModel : chooseSelModel,
				initComponent : function() {
					var me = this;
					me.store = me.complaintVerifyTaskData
							.initComplaintVerifyTaskSearchStore(me.beforeLoadScatterFn);
					me.dockedItems = me.getMyDockedItems();
					me.columns = me.getColumns();
					this.callParent();
				},
				getColumns : function() {
					var me = this;
					return [
							{xtype:'rownumberer',header:i18n('i18n.complaint.serial_number'),width:40},
							{header:i18n('i18n.complaint.businessType'),dataIndex:'businessModel',
								renderer:function(value){
									return rendererDictionary(value,DataDictionary.COMPLAINT_BUSINESS_MODEL);
								}
							},{
								header : i18n('i18n.complaint.comp_bill'),
								dataIndex : 'bill'
								,width:70
							}, {
								header : i18n('i18n.complaint.comp_dealbill'),
								dataIndex : 'dealbill'
								,width:75
							}, {
								header : i18n('i18n.complaint.comp_recomcode'),
								dataIndex : 'recomcode'
								,width:70
								,renderer:function(value){
				  					return '<a href="javascript:void(0);">'+value+'</a>';
				  				}								
							}, {
								header : i18n('i18n.complaint.comp_reportType'),
								dataIndex : 'reporttype'
								,width:60
								,renderer:function(value){
				  					return rendererDictionary(value,DataDictionary.REPORT_TYPE);
				  				}
							}, {
								header : i18n('i18n.complaint.comp_compman'),
								dataIndex : 'compman'
								,width:60
								,renderer:function(value){
				  					return '<a href="javascript:void(0);">'+value+'</a>';
				  				}								
							},{
								header:'客户等级',
								dataIndex:'custlevel',
								width:60,
								renderer:function(value){
				  					return rendererDictionary(value,DataDictionary.MEMBER_GRADE);
				  				}
							
							},{
								header : i18n('i18n.complaint.comp_pririty'),
								dataIndex : 'pririty'
								,width:60
								,renderer:function(value){
				  					return rendererDictionary(value,DataDictionary.PRIORITY_RATING);
				  				}
							}, {
								header : i18n('i18n.complaint.comp_complevel'),
								dataIndex : 'complevel'
								,width:60
								,renderer:function(value){
				  					return rendererDictionary(value,DataDictionary.COMPLAINT_LEVEL);
				  				}
							}, {
								header : i18n('i18n.complaint.comp_prostatus'),
								dataIndex : 'prostatus'
								,width:60
								,renderer:function(value){
				  					return rendererDictionary(value,DataDictionary.COMPLAINT_PROCESS_STATUS);
				  				}
							}, {
								header : i18n('i18n.complaint.comp_dealstatus'),
								dataIndex : 'dealstatus'
								,width:60
								,renderer:function(value){
				  					return rendererDictionary(value,DataDictionary.RESOLVE_CASE);
				  				}
							}, {
								header : i18n('i18n.complaint.comp_dealman'),
								dataIndex : 'dealman'
								,width:60
							},{
								header : i18n('i18n.complaintVerifyTask.processTime'),
								dataIndex : 'dealtime'
								,width:115
								,renderer:function(value) {
									if(value != null){
										return Ext.Date.format(new Date(value), 'Y-m-d H:i');
									}else{
										return null;
									}
								}
							},{
								header : i18n('i18n.complaint.comp_tel'),
								dataIndex : 'tel'
								,width:60
								,hidden:true
							}];
				},
				// 分页条
				getMyDockedItems : function() {
					var me = this;
					return [{
								xtype : 'pagingtoolbar',
								store : me.store,
								dock : 'bottom',
								displayInfo : true
							}];
				},
				// beforeLoad方法
				beforeLoadScatterFn : function(store, operation, eOpts) {
					var searchForm = Ext.getCmp('complaintVerifyTaskConditionFormId').getForm();
					if(searchForm!=null){
						var searchParams = {};
						searchParams = {
							'complaintSearchCondition.dealbill':searchForm.findField('dealbill').getValue()
							,'complaintSearchCondition.bill':searchForm.findField('bill').getValue()
							,'complaintSearchCondition.reportType':searchForm.findField('reportType').getValue()
							,'complaintSearchCondition.businessModel':searchForm.findField('verifyTaskBusinessType').getValue()
							,'defaultSearchVerify':defaultSearchVerify
						};
						Ext.apply(operation,{params : searchParams});	
					}
				}
			});
			
		    /**
			 * 查询条件
			 */
			Ext.define('ComplaintVerifyTaskConditionForm',{			
				extend:'SearchFormPanel',
				height:50,
				items:null,
				layout:{ 
					type:'table',
					columns:4
				},
				defaultType:'textfield',
				initComponent:function(){
					var me = this;
					me.defaults = me.getDefaultsContainer();
					this.items = this.getItems();
					this.callParent();
					Ext.getCmp('verifyTaskBusinessType').getStore().add({'code':'','codeDesc':'全部'});
					Ext.getCmp('verifyTaskBusinessType').setValue('');
				},
				getItems:function(){
					var me = this;
					return [
							{
							   name:'dealbill',fieldLabel:i18n('i18n.complaint.comp_dealbill')
							},
							{
							   name:'bill',fieldLabel:i18n('i18n.complaint.comp_bill')
							},{
								xtype:'combobox',name:'reportType',fieldLabel:i18n('i18n.complaint.comp_reportType')
								,store:getDataDictionaryByName(DataDictionary,'REPORT_TYPE')
								,queryMode: 'local',displayField:'codeDesc',valueField:'code'
							},{
								xtype:'combo',
								name:'verifyTaskBusinessType',
								id:'verifyTaskBusinessType',
								displayField:'codeDesc',
							    valueField:'code',
							    queryMode:'local',
							    fieldLabel:i18n('i18n.complaint.businessType'),
							    editable:false,
							    store:getDataDictionaryByName(DataDictionary,'COMPLAINT_BUSINESS_MODEL')
							}
				]},
				//增加监听事件
				getDefaultsContainer:function(){
					var me = this;
					return {
						labelWidth:60,
						width:195,
						enableKeyEvents:true,
						listeners:{
							scope : me,
							keypress : me.keypressEvent
						}
					};
				},
				//监听按键事件
				keypressEvent:function(field,event){
					var me = this;
					if(event.getKey() == Ext.EventObject.ENTER){
			    		me.parent.complaintVerifyBtnPanel.fnSearch();
			    	}
				}
			});
			
			/**
			 * 查看按钮 panel
			 */
			Ext.define('ComplaintVerifyBtnPanel',{
				extend:'NormalButtonPanel'
				,parent:null   //父级
				,initComponent:function(){
					var me = this;
					me.items = me.getItems();
					this.callParent(arguments);
				}
				,getItems:function(){
					var me = this;
					return [{
						xtype:'leftbuttonpanel',
						items:[{
							xtype:'button',text:i18n('i18n.complaintVerifyTask.btnConfirm'),handler:function(){
									  		processingMask.show();//显示进度条信息
											var sm = Ext.getCmp('ComplaintVerifyTaskResultGridId').getSelectionModel();
											if (sm.getSelection().length > 0) {
												this.action = 'update';
												var record = sm.getSelection()[0];
												show_comp_detailsWin(record.get('fid'));
												Ext.getCmp('verifyTaskRegisterId').show();
												
										} else {
											processingMask.hide();//隐藏进度条信息
											MessageUtil.showMessage(i18n('i18n.complaintReportReturnProcess.noRecord'));
										}
									 }
						},{
							xtype:'button',text:i18n('i18n.complaintVerifyTask.calling'),handler:function(){
										var sm = Ext.getCmp('ComplaintVerifyTaskResultGridId').getSelectionModel();
										if (sm.getSelection().length > 0) {
											var record = sm.getSelection()[0];
												var url1='/ssoURLRequest?app=hollycrm&url=hollycc/call/out/call_out_crm.jsp?'+
												'complaintId='+record.data.fid+'&phone='+record.data.tel
											parent.clickTabpanel('phoneCallout',i18n('i18n.complaintVerifyTask.outCalling'),url1);
										} else {
											MessageUtil.showMessage(i18n('i18n.complaintReportReturnProcess.noRecord'));
										}
									 }
						}]						
						}
						,{xtype:'middlebuttonpanel'}
						,{xtype:'rightbuttonpanel',items:{xtype:'button',text:i18n('i18n.complaint.search'),scope:me,handler:me.fnSearch}}
					];
				}
				,fnSearch:function(){
					defaultSearchVerify=false;//不是默认查询
					Ext.getCmp('ComplaintVerifyTaskResultGridId').store.loadPage(1);
				}
			});
			
			viewport = Ext.create('Ext.container.Viewport', {
						layout : 'border','autoScroll':true,
						items : [
									Ext.create('complaintVerifyTaskPanel', {
											'complaintVerifyTaskData' : complaintVerifyTaskDataControl,
											region:'center'
									})
								]
		    });
		    viewport.setAutoScroll(false);
			viewport.doLayout();
	
		    Ext.define('ComplaintTaskDetailsWindow', {
			    extend: 'PopWindow'
			    ,y:0
			    ,x:0
			    ,id:'complaintTaskDetailsWindowId'
				,'complaintData':{
					complaint:null //客户工单信息
					,bulletinList:null   //通报对象
					,paraBasciLevel:null   //业务范围
					,basciLevel:null   //业务类型
				}
				
				,complaintBasicFormPanel:null/* 显示基本信息  formPanel */
				,surveySuggestGrid:null   /* 调查建议*/
				,baseInfoLevelPanel:null	/*业务类型、业务范围、投诉级别*/
				,complaintFourPanel:null   //第四块
				,complaintBtnFivePanel:null     // 第五块
				,processResultPanel:null/*处理结果Panel */
				,processRecordPanel:null/*处理记录记录Panel */
//				,delayApplyPanel:null	//延时申请
				
				,width:820,height:790
			    ,layout: {align:'stretch',type:'vbox'},modal:true
				,title:i18n('i18n.complaintVerifyTask.detailPage')
			    ,initComponent: function() {
			    	
			        var me = this;
			      	this.complaintBasicFormPanel = Ext.create('ComplaintBasicFormPanel',{
						'complaint':me.complaintData.complaint
					});
					this.surveySuggestGrid = new Ext.create('SurveySuggestGrid',{'complaint':me.complaintData.complaint});
					this.baseInfoLevelPanel = Ext.create('BaseInfoLevelPanel',{
						isEditable:false,'complaint':me.complaintData.complaint,padding:'0 0 0 5',forceSelectionStatus:false
					});
					this.complaintFourPanel = Ext.create('ComplaintHiddenFourPanel');
					
					{// 通知对象显示
						var searchParams = {
							complaintSearchCondition:{'fid':me.complaintData.complaint.fid}
						};
						//执行成功
						var successFn = function(response){
							var bulletinValue = "";
							var bulletinList = response.bulletinList;
							for(var i=0;i<bulletinList.length;i++){
								bulletinValue +=  bulletinList[i].bulletinname + ',';
							}
							bulletinValue = bulletinValue.substring(0,bulletinValue.length-1);
							Ext.getCmp('bulletinId').setValue(bulletinValue);
						}
						
						//执行失败
						var failFn = function(response){
							MessageUtil.showErrorMes(response.message);
						}
						DpUtil.requestJsonAjax('searchBulletinListByCompId.action',searchParams,successFn,failFn);
					}
					
					this.processResultPanel = Ext.create('ProcessResultGrid',{
						'complaint':me.complaintData.complaint
						,'isVerifyTask':true
					});
					this.processRecordPanel = Ext.create('ProcessRecordGrid',{
						'complaint':me.complaintData.complaint
					});
					
//					this.delayApplyPanel=Ext.create('DelayApplyPanel',{
//						isEditable:false,'complaint':me.complaintData.complaint
//					});
			        Ext.applyIf(me,{
			        	buttons:[
							{text:i18n('i18n.complaintVerifyTask.backVisitRegister'),id:'verifyTaskRegisterId'
					    			,handler:function(){
	    							Ext.create('VerifyTaskRegisterWindow',{complaint:me.complaintData.complaint}).show();}
							}
							,{text:i18n('i18n.complaintReport.btn_close'),handler:function(){
									me.close();
							}}
						]
						,items:[
							me.complaintBasicFormPanel
							,me.surveySuggestGrid
							,me.baseInfoLevelPanel
							,me.processResultPanel
							,me.processRecordPanel
							,me.complaintFourPanel
//							,me.delayApplyPanel
						]
			        });
			        me.callParent(arguments);
			    }
			    /* 添加滚动条 修改 begin */
				,'closeAction':'destroy'
				,listeners:{
					destroy:function(){
						if(viewport && viewport!=null){
							document.body.scrollLeft=0;
							document.body.scrollTop=0;
							document.getElementsByTagName("html")[0].style.overflowY="hidden";
							document.getElementsByTagName("html")[0].style.overflowX="hidden";
							viewport.doLayout();
						}
					},
					show:function(){
						if(viewport && viewport!=null){
							document.getElementsByTagName("html")[0].style.overflowY="auto";
							document.getElementsByTagName("html")[0].style.overflowX="auto";
							viewport.doLayout();
						}
					}
				}
				/* 添加滚动条 修改 end */			    
			});
		    
			/** 
			 * 回访登记 声明
			 */
			Ext.define('VerifyTaskRegisterWindow', {
			    extend:'PopWindow',
			    modal : true
			    ,id:'verifyTaskRegisterWindowId'
				,width:270,height:240
			   ,layout: {align:'stretch',type:'vbox'}
				,title:i18n('i18n.complaintVerifyTask.backVisitRegister')
				,complaint:null
			    ,items:[
			    		{fieldLabel : i18n('i18n.complaint.comp_dealstatus'),labelWidth:80,
//			    						region:'north',
			    						id:'resolveCodeId',
										xtype: 'combobox',
										mode:           'local',
							            triggerAction:  'all',
							            forceSelection:true,
							            editable:false,//不能编辑
							            allowBlank:false,
										displayField:'codeDesc',
									    valueField:'code',
										//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
										store:getDataDictionaryByName(DataDictionary,'RESOLVE_CASE')
			    		},
			    		{fieldLabel : i18n('i18n.complaint.process_record.custsatisfy'),labelWidth:80,
//			    						region:'center',
			    						id:'satisfactionDegreeId',
										xtype: 'combobox',
										mode:           'local',
							            triggerAction:  'all',
							            forceSelection:true,
							            editable:false,//不能编辑
							            allowBlank:false,
										displayField:'codeDesc',
									    valueField:'code',
										//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
										store:getDataDictionaryByName(DataDictionary,'SATISFACTION_DEGREE')
			    		},{xtype:'textareafield',labelWidth:80,fieldLabel:i18n('i18n.complaintVerifyTask.backVisitResult'),id:'txtReturnVisitResult',
			    			allowBlank : false,
							maxLength:400,
							blankText : i18n('i18n.complaintVerifyTask.empty_backVisitResult')
			    		}	
			    		,{
			    			xtype:'checkboxfield',id:'feedback',margin:'0 0 10 45',boxLabel:i18n('i18n.complaint.comp_feedback'),width:80,checked: true//,region:'south'
			    		}
			    ]
				,initComponent:function(){
					var me = this;
					Ext.applyIf(this, {
					    buttons:[
							{text:i18n('i18n.complaint.submit'),scope:me,handler:me.submitEvent}
							,{text:i18n('i18n.complaintVerifyTask.cancel'),handler:function(){
								Ext.getCmp('verifyTaskRegisterWindowId').close();
							}}
						]
					});
					this.callParent(arguments);
			    }
				,submitEvent:function(){
					processingMask.show();//显示进度条信息
					var me = this;
					var resolveCode = Ext.getCmp('resolveCodeId').getValue();
					var satisfactionDegree=Ext.getCmp('satisfactionDegreeId').getValue();
					var feedback=Ext.getCmp('feedback').getValue();
					var returnVisitResult=Ext.getCmp('txtReturnVisitResult').getValue();
					
					if(feedback){
						feedback='1';
					}else{
						feedback='0';	
					}
					if (DpUtil.isEmpty(resolveCode)){
						processingMask.hide();//隐藏进度条信息
						MessageUtil.showMessage(i18n('i18n.complaintTask.empty_resolveCode'));return;
					}else if (DpUtil.isEmpty(satisfactionDegree)){
						processingMask.hide();//隐藏进度条信息
						MessageUtil.showMessage(i18n('i18n.complaintVerifyTask.empty_satisfactionDegree'));return;
					}else if (DpUtil.isEmpty(returnVisitResult)){
						processingMask.hide();//隐藏进度条信息
						MessageUtil.showMessage(i18n('i18n.complaintVerifyTask.empty_backVisitResult'));return;
					}
					
					//执行成功
					var successFn = function(response){
						processingMask.hide();//隐藏进度条信息
						Ext.getCmp("verifyTaskRegisterWindowId").close();
						Ext.getCmp('complaintDetailsWindowId').close();
						// 调用客户工单详情 弹出框
						MessageUtil.showInfoMes(i18n('i18n.complaintVerifyTask.success_register'));
						Ext.getCmp('ComplaintVerifyTaskResultGridId').store.loadPage(1);
					}
					
					//执行失败
					var failFn = function(){
						processingMask.hide();//隐藏进度条信息
						MessageUtil.showErrorMes(i18n('i18n.complaintVerifyTask.failer_register'));
					}
					var record = new ComplaintVerifyTaskModel();
					record.data.fid=me.complaint.fid;
					record.data.feedback=feedback;
					record.data.dealstatus=resolveCode;
					record.data.bill= me.complaint.bill;
					
					var feedBackReasionModel=new FeedBackReasionModel();
					feedBackReasionModel.data.revisitresult=resolveCode;//returnVisitResult;
					feedBackReasionModel.data.custsatisfy=satisfactionDegree;
					feedBackReasionModel.data.wailbillcontent=returnVisitResult;//resolveCode;
					
					var params = {};
				    params.complaint = record.data;
					params.feedBackReasion=feedBackReasionModel.data;
				    DpUtil.requestJsonAjax('submitVerfiyComplaint.action',params,successFn,failFn);
				}
			});
			/**
			 * 延时申请
			 */
			Ext.define('DelayApplyPanel', {
			   extend:'BasicPanel'
				,padding:'0 0 0 42'
				,complaint:null
				,layout:{type:'table',columns:2}
				,defaults:{readOnly:true}
				,complaint:null
			    ,items:[
			    		{fieldLabel : i18n('i18n.complaintTask.title_delayApply'),labelWidth:60,
										xtype: 'combobox',
										mode:           'local',
							            triggerAction:  'all',
							            forceSelection:true,
							            id:'cboDelayApply',
							            editable:false,//不能编辑
										displayField:'codeDesc',
									    valueField:'code',
										//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
										store:getDataDictionaryByName(DataDictionary,'DELAY_APPLY')
			    		}
			    ]
				,initComponent:function(){
					var me = this;
					this.callParent(arguments);
					Ext.getCmp('cboDelayApply').setValue(me.complaint.approdelay);
			    }
			});
});


