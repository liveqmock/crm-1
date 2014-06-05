/**
 * 在线理赔监控主界面
 * @author 杨伟
 * @时间 2013-11-01
 */
 //是否初始加载
 var firstLoadonlineApply=true;
 var firstLoad = true;
 var shortMessageSendPeople=new Array();
//单独添加短信发送人
var shortMessageSendPeople_add=new Array();
//不能删除员工PANEL
Ext.define('EmpPanel',{
	extend:'BasicPanel',
	labelText:null,
	empName:null,
	empCode:null,
	empPhone:null,
	height:25,
	html:'',
	cls:'customLabel',
	frame:true,
//	width:120,
	items:null,
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me=this;
		return [{
			xtype:'label',
			text:me.labelText
		}]
	}
});
//不能删除职位PANEL
Ext.define('PositionPanel',{
	extend:'BasicPanel',
	labelText:null,
	position:null,
	inputValue:null,
	height:25,
	frame:true,
	cls:'customLabel',
	items:null,
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me=this;
		return [{
			xtype:'label',
			text:me.labelText
		}]
	}
});
//可删除职位Panel
Ext.define('LabelPanel',{
	extend:'BasicPanel',
	labelText:null,//职位名称
	empName:null,
	empCode:null,
	cls:'deleteLabel',
//	margin:'5 0 5 0',
	height:25,
	layout:'auto',
	positionValue:null,//职位插入值
	empPhone:null,
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
	},
	getLabelText:function(){
		var me =this;
		return me.labelText;
	},
	getItems:function(){
		var me =this;
		return [{
			xtype:'label',
			text:me.labelText
		},{
			xtype:'button',
//			width:10,
			handler:function(){
				//单人短信发送
				if(this.up('window').sendPersonNum=='one'){
					//重复选中人
					if(this.up('panel').positionValue==null){
						for(var i=0;i<shortMessageSendPeople_add.length;i++){
							if(shortMessageSendPeople_add[i].empCode==this.up('panel').empCode){
								shortMessageSendPeople_add.splice(i,1);
								break;
							}
						}
					}else{
						for(var i=0;i<shortMessageSendPeople.length;i++){
							if(shortMessageSendPeople[i].empCode==this.up('window').empCode){
								shortMessageSendPeople.splice(i,1);
								break;
							}
						}
					}
					this.up('panel').close();
					Ext.getCmp('oneShortMessageReceivePersonPanel1').setHeight();
				}else{
					var labelText=this.up('panel').labelText;
					Ext.Array.remove(shortMessageSendPeople,labelText);
					var arr=[{position:'经理',inputValue:'1'},
							{position:'区域经理',inputValue:'2'},
							{position:'大区总经理',inputValue:'3'}]
					var inputValue;
					for(var i=0;i<arr.length;i++){
						if(arr[i].position==labelText){
							inputValue=arr[i].inputValue;
						}
					}
					var arrCk=Ext.getCmp('positionCheckboxId').getChecked();
					for(var i=0;i<arrCk.length;i++){
						if(arrCk[i].getSubmitValue()==inputValue){
							arrCk[i].setValue(false);
						}
					}
				}
			}
		}];
	}
});

/*
 * 短信通知
 */
Ext.define('ShortMessagesWindow',{
	extend:'Ext.window.Window',
	title:null,
	width:800,
	sendPersonNum:null,
	items:null,
	constrainHeader: true,
	recompenseId:null,
	status:null,
	waybillNum:null,
	reportDeptName:null,
	recompenseType:null,
	acceptDept:null,
	height:330,
	id:null,
	modal:true,
	//draggable:false,
	resizable:false,
	closeAction:'destroy',
	reShowLabelPanel:function(){
		if(this.sendPersonNum=='one'){
		    Ext.getCmp('oneShortMessageReceivePersonPanel2').removeAll(true);
			if(shortMessageSendPeople.length>0){
				for(var i=0;i<shortMessageSendPeople.length;i++){
					var labelTextStr=shortMessageSendPeople[i].empName+'('+
						shortMessageSendPeople[i].empCode+')';
					Ext.getCmp('oneShortMessageReceivePersonPanel2').add(
						Ext.create('EmpPanel',{
							labelText:labelTextStr,
							empCode:shortMessageSendPeople[i].empCode,
							empPhone:shortMessageSendPeople[i].empPhone,
							positionValue:shortMessageSendPeople[i].positionValue
						})
					);
				}
			}
		}else{
			Ext.getCmp('manyShortMessageReceivePersonPanel').removeAll();
			if(shortMessageSendPeople.length==0){
				return;
			}
			for(var i=0;i<shortMessageSendPeople.length;i++){
				Ext.getCmp('manyShortMessageReceivePersonPanel').add(
					Ext.create('PositionPanel',{
						labelText:shortMessageSendPeople[i],
						position:shortMessageSendPeople[i]
					})
				);
			}
		}
	},
	listeners:{
		beforedestroy:function(){
			shortMessageSendPeople=new Array();
			shortMessageSendPeople_add=new Array();
		}
	},
	getItems:function(){
		var isHide = false;
		var me=this;
		if(me.sendPersonNum=='many'){
			isHide=true;
			me.title='多条短信发送';
		}else{
			isHide=false;
			me.title='单条短信发送';
		}
		return [{
			xtype:'form',
			border:true,
			height:250,
			layout:{
				type:'table',
				columns:2
			},
			defaults:{
				colspan:2
			},
			items:[{
				xtype:'checkboxgroup',
				id:'positionCheckboxId',
				height:30,
				labelWidth:90,
				width:760,
				fieldLabel:i18n('i18n.recompenseMonitoring.notifyPerson'),
				layout:{
					type:'table',
					columns:3
				},
				margin:'10 0 0 0',
				defaults:{
					listeners:{
						'change':function(obt,isChecked){
							var position=this.boxLabel;
							var personNum=this.up('window').sendPersonNum;
							if(personNum=='many'){
								//多条短信发送
								if(isChecked){
									Ext.Array.include(shortMessageSendPeople,position);
									Ext.getCmp('shortMessageContentId').setValue('您好，' +
											'运单号***客户已上报在线理赔，目前仍处于待受理' +
											'状态，应由贵部受理，请协助跟进。（理赔研究小组）');
								}else{
									Ext.Array.remove(shortMessageSendPeople,position);
								}
								Ext.getCmp('manyShortMessagesWindow').reShowLabelPanel();
							}else{
								//单条短信发送
								var url='../recompense/searchOnlineApplyPerson.action';
								var noticeTypes='';
								var inputValueIndex=0;
								var arrCk=Ext.getCmp('positionCheckboxId').getChecked();
								if(arrCk.length>0){
									noticeTypes+=arrCk[0].getSubmitValue();
									if(arrCk.length>1){
										for(var i=1;i<arrCk.length;i++){
											noticeTypes+=','+arrCk[i].getSubmitValue();
										}
									}
								}else{
									shortMessageSendPeople=new Array();
									//重新展示职位Panel
									Ext.getCmp('oneShortMessagesWindow').reShowLabelPanel();
									return;
								}
								var params={
									wayBillNum:this.up('window').waybillNum,//运单号
									acceptDept:this.up('window').acceptDept,//受理部门
									noticeTypes:noticeTypes//职位
								}
								var successFn=function(json){
									var recSmsInformationArr=json.recSmsInformationList;
									shortMessageSendPeople=new Array();
									for(var i=0;i<recSmsInformationArr.length;i++){
										var add_people ={'empName':recSmsInformationArr[i].empName,
						 								'empCode':recSmsInformationArr[i].empCode,
						 								'empPhone':recSmsInformationArr[i].mobileNumber,
						 								'positionValue':null};
									Ext.Array.include(shortMessageSendPeople,add_people);
						 			
									}
									Ext.getCmp('oneShortMessagesWindow').reShowLabelPanel();
								}
								var failFn=function(){
									for(var i=0;i<arrCk.length;i++){
										arrCk[i].setValue(false);
									}
								}
								DpUtil.requestJsonAjax(url,params,successFn,failFn);
							}
						}
					}
				},
				items:[{
					name:'notifyPerson',
					boxLabel:'经理',
					margin:'0 0 0 5',
					inputValue:'1',
					width:90
				},{
					name:'notifyPerson',
					boxLabel:'区域经理',
					inputValue:'2',
					width:110
				},{
					name:'notifyPerson',
					boxLabel:'大区总经理',
					inputValue:'3',
					width:120
				}]
			},{
				xtype:'displayfield',
				fieldLabel:'浮动通知对象',
				colspan:1,
				labelWidth:90,
				hidden:isHide
			},{
				xtype:'employeelookup',
			  	editable:false,
			 	emptyText:'',
			 	fieldLabel:'',
			 	empCode:null,
			 	hidden:isHide,
			 	empPhone:null,
			 	listeners:{
			 		select:function(t,r){
			 			var empCode=r.get("empCode");
			 			var empPhone=r.get('mobilePhone');
			 			var empName=r.get('empName');
			 			var labelTextStr=empName+'('+empCode+')';
			 			var add_people ={'empName':empName,
			 							'empCode':empCode,
			 							'empPhone':empPhone,
			 							'positionValue':null};
			 			if(shortMessageSendPeople_add.length>0){
				 			for(var i=0;i<shortMessageSendPeople_add.length;i++){
				 				if(shortMessageSendPeople_add[i].empCode==empCode){
				 					MessageUtil.showMessage('通知对象已添加');
				 					return;
				 				}
				 			}
			 			}
			 			Ext.Array.include(shortMessageSendPeople_add,add_people);
			 			Ext.getCmp('oneShortMessageReceivePersonPanel1').add(
		 					Ext.create('LabelPanel',{
		 						labelText:labelTextStr,
		 						empPhone:empPhone,
		 						empCode:empCode,
		 						empName:empName
		 					})
			 			);
			 		}
			 	}
			},{
				xtype:'displayfield',
				fieldLabel:'短信接收人',
				colspan:1,
				labelWidth:90,
				hidden:!isHide
			},{
				xtype:'panel',
				layout:'column',
				id:'manyShortMessageReceivePersonPanel',
				frame:true,
				cls:'whitePanel',
//				width:660,
				height:50,
				autoScroll:true,
				colspan:1,
				hidden:!isHide,
				items:[
					
				]
			},{
				xtype:'displayfield',
				fieldLabel:'短信接收人',
				colspan:1,
				labelWidth:90,
				hidden:isHide
			},{
				xtype:'panel',
				layout:{
				    xtype:'table',
				    columns:1
				},
				frame:true,
//				width:660,
				id:'shortMessageReceivePersonPanel',
				cls:'whitePanel',
				height:80,
				autoScroll:true,
				colspan:1,
				hidden:isHide,
				items:[{
					xtype:'panel',
				    id:'oneShortMessageReceivePersonPanel1',	
				    autoScroll:true,
				    layout:'column',
				    frame:false,
				    hidden:isHide,
				    wihdth:650,
				    items:[]
				},{
					xtype:'panel',
				    id:'oneShortMessageReceivePersonPanel2',	
				    autoScroll:true,
				    wihdth:650,
				    layout:'column',
					frame:true,
				    hidden:isHide,
				    items:[]
				}]
			},{
				xtype:'displayfield',
				fieldLabel:'短信模板',
				labelWidth:90,
				hidden:!isHide,
				colspan:1
			},{
				xtype:'textarea',
				labelSeparator:'',
				width:660,
				height:90,
				id:'shortMessageContentId',
				labelWidth:10,
				disabled:true,
				autoScroll:true,
				hidden:!isHide,
				colspan:1
			},{
				xtype:'displayfield',
				fieldLabel:'短信内容',
				hidden:isHide,
				value:null,
				colspan:1,
				labelWidth:90
			},{
				xtype:'textarea',
				labelSeparator:'',
				width:660,
				height:70,
				id:'shortMessageTemplateId',
				maxLength:150,
				labelWidth:20,
				disabled:false,
				autoScroll:true,
				hidden:isHide,
				rowspan:2
			},{
				xtype:'checkbox',
				labelSeparator:'',
				boxLabel:'按模板发送',
				id:'templateToSend',
				hidden:isHide,
				labelWidth:5,
				fieldLabel:' ',
				width:90,
				listeners:{
					change:function(obt,isChecked){
						if(isChecked){
							Ext.getCmp('shortMessageTemplateId').setValue(
							'您好，运单号'+this.up('window').waybillNum+'已由客户上报在线理赔，' +
							'截止目前仍处于待受理状态，为避免客户投诉，请您及' +
							'时受理并协助跟进。（理赔研究小组）');
							Ext.getCmp('shortMessageTemplateId').setDisabled(true);
						}else{
							Ext.getCmp('shortMessageTemplateId').setDisabled(false);
						}
					}
				}
			}]
		},{
			xtype:'container',
			border:false,
			layout:{
				type:'table',
				columns:2
			},
			items:[{
				xtype:'button',
				text:'发送',
				margin:'5 0 0 650',
				handler:function(me){
					var smsInfoListJSON=new Array();
					var arr=Ext.Array.unique(Ext.Array.flatten(
							[shortMessageSendPeople_add,shortMessageSendPeople]));
					for(var i=0;i<arr.length-1;i++){
						for(var j=i+1;j<arr.length;j++){
							if(arr[i].empCode==arr[j].empCode){
								arr.splice(i,1);
							}
						}
					}
					if(this.up('window').sendPersonNum=='one'){
						var msgContent=Ext.getCmp('shortMessageTemplateId').getValue();
						if(Ext.getCmp('shortMessageTemplateId').getValue().length>150){
							return;
						}
						if(arr.length==0&&Ext.isEmpty(msgContent)){
							MessageUtil.showMessage('通知对象与短信内容不允许为空');
							return;
						}
						if(arr.length==0){
							MessageUtil.showMessage('通知对象不允许为空，请选择通知对象');
							return;
						}
						if(Ext.isEmpty(msgContent)){
							if(!Ext.getCmp('templateToSend').getValue()){
								MessageUtil.showMessage('短信内容不允许为空，请输入短信内容');
								return;
							}
						}
						for(var i=0;i<arr.length;i++){
							Ext.Array.include(smsInfoListJSON,{mobile:arr[i].empPhone});
						}
						var noticeTypes='';
						var arrCk=Ext.getCmp('positionCheckboxId').getChecked();
						if(arrCk.length>0){
							noticeTypes+=arrCk[0].getSubmitValue();
							if(arrCk.length>1){
								for(var i=1;i<arrCk.length;i++){
									noticeTypes+=','+arrCk[i].getSubmitValue();
								}
							}
						}
						me.setDisabled(true);
						var params={
							wayBillNum:this.up('window').waybillNum,
							noticeTypes:noticeTypes,
							smsInfoList:smsInfoListJSON,
							msgContent:msgContent
						}
						var successFn = function(json){
							if(json.msgContent.length>100){
								msgContent=msgContent.substring(0,100)+'...';
							}
							MessageUtil.showInfoMes('短信发送成功<br>短信内容:'+msgContent);
							Ext.getCmp('oneShortMessagesWindow').close();
						}
						var failFn = function(){
							me.setDisabled(false);
							MessageUtil.showMessage('短信发送失败');
						}
						var url ='../recompense/oneShortMessageSendForOnline.action';
						DpUtil.requestJsonAjax(url,params,successFn,failFn);
					}else{
						//多条短信发送BUTTON
						var msgContent=Ext.getCmp('shortMessageContentId').getValue();
						if(arr.length==0&&Ext.isEmpty(msgContent)){
							MessageUtil.showMessage('通知对象与短信内容不允许为空');
							return;
						}
						if(arr.length==0){
							MessageUtil.showMessage('通知对象不允许为空，请选择通知对象');
							return;
						}
						if(Ext.isEmpty(msgContent)){
							if(!Ext.getCmp('templateToSend').getValue()){
								MessageUtil.showMessage('短信内容不允许为空，请输入短信内容');
								return;
							}
						}
						var waybillNumList=new Array();
						var monitoringData = Ext.getCmp('monitoringGridPanel').getSelectionModel().getSelection();
						for(var i=0;i<monitoringData.length;i++){
							Ext.Array.include(waybillNumList,monitoringData[i].get('waybillNumber'));
						}
						var noticeTypes='';
						var arrCk=Ext.getCmp('positionCheckboxId').getChecked();
						if(arrCk.length>0){
							noticeTypes+=arrCk[0].getSubmitValue();
							if(arrCk.length>1){
								for(var i=1;i<arrCk.length;i++){
									noticeTypes+=','+arrCk[i].getSubmitValue();
								}
							}
						}
						me.setDisabled(true);
						var params={
							noticeTypes:noticeTypes,
							msgContent:Ext.getCmp('shortMessageContentId').getValue(),
							waybillNumList:waybillNumList
						}
						var successFn = function(json){
							MessageUtil.showInfoMes('短信发送成功<br>短信模板:'+json.msgContent);
							Ext.getCmp('manyShortMessagesWindow').close();
						}
						var failFn = function(){
							me.setDisabled(false);
							MessageUtil.showMessage('短信发送失败');
						}
						var url='../recompense/' +
								'manyShortMessageSendForOnline.action';
						DpUtil.requestJsonAjax(url,params,successFn,failFn);
					}
				}
			},{
				xtype:'button',
				text:'关闭',
				margin:'5 0 0 10',
				handler:function(){
					if(Ext.getCmp('manyShortMessagesWindow')){
						Ext.getCmp('manyShortMessagesWindow').close();
					}
					if(Ext.getCmp('oneShortMessagesWindow')){
						Ext.getCmp('oneShortMessagesWindow').close();
					}
				}
			}]
		}]
	},
	initComponent:function(){
		var me=this;
		me.items=me.getItems();
		this.callParent();
	}
});
 
 /**
  * 在线理赔查询条件FORM
  */
Ext.define('OnlineapplyMonitoringSearchForm',{
		extend:'SearchFormPanel',
		items:null,
		initComponent:function(){
			var me=this;
			me.items=me.getItems();
			this.callParent();
			//上报时间初始值
			me.getForm().findField('reportDateTo').setValue(new Date());			
			var date = new Date();
			date.setDate(date.getDate()-31);
			me.getForm().findField('reportDate').setValue(date);
			//下拉框添加--全部
			Ext.data.StoreManager.lookup('onlineapplyStatusStore').
				add({'code':'','codeDesc':'全部'});
			Ext.data.StoreManager.lookup('claimAmountStore').
				add({'code':'','codeDesc':'全部'});
			Ext.data.StoreManager.lookup('onlineapplyTranstypeStore').
				add({'code':'','codeDesc':'全部'});
			Ext.data.StoreManager.lookup('belongsAreaStore').
				add({'id':'','deptName':'全部'});
			//下拉框设默认值--全部
			me.getForm().findField('claimAmount').setValue('');
			me.getForm().findField('transType').setValue('');
			me.getForm().findField('acceptStatus').setValue('WaitAccept');
			me.getForm().findField('belongsArea').setValue('');
			
		},
		layout:{
			type:'table',
			columns:3
		},
		defaults:{
			labelWidth:60,
			width:260
		},
		getItems:function(){
			return [{
				xtype:'textfield',
				fieldLabel:'凭证号',
				name:'waybillNumber',
				maxLength:20
			},{
				xtype:'datefield',
				fieldLabel:'上报时间',
				name:'reportDate',
				editable:false
			},{
				xtype:'datefield',
				fieldLabel:'到',
				name:'reportDateTo',
				editable:false
			},{
				xtype:'numberfield',
				fieldLabel:'持续时间',
				name:'acceptTime',
				maxValue:365,
				maxText:'该输入项的最大值是365',
				minValue:0,
				minText:'该输入项的最大值是0',
				hideTrigger:true,
				allowDecimals:false,
				allowNegative:false,
				nanText:'请输入0-365的数字'
			},{
				xtype:'numberfield',
				fieldLabel:'到',
				name:'acceptTimeTo',
				maxValue:365,
				maxText:'该输入项的最大值是365',
				minValue:0,
				minText:'该输入项的最大值是0',
				hideTrigger:true,
				allowDecimals:false,
				allowNegative:false,
				nanText:'请输入0-365的数字'
			},{
				xtype:'combo',
				fieldLabel:'受理状态',
				name:'acceptStatus',
				displayField:'codeDesc',
				valueField:'code',
				queryMode:'local',
				editable:false,
				store:DpUtil.getStore('onlineapplyStatusStore',null,['code','codeDesc'],
					DataDictionary.ONLINEAPPLY_STATUS)
			},Ext.create('Ext.lookup.DeptLookup',{
				editable:false,
				fieldLabel:'受理部门',
				labelWidth:60,
				name:'acceptDept',
				id:'formAcceptDeptId',
				width:260,
				emptyText:''
			}),{
				xtype:'combo',
				fieldLabel:'所属区域',
				name:'belongsArea',
				fieldLabel : i18n('i18n.recompense.belongArea'),
				displayField : 'deptName',
				valueField:'id',
				queryMode:'local',
				store : DpUtil.getStore('belongsAreaStore',null,['id','deptName'],AllAreas)
			},{
				xtype:'combo',
				fieldLabel:'索赔金额',
				name:'claimAmount',
				valueField:'code',
				displayField:'codeDesc',
				queryMode:'local',
				editable:false,
				store:DpUtil.getStore('claimAmountStore',null,['code','codeDesc'],
					DataDictionary.ONLINEAPPLY_AMOUNT)
			},{
				xtype:'combo',
				fieldLabel:'运输类型',
				editable:false,
				name:'transType',
				valueField:'code',
				displayField:'codeDesc',
				queryMode:'local',
				editable:false,
				store:DpUtil.getStore('onlineapplyTranstypeStore',null,['code','codeDesc'],
					DataDictionary.TRANSPORT_TYPE)
			}];
		}
	});
/**
 * 在线理赔监控中间buttons
 */
Ext.define('OnlineapplyMonitoringSearchButtons',{
	extend:'NormalButtonPanel',
	items:null,
	initComponent:function(){
		var me=this;
		me.items=me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me=this;
		return [{
			xtype:'leftbuttonpanel',
			width:320,
			items:[{
				xtype:'button',
				text:'多条短信通知',
				handler:function(){
					// TODO 多条短信通知方法
					var monitoringData = Ext.getCmp('monitoringGridPanel').getSelectionModel().getSelection();
					if(monitoringData.length<1){
						MessageUtil.showMessage('请至少选择一条运单进行通知');
						return;
					}else{
						if(monitoringData.length==1&&monitoringData[0].get('status')!='WaitAccept'){
							MessageUtil.showMessage('只有待受理状态的理赔单，才能进行短信通知');
							return;
						}
						for(var i=0;i<monitoringData.length;i++){
							if(monitoringData[i].get('transType')=='PACKAGE'
								||monitoringData[i].get('transType')=='快递'){
								MessageUtil.showMessage('该理赔单的运输类型为快递，不能进行短信通知');
								return;
							}
							if(monitoringData[i].get('status')!='WaitAccept'){
								MessageUtil.showMessage('多条短信发送时：处理状态必须全部为待受理状态');
								return;
							}
						}
						if(Ext.getCmp('manyShortMessagesWindow')){
							Ext.getCmp('manyShortMessagesWindow').show();
						}else{
							Ext.create('ShortMessagesWindow',{
								id:'manyShortMessagesWindow',
								sendPersonNum:'many',
								status:monitoringData[0].get('status')
							}).show();
						}
					}
				}
			},{
				xtype:'button',
				text:'催促办理',
				handler:function(){
					var model = Ext.getCmp('monitoringGridPanel').getSelectionModel().getSelection();
					if(model.length<1){
						MessageUtil.showMessage('请至少选择一条记录');
						return;
					}
					if(model.length>1){
						MessageUtil.showMessage('催促办理只能选择一条数据');
						return;
					}else{
						var params={
								'recompenseId':model[0].data.waybillNumber
						};
						var successFn = function(json){
							MessageUtil.showInfoMes(json.message);
						};
						var failureFn = function(json){
							if(Ext.isEmpty(json)){
								MessageUtil.showMessage("网络超时");
							}else{
								MessageUtil.showMessage(json.message);
							}
						};
						RecompenseDataN.prototype.pressDoForOnline(params, successFn, failureFn);
					}
				}
			},{
				xtype:'button',
				text:'查看详情',
				handler:function(){
					var selections = Ext.getCmp('monitoringGridPanel').getSelectionModel().getSelection();
        			if(selections.length!=1){
        				MessageUtil.showMessage(i18n('i18n.recompense.pleaseSelectOneLine'));
        				return;
        			}
        			var id = selections[0].get('id');
        			var successFn = function(json){
        				new OnlineRecompenseWindow({'record':json.app}).show();
        				document.getElementsByTagName("html")[0].style.overflowY="auto";
						document.getElementsByTagName("html")[0].style.overflowX="auto";
	        			Ext.getCmp('normalRadio').setDisabled(true);
	        			Ext.getCmp('fastRecompenseMethod_id').setDisabled(true);
	        			Ext.getCmp('onlineRadio').setDisabled(false);
	        			Ext.getCmp('onlineRadio').setValue(true);
        			};
        			var failureFn = function(json){
        				if(Ext.isEmpty(json)){
        					MessageUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
        				}else{
        					MessageUtil.showMessage(json.message);
        				}
        			};
        			var param = {'recompenseId':id};
					RecompenseDataN.prototype.lookUpOnlineApplyDetail(param, successFn, failureFn);
				}
			}]
		},{
			xtype : 'middlebuttonpanel'
		},{
			xtype:'rightbuttonpanel',
			items:[{
				xtype:'button',
				text:'查询',
				id:'onlineapplyMonitoringSearch',
				handler:function(){
					// 查询按钮方法
					Ext.getCmp('onlineapplyMonitoringSearch').setDisabled(true);
					function disableSearchButton(){
						Ext.getCmp('onlineapplyMonitoringSearch').setDisabled(false);
						clearTimeout(task);
					};
					var task= setTimeout(disableSearchButton, 2000);
					//如果运单号不为空 就不用校验条件
					if(Ext.isEmpty(Ext.getCmp('onlineApplySearchFormId').getForm()
								.findField('waybillNumber').getValue())){
						if(!Ext.getCmp('monitoringGridPanel').varifyForm()){
							return;
						}
					}
					Ext.getCmp('monitoringGridPanel').getStore().loadPage(1);
				}
			}]
		}];
	}
});
/**
 * 在线理赔监控查询结果表格grid
 */
Ext.define('OnlineapplyMonitoringSearchGrid',{
	extend:'SearchGridPanel',
	store : null,
	flex:7,
	record : null,
	autoScroll:true,
	initComponent : function() {
		var me = this;
		me.columns = me.getColumns();
		me.selModel =  me.getSelModel();
		me.store=me.getOnlineapplyStore();
		me.bbar = me.getBBar();
		this.callParent();
		me.getStore().on('beforeLoad',function(store,operation,obj){
			var searchParams;
			if(Ext.isEmpty(Ext.getCmp('onlineApplySearchFormId').getForm()
								.findField('waybillNumber').getValue())){
				if(Ext.isEmpty(me.getCondition())){
					return;
				}
				searchParams = me.transform(me.getCondition());
			}else{
				searchParams = 
				{'onlineApplyCondition.waybillNumber':Ext.getCmp('onlineApplySearchFormId')
											.getForm().findField('waybillNumber').getValue()}
			}
			Ext.apply(operation,{
				params : searchParams
			});
		});
	},
	//校验查询条件
	varifyForm:function(){
		var onlineApplySearchForm = Ext.getCmp('onlineApplySearchFormId').getForm();
		if(!onlineApplySearchForm.isValid()){
			MessageUtil.showMessage("查询条件有误",null);
			return false;
		}
		var reportDate = onlineApplySearchForm.findField('reportDate').getValue();
		var reportDateTo = onlineApplySearchForm.findField('reportDateTo').getValue();
		if(reportDate>reportDateTo){
			MessageUtil.showMessage("上报时间前者不能大于后者",null);
			return false;
		}
		//上报时间+31天
		reportDate.setDate(reportDate.getDate()+31);
		if(reportDate<reportDateTo){
			MessageUtil.showMessage("上报时间相差不能超过31天",null);
			return false;
		}
		var acceptTime = onlineApplySearchForm.findField('acceptTime').getValue();
		var acceptTimeTo = onlineApplySearchForm.findField('acceptTimeTo').getValue();
		//持续时间前者不能大于后者
		if((!Ext.isEmpty(acceptTime))&&(!Ext.isEmpty(acceptTimeTo))){
			if(acceptTime>acceptTimeTo){
				MessageUtil.showMessage("持续时间前者不能大于后者",null);
				return false;
			}
			//持续时间和持续时间到一个为空
		}else if((Ext.isEmpty(acceptTime)&&!Ext.isEmpty(acceptTimeTo))
					||(Ext.isEmpty(acceptTimeTo)&&!Ext.isEmpty(acceptTime))){
				MessageUtil.showMessage('起始值和结束值必须同时为空或同时不为空！',null);
				return false;
		}
		return true;
	},
	//得到查询条件
	getCondition:function(){
		var record = new OnlineApplyCondition();
		Ext.getCmp('onlineApplySearchFormId').getForm().updateRecord(record)
		if(!Ext.isEmpty(Ext.getCmp('formAcceptDeptId').getValue())){
			record.set('acceptDept',Ext.getCmp('formAcceptDeptId').valueId);
		}
		return record;
	},
	//转换请求参数
	transform:function(record){
		if(firstLoad){
			record.data.acceptStatus='WaitAccept';
			firstLoad = false;
		}
		var searchParams = {
			'onlineApplyCondition.waybillNumber':record.get('waybillNumber'),
			'onlineApplyCondition.reportDate':record.get('reportDate'),
			'onlineApplyCondition.reportDateTo':record.get('reportDateTo'),
			'onlineApplyCondition.acceptTime':record.get('acceptTime'),
			'onlineApplyCondition.acceptTimeTo':record.get('acceptTimeTo'),
			'onlineApplyCondition.onlineApplyStatus':record.get('acceptStatus'),
			'onlineApplyCondition.acceptDept':record.get('acceptDept'),
			'onlineApplyCondition.belongsArea':record.get('belongsArea'),
			'onlineApplyCondition.claimAmount':record.get('claimAmount'),
			'onlineApplyCondition.transType':record.get('transType')
		}
		return searchParams;
	},
	getOnlineapplyStore:function(){
		if(!Ext.isEmpty(Ext.data.StoreManager.lookup('OnlineApplyStoreId'))){
			return Ext.data.StoreManager.lookup('OnlineApplyStoreId');
		}
		Ext.define('OnlineApplyStore',{
			pageSize : 10,
			extend:'Ext.data.Store',
			model:'OnlineApplyModel',
			autoLoad:true,
			storeId:'OnlineApplyStoreId',
			proxy:{
				type:'ajax',
				url:'../recompense/searchOnlineApply.action',
				actionMethods:'POST',
				reader:{
					type:'json',
					root:'onlineApplyList',
					totalProperty : 'totalCount'
				}
			}
		});
		return new OnlineApplyStore();
	},
	//双击表格事件
	listeners:{
		itemdblclick:function(){
			var selections = Ext.getCmp('monitoringGridPanel').getSelectionModel().getSelection();
			if(selections.length!=1){
				MessageUtil.showMessage(i18n('i18n.recompense.pleaseSelectOneLine'));
				return;
			}
			var id = selections[0].get('id');
			var successFn = function(json){
				new OnlineRecompenseWindow({'record':json.app}).show();
				document.getElementsByTagName("html")[0].style.overflowY="auto";
				document.getElementsByTagName("html")[0].style.overflowX="auto";
    			Ext.getCmp('normalRadio').setDisabled(true);
    			Ext.getCmp('fastRecompenseMethod_id').setDisabled(true);
    			Ext.getCmp('onlineRadio').setDisabled(false);
    			Ext.getCmp('onlineRadio').setValue(true);
			};
			var failureFn = function(json){
				if(Ext.isEmpty(json)){
					MessageUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
				}else{
					MessageUtil.showMessage(json.message);
				}
			};
			var param = {'recompenseId':id};
			RecompenseDataN.prototype.lookOnlineDetail(param, successFn, failureFn);
		}
	},
	//获取选择框
	getSelModel : function() {
		return  Ext.create('Ext.selection.CheckboxModel', {
			
		})
	},
	getBBar:function(){
		var me = this;
		return Ext.create('Ext.toolbar.Paging',{
			displayMsg : i18n('i18n.recompense.displayMsg'),
			displayInfo : true,
			id:'pagingToolbar',
			store:me.store,
			items:[
	            '-',
	        	{
					text: i18n('i18n.recompense.page_count'),
					xtype: 'tbtext'
				},Ext.create('Ext.form.ComboBox', {
	               width:          50,
	               value:          '10',
	               triggerAction:  'all',
	               forceSelection: true,
	               editable:       false,
	               name:           'comboItem',
	               displayField:   'value',
	               valueField:     'value',
	               queryMode:      'local',
	               store : Ext.create('Ext.data.Store',{
	                   fields : ['value'],
	                   data   : [
	                       {'value':'10'},
	                       {'value':'15'},
	                       {'value':'20'},
	                       {'value':'25'},
	                       {'value':'40'},
	                       {'value':'100'}
	                   ]
	               }),
	               listeners:{
						select : {
							scope : this,
				            fn: function(_field,_value){
				            	var pageSize = Ext.getCmp('monitoringGridPanel').store.pageSize;
				            	var newPageSize = parseInt(_field.value);
				            	if(pageSize!=newPageSize){
				            		Ext.getCmp('monitoringGridPanel').store.pageSize = newPageSize;
				            		Ext.getCmp('pagingToolbar').moveFirst();
				            	}
				            }
				        }
	               }
	           }),{
					text: i18n('i18n.recompense.pageNumber'),
					xtype: 'tbtext'
	           }]
		});
	},
	getColumns:function(){
		var me = this;
		return [{
			xtype:'rownumberer',
			width:40,
			text:'序号'
		},{
			header:i18n('i18n.recompenseMonitoring.oneShortMessages'),//单条短信通知
			dataIndex:'oneShortMessages',
			xtype:'actioncolumn',
			align:'center',
			items:[{
	            icon   : '../images/recompense/recompense/sendShortMessage.png',  
	            tooltip: '单条短信通知',
	            handler: function(grid,rowIndex,colIndex) {
	         		//为快递提示不能进行短信发送
	            	var transtype=grid.getStore().getAt(rowIndex).get('transType');
                	if(transtype == 'PACKAGE'||transtype == '快递'){
                		MessageUtil.showMessage('该理赔单的运输类型为快递，不能进行短信通知');
						return;
                	}
                	//状态为待受理的提示
                	var tempStatus=grid.getStore().getAt(rowIndex).get('status');
                	if(tempStatus!='WaitAccept'){
                		MessageUtil.showMessage('该条理赔单不是待受理状态，不能短信通知');
						return;
                	}
                	if(Ext.getCmp('oneShortMessagesWindow')){
             			Ext.getCmp('oneShortMessagesWindow').show();	
             		}else{
             			Ext.create('ShortMessagesWindow',{
             				id:'oneShortMessagesWindow',
             				waybillNum:grid.getStore().getAt(rowIndex).get('waybillNumber'),
             				status:grid.getStore().getAt(rowIndex).get('status'),
             				sendPersonNum:'one',
             				acceptDept:grid.getStore().getAt(rowIndex).get('applyDeptId')
             			}).show();
             		}
            	 }
			}]
		},{
			header:'凭证号',
			dataIndex:'waybillNumber'
		},{
			header:'上报时间',
			dataIndex:'createDate',
			renderer : function(value){
            	if(!Ext.isEmpty(value)){
            		var date = new Date(value);
            		return Ext.Date.format(new Date(value),'Y-m-d')
            	}
            	return value;
            }
		},{
			header:'状态持续时间',
			dataIndex:'acceptDays',
			align:'center'
		},{
			header:'受理状态',
			dataIndex:'status',
			renderer : function(value){
            	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.ONLINEAPPLY_STATUS);
            }
		},{
			header:'受理部门',
			dataIndex:'acceptDeptName'
		},{
			header:'所属区域',
			dataIndex:'belongsAreaName'
		},{
			header:'索赔金额',
			dataIndex:'recompenseAmount',
			align:'center'
		},{
			header:'运输类型',
			dataIndex:'transType',
			align:'center',
			renderer : function(value){
				if(value=='FLF'||value=='FSF'||value=='LRF'
					||value=='SRF'||value=='PLF'||value=='FV'){
					return '汽运'	
				}
				if(value=='AF'){
					return '空运'
				}
				if(value=='PACKAGE'){
					return '快递'
				}
				return value;
            }
		}];
	}
});
Ext.onReady(function() {
	Ext.QuickTips.init();
	var keys =['ONLINEAPPLY_STATUS',			//在线理赔状态
	           'ONLINEAPPLY_AMOUNT',			//在线理赔金额区间
	           'RECOMPENSE_STATUS',	            // 处理状态
	        	'RECOMPENSE_WAY',                   // 理赔方式 
	        	'RECOMPENSE_TYPE',                  //理赔类型
	        	'DANGER_TYPE',                       //出险类型
	        	'MEMBER_GRADE',						//客户等级
	        	'TRANSPORT_TYPE'					//运输类型
	        	];
	initDataDictionary(keys);
	initAllAreas();
	var onlineapplyMonitoringSearchForm = Ext.create('OnlineapplyMonitoringSearchForm',{
		id:'onlineApplySearchFormId'
			});
	var onlineapplyMonitoringSearchButtons = Ext.create('OnlineapplyMonitoringSearchButtons',{
	});
	var onlineapplyMonitoringSearchGrid = Ext.create('OnlineapplyMonitoringSearchGrid',{
		id:'monitoringGridPanel'
	});
	Ext.create('Ext.container.Viewport',{
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		items:[onlineapplyMonitoringSearchForm,
				onlineapplyMonitoringSearchButtons,
				onlineapplyMonitoringSearchGrid]
	});
});
Ext.form.Field.prototype.msgTarget='side';