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
	var keys = ['AWARD_TYPE', 'AWARD_TARGET_TYPE', 'APPROVE_REUSLT'];
	initDataDictionary(keys);
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
	var popDetailForm = Ext.create('CommonDetailPopForm');

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