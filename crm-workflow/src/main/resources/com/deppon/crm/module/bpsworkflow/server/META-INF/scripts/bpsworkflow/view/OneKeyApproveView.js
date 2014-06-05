
 
 Ext.onReady(function(){

	var  keys = [
	                 'CLAIM_WORKFLOW_STATE',
	                 'WORKFLOW_TYPE'
	    		];

	initDataDictionary(keys);
	var temp = null; 
	Ext.define('OneKeyQueryForm',{
	extend:'SearchFormPanel',
	frame:true,
	parent : null,
	margin:'3 0 0 0',
	layout:{
		type:'table',
		columns:3
	}
	,defaultType:'textfield',
	
	defaults:{
		width:210,
		labelWidth : 65,
		labelAlign:'right'	
	}
	,items:[],
	listeners:{
			'afterrender':function(t){
				var form = this.getForm();
				var agreeBtn = buttonDemoPanel.down('button[name=agreeBtn]');
				var disAgreeBtn = buttonDemoPanel.down('button[name=disAgreeBtn]')
				t.add({
					id : 'workflow_type' ,
					fieldLabel :i18n('i18n.workflow.name'),
					name : 'type',
					xtype : 'combobox',
					value:'CONTRACT_LTT_NEW',
					store :getDataDictionaryByName(DataDictionary,'WORKFLOW_TYPE'),
					queryMode : 'local',
					forceSelection : true,
					displayField : 'codeDesc',
					valueField : 'code',
					editable:false,
					listeners : {
						change : function(){
							Ext.getCmp('workflow_type');
							agreeBtn.setDisabled(true);
							disAgreeBtn.setDisabled(true);
						} 
					}
				},
				{
					name:'workflowNo',
					maxLength:50,
					fieldLabel:i18n('i18n.workflow.workflowNo'),
					regex: /^ICRM((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229))\d{7}$/,
					regexText: i18n('i18n.workflow.workflowNoErrorInfo'),
					listeners : {
						change : function(){
							agreeBtn.setDisabled(true);
							disAgreeBtn.setDisabled(true);
						} 
					}
				},{
					margin : '-5 0 0 50',
					name : 'searchWorkFlowPicture',
					xtype : 'button',
					text : '查看流程图',
					width : 100,
					handler : function(r){
						var workFlowNo = form.findField('workflowNo').getValue();
						var workFlowType = form.findField('workflow_type').getValue();
						if(!form.isValid() || '' == workFlowNo){
							MessageUtil.showMessage('请输入正确工作流号');
							return;
						}; 
						var params = {
							'workFlowNo' : workFlowNo,
							'workFlowType' : workFlowType
						}

						var fnFailure = function(json){
							
						}
						var fnSuccess = function(json){
							 var processInstId = json.wfi.processinstid;
							 var busino = json.wfi.busino;
							 var processDefName = json.wfi.processDefName;
							 Ext.getCmp('buttonDemoPanelId').workflowApprove = {
							 	'processInstId' : processInstId,
							 	'busino' : busino,
							 	'processDefName' : processDefName
							 }
							 this.parent = temp;
							 agreeBtn.setDisabled(false);
							 disAgreeBtn.setDisabled(false);
							 var html = '<iframe   src="generateWorkflowImage.action?processInstId='+processInstId+'" style="height:100%;width:100%;" frameborder="0"></iframe>';
							 Ext.getCmp('workFlowImage').update(html);
						}
						workflowData.findOneKeyForApproveWorkFlow(params,fnSuccess,fnFailure);
					}
				}
				);		
			}
		}
	});
	/**
	 * 中间按钮
	 */
	Ext.define('ButtonDemoPanel',{
		extend:'NormalButtonPanel', 
		id : 'buttonDemoPanelId',
		workflowApprove : null,
		getItems:function(){
			return [{
				xtype:'middlebuttonpanel'  
			},{
					xtype:'rightbuttonpanel',  
					items:[{
						xtype:'button',    
						text:'同意',//同意
						name : 'agreeBtn',
						disabled : true,
						handler:function(){
							fnSuccess = function(){
								MessageUtil.showMessage('该工作流状态已经修改为已同意！');return;
							}
							fnFailure = function(){
								
							}
							workflowApprove = this.ownerCt.ownerCt.workflowApprove;
							workflowApprove.approveOperateType = '0';
							var msg ='是否将工作流状态改为同意';
							var chooseFn = function(e){
								if(e=='yes'){
									workflowData.oneKeyForApprove({ workflowApprove: workflowApprove},fnSuccess,fnFailure);
								}
							}
							MessageUtil.showQuestionMes(msg,chooseFn);
						}
					},{
						xtype:'button',     
						name:'disAgreeBtn',
						disabled : true,
						text:'不同意',//不同意
						handler:function(){
							fnSuccess = function(){
								MessageUtil.showMessage('该工作流状态已经修改为不同意！');return;
							}
							fnFailure = function(){
								
							}
							workflowApprove = this.ownerCt.ownerCt.workflowApprove;
							workflowApprove.approveOperateType = '1';
							var msg ='是否将工作流状态改为不同意';
							var chooseFn = function(e){
								if(e=='yes'){
									workflowData.oneKeyForApprove({ workflowApprove: workflowApprove},fnSuccess,fnFailure);
								}
							}
							MessageUtil.showQuestionMes(msg,chooseFn);
							
						}
					}]
			}]
		},
		initComponent : function(){
			var me = this;
			me.items = me.getItems();
			me.callParent();
		}
	});
	
	Ext.define('WorkFlowImagePanel',{
		extend : 'Ext.panel.Panel',
		name : 'workFlowImage',
		id : 'workFlowImage',
		flex : 1,
		items:[{
				html: ''
				}]
	});
	var imageWork = Ext.create('WorkFlowImagePanel');
	var buttonDemoPanel=Ext.create('ButtonDemoPanel');
	var queryFormPanel = Ext.create('OneKeyQueryForm');
	

	/**
	 * viewport
	 */
	Ext.create('Ext.container.Viewport', {
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		items:[queryFormPanel,buttonDemoPanel,imageWork]
	});
});