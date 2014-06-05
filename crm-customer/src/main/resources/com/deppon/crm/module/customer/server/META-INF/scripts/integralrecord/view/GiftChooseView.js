var recordSearchControl = (CONFIG.get('TEST')) ? Ext
		.create('RecordSearchDataTest') : Ext.create('RecordSearchData');
		
/**
 * .
 * <p>
 * 礼品查询grid</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */
Ext.define('GiftChoooseGridPanel', {
	extend : 'PopupGridPanel',
	id : 'giftChooseGridPanel',
//	height:'85%',
	store : null,
	columns : null,
	listeners:{
		itemdblclick:function( th, record,  item, index,  e,eOpts ){
			Ext.getCmp('giftChoosePanel').getForm().loadRecord(record);
			Ext.getCmp('giftChooseView').close();
		}
	},
	initComponent : function() {
		var me = this;
		//分页工具栏
		me.bbar = me.getBBar();
		//列
		me.columns = me.getColumns();
		//选项模式
		me.selModel =  me.getSelModel();
		//store
		me.store = recordSearchControl.getGiftChooseStore();
		//store添加beforeload事件
		me.store.on('beforeload',function(store,operation,obj){
			var giftName=Ext.getCmp('gifChooseName').getValue();
			param={'giftName':giftName};
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
			store : me.store,
			//displayMsg : i18n('i18n.GiftChooseView.dataShowThat'),// i18n('i18n.order.displayMsg'),
			displayInfo : true
		})
	},
	getSelModel : function() {
		return  Ext.create('Ext.selection.CheckboxModel', {
			mode : 'SINGLE'
		})
	},
	getColumns : function() {
		return [ {
			xtype : 'rownumberer',
			width : 40,
			text : i18n('i18n.Integral.number')
		}, {
			header : i18n('i18n.Integral.giftName'),
			dataIndex : CONFIGNAME.get('gifChooseName')
		}, {
			hidden:true,
			dataIndex : CONFIGNAME.get('gifGiftNumber')
		}, {
			header : i18n('i18n.IntegralEx.needRecord'),
			dataIndex : CONFIGNAME.get('gifChooseNeedRecord')
		}, {
			header : i18n('i18n.IntegralEx.storeNumber'),
			dataIndex : CONFIGNAME.get('gifChooseStoreNumber')
		}, {
			header : i18n('i18n.IntegralEx.giftValue'),
			dataIndex : CONFIGNAME.get('gifChooseGiftValue')
		}, {
			header : i18n('i18n.IntegralEx.realValue'),
			dataIndex : CONFIGNAME.get('gifChooseRealValue')
		}, {
			header : i18n('i18n.IntegralEx.giftDesc'),
			dataIndex : CONFIGNAME.get('gifChooseGiftDesc')
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
Ext.define('GiftChooseSearchPanel', {
	extend : 'BasicHboxFormPanel',
	items : null,
	giftName:null,
//	width:300,
	initComponent : function() {
		var me = this
		me.items = me.getItems();
		this.callParent();
		Ext.getCmp('gifChooseName').setValue(me.giftName);
	},
	getItems : function() {
		return [
		    	{
					xtype:'basicformpanel',
					layout:{
						type:'table',
						columns:1
					},
					defaultType:'textfield',
					defaults:{
						labelWidth:60,
						width:170
					},
					items:[{
						fieldLabel:i18n('i18n.Integral.giftName'),
						id:'gifChooseName',
						listeners:{
							specialkey:function(th,  e,  eOpts ){
								if (e.getKey()==Ext.EventObject.ENTER) {
									Ext.getCmp('giftChooseGridPanel').getStore().load();
								}
							}
						}
					}]
				},{
					xtype:'normalbuttonpanel', 
					width:100,
					margin:'0 0 0 10',
					items:[{
						xtype:'button',
						text:i18n('i18n.PotentialCustManagerView.search'),
						handler:function(){
							Ext.getCmp('giftChooseGridPanel').getStore().load();
						}
						}]
				}]
	}
});


Ext.define('GiftChosePanel',{
	extend:'BasicPanel',
	giftName:null,
	giftChooseSearchPanel : null,
	giftChoooseGridPanel : null,
	layout:{
        type:'vbox',
        align:'stretch'
    },
	initComponent:function(){
		var me = this;
		me.giftChooseSearchPanel = new GiftChooseSearchPanel({'giftName':me.giftName});
		me.giftChoooseGridPanel = new GiftChoooseGridPanel();
		me.items = [{
			xtype:'basicpanel',
			height:36,
			items:[me.giftChooseSearchPanel]
		},{
			xtype:'basicpanel',
			flex:1,
			items:[
			       me.giftChoooseGridPanel
			       ]
		}];
		this.callParent();
	}
});
/**
 * .
 * <p>
 * 积分查询window</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */

Ext.define('GiftChooseView', {
	extend : 'PopWindow',
	layout:'fit',
	width:698,
	height:310,
	giftName:null,
	id:'giftChooseView',
	title:i18n('i18n.IntegralEx.giftChoose'),
	initComponent:function(){
		this.items=[new GiftChosePanel({'giftName':this.giftName})];
		this.fbar = this.getFbar();
		this.callParent();
	},
	getFbar:function(){ 
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.IntegralEx.choose'),
			handler:function(){
				me.selectGift();
			}
		}];
	},
	//选择礼品
	selectGift:function(){
		var selections = Ext.getCmp('giftChooseGridPanel').getSelectionModel().getSelection();
		if (selections.length!=1) {
			MessageUtil.showErrorMes(i18n('i18n.IntegralEx.chooseOneGift'));
			return;
		};
		this.close();
		Ext.getCmp('giftChoosePanel').getForm().loadRecord(selections[0]);
	}
});

