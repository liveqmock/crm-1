var recordSearchControl = (CONFIG.get('TEST')) ? Ext
		.create('RecordSearchDataTest') : Ext.create('RecordSearchData');

/**
 * .
 * <p>
 * 积分规则管理主页面</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */
Ext.onReady(function() {
	var keys = ['ORDER_SOURCE','TRANS_TYPE'];
	 initDataDictionary(keys);
	 initUser();
	Ext.QuickTips.init();
	new RecordRuleManagementView();
});
Ext.form.Field.prototype.msgTarget = 'side';

/**
 * .
 * <p>
 *积分规则按钮</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */

Ext.define('RecordRuleBtnPanel', {
	extend : 'LeftAlignedPanel',
//	height:37,
	initComponent : function() {
		this.items = this.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [{
				xtype : 'button', 
				text : i18n('i18n.PotentialCustManagerView.add'),
				hidden:!isPermission('/customer/RecordRuleOperateABtn.action'),
				handler:function(){
					var recordEditView = Ext.getCmp('recordEditView');
					var addressData = {};
					addressData.leadept = '';
					addressData.arrdept = '';
					if (null==recordEditView||undefined==recordEditView) {
						recordEditView = new RecordEditView({'addressData':addressData,'readOnly':false,'detailEditable':false,checkEnable:false});
					}
//					var failureFn=function(json){
//					};
//					var successFn=function(json){
//						if (json.ruleId!=null) {
//							Ext.getCmp('editRuleTextFiled').setValue(json.ruleId);
//						}
//					};
//					recordSearchControl.getRecordRuleByNumber(null,successFn,failureFn);
					recordEditView.show();
				}
			}, {
				xtype : 'button', 
				text : i18n('i18n.PotentialCustManagerView.update'),
				hidden:!isPermission('/customer/RecordRuleOperateUBtn.action'),
				handler:function(){
//					if (me.checkChooseRow()) {
						var recordEditView = Ext.getCmp('recordEditView');
						if (null==recordEditView||undefined==recordEditView) {
							var addressData = {};
							addressData.leadept = '';
							addressData.arrdept = '';
							recordEditView = new RecordEditView({'addressData':addressData,'isUpdate':true,'readOnly':false,'detailEditable':false,checkEnable:true});
						}
						if(me.formLoadData()){
							recordEditView.show();
						}
//					}
				}
			},{
				xtype : 'button',
				text : i18n('i18n.IntegralRuleEdit.detail'),
				handler:function(){
//					if (me.checkChooseRow()) {
						var recordEditView = Ext.getCmp('recordEditView');
						if (null==recordEditView||undefined==recordEditView) {
							var addressData = {};
							addressData.leadept = '';
							addressData.arrdept = '';
							recordEditView = new RecordEditView({'addressData':addressData,'readOnly':true,'detailEditable':true,checkEnable:true});
						}
						if(me.formLoadData()){
							recordEditView.show();
						}
//					}
				}
			}
//			, {
//				xtype : 'button', 
//				text : i18n('i18n.PotentialCustManagerView.delete'),
//				handler:function(){
//					if (me.checkChooseRow()) {
//						var product = Ext.getCmp('productRuleGridPanel').getSelectionModel().getSelection();
//						var channel = Ext.getCmp('channelRuleGridPanel').getSelectionModel().getSelection();
//						var way = Ext.getCmp('wayRuleGridPanel').getSelectionModel().getSelection();
//						var deleteParams = null;
//						var successFn=function(json){
//							DpUtil.showMessage(i18n('i18n.RecordRuleManagement.deleteSuccessWar'));
//						}
//						if (product.length==1) {
//							deleteParams={'ruleId':product[0].data.id};
//							recordSearchControl.deleteRecordRule(deleteParams,successFn,successFn);
//							Ext.getCmp('productRuleGridPanel').getStore().remove(product[0]);
//						}else if (channel.length==1) {
//							deleteParams={'ruleId':channel[0].data.id};
//							recordSearchControl.deleteRecordRule(deleteParams,successFn,successFn);
//							Ext.getCmp('channelRuleGridPanel').getStore().remove(channel[0]);
//						}else{
//							deleteParams={'ruleId':way[0].data.id};
//							recordSearchControl.deleteRecordRule(deleteParams,successFn,successFn);
//							Ext.getCmp('wayRuleGridPanel').getStore().remove(way[0]);
//						}
//					}
//				}
//			}
			];
	},
	checkChooseRow:function(gridId){
		var selections = Ext.getCmp(gridId).getSelectionModel().getSelection();
//		var product = Ext.getCmp('productRuleGridPanel').getSelectionModel().getSelection();
//		var channel = Ext.getCmp('channelRuleGridPanel').getSelectionModel().getSelection();
//		var way = Ext.getCmp('wayRuleGridPanel').getSelectionModel().getSelection();
//		var selections = product.length+channel.length+way.length;
		if (selections.length==1) {
			return true;
		}
		MessageUtil.showErrorMes(i18n('i18n.PotentialCustManagerView.operation_message'));
		return false;
	},
	formLoadData:function(){
		var me =this;
		//获取三个表格中选择的行
		var product = Ext.getCmp('productRuleGridPanel').getSelectionModel().getSelection();
		var channel = Ext.getCmp('channelRuleGridPanel').getSelectionModel().getSelection();
		var way = Ext.getCmp('wayRuleGridPanel').getSelectionModel().getSelection();
		//选中的行
		var record = null;
		var tabId = Ext.getCmp('tabs').getActiveTab().getId();
		//选中的行是产品
//		if (product.length==1) {
		if (tabId=='tab1') {
			if (!me.checkChooseRow('productRuleGridPanel')) {
				return false;
			}
			record= product[0];
			//设置产品的值
			Ext.getCmp('recordRuleEidtProduct').setValue(true);
			Ext.getCmp('recordRuleEditProductType').setReadOnly(true);
			Ext.getCmp('recordRuleEditProductType').setValue(record.get(CONFIGNAME.get('rulProductType')));
			//选中中的行是渠道
//		}else if (channel.length==1) {
		}else if (tabId=='tab2') {
			if (!me.checkChooseRow('channelRuleGridPanel')) {
				return false;
			}
			record=channel[0];
			//是指渠道的值
			Ext.getCmp('recordRuleEidtChannel').setValue(true);
			Ext.getCmp('recordRuleEditChannelType').setReadOnly(true);
			Ext.getCmp('recordRuleEditChannelType').setValue(record.get(CONFIGNAME.get('rulChannelType')));
			//选中的是路线
		}else {
			if (!me.checkChooseRow('wayRuleGridPanel')) {
				return false;
			}
			record=way[0];
			//设置路线的值
			Ext.getCmp('recordRuleEidtWay').setValue(true);
			Ext.getCmp('startStationAreaSelect').store.add(Ext.create('cityModel',{'id':record.get(CONFIGNAME.get('rulStartStation')),'name':record.get('leadeptName')}));
			Ext.getCmp('endStationAreaSelect').store.add(Ext.create('cityModel',{'id':record.get(CONFIGNAME.get('rulEndStation')),'name':record.get('arrdeptName')}));
			Ext.getCmp('startStationAreaSelect').setValue(record.get(CONFIGNAME.get('rulStartStation')));
			Ext.getCmp('endStationAreaSelect').setValue(record.get(CONFIGNAME.get('rulEndStation')));
			//修改者：李学兴，增加出发和到达城市 name赋值，并修改 出发和到达城市id赋值
			Ext.getCmp('leadeptName_id').setValue(record.get('leadeptName'));
			Ext.getCmp('arrdeptName_id').setValue(record.get('arrdeptName'));
			Ext.getCmp('startStationAreaSelect').setDisabled(true);
			Ext.getCmp('endStationAreaSelect').setDisabled(true);
		}
		//设置规则编号
		Ext.getCmp('editRuleTextFiled').setValue(record.get(CONFIGNAME.get('id')));
//		var integralRuleModel = new IntegralRuleModel(record.data);
		//设置规则的详情
		Ext.getCmp('ruleDetailEditPanel').getForm().loadRecord(record);
		return true;
	}
});

/**
 * .
 * <p>
 * 产品规则grid</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */
Ext.define('ProductRuleGridPanel', {
	extend : 'SearchGridPanel',
	id : 'productRuleGridPanel',
	flex:1,
//	height:'85%',
	store : null,
	columns : null,
	listeners:{
		itemdblclick:function( th, record,  item, index,  e,eOpts ){
//			if (me.checkChooseRow()) {
				var recordEditView = Ext.getCmp('recordEditView');
				if (null==recordEditView||undefined==recordEditView) {
					//修改者：李学兴，出发到达城市 id
					var addressData = {};
					addressData.leadept = '';
					addressData.arrdept = '';
					if(!Ext.isEmpty(record.get('leadept'))){
						addressDaa.leadept = record.get('leadept');
						addressDaa.arrdept = record.get('arrdept');
					}
					recordEditView = new RecordEditView({'readOnly':true,'detailEditable':true,checkEnable:true});
					Ext.getCmp('recordRuleEditProductType').setValue(record.get(CONFIGNAME.get('rulProductType')));
				}
				//设置规则编号
				Ext.getCmp('editRuleTextFiled').setValue(record.get(CONFIGNAME.get('id')));
//				var integralRuleModel = new IntegralRuleModel(record.data);
				//设置规则的详情
				Ext.getCmp('ruleDetailEditPanel').getForm().loadRecord(record);
				recordEditView.show();
//			}
		}
	},
	initComponent : function() {
		var me = this;
		me.store = recordSearchControl.getProductRuleStore();
		me.dockedItems = me.getBBar();
		me.columns = me.getColumns();
		me.selModel =  me.getSelModel();
		this.callParent();
	},
	// 分页工具栏
	getBBar : function() {
		var me = this;
		return [ {
			xtype : 'pagingtoolbar',
			store : me.store,
			dock : 'bottom',
			displayInfo : true
		} ];
	},
	getSelModel : function() {
		return  Ext.create('Ext.selection.CheckboxModel', {
			mode : 'MULTI'
		})
	},
	getColumns : function() {
		return [ {
			xtype : 'rownumberer',
			width : 40,
			text : i18n('i18n.Integral.number')
		}, {
			header : i18n('i18n.IntegralRuleEdit.number'),
			dataIndex : CONFIGNAME.get('id')
		}, {
			header : i18n('i18n.IntegralRuleEdit.product'),
			dataIndex : CONFIGNAME.get('rulProductType'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.TRANS_TYPE);
				}
			}
		}, {
			header : i18n('i18n.Integral.recordRate'),
			dataIndex : CONFIGNAME.get('rulRate')
		}, {
			header : i18n('i18n.IntegralRuleEdit.validateDate'),
			dataIndex : CONFIGNAME.get('rulValideTime'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return Ext.Date.format(new Date(value),'Y-m-d');
				}
			}
		}, {
			header : i18n('i18n.IntegralRuleEdit.endDate'),
			dataIndex : CONFIGNAME.get('rulValideInvalideTime'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return  Ext.Date.format(new Date(value),'Y-m-d');
				}
			}
		}, {
			header : i18n('i18n.Integral.recordDesc'),
			dataIndex : CONFIGNAME.get('rulRecordDesc')
		}, {
			header : i18n('i18n.PotentialCustManagerView.creator'),
			dataIndex : CONFIGNAME.get('rulCreateUser')
		}, {
			header : i18n('i18n.PotentialCustManagerView.searchStartTime'),
			dataIndex : CONFIGNAME.get('rulCreateTime'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return  Ext.Date.format(new Date(value),'Y-m-d');
				}
			}
		}, {
			header : i18n('i18n.PotentialCustManagerView.lastUpdate'),
			dataIndex : CONFIGNAME.get('rulModifyUser')
		}, {
			header : i18n('i18n.PotentialCustManagerView.lastUpdateTime'),
			dataIndex : CONFIGNAME.get('rulModifyDate'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return  Ext.Date.format(new Date(value),'Y-m-d');
				}
			}
		} ];
	}
})
//
///**
// * .
// * <p>
// * 带标题的产品规则</br>
// * </p>
// * 
// * @author 潘光均
// * @时间 2012-03-19
// */
//
//Ext.define('ProductRuleGridTitlePanel',{
//	extend:'BasicVboxPanel', 
//	width:'100%',
////	height:100,
//	flex:1,
//	initComponent : function() {
//		this.items = this.getItems();
//		this.callParent();
//	},
//	getItems : function() {
//		var me = this;
//		return [
//		{
//			xtype:'toppanel',   
//			items:[
//			{
//				xtype:'titlepanel',  
//				flex:1,
//				items:[{
//					xtype:'displayfield',
//					value:i18n('i18n.IntegralRuleEdit.productRule')
//				}]
//			}]
//		},{
//			xtype:'basicpanel', 
//			flex:1,
//			items:[new ProductRuleGridPanel()]
//		}
//	  ];
//	}
//});



/**
 * .
 * <p>
 * 渠道规则grid</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */
Ext.define('ChannelRuleGridPanel', {
	extend : 'SearchGridPanel',
	id : 'channelRuleGridPanel',
	flex:1,
//	height:'85%',
	store : null,
	columns : null,
	listeners:{
		itemdblclick:function( th, record,  item, index,  e,eOpts ){
//			if (me.checkChooseRow()) {
				var recordEditView = Ext.getCmp('recordEditView');
				if (null==recordEditView||undefined==recordEditView) {
					//修改者：李学兴，出发到达城市 id
					var addressData = {};
					addressData.leadept = '';
					addressData.arrdept = '';
					if(!Ext.isEmpty(record.get('leadept'))){
						addressData.leadept = record.get('leadept');
						addressData.arrdept = record.get('arrdept');
					}
					recordEditView = new RecordEditView({'addressData':addressData,'readOnly':true,'detailEditable':true,checkEnable:true});
					Ext.getCmp('recordRuleEditChannelType').setValue(record.get(CONFIGNAME.get('rulChannelType')));
				}
				//设置规则编号
				Ext.getCmp('editRuleTextFiled').setValue(record.get(CONFIGNAME.get('id')));
//				var integralRuleModel = new IntegralRuleModel(record.data);
				//设置规则的详情
				Ext.getCmp('ruleDetailEditPanel').getForm().loadRecord(record);
				recordEditView.show();
//			}
		}
	},
	initComponent : function() {
		var me = this;
		me.store = recordSearchControl.getChannelRuleStore();
		me.bbar = me.getBBar();
		me.columns = me.getColumns();
		me.selModel =  me.getSelModel();
		this.callParent();
	},
	// 分页工具栏
	getBBar : function() {
		var me = this;
		return Ext.create('Ext.toolbar.Paging', {
			store : me.store,
//			displayMsg : i18n('i18n.GiftChooseView.dataShowThat'),// i18n('i18n.order.displayMsg'),
			displayInfo : true
		})
	},
	getSelModel : function() {
		return  Ext.create('Ext.selection.CheckboxModel', {
			mode : 'MULTI'
		})
	},
	getColumns : function() {
		return [ {
			xtype : 'rownumberer',
			width : 40,
			text : i18n('i18n.Integral.number')
		}, {
			header : i18n('i18n.IntegralRuleEdit.number'),
			dataIndex : CONFIGNAME.get('id')
		}, {
			header : i18n('i18n.Integral.channelOrder'),
			dataIndex : CONFIGNAME.get('rulChannelType'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.ORDER_SOURCE);
				}
			}
		}, {
			header : i18n('i18n.Integral.recordRate'),
			dataIndex : CONFIGNAME.get('rulRate')
		}, {
			header : i18n('i18n.Integral.recordDesc'),
			dataIndex : CONFIGNAME.get('rulRecordDesc')
		}, {
			header : i18n('i18n.IntegralRuleEdit.validateDate'),
			dataIndex : CONFIGNAME.get('rulValideTime'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return  Ext.Date.format(new Date(value),'Y-m-d');
				}
			}
		}, {
			header : i18n('i18n.IntegralRuleEdit.endDate'),
			dataIndex : CONFIGNAME.get('rulValideInvalideTime'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return  Ext.Date.format(new Date(value),'Y-m-d');
				}
			}
		}, {
			header : i18n('i18n.PotentialCustManagerView.creator'),
			dataIndex : CONFIGNAME.get('rulCreateUser')
		}, {
			header : i18n('i18n.PotentialCustManagerView.createTime'),
			dataIndex : CONFIGNAME.get('rulCreateTime'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return  Ext.Date.format(new Date(value),'Y-m-d');
				}
			}
		}, {
			header : i18n('i18n.PotentialCustManagerView.lastUpdate'),
			dataIndex : CONFIGNAME.get('rulModifyUser')
		}, {
			header : i18n('i18n.PotentialCustManagerView.lastUpdateTime'),
			dataIndex : CONFIGNAME.get('rulModifyDate'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return  Ext.Date.format(new Date(value),'Y-m-d');
				}
			}
		} ];
	}
})

///**
// * .
// * <p>
// * 带标题的产品规则</br>
// * </p>
// * 
// * @author 潘光均
// * @时间 2012-03-19
// */
//
//Ext.define('ChannelRuleGridTitlePanel',{
//	extend:'BasicVboxPanel', 
//	width:'100%',
////	height:100,
//	flex:1,
//	initComponent : function() {
//		this.items = this.getItems();
//		this.callParent();
//	},
//	getItems : function() {
//		var me = this;
//		return [
//		{
//			xtype:'toppanel',   
//			items:[
//			{
//				xtype:'titlepanel',  
//				flex:1,
//				items:[{
//					xtype:'displayfield',
//					value:i18n('i18n.IntegralRuleEdit.channelRule')
//				}]
//			}]
//		},{
//			xtype:'basicpanel', 
//			flex:1,
//			items:[new ChannelRuleGridPanel()]
//		}
//	  ];
//	}
//});


/**
 * .
 * <p>
 * 路线规则grid</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */
Ext.define('WayRuleGridPanel', {
	extend : 'SearchGridPanel',
	id : 'wayRuleGridPanel',
	flex:1,
//	height:'85%',
	store : null,
	columns : null,
	listeners:{
		itemdblclick:function( th, record,  item, index,  e,eOpts ){
//			if (me.checkChooseRow()) {
				var recordEditView = Ext.getCmp('recordEditView');
				if (null==recordEditView||undefined==recordEditView) {
					//修改者：李学兴，出发到达城市 id
					var addressData = {};
					addressData.leadept = '';
					addressData.arrdept = '';
					if(!Ext.isEmpty(record.get('leadept'))){
						addressData.leadept = record.get('leadept');
						addressData.arrdept = record.get('arrdept');
					}
					recordEditView = new RecordEditView({'addressData':addressData,'readOnly':true,'detailEditable':true,checkEnable:true});
				}
				//修改者:李学兴，cope查看详情 设置路线的值
				Ext.getCmp('startStationAreaSelect').store.add(Ext.create('cityModel',{'id':record.get(CONFIGNAME.get('rulStartStation')),'name':record.get('leadeptName')}));
				Ext.getCmp('endStationAreaSelect').store.add(Ext.create('cityModel',{'id':record.get(CONFIGNAME.get('rulEndStation')),'name':record.get('arrdeptName')}));
				Ext.getCmp('startStationAreaSelect').setValue(record.get(CONFIGNAME.get('rulStartStation')));
				Ext.getCmp('endStationAreaSelect').setValue(record.get(CONFIGNAME.get('rulEndStation')));
				Ext.getCmp('leadeptName_id').setValue(record.get('leadeptName'));
				Ext.getCmp('arrdeptName_id').setValue(record.get('arrdeptName'));
				//设置规则编号
				Ext.getCmp('editRuleTextFiled').setValue(record.get(CONFIGNAME.get('id')));
//				var integralRuleModel = new IntegralRuleModel(record.data);
				//设置规则的详情
				Ext.getCmp('ruleDetailEditPanel').getForm().loadRecord(record);
				recordEditView.show();
//			}
		}
//,
//		select:function(th, record, index, eOpts){
//			Ext.getCmp('channelRuleGridPanel').getSelectModel().getSelection().each(function(th){
//				Ext.getCmp('channelRuleGridPanel').getSelectModel().getSelection().deselect(th);
//			})
//		}
	},
	initComponent : function() {
		var me = this;
		me.store = recordSearchControl.getWayRuleStore();
		me.bbar = me.getBBar();
		me.columns = me.getColumns();
		me.selModel =  me.getSelModel();
		this.callParent();
	},
	// 分页工具栏
	getBBar : function() {
		var me = this;
		return Ext.create('Ext.toolbar.Paging', {
			store : me.store,
//			displayMsg : i18n('i18n.GiftChooseView.dataShowThat'),// i18n('i18n.order.displayMsg'),
			displayInfo : true
		})
	},
	getSelModel : function() {
		return  Ext.create('Ext.selection.CheckboxModel', {
			mode : 'MULTI'
		})
	},
	getColumns : function() {
		return [ {
			xtype : 'rownumberer',
			width : 40,
			text : i18n('i18n.Integral.number')
		}, {
			header : i18n('i18n.IntegralRuleEdit.number'),
			dataIndex : CONFIGNAME.get('id')
		}, {
			header : i18n('i18n.IntegralRuleEdit.startCityId'),
			dataIndex : CONFIGNAME.get('rulStartStation'),
			hidden:true
		}, {
			header : i18n('i18n.IntegralRuleEdit.endCityId'),
			dataIndex : CONFIGNAME.get('rulEndStation'),
			hidden:true
		}
		//修改者：李学兴，出发和到达城市 名称，隐藏出发和到达城市 id
		, {
			header : i18n('i18n.IntegralRuleEdit.startCity'),
			dataIndex : 'leadeptName'
		}, {
			header : i18n('i18n.IntegralRuleEdit.endCity'),
			dataIndex : 'arrdeptName'
		}
		, {
			header : i18n('i18n.Integral.recordRate'),
			dataIndex : CONFIGNAME.get('rulRate')
		}, {
			header : i18n('i18n.Integral.recordDesc'),
			dataIndex : CONFIGNAME.get('rulRecordDesc')
		}, {
			header : i18n('i18n.IntegralRuleEdit.validateDate'),
			dataIndex : CONFIGNAME.get('rulValideTime'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return  Ext.Date.format(new Date(value),'Y-m-d');
				}
			}
		}, {
			header : i18n('i18n.IntegralRuleEdit.endDate'),
			dataIndex : CONFIGNAME.get('rulValideInvalideTime'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return  Ext.Date.format(new Date(value),'Y-m-d');
				}
			}
		}, {
			header : i18n('i18n.PotentialCustManagerView.creator'),
			dataIndex : CONFIGNAME.get('rulCreateUser')
		}, {
			header : i18n('i18n.PotentialCustManagerView.createTime'),
			dataIndex : CONFIGNAME.get('rulCreateTime'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return  Ext.Date.format(new Date(value),'Y-m-d');
				}
			}
		}, {
			header : i18n('i18n.PotentialCustManagerView.lastUpdate'),
			dataIndex : CONFIGNAME.get('rulModifyUser')
		}, {
			header : i18n('i18n.PotentialCustManagerView.lastUpdateTime'),
			dataIndex : CONFIGNAME.get('rulModifyDate'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return  Ext.Date.format(new Date(value),'Y-m-d');
				}
			}
		} ];
	}
})

///**
// * .
// * <p>
// * 带标题的路线规则Grid</br>
// * </p>
// * 
// * @author 潘光均
// * @时间 2012-03-19
// */
//
//Ext.define('WayRuleGridTitlePanel',{
//	extend:'BasicVboxPanel', 
//	width:'100%',
////	height:100,
//	flex:1,
//	initComponent : function() {
//		this.items = this.getItems();
//		this.callParent();
//	},
//	getItems : function() {
//		var me = this;
//		return [
//		{
//			xtype:'toppanel',   
//			items:[
//			{
//				xtype:'titlepanel',  
//				flex:1,
//				items:[{
//					xtype:'displayfield',
//					value:i18n('i18n.RecordRuleManagement.lineRules')
//				}]
//			}]
//		},{
//			xtype:'basicpanel', 
//			flex:1,
//			items:[new WayRuleGridPanel()]
//		}
//	  ];
//	}
//});
/**
 * .
 * <p>
 * 积分规则管理viewport</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */


Ext.define('Tabpanel1',{
	extend:'TabContentPanel', 
	items:null,
	id:'tab1',
	title:i18n('i18n.IntegralRuleEdit.productRule'),
	layout:{
		type:'vbox',
        align:'stretch'
	},
	memberData:null,
	memberBasicRecord:null,     
	initComponent:function(){
		var me = this;
		me.productRuleGridPanel = new ProductRuleGridPanel();
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [me.productRuleGridPanel];
	}
});

Ext.define('Tabpanel2',{
	extend:'TabContentPanel', 
	items:null,
	id:'tab2',
	title:i18n('i18n.IntegralRuleEdit.channelRule'),
	layout:{
		type:'vbox',
        align:'stretch'
	},
	memberData:null,
	memberBasicRecord:null,     
	initComponent:function(){
		var me = this;
		me.channelRuleGridPanel = new ChannelRuleGridPanel();
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [me.channelRuleGridPanel];
	}
});

Ext.define('Tabpanel3',{
	extend:'TabContentPanel', 
	items:null,
	id:'tab3',
	title:i18n('i18n.IntegralRuleEdit.wayRule'),
	layout:{
		type:'vbox',
        align:'stretch'
	},
	memberData:null,
	memberBasicRecord:null,     
	initComponent:function(){
		var me = this;
		me.wayRuleGridPanel = new WayRuleGridPanel();
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [me.wayRuleGridPanel];
	}
});

Ext.define('RecordRuleManagementPanel',{
		extend:'BasicPanel',
		layout:{
			type:'vbox',
			align:'stretch'
		},
		items:null,
		validateMemberPanel:null,
		memberEditPanel:null,
		initComponent:function(){
			var me = this;
			me.recordRuleBtnPanel = new RecordRuleBtnPanel();
			me.tabpanel1 = new Tabpanel1();
			me.tabpanel2 = new Tabpanel2();
			me.tabpanel3=new Tabpanel3();
			me.items = [
			{
				xtype:'basicpanel',
				items:[me.recordRuleBtnPanel]
//				height:37
			},{
				xtype:'normaltabpanel',
				id:'tabs',
				items:[me.tabpanel1,me.tabpanel2,me.tabpanel3],
				flex:1
			}
			];
			this.callParent();
		}
	});
	
Ext.define('RecordRuleManagementView', {
	extend : 'Ext.container.Viewport',
	layout:'fit',
	initComponent:function(){
		var me=this;
		me.items=[new RecordRuleManagementPanel()];
		this.callParent();
	}
})
//Ext.define('RecordRuleManagementView', {
//	extend : 'Ext.container.Viewport',
////	autoScroll:true,
//	//积分规则按钮
//	recordRuleBtnPanel : null,
//	//路线规则
//	wayRuleGridTitlePanel:null,
//	//渠道规则
//	channelRuleGridTitlePanel:null,
//	//产品规则
//	productRuleGridTitlePanel:null,
//	layout:{
//		type:'vbox',
//		align:'stretch'
//	},
//	initComponent : function() {
//		var me = this;
//		me.items = me.getItems();
//		this.callParent();
//	},
//	getItems : function() {
//		var me = this;
//		me.recordRuleBtnPanel = new RecordRuleBtnPanel();
//		me.productRuleGridTitlePanel = new ProductRuleGridTitlePanel();
//		me.channelRuleGridTitlePanel = new ChannelRuleGridTitlePanel();
//		me.wayRuleGridTitlePanel=new WayRuleGridTitlePanel();
//		return [
//		        me.recordRuleBtnPanel,
//		        me.productRuleGridTitlePanel, 
//		         me.channelRuleGridTitlePanel,me.wayRuleGridTitlePanel]
//	}
//});
//

