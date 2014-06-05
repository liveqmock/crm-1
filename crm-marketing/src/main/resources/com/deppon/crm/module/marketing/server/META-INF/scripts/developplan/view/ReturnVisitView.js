/*
 * 营销记录查询结果显示界面
 */
Ext.onReady(function(){
	//加载数据字典
	var keys = [
		//开发状态
		'CUSTOMER_IDEA',
		'DEVELOP_WAY',
		//需求分类
		'CUSTOMER_IDEA',
		//客户来源      
  	    'CUST_SOURCE',
  	    //客户类别，修改了客户类别数据字典
		'CUST_TYPE',
		//新增计划类别
  		'PLAN_TYPE',
  		//问卷适用范围
  		'SURVEY_RANGE'
	];
	//初始化业务参数
	initDataDictionary(keys);
	initDeptAndUserInfo();
	
	
	/**
	 * 营销记录查询详情的form
	 * xiaohongye
	 */
	Ext.define('CustomerInfoInMarketRecordPanel',{
		extend:'TitleFormPanel',  
		items:null,
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [{
				xtype:'basicfiledset',
				title:i18n('i18n.marketRecord.marketRecordDetailInfo'),
				layout:{
					type:'table',
					columns:4
				},
				defaults:{
					labelAlign:'right',
					labelWidth:65,
					width:180,
					margin:'4 5 4 5'
				},
				defaultType:'textfield',
				items:[{
					xtype:'datefield',
					id:'executorTime',
					name:'executorTime',
					fieldLabel:i18n('i18n.marketRecord.marketTime'),
					format:'Y-m-d',
					value:new Date(),
					readOnly:true,
					cls:'readonly'
				},{
					fieldLabel:i18n('i18n.returnVisit.psCustomerName'),
					id:'psCustomerName',
					name:'psCustomerName',
					readOnly:true,
					cls:'readonly'
				},{
					fieldLabel:i18n('i18n.returnVisit.linkManMobile'),
					id:'linkManMobile',
					name:'linkManMobile',
					readOnly:true,
					cls:'readonly'
				},{
					fieldLabel:i18n('i18n.returnVisit.linkManPhone'),
					id:'linkManPhone',
					name:'linkManPhone',
					readOnly:true,
					cls:'readonly'
				},{//营销方式
					xtype:'combo',
					fieldLabel:i18n('i18n.marketRecord.marketWay'),
					id:'way',
					name:'way',
//					cls:'readonly',
					typeAhead: true,
				    triggerAction: 'all',
				    queryMode: 'local',
				    store: getDataDictionaryByName(DataDictionary,'DEVELOP_WAY'),
				    valueField: 'code',
				    displayField: 'codeDesc',
					forceSelection :true,
	                allowBlank: false,
					listeners:{
						change:DButil.comboSelsct
					}
				},{
					fieldLabel:i18n('i18n.returnVisit.linkName'),
					id:'linkName',
					name:'linkName',
					readOnly:true,
					cls:'readonly'
				},{//营销依据
					fieldLabel:i18n('i18n.marketRecord.marketRecordDepends'),
					id:'accordingDesc',
					name:'accordingDesc',
					readOnly:true,
					cls:'readonly'
				},{
					fieldLabel:i18n('i18n.returnVisit.theme'),
					id:'theme',
					name:'theme',
					readOnly:true,
					cls:'readonly'
				},{
					id:'psCustomerId',
					name:'psCustomerId',
					//fieldLabel:i18n('i18n.Cycle.customerId'),
					hidden:true,
					readOnly:true,
					cls:'readonly'
				},{
					id:'memberId',
					name:'memberId',
					//fieldLabel:i18n('i18n.Cycle.customerId'),
					hidden:true,
					readOnly:true,
					cls:'readonly'
				},{
					id:'according',
					name:'according',
					//fieldLabel:i18n('i18n.ReturnVisitAdd.evidenceId'),
					hidden:true,
					readOnly:true,
					cls:'readonly'
				},{
					id:'linkManId',
					name:'linkManId',
					//fieldLabel:i18n('i18n.ReturnVisitAdd.inkmanId'),
					hidden:true,
					readOnly:true,
					cls:'readonly'
				},{
					id:'planId',
					name:'planId',
					//fieldLabel:i18n('i18n.ReturnVisitAdd.developMain'),
					hidden:true,
					readOnly:true,
					cls:'readonly'
				},{
					id:'scheduleId',
					name:'scheduleId',
					//fieldLabel:i18n('i18n.ReturnVisitAdd.scheDuleId'),
					hidden:true,
					readOnly:true,
					cls:'readonly'
				},{
					xtype:'hiddenfield',
					name:'scheType',
					id:'scheType'
				}]
			}];
		}
	});
	
	
	
	/**
	 * 营销记录详情页面整体布局panel
	 */
	Ext.define('CustomerMarketRecordPanel', {
		extend : 'BasicPanel',
		layout : 'border',
		items : [
		         //营销记录详情基本信息panel
		         {
					xtype : 'basicpanel',
					region : 'north',
					height : 100,
					layout:'fit',
					items:[Ext.create('CustomerInfoInMarketRecordPanel',{'id':'customerInfoFormPanel'})]
				},
				//走货潜力panel
				{
					region : 'center',
					xtype : 'basicpanel',
					layout:'border',
					items:[{
						xtype:'basicpanel',
						region:'center',
						layout:'fit',
						flex:1,
						items:[Ext.create('SendGoodsPontentialPanel',{'id':'sendGoodsPontentialPanel'})]
					},
					//客户意见panel
					{
						xtype:'basicpanel',
						region:'south',
						flex:1,
						layout:'fit',
						items:[Ext.create('CustomerOpinionPanel',{'id':'customerOpinionPanel'})]
					}]
				},
				//跟踪日程制定panel
				{
					xtype : 'basicpanel',
					region : 'south',
					height : 150,
					id:'scheduleMakePanel',
					layout:'fit',
					items:[Ext.create('ScheduleMakeForm',{'id':'scheduleMakeForm'})]
				}]
		});

	/**
	 * 回访查询列表
	 */	
	Ext.define('ReturnVisitGird', {
		extend:'SearchGridPanel',
		store :null,
		columnLines:true,
		plugins:null,
		selModel : Ext.create('Ext.selection.CheckboxModel',{mode:'MULTI'}),
		columns:null,
		viewConfig: { 
	   	 	forceFit:true 
	    }, 
		initComponent:function(){
			var me = this;
			this.dockedItems=[{
				xtype:'pagingtoolbar',
				store:me.store,
				dock:'bottom',
				displayInfo : true,
				autoScroll : true
			}];
		this.callParent();
		}
	});

	/*
	 * 营销记录查询条件输入form
	 * 
	 * 
	 */
	Ext.define('ReturnVisitForm',{
		extend:'SearchFormPanel',
		planeType:null,
		layout:{
			type:'table',
			columns:3
		},
		defaults:{
			labelWidth:80,
			labelAlign:'right',
			width:240,
			margin:'4 5 4 5'
		},
		defaultType:'combobox',
		items:null,
		getItems:function(){
			var me = this;
			return [{
				fieldLabel :i18n('i18n.marketRecord.marketDept'),
				name:'deptName',
				store:Ext.create('DeptStore',{
					listeners:{
						beforeload:function(store, operation, eOpts){
							Ext.apply(operation,{
								params : {
									'deptName':Ext.getCmp("returnVisitForm").getForm().findField("deptName").getRawValue()
									}
								}
							);	
						}
					}
				}),
				//queryMode: 'local',
//				colspan:2,
				allowBlank:false,
				triggerAction : 'all',
				displayField:'deptName',
				valueField:'id',
				forceSelection :true,
//				selectOnFocus:false,
				hideTrigger:false,
				emptyText:'请输入部门关键字',
				pageSize: 10,
				minChars:2,
				queryDelay:800,
//				typeAhead:true,//根据输入框值自动补全
				listConfig: {
	  	        	minWidth :300,
	  	            getInnerTpl: function() {
	  	            	 return '{deptName}';
	  	            }
	  	        },
				listeners:{
					change:DButil.comboSelsct,
					select:function(th,records){
						Ext.getCmp("returnVisitForm").getForm().findField("execUserId").store.load({params:{executeDept:th.value}});
					}
				}
			}
//			        {//营销部门
//						fieldLabel :i18n('i18n.marketRecord.marketDept'),
//						xtype:'combo',
//						name:'deptName',
//			            store:Ext.create('DeptStore',{
//							listeners:{
//								load:function(){
//									Ext.getCmp("returnVisitForm").getForm().findField("deptName").setValue(User.deptId);
//								}
//							}
//						}),
//						queryMode: 'local',
//						triggerAction : 'all',
//						displayField:'deptName',
//						valueField:'id',
//						oldValue:null,
//						editable:       false,
//						forceSelection :true,
//						listeners:{
//							change:DButil.comboSelsct,
//							'select':function(th,records){
//								
//							}
//						}
//					}
			        ,{//客户类别
						xtype:'combo',
						name:'customerType',
						id:'customerType',
						allowBlank:false,
						fieldLabel:'客户类型',
						queryMode:'local',
						store:getDataDictionaryByName(DataDictionary,'CUST_TYPE'),
						displayField:'codeDesc',
						valueField:'code',
						value:'RC_CUSTOMER',
						forceSelection :true,
						listeners:{
							change:DButil.comboSelsct
						}
					},{//客户编码
						xtype:'textfield',
						fieldLabel:i18n('i18n.customerGroup.custNumber'),
						name:'custNumber',
						id:'custNumber'
					},{//潜客来源
						fieldLabel:i18n('i18n.PotentialCustManagerView.potentialCustSource'),
						name:'potentialCustSource',
						store:getDataDictionaryByName(DataDictionary,'CUST_SOURCE'),
						queryMode: 'local',
						displayField:'codeDesc',
						valueField:'code',
						emptyText : '',
						forceSelection :true,
						listeners:{
							change:DButil.comboSelsct
						}
					},{//需求分类
						fieldLabel:i18n('i18n.returnVisit.opinionType'),
						name:'opinion',
						id:'searchopinion',
						store:getDataDictionaryByName(DataDictionary,'CUSTOMER_IDEA'),
						queryMode: 'local',
						displayField:'codeDesc',
						valueField:'code',
						emptyText : '',
						forceSelection :true,
						listeners:{
							change:DButil.comboSelsct
						}
					},{//联系人姓名
						xtype:'textfield',
						fieldLabel:i18n('i18n.returnVisit.linkName'),
						name:'linkName',
						id:'searchlinkName'
					},{//计划主题
						xtype:'textfield',
						fieldLabel:i18n('i18n.returnVisit.theme'),
						name:'planName'
					},{//营销方式
						fieldLabel:i18n('i18n.marketRecord.marketWay'),
						name:'way',
						id:'searchway',
						store:getDataDictionaryByName(DataDictionary,'DEVELOP_WAY'),
						queryMode: 'local',
						triggerAction : 'all',
						displayField:'codeDesc',
						valueField:'code',
						emptyText : '',
						forceSelection :true,
						listeners:{
							change:DButil.comboSelsct
						}
					},{//营销人
						fieldLabel:i18n('i18n.marketRecord.marketName'),
						name:'execUserId',
						id:'searchexecUserId',
						store:Ext.create('UserStore',{autoLoad:true}),
						queryMode: 'local',
						triggerAction : 'all',
						queryParam : 'planeDraft',
						displayField:'empName',
						valueField:'id',
						forceSelection :true,
						//剔出朦层
						listConfig: {
			  				  loadMask:false
						},
						listeners:{
							change:DButil.comboSelsct
						}
					},{//营销时间
						xtype:'datefield',
						fieldLabel:i18n('i18n.marketRecord.marketTime'),
						id:'searchbeginTime',
						name:'beginTime',
						format:'Y-m-d',
						editable:false,
						value:DButil.getMonthStartDate()
					},{//回访到时间
						xtype:'datefield',
						name:'endTime',
						fieldLabel:i18n('i18n.CommonView.to'),
						id:'searchendTime',
						format:'Y-m-d',
						editable:false,
						value:new Date()
					}
				];
		},initComponent:function(){
			var me = this;
			me.items = this.getItems();
			this.callParent();
		}
	});
	
	/**
	 * 按钮区
	 */
	Ext.define('ButtonPanel',{
		extend:'NormalButtonPanel',
		items:null,
		region:'south',
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [{
				xtype:'leftbuttonpanel', 
				items:[{xtype:'button',text:i18n('i18n.DevelopManageView.find'),
					handler:function(){
						//获取选中的表格
						var selection=Ext.getCmp('returnVisitGird').getSelectionModel().getSelection();
						//判断是否选中行
						//判断是否选中行
						if (selection.length == 0) {
							MessageUtil.showMessage(i18n('i18n.developPlan.choice'));
							return false;
						}
						//判断是否只选中一行记录
						if (selection.length != 1) {
							MessageUtil.showMessage(i18n('i18n.developPlan.chooseOne'));
							return false;
						}
						//判断所选记录中的问卷id是否为空，如果不为空，则弹出问卷回访详情页面，如果为空则弹出走货潜力、客户需求回访详情页面
						if(!Ext.isEmpty(selection[0].get("surveyId"))){
							var questionnaireDetailPopWindow = Ext.getCmp("questionnaireDetailPopWindowId");//获取win
			        		if(!questionnaireDetailPopWindow){
			        			questionnaireDetailPopWindow = Ext.create('QuestionnaireDetailPopWindow',{id:'questionnaireDetailPopWindowId'});
			        		}
			        		//获得问卷基本信息面板
			        		var questionnaireDetailForm = Ext.getCmp('questionnaireDetailFormId').getForm();
			        		//重置查看详情页面
			        		questionnaireDetailForm.reset();
			        		
			        		//根据问卷id查询问卷基本信息
			        		var successFunction = function(json){
			        			var questionnaireList = json.questionnaireList;
			        			if(!Ext.isEmpty(questionnaireList)){
			        				var model = new QuestionnaireInfoModel(questionnaireList[0]);
					        		questionnaireDetailForm.loadRecord(model);
			        			}
			        		}
			        		//查询问卷失败回调函数
			        		var failureFunction = function(json){
			        			if(!Ext.isEmpty(json)){
			        				MessageUtil.showErrorMes(json.message);
			        			}
			        		}
			        		CommonQuestionnaireStore.prototype.searchQuestionnaireInfoList({
		        				'questionnaireQueryCondi.questionnaireId':selection[0].get('surveyId'),
		        				'start':0,
		        				'limit':1
		        			},successFunction,failureFunction);
			        		Ext.getCmp('questionListGridId').store.load({params:{'visitId':selection[0].get('id')}});
			        		//查询问卷详情成功回调函数
			        		function successFn(json){
			        			var questions = json.questionList;
			        			var store = Ext.getCmp('questionListGridId').store;
			        			for(var i = 0;i < questions.length;i++){
			        				var question = new QuestionInfoModel(questions[i]);
			        				store.add(question);
			        			}
			        		}
			        		//查询问卷失败回调函数
			        		function failureFn(json){
			        			MessageUtil.showErrorMes(json.message);
			        		}
			        		questionnaireDetailPopWindow.show();
						}else{
							var customerOpinionGrid = Ext.getCmp('customerOpinionGrid');
							var sendGoodsPontentialGrid = Ext.getCmp('sendGoodsPontentialGrid');
							//添加不可编辑事件
							sendGoodsPontentialGrid.on('beforeedit',function(th){
								return false;
							});
							customerOpinionGrid.on('beforeedit',function(th){
								return false;
							});
							var customerInfoFormPanel = Ext.getCmp("customerInfoFormPanel").getForm();
							var successFn = function(json){
								//load客户信息form
								var initData=new InitDataModel(json.returnVisit);
								customerInfoFormPanel.loadRecord(initData);
								//load客户发货潜力grid
								var goodsPontentialArray = [];
								for(var i = 0;i<json.returnVisitVolumePotentialList.length;i++){
									var sendGoodsPontentialModel = new SendGoodsPontentialModel(json.returnVisitVolumePotentialList[i]);
									goodsPontentialArray.push(sendGoodsPontentialModel);
								}
								sendGoodsPontentialGrid.store.loadRecords(goodsPontentialArray);
								//load客户意见grid
								var customerOpinionArray = [];
								for(var i = 0;i<json.returnVisitOpinionList.length;i++){
									var customerOpinionModel = new CustomerOpinionModel(json.returnVisitOpinionList[i]);
									customerOpinionArray.push(customerOpinionModel);
								}
								customerOpinionGrid.store.loadRecords(customerOpinionArray);
							};
							var failureFn = function(json){
								MessageUtil.showErrorMes(json.message);
							};
							
							ReturnVisitData.prototype.queryRetureVisitDetailed({'returnVisit.id':selection[0].get("id")}, successFn, failureFn);
						    //清空走货潜力表格
							 sendGoodsPontentialGrid.store.removeAll();
							//清空客户意见表格
							customerOpinionGrid.store.removeAll();
							//显示界面
							win.show();
							document.getElementsByTagName("html")[0].style.overflowY="auto";
							document.getElementsByTagName("html")[0].style.overflowX="auto";
							viewport.doLayout();
							Ext.getCmp('scheduleMakePanel').hide();
							Ext.getCmp('SendGoodsAddBtn').hide();
							Ext.getCmp('SendGoodsDeleteBtn').hide();
							Ext.getCmp('CustomerOpinionAddBtn').hide();
							Ext.getCmp('CustomerOpinionDeleteBtn').hide();
							Ext.getCmp("way").setReadOnly(true);
							Ext.getCmp('customerReturnVisitPanel').doLayout();
						}
					}},{//营销记录查询按钮
						xtype:'button',
						text:i18n('i18n.marketRecord.marketRecord'),
						handler:function(){
							//获取选中的表格
							var selection=Ext.getCmp('returnVisitGird').getSelectionModel().getSelection();
							//判断是否有且选中一行记录
							if (selection.length == 0) {
								MessageUtil.showMessage(i18n('i18n.developPlan.choice'));
								return false;
							}
							if (selection.length != 1) {
								MessageUtil.showMessage(i18n('i18n.developPlan.chooseOne'));
								return false;
							}
							
							if(!Ext.isEmpty(selection[0].get("memberId"))){
								custIDForMarketRecord = selection[0].get("memberId");
								custTypeForMarketRecord = 'MEMBER';
							}
							else if(!Ext.isEmpty(selection[0].get("psCustomerId"))){
								custIDForMarketRecord = selection[0].get("psCustomerId");
								custTypeForMarketRecord = 'PC_CUSTOMER';
							}
							else {
								MessageUtil.showMessage(i18n('i18n.customerCallIn.nullCustId'));
								return false;
							}
							
							Ext.getCmp('sendGoodsPontentialResultGridId').store.loadPage(1);
		  					Ext.getCmp('customerDemandResultGridId').store.loadPage(1);
		  					Ext.getCmp('questionnaireResultGridForMarketRecordId').store.loadPage(1);
							//点击按钮，弹出营销历史记录查询结果窗口
							marketHistoryRecordWindow.show();
							
						}}]
				},{
					xtype:'middlebuttonpanel' 
				},{
					xtype:'rightbuttonpanel', 
					items:[{ 
						xtype:'button',
						text:i18n('i18n.returnVisit.search'),
						handler:function(){
							if(!Ext.getCmp("returnVisitForm").getForm().isValid()){
								MessageUtil.showMessage(i18n('i18n.PotentialCustManagerView.message_warn'))
								return false;
							}
							if(Ext.isEmpty(Ext.getCmp('returnVisitForm').getForm().findField('customerType').getValue())){
								MessageUtil.showMessage(i18n('i18n.returnvisit.customerType'));
								return false;
							}
							var startDate = Ext.getCmp("returnVisitForm").getForm().findField("beginTime").getValue();
							var endDate = Ext.getCmp("returnVisitForm").getForm().findField("endTime").getValue();
							if(!Ext.isEmpty(startDate)){
								if(!Ext.isEmpty(endDate)){
									var days = DButil.compareTwoDate(startDate,endDate);
									if(days<0){
										MessageUtil.showInfoMes(i18n('i18n.developPlan.startDateBGEndDate'));
										return;
									}else if(days>90){
										MessageUtil.showInfoMes(i18n('i18n.developPlan.queryDateLimit'));
										return;
									}
									//load数据
				  					Ext.getCmp('returnVisitGird').store.loadPage(1);
								}else{
									MessageUtil.showInfoMes("回访结束时间不能为空~");
									return;
								}
							}else{
								MessageUtil.showInfoMes("回访起始时间不能为空~");
								return;
							}
							
						}
					}]
				}];
		}
	});
		
	Ext.define('ReturnVisitViewPanel',{
		extend:'BasicPanel',
		layout:'border',
		items:null,
		buttonBar:null,
		getColumnsItems:function(){
			return [{
				xtype:'rownumberer',
				width:40,
				align:'center',
				header:i18n('i18n.Cycle.rownum')
			},{
				header :i18n('i18n.returnVisit.linkName'),
				dataIndex : 'linkName',
				align : 'center',
				sortable : true,
				width:80
			},{
				header :i18n('i18n.returnVisit.linkManMobile'),
				dataIndex : 'linkManMobile',
				align : 'center',
				sortable : true,
				width:95
			},{
				header : i18n('i18n.returnVisit.linkManPhone'),
				dataIndex : 'linkManPhone',
				align : 'center',
				sortable : true
			},{
				header :i18n('i18n.returnVisit.theme'),
				dataIndex : 'theme',
				align : 'center',
				sortable : true
			},{
				header:i18n('i18n.developSchedule.custName'),
				dataIndex:'memberName',
				align : 'center',
				sortable : true
			},{//营销部门
				header:i18n('i18n.marketRecord.marketDept'),
				dataIndex:'departName',
				align : 'center',
				sortable : true
			},{//需求分类
				header : i18n('i18n.returnVisit.demandType'),
				dataIndex : 'needType',
				align : 'center',
				sortable : true,
				renderer:function(value){
					return DButil.rendererDictionary(value,DataDictionary.CUSTOMER_IDEA);
				}
			},{
				header : i18n('i18n.marketRecord.marketWay'),
				dataIndex : 'way',
				sortable : true,
				align : 'center',
				renderer:function(value){
					return DButil.rendererDictionary(value,DataDictionary.DEVELOP_WAY);
				},
				width:70
			},{
				header : i18n('i18n.marketRecord.marketName'),
				dataIndex : 'userName',
				width:75
			},{//营销时间
				header : i18n('i18n.marketRecord.marketTime'),
				dataIndex : 'executorTime',
				align : 'center',
				sortable : true,
				renderer : DButil.renderDate,
				editor: {
		               xtype: 'datefield',
		               format: 'Y-m-d'
	            }
			},{
				header:i18n('i18n.developSchedule.requireAndPro'),
				dataIndex:'problem',
				align : 'center',
				sortable : true
			},{
				header:i18n('i18n.developSchedule.solution'),
				dataIndex:'solution',
				align : 'center',
				sortable : true,
				width : 180
			}];
		},
		initComponent:function(){
			var me = this;
				//给store添加事件,来在点击查询时，将查询条件传递到后台 
				var returnVisitViewStore =  Ext.create('ReturnVisitViewStore',{'id':'returnVisitViewStore'});
				//获取表格
				var returnVisitGird = Ext.create('ReturnVisitGird',{'id':'returnVisitGird','columns':me.getColumnsItems(),
					'store':returnVisitViewStore})
				//添加查询条件
				returnVisitViewStore.on('beforeLoad',function(returnVisitViewStore,operation,e){
					
					//封装查询条件传递给action
					var returnVisitForm = Ext.getCmp("returnVisitForm");
					var searchParams = {
						'returnVisitQueryCondition.linkName':returnVisitForm.getForm().findField('searchlinkName').getValue(),
						'returnVisitQueryCondition.planName':returnVisitForm.getForm().findField('planName').getValue(),
						'returnVisitQueryCondition.opinion':returnVisitForm.getForm().findField('searchopinion').getValue(),
						'returnVisitQueryCondition.way':returnVisitForm.getForm().findField('searchway').getValue(),
						'returnVisitQueryCondition.beginTime':returnVisitForm.getForm().findField('searchbeginTime').getValue(),
						'returnVisitQueryCondition.endTime':returnVisitForm.getForm().findField('searchendTime').getValue(),
						'returnVisitQueryCondition.customerType':returnVisitForm.getForm().findField('customerType').getValue(),
						'returnVisitQueryCondition.execUserId':returnVisitForm.getForm().findField('searchexecUserId').getValue(),
						'returnVisitQueryCondition.deptId':returnVisitForm.getForm().findField('deptName').getValue(),
						'returnVisitQueryCondition.custNumber':returnVisitForm.getForm().findField('custNumber').getValue(),
						//客户来源,配合需求编号为ROWS2013070101新增的字段，用以查询客户的潜客来源
						'returnVisitQueryCondition.potentialCustSource':returnVisitForm.getForm().findField('potentialCustSource').getValue()
					};
					Ext.apply(operation,{
							params : searchParams
					});
	//				alert(returnVisitForm.getForm().findField('searchexecUserId').getValue());
				});
										
				
				//添加双击事件
				returnVisitGird.on('itemdblclick',function(){
					var customerOpinionGrid = Ext.getCmp('customerOpinionGrid');
					var sendGoodsPontentialGrid = Ext.getCmp('sendGoodsPontentialGrid');
					//添加不可编辑事件
					sendGoodsPontentialGrid.on('beforeedit',function(th){
						return false;
					});
					customerOpinionGrid.on('beforeedit',function(th){
						return false;
					});
					var customerInfoFormPanel = Ext.getCmp("customerInfoFormPanel").getForm();
					var successFn = function(json){
						//load客户信息form
						var initData=new InitDataModel(json.returnVisit);
						customerInfoFormPanel.loadRecord(initData);
						//load客户发货潜力grid
						var goodsPontentialArray = [];
						for(var i = 0;i<json.returnVisitVolumePotentialList.length;i++){
							var sendGoodsPontentialModel = new SendGoodsPontentialModel(json.returnVisitVolumePotentialList[i]);
							goodsPontentialArray.push(sendGoodsPontentialModel);
						}
						sendGoodsPontentialGrid.store.loadRecords(goodsPontentialArray);
						//load客户意见grid
						var customerOpinionArray = [];
						for(var i = 0;i<json.returnVisitOpinionList.length;i++){
							var customerOpinionModel = new CustomerOpinionModel(json.returnVisitOpinionList[i]);
							customerOpinionArray.push(customerOpinionModel);
						}
						customerOpinionGrid.store.loadRecords(customerOpinionArray);
					};
					var failureFn = function(json){
						MessageUtil.showErrorMes(json.message);
					};
					
					//获取选中的表格
					var selection=Ext.getCmp('returnVisitGird').getSelectionModel().getSelection();
					ReturnVisitData.prototype.queryRetureVisitDetailed({'returnVisit.id':selection[0].get("id")}, successFn, failureFn);
				    //清空走货潜力表格
					 sendGoodsPontentialGrid.store.removeAll();
					//清空客户意见表格
					customerOpinionGrid.store.removeAll();
					//显示界面
					win.show();
					document.getElementsByTagName("html")[0].style.overflowY="auto";
					document.getElementsByTagName("html")[0].style.overflowX="auto";
					viewport.doLayout();
					Ext.getCmp('scheduleMakePanel').hide();
					Ext.getCmp('SendGoodsAddBtn').hide();
					Ext.getCmp('SendGoodsDeleteBtn').hide();
					Ext.getCmp('CustomerOpinionAddBtn').hide();
					Ext.getCmp('CustomerOpinionDeleteBtn').hide();
					Ext.getCmp("way").setReadOnly(true);
					Ext.getCmp('customerReturnVisitPanel').doLayout();
					
			});
			
			//界面布局
			me.items = [{
				xtype:'basicpanel',
				region:'north',
				layout:'border',
				height:180,
				items:[{
					xtype:'basicpanel',
					region:'center',
					layout:'fit',
					items:[Ext.create('ReturnVisitForm',{'id':'returnVisitForm','planeType':'dev'})]
				},Ext.create('ButtonPanel')]
			},{
				xtype:'basicpanel',
				region:'center',
				layout:'fit',
				items:[returnVisitGird]
			}];
			var deptModel=new DeptModel();
			deptModel.set("id",User.deptId);
			deptModel.set("deptName",User.deptName);
			Ext.getCmp("returnVisitForm").getForm().findField("deptName").store.add(deptModel);
			Ext.getCmp("returnVisitForm").getForm().findField("deptName").setValue(User.deptId);
	//		Ext.getCmp("searchplanId").store.load();
			this.callParent();
		}
	});
	
	/**
	 * 显示制定、修改计划
	*/
	Ext.define('CreateRevisitDetailWindow',{
		extend:'PopWindow',
		alias : 'widget.basicwindow',
		width:820,//########
		height:600,
		modal:true,
		layout:'fit',
		closeAction:'hide',
		items:[Ext.create('CustomerMarketRecordPanel',{'id':'customerReturnVisitPanel'})],
		listeners:{
			hide:function(){
				document.body.scrollLeft=0;
				document.body.scrollTop=0;
				document.getElementsByTagName("html")[0].style.overflowY="hidden";
				document.getElementsByTagName("html")[0].style.overflowX="hidden";
				viewport.doLayout();
			}
		},
		buttons:[{
			xtype:'button',
			text:i18n('i18n.DevelopManageView.close'),
			handler:function(){
				Ext.getCmp("createRevisitDetailWindow").close();
			}
		}]
	});
	var win=Ext.getCmp("createRevisitDetailWindow");//获取win
	if(!win){
		win=Ext.create('CreateRevisitDetailWindow',{id:'createRevisitDetailWindow'});
	}
	
	/**
	 * 单击营销记录按钮，弹出的营销历史记录查询结果窗口
	*/
	Ext.define('MarketHistoryRecordWindow',{
		extend:'PopWindow',
		title:'营销历史记录',
		alias : 'widget.basicwindow',
		width:820,
		height:600,
		modal:true,
		layout:'fit',
		closeAction:'hide',
		items:[Ext.create('MarketHistoryResultPanel',{'id':'marketHistoryResultPanelId'})],
		listeners:{
			hide:function(){
				document.body.scrollLeft=0;
				document.body.scrollTop=0;
				document.getElementsByTagName("html")[0].style.overflowY="hidden";
				document.getElementsByTagName("html")[0].style.overflowX="hidden";
				viewport.doLayout();
			}
		},
		buttons:[{
			xtype:'button',
			text:i18n('i18n.DevelopManageView.close'),
			handler:function(){
				Ext.getCmp("marketHistoryRecordWindowId").hide();
			}
		}]
	});
	
	var marketHistoryRecordWindow = Ext.getCmp("MarketHistoryRecordWindow");//获取win
	if(!marketHistoryRecordWindow){
		marketHistoryRecordWindow = Ext.create('MarketHistoryRecordWindow',{id:'marketHistoryRecordWindowId'});
	}
	
	/**
	 *将界面显示到viewport中 
	 */
	var viewport=Ext.create('Ext.Viewport',{
		layout : 'border',
		autoScroll:true,
		items:[Ext.create('ReturnVisitViewPanel',{region:'center'})]
	});
	viewport.setAutoScroll(false);
	viewport.doLayout();
});