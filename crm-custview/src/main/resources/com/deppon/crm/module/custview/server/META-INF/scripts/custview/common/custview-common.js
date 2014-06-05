var MemberViewDataObj 	= (CONFIG.get("TEST")) ? new MemberViewDataTest() : new MemberViewData();
Ext.onReady(function() {
	Ext.form.Field.prototype.msgTarget = "side";
	
	//数据字典加载
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
        		'SECOND_TRADE',											//客户二级行业
        		'CUST_SOURCE',											//潜客来源
	          	'COMP_NATURE',										//公司性质
		        'CUST_POTENTIAL',									//客户潜力类型
          		'PREFERENCE_CHANNEL',								//来源渠道',偏好渠道
	          	'PREFERENCE_SERVICE',								//偏好服务
		        'FIRM_SIZE',										//上一年公司规模
		        'CUSTOMER_NATURE',									//客户属性
		        'CARDTYPECON',										//联系人证件类型
		        'CUST_CATEGORY',									//业务类别
		        'ACTIVITY_CATEGORY',									//业务类别
		        'BUSINESS_OPPORTUNITY_STATUS',	//商机状态
		        'BUSINESS_OPPORTUNITY_STEP',	//商机阶段
		        'TRANS_TYPE',					//运输类型
		        'COMPLAINT_BUSINESS_MODEL',		//业务模式
		        'TRANSPORT_TYPE',				//运输类型
		        'INVOICE_MARK',					//发票标记
		        'EXPRIVILEGE_TYPE',				//快递优惠类型
		        'ACTIVITY_TYPE',				//活动类型
		        'PREFERENTIAL_WAY',				//优惠方式
		        'LOSS_REASON',//流失原因
		        'PLAN_TYPE',				//计划类别	
		        'WARNSTATUS'											//预警状态
		        ];
	initDataDictionary(h);
	
	/*==================================================================================
	 * 合同信息页签
	 ===================================================================================*/
	//5.1.合同信息表格
	Ext.define("ContInforGrid", {				
		extend : "PopupGridPanel",
		autoScroll : true,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		id : "contInforGrid",
		listeners : {
			select : function(o, k, n, p) {
				var customerId = k.get("id");
				Ext.getCmp("contInforForm").getForm().reset();
				var param = {
					contractId : customerId
				};
				var fnSuccess = function(json) {
					if(!Ext.isEmpty(json.contractView)) {
						var contract = new ContractModel(json.contractView.contract);
						//1.部门
						contract.set(CONFIGNAME.get("contract_deptName"), json.contractView.contract.deptName);

						//2.合同状态
						contract.set(CONFIGNAME.get("contract_contractStatus"), DpUtil.changeDictionaryCodeToDescrip(contract.get(CONFIGNAME.get("contract_contractStatus")), DataDictionary.CONTACT_STATUS));

						//3.原合同序号/编号
						contract.set(CONFIGNAME.get("contract_beforeContractNum"), json.contractView.contract.beforeContractNum);
						
						//4.合同编号/序号
						contract.set(CONFIGNAME.get("contract_contractNum"), json.contractView.contract.contractNum);
						
						//5.合同主体	 即所属子公司
						contract.set(CONFIGNAME.get("contract_contractSubject"), json.contractView.contract.contractSubject);
					
						//6.客户全称
						contract.set(CONFIGNAME.get("contract_custCompany"), json.contractView.contract.custCompany);
						
						//7.近三个月发货金额
						contract.set(CONFIGNAME.get("contract_threeMonthAmount"),json.contractView.contract.arrAmount);
						
						//8.合同起始日期
						contract.set(CONFIGNAME.get("contract_contractBeginDate"), new Date(contract.get(CONFIGNAME.get("contract_contractBeginDate"))).format("yyyy-MM-dd"));
						
						//9.合同到期日期
						contract.set(CONFIGNAME.get("contract_contractendDate"), new Date(contract.get(CONFIGNAME.get("contract_contractendDate"))).format("yyyy-MM-dd"));
						
						//10.协议联系人
						contract.set(CONFIGNAME.get("contract_linkManName"), json.contractView.contract.linkManName);
						
						//11.联系人手机
						contract.set(CONFIGNAME.get("contract_linkManMobile"), json.contractView.contract.linkManMobile);
						
						//12.固定电话
						contract.set(CONFIGNAME.get("contract_linkManPhone"), json.contractView.contract.linkManPhone);

						//13.结款方式
						contract.set(CONFIGNAME.get("contract_payWay"), DpUtil.changeDictionaryCodeToDescrip(contract.get(CONFIGNAME.get("contract_payWay")), DataDictionary.RECKON_WAY));
						
						//13.快递结款方式
						contract.set('exPayWay', DpUtil.changeDictionaryCodeToDescrip(contract.get('exPayWay'), DataDictionary.RECKON_WAY));
						
						//14.结算限额
						contract.set(CONFIGNAME.get("contract_arrearaMount"), json.contractView.contract.arrearaMount);
						
						//15.结款日期
						var w = (contract.get(CONFIGNAME.get("contract_resultDates")) == 0 ? null : contract.get(CONFIGNAME.get("contract_resultDates")) + i18n("i18n.memberView.day"));
						contract.set(CONFIGNAME.get("contract_resultDates"), w);

						//16.快递优惠类型
						contract.set('exPreferentialType', DpUtil.changeDictionaryCodeToDescrip(contract.get('exPreferentialType'), DataDictionary.EXPRIVILEGE_TYPE));
						
						//16.优惠类型
						contract.set(CONFIGNAME.get("contract_preferentialType"), DpUtil.changeDictionaryCodeToDescrip(contract.get(CONFIGNAME.get("contract_preferentialType")), DataDictionary.PRIVILEGE_TYPE));
						
						//17.合同优惠信息
						if(!Ext.isEmpty(contract.get(CONFIGNAME.get("contract_preferential")))) {
							
							//合同优惠信息
							var preferential = contract.get(CONFIGNAME.get("contract_preferential"));
							
							var insuredPriceRate = (preferential.insuredPriceRate==1)?null:preferential.insuredPriceRate;
							var receivePriceRate	= (preferential.receivePriceRate==1)?null:preferential.receivePriceRate;
							var deliveryPriceRate	= (preferential.deliveryPriceRate==1)?null:preferential.deliveryPriceRate;
							var chargeRebate		= (preferential.chargeRebate==1)?null:preferential.chargeRebate;
							var agentgathRate		= (preferential.agentgathRate==1)?null:preferential.agentgathRate;
							
							//17.1保价费率
							contract.set(CONFIGNAME.get("contract_insuredPriceRate"), insuredPriceRate);
							
							//17.2.接货费率
							contract.set(CONFIGNAME.get("contract_receivePriceRate"), receivePriceRate);
							
							//17.3.送货费率
							contract.set(CONFIGNAME.get("contract_deliveryPriceRate"), deliveryPriceRate);
							
							//17.4.运费折扣
							contract.set(CONFIGNAME.get("contract_postageDiscounts"), chargeRebate);
							
							//17.5.代收费率
							contract.set(CONFIGNAME.get("contract_agentgathRate"), agentgathRate);
						};
						
						//17.快递合同优惠信息
						if(!Ext.isEmpty(contract.get('exPreferential'))) {
							
							//合同优惠信息
							var exPreferential = contract.get('exPreferential');
							
							var insuredPriceRate = (exPreferential.insuredPriceRate ==1)?null:exPreferential.insuredPriceRate;
							var receivePriceRate	= (exPreferential.receivePriceRate== 1)?null:exPreferential.receivePriceRate;
							var deliveryPriceRate	= (exPreferential.deliveryPriceRate== 1)?null:exPreferential.deliveryPriceRate;
							var chargeRebate		= (exPreferential.chargeRebate== 1)?null:exPreferential.chargeRebate;
							var agentgathRate		= (exPreferential.agentgathRate== 1)?null:exPreferential.agentgathRate;
							
							//17.1保价费率
							contract.set('exPreferential.insuredPriceRate', insuredPriceRate);
							
							//17.2.接货费率
							contract.set('exPreferential.receivePriceRate', receivePriceRate);
							
							//17.3.送货费率
							contract.set('exPreferential.deliveryPriceRate', deliveryPriceRate);
							
							//17.4.运费折扣
							contract.set('exPreferential.chargeRebate', chargeRebate);
							
							//17.5.代收费率
							contract.set('exPreferential.agentgathRate', agentgathRate);
						};
						//18.申请事由
						contract.set(CONFIGNAME.get("contract_application"), json.contractView.contract.application);

						//19.合同附件
						var y = "";
						var r = json.contractView.contract.fileInfoList;
						if(!Ext.isEmpty(r)) {
							for(var s = 0; s < r.length; s++) {
								y = y + '<a href="../common/downLoad.action?fileName=' + 
										r[s].fileName + "&inputPath=" + 
										r[s].savePath + '">' + 
										r[s].fileName + "</a><br />";
								Ext.getCmp("contract_Annex").setHeight(20*(s+1));
							}
						} else {
							//y = i18n("i18n.memberView.haveNothing");
						}
						Ext.getCmp("contract_Annex").setRawValue(y);
						
						//20.创建人
						contract.set(CONFIGNAME.get("contract_createUser"), json.contractView.createrName);
						
						//21.创建部门
						contract.set(CONFIGNAME.get("contract_createDeptName"), json.contractView.createDeptName);
						
						//22.创建时间
						contract.set(CONFIGNAME.get("contract_createDate"), json.contractView.contract.createDate);
						
						//23.最后修改人
						contract.set(CONFIGNAME.get("contract_lastModifyUser"),json.contractView.modifyUser);
						
						//24最后修改部门
						contract.set(CONFIGNAME.get("contract_lastModifyDept"),json.contractView.contract.lastModifyDept.deptName);
						
						//25.最后修改时间
						contract.set(CONFIGNAME.get("contract_lastModifyDate"),Ext.Date.format(new Date(json.contractView.contract.modifyDate),'Y-m-d H:i:s'));
						
						/****************************************/
						//26.注册资金
						contract.set(CONFIGNAME.get("contract_regicapital"), json.contractView.contract.regicapital);
						
						//27.对账日期
						var x = (contract.get(CONFIGNAME.get("contract_reconDate")) == 0 ? null : contract.get(CONFIGNAME.get("contract_reconDate")) + i18n("i18n.memberView.day"));
						contract.set(CONFIGNAME.get("contract_reconDate"), x);
						
						//28.开发票日期
						var	u = (contract.get(CONFIGNAME.get("contract_invoicDate")) == 0 ? null : contract.get(CONFIGNAME.get("contract_invoicDate")) + i18n("i18n.memberView.day"));
						contract.set(CONFIGNAME.get("contract_invoicDate"), u);
						
						/*
						 * 新增字段  2014-04  360优化
						 */
						var contract_temp = json.contractView.contract;
						//是否异地调货
						contract.set('ifForeignGoods',DpUtil.changeTrueAndFalse(contract.get('ifForeignGoods')));
//						催款部门
						contract.set('dunningDeptName',contract.get('dunningDeptName'));
//						//签署合同公司
						contract.set('signCompany',contract.get('signCompany'));
//						发票标记
						contract.set('invoiceType',DpUtil.changeDictionaryCodeToDescrip(contract.get('invoiceType'), DataDictionary.INVOICE_MARK));//
						//价格版本
						contract.set('priceVersionDate',Ext.isEmpty(contract.get('priceVersionDate'))?null:Ext.Date.format(new Date(contract.get('priceVersionDate')),'Y-m-d H:i:s'));
						if(!Ext.isEmpty(contract_temp.preferential)){
							//接货费率
							contract.set('preferential.receivePriceRate',Ext.isEmpty(contract_temp.preferential.receivePriceRate)?null:contract_temp.preferential.receivePriceRate);
							//折扣更新时间
							contract.set('preferential.updateDate',Ext.isEmpty(contract_temp.preferential.modifyDate)?null:Ext.Date.format(new Date(contract_temp.preferential.modifyDate),'Y-m-d H:i'));
						}
						if(!Ext.isEmpty(contract_temp.exPreferential)){
							//快递折扣更新时间
							contract.set('exPreferential.updateDate',Ext.isEmpty(contract_temp.exPreferential.modifyDate)?null:Ext.Date.format(new Date(contract_temp.exPreferential.modifyDate),'Y-m-d H:i'));
						}
						//快递价格版本
						contract.set('exPriceVersionDate',Ext.isEmpty(contract.get('exPriceVersionDate'))?null:Ext.Date.format(new Date(contract.get('exPriceVersionDate')),'Y-m-d H:i:s'));
						
						Ext.getCmp("contInforForm").getForm().loadRecord(contract);//填充form表单
						
					}
				};
				var fnFail = function(r) {
					if(r)MessageUtil.showErrorMes(r.message)
				};
				MemberViewDataObj.searchContractByContractId(param, fnSuccess, fnFail)
			}
		},
		selModel : Ext.create("Ext.selection.CheckboxModel", {
			mode : "SINGLE"
		}),
		store : DpUtil.getStore("ContInforStoreId", "ReturnVisit", null, []),
		columns : [{
			//合同 编号/序号
			header : i18n("i18n.memberView.auditNumber"),
			dataIndex : CONFIGNAME.get("contract_contractNum"),
			flex : 1
		}, {		
			//所属部门'
			header:i18n('i18n.memberView.blongDept'),
			dataIndex : CONFIGNAME.get("contract_deptName"),
			flex:1
		}, {
			//优惠类型
			header : i18n("i18n.memberView.preferentialType"),
			dataIndex : CONFIGNAME.get("contract_preferentialType"),
			flex : 1,
			renderer : function(value) {
				return DpUtil.changeDictionaryCodeToDescrip(value, DataDictionary.PRIVILEGE_TYPE)
			}
		}, {
			//结款方式
			header : i18n("i18n.memberView.termsOfPayment"),
			dataIndex : CONFIGNAME.get("contract_payWay"),
			flex : 1,
			renderer : function(value) {
				return DpUtil.changeDictionaryCodeToDescrip(value, DataDictionary.RECKON_WAY)
			}
		},{
			//结算限额
			header:i18n('i18n.memberView.monthlyAmount'),
			dataIndex:CONFIGNAME.get("contract_arrearaMount"),
			flex:1
		},{
			//生效日期
			header : i18n("i18n.memberView.effectiveDate"),
			dataIndex : CONFIGNAME.get("contract_contractBeginDate"),
			flex : 1,
			renderer : function(value) {
				return DpUtil.renderDate(value);
			}
		}, {
			//失效日期
			header : i18n("i18n.memberView.expirationDate"),
			dataIndex : CONFIGNAME.get("contract_contractendDate"),
			flex : 1,
			renderer : function(value) {
				return DpUtil.renderDate(value);
			}
		}],
		height : 200
	});
	
	
/*******************************合同信息form**************20121025********************************************************/
	
	Ext.define("ContInforForm", {				//5.2.合同信息form
		extend : "TitleFormPanel",
		id : "contInforForm",
		items : null,
		autoScroll:true,
		initComponent : function() {
			this.items = this.getItems();
			this.callParent()
		},
		getItems : function() {
			var me = this;
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
					labelWidth : 100,
					width : 250
				},
				items : [{
					fieldLabel : i18n("i18n.memberView.deptName"),				//1.部门名称
					name : CONFIGNAME.get("contract_deptName")
				},{
					fieldLabel : i18n("i18n.memberView.contractStatus"),		//2.合同状态
					name : CONFIGNAME.get("contract_contractStatus")
				},  {
					fieldLabel : i18n("i18n.memberView.beforeContractNum"),		//3.原合同序号/编号
					name : CONFIGNAME.get("contract_beforeContractNum")
				}, {
					fieldLabel : i18n("i18n.memberView.auditNumber"),			//4.合同序号/编号
					name : CONFIGNAME.get("contract_contractNum")
				}, {
					fieldLabel : i18n("i18n.memberView.belongChildCompany"),	//5.所属子公司，即合同主体
					name : CONFIGNAME.get("contract_contractSubject")
				}, {
					fieldLabel : i18n("i18n.memberView.custCompany"),			//6.客户全称
					name : CONFIGNAME.get("contract_custCompany")
				}, {
					fieldLabel : i18n("i18n.memberView.contractStartDate"),		//8.合同起始日期
					name : CONFIGNAME.get("contract_contractBeginDate")
				}, {
					fieldLabel : i18n("i18n.memberView.contractEndDate"),		//9.合同到期日期
					name : CONFIGNAME.get("contract_contractendDate")
				}, {
					fieldLabel : i18n("i18n.memberView.protocol"),				//10.协议联系人
					name : CONFIGNAME.get("contract_linkManName")
				}, {
					fieldLabel : i18n("i18n.memberView.contactMobilePhone"),	//11.联系人手机
					name : CONFIGNAME.get("contract_linkManMobile")
				}, {
					fieldLabel : i18n("i18n.memberView.telephoneNumber"),		//12.固定电话
					name : CONFIGNAME.get("contract_linkManPhone")
				}, {
					fieldLabel : i18n("i18n.memberView.monthlyAmount"),			//14.结算限额
					name : CONFIGNAME.get("contract_arrearaMount")				
				}, {
					fieldLabel : i18n("i18n.memberView.dateOfLoan"),			//15.结款日期
					name : CONFIGNAME.get("contract_resultDates")
				},{
					fieldLabel : i18n("i18n.memberView.debtDays"),		//20.合同月结天数
					name : CONFIGNAME.get("contract_debtDays")
				}, {
					fieldLabel :i18n('i18n.custview.ifForeignGoods'),// '是否异地调货'
					name : 'ifForeignGoods'
				}, {
					fieldLabel : i18n('i18n.custview.dunningDeptName'),//'催款部门',		
					name : 'dunningDeptName'
				},{
					fieldLabel : i18n('i18n.custview.signCompany'),//'签署合同公司'
					name : 'signCompany'
				}, {
					fieldLabel : i18n('i18n.custview.invoiceType'),//'发票标记',		
					name : 'invoiceType'
				},{
					fieldLabel:i18n('i18n.custview.contractLTT'),//'零担合同',
					xtype:'displayfield',
					colspan:3,
					style:'font-size:22px;font-weight:bolder;'
				},{
					fieldLabel : i18n('i18n.memberView.threeMonthAmount'),		//7.近三个月发货金额
					name : CONFIGNAME.get("contract_threeMonthAmount"),
					labelWidth:100
				},{
					fieldLabel : i18n("i18n.memberView.termsOfPayment"),		//13:结款方式
					name : CONFIGNAME.get("contract_payWay")
				}, {
					fieldLabel : i18n("i18n.memberView.preferentialType"),		//16.优惠类型
					name : CONFIGNAME.get("contract_preferentialType")
				}, {
					fieldLabel : i18n("i18n.memberView.postageDiscounts"),	//17.运费折扣
					name : CONFIGNAME.get("contract_postageDiscounts")	
				}, {
					fieldLabel : i18n("i18n.memberView.agentgathRate"),		//18.代收费率折扣
					name : CONFIGNAME.get("contract_agentgathRate")						
				}, {
					fieldLabel : i18n("i18n.memberView.chargeRate"),		//19.保价费率折扣
					name : CONFIGNAME.get("contract_insuredPriceRate")
				},{
					fieldLabel : i18n('i18n.custview.preferential.receivePriceRate'),//'接货费',
					name : 'preferential.receivePriceRate'
				},{
					fieldLabel : i18n('i18n.custview.priceVersionDate'),//'价格版本',
					name : 'priceVersionDate'
				},{
					fieldLabel : i18n('i18n.custview.preferential.updateDate'),//'折扣更新时间',
					name : 'preferential.updateDate'
				}, {
					fieldLabel:i18n('i18n.custview.contractExpress'),//'快递合同',
					xtype:'displayfield',
					colspan:3,
					style:'font-size:22px;font-weight:bolder;'
				},{
					fieldLabel : i18n('i18n.memberView.threeMonthAmount'),		//7.近三个月发货金额
					name : 'exArrAmount',
					labelWidth:100
				},{
					fieldLabel : i18n("i18n.memberView.termsOfPayment"),		//13:结款方式
					name : 'exPayWay'
				}, {
					fieldLabel : i18n("i18n.memberView.preferentialType"),		//16.优惠类型
					name : 'exPreferentialType'
				}, {
					fieldLabel : i18n("i18n.memberView.postageDiscounts"),	//17.运费折扣
					name : 'exPreferential.chargeRebate'
				}, {
					fieldLabel : i18n('i18n.custview.exPreferential.agentgathRate'),//'即日退代收折扣',
					name : 'exPreferential.agentgathRate'				
				}, {
					fieldLabel : i18n("i18n.memberView.chargeRate"),		//19.保价费率折扣
					name : 'exPreferential.insuredPriceRate'
				},{
					fieldLabel : i18n('i18n.custview.exPriceVersionDate'),//'价格版本',
					name : 'exPriceVersionDate'
				},{
					fieldLabel : i18n('i18n.custview.exPreferential.updateDate'),//'折扣更新时间',
					name : 'exPreferential.updateDate',
					colspan:2
				},{
					
					fieldLabel : i18n("i18n.memberView.applicationReasons"),	//申请事由	-大的
					xtype : "textarea",
					width : 630,
					colspan : 3,
					margin:'20 0 0 0',
					name : CONFIGNAME.get("contract_application")
				}, {
					xtype : "displayfield",
					fieldLabel : i18n("i18n.memberView.contractAnnex"),			//合同附件
					id : "contract_Annex",
					style:{
	 					'text-align':'left'
	 				},
					width : 620,
					height : 20,
					colspan : 3
				}, {
					fieldLabel : i18n("i18n.memberView.createPeople"),			//创建人
					name : CONFIGNAME.get("contract_createUser")
				}, {
					fieldLabel : i18n("i18n.memberView.createBranch"),			//创建部门
					name : CONFIGNAME.get("contract_createDeptName")
				}, {
					fieldLabel : i18n("i18n.memberView.createTime"),			//创建时间
					name : CONFIGNAME.get("contract_createDate")
				}, {
					fieldLabel : i18n('i18n.memberView.lastModifyPeople'),		//'最后修改人',
					name : CONFIGNAME.get("contract_lastModifyUser")
				}, {
					fieldLabel : i18n('i18n.memberView.lastModifyDept'),		//'最后修改部门',
					name : CONFIGNAME.get("contract_lastModifyDept")
				}, {
					fieldLabel :i18n('i18n.memberView.lastModifyDate'),			//'最后修改时间',
					name : CONFIGNAME.get("contract_lastModifyDate")
				}]
			}]
		}
	});
	
	/*===============================================================================
	 * 	信用信息页签formPanel
	 ================================================================================*/
	Ext.define('Crm.custview.CreditInformationForm',{
		extend:'TitleFormPanel',
		items:null,
		initComponent:function(){
			var me = this;
			this.items = this.getItems();
			this.callParent()
		},
		getItems:function(){
			return [{
				xtype:'basicfiledset',
				title:i18n('i18n.custview.creditInfo'),//'信用信息',
				layout:{
					type:'table',
					columns:3
				},
				defaults:{
					readOnly : true,
					cls : "readonly",
					labelWidth : 120,
					width : 210
				},
				defaultType:'textfield',
				items:[{
					fieldLabel:i18n('i18n.custview.overdraftCreditAmount')//'临欠信用额度'
					,name:'overdraftCreditAmount'
				},{
					fieldLabel:i18n('i18n.custview.overdrftRecivableAmount')//'临欠当期应收金额'
					,name:'overdrftRecivableAmount'
				},{
					fieldLabel:i18n('i18n.custview.overdraftCreditBalanceAmount')//'临欠信用额度余额'
					,name:'overdraftCreditBalanceAmount'
				},{
					fieldLabel:i18n('i18n.custview.monthCreditAmount')//'月结信用额度'
					,name:'monthCreditAmount'
				},{
					fieldLabel:i18n('i18n.custview.lttRecivableAmount')//'月结当期应收金额'
					,name:'lttRecivableAmount'
				},{
					fieldLabel:i18n('i18n.custview.monthCreditBalanceAmount')//'月结信用额度余额'
					,name:'monthCreditBalanceAmount'
				},{
					fieldLabel:i18n('i18n.custview.totalReceivableAmount')//'应收总额'
					,name:'totalReceivableAmount'
				},{
					fieldLabel:i18n('i18n.custview.creditTimes')//'信用预警次数'
					,name:'creditTimes'
				},{
					fieldLabel:i18n('i18n.custview.lastWarnTime')//'最后一次预警时间'
					,name:'lastWarnTime'
				},{
					fieldLabel:i18n('i18n.custview.isPoorCredit')//'是否信用较差客户'
					,name:'isPoorCredit'
				}]
			}]
		}
	});
	
	/*=================================================================================
	 * 维护页签内容
	 ==================================================================================*/
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
			header:'计划类别',
			dataIndex :'projectType',
			renderer:function(value){
				return DpUtil.changeDictionaryCodeToDescrip(value, DataDictionary.PLAN_TYPE)
			},
			flex:1
		},{
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
			renderer : function(value) {
				return DpUtil.renderDate(value);
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
			renderer : function(value) {
				return DpUtil.renderDate(value)
			}
		}],
		flex : 1
	});
	
	Ext.define("Crm.custview.LoseWarningGrid", {				//6.3 流失预警grid
		extend : "PopupGridPanel",
		id:'Crm.custview.LoseWarningId',
		autoScroll : true,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		title : i18n('i18n.custview.LoseWarning'),//'流失预警',
		store :Ext.create('Crm.custview.LoseWarningStore',{}),
		columns : [
		    new Ext.grid.RowNumberer()
		, {
			header : i18n('i18n.custview.warnStatus'),//'预警状态',
			dataIndex : 'warnStatus',
			flex : 1
		}, {
			header : i18n('i18n.custview.preDeliverytendTime'),//'预发货开始时间',
			dataIndex : 'preDeliverytendTime',
			flex : 1,
			renderer : function(value) {
				return DpUtil.renderDate(value);
			}
		}, {
			header : i18n('i18n.custview.preDeliverytBiegnTime'),//'预发货结束时间',
			dataIndex : 'preDeliverytBiegnTime',
			flex : 1,
			renderer : function(value) {
				return DpUtil.renderDate(value);
			}
		}, {
			header : i18n('i18n.custview.isSeason'),//'是否为季节性',
			dataIndex : 'isSeason',
			flex : 1,
			renderer:function(value){
				return DpUtil.changeTrueAndFalse(value)
			}
		}, {
			header : i18n('i18n.custview.lostCause'),//'流失原因',
			dataIndex : 'lostCause',
			renderer:function(value){
				return DpUtil.changeDictionaryCodeToDescrip(value, DataDictionary.LOSS_REASON)
			},
			flex : 1
		}, {
			header : i18n('i18n.custview.createTime'),//'预警时间',
			dataIndex : 'createTime',
			flex : 1,
			renderer : function(value) {
				return DpUtil.renderDate(value);
			}
		}, {
			header : i18n('i18n.custview.createUserName'),//'执行人',
			dataIndex : 'executor',
			flex : 1
		}, {
			header : i18n('i18n.custview.warnStatusChangeTime'),//'变更时间',
			dataIndex : 'warnStatusChangeTime',
			flex : 1,
			renderer : function(value) {
				return DpUtil.renderDate(value);
			}
		}, {
			header : i18n('i18n.custview.lostCustRemark'),//'备注',
			dataIndex : 'lostCustRemark',
			flex : 1
		}],
		flex : 1
	});
	
	/*=================================================================================
	 * 订单信息页签内容
	 ==================================================================================*/
	Ext.define("OrderInforForm", {				// 7.1.订单信息查询表单
		extend : "BasicHboxFormPanel",
		id : "OrderInforFormId",
		border:true,
		height : 42,
		width : 810,
		items : null,
		fbar : null,
		initComponent : function() {
			var j = this;
			j.items = j.getItems();
			this.callParent()
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
		autoScroll : false,
//				flex : 1,
		height : 545,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		store : MemberViewDataObj.searchOrderListByCustNum(),
		columns : [new Ext.grid.RowNumberer(), {
			header : i18n("i18n.memberView.orderNum"),
			dataIndex : 'orderNumber',
			width:120
		}, {
			header : i18n("i18n.memberView.waybillNumber"),
			dataIndex : 'waybillNumber',
			width:120
		}, {
			header:'运输方式',
			dataIndex:'transportMode',
			renderer:function(value){
				return DpUtil.changeDictionaryCodeToDescrip(value, DataDictionary.TRANS_TYPE)
			},
			width:120
		}, {
			header : i18n("i18n.memberView.orderSource"),
			dataIndex : 'resource',
			width:150,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.ORDER_SOURCE)
			}
		}, {
			header : i18n("i18n.memberView.consignor"),
			dataIndex : 'contactName',
			width:120
		}, {
			header : i18n("i18n.memberView.goodsName"),
			dataIndex : 'goodsName',
			width:150
		}, {
			header : i18n("i18n.memberView.theNumberOf"),
			dataIndex : 'goodsNumber',
			width:80,
			renderer : function(j) {
				return j + i18n("i18n.memberView.parts")
			}
		}, {
			header : i18n("i18n.memberView.weight"),
			dataIndex : 'totalWeight',
			width:80,
			renderer : function(j) {
				return j + i18n("i18n.memberView.kg")
			}
		}, {
			header : i18n("i18n.memberView.volume"),
			dataIndex : 'totalVolume',
			width:80,
			renderer : function(j) {
				return j + i18n("i18n.memberView.ccubicMetre")
			}
		}, {
			header : i18n("i18n.memberView.orderStatus"),
			dataIndex : 'orderStatus',
			width:60,
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j, DataDictionary.ORDER_STATUS)
			}
		}, {
			header : i18n("i18n.memberView.startStation"),
			dataIndex : 'startStationName',
			width:150
		}, {
			header : i18n("i18n.memberView.processStation"),
			dataIndex : 'acceptDeptName',
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
		}//,
//				height : 200
	});	
	
	/*================================================================================
	 * 运单信息
	 =================================================================================*/
	Ext.define("Crm.custview.WaybillInforForm", {				// 11.1.运单信息查询表单
		extend : "BasicHboxFormPanel",
		id : "crm.custview.waybillInforFormId",
		height : 42,
		width : 800,
		items : null,
		fbar : null,
		initComponent : function() {
			var j = this;
			j.items = j.getItems();
			this.callParent()
		},
		getItems : function() {
			var j = this;
			var date = new Date();
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
					id : "searchWaybillDateFrom",
					maxValue:new Date(),
					xtype : "datefield",
					allowBlank:false,
					editable : false
				}, {
					fieldLabel : i18n("i18n.memberView.to"),
					id : "searchWaybillDateTo",
					maxValue:new Date(),
					xtype : "datefield",
					allowBlank:false,
					editable : false
				}, {
					xtype : "btnpanel",
					width : 100,
					items : [{
						xtype : "button",
						id	:"crm.custview.waybillInforForm.searchId",
						text : i18n("i18n.memberView.search"),
						defaults : {
							margins : "0 0 5 0"
						},
						handler : function() {
							var me = this;
							me.setDisabled(true);
							j.searchWaybillInfo();
							setTimeout(function(){
								me.setDisabled(false);
							},3000);
						}
					}]
				}]
			}]
		}
	});
	
	Ext.define("Crm.custview.WaybillInforGrid", {					// 11.2	运单信息表格
		id : "crm.custview.waybillInforGrid",
		extend : "PopupGridPanel",
		autoScroll : true,
		flex : 1,
		sortableColumns : false,
		enableColumnHide : false,
		enableColumnMove : false,
		store:MemberViewData.prototype.searchWaybillListByCustNum(),
		columns : [new Ext.grid.RowNumberer(), {
			header : i18n('i18n.custview.waybillNum'),//'单号',
			dataIndex : 'waybillNum',
			// flex : 1
			width:120
		}, {
			header : i18n('i18n.custview.consigneeNumber'),//'收货客户名称',
			dataIndex : 'consigneeNumber',//
			// flex : 1
			width:120
		}, {
			header : i18n('i18n.custview.consigneeMobile'),//'收货人手机',
			dataIndex : 'consigneeMobile',
			// flex : 1
			width:120
		}, {
			header :i18n('i18n.custview.ladingStationName'),// '提货网点',
			dataIndex : 'ladingStationName',//暂时使用到达网点
			// flex : 1
			width:120
		}, {
			header : i18n('i18n.custview.consigneePhone'),//'收货人电话',
			dataIndex : 'consigneePhone',
			// flex : 1
			width:120
		}, {
			header :i18n('i18n.custview.consignee'),// '收货人联系人',
			dataIndex : 'consignee',
			// flex : 1
			width:120
		}, {
			header : i18n('i18n.custview.tranProperty'),//'运输性质',
			dataIndex : 'tranProperty',
			// flex : 1
			width:120
		}, {
			header : i18n('i18n.custview.consigneeAddress'),//'收货人地址',
			dataIndex :'consigneeAddress',
			// flex : 1
			width:120
		}, {
			header : i18n('i18n.custview.sendTime'),//'开单时间',
			dataIndex : 'sendTime',
			width:120,
			renderer:function(value){
				return  DpUtil.renderDate(value);
			}
		}],
		initComponent : function() {
			var me = this;
			me.bbar = me.getBBar();
			this.callParent()
		},
		getBBar : function() {
			var gird = this;
			return Ext.create("Ext.toolbar.Paging", {
				id : "waybillInforGrid_pagingToolbar",
				store : gird.store,
				displayMsg : i18n("i18n.memberView.displayMsg"),
				displayInfo : true,
				items : ["-", {
					text : i18n("i18n.memberView.page_count"),
					xtype : "tbtext"
				}, "20", {
					text : i18n("i18n.memberView.pageNumber"),
					xtype : "tbtext"
				}]
			})
		},
		height : 200
	});
	
	/*================================================================================
	 * 投诉理赔信息
	 =================================================================================*/
	Ext.define("ComplaintGrid", {				//9.1投诉表格
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
			header:i18n('i18n.custview.businessModel'),//'业务模式',
			dataIndex:'businessModel',
			renderer:function(value){
				return DpUtil.changeDictionaryCodeToDescrip(value, DataDictionary.COMPLAINT_BUSINESS_MODEL) 
			},
			flex:1
		},{
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
	Ext.define("ClaimsGrid", {					//9.2理赔信息表格
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
			header:i18n('i18n.custview.transPort'),//'运输类型',
			dataIndex:CONFIGNAME.get('recompense_waybill'),
			renderer : function(j) {
				return DpUtil.changeDictionaryCodeToDescrip(j.transType,DataDictionary.TRANSPORT_TYPE)
			},
			flex:1
		},{
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
});