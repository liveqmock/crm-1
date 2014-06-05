/**
 * 客户开发效果评估页面
 */

//定义全局变量，用以存放上一级部门信息
var devSuperDeptName = i18n('i18n.boReport.divisionName');
//定义一个全局变量，用以保存查询条件的查询结束时间
var devEndDate = null;
var devStartDate = null;
//查询时候部门ID
var deptId = '';
//当前登录用户所有有权限部门ID
var deptAuthId = '';
Ext.onReady(function(){
	//初始化部门用户等信息
	initDeptAndUserInfo();
	//初始化登录用户权限信息
	createAssessAuth();
	/**
	 *商机效果评估页面中查询条件输入面板form
	 */
	Ext.define('CRM.report.BoQueryPanel',{
		extend:'SearchFormPanel', 
		items:null,
		border:0,
		layout:{
			type:'table',
			columns:2
		},
		defaults:{
			labelWidth:60,
			labelAlign:'right',
			width:250,
			margin:'0 5 5 0'
		},
		defaultType:'combobox',
		initComponent:function(){			
			this.items = this.getItems();
			this.callParent();
			initCombobox();
		},
		getItems:function(){
			var me = this;
			return [
			    {//经营本部
			    	fieldLabel : i18n('i18n.boReport.manageDept'),
			    	store: Ext.create('ManageDeptStore'),
			    	queryMode:'local',
			    	forceSelection :true,
			    	displayField:'deptName',
					valueField:'id',
					name:'manageDept',
					listeners:{
						select:function(th,records,e){
							Ext.getCmp("businessDept").store.load({params:{'parentDeptId':th.getValue()}});
						},
						change:function(th,newValue,oldValue){
							comboSelsct(th);
							if(Ext.isEmpty(newValue)){
								Ext.getCmp("businessDept").store.removeAll();
								Ext.getCmp("businessDept").setValue('');
							}
						}
					},
					id:'manageDept'
			    },{//事业部
			    	fieldLabel:i18n('i18n.boReport.businessDept'),
			    	//剔出朦层
	    			listConfig: {
	      		        loadMask:false
	    			},
			    	queryMode:'local',
			    	forceSelection :true,
			    	store:Ext.create('SelectDeptBySuperiorDeptStore'),
			    	displayField:'deptName',
					valueField:'id',
					name:'businessDept',
					id:'businessDept'
				},{//起止时间
			    	xtype:'datefield',
			    	disabled:true,
			    	readOnly:true,
			    	fieldLabel:i18n('i18n.boReport.startDate'),
			    	format:'Y-m-d',
					name:'startDate',
					id:'startDate',
					//设置起始时间的初始值为当前月份的第一天
					value:Ext.Date.getFirstDateOfMonth(Ext.Date.add(new Date(),Ext.Date.DAY,-1))
				},{//起止时间到
					xtype:'datefield',
			    	fieldLabel:i18n('i18n.boReport.endDate'),
			    	format:'Y-m-d',
			    	maxValue:Ext.Date.add(new Date(),Ext.Date.DAY,-1),
			    	//为结束时间赋初值
			    	value:Ext.Date.add(new Date(),Ext.Date.DAY,-1),
					name:'endDate',
					id:'endDate',
					listeners:{
						change:function(th,newValue,oldValue){
							if(newValue!==oldValue){
								//如果结束时间改变，起始时间做相应改变
								Ext.getCmp('startDate').setValue(Ext.Date.getFirstDateOfMonth(new Date(newValue)));
							}
						}
					}
				}
		    ];
		}	
	});
	//客户开发效果评估操作按钮面板
	Ext.define('CRM.report.BoBtnPanel',{
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
				width:450,
				items:[{//导出当前查询结果
					  xtype:'button',
					  text:i18n('i18n.boReport.exportCurrentResult'),
					  handler:function(btn){
						//判断是否可以进行导出操作
						  if( Ext.getCmp('devEffectEvaluationListGrid').store.data.length == 0){
							  btn.enable();
							  MessageUtil.showErrorMes(i18n('i18n.boReport.searchResultNull'));
							  return false;
						  }
						  //导出权限下全部数据功能实现区
						  //进度条
						  Ext.MessageBox.show({
								title:i18n('i18n.boReport.exportNowSearch'),
							    msg:i18n('i18n.boReport.exportIngPleaseWait'),
							    wait:true
						  });
						  var successFn = function(json){
								 window.location.href = "../marketingReport/exportBoReportExcel.action?realPath="
										 +json.realPath+'&downloadFileName='+json.downloadFileName;
								 btn.enable();
								 //隐藏进度条
								 Ext.MessageBox.hide();
								 MessageUtil.showInfoMes(i18n('i18n.boReport.exportSuccess'));//导出成功	  
							   
						  };
						 var param = {'brc':{'deptId':deptId,'deptName':devSuperDeptName,'endTime':devEndDate,'beginTime':devStartDate}};
						 var failureFn=function(){
							btn.enable();
							//隐藏进度条
							Ext.MessageBox.hide();
							MessageUtil.showErrorMes(i18n('i18n.boReport.exportFailure'));
						 };
						 btn.disable();
						 BoEvaluationStore.prototype.creatBoReportExcel(param,successFn,failureFn);							
					  }
				  },{//导出权限下全部数据
					  xtype:'button',
					  text:i18n('i18n.boReport.exportAll'),
					  handler:function(btn){
						  var devEffectEvaluationQueryForm = Ext.getCmp("devEffectEvaluationQueryPanel").getForm();
						  if(deptAuthId=='noAuth'){//没有权限
							  MessageUtil.showErrorMes('您没有权限！');//没有权限！
							  return;
						  }
						  //导出权限下全部数据功能实现区
						  //进度条
						  Ext.MessageBox.show({
								title:i18n('i18n.boReport.exportAll'),
							    msg:i18n('i18n.boReport.exportIngPleaseWait'),
							    wait:true
						  });
						  var successFn = function(json){
								 window.location.href = "../marketingReport/exportBoReportExcel.action?realPath="
										 +json.realPath+'&downloadFileName='+json.downloadFileName;
								 btn.enable();
								 //隐藏进度条
								 Ext.MessageBox.hide();
								 MessageUtil.showInfoMes(i18n('i18n.boReport.exportSuccess'));	  
							   
						  };
						//点击查询按钮时为全局变量devEndDate赋值
					    	devEndDate = devEffectEvaluationQueryForm.findField('endDate').getValue();
					    	devStartDate = devEffectEvaluationQueryForm.findField('startDate').getValue();
						 var param = {'brc':{'deptId':deptAuthId,'deptName':i18n('i18n.boReport.bizName'),'endTime':devEndDate,'beginTime':devStartDate}};
						 var failureFn=function(){
							btn.enable();
							//隐藏进度条
							Ext.MessageBox.hide();
							MessageUtil.showErrorMes(i18n('i18n.boReport.exportFailure'));//导出失败
						 };
						 btn.disable();
						 BoEvaluationStore.prototype.creatBoReportExcel(param,successFn,failureFn);		  
					  }
				  }
				]
			   },{
				 xtype:'middlebuttonpanel' 
			   },{
				xtype : 'rightbuttonpanel',
				items : [{//查询
				    text:i18n('i18n.boReport.search'),
				    xtype:'button',
				    id:'btnForDevelopSearch',
				    handler:function(btn){
				    	var devEffectEvaluationQueryForm = Ext.getCmp("devEffectEvaluationQueryPanel").getForm();
				    	//点击查询按钮时为全局变量devEndDate赋值
				    	devEndDate = devEffectEvaluationQueryForm.findField('endDate').getValue();
				    	devStartDate = devEffectEvaluationQueryForm.findField('startDate').getValue();
				    	//取出查询表单中各个条件的value
				    	var manageDept = devEffectEvaluationQueryForm.findField('manageDept').getValue();
						var businessDept = devEffectEvaluationQueryForm.findField('businessDept').getValue();
						//查询条件细化到事业部
                        if(!Ext.isEmpty(businessDept) && !Ext.isEmpty(manageDept)){
							deptId = businessDept;
				    		//上一级部门名称
				    		devSuperDeptName = devEffectEvaluationQueryForm.findField('businessDept').getRawValue();
				    		Ext.getCmp("deptNameColumn").setText(devSuperDeptName);
						}
						//查询条件细化到经营本部
						else if(Ext.isEmpty(businessDept) && !Ext.isEmpty(manageDept)){
							deptId = manageDept;
				    		//上一级部门名称
				    		devSuperDeptName = devEffectEvaluationQueryForm.findField('manageDept').getRawValue();
				    		Ext.getCmp("deptNameColumn").setText(devSuperDeptName);
						}
						
						//无任何查询条件
						else if(Ext.isEmpty(businessDept) && Ext.isEmpty(manageDept)){
							deptId = '';
				    		//上一级部门名称
				    		devSuperDeptName = i18n('i18n.boReport.divisionName');
				    		Ext.getCmp("deptNameColumn").setText(i18n('i18n.boReport.divisionName'));
						}
						else{
							MessageUtil.showErrorMes(i18n('i18n.boReport.searchConnot'));
							return false;
						}
						//查询按钮功能实现区
				    	Ext.getCmp('devEffectEvaluationListGrid').store.loadPage(1);
				  }
				 }]
			}];
		}
	});
	
	/**
	 * 客户开发效果评估查询结果表格
	 */
	Ext.define('CRM.report.BoListGrid',{
		extend:'SearchGridPanel',   
		columns:null,
		store:null,
		defaults:{
			align:'center'
		},
	   sortableColumns:false,//不能排序
	   enableColumnHide:false,//不能隐藏
	   enableColumnMove:false,//不能移动
		initComponent:function(){             
			var me = this;
			var store = Ext.create('CRM.Report.BOStore');
			store.on('beforeLoad',function(store,operation,e){
				
				//封装查询条件传递给action
				var searchParams = {
					'brc.deptId':deptId,
					'brc.endTime':devEndDate,
					'brc.beginTime':devStartDate
				};
				Ext.apply(operation,{
					params : searchParams
				});
			});
			me.store = store;
			me.columns = [{//经营本部   
				header : i18n('i18n.boReport.divisionName'),
				width:220,
				id:'deptNameColumn',
				dataIndex:'deptName',
				renderer:function(val,metaData,record){
					if(!Ext.isEmpty(val)){
						if(val.indexOf("总计")!== -1){
							return	'<span style="color:#ff0000">'+val+'</span>';
						}else if(record.get('deptId')=='227775'){
							return val;
						}else{
							return '<a href=javascript:clickToNextLevel("'+val+'","'+record.get('deptId')+'")>'+val+'</a>';
						}
					}				
				}
			},{//商机数量统计
				header : i18n('i18n.boReport.boNumSum'),
				columns: [{
							header:i18n('i18n.boReport.createNum'),
							// 新建商机数量
							dataIndex:'createNum'
						}, {
							header:i18n('i18n.boReport.ongoingNum'),
							// 当前进行中商机数
							dataIndex : 'ongoingNum'
						},{
							header:i18n('i18n.boReport.extendedNum'),
							// 当前超期进行中商机数
							dataIndex : 'extendedNum'
						}, {
							header:i18n('i18n.boReport.dormantNum'),
							// 当前已休眠商机数
							dataIndex : 'dormantNum'
						},{
							header:i18n('i18n.boReport.expectDeliveryAmount'),
							// 商机预计发货总金额
							dataIndex : 'expectDeliveryAmount'
						}]
			 },{//商机数量统计
				header : i18n('i18n.boReport.boStepTurn'),
				columns: [{
							header:i18n('i18n.boReport.contactNum'),
							// 当前初步接触阶段商机数
							dataIndex : 'contactNum'
						},{
							header:i18n('i18n.boReport.contactReturnVisitNum'),
							// 本月初步接触阶段商机回访次数
							dataIndex : 'contactReturnVisitNum'
						}, {
							header:i18n('i18n.boReport.contactTransNum'),
							// 本月初步接触阶段转化商机数
							dataIndex : 'contactTransNum'
						},{
							header:i18n('i18n.boReport.contactTransRate'),
							// 初步接触阶段转化率
							dataIndex : 'contactTransRate',
							renderer: function(value){
								return value+'%';
							}
						}, {
							header:i18n('i18n.boReport.analyzeNum'),
							// 当前需求分析阶段商机数
							dataIndex : 'analyzeNum'
						},{
							header:i18n('i18n.boReport.analyzeReturnVisitNum'),
							// 本月需求分析阶段商机回访次数
							dataIndex : 'analyzeReturnVisitNum'
						}, {
							header:i18n('i18n.boReport.analyzeTransNum'),
							// 本月需求分析阶段转化商机数
							dataIndex : 'analyzeTransNum'
						},{
							header:i18n('i18n.boReport.analyzeTransRate'),
							// 需求分析阶段转化率
							dataIndex : 'analyzeTransRate',
							renderer: function(value){
								return value+'%';
							}
						}, {
							header:i18n('i18n.boReport.schemeNum'),
							// 当前制定方案阶段商机数
							dataIndex : 'schemeNum'
						}, {
							header:i18n('i18n.boReport.schemeReturnVisitNum'),
							// 本月制定方案阶段商机回访次数
							dataIndex : 'schemeReturnVisitNum'
						}, {
							header:i18n('i18n.boReport.schemeTransNum'),
							// 本月制定方案阶段转化商机数
							dataIndex : 'schemeTransNum'
						}, {
							header:i18n('i18n.boReport.schemeTransRate'),
							// 制定方案阶段转化率
							dataIndex : 'schemeTransRate',
							renderer: function(value){
								return value+'%';
							}
						}, {
							header:i18n('i18n.boReport.offerNum'),
							// 当前报价/竞标阶段商机数
							dataIndex : 'offerNum'
						},{
                            header:i18n('i18n.boReport.offerReturnVisitNum'),
                            // 本月报价/竞标阶段商机回访次数
                            dataIndex : 'offerReturnVisitNum'
                        },{
							header:i18n('i18n.boReport.offerTransNum'),
							// 本月报价/竞标阶段转化商机数
							dataIndex : 'offerTransNum'
						},{
							header:i18n('i18n.boReport.offerTransRate'),
							// 报价/竞标阶段转化率
							dataIndex : 'offerTransRate',
							renderer: function(value){
								return value+'%';
							}
						}, {
							header:i18n('i18n.boReport.deliverNum'),
							// 当前持续发货阶段商机数
							dataIndex : 'deliverNum'
						} , {
							header:i18n('i18n.boReport.deliverReturnVisitNum'),
							// 本月持续发货阶段商机回访次数
							dataIndex : 'deliverReturnVisitNum'
						}, {
							header:i18n('i18n.boReport.deliverTransNum'),
							// 本月持续发货阶段转化商机数
							dataIndex : 'deliverTransNum'
						}, {
							header:i18n('i18n.boReport.deliverTransRate'),
							// 持续发货阶段转化率
							dataIndex : 'deliverTransRate',
							renderer: function(value){
								return value+'%';
							}
						}]
			},{//商机开发结果统计
					header : i18n('i18n.boReport.boDevelopResult'),
					columns: [{
						header:i18n('i18n.boReport.failureNum'),
						// 失败关闭商机数
						dataIndex : 'failureNum',
						summaryType: 'sum'
					},{
						header:i18n('i18n.boReport.successNum'),
						// 成功关闭商机数
						dataIndex : 'successNum',
						summaryType: 'sum'
					}, {
						header:i18n('i18n.boReport.successRate'),
						// 商机成功率
						dataIndex : 'successRate',
						renderer: function(value){
							return value+'%';
						}
					}, {
						header:i18n('i18n.boReport.deliverAmount'),
						// 开发成功商机对应客户发货量
						dataIndex : 'deliverAmount',
						summaryType: 'sum'
					}]
			},{//商机任务执行统计
				header : i18n('i18n.boReport.boPlanCount'),
				columns: [{
					header:i18n('i18n.boReport.scheduleNum'),
					// 本月应执行的商机日程数量
					dataIndex : 'scheduleNum',
					summaryType: 'sum'
				}, {
					header:i18n('i18n.boReport.performNum'),
					// 本月内实际执行的商机日程数量
					dataIndex : 'performNum',
					summaryType: 'sum'
				},{
					header:i18n('i18n.boReport.performRate'),
					// 本月商机日程完成率
					dataIndex : 'performRate',
					renderer: function(value){
						return value+'%';
					}
			  }]
			}];
			this.callParent();
		}
	});

	
	/*
	 * 新建一个viewport，用于显示客户开发效果评估界面
	 * 肖红叶
	 */
	var viewport=Ext.create('Ext.Viewport',{
		layout : 'border',
		items:[
	        {//营销效果评估页面中查询条件输入面板form
	        	xtype:'container',
				region:'north',
				layout:'fit',
				items:[Ext.create('CRM.report.BoQueryPanel',{id:'devEffectEvaluationQueryPanel'})]
			},{
				xtype:'container',
				region:'center',
				layout:'border',
				items:[
					{//按钮面板
						xtype:'container',
						region:'north',
					    layout:'fit',
					    items:[Ext.create('CRM.report.BoBtnPanel',{id:'devEffectEvaluationBtnPanel'})]
					},{//客户开发效果评估查询结果表格
						xtype:'container',
						region:'center',
			    	    layout:'fit',
			    	    items:[ Ext.create('CRM.report.BoListGrid',{id:'devEffectEvaluationListGrid'})]
			        }
			   ]
			}
	    ]
	});
	
	viewport.setAutoScroll(false);	
	viewport.doLayout();
});
/**
 * comboBox的change事件重写，使其达到可以删除，且只能选择下拉框里面的内容
 */
function comboSelsct(combo){
		if(Ext.isEmpty(combo.getValue())){
			combo.setValue("");
	}
};
/**
 * 定义一个函数，当单击表格中除总计行外的其他记录时，进入当前行统计信息的详细信息列表
 * @param 部门名称
 * @param 部门Id
 */
function clickToNextLevel(deptName,IndeptId){
	deptId = IndeptId;
	if(Ext.isEmpty(IndeptId)){//当deptId为空的时候 查询经营本部
		Ext.getCmp("deptNameColumn").setText(i18n('i18n.boReport.divisionName'));
	}else{
		Ext.getCmp("deptNameColumn").setText(deptName);
	}
	Ext.getCmp('devEffectEvaluationListGrid').store.loadPage(1);
}

/**
 * initCombobox，根据用户初始化开发和维护查询条件中combobox的默认显示
 */
function initCombobox(){
	//取出查询表单中各个条件
	var manageDept = Ext.getCmp('manageDept');
	var businessDept = Ext.getCmp('businessDept');
	//当登录用户具有某个经营本部的查询权限
	if(AssessAuthority.assessAuthority==='400000127'){ 
		manageDept.disable();
		if(!Ext.isEmpty(AssessAuthority.assessDept)){
			var manageDeptModel=new DeptListModel();
			manageDeptModel.set("id",AssessAuthority.assessDept.id);
			deptAuthId = AssessAuthority.assessDept.id;
			manageDeptModel.set("deptName",AssessAuthority.assessDept.deptName);
			var manageDeptStore = manageDept.getStore();
			manageDeptStore.add(manageDeptModel);
			manageDept.setValue(AssessAuthority.assessDept.id);
			Ext.getCmp("businessDept").store.load({params:{'parentDeptId':AssessAuthority.assessDept.id}});
		}
		
	}
	//当登录用户具有某个事业部的查询权限
	else if(AssessAuthority.assessAuthority==='400000146'){
		manageDept.disable();
		businessDept.disable();
		if(!Ext.isEmpty(AssessAuthority.assessDept)){
			var manageDeptModel=new DeptListModel();
			manageDeptModel.set("id",AssessAuthority.assessDept.id);
			manageDeptModel.set("deptName",AssessAuthority.assessDept.deptName);
			var manageDeptStore = manageDept.getStore();
			manageDeptStore.add(manageDeptModel);
			
			manageDept.setValue(AssessAuthority.assessDept.id);
			if(!Ext.isEmpty(AssessAuthority.assessDept.childDept)){
				var businessDeptModel=new DeptListModel();
				businessDeptModel.set("id",AssessAuthority.assessDept.childDept.id);
				businessDeptModel.set("deptName",AssessAuthority.assessDept.childDept.deptName);
				var businessDeptStore = businessDept.getStore();
				businessDeptStore.add(businessDeptModel);
				businessDept.setValue(AssessAuthority.assessDept.childDept.id);
				deptAuthId = AssessAuthority.assessDept.childDept.id;
				businessDept.setRawValue(AssessAuthority.assessDept.childDept.deptName);
			}
		}
		
	}
	//当登录用户具有全国的查询权限
	else if(AssessAuthority.assessAuthority==='400000147'){
		deptAuthId = '0';
		manageDept.enable();
		businessDept.enable();
		Ext.getCmp("manageDept").store.load();	
	}
	//当非法用户进入查询页面
	else{
		manageDept.disable();
		businessDept.disable();
		deptAuthId = 'noAuth';
	}
}