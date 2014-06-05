/**
 * 审批权限金额配置
 */
Ext.onReady(function(){
	
	var keys = [
                 'WORKFLOW_CONFIG'
    		];
	initDataDictionary(keys);
	
	var ativityStore =Ext.create('ActivityStore',{autoLoad:true})
	
	/**
	 * 顶部查询表单
	 */
	Ext.define('QueryForm',{
		extend:'SearchFormPanel',
		frame:true,
		margin:'3 0 0 0',
		layout:{
			type:'table',
			columns:2
		},
		defaultType:'textfield',
		defaults:{
			width:300,
			labelWidth : 80,
			labelAlign:'right'	
		},
		items:[
			{
				fieldLabel :i18n('i18n.workflow.amountConfig.workflowName'),
				name : 'mcDefiniTionName',
				xtype : 'combobox',
				store :getDataDictionaryByName(DataDictionary,'WORKFLOW_CONFIG'),
				queryMode : 'local',
				forceSelection : true,
				editable:false,
				displayField : 'codeDesc',
				valueField : 'code',
				colspan: 2,
				listeners:{
					select:function(f){
						var form =queryFormPanel.getForm();
						form.findField('currentApproStepNo').setDisabled(false);
						form.findField('targetApproStepNo').setDisabled(false);
						
						
						form.findField('currentApproStepNo').clearValue();
						form.findField('targetApproStepNo').clearValue();
						
						form.findField('currentApproStepNo').getStore().removeAll();
						form.findField('targetApproStepNo').getStore().removeAll();
						
						ativityStore.load({params:{mcDefiniTionName:f.getValue()}});
					}
				}
			},{
				fieldLabel :i18n('i18n.workflow.amountConfig.currentStep'),
				name : 'currentApproStepNo',
				xtype : 'combobox',
				store :ativityStore,
				editable:false,
				queryMode : 'local',
				forceSelection : true,
				displayField : 'activityName',
				valueField : 'activityDefId',
				listConfig: {
	      		        loadMask:false
    			},
				disabled:true
			},{
				fieldLabel :i18n('i18n.workflow.amountConfig.targetStep'),
				name : 'targetApproStepNo',
				xtype : 'combobox',
				store :ativityStore,
				editable:false,
				queryMode : 'local',
				forceSelection : true,
				displayField : 'activityName',
				valueField : 'activityDefId',
				listConfig: {
	      		        loadMask:false
    			},
				disabled:true
		}]
	});
	
	/**
	 * 中间按钮
	 */
	Ext.define('ButtonDemoPanel',{
		extend:'NormalButtonPanel',
		items:[{
				xtype:'leftbuttonpanel',
				items:[{
						xtype:'button',   
						text:i18n('i18n.workflow.amountConfig.add'),
						handler:function(){
							 if(!Ext.getCmp('PopupWindow')){
								 Ext.create('PopupWindow').show();
							}else{
								Ext.getCmp('PopupWindow').show();
							}
							Ext.getCmp('PopupWindow').setTitle(i18n('i18n.workflow.amountConfig.addTitle'));
							popupForm.getForm().reset();
							popupForm.getForm().findField('currentApproStepNo').setDisabled(true);
							popupForm.getForm().findField('targetApproStepNo').setDisabled(true);
						}
					},{
						xtype:'button',   
						text:i18n('i18n.workflow.amountConfig.update'),
						handler:function(){
							var record = gridPanel.getSelectionModel().getSelection()[0];
							if (!record) {
								MessageUtil.showMessage(i18n('i18n.workflow.amountConfig.pleaseSelectOneLine'));
								return false;
							}
							 if(!Ext.getCmp('PopupWindow')){
								 Ext.create('PopupWindow').show();
							}else{
								Ext.getCmp('PopupWindow').show();
							}
							Ext.getCmp('PopupWindow').setTitle(i18n('i18n.workflow.amountConfig.updateTitle'));
							popupForm.getForm().reset();
							popupForm.getForm().findField('currentApproStepNo').setDisabled(false);
							popupForm.getForm().findField('targetApproStepNo').setDisabled(false);
							popupForm.getForm().findField('id').setValue(record.get('id'));
							
							popupForm.getForm().findField('mcDefiniTionName').setValue(record.get('mcDefiniTionName'));
							formAtivityStore.load({params:{mcDefiniTionName:record.get('mcDefiniTionName')},callback:function(){
								popupForm.getForm().findField('currentApproStepNo').setValue(record.get('currentApproStepNo'));
								popupForm.getForm().findField('targetApproStepNo').setValue(record.get('targetApproStepNo'));
							}});
							
							popupForm.getForm().findField('minAmount').setValue(record.get('minAmount'));
							popupForm.getForm().findField('maxAmount').setValue(record.get('maxAmount'));
							
						}
					},{
						xtype:'button',   
						text:i18n('i18n.workflow.amountConfig.delete'),
						handler:function(){
							var record = gridPanel.getSelectionModel().getSelection()[0];
							if (!record) {
								MessageUtil.showMessage(i18n('i18n.workflow.amountConfig.pleaseSelectOneLine'));
								return false;
							}
							successFn = function(json){
								MessageUtil.showInfoMes(i18n('i18n.workflow.amountConfig.deleteSuccess'));
								gridPanel.getStore().load();
							},
							failureFn = function(json){
									MessageUtil.showErrorMes(i18n('i18n.workflow.amountConfig.serviceMoreTime'));
							};
							var params = {'id':record.get('id')};
							MessageUtil.showQuestionMes(i18n('i18n.workflow.amountConfig.sureDelete'),function(e){
								if(e == 'yes'){
									AmountConfigData.deleteAmountConfig(params, successFn, failureFn );
								}
							});
					}
					}]
			},{
				xtype:'middlebuttonpanel' 
			},{
					xtype:'rightbuttonpanel',
					items:[{
						xtype:'button',  
						text:i18n("i18n.workflow.amountConfig.search"),//查询
						handler:function(){
							var params=queryFormPanel.getForm().getValues();
							amountConfigStore.load({params:params});
						}
					},{
						xtype:'button',   
						text:i18n('i18n.workflow.amountConfig.reset'),//重置
						handler:function(){
							queryFormPanel.getForm().reset();
							queryFormPanel.getForm().findField('currentApproStepNo').setDisabled(true);
							queryFormPanel.getForm().findField('targetApproStepNo').setDisabled(true);
						}
					}]
			}]
	});
	
	/**
	 * 底部GridPanel
	 */
	var amountConfigStore = Ext.create('AmountConfigStore',{autoLoad:true});
	
	Ext.define('CommonSearchGridPanel',{
			extend:'SearchGridPanel',
			store:amountConfigStore,
			flex:1,
			selModel:Ext.create('Ext.selection.CheckboxModel',{mode:'single'}),//选择框
			columns:[{
				xtype : 'rownumberer',width : 40,text : i18n('i18n.workflow.amountConfig.num')
			},{
				text:i18n('i18n.workflow.amountConfig.workflowName'),dataIndex:'mcName',flex:1
			},{
				text:i18n('i18n.workflow.amountConfig.currentStep'),dataIndex:'currentApproStepName',flex:1
			},{
				text:i18n('i18n.workflow.amountConfig.targetStep'),dataIndex:'targetApproStepName',flex:1
			},{
				text:i18n('i18n.workflow.amountConfig.amountRang'),dataIndex:'minAmount',flex:1,
				renderer:function(v,s,r){
					return r.get('minAmount')+'-'+r.get('maxAmount');
				}
			}],
			viewConfig:{
				forceFit:true
			},
			dockedItems:[{
				xtype:'pagingtoolbar',
				store:amountConfigStore,
				dock:'bottom',
				displayInfo : true,
				autoScroll : true
			}]
		});
	
	var formAtivityStore =Ext.create('ActivityStore');	
	Ext.define('PopupForm',{	
		extend:'NoTitleFormPanel',		
		items:[{
				layout:{
					type:'table',
					columns:2
				},
				defaults:{
					labelWidth:100,
					width:300
				},
				items:[{
					fieldLabel :i18n('i18n.workflow.amountConfig.workflowName'),
					name : 'mcDefiniTionName',
					xtype : 'combobox',
					store :getDataDictionaryByName(DataDictionary,'WORKFLOW_CONFIG'),
					queryMode : 'local',
					forceSelection : true,
					displayField : 'codeDesc',
					valueField : 'code',
					editable:false,
					allowBlank : false,
					colspan: 2,
					listeners:{
					select:function(f){
						var form=popupForm.getForm();
						form.findField('currentApproStepNo').setDisabled(false);
						form.findField('targetApproStepNo').setDisabled(false);
						
						form.findField('currentApproStepNo').clearValue();
						form.findField('targetApproStepNo').clearValue();
						
						form.findField('currentApproStepNo').getStore().removeAll();
						form.findField('targetApproStepNo').getStore().removeAll();
						
						formAtivityStore.load({params:{mcDefiniTionName:f.getValue()}});
					}
				}
				 },{
					fieldLabel :i18n('i18n.workflow.amountConfig.currentStep'),
					name : 'currentApproStepNo',
					xtype : 'combobox',
					store :formAtivityStore,
					queryMode : 'local',
					forceSelection : true,
					editable:false,
					allowBlank : false,
					displayField : 'activityName',
					valueField : 'activityDefId',
					listConfig: {
	      		        loadMask:false
    				},
					disabled:true
				 },{
					fieldLabel :i18n('i18n.workflow.amountConfig.targetStep'),
					name : 'targetApproStepNo',
					xtype : 'combobox',
					store :formAtivityStore,
					queryMode : 'local',
					forceSelection : true,
					editable:false,
					allowBlank : false,
					displayField : 'activityName',
					valueField : 'activityDefId',
					listConfig: {
	      		        loadMask:false
    				},
					disabled:true
				},{
					fieldLabel :i18n('i18n.workflow.amountConfig.minAmount'),
					name : 'minAmount',
					xtype:'numberfield',
					minValue:0,
					maxValue:1000000000,
					allowBlank : false
				},{
					fieldLabel :i18n('i18n.workflow.amountConfig.maxAmount'),
					name : 'maxAmount',
					xtype:'numberfield',
					minValue:0,
					maxValue:1000000000,
					allowBlank : false
				},{
					name:'id',
					xtype:'hiddenfield'
				}]		
			}]
	});
	var popupForm=Ext.create('PopupForm');		
		
	//弹出的新增窗口
	Ext.define('PopupWindow',{				
		extend:'PopWindow',
		id:'PopupWindow',
		fbar:[{
				xtype:'button',
				text:i18n('i18n.workflow.amountConfig.determine'),	// 确定
				handler:function(){
					var form =popupForm.getForm();
					if(!form.isValid()){
						return;
					}; 
					if(form.findField('minAmount').getValue()>=form.findField('maxAmount').getValue()){
						 MessageUtil.showErrorMes(i18n('i18n.workflow.amountConfig.validateInfo'));	
						 return;
					}
						var params=form.getValues();
						params.currentApproStepName=form.findField('currentApproStepNo').getRawValue();
						params.targetApproStepName=form.findField('targetApproStepNo').getRawValue();
						if(Ext.getCmp('PopupWindow').title==i18n('i18n.workflow.amountConfig.addTitle')){
							var fnSuccess = function(json){
							 MessageUtil.showInfoMes(i18n('i18n.workflow.amountConfig.addSuccess'));	
							 Ext.getCmp('PopupWindow').hide();
								gridPanel.getStore().load();
							},
							fnFailure = function(json){
								MessageUtil.showErrorMes(json.message);	
							};
							params.mcName=rendererDictionary(params.mcDefiniTionName,DataDictionary.WORKFLOW_CONFIG);
							AmountConfigData.addAmountConfig ({amountConfigEntity:params},fnSuccess,fnFailure);
					}else{
							var fnSuccess = function(json){
								 MessageUtil.showInfoMes(i18n('i18n.workflow.amountConfig.updateSuccess'));	
								 Ext.getCmp('PopupWindow').hide();
								gridPanel.getStore().load();
							},
							fnFailure = function(json){
								MessageUtil.showErrorMes(json.message);	
							};
							params.mcName=rendererDictionary(params.mcDefiniTionName,DataDictionary.WORKFLOW_CONFIG);
							AmountConfigData.updateAmountConfig ({amountConfigEntity:params},fnSuccess,fnFailure);
					}
				}
			},{
				xtype:'button',
				text:i18n('i18n.workflow.amountConfig.cancel'),	// 取消
				handler:function(){
					Ext.getCmp('PopupWindow').hide();
			}
		}],
		items:[popupForm],
		closeAction:'hide',
		layout: 'fit'
	});

	var queryFormPanel = Ext.create('QueryForm');
	var buttonDemoPanel=Ext.create('ButtonDemoPanel');
	var gridPanel =Ext.create('CommonSearchGridPanel');	
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