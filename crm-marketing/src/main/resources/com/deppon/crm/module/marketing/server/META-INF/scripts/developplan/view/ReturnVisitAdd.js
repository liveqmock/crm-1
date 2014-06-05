/**
 * 回访页面改造
 * 肖红叶
 * 20140319
 */
var keys=[
	// 发货潜力
	'CARGO_POTENTIAL',
	//合作物流
	'COOPERATION_LOGISTICS',
	//客户意见(需求分类)
	'CUSTOMER_IDEA',
	//维护方式
	'MAINTAIN_WAY',
	//预警状态
	'WARNSTATUS',
	//流失原因
	'LOSS_REASON',
	//合作意向'
	'COOPERATION_INTENTION'
];
//初始化业务参数
initDataDictionary(keys);
	
/**
 * 回访页面客户基本信息维护form
 */
Ext.define('CustomerInfoFormPanel',{
	extend:'TitleFormPanel',  
	items:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'basicfiledset',
			title:i18n('i18n.returnVisit.customerInfo'),
			layout:{
				type:'table',
				columns:4
			},
			defaults:{
				labelAlign:'right',
				labelWidth:65,
				width:180,
				margin:'4 5 4 5'
			},
			defaultType:'textfield',
			items:[{//回访时间
				xtype:'datefield',
				id:'executorTime',
				name:'executorTime',
				fieldLabel:i18n('i18n.returnVisit.executorTime'),
				format:'Y-m-d',
				value:new Date(),
				readOnly:true,
				cls:'readonly'
			},{//客户姓名
				fieldLabel:i18n('i18n.returnVisit.psCustomerName'),
				id:'psCustomerName',
				name:'psCustomerName',
				readOnly:true,
				cls:'readonly'
			},{//联系人手机
				fieldLabel:i18n('i18n.returnVisit.linkManMobile'),
				id:'linkManMobile',
				name:'linkManMobile',
				readOnly:true,
				cls:'readonly'
			},{//联系人电话
				fieldLabel:i18n('i18n.returnVisit.linkManPhone'),
				id:'linkManPhone',
				name:'linkManPhone',
				readOnly:true,
				cls:'readonly'
			},{//回访方式
				xtype:'combo',
				fieldLabel:i18n('i18n.returnVisit.wayDesc'),
				id:'way',
				name:'way',
				typeAhead: true,
			    triggerAction: 'all',
			    queryMode: 'local',
			    store: getDataDictionaryByName(DataDictionary,'MAINTAIN_WAY'),
			    valueField: 'code',
			    displayField: 'codeDesc',
				forceSelection :true,
                allowBlank: false,
				listeners:{
					change:DButil.comboSelsct
				}
			},{//联系人姓名
				fieldLabel:i18n('i18n.returnVisit.linkName'),
				id:'linkName',
				name:'linkName',
				readOnly:true,
				cls:'readonly'
			},{//回访依据
				fieldLabel:i18n('i18n.returnVisit.returnVisitDesc'),
				id:'accordingDesc',
				name:'accordingDesc',
				readOnly:true,
				cls:'readonly'
			},{//计划主题
				fieldLabel:i18n('i18n.returnVisit.theme'),
				id:'theme',
				name:'theme',
				readOnly:true,
				cls:'readonly'
			},{
				id:'psCustomerId',
				name:'psCustomerId',
				//fieldLabel:i18n('i18n.Cycle.customerId'),
				hidden:true,
				readOnly:true,
				cls:'readonly'
			},{
				id:'memberId',
				name:'memberId',
				//fieldLabel:i18n('i18n.Cycle.customerId'),
				hidden:true,
				readOnly:true,
				cls:'readonly'
			},{
				id:'according',
				name:'according',
				//fieldLabel:i18n('i18n.ReturnVisitAdd.evidenceId'),
				hidden:true,
				readOnly:true,
				cls:'readonly'
			},{
				id:'linkManId',
				name:'linkManId',
				//fieldLabel:i18n('i18n.ReturnVisitAdd.inkmanId'),
				hidden:true,
				readOnly:true,
				cls:'readonly'
			},{
				id:'planId',
				name:'planId',
				//fieldLabel:i18n('i18n.ReturnVisitAdd.developMain'),
				hidden:true,
				readOnly:true,
				cls:'readonly'
			},{
				id:'scheduleId',
				name:'scheduleId',
				//fieldLabel:i18n('i18n.ReturnVisitAdd.scheDuleId'),
				hidden:true,
				readOnly:true,
				cls:'readonly'
			},{
				xtype:'hiddenfield',
				name:'scheType',
				id:'scheType'
			},{//客户编码
				xtype:'hiddenfield',
				name:'custNumber'
			}]
		}];
	}
});

/**
 * 流失预警客户回访页面中的预警信息form
 * 肖红叶
 * 20140319
 */
Ext.define('LostWarnCustomerInfoFormPanel',{
	extend:'TitleFormPanel',  
	items:null,
	hidden:true,
	height:0,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{//预警信息
			xtype:'basicfiledset',
			title:'预警信息',
			layout:{
				type:'table',
				columns:4
			},
			defaults:{
				labelAlign:'right',
				labelWidth:65,
				width:180,
				margin:'4 5 4 5'
			},
			defaultType:'combobox',
			items:[{//季节性客户
				fieldLabel:'季节性客户',
				name:'isseason',
				store:Ext.create('Ext.data.Store',{
			    	fields:['code','codeDesc'],
			    	data:[
			    	  {code:1,codeDesc:'是'},
			    	  {code:0,codeDesc:'否'}
			    	]
			    }),
			    value:0,
			    allowBlank:false,
			    valueField: 'code',
			    displayField: 'codeDesc',
				forceSelection :true,
			    queryMode: 'local',
				listeners:{
					change:function(th,newValue,oldValue){
						DButil.comboSelsct(th);
						if(newValue === 1){
							//禁用预警状态
							th.up('form').getForm().findField('warnStatus').disable();
							th.up('form').getForm().findField('warnStatus').setValue('PRELOST');
							//启用预发货时限
							th.up('form').getForm().findField('preSendEndTime').enable();
							th.up('form').getForm().findField('preSendBeginTime').enable();
							//当次预警时间
							var warnTime = th.up('form').getForm().findField('warnTime').getValue()
							//当次预警时间前一天
							var preWarnTime = Ext.Date.add(new Date(warnTime),Ext.Date.DAY,-1);
							th.up('form').getForm().findField('preSendBeginTime').setValue(warnTime);
							th.up('form').getForm().findField('preSendBeginTime').setMinValue(warnTime);
							th.up('form').getForm().findField('preSendBeginTime').setMaxValue(Ext.Date.add(new Date(preWarnTime),Ext.Date.YEAR,1));
							th.up('form').getForm().findField('preSendEndTime').setValue();
							th.up('form').getForm().findField('preSendEndTime').setMinValue(warnTime);
							th.up('form').getForm().findField('preSendEndTime').setMaxValue(Ext.Date.add(new Date(preWarnTime),Ext.Date.YEAR,1));
							th.up('form').getForm().findField('preSendBeginTime').clearInvalid();
							th.up('form').getForm().findField('preSendEndTime').clearInvalid();
							//禁用流失原因，隐藏流失原因备注
							th.up('form').getForm().findField('lostCause').disable();
							th.up('form').getForm().findField('lostCause').setValue('');
							th.up('form').getForm().findField('lostCauseRemark').hide();
							th.up('form').getForm().findField('lostCauseRemark').setValue('');
						}else{
							//启用预警状态
							th.up('form').getForm().findField('warnStatus').enable();
							th.up('form').getForm().findField('warnStatus').setValue('PRELOST');
							//禁用预发货时限
							th.up('form').getForm().findField('preSendEndTime').disable();
							th.up('form').getForm().findField('preSendBeginTime').disable();
							th.up('form').getForm().findField('preSendBeginTime').setValue();
							th.up('form').getForm().findField('preSendEndTime').setValue();
							th.up('form').getForm().findField('preSendBeginTime').clearInvalid();
							th.up('form').getForm().findField('preSendEndTime').clearInvalid();
							//禁用流失原因，隐藏流失原因备注
							th.up('form').getForm().findField('lostCause').disable();
							th.up('form').getForm().findField('lostCause').setValue('');
							th.up('form').getForm().findField('lostCauseRemark').hide();
							th.up('form').getForm().findField('lostCauseRemark').setValue('');
						}
					}
				}
			},{//预警状态
				fieldLabel:'预警状态',
				name:'warnStatus',
				value:'PRELOST',
				typeAhead: true,
			    triggerAction: 'all',
			    queryMode: 'local',
			    store: getDataDictionaryByName(DataDictionary,'WARNSTATUS'),
			    valueField: 'code',
			    displayField: 'codeDesc',
				forceSelection :true,
                allowBlank: false,
				listeners:{
					change:function(th,newValue,oldValue){
						DButil.comboSelsct(th);
						if(newValue === 'NORMAL'){
							//启用预发货时限
							th.up('form').getForm().findField('preSendEndTime').enable();
							th.up('form').getForm().findField('preSendBeginTime').disable();
							//当次预警时间
							var warnTime = th.up('form').getForm().findField('warnTime').getValue();
							//最近一次发货时间
							var lastSendTime = th.up('form').getForm().findField('lastSendTime').getValue();
							if(Ext.isEmpty(lastSendTime)){
								th.up('form').getForm().findField('preSendBeginTime').setValue(warnTime);
								th.up('form').getForm().findField('preSendBeginTime').setMinValue(warnTime);
								th.up('form').getForm().findField('preSendBeginTime').setMaxValue();
								th.up('form').getForm().findField('preSendEndTime').setValue();
								th.up('form').getForm().findField('preSendEndTime').setMinValue(warnTime);
								th.up('form').getForm().findField('preSendEndTime').setMaxValue(Ext.Date.add(warnTime,Ext.Date.DAY,59));
							}
							else{
								th.up('form').getForm().findField('preSendBeginTime').setValue(Ext.Date.add(lastSendTime,Ext.Date.DAY,1));
								th.up('form').getForm().findField('preSendBeginTime').setMinValue(lastSendTime);
								th.up('form').getForm().findField('preSendBeginTime').setMaxValue();
								th.up('form').getForm().findField('preSendEndTime').setValue();
								th.up('form').getForm().findField('preSendEndTime').setMinValue(lastSendTime);
								th.up('form').getForm().findField('preSendEndTime').setMaxValue(Ext.Date.add(lastSendTime,Ext.Date.DAY,60));
							}
							
							th.up('form').getForm().findField('preSendBeginTime').clearInvalid();
							th.up('form').getForm().findField('preSendEndTime').clearInvalid();
							//禁用流失原因，隐藏流失原因备注
							th.up('form').getForm().findField('lostCause').disable();
							th.up('form').getForm().findField('lostCause').setValue('');
							th.up('form').getForm().findField('lostCauseRemark').hide();
							th.up('form').getForm().findField('lostCauseRemark').setValue('');
						}else if(newValue === 'LOST'){
							//禁用预发货时限
							th.up('form').getForm().findField('preSendEndTime').disable();
							th.up('form').getForm().findField('preSendBeginTime').disable();
							th.up('form').getForm().findField('preSendBeginTime').setValue();
							th.up('form').getForm().findField('preSendEndTime').setValue();
							th.up('form').getForm().findField('preSendBeginTime').clearInvalid();
							th.up('form').getForm().findField('preSendEndTime').clearInvalid();
							//启用流失原因，隐藏流失原因备注
							th.up('form').getForm().findField('lostCause').enable();
							th.up('form').getForm().findField('lostCause').setValue('');
							th.up('form').getForm().findField('lostCauseRemark').show();
							th.up('form').getForm().findField('lostCauseRemark').setValue('');
						}else{
							//禁用预发货时限
							th.up('form').getForm().findField('preSendEndTime').disable();
							th.up('form').getForm().findField('preSendBeginTime').disable();
							th.up('form').getForm().findField('preSendBeginTime').setValue();
							th.up('form').getForm().findField('preSendEndTime').setValue();
							th.up('form').getForm().findField('preSendBeginTime').clearInvalid();
							th.up('form').getForm().findField('preSendEndTime').clearInvalid();
							//禁用流失原因，隐藏流失原因备注
							th.up('form').getForm().findField('lostCause').disable();
							th.up('form').getForm().findField('lostCause').setValue('');
							th.up('form').getForm().findField('lostCauseRemark').hide();
							th.up('form').getForm().findField('lostCauseRemark').setValue('');
						}
					}
				}
			},{//预发货时限
				xtype:'datefield',
				name:'preSendBeginTime',
				fieldLabel:'预发货时限',
				format:'Y-m-d'
			},{//预发货时限
				xtype:'datefield',
				name:'preSendEndTime',
				labelWidth:5,
				width:120,
				labelSeparator:'',
				fieldLabel:'-',
				format:'Y-m-d'
			},{//流失原因
				fieldLabel:'流失原因',
				name:'lostCause',
				disabled:true,
				typeAhead: true,
			    triggerAction: 'all',
			    queryMode: 'local',
			    store: getDataDictionaryByName(DataDictionary,'LOSS_REASON'),
			    valueField: 'code',
			    displayField: 'codeDesc',
				forceSelection :true,
				listeners:{
					change:DButil.comboSelsct
				}
			},{//流失原因备注
				xtype:'textarea',
				colspan:3,
				emptyText:'请输入具体流失原因',
				height:25,
				fieldLabel:'',
				labelWidth:0,
				width:500,
				name:'lostCauseRemark',
				maxLength:500,
				hidden:true
			},{//最近一次发货时间，隐藏域
				xtype:'datefield',
				name:'lastSendTime',
				fieldLabel:'最近一次发货时间',
				format:'Y-m-d',
				hidden:true
			},{//当次预警时间，隐藏域
				xtype:'datefield',
				name:'warnTime',
				fieldLabel:'当次预警时间',
				format:'Y-m-d',
				hidden:true
			}]
		}];
	}
});

/**
 * 发货潜力combo的Store
 */
Ext.define('VolumePotentialCombobox', {
	extend:'Ext.form.field.ComboBox',
	typeAhead: true,
    triggerAction: 'all',
    queryMode: 'local',
    store: getDataDictionaryByName(DataDictionary,'CARGO_POTENTIAL'),
    valueField: 'code',
    displayField: 'codeDesc',
	forceSelection :true,
	listeners:{
		change:DButil.comboSelsct
	}
});  

/**
 * 合作物流combo的Store
 */
Ext.define('CompanyCombobox', {
	extend:'Ext.form.field.ComboBox',
	typeAhead: true,
    triggerAction: 'all',
    queryMode: 'local',
    store: getDataDictionaryByName(DataDictionary,'COOPERATION_LOGISTICS'),
    valueField: 'code',
    displayField: 'codeDesc',
	forceSelection :true,
	listeners:{
		change:DButil.comboSelsct
	}
}); 
/**
 * 是否已有线路 store
 */
Ext.define('AlreadyLineComboBox', {
	extend:'Ext.form.field.ComboBox',
	typeAhead: true,
    triggerAction: 'all',
    queryMode: 'local',
//    store: getDataDictionaryByName(DataDictionary,'ALREADY_HAVELINE'),
    store:Ext.create('Ext.data.Store',{
    	fields:['code','codeDesc'],
    	data:[
    	  {code:'YES',codeDesc:'是'},
    	  {code:'NO',codeDesc:'否'}
    	]
    }),
    valueField: 'code',
    displayField: 'codeDesc',
	forceSelection :true,
	listeners:{
		change:DButil.comboSelsct
	}
});

/**
 * 走货潜力grid
 */
Ext.define('SendGoodsPontentialGrid', {
	extend:'PopupGridPanel',
	region:'center',
	store :Ext.create('SendGoodsPontentialStore'),
	columnLines:true,
	//单元格编辑插件
	plugins:Ext.create('Ext.grid.plugin.CellEditing', {  
	  	clicksToEdit : 1
	}) ,
	viewConfig: { 
   	 	forceFit:true 
    }, 
	initComponent:function(){
		var me = this;
		//货量潜力下拉框
		var VolumePotentialCombobox = Ext.create('VolumePotentialCombobox');
		//合作物流下拉框
		var CompanyCombobox = Ext.create('CompanyCombobox');
		//是否已有路线下拉框
		var AlreadyLineComboBox = Ext.create('AlreadyLineComboBox');
		me.columns = [{//序号
			xtype:'rownumberer',
			width:40,
			align:'center',
			header:i18n('i18n.Cycle.rownum')
		},{//始发城市
			header : i18n('i18n.returnVisit.comeFromCity'),
			dataIndex : 'comeFromCity',
			align : 'center',
			width : 120,
			sortable : true,
			editor:{
				xtype:'textfield',
				maxLength:15,
				maxLengthText:'不能超过15字！'
			}
		},{//到达城市
			header : i18n('i18n.returnVisit.comeToCity'),
			dataIndex : 'comeToCity',
			align : 'center',
			width : 120,
			sortable : true,
			editor:{
				xtype:'textfield',
				maxLength:15,
				maxLengthText:'不能超过15字！'
			}
		},{//货量潜力
			header : i18n('i18n.returnVisit.volumePotential'),
			dataIndex : 'volumePotential',
			align : 'center',
			width : 120,
			sortable : true,
			field:VolumePotentialCombobox,
			renderer: Ext.util.Format.comboRenderer(VolumePotentialCombobox)
		},{//合作物流公司
			header : i18n('i18n.returnVisit.companyId'),
			dataIndex : 'companyId',
			align : 'center',
			width : 90,
			sortable : true,
			field:CompanyCombobox,
			renderer: Ext.util.Format.comboRenderer(CompanyCombobox)
		},{//是否已有路线
			header:'是否已有路线',
			dataIndex : 'alreadyHaveLine',
			field : AlreadyLineComboBox,
			renderer: Ext.util.Format.comboRenderer(AlreadyLineComboBox),
			width : 90
		},{//合作意向
			header : i18n('i18n.developSchedule.cooperatePurpose'),
			dataIndex : 'cooperate',
			align : 'center',
			width : 90,
			sortable : true,
			editor:{
				xtype:'combo',
				typeAhead: true,
				allowBlank:false,
			    triggerAction: 'all',
			    queryMode: 'local',
			    store:getDataDictionaryByName(DataDictionary,'COOPERATION_INTENTION'),
			    valueField: 'code',
			    displayField: 'codeDesc',
				forceSelection :true
		    },
			renderer: function(value){
				return DButil.rendererDictionary(value,DataDictionary.COOPERATION_INTENTION); 
			}
		},{//适合我司承运
			header : i18n('i18n.businessOpportunity.accord'),
			dataIndex : 'accord',
			align : 'center',
			width : 90,
			sortable : true,
			editor:{
				    xtype:'combo',
					typeAhead: true,
					allowBlank:false,
				    triggerAction: 'all',
				    queryMode: 'local',
				    store:Ext.create('Ext.data.Store',{
				    	fields:['code','codeDesc'],
				    	data:[
				    	  {code:'1',codeDesc:'是'},
				    	  {code:'0',codeDesc:'否'}
				    	]
				    }),
				    valueField: 'code',
				    displayField: 'codeDesc',
					forceSelection :true
			},
			renderer: function(value){
				if(value=='1'){
					return i18n('i18n.ChangeContactAffiliatedRelationView.yes');
				}else if(value=='0'){
					return i18n('i18n.ChangeContactAffiliatedRelationView.no');
				}else{
					return value;
				}
			}
		},{//备注
			header : i18n('i18n.returnVisit.remark'),
			dataIndex : 'remark',
			sortable : true,
			flex:1,
			align : 'center',
			editor:{
				xtype:'textfield',
				maxLength:200,
				maxLengthText:'不能超过200字！'
			}
		}];
		this.callParent();
	}
});

/**
 * 走货潜力grid的Panel
 */
Ext.define('SendGoodsPontentialPanel',{
	extend:'BasicVboxPanel',
	items : null,
	initComponent : function() {
		this.items = this.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [{
			xtype:'toppanel',   
			items:[
			{
				xtype:'titlepanel',  
				flex:1,
				items:[{
					xtype:'displayfield',
					value:i18n('i18n.returnVisit.sendGoodsPontential')
				}]
			},{
				xtype:'btnpanel',  
				defaultType:'button',
				items:[{
					text:i18n('i18n.returnVisit.add'),
					id:'SendGoodsAddBtn',
					handler:function(){
						var panelStore = Ext.getCmp('sendGoodsPontentialGrid').getStore();
	               	 	var panelModel = Ext.getCmp('sendGoodsPontentialGrid').store.model.modelName;
	               	 	panelStore.add(Ext.create(panelModel));
					}
				},{
					text:i18n('i18n.returnVisit.delete'),
					id:'SendGoodsDeleteBtn',
					handler:function(){
						var selection = Ext.getCmp('sendGoodsPontentialGrid').getSelectionModel().getSelection()[0];;
			            var panelStore = Ext.getCmp('sendGoodsPontentialGrid').getStore();
			            if (selection) {
			            	MessageUtil.showQuestionMes(i18n('i18n.developSchedule.isdelete'), function(e) {
			            		if (e == 'yes') {
			            			panelStore.remove(selection);
			            		}
			            	});
			            }	
			      	}
				}]
			}] 
		},{
			xtype:'basicpanel',     
			flex:1,
			items:[Ext.create('SendGoodsPontentialGrid',{'id':'sendGoodsPontentialGrid'})]
		}
	  ];
	}
});

/**
 * 需求分类combo的Store
 */
Ext.define('OpinionTypeCombobox', {
	extend:'Ext.form.field.ComboBox',
	typeAhead: true,
    triggerAction: 'all',
    queryMode: 'local',
    store: getDataDictionaryByName(DataDictionary,'CUSTOMER_IDEA'),
    valueField: 'code',
    displayField: 'codeDesc',
	forceSelection :true,
	listeners:{
		change:DButil.comboSelsct
	}
});  

/**
 * 客户意见grid
 */
Ext.define('CustomerOpinionGrid', {
	extend:'PopupGridPanel',
	region:'center',
	store :Ext.create('CustomerOpinionStore',{'id':'customerOpinionStore'}),
	columnLines:true,
	plugins:Ext.create('Ext.grid.plugin.CellEditing', {  
		clicksToEdit : 1
	}),
	viewConfig: { 
   	 	forceFit:true 
    }, 
	initComponent:function(){
		var me = this;
		//需求分类
		var OpinionTypeCombobox = Ext.create('OpinionTypeCombobox');
		me.columns = [{//序号
			xtype:'rownumberer',
			width:40,
			align:'center',
			header:i18n('i18n.Cycle.rownum')
		},{//需求分类
			header : i18n('i18n.returnVisit.opinionType'),
			dataIndex : 'opinionType',
			align : 'center',
			width : 95,
			sortable : true,
			field:OpinionTypeCombobox,
			renderer: Ext.util.Format.comboRenderer(OpinionTypeCombobox)
		},{//需求及问题
			header : i18n('i18n.returnVisit.problem'),
			dataIndex : 'problem',
			align : 'center',
			width : 290,
			sortable : true,
			editor:{
				xtype:'textfield',
				maxLength:'100',
				maxLengthText:'不能超过100字！'
			}
		},{//需求问题解决办法
			header : i18n('i18n.returnVisit.solution'),
			dataIndex : 'solution',
			align : 'center',
			width : 372,
			sortable : true,
			editor:{
				xtype:'textfield',
				maxLength:'200',
				maxLengthText:'不能超过200字！'
			}
		}];
		this.callParent();
	}
});

/**
 * 客户意见panel
 */
Ext.define('CustomerOpinionPanel',{
	extend:'BasicVboxPanel',
	items : null,
	initComponent : function() {
		this.items = this.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [{
			xtype:'toppanel',   
			items:[
			{
				xtype:'titlepanel',  
				flex:1,
				items:[{
					xtype:'displayfield',
					//客户意见栏更名为客户需求  2012-10-19
					//value:i18n('i18n.returnVisit.customerOpinion')
					value:i18n('i18n.PotentialCustEditView.custNeed')
				}]
			},{
				xtype:'btnpanel',  
				defaultType:'button',
				items:[{
					text:i18n('i18n.returnVisit.add'),
					id:'CustomerOpinionAddBtn',
					handler:function(){
						var panelStore = Ext.getCmp('customerOpinionGrid').getStore();
						if(panelStore.data.length>=10){
							MessageUtil.showErrorMes(i18n('i18n.returnVisit.lessThenTen'));
							return false;
						}
	               	 	var panelModel = Ext.getCmp('customerOpinionGrid').store.model.modelName;
	               	 	panelStore.add(Ext.create(panelModel));
					}
				},{
					text:i18n('i18n.returnVisit.delete'),
					id:'CustomerOpinionDeleteBtn',
					handler:function(){
						var selection = Ext.getCmp('customerOpinionGrid').getSelectionModel().getSelection()[0];;
			            var panelStore = Ext.getCmp('customerOpinionGrid').getStore();
			            if (selection) {
			            	MessageUtil.showQuestionMes(i18n('i18n.developSchedule.isdelete'), function(e) {
			            		if (e == 'yes') {
			            			panelStore.remove(selection);
			            		}
			            	});
			            }	
			      	}
				}]
			}] 
		},{
			xtype:'basicpanel',     
			flex:1,
			items:[Ext.create('CustomerOpinionGrid',{'id':'customerOpinionGrid'})]
		}
	  ];
	}
});

/**
 * button区域
 */
Ext.define('ReturnVisitButtonPanel',{
	extend:'PopButtonPanel', 
	items:null,
	region:'south',
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'middlebuttonpanel' 
		},{
			xtype:'poprightbuttonpanel', 
			width:400,
			items:[
			       //暂时去掉回访记录查询CRM-1663 
//			       { 
//				xtype:'button',
//				text:i18n('i18n.returnVisit.returnVisitDetail'),
//				handler:function(){
//					Ext.getCmp('returnVisitDetailGird').store.loadPage(1);
//					win.show();
//				}
//			}
//			       ,
			{ //保存
				xtype:'button',
				text:i18n('i18n.returnVisit.save'),
				handler:function(t){
					t.disable();
					if(!Ext.getCmp('schedule').isValid()){
						t.enable();
						return;
					}
					//校验客户信息维护信息正确性
					var CustomerInfoFormPanel = Ext.getCmp('customerInfoFormPanel');
					if(!CustomerInfoFormPanel.getForm().isValid()){
						t.enable();
						MessageUtil.showErrorMes("回访方式不能为空！");
						return false;
					}
					//跟踪日程制定
					var ScheduleMakeForm = Ext.getCmp('scheduleMakeForm');
					//如果回访类型非问卷
				    if(ScheduleMakeForm.getForm().findField('returnVisitTypeForWhole').getValue() != 'QUESTIONNAIRE'){
				    	var customerOpinionGrid = Ext.getCmp('customerOpinionGrid');
						var customerOpinionStore = customerOpinionGrid.store;
						customerOpinionStore.each(function(record){
							//删除空数据
							if(record.data.opinionType=="" && record.data.problem=="" && record.data.solution ==""){
								customerOpinionStore.remove(record);
							}
						});
						//走货潜力
						var sendGoodsPontentialGrid = Ext.getCmp('sendGoodsPontentialGrid')
						var bj=false;
						var j=1;
						sendGoodsPontentialGrid.store.each(function(record){
							//删除空数据
							if(record.data.comeFromCity=="" && record.data.comeToCity=="" && record.data.volumePotential ==""
								&& record.data.companyId =="" && record.data.remark =="" && record.data.cooperate ==""){
								sendGoodsPontentialGrid.store.remove(record);
							}else{
								if(record.data.comeFromCity.length>15){
									t.enable();
									bj=true;
									MessageUtil.showInfoMes("第"+j+"行中“始发城市”输入字符过长，最多输入15个字！");
									return false;
								}
								if(record.data.comeToCity.length>15){
									t.enable();
									bj=true;
									MessageUtil.showInfoMes("第"+j+"行中“到达城市”输入字符过长，最多输入15个字！");
									return false;
								}
								if(record.data.accord==0&&Ext.isEmpty(record.data.remark)){
									t.enable();
									bj=true;
									MessageUtil.showInfoMes("第"+j+"行中“适合我司承运”为否，“备注”必填！");
									return false;
								}
								if(record.data.remark.length>200){
									bj=true;
									t.enable();
									MessageUtil.showInfoMes("第"+j+"行中“备注”输入字符过长，最多输入200个字！");
									return false;
								}
								j++;
							}
						});
						//校验客户意见，至少添加一条
						if(customerOpinionStore.data.length==0){
							t.enable();
							MessageUtil.showMessage(i18n('i18n.returnVisit.moreThenOne'));
							return false;
						}
						var i=1;
						customerOpinionStore.each(function(record){
							if(Ext.isEmpty(record.data.opinionType)){
								bj=true;
								t.enable();
								MessageUtil.showErrorMes("第"+i+"行中“需求分类”不能为空！");
								return false;
							}
							if(record.data.problem.length===0){
								bj=true;
								t.enable();
								MessageUtil.showErrorMes("第"+i+"行中“需求及问题”不能为空！");
								return false;
							}
							if(record.data.problem.length>100){
								bj=true;
								t.enable();
								MessageUtil.showErrorMes("第"+i+"行中“需求及问题”输入过长，最多输入100个字！");
								return false;
							}
							if(record.data.solution.length===0){
								bj=true;
								t.enable();
								MessageUtil.showErrorMes("第"+i+"行中“需求问题解决办法”不能为空！");
								return false;
							}
							if(record.data.solution.length>200){
								bj=true;
								t.enable();
								MessageUtil.showErrorMes("第"+i+"行中“需求问题解决办法”输入过长，最多输入200个字！");
								return false;
							}
							i++;
					    });
						if(bj){
							return false;
						}
				    }
					var successFn = function(json){
						t.enable();
						//如果回访类型非问卷
					    if(ScheduleMakeForm.getForm().findField('returnVisitTypeForWhole').getValue() != 'QUESTIONNAIRE'){
					    	//清空走货潜力表格
					    	sendGoodsPontentialGrid.store.removeAll();
					    	//清空客户意见表格
					    	customerOpinionGrid.store.removeAll();
					    }
				    	//清空跟踪时间和跟踪方式
				    	Ext.getCmp('schedule').setValue(null);
				    	//Ext.getCmp('traceWay').setValue(null);
				    	//关闭窗口
				    	Ext.getCmp('CreateDevelopPopWindowId').close();	
				    	//如果不是在可视化营销界面
				    	if(!Ext.isEmpty(Ext.getCmp('customerDevelopPlaneGird'))){
				    		//Ext.data.StoreManager.lookup('developScheduleGirdStore').loadPage(1);
				    		// 回访保存成功，刷新日程Grid By ZPJ 20120615 add
							var grid = Ext.getCmp('customerDevelopPlaneGird');
							//获取store中数据
							var	store = grid.getStore();
							store.load();
				    	}
				    	//如果在可视化营销界面
				    	else if(!Ext.isEmpty(Ext.getCmp('returnVisitTabGridId'))){
				    		if(!Ext.isEmpty(Ext.getCmp('custIdForReturnVisitPanel')) && 
				    				!Ext.isEmpty(Ext.getCmp('custTypeForReturnVisitPanel'))){
				    			Ext.getCmp('returnVisitTabGridId').store.load();
				    		}	
				    	}
                        //修改developScheduleGirdStore 为  customerDevelopPlaneGird by 石应华 20130827 
                        //如果是开发日程/维护日程新增页面，则刷新该页面的grid
                        else if(!Ext.isEmpty(Ext.getCmp('searchResultGridId'))){
                            Ext.getCmp('searchResultGridId').getStore().load();
                        }
						MessageUtil.showInfoMes("保存成功！");
				    };
				    var failureFn = function(json){
				    	t.enable();
				    	MessageUtil.showErrorMes(json.message);
				    };
				   
				    //客户意见
				    var opinionTypes = new Array();
				    //走货潜力
					var goodsPontentials = new Array();
					 //如果回访类型非问卷
				    if(ScheduleMakeForm.getForm().findField('returnVisitTypeForWhole').getValue() != 'QUESTIONNAIRE'){
				    	customerOpinionGrid.store.each(function(record){
					    	opinionTypes.push(record.data);
					    });
					    sendGoodsPontentialGrid.store.each(function(record){
					    	goodsPontentials.push(record.data);
					    });
				    }
				    
				    //回访问卷客户答案
				    var answerMainInfoList =  new Array();
				    //预警信息实体
				    var lostWarnInfo = null;
				    //是否可以点击保存按钮
				    var canNotSubmit = false;
				    //如果回访类型为问卷回访
				    if(ScheduleMakeForm.getForm().findField('returnVisitTypeForWhole').getValue() === 'QUESTIONNAIRE'){
				    	//问卷id
				    	var questionnaireId = ScheduleMakeForm.getForm().findField('questionnaireId').getValue();
				    	//客户id
				    	var memberId = CustomerInfoFormPanel.getForm().findField('memberId').getValue();
				    	//问卷问题列表
				    	var questionnaireQuestionListGrid = Ext.getCmp('questionnaireQuestionListGridId');
				    	//问卷问题列表的store
				    	var questionnaireQuestionListStore = questionnaireQuestionListGrid.store;
				    	questionnaireQuestionListStore.each(function(record){
							//问题id
				    		var questionId = record.get('id');
				    		//回访问卷客户答案主信息model
				    		var answerMainInfoModel = new AnswerMainInfoModel();
				    		answerMainInfoModel.set('questionnaireId',questionnaireId);
				    		answerMainInfoModel.set('questionId',questionId);
				    		answerMainInfoModel.set('custId',memberId);
				    		//问题类型
				    		var questionType = record.get('questionType');
				    		//回访问卷客户答案明细信息列表
				    		var answerDetailInfoList =  new Array();
				    		//如果问题类型为选择题
				    		if(questionType != 'QUESTION_ANSWER'){
				    			//选中选项的个数
				    			var checkedOptionCount = 0;
				    			var options = document.getElementsByName(questionId);
			    	            for(var i=0;i<options.length;i++){             
			    	              if(options[i].checked){
			    	            	  checkedOptionCount = checkedOptionCount + 1;
			    	            	  //回访问卷客户答案明细model
			    	            	  var answerDetailInfoModel = new AnswerDetailInfoModel();
			    	            	  answerDetailInfoModel.set('answer',options[i].value);
			    	            	  
			    	            	  if(!Ext.isEmpty(Ext.getCmp(options[i].value+'Else'))){
			    	            		  var answerRemark = Ext.getCmp(options[i].value+'Else');
			    	            		  if(!answerRemark.isValid()){
			    	            			  t.enable();
			    	            			  canNotSubmit = true;
			    	            			  return false;
			    	            		  }
			    	            		  
			    	            		  if(Ext.isEmpty(answerRemark.getValue()) ||
			    	            				  Ext.isEmpty(Ext.String.trim(answerRemark.getValue()))){
			    	            			  t.enable();
			    	            			  canNotSubmit = true;
			    	            			  MessageUtil.showErrorMes('第' + record.get('questionSeq') + '题选项如果选择其他，则必须输入其他具体答案！');
			    	            			  return false;
			    	            		  }
			    	            		  answerDetailInfoModel.set('answerRemark',answerRemark.getValue());
			    	            	  }
			    	            	  answerDetailInfoList.push(answerDetailInfoModel.data);
			    	              }
			    	            }
			    	            if(checkedOptionCount < 1){
			    	            	t.enable();
			    	            	canNotSubmit = true;
			    	            	MessageUtil.showErrorMes('第' + record.get('questionSeq') + '题选项不能为空！');
			    	            	return false;
			    	            }
				    		}
				    		//如果问题类型为简答题
				    		else{
				    			//回访问卷客户答案明细model
		    	            	var answerDetailInfoModel = new AnswerDetailInfoModel();
		    	            	if(!Ext.isEmpty(Ext.getCmp(questionId))){
		    	            		  var answerRemark = Ext.getCmp(questionId);
		    	            		  if(!answerRemark.isValid()){
		    	            			  t.enable();
		    	            			  canNotSubmit = true;
		    	            			  return false;
		    	            		  }
		    	            		  if(Ext.isEmpty(answerRemark.getValue()) ||
		    	            				  Ext.isEmpty(Ext.String.trim(answerRemark.getValue()))){
		    	            			  t.enable();
		    	            			  canNotSubmit = true;
		    	            			  MessageUtil.showErrorMes('第' + record.get('questionSeq') + '题答案不能为空！');
		    	            			  return false;
		    	            		  }
		    	            		  answerDetailInfoModel.set('answerRemark',answerRemark.getValue());
		    	            	}
		    	            	
		    	            	answerDetailInfoList.push(answerDetailInfoModel.data);
				    		}
				    		answerMainInfoModel.set('answerList',answerDetailInfoList);
				    		answerMainInfoList.push(answerMainInfoModel.data);
						});
				    }
				    //如果回访类型为流失预警
				    else if(ScheduleMakeForm.getForm().findField('returnVisitTypeForWhole').getValue() === 'LOSTWARN'){
				    	//流失预警信息panel
				    	var lostWarnCustomerInfoFormPanel = Ext.getCmp('lostWarnCustomerInfoFormPanelId').getForm();
				    	if(!lostWarnCustomerInfoFormPanel.isValid()){
				    		t.enable();
            			  	canNotSubmit = true;
            			  	return false;
				    	}
				    	//是否季节性客户
				    	var isseason = lostWarnCustomerInfoFormPanel.findField('isseason').getValue();
				    	//预警状态
				    	var warnStatus = lostWarnCustomerInfoFormPanel.findField('warnStatus').getValue();
				    	//预发货开始时间
				    	var preSendBeginTime = lostWarnCustomerInfoFormPanel.findField('preSendBeginTime').getValue();
				    	//预发货结束时间
				    	var preSendEndTime = lostWarnCustomerInfoFormPanel.findField('preSendEndTime').getValue();
				    	//流失原因
				    	var lostCause = lostWarnCustomerInfoFormPanel.findField('lostCause').getValue();
				    	//流失原因备注
				    	var lostCauseRemark = lostWarnCustomerInfoFormPanel.findField('lostCauseRemark').getValue();
				    	//最近一次发货时间
				    	var lastSendTime = lostWarnCustomerInfoFormPanel.findField('lastSendTime').getValue();
				    	//最近一次发货时间string
				    	var lastSendTimeRaw = lostWarnCustomerInfoFormPanel.findField('lastSendTime').getRawValue();
				    	if(isseason === 1){
				    		//预发货开始时间与预发货结束时间不可为空
				    		if(Ext.isEmpty(preSendBeginTime) || Ext.isEmpty(preSendEndTime)){
				    			t.enable();
  	            			  	canNotSubmit = true;
  	            			  	MessageUtil.showErrorMes('季节性客户的预发货时限不可为空！');
  	            			  	return false;
				    		}
				    		
				    		if(dateRangeNotValid(preSendBeginTime,preSendEndTime,3,'MONTH')){
				    			t.enable();
  	            			  	canNotSubmit = true;
				    			MessageUtil.showErrorMes('季节性客户的预发货开始时间不能晚于预发货结束时间,且预发货时限不能超过3个月！');
								return false;
					    	}
				    	}
				    	if(isseason === 0 && warnStatus === 'NORMAL'){
				    		//预发货开始时间与预发货结束时间不可为空
				    		if(Ext.isEmpty(preSendBeginTime) || Ext.isEmpty(preSendEndTime)){
				    			t.enable();
  	            			  	canNotSubmit = true;
  	            			  	MessageUtil.showErrorMes('正常状态的非季节性固定客户预发货时限不可为空！');
  	            			  	return false;
				    		}
				    		
				    		if(dateRangeNotValid(preSendBeginTime,preSendEndTime,1,'YEAR')){
				    			t.enable();
  	            			  	canNotSubmit = true;
				    			MessageUtil.showErrorMes('正常状态的非季节性固定客户的预发货开始时间不能晚于预发货结束时间！');
								return false;
					    	}
				    		
				    		if(dateRangeNotValid(lastSendTime,preSendEndTime,60,'DAY')){
				    			t.enable();
  	            			  	canNotSubmit = true;
				    			MessageUtil.showErrorMes('正常状态的非季节性固定客户预发货结束时间与最近一次发货时间'+lastSendTimeRaw+'之间的时间间隔不能超过60天！');
								return false;
					    	}
				    	}
				    	if(isseason === 0 && warnStatus === 'LOST'){
				    		if(Ext.isEmpty(lostCause)){
				    			t.enable();
  	            			  	canNotSubmit = true;
				    			MessageUtil.showErrorMes('流失状态的非季节性固定客户必须填写流失原因！');
								return false;
				    		}
				    		
				    		if(lostCause === 'OTHERS' && (Ext.isEmpty(lostCauseRemark) ||
				    				Ext.isEmpty(Ext.String.trim(lostCauseRemark)))){
				    			t.enable();
  	            			  	canNotSubmit = true;
				    			MessageUtil.showErrorMes('请输入导致固定客户流失的其他具体原因！');
								return false;
				    		}
				    	}
				    	//客户编码
				    	var custNumber = CustomerInfoFormPanel.getForm().findField('custNumber').getValue();
				    	//流失预警信息model
				    	var lostWarnCustInfoModel = new LostWarnCustInfoModel();
				    	lostWarnCustInfoModel.set('isseason',lostWarnCustomerInfoFormPanel.findField('isseason').getValue());
				    	lostWarnCustInfoModel.set('warnStatus',lostWarnCustomerInfoFormPanel.findField('warnStatus').getValue());
				    	lostWarnCustInfoModel.set('preSendBeginTime',lostWarnCustomerInfoFormPanel.findField('preSendBeginTime').getValue());
				    	lostWarnCustInfoModel.set('preSendEndTime',lostWarnCustomerInfoFormPanel.findField('preSendEndTime').getValue());
				    	lostWarnCustInfoModel.set('lostCause',lostWarnCustomerInfoFormPanel.findField('lostCause').getValue());
				    	lostWarnCustInfoModel.set('lostCauseRemark',lostWarnCustomerInfoFormPanel.findField('lostCauseRemark').getValue());
				    	lostWarnCustInfoModel.set('lastSendTime',lostWarnCustomerInfoFormPanel.findField('lastSendTime').getValue());
				    	lostWarnCustInfoModel.set('warnTime',lostWarnCustomerInfoFormPanel.findField('warnTime').getValue());
				    	lostWarnCustInfoModel.set('custNumber',custNumber);
				    	lostWarnInfo = lostWarnCustInfoModel.data;
				    }
				    //验证所有条件是否都校验通过
				    if(canNotSubmit){
				    	return false;
				    }
				    var param = {
				    		'returnVisit':{
				    			'executorTime':Ext.isEmpty(CustomerInfoFormPanel.getForm().findField('executorTime').getValue())==true?CustomerInfoFormPanel.getForm().findField('executorTime').getValue():CustomerInfoFormPanel.getForm().findField('executorTime').getValue().getTime(),
				    			'linkManPhone':CustomerInfoFormPanel.getForm().findField('linkManPhone').getValue(),
						    	'linkManMobile':CustomerInfoFormPanel.getForm().findField('linkManMobile').getValue(),
						    	'psCustomerId':CustomerInfoFormPanel.getForm().findField('psCustomerId').getValue(),
						    	'according':CustomerInfoFormPanel.getForm().findField('according').getValue(),
						    	'way':CustomerInfoFormPanel.getForm().findField('way').getValue(),
						    	'memberId':Ext.getCmp('memberId').getValue(),
						    	//'way':CustomerInfoFormPanel.getForm().findField('way').getValue(),
						    	'linkManId':CustomerInfoFormPanel.getForm().findField('linkManId').getValue(),
						   	    'linkName':CustomerInfoFormPanel.getForm().findField('linkName').getValue(),
						    	'planId':CustomerInfoFormPanel.getForm().findField('planId').getValue(),
						    	'scheduleId':CustomerInfoFormPanel.getForm().findField('scheduleId').getValue(),
						    	'scheType':CustomerInfoFormPanel.getForm().findField('scheType').getValue(),
						    	//跟踪日程制定信息
						    	'schedule':Ext.isEmpty(ScheduleMakeForm.getForm().findField('schedule').getValue())==true?ScheduleMakeForm.getForm().findField('schedule').getValue():ScheduleMakeForm.getForm().findField('schedule').getValue().getTime(),
						    	//'traceWay':ScheduleMakeForm.getForm().findField('traceWay').getValue(),
						    	'executorId':ScheduleMakeForm.getForm().findField('executorId').getValue(),
						    	'executeDeptId':ScheduleMakeForm.getForm().findField('executeDeptId').getValue(),
						    	'continueMarket':ScheduleMakeForm.getForm().findField('continueMarket').getValue()
				    		},
				    		'potentialList':goodsPontentials,
			    			//客户意见
			    			'opinionList':opinionTypes,
			    			'answerMainInfoList':answerMainInfoList,
			    			'lostWarnInfo':lostWarnInfo
				    };
				    //t.disable();
	    			ReturnVisitData.prototype.saveReturnVisit(param, successFn, failureFn);
				}
			},{//关闭
				xtype : 'button',
				text : i18n('i18n.DevelopManageView.close'),
				width : 70,
				handler : function(){
					Ext.getCmp("CreateDevelopPopWindowId").close();
				}
			}]
		}];
	}
});
/**
 * 跟踪日程制定form
 */
Ext.define('ScheduleMakeForm',{
	extend:'TitleFormPanel',
	items:null,
	layout:'border',
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me =this;
		return [{
			xtype:'basicfiledset',
			region:'center',
			title:i18n('i18n.returnVisit.scheduleSave'),
			height:100,
			layout:{
				type:'table',
				columns:3
			},
			defaults:{
				labelAlign:'right',
				labelWidth:80,
				width:250,
				margin:'4 5 4 5'
			},
			defaultType:'textfield',
			items:[{//是否制定跟踪日程
            	width:160,
                xtype:          'combo',
                mode:           'local',
                triggerAction:  'all',
                fieldLabel:i18n('i18n.developPlan.makeschedule'),
                name:'ifparent',
                id:'ifparent',
				displayField:'value',
				valueField:'name',
				forceSelection :true,
				editable:false,
				value:'0',
                store:Ext.create('Ext.data.Store', {
                    fields : ['name', 'value'],
                    data   : [
                        {name : '0',  value: '否'},
                        {name : '1',  value:'是'}
                    ]
                }),
                listeners:{
                	select:function(t){
                		if(t.getValue()=='1'){
                			Ext.getCmp("schedule").enable();
                		}else{
                			Ext.getCmp("schedule").setValue("");
                			Ext.getCmp("schedule").disable();
                			Ext.getCmp('schedule').clearInvalid();
                		}
                	}
                }
            },{//日程时间
				fieldLabel:i18n('i18n.returnVisit.schedule'),
				xtype:'datefield',
				id:'schedule',
				//disabled:true,
				width:180,
				editable:false,
				format:'Y-m-d',
				minValue : new Date(),
				name:'schedule',
				allowBlank:false
			},{//继续营销
            	width:160,
                xtype:          'combo',
                mode:           'local',
                triggerAction:  'all',
                fieldLabel:i18n('i18n.developPlan.continueMark'),
                name:'continueMarket',
                id : 'continueMarket',
				displayField:'value',
				valueField:'name',
				forceSelection :true,
				editable:false,
				value:'1',
                store:Ext.create('Ext.data.Store', {
                    fields : ['name', 'value'],
                    data   : [
                        {name : '0',  value: '否'},
                        {name : '1',  value:'是'}
                    ]
                }),
            },{//执行部门名称
				fieldLabel:i18n('i18n.returnVisit.departName'),
				id:'departName',
				name:'departName',
				readOnly:true,
				cls:'readonly'
			},{//执行人名称
				fieldLabel:i18n('i18n.returnVisit.userName'),
				id:'userName',
				name:'userName',
				readOnly:true,
				cls:'readonly'
			},{
				//fieldLabel:i18n('i18n.ReturnVisitAdd.executeUserId'),
				id:'executorId',
				name:'executorId',
				hidden:true
			},{
				//fieldLabel:i18n('i18n.ReturnVisitAdd.executeDeptId'),
				id:'executeDeptId',
				name:'executeDeptId',
				hidden:true
			},{//问卷id
				xtype:'hiddenfield',
				name:'questionnaireId'
			},{//回访类型
				xtype:'hiddenfield',
				name:'returnVisitTypeForWhole'
			}]
		}
		,Ext.create('ReturnVisitButtonPanel')
		]
	}
});

/**
 * 问卷信息列表
 * 2014-3-6
 * 肖红叶
 */
Ext.define('QuestionnaireQuestionListGrid',{
	extend:'PopupGridPanel', 
	title:'问卷信息',
	columns:null,
	store:null,
	defaults:{
		align:'center'
	},
	initComponent:function(){             
		var me = this;
		var store = Ext.create('QuestionInfoListStore');
		me.store = store;
		me.columns = [{//问题id 
			header : '问题列表',
			align:'left',
			flex:1,
			dataIndex:'id',
			renderer:function(val,metaData,record){
				//根据问题类型，显示相应的中文
				var questionType = optionInfoConvert(record.get('questionType'));
				if(!Ext.isEmpty(record)){
					//单选题与多选题的显示样式转化
					if(record.get('questionType') != 'QUESTION_ANSWER'){
						//记录有多少个选项
						var length = 0;
						if(!Ext.isEmpty(record.optionsStore) && !Ext.isEmpty(record.optionsStore.data)){
							length = record.optionsStore.data.length;
						}
						var tableString = '<div align = "left"><table>';
						for(var i = 0;i < length;i++){
							//获得选项数据
							var optionsData = record.optionsStore.data.items[i].data
							//根据问题类型、问题id和选项id显示适当的表单符号
							var optionDisplay = optionFormDisplayConvert(record.get('questionType'),record.get('id'),optionsData.id);
							//根据问题选项编号转换英文显示
							var optionSeq = optionCodeConvert(optionsData.optionSeq);
							var optionDes = optionsData.optionDes;
							var j = optionDes.length;
							var showString = "";
							while(j > 50){
								showString=showString+optionDes.substring(0,50)+'<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
								optionDes = optionDes.substring(50,j);
								j =j - 50;
							}
							showString = showString + optionDes;
							if(optionsData.optionDes !== '其他'){
								tableString = tableString + '<tr><td height="25">&nbsp;&nbsp;'+optionDisplay+'&nbsp;&nbsp;'+optionSeq+'&nbsp;&nbsp;'+showString
								+'</td></tr>';
							}
							else{
								tableString = tableString + '<tr><td height="25"><div style="display:inline;width:80px ;float:left">&nbsp;&nbsp;'+optionDisplay+'&nbsp;&nbsp;'+optionSeq+'&nbsp;&nbsp;'+optionsData.optionDes
								+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><div id="'+optionsData.id+'Else" style="display:inline;width:550px;float:left"></div></td></tr>';
								setTimeout(function(){
									Ext.create('Ext.form.field.Text',{
										id:optionsData.id+'Else',
										width:550,
										maxLength:100,
										maxLengthText:'输入答案不能超过100字！',
										emptyText:'请输入其他答案...',
										hidden:true,
										renderTo:optionsData.id+'Else'
									});   		        		
								},1000);
							}
						}
						tableString = tableString + '</table></div>';
					}
					else{//简答题的显示方式
						setTimeout(function(){
							Ext.create('Ext.form.field.TextArea',{
								id:record.get('id'),
								width:650,
								height:100,
								maxLength:300,
								maxLengthText:'输入答案不能超过300字！',
								renderTo:record.get('id')
							});   		        		
						},1000);
						var tableString = '<div align = "left"><table>';
						tableString = tableString + '<tr><td height="125">&nbsp;&nbsp;<div id="'+record.get('id')+'"></div></td></tr></table></div>';
					}
				}
				//格式化结果显示
				var questionTitle =record.get('questionTitle');
				var k = questionTitle.length;
				var title = "";//问题标题临时变量
				while(k > 50){
					title=title+questionTitle.substring(0,50)+'<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
					questionTitle = questionTitle.substring(50,k);
					k =k - 50;
				}
				title = title + questionTitle;
				return	Ext.String.format(
					'<br><p><b>{0}、{1}({2})</b></p><br>'+tableString+'<br>',
					//传入的参数列表
					record.get('questionSeq'),
					title,
					questionType
				);
			}
		}];
		this.callParent();
	}
});

/**
 * 回访添加页面整体布局panel
 */
Ext.define('CustomerReturnVisitPanel', {
	extend : 'BasicPanel',
	//回访类型 NORMAL或者空表示普通回访，LOSTWARN表示流失预警回访，QUESTIONNAIRE表示问卷回访
	returnVisitType:null,
	//回访客户基本信息panel
	customerInfoFormPanel:null,
	//客户需求grid
	customerOpinionPanel:null,
	//走货潜力grid
	sendGoodsPontentialPanel:null,
	//跟踪日程制定panel
	scheduleMakeForm:null,
	//流失预警客户回访信息panel
	lostWarnCustomerInfoFormPanel:null,
	//问卷信息panel
	questionnaireInfoPanel:null,
	layout : 'border',
	initComponent:function(){
		var me = this;
		//回访客户基本信息panel
		me.customerInfoFormPanel = Ext.create('CustomerInfoFormPanel',{'id':'customerInfoFormPanel'});
		//客户需求grid
		me.customerOpinionPanel = Ext.create('CustomerOpinionPanel',{'id':'customerOpinionPanel'});
		//走货潜力grid
		me.sendGoodsPontentialPanel = Ext.create('SendGoodsPontentialPanel',{'id':'sendGoodsPontentialPanel'});
		//跟踪日程制定panel
		me.scheduleMakeForm = Ext.create('ScheduleMakeForm',{'id':'scheduleMakeForm'});
		//如果回访的类型属于流失预警客户周维护计划的回访
		if(me.returnVisitType === 'LOSTWARN'){
			//流失预警客户回访信息panel
			me.lostWarnCustomerInfoFormPanel = Ext.create('LostWarnCustomerInfoFormPanel',{
				id:'lostWarnCustomerInfoFormPanelId',
				hidden:false,
				region:'north',
				height:100
			});
			me.items = [{//回访基本信息panel
				xtype : 'basicpanel',
				region : 'north',
				height : 100,
				layout:'fit',
				items:[me.customerInfoFormPanel]
			},{//走货潜力panel
				region : 'center',
				xtype : 'basicpanel',
				layout:'border',
				items:[//流失预警客户回访信息panel
					me.lostWarnCustomerInfoFormPanel,{//客户需求grid panel
					xtype:'basicpanel',
					region:'center',
					layout:'fit',
					items:[me.customerOpinionPanel]
				},{//走货潜力grid panel
					xtype:'basicpanel',
					region:'south',
					layout:'fit',
					height:150,
					items:[me.sendGoodsPontentialPanel]
				}]
			},{//跟踪日程制定panel
				xtype : 'basicpanel',
				region : 'south',
				height : 150,
				layout:'fit',
				items:[me.scheduleMakeForm]
			}];
		}
		//如果回访的类型属于含有问卷的回访
		else if(me.returnVisitType === 'QUESTIONNAIRE'){
			//问卷信息panel
			me.questionnaireInfoPanel = null;
			me.items = [{//回访基本信息panel
				xtype : 'basicpanel',
				region : 'north',
				height : 100,
				layout:'fit',
				items:[me.customerInfoFormPanel]
			},{//走货潜力panel
				region : 'center',
				xtype : 'basicpanel',
				layout:'fit',
//				html:'问卷信息'
				items:[Ext.create('QuestionnaireQuestionListGrid',{
					id:'questionnaireQuestionListGridId'
				})]
			},{//跟踪日程制定panel
				xtype : 'basicpanel',
				region : 'south',
				height : 150,
				layout:'fit',
				items:[me.scheduleMakeForm]
			}];
		}
		else{
			me.items = [{//回访基本信息panel
				xtype : 'basicpanel',
				region : 'north',
				height : 100,
				layout:'fit',
				items:[me.customerInfoFormPanel]
			},{//走货潜力panel
				region : 'center',
				xtype : 'basicpanel',
				layout:'border',
				items:[{//客户需求grid panel
					xtype:'basicpanel',
					region:'center',
					layout:'fit',
					items:[me.customerOpinionPanel]
				},{//走货潜力grid panel
					xtype:'basicpanel',
					region:'south',
					layout:'fit',
					height:220,
					items:[me.sendGoodsPontentialPanel]
				}]
			},{//跟踪日程制定panel
				xtype : 'basicpanel',
				region : 'south',
				height : 150,
				layout:'fit',
				items:[me.scheduleMakeForm]
			}];
		}
		
		this.callParent();
	}
});