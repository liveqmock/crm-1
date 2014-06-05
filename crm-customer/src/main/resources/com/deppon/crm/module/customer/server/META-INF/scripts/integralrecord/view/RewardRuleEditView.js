/**
 * 功能：奖励积分规则管理 edit界面
 * @author 李学兴 
 */
var rewardRuleEditDataControl= (CONFIG.get("TEST"))? new RewardRuleManagerDataTest():new RewardRuleManagerData();
//Ext.onReady(function(){
/**
 * 日期校验
 */
Ext.form.field.VTypes.dateRange = function(val,field){
		 var date = field.parseDate(val);	
	     if (!date) {
	         return false;
	     }
	     if (field.startDateFieldName && (!this.dateRangeMax || (date.getTime() != this.dateRangeMax.getTime()))) {
	         var start = field.up('form').getForm().findField(field.startDateFieldName);
	         start.setMaxValue(date);
	         start.validate();
	         this.dateRangeMax = date;
	     }
	     else if (field.endDateFieldName && (!this.dateRangeMin || (date.getTime() != this.dateRangeMin.getTime()))) {
	         var end = field.up('form').getForm().findField(field.endDateFieldName);
	         end.setMinValue(date);
	         end.validate();
	         this.dateRangeMin = date;
	         if(end.getValue() < new Date()){
	            end.setMinValue(new Date());
	      	 }
	     }
	     return true;
};
Ext.define('RewardRuleEditWin',{
	extend:'PopWindow',
	title:i18n('i18n.RewardRuleManagerView.rewardRule'),
	rewardRuleEditPanel:null,
	record:null,
	operatorStatus:'NEW',//标志位  NEW 新建， UPDATE 修改，VIEW 查看 
	width:460,
//		height:400,
	height:210,
	layout:'fit',
	initComponent:function(){
		var me = this;
		me.rewardRuleEditPanel = Ext.create('RewardRuleEditPanel',{'parent':me.parent,'parentWin':me,'operatorStatus':me.operatorStatus,'record':me.record,'rewardRuleEditData':rewardRuleEditDataControl});
		me.items = me.getItems();
		me.fbar = me.getFbar();
		this.callParent();
		if('VIEW' == me.operatorStatus){
			me.lockAllComponents();
		}
		if('NEW' == me.operatorStatus || 'UPDATE' == me.operatorStatus){
			me.unLockAllComponents();
			//如果是新增积分类型默认选择 按分值
			if('NEW' == me.operatorStatus){
				var form = me.down('form').getForm();
				form.findField('integType').setValue('SCORE');
				form.findField('fraction').hide();
				form.findField('fraction').allowBlank = true;
				form.findField('pointvalue').allowBlank = false;
				me.down('form').doLayout();
			}
		}
	},
	getItems:function(){
		var me = this;
		return [me.rewardRuleEditPanel];
	},
	getFbar:function(){
		var me = this;
		return [ {
			xtype : 'button',
			text : i18n('i18n.PotentialCustEditView.save'),
			disabled:('VIEW' == me.operatorStatus)?true:false,
			scope:me.rewardRuleEditPanel,
			handler:me.rewardRuleEditPanel.saveEvent
		},{
			xtype : 'button',
			text : i18n('i18n.ManualRewardIntegralEditView.b_cance'),
			scope:me,
			handler:me.cancelEvent
		}];
	},
	cancelEvent:function(){
		var me = this;
		me.close();
	},
	//锁定所有控件
	lockAllComponents:function(){
		var me = this;
		//奖励规则基本信息
		var rewardRuleInfo = me.down('form').getForm();
		rewardRuleInfo.getFields().each(function(field){
			field.setReadOnly(true);
		});
		//隐藏保存按钮
		var rewardRuleInfo = me.down('button').hide();
	},
	//解除锁定所有控件
	unLockAllComponents:function(){
		var me = this;
		//奖励规则基本信息
		var rewardRuleInfo = me.down('form').getForm();
		rewardRuleInfo.getFields().each(function(field){
			field.setReadOnly(false);
		});
	}
});
	
//	Ext.define('RewardTypeEditWin',{
//		extend:'PopWindow',
//		title:'奖励类型管理 ',
//		rewardTypeEditPanel:null,
//		y:0,
//		width:300,
//		height:240,
//		layout:'fit',
//		initComponent:function(){
//			var me = this;
//			me.rewardTypeEditPanel = Ext.create('RewardTypeEditPanel',{'rewardRuleEditData':rewardRuleEditDataControl});
//			me.items = me.getItems();
//			this.callParent();
//		},
//		getItems:function(){
//			var me = this;
//			return [me.rewardTypeEditPanel];
//		}
//	});
//	Ext.create('RewardTypeEditWin').show();

//});


Ext.define('RewardRuleEditPanel',{
	extend:'BasicPanel',
	/*extend:'Ext.panel.Panel',*/
	rewardRuleEditInfoPanel:null,  //奖励规则基本信息
	rewardRuleEditLimitPanel:null,//奖励规则限制条件
	rewardRuleEditButtonPanel:null,//奖励规则 按钮面板
	rewardRuleEditData:null,//数据Data
	operatorStatus:null,
	parentWin:null,
	record:null,
	layout:{
        type:'vbox',
        align:'stretch'
    },
	initComponent:function(){
		var me = this;
		me.rewardRuleEditInfoPanel = Ext.create('RewardRuleEditInfoPanel',{'record':me.record,'rewardRuleEditData':me.rewardRuleEditData});
//后续功能		me.rewardRuleEditLimitPanel = Ext.create('RewardRuleEditLimitPanel',{'rewardRuleEditData':me.rewardRuleEditData});
//后续功能		me.rewardRuleEditButtonPanel = Ext.create('RewardRuleEditButtonPanel',{'rewardRuleEditData':me.rewardRuleEditData});
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [
//			{
//			/*xtype:'middlebuttonpanel'*/
//			xtype:'basicpanel',
//			items:[me.rewardRuleEditButtonPanel]
//		},
			{
			/*xtype:'leftbuttonpanel',*/
//			xtype:'poptitleformpanel',
			xtype : 'fieldset',
			height:110,
			title : i18n('i18n.RewardRuleEditView.integralRewardInfo'),
			items:[me.rewardRuleEditInfoPanel]		
		}
//		,{
//			/*xtype:'middlebuttonpanel'*/
//			xtype : 'fieldset',
//			height:150,
//			title : i18n('i18n.RewardRuleEditView.rewardRulesLimitConditions'),
//			items:[me.rewardRuleEditLimitPanel]	
//		}
		];
	},
	saveEvent:function(){
		var me = this;
		var form = me.rewardRuleEditInfoPanel;
		form.getForm().updateRecord(me.record);
		var params = {};
		params.rewardIntegRule = me.record.data;
		if('NEW' == me.operatorStatus){
			if(!me.rewardRuleEditInfoPanel.getForm().isValid()){
				MessageUtil.showMessage(i18n('i18n.RewardRuleEditView.pleaseInputMustInputItem'));
				return;
			}
			var saveFail = function(result){
				MessageUtil.showErrorMes(i18n('i18n.RecordExchangeRuleManagerView.saveFailureWar')+result.message);
			}
			var saveSuccess = function(result){
				MessageUtil.showInfoMes(i18n('i18n.RecordExchangeRuleManagerView.saveSuccessWar'));
				me.parentWin.close();
				me.parent.reloadDate(result.rewardIntegRule);//调用父界面 重新加载数据 把新增成功的数据加载到第一行
			}
			me.rewardRuleEditData.saveRewardIntegRule(params,saveSuccess,saveFail);
		}
		if('UPDATE' == me.operatorStatus){
			if(!me.rewardRuleEditInfoPanel.getForm().isValid()){
				MessageUtil.showErrorMes(i18n('i18n.RewardRuleEditView.pleaseInputMustInputItem'));
				return;
			}
			var updateFail = function(result){
				MessageUtil.showMessage(i18n('i18n.RecordExchangeRuleManagerView.updateFailureWar')+result.message);
			}
			var updateSuccess = function(result){
				MessageUtil.showInfoMes(i18n('i18n.RecordExchangeRuleManagerView.updateSuccessWar'));
				me.parentWin.close();
				me.parent.reloadDate(result.rewardIntegRule,me.record);//调用父界面 重新加载数据 把新增成功的数据加载到第一行
			}
			me.rewardRuleEditData.updateRewardIntegRule(params,updateSuccess,updateFail);
		}
	}
});

/**
 * 奖励规则 按钮面板
 */
Ext.define('RewardRuleEditButtonPanel',{
	extend:'PopButtonPanel',
//	extend:'Ext.panel.Panel',
	rewardRuleEditData:null,   //data层接口
	parent:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'popleftbuttonpanel',
//			xtype:'panel',
			width:610,
			items:[{
				xtype:'button',
				text:i18n('i18n.PotentialCustManagerView.add'),
				scope:me
				/*handler:me.addRecord*/
			},{
				xtype:'button',
				text:i18n('i18n.PotentialCustManagerView.update'),
				scope:me
				/*handler:me.invalidContract*/
			},{
				xtype:'button',
				text:i18n('i18n.PotentialCustEditView.save'),
				scope:me
				/*handler:me.boundContract*/
			}]
		},{
			xtype:'middlebuttonpanel'
		}];
	}
});

/**
 * 奖励规则基本信息
 */
Ext.define('RewardRuleEditInfoPanel',{
	extend:'NoTitleFormPanel',
//	extend:'Ext.panel.Panel',
	rewardRuleEditData:null,//数据
	record:null,
	width:700,
	height:200,
	layout: {
        type: 'vbox',
        align: 'stretch'
    },
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
		if(me.record != null){
			if('SCORE' == me.record.get('integType')){//按分值
				me.getForm().findField('fraction').hide();
				me.getForm().findField('fraction').allowBlank = true;
				me.getForm().findField('pointvalue').allowBlank = false;
				me.doLayout();
			}
			if('RATIO' == me.record.get('integType')){//按比率
				me.getForm().findField('pointvalue').hide();
				me.getForm().findField('pointvalue').allowBlank = true;
				me.getForm().findField('fraction').allowBlank = false;
				me.doLayout();
			}
			me.loadRecord(me.record);
		}
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'fieldcontainer',
			layout:'column',
			defaults:{
				width:200,
				allowBlank:false
			},
			items:[{xtype:'displayfield',value:'奖励类型<span style="color:red;">*</span>',width:80},
				{
					xtype:'combo',
					name:'ruletype',
					width:120,
					store:me.rewardRuleEditData.getRuletypeStore(),
					queryMode:'local',
	                forceSelection:true,
					displayField:'codeDesc',
					valueField:'code'
				},{ 
					xtype:'textfield',
					name:'rulename',
					labelWidth:75,
					fieldLabel:i18n('i18n.Integral.awardName'),
					maxLength : 20,
					maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20)
				}]
		},{
			xtype:'fieldcontainer',
			layout:'column',
			defaults:{
				labelWidth:75,
				width:200,
				allowBlank:false
			},
			items:[{
					xtype:'datefield',
					name:'pointbegintime',
					xtype : 'datefield',
					endDateFieldName : 'pointendtime',
					vtype : 'dateRange',
					blankText:i18n('i18n.RewardRuleEditView.exchangeStartingTimeWar'),
					fieldLabel:i18n('i18n.RewardRuleEditView.startDate')
				},{
					xtype:'datefield',
					name:'pointendtime',
					startDateFieldName : 'pointbegintime',
					vtype : 'dateRange',
					minValue:new Date(),
					blankText:i18n('i18n.RewardRuleEditView.exchangeTheEndOfTimeWar'),
					fieldLabel:i18n('i18n.IntegralRuleEdit.endDate')
				}]
		},
//			{
//			xtype:'fieldcontainer',
//			layout:'column',
//			items:[{
//						xtype:'datefield',
//						fieldLabel:i18n('i18n.RewardRuleEditView.integralLimitationDate'),
//						width:200,
//						name:''
//					
//					
//				}]
//		},
			{
			xtype:'fieldcontainer',
			layout:'column',
			defaults:{
				width:200
			},
			items:[{xtype:'displayfield',value:i18n('i18n.RewardRuleEditView.rewardPoints'),width:80},
			       {
					xtype:'combo',
					name:'integType',
					width:50,
					store:me.rewardRuleEditData.getIntegTypeStore(),
					queryMode:'local',
					allowBlank:false,
	                forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					listeners:{
						scope:me,
						select:me.selectIntegTypeFn
					}
				   },{xtype:'displayfield',value:i18n('i18n.RewardRuleEditView.gift'),width:40},{
						xtype:'numberfield',
						name:'pointvalue',//按分值
						width:30,
						hideTrigger: true,
				        mouseWheelEnabled: false
				   },{
						xtype:'numberfield',
						name:'fraction',//按比率
						width:30,
						hideTrigger: true,
				        mouseWheelEnabled: false
				   },{
					    xtype:'textfield',
						labelWidth:75,
						allowBlank:false,
						name:'pointdesc',
						maxLength:100,
						maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',100),
						fieldLabel:i18n('i18n.Integral.awardRecordDesc'),
						width:200
					}
				]
		}];
	},
	//选择积分类型 
	selectIntegTypeFn:function(combox,selectedRecords){
		var me = this;
		//选择积分类型 
		if('SCORE' == combox.getValue()){//选择按分值，则比率输入框隐藏
			me.getForm().findField('fraction').hide();
			me.getForm().findField('fraction').allowBlank = true;
			me.getForm().findField('pointvalue').show();
			me.getForm().findField('pointvalue').allowBlank = false;
			me.doLayout();
		}
		if('RATIO' == combox.getValue()){//按比率，则分值输入框隐藏
			me.getForm().findField('pointvalue').hide();
			me.getForm().findField('pointvalue').allowBlank = true;
			me.getForm().findField('fraction').show();
			me.getForm().findField('fraction').allowBlank = false;
			me.doLayout();
		}
	}
});
/**
 * 奖励规则限制条件
 */
Ext.define('RewardRuleEditLimitPanel',{
	extend:'NoTitleFormPanel',
	/*extend:'Ext.panel.Panel',*/
	rewardRuleEditData:null,//数据
	width:700,
	height:400,
	layout: {
        type: 'vbox',
        align: 'stretch'
    },
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'fieldcontainer',
			layout:'column',
			defaults:{
				width:200
			},
			items:[{xtype:'displayfield',value:'本规则适用于<span style="color:red;">*</span>',width:100},
				{
					xtype:'checkboxgroup',
					width:300,
					items:[{boxLabel:i18n('i18n.RewardRuleEditView.normalMember'),name:'memberGrage',inputValue:'0'},
	        	       {boxLabel:i18n('i18n.RewardRuleEditView.goldMember'),name:'memberGrage',inputValue:'1'},
	        	       {boxLabel:i18n('i18n.RewardRuleEditView.platinumMember'),name:'memberGrage',inputValue:'2'},
	        	       {boxLabel:i18n('i18n.RewardRuleEditView.diamondMember'),name:'linkmanType',inputValue:'3'}]
				}]
		},{
			xtype:'fieldcontainer',
			layout:'column',
			items:[{xtype:'displayfield',value:i18n('i18n.RewardRuleEditView.rulesRewardForMemberLimited'),width:240},
				{
					xtype:'numberfield',
					width:40,
					name:'',
					hideTrigger: true,
			        mouseWheelEnabled: false
				},{
					xtype:'combo',
					width:40,
					name:''
				},{
					xtype:'textfield',
					width:40,
					name:''
				},{
					xtype:'displayfield',
					width:20,
					value:i18n('i18n.RewardRuleEditView.times')
				}]
		},{
			xtype:'fieldcontainer',
			layout:'column',
			defaults:{
				width:200
			},
			items:[{xtype:'displayfield',value:i18n('i18n.RewardRuleEditView.ruleCanEligibleMemberLimited'),width:240},
				{
					xtype:'numberfield',
					width:40,
					name:'',
					hideTrigger: true,
			        mouseWheelEnabled: false
				},{
					xtype:'combo',
					width:40,
					name:''
				},{
					xtype:'textfield',
					width:40,
					name:''
				},{
					xtype:'displayfield',
					width:20,
					value:i18n('i18n.RewardRuleEditView.times')
				}]
		},{
			xtype:'fieldcontainer',
			layout:'column',
			items:[{xtype:'displayfield',value:i18n('i18n.RewardRuleEditView.unlimitedPleaseEnter0'),width:400}]
		}];
	}
});
/**
 * 奖励类型管理panel
 */
Ext.define('RewardTypeEditPanel',{
	extend:'BasicVboxPanel',
	rewardRuleEditData:null,
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
			items:[{
				xtype:'btnpanel',
				items:[{
					xtype:'button',
					text:i18n('i18n.PotentialCustManagerView.update')
				},{
					xtype:'button',
					text:i18n('i18n.RewardRuleEditView.add')
				},{
					xtype:'button',
					text:i18n('i18n.PotentialCustEditView.save')
				}]
			}]
		}
		,{
			xtype : 'popupgridpanel',
			flex:1,
			store:Ext.create('Ext.data.Store',{
				fields:['code','codeDesc'],
				proxy:{
					type:'memory',
					data:[]
				}
			}),
			columns:[new Ext.grid.RowNumberer(),
			         {
						header:i18n('i18n.RewardRuleEditView.ruletype'),
						dataIndex:'',
						flex : 1
			         },{
				         header:i18n('i18n.RewardRuleEditView.modified'),
			        	 dataIndex:'',
						flex : 1
			         },{
				         header:i18n('i18n.RecordExchangeRuleManagerView.modiflyUser'),
			        	 dataIndex:'',
						flex : 1
			         }]
		}];
	}
});

