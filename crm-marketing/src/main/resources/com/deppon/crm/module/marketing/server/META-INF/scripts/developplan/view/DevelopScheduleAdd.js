var developScheduleData =  new DevelopScheduleData();//初始化计划新增修改data
Ext.onReady(function() {
    var keys=[
        //客户来源      
        'CUST_SOURCE',
        //客户类型
        'CUST_TYPE',
        // 行业
        'TRADE',
        //二级行业
        'SECOND_TRADE',
        // 商机状态
        'BUSINESS_STATUS',
        // 合作物流
        'COOPERATION_LOGISTICS',
        // 合作意向
        'COOPERATION_INTENTION',
        // 货量潜力
        'CARGO_POTENTIAL',
        // 开发状态
        'DEVELOP_STATUS',
        //客户属性
        'CUSTOMER_NATURE',
        //客户类型
        'CUSTOMER_TYPE',
        //公司性质
        'COMP_NATURE',
        //上一年公司规模
        'FIRM_SIZE',
        // 货量潜力
        'CARGO_POTENTIAL',
        //营销方式
        'DEVELOP_WAY',
        //业务类别
        'BUSINESS_TYPE',
        //计划类别
        'PLAN_TYPE'
    ];
    //初始化业务参数
    initDataDictionary(keys);
    initDeptAndUserInfo();
    var scattercustdetailWindow;
    var potentialcustbasicdetailinfoWin;
    /**
    * 散客基本信息
    */
    Ext.define('BasicScatterCustForm',{
        extend:'BasicFormPanel',
        initComponent:function(){
            this.items = this.getItems();
            this.callParent();
        },
        getItems:function(){
            var me = this;
            return [{
                xtype:'fieldset',
                title:i18n('i18n.ScatterCustManagerView.scatterInfo'),
                layout:{
                    type:'table',
                    columns:3
                },
                defaultType:'textfield',
                defaults:{
                    labelWidth:80,//######
                    width:210
                },
                items:[{
                        fieldLabel:i18n('i18n.PotentialCustManagerView.customerName'),
                        name:'custName',
                        cls:'readonly ',
                        readOnly:true
                       },{
                        fieldLabel:i18n('i18n.PotentialCustManagerView.contactName'),
                        name:'linkManName',
                        cls:'readonly ',
                        readOnly:true
                       },{
                        fieldLabel:i18n('i18n.ScatterCustManagerView.memberNo'),
                        name:'memberNo',
                        cls:'readonly ',
                        readOnly:true
                       },
                       {
                           fieldLabel:i18n('i18n.PotentialCustManagerView.contactTel'),
                           name:'linkManPhone',
                           cls:'readonly ',
                           readOnly:true
                       },{
                        fieldLabel:i18n('i18n.PotentialCustManagerView.contactPhone'),
                        name:'linkManMobile',
                        cls:'readonly ',
                        readOnly:true
                       },{
                        fieldLabel:i18n('i18n.ScatterCustManagerView.idNumber'),
                        name:'idNumber',
                        cls:'readonly ',
                        readOnly:true
                       },{
                        fieldLabel:i18n('i18n.ScatterCustManagerView.companySize'),
                        xtype:'combobox',
                        store:getDataDictionaryByName(DataDictionary,'FIRM_SIZE'),
                        queryMode:'local',
                        forceSelection:true,
                        displayField:'codeDesc',
                        valueField:'code',
                        name:'companySize',
                        cls:'readonly ',
                        readOnly:true
                       },{
                        fieldLabel:i18n('i18n.ScatterCustManagerView.companyProperties'),
                        xtype:'combobox',
                        store:getDataDictionaryByName(DataDictionary,'COMP_NATURE'),
                        queryMode:'local',
                        forceSelection:true,
                        displayField:'codeDesc',
                        valueField:'code',
                        name:'companyNature',
                        cls:'readonly ',
                        readOnly:true
                       },{
                        fieldLabel:i18n('i18n.ScatterCustManagerView.taxId'),
                        name:'taxregistNo',
                        cls:'readonly ',
                        readOnly:true
                       },{
                        fieldLabel:i18n('i18n.developSchedule.position'),
                        name:'post',
                        cls:'readonly ',
                        readOnly:true
                       },{
                        fieldLabel:i18n('i18n.PotentialCustEditView.city'),
                        name:'cityName',
                        cls:'readonly ',
                        readOnly:true
                       }
                       ,{
                        fieldLabel:i18n('i18n.ScatterCustManagerView.custAttribute'),
                        xtype:'combobox',
                        store:getDataDictionaryByName(DataDictionary,'CUSTOMER_NATURE'),
                        queryMode:'local',
                        forceSelection:true,
                        displayField:'codeDesc',
                        valueField:'code',
                        name:'custNature',
                        readOnly:true,
                        cls:'readonly ',
                        readOnly:true
                       },{
                        fieldLabel:i18n('i18n.PotentialCustManagerView.bizStatus'),
                        xtype:'combobox',
                        store:getDataDictionaryByName(DataDictionary,'BUSINESS_STATUS'),
                        queryMode:'local',
                        forceSelection:true,
                        displayField:'codeDesc',
                        valueField:'code',
                        name:'bussinesState',
                        cls:'readonly ',
                        readOnly:true
                       },{
                        fieldLabel:i18n('i18n.ScatterCustManagerView.custProperty'),
                        colspan:2,
                        xtype:'combobox',
                        store:getDataDictionaryByName(DataDictionary,'CUSTOMER_TYPE'),
                        queryMode:'local',
                        forceSelection:true,
                        displayField:'codeDesc',
                        valueField:'code',
                        name:'custProperty',
                        cls:'readonly ',
                        readOnly:true
                       },{//一级行业
                          fieldLabel:i18n('i18n.PotentialCustManagerView.firstTrade'),
                          xtype:'combobox',
                          store:getDataDictionaryByName(DataDictionary,'TRADE'),
                          queryMode:'local',
                          forceSelection:true,
                          displayField:'codeDesc',
                          valueField:'code',
                          name:'trade',
                          cls:'readonly ',
                          readOnly:true
                       },{//二级行业
                        fieldLabel:i18n('i18n.PotentialCustManagerView.secondTrade'),
                        colspan:2,
                        xtype:'combobox',
                        store:getDataDictionaryByName(DataDictionary,'SECOND_TRADE'),
                        queryMode:'local',
                        forceSelection:true,
                        displayField:'codeDesc',
                        valueField:'code',
                        name:'secondTrade',
                        cls:'readonly ',
                        readOnly:true
                       },{
                        fieldLabel:i18n('i18n.PotentialCustEditView.address'),
                        colspan:3,
                        width:630,
                        name:'address',
                        cls:'readonly ',
                        readOnly:true
                       },{
                        fieldLabel:i18n('i18n.returnVisit.remark'),
                        name:'remark',
                        cls:'readonly ',
                        colspan:3,
                        width:630,
                        readOnly:true
                }]
            }];
        }
    });
    
    /**
    *散客业务信息
    */
    Ext.define('ScatterBusinessForm',{
        extend:'BasicFormPanel',
        items:null,
        initComponent:function(){
            this.items = this.getItems();
            this.callParent();
        },
        getItems:function(){
            var me = this;
            return [{
                xtype:'fieldset',
                title:i18n('i18n.ScatterCustManagerView.scatterBusinessInfo'),
                layout:{
                    type:'table',
                    columns:3
                },
                defaults:{
                    labelWidth:80,
                    width:210
                },
                items:[{
                    fieldLabel:i18n('i18n.PotentialCustEditView.companyName'),
                    xtype:'combobox',
                    store:getDataDictionaryByName(DataDictionary,'COOPERATION_LOGISTICS'),
                    queryMode:'local',
                    forceSelection:true,
                    displayField:'codeDesc',
                    valueField:'code',
                    name:'recentcoop',
                    cls:'readonly ',
                    readOnly:true
                },{
                    fieldLabel:i18n('i18n.PotentialCustManagerView.coopIntention'),
                    xtype:'combobox',
                    store:getDataDictionaryByName(DataDictionary,'COOPERATION_INTENTION'),
                    queryMode:'local',
                    forceSelection:true,
                    displayField:'codeDesc',
                    valueField:'code',
                    name:'coopIntention',
                    cls:'readonly ',
                    readOnly:true
                },{
                    fieldLabel:i18n('i18n.developSchedule.sendGoodsPontential'),
                    xtype:'combobox',
                    store:getDataDictionaryByName(DataDictionary,'CARGO_POTENTIAL'),
                    queryMode:'local',
                    forceSelection:true,
                    displayField:'codeDesc',
                    valueField:'code',
                    name:'volumePotential',
                    cls:'readonly ',
                    readOnly:true
                },{
                    fieldLabel:i18n('i18n.PotentialCustEditView.goodsTrendCondition'),
                    xtype:'textareafield',
                    name:'recentGoods',
                    cls:'readonly ',
                    readOnly:true
                },{
                    fieldLabel:i18n('i18n.PotentialCustEditView.custNeed'),
                    xtype:'textareafield',
                    colspan:2,
                    width:420,
                    name:'custNeed',
                    cls:'readonly ',
                    readOnly:true
                },{//盛诗庆 新增一个业务类别字段
                	 fieldLabel:i18n('i18n.PotentialCustManagerView.businessType'),
                     xtype:'combo',
                     name : 'businessType',
                     queryMode:'local',
                     store:getDataDictionaryByName(DataDictionary,'BUSINESS_TYPE'),
                     forceSelection:true,
                     displayField:'codeDesc',
                     valueField:'code',
                     cls:'readonly ',
                     readOnly:true
                }]
            }];
        }
    });
    
    //潜客基本信息
    Ext.define('PotentialCustBasicDetailInfoForm',{
        extend:'BasicFormPanel',
        defaultType:'textfield',
        layout:'fit',
        initComponent:function(){
            this.items = this.getItems();
            this.callParent();
        },
        //设置items
        getItems:function(){
            var me = this;
            var items = [{
                xtype:'fieldset',
                title:i18n('i18n.ScatterCustManagerView.scatterInfo'),
                defaults:{
                    labelWidth:80,
                    width:210
                },
                layout:{
                    type:'table',    
                    columns : 3         
                },
                defaultType:'textfield',
                items:[{
                    fieldLabel:i18n('i18n.PotentialCustManagerView.customerName'),
                    name:'custName',
                    cls:'readonly ',
                    readOnly:true
                },{ //城市
                    fieldLabel:i18n('i18n.PotentialCustEditView.city'),
                    name:'cityName',
                    cls:'readonly ',
                    readOnly:true
                },{ //客户来源
                    fieldLabel:i18n('i18n.PotentialCustManagerView.custSource'),
                    xtype:'combobox',
                    forceSelection:true,
                    store:getDataDictionaryByName(DataDictionary,'CUST_SOURCE'),
                    queryMode:'local',
                    displayField:'codeDesc',
                    valueField:'code',
                    name:'custbase',
                    cls:'readonly ',
                    readOnly:true
                },{
                    fieldLabel:i18n('i18n.PotentialCustManagerView.contactName'),
                    name:'linkManName',
                    cls:'readonly ',
                    readOnly:true
                },{
                    fieldLabel:i18n('i18n.PotentialCustManagerView.contactTel'),
                   name:'linkManPhone',
                   cls:'readonly ',
                   readOnly:true
                },{
                    fieldLabel:i18n('i18n.PotentialCustManagerView.contactPhone'),
                    name:'linkManMobile',
                    cls:'readonly ',
                    readOnly:true
                },{
                    fieldLabel:i18n('i18n.PotentialCustManagerView.position'),
                    name:'post',
                    cls:'readonly ',
                    readOnly:true
                },{ //商机状态
                    fieldLabel:i18n('i18n.PotentialCustManagerView.bizStatus'),
                    colspan:2,
                    xtype:'combobox',
                    store:getDataDictionaryByName(DataDictionary,'BUSINESS_STATUS'),
                    queryMode:'local',
                    forceSelection:true,
                    displayField:'codeDesc',
                    valueField:'code',
                    cls:'readonly ',
                    readOnly:true,
                    name:'bussinesState'
                },{ //行业
                    fieldLabel:i18n('i18n.PotentialCustManagerView.firstTrade'),
                    xtype:'combobox',
                    store:getDataDictionaryByName(DataDictionary,'TRADE'),
                    queryMode:'local',
                    forceSelection:true,
                    displayField:'codeDesc',
                    valueField:'code',
                    name:'trade',
                    cls:'readonly ',
                    readOnly:true
                },{ //二级行业
                    fieldLabel:i18n('i18n.PotentialCustManagerView.secondTrade'),
                    colspan:2,
                    xtype:'combobox',
                    store:getDataDictionaryByName(DataDictionary,'SECOND_TRADE'),
                    queryMode:'local',
                    forceSelection:true,
                    displayField:'codeDesc',
                    valueField:'code',
                    name:'secondTrade',
                    cls:'readonly ',
                    readOnly:true
                },{
                    fieldLabel:i18n('i18n.PotentialCustEditView.address'),
                    colspan:3,
                    width:630,
                    name:'address',
                    cls:'readonly ',
                    readOnly:true
                }]
            }];
            return items;
        }
    });
    
    Ext.define('ScatterCustDetailWindow',{
        extend:'PopWindow',
        title:i18n('i18n.DevelopScheduleAdd.detail'),
        layout:'fit',
//      y:0,
        height:500,
        width:690,
        closeAction:'hide',
        basicScatterCustInfo:null,     //客户基本信息
        scatterCustBusinessInfo:null,  //客户业务信息
        items:null,
        fbar:null,
        initComponent:function(){
            var  me = this;
            me.basicScatterCustInfo = Ext.create('BasicScatterCustForm',{id:'scatterCustFormId'});
            me.scatterCustBusinessInfo = Ext.create('ScatterBusinessForm',{id:'scatterCustBusinessFormId'});
            //设置items
            me.items = me.getItems();
            this.callParent();
        },
        getItems:function(){
            var me = this;
            return [{
                xtype:'basicformpanel',
//              region:'center',
                height:385,
                layout:{
                    type:'vbox',
                    align:'stretch'
                },
                items:
                    [{
                        xtype:'basicpanel',
                        layout:'fit',
                        items:[me.basicScatterCustInfo]
                    },{
                        xtype:'basicpanel',
                        layout:'fit',
                        items:[me.scatterCustBusinessInfo]
                    }]
             }];
        },
        buttons:[{
            xtype:'button',
            text:i18n('i18n.DevelopManageView.close'),
            handler:function(){
                Ext.getCmp("scatterCustDetailWindowId").close();
            }
        }]
    });
    
    scattercustdetailWindow=Ext.getCmp("scatterCustDetailWindowId");//获取win
    if(!scattercustdetailWindow){
        scattercustdetailWindow=Ext.create('ScatterCustDetailWindow',{id:'scatterCustDetailWindowId'});
    }
    
    Ext.define('PotentialCustbasicDetailinfoWin',{
        extend:'PopWindow',
        title:i18n('i18n.DevelopScheduleAdd.detail'),
        layout:'fit',
        height:400,
        width:690,
        closeAction:'hide',
        basicScatterCustInfo:null,     //客户基本信息
        scatterCustBusinessInfo:null,  //客户业务信息
        items:null,
        fbar:null,
        initComponent:function(){
            var  me = this;
            me.basicScatterCustInfo = Ext.create('PotentialCustBasicDetailInfoForm',{id:'potentialCustFromId'});
            me.scatterCustBusinessInfo = Ext.create('ScatterBusinessForm',{id:'potentialCustBusinessFromId'});
            //设置items
            me.items = me.getItems();
            this.callParent();
        },
        getItems:function(){
            var me = this;
            return [{
                xtype:'basicformpanel',
                height:375,
                layout:{
                    type:'vbox',
                    align:'stretch'
                },
                items:
                    [{
                        xtype:'basicpanel',
                        layout:'fit',
                        items:[me.basicScatterCustInfo]
                    },{
                        xtype:'basicpanel',
                        layout:'fit',
                        items:[me.scatterCustBusinessInfo]
                    }]
             }];
        },
        buttons:[{
            xtype:'button',
            text:i18n('i18n.DevelopManageView.close'),
            handler:function(){
                Ext.getCmp("potentialCustbasicDetailinfoWinId").close();
            }
        }]
    });
    
    potentialcustbasicdetailinfoWin=Ext.getCmp("potentialCustbasicDetailinfoWinId");//获取win
    if(!potentialcustbasicdetailinfoWin){
        potentialcustbasicdetailinfoWin=Ext.create('PotentialCustbasicDetailinfoWin',{id:'potentialCustbasicDetailinfoWinId'});
    }
    
    
    /**
     * 查询按钮
     */
    Ext.define('RightSearchButtonPanel',{
        extend:'NormalButtonPanel', 
        items:null,
        region:'south',
        initComponent:function(){
            this.items = this.getItems();
            this.callParent();
        },
        getItems:function(){
            var me = this;
            return [{
                xtype:'leftbuttonpanel', 
                items:[{xtype:'button',text:i18n('i18n.DevelopManageView.find'),
                    handler:function(){
                        var grid = Ext.getCmp('searchResultGridId');
                        var selection=grid.getSelectionModel().getSelection();
                        //判断是否选中行
                        if (selection.length == 0) {
                            MessageUtil.showMessage(i18n('i18n.developPlan.choice'));
                            return false;
                        }
                        if (selection.length != 1) {
                            MessageUtil.showMessage(i18n('i18n.developPlan.chooseOne'));
                            return false;
                        }
                        //保存成功回调函数
                        var successFn = function(json){
                            var m=new SearchConditionModel(json.detailInfo);
                            if(selection[0].get("custType")==POTENTIAL_CUSTOMER){//潜客
                                Ext.getCmp("potentialCustFromId").getForm().loadRecord(m);
                                Ext.getCmp("potentialCustBusinessFromId").getForm().loadRecord(m);
                                potentialcustbasicdetailinfoWin.show();
                            }
                            if(selection[0].get("custType")==SCATTER_CUSTOMER){
                                Ext.getCmp("scatterCustBusinessFormId").getForm().loadRecord(m);
                                Ext.getCmp("scatterCustFormId").getForm().loadRecord(m);
                                scattercustdetailWindow.show();
                            }
                        };
                        var failureFn = function(json){
                            MessageUtil.showErrorMes(json.message);
                        };
                        var params={
                            'id':selection[0].get("custId")
                        };
                        developScheduleData.searchPcScDetail(params, successFn, failureFn);
                }},{
                    text:i18n('i18n.developSchedule.returnVisit'),
                    xtype:'button',
                    handler:function(){
                        var grid = Ext.getCmp('searchResultGridId');
                        //获取store中数据
                        var store = grid.getStore();
                        var selection=grid.getSelectionModel().getSelection();
                        if (selection.length == 0) {
                            MessageUtil.showMessage(i18n('i18n.developSchedule.choice'));
                            return false;
                        }
                        if(selection.length>1){
                            MessageUtil.showMessage(i18n('i18n.developPlan.chooseOne'));
                            return false;
                        }
                        var scheduleType=DEVELOP_TYPE;
                        var successFn = function(json){
                            var initData=new InitDataModel(json.returnVisit);
                            var customerInfoFormPanel = Ext.getCmp("customerInfoFormPanel").getForm();
                            customerInfoFormPanel.loadRecord(initData);
                            var ScheduleMakeForm =  Ext.getCmp("scheduleMakeForm").getForm();
                            ScheduleMakeForm.loadRecord(initData);
                            Ext.getCmp('scheType').setValue(scheduleType);
                            win.show();
                            document.getElementsByTagName("html")[0].style.overflowY="auto";
                            document.getElementsByTagName("html")[0].style.overflowX="auto";
                            viewport.doLayout();
                        };
                        var failureFn = function(json){
                            MessageUtil.showErrorMes(json.message);
                        };
                        DevelopScheduleData.prototype.setRetureVisitByCust({
                            'returnVisit.scheduleId':selection[0].get("id"),
                            'returnVisit.linkManId':selection[0].get("contactId"),
                            'returnVisit.memberId':selection[0].get("memberId"),
                            'returnVisit.scheType':scheduleType
                        }, successFn, failureFn);
                        //清空走货潜力表格
                        Ext.getCmp('sendGoodsPontentialGrid').store.removeAll();
                        //清空客户意见表格
                        Ext.getCmp('customerOpinionGrid').store.removeAll();
                        //清空跟踪时间和跟踪方式
                        Ext.getCmp('schedule').setValue(null).disable();
                        Ext.getCmp("ifparent").setValue('0');
                    }
                },//营销记录按钮，点击查询营销历史记录               
                {
                    xtype:'button',
                    text:i18n('i18n.marketRecord.marketRecord'),
                    handler:function(){
                        var grid = Ext.getCmp('searchResultGridId');
                        //获取store中数据
                        var store = grid.getStore();
                        var selection=grid.getSelectionModel().getSelection();
                        if (selection.length == 0) {
                            MessageUtil.showMessage(i18n('i18n.developSchedule.choice'));
                            return false;
                        }
                        if(selection.length>1){
                            MessageUtil.showMessage(i18n('i18n.developPlan.chooseOne'));
                            return false;
                        }
                        //根据客户id和客户类型查询客户所有营销历史记录
                        if(selection.length==1&&!Ext.isEmpty(selection[0].get("custType"))&&!Ext.isEmpty(selection[0].get("custId"))){
                            custIDForMarketRecord = selection[0].get("custId");
                            custTypeForMarketRecord = selection[0].get("custType");
                        }
                        else {
                            MessageUtil.showMessage(i18n('i18n.customerCallIn.nullCustIdOrCustType'));
                            return false;
                        }
                        
                        Ext.getCmp('sendGoodsPontentialResultGridId').store.loadPage(1);
                        Ext.getCmp('customerDemandResultGridId').store.loadPage(1);
    
                        //点击按钮，弹出营销历史记录查询结果窗口
                        marketHistoryRecordWindow.show();
                        
                    }}
                ]
            },{
                xtype:'middlebuttonpanel' 
            },{
                xtype:'rightbuttonpanel',  
                items : [{
                    xtype : 'button',
                    text : i18n('i18n.PotentialCustManagerView.search'),
                    handler : function(){
                        //判断界面校验是否通过
                        if(!Ext.getCmp('SearchConditionFormId').getForm().isValid()){
                            MessageUtil.showMessage(i18n('i18n.DevelopManageView.pleaseCondition'));
                            return false;
                        }
                        
                        var startDate = Ext.getCmp("SearchConditionFormId").getForm().findField("beginTime").getValue();
                        var endDate = Ext.getCmp("SearchConditionFormId").getForm().findField("overTime").getValue();
                        if(!Ext.isEmpty(startDate)){
                            if(!Ext.isEmpty(endDate)){
                                var days = DButil.compareTwoDate(startDate,endDate);
                                if(days<0){
                                    MessageUtil.showInfoMes(i18n('i18n.developPlan.startDateBGEndDate'));
                                    return;
                                }else if(days>365){
                                    MessageUtil.showInfoMes(i18n('i18n.developPlan.queryDateLimityears'));
                                    return;
                                }
                                //load数据
                                Ext.getCmp('searchResultGridId').store.loadPage(1);
                            }else{
                                MessageUtil.showInfoMes("创建时间结束日期不能为空~");
                                return;
                            }
                        }else{
                            MessageUtil.showInfoMes("创建时间起始日期不能为空~");
                            return;
                        }
                    }
                },{
                    xtype : 'button',
                    text : i18n('i18n.developPlan.reset'),
                    handler : function(){
                         //处理重置时客户类型为潜客禁用输入框问题
                        var typeCob = Ext.getCmp("SearchConditionFormId").getForm().findField("custType");
                        if(typeCob){
                            //设置默认类型为潜客，且触发select事件
                            typeCob.setValue(POTENTIAL_CUSTOMER).fireEvent("select",typeCob);
                        }
                        Ext.getCmp("SearchConditionFormId").getForm().reset();
                        Ext.getCmp("SearchConditionFormId").down('form').getForm().findField('secondTrade').store.removeAll();
                        //设置默认部门
                        var deptModel=new DeptModel();
                        deptModel.set("id",User.deptId);
                        deptModel.set("deptName",User.deptName);
                        Ext.getCmp("SearchConditionFormId").getForm().findField("deptName").store.removeAll();
                        Ext.getCmp("SearchConditionFormId").getForm().findField("deptName").store.add(deptModel);
                        Ext.getCmp("SearchConditionFormId").getForm().findField("deptName").setValue(User.deptId);
                    }
                }]
            }];
        }
    });
    
    
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
                    fieldLabel : i18n('i18n.PotentialCustManagerView.deptName'),
                    name:'deptName',
                    xtype:'combo',
                    store:Ext.create('DeptStore',{id:'queryDeptStoreId',
                        listeners:{
                            beforeload:function(store, operation, eOpts){
                                Ext.apply(operation,{
                                    params : {
                                        'deptName':Ext.getCmp("SearchConditionFormId").getForm().findField("deptName").getRawValue()
                                        }
                                    }
                                );  
                            }
                        }
                    }),
                    colspan:3,
                    triggerAction : 'all',
                    allowBlank:false,
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
                        change:DButil.comboSelsct
                    }
                }
//              {
//                  fieldLabel : i18n('i18n.PotentialCustManagerView.deptName'),
//                  xtype:'combo',
//                  colspan:3,
//                  name:'deptName',
//                  id:'deptId',
//                  store:Ext.create('DeptStore',{
//                      listeners:{
//                          load:function(){
//                              Ext.getCmp("deptId").setValue(User.deptId);
//                          }
//                      }
//                  }),
//                  queryMode: 'local',
//                  triggerAction : 'all',
//                  displayField:'deptName',
//                  valueField:'id',
//                  colspan : 3,
//                  oldValue:null,
//                  editable:       false,
//                  forceSelection :true,
//                  listeners:{
//                      change:DButil.comboSelsct,
//                      'select':function(th,records){
//                          
//                      }
//                  }
//              }
                ,{
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
                    //配合香港开点：手机号为8位数字或是首位为1的11位数字
                    regex : /(^1\d{10}$)|(^\d{8}$)/
                }, {
                    fieldLabel : i18n('i18n.PotentialCustManagerView.contactTel'),
                    colspan : 3,
                    name : 'linkManPhone',
                    maxLength : 20,
                    maxLengthText : i18n('i18n.developPlan.maxLength')+20
                }, {
                    fieldLabel : i18n('i18n.ScatterCustManagerView.custAttribute'),
                    xtype : 'combo',
                    colspan : 3,
                    disabled:true,
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
                }, {//客户类型
                	//盛诗庆，将客户类型设为必填项
                        xtype:          'combo',
                        mode:           'local',
                        triggerAction:  'all',
                        colspan : 3,
                        fieldLabel:     i18n('i18n.PotentialCustManagerView.custType'),
                        value:POTENTIAL_CUSTOMER,
                        name:           'custType',
                        displayField:   'codeDesc',
                        allowBlank : false,
                        blankText : '请选择客户类型',
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
                    },{ //客户来源
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
                }, {//货量潜力
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
                },{/*
                    @author 盛诗庆
                    @description 新增一个业务类别查询字段
                    */
                        fieldLabel : i18n('i18n.PotentialCustManagerView.businessType'),
                        colspan : 3,
                        xtype : 'combo',
                        allowBlank : false,
                        blankText : '请选择业务类型',
                        name : 'businessType',
                        store:getDataDictionaryByName(DataDictionary,'BUSINESS_TYPE'),
                        queryMode:'local',
                        displayField:'codeDesc',
                        valueField:'code',
                        value : 'LESS_THAN_TRUCKLOAD',
                        forceSelection :true,
                        listeners:{
                            change:DButil.comboSelsct
                        }
                    },{//行业
//                    fieldLabel : i18n('i18n.PotentialCustManagerView.industry'),
//                    colspan : 3,
//                    xtype : 'combo',
//                    name : 'trade',
//                    store:getDataDictionaryByName(DataDictionary,'TRADE'),
//                    queryMode:'local',
//                    displayField:'codeDesc',
//                    valueField:'code',
//                    forceSelection :true,
//                    listeners:{
//                        change:DButil.comboSelsct
//                    }
                    //二级行业
                    xtype:'customertrade',
                    id:'firstTradeAndSecondTradeId',
                    userType:'SEARCH',
                    colspan:6,
                    fieldLabel:i18n('i18n.PotentialCustManagerView.industry'),
                    width:390,
                    trade_labelWidth:55,
                    trade_width:180,
                    trade_name:'trade',
                    trade_fieldname:i18n('i18n.PotentialCustManagerView.firstTrade'),
                    second_trade_labelWidth:70,
                    second_trade_width:195,
                    second_trade_name:'secondTrade',
                    second_trade_fieldname:i18n('i18n.PotentialCustManagerView.secondTrade')
                }, {//创建开始时间
                    fieldLabel : i18n('i18n.PotentialCustManagerView.searchStartTime'),
                    colspan : 3,
                    xtype : 'datefield',
                    format : 'Y-m-d',
                    editable: false,
                    name : 'beginTime',
                    id:'beginTime',
                    value : new Date(new Date().setMonth(new Date().getMonth() - 3))
//                  ,
//                  listeners:{
//                      change:function(field,newValue,oldValue,eOpts){
//                          var endDate = Ext.getCmp('overTime').getValue();
//                          var days = DButil.compareTwoDate(newValue,endDate);
//                          if(days<0){ 
//                              MessageUtil.showInfoMes(i18n('i18n.developPlan.startDateBGEndDate'));
//                              field.setValue(oldValue);
//                              return;
//                          }else if(days>365){
//                              MessageUtil.showInfoMes(i18n('i18n.CommonView.oneyear'));
//                              field.setValue(new Date());
//                              return;
//                          }
//                      }
//                  }
                }, {//创建结束时间
                    xtype: 'fieldcontainer',
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
//                          ,
//                          listeners:{
//                              change:function(field,newValue,oldValue,eOpts){
//                                  var startDate = Ext.getCmp('beginTime').getValue();
//                                  var days = DButil.compareTwoDate(startDate,newValue);
//                                  if(days<0){
//                                      MessageUtil.showInfoMes(i18n('i18n.developPlan.EndDateAfterStartDate'));
//                                      field.setValue(oldValue);
//                                      return;
//                                  }else if(days>365){
//                                      MessageUtil.showInfoMes(i18n('i18n.CommonView.oneyear'));
//                                      field.setValue(oldValue);
//                                      return;
//                                  }
//                              }
//                          }
                        }]
                    }, {//出发货量
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
                }, {//到达货量
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
                }, {//发货金额
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
                } ,{//发货票数
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
                },  {//到达票数
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
                }]
            }];
        }
    });
    
    /**.
     * <p>
     * 开发计划新增、修改主Panel
     * <p>
     * @author 张登
     * @时间 2012-3-26
     */
    Ext.define('CustomerDevelopPlanePanel',{
        extend:'BasicPanel',
        searchCondition:null, //查询条件From
        searchLeftResult:null, //查询客户列表（左边Grid）
        items:null,
        layout:'border',
        initComponent:function(){
            var me = this;
            
            //查询条件From
            me.searchCondition = Ext.create('SearchConditionForm',{id:'SearchConditionFormId'});
            //设置起始结束时间
            var date = new Date();
//          me.searchCondition.getForm().findField("overTime").setValue(date);
//          date.setDate(new Date().getDate()-30);
//          me.searchCondition.getForm().findField("beginTime").setValue(date);
            
            //查询客户列表store
            var store=Ext.create('ScheDuleStore',{id:'ScheDuleStoreId'});
            store.on('beforeload',function(store,operation,e){
                var searchScatterForm = Ext.getCmp("SearchConditionFormId");
                //获取二级行业
                var firstTrade = searchScatterForm.down('form').getForm().findField('trade').getValue();
                var secondTrade = searchScatterForm.down('form').getForm().findField('secondTrade').getValue();

                //设置请求参数
                var searchParams = { 
                    //客户名称
                    'customerVo.custName':searchScatterForm.getForm().findField('custName').getValue(),
                    //联系人姓名
                    'customerVo.linkManName':searchScatterForm.getForm().findField('linkManName').getValue(),
                    //联系人手机
                    'customerVo.linkManMobile':searchScatterForm.getForm().findField('linkManMobile').getValue(),
                    //联系人电话
                    'customerVo.linkManPhone':searchScatterForm.getForm().findField('linkManPhone').getValue(),
                    //客户属性  出发客户 到达客户
                    'customerVo.custProperty':searchScatterForm.getForm().findField('custProperty').getValue(),
                    //合作意向
                    'customerVo.coopIntention':searchScatterForm.getForm().findField('coopIntention').getValue(),
                    //商机状态
                    'customerVo.bussinesState':searchScatterForm.getForm().findField('bussinesState').getValue(),
                    //客户来源
                    'customerVo.custbase':searchScatterForm.getForm().findField('custbase').getValue(),
                    //货量潜力
                    'customerVo.volumePotential':searchScatterForm.getForm().findField('volumePotential').getValue(),
                    //客户类型
                    'customerVo.custType':searchScatterForm.getForm().findField('custType').getValue(),
                    'customerVo.scheduleType':DEVELOP_TYPE,
                    //行业
                    'customerVo.trade':firstTrade,
                    //二级行业
                    'customerVo.secondTrade':secondTrade,
                    //创建时间
                    'customerVo.beginTime':searchScatterForm.getForm().findField('beginTime').getValue(),
                    'customerVo.overTime':searchScatterForm.getForm().findField('overTime').getValue(),
                    //发货量
                    'customerVo.beginShipWeight':searchScatterForm.getForm().findField('beginShipWeight').getValue(),
                    'customerVo.overShipWeight':searchScatterForm.getForm().findField('overShipWeight').getValue(),
                    //发货票数
                    'customerVo.beginShipVotes':searchScatterForm.getForm().findField('beginShipVotes').getValue(),
                    'customerVo.overShipVotes':searchScatterForm.getForm().findField('overShipVotes').getValue(),
                    //发货金额
                    'customerVo.beginShipAmount':searchScatterForm.getForm().findField('beginShipAmount').getValue(),
                    'customerVo.overShipAmount':searchScatterForm.getForm().findField('overShipAmount').getValue(),
                    //到达货量
                    'customerVo.beginArrivalWeight':searchScatterForm.getForm().findField('beginArrivalWeight').getValue(),
                    'customerVo.overArrivalWeight':searchScatterForm.getForm().findField('overArrivalWeight').getValue(),
                    //到达票数
                    'customerVo.beginArrivalVotes':searchScatterForm.getForm().findField('beginArrivalVotes').getValue(),
                    'customerVo.overArrivalVotes':searchScatterForm.getForm().findField('overArrivalVotes').getValue(),
                    //到达金额
                    'customerVo.beginArrivalAmount':searchScatterForm.getForm().findField('beginArrivalAmount').getValue(),
                    'customerVo.overArrivalAmount':searchScatterForm.getForm().findField('overArrivalAmount').getValue(),
                    'customerVo.deptId':searchScatterForm.getForm().findField('deptName').getValue(),
                    /*
                     * 盛诗庆
                     * 新增查询条件业务类型
                     * */
                    'customerVo.businessType':searchScatterForm.getForm().findField('businessType').getValue(),
                };
                Ext.apply(operation,{
                    params : searchParams
                });
            });
            var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
                 clicksToMoveEditor: 2,
                 clicksToEdit:2,
                 autoCancel: false
            });
            
            rowEditing.on('edit',function(th,e,eop){
                var record=th.record;
                var id = record.get('scheduleId');
                var scheduleDate = record.data.scheduleDate;
                if(Ext.isEmpty(scheduleDate)){
                    MessageUtil.showMessage(i18n('i18n.developSchedule.developTimeNotNull'));
                    return false;
                }
                var planId = record.data.planId;
                var custId = record.data.custId;
                
                //保存成功回调函数
                var successFn = function(json){
                    MessageUtil.showInfoMes("保存成功");
                    //Ext.getCmp("searchResultGridId").store.loadPage(1);
                    record.commit();
                };
                
                //保存失败回调函数
                var failureFn = function(json){
                    MessageUtil.showErrorMes(json.message);
                };
                var params={
                        'schedule.id':id,
                        'schedule.time':DButil.changeLongToDate(scheduleDate),
                        'schedule.planId':planId,
                        'schedule.custId':custId,
                        'schedule.type':DEVELOP_TYPE//开发
                    };
                developScheduleData.saveSchedule(params, successFn, failureFn);
            });
            var selModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE',id:'selModelId'});
            //查询结果Grid
            me.searchLeftResult =  Ext.create('SearchGridPanel',{
                id:'searchResultGridId',
                cls:'market',
                store:store,
                columns:me.getColumns(),
                selModel:selModel,
                'plugins':rowEditing,
                dockedItems:[{
                    xtype:'pagingtoolbar',
                    store:store,
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
                                    var pageSize = store.pageSize;
                                    var newPageSize = parseInt(_field.value);
                                    if(pageSize!=newPageSize){
                                        store.pageSize = newPageSize;
                                        this.up('pagingtoolbar').moveFirst();
                                    }
                                }
                            }
                       }
                   }),{
                        text: '条',
                        xtype: 'tbtext'
                   }]
                }]
            });
            
            //设置items
            me.items = me.getItems();
            this.callParent();
            
            //设置默认部门
            var deptModel=new DeptModel();
            deptModel.set("id",User.deptId);
            deptModel.set("deptName",User.deptName);
            Ext.getCmp("SearchConditionFormId").getForm().findField("deptName").store.add(deptModel);
            Ext.getCmp("SearchConditionFormId").getForm().findField("deptName").setValue(User.deptId);
        },
        getItems:function(){//整体布局
            var me = this;
            return [{
                region:'north',
                xtype:'basicpanel',
                height:250,
                layout:'border',
                items:[{
                    region:'center',
                    xtype:'basicpanel',
                    layout:'fit',
                    items:[me.searchCondition]
                },
                Ext.create('RightSearchButtonPanel')]//border布局下面查询按钮
            },{
                region:'center',
                xtype:'basicpanel',
                layout:'border',
                items:[{
                    region:'center',
                    xtype:'basicpanel',
                    layout:'border',
                    items:[{
                        region:'center',
                        xtype:'basicpanel',
                        layout:'fit',
                        flex:1,
                        items:[me.searchLeftResult]//查询按钮
                    }]
                }]
            }];
        },
        getColumns:function(){//查询结果列
            return [{
                xtype:'rownumberer',
                width:40,
                align:'center',
                header:i18n('i18n.Cycle.rownum')
            },{
                header : i18n('i18n.PotentialCustManagerView.customerName'),
                dataIndex : 'custName',
                width:100
            }, {
                header : i18n('i18n.PotentialCustManagerView.contactName'),
                width:100,
                dataIndex : 'contactName'
            }, {
                header : i18n('i18n.PotentialCustManagerView.contactPhone'),
                width:100,
                dataIndex : 'contactMobile'
            }, {
                header : i18n('i18n.PotentialCustManagerView.contactTel'),
                width:100,
                dataIndex : 'contactTel'
            }, {
                header : i18n('i18n.PotentialCustManagerView.programmeTime'),
                dataIndex : 'scheduleDate',
                width:95,
                renderer : function(value){
                    if(!Ext.isEmpty(value)){
                        return DButil.renderDate(value);
                    }
                },
                editor: {
                    xtype: 'datefield',
                    minValue :new Date(),
                    editable: false,
                    format: 'Y-m-d'
               }
            },{/*
            @author 盛诗庆
            @description 查询结果新增一列业务类型
            */
                header : i18n('i18n.PotentialCustManagerView.businessType'),
                width:100,
                dataIndex : 'businessType',
                renderer:function(value){
                    return rendererDictionary(value,DataDictionary.BUSINESS_TYPE);
                }
            },{
                header : i18n('i18n.MonitorPlan.noFinishNumber'),
                width:100,
                dataIndex : 'unfinishedPlanNum'
            }, {
                header : i18n('i18n.PotentialCustManagerView.reviTimes'),
                width:60,
                dataIndex : 'visitNum'
            }, {
                header : i18n('i18n.PotentialCustManagerView.finalreviTime'),
                dataIndex : 'lastVisitDate',
                width:100,
                renderer : function(value){
                    if(!Ext.isEmpty(value)){
                        return DButil.renderDate(value);
                    }
                }
            },{
                header : i18n('i18n.PotentialCustManagerView.position'),
                width:100,
                dataIndex : 'position'
            },{
                header : i18n('i18n.PotentialCustManagerView.coopIntention'),
                width:65,
                dataIndex : 'coopIntetion',
                renderer:function(value){
                    return rendererDictionary(value,DataDictionary.COOPERATION_INTENTION);
                }
            }, {
                header : i18n('i18n.PotentialCustManagerView.goodsPotential'),
                dataIndex : 'goodsPotential',
                width:90,
                renderer:function(value){
                    return rendererDictionary(value,DataDictionary.CARGO_POTENTIAL);
                }
            }, {
                header : i18n('i18n.PotentialCustManagerView.bizStatus'),
                width:80,
                dataIndex : 'businessStatus',
                renderer:function(value){
                    return rendererDictionary(value,DataDictionary.BUSINESS_STATUS);
                }
            }]
        }
    });
    /**
     * 回访
     */
    Ext.define('CreateDevelopPopWindow',{
        extend:'PopWindow',
        alias : 'widget.basicwindow',
        width:820,
        height:750,
        modal:true,
        layout:'fit',
        closeAction:'hide',
        items:[Ext.create('CustomerReturnVisitPanel',{'id':'customerReturnVisitPanel'})],
        listeners:{
            hide:function(){
                document.body.scrollLeft=0;
                document.body.scrollTop=0;
                document.getElementsByTagName("html")[0].style.overflowY="hidden";
                document.getElementsByTagName("html")[0].style.overflowX="hidden";
                viewport.doLayout();
            }
        }
    });
    var win=Ext.getCmp("CreateDevelopPopWindowId");//获取win
    if(!win){
        win=Ext.create('CreateDevelopPopWindow',{id:'CreateDevelopPopWindowId'});
    }

    /**
     * 单击营销记录按钮，弹出的营销历史记录查询结果窗口
    */
    Ext.define('MarketHistoryRecordWindow',{
        extend:'PopWindow',
        title:'营销历史记录',
        alias : 'widget.basicwindow',
        width:820,
        height:600,
        modal:true,
        layout:'fit',
        closeAction:'hide',
        items:[Ext.create('MarketHistoryResultPanel',{'id':'marketHistoryResultPanelId'})],
        listeners:{
            hide:function(){
                document.body.scrollLeft=0;
                document.body.scrollTop=0;
                document.getElementsByTagName("html")[0].style.overflowY="hidden";
                document.getElementsByTagName("html")[0].style.overflowX="hidden";
                viewport.doLayout();
            }
        },
        buttons:[{
            xtype:'button',
            text:i18n('i18n.DevelopManageView.close'),
            handler:function(){
                Ext.getCmp("marketHistoryRecordWindowId").hide();
            }
        }]
    });
    
    var marketHistoryRecordWindow = Ext.getCmp("MarketHistoryRecordWindow");//获取win
    if(!marketHistoryRecordWindow){
        marketHistoryRecordWindow = Ext.create('MarketHistoryRecordWindow',{id:'marketHistoryRecordWindowId'});
    }
    
    //整体布局
    var viewport=Ext.create('Ext.Viewport', {
        layout : {
            type : 'fit'
        },
        items : [ Ext.create('CustomerDevelopPlanePanel')]
    });
});

