
var isPageLoad = true;//是否点击了查询按钮，点击之后为false
//var isSearch = false;
/**
 * 工单责任审批页面
 */
var viewport = null;
Ext.onReady(function(){
	//加入数据字典
	var keys = ['COMPLAINT_FIELD',
	            'DUTY_SURVEY_RESULT',
	            'APPROVAL_ACCORDING',
	            'REPORT_TYPE',
	            'DUTY_RESULT_STATUS',
	            'DUTY_TOTAL_SURVEY_RESULT',
	            'COMPLAINT_BUSINESS_MODEL'
	            ];
	initDataDictionary(keys);
	initDeptAndUserInfo();
	//初始化提示信息条
	Ext.QuickTips.init();
	
	
	//判断登录的用户是否为统计员
	if(Ext.Array.contains(User.roleids,i18n('i18n.Duty.DutyApproval.StatisticRoleId'))){
		dutyApprovalRole = i18n('i18n.Duty.DutyApproval.StatisticRoleId');
	}
	//判断登录的用户是否为质检员
	else if(Ext.Array.contains(User.roleids,i18n('i18n.Duty.DutyApproval.CallerRoleId'))){
		dutyApprovalRole = i18n('i18n.Duty.DutyApproval.CallerRoleId');
	}
	//判断登录的用户是否为事业部质检员
	else if(Ext.Array.contains(User.roleids,i18n('i18n.Duty.DutyApproval.BusCallerRoleId'))){
		dutyApprovalRole = i18n('i18n.Duty.DutyApproval.BusCallerRoleId');
	}
	
	/**
	 *工单责任审批-统计员和质检员查询条件输入面板form
	 */
	Ext.define('DutyApprovalQueryPanel',{
		extend:'SearchFormPanel', 
		items:null,
		border:0,
		layout:{
			type:'table',
			columns:4
		},
		defaults:{
			labelWidth:60,
			labelAlign:'right',
			width:240,
			margin:'0 5 5 0'
		},
		defaultType:'textfield',
		initComponent:function(){			
			this.items = this.getItems();
			this.callParent();
			var store = Ext.getCmp('approvalDutyStatus').store;
			store.remove(store.findRecord('code','WAITING_FEEDBACK'));
			store.remove(store.findRecord('code','FEEDBACK_EXCEED'));
			store.remove(store.findRecord('code','DUTY_ACCEPT'));
			store.remove(store.findRecord('code','NO_DUTY'));
			store.remove(store.findRecord('code','STATIS_TRUNING_BACK'));//统计员退回无效
			store.remove(store.findRecord('code','APPROVALING'));//反馈审批中
		},
		getItems:function(){
			var me = this;
			var feedBackEndTime = new Date();
			var feedBackStartTime = Ext.Date.add(new Date(),Ext.Date.DAY,-30);
			return [
			    {//反馈人
			    	fieldLabel : i18n('i18n.Duty.DutyApproval.FeedbackManName'),
			    	width:240,
			    	id:'feedBackPerson',
					name : 'feedBackPerson'
			    },{//编号类型选择
			    	xtype:'combobox',
			    	labelSeparator:'',
			    	margin:'0 0 5 10',
			    	fieldLabel:'',
			    	labelWidth:0,
			    	width:90,
			    	store:Ext.create('Ext.data.Store', {
			    	    fields: ['code', 'codeDesc'],
			    	    data : [
			    	        {"code":"001", "codeDesc":i18n('i18n.Duty.resultDutyPanel.dealNumber')},
			    	        {"code":"002", "codeDesc":i18n('i18n.Duty.resultDutyPanel.voucherNumber')}
			    	    ]
			    	}),
			    	queryMode:'local',
			    	editable:false,
			    	displayField:'codeDesc',
					valueField:'code',
					value:'002',
					name:'codeType'
				},{//编号内容输入
					labelSeparator:'',
					fieldLabel:'',
					labelWidth:2,
					maxLength:20,
			    	width:140,
					name:'code'
				},{//责任状态
					xtype:'combobox',
					fieldLabel:i18n('i18n.Duty.DutyApproval.dutyStatus'),
					store:getDataDictionaryByName(DataDictionary,'DUTY_RESULT_STATUS'),
			    	queryMode:'local',
			    	editable:true,
			    	forceSelection:true,
			    	displayField:'codeDesc',
					valueField:'code',
					name:'dutyStatus',
					id:'approvalDutyStatus',
					listeners:{
						//comboBox的change事件重写，使其达到可以删除，且只能选择下拉框里面的内容
						change:function(th){
							DutyUtil.comboSelsct(th);
						}
					}		
				},{//反馈部门
					fieldLabel : i18n('i18n.Duty.DutyApproval.FeedbackDeptName'),
					maxLength:50,
					name : 'feedBackDept'
				},{//反馈时间
			    	xtype:'datetimefield',
			    	fieldLabel:i18n('i18n.Duty.DutyApproval.FeedbackTimeStart'),
			    	editable:false,
			    	colspan:2,
			    	value:new Date(feedBackStartTime.getFullYear(),feedBackStartTime.getMonth(),feedBackStartTime.getDate(),0,0,0),
			    	width:240,
			    	format:'Y-m-d H:i:s',
					name:'feedBackStartTime'
				},{//反馈时间到
					xtype:'datetimefield',
			    	fieldLabel:i18n('i18n.Duty.DutyApproval.FeedbackTimeEnd'),
			    	editable:false,
			    	value:new Date(feedBackEndTime.getFullYear(),feedBackEndTime.getMonth(),feedBackEndTime.getDate(),23,59,59),
			    	format:'Y-m-d H:i:s',
					name:'feedBackEndTime'
				},{//调查结果
					xtype:'combobox',
					fieldLabel:i18n('i18n.Duty.resultDutyPanel.surveyResult'),
					width:240,
					store:getDataDictionaryByName(DataDictionary,'DUTY_SURVEY_RESULT'),
			    	queryMode:'local',
			    	editable:true,
			    	forceSelection:true,
			    	displayField:'codeDesc',
					valueField:'code',
					name:'surveyResult',
					listeners:{
						//comboBox的change事件重写，使其达到可以删除，且只能选择下拉框里面的内容
						change:function(th){
							DutyUtil.comboSelsct(th);
						}
					}
				},{//业务模式
					fieldLabel :i18n('i18n.duty.businessModel'),
					name : 'businessModel',
					xtype : 'combobox',
					store :getDataDictionaryByName(DataDictionary,'COMPLAINT_BUSINESS_MODEL'),
					queryMode : 'local',
					forceSelection : true,
					editable:false,
					displayField : 'codeDesc',
					valueField : 'code',
					width:240,
					colspan:3,
					listeners:{
						beforerender:function(f){
							f.getStore().insert(0,{'code':'','codeDesc':i18n('i18n.duty.all')});
							f.setValue('');
						}
					}
				}
		    ];
		}
	});	
	
	//责任审批操作按钮面板
	Ext.define('DutyApprovalButtonPanel',{
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
				items:[
				  {//责任审批
					  xtype:'button',
					  text:i18n('i18n.Duty.DutyApproval.DutyApproval'),
					  handler:function(){
					  	var dutyApprovalResultGrid = Ext.getCmp('dutyApprovalResultGridId');
						var records=dutyApprovalResultGrid.getSelectionModel().getSelection();
						//判断是否选中行
						if (records.length == 0) {
							MessageUtil.showErrorMes(i18n('i18n.Duty.searchDutyErrorMesg.singleRecord'));
							return false;
						}
						if (records.length != 1) {
							MessageUtil.showErrorMes(i18n('i18n.Duty.DutyApproval.OnlySelectOne'));
							return false;
						}
						if(Ext.isEmpty(Ext.String.trim(records[0].get('dutyId')))){
							MessageUtil.showErrorMes(i18n('i18n.Duty.DutyApproval.NullDutyId'));
							return false;
						}
						Ext.getCmp('dutyApprovalOperationWindowId').dutyId = records[0].get('dutyId');
						
						//根据责任ID查询工单责任基本信息用于显示
						var successFn = function(json){
							//工单责任详情
							var dutyDetailInfoPanel=Ext.getCmp("callerDutyComplaintDetailBasicInfoPanelId").getForm();
							dutyDetailInfoPanel.loadRecord(new DutySearchDetailModel(json.dutyDetail));
							// 处理经过
							Ext.getCmp("callerDutyFeedbackDealProcedureGridId").store.loadData(json.dutyDealProcess);
							// 责任划分结果
							Ext.getCmp("callerDutyAllocationResultDeptGridId").store.loadData(json.dutyResult);
							
							Ext.getCmp('dutyApprovalOperationWindowId').down('textarea[id="informUser"]').setValue(json.userNames);
							//通知对象
							//Ext.getCmp("informUser").setValue(json.userNames);
						};
						var failureFn = function(json){
							MessageUtil.showErrorMes(json.message);
						};
						var param={
								'dutyId':records[0].get('dutyId')
						};
						DutyApprovalStore.prototype.searchDutyDetailByDutyId(param, successFn, failureFn);
						
						//显示责任审批操作窗口--质检员
						Ext.getCmp("dutyFeedbackRecordOperationGridId").store.load({params:{dutyId:records[0].get('dutyId')}});
//							Ext.getCmp('modifyDutyAllocationBtn').disable();
						dutyApprovalOperationWindow.show();
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
				    	isPageLoad = false;
				    	var dutyApprovalQueryForm = Ext.getCmp('dutyApprovalQueryPanelId').getForm();
				    	//编号内容
				    	var code = dutyApprovalQueryForm.findField('code').getValue();
				    	//开始反馈时间
				    	var feedBackStartTime = dutyApprovalQueryForm.findField('feedBackStartTime').getValue();
				    	//反馈时间到
				    	var feedBackEndTime = dutyApprovalQueryForm.findField('feedBackEndTime').getValue();
				    	var compareTwoDate = DutyUtil.compareTwoDate(feedBackStartTime,feedBackEndTime);
				    	if(Ext.isEmpty(code)){
				    		if(Ext.isEmpty(feedBackStartTime) && Ext.isEmpty(feedBackEndTime)){
				    			MessageUtil.showErrorMes(i18n('i18n.Duty.DutyApproval.SelectFeedbackTime'));
				    			return false;
				    		}
				    		if(Ext.isEmpty(feedBackStartTime) || Ext.isEmpty(feedBackEndTime)){
				    			MessageUtil.showErrorMes(i18n('i18n.Duty.DutyApproval.SelectRightFeedbackTime'));
				    			return false;
				    		}
				    		if(compareTwoDate > 31){
					    		MessageUtil.showErrorMes(i18n('i18n.Duty.DutyApproval.CanNotExceedDays'));
				    			return false;
					    	}
				    		
				    		if(0 > compareTwoDate){
					    		MessageUtil.showErrorMes(i18n('i18n.Message.showMessage.reportTimeBeginParaBiggerThanreportTimeEndPara'));
				    			return false;
					    	}
				    	}
				    	Ext.getCmp('dutyApprovalResultGridId').store.loadPage(1);
				  }
				 }]
			}];
		}
	});
	
	/**
	 * 工单责任审批查询结果表格
	 */
	Ext.define('DutyApprovalQueryResultGrid',{
		extend:'SearchGridPanel',
		selModel:Ext.create('Ext.selection.CheckboxModel',{mode:'single'}),
		//行展开控件，表格中嵌套表格
		plugins: [{
		    ptype: 'rowexpander',
			//定义行展开模式（单行与多行），默认是多行展开(值true)
			rowsExpander: false,
			//行体内容
			rowBodyElement : 'ExpandDutyApprovalResultGrid'
		}],
		initComponent:function(){ 
			var me = this;
			var store = Ext.create('DutyQueryResultStore');
			store.on('beforeload',function(store,operation,e){					
				//查询按钮功能实现区
		    	var dutyApprovalQueryForm = Ext.getCmp('dutyApprovalQueryPanelId').getForm();
		    	var params = {
						'queryDutyCondition.feedBackPerson':dutyApprovalQueryForm.findField('feedBackPerson').getValue(),
						'queryDutyCondition.typeName':dutyApprovalQueryForm.findField('codeType').getValue(),
						'queryDutyCondition.typeId':dutyApprovalQueryForm.findField('code').getValue(),
						'queryDutyCondition.dutyStates':dutyApprovalQueryForm.findField('dutyStatus').getValue(),
						'queryDutyCondition.feedbackDept':dutyApprovalQueryForm.findField('feedBackDept').getValue(),
						'queryDutyCondition.feedBackTimeBegin':dutyApprovalQueryForm.findField('feedBackStartTime').getValue(),
						'queryDutyCondition.feedBackTimeEnd':dutyApprovalQueryForm.findField('feedBackEndTime').getValue(),
						'queryDutyCondition.surveyResult':dutyApprovalQueryForm.findField('surveyResult').getValue(),
						'queryDutyCondition.businessModel':dutyApprovalQueryForm.findField("businessModel").getValue()
				};
		    	if(isPageLoad){
		    		if(Ext.isEmpty(dutyApprovalRole)){
						MessageUtil.showErrorMes(i18n('i18n.Duty.DutyApproval.NoOperationRole'));
		    			return false;
					}
					if(//400质检员  || 事业部质检员
	    				dutyApprovalRole === i18n('i18n.Duty.DutyApproval.CallerRoleId') || 
	    				dutyApprovalRole === i18n('i18n.Duty.DutyApproval.BusCallerRoleId')){
	    				params = {'queryDutyCondition.dutyStates':'WAITING_APPROVAL'};
	    			}
		    	}
				
				Ext.apply(operation,{
					params : params
				});
			});
			me.store = store;
			me.columns = [
			    {//序号
			    	xtype:'rownumberer',
					header:i18n('i18n.duty.serial_number'),
					draggable:false,
					width:40
			    },{ //业务模式
					header :i18n('i18n.duty.businessModel'),
					dataIndex:'businessModel',
					width:80,
					renderer:function(v){
						return rendererDictionary(v,DataDictionary.COMPLAINT_BUSINESS_MODEL);
					}
			    },{//凭证号
					header:i18n('i18n.Duty.resultDutyPanel.voucherNumber'),
					width:110,
					dataIndex:'voucherNumber'
			    },{ //处理编号
					header :i18n('i18n.Duty.resultDutyPanel.dealNumber'),
					dataIndex:'dealNumber',
					width:120
			    },{//上报类型
					header :i18n('i18n.Duty.resultDutyPanel.reportType'),
					dataIndex:'reportType',
					renderer:function(value){
						return DutyUtil.rendererDictionary(value,DataDictionary.REPORT_TYPE);
					},
					width:100
			    },{//来电人
					header :i18n('i18n.Duty.resultDutyPanel.caller'),
					dataIndex:'caller',
					width:80
			    },{//调查结果
					header :i18n('i18n.Duty.resultDutyPanel.surveyResult'),
					dataIndex:'surveyResult',
					renderer:function(value) {
						return DutyUtil.rendererDictionary(value,DataDictionary.DUTY_TOTAL_SURVEY_RESULT);
					},
					width:65
			    },{//责任划分人
					header :i18n('i18n.Duty.DutyApproval.DutyAllocationMan'),
					dataIndex:'appDutyUser',
					width:85
			   },{//责任划分时间
					header :i18n('i18n.Duty.DutyApproval.DutyAllocationTime'),
					dataIndex:'appDutyTime',
					renderer : DutyUtil.renderDateTime,
					width:150
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
		//双击表格记录，显示嵌套表格信息
		me.on('itemdblclick',function(th,record,item,index,e,eOpts){
			if(Ext.isEmpty(Ext.String.trim(record.get('dutyId')))){
				MessageUtil.showErrorMes(i18n('i18n.Duty.DutyApproval.NullDutyIdToOperate'));
				return false;
			}
			dutyId = record.get("dutyId");
		});
		
		me.store.loadPage(1);
	    this.callParent();
	   }
	});
	
	/*
	 * 新建一个viewport，用于显示工单责任审批界面
	 * 肖红叶
	 */
	viewport=Ext.create('Ext.Viewport',{
		layout : 'border',
		items:[
	        {//责任审批查询条件输入面板
	        	xtype:'container',
				region:'north',
				layout:'fit',
				items:[Ext.create('DutyApprovalQueryPanel',{id:'dutyApprovalQueryPanelId'})]
			},{
				xtype:'container',
				region:'center',
				layout:'border',
				items:[
					{//按钮面板
						xtype:'container',
						region:'north',
					    layout:'fit',
					    items:[Ext.create('DutyApprovalButtonPanel',{id:'dutyApprovalButtonPanelId'})]
					},{//责任审批查询结果表格
						xtype:'container',
						region:'center',
			    	    layout:'fit',
			    	    items:[ Ext.create('DutyApprovalQueryResultGrid',{id:'dutyApprovalResultGridId'})]
			        }	
			   ]
			}
	    ]
	});

/******************呼叫中心质检员审批操作页面********begin***********************************/	
	/**
	 * 工单责任审批操作页面整体布局panel
	 */
	Ext.define('DutyApprovalOperationPanel', {
		extend : 'BasicPanel',
		layout : 'border',
		items :[
		  {//工单详情基本工单信息
			xtype:'container',
			region:'north',
			layout:'fit',
			items:[Ext.create('DutyComplaintDetailBasicInfoPanel',{id:'callerDutyComplaintDetailBasicInfoPanelId'})]
		  },{
			xtype:'container',
			layout:'border',
			region:'center',
			items:[
			  {//处理经过列表
				xtype:'container',//container可以去除panel的边框
				layout:'fit',
				region:'north',
				height:120,
				items:[Ext.create('DutyFeedbackDealProcedureGrid',{id:'callerDutyFeedbackDealProcedureGridId'})]	  
			  },{//责任划分结果列表
				xtype : 'container',
				layout:'fit',
				region:'center',
				items:[Ext.create('DutyAllocationResultDeptGrid',{title:i18n('i18n.Duty.DutyApproval.DutyAllocationResult'),id:'callerDutyAllocationResultDeptGridId'})]
			  },{//通知对象
				xtype:'container',//container可以去除panel的边框
				layout:'fit',
				region:'south',
				items:[Ext.create('DutyFeedbackInfoToPanel')]	
			  }      
			]
		  },{//责任反馈记录列表
	  	    xtype : 'container',
			layout:'fit',
			region:'south',
			height:150,
			items:[Ext.create('DutyFeedbackRecordOperationGrid',{id:'dutyFeedbackRecordOperationGridId'})]
	       }
		]
	});	


	/**
	 * 单击责任审批按钮，弹出的责任审批窗口
	 * xiaohongye
	*/
	Ext.define('DutyApprovalOperationWindow',{
		extend:'PopWindow',
		title:i18n('i18n.Duty.DutyApproval.ComplaintDutyApproval'),
		alias : 'widget.basicwindow',
		width:820,
		height:750,
		modal:true,
		layout:'fit',
		closeAction:'hide',
		items:[Ext.create('DutyApprovalOperationPanel',{'id':'dutyApprovalOperationPanelId'})],
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
		    items: [{//查看工单详情
				xtype:'button',
				text:i18n('i18n.Duty.DutyApproval.SearchComplaintDetail'),
				handler:function(btn){
					var selectRecords = Ext.getCmp('dutyApprovalResultGridId').getSelectionModel().getSelection();
					show_comp_detailsWin(selectRecords[0].get('complaintId'));
			    }
			},
			'->',
			{//关闭
				xtype:'button',
				text:i18n('i18n.Duty.DutyApproval.Close'),
				handler:function(){
					dutyApprovalOperationWindow.close();
				}
			}]
		}]
	});

	var dutyApprovalOperationWindow = Ext.getCmp("dutyApprovalOperationWindowId");
	if(!dutyApprovalOperationWindow){
		dutyApprovalOperationWindow = Ext.create('DutyApprovalOperationWindow',{id:'dutyApprovalOperationWindowId'});
	}
/******************呼叫中心质检员审批操作页面********end***********************************/	

/******************统计员退回、质检员同意、质检员退回弹出框********begin***********************************/	
	

	

	/**
	 * 审批退回
	 */
	Ext.define('DutyApprovalPassFormPanel',{
		extend : 'BasicFormPanel',
		layout: {
	        align: 'stretch',type: 'vbox'
	    },
		items:[
			{
				xtype:'checkbox',name:'isSelected',
				fieldLabel:'',labelWidth:60,
				boxLabel:'退回责任部门重新反馈'
			},
			{//审批意见
				xtype:'textarea',
				labelWidth:60,
				allowBlank:false,
				maxLength:300,
				regex : new RegExp('^[^"]{0,}$'),
				regexText:i18n('i18n.Duty.pleaseNotInputDoubleYin'),
				emptyText:i18n('i18n.DutySearch.maxLength300'),
				blankText:i18n('i18n.Duty.DutyApproval.NullApprovalOpinion'),
				fieldLabel:i18n('i18n.Duty.DutyApproval.ApprovalOpinion'),
				flex:1,name:'callCenDescision'
			},
			{xtype: 'hiddenfield',name: 'dutyId'},
			{xtype: 'hiddenfield',name: 'detailId'},
			{xtype: 'hiddenfield',name: 'feedbackId'}
		]
	});

	/**
	 * 质检员单击退回按钮，弹出的填写退回意见窗口
	 * xiaohongye
	*/
	Ext.define('DutyApprovalSendBackByCallWindow',{
		extend:'PopWindow',
		title:i18n('i18n.Duty.DutyApproval.CallerApprovalOpinion'),
		alias : 'widget.basicwindow',
		width:450,
		height:280,
		modal:true,
		layout: 'fit',
		closeAction:'hide',
		items:[
			Ext.create('DutyApprovalPassFormPanel',{id:'dutyApprovalPassFormPanelId'})
		],
		listeners:{
			hide:function(){
				document.body.scrollLeft=0;
				document.body.scrollTop=0;
				document.getElementsByTagName("html")[0].style.overflowY="hidden";
				document.getElementsByTagName("html")[0].style.overflowX="hidden";
				
				Ext.getCmp('dutyApprovalPassFormPanelId').getForm().reset();
			}
		},
		dockedItems: [{
		    xtype: 'toolbar',
		    dock: 'bottom',
		    ui: 'footer',
		    items: [
				'->',
				{//提交
					xtype:'button',
					text:i18n('i18n.duty.submit'),
					handler:function(){
						var form = Ext.getCmp('dutyApprovalPassFormPanelId').getForm();
						if(!form.isValid()){
							return false;
						}
						//质检员审批退回操作
						var successFn = function(json){
							MessageUtil.showInfoMes(i18n('i18n.Duty.DutyApproval.ApprovalReturnBack'),function(){
								dutyApprovalSendBackByCallWindow.close();
								Ext.getCmp("dutyApprovalOperationWindowId").close();
								//刷新审批页面
								Ext.getCmp('dutyApprovalResultGridId').getStore().loadPage(1);
							});
						}
						var failureFn = function(json){
					    	MessageUtil.showErrorMes(json.message);
					    };
					    var param = {
					    		'dutyFeedback':{
					    			//责任反馈ID
					    			'feedbackId':form.findField('feedbackId').getValue(),
					    			//责任划分结果ID
							    	'detailId':form.findField('detailId').getValue(),
							    	//质检员审批意见
							    	'callCenDescision':form.findField('callCenDescision').getValue(),
							    	//责任编号
							    	'dutyId':form.findField('dutyId').getValue(),
							    	//是否退回营业部
							    	'isSelected':form.findField('isSelected').getValue()
					    		},'approvalType':'INSPECTOR_DISAGREE'
					    };
					    DutyApprovalStore.prototype.dutyApprovalOperation(param, successFn, failureFn);
					}
				},{//取消
					xtype:'button',
					text:i18n('i18n.duty.cancel'),
					handler:function(){dutyApprovalSendBackByCallWindow.close();}
				}
			]
		}]
	});
	//质检员审批退回时弹出的窗口
	var dutyApprovalSendBackByCallWindow = Ext.getCmp("dutyApprovalSendBackByCallWindowId");
	if(!dutyApprovalSendBackByCallWindow){
		dutyApprovalSendBackByCallWindow = Ext.create('DutyApprovalSendBackByCallWindow',{id:'dutyApprovalSendBackByCallWindowId'});
	}
	
});

//质检员审批同意
var callerAgree = function(feedbackId,detailId,dutyId){
	appDivDutyDivideWindow = Ext.getCmp("AppDivDutyDivideWindow");//获取工单责任划分操作窗口
	if(!appDivDutyDivideWindow){
		appDivDutyDivideWindow = Ext.create('AppDivDutyDivideWindow',{id:'AppDivDutyDivideWindow'});
	}
	appDivDutyDivideWindow.show();
	
	//加载数据
	appDivDutyDivideWindow.dutyDivideLook({
		'dutyId':dutyId,
		'detailId':detailId,
		'feedbackId':feedbackId
	});
}

//质检员审批不同意
var callerDisagree = function(feedbackId,detailId,dutyId){
	dutyApprovalSendBackByCallWindow = Ext.getCmp("dutyApprovalSendBackByCallWindowId");
	if(!dutyApprovalSendBackByCallWindow){
		dutyApprovalSendBackByCallWindow = Ext.create('DutyApprovalSendBackByCallWindow',{id:'dutyApprovalSendBackByCallWindowId'});
	}
	dutyApprovalSendBackByCallWindow.show();
	
	var form = Ext.getCmp('dutyApprovalPassFormPanelId').getForm();
	form.findField('dutyId').setValue(dutyId);
	form.findField('feedbackId').setValue(feedbackId);
	form.findField('detailId').setValue(detailId);
}