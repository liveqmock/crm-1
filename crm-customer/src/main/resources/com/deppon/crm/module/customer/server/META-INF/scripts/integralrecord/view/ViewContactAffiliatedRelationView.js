//变更联系人挂靠关系
var recordExchangeRuleManagerDataControl= (CONFIG.get("TEST"))? new ChangeContactAffiliatedRelationDataTest():new ChangeContactAffiliatedRelationData();
//var recordExchangeRuleManagerDataControl= new ChangeContactAffiliatedRelationData();
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
Ext.define('DealContactBelongView',{
	extend:'PopWindow',
	width:890,
	height:415,
	dealContactAffiliatedRelationView:null,
	workFlowSecondPanel:null,
	nowRecord:null,
	targetRecord:null,
	parentWin:null,
	fileInfoList:null,
	myWorkFlowData:null,
	workFlowId:null,//审批数据使用  工作流id
	historyWorkflowDate:null,
	recordExchangeRuleManagerData:null,
	layout:'card',
	initComponent:function(){
		var me = this;
		
		me.dealContactAffiliatedRelationView = new DealContactAffiliatedRelationView({
			'nowRecord':me.nowRecord,
			'parent':me,
			'fileInfoList':me.fileInfoList,
			'targetRecord':me.targetRecord,
			'recordExchangeRuleManagerData':recordExchangeRuleManagerDataControl});
		me.workflowSecondPanel = Ext.create('WorkFlowSecondPanel',{
			'workFlowId':me.workFlowId,
			'myWorkFlowData':me.myWorkFlowData,'parent':me,'parentWin':me.parentWin,
			'createState':'APPROVE_CONTACT_BELONG'});
		me.items=[me.dealContactAffiliatedRelationView,me.workflowSecondPanel];
		this.callParent();
	}
	
});

//变更联系人挂靠关系 主panel
Ext.define('DealContactAffiliatedRelationView',{
	extend:'BasicPanel',
	layout:{
        type:'vbox',
        align:'stretch'
    },
	nowAffiliatedCust:null,//现挂靠关系
	nowRecord:null,
	targetAffiliatedCust:null,//目标挂靠关系
	editStatus:'DEAL',//编辑状态  包括  VIEW（查看状态用户我的工作流进行查看） DEAL（处理状态，用于我要处理的工作流的 进行处理）
	targetRecord:null,
	fileInfo:null,//上传附件 panel
	fileInfoList:null,//附件数据
	recordExchangeRuleManagerData:null,//data 层
	initComponent:function(){
		var me = this;
		if(me.nowRecord == null){me.nowRecord = Ext.create('MemberIntegralModel')}else{me.nowRecord = Ext.create('MemberIntegralModel',me.nowRecord)}
		if(me.targetRecord == null){me.targetRecord = Ext.create('MemberIntegralModel')}else{me.targetRecord = Ext.create('MemberIntegralModel',me.targetRecord)}
		me.nowAffiliatedCust = Ext.create('ContactCustPanel',{
			'record':me.nowRecord,
			'recordExchangeRuleManagerData':me.recordExchangeRuleManagerData,
			'numType':'CONTACT'});
		me.targetAffiliatedCust = Ext.create('ContactCustPanel',{
			'record':me.targetRecord,
			'recordExchangeRuleManagerData':me.recordExchangeRuleManagerData,
			'numType':'CUSTOMER'});
		me.fileInfo = Ext.create('ContractDownAttachment',{
			'operateType':'DELAY',
			'fileInfoList':me.fileInfoList,
			'recordExchangeRuleManagerData':me.recordExchangeRuleManagerData});
		me.items = me.getItems();
		this.callParent();
		//如果是工作流查看功能  则没有 进行审批 功能（隐藏进行审批按钮）
		if('VIEW' == me.editStatus){
			Ext.getCmp('examineDealContactAffiliatedRelationId').hide();
		}
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'basicpanel',
   			height:235,
			layout:{
		        type:'hbox',
		        align:'stretch'
		    },
			defaultType:'titleformpanel',
		    items:[{
		    	items:[{
					xtype : 'basicfiledset',
					title : i18n('i18n.AffiliatedRel.nowAffiliatedRel'),
					items:[me.nowAffiliatedCust]	
		    	}]
		    },{
		    	xtype:'basicpanel',
		    	width:10
		    },{
		    	items:[{
			    	xtype : 'basicfiledset',
					title : i18n('i18n.AffiliatedRel.doApprove'),
					items:[me.targetAffiliatedCust]	
				}]
		    }]
		},{
			xtype : 'basicpanel',
			height : 100,
			items : [ me.fileInfo ]
		},{
			xtype:'fieldcontainer',
			layout:'column',
			items:[{
				xtype:'button',
				id:'examineDealContactAffiliatedRelationId',//增加id，便于通过不同编辑状态（查看、处理） 控制按钮是否显示
				text:i18n('i18n.ViewContactAffiliatedRelationView.forApproval'),
				width:100,
				scope:me,
				handler:function(){
					me.goToNextPanel();
				}
				}]
		}]
	},
    //进入审批页面
    goToNextPanel:function(){
    	var me = this;
    	var layout = me.parent.getLayout();
    	layout['next']();
    }
});

//变更联系人挂靠关系 会员和联系人信息
Ext.define('ContactCustPanel',{
	extend:'BasicPanel',
	recordExchangeRuleManagerData:null,
	numType:'CONTACT',//CONTACT 联系人，CUSTOMER 会员
	record:null,
	layout:{
        type:'vbox',
        align:'stretch'
    },
    height:230,
    width:365,
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
//		me.down('form').loadRecord(me.record);	
//		var memberInfor = Ext.create('MemberCustomerModel',me.record.get('member'));
//		memberInfor.set('custNature',DpUtil.changeDictionaryCodeToDescrip(memberInfor.get('custNature'),DataDictionary.CUSTOMER_NATURE));
//		memberInfor.set('degree',DpUtil.changeDictionaryCodeToDescrip(memberInfor.get('degree'),DataDictionary.MEMBER_GRADE));
//		memberInfor.set('custType',DpUtil.changeDictionaryCodeToDescrip(memberInfor.get('custType'),DataDictionary.CUSTOMER_TYPE));
//		memberInfor.set('tradeId',DpUtil.changeDictionaryCodeToDescrip(memberInfor.get('tradeId'),DataDictionary.TRADE));
//		me.down('form').loadRecord(memberInfor);
		me.loadContactAffiliatedCustData(me.record);
	},
	getItems:function(){
		var me = this;
		var store = Ext.create('ContactStore');
		if('CONTACT' == me.numType){
			store = me.recordExchangeRuleManagerData.getContactLinkStore();
		}else if('CUSTOMER' == me.numType){
			store = me.recordExchangeRuleManagerData.getContactCustStore();
		}
		return [{
			xtype:'fieldcontainer',
			layout:'column',
			items:[{
			xtype:'basicformpanel',
			layout:{
				type:'table',
				columns:2
			},
			defaultType:'dptextfield',
			defaults:{
				labelAlign: 'right',
				labelWidth : 65,
				width : 175,
				readOnly:true
			},
			items:[{
					fieldLabel:i18n('i18n.MemberCustEditView.custNo'),
					name : 'custNumber'
//					labelWidth : 55,
//					width : 183
				},{
					fieldLabel:i18n('i18n.PotentialCustManagerView.customerName'),
					name : 'custName'
				},{
					fieldLabel:i18n('i18n.ScatterCustManagerView.custAttribute'),
					xtype:'dpcombo',
					name : 'custNature',
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
//					labelWidth : 55,
//					width : 183,
					store:me.recordExchangeRuleManagerData.getCustomerNatureStore()
				},{
					fieldLabel:i18n('i18n.ScatterCustManagerView.taxId'),
					name : 'taxregNumber'
				},{
					fieldLabel:i18n('i18n.MemberCustEditView.custLevel'),
//					labelWidth : 55,
//					width : 183,
					xtype:'dpcombo',
					name : 'degree',
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					store:me.recordExchangeRuleManagerData.getMemberGradeStore()
				},{
					fieldLabel:i18n('i18n.MemberCustEditView.custType'),
//					labelWidth : 55,
//					width : 183,
					xtype:'dpcombo',
					name : 'custType',
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					store:me.recordExchangeRuleManagerData.getCustomerTypeStore()
				},{
					fieldLabel:i18n('i18n.MemberCustEditView.custIndustry'),
					xtype:'dpcombo',
					queryMode:'local',
					forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					name:'tradeId',
					blankText:i18n('i18n.ChangeContactAffiliatedRelationView.regex_tradeId'),
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
				},{
					fieldLabel:i18n('i18n.AffiliatedRel.custTotalInteg'),
					name : 'totalScore',
					hidden:true
				},{
					xtype:'hiddenfield',
					fieldLabel:i18n('i18n.ChangeContactAffiliatedRelationView.custId'),
					name : 'custId',
					hidden:true
				}]
		},{
			xtype : 'popupgridpanel',
			height:90,
			width:360,
			store:store,
			columns:[
//			new Ext.grid.RowNumberer(),
			         {
						header:i18n('i18n.Integral.contatct'),
						flex : 1,
						dataIndex:'name'
			         },{
				         header:i18n('i18n.MemberCustEditView.mobileNo'),
						 flex : 1,
			        	 dataIndex:'mobilePhone'
			         },{
				         header:i18n('i18n.MemberCustEditView.telephoneNo'),
						 flex : 1,
			        	 dataIndex:'telPhone'
			         },{
				         header:i18n('i18n.MemberCustEditView.isMainContact'),
						 flex : 1,
			        	 dataIndex:'isMainLinkMan',
						renderer : function(value) {
							if(value){
								return i18n('i18n.ChangeContactAffiliatedRelationView.yes');
							}else{
								return i18n('i18n.ChangeContactAffiliatedRelationView.no');
							} 
						}
			         }]
				}]
			}];
	},
	searchMember:function(){
		var me = this;
		var params = {};
//		params.custNum = me.record.data;
		params.contactNum = me.down('textfield').getValue();
		var seachFail = function(result){
			MessageUtil.showErrorMes(i18n('i18n.ViewContactAffiliatedRelationView.queryFailedWar')+result.message)
		}
		var seachSuccess = function(result){
			var memberIntegral = result.memberIntegral;
			me.loadContactAffiliatedCustData(Ext.create('MemberIntegralModel',memberIntegral));
		}
		me.recordExchangeRuleManagerData.searchMember(params,seachSuccess,seachFail);
	},
	loadContactAffiliatedCustData:function(memberIntegralRecord){
		var me = this;
		if(memberIntegralRecord != null){
			var member = memberIntegralRecord.get('member');
			var form = me.down('form');
			form.loadRecord(Ext.create('MemberCustomerModel',member));
			form.getForm().findField('totalScore').setValue(memberIntegralRecord.get('totalScore'));
			if(member != null){
				var contactList = member.contactList;
				me.down('grid').store.loadData(contactList);
			}
		}
	}
});

//变更联系人附件下载pannel
Ext.define('ContractDownAttachment',{
	extend:'BasicVboxPanel',
	recordExchangeRuleManagerData:null,//data层
	attachData:null,
	fileInfoList:null,
	operateType:'DELAY',//IMMEDIATE:附件直接持久化到数据库,DELAY：附件延迟持久化到数据库
	initComponent : function() {
		var me = this;
		me.items = me.getItems();
		this.callParent();
		if (me.fileInfoList!=null&&me.fileInfoList.length>0) {
			me.down('grid').store.loadData(me.fileInfoList);
		}
	},
	getItems : function() {
		var me = this;
		return [
		{
			xtype:'toppanel',
			items:[
			{
				xtype:'titlepanel',
				flex:1,
				items:[{
					xtype:'displayfield',
					value:i18n('i18n.ChangeContactAffiliatedRelationView.changeContact')
				}]
			}
			,{
				xtype:'btnpanel',
				items:[{
					xtype:'button',
					text:i18n('i18n.AffiliatedRel.downloadAttachment'),
					scope:me,
					handler:me.downLoadFile
				}]
			}]
		}
		,{
			xtype : 'popupgridpanel',
			flex:1,
			store:me.recordExchangeRuleManagerData.getFileInfoStore(),
			selModel:Ext.create('Ext.selection.CheckboxModel', {
				mode : 'SINGLE'
			}),
			columns:[new Ext.grid.RowNumberer(),
			         {
						header:i18n('i18n.ChangeContactAffiliatedRelationView.fileName'),
						flex : 1,
						dataIndex:'fileName'
			         },{
				         header:i18n('i18n.ChangeContactAffiliatedRelationView.savePath'),
			        	 dataIndex:'savePath',
			        	 flex : 1
//			        	 hidden:true
			         }]
		}];
	},
	//搜集附件修改的信息
	downLoadFile:function(){
		var me = this;		
		var records=me.down('grid').getSelectionModel().getSelection();
		if(records.length!=1){
			DpUtil.showMessage(i18n('i18n.AffiliatedRel.choseOneAttahctment'));
			return; 
		}
		window.open('../common/downLoad.action?fileName='+
		records[0].data.fileName+'&inputPath='+records[0].data.savePath,'download','height=100, width=400');
	}
});

