var MemberViewDataObj 	= (CONFIG.get("TEST")) ? new MemberViewDataTest() : new MemberViewData(), 
	CustNumber 			= null, 
	CustId 				= null, 
	CustName 			= null, 
	data 				= null;
Ext.onReady(function() {
	Ext.form.Field.prototype.msgTarget = "side";
	var h = ['ORDER_SOURCE',										//订单来源
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
        		'SECOND_TRADE',											//客户行业
	          	'COMP_NATURE',										//公司性质
		        'CUST_POTENTIAL',									//客户潜力类型
          		'PREFERENCE_CHANNEL',								//来源渠道',偏好渠道
	          	'PREFERENCE_SERVICE',								//偏好服务
		        'FIRM_SIZE',										//上一年公司规模
		        'CUSTOMER_NATURE',									//客户属性
		        'CARDTYPECON'										//联系人证件类型
		        ];
	

	initDataDictionary(h);
	Ext.define("jg", {
		extend : "TabContentPanel",
		padding : "10 0 0 0"
	});
	var i = Ext.create("Ext.data.Store", {
		fields : ["abbr", "name"],
		data : [{
			abbr : "customerCode",
			name : i18n("i18n.memberView.customerCode")
		}, {
			abbr : "contactCoding",
			name : i18n("i18n.memberView.contactCoding")
		}, {
			abbr : "mobileNumber",
			name : i18n("i18n.memberView.mobileNumber")
		}]
	});
	Ext.define("FormAndBtnPanelTest", {			//00顶部搜索表单父容器
		extend : "NotitleBGroundFormPanel",
		height : 45,
		items : null,
		fbar : null,
		defaultType : "textfield",
		initComponent : function() {
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			var j = this;
			return [{
				xtype : "basicformpanel",
				defaultType : "textfield",
				layout : {
					type : "hbox",
					columns : 3,
					align : top
				},
				defaults : {
					labelWidth : 70,
					width : 240
				},
				items : [{
					fieldLabel : i18n("i18n.memberView.searchCondition"),
					xtype : "membersearchcombox",
					queryMode : "local",
					//													value : "customerCode",
					id : "searchConditionCombo",
					setValueComeBack : function(memberRecord, addressRecord) {
						this.setValue(memberRecord.get('custNum'));
						//														this.setRawValue(memberRecord.get('custName'));
					},
					//													store : i,
					//													forceSelection : true,
					displayField : "custName",
					valueField : "custNum"
				},
				{
					xtype : "button",
					id : "custViewSearch",
					text : i18n("i18n.memberView.search"),
					handler : function() {
						//														Ext.getCmp(
						//																		"searchConditionCustNum")
						//																.setDisabled(
						//																		true);
						j.fnSearch();
						//														if(data.40000223){}
					},
					style : {
						marginLeft : "20px"
					},
					width : 80
				}]
			}]
		},
		resetFormAndGrid : function() {
			var j = ["InformationTopLeftTopFormID", "InformationTopLeftbtmGridID", "InformationTopRighTopFormID", "InformationTopRighCtnGridID",
			//											"HistyComplaintStatisticsID",
			"VolumeAnalysisData", "ARecentComplaintListID", "HistoricalClaimsStatisticsID", "ARecentClaimsListID", "InformationMidBtmGridID", "InformationBtmGridID", "itemBasiInfor", "ItemHomeInfo", "contactInforForm", "contactInforTopForm", "contactInforTopGrid", "contactInforGrid", "financialInforGrid", "financialGridFir", "financialGridSec", "contInforGrid", "contInforForm", "planScheduleDetails", "maintenanceRecords", "OrderInforFormId", "orderInforGrid", "integrationFormID", "IntegrationID", "complaintGrid", "claimsGrid", "OperatingDecisionFormId", "operatingDecisionR", "VolumeStructureData"], l = function(m) {
				if(!Ext.isEmpty(Ext.getCmp(m).store)) {
					Ext.getCmp(m).getStore().removeAll()
				} else {
					Ext.getCmp(m).getForm().reset()
				}
			}, k = function(m) {
				for(var n in m) {
					Ext.isEmpty(Ext.getCmp(m[n])) ? "" : l(m[n])
				}
			};
			k(j);
			if(!Ext.isEmpty(g)) {
				g.hide()
			}
			if(!Ext.isEmpty(f)) {
				f.hide()
			}
			if(!Ext.isEmpty(e)) {
				e.hide()
			}
			if(!Ext.isEmpty(d)) {
				d.hide()
			}
			if(!Ext.isEmpty(c)) {
				c.hide()
			}
			if(!Ext.isEmpty(b)) {
				b.hide()
			}
			Ext.data.StoreManager.lookup("VolumeStructureData").loadData([new OperationAnalysisModel().data]);
			Ext.data.StoreManager.lookup("VolumeAnalysisPanelData").loadData([new OperationAnalysisModel().data])
		},
		fnSearch : function() {					//根据会员客户编码，查询出九个模块的360度视图。
			var n = this,
			//									l = Ext.getCmp("searchConditionCustNum").getValue(),
			k = Ext.getCmp("searchConditionCombo").getValue(), o = {
				custNum : k,
				comboVal : 'customerCode'
			}, j = function(D) {
				Ext.getCmp("infoTabPanelView").setActiveTab(0);
				Ext.getCmp("custViewSearch").setDisabled(false);
				//										Ext.getCmp("searchConditionCustNum")
				//												.setDisabled(false);
				if(Ext.isEmpty(D.memberIntegratedInfoView)) {
					MessageUtil.showErrorMes(i18n("i18n.memberView.searchErro2"));
					return
				}
				data = D;
				var E = D.memberIntegratedInfoView.memberIntegral, 					// 会员基本信息	
					t = D.memberIntegratedInfoView.contactIntegralList, 			// 联系人信息列表
					q = D.memberIntegratedInfoView.latelyTrade, 
					u = D.memberIntegratedInfoView.twelveMonthList, 
					p = D.memberIntegratedInfoView.operationAnalysisList, 
					F = D.memberIntegratedInfoView.complaintStatisticsList, 		// 历史投诉统计数据对象
					FF = D.memberIntegratedInfoView.complaintStatistics, 			// 历史投诉统计数据对象(new)
					y = D.memberIntegratedInfoView.recStatisticsList, 				// 历史理赔统计数据对象
					G = D.memberIntegratedInfoView.complaint, 						// 投诉信息
					z = D.memberIntegratedInfoView.recompense, 						// 理赔
					s = D.memberIntegratedInfoView.returnVisit, 					// 回访记录
					A = D.memberIntegratedInfoView.contract, 						// 合同信息
					v = new InformationTopRighTopModel(q);
				var C = function(I) {
					var H = [];
					H[0] = I;
					return H
				};
				CustId = E.member.id;
				CustNumber = E.member.custNumber;
				CustName = E.member.custName;
				var r = new InformationTopLeftTopFormModel(E.member);
				r.set(CONFIGNAME.get("Info_memberIntegral"), E.currentTotalScore);
				r.set(CONFIGNAME.get("Info_IDCardNumber"), E.member.mainContact.idCard);
				Ext.getCmp("InformationTopLeftTopFormID").getForm().loadRecord(r);
				var w = new Array();
				if(!(Ext.isEmpty(t))){
					for(var B = 0; B < t.length; B++) {
						if(Ext.isEmpty(t[B])||Ext.isEmpty(t[B].contact))continue;
							var x = new InformationTopLeftbtmModel(t[B].contact);
							x.set(CONFIGNAME.get("InfoTLB_integral"), t[B].currentUsableScore);
							x.commit();
							w.push(x)
					}
				};
				Ext.getCmp("InformationTopLeftbtmGridID").getStore().loadRecords(w);
				Ext.getCmp("InformationTopRighTopFormID").getForm().loadRecord(v);
				Ext.getCmp("InformationTopRighCtnGridID").getStore().loadData(u);
				//										Ext.getCmp( "HistyComplaintStatisticsID").getStore().loadData(F);
				if(FF) {
					window.FF = FF;
					Ext.getCmp("HistyComplaintStatisticsID").fnDoLayout();
				};
				if(!Ext.isEmpty(p)) {
					g.show()
				}
				Ext.data.StoreManager.lookup("VolumeAnalysisPanelData").loadData(p);
				Ext.getCmp("ARecentComplaintListID").getStore().loadData(C(G));
				Ext.getCmp("HistoricalClaimsStatisticsID").getStore().loadData(y);
				Ext.getCmp("ARecentClaimsListID").getStore().loadData(C(z));
				Ext.getCmp("InformationMidBtmGridID").getStore().loadData(C(s));
				Ext.getCmp("InformationBtmGridID").getStore().loadData(C(A))
			}, m = function(p) {
				Ext.getCmp("custViewSearch").setDisabled(false);
				//										Ext.getCmp("searchConditionCustNum")
				//												.setDisabled(false);
				MessageUtil.showErrorMes(p.message)
			};
			//									if (Ext.isEmpty(l.trim())) {
			//										MessageUtil
			//												.showMessage(i18n("i18n.memberView.pleaseInputCustNum"));
			//										this.resetFormAndGrid();
			////										Ext.getCmp("searchConditionCustNum")
			////												.setDisabled(false);
			//										return
			//									}
			CustId = null;
			CustNumber = null;
			CustName = null;
			this.resetFormAndGrid();
			Ext.getCmp("custViewSearch").setDisabled(true);
			MemberViewDataObj.searchMemberIntegratedInfoViewByCustNum(o, j, m)
		}
	});
	Ext.define("Information", {									//1.综合信息Information
		extend : "TabContentPanel",
		title : i18n("i18n.memberView.information"),
		height : 500,
		padding : "0 0 0 5",
		autoScroll : true,
		itemTopPanel : null,
		itemCtrPanel : null,
		itemCtrBtmPanel : null,
		itemMidBtmPanel : null,
		itemBtmGrid : null,
		items : null,
		buttons : null,
		initComponent : function() {
			this.items = this.getItems();
			this.buttons = this.getButtons();
			this.callParent()
		},
		listeners : {},
		getItems : function() {
			this.itemTopPanel = new InformationTopPanel();
			this.itemCtrPanel = new InformationCtrPanel();
			this.itemCtrBtmPanel = new InformationCtrBtmPanel();
			this.itemMidBtmPanel = new InformationMidBtmPanel();
			this.itemBtmGrid = new InformationBtmPanel();
			return [this.itemTopPanel, this.itemCtrPanel, this.itemCtrBtmPanel, this.itemMidBtmPanel, this.itemBtmGrid]
		},
		getButtons : function() {
			var j = this;
			return [{
				text : i18n("i18n.memberView.printThisPage"),
				margin : "10 0 10 0",
				scope : j,
				handler : function() {
					url = MemberViewDataObj.printMemberView();
					window.open(url, "names", "")
				}
			}, {
				text : "\u9350\u693e\u7d91\u7f01\u64b4\u702f",
				style : {
					visibility : "hidden"
				},
				handler : function() {
				}
			}]
		}
	});
	Ext.define("InformationTopPanel", {							//1.1综合信息上
		extend : "Ext.panel.Panel",
		id : "InformationTopPanelID",
		autoScroll : true,
		width : 776,
		height : 240,
		itemInformationTopLeftPanel : null,
		itemInformationTopRighPanel : null,
		items : null,
		initComponent : function() {
			this.items = this.getItems();
			this.callParent()
		},
		layout : {
			type : "hbox",
			pack : "center",
			align : "stretch"
		},
		getItems : function() {
			this.itemInformationTopLeftPanel = new InformationTopLeftPanel();
			this.itemInformationTopRighPanel = new InformationTopRighPanel();
			return [this.itemInformationTopLeftPanel, this.itemInformationTopRighPanel]
		}
	});
	Ext.define("InformationTopLeftPanel", {						//1.1.1
		extend : "Ext.panel.Panel",
		width : 384,
		padding : "0 4 0 0",
		itemTop : null,
		itemBtm : null,
		items : null,
		initComponent : function() {
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			this.itemTop = new InformationTopLeftTopForm();
			this.itemBtm = new InformationTopLeftbtmGrid();
			return [this.itemTop, this.itemBtm]
		}
	});
	Ext.define("InformationTopLeftTopForm", {					//最内左上角form
		extend : "NoTitleFormPanel",
		id : "InformationTopLeftTopFormID",
		items : null,
		height : 140,
		initComponent : function() {
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
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
					fieldLabel : i18n("i18n.memberView.taxRegistrationNumber"),
					xtype : "",
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
				},{	//TODO:i18n.memberView.IDCardNumber	证件号码
					fieldLabel : i18n("i18n.memberView.IDCardNumber"),
					name : CONFIGNAME.get("Info_IDCardNumber")	//TODO:Info_IDCardNumber
				}]
			}]
		}
	});
	Ext.define("InformationTopLeftbtmGrid", {				//最内左上Grid
		id : "InformationTopLeftbtmGridID",
		extend : "PopupGridPanel",
		autoScroll : true,
		height : 92,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		store : DpUtil.getStore("InformationTopLeftbtmStoreID", "InformationTopLeftbtmModel", null, []),
		columns : [{
			header : i18n("i18n.memberView.contactPerson"),
			dataIndex : CONFIGNAME.get("InfoTLB_contactPerson"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.sex"),
			dataIndex : CONFIGNAME.get("InfoTLB_sex"),
			flex : 1
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
			flex : 2,
			renderer : function(j) {
				if(Ext.isEmpty(j)) {
					return null
				} else {
					return str = j ? i18n("i18n.memberView.yes") : i18n("i18n.memberView.no")
				}
			}
		}]
	});
	var g = Ext.create("widget.panel", {
		width : 390,
		height : 80,
		layout : "fit",
		items : {
			xtype : "chart",
			animate : false,
			store : DpUtil.getStore("VolumeAnalysisPanelData", "OperationAnalysisModel", null, []),
			insetPadding : 15,
			style : {
				position : "relative",
				background : "#eee"
			},
			axes : [{
				type : "Numeric",
				minimum : 0,
				position : "left",
				fields : ["leaveMoney"],
				title : false,
				grid : true,
				label : {
					renderer : Ext.util.Format.numberRenderer("0,0"),
					font : "10px Arial"
				}
			}, {
				type : "Category",
				position : "bottom",
				fields : ["yearMonth"],
				title : false,
				label : {
					font : "11px Arial",
					renderer : function(j) {
						return j
					}
				}
			}],
			series : [{
				type : "line",
				axis : "left",
				xField : "yearMonth",
				yField : "leaveMoney",
				tips : {
					trackMouse : true,
					width : 110,
					height : 50,
					renderer : function(k, j) {
						this.setTitle(k.get("yearMonth") +"<br />"+ i18n("i18n.memberView.startingMonOf") +"<br />"+ k.get("leaveMoney"))
					}
				},
				style : {
					fill : "#00ff00",
					stroke : "#00ff00",
					"stroke-width" : 3
				},
				markerConfig : {
					type : "circle",
					size : 4,
					radius : 4,
					"stroke-width" : 0,
					fill : "#00ff00",
					stroke : "#00ff00"
				}
			}, {
				type : "line",
				axis : "left",
				xField : "yearMonth",
				yField : "arriveMoney",
				tips : {
					trackMouse : true,
					width : 110,
					height : 50,
					renderer : function(k, j) {
						this.setTitle(k.get("yearMonth") +"<br />"+ i18n("i18n.memberView.toReachTheMonOf") +"<br />"+ k.get("arriveMoney"))
					}
				},
				style : {
					fill : "#ff0000",
					stroke : "#ff0000",
					"stroke-width" : 3
				},
				markerConfig : {
					type : "circle",
					size : 4,
					radius : 4,
					"stroke-width" : 0,
					fill : "#ff0000",
					stroke : "#ff0000"
				}
			}]
		}
	});
	Ext.define("InformationTopRighPanel", {					//1.1.2
		extend : "Ext.panel.Panel",
		width : 400,
		height : 300,
		padding : "0 0 0 6",
		itemTop : null,
		itemCtn : null,
		itemJg : null,
		itemBtm : null,
		items : null,
		initComponent : function() {
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			this.itemTop = new InformationTopRighTopForm();
			this.itemCtn = new InformationTopRighCtnGridPanel();
			this.itemJg = new jg();
			return [this.itemTop, this.itemCtn, this.itemJg, g]
		}
	});
	Ext.define("InformationTopRighTopForm", {				//1.1.2.1formInformationTopRighTopFormID
		id : "InformationTopRighTopFormID",
		extend : "NoTitleFormPanel",
		width : 398,
		height : 32,
		items : null,
		initComponent : function() {
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			var j = this;
			return [{
				layout : {
					type : "table",
					columns : 2
				},
				defaultType : "textfield",
				defaults : {
					readOnly : true,
					cls : "readonly"
				},
				items : [{
					fieldLabel : i18n("i18n.memberView.lastTransactionDate"),
					name : CONFIGNAME.get("InfoTLT_LastTransactionDate"),
					labelWidth : 100,
					width : 186
				}, 
				{
					xtype : "container",
					width : 200,
					items : [{
						xtype : "textfield",
						fieldLabel : i18n("i18n.memberView.lastTransactionLength"),		//保价费率
						width : 180,
						labelWidth : 110,
						readOnly : true,
						cls : "readonly",
						style : "float:left;",
						name : CONFIGNAME.get("InfoTLT_durnTheLastTransaction"),
					}, {
						xtype : "label",
						text : i18n("i18n.memberView.days"),		//"折",
						style : "float:left;",
						width : 14,
						padding:'4 0 0 6'
					}]
				}]
			}]
		}
	});
	Ext.create("Ext.data.Store", {
		storeId : "InformationTopRighCtnGridLStoreId",
		fields : ["onlyLine"],
		data : [{
			onlyLine : i18n("i18n.memberView.month")
		}, {
			onlyLine : i18n("i18n.memberView.shippingWeight")
		}, {
			onlyLine : i18n("i18n.memberView.theVolume")
		}]
	});
	Ext.define("InformationTopRighCtnGridL", {
		extend : "Ext.grid.Panel",
		cla : "grid_lackleftright",
		autoScroll : true,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		store : Ext.data.StoreManager.lookup("InformationTopRighCtnGridLStoreId"),
		columns : [{
			header : "\u741b\u3125\u3054",
			dataIndex : "onlyLine",
			flex : 1
		}],
		height : 68,
		width : 64,
		border : false,
		hideHeaders : true
	});
	Ext.define("InformationTopRighCtnGrid", {				//1.1.2.2客户货量分析表格
		id : "InformationTopRighCtnGridID",
		extend : "Ext.grid.Panel",
		cla : "grid_lackright",
		autoScroll : true,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		hideHeaders : true,
		border : false,
		width : 400,
		height : 68,
		store : DpUtil.getStore("InformationTopRighCtnStoreId", "InformationTopRighCtnModel", null, []),
		columns : [{
			header : "1\u93c8\ufffd",
			dataIndex : CONFIGNAME.get("month1"),
			flex : 1
		}, {
			header : "2\u93c8\ufffd",
			dataIndex : CONFIGNAME.get("month2"),
			flex : 1
		}, {
			header : "3\u93c8\ufffd",
			dataIndex : CONFIGNAME.get("month3"),
			flex : 1
		}, {
			header : "4\u93c8\ufffd",
			dataIndex : CONFIGNAME.get("month4"),
			flex : 1
		}, {
			header : "5\u93c8\ufffd",
			dataIndex : CONFIGNAME.get("month5"),
			flex : 1
		}, {
			header : "6\u93c8\ufffd",
			dataIndex : CONFIGNAME.get("month6"),
			flex : 1
		}],
		initComponent : function() {
			this.callParent()
		}
	});
	Ext.define("InformationTopRighCtnGridPanel", {
		extend : "TabContentPanel",
		items : null,
		autoScroll : true,
		title : i18n("i18n.memberView.customerVolumeAnalysis"),
		width : 386,
		height : 110,
		style : {
			border : "1px #999 solid",
			borderTop : 0,
			borderBottom : 0,
			padding : 0
		},
		itemLeftGrid : null,
		itemRighGrid : null,
		initComponent : function() {
			var j = this;
			this.items = this.getItems();
			this.callParent()
		},
		layout : {
			type : "table",
			columns : 2
		},
		getItems : function() {
			this.itemLeftGrid = new InformationTopRighCtnGridL();
			this.itemRighGrid = new InformationTopRighCtnGrid();
			return [this.itemLeftGrid, this.itemRighGrid]
		}
	});
	Ext.define("InformationCtrPanel", {						//1.2综合信息中部panel
		extend : "BasicFieldSet",
		title : i18n("i18n.memberView.complaintsRelated"),
		width : 776,
		height : 184,
		layout : {
			type : "vbox",
			align : "stretch"
		},
		itemInformationCtrTopGrid : null,
		itemInformationCtrBtmGrid : null,
		items : null,
		initComponent : function() {
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			this.itemInformationCtrTopGrid = Ext.create('HistyComplaintStatistics');
			this.itemInformationCtrBtmGrid = new ARecentComplaintList();
			return [this.itemInformationCtrTopGrid, this.itemInformationCtrBtmGrid]
		}
	});
	Ext.define("HistyComplaintStatistics", {				//1.2.1历史投诉统计
		id : "HistyComplaintStatisticsID",
		extend : "TabContentPanel",
		//				title : i18n("i18n.memberView.complaintHistoryStatistics"),
		autoScroll : true,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		width : 774,
		padding : 0,
		html : '<div style="height:86px;overflow:auto;"><h3 style="text-align:left;text-indent:4px;font-size:12px;line-height:22px;height:22px;font-weight:300;padding:0;margin:0;color:#373C64;">' + i18n("i18n.memberView.complaintHistoryStatistics") + '</h3><div style="width:754px;overflow:auto; height:64px;"><table class="tsTable"><tr><th class="tsTh">' + i18n("i18n.memberView.complaintSty") + '</th></tr><tr><th class="tsTh">' + i18n("i18n.memberView.complaintHistoryStatistic") + '</th></tr></table></div></div>',
		fnDoLayout : function() {
			var me = this;
			if(FF) {
				//TODO:i18n
				if(FF.length >= 0) {
					var sHtmTit = '<div style="margin:0;padding:0;"><h3 style="text-align:left;text-indent:4px;font-size:12px;line-height:22px;height:22px;font-weight:300;padding:0;margin:0;color:#373C64;">' + i18n("i18n.memberView.complaintHistoryStatistics") + '</h3><div style="width:754px;overflow-x:auto;overflow-y:hidden; height:64px;"><table class="tsTable"><tr><th class="tsTh">' + i18n("i18n.memberView.complaintSty") + '</th>', sThead = '', sTr = '</tr><tr><th class="tsTh">' + i18n("i18n.memberView.complaintHistoryStatistic") + '</th>', sTbody = '', sHtmEnd = '</tr></table></div></div>';
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

	Ext.define("ARecentComplaintList", {					//1.2.2最后一次投诉明细
		id : "ARecentComplaintListID",
		extend : "PopupInnerGridPanel",
		autoScroll : true,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		title : i18n("i18n.memberView.lastComplaintList"),
		height : 72,
		store : DpUtil.getStore("ARecentComplaintListStoreId", "ARecentComplaintListModel", null, []),
		columns : [{
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
		initComponent : function() {
			this.callParent()
		}
	});
	Ext.define("InformationCtrBtmPanel", {					//1.3综合信息中下部panel
		extend : "BasicFieldSet",
		title : i18n("i18n.memberView.claimsRelated"),
		width : 776,
		height : 170,
		itemInformationUnderTopGrid : null,
		itemInformationUnderBtmGrid : null,
		items : null,
		layout : {
			type : "vbox",
			align : "stretch"
		},
		initComponent : function() {
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			this.itemInformationUnderTopGrid = new HistoricalClaimsStatistics();
			this.itemInformationUnderBtmGrid = new ARecentClaimsList();
			return [this.itemInformationUnderTopGrid, this.itemInformationUnderBtmGrid]
		}
	});
	Ext.define("HistoricalClaimsStatistics", {				//1.3.1历史理赔统计
		id : "HistoricalClaimsStatisticsID",
		extend : "PopupInnerGridPanel",
		autoScroll : true,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		title : i18n("i18n.memberView.historicalClaimsStatistics"),
		store : DpUtil.getStore("HistoricalClaimsStatisticsStoreId", "HistoricalClaimsStatisticsModel", null, []),
		columns : [{
			header : i18n("i18n.memberView.accidentType"),
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
		height : 72,
		initComponent : function() {
			this.callParent()
		}
	});
	Ext.define("ARecentClaimsList", {					//1.3.2最近一次理赔明细
		id : "ARecentClaimsListID",
		extend : "PopupInnerGridPanel",
		autoScroll : true,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		title : i18n("i18n.memberView.aRecentClaims"),
		height : 72,
		store : DpUtil.getStore("ARecentClaimsListStoreId", "ARecentClaimsListModel", null, []),
		columns : [{
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
		initComponent : function() {
			this.callParent()
		}
	});
	Ext.define("InformationMidBtmPanel", {				//1.4综合信息中下部表格
		id : "InformationMidBtmGridID",
		title : i18n("i18n.memberView.maintenance"),
		extend : "PopupGridPanel",
		autoScroll : true,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		width : 776,
		style : {
			height : "72px",
			align : "left"
		},
		store : DpUtil.getStore("InformationMidBtmStoreId", "InformationMidBtmModel", null, []),
		columns : [{
			header : i18n("i18n.memberView.maintenanceTime"),
			dataIndex : CONFIGNAME.get("IMB_visitingTime"),
			flex : 1,
			renderer : function(j) {
				if(Ext.isEmpty(j)) {
					return
				} else {
					return new Date(j).format("yyyy-MM-dd")
				}
			}
		}, {
			header : i18n("i18n.memberView.contactName"),
			dataIndex : CONFIGNAME.get("IMB_investigators"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.mobileNumber"),
			dataIndex : CONFIGNAME.get("IMB_accessObject"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.telephoneNumber"),
			dataIndex : CONFIGNAME.get("IMB_customerRequirType"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.maintenanceOfTheme"),
			dataIndex : CONFIGNAME.get("IMB_customerRequirDescrip"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.customerFeedback"),
			dataIndex : CONFIGNAME.get("IMB_solution"),
			flex : 1,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.CUSTOMER_IDEA)
			}
		}, {
			header : i18n("i18n.memberView.specificMaintenanceMode"),
			dataIndex : CONFIGNAME.get("IMB_customerAdvice"),
			flex : 1,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.MAINTAIN_WAY)
			}
		}],
		initComponent : function() {
			this.callParent()
		}
	});
	Ext.define("InformationBtmPanel", {					//1.5综合信息下部表格
		id : "InformationBtmGridID",
		extend : "PopupGridPanel",
		title : i18n("i18n.memberView.contractInformation"),
		autoScroll : true,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		width : 776,
		style : {
			height : "72px",
			align : "left"
		},
		store : DpUtil.getStore("InformationBtmStoreId", "InformationBtmModel", null, []),
		columns : [{
			header : i18n("i18n.memberView.auditNumber"),
			dataIndex : CONFIGNAME.get("IBG_auditNumber"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.termsOfPayment"),
			dataIndex : CONFIGNAME.get("IBG_termsOfPayment"),
			flex : 1,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.RECKON_WAY)
			}
		}, {
			header : i18n("i18n.memberView.preferentialType"),
			dataIndex : CONFIGNAME.get("IBG_preferentialType"),
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.PRIVILEGE_TYPE)
			},
			flex : 1
		}, {
			header : i18n("i18n.memberView.effectiveDate"),
			dataIndex : CONFIGNAME.get("IBG_effectiveDate"),
			renderer : function(j) {
				if(Ext.isEmpty(j)) {
					return
				} else {
					return new Date(j).format("yyyy-MM-dd")
				}
			},
			flex : 1
		}, {
			header : i18n("i18n.memberView.expirationDate"),
			dataIndex : CONFIGNAME.get("IBG_expirationDate"),
			renderer : function(j) {
				if(Ext.isEmpty(j)) {
					return
				} else {
					return new Date(j).format("yyyy-MM-dd")
				}
			},
			flex : 1
		}, {
			header : i18n("i18n.memberView.monthlyAmount"),
			dataIndex : CONFIGNAME.get("IBG_monthlyAmount"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.protocol"),
			dataIndex : CONFIGNAME.get("IBG_protocol"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.contactMobilePhone"),
			dataIndex : CONFIGNAME.get("IBG_contactMobilePhone"),
			flex : 1
		}],
		initComponent : function() {
			this.callParent()
		}
	});
	Ext.define("BasicInfor", {					//2.基本信息BasicInfor
		extend : "TabContentPanel",
		items : null,
		title : i18n("i18n.memberView.basicInfor"),
		height : 500,
		autoScroll : true,
		itemBasiInfo : null,
		itemHomeInfo : null,
		items : null,
		memberData : null,
		listeners : {
			activate : function(m, n) {
				if(Ext.getCmp("financialInforGrid").getStore().getCount() != 0) {
					return
				}
				var p = CustNumber;
				if(Ext.isEmpty(p)) {
					MessageUtil.showMessage(i18n("i18n.memberView.pleaseInputCustNum"));
					return
				}
				var l = CustId;
				var o = {
					custId : l
				};
				var j = function(r) {
					if(!Ext.isEmpty(r.memberView)) {
						var q = new ItemBasiInforModel(r.memberView.member);
						q.set(CONFIGNAME.get("BI_areaName"), r.memberView.areaName);
						q.set(CONFIGNAME.get("BI_createPeople"), r.memberView.createrName);
						q.set(CONFIGNAME.get("BI_createBranch"), r.memberView.createDeptName);
						q.set(CONFIGNAME.get("BI_createTime"), new Date(q.get(CONFIGNAME.get("BI_createTime"))).format("yyyy-MM-dd"));
						q.set(CONFIGNAME.get("BI_Info_customerRating"), DpUtil.changeDictionaryCodeToDescrip(q.get(CONFIGNAME.get("BI_Info_customerRating")), DataDictionary.MEMBER_GRADE));
						q.set(CONFIGNAME.get("BI_customerType"), DpUtil.changeDictionaryCodeToDescrip(q.get(CONFIGNAME.get("BI_customerType")), DataDictionary.CUSTOMER_TYPE));
						q.set(CONFIGNAME.get("BI_Info_customerIndustry"), DpUtil.changeDictionaryCodeToDescrip(q.get(CONFIGNAME.get("BI_Info_customerIndustry")), DataDictionary.TRADE));
						q.set(CONFIGNAME.get("BI_Info_customerSecondIndustry"), DpUtil.changeDictionaryCodeToDescrip(q.get(CONFIGNAME.get("BI_Info_customerIndustry")), DataDictionary.SECOND_TRADE));
						q.set(CONFIGNAME.get("BI_natureOfCompany"), DpUtil.changeDictionaryCodeToDescrip(q.get(CONFIGNAME.get("BI_natureOfCompany")), DataDictionary.COMP_NATURE));
						q.set(CONFIGNAME.get("BI_customerPotentialType"), DpUtil.changeDictionaryCodeToDescrip(q.get(CONFIGNAME.get("BI_customerPotentialType")), DataDictionary.CUST_POTENTIAL));
						q.set(CONFIGNAME.get("BI_sourceChannel"), DpUtil.changeDictionaryCodeToDescrip(q.get(CONFIGNAME.get("BI_sourceChannel")), DataDictionary.PREFERENCE_CHANNEL));
						q.set(CONFIGNAME.get("BI_preferenceChannels"), DpUtil.changeDictionaryCodeToDescrip(q.get(CONFIGNAME.get("BI_preferenceChannels")), DataDictionary.PREFERENCE_CHANNEL));
						q.set(CONFIGNAME.get("BI_Info_businessType"), DpUtil.changeDictionaryCodeToDescrip(q.get(CONFIGNAME.get("BI_Info_businessType")), DataDictionary.CUSTOMER_NATURE));
						q.set(CONFIGNAME.get("BI_preferenceService"), DpUtil.changeDictionaryCodeToDescrip(q.get(CONFIGNAME.get("BI_preferenceService")), DataDictionary.PREFERENCE_SERVICE));
						q.set(CONFIGNAME.get("BI_companyscaleTheLastYear"), DpUtil.changeDictionaryCodeToDescrip(q.get(CONFIGNAME.get("BI_companyscaleTheLastYear")), DataDictionary.FIRM_SIZE));
						q.set(CONFIGNAME.get("BI_whetherSpecialcustomers"), DpUtil.changeTrueAndFalse(q.get(CONFIGNAME.get("BI_whetherSpecialcustomers"))));
						q.set(CONFIGNAME.get("BI_AllowedContactExchangePoints"), DpUtil.changeTrueAndFalse(q.get(CONFIGNAME.get("BI_AllowedContactExchangePoints"))));
						q.set(CONFIGNAME.get("BI_whetherImportantCustomers"), DpUtil.changeTrueAndFalse(q.get(CONFIGNAME.get("BI_whetherImportantCustomers"))));
						q.set(CONFIGNAME.get("BI_whetherAcceptMarketingInfo"), DpUtil.changeTrueAndFalse(q.get(CONFIGNAME.get("BI_whetherAcceptMarketingInfo"))));
						q.set(CONFIGNAME.get("BI_doesCentralizedReceiving"), DpUtil.changeTrueAndFalse(q.get(CONFIGNAME.get("BI_doesCentralizedReceiving"))));
						q.set(CONFIGNAME.get("BI_ifTheParentCompany"), DpUtil.changeTrueAndFalse(q.get(CONFIGNAME.get("BI_ifTheParentCompany"))));
						q.set(CONFIGNAME.get("BI_deptName"), r.memberView.deptName);
						q.set(CONFIGNAME.get("BI_isForce"), DpUtil.verifIsEffective(q.get(CONFIGNAME.get("BI_isForce"))));
						Ext.getCmp("itemBasiInfor").getForm().loadRecord(q);
						Ext.getCmp("itemHomeInfo").getForm().loadRecord(q)
					}
				};
				var k = function(q) {
					MessageUtil.showErrorMes(q.message)
				};
				MemberViewDataObj.searchMemberViewByCustNum(o, j, k)
			}
		},
		initComponent : function() {
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			return [new ItemBasiInfor(), new ItemHomeInfo()]
		}
	});
	Ext.define("ItemBasiInfor", {					//2.1基础信息
		extend : "TitleFormPanel",
		id : "itemBasiInfor",
		items : null,
		width : 776,
		height : 304,
		initComponent : function() {
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			return [{
				xtype : "basicfiledset",
				title : i18n("i18n.memberView.BasiInfor"),
				layout : {
					type : "table",
					columns : 3
				},
				defaultType : "textfield",
				defaults : {
					readOnly : true,
					cls : "readonly",
					labelWidth : 110,
					width : 252
				},
				items : [{
					fieldLabel : i18n("i18n.memberView.customerCode"),		//1.客户编码
					name : CONFIGNAME.get("BI_customerCode")
				}, {
					fieldLabel : i18n("i18n.memberView.blongDept"),			//2.所属部门
					name : CONFIGNAME.get("BI_deptName")
				}, {
					fieldLabel : i18n("i18n.memberView.customerRating"),	//3.客户等级
					name : CONFIGNAME.get("BI_Info_customerRating")
				}, {
					fieldLabel : i18n("i18n.memberView.customerName"),		//4.客户名称
					name : CONFIGNAME.get("BI_Info_customerName")
				}, {
					fieldLabel : i18n("i18n.memberView.customerIndustry"),	//5.客户行业
					name : CONFIGNAME.get("BI_Info_customerIndustry")
				}, {
					fieldLabel : i18n("i18n.memberView.customerType"),		//6.客户类型
					name : CONFIGNAME.get("BI_customerType")
				}, {
					fieldLabel : i18n("i18n.memberView.taxRegistrationNumber"),//7.企业税务登记号
					name : CONFIGNAME.get("BI_taxRegistrationNumber")
				}, {
					fieldLabel : i18n("i18n.memberView.natureOfCompany"),	//8.公司性质
					name : CONFIGNAME.get("BI_natureOfCompany")
				}, {
					fieldLabel : i18n("i18n.memberView.customerAttribute"),	//9.客户属性
					name : CONFIGNAME.get("BI_Info_businessType")
				}, {
					fieldLabel : i18n("i18n.memberView.provincesAndCities"),//10.所在省市  TODO：修改为省市
					name : CONFIGNAME.get("BI_provincesAndCities")
				}, 
				// {
					// fieldLabel : i18n("i18n.memberView.registAddress"),		//11.TODO：公司地址
					// name : CONFIGNAME.get("")
				// }, 
				{
					fieldLabel : i18n("i18n.memberView.whetherSpecialcustomers"),//12.特殊客户
					name : CONFIGNAME.get("BI_whetherSpecialcustomers")
				}, {
					fieldLabel : i18n("i18n.memberView.whetherImportantCustomers"),//13.重要客户
					name : CONFIGNAME.get("BI_whetherImportantCustomers")
				}, {
					fieldLabel : i18n("i18n.memberView.customerPotentialType"),	//14.潜力类型
					name : CONFIGNAME.get("BI_customerPotentialType")
				}, {
					fieldLabel : i18n("i18n.memberView.whetherAcceptMarketingInfo"),//15.
					name : CONFIGNAME.get("BI_whetherAcceptMarketingInfo")
				}, {
					fieldLabel : i18n("i18n.memberView.brandValue"),		//16.
					name : CONFIGNAME.get("BI_brandValue")
				}, {
					fieldLabel : i18n("i18n.memberView.sourceChannel"),		//17.
					name : CONFIGNAME.get("BI_sourceChannel")
				}, {
					fieldLabel : i18n("i18n.memberView.preferenceChannels"),//18.
					name : CONFIGNAME.get("BI_preferenceChannels")
				}, {
					fieldLabel : i18n("i18n.memberView.preferenceService"),	//19.
					name : CONFIGNAME.get("BI_preferenceService")
				}, {
					fieldLabel : i18n("i18n.memberView.companyscaleTheLastYear"),//20
					name : CONFIGNAME.get("BI_companyscaleTheLastYear")
				}, {
					fieldLabel : i18n("i18n.memberView.profitsOfCompanyTheLastYear"),//21.
					name : CONFIGNAME.get("BI_profitsOfCompanyTheLastYear")
				}, {
					fieldLabel : i18n("i18n.memberView.aYearIncome"),		//22.
					name : CONFIGNAME.get("BI_aYearIncome")
				}, {
					fieldLabel : i18n("i18n.memberView.invoiceTo"),			//23.
					name : CONFIGNAME.get("BI_invoiceTo")
				}, {
					fieldLabel : i18n("i18n.memberView.registeredCapital"),	//24.
					name : CONFIGNAME.get("BI_registeredCapital")
				}, {
					fieldLabel : i18n("i18n.memberView.lineOfCredit"),		//25.
					name : CONFIGNAME.get("BI_lineOfCredit")
					,colspan : 2
				}, {
					fieldLabel : i18n("i18n.memberView.remarks"),			//26.
					name : CONFIGNAME.get("BI_remarks"),
					xtype : "textfield",
					width : 756,
					height : 45,
					style : {
						position : "absolute"
					}
				}]
			}]
		}
	});
	Ext.define("ItemHomeInfo", {					//2.2归属信息
		extend : "TitleFormPanel",
		id : "itemHomeInfo",
		items : null,
		width : 776,
		height : 90,
		initComponent : function() {
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			return [{
				xtype : "basicfiledset",
				title : i18n("i18n.memberView.homeInformation"),
				layout : {
					type : "table",
					columns : 3
				},
				defaultType : "textfield",
				defaults : {
					readOnly : true,
					cls : "readonly",
					labelWidth : 110,
					width : 252
				},
				items : [{
					fieldLabel : i18n("i18n.memberView.isForce"),
					name : CONFIGNAME.get("BI_isForce")
				}, {
					fieldLabel : i18n("i18n.memberView.createPeople"),
					name : CONFIGNAME.get("BI_createPeople")
				}, {
					fieldLabel : i18n("i18n.memberView.createBranch"),
					name : CONFIGNAME.get("BI_createBranch")
				}, {
					fieldLabel : i18n("i18n.memberView.createTime"),
					name : CONFIGNAME.get("BI_createTime")
				}]
			}]
		}
	});
	Ext.define("MarginV", {
		extend : "Ext.panel.Panel",
		bodyPadding : 5,
	});
	Ext.define("ContactInfor", {					//3.联系人信息ContactInfor
		extend : "TabContentPanel",
		title : i18n("i18n.memberView.contactInformation"),
		height : 500,
		width : 780,
		autoScroll : true,
		itemContactInforTopGrid : null,
		itemContactInforTopForm : null,
		itemContactInforGrid : null,
		itemContactInforForm : null,
		itemMarginV1 : null,
		itemMarginV2 : null,
		itemMarginV3 : null,
		items : null,
		listeners : {
			activate : function(m, n) {
				if(Ext.getCmp("contactInforTopGrid").getStore().getCount() != 0) {
					return
				}
				var p = CustNumber;
				if(Ext.isEmpty(p)) {
					MessageUtil.showMessage(i18n("i18n.memberView.pleaseInputCustNum"));
					return
				}
				var l = CustId;
				var o = {
					custId : l
				};
				var j = function(q) {
					Ext.getCmp("contactInforTopGrid").getStore().removeAll();
					if(!Ext.isEmpty(q.contactList)) {
						Ext.getCmp("contactInforTopGrid").getStore().loadData(q.contactList)
					}
				};
				var k = function(q) {
					MessageUtil.showErrorMes(q.message)
				};
				MemberViewDataObj.searchContactViewByCustNum(o, j, k)
			}
		},
		initComponent : function() {
			var j = this;
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			this.itemMarginV1 = new MarginV();
			this.itemMarginV2 = new MarginV();
			this.itemMarginV3 = new MarginV();
			this.itemContactInforTopGrid = new ContactInforTopGrid();
			this.itemContactInforTopForm = new ContactInforTopForm();
			this.itemContactInforGrid = new ContactInforGrid();
			this.itemContactInforForm = new ContactInforForm();
			return [this.itemContactInforTopGrid, this.itemMarginV1, this.itemContactInforTopForm, this.itemMarginV2, this.itemContactInforGrid, this.itemMarginV3, this.itemContactInforForm]
		}
	});
	Ext.define("ContactInforTopGrid", {					//3.1
		extend : "PopupGridPanel",
		autoScroll : true,
		width : 776,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		id : "contactInforTopGrid",
		selModel : Ext.create("Ext.selection.CheckboxModel", {
			mode : "SINGLE"
		}),
		listeners : {
			select : function(n, k, m, p) {
				var o = k.get("id");
				var q = {
					contactId : o
				};
				var j = function(u) {
					if(Ext.isEmpty(u.contactView)) {
						return
					}
					var s = new ContactModel(u.contactView.contact);
					s.set(CONFIGNAME.get("CITG_whetherTheMainContact"), DpUtil.changeTrueAndFalse(k.get(CONFIGNAME.get("CITG_whetherTheMainContact"))));
					s.set(CONFIGNAME.get("CITG_sex"), DpUtil.changeDictionaryCodeToDescrip(k.get(CONFIGNAME.get("CITG_sex")), DataDictionary.GENDER));
					s.set(CONFIGNAME.get("CITF_logisticsDecision"), DpUtil.changeDictionaryCodeToDescrip(k.get(CONFIGNAME.get("CITF_logisticsDecision")), DataDictionary.LOGIST_DECI));
					s.set(CONFIGNAME.get("CITF_birthDate"), new Date(k.get(CONFIGNAME.get("CITF_birthDate"))).format("yyyy-MM-dd"));
					s.set(CONFIGNAME.get("CIF_createTime"), new Date(k.get(CONFIGNAME.get("CIF_createTime"))).format("yyyy-MM-dd"));
					s.set(CONFIGNAME.get("CIF_lastUpdateDepartment"), new Date(k.get(CONFIGNAME.get("CIF_lastUpdateDepartment"))).format("yyyy-MM-dd"));
					s.set(CONFIGNAME.get("CIF_createPeople"), u.contactView.createrName);
					s.set(CONFIGNAME.get("CIF_createBranch"), u.contactView.createDeptName);
					s.set(CONFIGNAME.get("CIF_lastUpdateMan"), u.contactView.updaterName);
					s.set(CONFIGNAME.get("CIF_lastUpdateDepartment"), u.contactView.updateDeptName);
					var r = s.get(CONFIGNAME.get("CITG_contactType")).split(",");
					var w = new Array();
					for(var t = 0; t < r.length; t++) {
						var v = r[t];
						if(v == "1" || v == "true") {
							w.push(t)
						}
					}
					s.set(CONFIGNAME.get("CITG_contactType"), w);
					Ext.getCmp("contactInforTopForm").getForm().loadRecord(s);
					Ext.getCmp("contactInforForm").getForm().loadRecord(s);
					Ext.getCmp("contactInforGrid").getStore().loadData(s.get(CONFIGNAME.get("CITF_preferenceAddressList")))
				};
				var l = function(r) {
					MessageUtil.showErrorMes(r.message)
				};
				MemberViewDataObj.searchContactByContactId(q, j, l)
			}
		},
		store : DpUtil.getStore("ContactInforTopStore", "ContactModel", null, []),
		columns : [new Ext.grid.RowNumberer(), {
			header : i18n("i18n.memberView.contactCoding"),
			dataIndex : CONFIGNAME.get("CITG_contactCoding"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.contactName"),
			dataIndex : CONFIGNAME.get("CITG_contactName"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.contactType"),
			dataIndex : CONFIGNAME.get("CITG_contactType"),
			flex : 1,
			renderer : function(m) {
				var l = [{
					code : "0",
					codeDesc : i18n("i18n.memberView.financialContactName")
				}, {
					code : "1",
					codeDesc : i18n("i18n.memberView.businessContact")
				}, {
					code : "2",
					codeDesc : i18n("i18n.memberView.deliveryContact")
				}, {
					code : "3",
					codeDesc : i18n("i18n.memberView.receivingContact")
				}, {
					code : "4",
					codeDesc : i18n("i18n.memberView.protocol")
				}];
				var n = "";
				var j = m.split(",");
				if(j != null) {
					for(var k = 0; k < j.length; k++) {
						if(j[k] == "true" || j[k] == "1") {
							if(k != 0 && !Ext.isEmpty(n)) {
								n += ","
							}
							n += DpUtil.changeDictionaryCodeToDescrip(k, l)
						}
					}
				}
				return n
			}
		}, {
			header : i18n("i18n.memberView.sex"),
			dataIndex : CONFIGNAME.get("CITG_sex"),
			flex : 0.5,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.GENDER)
			}
		}, {
			header : i18n("i18n.memberView.post"),
			dataIndex : CONFIGNAME.get("CITG_post"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.mobileNumber"),
			dataIndex : CONFIGNAME.get("CITG_mobileNumber"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.telephoneNumber"),
			dataIndex : CONFIGNAME.get("CITG_telephoneNumber"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.whetherTheMainContact"),
			dataIndex : CONFIGNAME.get("CITG_whetherTheMainContact"),
			flex : 1,
			renderer : function(j) {
				return DpUtil.changeTrueAndFalse(j)
			}
		}],
		height : 218
	});
	Ext.define("ContactInforTopForm", {				//3.2
		extend : "SearchFormPanel",
		id : "contactInforTopForm",
		items : null,
		width : 776,
		height : 230,
		initComponent : function() {
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			var j = this;
			return [{
				layout : {
					type : "table",
					columns : 4
				},
				defaultType : "textfield",
				defaults : {
					readOnly : true,
					labelWidth : 84,
					width : 186
				},
				items : [{
					fieldLabel : i18n("i18n.memberView.contactCoding"),
					name : CONFIGNAME.get("CITG_contactCoding")
				}, {
					fieldLabel : i18n("i18n.memberView.contactName"),
					name : CONFIGNAME.get("CITG_contactName")
				}, {
					fieldLabel : i18n("i18n.memberView.whetherTheMainContact"),
					name : CONFIGNAME.get("CITG_whetherTheMainContact")
				}, {
					fieldLabel : i18n("i18n.memberView.sex"),
					name : CONFIGNAME.get("CITG_sex")
				}, {
					xtype : "checkboxgroup",
					fieldLabel : i18n("i18n.memberView.contactType"),
					vertical : true,
					width : 600,
					colspan : 4,
					name : CONFIGNAME.get("CITG_contactType"),
					defaults : {
						readOnly : true
					},
					items : [{
						boxLabel : i18n("i18n.memberView.financialContactName"),
						inputValue : 0
					}, {
						boxLabel : i18n("i18n.memberView.businessContact"),
						inputValue : 1
					}, {
						boxLabel : i18n("i18n.memberView.deliveryContact"),
						inputValue : 2
					}, {
						boxLabel : i18n("i18n.memberView.receivingContact"),
						inputValue : 3
					}, {
						boxLabel : i18n("i18n.memberView.protocol"),
						inputValue : 4
					}]
				}, {
					fieldLabel : i18n("i18n.memberView.telephoneNumber"),
					name : CONFIGNAME.get("CITG_telephoneNumber")
				}, {
					fieldLabel : i18n("i18n.memberView.mobileNumber"),
					name : CONFIGNAME.get("CITG_mobileNumber")
				}, {
					fieldLabel : i18n("i18n.memberView.post"),
					name : CONFIGNAME.get("CITG_post")
				}, {
					fieldLabel : i18n("i18n.memberView.theOffice"),
					name : CONFIGNAME.get("CITF_theOffice")
				}, {
					fieldLabel : i18n("i18n.memberView.birthDate"),
					name : CONFIGNAME.get("CITF_birthDate")
				}, {
					fieldLabel : i18n("i18n.memberView.learnCompanyWay"),
					name : CONFIGNAME.get("CITF_learnCompanyWay")
				}, {
					fieldLabel : i18n("i18n.memberView.logisticsDecision"),
					name : CONFIGNAME.get("CITF_logisticsDecision")
				}, {
					fieldLabel : i18n("i18n.memberView.placeOfOrigin"),
					name : CONFIGNAME.get("CITF_placeOfOrigin")
				}, {
					fieldLabel : i18n("i18n.memberView.personalHobby"),
					name : CONFIGNAME.get("CITF_personalHobby")
				}, {
					fieldLabel : i18n("i18n.memberView.Nation"),
					name : CONFIGNAME.get("CITF_Nation")
				}, {
					fieldLabel : "QQ",
					name : CONFIGNAME.get("CITF_qqNumber")
				}, {
					fieldLabel : "MSN",
					name : CONFIGNAME.get("CITF_msn")
				}, {
					fieldLabel : i18n("i18n.memberView.wangwang"),
					name : CONFIGNAME.get("CITF_wangwang")
				}, {
					fieldLabel : i18n("i18n.memberView.tennisCampID"),
					name : CONFIGNAME.get("CITF_aliID")
				}, {
					fieldLabel : i18n("i18n.memberView.aliID"),
					name : CONFIGNAME.get("CITF_tennisCampID")
				}, {
					fieldLabel : i18n("i18n.memberView.taobaoID"),
					name : CONFIGNAME.get("CITF_taobaoID")
				}, {
					fieldLabel : i18n("i18n.memberView.kingdeeFriendsBusinessGroup"),
					name : CONFIGNAME.get("CITF_kingdeeFriendsBusinessGroup")
				}, {
					fieldLabel : "EMAIL",
					name : CONFIGNAME.get("CITF_email"),
					width : 240,
					colspan : 3
				}, {
					fieldLabel : i18n("i18n.memberView.cardTypeCon"), //联系人类型
					name : CONFIGNAME.get("CITG_ContactModel")
				}, {
					fieldLabel : i18n("i18n.memberView.IDCardNumber"),
					name : CONFIGNAME.get("CITF_IDCardNumber"),
					width : 240,
					colspan : 3
				}]
			}]
		}
	});
	Ext.define("ContactInforGrid", {				//3.3			//偏好地址GRID
		extend : "PopupGridPanel",
		autoScroll : true,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		id : "contactInforGrid",
		viewConfig : {
			stripeRows : true
		},
		height : 114,
		width : 776,
		columnLines : true,
		store : DpUtil.getStore("PreferenceAddress", "PreferenceAddressModel", null, []),
		columns : [new Ext.grid.RowNumberer(), {
			text : i18n("i18n.memberView.addressType"),
			flex : 1,
			sortable : false,
			dataIndex : CONFIGNAME.get("CIG_addressType"),
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.ADDRESS_TYPE)
			}
		}, {
			text : i18n("i18n.memberView.address"),
			flex : 1,
			sortable : false,
			dataIndex : CONFIGNAME.get("CIG_address")
		}, {
			text : i18n("i18n.memberView.timePreference"),
			columns : [{
				text : i18n("i18n.memberView.startingTime"),
				width : 75,
				sortable : true,
				dataIndex : CONFIGNAME.get("CIG_startingTime"),
				renderer : function(j) {
					return Ext.Date.format(new Date(j), i18n("i18n.memberView.HPoint"))
				}
			}, {
				text : i18n("i18n.memberView.endTime"),
				width : 75,
				sortable : true,
				dataIndex : CONFIGNAME.get("CIG_endTime"),
				renderer : function(j) {
					return Ext.Date.format(new Date(j), i18n("i18n.memberView.HPoint"))
				}
			}]
		}, {
			text : i18n("i18n.memberView.invoice"),
			flex : 1,
			sortable : true,
			dataIndex : CONFIGNAME.get("CIG_invoice"),
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.BILL_REQUIRE)
			}
		}, {
			text : i18n("i18n.memberView.toFare"),
			flex : 1,
			sortable : true,
			dataIndex : CONFIGNAME.get("CIG_toFare"),
			renderer : function(j) {
				return DpUtil.changeTrueAndFalse(j)
			}
		}, {
			text : i18n("i18n.memberView.termsOfPayment"),
			flex : 1,
			sortable : true,
			dataIndex : CONFIGNAME.get("CIG_termsOfPayment"),
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.PAY_WAY)
			}
		}, {
			text : i18n("i18n.memberView.DoorToDoor"),
			flex : 1,
			sortable : true,
			dataIndex : CONFIGNAME.get("CIG_DoorToDoor"),
			renderer : function(j) {
				if(j == 0) {
					return i18n("i18n.memberView.no")
				} else {
					if(j == 1) {
						return i18n("i18n.memberView.yes")
					}
				}
			}
		}, {
			text : i18n("i18n.memberView.otherRequirements"),
			flex : 1,
			sortable : true,
			dataIndex : CONFIGNAME.get("CIG_otherRequirements")
		}]
	});
	Ext.define("ContactInforForm", {				//3.4
		extend : "SearchFormPanel",
		id : "contactInforForm",
		height : 40,
		width : 776,
		items : null,
		initComponent : function() {
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			var j = this;
			return [{
				layout : {
					type : "table",
					columns : 3
				},
				defaultType : "textfield",
				defaults : {
					readOnly : true,
					cls : "readonly",
					labelWidth : 80,
					width : 210
				},
				items : [{
					fieldLabel : i18n("i18n.memberView.createPeople"),
					name : CONFIGNAME.get("CIF_createPeople")
				}, {
					fieldLabel : i18n("i18n.memberView.createBranch"),
					name : CONFIGNAME.get("CIF_createBranch")
					,defaults : {
						labelWidth : 80,
						width :240
					},
				}, {
					fieldLabel : i18n("i18n.memberView.createTime"),
					name : CONFIGNAME.get("CIF_createTime")
				}]
			}]
		}
	});
	Ext.define("FinancialInfor", {					//4.财务信息FinancialInfor
		extend : "TabContentPanel",
		title : i18n("i18n.memberView.financialInformation"),
		height : 500,
		autoScroll : true,
		items : null,
		itemFinancialInforGrid : null,
		itemJg1 : null,
		itemFinancialGridFir : null,
		itemJg2 : null,
		itemFinancialGridSec : null,
		listeners : {
			activate : function(l, m) {
				if(Ext.getCmp("financialInforGrid").getStore().getCount() != 0) {
					return
				}
				var o = CustNumber;
				if(Ext.isEmpty(o)) {
					MessageUtil.showMessage(i18n("i18n.memberView.pleaseInputCustNum"));
					return
				}
				var n = {
					custId : CustId
				};
				var j = function(p) {
					Ext.getCmp("financialInforGrid").getStore().removeAll();
					if(!Ext.isEmpty(p.accountList)) {
						Ext.getCmp("financialInforGrid").getStore().loadData(p.accountList)
					}
				};
				var k = function(p) {
					MessageUtil.showErrorMes(p.message)
				};
				MemberViewDataObj.searchAccountViewByCustNum(n, j, k)
			}
		},
		initComponent : function() {
			var j = this;
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			this.itemFinancialInforGrid = new FinancialInforGrid();
			this.itemJg1 = new jg();
			this.itemFinancialGridFir = new FinancialGridFir();
			this.itemJg2 = new jg();
			this.itemFinancialGridSec = new FinancialGridSec();
			return [this.itemFinancialInforGrid, this.itemJg1, this.itemFinancialGridFir, this.itemJg2, this.itemFinancialGridSec]
		}
	});
	Ext.define("FinancialInforGrid", {					//4.1财务信息grid
		id : "financialInforGrid",
		selModel : Ext.create("Ext.selection.CheckboxModel", {
			mode : "SINGLE"
		}),
		listeners : {
			select : function(n, k, m, o) {
				var j = function(q) {
					var r = new AccountModel(q.accountView.account);
					r.set(CONFIGNAME.get("account_createUser"), q.accountView.createrName);
					r.set(CONFIGNAME.get("account_createDeptId"), q.accountView.createDeptName);
					r.set(CONFIGNAME.get("account_modifyUser"), q.accountView.updaterName);
					r.set(CONFIGNAME.get("account_lastUpdateDeptId"), q.accountView.updateDeptName);
					r.set(CONFIGNAME.get("account_isdefaultaccount"), DpUtil.changeTrueAndFalse(r.get(CONFIGNAME.get("account_isdefaultaccount"))));
					r.set(CONFIGNAME.get("account_accountNature"), DpUtil.changeDictionaryCodeToDescrip(r.get(CONFIGNAME.get("account_accountNature")), DataDictionary.ACCOUNT_NATURE));
					r.set(CONFIGNAME.get("account_accountUse"), DpUtil.changeDictionaryCodeToDescrip(r.get(CONFIGNAME.get("account_accountUse")), DataDictionary.ACCOUNT_USE));
					r.set(CONFIGNAME.get("account_createDate"), new Date(r.get(CONFIGNAME.get("account_createDate"))).format("yyyy-MM-dd"));
					r.set(CONFIGNAME.get("account_modifyDate"), new Date(r.get(CONFIGNAME.get("account_modifyDate"))).format("yyyy-MM-dd"));
					Ext.getCmp("financialGridFir").getForm().loadRecord(r);
					Ext.getCmp("financialGridSec").getForm().loadRecord(r)
				};
				var l = function(q) {
					MessageUtil.showErrorMes(q.message)
				};
				var p = {
					accountId : k.get("id")
				};
				MemberViewDataObj.searchAccountViewById(p, j, l)
			}
		},
		extend : "PopupGridPanel",
		autoScroll : true,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		height : 190,
		store : DpUtil.getStore("FinancialInforStoreId", "AccountModel", null, []),
		columns : [new Ext.grid.RowNumberer(), {
			header : i18n("i18n.memberView.bankAccount"),
			dataIndex : CONFIGNAME.get("account_bank"),
			width : 200
		}, {
			header : i18n("i18n.memberView.accountName"),
			dataIndex : CONFIGNAME.get("account_countName"),
			width : 90
		}, {
			header : i18n("i18n.memberView.bankAccountnumber"),
			dataIndex : CONFIGNAME.get("account_bankAccount"),
			width : 120
		}, {
			header : i18n("i18n.memberView.accountApplication"),
			dataIndex : CONFIGNAME.get("account_accountUse"),
			width : 120,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.ACCOUNT_USE)
			}
		}, {
			header : i18n("i18n.memberView.financialContactName"),
			dataIndex : CONFIGNAME.get("account_financeLinkman"),
			width : 100
		}, {
			header : i18n("i18n.memberView.mobileNumber"),
			dataIndex : CONFIGNAME.get("account_linkManMobile"),
			width : 100
		}, {
			header : i18n("i18n.memberView.telephoneNumber"),
			dataIndex : CONFIGNAME.get("account_linkManPhone"),
			width : 100
		}, {
			header : i18n("i18n.memberView.ifADefaultAccount"),
			dataIndex : CONFIGNAME.get("account_isdefaultaccount"),
			width : 90,
			renderer : function(j) {
				return DpUtil.changeTrueAndFalse(j)
			}
		}, {
			header : i18n("i18n.memberView.accountAndCustomerRelationship"),
			dataIndex : CONFIGNAME.get("account_relation"),
			width : 120
		}],
		width : 786
	});
	Ext.define("FinancialGridFir", {				//4.2财务信息form第一个
		extend : "SearchFormPanel",
		id : "financialGridFir",
		items : null,
		initComponent : function() {
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			var j = this;
			return [{
				layout : {
					type : "table",
					columns : 3
				},
				defaultType : "textfield",
				defaults : {
					labelWidth : 100,
					readOnly : true,
					cls : "readonly",
					width : 210
				},
				items : [{
					fieldLabel : i18n("i18n.memberView.bankAccount"),
					name : CONFIGNAME.get("account_bank")
				}, {
					fieldLabel : i18n("i18n.memberView.branchName"),
					name : CONFIGNAME.get("account_subBankname")
				}, {
					fieldLabel : i18n("i18n.memberView.ifADefaultAccount"),
					name : CONFIGNAME.get("account_isdefaultaccount")
				}, {
					fieldLabel : i18n("i18n.memberView.bankAccountnumber"),
					name : CONFIGNAME.get("account_bankAccount")
				}, {
					fieldLabel : i18n("i18n.memberView.developmentName"),
					name : CONFIGNAME.get("account_countName")
				}, {
					fieldLabel : i18n("i18n.memberView.accountAndCustomerRelationship"),
					name : CONFIGNAME.get("account_relation")
				}, {
					fieldLabel : i18n("i18n.memberView.openingProvince"),
					name : CONFIGNAME.get("account_bankProvinceId")
				}, {
					fieldLabel : i18n("i18n.memberView.natureOfAccount"),
					name : CONFIGNAME.get("account_accountNature")
				}, {
					fieldLabel : i18n("i18n.memberView.accountApplication"),
					name : CONFIGNAME.get("account_accountUse")
				}, {
					fieldLabel : i18n("i18n.memberView.openCity"),
					name : CONFIGNAME.get("account_bankCityId")
				}, {
					fieldLabel : i18n("i18n.memberView.financialContactName"),
					name : CONFIGNAME.get("account_financeLinkman")
				}, {
					fieldLabel : i18n("i18n.memberView.mobileNumber"),
					name : CONFIGNAME.get("account_linkManMobile")
				}, {
					fieldLabel : i18n("i18n.memberView.telephoneNumber"),
					name : CONFIGNAME.get("account_linkManPhone")
				}]
			}]
		}
	});
	Ext.define("FinancialGridSec", {				//4.3财务信息第二个form
		extend : "SearchFormPanel",
		id : "financialGridSec",
		items : null,
		initComponent : function() {
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			var j = this;
			return [{
				layout : {
					type : "table",
					columns : 3
				},
				defaultType : "textfield",
				defaults : {
					readOnly : true,
					cls : "readonly",
					labelWidth : 100,
					width : 210
				},
				items : [{
					fieldLabel : i18n("i18n.memberView.createPeople"),
					name : CONFIGNAME.get("account_createUser")
				}, {
					fieldLabel : i18n("i18n.memberView.createBranch"),
					name : CONFIGNAME.get("account_createDeptId")
				}, {
					fieldLabel : i18n("i18n.memberView.createTime"),
					name : CONFIGNAME.get("account_createDate")
				}]
			}]
		}
	});
	Ext.define("ContInfor", {					//5.合同信息ContInfor
		extend : "TabContentPanel",
		title : i18n("i18n.memberView.contractInformation"),
		height : 500,
		autoScroll : true,
		itemContInforGrid : null,
		itemJg : null,
		itemContInforForm : null,
		items : null,
		listeners : {
			activate : function(m, n) {
				if(Ext.getCmp("contInforGrid").getStore().getCount() != 0) {
					return
				}
				var p = CustNumber;
				if(Ext.isEmpty(p)) {
					MessageUtil.showMessage(i18n("i18n.memberView.pleaseInputCustNum"));
					return
				}
				var l = CustId;
				var o = {
					custId : l
				};
				var j = function(q) {
					Ext.getCmp("contInforGrid").getStore().removeAll();
					if(!Ext.isEmpty(q.contractList)) {
						Ext.getCmp("contInforGrid").getStore().loadData(q.contractList)
					}
				};
				var k = function(q) {
					MessageUtil.showErrorMes(q.message)
				};
				MemberViewDataObj.searchContractViewByCustNum(o, j, k)
			}
		},
		initComponent : function() {
			var j = this;
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			this.itemContInforGrid = new ContInforGrid();
			this.itemJg = new jg();
			this.itemContInforForm = new ContInforForm();
			return [this.itemContInforGrid, this.itemJg, this.itemContInforForm]
		}
	});
	Ext.define("ContInforGrid", {				//5.1.合同信息表格
		extend : "PopupGridPanel",
		autoScroll : true,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		id : "contInforGrid",
		listeners : {
			select : function(o, k, n, p) {
				var l = k.get("id");
				var q = {
					contractId : l
				};
				var j = function(t) {
					if(!Ext.isEmpty(t.contractView)) {
						var v = new ContractModel(t.contractView.contract);
						v.set(CONFIGNAME.get("contract_deptName"), t.contractView.contract.deptName);
						v.set(CONFIGNAME.get("contract_beforeContractNum"), t.contractView.contract.beforeContractNum);
						v.set(CONFIGNAME.get("contract_contractSubject"), t.contractView.contract.contractSubject);
						v.set(CONFIGNAME.get("contract_custCompany"), t.contractView.contract.custCompany);
						v.set(CONFIGNAME.get("contract_regicapital"), t.contractView.contract.regicapital);
						v.set(CONFIGNAME.get("contract_contractStatus"), DpUtil.changeDictionaryCodeToDescrip(v.get(CONFIGNAME.get("contract_contractStatus")), DataDictionary.CONTACT_STATUS));
						v.set(CONFIGNAME.get("contract_payWay"), DpUtil.changeDictionaryCodeToDescrip(v.get(CONFIGNAME.get("contract_payWay")), DataDictionary.RECKON_WAY));
						v.set(CONFIGNAME.get("contract_preferentialType"), DpUtil.changeDictionaryCodeToDescrip(v.get(CONFIGNAME.get("contract_preferentialType")), DataDictionary.PRIVILEGE_TYPE));
						var x = (v.get(CONFIGNAME.get("contract_reconDate")) == 0 ? null : v.get(CONFIGNAME.get("contract_reconDate")) + i18n("i18n.memberView.day")), w = (v.get(CONFIGNAME.get("contract_resultDates")) == 0 ? null : v.get(CONFIGNAME.get("contract_resultDates")) + i18n("i18n.memberView.day")), u = (v.get(CONFIGNAME.get("contract_invoicDate")) == 0 ? null : v.get(CONFIGNAME.get("contract_invoicDate")) + i18n("i18n.memberView.day"));
						v.set(CONFIGNAME.get("contract_reconDate"), x);
						v.set(CONFIGNAME.get("contract_resultDates"), w);
						v.set(CONFIGNAME.get("contract_invoicDate"), u);
						v.set(CONFIGNAME.get("contract_contractBeginDate"), new Date(v.get(CONFIGNAME.get("contract_contractBeginDate"))).format("yyyy-MM-dd"));
						v.set(CONFIGNAME.get("contract_contractendDate"), new Date(v.get(CONFIGNAME.get("contract_contractendDate"))).format("yyyy-MM-dd"));
						if(!Ext.isEmpty(v.get(CONFIGNAME.get("contract_preferential")))) {
							var preferential = v.get(CONFIGNAME.get("contract_preferential"));
							var insuredPriceRate,receivePriceRate,deliveryPriceRate,chargeRebate,agentgathRate;
								
							insuredPriceRate = Ext.isEmpty(preferential.insuredPriceRate)?0:preferential.insuredPriceRate;
							receivePriceRate	= Ext.isEmpty(preferential.receivePriceRate)?0:preferential.receivePriceRate;
							deliveryPriceRate	= Ext.isEmpty(preferential.deliveryPriceRate)?0:preferential.deliveryPriceRate;
							chargeRebate		= Ext.isEmpty(preferential.chargeRebate)?0:preferential.chargeRebate;
							agentgathRate		= Ext.isEmpty(preferential.agentgathRate)?0:preferential.agentgathRate;
							v.set(CONFIGNAME.get("contract_insuredPriceRate"), insuredPriceRate);
							v.set(CONFIGNAME.get("contract_receivePriceRate"), receivePriceRate);
							v.set(CONFIGNAME.get("contract_deliveryPriceRate"), deliveryPriceRate);
							v.set(CONFIGNAME.get("contract_postageDiscounts"), chargeRebate);
							v.set(CONFIGNAME.get("contract_agentgathRate"), agentgathRate);
						};
						v.set(CONFIGNAME.get("contract_createUser"), t.contractView.createrName);
						v.set(CONFIGNAME.get("contract_createDeptName"), t.contractView.createDeptName);
						Ext.getCmp("contInforForm").getForm().loadRecord(v);
						var y = "";
						var r = t.contractView.contract.fileInfoList;
						if(!Ext.isEmpty(r)) {
							for(var s = 0; s < r.length; s++) {
								y = y + '<a href="../common/downLoad.action?fileName=' + r[s].fileName + "&inputPath=" + r[s].savePath + '">' + r[s].fileName + "</a>"
							}
						} else {
							y = i18n("i18n.memberView.haveNothing")
						}
						Ext.getCmp("contract_Annex").setRawValue(y)
					}
				};
				var m = function(r) {
					if(r)MessageUtil.showErrorMes(r.message)
				};
				MemberViewDataObj.searchContractByContractId(q, j, m)
			}
		},
		selModel : Ext.create("Ext.selection.CheckboxModel", {
			mode : "SINGLE"
		}),
		store : DpUtil.getStore("ContInforStoreId", "ReturnVisit", null, []),
		columns : [{
			header : i18n("i18n.memberView.auditNumber"),
			dataIndex : CONFIGNAME.get("contract_contractNum"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.termsOfPayment"),
			dataIndex : CONFIGNAME.get("contract_payWay"),
			flex : 1,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.RECKON_WAY)
			}
		}, {
			header : i18n("i18n.memberView.preferentialType"),
			dataIndex : CONFIGNAME.get("contract_preferentialType"),
			flex : 1,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.PRIVILEGE_TYPE)
			}
		}, {
			header : i18n("i18n.memberView.effectiveDate"),
			dataIndex : CONFIGNAME.get("contract_contractBeginDate"),
			flex : 1,
			renderer : function(j) {
				return new Date(j).format("yyyy-MM-dd")
			}
		}, {
			header : i18n("i18n.memberView.expirationDate"),
			dataIndex : CONFIGNAME.get("contract_contractendDate"),
			flex : 1,
			renderer : function(j) {
				return new Date(j).format("yyyy-MM-dd")
			}
		}, {
			header : i18n("i18n.memberView.monthlyAmount"),
			dataIndex : CONFIGNAME.get("contract_arrearaMount"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.protocol"),
			dataIndex : CONFIGNAME.get("contract_linkManName"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.mobileNumber"),
			dataIndex : CONFIGNAME.get("contract_linkManMobile"),
			flex : 1
		}],
		height : 200
	});
	Ext.define("ContInforForm", {				//5.2.合同信息form
		extend : "TitleFormPanel",
		id : "contInforForm",
		items : null,
		initComponent : function() {
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			var j = this;
			return [{
				xtype : "basicfiledset",
				layout : {
					type : "table",
					columns : 3
				},
				defaultType : "textfield",
				defaults : {
					readOnly : true,
					cls : "readonly",
					labelWidth : 80,
					width : 210
				},
				items : [{
					fieldLabel : i18n("i18n.memberView.deptName"),				//1.部门名称
					name : CONFIGNAME.get("contract_deptName")
				},{
					fieldLabel : i18n("i18n.memberView.contractStatus"),		//2.合同状态
					name : CONFIGNAME.get("contract_contractStatus")
				},  {
					fieldLabel : i18n("i18n.memberView.beforeContractNum"),		//3.原合同编号
					name : CONFIGNAME.get("contract_beforeContractNum")
				}, {
					fieldLabel : i18n("i18n.memberView.auditNumber"),			//4.合同编号
					name : CONFIGNAME.get("contract_contractNum")
				}, {
					fieldLabel : i18n("i18n.memberView.contractSubject"),		//5.合同主体
					name : CONFIGNAME.get("contract_contractSubject")
				}, {
					fieldLabel : i18n("i18n.memberView.custCompany"),			//6.客户全称
					name : CONFIGNAME.get("contract_custCompany")
				}, {
					fieldLabel : i18n("i18n.memberView.preferentialType"),		//8.优惠类型
					name : CONFIGNAME.get("contract_preferentialType")
				}, {
					fieldLabel : i18n("i18n.memberView.goodsNm"),				//9.走货名称
					name : CONFIGNAME.get("contract_goodsName")
				}, {
					fieldLabel : i18n("i18n.memberView.regicapital"),			//10.注册资金
					name : CONFIGNAME.get("contract_regicapital")
				}, {
					fieldLabel : i18n("i18n.memberView.termsOfPayment"),		//11.付款方式TODO:改为——结款方式
					name : CONFIGNAME.get("contract_payWay")
				}, {
					fieldLabel : i18n("i18n.memberView.debtAmountApplication"),	//12.申请欠款额度
					name : CONFIGNAME.get("contract_arrearaMount")
				}, {
					fieldLabel : i18n("i18n.memberView.contractStartDate"),		//13.合同起始日期
					name : CONFIGNAME.get("contract_contractBeginDate")
				}, {
					fieldLabel : i18n("i18n.memberView.protocol"),				//14.协议联系人
					name : CONFIGNAME.get("contract_linkManName")
				}, {
					fieldLabel : i18n("i18n.memberView.contactMobilePhone"),	//15.联系人手机
					name : CONFIGNAME.get("contract_linkManMobile")
				}, {
					fieldLabel : i18n("i18n.memberView.telephoneNumber"),		//16.固定电话
					name : CONFIGNAME.get("contract_linkManPhone")
				}, {
					fieldLabel : i18n("i18n.memberView.contractEndDate"),		//17.合同到期日期
					name : CONFIGNAME.get("contract_contractendDate")
				}, {
					fieldLabel : i18n("i18n.memberView.checkingDate"),			//18.对账日期
					name : CONFIGNAME.get("contract_reconDate")
				}, {
					fieldLabel : i18n("i18n.memberView.invoiceDate"),			//19.开发票日期
					name : CONFIGNAME.get("contract_invoicDate")
				}, {
					fieldLabel : i18n("i18n.memberView.dateOfLoan"),			//20.结款日期
					name : CONFIGNAME.get("contract_resultDates")
				}, {
					xtype : "container",
					width : 210,
					items : [{
						xtype : "textfield",
						fieldLabel : i18n("i18n.memberView.chargeRate"),		//保价费率
						width : 190,
						labelWidth : 80,
						readOnly : true,
						cls : "readonly",
						style : "float:left;",
						name : CONFIGNAME.get("contract_insuredPriceRate")
					}, {
						xtype : "label",
						text : i18n("i18n.memberView.Fold"),		//"折",
						style : "float:left;",
						width : 14,
						padding:'4 0 0 6'
					}]
				}, {
					xtype:"container",
					width:210,
					items:[{
						xtype:"textfield",
						fieldLabel : i18n("i18n.memberView.agentgathRate"),		//代收费率
						width:190,
						labelWidth:80,
						readOnly:true,
						cls:"readonly",
						style:"float:left;",
						name : CONFIGNAME.get("contract_agentgathRate")						
					},{
						xtype : "label",
						text : i18n("i18n.memberView.Fold"),		//"折",
						style : "float:left;",
						width : 20,
						padding:'4 0 0 6'
					}]
				}, {
					xtype:"container",	// 运费折扣
					width:210,
					items:[{
						xtype:"textfield",
						fieldLabel : i18n("i18n.memberView.postageDiscounts"),	//运费折扣
						width:190,
						labelWidth:80,
						readOnly:true,
						cls:"readonly",
						style:"float:left;",
						name : CONFIGNAME.get("contract_postageDiscounts")	//chargeRebate				
					},{
						xtype : "label",
						text : i18n("i18n.memberView.Fold"),		//"折",
						style : "float:left;",
						width : 20,
						padding:'4 0 0 6'
					}]
				}, {
					xtype : "container",
					width : 210,
					items : [{
						xtype : "textfield",
						fieldLabel : i18n("i18n.memberView.deliveryFee"),		//接货费率
						width : 190,
						labelWidth : 80,
						readOnly : true,
						cls : "readonly",
						style : "float:left;",
						name : CONFIGNAME.get("contract_receivePriceRate")
					}, {
						xtype : "label",
						text : i18n("i18n.memberView.Fold"),
						style : "float:left;",
						width : 20,
						padding:'4 0 0 6'
					}]
				}, {
					xtype : "container",
					width : 210,
					items : [{
						xtype : "textfield",
						fieldLabel : i18n("i18n.memberView.deliveryCharges"),	//送货费率
						width : 190,
						labelWidth : 80,
						readOnly : true,
						cls : "readonly",
						style : "float:left;",
						name : CONFIGNAME.get("contract_deliveryPriceRate")
					}, {
						xtype : "label",
						text : i18n("i18n.memberView.Fold"),
						style : "float:left;",
						width : 20,
						padding:'4 0 0 6'
					}]
				}, {
					fieldLabel : i18n("i18n.memberView.applicationReasons"),	//申请事由	-大的
					xtype : "textarea",
					width : 645,
					colspan : 3,
					name : CONFIGNAME.get("contract_application")
				}, {
					xtype : "displayfield",
					fieldLabel : i18n("i18n.memberView.contractAnnex"),			//合同附件
					id : "contract_Annex",
					style : "text-align: left;",
					width : 620,
					height : 20,
					colspan : 3
				}, {
					fieldLabel : i18n("i18n.memberView.createPeople"),
					name : CONFIGNAME.get("contract_createUser")
				}, {
					fieldLabel : i18n("i18n.memberView.createBranch"),
					name : CONFIGNAME.get("contract_createDeptName"),
					labelWidth : 70,
					width : 210
				}, {
					fieldLabel : i18n("i18n.memberView.createTime"),
					name : CONFIGNAME.get("contract_createDate"),
					labelWidth : 70,
					width : 210
				}]
			}]
		}
	});
	Ext.define("MaintenanceInfor", {				//6.维护信息MaintenanceInfor
		extend : "TabContentPanel",
		title : i18n("i18n.memberView.maintenanceInformation"),
		height : 500,
		autoScroll : true,
		itemPlanScheduleDetails : null,
		itemMaintenanceRecords : null,
		items : null,
		layout : {
			type : "vbox",
			align : "stretch"
		},
		initComponent : function() {
			this.items = this.getItems();
			this.callParent()
		},
		listeners : {
			activate : function(m, n) {
				if(Ext.getCmp("planScheduleDetails").getStore().getCount() != 0 || Ext.getCmp("maintenanceRecords").getStore().getCount() != 0) {
					return
				}
				var p = CustNumber;
				if(Ext.isEmpty(p)) {
					MessageUtil.showMessage(i18n("i18n.memberView.pleaseInputCustNum"));
					return
				}
				var l = CustId;
				var o = {
					custId : l
				};
				var j = function(q) {
					Ext.getCmp("planScheduleDetails").getStore().removeAll();
					Ext.getCmp("maintenanceRecords").getStore().removeAll();
					if(!Ext.isEmpty(q.integratedCustDevView.planScheduleList)) {
						Ext.getCmp("planScheduleDetails").getStore().loadData(q.integratedCustDevView.planScheduleList)
					}
					if(!Ext.isEmpty(q.integratedCustDevView.visitRecordList)) {
						Ext.getCmp("maintenanceRecords").getStore().loadData(q.integratedCustDevView.visitRecordList)
					}
				};
				var k = function(q) {
					MessageUtil.showErrorMes(q.message)
				};
				MemberViewDataObj.searchIntegratedCustDevViewByCustNum(o, j, k)
			}
		},
		getItems : function() {
			return [new PlanScheduleDetails(), new MaintenanceRecords()]
		}
	});
	Ext.define("PlanScheduleDetails", {				//6.1计划/日程详情grid
		id : "planScheduleDetails",
		extend : "PopupGridPanel",
		autoScroll : true,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		title : i18n("i18n.memberView.plan/ScheduleDetails"),
		store : DpUtil.getStore("PlanScheduleDetailsStoreId", "ReturnVisit", null, []),
		columns : [new Ext.grid.RowNumberer(), {
			header : i18n("i18n.memberView.planPeople"),
			dataIndex : CONFIGNAME.get("returnVisit_formulater"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.executor"),
			dataIndex : CONFIGNAME.get("returnVisit_userName"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.contactName"),
			dataIndex : CONFIGNAME.get("returnVisit_linkName"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.mobileNumber"),
			dataIndex : CONFIGNAME.get("returnVisit_linkManMobile"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.telephoneNumber"),
			dataIndex : CONFIGNAME.get("returnVisit_linkManPhone"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.programExecutionTime"),
			dataIndex : CONFIGNAME.get("returnVisit_schedule"),
			flex : 1,
			renderer : function(j) {
				return new Date(j).format("yyyy-MM-dd")
			}
		}],
		flex : 1
	});
	Ext.define("MaintenanceRecords", {				//6.2维护记录grid
		extend : "PopupGridPanel",
		autoScroll : true,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		id : "maintenanceRecords",
		title : i18n("i18n.memberView.maintenanceRecords"),
		store : DpUtil.getStore("MaintenanceRecordsStoreId", "ReturnVisit", null, []),
		columns : [new Ext.grid.RowNumberer(), {
			header : i18n("i18n.memberView.executor"),
			dataIndex : CONFIGNAME.get("returnVisit_userName"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.contactName"),
			dataIndex : CONFIGNAME.get("returnVisit_linkName"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.mobileNumber"),
			dataIndex : CONFIGNAME.get("returnVisit_linkManMobile"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.telephoneNumber"),
			dataIndex : CONFIGNAME.get("returnVisit_linkManPhone"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.requirementsClassification"),
			dataIndex : CONFIGNAME.get("returnVisit_needType"),
			flex : 1,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.CUSTOMER_IDEA)
			}
		}, {
			header : i18n("i18n.memberView.needsAndProblems"),
			dataIndex : CONFIGNAME.get("returnVisit_problem"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.demandProblemSolvingMethod"),
			dataIndex : CONFIGNAME.get("returnVisit_solution"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.specificMaintenanceMode"),
			dataIndex : CONFIGNAME.get("returnVisit_way"),
			flex : 1,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.MAINTAIN_WAY)
			}
		}, {
			header : i18n("i18n.memberView.maintenanceTime"),
			dataIndex : CONFIGNAME.get("returnVisit_schedule"),
			flex : 1,
			renderer : function(j) {
				return new Date(j).format("yyyy-MM-dd")
			}
		}],
		flex : 1
	});
	Ext.define("OrderInfor", {					//7.订单信息OrderInfor
		extend : "TabContentPanel",
		title : i18n("i18n.memberView.orderInfo"),
		height : 500,
		autoScroll : true,
		layout : {
			type : "vbox",
			align : "stretch"
		},
		items : null,
		itemOrderInforForm : null,
		itemOrderInforGrid : null,
		initComponent : function() {
			var j = this;
			this.items = this.getItems();
			Ext.getCmp("orderInforGrid").getStore().on("beforeload", function(m, l, o) {
				var k = Ext.getCmp("searchOrderDateFrom").getValue();
				var n = Ext.getCmp("searchOrderDateTo").getValue();
				var p = {
					custId : CustId,
					searchOrderDateFrom : k,
					searchOrderDateTo : n
				};
				Ext.apply(l, {
					params : p
				})
			});
			this.callParent()
		},
		getItems : function() {
			this.itemOrderInforForm = new OrderInforForm();
			this.itemOrderInforGrid = new OrderInforGrid();
			return [this.itemOrderInforForm, this.itemOrderInforGrid]
		}
	});
	Ext.define("OrderInforForm", {				// 7.1.订单信息查询表单
		extend : "BasicHboxFormPanel",
		id : "OrderInforFormId",
		height : 42,
		width : 800,
		items : null,
		fbar : null,
		initComponent : function() {
			var j = this;
			j.items = j.getItems();
			this.callParent()
		},
		searchOrder : function() {
			var l = CustNumber;
			if(Ext.isEmpty(l)) {
				MessageUtil.showMessage(i18n("i18n.memberView.pleaseInputCustNum"));
				return
			}
			var j = Ext.getCmp("searchOrderDateFrom").getValue();
			var k = Ext.getCmp("searchOrderDateTo").getValue();
			if(DpUtil.isFindOutDeadline(j, k, 90)) {
				MessageUtil.showMessage(i18n("i18n.memberView.moreSearchDate"));
				return
			}
			Ext.getCmp("orderInforGrid_pagingToolbar").moveFirst()
		},
		getItems : function() {
			var j = this;
			return [{
				xtype : "basicformpanel",
				layout : {
					type : "table",
					columns : 3
				},
				defaultType : "textfield",
				defaults : {
					labelWidth : 24,
					padding : "10 0 0 0",
					width : 250
				},
				items : [{
					fieldLabel : i18n("i18n.memberView.from"),
					id : "searchOrderDateFrom",
					value : new Date(),
					xtype : "datefield",
					editable : false
				}, {
					fieldLabel : i18n("i18n.memberView.to"),
					id : "searchOrderDateTo",
					value : new Date(),
					xtype : "datefield",
					editable : false
				}, {
					xtype : "btnpanel",
					width : 100,
					items : [{
						xtype : "button",
						text : i18n("i18n.memberView.search"),
						defaults : {
							margins : "0 0 5 0"
						},
						handler : function() {
							j.searchOrder()
						}
					}]
				}]
			}]
		}
	});
	Ext.define("OrderInforGrid", {					// 7.2.订单信息表格
		id : "orderInforGrid",
		extend : "PopupGridPanel",
		autoScroll : true,
		flex : 1,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		store : MemberViewDataObj.searchOrderListByCustNum(),
		columns : [new Ext.grid.RowNumberer(), {
			header : i18n("i18n.memberView.orderNum"),
			dataIndex : CONFIGNAME.get("order_orderNum"),
			// flex : 1
			width:120
		}, {
			header : i18n("i18n.memberView.waybillNumber"),
			dataIndex : CONFIGNAME.get("order_waybillNum"),
			// flex : 1
			width:120
		}, {
			header : i18n("i18n.memberView.orderSource"),
			dataIndex : CONFIGNAME.get("order_resource"),
			// flex : 1,
			width:150,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.ORDER_SOURCE)
			}
		}, {
			header : i18n("i18n.memberView.consignor"),
			dataIndex : CONFIGNAME.get("order_consignorName"),
			// flex : 1
			width:120
		}, {
			header : i18n("i18n.memberView.goodsName"),
			dataIndex : CONFIGNAME.get("order_goodsName"),
			// flex : 1
			width:150
		}, {
			header : i18n("i18n.memberView.theNumberOf"),
			dataIndex : CONFIGNAME.get("order_goodsTotalNumber"),
			// flex : 1,
			width:80,
			renderer : function(j) {
				return j + i18n("i18n.memberView.parts")
			}
		}, {
			header : i18n("i18n.memberView.weight"),
			dataIndex : CONFIGNAME.get("order_goodsTotalWeight"),
			// flex : 1,
			width:80,
			renderer : function(j) {
				return j + i18n("i18n.memberView.kg")
			}
		}, {
			header : i18n("i18n.memberView.volume"),
			dataIndex : CONFIGNAME.get("order_goodsTotalVolume"),
			// flex : 1,
			width:80,
			renderer : function(j) {
				return j + i18n("i18n.memberView.ccubicMetre")
			}
		}, {
			header : i18n("i18n.memberView.orderStatus"),
			dataIndex : CONFIGNAME.get("order_orderStatus"),
			// flex : 1,
			width:60,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.ORDER_STATUS)
			}
		}, {
			header : i18n("i18n.memberView.startStation"),
			dataIndex : CONFIGNAME.get("order_startStationName"),
			// flex : 1
			width:150
		}, {
			header : i18n("i18n.memberView.processStation"),
			dataIndex : CONFIGNAME.get("order_acceptDeptName"),
			// flex : 1
			width:150
		}],
		initComponent : function() {
			var j = this;
			j.bbar = j.getBBar();
			this.callParent()
		},
		getBBar : function() {
			var j = this;
			return Ext.create("Ext.toolbar.Paging", {
				id : "orderInforGrid_pagingToolbar",
				store : j.store,
				displayMsg : i18n("i18n.memberView.displayMsg"),
				displayInfo : true,
				items : ["-", {
					text : i18n("i18n.memberView.page_count"),
					xtype : "tbtext"
				}, Ext.create("Ext.form.ComboBox", {
					width : 50,
					value : "10",
					triggerAction : "all",
					forceSelection : true,
					editable : false,
					name : "comboItem",
					displayField : "value",
					valueField : "value",
					queryMode : "local",
					store : Ext.create("Ext.data.Store", {
						fields : ["value"],
						data : [{
							value : "10"
						}, {
							value : "15"
						}, {
							value : "20"
						}, {
							value : "25"
						}, {
							value : "40"
						}, {
							value : "100"
						}]
					}),
					listeners : {
						select : {
							scope : this,
							fn : function(m, l) {
								var k = Ext.getCmp("orderInforGrid").store.pageSize;
								var n = parseInt(m.value);
								if(k != n) {
									Ext.getCmp("orderInforGrid").store.pageSize = n;
									Ext.getCmp("orderInforGrid_pagingToolbar").moveFirst()
								}
							}
						}
					}
				}), {
					text : i18n("i18n.memberView.pageNumber"),
					xtype : "tbtext"
				}]
			})
		},
		height : 200
	});
	Ext.define("IntegrationInfor", {				//8.积分信息IntegrationInfor
		extend : "TabContentPanel",
		title : i18n("i18n.memberView.pointsOfInformation"),
		height : 500,
		autoScroll : true,
		layout : {
			type : "vbox",
			align : "stretch"
		},
		itemIntegrationForm : null,
		itemIntegrationGrid : null,
		items : null,
		custNumber : null,
		initComponent : function() {
			var j = this;
			this.items = this.getItems();
			this.callParent()
		},
		listeners : {
			activate : function(n, o) {
				var k = CustNumber, m = Ext.getCmp("IntegrationID").getStore().getCount();
				if(Ext.isEmpty(k)) {
					MessageUtil.showMessage(i18n("i18n.memberView.pleaseInputCustNum"));
					return
				}
				if(m != 0) {
					return
				}
				var p = {
					custId : CustId
				}, j = function(q) {
					var r = new IntegralFormModel(q.memberPointsView.memberIntegral);
					r.set(CONFIGNAME.get("I_AllowedContactExchangePoints"), DpUtil.changeTrueAndFalse(q.memberPointsView.memberIntegral.member.isRedeempoints));
					Ext.getCmp("integrationFormID").getForm().loadRecord(r);
					Ext.getCmp("IntegrationID").getStore().loadData(q.memberPointsView.contactIntegralList, false);
					Ext.getCmp("IntegrationID").getStore().each(function(s) {
						s.set(CONFIGNAME.get("IF_contactName"), s.get(CONFIGNAME.get("IF_contact")).name);
						s.set(CONFIGNAME.get("IF_contactCoding"), s.get(CONFIGNAME.get("IF_contact")).number);
						s.commit()
					})
				}, l = function(q) {
					MessageUtil.showErrorMes(q.message)
				};
				MemberViewDataObj.searchMemberPointsViewByCustNum(p, j, l)
			}
		},
		getItems : function() {
			this.itemIntegrationForm = new IntegrationForm();
			this.itemIntegrationGrid = new IntegrationGrid();
			return [this.itemIntegrationForm, this.itemIntegrationGrid]
		}
	});
	Ext.define("IntegrationForm", {					//8.1会员积分信息
		extend : "TitleFormPanel",
		id : "integrationFormID",
		title : i18n("i18n.memberView.memberPointsInformation"),
		items : null,
		initComponent : function() {
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			return [{
				xtype : "basicfiledset",
				layout : {
					type : "table",
					columns : 3
				},
				defaultType : "textfield",
				defaults : {
					readOnly : true,
					cls : "readonly"
				},
				items : [{
					fieldLabel : i18n("i18n.memberView.customersTotalScore"),
					name : CONFIGNAME.get("I_memberIntegral"),
					labelWidth : 70,
					width : 180
				}, {
					fieldLabel : i18n("i18n.memberView.customerAvailablePoints"),
					name : CONFIGNAME.get("I_customerAvailablePoints"),
					labelWidth : 100,
					width : 200
				}, {
					fieldLabel : i18n("i18n.memberView.AllowedContactExchangePoints"),
					name : CONFIGNAME.get("I_AllowedContactExchangePoints"),
					labelWidth : 140,
					width : 180
				}]
			}]
		}
	});
	Ext.define("IntegrationGrid", {					//8.2联系人积分详情表格
		extend : "PopupGridPanel",
		autoScroll : true,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		id : "IntegrationID",
		store : DpUtil.getStore("IntegrationStoreId", "IntegralModel", null, []),
		columns : [new Ext.grid.RowNumberer(), {
			header : i18n("i18n.memberView.contactName"),
			dataIndex : CONFIGNAME.get("IF_contactName"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.contactCoding"),
			dataIndex : CONFIGNAME.get("IF_contactCoding"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.availableIntegralThisPeriod"),
			dataIndex : CONFIGNAME.get("IF_availablePoints"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.thisIssueHasBeenWithTheIntegral"),
			dataIndex : CONFIGNAME.get("IF_thisIssueHasBeenWithTheIntegral"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.currentAccumulatedPoints"),
			dataIndex : CONFIGNAME.get("IF_currentAccumulatedPoints"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.theCumulativeTotalScore"),
			dataIndex : CONFIGNAME.get("IF_cumulativeTotalScore"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.finallyIntegralTime"),
			dataIndex : CONFIGNAME.get("IF_finalIntegrationTime"),
			flex : 1,
			renderer : function(j) {
				return new Date(j).format("yyyy-MM-dd")
			}
		}],
		flex : 3
	});
	Ext.define("ComplaintAndClaimInfor", {			//9.投诉理赔信息ComplaintAndClaimInfor
		extend : "TabContentPanel",
		title : i18n("i18n.memberView.complaintClaimsInformation"),
		height : 500,
		autoScroll : true,
		itemComplaintGrid : null,
		itemClaimsGrid : null,
		items : null,
		initComponent : function() {
			var j = this;
			this.items = this.getItems();
			this.callParent()
		},
		listeners : {
			activate : function(m, n) {
				if(Ext.getCmp("complaintGrid").getStore().getCount() != 0 || Ext.getCmp("claimsGrid").getStore().getCount() != 0) {
					return
				}
				var p = CustNumber;
				var k = CustId;
				if(Ext.isEmpty(p)) {
					MessageUtil.showMessage(i18n("i18n.memberView.pleaseInputCustNum"));
					return
				}
				var o = {
					custNum : p,
					custId : k
				};
				var j = function(q) {
					Ext.getCmp("complaintGrid").getStore().removeAll();
					Ext.getCmp("claimsGrid").getStore().removeAll();
					if(!Ext.isEmpty(q.complaintRecompenseView.complaintList)) {
						Ext.getCmp("complaintGrid").getStore().loadData(q.complaintRecompenseView.complaintList)
					}
					if(!Ext.isEmpty(q.complaintRecompenseView.recompenseList)) {
						Ext.getCmp("claimsGrid").getStore().loadData(q.complaintRecompenseView.recompenseList)
					}
					Ext.getCmp("claimsGrid").getStore().each(function(r) {
						r.set(CONFIGNAME.get("recompense_waybillNumber"), r.get(CONFIGNAME.get("recompense_waybill")).waybillNumber);
						r.set(CONFIGNAME.get("recompense_receiveDept"), r.get(CONFIGNAME.get("recompense_waybill")).receiveDept);
						r.commit()
					})
				};
				var l = function(q) {
					MessageUtil.showErrorMes(q.message)
				};
				MemberViewDataObj.searchComplaintRecompenseViewByCustNum(o, j, l)
			}
		},
		getItems : function() {
			this.itemComplaintGrid = new ComplaintGrid();
			this.itemClaimsGrid = new ClaimsGrid();
			return [this.itemComplaintGrid, this.itemClaimsGrid]
		}
	});
	Ext.define("ComplaintGrid", {				//9.1投诉你表哥
		extend : "PopupGridPanel",
		autoScroll : true,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		id : "complaintGrid",
		title : i18n("i18n.memberView.complaintInformation"),
		store : DpUtil.getStore("ComplaintGridStoreId", "Complaint", null, []),
		columns : [new Ext.grid.RowNumberer(), {
			header : i18n("i18n.memberView.treatmentNumber"),
			dataIndex : CONFIGNAME.get("complaint_dealbill"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.waybillNum"),	//i18n("i18n.memberView.billNum"),
			dataIndex : CONFIGNAME.get("complaint_bill"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.caller"),
			dataIndex : CONFIGNAME.get("complaint_compman"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.linkMethod"),
			dataIndex : CONFIGNAME.get("complaint_tel"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.complaintTime"),
			dataIndex : CONFIGNAME.get("complaint_reporttime"),
			flex : 1.25,
			renderer : function(j) {
				if(Ext.isEmpty(j)) {
					return
				}
				return new Date(j).format("yyyy-MM-dd hh:mm:ss")
			}
		}, {
			header : i18n("i18n.memberView.complaintsType"),
			dataIndex : CONFIGNAME.get("complaint_reporttype"),
			flex : 1,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.REPORT_TYPE)
			}
		}, {
			header : i18n("i18n.memberView.processingStatus"),
			dataIndex : CONFIGNAME.get("complaint_prostatus"),
			flex : 1,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.COMPLAINT_PROCESS_STATUS)
			}
		}, {
			header : i18n("i18n.memberView.slovstion"),
			dataIndex : CONFIGNAME.get("complaint_dealstatus"),
			flex : 1,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.RESOLVE_CASE)
			}
		}, {
			header : i18n("i18n.memberView.cussatisfaction"),
			dataIndex : CONFIGNAME.get("complaint_cussatisfaction"),
			flex : 1,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.SATISFACTION_DEGREE)
			}
		}],
		height : 200
	});
	Ext.define("ClaimsGrid", {					//9.2理赔信息表哥
		extend : "PopupGridPanel",
		autoScroll : true,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		id : "claimsGrid",
		title : i18n("i18n.memberView.recompenseInfo"),
		store : DpUtil.getStore("ClaimsGridStoreId", "RecompenseApp", null, []),
		columns : [new Ext.grid.RowNumberer(), {
			header : i18n("i18n.memberView.waybillNum"),
			dataIndex : CONFIGNAME.get("recompense_waybillNumber"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.startingSector"),
			dataIndex : CONFIGNAME.get("recompense_receiveDept"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.accidentType"),
			dataIndex : CONFIGNAME.get("recompense_insurType"),
			flex : 1,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.DANGER_TYPE)
			}
		}, {
			header : i18n("i18n.memberView.claimTypes"),
			dataIndex : CONFIGNAME.get("recompense_recompenseType"),
			flex : 1,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.RECOMPENSE_TYPE)
			}
		}, {
			header : i18n("i18n.memberView.claimForm"),
			dataIndex : CONFIGNAME.get("recompense_recompenseMethod"),
			flex : 1,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.RECOMPENSE_WAY)
			}
		}, {
			header : i18n("i18n.memberView.claimAmount"),
			dataIndex : CONFIGNAME.get("recompense_realAmount"),
			flex : 1
		}, {
			header : i18n("i18n.memberView.processingStatus"),
			dataIndex : CONFIGNAME.get("recompense_status"),
			flex : 1,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.RECOMPENSE_STATUS)
			}
		}],
		height : 200
	});
	Ext.define("OperatingDecisionInfor", {		//10.运营决策分析OperatingDecisionInfor
		extend : "TabContentPanel",
		title : i18n("i18n.memberView.analysisOfOperationalDecisions"),
		items : null,
		height : 960,
		autoScroll : true,
		border : true,
		itemOperatingDecisionForm : null,
		itemOperatingDecisionPanel : null,
		itemOperatingDecisionTabs : null,
		initComponent : function() {
			var j = this;
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			this.itemOperatingDecisionForm = new OperatingDecisionForm();
			this.itemOperatingDecisionPanel = new OperatingDecisionPanel();
			this.itemOperatingDecisionTabs = new OperatingDecisionTabs();
			return [this.itemOperatingDecisionForm, this.itemOperatingDecisionPanel, this.itemOperatingDecisionTabs]
		},
		listeners : {
			activate : function(j, k) {
				if(Ext.getCmp("complaintGrid").getStore().getCount() != 0 || Ext.getCmp("claimsGrid").getStore().getCount() != 0) {
					return
				}
				var l = CustNumber;
				if(Ext.isEmpty(l)) {
					MessageUtil.showMessage(i18n("i18n.memberView.pleaseInputCustNum"));
					return
				}
			}
		}
	});
	Ext.define("OperatingDecisionForm", {			//10.1运营决策分析OperatingDecisionInfor
		extend : "BasicHboxFormPanel",
		id : "OperatingDecisionFormId",
		height : 27,
		items : null,
		fbar : null,
		initComponent : function() {
			var j = this;
			j.items = j.getItems();
			this.callParent()
		},
		searchOperatingDecision : function() {
			var k = Ext.getCmp("operatingDecisionFrom").getValue();
			var n = Ext.getCmp("operatingDecisionTo").getValue();
			if(Ext.isEmpty(k) || Ext.isEmpty(n)) {
				MessageUtil.showMessage(i18n("i18n.memberView.pleaseInputDate"));
				return
			}
			var o = (n.getFullYear() - k.getFullYear()) * 12 + n.getMonth() - k.getMonth();
			if(o > 12 || o < 0) {
				MessageUtil.showMessage(i18n("i18n.memberView.12month"));
				return
			}
			var q = CustNumber;
			if(Ext.isEmpty(q)) {
				MessageUtil.showMessage(i18n("i18n.memberView.pleaseInputCustNum"));
				return
			}
			var m = CustId;
			Ext.getCmp("custViewSearchOperatingDecision").setDisabled(true);
			var j = function(r) {
				Ext.getCmp("custViewSearchOperatingDecision").setDisabled(false);
				Ext.getCmp("operatingDecisionR").getStore().loadData(r.twelveMonthList);
				if(!Ext.isEmpty(r.operationAnalysisList)) {
					f.show();
					e.show();
					d.show();
					c.show();
					b.show();
					a.show()
				}
				Ext.data.StoreManager.lookup("VolumeStructureData").loadData(r.operationAnalysisList)
			};
			var l = function(r) {
				Ext.getCmp("custViewSearchOperatingDecision").setDisabled(false);
				MessageUtil.showErrorMes(r.message)
			};
			var p = {
				searchOrderDateFrom : k,
				searchOrderDateTo : n,
				custId : m
			};
			MemberViewDataObj.searchOperatingDecision(p, j, l)
		},
		getItems : function() {
			var j = this;
			return [{
				xtype : "basicformpanel",
				layout : {
					type : "table",
					columns : 3
				},
				defaultType : "textfield",
				defaults : {
					labelWidth : 30,
					width : 200
				},
				items : [{
					fieldLabel : i18n("i18n.memberView.from"),
					id : "operatingDecisionFrom",
					xtype : "datefield",
					format : "y-m",
					editable : false
				}, {
					fieldLabel : i18n("i18n.memberView.to"),
					id : "operatingDecisionTo",
					xtype : "datefield",
					format : "y-m",
					editable : false
				}, {
					xtype : "btnpanel",
					width : 160,
					items : [{
						xtype : "button",
						text : i18n("i18n.memberView.search"),
						id : "custViewSearchOperatingDecision",
						handler : function() {
							j.searchOperatingDecision()
						}
					}, {
						xtype : "button",
						text : i18n("i18n.memberView.exportFile"),
						handler : function() {
							var k = Ext.getCmp("operatingDecisionFrom").getValue();
							var m = Ext.getCmp("operatingDecisionTo").getValue();
							if(Ext.isEmpty(k) || Ext.isEmpty(m)) {
								MessageUtil.showMessage(i18n("i18n.memberView.pleaseInputDate"));
								return
							}
							var o = (m.getFullYear() - k.getFullYear()) * 12 + m.getMonth() - k.getMonth();
							if(o > 12 || o < 0) {
								MessageUtil.showMessage(i18n("i18n.memberView.12month"));
								return
							}
							var p = CustNumber;
							if(Ext.isEmpty(p)) {
								MessageUtil.showMessage(i18n("i18n.memberView.pleaseInputCustNum"));
								return
							}
							var l = CustId;
							var n = "../custview/downLoad.action?custId=" + l + "&searchOrderDateFrom=" + k.getTime() + "&searchOrderDateTo=" + m.getTime() + "&custName=" + CustName;
							window.location.href = n
						}
					}]
				}]
			}]
		}
	});
	Ext.define("OperatingDecisionL", {				//10.2.1
		extend : "TabContentPanel",
		height : 398,
		width : 100,
		layout : {
			type : "vbox",
			align : "center"
		},
		padding : 0,
		items : [{
			xtype : "panel",
			html : '<ul class="cls" style="font-weight:600;text-align:center;border:1px #b5b8c8 solid;border-top:1px #b5b8c8 solid; border-right:0;border-bottom:1px #b5b8c8 solid; height:379px;list-style:none;padding:0;"><li style="height:21.5px;line-height:21.5px;border-bottom:1px #ededed solid;border-top:1px #fff solid;">' + i18n("i18n.memberView.dimensions") + '</li><li style="height:84px;line-height:84px;border-bottom:1px #ededed solid;">' + i18n("i18n.memberView.volumeOfStructure") + '</li><li style="height:41px;line-height:41px;border-bottom:1px #ededed solid;">' + i18n("i18n.memberView.UseOfTheProduct") + '</li><li style="height:64.5px;line-height:64.5px;border-bottom:1px #ededed solid;">' + i18n("i18n.memberView.paulCampRatio") + '</li><li style="height:42px;line-height:42px;border-bottom:1px #ededed solid;">' + i18n("i18n.memberView.packageBattalionRatio") + '</li><li style="height:42px;line-height:42px;border-bottom:1px #ededed solid;">' + i18n("i18n.memberView.theAmountOfPayment") + '</li><li style="height:85px;line-height:85px;">' + i18n("i18n.memberView.ChannelPreference") + "</li></ul>",
			width : "100%",
			padding : 0,
			margin : 0,
			flex : 1
		}]
	});
	Ext.define("OperatingDecisionC", {			//10.2.2
		extend : "TabContentPanel",
		layout : {
			type : "vbox",
			align : "center"
		},
		height : 398,
		width : 122,
		padding : 0,
		items : [{
			xtype : "panel",
			html : '<table style="border-collapse:collapse;border-top:1px #b5b8c8 solid;border-left:1px #b5b8c8 solid;"><tr><td style="float:left;display:inline;width:120px;padding:3px 0 3px 0;font-size:12px;text-align:center;border-bottom:1px #ededed solid;">' + i18n("i18n.memberView.month") + '</td></tr><tr><td style="float:left;display:inline;width:120px;padding:3px 0 3px 0;font-size:12px;text-align:center;border-bottom:1px #ededed solid;">' + i18n("i18n.memberView.startingVolumeOfUnit") + '</td></tr><tr><td style="float:left;display:inline;width:120px;padding:3px 0 3px 0;font-size:12px;text-align:center;border-bottom:1px #ededed solid;">' + i18n("i18n.memberView.startingNumberOfVotesUnit") + '</td></tr><tr><td style="float:left;display:inline;width:120px;padding:3px 0 3px 0;font-size:12px;text-align:center;border-bottom:1px #ededed solid;">' + i18n("i18n.memberView.toReachTheVolumeOfUnit") + '</td></tr><tr><td style="float:left;display:inline;width:120px;padding:3px 0 3px 0;font-size:12px;text-align:center;border-bottom:1px #ededed solid;">' + i18n("i18n.memberView.reachTheNumberOfVotesUnit") + '</td></tr><tr><td style="float:left;display:inline;width:120px;padding:3px 0 3px 0;font-size:12px;text-align:center;border-bottom:1px #ededed solid;">' + i18n("i18n.memberView.advantageBusinessAccounted") + '</td></tr><tr><td style="float:left;display:inline;width:120px;padding:3px 0 3px 0;font-size:12px;text-align:center;border-bottom:1px #ededed solid;">' + i18n("i18n.memberView.automotiveAccountedFor") + '</td></tr><tr><td style="float:left;display:inline;width:120px;padding:3px 0 3px 0;font-size:12px;text-align:center;border-bottom:1px #ededed solid;">' + i18n("i18n.memberView.insuranceUnit") + '</td></tr><tr><td style="float:left;display:inline;width:120px;padding:3px 0 3px 0;font-size:12px;text-align:center;border-bottom:1px #ededed solid;">' + i18n("i18n.memberView.paulCampRatio") + '</td></tr><tr><td style="float:left;display:inline;width:120px;padding:3px 0 3px 0;font-size:12px;text-align:center;border-bottom:1px #ededed solid;">' + i18n("i18n.memberView.claimAmountUnit") + '</td></tr><tr><td style="float:left;display:inline;width:120px;padding:3px 0 3px 0;font-size:12px;text-align:center;border-bottom:1px #ededed solid;">' + i18n("i18n.memberView.packingChargesUnit") + '</td></tr><tr><td style="float:left;display:inline;width:120px;padding:3px 0 3px 0;font-size:12px;text-align:center;border-bottom:1px #ededed solid;">' + i18n("i18n.memberView.packageBattalionRatio") + '</td></tr><tr><td style="float:left;display:inline;width:120px;padding:3px 0 3px 0;font-size:12px;text-align:center;border-bottom:1px #ededed solid;">' + i18n("i18n.memberView.theAmountOfPaymentCollectionUnit") + '</td></tr><tr><td style="float:left;display:inline;width:120px;padding:3px 0 3px 0;font-size:12px;text-align:center;border-bottom:1px #ededed solid;">' + i18n("i18n.memberView.collectionOfMoreThan") + '</td></tr><tr><td style="float:left;display:inline;width:120px;padding:3px 0 3px 0;font-size:12px;text-align:center;border-bottom:1px #ededed solid;">' + i18n("i18n.memberView.cnlineBusiness") + '</td></tr><tr><td style="float:left;display:inline;width:120px;padding:3px 0 3px 0;font-size:12px;text-align:center;border-bottom:1px #ededed solid;">' + i18n("i18n.memberView.callCentersAccounted") + '</td></tr><tr><td style="float:left;display:inline;width:120px;padding:3px 0 3px 0;font-size:12px;text-align:center;border-bottom:1px #ededed solid;">' + i18n("i18n.memberView.theOperatingRoomAccounted") + '</td></tr><tr><td style="float:left;display:inline;width:120px;padding:3px 0 3px 0;font-size:12px;text-align:center;border-bottom:1px #b5b8c8 solid;">' + i18n("i18n.memberView.third-partyChannels") + "</td></tr></table>",
			width : "100%",
			padding : 0,
			margin : 0,
			flex : 1
		}]
	});
	Ext.define("OperatingDecisionR", {			//10.2.3
		extend : "PopupGridPanel",
		id : "operatingDecisionR",
		autoScroll : true,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		hideHeaders : true,
		columnLines : true,
		store : DpUtil.getStore("OperatingDecisionRStoreId", null, ["month1", "month2", "month3", "month4", "month5", "month6", "month7", "month8", "month9", "month10", "month11", "month12"], []),
		columns : [{
			header : "1",
			dataIndex : "month1",
			width : 65
		}, {
			header : "2",
			dataIndex : "month2",
			width : 65
		}, {
			header : "3",
			dataIndex : "month3",
			width : 65
		}, {
			header : "4",
			dataIndex : "month4",
			width : 65
		}, {
			header : "5",
			dataIndex : "month5",
			width : 65
		}, {
			header : "6",
			dataIndex : "month6",
			width : 65
		}, {
			header : "7",
			dataIndex : "month7",
			width : 65
		}, {
			header : "8",
			dataIndex : "month8",
			width : 65
		}, {
			header : "9",
			dataIndex : "month9",
			width : 65
		}, {
			header : "10",
			dataIndex : "month10",
			width : 65
		}, {
			header : "11",
			dataIndex : "month11",
			width : 65
		}, {
			header : "12",
			dataIndex : "month12",
			width : 65
		}],
		height : 395,
		width : 518,
		border : false,
		initComponent : function() {
			this.callParent()
		}
	});
	var f = Ext.create("widget.panel", {
		width : 600,
		height : 400,
		layout : "fit",
		items : {
			xtype : "chart",
			animate : false,
			store : DpUtil.getStore("VolumeStructureData", "OperationAnalysisModel", null, []),
			insetPadding : 30,
			axes : [{
				type : "Numeric",
				minimum : 0,
				position : "left",
				fields : ["arriveMoney"],
				title : false,
				grid : true,
				label : {
					renderer : Ext.util.Format.numberRenderer("0" + i18n("i18n.memberView.tonnec")),
					font : "10px Arial"
				}
			}, {
				type : "Numeric",
				minimum : 0,
				position : "right",
				fields : ["arriveBill"],
				title : false,
				grid : true,
				label : {
					renderer : Ext.util.Format.numberRenderer("0.0" + i18n("i18n.memberView.piao")),
					font : "10px Arial"
				}
			}, {
				type : "Category",
				position : "bottom",
				fields : ["yearMonth"],
				title : false,
				label : {
					font : "11px Arial",
					renderer : function(j) {
						return j
					}
				}
			}],
			series : [{
				type : "line",
				axis : "left",
				xField : "yearMonth",
				yField : "leaveMoney",
				tips : {
					trackMouse : true,
					width : 110,
					height : 50,
					renderer : function(k, j) {
						this.setTitle(k.get("yearMonth") + i18n("i18n.memberView.startingVolumeOf") + k.get("leaveMoney") + i18n("i18n.memberView.tonnec"))
					}
				},
				style : {
					fill : "#00ff00",
					stroke : "#00ff00",
					"stroke-width" : 3
				},
				markerConfig : {
					type : "circle",
					size : 4,
					radius : 4,
					"stroke-width" : 0,
					fill : "#00ff00",
					stroke : "#00ff00"
				}
			}, {
				type : "line",
				axis : "left",
				xField : "yearMonth",
				yField : "arriveMoney",
				tips : {
					trackMouse : true,
					width : 110,
					height : 50,
					renderer : function(k, j) {
						this.setTitle(k.get("yearMonth") + i18n("i18n.memberView.toReachTheVolumeOf") + k.get("arriveMoney") + i18n("i18n.memberView.tonnec"))
					}
				},
				style : {
					fill : "#ff0000",
					stroke : "#ff0000",
					"stroke-width" : 3
				},
				markerConfig : {
					type : "circle",
					size : 4,
					radius : 4,
					"stroke-width" : 0,
					fill : "#ff0000",
					stroke : "#ff0000"
				}
			}, {
				type : "line",
				axis : "right",
				xField : "yearMonth",
				yField : "leaveBill",
				tips : {
					trackMouse : true,
					width : 110,
					height : 50,
					renderer : function(k, j) {
						this.setTitle(k.get("yearMonth") + i18n("i18n.memberView.startingNumberOfVotes") + k.get("leaveBill") + i18n("i18n.memberView.piao"))
					}
				},
				style : {
					fill : "#38B8BF",
					stroke : "#38B8BF",
					"stroke-width" : 3
				},
				markerConfig : {
					type : "circle",
					size : 4,
					radius : 4,
					"stroke-width" : 0,
					fill : "#38B8BF",
					stroke : "#38B8BF"
				}
			}, {
				type : "line",
				axis : "right",
				xField : "yearMonth",
				yField : "arriveBill",
				tips : {
					trackMouse : true,
					width : 110,
					height : 50,
					renderer : function(k, j) {
						this.setTitle(k.get("yearMonth") + i18n("i18n.memberView.reachTheNumberOfVotes") + k.get("arriveBill") + i18n("i18n.memberView.piao"))
					}
				},
				style : {
					fill : "#000000",
					stroke : "#000000",
					"stroke-width" : 3
				},
				markerConfig : {
					type : "circle",
					size : 4,
					radius : 4,
					"stroke-width" : 0,
					fill : "#000000",
					stroke : "#000000"
				}
			}]
		}
	});
	var e = Ext.create("widget.panel", {
		width : 600,
		height : 400,
		layout : "fit",
		items : {
			xtype : "chart",
			animate : false,
			store : DpUtil.getStore("VolumeStructureData", "OperationAnalysisModel", null, []),
			insetPadding : 30,
			axes : [{
				type : "Numeric",
				minimum : 0,
				maximum : 100,
				position : "left",
				fields : ["advantageBusiRate"],
				title : false,
				grid : true,
				label : {
					renderer : Ext.util.Format.numberRenderer("0%"),
					font : "10px Arial"
				}
			}, {
				type : "Category",
				position : "bottom",
				fields : ["yearMonth"],
				title : false,
				label : {
					font : "11px Arial",
					renderer : function(j) {
						return j
					}
				}
			}],
			series : [{
				type : "line",
				axis : "left",
				xField : "yearMonth",
				yField : "advantageBusiRate",
				tips : {
					trackMouse : true,
					width : 110,
					height : 50,
					renderer : function(k, j) {
						this.setTitle(k.get("yearMonth") + i18n("i18n.memberView.advantageBusinessAccounted") + k.get("advantageBusiRate") + "%")
					}
				},
				style : {
					fill : "#00ff00",
					stroke : "#00ff00",
					"stroke-width" : 3
				},
				markerConfig : {
					type : "circle",
					size : 4,
					radius : 4,
					"stroke-width" : 0,
					fill : "#00ff00",
					stroke : "#00ff00"
				}
			}, {
				type : "line",
				axis : "left",
				xField : "yearMonth",
				yField : "motorRate",
				tips : {
					trackMouse : true,
					width : 110,
					height : 50,
					renderer : function(k, j) {
						this.setTitle(k.get("yearMonth") + i18n("i18n.memberView.automotiveAccountedFor") + k.get("motorRate") + "%")
					}
				},
				style : {
					fill : "#ff0000",
					stroke : "#ff0000",
					"stroke-width" : 3
				},
				markerConfig : {
					type : "circle",
					size : 4,
					radius : 4,
					"stroke-width" : 0,
					fill : "#ff0000",
					stroke : "#ff0000"
				}
			}]
		}
	});
	var d = Ext.create("widget.panel", {
		width : 600,
		height : 400,
		layout : "fit",
		items : {
			xtype : "chart",
			animate : false,
			store : DpUtil.getStore("VolumeStructureData", "OperationAnalysisModel", null, []),
			insetPadding : 30,
			axes : [{
				type : "Numeric",
				minimum : 0,
				position : "left",
				fields : ["claimsMoney"],
				title : false,
				grid : true,
				label : {
					renderer : Ext.util.Format.numberRenderer("0,0" + i18n("i18n.memberView.RMB")),
					font : "10px Arial"
				}
			}, {
				type : "Numeric",
				minimum : 0,
				maximum : 100,
				position : "right",
				fields : ["insuranceRate"],
				title : false,
				grid : true,
				label : {
					renderer : Ext.util.Format.numberRenderer("0%"),
					font : "10px Arial"
				}
			}, {
				type : "Category",
				position : "bottom",
				fields : ["yearMonth"],
				title : false,
				label : {
					font : "11px Arial",
					renderer : function(j) {
						return j
					}
				}
			}],
			series : [{
				type : "line",
				axis : "left",
				xField : "yearMonth",
				yField : "insurance",
				tips : {
					trackMouse : true,
					width : 110,
					height : 50,
					renderer : function(k, j) {
						this.setTitle(k.get("yearMonth") + i18n("i18n.memberView.insurance") + k.get("insurance") + i18n("i18n.memberView.RMB"))
					}
				},
				style : {
					fill : "#00ff00",
					stroke : "#00ff00",
					"stroke-width" : 3
				},
				markerConfig : {
					type : "circle",
					size : 4,
					radius : 4,
					"stroke-width" : 0,
					fill : "#00ff00",
					stroke : "#00ff00"
				}
			}, {
				type : "line",
				axis : "left",
				xField : "yearMonth",
				yField : "claimsMoney",
				tips : {
					trackMouse : true,
					width : 110,
					height : 50,
					renderer : function(k, j) {
						this.setTitle(k.get("yearMonth") + i18n("i18n.memberView.claimAmount") + k.get("claimsMoney") + i18n("i18n.memberView.RMB"))
					}
				},
				style : {
					fill : "#ff0000",
					stroke : "#ff0000",
					"stroke-width" : 3
				},
				markerConfig : {
					type : "circle",
					size : 4,
					radius : 4,
					"stroke-width" : 0,
					fill : "#ff0000",
					stroke : "#ff0000"
				}
			}, {
				type : "line",
				axis : "right",
				xField : "yearMonth",
				yField : "insuranceRate",
				tips : {
					trackMouse : true,
					width : 110,
					height : 50,
					renderer : function(k, j) {
						this.setTitle(k.get("yearMonth") + i18n("i18n.memberView.paulCampRatio") + k.get("insuranceRate") + "%")
					}
				},
				style : {
					fill : "#ff0000",
					stroke : "#ff0000",
					"stroke-width" : 3
				},
				markerConfig : {
					type : "circle",
					size : 4,
					radius : 4,
					"stroke-width" : 0,
					fill : "#ff0000",
					stroke : "#ff0000"
				}
			}]
		}
	});
	var c = Ext.create("widget.panel", {
		width : 600,
		height : 400,
		layout : "fit",
		items : {
			xtype : "chart",
			animate : false,
			store : DpUtil.getStore("VolumeStructureData", "OperationAnalysisModel", null, []),
			insetPadding : 30,
			axes : [{
				type : "Numeric",
				minimum : 0,
				position : "left",
				fields : ["fpackage"],
				title : false,
				grid : true,
				label : {
					renderer : Ext.util.Format.numberRenderer("0,0" + i18n("i18n.memberView.RMB")),
					font : "10px Arial"
				}
			}, {
				type : "Numeric",
				minimum : 0,
				maxmum : 100,
				position : "right",
				fields : ["packageRate"],
				title : false,
				grid : true,
				label : {
					renderer : Ext.util.Format.numberRenderer("0%"),
					font : "10px Arial"
				}
			}, {
				type : "Category",
				position : "bottom",
				fields : ["yearMonth"],
				title : false,
				label : {
					font : "11px Arial",
					renderer : function(j) {
						return j
					}
				}
			}],
			series : [{
				type : "line",
				axis : "left",
				xField : "yearMonth",
				yField : "fpackage",
				tips : {
					trackMouse : true,
					width : 110,
					height : 50,
					renderer : function(k, j) {
						this.setTitle(k.get("yearMonth") + i18n("i18n.memberView.packingCharges") + k.get("fpackage") + i18n("i18n.memberView.RMB"))
					}
				},
				style : {
					fill : "#00ff00",
					stroke : "#00ff00",
					"stroke-width" : 3
				},
				markerConfig : {
					type : "circle",
					size : 4,
					radius : 4,
					"stroke-width" : 0,
					fill : "#00ff00",
					stroke : "#00ff00"
				}
			}, {
				type : "line",
				axis : "right",
				xField : "yearMonth",
				yField : "packageRate",
				tips : {
					trackMouse : true,
					width : 110,
					height : 50,
					renderer : function(k, j) {
						this.setTitle(k.get("yearMonth") + i18n("i18n.memberView.packageBattalionRatio") + k.get("packageRate") + "%")
					}
				},
				style : {
					fill : "#ff0000",
					stroke : "#ff0000",
					"stroke-width" : 3
				},
				markerConfig : {
					type : "circle",
					size : 4,
					radius : 4,
					"stroke-width" : 0,
					fill : "#ff0000",
					stroke : "#ff0000"
				}
			}]
		}
	});
	var b = Ext.create("widget.panel", {
		width : 600,
		height : 400,
		layout : "fit",
		items : {
			xtype : "chart",
			animate : false,
			store : DpUtil.getStore("VolumeStructureData", "OperationAnalysisModel", null, []),
			insetPadding : 30,
			axes : [{
				type : "Numeric",
				minimum : 0,
				position : "left",
				fields : ["agentreceivePay"],
				title : false,
				grid : true,
				label : {
					renderer : Ext.util.Format.numberRenderer("0,0" + i18n("i18n.memberView.1000RMB")),
					font : "10px Arial"
				}
			}, {
				type : "Numeric",
				minimum : 0,
				maxmum : 100,
				position : "right",
				fields : ["agentreceivepayRate"],
				title : false,
				grid : true,
				label : {
					renderer : Ext.util.Format.numberRenderer("0%"),
					font : "10px Arial"
				}
			}, {
				type : "Category",
				position : "bottom",
				fields : ["yearMonth"],
				title : false,
				label : {
					font : "11px Arial",
					renderer : function(j) {
						return j
					}
				}
			}],
			series : [{
				type : "line",
				axis : "left",
				xField : "yearMonth",
				yField : "agentreceivePay",
				tips : {
					trackMouse : true,
					width : 110,
					height : 50,
					renderer : function(k, j) {
						this.setTitle(k.get("yearMonth") + i18n("i18n.memberView.theAmountOfPaymentCollection") + k.get("agentreceivePay") + i18n("i18n.memberView.1000RMB"))
					}
				},
				style : {
					fill : "#00ff00",
					stroke : "#00ff00",
					"stroke-width" : 3
				},
				markerConfig : {
					type : "circle",
					size : 4,
					radius : 4,
					"stroke-width" : 0,
					fill : "#00ff00",
					stroke : "#00ff00"
				}
			}, {
				type : "line",
				axis : "right",
				xField : "yearMonth",
				yField : "agentreceivepayRate",
				tips : {
					trackMouse : true,
					width : 110,
					height : 50,
					renderer : function(k, j) {
						this.setTitle(k.get("yearMonth") + i18n("i18n.memberView.paymentCollectionRatio") + k.get("agentreceivepayRate") + "%")
					}
				},
				style : {
					fill : "#ff0000",
					stroke : "#ff0000",
					"stroke-width" : 3
				},
				markerConfig : {
					type : "circle",
					size : 4,
					radius : 4,
					"stroke-width" : 0,
					fill : "#ff0000",
					stroke : "#ff0000"
				}
			}]
		}
	});
	var a = Ext.create("widget.panel", {
		width : 600,
		height : 400,
		layout : "fit",
		items : {
			xtype : "chart",
			animate : false,
			store : DpUtil.getStore("VolumeStructureData", "OperationAnalysisModel", null, []),
			insetPadding : 30,
			axes : [{
				type : "Numeric",
				minimum : 0,
				maxmum : 100,
				position : "left",
				fields : ["agentreceivepayRate"],
				title : false,
				grid : true,
				label : {
					renderer : Ext.util.Format.numberRenderer("0%"),
					font : "10px Arial"
				}
			}, {
				type : "Category",
				position : "bottom",
				fields : ["yearMonth"],
				title : false,
				label : {
					font : "11px Arial",
					renderer : function(j) {
						return j
					}
				}
			}],
			series : [{
				type : "line",
				axis : "left",
				xField : "yearMonth",
				yField : "wtvoteRate",
				tips : {
					trackMouse : true,
					width : 110,
					height : 50,
					renderer : function(k, j) {
						this.setTitle(k.get("yearMonth") + i18n("i18n.memberView.onlineBusinessAccounted") + k.get("wtvoteRate") + "%")
					}
				},
				style : {
					fill : "#00ff00",
					stroke : "#00ff00",
					"stroke-width" : 3
				},
				markerConfig : {
					type : "circle",
					size : 4,
					radius : 4,
					"stroke-width" : 0,
					fill : "#00ff00",
					stroke : "#00ff00"
				}
			}, {
				type : "line",
				axis : "left",
				xField : "yearMonth",
				yField : "callvoteRate",
				tips : {
					trackMouse : true,
					width : 110,
					height : 50,
					renderer : function(k, j) {
						this.setTitle(k.get("yearMonth") + i18n("i18n.memberView.callCentersAccounted") + k.get("callvoteRate") + "%")
					}
				},
				style : {
					fill : "#ff0000",
					stroke : "#ff0000",
					"stroke-width" : 3
				},
				markerConfig : {
					type : "circle",
					size : 4,
					radius : 4,
					"stroke-width" : 0,
					fill : "#ff0000",
					stroke : "#ff0000"
				}
			}, {
				type : "line",
				axis : "left",
				xField : "yearMonth",
				yField : "yytvoteRate",
				tips : {
					trackMouse : true,
					width : 110,
					height : 50,
					renderer : function(k, j) {
						this.setTitle(k.get("yearMonth") + i18n("i18n.memberView.theOperatingRoomAccounted") + k.get("yytvoteRate") + "%")
					}
				},
				style : {
					fill : "#ffff00",
					stroke : "#ffff00",
					"stroke-width" : 3
				},
				markerConfig : {
					type : "circle",
					size : 4,
					radius : 4,
					"stroke-width" : 0,
					fill : "#ffff00",
					stroke : "#ffff00"
				}
			}, {
				type : "line",
				axis : "left",
				xField : "yearMonth",
				yField : "othervoteRate",
				tips : {
					trackMouse : true,
					width : 110,
					height : 50,
					renderer : function(k, j) {
						this.setTitle(k.get("yearMonth") + i18n("i18n.memberView.third-partyChannelsAccountingFor") + k.get("othervoteRate") + "%")
					}
				},
				style : {
					fill : "#ff00ff",
					stroke : "#ff00ff",
					"stroke-width" : 3
				},
				markerConfig : {
					type : "circle",
					size : 4,
					radius : 4,
					"stroke-width" : 0,
					fill : "#ff00ff",
					stroke : "#ff00ff"
				}
			}]
		}
	});
	Ext.define("OperatingDecisionPanel", {			//10.2运营决策分析OperatingDecisionInfor
		extend : "TabContentPanel",
		title : i18n("i18n.memberView.analysisOfOperationalDecisions"),
		items : null,
		itemLeftGrid : null,
		itemCentGrid : null,
		itemRighGrid : null,
		initComponent : function() {
			var j = this;
			this.items = this.getItems();
			this.callParent()
		},
		layout : {
			type : "hbox",
			columns : 3,
			align : top
		},
		height : 440,
		width : 760,
		getItems : function() {
			this.itemLeftGrid = new OperatingDecisionL();
			this.itemCentGrid = new OperatingDecisionC();
			this.itemRighGrid = new OperatingDecisionR();
			return [this.itemLeftGrid, this.itemCentGrid, this.itemRighGrid]
		}
	});
	Ext.define("VolumeStructure", {					//10.3.1货量结构分析
		extend : "TabContentPanel",
		title : i18n("i18n.memberView.TheVolumeOfStructuralAnalysis"),
		items : null,
		width : 760,
		initComponent : function() {
			var j = this;
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			return [f]
		}
	});
	Ext.define("UseOfProduct", {					//10.3.2使用产品分析
		extend : "TabContentPanel",
		title : i18n("i18n.memberView.UsingTheProductAnalysis"),
		items : null,
		initComponent : function() {
			var j = this;
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			return [e]
		}
	});
	Ext.define("CampSecurity", {					//10.3.3保营比分析
		extend : "TabContentPanel",
		title : i18n("i18n.memberView.PaulCampThanAnalysis"),
		items : null,
		initComponent : function() {
			var j = this;
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			return [d]
		}
	});
	Ext.define("PackageRatio", {					//10.3.4包营比分析
		extend : "TabContentPanel",
		title : i18n("i18n.memberView.PackingCampThanAnalysis"),
		items : null,
		initComponent : function() {
			var j = this;
			j.items = j.getItems();
			this.callParent()
		},
		getItems : function() {
			return [c]
		}
	});
	Ext.define("CollectionOfMoney", {				//10.3.5代收货款分析
		extend : "TabContentPanel",
		title : i18n("i18n.memberView.CollectionOfMoneyAnalysis"),
		items : null,
		initComponent : function() {
			var j = this;
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			return [b]
		}
	});
	Ext.define("ChannelPreference", {				//10.3.6渠道偏好分析
		extend : "TabContentPanel",
		title : i18n("i18n.memberView.ChannelPreferenceAnalysis"),
		items : null,
		initComponent : function() {
			var j = this;
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			return [a]
		}
	});
	Ext.define("OperatingDecisionTabs", {			//10.3分析结果柱状图容器tab
		extend : "NormalTabPanel",
		width : 760,
		height : 450,
		border : true,
		flex : 1,
		itemVolumeStructure : null,
		itemUseOfProduct : null,
		itemCampSecurity : null,
		itemPackageRatio : null,
		itemCollectionOfMoney : null,
		itemChannelPreference : null,
		items : null,
		initComponent : function() {
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			this.itemVolumeStructure = new VolumeStructure();
			this.itemUseOfProduct = new UseOfProduct();
			this.itemCampSecurity = new CampSecurity();
			this.itemPackageRatio = new PackageRatio();
			this.itemCollectionOfMoney = new CollectionOfMoney();
			this.itemChannelPreference = new ChannelPreference();
			return [this.itemVolumeStructure, this.itemUseOfProduct, this.itemCampSecurity, this.itemPackageRatio, this.itemCollectionOfMoney, this.itemChannelPreference]
		}
	});
	Ext.define("InfoTabPanel", {
		extend : "NormalTabPanel",
		id : "infoTabPanelView",
		width : 700,
		height : 400,
		border : true,
		flex : 1,
		info0 : null,
		info1 : null,
		info2 : null,
		info3 : null,
		info4 : null,
		info5 : null,
		info6 : null,
		info7 : null,
		info8 : null,
		info9 : null,
		items : null,
		initComponent : function() {
			var j = this;
			j.info0 = new Information();
			j.info1 = new BasicInfor();
			j.info2 = new ContactInfor();
			j.info3 = new FinancialInfor();
			j.info4 = new ContInfor();
			j.info5 = new MaintenanceInfor();
			j.info6 = new OrderInfor();
			j.info7 = new IntegrationInfor();
			j.info8 = new ComplaintAndClaimInfor();
			j.info9 = new OperatingDecisionInfor();
			j.items = [j.info0, j.info1, j.info2, j.info3, j.info4, j.info5, j.info6, j.info8, j.info9], this.callParent()
		}
	});
	Ext.define("WrapperPanel", {				// 整合布局容器
		extend : "Ext.container.Viewport",
		record : null,
		layout : {
			type : "vbox",
			align : "stretch",
			padding : "5px 0 0"
		},
		initComponent : function() {
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			var j = new FormAndBtnPanelTest(), k = new InfoTabPanel();
			return [j, k]
		}
	});
	new WrapperPanel()
});