Ext.onReady(function() {
			var MemberViewDataObj 	= (CONFIG.get("TEST")) ? new MemberViewDataTest() : new MemberViewData();
			Ext.Loader.setConfig({
				enabled : true
			});

	     	//综合信息的上部分（form）
	     	Ext.define('InformationTopPanel',{
	     		extend : "NoBorderFormPanel",
	     		alias : 'widget.informationTopPanel',
	     		layout:{
	     				type:'table',
	     				columns:4
	     		},
	     		defaultType:'dptextfield',
	     		defaults:{
	     			labelWidth:70,
	     			width:240,
	     			readOnly:true
	     		},
	     		style: {
	                 marginBottom: '10px'
	             },
		           	items: [{
		                fieldLabel: i18n('i18n.memberView.customerCode'),//'客户编码',
		                name: 'custNumber'
		            },{
		            	xtype:'displayfield',
		            	width:20,
		                name: 'isKeyCustomerSign',
		                margin:'0 0 5 -15',
						value:'<img src="../images/custview/VIP.png"/>',
		                hidden:true
		            },{
		                fieldLabel: i18n('i18n.memberView.blongDept'),//'所属部门',
		                name: 'deptName'
		            },{
		                fieldLabel: i18n('i18n.memberView.customerRating'),//'客户等级',
		                name: 'degree'
		            },{
		                fieldLabel: i18n('i18n.memberView.customerName'),//'客户名称',
		                name: 'custName',
		                colspan:2
		            },{
		                fieldLabel: i18n('i18n.memberView.customerIndustry'),//'客户行业',
		                name: 'tradeId'
		            },{
		                fieldLabel: i18n('i18n.memberView.customerType'),//'客户类型',
		                name: 'custType'
		            },{
		                fieldLabel: i18n('i18n.memberView.customerAttribute'),//'客户属性',
		                name: 'custNature',
		                colspan:2
		            },{
		                fieldLabel: i18n('i18n.memberView.taxRegistrationNumber'),//'税务登记号',
		                name: 'taxregNumber'
		            },{
		                fieldLabel: i18n('i18n.memberView.IDCardNumber'),//'证件号码',
		                name: 'idCard'
		            },{
		                fieldLabel: i18n('i18n.memberView.invoiceTo'),//'发票抬头',
		                name: 'billTitle',
		                colspan:2
		            },{
		                fieldLabel: i18n('i18n.memberView.isForce'),//'客户状态',
		                name: 'custStatus'
		            },{
		                fieldLabel: i18n('i18n.custview.custCategory'),//'客户类别',
		                name: 'custCategory'
		            },{
		            	fieldLabel: i18n('i18n.custview.isKeyCustomer'),//是否大客户
		                name: 'isKeyCustomer'
		            },{
		            	fieldLabel: 'id',
		            	hidden:true,
		            	name: 'id'
		            }] 
	     	});

	     //综合信息的中间部分（grid）
	     Ext.define('InformationCenterPanel',{
	     	 extend: 'PopupGridPanel',
	     	 alias : 'widget.informationCenterPanel',
	         height:90,
	         style: {
	             marginBottom: '10px'
	         },
	         store:DpUtil.getStore('','ContactModel',null,null),
	     	 //selModel :Ext.create('Ext.selection.CheckboxModel'),
	         columns: [{
	     				dataIndex: 'number',
	     				text:i18n("i18n.memberView.contactCoding")
	     			},{
	     				dataIndex: 'name',
	     				text:i18n("i18n.memberView.contactName")
	     			},{
	     				dataIndex: 'linkmanType',
	     				text:i18n("i18n.memberView.contactType"),
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
	     			},{
	     				dataIndex: 'sex',
	     				text:i18n("i18n.memberView.sex"),
	     				width:52,
	     				renderer : function(j) {
	     					return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.GENDER)
	     				}
	     			},{
	     				dataIndex: 'duty',
	     				text:i18n("i18n.memberView.post")
	     			},{
	     				dataIndex: 'mobilePhone',
	     				text:i18n("i18n.memberView.mobileNumber")
	     			},{
	     				dataIndex: 'telPhone',
	     				text:i18n("i18n.memberView.telephoneNumber")
	     			},{
	     				dataIndex: 'isMainLinkMan',
	     				text:i18n("i18n.memberView.whetherTheMainContact"),
	     				renderer : function(j) {
	     					return DpUtil.changeTrueAndFalse(j)
	     				}
	     			}]	
	     });
	     var g = Ext.create("widget.panel", {
	     	width:370,
			height:180,
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
	 				fields : ["arriveMoney","leaveMoney"],
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
	 					font : "7px Arial",
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
	 					anchor:'left',
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
	 					anchor:'left',
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
	 	
		var gExpress = Ext.create("widget.panel", {
	     	width:370,
			height:180,
	 		layout : "fit",
	 		items : {
	 			xtype : "chart",
	 			animate : false,
	 			store : DpUtil.getStore("VolumeAnalysisPanelExpData", "OperationAnalysisExpModel", null, []),
	 			insetPadding : 15,
	 			style : {
	 				position : "relative",
	 				background : "#eee"
	 			},
	 			axes : [{
	 				type : "Numeric",
	 				minimum : 0,
	 				position : "left",
	 				id:'crm.custview.gExpress.left',
	 				fields : ["arriveMoney","leaveMoney"],
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
	 					font : "7px Arial",
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
	 					anchor:'right',
	 					renderer : function(k, j) {
	 						this.setTitle(k.get("yearMonth") +"<br />"+ i18n("i18n.memberView.startingMonOfExp") +"<br />"+ k.get("leaveMoney"))
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
	 					anchor:'right',
	 					renderer : function(k, j) {
	 						this.setTitle(k.get("yearMonth") +"<br />"+ i18n("i18n.memberView.toReachTheMonOfExp") +"<br />"+ k.get("arriveMoney"))
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
	 	
	     Ext.create("Ext.data.Store", {
	 		storeId : "InformationTopRighCtnGridLStoreId",
	 		fields : ["onlyLine"],
	 		data : [{
	 			onlyLine : i18n("i18n.memberView.month")
	 		}, {
	 			onlyLine : i18n("i18n.custview.leaveMoney")
	 		}, {
	 			onlyLine : i18n("i18n.custview.arriveMoney")
	 		},{
	 			onlyLine : i18n("i18n.custview.leaveBill")
	 		},{
	 			onlyLine : i18n("i18n.custview.arriveBill")
	 		}]
	 	});
	     //中间grid的左边列
	     Ext.define("InformationTopRighCtnGridL", {
	    	id : "InformationTopRighCtnGridLID_simple",
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
	 		height : 128,
	 		width : 100,
//	 		border: 0,
//	 		style : {
//	 			border : "1px #999 solid",
//	 			borderLeft:0,
//	 			borderTop : 0,
//	 			borderBottom : 1,
//	 			padding : 1
//	 		},
//	 		margin:'30 0 0 0',
	 		hideHeaders : true
	 	});
	     //中间gird右边列（一月、二月、三月、、六月、、）
	     Ext.define("InformationTopRighCtnGrid", {				//1.1.2.2客户货量分析表格
	 		extend : "Ext.grid.Panel",
	 		cla : "grid_lackright",
	 		autoScroll : true,
	 		sortableColumns : false,
	 		enableColumnHide : false,
	 		enableColumnMove : false,
	 		hideHeaders : true,
	 		style : {
	 			border : "1px #999 solid",
	 			borderLeft:0,
	 			borderRight:0,
	 			borderTop : 0,
	 			borderBottom : 1,
	 			padding : 0
	 		},
	 		width : 302,
	 		height : 128,
	 		columns : [{
	 			header : i18n('i18n.custview.January'),//"1月",
	 			dataIndex : 'month1',
	 			width:65
	 		}, {
	 			header : i18n('i18n.custview.February'),//"2月",
	 			dataIndex : 'month2',
	 			width:65
	 		}, {
	 			header : i18n('i18n.custview.March'),//"3月",
	 			dataIndex : 'month3',
	 			width:65
	 		}, {
	 			header : i18n('i18n.custview.April'),//"4月",
	 			dataIndex : 'month4',
	 			width:65
	 		}, {
	 			header : i18n('i18n.custview.May'),//"5月",
	 			dataIndex : 'month5',
	 			width:65
	 		}, {
	 			header : i18n('i18n.custview.June'),//"6月",
	 			dataIndex : 'month6',
	 			width:65
	 		}, {
	 			header : i18n('i18n.custview.July'),//"7月",
	 			dataIndex : 'month7',
	 			width:65
	 		}, {
	 			header : i18n('i18n.custview.Aguest'),//"8月",
	 			dataIndex : 'month8',
	 			width:65
	 		}, {
	 			header : i18n('i18n.custview.September'),//"9月",
	 			dataIndex : 'month9',
	 			width:65
	 		}, {
	 			header : i18n('i18n.custview.October'),//"10月",
	 			dataIndex : 'month10',
	 			width:65
	 		}, {
	 			header : i18n('i18n.custview.November'),//"11月",
	 			dataIndex : 'month11',
	 			width:65
	 		}, {
	 			header : i18n('i18n.custview.December'),//"12月",
	 			dataIndex : 'month12',
	 			width:65
	 		}],
	 		initComponent : function() {
	 			this.callParent()
	 		}
	 	});
	     //中间的gird
	     Ext.define("InformationTopRighCtnGridPanel", {
	 		extend : "TabContentPanel",
	 		items : null,
	 		autoScroll : true,
	 		title : i18n("i18n.memberView.customerVolumeAnalysis"),
	 		frame:true,
	 		itemLeftGrid : null,
	 		itemRighGrid : null,
	 		itemCenterGrid:null,
	 		initComponent : function() {
	 			var j = this;
	 			this.items = this.getItems();
	 			this.callParent()
	 		},
	 		layout : {
	 			type : "table",
	 			columns : 3
	 		},
	 		getItems : function() {
	 			this.itemLeftGrid = new InformationTopRighCtnGridL();
	 			this.itemCenterGrid = new InformationTopRighCtnGrid({
	 				id : "InformationTopCenterCtnGridID_simple",
	 				margin:'0 5 0 0',
	 				store : DpUtil.getStore("itemCenterGridStore", "InformationTopRighCtnModel", null, [])
	 			});
	 			this.itemRighGrid = new InformationTopRighCtnGrid({
	 				id : "InformationTopRighCtnGridID_simple",
	 				margin:'0 5 0 0',
	 				store : DpUtil.getStore("itemRighGridStore", "InformationTopRighCtnModel", null, [])
	 			});
	 			return [{
	 				xtype:'displayfield',
	 				value:'',
	 				width:100
	 			},{
	 				xtype:'displayfield',
	 				html:'<span>零担</span>',
	 				style:{
	 					'font-weight':'bold',
	 					'text-align':'center'
	 				},
	 				width:305
	 			},{
	 				xtype:'displayfield',
	 				html:'<span>快递</span>',
	 				style:{
	 					'font-weight':'bold',
	 					'text-align':'center'
	 				},
	 				width:305
	 			},this.itemLeftGrid, this.itemCenterGrid,this.itemRighGrid]
	 		}
	 	});
	     //图片（折线图）
	     Ext.define("jg", {
	 		extend : "TabContentPanel"
	 	});
	     //综合信息的下部分（表和折线图“主Panel”---------）
	     Ext.define("InformationBottomPanel", {					//1.1.2
	 		extend : "Ext.panel.Panel",
//	 		width : 300,
//	 		height : 550,
	 		itemCtn : null,
	 		itemJg : null,
	 		itemBtm : null,
	 		items : null,
	 		initComponent : function() {
	 			this.items = this.getItems();
	 			this.callParent()
	 		},
	 		getItems : function() {
	 			this.itemCtn = new InformationTopRighCtnGridPanel();
	 			this.itemJg = new jg();
	 			return [this.itemCtn, this.itemJg,{
	 				xtype:'panel',
//	 				width:740,
	 				layout:{
	 					type:'table',
	 					columns:2
	 				},
	 				items:[g,gExpress]
	 			}]
	 		}
	 	});

	     //综合信息主panel(页签)
	     Ext.define('InformationPanel',{
	    	 	id:'simple360InformationPanel',
	    	 	title:i18n('i18n.memberView.information'),//'综合信息',
	    	    flex:1,
	     		extend : "TabContentPanel",
	     		itemInformationTopPanel : null,
	     		itemInformationCenterPanel : null,
	     		itemInformationBottomPanel : null,
	     		height:400,
	     		autoScroll:true,
	     		layout : {
	     			type : "vbox",
	     			align : "stretch"
	     		},
	     		items : null,
	     		parent:null,
	     		listeners:{
	     			afterrender:function(ths,obj){
	     				var custNumber=window.location.search.split("=")[1];
						var param = {"searchFastCondition":{"custNumber": custNumber}}
						var succFn = function(json) {
							if(Ext.isEmpty(json)) {
								return;
							}
							if(Ext.isEmpty(json.memberIntegratedInfoView)) {
								return;
							}
							if(!Ext.isEmpty(json.memberIntegratedInfoView.memberIntegral)) {
								var member=json.memberIntegratedInfoView.memberIntegral.member;
								var model = new InformationTopModel(member);
								model.set('idCard', member.mainContact.idCard);
								model.set('isKeyCustomer',DpUtil.changeTrueAndFalse(member.isKeyCustomer));
								model.set('custCategory',DpUtil.changeDictionaryCodeToDescrip(member.custCategory, DataDictionary.CUST_CATEGORY));
								
								var form = Ext.getCmp('simple360InformationPanel').itemInformationTopPanel.getForm();
								var i = 1;
								if(i == member.isKeyCustomer){
									form.findField('isKeyCustomerSign').setVisible(true);
									form.findField('custNumber').setWidth(215);
								}else{
									form.findField('isKeyCustomerSign').setVisible(false);
									form.findField('custNumber').setWidth(240);
								}
								ths.itemInformationTopPanel.loadRecord(model);
								Ext.data.StoreManager.lookup("VolumeAnalysisPanelData").loadData(json.memberIntegratedInfoView.operationAnalysisList);
								Ext.data.StoreManager.lookup("VolumeAnalysisPanelExpData").loadData(json.memberIntegratedInfoView.operationAnalysisExpList);
								Ext.getCmp("InformationTopCenterCtnGridID_simple").getStore().loadData(json.memberIntegratedInfoView.twelveMonthList);
								Ext.getCmp("InformationTopRighCtnGridID_simple").getStore().loadData(json.memberIntegratedInfoView.twelveMonthListExp);
								if(!Ext.isEmpty(member)&&!Ext.isEmpty(member.contactList)) {
									ths.itemInformationCenterPanel.getStore().loadData(member.contactList);
								}
							}
						}
						var failFn = function(json) {
							if(Ext.isEmpty(json)) {
								return;
							}
							MessageUtil.showErrorMes(json.message)
						}
						MemberViewDataObj.searchMemberntegrateInfo(param, succFn, failFn)
	     			}
	     		},
	     		initComponent:function(){
	     			var me = this;
	     			me.items = me.getItems();
	     			var custNumber=window.location.search.split("=")[1];
	     			this.callParent();
	     			me.itemInformationTopPanel.getForm().findField('custNumber').setValue(custNumber);
	     			
	     		},
	     		getItems : function() {
	     			this.itemInformationTopPanel = new InformationTopPanel();
	     			this.itemInformationCenterPanel = new InformationCenterPanel();
	     			this.itemInformationBottomPanel = new InformationBottomPanel();
	     			return [this.itemInformationTopPanel, this.itemInformationCenterPanel, this.itemInformationBottomPanel]
	     		}
	     	
	     	});
			//账号信息第一信息
			Ext.define("FinancialGridFir", {				//4.2财务信息form第一个
				extend : "SearchFormPanel",
				id : "financialGridFir_simple",
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
							labelWidth : 90,
							readOnly : true,
							cls : "readonly",
							width : 240
						},
						items : [{
							fieldLabel : i18n("i18n.memberView.bankAccount"),
							name : 'bank'
						}, {
							fieldLabel : i18n("i18n.memberView.branchName"),
							name : 'subBankname'
						}, {
							fieldLabel : i18n("i18n.memberView.ifADefaultAccount"),
							name : 'isdefaultaccount'
						}, {
							fieldLabel : i18n("i18n.memberView.bankAccountnumber"),
							name : 'bankAccount'
						}, {
							fieldLabel : i18n("i18n.memberView.developmentName"),
							name : 'countName'
						}, {
							fieldLabel : i18n("i18n.memberView.accountAndCustomerRelationship"),
							name : 'relation'
						}, {
							fieldLabel : i18n("i18n.memberView.openingProvince"),
							name : 'bankProvinceName'
						}, {
							fieldLabel : i18n("i18n.memberView.natureOfAccount"),
							name : 'accountNature'
						}, {
							fieldLabel : i18n("i18n.memberView.accountApplication"),
							name : 'accountUse'
						}, {
							fieldLabel : i18n("i18n.memberView.openCity"),
							name : 'bankCityName'
						}, {
							fieldLabel : i18n("i18n.memberView.financialContactName"),
							name : 'financeLinkman'
						}, {
							fieldLabel : i18n("i18n.memberView.mobileNumber"),
							name : 'linkManMobile'
						}, {
							fieldLabel : i18n("i18n.memberView.telephoneNumber"),
							name : 'linkManPhone'
						}]
					}]
				}
			});
			//账号信息第二项
			Ext.define("FinancialGridSec", {				//4.3财务信息第二个form
				extend : "SearchFormPanel",
				id : "financialGridSec_simple",
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
							labelWidth : 90,
							width : 240
						},
						items : [{
							fieldLabel : i18n("i18n.memberView.createPeople"),
							name : 'createUser'
						}, {
							fieldLabel : i18n("i18n.memberView.createBranch"),
							name : 'createDeptId'
						}, {
							fieldLabel : i18n("i18n.memberView.createTime"),
							name : 'createDate'
						}]
					}]
				}
			});
			//账户信息
			Ext.define("FinancialInfor", {					//4.财务信息FinancialInfor
				extend : "TabContentPanel",
				title : i18n("i18n.memberView.financialInformation"),
				height : 500,
				autoScroll : false,
				items : null,
				itemFinancialInforGrid : null,
				itemJg1 : null,
				itemFinancialGridFir : null,
				itemJg2 : null,
				itemFinancialGridSec : null,
				listeners : {
					beforeactivate : function(ths,obj) {
						var custId =Ext.getCmp('simple360InformationPanel').itemInformationTopPanel.getForm().findField('id').getValue();
						if(Ext.isEmpty(custId)) {
							MessageUtil.showMessage(i18n("i18n.memberView.pleaseInputCustNum"));
							return;
						}
						var param = {
							custId : custId
						};
						var secussFn = function(json) {
							var finGrid = ths.down('popupgridpanel');
							finGrid.getStore().removeAll();
							if(!Ext.isEmpty(json.accountList)) {
								finGrid.getStore().loadData(json.accountList);
							}
							finGrid.scrollByDeltaX(-1);//避免滚动条引起的错位
						};
						var failFn = function(json) {
							MessageUtil.showErrorMes(json.message);
						};
						MemberViewDataObj.searchAccountViewByCustNum(param, secussFn, failFn)
					}
				},
				initComponent : function() {
					var me = this;
					me.itemFinancialInforGrid = new FinancialInforGrid();
					me.itemJg1 = new jg();
					me.itemFinancialGridFir = new FinancialGridFir();
					me.itemJg2 = new jg();
					me.itemFinancialGridSec = new FinancialGridSec();
					me.items = [me.itemFinancialInforGrid, me.itemJg1,
					            me.itemFinancialGridFir, me.itemJg2, 
					            me.itemFinancialGridSec];
					this.callParent();
				}
			});
			
			//账号列表
			Ext.define("FinancialInforGrid", {
				//4.1财务信息grid
				extend : "PopupGridPanel",
				autoScroll : true,
				sortableColumns : false,
				enableColumnHide : false,
				enableColumnMove : false,
				height : 190,
				width : 738,
//				store : DpUtil.getStore("FinancialInforStoreId", "AccountModel", null, []),
				selModel : Ext.create("Ext.selection.CheckboxModel", {
					mode : "SINGLE"
				}),
				listeners : {
					select : function(n, k, m, o) {
						var j = function(q) {
							var r = new AccountModel(q.accountView.account);
							r.set('createUser', q.accountView.createrName);
							r.set('createDeptId', q.accountView.createDeptName);
							r.set('isdefaultaccount', DpUtil.changeTrueAndFalse(r.get('isdefaultaccount')));
							r.set('accountNature', DpUtil.changeDictionaryCodeToDescrip(r.get('accountNature'), DataDictionary.ACCOUNT_NATURE));
							r.set('accountUse', DpUtil.changeDictionaryCodeToDescrip(r.get('accountUse'), DataDictionary.ACCOUNT_USE));
							r.set('createDate', new Date(r.get('createDate')).format("yyyy-MM-dd"));
							r.set('modifyDate', new Date(r.get('modifyDate')).format("yyyy-MM-dd"));
							Ext.getCmp("financialGridFir_simple").getForm().loadRecord(r);
							Ext.getCmp("financialGridSec_simple").getForm().loadRecord(r);
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
				columns : [new Ext.grid.RowNumberer(), {
					header : i18n("i18n.memberView.bankAccount"),
					dataIndex : 'bank',
					width : 200
				}, {
					header : i18n("i18n.memberView.accountName"),
					dataIndex : 'countName',
					width : 90
				}, {
					header : i18n("i18n.memberView.bankAccountnumber"),
					dataIndex : 'bankAccount',
					width : 120
				}, {
					header : i18n("i18n.memberView.accountApplication"),
					dataIndex : 'accountUse',
					width : 120,
					renderer : function(j) {
						return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.ACCOUNT_USE)
					}
				}, {
					header : i18n("i18n.memberView.financialContactName"),
					dataIndex : 'financeLinkman',
					width : 100
				}, {
					header : i18n("i18n.memberView.mobileNumber"),
					dataIndex : 'linkManMobile',
					width : 100
				}, {
					header : i18n("i18n.memberView.telephoneNumber"),
					dataIndex : 'linkManPhone',
					width : 100
				}, {
					header : i18n("i18n.memberView.ifADefaultAccount"),
					dataIndex : 'isdefaultaccount',
					width : 90,
					renderer : function(j) {
						return DpUtil.changeTrueAndFalse(j)
					}
				}, {
					header : i18n("i18n.memberView.accountAndCustomerRelationship"),
					dataIndex : 'relation',
					width : 120
				}]
			});
			
	/*===================================================================
	 * 描述：新增信用信息页签
	 * 日期：2014-04-10
	 * 作者：邹明
	 * ==================================================================*/
	Ext.define('Crm.custview.CreditInformationSimple',{
		extend : "TabContentPanel",
		title : i18n('i18n.custview.creditInfo'),//'信用信息',
		height : 500,
		autoScroll : true,
		items:null,
		CreditInformationForm:null,
		getCreditInformationForm:function(CreditInformationForm){
			return this.CreditInformationForm;
		},
		listeners : {
			activate : function(l, m) {
				var custId =Ext.getCmp('simple360InformationPanel').itemInformationTopPanel.getForm().findField('id').getValue();
				var param = {
					custId : custId
				};
				var successFn = function(json) {
					if(!Ext.isEmpty(json) && !Ext.isEmpty(json.custCredit)){
						if(json.custCredit.isPoorCredit=='1'){
							json.custCredit.isPoorCredit=i18n('i18n.memberView.yes');
						}else if(json.custCredit.isPoorCredit=='0'){
							json.custCredit.isPoorCredit=i18n('i18n.memberView.no');
						}
						if(!Ext.isEmpty(json.custCredit.lastWarnTime)){
							json.custCredit.lastWarnTime = Ext.Date.format(new Date(json.custCredit.lastWarnTime),'Y-m-d H:i:s');
						}
						var custCreditModel = new Crm.custview.CustCreditModel(json.custCredit);
						l.getCreditInformationForm().getForm().loadRecord(custCreditModel);
					}
					
				};
				var failureFn = function(json) {
					MessageUtil.showErrorMes(json.message)
				};
				MemberViewDataObj.searchCustCredit(param, successFn, failureFn);
			}
		},
		initComponent : function() {
			this.CreditInformationForm = new Crm.custview.CreditInformationForm();
			this.items = this.getItems(this.CreditInformationForm);
			this.callParent()
		},
		getItems : function(item) {
			return [item]
		}
	});
	
	/*======================================================================================
	 * 描述：新增运单信息页签
	 * 日期：2014-04-02
	 * 作者：zm
	 * ====================================================================================*/
	Ext.define("Crm.custview.WaybillInforSimple", {					//11.运单信息waybillInfor
		extend : "TabContentPanel",
		title : i18n('i18n.custview.waybillInfo'),//'运单信息',
		height : 500,
		autoScroll : true,
		layout : {
			type : "vbox",
			align : "stretch"
		},
		items : null,
		itemWaybillInforForm : null,
		itemWaybillInforGrid : null,
		initComponent : function() {
			var j = this;
			this.items = this.getItems();
			Ext.getCmp('crm.custview.waybillInforGrid').getStore().on('beforeload',function(store,operation,obj){
				var from = Ext.getCmp("searchWaybillDateFrom").getValue();
				var to = Ext.getCmp("searchWaybillDateTo").getValue();
				if(Ext.isEmpty(from) ||Ext.isEmpty(to) ){
					MessageUtil.showMessage("您好，开始时间以及结束时间均不能为空");
					return;
				}
						
				if(from>to){
					MessageUtil.showMessage(i18n("i18n.memberView.timeLimitMessage"));
					return;
				}
				else if(DpUtil.isFindOutDeadline(from, to, 10)) {
					MessageUtil.showMessage(i18n("i18n.memberView.moreSearchDate_10"));
					return
				}
				
				var custNumber =Ext.getCmp('simple360InformationPanel').itemInformationTopPanel.getForm().findField('custNumber').getValue();
				Ext.apply(operation,{
					params:{
						custNum : custNumber,
						searchOrderDateFrom : from,
						searchOrderDateTo : to
					}
				})
			});
			this.callParent();
		},
		getItems : function() {
			this.itemWaybillInforForm = Ext.create('Crm.custview.WaybillInforForm',{
					searchWaybillInfo : function() {
						var l =Ext.getCmp('simple360InformationPanel').itemInformationTopPanel.getForm().findField('custNumber').getValue();
						
						if(!this.getForm().isValid()){
							MessageUtil.showMessage("您好，开始时间以及结束时间均不能为空");
							return;
						}
						if(Ext.isEmpty(l)) {
							MessageUtil.showMessage(i18n("i18n.memberView.pleaseInputCustNum"));
							return
						}
						var j = Ext.getCmp("searchWaybillDateFrom").getValue();
						var k = Ext.getCmp("searchWaybillDateTo").getValue();
						if(j>k){
							MessageUtil.showMessage(i18n("i18n.memberView.timeLimitMessage"));
							return;
						}
						else if(DpUtil.isFindOutDeadline(j, k, 10)) {
							MessageUtil.showMessage(i18n("i18n.memberView.moreSearchDate_10"));
							return
						}
						Ext.getCmp("waybillInforGrid_pagingToolbar").moveFirst()
					}		
			});
			this.itemWaybillInforGrid = new Crm.custview.WaybillInforGrid();
			return [this.itemWaybillInforForm, this.itemWaybillInforGrid]
		}
	});
	
	
	/*=================================================================================
	 * //5.合同信息ContInfor
	 =================================================================================*/
			Ext.define("ContInfor", {					
				extend : "TabContentPanel",
				title : i18n("i18n.memberView.contractInformation"),
//				layout:{
//					type:'vbox',
//					align:'stretch'
//				},
				autoScroll:true,
				itemContInforGrid : null,
				itemJg : null,
				itemContInforForm : null,
				items : null,
				listeners : {
					activate : function(ths, obj) {
						var custId =Ext.getCmp('simple360InformationPanel').itemInformationTopPanel.getForm().findField('id').getValue();
						if(Ext.isEmpty(custId)) {
							MessageUtil.showMessage(i18n("i18n.memberView.pleaseInputCustNum"));
							return;
						}
						var param = {
							custId : custId
						};
						var fnSucess = function(json) {
							ths.down('popupgridpanel').getStore().removeAll();
							if(!Ext.isEmpty(json.contractList)) {
								ths.down('popupgridpanel').getStore().loadData(json.contractList)
							}
						};
						var fnFail = function(q) {
							MessageUtil.showErrorMes(q.message);
						};
						MemberViewDataObj.searchContractViewByCustNum(param, fnSucess, fnFail)
					}
				},
				initComponent : function() {
					var me = this;
					me.items = me.getItems();
					me.callParent();
				},
				getItems : function() {
					this.itemContInforGrid = new ContInforGrid();
					this.itemJg = new jg();
					this.itemContInforForm = new ContInforForm();
					return [this.itemContInforGrid, this.itemJg, this.itemContInforForm]
				}
			});
		
			
			Ext.define("MaintenanceInfor", {				//6.维护信息MaintenanceInfor
				extend : "TabContentPanel",
				title : i18n("i18n.memberView.maintenanceInformation"),
				height : 700,
				autoScroll : false,
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
					activate : function(ths, obj) {
						var custId =Ext.getCmp('simple360InformationPanel').itemInformationTopPanel.getForm().findField('id').getValue();
						var custNumber =Ext.getCmp('simple360InformationPanel').itemInformationTopPanel.getForm().findField('custNumber').getValue();
	
						if(Ext.isEmpty(custId)) {
							MessageUtil.showMessage(i18n("i18n.memberView.pleaseInputCustNum"));
							return;
						}
						var param = {
							custId : custId
						};
						var secussFn = function(json) {
							ths.down('popupgridpanel').getStore().removeAll();
							ths.down('popupgridpanel').next('popupgridpanel').getStore().removeAll();
							if(!Ext.isEmpty(json.integratedCustDevView.planScheduleList)) {
								ths.down('popupgridpanel').getStore().loadData(json.integratedCustDevView.planScheduleList)
							}
							if(!Ext.isEmpty(json.integratedCustDevView.visitRecordList)) {
								ths.down('popupgridpanel').next('popupgridpanel').getStore().loadData(json.integratedCustDevView.visitRecordList)
							}
						};
						var failFn = function(json) {
							if(Ext.isEmpty(json)){
								MessageUtil.showErrorMes(json.message);
							}else{
								MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'))
							}
						};

						Ext.getCmp('Crm.custview.LoseWarningId').getStore().on('beforeload',function(store,op,obj){
							Ext.apply(op,{
								params:{'custNum': custNumber}
							});
						});
						Ext.getCmp('Crm.custview.LoseWarningId').getStore().load();
						MemberViewDataObj.searchIntegratedCustDevViewByCustNum(param, secussFn, failFn)
					}
				},
				getItems : function() {
					return [new PlanScheduleDetails(), new MaintenanceRecords(),new Crm.custview.LoseWarningGrid()]
				}
			});

	
			Ext.define("OrderInfor", {					//7.订单信息OrderInfor
				extend : "TabContentPanel",
				title : i18n("i18n.memberView.orderInfo"),
				height : 700,
				autoScroll : false,
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
						var custId =Ext.getCmp('simple360InformationPanel').itemInformationTopPanel.getForm().findField('id').getValue();
						var k = Ext.getCmp("searchOrderDateFrom").getValue();
						var n = Ext.getCmp("searchOrderDateTo").getValue();
						var p = {
							custId : custId,
							searchOrderDateFrom : k,
							searchOrderDateTo : n
						};
						Ext.apply(l, {
							params : p
						})
					});
					this.callParent();
				},
				getItems : function() {
					this.itemOrderInforForm = new OrderInforForm({
						searchOrder : function() {
							var custId =Ext.getCmp('simple360InformationPanel').itemInformationTopPanel.getForm().findField('id').getValue();
							if(Ext.isEmpty(custId)) {
								MessageUtil.showMessage(i18n("i18n.memberView.pleaseInputCustNum"));
								return;
							}
							var j = Ext.getCmp("searchOrderDateFrom").getValue();
							var k = Ext.getCmp("searchOrderDateTo").getValue();
							if(j>k){
								MessageUtil.showMessage(i18n("i18n.memberView.timeLimitMessage"));
								return;
							}
							else if(DpUtil.isFindOutDeadline(j, k, 90)) {
								MessageUtil.showMessage(i18n("i18n.memberView.moreSearchDate"));
								return;
							}
							Ext.getCmp("orderInforGrid_pagingToolbar").moveFirst()
						}
					});
					this.itemOrderInforGrid = new OrderInforGrid();
					return [this.itemOrderInforForm, this.itemOrderInforGrid]
				}
			});
			
			
			Ext.define("ComplaintAndClaimInfor", {			//9.投诉理赔信息ComplaintAndClaimInfor
				extend : "TabContentPanel",
				title : i18n("i18n.memberView.complaintClaimsInformation"),
				height : 650,
				autoScroll : false,
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
						var custNumber =Ext.getCmp('simple360InformationPanel').itemInformationTopPanel.getForm().findField('custNumber').getValue();
						var custId =Ext.getCmp('simple360InformationPanel').itemInformationTopPanel.getForm().findField('id').getValue();
						if(Ext.isEmpty(custNumber)) {
							MessageUtil.showMessage(i18n("i18n.memberView.pleaseInputCustNum"));
							return
						}
						var o = {
							custNum : custNumber,
							custId:custId
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
			
	/*======================================================================================
	 * 整体tabpanel
	 =======================================================================================*/
			Ext.define('EditTabPanel', {
				extend : 'NormalTabPanel',
				itemInformationPanel : null,
				height:700,
				items : null,
				parentWindow : null, // 父窗口
				items : [Ext.create('InformationPanel'),Ext.create('FinancialInfor'),Ext.create('Crm.custview.CreditInformationSimple'),
				         Ext.create('ContInfor'),Ext.create('MaintenanceInfor'),
				         Ext.create('OrderInfor'),Ext.create('ComplaintAndClaimInfor'),
				         Ext.create('Crm.custview.WaybillInforSimple')]
			});
			Ext.create('Ext.container.Viewport', {
				layout : 'fit',
				items : [Ext.create('EditTabPanel')]

			});
		});
 