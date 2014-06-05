/**
* 工作流审批界面
*/
var myWorkFlowEditDataControl =  (CONFIG.get("TEST"))? new MyWorkFlowDealManagerDataTest():new MyWorkFlowDealManagerData();
var contractManagerControl = (CONFIG.get('TEST'))? Ext.create('ContractManagerDataTest'):Ext.create('ContractManagerData');
//var myWorkFlowEditDataControl =  new MyWorkFlowDealManagerData();

WorkFlowEditView = function(){};
WorkFlowEditView.showWorkFlowEditWin = function(params,createState){
	var dictionaryParams =[	// 目标级别i18n('i18n.ChangeContactAffiliatedRelationView.nowLevelCustLevel'),会员等级
          			'MEMBER_GRADE',
          			// 客户行业
          			'TRADE',
          			// 客户属性
          			'CUSTOMER_NATURE',
          			// 公司性质
          			'COMP_NATURE',
          			// 客户类型
          			'CUSTOMER_TYPE',
          			// 客户潜力类型
          			'COOPERATION_INTENTION',
          			// 客户潜力类型
          			'CUST_POTENTIAL',
          			'CARGO_POTENTIAL',
          			// 上一年公司规模
          			'FIRM_SIZE',
          		//客户来源
          			'CUST_SOURCE',
          			// 信用等级
          			'CREDIT_GRADE',
          			// 地址类型
          			'ADDRESS_TYPE',
          			// 联系人类型 已取消
          			// 物流决定权
          			'LOGIST_DECI',
          			// 付款方式
          			'PAY_WAY',
          			// 来源渠道',偏好渠道
          			'PREFERENCE_CHANNEL',
          			// 偏好服务
          			'PREFERENCE_SERVICE',
          			// 发票要求
          			'BILL_REQUIRE',
          			//账户性质
          			'ACCOUNT_NATURE',
          			//账户用途
          			'ACCOUNT_USE',
          			//客户类别
          			'CUST_CATEGORY',
          			//性别
          			'GENDER',
	      			//是否接受营销信息
	      			'MARKETINFO',
          			'WORKFLOW_NAME',// 工作流名称
	 				'WORKFLOW_STATE',// 流程状态
	      			//联系人证件类型
	      			'CARDTYPECON',
	      			//税务登记号证件类型
	      			'CARDTYPECUST',
	      			//证件类型新增和修改(大区总以上权限)
	      			'CARDTYPECON_NOTVIEW',
	      			//证件类型新增和修改
	      			'CARDTYPECON_AU',
	      		//客户性质（潜散客固客
	      			'CUST_TYPE',
	      			'OPERATE_TYPE'
          			];
	initDataDictionary(dictionaryParams);
	//初始化自定义的数据字典--联系人类型
	memberCustControl.initSelfDefineLinkmanType(DataDictionary);
	if(params != null){
		if('CREATE_SPECIAL_MEMBER'==createState){//特殊会员
			Ext.create('WorkFlowEditWindow',{'y':30,'width':815,'height':500,'params':params,'createState':createState,'myWorkFlowData':myWorkFlowEditDataControl}).show();
		}else if('UPDATE_CUSTOMERINFO'==createState){//修改会员
			Ext.create('WorkFlowEditWindow',{'y':30,'width':815,'height':680,'params':params,'createState':createState,'myWorkFlowData':myWorkFlowEditDataControl}).show();
		}else if('CHANGE_MEMBER_DEPT'==createState){//会员部门变更额
			Ext.create('WorkFlowEditWindow',{'width':560,'height':430,'params':params,'createState':createState,'myWorkFlowData':myWorkFlowEditDataControl}).show();
		}else if('CONTRACT_ADD'==createState){//合同新增
			Ext.create('WorkFlowEditWindow',{'width':805,'height':610,'params':params,'createState':createState,'myWorkFlowData':myWorkFlowEditDataControl}).show();
		}
	}else{
		Ext.create('WorkFlowEditWindow',{'createState':createState,'myWorkFlowData':myWorkFlowEditDataControl}).show();
	}
};
/**
 * 工作流审批 提交事件【会员归属变更，账户维护】
 * 散客账户维护：APPROVE_SATTERCUST_ACCOUNT;固定客户归属部门变更：CHANGE_MEMBER_DEPT;
 */
function commitEvent4Workflow (button,createState,me){
	var workFlowId = me.params.workFlowId;
	//提交按钮不可用
	button.setDisabled(true);
	var form = me.workFlowTreatmentSuggestions;
	var ap = Ext.getCmp('aggAppro').getValue();
	var app = Ext.getCmp('disAggAppro').getValue();
	if (!ap&&!app) {
		MessageUtil.showErrorMes(i18n('i18n.MyWorkFlowDealEditView.selectOneOpinon'));
		button.setDisabled(false);
		return;
	}
	if(!Ext.isEmpty(form.getForm().findField('disposeUserId').getValue())&&
		!Ext.isEmpty(form.getForm().findField('opinion'))){
			var commitParams = {};
			var record = form.record;
			form.getForm().updateRecord(record);
			if(''==record.get('modifyDate')){//后台接收modifyDate一个时间对象
				record.set('modifyDate',null);
			}
			record.set('disposeUserId',top.User.userId);//当前登录用户id
			record.set('workFlowId',workFlowId);
			commitParams.examineRecord = record.data;
			//提交 成功
			var commitSuccess = function(result){
				button.setDisabled(false);
				MessageUtil.showInfoMes(i18n('i18n.MyWorkFlowDealEditView.commitSuccessWar'));
				me.close();
				myWorkFlowEditDataControl.getMyWorkFlowDealManagerStore().loadPage(1);
			}
			//提交 失败
			var commitFail = function(result){
				button.setDisabled(false);
				MessageUtil.showErrorMes(result.message);
				me.close();
			}
			// 会员归属变更请求
			if('CHANGE_MEMBER_DEPT' == createState){
				myWorkFlowEditDataControl.commitChangeMemberDeptExamin(commitParams,commitSuccess,commitFail);
			}//散客账户信息
			else if('APPROVE_SATTERCUST_ACCOUNT' == createState){
				myWorkFlowEditDataControl.commitSCAccountAlterExamin(commitParams,commitSuccess,commitFail);
			}
		}
};
Ext.define('WorkFlowEditWindow',{
	extend:'Ext.window.Window',
	title:i18n('i18n.MyWorkFlowDealEditView.approvalWorkflow'),
	workflowPanel:null,
	layout:'fit',
	width:860,
	height:350,
//	width:860,
//	height:650,
	params:null,//父界面 传递参数
	createState:null,//工作流名称
	myWorkFlowData:null,//工作流data层
	workFlowTreatmentSuggestions:null,//处理工作流意见
	initComponent:function(){
		var me = this;
		var alterInfo = me.params.appList;//修改信息字段
		var workFlowManagerResultInfo = null;//加载审批记录 grid
		var record = null;//工作流审批意见及审批人
		//会员部门变更
		if('CHANGE_MEMBER_DEPT'==me.createState){
			var changeMemberDeptRecord = Ext.create('ChangeMemberDeptModel',me.params.changeMemberDept);
			//会员部门变更 form
			var changeMemberDeptForm = Ext.create('ChangeMemberDeptForm',{
				'viewStatus':'VIEW',
				height:120,
				'changeMemberDeptRecord':changeMemberDeptRecord});
			record = Ext.create('ExamineRecordModel');
			//历史审批记录
			workFlowManagerResultInfo = Ext.create('WorkFlowEditResultGrid',{
				'title':i18n('i18n.MyWorkFlowDealEditView.m_historyApply'),
				height:120,
				'parent':me,
				'myWorkFlowData':me.myWorkFlowData});
			//处理工作流
			me.workFlowTreatmentSuggestions = Ext.create('WorkFlowEditSearchForm',{
				'parent':me,
				'textareaWidth':525,
				'myWorkFlowData':me.myWorkFlowData,
				'record':record});
			me.fbar = me.getFbar();
			me.items = [{
				xtype:'basicpanel',
				width:500,
				height:100,
				layout:{
			        type:'vbox',
			        align:'stretch'
			    },
				items:[changeMemberDeptForm,
					workFlowManagerResultInfo,
					me.workFlowTreatmentSuggestions
				]
			}];
		}else{
			me.workflowPanel = Ext.create('WorkFlowEditPanel',{
				'myWorkFlowData':me.myWorkFlowData,
				'params':me.params,'parentWin':me,
				'createState':me.createState});
			me.items = [me.workflowPanel];
		}
		this.callParent();
		//会员部门变更
		if('CHANGE_MEMBER_DEPT' == me.createState){
			if(!Ext.isEmpty(record)){
				//显示审批人
				record.set('disposeUserId',top.User.empName);
				me.workFlowTreatmentSuggestions.loadRecord(record);		
			}
		}else{
			//修改字段记录
			var alterInfoGrid = me.workflowPanel.workflowFirstPanel.alterInfoPanel;
			if(!Ext.isEmpty(alterInfoGrid)){
				alterInfoGrid.store.loadData(me.processingData(alterInfo));
			}
			workFlowManagerResultInfo = me.workflowPanel.workflowSecondPanel.workFlowManagerResultInfo;
		}
	    //加载审批记录
		workFlowManagerResultInfo.store.loadData(me.params.examineRecordList);

	},
	//处理会员维护修改数据，对ID类的数据不进行显示
	processingData:function(alterInfo){
		var me = this;
		var returnValue = [];
		for (var i = 0; i < alterInfo.length; i++){
			var fieldName = ModifyMember.getField(alterInfo[i].fieldName);
			if('KEY' != fieldName && 'LIST' != fieldName && 'OBJ' != fieldName && 'ISSTATUS' != fieldName){
				//修改前后相同的的不显示
				if(alterInfo[i].newValue!=alterInfo[i].oldValue
				&&!(Ext.isEmpty(alterInfo[i].newValue)
				&&Ext.isEmpty(alterInfo[i].oldValue))){
					returnValue.push(alterInfo[i]);
				}
			}
		}
		return returnValue;
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.MyWorkFlowDealEditView.b_commite'),
			scope:me,
			handler:me.commitEvent
		},{
			xtype:'button',
			text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
			handler:function(){
				me.close();
			}
		}];
	},
	//提交审批事件
	commitEvent:function(button){
		commitEvent4Workflow(button,this.createState,this);
	}
});

Ext.define('WorkFlowEditPanel',{
	extend:'BasicPanel',
//	width:850,
	height:300,
	params:null,//父界面 传递参数
	createState:null,//工作流名称
	myWorkFlowData:null,//工作流数据层
	workflowFirstPanel:null,
	workflowSecondPanel:null,
	layout:'card',
	autoScroll:true,
	fbar:null,
	parentWin:null,
    initComponent:function(){
    	var me = this;
		var member = me.params.member;//会员信息
		var alterInfo = me.params.appList;//修改信息字段
		var workFlowId = me.params.workFlowId;
		var operateGrid = me.params.operateGrid;
    	me.workflowFirstPanel = Ext.create('WorkFlowFirstPanel',{
    		'member':member,
    		'params':me.params,
    		'alterInfo':alterInfo,
    		'myWorkFlowData':me.myWorkFlowData,
    		'parent':me,'parentWin':me.parentWin,
    		'createState':me.createState});
    	me.workflowSecondPanel = Ext.create('WorkFlowSecondPanel',{
    		'operateGrid':operateGrid,
    		'workFlowId':workFlowId,
    		'myWorkFlowData':me.myWorkFlowData,
    		'parent':me,'parentWin':me.parentWin,
    		'createState':me.createState});
    	me.items = [me.workflowFirstPanel,me.workflowSecondPanel];
    	this.callParent();
    }
});

/**
 * 工作流审批第一界面
 */
Ext.define('WorkFlowFirstPanel',{
	extend:'BasicPanel',
	member:null,
	memberEditPanel:null, //会员信息
	alterInfoPanel:null,  //修改信息
	params:null,//list通过id查询结果
	alterInfo:null,//修改信息字段
	myWorkFlowData:null,
	layout:{
		type:'vbox',
		align:'stretch'
	},
	parent:null, //父面板
	parentWin:null, //父窗口
	createState:null,//标志创建哪类工作流 1、会员信息维护 UPDATE_CUSTOMERINFO 2、创建特殊会员 CREATE_SPECIAL_MEMBER
	initComponent:function(){
		var me = this;
		me.memberEditPanel = Ext.create('MemberCustEditPanel',{
			'member':me.member,
			'viewStatus':'view',
			'channelType':'workflow'});
		//会员信息维护
		if('UPDATE_CUSTOMERINFO'==me.createState){
			me.alterInfoPanel = Ext.create('MemberAlterGrid',{'myWorkFlowData':me.myWorkFlowData});
			me.items = [{
				xtype:'basicpanel',
				height:425,
				items:[me.memberEditPanel]
			},{
				xtype:'basicpanel',
//				flex:1,
				height:150,
				items:[me.alterInfoPanel]
			}];
		}
		//创建特殊会员
		else if('CREATE_SPECIAL_MEMBER'==me.createState){
			me.items = [{
				xtype:'basicpanel',
				height:800,
				items:[me.memberEditPanel]
			}];
		}
		//合同新增
		else if('CONTRACT_ADD'==me.createState){
			var contractEditView = Ext.create('ContractEditView',{
				'parentWin':me.parentWin,
				'contractData':contractManagerControl,
				'contractView':me.params.contractView,
				'showFbar':'MWF',
				'status':'VIEW'});
			me.items = [{
				xtype:'basicpanel',
				height:535,
				items:[contractEditView]
			}];
		}
	   	me.fbar = me.getFbar();
		this.callParent();
	},
    getFbar:function(){
    	var me = this;
		return [{
			text:i18n('i18n.ViewContactAffiliatedRelationView.forApproval'),
			xtype:'button',
			scope:me,
			handler:me.goToNextPanel
		},{
			text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
			xtype:'button',
			handler:function(){
				if(me.parentWin != null){
					me.parentWin.close();
				}
			}
		}];
    },
    //进入审批页面
    goToNextPanel:function(){
    	var me = this;
    	var layout = me.parent.getLayout();
    	layout['next']();
    }
});

/**
 * 工作流审批第一界面修改信息显示面板
 */
Ext.define('MemberAlterGrid',{
	extend:'PopupGridPanel',
	id:'memberAlterGridId',
	myWorkFlowData:null,
	initComponent:function(){
		var me = this;
		me.columns = me.getColumns();
		me.store = me.myWorkFlowData.getExamineRecordStore();
		this.callParent();
	},
	getColumns:function(){
		var me = this;
		return [new Ext.grid.RowNumberer(),{
				flex:1,
				header : '修改对象',
				dataIndex : 'changerName'
			},{
				flex:1,
				header : i18n('i18n.MyWorkFlowDealEditView.fieldName'),
				dataIndex : 'fieldName',
				renderer : ModifyMember.getField
			},{
				flex:1,
				header : i18n('i18n.MyWorkFlowDealEditView.beforeUpdate'),
				dataIndex : 'oldValue',
				renderer : function(value, metaData, record){
					var name = record.get('fieldName');
					//标志位 "ISTXT"代表直接显示该文本
					if('ISTXT' == ModifyMember.getFieldModify(name,value)){
						return value;
					}else if('ISDATE' == ModifyMember.getFieldModify(name,value)){
						return DpUtil.renderDate(value);
					}else{
						return ModifyMember.getFieldModify(name,value);
					}
				}
			},{
				flex:1,
				header : i18n('i18n.MyWorkFlowDealEditView.afterUpdate'),
				dataIndex : 'newValue',
				renderer : function(value, metaData, record){
					var name = record.get('fieldName');
					if('ISTXT' == ModifyMember.getFieldModify(name,value)){
						return value;
					}else if('ISDATE' == ModifyMember.getFieldModify(name,value)){
						return DpUtil.renderDate(value);
					}else{
						return ModifyMember.getFieldModify(name,value);
					}
				}
			}];
	}
});
/**
 * 工作流审批第二界面
 */
Ext.define('WorkFlowSecondPanel',{
	extend:'BasicPanel',
	workFlowTreatmentSuggestions:null,      //处理意见TreatmentSuggestions
	workFlowManagerResultInfo:null,  //历史处理记录
	myWorkFlowData:null,
	operateGrid:null,//父界面数据grid
	items:null,
	layout:{
        type:'vbox',
        align:'stretch'
    },
    parent:null,  //父面板
    parentWin:null, //父窗口
    createState:null,
    workFlowId:null,
    fbar:null,
	initComponent:function(){
		var me = this;
		var record = new ExamineRecordModel();
		me.workFlowTreatmentSuggestions = Ext.create('WorkFlowEditSearchForm',{'parent':me,'myWorkFlowData':me.myWorkFlowData,'record':record});
		me.workFlowManagerResultInfo = Ext.create('WorkFlowEditResultGrid',{'parent':me,'myWorkFlowData':me.myWorkFlowData});
		me.items = [
		{
				xtype:'basicpanel',
				flex:1,
				layout:{
					type:'vbox',
					align:'stretch'
				},
				items:[{
					xtype:'titlepanel',
					items:[{xtype: 'displayfield',
							value: i18n('i18n.MyWorkFlowDealEditView.historicalDisposalRecords'),
							width:100}]
				}
				,{
					xtype:'basicpanel',
					flex:1,
					items:[me.workFlowManagerResultInfo]
				}
				]
		}
		,{
			xtype:'basicpanel',
			flex:1,
			items:[me.workFlowTreatmentSuggestions]
		}
		];
		me.fbar = me.getFbar();
		me.callParent();
		me.workFlowTreatmentSuggestions.loadRecord(record);
		me.workFlowTreatmentSuggestions.getForm().findField('disposeUserId').setValue(CurrentUser.empName);
	},
    getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.IntegralRuleEdit.return'),
			scope:me,
			handler:me.comeBackToFirst
		},{
			xtype:'button',
			text:i18n('i18n.MemberCustEditView.submit'),
			scope:me,
			handler:me.commitEvent
		},{
			xtype:'button',
			text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
			handler:function(){
				if(me.parentWin != null){
					me.parentWin.close();
				}
			}
		}];
	},
	//回到第一个页面，查看审批信息
	comeBackToFirst:function(){
		var me = this;
		var layout = me.parent.getLayout();
		layout['prev']();
	},
	//提交
	commitEvent:function(button){
		var me = this;
		//提交按钮不可用
		button.setDisabled(true);
		var form = me.workFlowTreatmentSuggestions;
		var ap = Ext.getCmp('aggAppro').getValue();
		var app = Ext.getCmp('disAggAppro').getValue();
		if (!ap&&!app) {
			MessageUtil.showErrorMes(i18n('i18n.MyWorkFlowDealEditView.selectOneOpinon'));
			button.setDisabled(false);
			return;
		}
		if(!Ext.isEmpty(form.getForm().findField('disposeUserId').getValue())){
				var commitParams = {};
				var record = form.record;
				form.getForm().updateRecord(record);
				if(''==record.get('modifyDate')){//后台接收modifyDate一个时间对象
					record.set('modifyDate',null);
				}
				record.set('disposeUserId',CurrentUser.userId);//当前登录用户
				record.set('workFlowId',me.workFlowId);
				commitParams.examineRecord = record.data
				//提交 成功
				var commitSuccess = function(result){
					button.setDisabled(false);
					MessageUtil.showInfoMes(i18n('i18n.MyWorkFlowDealEditView.commitSuccessWar'));
					me.parentWin.close();
					me.operateGrid.store.loadPage(1);
				}
				//提交 失败
				var commitFail = function(result){
					button.setDisabled(false);
					MessageUtil.showErrorMes(result.message);
					me.parentWin.close();
				}
				if('CREATE_SPECIAL_MEMBER'==me.createState){
					me.myWorkFlowData.commitSepCreateMemberExamin(commitParams,commitSuccess,commitFail);			
				}
				if('UPDATE_CUSTOMERINFO'==me.createState){
					me.myWorkFlowData.commitModifyMemberExamin(commitParams,commitSuccess,commitFail);			
				}else if('APPROVE_CONTACT_BELONG'==me.createState){
					me.myWorkFlowData.commitMemberContactRalationExamin(commitParams,commitSuccess,commitFail);
				}else if('CONTRACT_ADD'==me.createState){
					me.myWorkFlowData.commitContractAddExamin(commitParams,commitSuccess,commitFail);
				}
			}
	}
});
/**
 * 审批意见
 */
Ext.define('WorkFlowEditSearchForm',{
	extend:'NoTitleFormPanel',
	id:'workFlowEditSearchFormId',
	layout:{
		type:'table',
		columns:4
	},
	defaults:{
		labelWidth : 65,
		width : 200
	},
	record:null,
	parent:null,
	textareaWidth:null,//控制审批意见宽度
	myWorkFlowData:null,
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'dptextfield',
			fieldLabel:i18n('i18n.MyWorkFlowDealEditView.approver'),
			name : 'disposeUserId',
			readOnly:true
		},{
			xtype:'radiogroup',
			fieldLabel:i18n('i18n.MyWorkFlowDealEditView.approvalResults'), 
			colspan : 3,
			items:[{
				xtype : 'radio',
				boxLabel : i18n('i18n.MyWorkFlowDealEditView.agree'),
				name:'result',
				id:'aggAppro',
				inputValue:'1'
			}, {
				xtype : 'radio',
				boxLabel : i18n('i18n.MyWorkFlowDealEditView.notAgree'),
				name:'result',
				id:'disAggAppro',
//				checked:true,
				inputValue:'0'
			}]
         },{
			xtype : 'textareafield',
			fieldLabel : i18n('i18n.MyWorkFlowDealEditView.m_applyReason'),
			colspan : 4,
			width : Ext.isEmpty(me.textareaWidth)?825:me.textareaWidth,
			height : 60,
			maxLength:200,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',200),
			name : 'opinion'
		}];
	}
});


/**
 * 历史审批记录
 */
Ext.define('WorkFlowEditResultGrid',{
	extend:'PopupGridPanel',
	id:'workFlowEditResultGridId',
	parent:null,
	myWorkFlowData:null,
	initComponent:function(){
		var me = this;
		me.columns = me.getColumns();
		me.store = me.myWorkFlowData.getApproveDataStore();
		this.callParent();
	},
	getColumns:function(){
		var me = this;
		return [new Ext.grid.RowNumberer(),{
			header:i18n('i18n.MyWorkFlowDealEditView.processingTime'),
			dataIndex:'modifyDate',
			renderer:DpUtil.renderDate
		},{
			header:i18n('i18n.MyWorkFlowDealEditView.dealingWithPeople'),
			dataIndex:'disposeUserId'
		},{
			header:i18n('i18n.MyWorkFlowDealEditView.theResults'),
			dataIndex:'result',
			renderer : function(value){
				var result = null;
				if(value){
					result = i18n('i18n.MyWorkFlowDealEditView.agree');
				}else{
					result = i18n('i18n.MyWorkFlowDealEditView.notAgree');
				}
				return result;
			}
		},{
			header:i18n('i18n.MyWorkFlowDealEditView.mattersOfOpinion'),
			dataIndex:'opinion'
		},{
			header:i18n('i18n.MyWorkFlowDealEditView.powersAndResponsibilities'),
			dataIndex:'rights'
		}];
	}
});


