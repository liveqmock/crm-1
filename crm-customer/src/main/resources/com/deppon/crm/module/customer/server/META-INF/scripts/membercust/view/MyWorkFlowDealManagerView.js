/**
* 散客升级列表界面
*/
var myMorkFlowManagerDataControl =  (CONFIG.get("TEST"))? new MyWorkFlowDealManagerDataTest():new MyWorkFlowDealManagerData();
//var myMorkFlowManagerDataControl =  new MyWorkFlowDealManagerData();
Ext.define('MyWorkFlowDealManagerPanel',{
	extend:'BasicPanel',
//	title:i18n('i18n.MyWorkFlowDealManagerView.workFlowManager'),
	myWorkFlowSearchCondition:null,      //查询条件
	myWorkFlowResultInfo:null,  //工作流查询结果
	myWorkFlowButtonPanel:null,  //工作流管理界面按钮
	myWorkFlowData:null,
	layout:{
        type:'vbox',
        align:'stretch'
    },
	initComponent:function(){
		var me = this;
		var record = new WorkflowConditionModel();
		me.myWorkFlowSearchCondition = Ext.create('MyWorkFlowDealManagerSearchForm',{'parent':me,'myWorkFlowData':me.myWorkFlowData,'record':record});
		me.myWorkFlowResultInfo = Ext.create('MyWorkFlowDealManagerResultGrid',{'parent':me,'myWorkFlowData':me.myWorkFlowData});
		me.myWorkFlowButtonPanel = Ext.create('MyWorkFlowDealManagerButtonPanel',{'parent':me,'myWorkFlowData':me.myWorkFlowData});
		me.items = [{
			xtype:'basicpanel',
			height:80,
			items:[me.myWorkFlowSearchCondition]
		},{
			xtype:'basicpanel',
			height:36,
			items:[me.myWorkFlowButtonPanel]
		},{
			xtype:'basicpanel',
			flex:1,
			items:[me.myWorkFlowResultInfo]
		}];
		Ext.apply(me,{
			items:me.items
		});
		me.callParent();
		me.myWorkFlowSearchCondition.loadRecord(record);
		me.myWorkFlowSearchCondition.getForm().findField('workflowId').setValue();
	}
});
/**
 * 工作流查询条件
 */
Ext.define('MyWorkFlowDealManagerSearchForm',{
	extend:'SearchFormPanel',
	id:'myWorkFlowSearchFormId',
	layout:{
		type:'table',
		columns:4
	},
	defaultType:'dptextfield',
	border:false,
	record:null,
	parent:null,
	myWorkFlowData:null,
	initComponent:function(){
		var me = this;
		me.defaults = me.getDefaultsContainer();
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			fieldLabel:i18n('i18n.MyWorkFlowDealManagerView.workFlowName'),
			xtype : 'combobox',
			name : 'workflowName',
			store:me.myWorkFlowData.getWorkFlowName(),
			queryMode:'local',
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code'
		},{
			fieldLabel:i18n('i18n.ContractEditView.workFlowNum'),
			maxLength:20,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20),
			regex : /^\d{0,}$/,
			regexText:i18n('i18n.MyWorkFlowDealManagerView.m_mustBeNum'),
			name : 'workflowId'
		},{
			fieldLabel:i18n('i18n.MyWorkFlowDealManagerView.workFlowStatus'),
			name:'applicationStatus',
			xtype : 'combobox',
			store:me.myWorkFlowData.getWorkFlowState(),
			queryMode:'local',
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code'
		},{
			fieldLabel:i18n('i18n.MyWorkFlowDealManagerView.applicants'),
			maxLength:40,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',40),
			name:'createUser'
		},{
			xtype:'belongdeptcombox',
			functionName : 'MemberFunction',
			forceSelection:true,
			name:'deptId',
			fieldLabel:i18n('i18n.ScatterUpgradeView.belongdeptName')
		},{
			xtype : 'fieldcontainer',
			colspan : 2,
			border : 0,
			width : 400,
			layout : 'column',
			defaultType : 'datefield',
			defaults : {
				enableKeyEvents:true,
				listeners:{
					scope : me,
					keypress : me.keypressEvent
				}
			},
			items : [ {
				fieldLabel : i18n('i18n.MyWorkFlowDealManagerView.theDateOfApplication'),labelWidth : 65,width : 200,format : 'Y-m-d',maxValue : new Date(),
				name : 'startDate'
			}, 
			{xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
			{xtype: 'datefield',name : 'endDate',width : 130,format : 'Y-m-d',maxValue : new Date()} ]
		
		}];
	},
	//增加监听事件
	getDefaultsContainer:function(){
		var me = this;
		return {
			labelWidth:65,
			width:195,
			enableKeyEvents:true,
			listeners:{
				scope : me,
				keypress : me.keypressEvent,
				change : me.changeEvent
			}
		};
	},
	//监听按键事件
	keypressEvent:function(field,event){
		var me = this;
		if(event.getKey() == Ext.EventObject.ENTER){
    		me.parent.myWorkFlowButtonPanel.searchMyWorkFlowDealManagerList();
    	}
	},
	//监听change事件
	changeEvent:function(field,newValue){
		var me = this;
		//如果是数据字典或所属部门组件 则显示只选
		if(('belongdeptcombox' == field.getXType() || 'combobox' == field.getXType()) 
			&& Ext.isEmpty(newValue)){
			field.setValue(null);
		}
	}
});
/**
 * 按钮面板
 */
Ext.define('MyWorkFlowDealManagerButtonPanel',{
	extend:'NormalButtonPanel',
	parent:null,
	myWorkFlowData:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'leftbuttonpanel',
			width:210,
			items:[{
				xtype:'button',
				text:i18n('i18n.MyWorkFlowDealManagerView.dealWith'),
				scope:me,
				handler:me.dealWorkFlow}]
		},{
			xtype:'middlebuttonpanel'
		},{
			xtype:'rightbuttonpanel',
			width:150,
			items:[{
				xtype:'button',
				text:i18n('i18n.PotentialCustManagerView.search'),
				scope:me,
				handler:me.searchMyWorkFlowDealManagerList
				}]
		}];
	},
	//工作流 查询
	searchMyWorkFlowDealManagerList:function(){
		var me = this;
		var form = me.parent.myWorkFlowSearchCondition;
		if(!me.validateCondition()){
			MessageUtil.showMessage(i18n('i18n.MemberUpgradeView.message_notAllNull'));
			return;
		}
		if(!form.getForm().isValid()){
			MessageUtil.showMessage(i18n('i18n.MyWorkFlowDealManagerView.m_changeAllRight'));
			return;
		}
		me.myWorkFlowData.getMyWorkFlowDealManagerStore().loadPage(1);
	},
	//工作流 处理
	dealWorkFlow:function(){
		var me = this;
		var grid = me.parent.myWorkFlowResultInfo;
		var selection=grid.getSelectionModel().getSelection();
		if (selection.length != 1) {
			MessageUtil.showMessage( i18n('i18n.MyWorkFlowDealManagerView.pleaseSelectOneRecordWar'));
			return;
		}
		var params = {};
		params.todoWorkflow = selection[0].data;
		//获得 查看工作流信息 失败
		var acquireMemberExaminViewFail = function(result){
			MessageUtil.showErrorMes(result.message);
		}
		var workflowId = selection[0].get('workflowId');
		var todoFlag = selection[0].get('todoFlag');
		var workflowName = selection[0].get('workflowName');
		//创建特殊会员
		if('createSpecialMember'==workflowName){
//		if('12123'==selection[0].get('workflowName')){//测试
			//获得 查看工作流信息 成功
			var acquireSepMemberExaminViewSuccess = function(result){
				var memberExaminView = result.memberExaminView;
				memberExaminView.workFlowId = workflowId;
				memberExaminView.todoFlag = todoFlag;
				//操作grid用于处理动作后进行grid刷新
				memberExaminView.operateGrid = me.parent.myWorkFlowResultInfo;
				//显示工作流审批界面
				WorkFlowEditView.showWorkFlowEditWin(memberExaminView,'CREATE_SPECIAL_MEMBER');
			}
			me.myWorkFlowData.acquireSepMemberExaminView(params,acquireSepMemberExaminViewSuccess,acquireMemberExaminViewFail);	
		}
		//会员信息维护memberMyWorkFlowDealManager
		else if('memberModify'==workflowName){
			//获得 查看工作流信息 成功
			var acquireModifyMemberExaminViewSuccess = function(result){
				var memberExaminView = result.memberExaminView;
				memberExaminView.workFlowId = workflowId;
				memberExaminView.todoFlag = todoFlag;
				//操作grid用于处理动作后进行grid刷新
				memberExaminView.operateGrid = me.parent.myWorkFlowResultInfo;
				//显示工作流审批界面
				WorkFlowEditView.showWorkFlowEditWin(memberExaminView,'UPDATE_CUSTOMERINFO');
			}
			me.myWorkFlowData.acquireModifyMemberExaminView(params,acquireModifyMemberExaminViewSuccess,acquireMemberExaminViewFail);			
		}
		//变更联系人挂靠关系
		else if('contactVary'==workflowName){
			//获得 查看工作流信息 成功
			var contactVarySuccess = function(result){
				var dealContactBelongView = null;
				if (dealContactBelongView==null||dealContactBelongView==undefined) {
					dealContactBelongView = new DealContactBelongView({
							'nowRecord':result.contactVaryExaminView.contactVary.fromMemberIntegral,
							'targetRecord':result.contactVaryExaminView.contactVary.toMemberIntegral,
							'parentWin':me,
							'operateGrid':me.parent.myWorkFlowResultInfo,//操作grid用于处理动作后进行grid刷新
							'fileInfoList':result.contactVaryExaminView.fileInfoList,
							'workFlowId':workflowId,
							'historyWorkflowDate':result.contactVaryExaminView.examineRecordList,
							'myWorkFlowData':myMorkFlowManagerDataControl});
				}
				dealContactBelongView.show();
			}
			me.myWorkFlowData.acquireContactVary(params,contactVarySuccess,acquireMemberExaminViewFail);			
		}
		//TODO 变更会员变更部门 数据字典
		else if('changeMemberDept'==workflowName){
//		if(true){
			//获得 查看工作流信息 成功
			var changeMemberDeptSuccess = function(result){
				var changeMemberDeptView = result.changeMemberDeptView;
				changeMemberDeptView.workFlowId = workflowId;
				WorkFlowEditView.showWorkFlowEditWin(changeMemberDeptView,'CHANGE_MEMBER_DEPT');
			}
			me.myWorkFlowData.acquireChangeMemberDept(params,changeMemberDeptSuccess,acquireMemberExaminViewFail);			
		}
		// 散客账号审批 
		else if('satterCustAccount'==workflowName){
			//获得 查看工作流信息 成功
			var scAccountAlter = function(result){
				var scatterCustomerView = result.scatterCustomerView;
				scatterCustomerView.workFlowId =workflowId;
				scatterCustomerView.todoFlag =todoFlag;
//				WorkFlowEditView.showWorkFlowEditWin(scatterCustomerView,'APPROVE_SATTERCUST_ACCOUNT');
				var win = Ext.create('WorkFlowScatterCustWindow',{params:scatterCustomerView});
				win.show();
			};
			me.myWorkFlowData.viewLoadScatterAccount(params,scAccountAlter,acquireMemberExaminViewFail);
		}
		//合同新增
		else if('contractAdd'==workflowName){
			//获得 查看工作流信息 成功
			var contractAddSuccess = function(result){
				var contractExaminView = result.contractExaminView;
				contractExaminView.workFlowId = workflowId;
				contractExaminView.todoFlag = todoFlag;
				WorkFlowEditView.showWorkFlowEditWin(contractExaminView,'CONTRACT_ADD');
			}
			me.myWorkFlowData.acquireContractAdd(params,contractAddSuccess,acquireMemberExaminViewFail);
		}
	},
	//校验是否查询条件都为空
	validateCondition:function(){
		var me = this;
		var form = me.parent.myWorkFlowSearchCondition;
		var flag = false;
		form.getForm().getFields().each(function(field){
			if(!(DpUtil.isEmpty(field.getValue()))&&field.getValue()!=i18n('i18n.PotentialCustManagerView.searchEndTime')){
				flag = true;
			}
		});
		return flag;
	}
});


/**
 * 我的工作流查询结果
 */
Ext.define('MyWorkFlowDealManagerResultGrid',{
	extend:'SearchGridPanel',
	parent:null,
	myWorkFlowData:null,
	initComponent:function(){
		var me = this;
		me.store = me.myWorkFlowData.initMyWorkFlowDealManagerStore(me.beforeLoadScatterFn);
		me.selModel = Ext.create('Ext.selection.CheckboxModel',{'mode':'SINGLE'});//只允许单选
		me.columns = me.getColumns();
		me.listeners = me.getMyListeners();
		me.dockedItems = me.getMyDockedItems();
		this.callParent();
	},
	getColumns:function(){
		var me = this;
		return [{
			header:i18n('i18n.ContractEditView.workFlowNum'),
			dataIndex:'workflowId'
		},{
			header:i18n('i18n.MyWorkFlowDealManagerView.workFlowName'),
			dataIndex:'workflowName',
			width:150,
			renderer  : function(value){
				return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.WORKFLOW_NAME);
			}
		},{
			header:i18n('i18n.MyWorkFlowDealManagerView.workFlowStatus'),
//			hidden:true,
			dataIndex:'status',
			renderer  : function(value){
				return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.WORKFLOW_STATE);
			}
		},{
			header:i18n('i18n.MyWorkFlowDealManagerView.applicants'),
//			hidden:true,
			labelWidth:50,
			dataIndex:'userName'
		},{
			header:i18n('i18n.MyWorkFlowDealManagerView.applicationTime'),
//			hidden:true,
			dataIndex:'createDate',
			renderer:DpUtil.renderDate
		},{
			header : i18n('i18n.ScatterUpgradeView.belongDeptId'),
			hidden:true,
			dataIndex:'deptId'
		},{
			header : i18n('i18n.ScatterUpgradeView.belongdeptName'),
			width:150,
//			hidden:true,
			dataIndex:'deptName'
		},{
			header : i18n('i18n.ScatterUpgradeView.belongdeptName'),
			hidden:true,
			dataIndex:'deptName'
		},{
			header : i18n('i18n.MyWorkFlowDealManagerView.businessid'),
			hidden:true,
			dataIndex:'applicationId'
		},
		//后台查询  查看数据需要
		{
			header : '',
			hidden:true,
			dataIndex:'applicationName'},{
			header : '',
			hidden:true,
			dataIndex:'applicationDesc'},{
			header : '',
			hidden:true,
			dataIndex:'applicationStatusDesc'},{
			header : '',
			hidden:true,
			dataIndex:'url'},{
			header : '',
			hidden:true,
			dataIndex:'roleId'},{
			header : '',
			hidden:true,
			dataIndex:'roleName'},{
			header : '',
			hidden:true},{
			dataIndex:'todoFlag',
			header : '',
			hidden:true
		}];
	},
	//分页条
	getMyDockedItems :function(){ 
		var me = this;
		return [ {
			xtype : 'pagingtoolbar',
			store : me.store,
			dock : 'bottom',
			displayInfo : true
		} ];
	},
	//双击事件
	getMyListeners : function() {
//		var me = this;
//		return {
//			scope : me,
//			itemdblclick : function(grid,record){
//				var acquireMemberExaminViewParams = {};
//				acquireMemberExaminViewParams.todoWorkflow = record.data;
//				//获得 查看工作流信息 成功
//				var acquireMemberExaminViewSuccess = function(result){
//					var memberExaminView = result.memberExaminView();
//					var params = null;
//					//显示工作流审批界面
//					WorkFlowEditView.showWorkFlowEditWin(params);
//				}
//				//获得 查看工作流信息 失败
//				var acquireMemberExaminViewFail = function(result){
//					
//				}
//				me.myWorkFlowData.acquireMemberExaminView(acquireMemberExaminViewParams,acquireMemberExaminViewSuccess,acquireMemberExaminViewFail);
//			}
//		};
	},
	// beforeLoad方法
	beforeLoadScatterFn:function(store, operation, eOpts){
		var searchConditionForm = Ext.getCmp('myWorkFlowSearchFormId');
		if(searchConditionForm!=null){
			searchConditionForm.getForm().updateRecord(searchConditionForm.record);
			//设置请求参数
			var searchCondition = searchConditionForm.record.data;
			var searchParams = {
					// 流程序号
					'workflowCondition.workflowId':searchCondition.workflowId,
					// 流程名称
					'workflowCondition.workflowName':searchCondition.workflowName,
					// 流程状态
					'workflowCondition.applicationStatus':searchCondition.applicationStatus,
					// 所属部门
					'workflowCondition.deptId':searchCondition.deptId,
					// 申请人
					'workflowCondition.createUser':searchCondition.createUser,
					// 申请开始时间
					'workflowCondition.startDate':searchCondition.startDate,
					// 申请结束时间
					'workflowCondition.endDate':searchCondition.endDate};
			Ext.apply(operation,{
				params : searchParams
				}
			);	
		}
	}
});

Ext.onReady(function(){
	var params = ['WORKFLOW_NAME',// 工作流名称
      			  'WORKFLOW_STATE'// 流程状态
	              ];
	initDataDictionary(params);
	//显示会员修改 内容描述
	new ModifyMemberControl().getModifyMember();
	myMorkFlowManagerDataControl.getCurrentUser();
	Ext.create('Ext.container.Viewport',{
		layout:'fit',
//		autoScroll:true,
		items:[Ext.create('MyWorkFlowDealManagerPanel',{'myWorkFlowData':myMorkFlowManagerDataControl})]
	});
});


