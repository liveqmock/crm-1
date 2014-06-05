Ext.QuickTips.init();
var customerId=null;
var customerDevelopPlane =  new CustomerDevelopPlaneData();//初始化计划新增修改data
Ext.onReady(function(){
//客户编码数组
var custNum = [];	
var keys=[
  	    //开发方式
  	    'DEVELOP_WAY',
  	    // 合作意向
  		'COOPERATION_INTENTION',
  		// 货量潜力
  		'CARGO_POTENTIAL',
  		// 商机状态
  		'BUSINESS_STATUS',
  	    //会员等级
		'MEMBER_GRADE',
		//发货类别，同计划类别
		'PLAN_TYPE',
		//问卷状态
	    'SURVEY_STATUS',
	    //适用范围
	    'SURVEY_RANGE',
		//客户类别
		'CUST_TYPE'
	];
  	//初始化业务参数
  	initDataDictionary(keys);
  	initDeptAndUserInfo();
/**
 * 客户信息
 */
Ext.define('CustomerInfoFormConditionPanel',{
	extend:'SearchFormPanel', 
	region:'north',
	height:92,
	items:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			layout:{
				type:'table',
				columns:3
			},
			defaults:{
				labelAlign:'right',
				labelWidth:65,
				width:240
			},
			defaultType:'combo',
			items:[{
				fieldLabel:i18n('i18n.Cycle.deptId'),
				id:'deptId',
				name:'deptId',
				xtype:'combo',
				store:Ext.create('DeptStore',{
					listeners:{
						beforeload:function(store, operation, eOpts){
							Ext.apply(operation,{
								params : {
									'deptName':Ext.getCmp("customerInfoFormPanelByCycle").getForm().findField("deptId").getRawValue()
									}
								}
							);	
						}
					}
				}),
//				colspan:3,
				triggerAction : 'all',
				displayField:'deptName',
				valueField:'id',
				forceSelection :true,
				hideTrigger:false,
				allowBlank :false,
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
					change:DButil.comboSelsct,
					select:function(th,records){
						Ext.getCmp("groupId").getStore().load({params:{'deptId':th.getValue()}});
						Ext.getCmp("userId").getStore().load({params:{'deptId':th.getValue()}});
					}
				}
			}
			,{
				fieldLabel:i18n('i18n.Cycle.custName'),
				xtype:'textfield',
				id:'custName',
				maxLength:20,
				maxLengthText : i18n('i18n.developPlan.maxLength')+20
			},{
				fieldLabel:i18n('i18n.Cycle.custId'),
				xtype:'textfield',
				id:'custId',
				name:'custId',
				maxLength : 20,
				maxLengthText : i18n('i18n.developPlan.maxLength')+20,
				listeners:{
					change:function(t){
						if(!Ext.isEmpty(t.getValue())){
							if(!Ext.isEmpty(Ext.getCmp('customerInfoFormPanelByCycle'))){
								Ext.getCmp('customerInfoFormPanelByCycle').getForm().findField('groupId').disable();	
								Ext.getCmp('customerInfoFormPanelByCycle').getForm().findField('userId').disable();
							}
						}else{
							if(!Ext.isEmpty(Ext.getCmp('customerInfoFormPanelByCycle'))){
								Ext.getCmp('customerInfoFormPanelByCycle').getForm().findField('groupId').enable();	
								Ext.getCmp('customerInfoFormPanelByCycle').getForm().findField('userId').enable();
								var sendGoodsType = Ext.getCmp('sendGoodsType').getValue();
							}
						}
					}
				}
			},{
				fieldLabel:i18n('i18n.Cycle.groupId'),
				id:'groupId',
				name:'groupId',
				store:Ext.create('CustomerGroupStore',{
					listeners:{
						load:function(t,records){
							if(records.length==0){
								Ext.getCmp('customerInfoFormPanelByCycle').getForm().findField('userId').setValue("");
							}
						}
					}
				}),
				queryMode: 'local',
				triggerAction : 'all',
				displayField:'groupName',
				valueField:'id',
				forceSelection :true,
				listConfig: {
	                loadMask:false
	            },
				listeners:{
					change:DButil.comboSelsct,
					select:function(th){
						if(!Ext.isEmpty(th.getValue())){
							var params = {
								'customerGroupId' : th.getValue(),
								'deptId':Ext.getCmp('customerInfoFormPanelByCycle').getForm().findField('deptId').getValue()
							};
							var successFn = function(json){
								if(!Ext.isEmpty(json) && !Ext.isEmpty(json.userId)){
									Ext.getCmp("userId").setValue(json.userId);
								}else{
									Ext.getCmp("userId").setValue("");
								}
							};
							var failureFn = function(json){
								MessageUtil.showErrorMes(json.message);
							};
							customerDevelopPlane.searchPrehumanByGroupId(params,successFn,failureFn);
						}
					}
				}
			},{
				fieldLabel:i18n('i18n.Cycle.userId'),
				id:'userId',
				name:'userId',
				store:Ext.create('PrehumanStore'),
				queryMode: 'local',
				triggerAction : 'all',
				displayField:'empName',
				queryParam : 'planeDraft',
				valueField:'id',
				forceSelection :true,
				listConfig: {
	                loadMask:false
	            },
				listeners:{
					change:DButil.comboSelsct,
					select:function(th){
						if(!Ext.isEmpty(th.getValue())){
							var params = {
								'userId' : th.getValue(),
								'deptId':Ext.getCmp('customerInfoFormPanelByCycle').getForm().findField('deptId').getValue()
							};
							var successFn = function(json){
								if(!Ext.isEmpty(json) && !Ext.isEmpty(json.customerGroupId)){
									Ext.getCmp("groupId").setValue(json.customerGroupId);	
								}else{
									Ext.getCmp("groupId").setValue("");
								}
							};
							var failureFn = function(json){
								MessageUtil.showErrorMes(json.message);
							};
							customerDevelopPlane.serachCustomerGroupByPrehuman(params,successFn,failureFn);
						}
					}
				}
			},{
				id:'queryDate',
				xtype:'datefield',
				name:'queryDate',
				fieldLabel:i18n('i18n.Cycle.queryDate'),
				allowBlank:false,
				//设置最大可选择的日期为当前日期的前一天
				maxValue:Ext.Date.add(new Date(), Ext.Date.DAY, -1),
				format:'Y-m-d',
				//设置默认显示时间为当前日期的前一天
				value:Ext.Date.add(new Date(), Ext.Date.DAY, -1)
			},{
				/*
				 * @auth 苏玉军
				 * @date 2014/03/04
				 * @description 发到货周期页面新增加“发货类别”，“客户类别”查询字段
				 */
				id:'sendGoodsType',
				name:'sendGoodsType',
				xtype:'combobox',
				fieldLabel:i18n('i18n.Cycle.sendGoodsType'),
				store:getDataDictionaryByName(DataDictionary,'PLAN_TYPE'),
				displayField:'codeDesc',
				valueField:'code',
				value:'LESS_THAN_TRUCKLOAD',
				allowBlank:false,
				editable:false,
				listeners:{
					change:DButil.comboSelsct,
					select:function(){
						var custId = Ext.getCmp('customerInfoFormPanelByCycle').getForm().findField('custId').getValue();
						if(this.getValue() == 'LESS_THAN_TRUCKLOAD'){
							Ext.getCmp('custType').disable();
						}else if(this.getValue() == 'EXPRESS'){
							Ext.getCmp('custType').enable();
						}
					}
				}
			},{
				id:'custType',
				name:'custType',
				xtype:'combobox',
				fieldLabel:i18n('i18n.Cycle.custType'),
				store:getDataDictionaryByName(DataDictionary,'CUST_TYPE'),
				queryMode: 'local',
			    displayField: 'codeDesc',
			    valueField: 'code',
			    value:'RC_CUSTOMER',
			    disabled:true,
				allowBlank:false,
				editable:false,
			    listeners:{
			    	afterrender:function(){
			    		this.store.remove(this.store.findRecord('code','PC_CUSTOMER'));
			    	}
			    }
			}]
		}];
	}
});	

/**
 * 创建buttonPanel
 */
Ext.define('ButtonPanel',{
	extend:'NormalButtonPanel', 
	items:null,
	region:'north',
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'leftbuttonpanel',
			width:400,
			defaultType:'button',
			items:[{
				text:i18n('i18n.Cycle.makePlan'),
				hidden:!isPermission('marketing.maintainplan.arrivalCycleView.btnSavePlan'),
				handler:function(){
					var cycleListGrid = Ext.getCmp("cycleListGrid");
					var selection = cycleListGrid.getSelectionModel().getSelection();
					if(selection.length == 0){
						MessageUtil.showMessage(i18n('i18n.developPlan.choice'));
						return false;
					}
					custNum = [];
					for(var i = 0;i<selection.length;i++){
						custNum.push(selection[i].data.custNumber);
					}
					
					Ext.getCmp('searchResultGridId').store.load();
					Ext.getCmp("ChooseCustomerGridId").store.each(function(r){
						Ext.getCmp("ChooseCustomerGridId").getSelectionModel().deselect(r);
					});
					Ext.getCmp('ChooseCustomerGridId').store.removeAll();
					var planFromPanel = Ext.getCmp("planFromPanel").getForm();
  		    		planFromPanel.findField('topic').setValue(null);
			    	planFromPanel.findField('execdeptid').setValue(null);//执行部门
				    planFromPanel.findField('activedesc').setValue(null);
				   	planFromPanel.findField('execuserid').setValue();//执行人员
					deliveryDevelopPopWindow.show();
					
					
					
					var deptName=Ext.getCmp('customerInfoFormPanelByCycle').getForm().findField('deptId').getRawValue();
					var deptId=Ext.getCmp('customerInfoFormPanelByCycle').getForm().findField('deptId').getValue();
					
					Ext.getCmp("planFromPanel").getForm().findField("execdeptid").setValue(deptName);
					Ext.getCmp("planFromPanel").getForm().findField("execuserid").store.load({params:{executeDept:deptId}});
					
					document.getElementsByTagName("html")[0].style.overflowY="auto";
					document.getElementsByTagName("html")[0].style.overflowX="auto";
					viewport.doLayout();
				}
			},{
				text:i18n('i18n.Cycle.makeSchedule'),
				handler:function(){
					var cycleListGrid = Ext.getCmp("cycleListGrid");
					var selection = cycleListGrid.getSelectionModel().getSelection();
					if(selection.length == 0){
						MessageUtil.showMessage(i18n('i18n.developPlan.choice'));
						return false;
					}
					custNum = [];
					for(var i = 0;i<selection.length;i++){
						custNum.push(selection[i].data.custNumber);
					}
					Ext.getCmp('searchScheduleGridId').store.load();
					deliverySchedulePopWindow.show();
				}
			},
			{
				/*
				 *描述：添加回访按钮，实现无计划无日程的回访功能 
				 *肖红叶
				 *2012-10-16
				 */
				text:i18n('i18n.developSchedule.returnVisit'),
				handler:function(){
					var cycleListGrid = Ext.getCmp("cycleListGrid");
					var selection = cycleListGrid.getSelectionModel().getSelection();
					
					//判断是否有且选中表中唯一一条记录
					if(selection.length == 0){
						MessageUtil.showMessage(i18n('i18n.developPlan.choice'));
						return false;
					}else if(selection.length > 1){
						MessageUtil.showMessage(i18n('i18n.returnVisit.selectOneRecord'));
						return false;	
					}
					Ext.getCmp('searchLinkManResultGridId').store.load();
					returnVisitPopWindow.show();
				}
			},{/*本月到货金额明细按钮，配合营销优化需求增加的按钮
				*肖红叶20131120
				*/
				text:i18n('i18n.Cycle.arrival')+i18n('i18n.Cycle.amountDetail'),
				handler:function(){
					var customerInfoFormPanel =  Ext.getCmp('customerInfoFormPanelByCycle');
					//获取选中的记录
                	var selection = Ext.getCmp('cycleListGrid').getSelectionModel().getSelection();
                	if(selection.length === 0){
                		MessageUtil.showErrorMes(i18n('i18n.plan.doNotSelectItem'));
                		return false;
                	}
					var params = {
						'condition.queryDate' : customerInfoFormPanel.getForm().findField('queryDate').getValue(),
						//发货标志
						'condition.fcAnalyseType' :CYCLE_ARRIVAL
					};
					
					var successFn = function(json){
						//获取win
						var arriveAmountDetailPopWindow=Ext.getCmp("arriveAmountDetailPopWindowId");
						//判断此窗体在页面中是否已经存在，如果不存在，则创建一个
						if(!arriveAmountDetailPopWindow){
							arriveAmountDetailPopWindow=Ext.create('ArriveAmountDetailPopWindow',{
								id:'arriveAmountDetailPopWindowId'
							});
						}
						//显示金额明细弹出框
						arriveAmountDetailPopWindow.show();
						Ext.getCmp('queryDetailDate').setValue(customerInfoFormPanel.getForm().findField('queryDate').getValue());
						//获取grid
						var arriveAmountDetailGrid = Ext.getCmp("arriveAmountDetailGridId");
						//获取grid的store
						var arriveAmountDetailStore = arriveAmountDetailGrid.store;
						//重配置grid的表头
						arriveAmountDetailGrid.reconfigure(arriveAmountDetailStore,json.columModel);
						//调整表格中数据显示
						arriveAmountDetailGrid.scrollByDeltaX(-1);
						//加载grid中的数据，并初始化为第一页
						arriveAmountDetailStore.loadPage(1);
						
					};
					var failureFn = function(json){
						MessageUtil.showErrorMes(json.message);
					};
					//获取列头
					CycleData.prototype.queryAmountDetailHeader(params,successFn,failureFn);
					
				}
			}
			]
		},{
			xtype:'middlebuttonpanel' 
		},{
			xtype:'rightbuttonpanel',
			items:[{
				xtype:'button',
				text:i18n('i18n.developSchedule.search'),
				handler:function(){
					var customerInfoFormPanel =  Ext.getCmp('customerInfoFormPanelByCycle');
                    //判断部门和日期是否为空 空则提示
                    if(Ext.isEmpty(customerInfoFormPanel.getForm().findField('queryDate').getValue())
                    ||Ext.isEmpty(customerInfoFormPanel.getForm().findField('deptId').getValue())){
                        MessageUtil.showMessage(i18n('i18n.Cycle.vaildate'));
                        return false;
                    }
                    //判断界面校验是否通过
                    if(!customerInfoFormPanel.getForm().isValid()){
                        return false;
                    }
					Ext.getCmp('searchDate').setValue(Ext.getCmp('queryDate').getValue());
					
					var params = {
						//查询日期
						'condition.queryDate' : customerInfoFormPanel.getForm().findField('queryDate').getValue(),
						//发货标志
						'condition.fcAnalyseType' :CYCLE_ARRIVAL
					};
					var successFn = function(json){
						//获取数据
						var cycleListGrid = Ext.getCmp("cycleListGrid");
						var cycleListStore = cycleListGrid.store;
						
						//显示数据
						cycleListGrid.reconfigure(cycleListStore,json.columModel);
						cycleListGrid.scrollByDeltaX(-1);
						//设置每次查询时，将当前页数设置成第一页
						cycleListStore.loadPage(1);
					};
					var failureFn = function(json){
						MessageUtil.showErrorMes(json.message);
					};
					//获取列头
					CycleData.prototype.queryCycleListHeader(params,successFn,failureFn);
					
				}
			}]
		}];
	}
});


/**
 * 回访记录表格
 */
Ext.define('ReturnVisitListGrid',{
	extend:'PopupGridPanel',  
	//height:185,
	store:Ext.create('CycleReturnVisitStore',{id:'cycleReturnVisitStore'}),
	initComponent:function(){             
		var me = this;
		me.columns = [{
			xtype:'rownumberer',
			width:40,
			align:'center',
			header:i18n('i18n.Cycle.rownum')
		},{    
			header :i18n('i18n.Cycle.schedule'),
			dataIndex:'schedule',
			renderer : DButil.renderDate
		},{
			header : i18n('i18n.Cycle.linkName'),
			dataIndex:'linkName'
		},{
			header : i18n('i18n.Cycle.linkManMobile'),
			dataIndex:'linkManMobile'
		},{
			header : i18n('i18n.Cycle.linkManPhone'),
			dataIndex:'linkManPhone'
		},{
			header : i18n('i18n.Cycle.theme'),
			dataIndex:'theme'
		},{
			header : i18n('i18n.Cycle.needType'),
			dataIndex:'needType'
		},{
			header : i18n('i18n.Cycle.way'),
			dataIndex:'way',
			renderer:function(value){
				return DButil.rendererDictionary(value,DataDictionary.DEVELOP_WAY);
			}
		},{
			header : i18n('i18n.Cycle.userName'),
			dataIndex:'userName'
		}];
		this.callParent();
	}
});

//回访记录页签
Ext.define('ReturnVisitTab',{
	extend:'TabContentPanel',
	title:i18n('i18n.Cycle.returnVisitLog'), 
	layout:'fit',
	items:[Ext.create('ReturnVisitListGrid',{id:'returnVisitListGrid'})],
	initComponent:function(){
		var me = this;
		this.callParent();
	}
});


/**
 *路线分析表格
 */
Ext.define('CircuitAnalysisGrid',{
	extend:'PopupGridPanel',  
	initComponent:function(){             
		var me = this;
			me.columns = [];
		this.callParent();
	}
});

//路线分析页签
Ext.define('CircuitAnalysis',{
	extend:'TabContentPanel',
	title:i18n('i18n.Cycle.circuitAnalysis'),
	layout:'fit',
	listeners:{
		'activate':function(){
			var searchDate  = Ext.getCmp('searchDate').getValue();
			var custNumber = Ext.getCmp('custNumber').getValue();
			//动态查询线路分析数据
			if(searchDate!=null && custNumber!=null){
				/**
				 * 苏玉军
				 */
				//1.获得线路分析表格、获得发货类别
				var circuitAnalysisGrid = Ext.getCmp('circuitAnalysisGrid');
				var sendGoodsType = Ext.getCmp('sendGoodsType').getValue();
				var column = circuitAnalysisGrid.columns;
				//2.如果发货类别是零担，隐藏经济快递
				if('LESS_THAN_TRUCKLOAD' == sendGoodsType){
					circuitAnalysisGrid.reconfigure(circuitAnalysisGrid.getStore(),[{
						xtype:'rownumberer',
						width:40,
						align:'center',
						header:i18n('i18n.Cycle.rownum')
					},{    
						header : i18n('i18n.Cycle.city'), 
						dataIndex:'city'
					},{
						header : i18n('i18n.Cycle.lastMonthSedRevAmount'), 
						dataIndex:'lastMonthAmount'
					},{
						header : i18n('i18n.Cycle.amount'), 
						columns: [{
			                header:i18n('i18n.Cycle.lastMonth'),
			                sortable : true,
			                dataIndex: 'lastMonthSameTimeAmount'
			            }, {
			                header: i18n('i18n.Cycle.currentMonth'),
			                sortable : true,
			                dataIndex: 'currentMonthSameTimeAmount'
			            }]
					},{
						header : i18n('i18n.Cycle.volume'), 
						columns:[{
							header: i18n('i18n.Cycle.lastMonth'),
							sortable : true,
			       			dataIndex: 'lastMonthvolume'
						},{
							header: i18n('i18n.Cycle.currentMonth'),
							sortable : true,
			       			dataIndex: 'currentMonthvolume'
						}]
					},{
						header : i18n('i18n.Cycle.votes'), 
						columns:[{
							header: i18n('i18n.Cycle.lastMonth'),
							sortable : true,
			       			dataIndex: 'lastMonthVotes'
						},{
							header: i18n('i18n.Cycle.currentMonth'),
							sortable : true,
			       			dataIndex: 'currentMonthvotes'
						}]
					},{
						header : i18n('i18n.Cycle.truckVotes'), 
						columns:[{
							header: i18n('i18n.Cycle.lastMonth'),
							sortable : true,
			       			dataIndex: 'lastMonthTruckVotes'
						},{
							header: i18n('i18n.Cycle.currentMonth'),
							sortable : true,
			       			dataIndex: 'currentMonthTruckVotes'
						}]
					},{
						header : i18n('i18n.Cycle.cityVotes'), 
						columns:[{
							header: i18n('i18n.Cycle.lastMonth'),
							sortable : true,
			       			dataIndex: 'lastMonthCityVotes'
						},{
							header: i18n('i18n.Cycle.currentMonth'),
							sortable : true,
			       			dataIndex: 'currentMonthCityVotes'
						}]
					},{
						header : i18n('i18n.Cycle.longTruckVotes'), 
						columns:[{
							header: i18n('i18n.Cycle.lastMonth'),
							sortable : true,
			       			dataIndex:'lastMonthlongTruckVotes'
						},{
							header: i18n('i18n.Cycle.currentMonth'),
							sortable : true,
			       			dataIndex: 'currentMonthlongTruckVotes'
						}]
					},{
						header : i18n('i18n.Cycle.shortTruckVotes'), 
						columns:[{
							header: i18n('i18n.Cycle.lastMonth'),
							sortable : true,
			       			dataIndex: 'lastMonthshortTruckVotes'
						},{
							header: i18n('i18n.Cycle.currentMonth'),
							sortable : true,
			       			dataIndex: 'currentMonthshortTruckVotes'
						}]
					},{
						header : i18n('i18n.Cycle.airTransVotes'), 
						columns:[{
							header: i18n('i18n.Cycle.lastMonth'),
							sortable : true,
			       			dataIndex: 'lastAirTransVotes'
						},{
							header: i18n('i18n.Cycle.currentMonth'),
							sortable : true,
			       			dataIndex: 'currentAirTransVotes'
						}]
					}]);
					circuitAnalysisGrid.scrollByDeltaX(-1);
				}else if('EXPRESS' == sendGoodsType){
					circuitAnalysisGrid.reconfigure(circuitAnalysisGrid.getStore(),[{
						xtype:'rownumberer',
						width:40,
						align:'center',
						header:i18n('i18n.Cycle.rownum')
					},{    
						header : i18n('i18n.Cycle.city'), 
						dataIndex:'city'
					},{
						header : i18n('i18n.Cycle.lastMonthSedRevAmount'), 
						dataIndex:'lastMonthAmount'
					},{
						header : i18n('i18n.Cycle.amount'), 
						columns: [{
			                header:i18n('i18n.Cycle.lastMonth'),
			                sortable : true,
			                dataIndex: 'lastMonthSameTimeAmount'
			            }, {
			                header: i18n('i18n.Cycle.currentMonth'),
			                sortable : true,
			                dataIndex: 'currentMonthSameTimeAmount'
			            }]
					},{
						header : i18n('i18n.Cycle.volume'), 
						columns:[{
							header: i18n('i18n.Cycle.lastMonth'),
							sortable : true,
			       			dataIndex: 'lastMonthvolume'
						},{
							header: i18n('i18n.Cycle.currentMonth'),
							sortable : true,
			       			dataIndex: 'currentMonthvolume'
						}]
					},{
						/**
						 * 经济快递
						 * @author 043260苏玉军
						 */
						header:i18n('i18n.Cycle.economyExpressVotes'),
						columns:[{
							header:i18n('i18n.Cycle.lastMonth'),
							sortable:true,
							dataIndex:'lastEconomyExpressVotes'
						},{
							header: i18n('i18n.Cycle.currentMonth'),
							sortable:true,
							dataIndex:'currentEconomyExpressVotes'
						}]
					}]);
					circuitAnalysisGrid.scrollByDeltaX(-1);
				}
				Ext.getCmp('circuitAnalysisGrid').store.load();
			}
		}
	},
	initComponent:function(){
		var me = this;
		var cycleCircuitAnalysisStore = Ext.create('CycleCircuitAnalysisStore',{id:'cycleCircuitAnalysisStore'});
		cycleCircuitAnalysisStore.on('beforeload',function(store,operation,e){
			var searchParams = {
				'condition.custNumber' : Ext.getCmp('custNumber').getValue(),
				'cycleType' : "1",
				'condition.queryDate' : Ext.getCmp('searchDate').getValue(),
				'condition.sendGoodsType' : Ext.getCmp('sendGoodsType').getValue()
				};
			Ext.apply(operation,{
				params : searchParams
			});
		});
		me.items=[Ext.create('CircuitAnalysisGrid',{id:'circuitAnalysisGrid',store:cycleCircuitAnalysisStore})]
		this.callParent();
	}
});

/**
 * 多页签
 */
Ext.define('CycleTabPanel',{        
	extend:'NormalTabPanel',
	region:'center',
	activeTab: 0,
	border:true,
	returnVisitTab:null,
	circuitAnalysis:null,
	layout:'fit',
	items:null,
	initComponent:function(){
		var me = this;
		me.returnVisitTab = Ext.create('ReturnVisitTab',{id:'returnVisitTab'});   
		me.circuitAnalysis = Ext.create('CircuitAnalysis',{id:'circuitAnalysis'});
		me.items = [
			me.returnVisitTab,        
			me.circuitAnalysis
		],
		this.callParent();
	}
});

Ext.define('CycleListGrid',{
	extend:'SearchGridPanel',   
	columns:null,
	region:'center',
	selModel :Ext.create('Ext.selection.CheckboxModel',{id:'selModelId'}),
	listeners:{
		'itemdblclick':function(th,record){
			Ext.getCmp('returnVisitListGrid').store.load({params:{'condition.custId':record.data.custId}});
			Ext.getCmp('cycleTabPanel').setActiveTab('returnVisitTab');
			Ext.getCmp('custNumber').setValue(record.data.custNumber);
			Ext.getCmp('customerName').setValue(record.data.custName);
		}
	},
	initComponent:function(){             
		var me = this;
		me.columns = [{
			xtype:'rownumberer',
			width:40,
			align:'center',
			header:i18n('i18n.Cycle.rownum')
		},{    
			header : i18n('i18n.Cycle.custId'),
			width:80,
			align:'center',
			dataIndex:'custNumber'
		},{
			header : i18n('i18n.Cycle.custName'),
			width:80,
			align:'center',
			dataIndex:'custName'
		},{
			header : i18n('i18n.Cycle.empName'),
			width:80,
			align:'center',
			dataIndex:'empName'
		},{
			header : i18n('i18n.Cycle.arriveGoodCycle'),
			width:105,
			align:'center',
			dataIndex:'sendGoodCycle'
		},{
			header : i18n('i18n.Cycle.monthArriveAmount'),
			flex:0.3,
			align:'center',
			dataIndex:'monthAmount'
		},{
			header : i18n('i18n.Cycle.month1ArriveAmount'),
			flex:0.3,
			align:'center',
			dataIndex:'month1Amount'
		},{
			header : i18n('i18n.Cycle.month2ArriveAmount'),
			flex:0.4,
			align:'center',
			dataIndex:'month2Amount'
		}];
		this.dockedItems=[{
			xtype:'pagingtoolbar',
			id:'pagingtoolbar',
			store:me.store,
			dock:'bottom',
			displayInfo : true,
			autoScroll : true
		}];
		this.callParent();
	}
});

Ext.define('CycleListPanel',{
	extend:'BasicPanel',
	region:'center',
	layout:'border',
	initComponent:function(){
		var me = this;
		var cycleListStore = Ext.create('CycleListStore',{id:'cycleListStore',
			listeners:{
				load:function(store,records){
					var girdcount=0;
					store.each(function(record){
						 if(!Ext.isEmpty(record.get("sendGoodCycle"))){
							record.set("sendGoodCycle",record.get("sendGoodCycle")+'天/次');
							record.commit();
			 			};	
			 			girdcount=girdcount+1;
					});
				}
			}
		});
		var cycleListGrid = Ext.create('CycleListGrid',{id:'cycleListGrid',store:cycleListStore,
			listeners: {
		    	scrollershow: function(scroller) {
		    		if (scroller && scroller.scrollEl) {
						scroller.clearManagedListeners(); 
						scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
		    		}
		    	}
		    }})
		//给store添加事件
		cycleListStore.on('beforeload',function(store,operation,e){
			var customerInfoFormPanel =  Ext.getCmp('customerInfoFormPanelByCycle');
            //查询前删除store里面的数据
//            cycleListStore.removeAll();
			var searchParams = {
				'condition.deptId' : customerInfoFormPanel.getForm().findField('deptId').getValue(),
				'condition.queryDate' : customerInfoFormPanel.getForm().findField('queryDate').getValue(),
				'condition.custNumber' : customerInfoFormPanel.getForm().findField('custId').getValue(),
				'condition.groupId' : customerInfoFormPanel.getForm().findField('groupId').getValue(),
				'condition.userId' : customerInfoFormPanel.getForm().findField('userId').getValue(),
				'condition.fcAnalyseType' :CYCLE_ARRIVAL,
				'condition.custName': customerInfoFormPanel.getForm().findField('custName').getValue(),
				//发货类别
				'condition.sendGoodsType' : customerInfoFormPanel.getForm().findField('sendGoodsType').getValue(),
				//客户类别
				'condition.custType':customerInfoFormPanel.getForm().findField('custType').getValue()
			};
			Ext.apply(operation,{
				params : searchParams
			});
		});
		cycleListStore.on('load',function(store,records){
			var girdcount=0;
        	store.each(function(record){
	 			var status = record.get("businessOpportunityStatus");//商机状态
	 			var isRepeat = record.get("repeatCust");//疑似重复
	 			if(isRepeat == '1' || status == 'ONGOING' || status == 'EXTENDED' || status == 'DORMANT'){//有未关闭的商机状态，标红
	 				var cells =  Ext.getCmp('cycleListGrid').getView().getNodes()[girdcount].children;
					for(var i= 0;i<cells.length;i++){
						cells[i].style.backgroundColor='#FF9797';
					};
	 			}
	 			girdcount=girdcount+1;
        	});
		});
		me.items = [
			Ext.create('ButtonPanel',{id:'buttonPanel'}),        
			cycleListGrid
		],
		this.callParent();
	}
});

Ext.define('CyclePanel',{
	extend:'BasicPanel',
	layout:'border',
	items:[{
		xtype:'basicpanel',
		region:'center',
		layout:'border',
		items:[Ext.create('CustomerInfoFormConditionPanel',{id:'customerInfoFormPanelByCycle'}),
		Ext.create('CycleListPanel',{id:'cycleListPanel'})]
	},{
		xtype:'basicpanel',
		region:'south',
		height:260,
		layout:'border',
		items:[{
			xtype:'notitleformpanel',
			region:'north',
			margin:'5 5 0 0',
			defaults:{
				labelAlign:'left',
				labelWidth:65,
				width:200
			},
			layout:'hbox',
			items:[{
				xtype:'textfield',
				fieldLabel:i18n('i18n.Cycle.custName'),
				id:'customerName',
				disabled:true,
				readOnly:true
			},{
				xtype:'datefield',
				fieldLabel:i18n('i18n.Cycle.queryDate'),
				id:'searchDate',
				format:'Y-m-d',
				disabled:true,
				readOnly:true
			},{
				xtype:'hiddenfield',
				id:'custNumber'
			}]
		},Ext.create('CycleTabPanel',{id:'cycleTabPanel'})]
	}]
});

/*===========制定计划弹出框============*/
/**
  * 制定开发计划按钮
 */
  	Ext.define('RightDownButtonPanel',{
  		extend:'PopButtonPanel', 
  		items:null,
  		region:'south',
  		weight:400,
  		initComponent:function(){
  			this.items = this.getItems();
  			this.callParent();
  		},
  		getItems:function(){
  			var me = this;
  			return [{
  				xtype:'middlebuttonpanel' 
  			},{
  				xtype:'poprightbuttonpanel',  
  				width:500,
  				items : [{
  					xtype : 'button',
  					text : i18n('i18n.developPlan.savePlan'),
  					width : 115,
  					scope:me.searchCondition,
  					handler : function(){
  						var store=Ext.getCmp('ChooseCustomerGridId').store;
  						if(store.getCount()!=0){
  							var custList=new Array();
  							var contactIds=new Array();
  							for(var i=0;i<store.getCount();i++){//获取已经选择的客户id
  								custList[i]=store.getAt(i).data.memberId;
  								contactIds[i]=store.getAt(i).data.contactId;
  							}
  						}else{//如果没有选择客户给出提示"请选择客户再新增计划~"
                            MessageUtil.showMessage(i18n("i18n.developPlan.choiceCustFirst"));   
                            return;
                        }
                        
  						var successFn = function(json){
  				    		MessageUtil.showInfoMes(i18n('i18n.developPlan.saveDevelopPlanSuccess'));
  				    		Ext.getCmp('ChooseCustomerGridId').store.removeAll();
  				    		var planFromPanel = Ext.getCmp("planFromPanel").getForm();
		  		    		planFromPanel.findField('topic').setValue(null);
					    	planFromPanel.findField('execdeptid').setValue(null);//执行部门
						    planFromPanel.findField('activedesc').setValue(null);
						   	planFromPanel.findField('execuserid').setValue();//执行人员
  				    		deliveryDevelopPopWindow.hide();
  				    	};
  				    	var failureFn = function(json){
  				    		MessageUtil.showErrorMes(json.message);
  				    	};
  				    	var planFromPanel = Ext.getCmp("planFromPanel").getForm();
  				    	var deptId=Ext.getCmp('customerInfoFormPanelByCycle').getForm().findField('deptId').getValue();
  				    	var planType = "";
  				    	var custType = Ext.getCmp('customerInfoFormPanelByCycle').getForm().findField('custType').getValue();
  				    	var sendGoodsType = Ext.getCmp('customerInfoFormPanelByCycle').getForm().findField('sendGoodsType').getValue();
  				    	if('LESS_THAN_TRUCKLOAD' == sendGoodsType ||('EXPRESS' == sendGoodsType && 'RC_CUSTOMER' == custType)){
  				    		planType = 'MAINTAIN_TYPE';
  				    	}else{
  				    		planType = 'DEVELOP_TYPE'
  				    	}
  				    	var param = {
  				    		'developPlan.beginTime':planFromPanel.findField('beginTime').getValue(),//开始时间
  		    			    'developPlan.endTime':planFromPanel.findField('endTime').getValue(),//结束时间
  		    			    'developPlan.topic':planFromPanel.findField('topic').getValue(),
  		    			    'developPlan.execdeptid':deptId,//执行部门
  		    			    'developPlan.activedesc':planFromPanel.findField('activedesc').getValue(),
  		    			    'developPlan.execuserid':planFromPanel.findField('execuserid').getValue(),//执行人员
  		    			    'developPlan.id':planFromPanel.findField('id').getValue(),
  		    			    'developPlan.planType':planType,
  		    			    'developPlan.projectType':planFromPanel.findField('projectType').getValue(),
  		    			  'developPlan.surveyId':planFromPanel.findField('questionnaireId').getValue(),
  		    			    'custList':custList,
  		    			    'contactIds':contactIds
  		    			};
  				    	if(planFromPanel.isValid()){
  				    		CustomerDevelopPlaneData.prototype.savePlan(param, successFn, failureFn);
  				    	}
  					}
  				},{
  					xtype : 'button',
  					text : i18n('i18n.developPlan.reset'),
  					width : 70,
  					handler : function(){
  						Ext.getCmp("planFromPanel").getForm().reset();
                        //重置不清除部门名称
                        var deptName=Ext.getCmp('customerInfoFormPanelByCycle').getForm().findField('deptId').getRawValue();
                        Ext.getCmp("planFromPanel").getForm().findField("execdeptid").setValue(deptName);
  					}
  				},{
  					xtype : 'button',
  					text : i18n('i18n.DevelopManageView.close'),
  					width : 70,
  					handler : function(){
  						deliveryDevelopPopWindow.hide();
  					}
  				}]
  			}];
  		}
  	});
	  	
  	/**
  	 * 移动按钮
  	 */
  	Ext.define('ButtonPanelRole',{
  		extend :'PopRightButtonPanel',
  		width : '100%',
  		buttonAlign : 'center',
  		layout : 'column',
  		items : [{
  			columnWidth : 1,
  			xtype : 'container',
  			style : 'padding-top:60px;border:none',
  			width : '100%',
  			items : [{
  				xtype : 'button',
  				text : '&gt;',
  				width : 39,
  				// 添加所选客户
  				handler : function(){
  					//得到所选客户
  					var selection=Ext.getCmp("searchResultGridId").getSelectionModel().getSelection();
  					//已选择客户store
  					var chooseStore=Ext.getCmp("ChooseCustomerGridId").store;
  					for(var i=0;i<selection.length;i++){//遍历所选客户
	  						var isRepeat = selection[i].get('repeatCust');//疑似重复
					    	var status = selection[i].get('businessOpportunityStatus');//商机状态
	  	  					if(status == 'ONGOING' || status == 'EXTENDED' || status == 'DORMANT'){//有未关闭的商机状态，不允许指定新计划
	  	  						 MessageUtil.showErrorMes('['+selection[i].get("linkManName")+']'+'存在未关闭的商机，不能制定计划！');
	  	  						 return false;
	  			 			}
		  	  				if(isRepeat == '1'){//疑似重复客户
	 	  						 MessageUtil.showErrorMes('['+selection[i].get("linkManName")+']'+'是' +'[' +selection[i].get("repeatCustDeptName")+']' + '疑似重复客户,不能制定计划！');
	 	  						 return false;
	 			 			}
    				    	if(chooseStore.find("contactId",selection[i].get("contactId"))!=-1){//判断是否有重复
    						    MessageUtil.showErrorMes(selection[i].get("linkManName")+i18n('i18n.developPlan.exist'));
    						    return false;
    					    }else{
    					    	Ext.getCmp("searchResultGridId").getSelectionModel().deselect(selection[i]);
    					    	Ext.getCmp("searchResultGridId").store.remove(selection[i]);
    					    	//添加到已选择客户store里
    					    	chooseStore.insert(i,selection[i]);
    					    }
  					}
  					//Ext.getCmp("chooseSelModelId").deselectAll(true);
  					//Ext.getCmp("selModelId").deselectAll(true);
  				}
  			}]
  		},{
  			columnWidth : 1,
  			width : '100%',
  			xtype : 'container',
  			style : 'padding-top:60px;border:none',
  			items : [{
  				xtype : 'button',
  				text : '&lt;',
  				width : 39,
  				//移除所选客户
  				handler : function(){
  					//得到已选客户
  					var selection=Ext.getCmp("ChooseCustomerGridId").getSelectionModel().getSelection();
  					//待选择客户store
  					var store=Ext.getCmp("searchResultGridId").store;
  					for(var j=0;j<selection.length;j++){//遍历所选客户
  						if(store.find("contactId",selection[j].get("contactId"))!=-1){//判断是否有重复
						    Ext.getCmp("ChooseCustomerGridId").getSelectionModel().deselect(selection[j]);
	  						Ext.getCmp("ChooseCustomerGridId").store.remove(selection[j]);
					    }else{
					    	Ext.getCmp("ChooseCustomerGridId").getSelectionModel().deselect(selection[j]);
	  						Ext.getCmp("ChooseCustomerGridId").store.remove(selection[j]);
	  						//添加到已选择客户store里
	  						store.insert(j,selection[j]);
					    }
  					}
  					if(Ext.getCmp("ChooseCustomerGridId").store.getCount()==0){
  						Ext.getCmp("ChooseCustomerGridId").getSelectionModel().deselectAll();
  					}
  				}
  			}]
  		}]
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
  		searchLeftResult:null, //查询客户列表（左边Grid）
  		searchRightResult:null, //已选择客户列表（右边Grid）
  		downPlanformPanel:null,//底部开发计划formPanel
  		items:null,
  		layout:'border',
  		initComponent:function(){
  			var me = this;
//  			//查询客户列表store
  			var store=Ext.create('DeliverySearchConditionStore',{id:'deliverySearchConditionStore'});
  			store.on('beforeload',function(th,operation,e){
				var searchParams = {
					'searchCustomerCondition.custNumbers':custNum,
					'searchCustomerCondition.type':'1'
				};
				Ext.apply(operation,{
					params : searchParams
				});	
			});
  			var selModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SIMPLE',id:'selModelId'});
  			//查询结果Grid
  			me.searchLeftResult =  Ext.create('PopupGridPanel',{
  				id:'searchResultGridId',
  				store:store,
  				columns:me.getColumns(),
  				selModel:selModel,
  				viewConfig:{//可拖动插件
  					forceFit:true
  				},
  				
  			    listeners: {
  			    	//消除表格的滚动条时而出现时而不出现的问题
  			    	scrollershow: function(scroller) {
  			    		if (scroller && scroller.scrollEl) {
  			    				scroller.clearManagedListeners(); 
  			    				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
  			    		}
  			    	}
  			    },
  				dockedItems:[{
  					xtype:'pagingtoolbar',
  					store:store,
  					dock:'bottom',
  					cls:'pagingtoolbar',
  					displayInfo : true,
  					autoScroll : true,
  					items:[{
  						text: '每页',
  						xtype: 'tbtext'
  					},Ext.create('Ext.form.ComboBox', {
  		               width:          window.screen.availWidth*0.0391,
  		               value:          '10',
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
  			
  			
  			var chooseSelModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SIMPLE',id:'chooseSelModelId'});
  			//查询已选择客户列表store
  			var chooseStore=Ext.create('DeliverySearchCustbyIdStore',{id:'deliverySearchCustbyIdStore'});
  			
  			var searchRightGrid=Ext.create('PopupGridPanel',{
  				id:'ChooseCustomerGridId',
  				store:chooseStore,
  				columns:me.getColumns(),
  			    listeners: {
  			    	scrollershow: function(scroller) {
  			    		if (scroller && scroller.scrollEl) {
  			    				scroller.clearManagedListeners(); 
  			    				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
  			    		}
  			    	}
  			    },
  				selModel:chooseSelModel,
  				viewConfig:{//可拖动插件
  					forceFit:true
  				}
  			});
  			
  			me.searchRightResult =  searchRightGrid;
			me.downPlanformPanel=Ext.create('SavePlanPanel',{id:'planFromPanel'});//制定计划
  			//设置items
  			me.items = me.getItems();
  			Ext.getCmp("planFromPanel").getForm().findField("execuserid").store.load();//加载执行人DetailPanel
  			
  			
  			store.on('load',function(store,records){
  				var girdcount=0;
		        store.each(function(record){
		            if(chooseStore.find("contactId",record.get("contactId"))!=-1){
						var cells =  Ext.getCmp('searchResultGridId').getView().getNodes()[girdcount].children;
						for(var i= 0;i<cells.length;i++){
							cells[i].style.backgroundColor='#FF9797';
						};
		 			};	
		 			girdcount=girdcount+1;
		        });
  			});
  			
  			this.callParent();
  		},
  		getItems:function(){//整体布局
  			var me = this;
  			return [{
  					region:'center',
  					xtype:'basicpanel',
  					layout:'border',
  					items:[{
  						region:'center',
  						xtype:'basicpanel',
  						layout:'border',
  						flex:1,
  						items:[{
  								region:'center',
  								xtype:'basicpanel',
  								layout:'fit',
  								items:[me.searchLeftResult]
  							},{
  								region:'east',
  								xtype:'basicpanel',
  								layout:'fit',
  								items:[Ext.create('ButtonPanelRole')]
  							}
  						]
  					},{
  						region:'east',
  						xtype:'basicpanel',
  						layout:'fit',
//  						flex:1,
  						width:220,
  						items:[me.searchRightResult]
  					}]
  				},{
  					region:'south',
  					xtype:'basicpanel',
  					layout:'border',
  					height:160,
  					items:[{
  						region:'center',
  						xtype:'basicpanel',
  						layout:'fit',
  						items:[me.downPlanformPanel]
  					},
  					Ext.create('RightDownButtonPanel')]//border布局下面制定开发计划按钮
  				}]
  		}
  		,getColumns:function(){//查询结果列
  			return [{
  				header : i18n('i18n.PotentialCustManagerView.customerName'),
  				dataIndex : 'custName'
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.contactName'),
  				dataIndex : 'linkManName'
  			}, {
  				header : i18n('i18n.ArrivalCycleView.memberLevel'),
  				dataIndex : 'memberLevel',
  				renderer:function(value){
  					return rendererDictionary(value,DataDictionary.MEMBER_GRADE);
  				}
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.contactPhone'),
  				dataIndex : 'linkManMobile'
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.contactTel'),
  				dataIndex : 'linkManPhone'
  			}, {
  				header : i18n('i18n.ArrivalCycleView.planName'),
  				dataIndex : 'unfinishedPlanName'
  			}, {
  				header : i18n('i18n.MonitorPlan.noExecute'),
  				dataIndex : 'unfinishedPlanNum'
  			},{
  				header : i18n('i18n.ArrivalCycleView.maintainMan'),
  				dataIndex : 'prehuMan'
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.reviTimes'),
  				dataIndex : 'visitNum'
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.finalreviTime'),
  				dataIndex : 'lastVistiTime',
 	            renderer : function(value){
 	            	if(!Ext.isEmpty(value)){
 	            		return DButil.renderDate(value);
 	            	}
	            }
  			}]
  		}
  	});
  		  
/**
 * 显示制定、修改计划
 */
Ext.define('DeliveryDevelopPopWindow',{
	extend:'PopWindow',
	alias : 'widget.basicwindow',
	width:820,
	height:500,
	modal:true,
	layout:'fit',
	title:i18n('i18n.Cycle.makePlan'),
	closeAction:'hide',
	items:[Ext.create('CustomerDevelopPlanePanel')],
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

var deliveryDevelopPopWindow=Ext.getCmp("deliveryDevelopPopWindow");//获取win
if(!deliveryDevelopPopWindow){
	deliveryDevelopPopWindow=Ext.create('DeliveryDevelopPopWindow',{id:'deliveryDevelopPopWindow'});
}

/*===================制定日程弹出框=======================*/

Ext.define('CustomerScheduleAddPanel',{
		extend:'BasicPanel',
		searchLeftResult:null, //查询客户列表（左边Grid）
		items:null,
		layout:'fit',
		initComponent:function(){
			var me = this;
			//查询客户列表store
			var store=Ext.create('DeliveryScheDuleStore',{id:'DeliveryScheDuleStore'});
			store.on('beforeload',function(store,operation,e){
				//设置请求参数
  				var searchParams = {
					'searchCustomerCondition.custNumbers':custNum,
					'searchCustomerCondition.type':'1'
  				};
				Ext.apply(operation,{
					params : searchParams
				});
			});
			var selModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE'});
			var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
				 clicksToMoveEditor: 1,
				 clicksToEdit:1,
     		 	 autoCancel: false
			});
			
			rowEditing.on('edit',function(th,e,eop){
				var record=th.record;
				//盛诗庆修改，对疑似重复以及存在商机的客户不允许指定日程
				var isRepeat = record.get('repeatCust');//疑似重复
		    	var status = record.get('businessOpportunityStatus');//商机状态
					if(status == 'ONGOING' || status == 'EXTENDED' || status == 'DORMANT'){//有未关闭的商机状态，不允许指定新计划
						 MessageUtil.showErrorMes('['+record.get("contactName")+']'+'存在未关闭的商机,不能制定日程！');
						 return false;
		 			} 
	  				if(isRepeat == '1'){//疑似重复客户
						 MessageUtil.showErrorMes('['+record.get("contactName")+']'+'是'+'['+record.get("repeatCustDeptName")+']'+'疑似重复客户,不能制定日程！');
						 return false;
		 			}
				var id = record.get('scheduleId');
				var scheduleDate = record.data.scheduleDate;
				var planId = record.data.planId;
				var custId = record.data.custId;
				var contactId=record.data.contactId;
				//保存成功回调函数
				var successFn = function(json){
					//Ext.getCmp("searchScheduleGridId").store.loadPage(1);
					record.commit();
					MessageUtil.showInfoMes("保存成功！");
				};
				
				//保存失败回调函数
				var failureFn = function(json){
					MessageUtil.showErrorMes(json.message);
				};
				var params={
						'schedule.id':id,
						'schedule.time':scheduleDate,
						'schedule.planId':planId,
						'schedule.contactId':contactId,
						'schedule.custId':custId,
						'schedule.type':MAINTAIN_TYPE//维护
					};
				DevelopScheduleData.prototype.saveSchedule(params, successFn, failureFn);
			});
			
			//查询结果Grid
			me.searchLeftResult =  Ext.create('SearchGridPanel',{
				id:'searchScheduleGridId',
				cls:'popmarket',//*******
				selModel:selModel,
				store:store,columns:me.getColumns(),
				'plugins':rowEditing,
  				dockedItems:[{
  					xtype:'pagingtoolbar',
  					store:store,
  					dock:'bottom',
  					displayInfo : true,
  					autoScroll : true
  				}]
			});
			
			//设置items
			me.items = me.getItems();
			
			this.callParent();
		},
		getItems:function(){//整体布局
			var me = this;
			return [me.searchLeftResult];
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
  				width:100
			},{
				header : i18n('i18n.developSchedule.linkManName'),
				dataIndex : 'contactName',
				width:100
			},{
				header : i18n('i18n.developSchedule.linkManMobeilPhone'),
				dataIndex : 'contactMobile',
				width:100
			},{
				header : i18n('i18n.developSchedule.linkManTelePhone'),
				dataIndex : 'contactTel',
				width:120
			},{
				header : i18n('i18n.developSchedule.scheduleDate'),
				dataIndex : 'scheduleDate',
				renderer : DButil.renderDate,
				editor: {
	                xtype: 'datefield',
	                editable: false,
	                format: 'Y-m-d'
           	   },
           	   width:100
			},{
				header : i18n('i18n.developSchedule.unfinishedPlanNum'),
				dataIndex : 'unfinishedPlanNum',
				width:120
			},{
				header : i18n('i18n.developSchedule.unfinishedPlanName'),
				dataIndex : 'unfinishedPlanName',
				width:120
			},{
				header : i18n('i18n.developSchedule.visitNum'),
				dataIndex : 'visitNum',
				width:100
			},{
				header : i18n('i18n.developSchedule.lastVisitDate'),
				dataIndex : 'lastVisitDate',
				renderer : DButil.renderDate,
				width:120
			}]
		}
	});

/**
 * 显示制定、修改计划
 */
Ext.define('DeliverySchedulePopWindow',{
	extend:'PopWindow',
	alias : 'widget.basicwindow',
	width:820,
	height:500,
	modal:true,
	layout:'fit',
	title:i18n('i18n.Cycle.makeSchedule'),
	closeAction:'hide',
	items:[Ext.create('CustomerScheduleAddPanel')],//
	buttons:[{
		xtype:'button',
		text:i18n('i18n.DevelopManageView.close'),
		handler:function(){
			Ext.getCmp("deliverySchedulePopWindow").close();
		}
	}]
});

var deliverySchedulePopWindow=Ext.getCmp("deliverySchedulePopWindow");//获取win
if(!deliverySchedulePopWindow){
	deliverySchedulePopWindow=Ext.create('DeliverySchedulePopWindow',{id:'deliverySchedulePopWindow'});
}



/**
 * 描述：单击页面的回访按钮后弹出的回访信息录入窗体
 * 肖红叶
 * 2012-10-17
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
var returnVisitWin=Ext.getCmp("CreateDevelopPopWindowId");//获取win
//判断此窗体在页面中是否已经存在
if(!returnVisitWin){
	returnVisitWin=Ext.create('CreateDevelopPopWindow',{id:'CreateDevelopPopWindowId'});
}

/**
 * 描述：单击页面的本月到货金额明细按钮后弹出的窗体
 * 肖红叶
 * 2013-11-20
 */
Ext.define('ArriveAmountDetailGrid',{
	extend:'SearchGridPanel', 
	/**
	 * @auth 苏玉军
	 * @desctiption 到货明细新增日期查询条件
	 */
	tbar : [{
		id:'queryDetailDate',
		name:'queryDetailDate',
		xtype:'datefield',
		fieldLabel:i18n('i18n.Cycle.queryDate'),
		allowBlank:false,
		format:'Y-m-d'
	},{
		xtype : 'button',
		text:i18n('i18n.developSchedule.search'),
		handler:function(){
			var params = {
					'condition.queryDate' : Ext.getCmp('queryDetailDate').getValue(),
					//发货标志
					'condition.fcAnalyseType' :CYCLE_ARRIVAL
				};
				var successFn = function(json){
					//获取数据
					var ArriveAmountDetailGrid = Ext.getCmp("arriveAmountDetailGridId");
					var ArriveAmountDetailStore = ArriveAmountDetailGrid.store;
					//显示数据
					ArriveAmountDetailGrid.reconfigure(ArriveAmountDetailStore,json.columModel);
					ArriveAmountDetailGrid.scrollByDeltaX(-1);
					//设置每次查询时，将当前页数设置成第一页
					ArriveAmountDetailStore.loadPage(1);
				};
				var failureFn = function(json){
					MessageUtil.showErrorMes(json.message);
				};
				//获取列头
				CycleData.prototype.queryAmountDetailHeader(params,successFn,failureFn);
		}
	}],
	columns:null,
	initComponent:function(){             
		var me = this;
		var store = Ext.create('CycleAmountDetailListStore',{
			listeners:{
				beforeload:function(store,operation,e){
					var customerInfoFormPanel =  Ext.getCmp('customerInfoFormPanelByCycle');
					//获取选中的记录
		        	var selection = Ext.getCmp('cycleListGrid').getSelectionModel().getSelection();
		        	//待查看的客户编码列表
					var customerNumberList = new Array();
					for(var i=0;i < selection.length;i++){
						customerNumberList[i]=selection[i].get('custNumber');
					}
					
					var searchParams = {
						'custGroupDayDetailCondition.custNumbers' : customerNumberList,
						'custGroupDayDetailCondition.searchDate' : Ext.getCmp('queryDetailDate').getValue(),
						'custGroupDayDetailCondition.fcAnalyseType' : CYCLE_ARRIVAL,
						//苏玉军
						'custGroupDayDetailCondition.sendGoodsType':customerInfoFormPanel.getForm().findField('sendGoodsType').getValue()
					};
					Ext.apply(operation,{
						params : searchParams
					});
				}
			}
		});
		me.store = store;
		me.columns = [{
			xtype:'rownumberer',
			width:40,
			align:'center',
			header:i18n('i18n.Cycle.rownum')
		},{    
			header : i18n('i18n.Cycle.custId'),
			width:80,
			align:'center',
			dataIndex:'custNumber'
		},{
			header : i18n('i18n.Cycle.custName'),
			width:80,
			align:'center',
			dataIndex:'custName'
		},{
			header : i18n('i18n.Cycle.empName'),
			width:80,
			align:'center',
			dataIndex:'empName'
		},{
			header : i18n('i18n.Cycle.sendGoodCycle'),
			width:100,
			align:'center',
			dataIndex:'sendGoodCycle'
		},{
			header : i18n('i18n.Cycle.monthAmount'),
			flex:0.3,
			align:'center',
			dataIndex:'monthAmount'
		},{
			header : i18n('i18n.Cycle.month1Amount'),
			flex:0.3,
			align:'center',
			dataIndex:'month1Amount'
		},{
			header : i18n('i18n.Cycle.month2Amount'),
			flex:0.4,
			align:'center',
			dataIndex:'month2Amount'
		}];
		this.callParent();
	}
});

Ext.define('ArriveAmountDetailPopWindow',{
	extend:'PopWindow',

	title:i18n('i18n.Cycle.arrival')+i18n('i18n.Cycle.amountDetail'),
	alias : 'widget.basicwindow',
	width:800,
	height:600,
	modal:true,
	layout:'fit',
	closeAction:'hide',
	items:[Ext.create('ArriveAmountDetailGrid',{'id':'arriveAmountDetailGridId'})],
	buttons:[{
		text:i18n('i18n.DevelopManageView.close'),
		handler:function(){
			this.up('window').hide();
		}
	}],
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



/*
 * 描述：点击回访按钮后弹出的联系人列表grid
 *肖红叶
 *2012-10-16 
*/
Ext.define('LinkManNameListPanel',{
		extend:'BasicPanel',
		searchLeftResult:null, 
		items:null,
		layout:'fit',
		
		initComponent:function(){
			var me = this;
			
			//查询客户列表store
			var store=Ext.create('DeliveryScheDuleStore',{id:'DeliveryScheDuleStore'});
			store.on('beforeload',function(store,operation,e){
				var cycleListGrid = Ext.getCmp("cycleListGrid");
				var selection = cycleListGrid.getSelectionModel().getSelection();
				//新建一个数组，用以存储选中的列表记录信息
				custNum = [];
				for(var i = 0;i<selection.length;i++){
					custNum.push(selection[i].data.custNumber);
				}
				//设置请求参数
  				var searchParams = {
					'searchCustomerCondition.custNumbers':custNum,
					'searchCustomerCondition.type':'1'
  				};
				Ext.apply(operation,{
					params : searchParams
				});
			});
			
			//查询结果联系人列表Grid
			me.searchLeftResult =  Ext.create('SearchGridPanel',{
				id:'searchLinkManResultGridId',
				cls:'popmarket',
				store:store,
				columns:me.getColumns(),
				
				//为表格中的每一条记录添加双击事件，双击一行记录后，弹出回访界面
				listeners:{
					'itemdblclick':function(th,record){
						var status = record.get("businessOpportunityStatus");//商机状态
			 			var isRepeat = record.get("repeatCust");//疑似重复
						if(isRepeat == '1'){
							MessageUtil.showErrorMes('客户'+'['+record.get('custName')+']是'+'['+record.get('repeatCustDeptName')+']'+'疑似重复客户不能回访！');
							return;
						}
			 			if(status == 'ONGOING' || status == 'EXTENDED' || status == 'DORMANT'){//有未关闭的商机状态，标红
			 				MessageUtil.showErrorMes('客户'+'['+record.get('custName')+']存在未关闭商机不能回访！');
							return;
			 			}
						var successFn = function(json){
						    var initData=new InitDataModel(json.returnVisit);
					    	var customerInfoFormPanel = Ext.getCmp("customerInfoFormPanel").getForm();
					    	//为客户基本信息赋初值
					    	customerInfoFormPanel.loadRecord(initData);
						 	var ScheduleMakeForm =  Ext.getCmp("scheduleMakeForm").getForm();
						 	ScheduleMakeForm.loadRecord(initData);
					    	returnVisitWin.show();
							document.getElementsByTagName("html")[0].style.overflowY="auto";
							document.getElementsByTagName("html")[0].style.overflowX="auto";
							viewport.doLayout();
					    };
						var failureFn = function(json){
							MessageUtil.showErrorMes(json.message);
						};
						
						//根据客户Id查询客户信息
						DevelopScheduleData.prototype.setRetureVisitByCust({
							'returnVisit.linkManId':record.get("contactId"),
							'returnVisit.scheType':MAINTAIN_TYPE,//维护
							'returnVisit.psCustomerId':record.get("custId")
						}, successFn, failureFn);
						
						//清空走货潜力表格
						Ext.getCmp('sendGoodsPontentialGrid').store.removeAll();
				    	//清空客户意见表格
				    	Ext.getCmp('customerOpinionGrid').store.removeAll();
				    	//清空跟踪时间和跟踪方式
				    	Ext.getCmp('schedule').setValue(null).disable();
				    	Ext.getCmp("ifparent").setValue('0');
					}
				},
				
				//分页显示的实现
  				dockedItems:[{
  					xtype:'pagingtoolbar',
  					store:store,
  					dock:'bottom',
  					displayInfo : true,
  					autoScroll : true
  				}]
			});
			
			//设置items
			me.items = me.getItems();
			
			this.callParent();
		},
		
		
		
		getItems:function(){//整体布局
			var me = this;
			return [me.searchLeftResult];
		},
		getColumns:function(){//查询结果列
			return [{
					xtype:'rownumberer',
					width:40,
					align:'center',
					header:i18n('i18n.Cycle.rownum')
				},
				{
					header : i18n('i18n.fiveKilometreMap.customerName'),
					dataIndex : 'custName',
					renderer:function(val){
						return '<span data-qtip="'+val+'">'+val+'</span>';
					},
					flex:0.25
				},
				{
				header : i18n('i18n.developSchedule.linkManName'),
				dataIndex : 'contactName',
				flex:0.25
			},{
				header : i18n('i18n.developSchedule.linkManMobeilPhone'),
				dataIndex : 'contactMobile',
				flex:0.25
			},{
				header : i18n('i18n.developSchedule.linkManTelePhone'),
				dataIndex : 'contactTel',
				flex:0.25
			}]
		}
	});

/**
 * 描述：单击页面回访按钮弹出的选择联系人窗体
 * 肖红叶
 * 2012-10-16
 */
Ext.define('ReturnVisitPopWindow',{
	extend:'PopWindow',
	alias : 'widget.basicwindow',
	width:600,
	height:300,
	modal:true,
	layout:'border',
	title:'选择联系人',
	closeAction:'hide',
//	maximizable:true,
//	minimizable:true,
	items:[//在弹出窗体中根据客户编码加载联系人列表
	       {
	    	   region:'north',
	    	   html:'<span style="color: #FF0000">请双击选择一个回访联系人！</span>',
	    	   height:25
	       },{
	    	   region:'center',
	    	   items:Ext.create('LinkManNameListPanel'),
	    	   layout:'fit'
	       }],
	buttons:[{
		xtype:'button',
		text:i18n('i18n.DevelopManageView.close'),
		handler:function(){
			Ext.getCmp("returnVisitPopWindow").close();
		}
	}]
});
//创建选择联系人窗体
var returnVisitPopWindow=Ext.getCmp("returnVisitPopWindow");//获取win
if(!returnVisitPopWindow){
	returnVisitPopWindow=Ext.create('ReturnVisitPopWindow',{id:'returnVisitPopWindow'});
}

/**
 *将界面显示到viewport中 
 */
var viewport=Ext.create('Ext.Viewport',{
	layout : 'border',
	autoScroll:true,
	items:[Ext.create('CyclePanel',{region:'center','id':'cyclePanel'})]
});
viewport.setAutoScroll(false);
viewport.doLayout();

//设置默认部门
var deptModel=new DeptModel();
deptModel.set("id",User.deptId);
deptModel.set("deptName",User.deptName);

Ext.getCmp("customerInfoFormPanelByCycle").getForm().findField("deptId").store.add(deptModel);
Ext.getCmp("customerInfoFormPanelByCycle").getForm().findField("deptId").setValue(User.deptId);
});
/**
 * 问卷管理查看详情问题列表 2014-3-6 肖红叶
 */
Ext.define('QuestionListGrid',{
	extend:'PopupGridPanel',   
	columns:null,
	store:null,
	defaults:{
		align:'center'
	},
	initComponent:function(){             
		var me = this;
		var store = Ext.create('QuestionInfoListStore');
		store.on('beforeLoad',function(store,operation,e){
		    var param = {
	    		// 问卷id列表
	    		'questionnaireIds':questionnaireIdListForWhole,
	    		// 问题id列表
	    		'questionIdList':questionIdListForWhole
		    };
			Ext.apply(operation,{
				params : param
			});
		});
		me.store = store;
		me.columns = [{// 问卷id
			header : '问题列表',
			flex:1,
			dataIndex:'id',
			renderer:function(val,metaData,record){
				// 判断是不是在渲染表格记录的第一行
				var timeFirst = true;
				// 问题类型转化
				var questionType = optionInfoConvert(record.get('questionType'));
				// 根据问题类型显示适当的表单符号
				var optionDisplay = optionDisplayConvert(record.get('questionType'));
				if(!Ext.isEmpty(record)){
					// 单选题与多选题的显示样式转化
					if(record.get('questionType') != i18n('i18n.questionnaireManage.questionTypeOfAnswer')){
						// 记录有多少个选项
						var length = 0;
						if(!Ext.isEmpty(record.optionsStore) && !Ext.isEmpty(record.optionsStore.data)){
							length = record.optionsStore.data.length;
						}
						var tableString = '<div align = "left"><table>';
						for(var i = 0;i < length;i++){
							var optionsData = record.optionsStore.data.items[i].data;
							var optionSeq = optionCodeConvert(optionsData.optionSeq);
							var optionDes = optionsData.optionDes;
							var j = optionDes.length;
							var showString = "";
							while(j > 50){
								showString=showString+optionDes.substring(0,50)+'<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
								optionDes = optionDes.substring(50,j);
								j =j - 50;
							}
							showString = showString + optionDes;
							tableString =tableString + '<tr><td height="25">&nbsp;&nbsp;'+optionDisplay+'&nbsp;&nbsp;'+optionSeq+'&nbsp;&nbsp;'+showString
							+'</td></tr>';
						}
						tableString = tableString + '</table></div>';
					}
					else{// 简答题的显示方式
						var tableString = '<br><div align = "left">&nbsp;&nbsp;&nbsp;&nbsp;<textarea style="width:550px; height:50px; border:solid 1px #000;" readonly="readonly"></textarea></div>';
					}
				}
				// 格式化结果显示
				var questionTitle =record.get('questionTitle');
				var k = questionTitle.length;
				var title = "";// 问题标题临时变量
				while(k > 50){
					title=title+questionTitle.substring(0,50)+'<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
					questionTitle = questionTitle.substring(50,k);
					k =k - 50;
				}
				title = title + questionTitle;
				return	Ext.String.format(
					'<br><p><b>{0}、{1}({2})</b></p><br><br>'+tableString+'<br><br>',
					// 传入的参数列表
					record.get('questionSeq'),
					title,
					questionType
				);
			}
		}];
		this.callParent();
	}
});
/**
 * 问卷查看详情显示Form 肖红叶 20140307
 */
Ext.define('QuestionnaireDetailForm',{
	extend:'NoTitleFormPanel', 
	items:null,
	border:0,
	layout:{
		type:'table',
		columns:3
	},
	defaults:{
		labelWidth:60,
		labelAlign:'right',
		width:200,
		margin:'0 5 5 0'
	},
	defaultType:'datefield',
	initComponent:function(){			
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{// 问卷编号
			xtype:'readonlytextfield',
	    	fieldLabel : '问卷编号',
			name:'questionnaireCode'
	    },{// 问卷名称
			xtype:'readonlytextfield',
	    	fieldLabel : '问卷名称',
			name:'questionnaireName'
	    },{// 适用范围
			xtype:'combobox',
			fieldLabel:'适用范围',
			name:'useRange',
			cls:'readonly',
			typeAhead: true,
		    triggerAction: 'all',
		    queryMode: 'local',
		    store: getDataDictionaryByName(DataDictionary,'SURVEY_RANGE'),
		    valueField: 'code',
		    displayField: 'codeDesc',
			forceSelection :true,
            allowBlank: false,
			listeners:{
				change:DButil.comboSelsct
			},
			readOnly:true
		},{// 生效时间
	    	fieldLabel:'生效时间',
	    	format:'Y-m-d',
			name:'effectiveTime',
			// 设置起始时间的初始值为当前月份的第一天
			readOnly:true,
			cls:'readonly'
		},{// 失效时间
	    	fieldLabel:'失效时间',
	    	format:'Y-m-d',
			name:'invalidTime',
			readOnly:true,
			cls:'readonly'
		},{// 采用次数
			xtype:'readonlytextfield',
	    	fieldLabel : '采用次数',
			name:'useTimes',
			value:'0'
	    },{// 问卷描述
        	xtype:'readonlytextarea',
            width:700,
            name : 'desc',
            fieldLabel: '问卷描述',
            height:50,
            colspan : 3
        }];
	}	
});
/**
 * 问卷管理查看详情显示的window 肖红叶 20140307
 */
Ext.define('QuestionnaireDetailPopWindow',{
	extend:'PopWindow',
	alias : 'widget.basicwindow',
	width:820,
	height:700,
	modal:true,
	layout:'border',
	title:'问卷详情',
	closeAction:'hide',
	items:[Ext.create('QuestionnaireDetailForm',{
		id:'questionnaireDetailFormId',
		region:'north',
		height:120
	}),Ext.create('QuestionListGrid',{
		id:'questionListGridId',
		region:'center'
	})],
	buttons:[{
		text:'关闭',
		handler:function(){
			this.up('window').hide();
		}
	}],
});