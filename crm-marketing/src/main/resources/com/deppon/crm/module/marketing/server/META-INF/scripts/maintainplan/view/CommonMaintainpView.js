/**
 * <p>
 * 维护计划新增修改查询条件Form
 * </p>
 * @author  张登
 * @date    2012-03-13
 */
Ext.define('SearchConditionForm',{
    extend:'SearchFormPanel',
    items:null,
    initComponent:function(){
        var me = this;
        me.items = me.getItems();
        this.callParent();
    },
    getItems:function(){
		var me = this;
		return[{
			xtype : 'panel',
			layout : {
				type : 'table',
				columns : 12
			},
			defaultType : 'textfield',
			defaults : {
				labelWidth : 65,
                labelAlign: 'right',
				width : 190
			},
			items : [{
				fieldLabel : i18n('i18n.PotentialCustManagerView.deptName'),
				name:'execdeptid',
				xtype:'combo',
				store:Ext.create('DeptStore',{id:'queryDeptStoreId',
					listeners:{
						beforeload:function(store, operation, eOpts){
							Ext.apply(operation,{
								params : {
									'deptName':Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").getRawValue()
									}
								}
							);	
						}
					}
				}),
				colspan:3,
				allowBlank :false,
				triggerAction : 'all',
				displayField:'deptName',
				valueField:'id',
				forceSelection :true,
				hideTrigger:false,
				emptyText:'请输入部门关键字',
				pageSize: 10,
				minChars:2,
				queryDelay:800,
				listConfig: {
	  	        	minWidth :300,
	  	            getInnerTpl: function() {
	  	            	 return '{deptName}';
	  	            }
	  	        },
				listeners:{
					change:DButil.comboSelsct,
					select:function(th,records){//盛诗庆，新增切换部门清空已选列表 20140415
                        if(!Ext.isEmpty(Ext.getCmp("ChooseCustomerGridId")) || !Ext.isEmpty(Ext.getCmp("searchResultGridId"))){
                            if(!Ext.isEmpty(Ext.getCmp("ChooseCustomerGridId")) || !Ext.isEmpty(Ext.getCmp("searchResultGridId"))){
                            	MessageUtil.showQuestionMes( "更换部门后会重置已选择客户，是否继续", function(e){ 
                                    if (e == 'yes') {
                                        Ext.getCmp("ChooseCustomerGridId").store.removeAll();
                                        Ext.getCmp("searchResultGridId").store.removeAll();
                                        if(!Ext.isEmpty(Ext.getCmp("planFromPanel"))){
                                            Ext.getCmp("planFromPanel").getForm().findField("execdeptid").setValue(th.getRawValue());
                                            Ext.getCmp("planFromPanel").getForm().findField("execuserid").store.load({params:{executeDept:th.value}});
                                        }
                                        th.oldValue=th.getValue();
                                    }else{
                                        th.setValue(th.oldValue);
                                    }
                                },function(){
                                	th.setValue(th.oldValue);
                                });
                            }else{
                                th.oldValue=th.getValue();
                                if(!Ext.isEmpty(Ext.getCmp("planFromPanel"))){
                                    Ext.getCmp("planFromPanel").getForm().findField("execdeptid").setValue(th.getRawValue());
                                    Ext.getCmp("planFromPanel").getForm().findField("execuserid").store.load({params:{executeDept:th.value}});
                                }
                            }
                        }
                    }
				}
			},{//新增业务类别 标识客户是零担，快递，还是零担加快递 盛诗庆
				fieldLabel : '业务类别',
				xtype : 'combo',
				colspan : 3,
				name : 'custCategory',
				store:getDataDictionaryByName(DataDictionary,'CUST_CATEGORY'),
				queryMode:'local',
				displayField:'codeDesc',
				valueField:'code',  
				forceSelection :true,
				listeners:{
					change:DButil.comboSelsct
				}
			},{//合并客户类型 标识潜散客，固定客户 盛诗庆
				fieldLabel : '客户类型',
				xtype : 'combo',
				colspan : 3,
				name : 'custType',
				store:getDataDictionaryByName(DataDictionary,'CUST_TYPE'),
				queryMode:'local',
				displayField:'codeDesc',
				valueField:'code',  
				forceSelection :true,
				value : 'PC_CUSTOMER',
				allowBlank : false,
				blankText : '请选择客户类型',
				oldValue : null,
				listeners:{
					change:function(combo,newValue,oldValue){
//						combo.oldValue = oldValue;
						if(Ext.isEmpty(combo.getValue())){
							combo.setValue("");
						}
					},
					select:function(th,records){//盛诗庆，新增切换部门清空已选列表 20140415
						
						if(th.value == 'RC_CUSTOMER'){//刷新客户等级下拉列表中数据
							me.getForm().findField('developStage').setValue("");
							me.getForm().findField('developStage').store.removeAll();//清空开发阶段
							controlForm(me.getForm(),false);
							var dictStore = getDataDictionaryByName(DataDictionary,'MEMBER_GRADE').data;
							var store = me.getForm().findField('memberLevel').store;
							var data = new Array();
							dictStore.each(function(model){
								data.push(model);
							})
							store.loadData(data);
						}else{
							me.getForm().findField('memberLevel').setValue(null);
							if(th.value == 'PC_CUSTOMER'){//潜客禁用发到货输入框
								controlForm(me.getForm(),true);	
							}else{
								controlForm(me.getForm(),false);	
							}
							var dictStore = getDataDictionaryByName(DataDictionary,'DEVELOP_STAGE').data;
							var store = me.getForm().findField('developStage').store;
							var data = new Array();
							dictStore.each(function(model){
								data.push(model);
							})
							store.loadData(data);
							me.getForm().findField('memberLevel').store.removeAll();
						}
						if(th.oldValue != null && th.oldValue != th.value && th.oldValue == 'RC_CUSTOMER' || th.value == 'RC_CUSTOMER'){
							if(!Ext.isEmpty(Ext.getCmp("ChooseCustomerGridId")) || !Ext.isEmpty(Ext.getCmp("searchResultGridId"))){
	                            if(Ext.getCmp("ChooseCustomerGridId").store.getCount()>0 || Ext.getCmp("searchResultGridId").store.getCount() > 0){
	                            	MessageUtil.showQuestionMes("切换客户类型后会重置已选择客户，是否继续", function(e){ 
//	                                	alert(e);
	                                	if (e == 'yes') {
	                                        Ext.getCmp("ChooseCustomerGridId").store.removeAll();
	                                        Ext.getCmp("searchResultGridId").store.removeAll();
	                                        th.oldValue=th.getValue();
	                                    }else{
	                                        th.setValue(th.oldValue);
	                                    }
	                                },function(){
	                                	th.setValue(th.oldValue);
	                                });
	                            }
	                            else{
	                                th.oldValue=th.getValue();
	                            }
	                        }
						}
                        
                    }
				}
			},{//合并合作意向 针对潜散客客户 盛诗庆
				fieldLabel : '合作意向',
				xtype : 'combo',
				colspan : 3,
				name : 'cooperationIntention',
				store:getDataDictionaryByName(DataDictionary,'COOPERATION_INTENTION'),
				queryMode:'local',
				displayField:'codeDesc',
				valueField:'code',  
				forceSelection :true,
				listeners:{
					change:DButil.comboSelsct
				}
			},{//合并客户来源查询条件 盛诗庆
				fieldLabel : '客户来源',
				xtype : 'combo',
				colspan : 3,
				name : 'custSorce',
				store:getDataDictionaryByName(DataDictionary,'CUST_SOURCE'),
				queryMode:'local',
				displayField:'codeDesc',
				valueField:'code',  
				forceSelection :true,
				listeners:{
					change:DButil.comboSelsct
				}
			},{//合并货量潜力 盛诗庆
				fieldLabel : '货量潜力',
				xtype : 'combo',
				colspan : 3,
				name : 'cargoPotential',
				store:getDataDictionaryByName(DataDictionary,'CARGO_POTENTIAL'),
				queryMode:'local',
				displayField:'codeDesc',
				valueField:'code',  
				forceSelection :true,
				listeners:{
					change:DButil.comboSelsct
				}
			},{//修改开发维护计划商机状态为开发阶段 盛诗庆
				fieldLabel : '开发阶段',
				xtype : 'combo',
				colspan : 3,
				name : 'developStage',
				store:getDataDictionaryByName(DataDictionary,'DEVELOP_STAGE'),
				queryMode:'local',
				displayField:'codeDesc',
				valueField:'code',  
				forceSelection :true,
				listeners:{
					change:DButil.comboSelsct
				}
			},{//添加继续营销字段
				fieldLabel : '继续营销',
				xtype : 'combo',
				colspan : 3,
				name : 'continueMarket',
				store:getDataDictionaryByName(DataDictionary,'CONTINUE_MARKET'),
				queryMode:'local',
				displayField:'codeDesc',
				valueField:'code',  
				forceSelection :true,
				listeners:{
					change:DButil.comboSelsct
				}
			},{
				fieldLabel : i18n('i18n.Maintainp.memberNo'),
				colspan : 3,
				maxLength : 40,
				maxLengthText : i18n('i18n.developPlan.maxLength')+40,
				name : 'memberNo'
			},{
				fieldLabel : i18n('i18n.PotentialCustManagerView.customerName'),
				colspan : 3,
				maxLength : 80,
				maxLengthText : i18n('i18n.developPlan.maxLength')+80,
				name : 'custName'
			},{
				fieldLabel : i18n('i18n.Maintainp.memberLevel'),
				colspan : 3,
				xtype : 'combo',
				name : 'memberLevel',
				store:Ext.create('Ext.data.Store',{
	                   fields : ['code','codeDesc']
		               }),
				queryMode:'local',
				displayField:'codeDesc',
				valueField:'code',  
				forceSelection :true,
				listeners:{
					change:DButil.comboSelsct
				}
			}, {
				fieldLabel : i18n('i18n.ScatterCustManagerView.custAttribute'),
				xtype : 'combo',
				colspan : 3,
				name : 'custProperty',
				store:getDataDictionaryByName(DataDictionary,'CUSTOMER_NATURE'),
				queryMode:'local',
				displayField:'codeDesc',
				valueField:'code',  
				forceSelection :true,
				listeners:{
					change:DButil.comboSelsct
				}
			}, {
				fieldLabel : i18n('i18n.developSchedule.linkManCode'),
				colspan : 3,
				maxLength : 40,
				maxLengthText : i18n('i18n.developPlan.maxLength')+40,
				name : 'linkManCode'
			}, {
				fieldLabel : i18n('i18n.PotentialCustManagerView.contactName'),
				colspan : 3,
				maxLength : 20,
				maxLengthText : i18n('i18n.developPlan.maxLength')+20,
				name : 'linkManName'
			}, {
				fieldLabel : i18n('i18n.PotentialCustManagerView.contactPhone'),
				colspan : 3,
				minLength:8,
				maxLength : 11,
				maxLengthText : i18n('i18n.developPlan.maxLength')+11,
				name : 'linkManMobile',
				regexText:i18n('i18n.ScatterCustManagerView.message_rightString'),
				//配合香港开点：手机号为8位数字或是首位为1的11位数字
				regex : /(^1\d{10}$)|(^\d{8}$)/
			}, {
				fieldLabel : i18n('i18n.PotentialCustManagerView.contactTel'),
				colspan : 3,
				name : 'linkManPhone',
				maxLength : 20,
				maxLengthText : i18n('i18n.developPlan.maxLength')+20
			},{
				fieldLabel : i18n('i18n.PotentialCustManagerView.searchStartTime'),
				colspan : 3,
				xtype : 'datefield',
				format : 'Y-m-d',
				editable:       false,
				name : 'beginTime',
				id:'beginTime',
				value : new Date(new Date().setMonth(new Date().getMonth() - 3))
			}, {
				xtype: 'datefield',
				//width : 120,
				colspan:3,
				labelSeparator:'',
				fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
				format : 'Y-m-d',
				name : 'overTime',
				id:'overTime',
				value : new Date(),
				editable:false
			},{//二级行业
				xtype:'customertrade',
				userType:'SEARCH',
				margin:'0 0 0 0',
				colspan:6,
				fieldLabel:i18n('i18n.PotentialCustManagerView.industry'),
				width:390,
				trade_labelWidth:55,
				trade_width:180,
				trade_name:'trade',
				trade_fieldname:i18n('i18n.PotentialCustManagerView.firstTrade'),
				second_trade_labelWidth:70,
				second_trade_width:195,
				trade_margin:'-10px 0 -5px 0',
	            second_trade_margin:'-10px 0 -5px 0',
				second_trade_name:'secondTrade',
				second_trade_fieldname:i18n('i18n.PotentialCustManagerView.secondTrade')
			},{
				fieldLabel : '查询时间',
				colspan : 3,
				xtype : 'datefield',
				format : 'Y-m-d',
				editable: true,
				name : 'searchBeginTime',
				readOnly : true
			},{
				xtype: 'datefield',
				//width : 120,
				colspan:3,
				labelSeparator:'',
				fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
				format : 'Y-m-d',
				name : 'searchOverTime',
				editable:true,
				readOnly : true
			},{
				xtype : 'fieldcontainer',
				colspan : 3,
				border : 0,
				layout : 'column',
				defaultType : 'numberfield',
				defaults : {
					hideTrigger: true,
					labelAlign: 'right',
					mouseWheelEnabled: false,
					minValue: 1
				},
				items : [ {
					fieldLabel : i18n('i18n.ScatterCustManagerView.beginShipWeight'),
					labelWidth : 65,
					maxLength :10,
					maxLengthText : i18n('i18n.developPlan.maxLength')+10,
					width : 120,
					name : 'beginShipWeight',
					readOnly : true
				},
				{xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
				{
					fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
					name : 'overShipWeight',
                    hideLabel: true,
                    maxLength :10,
					maxLengthText : i18n('i18n.developPlan.maxLength')+10,
					width : 55,
					labelWidth : 10,
					readOnly : true
				} ]
			}, {
				xtype : 'fieldcontainer',
				colspan : 3,
				border : 0,
				layout : 'column',
				defaultType : 'numberfield',
				defaults : {
					hideTrigger: true,
					mouseWheelEnabled: false,
					labelAlign: 'right',
					minValue: 1
				},
				items : [ {
					fieldLabel : i18n('i18n.ScatterCustManagerView.beginArrivalWeight'),
					name : 'beginArrivalWeight',
					maxLength :10,
					maxLengthText : i18n('i18n.developPlan.maxLength')+10,
					labelWidth : 65,
					width : 120,
					readOnly : true
				}, 
				{xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
				{
					fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
					name : 'overArrivalWeight',
                    hideLabel: true,
                    maxLength :10,
					maxLengthText : i18n('i18n.developPlan.maxLength')+10,
					width : 55,
					labelWidth : 10,
					readOnly : true
				} ]
			},
			{
				xtype : 'fieldcontainer',
				colspan : 3,
				border : 0,
				layout : 'column',
				defaultType : 'numberfield',
				defaults : {
					hideTrigger: true,
					labelAlign: 'right',
					mouseWheelEnabled: false,
					minValue: 1
				},
				items : [ {
					fieldLabel : i18n('i18n.ScatterCustManagerView.beginShipAmount'),
					name : 'beginShipAmount',
					labelWidth : 65,
					maxLength :10,
					maxLengthText : i18n('i18n.developPlan.maxLength')+10,
					width : 120,
					readOnly : true
				}, 
				{xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
				{
					fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
					name : 'overShipAmount',
                    hideLabel: true,
					width : 55,
					maxLength :10,
					maxLengthText : i18n('i18n.developPlan.maxLength')+10,
					labelWidth : 10,
					readOnly : true
				} ]
			},
			{
				xtype : 'fieldcontainer',
				colspan : 3,
				border : 0,
				layout : 'column',
				defaultType : 'numberfield',
				defaults : {
					hideTrigger: true,
					mouseWheelEnabled: false,
	                labelAlign: 'right',
					minValue: 1
				},
				items : [ {
					fieldLabel : i18n('i18n.ScatterCustManagerView.beginArrivalAmount'),
					name : 'beginArrivalAmount',
					labelWidth : 65,
					maxLength :10,
					maxLengthText : i18n('i18n.developPlan.maxLength')+10,
					width : 120,
					readOnly : true
				},
				{
					xtype: 'displayfield',
					width : 10, 
					value: i18n('i18n.PotentialCustManagerView.searchEndTime')
				},
				{
					fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
					name : 'overArrivalAmount',
                    hideLabel: true,
					width : 55,
					maxLength :10,
					maxLengthText : i18n('i18n.developPlan.maxLength')+10,
					labelWidth : 10,
					readOnly : true
				} ]
			},{//发货票数
				xtype : 'fieldcontainer',
				colspan : 3,
				margin : '-5px 0 0 0',
				border : 0,
				layout : 'column',
				defaultType : 'numberfield',
				defaults : {
					hideTrigger: true,
					labelAlign: 'right',
					mouseWheelEnabled: false,
					minValue: 1
				},
				items : [ {
					fieldLabel : i18n('i18n.ScatterCustManagerView.beginShipVotes'),
					name : 'beginShipVotes',
					labelWidth : 65,
					maxLength :10,
					maxLengthText : i18n('i18n.developPlan.maxLength')+10,
					width : 120,
					regexText:i18n('i18n.ScatterCustManagerView.message_rightString'),
					regex : /^\d{0,10}$/,
					readOnly : true
				}, 
				{xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
				{
					fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
					name : 'overShipVotes',
                    hideLabel: true,
					width : 55,
					maxLength :10,
					maxLengthText : i18n('i18n.developPlan.maxLength')+10,
					labelWidth : 10,
					regex : /^\d{0,10}$/,
					regexText:i18n('i18n.ScatterCustManagerView.message_rightString'),
					readOnly : true
				} ]
			},
			
			 {//到达票数
				xtype : 'fieldcontainer',
				colspan : 3,
				margin : '-5px0 0 0',
				border : 0,
				layout : 'column',
				defaultType : 'numberfield',
				defaults : {
					hideTrigger: true,
					mouseWheelEnabled: false,
					labelAlign: 'right',
					minValue: 1
				},
				items : [ {
					fieldLabel : i18n('i18n.ScatterCustManagerView.beginArrivalVotes'),
					name : 'beginArrivalVotes',
					labelWidth : 65,
					width : 120,
					maxLength :10,
					maxLengthText : i18n('i18n.developPlan.maxLength')+10,
					regexText:i18n('i18n.ScatterCustManagerView.message_rightString'),
					regex : /^\d{0,10}$/,
					readOnly : true
				}, 
				{xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
				{
					fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
					name : 'overArrivalVotes',
                    hideLabel: true,
					width : 55,
					labelWidth : 10,
					maxLength :10,
					maxLengthText : i18n('i18n.developPlan.maxLength')+10,
					regexText:i18n('i18n.ScatterCustManagerView.message_rightString'),
					regex : /^\d{0,10}$/,
					readOnly : true
				} ]
			} ]
		}];
	}
});


/**
 * <p>
 * 制定计划formPnale
 * </p>
 * @author  张登
 * @date    2012-03-13
 */
Ext.define('SavePlanPanel',{
	extend:'TitleFormPanel',  
	items:null,
	initComponent:function(){
		this.items = this.getItems(); 
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'basicfiledset', 
			title:i18n('i18n.plan.gridPaneltitle'),
			layout:{
				type:'table',
				columns:4
			},
			defaultType:'textfield',
			defaults:{
				labelWidth : 65,
		        labelAlign: 'right',
				width : 200
			},
			items:[{
                name: 'beginTime',
                xtype     : 'datefield',
                width:195,
                fieldLabel: i18n('i18n.Maintainp.startEnd'),
                editable:       false,
                format: 'Y-m-d',
                minValue :Ext.Date.add(new Date(), Ext.Date.DAY, 1),
                value:Ext.Date.add(new Date(), Ext.Date.DAY, 1),
                allowBlank: false
            }, {
                width:140,
                xtype     : 'datefield',
                name: 'endTime',
                fieldLabel: '-',
                labelSeparator:'',
                editable:       false,
                labelWidth: 10,
                format: 'Y-m-d',
                minValue :Ext.Date.add(new Date(), Ext.Date.DAY, 2),
                value:Ext.Date.add(new Date(), Ext.Date.DAY, 2),
                allowBlank: false
            },{
                width:210,
                name : 'execdeptid',
                fieldLabel: i18n('i18n.developPlan.executeDept'),
                disabled:true,
                allowBlank: false
            },{
            	width:210,
                xtype:          'combo',
              	fieldLabel: i18n('i18n.developPlan.executePersion'),
                name:'execuserid',
                store:Ext.create('UserStore'),
    			queryMode: 'local',
    			triggerAction : 'all',
    			displayField:'empName',
    			valueField:'id',
    			//剔出朦层
    			listConfig: {
      		        loadMask:false
    			},
    			forceSelection :true,
    			listeners:{
    				change:DButil.comboSelsct
    			},
                allowBlank: false
            },{
                width:340,
                name : 'topic',
                fieldLabel: i18n('i18n.Maintainp.topic'),
                maxLength :50,
				maxLengthText : i18n('i18n.developPlan.maxLength')+50,
                colspan : 2,
                allowBlank: false
            },{
                width:210,
                name : 'activedesc',
                maxLength :50,
				maxLengthText : i18n('i18n.developPlan.maxLength')+50,
                fieldLabel: i18n('i18n.Maintainp.planDesc'),
                colspan : 1
            },{
            	/**
            	 * 苏玉军
            	 */
            	width:210,
            	name:'projectType',
            	xtype:'combobox',
            	fieldLabel:i18n('i18n.developSchedule.planeType'),
            	store:getDataDictionaryByName(DataDictionary,'PLAN_TYPE'),
				displayField:'codeDesc',
				valueField:'code',
				allowBlank : false,
				forceSelection :true,
				blankText : i18n('i18n.developSchedule.planeTypeNeeded'),
				value:'LESS_THAN_TRUCKLOAD',
				colspan:1,
				listeners:{
					change:DButil.comboSelsct
				}
            	
            },{ //选择问卷-肖红叶-20140322
    			fieldLabel:i18n('i18n.Maintainp.chooseQuestionnaire'), 
    			xtype : "queryCombox",
    			store:Ext.create('Ext.data.Store',{
    				model:'QuestionnaireInfoModel'
    			}),
                maxLength:170,
                width:340,
                colspan:2,
    			queryMode : "local",
    			editable:true,
    			forceSelection : true,
    			listeners:{
    				change : DButil.comboSelsct,
    				expand:function(){
    					//此时选择问卷时只能筛选出生效和适用中的问卷
    					questionnaireStatusTypeForCombobox = 'SPECIAL';
    					var seletctQuestionnaireWindow = Ext.getCmp("seletctQuestionnaireWindowId");//获取win
    					if(!seletctQuestionnaireWindow){
    						seletctQuestionnaireWindow = Ext.create('SeletctQuestionnaireWindow',{id:'seletctQuestionnaireWindowId','parent':me});
    					}
    					//重置查询条件和清空问卷结果
    					Ext.getCmp('questionnaireInfoListGridForPlan').store.removeAll();
    					Ext.getCmp('questionnaireQueryPanelForPlan').getForm().reset();
    					Ext.getCmp('questionnaireInfoListGridForPlan').scrollByDeltaX(-1);
    					seletctQuestionnaireWindow.show();
    				}
    			},
    			displayField : "questionnaireName",
    			valueField : "id",
    			name:'questionnaireId'
    		},{//隐藏域--问卷生效时间
                name: 'effectiveTime',
                width:210,
                xtype     : 'datefield',
                fieldLabel: '生效时间',
                format: 'Y-m-d',
                hidden:true
            },{//隐藏域--问卷失效时间
                name: 'invalidTime',
                xtype     : 'datefield',
                fieldLabel: '失效时间',
                width:210,
                format: 'Y-m-d',
                hidden:true
            },
            {
            	xtype:'hiddenfield',
            	width:110,
            	name:'id'
            },{
            	xtype:'hiddenfield',
            	width:110,
            	name:'planType'
            }]		
		}];
	}
});

/**
 * 选择问卷查询按钮面板
 * 2014-3-6
 * 肖红叶
 */
Ext.define('QuestionnaireBtnPanelForPlan',{
	extend:'NormalButtonPanel',
	border:false,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();		
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'leftbuttonpanel',
			width:450,
			items:[
			  {//查看详情
				  xtype:'button',
				  text:'查看详情',
				  disabled:true,
				  id:'questionnairePanelDetailBtnId',
				  handler:function(btn){
					  var questionnaireDetailPopWindow = Ext.getCmp("questionnaireDetailPopWindowId");//获取win
						if(!questionnaireDetailPopWindow){
							questionnaireDetailPopWindow = Ext.create('QuestionnaireDetailPopWindow',{id:'questionnaireDetailPopWindowId'});
						}
  					  //获得问卷基本信息面板
					  var questionnaireDetailForm = Ext.getCmp('questionnaireDetailFormId').getForm();
					  //重置查看详情页面
					  questionnaireDetailForm.reset();
					  Ext.getCmp('questionListGridId').store.removeAll();
					  questionnaireDetailPopWindow.show();
					  
					  //根据问卷id查询已选问题列表
					  //获得问卷查询结果列表
					  var grid = Ext.getCmp('questionnaireInfoListGridForPlan');
					  //获得所选问卷记录
					  var selection = grid.getSelectionModel().getSelection();
					  questionnaireDetailForm.loadRecord(selection[0]);
					  //获得问卷id数组
					  var idArray = new Array();
					  for(var i = 0;i < selection.length;i++){
						  idArray.push(selection[i].get("id"));
					  }
					  questionnaireIdListForWhole = idArray;
					  questionIdListForWhole = null;
					  //加载查看详情中的问题信息列表
					  Ext.getCmp('questionListGridId').store.removeAll();
					  Ext.getCmp('questionListGridId').store.load();
				  }
			  }]
		   },{
			 xtype:'middlebuttonpanel' 
		   },{
			xtype : 'rightbuttonpanel',
			items : [{//查询
			    text:'查询',
			    xtype:'button',
			    handler:function(btn){
			    	//获得查询条件输入form
			    	var form = Ext.getCmp('questionnaireQueryPanelForPlan').getForm();
			    	//开始创建时间
			    	var createStartDate = form.findField('createStartDate').getValue();
			    	//结束创建时间
			    	var createEndDate = form.findField('createEndDate').getValue();
			    	//开始有效时间
			    	var validStartDate = form.findField('validStartDate').getValue();
			    	//结束有效时间
			    	var validEndDate = form.findField('validEndDate').getValue();
			    	//创建时间范围与有效时间范围不能同时为空
			    	if(!((!(Ext.isEmpty(createStartDate)) && !(Ext.isEmpty(createEndDate))) ||
			    			(!(Ext.isEmpty(validStartDate)) && !(Ext.isEmpty(validEndDate))))){
			    		MessageUtil.showErrorMes(i18n('i18n.questionnaireManage.queryDateWrong'));
						return false;
			    	}
			    	//判断时间范围是否填写完整
			    	if(dateRangeNotWhole(createStartDate,createEndDate) || 
			    			dateRangeNotWhole(createEndDate,createStartDate)){
			    		MessageUtil.showErrorMes('创建时间范围如果录入，请录入完整的起止创建时间！');
						return false;
			    	}
			    	if(dateRangeNotWhole(validStartDate,validEndDate) || 
			    			dateRangeNotWhole(validEndDate,validStartDate)){
			    		MessageUtil.showErrorMes('有效时间范围如果录入，请录入完整的起止有效时间！');
						return false;
			    	}
			    	//判断时间范围是否合法且时间范围间隔不超过90天
			    	if(dateRangeNotValid(createStartDate,createEndDate,89,'DAY')){
			    		MessageUtil.showErrorMes('创建开始时间必须早于创建结束时间且最大创建时间范围为90天！');
						return false;
			    	}
			    	if(dateRangeNotValid(validStartDate,validEndDate,89,'DAY')){
			    		MessageUtil.showErrorMes('开始有效时间必须早于结束有效时间且最大有效时间范围为90天！');
						return false;
			    	}
			    	//查询条件面板存在校验不通过的条件，则不允许触发请求
			    	if(!form.isValid()){
			    		return false;
			    	}
			    	//为查询时的查询参数赋值
			    	searchParamsForWhole = null;
			    	searchParamsForWhole = queryConditionParam(form);
			    	Ext.getCmp('questionnaireInfoListGridForPlan').store.loadPage(1);
			    }
			 },{
				 xtype:'button',
				 text:'重置',
				 handler:function(){
					 //重置查询条件
					 Ext.getCmp('questionnaireQueryPanelForPlan').getForm().reset();
				 }
			 }]
		   }];
	}
});

/**
 * 选择问卷弹出框窗口PANEL
 * 肖红叶
 * 20140322
 */
Ext.define('SeletctQuestionnairePanel',{
	extend:'BasicPanel',
	layout:'border',
	getSelModel : function(){
		return Ext.create('Ext.selection.CheckboxModel',{
    			mode:'SINGLE',
    			allowDeselect:true,
    			listeners:{
    				select:function(th,record,index){
    					Ext.getCmp('questionnairePanelDetailBtnId').enable();
    				},
    				deselect:function(th,record,index){
    					//禁用查看详情
    					Ext.getCmp('questionnairePanelDetailBtnId').disable();
    				}
    			}
		});
	},
	getItems : function(){
		return [{//问卷查询条件form
	       	xtype:'container',
			region:'north',
			layout:'fit',
			items:[Ext.create('QuestionnaireQueryPanel',{id:'questionnaireQueryPanelForPlan'})]
		},{
			xtype:'container',
			region:'center',
			layout:'border',
			items:[
				{//选择问卷查询按钮面板
					xtype:'container',
					region:'north',
				    layout:'fit',
				    items:[Ext.create('QuestionnaireBtnPanelForPlan')]
				},{//问卷管理查询结果表格
					xtype:'container',
					region:'center',
		    	    layout:'fit',
		    	    items:[ Ext.create('QuestionnaireInfoListGrid',{
		    	    	id:'questionnaireInfoListGridForPlan',
		    	    	selModel : Ext.create('Ext.selection.CheckboxModel',{
		        			mode:'SINGLE',
		        			allowDeselect:true,
		        			listeners:{
		        				select:function(th,record,index){
		        					if(!Ext.isEmpty(record)){
		        						Ext.getCmp('questionnairePanelDetailBtnId').enable();
		        					}
		        				},
		        				deselect:function(th,record,index){
		        					//禁用查看详情
		        					Ext.getCmp('questionnairePanelDetailBtnId').disable();
		        				}
		        			}
		    	    	}),
		    	    	listeners:{
		    	    		itemdblclick:function(th,record,item,index,e,eOpts){
		    	    			if(questionnaireStatusTypeForCombobox === 'SPECIAL'){
		    	    				if(record.get('status') === 'WAITINGVALID' ||
			    	    					record.get('status') === 'OVERDUE'){
			    	    				MessageUtil.showErrorMes('抱歉，待生效或者过期状态下的问卷不允许使用在计划中！');
			    	    				return false;
			    	    			}
//			    	    			
			    	    			//为选择问卷控件设置store
			    	    			th.up('window').parent.getForm().findField('questionnaireId').store.removeAll();
			    	    			th.up('window').parent.getForm().findField('questionnaireId').store.add(record);
			    	    			th.up('window').parent.getForm().findField('questionnaireId').setValue(record.get('id'));
			    	    			//为问卷的生效时间和失效时间赋值
			    	    			th.up('window').parent.getForm().findField('effectiveTime').reset();
			    	    			th.up('window').parent.getForm().findField('invalidTime').reset();
			    	    			th.up('window').parent.getForm().findField('effectiveTime').setValue(record.get('effectiveTime'));
			    	    			th.up('window').parent.getForm().findField('invalidTime').setValue(record.get('invalidTime'));
			    	    			th.up('window').hide();
		    	    			}else{
		    	    				
			    	    			//为选择问卷控件设置store
			    	    			th.up('window').parent.getForm().findField('questionnaireId').store.removeAll();
			    	    			th.up('window').parent.getForm().findField('questionnaireId').store.add(record);
			    	    			th.up('window').parent.getForm().findField('questionnaireId').setValue(record.get('id'));
			    	    			th.up('window').hide();
		    	    			}
		    	    			
		    	    		}
		    	    	},
		    	    })]
		        }
		   ]
		}]
	},
	initComponent:function()
	{
		var me = this;
		me.items = me.getItems();
		this.callParent();
	}
});

/**
 * 选择问卷弹出框窗体window
 * 肖红叶
 * 20140322
 */
Ext.define('SeletctQuestionnaireWindow',{
	extend:'PopWindow',
	alias : 'widget.basicwindow',
	width:700,
	height:450,
	modal:true,
	layout:'fit',
	title:'选择问卷',
	closeAction:'destroy',
	initComponent:function()
	{
		var me = this;
		me.items = [Ext.create('SeletctQuestionnairePanel')];
		this.callParent();
	},
	listeners:{
		hide:function(){
			document.body.scrollLeft=0;
			document.body.scrollTop=0;
			document.getElementsByTagName("html")[0].style.overflowY="hidden";
			document.getElementsByTagName("html")[0].style.overflowX="hidden";
		}
	}
});

/**
 * 计划管理主页面查询条件表单
 * xiaohongye
 * 最后修改时间： 2014-3-14 
 * 修改原因：开发维护计划合并
 */
Ext.define('CustomerDevelopPlaneForm',{//********修改日期控件布局
	extend:'SearchFormPanel',
	id:'customerDevelopPlaneForm',
	planeType:null,
	layout : {
		type : 'table',
		columns : 6
	},
	items:null,
	defaults: {
	    	margin:'0 5 4 5',
	    	labelWidth:80,
	    	width : 250,
	    	labelAlign: 'right'
	},
	defaultType:'combobox',
	getItems:function(){
		var me = this;
		return [{
			xtype:'textfield',
			fieldLabel:i18n('i18n.developPlan.planeName'),
			id:'planeName',
			name:'planeName',
//			width:200,
			colspan:2
		},{
			fieldLabel:i18n('i18n.developPlan.planeDraft'),
			id:'planeDraft',
			name:'planeDraft',
			store:Ext.create('UserStore',{
				id:'planeDraftStore',
				autoLoad:true
			}),
			queryMode: 'local',
			minChars:2,
			queryDelay:800,
			colspan:2,
			triggerAction : 'all',
			displayField:'empName',
			queryParam : 'planeDraft',
			valueField:'id',
			forceSelection :true,
			//剔出朦层
			listConfig: {
  				  loadMask:false
			},
			listeners:{
				change:DButil.comboSelsct
			}
		},/**
         * 在计划管理里面添加计划类别查询条件
         * auth:盛诗庆
         * date:2014-03-24
         */
        {
            fieldLabel: i18n('i18n.returnVisit.planType'),
            name : 'projectType',
            xtype : 'combo',
            store:getDataDictionaryByName(DataDictionary,'PLAN_TYPE'),
            queryMode:'local',
            displayField:'codeDesc',
            valueField:'code',
            forceSelection :true,
            colspan:2,
            listeners:{
                change:DButil.comboSelsct
            }
        },{
			fieldLabel:i18n('i18n.developPlan.executeDept'),
			name:'executeDept',
			store:Ext.create('DeptStore',{id:'queryDeptStoreId',
				listeners:{
					beforeload:function(store, operation, eOpts){
						Ext.apply(operation,{
							params : {
								'deptName':Ext.getCmp("customerDevelopPlaneForm").getForm().findField("executeDept").getRawValue()
								}
							}
						);	
					}
				}
			}),
			//queryMode: 'local',
			colspan:2,
			triggerAction : 'all',
			displayField:'deptName',
			valueField:'id',
			forceSelection :true,
//			selectOnFocus:false,
			hideTrigger:false,
			emptyText:i18n('i18n.customerGroup.deptKeyWord'),
			pageSize: 10,
			minChars:2,
			queryDelay:800,
//			typeAhead:true,//根据输入框值自动补全
			listConfig: {
  	        	minWidth :280,
  	            getInnerTpl: function() {
  	            	 return '{deptName}';
  	            }
  	        },
			listeners:{
				change:DButil.comboSelsct,
				select:function(th,records){
					Ext.getCmp("customerDevelopPlaneForm").getForm().findField("execuserid").store.load({params:{executeDept:th.value}});
					Ext.getCmp("customerDevelopPlaneForm").getForm().findField("planeDraft").store.load({params:{executeDept:th.value}});
				}
			}
		},{
            xtype:          'combo',
          	fieldLabel: i18n('i18n.developPlan.executePersion'),
            name:'execuserid',
			store:Ext.create('UserStore',{
				id:'queryUserStoreId',
				autoLoad:true
			}),
			queryMode: 'local',
			triggerAction : 'all',
			displayField:'empName',
			valueField:'id',
			colspan:2,
			//剔出朦层
			listConfig: {
  				  loadMask:false
			},
			forceSelection :true,
			listeners:{
				change:DButil.comboSelsct
			}
        },{ //选择问卷-盛诗庆-20140324
			fieldLabel:'选择问卷', 
			xtype : "queryCombox",
			store:Ext.create('Ext.data.Store',{
				model:'QuestionnaireInfoModel'
			}),
            maxLength:170,
            colspan:2,
			queryMode : "local",
			editable:true,
			forceSelection : true,
			listeners:{
				change : DButil.comboSelsct,
				expand:function(){
					//此时选择问卷时能筛选出所有状态的问卷
					questionnaireStatusTypeForCombobox = 'NORMAL';
					var seletctQuestionnaireWindow = Ext.getCmp("seletctQuestionnaireWindowId");//获取win
					if(!seletctQuestionnaireWindow){
						seletctQuestionnaireWindow = Ext.create('SeletctQuestionnaireWindow',{id:'seletctQuestionnaireWindowId','parent':me});
					}
					//重置查询条件和清空问卷结果
					Ext.getCmp('questionnaireInfoListGridForPlan').store.removeAll();
					Ext.getCmp('questionnaireQueryPanelForPlan').getForm().reset();
					Ext.getCmp('questionnaireInfoListGridForPlan').scrollByDeltaX(-1);
					seletctQuestionnaireWindow.show();
					
				}
			},
			displayField : "questionnaireName",
			valueField : "id",
			name:'questionnaireId'
		},{
			xtype : 'datefield',
			name:'createStartTime',
			id:'createStartTime',
			editable : false,
			fieldLabel : i18n('i18n.Maintainp.startEnd'),
			format : 'Y-m-d',
			colspan:2,
			allowBlank:false
		},{
			xtype : 'datefield',
			name:'createEndTime',
			editable : false,
			id:'createEndTime',
			format : 'Y-m-d',
			fieldLabel : i18n('i18n.CommonView.to'),
			allowBlank:false,
			colspan:2
		}];
	},
	initComponent:function()
	{
		var me = this;
		me.items = me.getItems();
		this.callParent();
	}
});

/**
 * 查询页面列表grid,开发和日程公用改store
 */

Ext.define('CustomerDevelopPlaneGird', {
	extend:'SearchGridPanel',
	id:'customerDevelopPlaneGird',
	store :null,
	columnLines:true,
	plugins:null,
	selModel : Ext.create('Ext.selection.CheckboxModel',{mode:'MULTI'}),
	columns:null,
	viewConfig: { 
   	 	forceFit:true 
    }, 
	initComponent:function(){
		var me = this;
		this.dockedItems=[{
			xtype:'pagingtoolbar',
			store:me.store,
			dock:'bottom',
			displayInfo : true,
			autoScroll : true,
			items:[{
				text: '每页',
				xtype: 'tbtext'
			},Ext.create('Ext.form.ComboBox', {
               width:          window.screen.availWidth*0.0391,
               value:          '20',
               triggerAction:  'all',
               forceSelection: true,
               editable:       false,
               name:           'comboItem',
               displayField:   'value',
               valueField:     'value',
               queryMode:      'local',
               store : Ext.create('Ext.data.Store',{
                   fields : ['value'],
                   data   : [
                       {'value':'10'},
                       {'value':'15'},
                       {'value':'20'},
                       {'value':'25'},
                       {'value':'40'},
                       {'value':'100'}
                   ]
               }),
               listeners:{
					select : {
			            fn: function(_field,_value){
			            	var pageSize = me.store.pageSize;
			            	var newPageSize = parseInt(_field.value);
			            	if(pageSize!=newPageSize){
			            		me.store.pageSize = newPageSize;
			            		this.up('pagingtoolbar').moveFirst();
			            	}
			            }
			        }
               }
           }),{
				text: '条',
				xtype: 'tbtext'
           }]
		}];
		this.callParent();
	}
});


/**
 * <p>
 * 客户分组管理
 * </p>
 * @author  张登
 * @date    2012-03-13
 */
Ext.define('CustomerGroupFormPanel',{
	extend:'SearchFormPanel',  
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
				columns:3
			},
			defaultType:'textfield',
			defaults:{
				labelWidth:70,
				width:210
			},
			items:[{//部门名称
				fieldLabel : i18n('i18n.PotentialCustManagerView.deptName'),
				name:'deptId',
				xtype:'combo',
				store:Ext.create('DeptStore',{
					listeners:{
						beforeload:function(store, operation, eOpts){
							Ext.apply(operation,{
								params : {
									'deptName':Ext.getCmp("CustomerGroupFormPanelId").getForm().findField("deptId").getRawValue()
									}
								}
							);	
						}
					}
				}),
				triggerAction : 'all',
				displayField:'deptName',
				valueField:'id',
                //部门Level
                deptLevel:null,
				forceSelection :true,
				hideTrigger:false,
				emptyText:i18n('i18n.customerGroup.deptKeyWord'),
				pageSize: 10,
				minChars:2,
				queryDelay:800,
				listConfig: {
	  	        	minWidth :300,
	  	            getInnerTpl: function() {
	  	            	 return '{deptName}';
	  	            }
	  	        },
				listeners:{
					change:DButil.comboSelsct,
                    select:function(th,records){
                        if(!Ext.isEmpty(Ext.getCmp("chooseCycleListGridId")) && !Ext.isEmpty(Ext.getCmp("cycleListGridId"))){
                            if(Ext.getCmp("chooseCycleListGridId").store.getCount()>0 || Ext.getCmp("cycleListGridId").store.getCount()>0){
                            	MessageUtil.showQuestionMes(i18n('i18n.customerGroup.changeDeptWillClearAll'), function(e){ 
                                    if (e == 'yes') {
                                        //清除已选客户
                                        Ext.getCmp("chooseCycleListGridId").store.removeAll();
                                        //清除客户列表
                                        Ext.getCmp("cycleListGridId").store.removeAll();
                                        //部门Level
                                        th.deptLevel=records[0].get('deptLevel');
                                        if(!Ext.isEmpty(Ext.getCmp("fUserId"))){
                                            //设置维护部门Name
//                                            Ext.getCmp("showDeptName").setValue(th.getRawValue());
                                            //设置维护部门Id
                                            Ext.getCmp("showDeptId").setValue(th.getValue());
                                            //部门Level
                                            Ext.getCmp("deptLevel").setValue(th.deptLevel);
                                            //加载分配维护人
                                            Ext.getCmp("fUserId").store.load({params:{executeDept:th.value}});
                                            //已有分组
                                            Ext.getCmp("formGroupId").store.load({params:{deptId:th.value}});
                                            //加载维护人
                                            Ext.getCmp("userId").store.load({params:{deptId:th.value}});
                                        }
                                        //设置已经和可选客户
                                        setCount(0);
                                        th.oldValue=th.getValue();
                                    }else{
                                        th.setValue(th.oldValue);
                                    }
                                },function(){
                                	th.setValue(th.oldValue);
                                });
                            }else{
                                th.oldValue=th.getValue();
                                th.deptLevel=records[0].get('deptLevel');
                                if(!Ext.isEmpty(Ext.getCmp("fUserId"))){
                                    //设置维护部门Name
//                                    Ext.getCmp("showDeptName").setValue(th.getRawValue());
                                    //设置维护部门Id
                                    Ext.getCmp("showDeptId").setValue(th.getValue());
                                    //部门Level
                                    Ext.getCmp("deptLevel").setValue(th.deptLevel);
                                    //加载分配维护人
                                    Ext.getCmp("fUserId").store.load({params:{executeDept:th.value}});
                                    //已有分组
                                    Ext.getCmp("formGroupId").store.load({params:{deptId:th.value}});
                                    //加载维护人
                                    Ext.getCmp("userId").store.load({params:{deptId:th.value}});
                                }
                            }
                        }
                    }
				}
			}
			,{//已有客户组
                xtype:          'combo',
                triggerAction:  'all',
                fieldLabel: i18n('i18n.customerGroup.groupId'),
                name: 'groupId',
                id:'formGroupId',
				displayField:'groupName',
				valueField:'id',
                queryMode: 'local', 
				forceSelection :true,
				listConfig: {
      		        loadMask:false
    			},
				listeners:{
					change:DButil.comboSelsct,
					select:function(th){
						if(!Ext.isEmpty(th.getValue())){
							var params = {
								'customerGroupId' : th.getValue(),
                                'deptId':Ext.getCmp("showDeptId").getValue()
							};
							var successFn = function(json){
								if(!Ext.isEmpty(json) && !Ext.isEmpty(json.userId)){
									Ext.getCmp("userId").setValue(json.userId);	
								}else{
									Ext.getCmp("userId").setValue("");
								}
							};
							var failureFn = function(json){
								MessageUtil.showErrorMes(json.message);
							};
							customerDevelopPlane.searchPrehumanByGroupId(params,successFn,failureFn);
						}
					}
				},
                store:Ext.create('CustomerGroupStore')
            },{//维护人
				fieldLabel:i18n('i18n.Cycle.userId'),
				id:'userId',
				name:'empName',
				xtype:          'combo',
                mode:           'local',
				store:Ext.create('PrehumanStore',{autoLoad:true}),
				queryMode: 'local',
				triggerAction : 'all',
				displayField:'empName',
				queryParam : 'planeDraft',
				valueField:'id',
				forceSelection :true,
				listConfig: {
      		        loadMask:false
    			},
				listeners:{
					change:DButil.comboSelsct,
					select:function(th){
						if(!Ext.isEmpty(th.getValue())){
							var params = {
								'userId' : th.getValue(),
                                'deptId':Ext.getCmp("showDeptId").getValue()
							};
							var successFn = function(json){
								if(!Ext.isEmpty(json) && !Ext.isEmpty(json.customerGroupId)){
									Ext.getCmp("formGroupId").setValue(json.customerGroupId);	
								}else{
									Ext.getCmp("formGroupId").setValue("");
								}
							};
							var failureFn = function(json){
								MessageUtil.showErrorMes(json.message);
							};
							customerDevelopPlane.serachCustomerGroupByPrehuman(params,successFn,failureFn);
						}
					}
				}
			},{//客户名称
                name : 'custName',
                fieldLabel: i18n('i18n.customerGroup.custName'),
                maxLength :20,
				maxLengthText : i18n('i18n.developPlan.maxLength')+20
            },{//客户编码
                name : 'custNumber',
                fieldLabel: i18n('i18n.customerGroup.custNumber'),
                maxLength :20,
				maxLengthText : i18n('i18n.developPlan.maxLength')+20,
				listeners:{
					change:function(t){
						if(!Ext.isEmpty(t.getValue())){
							if(!Ext.isEmpty(Ext.getCmp('CustomerGroupFormPanelId'))){
								Ext.getCmp('CustomerGroupFormPanelId').getForm().findField('groupId').disable();	
								Ext.getCmp('CustomerGroupFormPanelId').getForm().findField('empName').disable();
							}
						}else{
							if(!Ext.isEmpty(Ext.getCmp('CustomerGroupFormPanelId'))){
								Ext.getCmp('CustomerGroupFormPanelId').getForm().findField('groupId').enable();	
								Ext.getCmp('CustomerGroupFormPanelId').getForm().findField('empName').enable();
							}
						}
					}
				}
            },{//查询日期
    			xtype : 'datefield',
    			name:'queryDate',
    			editable : false,
    			disabled:true,
    			fieldLabel:i18n('i18n.customerGroup.queryDate'),
    			format : 'Y-m-d',
    			value : new Date()
    		},{
    			xtype:'hiddenfield',
    			id:'showDeptId'
    		}]		
		}];
	}
});

/**
 * 客户分组管理grid
 */
Ext.define('CycleListGrid',{
	extend:'PopupGridPanel',   
	initComponent:function(){             
		var me = this;
		me.columns = [{
			xtype:'rownumberer',
			width:40,
			align:'center',
			header:i18n('i18n.Cycle.rownum')
		},{    
			header : i18n('i18n.customerGroup.custNumber'),
			dataIndex:'custNumber'
		},{
			header : i18n('i18n.customerGroup.custName'),
			dataIndex:'custName'
		},{
			header : i18n('i18n.customerGroup.custId'),
			dataIndex:'custId'
		},{
			header : i18n('i18n.customerGroup.empName'),
			dataIndex:'empName'
		},{
			header : i18n('i18n.Cycle.sendGoodCycle'),
			dataIndex:'sendGoodCycle'
		},{
			header : i18n('i18n.Cycle.monthAmount'),
			dataIndex:'monthAmount'
		},{
			header : i18n('i18n.Cycle.month1Amount'),
			dataIndex:'month1Amount'
		},{
			header :  i18n('i18n.Cycle.month2Amount'),
			dataIndex:'month2Amount'
		}];
		this.callParent();
	}
});
function controlForm(form,flag)
{
	if(flag){
		form.findField('overArrivalVotes').reset();
		form.findField('beginArrivalVotes').reset();
		form.findField('overShipVotes').reset();
		form.findField('beginShipVotes').reset();
		form.findField('overArrivalAmount').reset();
		form.findField('beginArrivalAmount').reset();
		form.findField('overShipAmount').reset();
		form.findField('beginShipAmount').reset();
		form.findField('overArrivalWeight').reset();
		form.findField('beginArrivalWeight').reset();
		form.findField('overShipWeight').reset();
		form.findField('beginShipWeight').reset();
		form.findField('searchOverTime').reset();
		form.findField('searchBeginTime').reset();
	}
	form.findField('overArrivalVotes').setReadOnly(flag);
	form.findField('beginArrivalVotes').setReadOnly(flag);
	form.findField('overShipVotes').setReadOnly(flag);
	form.findField('beginShipVotes').setReadOnly(flag);
	form.findField('overArrivalAmount').setReadOnly(flag);
	form.findField('beginArrivalAmount').setReadOnly(flag);
	form.findField('overShipAmount').setReadOnly(flag);
	form.findField('beginShipAmount').setReadOnly(flag);
	form.findField('overArrivalWeight').setReadOnly(flag);
	form.findField('beginArrivalWeight').setReadOnly(flag);
	form.findField('overShipWeight').setReadOnly(flag);
	form.findField('beginShipWeight').setReadOnly(flag);
	form.findField('searchOverTime').setReadOnly(flag);
	form.findField('searchBeginTime').setReadOnly(flag);
}