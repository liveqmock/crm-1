		/**
		 * 
		 * <p>
		 * Description:索赔录入页面<br />
		 * </p>
		 * @title SaveClaim.js
		 * @author 许华龙
		 * @version 0.1 2013-2-26
		 */	
	Ext.onReady(function(){
		var keys=[
		          'RECOMPENSE_TYPE',
		          'SERVICE_RECOVERY_CUSTOMER_TYPE'
		          
		          ];
		var mustFillSign = '<span style="color:red">*</span>';//必填符号  *
		var isReset = false;
		initDataDictionary(keys);
		var style = "color:'red'";
		var form = Ext.create('TitleFormPanel',{
			width:800,
			items:[{
				xtype:'basicfiledset',
				title:i18n('i18n.SaveClaim.claimsInfo'),
				height:230,
				layout:{
					type:'table',
					columns:3
				},
				defaults:{
					xtype:'textfield',
					width:240
				},
				items:[{
					xtype:'combo',
					fieldLabel:mustFillSign+i18n('i18n.recompense.recompensetype'),
					name:'recompenseMethod',
					store:getDataDictionaryByName(DataDictionary,'RECOMPENSE_TYPE'),
					allowBlank:false,
					editable:false,
					value:'abnormal',
					maxLength:30,
					displayField:'codeDesc',
					valueField:'code',
					listeners:{
						'change':function(me,newValue,oldValue){
							if(newValue!=oldValue){
								if(isReset==true){
									isReset=false;
									return;
								}
								
								MessageUtil.showQuestionMes(i18n('i18n.SaveClaim.switchClaimsClearEnteredInformationConfirm'),function(e){
									if(e=='yes'){
										form.getForm().findField('waybillNumber').reset();
										form.getForm().findField('claimer').reset();
										form.getForm().findField('linkMan').reset();
										form.getForm().findField('linkMobile').reset();
										form.getForm().findField('insuranceValue').reset();
										form.getForm().findField('claimPerson').reset();
										form.getForm().findField('claimRequire').reset();
										form.getForm().findField('claimAmount').reset();
									}
								});
							}
						}
					}
				},{
					fieldLabel:mustFillSign+i18n('i18n.recompense.orderNumOrErroeNum'),
					name:'waybillNumber',
					maxLength:30,
					allowBlank:false,
					blankText:i18n('i18n.SearchClaim.waybillNotNull'),//凭证号不可为空
					listeners:{
						'blur':function(me){
							if(Ext.isEmpty(me.getValue())){
								return;
							}
							var param={
									'voucherNo' : Ext.util.Format.trim(me.getValue()), 
									'recompenseMethod' : form.getForm().findField('recompenseMethod').getValue()
							};
							var successFn=function(json){
								if(!Ext.isEmpty(json.claim.claimer)){
									form.getForm().findField('claimer').setValue(json.claim.claimer);
									form.getForm().findField('linkMan').setValue(json.claim.linkMan);
									form.getForm().findField('linkMobile').setValue(json.claim.linkMobile);
									form.getForm().findField('insuranceValue').setValue(json.claim.insuranceValue);
									form.getForm().findField('receiveDept').setValue(json.claim.receiveDept);
									form.getForm().findField('arrivedDept').setValue(json.claim.arrivedDept);
									form.getForm().findField('shipper').setValue(json.claim.shipper);
									form.getForm().findField('consignee').setValue(json.claim.consignee);
									form.getForm().findField('shipperPhone').setValue(json.claim.shipperPhone);
									form.getForm().findField('consigneePhone').setValue(json.claim.consigneePhone);
									form.getForm().findField('transType').setValue(json.claim.transType);
									
//									if(json.claim.transType=="偏线"||json.claim.transType=='空运'){
//										form.getForm().findField('claimer').setReadOnly(false);
//										form.getForm().findField('claimer').removeCls('readonly');
//										form.getForm().findField('claimer').doComponentLayout();
//									}else{
//										form.getForm().findField('claimer').setReadOnly(true);
//									}
									
									if(!Ext.isEmpty(json.claim.arrivedDept)){
										form.getForm().findField('claimer').setReadOnly(false);
										form.getForm().findField('claimer').removeCls('readonly');
										form.getForm().findField('claimer').doComponentLayout();
									}else{
										form.getForm().findField('claimer').setReadOnly(true);
									}
									
								}else{
									me.setValue('');
								}
								
							};
							var failFn=function(json){
								if(Ext.isEmpty(json)){
									MessageUtil.showErrorMes(i18n('i18n.SearchClaim.timeOut'));
								}else{
									MessageUtil.showErrorMes(json.message);
									me.setValue('');
								}
							}
							getWayBillAndType(param,successFn,failFn);
						}
					}
				},{
					xtype:'combo',
					fieldLabel:i18n('i18n.recompense.recompenseMan'),
					name:'claimer',
					maxLength:30,
					readOnly:true,
					allowBlank:false,
					store:getDataDictionaryByName(DataDictionary,'SERVICE_RECOVERY_CUSTOMER_TYPE'),
					displayField:'codeDesc',
					valueField:'code',
					cls:'readonly',
					editable:false,
					listeners:{
						'change':function(me){
							var shipper = form.getForm().findField('shipper').getValue();
							var consignee = form.getForm().findField('consignee').getValue();
							var shipperPhone = form.getForm().findField('shipperPhone').getValue();
							var consigneePhone = form.getForm().findField('consigneePhone').getValue();
							if(me.getValue()=='SHIPPER'){//发货方
								form.getForm().findField('linkMan').setValue(shipper);
								form.getForm().findField('linkMobile').setValue(shipperPhone);
							}else if(me.getValue()=='CONSIGNEE'){//收货方
								form.getForm().findField('linkMan').setValue(consignee);
								form.getForm().findField('linkMobile').setValue(consigneePhone);
							}
						}
					}

				},{
					fieldLabel:i18n('i18n.SaveClaim.contact'),
					maxLength:50,
					allowBlank:false,
					name:'linkMan',
					readOnly:true
				},{
					fieldLabel:i18n('i18n.SaveClaim.contactWay'),
					maxLength:50,
					allowBlank:false,
					name:'linkMobile',
					readOnly:true
				},{
					fieldLabel:i18n('i18n.recompense.insureAmount'),
					maxLength:30,
					allowBlank:false,
					name:'insuranceValue',
					readOnly:true
				},{
					fieldLabel:mustFillSign+i18n('i18n.recompense.recompensePerson'),
					maxLength:50,
					name:'claimPerson',
					allowBlank:false,
					blankText:i18n('i18n.SearchClaim.pleaseInputClaimerName'),//请输入索赔人名字
					listeners:{
						'blur':function(me){
							if(me.getValue()!=form.getForm().findField('linkMan').getValue()){
								MessageUtil.showMessage(i18n('i18n.SaveClaim.thisClaimsManNameNotSamePleaseCheck'));
							}
						}
					}
				},{
					xtype:'numberfield',
					fieldLabel:mustFillSign+i18n('i18n.recompense.claimAmount'),
					maxLength:30,
					minValue:0,
					name:'claimAmount',
					allowBlank:false,
					hideTrigger:true,
					blankText:i18n('i18n.recomepnse.claimAmountNotNull'),//索赔金额不可为空
					colspan:2
				},{
					fieldLabel:i18n('i18n.SaveClaim.customerClaims'),
					name:'claimRequire',
					xtype:'textarea',
					width:483,
					maxLength:300,
					maxLengthText:i18n('i18n.SaveClaim.requireLengthLimit'),
					height:100,
					blankText:i18n('i18n.SearchClaim.pleaseInputClaimRequre'),//请输入索赔要求
					colspan:2
				}]
			},{
				xtype:'displayfield',
				height:20,
				value:'<span style="color:red">'+
				i18n('i18n.SaveClaim.outDeptOrToDeptWriteClaimsInfo')+'</span>'
			},{
				xtype:'hiddenfield',
				name:'receiveDept'
			
			},{
				xtype:'hiddenfield',
				name:'arrivedDept'
			
			},{
				xtype:'hiddenfield',
				name:'shipper'
			
			},{
				xtype:'hiddenfield',
				name:'consignee'
			
			},{
				xtype:'hiddenfield',
				name:'shipperPhone'
			
			},{
				xtype:'hiddenfield',
				hidden:true,
				name:'consigneePhone'
			
			},{
				xtype:'hiddenfield',
				hidden:true,
				name:'transType'
			}],
			submitFn:function(){
				if(!form.getForm().isValid()){
					return;
				}
				var param = {
						'claim':{
							'recompenseMethod':form.getForm().findField('recompenseMethod').getValue(),
							'waybillNumber':Ext.util.Format.trim(form.getForm().findField('waybillNumber').getValue()),
							'claimer':form.getForm().findField('claimer').getValue(),
							'linkMan':form.getForm().findField('linkMan').getValue(),
							'linkMobile':form.getForm().findField('linkMobile').getValue(),
							'insuranceValue':form.getForm().findField('insuranceValue').getValue(),
							'claimPerson':form.getForm().findField('claimPerson').getValue(),
							'claimAmount':form.getForm().findField('claimAmount').getValue(),
							'claimRequire':form.getForm().findField('claimRequire').getValue(),
							'receiveDept':form.getForm().findField('receiveDept').getValue(),
							'arrivedDept':form.getForm().findField('arrivedDept').getValue(),
							'shipper':form.getForm().findField('shipper').getValue(),
							'consignee':form.getForm().findField('consignee').getValue(),
							'shipperPhone':form.getForm().findField('shipperPhone').getValue(),
							'consigneePhone':form.getForm().findField('consigneePhone').getValue(),
							'transType':form.getForm().findField('transType').getValue()
						}
				};
				var successFn = function(json){
					isReset = true;
					MessageUtil.showInfoMes(i18n('i18n.SaveClaim.commitSuccess'));
					form.getForm().reset();
				};
				var failFn = function(json){
					if(Ext.isEmpty(json)){
						MessageUtil.showErrorMes(i18n('i18n.SearchClaim.timeOut'));
					}else{
						MessageUtil.showErrorMes(json.message);
					}
				}
				MessageUtil.showQuestionMes(i18n('i18n.SaveClaim.helloPleaseConfirmSubmitEntrySubmittedNotModified'),function(e){
					if(e=='yes'){
						saveClaim(param,successFn,failFn);
					}
				});
			},
			buttons:[{
				text:i18n('i18n.recompense.save'),
				handler:function(){
					form.submitFn();
				}
			},{
				text:i18n('i18n.SaveClaim.setEmpty'),
				handler:function(){
					isReset=true;
					form.getForm().reset();
				}
			}]
				
		});
		
		/**界面显示**/
		Ext.create('Ext.container.Viewport', {
	    	items: [form]
		});
	});
	

