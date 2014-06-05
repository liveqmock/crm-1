var recordSearchControl = (CONFIG.get('TEST')) ? Ext
		.create('RecordSearchDataTest') : Ext.create('RecordSearchData');

/**
 * .
 * <p>
 * 积分查询主页面</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */
Ext.onReady(function() {
	var params = ['AWARD_TYPE',// 奖励类型
	              	'CUST_RULETYPE',
	              	'INTEG_AWARD_TYPE',
	              	 //运输类型
                    'TRANS_TYPE',
	              	 //订单来源
                	 'ORDER_SOURCE',
                    //付款方式
                    'PAY_WAY',
	              	'CUST_SEND_STATUS'
					];
		initDataDictionary(params);//数据字典
	 initUser();
	new RecordSearchView();
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

Ext.define('ContactRecordGridPanel', {
	extend : 'PopupGridPanel', // --第一步,继承PopupGridPanel
	id : 'contactRecordGridPanel',
	flex:1,
	store:null,
	initComponent : function() {
		var me = this;
		me.columns = me.getColumns();
		 me.store=recordSearchControl.getContactRecordStore();
		me.store.on('beforeload',function(store,operation,obj){
				var record = Ext.getCmp('recordGridPanel').getSelectionModel().getSelection();
				var searchParams=null;
				var searchType = Ext.getCmp('searchType').getValue();
				var contactId = Ext.getCmp('conditionValue').getValue();
				if (record.length==1) {
					searchParams = {'condition.memberId':record[0].data.member.id};
				}else if (searchType==4&&!Ext.isEmpty(contactId)) {
					searchParams = {'condition.contactId':contactId};
				}else{
					return;
				}
				Ext.apply(operation,{
					params : searchParams
				});
			});
		me.store.on('load',function(store,records){
			var girdcount = 0;
			store.each(function(record){
				if(record.get(CONFIGNAME.get('conContact')).isMainLinkMan){
					var cells =  me.getView().getNodes()[girdcount].children;
		               for(var i= 0;i<cells.length;i++){
		            	  cells[i].style.backgroundColor='#FF0000';
		               }
				}
				girdcount++;
			})
		});
		me.bbar = me.getBBar();
		this.callParent();
	},
	// 分页工具栏
	getBBar : function() {
		var me = this;
		return Ext.create('Ext.toolbar.Paging', {
			store :me.store,
//			displayMsg : i18n('i18n.GiftChooseView.dataShowThat'),// i18n('i18n.order.displayMsg'),
			displayInfo : true
		})
	},
	// 表格列
	getColumns : function() {
		return columns = [ {
			xtype : 'rownumberer',
			width : 40,
			text : i18n('i18n.Integral.number')
		}, {
			header : i18n('i18n.MemberCustEditView.contactNo'),
			dataIndex : CONFIGNAME.get('conContact'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.number;
				}
			}
		}, {
			header : i18n('i18n.Integral.contatct'),
			dataIndex : CONFIGNAME.get('conContact'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.name;
				}
			}
		}, {
			header :  i18n('i18n.Integral.currentAviableRecrod'),
			dataIndex : CONFIGNAME.get('conCurrentUsableScorePeriod')
		}, {
			header : i18n('i18n.Integral.currentUsedRecrod'),
			dataIndex : CONFIGNAME.get('conUsedReocordCurrentPeriod')
		}, {
			header : i18n('i18n.Integral.currentAllRecrod'),
			dataIndex : CONFIGNAME.get('conTotalReocordCurrentPeriod')
		}, {
			header : i18n('i18n.Integral.allRecrod'),
			dataIndex : CONFIGNAME.get('conAllReocord')
		}, {
			header : i18n('i18n.Integral.allUsedRecord'),
			dataIndex : CONFIGNAME.get('conTotalUsedReocord')
		}, {
			header : i18n('i18n.Integral.lastestRecordTime'),
			dataIndex : CONFIGNAME.get('conLastRecordTime'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return Ext.Date.format(new Date(value), 'Y-m-d');
				}
			}
		}
		];
	}
});

/**
 * .
 * <p>
 * 联系人积分tab</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */
Ext.define('ContactTabPanel', {
	extend : 'TabContentPanel',
	title : i18n('i18n.Integral.contactRecord'),
	flex:1,
	layout:'fit',
	id:'contactTabPanel',
	contactRecordGridPanel : null,
	initComponent : function() {
		var me = this;
//		me.on('beforeactivate',function(th,obj){
//			me.contactRecordGridPanel.store().load();
//		});
		me.contactRecordGridPanel = new ContactRecordGridPanel();
		me.items = [ me.contactRecordGridPanel ];
		this.callParent();
	}
});

/**
 * .
 * <p>
 * 礼品申请grid</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */

Ext.define('PresentApplyGridPanel', {
	extend : 'PopupGridPanel', // --第一步,继承PopupGridPanel
	id : 'presentApplyGridPanel',
	flex:1,
	store:null,
	// width:925,
	// height:80,
	initComponent : function() {
		var me = this;
		me.columns = me.getColumns();
		me.store=recordSearchControl.getPresentApplyStore();
		me.store.on('beforeload',function(store,operation,obj){
			var record = Ext.getCmp('recordGridPanel').getSelectionModel().getSelection();
			var searchParams=null;
			var searchType = Ext.getCmp('searchType').getValue();
			var contactId = Ext.getCmp('conditionValue').getValue();
			if (record.length==1) {
				searchParams = {'condition.memberId':record[0].data.member.id};
			}else if (searchType==4&&!Ext.isEmpty(contactId)) {
				searchParams = {'condition.contactId':contactId};
			}else{
				return;
			}
			Ext.apply(operation,{
				params : searchParams
			});
		});
		me.bbar = me.getBBar();
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
	getColumns : function() {
		return columns = [ {
			xtype : 'rownumberer',
			width : 40,
			text : i18n('i18n.Integral.number')
		}, {
			header : i18n('i18n.MemberCustEditView.contactNo'),
			dataIndex : CONFIGNAME.get('preContact'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.number;
				}
			}
		}, {
			header : i18n('i18n.Integral.contatct'),
			dataIndex : CONFIGNAME.get('preContact'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.name;
				}
			}
		}, {
			header : i18n('i18n.Integral.giftName'),
			dataIndex : CONFIGNAME.get('prePresentName'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.giftName;
				}
			}
		}, {
			header : i18n('i18n.Integral.exchangeNum'),
			dataIndex : CONFIGNAME.get('preExchangeNumber')
		}, {
			header : i18n('i18n.Integral.userdRecord'),
			dataIndex : CONFIGNAME.get('preConsumeRecord')
		}, {
			header : i18n('i18n.Integral.sendDate'),
			dataIndex : CONFIGNAME.get('preIssueTime'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return Ext.Date.format(new Date(value), 'Y-m-d');
				}
			}
		}, {
			header : i18n('i18n.Integral.exchangeDate'),
			dataIndex : CONFIGNAME.get('preExchangeTime'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return Ext.Date.format(new Date(value), 'Y-m-d');
				}
			}
		}, {
			header : i18n('i18n.Integral.isSend'),
			dataIndex : CONFIGNAME.get('preIsIssue'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.CUST_SEND_STATUS);
				}
			}
		}, {
			header : i18n('i18n.Integral.exchagerNo'),
			dataIndex : CONFIGNAME.get('preExchangerNo')
		}, {
			header : i18n('i18n.Integral.workflowNo'),
			dataIndex : CONFIGNAME.get('preWorkflowNumber')
		} ];
	}
});

/**
 * .
 * <p>
 * 礼品申请记录tab</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */
Ext.define('PresentApplyTab', {
	extend : 'TabContentPanel', // --,定义页签容器里的子页签,其继承TabContentPanel
	title : i18n('i18n.Integral.giftApplyHistory'),
	flex:1,
	layout:'fit',
	presentApplyGridPanel : null,
	listeners:{
		beforeactivate:function(th,obj){
			var me =this;
			var searchType = Ext.getCmp('searchType').getValue();
//			if (searchType=='4') {
//				return;
//			}
			me.presentApplyGridPanel.getStore().load();
		}
	},
	initComponent : function() {
		var me = this;
		me.presentApplyGridPanel = new PresentApplyGridPanel();
		me.items = [ me.presentApplyGridPanel ];
		this.callParent();
	}
});

/**
 * .
 * <p>
 * 积分运单grid</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */

Ext.define('RecordWaybillGridPanel', {
	extend : 'PopupGridPanel', // --第一步,继承PopupGridPanel
	store : null,
	flex:1,
	id : 'recordWaybillGridPanel',
	// width:925,
	// height:80,
	initComponent : function() {
		var me = this;
		me.columns = me.getColumns();
		me.store = recordSearchControl.getWaybillRecordStore();
		me.store.on('load',function(store,records){
			records = store.data;
			var totalRecord =0;
			for ( var i = 0; i < records.length; i++) {
				totalRecord+=records.getAt(i).get(CONFIGNAME.get('wayRecord'));
			}
			Ext.getCmp('wayTotalNumber').setValue(store.getTotalCount());
			Ext.getCmp('wayTatalRecord').setValue(totalRecord);
		});
		me.store.on('beforeload',function(store,operation,obj){
			var belongDept = Ext.getCmp('integeralbelongdeptcombox').getValue();
			var record = Ext.getCmp('recordGridPanel').getSelectionModel().getSelection();
			var searchParams=null;
			var searchType = Ext.getCmp('searchType').getValue();
			var contactId = Ext.getCmp('conditionValue').getValue();
			if (record.length==1) {
				searchParams = {'condition.memberId':record[0].data.member.id,
								'condition.waybillStartDate' : startDate,
								'condition.waybillEndDate' : endDate};
			}else if (searchType==4&&!Ext.isEmpty(contactId)) {
				searchParams = {'condition.contactId':contactId,
									'condition.waybillStartDate' : startDate,
									'condition.waybillEndDate' : endDate};
			}else{
				return;
			}
			var startDate = Ext.getCmp('waybillDate').getValue();
			var endDate = Ext.getCmp('waybillEndDate').getValue();
			//构造查询参数
//			var searchParams =null;
//			if (searchType==4) {
//				searchParams = {
//						'condition.contactId' : contactId,
//						'condition.waybillStartDate' : startDate,
//						'condition.waybillEndDate' : endDate
//				};
//			}else{
//				searchParams = {
//						'condition.deptId' : belongDept,
//						'condition.memberName' : memberName,
//						'condition.contactName' : contactName,
//						'condition.memberNum' : memberNum,
//						'condition.waybillStartDate' : startDate,
//						'condition.waybillEndDate' : endDate,
//						'condition.contactId' : contactId
//				};
//			}
			Ext.apply(operation,{
				params : searchParams
			});
		});
		me.bbar = me.getBBar();
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
	getColumns : function() {
		return columns = [ {
			xtype : 'rownumberer',
			width : 40,
			text : i18n('i18n.Integral.number')
		}, {
			header : i18n('i18n.Integral.contactNumber'),
			dataIndex : CONFIGNAME.get('wayContact'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.number;
				}
			}
		}, {
			header : i18n('i18n.Integral.contatct'),
			dataIndex : CONFIGNAME.get('wayContact'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.name;
				}
			}
		}, {
			header : i18n('i18n.Integral.waybillNumber'),
			dataIndex : CONFIGNAME.get('wayWaybillNumber')
		}, {
			header : i18n('i18n.ScatterCustManagerView.startDept'),
			dataIndex : CONFIGNAME.get('wayStartStation')
		}, {
			header : i18n('i18n.ScatterCustManagerView.arriveDept'),
			dataIndex : CONFIGNAME.get('wayEndStation')
		}, {
			header : i18n('i18n.Integral.transType'),
			dataIndex : CONFIGNAME.get('wayTransType'),
			renderer : function(value){
        		if (!Ext.isEmpty(value)) {
        			return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.TRANS_TYPE);
        		}
			}
		}, {
			header : i18n('i18n.MemberCustEditView.payWay'),
			dataIndex : CONFIGNAME.get('wayPaymentType'),
			renderer : function(value){
	        		if (!Ext.isEmpty(value)) {
	        			return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.PAY_WAY);
				}
	        }
		}, {
			header : i18n('i18n.Integral.channelOrder'),
			dataIndex : CONFIGNAME.get('wayChannelNumber'),
			renderer : function(value){
        		if (!Ext.isEmpty(value)) {
        			return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.ORDER_SOURCE);
			}
        }
		}, {
			header : i18n('i18n.Integral.totalFee'),
			dataIndex : CONFIGNAME.get('wayTotalPrice')
		}, {
			header : i18n('i18n.Integral.recordRate'),
			dataIndex : CONFIGNAME.get('wayIntegralRate')
		}, {
			header : i18n('i18n.Integral.record'),
			dataIndex : CONFIGNAME.get('wayRecord')
		}, {
			header : i18n('i18n.Integral.recordRole'),
			dataIndex : CONFIGNAME.get('wayReordRole')
		}, {
			header : i18n('i18n.Integral.recordDesc'),
			dataIndex : CONFIGNAME.get('wayRecordDesc')
		}, {
			header : i18n('i18n.Integral.recordDate'),
			dataIndex : CONFIGNAME.get('waylastRecordTime'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return Ext.Date.format(new Date(value), 'Y-m-d');
				}
			}
		} ];
	}
});

/**
 * .
 * <p>
 * 已积分运单查询panel</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */
Ext.define('RecordWaybillSearchPanel', {
	extend : 'BasicHboxFormPanel',
	height : 25,
	// width:800,
//	flex:1,
	items : null,
	initComponent : function() {
		var me = this
		me.items = me.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [ {
			xtype : 'basicformpanel',
			layout : {
				type : 'table',
				columns : 4
			},
			defaultType : 'textfield',
			defaults : {
			labelWidth : 60,
			width:155,
			listeners:{
				specialkey:function(th,  e,  eOpts ){
					if (e.getKey()==Ext.EventObject.ENTER) {
						me.searchWaybill();
					}
				}
			}
			},
			items : [ {
				xtype : 'textfield',
				fieldLabel : i18n('i18n.Integral.totalNumber'),
				readOnly:true,
				maxLength:20,
				maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20),
				id : 'wayTotalNumber'
			}, {
				xtype : 'textfield',
				fieldLabel : i18n('i18n.Integral.totaLRecord'),
				readOnly:true,
				maxLength:20,
				maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20),
				id : 'wayTatalRecord'
			}, {
				xtype : 'datefield',
				width:210,
				labelWidth : 80,
				value : new Date(new Date() - 31 * 24 * 3600 * 1000),
				fieldLabel : i18n('i18n.Integral.waybillBeginDate'),
				id : 'waybillDate'
			}, {
				labelWidth : 20,
				width:150,
				xtype : 'datefield',
				value : new Date(),
				fieldLabel : i18n('i18n.Integral.waybillEndDate'),
				id : 'waybillEndDate'
			} ]
		}, {
			xtype : 'btnpanel',
			items : [ {
				xtype : 'button',
				text : i18n('i18n.PotentialCustManagerView.search'),
				handler:function(){
					me.searchWaybill();
				}
			} ]
		} ]
	},
	searchWaybill:function(){
		var store = Ext.getCmp('recordWaybillGridPanel').getStore();
		store.load();
//		records = store.data;
//		var totalRecord =0;
//		for ( var i = 0; i < records.length; i++) {
//			totalRecord+=records.getAt(i).get(CONFIGNAME.get('wayRecord'));
//		}
//		Ext.getCmp('wayTotalNumber').setValue(store.getTotalCount());
//		Ext.getCmp('wayTatalRecord').setValue(totalRecord);
	}
});
/**
 * .
 * <p>
 * 已积分运单tab</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */
Ext.define('RecordWaybillTab', {
	extend : 'TabContentPanel', // --定义页签容器里的子页签,其继承TabContentPanel
	title : i18n('i18n.Integral.recordWaybill'),
	flex:1,
//	layout:'fit',
	layout:{
		type:'vbox',
		align:'stretch'
	},
	listeners:{
		beforeactivate:function(th,obj){
			var me =this;
			var searchType = Ext.getCmp('searchType').getValue();
//			if (searchType=='4') {
//				return;
//			}
//			me.recordWaybillGridPanel.getStore().load();
		}
	},
	recordWaybillGridPanel : null,
	recordWaybillSearchPanel : null,
	initComponent : function() {
		var me = this;
		me.recordWaybillGridPanel = new RecordWaybillGridPanel();
		me.recordWaybillSearchPanel = new RecordWaybillSearchPanel();
		me.items = [ me.recordWaybillSearchPanel, me.recordWaybillGridPanel ];
		this.callParent();
	}
});

/**
 * .
 * <p>
 * 奖励积分grid</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */

Ext.define('AwardRecordGridPanel', {
	extend : 'PopupGridPanel', // --第一步,继承PopupGridPanel
	store : null,
	flex:1,
	id : 'awardRecordGridPanel',
	// width:925,
	// height:80,
	initComponent : function() {
		var me = this;
		me.columns = me.getColumns();
		me.store=recordSearchControl.getAwardRecordStore();
		me.store.on('beforeload',function(store,operation,obj){
			var record = Ext.getCmp('recordGridPanel').getSelectionModel().getSelection();
			var searchParams=null;
			var searchType = Ext.getCmp('searchType').getValue();
			var contactId = Ext.getCmp('conditionValue').getValue();
			if (record.length==1) {
				searchParams = {'condition.memberId':record[0].data.member.id};
			}else if (searchType==4&&!Ext.isEmpty(contactId)) {
				searchParams = {'condition.contactId':contactId};
			}else{
				return;
			}
			Ext.apply(operation,{
				params : searchParams
			});
		});
		me.bbar = me.getBBar();
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
	getColumns : function() {
		return columns = [ {
			xtype : 'rownumberer',
			width : 40,
			text : i18n('i18n.Integral.number')
		}, {
			header : i18n('i18n.Integral.contactNumber'),
			dataIndex : CONFIGNAME.get('awaContact'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.number;
				}
			}
		}, {
			header : i18n('i18n.Integral.contatct'),
			dataIndex : CONFIGNAME.get('awaContact'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.name;
				}
			}
		}, {
			header : i18n('i18n.Integral.rewardRecordDate'),
			dataIndex : CONFIGNAME.get('awaRewardDate'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return Ext.Date.format(new Date(value), 'Y-m-d');
				}
			}
		}, {
			header : i18n('i18n.Integral.rewardRecordType'),
			dataIndex : CONFIGNAME.get('awaRewardIntegral'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return DpUtil.changeDictionaryCodeToDescrip(value.ruletype,DataDictionary.AWARD_TYPE);
				}
			}
		}, {
			header :  i18n('i18n.Integral.awardName'),
			dataIndex : CONFIGNAME.get('awaRewardIntegral'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.rulename;
				}
			}
//		}, {
//			header : i18n('i18n.IntegralRuleEdit.recordInvalidateTime'),
//			dataIndex : CONFIGNAME.get('awarewardIntegral')
		}, {
			header :  i18n('i18n.Integral.awardBase'),
			dataIndex : CONFIGNAME.get('awaIntegralBasicNumber')
		}, {
			header : i18n('i18n.Integral.recordRate'), 
			dataIndex : CONFIGNAME.get('awaRewardIntegral'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.fraction;
				}
			}
		}, {
			header :  i18n('i18n.Integral.awardRecord'),
			dataIndex : CONFIGNAME.get('awaRewardIntegral'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.pointvalue;
				}
			}
		}, {
			header :  i18n('i18n.Integral.awardRecordType'),
			dataIndex : CONFIGNAME.get('awaRewardIntegral'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return DpUtil.changeDictionaryCodeToDescrip(value.integType,DataDictionary.CUST_RULETYPE);
				}
			}
		}, {
			header : i18n('i18n.Integral.awardRecordDesc'),
			dataIndex : CONFIGNAME.get('awaRewardIntegral'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.pointdesc;
				}
			}
		} ];
	}
});
/**
 * .
 * <p>
 * 奖励积分列表tab</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */
Ext.define('AwardRecordTab', {
	extend : 'TabContentPanel', // --定义页签容器里的子页签,其继承TabContentPanel
	title :  i18n('i18n.Integral.awardRecordList'),
	flex:1,
	layout:'fit',
	awardRecordGridPanel : null,
	listeners:{
		beforeactivate:function(th,obj){
			var searchType = Ext.getCmp('searchType').getValue();
//			if (searchType=='4') {
//				return;
//			}
			var me =this;
			me.awardRecordGridPanel.getStore().load();
		}
	},
	initComponent : function() {
		var me = this;
		me.awardRecordGridPanel = new AwardRecordGridPanel();
		me.items = [ me.awardRecordGridPanel ];
		this.callParent();
	}
});

/**
 * .
 * <p>
 * 调整积分grid</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */

Ext.define('AdjustRecordGridPanel', {
	extend : 'PopupGridPanel', // --第一步,继承PopupGridPanel
	store : null,
	flex:1,
	id : 'adjustRecordGridPanel',
	// width:925,
	// height:80,
	initComponent : function() {
		var me = this;
		me.columns = me.getColumns();
		me.store=recordSearchControl.getAdjustRecordStore();
		me.store.on('beforeload',function(store,operation,obj){
			var record = Ext.getCmp('recordGridPanel').getSelectionModel().getSelection();
			var searchParams=null;
			var searchType = Ext.getCmp('searchType').getValue();
			var contactId = Ext.getCmp('conditionValue').getValue();
			if (record.length==1) {
				searchParams = {'condition.memberId':record[0].data.member.id};
			}else if (searchType==4&&!Ext.isEmpty(contactId)) {
				searchParams = {'condition.contactId':contactId};
			}else{
				return;
			}
			Ext.apply(operation,{
				params : searchParams
			});
		});
		me.bbar = me.getBBar();
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
	getColumns : function() {
		return columns = [ {
			xtype : 'rownumberer',
			width : 40,
			text :  i18n('i18n.Integral.number')
		}, {
			header : i18n('i18n.Integral.contactNumber'),
			dataIndex : CONFIGNAME.get('adjcontact'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.number;
				}
			}
		}, {
			header : i18n('i18n.Integral.contatct'),
			dataIndex : CONFIGNAME.get('adjcontact'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.name;
				}
			}
		}, {
			header :  i18n('i18n.Integral.ajustDate'),
			dataIndex : CONFIGNAME.get('adjAdjustDate'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return  Ext.Date.format(new Date(value),'Y-m-d');
				}
			}
		}, {
			header : i18n('i18n.Integral.ajustType'), 
			dataIndex : CONFIGNAME.get('adjAdjustType')
		}, {
			header :  i18n('i18n.Integral.ajustRecord'),
			dataIndex : CONFIGNAME.get('adjAdjustRecord')
		}, {
			header : i18n('i18n.Integral.ajustTo'),
			dataIndex : CONFIGNAME.get('adjAdjustGo'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.name;
				}
			}
		}, {
			header :  i18n('i18n.Integral.ajustFrom'),
			dataIndex : CONFIGNAME.get('adjAdjustFrom'),
			renderer:function(value){
				if (!Ext.isEmpty(value)) {
					return value.name;
				}
			}
		} ];
	}
});
/**
 * .
 * <p>
 * 调整积分tab</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */
Ext.define('AdjustRecordTab', {
	extend : 'TabContentPanel', // --定义页签容器里的子页签,其继承TabContentPanel
	title :  i18n('i18n.Integral.ajustRecord'),
	flex:1,
	layout:'fit',
	adjustRecordGridPanel : null,
	listeners:{
		beforeactivate:function(th,obj){
			var searchType = Ext.getCmp('searchType').getValue();
//			if (searchType=='4') {
//				return;
//			}
			var me =this;
			me.adjustRecordGridPanel.getStore().load();
		}
	},
	initComponent : function() {
		var me = this;
		me.adjustRecordGridPanel = new AdjustRecordGridPanel();
		me.items = [ me.adjustRecordGridPanel ];
		this.callParent();
	}
});

/**
 * .
 * <p>
 * 会员积分查询grid</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */
Ext.define('RecordGridPanel', {
	extend : 'SearchGridPanel',
	id : 'recordGridPanel',
	flex:1,
//	height : '50%',
	store : null,
	columns : null,
	listeners:{
		itemclick:function(th,record,item,e,obj){
			Ext.getCmp('recordDetailTab').setActiveTab(0);
			//清空以积分运单积分
			Ext.getCmp('presentApplyGridPanel').getStore().removeAll();
			Ext.getCmp('recordWaybillGridPanel').getStore().removeAll();
			Ext.getCmp('adjustRecordGridPanel').getStore().removeAll();
			Ext.getCmp('awardRecordGridPanel').getStore().removeAll();
			//重新加载联系人积分信息
			Ext.getCmp('contactRecordGridPanel').store.load();
			Ext.getCmp('wayTotalNumber').setValue();
			Ext.getCmp('wayTatalRecord').setValue();
		}
	},
	initComponent : function() {
		var me = this;
		me.columns = me.getColumns();
		me.selModel =  me.getSelModel();
		me.store = recordSearchControl.getSearchRecordStore();
		me.bbar = me.getBBar();
		me.store.on('beforeload',function(store,operation,obj){
					var belongDept = Ext.getCmp('integeralbelongdeptcombox').getValue();
					var searchType = Ext.getCmp('searchType').getValue();
					var conditionValue = Ext.getCmp('conditionValue').getValue();
					var memberName = null;
					var contactName=null;
					var memberNum = null;
					var contactId = null;
					//根据查询类型设置查询条件的值
					//查询条件为会员编号
					if (searchType==1) {
						memberNum=conditionValue;
						//会员名称
					}else if (searchType==2) {
						memberName=conditionValue;
						//联系人姓名
					}else if (searchType==3) {
						contactName =conditionValue;
						//联系人编码
					}else if (searchType==4) {
						contactId=conditionValue;
					}
					var startDate = Ext.getCmp('waybillDate').getValue();
					var endDate = Ext.getCmp('waybillEndDate').getValue();
					//构造查询参数
					var searchParams = {
						'condition.deptId' : belongDept,
						'condition.memberName' : memberName,
						'condition.contactName' : contactName,
						'condition.memberNum' : memberNum,
						'condition.contactId' : contactId
					};
					Ext.apply(operation,{
						params : searchParams
					});
		});
		this.callParent();
	},
	//行选中模式
	getSelModel : function() {
		return  Ext.create('Ext.selection.CheckboxModel', {
			mode : 'SINGLE'
		})
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
	getColumns : function() {
		return [ {
			xtype : 'rownumberer',
			width : 40,
			text : i18n('i18n.Integral.number')
		}, {
			header :  i18n('i18n.ScatterCustManagerView.memberNo'),
			dataIndex : CONFIGNAME.get('member'),
			renderer : function(value) {
				if (value != null && value != undefined) {
					return value.custNumber;
				}
			}
		}, {
			header : i18n('i18n.Integral.menberName'), 
			dataIndex : CONFIGNAME.get('member'),
			renderer : function(value) {
				if (value != null && value != undefined) {
					return value.custName;
				}
			}
		}, {
			header : i18n('i18n.Integral.dept'), 
			dataIndex : CONFIGNAME.get('member'),
			renderer : function(value) {
				if (value != null && value != undefined) {
					return value.deptName;
				}
			}
		}, {
			header :  i18n('i18n.Integral.currentAviableRecrod'),
			dataIndex : CONFIGNAME.get('currentUsableScore')
		}, {
			header : i18n('i18n.Integral.currentUsedRecrod'), 
			dataIndex : CONFIGNAME.get('currentToUsesScore')
		}, {
			header :  i18n('i18n.Integral.currentAllRecrod'),
			dataIndex : CONFIGNAME.get('currentTotalScore')
		}, {
			header : i18n('i18n.Integral.allRecrod'),
			dataIndex : CONFIGNAME.get('totalRecord')
		}, {
			header :  i18n('i18n.Integral.allUsedRecord'),
			dataIndex : CONFIGNAME.get('totalToUsesScore')
		}, {
			header :  i18n('i18n.Integral.isOnlyMainExchage'),
			dataIndex : CONFIGNAME.get('member'),
			renderer:function(value){
				if (value!=null&&value.isRedeempoints!=null) {
					if (value.isRedeempoints) {
						return i18n('i18n.ChangeContactAffiliatedRelationView.yes');
					}else{
						return i18n('i18n.ChangeContactAffiliatedRelationView.no');
					}
				}
			}
		}, {
			header :  i18n('i18n.Integral.lastestRecordTime'),
			dataIndex : CONFIGNAME.get('lastRecordTime'),
			renderer:function(value){
				if (value!=null) {
					return  Ext.Date.format(new Date(value),'Y-m-d');
				}
			}
		}
	 ];
	}
})
/**
 * .
 * <p>
 * 积分查询条件panel</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */
Ext.define('SearchConditionPanel', {
	extend : 'SearchFormPanel',
	items : null,
	initComponent : function() {
		var me = this
		me.items = me.getItems();
//		if(Ext.isEmpty(User)||Ext.isEmpty(User.roleids)||me.isArrayContaintChar('4',User.roleids)){
//			
//		}
		this.callParent();
		Ext.getCmp('searchType').setValue(Ext.getCmp('searchType').getStore().getAt(3).get('id'));
	},
	isArrayContaintChar:function(str,array){
		for ( var i = 0; i < array.length; i++) {
			if (str==array[0]) {
				return true;
			}
		}
		return false;
	},
	search:function(){
		var searchType = Ext.getCmp('searchType').getValue();
		var conditionValue = Ext.getCmp('conditionValue').getValue();
		var checkStringNull=function(str){
			return str==null||str==undefined||str.trim()=='';
		}
//		if (checkStringNull(conditionValue)&&!checkStringNull(searchType)) {
//			DpUtil.showMessage(i18n('i18n.RecordSearchView.pleaseInputSearchCondition'));
//			return;
//		}
		//清空store数据，重新加载
		Ext.getCmp('contactRecordGridPanel').getStore().removeAll();
		Ext.getCmp('recordGridPanel').getStore().removeAll();
		Ext.getCmp('presentApplyGridPanel').getStore().removeAll();
		Ext.getCmp('recordWaybillGridPanel').getStore().removeAll();
		Ext.getCmp('adjustRecordGridPanel').getStore().removeAll();
		Ext.getCmp('awardRecordGridPanel').getStore().removeAll();
			
		//清空以积分运单积分
		Ext.getCmp('wayTotalNumber').setValue("");
		Ext.getCmp('wayTatalRecord').setValue("");
		//联系人grid活动
		Ext.getCmp('recordDetailTab').setActiveTab(0);
		Ext.getCmp('contactRecordGridPanel').store.load();
		//重新加载数据
		Ext.getCmp('recordGridPanel').getStore().loadPage(1);
	},
	getItems : function() {
		var me =this;
		return [ {
			xtype : 'basicformpanel',
			layout : {
				type : 'table',
				columns : 4
			},
			defaultType : 'textfield',
			defaults : {
				labelWidth : 60,
				listeners:{
					specialkey:function(th,  e,  eOpts ){
						if (e.getKey()==Ext.EventObject.ENTER) {
							me.search();
						}
					}
				}
			},
			items : [
					{
						xtype : 'belongdeptcombox',
						name:'belongdeptcombox',
						id:'integeralbelongdeptcombox'
					},{
						width : 200,
						fieldLabel :  i18n('i18n.Integral.searchCondition'),
						id : 'searchType',
						xtype : 'combo',
						displayField : 'condition',
						valueField : 'id',
						queryMode : 'local',
						store : recordSearchControl.getSearchConditionStore(),
						listeners:{
							scope:me,
							select:me.selectEvent
						}
					},
					{
						xtype : 'textfield',
						id : 'conditionValue'
					},
					{
						xtype : 'btnpanel',
						width : 60,
						defaults : {
							margins : '0 0 5 0'
						},
						items : [ {
							xtype : 'button',
							text : i18n('i18n.PotentialCustManagerView.search'),
							handler : function() {
								me.search();
							}
						} ]
					} ]
		} ]
	},
	//搜索条件
	selectEvent:function(combox,records){
		if(records.length > 0){
			var conditionValue = Ext.getCmp('conditionValue');
			if('2' == combox.getValue()){
				conditionValue.maxLength = 80;
    	    	conditionValue.maxLengthText = i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80);
    	    	combox.up('form').doLayout();
			}else {
				conditionValue.maxLength = 20;
    	    	conditionValue.maxLengthText = i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20);
    	    	combox.up('form').doLayout();
			}
		}
	}
});
/**
 * .
 * <p>
 * 积分详情tabContent</br>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-19
 */
Ext.define('RecordDetailTab', {
	extend : 'NormalTabPanel',
	border : true,
	layout:'fit',
	id:'recordDetailTab',
//	height : '50%',
	autoScroll : true,
	contactTabPanel : null,
	presentApplyTab : null,
	recordWaybillTab : null,
	awardRecordTab : null,
	adjustRecordTab : null,

	initComponent : function() {
		var me = this;
		me.items = me.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		me.contactTabPanel = new ContactTabPanel();
		me.presentApplyTab = new PresentApplyTab();
		me.recordWaybillTab = new RecordWaybillTab();
		me.awardRecordTab = new AwardRecordTab();
		me.adjustRecordTab = new AdjustRecordTab();
		return [ me.contactTabPanel, me.presentApplyTab, me.recordWaybillTab,
				me.awardRecordTab, me.adjustRecordTab ];
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
Ext.define('RecordSearchPanel',{
	extend:'BasicPanel',
	searchConditionPanel:null,
	recordGridPanel:null,
	recordDetailTab:null,
	layout:{
        type:'vbox',
        align:'stretch'
    },
	items:null,
	initComponent:function(){
		var me = this;
		me.searchConditionPanel= new SearchConditionPanel();
		me.recordGridPanel= new RecordGridPanel();
		me.recordDetailTab= new RecordDetailTab();
		me.items = [{
			xtype:'basicpanel',
			height:45,
			items:[me.searchConditionPanel]
		},{
			xtype:'basicpanel',
			defaults : {
				margins : '5 0 0 0'
			},
			flex:1,
			items:[me.recordGridPanel]
		},{
			xtype:'basicpanel',
			flex:1,
			layout:'fit',
			items:[me.recordDetailTab]
		}];
		this.callParent();
	}
});
Ext.define('RecordSearchView', {
	extend : 'Ext.container.Viewport',
	layout:'fit',
	items:[new RecordSearchPanel() ]

})

