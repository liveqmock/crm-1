//重写numberfield 四舍五入
Ext.override(Ext.form.NumberField, {
    setValue : function(v){
            v = typeof v == 'number' ? v : String(v).replace(this.decimalSeparator, ".");
        v = isNaN(v) ? '' : String(v).replace(".", this.decimalSeparator);
        return Ext.form.NumberField.superclass.setValue.call(this, v);
    },
    fixPrecision : function(value){
        var nan = isNaN(value);
        if(!this.allowDecimals || this.decimalPrecision == -1 || nan || !value){
           return nan ? '' : value;
        }
        return Math.round(value * 1000) / 1000;
    }
})
//查询主界面信息
Ext.define('MarketActivitySearchCondition',{
    extend:'SearchFormPanel',
    id : 'marketActivitySearchConditionId',
    layout : {
        type : 'table',
        columns : 8
    },
    defaultType : 'textfield',
    defaults : {
        margin:'5 5 5 5',
        labelWidth : 80,
        labelAlign: 'right',
        width : 120
    },
    getItems:function(){
        var me = this;
        return [{
            fieldLabel:i18n('i18n.MarketActivityManagerView.activityCode'),//活动编码
            name : 'activityCode',
            width:240,
            maxLength : 20,
            colspan:2
        },{
            fieldLabel:i18n('i18n.MarketActivityManagerView.activityName'),//活动名称
            name : 'activityName',
            width:310,
            maxLength : 40,
            colspan:3
        },{
            xtype : 'combo',
            fieldLabel:i18n('i18n.MarketActivityManagerView.activityStatus'),//活动状态
            name : 'activityStatus',
            store:getDataDictionaryByName(DataDictionary,'ACTIVITY_STATUS'),
            width:200,
            colspan:2,
            queryMode:'local',
            displayField:'codeDesc',
            valueField:'code',
            forceSelection :true,
            width:200,
            colspan:4,
            listeners:{
                change:DButil.comboSelsct
            }
        },{
            xtype : 'combo',
            fieldLabel:i18n('i18n.MarketActivityManagerView.activityType'),//活动类型
            name : 'activityType',
            store:getDataDictionaryByName(DataDictionary,'ACTIVITY_TYPE'),
            width:240,
            colspan:2,
            queryMode:'local',
            displayField:'codeDesc',
            valueField:'code',
            forceSelection :true,
            listeners:{
                change:DButil.comboSelsct
            }
        },{
            fieldLabel:i18n('i18n.MarketActivityManagerView.timeLimit'),//活动时限
            labelWidth:80,
            name : 'startTime',
            xtype: 'datefield',
            format : 'Y-m-d',
            minValue : new Date(2013,0),
            maxValue : new Date(2023,11,31),
            value : new Date(new Date().setFullYear((new Date()).getFullYear()-1)),
            editable:false,
            width:180,
            colspan:1
        },{
            fieldLabel:'——',//活动时限
            labelWidth:20,
            labelSeparator : '',
            name : 'endTime',
            xtype: 'datefield',
            format : 'Y-m-d',
            minValue : new Date(2013,0),
            maxValue : new Date(2023,11,31),
            value : new Date(new Date().setFullYear((new Date()).getFullYear()+1)),
            editable:false,
            width:120,
            colspan:2
        },{
            xtype : 'combo',
            fieldLabel:i18n('i18n.MarketActivityManagerView.activityCategory'),//活动类别
            store:getDataDictionaryByName(DataDictionary,'ACTIVITY_CATEGORY'),
            name : 'activityCategory',
            width:200,
            colspan:2,
            queryMode:'local',
            displayField:'codeDesc',
            valueField:'code',
            forceSelection :true,
            listeners:{
                change:DButil.comboSelsct
            }
        }];
    },
    initComponent:function(){
        var me = this;
        me.items = me.getItems();
        this.callParent();
    }
});


Ext.define('MarketActivityGridPanel', {
    name : 'marketActivityGridPanel',
    id : 'marketActivityGridPanelId',
    extend:'SearchGridPanel',
    columnLines:true,
    plugins:null,
    
    selModel : Ext.create('Ext.selection.CheckboxModel',{
        mode:'SINGLE',
        allowDeselect:true,
        listeners : {
            select:function(records){
                var panel = Ext.getCmp('operationButtonPanelId');
                //当前用户或部门负责人 并且活动为全国 且状态不为已终止
                var selection = Ext.getCmp('marketActivityGridPanelId').getSelectionModel().getSelection();
                if(selection[0].get('deptId') == User.deptId 
                &&(selection[0].get('createUser') == User.empId ||  User.empCode == Principal) 
                && selection[0].get('activityType') == 'NATIONWIDE'
                && selection[0].get('activityStatus') != 'TERMINATED'){
                    panel.down('button[name=updateButton]').enable();
                }
                //活动为区域状态为 审批通过 以及 折扣已生效 且当前用户或部门负责人则下发按钮生效
                if(selection[0].get('deptId') == User.deptId 
                &&(selection[0].get('createUser') == User.empId || User.empCode == Principal)
                && selection[0].get('activityType') != 'NATIONWIDE'
                && (selection[0].get('activityStatus') == 'PROCESSED' || selection[0].get('activityStatus') == 'EXECUTED')){
                    panel.down('button[name=issuedClientBtn]').enable();
                }
                //活动为全国状态为 已制定 以及折扣已生效 且当前用户或部门负责人则下发按钮生效
                if(selection[0].get('deptId') == User.deptId 
                &&(selection[0].get('createUser') == User.empId || User.empCode == Principal)
                && selection[0].get('activityType') == 'NATIONWIDE'
                &&(selection[0].get('activityStatus') == 'ESTABLISHED' || selection[0].get('activityStatus') == 'EXECUTED')){
                    panel.down('button[name=issuedClientBtn]').enable();
                }
                panel.down('button[name=lookButton]').enable();
            },
            deselect:function(records){
                var panel = Ext.getCmp('operationButtonPanelId');
                panel.down('button[name=updateButton]').disable();
                panel.down('button[name=issuedClientBtn]').disable();
                panel.down('button[name=lookButton]').disable();
            }
        }
        
    }),
    columns:null,
    viewConfig: { 
        forceFit:true 
    }, 
    initComponent:function(){
        var me = this;
        var store = Ext.create('ActivityDataStore');
        var form = Ext.getCmp('marketActivitySearchConditionId').getForm();
        if(DeptCharacter == 'DIVISION' || DeptCharacter == 'BIGREGION'){
            form.findField('activityType').getStore().removeAt(0);
        }
        me.store = store;
        store.on('beforeLoad',function(store,operation,e){       
            if(!form.isValid()){
                return false;
            }
            //封装查询条件传递给action
            var searchParams = {
                    'marketActivityVO.activityCode' : Ext.String.trim(form.findField('activityCode').getValue()),
                    'marketActivityVO.activityName': Ext.String.trim(form.findField('activityName').getValue()),
                    'marketActivityVO.activityType': (DeptCharacter == 'DIVISION' || DeptCharacter == 'BIGREGION')?'REGION':form.findField('activityType').getValue(),
                    'marketActivityVO.startTime': form.findField('startTime').getValue(),
                    'marketActivityVO.endTime': form.findField('endTime').getValue(),
                    'marketActivityVO.activityCategory': form.findField('activityCategory').getValue(),
                    'marketActivityVO.activityStatus': form.findField('activityStatus').getValue()
            };
            Ext.apply(operation,{
                    params : searchParams
            });
        });
        this.columns = [{
            text : i18n('i18n.MarketActivityManagerView.activityCode'),//活动编码
            dataIndex : 'activityCode'
        },{
            text : i18n('i18n.MarketActivityManagerView.activityName'),//活动名称
            dataIndex : 'activityName',
            renderer:function(value){
                if(!Ext.isEmpty(value)){
                    return '<span data-qtip="'+value+'">'+value+'</span>';
                }
            }
        },{
            text : i18n('i18n.MarketActivityManagerView.timeLimit'),//活动时限
            dataIndex : 'startTime',
            renderer : function(value,metaData,record){
                var endTime = record.data.endTime;
                return DButil.renderDate(value)+"~"+DButil.renderDate(endTime);
            }
        },{
            text : i18n('i18n.MarketActivityManagerView.activityStatus'),//活动状态
            dataIndex : 'activityStatus',
            renderer:function(value){
                return rendererDictionary(value,DataDictionary.ACTIVITY_STATUS);
            }
        },{
            text : i18n('i18n.MarketActivityManagerView.clientName'),//客户群名称
            dataIndex : 'clientBases'
        },{
            text : i18n('i18n.MarketActivityManagerView.activityType'),//活动类型
            dataIndex : 'activityType',
            renderer:function(value){
                return rendererDictionary(value,DataDictionary.ACTIVITY_TYPE);
            }
        },{
            text : i18n('i18n.MarketActivityManagerView.activityCategory'),//活动类别
            dataIndex : 'activityCategory',
            renderer:function(value){
                return rendererDictionary(value,DataDictionary.ACTIVITY_CATEGORY);
            }
        },{
            text : i18n('i18n.MarketActivityManagerView.activityCreateUser'),//活动制定人
            dataIndex : 'createUserName'
        },{
            text : i18n('i18n.MarketActivityManagerView.activityCreateTime'),//活动制定时间
            dataIndex : 'createDate',
            renderer : function(value,metaData,record){
                return DButil.renderDate(value);
            }
        }];
        this.dockedItems=[{
            xtype:'pagingtoolbar',
            store:me.store,
            dock:'bottom',
            displayInfo : true,
            autoScroll : true,
            items:[{
                text: i18n('i18n.MarketActivityManagerView.page'),//每页
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
                text: i18n('i18n.MarketActivityManagerView.item'),//条
                xtype: 'tbtext'
           }]
        }];
        this.callParent();
        me.store.load();
    }
});


/**
 * 新增、修改等操作按钮区
 * 20140307
 */
Ext.define('OperationButtonPanel',{
    extend : 'NormalButtonPanel', 
    id : 'operationButtonPanelId',
    getItems:function(){
        var me = this;
        return [{
            xtype:'leftbuttonpanel',
            width:380,
            items:[{//新增按钮
                xtype:'button',
                text : i18n('i18n.MarketActivityManagerView.append'),//新增
                handler : function(){
                    //初始化活动成功则验证用户部门性质
                    var successFn = function(json){
                        var marketActivityWindow=Ext.getCmp("marketActivityWindowId");//获取win
                        if(!marketActivityWindow){
                            marketActivityWindow=Ext.create('MarketActivityWindow',{id:'marketActivityWindowId'});
                        };
                        marketActivityWindow.show();
//                        Ext.getCmp('lineTreeRequireId').setValue('LEAVE_ARRIVE');
                        //当点击新增按钮时 给线路按钮传递参数， 用于判断是否是此次新增第一次点击线路按钮
                        Ext.getCmp('activityJoinConditionPanelId').down('button[name=lineRequest]').isAdd = true;
                        disp_disable(false);
                        Ext.getCmp('activityFormPanelId').getForm().findField('contactsName').forceSelection = true;
                        changeTitle(Ext.getCmp('deptGridPanelId'),0,i18n('i18n.MarketActivityManagerView.deptName'));
                        Ext.getCmp('deptTreeForActivityId').getRootNode().cascadeBy(function(node){
                            node.set('checked', false);
                        })
                        Ext.getCmp('deptTreeForActivityId').collapseAll();
                        var activityForm = Ext.getCmp('activityFormPanelId').getForm();
                        //设置活动类别
                        if(DeptCharacter == "EXPGROUP"){
                            activityForm.findField('activityCategory').setValue('EXPRESS');
                        }else{
                            activityForm.findField('activityCategory').setValue('LTT');
                        }
                        //设置活动类型
                        if(DeptCharacter == "LTTGROUP" || DeptCharacter == "EXPGROUP"){
                            activityForm.findField('activityType').setValue('NATIONWIDE');
                        }else{
                            activityForm.findField('activityType').setValue('REGION');
                        }
                        //设置申请人
                        activityForm.findField('proposerName').setValue(User.empName);
                        //设置申请人工号
                        activityForm.findField('proposerCode').setValue(User.empCode);
                        marketActivityWindow.setTitle(i18n('i18n.MarketActivityManagerView.activityAdd'));//'活动新增'
                        //设置全局id 在新增时把此id传回后台
                        marketActivityId = json.marketActivity.id;
                        operatorForMarket = 'add';
                        Ext.getCmp('deptGridPanelId').down('button[name=countDept]').show();
                        Ext.getCmp('deptGridPanelId').down('displayfield').setValue(0);
                    };
                    var failureFn = function(json){
                        MessageUtil.showErrorMes(json.message);
                        return;
                    }
                    //初始化活动
                    activityRecordData.initMarketActivity({},successFn,failureFn);
               }
            }, {//修改按钮
                xtype : 'button',
                name : 'updateButton',
                disabled : true,
                text : i18n('i18n.MarketActivityManagerView.update'),//活动修改
                handler : function() {
                    var me = this;
                    var selection = Ext.getCmp('marketActivityGridPanelId').getSelectionModel().getSelection();
                    if(selection.length != 1){
                        MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.pleaseSelectOne'));//请选择一条操作记录!
                        return;
                    }
                    var data = selection[0];
                    //成功操作
                    var successFn = function(json){
                        var marketActivityWindow=Ext.getCmp("marketActivityWindowId");//获取win
                        if(!marketActivityWindow){
                            marketActivityWindow=Ext.create('MarketActivityWindow',{id:'marketActivityWindowId'});
                        };
                        marketActivityWindow.show();
                        marketActivityWindow.setTitle(i18n('i18n.MarketActivityManagerView.activityUpdate'));//活动修改
                        operatorForMarket = 'update';
                        disp_disable(true);
                        Ext.getCmp('clientBasePanelId').down('combo[name = clientName]').enable();
                        marketActivityWindow.down('button[name=savebutton]').enable();
                        Ext.getCmp('activityFormPanelId').getForm().findField('contactsName').forceSelection = false;
                        loadReacordToWin(json);
                        changeTitle(Ext.getCmp('deptGridPanelId'),0,i18n('i18n.MarketActivityManagerView.deptName'));
                        Ext.getCmp('deptGridPanelId').down('button[name=countDept]').hide();
                        var deptCodes = new Array();
                        Ext.getCmp('deptGridPanelId').getStore().each(function(record){
                            deptCodes.push(record.get('deptStandardCode'));
                        });
                        var param = { 
                            'deptCodes' : deptCodes
                        };
                        var successFn= function(json){
                            Ext.getCmp('deptGridPanelId').down('displayfield').setValue(json.totalCount);
                        };
                        var failureFn= function(json){
                            MessageUtil.showErrorMes(json.message);
                            return;
                        }
                        activityRecordData.countActivityDeptNum(param,successFn,failureFn);
                    }
                    //失败提示
                    var failureFn = function(json){
                        MessageUtil.showErrorMes(json.message);
                        return;
                    }
                    marketActivityId = data.getId();
                    var param = {'id' : data.getId()};
                    activityRecordData.queryActivityAll(param,successFn,failureFn);
                }
            }, {//查看详情按钮
                xtype : 'button',
                name : 'lookButton',
                disabled : true,
                text : i18n('i18n.MarketActivityManagerView.viewDetails'),//查看详情
                handler : function() {
                    var me = this;
                    var selection = Ext.getCmp('marketActivityGridPanelId').getSelectionModel().getSelection();
                    if(selection.length != 1){
                        MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.pleaseSelectOne'));//请选择一条操作记录!
                        return;
                    }
                    var data = selection[0];
                    //如果查询成功 则显示窗体并加载所需数据
                    var successFn = function(json){
                        var marketActivityWindow=Ext.getCmp("marketActivityWindowId");//获取win
                        if(!marketActivityWindow){
                            marketActivityWindow=Ext.create('MarketActivityWindow',{id:'marketActivityWindowId'});
                        };
                        marketActivityWindow.show();
                        disp_disable(true);
                        marketActivityWindow.setTitle(i18n('i18n.MarketActivityManagerView.viewDetails'));//查看详情
                        operatorForMarket = 'look';
                        Ext.getCmp('activityFormPanelId').getForm().findField('contactsName').forceSelection = false;
                        loadReacordToWin(json);
                        changeTitle(Ext.getCmp('deptGridPanelId'),0,i18n('i18n.MarketActivityManagerView.deptName'));
                        Ext.getCmp('deptGridPanelId').down('button[name=countDept]').hide();
                        var deptCodes = new Array();
                        Ext.getCmp('deptGridPanelId').getStore().each(function(record){
                            deptCodes.push(record.get('deptStandardCode'));
                        });
                        var param = { 
                            'deptCodes' : deptCodes
                        };
                        var successFn= function(json){
                            Ext.getCmp('deptGridPanelId').down('displayfield').setValue(json.totalCount);
                        };
                        var failureFn= function(json){
                            MessageUtil.showErrorMes(json.message);
                            return;
                        }
                        activityRecordData.countActivityDeptNum(param,successFn,failureFn);
                    }
                    var failureFn = function(json){
                        MessageUtil.showErrorMes(json.message);
                        return;
                    }
                    var param = {'id' : data.getId()};
                    activityRecordData.queryActivityAll(param,successFn,failureFn);
                }
            }, {//下发客户群按钮
                xtype : 'button',
                name : 'issuedClientBtn',
                disabled : true,
                text : i18n('i18n.MarketActivityManagerView.issuedClient'),//下发客户群
                handler : function() {
                    var me = this;
                    var selection = Ext.getCmp('marketActivityGridPanelId').getSelectionModel().getSelection();
                    if(selection.length != 1){
                        MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.pleaseSelectOne'));//请选择一条操作记录! 
                        return;
                    }
                    var data = selection[0];
                    var issuedClientWindow = Ext.getCmp('issuedClientWindowId');
                    if(Ext.isEmpty(issuedClientWindow)){
                        issuedClientWindow = Ext.create('IssuedClientWindow',{id : 'issuedClientWindowId'});
                    }
                    issuedClientWindow.show();
                    issuedClientWindow.down('panel').data = data;
                    marketActivityId = data.getId();
                    //加载下发客户群时的数据
                    Ext.getCmp('issuedFormPanelId').getForm().findField('activityName').setValue(data.get('activityName'));
                }
            }]
        },{
            xtype:'middlebuttonpanel' 
        },{
            xtype:'rightbuttonpanel',
            items:[{
                xtype:'button',    
                text:i18n('i18n.MarketActivityManagerView.search'),//'查询',
                handler:function(){
                    var grid = Ext.getCmp('marketActivityGridPanelId');
                    var form = Ext.getCmp('marketActivitySearchConditionId').getForm();
                    var startTime = form.findField('startTime').getValue();
                    var endTime = form.findField('endTime').getValue();
                    if(!form.isValid()){
                        return;
                    }
                    if(startTime > endTime){
                        MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.startMoreThanEnd'));//开始时间不能大于结束时间! 
                        return;
                    }
                    grid.getStore().loadPage(1);
                }
            }]
        }];
    },
    initComponent:function(){
        this.items = this.getItems();
        this.callParent();
    }
});

//客户群下发panel
Ext.define('IssuedFormPanel',{  
    extend : 'SearchFormPanel',
    id : 'issuedFormPanelId',
    data : null,
    layout : {
        type : 'table',
        columns : 6
    },
    defaults : {
        labelWidth : 80,
        width : 240,
        labelAlign: 'right'
    },
    getItems : function (){
        var me = this;
        return [{
            //市场推广活动名称
            xtype : 'textfield',
            fieldLabel:i18n('i18n.MarketActivityManagerView.marketActivityName'),
            readOnly : true,
            labelWidth : 100,
            height : 20,
            name : 'activityName',
            width : 480,
            colspan : 5
        },{
            xtype:'hiddenfield',
            name : 'clientId',
            colspan : 1
        },{
            //客户群名称
            xtype : 'combo',
            fieldLabel : i18n('i18n.MarketActivityManagerView.clientName'),
            name : 'clientName',
            store:Ext.create('LoadUsedClientBaseStore',{
                    listeners:{
                        beforeload:function(store, operation, eOpts){
                            if(clientId==Ext.getCmp('issuedFormPanelId').down('combo[name=clientName]').getValue()&&Ext.getCmp('issuedFormPanelId').down('combo[name=clientName]').getValue()!=null){
                                return false;
                            }
                            Ext.apply(operation,{
                                params : {
                                        'id':Ext.getCmp('issuedFormPanelId').data.getId()
                                    }
                                }
                            );  
                        }
                    }
                }),
                triggerAction : 'all',
                displayField:'clientBaseName',
                valueField:'clientBaseId',
                forceSelection :true,
                hideTrigger:false,
                allowBlank : false,
                editable : false,
                colspan : 2,
                listConfig: {
                    minWidth :300,
                    loadMask:false,
                    getInnerTpl: function() {
                         return '{clientBaseName}';
                    }
                },
                listeners:{
                    change:DButil.comboSelsct,
                    select:function(combo,records){
                        clientId = records[0].get('clientBaseId')
                        var id = records[0].get('id');
                        combo.ownerCt.getForm().findField('clientId').setValue(id);
                        clientNum = records[0].get('clientNum');
                        combo.ownerCt.getForm().findField('clientNum').setValue(clientNum);
                    },
                    beforequery: function(qe){
                        delete qe.combo.lastQuery;
                    }
                }
        },{
            //客户群数量
            xtype : 'textfield',
            fieldLabel:i18n('i18n.MarketActivityManagerView.clientNum'),
            readOnly : true,
            name : 'clientNum',
            colspan : 4
        },{
            //开始时间
            fieldLabel:i18n('i18n.MarketActivityManagerView.marketingTimeLimit'),
            xtype: 'datefield',
            name : 'planStartTime',
            format : 'Y-m-d',
            allowBlank : false,
            value : new Date(),
            minValue : new Date(),
            editable:false,
            colspan : 2
        },{
            //结束时间
            fieldLabel:'——',   
            xtype: 'datefield',
            name : 'planEndTime',
            format : 'Y-m-d',
            labelSeparator : '',
            minValue : new Date(),
            allowBlank : false,
            editable:false,
            colspan: 2
    }];
    },
    initComponent:function(){
        var me = this;
        me.items = me.getItems();
        this.callParent();
    }  
});  


//线路要求
var lineRequest = null;
//线路数据
var lineDate = null;
//加载数据到界面中
function loadReacordToWin(json){
    Ext.getCmp('marketActivityWindowId').down('tabpanel').setActiveTab(1);
    //获取活动实体
    var marketActivity = new ActivityMainModel();
    json.marketActivity.secondTrade = null;
    marketActivity.set(json.marketActivity);
    //设置线路类型
    lineRequest = json.marketActivity.lineRequest;
    
    //加载数据到前台界面
    Ext.getCmp('activityFormPanelId').getForm().loadRecord(marketActivity);
    if(json.marketActivity.couponInfo.length != 0){
        Ext.getCmp('preferentialInformationPanelId').down('combo[name=preferType]').setValue('COUPON');
    }else{
        Ext.getCmp('preferentialInformationPanelId').down('combo[name=preferType]').setValue('DISCOUNT');
    }
    if(!Ext.isEmpty(json.marketActivity.workFlowNum)){
        Ext.getCmp('preferentialInformationPanelId').down('textfield[name=workFlowNum]').setValue(json.marketActivity.workFlowNum);
    }
    Ext.getCmp('useConditionFormPanelId').getForm().loadRecord(marketActivity);
    secondTrade = Ext.getCmp('activityJoinConditionPanelId').down('combo[name=secondTrade]');
    secondTrade.reset();
//    secondTrade.getStore().load({  
//	    params: {  
//            'trades' : json.marketActivity.custTrade  
//	    },
//	    callback :function(){
//	        secondTrade.setValue(json.marketActivity.secondTrade)
//	    }
//    });
    var successFn = function(json){
        secondTrade.forceSelection=false;//二级行业
        secondTrade.setValue(json.secondTrade.toString());
        secondTrade.forceSelection=true;//二级行业
    }
    var failureFn = function(json){
        MessageUtil.showErrorMes(json.message);
        return false;
    }
    ClientBaseStore.prototype.searchSecondTradesToString({'conditionId' :json.marketActivity.id,'conditionType' : "ACTIVITY"},successFn,failureFn) 
    Ext.getCmp('activityJoinConditionPanelId').getForm().loadRecord(marketActivity);
    //折扣
    Ext.getCmp('preferentialInformationPanelId').getStore().loadData(json.marketActivity.discountInfo);
    //优惠券
    Ext.getCmp('couponGridPanelId').getStore().loadData(json.marketActivity.couponInfo);
    //物料
    Ext.getCmp('materialGridPanelId').getStore().loadData(json.marketActivity.materialInfo);
    //客户群
    Ext.getCmp('clientBasePanelId').getStore().loadData(json.marketActivity.clientBase);
    //上传附件
    Ext.getCmp('accessoryPanelId').getStore().loadData(json.marketActivity.files);
    //加载部门数据到前台
    Ext.getCmp('deptGridPanelId').getStore().loadData(json.marketActivity.activityDepts);
    lineDate = json.marketActivity.lineDepts
}