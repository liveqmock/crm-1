/**
* 散客工作流处理页面界面
*/
var scatterCustBasicData = Ext.create('ScatterCustBasicData');
Ext.define('WorkFlowScatterCustWindow',{
	extend:'PopWindow',
	title:i18n('i18n.ScatterCustManagerView.DealScatterWorkFlow'),
	params:null,											//散客审批view信息 scatterCustomerView包括workflowId，todoFlag
	fbar:null,
	basicPanel:null,										//客户基本信息
	operateMenuType:'MY-DEAL',								//操作类型：要处理的工作流MY-DEAL，我申请的工作流MY-VIEW，我处理过的工作流MY-APPROVED
	workFlowTreatmentSuggestions:null,						//工作流审批Panel
	fbar:null,
	layout:{
        type:'vbox',
        align:'stretch'
    },
    recored:null,
	height:800,
	width:813,
	initComponent:function(){
		var me = this;
		var examineRecord = Ext.create('ExamineRecordModel');
		me.basicPanel = Ext.create('ScatterInfo4Workflow',{
			height:80,
			'parent':me});
		me.workFlowTreatmentSuggestions = Ext.create('WorkFlowEditSearchForm',{
			'parent':me,
			'textareaWidth':780,
			'record':examineRecord});
		var winHeight = [130];//动态修改弹出框的高度,需要传递为类所以定义成数组
		var assemElement = [];
		assemElement.push(me.basicPanel);//散客基本信息
		me.returnAccountList(me.params,assemElement,winHeight);//组装散客修改数据
		//我要处理的工作流
		if('MY-DEAL' == me.operateMenuType){
			assemElement.push({
						xtype:'basicpanel',
						height:120,
						items:[me.workFlowTreatmentSuggestions]
					});
			winHeight[0] =winHeight[0] + 125;
		}
		//我申请的工作流MY-VIEW，我处理过的工作流MY-APPROVED '' == me.operateMenuType
		else {
			assemElement.push(Ext.create('BasicPanel',{
				title:i18n('i18n.ScatterCustManagerView.DealMan'),
				height:80,
				items:{
					xtype : 'textareafield',
					value:Ext.isEmpty(me.params.currentExaminer)?'【】':me.params.currentExaminer,
					readOnly : true
				}
			}));
			winHeight[0] =winHeight[0] + 85;
		}
		me.items = assemElement;
		me.fbar = me.getFbar();
		me.setHeight(winHeight[0]);//动态修改弹出框的高度
		this.callParent();
		//加载散客基本信息
		me.basicPanel.getForm().loadRecord(Ext.create('ScatterCustModel',!Ext.isEmpty(me.params)?me.params.customer:null));
		//我要处理的工作流
		if('MY-DEAL' == me.operateMenuType){
			//显示审批人
			examineRecord.set('disposeUserId',top.User.empName);
			me.workFlowTreatmentSuggestions.loadRecord(examineRecord);	
		}
	},
	getFbar:function(){
		var me = this;
		return[{
		    	   text:i18n('i18n.ManualRewardIntegralEditView.b_save'),
		    	   xtype:'button',
		    	   scope:me,
		    	   hidden:('MY-DEAL' != me.operateMenuType)?true:false,
		    	   handler:me.commitEvent4Workflow
		       },{
		    	   text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
		    	   xtype:'button',
		    	   handler:function(){
		    		   me.close();
		    	   }
		       }];
	},
	//组织界面元素 账号信息
	returnAccountList:function(scatterCustomerView,assemElement,winHeight){
		var me = this;
		//存在新增账号
		if(!Ext.isEmpty(scatterCustomerView.addAccounts)){
			assemElement.push(Ext.create('SCAccountGrid4Workflow',{title:i18n('i18n.ScatterCustManagerView.newInfoAccount'),
				store:scatterCustBasicData.getAddAccountStore()
			}));
			scatterCustBasicData.getAddAccountStore().loadData(me.dealAccount4DateDictionary(scatterCustomerView.addAccounts));
			winHeight[0] = winHeight[0]+135;
		}
		//存在修改账号
		if (!Ext.isEmpty(scatterCustomerView.beforeAccounts)
				|| !Ext.isEmpty(scatterCustomerView.modifiedAccounts)){
			
			var beforeDate = scatterCustomerView.beforeAccounts;
			var modifiedDate = scatterCustomerView.modifiedAccounts;
			
			assemElement.push(Ext.create('SCAccountGrid4Workflow',{title:i18n('i18n.MyWorkFlowDealEditView.beforeUpdate '),
				store:scatterCustBasicData.getBeforeModifyAccountStore()
			}));
			assemElement.push(Ext.create('SCAccountGrid4Workflow',{title:i18n('i18n.MyWorkFlowDealEditView.afterUpdate '),
				store:scatterCustBasicData.getAfterModifyAccountStore()
			}));
			// 后台传过来的 修改后账号只有修改的账号某个字段信息 需要补齐账号信息并且把修改的字段信息标识为红色
			var updateDate = [];
			me.dealAccount4UpdateDate(beforeDate,modifiedDate,updateDate);
			scatterCustBasicData.getBeforeModifyAccountStore().loadData(me.dealAccount4DateDictionary(beforeDate));
			scatterCustBasicData.getAfterModifyAccountStore().loadData(updateDate);
			winHeight[0] = winHeight[0]+265;
		}
		//存在删除账号
		if(!Ext.isEmpty(scatterCustomerView.deleteAccounts)){
			assemElement.push(Ext.create('SCAccountGrid4Workflow',{title:i18n('i18n.MyWorkFlowDealEditView.deleteAccount'),
				store:scatterCustBasicData.getDeleteAccountStore()
			}));
			scatterCustBasicData.getDeleteAccountStore().loadData(me.dealAccount4DateDictionary(scatterCustomerView.deleteAccounts));
			winHeight[0] = winHeight[0]+135;
		}
	},
	commitEvent4Workflow:function(button){
		commitEvent4Workflow(button,'APPROVE_SATTERCUST_ACCOUNT',this);
	},
	//账号中 处理数据字典展示
	dealAccount4DateDictionary:function(accountDate){
		for(var i in accountDate){
			accountDate[i].accountUse = DpUtil.changeDictionaryCodeToDescrip(accountDate[i].accountUse,DataDictionary.ACCOUNT_USE);
			accountDate[i].isDefaultAccount = DpUtil.changeBooleanToDescrip(accountDate[i].isDefaultAccount);
		}
		return accountDate;
	},
	//转换成修改的model数组,字段 标志为红色
	setModelFieldValue:function(record,name,value){
		record.set(name+'','<span style="color:red">'+value+'</span>');
	},
	//处理账户信息中 修改后账户 数据
	dealAccount4UpdateDate:function(beforeDate,modifiedDate,updateDate){
		var me = this;
		for(var i in beforeDate){
			for(var j in modifiedDate){
				if(beforeDate[i].id == modifiedDate[j].id){
					var beforeDateData = Ext.create('ScatterCustAccountModel',beforeDate[i]);
					updateDate[j] = Ext.create('ScatterCustAccountModel',modifiedDate[j]);
					//修改的字段标志位红色，未修改的字段复制原有字段值
					Ext.Object.each(modifiedDate[j],function(key, value, myself){
						if(!Ext.isEmpty(value)){
							if(key == 'accountUse'){//账户用途 数据字典
								me.setModelFieldValue(updateDate[i],key,DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.ACCOUNT_USE));
							}else if(key =='isDefaultAccount'){//是否主账户 0或1转换成文字
								me.setModelFieldValue(updateDate[i],key,DpUtil.changeBooleanToDescrip(value));
							}else{
								me.setModelFieldValue(updateDate[i],key,value);
							}
						}else{
							if(key == 'accountUse'){//账户用途 数据字典
								updateDate[j].set('accountUse',DpUtil.changeDictionaryCodeToDescrip(beforeDateData.get('accountUse'),DataDictionary.ACCOUNT_USE));
							}else if(key =='isDefaultAccount'){//是否主账户 0或1转换成文字
								updateDate[j].set('isDefaultAccount',DpUtil.changeBooleanToDescrip(beforeDateData.get('isDefaultAccount')));
							}else{
								updateDate[j].set(key+'',beforeDateData.get(key+''));
							}
						}
					});
					updateDate[j].commit();
					//TODO　跳出本次循环，并且修改数组长度
				}
			}
		}
	}
});
Ext.define('ScatterInfo4Workflow',{
	extend:'BasicFormPanel',
	title:i18n('i18n.ContractEditView.t_custInfo'),
	defaults:{labelWidth:100,readOnly:true},
	layout:{type:'table',columns:3},
	defaultType:'dptextfield',
	items:[{
	   fieldLabel:i18n('i18n.ScatterUpgradeView.belongdeptName'),
	   name:'deptName'
   },{
	   fieldLabel:i18n('i18n.ScatterCustManagerView.scatterCustNumber'),
	   name:'scatterNum'
   },{
	   fieldLabel:i18n('i18n.ScatterCustManagerView.scatterCustName'),
	   name:'custName'
   },{
	   fieldLabel:i18n('i18n.PotentialCustManagerView.contactName'),
	   name:'linkManName'
   },{
	   fieldLabel:i18n('i18n.PotentialCustManagerView.contactPhone'),
	   name:'linkManMobile'
   },{
	   fieldLabel:i18n('i18n.PotentialCustManagerView.contactTel'),
	   name:'linkManPhone'
   }]
});

Ext.define('SCAccountGrid4Workflow',{
	extend:'PopupGridPanel',
	height:130,
	autoScroll :true,
	columns : [{xtype: 'rownumberer',text:i18n('i18n.PotentialCustManagerView.potentialxuhao'),width:40},
	         {text:i18n('i18n.MemberCustEditView.openBank'),dataIndex:'bank'},
	         {text:i18n('i18n.MemberCustEditView.openName'),dataIndex:'accountName'},
	         {text:i18n('i18n.MemberCustEditView.accountNo'),dataIndex:'bankAccount',width:150},
	         {text:i18n('i18n.MemberCustEditView.accountPurpose'),dataIndex:'accountUse'},
	         {text:i18n('i18n.MemberCustEditView.accountContactName'),dataIndex:'financeLinkmanName'},
	         {text:i18n('i18n.MemberCustEditView.contactPhone'),dataIndex:'linkmanMobile'},
	         {text:i18n('i18n.MemberCustEditView.contactTel'),dataIndex:'linkmanPhone'},
	         {text:i18n('i18n.MemberCustEditView.isAcquiesceAccount'),dataIndex:'isDefaultAccount'},
	         {text:i18n('i18n.MemberCustEditView.CRAccount'),dataIndex:'relation'},
	         {text:i18n('i18n.MemberCustEditView.accountProvince'),dataIndex:'bankProvinceName',hidden:true},
	         {text:i18n('i18n.MemberCustEditView.accountCity'),dataIndex:'bankCityName',hidden:true},
	         {text:i18n('i18n.MemberCustEditView.accountProvinceid'),dataIndex:'bankProvinceId',hidden:true},
	         {text:i18n('i18n.MemberCustEditView.accountCityid'),dataIndex:'bankCityId',hidden:true}]	
});