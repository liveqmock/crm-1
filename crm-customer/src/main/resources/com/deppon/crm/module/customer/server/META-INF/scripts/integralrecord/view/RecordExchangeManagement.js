var recordSearchControl = (CONFIG.get('TEST')) ? Ext
		.create('RecordSearchDataTest') : Ext.create('RecordSearchData');

/**
 * .
 * <p>
 *积分兑换管理主页面</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */
Ext.onReady(function() {
	var keys = [ 'CUST_SEND_STATUS'];
	 initDataDictionary(keys);
	 initUser();
	Ext.QuickTips.init();
	new GiftManagementView();
});
Ext.form.Field.prototype.msgTarget = 'side';

/**
 * .
 * <p>
 * 联系人积分grid</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */

Ext.define('GiftBtnPanel', {
	extend : 'NormalButtonPanel', // --第一步,定义一个主panel,继承NormalButtonPanel
	items : null,
	initComponent : function() {
		this.items = this.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [ {
			xtype : 'leftbuttonpanel', // --第二步,定义一个位于左边的按钮容器,继承leftbuttonpanel
			items : [ {
				xtype : 'button',
				text : i18n('i18n.IntegralRuleEdit.integExchange'),
				handler:function(){
					var recordExchangeView = Ext.getCmp('recordExchangeView');
					if (null==recordExchangeView||undefined==recordExchangeView) {
						recordExchangeView = new RecordExchangeView();
					}
					recordExchangeView.applyGridTitlePanel.getStore().removeAll();
					var scFn=function(json){
						recordExchangeView.applyGridTitlePanel.getStore().loadData(json.custGiftList);
						recordExchangeView.show();
					};
					var faFn=function(json){
						
					}
					recordSearchControl.searchTemporaryGiftBill(null,scFn,scFn);
				}
			}, {
				xtype : 'button', 
				text : i18n('i18n.IntegralRuleEdit.comfirmSend'),
				handler:function(){
					me.confimSend();
				}
			}, {
				xtype : 'button', 
				text : i18n('i18n.IntegralRuleEdit.recoverStore'),
				handler:function(){
					me.revertStore();
				}
			} ]
		}, {
			xtype : 'middlebuttonpanel'
		}, {
			xtype : 'rightbuttonpanel', 
			items : [ {
				xtype : 'button', 
				text : i18n('i18n.PotentialCustManagerView.search'),
				handler:function(){
					me.search();
				}
			} ]
		} ];
	},
	search:function(){
		Ext.getCmp('giftGridPanel').getStore().load();
	},
	confimSend:function(){
		var records = Ext.getCmp('giftGridPanel').getSelectionModel().getSelection();
		if (records.length<1) {
			MessageUtil.showErrorMes(i18n('i18n.IntegralRuleEdit.chooseOne'));
			return;
		};
		var params =[];
		for ( var i = 0; i < records.length; i++) {
			if (records[i].get(CONFIGNAME.get('custGifIsSend'))!='PASS') {
				MessageUtil.showErrorMes(i18n('i18n.IntegralRuleEdit.giftNotSatifieCondition'));
				return;
			}else{
				params.push(records[i].get(CONFIGNAME.get('id')));
			}
		}
		var param = {'giftIds':params};
		
		var successFn=function(){
			MessageUtil.showInfoMes(i18n('i18n.IntegralRuleEdit.sendSuccess'));
			for ( var i = 0; i < records.length; i++) {
				records[i].set(CONFIGNAME.get('custGifIsSend'),'TRANSMITTED');
			}
			//修改者：李学兴，点击"确认发放"按钮后重新加载grid
			if(!Ext.isEmpty(Ext.getCmp('giftGridPanel'))){
				Ext.getCmp('giftGridPanel').store.load();
			}
		};
		var param = {'giftIds':params};
		var failureFn=function(){
			MessageUtil.showErrorMes(i18n('i18n.IntegralRuleEdit.sendFailue'));
		};
		recordSearchControl.confirmSendGift(param,successFn,failureFn);
	},
	revertStore:function(){
		var records = Ext.getCmp('giftGridPanel').getSelectionModel().getSelection();
		if (records.length<1) {
			MessageUtil.showErrorMes(i18n('i18n.IntegralRuleEdit.chooseOne'));
			return;
		};
		var params =[];
		for ( var i = 0; i < records.length; i++) {
			if (records[i].get(CONFIGNAME.get('custGifIsSend'))!='TRANSMITTED') {
				MessageUtil.showErrorMes(i18n('i18n.IntegralRuleEdit.giftNotSatifieRecoverCondition'));
				return;
			}else{
				params.push(records[i].get(CONFIGNAME.get('id')));
			}
		};
		var param = {'giftIds':params};
		
		var successFn=function(json){
			var message = Ext.isEmpty(json.message)?"":json.message;
			MessageUtil.showInfoMes(message+i18n('i18n.IntegralRuleEdit.recoverSuccess'));
			for ( var i = 0; i < records.length; i++) {
				records[i].set(CONFIGNAME.get('custGifIsSend'),'PASS');
			}
		};
		var failureFn=function(json){
			var message = Ext.isEmpty(json.message)?"":json.message;
			MessageUtil.showErrorMes(message+i18n('i18n.IntegralRuleEdit.recoverFailue'));
		};
		recordSearchControl.revertStoreGift(param,successFn,failureFn);
	}
});

/**
 * .
 * <p>
 * 礼品查询grid</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */
Ext.define('GiftGridPanel', {
	extend : 'SearchGridPanel',
	id : 'giftGridPanel',
//	height:'85%',
	store : null,
	columns : null,
	initComponent : function() {
		var me = this;
		//store
		me.store = recordSearchControl.getCustGiftStore();
		//分页工具栏
		me.bbar = me.getBBar();
		//列
		me.columns = me.getColumns();
		//选项模式
		me.selModel =  me.getSelModel();
		//store添加beforeload事件
		me.store.on('beforeload',function(store,operation,obj){
			var custNumber=Ext.getCmp('gifCustNumber').getValue();
			var contactNumber=Ext.getCmp('gifContactNumber').getValue();
			var sendStatus = Ext.getCmp('gifSendStatus').getValue();
			param={'custNo':custNumber,'contactNo':contactNumber,'sendStatus':sendStatus};
			Ext.apply(operation,{
				params : param
			})
		});
		this.callParent();
	},
	// 分页工具栏
	getBBar : function() {
		var me = this;
		return Ext.create('Ext.toolbar.Paging', {
			id : 'pagingToolbar',
			store : me.store,
//			displayMsg : i18n('i18n.GiftChooseView.dataShowThat'),// i18n('i18n.order.displayMsg'),
			displayInfo : true,
			
			  items:[ i18n('i18n.PotentialCustManagerView.searchEndTime'),{
				  text: i18n('i18n.RecordExchangeManagement.c_pageShow'), 
				  xtype: 'tbtext' },
				  Ext.create('Ext.form.ComboBox', {
					  width: 50, 
					  value: '10', 
					  triggerAction: 'all',
					  forceSelection: true,
					  editable: false,
					  name: 'comboItem',
					  displayField: 'value', 
					  valueField: 'value', 
					  queryMode: 'local', 
					  store : Ext.create('Ext.data.Store',
							  { fields : ['value'], 
						  		 data : [  {'value':'10'}, {'value':'15'},  {'value':'20'}, 
						  		          {'value':'25'}, {'value':'40'}, {'value':'100'}] }),
					 listeners:{ select : {
						            	scope : this, 
						            	fn: function(_field,_value){ 
						            		var pageSize =  me.store.pageSize;
						            		var	newPageSize = parseInt(_field.value); 
						            		if(pageSize!=newPageSize){
						            			me.store.pageSize = newPageSize;
						            			Ext.getCmp('pagingToolbar').moveFirst(); 
						            			} } } 
				  } }),
					{ text:  i18n('i18n.PotentialCustManagerView.SearchResultGrid.number'), 
					  	xtype: 'tbtext' }]
			 
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
//			header : i18n('i18n.ScatterCustManagerView.memberNo'),
//			dataIndex : CONFIGNAME.get('custGifContact'),
//			renderer:function(value){
//				if (!Ext.isEmpty(value)) {
//					return value.member.custNumber;
//				}
//			}
//		}, {
			header : i18n('i18n.Integral.contactNumber'),
			dataIndex : CONFIGNAME.get('custGifContact'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.number;
				}
			}
		}, {
			header : i18n('i18n.Integral.contatct'),
			dataIndex : CONFIGNAME.get('custGifContact'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.name;
				}
			}
		}, {
			header : i18n('i18n.IntegralRuleEdit.workflowNum'),
			dataIndex : CONFIGNAME.get('custGifWorkFlowId')
		}, {
			header : i18n('i18n.Integral.giftName'),
			dataIndex : CONFIGNAME.get('custGifGift'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.giftName;
				}
			}
		}, {
			header : i18n('i18n.IntegralEx.giftDesc'),
			dataIndex : CONFIGNAME.get('custGifGift'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.giftDesc;
				}
			}
		}, {
			header : i18n('i18n.Integral.userdRecord'),
			dataIndex : CONFIGNAME.get('custUsedRecord')
		}, {
			header : i18n('i18n.Integral.exchangeNum'),
			dataIndex : CONFIGNAME.get('custGifNum')
		}, {
			header : i18n('i18n.Integral.exchangeDate'),
			dataIndex : CONFIGNAME.get('custGifConvertTime'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return Ext.Date.format(new Date(value),'Y-m-d');
				}
			}
		}, {
			header : i18n('i18n.Integral.sendDate'),
			dataIndex : CONFIGNAME.get('custGifSendTime'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return Ext.Date.format(new Date(value),'Y-m-d');
				}
			}
		}, {
			header : i18n('i18n.Integral.isSend'),
			dataIndex : CONFIGNAME.get('custGifIsSend'),
			renderer : function(value){
            	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.CUST_SEND_STATUS);
            }
		}, {
			header : i18n('i18n.Integral.exchagerNo'),
			dataIndex : CONFIGNAME.get('custGifExchangerNo')
		} ];
	}
})
/**
 * .
 * <p>
 * 理赔查询条件panel</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */
Ext.define('GiftSearchPanel', {
	extend : 'SearchFormPanel',
	items : null,
	initComponent : function() {
		var me = this
		me.items = me.getItems();
		this.callParent();
	},
	getItems : function() {
		return [{
			defaults:{
				width:210,
				labelWidth:70,
				listeners:{
					specialkey:function(th,  e,  eOpts ){
						if (e.getKey()==Ext.EventObject.ENTER) {
							Ext.getCmp('giftGridPanel').getStore().load();
						}
					}
				}
			},
			layout:{
				type:'table',
				columns:3
			},items:[{
			xtype:'textfield',
			id:'gifCustNumber',
			fieldLabel:i18n('i18n.MemberCustEditView.custNo')
		}, {
			xtype:'textfield',
			id:'gifContactNumber',
			fieldLabel:i18n('i18n.MemberCustEditView.contactNo')
		}, {
			xtype:'combo',
			id:'gifSendStatus',
			fieldLabel:i18n('i18n.Integral.isSend'),
			valueField:'code',
			displayField:'codeDesc',
			queryMode:'local',
			store:DpUtil.createStore('sendStatusStore',null,['code','codeDesc'],DataDictionary.CUST_SEND_STATUS),
			forceSelection:true,
			listeners:{
				change:function(field, newValue) {
					if (Ext.isEmpty(newValue)) {
						field.setValue(null);
					}
				}
			}
		}] 
	}]
	}
});

/**
 * .
 * <p>
 * 积分查询viewport</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */

Ext.define('GiftManagementPanel',{
	extend:'BasicPanel',
	giftSearchPanel : null,
	giftBtnPanel : null,
	giftGridPanel : null,
	layout:{
        type:'vbox',
        align:'stretch'
    },
	initComponent:function(){
		var me = this;
		me.giftSearchPanel = new GiftSearchPanel();
		me.giftBtnPanel = new GiftBtnPanel();
		me.giftGridPanel = new GiftGridPanel();
		me.items = [{
			xtype:'basicpanel',
			height:40,
			items:[me.giftSearchPanel]
		},{
			xtype:'basicpanel',
			height:36,
			items:[me.giftBtnPanel]
		},{
			xtype:'basicpanel',
			flex:1,
			items:[me.giftGridPanel]
		}];
		this.callParent();
	}
});
Ext.define('GiftManagementView', {
	extend : 'Ext.container.Viewport',
	layout:'fit',
	initComponent:function(){
		this.items=[new GiftManagementPanel()];
		this.callParent();
	}
});

