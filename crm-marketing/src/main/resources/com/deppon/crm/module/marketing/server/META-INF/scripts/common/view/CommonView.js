/**
 * <p>
 * 开发计划新增修改查询条件Form
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
                fieldLabel: '部门',
                xtype:'combo',
                name:'execdeptid',
                store:Ext.create('DeptStore',{
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
                    select:function(th,records){
                        if(!Ext.isEmpty(Ext.getCmp("ChooseCustomerGridId"))){
                            if(Ext.getCmp("ChooseCustomerGridId").store.getCount()>0){
                            	MessageUtil.showQuestionMes("更换部门后会重置已选择客户，是否继续", function(e){ 
                                    if (e == 'yes') {
                                        Ext.getCmp("ChooseCustomerGridId").store.removeAll();
                                        if(!Ext.isEmpty(Ext.getCmp("developPlanFromPanel"))){
                                            Ext.getCmp("developPlanFromPanel").getForm().findField("execdeptid").setValue(th.getRawValue());
                                            Ext.getCmp("developPlanFromPanel").getForm().findField("execuserid").store.load({params:{executeDept:th.value}});
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
                                if(!Ext.isEmpty(Ext.getCmp("developPlanFromPanel"))){
                                    Ext.getCmp("developPlanFromPanel").getForm().findField("execdeptid").setValue(th.getRawValue());
                                    Ext.getCmp("developPlanFromPanel").getForm().findField("execuserid").store.load({params:{executeDept:th.value}});
                                }
                            }
                        }
                    }
                }
            },{
                xtype:          'combo',
                mode:           'local',
                triggerAction:  'all',
                colspan : 3,
                fieldLabel:     i18n('i18n.PotentialCustManagerView.custType'),
                value:POTENTIAL_CUSTOMER,
                name:           'custType',
                displayField:   'codeDesc',
                valueField:     'code',
                queryMode: 'local',
                store:getDataDictionaryByName(DataDictionary,'CUST_TYPE'),
                forceSelection :true,
                listeners:{
                    change:DButil.comboSelsct,
                    select:function(t){
                        if(t.getValue()==POTENTIAL_CUSTOMER){
                            var searchScatterForm = Ext.getCmp("SearchConditionFormId");
                            //发货量
                            searchScatterForm.getForm().findField('beginShipWeight').disable();
                            searchScatterForm.getForm().findField('overShipWeight').disable();
                            //发货票数
                            searchScatterForm.getForm().findField('beginShipVotes').disable();
                            searchScatterForm.getForm().findField('overShipVotes').disable();
                            //发货金额
                            searchScatterForm.getForm().findField('beginShipAmount').disable();
                            searchScatterForm.getForm().findField('overShipAmount').disable();
                            //到达货量
                            searchScatterForm.getForm().findField('beginArrivalWeight').disable();
                            searchScatterForm.getForm().findField('overArrivalWeight').disable();
                            //到达票数
                            searchScatterForm.getForm().findField('beginArrivalVotes').disable();
                            searchScatterForm.getForm().findField('overArrivalVotes').disable();
                            //到达金额
                            searchScatterForm.getForm().findField('beginArrivalAmount').disable();
                            searchScatterForm.getForm().findField('overArrivalAmount').disable();
                            
                            //客户属性
                            searchScatterForm.getForm().findField('custProperty').disable();
                            
                        }else{
                            var searchScatterForm = Ext.getCmp("SearchConditionFormId");
                            //发货量
                            searchScatterForm.getForm().findField('beginShipWeight').enable();
                            searchScatterForm.getForm().findField('overShipWeight').enable();
                            //发货票数
                            searchScatterForm.getForm().findField('beginShipVotes').enable();
                            searchScatterForm.getForm().findField('overShipVotes').enable();
                            //发货金额
                            searchScatterForm.getForm().findField('beginShipAmount').enable();
                            searchScatterForm.getForm().findField('overShipAmount').enable();
                            //到达货量
                            searchScatterForm.getForm().findField('beginArrivalWeight').enable();
                            searchScatterForm.getForm().findField('overArrivalWeight').enable();
                            //到达票数
                            searchScatterForm.getForm().findField('beginArrivalVotes').enable();
                            searchScatterForm.getForm().findField('overArrivalVotes').enable();
                            //到达金额
                            searchScatterForm.getForm().findField('beginArrivalAmount').enable();
                            searchScatterForm.getForm().findField('overArrivalAmount').enable();
                            
                            //客户属性
                            searchScatterForm.getForm().findField('custProperty').enable();
                            
                        }
                    }
                }
            },{//客户来源
                xtype:          'combo',
                mode:           'local',
                triggerAction:  'all',
                colspan : 3,
                fieldLabel:     i18n('i18n.PotentialCustManagerView.custSource'),
                value:'',
                name:           'custbase',
                displayField:   'codeDesc',
                valueField:     'code',
                queryMode: 'local',
                store:getDataDictionaryByName(DataDictionary,'CUST_SOURCE'),
                forceSelection :true,
                listeners:{
                    change:DButil.comboSelsct
                }
            }, {
                fieldLabel : i18n('i18n.ScatterCustManagerView.custAttribute'),
                xtype : 'combo',
                colspan : 3,
                name : 'custProperty',
                disabled:true,
                store:getDataDictionaryByName(DataDictionary,'CUSTOMER_NATURE'),
                queryMode:'local',
                displayField:'codeDesc',
                valueField:'code',
                forceSelection :true,
                listeners:{
                    change:DButil.comboSelsct
                }
            },{
                fieldLabel : i18n('i18n.PotentialCustManagerView.customerName'),
                colspan : 3,
                name : 'custName',
                maxLength : 20,
                maxLengthText : i18n('i18n.developPlan.maxLength')+20
            }, {
                fieldLabel : i18n('i18n.PotentialCustManagerView.contactName'),
                colspan : 3,
                name : 'linkManName',
                maxLength : 20,
                maxLengthText : i18n('i18n.developPlan.maxLength')+20
            }, {
                fieldLabel : i18n('i18n.PotentialCustManagerView.contactPhone'),
                colspan : 3,
                name : 'linkManMobile',
                minLength:8,
                maxLength : 11,
                maxLengthText : i18n('i18n.developPlan.maxLength')+11,
                regexText:i18n('i18n.ScatterCustManagerView.message_rightString'),
                //配合香港开点：手机号长度为8位数字，或是首位为1的11位数字
                regex : /(^1\d{10}$)|(^\d{8}$)/
            }, {
                fieldLabel : i18n('i18n.PotentialCustManagerView.contactTel'),
                colspan : 3,
                name : 'linkManPhone',
                maxLength : 20,
                maxLengthText : i18n('i18n.developPlan.maxLength')+20
//              regexText:i18n('i18n.ScatterCustManagerView.message_rightString'),
//              regex : /^\d{1,15}$/
            }, {
                fieldLabel : i18n('i18n.PotentialCustManagerView.goodsPotential'),
                colspan : 3,
                name : 'volumePotential',
                xtype : 'combobox',
                store:getDataDictionaryByName(DataDictionary,'CARGO_POTENTIAL'),
                queryMode:'local',
                displayField:'codeDesc',
                valueField:'code',
                forceSelection :true,
                listeners:{
                    change:DButil.comboSelsct
                }
            }, {
                fieldLabel : i18n('i18n.PotentialCustManagerView.bizStatus'),
                colspan : 3,
                xtype : 'combo',
                name : 'bussinesState',
                store:getDataDictionaryByName(DataDictionary,'BUSINESS_STATUS'),
                queryMode:'local',
                displayField:'codeDesc',
                valueField:'code',
                forceSelection :true,
                listeners:{
                    change:DButil.comboSelsct
                }
            }, {
                fieldLabel : i18n('i18n.PotentialCustManagerView.searchStartTime'),
                colspan : 3,
                xtype : 'datefield',
                format : 'Y-m-d',
                editable: false,
                name : 'beginTime',
                id:'beginTime',
//              value : DButil.getMonthStartDate()
                value : new Date(new Date().setMonth(new Date().getMonth() - 3))
            }, {xtype: 'fieldcontainer',
                colspan : 3,
                collapsible: true,
                layout:'column',
                defaults: {
                    hideLabel: true
                },
                items: [ 
                    {xtype: 'displayfield',width : 70, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
                    {
                        xtype: 'datefield',
                        width : 120,
                        format : 'Y-m-d',
                        name : 'overTime',
                        id:'overTime',
                        value : new Date(),
                        editable:false
                    }]
                },{
                //二级行业
                xtype:'customertrade',
                id:'firstTradeAndSecondTradeId',
                colspan:6,
                userType:'SEARCH',
                fieldLabel:i18n('i18n.PotentialCustManagerView.industry'),
                width:390,
                trade_labelWidth:55,
                trade_margin:'-5px 0 0 0',
                second_trade_margin:'-5px 0 0 0',
                trade_width:180,
                trade_name:'trade',
                trade_fieldname:i18n('i18n.PotentialCustManagerView.firstTrade'),
                second_trade_labelWidth:70,
                second_trade_width:195,
                second_trade_name:'secondTrade',
                second_trade_fieldname:i18n('i18n.PotentialCustManagerView.secondTrade')
            },{ //合作意向
                fieldLabel : i18n('i18n.PotentialCustManagerView.coopIntention'),
                colspan : 3,
                xtype : 'combo',
                name : 'coopIntention',
                store:getDataDictionaryByName(DataDictionary,'COOPERATION_INTENTION'),
                queryMode:'local',
                displayField:'codeDesc',
                valueField:'code',
                forceSelection :true,
                listeners:{
                    change:DButil.comboSelsct
                }
            },
            /**
             * auth:李春雨
             * date:2014-01-11
             * 增加业务类别选择框
             */
            {
                //业务类别
                fieldLabel : i18n('i18n.PotentialCustManagerView.businessType'),
                name : 'businessType',
                xtype : 'combo',
                colspan : 3,
                editable : false,
                store:getDataDictionaryByName(DataDictionary,'BUSINESS_TYPE'),
                queryMode:'local',
                displayField:'codeDesc',
                allowBlank:false,
                valueField:'code',
                value : 'LESS_THAN_TRUCKLOAD',
                forceSelection :true,
                listeners:{
                    change:DButil.comboSelsct
                }
            }
            ,{//出发货量
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
                    width : 125,
                    maxLength : 10,
                    disabled:true,
                    maxLengthText : i18n('i18n.developPlan.maxLength')+10,
                    name : 'beginShipWeight'
                },
                {xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
                {
                    fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
                    name : 'overShipWeight',
                    maxLength : 10,
                    disabled:true,
                    maxLengthText : i18n('i18n.developPlan.maxLength')+10,
                    hideLabel: true,
                    width : 55,
                    labelWidth : 10
                } ]
            }, {
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
                    fieldLabel : i18n('i18n.ScatterCustManagerView.beginShipVotes'),
                    name : 'beginShipVotes',
                    labelWidth : 65,
                    width : 125,
                    maxLength : 10,
                    disabled:true,
                    maxLengthText : i18n('i18n.developPlan.maxLength')+10,
                    regexText:i18n('i18n.ScatterCustManagerView.message_rightString'),
                    regex : /^\d{0,10}$/
                }, 
                {xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
                {
                    fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
                    name : 'overShipVotes',
                    maxLength : 10,
                    disabled:true,
                    maxLengthText : i18n('i18n.developPlan.maxLength')+10,
                    hideLabel: true,
                    width : 55,
                    labelWidth : 10,
                    regex : /^\d{0,10}$/,
                    regexText:i18n('i18n.ScatterCustManagerView.message_rightString')
                } ]
            }, {
                xtype : 'fieldcontainer',
                colspan : 6,
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
                    maxLength : 10,
                    disabled:true,
                    maxLengthText : i18n('i18n.developPlan.maxLength')+10,
                    width : 125
                }, 
                {xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
                {
                    fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
                    name : 'overShipAmount',
                    maxLength : 10,
                    disabled:true,
                    maxLengthText : i18n('i18n.developPlan.maxLength')+10,
                    hideLabel: true,
                    width : 55,
                    labelWidth : 10
                } ]
            },{ //到达货量
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
                    labelWidth : 65,
                    maxLength : 10,
                    disabled:true,
                    maxLengthText : i18n('i18n.developPlan.maxLength')+10,
                    width : 125
                }, 
                {xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
                {
                    fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
                    name : 'overArrivalWeight',
                    maxLength : 10,
                    disabled:true,
                    maxLengthText : i18n('i18n.developPlan.maxLength')+10,
                    hideLabel: true,
                    width : 55,
                    labelWidth : 10
                } ]
            }, {//到达票数
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
                    fieldLabel : i18n('i18n.ScatterCustManagerView.beginArrivalVotes'),
                    name : 'beginArrivalVotes',
                    labelWidth : 65,
                    maxLength : 10,
                    disabled:true,
                    maxLengthText : i18n('i18n.developPlan.maxLength')+10,
                    width : 125,
                    regexText:i18n('i18n.ScatterCustManagerView.message_rightString'),
                    regex : /^\d{0,10}$/
                }, 
                {xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
                {
                    fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
                    name : 'overArrivalVotes',
                    hideLabel: true,
                    maxLength : 10,
                    disabled:true,
                    maxLengthText : i18n('i18n.developPlan.maxLength')+10,
                    width : 55,
                    labelWidth : 10,
                    regexText:i18n('i18n.ScatterCustManagerView.message_rightString'),
                    regex : /^\d{0,10}$/
                } ]
            }, {//到达金额
                xtype : 'fieldcontainer',
                colspan : 6,
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
                    maxLength : 10,
                    disabled:true,
                    maxLengthText : i18n('i18n.developPlan.maxLength')+10,
                    labelWidth : 65,
                    width : 125
                },
                {xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
                {
                    fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
                    name : 'overArrivalAmount',
                    hideLabel: true,
                    maxLength : 10,
                    disabled:true,
                    maxLengthText : i18n('i18n.developPlan.maxLength')+10,
                    width : 55,
                    labelWidth : 10
                } ]
            }]
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
                xtype: 'datefield',
                width:195,
                fieldLabel: i18n('i18n.plan.beginTime'),
                editable: false,
                minValue :Ext.Date.add(new Date(), Ext.Date.DAY, 1),
                format: 'Y-m-d',
                value:Ext.Date.add(new Date(), Ext.Date.DAY, 1),
                allowBlank: false
            }, {
                width:140,
                xtype: 'datefield',
                name: 'endTime',
                fieldLabel: i18n('i18n.PotentialCustManagerView.searchEndTime'),
                editable:false,
                labelWidth: 10,
                format: 'Y-m-d',
                labelSeparator:'', 
                minValue :Ext.Date.add(new Date(), Ext.Date.DAY, 1),
                value:Ext.Date.add(new Date(), Ext.Date.DAY, 1),
                allowBlank: false
            },{
                width:210,
                name : 'execdeptid',
                fieldLabel: i18n('i18n.developPlan.executeDept'),
                disabled:true,
                allowBlank: false
            },{
                width:220,
                xtype: 'combo',
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
                fieldLabel: i18n('i18n.developPlan.developTheme'),
                colspan : 2,
                maxLength : 20,
                maxLengthText : i18n('i18n.developPlan.maxLength')+20,
                allowBlank: false
            },{
//                width:420,
                width:210,
                name : 'activedesc',
                fieldLabel: i18n('i18n.plan.activedesc'),
                maxLength : 40,
                maxLengthText : i18n('i18n.developPlan.maxLength')+40,
                colspan : 1
            },
            /**
             * 增加计划类别,在开发计划管理中
             * auth:李春雨
             * date:2014-01-13
             */
            {
                fieldLabel: i18n('i18n.returnVisit.planType'),
                name : 'projectType',
                xtype : 'combo',
                editable : false,
                width : 220,
                store:getDataDictionaryByName(DataDictionary,'PLAN_TYPE'),
                queryMode:'local',
                displayField:'codeDesc',
                valueField:'code',
                forceSelection :true,
                allowBlank : false,
                listeners:{
                    change:DButil.comboSelsct
                }
            },{
                xtype:'hiddenfield',
                name:'id'
            }]      
        }];
    }
});

/**
 * 开发计划管理查询
 */
Ext.define('CustomerDevelopPlaneForm',{
    extend:'SearchFormPanel',
    planeType:null,
    layout : {
        type : 'table',
        columns : 4
    },
    items:null,
    defaults: {
            margin:'0 5 4 5',
            labelWidth:65,
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
//          width:200,
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
            colspan:1,
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
        },
        /**
         * 在开发计划管理里面添加计划类别查询条件
         * auth:李春雨
         * date:2014-01-16
         */
        {
            fieldLabel: i18n('i18n.returnVisit.planType'),
            name : 'projectType',
            xtype : 'combo',
            width : 220,
            store:getDataDictionaryByName(DataDictionary,'PLAN_TYPE'),
            queryMode:'local',
            displayField:'codeDesc',
            valueField:'code',
            forceSelection :true,
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
//          selectOnFocus:false,
            hideTrigger:false,
            emptyText:'请输入部门关键字',
            pageSize: 10,
            minChars:2,
            queryDelay:800,
//          typeAhead:true,//根据输入框值自动补全
            listConfig: {
                minWidth :300,
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
        },{
            xtype : 'datefield',
            name:'createStartTime',
            id:'createStartTime',
            editable : false,
            fieldLabel : i18n('i18n.plan.beginTime'),
            format : 'Y-m-d',
            allowBlank:false,
            colspan:2
//          value : new Date().setDate(new Date().getDate()-15)
        },{
            xtype : 'datefield',
            name:'createEndTime',
            editable : false,
            id:'createEndTime',
            fieldLabel : i18n('i18n.CommonView.to'),
            format : 'Y-m-d',
            allowBlank:false,
//          value : new Date().getDate()+15,
            colspan:2
        },{
            xtype:'textfield',
            name:'planeType',
            id:'planeType',
            fieldLabel:i18n('i18n.developPlan.planeType'),
            hidden:true,
            value:me.planeType,
            readOnly:true
            
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
 * 日程管理查询条件表单
 */ 
Ext.define('CustomerScheduleForm',{
    extend:'SearchFormPanel',
    id:'customerScheduleForm',
    planeType:null,
    layout:{
        type:'table',
        columns:3
    },
    defaults:{
        labelAlign:'right',
        labelWidth:80,
        width:250
    },
    defaultType:'combobox',
    items:null,
    getItems:function(){
        var me = this;
        return [{
            fieldLabel : i18n('i18n.PotentialCustManagerView.deptName'),
            name:'deptName',
            xtype:'combo',
            store:Ext.create('DeptStore',{
                listeners:{
                    beforeload:function(store, operation, eOpts){
                        Ext.apply(operation,{
                            params : {
                                'deptName':Ext.getCmp("customerScheduleForm").getForm().findField("deptName").getRawValue()
                                }
                            }
                        );  
                    }
                }
            }),
//          colspan:3,
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
                select:function(th,records){
                    Ext.getCmp("customerScheduleForm").getForm().findField("executePersion").store.load({params:{executeDept:th.value}});
                    //load计划名称
                    Ext.getCmp("planeName").store.load({params:{
//                        'planType':Ext.getCmp("customerScheduleForm").getForm().findField('scheduleType').getValue(),
                        'currentDeptId':th.getValue()
                    }});
                }
            }
        },{
            fieldLabel:i18n('i18n.developSchedule.planeName'),
            name:'planeName',
            id:'planeName',
            width:250,
            store:Ext.create('DevelopName'),
            queryMode:'local',
            displayField:'topic',
            valueField:'id',
            emptyText : '',
            forceSelection :true,
            //剔出朦层
            listConfig: {
                  loadMask:false
            },
            listeners:{
                change:DButil.comboSelsct
            }
        },{
            /*fieldLabel:i18n('i18n.developSchedule.planeState'),*/
            fieldLabel:i18n('i18n.developSchedule.scheduleState'),
            /*name:'planeState',*/
            name:'scheduleStatus',
            /*id:'planeState',*/
            id:'scheduleStatus',
            store:getDataDictionaryByName(DataDictionary,'SCHEDULE_STATUS'),
            queryMode: 'local',
            displayField:'codeDesc',
            valueField:'code',
            emptyText : '',
            width:250,
            forceSelection :true,
            listeners:{
                change:DButil.comboSelsct
            }
        },{
            fieldLabel:i18n('i18n.developSchedule.executePersion'),
            name:'executePersion',
            id:'executePersion',
            width:250,
            store:Ext.create('UserStore',{autoLoad:true}),
            queryMode: 'local',
            //剔出朦层
            listConfig: {
                  loadMask:false
            },
            forceSelection :true,
            triggerAction : 'all',
            queryParam : 'planeDraft',
            displayField:'empName',
            valueField:'id',
            listeners:{
                change:DButil.comboSelsct
            }
        },{
            xtype:'datefield',
            fieldLabel:i18n('i18n.CommonView.schedule'),
            id:'createStartTime',
            name:'createStartTime',
            editable:false,
            format:'Y-m-d',
            width:250,
            editable:false
        },{
            xtype:'datefield',
            name:'createEndTime',
            fieldLabel:i18n('i18n.CommonView.to'),
            id:'createEndTime',
            editable:false,
            format:'Y-m-d',
            editable:false
        },{
            /*fieldLabel:i18n('i18n.developSchedule.planeState'),*/
            fieldLabel:i18n('i18n.developSchedule.planeType'),
            /*name:'planeState',*/
            name:'projectType',
            /*id:'planeState',*/
            id:'projectType',
            store:getDataDictionaryByName(DataDictionary,'PLAN_TYPE'),
            queryMode: 'local',
            displayField:'codeDesc',
            valueField:'code',
            emptyText : '',
            width:250,
            hidden:true,
            forceSelection :true,
            listeners:{
                change:DButil.comboSelsct
            }
        },{
            xtype:'textfield',
            fieldLabel:i18n('i18n.developSchedule.scheduleType'),
            name:'scheduleType',
            id:'scheduleType',
            hidden:true,
            value:me.planeType,
            readOnly:true,
            allowBlank:false
        }];
    },initComponent:function(){
        var me = this;
        me.items = this.getItems();
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
    cls:'market',
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

