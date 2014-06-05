/**
 * 使用说明：xtype:'customertrade' 使用 客户行业控件
 */
BelongDeptView = function() {
};
/**
 * 客户行业 model
 */
Ext.define('TradeModel', {
	extend : 'Ext.data.Model',
	fields : ['code',
		        'codeDesc',
				'codeSeq',
				'codeType',
				'createDate',
				'createUser',
				'id',
				'invaliTime',
				'language',
				'modifyDate',
				'modifyUser',
				'parentId',
				'status']
});

var params =[// 客户行业
   			'TRADE',
   			// 客户属性
   			'SECOND_TRADE'];
initDataDictionary(params);

Ext.define('SecondTradeStore',{
	extend:'Ext.data.Store',
	model:'TradeModel',
	data:DataDictionary['SECOND_TRADE'],
	proxy:{
		type:'memory'
	}
});


requestJsonAjax = function(url, params, successFn, failFn, timeout) {
	Ext.Ajax.request({
		url : url,
		jsonData : params,
		timeout : (Ext.isEmpty(timeout)) ? 600000 : timeout,
		success : function(response) {
			var result = Ext.decode(response.responseText);
			if (result.success) {
				successFn(result);
			} else {
				failFn(result);
			}
		},
		failure : function(response) {
			var result = Ext.decode(response.responseText);
			failFn(result);
		}
	});
};


/**
 * 获得所属部门控件
 */
Ext.define('CustomerTrade', {
	extend : 'Ext.container.Container',
	alias : 'widget.bocustomertrade',
	layout:{
		type:'table',
		columns:2
	},
	style:{padding:'0px'},
	userType:'OTHER',//SEARCH:用于做查询条件是查询，
	//查询的是一月内有效的二级行业，OTHER :用于新增或修改行业信息是使用
	trade_labelWidth:70,
	trade_width:240,
	trade_allowBlank:true,
	trade_name:'tradeId',
	trade_fieldname:'客户行业',
	trade_margin:'0 0 0 0',
	trade_readOnly:false,
	trade_forceSelect:true,
	second_trade_auto_expand:true,
	second_trade_margin:'0 0 0 0',
	second_trade_readOnly:false,
	second_trade_allowBlank:true,
	second_trade_forceSelect:true,
	second_trade_labelWidth:70,
	second_trade_width:240,
	second_trade_name:'secondTrade',
	second_trade_labelSeparator:'',
	second_trade_fieldname:i18n('i18n.secondTrade.secondTrade'),
	searchSecondTrade:function(parentId){
		var  me=this;
		var param = {
				'condition':{'parentId': parentId}
		};
		var sucessFn = function(json) {
			me.up('form').getForm().findField(me.second_trade_name).setValue(null);
			me.up('form').getForm().findField(me.second_trade_name).getStore().removeAll();
			if (!Ext.isEmpty(json.results)) {
				me.up('form').getForm().findField(me.second_trade_name).getStore().loadData(json.results);
			}
			if (me.second_trade_auto_expand) {
				me.up('form').getForm().findField(me.second_trade_name).expand();
			}
		}
		var failFn = function(json) {
			
		}
		var searchSecondTradeByParentId='../customer/searchSecondTradeByParentId.action';
		if (me.userType==='SEARCH') {
			searchSecondTradeByParentId='../customer/searchMonthValidSecondTrade.action';
		}
		requestJsonAjax(searchSecondTradeByParentId,param,sucessFn,failFn);
	},
	initComponent : function() {
		var me = this;
		var tradeStore = getDataDictionaryByName(DataDictionary, 'TRADE');
		var secondTradeStore = new SecondTradeStore();
		me.items = [ {
			xtype : 'combo',
			name : me.trade_name,
			forceSelection : me.trade_forceSelect,
			labelWidth:me.trade_labelWidth,
			width:me.trade_width,
			margin:me.trade_margin,
			readOnly:me.trade_readOnly,
			fieldLabel:me.trade_fieldname,
			allowBlank:me.trade_allowBlank,
			displayField : 'codeDesc',
			valueField : 'code',
			store : tradeStore,
			listeners : {
				select : function(combo, records, obj) {
					me.searchSecondTrade(records[0].get('id'));
				},
				change:function(ths,newValue,oldValue,opt){
					if (Ext.isEmpty(newValue)) {
						me.up('form').getForm().findField(me.second_trade_name).setValue(null);
					}
					if (Ext.isEmpty(newValue)) {
						ths.setValue(null);
					}
				}
			}
		}, {
			xtype : 'combo',
			margin:me.second_trade_margin,
			name : me.second_trade_name,
			readOnly:me.second_trade_readOnly,
			allowBlank:me.second_trade_allowBlank,
			labelWidth:me.second_trade_labelWidth,
			width:me.second_trade_width,
			fieldLabel:me.second_trade_fieldname,
			forceSelection : me.second_trade_forceSelect,
			labelSeparator : me.second_trade_labelSeparator,
			queryMode:'local',
			displayField : 'codeDesc',
			valueField : 'code',
			store : secondTradeStore,
			listeners:{
				beforequery:function(queryEvent,eOpts){
					var trade_store = me.up('form').getForm().findField(me.trade_name).getStore();
					var second_trade_field = me.up('form').getForm().findField(me.second_trade_name);
					var second_trade_store = second_trade_field.getStore();
					var tradeValue = me.up('form').getForm().findField(me.trade_name).getValue();
					var trade_id = null;
					trade_store.each(function(record){
						if (record.get('code')===tradeValue) {
							trade_id=record.get('id');
						}
					});
					
					second_trade_store.each(function(record){
						if (record.get('parentId')!=trade_id) {
							second_trade_store.remove(record);
						}
						//如果是用于新增，状态是无效的则移除（因为进入页面时加载了所有的二级行业）
						if ('OTHER'===me.userType&&
								!second_trade_field.readOnly&&!second_trade_field.disabled) {
							if (!record.get('status')) {
								second_trade_store.remove(record);
							}
						}
					});
					if(Ext.isEmpty(me.up('form').getForm().findField(me.trade_name).getValue())){
						this.store.removeAll();
					}
				},
				change:function(ths,newValue,oldValue,obj){
					if (Ext.isEmpty(newValue)) {
						ths.setValue(null);
					}
				}
			}
		} ];
		this.callParent();
	}
});
