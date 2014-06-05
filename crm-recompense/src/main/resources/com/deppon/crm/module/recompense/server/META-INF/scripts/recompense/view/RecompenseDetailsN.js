/**
 * @功能：理赔查看窗体
 */

RecompenseDetails = function() {
};
var recompenseData = (CONFIG.get("TEST")) ? new RecompenseDataTestN(): new RecompenseDataN(),
	printDatas = null;
Ext.define('RecompenseDetailsUI', {
	id:'recompenseDetailsUI',
	extend : 'PopWindow',
	closeAction:'hide',
	y:0,
	autoDestroy : true,
	closable : true,
	style:{
		'z-index':-1
	},
	autoScroll : true,
	recompenseInfo : null,
	dangerInfo : null,
	recompenseInfo : null,
	detailBtnPanel : null,
	title : i18n('i18n.recompense.recompenseDetaillInfo'),
	width : 785,
	height :785,
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	modal : true,
	initComponent : function() {
		var me = this;
		me.items = me.getItems();
		if(Ext.isEmpty(me.flag)){
			//设置关闭按钮
			me.fbar = me.getFbar();
			//理赔监控界面只用于显示，将操作按钮隐藏
			Ext.getCmp('awardItemAdd').hide();
			Ext.getCmp('awardItemUpdate').hide();
			Ext.getCmp('awardItemDelete').hide();
			Ext.getCmp('responsibleDeptAdd').hide();
			Ext.getCmp('responsibleDeptUpdate').hide();
			Ext.getCmp('responsibleDeptDelete').hide();
			Ext.getCmp('messageReminderAdd').hide();
			Ext.getCmp('messageReminderDelete').hide();
			Ext.getCmp('deptChargeAdd').hide();
			Ext.getCmp('deptChargeUpdate').hide();
			Ext.getCmp('deptChargeDelete').hide();
		}
		this.callParent();
	},
	listeners:{
		hide:function(){
			document.body.scrollLeft=0;
			document.body.scrollTop=0;
			document.getElementsByTagName("html")[0].style.overflowY="hidden";
			document.getElementsByTagName("html")[0].style.overflowX="hidden";
			viewport.doLayout();
		},
		beforehide:function(){
			IsReset = true;
			DpUtil.closeButton();
			//Ext.getCmp('buttonSearchPanel').searchRecompense();
			Ext.getCmp('pagingToolbar').doRefresh( );
			if(!Ext.isEmpty(Ext.getCmp('detailBtnPanel'))){
				Ext.getCmp('detailBtnPanel').destroy( );
			}
			if(!Ext.isEmpty(Ext.getCmp('recompHistoryAllPanel'))){
				Ext.getCmp('recompHistoryAllPanel').getForm().reset();
			}
		}, 
		beforeshow:function(th){
				var me= this;
				this.loadData();
				if(me.flag =='button'){//如果flag打上button的标记，才会显示按钮
					if(Ext.isEmpty(Ext.getCmp('detailBtnPanel'))){
						this.add(new DetailBtnPanel({'actionIds':me.actionIds,'recompenseId':me.record.id
							,'status':me.record.status,'recompenseMethod':me.record.recompenseMethod
							,'reportDept':me.record.reportDept,'deptId':me.record.deptId,'confirmDeptId':me.record.confirmAmountDept}));
					}
				}
				th.doLayout();
		}
	},
	/**
	 * <P>
	 * 根据理赔类型设置insurType中的Store值
	 * </p>
	 * 
	 * @author 张斌
	 * @date 2012-04-18
	 */
	changeInsurTypeStoreData:function(recompenseType){
		if(recompenseType=='unbilled'){
			   //三个combox的数据都修改
			 var dataInsurTypeUnbilled = new Array();
			   for(var i = 0;i<DataDictionary.DANGER_TYPE.length;i++){
				   if(DataDictionary.DANGER_TYPE[i].code !='FALSELY_CLAIM'){
					   dataInsurTypeUnbilled.push(DataDictionary.DANGER_TYPE[i]);
				   }
			   }
			   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().removeAll();
			   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().loadData(dataInsurTypeUnbilled);
			 }
		 if(recompenseType=='lost_goods'){
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
			   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().removeAll();
			   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().loadData(dataInsurType);
		 }
	  if(recompenseType=='abnormal'){
			var dataInsurType = new Array();
		   for(var i = 0;i<DataDictionary.DANGER_TYPE.length;i++){
			   if(DataDictionary.DANGER_TYPE[i].code =='PIECE_LOST'||DataDictionary.DANGER_TYPE[i].code =='TICKET_LOST'){
				  
			   }else{
				   dataInsurType.push(DataDictionary.DANGER_TYPE[i]);
			   }
		   }
		   //三个combox的数据都修改
		   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().removeAll();
		   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().loadData(dataInsurType);
		 }
	},
	getItems : function() {
		var me = this;
		me.dangerInfo = new DangerInfo();
		me.recompenseInfo = new DetailRecompenseInfo();
		me.recompenseDetailPanel = new RecompenseDetailPanel();
		if(me.flag =='button'){
			me.detailBtnPanel = new DetailBtnPanel({'actionIds':me.actionIds
				,'recompenseId':me.record.id,'status':me.record.status
				,'recompenseMethod':me.record.recompenseMethod,'reportDept':me.record.reportDept,
				'deptId':me.record.deptId,'confirmDeptId':me.record.confirmAmountDept});
			//me.loadData();
			me.setComponentReadOnly();
			return [ me.dangerInfo, me.recompenseInfo, me.recompenseDetailPanel,
					me.detailBtnPanel];
		}else{
			me.detailBtnPanel = {};
			//me.loadData();
			me.setComponentReadOnly();
			return [ me.dangerInfo, me.recompenseInfo, me.recompenseDetailPanel];
		}
		
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.recompense.close'),
			handler:function(){
				me.hide();
			}
			
		}];
	},
	loadData : function() {
		var me = this;
		Ext.getCmp('detailRecompenseInfo').getForm().reset();
		Ext.getCmp('recompenseDetailPanel').setActiveTab(1);
		var waybillModel = new WaybillModel(me.record.waybill);
		var recompenseApp = new RecompenseApp(me.record);
		//多赔金额设置
		var overPay = recompenseApp.get(CONFIGNAME.get('overpay'));
		var overPayModel = new OverPayModel(overPay);
		Ext.getCmp(CONFIGNAME.get('overpayAmount')).setValue(overPayModel.get(CONFIGNAME.get('overpayAmount')));

		me.changeInsurTypeStoreData(recompenseApp.get(CONFIGNAME.get('recompenseType')));
		// 设置model的理赔方式数据字典值
		var recompenseType = recompenseApp
				.get(CONFIGNAME.get('recompenseType'));
		recompenseApp.set(CONFIGNAME.get('recompenseType'), DpUtil
				.changeDictionaryCodeToDescrip(recompenseType,
						DataDictionary.RECOMPENSE_TYPE));
		// 设置model的理赔类型数据字典值
		var recompenseMethod = recompenseApp.get(CONFIGNAME
				.get('recompenseMethod'));
		recompenseApp.set(CONFIGNAME.get('recompenseMethod'), DpUtil
				.changeDictionaryCodeToDescrip(recompenseMethod,
						DataDictionary.RECOMPENSE_WAY));
		// 设置model的状态数据字典值
		var status = recompenseApp.get(CONFIGNAME.get('status'));
		recompenseApp.set(CONFIGNAME.get('status'), DpUtil
				.changeDictionaryCodeToDescrip(status,
						DataDictionary.RECOMPENSE_STATUS));
		// 设置model的出险类型数据字典值
		var insurType = recompenseApp.get(CONFIGNAME.get('insurType'));
		recompenseApp.set(CONFIGNAME.get('insurType'), DpUtil
				.changeDictionaryCodeToDescrip(insurType,
						DataDictionary.DANGER_TYPE));
		me.dangerInfo.loadRecord(waybillModel);
		me.dangerInfo.loadRecord(recompenseApp);
		

		// 设置出险信息中大区的默认值
		var comb = Ext.getCmp(CONFIGNAME.get('deptId'));
		comb.store.removeAll();
		comb.store.add({
			'id' : me.record.deptId,
			'deptName' : me.record.deptName
		});
		comb.setValue(comb.store.getAt(0).get('id'));
        //设置报案人
		
		//设置报案部门
		
		//索赔金额四舍五入
//		var recompenseAmount  = recompenseApp.get(CONFIGNAME.get('recompenseAmount'));
//		recompenseApp.set(CONFIGNAME.get('recompenseAmount'),recompenseAmount.toFixed(0));
		
		// 向理赔信息form中数据
		me.recompenseInfo.loadRecord(recompenseApp);
		
		//设置理赔信息中客户信息的值
		if(!Ext.isEmpty(recompenseApp.get(CONFIGNAME.get('customer')))){
			Ext.getCmp('showCustNumber').setValue(recompenseApp.get(CONFIGNAME.get('customer')).custNumber);
			Ext.getCmp('showCustName').setValue(recompenseApp.get(CONFIGNAME.get('customer')).custName);
			Ext.getCmp('showConCustomerLevel').setValue(DpUtil.changeDictionaryCodeToDescrip(recompenseApp.get(CONFIGNAME.get('customer')).degree,DataDictionary.MEMBER_GRADE));
		}
		//设置出现描述、理赔清单、货物托运清单、附件表格信息
		//出现信息
		
		var recalledCom = recompenseApp.get(CONFIGNAME.get('recalledCom'));
		var recalledComModel = new RecalledComModel(recalledCom);
		//加载金额信息
		Ext.getCmp('amountPanel').getForm().loadRecord(recompenseApp);
		if(!Ext.isEmpty(recompenseApp.get(CONFIGNAME.get('overpay')))){
			var overpayAmount = recompenseApp.get(CONFIGNAME.get('overpay')).overpayAmount;
			Ext.getCmp(CONFIGNAME.get('overpayAmount')).setValue(overpayAmount);
		}
		Ext.getCmp(CONFIGNAME.get('costExplain')).setValue(recompenseApp.get(CONFIGNAME.get('costExplain')));
		//入部门费用
		Ext.getCmp('deptChargeGird').getStore().removeAll();
		if(!Ext.isEmpty(recompenseApp.get(CONFIGNAME.get('deptChargeList')))){
			Ext.getCmp('deptChargeGird').getStore().loadData(recompenseApp.get(CONFIGNAME.get('deptChargeList')));
		}
		//责任部门
		Ext.getCmp('responsibleDeptGird').getStore().removeAll();
		if(!Ext.isEmpty(recompenseApp.get(CONFIGNAME.get('responsibleDeptList')))){
			Ext.getCmp('responsibleDeptGird').getStore().loadData(recompenseApp.get(CONFIGNAME.get('responsibleDeptList')));
		}
		//奖罚明细
		Ext.getCmp('awardItemGird').getStore().removeAll();
		if(!Ext.isEmpty(recompenseApp.get(CONFIGNAME.get('awardItemList')))){
			Ext.getCmp('awardItemGird').getStore().loadData(recompenseApp.get(CONFIGNAME.get('awardItemList')));
		}
		//消息提醒
		Ext.getCmp('messageReminderGird').getStore().removeAll();
		if(!Ext.isEmpty(recompenseApp.get(CONFIGNAME.get('messageReminderList')))){
			Ext.getCmp('messageReminderGird').getStore().loadData(recompenseApp.get(CONFIGNAME.get('messageReminderList')));
		}
		//跟进信息
		Ext.getCmp('messageGird').getStore().removeAll();
		if(!Ext.isEmpty(recompenseApp.get(CONFIGNAME.get('messageList')))){
			Ext.getCmp('messageGird').getStore().loadData(recompenseApp.get(CONFIGNAME.get('messageList')));
		}
		//多陪
		Ext.getCmp('overPayGrid').getStore().removeAll();
		if(!Ext.isEmpty(recompenseApp.get(CONFIGNAME.get('overpayList')))){
			Ext.getCmp('overPayGrid').getStore().loadData(recompenseApp.get(CONFIGNAME.get('overpayList')));
		}
		Ext.getCmp('responsibleDeptGird').getStore().each(function(record){
			record.commit();
		});
		Ext.getCmp('responsibleDeptGird').getStore().removed = [];
		Ext.getCmp('deptChargeGird').getStore().each(function(record){
			record.commit();
		});
		Ext.getCmp('deptChargeGird').getStore().removed = [];
		Ext.getCmp('awardItemGird').getStore().each(function(record){
			record.commit();
		});
		Ext.getCmp('awardItemGird').getStore().removed = [];
		Ext.getCmp('messageReminderGird').getStore().each(function(record){
			record.commit();
		});
		Ext.getCmp('messageReminderGird').getStore().removed = [];
		//客户历史理赔记录清空
		Ext.getCmp('recomHistoryGird').getStore().removeAll();
		//加载追偿信息数据
		Ext.getCmp('recalledComPanel').getForm().reset();
		Ext.getCmp('recalledComPanel').getForm().loadRecord(recalledComModel);
		//设置追偿部门信息
		Ext.getCmp('recoveryDeptId').setValueId(recalledComModel.get('deptId'));
		Ext.getCmp('recoveryDeptId').setValue(recalledComModel.get('deptName'));
		//Ext.getCmp('recoveryDeptId').getStore().add({'id':recalledComModel.get('deptId'),'deptName':recalledComModel.get('deptName')});
		//Ext.getCmp('recoveryDeptId').setValue(recalledComModel.get('deptId'));
		Ext.getCmp('recompenseDetailPanel').setActiveTab(1);
		
		if(me.record.recompenseMethod=='online' && !Ext.getCmp('detailBankInfo')){
			Ext.getCmp('recompenseDetailPanel').add(new BankInfo({'id':'detailBankInfo'})).doLayout();
			if(Ext.getCmp('detailBankInfo')){//在线理赔显示账户信息
				Ext.getCmp('detailBankInfo').getForm().loadRecord(recompenseApp);
			}
		}else if(me.record.recompenseMethod!='online' && Ext.getCmp('detailBankInfo')){
			Ext.getCmp('recompenseDetailPanel').remove('detailBankInfo').doLayout();
		}			
		
		if((me.record.status=='Submited'||me.record.status=='DocConfirmed')&&me.record.recompenseMethod=='normal'){
			me.deptChargeGirdData = new Array();
			Ext.getCmp('deptChargeGird').getStore().each(function(record){
				me.deptChargeGirdData.push(new DeptChargeModel(record.data));
			});
			me.responsibleDeptGirdData = new Array();
			Ext.getCmp('responsibleDeptGird').getStore().each(function(record){
				me.responsibleDeptGirdData.push(new ResponsibleDeptModel(record.data));
			});
			me.awardItemGirdData = new Array();
			Ext.getCmp('awardItemGird').getStore().each(function(record){
				me.awardItemGirdData.push(new AwardItemModel(record.data));
			});
			me.messageReminderGirdData = new Array();
			Ext.getCmp('messageReminderGird').getStore().each(function(record){
				me.messageReminderGirdData.push(new MessageReminderModel(record.data));
			});
			Ext.getCmp('deptChargeGird').store.removeAll();
			Ext.getCmp('responsibleDeptGird').store.removeAll();
			Ext.getCmp('awardItemGird').store.removeAll();
			Ext.getCmp('messageReminderGird').store.removeAll();
		}
		printDatas = me.record;												// 新增打印需要的数据，传给子页面
	},
	// 设置出险信息为不可编辑
	setComponentReadOnly : function() {
		var me = this;
		Ext.getCmp(CONFIGNAME.get('waybillNumber')).disable(true);
		Ext.getCmp(CONFIGNAME.get('insurDate')).setReadOnly(true);
		Ext.getCmp(CONFIGNAME.get('insurDate')).addCls('readonly');
		Ext.getCmp(CONFIGNAME.get('insurType')).setReadOnly(true);
		Ext.getCmp(CONFIGNAME.get('insurType')).addCls('readonly');
		Ext.getCmp(CONFIGNAME.get('insurQuantity')).setReadOnly(true);
		Ext.getCmp(CONFIGNAME.get('insurQuantity')).addCls('readonly');
		Ext.getCmp(CONFIGNAME.get('deptId')).setReadOnly(true);
		Ext.getCmp(CONFIGNAME.get('deptId')).addCls('readonly');
	}
});

/**
 * tabContent详情
 */
Ext.define('DetailBtnPanel', {
	extend : 'PopButtonPanel',
	id : 'detailBtnPanel',
	getItems:function(){
		var me = this;
		var actionIds = me.actionIds;
		var recompenseId = me.recompenseId;
		var status = me.status;
		var recompenseMethod = me.recompenseMethod;
		var reportDept = me.reportDept;
		var deptId = me.deptId;
		var confirmDeptId=me.confirmDeptId;
		
		return [{
			xtype:'middlebuttonpanel'
		},{
			xtype:'poprightbuttonpanel',
			id:'detailBtnPanelPoprightbuttonpanel',
			width:700,
			items:[DpUtil.getActionButton(actionIds,recompenseId,status,recompenseMethod,reportDept,deptId,confirmDeptId)]
		}];
	},
	initComponent : function() {
		this.items = this.getItems();
		this.callParent();
	}
});

/**
 * 理赔信息 form
 */
Ext.define('DetailRecompenseInfo', {
	extend : 'TitleFormPanel',
	id:'detailRecompenseInfo',
	// width:730,
	height : 97,
//	flex:1.5,
	getItems : function() {
		var me = this;
		return [ {
			xtype : 'fieldset',
			title : i18n('i18n.recompense.recompenseInfo'),
			layout : {
				type : 'table',
				columns : 5
			},
			defaults : {
				labelSeparator : '',
				style : 'margin-right:5px;',
				labelWidth : 50,
				width : 135,
				xtype : 'textfield',
				readOnly : true,
				cls:'readonly',
				colspan : 1
			},
			items : [ {
				name : CONFIGNAME.get('conRecompenseMethod'),			//1.
				fieldLabel : i18n('i18n.recompense.recompenseMethod')
			}, {
				fieldLabel : i18n('i18n.recompense.recompensetype'),	//2.
				name : CONFIGNAME.get('recompenseType')
			}, {
				fieldLabel : i18n('i18n.recompense.status'),			//3.
				name : CONFIGNAME.get('status')
			}, {
				fieldLabel : i18n('i18n.recompense.custNumber'),		//4.
				id :'showCustNumber',
				name : CONFIGNAME.get('custNumber')
			}, {
				fieldLabel : i18n('i18n.recompense.custName'),			//5.
				id :'showCustName',
				name : CONFIGNAME.get('custName'),
				xtype : 'textfield'
			}, {
				fieldLabel : i18n('i18n.recompense.custLevel'),			//6.
				id :'showConCustomerLevel',
				name : CONFIGNAME.get('degree'),
				xtype : 'textfield'
			}, {
				fieldLabel : i18n('i18n.recompense.claimAmount'),		//7.
				name : CONFIGNAME.get('recompenseAmount'),
				xtype : 'numberfield'
			}, {
				fieldLabel : i18n('i18n.recompense.recompensePerson'),	//8.
				name : CONFIGNAME.get('companyName')
			}, {
				fieldLabel : i18n('i18n.recompense.phone'),				//9.
				name : CONFIGNAME.get('companyPhone')
			}, {
				fieldLabel : i18n('i18n.recompense.fax'),				//10.
				name : CONFIGNAME.get('companyFax')
			} ]
		} ];
	},
	initComponent : function() {
		var me = this;
		me.items = me.getItems();
		this.callParent();
	}
});

/**
 * 理赔详情
 */
Ext.define('RecompenseDetailPanel', { // 第二步,定义页签容,其继承NormalTabPanel
	extend : 'NormalTabPanel',
	id:'recompenseDetailPanel',
	border : true,
	flex : 3,
//	layout:'anchor',
	autoScroll:true,
	initComponent : function() {
		var me = this;
		me.items = me.getItems();
		this.callParent();
	},
	getItems : function() {
		var handleOpinionsTab = new HandleOpinionsTab();
		var recallInfoTab = new RecalledComTab();
		var workflowInfoTab = new WorkflowInfoTab();//工作流TAB页签
		var accidentInfoTab = new AccidentInfoTab();
		var historyPanelTab = new HistoryPanelTab();//历史理赔记录TAB页签
		var recompenseDescTab = new RecompenseDescTab();//理赔描述TAB页签
		var overPayTab = new OverPayTab();//多赔TAB页签
		var payRecordPanel = new PayRecordPanel();//交易记录
		return [ recompenseDescTab, handleOpinionsTab, recallInfoTab,
				workflowInfoTab, accidentInfoTab, historyPanelTab,overPayTab,payRecordPanel ];
	}
});
/**
 * <P>
 * 差错信息Tab
 * </p>
 * 
 * @author 潘光均
 * @date 2012-04-12
 */
Ext.define('AccidentInfoTab', {
	extend : 'TabContentPanel',
	items : null,
	title : i18n('i18n.recompense.accidentInfo'),
	flex : 1,
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	listeners:{
		/**.
		 * <p>
		 * 切换到该（差错信息）TAB页面时，发送请求，加载差错信息数据</br>
		 * </p>
		 * @author  张斌
		 * @时间    2012-06-29
		 */
		activate:function(th,e){
			if(SearchError){
				Ext.getCmp('accidentGrid').getStore().removeAll();
				var recompenseType = Ext.getCmp('recompenseDetailsUI').record.recompenseType;
				var wayBillNum = null;
				if(!Ext.isEmpty(Ext.getCmp('recompenseDetailsUI').record.waybill)){
					wayBillNum = Ext.getCmp('recompenseDetailsUI').record.waybill.waybillNumber;
				}
				var params = {'recompenseType':recompenseType,'wayBillNum':wayBillNum};
				var successFn = function(json){
					SearchError = false;
					var oaAccidentInfoList = json.oaAccidentInfoList;
					if (oaAccidentInfoList!=null&&oaAccidentInfoList!=undefined&&oaAccidentInfoList.length>0) {
						for(var i= 0;i<oaAccidentInfoList.length;i++){
							if(!Ext.isEmpty(oaAccidentInfoList[i])){
								var accidentModel = new AccidentModel();
								accidentModel.set(CONFIGNAME.get('accidentCode'),oaAccidentInfoList[i].accidentCode);
								accidentModel.set(CONFIGNAME.get('accidentName'),oaAccidentInfoList[i].accidentName);
								if(!Ext.isEmpty(oaAccidentInfoList[i].rewardPunishments)){
									for(var j=0;j<oaAccidentInfoList[i].rewardPunishments.length;j++){
										accidentModel.set(CONFIGNAME.get('money'),oaAccidentInfoList[i].rewardPunishments[j].money);
										accidentModel.set(CONFIGNAME.get('processDate'),oaAccidentInfoList[i].rewardPunishments[j].processDate);
										accidentModel.set(CONFIGNAME.get('processPerson'),oaAccidentInfoList[i].rewardPunishments[j].processPerson);
										accidentModel.set(CONFIGNAME.get('processResult'),oaAccidentInfoList[i].rewardPunishments[j].processResult);
										accidentModel.set(CONFIGNAME.get('processTarget'),oaAccidentInfoList[i].rewardPunishments[j].processTarget);
										accidentModel.set(CONFIGNAME.get('rewardPunishmentDescription'),oaAccidentInfoList[i].rewardPunishments[j].rewardPunishmentDescription);
										accidentModel.commit();
									}
								}
								Ext.getCmp('accidentGrid').getStore().add(accidentModel);
							}
						}
					}
				};
				var failureFn = function(json){
					if(Ext.isEmpty(json)){
						MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
						//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
					}else{
						MessageUtil.showErrorMes(json.message);
					}
				};
				recompenseData.searchRecompenseOaAccidentInfo(params,successFn,failureFn);
			}
		}
	},
	initComponent : function() {
		this.items = [ {
			xtype : 'titlepanel',
			items : [ {
				xtype : 'displayfield',
				value : i18n('i18n.recompense.accidentInfo'),
				width : 100
			} ]
		}, {
			xtype : 'basicpanel',
			flex : 1,
			items : [ new AccidentGrid() ]
		} ];
		this.callParent();
	}
});
/**
 * <P>
 * 多赔信息Tab
 * </p>
 * 
 * @author 潘光均
 * @date 2012-04-12
 */
Ext.define('OverPayTab', {
	extend : 'TabContentPanel',
	items : null,
	title : i18n('i18n.recompense.overPayInfo'),
	flex : 1,
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	initComponent : function() {
		this.items = [ {
			xtype : 'titlepanel',
			items : [ {
				xtype : 'displayfield',
				value : i18n('i18n.recompense.overPayInfo'),
				width : 100
			} ]
		}, {
			xtype : 'basicpanel',
			flex : 1,
			items : [ new OverPayGrid() ]
		} ];
		this.callParent();
	}
});
/**
 * <P>
 * 差错信息Grid
 * </p>
 * 
 * @author 潘光均
 * @date 2012-04-12
 */
Ext.define('AccidentGrid', {
	extend : 'PopupGridPanel',
	id : 'accidentGrid',
	sortableColumns : false,
	autoScroll:true,
	enableColumnHide : false,
	enableColumnMove : false,
	store:null,
	getColumns : function() {
		var me = this;
		return [ {
			xtype : 'rownumberer',
			width : 40,
			text : i18n('i18n.recompense.num')
		},{
			header : i18n("i18n.recompense.accidentName"),
			dataIndex : CONFIGNAME.get('accidentName')
		}, {
			header : i18n("i18n.recompense.accidentHandleResult"),
			dataIndex : CONFIGNAME.get('processResult')
//			renderer : function(value) {
//				return new Date(value).format('yyyy-MM-dd hh:mm:ss');
//			}
		}, {
			header : i18n("i18n.recompense.handleObj"),
			dataIndex : CONFIGNAME.get('processTarget')
		}, {
			header : i18n("i18n.recompense.awardDesc"),
			dataIndex : CONFIGNAME.get('rewardPunishmentDescription')
		}, {
			header : i18n("i18n.recompense.awardAmount"),
			width : 65,
			dataIndex :CONFIGNAME.get('money')
			
		}, {
			header : i18n("i18n.recompense.handleTime"),
			dataIndex : CONFIGNAME.get('processDate'),
			renderer : function(value) {
				if (null!=value) {
					return new Date(value).format('yyyy-MM-dd hh:mm:ss');
				}
			}
		}, {
			header : i18n("i18n.recompense.handlePerson"),
			dataIndex :  CONFIGNAME.get('processPerson')
		} ];
	},
	initComponent : function() {
		var me = this;
		me.columns = me.getColumns();
		me.store = DpUtil.getStore('accidentGrid', 'AccidentModel', null, []);
		this.callParent();
		me.getView().on('render', function(view) {
		    view.tip = Ext.create('Ext.tip.ToolTip', {
		        target: view.el,
		        delegate: view.itemSelector,
		        trackMouse: true,
		        listeners: {
		            beforeshow: function updateTipBody(tip) {
		                tip.update(i18n('i18n.recompense.accidentHandleResult')+':'+ view.getRecord(tip.triggerElement).get(CONFIGNAME.get('processResult')));
		            }
		        }
		    });
		});
	}
});

/**
 * <P>
 * 工作流与跟进Tab
 * </p>
 * 
 * @author 潘光均
 * @date 2012-04-12
 */
Ext.define('WorkflowInfoTab', {
	extend : 'TabContentPanel', // --,定义页签容器里的子页签,其继承TabContentPanel
	title : i18n('i18n.recompense.workflowAndFellow'),
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	listeners:{
		/**.
		 * <p>
		 * 切换到该（工作流与跟进
		 * 信息）TAB页面时，发送请求，加载工作流信息数据</br>
		 * </p>
		 * @author  张斌
		 * @时间    2012-07-11
		 */
		activate:function(th,e){
			if(SearchWorkFlow){
				Ext.getCmp('workFlowGird').getStore().removeAll();
				var recompenseId = Ext.getCmp('recompenseDetailsUI').record.id;
				var params = {'recompenseId':recompenseId};
				var successFn = function(json){
					SearchWorkFlow = false;
					var workFlowList = json.oaWorkflowList;
					if (!Ext.isEmpty(workFlowList)) {
						Ext.getCmp('workFlowGird').getStore().loadData(workFlowList);
					}
				};
				var failureFn = function(json){
					if(Ext.isEmpty(json)){
						MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
						//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
					}else{
						MessageUtil.showErrorMes(json.message);
					}
				};
				recompenseData.searchRecompenseWorkflowById(params,successFn,failureFn);
			}
		}
	},
	getItems : function() {
		var workFlowGird = new WorkFlowTitlePanel();
		var messageGird = new MessageTitlePanel();
		return [ messageGird, workFlowGird ];
	},
	initComponent : function() {
		this.items = this.getItems();
		this.callParent();
	}
});

/**
 * <P>
 * 处理意见Tab
 * </p>
 * 
 * @author 潘光均
 * @date 2012-04-12
 */
Ext.define('HandleOpinionsTab', {
	extend : 'TabContentPanel', // --,定义页签容器里的子页签,其继承TabContentPanel
	title : i18n('i18n.recompense.handleOpoinons'),
	layout : 'border',
	initComponent : function() {
		this.items = this.getItems();
		this.callParent();
	},
	getItems : function() {
		var amountPanel = new AmountPanel();
		var deptChargeGird = null;
		if(Ext.isEmpty(Ext.getCmp('deptChargeTitlePanel'))){
			deptChargeGird = new DeptChargeTitlePanel();
		}else{
			deptChargeGird = Ext.getCmp('deptChargeTitlePanel');
		}
		var responsibleDeptGird = null;
		if(Ext.isEmpty(Ext.getCmp('responsibleDeptTitlePanel'))){
			responsibleDeptGird = new ResponsibleDeptTitlePanel();
		}else{
			responsibleDeptGird = Ext.getCmp('responsibleDeptTitlePanel');
		}
		var awardItemGird = null;
		if(Ext.isEmpty(Ext.getCmp('awardItemTitlePanel'))){
			awardItemGird = new AwardItemTitlePanel();
		}else{
			awardItemGird = Ext.getCmp('awardItemTitlePanel');
		}
		var messageReminderGirdPanel = null;
		if(Ext.isEmpty(Ext.getCmp('messageReminderTitlePanel'))){
			messageReminderGirdPanel = new MessageReminderTitlePanel();
		}else{
			messageReminderGirdPanel = Ext.getCmp('messageReminderTitlePanel');
		}
		var feeInfo = {
			xtype : 'titlepanel',
			flex : 1,
			layout : {
				type : 'vbox',
				align : 'stretch'
			},
			items : [ {
				xtype : 'displayfield',
				value : i18n('i18n.recompense.feeInfo'),
				flex : 1,
				width : 100
			}, {
				xtype : 'textarea',
				id:CONFIGNAME.get('costExplain'),//费用说明
				//readOnly:true,
				//cls:'readonly',
				height:300,
				name:CONFIGNAME.get('costExplain'),
				maxLength:1200,
				flex : 4
			} ]
		};

		var northPanel = {
			region : 'north',
			xtype : 'basicpanel',
			items : [ amountPanel ]
		};

		var centerPanel = {
			flex : 9,
			height:300,
			region : 'center',
			xtype : 'basicpanel',
			layout : {
				type : 'vbox',
				align : 'stretch'
			},
			items : [ {
				flex : 1,
				xtype : 'basicpanel',
				layout : {
					type : 'vbox',
					align : 'stretch'
				},
				items : [ 
				   {
					xtype : 'basicpanel',
					flex : 3,
					layout : {
						type : 'vbox',
						align : 'stretch'
					},
					items : [ {
						xtype : 'basicpanel',
						flex : 1,
						layout : {
							type : 'hbox',
							align : 'stretch'
						},
						items : [ {
							flex : 1,
							layout : {
								type : 'vbox',
								align : 'stretch'
							},
							items : [ responsibleDeptGird, awardItemGird ]
						}, {
							flex : 1,
							layout : {
								type : 'vbox',
								align : 'stretch'
							},
							items : [ deptChargeGird, messageReminderGirdPanel ]
						} ]
					} ]
				}, feeInfo ]
			} ]
		};
		return [ northPanel, centerPanel ];
	}
});
/**
 * <P>
 * 追偿信息Tab
 * </p>
 * 
 * @author 潘光均
 * @date 2012-04-12
 */
Ext.define('RecalledComTab', {
	extend : 'TabContentPanel', // --,定义页签容器里的子页签,其继承TabContentPanel
	title : i18n('i18n.recompense.recalledInfo'),
	getItems : function() {
		var recalledComPanel = new RecalledComPanel();
		return [ recalledComPanel ];
	},
	initComponent : function() {
		this.items = this.getItems();
		this.callParent();
	}
});
/**
 * <P>
 * 历史描述Tab
 * </p>
 * 
 * @author 潘光均
 * @date 2012-04-12
 */
Ext.define('RecompenseDescTab', {
	extend : 'TabContentPanel', // --,定义页签容器里的子页签,其继承TabContentPanel
	title : i18n('i18n.recompense.recompenseDesc'),
	autoScroll : true,
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	listeners:{
		activate:function(th,e){
			//if(RecompenseInfoDataNew){
				//RecompenseInfoDataNew = false;
				var recompenseApp = new RecompenseApp(Ext.getCmp('recompenseDetailsUI').record);
				Ext.getCmp('showDangerInfoGrid').getStore().removeAll();
				if(!Ext.isEmpty(recompenseApp.get(CONFIGNAME.get('issueItemList')))){
					Ext.getCmp('showDangerInfoGrid').getStore().loadData(recompenseApp.get(CONFIGNAME.get('issueItemList')));
					Ext.getCmp('showDangerInfoGrid').doLayout();
				}
				var goodsTransList = recompenseApp.get(CONFIGNAME.get('goodsTransList'));
				var goodsTranceList = new Array();//货物托运清单
				var recompenseList = new Array();//理赔清单
				for(var i = 0;i<goodsTransList.length;i++){
					if(goodsTransList[i].isClaims){
						recompenseList.push(goodsTransList[i]);
					}else{
						goodsTranceList.push(goodsTransList[i]);
					}
				}
				//理赔清单
				Ext.getCmp('showRecompenseListGird').getStore().removeAll();
				if(!Ext.isEmpty(recompenseList)){
					Ext.getCmp('showRecompenseListGird').getStore().loadData(recompenseList);
					Ext.getCmp('showRecompenseListGird').doLayout();
				}
				//货物托运清单
				Ext.getCmp('showGoodsTranceGird').getStore().removeAll();
				if(!Ext.isEmpty(goodsTranceList)){
					Ext.getCmp('showGoodsTranceGird').getStore().loadData(goodsTranceList);
					Ext.getCmp('showGoodsTranceGird').doLayout();
				}
				//附件
				Ext.getCmp('showAttachmentGrid').getStore().removeAll();
				if(!Ext.isEmpty(recompenseApp.get(CONFIGNAME.get('attachmentList')))){
					Ext.getCmp('showAttachmentGrid').getStore().loadData(recompenseApp.get(CONFIGNAME.get('attachmentList')));
					Ext.getCmp('showAttachmentGrid').doLayout();
				}
			//}	
		}
	},
	getItems : function() {
		var recompenseListPanel = null;
		if(Ext.isEmpty(Ext.getCmp('recompenseListTitlePanel'))){
			recompenseListPanel = new RecompenseListTitlePanel();
		}else{
			recompenseListPanel = Ext.getCmp('recompenseListTitlePanel');
		}
		var recompenseDescListPanel = null;
		if(Ext.isEmpty(Ext.getCmp('dangerInfoTitlePanel'))){
			recompenseDescListPanel = new DangerInfoTitlePanel();
		}else{
			recompenseDescListPanel = Ext.getCmp('dangerInfoTitlePanel');
		}
		var goodsTransListPanel = null;
		if(Ext.isEmpty(Ext.getCmp('goodsTranceTitlePanel'))){
			goodsTransListPanel = new GoodsTranceTitlePanel();
		}else{
			goodsTransListPanel = Ext.getCmp('goodsTranceTitlePanel');
		}
		var attachmentListPanel = null;
		if(Ext.isEmpty(Ext.getCmp('attachmentTitlePanel'))){
			attachmentListPanel = new AttachmentTitlePanel();
		}else{
			attachmentListPanel = Ext.getCmp('attachmentTitlePanel');
		}
		var leftPanel = {
			flex : 1,
			xtype : 'basicpanel',
			colspan : 1,
			layout : {
				type : 'hbox',
				align : 'stretch'
			},
			items : [ {
				flex : 1,
				xtype : 'basicpanel',
				items : [ goodsTransListPanel ]
			}, {
				flex : 1,
				xtype : 'basicpanel',
				items : [ recompenseListPanel ]
			} ]
		};
		var reigthPanel = {
			xtype : 'basicpanel',
			colspan : 1,
			flex : 1,
			layout : {
				type : 'hbox',
				align : 'stretch'
			},
			items : [ {
				flex : 1,
				xtype : 'basicpanel',
				items : [ recompenseDescListPanel ]
			}, {
				flex : 1,
				xtype : 'basicpanel',
				items : [ attachmentListPanel ]
			} ]
		};

		return [ leftPanel, reigthPanel ];

		// return [goodsTransListPanel,recompenseDescListPanel,
		// recompenseListPanel,attachmentListPanel];
	},
	initComponent : function() {
		var me = this;
		me.items = me.getItems();
		this.callParent();
	}
});

/**
 * 理赔历史记录tab
 */
Ext.define('HistoryPanelTab', {
	extend : 'TabContentPanel', // --,定义页签容器里的子页签,其继承TabContentPanel
	title : i18n('i18n.recompense.recompenseHistoryInfo'),
	initComponent : function() {
		var me = this;
		me.items = me.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		var historyBtnPanel = new RecompHistoryBtnPanel();
		var historyGridPanel = new RecomHistoryGird();
		return [ historyBtnPanel,new RecompHistoryAllPanel(), historyGridPanel ];
	}
});

/**
 * <P>
 * 历史记录查询按钮panel
 * </p>
 * 
 * @author 潘光均
 * @date 2012-04-12
 */
Ext.define('RecompHistoryBtnPanel', {
	extend : 'BasicHboxFormPanel', // --第一步,继承FormAndBtnPanel
	height:28,
	defaults:{margins:'0 0 7 0'},
	initComponent : function() {
		var me = this;
		me.items = me.getItems(); // --第二步,获得items的值
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [
		        
//		        {
//			xtype : 'basicformpanel', // --1.
//			// 设置一个noborderformpanel,用于存放各个查询条件
//			 layout:{
//			 type:'table',
//			 columns:3
//			 },
//			defaultType : 'textfield',
//			items : [
			         
			         
			         {
				xtype:'datefield',
				labelWidth : 60,
				width:210,
				fieldLabel : i18n('i18n.recompense.reportTime'),
				value : new Date(new Date()-31*24*3600*1000),
				format:'Y-m-d',
				editable:false,
				id:'historySearchReportTime'
			}, {
				labelWidth : 20,
				width:170,
				xtype:'datefield',
				fieldLabel : i18n('i18n.recompense.to'),
				value : new Date(),
				editable:false,
				format:'Y-m-d',
				id:'historySearchReportEndTime'
			},
			{
				width:15
			},
					{
						text : i18n('i18n.recompense.search'),
						xtype : 'button',// --2. 设置一个查询条件simpsearchbtn
						handler:function(){
							var reportDate = Ext.getCmp('historySearchReportTime').getValue();
							var reportEndDate = Ext.getCmp('historySearchReportEndTime').getValue();
							if ((reportEndDate-reportDate)/(24*3600*1000)>31||reportEndDate-reportDate<0) {
								MessageUtil.showMessage(i18n('i18n.recompense.infomation.timeOutOfRange'));
								return false;
							}
							//客户编码
							var custId = null;
							if(Ext.isEmpty(Ext.getCmp('recompenseDetailsUI').record.customer)){
								MessageUtil.showErrorMes(i18n('i18n.recompense.recompenseHaveNoCustHaveNoHistory'));
								return ;
							}else{
								custId = Ext.getCmp('recompenseDetailsUI').record.customer.id;
							}
							var recompenseId = Ext.getCmp('recompenseDetailsUI').record.id;
							Ext.getCmp('recomHistoryGird').getStore().removeAll();
							//封装查询条件
							var searchParams = {'startTime':reportDate,
									'endTime':reportEndDate,
									'customerId':custId,
									'recompenseId':recompenseId
							};
							var successFn = function(json){
								var recompenseApp = new RecompenseApp(json.app);
								Ext.getCmp('recompHistoryAllPanel').getForm().loadRecord(recompenseApp);
								Ext.getCmp('totalInsurAmount').setValue(recompenseApp.get(CONFIGNAME.get('waybill')).insurAmount);
								//请求刷新历史记录表格数据
								Ext.getCmp('recomHistoryGird').getStore().add(json.recompenseAppList);
							};
							var failureFn = function(json){
								if(Ext.isEmpty(json)){
									MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
									//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
								}else{
									MessageUtil.showErrorMes(json.message);
								}
							}
							//获取统计信息
							recompenseData.searchHistroyRecompense(searchParams,successFn,failureFn);
						}
					}
					//-----------------------
//				       ]
//			}

			]
//		} ];
	}
});
/**
 * <P>
 * 历史统计信息panel
 * </p>
 * 
 * @author 张斌
 * @date 2012-05-11
 */
Ext.define('RecompHistoryAllPanel', {
	extend : 'NoTitleFormPanel',
	id:'recompHistoryAllPanel',
	height:40,
	defaults:{margins:'0 0 7 0'},
	initComponent : function() {
		var me = this;
		me.items = me.getItems(); // --第二步,获得items的值
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [{
			layout:{
				type:'table',
				columns:4
			},
			defaultType:'textfield',
			defaults:{
				labelWidth:100,
				width:180
			},
			items:[
			         {
					xtype:'textfield',
					labelWidth:84,
					width:164,
					name:CONFIGNAME.get('recompenseAmount'),
					fieldLabel : i18n('i18n.recompense.recompenseAmountAll'),
					cls:'readonly',
					readOnly:true
				}, {
					xtype:'textfield',
					name:CONFIGNAME.get('normalAmount'),
					fieldLabel : i18n('i18n.recompense.normalAmountAll'),
					cls:'readonly',
					readOnly:true
				},
				{
					xtype:'textfield',
					name:CONFIGNAME.get('realAmount'),
					fieldLabel : i18n('i18n.recompense.realAmountAll'),
					cls:'readonly',
					readOnly:true
				},{
					xtype:'textfield',
					id:'totalInsurAmount',
					name:CONFIGNAME.get('insurAmount'),
					fieldLabel : i18n('i18n.recompense.isureAmountAll'),
					cls:'readonly',
					readOnly:true
				}
			]
		}]
	}
});
/**
 * 理赔历史记录表格
 */
Ext.define('RecomHistoryGird', {
	extend : 'PopupGridPanel', // --第一步,继承NormalGridPanel
	store : null,
	id:'recomHistoryGird',
	columns : null,
	height:255,
	autoScroll:true,
	initComponent : function() {
		var me = this;
		me.columns = me.getColums();
		me.store = recompenseData.getHistroyRecompenseSearchStore();// --第三步,加载每个单元格的具体内容
		this.callParent();
	},
	getColums : function() {
		return [ {
			xtype : 'rownumberer',
			width : 40,
			text : i18n('i18n.recompense.num')
		},{ // --第二步,规定表格title
			header : i18n('i18n.recompense.custNumber'),
			dataIndex : CONFIGNAME.get('customer'),
			renderer : function(value) {
				if (!Ext.isEmpty(value)) {
					return value.custNumber;
				}
				return null;
			}
		}, {
			header : i18n('i18n.recompense.custLevel'),
			dataIndex : CONFIGNAME.get('customer'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return DpUtil.changeDictionaryCodeToDescrip(value.degree,DataDictionary.MEMBER_GRADE);
				}
				return null;
			}
		}, {
			header : i18n('i18n.recompense.custName'),
			dataIndex : CONFIGNAME.get('customer'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.custName;
				}
				return null;
			}
		}, {
			header : i18n('i18n.recompense.orderNumOrErroeNum'),
			dataIndex : CONFIGNAME.get('waybill'),
			renderer:function(value){
				if(!Ext.isEmpty(value)){
					return value.waybillNumber;
				}else{
					return null;
				}
				
			}
		}, {
			header : i18n('i18n.recompense.insureAmount'),
			dataIndex : CONFIGNAME.get('waybill'),
			renderer:function(value){
				if(Ext.isEmpty(value)){
					return null;
				}else{
					return value.insurAmount
				}
			}
		}, {
			header : i18n('i18n.recompense.claimAmount'),
			dataIndex : CONFIGNAME.get('recompenseAmount')
		}, {
			header : i18n('i18n.recompense.normalAmount'),
			dataIndex : CONFIGNAME.get('normalAmount')
		}, {
			header : i18n('i18n.recompense.realPrice'),
			dataIndex : CONFIGNAME.get('realAmount')

		}, {
			header : i18n('i18n.recompense.reportPerson'),
			dataIndex : CONFIGNAME.get('reportMan')
		}, {
			header : i18n('i18n.recompense.reportTime'),
			dataIndex : CONFIGNAME.get('reportDate'),
	    	renderer : function(value){
         	return new Date(value).format('yyyy-MM-dd hh:mm:ss');
         }
		}, {
			header : i18n('i18n.recompense.approveEndTime'),
			dataIndex : CONFIGNAME.get('lastApprovedTime'),
	    	renderer : function(value){
	    		if(!Ext.isEmpty(value)){
	    			return new Date(value).format('yyyy-MM-dd hh:mm:ss');
	    		}else{
	    			return null;
	    		}
         	
         }
		} ];
	}
});
/**.
 * <p>
 * 修改理赔WINDOW</br>
 * </p>
 * @author  张斌
 * @时间    2012-04-18
 */
Ext.define('UpdateRecompenseWindow',{
	extend:'PopWindow',
	title:i18n('i18n.recompense.updateRecompenseWindow'),
	id:'updateRecompenseWindow',
	fbar:null,
	closeAction:'hide',
	items:null,
	layout: 'fit',
	initComponent:function(){
		var me = this;
		me.fbar = me.getFbar();
		me.items = me.getItems();
		me.loadData();
		this.callParent();
	},
	listeners:{
		beforeshow:function(th){
			th.loadData();
		}
	},
	/**.
	 * <p>
	 * 加载数据</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-18
	 */
	loadData:function(){
		var recompenseApp = new RecompenseApp(Ext.getCmp('recompenseDetailsUI').record);
        if(recompenseApp.get(CONFIGNAME.get('recompenseType'))=='unbilled'){
        	Ext.getCmp('fastRecompenseMethod_id').setDisabled(true);
			   Ext.getCmp('normalRadio').setValue(true);
			   
			   var dataInsurTypeUnbilled = new Array();
  			   for(var i = 0;i<DataDictionary.DANGER_TYPE.length;i++){
				   if(DataDictionary.DANGER_TYPE[i].code !='FALSELY_CLAIM'){
					   dataInsurTypeUnbilled.push(DataDictionary.DANGER_TYPE[i]);
				   }
			   }    			   
  			   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().loadData(dataInsurTypeUnbilled);
		}
		var waybill = new WaybillModel(recompenseApp.get(CONFIGNAME.get('waybill')));
		Ext.getCmp('updateRecompenseBelongArea').setValue(recompenseApp.get(CONFIGNAME.get('deptId')));
		//Ext.getCmp('updateRecompenseBelongArea').getStore().loadData([{'id':recompenseApp.get(CONFIGNAME.get('deptId')),'deptName':recompenseApp.get(CONFIGNAME.get('deptName'))}]);
		
		Ext.getCmp('updateDangerInfoForm').getForm().loadRecord(waybill);
		Ext.getCmp('updateDangerInfoForm').getForm().loadRecord(recompenseApp);
		Ext.getCmp('recompenseInfoForm').getForm().loadRecord(recompenseApp);
		Ext.getCmp('showRecompenseType').setValue(recompenseApp.get(CONFIGNAME.get('recompenseType')));
		//设置出现描述、理赔清单、货物托运清单、附件表格信息
		Ext.getCmp('dangerInfoGrid').getStore().loadData(recompenseApp.get(CONFIGNAME.get('issueItemList')));
		var goodsTransList = recompenseApp.get(CONFIGNAME.get('goodsTransList'));
		var goodsTranceList = new Array();
		var recompenseList = new Array();
		if(recompenseApp.get(CONFIGNAME.get('recompenseMethod'))=='online'){
			Ext.getCmp('normalRadio').setDisabled(true);
			Ext.getCmp('fastRecompenseMethod_id').setDisabled(true);
			Ext.getCmp('onlineRadio').setDisabled(false);
			Ext.getCmp('onlineRadio').setValue(true);
		}
		for(var i = 0;i<goodsTransList.length;i++){
			if(goodsTransList[i].isClaims){
				recompenseList.push(goodsTransList[i]);
			}else{
				goodsTranceList.push(goodsTransList[i]);
			}
		}
		Ext.getCmp('recompenseListGird').getStore().loadData(recompenseList);
		Ext.getCmp('recompenseListTotalAmount').getTotalAmount();
		Ext.getCmp('goodsTranceGird').getStore().loadData(goodsTranceList);
		Ext.getCmp('goodsTrancesListTotalAmount').getTotalAmount();
		Ext.getCmp('attachmentGrid').getStore().loadData(recompenseApp.get(CONFIGNAME.get('attachmentList')));
		Ext.getCmp('dangerInfoGrid').getStore().each(function(record){
			record.commit();
		});
		Ext.getCmp('dangerInfoGrid').getStore().removed = [];
		Ext.getCmp('recompenseListGird').getStore().each(function(record){
			record.commit();
		});
		Ext.getCmp('recompenseListGird').getStore().removed = [];
		Ext.getCmp('goodsTranceGird').getStore().each(function(record){
			record.commit();
		});
		Ext.getCmp('goodsTranceGird').getStore().removed = [];
		Ext.getCmp('attachmentGrid').getStore().each(function(record){
			record.commit();
		});
		Ext.getCmp('attachmentGrid').getStore().removed = [];
		
		//在线理赔可修改理赔类型
		if((recompenseApp.get(CONFIGNAME.get('status'))=='Submited'||recompenseApp.get(CONFIGNAME.get('status'))=='OnlineRefused')&&recompenseApp.get(CONFIGNAME.get('recompenseMethod'))=='online'){
			Ext.getCmp('showRecompenseType').setReadOnly(false);
			Ext.getCmp('showRecompenseType').removeCls('readonly');
			Ext.getCmp('showRecompenseType').getStore().each(function(record){
				if(record.get('code')=='unbilled'){
					Ext.getCmp('showRecompenseType').getStore().remove(record);//去掉未开单理赔
				}
				if(record.get('code')=='ALL'){
					Ext.getCmp('showRecompenseType').getStore().remove(record);//去掉ALL
				}
			});	
		}else{
			Ext.getCmp('showRecompenseType').setReadOnly(true);
			Ext.getCmp('showRecompenseType').addCls('readonly');
		}
		
		if(recompenseApp.get(CONFIGNAME.get('recompenseMethod'))=='online' && !Ext.getCmp('bankInfoForm')){
			Ext.getCmp('recompenseInfoTab').add(new BankInfo({'id':'bankInfoForm'})).doLayout();
			if(Ext.getCmp('bankInfoForm')){//在线理赔显示账户信息
				Ext.getCmp('bankInfoForm').getForm().loadRecord(recompenseApp);
			}
		}else if(recompenseApp.get(CONFIGNAME.get('recompenseMethod'))!='online' && Ext.getCmp('bankInfoForm')){
			Ext.getCmp('recompenseInfoTab').remove('bankInfoForm').doLayout();
		}	
	},
	getItems:function(){
		var me = this;
		var showRecompenseType = null;
		if(Ext.isEmpty(Ext.getCmp('showRecompenseTypeForm'))){
			showRecompenseType = new ShowRecompenseType();
		}else{
			showRecompenseType = Ext.getCmp('showRecompenseTypeForm');
		}
		var updateDangerInfo = null;
		if(Ext.isEmpty(Ext.getCmp('updateDangerInfoForm'))){
			updateDangerInfo = new UpdateDangerInfo();
		}else{
			processDangerInfo = Ext.getCmp('processDangerInfoForm');
		}
		var recompenseInfoTab = null;
		if(Ext.isEmpty(Ext.getCmp('recompenseInfoTab'))){
			recompenseInfoTab = new RecompenseInfoTab({'width':750});
		}else{
			recompenseInfoTab = Ext.getCmp('recompenseInfoTab');
		}
		return [showRecompenseType,updateDangerInfo,recompenseInfoTab];
	},
	/**.
	 * <p>
	 * 修改后理赔上报数据提交</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-18
	 */
	updateRecompense:function(){
		var me = this;
		var recompenseApp = new RecompenseApp(Ext.getCmp('recompenseDetailsUI').record);
		var me = this;
		if(!Ext.getCmp('updateDangerInfoForm').getForm().isValid()){
			return;
		}
		if(!Ext.getCmp('recompenseInfoForm').getForm().isValid()){
			MessageUtil.showMessage(i18n('i18n.recompense.haveMoreRecompenseInfo'));
			return;
		}
		if(Ext.getCmp('dangerInfoGrid').getStore().getCount()==0){
			MessageUtil.showMessage(i18n('i18n.recompense.dangerInfoMustHaveOne'));
			return ;
		}
		var recompenseMethod = Ext.getCmp(CONFIGNAME.get('recompenseMethod')).getValue().recompenseMethod;
		if(recompenseMethod=='normal'){
			if(Ext.getCmp('goodsTranceGird').getStore().getCount()==0){
				MessageUtil.showMessage(i18n('i18n.recompense.tranceListHaveOne'));
				return ;
			}else if(Ext.getCmp('recompenseListGird').getStore().getCount()==0){
				MessageUtil.showMessage(i18n('i18n.recompense.recompenseListHaveOne'));
				return ;
			}
		}
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
		Ext.getCmp('updateDangerInfoForm').getForm().updateRecord(recompenseApp);
		var custId = Ext.getCmp(CONFIGNAME.get('custId')).getValue();
		var custmer = {'id':custId};
		recompenseApp.set(CONFIGNAME.get('customer'),custmer);
		Ext.getCmp('recompenseInfoForm').getForm().updateRecord(recompenseApp);
		Ext.getCmp('showRecompenseTypeForm').getForm().updateRecord(recompenseApp);
		var successFn = function(json){
			Ext.getCmp('determineUpdateRecompense').setDisabled(false);
			me.hide();
			DpUtil.refreshRecompenseInfo();
			MessageUtil.showInfoMes(json.message);
		}
		var failureFn = function(json){
			Ext.getCmp('determineUpdateRecompense').setDisabled(false);
			if(Ext.isEmpty(json)){
				MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
				//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
			}else{
				MessageUtil.showErrorMes(json.message);
			}
		}
		//出险描述
		var dangerInfoGridAddModelList = Ext.getCmp('dangerInfoGrid').getStore().getNewRecords( );			
		var dangerInfoGridDelModelList = Ext.getCmp('dangerInfoGrid').getStore().getRemovedRecords( ) ;
		var dangerInfoGridUpdModelList = Ext.getCmp('dangerInfoGrid').getStore().getUpdatedRecords( );
		var dangerInfoGridAddList = DpUtil.changeModelListToDataList(dangerInfoGridAddModelList);
		var dangerInfoGridDelList = DpUtil.changeModelListToDataList(dangerInfoGridDelModelList);
		var dangerInfoGridUpdList = DpUtil.changeModelListToDataList(dangerInfoGridUpdModelList);
		//货物托运清单
		var goodsTranceGirdAddModelList = Ext.getCmp('goodsTranceGird').getStore().getNewRecords( );
		var goodsTranceGirdUpdModelList = Ext.getCmp('goodsTranceGird').getStore().getUpdatedRecords( );
		var goodsTranceGirdDelModelList = Ext.getCmp('goodsTranceGird').getStore().getRemovedRecords( ) ;
		var goodsTranceGirdAddList = DpUtil.changeModelListToDataList(goodsTranceGirdAddModelList);
		var goodsTranceGirdUpdList = DpUtil.changeModelListToDataList(goodsTranceGirdUpdModelList);
		var goodsTranceGirdDelList = DpUtil.changeModelListToDataList(goodsTranceGirdDelModelList);
		//理赔清单
		var recompenseListGirdAddModelList = Ext.getCmp('recompenseListGird').getStore().getNewRecords( );
		var recompenseListGirdUpdModelList = Ext.getCmp('recompenseListGird').getStore().getUpdatedRecords( );
		var recompenseListGirdDelModelList = Ext.getCmp('recompenseListGird').getStore().getRemovedRecords( ) ;
		var recompenseListGirdAddList = DpUtil.changeModelListToDataList(recompenseListGirdAddModelList);
		var recompenseListGirdUpdList = DpUtil.changeModelListToDataList(recompenseListGirdUpdModelList);
		var recompenseListGirdDelList = DpUtil.changeModelListToDataList(recompenseListGirdDelModelList);
		DpUtil.listPushOtherList(goodsTranceGirdAddList,recompenseListGirdAddList);
		DpUtil.listPushOtherList(goodsTranceGirdDelList,recompenseListGirdDelList);
		DpUtil.listPushOtherList(goodsTranceGirdUpdList,recompenseListGirdUpdList);
		//附件
		var attachmentGridAddModelList = Ext.getCmp('attachmentGrid').getStore().getNewRecords( );
		var attachmentGridUpdModelList = Ext.getCmp('attachmentGrid').getStore().getUpdatedRecords( );
		var attachmentGridDelModelList = Ext.getCmp('attachmentGrid').getStore().getRemovedRecords( ) ;
		var attachmentGridAddList = DpUtil.changeModelListToDataList(attachmentGridAddModelList);
		var attachmentGridUpdList = DpUtil.changeModelListToDataList(attachmentGridUpdModelList);
		var attachmentGridDelList = DpUtil.changeModelListToDataList(attachmentGridDelModelList);
		var params = {'recompenseView':{'issueItemAddList':dangerInfoGridAddList,'goodsTransAddList':goodsTranceGirdAddList,
			'attachmentAddList':attachmentGridAddList,'issueItemDelList':dangerInfoGridDelList,'issueItemUpdList':dangerInfoGridUpdList,
			'goodsTransDelList':goodsTranceGirdDelList,'goodsTransUpdList':goodsTranceGirdUpdList,'attachmentDelList':attachmentGridDelList,
			'attachmentUpdList':attachmentGridUpdList,'app':recompenseApp.data}};
		Ext.getCmp('determineUpdateRecompense').setDisabled(true);
		recompenseData.updateRecompense(params,successFn,failureFn);
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			id:'determineUpdateRecompense',
			text:i18n('i18n.recompense.determine'),
			handler:function(){
				me.updateRecompense();
			}
		},{
			xtype:'button',
			text:i18n('i18n.recompense.cancel'),
			handler:function(){
				me.hide();
			}
			
		}];
	}
});
/**.
* <p>
* 出险信息界面</br>
* </p>
* @author  张斌
* @retuens {DangerInfo}
* @时间    2012-04-11
*/
Ext.define('UpdateDangerInfo',{
	extend:'TitleFormPanel',
	id:'updateDangerInfoForm',
	height:186,
	getItems:function(){
		var me = this;
		return [{xtype: 'basicfiledset',
		    title: i18n('i18n.recompense.dangerInfo'),
		    layout: {
		        type: 'table',
		        columns: 4
		    },
		    defaults:{
		    	labelSeparator:'',
		    	style:'margin-right:5px;',
		    	labelWidth:55,
		    	width:170
		    },
		    items:[{xtype: 'textfield',
			    	readOnly:true,
			    	cls:'readonly',
			    	name: CONFIGNAME.get('waybillNumber'),//凭证号
			    	emptyText:i18n('i18n.recompense.orderNum'),
			        fieldLabel:i18n('i18n.recompense.orderNumOrErroeNum*'),
				    colspan: 1
				},{
					xtype: 'textfield',
					name: CONFIGNAME.get('goodsName'),//货物名称
					colspan: 1,
			        fieldLabel:i18n('i18n.recompense.goodsName'),
			        cls:'readonly',
			        readOnly:true
				},{
					xtype: 'combo',//运输类型
					id:'detailsNTransType',
					name: CONFIGNAME.get('transType'),
					colspan: 1,
					fieldLabel:i18n('i18n.recompense.transType'),
					cls:'readonly',
			        readOnly:true,
			        displayField:'codeDesc',
			        valueField:'code',
			        queryMode:'local',
			        store:DpUtil.getStore(CONFIGNAME.get('transType'),null,['code','codeDesc'],DataDictionary.TRANSPORT_TYPE)
			        
			    },{
					xtype: 'textfield',
					colspan: 1,
					name: CONFIGNAME.get('packaging'),//包装
			        fieldLabel:i18n('i18n.recompense.packging'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'textfield',//保价人
					colspan: 1,
					name: CONFIGNAME.get('insured'),
			        fieldLabel:i18n('i18n.recompense.insureMan'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'textfield',//联系电话
					colspan: 1,
					name: CONFIGNAME.get('telephone'),
			        fieldLabel:i18n('i18n.recompense.connectMobilePhone'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'textfield',//s始发站
					colspan: 1,
					name: CONFIGNAME.get('startStation'),
			        fieldLabel:i18n('i18n.recompense.startStation'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'textfield',//目的站
					colspan: 1,
					name: CONFIGNAME.get('endStation'),
			        fieldLabel:i18n('i18n.recompense.endStation'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'datefield',//发货日期
					colspan: 1,
					format:'Y-m-d',
					editable:false,
					name: CONFIGNAME.get('sendDate'),
			        fieldLabel:i18n('i18n.recompense.sendGoodsTime'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'textfield',
					colspan: 1,
					name: CONFIGNAME.get('pwv'),//体件重
			        fieldLabel:i18n('i18n.recompense.numWeightVL'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'textfield',
					colspan: 1,
					name: CONFIGNAME.get('insurAmount'),//保价金额
			        fieldLabel:i18n('i18n.recompense.insureAmount'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'textfield',
					colspan: 1,
					name: CONFIGNAME.get('receiveDept'),//收货部门
			        fieldLabel:i18n('i18n.recompense.receiveDept'),
			        cls:'readonly',
			        readOnly:true
				  },{
					colspan: 1,
					readOnly:true,
					cls:'readonly',
					value:User.deptName,//报案
					name:CONFIGNAME.get('reportDeptName'),
			        fieldLabel:i18n('i18n.recompense.reportDept'),
			        xtype:'textfield'
				  },{
 					colspan: 1,
					value:User.empName,//报案人
					xtype:'textfield',
					name:CONFIGNAME.get('reportManName'),
			        fieldLabel:i18n('i18n.recompense.reportPerson'),
			        cls:'readonly',
			        readOnly:true
			    },{
					xtype: 'datefield',
					editable:false,
					readOnly:true,
					cls:'readonly',
					value:new Date(),//报案日期
					format:'Y-m-d',
					name: CONFIGNAME.get('reportDate'),
			        fieldLabel:i18n('i18n.recompense.reportTime')
				},{
					xtype: 'datefield',
					editable:false,
					allowBlank:false,//出险日期
					format:'Y-m-d',
					id:'updateInsurDate',
					name: CONFIGNAME.get('insurDate'),
			        fieldLabel:i18n('i18n.recompense.dangerTime*'),
			        listeners:{
			        	select:function(th,value,eOpts ){
                		   if(value>Ext.getCmp(CONFIGNAME.get('reportDate')).getValue()){
                			   MessageUtil.showMessage(i18n('i18n.recompense.dangerDateEarlyReportDate'));
                			   th.reset();
                		   }
	                	}
		            }
				},{
					colspan: 1,
					id:'updateInsurType',
					name: CONFIGNAME.get('insurType'),//出现类型
			        fieldLabel:i18n('i18n.recompense.dangerType*'),
			        xtype:'combo',
		            store: DpUtil.getStore(CONFIGNAME.get('insurType'),null,['code','codeDesc'],[]),
		             queryMode: 'local',
		             forceSelection: true,
		             editable:false,
		             allowBlank:false,
		             displayField: 'codeDesc',
		             valueField: 'code'
				  },{
	           	   xtype: 'numberfield',
					decimalPrecision:0,
					minValue:1,
					maxValue:9999999999,//出险数量
					allowBlank:false,
					name: CONFIGNAME.get('insurQuantity'),
			        fieldLabel:i18n('i18n.recompense.dangerCount*')
	              },{
	            	colspan: 1,
	  				name: CONFIGNAME.get('deptId'),//所属大区
	  		        fieldLabel:i18n('i18n.recompense.belongArea*'),
	  		        id:'updateRecompenseBelongArea',
	  		        xtype:'combo',
	  	            store: DpUtil.getStore('updateRecompenseBelongArea',null,['id','deptName'],AllAreas),
	  	            queryMode: 'local',
	  	            forceSelection: true,
	  	            allowBlank:false,
	  	            valueField:'id',
	  	            displayField: 'deptName',
	  	            listeners:{
	  	            	select:function(th){
	  	            		Ext.getCmp('showAreaDeptName').setValue(th.getRawValue());
	  	            	}
			        } 
                 },{
					hidden:true,
					xtype:'textfield',
					name: CONFIGNAME.get('arriveCustomerId')
			   },{
					hidden:true,
					xtype:'textfield',
					name: CONFIGNAME.get('leaveCustomerId')
			  },{
					hidden:true,
					value:User.deptId,
					xtype:'textfield',
					name: CONFIGNAME.get('reportDept')
			  },{
					hidden:true,
					value:User.userId,
					xtype:'textfield',
					name:CONFIGNAME.get('reportMan')
			  },{
					hidden:true,
					xtype:'textfield',
					id:'showAreaDeptName',
					name:CONFIGNAME.get('deptName')
				  }]
	           
	    }];
	},
	initComponent:function(){
		var me = this;
	    me.items = me.getItems();
		this.callParent();
	}
});

	function changeInsurTypeStore(recompenseType){
		Ext.getCmp('updateInsurType').clearValue();
		if(recompenseType=='lost_goods'){
		   //三个combox的数据都修改
		   Ext.getCmp('updateInsurType').getStore().removeAll(false);
		   Ext.getCmp('updateInsurType').getStore().add(
				   {	 code:'PIECE_LOST'		,codeDesc:'整件丢失'	},
				   {	 code:'TICKET_LOST'		,codeDesc:'整票丢失'	}
		   );
		}
		if(recompenseType=='abnormal'){				
		   //三个combox的数据都修改
		   Ext.getCmp('updateInsurType').getStore().removeAll(false);
		   Ext.getCmp('updateInsurType').getStore().add(
				   {	 code:'BREAKED'			,codeDesc:'破损'	},
				   {	 code:'DAMP'			,codeDesc:'潮湿'	},
				   {	 code:'POLLUTE'			,codeDesc:'污染'	},
				   {	 code:'GOODS_LESS'		,codeDesc:'内物短少'	},
				   {	 code:'FALSELY_CLAIM'	,codeDesc:'冒领'	},
				   {	 code:'OTHER'			,codeDesc:'其它'	}
		   );
			}
	}


Ext.define('ShowRecompenseType',{
	extend:'TitleFormPanel',
	id:'showRecompenseTypeForm',
	height:69,
	getItems:function(){
		return [{xtype: 'basicfiledset',
			title: i18n('i18n.recompense.recompenseType'),
			layout:{
				type:'table',
				columns:3
			},
			defaultType:'textfield',
			defaults:{
				labelSeparator:'',
				labelWidth:55,
				width:210
			},
		    items:[{
				colspan: 1,
				id:'showRecompenseType',
				name: CONFIGNAME.get('recompenseType'),
		        fieldLabel:i18n('i18n.recompense.recompenseType*'),
		        xtype:'combo',
		        oldValue:'unbilled',
	            store: DpUtil.getStore(CONFIGNAME.get('recompenseType'),null,['code','codeDesc'],DataDictionary.RECOMPENSE_TYPE),
	             queryMode: 'local',
	             displayField: 'codeDesc',
	             valueField: 'code',
	             listeners:{
	            	 'select':function(t){
	            		 	changeInsurTypeStore(t.getValue());
	            	 }
	             }
		    }]
		}];
	},
    initComponent:function(){
    	var me = this;
        me.items = me.getItems();
		this.callParent();
    }
});
//-----------------------------------------------待完成------------------------------------------------------------------------------------------------------
/**
 * 多赔表格
 */
Ext.define('OverPayGrid', {
	extend : 'PopupGridPanel', // --第一步,继承NormalGridPanel
	store : null,
	id:'overPayGrid',
	autoScroll:true,
	columns : null,
	initComponent : function() {
		var me = this;
		me.columns = me.getColums();
		me.store = DpUtil.getStore('overPayGridStore','OverPayModel',null,[]),
		this.callParent();
	},
	getColums : function() {
		return [ {
			xtype : 'rownumberer',
			width : 40,
			text : i18n('i18n.recompense.num')
		}, { // --第二步,规定表格title
			header : i18n('i18n.recompense.moreAmount'),
			dataIndex : CONFIGNAME.get('overpayAmount')
		}, {
			header : i18n('i18n.recompense.totalAmount'),
			dataIndex : CONFIGNAME.get('totalAmount')
		}, {
			header : i18n('i18n.recompense.recoverYszk'),
			dataIndex : CONFIGNAME.get('recoverYszk'),
			renderer:function(value){
				return DpUtil.changeTrueAndFalse(value);
			}
		}, {
			header : i18n('i18n.recompense.sectorAccounting'),
			dataIndex : CONFIGNAME.get('overpayAccountDept'),
			renderer:function(value){
				if(Ext.isEmpty(value)){
					return null;
				}else{
					if(Ext.isEmpty(value.empCode)){
						return null;
					}else{
						return value.empCode.empName;
					}
				}
			}
		}, {
			header : i18n('i18n.recompense.overpayBu'),
			dataIndex : CONFIGNAME.get('overpayBu'),
            renderer:function(value){
            	if(Ext.isEmpty(value)){
					return null;
				}else{
					return value.deptName;
				}
            	
			}
		}, {
			header : i18n('i18n.recompense.overpayReason'),
			dataIndex : CONFIGNAME.get('overpayReason')
		}, {
			header : i18n('i18n.recompense.workNumber'),
			dataIndex : 'workflowNo'
		}];
	}
});