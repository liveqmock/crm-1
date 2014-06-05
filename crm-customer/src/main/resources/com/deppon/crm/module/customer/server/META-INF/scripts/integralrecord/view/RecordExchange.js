var recordSearchControl = (CONFIG.get('TEST')) ? Ext
		.create('RecordSearchDataTest') : Ext.create('RecordSearchData');

///**
// * .
// * <p>
// * 带标题的礼品申请单</br>
// * </p>
// * 
// * @author 潘光均
// * @时间 2012-03-19
// */

//Ext.define('ApplyGridTitlePanel', {
//	extend : 'BasicVboxPanel',
//	width : '100%',
//	applyGridPanel : null,
//	height : 315,
//	initComponent : function() {
//		this.items = this.getItems();
//		this.callParent();
//	},
//	getItems : function() {
//		var me = this;
//		me.applyGridPanel = new ApplyGridPanel();
//		return [ 
////		         {
////			xtype : 'toppanel',
////			items : [ {
////				xtype : 'titlepanel',
////				flex : 1,
////				items : [ {
////					xtype : 'displayfield',
////					value : i18n('i18n.RecordExchange.aListOfApplications')
////				} ]
////			} ]
////		}, 
//		{
//			xtype : 'basicpanel',
//			flex : 1,
//			items : [ me.applyGridPanel ]
//		} ];
//	}
//});

/**
 * .
 * <p>
 * 申请单</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */
Ext.define('ApplyGridPanel', {
	extend : 'PopupGridPanel',
//	width : 410,
	flex:1,
	width:'100%',
	id : 'applyGridPanel',
	store : null,
	columns : null,
	initComponent : function() {
		var me = this;
		me.columns = me.getColumns();
		me.selModel = me.getSelModel();
		me.store = DpUtil.createStore('applyGiftGridStore',
				'GiftExchangeModel', null, null);
		this.callParent();
	},
	getSelModel : function() {
		return Ext.create('Ext.selection.CheckboxModel', {
			mode : 'MULTI'
		})
	},
	getColumns : function() {
		return [ {
			xtype : 'rownumberer',
			width : 40,
			text : i18n('i18n.Integral.number')
		}, {
			header : 'id',
			hidden : true,
			dataIndex : CONFIGNAME.get('id')
		}, {
			hidden : true,
			header : 'contactId',
			dataIndex : CONFIGNAME.get('conContact'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.id;
				}
			}
		}, {
			header : i18n('i18n.Integral.giftName'),
			dataIndex : CONFIGNAME.get('custGifGift'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.giftName;
				}
			}
		}, {
//			header : i18n('i18n.Integral.contactNumber'),
			hidden:true,
			dataIndex : CONFIGNAME.get('custGifGift'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.giftNumber;
				}
			}
		}, {
//			header : i18n('i18n.Integral.contactNumber'),
			hidden:true,
			dataIndex : CONFIGNAME.get('custGifGift'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.beginTime;
				}
			}
		}, {
//			header : i18n('i18n.Integral.contactNumber'),
			hidden:true,
			dataIndex : CONFIGNAME.get('custGifGift'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.endTime;
				}
			}
		}, {
//			header : i18n('i18n.Integral.contactNumber'),
			hidden:true,
			dataIndex : CONFIGNAME.get('custGifGift'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.isStart;
				}
			}
		}, {
			header : i18n('i18n.Integral.contactNumber'),
			dataIndex : CONFIGNAME.get('conContact'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.number;
				}
			}
		}, {
			header : i18n('i18n.Integral.contatct'),
			dataIndex : CONFIGNAME.get('conContact'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.name;
				}
			}
		}, {
			header : i18n('i18n.Integral.exchangeNum'),
			dataIndex : CONFIGNAME.get('excExchangeNum')
		}, {
			header : i18n('i18n.Integral.userdRecord'),
			dataIndex : CONFIGNAME.get('excSpendRecord')
		}, {
			header : i18n('i18n.Integral.exchagerNo'),
			dataIndex : CONFIGNAME.get('conContact'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.idCard;
				}
			}
		} ];
	}
})
/**
 * .
 * <p>
 * 客户信息</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */
Ext
		.define(
				'CustomerPanel',
				{
					extend : 'BasicFormPanel',
					id : 'customerPanel',
					width : '67%',
					height : 190,
					initComponent : function() {
						var me = this
						me.items = me.getItems();
						this.callParent();
					},
					getItems : function() {
						var me = this;
						return [ {
							xtype : 'basicfiledset',
							title : i18n('i18n.IntegralRuleEdit.custInfo'),
//							height : 190,
							// width:'20%',
							defaults : {
								width : 210,
								labelWidth : 90,
								// height:25,
								xtype : 'textfield'
							},
							layout : {
								type : 'table',
								columns :2
							},
							items : [{
											xtype : 'textfield',
											id : 'cusContactNum',
											fieldLabel : i18n('i18n.MemberCustEditView.contactNo'),
											maxLength:20,
											width:280,
//											width : 170,
											labelWidth : 90,
											maxLength:20,
											maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20),
											listeners:{
												specialkey:function(th,  e,  eOpts ){
													if (e.getKey()==Ext.EventObject.ENTER) {
														me.serachCustomer();
													}
												}
											}
										}, {
											xtype : 'button',
											text : i18n('i18n.PotentialCustManagerView.search'),
											margin:'0 0 2 20',
											width : 50,
											handler : function() {
												me.serachCustomer();
											}
									},{
										xtype : 'basicpanel',
										width:280,
										layout : 'hbox',
										items : [ {
											xtype : 'textfield',
											id : 'cusContactName',
											fieldLabel : i18n('i18n.Integral.contatct'),
											width : 185,
											labelWidth : 90,
											readOnly : true
										}, {
											xtype : 'checkbox',
											labelWidth : 60,
											readOnly : true,
											name : 'isMainContact',
											fieldLabel : i18n('i18n.IntegralRuleEdit.mainContact')
										} ]
									},
									{
										fieldLabel : i18n('i18n.Integral.currentAviableRecrod'),
										readOnly : true,
//										margin:'0 0 0 10',
										name : CONFIGNAME
												.get('conCurrentUsableScorePeriod')
									},
									{
										fieldLabel : i18n('i18n.IntegralRuleEdit.borrowRecord'),
										name : 'canExchangeRecord',
										readOnly : true
									},
									{
										fieldLabel : i18n('i18n.Integral.currentAllRecrod'),
										readOnly : true,
//										margin:'0 0 0 10',
										name : CONFIGNAME.get('conTotalReocordCurrentPeriod')
									},
									{
										fieldLabel : i18n('i18n.Integral.lastestRecordTime'),
										readOnly : true,
										cls:'readonly',
										xtype : 'datefield',
										name : CONFIGNAME
												.get('conLastRecordTime')
									},{
										fieldLabel : i18n('i18n.Integral.exchagerNo'),
										readOnly : true,
//										margin:'0 0 0 10',
										labelWidth : 90,
//										width : 170,
										id : CONFIGNAME.get('excExchangerNo'),
										name : CONFIGNAME.get('excExchangerNo'),
										emptyText :  i18n('i18n.Integral.exchagerNo'),
										regex : /^[1-9](\d{16}|\d{13})[0-9X]$/
									},
									//需求变更
//									{
//										fieldLabel : i18n('i18n.IntegralRuleEdit.recordInvalidateTime'),
//										xtype : 'datefield',
//										margin:'0 0 0 10',
//										readOnly : true
//									}, 
									{
										fieldLabel : i18n('i18n.PotentialCustManagerView.customerName'),
										readOnly : true,
										xtype : 'textfield',
										name : 'customerName'
									}, {
										fieldLabel : i18n('i18n.MemberCustEditView.custNo'),
//										margin:'0 0 0 10',
										xtype : 'textfield',
										readOnly : true,
										name : 'customerNum'
									}, {
										fieldLabel : i18n('i18n.Integral.currentAviableRecrod'),
										readOnly : true,
										name : 'custCurrentUsableScore'
									}, {
										xtype : 'checkbox',
										labelWidth : 180,
//										margin:'0 0 0 10',
										name : 'allowExchangeGift',
										readOnly : true,
										fieldLabel : i18n('i18n.IntegralRuleEdit.onlyAllowMianContactExchange')
									} ]
						} ];
					},
					serachCustomer : function() {
						var contactId = Ext.getCmp('cusContactNum').getValue();
						var param = {
							condition : {
								'contactId' : contactId
							}
						};
						// 如果数据加载成功，执行该方法
						var successFn = function(json) {
							if (json.contactRecordList == null
									|| json.ingegralList == null
									|| json.contactRecordList <= 0
									|| json.ingegralList <= 0) {
//								DpUtil.showMessage(i18n('i18n.PotentialCustManagerView.searchRecords'));
								return;
							}
							var recordListModel = new RecordListModel(
									json.ingegralList[0]);
							var contactRecordModel = new ContactRecordModel(
									json.contactRecordList[0]);
							/** *******加载客户表格的值***************** */
							Ext.getCmp('customerPanel').getForm().loadRecord(contactRecordModel);
							//获取联系人id
							contactNo = contactRecordModel.get(CONFIGNAME
									.get('conContact')).id;
							//加载兑换分身份证号
							Ext.getCmp(CONFIGNAME.get('excExchangerNo')).setValue(
								contactRecordModel.get(CONFIGNAME.get('conContact')).idCard);
							// 加载联系人姓名
							Ext.getCmp('cusContactName').setValue(
									contactRecordModel.get(CONFIGNAME
											.get('conContact')).name);
							// 加载是否主联系人信息
							var isMainLinkMan = contactRecordModel
									.get(CONFIGNAME.get('conContact')).isMainLinkMan;
							Ext.getCmp('customerPanel').getForm().findField(
									'isMainContact').setValue(isMainLinkMan);

							// 加载客户姓名
							Ext.getCmp('customerPanel').getForm().findField(
									'customerName').setValue(
									recordListModel.get(CONFIGNAME
											.get('member')).custName);
							// 加载客户编码
							Ext.getCmp('customerPanel').getForm().findField(
									'customerNum').setValue(
									recordListModel.get(CONFIGNAME
											.get('member')).custNumber);
							// 加载联系人本期可用积分
							var conRecord = contactRecordModel.get(CONFIGNAME
									.get('conCurrentUsableScorePeriod'));
							
							//减去暂存礼品所需积分
							Ext.getCmp('applyGridPanel').getStore().each(function(record){
								if (record.get(CONFIGNAME.get('conContact')).number==contactId) {
									conRecord-=record.get(CONFIGNAME.get('excSpendRecord'));
								}
							});
							//设置联系人本期可用积分
							Ext.getCmp('customerPanel').getForm().findField(
											CONFIGNAME.get('conCurrentUsableScorePeriod')).setValue(conRecord);

							// 加载客户本期可用积分
							var custRecord = recordListModel.get(CONFIGNAME
									.get('currentUsableScore'));
							Ext.getCmp('customerPanel').getForm().findField(
									'custCurrentUsableScore').setValue(
									custRecord);
							// 加载是否允许联系人兑换积分
							var isRedeempoints = recordListModel.get(CONFIGNAME
									.get('member')).isRedeempoints;
							Ext.getCmp('customerPanel').getForm().findField(
									'allowExchangeGift').setValue(
									isRedeempoints);

							/** *****根据主联系人和是否只允许主联系人兑换礼品设置可调用积分的值********* */
							if (isRedeempoints&&!isMainLinkMan) {
								MessageUtil.showErrorMes(i18n('i18n.IntegralEx.onlyMainContactCanExchange'));
								Ext.getCmp('customerPanel').getForm().reset();
							} else if (isRedeempoints && isMainLinkMan) {
								Ext.getCmp('customerPanel').getForm()
										.findField('canExchangeRecord')
										.setValue(custRecord - conRecord);
							} else if(!isRedeempoints) {
								Ext.getCmp('customerPanel').getForm()
										.findField('canExchangeRecord')
										.setValue(0);
							}
							// 礼品选择页面需要的可用积分
							avilableRecord = conRecord
									+ parseInt(Ext.getCmp('customerPanel')
											.getForm().findField(
													'canExchangeRecord')
											.getValue());

						};
						// 如果数据加载失败的方法
						var failureFn = function(json) {
							MessageUtil.showErrorMes(json.message);
							Ext.getCmp('customerPanel').getForm().reset();
						};
						// 请求数据
						recordSearchControl.searchContactRecordInfo(param,
								successFn, failureFn);
					}
				});

/**
 * .
 * <p>
 * 礼品选择panel</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */
Ext.define('GiftChoosePanel',{
					extend : 'BasicFormPanel', // --第一步,定义一个主panel,继承NoTitleFormPanel
					width : '33%',
					height : 190,
					id : 'giftChoosePanel',
					// 选择礼品时传过来的model
					record : null,
					defaults : {
						margins : '0 0 0 5'
					},
					initComponent : function() {
						this.items = this.getItems();
						this.callParent();
					},
					getItems : function() {
						var me = this;
						return [ {
							xtype : 'basicfiledset',
							title : i18n('i18n.IntegralEx.giftChoose'),
							layout : {
								type : 'table',
								columns : 1
							},
							defaultType : 'textfield',
							defaults : {
								labelWidth : 60,
								width : 220
							},
							items : [
									{
										name : 'id',
										hidden : true,
										id : 'giftId'
									},
									{
										xtype : 'queryCombox',
										fieldLabel :i18n( 'i18n.Integral.giftName'),
										editable:false,
										name : CONFIGNAME.get('excGiftName'),
										id : CONFIGNAME.get('excGiftName'),
										queryMode : 'local',
										cls:'readonly',
										listeners : {
//											specialkey : function(field, e, obj) {
//												var value = Ext
//														.getCmp(
//																CONFIGNAME
//																		.get('excGiftName'))
//														.getValue();
//												if (e.getKey() == e.ENTER
//														&& value.length > 3) {
//													var giftChooseView = null;
//													if (Ext
//															.getCmp('giftChooseView') == null
//															|| Ext
//																	.getCmp('giftChooseView') == undefined) {
//														giftChooseView = new GiftChooseView(
//																{
//																	'giftName' : value
//																});
//													}
//													giftChooseView.show();
//												}
//											}
											expand:function(field,eOptss){
												var giftChooseView = null;
												var value = Ext.getCmp(CONFIGNAME.get('excGiftName')).getValue();
												if (Ext
														.getCmp('giftChooseView') == null
														|| Ext
																.getCmp('giftChooseView') == undefined) {
													giftChooseView = new GiftChooseView(
															{
																'giftName' : value
															});
												}
												giftChooseView.show();
											}
										}
									},
									{
//										xtype : 'numberfield',
										fieldLabel : i18n('i18n.IntegralRuleEdit.giftRecord'),
										name : CONFIGNAME
												.get('gifChooseNeedRecord'),
										id : CONFIGNAME
												.get('gifChooseNeedRecord'),
										readOnly : true
									},
									{
										fieldLabel : i18n('i18n.Integral.exchangeNum'),
										xtype : 'numberfield',
										allowBlank : false, // 允许为空
										allowDecimals : false, // 允许小数点
										// allowNegative: false, // 允许负数，不能用咯。。
										hideTrigger: true,//隐藏扩展按钮
										mouseWheelEnabled: false,//禁止鼠标滚动轴改变值
										minValue : 1,
										maxLength:20,
										maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20),
										id : 'exchangeNum',
										name : CONFIGNAME.get('excExchangeNum'),
										listeners : {
											change : function(th,newValue,oldValue, obj) {
												me.exchangeNumBlur(th);
											}
										}
									},{
										fieldLabel : i18n('i18n.Integral.userdRecord'),
										id : 'spendRecord',
										name : CONFIGNAME.get('excSpendRecord'),
										allowBlank : false,
										readOnly : true
									},{
//										fieldLabel : i18n('i18n.Integral.userdRecord'),
										id : CONFIGNAME.get('gifGiftNumber'),
										hidden:true,
										name : CONFIGNAME.get('gifGiftNumber')
									},{
//										fieldLabel : i18n('i18n.Integral.userdRecord'),
										id : CONFIGNAME.get('gifIsStart'),
										hidden:true,
										name : CONFIGNAME.get('gifIsStart')
									},{
//										fieldLabel : i18n('i18n.Integral.userdRecord'),
										id : CONFIGNAME.get('giftBeginTime'),
										hidden:true,
										name : CONFIGNAME.get('giftBeginTime')
									} ]
						} ];
					},
					// 兑换数量改变时自动调整所需积分的值
					exchangeNumBlur : function(field) {
						if (avilableRecord == null
								|| avilableRecord == undefined) {
							MessageUtil.showErrorMes(i18n('i18n.IntegralRuleEdit.searchCustInfoFirst'));
							field.setValue(null);
							return;
						}
//						if (Ext.isEmpty(Ext.getCmp(CONFIGNAME.get('gifChooseNeedRecord')).getValue())) {
//							DpUtil.showMessage(i18n('i18n.IntegralRuleEdit.searchGiftFirst'));
//							return;
//						}
						// 计算选择礼品需要的积分
						spendRecord = field.getValue()
								* Ext.getCmp(
										CONFIGNAME.get('gifChooseNeedRecord'))
										.getValue();
						// 如果可用的积分逍遥需要的积分，则不能选择礼品
						if (avilableRecord < spendRecord) {
							MessageUtil.showErrorMes(i18n('i18n.IntegralRuleEdit.spendRecordCannntMoreThanAvaliable'));
							return;
							field.setValue(null);
							Ext.getCmp('spendRecord').setValue(null);
						}
						// 自动填充消耗积分字段
						Ext.getCmp('spendRecord').setValue(spendRecord);
					}

				});

// 操作按钮面板
Ext.define('ButtonExchangePanel', {
	extend : 'NormalButtonPanel',
	width:'100%',
	items : null,
	initComponent : function() {
		this.items = this.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [ {
			xtype : 'leftbuttonpanel',
			items : [ {
				xtype : 'button',
				text : i18n('i18n.IntegralRuleEdit.removeList'),
				handler : function() {
					me.removeItem();
				}
			} ]
		}, {
			xtype : 'middlebuttonpanel'
		}, {
			xtype : 'rightbuttonpanel',
			items : [ {
				xtype : 'button',
				text : i18n('i18n.IntegralRuleEdit.addList'),
				handler : function() {
					me.addItem();
				}
			} ]
		} ];
	},

	/**
	 * 添加礼品项
	 */
	addItem : function() {
		var me = this;
		if (!Ext.getCmp('giftChoosePanel').getForm().isValid()) {
			MessageUtil.showErrorMes(i18n('i18n.IntegralRuleEdit.giftInfoIncomplete'));
			return;
		}
		if (Ext.isEmpty(Ext.getCmp(CONFIGNAME.get('gifChooseNeedRecord')).getValue())) {
			MessageUtil.showErrorMes(i18n('i18n.IntegralRuleEdit.searchGiftFirst'));
		Ext.getCmp('exchangeNum').setValue(null);
		return;
	}
		/** ************扣除联系人积分************** */
		// 改变联系人可用积分的值
		var contactRecord = Ext.getCmp('customerPanel').getForm().findField(
				CONFIGNAME.get('conCurrentUsableScorePeriod'));
		// 若联系人本人的积分不够，则减可调用积分
		if (contactRecord.getValue() - spendRecord < 0) {
			contactRecord.setValue(0);
			var excRecord = Ext.getCmp('customerPanel').getForm().findField(
					'canExchangeRecord');
			var messRecord = contactRecord.getValue() - spendRecord;
			if (messRecord >= 0) {
				excRecord.setValue(excRecord.getValue()
						- (contactRecord.getValue() - spendRecord));
			}
			// 减去可以积分
			avilableRecord -= spendRecord;
		} else {
			contactRecord.setValue(contactRecord.getValue() - spendRecord);
			// 减去可以积分
			avilableRecord -= spendRecord;
		}

		// 加载表格数据
		var record = new GiftExchangeModel();
		Ext.getCmp('giftChoosePanel').getForm().updateRecord(record);
		var gifGiftNumber = Ext.getCmp(CONFIGNAME.get('gifGiftNumber')).getValue();
		var giftName = Ext.getCmp(CONFIGNAME.get('excGiftName')).getValue();
		var giftId = Ext.getCmp('giftId').getValue();
		var gift = {'id':giftId,'giftNumber':gifGiftNumber,'giftName':giftName}
		
//		var contactId = Ext.getCmp('cusContactNum').getValue();
		var contactNum = Ext.getCmp('cusContactNum').getValue();
		var contactName = Ext.getCmp('cusContactName').getValue();
		var exchangerNo = Ext.getCmp(CONFIGNAME.get('excExchangerNo')).getValue();
		
		var contact = {'id':contactNo,'number':contactNum,'name':contactName,'idCard':exchangerNo};
		
		record.set(CONFIGNAME.get('conContact'), contact);
		record.set(CONFIGNAME.get('custGifGift'), gift);
//		record.set(CONFIGNAME.get('excContactNo'), contactNum);
//		record.set(CONFIGNAME.get('excContactName'), contactName);
//		record.set(CONFIGNAME.get('excExchangerNo'), exchangerNo);
		Ext.getCmp('applyGridPanel').getStore().add(record);
		Ext.getCmp('giftChoosePanel').getForm().reset();
	},
	/**
	 * 删除礼品项
	 */
	removeItem : function() {
		// 返回Model[]
		var records = Ext.getCmp('applyGridPanel').getSelectionModel()
				.getSelection();
		if (records.length <= 0) {
			MessageUtil.showErrorMes(i18n('i18n.IntegralRuleEdit.chooseOne'));
			return;
		}
		//联系人编码
		var contactId = Ext.getCmp('cusContactNum').getValue();
		//恢复的积分数
		var returnRecord =0;
		for ( var i = 0; i < records.length; i++) {
			if (records[i].get(CONFIGNAME.get('conContact')).number==contactId) {
				returnRecord+=records[i].get(CONFIGNAME.get('excSpendRecord'));
			}
		}
		//恢复联系人积分
		Ext.getCmp('customerPanel').getForm().findField(
				CONFIGNAME.get('conCurrentUsableScorePeriod')).setValue(parseFloat(
						Ext.getCmp('customerPanel').getForm().findField(
						CONFIGNAME.get('conCurrentUsableScorePeriod')).getValue())+parseFloat(returnRecord));
		Ext.getCmp('applyGridPanel').getStore().remove(records);
	}
});

// 用于记录用户可以消费的积分
var avilableRecord = null;
/**
 * .
 * <p>
 * 积分查询viewport</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */
Ext.define('RecordExchangeView', {
	extend : 'PopWindow',
	id : 'recordExchangeView',
	width : 810,
	height : 450,
	layout:'fit',
	// 客户信息from
	customerPanel : null,
	// 礼品信息form
	giftChoosePanel : null,
	//条件和移除清单按钮panel
	buttonExchangePanel:null,
	// 申请单grid
	applyGridTitlePanel : null,
	// 初始化方法
	initComponent : function() {
		var me = this;
		this.fbar = this.getFbar();
		me.items = me.getItems();
		this.callParent();
	},
	// 底按钮
	getFbar : function() {
		var me = this;
		return [ {
			xtype : 'button',
			text : i18n('i18n.IntegralRuleEdit.temporaryBill'),
			handler : function() {
				me.temporaryBill();
			}
		}, {
			xtype : 'button',
			text : i18n('i18n.IntegralRuleEdit.saveBill'),
			handler : function() {
				me.saveBill();
			}
		}, {
			xtype : 'button',
			text : i18n('i18n.ManualRewardIntegralEditView.b_cance'),
			handler : function() {
				me.cancelBill();
			}
		} ]
	},
	getItems : function() {
		var me = this;
		me.customerPanel = new CustomerPanel();
		me.giftChoosePanel = new GiftChoosePanel();
		me.applyGridTitlePanel = new ApplyGridPanel();
		me.buttonExchangePanel = new ButtonExchangePanel();
		var centerPanel = {
			xtype : 'basicpanel',
			autoScroll:false,
			id : 'centerpanel',
//			height:180,
			layout : 'vbox',
			items : [{xtype:'basicpanel',
								width:'100%',
								height:180,
								autoScroll:false,
								layout:'column',
								items:[me.customerPanel, 
								       		me.giftChoosePanel
								       ]},
								 me.buttonExchangePanel,
								 me.applyGridTitlePanel
							]};
		return centerPanel;
	},
	productParam : function() {
		var me = this;
		var giftArray = new Array();
		var custGiftModel = null;
		me.applyGridTitlePanel.getStore().each(function(record) {
			if (record.get(CONFIGNAME.get('custGifGift'))!=null&&
					record.get(CONFIGNAME.get('conContact'))!=null) {
				var custGiftModel = {
						'id':record.get('id'),
						'convertNumber' : record.get('convertNumber'),
						'score' : record.get('score'),
						'convertIdCard' : record.get(CONFIGNAME.get('excExchangerNo')),
						'gift' : {
							'id' : record.get(CONFIGNAME.get('custGifGift')).id,
							'giftName' : record.get(CONFIGNAME.get('custGifGift')).giftName,
							'giftNumber':record.get(CONFIGNAME.get('custGifGift')).giftNumber,
							'isStart':record.get(CONFIGNAME.get('custGifGift')).isStart,
							'beginTime':record.get(CONFIGNAME.get('custGifGift')).beginTime,
							'endTime':record.get(CONFIGNAME.get('custGifGift')).beginTime
						},
						'contact' : {
							'id':record.get(CONFIGNAME.get('conContact')).id,
							'number' : record.get(CONFIGNAME.get('conContact')).number,
							'name' : record.get(CONFIGNAME.get('conContact')).name,
							'idCard':record.get(CONFIGNAME.get('conContact')).idCard
						}
				};
			}
			giftArray.push(custGiftModel);
		});
		return giftArray;
	},
	// 暂存功能按钮事件
	temporaryBill : function() {
		var me = this;
		giftArray = me.productParam();
		if (giftArray.length <= 0) {
			MessageUtil.showErrorMes(i18n('i18n.IntegralRuleEdit.cannotTemporary'));
			return;
		}
		var successFn = function(json) {
			MessageUtil.showInfoMes("暂存成功");
			//关闭窗体
			me.close();
		};
		var failureFn = function(json) {
			MessageUtil.showErrorMes(json.message/*"暂存失败"*/);
		};
		recordSearchControl.temporarySaveGiftBill({
			'custGiftList' : giftArray
		}, successFn, failureFn);
	},
	// 生成清单功能按钮事件
	saveBill : function() {
		var me = this;
		var giftArray = me.productParam();
		if (giftArray.length <= 0) {
			MessageUtil.showErrorMes(i18n('i18n.IntegralRuleEdit.cannotSave'));
			return;
		}
			var failFn=function(json){
				MessageUtil.showErrorMes(json.message);
			}
//		var successFn = function(json) {
//			if (json.message == null) {
				var sucFn = function(json) {
					if (Ext.isEmpty(json.checkGiftList)) {
						MessageUtil.showInfoMes(i18n('i18n.IntegralRuleEdit.saveBillSuccess'));
						//关闭窗体
						me.close();
					}else{
						//返回的不满足条件的礼品或者联系人的集合
						var messageAry  =json.checkGiftList;
						//不符合条件的类别
						var checkType=messageAry[0];
						//如果是礼品不满足条件
						if (checkType=='gift') {
							for ( var i = 1; i < messageAry.length; i++) {
								var girdcount = 0;
								me.applyGridTitlePanel.getStore().each(function(record) {
									if (record.get(CONFIGNAME.get('custGifGift')).giftNumber==messageAry[i]) {
										var cells =  me.applyGridTitlePanel.getView().getNodes()[girdcount].children;
							               for(var k= 0;k<cells.length;k++){
							            	  cells[k].style.backgroundColor='#FF0000';
							               }
							               girdcount++;
									}
								})
							}
							//联系人积分不满足条件ew
						}else if (checkType=='integral') {
							for ( var i = 1; i < messageAry.length; i++) {
								var girdcount = 0;
								me.applyGridTitlePanel.getStore().each(function(record) {
									if (record.get(CONFIGNAME.get('conContact')).id==messageAry[i]) {
										var cells =  me.applyGridTitlePanel.getView().getNodes()[girdcount].children;
							               for(var k= 0;k<cells.length;k++){
							            	  cells[k].style.backgroundColor='#FFFF00';
							               }
							               girdcount++;
									}
								})
							}
						}
						//提示不满足条件
						MessageUtil.showErrorMes(i18n('i18n.IntegralRuleEdit.giftOrContactIntegNotStatified'));
					}
				};
				recordSearchControl.saveGiftBill({
					'custGiftList' : giftArray
				}, sucFn, failFn);
//			} else {
//				DpUtil.showMessage(json.message);
//			}
//		};
//		recordSearchControl.checkGiftBillAviable({
//			'custGiftList' : giftArray//,'contactNo' : contactNo
//		}, successFn, failFn);
	},
	// 取消功能按钮事件
	cancelBill : function() {
		var me = this;
		var giftArray = me.applyGridTitlePanel.getStore().data;
		if (giftArray.length > 0) {
			MessageUtil.showQuestionMes(i18n('i18n.IntegralRuleEdit.confirmCancel'),function(e){
				if (e=='yes') {
					me.close();
				}
			});
		} else {
			me.close();
		}
	}
});

