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
	             'TRANSPORT_TYPE',
	             'APPROVE_REUSLT',
	             'AWARD_TARGET_TYPE',
                 'AWARD_TYPE',
                 'SERVICERECOVERY_REDUCTION_TYPE',
                 'TRANSPORT_TYPE'
			];
	initDataDictionary(keys);
	initDeptAndUserInfo();
 	var canUpload,uploadPanel=Ext.create('NoTitleFormPanel',{
			frame:true,
			flex:1,
			layout:{
				type:'table',
				columns:3
			},		
			items:[{
					xtype:'filefield',
					name:'file',
					width:400,
					fieldLabel:i18n('i18n.workflow.uploadFile'),
					buttonText:i18n('i18n.workflow.browse')
				},{
					xtype:'displayfield',
					width:20
				},{
					xtype:'button',
					margin:'0 0 5 0',
					text:i18n('i18n.workflow.add'),
					handler:function(t){
						if(t.findParentByType('form').query('button').length>=5){
							MessageUtil.showErrorMes(i18n('i18n.workflow.maxCountFile'));
							return;
						}
						var f1,f2,f3;
						//动态添加
						uploadPanel.add({
							xtype:'filefield',
							name:'file',
							width:400,
							fieldLabel:i18n('i18n.workflow.uploadFile'),
							buttonText:i18n('i18n.workflow.browse'),
							listeners:{
								'afterrender':function(t){
									f1=t;
								}
							}
						},{
							xtype:'displayfield',
							width:20,
							listeners:{
								'afterrender':function(t){
									f2=t;
								}
							}
						},{
							xtype:'button',
							margin:'0 0 5 0',
							name:'deleteBtn',
							text:i18n('i18n.workflow.delete'),
							listeners:{
								'afterrender':function(t){
									f3=t;
								}
							},
							handler:function(t){
								t.findParentByType('form').remove(f1);
								t.findParentByType('form').remove(f2);
								t.findParentByType('form').remove(f3);
							}
						});
					}
				}
			]
		});
	
	
	/**
	 * 弹出框查询表单
	 */
	Ext.define('CommonPopApproveForm',{
		extend:'PopTitleFormPanel',
		title:i18n('i18n.workflow.approveArea'),
		frame:true,
		cls:'appWorkflow',
		width:745,
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
		],
		listeners:{
			beforerender:function(f){
				var successFn = function(json){
					//印章管理员且非零担才能选同意
					if(!json.canChoose){
						f.getForm().findField('approveOperateType').setValue(0);
						f.getForm().findField('approveOperateTypeGroup').remove(1);//不同意选项消失
						//f.getForm().findField('approveOperateTypeGroup').items.items[1].setDisabled(true);//不同意选项置灰
					}
					if(json.canUpload){
						canUpload=true;
						f.add(uploadPanel);
					}
				};
				var failFn = function(json){
						MessageUtil.showErrorMes(i18n('i18n.workflow.amountConfig.approvePermissionInfo'));
				};
				workflowData.findApprovePermission({processInstId:getUrlVars('processInstId')},successFn,failFn);
			}
		}
		
	});
		
	var popApproveForm = Ext.create('CommonPopApproveForm');
	
	/**
	 * 弹出框GridPanel
	 */
	var approvalInfoStore = Ext.create('ApprovalInfoStore');
	approvalInfoStore.load({params:{processInstId:getUrlVars('processInstId')}});
	definePopupGridPanel(approvalInfoStore);//定义弹出框GridPanel
	var popGridPanel =Ext.create('CommonPopupGridPanel');
	
	/**
	 * 弹出框里详情信息表单
	 */	
	var popDetailForm;	
	if(getUrlVars('processDefName')=='com.deppon.bpms.module.crm.bpsdesign.claims.generalclaims'){
		popDetailForm = Ext.create('CommonDetailPopForm',{flex:1.5});//常规理赔
	}else if(getUrlVars('processDefName')=='com.deppon.bpms.module.crm.bpsdesign.financial.serviceRecovery'){
		popDetailForm = Ext.create('CommonServiceDetailPopForm',{flex:1.5});//服务补救
	}else if(getUrlVars('processDefName')=='com.deppon.bpms.module.crm.bpsdesign.operate.multiReparation'){//多赔
		popDetailForm = Ext.create('CommonOverpayDetailPopForm',{flex:1.5});//多赔
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
				if(canUpload){
						var form =uploadPanel.getForm();						
						var fileFields=uploadPanel.query('filefield');
						var hasFiles=false;
						for(var i=0;i<fileFields.length;i++){
							if(!Ext.isEmpty(fileFields[i].getValue())){
								hasFiles=true;
							}
						}
						if(hasFiles){						
							for(var i=0;i<fileFields.length;i++){
								if(!Ext.isEmpty(fileFields[i].getValue())){
									//上传格式限制
									var addr = fileFields[i].getValue().split('.');
									var count = addr.length;
									//限制文件格式
									if(addr[count-1]!='jpg'&&addr[count-1]!='JPG'&&
											addr[count-1]!='jpeg'&&addr[count-1]!='JPEG'&&
											addr[count-1]!='gif'&&addr[count-1]!='GIF'&&
											addr[count-1]!='png'&&addr[count-1]!='PNG'&&
											addr[count-1]!='dmp'&&addr[count-1]!='DMP'&&
											addr[count-1]!='txt'&&addr[count-1]!='TXT'&&
											addr[count-1]!='pdf'&&addr[count-1]!='PDF'&&
											addr[count-1]!='doc'&&addr[count-1]!='DOC'&&
											addr[count-1]!='docx'&&addr[count-1]!='DOCX'&&
											addr[count-1]!='xls'&&addr[count-1]!='XLS'&&
											addr[count-1]!='xlsx'&&addr[count-1]!='XLSX'&&
											addr[count-1]!='rar'&&addr[count-1]!='RAR'){
										MessageUtil.showErrorMes(i18n('i18n.workflow.pleaseSurePattern'));
										return;
									}
								}
							}
						var params={type:'workflowAttDir',imageOrAttachment:'attachment',maxSizeLimit:'15M'};	
					 	var successFn = function(form, response) {
		                    	var result = response.result;
		                    	if(result.success){
		                    		if(result.fileInfoList.length>0){
		                    			doSubmit(result.fileInfoList)
		                    		}else{
		                    			MessageUtil.showMessage(i18n('i18n.workflow.uploadTimeout'));
		                    			return;
		                    		}
								}else{
		                       		MessageUtil.showErrorMes( result.message);
								}
		                    },
							failureFn = function(form, response){
			                 	var result = response.result;
			                 	if(!result.success){
			                       	MessageUtil.showErrorMes(result.message);
								}
			                 };
						workflowData.workflowUpload(form,i18n('i18n.workflow.uploading'),params,successFn,failureFn);
					}else{
						doSubmit();
					}
				}else{
					doSubmit();
				}
					
				function doSubmit(fileInfoList){
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
					params.processDefName=getUrlVars('processDefName');
					params.fileInfoList=fileInfoList;
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
		      {xtype:'tabcontentpanel',title:i18n('i18n.workflow.approveWorkflow'),layout : {type : 'vbox',	align : 'stretch'},items:[popApproveForm,popGridPanel,popDetailForm]},
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