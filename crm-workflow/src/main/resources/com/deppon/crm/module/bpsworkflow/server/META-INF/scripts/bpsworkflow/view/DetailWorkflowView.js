/**
 * 工作流详情
 */
if (!window.opener && navigator.userAgent.indexOf("Firefox") > 0) {
	var plugin = document.getElementById('plugin0');
	if (!plugin.openIE) {
		location.href = "../scripts/workflow/workflow/view/deppon-nopie.xpi";
	}
}
Ext.onReady(function() {
	Ext.QuickTips.init();
	var keys = ['AWARD_TYPE', 
	'AWARD_TARGET_TYPE', 
	'COOPERATION_INTENTION'//意愿程度
	,'MEMBER_GRADE'//客户等级
	,'KEYCUST_WORKFLOW_STATUS'//工作流状态
	,'KEYCUST_WORKFLOW_TYPE'  	//工作流类型
	,'APPROVE_REUSLT'
    ,'TRADE'//一级行业
    ,'ACTIVITY_CATEGORY'//活动类别
    ,'ACTIVITY_TYPE'//活动类型
    ,'PREFERENTIAL_WAY'//优惠方式
    ,'SECOND_TRADE'//二级行业
    ,'DISCOUNT_PRODUCTS'////优惠产品=抵扣类型
    ,'CUST_CONDITION_STATUS'//客户群状态
    ,'LINE_TYPE'//活动线路区域要求
    ,'CLIENTBASE_PRODUCT_TYPE'//产品类型
	];
	initDataDictionary(keys);
	//初始化订单来源
    initOrderSources();
	var processDefName = getUrlVars('processDefName');
	/**
	 * 弹出框GridPanel
	 */
	var approvalInfoStore = Ext.create('ApprovalInfoStore');
	approvalInfoStore.load({
				params : {
					processInstId : getUrlVars('processInstId')
				}
			});
	definePopupGridPanel(approvalInfoStore);// 定义弹出框GridPanel
	var popGridPanel = Ext.create('CommonPopupGridPanel', {
		autoScroll : true,
		width : 900,
		margin : '0 0 0 4',
		flex:1,
		bbar : [{
					xtype : 'displayfield',
					fieldLabel : '&nbsp;',
					id : 'currentApprover',
					labelSeparator : '',
					labelWidth : 15,
					name : 'currentApprover',
					width : 750
				}],
		// anchor:'100% 40%',
		listeners : {
			'afterrender' : function(t) {
				var param = {
					processInstId : getUrlVars('processInstId')
				};
				var successFn = function(json) {
					Ext.getCmp('currentApprover').setValue(json.approver
							? json.approver
							: i18n('i18n.workflow.none'));
					// 修复DEFECT-1062
					popGridPanel.doLayout();
				};
				var failFn = function(json) {
					MessageUtil
							.showErrorMes(i18n('i18n.workflow.getCurrentApproverFail'));
				};
				workflowData.getCurrentApproval(param, successFn, failFn);
			}
		}
	});

	/**
	 * 弹出框里详情信息表单
	 */
	if(processDefName =='com.deppon.bpms.module.crm.bpsdesign.administLegal.monCustSignAppLTL'
	||processDefName =='com.deppon.bpms.module.crm.bpsdesign.administLegal.monCustSignAppExpress'
	||processDefName =='com.deppon.bpms.module.crm.bpsdesign.administLegal.monCustSignEidtLTL'
	||processDefName =='com.deppon.bpms.module.crm.bpsdesign.administLegal.monCustSignEidtExpress'
	||processDefName =='com.deppon.bpms.module.crm.bpsdesign.administLegal.monCustTermination'){
		var popDetailForm = Ext.create('CommonDetailPopForm');//打开合同工作流详情页面
	}else if(processDefName =='com.deppon.bpms.module.crm.bpsdesign.customer.customerAccessAndSave'){
		var popDetailForm = Ext.create('BigCustomerDetailForm');//打开大客户工作流详情页面
	}else if(processDefName =='com.deppon.bpms.module.crm.bpsdesign.customer.repeatedCostomer'){
		var popDetailForm = Ext.create('RepetitveCustWorkFlowDetailForm');//打开疑似重复客户工作流详情页面
	}else if(processDefName =='com.deppon.bpms.module.crm.bpsdesign.customer.areaMarketingActivities'){
		var popDetailForm = Ext.create('MarketActivityDetailForm');//打开区域营销活动工作流详情页面
	}else if(getUrlVars('processDefName')=='com.deppon.bpms.module.crm.bpsdesign.claims.generalclaims'){
		popDetailForm = Ext.create('CommonRecompensePopForm',{anchor:'100% 60%'});//常规理赔
	}else if(getUrlVars('processDefName')=='com.deppon.bpms.module.crm.bpsdesign.financial.serviceRecovery'){
		popDetailForm = Ext.create('CommonServiceDetailPopForm',{anchor:'100% 60%'});//服务补救
	}else if(getUrlVars('processDefName')=='com.deppon.bpms.module.crm.bpsdesign.operate.multiReparation'){//多赔
		popDetailForm = Ext.create('CommonOverpayDetailPopForm',{anchor:'100% 60%'});//多赔
	}
	

	popDetailForm.addListener('beforerender', popDetailForm.getRenderFun);

	/**
	 * 主弹出框
	 */
	var mainPanel = Ext.create('BasicFormPanel', {
		width : 900,
		modal : true,
		// layout:'fit',
		closeAction : 'hide',
		buttons : [{
			xtype : 'button',
			text : i18n('i18n.workflow.close'),
			handler : function() {
				window.close();
				if (!window.opener) {
					MessageUtil.showQuestionMes(
							i18n('i18n.workflow.sureCloseAll'), function(e) {
								if (e == 'yes') {
									document.getElementById('plugin0')
											.closeWin();
								}
							});
				}
			}
		}],
		items : [{
			xtype : 'normaltabpanel',
			items : [{
						xtype : 'tabcontentpanel',
						autoScroll : true,
						title : i18n('i18n.workflow.detailInfo'),
						layout : {
							type : 'table',
							columns : 1
						},
						items : [popDetailForm, popGridPanel]
					}, {
						xtype : 'tabcontentpanel',
						title : i18n('i18n.workflow.imageModel'),
						layout : 'fit',
						items : [{
							html : '<iframe   src="generateWorkflowImage.action?processInstId='
									+ getUrlVars('processInstId')
									+ '" style="height:100%;width:100%;" frameborder="0"></iframe>'
						}]
					}]
		}]
	});

	
	
	/**
	 * viewport
	 */
	Ext.create('Ext.container.Viewport', {
				layout : 'fit',
				border : false,
				items : [mainPanel]
			});
});