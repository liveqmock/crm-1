var recordSearchControl = (CONFIG.get('TEST')) ? Ext
		.create('RecordSearchDataTest') : Ext.create('RecordSearchData');

		/**
		 * .
		 * <p>
		 * 积分规规则EditPanel</br>
		 * </p>
		 * 
		 * @author 潘光均
		 * @时间 2012-03-19
		 */

		Ext.define('RecordTypePanel',{
			extend:'BasicFormPanel',  
			checkEnable:null,
			id:'recordTypePanel',
			initComponent:function(){
				this.items = this.getItems();
				Ext.data.StoreManager.lookup('channelType').on('load',function(store,operation,obj){
					for ( var i = 0; i < store.data.length; i++) {
						if (store.getAt(i).get('code')=='ALL') {
							store.removeAt(i);
						}
					}
//					var record = {'code':'ALL','codeDesc':i18n('i18n.RecordEditView.all')};
//					store.remove(record);
//					store.removeAt(6);
				});
				this.callParent();
			},
			getItems:function(){
				var me = this;
				return [{
					xtype:'basicfiledset', 
					title:i18n('i18n.IntegralRuleEdit.ruleType'),
					layout:{
						type:'table',
						columns:3
					},
					defaultType:'textfield',
					items:[{
						fieldLabel:i18n('i18n.IntegralRuleEdit.product'),
						width:50,
						id:'recordRuleEidtProduct',
						labelWidth:30,
						name:'rule',
						xtype:'radio',
						disabled:me.checkEnable,
						listeners:{
							change:function(){
									if (this.getValue()) {
										Ext.getCmp('recordRuleEditProductType').setReadOnly(false);
										Ext.getCmp('recordRuleEditChannelType').setReadOnly(true);
										Ext.getCmp('startStationAreaSelect').setDisabled(true);
										Ext.getCmp('endStationAreaSelect').setDisabled(true);
										Ext.getCmp('recordRuleEditChannelType').setValue(null);
										Ext.getCmp('startStationAreaSelect').setValue(null);
										Ext.getCmp('endStationAreaSelect').setValue(null);
									}
						}
					}
					},{
						xtype:'combo',
						labelWidth:60,
						width:220,
						valueField:'code',
						displayField:'codeDesc',
						id:'recordRuleEditProductType',
						store:DpUtil.createStore('productType',null,['code','codeDesc'],DataDictionary.TRANS_TYPE),
						fieldLabel:i18n('i18n.IntegralRuleEdit.productType'),
						readOnly:true,
						cls:me.checkEnable?'readonly':''
					},{
						xtype:'middlebuttonpanel'
					},{
						fieldLabel:i18n('i18n.IntegralRuleEdit.channel'),
						width:50,
						name:'rule',
						id:'recordRuleEidtChannel',
						labelWidth:30,
						xtype:'radio',
						disabled:me.checkEnable,
						listeners:{
							change:function(){
								if (this.getValue()) {
									Ext.getCmp('recordRuleEditProductType').setValue(null);
									Ext.getCmp('recordRuleEditChannelType').setReadOnly(false);
									Ext.getCmp('recordRuleEditProductType').setReadOnly(true);
									Ext.getCmp('startStationAreaSelect').setDisabled(true);
									Ext.getCmp('endStationAreaSelect').setDisabled(true);
									Ext.getCmp('startStationAreaSelect').setValue(null);
									Ext.getCmp('endStationAreaSelect').setValue(null);
								}
							}
						}
					},{
						xtype:'combo',
						labelWidth:60,
						width:220,
						valueField:'code',
						displayField:'codeDesc',
						id:'recordRuleEditChannelType',
						fieldLabel:i18n('i18n.IntegralRuleEditchannelType'),
						store:DpUtil.createStore('channelType',null,['code','codeDesc'],DataDictionary.ORDER_SOURCE),
						readOnly:true,
						cls:me.checkEnable?'readonly':''
					},{
						xtype:'middlebuttonpanel'
					},{
						fieldLabel:i18n('i18n.IntegralRuleEdit.way'),
						id:'recordRuleEidtWay',
						labelWidth:30,
						width:50,
						name:'rule',
						xtype:'radio',
						disabled:me.checkEnable,
						listeners:{
							change:function(){
								if (this.getValue()) {
									Ext.getCmp('recordRuleEditChannelType').setReadOnly(true);
									Ext.getCmp('recordRuleEditProductType').setReadOnly(true);
									Ext.getCmp('recordRuleEditChannelType').setValue(null);
									Ext.getCmp('recordRuleEditProductType').setValue(null);
									Ext.getCmp('startStationAreaSelect').setDisabled(false);
									Ext.getCmp('endStationAreaSelect').setDisabled(false);
								}
							}
					}
					},{
						xtype:'basicformpanel',
						id:'startStationAreaSelect_formId',
						margin:'0 0 5 0',
						items:[new AreaAddressCombox({'id':'startStationAreaSelect',
							'tabPanel':['provincePanel','cityPanel'],
//							selectedValue:me.addressData.leadept,
							listeners:{
								change:function(combobox,newValue,oldValue){
									if(!DpUtil.isEmpty(newValue)){
										var array = combobox.getRawValue().split('-');	
										if(array.length == 2){
											Ext.getCmp('leadeptName_id').setValue(array[1]);
										}
									}
								}
							},
							'disabled':true,fieldLabel:i18n('i18n.IntegralRuleEdit.startCity'),labelWidth:60})]
					},{
						xtype:'basicformpanel',
						id:'endStationAreaSelect_formId',
						margin:'0 0 5 0',
						items:[new AreaAddressCombox({'id':'endStationAreaSelect',
							'tabPanel':['provincePanel','cityPanel'],
//							selectedValue:me.addressData.arrdept,
							listeners:{
								change:function(combobox,newValue,oldValue){
									if(!DpUtil.isEmpty(newValue)){
										var array = combobox.getRawValue().split('-');	
										if(array.length == 2){
											Ext.getCmp('arrdeptName_id').setValue(array[1]);
										}
									}
								}
							},
							'disabled':true,fieldLabel:i18n('i18n.IntegralRuleEdit.endCity'),labelWidth:60})]
					},{
					xtype:'textfield',
					id:'leadeptName_id',
					hidden:true,
					name:'leadeptName'
					},{
					xtype:'textfield',
					id:'arrdeptName_id',
					hidden:true,
					name:'arrdeptName'
					}
				]		
				}];
			}
		});


		/**
		 * .
		 * <p>
		 * 规则明细EditPanel</br>
		 * </p>
		 * 
		 * @author 潘光均
		 * @时间 2012-03-19
		 */

		Ext.define('RuleDetailEditPanel',{
			extend:'BasicFormPanel',  
			detailEditable:null,
			isUpdate:null,
			id:'ruleDetailEditPanel',
			width:514,
			initComponent:function(){
				this.items = this.getItems();
				this.callParent();
			},
			getItems:function(){
				var me = this;
				return [{
					xtype:'basicfiledset', 
//					id:'ruleDetailEditPanel',
					title:i18n('i18n.IntegralRuleEdit.ruleType'),
					layout:{
						type:'table',
						columns:1
					},
					defaultType:'textfield',
					defaults:{
						labelWidth:80,
						width:240
					},
					items:[{
						fieldLabel:'id',
						name:CONFIGNAME.get('id'),
						hidden:true
					},{
						fieldLabel:i18n('i18n.Integral.recordRate'),
						name:CONFIGNAME.get('rulRate'),
						xtype:'numberfield',
						minValue:0,
						maxLength:20,
						maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20),
						allowBlank:false,
						hideTrigger: true,
				        mouseWheelEnabled: false,
						readOnly:me.detailEditable,
						cls:me.detailEditable?'readonly':''
					},{
						fieldLabel:i18n('i18n.IntegralRuleEdit.integralValidateDate'),
						name:CONFIGNAME.get('rulValideTime'),
						xtype:'datefield',
						allowBlank:false,
						format:'Y-m-d',
						readOnly:me.detailEditable,
						cls:me.detailEditable?'readonly':''
					},{
						fieldLabel:i18n('i18n.IntegralRuleEdit.integralEndValidateDate'),
						name:CONFIGNAME.get('rulValideInvalideTime'),
						id:CONFIGNAME.get('rulValideInvalideTime'),
						xtype:'datefield',
						allowBlank:false,
						format:'Y-m-d',
						readOnly:me.detailEditable,
						cls:me.detailEditable?'readonly':''
					},{
						fieldLabel:i18n('i18n.Integral.recordDesc'),
						name:CONFIGNAME.get('rulRecordDesc'),
						xtype:'textarea',
						maxLength:50,
						maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20),
						allowBlank:false,
						width:400,
						readOnly:me.detailEditable,
						margin:'0 0 20 0'
					}]		
				}];
			}
		});
		
		//弹出框
		 Ext.define('RecordEditView',{
			extend:'PopWindow',
			id:'recordEditView',
			title:i18n('i18n.IntegralRuleEdit.ruleType'),
			height:400,
			width:550,
			//form提交是否是更改的提交
			isUpdate:null,
			//规则类型是否可以编辑
			readOnly:null,
			//checkBox是否可用
			checkEnable:null,
			//规则详情是否可以编辑
			detailEditable:null,
			//规则按钮是否可见
			ruleEditBtnPanel :null,
			//规则详情是否可以编辑
			ruleDetailEditPanel:null,
			recordTypePanel:null,
			layout: {
				type:'vbox',
				align:'strecth'
			},
			initComponent:function(){
				var me = this;
				me.items = me.getItems();  
				this.fbar = this.getFbar();
				this.callParent();
			},
			getFbar:function(){
				var me = this;
				return [{ 
					xtype:'button',
					text:i18n('i18n.PotentialCustEditView.save'),
					hidden:me.readOnly,
					handler:function(){
						me.saveRule();
					}
				},{
					xtype:'button',
					text:i18n('i18n.PotentialCustEditView.cancel'),
					hidden:me.readOnly,
					scope:me,
					handler:function(){	
						MessageUtil.showQuestionMes(i18n('i18n.IntegralRuleEdit.confirmCancel'),function(e){
							if (e=='yes') {
								me.close();
							}
						});
					}
				},{
					xtype:'button',
					text:i18n('i18n.IntegralRuleEdit.return'),
				//	scope:me,
					hidden:!me.readOnly,
					handler:function(){
						me.close();
					}
				}]
			},
			saveRule:function(){
				var form=Ext.getCmp('ruleDetailEditPanel').getForm();
				if (!form.isValid()) {
					MessageUtil.showErrorMes(i18n('i18n.RecordEditView.m_checkAllRight'));
					return;
				}
				if (!Ext.isEmpty(form.findField(CONFIGNAME.get('rulRate')))&&
					!Ext.isEmpty(form.findField(CONFIGNAME.get('rulValideTime')))&&
					!Ext.isEmpty(form.findField(CONFIGNAME.get('rulRecordDesc')))&&
					!Ext.isEmpty(form.findField(CONFIGNAME.get('rulValideInvalideTime')))) {
					var me=this;
					var startDate =me.ruleDetailEditPanel.getForm().findField(
								CONFIGNAME.get('rulValideTime')).getValue();
					startDate.setDate(startDate.getDate()+1);
					var endStartDate=me.ruleDetailEditPanel.getForm().findField(
								CONFIGNAME.get('rulValideInvalideTime')).getValue();
	
					endStartDate.setDate(endStartDate.getDate()+1);
					if (startDate<(new Date()).getTime()&&!me.isUpdate) {
						MessageUtil.showErrorMes(
								i18n('i18n.IntegralRuleEdit.validateInvalid'));
						return;
					}
					if (endStartDate.getTime()<(new Date()).getTime()&&!me.isUpdate) {
						MessageUtil.showErrorMes(
								i18n('i18n.IntegralRuleEdit.invalidateInvalid'));
						return;
					}
					if (startDate>endStartDate) {
						MessageUtil.showErrorMes(
								i18n('i18n.IntegralRuleEdit.startDateCannotBiggerThanEnd'));
						return;
					}
					
					var record = new IntegralRuleModel();
					//保存的实体参数
					var param=null;
					//获取产品规则类型的值
					var productType=Ext.getCmp('recordRuleEditProductType').getValue();
					//获取渠道规则类型的值
					var channelType=Ext.getCmp('recordRuleEditChannelType').getValue();
					//获取路线始发站的值,获得城市的id
					if(me.isUpdate){
						var startStationAreaSelect=Ext.getCmp('startStationAreaSelect').getValue();
					}else if(!(Ext.isEmpty(Ext.getCmp('startStationAreaSelect').getValue()))){
						var startStationAreaSelect=Ext.getCmp('startStationAreaSelect').getValue().split('-')[1];
					}
					//获取路线始发站的name
					var leadeptName=Ext.getCmp('leadeptName_id').getValue();
					//获取路线到达站的值,获得城市的id
					if(me.isUpdate){
						var endStationAreaSelect=Ext.getCmp('endStationAreaSelect').getValue();
					}else if(!(Ext.isEmpty(Ext.getCmp('endStationAreaSelect').getValue()))){
						var endStationAreaSelect=Ext.getCmp('endStationAreaSelect').getValue().split('-')[1];
					}
					//获取路线始发站的name
					var arrdeptName=Ext.getCmp('arrdeptName_id').getValue();
					//record设值
					Ext.getCmp('ruleDetailEditPanel').getForm().updateRecord(record);
					
					var IsValueNull = function(value){
						return value==null||value==undefined||value=='';
					};
					
					//如果规则类型的值是空则不能提交
					if (IsValueNull(productType)&&IsValueNull(channelType)&&
							(IsValueNull(startStationAreaSelect)||IsValueNull(endStationAreaSelect))) {
						MessageUtil.showErrorMes(i18n('i18n.IntegralRuleEdit.ruleTypeNeed'));
						return;
					}
					//如果是产品规则
					if (!IsValueNull(productType)) {
						param={'productRule':{'id':record.get(CONFIGNAME.get('id')),
																'number':Ext.getCmp('editRuleTextFiled').getValue(),
																'product':productType,
																'fraction':record.get(CONFIGNAME.get('rulRate')),
																'pointdesc':record.get(CONFIGNAME.get('rulRecordDesc')),
																'pointbegintime':record.get(CONFIGNAME.get('rulValideTime')),
																'pointendtime':record.get(CONFIGNAME.get('rulValideInvalideTime'))
											}
									};
						//如果是渠道规则
					}else if(!IsValueNull(channelType)){
						param={'channelRule':{'id':record.get(CONFIGNAME.get('id')),
																'number':Ext.getCmp('editRuleTextFiled').getValue(),
																'channeltype':channelType,
																'fraction':record.get(CONFIGNAME.get('rulRate')),
																'pointdesc':record.get(CONFIGNAME.get('rulRecordDesc')),
																'pointbegintime':record.get(CONFIGNAME.get('rulValideTime')),
																'pointendtime':record.get(CONFIGNAME.get('rulValideInvalideTime'))
											}
									};
						//如果是路线规则
					}else{
						param={'wiringRule':{'id':record.get(CONFIGNAME.get('id')),
																'number':Ext.getCmp('editRuleTextFiled').getValue(),
																'leadept':startStationAreaSelect,
																'arrdept':endStationAreaSelect,
																'leadeptName':leadeptName,
																'arrdeptName':arrdeptName,
																'fraction':record.get(CONFIGNAME.get('rulRate')),
																'pointdesc':record.get(CONFIGNAME.get('rulRecordDesc')),
																'pointbegintime':record.get(CONFIGNAME.get('rulValideTime')),
																'pointendtime':record.get(CONFIGNAME.get('rulValideInvalideTime'))
											}
									};
					}
					var sucessFn=function(json){
						MessageUtil.showInfoMes(i18n('i18n.PotentialCustEditView.saveSuccess'));
						if (!IsValueNull(productType)) {
							Ext.getCmp('productRuleGridPanel').getStore().loadPage(1);
	//						Ext.getCmp('productRuleGridPanel').getStore().insert(1,param.productRule);
						}else if(!IsValueNull(channelType)){
							Ext.getCmp('channelRuleGridPanel').getStore().loadPage(1);
	//						Ext.getCmp('channelRuleGridPanel').getStore().insert(1,param.channelRule);
						}else{
							Ext.getCmp('wayRuleGridPanel').getStore().loadPage(1);
	//						Ext.getCmp('wayRuleGridPanel').getStore().insert(1,param.wiringRule);
						}
	//					Ext.getCmp('ruleDetailEditPanel').getForm().reset();
	//					Ext.getCmp('recordTypePanel').getForm().reset();
						me.close();
					};
					var filureFn=function(json){
						MessageUtil.showErrorMes(json.message);//i18n('i18n.IntegralRuleEdit.ruleType');
					};
				
						if (IsValueNull(record.get(CONFIGNAME.get('id')))) {
							recordSearchControl.createRecordRule(param,sucessFn,filureFn);
						}else{
							recordSearchControl.modifyRecordRule(param,sucessFn,filureFn);
						}
				}
			},
			getItems:function(){ 
				var me = this;
//				me.ruleEditBtnPanel = new RuleEditBtnPanel({'readOnly':me.readOnly,'parent':me});
				me.ruleDetailEditPanel = new RuleDetailEditPanel({'detailEditable':me.detailEditable,'isUpdate':me.isUpdate});
				me.recordTypePanel = new RecordTypePanel({'readOnly':me.readOnly,'checkEnable':me.checkEnable});
				return [
				{
					xtype:'textfield',
					width:210,
					height:21,
					labelWidth:90,
					name:CONFIGNAME.get('id'),
					readOnly:true,
//					allowBlank:false,
					id:'editRuleTextFiled',
					width:250,
					fieldLabel:i18n('i18n.IntegralRuleEdit.nbspNumber')
//					listeners:{
//						blur:function(){
//							var param = {'ruleId':this.getValue()};
////							me.blurFn(param);
//						}
//					}
				},
					me.recordTypePanel,
					me.ruleDetailEditPanel
//					,me.ruleEditBtnPanel
				];
			}
//			blurFn:function(param){
//				var failureFn=function(json){
//				};
//				var successFn=function(json){
//					if (json.ruleId!=null) {
//						DpUtil.showMessage(i18n('i18n.IntegralRuleEdit.numExist'));
//						Ext.getCmp('editRuleTextFiled').setValue(null);
//					}
//				};
//				if (Ext.getCmp('editRuleTextFiled')!=null&&Ext.getCmp('editRuleTextFiled')!=undefined
//						||Ext.getCmp('editRuleTextFiled')!='') {
//					recordSearchControl.getRecordRuleByNumber(param,successFn,failureFn);
//				}
//			}
		});

