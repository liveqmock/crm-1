/**
 * 已处理的工作流
 */
Ext.onReady(function(){
	
	var keys = [
                 'CLAIM_WORKFLOW_STATE',
                 'REC_WORKFLOW_TYPE'
    		];
	initDataDictionary(keys);
	
	/**
	 * 顶部查询表单
	 */
	defineCommonWSQueryForm(3);
	
	
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
							showDetailWorkflow('showDetailWorkflow.action?processInstId='+record.get('processinstidEnc')+'&processDefName='+record.get('processDefName'));
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
							params.applerId=form.findField("userSearch").getValueId();
							params.deptId=form.findField("deptName").getValueId();
							handledWorkflowStore.getProxy().extraParams=params;
							handledWorkflowStore.load();
						}
					},{
						xtype:'button',   
						name:'ResetLogBtn',
						text:i18n('i18n.workflow.reset'),//重置
						handler:function(){
							var form =queryFormPanel.getForm();
							form.reset();
							form.findField("userSearch").setValueId('');//需要手动重置
							form.findField("deptName").setValueId('');//需要手动重置
						}
					}]
			}]
	});
	
	var buttonDemoPanel=Ext.create('ButtonDemoPanel',{region: 'north'});
	
	/**
	 * 底部GridPanel
	 */
	var handledWorkflowStore = Ext.create('HandledWorkflowStore',{autoLoad:false});
	defineSearchGridPanel(handledWorkflowStore,true);//定义底部GridPanel
	
	
	var queryFormPanel = Ext.create('CommonWSQueryForm',{region: 'north'});
	var buttonDemoPanel=Ext.create('ButtonDemoPanel',{region: 'north'});
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