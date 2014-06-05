var complaintTaskDataControl = (CONFIG.get("TEST"))
		? new ComplaintTaskDataTest()
		: new ComplaintTaskData();
var viewport = null;/* 视图容器 */

Ext.define('complaintTaskPanel', {
			extend : 'BasicPanel',
			parent : null,
			complaintTaskResultGrid : null, // 查询结果
			complaintTaskData : null,// 数据Data
			layout : {
				type : 'vbox',
				align : 'stretch'
			},
			items : null,
			initComponent : function() {
				var me = this;
				var record = new ComplaintTaskModel();
				me.complaintTaskResultGrid = Ext.create(
						'ComplaintTaskResultGrid', {
							'parent' : me,
							'complaintTaskData' : me.complaintTaskData
						});
				
				me.complaintTaskResultGrid.on('cellclick', function(thiz, row, col, e) {
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
								show_comp_detailsWin(e.get('complaintId'));/* 调用工单详情-弹出框 */
							}
				});								
						
				me.items = [{
							xtype : 'basicpanel',
							flex : 1,
							items : [me.complaintTaskResultGrid]
						}
						];
				this.callParent();
			}
		});

/**
 * 查询条件
 */
Ext.define('ComplaintTaskConditionForm', {
			name : 'complaintTaskConditionFormId',
			id : 'complaintTaskConditionFormId',
			extend : 'TitleFormPanel',
			items : null,
			receiverMember:null,
			shipperMember:null,
			initComponent : function() {
				this.items = this.getItems();
				this.callParent();
			},
			getItems : function() {}
		});
		
Ext.onReady(function() {
		//页面需要加载的数据字典数组
			var keys=[
				//解决情况
			    'RESOLVE_CASE',
			    //客户满意度
			    'SATISFACTION_DEGREE',	
			    //延迟时间
			    'DELAY_APPLY',
			    //上报类型
			    'REPORT_TYPE',
			    //工单模块处理状态
			    'COMPLAINT_PROCESS_STATUS',
			    'COMPLAINT_RECORD_TYPE'//工单模块反馈类型
			    ,'EXPECTED_TIME_LIMIT'//期望时限
			    //工单来源
			    ,'WORK_SINGLE_SOURCE'
			    //优先级别
			    ,'PRIORITY_RATING'
			    //投诉级别
			    ,'COMPLAINT_LEVEL'
			    ,'COMPLAINT_LINK'	/*业务环节*/
		         // 客户类型
		      	,'CUSTOMER_TYPE',
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
			 * 工单上报查询结果
			 */
			Ext.define('ComplaintTaskResultGrid', {
				extend : 'SearchGridPanel',
				parent : null,
				id:'ComplaintTaskResultGridId',
				complaintTaskData : null,// 数据Data
				selModel : chooseSelModel,
				initComponent : function() {
					
					var me = this;
					me.store = me.complaintTaskData
							.initComplaintTaskSearchStore(me.beforeLoadScatterFn);
					me.dockedItems = me.getMyDockedItems();
					me.columns = me.getColumns();
					this.callParent();
				},
				getColumns : function() {
					var me = this;
					return [
							{xtype:'rownumberer',header:i18n('i18n.complaint.serial_number'),width:35},
							{dataIndex:'businessModel',header:i18n('i18n.complaint.businessType'),
								renderer:function(value){
									return rendererDictionary(value,DataDictionary.COMPLAINT_BUSINESS_MODEL);
								}
							},
							{
								header : i18n('i18n.complaint.comp_bill')
							   ,dataIndex : 'bill'
							   ,width:60
							}, {
								header : i18n('i18n.complaint.comp_dealbill')
							   ,dataIndex : 'dealbill'
							   ,width:60
							}, {
								 header : i18n('i18n.complaint.comp_recomcode')
								,dataIndex : 'recomcode'
								,width:70
								,renderer:function(value){
				  					return '<a href="javascript:void(0);">'+value+'</a>';
				  				}
							}, {
								 header : i18n('i18n.complaint.comp_reportType')
								,dataIndex : 'reporttype'
								,width:60	
								,renderer:function(value){
				  					return rendererDictionary(value,DataDictionary.REPORT_TYPE);
				  				}
							}, {
								header : i18n('i18n.complaint.comp_compman')
								,dataIndex : 'compman'
								,width:60
								,renderer:function(value){
				  					return '<a href="javascript:void(0);">'+value+'</a>';
				  				}								
							}, {
								 header : i18n('i18n.complaint.comp_prostatus')
								,dataIndex : 'prostatus'
								,width:60	
								,renderer:function(value){
				  					return rendererDictionary(value,DataDictionary.COMPLAINT_PROCESS_STATUS);
				  				}
							}, {
								 header : i18n('i18n.complaint.comp_complevel')
								,dataIndex : 'complevel'
								,width:60
								,renderer:function(value){
				  					return rendererDictionary(value,DataDictionary.COMPLAINT_LEVEL);
				  				}
							}, {
								 header : i18n('i18n.complaint.process_result.feedtimeLimit')
								,dataIndex : 'feedtimelimit'
								,width:60
							}, {
								header : i18n('i18n.complaint.process_result.processtimeLimit')
								,dataIndex : 'processtimelimit'
								,width:60
							}, {
								header : i18n('i18n.complaint.comp_feedback_department')
								,dataIndex : 'taskpartment'
								,width:85
							}, {
								header : i18n('i18n.complaint.comp_reporttime')
								,dataIndex : 'reporttime'
								,width:115	
								,renderer:function(value) {
									if(value != null){
										return Ext.Date.format(new Date(value), 'Y-m-d H:i');
									}else{
										return null;
									}
								}
							}];
				},
				// 分页条
				getMyDockedItems : function() {
					var me = this;
					return [{
								xtype : 'pagingtoolbar',
//								plugins : [Ext.create('Ext.ux.PageComboResizer')],
								store : me.store,
								dock : 'bottom',
								displayInfo : true,
								items:[{
			  						text: '每页',
			  						xtype: 'tbtext'
			  					},Ext.create('Ext.form.ComboBox', {
			  		               width:          window.screen.availWidth*0.0391,
			  		               triggerAction:  'all',
			  		               forceSelection: true,
			  		               value:'100',
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
			  					            fn: function(_field,_value){
			  					            	var pageSize = me.store.pageSize;
			  					            	var newPageSize = parseInt(_field.value);
			  					            	if(pageSize!=newPageSize){
			  					            		me.store.pageSize = newPageSize;
			  					            		this.up('pagingtoolbar').moveFirst();
			  					            	}
			  					            }
			  					        }
			  		               }
			  		           }),{
			  						text: '条',
			  						xtype: 'tbtext'
			  		           }]
							}];
				},
				// beforeLoad方法
				beforeLoadScatterFn : function(store, operation, eOpts) {
					var searchConditionForm = Ext.getCmp('complaintTaskConditionFormId');
					if (searchConditionForm != null) {
						searchConditionForm.getForm()
								.updateRecord(searchConditionForm.record);
						// 设置请求参数
						var searchCustCondition = searchConditionForm.record.data;
						var searchParams = {
						};
						Ext.apply(operation, {
									params : searchParams
								});
					}
				}
			});
			
			Ext.define('LeftModifyButtonPanel',
				{	
				extend:'NormalButtonPanel', 
				items:null,
				initComponent:function(){
					this.items = this.getItems();	
					this.callParent();	
				},
				getItems:function(){
					var me = this;	
					return [{
						xtype:'leftbuttonpanel',
						items:[{xtype:'button',text:i18n('i18n.complaintTask.process'),handler:function(){
							processingMask.show();//显示进度条信息
							
							var sm = Ext.getCmp('ComplaintTaskResultGridId').getSelectionModel();
							if (sm.getSelection().length > 0) {
								this.action = 'update';
								var record = sm.getSelection()[0];
								
								//执行成功
								var successFn = function(response){
									processingMask.hide();//隐藏进度条信息
									// 调用客户工单详情 弹出框
									
									show_comp_detailsWin(record.get('complaintId'),function(){
										new CompTaskButtonView().initButForTask({
											'complaint':response.complaint,
											'resultData':record.data
										});
									});/* 调用工单详情-弹出框 */
								}
								
								//执行失败
								var failFn = function(){
									processingMask.hide();//隐藏进度条信息
									MessageUtil.showErrorMes(i18n('i18n.alert_message_dataNotExist'));
								}
								var params={};
								params.fid=record.data.complaintId;
								DpUtil.requestJsonAjax('searchComplaintAndOther.action',params,successFn,failFn);
								
							} else {
								processingMask.hide();//隐藏进度条信息
								MessageUtil.showMessage(i18n('i18n.complaintReportReturnProcess.noRecord'));
							}
						}}]
					}]
			}});
			
			viewport = Ext.create('Ext.container.Viewport', {
						layout : 'border','autoScroll':true,
						items : [
						Ext.create('LeftModifyButtonPanel',{region:'north'})
						,Ext.create('complaintTaskPanel', {region:'center',
										'complaintTaskData' : complaintTaskDataControl
									})
						]
		    });
		    viewport.setAutoScroll(false);
			viewport.doLayout();			
			/** 
			 * 退回处理人弹出框 声明
			 */
			Ext.define('ReturnToProcessWindow', {
			    extend:'PopWindow',
			    modal : true,
			    depId:null
			    ,id:'returnToProcessWindowId'
				,width:300,height:200
			    ,layout: 'fit',modal:true
				,title:i18n('i18n.complaintTask.btnReturnToProcess')
				,complaint:null  //投诉基本信息
			    ,items:[{xtype:'textareafield',labelWidth:60,fieldLabel:i18n('i18n.comp.comp_process.return_cause'),id:'returnToProcessReason',
			    	allowBlank : false,
					maxLength:200,
					blankText : i18n('i18n.complaintTask.empty_returnCause')
			    }]
				,initComponent:function(){
					var me = this;
					Ext.applyIf(this, {
					    buttons:[
							{text:i18n('i18n.complaint.submit'),scope:me,handler:me.submitEvent}
							,{text:i18n('i18n.complaintReport.btn_close'),handler:function(){
								Ext.getCmp('returnToProcessWindowId').close();
							}}
						]
					});
					this.callParent(arguments);
			    }
				,submitEvent:function(){
					processingMask.show();//显示进度条信息
					var me = this;
					var feedbackreason = Ext.getCmp('returnToProcessReason').getValue();
					if(DpUtil.isEmpty(feedbackreason) || Ext.String.trim(feedbackreason)==""){
						processingMask.hide();//隐藏进度条信息
						MessageUtil.showMessage(i18n('i18n.complaintTask.empty_returnCause'));return;
					}
					
					//执行成功
					var successFn = function(response){
						processingMask.hide();//隐藏进度条信息
						Ext.getCmp("returnToProcessWindowId").close();
						Ext.getCmp('complaintDetailsWindowId').close();
						// 调用客户工单详情 弹出框
						MessageUtil.showInfoMes(i18n('i18n.complaintTask.success_returnProcess'));
						Ext.getCmp('ComplaintTaskResultGridId').store.load();
					}
					
					//执行失败
					var failFn = function(response){
						processingMask.hide();//隐藏进度条信息
						MessageUtil.showErrorMes(response.message);
					}
					var record = new SearchComplaintTaskConditionModel();
					record.data.fid=me.complaint.fid;
					record.data.feedbackContent=feedbackreason;
					//部门id
					record.data.deptId=me.depId;
					var params = {};
				    params.complaintTaskCondtion = record.data;
					DpUtil.requestJsonAjax('returnToProcess.action',params,successFn,failFn);
				}
			});

			/** 
			 * 反馈登记弹出框 声明
			 */
			Ext.define('feedbackRegisterWindow', {
			    extend:'PopWindow',
			    modal : true
			    ,id:'feedbackRegisterWindowId'
				,width:300,height:220
			    ,layout: 'border',modal:true
				,title:i18n('i18n.complaintTask.btnFeedbackRegister')
				,complaint:null
				,depId:null
			    ,items:[
			    		{xtype:'textareafield',labelWidth:60,fieldLabel:i18n('i18n.complaint.comp_feedbackResult'),id:'resolveResultId',region:'center',
			    			allowBlank : false,
							maxLength:400,
							blankText : i18n('i18n.complaintTask.empty_feedbackResult')
			    		},
			    		{fieldLabel : i18n('i18n.complaint.comp_dealstatus'),labelWidth:60,
			    						region:'south',
			    						id:'resolveCodeId',
										xtype: 'combobox',height:20,
										mode:           'local',
							            triggerAction:  'all',
							            forceSelection:true,
							            editable:false,//不能编辑
							            allowBlank:false,
										displayField:'codeDesc',
									    valueField:'code',
										//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
										store:getDataDictionaryByName(DataDictionary,'RESOLVE_CASE')
			    		}
			    ]
				,initComponent:function(){
					var me = this;
					Ext.applyIf(this, {
					    buttons:[
							{text:i18n('i18n.complaint.submit'),scope:me,handler:me.submitEvent}
							,{text:i18n('i18n.complaintReport.btn_close'),handler:function(){
								Ext.getCmp('feedbackRegisterWindowId').close();
							}}
						]
					});
					this.callParent(arguments);
			    }
				,submitEvent:function(obj){
					processingMask.show();//显示进度条信息
					var me = this;
					var resolveResult = Ext.getCmp('resolveResultId').getValue();
					var resolveCode=Ext.getCmp('resolveCodeId').getValue();
					if(DpUtil.isEmpty(resolveResult) || Ext.String.trim(resolveResult)==""){
						processingMask.hide();//隐藏进度条信息
						MessageUtil.showMessage(i18n('i18n.complaintTask.empty_feedbackResult'));
						return;
					}else if(resolveResult.length>400){
						processingMask.hide();//隐藏进度条信息
						MessageUtil.showMessage(i18n('i18n.complaint.comp_feedbackResult_len_max'));
						return;
					}
					
					if (DpUtil.isEmpty(resolveCode)){
						processingMask.hide();//隐藏进度条信息
						MessageUtil.showMessage(i18n('i18n.complaintTask.empty_resolveCode'));
						return;
					}
					
					
					//执行成功
					var successFn = function(response){
						processingMask.hide();//隐藏进度条信息
						Ext.getCmp("feedbackRegisterWindowId").close();
						Ext.getCmp('complaintDetailsWindowId').close();
						// 调用客户工单详情 弹出框
						MessageUtil.showInfoMes(i18n('i18n.complaintTask.success_register'));
						Ext.getCmp('ComplaintTaskResultGridId').store.load();
					}
					
					//执行失败
					var failFn = function(response){
						processingMask.hide();//隐藏进度条信息
						MessageUtil.showErrorMes(response.message);
					}
					var record = new SearchComplaintTaskConditionModel();
					record.data.fid=me.complaint.fid;
					record.data.feedbackContent=resolveResult;
					//部门id
					record.data.deptId=me.depId;
					record.data.resolveCode=resolveCode;
					var params = {};
				    params.complaintTaskCondtion = record.data;
					DpUtil.requestJsonAjax('passFeedback.action',params,successFn,failFn);
				}
			});
			
			/** 
			 * 延时申请弹出框 声明
			 */
			Ext.define('delayApplicationWindow', {
			    extend:'PopWindow',
			    modal : true
			    ,id:'delayApplicationWindowId'
				,width:300,height:180
			    ,layout: 'border',modal:true
				,title:i18n('i18n.complaintTask.title_delayApply')
				,complaint:null
				,depId:null
			    ,items:[
			    		{xtype:'textareafield',labelWidth:60,fieldLabel:i18n('i18n.complaintTask.delayReason'),id:'delayReasonId',region:'center',
			    			allowBlank : false,
							maxLength:400,
							blankText : i18n('i18n.complaintTask.empty_delayReason')
			    		},
			    		{fieldLabel : i18n('i18n.complaintTask.delayDay'),labelWidth:60,
			    						region:'south',
			    						id:'delayDayId',
										xtype: 'combobox',height:20,
										mode:           'local',
							            triggerAction:  'all',
							            forceSelection:true,
							            editable:false,//不能编辑
							            allowBlank:false,
										displayField:'codeDesc',
									    valueField:'code',
										//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
										store:getDataDictionaryByName(DataDictionary,'DELAY_APPLY')
			    		}
			    ]
				,initComponent:function(){
					var me = this;
					Ext.applyIf(this, {
					    buttons:[
							{text:i18n('i18n.complaint.submit'),scope:me,handler:me.submitEvent}
							,{text:i18n('i18n.complaintReport.btn_close'),handler:function(){
								Ext.getCmp('delayApplicationWindowId').close();
							}}
						]
					});
					this.callParent(arguments);
			    }
				,submitEvent:function(){
					processingMask.show();//显示进度条信息
					var me = this;
					var delayReason = Ext.getCmp('delayReasonId').getValue();
					var delayDay=Ext.getCmp('delayDayId').getValue();
					if(DpUtil.isEmpty(delayReason) || Ext.String.trim(delayReason)==""){
						processingMask.hide();//隐藏进度条信息
						MessageUtil.showMessage(i18n('i18n.complaintTask.empty_delayReason'));return;
					}else if (DpUtil.isEmpty(delayDay)){
						processingMask.hide();//隐藏进度条信息
						MessageUtil.showMessage(i18n('i18n.complaintTask.empty_delayDay'));return;
					}
					
					//执行成功
					var successFn = function(response){
						processingMask.hide();//隐藏进度条信息
						Ext.getCmp("delayApplicationWindowId").close();
						Ext.getCmp('complaintDetailsWindowId').close();
						// 调用客户工单详情 弹出框
						MessageUtil.showInfoMes(i18n('i18n.complaintTask.success_delayApply'));
						Ext.getCmp('ComplaintTaskResultGridId').store.load();
					}
					
					//执行失败
					var failFn = function(response){
						processingMask.hide();//隐藏进度条信息
						MessageUtil.showErrorMes(response.message);
					}
					var record = new SearchComplaintTaskConditionModel();
					record.data.fid=me.complaint.fid;
					record.data.feedbackContent=delayReason;
					//部门id
					record.data.deptId=me.depId;
					record.data.applyCode=delayDay;
					var params = {};
				    params.complaintTaskCondtion = record.data;
					DpUtil.requestJsonAjax('delayApplication.action',params,successFn,failFn);
				}
			});
});


