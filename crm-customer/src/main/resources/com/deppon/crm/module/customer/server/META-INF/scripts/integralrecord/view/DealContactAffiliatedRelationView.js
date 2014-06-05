//变更联系人挂靠关系
var recordExchangeRuleManagerDataControl = (CONFIG.get("TEST")) ? new ChangeContactAffiliatedRelationDataTest()
		: new ChangeContactAffiliatedRelationData();
//var recordExchangeRuleManagerDataControl = new ChangeContactAffiliatedRelationData();
//加载数据字典
Ext.onReady(function(){
	var params = [	// 客户行业
	      			'TRADE',
	      			// 客户属性
	      			'CUSTOMER_NATURE',
	      			// 客户类型
	      			'CUSTOMER_TYPE',
					// 目标级别i18n('i18n.ChangeContactAffiliatedRelationView.nowLevelCustLevel'),会员等级
	      			'MEMBER_GRADE'
	              ];
	initDataDictionary(params);
});
Ext
		.define(
				'DealContactBelongView',
				{
					extend : 'PopWindow',
					title:i18n('i18n.MyWorkFlowDealEditView.approvalWorkflow'),
					width : 795,
					height : 415,
					dealContactAffiliatedRelationView : null,
					workFlowSecondPanel : null,
					nowRecord : null,
					targetRecord : null,
					parentWin : null,
					operateGrid:null,//父界面 操作的数据列表grid
					fileInfoList : null,
					myWorkFlowData : null,
					workFlowId : null,// 审批数据使用 工作流id
					historyWorkflowDate : null,
					recordExchangeRuleManagerData : null,
					layout : 'card',
					initComponent : function() {
						var me = this;

						me.dealContactAffiliatedRelationView = new DealContactAffiliatedRelationView(
								{
									'nowRecord' : me.nowRecord,
									'parent' : me,
									'fileInfoList' : me.fileInfoList,
									'targetRecord' : me.targetRecord,
									'recordExchangeRuleManagerData' : recordExchangeRuleManagerDataControl
								});
						me.workflowSecondPanel = Ext.create(
								'WorkFlowSecondPanelDealContactBelong', {
									'workFlowId' : me.workFlowId,
									'myWorkFlowData' : me.myWorkFlowData,
									'parent' : me,
									'operateGrid':me.operateGrid,
									'parentWin' : me,
									'createState' : 'APPROVE_CONTACT_BELONG'
								});
						me.items = [ me.dealContactAffiliatedRelationView,
								me.workflowSecondPanel ];
						this.callParent();
					}

				});

// 变更联系人挂靠关系 主panel
Ext.define('DealContactAffiliatedRelationView', {
	extend : 'BasicPanel',
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	nowAffiliatedCust : null,// 现挂靠关系
	nowRecord : null,
	targetAffiliatedCust : null,// 目标挂靠关系
	targetRecord : null,
	fileInfo : null,// 上传附件 panel
	fileInfoList : null,// 附件数据
	recordExchangeRuleManagerData : null,// data 层
	initComponent : function() {
		var me = this;
		if (me.nowRecord == null) {
			me.nowRecord = Ext.create('MemberIntegralModel');
		}else{
			me.nowRecord = Ext.create('MemberIntegralModel',me.nowRecord);
		}
		if (me.targetRecord == null) {
			me.targetRecord = Ext.create('MemberIntegralModel');
		}else{
			me.targetRecord = Ext.create('MemberIntegralModel',me.targetRecord);
		}
		me.nowAffiliatedCust = Ext.create('ContactCustPanel', {
			'record' : me.nowRecord,
			'recordExchangeRuleManagerData' : me.recordExchangeRuleManagerData,
			'numType' : 'CONTACT'
		});
		me.targetAffiliatedCust = Ext.create('ContactCustPanel', {
			'record' : me.targetRecord,
			'recordExchangeRuleManagerData' : me.recordExchangeRuleManagerData,
			'numType' : 'CUSTOMER'
		});
		me.fileInfo = Ext.create('ContractDownAttachment', {
			'operateType' : 'DELAY',
			'fileInfoList' : me.fileInfoList,
			'recordExchangeRuleManagerData' : me.recordExchangeRuleManagerData
		});
		me.items = me.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [ {
			xtype : 'basicpanel',
			height : 235,
			layout : {
				type : 'hbox',
				align : 'stretch'
			},
			defaultType : 'titleformpanel',
			items : [ {
				items : [ {
					xtype : 'basicfiledset',
					title : i18n('i18n.AffiliatedRel.nowAffiliatedRel'),
					items : [ me.nowAffiliatedCust ]
				} ]
			}, {
				xtype : 'basicpanel',
				width : 10
			}, {
				items : [ {
					xtype : 'basicfiledset',
					title : i18n('i18n.AffiliatedRel.targetAffiliatedRel'),
					items : [ me.targetAffiliatedCust ]
				} ]
			} ]
		}, {
			xtype : 'basicpanel',
			height : 100,
			items : [ me.fileInfo ]
		}, {
			xtype : 'fieldcontainer',
			layout : 'column',
			items : [ {
				xtype : 'button',
				text : i18n('i18n.AffiliatedRel.doApprove'),
				width : 100,
				scope : me,
				handler : function() {
					me.goToNextPanel();
				}
			} ]
		} ]
	},
	// 进入审批页面
	goToNextPanel : function() {
		var me = this;
		var layout = me.parent.getLayout();
		layout['next']();
	}
});

// 变更联系人挂靠关系 会员和联系人信息
Ext.define('ContactCustPanel', {
	extend : 'BasicPanel',
	recordExchangeRuleManagerData : null,
	numType : 'CONTACT',// CONTACT 联系人，CUSTOMER 会员
	record : null,
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	height : 230,
	width : 360,
	initComponent : function() {
		var me = this;
		me.items = me.getItems();
		this.callParent();
//		me.down('form').loadRecord(me.record);
//		me.down('form').loadRecord(me.record.get('member'));
		me.loadContactAffiliatedCustData(me.record);
	},
	getItems : function() {
		var me = this;
		var store = Ext.create('ContactStore');
		if ('CONTACT' == me.numType) {
			store = me.recordExchangeRuleManagerData.getContactLinkStore();
		} else if ('CUSTOMER' == me.numType) {
			store = me.recordExchangeRuleManagerData.getContactCustStore();
		}
		return [ {
			xtype : 'fieldcontainer',
			layout : 'column',
			items : [ {
				xtype : 'basicformpanel',
				layout : {
					type : 'table',
					columns : 2
				},
				defaultType : 'dptextfield',
				defaults : {
					labelAlign : 'right',
					labelWidth : 65,
					width : 175,
					readOnly : true
				},
				items : [ {
					fieldLabel : i18n('i18n.MemberCustEditView.custNo'),
					name : 'custNumber'
				}, {
					fieldLabel :i18n('i18n.PotentialCustManagerView.customerName'),
					name : 'custName'
				}, {
					fieldLabel :i18n('i18n.ScatterCustManagerView.custAttribute'),
					xtype : 'dpcombo',
					name : 'custNature',
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					store:me.recordExchangeRuleManagerData.getCustomerNatureStore()
				}, {
					fieldLabel :i18n('i18n.ScatterCustManagerView.taxId'),
					name : 'taxregNumber'
				}, {
					fieldLabel :i18n('i18n.MemberCustEditView.custLevel'),
					xtype : 'dpcombo',
					name : 'degree',
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					store:me.recordExchangeRuleManagerData.getMemberGradeStore()
				}, {
					fieldLabel :i18n('i18n.MemberCustEditView.custType'),
					xtype : 'dpcombo',
					name : 'custType',
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					store:me.recordExchangeRuleManagerData.getCustomerTypeStore()
				}, {
					fieldLabel : i18n('i18n.MemberCustEditView.custIndustry'),
					xtype : 'dpcombo',
					queryMode : 'local',
					displayField : 'codeDesc',
					valueField : 'code',
					name : 'tradeId',
					blankText :i18n('i18n.MemberCustEditView.custIndustryAlert'),
					store:me.recordExchangeRuleManagerData.getTradeStore()
				}, {
					fieldLabel : i18n('i18n.secondTrade.secondTrade'),
					xtype : 'dpcombo',
					queryMode : 'local',
					displayField : 'codeDesc',
					valueField : 'code',
					name : 'secondTrade',
//					blankText :i18n('i18n.MemberCustEditView.custIndustryAlert'),
					store:me.recordExchangeRuleManagerData.getSecondTradeStore()
				}, {
					fieldLabel : i18n('i18n.AffiliatedRel.custTotalInteg'),
					hidden : true,
					name : 'totalScore'
				}, {
					xtype : 'hiddenfield',
					fieldLabel :i18n('i18n.ScatterUpgradeView.custId'),
					name : 'custId',
					hidden : true
				} ]
			}, {
				xtype : 'popupgridpanel',
				height : 90,
				width : 360,
				store : store,
				columns : [
				// new Ext.grid.RowNumberer(),
				{
					header : i18n('i18n.Integral.contatct'),
					flex : 1,
					dataIndex : 'name'
				}, {
					header :i18n('i18n.MemberCustEditView.mobileNo'),
					flex : 1,
					dataIndex : 'mobilePhone'
				}, {
					header :i18n('i18n.MemberCustEditView.telephoneNo'),
					flex : 1,
					dataIndex : 'telPhone'
				}, {
					header :i18n('i18n.MemberCustEditView.isMainContact'),
					flex : 1,
					dataIndex : 'isMainLinkMan',
					renderer : function(value) {
						if(value){
							return i18n('i18n.ChangeContactAffiliatedRelationView.yes');
						}else{
							return i18n('i18n.ChangeContactAffiliatedRelationView.no');
						} 
					}
				} ]
			} ]
		} ];
	},
	searchMember : function() {
		var me = this;
		var params = {};
		// params.custNum = me.record.data;
		params.contactNum = me.down('textfield').getValue();
		var seachFail = function(result) {
			MessageUtil.showErrorMes( i18n('i18n.PotentialCustManagerView.searchFailed') + result.message)
		}
		var seachSuccess = function(result) {
			var memberIntegral = result.memberIntegral;
			me.loadContactAffiliatedCustData(Ext.create('MemberIntegralModel',memberIntegral));
		}
		me.recordExchangeRuleManagerData.searchMember(params, seachSuccess,
				seachFail);
	},
	loadContactAffiliatedCustData : function(memberIntegralRecord) {
		var me = this;
		if (memberIntegralRecord != null) {
			var member = memberIntegralRecord.get('member');
			var form = me.down('form');
			form.loadRecord(Ext.create('MemberCustomerModel', member));
			form.getForm().findField('totalScore').setValue(
					memberIntegralRecord.get('totalScore'));
			if (member != null) {
				var contactList = member.contactList;
				me.down('grid').store.loadData(contactList);
			}
		}
	}
});

// 变更联系人附件下载pannel
Ext.define('ContractDownAttachment', {
	extend : 'BasicVboxPanel',
	recordExchangeRuleManagerData : null,// data层
	attachData : null,
	fileInfoList : null,
	operateType : 'DELAY',// IMMEDIATE:附件直接持久化到数据库,DELAY：附件延迟持久化到数据库
	initComponent : function() {
		var me = this;
		me.items = me.getItems();
		this.callParent();
		if (me.fileInfoList != null && me.fileInfoList.length > 0) {
			me.down('grid').store.loadData(me.fileInfoList);
		}
	},
	getItems : function() {
		var me = this;
		return [ {
			xtype : 'toppanel',
			items : [ {
				xtype : 'titlepanel',
				flex : 1,
				items : [ {
					xtype : 'displayfield',
					value : i18n('i18n.AffiliatedRel.affiliatedRelAttachment')
				} ]
			}, {
				xtype : 'btnpanel',
				items : [ {
					xtype : 'button',
					text : i18n('i18n.AffiliatedRel.downloadAttachment'),
					scope : me,
					handler : me.downLoadFile
				} ]
			} ]
		}, {
			xtype : 'popupgridpanel',
			flex : 1,
			store : me.recordExchangeRuleManagerData.getFileInfoStore(),
			selModel : Ext.create('Ext.selection.CheckboxModel', {
				mode : 'SINGLE'
			}),
			columns : [ new Ext.grid.RowNumberer(), {
				header : i18n('i18n.AffiliatedRel.attachmentName'),
				flex : 1,
				dataIndex : 'fileName'
			}, {
				header : i18n('i18n.AffiliatedRel.attachmentPath'),
				dataIndex : 'savePath',
				flex : 1,
				hidden:true
			} ]
		} ];
	},
	// 搜集附件修改的信息
	downLoadFile : function() {
		var me = this;
		var records = me.down('grid').getSelectionModel().getSelection();
		if (records.length != 1) {
			DpUtil.showMessage(i18n('i18n.AffiliatedRel.choseOneAttahctment'));
			return;
		}
		 window.open('../common/downLoad.action?fileName='+
		 records[0].data.fileName+'&inputPath='+records[0].data.savePath,'download','height=100, width=400');

	}
});

/**
 * 工作流审批第二界面
 */
Ext.define('WorkFlowSecondPanelDealContactBelong',{
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
		//处理工作流
		me.workFlowTreatmentSuggestions = Ext.create('WorkFlowEditSearchForm',{
			'parent':me,
			'textareaWidth':760,
			'myWorkFlowData':me.myWorkFlowData,
			'record':record});
		//历史审批记录
		me.workFlowManagerResultInfo = Ext.create('WorkFlowEditResultGrid',{
//			'title':i18n('i18n.MyWorkFlowDealEditView.m_historyApply'),
			height:120,
			'parent':me,
			'myWorkFlowData':me.myWorkFlowData});
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
				}
			}
	}
});