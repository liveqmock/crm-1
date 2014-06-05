var recompenseData = (CONFIG.get("TEST")) ? new RecompenseDataTestN()
		: new RecompenseDataN();

var INITSEARCH = false;
var SearchError = false;
var RecompenseInfoDataNew = false;
var SearchWorkFlow = false;
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
							{position:'理赔专员',inputValue:'2'},
							{position:'区域经理',inputValue:'3'},
							{position:'大区总经理',inputValue:'4'},
							{position:'事业部办公室主任',inputValue:'5'},
							{position:'事业部总裁',inputValue:'6'}]
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
			if(shortMessageSendPeople.length==0){
				return;
			}
			Ext.getCmp('manyShortMessageReceivePersonPanel').removeAll();
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
					columns:6
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
								}else{
									Ext.Array.remove(shortMessageSendPeople,position);
								}
								var url = 'getSMSTemplateMore.action';
								var noticeTypes='';
								var arrCk=Ext.getCmp('positionCheckboxId').getChecked();
								if(arrCk.length>0){
									noticeTypes+=arrCk[0].getSubmitValue();
									if(arrCk.length>1){
										for(var i=1;i<arrCk.length;i++){
											noticeTypes+=','+arrCk[i].getSubmitValue();
										}
									}
								}else{
									Ext.getCmp('manyShortMessageReceivePersonPanel').removeAll();
									return;
								}
								var params={
									status:this.up('window').status,//状态
									noticeTypes:noticeTypes
								}
								var successFn=function(json){
									Ext.getCmp('shortMessageContentId').setValue(
										json.msgContent
									);
								}
								DpUtil.requestJsonAjax(url,params,successFn,failFn);
								Ext.getCmp('manyShortMessagesWindow').reShowLabelPanel();
							}else{
								//单条短信发送
								var url='searchEmpAndShortMessageTemplate.action';
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
									status:this.up('window').status,//状态
									wayBillNum:this.up('window').waybillNum,//运单号
									deptName:this.up('window').reportDeptName,//上报部门
									recompenseType:this.up('window').recompenseType,//理赔类型
									noticeTypes:noticeTypes,//职位
									recompenseIdList:[this.up('window').recompenseId],//理赔id集合
									toGetShortMessageTemplate:Ext.getCmp('templateToSend').getValue()
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
									if(Ext.getCmp('templateToSend').getValue()){
										Ext.getCmp('shortMessageTemplateId').setValue(
											json.shortMessageTemplate
										);
									}
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
					boxLabel:'理赔专员',
					inputValue:'2',
					width:110
				},{
					name:'notifyPerson',
					boxLabel:'区域经理',
					inputValue:'3',
					width:110
				},{
					name:'notifyPerson',
					boxLabel:'大区总经理',
					inputValue:'4',
					width:120
				},{
					name:'notifyPerson',
					boxLabel:'事业部办公室主任',
					inputValue:'5',
					width:150
				},{
					name:'notifyPerson',
					boxLabel:'事业部总裁',
					inputValue:'6',
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
							Ext.getCmp('shortMessageTemplateId').setDisabled(true);
							var url='searchEmpAndShortMessageTemplate.action';
							var successFn=function(json){
								Ext.getCmp('shortMessageTemplateId').setValue(
									json.shortMessageTemplate
								);
							}
							var failFn=function(){
								obt.setValue(false);
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
							}else{
								noticeTypes='1';
							}
							var params={
								status:this.up('window').status,
								wayBillNum:this.up('window').waybillNum,
								deptName:this.up('window').reportDeptName,
								recompenseType:this.up('window').recompenseType,
								noticeTypes:noticeTypes,
								recompenseIdList:[this.up('window').recompenseId],
								toGetShortMessageTemplate:true
							}
							DpUtil.requestJsonAjax(url,params,successFn,failFn);
						}else{
							Ext.getCmp('shortMessageTemplateId').setDisabled(false)
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
							status:this.up('window').status,
							wayBillNum:this.up('window').waybillNum,
							deptName:this.up('window').reportDeptName,
							recompenseType:this.up('window').recompenseType,
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
						var url ='oneShortMessageSend.action';
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
						var recompenseIdList=new Array();
						var monitoringData = Ext.getCmp('monitoringGridPanel').getSelectionModel().getSelection();
						for(var i=0;i<monitoringData.length;i++){
							Ext.Array.include(recompenseIdList,monitoringData[i].get('id'));
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
							status:this.up('window').status,
							noticeTypes:noticeTypes,
							smsInfoList:smsInfoListJSON,
							msgContent:Ext.getCmp('shortMessageContentId').getValue(),
							recompenseIdList:recompenseIdList
						}
						var successFn = function(json){
							MessageUtil.showInfoMes('短信发送成功<br>短信模板:'+json.msgContent);
							Ext.getCmp('manyShortMessagesWindow').close();
						}
						var failFn = function(){
							me.setDisabled(false);
							MessageUtil.showMessage('短信发送失败');
						}
						var url='manyShortMessageSend.action';
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

Ext.define('MonitoringSearchFrom', {
	extend : 'SearchFormPanel',
	height : 190,
//	flex:3,
	id : 'monitoringSearchFrom',
	initComponent : function() {
		var me = this;
		me.items = me.getItems();
		var successFn = function(json){
        	Ext.data.StoreManager.lookup(CONFIGNAME.get('belongArea')).add(json.deptList);
	    };
        var filureFn = function(json){
        	MessageUtil.showErrorMes(json.message);
        };
        //获取大区数据
        recompenseData.searchAreas(null,successFn,filureFn);
		this.callParent();
		me.initPage();
	},
	initPage : function() {
		var	me=this;
		var condition = new Condition();
		me.getForm().loadRecord(condition);
		Ext.getCmp(CONFIGNAME.get('conCustomerLevel')).getStore().add({'code':'ALL','codeDesc':i18n('i18n.recompense.all')});
		Ext.getCmp(CONFIGNAME.get('conCustomerLevel')).setValue('ALL');
		Ext.getCmp('monitoringTransType').getStore().add({'code':'ALL','codeDesc':i18n('i18n.recompense.all')});
		Ext.getCmp('monitoringTransType').setValue('ALL');
		if (Ext.isEmpty(User)||Ext.isEmpty(User.roleids)||me.isArrayContaintChar('4',User.roleids)) {
				Ext.getCmp(CONFIGNAME.get('reportDept')).disable(true);
				Ext.getCmp(CONFIGNAME.get('belongArea')).disable(true);
			}
		Ext.getCmp(CONFIGNAME.get('recompenseType')).getStore().add({'code':'','codeDesc':i18n('i18n.recompense.all')});
		Ext.getCmp(CONFIGNAME.get('recompenseType')).setValue('');
		Ext.getCmp('conRecompenseMethod').getStore().add({'code':'','codeDesc':i18n('i18n.recompense.all')});

	},
	isArrayContaintChar:function(char,array){
		for ( var i = 0; i < array.length; i++) {
			if (array[i]==char) {
				return true;
			}
			return false;
		}
	},
	initComb:function(combId){
		var grid =  Ext.getCmp(combId);
		grid.getStore().add({'id':User.deptId,'deptName':User.deptName});
		grid.setValue(grid.store.getAt(0).get('id'));
//		grid.store.on('load',function(th){
//			if (th.getCount()>0) {
//				grid.setValue(th.getAt(0).get("id"));
//			}
//		})
	},
	getItems : function() {
		return [ {
//			xtype : 'fieldset',
			// title : i18n('i18n.recompenseMonitoringN.searchCondition'),
			// margin:'3 0 10 3',
			layout : {
				type : 'table',
				columns : 3
			},
			defaults : {
				xtype : 'textfield',
				labelSeparator : '',
				labelWidth : 50,
				margin : '5 5 5 0',
				width : 255,
				colspan : 1,
				enableKeyEvents:true,
				listeners:{
					keypress:function(field,event){
						if(event.getKey() == Ext.EventObject.ENTER){
							Ext.getCmp('buttonSearchPanel').searchRecompense();
						}
					}
				}
			},
			items : [ {
				xtype:'textfield',
				name:'waybillNum',
				fieldLabel:i18n('i18n.recompense.orderNumOrErroeNum'),
				maxLength:20
			}, {
				xtype : 'datefield',
				name : CONFIGNAME.get('conReportStartTime'),
				id : CONFIGNAME.get('conReportStartTime'),
				convert : DpUtil.changeLongToDate,
				value : new Date(),
				// value:new Date().setDate(new Date().getDate()-31),
				fieldLabel : i18n('i18n.recompense.reportTime')
			}, {
				xtype : 'datefield',
				id : CONFIGNAME.get('reportEndTime'),
				name : CONFIGNAME.get('reportEndTime'),
				value : new Date(),
				fieldLabel : i18n('i18n.recompense.to')
			}, {
				xtype : 'combo',
				id : CONFIGNAME.get('conCustomerLevel'),
				name : CONFIGNAME.get('conCustomerLevel'),
				fieldLabel : i18n('i18n.recompense.custLevel'),
				displayField : 'codeDesc',
				valueField  : 'code',
				queryMode:'local',
				editable:false,
				store : DpUtil.getStore(CONFIGNAME.get('conCustomerLevel'),null,['code','codeDesc'],DataDictionary.MEMBER_GRADE)
			}, {
				id : CONFIGNAME.get('conCustNum'),
				name : CONFIGNAME.get('conCustNum'),
				fieldLabel : i18n('i18n.recompense.custNum')
			}, {
				xtype : 'combo',
				id : CONFIGNAME.get('belongArea'),
				name : CONFIGNAME.get('belongArea'),
				fieldLabel : i18n('i18n.recompense.belongArea'),
				displayField : 'deptName',
				valueField:'id',
				queryMode:'local',
				store : DpUtil.getStore(CONFIGNAME.get('belongArea'),null,['id','deptName'],[{'id':'ALL','deptName':i18n('i18n.recompense.all')}])
			},{
				xtype : 'combo',
				id : 'recompenseType',
				name :'recompenseType',
				fieldLabel:i18n('i18n.recompenseMonitoring.recompenseType'),//理赔类型
				store: DpUtil.getStore(CONFIGNAME.get('recompenseType'),null,['code','codeDesc'],DataDictionary.RECOMPENSE_TYPE),
				displayField: 'codeDesc',
				queryMode:'local',
				valueField: 'code',
				editable:false
			}, {
				xtype:'numberfield',
				fieldLabel:i18n('i18n.recompenseMonitoring.durationTime'),//持续时间为
				allowDecimals:false,
				hideTrigger:true,
				id:'durationTime',
				name:'beginDuration',
				maxValue:365,
				minValue:0
			},{
				xtype:'numberfield',
				fieldLabel:i18n('i18n.recompenseMonitoring.durationTimeTo'),//到
				allowDecimals:false,
				hideTrigger:true,
				id:'durationTimeTo',
				name:'endDuration',
				maxValue:365,
				minValue:0
			},{
				xtype : 'combo',
				id : 'conRecompenseMethod',
				name : CONFIGNAME.get('conRecompenseMethod'),
				fieldLabel : i18n('i18n.recompense.recompenseMethod'),
				displayField : 'codeDesc',
				valueField :'code',
				queryMode:'local',
				editable:false,
				store : DpUtil.getStore('conRecompenseMethod',null,['code','codeDesc'],DataDictionary.RECOMPENSE_WAY)
			},{
				xtype : 'combo',
				id : 'recompenseAmount',
				name :'recompenseAmount',
				fieldLabel:i18n('i18n.recompenseMonitoring.recompenseAmount'),//索赔金额
				displayField : 'amount',
				valueField  : 'limit',
				queryMode:'local',
				editable:false,
				store:Ext.create('RecompenseAmountStore')
			},{
				xtype : 'combo',
				id : 'realRecompenseAmount',
				name :'realAmount',
				fieldLabel:i18n('i18n.recompenseMonitoring.realRecompenseAmount'),//理赔金额
				displayField : 'amount',
				valueField  : 'limit',
				queryMode:'local',
				editable:false,
				store:Ext.create('RecompenseAmountStore')
			},{
				xtype : 'combo',
				id : 'handleStatus',
				name :'recompenseState',
				fieldLabel:i18n('i18n.recompenseMonitoring.handleStatus'),//处理状态
				displayField : 'codeDesc',
				valueField  : 'code',
				queryMode:'local',
				editable:false,
				store : DpUtil.getStore(CONFIGNAME.get('recompenseState'),null,['code','codeDesc'],DataDictionary.RECOMPENSE_STATUS)
			},{
				xtype:'combobox',
				id:'monitoringTransType',
				name:CONFIGNAME.get('transType'),
				queryMode:'local',
				fieldLabel:i18n('i18n.recompense.transType'),
				store:DpUtil.getStore(CONFIGNAME.get('transType'),null,['code','codeDesc'],DataDictionary.TRANSPORT_TYPE),
				displayField:'codeDesc',
				valueField:'code'
			}]
		} ];
	}
});

/**
 * 搜索按钮panel
 */
Ext.define('ButtonSearchPanel', {
	extend : 'NormalButtonPanel',
	id:'buttonSearchPanel',
//	weight : 800,
//	height : 37,
	grid : null,
	searchCondition:null,
	initComponent : function() {
		var me = this;
		this.items = me.getItems();
		this.callParent();
	},
	/**
	 * <p>查看理赔详情<p>
	 * @author 张斌
	 * @date 2012-06-14
	 */
	detailRecompense:function(){
		var me = this;
		var record = me.grid.getSelectionModel().getSelection();
		if (record.length<1) {
			MessageUtil.showMessage(i18n('i18n.recompense.havenotSelectOne'));
			return;
		}
		if (record.length>1) {
			MessageUtil.showMessage('只能选择一条记录');
			return;
		}
		var succseeFn = function(json){
			SearchError = true;
			SearchWorkFlow = true;
			RecompenseInfoDataNew = true;
			if(Ext.isEmpty(Ext.getCmp('recompenseDetailsUI'))){
				new RecompenseDetailsUI({'record':json.app,'actionIds':json.actionIds}).show();
				document.getElementsByTagName("html")[0].style.overflowY="auto";
				document.getElementsByTagName("html")[0].style.overflowX="auto";
				viewport.doLayout();
			}else{
				Ext.getCmp('recompenseDetailsUI').record = json.app;
				Ext.getCmp('recompenseDetailsUI').workFlowList = json.oaWorkflowList;
				Ext.getCmp('recompenseDetailsUI').actionIds = json.actionIds;
				Ext.getCmp('recompenseDetailsUI').show();
				document.getElementsByTagName("html")[0].style.overflowY="auto";
				document.getElementsByTagName("html")[0].style.overflowX="auto";
				viewport.doLayout();
			}
		}
		var failureFn = function(json){
			if(Ext.isEmpty(json)){
				MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
				//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
			}else{
				MessageUtil.showErrorMes(json.message);
			}
		}
		var param = {'recompenseId':record[0].get('id')};
		recompenseData.searchRecompenseById(param,succseeFn,failureFn);
	},
	getItems : function() {
		var me = this;
		return [ {
			xtype : 'leftbuttonpanel',
			width:350,
			items : [ {
				xtype : 'button',
				text :i18n('i18n.recompenseMonitoring.manyShortMessages'),//多条短信通知
				handler : function() {
					var monitoringData = me.grid.getSelectionModel().getSelection();
					if(monitoringData.length<1){
						MessageUtil.showMessage('请至少选择一条运单进行通知');
						return;
					}else{
						var tempStatus=monitoringData[0].get('status');
						if(monitoringData.length>1){
							for(var i=1;i<monitoringData.length;i++){
								if(tempStatus!=monitoringData[i].get('status')){
									MessageUtil.showMessage('多条短信发送时：处理状态必须全部一致！');
									return;
								}
								//大于1条短信发送判断是否含有快递理赔单
								if(monitoringData[i].get('waybill.transType')=='快递'||
									monitoringData[i].get('waybill.transType')=='TRANS_EXPRESS'){
										MessageUtil.showMessage('多条短信发送时,不能包含有快递理赔');
										return;
								}
							}
						}else{
							//1条短信发送判断是否含有快递理赔单
							if(monitoringData[0].get('waybill.transType')=='快递'||
								monitoringData[0].get('waybill.transType')=='TRANS_EXPRESS'){
									MessageUtil.showMessage('多条短信发送时,不能包含有快递理赔');
									return;
							}
						}
						if(!(tempStatus=='Submited'||tempStatus=='Handled'||tempStatus=='InOaApprove'||
							tempStatus=='Approved'||tempStatus=='InPayment'||tempStatus=='Submited')){
							MessageUtil.showMessage('抱歉，该状态无法进行多条短信通知');
							return;
						}
						for ( var i = 0; i < monitoringData.length; i++) {
							var array_element = monitoringData[i];
							if(monitoringData[i].get('waybill.transType') == 'TRANS_EXPRESS' || 
									monitoringData[i].get('waybill.transType') == '快递') {
								MessageUtil.showMessage('抱歉，快递类理赔不能进行发送短信操作');
								return;
							}
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
			},{
				xtype : 'button',
				text :i18n('i18n.recompense.urgeProcess'),
				handler : function() {
					var record = me.grid.getSelectionModel().getSelection();
					if (record.length<1) {
						MessageUtil.showMessage(i18n('i18n.recompense.havenotSelectOne'));
						return;
					}
					if(record.length>1){
						MessageUtil.showMessage('只能选择一条记录');
						return;
					}
					me.ugerProcess(record[0]);
					}
				},{
				xtype : 'button',
				text :i18n('i18n.recompense.detailInfo'),
				handler : function() {
					me.detailRecompense();
				}
			} ]
		}, {
			xtype : 'middlebuttonpanel'
		}, {
			xtype : 'rightbuttonpanel',
			items : [ {
				xtype : 'button',
				text :i18n('i18n.recompense.search'),
				handler : function() {
					if(!Ext.getCmp('monitoringSearchFrom').getForm().isValid()){
						return;
					}
					if((!Ext.isEmpty(Ext.getCmp('durationTime').getValue())&&!Ext.isEmpty(Ext.getCmp('durationTimeTo').getValue()))||
							(Ext.isEmpty(Ext.getCmp('durationTime').getValue())&&Ext.isEmpty(Ext.getCmp('durationTimeTo').getValue()))){
						if(Ext.getCmp('durationTime').getValue()>Ext.getCmp('durationTimeTo').getValue()){
							MessageUtil.showMessage('请检查持续时间，起始值是否小于等于结束值');
							return;
						};
					me.searchRecompense();
					}else{
						MessageUtil.showMessage('起始值和结束值必须同时为空或同时不为空！');
					}
				}
			}]
		} ];
	},
	ugerProcess:function(record){
		var successFn=function(json){
			MessageUtil.showInfoMes(json.message);
		};
		var failureFn=function(json){
			if(Ext.isEmpty(json)){
				MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
				//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
			}else{
				MessageUtil.showErrorMes(json.message);
			}
		};
		var param = {'recompenseId':record.get('id')};
		recompenseData.urgeProcess(param,successFn,failureFn);
	},
	searchRecompense : function() {
		//判断选择时间是否合理
		var me= this;
		var record = new Condition();
		Ext.getCmp('monitoringSearchFrom').getForm().updateRecord(record);
		//上报日期
		var reportDate = record.get(CONFIGNAME.get('conReportStartTime'));
		//上报结束时间
		var reportEndDate = record.get(CONFIGNAME.get('reportEndTime'));
		if ((reportEndDate-reportDate)/(24*3600*1000)>31||reportEndDate-reportDate<0) {
			MessageUtil.showMessage(i18n('i18n.recompense.infomation.timeOutOfRange'));
			return false;
		}
		if (me.validateDate(reportDate,reportEndDate)) {
			Ext.getCmp('pagingToolbar').moveFirst();
		}
	},
	validateDate:function(reDate,reEndDate){
		return true;
	}
});


/**
 * <p>搜索查询结果显示grid<p>
 * @author 潘光均
 * @date 2012-04-14
 */
Ext.define(	'MonitoringGridPanel',{
					extend : 'SearchGridPanel', // --第一步,继承PopupGridPanel
					id:'monitoringGridPanel',
					store : null,
					flex:7,
//					width : 835,
					record : null,
//					height : '64.5%',
					//layout:'anchor',
					autoScroll:true,
					initComponent : function() {
						var me = this;
						me.columns = me.getColumns();
						me.selModel =  me.getSelModel();
						me.bbar = me.getBBar();
						this.callParent();
					},
					listeners:{
						itemdblclick:function(){
							Ext.getCmp('buttonSearchPanel').detailRecompense();
						}
					},
					getColumns:function(){
	return [ {
		xtype : 'rownumberer',
		width : 40,
		text : i18n('i18n.recompense.num')
		},{
			header:i18n('i18n.recompenseMonitoring.oneShortMessages'),//单条短信通知
			dataIndex:'oneShortMessages',
			xtype:'actioncolumn',
			align:'center',
			items:[{
                    icon   : '../images/recompense/recompense/sendShortMessage.png',  
                    tooltip: '单条短信通知',
                    handler: function(grid,rowIndex,colIndex) {
                    	if(grid.getStore().getAt(rowIndex).get('waybill.transType')=='快递'
                    		||grid.getStore().getAt(rowIndex).get('waybill.transType')=='TRANS_EXPRESS'){
                    		MessageUtil.showMessage('抱歉，快递类理赔不能进行发送短信操作');
                    		return;
                    	}else{
	                 		if(Ext.getCmp('oneShortMessagesWindow')){
	                 			Ext.getCmp('oneShortMessagesWindow').show();	
	                 		}else{
	                 			Ext.create('ShortMessagesWindow',{
	                 				id:'oneShortMessagesWindow',
	                 				recompenseId:grid.getStore().getAt(rowIndex).get('id'),
	                 				waybillNum:grid.getStore().getAt(rowIndex).get('waybill.waybillNumber'),
	                 				reportDeptName:grid.getStore().getAt(rowIndex).get('reportDeptName'),
	                 				recompenseType:grid.getStore().getAt(rowIndex).get('recompenseType'),
	                 				status:grid.getStore().getAt(rowIndex).get('status'),
	                 				sendPersonNum:'one'
	                 			}).show();
	                 			var tempStatus=grid.getStore().getAt(rowIndex).get('status');
	                 			if(!(tempStatus=='Submited'||tempStatus=='Handled'||tempStatus=='InOaApprove'||
								tempStatus=='Approved'||tempStatus=='InPayment'||tempStatus=='Submited')){
									Ext.getCmp('templateToSend').setDisabled(true);
								}
	                 		}
                    	}
                    }
                }]
		} , {
							header : i18n('i18n.recompense.recompenseMethod'),
							dataIndex : CONFIGNAME.get('recompenseMethod'),
							renderer : function(value){
								return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.RECOMPENSE_WAY);
							}
						},{ // --第二步,规定表格title
							header : i18n('i18n.recompense.recompensetype'),
							dataIndex : CONFIGNAME.get('recompenseType'),
							 renderer : function(value){
					            	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.RECOMPENSE_TYPE);
					            }
						}, {
							header : i18n('i18n.recompense.orderNumOrErroeNum'),
							dataIndex : CONFIGNAME.get('waybill'),
							renderer:function(value){
								if(Ext.isEmpty(value)){
									return null;
								}else{
									return value.waybillNumber;
								}
							}
						}, {
							header : i18n('i18n.recompense.custNum'),
							dataIndex : CONFIGNAME.get('customer'),
							renderer:function(value){
								if(!Ext.isEmpty(value)){
									return value.custNumber;
								}else{
									return null;
								}
							}
						}, {
							header : i18n('i18n.recompense.custLevel'),
							dataIndex : CONFIGNAME.get('customer'),
							renderer:function(value){
								if(!Ext.isEmpty(value)){
									return DpUtil.changeDictionaryCodeToDescrip(value.degree,DataDictionary.MEMBER_GRADE);
								}else{
									return null;
								}
							}
						}, {
							header : i18n('i18n.recompense.reportDept'),
							dataIndex : CONFIGNAME.get('reportDeptName')
						},{
							header : i18n('i18n.recompense.belongArea'),
							dataIndex :CONFIGNAME.get('deptName')
						
						}, {
							header : i18n('i18n.recompense.reportTime'),
							dataIndex : CONFIGNAME.get('reportDate'),
							renderer : function(value){
				            	return new Date(value).format('yyyy-MM-dd hh:mm:ss');
				            }
						}, {
							header : i18n('i18n.recompense.handleTime'),
							dataIndex : CONFIGNAME.get('modifyDate'),
							renderer : function(value){
								if (null!=value) {
									return new Date(value).format('yyyy-MM-dd hh:mm:ss');
								}
				            }
						}, {
							header : i18n('i18n.recompense.handleStatus'),
							dataIndex : CONFIGNAME.get('status'),
							 renderer : function(value){
					            	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.RECOMPENSE_STATUS);
					            }
						},{
							header : i18n('i18n.recompenseMonitoring.recompenseAmount'),
							dataIndex :'recompenseAmount'
						},{
							header : i18n('i18n.recompenseMonitoring.realRecompenseAmount'),
							dataIndex :'realAmount'
						}, {
							header : i18n('i18n.recompense.statusSustainTime'),
							dataIndex :'duration',
							renderer:function(value){
								if(Ext.isEmpty(value)){
									return null;
								}else{
									return  Math.round(value/24/60)+i18n('i18n.recompense.day');
								}
							}
						}, {
							header : i18n('i18n.recompense.transType'),
							dataIndex  :'waybill.transType',
							renderer : function(value){
					            	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.TRANSPORT_TYPE);
					            }
						}];
					},
					getSelModel : function() {
						return  Ext.create('Ext.selection.CheckboxModel', {
							
						})
					},
					getBBar : function() {
						var me = this;
				    	return Ext.create('Ext.toolbar.Paging', {
							id : 'pagingToolbar',
							store : me.store,
							displayMsg : i18n('i18n.recompense.displayMsg'),
							displayInfo : true,
							items:[
					            '-',{
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
				}
})

Ext.define('MonitorSearchView', {
	extend : 'Ext.container.Viewport',
	height : '100%',
	record : null,
	autoScroll : true,
	//监控查询条件form
	monitoringSearchFrom:null,
	//显示列表
	monitoringGridPanel:null,
	//按钮panel
	buttonSearchPanel:null,
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	initComponent : function() {
		var me = this;
		me.record = new Condition();
		me.items = me.getItems();
		Ext.getCmp('monitoringSearchFrom').getForm().updateRecord(me.record);
		this.callParent();
		this.addStoreListener();
	},
	//获取查询表的值
	produceSearchConditon:function(){
		var me = this;
		//获取界面的值，用户判断查询条件逻辑
		var record = new Condition();
		Ext.getCmp('monitoringSearchFrom').getForm().updateRecord(record);
		record.set(CONFIGNAME.get('approveTime'),null);
		record.set(CONFIGNAME.get('approveEndTime'),null);
		//运输类型
		var transType=Ext.getCmp('monitoringTransType').getValue();
		record.set(CONFIGNAME.get('transType'),transType);
		//设置所属区域的值
		var belongDept = Ext.getCmp(CONFIGNAME.get('belongArea')).getValue();
		record.set(CONFIGNAME.get('belongArea'),belongDept);
        return record;
	},
	//添加查询结果store的beforeLoad事件
	addStoreListener:function(){
		var me = this;
		Ext.getCmp('monitoringGridPanel').store.on('beforeload',function(store,operation,obj){
			var record = me.produceSearchConditon();
			var searchParams = DpUtil.produceSearchParams(record);
			Ext.apply(operation,{
				params : searchParams
			});
		});
	},
	//获取window的控件
	getItems : function() {
		var me =this;
		me.monitoringSearchFrom = new MonitoringSearchFrom();
		 var recompenseSearchStore =recompenseData.getMonitorRecompenseSearchStore();
		me.monitoringGridPanel = new MonitoringGridPanel({
			 'store' : recompenseSearchStore,
			 'record' : me.record
		 });
		 me.buttonSearchPanel = new ButtonSearchPanel({
				'store' : recompenseSearchStore,
				'grid' : me.monitoringGridPanel,
				'searchCondition':me.produceSearchConditon()
			});
		return [ me.monitoringSearchFrom, me.buttonSearchPanel,me.monitoringGridPanel ];
	}
});



/**.
 * <p>
 * 理赔查询主页面</br>
 * </p>
 * @author  潘光均
 * @时间    2012-03-19
 */
Ext.onReady(function() {
	var keys =[ 'DANGER_TYPE','RECOMPENSE_WAY',
	           'RECOMPENSE_TYPE','RECOMPENSE_STATUS','MEMBER_GRADE',
	           'TRANSPORT_TYPE','CUST_DROWMONEY_TYPE','ACCOUNT_PROPERTIES',
	           'RECOMPENSE_WORKFLOW_TYPE','RECOMPENSE_WORKFLOW_STATUS'];
	initDataDictionary(keys);
	initUser();
	initArea();
    Ext.QuickTips.init();    
    viewport =  new MonitorSearchView();
	 viewport.setAutoScroll(false);
	 viewport.doLayout();
});
Ext.form.Field.prototype.msgTarget='side';