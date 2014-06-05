var prefrentialDealControl = (CONFIG.get('TEST')) ? Ext
		.create('ContractBasicDataTest') : Ext.create('PrefrentialDealData');
Ext.onReady(function() {
	var preType=(window.location.href.split('/')[5])=='monthSendRateManage.action'?'MONTH_SEND':'MONTH_REBATE';
	Ext.define('prefrentialDealEditWin', {
		extend : 'PopWindow',
		width : 580,
		y : 30,// 距离界面顶层 距离
		height :400,
		btnEnabled:null,
		readOnly:null,
		parent:null,
		title : '运费折扣基础资料编辑',
		prefrentialDealControl : null,// 合同contractViewview
		prefrentialDealData : null,// 合同contractViewview
		layout : {
			type : 'fit'
		},
		initComponent : function() {
			var me = this;
			me.prefrentialDealControl = prefrentialDealControl;
			me.prefrentialDealViewPanel = Ext.create('prefrentialDealViewPanel', {
				'parent' : me.parent,
				'parentWin':me,
				'readOnly':me.readOnly,
				'btnEnabled':me.btnEnabled,
				'beforeChangeData':me.beforeChangeData,
				'prefrentialDealControl':me.prefrentialDealControl,
				'prefrentialDealData' : me.prefrentialDealData
			});
			me.items = [ me.prefrentialDealViewPanel ];
			this.callParent();
		}
	});
	
	/**
	 * 优惠方案编辑主页面
	 */
	Ext
			.define(
					'prefrentialDealViewPanel',
					{
						extend : 'BasicPanel',
						prefrentialBasicPanel : null,
						prefrentialItemPanel : null,
						prefrentialDealData : null,
						prefrentialDealControl:null,
						beforeChangeData:null,
						btnEnabled:null,
						parentWin:null,
						items : null,
						readOnly:null,
						layout : {
							type : 'vbox',
							align : 'stretch'
						},
						initComponent : function() {
							var me = this;
							me.items = me.getItems();
							me.fbar = me.getFbar();
							this.callParent();
						},
						getItems : function() {
							var me = this;
							me.prefrentialBasicPanel = Ext
									.create('prefrentialBasicFormPanel',{
										'prefrentialDealData' : me.prefrentialDealData,
										'btnEnabled':me.btnEnabled,
										'readOnly':me.readOnly,
										'prefrentialDealControl':me.prefrentialDealControl
									});
							me.prefrentialItemPanel = Ext
									.create(
											'prefrentialItemPanel',
											{
												'btnEnabled':me.btnEnabled,
												'readOnly':me.readOnly,
												'prefrentialDealData' : me.prefrentialDealData,
												'prefrentialDealControl':me.prefrentialDealControl
											});
							return [ me.prefrentialBasicPanel,
									me.prefrentialItemPanel ];
						},
						/**
						 * 保存优惠方案
						 */
						addPrefrentialDeal : function(button) {
							var me = this;
							MessageUtil.showQuestionMes('是否确认提交？',function(e){
								if (e == 'no') {
									return;
								}
								button.setDisabled(true);
								var baseForm = me.down('form');
								if (!baseForm.getForm().isValid()) {
									button.setDisabled(false);
									return;
								}
									var prefrentialDealModel = Ext
											.create('PrefrentialDealModel');
									// 将表单的数据load到model
									baseForm.getForm().updateRecord(
											prefrentialDealModel);
									// grid store中的数据
									var items = me.down('grid').getStore().data.items;
									// 遍历store的数据封装方案条目
									var dealItems = new Array();
									for ( var k = 0; k < items.length; k++) {
										dealItems.push(items[k].data);
									}
									var param = {
										'prefrentialDeal' : prefrentialDealModel.data
									};
									// 设置方案条目信息
									param.prefrentialDeal.dealItems = dealItems;
									var successFn = function(response) {
										MessageUtil.showInfoMes('保存成功!');
										button.setDisabled(true);
										me.parent.down('grid').getStore().loadPage(1);
										me.parentWin.close();
									}
									var failureFn = function(response) {
										MessageUtil.showErrorMes(response.message);
										button.setDisabled(false);
									}
									if (me.readOnly) {
										var  afterData = Ext.create('PrefrentialDealModel');
										me.parentWin.down('form').getForm().updateRecord(afterData);
										var beforeData = me.beforeChangeData;
										var afterItems = me.parentWin.down('grid').getStore().data.items;
										if(!me.validateChangeData(beforeData,afterData.data,afterItems)){
											MessageUtil.showErrorMes('只能修改条目折扣！');
											button.setDisabled(false);
											return;
										}
										if (me.validateRateNotChange(beforeData.dealItems,afterItems)) {
											MessageUtil.showErrorMes('方案未修改折扣！');
											button.setDisabled(false);
											return;
										}
									}
									if (me.validateData(prefrentialDealModel)) {
										me.prefrentialDealControl.savePrefrentialDeal(
												param, successFn, failureFn);
									}else{
										button.setDisabled(false);
									}
							},null);
						},
						//校验修改前后每个方案条目折扣率不都一样
						validateRateNotChange:function(beforeItems,afterItems){
							for ( var i = 0; i < beforeItems.length; i++) {
								for ( var j = 0; j < afterItems.length; j++) {
									if (beforeItems[i].id==afterItems[j].get('id')
											&&beforeItems[i].rate!=afterItems[j].get('rate')) {
										return false;
									}
								}
							}
							return true;
						},
						validateChangeData:function(beforeData,afterData,afterItems){
							var me =this;
	//						if (beforeData.beginTime!=afterData.beginTime.getTime()) {
	//							return false;
	//						}
	//						if (beforeData.endTime!=afterData.endTime.getTime()) {
	//							return false;
	//						}
							if (beforeData.dealName!=afterData.dealName) {
								return false;
							}
							
							var beforeItems = beforeData.dealItems;
							if (beforeItems.length!=afterItems.length) {
								return false;
							}
							for ( var i = 0; i < beforeItems.length; i++) {
								for ( var j = 0; j < afterItems.length; j++) {
									if (beforeItems[i].id==afterItems[j].get('id')) {
										if (beforeItems[i].degree!=afterItems[j].get('degree')) {
											return false;
										}else if(beforeItems[i].minAmount!=afterItems[j].get('minAmount')) {
											return false;
										}else if(beforeItems[i].maxAmount!=afterItems[j].get('maxAmount')) {
											return false;
										}else if(beforeItems[i].itemDesc!=afterItems[j].get('itemDesc')) {
											return false;
										}
									}
								}
							}
							return true;
						},
						validateData : function(prefrentialDealModel) {
							if (prefrentialDealModel.get('beginTime')>=prefrentialDealModel.get('endTime')) {
								MessageUtil.showErrorMes('生效时间必须小于失效时间!');
								return false;
							}
							var dealItems = prefrentialDealModel.get('dealItems');
							if (Ext.isEmpty(dealItems)||0>=dealItems.length) {
								MessageUtil.showErrorMes('方案条目不能为空!');
								return false;
							}
							for ( var i = 0; i < dealItems.length; i++) {
								/**
								 * 校验字段非空
								 */
								if (Ext.isEmpty(dealItems[i].degree)) {
									MessageUtil.showErrorMes('条目' + (i + 1)
											+ '等级不能为空!');
									return false;
								}
								if (Ext.isEmpty(dealItems[i].minAmount)) {
									MessageUtil.showErrorMes('条目' + (i + 1)
											+ '最小金额不能为空!');
									return false;
								}
								if (Ext.isEmpty(dealItems[i].maxAmount)) {
									MessageUtil.showErrorMes('条目' + (i + 1)
											+ '最大金额不能为空!');
									return false;
								}
								if (Ext.isEmpty(dealItems[i].rate)) {
									MessageUtil.showErrorMes('条目' + (i + 1)
											+ '运费折扣不能为空!');
									return false;
								}
								if (Ext.isEmpty(dealItems[i].itemDesc)) {
									MessageUtil.showErrorMes('条目' + (i + 1)
											+ '描述不能为空!');
									return false;
								}
								if (dealItems[i].minAmount >= dealItems[i].maxAmount) {
									MessageUtil.showErrorMes('条目' + (i + 1)
											+ '最小金额必须小于最大金额，请修改后提交');
									return false;
								}
								for ( var j = i + 1; j < dealItems.length; j++) {
									if (dealItems[i].degree == dealItems[j].degree) {
										MessageUtil.showErrorMes('条目' + (i + 1)
												+ '与条目' + (j + 1) + '等级重复!');
										return false;
									}
									if ((dealItems[i].minAmount >= dealItems[j].minAmount
											&& dealItems[i].maxAmount >= dealItems[j].maxAmount && dealItems[i].minAmount <= dealItems[j].maxAmount)
											|| (dealItems[i].minAmount <= dealItems[j].minAmount
													&& dealItems[i].maxAmount <= dealItems[j].maxAmount && dealItems[i].maxAmount >= dealItems[j].minAmount)
											|| (dealItems[i].minAmount >= dealItems[j].minAmount && dealItems[i].maxAmount <= dealItems[j].maxAmount)
											|| (dealItems[i].minAmount <= dealItems[j].minAmount && dealItems[i].maxAmount >= dealItems[j].maxAmount)) {
										MessageUtil.showErrorMes('方案之间条目金额不能交叉!');
										return false;
									}
								}
							}
							return true;
						},
						getFbar : function() {
							var me = this;
							return [
									{
										xtype : 'button',
										disabled:!me.btnEnabled,
										id : 'contractSaveButton',
										text : i18n('i18n.MemberCustEditView.submit'),
										scope : me,
										handler : me.addPrefrentialDeal
									},
									{
										xtype : 'button',
										disabled:!me.btnEnabled,
										id : 'contractCancelButton_id',
										text : i18n('i18n.ManualRewardIntegralEditView.b_cance'),
										handler : function() {
											if (me.parentWin != null) {
												me.parentWin.close();
											}
										}
									} ];
						}
					});
	
	Ext.define('prefrentialBasicFormPanel', {
		extend : 'PopTitleFormPanel',
		height : 100,
		prefrentialDealData:null,
		prefrentialDealControl:null,
		initComponent : function() {
			var me = this;
			me.items = me.getItem();
			this.callParent();
			if (!Ext.isEmpty(me.prefrentialDealData)) {
				var model = Ext.create('PrefrentialDealModel',me.prefrentialDealData);
				me.loadRecord(model);
			}
			if (me.readOnly) {
				me.getForm().findField('beginTime').setMinValue(null);
				me.getForm().findField('beginTime').clearInvalid();
			}
		},
		getItem : function() {
			var me = this;
			return [ {
				xtype : 'fieldset',
				title : '方案基本信息',
				layout : {
					type : 'table',
					columns : 2
				},
				items : [ {
					name : 'id',
					xtype : 'dptextfield',
					colspan : 2,
					hidden : true
				}, {
					margin : '10 0 5 0',
					colspan : 2,
					width : 510,
					disabled : !me.btnEnabled,
					fieldLabel : '方案名称',
					readOnly : me.readOnly,
					xtype : 'dptextfield',
					maxLength : 100,
					allowBlank : false,
					maxLengthText : '方案名称不能超过100字，请删减',
					name : 'dealName'
				}, {
					fieldLabel : '生效时间',
					xtype : 'dpdatefield',
					minValue : new Date(),
					allowBlank : false,
					readOnly : me.readOnly,
					disabled : !me.btnEnabled,
					maxValue:new Date(2999, 11, 31,23,59,59),
					format : 'Y-m-d',
					name : 'beginTime',
					listener : {
						blur : function(field, obj) {
							if (field.getValue() >= new Date(2999, 11, 31)) {
								field.setValue(null);
							}
						}
					}
				}, {
					fieldLabel : '失效时间',
					xtype : 'dpdatefield',
					value : new Date(2999, 11, 31),
					format : 'Y-m-d',
					allowBlank : false,
					readOnly : true,
					name : 'endTime'
				}, {
					fieldLabel : '折扣类型',
					xtype : 'textfield',
					value : preType,
					allowBlank : false,
					hidden : true,
					name : 'preferType'
				} ]
			} ];
		}
	});
	
	Ext.define('prefrentialItemPanel', {
		extend : 'PopupGridPanel',
		columns : null,
		height : 200,
		store : null,
		btnEnabled:null,
		readOnly:null,
		prefrentialDealData : null,
		prefrentialDealControl:null,
		cellEditing : null,
		getTbar : function() {
			var me = this;
			var store = me.store;
			return [ {
				text : '+',
				disabled:(!me.btnEnabled||me.readOnly),
				handler : function() {
					// Create a model instance
					var r = Ext.create('PrefrentialDealItemModel', {
						id : '',
						dealId : '',
						degree : '',
						minAmount : 0,
						maxAmount : 0,
						rate : 1,
						itemDesc : ''
					});
					me.store.insert(store.data.length, r);
				}
			}, {
				text : '-',
				disabled:(!me.btnEnabled||me.readOnly),
				handler : function() {
					var row = me.getSelectionModel().getSelection();
					me.store.remove(row);
				}
			} ]
		},
		initComponent : function() {
			var me = this;
			me.columns = me.getColumns();
	//		me.selModel = Ext.create('Ext.selection.CheckboxModel', {
	//			mode : 'SINGLE'
	//		});
			me.cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
				clicksToEdit : 2
			});
			me.store = prefrentialDealControl.getPrefrentialDealItemStore();
			me.tbar = me.getTbar();
			me.plugins = [ me.cellEditing ];
			this.callParent();
			me.store.removeAll();
			if (!Ext.isEmpty(me.prefrentialDealData)&&!Ext.isEmpty(me.prefrentialDealData.dealItems)) {
				me.store.loadData(me.prefrentialDealData.dealItems);
			}
			if (!me.btnEnabled) {
				me.plugins = null;
			}
		},
		getColumns : function() {
			var me = this;
			return [ {
				xtype : 'rownumberer',
				width : 40,
				text : '序号'
			}, {
				text : 'id',
				dataIndex : 'id',
				hidden : true
			}, {
				text : i18n('i18n.ContractEditView.contractId'),
				dataIndex : 'dealId',
				hidden : true
			}, {
				text : '等级',
				width : 70,
				allowBlank : false,
				dataIndex : 'degree',
				editor : {
					maxLength : 9,
					xtype : 'numberfield',
					decimalPrecision : 0,
					minValue:0,
					hideTrigger : true,
					id : 'degree_id',
					allowBlank : false,
					disabled:(!me.btnEnabled||me.readOnly)
				}
			}, {
				text : '最小金额',
				width : 70,
				dataIndex : 'minAmount',
				allowBlank : false,
				editor : {
					xtype : 'numberfield',
					id : 'minAmount_id',
					minValue : 0,
					maxLength : 9,
					decimalPrecision : 0,
					hideTrigger : true,
					allowBlank : false,
					disabled:(!me.btnEnabled||me.readOnly)
				}
			}, {
				text : '最大金额',
				allowBlank : false,
				dataIndex : 'maxAmount',
				width : 70,
				editor : {
					xtype : 'numberfield',
					id : 'maxAmount_id',
					minValue : 0,
					maxLength : 9,
					decimalPrecision : 0,
					hideTrigger : true,
					allowBlank : false,
					disabled:(!me.btnEnabled||me.readOnly)
				}
			}, {
				text : '运费折扣',
				dataIndex : 'rate',
				width : 70,
				allowBlank : false,
				editor : {
					xtype : 'numberfield',
					minValue : 0.01,
					maxLength : 4,
					maxValue : 1,
					decimalPrecision : 2,
					hideTrigger : true,
					allowBlank : false
				}
			}, {
				text : '描述',
				dataIndex : 'itemDesc',
				flex : 1,
				allowBlank : false,
				editor : {
					maxLength : 100,
					id : 'description_id',
					hideTrigger : true,
					allowBlank : false,
					disabled:(!me.btnEnabled||me.readOnly)
				}
			} ];
		}
	});
	
	Ext.define('PrefrentialDealViewWin', {
		extend : 'BasicPanel',
		parent : null,
		prefrentialDealResultGrid : null,
		prefrentialDealButtonPanel : null,
		prefrentialDealControl:null,
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		initComponent : function() {
			var me = this;
			me.prefrentialDealResultGrid = Ext.create('prefrentialDealResultGrid',
					{
						'parent' : me,
						'prefrentialDealData' : me.prefrentialDealData,
						'prefrentialDealControl':me.prefrentialDealControl
					});
			me.prefrentialDealButtonPanel = Ext.create(
					'prefrentialDealButtonPanel', {
						'parent' : me,
						'prefrentialDealData' : me.prefrentialDealData,
						'prefrentialDealControl':me.prefrentialDealControl
					});
			me.items = [ me.prefrentialDealButtonPanel,me.prefrentialDealResultGrid
			];
			this.callParent();
		}
	});
	
	Ext.define('prefrentialDealButtonPanel',
			{
				extend : 'NormalButtonPanel',
				prefrentialDealControl : null,
				parent : null,
				initComponent : function() {
					this.items = this.getItems();
					this.callParent();
				},
				getItems : function() {
					var me = this;
					return [ {
						xtype : 'leftbuttonpanel',
						width : 400,
						defaultType : 'button',
						defaults : {
							scope : me,
							margins : '0 5 0 0'
						},
						items : [ {
							text : '新增',
							handler : me.addPrefrentialDeal
						}, {
							text : '修改',
							handler : me.updatePrefrentialDeal
						}, {
							text : '删除',
							handler : me.deletePrefrentialDeal
						}, {
							text : '激活',
							handler : me.activePrefrentialDeal
						}, {
							text : '查看详情',
							handler : me.viewPrefrentialDeal
						} ]
					} ];
				},
				getSelectedRow:function(){
					var me = this;
					var rows = me.up('panel').down('grid').getSelectionModel().getSelection();
					if (Ext.isEmpty(rows)) {
						MessageUtil.showErrorMes('请选择一条数据再进行操作！');
					}
					return rows;
				},
				// 查看详情
				viewPrefrentialDeal : function() {
					var me = this;
						var prefrentialDealModel = me.getSelectedRow()[0];
						var param = {
							'prefrentialDeal' : prefrentialDealModel.data
						};
						var successFn = function(response) {
							var preDealModel = Ext.create('PrefrentialDealModel',response.prefrentialDeal);
							Ext.create('prefrentialDealEditWin',{
								'prefrentialDealData':response.prefrentialDeal,
								'btnEnabled':false
							}).show();
						}
						var failureFn = function(response) {
							MessageUtil.showErrorMes(response.message);
						}
							me.prefrentialDealControl.viewPrefrentialDeal(
									param, successFn, failureFn);
				
				},
				// 删除
				deletePrefrentialDeal : function() {
					var me = this;
					var prefrentialDealModel = me.getSelectedRow()[0];
					if (prefrentialDealModel.get('status')!='3') {
						MessageUtil.showErrorMes('该套月发月送运费折扣基础资料不能删除，只有待生效的折扣可以删除!');
						return;
					}
					MessageUtil.showQuestionMes('删除后将无法使用该套折扣，是否确认删除',function(e){
						if (e == 'no') {
							return;
						}
						var param = {
								'prefrentialDeal' : prefrentialDealModel.data
						};
						var successFn = function(response) {
							MessageUtil.showInfoMes('删除成功!');
							me.parent.down('grid').getStore().loadPage(1);
						}
						var failureFn = function(response) {
							MessageUtil.showErrorMes(response.message);
						}
						me.prefrentialDealControl.deletePrefrentialDeal(
								param, successFn, failureFn);
					},null);
				},
				// 激活
				activePrefrentialDeal : function() {
					var me = this;
					var prefrentialDealModel = me.getSelectedRow()[0];
					if (prefrentialDealModel.get('status')!='3') {
						MessageUtil.showErrorMes('只能激活待生效方案!');
						return;
					}
					MessageUtil.showQuestionMes('激活后，该套运费折扣基础资料立即生效，确认是否激活？',function(e){
						if (e == 'no') {
							return;
						}
						var param = {
								'prefrentialDeal' : prefrentialDealModel.data
						};
						var successFn = function(response) {
							MessageUtil.showInfoMes('激活成功!');
							me.parent.down('grid').getStore().loadPage(1);
						}
						var failureFn = function(response) {
							MessageUtil.showErrorMes(response.message);
						}
						me.prefrentialDealControl.activePrefrentialDeal(
								param, successFn, failureFn);
					},null);
				},
				// 新增
				addPrefrentialDeal : function() {
					var me = this;
					Ext.create('prefrentialDealEditWin',{
						'btnEnabled':true,
						'parent':me.parent,
						'prefrentialDealData':null
					}).show();
				},
				// 修改
				updatePrefrentialDeal : function(button) {
					var me = this;
						var prefrentialDealModel = me.getSelectedRow()[0];
						if (prefrentialDealModel.get('status')!='1') {
							MessageUtil.showErrorMes('只能修改有效的方案!');
							return;
						}
						var param = {
							'prefrentialDeal' : prefrentialDealModel.data
						};
						var successFn = function(response) {
							var preDealModel = Ext.create('PrefrentialDealModel',response.prefrentialDeal);
							Ext.create('prefrentialDealEditWin',{
								'prefrentialDealData':response.prefrentialDeal,
								'parentWin':me.parent,
								'parent':me.parent,
								'beforeChangeData':response.prefrentialDeal,
								'btnEnabled':true,
								'readOnly':true
							}).show();
						}
						var failureFn = function(response) {
							MessageUtil.showErrorMes(response.message);
						}
							me.prefrentialDealControl.viewPrefrentialDeal(
									param, successFn, failureFn);
				}
			});
	Ext.define('prefrentialDealResultGrid', {
		extend : 'SearchGridPanel',
		flex:1,
		prefrentialDealControl : null,// 数据Data
		initComponent : function() {
			var me = this;
			me.store = me.prefrentialDealControl.initPrefrentialDealStore();
			
			me.store.addListener('beforeload',function(store,operation,eOpts){
				var searchParams={'prefrentialDeal.preferType':preType};
				
				Ext.apply(operation,{
					params : searchParams
					}
				);	
			});
			me.selModel = Ext.create('Ext.selection.CheckboxModel', {
				mode : 'SINGLE'
			});
			me.dockedItems = me.getMyDockedItems();
			me.columns = me.getColumns();
			this.callParent();
		},
		listeners:{
			itemdblclick:function(ths,record,item,index,e,opts){
				var me =this;
				me.up('basicpanel').down('normalbuttonpanel').viewPrefrentialDeal();
			}
		},
		// 分页条
		getMyDockedItems : function() {
			var me = this;
			return [ {
				xtype : 'pagingtoolbar',
				store : me.store,
				dock : 'bottom',
				displayInfo : true
			} ];
		},
		getColumns : function() {
			var me = this;
			return [
					{
						xtype : 'rownumberer',
						width : 40,
						text : '序号'
					},
					{
						header : '编码',
						width : 65,
						dataIndex : 'dealNumber'
					},
					{
						header : '方案名称',
						width : 130,
						dataIndex : 'dealName'
					},
					{
						header : '状态',
						width : 60,
						dataIndex : 'status',
						renderer : function(value) {
							return DpUtil.changeDictionaryCodeToDescrip(value,
									DataDictionary.CONTACT_STATUS);
						}
					}, {
						header : '生效时间',
						width : 135,
						dataIndex : 'beginTime',
						renderer : DpUtil.renderTime
					}, {
						header : '失效时间',
						width : 135,
						dataIndex : 'endTime',
						renderer : DpUtil.renderTime
					}, {
						header : '创建时间',
						width : 135,
						dataIndex : 'createDate',
						renderer : DpUtil.renderTime
					}, {
						header : '创建人',
						width : 80,
						dataIndex : 'createUser'
					} ]
		}
	});

	var params = [ 'CONTACT_STATUS' ];
	initDataDictionary(params);// 数据字典
	var commonWin = Ext.create('Ext.container.Viewport', {
		layout : 'fit',
		items : [ Ext.create('PrefrentialDealViewWin', {
			'prefrentialDealControl' : prefrentialDealControl
		}) ]
	});

});
