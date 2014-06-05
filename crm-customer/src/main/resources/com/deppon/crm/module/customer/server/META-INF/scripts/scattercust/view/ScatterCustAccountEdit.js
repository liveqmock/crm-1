var scatterAccountControl = (CONFIG.get('TEST'))? Ext.create('MemberCustDataTest'):Ext.create('MemberCustData');

/**
*账号编辑界面
*/
//Ext.define('MemberAccountEditWin',{
Ext.define('ScatterCustAccountEditWin',{
	extend:'PopWindow',
	layout:'fit',
	items:null,
	fbar:null,
	title:i18n('i18n.ScatterCustManagerView.custNumberAdd'),
	width:700,
	height:230,
	memberData:scatterAccountControl,
	sourcesChannel:'MEMBER',					//来源渠道  "SCATTERCUST" "MEMBER"默认为"MEMBER"
	accountRecord:null,    //AccountModel类型的record
	viewStatus:null,       //new表示新增，update表示修改，view表示查看
	listeners : {
		//清除上次残留数据一般在beforeshow和beforehide事件里面
		beforehide : function(window) {//在关闭window窗口前先将已经存在window里面的form的信息清空掉
			window.memberData.getBankInfoStore().removeAll();
		}
	},
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		me.fbar = me.getFbar();
		this.callParent();
		me.memberData.getBankNameStore().load();
		//加载数据
		if(me.accountRecord != null){
			//支行名称特殊处理 先把查询结果的 银行和支行名称加入到 对应combo的store中
			var bankModel = Ext.create('BankSearchConditionModel');
			bankModel.set('accountBank',me.accountRecord.get('bank'));//银行名称
			bankModel.set('accountBankId',me.accountRecord.get('bankId'));
			bankModel.set('accountBranch',me.accountRecord.get('subBankname'));//支行名称
			bankModel.set('accountBranchId',me.accountRecord.get('subBankId'));
			me.memberData.getBankComboxStore().add(bankModel);
			
			if(me.viewStatus == 'new'){me.down('form').getForm().reset();}
			//加载账号信息
			me.down('form').loadRecord(me.accountRecord);
			//查看状态则锁定所有组件
			if(me.viewStatus == 'view'){
				me.lockAllComponents();
			}
		}
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'noborderformpanel',
			layout:{
				type:'table',
				columns:3
			},
			defaultType:'dptextfield',
			defaults:{
				labelWidth:70,
				width:210,
				enableKeyEvents:true,
				listeners:{
					scope:me,
					keypress:me.enterKeyEvent
				},
				allowBlank:false
		    },
			items:[{
					xtype:'queryCombox',
					fieldLabel:i18n('i18n.MemberCustEditView.branchBankName'),
					name:'subBankId',
					blankText:i18n('i18n.MemberCustEditView.branchBankNameAlert'),
					subBankWindow:null,   //弹出的查询窗口
					store:me.memberData.getBankComboxStore(),
					displayField:'accountBranch',
					valueField:'accountBranchId',
					queryMode:'local',
					forceSelection:true,
					queryDelay:2000,
					listeners:{
						'expand':function(subBankCombox){
							if(subBankCombox.subBankWindow == null){
								subBankCombox.subBankWindow = Ext.create('BankSearchUI',{'memberData':me.memberData,'parent':subBankCombox});
							}else{
								 //清空查询界面
								 subBankCombox.subBankWindow.searchConditionPanel.getForm().reset();
								 me.memberData.getBankInfoStore().removeAll();
							}
							subBankCombox.subBankWindow.show();
							var position  = subBankCombox.getPosition();
							position[1] = position[1]+22;
							subBankCombox.subBankWindow.setPosition(position);
						},
						'change':function(subBankCombox){
							if(DpUtil.isEmpty(subBankCombox.getValue())){
								subBankCombox.setValue(null);
								//清空开户省市、以及银行名称
								var basicForm = me.down('form').getForm();
								basicForm.findField('bankProvinceId').setValue(null);
								basicForm.findField('bankCityId').setValue(null);
								basicForm.findField('bankId').setValue(null);
							}
						}
					},
					//支行信息查询页面查询结果赋值
					setValueComeback:function(backRecord){
//						me.memberData.getBankComboxStore().removeAll();
						me.memberData.getBankComboxStore().add(backRecord);

						var basicForm = this.up('form').getForm();
						//设置支行名称
						this.setValue(backRecord.get('accountBranchId'));
						basicForm.findField('subBankname').setValue(backRecord.get('accountBranch'));
						
						//设置银行名称
						basicForm.findField('bankId').setValue(backRecord.get('accountBankId'));
						basicForm.findField('bank').setValue(backRecord.get('accountBank'));
						
						//设置省份、城市
						basicForm.findField('bankProvinceName').setValue(backRecord.get('province'));
						basicForm.findField('bankCityName').setValue(backRecord.get('city'));
						basicForm.findField('bankProvinceId').setValue(backRecord.get('provinceId'));
						basicForm.findField('bankCityId').setValue(backRecord.get('cityId'));
					}
			   },
			   {
				xtype:'fieldcontainer',
				layout:{
					type:'hbox'
				},
				defaults:{
					xtype:'dptextfield',
					hideLabel:true,
					allowBlank:false,
					readOnly:true
				},
				items:[{xtype:'displayfield',value:i18n('i18n.MemberCustEditView.accountProvinceCityWar'),width:75},
						{name:'bankProvinceName',
						blankText:i18n('i18n.MemberCustEditView.openProvinceAlert'),
						flex:1},
				       {name:'bankCityName',
						blankText:i18n('i18n.MemberCustEditView.openCityAlert'),
						flex:1
						}]
			},{
				xtype:'dpcombobox',
				fieldLabel:i18n('i18n.MemberCustEditView.bankName'),
				name:'bankId',
				blankText:i18n('i18n.MemberCustEditView.pleaseInputBank'),
				hideTrigger:true,
				store:me.memberData.getBankComboxStore(),
				displayField:'accountBank',
				valueField:'accountBankId',
				queryMode:'local',
				readOnly:true
			},{
				fieldLabel:i18n('i18n.MemberCustEditView.whetherTheDefaultAccount'),
				name:'isDefaultAccount',
				blankText:i18n('i18n.MemberCustEditView.isAcquiesceAccountAlert'),
				xtype:'dpcombobox',
				queryMode:'local',
	            forceSelection:true,
				displayField:'name',
				valueField:'value',
//				store:me.memberData.getBooleanStore()
				store:scatterCustControl.getBooleanStore()
			},{
				fieldLabel:i18n('i18n.MemberCustEditView.accountNo'),
				name:'bankAccount',
				regex : /^\d{0,}$/,
				maxLength:80,
				blankText:i18n('i18n.MemberCustEditView.accountNoAlert')
			},{
				fieldLabel:i18n('i18n.MemberCustEditView.nameOfAccountHolder'),
				name:'accountName',
				maxLength:80,
				maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80),
				blankText:i18n('i18n.MemberCustEditView.openNameAlert')
			},{
				fieldLabel:i18n('i18n.MemberCustEditView.CRAccount'),
				name:'relation',
				maxLength:50,
				maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',50),
				blankText:i18n('i18n.MemberCustEditView.CRAccountAlert')
			},{
				fieldLabel:i18n('i18n.MemberCustEditView.accountNature'),
				name:'accountNature',
				blankText:i18n('i18n.MemberCustEditView.accountNatureAlert'),
				xtype:'dpcombobox',
				queryMode:'local',
	            forceSelection:true,
				displayField:'codeDesc',
				valueField:'code',
				store:scatterCustControl.getAccountNatureSC()
			},{
				fieldLabel:i18n('i18n.MemberCustEditView.accountPurpose'),
				name:'accountUse',
				xtype:'dpcombobox',
				queryMode:'local',
	            forceSelection:true,
	            displayField:'codeDesc',
				valueField:'code',
				store:me.memberData.getAccountUseStore(),
				blankText:i18n('i18n.MemberCustEditView.accountUseAlert')
			},{
				fieldLabel:i18n('i18n.MemberCustEditView.financeContact'),
				name:'financeLinkmanName',
				maxLength:80,
				blankText:i18n('i18n.MemberCustEditView.financeContactAlert')
			},{
				fieldLabel:i18n('i18n.MemberCustEditView.contactPhone'),
				name:'linkmanMobile',
				blankText:i18n('i18n.MemberCustEditView.contactPhoneAlert'),
				readOnly:('SCATTERCUST' == me.sourcesChannel)?false:true,
		   		regex : DpUtil.linkWayLimit('M'),
				allowBlank:true,
				listeners:{
					change:function(field,newValue,oldValue){
						// 增加失焦事件 散客名称自动填充到联系人名称上
						if('view' != me.viewStatus && !Ext.isEmpty(newValue)){
							DpUtil.autoChangeMobileLength(field,newValue);
						}
					}
				}
			},{
				fieldLabel:i18n('i18n.MemberCustEditView.contactTel'),
				name:'linkmanPhone',
				blankText:i18n('i18n.MemberCustEditView.contactTelAlert'),
				readOnly:('SCATTERCUST' == me.sourcesChannel)?false:true,
		   		regex : DpUtil.linkWayLimit('T'),
				allowBlank:true
			},{
				name:'subBankname',
				hidden:true              //设置隐藏域，保存支行名称
			},{
				name:'bank',
				hidden:true        //设置隐藏域，保存开户行名称
			},{
				name:'bankProvinceId',
				hidden:true               //设置隐藏域，保存bankProvinceId 银行开户省
			},{
				name:'bankCityId',
				hidden:true               //设置隐藏域，保存bankProvinceId 银行开户省name
			},{
				name:'id',
				allowBlank:true,		//新增 会员账号时 没有财务联系人id
				hidden:true               //设置隐藏域，账户id
			}]
		}];
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'displayfield',
			margin:'0 90 0 0',
			value:i18n('i18n.MemberCustEditView.accountHelpDoc')
		},{
			xtype:'button',
			text:i18n('i18n.MemberCustEditView.ensure'),
			disabled : ('view' == me.viewStatus) ? true : false,
			scope:me,
			handler:me.commitMemberAccount
		},{
			xtype:'button',
			text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
			handler:function(){
				me.close();
			}
		}];
	},
	//Enter键按下时焦点自动移到下个控件
	enterKeyEvent:function(field,event){
		var me = this;
		if(event.getKey() == Ext.EventObject.ENTER){
			if(field.next() != null){
				field.next().focus();
			}else{
				me.down('button').focus();
			}
		}
	},
	//提交时校验账号信息
	commitMemberAccount:function(){
		var me = this;
		var flag = 0;
		var form = me.down('form').getForm();
		if(form.isValid()){
			//校验手机和固话电话不能同时为空
			if(Ext.isEmpty(form.findField('linkmanMobile').getValue())&&Ext.isEmpty(form.findField('linkmanPhone').getValue())){
				MessageUtil.showMessage(i18n('i18n.ScatterCustManagerView.custMobileAndPhoneWarning'));
				return;
			}
			//校验账号是否重复
			var store = me.memberData.getAccountStore();
			//散客账号信息
			if('SCATTERCUST' == me.sourcesChannel){
				store = scatterCustControl.getScatterCustAccountStore();
			}
			store.each(function(r){
				if(form.findField('id').getValue()!=r.get('id')&&
					form.findField('bankAccount').getValue()==r.get('bankAccount')){
					flag++;
				}
			});
			if (flag > 0){
				MessageUtil.showMessage(i18n('i18n.MemberCustEditView.accountRepeat'));
				return;
			}
			var updateAccount = function(){
				//更新表单数据
				me.down('form').getForm().updateRecord(me.accountRecord);
				//插入到账号store
       			if(me.viewStatus == 'new'){
       				store.insert(0,me.accountRecord);
       			}
       			me.close();
			}
			if(form.findField('accountNature').getValue()=='PUBLIC_ACCOUNT' && form.findField('accountName').getValue().length <= 7){
				MessageUtil.showQuestionMes(i18n('i18n.MemberCustEditView.beSureIsCompAccount'),function(e){
					if(e=='yes'){
						updateAccount();
					}
				});
			}else if(form.findField('accountNature').getValue()=='PRIVATE_ACCOUNT' && form.findField('accountName').getValue().length > 4){
				MessageUtil.showQuestionMes(i18n('i18n.MemberCustEditView.beSureIsPersonalAccount'),function(e){
					if(e=='yes'){
						updateAccount();
					}
				});
			}else{
				var titleDescrip = (me.viewStatus == 'new')? i18n('i18n.PotentialCustManagerView.add'):i18n('i18n.PotentialCustManagerView.update');
				 MessageUtil.showQuestionMes(i18n('i18n.MemberCustEditView.sureYouWantTo')+ titleDescrip +i18n('i18n.ScatterCustManagerView.custSomeOtherMsg'), function(btn) {
			        	   if(btn == 'yes'){
			        		   updateAccount();
			        	   }
			       });
			}
		}else{
			MessageUtil.showMessage(i18n('i18n.MemberCustEditView.accountFormValid'));
			return;
		}
	},
	//锁定账号基本信息
	lockAllComponents:function(){
		var me = this;
		var accountForm = me.down('form').getForm();
		accountForm.getFields().each(function(field){
			field.setReadOnly(true);
		});
	}
});
/**
 * 支行查询界面
 */
Ext.define('BankSearchUI',{
	extend:'PopWindow',
	items:null,
	searchConditionPanel:null, //查询条件面板
	buttonPanel:null, //按钮面板
	searchResultPanel:null,  //查询结果面板
	title:i18n('i18n.MemberCustEditView.sub-branchInformationQueryInterface'),
	layout:{
		type:'vbox',
		align:'stretch'
	},
	width:650,
	height:400,
	searchBankRecord:null, //查询银行的record
	memberData:null,
	parent:null,       //父控件
    listeners:{
    	beforeclose:function(bankSearchWin){
    		bankSearchWin.hide();
    		return false;
    	}
    },
	initComponent:function(){
		var me = this;
		me.searchConditionPanel = Ext.create('BankSearchConditionPanel',{'parent':me,'memberData':me.memberData});
		me.buttonPanel =  Ext.create('BankButtonPanel',{'parent':me,'memberData':me.memberData});
		me.searchResultPanel =  Ext.create('BankSearchResultGrid',{'memberData':me.memberData,'parent':me.parent});
		
		me.items = [{
			xtype:'basicpanel',
			items:[me.searchConditionPanel],
			height:50
		},
		me.buttonPanel,
		{
			xtype:'basicpanel',
			items:[me.searchResultPanel],
			flex:1
		}];
		this.callParent();
		
		//初始化数据
		me.searchBankRecord = Ext.create('BankSearchConditionModel');
		me.searchConditionPanel.getForm().loadRecord(me.searchBankRecord);
	}
});


/**
 * 支行查询条件面板
 */
Ext.define('BankSearchConditionPanel',{
	extend:'SearchFormPanel',
	memberData:null,
	layout:{
		type:'table',
		columns:4
	},
	items:null,
	defaultType:'dptextfield',
	defaults:{
		labelWidth : 60,
		width : 150
	},
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
		me.memberData.getBankNameStore().load();
	},
	getItems:function(){
		var me = this;
		return [{
				xtype:'fieldcontainer',
				layout:{
					type:'hbox'
				},
				defaults:{
					xtype:'dpcombo',
					hideLabel:true
				},
				width : 235,
				items:[{xtype:'displayfield',value:i18n('i18n.MemberCustEditView.accountProvinceCityWar'),width:75},
						{name:'provinceId',
						blankText:i18n('i18n.MemberCustEditView.openProvinceAlert'),
						store:me.memberData.getBankProvinceStore(),
						displayField:'name',
						valueField:'id',
						flex:1,
						queryMode:'local',
						listeners : {
							select : function(combo,records) {
								if(records.length > 0){
									var form = combo.up('form').getForm();
									form.findField('cityId').setValue();
									me.memberData.getBankCityStore().load({
										params : {
											bankprovince : records[0].get('id')
										}
									});
								}
							}
						}},
				       {name:'cityId',
						blankText:i18n('i18n.MemberCustEditView.openCityAlert'),
						store:me.memberData.getBankCityStore(),
						displayField:'name',
						valueField:'id',
						queryMode:'local',
	            		forceSelection:true,
						flex:1,
						listConfig: {
						    loadMask:false
						}
						}]
			},{
				xtype:'dpcombo',
				name:'accountBank',
				fieldLabel:i18n('i18n.MemberCustEditView.bankName'),
				store:me.memberData.getBankNameStore(),
				displayField:'name',
				valueField:'name',
				allowBlank:false,
				flex:1,
				blankText:i18n('i18n.MemberCustEditView.pleaseInputBank'),
				editable : false
			},
//		            {fieldLabel:i18n('i18n.MemberCustEditView.bankAccount'),name:'accountBank'},
		            {fieldLabel:i18n('i18n.MemberCustEditView.branchBankName'),name:'accountBranch'}
//		            ,{fieldLabel:i18n('i18n.MemberCustEditView.provinceis'),hidden:true,name:'provinceId'}
		            ];
	}
});

/**
 * 支行查询按钮面板
 */
Ext.define('BankButtonPanel',{
	extend:'NormalButtonPanel',
	items:null,
	parent:null,   //父窗体 
	memberData:null,   //会员data层接口
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'middlebuttonpanel'
		},{
			xtype:'rightbuttonpanel',
			items:[{
					xtype:'button',
					text:i18n('i18n.PotentialCustManagerView.search'),
					scope:me,
					handler:me.searchBank
				},{
					xtype:'button',
					text:i18n('i18n.MemberCustEditView.reset'),
					scope:me,
					handler:me.resetCondition
				}]
		}];
	},
	//查询支行信息
	searchBank:function(){
		var me = this;
		//更新查询条件
		me.parent.searchConditionPanel.getForm().updateRecord(me.parent.searchBankRecord);
		// 请检测必填项是否填写完整
		if(!me.parent.searchConditionPanel.getForm().isValid()){
			MessageUtil.showErrorMes(i18n('i18n.MemberCustEditView.accountFormValid'));
			return;
		}
		var params = {};
		params.bankView = me.parent.searchBankRecord.data;
		
		var searchSuccessFn = function(result){
			var bankView = result.bankViewList;
			if(bankView != null && bankView.length > 0){
				me.memberData.getBankInfoStore().loadData(bankView);
			}else{
				MessageUtil.showMessage(i18n('i18n.MemberCustEditView.relevantBankInformationNotFound'));
			}
		};
		
		var failSuccessFn = function(result){
			if(!DpUtil.isEmpty(result.message)){
				MessageUtil.showMessage(result.message);
			}else{
				MessageUtil.showMessage(i18n('i18n.MemberCustEditView.serverExceptionWar'));
			}
		}
		
		me.memberData.searchBankInfo(params,searchSuccessFn,failSuccessFn);
	},
	//处理 支行所在省市
	manageModel4BankCity:function(searchBankModel){
		var me = this;
		if(!Ext.isEmpty(searchBankModel)){
			var cityId = searchBankModel.get('cityId');
			if(!Ext.isEmpty(cityId)){
				var pca = cityId.split(i18n('i18n.PotentialCustManagerView.searchEndTime'));
				if(pca>1){
					searchBankModel.set('cityId',pca[1]);
					searchBankModel.set('provinceId',pca[0]);
				}
			}
			return searchBankModel;
		}
		return searchBankModel;
	},
	//重置查询条件
	resetCondition:function(){
		var me = this;
		me.parent.searchConditionPanel.getForm().reset();
	}
});


/**
 * 支行查询结果grid面板
 */
Ext.define('BankSearchResultGrid',{
	extend:'PopupGridPanel',
	memberData:null,
	columns:[{text:i18n('i18n.MemberCustEditView.belongProvince'),dataIndex:'province'},
	         {text:i18n('i18n.MemberCustEditView.belongCity'),dataIndex:'city'},
	         {text:i18n('i18n.MemberCustEditView.bankAccount'),dataIndex:'accountBank'},
	         {text:i18n('i18n.MemberCustEditView.branchBankName'),dataIndex:'accountBranch',width:250}],
	store:null,
	parent:null,
	initComponent:function(){
		var me = this;
		me.store = me.memberData.getBankInfoStore();
		me.listeners = {
				scope:me,
				itemdblclick:me.doubleClickEvent
		};
		this.callParent();
	},
	//双击grid填充到父页面上
	doubleClickEvent:function(view,record){
		var me = this;
		//查询结果回传
		me.parent.setValueComeback(record);
		me.parent.subBankWindow.hide();
	}
});
