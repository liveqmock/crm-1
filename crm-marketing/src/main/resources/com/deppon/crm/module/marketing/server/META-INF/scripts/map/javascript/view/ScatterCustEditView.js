/**
* 散客编辑界面win										ScatterCustEditWindow
* 散客基本信息form										BasicScatterCustForm
* 散客账号信息panel										ScatterCustAccountPanel
* 散客业务信息form										ScatterBusinessForm
*/

//加载数据字典
var keys = [
//地图标记
	'BUSINESS_TYPE'
];
//初始化数据字典
initDataDictionary(keys);

var scatterCustControl = Ext.create('ScatterCustData');
var memberCustControl = Ext.create('MemberCustData');
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
		//清空 全局变量，联系人偏好地址,所有联系人地址
    	RealTimeVerifyResult = {success:[true,true,true,true,true,true,true,true,true,true,true],
							message:['',[],[],'','','','',[],'','',[]]}
		//编辑界面关闭时重新加载父页面的数据
		if(this.scatterCustManagerView){
			this.scatterCustManagerView.reloadScatterCust(this.scatterCustRecord);
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
			if(this.viewStatus == 'view'){					
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
			text:i18n('i18n.PotentialCustEditView.save')
		},{
			xtype:'button',
			text:i18n('i18n.PotentialCustEditView.cancel')
		}];
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
			title:i18n('i18n.ScatterCustManagerView.scatterInfo1'),
			layout:{
				type:'table',
				columns:3
			},
			defaultType:'dptextfield',
			defaults:{
				labelWidth:75,
				width:210,
				enableKeyEvents:true
			},
			items:[{
					//fieldLabel:'所属部门',
//					xtype:'belongdeptcombox',
					name:'deptId',
//					functionName :'ScatterCustomerFunction',
					fieldLabel:i18n('i18n.ScatterUpgradeView.belongdeptName')+DpUtil.getSpecialStr('redSpan')
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
					blankText:i18n('i18n.ScatterCustManagerView.message_custNameNotNull')
				   },{
					fieldLabel:i18n('i18n.PotentialCustManagerView.contactName')+DpUtil.getSpecialStr('redSpan'),
					name:'linkManName',
					maxLength :40,	
					maxLengthText :i18n('i18n.ManualRewardIntegralEditView.m_maxLength',40),
					allowBlank:false,
					blankText:i18n('i18n.ScatterCustManagerView.message_linkManNameNotNull')
				   },{
					fieldLabel:i18n('i18n.PotentialCustManagerView.contactPhone1')+DpUtil.getSpecialStr('redSpan'),
					name:'linkManMobile',
	   				regex : DpUtil.linkWayLimit('M')
				   },{
                   fieldLabel:i18n('i18n.PotentialCustManagerView.contactTel1')+DpUtil.getSpecialStr('redSpan'),
   				   name:'linkManPhone',
   				   emptyText:('view' != me.viewStatus)?'021-31350606-12':'',
   				   regex : DpUtil.linkWayLimit('T')
                   },{
					fieldLabel:i18n('i18n.ScatterCustManagerView.custIdCradNumber'),
					name:'idNumber',
					minLength:18,
					regex : DpUtil.linkWayLimit('I'),
					regexText:i18n('i18n.ScatterCustManagerView.scatterIdCardWarnning'),
					minLengthText:i18n('i18n.ScatterCustManagerView.scatterIdCardWarnning')
				   },{
					fieldLabel:User.deptCityLocation == "1"?'商业登记号':i18n('i18n.ScatterCustManagerView.taxId'),
					name:'taxregistNo'
				   },{
					fieldLabel:i18n('i18n.ScatterCustManagerView.custProperty'),
					xtype:'dpcombo',
					store:me.scatterCustData.getCustNatureStore(),
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					name:'custProperty'
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
					fieldLabel:i18n('i18n.PotentialCustManagerView.industry'),
					xtype:'dpcombo',
					store:me.scatterCustData.getIndustryStore(),
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					name:'trade'
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
						fieldLabel:i18n('i18n.PotentialCustEditView.city'),
						xtype:'areaaddresscombox',
						name:'city',
						width:210,
						operateType:operateType,
						disabled:('view' == me.viewStatus)?true:false,
						selectedValue:me.parent.scatterCustRecord.get('city'),
						tabPanel:['provincePanel','cityPanel'],
						blankText:i18n('i18n.MemberCustEditView.cityAlert'),
						emptyText:i18n('i18n.MemberCustEditView.cityAlert')
				   },{
					fieldLabel:i18n('i18n.PotentialCustEditView.address'),
					colspan:2,
					width:420,
					maxLength :100,	
					maxLengthText :i18n('i18n.ManualRewardIntegralEditView.m_maxLength',100),
					name:'address'
				   },{
					fieldLabel:i18n('i18n.ScatterCustManagerView.remark'),
					colspan:3,
					width:630,
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
					disabled:!isPermission('/customer/accountRecognizedABtn.action') || 'view' == me.viewStatus?true:false
				},{
					xtype:'button',
					text:i18n('i18n.PotentialCustManagerView.update'),
					disabled:!isPermission('/customer/accountRecognizedABtn.action') || 'view' == me.viewStatus?true:false
				},{
					xtype:'button',
					text:i18n('i18n.PotentialCustManagerView.delete'),
					disabled:!isPermission('/customer/accountRecognizedABtn.action') || 'view' == me.viewStatus?true:false
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
	//获得账号窗体
	getAccountWin:function(memberData,record,viewStatus){
		Ext.create('ScatterCustAccountEditWin',{'accountRecord':record,
											'sourcesChannel':'SCATTERCUST',
										   'viewStatus':viewStatus}).show();
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
			title:i18n('i18n.ScatterCustManagerView.scatterBusinessInfo1'),
			layout:{
				type:'table',
				columns:3
			},
			defaults:{
				labelWidth:70,
				width:210,
				enableKeyEvents:true,
				maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',200),
				maxLength:200
			},
			items:[{
				fieldLabel:i18n('i18n.PotentialCustEditView.companyName1'),
				xtype:'dpcombo',
				store:me.scatterCustData.getCoopertationCompanyStore(),
				queryMode:'local',
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
				fieldLabel:i18n('i18n.PotentialCustEditView.goodsTrendCondition1'),
				height:30,
				xtype:'textareafield',
				name:'recentGoods'
			},{
				fieldLabel:i18n('i18n.PotentialCustEditView.custNeed'),
				xtype:'textareafield',
				colspan:2,
				height:30,
				width:420,
				name:'custNeed'
			},{
				fieldLabel:i18n('i18n.PotentialCustManagerView.businessType'),
				xtype:'combobox',
				store:getDataDictionaryByName(DataDictionary,'BUSINESS_TYPE'),
				queryMode:'local',
	            forceSelection:true,
				displayField:'codeDesc',
				valueField:'code',
				name:'businessType',
				cls:'readonly ',
				readOnly:true
			}]
		}];
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
		basicForm.loadRecord(me.scatterCustRecord);
		basicForm.getForm().findField('deptId').setValue(this.scatterCustRecord.get('deptName'));		//加载散客基本和业务信息
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
//						height:90,
						items:[me.custLabelInfo]
				}]
		 },
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
				  ]
				}
		 ];
	},
	renderGridDescrip:function(rowId){
		if(rowId == '0'){
			return i18n('i18n.ScatterCustManagerView.preMonth');
		}else if(rowId == '1'){
			return i18n('i18n.ScatterCustManagerView.currentMonth');
		}
		return '';
	}
});