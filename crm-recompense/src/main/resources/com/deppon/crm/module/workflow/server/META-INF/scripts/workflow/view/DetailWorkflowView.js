/**
 * 工作流详情
 */
if(!window.opener&&navigator.userAgent.indexOf("Firefox")>0){
	var plugin = document.getElementById('plugin0');
	if(! plugin.openIE){
		location.href = "../scripts/workflow/workflow/view/deppon-nopie.xpi";
	}
}
Ext.onReady(function(){
	Ext.QuickTips.init();
	var keys = [
                 'TRANSPORT_TYPE',
                 'AWARD_TYPE',
                 'AWARD_TARGET_TYPE',
                 'APPROVE_REUSLT',
                 'SERVICERECOVERY_REDUCTION_TYPE',
                 'TRANSPORT_TYPE'
    		];
	initDataDictionary(keys);
	/**
	 * 弹出框GridPanel
	 */
	var approvalInfoStore = Ext.create('ApprovalInfoStore');
	approvalInfoStore.load({params:{processInstId:getUrlVars('processInstId')}});
	definePopupGridPanel(approvalInfoStore);//定义弹出框GridPanel
	var popGridPanel =Ext.create('CommonPopupGridPanel',{
		bbar:[{
			xtype:'displayfield',   
			fieldLabel : '&nbsp;',
			id:'currentApprover',
			labelSeparator:'',
			labelWidth : 5,
			name:'currentApprover',
			width:500
		}],
			anchor:'100% 40%',
			listeners:{
				'beforerender':function(t){
					var param = {processInstId:getUrlVars('processInstId')};
					var successFn = function(json){
						Ext.getCmp('currentApprover').setValue(json.approver?json.approver:i18n('i18n.workflow.none'));		
						
					};
					var failFn = function(json){
						MessageUtil.showErrorMes(i18n('i18n.workflow.getCurrentApproverFail'));
					};
					workflowData.getCurrentApproval(param,successFn,failFn);
					}
			}
		});
	
	/**
	 * 弹出框里详情信息表单
	 */	
		
	var popDetailForm;	
	if(getUrlVars('processDefName')=='com.deppon.bpms.module.crm.bpsdesign.claims.generalclaims'){
		popDetailForm = Ext.create('CommonDetailPopForm',{anchor:'100% 60%'});//常规理赔
	}else if(getUrlVars('processDefName')=='com.deppon.bpms.module.crm.bpsdesign.financial.serviceRecovery'){
		popDetailForm = Ext.create('CommonServiceDetailPopForm',{anchor:'100% 60%'});//服务补救
	}else if(getUrlVars('processDefName')=='com.deppon.bpms.module.crm.bpsdesign.operate.multiReparation'){//多赔
		popDetailForm = Ext.create('CommonOverpayDetailPopForm',{anchor:'100% 60%'});//多赔
	}
	 popDetailForm.addListener('beforerender',popDetailForm.getRenderFun);
	 
	/**
	 * 主弹出框
	 */
	var mainPanel=Ext.create('BasicFormPanel',{
		width:820,
		height:700,
		modal:true,
		layout:'fit',
		closeAction:'hide',
		buttons:[{
			xtype:'button',    
			text:i18n('i18n.workflow.close'),
			handler:function(){
				window.close();
				if(!window.opener){
						 MessageUtil.showQuestionMes(i18n('i18n.workflow.sureCloseAll'),function(e){
						if(e == 'yes'){
							document.getElementById('plugin0').closeWin(); 
						}
					 });
				  }
			}
		}],
		
		items:[{
			xtype:'normaltabpanel',
			items:[
		      {xtype:'tabcontentpanel',title:i18n('i18n.workflow.detailInfo'),layout:'anchor',items:[popDetailForm,popGridPanel]},
		      {xtype:'tabcontentpanel',title:i18n('i18n.workflow.imageModel'),layout:'fit',items:[{	
				html: '<iframe   src="generateWorkflowImage.action?processInstId='+getUrlVars('processInstId')+'" style="height:100%;width:100%;" frameborder="0"></iframe>'
				}]}
	       ]
		}]
	});
	

	/**
	 * viewport
	 */
	Ext.create('Ext.container.Viewport', {
		layout: 'fit',
		border:false,
		items:[mainPanel]
	});
});