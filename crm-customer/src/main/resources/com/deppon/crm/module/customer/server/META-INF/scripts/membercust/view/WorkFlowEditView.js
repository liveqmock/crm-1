/**
* 工作流审批界面
*/
var workFlowEditDataControl =  (CONFIG.get("TEST"))? new WorkFlowManagerDataTest():new WorkFlowManagerData();
var contractManagerControl = (CONFIG.get('TEST'))? Ext.create('ContractManagerDataTest'):Ext.create('ContractManagerData');
//var workFlowEditDataControl =  new WorkFlowManagerData();

WorkFlowEditView = function(){};
WorkFlowEditView.showWorkFlowEditWin = function(params,createState){
	var dictionaryParams = [  // 目标级别i18n('i18n.ChangeContactAffiliatedRelationView.nowLevelCustLevel'),会员等级
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
	      			'CUST_POTENTIAL',
	      			'CARGO_POTENTIAL',
	      			// 上一年公司规模
	      			'FIRM_SIZE',
	      			// 信用等级
	      			'CREDIT_GRADE',
	      			//客户来源
	      			'CUST_SOURCE',
	      			//客户性质
	      			'CUST_TYPE',
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
	      			//联系人证件类型
	      			'CARDTYPECON',
	      			//税务登记号证件类型
	      			'CARDTYPECUST',
	      			//证件类型新增和修改(大区总以上权限)
	      			'CARDTYPECON_NOTVIEW',
	      			//证件类型新增和修改
	      			'CARDTYPECON_AU'];
	initDataDictionary(dictionaryParams);//数据字典
	//初始化自定义的数据字典--联系人类型
	memberCustControl.initSelfDefineLinkmanType(DataDictionary);
	if(params != null){
		if('CREATE_SPECIAL_MEMBER'==createState){//特殊会员
			Ext.create('WorkFlowEditWindow',{y:30,'width':815,'height':700,'params':params,'createState':createState,'workFlowManagerData':workFlowManagerDataControl}).show();
		}else if('UPDATE_CUSTOMERINFO'==createState){//修改会员
			Ext.create('WorkFlowEditWindow',{y:30,'width':815,'height':750,'params':params,'createState':createState,'workFlowManagerData':workFlowManagerDataControl}).show();
		}else if('CONTACT_VARY'==createState){//联系人变更挂靠关系
			Ext.create('WorkFlowEditWindow',{y:30,'width':830,'height':650,'params':params,'createState':createState,'workFlowManagerData':workFlowManagerDataControl}).show();
		}else if('CHANGE_MEMBER_DEPT'==createState){//变更会员归属部门
			Ext.create('WorkFlowEditWindow',{'width':580,'height':415,'params':params,'createState':createState,'workFlowManagerData':workFlowManagerDataControl}).show();
		}else if('CONTRACT_ADD'==createState){//合同新增
			Ext.create('WorkFlowEditWindow',{'width':805,'height':750,'params':params,'createState':createState,'workFlowManagerData':workFlowManagerDataControl}).show();
		}
	}else{
		Ext.create('WorkFlowEditWindow',{'createState':createState,'workFlowManagerData':workFlowEditDataControl}).show();
	}
};

Ext.define('WorkFlowEditWindow',{
	extend:'Ext.window.Window',
	title:i18n('i18n.MyWorkFlowDealEditView.viewWorkflow'),
	workflowPanel:null,
	layout:'fit',
	width:840,
	height:740,
	params:null,//父界面 传递参数
	createState:null,//工作流名称
	workFlowManagerData:null,//工作流data层
	initComponent:function(){
		var me = this;
		var alterInfo = me.params.appList;//修改信息字段
		me.workflowPanel = Ext.create('WorkFlowEditPanel',{'workFlowManagerData':me.workFlowManagerData,'params':me.params,'parentWin':me,'createState':me.createState});
		me.items = [me.workflowPanel];
		this.callParent();
		var alterInfoGrid = me.workflowPanel.workflowFirstPanel.alterInfoPanel;
		//修改字段记录
		if(!Ext.isEmpty(alterInfoGrid)){
//			me.workflowPanel.workFlowManagerData.getApproveDataStore().loadData(alterInfo);
			alterInfoGrid.store.loadData(me.processingData(alterInfo));
		}
    	//加载审批记录
		var workFlowManagerResultInfo = me.workflowPanel.workflowSecondPanel.workFlowManagerResultInfo;
//		me.workflowPanel.workFlowManagerData.getExamineRecordStore().loadData(me.params.examineRecordList);
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
	}
});

Ext.define('WorkFlowEditPanel',{
	extend:'BasicPanel',
//	width:850,
	height:740,
	params:null,//父界面 传递参数
	createState:null,//工作流名称
	workFlowManagerData:null,//工作流数据层
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
		var operateGrid = me.params.operateGrid;//父界面操作 grid
    	me.workflowFirstPanel = Ext.create('WorkFlowFirstPanel',{'params':me.params,'member':member,'alterInfo':alterInfo,'workFlowManagerData':me.workFlowManagerData,'parent':me,'parentWin':me.parentWin,'createState':me.createState});
    	me.workflowSecondPanel = Ext.create('WorkFlowSecondPanel',{'operateGrid':operateGrid,'workFlowId':workFlowId,'workFlowManagerData':me.workFlowManagerData,'parent':me,'parentWin':me.parentWin,'createState':me.createState});
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
	params:null,//特殊处理 联系人变更挂靠关系 
	memberEditPanel:null, //会员信息
	alterInfoPanel:null,  //修改信息
	contactVaryInfo:null,//联系人变更信息
	changeDeptInfo:null,//会员归属部门变更信息
	alterInfo:null,//修改信息字段
	workFlowManagerData:null,
//	layout:{
//		type:'vbox',
//		align:'stretch'
//	},
	layout:'column',
	parent:null, //父面板
	parentWin:null, //父窗口
	createState:null,//标志创建哪类工作流 1、会员信息维护 UPDATE_CUSTOMERINFO 
						//2、创建特殊会员 CREATE_SPECIAL_MEMBER
						//3、联系人变更审批  CONTACT_VARY
	initComponent:function(){
		var me = this;
		me.memberEditPanel = null;
		me.workFlowManagerResultInfo = Ext.create('WorkFlowEditResultGrid',{'parent':me,'workFlowManagerData':me.workFlowManagerData});
		var currentExaminerArea =Ext.create('BasicPanel',{
			title:'当前审批人',
			height:80,
			width:755,
			items:{
				xtype : 'textareafield',
				value:Ext.isEmpty(me.params.currentExaminer)?'':me.params.currentExaminer,
				readOnly : true
			}
		});
		//会员信息维护
		if('UPDATE_CUSTOMERINFO'==me.createState){
			me.alterInfoPanel = Ext.create('MemberAlterGrid',{'workFlowManagerData':me.workFlowManagerData});
			me.memberEditPanel=Ext.create('MemberCustEditPanel',{'member':me.member,'viewStatus':'view','channelType':'workflow'});
			me.items = [{
				xtype:'basicpanel',
				height:400,
				width:755,
				items:[me.memberEditPanel]
			},{
				xtype:'basicpanel',
//				flex:1,
				width:755,
				height:150,
				items:[me.alterInfoPanel]
			},{
				xtype:'basicpanel',
//				flex:1,
//				autoScroll:true,
				width:755,
				height:100,
				items:[me.workFlowManagerResultInfo]
			},currentExaminerArea];
//			me.alterInfoPanel.store.loadData(me.alterInfo);
		}
		//创建特殊会员
		else if('CREATE_SPECIAL_MEMBER'==me.createState){
			me.memberEditPanel=Ext.create('MemberCustEditPanel',{'member':me.member,'viewStatus':'view','channelType':'workflow'});
			me.items = [{
				xtype:'basicpanel',
				height:400,
				width:755,
				items:[me.memberEditPanel]
			},{
				xtype:'basicpanel',
				width:755,
				height:100,
				items:[me.workFlowManagerResultInfo]
			},currentExaminerArea];
		}
		//联系人变更
		else if('CONTACT_VARY'==me.createState){
			currentExaminerArea .setWidth(790);
			me.contactVaryInfo = Ext.create('DealContactAffiliatedRelationView',{
						'nowRecord':me.params.contactVary.fromMemberIntegral,
						'width':810,
						'editStatus':'VIEW',
						'fileInfoList':me.params.fileInfoList,
						'targetRecord':me.params.contactVary.toMemberIntegral,
						'recordExchangeRuleManagerData':recordExchangeRuleManagerDataControl});
			me.items = [{
				xtype:'basicpanel',
				height:350,
				width:800,
				items:[me.contactVaryInfo]
			},{
				xtype:'basicpanel',
				width:790,
				height:100,
				items:[me.workFlowManagerResultInfo]
			},currentExaminerArea];
		}
		//变更会员归属部门
		else if('CHANGE_MEMBER_DEPT'==me.createState){
			currentExaminerArea .setWidth(520);
			var changeMemberDeptRecord = Ext.create('ChangeMemberDeptModel',me.params.changeMemberDept);
			me.changeDeptInfo = Ext.create('ChangeMemberDeptForm',{
				'viewStatus':'VIEW',
				'changeMemberDeptRecord':changeMemberDeptRecord});
			me.items = [{
				xtype:'basicpanel',
				height:130,
				width:520,
				items:[me.changeDeptInfo]
			},{
				xtype:'basicpanel',
//				flex:1,
				height:100,
				width:520,
				items:[me.workFlowManagerResultInfo]
			},currentExaminerArea];
		}
		//合同新增
		else if('CONTRACT_ADD'==me.createState){
			var contractEditView = Ext.create('ContractEditView',{
				'parentWin':me.parentWin,
				'contractData':contractManagerControl,
				'contractView':me.params.contractView,
				'showFbar':'WF',
				'status':'VIEW'});
			me.items = [{
				xtype:'basicpanel',
				height:530,
				items:[contractEditView]
			},{
				xtype:'basicpanel',
				flex:1,
				items:[me.workFlowManagerResultInfo]
			},currentExaminerArea];
		}
	   	me.fbar = me.getFbar();
		this.callParent();
	},
    getFbar:function(){
    	var me = this;
		return [
//			{
//			text:i18n('i18n.ViewContactAffiliatedRelationView.forApproval'),
//			xtype:'button',
//			scope:me,
//			handler:me.goToNextPanel
//		},
			{
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
	workFlowManagerData:null,
	initComponent:function(){
		var me = this;
		me.columns = me.getColumns();
		me.store = me.workFlowManagerData.getExamineRecordStore();
		this.callParent();
	},
	getColumns:function(){
		var me = this;
		return [new Ext.grid.RowNumberer(),{
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
	workFlowManagerData:null,
	operateGrid:null,//父界面操作数据grid
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
		me.workFlowTreatmentSuggestions = Ext.create('WorkFlowEditSearchForm',{'parent':me,'workFlowManagerData':me.workFlowManagerData,'record':record});
		me.workFlowManagerResultInfo = Ext.create('WorkFlowEditResultGrid',{'parent':me,'workFlowManagerData':me.workFlowManagerData});
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
	commitEvent:function(){
		var me = this;
		var form = me.workFlowTreatmentSuggestions;
		if(!Ext.isEmpty(form.getForm().findField('disposeUserId'))&&
			!Ext.isEmpty(form.getForm().findField('opinion'))){
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
					MessageUtil.showInfoMes(i18n('i18n.MyWorkFlowDealEditView.commitSuccessWar'));
					me.operateGrid.store.loadPage(1);
					me.parentWin.close();
				}
				//提交 失败
				var commitFail = function(result){
					MessageUtil.showErrorMes(result.message);
					me.parentWin.close();
				}
				if('CREATE_SPECIAL_MEMBER'==me.createState){
					me.workFlowManagerData.commitSepCreateMemberExamin(commitParams,commitSuccess,commitFail);			
				}
				if('UPDATE_CUSTOMERINFO'==me.createState){
					me.workFlowManagerData.commitModifyMemberExamin(commitParams,commitSuccess,commitFail);			
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
		labelAlign: 'right',
		labelWidth : 65,
		width : 200
	},
	record:null,
	parent:null,
	workFlowManagerData:null,
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
				inputValue:'1'
			}, {
				xtype : 'radio',
				boxLabel : i18n('i18n.MyWorkFlowDealEditView.notAgree'),
				name:'result',
				checked:true,
				inputValue:'0'
			}]
         },{
			xtype : 'textareafield',
			fieldLabel : i18n('i18n.MyWorkFlowDealEditView.m_applyReason'),
			colspan : 4,
			width : 825,
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
	workFlowManagerData:null,
	initComponent:function(){
		var me = this;
		me.columns = me.getColumns();
		me.store = me.workFlowManagerData.getApproveDataStore();
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


