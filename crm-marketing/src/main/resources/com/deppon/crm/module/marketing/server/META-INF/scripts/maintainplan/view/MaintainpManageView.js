Ext.QuickTips.init();
var customerDevelopPlane =  new CustomerDevelopPlaneData();// 初始化计划新增修改data
// 查看详情时的问卷id列表
var questionnaireIdListForWhole = null;
// 查看详情或者问卷预览时的问题id列表
var questionIdListForWhole = null;
// 计划管理主页面
Ext.onReady(function(){
	var bisYd=null;
	var keys=[
		// 行业
		'TRADE',
		// 二级行业
		'SECOND_TRADE',
		// 客户属性
		'CUSTOMER_NATURE',
		// 客户性质
		'CUSTOMER_TYPE',
		// 会员等级
		'MEMBER_GRADE',
		// 计划状态
		'PLAN_STATUS',
		// 问卷状态
	    'SURVEY_STATUS',
	    // 适用范围
	    'SURVEY_RANGE',
	    // 问题类型
	    'QUESTION_TYPE',
	    // 计划类别标识零担还是快递 盛诗庆
	    'PLAN_TYPE',
	    // 业务类别 标识零担，快递，零担和快递 盛诗庆
	    'CUST_CATEGORY',
	    // 客户类型 潜客，散客，固定客户 
	    'CUST_TYPE',
	    // 客户来源 
	    'CUST_SOURCE',
	    //货量潜力 
	    'CARGO_POTENTIAL',
	    // 合作意向
	    'COOPERATION_INTENTION',
	    // 商机状态
	    'BUSINESS_OPPORTUNITY_STATUS',
	    // 将潜散客商机状态修改为开发阶段 盛诗庆
  		'DEVELOP_STAGE',
  		// 继续营销
  		'CONTINUE_MARKET'
	];
	
  	// 初始化业务参数
  	initDataDictionary(keys);
  	initDeptAndUserInfo();
  	var type;// 后台的计划类型
	// 操作按钮面板
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
				items:[{// 查看详情按钮
					xtype:'button',
					id : 'findButtonId',
					hidden:!isPermission('/marketing/planManageSBtn.action'),
					text:i18n('i18n.DevelopManageView.find'),
					handler:function(){
						var grid = Ext.getCmp('customerDevelopPlaneGird');
						var selection=grid.getSelectionModel().getSelection();
						// 判断是否选中行
						// 判断是否选中行
						if (selection.length == 0) {
							MessageUtil.showMessage(i18n('i18n.developPlan.choice'));
							return false;
						}
						if (selection.length != 1) {
							MessageUtil.showMessage(i18n('i18n.developPlan.chooseOne'));
							return false;
						}
						var successFn = function(json){
						    var createPlanModel=new CreatePlanModel(json.plan);
						    /**
							 * @author 盛诗庆
							 * @description 获取计划信息
							 * @date 2014-01-20
							 */
                            Ext.getCmp("findButtonId").planInfo = createPlanModel.data;
						    var planFromPanel = Ext.getCmp("DetailPanel").getForm();
					    	planFromPanel.loadRecord(createPlanModel);
					    	var fields = planFromPanel.getFields().items;
							for(var i = 0; i < fields.length; i++){  
								fields[i].setReadOnly(true);
							}
							// 设置执行部门执行人
							planFromPanel.findField("execdeptid").setRawValue(selection[0].get("execdeptName"));
							planFromPanel.findField("execuserid").setRawValue(selection[0].get("execdeptUserName"));
							planFromPanel.findField("questionnaireId").setRawValue(selection[0].get("surveyName"));
							planFromPanel.findField("execuserid").disable(); 
							planFromPanel.findField("questionnaireId").disable();
							planFromPanel.findField("beginTime").clearInvalid();
							planFromPanel.findField("endTime").clearInvalid();
						};
						var failureFn = function(json){
							MessageUtil.showErrorMes(json.message);
						};
						customerDevelopPlane.searchPlanById({'planId':selection[0].get("id")}, successFn, failureFn);
						
						Ext.getCmp("detailGridId").store.load({params:{'planId':selection[0].get("id")},callback : function(records){
                        	Ext.getCmp("findButtonId").printData =  records;
                        }});// 加载已经选择的客户
						detailWin.show();
						document.getElementsByTagName("html")[0].style.overflowY="auto";
						document.getElementsByTagName("html")[0].style.overflowX="auto";
						viewport.doLayout();
					}
				},{// 新增按钮
					text:i18n('i18n.developPlan.add'),
					xtype:'button',
					hidden:!isPermission('/marketing/planManageABtn.action'),
					handler:function(){
						win.show();
						var form = Ext.getCmp("SearchConditionFormId").getForm();
						form.reset();// 重置查询条件
						controlForm(form,true);
						Ext.getCmp("planFromPanel").getForm().reset();// 重置表单
						Ext.getCmp("planFromPanel").getForm().findField("questionnaireId").setReadOnly(false);
						Ext.getCmp("searchResultGridId").store.each(function(r){
							Ext.getCmp("searchResultGridId").getSelectionModel().deselect(r);
						});
						Ext.getCmp("ChooseCustomerGridId").store.each(function(r){
							Ext.getCmp("ChooseCustomerGridId").getSelectionModel().deselect(r);
						});
						Ext.getCmp("searchResultGridId").store.removeAll();
						Ext.getCmp("ChooseCustomerGridId").store.removeAll();
						Ext.getCmp("planFromPanel").getForm().findField("beginTime").enable();
						Ext.getCmp("planFromPanel").getForm().findField("endTime").enable();
						Ext.getCmp("planFromPanel").getForm().findField("topic").enable();
						Ext.getCmp("planFromPanel").getForm().findField("activedesc").enable();
						Ext.getCmp("planFromPanel").getForm().findField("questionnaireId").enable();
						
						var deptModel=new DeptModel();
						deptModel.set("id",User.deptId);
						deptModel.set("deptName",User.deptName);
                        Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").store.removeAll();
						Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").store.add(deptModel);
						Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").setValue(User.deptId);
						Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").oldValue=User.deptId;
						Ext.getCmp("planFromPanel").getForm().findField("execdeptid").setValue(User.deptName);
						Ext.getCmp("planFromPanel").getForm().findField("execuserid").store.load({params:{executeDept:User.deptId}});
						document.getElementsByTagName("html")[0].style.overflowY="auto";
						document.getElementsByTagName("html")[0].style.overflowX="auto";
						viewport.doLayout();
					}
				},{// 修改按钮
					text:i18n('i18n.developPlan.update'),
					xtype:'button',
					hidden:!isPermission('/marketing/planManageUBtn.action'),
					handler:function(){
						var grid = Ext.getCmp('customerDevelopPlaneGird');
						
						var selection=grid.getSelectionModel().getSelection();
						// 判断是否选中行
						// 判断是否选中行
						if (selection.length == 0) {
							MessageUtil.showMessage(i18n('i18n.developPlan.choice'));
							return false;
						}
						if (selection.length != 1) {
							MessageUtil.showMessage(i18n('i18n.developPlan.chooseOne'));
							return false;
						}
						if(selection[0].get("status")==40){
							MessageUtil.showMessage(i18n('i18n.developPlan.updatewarning'));
							return false;
						}
						if(selection[0].get("status")==30){// 已完成
							MessageUtil.showMessage(i18n('i18n.developPlan.updatewarningover'));
							return false;
						}
						Ext.getCmp("searchResultGridId").store.each(function(r){
							Ext.getCmp("searchResultGridId").getSelectionModel().deselect(r);
						});
						Ext.getCmp("ChooseCustomerGridId").store.each(function(r){
							Ext.getCmp("ChooseCustomerGridId").getSelectionModel().deselect(r);
						});
						type=selection[0].get("planType");
						var successFn = function(json){
							Ext.getCmp("SearchConditionFormId").getForm().reset();// 重置查询条件
							
							win.show();
							document.getElementsByTagName("html")[0].style.overflowY="auto";
							document.getElementsByTagName("html")[0].style.overflowX="auto";
							viewport.doLayout();
							Ext.getCmp("searchResultGridId").store.removeAll();
							Ext.getCmp("ChooseCustomerGridId").store.load({params:{'planId':selection[0].get("id")},callback : function(){
								var custType = Ext.getCmp("ChooseCustomerGridId").store.getAt(0).get('custType');
								//修改时保持客户类型为计划中第一个客户的类型
								var form = Ext.getCmp("SearchConditionFormId").getForm();
								form.findField('custType').oldValue = custType;
								form.findField('custType').setValue(custType);
								if(custType == 'PC_CUSTOMER'){
									controlForm(form,true);
								}else{
									controlForm(form,false);
								}
							}});// 加载已经选择的客户
							Ext.getCmp("planFromPanel").getForm().reset();// 重置表单
						    var createPlanModel=new CreatePlanModel(json.plan);
						    // 添加问卷生效和时效时间 todo
						    var planForm = Ext.getCmp("planFromPanel").getForm();
					    	var planFromPanel = Ext.getCmp("planFromPanel").getForm();
					    	planFromPanel.loadRecord(createPlanModel);
					    	// 获取问卷id和问卷名称
					    	var surveyName = selection[0].get("surveyName");
					    	var surveyId = selection[0].get("surveyId");
					    	var questionnaireModel = new QuestionnaireInfoModel();
					    	questionnaireModel.set('questionnaireName',surveyName);
					    	questionnaireModel.set('id',surveyId);
					    	planForm.findField("questionnaireId").setValue(questionnaireModel);
					    	planForm.findField("invalidTime").setValue(DButil.renderDate(json.invalidTime));
					    	planForm.findField("effectiveTime").setValue(DButil.renderDate(json.validTime));
					    	var deptModel=new DeptModel();
							deptModel.set("id",json.plan.execdeptid);
							deptModel.set("deptName",selection[0].get("execdeptName"));
							// 初始化设置部门信息
							Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").store.removeAll();
							Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").store.add(deptModel);
							Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").setValue(json.plan.execdeptid);
							Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").oldValue=json.plan.execdeptid;
							// 设置下面执行部门名称
							Ext.getCmp("planFromPanel").getForm().findField("execdeptid").setValue(selection[0].get("execdeptName"));
							// 根据部门查询执行人
							Ext.getCmp("planFromPanel").getForm().findField("execuserid").store.load({params:{executeDept:json.plan.execdeptid}});
							if(json.bisYd==="1"){// 离职或异动
								bisYd="1";// 设置是异动
								Ext.getCmp("planFromPanel").getForm().findField("beginTime").clearInvalid();
								Ext.getCmp("planFromPanel").getForm().findField("endTime").clearInvalid();
								Ext.getCmp("planFromPanel").getForm().findField("beginTime").disable();
								Ext.getCmp("planFromPanel").getForm().findField("endTime").disable();
								Ext.getCmp("planFromPanel").getForm().findField("topic").disable();
								Ext.getCmp("planFromPanel").getForm().findField("activedesc").disable();
								Ext.getCmp("planFromPanel").getForm().findField("execuserid").setValue("");
							}else{
								bisYd=null;// 设置是否异动为空
								Ext.getCmp("planFromPanel").getForm().findField("execuserid").setValue(json.plan.execuserid);
								Ext.getCmp("planFromPanel").getForm().findField("beginTime").enable();
								Ext.getCmp("planFromPanel").getForm().findField("endTime").enable();
								Ext.getCmp("planFromPanel").getForm().findField("topic").enable();
								Ext.getCmp("planFromPanel").getForm().findField("activedesc").enable();
								Ext.getCmp("planFromPanel").getForm().findField("questionnaireId").enable();
							}
						};
						var failureFn = function(json){
							MessageUtil.showErrorMes(json.message);
						};
						var planFromPanel = Ext.getCmp("planFromPanel").getForm();
						customerDevelopPlane.isOutOrLeave({'planId':selection[0].get("id")}, successFn, failureFn);
					}
				},{// 删除按钮
					text:i18n('i18n.developPlan.delete'),
					hidden:!isPermission('/marketing/planManageDBtn.action'),
					xtype:'button',
					handler:function(){
						var me = this;
						var grid;
						var	store;
						if(Ext.getCmp('customerDevelopPlaneGird')!=null){
							grid = Ext.getCmp('customerDevelopPlaneGird');
							// 获取store中数据
							store = grid.getStore();
							selection=grid.getSelectionModel().getSelection();
							// 判断是否选中行
							if (selection.length == 0) {
								MessageUtil.showMessage(i18n('i18n.developPlan.choice'));
								return false;
							}
							var bj=false;
							for (var j = 0; j < selection.length; j++) {
								if(selection[j].get("status")==40 || selection[j].get("status")==20 || selection[j].get("status")==30){
									bj=true;
									MessageUtil.showMessage(i18n('i18n.developPlan.deletewarning'));
									return false;
								}
								if(bj){
									break;
								}
							}
							// 删除弹出提示，是否删除
							MessageUtil.showQuestionMes(i18n('i18n.developPlan.isdelete'), function(e){	
								if (e == 'yes') {
									var planeIds = new Array();
									for (var j = 0; j < selection.length; j++) {
										// 当为false时，提示不能删除
										if(selection[j].get('running')>0){
											MessageUtil.showErrorMes(i18n('i18n.developPlan.errorPlaneState'));
											return false;
										}
										planeIds.push(selection[j].get('id'))
									}
									// 保存成功回调函数
									var delSuccessFn = function(result){
										// 删除grid中数据
										for (var j = 0; j < selection.length; j++) {
											store.remove(selection[j]);
										}
										grid.getView().refresh();
										MessageUtil.showInfoMes(i18n('i18n.developPlan.deleteSuccess'));
									};
									// 保存失败回调函数
									var delFailFn = function(result){
										MessageUtil.showErrorMes(result.message);
									};
									var params={'planeIds':planeIds};
									DevelopManageData.prototype.deletePlan(params,delSuccessFn,delFailFn);
								}
							});
						}
					}
				}]
			},{
				xtype:'middlebuttonpanel' 
			},{
				xtype : 'rightbuttonpanel',
				items : [{// 查询按钮
					xtype : 'button',
					text : i18n('i18n.developPlan.search'),
					handler:function(){
						var startDate = Ext.getCmp("customerDevelopPlaneForm").getForm().findField("createStartTime").getValue();
						var endDate = Ext.getCmp("customerDevelopPlaneForm").getForm().findField("createEndTime").getValue();
						if(!Ext.isEmpty(startDate)){
							if(!Ext.isEmpty(endDate)){
								var days = DButil.compareTwoDate(startDate,endDate);
								if(days<0){
									MessageUtil.showInfoMes(i18n('i18n.developPlan.startDateBGEndDate'));
									return;
								}else if(days>=90){
									MessageUtil.showInfoMes(i18n('i18n.developPlan.queryDateLimit'));
									return;
								}
								if(Ext.isEmpty(Ext.getCmp("customerDevelopPlaneForm").getForm().findField("executeDept").getValue())){
									var successFn = function(json){
									    if(!Ext.isEmpty(json)){
									    	if(parseInt(json.totalCount)>800){
									    		MessageUtil.showInfoMes(i18n('i18n.developPlan.deptLimit'));
									    		return false;
									    	}
									    	Ext.data.StoreManager.lookup('developPlaneGirdStore').loadPage(1);    	
									    }
									};
									var failureFn = function(json){
										MessageUtil.showErrorMes(json.message);
									};
									customerDevelopPlane.searchDeptCount(null, successFn, failureFn);
								}else{
									// 计划查询
									Ext.data.StoreManager.lookup('developPlaneGirdStore').loadPage(1);
								}
							}else{
								MessageUtil.showInfoMes("开发时限结束日期不能为空~");
								return;
							}
						}else{
							MessageUtil.showInfoMes("开发时限起始日期不能为空~");
							return;
						}
						
					}
				},{// 重置按钮
					xtype:'button',
					text:'重置',
					handler:function(){
						Ext.getCmp("customerDevelopPlaneForm").getForm().reset();
						// 初始化计划时限
						resetQueryDate(45,44);
					}
				}]
			}];
		}
	});
	/**
	 * 定义整个界面布局
	 */
	Ext.define('CustomerDevelopPlane',{
		extend:'BasicPanel',
		id:'customerDevelopPlane',
		layout:'border',
		items:null,
		buttonBar:null,
		getColumnsItems:function(){
		return [{
  				xtype:'rownumberer',
  				width:40,
  				header:i18n('i18n.Cycle.rownum')
  			},{
				header : i18n('i18n.Maintainp.topic'),
				dataIndex : 'topic',
				width:190
			},{
                /**
				 * 增加计划类别显示字段 auth：盛诗庆 date：2014-03-24
				 */
                header : i18n('i18n.returnVisit.planType'),
                dataIndex : 'projectType',
                renderer : function(value){
                    if(value==null||value==''){
                        return i18n('i18n.developSchedule.noMeaning');
                    }else{
                        return DButil.rendererDictionary(value,DataDictionary.PLAN_TYPE);
                    }
                },
                width:85
            },{
                /**
				 * 问卷名称字段 auth：盛诗庆 date：2014-03-24
				 */
                header : i18n('i18n.developPlan.surveyName'),
                dataIndex : 'surveyName',
                renderer : function(value,metaData,record,rowIndex ){
                	return '<a href = "javascript:viewQuestionnaireDetail('+ rowIndex + ')">' +value+ '</a>'
                },
                width:85
            },{
				header : i18n('i18n.Maintainp.startEnd'),
				dataIndex : 'timelimit',
				width:160
			},{
				header:i18n('i18n.MaintainpManageView.planStatus'),
				dataIndex:'status',
				renderer:function(value){
  					return rendererDictionary(value,DataDictionary.PLAN_STATUS);
  				},
  				width:75
			},{
				header : i18n('i18n.developPlan.executeDept'),
				dataIndex : 'execdeptName',
				width:140
			},{
				header : i18n('i18n.developPlan.executePersion'),
				dataIndex : 'execdeptUserName',
				width:85
			},{
				header : i18n('i18n.developPlan.creator'),
				dataIndex : 'createUserName',
				width:85
			},{
				header : i18n('i18n.developPlan.createTime'),
				dataIndex : 'createDate',
				renderer : DButil.renderDate,
				width:85
			},{
				header : i18n('i18n.developPlan.lastUpdator'),
				dataIndex : 'modifyUserName',
				hidden:true
			},{
				header : i18n('i18n.developPlan.lastUpdateTime'),
				dataIndex : 'modifyDate',
				renderer : DButil.renderDate
			}];
		},
		initComponent:function(){
			var me = this;
			var developPlaneGirdStore =  Ext.create('DevelopPlaneGirdStore',{'id':'developPlaneGirdStore'});
			developPlaneGirdStore.on('beforeload',function(developPlaneGirdStore,operation,e){

				// 将表单查询条件传递到后台去
				var searchDevelopPlaneForm = Ext.getCmp("customerDevelopPlaneForm");
				var executorId=searchDevelopPlaneForm.getForm().findField("execuserid").getValue();
				var executeDeptId=searchDevelopPlaneForm.getForm().findField("executeDept").getValue();
				var searchParams = {
				'planCondition.planName':searchDevelopPlaneForm.getForm().findField('planeName').getValue(),
				'planCondition.planMaker':searchDevelopPlaneForm.getForm().findField('planeDraft').getValue(),
				'planCondition.executor':Ext.isEmpty(executorId)==true?null:executorId,
				'planCondition.executeDept':Ext.isEmpty(executeDeptId)==true?null:executeDeptId,
				'planCondition.planStartDate':searchDevelopPlaneForm.getForm().findField('createStartTime').getValue(),
				'planCondition.planOverDate':searchDevelopPlaneForm.getForm().findField('createEndTime').getValue(),
				// 新增计划类别和问卷id查询条件 盛诗庆
				'planCondition.projectType':searchDevelopPlaneForm.getForm().findField('projectType').getValue(),
				'planCondition.surveyId' : searchDevelopPlaneForm.getForm().findField('questionnaireId').getValue(),
				};
				Ext.apply(operation,{
					params : searchParams
				});
			});	
			
			var customerDevelopPlaneGird = Ext.create('CustomerDevelopPlaneGird',{
				id:'customerDevelopPlaneGird',
				columns:me.getColumnsItems(),
				store:developPlaneGirdStore
			});
			// 添加双击事件
			customerDevelopPlaneGird.on('itemdblclick',function(th,record,item,index,e,eOpts){
				var successFn = function(json){
				    var createPlanModel=new CreatePlanModel(json.plan);
				    Ext.getCmp("findButtonId").planInfo = createPlanModel.data;
			    	var planFromPanel = Ext.getCmp("DetailPanel").getForm();
			    	planFromPanel.loadRecord(createPlanModel);
			    	var fields = planFromPanel.getFields().items;
					for(var i = 0; i < fields.length; i++){  
						fields[i].setReadOnly(true);
					}
					// 设置执行部门执行人
					planFromPanel.findField("execdeptid").setRawValue(record.get("execdeptName"));
					planFromPanel.findField("execuserid").setRawValue(record.get("execdeptUserName"));
					planFromPanel.findField("questionnaireId").setRawValue(record.get("surveyName"));
					planFromPanel.findField("execuserid").disable(); 
					planFromPanel.findField("questionnaireId").disable();
					planFromPanel.findField("beginTime").clearInvalid();
					planFromPanel.findField("endTime").clearInvalid();
				};
				var failureFn = function(json){
					MessageUtil.showErrorMes(json.message);
				};
				customerDevelopPlane.searchPlanById({'planId':record.get("id")}, successFn, failureFn);
				 Ext.getCmp("detailGridId").store.load({params:{'planId':record.get("id")},callback : function(records){
	                	Ext.getCmp("findButtonId").printData =  records;
	                }});// 加载已经选择的客户
				detailWin.show();
				document.getElementsByTagName("html")[0].style.overflowY="auto";
				document.getElementsByTagName("html")[0].style.overflowX="auto";
				viewport.doLayout();
			});
			// 界面布局模块
			me.items =[{
				xtype:'basicpanel',
				height:132,
				region:'north',
				layout:'border',
				items:[{
					region:'center',
					xtype:'basicpanel',
					layout:'fit',
					items:[Ext.create('CustomerDevelopPlaneForm',{
						'id':'customerDevelopPlaneForm'
// 'planeType':MAINTAIN_TYPE
					})]
				},
				Ext.create('ButtonPanel')]
			},{
				xtype:'basicpanel',
				region:'center',
				layout:'fit',
				items:[customerDevelopPlaneGird]
			}];
			// 初始化查询时间
			resetQueryDate(45,44);
			
			this.callParent();
		}
	});
		  	
  	/**
	 * 查询按钮
	 */
  	Ext.define('RightSearchButtonPanel',{
  		extend:'NormalButtonPanel', 
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
  				xtype:'rightbuttonpanel',  
  				items : [{
  					xtype : 'button',
  					text : i18n('i18n.PotentialCustManagerView.search'),
  					width : 70,
  					handler : function(){
  						// 判断界面校验是否通过
						if(!Ext.getCmp('SearchConditionFormId').getForm().isValid()){
// MessageUtil.showInfoMes(i18n('i18n.DevelopManageView.pleaseCondition'));
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
								// load数据
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
  					width : 70,
  					handler : function(){
  						Ext.getCmp("SearchConditionFormId").getForm().reset();
  						var deptModel=new DeptModel();
						deptModel.set("id",User.deptId);
						deptModel.set("deptName",User.deptName);
                        Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").store.removeAll();
						Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").store.add(deptModel);
						Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").setValue(User.deptId);
						Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").oldValue=User.deptId;
						Ext.getCmp("planFromPanel").getForm().findField("execdeptid").setValue(User.deptName);
						Ext.getCmp("planFromPanel").getForm().findField("execuserid").store.load({params:{executeDept:User.deptId}});
  					}
  				}]
  			}];
  		}
  	});
	  	
  	/**
	 * 制定开发计划按钮
	 */
  	Ext.define('RightDownButtonPanel',{
  		extend:'PopButtonPanel', 
  		items:null,
  		region:'south',
  		width:400,
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
  					text : i18n('i18n.maintainp.savePlan'),
  					width : 115,
  					scope:me.searchCondition,
  					handler : function(t){
  						var store=Ext.getCmp('ChooseCustomerGridId').store;
  						var custList=new Array();
  						var contactIds=new Array();
  						if(store.getCount()!=0){
  							for(var i=0;i<store.getCount();i++){// 获取已经选择的客户id
  								custList[i]=store.getAt(i).data.memberId;
  								contactIds[i]=store.getAt(i).data.contactId;
  							}
  						}else{
  							MessageUtil.showErrorMes(i18n("i18n.developPlan.choiceCustFirst"));
  				    		return false;
  						}
  						// 判断界面校验是否通过
						if(!Ext.getCmp('planFromPanel').getForm().isValid()){
							return false;
						}
						var planFromPanel = Ext.getCmp("planFromPanel").getForm();
						var planBeginTime = planFromPanel.findField('beginTime').getValue();
						var planEndTime = planFromPanel.findField('endTime').getValue();
						if(DButil.compareTwoDate(planBeginTime,planEndTime) <= 0){
							MessageUtil.showErrorMes('抱歉，计划开始时间必须早于计划结束时间！');
  				    		return false;
						}
						// 校验计划时限是否在问卷有效期之内
						if(!Ext.isEmpty(planFromPanel.findField('questionnaireId').getValue())){
							
							var effectiveTime = planFromPanel.findField('effectiveTime').getValue();
							var invalidTime = planFromPanel.findField('invalidTime').getValue();
							// 比较计划时限是否在问卷有效期范围之内
							if(!DButil.compareTwoDateRange(planBeginTime,planEndTime,effectiveTime,invalidTime)){
								MessageUtil.showErrorMes('抱歉，计划时限必须在问卷有效期之内！请重新选择计划时限或者重新选择问卷！');
	  				    		return false;
							}
						}
  						var successFn = function(json){
  				    		t.enable();
  				    		win.hide();
  				    		Ext.data.StoreManager.lookup('developPlaneGirdStore').loadPage(1);
  				    		MessageUtil.showInfoMes(i18n('i18n.developPlan.saveDevelopPlanSuccess'));
  				    	};
  				    	var failureFn = function(json){
  				    		t.enable();
  				    		MessageUtil.showErrorMes(json.message);
  				    	};
  				    	
  				    	var planFromPanel1 = Ext.getCmp("SearchConditionFormId").getForm();
  				    	var customerType = planFromPanel1.findField('custType').getValue();
  				    	if(Ext.isEmpty(type)||type =='mat'||type=='dev'){
  				    		if(customerType == 'RC_CUSTOMER'){// 客户类型为固定客户
  	  				    		type = 'mat';
  	  				    	}else{
  	  				    		type = 'dev';
  	  				    	}
  				    	} 
  				    	
  				    	var param = {
  				    		'developPlan.beginTime':planFromPanel.findField('beginTime').getValue(),// 开始时间
  		    			    'developPlan.endTime':planFromPanel.findField('endTime').getValue(),// 结束时间
  		    			    'developPlan.topic':planFromPanel.findField('topic').getValue(),
  		    			    'developPlan.execdeptid':planFromPanel1.findField('execdeptid').getValue(),// 执行部门
  		    			    'developPlan.activedesc':planFromPanel.findField('activedesc').getValue(),
  		    			    'developPlan.execuserid':planFromPanel.findField('execuserid').getValue(),// 执行人员
  		    			    'developPlan.id':planFromPanel.findField('id').getValue(),
  		    			    'developPlan.planType':type,
  		    			    // 计划对应的问卷id
  		    			    'developPlan.surveyId':planFromPanel.findField('questionnaireId').getValue(),
  		    			    // 计划类别 盛诗庆
  		    			    'developPlan.projectType':planFromPanel.findField('projectType').getValue(),
  		    			    'custList':custList,
  		    			    'bisYd':bisYd,
  		    			    'contactIds':contactIds
  		    			};
  				    	t.disable();
  				    	customerDevelopPlane.savePlan(param, successFn, failureFn);
  					}
  				},{
  					xtype : 'button',
  					text : i18n('i18n.developPlan.reset'),
  					width : 70,
  					handler : function(){
                         // -----修改重置后新增计划BUG auth:李春雨
                        var planFromPanel = Ext.getCmp("planFromPanel");
                        var id = planFromPanel.getForm().findField('id').getValue();
                        // -----
  						Ext.getCmp("planFromPanel").getForm().reset();
  						var deptName=Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").getRawValue();
  						var deptId=Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").getValue();
  						Ext.getCmp("planFromPanel").getForm().findField("execdeptid").setValue(deptName);
                        planFromPanel.getForm().findField('id').setValue(id);
  					}
  				},{
  					xtype : 'button',
  					text : i18n('i18n.DevelopManageView.close'),
  					width : 70,
  					handler : function(){
  						win.close();
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
  					// 得到所选客户
  					var selection=Ext.getCmp("searchResultGridId").getSelectionModel().getSelection();
  					// 已选择客户store
  					var chooseStore=Ext.getCmp("ChooseCustomerGridId").store;
  					for(var i=0;i<selection.length;i++){// 遍历所选客户
  							var isRepeat = selection[i].get('repeatCust');// 疑似重复
    				    	var status = selection[i].get('businessOpportunityStatus');// 商机状态
	  	  					if(status == 'ONGOING' || status == 'EXTENDED' || status == 'DORMANT'){// 有未关闭的商机状态，不允许指定新计划
	  	  						 MessageUtil.showErrorMes('[' +selection[i].get("linkManName")+']'+'存在未关闭的商机，不能制定计划！');
	  	  						 return false;
	  			 			}
		  	  				if(isRepeat == '1'){// 疑似重复客户
	 	  						 MessageUtil.showErrorMes('['+selection[i].get("linkManName")+']'+'是' + '[' +selection[i].get("repeatCustDeptName")+']' + '疑似重复客户，不能制定计划！');
	 	  						 return false;
	 			 			}
    				    	if(chooseStore.find("contactId",selection[i].get("contactId"))!=-1){// 判断是否有重复
    						    MessageUtil.showErrorMes(selection[i].get("linkManName")+i18n('i18n.developPlan.exist'));
    						    return false;
    					    }else{
    					    	Ext.getCmp("searchResultGridId").getSelectionModel().deselect(selection[i]);
    					    	Ext.getCmp("searchResultGridId").store.remove(selection[i]);
    					    	// 添加到已选择客户store里
    					    	chooseStore.add(selection[i]);
    					    }
  					}
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
  				// 移除所选客户
  				handler : function(){
  					// 得到已选客户
  					var selection=Ext.getCmp("ChooseCustomerGridId").getSelectionModel().getSelection();
  					// 待选择客户store
  					var store=Ext.getCmp("searchResultGridId").store;
  					for(var j=0;j<selection.length;j++){// 遍历所选客户
  						if(store.find("contactId",selection[j].get("contactId"))!=-1){// 判断是否有重复
						    Ext.getCmp("ChooseCustomerGridId").getSelectionModel().deselect(selection[j]);
	  						Ext.getCmp("ChooseCustomerGridId").store.remove(selection[j]);
					    }else{
					    	Ext.getCmp("ChooseCustomerGridId").getSelectionModel().deselect(selection[j]);
	  						Ext.getCmp("ChooseCustomerGridId").store.remove(selection[j]);
	  						// 添加到已选择客户store里
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
	  	
	  	
  	/**
	 * .
	 * <p>
	 * 开发计划新增、修改主Panel
	 * <p>
	 * 
	 * @author 张登
	 * @时间 2012-3-26
	 */
  	Ext.define('CustomerDevelopPlanePanel',{
  		extend:'BasicPanel',
  		searchCondition:null, // 查询条件From
  		searchLeftResult:null, // 查询客户列表（左边Grid）
  		searchRightResult:null, // 已选择客户列表（右边Grid）
  		downPlanformPanel:null,// 底部开发计划formPanel
  		items:null,
  		layout:'border',
  		initComponent:function(){
  			var me = this;
  			
  			// 查询条件From
  			me.searchCondition = Ext.create('SearchConditionForm',{id:'SearchConditionFormId'});
  			// 查询客户列表store
  			var store=Ext.create('SearchMemberStore',{id:'SearchMemberStoreId'});
  			store.on('beforeload',function(store,operation,e){
  				var searchScatterForm = Ext.getCmp("SearchConditionFormId");
  				// 获取二级行业
				var firstTrade = searchScatterForm.down('form').getForm().findField('trade').getValue();
				var secondTrade = searchScatterForm.down('form').getForm().findField('secondTrade').getValue();
  				// 设置请求参数
  				var searchParams = { 
  					// 客户名称
  	  					'customerVo.custName':searchScatterForm.getForm().findField('custName').getValue(),
  	  					// 部门
  	  					'customerVo.deptId':searchScatterForm.getForm().findField('execdeptid').getValue(),
  	  					// 联系人姓名
  	  					'customerVo.linkManName':searchScatterForm.getForm().findField('linkManName').getValue(),
  	  					// 联系人手机
  	  					'customerVo.linkManMobile':searchScatterForm.getForm().findField('linkManMobile').getValue(),
  	  					// 联系人电话
  	  					'customerVo.linkManPhone':searchScatterForm.getForm().findField('linkManPhone').getValue(),
  	  					// 客户属性 出发客户 到达客户
  	  					'customerVo.custProperty':searchScatterForm.getForm().findField('custProperty').getValue(),
  	  					// 行业
  	  					'customerVo.trade':firstTrade,
  	  					// 二级行业
  	  					'customerVo.secondTrade':secondTrade,
  	  					'customerVo.scheduleType':MAINTAIN_TYPE,
  	  					// 客户编码
  	  					'customerVo.memberNo':searchScatterForm.getForm().findField('memberNo').getValue(),
  	  					// 联系人编码
  	  					'customerVo.linkManCode':searchScatterForm.getForm().findField('linkManCode').getValue(),
  	  					// 会员等级
  	  					'customerVo.memberLevel':searchScatterForm.getForm().findField('memberLevel').getValue(),
  	  					// 创建时间
  	  					'customerVo.beginTime':searchScatterForm.getForm().findField('beginTime').getValue(),
  	  					'customerVo.overTime':searchScatterForm.getForm().findField('overTime').getValue(),
  	  					// 发货量
  	  					'customerVo.beginShipWeight':searchScatterForm.getForm().findField('beginShipWeight').getValue(),
  	  					'customerVo.overShipWeight':searchScatterForm.getForm().findField('overShipWeight').getValue(),
  	  					// 发货票数
  	  					'customerVo.beginShipVotes':searchScatterForm.getForm().findField('beginShipVotes').getValue(),
  	  					'customerVo.overShipVotes':searchScatterForm.getForm().findField('overShipVotes').getValue(),
  	  					// 发货金额
  	  					'customerVo.beginShipAmount':searchScatterForm.getForm().findField('beginShipAmount').getValue(),
  	  					'customerVo.overShipAmount':searchScatterForm.getForm().findField('overShipAmount').getValue(),
  	  					// 到达货量
  	  					'customerVo.beginArrivalWeight':searchScatterForm.getForm().findField('beginArrivalWeight').getValue(),
  	  					'customerVo.overArrivalWeight':searchScatterForm.getForm().findField('overArrivalWeight').getValue(),
  	  					// 到达票数
  	  					'customerVo.beginArrivalVotes':searchScatterForm.getForm().findField('beginArrivalVotes').getValue(),
  	  					'customerVo.overArrivalVotes':searchScatterForm.getForm().findField('overArrivalVotes').getValue(),
  	  					// 到达金额
  	  					'customerVo.beginArrivalAmount':searchScatterForm.getForm().findField('beginArrivalAmount').getValue(),
  	  					'customerVo.overArrivalAmount':searchScatterForm.getForm().findField('overArrivalAmount').getValue(),
  	  					// 业务类别 零担快递
  	  					'customerVo.businessType':searchScatterForm.getForm().findField('custCategory').getValue(),
  	  					// 客户类型 潜散客
  	  					'customerVo.custType':searchScatterForm.getForm().findField('custType').getValue(),
  	  					// 合作意向
  	  					'customerVo.cooperationIntention':searchScatterForm.getForm().findField('cooperationIntention').getValue(),
  	  					// 客户来源
  	  					'customerVo.custbase':searchScatterForm.getForm().findField('custSorce').getValue(),
  	  					// 货量潜力
  	  					'customerVo.volumePotential':searchScatterForm.getForm().findField('cargoPotential').getValue(),
  	  					// 开发阶段
  	  					'customerVo.developState':searchScatterForm.getForm().findField('developStage').getValue(),
  	  					// 继续营销
  	  					'customerVo.continueMark':searchScatterForm.getForm().findField('continueMarket').getValue(),
  	  					// 查询时间
  	  					'customerVo.queryBeginTime':searchScatterForm.getForm().findField('searchBeginTime').getValue(),
  	  					'customerVo.queryOverTime':searchScatterForm.getForm().findField('searchOverTime').getValue()
  				};
  				Ext.apply(operation,{
  					params : searchParams
  				});
  			});
  			var selModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SIMPLE',id:'selModelId'});
  			// 查询结果Grid
  			me.searchLeftResult =  Ext.create('PopupGridPanel',{
  				id:'searchResultGridId',
  				store:store,
  				columns:me.getColumns(),
  				selModel:selModel,
  				viewConfig:{// 可拖动插件
  					forceFit:true
  				},
  			    listeners: {
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
  					cls:'pagingtoolbar',
  					dock:'bottom',
  					displayInfo : true,
  					autoScroll : true,
  					items:[{
  						text: '每页',
  						xtype: 'tbtext'
  					},Ext.create('Ext.form.ComboBox', {
  		               width:          window.screen.availWidth*0.0391,
  		               triggerAction:  'all',
  		               forceSelection: true,
  		               value:'10',
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
  			// 查询已选择客户列表store
  			var chooseStore=Ext.create('SearchMemberListByPlanIdStore',{id:'SearchMemberListByPlanIdStoreId'});
  			
  			var searchRightGrid=Ext.create('PopupGridPanel',{
  				id:'ChooseCustomerGridId',
  				store:chooseStore,
  				columns:me.getColumns(),
  				selModel:chooseSelModel,
  			    listeners: {
  			    	scrollershow: function(scroller) {
  			    		if (scroller && scroller.scrollEl) {
  			    				scroller.clearManagedListeners(); 
  			    				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
  			    		}
  			    	}
  			    },
  				viewConfig:{// 可拖动插件
  					forceFit:true
  				}
  			});
  			
  			me.searchRightResult =  searchRightGrid;
  			me.downPlanformPanel=Ext.create('SavePlanPanel',{id:'planFromPanel'});// 制定计划
  			store.on('load',function(store,records){
  				var girdcount=0;
		        store.each(function(record){
		            if(chooseStore.find("contactId",record.get("contactId"))!=-1){
						var cells =  Ext.getCmp('searchResultGridId').getView().getNodes()[girdcount].children;
						for(var i= 0;i<cells.length;i++){
							cells[i].style.backgroundColor='#FF9797';
						};
		 			};
		 			var status = record.get("businessOpportunityStatus");// 商机状态
		 			var isRepeat = record.get("repeatCust");// 疑似重复
		 			if(isRepeat == '1' || status == 'ONGOING' || status == 'EXTENDED' || status == 'DORMANT'){// 有未关闭的商机状态，标红
		 				var cells =  Ext.getCmp('searchResultGridId').getView().getNodes()[girdcount].children;
						for(var i= 0;i<cells.length;i++){
							cells[i].style.backgroundColor='#FF9797';
						};
		 			}
		 			girdcount=girdcount+1;
		        });
  			});
  			
  			// 设置items
  			me.items = me.getItems();
  			this.callParent();
  		},
  		getItems:function(){// 整体布局
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
  				Ext.create('RightSearchButtonPanel')]// border布局下面查询按钮
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
// flex:1,
  						width:220,
  						items:[me.searchRightResult]
  					}]
  				},{
  					region:'south',
  					xtype:'basicpanel',
  					layout:'border',
  					height:150,
  					items:[{
  						region:'center',
  						xtype:'basicpanel',
  						layout:'fit',
  						items:[me.downPlanformPanel]
  					},
  					Ext.create('RightDownButtonPanel')]// border布局下面制定开发计划按钮
  				}]
  			}];
  		},
  		getColumns:function(){// 查询结果列
  			return [{
  				header : i18n('i18n.PotentialCustManagerView.customerName'),
  				dataIndex : 'custName',
  				width:75
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.finalreviTime'),
  				dataIndex : 'lastVistiTime',
 	            renderer : function(value){
 	            	if(!Ext.isEmpty(value)){
 	            		return DButil.renderDate(value);
 	            	}
	            }
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.contactName'),
  				dataIndex : 'linkManName'
  			}, {
  				header : i18n('i18n.ArrivalCycleView.memberLevel'),
  				dataIndex : 'memberLevel',
  				renderer:function(value){
  					return rendererDictionary(value,DataDictionary.MEMBER_GRADE);
  				},
  				width:85
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.contactPhone'),
  				dataIndex : 'linkManMobile'
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.contactTel'),
  				dataIndex : 'linkManPhone'
  			}, {
  				header : i18n('i18n.MonitorPlan.noExecute'),
  				dataIndex : 'unfinishedPlanNum'
  			},{
  				header : i18n('i18n.ArrivalCycleView.maintainMan'),
  				dataIndex : 'prehuMan',
  				width:75
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.reviTimes'),
  				dataIndex : 'visitNum',
  				width:75
  			},{
  				header : i18n('i18n.developSchedule.cooperatePurpose'),
  				dataIndex : 'coopIntention',
  				renderer:function(value){
  					return rendererDictionary(value,DataDictionary.COOPERATION_INTENTION);
  				},
  				width:75
  			},{
  				header : i18n('i18n.developSchedule.sendGoodsPontential'),
  				dataIndex : 'volumePotential',
  				renderer:function(value){
  					return rendererDictionary(value,DataDictionary.CARGO_POTENTIAL);
  				},
  				width:75
  			},{
  				header : i18n('i18n.PotentialCustManagerView.bizStep'),
  				dataIndex : 'developmentPhase',
  				renderer:function(value){
  					return rendererDictionary(value,DataDictionary.DEVELOP_STAGE);
  				},
  				width:75
  			},{
  				header : i18n('i18n.PotentialCustManagerView.businessType'),
  				dataIndex : 'businessType',
  				renderer:function(value){
  					return rendererDictionary(value,DataDictionary.CUST_CATEGORY);
  				},
  				width:75
  			}]
  		}
  	});
  	
	/**
	 * 显示制定、修改计划
	 */
	Ext.define('CreateDevelopPopWindow',{
		extend:'PopWindow',
		alias : 'widget.basicwindow',
		width:820,
		height:700,
		modal:true,
		layout:'fit',
		title:i18n('i18n.FrameHelper.maintenancePlanManagementAdd'),
		closeAction:'hide',
		items:[Ext.create('CustomerDevelopPlanePanel',{'id':'customerDevelopPlanePanel'})],
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
	var win=Ext.getCmp("CreateDevelopPopWindowId");// 获取win
	if(!win){
		win=Ext.create('CreateDevelopPopWindow',{id:'CreateDevelopPopWindowId'});
	}
	
	
	/**
	 * .
	 * <p>
	 * 开发计划详细
	 * <p>
	 * 
	 * @author 张登
	 * @时间 2012-3-26
	 */
  	Ext.define('DetailPanel',{
  		extend:'BasicPanel',
  		searchCondition:null, // 查询条件From
  		searchResult:null, // 查询客户列表（左边Grid）
  		items:null,
  		layout:'border',
  		initComponent:function(){
  			var me = this;
  			// 查询条件From
  			me.searchCondition = Ext.create('SavePlanPanel',{id:'DetailPanel'});
// var
// detailSelModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SIMPLE',id:'detailSelModelId'});
  			// 查询已选择客户列表store
  			var detailStore=Ext.create('SearchMemberListByPlanIdStore',{id:'detailStoreId'});
  			me.searchResult=Ext.create('PopupGridPanel',{
  				id:'detailGridId',
  				store:detailStore,
  				columns:me.getColumns(),
// selModel:detailSelModel,
  				viewConfig:{
  					forceFit:true
  				}
  			});
  			// 设置items
  			me.items = me.getItems();
  			
  			// 加载明细执行人
  			Ext.getCmp("DetailPanel").getForm().findField("execuserid").store.load();
  			this.callParent();
  		},
  		getItems:function(){// 整体布局
  			var me = this;
  			return [{
  				region:'north',
  				xtype:'basicpanel',
  				height:110,
  				layout:'fit',
  				items:[me.searchCondition]
  			},{
  				region:'center',
  				xtype:'basicpanel',
  				layout:'fit',
  				items:[me.searchResult]
  			}];
  		},
  		getColumns:function(){// 查询结果列
  			return [{
  				xtype:'rownumberer',
  				width:40,
  				align:'center',
  				header:i18n('i18n.Cycle.rownum')
  			},{
  				header : i18n('i18n.PotentialCustManagerView.customerName'),
  				dataIndex : 'custName'
  			}, {
  				header : i18n('i18n.ArrivalCycleView.memberLevel'),
  				dataIndex : 'memberLevel',
				renderer:function(value){
					return DButil.rendererDictionary(value,DataDictionary.MEMBER_GRADE); 
				}
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.contactName'),
  				dataIndex : 'linkManName'
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.contactPhone'),
  				dataIndex : 'linkManMobile'
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.contactTel'),
  				dataIndex : 'linkManPhone'
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
  			},
  			// 添加营销备注字段 by 肖红叶
  			{
  				header : i18n('i18n.developSchedule.marketRemark'),
  				dataIndex : 'marketRemark',
  				width:120,
  				renderer:function(value){
  					if(!Ext.isEmpty(value)){
 						return '<span data-qtip="'+value+'">'+value+'</span>';
 	            	}
  				}
  			}]
  		}
  	});
	
  	/**
	 * 显示制定、修改计划
	 */
	Ext.define('DetailDevelopPopWindow',{
		extend:'PopWindow',
		alias : 'widget.basicwindow',
		width:820,
		height:600,
		modal:true,
		layout:'fit',
		closeAction:'hide',
		items:[Ext.create('DetailPanel',{'id':'DetailPanelId'})],
		listeners:{
			hide:function(){
				document.body.scrollLeft=0;
				document.body.scrollTop=0;
				document.getElementsByTagName("html")[0].style.overflowY="hidden";
				document.getElementsByTagName("html")[0].style.overflowX="hidden";
				viewport.doLayout();
			}
		},
		buttons:[
		         { 
		        	 xtype:'button',// 打印计划按钮 盛诗庆
				 	 text:i18n('i18n.DevelopManageView.printPlan'),
				 	 handler:function(){
					 	 window.planInfo = Ext.getCmp('findButtonId').planInfo;
				    	 window.printData = Ext.getCmp('findButtonId').printData;
				    	 window.open('../marketing/printPlan.action','printPlanPage', 'top=0,left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,channelmode=yes,fullscreen=yes,titlebar=no');
				 	 }
		         },
		    {
				xtype:'button',
				text:i18n('i18n.DevelopManageView.close'),
				handler:function(){
					Ext.getCmp("DetailDevelopPopWindowId").close();
				}
		    }]
	});
	var detailWin=Ext.getCmp("DetailDevelopPopWindowId");// 获取win
	if(!detailWin){
		detailWin=Ext.create('DetailDevelopPopWindow',{id:'DetailDevelopPopWindowId'});
	}
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
// if(Ext.isEmpty(document.getElementById("xxxxxxx"))){
// alert("空的撒");
// }
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
	/**
	 * 将界面显示到viewport中
	 */
	var viewport=Ext.create('Ext.Viewport',{
		layout : 'border',
		autoScroll:true,
		items:[Ext.create('CustomerDevelopPlane',{region:'center'})]
	});
	viewport.setAutoScroll(false);
	viewport.doLayout();
});

/**
 * 初始化主页面查询条件的创建起止时间 肖红叶 2014-03-14
 */
function resetQueryDate(beforeInt,afterInt){
	var date = new Date();
	date.setDate(new Date().getDate()-beforeInt);
	Ext.getCmp("customerDevelopPlaneForm").getForm().findField("createStartTime").setValue(date);
	Ext.getCmp("customerDevelopPlaneForm").getForm().findField("createEndTime").setValue(new Date().getDate()+afterInt);
}
/**
 * 显示问卷详情
 * 
 * @param id
 */
function viewQuestionnaireDetail(rowIndex){
	var questionnaireDetailPopWindow = Ext.getCmp("questionnaireDetailPopWindowId");// 获取win
	if(!questionnaireDetailPopWindow){
		questionnaireDetailPopWindow = Ext.create('QuestionnaireDetailPopWindow',{id:'questionnaireDetailPopWindowId'});
	}
	// 获得问卷基本信息面板
	var questionnaireDetailForm = Ext.getCmp('questionnaireDetailFormId').getForm();
	// 重置查看详情页面
	questionnaireDetailForm.reset();
	Ext.getCmp('questionListGridId').store.removeAll();
	// 获取选中行
	var record = Ext.data.StoreManager.lookup('developPlaneGirdStore').getAt(rowIndex);
	// 查询问卷详情成功回调函数
	function successFn(json){
		// 新建问卷model
		var model = new QuestionnaireInfoModel(json.questionnaireList[0])
		// 向form中加载数据
		questionnaireDetailForm.loadRecord(model);
	}
	// 查询问卷失败回调函数
	function failureFn(json){
		MessageUtil.showErrorMes(json.message);
	}
	customerDevelopPlane.searchQuestionnaireInfoList({'questionnaireQueryCondi.questionnaireId':record.get('surveyId'),'start':0,'limit':1},successFn,failureFn);
	questionnaireDetailPopWindow.show();
	  // 根据问卷id查询已选问题列表
	  // 获得问卷查询结果列表
	  var grid = Ext.getCmp('questionnaireInfoListGridId');
	  // 获得问卷id数组
	  var idArray = new Array();
	  idArray.push(record.get('surveyId'));
	  questionnaireIdListForWhole = idArray;
	  questionIdListForWhole = null;
	  // 加载查看详情中的问题信息列表
	  Ext.getCmp('questionListGridId').store.removeAll();
	  Ext.getCmp('questionListGridId').store.load();
}
/**
 * @description 验证查询界面货量查询条件是否可用
 * @author 盛诗庆
 * @return true 验证通过 false 验证不通过
 */
function searchTimeIsValid(){
	// 发货量
	var beginShipWeight = Ext.getCmp("SearchConditionFormId").getForm().findField("beginShipWeight").getValue();
	var overShipWeight = Ext.getCmp("SearchConditionFormId").getForm().findField("overShipWeight").getValue();
	// 到达货量
	var beginArrivalWeight = Ext.getCmp("SearchConditionFormId").getForm().findField("beginArrivalWeight").getValue();
	var overArrivalWeight = Ext.getCmp("SearchConditionFormId").getForm().findField("overArrivalWeight").getValue();
	// 发货金额
	var beginShipAmount = Ext.getCmp("SearchConditionFormId").getForm().findField("beginShipAmount").getValue();
	var overShipAmount = Ext.getCmp("SearchConditionFormId").getForm().findField("overShipAmount").getValue();
	// 到达金额
	var beginArrivalAmount = Ext.getCmp("SearchConditionFormId").getForm().findField("beginArrivalAmount").getValue();
	var overArrivalAmount = Ext.getCmp("SearchConditionFormId").getForm().findField("overArrivalAmount").getValue();
	// 发货票数
	var beginShipVotes = Ext.getCmp("SearchConditionFormId").getForm().findField("beginShipVotes").getValue();
	var overShipVotes = Ext.getCmp("SearchConditionFormId").getForm().findField("overShipVotes").getValue();
	// 到达票数
	var beginArrivalVotes = Ext.getCmp("SearchConditionFormId").getForm().findField("beginArrivalVotes").getValue();
	var overArrivalVotes = Ext.getCmp("SearchConditionFormId").getForm().findField("overArrivalVotes").getValue();
	// 获取查询时间范围
	var searchBeginTime = Ext.getCmp("SearchConditionFormId").getForm().findField("searchBeginTime").getValue();
	var searchOverTime = Ext.getCmp("SearchConditionFormId").getForm().findField("searchOverTime").getValue();
	// 获取客户创建时间
	var startDate = Ext.getCmp("SearchConditionFormId").getForm().findField("beginTime").getValue();
	var endDate = Ext.getCmp("SearchConditionFormId").getForm().findField("overTime").getValue();
	if(overArrivalVotes !=null || beginArrivalVotes !=null 
			||overShipVotes !=null ||beginShipVotes !=null 
			||overArrivalAmount !=null ||beginArrivalAmount !=null 
			||overShipAmount !=null ||beginShipAmount !=null 
			||overArrivalWeight !=null ||beginArrivalWeight !=null 
			||overShipWeight !=null ||beginShipWeight !=null )// 如果以上任意一个不为空，验证查询时间条件
	{
		if(searchBeginTime == null || searchOverTime == null){
			MessageUtil.showErrorMes('请选择查询时间');
			return false;
		}else
		{
			var searchTimeRegion = DButil.compareTwoDate(searchBeginTime,searchOverTime);// 查询时间范围
			if(searchTimeRegion < 0){
				MessageUtil.showErrorMes('查询起始时间小于结束时间');
				return false;
			}else if(searchTimeRegion > 365){
				MessageUtil.showErrorMes('查询时间范围不能超过一年');
				return false;
			}
			var days = DButil.compareTwoDate(searchBeginTime,endDate);
			if(days < 0){// 查询时间在创建时间之前
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