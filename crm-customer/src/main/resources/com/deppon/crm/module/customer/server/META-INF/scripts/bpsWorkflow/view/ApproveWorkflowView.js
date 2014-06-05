/**
 * 工作流审批
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
	             'APPROVE_REUSLT',
	             'AWARD_TARGET_TYPE',
                 'AWARD_TYPE'
			];
	initDataDictionary(keys);
	initDeptAndUserInfo();
	/**
	 * 弹出框查询表单
	 */
	Ext.define('CommonPopApproveForm',{
		extend:'PopTitleFormPanel',
		title:i18n('i18n.workflow.approveArea'),
		frame:true,
		cls:'appWorkflow',
		width:900,
		defaultType:'textfield',
		defaults:{
			labelAlign:'right'	
		}
		,items:[{
			xtype: 'radiogroup',
			name:'approveOperateTypeGroup',
	        fieldLabel: i18n('i18n.workflow.yourChoose'),
	        width:300,
	        allowBlank:false,
	        blankText : i18n('i18n.workflow.mustChooseOne'),
	        items: [
	            {boxLabel: i18n('i18n.workflow.agree'), name: 'approveOperateType', inputValue: 0},
	            {boxLabel: i18n('i18n.workflow.disagree'), name: 'approveOperateType', inputValue: 1}
	        ]
		},
		{
			fieldLabel:i18n('i18n.workflow.approvever'),
			xtype: 'textarea',
			maxLength:1000,
			name:'approveOpinion',
			width:600,
			height: 50
		},{
			xtype: 'displayfield',
			fieldLabel :i18n('i18n.workflow.approver'),
			value:User.empName
		}			
		]
		
	});
	var popApproveForm = Ext.create('CommonPopApproveForm');
	
	/**
	 * 弹出框GridPanel
	 */
	var approvalInfoStore = Ext.create('ApprovalInfoStore');
	approvalInfoStore.load({params:{processInstId:getUrlVars('processInstId')}});
	definePopupGridPanel(approvalInfoStore);//定义弹出框GridPanel
	var popGridPanel =Ext.create('CommonPopupGridPanel',{
		autoScroll : true,
		width:900,
		margin:'0 0 0 4'
	});
	
	/**
	 * 弹出框里详情信息表单
	 */	
	 var popDetailForm = Ext.create('CommonDetailPopForm',{flex:1.5});
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
		tbar:[{
			xtype:'button',   
			name:'serachLogBtn',
			text:i18n('i18n.workflow.submit'),
			handler:function(t){
				if(popApproveForm.getForm().findField('approveOperateTypeGroup').getChecked().length==0){
					MessageUtil.showErrorMes(i18n('i18n.workflow.pleaseChooseYourDecision'));
					return;
				}
				if(!popApproveForm.getForm().isValid()){
					return;
				}
				
					doSubmit();
				
				
				function doSubmit(){
					t.setDisabled(true);
					var successFn = function(json){
						t.setDisabled(false);
						if(window.opener&&window.opener.Ext&&window.opener.Ext.getCmp('toHandleGrid')){
							window.opener.Ext.getCmp('toHandleGrid').getStore().load();
						}
						MessageUtil.showInfoMes(i18n('i18n.workflow.approveSuccess'),function(){
						window.close();
						if(!window.opener){
							 MessageUtil.showQuestionMes(i18n('i18n.workflow.sureCloseAll'),function(e){
							if(e == 'yes'){
								document.getElementById('plugin0').closeWin(); 
							}
						 });
					  }
						});
					},
					failureFn = function(json){
						t.setDisabled(false);
						if(json.message){
							MessageUtil.showErrorMes(json.message);	
						}else{
							MessageUtil.showErrorMes(i18n('i18n.workflow.serviceMoreTime'));
						}
					};
					var params=popApproveForm.getForm().getValues();
					params.workItemIdEnc=getUrlVars('workItemId');
					params.processInstIdEnc=getUrlVars('processInstId');
					params.busino=getUrlVars('busino');
					workflowData.workflowApprove({workflowApprove:params},successFn,failureFn);
				}
				
			}
		},{
			xtype:'button',    
			name:'ResetLogBtn',
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
		      {xtype:'tabcontentpanel',autoScroll : true,title:i18n('i18n.workflow.approveWorkflow'),layout:{
					type:'table',
					columns:1
				},items:[popApproveForm,popGridPanel,popDetailForm]},
		      {xtype:'tabcontentpanel',title:i18n('i18n.workflow.workflowImage'),layout:'fit',items:[{	
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
		items:[mainPanel]
	});
});