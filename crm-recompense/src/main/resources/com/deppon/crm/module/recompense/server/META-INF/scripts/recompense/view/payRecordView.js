
/**
 * 
 * <p>
 * Description:付款记录<br />
 * </p>
 * @title PayRecordGrid
 * @author 侯斌飞
 * @version 0.1 2012-12-26
 */
Ext.define('PayRecordGrid',{
	extend:'SearchGridPanel',autoScroll:true,'region':'center',
	title:i18n('i18n.recompense.pay_rcd.title'),/* 付款记录 */
	initComponent:function(){
		var me = this;
		me.store = Ext.create('PayRecordStore',{
			autoLoad:false
		});
		me.store.on('beforeload',function(th,operation){
//			var waybillNum = Ext.getCmp(CONFIGNAME.get('waybillNumber')).getValue();
			var param = {'recompenseId':Ext.getCmp('recompenseDetailsUI').record.id};
			Ext.apply(operation,{
				params : param
			});
		});
		me.columns = me.getColumns();
		me.callParent(arguments);
	}
	,getColumns:function(){
		var me = this;
		return [
				{xtype:'rownumberer',header:i18n('i18n.recompense.num'),width:40},
				{
					header:i18n('i18n.recompense.pay_rcd.take_way')/*客户领款方式*/,
					width:110,dataIndex:'paymentMode',
					renderer:function(value){
						return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.CUST_DROWMONEY_TYPE);
					}
				},
				{
					header:i18n('i18n.recompense.pay_rcd.account_quale')/*账户性质*/,
					width:90,
					dataIndex:'accountProp'
						,
					renderer:function(value){
						return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.ACCOUNT_PROPERTIES);
					}
				},
				{header:i18n('i18n.recompense.pay_rcd.bankName')/*开户银行*/,width:90,dataIndex:'bankName'},
				{header:i18n('i18n.recompense.pay_rcd.branch_bankName')/*开户支行*/,width:90,dataIndex:'branchName'},
				{header:i18n('i18n.recompense.pay_rcd.userName')/*开户名*/,width:70,dataIndex:'openName'},
				{header:i18n('i18n.recompense.pay_rcd.account_number')/*开户账号*/,width:90,dataIndex:'account'},
				{header:i18n('i18n.recompense.pay_rcd.numberPhone_number')/*手机号码*/,width:90,dataIndex:'mobile'},
				{
					header:i18n('i18n.recompense.pay_rcd.apply_time')/*申请时间*/,
					width:90,dataIndex:'applyTime',
					renderer:function(value){
						if(value!=null){
							return Ext.Date.format(new Date(value),'Y-m-d H:i:s');
						}else{
							return null;
						}					
					}
				},
				{
					header:i18n('i18n.recompense.pay_rcd.pay_status')/*付款状态*/,
					width:90,dataIndex:'paymentStatus',
					renderer:function(value){
						return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.RECOMPENSE_STATUS);
					}
				}
			];
	}
});


/**
 * 
 * <p>
 * Description:交易记录 panelTab<br />
 * </p>
 * @title PayRecordModel
 * @author 侯斌飞
 * @version 0.1 2012-12-31
 */
Ext.define('PayRecordPanel',{
	extend:'TabContentPanel'
	,layout:'border'
	,padding:'10',
	title:i18n('i18n.recompense.pay_rcd.title'),/* 付款记录 */
	items:[
		Ext.create('PayRecordGrid',{id:'PayRecordGridId'})
	]
,
	listeners:{
		activate:function(me, eOpts ){
			Ext.getCmp('PayRecordGridId').getStore().load();
		}
	}
});

/**
*日期格式转换
*zouming
*/
function formatDate(value){
	if(value!=null){
		return Ext.Date.format(new Date(value),'Y-m-d H:i:s');
	}else{
		return null;
	}
}