/**
 * 工单责任查询页面
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
  		'DUTY_SURVEY_RESULT',
  		'DUTY_RESULT_STATUS',
  		'COMPLAINT_BUSINESS_MODEL'
  	];
	//初始数据字典
	initDataDictionary(keys);
	var dutyId =null;
	//初始化提示信息条
	Ext.QuickTips.init();
	
	/**
	 *工单责任查询条件输入面板form
	 */
	Ext.define('DutySearchPanel',{
		extend:'SearchFormPanel', 
		items:null,
		border:0,
		layout:{
			type:'table',
			columns:4
		},
		defaults:{
			labelWidth:75,
			labelAlign:'right',
			width:240,
			margin:'0 5 5 0'
		},
		defaultType:'textfield',
		initComponent:function(){			
			this.items = this.getItems();
			this.callParent();
			var store = Ext.getCmp('dutyStates').store;
			store.remove(store.findRecord('code','NO_DUTY'));//确认无责
			store.remove(store.findRecord('code','APPROVALING'));//反馈审批中
			store.remove(store.findRecord('code','STATIS_TRUNING_BACK'));//统计员退回
		},
		getItems:function(){
			var me = this;
			// 获取当前时间的前31天
			var nowDateLong = new Date();
			// 获取当前时间
			var nowDate = new Date();
			// 获取当前时间的前31天
			nowDateLong.setDate(nowDateLong.getDate()-30);
			return [
			    {//责任划分人
			    	fieldLabel : i18n('i18n.Duty.DutyApproval.DutyAllocationMan'),//i18n('i18n.Duty.DutyApproval.DutyAllocationMan'),
			    	colspan:2,
			    	width:240,
			    	id:'appDutyUser',
					name : 'appDutyUser'
			    },{//上报时间
			    	xtype:'datetimefield',
			    	fieldLabel:i18n('i18n.Duty.resultDutyPanel.reportTime'),//i18n('i18n.Duty.resultDutyPanel.reportTime'),
			    	format:'Y-m-d H:i:s',
			    	setDefaultTime:[0,0,0],
			    	value:new Date(nowDateLong.getFullYear(),nowDateLong.getMonth(),nowDateLong.getDate(),0,0,0),
					name:'reportTimeBegin',
					labelWidth:80,
					listeners:{
			    		expand:function(t){
			    			if(Ext.isEmpty(t.getValue())){
			    			t.setValue(new Date(nowDateLong.getFullYear(),nowDateLong.getMonth(),nowDateLong.getDate()));
			    			}
			    		}
			    	},
					id:'reportTimeBegin'
				},{//上报时间到
					xtype:'datetimefield',
			    	fieldLabel:i18n('i18n.Duty.searchDutyPanel.receiveTimeTo'),//i18n('i18n.Duty.DutyApproval.FeedbackTimeEnd'),
			    	format:'Y-m-d H:i:s',
			    	setDefaultTime:[23,59,59],
			    	value:new Date(nowDate.getFullYear(),nowDate.getMonth(),nowDate.getDate(),23,59,59),
					name:'reportTimeEnd',
					listeners:{
			    		expand:function(t){
			    			if(Ext.isEmpty(t.getValue())){
			    			t.setValue(new Date(nowDate.getFullYear(),nowDate.getMonth(),nowDate.getDate(),23,59,59));
			    			}
			    		}
			    	},
					id:'reportTimeEnd'
				},{//编号类型选择
			    	xtype:'combobox',
			    	labelSeparator:'',
			    	margin:'0 0 5 10',
			    	fieldLabel:'',
			    	labelWidth:0,
			    	value:"002",
			    	width:90,
			    	store:Ext.create('Ext.data.Store', {
			    	    fields: ['code', 'codeDesc'],
			    	    data : [
			    	        {"code":"001", "codeDesc":i18n('i18n.Duty.resultDutyPanel.dealNumber')},//"处理编号"},
			    	        {"code":"002", "codeDesc":i18n('i18n.Duty.resultDutyPanel.voucherNumber')}//"凭证号"}
			    	    ]
			    	}),
			    	queryMode:'local',
			    	editable:false,
			    	displayField:'codeDesc',
					valueField:'code',
					name:'typeId',
					id:'typeId'
				},{//编号内容输入
					labelSeparator:'',
					fieldLabel:'',
					labelWidth:2,
			    	width:140,
					name:'typeName',
					id:'typeName'
				},{//反馈时间
			    	xtype:'datetimefield',
			    	fieldLabel:i18n('i18n.Duty.DutyApproval.FeedbackTimeStart'),//i18n('i18n.Duty.DutyApproval.FeedbackTimeStart'),
			    	labelWidth:80,
			    	setDefaultTime:[0,0,0],
			    	format:'Y-m-d H:i:s',
			    	listeners:{
			    		expand:function(t){
			    			if(Ext.isEmpty(t.getValue())){
			    			t.setValue(new Date(nowDateLong.getFullYear(),nowDateLong.getMonth(),nowDateLong.getDate()));
			    			}
			    		}
			    	},
					name:'feedBackTimeBegin',
					id:'feedBackTimeBegin'
				},{//反馈时间到
					xtype:'datetimefield',
			    	fieldLabel:i18n('i18n.Duty.searchDutyPanel.receiveTimeTo'),//i18n('i18n.Duty.DutyApproval.FeedbackTimeEnd'),
			    	format:'Y-m-d H:i:s',
			    	setDefaultTime:[23,59,59],
			    	listeners:{
			    		expand:function(t){
			    			if(Ext.isEmpty(t.getValue())){
			    			t.setValue(new Date((new Date).getFullYear(),(new Date).getMonth(),(new Date).getDate(),23,59,59));
			    			}
			    		}
			    	},
					name:'feedBackTimeEnd',
					id:'feedBackTimeEnd'
				},{//调查结果
					xtype:'combobox',
					fieldLabel:i18n('i18n.duty.result.surveyResult'),//i18n('i18n.duty.result.surveyResult'),
					colspan:2,
					width:240,
					store:Ext.create('Ext.data.Store', {
			    	    fields: ['code', 'codeDesc'],
			    	    data : [
			    	        {"code":"DUTY_SURVEYING", "codeDesc":i18n('i18n.Duty.result.investigating')},//"调查中"},
			    	        {"code":"DUTY_ESTABLISH", "codeDesc":i18n('i18n.Duty.result.establish')},//"成立"},
			    	        {"code":"DUTY_NOT_ESTABLISH", "codeDesc":i18n('i18n.Duty.result.inconsistent')}//"不成立"}
			    	    ]
			    	}),
			    	queryMode:'local',
			    	forceSelection:true,
			    	displayField:'codeDesc',
					valueField:'code',
					name:'surveyResult',
					id:'surveyResult',
					assertValue:function(){
						var me = this;
						rec = me.findRecordByDisplay(me.getRawValue());
						if (rec) {
		                    me.select(rec);
		                } else {
		                	me.setValue("");
		                }
						 me.collapse();
					}
				},{//责任划分时间
			    	xtype:'datetimefield',
			    	fieldLabel:i18n('i18n.Duty.DutyApproval.DutyAllocationTime'),//i18n('i18n.Duty.DutyApproval.DutyAllocationTime'),
			    	setDefaultTime:[0,0,0],
			    	format:'Y-m-d H:i:s',
			    	listeners:{
			    		expand:function(t){
			    			if(Ext.isEmpty(t.getValue())){
			    			t.setValue(new Date(nowDateLong.getFullYear(),nowDateLong.getMonth(),nowDateLong.getDate()));
			    			}
			    		}
			    	},
			    	labelWidth:80,
					name:'appDutyTimeBegin',
					id:'appDutyTimeBegin'
				},{//责任划分时间到
					xtype:'datetimefield',
			    	fieldLabel:i18n('i18n.Duty.searchDutyPanel.receiveTimeTo'),//i18n('i18n.Duty.DutyApproval.FeedbackTimeEnd'),
			    	format:'Y-m-d H:i:s',
			    	setDefaultTime:[23,59,59],
			    	listeners:{
			    		expand:function(t){
			    			if(Ext.isEmpty(t.getValue())){
			    			t.setValue(new Date((new Date).getFullYear(),(new Date).getMonth(),(new Date).getDate(),23,59,59));
			    			}
			    		}
			    	},
					name:'appDutyTimeEnd',
					id:'appDutyTimeEnd'
				},{//责任状态
					xtype:'combobox',
					fieldLabel:i18n('i18n.Duty.DutyApproval.dutyStatus'),//i18n('i18n.Duty.DutyApproval.dutyStatus'),
					forceSelection:true,
					colspan:2,
					store:getDataDictionaryByName(DataDictionary,'DUTY_RESULT_STATUS'),
			    	queryMode:'local',
			    	displayField:'codeDesc',
					valueField:'code',
					name:'dutyStates',
					id:'dutyStates',
					listeners:{
						//comboBox的change事件重写，使其达到可以删除，且只能选择下拉框里面的内容
						change:function(th){
							DutyUtil.comboSelsct(th);
						}
					}
				},{//责任部门
					fieldLabel : i18n('i18n.DutyFeedbackView.dutyDept'),//i18n('i18n.duty.result.taskDeptName'),
					labelWidth:80,
			    	id:'dutyDept',
					name : 'dutyDept'
				},{//反馈部门
					fieldLabel : i18n('i18n.Duty.DutyApproval.FeedbackDeptName'),//i18n('i18n.Duty.DutyApproval.FeedbackDeptName'),
			    	id:'feedbackDept',
					name : 'feedbackDept'
				}
				,{//呼叫中心审批人
					fieldLabel : i18n('i18n.Duty.DutyApproval.CallerApprover'),//i18n('i18n.Duty.DutyApproval.CallerApprover'),
					labelWidth:100,
			    	id:'callCenterUser',
			    	colspan:2,
					name : 'callCenterUser'
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
					colspan:2,
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
	
	/**
	 * 工单责任查询结果表格嵌套表格
	 */
	Ext.define('ExpandDutyQueryResultGrid',{
		extend:'SearchGridPanel',
		defaults:{
			align:'center'
		},
		autoScroll:true,
		id:'ExpandDutyQueryResultGridID',
		initComponent:function(){ 
			var me = this;
			var store = Ext.create('DutyAllocationResultDeptStore');
			me.store = store;
			me.columns = [
			    {//序号
			    	xtype:'rownumberer',
					header:i18n('i18n.Duty.resultDutyPanel.rownumberer'),
					width:40
			    },{//业务项
					header:i18n('i18n.duty.busItemName'),
					width:100,
					dataIndex:'busAspectId'
			    },{ //业务范围
					header :i18n('i18n.duty.busScopeName'),
					dataIndex:'busScope',
					width:100
			    },{//业务类型
					header :i18n('i18n.duty.busTypeName'),
					dataIndex:'busType',
					width:100
			    },{//业务环节
					header :i18n('i18n.duty.result.businessLink'),
					dataIndex:'businessLink',
					renderer:function(value) {
						return rendererDictionary(value,DataDictionary.DUTY_COMPLAINT_LINK);
					},
					width:100
			    },{//责任状态
					header :i18n('i18n.Duty.DutyApproval.dutyStatus'),
					dataIndex:'dutyStatus',
					renderer:function(value) {
						return rendererDictionary(value,DataDictionary.DUTY_RESULT_STATUS);
					},
					width:100
			    },{//处理语言
					header :i18n('i18n.duty.dealLanguage'),
					dataIndex:'dealLanguage',
					renderer:function(value) {
						if(!Ext.isEmpty(value)){
							var val = '<span data-qtip="'+value+'">'+value+'</span>';
							return val;
						}
					},
					width:100
			    },{//类型
					header :i18n('i18n.duty.result.dealType'),
					dataIndex:'divideType',
					renderer:function(val){
						if(!Ext.isEmpty(val)){
							if(val === 'department'){
								return i18n('i18n.duty.bulletin.deptName');
							}
							else{
								return i18n('i18n.CommonView.personal');
							}
						}
						
					},
					width:80
			    },{//责任部门
			    	header:i18n('i18n.DutyFeedbackView.dutyDept'),
			    	dataIndex:'dutyPerName',
					width:120
			    },{//奖罚类型
					header:i18n('i18n.duty.result.reworpusType'),
					width:100,
					dataIndex:'reworpusType',
					renderer:function(value) {
						return ReworpusType.getReworpusTypeName(value);
					}
			    },{ //反馈时限
					header :i18n('i18n.duty.result.feedtimeLimit'),
					dataIndex:'dutyDeadLine',
					renderer:DutyUtil.renderDateTime,
					width:120
			    },{//调查结果
					header :i18n('i18n.duty.result.surveyResult'),
					dataIndex:'surveyResult',
					width:100,
					renderer:function(value) {
						return rendererDictionary(value,DataDictionary.DUTY_SURVEY_RESULT);
					}
			    }
		    ];
			this.callParent();
			me.store.load({params:{dutyId:dutyId}});
	   }
	});
	/**
	 * 工单责任查询结果表格
	 */
	Ext.define('DutyQueryResultGrid',{
		extend:'SearchGridPanel',
		defaults:{
			align:'center'
		},
		autoScroll:true,
		selModel:Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE',id:'selModelId'}),
		//行展开控件，表格中嵌套表格
		plugins: [{
		    ptype: 'rowexpander',
			//定义行展开模式（单行与多行），默认是多行展开(值true)
			rowsExpander: false,
			//行体内容
			rowBodyElement : 'ExpandDutyQueryResultGrid'
		}],	
		initComponent:function(){ 
			var me = this;
			var store = Ext.create('DutyQueryResultStore');
			store.on({
				beforeload:function(store,operation,e){					
					var dutySearchPanelForm = Ext.getCmp("dutySearchPanelId");
					appDutyUserPara = dutySearchPanelForm.getForm().findField('appDutyUser').getValue(); // 责任划分人
					reportTimeBeginPara = dutySearchPanelForm.getForm().findField('reportTimeBegin').getValue(); // 上报时间
					reportTimeEndPara = dutySearchPanelForm.getForm().findField('reportTimeEnd').getValue(); // 上报时间到
					typeIdPara = dutySearchPanelForm.getForm().findField('typeId').getValue(); // 编号类型选择
					typeNamePara = dutySearchPanelForm.getForm().findField('typeName').getValue(); // 编号内容输入
					feedBackTimeBeginPara = dutySearchPanelForm.getForm().findField('feedBackTimeBegin').getValue(); // 反馈时间
					feedBackTimeEndPara = dutySearchPanelForm.getForm().findField('feedBackTimeEnd').getValue(); // 反馈时间到
					surveyResultPara = dutySearchPanelForm.getForm().findField('surveyResult').getValue(); // 调查结果
					appDutyTimeBeginPara = dutySearchPanelForm.getForm().findField('appDutyTimeBegin').getValue(); // 责任划分时间
					appDutyTimeEndPara = dutySearchPanelForm.getForm().findField('appDutyTimeEnd').getValue(); // 责任划分时间到
					dutyStatesPara = dutySearchPanelForm.getForm().findField('dutyStates').getValue(); // 责任状态
					dutyDeptPara = dutySearchPanelForm.getForm().findField('dutyDept').getValue(); // 责任部门
					feedbackDeptPara = dutySearchPanelForm.getForm().findField('feedbackDept').getValue(); // 反馈部门
					callCenterUserPara = dutySearchPanelForm.getForm().findField('callCenterUser').getValue(); // 呼叫中心审批人
					businessModel = dutySearchPanelForm.getForm().findField('businessModel').getValue(); // 业务模式
					var searchParams = {
						'queryDutyCondition.appDutyUser':appDutyUserPara,
						'queryDutyCondition.reportTimeBegin':reportTimeBeginPara,
						'queryDutyCondition.reportTimeEnd':reportTimeEndPara,
						'queryDutyCondition.typeId':typeIdPara,
						'queryDutyCondition.typeName':typeNamePara,
						'queryDutyCondition.feedBackTimeBegin':feedBackTimeBeginPara,
						'queryDutyCondition.feedBackTimeEnd':feedBackTimeEndPara,
						'queryDutyCondition.surveyResult':surveyResultPara,
						'queryDutyCondition.appDutyTimeBegin':appDutyTimeBeginPara,
						'queryDutyCondition.appDutyTimeEnd':appDutyTimeEndPara,
						'queryDutyCondition.dutyStates':dutyStatesPara,
						'queryDutyCondition.dutyDept':dutyDeptPara,
						'queryDutyCondition.feedbackDept':feedbackDeptPara,
						'queryDutyCondition.callCenterUser':callCenterUserPara,
						'queryDutyCondition.businessModel':businessModel
					};
					Ext.apply(operation,{
						params : searchParams
					});
				},
				load:function(store){
					if(store.getCount()==0){
						MessageUtil.showErrorMes(i18n('i18n.Duty.queryDutyListResult.IsNull'));	
					}
				}
			});
			me.store = store;
			me.columns = [
			    {//序号
			    	xtype:'rownumberer',
					header:i18n('i18n.duty.serial_number'),//i18n('i18n.Duty.resultDutyPanel.rownumberer'),
					width:40
			    },{ //业务模式
					header :i18n('i18n.duty.businessModel'),
					dataIndex:'businessModel',
					width:80,
					renderer:function(v){
						return rendererDictionary(v,DataDictionary.COMPLAINT_BUSINESS_MODEL);
					}
			    },{//凭证号
					header:i18n('i18n.Duty.resultDutyPanel.voucherNumber'),//i18n('i18n.Duty.resultDutyPanel.voucherNumber'),
					width:110,
					dataIndex:'voucherNumber'
			    },{ //处理编号
					header :i18n('i18n.Duty.resultDutyPanel.dealNumber'),//i18n('i18n.Duty.resultDutyPanel.dealNumber'),
					dataIndex:'dealNumber',
					width:120
			    },{//上报类型
					header :i18n('i18n.Duty.resultDutyPanel.reportType'),//i18n('i18n.duty.reportType'),
					dataIndex:'reportType',
					width:100,
					renderer:function(value) {
						return rendererDictionary(value,DataDictionary.REPORT_TYPE);
					}
			    },{//来电人
					header :i18n('i18n.Duty.resultDutyPanel.caller'),//i18n('i18n.Duty.resultDutyPanel.caller'),
					dataIndex:'caller',
					width:80
			    },{//调查结果
					header :i18n('i18n.Duty.resultDutyPanel.surveyResult'),//i18n('i18n.duty.result.surveyResult'),
					dataIndex:'surveyResult',
					width:65,
					renderer:function(value) {
						return rendererDictionary(value,DataDictionary.DUTY_TOTAL_SURVEY_RESULT);
					}
			    },{//责任划分人
					header :i18n('i18n.Duty.DutyApproval.DutyAllocationMan'),//i18n('i18n.Duty.DutyApproval.DutyAllocationMan'),
					dataIndex:'operateName',
					width:85
			   },{//责任划分时间
					header :i18n('i18n.Duty.DutyApproval.DutyAllocationTime'),//i18n('i18n.Duty.DutyApproval.DutyAllocationTime'),
					dataIndex:'operateDate',
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
					text: i18n('i18n.pager.prefixText'),//i18n('i18n.pager.prefixText'),
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
					text: i18n('i18n.Duty.resultDutyPanel.numPerPageNum'),//i18n('i18n.Duty.resultDutyPanel.numPerPageNum'),
					xtype: 'tbtext'
	           }]
		}];
		//双击表格记录，弹出工单责任详情窗口
		me.on('itemdblclick',function(th,record,item,index,e,eOpts){
			dutyId = record.get("dutyId");
		});
	    this.callParent();
	   }
	});
	
	//责任查询操作按钮面板
	Ext.define('DutySearchButtonPanel',{
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
				  {//查看详情
					  xtype:'button',
					  text:i18n('i18n.duty.look_up'),//i18n('i18n.duty.look_up'),
					  handler:function(btn){
						  var dutySearchDetailWindow = Ext.getCmp("dutySearchDetailWindowId");
							if(!dutySearchDetailWindow){
								dutySearchDetailWindow = Ext.create('DutySearchDetailWindow',{id:'dutySearchDetailWindowId'});
							}
						  CommonStore.prototype.loadDataToDutyDetail(btn,'dutyQueryResultGridId',function(){
							  dutySearchDetailWindow.show();
							});
					  }
				  }
				]
			   },{
				 xtype:'middlebuttonpanel' 
			   },{
				xtype : 'rightbuttonpanel',
				items : [{//查询
				    text:i18n('i18n.duty.search'),//i18n('i18n.duty.search'),
				    xtype:'button',
				    handler:function(){
				    	var dutySearchPanelForm = Ext.getCmp("dutySearchPanelId");
						var appDutyUserPara = dutySearchPanelForm.getForm().findField('appDutyUser').getValue(); // 责任划分人
						var reportTimeBeginPara = dutySearchPanelForm.getForm().findField('reportTimeBegin').getValue(); // 上报时间
						var reportTimeEndPara = dutySearchPanelForm.getForm().findField('reportTimeEnd').getValue(); // 上报时间到
						var typeIdPara = dutySearchPanelForm.getForm().findField('typeId').getValue(); // 编号类型选择
						var typeNamePara = dutySearchPanelForm.getForm().findField('typeName').getValue(); // 编号内容输入
						var feedBackTimeBeginPara = dutySearchPanelForm.getForm().findField('feedBackTimeBegin').getValue(); // 反馈时间
						var feedBackTimeEndPara = dutySearchPanelForm.getForm().findField('feedBackTimeEnd').getValue(); // 反馈时间到
						var surveyResultPara = dutySearchPanelForm.getForm().findField('surveyResult').getValue(); // 调查结果
						var appDutyTimeBeginPara = dutySearchPanelForm.getForm().findField('appDutyTimeBegin').getValue(); // 责任划分时间
						var appDutyTimeEndPara = dutySearchPanelForm.getForm().findField('appDutyTimeEnd').getValue(); // 责任划分时间到
						var dutyStatesPara = dutySearchPanelForm.getForm().findField('dutyStates').getValue(); // 责任状态
						var dutyDeptPara = dutySearchPanelForm.getForm().findField('dutyDept').getValue(); // 责任部门
						var feedbackDeptPara = dutySearchPanelForm.getForm().findField('feedbackDept').getValue(); // 反馈部门
						var callCenterUserPara = dutySearchPanelForm.getForm().findField('callCenterUser').getValue(); // 呼叫中心审批人
						if(Ext.isEmpty(reportTimeBeginPara)&&Ext.isEmpty(reportTimeEndPara)
								&&Ext.isEmpty(feedBackTimeBeginPara)&&Ext.isEmpty(feedBackTimeEndPara)
								&&Ext.isEmpty(appDutyTimeBeginPara)&&Ext.isEmpty(appDutyTimeEndPara)&&Ext.isEmpty(typeNamePara)){
							MessageUtil.showMessage(i18n('i18n.DutySearch.timeSearchIsNotAll'));//上报时间、反馈时间和责任划分时间必填其一！
							return false;
						}
						// 上报时间
						if(Ext.isEmpty(reportTimeBeginPara)^Ext.isEmpty(reportTimeEndPara)){
							MessageUtil.showMessage(i18n('i18n.Message.showMessage.reportTimeEmpty'));//(i18n('i18n.Message.showMessage.reportTimeEmpty'));
							return false;
						}
						if(!(Ext.isEmpty(reportTimeBeginPara) || Ext.isEmpty(reportTimeEndPara))){
							var reportTime = compareTwoDate(reportTimeBeginPara,reportTimeEndPara);
							if(reportTime > 31){
								MessageUtil.showMessage(i18n('i18n.Message.showMessage.reportTimeLimit'));//(i18n('i18n.Message.showMessage.reportTimeLimit'));
								return false;
							}
						}
						if(reportTime<0){
							MessageUtil.showMessage(i18n('i18n.Message.showMessage.reportTimeBeginParaBiggerThanreportTimeEndPara'));//开始时间不能大于结束时间！
							return false;
						}
						// 反馈时间
						if(Ext.isEmpty(feedBackTimeBeginPara)^Ext.isEmpty(feedBackTimeEndPara)){
							MessageUtil.showMessage(i18n('i18n.Message.showMessage.feedBackTimeEmpty'));//(i18n('i18n.Message.showMessage.feedBackTimeEmpty'));
							return false;
						}
						if(!(Ext.isEmpty(feedBackTimeBeginPara) || Ext.isEmpty(feedBackTimeEndPara))){
							var feedBackTime = compareTwoDate(feedBackTimeBeginPara,feedBackTimeEndPara);
							if(feedBackTime > 31){
								MessageUtil.showMessage(i18n('i18n.Message.showMessage.feedBackTimeLimit'));//(i18n('i18n.Message.showMessage.feedBackTimeLimit'));
								return false;
							}else if(feedBackTime<0){
								MessageUtil.showMessage(i18n('i18n.Message.showMessage.reportTimeBeginParaBiggerThanreportTimeEndPara'));//开始时间不能大于结束时间！
								return false;
							}
						}
						// 责任划分时间
						if(Ext.isEmpty(appDutyTimeBeginPara)^Ext.isEmpty(appDutyTimeEndPara)){
							MessageUtil.showMessage(i18n('i18n.Message.showMessage.appDutyTimeEmpty'));//(i18n('i18n.Message.showMessage.appDutyTimeEmpty'));
							return false;
						}
						if(!(Ext.isEmpty(appDutyTimeBeginPara) || Ext.isEmpty(appDutyTimeEndPara))){
							var appDutyTime = compareTwoDate(appDutyTimeBeginPara,appDutyTimeEndPara);
							if(appDutyTime> 31){
								MessageUtil.showMessage(i18n('i18n.Message.showMessage.appDutyTimeLimit'));//(i18n('i18n.Message.showMessage.appDutyTimeLimit'));
								return false;
							}else if(appDutyTime<0){
								MessageUtil.showMessage(i18n('i18n.Message.showMessage.reportTimeBeginParaBiggerThanreportTimeEndPara'));//开始时间不能大于结束时间！
								return false;
							}
						}
						// 责任划分人、责任部门、反馈部门、调查结果、责任状态、呼叫中心审批人组合查询时必须有一个时间条件
						if(!(Ext.isEmpty(appDutyUserPara)||Ext.isEmpty(dutyDeptPara)||Ext.isEmpty(feedbackDeptPara)
								||Ext.isEmpty(surveyResultPara)||Ext.isEmpty(dutyStatesPara)||Ext.isEmpty(callCenterUserPara))&&
								(null == reportTimeBeginPara && null == feedBackTimeBeginPara && null == appDutyTimeBeginPara)){
							MessageUtil.showMessage(i18n('i18n.Message.showMessage.limitTime'));//(i18n('i18n.Message.showMessage.limitTime'));
							return false;
						}
				    	// 删除上一次数据
				    	Ext.getCmp("dutyQueryResultGridId").store.removeAll();
				    	//查询按钮功能实现区
						Ext.getCmp("dutyQueryResultGridId").store.loadPage(1);
				    		  
				  }
				 }]
			}];
		}
	});
	
	/*
	 * 新建一个viewport，用于显示工单责任查询界面
	 * 肖红叶
	 */
	viewport=Ext.create('Ext.Viewport',{
		layout : 'border',
		items:[
	        {//责任审批查询条件输入面板
	        	xtype:'container',
				region:'north',
				layout:'fit',
				items:[Ext.create('DutySearchPanel',{id:'dutySearchPanelId'})]
			},{
				xtype:'container',
				region:'center',
				layout:'border',
				items:[
					{//按钮面板
						xtype:'container',
						region:'north',
					    layout:'fit',
					    items:[Ext.create('DutySearchButtonPanel',{id:'dutySearchButtonPanelId'})]
					},{//责任审批查询结果表格
						xtype:'container',
						region:'center',
			    	    layout:'fit',
			    	    items:[ Ext.create('DutyQueryResultGrid',{id:'dutyQueryResultGridId'})]
			        }	
			   ]
			}
	    ]
	});

	
	function compareTwoDate(startDate,endDate){
		  var days, t, t2;
		  var MinMilli = 24 * 1000 * 60 * 60;
		  t = Date.parse(startDate);
		  t2= Date.parse(endDate); 
		  days = (t2-t)/ MinMilli;
		  return days;
	};
	viewport.setAutoScroll(false);	
	viewport.doLayout();
	
});

