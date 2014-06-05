//变更联系人挂靠关系
var recordExchangeRuleManagerDataControl= (CONFIG.get("TEST"))? new ChangeContactAffiliatedRelationDataTest():new ChangeContactAffiliatedRelationData();
//var recordExchangeRuleManagerDataControl= new ChangeContactAffiliatedRelationData();
Ext.onReady(function() {
	var params = [	// 客户行业
	      			'TRADE',
	      			// 客户二级行业
	      			'SECOND_TRADE',
	      			// 客户属性
	      			'CUSTOMER_NATURE',
	      			// 客户类型
	      			'CUSTOMER_TYPE',
					// 目标级别i18n('i18n.ChangeContactAffiliatedRelationView.nowLevelCustLevel'),会员等级
	      			'MEMBER_GRADE',
	      			//客户性质
	      			'CUST_TYPE'
	              ];
	initDataDictionary(params);
	//初始化部门信息
	initDeptAndUserInfo("1");
	//显示界面
	Ext.create('Ext.container.Viewport', {
		layout : 'fit',
//		autoScroll:true,
		items : [ Ext.create('ChangeContactAffiliatedRelation', {
			'recordExchangeRuleManagerData' : recordExchangeRuleManagerDataControl
		}) ]
	});
});
//被移动的会员积分  全局变量
var FromMemberIntegral = {};
//目标的会员积分 全局变量
var ToMemberIntegral = {};
//变更联系人挂靠关系 主panel
Ext.define('ChangeContactAffiliatedRelation',{
	extend:'BasicPanel',
	layout:{
        type:'vbox',
        align:'stretch'
    },
	nowAffiliatedCust:null,//现挂靠关系
	nowRecord:null,
	targetAffiliatedCust:null,//目标挂靠关系
	targetRecord:null,
	fileInfo:null,//上传附件 panel
	fileInfoList:null,//附件数据
	recordExchangeRuleManagerData:null,//data 层
	initComponent:function(){
		var me = this;
		if(me.nowRecord == null){me.nowRecord = Ext.create('MemberIntegralModel')}else{me.nowRecord = Ext.create('MemberIntegralModel',me.nowRecord)}
		if(me.targetRecord == null){me.targetRecord = Ext.create('MemberIntegralModel')}else{me.targetRecord = Ext.create('MemberIntegralModel',me.targetRecord)}
		me.nowAffiliatedCust = Ext.create('ContactAffiliatedCustPanel',{
			'title' : i18n('i18n.ChangeContactAffiliatedRelationView.nowAffiliatedCust'),
			'record':me.nowRecord,
			'recordExchangeRuleManagerData':me.recordExchangeRuleManagerData,
			'numType':'CONTACT'});
		me.targetAffiliatedCust = Ext.create('ContactAffiliatedCustPanel',{
			'title':i18n('i18n.ChangeContactAffiliatedRelationView.targetAffiliatedCust'),
			'record':me.targetRecord,
			'recordExchangeRuleManagerData':me.recordExchangeRuleManagerData,
			'numType':'CUSTOMER'});
		me.fileInfo = Ext.create('ContractAttachment',{
			'width':800,
			'operateType':'DELAY',
			'recordExchangeRuleManagerData':me.recordExchangeRuleManagerData,
			'attachData':me.getAttachData()});
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'basicpanel',
   			height:300,
			layout:{
		        type:'hbox',
		        align:'stretch'
		    },
			defaultType:'titleformpanel',
		    items:[me.nowAffiliatedCust
		    ,{
		    	xtype:'basicpanel',
		    	width:10
		    },me.targetAffiliatedCust
		    ]
		},{height:10,border:false},
		{
			xtype : 'basicpanel',
			layout:null,
			items : [ me.fileInfo ]
		},{
			xtype:'fieldcontainer',
			layout:'column',
			items:[{xtype:'displayfield',value:'<span style="color:red;">'+i18n('i18n.ChangeContactAffiliatedRelationView.fileSize')+'</span>',width:300}
//			,{
//				xtype:'button',
//				text:i18n('i18n.ChangeContactAffiliatedRelationView.merge'),
//				width:80,
//				margin:'0 0 0 430',
//				scope:me,
//				handler:me.mergeEvent
//				}
				]
		},{
			xtype:'fieldcontainer',
			layout:'column',
			items:[{
				xtype:'button',
				text:i18n('i18n.ChangeContactAffiliatedRelationView.merge'),
				id:'merge_contact_id',
				disabled:false,
				width:80,
				margin:'0 0 0 708',
				scope:me,
				handler:me.mergeEvent
				}]
		}]
	},
	//获得附件Data
	getAttachData:function(){
		var  me = this;
		var attachData = [];
		if(me.contractView != null){
			attachData = me.fileInfoList;
		}
		return attachData;
	},
	//合并事件
	mergeEvent:function(button){
		var me = this;
		var params = {};
		//搜集合并数据
		params = me.collectMergeDate(params);
		//判断是否变更的是主联系人
		if(me.checkIsMainContact(params.contactVary)){
			MessageUtil.showMessage(i18n('i18n.ChangeContactAffiliatedRelationView.m_isMainContact'));
			return;
		}
		//合并联系人必须先上传附件
		if(Ext.isEmpty(FromMemberIntegral) || Ext.isEmpty(ToMemberIntegral)){
			MessageUtil.showMessage(i18n('i18n.ChangeContactAffiliatedRelationView.reSearch'));
			return;
		}
		//合并联系人必须先上传附件
		if(Ext.isEmpty(params.fileInfoList)){
			MessageUtil.showMessage(i18n('i18n.ChangeContactAffiliatedRelationView.m_uploadFile'));
			return;
		}
		//合并按钮不可用
		button.setDisabled(true);
		var mergeFail = function(result){
			//合并按钮可用
			button.setDisabled(false);
			MessageUtil.showErrorMes(result.message);
		}
		var mergeSuccess = function(result){
			MessageUtil.showInfoMes(i18n('i18n.ChangeContactAffiliatedRelationView.operateSuccess')+result.workFlowNum);
			//清空全局变量
			FromMemberIntegral = {};
			ToMemberIntegral = {};
		}
		MessageUtil.showQuestionMes(i18n('i18n.contractEditView.createWorkflowNotice'),function(e){
			if('yes' == e){
				me.recordExchangeRuleManagerData.mergeContact(params,mergeSuccess,mergeFail);
			}else{
				button.setDisabled(false);
			}
		},function(){
			button.setDisabled(false);
		});
	},
	//搜集合并数据
	collectMergeDate:function(params){
		var me = this;
		//附件 alterAddLinkmanList
		var fileInfoList = new Array();
		me.fileInfo.collectAlterAttachData(fileInfoList);
		//联系人变更实体
		var contactVary = {};
		contactVary.contactNumber = me.nowAffiliatedCust.down('textfield').getValue();//变更联系人编码
		contactVary.custNum = me.targetAffiliatedCust.down('textfield').getValue();//被挂的会员编码
		contactVary.fromMemberIntegral = FromMemberIntegral;//被移动的会员积分
		contactVary.toMemberIntegral = ToMemberIntegral;//目标的会员积分
		
		params.fileInfoList = fileInfoList;
		params.contactVary = contactVary;
		return params;
	},
	//判断变更的联系人是不是主联系人，如果是则返回true，否则返回false
	checkIsMainContact:function(contactVary){
		var me = this;
		var mainContact = contactVary.fromMemberIntegral.member.mainContact;
		if(contactVary.contactNumber == mainContact.number){
			return true;
		}else{
			return false;
		}
	}
});
//变更联系人挂靠关系 会员和联系人信息
Ext.define('ContactAffiliatedCustPanel',{
	extend:'Ext.panel.Panel',
	cls:'normalpanel',
	bodyStyle:'padding:5px',
	recordExchangeRuleManagerData:null,
	numType:'CONTACT',//CONTACT 联系人，CUSTOMER 会员
	record:null,
	layout:{
        type:'vbox',
        align:'stretch'
    },
    height:250,
    width:395,
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
//		me.down('form').loadRecord(me.record);	
		me.loadContactAffiliatedCustData(me.record);
	},
	getItems:function(){
		var me = this;
		var numTextfield = {};
		var store = Ext.create('ContactStore');
		if('CONTACT' == me.numType){
			numTextfield = {xtype:'dptextfield',
							fieldLabel:i18n('i18n.Integral.contactNumber'),
							labelWidth:75,
							maxLength:40,
							maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',40),
							name:'contactNum',
							width:200
						   };
			store = me.recordExchangeRuleManagerData.getContactLinkStore();
		}else if('CUSTOMER' == me.numType){
			numTextfield = {xtype:'dptextfield',
							fieldLabel:i18n('i18n.MemberCustEditView.custNo'),
							labelWidth:75,
							maxLength:40,
							maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',40),
							name:'custNum',
							width:200
						   };
			store = me.recordExchangeRuleManagerData.getContactCustStore();
		}
		return [{
			xtype:'notitlebgroundformpanel',
			layout:'column',
			items:[numTextfield,{
						xtype:'button',
						text:i18n('i18n.PotentialCustManagerView.search'),
						width:60,
						margin : '0 0 0 10',
						scope:me,
						handler:me.searchMember
				   }]
		},{
			xtype:'basicformpanel',
			margin : '5 0 5 0',
			layout:{
				type:'table',
				columns:2
			},
			defaultType:'dptextfield',
			defaults:{
				labelAlign: 'right',
				labelWidth : 75,
				width : 195,
				readOnly:true
			},
			items:[{
					fieldLabel:i18n('i18n.MemberCustEditView.custNo'),
					name : 'custNumber',
					labelWidth : 55,
					width : 183
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
					labelWidth : 55,
					width : 183,
					store:me.recordExchangeRuleManagerData.getCustomerNatureStore()
				},{
//					根据后台传过来的数据，判断是香港还是大陆地区
					fieldLabel:User.deptCityLocation == "1"?'商业登记号':i18n('i18n.ScatterCustManagerView.taxId'),
					name : 'taxregNumber',
					regex:User.deptCityLocation == "1"?DpUtil.linkWayLimit('HKTAX'):DpUtil.linkWayLimit('CNTAX'),
					emptyText:User.deptCityLocation == "1"?'输入时不要输入“-”即12345678000':'请输入合法的税务登记号',
					regexText:User.deptCityLocation == "1"?'商业登记号为：12345678-000，输入时不要输入“-”即12345678000':'请输入合法的税务登记号'
				},{
					fieldLabel:i18n('i18n.MemberCustEditView.custLevel'),
					labelWidth : 55,
					width : 183,
					xtype:'dpcombo',
					name : 'degree',
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					store:me.recordExchangeRuleManagerData.getMemberGradeStore()
				},{
					fieldLabel:i18n('i18n.MemberCustEditView.custType'),
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
					labelWidth : 55,
					width : 183,
					blankText:i18n('i18n.ChangeContactAffiliatedRelationView.regex_tradeId'),
					store:me.recordExchangeRuleManagerData.getTradeStore()
				},{
					fieldLabel:i18n('i18n.secondTrade.secondTrade'),
					xtype:'dpcombo',
					queryMode:'local',
					forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					name:'secondTrade',
					store:me.recordExchangeRuleManagerData.getSecondTradeStore()
				},{
					fieldLabel:i18n('i18n.MemberCustEditView.custGroup'),
					name:'custGroup',
					xtype:'dpcombo',
					forceSelection:true,
					labelWidth : 55,
					width : 183,
					readOnly:true,
					allowBlank:false,
					blankText:i18n('i18n.MemberCustEditView.m_noCustGroup'),
					store:me.recordExchangeRuleManagerData.getCustGroupStore(),
					displayField:'codeDesc',
					valueField:'code',
					queryMode:'local',
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
			height:100,
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
		}];
	},
	searchMember:function(){
		var me = this;
		//避免重复提交
		if(!me.down('textfield').isValid()){
			MessageUtil.showMessage(i18n('i18n.ChangeContactAffiliatedRelationView.m_fillRightCondition'));
			return;
		}
		var params = {};
		//CONTACT 联系人，CUSTOMER 会员
		if('CONTACT' == me.numType){
			params.contactNum = me.down('textfield').getValue();
		}else if('CUSTOMER' == me.numType){
			params.custNum = me.down('textfield').getValue();
		}
//		params.custNum = me.record.data;
		var seachFail = function(result){
			MessageUtil.showErrorMes(result.message);
			me.unLoadContactAffiliatedCustData();
		}
		var seachSuccess = function(result){
			var memberIntegral = result.memberIntegral;
			//交予后台校验是否已经合并过
			if('CONTACT' == me.numType){
				if(Ext.isEmpty(memberIntegral)){me.loadContactAffiliatedCustData(Ext.create('MemberIntegralModel',memberIntegral));return;}
				if(Ext.isEmpty(memberIntegral.member)){
					MessageUtil.showMessage(i18n('i18n.ChangeContactAffiliatedRelationView.m_reSearch'));
					return;
				}
				if(top.User.deptId != memberIntegral.member.deptId){
					MessageUtil.showMessage(i18n('i18n.ChangeContactAffiliatedRelationView.m_notMainDept'));
					return;
				}else{
					Ext.getCmp('merge_contact_id').setDisabled(false);
				}
				FromMemberIntegral = memberIntegral;
				
			}else if('CUSTOMER' == me.numType){
				ToMemberIntegral = memberIntegral;
			}
			me.loadContactAffiliatedCustData(Ext.create('MemberIntegralModel',memberIntegral));
		}
		me.recordExchangeRuleManagerData.searchMember(params,seachSuccess,seachFail);
	},
	//加载所有数据
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
			}else{
				me.down('grid').store.loadData([]);
			}
		}
	},
	//清空所有数据
	unLoadContactAffiliatedCustData:function(){
		var me = this;
		var form = me.down('form');
		form.getForm().reset();
		me.down('grid').store.removeAll();
		//维护全局变量值
		if('CONTACT' == me.numType){
			FromMemberIntegral = {};
		}else if('CUSTOMER' == me.numType){
			ToMemberIntegral = {};
		}
	}
});
//变更联系人挂靠关系 附件pannel
Ext.define('ContractAttachment',{
	extend:'Ext.panel.Panel',
	border:false,
	recordExchangeRuleManagerData:null,//data层
	attachData:null,
	operateType:'DELAY',//IMMEDIATE:附件直接持久化到数据库,DELAY：附件延迟持久化到数据库
	initComponent : function() {
		var me = this;
		me.items = me.getItems();
		this.callParent();
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
					id:'fileUpLoadButton',
					text:i18n('i18n.ChangeContactAffiliatedRelationView.uploadFile'),
					scope:me,
					handler:me.showUploadAttachWin
				},{
					xtype:'button',
					text:'删除',
					scope:me,
					handler:function(){
						var grid = me.down('grid');
						var store = grid.store;
						selection = grid.getSelectionModel().getSelection();
						for(var i = 0;i<selection.length;i++){
							if(!Ext.isEmpty(selection[i].get('id'))){
								MessageUtil.showMessage(i18n('i18n.contractEditView.cannotDel',selection[i].get('fileName')));
								return;
							}
							store.remove(selection[i]);
						}
					}
				}]
			}]
		}
		,{
			xtype : 'popupgridpanel',
			flex:1,
			store:me.recordExchangeRuleManagerData.getFileInfoStore(),
			columns:[new Ext.grid.RowNumberer(),
			         {
						header:i18n('i18n.ChangeContactAffiliatedRelationView.fileName'),
						flex : 1,
						dataIndex:'fileName'
			         },{
				         header:i18n('i18n.ChangeContactAffiliatedRelationView.savePath'),
			        	 dataIndex:'savePath',
			        	 hidden:true
			         }]
		}];
	},
	//显示上传附件窗口
	showUploadAttachWin:function(){
		var me = this;
		Ext.create('UploadAttachmentWin',{'operateType':me.operateType,'parent':me,'contractData':me.contractData}).show();
	},
	//新增附件 调用
	addAttachData:function(attachData){
		var me = this;
		me.down('grid').store.add(attachData);
	},
	//加载附件Data
	loadAttachData:function(attachData){
		var me = this;
		me.down('grid').store.loadData(attachData);
	},
	//搜集附件修改的信息
	collectAlterAttachData:function(alterAddAttachList){
		var me = this;		
		//搜集附件 新增的数据
		var addAttachRecords = me.recordExchangeRuleManagerData.getFileInfoStore().getNewRecords();
		for(var i = 0; i < addAttachRecords.length; i++){
			alterAddAttachList.push(addAttachRecords[i].data);
		}
	}
});
//上传附件弹出框
Ext.define('UploadAttachmentWin',{
	extend:'PopWindow',
	title:i18n('i18n.PotentialCustManagerView.messageTip'),
	layout:{
		type:'vbox',
		align:'stretch'
	},
	operateType:null,
	recordExchangeRuleManagerData:null,
	width:395,
	height:150,
	parent:null,
	attachmentForm:null,  //附件form
	initComponent:function(){
		var me = this;
		me.items = [
		{
			xtype:'titlepanel',
			items:[{xtype: 'displayfield',
					value: i18n('i18n.ChangeContactAffiliatedRelationView.title_uploadFile'),
					width:100}]
		},
		{
			xtype:'basicformpanel',
			flex:1,
			layout:{
				type : 'hbox'
			},
			defaults : {
				margins : '0 5 0 0'
			},
			items:[{
				xtype:'filefield',
				name:'file',
				fieldLabel:i18n('i18n.ChangeContactAffiliatedRelationView.title_uploadFile'),
				labelWidth:60, allowBlank: false,
				buttonText:i18n('i18n.ChangeContactAffiliatedRelationView.view'),
				flex:3
			},
//				{
//				text:i18n('i18n.ChangeContactAffiliatedRelationView.memberId'),
//				name:'sourceId',
//				xtype:'hiddenfield'
//			},
				{
				text:i18n('i18n.ChangeContactAffiliatedRelationView.sourceType'),
				name:'type',
				xtype:'hiddenfield',
				value:'CHANGECONTACT'//变更联系人
			}]
		}];
		me.fbar = me.getFbar();
		this.callParent();
	},
	getFbar:function(){
		var me = this;
		return [{
			text:i18n('i18n.ChangeContactAffiliatedRelationView.button_upload'),
			xtype:'button',
			scope:me,
			handler:me.uploadFile
		},{
			text:i18n('i18n.PotentialCustEditView.cancel'),
			xtype:'button',
			handler:function(){
				me.close();
			}
		}];
	},
	//文件上传
	uploadFile:function(){
		var me = this;
		var form = me.down('form');
//		var uploadSuccess = function(result){
//			MessageUtil.showMessage(result.fileInfoList[0].fileName+i18n('i18n.ContractEditView.uploadSuccessWar'));
//		}
//		var uploadFail = function(result){
//			MessageUtil.showMessage(result.message);
//		}
//		me.contractData.uploadFile(form,uploadSuccess,uploadFail);
		if(!form.getForm().isValid()){MessageUtil.showMessage(i18n('i18n.ContractEditView.m_uploadFile'));return;}
		form.submit({
                    url: '../common/fileUpload.action',
                    waitMsg: i18n('i18n.ChangeContactAffiliatedRelationView.uploading'),
                    success: function(form, response) {
                    	var result = response.result;
                    	if(result.success){
                    		var fileInfo = result.fileInfoList[0];
//                        	var contractNoun = me.contractData.changeFileInfo2ContractNoun(fileInfo,new FileInfoModel().data);
                        	me.parent.addAttachData(fileInfo);
                        	MessageUtil.showInfoMes( i18n('i18n.ChangeContactAffiliatedRelationView.file')+ fileInfo.fileName + i18n('i18n.ChangeContactAffiliatedRelationView.uploadSeccess'));
                        	me.close();
						}else{   
                       		MessageUtil.showErrorMes( result.message);
						}
                    },
	                 failure:function(form, response){
	                 	var result = response.result;
	                 	if(!result.success){
	                       	MessageUtil.showErrorMes(result.message);
						}
	                 }
                });
	}
});

