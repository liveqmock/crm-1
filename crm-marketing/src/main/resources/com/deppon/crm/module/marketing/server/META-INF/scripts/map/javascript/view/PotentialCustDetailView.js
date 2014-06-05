/**
*  潜客详情
*/
//Data层对象
var potentialCustControl = (CONFIG.get('TEST'))? Ext.create('PotentialCustDataTest'):Ext.create('PotentialCustData');

//加载数据字典
var keys = [
//地图标记
	'BUSINESS_TYPE'
];
//初始化数据字典
initDataDictionary(keys);

//客户编辑界面
Ext.define('PotentialCustDetailWindow',{
	extend:'PopWindow',
	title:i18n('i18n.PotentialCustEditView.potentialCustEditView'),
	tbar:null, 
	potentialCustBasicInfo:null, //客户基本信息
	potentialCustBusinessInfo:null, //客户业务信息
	potentialCustPlanInfo:null,//潜客计划信息（回访，日程）
	items:null,
	width:680,
	height:460,
	layout:'fit',
	viewStatus:null,           //界面查看状态，新增add，修改update，查看view
	potentialCustRecord:null,  //修改或者查看潜在客户时传入的record
	custLabelInfo:null,
	potentialCustData:potentialCustControl,   //Data层接口
	potentialCustManagerView:null,
	isSavePotentialSuccess:false,   //标识是否保存潜在客户成功
	initComponent:function(){
		var me = this;
		//设置tbar  PotentialCustManagerPanel
		me.potentialCustBasicInfo = Ext.create('PotentialCustBasicDetailInfoForm',{'potentialCustData':me.potentialCustData,'parent':me});
		me.potentialCustBusinessInfo = Ext.create('PotentialCustDetailBusinessInfoForm',{'potentialCustData':me.potentialCustData});
		
		me.custLabelInfo = Ext.create('CustLabelInfoForm',{'status':me.viewStatus,'custType':'PC_CUSTOMER','custData':me.potentialCustRecord});
		//设置itemsnew PotentialCustPlanProgrammeGrid()
		me.items = [{
			xtype:'basicformpanel',
			layout:'border',
			items:[{
				xtype:'basicpanel',
				region:'north',
				layout:'fit',
				height:135,
				items:[me.potentialCustBasicInfo]
			},{
				xtype:'basicpanel',
				region:'center',
				layout:'fit',
				items:[me.potentialCustBusinessInfo]
			},{
				xtype:'basicformpanel',
				region:'south',
				layout:'fit',
				height:105,
				items:[me.custLabelInfo]
			}
			]
		}];
		this.on('beforeclose',this.beforeCloseEvent);
		initBasicData(me.viewStatus,'PC_CUSTOMER',me.potentialCustRecord,null,top.User.deptId);
		this.callParent();
		me.controlComponentByViewStatus();
	},
	beforeCloseEvent:function(){
	},
	//通过查看状态设置控件
	controlComponentByViewStatus:function(){
		var basicForm = this.down('basicformpanel');
		if(this.potentialCustRecord == null){
			MessageUtil.showMessage(i18n('i18n.PotentialCustEditView.incomingCustInfo'));
		}else{
			basicForm.getForm().loadRecord(this.potentialCustRecord);
			this.lockAllComponent();
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
	//所有控件解除锁定
	unLockAllComponent:function(){
		var basicForm = this.down('basicformpanel');
		var mainForm = basicForm.getForm();
		mainForm.getFields().each(function(field){
			field.setReadOnly(false);
		});
	}
});

//潜客基本信息
Ext.define('PotentialCustBasicDetailInfoForm',{
	extend:'BasicFormPanel',
	defaultType:'textfield',
	layout:'fit',
	potentialCustData:null,
	viewStatus:null,
	items:null,
	parent:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	//设置items
	getItems:function(){
		var me = this;
		var items = [{
			xtype:'fieldset',
			title:i18n('i18n.PotentialCustEditView.potentialCustInfo'),
			defaults:{
				labelWidth:70,
				width:200
			},
			layout:{
	         	type:'table',    
	         	columns : 3         
	        },
	        defaultType:'textfield',
			items:[{
						fieldLabel:i18n('i18n.PotentialCustManagerView.customerName'),
						name:'custName'
					},{
						fieldLabel:i18n('i18n.PotentialCustManagerView.industry'),
						xtype:'dpcombo',
						store:me.potentialCustData.getIndustryStore(),
						queryMode:'local',
		                forceSelection:true,
						displayField:'codeDesc',
						valueField:'code',
						name:'trade'
					},{
						fieldLabel:i18n('i18n.PotentialCustManagerView.custSource'),
						xtype:'dpcombo',
		                forceSelection:true,
						store:me.potentialCustData.getCustSourceStore(),
						queryMode:'local',
						displayField:'codeDesc',
						valueField:'code',
						name:'custbase'
					},{
						fieldLabel:i18n('i18n.PotentialCustManagerView.contactName'),
						name:'linkManName'
					},{
						fieldLabel:i18n('i18n.PotentialCustManagerView.contactPhone1'),
						name:'linkManMobile'
					},{
						fieldLabel:i18n('i18n.PotentialCustManagerView.contactTel1'),
						name:'linkManPhone'
					},{
						fieldLabel:i18n('i18n.PotentialCustManagerView.position'),
						name:'post'
					},{
						fieldLabel:i18n('i18n.PotentialCustManagerView.bizStatus'),
						xtype:'dpcombo',
						store:me.potentialCustData.getBusinessOpportunityStore(),
						queryMode:'local',
		                forceSelection:true,
						displayField:'codeDesc',
						valueField:'code',
						readOnly:true,
						name:'bussinesState'
					},{
						fieldLabel:i18n('i18n.PotentialCustEditView.city'),
						name:'cityName',
						value:CurrentCity.name,
						readOnly:true
					}
					,{
						fieldLabel:i18n('i18n.PotentialCustEditView.address'),
						colspan:3,
						width:600,
						name:'address'
					},{
						//城市id
						xtype:'hiddenfield',
						name:'city'
					}]
		}];
		return items;
	},
	getCity:function(){
		var me = this;
		var returnValue =  new AreaAddressCombox({
			'id':'city',
			'fieldLabel':i18n('i18n.PotentialCustEditView.city'),
			'labelWidth':70,
			'width':200,
			'minWidth' :200,
			'name':'city',
			'operateType':'VIEW',
			'selectedValue':me.parent.potentialCustRecord.get('city'),
			'tabPanel':['provincePanel','cityPanel']});
		return returnValue;
	}
});

//客户业务信息
Ext.define('PotentialCustDetailBusinessInfoForm',{
	extend:'BasicFormPanel',
	layout:'fit',
	potentialCustData:null,
	items:null,
    initComponent:function(){
	   this.items = this.getItems();
	   this.callParent();
    },
    //设置items
    getItems:function(){
    	var me = this;
	   return [{
			xtype:'fieldset',
			title:i18n('i18n.PotentialCustEditView.recentGoods'),
			defaults:{
				labelWidth:70,
				width:200
			},
			layout:{
	         	type:'table',    
	         	columns : 3         
	        },
	        defaultType:'textfield',
	        items:[{
				fieldLabel:i18n('i18n.PotentialCustEditView.companyName1'),
				xtype:'dpcombo',
				store:me.potentialCustData.getCoopertationCompanyStore(),
				queryMode:'local',
                forceSelection:true,
				displayField:'codeDesc',
				valueField:'code',
				name:'recentcoop'
			},{
				fieldLabel:i18n('i18n.PotentialCustManagerView.coopIntention'),
				xtype:'dpcombo',
				store:me.potentialCustData.getCoopertationIntensionStore(),
				queryMode:'local',
                forceSelection:true,
				displayField:'codeDesc',
				valueField:'code',
				name:'coopIntention'
			},{
				fieldLabel:i18n('i18n.PotentialCustManagerView.goodsPotential'),
				xtype:'dpcombo',
				store:me.potentialCustData.getGoodsPotentialStore(),
				queryMode:'local',
                forceSelection:true,
				displayField:'codeDesc',
				valueField:'code',
				name:'volumePotential'
			},{
				fieldLabel:i18n('i18n.PotentialCustEditView.goodsTrendCondition1'),
				xtype:'textareafield',
				name:'recentGoods'
			},{
				fieldLabel:i18n('i18n.PotentialCustEditView.custNeed'),
				xtype:'textareafield',
				colspan:2,
				width:400,
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
		  }
		];
    }
});

//潜客计划信息（回访，日程）
Ext.define('PotentialCustPlanTab',{
	extend:'Ext.tab.Panel',
	layout:'fit',
	potentialCustData:null,
	items:null,
  initComponent:function(){
	   this.items = this.getItems();
	   this.callParent();
  },
  //设置items
  getItems:function(){
  	var me = this;
	   return [{
 		title : i18n('i18n.PotentialCustDetailView.potentialCustomerReturnVisitRecord'),
		items:[new PotentialCustPlanVisitGrid()]
	},{
 		title : i18n('i18n.PotentialCustDetailView.potentialCustomerScheduleRecord'),
		items:[new PotentialCustPlanProgrammeGrid()]
	}];
  }
});
//潜客计划信息（回访）
Ext.define('PotentialCustPlanVisitGrid',{
	extend:'Ext.grid.Panel',
	layout:'fit',
	height:100,
	potentialCustData:null,
	columns:null,
  initComponent:function(){
	   this.columns = this.getColumns();
	   this.callParent();
  },
  //设置items
  getColumns:function(){
  	var me = this;
		   return [ {
			header : i18n('i18n.PotentialCustManagerView.coopIntention'),
			dataIndex : ''
		}, {
			header : i18n('i18n.ManualRewardIntegralEditView.name'),
			dataIndex : ''
		}, {
			header : i18n('i18n.PotentialCustDetailView.planNum'),
			dataIndex : ''
		}, {
			header : i18n('i18n.PotentialCustDetailView.planName'),
			dataIndex : ''
		}, {
			header : i18n('i18n.PotentialCustManagerView.goodsPotential'),
			dataIndex : ''
		}, {
			header : i18n('i18n.PotentialCustManagerView.bizStatus'),
			dataIndex : ''
		}, {
			header : i18n('i18n.PotentialCustDetailView.executor'),
			dataIndex : ''
		}, {
			header : i18n('i18n.PotentialCustManagerView.finalreviTime'),
			dataIndex : ''
		}, {
			header : i18n('i18n.PotentialCustManagerView.reviTimes'),
			dataIndex : ''
		}, {
			header : i18n('i18n.PotentialCustManagerView.programmeTime'),
			dataIndex : ''
		}, {
			header : i18n('i18n.PotentialCustManagerView.createTime'),
			dataIndex : ''
		}, {
			header : i18n('i18n.PotentialCustManagerView.lastUpdate'),
			dataIndex : ''
		}, {
			header : i18n('i18n.PotentialCustManagerView.lastUpdateTime'),
			dataIndex : ''
		}];
  }
});
//潜客计划信息（日程）
Ext.define('PotentialCustPlanProgrammeGrid',{
	extend:'PopupGridPanel',
	layout:'fit',
	height:100,
	potentialCustData:null,
	columns:null,
  initComponent:function(){
	   this.columns = this.getColumns();
	   this.callParent();
  },
  //设置items
  getColumns:function(){
  	var me = this;
		   return [ {
			header : i18n('i18n.PotentialCustManagerView.contactName'),
			dataIndex : ''
		}, {
			header : i18n('i18n.PotentialCustManagerView.contactPhone1'),
			dataIndex : ''
		}, {
			header : i18n('i18n.PotentialCustManagerView.contactTel1'),
			dataIndex : ''
		}, {
			header : i18n('i18n.PotentialCustDetailView.developmentOfTheme'),
			dataIndex : ''
		}, {
			header : i18n('i18n.PotentialCustDetailView.customerFeedback'),
			dataIndex : ''
		}, {
			header : i18n('i18n.PotentialCustDetailView.developmentTimeInfo'),
			dataIndex : ''
		}, {
			header : i18n('i18n.PotentialCustDetailView.developmentTime'),
			dataIndex : ''
		}, {
			header : i18n('i18n.PotentialCustManagerView.creator'),
			dataIndex : ''
		}, {
			header : i18n('i18n.PotentialCustManagerView.createTime'),
			dataIndex : ''
		}, {
			header : i18n('i18n.PotentialCustManagerView.lastUpdate'),
			dataIndex : ''
		}, {
			header : i18n('i18n.PotentialCustManagerView.lastUpdateTime'),
			dataIndex : ''
		}];
  }
});

