/**
* @理赔打印
* @author  Rock
*/
var recompenseData = (CONFIG.get("TEST")) ? new RecompenseDataTestN():new RecompenseDataN(),
	printDatas = window.opener.printDatas,
	keys =[ 'DANGER_TYPE',
	        'RECOMPENSE_WAY',
	        'AWARD_TYPE',				//奖罚类型
            'MEMBER_GRADE',            	//客户等级
            'MESSAGE_REMIND',			//消息提醒方式
            'AWARD_TARGET_TYPE',		//奖罚对象类型
            'PAY_METHOD',				//付款方式
            'RECOMPENSE_TYPE',
            'RECOMPENSE_STATUS',
            'TRANSPORT_TYPE'];
	initDataDictionary(keys);
Ext.onReady(function() {
	Ext.form.Field.prototype.msgTarget='side';
	
	Ext.define('jg',{
		extend:'TabContentPanel',
		padding:'10 0 0 0'
	});
	
	Ext.define('DangerInfo',{								// 1.出险信息form
		extend:'TitleFormPanel',
		id:'dangerInfoForm',
		height:224,
		width:660,
		getItems:function(){
			var me = this;
			return [{xtype: 'basicfiledset',
			    title: i18n('i18n.recompense.dangerInfo'),
			    layout: {
			        type: 'table',
			        columns: 3
			    },
			    defaults:{
			    	labelSeparator:'',
			    	style:'margin-right:5px;',
			    	labelWidth:60,
			    	width:210
			    },
			    items:[{xtype: 'textfield',
			    	readOnly:true,
			    	cls:'readonly',
			    	name: CONFIGNAME.get('waybillNumber'),//凭证号
			    	emptyText:i18n('i18n.recompense.orderNum'),
			        fieldLabel:i18n('i18n.recompense.orderNumOrErroeNum*'),
				    colspan: 1
				},{
					xtype: 'textfield',
					name: CONFIGNAME.get('goodsName'),//货物名称
					colspan: 1,
			        fieldLabel:i18n('i18n.recompense.goodsName'),
			        cls:'readonly',
			        readOnly:true
				},{
					xtype: 'combo',//运输类型
					name: CONFIGNAME.get('transType'),
					colspan: 1,
					fieldLabel:i18n('i18n.recompense.transType'),
					cls:'readonly',
			        readOnly:true,
			        displayField:'codeDesc',
			        valueField:'code',
			        queryMode:'local',
			        store:DpUtil.getStore(CONFIGNAME.get('transType'),null,['code','codeDesc'],DataDictionary.TRANSPORT_TYPE)
			    },{
					xtype: 'textfield',
					colspan: 1,
					name: CONFIGNAME.get('packaging'),//包装
			        fieldLabel:i18n('i18n.recompense.packging'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'textfield',//保价人
					colspan: 1,
					name: CONFIGNAME.get('insured'),
			        fieldLabel:i18n('i18n.recompense.insureMan'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'textfield',//联系电话
					colspan: 1,
					name: CONFIGNAME.get('telephone'),
			        fieldLabel:i18n('i18n.recompense.connectMobilePhone'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'textfield',//s始发站
					colspan: 1,
					name: CONFIGNAME.get('startStation'),
			        fieldLabel:i18n('i18n.recompense.startStation'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'textfield',//目的站
					colspan: 1,
					name: CONFIGNAME.get('endStation'),
			        fieldLabel:i18n('i18n.recompense.endStation'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'datefield',//发货日期
					colspan: 1,
					format:'Y-m-d',
					editable:false,
					name: CONFIGNAME.get('sendDate'),
			        fieldLabel:i18n('i18n.recompense.sendGoodsTime'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'textfield',
					colspan: 1,
					name: CONFIGNAME.get('pwv'),//体件重
			        fieldLabel:i18n('i18n.recompense.numWeightVL'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'textfield',
					colspan: 1,
					name: CONFIGNAME.get('insurAmount'),//保价金额
			        fieldLabel:i18n('i18n.recompense.insureAmount'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'textfield',
					colspan: 1,
					name: CONFIGNAME.get('receiveDept'),//收货部门
			        fieldLabel:i18n('i18n.recompense.receiveDept'),
			        cls:'readonly',
			        readOnly:true
				  },{
					colspan: 1,
					readOnly:true,
					cls:'readonly',
					value:User.deptName,//报案
					name:CONFIGNAME.get('reportDeptName'),
			        fieldLabel:i18n('i18n.recompense.reportDept'),
			        xtype:'textfield'
				  },{
 					colspan: 1,
					value:User.empName,//报案人
					xtype:'textfield',
					name:CONFIGNAME.get('reportManName'),
			        fieldLabel:i18n('i18n.recompense.reportPerson'),
			        cls:'readonly',
			        readOnly:true
			    },{
					xtype: 'datefield',
					editable:false,
					readOnly:true,
					cls:'readonly',
					value:new Date(),//报案日期
					format:'Y-m-d',
					name: CONFIGNAME.get('reportDate'),
			        fieldLabel:i18n('i18n.recompense.reportTime')
				},{
					xtype: 'datefield',
					editable:false,
					allowBlank:false,//出险日期
					format:'Y-m-d',
					id:'updateInsurDate',
					cls:'readonly',
					name: CONFIGNAME.get('insurDate'),
			        fieldLabel:i18n('i18n.recompense.dangerTime*'),
			        listeners:{
			        	select:function(th,value,eOpts ){
                		   if(value>Ext.getCmp(CONFIGNAME.get('reportDate')).getValue()){
                			   MessageUtil.showMessage(i18n('i18n.recompense.dangerDateEarlyReportDate'));
                			   th.reset();
                		   }
	                	}
		            },
			        readOnly:true
				},{
//					colspan: 1,
//					id:'updateInsurType',
//					name: CONFIGNAME.get('insurType'),//出现类型
//			        fieldLabel:i18n('i18n.recompense.dangerType*'),
//			        xtype:'combo',
//		            store: DpUtil.getStore(CONFIGNAME.get('insurType'),null,['code','codeDesc'],[]),
//		            queryMode: 'local',
//		            forceSelection: true,
//		            editable:false,
//		            allowBlank:false,
//		            displayField: 'codeDesc',
//		            valueField: 'code',
					cls:'readonly',
				    fieldLabel:i18n('i18n.recompense.dangerType'),
				    readOnly:true,
					xtype:'textfield',					//出险类型insurType
					name: CONFIGNAME.get('insurType')
				  },{
					xtype: 'numberfield',
					decimalPrecision:0,
					minValue:0,
					maxValue:9999999999,//出险数量
					allowBlank:false,
					name: CONFIGNAME.get('insurQuantity'),
			        fieldLabel:i18n('i18n.recompense.dangerCount*'),
					cls:'readonly',
			        readOnly:true
	              },{
//	            	colspan: 1,
//	  				name: CONFIGNAME.get('deptId'),//所属大区
//	  		        fieldLabel:i18n('i18n.recompense.belongArea*'),
//	  		        id:'updateRecompenseBelongArea',
//	  		        xtype:'combo',
//	  	            store: DpUtil.getStore('updateRecompenseBelongArea',null,['id','deptName'],[]),
//	  	            queryMode: 'local',
//	  	            forceSelection: true,
//	  	            allowBlank:false,
//	  	            valueField:'id',
//	  	            displayField: 'deptName',
//	  	            listeners:{
//	  	            	select:function(th){
//	  	            		Ext.getCmp('showAreaDeptName').setValue(th.getRawValue());
//	  	            	}
//			        },
				    fieldLabel:i18n('i18n.recompense.belongArea'),
				    xtype:'textfield',					//所属大区belongArea
					cls:'readonly',
					name: CONFIGNAME.get('belongArea'),
			        readOnly:true
                 },{
					hidden:true,
					xtype:'textfield',
					name: CONFIGNAME.get('arriveCustomerId')
			   },{
					hidden:true,
					xtype:'textfield',
					name: CONFIGNAME.get('leaveCustomerId')
			  },{
					hidden:true,
					value:User.deptId,
					xtype:'textfield',
					name: CONFIGNAME.get('reportDept')
			  },{
					hidden:true,
					value:User.userId,
					xtype:'textfield',
					name:CONFIGNAME.get('reportMan')
			  },{
					hidden:true,
					xtype:'textfield',
					id:'showAreaDeptName',
					name:CONFIGNAME.get('deptName')
				  }]
		           
		    }];
		},
		initComponent:function(){
			var me = this;
		    me.items = me.getItems();
			this.callParent();
		}
});

	Ext.define('DetailRecompenseInfo', {			// 2.理赔信息form	//去除掉
		extend : 'TitleFormPanel',
		width:660,
		height : 116,
		getItems : function() {
			var me = this;
			return [ {
				xtype : 'fieldset',
				title : i18n('i18n.recompense.recompenseInfo'),
				layout : {
					type : 'table',
					columns : 4
				},
				defaults : {
					labelSeparator : '',
					style : 'margin-right:5px;',
					labelWidth : 60,
					width : 155,
					xtype : 'textfield',
					readOnly : true,
					cls:'readonly',
					colspan : 1
				},
				items : [ {
					name : CONFIGNAME.get('conRecompenseMethod'),
					fieldLabel : i18n('i18n.recompense.recompenseMethod')
				}, {
					fieldLabel : i18n('i18n.recompense.recompensetype'),
					name : CONFIGNAME.get('recompenseType')
				}, {
					fieldLabel : i18n('i18n.recompense.status'),
					name : CONFIGNAME.get('status')
				}, {
					fieldLabel : i18n('i18n.recompense.custNumber'),
					id :'showCustNumber',
					name : CONFIGNAME.get('custNumber')
				}, {
					fieldLabel : i18n('i18n.recompense.custName'),
					id :'showCustName',
					name : CONFIGNAME.get('custName'),
					xtype : 'textfield'
				}, {
					fieldLabel : i18n('i18n.recompense.custLevel'),
					id :'showConCustomerLevel',
					name : CONFIGNAME.get('degree'),
					xtype : 'textfield'
				}, {
					fieldLabel : i18n('i18n.recompense.claimAmount'),
					name : CONFIGNAME.get('recompenseAmount'),
					xtype : 'numberfield'
				}, {
					fieldLabel : i18n('i18n.recompense.recompensePerson'),
					name : CONFIGNAME.get('companyName')
				}, {
					fieldLabel : i18n('i18n.recompense.phone'),
					name : CONFIGNAME.get('companyPhone')
				}, {
					fieldLabel : i18n('i18n.recompense.fax'),
					name : CONFIGNAME.get('companyFax')
				} ]
			} ];
		},
		initComponent : function() {
			var me = this;
			me.items = me.getItems();
			this.callParent();
		}
});

		
		
	Ext.define('DeptChargeGird',{							//3.入部门费用Grid
		extend:'PopupGridPanel',
	    selModel: Ext.create('Ext.selection.CheckboxModel'), //选择框
		id:'deptChargeGird',
		width:660,
		height:95,
	    sortableColumns:false,
	    autoScroll:true,
	    enableColumnHide:false,
	    enableColumnMove:false,
	    getColumns:function(){
	    	var me = this;
	    	return [
	    		    new Ext.grid.RowNumberer(),
	    		    { header: i18n("i18n.recompense.dept"),dataIndex:CONFIGNAME.get('deptName'),flex:2},
	    			{ header: i18n("i18n.recompense.amount"),dataIndex:CONFIGNAME.get('amount'),flex:1}
	    			
	    		];
	    },
		initComponent:function()
		{
			var me = this;
			me.columns = me.getColumns();
			me.store = DpUtil.getStore('deptChargeStore','DeptChargeModel',null,[]);
			this.callParent();
		}
	});

	Ext.define('AwardItemGird',{							// 4.奖罚明细Grid
		extend:'PopupGridPanel',
		 //选择框
	    selModel:Ext.create('Ext.selection.CheckboxModel'),
		id:'awardItemGird',
		width:660,
	    height:50,
	    sortableColumns:false,
	    autoScroll:true,
	    enableColumnHide:false,
	    enableColumnMove:false,
	    getColumns:function(){
	    	var me = this;
	    	return [
	    		    { header: i18n("i18n.recompense.awardType"),dataIndex:CONFIGNAME.get('awardType'),flex:1,
	    				renderer : function(value){
	     	            	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.AWARD_TYPE);
	     	        }},
	    		    { header: i18n("i18n.recompense.dept"),dataIndex:CONFIGNAME.get('deptName'),flex:1},
	    		    { header: i18n("i18n.recompense.amount"),dataIndex:CONFIGNAME.get('amount'),flex:1},
	    		    { header: i18n("i18n.recompense.employee"),dataIndex:CONFIGNAME.get('userNumber'),flex:1},
	    		    { header: i18n("i18n.recompense.awardTargetType"),dataIndex:CONFIGNAME.get('awardTargetType'),flex:1,
	    				renderer : function(value){
	     	            	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.AWARD_TARGET_TYPE);
	     	        }}
	    		];
	    },
		initComponent:function()
		{
			var me = this;
			me.columns = me.getColumns();
			me.store = DpUtil.getStore('awardItemStore','AwardItemModel',null,[]);
			this.callParent();
		}	});
	
	var feeInfo = {
			xtype : 'titlepanel',
			height:250,
			width:660,
			layout : {
				type : 'vbox',
				align : 'stretch'
			},
			items : [ {
				xtype : 'displayfield',
				value : i18n('i18n.recompense.feeInfo'),
				flex : 1,
				width : 100
			}, {
				xtype : 'textarea',
//				id:'feeInfoTxtId',
				id:CONFIGNAME.get('costExplain'),
				name:CONFIGNAME.get('costExplain'),
				maxLength:4000,
				flex :9
			} ]
	};

	/**
	* 出险描述Grid
	* @author  Rock
	*/
	Ext.define('ShowDangerInfoGrid',{						// 5.1
		extend:'PopupGridPanel',
		id:'showDangerInfoGrid',
	    sortableColumns:false,
	    autoScroll:true,
	    flex:1,
	    enableColumnHide:false,
	    enableColumnMove:false,
	    getColumns:function(){
	    	var me = this;
	    	return [
	    			{ header: i18n("i18n.recompense.dangerType"),dataIndex:CONFIGNAME.get('insurType'),flex:1,
	    				renderer : function(value){
	     	            	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.DANGER_TYPE);
	     	            }
	    			},
	    			{ header: i18n("i18n.recompense.number"),dataIndex:CONFIGNAME.get('quality'),flex:1},
	    			{ header: i18n("i18n.recompense.description"),dataIndex:CONFIGNAME.get('description'),flex:3}
	    		];
	    },
		initComponent:function()
		{
			var me = this;
			me.columns = me.getColumns();
			me.store = DpUtil.getStore(me.id,'IssueDescriptionModel',null,[]);
			this.callParent();
			me.getView().on('render', function(view) {
			    view.tip = Ext.create('Ext.tip.ToolTip', {
			        target: view.el,
			        delegate: view.itemSelector,
			        trackMouse: true,
			        listeners: {
			            beforeshow: function updateTipBody(tip) {
			                tip.update(i18n('i18n.recompense.description')+':'+ view.getRecord(tip.triggerElement).get(CONFIGNAME.get('description')));
			            }
			        }
			    });
			});
		}
	});
	
	/**
	* 出险描述表格</br>
	* @author  Rock
	*/
	Ext.define('DangerInfoTitlePanel',{						// 5.
		id:'dangerInfoTitlePanel',
		extend:'BasicVboxPanel',
		items:null,
	    sortableColumns:false,
	    autoScroll:true,
	    enableColumnHide:false,
	    enableColumnMove:false,
	    width:660,
	    height:92,
	    layout:{
			type:'fit',
			align:'stretch'
		},
		title:i18n('i18n.recompense.dangerDescription'),
		initComponent:function(){
			this.items = [{
				xtype:'basicpanel',
				flex:1,
				items:[new ShowDangerInfoGrid()]
			}];
			this.callParent();
		}
	});
	
	/**
	* 金额信息PANEL</br>
	* @author  Rock
	*/
	Ext.define('AmountPanel',{						// 6
		extend:'SearchFormPanel',
		id:'amountPanel',
		height:44,
		width:660,
		items:null,
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [{
				height:30,
				layout:{
					type:'table',
					columns:3
				},
				defaultType:'textfield',
				defaults:{
					labelWidth:70,
					width:210
				},
				items:[{
	   	    		   fieldLabel:i18n('i18n.recompense.normalRecompense'),//正常理赔金额
	   	    		   id:CONFIGNAME.get('normalAmount'),
		    	       name:CONFIGNAME.get('normalAmount'),
					    xtype:'numberfield',
					    colspan: 1,
					    readOnly:true,
					    cls:'readonly',
					    minValue:0.01,
					    //@TODO流程金额确认之后，理赔专员不可以修改
					    maxValue:9999999999999999,
					    allowBlank:false
				   },{
	   	    		   fieldLabel:i18n('i18n.recompense.moreAmount'),//多赔金额
	   	    		   id:CONFIGNAME.get('overpayAmount'),
		    	       name:CONFIGNAME.get('overpayAmount'),
					    xtype:'numberfield',
//					    decimalPrecision:0
					    readOnly:true,
					    cls:'readonly',
					    colspan: 1,
					    minValue:0.01,
					    value:0,
					    maxValue:9999999999999999
				   },{
	   	    		   fieldLabel:i18n('i18n.recompense.realAmount'),//实际理赔金额
	   	    		   id:CONFIGNAME.get('realAmount'),
		    	       name:CONFIGNAME.get('realAmount'),
					    xtype:'numberfield',
					    readOnly:true,
					    cls:'readonly',
//					    decimalPrecision:0,
					    colspan: 1,
					    minValue:0.01,
					    maxValue:9999999999999999,
					    allowBlank:false
				   }]		
			}];
		}
	});

	Ext.define('innerPanel',{
		extend:'Ext.panel.Panel',
		record : null,
		width:666,
		height:910,
	    autoScroll:true,				//TODO:滚动条
		items:null,
		itemsDangerInfo:null,			//出险信息	form
//		itemsDetailRecompenseInfo:null,	//理赔信息	form
		itemsDeptChargeGird:null,		//入部门费用	Grid
		itemsAwardItemGird:null,		//奖罚明细	Grid
		itemsDangerInfoTitlePanel:null,	//
		itemsAmountPanel:null,			//
		itemsFeeInfo:null,				//费用说明	form
		itemsJg1:null,
		itemsJg2:null,
		itemsJg3:null,
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		border:false,
		initComponent : function(){
			var me = this;
			this.items = this.getItems();
			setTimeout(function(){
				me.loadData();
			},1)
			this.callParent();
		},
		getItems: function(){
			this.itemsDangerInfo 			= new DangerInfo();
//			this.itemsJg1					= new jg();
//			this.itemsDetailRecompenseInfo	= new DetailRecompenseInfo();
			this.itemsJg2					= new jg();
			this.itemsDeptChargeGird 		= new DeptChargeGird();
			this.itemsJg3					= new jg();
			this.itemsAwardItemGird 		= new AwardItemGird();
			this.itemsJg4					= new jg();
			this.itemsDangerInfoTitlePanel	= new DangerInfoTitlePanel();
			this.itemsJg5					= new jg();
			this.itemsAmountPanel			= new AmountPanel();
			this.itemsJg6					= new jg();
			this.itemsFeeInfo 				= feeInfo;
//			this.itemsFeeInfo 				= new feeInfo();
			return [ this.itemsDangerInfo,
//			         this.itemsJg1,
//			         this.itemsDetailRecompenseInfo,
			         this.itemsJg2,
			         this.itemsDeptChargeGird,
			         this.itemsJg3,
			         this.itemsAwardItemGird,
			         this.itemsJg4,
			         this.itemsDangerInfoTitlePanel,
			         this.itemsJg5,
			         this.itemsAmountPanel,
			         this.itemsJg6,
			         this.itemsFeeInfo,
			         {	xtype : "button",
						text  : i18n('i18n.recompense.btnPrint'),			//打印
						style : {
							marginLeft	:'550px'
						},
						handler:function(){
							this.setVisible(false);
							window.print();
							this.setVisible(true);
						}}
			];
		},
		loadData:function(){
			var me 				= this;
			me.record			= printDatas;
//			console.log(printDatas);				//所属大区belongArea出险类型insurType
			var waybillModel 	= new WaybillModel(me.record.waybill),
				recompenseApp 	= new RecompenseApp(me.record);
			
/*			if(!Ext.isEmpty(recompenseApp.get(CONFIGNAME.get('customer')))){
				Ext.getCmp('showCustNumber').setValue(recompenseApp.get(CONFIGNAME.get('customer')).custNumber?recompenseApp.get(CONFIGNAME.get('customer')).custNumber:'');
				Ext.getCmp('showCustName').setValue(recompenseApp.get(CONFIGNAME.get('customer')).custName);
				Ext.getCmp('showConCustomerLevel').setValue(DpUtil.changeDictionaryCodeToDescrip(recompenseApp.get(CONFIGNAME.get('customer')).degree,DataDictionary.MEMBER_GRADE));
			};*/
			
			var recompenseType 	= recompenseApp.get(CONFIGNAME.get('recompenseType'));		//理赔类型
			recompenseApp.set(CONFIGNAME.get('recompenseType'), DpUtil.changeDictionaryCodeToDescrip(recompenseType,
							DataDictionary.RECOMPENSE_TYPE));
			var recompenseMethod = recompenseApp.get(CONFIGNAME.get('recompenseMethod'));	//理赔方式
			recompenseApp.set(CONFIGNAME.get('recompenseMethod'), DpUtil.changeDictionaryCodeToDescrip(recompenseMethod,
							DataDictionary.RECOMPENSE_WAY));
			var status = recompenseApp.get(CONFIGNAME.get('status'));						//状态
			recompenseApp.set(CONFIGNAME.get('status'), DpUtil.changeDictionaryCodeToDescrip(status,
							DataDictionary.RECOMPENSE_STATUS));
			var insurType = recompenseApp.get(CONFIGNAME.get('insurType'));					//出险类型
			recompenseApp.set(CONFIGNAME.get('insurType'), DpUtil.changeDictionaryCodeToDescrip(insurType,
					DataDictionary.DANGER_TYPE));
			recompenseApp.set(CONFIGNAME.get('belongArea'),recompenseApp.get(CONFIGNAME.get('deptName')) );
			if(!Ext.isEmpty(recompenseApp.get('overpay'))){
				Ext.getCmp(CONFIGNAME.get('overpayAmount')).setValue(recompenseApp.get('overpay').overpayAmount);
				Ext.getCmp(CONFIGNAME.get('realAmount')).setValue(recompenseApp.get('overpay').totalAmount);
			}
			me.itemsDangerInfo.loadRecord(waybillModel);						//loadData1
			me.itemsDangerInfo.loadRecord(recompenseApp);
//			me.itemsDetailRecompenseInfo.loadRecord(recompenseApp);				//loadData2
			Ext.getCmp('deptChargeGird').getStore().loadData(recompenseApp.get(CONFIGNAME.get('deptChargeList')));	//loadData3
			Ext.getCmp('awardItemGird').getStore().loadData(recompenseApp.get(CONFIGNAME.get('awardItemList')));	//loadData4
			
			Ext.getCmp('showDangerInfoGrid').getStore().removeAll();			//loadData5:出现描述
			if(!Ext.isEmpty(recompenseApp.get(CONFIGNAME.get('issueItemList')))){
				Ext.getCmp('showDangerInfoGrid').getStore().loadData(recompenseApp.get(CONFIGNAME.get('issueItemList')));
			};
			Ext.getCmp('amountPanel').getForm().loadRecord(recompenseApp);
			Ext.getCmp(CONFIGNAME.get('costExplain')).setValue(recompenseApp.get(CONFIGNAME.get('costExplain')));
//			me.itemsFeeInfo.loadRecord(recompenseApp);																//loadData5	???
		}
	
	});
//;;;	
	Ext.define('PrintPanel', {
		extend : 'Ext.container.Viewport',
		layout : 'fit',
		items  : null,
		layout : {
			type : 'vbox',
			align : 'left'
		},
		getItems : function(){
			var inner = new innerPanel();
			return [ inner ];
		},
	    initComponent : function(){
			this.items = this.getItems();
			this.callParent();
		}
	});
	new PrintPanel();
});