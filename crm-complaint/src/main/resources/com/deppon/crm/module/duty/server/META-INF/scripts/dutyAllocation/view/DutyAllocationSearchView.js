/**
 * 工单责任划分页面
 */
var isSearch = false;//是否点击查询
/*
 * 新建一个viewport，用于显示工单责任划分界面
 * 肖红叶
 */
var viewport = null;
Ext.onReady(function(){	
	//页面需要加载的数据字典数组
	var keys=[
	    'COMPLAINT_FIELD', /*客户工单查询条件*/
		'REPORT_TYPE' ,/*上报类型*/
		'COMPLAINT_PROCESS_STATUS', /*工单模块处理状态*/
		'RESOLVE_CASE' ,    /*解决情况*/
		'EXPECTED_TIME_LIMIT',//期望时限
	    'WORK_SINGLE_SOURCE',/*工单来源*/
	    'PRIORITY_RATING',/*优先级别*/
		'COMPLAINT_LEVEL',	/* 投诉级别 */
		'COMPLAINT_RECORD_TYPE',/*工单模块反馈类型*/
		'SATISFACTION_DEGREE',/*客户满意度*/
		'CALLER_TYPE',/*来电人类型*/
         // 客户类型
      	'CUSTOMER_TYPE',
  		// 目标级别,会员等级
  		'MEMBER_GRADE',
  		'DUTY_TOTAL_SURVEY_RESULT',
  		'COMPLAINT_BUSINESS_MODEL'//业务模式
  	];
	//初始数据字典
	initDataDictionary(keys);
	//初始化提示信息条
	Ext.QuickTips.init();
	initDeptAndUserInfo();
	initAuthOrReportType();
	/**
	 *工单责任划分经理查询条件输入面板form
	 */
	Ext.define('DutyAllocationQueryPanel',{
		extend:'SearchFormPanel', 
		items:null,
		border:0,
		layout:{
			type:'table',
			columns:6
		},
		defaults:{
			labelWidth:60,
			labelAlign:'right',
			width:200,
			margin:'0 5 5 0'
		},
		defaultType:'textfield',
		initComponent:function(){			
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [
			    {//待划分投诉/异常数
			    	xtype:'displayfield',
			    	value : i18n('i18n.Duty.searchDutyPanel.unallocatDuty')+'--'+'<span name="unreceiveDutyType" style="color: #FF0000">'+reportType
			    	+'</span>'+i18n('i18n.Duty.searchDutyPanel.unallocatDutyNum'
	    			)+i18n('i18n.DutyAllocationSearchView.colon'),
					labelWidth:0,
					width:130,
					margin:'0 0 10 0',
					colspan:5,
					labelSeparator:'',
					labelAlign:'left',
					name : 'unreceiveDutyType' 
			    },{//待划分投诉/异常数
			    	xtype:'displayfield',
			    	value: '<span name="unreceiveDutyNum" style="color: #FF0000">0</span>',
					labelWidth:0,
					width:20,
					margin:'0 0 10 -380',
					labelSeparator:'',
					labelAlign:'left',
					name : 'unreceiveDutyNum' 
			    },{//编号类型选择
			    	xtype:'combobox',
			    	labelSeparator:'',
			    	fieldLabel:'',
			    	labelWidth:0,
			    	width:90,
			    	store:getDataDictionaryByName(DataDictionary,'COMPLAINT_FIELD'),
			    	queryMode: 'local',
			    	displayField:'codeDesc',
			    	valueField:'code',
			    	forceSelection:true,
					name:'codeType',
					listeners:{
						//comboBox的change事件重写，使其达到可以删除，且只能选择下拉框里面的内容
						change:function(th){
							DutyUtil.comboSelsct(th);
						}
					}
				},{//编号内容输入
					labelSeparator:'',
					fieldLabel:'',
			    	width:145,
					name:'code'
				},{//接入人
					fieldLabel:i18n('i18n.Duty.searchDutyPanel.receiveUser'),
					labelWidth:40,
					width:190,
					name:'receiveUser'
					
				},{//业务模式
					fieldLabel :i18n('i18n.duty.businessModel'),
					name : 'businessModel',
					labelWidth:55,
					xtype : 'combobox',
					store :getDataDictionaryByName(DataDictionary,'COMPLAINT_BUSINESS_MODEL'),
					queryMode : 'local',
					forceSelection : true,
					editable:false,
					displayField : 'codeDesc',
					valueField : 'code',
					width:170,
					colspan:3,
					listeners:{
						beforerender:function(f){
							f.getStore().insert(0,{'code':'','codeDesc':i18n('i18n.duty.all')});
							f.setValue('');
						}
					}
				},{//接入时间 
					xtype:'datetimefield',
					width:240,
					labelWidth:90,
					fieldLabel:i18n('i18n.Duty.searchDutyPanel.receiveTime'),
					format: 'Y-m-d H:i:s',
					editable:false,
					value:Ext.Date.parse(Ext.Date.format(Ext.Date.add(new Date(),Ext.Date.DAY,-30),'Y-m-d')+' 00:00:00','Y-m-d H:i:s'),
					name:'receiveBeginTime',
					colspan:2,
					defaultTime:[0,0,0]
				},{//接入时间到
					xtype:'datetimefield',
					labelWidth:40,
					value:Ext.Date.parse(Ext.Date.format(new Date(),'Y-m-d')+' 23:59:59','Y-m-d H:i:s'),
					width:190,
					fieldLabel:i18n('i18n.Duty.searchDutyPanel.receiveTimeTo'),
					format: 'Y-m-d H:i:s',					
					editable:false,
					name:'receiveEndTime',
					colspan:4,
					defaultTime:[23,59,59]
				}
		    ];
		}
	}); 
	
	//操作按钮面板---只有当登陆人为经理时才显示
	Ext.define('DutyAllocationButtonPanel',{
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
				width:150,
				items:[
				  {//责任划分
					  xtype:'button',
					  text:i18n('i18n.Duty.receiveDutyPanel.allocatDuty'),
					  handler:function(){
					  	var selection = Ext.getCmp('dutyAllocationResultGridId').getSelectionModel().getSelection();
					  	if(Ext.isEmpty(selection) || selection.length!=1){
							MessageUtil.showMessage(i18n('i18n.DutyAllocationSearchView.pleaseSelectOneData'));return;//请选择数据一条数据
						}
						  //显示责任划分操作窗口
					  	var dutyAllocationOperationWindow = Ext.getCmp("dutyAllocationOperationWindowId");//获取工单责任划分操作窗口
						if(!dutyAllocationOperationWindow){
							dutyAllocationOperationWindow = Ext.create('DutyAllocationOperationWindow',{id:'dutyAllocationOperationWindowId'});
						}
						dutyAllocationOperationWindow.show();
						Ext.getCmp('temporaryBtnId').hide();/*经理隐藏暂存按钮*/
						dutyAllocationOperationWindow.dutyDivideLook(selection[0]);
					  }
				  }
				]
			   },{
				 xtype:'middlebuttonpanel' 
			   },{
				xtype : 'rightbuttonpanel',
				width:150,
				items : [{//查询
				    text:i18n('i18n.duty.search'),
				    xtype:'button',
				    handler:function(){
				    	isSearch = true;
				    	firstLoad=1;
				    	var dutyAllocationQueryForm = Ext.getCmp("dutyAllocationQueryPanelId").getForm();
				    	var codeType = dutyAllocationQueryForm.findField("codeType").getValue();
				    	var code = dutyAllocationQueryForm.findField("code").getValue();
				    	var receiveUser = dutyAllocationQueryForm.findField("receiveUser").getValue();
				    	var receiveBeginTime = dutyAllocationQueryForm.findField("receiveBeginTime").getValue();
				    	var receiveEndTime = dutyAllocationQueryForm.findField("receiveEndTime").getValue();
				    	/**
				    	 * 1校验日期如果开始时间和结束时间不能为空
				    	 * 2校验开始时间和结束时间差值不能超过31天且开始时间不能晚于结束时间
				    	 * 3如果选择了查询类型 所查字段不能为空
				    	 * 4如果填写了查询字段 查询类型不能为空
				    	 */
				    	if( Ext.isEmpty(receiveBeginTime) || Ext.isEmpty(receiveBeginTime)){
				    		//开始日期或结束日期不能为空
				    		MessageUtil.showErrorMes(i18n('i18n.Duty.searchDutyErrorMesg.searchTimeNotNull'));
				    		return false;
				    	}
				    	if(receiveEndTime-receiveBeginTime-86400000*31>0){
				    		//查询时间最大不能大于31天
				    		MessageUtil.showErrorMes(i18n('i18n.Duty.searchDutyErrorMesg.searchTimeMoreThenOneMonth'));
				    		return false;
				    	}else if(receiveEndTime-receiveBeginTime<0){
				    		//所查开始时间不能大于结束时间
				    		MessageUtil.showErrorMes(i18n('i18n.Duty.searchDutyErrorMesg.searchEndTimeLessThenStartTime'));	
				    		return false;
				    	}
				    	if( Ext.isEmpty(codeType)&& !Ext.isEmpty(code)){
				    		//请选择查询条件
				    		MessageUtil.showErrorMes(i18n('i18n.Duty.searchDutyErrorMesg.searchConditionNotNull'));	
				    		return false;
				    	}else if(!Ext.isEmpty(codeType)&& Ext.isEmpty(code)){
				    		//请输入查询内容
				    		MessageUtil.showErrorMes(i18n('i18n.Duty.searchDutyErrorMesg.searchContentNotNull'));	
				    		return false;
				    	}
				    	countUnreceiveDutyNum();
				    	var store = Ext.getCmp('dutyAllocationResultGridId').getStore();				    
				    	store.loadPage(1);				    	
				  }
				 }]
			}];
		}
	});
	
	/**
	 *工单责任划分员工接入按钮面板--只有当登陆人为组员时才显示
	 */
	Ext.define('DutyAllocationJoinComplaintPanel',{
		extend:'SearchFormPanel', 
		border:false,
		layout : {
			type : 'table',
			columns : 4
		},
		margin:'2 0 0 0',
		items:null,
		initComponent:function(){			
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [{//快递投诉/异常数
			    	xtype:'displayfield',
			   		value : i18n('i18n.duty.express')+'<span name="unreceiveDutyType" style="color: #FF0000">'+reportType
			    	+'</span>'+i18n('i18n.Duty.searchDutyPanel.unallocatDutyNum'
			    			)+i18n('i18n.DutyAllocationSearchView.colon'),
					labelWidth:0,
					width:130,
					labelSeparator:'',
					labelAlign:'left',
					margin:'0 0 5 100',
					name : 'expressDutyNumLabel'
					
			    },{//快递投诉/异常数
			    	xtype:'displayfield',
			    	value :'<span name="unreceiveDutyNum" style="color: #FF0000">0</span>',
					labelWidth:0,
					width:20,
					labelSeparator:'',
					labelAlign:'left',
					name : 'expressDutyNum'
			    },{//接入快递按钮
					xtype:'button',
					id:'receiveExpressDutyButtonId',
					name:'receiveDutyType',
					text:i18n('i18n.Duty.receiveDutyPanel.receiveExpressDuty')+'<span name="unreceiveDutyType" style="color: #FF0000">'+reportType+'</span>',
					margin:'0 0 0 6',
					colspan:2,
					handler:function(btn){
						commonReceive(1);						
					}
				},
		        {//待划分投诉/异常数
			    	xtype:'displayfield',
					labelWidth:0,
					width:130,
					labelSeparator:'',
					labelAlign:'left',
					colspan:3,
					name : 'totalDutyNum'
			    },{//责任划分
					xtype:'button',
					margin:'0 0 0 120',
					text:i18n('i18n.Duty.receiveDutyPanel.allocatDuty'),
					handler:function(){
						//显示责任划分操作窗口
						var selection = Ext.getCmp('dutyAllocationResultGridId').getSelectionModel().getSelection();
					  	if(Ext.isEmpty(selection) || selection.length!=1){
							MessageUtil.showMessage(i18n('i18n.Duty.searchDutyErrorMesg.singleRecord'));return;
						}
						  //显示责任划分操作窗口
					  	var dutyAllocationOperationWindow = Ext.getCmp("dutyAllocationOperationWindowId");//获取工单责任划分操作窗口
						if(!dutyAllocationOperationWindow){
							dutyAllocationOperationWindow = Ext.create('DutyAllocationOperationWindow',{id:'dutyAllocationOperationWindowId'});
						}
						dutyAllocationOperationWindow.show();
						Ext.getCmp('temporaryBtnId').show();/*处理员显示暂存按钮*/
						dutyAllocationOperationWindow.dutyDivideLook(selection[0]);	
					}
				},{//零担投诉/异常数
			    	xtype:'displayfield',
			   		value : i18n('i18n.duty.unExpress')+'<span name="unreceiveDutyType" style="color: #FF0000">'+reportType
			    	+'</span>'+i18n('i18n.Duty.searchDutyPanel.unallocatDutyNum'
			    			)+i18n('i18n.DutyAllocationSearchView.colon'),
					labelWidth:0,
					width:130,
					labelSeparator:'',
					labelAlign:'left',
					margin:'0 0 5 100',
					name : 'unExpressDutyNumLabel'
			    },{//零担投诉/异常数
			    	xtype:'displayfield',
			    	value :'<span name="unreceiveDutyNum" style="color: #FF0000">0</span>',
					labelWidth:0,
					width:20,
					labelSeparator:'',
					labelAlign:'left',
					name : 'unExpressDutyNum'
			    },{//接入零担按钮
					xtype:'button',
					id:'receiveUnExpressDutyButtonId',
					name:'receiveDutyType',
					text:i18n('i18n.Duty.receiveDutyPanel.receiveUnExpressDuty')+'<span name="unreceiveDutyType" style="color: #FF0000">'+reportType+'</span>',
					margin:'0 0 0 6',
					colspan:2,
					handler:function(btn){
						commonReceive(2);
					}
				}
		    ];
		}
	});	
	
	/**
	 * 工单责任划分查询结果表格
	 */
	
	Ext.define('DutyAllocationResultGrid',{
		extend:'SearchGridPanel',
		defaults:{
			align:'center'
		},
		autoScroll:true,
		selModel:Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE'}),
		listeners:{
			sortchange:function(){
				//改变暂存的工单颜色
				changeGridColor(Ext.getCmp('dutyAllocationResultGridId').store);
			}
		},
		initComponent:function(){ 
			var me = this;
			var store = null;
			if( receiveRole==constance_manager){
				store = Ext.create('SearchReceiveDutyStore',{
					listeners:{
						beforeLoad:function(store,operation,e){
							var form = Ext.getCmp("dutyAllocationQueryPanelId").getForm();
							var param = null;
								if(firstLoad==1){			
									param ={
							    			'searchDutyCondition.codeType':form.findField("codeType").getValue(),
							    			'searchDutyCondition.code':form.findField("code").getValue(),
							    			'searchDutyCondition.receiveUser':form.findField("receiveUser").getValue(),
							    			'searchDutyCondition.receiveBeginTime':form.findField("receiveBeginTime").getValue(),
							    			'searchDutyCondition.receiveEndTime':form.findField("receiveEndTime").getValue(),
							    			'searchDutyCondition.firstLoad':firstLoad,
							    			'searchDutyCondition.businessModel':form.findField("businessModel").getValue()
							    	}
								}else{
									param ={
							    			'searchDutyCondition.firstLoad':firstLoad
							    	}
								}
						    	
					    		Ext.apply(operation,{
									params : param
								});
						},
						load:function(store,operation,e){
							changeGridColor(store);
							if(store.getCount()==0&&isSearch){
								MessageUtil.showErrorMes(i18n('i18n.Duty.dutyList.isNull'));	
							}
						}
					}
				});
			}else if(receiveRole==constance_employee){
				store = Ext.create('ReceiveDutyStore');
				store.on('load',function(store,records){
					var receiveButton = Ext.getCmp('receiveExpressDutyButtonId');
					var receiveButton2 = Ext.getCmp('receiveUnExpressDutyButtonId');
					if(me.store.getCount()<10){
						receiveButton.enable();
						receiveButton2.enable();
					}/*else{
						receiveButton.disable();
						receiveButton2.disable();
					}*/
					changeGridColor(store);
				});
			}
			me.store = store;
			me.columns = [
			    {//序号
			    	xtype:'rownumberer',
					header:i18n('i18n.Duty.resultDutyPanel.rownumberer'),
					width:40
			    },{//接入时间
					header:i18n('i18n.Duty.resultDutyPanel.receiveTime'),
					renderer : DutyUtil.renderDateTime,
					width:140,
					dataIndex:'receiveTime'
			    },{ //接入人
					header :i18n('i18n.Duty.resultDutyPanel.receiveUser'),
					dataIndex:'receiveUser',
					width:80
			    },{ //业务模式
					header :i18n('i18n.duty.businessModel'),
					dataIndex:'businessModel',
					width:80,
					renderer:function(v){
						return rendererDictionary(v,DataDictionary.COMPLAINT_BUSINESS_MODEL);
					}
			    },{//凭证号
					header :i18n('i18n.Duty.resultDutyPanel.voucherNumber'),
					dataIndex:'voucherNumber',
					width:100
			    },{//处理编号
					header :i18n('i18n.Duty.resultDutyPanel.dealNumber'),
					dataIndex:'dealNumber',
					width:120
			    },{//重复工单码
					header :i18n('i18n.Duty.resultDutyPanel.rebindNo'),
					dataIndex:'rebindNo',
					width:100
			    },{//上报类型
					header :i18n('i18n.Duty.resultDutyPanel.reportType'),
					dataIndex:'reportType',
					width:80,
					renderer:function(value) {
						return rendererDictionary(value,DataDictionary.REPORT_TYPE);
					}
			   },{//来电人
					header :i18n('i18n.Duty.resultDutyPanel.caller'),
					dataIndex:'caller',
					width:80
			    },{//优先级别
			    	header :i18n('i18n.Duty.resultDutyPanel.pririty'),
					dataIndex:'pririty',
					width:80,
					renderer:function(value) {
						return rendererDictionary(value,DataDictionary.PRIORITY_RATING);
					}
			    },{//处理状态
			    	header :i18n('i18n.Duty.resultDutyPanel.proStatus'),
					width:100,
					dataIndex:'proStatus',
					renderer:function(value) {
						return rendererDictionary(value,DataDictionary.COMPLAINT_PROCESS_STATUS);
					}
			    },/*{ //上报人
			    	header :i18n('i18n.Duty.resultDutyPanel.reporter'),
					dataIndex:'reporter',
					width:80
			    },{//上报时间
			    	header :i18n('i18n.Duty.resultDutyPanel.reportTime'),
					dataIndex:'reportTime',
					renderer : DutyUtil.renderDateTime,
					width:100
			    },*/{//调查结果
			    	header :i18n('i18n.Duty.resultDutyPanel.surveyResult'),
					dataIndex:'surveyResult',
					width:80,
					renderer:function(value) {
						return rendererDictionary(value,DataDictionary.DUTY_TOTAL_SURVEY_RESULT);
					}
			    }
		    ];
			if(receiveRole == constance_manager){
				me.dockedItems=[{
					xtype:'pagingtoolbar',
					cls:'pagingtoolbar',
					store:store,
					dock:'bottom',
					displayInfo : true,
					autoScroll : true,
					items:[{//每页显示
						text: i18n('i18n.Duty.resultDutyPanel.numPerPage'),
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
		}
	    this.callParent();
		countUnreceiveDutyNum();
	   }
	});
	
	if( receiveRole == constance_manager){
		viewport=Ext.create('Ext.Viewport',{
			layout : 'border',
			items:[
		        {//责任划分查询条件输入面板
		        	xtype:'container',
					region:'north',
					layout:'fit',
					items:[Ext.create('DutyAllocationQueryPanel',{id:'dutyAllocationQueryPanelId'})]
				},{
					xtype:'container',
					region:'center',
					layout:'border',
					items:[
						{//按钮面板
							xtype:'container',
							region:'north',
						    layout:'fit',
						    items:[Ext.create('DutyAllocationButtonPanel',{id:'dutyAllocationButtonPanelId'})]
						},{//责任划分查询结果表格
							xtype:'container',
							region:'center',
				    	    layout:'fit',
				    	    items:[ Ext.create('DutyAllocationResultGrid',{id:'dutyAllocationResultGridId'})]
				        }	
				   ]
				}
		    ]
		});
	}else if(receiveRole==constance_employee){
		viewport=Ext.create('Ext.Viewport',{
			layout : 'border',
			items:[
		        {//责任划分查询条件输入面板
		        	xtype:'container',
					region:'north',
					layout:'fit',
					items:[Ext.create('DutyAllocationJoinComplaintPanel',{id:'dutyAllocationQueryPanelId'})]
				},{
					xtype:'container',
					region:'center',
					layout:'border',
					items:[
						{//责任划分查询结果表格
							xtype:'container',
							region:'center',
				    	    layout:'fit',
				    	    items:[ Ext.create('DutyAllocationResultGrid',{id:'dutyAllocationResultGridId'})]
				        }	
				   ]
				}
		    ]
		});
	}

	viewport.setAutoScroll(false);	
	viewport.doLayout();
});
var reportType = null;
var receiveRole = null;
var constance_complaint = i18n('i18n.Duty.dutyType.complaint');
var constance_unusual = i18n('i18n.Duty.dutyType.unusual');
var constance_manager = 'MANAGER';
var constance_employee = 'EMPLOYEE';
var firstLoad = 0;
function initAuthOrReportType(){
	var text = null;
	var text1 = null;
	var roleIds = User.roleids;
	if( isPermission('/complaint/insertComplaint.action') || isPermission('/common/complaintProcess.action') ){		
		reportType = constance_complaint;
	}else if(isPermission('/complaint/insertException.action')  || isPermission('/common/exceptionProcess.action') ){
		reportType = constance_unusual;
	}
	if( isPermission('/complaint/insertComplaint.action')  || isPermission('/complaint/insertException.action') ){
		receiveRole = constance_employee;
	}else if( isPermission('/common/complaintProcess.action') || isPermission('/common/exceptionProcess.action')){
		receiveRole = constance_manager;
	}
};
function countUnreceiveDutyNum(){
	//更新接入条数成功函数
	var countSuccessFn = function(result){
		var totalText =i18n('i18n.duty.tobereceive')+'(<span name="unreceiveDutyNum" >'+result.numMap.totalCount+'</span>)';	
		var expressText ='<span style="color: #FF0000">'+result.numMap.expressCount+'</span>';	
		var unExpressText ='<span style="color: #FF0000">'+result.numMap.unExpressCount+'</span>';	
		var form =Ext.getCmp("dutyAllocationQueryPanelId").getForm();
		if(!form.findField("unreceiveDutyNum")){
			form.findField("totalDutyNum").setValue(totalText);
			form.findField("expressDutyNum").setValue(expressText);
			form.findField("unExpressDutyNum").setValue(unExpressText);
		}else{
			form.findField("unreceiveDutyNum").setValue('<span name="unreceiveDutyNum" style="color: #FF0000">'+result.numMap.totalCount+'</span>');
		}
		
	};
	//更新接入条数失败函数
	var countFailFn = function(result){
	};
	DutyAllocationStore.prototype.countUnreceiveDutyNum(null,countSuccessFn,countFailFn);
};
function changeGridColor(store){
	 var girdcount=0;   
     store.each(function(r){   
     	if(r.get('status')=='TEMPORARY'){
        	//给单元格涂色
        	var cells = Ext.getCmp("dutyAllocationResultGridId").getView().getNodes()[girdcount].children;
  			for(var i= 0;i<cells.length;i++){
  				cells[i].style.backgroundColor='#28FF28';
  			}
         }
  		girdcount++;
     });
}

function commonReceive(type){
	var store = Ext.getCmp('dutyAllocationResultGridId').store;	
		Ext.getCmp('receiveExpressDutyButtonId').disable();
		Ext.getCmp('receiveUnExpressDutyButtonId').disable();
		if(store.getCount()>10){
			MessageUtil.showErrorMes(i18n('i18n.Duty.searchDutyErrorMesg.beyondMaxReceiveNum'));
			return false;
		}
		if(store.getCount()>0){
			var businessModel=store.getAt(0).get('businessModel');
			if((type==1&&businessModel=='UNEXPRESS')||(type==2&&businessModel=='EXPRESS')){
				MessageUtil.showErrorMes(i18n('i18n.Duty.searchDutyErrorMesg.pleaseChooseTheSameModel'));
				setTimeout(function(){
					Ext.getCmp('receiveExpressDutyButtonId').enable();
					Ext.getCmp('receiveUnExpressDutyButtonId').enable();
				},2000);
				return false;
			}
		}
		var reSuccessFn = function(result){
			countUnreceiveDutyNum();//在成功之后才能去刷新数字	
			//获得结果列表的store
			var store = Ext.getCmp("dutyAllocationResultGridId").store;
			//接入新工单列表
			var receiveDutys = result.receiveDutys;							
			//将新接入的工单加载到列表中							
			Ext.each(receiveDutys,function(r){
				store.add(new DutySearchDetailModel(r));
			});
			changeGridColor(store);					
			//计算store的记录数
			var count = store.getCount();
			//如果小于10条则
			if(count < 10){
				//限制接入按钮的点击频率 每两秒点击一次
				setTimeout(function(){
					Ext.getCmp('receiveExpressDutyButtonId').enable();
					Ext.getCmp('receiveUnExpressDutyButtonId').enable();
				},2000);	
			//如果接入责任大于10条则禁用接入按钮
			}/*else if( count >= 10){
				Ext.getCmp('receiveExpressDutyButtonId').disable();
				Ext.getCmp('receiveUnExpressDutyButtonId').disable();
			}*/
		};
		var reFailFn = function(result){
			setTimeout(function(){
				Ext.getCmp('receiveExpressDutyButtonId').enable();
				Ext.getCmp('receiveUnExpressDutyButtonId').enable();
			},2000);
			MessageUtil.showErrorMes(result.message);
		};				
		DutyAllocationStore.prototype.receiveDuty({type:type},reSuccessFn,reFailFn);
}
		

