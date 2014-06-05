var keys = [
// 客户领款方式
		'CUST_DROWMONEY_TYPE',
		// 账户性质
		'ACCOUNT_PROPERTIES'];
initDataDictionary(keys);
var paymentList = new Array();

/**
 * 描述：提交付款界面
 *
 * @author zouming
 * @Date 2012-1-9
 */
Ext.define('PaymenApplyForm', {
	extend : 'TitleFormPanel',
	defaultType : 'basicfiledset',
	items : [{
				title : i18n('i18n.recompense.recompenseInfo'),// '理赔信息',
				defaultType : 'textfield',
				layout : {
					type : 'table',
					columns : 2
				},
				defaults : {
					width : 240
				},
				items : [{
							fieldLabel : i18n('i18n.recompense.recompenseType'),// '理赔类型',
							name : 'recompenseType',
							readOnly : true
						}, {
							fieldLabel : i18n('i18n.recompense.orderNumOrErroeNum'),// '凭证号',
							name : 'recompenseNum',
							readOnly : true
						}, {
							fieldLabel : i18n('i18n.recompense.recompenseMethod'),// '理赔方式',
							name : 'recompenseMethod',
							readOnly : true
						}, {
							fieldLabel : i18n('i18n.recompense.recompenseAmount'),// '理赔金额',
							name : 'realAmount',
							readOnly : true
						}]
			}, {
				title : i18n('i18n.payment.customerInfo'),// '客户信息',
				defaultType : 'textfield',
				layout : {
					type : 'table',
					columns : 2
				},
				defaults : {
					width : 240
				},
				items : [{
							fieldLabel : i18n('i18n.payment.customerName'),// '客户名称',
							name : 'custName',
							readOnly : true
						}, {
							fieldLabel : i18n('i18n.payment.customerCode'),// '客户编码',
							name : 'custNumber',
							readOnly : true
						}, {
							fieldLabel : i18n('i18n.recompense.connectMobilePhone'),// '联系电话',
							name : 'companyPhone',
							readOnly : true
						}]

			}, {
				title : i18n('i18n.recompense.accountInfo'),// '账户信息',
				defaultType : 'textfield',
				layout : {
					type : 'table',
					columns : 3
				},
				defaults : {
					width : 240
				},
				items : [{
					xtype : 'combo',
					store : getDataDictionaryByName(DataDictionary,
							'CUST_DROWMONEY_TYPE'),
					fieldLabel : i18n('i18n.recompense.custGetMoneyWay'),// '客户领款方式',
					name : 'paymentMode',
					displayField : 'codeDesc',
					allowBlank : false,
					editable : false,
					blankText : '领款方式不能为空',
					queryMode : 'local',
					valueField : 'code',
					value : i18n('i18n.payment.remittance'),// '汇款',
					listeners : {
						change : function(me, newValue, oldValue, eOpts) {
							paymentList = new Array();
							modeChange(me, newValue, oldValue);
						}
					}
				}, {
					xtype : 'combo',
					fieldLabel : i18n('i18n.recompense.pay_rcd.account_quale'),// '账户性质',
					id : 'accountPropId',
					value : 'PRIVATE_ACCOUNT',// '个人账户',
					store : getDataDictionaryByName(DataDictionary,
							'ACCOUNT_PROPERTIES'),
					allowBlank : false,
					editable : false,
					blankText : '账户性质不能为空',
					displayField : 'codeDesc',
					valueField : 'code',
					queryMode : 'local',
					// allowBlank:false,
					name : 'accountProp',
					colspan : 2
				}, {
					/**
					 * http://localhost:8080/crm-customer/customer/searchBankName.action
					 * http://localhost:8080/crm-customer/customer/searchBankName.
					 */
					xtype : 'combo',
					name : 'bankName',
					id : 'openBankId',
					fieldLabel : i18n('i18n.recompense.pay_rcd.bankName'),// '开户银行',
					store : Ext.create('BankListStore', {
								listeners : {
									beforeload : function(store, operation,
											eOpts) {
										Ext.apply(operation, {
													params : {
														'bankName' : Ext
																.getCmp('openBankId')
																.getValue()
													}
												})
									}
								}
							}),
					allowBlank : false,
					blankText : '开户银行不能为空',
					emptyText : '输入模糊查询',
					forceSelection : true,
					minChars : 2,
					displayField : 'name',
					valueField : 'name',
					labelWidth : 100,
					colspan : 3,
					listeners : {
						change : function(me, newValue, oldValue, eOpts) {
							for (var i = 0; i < this.store.data.length; i++) {
								if (this.getValue() == this.getStore().data.items[i].data.name) {
									this
											.up('form')
											.getForm()
											.findField('openBank01')
											.setValue(this.getStore().data.items[i].data.id);
								}
							}
							if (newValue != oldValue) {
								Ext.getCmp('subBankId').getStore().removeAll();
								Ext.getCmp('subBankId').reset();
								if (!Ext.isEmpty(me.up('form').getForm()
										.findField('city').getValue())
										&& !Ext.isEmpty(me.up('form').getForm()
												.findField('province')
												.getValue())
										&& !Ext.isEmpty(newValue)) {
									Ext.getCmp('subBankId').getStore().load();
								}
							}
						}
					}
				}, {
					/**
					 * 省份
					 * http://localhost:8080/crm-customer/customer/searchBankProvince.action?_dc=1357524125487
					 */
					xtype : 'combo',
					name : 'province',
					id : 'bankAddressId',
					labelSeparator : ':',
					fieldLabel : i18n('i18n.recompense.pay_rcd.branch_bankName'),// '开户支行',
					labelWidth : 100,
					store : Ext.create('ProvinceStore', {}),
					displayField : 'name',
					valueField : 'id',
					editable : false,
					allowBlank : false,
					blankText : '银行省份不能为空',
					emptyText : '省份',
					listeners : {
						change : function(me, newValue, oldValue, eOpts) {
							var form = this.up('form').getForm();

							/**
							 * 如果理赔方式为在线理赔或者付款性质与现金相关，那么不走省市级连的操作
							 */
							if (form.findField('recompenseMethod').getValue() == '在线理赔'
									|| form.findField('recompenseMethod')
											.getValue() == 'online'
									|| (form.findField('paymentMode')
											.getValue() == 'CASH'
											|| form.findField('paymentMode')
													.getValue() == 'CASH_AFTER_STRIKE_A_BALANCE' || form
											.findField('paymentMode')
											.getValue() == 'ALL_STRIKE_A_BALANCE')) {
								return;
							}
							if (newValue != oldValue && !Ext.isEmpty(newValue)) {
								Ext.getCmp('cityId').getStore().removeAll();
								Ext.getCmp('cityId').reset();
								Ext.getCmp('cityId').getStore().load();
								Ext.getCmp('subBankId').reset();
							}
						}
					}
				}, {
					/**
					 * 城市 bankprovince:wwNiEgEuEADgA4V6wKjeATcYh+k=
					 * http://localhost:8080/crm-customer/customer/searchBankCityByProvinceId.action
					 */
					xtype : 'combo',
					name : 'city',
					id : 'cityId',
					width : 105,
					editable : false,
					allowBlank : false,
					blankText : '银行城市不能为空',
					emptyText : '市',
					store : Ext.create('CityListStore', {
						listeners : {
							beforeload : function(store, operation, eOpts) {
								Ext.apply(operation, {
											params : {
												'bankprovince' : Ext
														.getCmp('bankAddressId')
														.getValue()
											}
										})
							}
						}
					}),
					displayField : 'name',
					valueField : 'id',
					listConfig : {
						loadMask : false
					},
					listeners : {
						change : function(me, newValue, oldValue, obj) {
							var form = this.up('form').getForm();

							/**
							 * 如果理赔方式为在线理赔或者付款性质与现金相关，那么不走省市支行级连的操作
							 */
							if (form.findField('recompenseMethod').getValue() == '在线理赔'
									|| form.findField('recompenseMethod')
											.getValue() == 'online'
									|| (form.findField('paymentMode')
											.getValue() == 'CASH'
											|| form.findField('paymentMode')
													.getValue() == 'CASH_AFTER_STRIKE_A_BALANCE' || form
											.findField('paymentMode')
											.getValue() == 'ALL_STRIKE_A_BALANCE')) {
								return;
							}
							if (!Ext.isEmpty(newValue) && newValue != oldValue) {
								var branchName = this.up('form').getForm()
										.findField('branchName');
								branchName.getStore().removeAll(true);
								branchName.reset();
								if (!Ext.isEmpty(me.up('form').getForm()
										.findField('bankName').getValue())
										&& !Ext.isEmpty(me.up('form').getForm()
												.findField('province')
												.getValue())
										&& !Ext.isEmpty(newValue)) {
									branchName.getStore().load();
								}

							}
						}
					}
				}, {
					// 支行名称
					xtype : 'combo',
					name : 'branchName',
					id : 'subBankId',
					width : 135,
					listConfig : {
						loadMask : false
					},
					allowBlank : false,
					forceSelection : true,
					blankText : '支行名称不能为空',
					emptyText : '支行名称--输入模糊查询',
					store : Ext.create('BranchBankListStore', {
						listeners : {
							beforeload : function(store, operation, eOpts) {
								Ext.getCmp('subBankId').getStore().removeAll();
								Ext.apply(operation, {
											/**
											 * "bankView":{"province":"","city":"",
											 * "accountBank":"\u4e2d\u56fd\u519c\u4e1a\u94f6\u884c",
											 * "accountBranch":"",
											 * "provinceId":"wwNiEgEuEADgA4V6wKjeATcYh+k=",
											 * "cityId":"wwNiEgEuEADgA4YTwKjeAU1UgE4=",
											 * "accountBankId":"",
											 * "accountBranchId":""}
											 */
											params : {
												'bankView.cityId' : Ext
														.getCmp('cityId')
														.getValue(),
												'bankView.accountBank' : Ext
														.getCmp('openBankId')
														.getValue(),
												'bankView.provinceId' : Ext
														.getCmp('bankAddressId')
														.getValue(),
												'bankView.accountBranch' : Ext
														.getCmp('subBankId')
														.getRawValue()
											}
										})
							}
						}
					}),
					minChars : 2,
					displayField : 'accountBranch',
					valueField : 'accountBranchId',
					fieldLabel : '',
					labelSeparator : ''
				}, {
					fieldLabel : i18n('i18n.recompense.pay_rcd.userName'),// '开户名',
					allowBlank : false,
					blankText : '开户名不能为空',
					id : 'openName',
					name : 'openName',
					readOnly : true
				}, {
					xtype : 'combobox',
					allowBlank : false,
					blankText : '开户账号不能为空',
					fieldLabel : i18n('i18n.recompense.pay_rcd.account_number'),// '开户账号',
					store : Ext.create('Ext.data.Store', {
								fields : ['account'],
								autoLoad : false
							}),
					regex : /^\d{0,}$/,
					regexText : '帐号只能为数字',
					queryMode : 'local',
					name : 'account',
					id : 'account',
					colspan : 2,
					displayField : 'account',
					valueField : 'account',
					listeners : {
						'select' : function(me, records) {
							var paymentMode = this.up('form').getForm()
									.findField('paymentMode').getValue();

							if (paymentMode == i18n('i18n.payment.cash')
									|| paymentMode == 'CASH'
									|| paymentMode == 'CASH_AFTER_STRIKE_A_BALANCE'
									|| paymentMode == 'ALL_STRIKE_A_BALANCE'
									|| paymentMode == i18n('i18n.payment.cashAfterStrikeABalance')
									|| paymentMode == i18n('i18n.payment.AllStrikeABalance')) {
								for (var i = 0; i < paymentList.length; i++) {
									// 根据所选择的的账号设置 省市银行支行的值
									if (records[0].get('account') == paymentList[i].account) {
										Ext.getCmp('openBankId').setValue(Ext
												.isEmpty(paymentList[i].bank)
												? ''
												: paymentList[i].bank.name);
										Ext
												.getCmp('bankAddressId')
												.setValue(Ext
														.isEmpty(paymentList[i].bankProvice)
														? ''
														: paymentList[i].bankProvice.name);
										Ext
												.getCmp('cityId')
												.setValue(Ext
														.isEmpty(paymentList[i].bankCity)
														? ''
														: paymentList[i].bankCity.name);
										Ext.getCmp('subBankId').setValue(Ext
												.isEmpty(paymentList[i].branch)
												? ''
												: paymentList[i].branch.name);
										Ext
												.getCmp('openName')
												.setValue(paymentList[i].openName);

										me
												.up('form')
												.getForm()
												.findField('bankId')
												.setValue(Ext
														.isEmpty(paymentList[i].bank)
														? ''
														: paymentList[i].bank.id);
										me
												.up('form')
												.getForm()
												.findField('bankProviceId')
												.setValue(Ext
														.isEmpty(paymentList[i].bankProvice)
														? ''
														: paymentList[i].bankProvice.id);
										me
												.up('form')
												.getForm()
												.findField('bankCityId')
												.setValue(Ext
														.isEmpty(paymentList[i].bankCity)
														? ''
														: paymentList[i].bankCity.id);
										me
												.up('form')
												.getForm()
												.findField('branchId')
												.setValue(Ext
														.isEmpty(paymentList[i].branch)
														? ''
														: paymentList[i].branch.id);
										return
									}
								}
							}
						}
					},
					listConfig : {
						loadMask : false
					}
				}, {
					xtype : 'numberfield',
					fieldLabel : i18n('i18n.recompense.mobileNumber'),// '手机号码',
					// allowBlank:false,
					// blankText:'手机号码不能为空',
					// regex:/^((\d{8})|(\d{11}))$/,
					// regexText:'手机号只能是数字且长度为8位或11位',
					name : 'mobile',
					id : 'mobile',
					colspan : 3,
					hideTrigger : true,
					readOnly : true
				}, {
					xtype : 'panel',
					colspan : 3,
					width : 450,
					margin : '0 0 0 30',
					border : false,
					html : '<span style="color:green">说明：公司账户*该账户性质对应客户账户名称为公司名称*<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp个人账户*该账户性质对应客户账户名称为人名*</span>'
				}, {
					xtype : 'hiddenfield',
					name : 'bankId',
					hidden : true
				}, {
					xtype : 'hiddenfield',
					name : 'branchId',
					hidden : true
				}, {
					xtype : 'hiddenfield',
					name : 'bankCityId',
					hidden : true
				}, {
					xtype : 'hiddenfield',
					name : 'bankProviceId',
					hidden : true
				}, {
					xtype : 'combo',
					name : 'openBank01',
					hidden : true
				}]

			}]
});
var paymentApplyWin;

/**
 * 领款方式改变之后的操作
 *
 * @param me
 * @param newValue
 * @param oldValue
 * @param eOpts
 */
function modeChange(me, newValue, oldValue) {
	if (newValue == 'CASH'/* i18n('i18n.payment.cash')/*"现金" */
			|| newValue == 'CASH_AFTER_STRIKE_A_BALANCE'/* i18n('i18n.payment.cashAfterStrikeABalance')/*"冲账后付现" */
			|| newValue == 'ALL_STRIKE_A_BALANCE'/* i18n('i18n.payment.AllStrikeABalance')/*"全额冲账" */) {
		Ext.getCmp('accountPropId')
				.setValue(i18n('i18n.payment.cashierCardAccount'));
		Ext.getCmp('accountPropId').setReadOnly(true);
		var params = {
			'employeeNum' : User.loginName
		};
		var showBankInfo = function(bankObj) {

		}
		var successFn = function(json) {

			if (json.paymentList.length == 0) {
				MessageUtil
						.showMessage("i18n.payment.notinsertcustomeraccount");
				return;
			}
			// Ext.getCmp('accountPropId').setValue();
			Ext.getCmp('accountPropId').setReadOnly(true);// 账户性质

			Ext.getCmp('openBankId').forceSelection = false;
			Ext.getCmp('openBankId').doComponentLayout();
			Ext.getCmp('openBankId').setValue(Ext
					.isEmpty(json.paymentList[0].bank)
					? ''
					: json.paymentList[0].bank.name);
			Ext.getCmp('openBankId').setReadOnly(true);// 开户银行
			me.up('form').getForm().findField('bankId').setValue(Ext
					.isEmpty(json.paymentList[0].bank)
					? ''
					: json.paymentList[0].bank.id);
			Ext.getCmp('bankAddressId').setValue(Ext
					.isEmpty(json.paymentList[0].bankProvice)
					? ''
					: json.paymentList[0].bankProvice.name);
			Ext.getCmp('bankAddressId').setReadOnly(true);// 省
			me.up('form').getForm().findField('bankProviceId').setValue(Ext
					.isEmpty(json.paymentList[0].bankProvice)
					? ''
					: json.paymentList[0].bankProvice.id);
			Ext.getCmp('cityId').setValue(Ext
					.isEmpty(json.paymentList[0].bankCity)
					? ''
					: json.paymentList[0].bankCity.name);
			Ext.getCmp('cityId').setReadOnly(true);// 市
			me.up('form').getForm().findField('bankCityId')
					.setValue(Ext.isEmpty(json.paymentList[0].bankCity)
					? ''
					:json.paymentList[0].bankCity.id);
			Ext.getCmp('subBankId').forceSelection = false;
			Ext.getCmp('subBankId').doComponentLayout();
			Ext.getCmp('subBankId').setValue(Ext.isEmpty(json.paymentList[0].branch)?'':
				json.paymentList[0].branch.name);
			Ext.getCmp('subBankId').setReadOnly(true);// 支行
			me.up('form').getForm().findField('branchId').setValue(Ext
					.isEmpty(json.paymentList[0].branch)
					? ''
					: json.paymentList[0].branch.id);

			Ext.getCmp('openName').setValue(json.paymentList[0].openName);
			Ext.getCmp('openName').setReadOnly(true);//开户名
			Ext.getCmp('account').setValue(json.paymentList[0].account);
			Ext.getCmp('account').hideTrigger = true;
			Ext.getCmp('account').doComponentLayout();
			Ext.getCmp('account').getStore().removeAll();
			paymentList = new Array();
			for (var i = 0; i < json.paymentList.length; i++) {
				accountBank = new Array();
				paymentList.push(json.paymentList[i]);
				Ext.getCmp('account').getStore().add({
							'account' : json.paymentList[i].account
						});
			}
			//			Ext.getCmp('account').setReadOnly(true);//帐号
			Ext.getCmp('mobile').setValue(json.paymentList[0].mobile);
			Ext.getCmp('mobile').setReadOnly(true);//电话

		};
		var failureFn = function(json) {
			if (Ext.isEmpty(json)) {
				MessageUtil
						.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
			} else {
				MessageUtil.showErrorMes(json.message);
			}
		};
		RecompenseDataN.prototype.queryPaymentByEmployeeCode(params, successFn,
				failureFn)

	} else if (newValue == 'REMIT'/*i18n('i18n.payment.remittance')汇款*/
			|| newValue == 'REMIT_AFTER_STRIKE_A_BALANCE'/*i18n('i18n.payment.remitAfterStrikeABalance')*/) {

		me.up('form').getForm().findField('account').getStore().removeAll(true);
		me.up('form').getForm().findField('account').reset();

		Ext.getCmp('openBankId').forceSelection = true;
		Ext.getCmp('openBankId').doComponentLayout();
		Ext.getCmp('subBankId').forceSelection = true;
		Ext.getCmp('subBankId').doComponentLayout();

		Ext.getCmp('accountPropId').getStore().removeAll();
		Ext.getCmp('accountPropId').getStore().add({
					'code' : 'PUBLIC_ACCOUNT',
					'codeDesc' : i18n('i18n.payment.outToPublicAccount')
				}, {
					'code' : 'PRIVATE_ACCOUNT',
					'codeDesc' : i18n('i18n.payment.outToPrivateAccount')
				});
		Ext.getCmp('accountPropId')
				.setValue(i18n('i18n.payment.outToPrivateAccount'));

		Ext.getCmp('accountPropId').setReadOnly(false);
		Ext.getCmp('openBankId').setReadOnly(false);//开户银行
		Ext.getCmp('openBankId').reset();

		Ext.getCmp('bankAddressId').setReadOnly(false);//省
		Ext.getCmp('bankAddressId').reset();//省

		Ext.getCmp('cityId').setReadOnly(false);//市
		Ext.getCmp('cityId').reset();

		Ext.getCmp('subBankId').setReadOnly(false);//支行
		Ext.getCmp('subBankId').reset();

		Ext.getCmp('openName').setReadOnly(false);//开户名
		Ext.getCmp('openName').reset();

		Ext.getCmp('mobile').setReadOnly(false);//电话
		Ext.getCmp('mobile').reset();

	}
}