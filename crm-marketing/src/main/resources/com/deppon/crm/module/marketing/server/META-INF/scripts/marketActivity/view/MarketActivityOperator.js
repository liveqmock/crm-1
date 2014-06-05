//市场活动推广主form
Ext.define('ActivityFormPanel',{  
    extend : 'BasicFormPanel',
    id : 'activityFormPanelId',
    layout : {
        type : 'table',
        columns : 4
    },
    height : 180,
    defaults : {
        labelWidth : 80,
        width : 190,
        labelAlign: 'right'
    },
    defaultType: 'textfield',
    getItems : function (){
        var me = this;
        return [{
        //活动类别
        xtype : 'combo',
        fieldLabel:required+i18n('i18n.MarketActivityManagerView.activityCategory'),//活动类别
        name : 'activityCategory',
        store:getDataDictionaryByName(DataDictionary,'ACTIVITY_CATEGORY'),
        queryMode:'local',
        displayField:'codeDesc',
        valueField:'code',
        cls : 'readonly',
        forceSelection :true,
        readOnly : true,
        listeners:{
            change:DButil.comboSelsct
        }
    },{
        //活动类型
        xtype : 'combo',
        fieldLabel:required+i18n('i18n.MarketActivityManagerView.activityType'),//活动类型
        name : 'activityType',
        store:getDataDictionaryByName(DataDictionary,'ACTIVITY_TYPE'),
        readOnly : true,
        cls : 'readonly',
        queryMode:'local',
        displayField:'codeDesc',
        valueField:'code',
        forceSelection :true,
        listeners:{
            change:DButil.comboSelsct
        }
    },{
        //活动名称
        fieldLabel:required+i18n('i18n.MarketActivityManagerView.activityName'),//活动名称
        allowBlank :false,
        blankText : i18n('i18n.MarketActivityManagerView.activityNameCantNull'),//活动名称不能为空！
        maxLength : 40,
        name : 'activityName',
        listeners:{
            change:function(field){
                viewQuickTips(field);
            },
            blur:function(field){
                var successFn= function(json){
                };
                var failureFn= function(json){
                    MessageUtil.showErrorMes(json.message);
                    field.reset();
                    return;
                }
                param = {
                    'marketActivityName' : Ext.String.trim(field.getValue())
                }
                //验证名字是否重复
                activityRecordData.checkHaveSamNameMarketActivity(param,successFn,failureFn);
          }
       }
    },{
        //活动编码
        fieldLabel:required+i18n('i18n.MarketActivityManagerView.activityCode'),
        name : 'activityCode',
        readOnly : true
    },{
        //申请人
        fieldLabel:required+i18n('i18n.MarketActivityManagerView.proposerName'),
        name : 'proposerName',
        readOnly : true
    },{
        //申请人工号
        fieldLabel:required+i18n('i18n.MarketActivityManagerView.proposerCode'),
        name : 'proposerCode',
        readOnly : true
    },{
        //开始时间
        fieldLabel:required+i18n('i18n.MarketActivityManagerView.startTime'),
        xtype: 'datetimefield',
        name : 'startTime',
        format: 'Y-m-d H:i:s',
        defaultTime:[0,0,0],
        allowBlank : false,
        blankText : i18n('i18n.marketActivity.activityStartTime.isNull'),//活动开始时间不能为空！
        value : new Date(new Date().getFullYear(),new Date().getMonth(),new Date().getDate(),0,0,0),
        minValue : new Date(),
        editable:false
    },{
        //结束时间
        fieldLabel:required+i18n('i18n.MarketActivityManagerView.endTime'),   
        xtype: 'datetimefield',
        name : 'endTime',
        format : 'Y-m-d H:i:s',
        minValue : new Date(),
        defaultTime:[23,59,59],
        allowBlank : false,
        blankText : i18n('i18n.marketActivity.activityEndTime.isNull'),//活动结束时间不能为空！
        editable:false,
        colspan: 2
    },{
        //活动主题
        xtype : 'textarea',
        fieldLabel:required+i18n('i18n.MarketActivityManagerView.topic'),
        name : 'topic',
        height:20,
        allowBlank :false,
        blankText : i18n('i18n.MarketActivityManagerView.topicCantNull'),//活动主题不能为空！
        colspan: 4,
        maxLength : 200,
        width : 770,
        listeners:{
            change:function(field){
                viewQuickTips(field);
            }
        }
        
    },{
        //对外宣传语
        xtype : 'textarea',
        fieldLabel:required+i18n('i18n.MarketActivityManagerView.slogan'),
        height:20,
        name : 'slogan',
        allowBlank :false,
        blankText : i18n('i18n.MarketActivityManagerView.sloganCantNull'),//外宣传语不能为空！
        maxLength : 300,
        colspan: 4,
        width : 770,
        listeners:{
            change:function(field){
                viewQuickTips(field);
            }
        }
    },{
        //活动内容
        xtype : 'textarea',
        fieldLabel:required+i18n('i18n.MarketActivityManagerView.content'),
        height:20,
        name : 'content',
        allowBlank :false,
        blankText : i18n('i18n.MarketActivityManagerView.contentCantNull'),//活动内容不能为空！
        maxLength : 500,
        colspan: 4,
        width : 770,
        listeners:{
            change:function(field){
                viewQuickTips(field);
            }
        }
    },{
        //第一个月目标
        fieldLabel:required+i18n('i18n.MarketActivityManagerView.target1'),
        allowBlank :false,
        labelWidth : 90,
        maxLength : 20,
//        colspan: 2,
        width : 200,
        name : 'target1'
    },{
        //第二个月目标
        fieldLabel:i18n('i18n.MarketActivityManagerView.target2'),
        maxLength : 20,
        name : 'target2'
    },{
        //第三个月目标
        fieldLabel:i18n('i18n.MarketActivityManagerView.target3'),
        maxLength : 20,
        name : 'target3'
    },{
        xtype:'hiddenfield',
        name : 'contactsId'
    },{
        fieldLabel : required+i18n('i18n.MarketActivityManagerView.contactsName'),//对接人
        name : 'contactsName',
        allowBlank :false,
        maxLength : 20,
        blankText : i18n('i18n.MarketActivityManagerView.contactsNameCantNull'),//对接人不能为空！
        xtype:'combobox',
        store:Ext.create('UserInfoStore',{
            listeners:{
                beforeload:function(store, operation, eOpts){
                    var contactsName = Ext.getCmp("activityFormPanelId").down('combobox[name=contactsName]').getValue();
                    if(!Ext.isEmpty(contactsName) && contactsName.length > 20){
                        return false;
                    };
                    if(dName==Ext.String.trim(Ext.getCmp("activityFormPanelId").down('combobox[name=contactsName]').getRawValue())){
                        return false;
                    }else{
                        Ext.apply(operation,{
                            params : {
                                    'condition.empCode':Ext.String.trim(Ext.getCmp("activityFormPanelId").down('combobox[name=contactsName]').getRawValue())
                                }
                            }
                        ); 
                    }
                }
            }
        }),
        triggerAction : 'all',
        displayField:'empName',
        valueField:'empName',
        hideTrigger:false,
        pageSize: 10,
        minChars:0,
        queryDelay:800,
        listConfig: {
            minWidth :300,
            getInnerTpl: function() {
                 return '{empCode}({empName})';
            }
        },
        listeners:{
            change:function(combo){
                if(Ext.isEmpty(combo.getValue())){
                    combo.setValue("");
                }
            },
            select:function(combo,records){
                var mobilePhone = records[0].get('mobilePhone');
                dName=records[0].get("empName");
                var contactsId = records[0].get('userId');
                Ext.getCmp('activityFormPanelId').down('textfield[name=contactsTel]').setValue(mobilePhone);
                Ext.getCmp('activityFormPanelId').down('hiddenfield[name=contactsId]').setValue(contactsId);
            },
            expand :function(combo){
                combo.getStore().load();
            }
        }
    },{
        fieldLabel : required+i18n('i18n.MarketActivityManagerView.contactsTel'),//联系电话
        name : 'contactsTel',
        allowBlank :false,
        blankText : i18n('i18n.MarketActivityManagerView.contactsTelCantNull'),//联系电话不能为空！
        regex:/^[0-9-]+$/,
        regexText:i18n('i18n.MarketActivityManagerView.telNumNotRight'),//请输入正确的手机号
        maxLength : 20
//        colspan : 2
    },{
        xtype : 'textareafield',
        fieldLabel : required+i18n('i18n.MarketActivityManagerView.applyReason'),//申请事由
        name : 'applyReason',
        allowBlank :false,
        height : 20,
        blankText : i18n('i18n.MarketActivityManagerView.applyReasonCantNull'),//申请事由不能为空！
        maxLength : 50,
        colspan : 2,
        width : 390,
        listeners:{
            change:function(field){
                viewQuickTips(field);
            }
        }
    }];
    },
    initComponent:function(){
        var me = this;
        me.items = me.getItems();
        this.callParent();
    }  
});  

var dCode=null,dName=null;

////第一个tab 物料申请信息


Ext.define('MaterialGridPanel',{
    extend : 'PopupGridPanel',
    id : 'materialGridPanelId',
    store : Ext.create('ActivityOptionDataStore',{id : 'materialDataStoreId'}),
    selModel : Ext.create('Ext.selection.CheckboxModel',{
        mode:'SINGLE',
        allowDeselect:true,
        listeners : {
            select:function(records){
                Ext.getCmp('materialGridPanelId').down('button[name=delBtn]').enable();
            },
            deselect:function(records){
                Ext.getCmp('materialGridPanelId').down('button[name=delBtn]').disable();
            }
        }
    }),
    columns: [
        //申请物品名称
        { header: i18n('i18n.MarketActivityManagerView.materialName'),  dataIndex: 'type',width: 370},
        //物品数量
        { header: i18n('i18n.MarketActivityManagerView.materialNum'), dataIndex: 'value', flex: 1 }
    ],
    tbar :[{
         xtype : 'textfield', 
         name : 'materialName',
         fieldLabel : i18n('i18n.MarketActivityManagerView.materialName'),//申请物品名称
         maxLength : 20,
         listeners : {
            //当都不为空时添加按钮启用
            change : function(){
                var materialNum = Ext.getCmp('materialGridPanelId').down('textfield[name=materialNum]').getValue();
                var materialName = Ext.getCmp('materialGridPanelId').down('textfield[name=materialName]').getValue();
                if(Ext.isEmpty(materialNum)||Ext.isEmpty(materialName)){
                    Ext.getCmp('materialGridPanelId').down('button[name=addBtn]').disable();
                }else{
                    Ext.getCmp('materialGridPanelId').down('button[name=addBtn]').enable();
                }
            }
         }
    },{
        xtype : 'textfield',
        name : 'materialNum',
        fieldLabel : i18n('i18n.MarketActivityManagerView.materialNum'),//物品数量
        maxLength : 20,
        regex : /^\d*$/,
        regexText : i18n('i18n.MarketActivityManagerView.numberLess20'),//20位以内整数
        listeners : {
            //当都不为空时添加按钮启用
            change : function(){
                var materialNum = Ext.getCmp('materialGridPanelId').down('textfield[name=materialNum]').getValue();
                var materialName = Ext.getCmp('materialGridPanelId').down('textfield[name=materialName]').getValue();
                if(Ext.isEmpty(materialNum)||Ext.isEmpty(materialName)){
                    Ext.getCmp('materialGridPanelId').down('button[name=addBtn]').disable();
                }else{
                    Ext.getCmp('materialGridPanelId').down('button[name=addBtn]').enable();
                }
            }
         }
    },{
        xtype : 'button',
        name : 'addBtn',
        text : i18n('i18n.MarketActivityManagerView.add'),//添加
        disabled : true,
        handler : function(){
            var materialGrid = Ext.getCmp('materialGridPanelId');
            if(materialGrid.getStore().count() >= 10){
                MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.canNotMoreTen'));//该控件最多不能超过10条！
                return false;
            };
            var materialName = materialGrid.down('textfield[name=materialName]');
            var materialNum = materialGrid.down('textfield[name=materialNum]');
            //如果输入不合法则返回
            if(!materialNum.isValid()||!materialName.isValid()){
                return ;
            }
            
            //如果添加的物品名称相同则不允许添加
            var type = Ext.String.trim(materialName.getValue());
            if(materialGrid.getStore().find('type',type,0,false,true,true) != -1){
                MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.haveExist'));//已存在该物品！
                return false;
            }
            
            var materialModel = new ActivityOptionModel();
            //设置3个GRID的类型与物品名称与值
            materialModel.set('infoType','APPLYGOODS');
            materialModel.set('type',type);
            materialModel.set('value',materialNum.getValue());
            Ext.getCmp('materialGridPanelId').getStore().add(materialModel);
            materialModel.commit();
            materialNum.setValue(null);
            materialName.setValue(null);
        }
    },{
        xtype : 'button',
        name : 'delBtn',
        text : i18n('i18n.MarketActivityManagerView.delete'),//删除
        disabled : true,
        handler : function(){
            var materialGrid = Ext.getCmp('materialGridPanelId');
            var selection = materialGrid.getSelectionModel().getSelection();
            if(selection.length != 1){
                MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.pleaseSelectOne'));//请选择一条操作记录!
                return false;
            };
            //是否删除选中的记录？
            MessageUtil.showQuestionMes(i18n('i18n.MarketActivityManagerView.deleteThisSure'), function(e){ 
                if (e == 'yes') {
                    materialGrid.getStore().remove(selection);
                    MessageUtil.showInfoMes(i18n('i18n.MarketActivityManagerView.deleteSuccess'));  //删除成功
                    Ext.getCmp('materialGridPanelId').down('button[name=delBtn]').disable();
                }
            });
        }
    }],
    initComponent:function(){
        this.callParent();
    } 
})

//第二个tab 活动参与条件
Ext.define('ActivityJoinConditionPanel',{
    extend : 'BasicFormPanel',
    id : 'activityJoinConditionPanelId',
    layout : {
        type : 'table',
        columns : 12
    },
    defaultType: 'textfield',
    getItems : function(){
        var me = this;
        return [{
            //产品类型
            fieldLabel : i18n('i18n.MarketActivityManagerView.productType'),//产品类型
            xtype : 'combo',
            name : 'productType',
            store:getDataDictionaryByName(DataDictionary,'CLIENTBASE_PRODUCT_TYPE'),
            editable: false,
            queryMode:'local',
            multiSelect:true,
            displayField:'codeDesc',
            valueField:'code',
            forceSelection :true,
            colspan : 3,
            listeners:{
                change:function(field){
                    viewQuickTips(field);
                }
            }
        },{
            //客户行业 一级
            xtype : 'combo',
            fieldLabel : i18n('i18n.MarketActivityManagerView.custTrade'),//客户行业
            name : 'custTrade',
            store:getDataDictionaryByName(DataDictionary,'TRADE'),
            queryMode:'local',
            multiSelect:true,
            displayField:'codeDesc',
            editable: false,
            valueField:'code',
            triggerAction: 'all',
            forceSelection :true,
            colspan : 3,
            listConfig: {
                loadMask:false
            },
            listeners:{
                change:function(field){
                    viewQuickTips(field);
                    if(operatorForMarket == 'add'){
                        secondTrade = Ext.getCmp('activityJoinConditionPanelId').down('combo[name=secondTrade]');
                        secondTrade.reset();
                        secondTrade.getStore().load({  
                            params: {  
                                'trades' : field.getValue()  
                            }  
                        });  
                    } 
                }
            }
        },{//客户行业 二级
            xtype : 'combo',
            name : 'secondTrade',
            store: Ext.create('SecondTradesStore'),
            queryMode:'local',
            multiSelect:true,
            displayField:'codeDesc',
            editable: false,
            valueField:'code',
            listConfig: {
                loadMask:false
            },
            triggerAction: 'all',
            forceSelection :true,
            colspan : 6,
            listeners: {
                change:function(field){
                    viewQuickTips(field);
                }
            }
        },{
            //开单品名
            fieldLabel : i18n('i18n.MarketActivityManagerView.itemNames'),
            name : 'itemNamesTemp',
            width : 210,
            maxLength : 20,
            regex:/^[^、]*$/,
            regexText:i18n('i18n.MarketActivityManagerView.itemNamesNo'),//不能输入、
            colspan : 1
        },{
            //+号
            xtype : 'button',
            name : 'itemNamesAdd',
            colspan : 1,
            margin : '0 0 0 0',
            width : 20,
            padding:'0 0 0 0',
            text : '＋',
            handler:function(btn){
                var itemNamesTemp = btn.up().down('textfield[name=itemNamesTemp]');
                var itemNames = btn.up().down('displayfield[name=itemNames]');
                var value = null;
                if(!itemNamesTemp.isValid()){
                    return;
                }
                //如果开单品名中没有值则value为框中值 否则为原来值+、+框中值
                if(Ext.isEmpty(itemNames.getValue())){
                    value = Ext.String.trim(itemNamesTemp.getValue());
                }
                else{
                    value = Ext.String.trim(itemNames.getValue()+'、'+itemNamesTemp.getValue());
                }
                if(Ext.isEmpty(Ext.String.trim(itemNamesTemp.getValue())) || Ext.isEmpty(value)){
                    return;                
                }
                if(value.length > 20){
                    //开单品名最长为20位
                    MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.itemNamesLength20'));
                    return;
                }
                itemNames.setValue(value);
                itemNamesTemp.reset();
            }
        },{
            //-号
            xtype : 'button',
            name : 'itemNamesMinus',
            colspan : 1,
            margin : '0 0 0 0',
            width : 20,
            padding:'0 0 0 0',
            text : '－',
            handler:function(btn){
                var itemNames = btn.up().down('displayfield[name=itemNames]');
                itemNames.reset();
            }
        },{
            xtype : 'numberfield',
            colspan : 3,
            fieldLabel : i18n('i18n.MarketActivityManagerView.minBillAmt'),
            minValue: 0, // 不允许负数
            negativeText : i18n('i18n.MarketActivityManagerView.valueNotNegative'),//此值不允许为负数
            maxValue : 99999999.999,
            name : 'minBillAmt',
            decimalPrecision : 3
        },{
            xtype: 'numberfield',
            name : 'maxBillAmt',
            width : 150,
            colspan : 3,
            fieldLabel : i18n('i18n.MarketActivityManagerView.yuan')+' -',//元  -
            minValue: 0, // 不允许负数
            negativeText : i18n('i18n.MarketActivityManagerView.valueNotNegative'),//此值不允许为负数
            maxValue : 99999999.999,
            labelSeparator : '',
            decimalPrecision : 3,
            labelWidth : 21
    
        },{
            xtype:'label',
            colspan : 3,
            text : i18n('i18n.MarketActivityManagerView.yuan')//元
        },{
            //开单品名 显示
            xtype : 'displayfield',
            name : 'itemNames',
            width : 250,
            maxLength : 20,
            colspan : 3
        },{
            xtype: 'numberfield',
            colspan : 3,
            fieldLabel : i18n('i18n.MarketActivityManagerView.minCargoWeight'),//货物重量
            name : 'minCargoWeight',
            maxValue : 99999999.999,
            minValue: 0, // 不允许负数
            negativeText : i18n('i18n.MarketActivityManagerView.valueNotNegative'),//此值不允许为负数
            decimalPrecision : 3
        },{
            xtype: 'numberfield',
            colspan : 3,
            width : 150,
            fieldLabel : 'KG  -',
            name : 'maxCargoWeight',
            maxValue : 99999999.999,
            minValue: 0, // 不允许负数
            negativeText : i18n('i18n.MarketActivityManagerView.valueNotNegative'),//此值不允许为负数
            labelSeparator : '',
            decimalPrecision : 3,
            labelWidth : 24 
        },{
            xtype:'label',
            colspan : 3,
            text : 'KG'
        },{
            fieldLabel : i18n('i18n.MarketActivityManagerView.orderSource'),//订单来源
            xtype:'combobox',
            colspan : 3,
            name : 'orderSource',
            editable:false,
            queryMode: 'local',
            multiSelect:true,
            displayField:'codeDesc',
            valueField:'code',  
            forceSelection :true,
            store: getOrderResourcesStore(),
            listeners: {
                change:function(field){
                    viewQuickTips(field);
                }
            }
        },{
            xtype: 'numberfield',
            colspan : 3,
            fieldLabel : i18n('i18n.MarketActivityManagerView.minCargoVolume'),//货物体积
            name : 'minCargoVolume',
            maxValue : 99999999.999,
            minValue: 0, // 不允许负数
            negativeText : i18n('i18n.MarketActivityManagerView.valueNotNegative'),//此值不允许为负数
            decimalPrecision : 3
        },{
            xtype: 'numberfield',
            width : 150,
            colspan : 3,
            fieldLabel : 'm³  -',
            name : 'maxCargoVolume',
            maxValue : 99999999.999,
            minValue: 0, // 不允许负数
            negativeText : i18n('i18n.MarketActivityManagerView.valueNotNegative'),//此值不允许为负数
            labelSeparator : '',
            decimalPrecision : 3,
            labelWidth : 25
        },{
            xtype:'label',
            colspan : 1,
            text : 'm³'
        },{
            xtype : 'button',
            colspan : 2,
            text : i18n('i18n.MarketActivityManagerView.lineRequest'),//线路区域要求
            padding : '2 5 2 5',
            name : 'lineRequest',
            handler : function(){
                var lineWindow = Ext.getCmp('lineWindowId');
                if(Ext.isEmpty(lineWindow)){
                    lineWindow = Ext.create('LineWindow',{id : 'lineWindowId'});                
                }
                lineWindow.show();
            }
        }];
    },
    initComponent:function(){
        var me = this;
        me.items = me.getItems();
        this.callParent();
    } 
});


/**
 * 开展部门tree
 */
Ext.define('DeptTreeForActivity',{
    extend : 'Ext.tree.Panel',
    id:'deptTreeForActivityId',
    listeners: {
        scrollershow: function(scroller) {
            if (scroller && scroller.scrollEl) {
                    scroller.clearManagedListeners(); 
                    scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
            }
        }
     },
    //指定树的宽度
    width:320,
    height:305,
    //绑定一个store
    store:  Ext.create('DeptTreeStore',{
        model : 'DeptTreeModel',
        proxy: {
                //内存方式
                type:'memory',
                //绑定数据
                data:null
        }
    }),
    //自动增加滚动条
    autoScroll:true,
    //动画效果
    animate:true,
    //使用vista风格的箭头图标，默认为false
    useArrows: true,
    //树节点是否可见
    rootVisible: false,
    //树对象增加一个边框
    frame: true,
    //定义树的标题
    title: i18n('i18n.MarketActivityManagerView.developDept'),//开展部门
    listeners:{
        checkchange:function(node,checked){
            var deptGrid = Ext.getCmp("deptGridPanelId");
            if(checked){
                if(!node.get('canSelect')){
                    MessageUtil.showMessage('您没有此部门权限！');
                    node.set('checked', false);
                    return;
                }
                if(deptGrid.getStore().getCount()>=100){
                    MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.deptMost100'));//部门最多添加100条！
                    node.set('checked', false);
                    return;
                }
                deptGrid.getStore().add({'deptStandardCode':node.get('deptStandardCode'),'deptName':node.get('text'),'id':node.data.id});
                changeTitle(deptGrid,0,i18n('i18n.MarketActivityManagerView.deptName'));
            }else{
                var index = Ext.getCmp('deptGridPanelId').getStore().find('id',node.data.id,0,false,true,true);
                deptGrid.getStore().removeAt(index);
                changeTitle(deptGrid,0,i18n('i18n.MarketActivityManagerView.deptName'));
            }
        }
    },
    initComponent:function(){
        this.callParent();
    } 
})


/**
 * 线路区域tree
 */
Ext.define('LineTreeForActivity',{
    extend : 'Ext.tree.Panel',
    id:'lineTreeForActivityId',
    listeners: {
        scrollershow: function(scroller) {
            if (scroller && scroller.scrollEl) {
                    scroller.clearManagedListeners(); 
                    scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
            }
        }
     },
    //指定树的宽度
    width:320,
    height:415,
    //绑定一个store
    store:  Ext.create('DeptTreeStore',{
        model : 'DeptTreeModel',
        proxy: {
                //内存方式
                type:'memory',
                //绑定数据
                data:null
        }
    }),
    //自动增加滚动条
    autoScroll:true,
    //动画效果
    animate:true,
    //使用vista风格的箭头图标，默认为false
    useArrows: true,
    //树节点是否可见
    rootVisible: false,
    //树对象增加一个边框
    frame: true,
    //定义树的标题
    listeners:{
        checkchange:function(node,checked){
            if (checked) {
                if(Ext.getCmp("lineTreeGridPanelId").getStore().getCount()>=100){
                    MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.lineMost100'));//线路最多添加100条！
                    node.set('checked', false);
                    return;
                }
                if(Ext.getCmp('lineTreeRequireId').getValue()=='LEAVE'){
                    Ext.getCmp('lineTreeGridPanelId').getStore().add({'leaveDeptCode':node.get('deptStandardCode'),'leavedDeptName':node.get('text')});
                }
                if(Ext.getCmp('lineTreeRequireId').getValue()=='ARRIVE'){
                    Ext.getCmp('lineTreeGridPanelId').getStore().add({'arriveDeptCode':node.get('deptStandardCode'),'arriveDeptName':node.get('text')});
                }
                changeTitle(Ext.getCmp('lineTreeGridPanelId'),1,i18n('i18n.MarketActivityManagerView.leavedDeptName'));
                changeTitle(Ext.getCmp('lineTreeGridPanelId'),2,i18n('i18n.MarketActivityManagerView.arriveDeptName'));
            }else{
                var index =null;
                if(Ext.getCmp('lineTreeRequireId').getValue()=='LEAVE'){
                    index = Ext.getCmp('lineTreeGridPanelId').getStore().find('leaveDeptCode',node.get('deptStandardCode'),0,false,true,true);
                }
                if(Ext.getCmp('lineTreeRequireId').getValue()=='ARRIVE'){
                    index = Ext.getCmp('lineTreeGridPanelId').getStore().find('arriveDeptCode',node.get('deptStandardCode'),0,false,true,true);
                }
                Ext.getCmp('lineTreeGridPanelId').getStore().removeAt(index);
                changeTitle(Ext.getCmp('lineTreeGridPanelId'),1,i18n('i18n.MarketActivityManagerView.leavedDeptName'));
                changeTitle(Ext.getCmp('lineTreeGridPanelId'),2,i18n('i18n.MarketActivityManagerView.arriveDeptName'));
            }
        }
    },getTbar : function(){
            var me = this;
            return [{
            fieldLabel : i18n('i18n.ClientBaseManegerView.LineRequset'),//线路要求
            id : 'lineTreeRequireId',
            width:165,
            labelWidth:70,
            xtype : 'combo',
            editable: false,
            store:getDataDictionaryByName(DataDictionary,'LINE_TYPE'),
            queryMode:'local',
            displayField:'codeDesc',
            valueField:'code',
            forceSelection :true,
            listeners:{
                change:DButil.comboSelsct,
                select : function(t){
                    var me = this;
                    var oldValue = oldValueUp;
                    var newValue = t.getValue();
                    if(Ext.getCmp("lineGridPanelId").getStore().getCount()===0 && 
                        Ext.getCmp("lineTreeGridPanelId").getStore().getCount()===0){
                        Ext.getCmp('lineTreeForActivityId').getRootNode().cascadeBy(function(node){
                               node.set('checked', false);
                           })
                        Ext.getCmp('lineTreeForActivityId').collapseAll();
                        setActivityLine(newValue);
                    }else{
                        t.setValue(oldValue);
                        //改变线路会清空数据,是否确认？
                        MessageUtil.showQuestionMes(i18n('i18n.MarketActivityManagerView.changeLineSure'), function(e){ 
                            if (e == 'yes') {
                               t.setValue(newValue);
                               Ext.getCmp("lineGridPanelId").getStore().removeAll();
                               Ext.getCmp("lineTreeGridPanelId").getStore().removeAll();
                               //获取到树并清空所有的勾选项
                               Ext.getCmp('lineTreeForActivityId').getRootNode().cascadeBy(function(node){
                                   node.set('checked', false);
                               })
                               Ext.getCmp('lineTreeForActivityId').collapseAll();
                               setActivityLine(newValue);
                            }
                        });
                    }
                }
            }
        }];
    },
    initComponent:function(){
        var me = this;
        me.tbar = me.getTbar();
        me.hide();
        this.callParent();
    } 
});

/**
 * 参与部门Grid
 */
Ext.define('DeptGridPanel',{
    extend : 'PopupGridPanel',
    id:'deptGridPanelId',
    autoScroll : true,
//    height: 200,
//    width : 500,
    listeners: {
        scrollershow: function(scroller) {
            if (scroller && scroller.scrollEl) {
                    scroller.clearManagedListeners(); 
                    scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
            }
        }
     },
    flex:1,
//    selModel:Ext.create('Ext.selection.CheckboxModel'),
    store:Ext.create('DeptStore'),
    columns: [
        //部门名称
       { header: i18n('i18n.MarketActivityManagerView.deptName'), dataIndex: 'deptName', flex: 1}
    ],
    fbar: [{
        xtype: 'displayfield', 
        name : 'deptCount',
        width : 120,
        labelAlign: 'right',
        labelWidth : 80,
        fieldLabel:i18n('i18n.MarketActivityManagerView.deptNumber'),//部门数
        value : 0
    },{
        xtype : 'button',
        name : 'countDept',
        text : i18n('i18n.MarketActivityManagerView.countDeptNum'),//计算部门数
        width : 100,
        handler: function(){
            if(Ext.getCmp('deptGridPanelId').getStore().count()<1){
                MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.beforeAdd'));
                return;
            }
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
    }],
    initComponent:function(){
        var me = this;
        this.callParent();
    } 
})


/**
 * 线路树展示Grid
 */
Ext.define('LineTreeGridPanel',{
    extend : 'PopupGridPanel',
    id:'lineTreeGridPanelId',
    autoScroll : true,
    flex:1,
    selModel:Ext.create('Ext.selection.CheckboxModel'),
    store:Ext.create('UseWalkGoodsLineStore'),
    columns: [
        //出发区域
        { header: i18n('i18n.MarketActivityManagerView.leavedDeptName'), dataIndex: 'leavedDeptName',sortable:false, flex: 1},
        //到达区域
        { header: i18n('i18n.MarketActivityManagerView.arriveDeptName'), dataIndex: 'arriveDeptName',sortable:false, flex: 1 }
    ],
    initComponent:function(){
        var me = this;
        me.hide();
        this.callParent();
    } 
})

/**
 * 线路区域Grid
 */
Ext.define('LineGridPanel',{
    extend : 'PopupGridPanel',
    id:'lineGridPanelId',
    autoScroll : true,
    flex:1,
    selModel:Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE',allowDeselect:true}),
    store:Ext.create('UseWalkGoodsLineStore'),
    columns: [
        //出发区域
        { header: i18n('i18n.MarketActivityManagerView.leavedDeptName'), dataIndex: 'leavedDeptName',sortable:false, flex: 1},
        //到达区域
        { header: i18n('i18n.MarketActivityManagerView.arriveDeptName'), dataIndex: 'arriveDeptName',sortable:false, flex: 1 }
    ],
    getTbar : function(){
            var me = this;
            return [{
            fieldLabel : i18n('i18n.ClientBaseManegerView.LineRequset'),//线路要求
            id : 'lineGridRequireId',
            width:165,
            labelWidth:70,
            xtype : 'combo',
            store:getDataDictionaryByName(DataDictionary,'LINE_TYPE'),
            editable : false,
            queryMode:'local',
            displayField:'codeDesc',
            valueField:'code',
            value : 'LEAVE_ARRIVE',
            forceSelection :true,
            listeners:{
                change:DButil.comboSelsct,
                beforeselect:function(t){
                    oldValueUp=t.getValue();
                },
                select : function(t){
                    var me = this;
                    var oldValue = oldValueUp;
                    var newValue = t.getValue();
                    if(Ext.getCmp("lineGridPanelId").getStore().getCount()===0 && 
                        Ext.getCmp("lineTreeGridPanelId").getStore().getCount()===0){
                        Ext.getCmp('lineTreeForActivityId').getRootNode().cascadeBy(function(node){
                            node.set('checked', false);
                        })
                        Ext.getCmp('lineTreeForActivityId').collapseAll();
                        setActivityLine(newValue);
                        if(treeLineData==null){
                            loadLineTreeDate();
                        }
                    }else{
                        t.setValue(oldValue);
                        //改变线路会清空数据,是否确认？
                        MessageUtil.showQuestionMes(i18n('i18n.MarketActivityManagerView.changeLineSure'), function(e){ 
                            if (e == 'yes') {
                               t.setValue(newValue);
                               Ext.getCmp("lineGridPanelId").getStore().removeAll();
                               Ext.getCmp("lineTreeGridPanelId").getStore().removeAll();
                               Ext.getCmp('lineTreeForActivityId').getRootNode().cascadeBy(function(node){
                                   node.set('checked', false);
                               })
                               Ext.getCmp('lineTreeForActivityId').collapseAll();
                               setActivityLine(newValue);
                               if(treeLineData==null){
                                   loadLineTreeDate();
                               }
                               
                            }
                        });
                    }
                }
            }
        },{
            fieldLabel : i18n('i18n.MarketActivityManagerView.from'),//从
            width:240,
            maxLength : 50,
            labelWidth:20,
            xtype:'combobox',
            id:'downBeginDeptOrCity',
            store:Ext.create('WalkGoodDeptStore',{
                listeners:{
                    beforeload:function(store, operation, eOpts){
                        if(!Ext.getCmp("downBeginDeptOrCity").isValid()){
                            return false;
                        }
                        Ext.apply(operation,{
                            params : {
                                    'deptName':Ext.getCmp("downBeginDeptOrCity").getRawValue()
                                }
                            }
                        );  
                    }
                }
            }),
            triggerAction : 'all',
            displayField:'deptName',
            valueField:'standardCode',
            forceSelection :true,
            hideTrigger:false,
            pageSize: 10,
            minChars:0,
            queryDelay:800,
            listConfig: {
                minWidth :300,
                getInnerTpl: function() {
                     return '{deptName}';
                }
            },
            listeners:{
                expand:function(combo){
                    if(Ext.isEmpty(combo.getValue())){
                        combo.getStore().load();
                    }   
                },
                change:function(combo){
                    if(Ext.isEmpty(combo.getValue())){
                        combo.setValue("");
                    }
                }
            }
        },{
            fieldLabel : i18n('i18n.MarketActivityManagerView.arrive'),//到
            xtype:'combobox',
            id : 'downEndDeptOrCity',
            maxLength : 50,
            width:240,
            labelWidth:20,
            store:Ext.create('WalkGoodDeptStore',{
                listeners:{
                    beforeload:function(store, operation, eOpts){
                        if(!Ext.getCmp("downEndDeptOrCity").isValid()){
                            return false;
                        }
                        Ext.apply(operation,{
                            params : {
                                    'deptName':Ext.getCmp("downEndDeptOrCity").getRawValue()
                                }
                            }
                        );  
                    }
                }
            }),
            triggerAction : 'all',
            displayField:'deptName',
            valueField:'standardCode',
            forceSelection :true,
            hideTrigger:false,
            pageSize: 10,
            minChars:0,
            queryDelay:800,
            listConfig: {
                minWidth :300,
                getInnerTpl: function() {
                     return '{deptName}';
                }
            },
            listeners:{
                expand:function(combo){
                    if(Ext.isEmpty(combo.getValue())){
                        combo.getStore().load();
                    }   
                },
                change:function(combo){
                    if(Ext.isEmpty(combo.getValue())){
                        combo.setValue("");
                    }
                }
            }
        },{
            xtype:'button',
            text:i18n('i18n.MarketActivityManagerView.add'),//添加
            handler:function(){
                var grid = Ext.getCmp('lineGridPanelId');
                var beginDept = Ext.getCmp("downBeginDeptOrCity");
                var endDept = Ext.getCmp("downEndDeptOrCity");
                //出发外场id
                var leaveDeptCode = beginDept.getValue() == null ? '' : beginDept.getValue();
                //出发外场名称
                var leavedDeptName = beginDept.getRawValue();
                //到达外场id
                var arriveDeptCode = endDept.getValue() == null ? '' : endDept.getValue();
                //到达外场名称
                var arriveDeptName = endDept.getRawValue();
                //如果都为空
                if(Ext.isEmpty(leaveDeptCode) && Ext.isEmpty(arriveDeptCode)){
                  MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.bothNull'));//不可都为空
                  return;
                }
                //是否重复添加
                var isSame = false;
                //遍历是否有相同的线路
                grid.store.each(function(obj){
                    if(obj.get("leaveDeptCode") === leaveDeptCode && 
                       obj.get("arriveDeptCode") === arriveDeptCode){
                      //相同的线路给出提示
                       MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.lineExist'));//该线路已添加
                       isSame=true;
                    }
                });
                //如果有相同的线路返回
                if(isSame){
                    return;
                }
                if(grid.getStore().getCount()>=100){
                    MessageUtil.showMessage(i18n('i18n.ClientBaseManeger.lineDeptMoreThan100'));//部门最多添加100条！
                    return false;
                }
                var lineDeptModel = new LineDeptModel();
                lineDeptModel.set('leavedDeptName',leavedDeptName);
                lineDeptModel.set('leaveDeptCode',leaveDeptCode);
                lineDeptModel.set('arriveDeptName',arriveDeptName);
                lineDeptModel.set('arriveDeptCode',arriveDeptCode);
                lineDeptModel.commit();
                grid.store.add(lineDeptModel);
                changeTitle(grid,1,i18n('i18n.MarketActivityManagerView.leavedDeptName'));
                changeTitle(grid,2,i18n('i18n.MarketActivityManagerView.arriveDeptName'));
                beginDept.reset();
                endDept.reset();
            }
        },{
            xtype:'button',
            text: i18n('i18n.MarketActivityManagerView.delete'),//删除
            name : 'delBtn',
            handler:function(){
                var lineGrid = Ext.getCmp('lineGridPanelId');
                var selection = lineGrid.getSelectionModel().getSelection();
                if(selection.length != 1){
                    MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.pleaseSelectOne'));//请选择一条操作记录!
                    return false;
                };
                //是否删除选中的记录？
                MessageUtil.showQuestionMes(i18n('i18n.MarketActivityManagerView.deleteThisSure'), function(e){ 
                    if (e == 'yes') {
                        lineGrid.getStore().remove(selection);
                        MessageUtil.showInfoMes(i18n('i18n.MarketActivityManagerView.deleteSuccess'));  //删除成功
                    }
                });
            }
        }];
    },
    initComponent:function(){
        var me = this;
        me.tbar = me.getTbar();
        this.callParent();
    } 
})

//记录选择优惠方式与线路选择的旧记录
var oldValueUp = null;
//第三个tab 折扣优惠信息
Ext.define('PreferentialInformationPanel',{
    extend : 'PopupGridPanel',
    id : 'preferentialInformationPanelId',
    store : Ext.create('ActivityOptionDataStore',{id : 'preferentialDataStoreId'}),
    selModel : Ext.create('Ext.selection.CheckboxModel',{
        mode:'SINGLE',
        allowDeselect:true,
        listeners : {
            select:function(records){
                Ext.getCmp('preferentialInformationPanelId').down('button[name=delBtn]').enable();
            },
            deselect:function(records){
                Ext.getCmp('preferentialInformationPanelId').down('button[name=delBtn]').disable();
            }
        }
    }),
    width : 390,
    columns: [{ 
        header: i18n('i18n.MarketActivityManagerView.preferProduct'),//优惠产品
        dataIndex: 'type',
        flex :1,
        renderer:function(value){
            return rendererDictionary(value,DataDictionary.DISCOUNT_PRODUCTS);
        }
    },{ header: i18n('i18n.MarketActivityManagerView.ratio')+'(%)', dataIndex: 'value',flex :1}],//折扣比例
    initComponent:function(){
        var me = this;
        me.dockedItems = [{
            //第一行
            xtype: 'toolbar',
            dock: 'top', 
            items: [{
                    xtype : 'combo', 
                    fieldLabel : i18n('i18n.MarketActivityManagerView.preferType'),//优惠方式
                    name : 'preferType',
                    store : getDataDictionaryByName(DataDictionary,'PREFERENTIAL_WAY'),
                    value : 'COUPON',
                    queryMode:'local',
                    displayField:'codeDesc',
                    valueField:'code',
                    editable : false,
                    forceSelection :true,
                    labelWidth : 70,
                    width: 150,
                    listeners:{
                        change:DButil.comboSelsct,
                        beforeselect:function(t){
                            oldValueUp=t.getValue();
                        },
                        select : function(t){
                            var me = this;
                            var oldValue = oldValueUp;
                            var newValue = t.getValue();
                            var createRule = Ext.getCmp('useConditionFormPanelId').getForm().findField('createRule').getValue();
                            var useRule = Ext.getCmp('useConditionFormPanelId').getForm().findField('useRule').getValue();
                            var preferential = Ext.getCmp('preferentialInformationPanelId');
                            var useCondition = Ext.getCmp('useConditionFormPanelId');
                            var workFlow = Ext.getCmp('preferentialInformationPanelId').down('textfield[name=workFlowNum]').getValue();
                            if(Ext.getCmp("preferentialInformationPanelId").getStore().getCount()===0 && 
                                Ext.getCmp("couponGridPanelId").getStore().getCount()===0 && Ext.isEmpty(createRule) && Ext.isEmpty(useRule) && Ext.isEmpty(workFlow)){
                                setActivityPreferential(newValue);
                                Ext.getCmp('useConditionFormPanelId').getForm().clearInvalid();
                                cleanPreferentialValue();
                            }else{
                                t.setValue(oldValue);
                                //改变优惠方式会清空数据,是否确认？
                                MessageUtil.showQuestionMes(i18n('i18n.MarketActivityManagerView.changePreferSure'), function(e){ 
                                    if (e == 'yes') {
                                       t.setValue(newValue);
                                       Ext.getCmp("preferentialInformationPanelId").getStore().removeAll();
                                       Ext.getCmp("couponGridPanelId").getStore().removeAll()
                                       Ext.getCmp('useConditionFormPanelId').getForm().reset();
                                       Ext.getCmp('useConditionFormPanelId').getForm().clearInvalid();
                                       setActivityPreferential(t.getValue());
                                       cleanPreferentialValue();
                                    }
                                });
                            }
                        }
                    }
                },{
                    xtype : 'textfield',
                    minValue : 0,
                    disabled : true,
//                    allowDecimals: false, // 不允许小数点
                    fieldLabel : i18n('i18n.MarketActivityManagerView.workFlowNum'),//折扣工作流号
                    name : 'workFlowNum',
                    maxLength : 30,
                    labelWidth : 90,
                    width: 230,
                    listeners : {
                        blur : function(field){
                            if(Ext.isEmpty(field.getValue())){
                                return;
                            }
                            param = {
                                'workFlowNum' : field.getValue()
                            };
                            var successFn = function(json){
                            };
                            
                            var failureFn= function(json){
                                MessageUtil.showErrorMes(json.message);
                                field.reset();
                                return;
                            };
                            activityRecordData.workFlowNumVerify(param,successFn,failureFn);
                        }
                    }
            }]
        },{
            //第二行
            xtype: 'toolbar',
            dock: 'top',
            name : 'toolbarName',
            items: [{
                xtype : 'combo', 
                fieldLabel : i18n('i18n.MarketActivityManagerView.preferProduct'),//优惠产品
                name : 'type',
                store:getDataDictionaryByName(DataDictionary,'DISCOUNT_PRODUCTS'),
                queryMode:'local',
                displayField:'codeDesc',
                valueField:'code',
                editable: false,
                forceSelection :true,
                labelWidth : 70,
                width: 150,
                listeners:{
                    change:DButil.comboSelsct,
                    select : function(){
                        var preferentialNum = Ext.getCmp('preferentialInformationPanelId').down('textfield[name=value]').getValue();
                        var preferentialName = Ext.getCmp('preferentialInformationPanelId').down('combo[name=type]').getValue();
                        if(Ext.isEmpty(preferentialNum)||Ext.isEmpty(preferentialName)){
                            Ext.getCmp('preferentialInformationPanelId').down('button[name=addBtn]').disable();
                        }else{
                            Ext.getCmp('preferentialInformationPanelId').down('button[name=addBtn]').enable();
                        }
                    }
                }
            },{
                xtype : 'textfield',
                fieldLabel : i18n('i18n.MarketActivityManagerView.ratio'),//折扣比例
                name : 'value',
                labelWidth : 70,
                //隐藏上下箭头
                hideTrigger: true,
                regex : /^((100)|(\d\d?(\.\d\d?)?))$/,
                regexText : i18n('i18n.MarketActivityManagerView.maxValue100'),//只允许100以内两位小数
                width: 120,
                listeners : {
                    change : function(){
                        var preferentialNum = Ext.getCmp('preferentialInformationPanelId').down('textfield[name=value]').getValue();
                        var preferentialName = Ext.getCmp('preferentialInformationPanelId').down('combo[name=type]').getValue();
                        if(Ext.isEmpty(preferentialNum)||Ext.isEmpty(preferentialName)){
                            Ext.getCmp('preferentialInformationPanelId').down('button[name=addBtn]').disable();
                        }else{
                            Ext.getCmp('preferentialInformationPanelId').down('button[name=addBtn]').enable();
                        }
                    }
                }
            },{
                xtype:'label',
                text : '%'
            },{
                xtype : 'button',
                name : 'addBtn',
                disabled : true,
                text : i18n('i18n.MarketActivityManagerView.add'),//添加
                padding : '2 0 2 0',
                width : 45,
                handler : function(){
                    var preferentialGrid = Ext.getCmp('preferentialInformationPanelId');
                    if(preferentialGrid.getStore().count() >= 10){
                        MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.canNotMoreTen'));//该控件最多不能超过10条！
                        return false;
                    };
                    var preferentialNum = preferentialGrid.down('textfield[name=value]');
                    var preferentialName = preferentialGrid.down('combo[name=type]');
                    //如果输入不合法则返回
                    if(!preferentialNum.isValid()||!preferentialName.isValid()){
                        return ;
                    }
                    
                    //如果添加的物品名称相同则不允许添加
                    var type = Ext.String.trim(preferentialName.getValue());
                    if(preferentialGrid.getStore().find('type',type,0,false,true,true) != -1){
                        MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.existToo'));//已存在该记录！
                        return false;
                    }
                    var preferentialModel = new ActivityOptionModel();
                    //设置3个GRID的类型与物品名称与值
                    preferentialModel.set('infoType','DISCOUNT');
                    preferentialModel.set('type',type);
                    preferentialModel.set('value',preferentialNum.getValue());
                    Ext.getCmp('preferentialInformationPanelId').getStore().add(preferentialModel);
                    preferentialModel.commit();
                    preferentialNum.setValue(null);
                    preferentialName.setValue(null);
                }
            },{
                xtype : 'button',
                name : 'delBtn',
                disabled : true,
                text : i18n('i18n.MarketActivityManagerView.delete'),//删除
                padding : '2 0 2 0',
                width : 45,
                handler : function(){
                    var preferentialGrid = Ext.getCmp('preferentialInformationPanelId');
                    var selection = preferentialGrid.getSelectionModel().getSelection();
                    if(selection.length != 1){
                        MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.pleaseSelectOne'));//请选择一条操作记录!
                        return false;
                    };
                    //是否删除选中的记录？
                    MessageUtil.showQuestionMes(i18n('i18n.MarketActivityManagerView.deleteThisSure'), function(e){ 
                        if (e == 'yes') {
                            preferentialGrid.getStore().remove(selection);
                            MessageUtil.showInfoMes(i18n('i18n.MarketActivityManagerView.deleteSuccess'));  //删除成功
                            Ext.getCmp('preferentialInformationPanelId').down('button[name=delBtn]').disable();
                        }
                    });
                }
            }]
        }];
        this.callParent();
        this.down('toolbar[name=toolbarName]').disable();
    } 
});
//优惠类型grid
Ext.define('CouponGridPanel',{
    extend : 'PopupGridPanel',
    id : 'couponGridPanelId',
    store : Ext.create('ActivityOptionDataStore',{id : 'discountDataStoreId'}),
    selModel : Ext.create('Ext.selection.CheckboxModel',{
        mode:'SINGLE',
        allowDeselect:true,
        listeners : {
            select:function(records){
                Ext.getCmp('couponGridPanelId').down('button[name=delBtn]').enable();
            },
            deselect:function(records){
                Ext.getCmp('couponGridPanelId').down('button[name=delBtn]').disable();
            }
        }
    }),
    width : 375,
    columns: [{ 
        header: i18n('i18n.MarketActivityManagerView.discountType'),  //抵扣类型
        dataIndex: 'type',
        flex :1,
        renderer:function(value){
            return rendererDictionary(value,DataDictionary.DISCOUNT_PRODUCTS);
        }
    },
        //优惠券数量
        { header: i18n('i18n.MarketActivityManagerView.couponNum'), dataIndex: 'value',flex :1}
    ],
    getTbar :function(){
        var me = this;
        return [{
             xtype : 'combo', 
             fieldLabel : i18n('i18n.MarketActivityManagerView.discountType'),//抵扣类型
             name : 'type',
             store:getDataDictionaryByName(DataDictionary,'DISCOUNT_PRODUCTS'),
             queryMode:'local',
             displayField:'codeDesc',
             valueField:'code',
             editable : false,
             forceSelection :true,
             labelWidth : 80,
             width: 150,
             listeners:{
                    change:DButil.comboSelsct,
                    select : function(){
                        var discountNum = Ext.getCmp('couponGridPanelId').down('textfield[name=value]').getValue();
                        var discountName = Ext.getCmp('couponGridPanelId').down('combo[name=type]').getValue();
                        if(Ext.isEmpty(discountNum)||Ext.isEmpty(discountName)){
                            Ext.getCmp('couponGridPanelId').down('button[name=addBtn]').disable();
                        }else{
                            Ext.getCmp('couponGridPanelId').down('button[name=addBtn]').enable();
                        }
                    }
             }
        },{
            xtype : 'textfield',
            fieldLabel : i18n('i18n.MarketActivityManagerView.couponNum'),//优惠劵数量
            name : 'value',
            //隐藏上下箭头
            hideTrigger: true,
//            allowDecimals: false, // 不允许小数点
//            minValue: 0, // 不允许负数
            regex : /^\d{0,20}$/,
            regexText : i18n('i18n.MarketActivityManagerView.numberLess20'),//20位以内整数
            labelWidth : 70,
            width: 120,
            listeners:{
                change : function(){
                    var discountNum = Ext.getCmp('couponGridPanelId').down('textfield[name=value]').getValue();
                    var discountName = Ext.getCmp('couponGridPanelId').down('combo[name=type]').getValue();
                    if(Ext.isEmpty(discountNum)||Ext.isEmpty(discountName)){
                        Ext.getCmp('couponGridPanelId').down('button[name=addBtn]').disable();
                    }else{
                        Ext.getCmp('couponGridPanelId').down('button[name=addBtn]').enable();
                    }
                }
            }
        },{
            xtype : 'button',
            name : 'addBtn',
            text : i18n('i18n.MarketActivityManagerView.add'),//添加
            padding : '2 0 2 0',
            width : 45,
            disabled : true,
            handler : function(){
                var couponGrid = Ext.getCmp('couponGridPanelId');
                if(couponGrid.getStore().count() >= 10){
                    MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.canNotMoreTen'));//该控件最多不能超过10条！
                    return false;
                };
                var couponNum = couponGrid.down('textfield[name=value]');
                var couponName = couponGrid.down('combo[name=type]');
                //如果输入不合法则返回
                if(!couponNum.isValid()||!couponName.isValid()){
                    return ;
                }
                
                //如果添加的物品名称相同则不允许添加
                var type = Ext.String.trim(couponName.getValue());
                if(couponGrid.getStore().find('type',type,0,false,true,true) != -1){
                    MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.existToo'));//已存在该记录！
                    return false;
                }
                
                var couponModel = new ActivityOptionModel();
                //设置3个GRID的类型与物品名称与值
                couponModel.set('infoType','COUPON');
                couponModel.set('type',type);
                couponModel.set('value',couponNum.getValue());
                Ext.getCmp('couponGridPanelId').getStore().add(couponModel);
                couponModel.commit();
                couponNum.setValue(null);
                couponName.setValue(null);
            }
        },{
            xtype : 'button',
            name : 'delBtn',
            text : i18n('i18n.MarketActivityManagerView.delete'),//删除
            padding : '2 0 2 0',
            width : 45,
            disabled : true,
            handler : function(){
                var discountGrid = Ext.getCmp('couponGridPanelId');
                var selection = discountGrid.getSelectionModel().getSelection();
                if(selection.length != 1){
                    MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.pleaseSelectOne'));//请选择一条操作记录!
                    return false;
                };
                //是否删除选中的记录？
                MessageUtil.showQuestionMes(i18n('i18n.MarketActivityManagerView.deleteThisSure'), function(e){ 
                    if (e == 'yes') {
                        discountGrid.getStore().remove(selection);
                        MessageUtil.showInfoMes(i18n('i18n.MarketActivityManagerView.deleteSuccess'));  //删除成功
                        Ext.getCmp('couponGridPanelId').down('button[name=delBtn]').disable();
                    }
                });
            }
        }]
    },
    initComponent:function(){
        var me = this;
        me.tbar = me.getTbar();
        this.callParent();
    } 
});
//生成 使用条件 form
Ext.define('UseConditionFormPanel',{
    extend : 'BasicFormPanel',
    id : 'useConditionFormPanelId',
    height : 70,
    layout : {
        type : 'table',
        columns : 3
    },
    getItems : function(){
        var me = this;
        return [{
            xtype : 'textfield',
            fieldLabel : required+i18n('i18n.MarketActivityManagerView.createRule'),//生成条件
            name : 'createRule',
            allowBlank :false,
            blankText : i18n('i18n.MarketActivityManagerView.createRuleCantNull'),//生成条件不能为空！
            height : 20,
            maxLength : 100,
            colspan : 3,
            width : 760
        },{
            xtype : 'textfield',
            fieldLabel : required+i18n('i18n.MarketActivityManagerView.useRule'),//使用条件
            name : 'useRule',
            allowBlank :false,
            blankText : i18n('i18n.MarketActivityManagerView.useRuleCantNull'),//使用条件不能为空！
            maxLength : 100,
            height : 20,
            colspan : 3,
            width : 760
        }];
    },
    initComponent:function(){
        var me = this;
        me.items = me.getItems();
        this.callParent();
    } 
});


var useClient = new Array();
var clientId = null;
//第四个tab 筛选客户群
Ext.define('ClientBasePanel',{
    extend : 'PopupGridPanel',
    id : 'clientBasePanelId',
    selModel : Ext.create('Ext.selection.CheckboxModel',{
        mode:'SINGLE',
        allowDeselect:true,
        listeners : {
            select:function(records){
                if(!(operatorForMarket=='look')){
                    var clientBaseStatus = Ext.getCmp('clientBasePanelId').getSelectionModel().getSelection()[0].get('clientBaseStatus');
                    if( clientBaseStatus != 'ISSUED_IN' && clientBaseStatus !='ISSUED_COMPLETE'){
                       Ext.getCmp('clientBasePanelId').down('button[name=delBtn]').enable();
                    }
                }
                Ext.getCmp('clientBasePanelId').down('button[name=lookBtn]').enable();
            },
            deselect:function(records){
                Ext.getCmp('clientBasePanelId').down('button[name=delBtn]').disable();
                Ext.getCmp('clientBasePanelId').down('button[name=lookBtn]').disable();
            }
        }
    }),
    columns: [
        //客户群名称
        { header: i18n('i18n.MarketActivityManagerView.clientName'),  dataIndex: 'clientBaseName',flex : 1},
        //客户群状态
        { header: i18n('i18n.MarketActivityManagerView.clientBaseStatus'),
          dataIndex: 'clientBaseStatus' ,
          flex : 1,
          renderer:function(value){
            return rendererDictionary(value,DataDictionary.CUST_CONDITION_STATUS);
          }
        },
        //创建人
        { header: i18n('i18n.MarketActivityManagerView.createUserName'), dataIndex: 'createUserName' ,flex : 1},
        //创建时间
        { 
            header: i18n('i18n.MarketActivityManagerView.createDate'), 
            dataIndex: 'createDate' ,
            flex : 1,
            renderer : function(value,metaData,record){
                return DButil.renderDate(value);
            }
        },
        //创建部门
        { header: i18n('i18n.MarketActivityManagerView.createDeptName'), dataIndex: 'deptName',flex : 1 }
    ],
    tbar :[{
        xtype : 'combo',
        fieldLabel : i18n('i18n.MarketActivityManagerView.clientName'),//客户群名称
        name : 'clientName',
        id : 'clientNameCombox',
        maxLength : 40,
        store:Ext.create('ActivityClientBaseStore',{
                listeners:{
                    beforeload:function(store, operation, eOpts){
                        if(!Ext.getCmp("clientNameCombox").isValid()){
                            return false;
                        };
                        if(clientId==Ext.getCmp('clientNameCombox').getValue()&&Ext.getCmp('clientNameCombox').getValue()!=null){
                            return false;
                        };
                        Ext.apply(operation,{
                            params : {
                                    'clientBaseName':Ext.String.trim(Ext.getCmp("clientNameCombox").getRawValue()),
                                    'activityClientIds':useClient
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
            pageSize: 10,
            minChars:0,
            queryDelay:800,
            listConfig: {
                minWidth :300,
                loadMask:false,
                getInnerTpl: function() {
                     return '{clientBaseName}';
                }
            },
            listeners:{
                change:function(combo){
                    if(Ext.isEmpty(combo.getValue())){
                        combo.setValue("");
                        Ext.getCmp('clientBasePanelId').down('button[name = add]').disable()
                    }else{
                        Ext.getCmp('clientBasePanelId').down('button[name = add]').enable();
                    }
                },
                select:function(combo,records){
                    var temp = records[0].data;
                    clientId = records[0].get('clientBaseId');
                    clientBase = Ext.create('ActivityClientBase')
                    clientBase.set(temp);
                    clientBase.commit();
                },
                beforequery: function(qe){
                    useClient.splice(0);
                    Ext.getCmp('clientBasePanelId').getStore().each(function(record){
                        useClient.push(record.data.clientBaseId)
                    });
                    delete qe.combo.lastQuery;
                } 
            }
    },{
        xtype : 'button',
        text : i18n('i18n.MarketActivityManagerView.add'),//添加
        disabled : true,
        name : 'add',
        handler : function(){
            var clientBaseStore = Ext.getCmp('clientBasePanelId').getStore();
            if(clientBaseStore.count() >= 10){
                MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.clientMostTen'));//客户群最多添加十条！
                return false;
            };
            if(clientBaseStore.find('clientBaseId',clientBase.get('clientBaseId'),0,false,true,true) != -1){
                MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.clientExist'));//已存在该客户群！
                return false;
            }
            clientBaseStore.add(clientBase);
            Ext.getCmp('clientNameCombox').reset();
        }
    },{
        xtype : 'button',
        text : i18n('i18n.MarketActivityManagerView.delete'),//删除
        name : 'delBtn',
        disabled : true,
        handler : function(){ 
            var clientBaseGrid = Ext.getCmp('clientBasePanelId');
            var selection = clientBaseGrid.getSelectionModel().getSelection();
            if(selection.length != 1){
                MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.pleaseSelectOne'));//请选择一条操作记录!
                return;
            };
            var clientBaseStatus = selection[0].get('clientBaseStatus');
            if( clientBaseStatus == 'ISSUED_IN' || clientBaseStatus =='ISSUED_COMPLETE'){
                MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.clientCantDelete'));//该客户群已下发，不能删除！
                return;
            }
            MessageUtil.showQuestionMes(i18n('i18n.MarketActivityManagerView.deleteThisClient'), function(e){//是否删除该客户群？
                if (e == 'yes') {
                    clientBaseGrid.getStore().remove(selection);
                    clientBaseGrid.getSelectionModel().deselectAll(false);
                    MessageUtil.showInfoMes(i18n('i18n.MarketActivityManagerView.deleteSuccess'));  //删除成功
                    Ext.getCmp('clientBasePanelId').down('button[name=delBtn]').disable();
                    Ext.getCmp('clientBasePanelId').down('button[name=lookBtn]').disable();
                }
            });
        }
    },{
        xtype : 'button',
        text : i18n('i18n.MarketActivityManagerView.viewDetails'),//查看详情
        name : 'lookBtn',
        disabled : true,
        handler : function(){ 
            //获得客户群查询结果列表
            var grid = Ext.getCmp('clientBasePanelId');
            var selection = grid.getSelectionModel().getSelection();
            if(selection.length != 1){
                MessageUtil.showMessage(i18n('i18n.ClientBaseManeger.message.onlyChoiceOne'));//请选择一条操作记录!
                return;
            }
            var data = selection[0].get('clientBaseId');
            
            //获得客户群查询结果列表
            var successFn = function(json){
            var clientBaseModifyAndDetailWindow = Ext.getCmp('clientBaseModifyAndDetailWindowId');//获取win
            if(Ext.isEmpty(clientBaseModifyAndDetailWindow)){
                clientBaseModifyAndDetailWindow = Ext.create('ClientBaseModifyAndDetailWindow',{id : 'clientBaseModifyAndDetailWindowId'});
            }
            clientBaseModifyAndDetailWindow.setWidth(800);
            clientBaseModifyAndDetailWindow.show();
            clientBaseModifyAndDetailWindow.flag="detail";
            var lineType=json.clientBase.lineType
            choiceLineDept(lineType,'detail');
            clientBaseModifyAndDetailWindow.setTitle(i18n('i18n.ClientBaseManegerView.detailView'));
            //隐藏按钮并跟换显示
            Ext.getCmp('clientBaseModifyAndDetailWindowId').down('button[name=save]').hide();
            Ext.getCmp('clientBaseModifyAndDetailWindowId').down('button[name=reset]').hide();
            Ext.getCmp('clientBaseModifyAndDetailWindowId').down('button[name=cancel]').setText(i18n('i18n.ClientBaseManegerView.cloes'));
           
            var custConditionModel = new ClientBaseInfoModel();
            custConditionModel.set(json.clientBase);
            // 加载二级行业数据
            secondTrade = Ext.getCmp('ClientBaseModifyAndDetailUpPanelId').down('combo[name=secondTrades]');
            secondTrade.reset();
//            secondTrade.getStore().load({  
//            params: {  
//                  'tradesList' : json.clientBase.trades  
//            }  
//            }); 
//            secondTrade.setValue(json.clientBase.secondTrade)
            var successFn = function(json){
                secondTrade.forceSelection=false;//二级行业
		        secondTrade.setValue(json.secondTrade.toString());
                secondTrade.forceSelection=true;//二级行业
            }
            var failureFn = function(json){
                MessageUtil.showErrorMes(json.message);
                return false;
            }
            ClientBaseStore.prototype.searchSecondTradesToString({'conditionId' :json.clientBase.id,'conditionType' : "CUSTOMER_BASE"},successFn,failureFn) 
            Ext.getCmp('ClientBaseModifyAndDetailUpPanelId').getForm().loadRecord(custConditionModel)
             //判断加载
            if('LEAVE_ARRIVE'==json.clientBase.lineType){
            Ext.getCmp('clientBaseLineGridId').getStore().loadData(json.clientBase.lineDept)
            }else{
            Ext.getCmp('clientBaseLineTreeGridId').getStore().loadData(json.clientBase.lineDept)
            }
             
            }
            var failureFn = function(json){
            MessageUtil.showErrorMes(json.message);
            return ;
            }
            ClientBaseStore.prototype.searchClientBaseById({'clientBase.id':data},successFn,failureFn)
                        
                        
        }
    }],
    initComponent:function(){
        this.callParent();
    } 
});

//第五个tab 上传附件
Ext.define('AccessoryPanel',{
    extend : 'PopupGridPanel',
    id : 'accessoryPanelId',
    autoScroll : true,
    height : 350,
    store : Ext.create('FileInfoStore'),
    selModel : Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE'}),
    columns: [{ 
        header: i18n('i18n.MarketActivityManagerView.fileName'),//文件名
        dataIndex: 'fileName',
        flex : 1
    },{ 
        header: i18n('i18n.MarketActivityManagerView.uploadDate'), //上传时间
        dataIndex: 'createDate' ,
        flex : 1,
        renderer : function(value,metaData,record){
            return DButil.renderDate(value);
        }
    },{ 
        header: i18n('i18n.MarketActivityManagerView.uploadUser'), //上传人
        dataIndex: 'createUser' ,
        flex : 1
    },{ 
        header: i18n('i18n.MarketActivityManagerView.operate'), //操作
        dataIndex: 'operator' ,
        flex : 1,
        renderer:function(value,metaData,record ,rowIndex ,colIndex ,store ,view ){
                if(operatorForMarket == 'add'){
                    //删除 下载
                    return '<a onclick="file_delete('+rowIndex+');" href="javascript:void(0);">'+i18n('i18n.MarketActivityManagerView.delete')+'</a>' +'&nbsp&nbsp&nbsp&nbsp'+'<a onclick="file_download('+rowIndex+');" href="javascript:void(0);">'+i18n('i18n.MarketActivityManagerView.download')+'</a>';
                }else{
                    //下载
                    return '<a onclick="file_download('+rowIndex+');" href="javascript:void(0);">'+i18n('i18n.MarketActivityManagerView.download')+'</a>';
                }
        }
    }],
    initComponent:function(){
        this.callParent();
    } 
});

//文件上传panel
Ext.define('FileFormPanel', {
    extend : 'Ext.form.Panel',
    id : 'fileformPanelId',
    flex:1,
    layout:{
        type : 'hbox'
    },
    defaults : {
        margins : '0 5 0 0'
    },
    items:[{
        xtype:'filefield',
        name:'file',
        id : 'id_fileField',
        fieldLabel:i18n('i18n.MarketActivityManagerView.selectFile'),//选择文件
        labelWidth:60,
        allowBlank: false,
        buttonText:i18n('i18n.MarketActivityManagerView.scan'),//浏览..
        flex:1
    },{
        name:'type',
        xtype:'hiddenfield',
        value:'ACTIVITIES'
    },{
        text:i18n('i18n.MarketActivityManagerView.upload'),//上传
        xtype:'button',
        handler : function(){
        var store = Ext.getCmp('accessoryPanelId').getStore();
        var form = Ext.getCmp('fileformPanelId');
        if(!form.getForm().isValid()){MessageUtil.showMessage(i18n('i18n.ContractEditView.m_uploadFile'));return;}
        //计算文件大小
        var field = document.getElementById('id_fileField');  
        //取控件中的input元素  
        var inputs = field.getElementsByTagName('input'); 
         if(Ext.isChrome){
            var flag=Ext.getCmp('id_fileField').getValue().split("\\")[2].length>100
        }else{
            //用于火狐浏览器
            var flag=Ext.getCmp('id_fileField').getValue().length>100
        }
        if(flag){
            MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.fileToolength'));
            return ;
        }
        var fileInput = null;  
        var il = inputs.length;
        var fileSize = null;
        //取出input 类型为file的元素  
        for(var i = 0; i < il; i ++){  
            if(inputs[i].type == 'file'){  
                fileInput = inputs[i];  
                break;  
            }  
        }
        if(fileInput != null){  
             fileSize = countFileSize(fileInput);
        }
        //判断文件数不能大于10个
        if(store.count()>= 10){
            MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.accessoryMostTen'));//附件最多添加10个！
            return;
        }
        //判断单个文件不能大于10M
        if(fileSize > 10*1024*1024){
            MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.accessoryOne'));//单个附件最大允许上传10MB！
            return;
        }
        //判断所有文件不能大于30M
        if((store.sum('fileSize')+fileSize/1024) > 30*1024){
            MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.accessoryAll'));//单个附件最大允许上传10MB！
            return;
        }
        form.submit({
            url: '../common/fileUpload.action',
            waitMsg: i18n('i18n.MarketActivityManagerView.uploading'),//正在上传请稍后
            success: function(form, response) {
                var result = response.result;
                if(result.success){
                    var fileInfo = result.fileInfoList[0];
                    var fileInfoModel = new FileInfoModel();
                    fileInfo.createDate = new Date();
                    fileInfo.createUser = User.empId;
                    fileInfo.createName = User.empName;
                    fileInfoModel.set(fileInfo);
                    fileInfoModel.set('createName',User.empName);
                    fileInfoModel.set('sourceId',marketActivityId);
                    fileInfoModel.set('fileSize',fileSize);
                    fileInfoModel.set('sourceType','ACTIVITIES');
                    var successFn= function(json){
                        fileInfoModel.set('id',json.fileInfo.id);
                        //文件：上传成功
                        MessageUtil.showInfoMes(i18n('i18n.MarketActivityManagerView.file') + fileInfoModel.get('fileName') + i18n('i18n.MarketActivityManagerView.uploadSuccess'));
                        //显示人名
                        fileInfo.createUser = User.empName;
                        fileInfoModel.set('createUser',User.empName);
                        store.add(fileInfoModel);
                        fileInfoModel.commit();
                    };
                    
                    var failureFn= function(json){
                        MessageUtil.showErrorMes(json.message);
                        return;
                    }
                    param = {
                        'fileInfo' : fileInfoModel.data
                    };
                    
                    activityRecordData.saveFileInfo(param,successFn,failureFn);
                }else{
                    MessageUtil.showErrorMes( result.message);
                }
            },
            failure:function(form, response){
                var result = response.result;
                if(!result.success){
                    MessageUtil.showErrorMes(result.message);
                }
            }
        });
    }
    }]
});

function setActivityPreferential(value){
    //如果选择优惠劵 则优惠券亮否则折扣亮
    var preferential = Ext.getCmp('preferentialInformationPanelId');
    var useCondition = Ext.getCmp('useConditionFormPanelId');
    if(value == 'COUPON'){
        Ext.getCmp('couponGridPanelId').child().enable();
        preferential.down('textfield[name=workFlowNum]').disable();
        preferential.down('toolbar[name=toolbarName]').disable();
        useCondition.down('textfield[name=createRule]').enable();
        useCondition.down('textfield[name=useRule]').enable(); 
    }else{
        Ext.getCmp('couponGridPanelId').child().disable();
        if(Ext.getCmp('activityFormPanelId').getForm().findField('activityType').getValue() == 'REGION'){
            preferential.down('textfield[name=workFlowNum]').enable();
        }
        preferential.down('toolbar[name=toolbarName]').enable();
        preferential.down('toolbar[name=toolbarName]').down('combo[name=type]').enable();
        preferential.down('toolbar[name=toolbarName]').down('textfield[name=value]').enable();
        useCondition.down('textfield[name=createRule]').disable();
        useCondition.down('textfield[name=useRule]').disable();
    }
}

//设置活动线路时的显示的设置
function setActivityLine(value){
    if(value != 'LEAVE_ARRIVE'){
        Ext.getCmp('lineTreeRequireId').setValue(value);
        Ext.getCmp('lineGridRequireId').setValue(value);
        changeTitle(Ext.getCmp('lineTreeGridPanelId'),1,i18n('i18n.MarketActivityManagerView.leavedDeptName'));
        changeTitle(Ext.getCmp('lineTreeGridPanelId'),2,i18n('i18n.MarketActivityManagerView.arriveDeptName'));
        if(value=='LEAVE'){
            Ext.getCmp('lineTreeGridPanelId').columns[1].show();
            Ext.getCmp('lineTreeGridPanelId').columns[2].hide();
        }else{
            Ext.getCmp('lineTreeGridPanelId').columns[1].hide();
            Ext.getCmp('lineTreeGridPanelId').columns[2].show();
        }
        Ext.getCmp('lineGridPanelId').hide();
        Ext.getCmp('lineTreeForActivityId').show();
        Ext.getCmp('lineTreeGridPanelId').show();
    }
    if(value == 'LEAVE_ARRIVE'){
        Ext.getCmp('lineTreeForActivityId').hide();
        Ext.getCmp('lineTreeGridPanelId').hide();
        Ext.getCmp('lineGridPanelId').show();
        Ext.getCmp('lineGridRequireId').setValue(value);
        Ext.getCmp('lineTreeRequireId').setValue(value);
        changeTitle(Ext.getCmp('lineGridPanelId'),1,i18n('i18n.MarketActivityManagerView.leavedDeptName'));
        changeTitle(Ext.getCmp('lineGridPanelId'),2,i18n('i18n.MarketActivityManagerView.arriveDeptName'));
    }
}


/**
 * 设置计数式标题
 * @param {} grid
 * @param {} index
 * @param {} title
 * @author 李春雨
 */
function changeTitle(grid,index,title){
    grid.columns[index].setText(title+'('+grid.getStore().getCount()+')');
}

//优惠劵与折扣添加框中数据清空
function cleanPreferentialValue(){
    Ext.getCmp('preferentialInformationPanelId').down('textfield[name=value]').reset();
    Ext.getCmp('preferentialInformationPanelId').down('combo[name=type]').reset();
    Ext.getCmp('preferentialInformationPanelId').down('textfield[name=workFlowNum]').reset();
    Ext.getCmp('couponGridPanelId').down('textfield[name=value]').reset();
    Ext.getCmp('couponGridPanelId').down('combo[name=type]').reset();
}
 
/**
 * 切换线路类型并禁用控件
 * @param lineType
 * @param flag
 */
function choiceLineDept(lineType,flag){
    if(lineType=='LEAVE_ARRIVE'||null==lineType){
        Ext.getCmp('lineTreeForClientBaseId').hide();
        Ext.getCmp('clientBaseLineTreeGridId').hide();
        Ext.getCmp('clientBaseLineGridId').show();
        Ext.getCmp('lineGridSelectId').setValue(lineType);
        if(flag=='detail'){
            //禁用window组件
            var DetailUpPanel=Ext.getCmp('ClientBaseModifyAndDetailUpPanelId')
            eachReadOnly(DetailUpPanel,13,true)
            Ext.getCmp('lineGridSelectId').up().disable();
        }
    }else{
        Ext.getCmp('clientBaseLineGridId').hide();
        Ext.getCmp('lineTreeForClientBaseId').show();
        Ext.getCmp('clientBaseLineTreeGridId').show();
        Ext.getCmp('lineTreeSelectId').setValue(lineType);
        if(lineType=='ARRIVE'){
                Ext.getCmp('clientBaseLineTreeGridId').columns[0].hide();
                Ext.getCmp('clientBaseLineTreeGridId').columns[1].show();
            }else{
                Ext.getCmp('clientBaseLineTreeGridId').columns[1].hide();
                Ext.getCmp('clientBaseLineTreeGridId').columns[0].show()
            }
        if(flag=='detail'){
            //禁用window组件
            var DetailUpPanel=Ext.getCmp('ClientBaseModifyAndDetailUpPanelId')
            eachReadOnly(DetailUpPanel,13,true)
            Ext.getCmp('lineTreeForClientBaseId').setVisible(false)
        }
    }
}

//设置form下的所有组件为ReadOnly
function eachReadOnly(form,length,isdisp){
    var i = 0;
    if(isdisp){
        for(i=0;i<length;i++){
            form.getForm().getFields().items[i].setReadOnly(true);
             form.getForm().getFields().items[i].addCls('readonly');
        }
    }else{
        for(i=0;i<length;i++){
            form.getForm().getFields().items[i].setReadOnly(false);
             form.getForm().getFields().items[i].removeCls('readonly');
        }
    }
}

/**
 * 重置控件
 * lvchongxin
 * 
 */
function resetClientBaseWindow(){
    Ext.getCmp('clientBaseLineGridId').store.removeAll();
}