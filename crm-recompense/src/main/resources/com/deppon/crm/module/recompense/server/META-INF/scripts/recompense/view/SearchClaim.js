var recompenseData=  new RecompenseDataN();
/**
 * 
 * <p>
 * Description:索赔管理页面<br />
 * </p>
 * @title SaveClaim.js
 * @author 许华龙
 * @version 0.1 2013-2-26
 */
	Ext.onReady(function(){
		Ext.QuickTips.init();
		var keys=[
		          'CLAIM_STATUS',
		          'RECOMPENSE_TYPE',
	          	  'SERVICE_RECOVERY_CUSTOMER_TYPE',
	          	  //出险类型
	              'DANGER_TYPE',
	              //理赔类型
	              'RECOMPENSE_TYPE',
	              //客户等级
	              'MEMBER_GRADE',
	              'TRANSPORT_TYPE'
		          ];
		initDataDictionary(keys);
		initDeptAndUserInfo();
		var win;
		/*******************************顶部查询条件的form***************************/
		Ext.define('QueryForm',{
			extend:'SearchFormPanel',
			frame:true,
			margin:'3 5 0 5',
			layout:{
				type:'table',
				columns:3
			}
			,defaultType:'textfield',
			defaults:{
				width:250,
				labelAlign:'right'	
			}
			,items:[
			Ext.create('Ext.lookup.DeptLookup',
					   {
				   id :'searchReportDept',
				   maxLength:80,
				   editable:false,
				   emptyText:'',
				   valueId:User.deptId,
				   value:User.deptName,
				   name:'reportDept',
				   readOnly:User.roleids.indexOf("3")==-1?false:true,
				   cls:User.roleids.indexOf("3")==-1?'':'readonly',
				   fieldLabel:i18n('i18n.SearchClaim.processingDepartment')   //处理部门,
			}),{
				name:'waybillNumber',
				maxLength:30,
				maxLengthText:'凭证号不能大于30位',
				fieldLabel:i18n('i18n.recompense.orderNumOrErroeNum')//凭证号',
			},
			{	xtype:'combobox',
				name:'claimStatus',
				maxLength:100,
				fieldLabel:i18n('i18n.SearchClaim.claimStatus')//索赔状态',
				,store:getDataDictionaryByName(DataDictionary,'CLAIM_STATUS')
				,displayField:'codeDesc'
				,valueField:'code'
				
			},{
				xtype     : 'datefield',
				format: 'Y-m-d',
				name      : 'startDate',
				maxValue:new Date(),
				fieldLabel: i18n('i18n.SearchClaim.inputTime'),
				value:new Date(new Date().getFullYear(),new Date().getMonth(),new Date().getDate()-31),
				allowBlank:false,
				editable: false
			},
			{	
				xtype     : 'datefield',
				format: 'Y-m-d',
				name      : 'endDate',
				fieldLabel: i18n('i18n.recompense.to'),//i18n('i18n.recompense.to'),
				labelSeparator:'',
				maxValue:new Date(),
				value:new Date(),
				allowBlank:false,
				editable: false
			}]
		});
		
		var followUpGrid = Ext.create('PopupGridPanel',{
			autoScroll:true,
			height:250,
			store:Ext.create('FollowUpGridStore',{id:'followUpStoreId'}),
			columns:[{
				text:i18n('i18n.recompense.num'),xtype:'rownumberer',width:40
			},{
				text:i18n('i18n.recompense.sendMessageMan'),dataIndex:'followUser'
			},{
				text:i18n('i18n.SearchClaim.followUpDepartment'),dataIndex:'followDeptName'
			},{
				text:i18n('i18n.recompense.sendMessageTime'),dataIndex:'createTime',
				renderer:function(value) {
					if(value != null){return Ext.Date.format(new Date(value),'Y-m-d H:i');
					}else{return null;}
				}
			},{
				text:i18n('i18n.recompense.sendMessageContent'),dataIndex:'content',flex:1,
//				  增加这个函数，用于显示每行的提示信息  
	            renderer: function(value,metaData,record,colIndex,store,view) {  
				       if(!Ext.isEmpty(value)){
				    	   metaData.tdAttr = 'data-qtip="'+value+'"';  
					       return value; 
				       } 
				}  
			}]
		});
		var operateLogGrid = Ext.create('PopupGridPanel',{
			height:250,
			autoScroll:true,
			store:Ext.create('OperateLogGridStore',{id:'operateLogStoreId'}),
			columns:[{
				text:i18n('i18n.recompense.num'),xtype:'rownumberer',width:40
			},{
				text:i18n('i18n.SearchClaim.operator'),dataIndex:'operatorName'
			},{
				text:i18n('i18n.SearchClaim.operationsDepartment'),dataIndex:'operatorDeptName'
			},{
				text:i18n('i18n.SearchClaim.operationsTime'),dataIndex:'operatorTime',
				renderer:function(value) {
					if(value != null){return Ext.Date.format(new Date(value),'Y-m-d H:i');
					}else{return null;}
				}
			},{
				text:i18n('i18n.SearchClaim.operationsInfo'),dataIndex:'operatorContent',flex:1,
//				  增加这个函数，用于显示每行的提示信息  
	            renderer: function(value,metaData,record,colIndex,store,view) {  
				       if(!Ext.isEmpty(value)){
				    	   metaData.tdAttr = 'data-qtip="'+value+'"';  
					       return value; 
				       } 
				}  
			}]
		});
		
		
		followUpGrid.getStore().on('beforeload',function(store,operation,obj){
			Ext.apply(operation,{
				params : {
					'claimId':Ext.getCmp('popFormId').getForm().findField('id').getValue()
				}
			});
		});
		
		operateLogGrid.getStore().on('beforeload',function(store,operation,obj){
			Ext.apply(operation,{
				params : {
					'claimId':Ext.getCmp('popFormId').getForm().findField('id').getValue()
				}
			});
		});
		
		
		/**填充弹窗的tabPanel*******************************************/
		Ext.define('TabPanel',{
			extend:'NormalTabPanel',
			items:[{
				title:i18n('i18n.SaveClaim.customerClaims'),
				items:[{
					xtype:'textarea',
					id:'claimRequireId',
					fieldLabel:i18n('i18n.SaveClaim.customerClaims'),
					maxLength:300,
					margin:'10 0 0 0',
					width:520,
					height:150,
					readOnly:true
				}]
			},{
				title:i18n('i18n.SearchClaim.followUpRecord'),
				layout:'fit',
				items:[{
					xtype:'panel',
					margin:'5 5 5 5',
				    items:followUpGrid
				}]
			},{
				title:i18n('i18n.SearchClaim.operationLog'),
				layout:'fit',
				items:[{
					xtype:'panel',
					margin:'5 5 5 5',
				    items:operateLogGrid
				}]
			}],
			listeners:{
				'tabchange':function(tabPanel,newCard,oldCard){
		    		if(newCard.title=='跟进记录'||newCard.title==i18n('i18n.SearchClaim.followUpRecord')){
		    			followUpGrid.getStore().load();
		    		}else if(newCard.title=='操作日志'||newCard.title==i18n('i18n.SearchClaim.operationLog')){
		    			operateLogGrid.getStore().load();
		    		}else{
		    			return;
		    		}
		    	}
			}
		});
		
		/**
		 * 详情界面form
		 */
		Ext.define('PopForm',{
			extend:'TitleFormPanel',
			id:'popFormId',
			items:[{
				xtype:'basicfiledset',
				title:i18n('i18n.SaveClaim.claimsInfo'),
				layout:{
					type:'table',
					columns:3
				},
				defaultType:'textfield',
				items:[{
					xtype:'combo',
					fieldLabel:i18n('i18n.recompense.recompensetype'),
					name:'recompenseMethod',
					editable:false,
					store:getDataDictionaryByName(DataDictionary,'RECOMPENSE_TYPE'),
					displayField:'codeDesc',
					valueField:'code',
					/**
					 * @author 张斌
					 * @constructor 设置样式将其灰掉
					 * @time 2013-3-12
					 */
					cls:'readonly',
					readOnly:true
				},{
					fieldLabel:i18n('i18n.recompense.orderNumOrErroeNum'),
					name:'waybillNumber',
					readOnly:true
				},{
					fieldLabel:i18n('i18n.recompense.recompenseMan'),
					xtype:'combo',
					name:'claimer',
					editable:false,
					displayField:'codeDesc',
					valueField:'code',
					/**
					 * @author 张斌
					 * @constructor 设置样式将其灰掉
					 * @time 2013-3-12
					 */
					cls:'readonly',
					store:getDataDictionaryByName(DataDictionary,'SERVICE_RECOVERY_CUSTOMER_TYPE'),
					readOnly:true
				},{
					fieldLabel:i18n('i18n.SaveClaim.contact'),
					name:'linkMan',
					readOnly:true
				},{
					fieldLabel:i18n('i18n.SaveClaim.contactWay'),
					name:'linkMobile',
					readOnly:true
				},{
					fieldLabel:i18n('i18n.recompense.insureAmount'),
					name:'insuranceValue',
					readOnly:true
				},{
					fieldLabel:i18n('i18n.SearchClaim.claimStatus'),
					name:'claimStatus',
					xtype:'combo',
					displayField:'codeDesc',
					valueField:'code',
					/**
					 * @author 张斌
					 * @constructor 设置样式将其灰掉
					 * @time 2013-3-12
					 */
					cls:'readonly',
					editable:false,
					store:getDataDictionaryByName(DataDictionary,'CLAIM_STATUS'),
					readOnly:true
				},{
					fieldLabel:i18n('i18n.recompense.recompensePerson'),//索赔人
					maxLength:50,
					maxLengthText:'索赔人最大长度为50',
					name:'claimPerson',
					readOnly:true
				},{
					fieldLabel:i18n('i18n.recompense.claimAmount'),
					name:'claimAmount',
					maxLength:30,
					minValue:0,
					maxLengthText:'索赔金额最大长度为30',
					xtype:'numberfield',
					hideTrigger:true,
					readOnly:true
				},{
					fieldLabel:i18n('i18n.SearchClaim.claim'),
					name:'showTime',
					xtype     : 'datefield',
					format: 'Y-m-d H:i:s',
					cls:'readonly',
					readOnly:true
				},{
					fieldLabel:i18n('i18n.SearchClaim.circulationViews'),
					name:'tranferCount',
					readOnly:true
				},{
					xtype:'hiddenfield',
					name:'id'
				}]
			},Ext.create('TabPanel',{
				id:'claimTabId',
				width:800,
				height:300
			})]
		});

		/**
		 * 详情弹窗
		 */
		Ext.define('ClaimDetailWin',{
			extend:'PopWindow',
			title:i18n('i18n.SearchClaim.claimDetails'),
			closeAction:'hide',
			layout:'fit',
			//表单
			form:null,
			getForm:function(){
				if(Ext.isEmpty(this.form)){
					this.form = Ext.create('PopForm');
				}
				return this.form;
			},
			//创建理赔弹窗
			createRecompenseWindow:null,
			getCreateRecompenseWindow:function(){
				initArea();
				if(Ext.isEmpty(this.createRecompenseWindow)){
					this.createRecompenseWindow = Ext.create('CreateRecompenseWindow');
					this.createRecompenseWindow.parent = this;
				}
				return this.createRecompenseWindow;
			},
			/**
			 * @author 张斌
			 * @constructor 在隐藏之前所有按钮隐藏，清除数据
			 * @time 2013-3-12
			 */
			listeners:{
				beforehide:function(me){
					Ext.getCmp('createRecompenseId').hide();
					Ext.getCmp('sendToAnotherDeptId').hide();
					Ext.getCmp('acceptId').hide();
					Ext.getCmp('remitClaimId').hide();
					Ext.getCmp('remitClaimCancelId').hide();
					Ext.getCmp('followUpId').hide();
					Ext.getCmp('saveAfterAcceptId').hide();
					followUpGrid.getStore().removeAll();
					operateLogGrid.getStore().removeAll();
					Ext.getCmp('popFormId').getForm().reset();
				},
				beforeshow:function(me){
					Ext.getCmp('claimTabId').setActiveTab(0);
				},
				close:function(){
					claimStore.loadPage(1);
				}
			},
			/**
			 * 1、理赔创建操作
			 */
			recompenseCreate:function(){
				//Ext.getCmp('followUpId').setVisible(false);
				
				this.up('window').getCreateRecompenseWindow();
				//显示window
       			this.up('window').getCreateRecompenseWindow().show();
				//设置理赔类型
				Ext.getCmp(CONFIGNAME.get('recompenseType')).setReadOnly(true);
				Ext.getCmp(CONFIGNAME.get('recompenseType')).addCls('readonly');
				var form = Ext.getCmp('popFormId');
				var recompenseType = form.getForm().findField('recompenseMethod').getValue();
				Ext.getCmp(CONFIGNAME.get('recompenseType')).setValue(recompenseType);
				if(recompenseType=='unbilled'){//未开单
       			   Ext.getCmp(CONFIGNAME.get('waybillNumber')).emptyText = i18n('i18n.recompense.ErroeNum');
      			   Ext.getCmp(CONFIGNAME.get('waybillNumber')).initField( );
      			   //三个combox的数据都修改
      			   Ext.getCmp(CONFIGNAME.get('insurType')).reset();
      			   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().removeAll(true);
      			   
      			   var dataInsurTypeUnbilled = new Array();
      			   for(var i = 0;i<DataDictionary.DANGER_TYPE.length;i++){
    				   if(DataDictionary.DANGER_TYPE[i].code !='FALSELY_CLAIM'){
    					   dataInsurTypeUnbilled.push(DataDictionary.DANGER_TYPE[i]);
    				   }
    			   }    			   
      			   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().loadData(dataInsurTypeUnbilled);
      			   Ext.getCmp('fastRecompenseMethod_id').setDisabled(true);
      			   Ext.getCmp('normalRadio').setValue(true);
       			 }else if(recompenseType=='lost_goods'){//丢货理赔
       				var dataInsurType = new Array();
      			   for(var i = 0;i<DataDictionary.DANGER_TYPE.length;i++){
      				   if(DataDictionary.DANGER_TYPE[i].code =='PIECE_LOST'){
      					   dataInsurType.push(DataDictionary.DANGER_TYPE[i]);
      				   }
      				   if(DataDictionary.DANGER_TYPE[i].code =='TICKET_LOST'){
      					   dataInsurType.push(DataDictionary.DANGER_TYPE[i]);
      				   }
      			   }
      			   //三个combox的数据都修改
      			   Ext.getCmp(CONFIGNAME.get('insurType')).reset();
      			   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().removeAll(true);
      			   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().loadData(dataInsurType);
      			   Ext.getCmp(CONFIGNAME.get('waybillNumber')).emptyText = i18n('i18n.recompense.orderNum');
    			       Ext.getCmp(CONFIGNAME.get('waybillNumber')).initField( );
    			       Ext.getCmp('fastRecompenseMethod_id').setDisabled(false);
      		     }else{//abnormal异常签收
      		       var dataInsurType = new Array();
       			   for(var i = 0;i<DataDictionary.DANGER_TYPE.length;i++){
       				   if(DataDictionary.DANGER_TYPE[i].code =='PIECE_LOST'||
       						DataDictionary.DANGER_TYPE[i].code =='TICKET_LOST'){	
       				   }else{
       					  dataInsurType.push(DataDictionary.DANGER_TYPE[i]);
       				   }
       				   
       			   }
       			
       	
       			   //三个combox的数据都修改
       			   Ext.getCmp(CONFIGNAME.get('insurType')).reset();
       			   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().removeAll(true);
       			   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().loadData(dataInsurType);
       			   Ext.getCmp(CONFIGNAME.get('waybillNumber')).emptyText = i18n('i18n.recompense.orderNum');
 			       Ext.getCmp(CONFIGNAME.get('waybillNumber')).initField( );
 			       Ext.getCmp('fastRecompenseMethod_id').setDisabled(false);
      		    }
       			 //设置运单号
       			Ext.getCmp(CONFIGNAME.get('waybillNumber')).setReadOnly(true);
       			var waybillNumber = form.getForm().findField('waybillNumber').getValue();
       			Ext.getCmp(CONFIGNAME.get('waybillNumber')).setValue(waybillNumber);
       			var me = Ext.getCmp('dangerInfoForm');
       			var dangerTime = Ext.getCmp(CONFIGNAME.get('insurDate')).getValue();
       			var dangerType = Ext.getCmp(CONFIGNAME.get('insurType')).getValue();
       			var dangerNumber = Ext.getCmp(CONFIGNAME.get('insurQuantity')).getValue();
       			var deptId = Ext.getCmp(CONFIGNAME.get('deptId')).getValue();
       			
       			var successFn = function(json){
       				me.getForm().reset();
       				var waybillModel = new WaybillModel(json.waybill);
       				me.getForm().loadRecord(waybillModel);
       				var sendDate = null;
       				if(!Ext.isEmpty(waybillModel.get(CONFIGNAME.get('sendDate')))){
       					sendDate = waybillModel.get(CONFIGNAME.get('sendDate')).format('yyyy-MM-dd');
       				}
       				Ext.getCmp(CONFIGNAME.get('sendDate')).setValue(sendDate);
       				Ext.getCmp(CONFIGNAME.get('insurDate')).setValue(dangerTime);
       				Ext.getCmp(CONFIGNAME.get('insurType')).setValue(dangerType);
       				Ext.getCmp(CONFIGNAME.get('insurQuantity')).setValue(dangerTime);
       				Ext.getCmp(CONFIGNAME.get('deptId')).setValue(deptId);
       				//索赔方
       				var claimer = form.getForm().findField('claimer').getValue();
       				var claimParty = null;
           			if(claimer=='SHIPPER'){//发货方
           				claimParty = 1;
           			}else  if(claimer=='CONSIGNEE'){//收货方
           				claimParty = 2;
           			}else{
           				claimParty = 0;
           			}
           		    Ext.getCmp(CONFIGNAME.get('claimParty')).setValue(claimParty);
           		       //如果是区域客服角色，不能选择快速理赔		
       				if(Ext.Array.contains(User.roleids,'9')){
		  			Ext.getCmp('fastRecompenseMethod_id').setDisabled(true);
				}
       			};
       			var failureFn = function(json){
       				if(Ext.isEmpty(json)){
       					MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
       					//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
       				}else{
       					MessageUtil.showErrorMes(json.message);
       					me.getForm().reset();
       					Ext.getCmp(CONFIGNAME.get('sendDate')).setValue(sendDate);
       					Ext.getCmp(CONFIGNAME.get('insurDate')).setValue(dangerTime);
       					Ext.getCmp(CONFIGNAME.get('insurType')).setValue(dangerType);
       					Ext.getCmp(CONFIGNAME.get('insurQuantity')).setValue(dangerTime);
       					Ext.getCmp(CONFIGNAME.get('deptId')).setValue(deptId);
       				}
       			};
       			if(Ext.getCmp(CONFIGNAME.get('recompenseType')).isValid()){
       				var recompenseType = Ext.getCmp(CONFIGNAME.get('recompenseType')).getValue();
       				var param = {'recomSearchCondition':{'waybillNum':waybillNumber,'recompenseType':recompenseType}};
       				recompenseData.getWaybillByNum(param,successFn,failureFn);
       			}else{
       				MessageUtil.showMessage(i18n('i18n.recompense.pleaseSelectRecompenseType'));
       			}
       			//索赔人和索赔金额
       		    var companyName = form.getForm().findField('claimPerson').getValue();
       		    var recompenseAmount = form.getForm().findField('claimAmount').getValue();
       		    Ext.getCmp(CONFIGNAME.get('companyName')).setValue(companyName);
       		    Ext.getCmp(CONFIGNAME.get('recompenseAmount')).setValue(recompenseAmount);
       			
			},
			/**
			 * 2、发送给对方部门处理操作
			 */
			sendToAnotherDept:function(){
				var parent = this.up('window');
				Ext.create('PopWindow',{
					title:i18n('i18n.SearchClaim.sendEachOtherDepartments'),
					layout:'fit',
					items:[{
						xtype:'form',
						id:'reason',
						boeder:false,
						items:[{
							width:500,
							height:80,
							xtype:'textarea',
							name:'reason',
							maxLength:300,
							maxLengthText:'您好，发送原因不能超过300字符',
							labelWidth:70,
							allowBlank:false,
							blankText:i18n('i18n.SearchClaim.sendReasonsNotEmpty'),
							fieldLabel:i18n('i18n.SearchClaim.sendReasons')
						}]
					}],
					buttons:[{
						text:i18n('i18n.recompense.commit'),
						id:'sendCommitBtn',
						handler:function(){
							var me = this.up('window');
							var form = Ext.getCmp('reason').getForm();
							if(!form.isValid()){
								return;
							}
							var btn = this;
							btn.setDisabled(true);
							var param = {
									'reason' :form.findField('reason').getValue(),
									'claimId':Ext.getCmp('popFormId').getForm().findField('id').getValue()
							};
							var successFn = function(json){
								btn.setDisabled(false);
								MessageUtil.showInfoMes(i18n("i18n.SearchClaim.sendSuccess"));
								//claimStore.loadPage(1);
								me.close();
								parent.close();
							};
							var failFn = function(json){
								btn.setDisabled(false);
								if(Ext.isEmpty(json)){
									MessageUtil.showErrorMes(i18n('i18n.SearchClaim.accessTimeout'));
								}else{
									MessageUtil.showErrorMes(json.message);
								}
							};
							MessageUtil.showQuestionMes(
									i18n('i18n.SearchClaim.sendDeptOpThis')+
									i18n('i18n.SearchClaim.noToInfoOp')+
									i18n('i18n.SearchClaim.soLongToClaimInfoIsCommit'),function(e){
										if(e=='yes'){
											sendToAnotherDept(param,successFn,failFn);
										}
							});
						}
					},{
						text:i18n('i18n.recompense.close'),
						handler:function(){
							this.up('window').close();
						}
					}]
				}).show();
			},
			/**
			 * 3、免赔操作
			 */
			remitClaim:function(){
				var me = this.up('window');
				var param = {
						'claimId':Ext.getCmp('popFormId').getForm().findField('id').getValue()
				};
				var successFn = function(json){
					MessageUtil.showInfoMes(i18n("i18n.SearchClaim.freePeiSuccess"));
					//claimStore.loadPage(1);
					me.close();
				};
				var failFn = function(json){
					if(Ext.isEmpty(json)){
						MessageUtil.showErrorMes(i18n('i18n.SearchClaim.accessTimeout'));
					}else{
						MessageUtil.showErrorMes(json.message);
					}
				};
				MessageUtil.showQuestionMes(
						i18n('i18n.SearchClaim.sureArticleClaimsDeductibleProcessing'),function(e){
							if(e=='yes'){
								remitClaimByClaimId(param,successFn,failFn);
							}
				});
			},
			/**
			 * 4、跟进操作
			 */
			followUp:function(){
				Ext.create('PopWindow',{
					title:i18n('i18n.recompense.sendMessage'),
					layout:'fit',
					width:400,
					height:200,
//					frame:true,
					items:[{
//							width:500,
//							height:80,
						xtype:'textarea',
						id:'messageTextarea',
						name:'message',
						labelWidth:70,
						maxLength:300,
						maxLengthText:'您好，跟进消息最多输入300字符',
						allowBlank:false,
						blankText:i18n('i18n.SearchClaim.sendReasonsNotEmpty'),
						fieldLabel:i18n('i18n.recompense.sendMessage')
					}],
					buttons:[{
						text:i18n('i18n.recompense.commit'),
						handler:function(){
							var me = this.up('window');
							if(!Ext.getCmp('messageTextarea').isValid()){
								return;
							}
							var param = {'claimMessage':{
							'content' :Ext.getCmp('messageTextarea').getValue(),
							'claimId':Ext.getCmp('popFormId').getForm().findField('id').getValue()
								
							}
									
							};
							var successFn = function(json){
								MessageUtil.showInfoMes(i18n("i18n.recompense.flowUpSuccess"));
								me.close();
								followUpGrid.getStore().load();
							};
							var failFn = function(json){
								if(Ext.isEmpty(json)){
									MessageUtil.showErrorMes(i18n('i18n.SearchClaim.accessTimeout'));
								}else if(Ext.isEmpty(json.message)){
									MessageUtil.showErrorMes(json);
								}
							};
							addFollowClaimMessage(param,successFn,failFn);
						}
					},{
						text:i18n('i18n.recompense.close'),
						handler:function(){
							this.up('window').close();
						}
					}]
				}).show();
			},
			/**
			 * 5、关闭操作
			 */
			closeFn:function(){
				var form = Ext.getCmp('popFormId').getForm();
				form.findField('claimPerson').setReadOnly(true);
				form.findField('claimAmount').setReadOnly(true);
				this.up('window').close();
				//claimStore.loadPage(1);

			},
			/**
			 * @author 张斌
			 * @constructor 同意受理提交操作
			 * @time 2013-3-12
			 */
			acceptCommitFn:function(){
				var form = Ext.getCmp('popFormId');
				if(!form.getForm().isValid()){
					return;
				}
				var param = {
						'claim':{
							'id':Ext.getCmp('popFormId').getForm().findField('id').getValue(),
							'claimPerson':form.getForm().findField('claimPerson').getValue(),//索赔人
							'claimAmount':form.getForm().findField('claimAmount').getValue(),//索赔金额
							'claimRequire':Ext.getCmp('claimRequireId').getValue(),//索赔要求
							'tranferCount':form.getForm().findField('tranferCount').getValue()//流转次数

						}
						
				};
				var successFn = function(json){
					MessageUtil.showInfoMes(i18n("i18n.SaveClaim.commitSuccess"));
					//保存成功之后，现将所有按钮隐藏，然后按照当前状态去设置按钮
					//清掉所有数据
					Ext.getCmp('createRecompenseId').hide();
					Ext.getCmp('sendToAnotherDeptId').hide();
					Ext.getCmp('acceptId').hide();
					Ext.getCmp('remitClaimId').hide();
					Ext.getCmp('remitClaimCancelId').hide();
					Ext.getCmp('followUpId').hide();
					Ext.getCmp('saveAfterAcceptId').hide();
					followUpGrid.getStore().removeAll();
					operateLogGrid.getStore().removeAll();
					Ext.getCmp('popFormId').getForm().reset();
					//重新设置按钮
					if(json.claim.claimStatus=='WAIT_DO'){//待处理
						/**
						 * 显示理赔创建、发送对方部门处理、免赔、跟进、关闭
						 */
							Ext.getCmp('createRecompenseId').setVisible(true);
						if(isPermission('/recompense/sendToAnotherDept.action')){
							Ext.getCmp('sendToAnotherDeptId').setVisible(true);
						}
						if(isPermission('/recompense/remitClaimByClaimId.action')){
							Ext.getCmp('remitClaimId').setVisible(true);
						}
							Ext.getCmp('followUpId').setVisible(true);
					};
					if(json.claim.claimStatus=='WAIT_ACCEPT'){//待受理
						/**

						 * 显示发送对方部门处理、同意受理，跟进，关闭
						 */
						if(isPermission('/recompense/sendToAnotherDept.action')){
							Ext.getCmp('sendToAnotherDeptId').setVisible(true);
						}
						if(isPermission('/recompense/acceptClaim.action')){
							Ext.getCmp('acceptId').setVisible(true);
						}
							Ext.getCmp('followUpId').setVisible(true);
					};
					if(json.claim.claimStatus=='HANDLED'){//已处理
						/**

						 * 显示关闭
						 */		
//						Ext.getCmp('followUpId').setVisible(true);
					};
					if(json.claim.claimStatus=='REMITTED'){//已免赔
						/**
						 * 显示免赔解冻、跟进、关闭
						 */	
						if(isPermission('/recompense/cancelRemitClaim.action')){
							Ext.getCmp('remitClaimCancelId').setVisible(true);
						}
						
						Ext.getCmp('followUpId').setVisible(true);
					};
					//加载数据
					var model = Ext.create('ClaimModel',json.claim);
					Ext.getCmp('popFormId').loadRecord(model);
					Ext.getCmp('popFormId').getForm().findField('showTime').setValue(new Date(model.get('showTime')));
					Ext.getCmp('claimRequireId').setValue(json.claim.claimRequire);
					//将设置可编辑的，弄成不可编辑
					var form = Ext.getCmp('popFormId');
					form.getForm().findField('claimPerson').setReadOnly(true);
					form.getForm().findField('claimAmount').setReadOnly(true);
					Ext.getCmp('claimRequireId').setReadOnly(true);
					claimStore.loadPage(1);
					followUpGrid.getStore().load();//加载跟进信息
					operateLogGrid.getStore().load();//加载操作记录
				};
				var failFn = function(json){
					if(Ext.isEmpty(json)){
						MessageUtil.showErrorMes(i18n("i18n.SearchClaim.timeOut"));
					}else{
						MessageUtil.showErrorMes(json.message);
					}
				};
				acceptClaim(param,successFn,failFn);
			},
			
			/**
			 * @author 张斌
			 * @constructor 同意受理操作
			 * @time 2013-3-12
			 */
			acceptFun:function(){
				var form = Ext.getCmp('popFormId');
				/**
				 * 隐藏理赔创建、发送给对方部门处理、跟进、同意受理、免赔等按钮，显示保存按钮
				 */
				Ext.getCmp('createRecompenseId').setVisible(false);
				Ext.getCmp('sendToAnotherDeptId').setVisible(false);
				Ext.getCmp('acceptId').setVisible(false);
				Ext.getCmp('followUpId').setVisible(false);
				Ext.getCmp('remitClaimId').setVisible(false);
				Ext.getCmp('saveAfterAcceptId').setVisible(true);
				
				form.getForm().findField('claimPerson').addListener('blur', function(me){					
					if((me.readOnly==false)&&(me.getValue()!=form.getForm().findField('linkMan').getValue())){
						MessageUtil.showMessage(i18n('i18n.SaveClaim.thisClaimsManNameNotSamePleaseCheck'));
					}				
				});
				form.getForm().findField('claimPerson').reset();
				form.getForm().findField('claimPerson').setReadOnly(false);
				form.getForm().findField('claimPerson').allowBlank=false;
				form.getForm().findField('claimPerson').blankText='您好，索赔人不能为空';
				form.getForm().findField('claimPerson').doComponentLayout();
				
				form.getForm().findField('claimAmount').reset();
				form.getForm().findField('claimAmount').setReadOnly(false);
				form.getForm().findField('claimAmount').allowBlank=false;
				form.getForm().findField('claimAmount').blankText='您好，索赔金额不能为空';
				form.getForm().findField('claimAmount').doComponentLayout();
				
				Ext.getCmp('claimRequireId').reset();
				Ext.getCmp('claimRequireId').setReadOnly(false);
			},
			/**
			 * 8、免赔解冻操作
			 */
			remitClaimCancel:function(){
				var me = this.up('window');
				var param = {
						'claimId':Ext.getCmp('popFormId').getForm().findField('id').getValue()
				};
				var successFn = function(json){
					MessageUtil.showInfoMes(i18n("i18n.SearchClaim.freePeiJDSuccess"));
					me.close();
					//claimStore.loadPage(1);

				};
				var failFn = function(json){
					if(Ext.isEmpty(json)){
						MessageUtil.showErrorMes(i18n("i18n.SearchClaim.timeOut"));
					}else{
						MessageUtil.showErrorMes(json.message);
					}
				};
				MessageUtil.showQuestionMes(i18n('i18n.SearchClaim.whetherTheClaimThawingTreatment'),function(e){
							if(e=='yes'){
								cancelRemitClaim(param,successFn,failFn);
							}
				});
			},
			buttons:null,
			getButtons:function(){
				var me = this;
				return [{
					text:i18n('i18n.SearchClaim.claimsCreated'),
					hidden:true,
					id:'createRecompenseId',
					handler:me.recompenseCreate
				},{
					text:i18n('i18n.SearchClaim.sendEachOtherDepartments'),
					hidden:true,
					id:'sendToAnotherDeptId',
					handler:me.sendToAnotherDept
				},{
					text:i18n('i18n.SearchClaim.agreedToAccept'),
					id:'acceptId',
					hidden:true,
					handler:me.acceptFun
				},{
					text:i18n('i18n.SearchClaim.deductible'),
					id:'remitClaimId',
					hidden:true,
					handler:me.remitClaim
				},{
					text:i18n('i18n.SearchClaim.deductibleThaw'),
					id:'remitClaimCancelId',
					hidden:true,
					handler:me.remitClaimCancel
				},{
					text:i18n('i18n.recompense.btnFellow'),
					id:'followUpId',
					hidden:true,
					handler:me.followUp
				},/*{
					text:i18n('i18n.recompense.save'),
					id:'saveId',
					hidden:true,
					handler:me.saveFn
				},*/{
					/**
					 * @author 张斌
					 * @constructor 同意受理之后的保存按钮
					 * @time 2013-3-12
					 */
					text:i18n('i18n.recompense.save'),
					id:'saveAfterAcceptId',
					hidden:true,
					handler:me.acceptCommitFn
				},{
					text:i18n('i18n.recompense.close'),
					handler:me.closeFn
				}];
			},
			initComponent : function() {
				var me = this;
				me.buttons = me.getButtons();
				me.items = me.getForm();
				this.callParent();
			}
		});
		
		
		/****************************中间按钮****************************/
		Ext.define('ButtonDemoPanel',{
			extend:'NormalButtonPanel' //--第一步,定义一个主panel,继承NormalButtonPanel
			,items:null,
			initComponent:function(){
				this.items = this.getItems();
				this.callParent();
			},
			getItems:function(){
				var me = this;
				return [{
					xtype:'leftbuttonpanel',
					items:[{
							xtype:'button',    //--向右部的按钮容器中，添加具体的按钮
							name:'claimSaveBtn',
							text:i18n('i18n.SearchClaim.claimsProcessing'),//"索赔处理",
							handler:function(){
								gridPanel.claimSave();
							}
						}]
					
					 //--第二步,定义一个位于左边的按钮容器,继承leftbuttonpanel
				},{
					xtype:'middlebuttonpanel' //--定义一个位于中间的空容器，用于填充								中间空白部分,继承middlebuttonpanel
				},{
						xtype:'rightbuttonpanel', //--定义一个位于右边的按钮容器,继承								rightbuttonpanel
						items:[{
							xtype:'button',    //--向右部的按钮容器中，添加具体的按钮
							name:'serachLogBtn',
							text:i18n("i18n.recompense.search"),//查询
							handler:function(){
								if(!queryFormPanel.getForm().isValid()){
									return;
								}
								var startDate = queryFormPanel.getForm().findField('startDate').getValue();
								var endDate = queryFormPanel.getForm().findField('endDate').getValue();
								
								if(startDate>endDate){
									MessageUtil.showErrorMes('您好，起始时间不能大于结束时间');
									return;
								}
								if ((endDate-startDate)/(24*3600*1000)>31) {
									MessageUtil.showMessage('您好，查询时间范围不能大于一个月。');
									 return;
								}
								claimStore.load();
							}
						},{
							xtype:'button',    //--向右部的按钮容器中，添加具体的按钮
							name:'ResetLogBtn',
							text:i18n('i18n.recompense.reset'),//重置
							handler:function(){
								queryFormPanel.getForm().reset();
							}
						}]
				}]
			}
		});
		/*******************************底部gridPanel****************************/
		var queryFormPanel = Ext.create('QueryForm',{id:'queryFormPanelId',region: 'north'});
		var claimStore = Ext.create('ClaimStore',{autoLoad:true});
		
		var processDepts = new Array();
		claimStore.on('beforeload',function(store,operation,obj){
			processDepts.push(queryFormPanel.getForm().findField('reportDept').getValueId());
			Ext.apply(operation,{
				params : {
						'claimCondition.waybillNumber':Ext.util.Format.trim(queryFormPanel.getForm().findField('waybillNumber').getValue()),
						'claimCondition.processDepts':processDepts,
						'claimCondition.claimStatus':queryFormPanel.getForm().findField('claimStatus').getValue(),
						'claimCondition.startTime':queryFormPanel.getForm().findField('startDate').getValue(),
						'claimCondition.endTime':queryFormPanel.getForm().findField('endDate').getValue()
				}
			});
			processDepts = new Array();
		});
		var gridPanel = Ext.create('SearchGridPanel',{
			id:'buttomGrid',region: 'center',
			store:claimStore,
			/**
			 * @author 张斌
			 * @constructor 不能一次创建一个win,建立一个单例的window，隐藏的时候将这个window设置为原始状态即可
			 * @time 2013-3-12
			 */
			//索赔处理弹出框
			claimDetailWin:null,
			getClaimDetailWin:function(){
				if(Ext.isEmpty(this.claimDetailWin)){
					this.claimDetailWin =  Ext.create('ClaimDetailWin');
					this.claimDetailWin.parent = this;
				}
				return this.claimDetailWin;
			},
			/**
			 * @author 张斌
			 * @constructor 索赔处理方法
			 * @time 2013-3-12
			 */
			claimSave:function(){
				var me = this;
				var record = me.getSelectionModel().getSelection()[0];
				if(Ext.isEmpty(record)){
					return;
				}
				var param = {
						'claimId':record.data.id
				};
				var successFn = function(json){
					var win  = me.getClaimDetailWin();
					
					if(json.claim.claimStatus=='WAIT_DO'){//待处理
						/**
						 * 显示理赔创建、发送对方部门处理、免赔、跟进、关闭
						 */
						if(json.claim.processDeptName == User.deptName){
							Ext.getCmp('createRecompenseId').setVisible(true);
							if(isPermission('/recompense/sendToAnotherDept.action')){
								Ext.getCmp('sendToAnotherDeptId').setVisible(true);
							}
							if(isPermission('/recompense/remitClaimByClaimId.action')){
								Ext.getCmp('remitClaimId').setVisible(true);
							}
							
							
							if(!Ext.isEmpty(json.claim.arrivedDept)){
								Ext.getCmp('sendToAnotherDeptId').setDisabled(false);
							}else{
								Ext.getCmp('sendToAnotherDeptId').setDisabled(true);
							}
						}
						Ext.getCmp('followUpId').setVisible(true);
						win.show();
					};
					if(json.claim.claimStatus=='WAIT_ACCEPT'){//待受理
						/**

						 * 显示发送对方部门处理、同意受理，跟进，关闭
						 */
						if(isPermission('/recompense/sendToAnotherDept.action')){
							Ext.getCmp('sendToAnotherDeptId').setVisible(true);
						}
						if(isPermission('/recompense/acceptClaim.action')){
							Ext.getCmp('acceptId').setVisible(true);
						}
						if(json.claim.processDept!=User.deptId){
							Ext.getCmp('sendToAnotherDeptId').setVisible(false);
							Ext.getCmp('acceptId').setVisible(false);
						}
						Ext.getCmp('followUpId').setVisible(true);
						win.show();
					};
					if(json.claim.claimStatus=='HANDLED'){//已处理
						/**

						 * 显示关闭
						 */		
//						Ext.getCmp('followUpId').setVisible(true);
						win.show();
					};
					if(json.claim.claimStatus=='REMITTED'){//已免赔
						/**
						 * 显示免赔解冻、跟进、关闭
						 */	
						if(isPermission('/recompense/cancelRemitClaim.action')){
							Ext.getCmp('remitClaimCancelId').setVisible(true);
						}
						
						Ext.getCmp('followUpId').setVisible(true);
						win.show();
					};
					var model = Ext.create('ClaimModel',json.claim);
					Ext.getCmp('popFormId').loadRecord(model);
					Ext.getCmp('popFormId').getForm().findField('showTime').setValue(new Date(model.get('showTime')));
					Ext.getCmp('claimRequireId').setValue(json.claim.claimRequire);
				};
				var failFn = function(json){
					if(Ext.isEmpty(json)){
						MessageUtil.showErrorMes(i18n("i18n.SearchClaim.timeOut"));
					}else{
						MessageUtil.showErrorMes(json.message);
					}
				};
				searchClaimById(param,successFn,failFn);
			},
			selModel:Ext.create('Ext.selection.CheckboxModel',{mode:'single'}),//选择框
			columns:[{
				text:i18n('i18n.recompense.num'),xtype:'rownumberer',width:40
			},{
				text:i18n('i18n.recompense.orderNumOrErroeNum'),dataIndex:'waybillNumber',width:120
			},{
				text:i18n('i18n.recompense.reportDept'),dataIndex:'reportDeptName',width:160
			},{
				text:i18n('i18n.recompense.reportPerson'),dataIndex:'reporterName',width:60
			},{
				text:i18n('i18n.SearchClaim.currentProcessingDepartment'),dataIndex:'processDeptName',width:160
			},{
				text:i18n('i18n.recompense.handlePerson'),dataIndex:'processorName',width:60
			},{
				text:i18n('i18n.SearchClaim.claim'),dataIndex:'showTime',width:120,
				renderer:function(value) {
					if(value != null){return Ext.Date.format(new Date(value),'Y-m-d H:i');
					}else{return null;}
				}
			},{
				text:i18n('i18n.SearchClaim.claimStatus'),dataIndex:'claimStatus',width:75,
				renderer:function(value){
						return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.CLAIM_STATUS);
					}
			},{
				text:i18n('i18n.SearchClaim.spreadTheNumberOf'),dataIndex:'tranferCount',width:80
			}],
			listeners:{
				'itemdblclick':function(me,record,item,index,e,eObj){
					me.up('grid').claimSave();
				}
			},
			bbar:Ext.create("Ext.toolbar.Paging", {
				id : "BBar",
				store : claimStore,
				displayInfo : true,
				items : ["-", {
					text : i18n("i18n.recompense.page_count"),				//i18n('i18n.recompense.page_count')
					xtype : "tbtext"
				}, Ext.create("Ext.form.ComboBox", {
					id:'showNum',
					width : 40,
					value:'10',
					editable:false,
					triggerAction : "all",
					forceSelection : true,
					editable : false,
					name : "comboItem",
					displayField : "value",
					valueField : "value",
					queryMode : "local",
					store :Ext.create('Ext.data.Store',{
						fields:['value'],
						data:[{
							value:'10'
						},{
							value:'25'
						},{
							value:'50'
						}]
					}),
					listeners : {
						select : {
							scope : this,
							fn : function(m, l) {
								var k = Ext.getCmp("buttomGrid").store.pageSize;
								var n = parseInt(m.value);
								if(k != n) {
									Ext.getCmp("buttomGrid").store.pageSize = n;
									Ext.getCmp("BBar").moveFirst()
								}
							}
						}
					}
				}), {
					text : i18n('i18n.recompense.pageNumber')		//i18n('i18n.recompense.pageNumber')
					,xtype : "tbtext"
				}]
			})
		});
		
		/**.
		 * <p>
		 * 理赔上报主页面</br>
		 * </p>
		 * @author  张斌
		 * @时间    2012-04-06
		 */
		Ext.define('CreateRecompenseWindow',{
			extend:'PopWindow',
			closeAction:'destory',
			height:580,
			width:780,
			layout: {
		        type: 'vbox',
		        align: 'stretch'
		    },
		    listeners:{
		    	beforehide:function(){
		    		//清除数据
		    		Ext.getCmp(CONFIGNAME.get('recompenseType')).reset();
		    		Ext.getCmp('dangerInfoForm').getForm().reset();
		    		Ext.getCmp('recompenseInfoForm').getForm().reset();
		    		Ext.getCmp('dangerInfoGrid').getStore().removeAll();
		    		Ext.getCmp('recompenseListGird').getStore().removeAll();
		    		Ext.getCmp('goodsTranceGird').getStore().removeAll();
		    		Ext.getCmp('attachmentGrid').getStore().removeAll();
		    		Ext.getCmp('goodsTrancesListTotalAmount').setValue('<a  style="color:red;font-size:25px;">'+i18n('i18n.recompense.totalAmountCount')+'0'+'</a>');
		    		Ext.getCmp('recompenseListTotalAmount').setValue('<a  style="color:red;font-size:25px;">'+i18n('i18n.recompense.totalAmountCount')+'0'+'</a>');
		    	}
		    },
		    getItems:function(){
		    	return [new RecompenseType(),new DangerInfo(),new RecompenseInfoTab(),new SaveButtonPanel()];
		    },
		    initComponent:function(){
				var me = this;
			    me.items = me.getItems();
			    var dataInsurType = new Array();
				   for(var i = 0;i<DataDictionary.DANGER_TYPE.length;i++){
					   if(DataDictionary.DANGER_TYPE[i].code =='PIECE_LOST'||DataDictionary.DANGER_TYPE[i].code =='TICKET_LOST'){
						  
					   }else{
						   dataInsurType.push(DataDictionary.DANGER_TYPE[i]);
					   }
				   }
				   //三个combox的数据都修改
				   Ext.getCmp(CONFIGNAME.get('insurType')).reset();
				   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().removeAll(true);
				   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().loadData(dataInsurType);
				   Ext.getCmp('recompenseListAdd').setDisabled(false);
				 Ext.getCmp('recompenseListUpdate').setDisabled(false);
				 Ext.getCmp('recompenseListDelete').setDisabled(false);
				 Ext.getCmp('tranceListAdd').setDisabled(false);
				 Ext.getCmp('tranceListUpdate').setDisabled(false);
				 Ext.getCmp('tranceListDelete').setDisabled(false);
				this.callParent();
			}
		});
		/**.
		 * <p>
		 * 按照规范写出底角的按钮panel</br>
		 * </p>
		 * @author  张斌
		 * @时间    2012-04-06
		 */
		Ext.define('SaveButtonPanel',{
			extend:'PopButtonPanel',
			items:null,
			//2012-7-17
			//张斌
			//加上margin使其离底部有一定距离，而不影响别的页面布局，修改UAT CRM-2226
			margin:'0 0 0 0',
			weight:400,
			initComponent:function(){
				this.items = this.getItems();
				this.callParent();
			},
			/**.
			 * <p>
			 * 理赔上报提交操作</br>
			 * </p>
			 * @author  张斌
			 * @时间    2012-04-06
			 */
			saveRecompense:function(savebtn){
				var me = this;
				if(!Ext.getCmp('recompenseTypeForm').getForm().isValid()){
					return;
				}
				if(!Ext.getCmp('dangerInfoForm').getForm().isValid()){
					return;
				}
				if(!Ext.getCmp('recompenseInfoForm').getForm().isValid()){
					MessageUtil.showMessage(i18n('i18n.recompense.haveMoreRecompenseInfo'));
					return;
				}
				var dangerInfoArray = new Array();
				Ext.getCmp('dangerInfoGrid').getStore().each(function(record){
					dangerInfoArray.push(record.data);
				});
				if(dangerInfoArray.length==0){
					MessageUtil.showMessage(i18n('i18n.recompense.dangerInfoMustHaveOne'));
					return ;
				}
				var recompenseListArray = new Array();
				Ext.getCmp('goodsTranceGird').getStore().each(function(record){
					recompenseListArray.push(record.data);
				});
				var recompenseMethod = Ext.getCmp(CONFIGNAME.get('recompenseMethod')).getValue().recompenseMethod;
				var transType=Ext.getCmp(CONFIGNAME.get('transType')).getValue();
				//判断索赔金额，与索赔清单总金额是否相等
				//张斌
				//2012-07-17
				//start
				if(recompenseMethod=='normal'){
					var recompenseListTotalCount = 0;
					Ext.getCmp('recompenseListGird').getStore().each(function(record){
						recompenseListTotalCount = recompenseListTotalCount+record.get('amount');
					});
					var claimAmount = Ext.getCmp(CONFIGNAME.get('recompenseAmount')).getValue();
					if(Ext.isEmpty(claimAmount)){
						claimAmount = 0;
					}
					if(claimAmount!=recompenseListTotalCount){
						MessageUtil.showMessage(i18n('i18n.recomepnse.recompenseTotalAmountCountEquelClaimAmount'));
						return ;
					}
				}
				//end
				if(recompenseMethod=='normal'){
					var length = recompenseListArray.length
					if(length==0){
						MessageUtil.showMessage(i18n('i18n.recompense.tranceListHaveOne'));
						return ;
					}else{
						Ext.getCmp('recompenseListGird').getStore().each(function(record){
							recompenseListArray.push(record.data);
						});
						if(recompenseListArray.length == length){
							MessageUtil.showMessage(i18n('i18n.recompense.recompenseListHaveOne'));
							return ;
						}
					}
				}else if(recompenseMethod=='fast'){
					var claimAmount = Ext.getCmp(CONFIGNAME.get('recompenseAmount')).getValue();
					var insurAmount = Ext.getCmp(CONFIGNAME.get('insurAmount')).getValue();
					if(Ext.isEmpty(claimAmount)){
						claimAmount = 0
					}
					if(Ext.isEmpty(insurAmount)){
						insurAmount = 0
					}
					if(claimAmount>insurAmount && transType != 'TRANS_EXPRESS'){
						MessageUtil.showMessage(i18n('i18n.recompense.fastAmountCompare'));
						return ;
					}
					//当快递快速理赔是金额不能超过200
					if(transType=='TRANS_EXPRESS'&&claimAmount>200){
						MessageUtil.showMessage(i18n('i18n.recompense.fastExpressAmount'));
						return;
					}
				}
				var attachmentListArray = new Array();
				Ext.getCmp('attachmentGrid').getStore().each(function(record){
					attachmentListArray.push(record.data);
				});
				var waybill = new WaybillModel();
				Ext.getCmp('dangerInfoForm').getForm().updateRecord(waybill);
				//去掉空格
				waybill.set(CONFIGNAME.get('waybillNumber'),waybill.get(CONFIGNAME.get('waybillNumber')).trim());
				//获取FROM中的数据放到RecompenseApp对象中
				var recompenseApp = new RecompenseApp();
				Ext.getCmp('dangerInfoForm').getForm().updateRecord(recompenseApp);
				recompenseApp.set(CONFIGNAME.get('waybill'),waybill.data);
				//获取客户ID
				var custId = Ext.getCmp(CONFIGNAME.get('custId')).getValue();
				var custmer = {'id':custId};
				recompenseApp.set(CONFIGNAME.get('customer'),custmer);
				Ext.getCmp('recompenseTypeForm').getForm().updateRecord(recompenseApp);
				Ext.getCmp('recompenseInfoForm').getForm().updateRecord(recompenseApp);
				savebtn.setDisabled(true);
				//上报成功之后按钮启用，重置界面，显示信息
				var successFn = function(json){
					savebtn.setDisabled(false);
					me.resetRecompense();
					MessageUtil.showInfoMes(json.message);
					me.up('window').close();
					
					//理赔上报成功之后关闭索赔详情界面
					gridPanel.getClaimDetailWin().close();
				}
				//上报失败之后按钮启用，显示信息
				var failureFn = function(json){
					savebtn.setDisabled(false);
					if(Ext.isEmpty(json)){
						MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
						//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
					}else{
						MessageUtil.showErrorMes(json.message);
					}
				}
				//如果为快速理赔，将索赔金额设置到正常理赔金额中
				if(recompenseMethod=='fast'){
					recompenseApp.set(CONFIGNAME.get('normalAmount'),recompenseApp.get(CONFIGNAME.get('recompenseAmount')));
					recompenseApp.set(CONFIGNAME.get('realAmount'),recompenseApp.get(CONFIGNAME.get('recompenseAmount')));
				}
				if(recompenseMethod=='normal'){
					recompenseApp.set(CONFIGNAME.get('costExplain'),i18n('i18n.recomepnse.defaultFeeInfo'));
				}
				var params = {'recompenseView':{'issueItemAddList':dangerInfoArray,'app':recompenseApp.data
					,'goodsTransAddList':recompenseListArray,'attachmentAddList':attachmentListArray}};
				
//				MessageUtil.showQuestionMes(i18n('i18n.recompense.isSureCommitRecompense'),function(e){
//					if (e == 'yes') {
						recompenseData.createRecompense(params,successFn,failureFn);
//					}
//				});
			},
			/**.
			 * <p>
			 * 理赔上报数据重置操作</br>
			 * </p>
			 * @author  张斌
			 * @时间    2012-04-06
			 */
			resetRecompense:function(){
				Ext.getCmp('recompenseInfoForm').getForm().reset();
				Ext.getCmp('dangerInfoForm').getForm().reset();
				Ext.getCmp('recompenseTypeForm').getForm().reset();
			    //设置OLDVALUE
				Ext.getCmp(CONFIGNAME.get('recompenseType')).oldValue = 'abnormal';
				Ext.getCmp('goodsTranceGird').getStore().removeAll();
				Ext.getCmp('goodsTrancesListTotalAmount').getTotalAmount();
				Ext.getCmp('dangerInfoGrid').getStore().removeAll();
				Ext.getCmp('recompenseListGird').getStore().removeAll();
				Ext.getCmp('recompenseListTotalAmount').getTotalAmount();
				Ext.getCmp('attachmentGrid').getStore().removeAll();
				Ext.getCmp(CONFIGNAME.get('waybillNumber')).emptyText = i18n('i18n.recompense.orderNum');
			   Ext.getCmp(CONFIGNAME.get('waybillNumber')).initField( );
			   Ext.getCmp(CONFIGNAME.get('waybillNumber')).setValue('');
			   Ext.getCmp(CONFIGNAME.get('insurType')).reset();
			   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().removeAll(true);
			   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().loadData(DataDictionary.DANGER_TYPE);
				 Ext.getCmp('fastRecompenseMethod_id').setDisabled(false);
			},
			getItems:function(){
				var me = this;
				return [{
					xtype:'middlebuttonpanel' 
				},{
					xtype:'poprightbuttonpanel',
					items:[{ 
						xtype:'button',
						text:i18n('i18n.recompense.save'),
						handler:function(){
							var btn = this;
								/**
								 * 表单提交优化
								 */
								MessageUtil.showQuestionMes(i18n('i18n.recompense.isSureCommitRecompense'),function(e){
									if (e == 'yes') {
										me.saveRecompense(btn);
									}else{
										return;
									}
								});
							}
						},{
							xtype:'button',
							text:i18n('i18n.recompense.close'),
							handler:function(){
								me.up('window').close();
							}
						}]
				}];
			}
		});
	/*******************************显示viewport****************************/
		Ext.create('Ext.container.Viewport', {
			width:750,
			layout: 'border',
			border:false,
			items:[queryFormPanel,{
				region: 'center',
				layout :'border',
				margin:'0 5 5 5',
				border:false,
				items:[
				Ext.create('ButtonDemoPanel',{region: 'north'}),gridPanel]
			}]
		});
	});
 

