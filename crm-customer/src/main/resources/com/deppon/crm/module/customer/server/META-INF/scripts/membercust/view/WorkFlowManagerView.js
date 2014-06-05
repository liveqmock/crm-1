/**
* 我的工作流 管理界面
*/
var workFlowManagerDataControl =  (CONFIG.get("TEST"))? new WorkFlowManagerDataTest():new WorkFlowManagerData();
//var workFlowManagerDataControl =  new WorkFlowManagerData();
WorkFlowManagerView = function(){};
/**
 * 查看工作流详情
 */
WorkFlowManagerView.viewWorkFlowDetail = function(grid,me){
	Ext.isEmpty(me)?me = this:'';
	var selection=grid.getSelectionModel().getSelection();
	if (selection.length != 1) {
		MessageUtil.showMessage( i18n('i18n.MyWorkFlowDealManagerView.pleaseSelectOneRecordWar'));
		return;
	}
	var params = {};
	params.workFlow = selection[0].data;
	//获得 查看工作流信息 失败
	var acquireMemberExaminViewFail = function(result){
		MessageUtil.showErrorMes(result.message);
	}
	var workflowId = selection[0].get('workFlowId');
	var todoFlag = selection[0].get('todoFlag');
	//创建特殊会员
	if('createSpecialMember'==selection[0].get('workFLowName')){
		//获得 查看工作流信息 成功
		var acquireSepMemberExaminViewSuccess = function(result){
			var memberExaminView = result.memberExaminView;
			memberExaminView.workFlowId = workflowId;
			memberExaminView.todoFlag = todoFlag;
			//显示工作流审批界面
			WorkFlowEditView.showWorkFlowEditWin(memberExaminView,'CREATE_SPECIAL_MEMBER');
		}
		me.workFlowManagerData.acquireSepMemberExaminView(params,acquireSepMemberExaminViewSuccess,acquireMemberExaminViewFail);	
	}
	//会员信息维护memberMyWorkFlowDealManager
	else if('memberModify'==selection[0].get('workFLowName')){
		//获得 查看工作流信息 成功
		var acquireModifyMemberExaminViewSuccess = function(result){
			var memberExaminView = result.memberExaminView;
			memberExaminView.workFlowId = workflowId;
			memberExaminView.todoFlag = todoFlag;
			//显示工作流审批界面
			WorkFlowEditView.showWorkFlowEditWin(memberExaminView,'UPDATE_CUSTOMERINFO');
		}
		me.workFlowManagerData.acquireModifyMemberExaminView(params,acquireModifyMemberExaminViewSuccess,acquireMemberExaminViewFail);			
	}
	//变更联系人挂靠关系CONTACT_VARY
	else if('contactVary'==selection[0].get('workFLowName')){
		//获得 查看工作流信息 成功
		var contactVarySuccess = function(result){
			var contactVaryExaminView = result.contactVaryExaminView;
			contactVaryExaminView.workFlowId = workflowId;
			contactVaryExaminView.todoFlag = todoFlag;
			contactVaryExaminView.appList = {};//防止 找不到属性报错
			//显示工作流审批界面
			WorkFlowEditView.showWorkFlowEditWin(contactVaryExaminView,'CONTACT_VARY');
		}
		me.workFlowManagerData.acquireContactVary(params,contactVarySuccess,acquireMemberExaminViewFail);			
	}
	//变更会员变更部门
	else if('changeMemberDept'==selection[0].get('workFLowName')){
		//获得 查看工作流信息 成功
		var changeMemberDeptSuccess = function(result){
			var changeMemberDeptView = result.changeMemberDeptView;
			WorkFlowEditView.showWorkFlowEditWin(changeMemberDeptView,'CHANGE_MEMBER_DEPT');
		}
		me.workFlowManagerData.viewChangeMemberDept(params,changeMemberDeptSuccess,acquireMemberExaminViewFail);			
	}
	//散客账户维护
	else if('satterCustAccount'==selection[0].get('workFLowName')){
		//获得 查看工作流信息 成功
		var scAccountAlter = function(result){
			var scatterCustomerView = result.scatterCustomerView;
			scatterCustomerView.workFlowId = workflowId;
			scatterCustomerView.todoFlag = todoFlag;
			var win = Ext.create('WorkFlowScatterCustWindow',{
				params:scatterCustomerView,
				operateMenuType:'MY-VIEW'});
			win.show();
		};
		me.workFlowManagerData.viewScatterAccountExamin(params,scAccountAlter,acquireMemberExaminViewFail);
	}
	//合同新增
	else if('contractAdd'==selection[0].get('workFLowName')){
		//获得 查看工作流信息 成功
		var contractAddSuccess = function(result){
			var contractExaminView = result.contractExaminView;
			contractExaminView.workFlowId = workflowId;
			contractExaminView.todoFlag = todoFlag;
			WorkFlowEditView.showWorkFlowEditWin(contractExaminView,'CONTRACT_ADD');
		}
		me.workFlowManagerData.viewContractAddExamin(params,contractAddSuccess,acquireMemberExaminViewFail);
	}
};
/**
 * 查询工作流 我的工作流/我的已处理的工作流
 */
WorkFlowManagerView.searchWorkFlowList = function(me,serachButtonType){
	Ext.isEmpty(me)?me = this:'';
	//全局变量  处理的：APPROVED approved 申请的：APPLIED applied,默认为查询 我申请的共组流查询
	Ext.isEmpty(serachButtonType)?SerachType = 'APPLIED':SerachType = serachButtonType;
	var form = me.parent.workFlowCondition;
	if(!me.validateCondition()){
		MessageUtil.showMessage(i18n('i18n.MemberUpgradeView.message_notAllNull'));
		return;
	}
	if(!form.getForm().isValid()){
		MessageUtil.showMessage(i18n('i18n.MyWorkFlowDealManagerView.m_changeAllRight'));
		return;
	}
	me.workFlowManagerData.getWorkFlowManagerStore().loadPage(1);
};
Ext.define('WorkFlowManagerPanel',{
	extend:'BasicPanel',
//	title:i18n('i18n.WorkFlowManagerView.workflowManagement'),
	workFlowCondition:null,      //查询条件
	workFlowResultInfo:null,  //我的工作流 查询结果
	workFlowButtonPanel:null,  //我的工作流 操作按钮
	workFlowManagerData:null,
	serachType:null,//查询按钮类型  处理的：APPROVED approved 申请的：APPLIED applied
	layout:{
        type:'vbox',
        align:'stretch'
    },
	initComponent:function(){
		var me = this;
		var record = new WorkflowConditionModel();
		me.workFlowCondition = Ext.create('WorkFlowManagerSearchForm',{'parent':me,'serachType':me.serachType,'workFlowManagerData':me.workFlowManagerData,'record':record});
		me.workFlowResultInfo = Ext.create('WorkFlowManagerResultGrid',{'parent':me,'workFlowManagerData':me.workFlowManagerData});
		me.workFlowButtonPanel = Ext.create('WorkFlowManagerButtonPanel',{'parent':me,'serachType':me.serachType,'workFlowManagerData':me.workFlowManagerData});
		me.items = [{
			xtype:'basicpanel',
			height:80,
			items:[me.workFlowCondition]
		},{
			xtype:'basicpanel',
			height:36,
			items:[me.workFlowButtonPanel]
		},{
			xtype:'basicpanel',
			flex:1,
			items:[me.workFlowResultInfo]
		}];
		Ext.apply(me,{
			items:me.items
		});
		me.callParent();
		me.workFlowCondition.loadRecord(record);
		me.workFlowCondition.getForm().findField('workflowId').setValue();
	}
});

/**
 * 我的工作流 查询条件
 */
Ext.define('WorkFlowManagerSearchForm',{
	extend:'SearchFormPanel',
	id:'workFlowManagerSearchFormId',
	layout:{
		type:'table',
		columns:4
	},
	defaultType:'dptextfield',
	border:false,
	record:null,
	parent:null,
	serachType:null,
	workFlowManagerData:null,
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
			store:me.workFlowManagerData.getWorkFlowName(),
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
			store:me.workFlowManagerData.getWorkFlowState(),
			queryMode:'local',
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code'
		},{
			fieldLabel:i18n('i18n.MyWorkFlowDealManagerView.applicants'),
			maxLength:40,
			labelWidth:50,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',40),
			name:'createUser'
		},{
			xtype:'belongdeptcombox',
			forceSelection:true,
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
//    		me.parent.workFlowButtonPanel.searchWorkFlowList();
    		WorkFlowManagerView.searchWorkFlowList(me.parent.workFlowButtonPanel,me.serachType);
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
Ext.define('WorkFlowManagerButtonPanel',{
	extend:'NormalButtonPanel',
	parent:null,
	serachType:null,
	workFlowManagerData:null,
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
				text:i18n('i18n.IntegralRuleEdit.detail'),
				scope:me,
				handler:me.viewWorkFlowEvent}]
		},{
			xtype:'middlebuttonpanel'
		},{
			xtype:'rightbuttonpanel',
			width:150,
			items:[{
				xtype:'button',
				text:i18n('i18n.PotentialCustManagerView.search'),
				scope:me,
				handler:me.searchWorkFlowList
				}]
		}];
	},
	//工作流 查询
	searchWorkFlowList:function(){
		WorkFlowManagerView.searchWorkFlowList(this,this.serachType);
	},
	viewWorkFlowEvent:function(){
		var me = this;
		me.viewWorkFlow(me.parent.workFlowResultInfo);
	},
	//查看工作流
	viewWorkFlow:function(grid){
		WorkFlowManagerView.viewWorkFlowDetail(grid,this);
	},
	//校验是否查询条件都为空
	validateCondition:function(){
		var me = this;
		var form = me.parent.workFlowCondition;
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
 * 我的工作流工作流查询结果
 */
Ext.define('WorkFlowManagerResultGrid',{
	extend:'SearchGridPanel',
	parent:null,
	workFlowManagerData:null,
	initComponent:function(){
		var me = this;
		me.store = me.workFlowManagerData.initWorkFlowManagerStore(me.beforeLoadFn);
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
			dataIndex:'workFlowId'
		},{
			header:i18n('i18n.MyWorkFlowDealManagerView.workFlowName'),
			dataIndex:'workFLowName',
			width:150,
			renderer  : function(value){
				return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.WORKFLOW_NAME);
			}
		},{
			header:i18n('i18n.MyWorkFlowDealManagerView.workFlowStatus'),
			dataIndex:'status',
			renderer  : function(value){
				return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.WORKFLOW_STATE);
			}
		},{
			header:i18n('i18n.MyWorkFlowDealManagerView.applicants'),
			dataIndex:'userName'
		},{
			header:i18n('i18n.MyWorkFlowDealManagerView.applicationTime'),
			dataIndex:'createDate',
			renderer:DpUtil.renderDate
		},{
			header : i18n('i18n.ScatterUpgradeView.belongDeptId'),
			hidden:true,
			dataIndex:'deptId'
		},{
			header : i18n('i18n.ScatterUpgradeView.belongdeptName'),
			width:150,
			dataIndex:'deptName'
		},{
			header : i18n('i18n.MyWorkFlowDealManagerView.businessid'),
			hidden:true,
			dataIndex:'appId'
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
		var me = this;
		return {
			scope:me,
	        'itemdblclick': function(){
	        	me.parent.workFlowButtonPanel.viewWorkFlow(me);
	        }
		}
		
	},
	// beforeLoad方法
	beforeLoadFn:function(store, operation, eOpts){
		var searchConditionForm = Ext.getCmp('workFlowManagerSearchFormId');
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
					'workflowCondition.endDate':searchCondition.endDate,
					// 查询按钮类型
					'searchButtonType':SerachType};
			Ext.apply(operation,{
				params : searchParams
				}
			);	
		}
	}
});


