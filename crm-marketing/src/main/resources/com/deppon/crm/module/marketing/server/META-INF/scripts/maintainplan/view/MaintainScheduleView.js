Ext.QuickTips.init();
//日程管理主页面
//新建一个全局变量，用以定义回访类型：QUESTIONNAIRE表示问卷回访；LOSTWARN表示流失预警回访；NORMAL或者空表示普通回访；
var returnVisitTypeForWhole = 'NORMAL';
var customerDevelopPlane =  new CustomerDevelopPlaneData();//初始化计划新增修改data
Ext.onReady(function(){
	//加载数据字典
	var keys = [
		//开发状态
		'SCHEDULE_STATUS',
		// 合作意向
		'COOPERATION_INTENTION',
		// 货量潜力
		'CARGO_POTENTIAL',
		// 行业
		'TRADE',
		//二级行业
		'SECOND_TRADE',
		//会员等级
  		'MEMBER_GRADE',
		// 商机状态
		'BUSINESS_STATUS',
		//客户意见(需求分类)
  		'CUSTOMER_IDEA',
  		//维护方式
  		'MAINTAIN_WAY',
		// 货量潜力
		'CARGO_POTENTIAL',
  		//营销方式
  		'DEVELOP_WAY',
  		//新增计划类别
  		'PLAN_TYPE',
  		//问卷适用范围
  		'SURVEY_RANGE'
	];
	//初始化业务参数
	initDataDictionary(keys);
	initDeptAndUserInfo();
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
				width:380,
				defaultType:'button',
				items:[{
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
											selection[j].commit();
										}
										MessageUtil.showInfoMes(i18n('i18n.developSchedule.deleteSuccess'));
										store.load();
									};
									//保存失败回调函数
									var delFailFn = function(result){
										MessageUtil.showErrorMes(result.message);
									};
									var params={'planeIds':planeIds};
									DevelopScheduleData.prototype.deleteSchedule(params,delSuccessFn,delFailFn);
								}
							});
						}
					}
				},{
					text:i18n('i18n.developSchedule.returnVisit'),
					handler:function(btn){
						btn.disable();
						var grid = Ext.getCmp('customerDevelopPlaneGird');
						//获取store中数据
						var	store = grid.getStore();
						var	selection=grid.getSelectionModel().getSelection();
						if (selection.length == 0) {
							MessageUtil.showMessage(i18n('i18n.developSchedule.choice'));
							btn.enable();
							return false;
						}
						if(selection.length>1){
							MessageUtil.showInfoMes(i18n('i18n.developPlan.chooseOne'));
							btn.enable();
							return false;
						}
						//设置回访类型为流失预警客户回访
						if(selection[0].get('planeName').search("流失预警客户") != -1 && selection[0].get('marketRemark')!=null&&
								selection[0].get('marketRemark').search("流失预警") != -1){
							returnVisitTypeForWhole = 'LOSTWARN';
						}
						//设置回访类型为问卷客户回访
						else if(!Ext.isEmpty(selection[0].get('questionnaireId'))){
							returnVisitTypeForWhole = 'QUESTIONNAIRE';
						}
						//设置回访类型为普通类型
						else{
							returnVisitTypeForWhole = 'NORMAL';
						}
						//创建回访窗口
						var win=Ext.getCmp("CreateDevelopPopWindowId");//获取win
						if(!win){
							win=Ext.create('CreateDevelopPopWindow',{
								id:'CreateDevelopPopWindowId'
							});
						}
						
//						win.show();
//						var scheduleType = Ext.getCmp("customerScheduleForm").getForm().findField('scheduleType').getValue();
						var successFn = function(json){
							btn.enable();
						    var initData=new InitDataModel(json.returnVisit);
						    initData.continueMarket = '1';
					    	var customerInfoFormPanel = Ext.getCmp("customerInfoFormPanel").getForm();
					    	customerInfoFormPanel.loadRecord(initData);
					    	customerInfoFormPanel.findField('memberId').setValue(selection[0].get("memberId"));
					    	customerInfoFormPanel.findField('custNumber').setValue(selection[0].get("custNumber"));
						 	var ScheduleMakeForm =  Ext.getCmp("scheduleMakeForm").getForm();
						 	ScheduleMakeForm.loadRecord(initData);
						 	ScheduleMakeForm.findField('questionnaireId').setValue(selection[0].get("questionnaireId"));
						 	ScheduleMakeForm.findField('returnVisitTypeForWhole').setValue(returnVisitTypeForWhole);
//						 	Ext.getCmp('scheType').setValue(scheduleType);

							//加载问卷信息
							if(returnVisitTypeForWhole === 'QUESTIONNAIRE'){
								//获得问卷信息列表的store
								var questionnaireStore = Ext.getCmp('questionnaireQuestionListGridId').store;
								//移除问卷信息列表中的所有内容
								questionnaireStore.removeAll();
								//加载问卷信息列表中的内容
								var questionnaireIdList = new Array();
								questionnaireIdList.push(selection[0].get("questionnaireId"));
								questionnaireStore.load({
									params:{
//										问题id列表
							    		'questionIdList':null,
							    		//问卷id列表
							    		'questionnaireIds':questionnaireIdList
									}
								});
							}
							//加载客户的预警信息
							else if(returnVisitTypeForWhole === 'LOSTWARN'){
								var successFn = function(json){
							    	var lostWarnCustomerInfoFormPanel = Ext.getCmp("lostWarnCustomerInfoFormPanelId").getForm();
							    	var lostWarnCustInfoModel = new LostWarnCustInfoModel(json.lostWarnInfo);
							    	lostWarnCustomerInfoFormPanel.loadRecord(lostWarnCustInfoModel);
							    	//是否季节性客户
							    	if(json.lostWarnInfo.isseason == 1){
							    		lostWarnCustomerInfoFormPanel.findField('isseason').setValue(0);
							    		lostWarnCustomerInfoFormPanel.findField('isseason').setValue(json.lostWarnInfo.isseason);
							    	}else{
							    		lostWarnCustomerInfoFormPanel.findField('isseason').setValue(1);
							    		lostWarnCustomerInfoFormPanel.findField('isseason').setValue(json.lostWarnInfo.isseason);
							    	}
							    	
							    };
								var failureFn = function(json){
									if(Ext.isEmpty(json)){
										MessageUtil.showErrorMes('抱歉，客户的预警信息加载失败！');
									}
									else{
										MessageUtil.showErrorMes(json.message);
									}
								};
								ReturnVisitData.prototype.searchWarnLostCustInfo({
									'custId':selection[0].get("memberId")
								}, successFn, failureFn);
							}
						 	
						 	
							win.show();
							document.getElementsByTagName("html")[0].style.overflowY="auto";
							document.getElementsByTagName("html")[0].style.overflowX="auto";
							viewport.doLayout();
					    };
						var failureFn = function(json){
							btn.enable();
							MessageUtil.showErrorMes(json.message);
						};
						DevelopScheduleData.prototype.setRetureVisit({
							'returnVisit.scheduleId':selection[0].get("id"),
							'returnVisit.linkManId':selection[0].get("linkManId"),
							'returnVisit.memberId':selection[0].get("memberId")
//							'returnVisit.scheType':scheduleType
						}, successFn, failureFn);
						//清空走货潜力表格
						Ext.getCmp('sendGoodsPontentialGrid').store.removeAll();
				    	//清空客户意见表格
				    	Ext.getCmp('customerOpinionGrid').store.removeAll();
				    	//清空跟踪时间和跟踪方式
				    	Ext.getCmp('schedule').setValue(null).disable();
				    	Ext.getCmp("ifparent").setValue('0');
				    	Ext.getCmp("continueMarket").setValue('1');
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
							MessageUtil.showInfoMes(i18n('i18n.developPlan.chooseOne'));
							return false;
						}
						
						
						//获取选中的表格
						if(selection.length==1&&!Ext.isEmpty(selection[0].get("memberId"))){
							custIDForMarketRecord = selection[0].get("memberId");
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
					}},{//客户详情
						xtype:'button',
						text:i18n('i18n.ClientBaseManegerView.detailView'),
						handler:function(){
							var grid = Ext.getCmp('customerDevelopPlaneGird');
							//获取store中数据
							var	store = grid.getStore();
							var	selection=grid.getSelectionModel().getSelection();
							if (selection.length == 0) {
								MessageUtil.showMessage(i18n('i18n.scheduleManagement.selectOneRecord'));
								return false;
							}
							if(selection.length>1){
								MessageUtil.showInfoMes(i18n('i18n.scheduleManagement.selectOneRecord'));
								return false;
							}
							var custType = selection[0].get("custType");
							if(custType == 'RC_CUSTOMER'){//如果客户是固定客户，调用简版360
								CustviewUtil.openSimpleCustview(selection[0].get('custNumber'));
							}else{
								CustviewUtil.openMemberWindow(selection[0].get('memberId'));
							}
						}
					}]
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
								//加载日程信息列表
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
				header : i18n('i18n.Maintainp.memberLevel'),
				dataIndex : 'memberLevel',
  				renderer:function(value){
  					return rendererDictionary(value,DataDictionary.MEMBER_GRADE);
  				},
  				width:70
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
	                editable: false,
	                minValue :new Date(),
	                format: 'Y-m-d'
           	   },
           	   width:95
			},{
				header:i18n('i18n.developPlan.scheduleStatus'),
				dataIndex:'scheduleStatus',
				renderer:function(value){
  					return rendererDictionary(value,DataDictionary.SCHEDULE_STATUS);
  				},
  				width:70
			},{//问卷名称
				header:'问卷名称',
				dataIndex:'questionnaireName',
  				width:120,
  				renderer : function(value,metaData,record,rowIndex ){
  					if(value == null){
  						value = '';
  					}else{
  						return '<a href = "javascript:viewQuestionnaireDetail('+ rowIndex + ')">' +value+ '</a>'
  					}
                	
                }
			},{
				header : i18n('i18n.developPlan.executePersion'),
				dataIndex : 'executePersion',
				width:70
			},{
				header:i18n('i18n.developPlan.planeName'),
				dataIndex:'planeName'
			},
			//添加营销备注字段 by肖红叶
			{
				header:i18n('i18n.developSchedule.marketRemark'),
				dataIndex : 'marketRemark',
				width:120,
				renderer:function(value){
  					if(!Ext.isEmpty(value)){
 						return '<span data-qtip="'+value+'">'+value+'</span>';
 	            	}
  				}
			},{
				header:i18n('i18n.Maintainp.startEnd'),
				dataIndex:'dateLimit',
				width:160
			},{
				header : i18n('i18n.developSchedule.unfinishedPlanNum'),
				dataIndex : 'unfinishedPlanNum'
			},/*{
				header : i18n('i18n.developSchedule.unfinishedPlanName'),
				dataIndex : 'unfinishedPlanName'
			},*/{
				header : i18n('i18n.developSchedule.visitNum'),
				dataIndex : 'visitNum',
				width:70
			},{
				header : i18n('i18n.developSchedule.lastVisitDate'),
				dataIndex : 'lastVisitDate',
				renderer : DButil.renderDate
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
				//获取值，剔除空格
				if(planeName!=null){
					planeName =  Ext.String.trim(planeName);
				}
				if(executePersion!=null){
					executePersion = Ext.String.trim(executePersion);
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
					'scheduleVO.scheduleStatus':searchCustomerScheduleForm.getForm().findField('scheduleStatus').getValue(),
					'scheduleVO.executePersion':searchCustomerScheduleForm.getForm().findField('executePersion').getValue(),
					'scheduleVO.createStartTime':searchCustomerScheduleForm.getForm().findField('createStartTime').getValue(),
					'scheduleVO.createEndTime':searchCustomerScheduleForm.getForm().findField('createEndTime').getValue(),
//					'scheduleVO.scheduleType':searchCustomerScheduleForm.getForm().findField('scheduleType').getValue(),
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
			//日程管理主界面查询结果列表		
			var customerDevelopPlaneGird = Ext.create('CustomerDevelopPlaneGird',
					{'id':'customerDevelopPlaneGird','columns':me.getColumnsItems(),'store': developScheduleGirdStore,'plugins':rowEditing})
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
					items:[Ext.create('CustomerScheduleForm',{'id':'customerScheduleForm','planeType':MAINTAIN_TYPE})]
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
			//date.setDate(new Date().getDate()+15);
			Ext.getCmp("customerScheduleForm").getForm().findField("createEndTime").setValue(new Date().getDate()+15);


//			Ext.getCmp("planeName").store.load({params:{'planType':MAINTAIN_TYPE}});
			this.callParent();
			
			//初始化默认部门
			var deptModel=new DeptModel();
			deptModel.set("id",User.deptId);
			deptModel.set("deptName",User.deptName);
			Ext.getCmp("customerScheduleForm").getForm().findField("deptName").store.add(deptModel);
			Ext.getCmp("customerScheduleForm").getForm().findField("deptName").setValue(User.deptId);
			
			//load计划名称
			Ext.getCmp("planeName").store.load({params:{
				'planType':MAINTAIN_TYPE,
				'currentDeptId':User.deptId
			}});
		}
	});
	
/**
 * 回访页面窗口
 */
Ext.define('CreateDevelopPopWindow',{
	extend:'PopWindow',
	alias : 'widget.basicwindow',
	width:820,
	height:750,
	modal:true,
	layout:'fit',
	closeAction:'destroy',
	initComponent: function() {
		var me = this;
		me.items = [Ext.create('CustomerReturnVisitPanel',{
			'id':'customerReturnVisitPanel',
			returnVisitType:returnVisitTypeForWhole
		})];
		this.callParent();
	},
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
	
	//画界面
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
/**
 * 问卷管理查看详情问题列表
 * 2014-3-6
 * 肖红叶
 */
Ext.define('QuestionListGridForSchedule',{
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
	    		//问卷id列表
	    		'questionnaireIds':questionnaireIdListForWhole,
	    		//问题id列表
	    		'questionIdList':questionIdListForWhole
		    };
			Ext.apply(operation,{
				params : param
			});
		});
		me.store = store;
		me.columns = [{//问卷id 
			header : '问题列表',
			flex:1,
			dataIndex:'id',
			renderer:function(val,metaData,record){
				//判断是不是在渲染表格记录的第一行
				var timeFirst = true;
				//问题类型转化
				var questionType = optionInfoConvert(record.get('questionType'));
				//根据问题类型显示适当的表单符号
				var optionDisplay = optionDisplayConvert(record.get('questionType'));
				if(!Ext.isEmpty(record)){
					//单选题与多选题的显示样式转化
					if(record.get('questionType') != i18n('i18n.questionnaireManage.questionTypeOfAnswer')){
						//记录有多少个选项
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
					else{//简答题的显示方式
						var tableString = '<br><div align = "left">&nbsp;&nbsp;&nbsp;&nbsp;<textarea style="width:550px; height:50px; border:solid 1px #000;" readonly="readonly"></textarea></div>';
					}
				}
				//格式化结果显示
				var questionTitle =record.get('questionTitle');
				var k = questionTitle.length;
				var title = "";//问题标题临时变量
				while(k > 50){
					title=title+questionTitle.substring(0,50)+'<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
					questionTitle = questionTitle.substring(50,k);
					k =k - 50;
				}
				title = title + questionTitle;
				return	Ext.String.format(
					'<br><p><b>{0}、{1}({2})</b></p><br><br>'+tableString+'<br><br>',
					//传入的参数列表
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
 * 问卷查看详情显示Form
 * 肖红叶
 * 20140307
 */
Ext.define('QuestionnaireDetailFormForSchedule',{
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
		return [{//问卷编号
			xtype:'readonlytextfield',
	    	fieldLabel : i18n('i18n.questionnaireManage.questionnaireCode'),
			name:'questionnaireCode'
	    },{//问卷名称
			xtype:'readonlytextfield',
	    	fieldLabel : i18n('i18n.questionnaireManage.questionnaireName'),
			name:'questionnaireName'
	    },{//适用范围
			xtype:'combobox',
			fieldLabel:i18n('i18n.questionManage.searchPanel.questionRange'),
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
		},{//生效时间
	    	fieldLabel:i18n('i18n.questionnaireManage.effectiveTime'),
	    	format:'Y-m-d',
			name:'effectiveTime',
			//设置起始时间的初始值为当前月份的第一天
			readOnly:true,
			cls:'readonly'
		},{//失效时间
	    	fieldLabel:i18n('i18n.questionnaireManage.invalidTime'),
	    	format:'Y-m-d',
			name:'invalidTime',
			readOnly:true,
			cls:'readonly'
		},{//采用次数
			xtype:'readonlytextfield',
	    	fieldLabel : i18n('i18n.questionManage.resultGrid.frequency'),
			name:'useTimes',
			value:'0'
	    },{//问卷描述
        	xtype:'readonlytextarea',
            width:700,
            name : 'desc',
            fieldLabel: i18n('i18n.questionnaireManage.questionnaireDesc'),
            height:50,
            colspan : 3
        }];
	}	
});
/**
 * 问卷管理查看详情显示的window
 * 肖红叶
 * 20140307
 */
Ext.define('QuestionnaireDetailPopWindowForSchedule',{
	extend:'PopWindow',
	alias : 'widget.basicwindow',
	width:820,
	height:700,
	modal:true,
	layout:'border',
	title:'问卷详情',
	closeAction:'hide',
	items:[Ext.create('QuestionnaireDetailFormForSchedule',{
		id:'questionnaireDetailFormForScheduleId',
		region:'north',
		height:120
	}),Ext.create('QuestionListGridForSchedule',{
		id:'questionListGridForScheduleId',
		region:'center'
	})],
	buttons:[{
		text:'关闭',
		handler:function(){
			this.up('window').hide();
		}
	}]
});
/**
 * 显示问卷详情
 * @param id
 */
function viewQuestionnaireDetail(rowIndex){
	var questionnaireDetailPopWindowForSchedule = Ext.getCmp("questionnaireDetailPopWindowForScheduleId");//获取win
	if(!questionnaireDetailPopWindowForSchedule){
		questionnaireDetailPopWindowForSchedule = Ext.create('QuestionnaireDetailPopWindowForSchedule',{id:'questionnaireDetailPopWindowForScheduleId'});
	}
	//获得问卷基本信息面板
	var questionnaireDetailFormForSchedule = Ext.getCmp('questionnaireDetailFormForScheduleId').getForm();
	//重置查看详情页面
	questionnaireDetailFormForSchedule.reset();
	Ext.getCmp('questionListGridForScheduleId').store.removeAll();
	//获取选中行
	var record = Ext.data.StoreManager.lookup('developScheduleGirdStore').getAt(rowIndex);
	//查询问卷详情成功回调函数
	function successFn(json){
		//新建问卷model
		var model = new QuestionnaireInfoModel(json.questionnaireList[0])
		//向form中加载数据
		questionnaireDetailFormForSchedule.loadRecord(model);
	}
	//查询问卷失败回调函数
	function failureFn(json){
		MessageUtil.showErrorMes(json.message);
	}
	customerDevelopPlane.searchQuestionnaireInfoList({'questionnaireQueryCondi.questionnaireId':record.get('questionnaireId'),'start':0,'limit':1},successFn,failureFn);
	questionnaireDetailPopWindowForSchedule.show();
	  //根据问卷id查询已选问题列表
	  //获得问卷查询结果列表
	  var grid = Ext.getCmp('questionnaireInfoListGridForScheduleId');
	  //获得问卷id数组
	  var idArray = new Array();
	  idArray.push(record.get('questionnaireId'));
	  questionnaireIdListForWhole = idArray;
	  questionIdListForWhole = null;
	  //加载查看详情中的问题信息列表
	  Ext.getCmp('questionListGridForScheduleId').store.removeAll();
	  Ext.getCmp('questionListGridForScheduleId').store.load();
}