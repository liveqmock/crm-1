//绑定联系人编码
var boundContactNumDataControl= (CONFIG.get("TEST"))? new BoundContactNumDataTest():new BoundContactNumData();
//注册人手机号
var registMobilePhone = null;
//延时
var RelayTime = 60;
//订单来源
var orderSource = null;
//订单号
var orderNum = null;
Ext.onReady(function() {
	var params = ['ORDER_SOURCE',//订单来源
				'MEMBER_GRADE'//客户等级
				];
	initDataDictionary(params);//数据字典
	
	/**
	 * <p>
	 * 解绑页面中联系人编码查询
	 * </p>
	 * @author  张登
	 * @date    2012-03-13
	 */
	Ext.define('NoBoundContactNumPanel',{
		extend:'BasicFormPanel',  
		height:120,
		layout:'border',
		border:false,
		initComponent:function(){
			this.items = this.getItems(); 
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [{
				region:'north',
				height:35,
				layout:{
					type:'table',
					columns:2
				},
				border:false,
				items:[{
					xtype:'textfield',
					labelWidth:75,
					id:'contactNoId',
					width:240,
					fieldLabel:i18n('i18n.MemberCustEditView.contactNo')
				},{
					xtype:'button',
					margin:'0 0 0 10',
					text:i18n('i18n.PotentialCustManagerView.search'),
					handler:function(){
						var successFn = function(json){
							var contactViewModel=new ContactViewModel(json.contactView);
							var form=Ext.getCmp("noBoundContactNumPanelId").getForm();
							form.loadRecord(contactViewModel);
							var contact=json.contactView.contact;
							form.findField("degree").setValue(rendererDictionary(contactViewModel.get("degree"),DataDictionary.MEMBER_GRADE));
							form.findField("contact.member.custNumber").setValue(contact.member.custNumber);
							form.findField("contact.name").setValue(contact.name);
							form.findField("contact.mobilePhone").setValue(contact.mobilePhone);
							form.findField("contact.telPhone").setValue(contact.telPhone);
							form.findField("contact.number").setValue(contact.number);
							if(!Ext.isEmpty(contact.number)){
								Ext.getCmp("registerInfoGird").store.load({params:{'custId':contact.id}});
								Ext.getCmp("chooseForm").getForm().findField("contactNum").setValue(contact.number);
								Ext.getCmp("chooseForm").getForm().findField("custId").reset();//清除数据
							}
						};
						var failureFn = function(json){
							MessageUtil.showErrorMes(json.message);
						};
						var params = {};
						var contactNumber = Ext.getCmp("contactNoId").getValue();
						if(!Ext.isEmpty(contactNumber)){
							params.contactNumber =contactNumber;
							boundContactNumDataControl.queryContactViewByNum(params,successFn,failureFn);
						}
					}
				}]
			},{
				region:'center',
				items:[{
					xtype:'basicformpanel', 
					height:'70',
					margin:'5 0 0 0',
					layout:{
						type:'table',
						columns:4
					},
					defaultType:'textfield',
					defaults:{
						labelWidth :75,
						readOnly:true,
				        labelAlign: 'right',
						width : 180
					},
					items:[{
		                name: 'contact.member.custNumber',
		                fieldLabel: i18n('i18n.ScatterCustManagerView.memberNo')
		            },{
		                name: 'custName',
		                fieldLabel: i18n('i18n.PotentialCustManagerView.customerName')
		            },{
		                name: 'degree',
		                fieldLabel: i18n('i18n.MemberCustEditView.custLevel'),
		                colspan:2
		            },{
		                name: 'contact.number',
		                fieldLabel: i18n('i18n.MemberCustEditView.contactNo')
		            },{
		                name: 'contact.name',
		                fieldLabel: i18n('i18n.ManualRewardIntegralEditView.name')
		            },{
		                name: 'contact.mobilePhone',
		                fieldLabel: i18n('i18n.MemberCustEditView.mobileNo')
		            },{
		                name: 'contact.telPhone',
		                fieldLabel: i18n('i18n.MemberCustEditView.telephoneNo')
		            }]	
				}]
			}];
		}
	});
	
	
	
	//解绑页面中联系人信息搜索panel
	Ext.define('NoBoundContactInfoSearchPanel',{
		extend:'NotitleBGroundFormPanel',
		layout:{
	        type:'vbox',
	        align:'stretch'
	    },
		height:125,
		parent:null,
		boundContactNumData:null,//data层
		items:[Ext.create('NoBoundContactNumPanel',{id:'noBoundContactNumPanelId'})],
		initComponent:function(){
			var me = this;
			this.callParent();
		}
	});
	
	
	Ext.create('Ext.container.Viewport', {
		layout : 'fit',
		border:false,
		items :Ext.create('NormalTabPanel',{
			id:'boundnumber',
			items:[{
				title:'绑定联系人编码',
				layout:'fit',
				border:false,
				cls:'noborder_tab_panel',
				items:[Ext.create('BoundContactNumPanel',{'boundContactNumData':boundContactNumDataControl,border:false})]
			},{
				title:'联系人编码解绑',
				border:false,
				layout:'border',
				cls:'noborder_tab_panel',
				padding:'5px',
				items:[{
					region:'north',
					xtype:'basicpanel',
					cls:'normalpanel',
					title:'网上信息搜索',
					border:true,
					height:145,
					width:796,
					items:[Ext.create('NoBoundContactInfoSearchPanel',{id:'noBoundContactInfoSearchPanelId'})]
				},{
					region:'center',
					layout:'border',
					xtype:'basicpanel',
//					cls:'normalpanel',
					height:250,
					border:false,
					title:'已绑定的用户名',
					items:[{
						xtype:'searchgridpanel',
						height:150,
						region:'north',
						id:'registerInfoGird',
						store:Ext.create('RegisterInfoStore'),
						columns:[
						//增加订单来源列 2012-10-19
						{
							header:'订单来源',
							dataIndex:'custsource',
							renderer:function(v){
								return rendererDictionary(v,DataDictionary.ORDER_SOURCE);
							},
							flex:0.15
						},{
							header:i18n('i18n.BoundContactNumView.channelCustId'),
							dataIndex:'userName',
							flex:0.15
						},{
							header:i18n('i18n.PotentialCustManagerView.contactName'),
							dataIndex:'realName',
							flex:0.15
						},{
							header:i18n('i18n.MemberCustEditView.mobileNo'),
							dataIndex:'telephone',
							flex:0.15
						},{
							header:i18n('i18n.MemberCustEditView.telephoneNo'),
							dataIndex:'fixedPhone',
							flex:0.15
						},{
							header:i18n('i18n.PotentialCustEditView.address'),
							flex:1,
							dataIndex:'address',
							flex:0.20
						}],
						listeners:{
							itemclick:function(th,record){
								if(!Ext.isEmpty(record.get("userName"))){
									Ext.getCmp("chooseForm").getForm().findField("custId").setValue(record.get("userName"));
									Ext.getCmp("chooseForm").getForm().findField("custsource").setValue(record.get("custsource"));
								}
							}
						}
					},{
						region:'center',
						height:35,
						border:false,
						items:[{
							xtype:'basicformpanel',
							layout:'column',
							id:'chooseForm',
							cls:'boundtextstyle',
							defaults:{
								readOnly:true,
								border:false,
								labelWidth : 100
							},
							defaultType:'textfield',
							items:[{
								fieldLabel:i18n('i18n.BoundContactNumView.custId'),
								name:'custId'
							},{
								fieldLabel:i18n('i18n.BoundContactNumView.contactNum'),
								name:'contactNum'
							},{
								hidden:true,
								name:'custsource'
							},{
								xtype:'button',
								text:i18n('i18n.ContractManagerView.unbind'),
								width:80,
								handler:function(){
									var records=Ext.getCmp("registerInfoGird").getSelectionModel().getSelection();
									if(records.length>0){
										MessageUtil.showQuestionMes("您确定此联系人编码解绑吗？", function(e) {
											if (e == 'yes') {
												var successFn = function(json){
													Ext.getCmp("registerInfoGird").store.remove(records);
													MessageUtil.showInfoMes("解除绑定成功！");
												};
												var failureFn = function(json){
													MessageUtil.showErrorMes(json.message);
												};
												var params = {};
												var custId=Ext.getCmp("chooseForm").getForm().findField("custId").getValue();
												if(Ext.isEmpty(custId)){
													MessageUtil.showMessage("用户ID为空不能进行解绑！");
													return false;
												}
												var contactNum=Ext.getCmp("chooseForm").getForm().findField("contactNum").getValue();
												if(Ext.isEmpty(contactNum)){
													MessageUtil.showMessage("客户编码为空不能进行解绑！");
													return false;
												}
												params.contactNumber =contactNum;
												params.custId = custId;
												params.orderSource = Ext.getCmp("chooseForm").getForm().findField("custsource").getValue();
												boundContactNumDataControl.unboundContactNumber(params,successFn,failureFn);
												//清空用户ID和联系人编码
												Ext.getCmp("chooseForm").getForm().findField("custId").setValue('');
												Ext.getCmp("chooseForm").getForm().findField("contactNum").setValue('');
											}
										});										
									}else{
										MessageUtil.showMessage("请选择用户");
									}
								},
								margin:'0 0 0 10'
							}]
						}]
					}
//					,{
//						region:'south',
//						height:100,
//						border:false,
//						layout:'fit',
//						items:[Ext.create('BoundUser')]
//					}
					]
				}]
			}]
		})
	});
});
//绑定联系人 panel
Ext.define('BoundContactNumPanel',{
	extend:'BasicPanel',
	layout:{
        type:'vbox',
        align:'stretch'
    },
    border:false,
	webInfoSearchPanel:null,//网上信息搜索
	contactInfoSearchPanel:null,//联系人信息搜索
	boundContactNumData:null,//data 层
	initComponent:function(){
		var me = this;
		me.webInfoSearchPanel = Ext.create('WebInfoSearchPanel',{'parent':me,'boundContactNumData':me.boundContactNumData,border:false});
		me.contactInfoSearchPanel = Ext.create('ContactInfoSearchPanel',{'parent':me,'boundContactNumData':me.boundContactNumData,border:false});
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'basicpanel',
   			height:310,
			layout:{
		        type:'vbox',
		        align:'stretch'
		    },
			defaultType:'panel',
			cls:'normalpanel',
		    items:[{
		    	items:[{
		    		bodyStyle:'padding:5px;margin-bottom:10px;border-width:0',
					title : i18n('i18n.BoundContactNumView.webInfoSearchPanel'),
					items:[me.webInfoSearchPanel]	
		    	}]
		    },{
		    	items:[{
		    		bodyStyle:'padding:5px;border-width:0',
					title : i18n('i18n.BoundContactNumView.contactInfoSearchPanel'),
					items:[me.contactInfoSearchPanel]	
				}]
		    }]
		},{
			xtype:'basicformpanel',
			id:'webInfoSearchFormId',
			layout:'column',
			cls:'boundtextstyle',
			defaults:{
				readOnly:true,
				labelWidth : 100
			},
			defaultType:'textfield',
			items:[{
				fieldLabel:i18n('i18n.BoundContactNumView.custId'),
				id : 'txt_custId_bound',
				name:'custId'
			},{
				fieldLabel:i18n('i18n.BoundContactNumView.contactNum'),
				id : 'txt_contactNum_bound',
				name:'contactNum'
			},
//			{
//				fieldLabel:i18n('i18n.BoundContactNumView.randomNumber'),
//				readOnly:false,
//				id : 'txt_randomNumber_bound',
//				labelWidth : 65,
//				name:'randomNumber'
//			},
			{
				xtype:'button',
				text:i18n('i18n.BoundContactNumView.bound'),
				width:60,
				scope:me,
				//disabled:true,
				handler:me.boundEvent,
				margin:'0 0 0 10'
			}/*,{
				xtype:'resultfield',
				hideLabel:true,
				fieldLabel:i18n('i18n.BoundContactNumView.checkedResult'),
				id : 'txt_checkedResult_bound',
				name:'checkedResult'
			}*/]
		}]
	},
	//绑定联系人编码
	boundEvent:function(){
		var me = this;
		var custId = Ext.getCmp('txt_custId_bound').getValue();//选定网上用户ID
		var contactNumber = Ext.getCmp('txt_contactNum_bound').getValue();//选定联系人编码
		var checkResult = me.checkInfoIsFull(custId,contactNumber);
		if(!checkResult.success){
			MessageUtil.showMessage(checkResult.message);
			return;
		}
		var orderNum=Ext.getCmp("msgOrderNum").getValue();
		MessageUtil.showQuestionMes("您确定绑定联系人编码吗？", function(e) {
			if (e == 'yes') {
				var params = {};
				params.custId = custId;
				params.contactNumber = contactNumber;
				params.orderSource = orderSource;
				params.orderNumber = orderNum;
				var boundFail = function(result){
					if(Ext.isEmpty(result.message)){
						MessageUtil.showErrorMes(i18n('i18n.BoundContactNumView.boundFail'));
					}else{
						MessageUtil.showErrorMes(i18n('i18n.BoundContactNumView.boundFail')+result.message);
					}
				}
				var boundSuccess = function(result){
					//清空用户ID和联系人编码
					Ext.getCmp('txt_custId_bound').setValue('');
					Ext.getCmp('txt_contactNum_bound').setValue('');
					if(result.operateSuccess){//短信验证通过
//						Ext.getCmp('txt_checkedResult_bound').setValue(i18n('i18n.BoundContactNumView.pass'));//短信验证结果 显示为通过
						MessageUtil.showInfoMes(i18n('i18n.BoundContactNumView.operateSuccess'));
//					}else{
//						Ext.getCmp('txt_checkedResult_bound').setValue(i18n('i18n.BoundContactNumView.notPass'));//短信验证结果 显示为通过
					}
				}
				//绑定联系人编码请求
				me.boundContactNumData.boundContactNum(params,boundSuccess,boundFail);
			}
		});
		
	},
	//校验 完整
	checkInfoIsFull:function(custId,contactNumber){
		var me = this;
		var success = true;
		var message = '';
		if(Ext.isEmpty(custId)){
			success = false;
			message = i18n('i18n.BoundContactNumView.doubleClickToSelectCustId');
		}
		if(Ext.isEmpty(contactNumber)){
			success = false;
			message = i18n('i18n.BoundContactNumView.doubleClickToSelectContactId');
		}
		return {success:success,message:message};
	}
});


/**
 * <p>
 * 绑定联系人编码订单号查询
 * </p>
 * @author  张登
 * @date    2012-03-13
 */
Ext.define('OrderNumPanel',{
	extend:'BasicFormPanel',  
	height:120,
	layout:'border',
	border:false,
	initComponent:function(){
		this.items = this.getItems(); 
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			region:'north',
			height:35,
			layout:{
				type:'table',
				columns:2
			},
			border:false,
			items:[{
				xtype:'textfield',
				labelWidth:65,
				name:'orderNum',
				id:'msgOrderNum',
				width:240,
				fieldLabel:i18n('i18n.BoundContactNumView.orderNum')
			},{
				xtype:'button',
				margin:'0 0 0 10',
				text:i18n('i18n.PotentialCustManagerView.search'),
				handler:function(){
					var orderNum=Ext.getCmp("msgOrderNum").getValue().trim();
						if(!Ext.isEmpty(orderNum)){
						var param = {'orderNumber':orderNum};
						var successFn = function(json){
							if(!Ext.isEmpty(json.message)){
								MessageUtil.showErrorMes(json.message);
								return;
							}
							var order = json.order;
							var registerInfo = json.registerInfo;
							if(Ext.isEmpty(order)){
								MessageUtil.showErrorMes(i18n('i18n.order.orderInfoNullPleaseCheckOrderNum'));
								return;
							}
							orderSource = order.resource;
							orderNum = order.orderNumber;
							var data = null;
							var form=Ext.getCmp("orderNumPanelId").getForm();
							if(!Ext.isEmpty(order)){
								form.findField("resource").setValue(rendererDictionary(orderSource,DataDictionary.ORDER_SOURCE));
							}
							if(!Ext.isEmpty(registerInfo)){
								form.findField("userName").setValue(registerInfo.userName);
								form.findField("realName").setValue(registerInfo.realName);
								form.findField("telephone").setValue(registerInfo.telephone);
								form.findField("fixedPhone").setValue(registerInfo.fixedPhone);
								form.findField("address").setValue(registerInfo.address);
								Ext.getCmp("txt_custId_bound").setValue(registerInfo.userName);
							}else{
								MessageUtil.showErrorMes("注册用户信息不存在！");
							}
						};
						var failureFn = function(json){
							if(Ext.isEmpty(json)){
								MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
							}else{
								MessageUtil.showErrorMes(json.message);
							}
						}
						boundContactNumDataControl.searchWebInfo(param,successFn,failureFn);
					}
				}
			}]
		},{
			region:'center',
			items:[{
				xtype:'basicpanel', 
				layout:{
					type:'table',
					columns:3
				},
				height:65,
				defaultType:'textfield',
				defaults:{
					labelWidth : 75,
					readOnly:true,
			        labelAlign: 'right',
					width : 180
				},
				items:[{
					fieldLabel:i18n('i18n.BoundContactNumView.resource'),
					name:'resource'
				},{
					fieldLabel:i18n('i18n.BoundContactNumView.channelCustId'),
					name:'userName'
				},{
					fieldLabel:i18n('i18n.PotentialCustManagerView.contactName'),
					name:'realName'
				},{
					fieldLabel:i18n('i18n.MemberCustEditView.mobileNo'),
					name:'telephone'
				},{
					fieldLabel:i18n('i18n.MemberCustEditView.telephoneNo'),
					name:'fixedPhone'
				},{
					//xtype:'displayfield',
					fieldLabel:i18n('i18n.PotentialCustEditView.address'),
					name:'address',
					width:410
				}]	
			}]
		}];
	}
});

//网上信息搜索panel
Ext.define('WebInfoSearchPanel',{
	extend:'NotitleBGroundFormPanel',
	layout:{
        type:'vbox',
        align:'stretch'
    },
	height:115,
	parent:null,
	boundContactNumData:null,//data层
	items:[Ext.create('OrderNumPanel',{id:'orderNumPanelId'})],
	initComponent:function(){
		var me = this;
		this.callParent();
		
	}
});


/**
 * <p>
 * 绑定联系人
 * </p>
 * @author  张登
 * @date    2012-03-13
 */
Ext.define('QueryContactNumPanel',{
	extend:'BasicFormPanel',  
	height:110,
	layout:'border',
	border:false,
	initComponent:function(){
		this.items = this.getItems(); 
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			region:'north',
			height:35,
			layout:{
				type:'table',
				columns:2
			},
			border:false,
			items:[{
				xtype:'textfield',
				labelWidth:65,
				id:'queryContactNoId',
				width:240,
				fieldLabel:i18n('i18n.MemberCustEditView.contactNo')
			},{
				xtype:'button',
				margin:'0 0 0 10',
				text:i18n('i18n.PotentialCustManagerView.search'),
				handler:function(){
					var successFn = function(json){
						var contactViewModel=new ContactViewModel(json.contactView);
						var form=Ext.getCmp("queryContactNumPanelId").getForm();
						form.loadRecord(contactViewModel);
						var contact=json.contactView.contact;
						form.findField("degree").setValue(rendererDictionary(contactViewModel.get("degree"),DataDictionary.MEMBER_GRADE));
						form.findField("contact.member.custNumber").setValue(contact.member.custNumber);
						form.findField("contact.name").setValue(contact.name);
						form.findField("contact.mobilePhone").setValue(contact.mobilePhone);
						form.findField("contact.telPhone").setValue(contact.telPhone);
						form.findField("contact.number").setValue(contact.number);
						if(!Ext.isEmpty(contact.number)){
//							Ext.getCmp("registerInfoGird").store.load({params:{'custId':contact.id}});
							Ext.getCmp("txt_contactNum_bound").setValue(contact.number);
						}
					};
					var failureFn = function(json){
						MessageUtil.showErrorMes(json.message);
					};
					var params = {};
					var contactNumber = Ext.getCmp("queryContactNoId").getValue();
					if(!Ext.isEmpty(contactNumber)){
						params.contactNumber =contactNumber;
						boundContactNumDataControl.queryContactViewByNum(params,successFn,failureFn);
					}
				}
			}]
		},{
			region:'center',
			items:[{
				xtype:'basicpanel', 
				layout:{
					type:'table',
					columns:4
				},
				height:65,
				defaultType:'textfield',
				defaults:{
					labelWidth : 75,
					readOnly:true,
			        labelAlign: 'right',
					width : 180
				},
				items:[{
	                name: 'contact.member.custNumber',
	                fieldLabel: i18n('i18n.ScatterCustManagerView.memberNo')
	            },{
	                name: 'custName',
	                fieldLabel: i18n('i18n.PotentialCustManagerView.customerName')
	            },{
	                name: 'degree',
	                fieldLabel: i18n('i18n.MemberCustEditView.custLevel'),
	                colspan:2
	            },{
	                name: 'contact.number',
	                fieldLabel: i18n('i18n.MemberCustEditView.contactNo')
	            },{
	                name: 'contact.name',
	                fieldLabel: i18n('i18n.ManualRewardIntegralEditView.name')
	            },{
	                name: 'contact.mobilePhone',
	                fieldLabel: i18n('i18n.MemberCustEditView.mobileNo')
	            },{
	                name: 'contact.telPhone',
	                fieldLabel: i18n('i18n.MemberCustEditView.telephoneNo')
	            }]	
			}]
		}];
	}
});

//联系人信息搜索panel
Ext.define('ContactInfoSearchPanel',{
	extend:'NotitleBGroundFormPanel',
	layout:{
        type:'vbox',
        align:'stretch'
    },
	height:115,
	parent:null,
	contactResultGrid:null,//查询结果
	contactButtonPanel:null,//操作按钮
	boundContactNumData:null,//data层
	items:[Ext.create('QueryContactNumPanel',{id:'queryContactNumPanelId'})],
	initComponent:function(){
		var me = this;
		this.callParent();
	}
});


function relayTime(){
	//@TODO客户包发布之后，将中文转换为I18N
	Ext.getCmp('relayTimeShow').setValue('<a  style="color:red;font-size:20px;">'+RelayTime+'秒'+'</a>');
	if(RelayTime!=0){
		RelayTime = RelayTime - 1;
	}else{
		Ext.getCmp('boundSendMessage').setDisabled(false);
		RelayTime = 60;
		Ext.getCmp('relayTimeShow').setValue('');
		return;
	}
	//每1秒执行一次
	setTimeout("relayTime()",1*1000);
}