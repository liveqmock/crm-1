/*
 * 360视图tab1打印
 * @author Rock( 13816470956 )
 */
var MemberViewDataObj 	= (CONFIG.get("TEST")) ? new MemberViewDataTest() : new MemberViewData(),
	CustNumber 			= null,
	CustId 				= null;
Ext.onReady(function() {
	Ext.form.Field.prototype.msgTarget='side';
	var keys = ['ORDER_SOURCE',										//订单来源
                'ORDER_STATUS',										//订单状态
                'CONTACT_STATUS',									//合同状态
                'MAINTAIN_WAY',										//维护方式
                'CUSTOMER_IDEA',									//需求及问题
                'RECKON_WAY',										//结款方式(合同)
                'PRIVILEGE_TYPE', 									//优惠类型
                'ACCOUNT_NATURE',									//账户性质
                'ACCOUNT_USE',										//账户用途
                'ADDRESS_TYPE',										//地址类型
                'PAY_WAY',											//付款方式
                'GENDER',											//男/女
                'LOGIST_DECI',										//物流决定权
                'BILL_REQUIRE',										//发票要求
        	  	'REPORT_TYPE',										//投诉类型
        	  	'COMPLAINT_PROCESS_STATUS',							//投诉处理状态
        	  	'RESOLVE_CASE',										//投诉解决情况
        	  	'SATISFACTION_DEGREE',								//客户满意度
        	  	'DANGER_TYPE',										//出险类型
        	  	'RECOMPENSE_TYPE',									//理赔类型
        	  	'RECOMPENSE_WAY',									//理赔方式
        	  	'RECOMPENSE_STATUS',								//理赔处理状态
        	    'MEMBER_GRADE',					//// 目标级别',目前级别,客户等级',会员等级
        		'CUSTOMER_TYPE',									//客户类型
        		'TRADE',											//客户行业
	          	'COMP_NATURE',										//公司性质
		        'CUST_POTENTIAL',									//客户潜力类型
          		'PREFERENCE_CHANNEL',								//来源渠道',偏好渠道
	          	'PREFERENCE_SERVICE',								//偏好服务
		        'FIRM_SIZE',										//上一年公司规模
		        'CUSTOMER_NATURE',									//客户属性
		        'CARDTYPECON'										//联系人证件类型
		        ];
	//初始化业务参数
	initDataDictionary(keys);
	Ext.define('jg',{
		extend:'TabContentPanel',
		padding:'10 0 0 0'
	});
	Ext.define('Information',{					//1.综合信息Information
		id:'InformationId',
		extend:'TabContentPanel',
		autoScroll:true,//
		title:i18n('i18n.memberView.information')+'&nbsp;&nbsp;&nbsp;&nbsp;'+'<span class="hd" style="color:red">\u6253\u5370\u65F6\u8BF7\u4F7F\u7528"\u6A2A\u5411\u6253\u5370"</span>'+
			'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+'<span class="hd" style="color:red">\u8FB9\u8DDD\u8BF7\u9009\u62E9"\u6700\u5C0F\u503C"</span>',//'综合信息',
		autoScroll:true,
		itemTopPanel:null,		// 上边panel
		itemCtrPanel:null,		// 中间panel = 中间表格
		itemCtrBtmPanel:null,	// 中下panel = 中下表格
		itemPageNext:null,
		itemMidBtmPanel:null,	// 下部panel = 下部表格
		itemJg:null,
		itemBtmGrid:null,		// 底部panel	 = 底部表格
		items:null,
		buttons:null,
		initComponent:function(){
			this.buttons = this.getButtons();
			this.items = this.getItems();
			setTimeout(function(){
				var json = window.opener.data;
				// console.log(json);
				var Member					= json.memberIntegratedInfoView.memberIntegral,					// 会员基本信息	
					Contact					= json.memberIntegratedInfoView.contactIntegralList,			// 联系人信息列表
					LatelyTrade				= json.memberIntegratedInfoView.latelyTrade,			
	                TwelveMonthList         = json.memberIntegratedInfoView.twelveMonthList,
	                OperationAnalysisList   = json.memberIntegratedInfoView.operationAnalysisList,
					ComplaintStatisticsList	= json.memberIntegratedInfoView.complaintStatisticsList,// 历史投诉统计数据对象
					FF 						= json.memberIntegratedInfoView.complaintStatistics, 			// 历史投诉统计数据对象(new)

					RecStatisticsList		= json.memberIntegratedInfoView.recStatisticsList,		// 历史理赔统计数据对象
					Complaint				= json.memberIntegratedInfoView.complaint, 				// 投诉信息
					Recompense				= json.memberIntegratedInfoView.recompense, 			// 理赔
					ReturnVisit				= json.memberIntegratedInfoView.returnVisit, 			// 回访记录
					Contract				= json.memberIntegratedInfoView.contract, 				// 合同信息
					latelyTrade	= new InformationTopRighTopModel(LatelyTrade);
					var objToArr = function(obj){
						var arr = [];
						arr[0]= obj;
						return arr;
					};
				CustId = Member.member.id;
				CustNumber = Member.member.custNumber;
				//及联系人信息组装起来，设置到表格中
				var member 		= new InformationTopLeftTopFormModel(Member.member);
				member.set(CONFIGNAME.get('Info_memberIntegral'),Member.currentTotalScore);
				member.set(CONFIGNAME.get("Info_IDCardNumber"), Member.member.mainContact.idCard);
				Ext.getCmp('InformationTopLeftTopFormID').getForm().loadRecord(member);				//最内左上form
				//及联系人信息(和积分)组装起来，设置到表格中
				
				
//				var informationTopLeftbtmModelList = new Array();
//				if(!Ext.isEmpty(Contact)){
//					for(var i=0;i<Contact.length;i++){
//						if(Ext.isEmpty(Contact[i])||Ext.isEmpty(Contact[i].contact))continue;
//						var informationTopLeftbtmModel = new InformationTopLeftbtmModel(Contact[i].contact);
//						informationTopLeftbtmModel.set(CONFIGNAME.get('InfoTLB_integral'),Contact[i].currentUsableScore);
//						informationTopLeftbtmModel.commit();
//						informationTopLeftbtmModelList.push(informationTopLeftbtmModel);
//					}
//				}

				
				Ext.getCmp('InformationTopLeftbtmGridID').getStore().loadData(Member.member.contactList);
//				Ext.getCmp('InformationTopLeftbtmGridID').getStore().loadRecords(informationTopLeftbtmModelList);				//最内左上grid（联系人信息列表GRID）
				
				Ext.getCmp('InformationTopRighTopFormID').getForm().loadRecord(latelyTrade);		//最内右上form
				Ext.getCmp('InformationTopRighCtnGridID').getStore().loadData(TwelveMonthList);		//最内右上grid 》客户货量分析
				if(FF) {
					window.FF = FF;
					Ext.getCmp("HistyComplaintStatisticsID").fnDoLayout();
				};
					Ext.getCmp('ARecentComplaintListID').getStore().loadData(objToArr(Complaint));		//最后一次投诉明细
					Ext.getCmp('HistoricalClaimsStatisticsID').getStore().loadData(RecStatisticsList);	//历史理赔统计
					Ext.getCmp('ARecentClaimsListID').getStore().loadData(objToArr(Recompense));		//最近一次理赔明细
			
			},1);
			this.callParent();
		},
		getItems:function(){
			this.itemTopPanel 	 = new InformationTopPanel();
			this.itemCtrPanel 	 = new InformationCtrPanel();
			this.itemPageNext	 = new pageNext();
			this.itemCtrBtmPanel = new InformationCtrBtmPanel();
			return [	this.itemTopPanel,
			        	this.itemCtrPanel,
			        	this.itemPageNext,
			        	this.itemCtrBtmPanel
						];
		},
		getButtons:function(){
			var me = this;
			return [{
				text: i18n('i18n.memberView.printThisPage'),
				scope:me,
				handler: function(){
					window.print();
				}
			}];
		}
	});
	Ext.define('InformationTopPanel',{			//1.1综合信息上
		extend:'Ext.panel.Panel',
		id:'InformationTopPanelID',
//		width:1000,
		height:300,
		itemInformationTopLeftPanel:null,	//左边panel是个vbox
		itemInformationTopRighPanel:null,	//右边panel是个vbox
		items:null,
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();
		},
		layout:{
			type: 'vbox'
		},
		getItems:function(){
			this.itemInformationTopLeftPanel = new InformationTopLeftPanel();
			this.itemInformationTopRighPanel = new InformationTopRighPanel();
			return [ this.itemInformationTopLeftPanel, //
			         this.itemInformationTopRighPanel	]
		}
	});
	Ext.define('InformationTopLeftPanel',{		//1.1.1
		extend:'Ext.panel.Panel',
		width :900,
		itemTop:null,
		itemBtm:null,
		items:null,
		layout:{
			type:'table',
			columns:2
		},
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			this.itemTop = new InformationTopRighCtnGridPanel();
//			var j = new jg();
			this.itemBtm = new InformationTopRighTopForm();
			return [	this.itemTop,
//						j,
			        	this.itemBtm	];
		}
	});
	Ext.define('InformationTopLeftTopForm',{	//最内右上角form第二个
		extend:'NoTitleFormPanel',
		id:'InformationTopLeftTopFormID',
		items:null,
		height:138,
		margin:'5 0 0 5',
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			return [{
				layout : {
					type : "table",
					columns : 2
				},
				defaultType : "textfield",
				defaults : {
					readOnly : true,
					cls : "readonly",
					labelWidth : 70,
					width : 200,
					padding : "3 0 2 0"
				},
				items : [{
					fieldLabel : i18n("i18n.memberView.customerCode"),
					name : CONFIGNAME.get("Info_customerCode"),
					labelWidth : 60,
					width : 176
				}, {
					fieldLabel : i18n("i18n.memberView.customerName"),
					name : CONFIGNAME.get("Info_custName")
				}, {
					fieldLabel : i18n("i18n.memberView.customerType"),
					name : CONFIGNAME.get("Info_custType"),
					labelWidth : 60,
					width : 176
				}, {
					fieldLabel : MemberViewData.prototype.registrationLableShow(/*hbf 登记号 显示处理 */),
					name : CONFIGNAME.get("Info_taxRegistrationNumber")
				}, {
					fieldLabel : i18n("i18n.memberView.customerRating"),
					name : CONFIGNAME.get("Info_customerRating"),
					labelWidth : 60,
					width : 176
				}, {
					fieldLabel : i18n("i18n.memberView.customerAttribute"),
					name : CONFIGNAME.get("Info_customerAttribute")
				}, {
					fieldLabel : i18n("i18n.memberView.customerIndustry"),
					name : CONFIGNAME.get("Info_customerIndustry"),
					labelWidth : 60,
					width : 176
				},{	
					fieldLabel : i18n("i18n.memberView.IDCardNumber"),
					name : CONFIGNAME.get("Info_IDCardNumber")	
				}]
			}]
		}
	});
	Ext.define('InformationTopLeftbtmGrid',{	//最内左上第二个Grid
		id:'InformationTopLeftbtmGridID',
		extend:'PopupGridPanel',
		autoScroll:true,
		width:515,
		height:130,
	    sortableColumns:false,
	    enableColumnHide:false,
	    enableColumnMove:false,
	    store: DpUtil.getStore('InformationTopLeftbtmStoreID','InformationTopLeftbtmModel',null,[]),
	  	columns:[{
			header : i18n("i18n.memberView.contactPerson"),
			dataIndex : CONFIGNAME.get("InfoTLB_contactPerson"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.sex"),
			dataIndex : CONFIGNAME.get("InfoTLB_sex"),
			flex : .5
		}, {
			header : i18n("i18n.memberView.post"),
			dataIndex : CONFIGNAME.get("InfoTLB_post"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.telephoneNumber"),
			dataIndex : CONFIGNAME.get("InfoTLB_telephoneNumber"),
			flex : 1.5
		}, {
			header : i18n("i18n.memberView.mobileNumber"),
			dataIndex : CONFIGNAME.get("InfoTLB_mobileNumber"),
			flex : 1.5
		}, {
			header : i18n("i18n.memberView.whetherTheMainContact"),
			dataIndex : CONFIGNAME.get("InfoTLB_isMainLinkMan"),
			flex : 1.5,
			renderer : function(j) {
				if(Ext.isEmpty(j)) {
					return null
				} else {
					return str = j ? i18n("i18n.memberView.yes") : i18n("i18n.memberView.no")
				}
			}
		}]		//地址
	});

	Ext.define('InformationTopRighPanel',{		//1.1.2
		extend:'Ext.panel.Panel',
		width :900,
//		height:300,
		itemTop:null,	//form
		itemCtn:null,	//grid
		itemJg: null,
		itemBtm:null,	//货量分析图表
		items:null,
		layout:{
			type:'table',
			columns:2
		},
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			this.itemTop = new InformationTopLeftbtmGrid();
			this.itemCtn = new InformationTopLeftTopForm();
			return [	this.itemTop,
			        	this.itemCtn
							];
		}
	});
	Ext.define('InformationTopRighTopForm',{	//1.1.2.1formInformationTopRighTopFormID
		id:'InformationTopRighTopFormID',
		extend:'NoTitleFormPanel',
//		width:498,
		height:140,
		margin:'15 0 0 0',
		items:null,
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [{
				layout:{
					type:'table',
					columns:1
				},
				defaultType:'textfield',
				defaults:{
					readOnly:true,
					cls:'readonly',
					labelWidth:140,
					width:295
				},
				items:[{
					fieldLabel:i18n('i18n.memberView.lastTransactionDate'),	//'最近一次交易日期'
					name:CONFIGNAME.get('InfoTLT_LastTransactionDate')
				},{
					fieldLabel:i18n('i18n.memberView.lastTransactionLength'),//'距离上次交易时长'
					name:CONFIGNAME.get('InfoTLT_durnTheLastTransaction'),
					margin:'5 0 0 0'
				},{
					fieldLabel:i18n('i18n.custview.latelyTradeDateExpress'),//'距离上次交易时长'
					name:'latelyTradeDateExpress',
					margin:'8 0 0 0'
				},{
					fieldLabel:i18n('i18n.custview.durationExpress'),//'距离上次交易时长'
					name:'durationExpress',
					margin:'8 0 0 0'
				}]		
			}];
		}
	});
	
	Ext.create('Ext.data.Store',{
		storeId:'InformationTopRighCtnGridLStoreId',
		fields:['onlyLine'],
		data:[ {'onlyLine':i18n('i18n.memberView.month')},					// 月份
		       {'onlyLine':i18n('i18n.memberView.shippingWeight')},			// 出发金额
		       {'onlyLine':i18n('i18n.memberView.theVolume')},				// 到货金额
		       {'onlyLine':i18n('i18n.memberView.shippingWeightExp')},				//快递发金额
		       {'onlyLine':i18n('i18n.memberView.theVolumeExp')}]				//快递到金额
	});
	
	Ext.define('InformationTopRighCtnGridL',{
		extend:'Ext.grid.Panel',
		cla:'grid_lackleftright',
		autoScroll:true,
	    sortableColumns:false,
	    enableColumnHide:false,
	    enableColumnMove:false,
	    store: Ext.data.StoreManager.lookup('InformationTopRighCtnGridLStoreId'),
	    columns:[
	  	        { header: '表头', dataIndex: 'onlyLine',flex:1}
	  	],
	  	height:110,
	  	width:100,
	  	border:false,
		hideHeaders:true
	});
	Ext.define('InformationTopRighCtnGrid',{	//1.1.1.1客户货量分析表格内右
		id:'InformationTopRighCtnGridID',
		extend:'Ext.grid.Panel',
		cla:'grid_lackright',
		autoScroll:true,
	    sortableColumns:false,
	    enableColumnHide:false,
	    enableColumnMove:false,
		hideHeaders:true,
		border:false,
		width:498,
		height:110,
	    store: DpUtil.getStore('InformationTopRighCtnStoreId','InformationTopRighCtnModel',null,[]),
	    columns:[
	  	        { header: '1月', 	dataIndex: CONFIGNAME.get('month1'), 	flex: 1 },
	  	        { header: '2月', 	dataIndex: CONFIGNAME.get('month2'), 	flex: 1 },
	  	        { header: '3月', 	dataIndex: CONFIGNAME.get('month3'), 	flex: 1 },
	  	        { header: '4月', 	dataIndex: CONFIGNAME.get('month4'), 	flex: 1 },
	  	        { header: '5月', 	dataIndex: CONFIGNAME.get('month5'), 	flex: 1 },
	  	        { header: '6月', 	dataIndex: CONFIGNAME.get('month6'), 	flex: 1 }	  	],
	  	initComponent:function(){
	  		this.callParent();
	  	}
	});
	Ext.define('InformationTopRighCtnGridPanel',{		//1.1.1.1
		extend:'TabContentPanel',
		items:null,
		autoScroll:true,
		title:i18n('i18n.memberView.customerVolumeAnalysis'),	//'客户货量分析'
		width:600,
		style:{	border:'1px #999 solid',borderTop:0,borderBottom:0,padding:0	},
		itemLeftGrid:null,
		itemRighGrid:null,
		initComponent:function(){
			var me = this;
			this.items = this.getItems();
			this.callParent();
		},
	    layout: {
	        type: 'table',
	        columns: 2
	    },
		getItems:function(){
			this.itemLeftGrid = new InformationTopRighCtnGridL();
			this.itemRighGrid = new InformationTopRighCtnGrid();
			return [	this.itemLeftGrid,
			        	this.itemRighGrid 	];
		}
	});
	Ext.define('InformationCtrPanel',{			//1.2综合信息中部panel
		extend:'BasicFieldSet',
		title:i18n('i18n.memberView.complaintsRelated'),	// 投诉相关
		width:897,
		height:165,
		itemInformationCtrTopGrid:null,	// 历史投诉统计
		itemInformationCtrBtmGrid:null,	// 最近一次投诉明细
		items:null,
		layout:{
			type:'table',
			columns:1
		},
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			this.itemInformationCtrTopGrid = new HistyComplaintStatistics();
			this.itemInformationCtrBtmGrid = new ARecentComplaintList();
			return [	this.itemInformationCtrTopGrid,
			        	this.itemInformationCtrBtmGrid	];
		}
	});

	Ext.define('HistyComplaintStatistics',{				//1.2.1历史投诉统计
		id : "HistyComplaintStatisticsID",
		extend : "TabContentPanel",
		autoScroll : true,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		width : 774,
		padding : 0,
		html : '<div style="height:70px;overflow:auto;"><h3 style="text-align:left;text-indent:4px;font-size:12px;line-height:22px;height:22px;font-weight:300;padding:0;margin:0;color:#373C64;">' + i18n("i18n.memberView.complaintHistoryStatistics") + '</h3><div style="width:754px;overflow:auto; height:50px;"><table class="tsTable"><tr><th class="tsTh">' + i18n("i18n.memberView.complaintSty") + '</th></tr><tr><th class="tsTh">' + i18n("i18n.memberView.complaintHistoryStatistic") + '</th></tr></table></div></div>',
		fnDoLayout : function() {
			var me = this;
			if(FF) {
				if(FF.length >= 0) {
					var sHtmTit = '<div style="margin:0;padding:0;"><h3 style="text-align:left;text-indent:4px;font-size:12px;line-height:22px;height:22px;font-weight:300;padding:0;margin:0;color:#373C64;">' + i18n("i18n.memberView.complaintHistoryStatistics") + '</h3><div style="width:754px;overflow-x:auto;overflow-y:hidden; height:50px;"><table class="tsTable"><tr><th class="tsTh">' + i18n("i18n.memberView.complaintSty") + '</th>', 
						sThead = '', 
						sTr = '</tr><tr><th class="tsTh">' + i18n("i18n.memberView.complaintHistoryStatistic") + '</th>', 
						sTbody = '', sHtmEnd = '</tr></table></div></div>';
					for(var i in FF ) {
						sThead += '<th class="tsTh">' + FF[i].BASCILEVELNAME + '</th>';
						sTbody += '<td class="tsTd">' + FF[i].COUNT + '</td>';
					};
					me.body.dom.innerHTML = sHtmTit + sThead + sTr + sTbody + sHtmEnd;
				};
				me.doLayout();
			};
		}
	});

	Ext.define('ARecentComplaintList',{				//1.2.2最后一次投诉明细
		id:'ARecentComplaintListID',
		extend:'PopupInnerGridPanel',
		autoScroll:true,
	    sortableColumns:false,
	    enableColumnHide:false,
	    enableColumnMove:false,
	    width:870,
	    padding:'0px 1px',
		title:i18n('i18n.memberView.lastComplaintList'),	//'最后一次投诉明细',
	    store: DpUtil.getStore('ARecentComplaintListStoreId','ARecentComplaintListModel',null,[]),
	    columns:[{
			header : i18n("i18n.memberView.treatmentNumber"),
			dataIndex : CONFIGNAME.get("RC_treatmentNumber"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.waybillNum"),
			dataIndex : CONFIGNAME.get("RC_waybillNumber"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.caller"),
			dataIndex : CONFIGNAME.get("RC_complainant"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.linkMethod"),
			dataIndex : CONFIGNAME.get("RC_telephoneNumber"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.complaintTime"),
			dataIndex : CONFIGNAME.get("RC_complaintTime"),
			renderer : function(j) {
				if(Ext.isEmpty(j)) {
					return
				} else {
					return new Date(j).format("yyyy-MM-dd hh:mm:ss")
				}
			},
			flex : 1.3
		}, {
			header : i18n("i18n.memberView.complaintsType"),
			dataIndex : CONFIGNAME.get("RC_complaintsType"),
			flex : 1,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.REPORT_TYPE)
			}
		}, {
			header : i18n("i18n.memberView.processingStatus"),
			dataIndex : CONFIGNAME.get("RC_processingStatus"),
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.COMPLAINT_PROCESS_STATUS)
			},
			flex : 1
		}],
	  	initComponent:function(){
	  		this.callParent();
	  	}
	});
	Ext.define('pageNext',{
		extend	:'TabContentPanel',
		html	:'<div class="PageNext" style="page-break-after: always;"></div>',
		padding:'2 0 0 0'
	});
	Ext.define('InformationCtrBtmPanel',{				//1.3综合信息中下部panel
		extend:'BasicFieldSet',
		title:i18n('i18n.memberView.claimsRelated'),//'理赔相关',
		width:897,
		height:168,
		itemInformationUnderTopGrid:null,	// 历史理赔统计
		itemInformationUnderBtmGrid:null,	// 最近一次理赔明细
		items:null,
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			this.itemInformationUnderTopGrid = new HistoricalClaimsStatistics();
			this.itemInformationUnderBtmGrid = new ARecentClaimsList();
			return [	this.itemInformationUnderTopGrid,
			        	this.itemInformationUnderBtmGrid	];
		}
	});

	Ext.define('HistoricalClaimsStatistics',{		//1.3.1历史理赔统计
		id:'HistoricalClaimsStatisticsID',
		extend:'PopupInnerGridPanel',
		autoScroll:true,
	    sortableColumns:false,
	    enableColumnHide:false,
	       width:870,
	    enableColumnMove:false,
		title:i18n('i18n.memberView.historicalClaimsStatistics'),//'历史理赔统计',
		padding:'0px 1px',
	    store: DpUtil.getStore('HistoricalClaimsStatisticsStoreId','HistoricalClaimsStatisticsModel',null,[]),
		columns:[{
			header :i18n("i18n.memberView.countDanger"),
			flex : 1,
			renderer : function() {
				return i18n("i18n.memberView.countDanger")
			}
		}, {
			header : i18n("i18n.memberView.impersonator"),
			dataIndex : CONFIGNAME.get("HCS_impersonator"),
			flex : 1	
		}, {
			header : i18n("i18n.memberView.pieceslost"),
			dataIndex : CONFIGNAME.get("HCS_pieceslost"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.billlost"),
			dataIndex : CONFIGNAME.get("HCS_billlost"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.goodslack"),
			dataIndex : CONFIGNAME.get("HCS_goodslack"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.damaged"),
			dataIndex : CONFIGNAME.get("HCS_damaged"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.wet"),
			dataIndex : CONFIGNAME.get("HCS_wet"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.pollution"),
			dataIndex : CONFIGNAME.get("HCS_pollution"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.miscellaneous"),
			dataIndex : CONFIGNAME.get("HCS_miscellaneous"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.total"),
			dataIndex : CONFIGNAME.get("HCS_total"),
			flex : 1
		}],
	  	height: 72,
	  	initComponent:function(){
	  		this.callParent();
	  	}
	});
	
	Ext.define('ARecentClaimsList',{				//1.3.2最近一次理赔明细
		id:'ARecentClaimsListID',
		extend:'PopupInnerGridPanel',
		autoScroll:true,
	    sortableColumns:false,
	    enableColumnHide:false,
	    enableColumnMove:false,
		title:i18n('i18n.memberView.aRecentClaims') ,//'最近一次理赔明细',
		padding:'0px 1px',
	  	height: 72,
	  	   width:870,
	    store: DpUtil.getStore('ARecentClaimsListStoreId','ARecentClaimsListModel',null,[]),
	  	columns:[{
			header : i18n("i18n.memberView.waybillNum"),
			dataIndex : CONFIGNAME.get("RCL_waybillNumber"),
			flex : 1,
			renderer : function(j) {
				return j.waybillNumber
			}
		}, {
			header : i18n("i18n.memberView.startingSector"),
			dataIndex : CONFIGNAME.get("RCL_startingSector"),
			flex : 1,
			renderer : function(j) {
				return j.startStation
			}
		}, {
			header : i18n("i18n.memberView.accidentType"),
			dataIndex : CONFIGNAME.get("RCL_accidentType"),
			flex : 1,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.DANGER_TYPE)
			}
		}, {
			header : i18n("i18n.memberView.claimTypes"),
			dataIndex : CONFIGNAME.get("RCL_claimTypes"),
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.RECOMPENSE_TYPE)
			},
			flex : 1
		}, {
			header : i18n("i18n.memberView.claimAmount"),
			dataIndex : CONFIGNAME.get("RCL_claimAmount"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.theState"),
			dataIndex : CONFIGNAME.get("RCL_theState"),
			flex : 1,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.RECOMPENSE_STATUS)
			},
			flex : 1
		}],
	  	initComponent:function(){
	  		this.callParent();
	  	}
	});
	
	Ext.define('PrintPanel', {				// 整合布局容器
		extend : 'Ext.container.Viewport',
		record : null,
		items:null,
		autoScroll:true,
		height:768,
		initComponent : function(){
			this.items = this.getItems();
			this.callParent();
		},
		getItems: function(){
			var ctn = new Information();
			return [ ctn ];
		}
	});
	new PrintPanel();
});