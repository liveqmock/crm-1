//特殊大客户申请条件判断完后，是否给予提示
var isSpecialApplyTip = false;
// 准入、退出、特殊大客户工作流window
Ext.define('WorkflowInfoWindow', {
	extend : 'PopWindow',
	id : 'WorkflowInfoWindow',
	applyType : null,
	width : 750,
	resizable : false,
	height : 325,
	closeAction : 'hide',
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	initComponent : function() {
		var me = this;
		me.items = me.getItems();
		this.callParent();
		if (me.wolkflowType == 'in') {
			Ext.getCmp('workflowFrom').getForm().findField('applicationReason').fieldLabel = '准入申请理由';
			me.setTitle('大客户准入工作流申请');
		} else if (me.wolkflowType == 'out') {
			Ext.getCmp('workflowFrom').getForm().findField('applicationReason').fieldLabel = '保留申请理由';
			me.setTitle('大客户保留工作流申请');
		} else if (me.wolkflowType == 'special') {
			Ext.getCmp('workflowFrom').getForm().findField('applicationReason').fieldLabel = '准入申请理由';
			me.setTitle('大客户工作流申请');
		}
	},
	wolkflowType : null,// in为准入，out为保留 --必填项
	keycustomer : null,
	getItems : function() {
		var me = this;
		return [{// 准入和保留 工作流申请弹窗的form
			xtype : 'form',
			layout : {
				type : 'table',
				columns : 3
			},
			defaults : {
				labelWidth : 120,
				width : 230
			},
			id : 'workflowFrom',
			items : [{
						xtype : 'textfield',
						fieldLabel : '所属部门',
						name : 'deptName',
						disabled : true,
						allowBlank : false
					}, {
						xtype : 'membersearchcombox',
						fieldLabel : '客户编码',
						displayField : 'custNum',
						valueField : 'custNum',
						name : 'customerNum',
						forceSelection : false,
						showType : 'custNumber',
						disabled : true,
						allowBlank : false,
						setValueComeBack : function(record, records, obj) {
							Ext.getCmp('workflowFrom').getForm().reset();
							if(record.get('deptId')!=User.deptId){
								MessageUtil.showMessage('只能选择本部门客户');
								return;
							}
							var url = '../keycustomer/createKeyCustomerWorkflowInfoByCustId.action';
							var params = {
								custId : record.get('custId')
							};
							var successFn = function(json) {
								if(json.keyStatusVO.fisKeyCutomer){
									MessageUtil.showMessage('客户已经成为大客户，无法进行大客户特殊申请');
									return;
								}
								if(json.keyStatusVO.fIsInCustomerapproved){
									MessageUtil.showMessage('该客户存在审批中的客户工作流，无法进行大客户工作流申请');
									return;
								}
								if(json.keyStatusVO.fisapproved){
									MessageUtil.showMessage('该客户存在审批中的大客户工作流，无法进行大客户工作流申请');
									return;
								}
								if(json.keyStatusVO.fisListIn){
									MessageUtil.showMessage('客户存在大客户待准入列表中，无法进行大客户特殊申请');
									return;
								}else if(json.keyStatusVO.fisListOut){
									MessageUtil.showMessage('客户存在大客户预退出列表中，无法进行大客户特殊申请');
									return;
								}else if(json.keyStatusVO.fisCustExistsRepeat){
									MessageUtil.showMessage('该客户是疑似重复客户，无法进行大客户准入申请');
									return;
								}else{
									if(!json.keyStatusVO.fisAmountAchieve){
										isSpecialApplyTip = true;
									}else{
										isSpecialApplyTip = false;
									}
									Ext.getCmp('workflowFrom').getForm()
											.findField('customerNum').setValue(record
													.get('custNum'));
									Ext.getCmp('workflowFrom').getForm()
											.findField('deptName').setValue(record
													.get('deptName'));
									Ext.getCmp('workflowFrom').getForm()
											.findField('customerName').setValue(record
													.get('custName'));
									Ext.getCmp('workflowFrom').getForm()
											.findField('custDegree').setValue(record
													.get('custGrade'));
									Ext.getCmp('workflowFrom').getForm()
											.findField('contactNum').setValue(record
													.get('contactNum'));
									Ext.getCmp('workflowFrom').getForm()
											.findField('contactName').setValue(record
													.get('contactName'));
									Ext.getCmp('workflowFrom').getForm()
											.findField('custId').setValue(record
													.get('custId'));
									Ext.getCmp('workflowFrom').getForm()
											.findField('deptId').setValue(record
													.get('deptId'));
									Ext.getCmp('workflowFrom').getForm()
											.findField('workFlowType').setValue('in');
								}
								var info = {};
								if (json.info) {
									info = json.info;
								}
								Ext.getCmp('workflowFrom').getForm()
										.findField('creditTimes')
										.setValue(info.creditTimes);
								Ext.getCmp('workflowFrom').getForm()
										.findField('amountOfSignMent')
										.setValue(info.amountOfSignMent);
							}
							var failFn = function(json) {
								MessageUtil.showErrorMes(json.message);
							}
							KcUtil.requestJsonAjax(url, params, successFn,
									failFn,false);
						}
					}, {
						xtype : 'textfield',
						fieldLabel : '客户名称',
						name : 'customerName',
						disabled : true,
						allowBlank : false
					}, {
						xtype : 'combo',
						fieldLabel : '客户等级',
						name : 'custDegree',
						store : KcUtil.getStore('custDegreeStoreId', ['code',
										'codeDesc'],
								DataDictionary.MEMBER_GRADE),
						queryMode : 'local',
						displayField : 'codeDesc',
						disabled : true,
						valueField : 'code'
					}, {
						xtype : 'textfield',
						fieldLabel : '主联系人编码',
						name : 'contactNum',
						disabled : true,
						allowBlank : false
					}, {
						xtype : 'textfield',
						fieldLabel : '主联系人姓名',
						name : 'contactName',
						disabled : true,
						allowBlank : false
					}, {
						xtype : 'textfield',
						fieldLabel : '客户信用预警次数',
						name : 'creditTimes',
						disabled : true,
						allowBlank : false
					}, {
						xtype : 'textfield',
						fieldLabel : '近三个月发货金额',
						name : 'amountOfSignMent',
						disabled : true,
						allowBlank : false,
						listeners:{
							change:function(obj,newVal){
								if(Ext.isEmpty(newVal)){
									Ext.getCmp('workflowFrom').getForm()
										.findField('isSpecialKeyCustomer')
										.setValue(null);
									return;
								}
								var temp = KcUtil.decideAmountOfSignMent(newVal);
								if(temp){
									Ext.getCmp('workflowFrom').getForm()
										.findField('isSpecialKeyCustomer')
										.setValue('是');
								}else{
									Ext.getCmp('workflowFrom').getForm()
										.findField('isSpecialKeyCustomer')
										.setValue('否');
								}
							}
						}
					}, {
						xtype : 'numberfield',
						fieldLabel : '次月在德邦发货潜力',
						name : 'custPotential',
						allowDecimals : false,
						allowBlank : false,
						maxLength : 100,
						minValue : 0
					}, {
						xtype : 'combo',
						fieldLabel : '意愿程度',
						name : 'cooperate',
						allowBlank : false,
						store : KcUtil.getStore('cooperateStoreId', ['code',
										'codeDesc'],
								DataDictionary.COOPERATION_INTENTION),
						queryMode : 'local',
						displayField : 'codeDesc',
						editable : false,
						valueField : 'code'
					}, {
						xtype : 'textfield',
						fieldLabel : '是否特殊大客户',
						name : 'isSpecialKeyCustomer',
						disabled : true,
						allowBlank : false,
						colspan : 2
					}, {
						xtype : 'textarea',
						fieldLabel : '',
						name : 'applicationReason',
						allowBlank : false,
						height : 100,
						width : 460,
						maxLength : 400,
						colspan : 3
					}, {
						xtype : 'hidden',// 客户ID'
						name : 'custId',
						listeners:{
							change:function(val){
								if(val==null){
									Ext.getCmp('workflowInfoWindow360Button').setDisabled(true);
								}else{
									Ext.getCmp('workflowInfoWindow360Button').setDisabled(false);
								}
							}
						}
					}, {
						xtype : 'hidden',// 客户归属部门'
						name : 'deptId'
					}, {
						xtype : 'hidden',// 工作流类型
						name : 'workFlowType'
					}]
		}, {	// 准入和保留 工作流申请弹窗的buttons
					xtype : 'panel',
					height : 50,
					border : false,
					items : [{
						xtype : 'button',
						text : '客户360视图',
						margin : '20 0 0 0',
						id:'workflowInfoWindow360Button',
						handler : function() {
							KcUtil.cust360View(Ext.getCmp('workflowFrom')
									.getForm().findField('customerNum').getValue());
						}
					}, {
						xtype : 'button',
						text : '提交',
						margin : '20 0 0 500',
						handler : function(me) {
							var workflowform = Ext.getCmp('workflowFrom')
									.getForm();
							if (!workflowform.isValid()) {
								return;
							}
							var wolkflowType = me.up('window').wolkflowType;
							var msg = '';
							if (wolkflowType == 'in') {
								msg = '大客户准入申请提交后将产生OA工作流，请确认是否提交？';
							} else if (wolkflowType == 'out') {
								msg = '大客户保留申请提交后将产生OA工作流，请确认是否提交？';
							} else if (wolkflowType == 'special') {
								msg = '大客户审批申请提交后将产生OA工作流，请确认是否提交?'
							}
							var chooseFn = function(e) {
								if (e == 'yes') {
									var params = {
										'info' : {
											'custId' : workflowform
													.findField('custId')
													.getValue(),
											'deptId' : workflowform
													.findField('deptId')
													.getValue(),
											'workFlowType' : workflowform
													.findField('workFlowType')
													.getValue(),
											'customerNum' : workflowform
													.findField('customerNum')
													.getValue(),
											'contactNum' : workflowform
													.findField('contactNum')
													.getValue(),
											'contactName' : workflowform
													.findField('contactName')
													.getValue(),
											'customerName' : workflowform
													.findField('customerName')
													.getValue(),
											'custDegree' : workflowform
													.findField('custDegree')
													.getValue(),
											'creditTimes' : workflowform
													.findField('creditTimes')
													.getValue(),
											'amountOfSignMent' : workflowform
													.findField('amountOfSignMent')
													.getValue(),
											'custPotential' : workflowform
													.findField('custPotential')
													.getValue(),
											'cooperate' : workflowform
													.findField('cooperate')
													.getValue(),
											'isSpecialKeyCustomer' : workflowform
													.findField('isSpecialKeyCustomer')
													.getValue() == '是'
													? true
													: false,
											'applicationReason' : workflowform
													.findField('applicationReason')
													.getValue()
										}
									}
									var successFn = function(json) {
										Ext.getCmp('WorkflowInfoWindow').hide();
										MessageUtil
												.showInfoMes('工作流申请成功!<BR/>工作流号为 '
														+ json.bizCode);
										Ext.getCmp('SearchGrid').getStore().load();
									}
									var failFn = function(json) {
										MessageUtil.showErrorMes(json.message)
									}
									KcUtil.applyWorkflow(params, successFn,
											failFn,false);
								}
							}
							if(isSpecialApplyTip){
								var sepicalChooseFn = function(e){
									if(e=='yes'){
										MessageUtil.showQuestionMes(msg, chooseFn);
									}else{
										Ext.getCmp('workflowFrom').getForm().reset();
										return;
									}
								}
								MessageUtil.showQuestionMes('该客户在统计周期内不符合大客户的准入和保留工作流申请,是否进行特殊申请?', sepicalChooseFn);
							}else{
								MessageUtil.showQuestionMes(msg, chooseFn);
							}
						}
					}, {
						xtype : 'button',
						text : '取消',
						margin : '20 0 0 10',
						handler : function(me) {
							Ext.getCmp('workflowFrom').getForm().reset();
							me.up('window').hide();
						}
					}]
				}]
	}
});
/**
 * 工作流申请弹窗弹出方法
 * 
 * @param {}
 *            wolkflowType
 */
var showWorkFlowWindow = function(wolkflowType, amountOfSignMent, applyType) {
	if (Ext.getCmp('SearchGrid').getSelectionModel().getSelection().length < 1
			&& !applyType) {
		MessageUtil.showMessage('请选择一条记录');
		return;
	}
	if (Ext.getCmp('SearchGrid').getSelectionModel().getSelection().length > 1) {
		MessageUtil.showMessage('只能选择一条记录');
		return;
	}
	var msg = '';
	if (!KcUtil.verifyRole(User)) {
		msg = (wolkflowType == 'in' ? '非营业部经理,无法进行准入申请操作' : '非营业部经理,无法进行保留申请操作');
		MessageUtil.showMessage(msg);
		return;
	}
	if (Ext.getCmp('SearchGrid').getSelectionModel().getSelection().length>0
			&&Ext.getCmp('SearchGrid').getSelectionModel().getSelection()[0]
				.get('deptId') != User.deptId) {
		msg = (wolkflowType == 'in'
				? '非本部门营业部经理,无法进行准入申请操作'
				: wolkflowType=='special'
					?'非本部门营业部经理,无法进行申请操作'
					:'非本部门营业部经理,无法进行保留申请操作')
		MessageUtil.showMessage(msg);
		return;
	}
	if (Ext.getCmp('WorkflowInfoWindow')) {
		Ext.getCmp('WorkflowInfoWindow').applyType = applyType;
		Ext.getCmp('WorkflowInfoWindow').show();
	} else {
		Ext.create('WorkflowInfoWindow', {
			wolkflowType : wolkflowType,
			applyType : applyType,
			listeners : {
				beforeshow : function(me) {
					Ext.getCmp('workflowInfoWindow360Button').setDisabled(true);
					if (me.applyType) {
						Ext.getCmp('workflowFrom').getForm()
								.findField('customerNum').setDisabled(false);
					} else {
						Ext.getCmp('workflowFrom').getForm()
								.findField('customerNum').setDisabled(true);
					}
					var selectData = Ext.getCmp('SearchGrid')
							.getSelectionModel().getSelection();
					if (selectData && selectData.length > 0) {
						Ext.getCmp('workflowFrom').getForm()
								.loadRecord(selectData[0]);
						me.keycustomer = selectData[0];
					}
					Ext.getCmp('workflowFrom').getForm()
							.findField('workFlowType')
							.setValue(me.wolkflowType == 'special'
									? 'in'
									: me.wolkflowType == 'in' ? 'in' : 'out');
				},
				beforehide : function(me) {
					isSpecialApplyTip = false;
					Ext.getCmp('workflowFrom').getForm().reset();
				}
			}
		}).show();
	}
	if (amountOfSignMent) {
		Ext.getCmp('workflowFrom').getForm().findField('amountOfSignMent')
				.setValue(amountOfSignMent);
	}
}