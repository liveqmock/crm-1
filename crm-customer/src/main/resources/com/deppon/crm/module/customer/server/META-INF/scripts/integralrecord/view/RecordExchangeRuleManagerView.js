//积分兑换规则管理
var recordExchangeRuleManagerDataControl= (CONFIG.get("TEST"))? new RecordExchangeRuleManagerDataTest():new RecordExchangeRuleManagerData();
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
Ext.define('RecordExchangeRuleManagerPanel',{
	extend:'BasicPanel',
	recordExchangeRuleManagerResultGrid:null,  //默认显示结果
	recordExchangeRuleManagerButtonPanel:null,//查询按钮面板
	recordExchangeRuleManagerData:null,//数据Data
	layout:{
        type:'vbox',
        align:'stretch'
    },
	initComponent:function(){
		var me = this;
		me.recordExchangeRuleManagerButtonPanel = Ext.create('RecordExchangeRuleManagerButtonPanel',{'parent':me,'recordExchangeRuleManagerData':me.recordExchangeRuleManagerData});
		me.recordExchangeRuleManagerResultGrid = Ext.create('RecordExchangeRuleManagerResultGrid',{'parent':me,'recordExchangeRuleManagerData':me.recordExchangeRuleManagerData});
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'basicpanel',
			height:38,
			items:[me.recordExchangeRuleManagerButtonPanel]		
		},{
			xtype:'basicpanel',
			flex:1,
			items:[me.recordExchangeRuleManagerResultGrid]
		}];
	},
	//新增或删除后 重新加载 页面数据
	reloadDate:function(addRecordDate,updateRecord){
		var me = this;
		var addRecord = Ext.create('GiftModle',addRecordDate);
		var store = me.recordExchangeRuleManagerResultGrid.store;
		
		
		if(!Ext.isEmpty(addRecordDate)){
			addRecord.set('createDate',new Date());
			addRecord.set('cname',top.User.empName);
			store.insert(store.getCount(),addRecord);
		}else if(!Ext.isEmpty(updateRecord)){
			updateRecord.set('modifyDate',new Date());
			updateRecord.set('mname',top.User.empName);
		}
		
		
//		//如果为修改操作则先删除修改前的数据
//		if(!Ext.isEmpty(updateRecord)){
//			grid.store.remove(updateRecord);
//		}
//		//数据加载到第一行
//		grid.store.insert(0,addRecord);
//		//如果该页分页已满则删除查询结果中最后一条数据
//		var count = grid.store.getCount();
//		if(count>=20){
//			grid.store.remove(grid.store.getAt(count-1));
//		}
//		//如果没有新增和修改数据  则为删除操作，重新加载数据
//		if(Ext.isEmpty(addRecordDate) && Ext.isEmpty(updateRecord)){
//			grid.store.load();
//		}
	}
});

/**
 * 积分兑换规则管理操作按钮面板
 */
Ext.define('RecordExchangeRuleManagerButtonPanel',{
	extend:'LeftAlignedPanel',
	contractManagerConditionForm:null,
	recordExchangeRuleManagerData:null,   //data层接口
	parent:null,
	defaultType:'button',
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
				text:i18n('i18n.PotentialCustManagerView.add'),
				scope:me,
				handler:me.addRecord
			},{
				text:i18n('i18n.PotentialCustManagerView.update'),
				scope:me,
				handler:me.updateRecord
			},{
				text:i18n('i18n.PotentialCustManagerView.delete'),
				scope:me,
				handler:me.deleteRecord
			},{
				text:i18n('i18n.RecordExchangeRuleManagerView.enableTheExchangeSet'),
				scope:me,
				handler:me.startExchangeRule
			}
			//取消 打印预览和打印功能
//				{
//				text:i18n('i18n.RecordExchangeRuleManagerView.printView'),
//				scope:me,
//				handler:me.printPreview
//			},{
//				text:i18n('i18n.RecordExchangeRuleManagerView.print'),
//				scope:me,
//				handler:me.printEvent
//			}
			];
	},
	//新增
	addRecord:function(){
		var me = this;
		var recordExchangeRuleWin = me.getRecordExchangeRuleWin(me.parent,me.recordExchangeRuleManagerData,'NEW',Ext.create('GiftModle')).show();
		recordExchangeRuleWin.down('form').getForm().reset();//新增时表单无值
		recordExchangeRuleWin.show();
	},
	//修改
	updateRecord:function(){
		var me = this;
		if(me.selectDate('ONE')){
			var record = me.selectedRecord()[0];
			me.getRecordExchangeRuleWin(me.parent,me.recordExchangeRuleManagerData,'UPDATE',record).show();
		}
	},
	//获得积分兑换规则管理编辑界面
	getRecordExchangeRuleWin:function(parent,recordExchangeRuleManagerData,operatorStatus,record){
		return Ext.create('RecordExchangeRuleWin',{
			'parent':parent,
			'recordExchangeRuleManagerData':recordExchangeRuleManagerData,
			operatorStatus:operatorStatus,
			'record':record})
	},
	//删除
	deleteRecord:function(){
		var me = this;
		if(me.selectDate('MANY')){
			var records = me.selectedRecord();
			var exchangeRuleIds = new Array();
			for (var i = 0; i < records.length; i++) {
				exchangeRuleIds.push(records[i].get('id'))
			}
			var params = {};
			params.exchangeRuleIds = exchangeRuleIds;
			var deleteFail = function(result){
				MessageUtil.showErrorMes(i18n('i18n.RecordExchangeRuleManagerView.deleteFailure')+result.message);
			}
			var deleteSuccess = function(result){
				MessageUtil.showInfoMes(i18n('i18n.RecordExchangeRuleManagerView.deleteSuccess'));
				//删除数据成功后重新加载数据
				me.parent.recordExchangeRuleManagerResultGrid.store.load();
			}
			Ext.MessageBox.confirm(i18n('i18n.PotentialCustManagerView.messageTip'), i18n('i18n.RecordExchangeRuleManagerView.m_isDelete'), function(e) {
				if (e == 'yes') {
					me.recordExchangeRuleManagerData.deleteExchangeRule(params,deleteSuccess,deleteFail);
				}
			});
		}
	},
	//启用兑换设置
	startExchangeRule:function(){
		var me = this;
		if(me.selectDate('ONE')){
			var record = me.selectedRecord()[0];
			var startCustExchangeManagerWin = Ext.create('StartCustExchangeManagerWin',{'parent':me.parent,'recordExchangeRuleManagerData':me.recordExchangeRuleManagerData,'record':Ext.create('GiftModle',record.data),'updateRecord':record})
			startCustExchangeManagerWin.down('form').getForm().reset();//新增时表单无值
			startCustExchangeManagerWin.show();
			//加载数据
			startCustExchangeManagerWin.down('form').loadRecord(record);
			if(!Ext.isEmpty(startCustExchangeManagerWin.record.get('department'))){
				startCustExchangeManagerWin.down('form').getForm().findField('department').setValue(record.get('department').id);
				startCustExchangeManagerWin.down('form').getForm().findField('department').setRawValue(record.get('department').deptName);
			}else{
				startCustExchangeManagerWin.down('form').getForm().findField('department').setValue('');
				startCustExchangeManagerWin.down('form').getForm().findField('department').setRawValue(i18n('i18n.RecordExchangeRuleManagerView.nationwide'));
			}
		}
	},
	//打印预览
	printPreview:function(){
		var me = this;
		
	},
	//打印
	printEvent:function(){
		var me = this;
		
	},
	selectDate:function(sum){
		var me = this;
		var selection=me.selectedRecord();
		if ('ONE'==sum && selection.length != 1) {
			Ext.MessageBox.alert(i18n('i18n.PotentialCustManagerView.messageTip'), i18n('i18n.ManualRewardIntegralEditView.m_selectOnlyOne'));
			return false;
		}
		if ('MANY'==sum && selection.length == 0) {
			Ext.MessageBox.alert(i18n('i18n.PotentialCustManagerView.messageTip'), i18n('i18n.ManualRewardIntegralEditView.m_selectOne'));
			return false;
		}
		return true;
	},
	selectedRecord:function(){
		var me = this;
		var grid = me.parent.recordExchangeRuleManagerResultGrid;
		return grid.getSelectionModel().getSelection();
	}
});

/**
 * 积分兑换规则管理默认显示数据结果
 */
Ext.define('RecordExchangeRuleManagerResultGrid',{
	extend:'SearchGridPanel',
	id:'RecordExchangeRuleManagerResultGrid_Id',
	parent:null,
	recordExchangeRuleManagerData:null,//数据Data
	initComponent:function(){
		var me = this;
		me.store = me.recordExchangeRuleManagerData.getGiftStore();
		me.dockedItems = me.getMyDockedItems();
		me.listeners = me.getMyListeners();
		me.selModel = Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE'});
		me.columns = me.getColumns();
		this.callParent();
	},
	getColumns:function(){
		var me = this;
		return [Ext.create('Ext.grid.RowNumberer'),{
			header:'id',
			dataIndex:'id',
			hidden:true
		},{
			header:i18n('i18n.IntegralRuleEdit.number'),
			dataIndex:'giftNumber'
		},{
			header:i18n('i18n.Integral.giftName'),
			dataIndex:'giftName'
		},{
			header:i18n('i18n.IntegralEx.giftValue'),
			dataIndex:'giftValue'
		},{
			header:i18n('i18n.IntegralEx.realValue'),
			dataIndex:'realValue'
		},{
			header:i18n('i18n.IntegralEx.needRecord'),
			dataIndex:'needPoints'
		},{
			header:i18n('i18n.RecordExchangeRuleManagerView.paymentOfTheNumberOf'),
			dataIndex:'inventNumber'
		},{
			header:i18n('i18n.IntegralEx.giftDesc'),
			dataIndex:'giftDesc'
		},{
			header:i18n('i18n.PotentialCustManagerView.createTime'),
			dataIndex:'createDate',
			renderer : DpUtil.renderDate
		},{
			header:i18n('i18n.ManualRewardIntegralManagerView.createName'),
			dataIndex:'cname'
		},{
			header : i18n('i18n.PotentialCustManagerView.lastUpdateTime'),
			dataIndex:'modifyDate',
			renderer : DpUtil.renderDate
		},{
			header : i18n('i18n.RecordExchangeRuleManagerView.modiflyUser'),
			dataIndex:'mname'
		},{
			header : i18n('i18n.RecordExchangeRuleManagerView.enabledExchangeCustomersPoints'),
			dataIndex:'isStart',
			renderer : function(value) {
				if(value){
					return i18n('i18n.RecordExchangeRuleManagerView.enabled');
				}else{
					return i18n('i18n.RecordExchangeRuleManagerView.notEnabled');
				}
			}
		},{
			header : i18n('i18n.RecordExchangeRuleManagerView.exchangeStartTime'),
			dataIndex:'beginTime',
			renderer : DpUtil.renderDate
		},{
			header : i18n('i18n.RecordExchangeRuleManagerView.exchangeClosingTime'),
			dataIndex:'endTime',
			renderer : DpUtil.renderDate
		},{
			header : i18n('i18n.RecordExchangeRuleManagerView.exchangeRegion'),
			dataIndex:'department',
			renderer:function(value){
				if(Ext.isEmpty(value)){
					return i18n('i18n.RecordExchangeRuleManagerView.nationwide');
				}
				return value.deptName;
			}
		}];
	},
	//分页条
	getMyDockedItems :function(){ 
		var me = this;
		return [ {
			xtype : 'pagingtoolbar',
			store : me.store,
			dock : 'bottom',
			displayInfo : true
		} ];
	},
	//事件，双击
	getMyListeners:function(){
		var me = this;
		return {
			itemdblclick:function(grid,record){
				me.parent.recordExchangeRuleManagerButtonPanel.getRecordExchangeRuleWin(
				me.parent,me.recordExchangeRuleManagerData,'VIEW',record).show();
			}
		};
	}
});
/**
 * 积分兑换规则 win
 */
Ext.define('RecordExchangeRuleWin',{
	extend:'PopWindow',
	title:i18n('i18n.RecordExchangeRuleManagerView.redeemRules'),
	width:240,
	height:270,
	layout:'column',
	recordExchangeRuleManagerData:null,
	operatorStatus:'NEW',//NEW新增，  UPDATE修改， VIEW查看，  默认为NEW
	record:null,
	initComponent:function(){
		var me = this;
		me.items = me.getItems();//合同附件panel
		me.fbar = me.getFbar();
		this.callParent();
		if(me.record != null){
			me.down('form').loadRecord(me.record);//绑定model
		}
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'basicformpanel',
			items:[{
			xtype:'fieldcontainer',
			layout:'column',
			items:[{xtype:'displayfield',value:'编码:<span style="color:red;">*</span>',width:70},
					{
						xtype:'textfield',
						name:'giftNumber',
						readOnly:('NEW' != me.operatorStatus)?true:false,
						allowBlank:false,
						maxLength:20,
						maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20),
						width:130
				   }]
		},{
			xtype:'fieldcontainer',
			layout:'column',
			items:[{xtype:'displayfield',value:'礼品名称:<span style="color:red;">*</span>',width:70},
					{
						xtype:'textfield',
						name:'giftName',
						readOnly:('VIEW' == me.operatorStatus)?true:false,
						allowBlank:false,
						maxLength:20,
						maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20),
						width:130
				   }]
		},{
			xtype:'fieldcontainer',
			layout:'column',
			items:[{xtype:'displayfield',value:'所需积分:<span style="color:red;">*</span>',width:70},
					{
						xtype:'numberfield',
						name:'needPoints',
						readOnly:('VIEW' == me.operatorStatus)?true:false,
						allowBlank:false,
						minValue: 0,
						hideTrigger: true,
				        mouseWheelEnabled: false,
						maxLength:20,
						maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20),
						width:130
				   }]
		},{
			xtype:'fieldcontainer',
			layout:'column',
			items:[{xtype:'displayfield',value:'礼品价值:<span style="color:red;">*</span>',width:70},
					{
						xtype:'numberfield',
						name:'giftValue',
						readOnly:('VIEW' == me.operatorStatus)?true:false,
						allowBlank:false,
						minValue: 0,
						hideTrigger: true,
				        mouseWheelEnabled: false,
						maxLength:20,
						maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20),
						width:130
				   }]
		},{
			xtype:'fieldcontainer',
			layout:'column',
			items:[{xtype:'displayfield',value:'实际价值:<span style="color:red;">*</span>',width:70},
					{
						xtype:'numberfield',
						name:'realValue',
						readOnly:('VIEW' == me.operatorStatus)?true:false,
						allowBlank:false,
						minValue: 0,
						hideTrigger: true,
				        mouseWheelEnabled: false,
						maxLength:20,
						maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20),
						width:130
				   }]
		},{
			xtype:'fieldcontainer',
			layout:'column',
			items:[{xtype:'displayfield',value:'发放数量:<span style="color:red;">*</span>',width:70},
					{
						xtype:'numberfield',
						name:'inventNumber',
						readOnly:('VIEW' == me.operatorStatus)?true:false,
						allowBlank:false,
						minValue: 0,
						hideTrigger: true,
				        mouseWheelEnabled: false,
						maxLength:20,
						maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20),
						width:130
				   }]
		},{
			xtype:'textfield',
			labelWidth:65,
			name:'giftDesc',
			readOnly:('VIEW' == me.operatorStatus)?true:false,
			fieldLabel:i18n('i18n.Integral.awardRecordDesc'),
			maxLength:20,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20),
			width:200
		}]
		}];
	},
	getFbar:function(){
		var me = this;
		return[{
	    	xtype:'button',
	    	scope:me,
	    	disabled:('VIEW' == me.operatorStatus)?true:false,
	    	text:i18n('i18n.PotentialCustEditView.save'),
	    	handler:me.saveRule
	    }];
	},
	saveRule:function(button){
		var me = this;
		var form = me.down('form').getForm();
		form.updateRecord(me.record);
		if(!form.isValid( )){
			MessageUtil.showMessage(i18n('i18n.RecordExchangeRuleManagerView.pleaseInputMustInputData'));
			return;
		}
		button.setDisabled(true);
		var params = {};
		params.gift = me.record.data;
		if('NEW' == me.operatorStatus){
			var saveFail = function(result){
				button.setDisabled(false);
				MessageUtil.showErrorMes(i18n('i18n.RecordExchangeRuleManagerView.saveFailureWar')+result.message);
			}
			var saveSuccess = function(result){
				MessageUtil.showInfoMes(i18n('i18n.RecordExchangeRuleManagerView.saveSuccessWar'));
				//保存成功后重新加载数据
				me.parent.reloadDate(result.gift);
				me.close();
				button.setDisabled(false);
			}
			me.recordExchangeRuleManagerData.saveExchangeRule(params,saveSuccess,saveFail);
		}
		if('UPDATE' == me.operatorStatus){
			var updateFail = function(result){
				button.setDisabled(false);
				MessageUtil.showErrorMes(i18n('i18n.RecordExchangeRuleManagerView.updateFailureWar')+result.message);
			}
			var updateSuccess = function(result){
				MessageUtil.showInfoMes(i18n('i18n.RecordExchangeRuleManagerView.updateSuccessWar'));
				//修改成功后重新加载数据
				me.parent.reloadDate(null,me.record);
				me.close();
				button.setDisabled(false);
			}
			me.recordExchangeRuleManagerData.updateExchangeRule(params,updateSuccess,updateFail);
		}
	}
});
/**
 * 启用客户兑换换管理 win
 */
Ext.define('StartCustExchangeManagerWin',{
	extend:'PopWindow',
	/*extend:'Ext.window.Window',*/
	title:i18n('i18n.RecordExchangeRuleManagerView.enableCustomersToExchangeChangeManagement'),
	y:0,
	width:240,
	height:250,
	layout:'column',
	contractData:null,
	record:null,
	updateRecord:null,
	contractAttachment:null,
	initComponent:function(){
		var me = this;
		me.items = me.getItems();//合同附件panel
		me.fbar = me.getFbar();
		this.callParent();
		//确保store 数据已经加载，防止第一次 启用兑换设置时  区域文本框没有数据
		me.recordExchangeRuleManagerData.getAllBizDeptStore();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'basicformpanel',
			items:[{xtype:'displayfield',value:i18n('i18n.RecordExchangeRuleManagerView.conversionTime'),width:70
			},{
				xtype:'fieldcontainer',
				layout:'column',
				items:[{xtype:'displayfield',value:'起:<span style="color:red;">*</span>',width:70},
						{
							xtype:'datefield',
							name:'beginTime',
							endDateFieldName : 'endTime',
							vtype : 'dateRange',
							allowBlank:false,
							width:130,
							format: 'Y-m-d'
					   }]
			},{
				xtype:'fieldcontainer',
				layout:'column',
				items:[{xtype:'displayfield',value:'止:<span style="color:red;">*</span>',width:70},
						{
							xtype:'datefield',
							name:'endTime',
							startDateFieldName : 'beginTime',
							vtype : 'dateRange',
							allowBlank:false,
							width:130,
							format: 'Y-m-d'
					   }]
			},{xtype:'displayfield',value:'可兑换区域:<span style="color:red;">*</span>',width:80
			},{
				xtype:'combo',
				name:'department',
				allowBlank:false,
				labelWidth:200,
				queryMode:'remote',
	            forceSelection:true,
				displayField:'deptName',
				valueField:'id',
				store:me.recordExchangeRuleManagerData.getAllBizDeptStore()
			},{
				xtype:'radiogroup',
				layout:'column',
				fieldLabel: i18n('i18n.RecordExchangeRuleManagerView.redeemFunction'),
				items:[{
						boxLabel:i18n('i18n.RecordExchangeRuleManagerView.startUse'),
						name:'isStart',
						inputValue: true
					   },{
						boxLabel:i18n('i18n.RecordExchangeRuleManagerView.endUse'),
						name:'isStart',
						inputValue: false
					   }]
			}]
		}];
	},
	getFbar:function(){
		var me = this;
		return[{
	    	xtype:'button',
	    	scope:me,
	    	text:i18n('i18n.RecordExchangeRuleManagerView.confirm'),
	    	handler:me.affirmStartExchange
	    }];
	},
	affirmStartExchange:function(button){
		var me = this;
		var form = me.down('form').getForm();
		if(!form.isValid( )){
			MessageUtil.showMessage(i18n('i18n.RecordExchangeRuleManagerView.pleaseInputMustInputData'));
			return;
		}
		button.setDisabled(true);
		form.updateRecord(me.record);
		var params = {};
				
		me.updateRecord.set('beginTime', form.findField('beginTime').getValue());
		me.updateRecord.set('endTime', form.findField('endTime').getValue());
		me.updateRecord.set('isStart', form.findField('isStart').getValue());
		
		var department = {}
		department.id = form.findField('department').getValue();
		department.deptName = form.findField('department').getRawValue();
		me.updateRecord.set('department',department);
//		params.gift = me.record.data;
		params.gift = me.updateRecord.data;
		
		var affirmFail = function(result){
			button.setDisabled(false);
			MessageUtil.showErrorMes(i18n('i18n.RecordExchangeRuleManagerView.saveFailureWar')+result.message);
		}
		var affirmSuccess = function(result){
			MessageUtil.showInfoMes(i18n('i18n.RecordExchangeRuleManagerView.saveSuccessWar'));
			//修改成功后重新加载数据
			me.parent.reloadDate(null,me.updateRecord);
			me.close();
			button.setDisabled(false);
		}
		me.recordExchangeRuleManagerData.startExchangeSet(params,affirmSuccess,affirmFail);
	}
});
Ext.onReady(function() {
	Ext.create('Ext.container.Viewport', {
		layout : 'fit',
//		autoScroll:true,
		items : [ Ext.create('RecordExchangeRuleManagerPanel', {
			'recordExchangeRuleManagerData' : recordExchangeRuleManagerDataControl
		}) ]
	});
});

