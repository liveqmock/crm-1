/**
 * 我的工作流
 */
Ext.onReady(function(){

	var  keys = [
	                 'CLAIM_WORKFLOW_STATE',
	                 'CONTRACT_WORKFLOW_TYPE'
	    		];

	initDataDictionary(keys);
	
	/**
	 * 顶部查询表单
	 */
	defineCommonWSQueryForm(1);
	
	
	/**
	 * 中间按钮
	 */
	Ext.define('ButtonDemoPanel',{
		extend:'NormalButtonPanel', 
		items:[{
				xtype:'leftbuttonpanel',
				items:[{
						xtype:'button',    
						text:i18n('i18n.workflow.seeTheDetail'),
						handler:function(){
							var record = gridPanel.getSelectionModel().getSelection()[0];
							if (!record) {
								MessageUtil.showMessage(i18n('i18n.workflow.pleaseSelectOneLine'));
								return false;
							}
							showDetailWorkflow('showDetailWorkflow.action?processInstId='+record.get('processinstidEnc')+'&&workItemId='+record.get('workItemIdEnc')+'&&processDefName='+record.get('processDefName')+'&busino='+record.get('busiNoEnc'));
						}
					}]
				
			},{
				xtype:'middlebuttonpanel'  
			},{
					xtype:'rightbuttonpanel',  
					items:[{
						xtype:'button',    
						text:i18n("i18n.workflow.search"),//查询
						handler:function(){
							var form =queryFormPanel.getForm();
							if(!form.isValid()){
								return;
							}; 
							var startDate=form.findField('startTime').getValue();
							var endDate=form.findField('endTime').getValue();
							//上报时间 上报结束数据不能超过31天
							if ((endDate-startDate)/(24*3600*1000)>31) {
								MessageUtil.showMessage(i18n('i18n.workflow.timeOutOfRang'));
								 return false;
							}
							if(endDate-startDate<0){
								MessageUtil.showMessage(i18n('i18n.workflow.startLessEnd'));
								 return false;
							}
							
							var params=form.getValues();
							findWorkflowStore.getProxy().extraParams=params;
							findWorkflowStore.load();
						}
					},{
						xtype:'button',     
						name:'ResetLogBtn',
						text:i18n('i18n.workflow.reset'),//重置
						handler:function(){
							queryFormPanel.getForm().reset();
						}
					}]
			}]
	});
	
	
	
	/**
	 * 底部GridPanel
	 */
	var findWorkflowStore = Ext.create('FindWorkflowStore',{autoLoad:false});
	defineSearchGridPanel(findWorkflowStore,true);//定义底部GridPanel
	
	var queryFormPanel = Ext.create('CommonWSQueryForm');
	var buttonDemoPanel=Ext.create('ButtonDemoPanel');
	var gridPanel =Ext.create('CommonSearchGridPanel',{flex:1});
	/**
	 * viewport
	 */
	Ext.create('Ext.container.Viewport', {
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		items:[queryFormPanel,buttonDemoPanel,gridPanel]
	});
});