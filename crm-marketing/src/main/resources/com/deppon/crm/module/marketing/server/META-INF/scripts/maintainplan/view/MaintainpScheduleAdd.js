var developScheduleData =  new DevelopScheduleData();//初始化计划新增修改data
Ext.onReady(function() {
	var keys=[
  		// 行业
  		'TRADE',
  		//二级行业
		'SECOND_TRADE',
  		//客户属性
  		'CUSTOMER_NATURE',
  		//客户性质
  		'CUSTOMER_TYPE',
  		// 商机状态
  		'BUSINESS_STATUS',
  		// 合作意向
  		'COOPERATION_INTENTION',
  		// 货量潜力
  		'CARGO_POTENTIAL',
  		//会员等级
  		'MEMBER_GRADE',
		// 货量潜力
		'CARGO_POTENTIAL',
  		//营销方式
  		'DEVELOP_WAY',
  		//业务类别 标识零担，快递，零担和快递
  		'CUST_CATEGORY',
  		//合作意向 潜散客 盛诗庆
  		'COOPERATION_INTENTION',
  		//新增商机状态 标识商机计划的状态 盛诗庆
  		'BUSINESS_OPPORTUNITY_STATUS',
  		//客户来源
  		'CUST_SOURCE',
  		//货量潜力
  		'CARGO_POTENTIAL',
  		//将潜散客商机状态修改为开发阶段
  		'DEVELOP_STAGE',
  		//继续营销
  		'CONTINUE_MARKET',
  		//客户类型 标识潜散客，固定客户
  		'CUST_TYPE',
  		//问卷适用范围
  		'SURVEY_RANGE'
  		
  	];
	//初始化业务参数
	initDataDictionary(keys);
	initDeptAndUserInfo();
	/**
	 * <p>
	 * 开发计划新增修改查询条件Form
	 * </p>
	 * @author  张登
	 * @date    2012-03-13
	 */
	Ext.define('SearchConditionForm',{
		extend:'SearchFormPanel',
		items:null,
//		deptShowBj:null,
		initComponent:function(){
			var me = this;
			me.items = me.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return[{
				xtype : 'panel',
				layout : {
					type : 'table',
					columns : 12
				},
				defaultType : 'textfield',
				defaults : {
					labelWidth : 65,
	                labelAlign: 'right',
					width : 190
				},
				items : [{
					fieldLabel : i18n('i18n.PotentialCustManagerView.deptName'),
					name:'deptName',
					xtype:'combo',
					store:Ext.create('DeptStore',{id:'queryDeptStoreId',
						listeners:{
							beforeload:function(store, operation, eOpts){
								Ext.apply(operation,{
									params : {
										'deptName':Ext.getCmp("SearchConditionFormId").getForm().findField("deptName").getRawValue()
										}
									}
								);	
							}
						}
					}),
					colspan:3,
					allowBlank :false,
					triggerAction : 'all',
					displayField:'deptName',
					valueField:'id',
					forceSelection :true,
					hideTrigger:false,
					emptyText:'请输入部门关键字',
					pageSize: 10,
					minChars:2,
					queryDelay:800,
					listConfig: {
		  	        	minWidth :300,
		  	            getInnerTpl: function() {
		  	            	 return '{deptName}';
		  	            }
		  	        },
					listeners:{
						change:DButil.comboSelsct
					}
				},{//新增业务类别 标识客户是零担，快递，还是零担加快递 盛诗庆
					fieldLabel : '业务类别',
					xtype : 'combo',
					colspan : 3,
					name : 'custCategory',
					store:getDataDictionaryByName(DataDictionary,'CUST_CATEGORY'),
					queryMode:'local',
					displayField:'codeDesc',
					valueField:'code',  
					forceSelection :true,
					listeners:{
						change:DButil.comboSelsct
					}
				},{//合并客户类型 标识潜散客，固定客户 盛诗庆
					fieldLabel : '客户类型',
					xtype : 'combo',
					colspan : 3,
					name : 'custType',
					store:getDataDictionaryByName(DataDictionary,'CUST_TYPE'),
					queryMode:'local',
					displayField:'codeDesc',
					valueField:'code',  
					forceSelection :true,
					value : 'PC_CUSTOMER',
					allowBlank : false,
					blankText : '请选择客户类型',
					listeners:{
						change:DButil.comboSelsct,
						select:function(th){
							if(th.value == 'RC_CUSTOMER'){//刷新客户等级下拉列表中数据
								controlForm(me.getForm(),false);
								me.getForm().findField('developStage').setValue("");
								me.getForm().findField('developStage').store.removeAll();//清空开发阶段
								var dictStore = getDataDictionaryByName(DataDictionary,'MEMBER_GRADE').data;
								var store = me.getForm().findField('memberLevel').store;
								var data = new Array();
								dictStore.each(function(model){
									data.push(model);
								})
								store.loadData(data);
							}else{
								if(th.value == 'PC_CUSTOMER'){//潜客禁用发到货输入框
									controlForm(me.getForm(),true);	
								}else{
									controlForm(me.getForm(),false);	
								}
								var dictStore = getDataDictionaryByName(DataDictionary,'DEVELOP_STAGE').data;
								var store = me.getForm().findField('developStage').store;
								var data = new Array();
								dictStore.each(function(model){
									data.push(model);
								})
								store.loadData(data);
								me.getForm().findField('memberLevel').setValue(null);
								me.getForm().findField('memberLevel').store.removeAll();
							}
						}
					}
				},{//合并合作意向 针对潜散客客户 盛诗庆
					fieldLabel : '合作意向',
					xtype : 'combo',
					colspan : 3,
					name : 'cooperationIntention',
					store:getDataDictionaryByName(DataDictionary,'COOPERATION_INTENTION'),
					queryMode:'local',
					displayField:'codeDesc',
					valueField:'code',  
					forceSelection :true,
					listeners:{
						change:DButil.comboSelsct
					}
				},{//合并客户来源查询条件 盛诗庆
					fieldLabel : '客户来源',
					xtype : 'combo',
					colspan : 3,
					name : 'custSorce',
					store:getDataDictionaryByName(DataDictionary,'CUST_SOURCE'),
					queryMode:'local',
					displayField:'codeDesc',
					valueField:'code',  
					forceSelection :true,
					listeners:{
						change:DButil.comboSelsct
					}
				},{//合并货量潜力 盛诗庆
					fieldLabel : '货量潜力',
					xtype : 'combo',
					colspan : 3,
					name : 'cargoPotential',
					store:getDataDictionaryByName(DataDictionary,'CARGO_POTENTIAL'),
					queryMode:'local',
					displayField:'codeDesc',
					valueField:'code',  
					forceSelection :true,
					listeners:{
						change:DButil.comboSelsct
					}
				},{//修改开发维护计划商机状态为开发阶段 盛诗庆
					fieldLabel : '开发阶段',
					xtype : 'combo',
					colspan : 3,
					name : 'developStage',
					store:getDataDictionaryByName(DataDictionary,'DEVELOP_STAGE'),
					queryMode:'local',
					displayField:'codeDesc',
					valueField:'code',  
					forceSelection :true,
					listeners:{
						change:DButil.comboSelsct
					}
				},{//继续营销
					fieldLabel : '继续营销',
					xtype : 'combo',
					colspan : 3,
					name : 'continueMarket',
					store:getDataDictionaryByName(DataDictionary,'CONTINUE_MARKET'),
					queryMode:'local',
					displayField:'codeDesc',
					valueField:'code',  
					forceSelection :true,
					listeners:{
						change:DButil.comboSelsct
					}
				},{
					fieldLabel : i18n('i18n.Maintainp.memberNo'),
					colspan : 3,
					maxLength : 40,
					maxLengthText : i18n('i18n.developPlan.maxLength')+40,
					name : 'memberNo'
				},{
					fieldLabel : i18n('i18n.PotentialCustManagerView.customerName'),
					colspan : 3,
					maxLength : 80,
					maxLengthText : i18n('i18n.developPlan.maxLength')+80,
					name : 'custName'
				},{
					fieldLabel : i18n('i18n.Maintainp.memberLevel'),
					colspan : 3,
					xtype : 'combo',
					name : 'memberLevel',
					store:Ext.create('Ext.data.Store',{
		                   fields : ['code','codeDesc']
		               }),
					queryMode:'local',
					displayField:'codeDesc',
					valueField:'code',  
					forceSelection :true,
					listeners:{
						change:DButil.comboSelsct
					}
				}, {
					fieldLabel : i18n('i18n.ScatterCustManagerView.custAttribute'),
					xtype : 'combo',
					colspan : 3,
					name : 'custProperty',
					store:getDataDictionaryByName(DataDictionary,'CUSTOMER_NATURE'),
					queryMode:'local',
					displayField:'codeDesc',
					valueField:'code',  
					forceSelection :true,
					listeners:{
						change:DButil.comboSelsct
					}
				}, {
					fieldLabel : i18n('i18n.developSchedule.linkManCode'),
					colspan : 3,
					maxLength : 40,
					maxLengthText : i18n('i18n.developPlan.maxLength')+40,
					name : 'linkManCode'
				}, {
					fieldLabel : i18n('i18n.PotentialCustManagerView.contactName'),
					colspan : 3,
					maxLength : 20,
					maxLengthText : i18n('i18n.developPlan.maxLength')+20,
					name : 'linkManName'
				}, {
					fieldLabel : i18n('i18n.PotentialCustManagerView.contactPhone'),
					colspan : 3,
					minLength:8,
					maxLength : 11,
					maxLengthText : i18n('i18n.developPlan.maxLength')+11,
					name : 'linkManMobile',
					regexText:i18n('i18n.ScatterCustManagerView.message_rightString'),
					//配合香港开点：手机号为8位数字或是首位为1的11位数字
					regex : /(^1\d{10}$)|(^\d{8}$)/
				}, {
					fieldLabel : i18n('i18n.PotentialCustManagerView.contactTel'),
					colspan : 3,
					name : 'linkManPhone',
					maxLength : 20,
					maxLengthText : i18n('i18n.developPlan.maxLength')+20
				},{
					fieldLabel : i18n('i18n.PotentialCustManagerView.searchStartTime'),
					colspan : 3,
					xtype : 'datefield',
					format : 'Y-m-d',
					editable:       false,
					name : 'beginTime',
					id:'beginTime',
					value : new Date(new Date().setMonth(new Date().getMonth() - 3))
				}, {
					xtype: 'datefield',
					//width : 120,
					colspan:3,
					labelSeparator:'',
					fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
					format : 'Y-m-d',
					name : 'overTime',
					id:'overTime',
					value : new Date(),
					editable:false
				},{//二级行业
					xtype:'customertrade',
					userType:'SEARCH',
					colspan:6,
					fieldLabel:i18n('i18n.PotentialCustManagerView.industry'),
					width:390,
					trade_labelWidth:55,
					trade_width:180,
					trade_name:'trade',
					trade_fieldname:i18n('i18n.PotentialCustManagerView.firstTrade'),
					second_trade_labelWidth:70,
					second_trade_width:195,
					trade_margin:'-10px 0 -5px 0',
		            second_trade_margin:'-10px 0 -5px 0',
					second_trade_name:'secondTrade',
					second_trade_fieldname:i18n('i18n.PotentialCustManagerView.secondTrade')
				},{
					fieldLabel : '查询时间',
					colspan : 3,
					xtype : 'datefield',
					format : 'Y-m-d',
					editable:       true,
					name : 'searchBeginTime',
					readOnly : true
				}, {
					xtype: 'datefield',
					//width : 120,
					colspan:3,
					labelSeparator:'',
					fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
					format : 'Y-m-d',
					name : 'searchOverTime',
					editable:true,
					readOnly : true
				},{
					xtype : 'fieldcontainer',
					colspan : 3,
					border : 0,
					layout : 'column',
					defaultType : 'numberfield',
					defaults : {
						hideTrigger: true,
						labelAlign: 'right',
						mouseWheelEnabled: false,
						minValue: 1
					},
					items : [ {
						fieldLabel : i18n('i18n.ScatterCustManagerView.beginShipWeight'),
						labelWidth : 65,
						maxLength :10,
						maxLengthText : i18n('i18n.developPlan.maxLength')+10,
						width : 120,
						name : 'beginShipWeight',
						readOnly : true
					},
					{xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
					{
						fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
						name : 'overShipWeight',
	                    hideLabel: true,
	                    maxLength :10,
						maxLengthText : i18n('i18n.developPlan.maxLength')+10,
						width : 55,
						labelWidth : 10,
						readOnly : true
					} ]
				}, {
					xtype : 'fieldcontainer',
					colspan : 3,
					border : 0,
					layout : 'column',
					defaultType : 'numberfield',
					defaults : {
						hideTrigger: true,
						mouseWheelEnabled: false,
						labelAlign: 'right',
						minValue: 1
					},
					items : [ {
						fieldLabel : i18n('i18n.ScatterCustManagerView.beginArrivalWeight'),
						name : 'beginArrivalWeight',
						maxLength :10,
						maxLengthText : i18n('i18n.developPlan.maxLength')+10,
						labelWidth : 65,
						width : 120,
						readOnly : true
					}, 
					{xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
					{
						fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
						name : 'overArrivalWeight',
	                    hideLabel: true,
	                    maxLength :10,
						maxLengthText : i18n('i18n.developPlan.maxLength')+10,
						width : 55,
						labelWidth : 10,
						readOnly : true
					} ]
				},
				{
					xtype : 'fieldcontainer',
					colspan : 3,
					border : 0,
					layout : 'column',
					defaultType : 'numberfield',
					defaults : {
						hideTrigger: true,
						labelAlign: 'right',
						mouseWheelEnabled: false,
						minValue: 1
					},
					items : [ {
						fieldLabel : i18n('i18n.ScatterCustManagerView.beginShipAmount'),
						name : 'beginShipAmount',
						labelWidth : 65,
						maxLength :10,
						maxLengthText : i18n('i18n.developPlan.maxLength')+10,
						width : 120,
						readOnly : true
					}, 
					{xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
					{
						fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
						name : 'overShipAmount',
	                    hideLabel: true,
						width : 55,
						maxLength :10,
						maxLengthText : i18n('i18n.developPlan.maxLength')+10,
						labelWidth : 10,
						readOnly : true
					} ]
				},
				{
					xtype : 'fieldcontainer',
					colspan : 3,
					border : 0,
					layout : 'column',
					defaultType : 'numberfield',
					defaults : {
						hideTrigger: true,
						mouseWheelEnabled: false,
		                labelAlign: 'right',
						minValue: 1
					},
					items : [ {
						fieldLabel : i18n('i18n.ScatterCustManagerView.beginArrivalAmount'),
						name : 'beginArrivalAmount',
						labelWidth : 65,
						maxLength :10,
						maxLengthText : i18n('i18n.developPlan.maxLength')+10,
						width : 120,
						readOnly : true
					},
					{
						xtype: 'displayfield',
						width : 10, 
						value: i18n('i18n.PotentialCustManagerView.searchEndTime')
					},
					{
						fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
						name : 'overArrivalAmount',
	                    hideLabel: true,
						width : 55,
						maxLength :10,
						maxLengthText : i18n('i18n.developPlan.maxLength')+10,
						labelWidth : 10,
						readOnly : true
					} ]
//				}
//				{
//					xtype:'displayfield',
//					colspan : 3
				},
				{
					xtype : 'fieldcontainer',
					colspan : 3,
					margin : '-5px 0 0 0',
					border : 0,
					layout : 'column',
					defaultType : 'numberfield',
					defaults : {
						hideTrigger: true,
						labelAlign: 'right',
						mouseWheelEnabled: false,
						minValue: 1
					},
					items : [ {
						fieldLabel : i18n('i18n.ScatterCustManagerView.beginShipVotes'),
						name : 'beginShipVotes',
						labelWidth : 65,
						maxLength :10,
						maxLengthText : i18n('i18n.developPlan.maxLength')+10,
						width : 120,
						regexText:i18n('i18n.ScatterCustManagerView.message_rightString'),
						regex : /^\d{0,10}$/,
						readOnly : true
					}, 
					{xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
					{
						fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
						name : 'overShipVotes',
	                    hideLabel: true,
						width : 55,
						maxLength :10,
						maxLengthText : i18n('i18n.developPlan.maxLength')+10,
						labelWidth : 10,
						regex : /^\d{0,10}$/,
						regexText:i18n('i18n.ScatterCustManagerView.message_rightString'),
						readOnly : true
					} ]
				},
				
				 {
					xtype : 'fieldcontainer',
					colspan : 3,
					border : 0,
					margin : '-5px 0 0 0',
					layout : 'column',
					defaultType : 'numberfield',
					defaults : {
						hideTrigger: true,
						mouseWheelEnabled: false,
						labelAlign: 'right',
						minValue: 1
					},
					items : [ {
						fieldLabel : i18n('i18n.ScatterCustManagerView.beginArrivalVotes'),
						name : 'beginArrivalVotes',
						labelWidth : 65,
						width : 120,
						maxLength :10,
						maxLengthText : i18n('i18n.developPlan.maxLength')+10,
						regexText:i18n('i18n.ScatterCustManagerView.message_rightString'),
						regex : /^\d{0,10}$/,
						readOnly : true
					}, 
					{xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
					{
						fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
						name : 'overArrivalVotes',
	                    hideLabel: true,
						width : 55,
						labelWidth : 10,
						maxLength :10,
						maxLengthText : i18n('i18n.developPlan.maxLength')+10,
						regexText:i18n('i18n.ScatterCustManagerView.message_rightString'),
						regex : /^\d{0,10}$/,
						readOnly : true
					} ]
				} ]
			}];
		}
	});
	
	
	/**
	 * 查询按钮
	 */
	Ext.define('RightSearchButtonPanel',{
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
				items:[{
					text:i18n('i18n.developSchedule.returnVisit'),
					xtype:'button',
					handler:function(){
						var grid = Ext.getCmp('searchResultGridId');
						//获取store中数据
						var	store = grid.getStore();
						var	selection=grid.getSelectionModel().getSelection();
						if (selection.length == 0) {
							MessageUtil.showMessage(i18n('i18n.developSchedule.choice'));
							return false;
						}
						if(selection.length>1){
							MessageUtil.showMessage(i18n('i18n.developPlan.chooseOne'));
							return false;
						}
						var scheduleType=MAINTAIN_TYPE;
						var successFn = function(json){
						    var initData=new InitDataModel(json.returnVisit);
					    	var customerInfoFormPanel = Ext.getCmp("customerInfoFormPanel").getForm();
					    	customerInfoFormPanel.loadRecord(initData);
						 	var ScheduleMakeForm =  Ext.getCmp("scheduleMakeForm").getForm();
						 	ScheduleMakeForm.loadRecord(initData);
					    	Ext.getCmp('scheType').setValue(scheduleType);
							win.show();
							document.getElementsByTagName("html")[0].style.overflowY="auto";
							document.getElementsByTagName("html")[0].style.overflowX="auto";
							viewport.doLayout();
					    };
						var failureFn = function(json){
							MessageUtil.showErrorMes(json.message);
						};
//						alert(selection[0].get("contactId"));
						DevelopScheduleData.prototype.setRetureVisitByCust({
							'returnVisit.scheduleId':selection[0].get("id"),
							'returnVisit.linkManId':selection[0].get("contactId"),
							'returnVisit.memberId':selection[0].get("custId"),
							'returnVisit.scheType':scheduleType
						}, successFn, failureFn);
						//清空走货潜力表格
						Ext.getCmp('sendGoodsPontentialGrid').store.removeAll();
				    	//清空客户意见表格
				    	Ext.getCmp('customerOpinionGrid').store.removeAll();
				    	//清空跟踪时间和跟踪方式
				    	Ext.getCmp('schedule').setValue(null).disable();
				    	Ext.getCmp("ifparent").setValue('0');
					}
				},{//营销记录查询按钮
					xtype:'button',
					text:i18n('i18n.marketRecord.marketRecord'),
					handler:function(){
						var grid = Ext.getCmp('searchResultGridId');
						//获取store中数据
						var	store = grid.getStore();
						var	selection=grid.getSelectionModel().getSelection();
						if (selection.length == 0) {
							MessageUtil.showMessage(i18n('i18n.developSchedule.choice'));
							return false;
						}
						if(selection.length>1){
							MessageUtil.showMessage(i18n('i18n.developPlan.chooseOne'));
							return false;
						}
						////根据客户id和客户类型查询客户所有营销历史记录
						if(selection.length==1&&!Ext.isEmpty(selection[0].get("custId"))){
							custIDForMarketRecord = selection[0].get("custId");
							custTypeForMarketRecord = 'MEMBER';
						}
						else {
							MessageUtil.showMessage(i18n('i18n.customerCallIn.nullCustId'));
							return false;
						}
						Ext.getCmp('sendGoodsPontentialResultGridId').store.loadPage(1);
	  					Ext.getCmp('customerDemandResultGridId').store.loadPage(1);
	  					Ext.getCmp('questionnaireResultGridForMarketRecordId').store.loadPage(1);
						//点击按钮，弹出营销历史记录查询结果窗口
	  					Ext.getCmp('marketHistoryResultPanelId').setActiveTab(0);
	  					marketHistoryRecordWindow.show();
					}},{//查看客户详情按钮
						xtype:'button',
						text:i18n('i18n.ClientBaseManegerView.detailView'),
						handler:function(){
							var grid = Ext.getCmp('searchResultGridId');
							//获取store中数据
							var	store = grid.getStore();
							var	selection=grid.getSelectionModel().getSelection();
							if (selection.length == 0) {
								MessageUtil.showMessage(i18n('i18n.scheduleManagement.selectOneRecord'));
								return false;
							}
							if(selection.length>1){
								MessageUtil.showMessage(i18n('i18n.scheduleManagement.selectOneRecord'));
								return false;
							}
							var custType = selection[0].get("custType");
							if(custType == 'RC_CUSTOMER'){//如果客户是固定客户，调用简版360
								CustviewUtil.openSimpleCustview(selection[0].get('custNumber'));
							}else{
								CustviewUtil.openMemberWindow(selection[0].get('custId'));
							}
						}
					}]
			},{
				xtype:'middlebuttonpanel' 
			},{
				xtype:'rightbuttonpanel',  
				items : [{
					xtype : 'button',
					text : i18n('i18n.PotentialCustManagerView.search'),
					handler : function(){
						//判断界面校验是否通过
						if(!Ext.getCmp('SearchConditionFormId').getForm().isValid()){
							MessageUtil.showMessage(i18n('i18n.DevelopManageView.pleaseCondition'));
							return false;
						}
						var startDate = Ext.getCmp("SearchConditionFormId").getForm().findField("beginTime").getValue();
						var endDate = Ext.getCmp("SearchConditionFormId").getForm().findField("overTime").getValue();
						if(!Ext.isEmpty(startDate)){
							if(!Ext.isEmpty(endDate)){
								var days = DButil.compareTwoDate(startDate,endDate);
								if(days<0){
									MessageUtil.showInfoMes(i18n('i18n.developPlan.startDateBGEndDate'));
									return;
								}else if(days>365){
									MessageUtil.showInfoMes(i18n('i18n.developPlan.queryDateLimityears'));
									return;
								}
								if(!searchTimeIsValid()){
									return;
								}
								//load数据
								Ext.getCmp('searchResultGridId').store.loadPage(1);
							}else{
								MessageUtil.showInfoMes("创建时间结束日期不能为空~");
								return;
							}
						}else{
							MessageUtil.showInfoMes("创建时间起始日期不能为空~");
							return;
						}
						
					}
				},{
                    xtype : 'button',
                    text : i18n('i18n.developPlan.reset'),
                    handler : function(){
                        Ext.getCmp("SearchConditionFormId").getForm().reset();
                        Ext.getCmp("SearchConditionFormId").down('form').getForm().findField('secondTrade').store.removeAll();
                        //设置默认部门
                        var deptModel=new DeptModel();
                        deptModel.set("id",User.deptId);
                        deptModel.set("deptName",User.deptName);
                        Ext.getCmp("SearchConditionFormId").getForm().findField("deptName").store.removeAll();
                        Ext.getCmp("SearchConditionFormId").getForm().findField("deptName").store.add(deptModel);
                        Ext.getCmp("SearchConditionFormId").getForm().findField("deptName").setValue(User.deptId);
                    }
                }]
			}];
		}
	});
	/**.
	 * <p>
	 * 开发计划新增、修改主Panel
	 * <p>
	 * @author 张登
	 * @时间 2012-3-26
	 */
	Ext.define('CustomerDevelopPlanePanel',{
		extend:'BasicPanel',
		searchCondition:null, //查询条件From
		searchLeftResult:null, //查询客户列表（左边Grid）
		items:null,
		layout:'border',
		initComponent:function(){
			var me = this;
			
			//查询条件From
			me.searchCondition = Ext.create('SearchConditionForm',{id:'SearchConditionFormId'});

			//设置起始结束时间
			var date = new Date();
//			me.searchCondition.getForm().findField("overTime").setValue(date);
//			date.setDate(new Date().getDate()-30);
//			me.searchCondition.getForm().findField("beginTime").setValue(date);
			
			//查询客户列表store
			var store=Ext.create('ScheDuleStore',{id:'ScheDuleStoreId'});
			store.on('beforeload',function(store,operation,e){
				var searchScatterForm = Ext.getCmp("SearchConditionFormId");
				var customerType = searchScatterForm.getForm().findField('custType').getValue();
				var scheduleType;
				if(customerType == 'RC_CUSTOMER'){//固定客户
					scheduleType = 'mat';
				}else{
					scheduleType = 'dev';
				}
				//获取二级行业
				var firstTrade = searchScatterForm.down('form').getForm().findField('trade').getValue();
				var secondTrade = searchScatterForm.down('form').getForm().findField('secondTrade').getValue();
				//设置请求参数
  				var searchParams = { 
  					//客户名称
  					'customerVo.custName':searchScatterForm.getForm().findField('custName').getValue(),
  					//部门
  					'customerVo.deptId':searchScatterForm.getForm().findField('deptName').getValue(),
  					//联系人姓名
  					'customerVo.linkManName':searchScatterForm.getForm().findField('linkManName').getValue(),
  					//联系人手机
  					'customerVo.linkManMobile':searchScatterForm.getForm().findField('linkManMobile').getValue(),
  					//联系人电话
  					'customerVo.linkManPhone':searchScatterForm.getForm().findField('linkManPhone').getValue(),
  					//客户属性  出发客户 到达客户
  					'customerVo.custProperty':searchScatterForm.getForm().findField('custProperty').getValue(),
  					//行业
  					'customerVo.trade':firstTrade,
  					//二级行业
  					'customerVo.secondTrade':secondTrade,
  					'customerVo.scheduleType':scheduleType,
  					//客户编码
  					'customerVo.memberNo':searchScatterForm.getForm().findField('memberNo').getValue(),
  					//联系人编码
  					'customerVo.linkManCode':searchScatterForm.getForm().findField('linkManCode').getValue(),
  					//会员等级
  					'customerVo.memberLevel':searchScatterForm.getForm().findField('memberLevel').getValue(),
  					//创建时间
  					'customerVo.beginTime':searchScatterForm.getForm().findField('beginTime').getValue(),
  					'customerVo.overTime':searchScatterForm.getForm().findField('overTime').getValue(),
  					//发货量
  					'customerVo.beginShipWeight':searchScatterForm.getForm().findField('beginShipWeight').getValue(),
  					'customerVo.overShipWeight':searchScatterForm.getForm().findField('overShipWeight').getValue(),
  					//发货票数
  					'customerVo.beginShipVotes':searchScatterForm.getForm().findField('beginShipVotes').getValue(),
  					'customerVo.overShipVotes':searchScatterForm.getForm().findField('overShipVotes').getValue(),
  					//发货金额
  					'customerVo.beginShipAmount':searchScatterForm.getForm().findField('beginShipAmount').getValue(),
  					'customerVo.overShipAmount':searchScatterForm.getForm().findField('overShipAmount').getValue(),
  					//到达货量
  					'customerVo.beginArrivalWeight':searchScatterForm.getForm().findField('beginArrivalWeight').getValue(),
  					'customerVo.overArrivalWeight':searchScatterForm.getForm().findField('overArrivalWeight').getValue(),
  					//到达票数
  					'customerVo.beginArrivalVotes':searchScatterForm.getForm().findField('beginArrivalVotes').getValue(),
  					'customerVo.overArrivalVotes':searchScatterForm.getForm().findField('overArrivalVotes').getValue(),
  					//到达金额
  					'customerVo.beginArrivalAmount':searchScatterForm.getForm().findField('beginArrivalAmount').getValue(),
  					'customerVo.overArrivalAmount':searchScatterForm.getForm().findField('overArrivalAmount').getValue(),
  					//业务类别 零担快递
  					'customerVo.businessType':searchScatterForm.getForm().findField('custCategory').getValue(),
  					//客户类型 潜散客
  					'customerVo.custType':searchScatterForm.getForm().findField('custType').getValue(),
  					//合作意向
  					'customerVo.cooperationIntention':searchScatterForm.getForm().findField('cooperationIntention').getValue(),
  					//客户来源
  					'customerVo.custbase':searchScatterForm.getForm().findField('custSorce').getValue(),
  					//货量潜力
  					'customerVo.volumePotential':searchScatterForm.getForm().findField('cargoPotential').getValue(),
  					//开发阶段
  					'customerVo.developState':searchScatterForm.getForm().findField('developStage').getValue(),
  					//继续营销
  					'customerVo.continueMark':searchScatterForm.getForm().findField('continueMarket').getValue(),
  					//查询时间
  					'customerVo.queryBeginTime':searchScatterForm.getForm().findField('searchBeginTime').getValue(),
  					'customerVo.queryOverTime':searchScatterForm.getForm().findField('searchOverTime').getValue()
  				};
				Ext.apply(operation,{
					params : searchParams
				});
			});
			store.on('load',function(store,records){
  				var girdcount=0;
		        store.each(function(record){
		 			var status = record.get("businessOpportunityStatus");//商机状态
		 			var isRepeat = record.get("repeatCust");//疑似重复
		 			if(isRepeat == '1' || status == 'ONGOING' || status == 'EXTENDED' || status == 'DORMANT'){//有未关闭的商机状态，标红
		 				var cells =  Ext.getCmp('searchResultGridId').getView().getNodes()[girdcount].children;
						for(var i= 0;i<cells.length;i++){
							cells[i].style.backgroundColor='#FF9797';
						};
		 			}
		 			girdcount=girdcount+1;
		        })});
			var selModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE'});
			var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
				 clicksToMoveEditor: 2,
				 clicksToEdit:2,
     		 	 autoCancel: false
			});
			
			rowEditing.on('edit',function(th,e,eop){
				var record=th.record;
				//盛诗庆修改，对疑似重复以及存在商机的客户不允许指定日程
				var isRepeat = record.get('repeatCust');//疑似重复
		    	var status = record.get('businessOpportunityStatus');//商机状态
					if(status == 'ONGOING' || status == 'EXTENDED' || status == 'DORMANT'){//有未关闭的商机状态，不允许指定新计划
						 MessageUtil.showErrorMes('['+record.get("contactName")+']'+'存在未关闭的商机,不能制定日程!');
						 return false;
		 			}
	  				if(isRepeat == '1'){//疑似重复客户
						 MessageUtil.showErrorMes('['+record.get("contactName")+']'+'是'+'['+record.get("repeatCustDeptName")+']'+'疑似重复客户,不能制定日程!');
						 return false;
		 			}
				var id = record.get('scheduleId');
				var scheduleDate = record.data.scheduleDate;
				var planId = record.data.planId;
				var custId = record.data.custId;
				var contactId=record.data.contactId;
				//保存成功回调函数
				var successFn = function(json){
					MessageUtil.showInfoMes("保存成功");
					//Ext.getCmp("searchResultGridId").store.loadPage(1);
					record.commit();
				};
				
				//保存失败回调函数
				var failureFn = function(json){
					MessageUtil.showErrorMes(json.message);
				};
				var conditionForm = Ext.getCmp("SearchConditionFormId").getForm();
				var customerType = conditionForm.findField('custType').getValue();
				var scheduleType;
				if(customerType == 'RC_CUSTOMER'){//固定客户
					scheduleType = 'mat';
				}else{
					scheduleType = 'dev';
				}
				var params={
						'schedule.id':id,
						'schedule.time':DButil.changeLongToDate(scheduleDate),
						'schedule.planId':planId,
						'schedule.contactId':contactId,
						'schedule.custId':custId,
						'schedule.type':scheduleType//维护
					};
				developScheduleData.saveSchedule(params, successFn, failureFn);
			});
			
			//查询结果Grid
			me.searchLeftResult =  Ext.create('SearchGridPanel',{
				id:'searchResultGridId',
				cls:'market',
				store:store,columns:me.getColumns(),
				selModel:selModel,
				'plugins':rowEditing,
  				dockedItems:[{
  					xtype:'pagingtoolbar',
  					store:store,
  					dock:'bottom',
  					displayInfo : true,
  					autoScroll : true,
  					items:[{
  						text: '每页',
  						xtype: 'tbtext'
  					},Ext.create('Ext.form.ComboBox', {
  		               width:          window.screen.availWidth*0.0391,
  		               value:          '20',
  		               triggerAction:  'all',
  		               forceSelection: true,
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
  					            	var pageSize = store.pageSize;
  					            	var newPageSize = parseInt(_field.value);
  					            	if(pageSize!=newPageSize){
  					            		store.pageSize = newPageSize;
  					            		this.up('pagingtoolbar').moveFirst();
  					            	}
  					            }
  					        }
  		               }
  		           }),{
  						text: '条',
  						xtype: 'tbtext'
  		           }]
  				}]
			});
			
			//设置items
			me.items = me.getItems();
			this.callParent();
			
			//设置默认部门
			var deptModel=new DeptModel();
			deptModel.set("id",User.deptId);
			deptModel.set("deptName",User.deptName);
			Ext.getCmp("SearchConditionFormId").getForm().findField("deptName").store.add(deptModel);
			Ext.getCmp("SearchConditionFormId").getForm().findField("deptName").setValue(User.deptId);
		},
		getItems:function(){//整体布局
			var me = this;
			return [{
				region:'north',
				xtype:'basicpanel',
				height:250,
				layout:'border',
				items:[{
					region:'center',
					xtype:'basicpanel',
					layout:'fit',
					items:[me.searchCondition]
				},
				Ext.create('RightSearchButtonPanel')]//border布局下面查询按钮
			},{
				region:'center',
				xtype:'basicpanel',
				layout:'border',
				items:[{
					region:'center',
					xtype:'basicpanel',
					layout:'border',
					items:[{
						region:'center',
						xtype:'basicpanel',
						layout:'fit',
						flex:1,
						items:[me.searchLeftResult]//查询按钮
					}]
				}]
			}];
		},
		getColumns:function(){//查询结果列
			return [{
				xtype:'rownumberer',
				width:40,
				align:'center',
				header:i18n('i18n.Cycle.rownum')
			},{
				header : i18n('i18n.developSchedule.custName'),
				dataIndex : 'custName',
				width:100
			},{
				header : i18n('i18n.Maintainp.memberLevel'),
				dataIndex : 'memberLevel',
  				renderer:function(value){
  					return rendererDictionary(value,DataDictionary.MEMBER_GRADE);
  				},
  				width:65
			},{
				header : i18n('i18n.developSchedule.linkManName'),
				dataIndex : 'contactName',
				width:100
			},{
				header : i18n('i18n.developSchedule.linkManMobeilPhone'),
				dataIndex : 'contactMobile'
			},{
				header : i18n('i18n.developSchedule.linkManTelePhone'),
				dataIndex : 'contactTel'
			},{
				header : i18n('i18n.developSchedule.scheduleDate'),
				dataIndex : 'scheduleDate',
				renderer : DButil.renderDate,
				editor: {
	                xtype: 'datefield',
	                minValue :new Date(),
	                editable: false,
	                format: 'Y-m-d'
           	   },
           	   width:95
			},{
				header : i18n('i18n.developSchedule.unfinishedPlanNum'),
				dataIndex : 'unfinishedPlanNum'
			},/*{
				header : i18n('i18n.developSchedule.unfinishedPlanName'),
				dataIndex : 'unfinishedPlanName'
			},*/{
				header : i18n('i18n.developSchedule.visitNum'),
				dataIndex : 'visitNum',
				width:65
			},{
				header : i18n('i18n.developSchedule.lastVisitDate'),
				dataIndex : 'lastVisitDate',
				renderer : DButil.renderDate
			}]
		}
	});
	/**
	 * 回访
	 */
	Ext.define('CreateDevelopPopWindow',{
		extend:'PopWindow',
		alias : 'widget.basicwindow',
		width:820,
		height:750,
		modal:true,
		layout:'fit',
		closeAction:'hide',
		items:[Ext.create('CustomerReturnVisitPanel',{'id':'customerReturnVisitPanel'})],
		listeners:{
			hide:function(){
				document.body.scrollLeft=0;
				document.body.scrollTop=0;
				document.getElementsByTagName("html")[0].style.overflowY="hidden";
				document.getElementsByTagName("html")[0].style.overflowX="hidden";
				viewport.doLayout();
			}
		}
	});
	var win=Ext.getCmp("CreateDevelopPopWindowId");//获取win
	if(!win){
		win=Ext.create('CreateDevelopPopWindow',{id:'CreateDevelopPopWindowId'});
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
	
	//整体布局
	var viewport=Ext.create('Ext.Viewport', {
		layout : {
			type : 'fit'
		},
		items : [ Ext.create('CustomerDevelopPlanePanel')]
	});
});
/**
 * @description 验证查询界面货量查询条件是否可用
 * @author 盛诗庆
 * @return true 验证通过 false 验证不通过
 */
function searchTimeIsValid(){
	//发货量
	var beginShipWeight = Ext.getCmp("SearchConditionFormId").getForm().findField("beginShipWeight").getValue();
	var overShipWeight = Ext.getCmp("SearchConditionFormId").getForm().findField("overShipWeight").getValue();
	//到达货量
	var beginArrivalWeight = Ext.getCmp("SearchConditionFormId").getForm().findField("beginArrivalWeight").getValue();
	var overArrivalWeight = Ext.getCmp("SearchConditionFormId").getForm().findField("overArrivalWeight").getValue();
	//发货金额
	var beginShipAmount = Ext.getCmp("SearchConditionFormId").getForm().findField("beginShipAmount").getValue();
	var overShipAmount = Ext.getCmp("SearchConditionFormId").getForm().findField("overShipAmount").getValue();
	//到达金额
	var beginArrivalAmount = Ext.getCmp("SearchConditionFormId").getForm().findField("beginArrivalAmount").getValue();
	var overArrivalAmount = Ext.getCmp("SearchConditionFormId").getForm().findField("overArrivalAmount").getValue();
	//发货票数
	var beginShipVotes = Ext.getCmp("SearchConditionFormId").getForm().findField("beginShipVotes").getValue();
	var overShipVotes = Ext.getCmp("SearchConditionFormId").getForm().findField("overShipVotes").getValue();
	//到达票数
	var beginArrivalVotes = Ext.getCmp("SearchConditionFormId").getForm().findField("beginArrivalVotes").getValue();
	var overArrivalVotes = Ext.getCmp("SearchConditionFormId").getForm().findField("overArrivalVotes").getValue();
	//获取查询时间范围
	var searchBeginTime = Ext.getCmp("SearchConditionFormId").getForm().findField("searchBeginTime").getValue();
	var searchOverTime = Ext.getCmp("SearchConditionFormId").getForm().findField("searchOverTime").getValue();
	//获取客户创建时间
	var startDate = Ext.getCmp("SearchConditionFormId").getForm().findField("beginTime").getValue();
	var endDate = Ext.getCmp("SearchConditionFormId").getForm().findField("overTime").getValue();
	if(overArrivalVotes !=null || beginArrivalVotes !=null 
			||overShipVotes !=null ||beginShipVotes !=null 
			||overArrivalAmount !=null ||beginArrivalAmount !=null 
			||overShipAmount !=null ||beginShipAmount !=null 
			||overArrivalWeight !=null ||beginArrivalWeight !=null 
			||overShipWeight !=null ||beginShipWeight !=null )//如果以上任意一个不为空，验证查询时间条件
	{
		if(searchBeginTime == null || searchOverTime == null){
			MessageUtil.showErrorMes('请选择查询时间');
			return false;
		}else
		{
			var searchTimeRegion = DButil.compareTwoDate(searchBeginTime,searchOverTime);//查询时间范围
			if(searchTimeRegion < 0){
				MessageUtil.showErrorMes('查询起始时间小于结束时间');
				return false;
			}else if(searchTimeRegion > 365){
				MessageUtil.showErrorMes('查询时间范围不能超过一年');
				return false;
			}
			var days = DButil.compareTwoDate(searchBeginTime,endDate);
			if(days < 0){//查询时间在创建时间之前
				MessageUtil.showErrorMes('您选择的查询时间已超过创建时间，请重新调整创建时间');
				return false;
			}else{
				return true;
			}
		}
	}else{
		return true;
	}
}
function controlForm(form,flag)
{
	if(flag){
		form.findField('overArrivalVotes').reset();
		form.findField('beginArrivalVotes').reset();
		form.findField('overShipVotes').reset();
		form.findField('beginShipVotes').reset();
		form.findField('overArrivalAmount').reset();
		form.findField('beginArrivalAmount').reset();
		form.findField('overShipAmount').reset();
		form.findField('beginShipAmount').reset();
		form.findField('overArrivalWeight').reset();
		form.findField('beginArrivalWeight').reset();
		form.findField('overShipWeight').reset();
		form.findField('beginShipWeight').reset();
		form.findField('searchOverTime').reset();
		form.findField('searchBeginTime').reset();
	}
	form.findField('overArrivalVotes').setReadOnly(flag);
	form.findField('beginArrivalVotes').setReadOnly(flag);
	form.findField('overShipVotes').setReadOnly(flag);
	form.findField('beginShipVotes').setReadOnly(flag);
	form.findField('overArrivalAmount').setReadOnly(flag);
	form.findField('beginArrivalAmount').setReadOnly(flag);
	form.findField('overShipAmount').setReadOnly(flag);
	form.findField('beginShipAmount').setReadOnly(flag);
	form.findField('overArrivalWeight').setReadOnly(flag);
	form.findField('beginArrivalWeight').setReadOnly(flag);
	form.findField('overShipWeight').setReadOnly(flag);
	form.findField('beginShipWeight').setReadOnly(flag);
	form.findField('searchOverTime').setReadOnly(flag);
	form.findField('searchBeginTime').setReadOnly(flag);
}