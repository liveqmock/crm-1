(function(){
	var keys=[
		'TASKPROPERTIES_TYPE', /*任务属性*/
		'MEMBER_GRADE',/*客户等级*/
		'PRIORITY_RATING',/*优先级别*/ 
		'WORK_SINGLE_SOURCE',/*工单来源*/
		'CUSTOMER_TYPE',/*客户类型*/
		'CALLER_TYPE',/*上报人类型*/
		'REPORT_TYPE', /*上报类型*/
		'DUTY_COMPLAINT_LINK',/*投诉环节*/
		'PROCESS_TYPE',	/* 处理类型 个人或部门*/
		'DUTY_RESULT_STATUS',	/*责任状态*/
		'AWARD_TYPE',	/*奖罚类型*/
		'DUTY_SURVEY_RESULT',	/*调查结果*/
		'APPROVAL_ACCORDING',/*审批依据*/
		'COMPLAINT_BUSINESS_MODEL'//业务模式
	];
	//初始数据字典
	initDataDictionary(keys);
})();
//定义一个全局变量，用以保存工单责任审批界面中显示嵌套表格内容所需要的责任划分ID
var dutyAllocationId =null;
/**
 * 工单详情之工单基本信息panel
 */
Ext.define('DutyComplaintDetailBasicInfoPanel',{
	extend:'SearchFormPanel', 
	items:null,
	border:0,
	layout:{
		type:'table',columns:5
	},
	defaults:{
		labelWidth:65,labelAlign:'right',
		width:180,margin:'0 5 5 0'
	},
	defaultType:'readonlytextfield',
	initComponent:function(){			
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [//colspan:2
			{fieldLabel:i18n('i18n.Duty.resultDutyPanel.voucherNumber'),name:'voucherNumber'},
			{fieldLabel:i18n('i18n.dutyCommon.connectTel'),name:'tel'},
			{fieldLabel:i18n('i18n.Duty.resultDutyPanel.caller')/*来电人*/,name:'caller',width:115},
			{/*来电人类型*/
				name:'sendOrReceive',xtype:'combobox',labelWidth:0,width:60,
				displayField:'codeDesc',valueField:'code',readOnly:true,cls:'readonly',
				//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
				store:getDataDictionaryByName(DataDictionary,'CALLER_TYPE')	
			},
			{/*上报类型*/
				fieldLabel:i18n('i18n.duty.reportType'),name:'reportType',
				xtype:'combobox',displayField:'codeDesc',valueField:'code',
				readOnly:true,cls:'readonly',
				//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
				store:getDataDictionaryByName(DataDictionary,'REPORT_TYPE')	
			},
			{fieldLabel:i18n('i18n.dutyCommon.comeCustermer'),name:'complaintCust'},
			{
				fieldLabel:i18n('i18n.dutyCommon.customerLevel'),name:'custLevel',
				readOnly:true,cls:'readonly',xtype:'combobox',mode:'local',triggerAction:'all',
			  	forceSelection:true,editable:false,/*不能编辑*/ displayField:'codeDesc',valueField:'code',
			  	//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
			  	store:getDataDictionaryByName(DataDictionary,'MEMBER_GRADE')	
			},
			{
				fieldLabel:i18n('i18n.dutyCommon.customerType')/*客户类型*/,name:'custType',colspan:2,width:180,
				readOnly:true,cls:'readonly',xtype:'combobox',mode:'local',
				triggerAction:'all',forceSelection:true,
				editable:false,/*不能编辑*/displayField:'codeDesc',valueField:'code',
				//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
				store:getDataDictionaryByName(DataDictionary,'CUSTOMER_TYPE')
			},
			{
				fieldLabel:i18n('i18n.dutyCommon.complaintResource'),name:'complaintSource',
				readOnly:true,cls:'readonly',xtype:'combobox',displayField:'codeDesc',valueField:'code',
				//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
				store:getDataDictionaryByName(DataDictionary,'WORK_SINGLE_SOURCE')	
			},
			//3
			{fieldLabel:i18n('i18n.dutyCommon.xgcustomer'),name:'relatCus'},
			{
				fieldLabel:i18n('i18n.dutyCommon.customerLevel'),name:'relatCusLevel',
				readOnly:true,cls:'readonly',xtype:'combobox',mode: 'local',
			    triggerAction:'all',forceSelection:true,
			  	editable:false,/*不能编辑*/displayField:'codeDesc',valueField:'code',
				//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
			 	store:getDataDictionaryByName(DataDictionary,'MEMBER_GRADE')
			},
			{
				fieldLabel:i18n('i18n.CommonView.expectedTimeLimit')/*期望时限*/,
				colspan:2,width:180,readOnly:true,cls:'readonly',
				name:'timeLimit'
			},
			{
				fieldLabel:i18n('i18n.Duty.resultDutyPanel.pririty'),name:'pririty',
				readOnly:true,cls:'readonly',xtype:'combobox',displayField:'codeDesc',valueField:'code',
				//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
				store:getDataDictionaryByName(DataDictionary,'PRIORITY_RATING')						
			},
			//4
			{fieldLabel:i18n('i18n.Duty.resultDutyPanel.rebindNo'),name:'rebindNo'},
			{fieldLabel:i18n('i18n.Duty.resultDutyPanel.dealNumber'),name:'dealNumber'},
			{
				fieldLabel:i18n('i18n.Duty.resultDutyPanel.reportTime')/*上报时间*/,
				colspan:2,width:180,readOnly:true,cls:'readonly',
				name:'reportTime',format:'Y-m-d H:i',xtype:'datefield'
			},
			{fieldLabel:i18n('i18n.Duty.resultDutyPanel.reporter'),name:'reporter'},
			{/*业务模式*/
				fieldLabel:i18n('i18n.duty.businessModel'),name:'businessModel',
				xtype:'combobox',displayField:'codeDesc',valueField:'code',
				readOnly:true,cls:'readonly',
				store:getDataDictionaryByName(DataDictionary,'COMPLAINT_BUSINESS_MODEL'),
				colspan:5
			},
			//5
			{xtype:'textareafield',fieldLabel:i18n('i18n.CommonView.theContentOfReporting'),name:'reportContent',readOnly:true,cls:'readonly',colspan:2,height:70,width:365},
			{xtype:'textareafield',fieldLabel:i18n('i18n.CommonView.customerRequirements'),name:'custRequire',readOnly:true,cls:'readonly',colspan:3,height:70,width:365}
	    ];
	}
});

/**

 * 责任划分结果表格
 */
Ext.define('DutyAllocationResultDeptGrid',{
	extend:'PopupGridPanel',
	title:i18n('i18n.Duty.DutyApproval.DutyAllocationResult'),
	defaults:{
		align:'center'
	},
	autoScroll:true,
	selModel:Ext.create('Ext.selection.CheckboxModel'),
	initComponent:function(){ 
		var me = this;
		var store = Ext.create('DutyAllocationResultDeptStore');
//		store.on('beforeload',function(store,operation,e){					
//		  
//			
//		});		
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
   }
});

/**
 * 处理经过列表
 */
Ext.define('DutyFeedbackDealProcedureGrid',{
	extend:'PopupGridPanel',
	title:i18n('i18n.DutyAllocationOperationView.treatmentAfter'),
	defaults:{
		align:'center'
	},
	autoScroll:true,
	initComponent:function(){ 
		var me = this;
		store = Ext.create('DutyDealProcedureStore');	
		me.store = store;
		me.columns = [
		    {//序号
		    	xtype:'rownumberer',
				header:i18n('i18n.duty.serial_number'),
				width:40
		    },{//处理经过
				header:i18n('i18n.DutyAllocationOperationView.treatmentAfter'),
				dataIndex:'treatProcess',
				flex:1,
				renderer:function(value){
					if(!Ext.isEmpty(value)){
						var val = '<span data-qtip="'+value+'">'+value+'</span>';
						return val;
					}
				}
		    },{ //操作人
				header :i18n('i18n.CommonView.operator'),
				dataIndex:'opratorName',
				width:100
		    },{//操作时间
				header :i18n('i18n.CommonView.operatingTime'),
				dataIndex:'operatorTime',
				renderer:DutyUtil.renderDateTime,
				width:145
		    }
	    ];
    this.callParent();
   }
});


/**
 * 通知对象panel
 */
Ext.define('DutyFeedbackInfoToPanel',{
	extend:'SimpSearchPanel', 
	items:null,
	border:0,
	layout:{
		type:'table',
		columns:1
	},
	initComponent:function(){			
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [
		    {//通知对象
				xtype:'readonlytextarea',
				width:750,
				labelWidth:75,
				fieldLabel:i18n('i18n.DutyAllocationOperationView.informUserCanNotEmpty'),
				height:50,
				name:'informUser',
				id:'informUser'
			}
	    ];
	}
});

/**
 * 工单责任审批查询结果表格双击后的嵌套表格by肖红叶
 */
Ext.define('ExpandDutyApprovalResultGrid',{
	extend:'SearchGridPanel',
	defaults:{
		align:'center'
	},
	autoScroll:true,
	sortableColumns:false,
    enableColumnHide:false,
    enableColumnMove:false,
	id:'expandDutyApprovalResultGridID',
	initComponent:function(){ 
		var me = this;
		var store = Ext.create('DutyAllocationResultDeptStore');
		store.on('load',function(storeIndex){
			 var girdcount=0;  
			 storeIndex.each(function(record){
				 if(record.get('dutyStatus')=='APPROVAL_EXCEED'){
			        	//给单元格涂色
			        	var cells = me.getView().getNodes()[girdcount].children;
		  			    for(var i= 0;i<cells.length;i++){
		  				    cells[i].style.backgroundColor='#ff0000';
		  			    }
			     }
				 girdcount++;
			 });
		});
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
				renderer:function(value,metaData,record,rowIndex,colIndex,store,view) {
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
 * 责任反馈记录表格
 */
Ext.define('DutyFeedbackRecordListGrid',{
	extend:'PopupGridPanel',
	title:i18n('i18n.Duty.DutyApproval.FeedbackRecord'),
	defaults:{
		align:'center'
	},
	autoScroll:true,
	initComponent:function(){ 
		var me = this;
		var store = Ext.create('DutyFeedbackRecordListStore',{id:'DutyFeedbackRecordListStoreId'});
		me.columns = [
		    {//序号
		    	xtype:'rownumberer',
				header:i18n('i18n.Duty.resultDutyPanel.rownumberer'),
				width:40
		    },{//反馈人
				header:i18n('i18n.Duty.DutyApproval.FeedbackManName'),
				dataIndex:'feedUserName',
				width:80
		    },{ //反馈部门
				header :i18n('i18n.Duty.DutyApproval.FeedbackDeptName'),
				dataIndex:'feedDeptName',
				width:100
		    },{//反馈时间
				header :i18n('i18n.Duty.DutyApproval.FeedbackTimeStart'),
				dataIndex:'feedBackTime',
				renderer:DutyUtil.renderDateTime,
				width:120
		    },{//责任反馈内容
				header:i18n('i18n.Duty.DutyApproval.FeedbackContext'),
				dataIndex:'describe',
				width:120,
				renderer:function(value){
					if(!Ext.isEmpty(value)){
						var val = '<span data-qtip="'+value+'">'+value+'</span>';
						return val;
					}
				}
		    },{ //附件
				header :i18n('i18n.Duty.DutyApproval.Attachment'),
				dataIndex:'haveFeedAttach',
				width:100,
				renderer:function(value,node,record){
					if(value === 'Y'){
						var feedbackId = record.get('feedbackId');
						return '<button type="button" onclick="watchFeedbackAttatch(\''+feedbackId+'\')">'+i18n('i18n.Duty.DutyApproval.AttachmentDetail')+'</button>'; 
					}
					else if(value === 'N'){
						return i18n('i18n.Duty.DutyApproval.NoAttachment');
					}
					else{
						return i18n('i18n.Duty.DutyApproval.NoAttachment');
					}
				}
		    },{//责任状态
				header :i18n('i18n.Duty.DutyApproval.dutyStatus'),
				dataIndex:'status',
				width:100,
				renderer:function(value){
					return DutyUtil.rendererDictionary(value,DataDictionary.DUTY_RESULT_STATUS);
				}
		    },{ //呼叫中心审批人
				header :i18n('i18n.Duty.DutyApproval.CallerApprover'),
				dataIndex:'callCenUser',
				width:120
		    },{//呼叫中心审批意见
				header :i18n('i18n.Duty.DutyApproval.CallerApprovalOpinion'),
				dataIndex:'callCenDescision',
				width:120,
				renderer:function(value){
  					if(!Ext.isEmpty(value)){
 						return '<span data-qtip="'+value+'">'+value+'</span>';
 	            	}
  				}
		    },{//呼叫中心审批时间
				header :i18n('i18n.Duty.DutyApproval.CallerApprovalTime'),
				dataIndex:'callcenterTime',
				renderer:DutyUtil.renderDateTime,
				width:150
		    }
	    ];
    this.callParent();
   }
});

/**
 * 责任反馈记录表格
 */
Ext.define('DutyFeedbackRecordListGridView',{
	extend:'PopupGridPanel',
	title:i18n('i18n.Duty.DutyApproval.FeedbackRecord'),
	defaults:{
		align:'center'
	},
	autoScroll:true,
	initComponent:function(){ 
		var me = this;
		var store = Ext.create('DutyFeedbackRecordListStore',{id:'DutyFeedbackRecordListStoreId'});
		me.columns = [
		    {//序号
		    	xtype:'rownumberer',
				header:i18n('i18n.Duty.resultDutyPanel.rownumberer'),
				width:40
		    },{//反馈人
				header:i18n('i18n.Duty.DutyApproval.FeedbackManName'),
				dataIndex:'feedUserName',
				width:80
		    },{ //反馈部门
				header :i18n('i18n.Duty.DutyApproval.FeedbackDeptName'),
				dataIndex:'feedDeptName',
				width:100
		    },{//反馈时间
				header :i18n('i18n.Duty.DutyApproval.FeedbackTimeStart'),
				dataIndex:'feedBackTime',
				renderer:DutyUtil.renderDateTime,
				width:120
		    },{//责任反馈内容
				header:i18n('i18n.Duty.DutyApproval.FeedbackContext'),
				dataIndex:'describe',
				width:120,
				renderer:function(value){
					if(!Ext.isEmpty(value)){
						var val = '<span data-qtip="'+value+'">'+value+'</span>';
						return val;
					}
				}
		    },{ //附件
				header :i18n('i18n.Duty.DutyApproval.Attachment'),
				dataIndex:'haveFeedAttach',
				width:100,
				renderer:function(value,node,record){
					if(value === 'Y'){
						var feedbackId = record.get('feedbackId');
						return '<button type="button" onclick="watchFeedbackAttatch(\''+feedbackId+'\')">'+i18n('i18n.Duty.DutyApproval.AttachmentDetail')+'</button>'; 
					}
					else if(value === 'N'){
						return i18n('i18n.Duty.DutyApproval.NoAttachment');
					}
					else{
						return i18n('i18n.Duty.DutyApproval.NoAttachment');
					}
				}
		    },{//责任状态
				header :i18n('i18n.Duty.DutyApproval.dutyStatus'),
				dataIndex:'status',
				width:100,
				renderer:function(value){
					return DutyUtil.rendererDictionary(value,DataDictionary.DUTY_RESULT_STATUS);
				}
		    },{//大区审批人(已经作废)
				header:i18n('i18n.Duty.DutyApproval.StatisApprover'),
				dataIndex:'statUserName',
				hidden : true,//隐藏掉了
				width:100
		    },{ //查看反馈到哪个部门(已经作废)
				header :i18n('i18n.duty.regionalEnumeratorsDepartment'),
				dataIndex:'id',
				width:100,
				hidden : true,//隐藏掉了
				renderer:function(value,node,record){
					var feedbackId = record.get('feedbackId');
					return '<button type="button" onclick="watchDeptName(\''+feedbackId+'\')">'+i18n('i18n.duty.search')+'</button>'; 
				}
		    },{//大区审批意见(已经作废)
				header :i18n('i18n.Duty.DutyApproval.StatisOpinion'),
				dataIndex:'statDecision',
				width:120,
				hidden : true,//隐藏掉了
				renderer:function(value){
					if(!Ext.isEmpty(value)){
						var val = '<span data-qtip="'+value+'">'+value+'</span>';
						return val;
					}
				}
		    },{ //大区审批时间(已经作废)
				header :i18n('i18n.Duty.DutyApproval.StatisApprovalTime'),
				dataIndex:'statTime',
				hidden : true,//隐藏掉了
				renderer:DutyUtil.renderDateTime,
				width:140
		    },{ //呼叫中心审批人
				header :i18n('i18n.Duty.DutyApproval.CallerApprover'),
				dataIndex:'callCenUser',
				width:120
		    },{//呼叫中心审批意见
				header :i18n('i18n.Duty.DutyApproval.CallerApprovalOpinion'),
				dataIndex:'callCenDescision',
				width:120,
				renderer:function(value){
  					if(!Ext.isEmpty(value)){
 						return '<span data-qtip="'+value+'">'+value+'</span>';
 	            	}
  				}
		    },{//审批依据
				header:i18n('i18n.Duty.DutyApproval.ApprovalAccording'),
				dataIndex:'according',
				renderer:function(value){
					return DutyUtil.rendererDictionary(value,DataDictionary.APPROVAL_ACCORDING);
				},
				width:120
		    },{//呼叫中心审批时间
				header :i18n('i18n.Duty.DutyApproval.CallerApprovalTime'),
				dataIndex:'callcenterTime',
				renderer:DutyUtil.renderDateTime,
				width:150
		    }
	    ];
    this.callParent();
   }
});

/**
 * 处理经过panel
 */
Ext.define('DutyAllocationDealRecordPanel',{
	extend:'SimpSearchPanel', 
	items:null,
	border:0,
	layout:{
		type:'table',
		columns:1
	},
	initComponent:function(){			
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [
		    {//处理经过
				xtype:'textarea',
				width:750,
				labelWidth:75,
				fieldLabel:i18n('i18n.DutyAllocationOperationView.treatmentAfter'),
				height:70
			}
	    ];
	}
});

/**
 * 责任划分结果增加、删除按钮Panel
 */
Ext.define('DutyAllocationAddAndDelPanel',{
	extend:'TopPanel',
	items : null,
	initComponent : function() {
		this.items = this.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [{
			xtype:'titlepanel',  
			flex:1,
			items:[{//责任划分结果
				xtype:'displayfield',
				value:i18n('i18n.Duty.DutyApproval.DutyAllocationResult')
		    }]
		},{
			xtype:'btnpanel',  
			defaultType:'button',
			items:[{//增加
				text:i18n('i18n.duty.add'),
				handler:function(){
					//增加按钮
					var dutyAddResultWindowsId = Ext.getCmp('dutyAddResultWindowsId');
					if(Ext.isEmpty(dutyAddResultWindowsId)){
						dutyAddResultWindowsId = Ext.create('DutyAddResultWindows',{id:'dutyAddResultWindowsId'});
					}
					dutyAddResultWindowsId.show();						
				}
			   },
			   {//删除，质检组修改责任划分时不显示
				text:i18n('i18n.duty.delete'),handler:function(){
					//删除按钮
					var grid = Ext.getCmp('dutyEditableResultGridId');
					var selection = grid.getSelectionModel().getSelection();
					if(Ext.isEmpty(selection) || selection.length==0){
						MessageUtil.showMessage(i18n('i18n.DutyBasicDataSettingView.selectDate'));return;
					}
					MessageUtil.showQuestionMes(i18n('i18n.CommonView.deleteSelectedProcessingResults'),function(e){
						if(e != 'yes'){return;}
						var parmArray = new Array();
						var selectionArray = new Array();
						Ext.each(selection,function(record){
							if(Ext.isEmpty(record.get('busItemId')) || '0' == record.get('busItemId')){
								//删除前端新增的
								grid.getStore().remove(record);
							}else{
								parmArray.push(record.data);
								selectionArray.push(record);
							}
						});
						if(parmArray.length == 0){return;}
					
						var params = {
							'basicInfoList':parmArray
						};
						var successFn = function(){
							MessageUtil.showMessage(i18n('i18n.DutyBasicDataSettingView.deleteDataSuccess'));
							Ext.each(selectionArray,function(record){
								//删除前端新增的
								grid.getStore().remove(record);
							});
						};
						var failFn = function(){
							MessageUtil.showMessage(i18n('i18n.DutyBasicDataSettingView.deleteDataFailure'));
						};
						ActionFunction.result_deleteByList(params,successFn,failFn);
					});
		      	}
			}]
		}]; 
	}
});

/**
 * 通知对象增加、删除按钮Panel
 */
Ext.define('DutyAllocationInforToBtnPanel',{
	extend:'TopPanel',items : null,
	initComponent : function() {
		this.items = this.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [{
			xtype:'titlepanel',flex:1,
			items:[{xtype:'displayfield',value:i18n('i18n.DutyAllocationOperationView.infoemUser')}]
		},{
			xtype:'btnpanel',  
			defaultType:'button',
			items:[{//增加
				text:i18n('i18n.duty.add'),handler:function(){
					//增加按钮
					var me = this;
					var is_existBulletin = function(my_record,dealType){
						var bulletinStore = Ext.getCmp('dutyBulletinGridId').getStore();
						var flag = false;
						bulletinStore.each(function(record){
							if(
								record.get('bulletinid') == my_record.get('empId')
								&& record.get('dealType') == dealType
							){
								record.set('is_manual_add',1);/*表示为：手动添加*/
								flag = true;return false;	
							}
						});
						return flag;
					}
					var store = Ext.getCmp('dutyBulletinGridId').getStore();
					Ext.create('EmployeeLookUpWindow',{
						title:i18n('i18n.duty.Employee.win_title'/*员工选择*/),
						listeners:{
			    			select:function(win,record){
			    				var divideType = 'employee';
			    				if(!is_existBulletin(record,dealType)){
			    					store.add({
										'id':record.get('empId'),
										'userNo':record.get('empCode'),
										'userName':record.get('empName'),
										'userPosition':record.get('position'),
										'divideType':divideType,
										'userContact':record.get('mobilePhone'),
										'bulletinDeptName':record.get('deptName'),
										'busType':''/* 为空表示手动添加 */
									});
			    				}
				        		win.close();
			    			}
			    		}
					}).show();
				}
			   },{//删除，质检组修改责任划分时不显示
				text:i18n('i18n.duty.delete'),
				handler:function(){
					//删除按钮
					var me = this;
					var grid = Ext.getCmp('dutyBulletinGridId');
					var selectionList = grid.getSelectionModel().getSelection();
					if(Ext.isEmpty(selectionList)){
						MessageUtil.showMessage(i18n('i18n.DutyAllocationOperationView.pleaseSelectTodeleteData'));return;
					}
					var panelStore = grid.getStore();
					for(var i=0;i<selectionList.length;i++){
						panelStore.remove(selectionList[i]);
					}
		      	}
			}]
		}]; 
	}
});



