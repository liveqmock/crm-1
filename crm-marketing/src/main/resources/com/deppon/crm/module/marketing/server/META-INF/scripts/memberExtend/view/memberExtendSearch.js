var memberExtendData = new MemberExtendData();
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
  		//继续营销数据字典 盛诗庆
  		'CONTINUE_MARKET',
  		//客户类型 标识潜散客，固定客户
  		'CUST_TYPE'
  		
  	];
	//初始化业务参数
	initDataDictionary(keys);
	initDeptAndUserInfo();
	/**
	 * <p>
	 * 开发计划新增修改查询条件Form
	 * </p>
	 * @author  张斌
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
                    id:'deptNamesId',
                    store:Ext.create('DeptStore',{id:'queryDeptStoreId',
                        listeners:{
                            beforeload:function(store, operation, eOpts){
                                var dept=Ext.getCmp("deptNamesId").getValue();
                                if(!Ext.getCmp("deptNamesId").isValid()&&!Ext.isEmpty(dept)){
                                    return false;
                                }
                                Ext.apply(operation,{
                                    params : {
                                        'deptName':Ext.getCmp("SearchConditionFormId").getForm().findField("deptName").getRawValue().trim()
                                        }
                                    }
                                );  
                            }
                        }
                    }),
                    colspan:3,
                    allowBlank :false,
                    triggerAction : 'all',
                    maxLength : 50,
                    displayField:'deptName',
                    valueField:'id',
                    forceSelection :true,
                    hideTrigger:false,
                    emptyText:i18n('i18n.visualMarket.inputKeyvalueOfDept'),
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
                    fieldLabel : i18n('i18n.developSchedule.linkManCode'),
                    colspan : 3,
                    maxLength : 40,
                    maxLengthText : i18n('i18n.developPlan.maxLength')+40,
                    name : 'linkManCode'
                },{//联系人名称
                    fieldLabel : i18n('i18n.developPhase.contactName'),
                    colspan : 3,
                    maxLength : 80,
                    maxLengthText : i18n('i18n.developPlan.maxLength')+80,
                    name : 'linkManName'
                },{//手机号
                    fieldLabel : i18n('i18n.developPhase.mobilPhone'),
                    colspan : 3,
                    minLength:8,
                    maxLength : 11,
                    maxLengthText : i18n('i18n.developPlan.maxLength')+11,
                    name : 'linkManMobile',
                    regexText:i18n('i18n.ScatterCustManagerView.message_rightString'),
                    //配合香港开点：手机号为8位数字或是首位为1的11位数字
                    regex : /(^1\d{10}$)|(^\d{8}$)/
                },{//联系人电话
                    fieldLabel : i18n('i18n.developPhase.telephone'),
                    colspan : 3,
                    name : 'linkManPhone',
                    maxLength : 20,
                    maxLengthText : i18n('i18n.developPlan.maxLength')+20,
                    regex:/^\d{3}[\d\*-/]{4,17}$/,
                    regexText:i18n('i18n.developPhase.right')
                },{//合并客户类型 标识潜散客，固定客户 盛诗庆
                    fieldLabel : i18n('i18n.PotentialCustManagerView.custType'),
                    xtype : 'combo',
                    colspan : 3,
                    name : 'custType',
                    store:getDataDictionaryByName(DataDictionary,'CUST_TYPE'),
                    queryMode:'local',
                    displayField:'codeDesc',
                    valueField:'code',  
                    forceSelection :true,
                    listeners:{
                        change:DButil.comboSelsct
                    }
                },{//合并客户来源查询条件 盛诗庆
                    fieldLabel : i18n('i18n.PotentialCustManagerView.custSource'),
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
                },{
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
                },{//合并合作意向 针对潜散客客户 盛诗庆
                    fieldLabel : i18n('i18n.developSchedule.cooperatePurpose'),
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
                },/*{//新增商机状态 标识客户所处商机计划的状态 盛诗庆
                    fieldLabel : i18n('i18n.PotentialCustManagerView.bizStatus'),
                    xtype : 'combo',
                    colspan : 3,
                    name : 'businessOpportunityStatus',
                    store:getDataDictionaryByName(DataDictionary,'BUSINESS_OPPORTUNITY_STATUS'),
                    queryMode:'local',
                    displayField:'codeDesc',
                    valueField:'code',  
                    forceSelection :true,
                    listeners:{
                        change:DButil.comboSelsct
                    }
                },*/{//新增业务类别 标识客户是零担，快递，还是零担加快递 盛诗庆
                    fieldLabel : i18n('i18n.PotentialCustManagerView.businessType'),
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
                },{//合并货量潜力 盛诗庆
                    fieldLabel : i18n('i18n.PotentialCustManagerView.goodsPotential'),
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
                    fieldLabel : i18n('i18n.PotentialCustManagerView.bizStep'),
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
                },{//修改开发维护计划商机状态为开发阶段 盛诗庆
                    fieldLabel : i18n('i18n.developPlan.continueMark'),
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
                    fieldLabel : i18n('i18n.Maintainp.memberLevel'),
                    colspan : 3,
                    xtype : 'combo',
                    name : 'memberLevel',
                    store:getDataDictionaryByName(DataDictionary,'MEMBER_GRADE'),
                    queryMode:'local',
                    displayField:'codeDesc',
                    valueField:'code',  
                    forceSelection :true,
                    listeners:{
                        change:DButil.comboSelsct
                    }
                }, {
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
                },/*{
                    xtype : 'displayfield',
                    colspan : 6
                },*/{//二级行业
                    xtype:'customertrade',
                    userType:'SEARCH',
                    colspan:6,
                    fieldLabel:i18n('i18n.PotentialCustManagerView.industry'),
                    width:390,
                    trade_labelWidth:65,
                    trade_width:190,
                    trade_name:'trade',
                    trade_fieldname:i18n('i18n.PotentialCustManagerView.firstTrade'),
                    second_trade_labelWidth:70,
                    second_trade_width:190,
                    second_trade_name:'secondTrade',
                    second_trade_fieldname:i18n('i18n.PotentialCustManagerView.secondTrade')
                }]
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
		updateWin:null,//修改win
		showWin:null,//修改win
	 	/**
		 * .
		 * <p>
		 * 获取修改win </br>
		 * </p>
		 * @author 张斌
		 * @时间 2014-03-14
		 */
	 	getUpdateWin:function(){
	 		var me  = this;
	 		if(Ext.isEmpty(me.updateWin)){
	 			me.updateWin = new CRM.BO.DevelopmentStageUpdate.Window({'parent':me});
	 		}
	 		return me.updateWin;
	 	},
	 	/**
		 * .
		 * <p>
		 * 获取修改win </br>
		 * </p>
		 * @author 张斌
		 * @时间 2014-03-14
		 */
	 	getShowWin:function(){
	 		var me  = this;
	 		if(Ext.isEmpty(me.showWin)){
	 			me.showWin = new CRM.BO.DevelopmentStageView.Window({'parent':me});
	 		}
	 		return me.showWin;
	 	},
	 	searchCust:function(){
	 		//判断界面校验是否通过
			if(!Ext.getCmp('SearchConditionFormId').getForm().isValid()){
				MessageUtil.showMessage(i18n('i18n.DevelopManageView.pleaseCondition'));
				return false;
			}
			eachTextFieldTrim(Ext.getCmp('SearchConditionFormId').getForm());
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
					//load数据
					Ext.getCmp('searchResultGridId').store.loadPage(1);
				}else{
					MessageUtil.showInfoMes(i18n('i18n.businessOpportunity.createDateEndNotNull~'));
					return;
				}
			}else{
				MessageUtil.showInfoMes(i18n('i18n.businessOpportunity.createDateStartNotNull'));
				return;
			}
	 	},
		getItems:function(){
			var me = this;
			return [{
				xtype:'leftbuttonpanel', 
				items:[{//查看详情
					xtype:'button',
					text:i18n('i18n.PotentialCustManagerView.viewDetails'),
					handler:function(){
						var selections =Ext.getCmp('searchResultGridId').getSelectionModel( ).getSelection( );//获取选中数据
	 					var showWin =me.getShowWin();//获取弹出窗口;
	 					if(selections.length!=1){
	 						MessageUtil.showMessage(i18n('i18n.businessOpportunity.selectOneUpdate'));//选择一条进行修改
	 						return;
	 					};
	 					var successFn = function(json){
	 						showWin.member = json.member;
	 						showWin.show();
	 					};
	 					var failureFn = function(json){
	 					    MessageUtil.showErrorMes(json.message);
	 					};
	 					var param = {'custId':selections[0].get('custId')};//构建请求数据
	 					memberExtendData.searchMemberExtend(param,successFn,failureFn);
					}
				},{//修改
					xtype:'button',
					text:i18n('i18n.questionManage.searchButton.modifyQuestion'),
					handler:function(){
						var selections =Ext.getCmp('searchResultGridId').getSelectionModel( ).getSelection( );//获取选中数据
	 					var updateWin =me.getUpdateWin();//获取弹出窗口;
	 					if(selections.length!=1){
	 						MessageUtil.showMessage(i18n('i18n.businessOpportunity.selectOneUpdate'));//选择一条进行修改
	 						return;
	 					};
	 					var successFn = function(json){
	 						updateWin.member = json.member;
	 						updateWin.show();
	 					};
	 					var failureFn = function(json){
	 					    MessageUtil.showErrorMes(json.message);
	 					};
	 					var param = {'custId':selections[0].get('custId')};//构建请求数据
	 					memberExtendData.searchMemberExtend(param,successFn,failureFn);
					}
				},{
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
				    	Ext.getCmp('schedule').setValue(null).enable();
				    	Ext.getCmp("ifparent").setValue('1');
					}
				}]
			},{
				xtype:'middlebuttonpanel' 
			},{
				xtype:'rightbuttonpanel',  
				items : [{
					xtype : 'button',
					text : i18n('i18n.PotentialCustManagerView.search'),
					scope:me,
					handler : function(){
						me.searchCust();
					}
				},{
                    xtype : 'button',
                    text : i18n('i18n.developPlan.reset'),
                    handler : function(){
                        Ext.getCmp("SearchConditionFormId").getForm().reset();
                        Ext.getCmp("SearchConditionFormId").getForm().findField('secondTrade').store.removeAll();
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
	 * @author 张斌
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
			
			//查询客户列表store
			var store=Ext.create('MemberExtendScheDuleStore',{id:'ScheDuleStoreId'});
			store.on('beforeload',function(store,operation,e){
				var searchScatterForm = Ext.getCmp("SearchConditionFormId");
				//获取二级行业
				var firstTrade = searchScatterForm.getForm().findField('trade').getValue();
				var secondTrade = searchScatterForm.getForm().findField('secondTrade').getValue();
				var memberNo = searchScatterForm.getForm().findField('memberNo').getValue();//客户编码
				var linkManCode = searchScatterForm.getForm().findField('linkManCode').getValue();//联系人编码
				var linkManMobile = searchScatterForm.getForm().findField('linkManMobile').getValue();//联系人手机
				var linkManPhone = searchScatterForm.getForm().findField('linkManPhone').getValue();//联系人电话
				var linkManName = searchScatterForm.getForm().findField('linkManName').getValue();//联系人姓名
				var searchParams = null;
				if(!Ext.isEmpty(memberNo)){
					var searchParams = { 
							//部门
		  					'customerVo.deptId':searchScatterForm.getForm().findField('deptName').getValue(),
							//客户编码
		  					'customerVo.memberNo':memberNo
					};
				}else if(!Ext.isEmpty(linkManCode)){
					var searchParams = { 
							//部门
		  					'customerVo.deptId':searchScatterForm.getForm().findField('deptName').getValue(),
							//联系人编码
		  					'customerVo.linkManCode':linkManCode
					};
				}else if(!Ext.isEmpty(linkManMobile)){
					var searchParams = { 
							//部门
		  					'customerVo.deptId':searchScatterForm.getForm().findField('deptName').getValue(),
							//联系人编码
		  					'customerVo.linkManMobile':linkManMobile
					};
				}else if(!Ext.isEmpty(linkManPhone)&&!Ext.isEmpty(linkManName)){
					var searchParams = { 
							//部门
		  					'customerVo.deptId':searchScatterForm.getForm().findField('deptName').getValue(),
							//联系人姓名和固定电话
		  					'customerVo.linkManPhone':linkManPhone,
		  					'customerVo.linkManName':linkManName
					};
				}else{
					//设置请求参数
	  				var searchParams = { 
	  					//客户名称
	  					'customerVo.custName':searchScatterForm.getForm().findField('custName').getValue(),
	  					//部门
	  					'customerVo.deptId':searchScatterForm.getForm().findField('deptName').getValue(),
	  					//联系人姓名
	  					'customerVo.linkManName':linkManName,
	  					//联系人手机
	  					'customerVo.linkManMobile':linkManMobile,
	  					//联系人电话
	  					'customerVo.linkManPhone':linkManPhone,
	  					//客户属性  出发客户 到达客户
	  					'customerVo.custProperty':searchScatterForm.getForm().findField('custProperty').getValue(),
	  					//行业
	  					'customerVo.trade':firstTrade,
	  					//二级行业
	  					'customerVo.secondTrade':secondTrade,
	  					'customerVo.scheduleType':MAINTAIN_TYPE,
	  					//客户编码
	  					'customerVo.memberNo':memberNo,
	  					//联系人编码
	  					'customerVo.linkManCode':linkManCode,
	  					//会员等级
	  					'customerVo.memberLevel':searchScatterForm.getForm().findField('memberLevel').getValue(),
	  					//创建时间
	  					'customerVo.beginTime':searchScatterForm.getForm().findField('beginTime').getValue(),
	  					'customerVo.overTime':searchScatterForm.getForm().findField('overTime').getValue(),
	  					//业务类别 零担快递
	  					'customerVo.businessType':searchScatterForm.getForm().findField('custCategory').getValue(),
	  					//客户类型 潜散客
	  					'customerVo.custType':searchScatterForm.getForm().findField('custType').getValue(),
	  					//合作意向
	  					'customerVo.cooperationIntention':searchScatterForm.getForm().findField('cooperationIntention').getValue(),
	  					//商机状态
//	  					'customerVo.bussinesState':searchScatterForm.getForm().findField('businessOpportunityStatus').getValue(),
	  					//客户来源
	  					'customerVo.custbase':searchScatterForm.getForm().findField('custSorce').getValue(),
	  					//货量潜力
	  					'customerVo.volumePotential':searchScatterForm.getForm().findField('cargoPotential').getValue(),
	  					//开发阶段
	  					'customerVo.developState':searchScatterForm.getForm().findField('developStage').getValue(),
	  					//继续营销
	  					'customerVo.continueMark':searchScatterForm.getForm().findField('continueMarket').getValue()
	  				};
				}
				Ext.apply(operation,{
					params : searchParams
				});
			});
			var selModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE'});
			
			//查询结果Grid
			me.searchLeftResult =  Ext.create('SearchGridPanel',{
				id:'searchResultGridId',
				cls:'market',
				store:store,columns:me.getColumns(),
				selModel:selModel,
  				dockedItems:[{
  					xtype:'pagingtoolbar',
  					store:store,
  					dock:'bottom',
  					displayInfo : true,
  					autoScroll : true,
  					items:[{
  						text: i18n('i18n.authorization.roleGrid.page_count'),
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
  						text: i18n('i18n.authorization.roleGrid.number'),
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
				height:210,
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
                header : '客户编码',
                dataIndex : 'custNumber',
                width:100
            },{
				header : i18n('i18n.developSchedule.custName'),
				dataIndex : 'custName',
				width:100
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
                header : i18n('i18n.developSchedule.sendGoodsPontential'),//货量潜力
                dataIndex : 'goodsPotential'
            },{
                header : i18n('i18n.developSchedule.cooperatePurpose'),//合作意向
                dataIndex : 'coopIntetion',
                renderer:function(value){
                    return rendererDictionary(value,DataDictionary.COOPERATION_INTENTION);
                }
            },{
                header : i18n('i18n.developPhase.businessStatus'),//开发阶段
                dataIndex : 'businessStatus',
                renderer:function(value){
	                return rendererDictionary(value,DataDictionary.DEVELOP_STAGE);
	            }
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
	
	
	

	
	//整体布局
	var viewport=Ext.create('Ext.Viewport', {
		layout : {
			type : 'fit'
		},
		items : [ Ext.create('CustomerDevelopPlanePanel')]
	});
});

//设置form下的所有文本组件去空格 
function eachTextFieldTrim(form){
    var i = 0;
    var xtype = null;
    for(i=0;i<form.getFields().length;i++){
        xtype = form.getFields().items[i].xtype;
        if(xtype != 'textfield' && xtype != 'textareafield' && xtype != 'textarea')
            continue;
        form.getFields().items[i].setValue(Ext.String.trim(form.getFields().items[i].getValue()))
    }
}