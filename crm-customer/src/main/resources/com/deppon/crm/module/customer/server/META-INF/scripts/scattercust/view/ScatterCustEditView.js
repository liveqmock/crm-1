/**
* 散客编辑界面win										ScatterCustEditWindow
* 散客基本信息form										BasicScatterCustForm
* 散客账号信息panel										ScatterCustAccountPanel
* 散客业务信息form										ScatterBusinessForm
*/

var scatterCustControl = (CONFIG.get('TEST'))? Ext.create('ScatterCustDataTest'):Ext.create('ScatterCustData');
//var scatterCustControl = Ext.create('ScatterCustData');
var memberCustControl = (CONFIG.get('TEST'))? Ext.create('MemberCustDataTest'):Ext.create('MemberCustData');
ScatterCustAccountObj = function(){};
var RealTimeVerifyResult = {success:[true,true,true,true,true,true,true,true,true,true,true],
		message:['',[],[],'','','','',[],'','',[]]};
var SequenceAccount = 1;//标记临时 会员账号 id:"A"+序列
//新增账户 Id 序列，为了和后台区分 增加"A"开头区别
ScatterCustAccountObj.getSequenceAccount = function(){
	return 'A'+SequenceAccount++;
};  
Ext.define('ScatterCustEditWindow',{
	extend:'PopWindow',
	title:i18n('i18n.ScatterCustManagerView.scatterEditUi'),
	fbar:null,
	basicScatterCustInfo:null,     //客户基本信息
	scatterCustAccountPanel:null,	//散客账号信息
	scatterCustBusinessInfo:null,  //客户业务信息
	scatterCustData:scatterCustControl,
	height:770,
	width:690,
	layout: 'fit',
    viewStatus:null, //查看状态，，add表示新增，update表示修改，view表示查看
    scatterCustRecord:null, //父界面传入的散客对象
    scatterCustManagerView:null, //父界面
    custLabelInfoForm:null,//标签
	listeners:{
		close:function(){
			custLabels= [];
		}
	},
	initComponent:function(){
		var me = this;
		//设置tbar
		me.fbar = me.getFbar();
		//设置items
		me.basicScatterCustInfo = Ext.create('BasicScatterCustForm',{'viewStatus':me.viewStatus,'scatterCustData':me.scatterCustData,'parent':me});
		me.scatterCustAccountPanel = Ext.create('ScatterCustAccountPanel',{'viewStatus':me.viewStatus,'scatterCustData':me.scatterCustData,'parent':me});
		me.scatterCustBusinessInfo = Ext.create('ScatterBusinessForm',{'scatterCustData':me.scatterCustData});
		
		me.custLabelInfo = Ext.create('CustLabelInfoForm',{'status':me.viewStatus,'custType':'SC_CUSTOMER','custData':me.scatterCustRecord});
		custLabeBasiclList = me.scatterCustRecord.data.custLabels;
		
		me.items = [{
			xtype:'basicformpanel',
			layout:{
		        type:'vbox',
		        align:'stretch'
		    },
			items:
				[{
					xtype:'basicpanel',
					layout:'fit',
					flex:2,
					items:[me.basicScatterCustInfo]
				},{
					xtype:'basicpanel',
					layout:'fit',
					flex:1,
					items:[me.scatterCustAccountPanel]
				},{
					xtype:'basicpanel',
					layout:'fit',
					flex:1,
					items:[me.scatterCustBusinessInfo]
				},{
					xtype:'basicformpanel',
//					region:'south',
					layout:'fit',
					height:158,
					items:[me.custLabelInfo]
				}]
		 }];
		
		//初始化散客账号的store
		scatterAccountControl.clearStore();
		//关闭前事件
		me.on('beforeclose',me.beforeCloseEvent);
		initBasicData(me.viewStatus,'SC_CUSTOMER',me.scatterCustRecord,null,top.User.deptId);
		this.callParent();
		
		me.controlComponentByViewStatus();
	},
	beforeCloseEvent:function(){
//			//清空 全局变量，联系人偏好地址,所有联系人地址
	    	RealTimeVerifyResult = {success:[true,true,true,true,true,true,true,true,true,true,true],
	    	message:['',[],[],'','','','',[],'','',[]]}
////			//编辑界面关闭时重新加载父页面的数据
//			if(this.scatterCustManagerView){
//				this.scatterCustManagerView.reloadScatterCust(oldScRecode);
//			}
    	var selectWin = Ext.getCmp('addrselectAreaTabWindow');
		if(!Ext.isEmpty(selectWin)){
			selectWin.close();
		}
	},
	//通过查看状态设置控件
	controlComponentByViewStatus:function(){
		var basicForm = this.down('basicformpanel');
		
		if(this.scatterCustRecord == null){
			MessageUtil.showMessage(i18n('i18n.PotentialCustManagerView.incomingScatter'));
		}else{
			var dept = basicForm.getForm().findField('deptId');
			dept.store.add(Ext.create('CurrentUserDeptListModel',{'deptId':this.scatterCustRecord.get('deptId'),'deptName':this.scatterCustRecord.get('deptName')}));
			if('add' != this.viewStatus){
				// 修改或查看散客时  加载所属部门信息
				dept.store.add(Ext.create('CurrentUserDeptListModel',{'deptId':this.scatterCustRecord.get('deptId'),'deptName':this.scatterCustRecord.get('deptName')}));
				dept.setReadOnly(true);
			}
			//加载数据
			basicForm.getForm().loadRecord(this.scatterCustRecord);
			this.scatterCustData.getScatterCustAccountStore().loadData(Ext.isEmpty(this.scatterCustRecord.get('accounts'))?[]:this.scatterCustRecord.get('accounts'));
			//查看状态，锁定所有的控件
			if(this.viewStatus == 'view'||this.scatterCustRecord.get('isCancel')==1){					
				this.lockAllComponent();
			}
		}
	},
	//锁定所有控件
	lockAllComponent:function(){
		var basicForm = this.down('basicformpanel');
		var mainForm = basicForm.getForm();
		mainForm.getFields().each(function(field){
			field.setReadOnly(true);
		});
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			id:'save_cust_id',
			text:i18n('i18n.PotentialCustEditView.save'),
			scope:me,
			handler:function(){
				MessageUtil.showQuestionMes(i18n('i18n.ScatterCustManagerView.showAreYouSureMessage'),function(e){
					if('yes'==e){						
						me.saveScatterCust();
					}
				});
			}
		},{
			xtype:'button',
			text:i18n('i18n.PotentialCustEditView.cancel'),
			scope:me,
			handler:me.cancelSaveScatter
		}];
	},
	//保存散客信息
	saveScatterCust:function(button){
		var me = this;
//		button.setDisabled(true);//锁定保存按钮
		Ext.getCmp('save_cust_id').setDisabled(true);//锁定保存按钮
		if(RealTimeVerifyResult.success[0] != true){
			Ext.getCmp('save_cust_id').setDisabled(false);
			MessageUtil.showMessage(RealTimeVerifyResult.message[0]);
			return;
		}
		var validateBussinessRoleRs = me.validateBussinessRole();
		//校验结果不通过时 提示相应错误
		if(validateBussinessRoleRs.success){
			var basicForm = this.down('basicformpanel');
			var mainForm = basicForm.getForm();
			var record = me.scatterCustRecord.copy();
			mainForm.updateRecord(record);
			
			//保存成功回调函数
			var saveSuccessFn = function(result){
				var workFlowId = result.workFlowId;
				// 存在工作流则 提示给用户
				if(Ext.isEmpty(workFlowId)){
					MessageUtil.showInfoMes(i18n('i18n.PotentialCustEditView.saveSuccess'));
				}else{
					var m = i18n('i18n.PotentialCustEditView.saveSuccess')+' 工作流号为：'+workFlowId;
					MessageUtil.showInfoMes(m);
				}
				//编辑界面关闭时重新加载父页面的数据
//				if(me.scatterCustManagerView){
//					me.scatterCustManagerView.reloadScatterCust(me.scatterCustRecord);
//				}
				Ext.getCmp('save_cust_id').setDisabled(false);
				me.close();
				me.scatterCustManagerView.searchResult.store.loadPage(1);
			};
			//保存成功回调函数
			var updateSuccessFn = function(result){
				var workFlowId = result.workFlowId;
				// 存在工作流则 提示给用户
				if(Ext.isEmpty(workFlowId)){
					MessageUtil.showInfoMes(i18n('i18n.PotentialCustEditView.saveSuccess'));
				}else{
					var m = i18n('i18n.PotentialCustEditView.saveSuccess')+' 工作流号为：'+workFlowId+',请通知经理审批';
					MessageUtil.showInfoMes(m);
				}
//				mainForm.updateRecord(me.scatterCustRecord);
//				//重新设置ID
//				me.scatterCustRecord.set('id',result.scatterCust.id);
//				//编辑界面关闭时重新加载父页面的数据
//				if(me.scatterCustManagerView){
//					me.scatterCustManagerView.reloadScatterCust(me.scatterCustRecord);
//				}
				Ext.getCmp('save_cust_id').setDisabled(false);
				me.close();
				me.scatterCustManagerView.searchResult.store.loadPage(1);
			};
			//保存失败回调函数
			var saveFailFn = function(result){
					MessageUtil.showErrorMes(result.message);
					Ext.getCmp('save_cust_id').setDisabled(false);
			};
			if('add' == me.viewStatus){
				//为空的时候。赋值一个空的数组
				if(record.data.custLabels == '') {
					record.data.custLabels = new Array();
				}
				// 新增散客
				record.data.custLabels = custLabels;
				me.scatterCustData.saveScatterCust(me.assembleNewInfo(record),saveSuccessFn,saveFailFn);				
			}else if('update' == me.viewStatus){
//				params.scatterCust = record.data;
				var params = me.assembleUpdateInfo(me.scatterCustRecord,record);
				if(Ext.isEmpty(params)){//必须修改散客信息后才可以提交
					MessageUtil.showMessage(i18n('i18n.ScatterCustManagerView.modifyCustInfoFirst'));
					Ext.getCmp('save_cust_id').setDisabled(false);
					return;
				}
				params.scatterCust.deptName = top.User.deptName;
				//修改散客
				me.scatterCustData.updateScatterCust(params,updateSuccessFn,saveFailFn);
			}
		}else{
			Ext.getCmp('save_cust_id').setDisabled(false);
			//提示相应后台或前台提示语
			MessageUtil.showMessage(validateBussinessRoleRs.message);
		}
	},
	//取消保存散客信息
	cancelSaveScatter:function(){
		custLabels= [];
		this.close();
	},
	// 保存时校验
	validateBussinessRole:function(){
		var me = this;
		var message = '';//校验结果 提示语
		var success = true;//检验结果 true为通过，false为不通过
		var form = me.basicScatterCustInfo.getForm();
		var busForm = me.scatterCustBusinessInfo.getForm();
		// 必填项是否填写完整且正确
		if(!form.isValid()||!busForm.isValid()){
			success = false;
			message = i18n('i18n.ScatterCustManagerView.message_ReFill');
			return {success:success,message:message};
		}
		//  手机号码和固定电话不能同时为空
		if( Ext.isEmpty(form.findField('linkManMobile').getValue()) && 
				Ext.isEmpty(form.findField('linkManPhone').getValue())){
			success = false;
			message = i18n('i18n.ScatterCustManagerView.custMobileAndPhoneWarning');
			return {success:success,message:message};
		}
		//  如果城市不为空，则必须选择到市
		var city = form.findField('city').getValue();
		if( !Ext.isEmpty(city) && city.split('-').length<1){
			success = false;
			message = i18n('i18n.ScatterCustManagerView.mustChooseCity');
			return {success:success,message:message};
		}
		//返回结果
		return {success:success,message:message};
	},
	// 收集散客保存数据
	assembleNewInfo:function(scatterRecord){
		var me = this;
		var saveParams = {};//传给后台的最终保存实体
		//收集散客账户STORE信息
		var addAccount = [];
		me.scatterCustData.getScatterCustAccountStore().each(function(record){
			record.set('isDefaultAccount',record.get('isDefaultAccount')?'1':'0');
			addAccount.push(record.data);
		});
		// 处理城市 id
		scatterRecord.set('city',Ext.isEmpty(scatterRecord.get('city'))?'':scatterRecord.get('city').split('-')[1]);
		saveParams.scatterCust = scatterRecord.data;
		//账号信息
		saveParams.scatterCust.accounts = addAccount;
		return saveParams;
	},
	//TODO 收集散客修改数据
	assembleUpdateInfo:function(oadScatterRecord,scatterRecord){
		var me = this;
		var saveParams = {};//传给后台的最终保存实体
		var alterFields = [];//修改的字段信息 包括散客基本信息修改和散客账户修改信息
		var alterDelAccountList = [];//删除的散客账户信息
		var alterAddAccountList = [];//新增的散客账户信息
		
		var oldCustLabel = oadScatterRecord.data.custLabels;//旧的labels
		//判断是否修改了客户标签
		var flag = true;
		flag = judgeScLabelsWhetherAlter(oldCustLabel,custLabels);
		
		collectAlterScatterCustData(alterFields,scatterRecord);
		collectAllAcountAlterData(alterFields,alterDelAccountList,alterAddAccountList);
		
		oadScatterRecord.data.custLabels = custLabels;
		saveParams.scatterCust = oadScatterRecord.data;
		saveParams.updateDataList = alterFields;
		saveParams.addScatterAccount = alterAddAccountList;
		saveParams.deleteDataList = alterDelAccountList;
		if (Ext.isEmpty(alterFields)&& Ext.isEmpty(alterAddAccountList)
				&& Ext.isEmpty(alterDelAccountList) && flag){return '';}
		return saveParams;
	}
});

/**
* 散客基本信息
*/
Ext.define('BasicScatterCustForm',{
	extend:'BasicFormPanel',
	scatterCustData:null,
	items:null,
	parent:null,
	height:245,
	viewStatus:null,										//散客编辑的操作状态 add,update,view
	initComponent:function(){
		//初始化部门信息
		initDeptAndUserInfo("1");
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		var operateType = 'ADD';
		if('view'==me.parent.viewStatus){
			operateType = 'VIEW';
		}else if('update'==me.parent.viewStatus){
			operateType = 'UPDATE';
		}else if('add'==me.parent.viewStatus){
			operateType = 'ADD';
		}
		return [{
			xtype:'fieldset',
			title:i18n('i18n.ScatterCustManagerView.scatterInfo'),
			layout:{
				type:'table',
				columns:3
			},
			defaultType:'dptextfield',
			defaults:{
				labelWidth:75,
				width:210,
				enableKeyEvents:true,
				listeners:{
					scope:me,
					keypress:me.enterKeyEvent,
					change:me.changeEvent
				}
			},
			items:[{
					//fieldLabel:'所属部门',
					xtype:'belongdeptcombox',
					name:'deptId',
					functionName :'ScatterCustomerFunction',
					fieldLabel:i18n('i18n.ScatterUpgradeView.belongdeptName')+DpUtil.getSpecialStr('redSpan'),
					listeners:{
						select:function(combo,records){
		            		if(records.length > 0){
		            			//赋值所属部门 name
		            			var form = combo.up('form').getForm();
		            			form.findField('deptName').setValue(records[0].get('deptName'));
		            			var linkManName = form.findField('linkManName').getValue();
								var linkManPhone = form.findField('linkManPhone').getValue();
								var deptId = form.findField('deptId').getValue();
								var mobile = form.findField('linkManMobile').getValue();
								if( !Ext.isEmpty(linkManPhone) && !Ext.isEmpty(linkManName)
										&&combo.isValid()&& 'view' != me.viewStatus){							
									validateTel(linkManPhone,linkManName,deptId);
								}
								if('view' != me.viewStatus && !Ext.isEmpty(mobile)
										&& combo.isValid()){
									validateMobil(mobile,deptId,linkManName);
								}
		            		}
						}
					}
				},{
					fieldLabel:i18n('i18n.ScatterCustManagerView.scatterCustNumber'),
					readOnly:true,
					name:'scatterNum'
				   },{
					fieldLabel:i18n('i18n.ScatterCustManagerView.scatterCustName')+DpUtil.getSpecialStr('redSpan'),
					name:'custName',
					maxLength :40,	
					maxLengthText :i18n('i18n.ManualRewardIntegralEditView.m_maxLength',40),
					allowBlank:false,
					blankText:i18n('i18n.ScatterCustManagerView.message_custNameNotNull'),
					listeners:{
						blur:function(field){
							// 增加失焦事件 散客名称自动填充到联系人名称上,只有新增才进行此操作
							var form = field.up('form');
							var value = field.getValue();
							if(Ext.isEmpty(value)){
								MessageUtil.showMessage('客户姓名不能为空');
							}
							if('add' == me.viewStatus && field.isValid()){	
								var linkManName = form.getForm().findField('linkManName');
								linkManName.setValue(field.getValue());
								var linkManNameValue = form.getForm().findField('linkManName').getValue();
								var linkManPhone = form.getForm().findField('linkManPhone').getValue();
								var deptId = form.getForm().findField('deptId').getValue();
								var linkManPhoneValid = form.getForm().findField('linkManPhone');
								if( !Ext.isEmpty(linkManPhone) && !Ext.isEmpty(linkManNameValue)
										&&linkManPhoneValid.isValid()
										&&value != me.parent.scatterCustRecord.get('custName')){
									
									validateTel(linkManPhone,linkManName,deptId);
								}
								
							}
						}
					}
				   },{
					fieldLabel:i18n('i18n.PotentialCustManagerView.contactName')+DpUtil.getSpecialStr('redSpan'),
					name:'linkManName',
//					readOnly:true,
					maxLength :40,	
					maxLengthText :i18n('i18n.ManualRewardIntegralEditView.m_maxLength',40),
					allowBlank:false,
					blankText:i18n('i18n.ScatterCustManagerView.message_linkManNameNotNull'),
					   //增加失焦事件 实时校验固话加姓名，前提联系人名称和固话都不为空
					listeners:{
						blur:function(field){
							var form = field.up('form').getForm();
							var value = field.getValue();
							// 固话加联系人姓名不能重复
							var linkManName = form.findField('linkManName').getValue();
							var linkManPhone = form.findField('linkManPhone').getValue();
							var deptId = form.findField('deptId').getValue();
							if(Ext.isEmpty(value)){
								MessageUtil.showMessage('联系人姓名不能为空');
							}
							if( !Ext.isEmpty(linkManPhone) && !Ext.isEmpty(linkManName)
									&&field.isValid()&& value !=me.parent.scatterCustRecord.get('linkManName')){
								validateTel(linkManPhone,linkManName,deptId);
							}
							
						}
   				     }
				   },{
					fieldLabel:i18n('i18n.PotentialCustManagerView.contactPhone')+DpUtil.getSpecialStr('redSpan'),
					name:'linkManMobile',
	   				regex : DpUtil.linkWayLimit('M'),
					listeners:{
						scope:me,
						change:function(field,newValue,oldValue){
							// 在新增和修改状态并且填入的值不为空的情况下，当输入手机号码过长时，
							//会自动删减掉多余出来的数字
							if('view' != me.viewStatus && !Ext.isEmpty(newValue)){
								DpUtil.autoChangeMobileLength(field,newValue);
						}
					},
					// 增加失焦事件 实时校验手机号码
					blur:function(field){
						var mobile = field.getValue();
						var form = field.up('form');
						var deptId = form.getForm().findField('deptId').getValue();
						var phone = form.getForm().findField('linkManPhone').getValue();
						var linkManName = form.getForm().findField('linkManName').getValue();
						if('view' != me.viewStatus && !Ext.isEmpty(mobile)&& field.isValid()
								&&mobile != me.parent.scatterCustRecord.get('linkManMobile')){
							validateMobil(mobile,deptId,linkManName);
						}
						if(Ext.isEmpty(mobile)&&Ext.isEmpty(phone)){
							MessageUtil.showMessage('手机号码和固定电话不能同时为空');
						}
					  }
					}
				   },{
                   fieldLabel:i18n('i18n.PotentialCustManagerView.contactTel')+DpUtil.getSpecialStr('redSpan'),
   				   name:'linkManPhone',
   				   emptyText:('view' != me.viewStatus)?'021-31350606-12':'',
   				   regex : DpUtil.linkWayLimit('T'),
				   // 增加失焦事件 实时校验固话加姓名，前提联系人名称和固话都不为空
   				   listeners:{
						blur:function(field){
							var form = field.up('form');
							var value = field.getValue();
							// 固话加联系人姓名不能重复
							var linkManName = form.getForm().findField('linkManName').getValue();
							var linkManPhone = form.getForm().findField('linkManPhone').getValue();
							var linkManMobile = form.getForm().findField('linkManMobile').getValue();
							var deptId = form.getForm().findField('deptId').getValue();
							if( !Ext.isEmpty(linkManPhone) && !Ext.isEmpty(linkManName)
									&&field.isValid()&& value !=me.parent.scatterCustRecord.get('linkManPhone')){
								validateTel(linkManPhone,linkManName,deptId);
							}
							if(Ext.isEmpty(value)&&Ext.isEmpty(linkManMobile)){
								MessageUtil.showMessage('手机号码和固定电话不能同时为空');
							}
						}
   				     }
                   },{
					fieldLabel:i18n('i18n.ScatterCustManagerView.custIdCradNumber'),
					name:'idNumber',
					minLength:18,
					regex : DpUtil.linkWayLimit('I'),
					regexText:i18n('i18n.ScatterCustManagerView.scatterIdCardWarnning'),
					minLengthText:i18n('i18n.ScatterCustManagerView.scatterIdCardWarnning'),
					listeners:{
						blur:function(field){
							// 增加失焦事件 散客名称自动填充到联系人名称上
							var value = field.getValue();
							if('view' != me.viewStatus && !Ext.isEmpty(value)&&field.isValid()
									&& value !=me.parent.scatterCustRecord.get('idNumber')){
								validaeIdCardInvalid(value);
							}
						}
					}
				   },{
					fieldLabel:User.deptCityLocation == "1"?'商业登记号':i18n('i18n.ScatterCustManagerView.taxId'),
					name:'taxregistNo',
					regex:User.deptCityLocation == "1"?DpUtil.linkWayLimit('HKTAX'):DpUtil.linkWayLimit('CNTAX'),
					emptyText:User.deptCityLocation == "1"?'输入时不要输入“-”即12345678000':'请输入合法的税务登记号',
					regexText:User.deptCityLocation == "1"?'商业登记号为：12345678-000，输入时不要输入“-”即12345678000':'请输入合法的税务登记号',
					listeners:{	
						scope:me,
						blur:function(field){
							// 验证税务登记号是否修改，如果没有修改，则不向后台请求
							//User.deptCityLocation == 0 表示是大陆地区
							var value = field.getValue();
							if('view' != me.viewStatus && !Ext.isEmpty(value) && field.isValid()
									&& value !=me.parent.scatterCustRecord.get('taxregistNo')
									&& '0' == User.deptCityLocation){
								validateTaxInvalid(value);
							}else if('view' != me.viewStatus && !Ext.isEmpty(value) && field.isValid()
									&& value !=me.parent.scatterCustRecord.get('taxregistNo')
									&&'1' == User.deptCityLocation){
								me.validateHKTaxInvalid(field)
							}	
						}
					}
				   },{
					fieldLabel:i18n('i18n.ScatterCustManagerView.custProperty'),
					xtype:'dpcombo',
					store:me.scatterCustData.getCustNatureStore(),
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					name:'custProperty'
//						name:'custNature',
				   },{
					fieldLabel:i18n('i18n.ScatterCustManagerView.companyProperties'),
					xtype:'dpcombo',
					store:me.scatterCustData.getCompanyNatureStore(),
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					name:'companyNature'
				   },{
					fieldLabel:i18n('i18n.ScatterCustManagerView.companySize'),
					xtype:'dpcombo',
					store:me.scatterCustData.getCompanySizeStore(),
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					name:'companySize'
				   },{
					fieldLabel:i18n('i18n.ScatterCustManagerView.custAttribute'),
					xtype:'dpcombo',
					store:me.scatterCustData.getCustPropertyStore(),
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					name:'custNature',
//						name:'custProperty',
//					readOnly:true,
					//TODO 客户属性是否为必填
//					allowBlank:false,
					blankText:i18n('i18n.ScatterCustManagerView.custAttributeNotNull')
				   },{
					fieldLabel:i18n('i18n.ScatterCustManagerView.scatterCustType'),
					xtype:'dpcombo',
					store:me.scatterCustData.getScatterCustTypeStore(),
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					readOnly:true,
					name:'scatterCustType'
				   },{
					fieldLabel:i18n('i18n.PotentialCustManagerView.custSource'),
					xtype:'dpcombo',
					store:me.scatterCustData.getSCCourceStore(),
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					name:'custbase'
				   },{
					fieldLabel:i18n('i18n.PotentialCustManagerView.bizStatus'),
					xtype:'dpcombo',
					store:me.scatterCustData.getBusinessOpportunityStore(),
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					name:'bussinesState',
					readOnly:true
				   },{
					   colspan:2,
						xtype:'customertrade',
						width:420,
						border:0,
						trade_labelWidth:75,
						trade_width:210,
						trade_name:'trade',
						trade_fieldname:i18n('i18n.PotentialCustManagerView.industry'),
						second_trade_labelWidth:75,
						second_trade_width:210,
						second_trade_name:'secondTrade',
						second_trade_fieldname:i18n('i18n.secondTrade.secondTrade')
				   },{
					fieldLabel:i18n('i18n.MemberCustEditView.procRedit'),
					xtype:'dpnumberfield',
					//临欠额度：正整数，小于等于3000元
					minValue:0,
					maxValue:3000,
					name:'velocityAmount',
					hideTrigger: true,//隐藏上下增减量按钮
					regex : /^\d{1,}$/,//0位小数
			        mouseWheelEnabled: false//鼠标滚动
				   },{
					fieldLabel:i18n('i18n.PotentialCustManagerView.position'),
					maxLength :20,	
					maxLengthText :i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20),
					name:'post'
				   },{
				   
						fieldLabel:i18n('i18n.ScatterCustEditView.city'),//
//						labelWidth:70,
						xtype:'areaaddresscombox',
						id:'addrselect',
						name:'city',
						width:210,
						operateType:operateType,
						disabled:('view' == me.viewStatus)?true:false,
						selectedValue:me.parent.scatterCustRecord.get('city'),
						tabPanel:['provincePanel','cityPanel'],
						blankText:i18n('i18n.MemberCustEditView.cityAlert'),
						emptyText:i18n('i18n.MemberCustEditView.cityAlert'),
						listeners:{
							change:function(combobox,newValue,oldValue){
								if(!DpUtil.isEmpty(newValue)){
									var array = combobox.getRawValue().split(i18n('i18n.PotentialCustManagerView.searchEndTime'));	
									if(array.length == 2){
										me.up('form').getForm().findField('cityName').setValue(array[1]);
										me.up('form').getForm().findField('address').setValue(array[0]+'-'+array[1]+'-');
									}
								}
							}
						}
				   },{
					fieldLabel:i18n('i18n.PotentialCustEditView.address'),
					colspan:1,
					width:210,
					maxLength :100,	
					maxLengthText :i18n('i18n.ManualRewardIntegralEditView.m_maxLength',100),
					name:'address'
				   },{
					fieldLabel:'财务冻结',//i18n('i18n.ScatterCustManagerView.remark'),
					name:'finOver',
					readOnly:true,
		        	xtype:'dpcombobox',
		        	queryMode:'local',
		            forceSelection:true,
					displayField:'name',
					valueField:'value',
					store:me.scatterCustData.getBooleanStore()
				   },{
					fieldLabel:i18n('i18n.ScatterCustManagerView.remark'),
					colspan:2,
					width:420,
					maxLength :200,	
					maxLengthText :i18n('i18n.ManualRewardIntegralEditView.m_maxLength',200),
					name:'remark'
				   },{
					xtype:'hiddenfield',
					name:'deptName'										//部门名称
					},{
					xtype:'hiddenfield',
					name:'cityName'									//城市名称
					},{
					fieldLabel:i18n('i18n.ScatterCustManagerView.startDept'),
					name:'leaDeptId',
					hidden:true,
					disabled:true
				   },{
					fieldLabel:i18n('i18n.ScatterCustManagerView.arriveDept'),
					name:'arrDeptId',
					hidden:true,
					disabled:true
				   }
			      ]
		}];
	},
	//Enter键按下时焦点自动移到下个控件
	enterKeyEvent:function(field,event){
		if(event.getKey() == Ext.EventObject.ENTER){
			if(field.next() == null){
				field = Ext.getCmp('recentcoop_id');
				field.focus();
			}else{
				while(field.next().readOnly || field.next().disabled){
					field = field.next();
				}
				field.next().focus();
			}
		}
	},
	//change事件
	changeEvent : function(field, newValue) {
		var me = this;
		if (Ext.isEmpty(newValue) && 'dpcombobox' == field.getXType()) {
			field.setValue(null);
		}
	},
	//失焦事件,TODO　待优化
	blurValidateEvent:function(field,validatefn){
		// 增加失焦事件 散客名称自动填充到联系人名称上
		var value = field.getValue();
		if('view' != me.viewStatus && !Ext.isEmpty(value)){
			var validateResult = validatefn(value);
			if(!validateResult.success){
				MessageUtil.showMessage(validateResult.message);
			}
		}
	
	},
	//验证商业登记号
	validateHKTaxInvalid:function(field){
		var me = this;
		var taxNum = field.getValue();
		var validateSuccessFn = function(result){
			//不存在 "exist" 为true
			if(result.exist){
				RealTimeVerifyResult.success[0] = true;
				RealTimeVerifyResult.message[0] = '';
			}else{
				RealTimeVerifyResult.success[0] = false;
				RealTimeVerifyResult.message[0] = i18n('i18n.MemberCustEditView.m_taxIsExsit');
				MessageUtil.showMessage(RealTimeVerifyResult.message[0]);
				return;
			}
//			RealTimeVerifyResult.success[0] = true;
		};
		var validateFailFn = function(result){
			if(DpUtil.isEmpty(result.message)){
				result.message = i18n('i18n.MemberCustEditView.anExceptionBackgroundAppearsWar');
				RealTimeVerifyResult.success[0] = false;
				RealTimeVerifyResult.message[0] = result.message;
			}
			RealTimeVerifyResult.success[0] = false;
			RealTimeVerifyResult.message[0] = result.message;
			MessageUtil.showMessage(result.message);
			return;
		};
		var params = {};
		params.taxNum = taxNum;
		memberCustControl.validateTaxNumberIsExist(params,validateSuccessFn,validateFailFn);
	}
});
/**
 * 散客账号信息Panel
 */
Ext.define('ScatterCustAccountPanel',{
	extend:'BasicVboxPanel',
	scatterCustData:null,												//散客数据层
	viewStatus:null,													//散客编辑 操作状态 'add' update view
	parent:null,
	initComponent : function() {
		var me = this;
		me.items = me.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [
		{
			xtype:'toppanel',
			items:[
			{
				xtype:'titlepanel',
				flex:1,
				items:[{
					xtype:'displayfield',
					value:i18n('i18n.ScatterCustManagerView.custNumberInfo')
				}]
			}
			,{
				xtype:'btnpanel',
				items:[{
					xtype:'button',
					text:i18n('i18n.PotentialCustManagerView.add'),
					name:'addccount_id',
					disabled:!isPermission('/customer/accountRecognizedABtn.action') || 'view' == me.viewStatus?true:false,
					scope:me,
					handler:me.showCreateAccountWin
				},{
					xtype:'button',
					text:i18n('i18n.PotentialCustManagerView.update'),
					disabled:!isPermission('/customer/accountRecognizedABtn.action') || 'view' == me.viewStatus?true:false,
					scope:me,
					handler:me.showUpdateAccountWin
				},{
					xtype:'button',
					text:i18n('i18n.PotentialCustManagerView.delete'),
					disabled:!isPermission('/customer/accountRecognizedABtn.action') || 'view' == me.viewStatus?true:false,
					scope:me,
					handler:me.deleteAccount
				}]
			}]
		}
		,{
			xtype : 'popupgridpanel',
			flex:1,
			store:me.scatterCustData.getScatterCustAccountStore(),
			selModel : Ext.create('Ext.selection.CheckboxModel'),
			columns:[{xtype: 'rownumberer',text:i18n('i18n.PotentialCustManagerView.potentialxuhao'),width:40},
			         {text:i18n('i18n.MemberCustEditView.openBank'),dataIndex:'bank'},
			         {text:i18n('i18n.MemberCustEditView.openName'),dataIndex:'accountName'},
			         {text:i18n('i18n.MemberCustEditView.accountNo'),dataIndex:'bankAccount',width:150},
			         {text:i18n('i18n.MemberCustEditView.accountPurpose'),dataIndex:'accountUse'
			        	 ,renderer:function(value){
			        	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.ACCOUNT_USE);
			         }
			         },
			         {text:i18n('i18n.MemberCustEditView.accountContactName'),dataIndex:'financeLinkmanName'},
			         {text:i18n('i18n.MemberCustEditView.contactPhone'),dataIndex:'linkmanMobile'},
			         {text:i18n('i18n.MemberCustEditView.contactTel'),dataIndex:'linkmanPhone'},
			         {text:i18n('i18n.MemberCustEditView.isAcquiesceAccount'),dataIndex:'isDefaultAccount',renderer:DpUtil.changeBooleanToDescrip},
			         {text:i18n('i18n.MemberCustEditView.CRAccount'),dataIndex:'relation'},
			         {text:i18n('i18n.MemberCustEditView.accountProvince'),dataIndex:'bankProvinceName',hidden:true},
			         {text:i18n('i18n.MemberCustEditView.accountCity'),dataIndex:'bankCityName',hidden:true},
			         {text:i18n('i18n.MemberCustEditView.accountProvinceid'),dataIndex:'bankProvinceId',hidden:true},
			         {text:i18n('i18n.MemberCustEditView.accountCityid'),dataIndex:'bankCityId',hidden:true}],
			         listeners:{
						   scope:me,
						   itemdblclick : function(grid,record){
						   		me.getAccountWin(me.scatterCustData,record,'view');
						   }
						}
		}];
	},
	//新增账号信息
	showCreateAccountWin:function(){
		var me = this;
		var newAccountRecord = scatterAccountAdd(me.parent.basicScatterCustInfo.getForm(),me.parent);
		newAccountRecord.set('id',ScatterCustAccountObj.getSequenceAccount());
		me.getAccountWin(me.memberData,newAccountRecord,'new');
	},
	//修改账号信息
	showUpdateAccountWin:function(){
		var me = this;
		var grid = me.down('grid');
		var selectModel = grid.getSelectionModel();
		var selections = selectModel.getSelection();
		if(selections.length != 1){
			MessageUtil.showMessage(i18n('i18n.MemberCustEditView.pleaseOneRecordToUpdate'));
		}else{
			var selected = selections[0];
			me.getAccountWin(me.memberData,selected,'update');
		}
	},
	//获得账号窗体
	getAccountWin:function(memberData,record,viewStatus){
		Ext.create('ScatterCustAccountEditWin',{'accountRecord':record,
											'sourcesChannel':'SCATTERCUST',
										   'viewStatus':viewStatus}).show();
	},
	//删除账号信息
	deleteAccount:function(){
		var me = this;
		var grid = me.down('grid');
		var selectModel = grid.getSelectionModel();
		var selections = selectModel.getSelection();
		if(selections.length != 1){
			MessageUtil.showMessage(i18n('i18n.MemberCustEditView.pleaseOneRecordToDelete'));
		}else{
			var selected = selections[0];
			  MessageUtil.showQuestionMes(i18n('i18n.PotentialCustManagerView.confirm_message'),function(btn){
			        	   if(btn == 'yes'){
			        	   me.scatterCustData.getScatterCustAccountStore().remove(selected);
		        	   }
		       });
		}
	},
	//搜集账号信息用于会员信息的提交,返回的类型对应后台的List<Account>
	collectMemberAccountsForSubmit:function(){
		var me = this;
		var accountArray = new Array();
		me.memberData.getAccountStore().each(function(record){
			accountArray.push(record.data);
		});
		return accountArray;
	},
	//校验账号信息
	validateAccount:function(){
		var me = this;
		var success = true;
		var message = '';
		var accountStore = me.memberData.getAccountStore();
		if(accountStore.getCount() != 0){
			var records = accountStore.getRange();
			for(var i = 0; i < records.length && success; i++){
				if(!records[i].isValid()){
					success = false;
					message = i18n('i18n.MemberCustEditView.accountInformationFillIncompleteWar');
					return {'success':success,'message':message};
				}
			}
		}
		return {'success':success,'message':message};
	},
	//搜集账号所有修改的信息
	collectAllAcountAlterData:function(alterContactList,alterDeleteList,alterAddAccountList){
		var me = this;
		//所有账号修改的record
		var updatedAccountRecords = me.memberData.getAccountStore().getUpdatedRecords();
		
		if(updatedAccountRecords.length > 0){
			var fieldsArray = updatedAccountRecords[0].fields.items;
			for(var j = 0; j < updatedAccountRecords.length; j++){
				for(var i = 0; i < fieldsArray.length;i++){
					//字段名
					var fieldName = fieldsArray[i].name;
					
					if(updatedAccountRecords[j].isModified(fieldName)){
						var basicApproveData = me.getEmptyAccountApproveData(updatedAccountRecords[j]);
						basicApproveData.set('fieldName',fieldName);
						basicApproveData.set('newValue',updatedAccountRecords[j].get(fieldName));
						alterContactList.push(basicApproveData.data);
					}
				}
			}
		}
		
		//搜集账号修改时删除的数据
		var deleteAccountRecords = me.memberData.getAccountStore().getRemovedRecords();
		for(var i = 0; i < deleteAccountRecords.length; i++){
			alterDeleteList.push(me.getEmptyAccountApproveData(deleteAccountRecords[i]).data);
		}
		
		//搜集新增的账号数据
		var addAccountRecords = me.memberData.getAccountStore().getNewRecords();
		for(var i = 0; i < addAccountRecords.length; i++){
			alterAddAccountList.push(addAccountRecords[i].data);
		}
	},
	//获取空的账号修改的approveData
	getEmptyAccountApproveData:function(accountRecord){
		var me = this;
		var basicApproveData = Ext.create('ApproveDataModel');
		basicApproveData.set('className','Account');
		basicApproveData.set('classId',accountRecord.get('id'));
		return basicApproveData;
	}
});
/**
*散客业务信息
*/
Ext.define('ScatterBusinessForm',{
	extend:'BasicFormPanel',
	scatterCustData:null,
	items:null,
	height:127,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'fieldset',
			title:i18n('i18n.ScatterCustManagerView.scatterBusinessInfo'),
			layout:{
				type:'table',
				columns:3
			},
			defaults:{
				labelWidth:70,
				width:210,
				enableKeyEvents:true,
				maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',200),
				maxLength:200,
				listeners:{
					scope:me,
					keypress:me.enterKeyEvent,
					change:me.changeEvent
				}
			},
			items:[{
				fieldLabel:i18n('i18n.PotentialCustEditView.companyName'),
				xtype:'dpcombo',
				store:me.scatterCustData.getCoopertationCompanyStore(),
				queryMode:'local',
				labelWidth:100,
	            forceSelection:true,
				displayField:'codeDesc',
				valueField:'code',
				name:'recentcoop',
				id:'recentcoop_id'
			},{
				fieldLabel:i18n('i18n.PotentialCustManagerView.coopIntention'),
				xtype:'dpcombo',
				store:me.scatterCustData.getCoopertationIntensionStore(),
				queryMode:'local',
	            forceSelection:true,
				displayField:'codeDesc',
				valueField:'code',
				name:'coopIntention'
			},{
				fieldLabel:i18n('i18n.PotentialCustManagerView.goodsPotential'),
				xtype:'dpcombo',
				store:me.scatterCustData.getGoodsPotentialStore(),
				queryMode:'local',
	            forceSelection:true,
				displayField:'codeDesc',
				valueField:'code',
				name:'volumePotential'
			},{
				fieldLabel:i18n('i18n.member.potenSource'),
				name:'potenSource',
				readOnly:true,
				xtype:'dpcombobox',
				queryMode:'local',
				forceSelection:true,
				readOnly:true,
//			   hidden:operateType!='VIEW',
				displayField:'codeDesc',
				valueField:'code',
				store:me.scatterCustData.getCustSourceStore()
			},
			{
				fieldLabel:'走货情况',
//				height:60,
				colspan:1,
				width:210,
				//emptyText:'最近合作物流公司',
				xtype:'textfield',
				name:'recentGoods'
			},
			{
				fieldLabel:i18n('i18n.ScatterCustManagerView.businessType')+DpUtil.getSpecialStr('redSpan'),
				name : 'businessType',
				colspan:1,
				width:210,
				xtype:'dpcombobox',
				allowBlank:false,
				blankText:i18n('i18n.ScatterCustEditView.businessTypeNull'),
				queryMode:'local',
				forceSelection:true,
				editable:false,
				displayField:'codeDesc',
				valueField:'code',
				store:me.scatterCustData.getBusinessTypeStore()
			}
			,{
				fieldLabel:i18n('i18n.PotentialCustEditView.custNeed'),
				xtype:'textareafield',
				colspan:3,
				height:40,
				width:630,
				name:'custNeed'
			}]
		}];
	},
	//change事件
	changeEvent : function(field, newValue) {
		var me = this;
		if (Ext.isEmpty(newValue) && 'dpcombobox' == field.getXType()) {
			field.setValue(null);
		}
	},
	//Enter键按下时焦点自动移到下个控件
	enterKeyEvent:function(field,event){
		if(event.getKey() == Ext.EventObject.ENTER && field.name != 'custNeed'){
			if(field.next() != null){
				while(field.next().readOnly || field.next().disabled){
					field = field.next();
				}
				field.next().focus();
			}
		}
	}
});


Ext.define('ScatterCustDetailWindow',{
	extend:'PopWindow',
	title:i18n('i18n.ScatterCustManagerView.custDetailInfo'),
	layout:'border',
	y:0,
	height:800,
	width:690,
	basicScatterCustInfo:null,     //客户基本信息
	scatterCustAccountPanel:null,	//散客账号信息
	scatterCustBusinessInfo:null,  //客户业务信息
	scatterCustData:scatterCustControl,
	scatterCustRecord:null,       //父界面传入散客record
	items:null,
	fbar:null,
	listeners:{
		close:function(){
			custLabels= [];
		}
	},
	initComponent:function(){
		var  me = this;
		me.basicScatterCustInfo = Ext.create('BasicScatterCustForm',{'viewStatus':'view','scatterCustData':me.scatterCustData,'parent':me});
		me.scatterCustAccountPanel = Ext.create('ScatterCustAccountPanel',{'viewStatus':'view','scatterCustData':me.scatterCustData,'parent':me});
		me.scatterCustBusinessInfo = Ext.create('ScatterBusinessForm',{'scatterCustData':me.scatterCustData});
		
		me.custLabelInfo = Ext.create('CustLabelInfoForm',{'status':me.viewStatus,'custType':'SC_CUSTOMER','custData':me.scatterCustRecord});
		//设置fbar
		me.fbar = me.getFbar();
		//设置items
		me.items = me.getItems();
		//请求后台加载交易信息
		me.scatterCustData.getTransactionStore().load({
			params:{
				'scatterCust.linkManName':me.scatterCustRecord.get('linkManName'),
				'scatterCust.linkManMobile':me.scatterCustRecord.get('linkManMobile'),
				'scatterCust.linkManPhone':me.scatterCustRecord.get('linkManPhone'),
				'scatterCust.id':me.scatterCustRecord.get('id')
		}});
		initBasicData(me.viewStatus,'SC_CUSTOMER',me.scatterCustRecord,null,top.User.deptId);
		this.callParent();
		var basicForm = this.down('basicformpanel');
		basicForm.getForm().findField('deptId').store.add(Ext.create('CurrentUserDeptListModel',{'deptId':this.scatterCustRecord.get('deptId'),'deptName':this.scatterCustRecord.get('deptName')}));
		//加载散客基本和业务信息
		basicForm.loadRecord(me.scatterCustRecord);
		//处理二级行业字段
		DpUtil.setSecondTradeValue(basicForm.getForm().findField('secondTrade'),me.scatterCustRecord);
		//锁定所有控件
		me.lockAllComponent();
	},
	getFbar:function(){
		var me = this;
		return [{xtype:'button',
				 text:i18n('i18n.ScatterCustManagerView.close'),
				 handler:function(){
					 me.close();
				 }}]
	},
	//锁定所有控件
	lockAllComponent:function(){
		var basicForm = this.down('basicformpanel');
		var mainForm = basicForm.getForm();
		mainForm.getFields().each(function(field){
			field.setReadOnly(true);
		});
	},
	getItems:function(){
		var me = this;
		//增加交易信息显示数据单位
		var rendertoVotes = function(value){return value+i18n('i18n.ScatterCustManagerView.rendertoVotes')};
		var rendertoVolume = function(value){return value+i18n('i18n.ScatterCustManagerView.rendertoVolume')};
		var rendertoAmount = function(value){return value+i18n('i18n.ScatterCustManagerView.rendertoAmount')};
		//初始化store
		return [{
			xtype:'basicformpanel',
			region:'north',
			height:600,
			layout:{
		        type:'vbox',
		        align:'stretch'
		    },
			items:
				[{
					xtype:'basicpanel',
					layout:'fit',
//					flex:1.7,
					items:[me.basicScatterCustInfo]
				},{
					xtype:'basicpanel',
					layout:'fit',
					height:125,
					autoScroll:true,
					items:[me.scatterCustAccountPanel]
				},{
					xtype:'basicpanel',
					layout:'fit',
//					flex:1.1,
					height:127,
					items:[me.scatterCustBusinessInfo]
				},{
					xtype:'basicformpanel',
//						region:'south',
					layout:'fit',
//						height:60,
					items:[me.custLabelInfo]
				}]
		 },
		 //-------------------------------------------------------------------------------------//
		 {
			 xtype:'basicvboxpanel',
			 region:'center',
			 items:[{
					xtype:'titlepanel',   
					height:23,	
					items:[{
							xtype:'displayfield',
							value:i18n('i18n.ScatterCustManagerView.tradeInfo')
						}]
					},{
						 xtype:'popupgridpanel',
						 flex:1,
						 store:me.scatterCustData.getTransactionStore(),
						 columns:[{text:i18n('i18n.ScatterCustManagerView.month'),dataIndex:'monthKind',renderer:me.renderGridDescrip,width:80},
						          {text:i18n('i18n.ScatterCustManagerView.lttBeginShipVotes'),dataIndex:'totalBillCount',renderer:rendertoVotes,width:82},
						          {text:i18n('i18n.ScatterCustManagerView.lttBeginArrivalVotes'),dataIndex:'recTotalBillCount',renderer:rendertoVotes,width:82},
						          {text:i18n('i18n.ScatterCustManagerView.lttBeginShipWeight'),dataIndex:'totalWeight',renderer:rendertoVolume,width:82},
						          {text:i18n('i18n.ScatterCustManagerView.lttBeginArrivalWeight'),dataIndex:'recTotalWeight',renderer:rendertoVolume,width:82},
						          {text:i18n('i18n.ScatterCustManagerView.lttBeginShipAmount'),dataIndex:'totalAmount',renderer:rendertoAmount,width:82},
						          {text:i18n('i18n.ScatterCustManagerView.lttBeginArrivalAmount'),dataIndex:'recTotalAmount',renderer:rendertoAmount,width:82},
						          {text:i18n('i18n.ScatterCustManagerView.exBeginShipVotes'),dataIndex:'exTotalBillCount',renderer:rendertoVotes,width:82},
						          {text:i18n('i18n.ScatterCustManagerView.exBeginArrivalVotes'),dataIndex:'exRecTotalBillCount',renderer:rendertoVotes,width:82},
						          {text:i18n('i18n.ScatterCustManagerView.exBeginShipWeight'),dataIndex:'exTotalWeight',renderer:rendertoVolume,width:82},
						          {text:i18n('i18n.ScatterCustManagerView.exBeginArrivalWeight'),dataIndex:'exRecTotalWeight',renderer:rendertoVolume,width:82},
						          {text:i18n('i18n.ScatterCustManagerView.exBeginShipAmount'),dataIndex:'exTotalAmount',renderer:rendertoAmount,width:82},
						          {text:i18n('i18n.ScatterCustManagerView.exBeginArrivalAmount'),dataIndex:'exRecTotalAmount',renderer:rendertoAmount,width:82}]
			        }
//			        ,
//			        {
//						xtype:'titlepanel',   
//						height:23,	
//						items:[{
//								xtype:'displayfield',
//								value:i18n('i18n.ScatterCustManagerView.visitInfo')
//							}]
//						},{
//			    		   xtype:'popupgridpanel',
//			    		   flex:1,
//			    		   store:null,
//			    		   columns:[{text:i18n('i18n.ScatterCustManagerView.visitDate'),dataIndex:''},
//			    		            {text:i18n('i18n.ScatterCustManagerView.visitTime'),dataIndex:''},
//			    		            {text:i18n('i18n.ScatterCustManagerView.visitor'),dataIndex:''},
//			    		            {text:i18n('i18n.ScatterCustManagerView.visitContext'),dataIndex:''}]
//			    	   }
				  ]
				}
		 ];
	},
		 //-------------------------------------------------------------------------------------//
//		 {
//			 xtype:'basicformpanel',
//			 region:'center',
//			 layout:{
//			        type:'vbox',
//			        align:'stretch'
//			    },
//			 items:[
//			       {
//			    	   xtype:'fieldset',
//			    	   title:i18n('i18n.ScatterCustManagerView.tradeInfo'),
//			    	   layout:'fit',
//			    	   flex:1.2,
//				       items:[ {
//							 xtype:'popupgridpanel',
//							 border:false,
//							 store:transactionStore,
//							 columns:[{text: '      ',dataIndex:'monthKind',renderer:me.renderGridDescrip,width:80},
//							          {text:i18n('i18n.ScatterCustManagerView.beginShipVotes'),dataIndex:'totalBillCount',width:82},
//							          {text:i18n('i18n.ScatterCustManagerView.beginArrivalVotes'),dataIndex:'recTotalBillCount',width:82},
//							          {text:i18n('i18n.ScatterCustManagerView.startVolume'),dataIndex:'totalWeight',width:82},
//							          {text:i18n('i18n.ScatterCustManagerView.beginArrivalWeight'),dataIndex:'recTotalWeight',width:82},
//							          {text:i18n('i18n.ScatterCustManagerView.beginShipAmount'),dataIndex:'totalAmount',width:82},
//							          {text:i18n('i18n.ScatterCustManagerView.beginArrivalAmount'),dataIndex:'recTotalAmount',width:82}]
//				        }]
//			       },{
//			    	   xtype:'fieldset',
//			    	   title:i18n('i18n.ScatterCustManagerView.visitInfo'),
//			    	   layout:'fit',
//			    	   flex:1,
//			    	   items:[{
//			    		   xtype:'popupgridpanel',
//			    		   border:false,
//			    		   store:null,
//			    		   columns:[{text:i18n('i18n.ScatterCustManagerView.visitDate'),dataIndex:''},
//			    		            {text:i18n('i18n.ScatterCustManagerView.visitTime'),dataIndex:''},
//			    		            {text:i18n('i18n.ScatterCustManagerView.visitor'),dataIndex:''},
//			    		            {text:i18n('i18n.ScatterCustManagerView.visitContext'),dataIndex:''}]
//			    	   }]
//			       }
//			 ]
//		 }
//		 ];
//	},
	//-------------------------------------------------------------------------------------//	 
	renderGridDescrip:function(rowId){
		if(rowId == '0'){
			return i18n('i18n.ScatterCustManagerView.preMonth');
		}else if(rowId == '1'){
			return i18n('i18n.ScatterCustManagerView.currentMonth');
		}
		return '';
	}
});

//Ext.onReady(function(){
//	new ScatterCustEditWindow({'scatterCustData':scatterCustControl,
//		                       'scatterCustRecord':Ext.create('ScatterCustModel',{
//		                    	   'custName':'李盛',
//		                    	   'linkManName':'李盛',
//		                    	   'custProperty':'LEAVE_CUSTOMER',
//		                    	   'linkManMobile':'13966987781'
//		                       }),
//		                       'viewStatus':'update'}).show();

//	new ScatterCustDetailWindow({'scatterCustData':scatterCustControl,
//								 'scatterCustRecord':Ext.create('ScatterCustModel',{
//			                    	   'custName':'李盛',
//			                    	   'linkManName':'李盛',
//			                    	   'custProperty':'LEAVE_CUSTOMER',
//			                    	   'linkManPhone':'13966987485'
//			                       })}).show();
//});
