var developScheduleData =  new DevelopScheduleData();//初始化计划新增修改data
Ext.onReady(function(){
	var keys=[
  	    //客户来源      
  	    'CUST_SOURCE',
  	    //客户类型
  	    'CUST_TYPE',
		// 行业
		'TRADE',
		//二级行业
		'SECOND_TRADE',
		'SCHEDULE_STATUS',
		// 商机状态
		'BUSINESS_STATUS',
		// 合作物流
		'COOPERATION_LOGISTICS',
		// 合作意向
		'COOPERATION_INTENTION',
		// 货量潜力
		'CARGO_POTENTIAL',
		// 开发状态
		'DEVELOP_STATUS',
		//客户属性
		'CUSTOMER_NATURE',
		//客户类型
		'CUSTOMER_TYPE',
		//公司性质
		'COMP_NATURE',
		//上一年公司规模
		'FIRM_SIZE',
		// 货量潜力
		'CARGO_POTENTIAL',
  		//营销方式
  		'DEVELOP_WAY',
		//计划类别
		'PLAN_TYPE',
		//业务类别
		'BUSINESS_TYPE'
	];
	//初始化业务参数
	initDataDictionary(keys);
	initDeptAndUserInfo();
	var scattercustdetailWindow;
	var potentialcustbasicdetailinfoWin;
	/**
	* 散客基本信息
	*/
	Ext.define('BasicScatterCustForm',{
		extend:'BasicFormPanel',
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [{
				xtype:'fieldset',
				title:i18n('i18n.ScatterCustManagerView.scatterInfo'),
				layout:{
					type:'table',
					columns:3
				},
				defaultType:'textfield',
				defaults:{
					labelWidth:80,//######
					width:210
				},
				items:[{
						fieldLabel:i18n('i18n.PotentialCustManagerView.customerName'),
						name:'custName',
						cls:'readonly ',
						readOnly:true
					   },{
						fieldLabel:i18n('i18n.PotentialCustManagerView.contactName'),
						name:'linkManName',
						cls:'readonly ',
						readOnly:true
					   },{
						fieldLabel:i18n('i18n.ScatterCustManagerView.memberNo'),
						name:'memberNo',
						cls:'readonly ',
						readOnly:true
					   },{//一级行业
							fieldLabel:i18n('i18n.PotentialCustManagerView.firstTrade'),
							xtype:'combobox',
							store:getDataDictionaryByName(DataDictionary,'TRADE'),
							queryMode:'local',
			                forceSelection:true,
							displayField:'codeDesc',
							valueField:'code',
							name:'trade',
							cls:'readonly ',
							readOnly:true
					   },{//二级行业
							fieldLabel:i18n('i18n.PotentialCustManagerView.secondTrade'),
							xtype:'combobox',
							store:getDataDictionaryByName(DataDictionary,'SECOND_TRADE'),
							queryMode:'local',
			                forceSelection:true,
							displayField:'codeDesc',
							valueField:'code',
							name:'secondTrade',
							cls:'readonly ',
							readOnly:true
					   },{
	                       fieldLabel:i18n('i18n.PotentialCustManagerView.contactTel'),
		   				   name:'linkManPhone',
		   				   cls:'readonly ',
		   				   readOnly:true
	                   },{
						fieldLabel:i18n('i18n.PotentialCustManagerView.contactPhone'),
						name:'linkManMobile',
						cls:'readonly ',
						readOnly:true
					   },{
						fieldLabel:i18n('i18n.ScatterCustManagerView.idNumber'),
						name:'idNumber',
						cls:'readonly ',
						readOnly:true
					   },{
						fieldLabel:i18n('i18n.ScatterCustManagerView.companySize'),
						xtype:'combobox',
						store:getDataDictionaryByName(DataDictionary,'FIRM_SIZE'),
						queryMode:'local',
			            forceSelection:true,
						displayField:'codeDesc',
						valueField:'code',
						name:'companySize',
						cls:'readonly ',
						readOnly:true
					   },{
						fieldLabel:i18n('i18n.ScatterCustManagerView.companyProperties'),
						xtype:'combobox',
						store:getDataDictionaryByName(DataDictionary,'COMP_NATURE'),
						queryMode:'local',
			            forceSelection:true,
						displayField:'codeDesc',
						valueField:'code',
						name:'companyNature',
						cls:'readonly ',
						readOnly:true
					   },{
						fieldLabel:i18n('i18n.ScatterCustManagerView.taxId'),
						name:'taxregistNo',
						cls:'readonly ',
						readOnly:true
					   },{
						fieldLabel:i18n('i18n.developSchedule.position'),
						name:'post',
						cls:'readonly ',
						readOnly:true
					   },{
						fieldLabel:i18n('i18n.PotentialCustEditView.city'),
						name:'cityName',
						cls:'readonly ',
						readOnly:true
					   }
					   ,{
						fieldLabel:i18n('i18n.PotentialCustEditView.address'),
						colspan:2,
						width:420,
						name:'address',
						cls:'readonly ',
						readOnly:true
					   },{
						fieldLabel:i18n('i18n.ScatterCustManagerView.custAttribute'),
						xtype:'combobox',
						store:getDataDictionaryByName(DataDictionary,'CUSTOMER_NATURE'),
						queryMode:'local',
			            forceSelection:true,
						displayField:'codeDesc',
						valueField:'code',
						name:'custNature',
						readOnly:true,
						cls:'readonly ',
						readOnly:true
					   },{
						fieldLabel:i18n('i18n.PotentialCustManagerView.bizStatus'),
						xtype:'combobox',
						store:getDataDictionaryByName(DataDictionary,'BUSINESS_STATUS'),
						queryMode:'local',
			            forceSelection:true,
						displayField:'codeDesc',
						valueField:'code',
						name:'bussinesState',
						cls:'readonly ',
						readOnly:true
					   },{
						fieldLabel:i18n('i18n.ScatterCustManagerView.custProperty'),
						xtype:'combobox',
						store:getDataDictionaryByName(DataDictionary,'CUSTOMER_TYPE'),
						queryMode:'local',
			            forceSelection:true,
						displayField:'codeDesc',
						valueField:'code',
						name:'custProperty',
						cls:'readonly ',
						readOnly:true
					   },{
						fieldLabel:i18n('i18n.returnVisit.remark'),
						name:'remark',
						cls:'readonly ',
						colspan:3,
						width:630,
						readOnly:true
				}]
			}];
		}
	});
	/**
	*散客业务信息
	*/
	Ext.define('ScatterBusinessForm',{
		extend:'BasicFormPanel',
		items:null,
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [{
				xtype:'fieldset',
				title:i18n('i18n.ScatterCustManagerView.scatterBusinessInfo'),
				layout:{
					type:'table',
					columns:3
				},
				defaults:{
					labelWidth:80,
					width:210
				},
				items:[{
					fieldLabel:i18n('i18n.PotentialCustEditView.companyName'),
					xtype:'combobox',
					store:getDataDictionaryByName(DataDictionary,'COOPERATION_LOGISTICS'),
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					name:'recentcoop',
					cls:'readonly ',
					readOnly:true
				},{
					fieldLabel:i18n('i18n.PotentialCustManagerView.coopIntention'),
					xtype:'combobox',
					store:getDataDictionaryByName(DataDictionary,'COOPERATION_INTENTION'),
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					name:'coopIntention',
					cls:'readonly ',
					readOnly:true
				},{
					fieldLabel:i18n('i18n.developSchedule.sendGoodsPontential'),
					xtype:'combobox',
					store:getDataDictionaryByName(DataDictionary,'CARGO_POTENTIAL'),
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					name:'volumePotential',
					cls:'readonly ',
					readOnly:true
				},{
					fieldLabel:i18n('i18n.PotentialCustEditView.goodsTrendCondition'),
					xtype:'textareafield',
					name:'recentGoods',
					cls:'readonly ',
					readOnly:true
				},{
					fieldLabel:i18n('i18n.PotentialCustEditView.custNeed'),
					xtype:'textareafield',
					colspan:2,
					width:420,
					name:'custNeed',
					cls:'readonly ',
					readOnly:true
				},{
					fieldLabel:i18n('i18n.PotentialCustManagerView.businessType'),
					xtype:'combobox',
					store:getDataDictionaryByName(DataDictionary,'BUSINESS_TYPE'),
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					name:'businessType',
					cls:'readonly ',
					readOnly:true
				}]
			}];
		}
	});
	
	//潜客基本信息
	Ext.define('PotentialCustBasicDetailInfoForm',{
		extend:'BasicFormPanel',
		defaultType:'textfield',
		layout:'fit',
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();
		},
		//设置items
		getItems:function(){
			var me = this;
			var items = [{
				xtype:'fieldset',
				title:i18n('i18n.ScatterCustManagerView.scatterInfo'),
				defaults:{
					labelWidth:70,
					width:200
				},
				layout:{
		         	type:'table',    
		         	columns : 3         
		        },
		        defaultType:'textfield',
				items:[{
					fieldLabel:i18n('i18n.PotentialCustManagerView.customerName'),
					name:'custName',
					cls:'readonly ',
					readOnly:true
				},{//一级行业
					fieldLabel:i18n('i18n.PotentialCustManagerView.firstTrade'),
					xtype:'combobox',
					store:getDataDictionaryByName(DataDictionary,'TRADE'),
					queryMode:'local',
	                forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					name:'trade',
					cls:'readonly ',
					readOnly:true
				},{//二级行业
					fieldLabel:i18n('i18n.PotentialCustManagerView.secondTrade'),
					xtype:'combobox',
					store:getDataDictionaryByName(DataDictionary,'SECOND_TRADE'),
					queryMode:'local',
	                forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					name:'secondTrade',
					cls:'readonly ',
					readOnly:true
				},{
					fieldLabel:i18n('i18n.PotentialCustManagerView.custSource'),
					xtype:'combobox',
	                forceSelection:true,
	                store:getDataDictionaryByName(DataDictionary,'CUST_SOURCE'),
					queryMode:'local',
					displayField:'codeDesc',
					valueField:'code',
					name:'custbase',
					cls:'readonly ',
					readOnly:true
				},{
					fieldLabel:i18n('i18n.PotentialCustManagerView.contactName'),
					name:'linkManName',
					cls:'readonly ',
					readOnly:true
				},{
                    fieldLabel:i18n('i18n.PotentialCustManagerView.contactTel'),
   				   name:'linkManPhone',
   				   cls:'readonly ',
   				   readOnly:true
                },{
					fieldLabel:i18n('i18n.PotentialCustManagerView.contactPhone'),
					name:'linkManMobile',
					cls:'readonly ',
					readOnly:true
				},{
					fieldLabel:i18n('i18n.PotentialCustManagerView.position'),
					name:'post',
					cls:'readonly ',
					readOnly:true
				},{
					fieldLabel:i18n('i18n.PotentialCustManagerView.bizStatus'),
					xtype:'combobox',
					store:getDataDictionaryByName(DataDictionary,'BUSINESS_STATUS'),
					queryMode:'local',
	                forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					cls:'readonly ',
					readOnly:true,
					name:'bussinesState'
				},{
					fieldLabel:i18n('i18n.PotentialCustEditView.city'),
					name:'cityName',
					cls:'readonly ',
					readOnly:true
				},{
					fieldLabel:i18n('i18n.PotentialCustEditView.address'),
					colspan:2,
					width:400,
					name:'address',
					cls:'readonly ',
					readOnly:true
				}]
			}];
			return items;
		}
	});
	
	Ext.define('ScatterCustDetailWindow',{
		extend:'PopWindow',
		title:i18n('i18n.DevelopScheduleAdd.detail'),
		layout:'fit',
//		y:0,
		height:440,
		width:690,
		closeAction:'hide',
		basicScatterCustInfo:null,     //客户基本信息
		scatterCustBusinessInfo:null,  //客户业务信息
		items:null,
		fbar:null,
		initComponent:function(){
			var  me = this;
			me.basicScatterCustInfo = Ext.create('BasicScatterCustForm',{id:'scatterCustFormId'});
			me.scatterCustBusinessInfo = Ext.create('ScatterBusinessForm',{id:'scatterCustBusinessFormId'});
			//设置items
			me.items = me.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [{
				xtype:'basicformpanel',
				height:350,
				layout:{
			        type:'vbox',
			        align:'stretch'
			    },
				items:
					[{
						xtype:'basicpanel',
						layout:'fit',
						items:[me.basicScatterCustInfo]
					},{
						xtype:'basicpanel',
						layout:'fit',
						items:[me.scatterCustBusinessInfo]
					}]
			 }];
		},
		buttons:[{
			xtype:'button',
			text:i18n('i18n.DevelopManageView.close'),
			handler:function(){
				Ext.getCmp("scatterCustDetailWindowId").close();
			}
		}]
	});
	
	scattercustdetailWindow=Ext.getCmp("scatterCustDetailWindowId");//获取win
	if(!scattercustdetailWindow){
		scattercustdetailWindow=Ext.create('ScatterCustDetailWindow',{id:'scatterCustDetailWindowId'});
	}
	
	Ext.define('PotentialCustbasicDetailinfoWin',{
		extend:'PopWindow',
		title:i18n('i18n.DevelopScheduleAdd.detail'),
		layout:'fit',
		height:390,
		width:690,
		closeAction:'hide',
		basicScatterCustInfo:null,     //客户基本信息
		scatterCustBusinessInfo:null,  //客户业务信息
		items:null,
		fbar:null,
		initComponent:function(){
			var  me = this;
			me.basicScatterCustInfo = Ext.create('PotentialCustBasicDetailInfoForm',{id:'potentialCustFromId'});
			me.scatterCustBusinessInfo = Ext.create('ScatterBusinessForm',{id:'potentialCustBusinessFromId'});
			//设置items
			me.items = me.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [{
				xtype:'basicformpanel',
				height:350,
				layout:{
			        type:'vbox',
			        align:'stretch'
			    },
				items:
					[{
						xtype:'basicpanel',
						layout:'fit',
						height:150,
						items:[me.basicScatterCustInfo]
					},{
						xtype:'basicpanel',
						layout:'fit',
						items:[me.scatterCustBusinessInfo]
					}]
			 }];
		},
		buttons:[{
			xtype:'button',
			text:i18n('i18n.DevelopManageView.close'),
			handler:function(){
				Ext.getCmp("potentialCustbasicDetailinfoWinId").close();
			}
		}]
	});
	
	potentialcustbasicdetailinfoWin=Ext.getCmp("potentialCustbasicDetailinfoWinId");//获取win
	if(!potentialcustbasicdetailinfoWin){
		potentialcustbasicdetailinfoWin=Ext.create('PotentialCustbasicDetailinfoWin',{id:'potentialCustbasicDetailinfoWinId'});
	}
	
	/**
	 * 创建buttonPanel
	 */
	Ext.define('ButtonPanel',{
		extend:'NormalButtonPanel', 
		items:null,
		region:'south',
		width:600,
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [{
				xtype:'leftbuttonpanel',
				width:350,
				defaultType:'button',
				items:[{xtype:'button',text:i18n('i18n.DevelopManageView.find'),
					handler:function(){
						var grid = Ext.getCmp('customerDevelopPlaneGird');
						var selection=grid.getSelectionModel().getSelection();
						//判断是否选中行
						if (selection.length == 0) {
							MessageUtil.showMessage(i18n('i18n.developPlan.choice'));
							return false;
						}
						if (selection.length != 1) {
							MessageUtil.showMessage(i18n('i18n.developPlan.chooseOne'));
							return false;
						}
						//保存成功回调函数
						var successFn = function(json){
							var m=new SearchConditionModel(json.detailInfo);
							if(m.get("custType")==POTENTIAL_CUSTOMER){//潜客
								Ext.getCmp("potentialCustFromId").getForm().loadRecord(m);
								Ext.getCmp("potentialCustBusinessFromId").getForm().loadRecord(m);
								potentialcustbasicdetailinfoWin.show();
							}
							if(m.get("custType")==SCATTER_CUSTOMER){
								Ext.getCmp("scatterCustBusinessFormId").getForm().loadRecord(m);
								Ext.getCmp("scatterCustFormId").getForm().loadRecord(m);
								scattercustdetailWindow.show();
							}
						};
						var failureFn = function(json){
							MessageUtil.showErrorMes(json.message);
						};
						var params={
							'id':selection[0].get("psCustomerId")
						};
						developScheduleData.searchPcScDetail(params, successFn, failureFn);
				}},{
					text:i18n('i18n.developSchedule.delete'),
					handler:function(){
						var me = this;
						var grid;
						var	store;
						if(Ext.getCmp('customerDevelopPlaneGird')!=null){
							grid = Ext.getCmp('customerDevelopPlaneGird');
							//获取store中数据
							store = grid.getStore();
							selection=grid.getSelectionModel().getSelection();
							if (selection.length == 0) {
								MessageUtil.showMessage(i18n('i18n.developSchedule.choice'));
								return false;
							}
							var bj=false;
							for (var j = 0; j < selection.length; j++) {
								if(Ext.isEmpty(selection[j].get('planId'))){//无计划日程
									if(selection[j].get("scheduleStatus")==30){
										bj=true;
										MessageUtil.showMessage(i18n('i18n.developSchedule.deletewarning'));
										return false;
									}
									if(bj){
										break;
									}
								}
							}
							MessageUtil.showQuestionMes(i18n('i18n.developSchedule.isdelete'), function(e) {
								if (e == 'yes') {
									var planeIds = new Array();
									for (var j = 0; j < selection.length; j++) {
										planeIds.push(selection[j].get('id'))
									}
									//保存成功回调函数
									var delSuccessFn = function(result){
										//删除grid中数据
										for (var j = 0; j < selection.length; j++) {
											//将其日期修改为null
											selection[j].set('scheduleDate',null);
										}
										MessageUtil.showInfoMes(i18n('i18n.developSchedule.deleteSuccess'));
										store.load();
									};
									//保存失败回调函数
									var delFailFn = function(result){
										MessageUtil.showErrorMes(result.message);
										store.load();
									};
									var params={'planeIds':planeIds};
									DevelopScheduleData.prototype.deleteSchedule(params,delSuccessFn,delFailFn);
								}
							});
						}
					}
				},{
					text:i18n('i18n.developSchedule.returnVisit'),
					handler:function(){
						var grid = Ext.getCmp('customerDevelopPlaneGird');
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
						var scheduleType = Ext.getCmp("customerScheduleForm").getForm().findField('scheduleType').getValue();
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
						DevelopScheduleData.prototype.setRetureVisit({
							'returnVisit.scheduleId':selection[0].get("id"),
							'returnVisit.linkManId':selection[0].get("linkManId"),
							'returnVisit.memberId':selection[0].get("memberId"),
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
						var grid = Ext.getCmp('customerDevelopPlaneGird');
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

						//点击按钮，弹出营销历史记录查询结果窗口
						marketHistoryRecordWindow.show();
						
					}}]
			},{
				xtype:'middlebuttonpanel' 
			},{
				xtype:'rightbuttonpanel',
				items:[{
					xtype:'button',
					text:i18n('i18n.developSchedule.search'),
					handler:function(){
						//判断界面校验是否通过
						if(!Ext.getCmp('customerScheduleForm').getForm().isValid()){
							MessageUtil.showMessage(i18n('i18n.DevelopManageView.pleaseCondition'));
							return false;
						}
						var startDate = Ext.getCmp("customerScheduleForm").getForm().findField("createStartTime").getValue();
						var endDate = Ext.getCmp("customerScheduleForm").getForm().findField("createEndTime").getValue();
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
								Ext.data.StoreManager.lookup('developScheduleGirdStore').loadPage(1);
							}else{
								MessageUtil.showInfoMes("日程时间结束日期不能为空~");
								return;
							}
						}else{
							MessageUtil.showInfoMes("日程时间起始日期不能为空~");
							return;
						}						
					}
				}]
			}];
		}
	});
	
	Ext.define('CustomerDevelopProgram',{
		extend:'BasicPanel',
		id:'customerDevelopProgram',
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
				header : i18n('i18n.developSchedule.custName'),
				dataIndex : 'custName',
				width:100
			},{
				header : i18n('i18n.developSchedule.linkManName'),
				dataIndex : 'linkManName',
				width:100
			},{
				header : i18n('i18n.developSchedule.linkManMobeilPhone'),
				dataIndex : 'linkManMobeilPhone'
			},{
				header : i18n('i18n.developSchedule.linkManTelePhone'),
				dataIndex : 'linkManTelePhone'
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
				header : i18n('i18n.developPlan.scheduleStatus'),
				dataIndex : 'scheduleStatus',
				renderer:function(value){
					return DButil.rendererDictionary(value,DataDictionary.SCHEDULE_STATUS); 
				},
				width:70
			},{
				header : i18n('i18n.MonitorPlan.execusername'),
				dataIndex : 'executePersion',
				width:70
			},{
				header:i18n('i18n.developPlan.planeName'),
				dataIndex:'planeName'
			},{
				header:i18n('i18n.developSchedule.planeType'),
				dataIndex:'projectType',
				renderer:function(value){
					if(value=='无'){
						return value;
					}
					return DButil.rendererDictionary(value,DataDictionary.PLAN_TYPE); 
				}
			},{
				header : i18n('i18n.plan.beginTime'),
				dataIndex : 'dateLimit',
				width:160
			},{
				header : i18n('i18n.developSchedule.unfinishedPlanNum'),
				dataIndex : 'unfinishedPlanNum'
			},/*{
				header : i18n('i18n.developSchedule.unfinishedPlanName'),
				dataIndex : 'unfinishedPlanName'
			},*/{
				header : i18n('i18n.developSchedule.lastVisitDate'),
				dataIndex : 'lastVisitDate',
				renderer : DButil.renderDate
			},{
				header :i18n('i18n.developSchedule.position'),
				dataIndex : 'position'
			},{
				header :i18n('i18n.developSchedule.cooperatePurpose'),
				dataIndex : 'cooperatePurpose',
				renderer:function(value){
					return DButil.rendererDictionary(value,DataDictionary.COOPERATION_INTENTION); 
				}
			},{
				header : i18n('i18n.developSchedule.sendGoodsPontential'),
				dataIndex : 'sendGoodsPontential',
				width:100,
				renderer:function(value){
					 return DButil.rendererDictionary(value,DataDictionary.CARGO_POTENTIAL);
				}
			},{
				header : i18n('i18n.PotentialCustManagerView.bizStatus'),
				dataIndex : 'businessStatus',
				width:100,
				renderer:function(value){
					 return DButil.rendererDictionary(value,DataDictionary.BUSINESS_STATUS);
				}
			}];
		},
		initComponent:function(){
			var me = this;
			
			//给store添加事件,来在点击查询时，将查询条件传递到后台 
			var developScheduleGirdStore =  Ext.create('DevelopScheduleGirdStore',{'id':'developScheduleGirdStore'});
			developScheduleGirdStore.on('beforeLoad',function(developPlaneGirdStore,operation,e){
				if(!Ext.getCmp('customerScheduleForm').getForm().isValid()){
					MessageUtil.showMessage(i18n('i18n.developSchedule.validate'));
					return false;
				}
				//获取值，判断是否都为null
				var planeName =	 Ext.getCmp('planeName').getValue();
				var scheduleStatus =  Ext.getCmp('scheduleStatus').getValue();
				var executePersion =  Ext.getCmp('executePersion').getValue();
				var projectType = Ext.getCmp('projectType').getValue();//计划类别 auth：kuang
				//获取值，剔除空格
				if(planeName!=null){
					planeName =  Ext.String.trim(planeName);
				}
				if(executePersion!=null){
					executePersion = Ext.String.trim(executePersion);
				}
				if(projectType!=null){  //计划类别 auth：kuang
					projectType =  Ext.String.trim(projectType);
				}
				//判断是否符合查询条件
//				if((planeName==""||planeName==null) && (planeState==null||planeState=="") 
//					&& (executePersion==null||executePersion=="")){
//						MessageUtil.showInfoMes(i18n('i18n.developSchedule.queryParamLimit'));
//						return false;
//				}
				//封装查询条件传递给action
				var searchCustomerScheduleForm = Ext.getCmp("customerScheduleForm");
				var searchParams = {
					'scheduleVO.planId':searchCustomerScheduleForm.getForm().findField('planeName').getValue(),
					'scheduleVO.projectType':searchCustomerScheduleForm.getForm().findField('projectType').getValue(),
					'scheduleVO.scheduleStatus':searchCustomerScheduleForm.getForm().findField('scheduleStatus').getValue(),
					'scheduleVO.executePersion':searchCustomerScheduleForm.getForm().findField('executePersion').getValue(),
					'scheduleVO.createStartTime':searchCustomerScheduleForm.getForm().findField('createStartTime').getValue(),
					'scheduleVO.createEndTime':searchCustomerScheduleForm.getForm().findField('createEndTime').getValue(),
					'scheduleVO.scheduleType':searchCustomerScheduleForm.getForm().findField('scheduleType').getValue(),
					'scheduleVO.executeDeptId':searchCustomerScheduleForm.getForm().findField('deptName').getValue()
				};
				Ext.apply(operation,{
						params : searchParams
				});
			});
			
			var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
				 clicksToMoveEditor: 2,
      		 	 autoCancel: false
			});
    		  	
			rowEditing.on('edit',function(th,e,eop){
				var record=th.record;
				var id = record.get('id');
				var scheduleDate = record.data.scheduleDate;
				var planId = record.data.planId;
				var grid = Ext.getCmp('customerDevelopPlaneGird');
				//获取store中数据
				var	store = grid.getStore();
				
				//保存成功回调函数
				var delSuccessFn = function(result){
					MessageUtil.showInfoMes(i18n('i18n.developSchedule.updateSuccess'));
					store.load();
				};
				//保存失败回调函数
				var delFailFn = function(result){
					MessageUtil.showErrorMes(result.message);
					
					store.load();
				};
				var params={'schedule.id':id,'schedule.time':scheduleDate,'schedule.planId':planId};
				DevelopScheduleData.prototype.updateSchedule(params,delSuccessFn,delFailFn);
			});
						
			var customerDevelopPlaneGird = Ext.create('CustomerDevelopPlaneGird',{'id':'customerDevelopPlaneGird','columns':me.getColumnsItems(),'store': developScheduleGirdStore,'plugins':rowEditing})
			//界面布局
			me.items = [{
				xtype:'basicpanel',
				region:'north',
				layout:'border',
				height:132,
				items:[{
					xtype:'basicpanel',
					region:'center',
					layout:'fit',
					items:[Ext.create('CustomerScheduleForm',{'id':'customerScheduleForm','planeType':DEVELOP_TYPE})]
				},Ext.create('ButtonPanel')]
			},{
				xtype:'basicpanel',
				region:'center',
				layout:'fit',
				items:[customerDevelopPlaneGird]
			}];
			var date = new Date();
			date.setDate(new Date().getDate()-15);
			Ext.getCmp("customerScheduleForm").getForm().findField("createStartTime").setValue(date);
//			date.setDate(new Date().getDate()+15);
			Ext.getCmp("customerScheduleForm").getForm().findField("createEndTime").setValue(new Date().getDate()+15);
			Ext.getCmp("customerScheduleForm").getForm().findField("projectType").show();
			//load计划名称
//			Ext.getCmp("planeName").store.load({params:{'planType':DEVELOP_TYPE}});
			this.callParent();
			//初始化默认部门
			var deptModel=new DeptModel();
			deptModel.set("id",User.deptId);
			deptModel.set("deptName",User.deptName);
			Ext.getCmp("customerScheduleForm").getForm().findField("deptName").store.add(deptModel);
			Ext.getCmp("customerScheduleForm").getForm().findField("deptName").setValue(User.deptId);
			
			//load计划名称
			Ext.getCmp("planeName").store.load({params:{
				'planType':DEVELOP_TYPE,
				'currentDeptId':User.deptId
			}});
		}
	});
	
	/**
	 * 回访窗口
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
	
	/**
	 *将界面显示到viewport中 
	 */
	var viewport=Ext.create('Ext.Viewport',{
		layout : 'border',
		autoScroll:true,
		items:[Ext.create('CustomerDevelopProgram',{region:'center'})]
	});
	
	viewport.setAutoScroll(false);
	viewport.doLayout();
});