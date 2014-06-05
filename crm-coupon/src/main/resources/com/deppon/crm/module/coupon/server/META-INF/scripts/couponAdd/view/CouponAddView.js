/**
 * <p>
 * 营销计划新增
 * </p>
 * @author  张登
 * @date    2012-11-15
 */
Ext.onReady(function(){
    var keys=[
        // 行业
        'TRADE',
        //会员等级
        'MEMBER_GRADE',
        //费用类型
        'COUPON_COST_TYPE',
        //金额要求
        'COUPON_AMOUNT_TYPE',
        //零担的产品类型
        'FOSS_PRODUCT_TYPE',
        //快递的产品类型
        'EXPRESS_PRODUCT_TYPE',
        //增值费
        'COUPON_APPRECIATION_TYPE',
        //抵扣类型 = 优惠产品
        'DISCOUNT_PRODUCTS'
    ];
    //自动发券combox记录上次的值 
    var oldValueUp=null;
    //使用规则combox记录上次的值 
    var oldValueDown=null;
    //初始化业务参数
    initDataDictionary(keys);
    //初始化订单来源
    initOrderSources();
    //产品类型的store，快递权限用户和零担权限用户显示的产品类型列表不同
    var productTypeStore = null;
    //市场推广活动id
    var markActivityId = null;
    //如果具有查看零担产品类型的权限
    if(isPermission('/coupon/selectLogisticsProductType.action')){
        productTypeStore = getDataDictionaryByName(DataDictionary,'FOSS_PRODUCT_TYPE');
    }//如果具有查看快递产品类型的权限
    else if(isPermission('/coupon/selectExpressProductType.action')){
        productTypeStore = getDataDictionaryByName(DataDictionary,'EXPRESS_PRODUCT_TYPE');
    }
    //数据字段转化为数组
    var discountProducts = new Array();
    getDataDictionaryByName(DataDictionary,'DISCOUNT_PRODUCTS').each(function(record){
        discountProducts.push(record.data);
    });
    Ext.create('Ext.container.Viewport', {
//        layout:'fit',
//        autoScroll : true,
        items:[{//最大层panel
            xtype:'basicformpanel',
//            autoScroll : true,
            layout: 'border',
            height : 740,
            items: [{//最上面的营销计划基本信息
                region: 'north',
                id:'marketplanFrom',
                xtype:'form',
                border: false,
//              height:28,//设置第一行form高度
//                layout : {
//                    type : 'table',
//                    columns : 4
//                },
                //修改布局方式为横向布局
                layout: 'column',
                defaultType : 'textfield',
                defaults : {
                    labelWidth : 80,
                    labelAlign: 'right',
                    width : 220
                },
                items:[{
                    fieldLabel : i18n('i18n.coupon.couponType'),
                    columnWidth:.2,
                    xtype:'combobox',
                    id:'couponType',
                    name : 'couponType',
                    labelWidth : 75,
                    value:'AUTOCOUPON',
                    editable:false,
                    queryMode: 'local',
                    width:180,
                    displayField: 'codeDesc',
                    valueField: 'code',
                    store:Ext.create('CouponTypeStore'),
                    listeners:{
                        change:function(t,newValue,oldValue,e){//切换标签页时的操作
                            if(t.getValue()===COUPON_SENDHAND){//手动
                                //设置优惠券的数量
                                //如果活动下拉框与类型下拉框都有值  则设置优惠券的数量
                                if(!Ext.isEmpty(Ext.getCmp('activityNameId').getValue())&&!Ext.isEmpty(t.ownerCt.getForm().findField('couponInfo').getValue())){
                                    var couponInfo = t.ownerCt.getForm().findField('couponInfo');
                                    var ruleCouponHandForm=Ext.getCmp("ruleCouponHandTab").getForm();//手动规则
                                    couponQuantity=ruleCouponHandForm.findField('couponQuantity');// 优惠券数量
                                    var couponNum = couponInfo.findRecordByValue(couponInfo.getValue()).get('value');
                                    couponQuantity.setValue(couponNum);
                                }
                                if(bIsChangeTab()){//调用判断自动页面数据是否为空
                                    Ext.Msg.show({
                                        title:'CRM提醒您:',
                                        width:110+14*15,
                                        msg:'<div id="message">'+i18n('i18n.coupon.changeTabResetData')+'</div>',
                                        buttons: Ext.Msg.YESNO,
                                        icon:Ext.MessageBox.QUESTION,
                                        callback:function(e){
                                            if(e=='yes'){
                                                var ruleCouponAutoForm=Ext.getCmp("ruleCouponAutoForm").getForm();//手动规则
                                                ruleCouponAutoForm.reset();
                                                //清空返券金额
                                                if(Ext.getCmp("autoCouponCostGrid").store.getCount()>0){
                                                    Ext.getCmp("autoCouponCostGrid").store.removeAll();
                                                }       
                                                //清空自动返券的信息
                                                Ext.getCmp("costType").setValue("");
                                                Ext.getCmp("upruleType").setValue("");
                                                Ext.getCmp("upBeginDeptOrCityCombox").setValue("");
                                                Ext.getCmp("upEndDeptOrCityCombox").setValue("");
                                                Ext.getCmp("downEndDeptOrCityCombox").setValue("");
                                                Ext.getCmp("makeGoodsLineDept").setValue("");
                                                //清空发到货
                                                if(Ext.getCmp("makeCrossGoodsLineGrid").store.getCount()>0){
                                                    Ext.getCmp("makeCrossGoodsLineGrid").store.removeAll();
                                                }   
                                                //清空走货线路
                                                if(Ext.getCmp("makeWalkGoodsLineGrid").store.getCount()>0){
                                                    Ext.getCmp("makeWalkGoodsLineGrid").store.removeAll();
                                                }
                                                Ext.getCmp("couponTabpanel").getComponent('ruleCouponHandTab').enable();
                                                Ext.getCmp("couponTabpanel").setActiveTab('ruleCouponHandTab');
                                                Ext.getCmp("couponTabpanel").getComponent('ruleCouponAutoTab').disable();
                                            }else{
                                                t.setValue(oldValue);
                                            }
                                        }
                                    });
                                }else{
                                    Ext.getCmp("couponTabpanel").getComponent('ruleCouponHandTab').enable();
                                    Ext.getCmp("couponTabpanel").setActiveTab('ruleCouponHandTab');
                                    Ext.getCmp("couponTabpanel").getComponent('ruleCouponAutoTab').disable();
                                }
                            }else{//判断手动表单是否已经填写，如果表单已经填写，切换时候会提示表单已经填写是否继续
                                var ruleCouponHandForm=Ext.getCmp("ruleCouponHandTab").getForm();//手动规则
                                couponValue=ruleCouponHandForm.findField('couponValue').getValue();// 优惠券金额
                                couponQuantity=ruleCouponHandForm.findField('couponQuantity').getValue();// 优惠券数量
                                if(!Ext.isEmpty(couponValue) || !Ext.isEmpty(couponQuantity)){
                                    Ext.Msg.show({
                                        title:'CRM提醒您:',
                                        width:110+14*15,
                                        msg:'<div id="message">'+i18n('i18n.coupon.changeTabResetData')+'</div>',
                                        buttons: Ext.Msg.YESNO,
                                        icon:Ext.MessageBox.QUESTION,
                                        callback:function(e){
                                            if(e=='yes'){
                                                //清空手动返券信息
                                                ruleCouponHandForm.reset();
                                                //激活自动
                                                Ext.getCmp("couponTabpanel").getComponent('ruleCouponAutoTab').enable();
                                                Ext.getCmp("couponTabpanel").setActiveTab('ruleCouponAutoTab');
                                                Ext.getCmp("couponTabpanel").getComponent('ruleCouponHandTab').disable();
                                            }else{
                                                t.setValue(oldValue);
                                            }
                                        }
                                    });
                                }else{//自动可以、激活自动、禁用手动
                                    Ext.getCmp("couponTabpanel").getComponent('ruleCouponAutoTab').enable();
                                    Ext.getCmp("couponTabpanel").setActiveTab('ruleCouponAutoTab');
                                    Ext.getCmp("couponTabpanel").getComponent('ruleCouponHandTab').disable();
                                }
                            }
                        }
                    }
                },{
                    fieldLabel : i18n('i18n.coupon.planNumber'),
                    labelWidth : 80,
                    columnWidth:.3,
                    width:235,
                    name : 'planNumber',
                    cls:'readonly',
                    readOnly:true
                },{
                    fieldLabel : i18n('i18n.coupon.planName'),
                    name : 'planName',
                    columnWidth:.5,
                    labelWidth : 100,
                    width:390,
                    colspan:2,
                    allowBlank:false,
                    maxLength : 80,
                    maxLengthText : i18n('i18n.coupon.maxLength')+80
                },{
                    xtype:'combobox',
                    columnWidth:.5,
                    fieldLabel : i18n('i18n.coupon.couponInfo'),//抵扣类型
                    name : 'couponInfo',
                    allowBlank:false,
                    store: Ext.create('ActivityOptionStore'),
                    queryMode:'local',
                    displayField:'codeDesc',
                    valueField:'code',
                    forceSelection :true,
                    editable : false,
                    width:390,
                    labelWidth : 75,
                    listConfig: {
                        loadMask:false
                    },
                    listeners:{
                        select : function(combo,record){
                            //如果为手动券则把选择的抵扣类型的数量填入到发放数量中
                            if(Ext.getCmp('couponType').getValue()===COUPON_SENDHAND){
                                var ruleCouponHandForm=Ext.getCmp("ruleCouponHandTab").getForm();//手动规则
                                couponQuantity=ruleCouponHandForm.findField('couponQuantity');// 优惠券数量
                                couponQuantity.setValue(record[0].get('value'));
                            }
                        },
                        expand : function(combo){
                            if(combo.getStore().getCount()==0 && Ext.isEmpty(Ext.getCmp('activityNameId').getValue())){
                                combo.getStore().loadData(discountProducts);
                            }
                        }
                    }
                },{
                    xtype:'combobox',
                    columnWidth:.4999,
                    fieldLabel : i18n('i18n.coupon.marketActivity'),//'市场推广活动',
                    name : 'activityName',
                    id : 'activityNameId',
                    labelWidth : 100,
                    maxLength : 40,
                    width:390,
                    colspan:2,
                    forceSelection :true,
                    triggerAction: 'all',
                    displayField:'activityName',
                    minChars:0,
                    queryDelay:800,
                    valueField:'id',
                    listConfig: {
                        loadMask:false
                    },
                    store:Ext.create('ActivityMainStore',{
                        listeners:{
                            beforeload:function(store, operation, eOpts){
                                if(!Ext.getCmp("activityNameId").isValid()){ 
		                            return false;
		                        };
                                if(markActivityId==Ext.getCmp('activityNameId').getValue()&&Ext.getCmp('activityNameId').getValue()!=null){
                                    return false;
                                };
                                Ext.apply(operation,{
                                    params : {
                                        'marketActivityName':Ext.String.trim(Ext.getCmp("activityNameId").getRawValue())
                                    }
                                });
                           }
                        }
                    }),
                    listeners:{
                        change : function(combo){
                            var couponInfo = combo.ownerCt.getForm().findField('couponInfo');
                            if(Ext.isEmpty(combo.getValue())){
                                combo.setValue("");
                                combo.ownerCt.getForm().findField('useRule').reset();
                                combo.ownerCt.getForm().findField('createRule').reset();
                                couponInfo.getStore().loadData(discountProducts);
                            }
                            couponInfo.reset();
                        },
                        select : function(combo,records){
                            if(Ext.isEmpty(combo.getValue())){
                                return false;
                            }
                            markActivityId = records[0].get('id');
                            var activityForm = combo.ownerCt.getForm();
                            var couponInfo = activityForm.findField('couponInfo');
                            activityForm.findField('createRule').setValue(records[0].get('createRule'));
                            activityForm.findField('useRule').setValue(records[0].get('useRule'));
                            var dictionaryStore = getDataDictionaryByName(DataDictionary,'DISCOUNT_PRODUCTS');
                            couponInfo.getStore().load({
                                params: {
                                    'id' : combo.getValue()
                                }  
                            });
                        },
                        beforequery: function(qe){
                            delete qe.combo.lastQuery;
                        }
                  }
                },{
                    xtype : 'textarea',
                    columnWidth:.5,
                    fieldLabel : i18n('i18n.coupon.createRule'),//生成规则
                    name : 'createRule',
                    readOnly : true,
                    labelWidth : 75,
                    width:390,
                    colspan:2,
                    height : 20
                },{
                    xtype : 'textarea',
                    columnWidth:.5,
                    fieldLabel : i18n('i18n.coupon.useRule'),//'使用规则',
                    readOnly : true,
                    name : 'useRule',
                    labelWidth : 75,
                    width:390,
                    colspan:2,
                    height : 20
                }]
            },{//中间手/自动返券tab
                region: 'center',
//              margins: '2 5',
                xtype: 'normaltabpanel',
                height : 500,
                id:'couponTabpanel',
                activeTab: 1,      
                items:[{//第一个标签页-手动返券
                    title: i18n('i18n.coupon.ruleCouponHand'),
                    id:'ruleCouponHandTab',
                    disabled:true,
                    xtype:'notitleformpanel',
                    defaultType : 'textfield',
                    defaults : {
                        labelWidth : 80,
                        labelAlign: 'right',
                        width : 220
                    },
                    layout : {
                        type : 'table',
                        columns : 2
                    },
                    items:[{
                        fieldLabel : i18n('i18n.coupon.useCouponValue'),
                        name : 'couponValue',
                        allowBlank:false,
                        regex: /^[1-9][0-9]*$/,
                        regexText: i18n('i18n.coupon.onlyNumber'),
                        maxLength : 10,
                        maxLengthText : i18n('i18n.coupon.maxLength')+10
                    },{
                        fieldLabel :i18n('i18n.coupon.couponQuantity'),
                        name : 'couponQuantity',
                        regex: /^[0-9]*[1-9][0-9]*$/,
                        xtype:'numberfield',
                        regexText: i18n('i18n.coupon.onlyNumber'),
                        allowBlank:false,
                        minValue:1,
                        maxValue:100000,
                        maxLength : 6,
                        maxLengthText : i18n('i18n.coupon.maxLength')+6
                    }]
                },{//第二个标签页-自动返券
                    title: i18n('i18n.coupon.ruleCouponAuto'),
                    layout:'border',
                    border:false,
                    id:'ruleCouponAutoTab',
                    items:[{//自动返券的容器（左边formPanel和返券金额）
                        xtype:'tabcontentpanel',
                        flex:0.54,
                        region:'west',
                        layout:'border',
                        border:false,
                        items:[{//自动返券左边的formPanel
                            region:'center',
                            xtype:'form',
                            id:'ruleCouponAutoForm',
                            defaultType : 'textfield',
                            defaults : {
                                labelWidth : 60,
                                labelAlign: 'right',
                                width : 180
                            },
                            layout : {
                                type : 'table',
                                columns : 2
                            },
                            items:[{
                                xtype : 'datetimefield',
                                name: 'autoBeginTime',
                                value:new Date((new Date).getFullYear(),(new Date).getMonth(),(new Date).getDate(),0,0,0),
                                defaultTime:[0,0,0],
                                width:210,
                                fieldLabel : i18n('i18n.coupon.autoBeginTimeByPlanManager'),
                                editable:false,
                                minValue:new Date(),
                                format: 'Y-m-d H:i'
//                              defaultTime:[0,0,0],
                            },{
                                xtype : 'datetimefield',
                                name: 'autoEndTime',
                                //value:new Date((new Date).getFullYear(),(new Date).getMonth(),(new Date).getDate(),23,59,59),
//                              defaultTime:[23,59,59],,
                                allowBlank: false,
                                minValue:new Date(),
                                width:195,
                                fieldLabel : i18n('i18n.coupon.go'),
                                editable:false,
                                format: 'Y-m-d H:i'
                            },{
                                fieldLabel : i18n('i18n.coupon.makeProductType'),
                                xtype:'combobox',
                                name : 'makeProductType',
                                colspan:2,
                                width:405,
                                editable:false,
                                queryMode: 'local',
                                allowBlank:false,
                                blankText:i18n('i18n.coupon.productTypeCanNotNull'),
                                multiSelect:true,
                                displayField:'codeDesc',
                                valueField:'code',  
                                store:productTypeStore
                            },{
                                fieldLabel : i18n('i18n.coupon.makeOrderSourceType'),
                                xtype:'combobox',
                                name : 'makeOrderSourceType',
                                colspan:2,
                                width:405,
                                editable:false,
                                queryMode: 'local',
                                multiSelect:true,
                                displayField:'codeDesc',
                                valueField:'code',  
                                store:getOrderResourcesStore()
                            },{
                                fieldLabel : i18n('i18n.coupon.makeCustomerLevelType'),
                                xtype:'combobox',
                                name : 'makeCustomerLevelType',
                                colspan:2,
                                width:405,
                                editable:false,
                                queryMode: 'local',
                                multiSelect:true,
                                displayField:'codeDesc',
                                valueField:'code',  
                                store:getDataDictionaryByName(DataDictionary,'MEMBER_GRADE')
                            },{
                                fieldLabel : i18n('i18n.coupon.makeCustomerTradeType'),
                                xtype:'combobox',
                                name : 'makeCustomerTradeType',
                                colspan:2,
                                width:405,
                                editable:false,
                                queryMode: 'local',
                                multiSelect:true,
                                displayField:'codeDesc',
                                valueField:'code',  
                                store:getDataDictionaryByName(DataDictionary,'TRADE')
                            }]
                        },{//自动返券左边下的返券金额Grid
                            xtype:'popupgridpanel',
                            id:'autoCouponCostGrid',//返券金额Grid
                            region:'south',
                            height:115,
                            title:i18n('i18n.coupon.autoCouponCost'),
                            selModel:Ext.create('Ext.selection.CheckboxModel'),
                            store:Ext.create('AutoCouponCostStore'),
                            plugins: [
                                Ext.create('Ext.grid.plugin.CellEditing', {  
                                    clicksToEdit : 1,
                                    listeners:{//编辑后提交
                                        edit:function(editor, e){
                                            e.record.commit();
                                        }
                                    }
                                })
                            ],
                            columns: [
                               { xtype:'rownumberer',width:40,header:i18n('i18n.coupon.rownumberer')},      
                               { header: '<center>'+i18n('i18n.coupon.costDown')+'</center>', dataIndex: 'costDown',sortable:false,flex: 0.5 ,editor: {
                                   xtype: 'numberfield',
                                   minValue:1,
                                   regex: /^[0-9]*[1-9][0-9]*$/,
                                   regexText: i18n('i18n.coupon.onlyNumber'),
                                   maxLength : 10,
                                   maxLengthText : i18n('i18n.coupon.maxLength')+10,
                                   allowBlank: false
                               }},
                               { header: '<center>'+i18n('i18n.coupon.autoCouponCost')+'</center>', dataIndex: 'coupoCost',sortable:false, flex: 0.5 ,editor: {
                                   xtype: 'numberfield',
                                   minValue:1,
                                   regex: /^[0-9]*[1-9][0-9]*$/,
                                   regexText: i18n('i18n.coupon.onlyNumber'),
                                   maxLength : 10,
                                   maxLengthText : i18n('i18n.coupon.maxLength')+10,
                                   allowBlank: false
                               }}
                            ],
                            tbar:[{
                                fieldLabel : i18n('i18n.coupon.costType'),
                                xtype:'combobox',
                                id:'costType',
                                name : 'costType',
                                colspan:2,
                                width:240,
                                labelWidth:65,
                                editable:false,
                                queryMode: 'local',
                                displayField:'codeDesc',
                                valueField:'code',  
                                store:getDataDictionaryByName(DataDictionary,'COUPON_COST_TYPE')
                            },'->',{
                                xtype:'button',
                                text:i18n('i18n.coupon.insert'),
                                handler:function(){
                                    if(!Ext.isEmpty(Ext.getCmp("costType").getValue())){
                                        var grid=Ext.getCmp('autoCouponCostGrid');
                                        if(grid.store.getCount()>9){
                                            MessageUtil.showMessage(i18n('i18n.coupon.maxTenItem'));
                                            return false;
                                        }
                                        grid.store.insert(0,new AutoCouponCostModel());
                                    }else{
                                        MessageUtil.showMessage("请先选择费用类型！");
                                    }
                                }
                            },{
                                xtype:'button',
                                text:i18n('i18n.coupon.delete'),
                                handler:function(){
                                    var grid=Ext.getCmp('autoCouponCostGrid');
                                    deleteRecordsByGrid(grid);
                                }
                            }]
                        }]
                    },{//自动返券右边的Grid，分上下二部分
                        region:'center',
                        margins: '5 2',
                        flex:0.46,
                        xtype:'container',
                        layout: 'border',
                        items:[{//上部分的线路选择要求combox
                            region:'north',
                            height:23,
                            xtype:'container',
                            items:[{
                                fieldLabel :i18n('i18n.coupon.ruleType'),
                                xtype:'combobox',
                                name : 'ruleType',
                                id:'upruleType',
                                colspan:2,
                                width:220,
                                labelWidth:80,
                                editable:false,
                                queryMode: 'local',
                                displayField: 'codeDesc',
                                valueField: 'code',
                                store:Ext.create('RuleTypeStore'),
                                listeners:{
                                    beforeselect:function(t){
                                        oldValueUp=t.getValue();
                                    },
                                    select:function(t){//切换标签页时的操作
                                        var oldValue = oldValueUp;
                                        var newValue = t.getValue();
                                        if(Ext.getCmp("makeWalkGoodsLineGrid").store.getCount()===0 && 
                                                Ext.getCmp("makeCrossGoodsLineGrid").store.getCount()===0){
                                            setActiveGoodLine("up",t.getValue());
                                        }else if(t.getValue()===GOOD_LINE_LEAVE || t.getValue()===GOOD_LINE_ARRIVED){// 发到货
                                            if(Ext.getCmp("makeWalkGoodsLineGrid").store.getCount()>0){
                                                t.setValue(oldValue);
                                                Ext.Msg.show({
                                                    title:'CRM提醒您:',
                                                    width:110+16*15,
                                                    msg:'<div id="message">'+"切换选项后会清空列表数据，是否继续？"+'</div>',
                                                    buttons: Ext.Msg.YESNO,
                                                    icon:Ext.MessageBox.QUESTION,
                                                    callback:function(e){
                                                        if(e=='yes'){
                                                            t.setValue(newValue);
                                                            Ext.getCmp("makeWalkGoodsLineGrid").store.removeAll();
                                                            setActiveGoodLine("up",t.getValue());
                                                        }
                                                    }
                                                });
                                            }else if(Ext.getCmp("makeCrossGoodsLineGrid").store.getCount()===0){
                                                setActiveGoodLine("up",t.getValue());
                                            }else{
                                                //是否显示复制button
                                                isShowCopyButton();
                                            }
                                        }else if(t.getValue()===GOOD_LINE_AREA){//走货
                                            if(Ext.getCmp("makeCrossGoodsLineGrid").store.getCount()>0){
                                                t.setValue(oldValue);
                                                Ext.Msg.show({
                                                    title:'CRM提醒您:',
                                                    width:110+16*15,
                                                    msg:'<div id="message">'+"切换选项后会清空列表数据，是否继续？"+'</div>',
                                                    buttons: Ext.Msg.YESNO,
                                                    icon:Ext.MessageBox.QUESTION,
                                                    callback:function(e){
                                                        if(e=='yes'){
                                                            t.setValue(newValue);
                                                            Ext.getCmp("makeCrossGoodsLineGrid").store.removeAll();
                                                            setActiveGoodLine("up",t.getValue());
                                                        }
                                                    }
                                                });
                                            }else{
                                                setActiveGoodLine("up",t.getValue());
                                            }
                                        }
                                    }
                                }
                            }]
                        },{//下面的Grid，根据选择不同显示对应的表格
                            region:'center',
                            layout: 'card',
                            id:'upCradPanel',
                            xtype:'container',
                            margins: '5 5 0 0',
                            items: [{//走货时的Grid
                                xtype:'popupgridpanel',
                                id:'makeWalkGoodsLineGrid',
                                selModel:Ext.create('Ext.selection.CheckboxModel'),
                                store:Ext.create('MakeWalkGoodsLineStore'),
                                columns: [
                                   { xtype:'rownumberer',width:40,header:i18n('i18n.coupon.rownumberer')},    
                                   { header: '<center>'+i18n('i18n.coupon.beginDeptOrCityName')+'</center>', dataIndex: 'beginDeptOrCityName',sortable:false, flex: 0.5 },
                                   { header: '<center>'+i18n('i18n.coupon.endDeptOrCityName')+'</center>', dataIndex: 'endDeptOrCityName',sortable:false, flex: 0.5 }
                                ],
                                tbar:[{
                                    fieldLabel : i18n('i18n.coupon.BeginDeptOrCity'),
                                    hideLabel:true,
                                    xtype:'combobox',
                                    id:'upBeginDeptOrCityCombox',
                                    name : 'upBeginDeptOrCityCombox',
                                    width:105,
                                    store:Ext.create('WalkGoodDeptStore',{
                                        listeners:{
                                            beforeload:function(store, operation, eOpts){
                                                Ext.apply(operation,{
                                                    params : {
                                                            'deptName':Ext.getCmp("upBeginDeptOrCityCombox").getRawValue()
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
                                    emptyText:i18n('i18n.coupon.inputDeptName'),
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
                                    fieldLabel : i18n('i18n.coupon.go'),
                                    xtype:'combobox',
                                    id:'upEndDeptOrCityCombox',
                                    name : 'upEndDeptOrCityCombox',
                                    width:125,
                                    labelWidth:20,
                                    store:Ext.create('WalkGoodDeptStore',{
                                        listeners:{
                                            beforeload:function(store, operation, eOpts){
                                                Ext.apply(operation,{
                                                    params : {
                                                            'deptName':Ext.getCmp("upEndDeptOrCityCombox").getRawValue()
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
                                    emptyText:i18n('i18n.coupon.inputDeptName'),
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
                                },'->',{
                                    xtype:'button',
                                    text:i18n('i18n.coupon.insert'),
                                    handler:function(){
                                        var grid=Ext.getCmp('makeWalkGoodsLineGrid');
                                        addWalkGoodsLine("up",grid);//调用线路区域要求方法,up指的是自动返券制定规则部分
                                    }
                                },{
                                    xtype:'button',
                                    text:i18n('i18n.coupon.delete'),
                                    handler:function(){
                                        var grid=Ext.getCmp('makeWalkGoodsLineGrid');
                                        deleteRecordsByGrid(grid);
                                    }
                                }]
                            },{//发到货Grid
                                xtype:'popupgridpanel',
                                id:'makeCrossGoodsLineGrid',
                                selModel:Ext.create('Ext.selection.CheckboxModel'),
                                store:Ext.create('MakeCrossGoodsLineStore'),
                                columns: [
                                   { xtype:'rownumberer',width:40,header:i18n('i18n.coupon.rownumberer')},       
                                   { header: '<center>'+i18n('i18n.coupon.beginDeptName')+'</center>', dataIndex: 'beginDeptOrCityName',sortable:false, flex: 1 }
                                ],
                                tbar:[{
                                    fieldLabel : i18n('i18n.coupon.deptName'),
                                    xtype:'combobox',
                                    name : 'makeGoodsLineDept',
                                    id:'makeGoodsLineDept',
                                    width:230,
                                    labelWidth:40,
                                    store:Ext.create('StartEndGoodDeptStore',{
                                        listeners:{
                                            beforeload:function(store, operation, eOpts){
                                                Ext.apply(operation,{
                                                    params : {
                                                            'deptName':Ext.getCmp("makeGoodsLineDept").getRawValue()
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
                                    emptyText:i18n('i18n.coupon.inputDeptName'),
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
                                },
                                '->',{
                                    xtype:'button',
                                    text:i18n('i18n.coupon.insert'),
                                    handler:function(){
                                        var grid=Ext.getCmp('makeCrossGoodsLineGrid');
                                        addGoodsLine("up",grid);//调用走货线路区域要求方法,up指的是自动返券制定规则部分
                                    }
                                },{
                                    xtype:'button',
                                    text:i18n('i18n.coupon.delete'),
                                    handler:function(){
                                        var grid=Ext.getCmp('makeCrossGoodsLineGrid');
                                        deleteRecordsByGrid(grid);
                                    }
                                }]
                            }]
                        }]
                    }]
                }]
            },{//下面的优惠券使用条件
                region: 'south',
                height: 370,
                layout:'border',
                border:true,
                bodyPadding:5,
                margins: '0 5',
                xtype:'titleformpanel',
                title:i18n('i18n.coupon.couponRuleFormTitle'),
                items:[{
                    region:'center',
                    layout:'border',
                    border:false,
                    items:[{
                        xtype:'container',
                        flex:0.54,
                        region:'west',
                        border:false,
                        layout:'fit',
                        items:[{//自动返券左边的formPanel
                            id:'couponRuleFormPanel',//优惠券使用规则
                            defaultType : 'textfield',
                            xtype:'basicsearchformpanel',
                            bodyPadding:5,
                            defaults : {
                                labelWidth : 60,
                                labelAlign: 'right',
                                width : 180
                            },
                            layout : {
                                type : 'table',
                                columns : 5
                            },
                            items:[{
                                checked: true,
                                fieldLabel: i18n('i18n.coupon.costMode'),
                                xtype:'radio',
                                width:75,
                                name: 'costMode',
                                inputValue: '1',
                                listeners:{
                                    change:function(t,newValue,oldValue,e){
                                        var form=Ext.getCmp("couponRuleFormPanel").getForm();
                                        if(t.getValue()){//分级抵扣模式
                                            Ext.getCmp("upcostType").enable();//金额类型
                                            Ext.getCmp("upvalue").enable();//不低于/每满
                                            Ext.getCmp("downcostType").setValue("").clearInvalid();//金额类型
                                            Ext.getCmp("downvalue").setValue("").clearInvalid();//不低于/每满
                                            form.findField("discount").setValue("").clearInvalid();//抵扣
                                            Ext.getCmp("downcostType").disable();//金额类型
                                            Ext.getCmp("downvalue").disable();//不低于/每满
                                            form.findField("discount").disable();//抵扣
                                        }else{//严格抵扣
                                            Ext.getCmp("downcostType").enable();//金额类型
                                            Ext.getCmp("downvalue").enable();//不低于/每满
                                            form.findField("discount").enable();//抵扣
                                            Ext.getCmp("upcostType").setValue("").clearInvalid();//金额类型
                                            Ext.getCmp("upvalue").setValue("").clearInvalid();//不低于/每满
                                            Ext.getCmp("upcostType").disable();//金额类型
                                            Ext.getCmp("upvalue").disable();//不低于/每满
                                        }
                                    }
                                }
                            },{
                                hideLabel:true,
                                labelWidth:5,
                                xtype:'combobox',
                                name : 'upcostType',
                                id:'upcostType',
                                width:75,
                                allowBlank:false,
                                editable:false,
                                queryMode: 'local',
                                displayField:'codeDesc',
                                valueField:'code',  
                                store:getDataDictionaryByName(DataDictionary,'COUPON_AMOUNT_TYPE')
                            },{
                                fieldLabel : i18n('i18n.coupon.upvalue'),
                                name : 'value',
                                id:'upvalue',
                                labelWidth:40,
                                width:115,
                                allowBlank:false,
                                regex: /^[1-9][0-9]*$/,
                                regexText: i18n('i18n.coupon.onlyNumber'),
                                maxLength : 10,
                                maxLengthText : i18n('i18n.coupon.maxLength')+10
                            },{
                                xtype:'label',
                                colspan:2,
                                text:' 元'
                            },{
                                fieldLabel: '&nbsp;',
                                labelSeparator:'',
                                xtype:'radio',
                                width:75,
                                name: 'costMode',
                                inputValue: '2'
                            },{
                                hideLabel:true,
                                labelWidth:5,
                                xtype:'combobox',
                                name : 'downcostType',
                                id : 'downcostType',
                                width:75,
                                allowBlank:false,
                                disabled:true,
                                editable:false,
                                queryMode: 'local',
                                displayField:'codeDesc',
                                valueField:'code',  
                                store:getDataDictionaryByName(DataDictionary,'COUPON_AMOUNT_TYPE')
                            },{
                                fieldLabel : i18n('i18n.coupon.downvalue'),
                                name : 'value',
                                id : 'downvalue',
                                labelWidth:40,
                                width:115,
                                allowBlank:false,
                                regex: /^[1-9][0-9]*$/,
                                regexText: i18n('i18n.coupon.onlyNumber'),
                                disabled:true,
                                maxLength : 10,
                                maxLengthText : i18n('i18n.coupon.maxLength')+10
                            },{
                                fieldLabel : i18n('i18n.coupon.discount'),
                                name : 'discount',
                                labelWidth:30,
                                width:95,
                                allowBlank:false,
                                regex: /^[1-9][0-9]*$/,
                                regexText: i18n('i18n.coupon.onlyNumber'),
                                disabled:true,
                                maxLength : 10,
                                maxLengthText : i18n('i18n.coupon.maxLength')+10
                            },{
                                xtype:'label',
                                text:' 元'
                            },{
                                fieldLabel: i18n('i18n.coupon.costAddedMode'),
                                xtype:'checkbox',
                                width:75,
                                name: 'costAddedMode',
                                inputValue: '1',
                                listeners:{
                                    change:function(t,newValue,oldValue,e){
                                        var form=Ext.getCmp("couponRuleFormPanel").getForm();
                                        if(t.getValue()){
                                            form.findField("costAddedType").enable();//增值费类型
                                            form.findField("costAdded").enable();//增值费金额
                                        }else{
                                            form.findField("costAddedType").setValue("").clearInvalid();//增值费类型
                                            form.findField("costAdded").setValue("").clearInvalid();//增值费金额
                                            form.findField("costAddedType").disable();//增值费类型
                                            form.findField("costAdded").disable();//增值费金额
                                        }
                                    }
                                }
                            },{
                                hideLabel:true,
                                labelWidth:5,
                                xtype:'combobox',
                                disabled:true,
                                name : 'costAddedType',
                                width:75,
                                allowBlank:false,
                                editable:false,
                                queryMode: 'local',
                                displayField:'codeDesc',
                                valueField:'code',  
                                store:getDataDictionaryByName(DataDictionary,'COUPON_APPRECIATION_TYPE')
                            },{
                                fieldLabel : i18n('i18n.coupon.upvalue'),
                                name : 'costAdded',
                                labelWidth:40,
                                width:115,
                                allowBlank:false,
                                regex: /^[1-9][0-9]*$/,
                                regexText: i18n('i18n.coupon.onlyNumber'),
                                disabled:true,
                                maxLength : 10,
                                maxLengthText : i18n('i18n.coupon.maxLength')+10
                            },{
                                xtype:'label',
                                colspan:2,
                                text:' 元'
                            },{
                                xtype : 'datetimefield',
                                name: 'begintime',
                                value:new Date((new Date).getFullYear(),(new Date).getMonth(),(new Date).getDate(),0,0,0),
                                defaultTime:[0,0,0],
                                allowBlank:false,
                                width:190,
                                colspan:2,
                                minValue:new Date(),
                                fieldLabel : i18n('i18n.coupon.couponRuleTime'),
                                editable:false,
                                format: 'Y-m-d H:i'
//                              defaultTime:[0,0,0],
                            },{
                                xtype : 'datetimefield',
                                name: 'endtime',
//                              value:new Date((new Date).getFullYear(),(new Date).getMonth(),(new Date).getDate(),23,59,59),
//                              defaultTime:[23,59,59],
                                allowBlank: false,
                                width:220,
                                allowBlank:false,
                                colspan:3,
                                minValue:new Date(),
                                fieldLabel : i18n('i18n.coupon.go'),
                                editable:false,
                                format: 'Y-m-d H:i'
//                              defaultTime:[0,0,0],
                            },{
                                fieldLabel : i18n('i18n.coupon.makeProductType'),
                                xtype:'combobox',
                                name : 'useProductType',
                                width:410,
                                colspan:5,
                                editable:false,
                                queryMode: 'local',
                                allowBlank:false,
                                blankText:i18n('i18n.coupon.productTypeCanNotNull'),
                                multiSelect:true,
                                displayField:'codeDesc',
                                valueField:'code',  
                                store:productTypeStore
                            },{//订单来源
                                fieldLabel : i18n('i18n.coupon.makeOrderSourceType'),
                                xtype:'combobox',
                                name : 'useOrderSourceType',
                                colspan:5,
                                width:410,
                                editable:false,
                                queryMode: 'local',
                                multiSelect:true,
                                displayField:'codeDesc',
                                valueField:'code',  
                                store: getOrderResourcesStore()
                            },{
                                fieldLabel : i18n('i18n.coupon.makeCustomerLevelType'),
                                xtype:'combobox',
                                name : 'useCustomerLevelType',
                                colspan:5,
                                width:410,
                                editable:false,
                                queryMode: 'local',
                                multiSelect:true,
                                displayField:'codeDesc',
                                valueField:'code',  
                                store:getDataDictionaryByName(DataDictionary,'MEMBER_GRADE')
                            },{
                                fieldLabel : i18n('i18n.coupon.makeCustomerTradeType'),
                                xtype:'combobox',
                                name : 'useCustomerTradeType',
                                colspan:5,
                                width:410,
                                editable:false,
                                queryMode: 'local',
                                multiSelect:true,
                                displayField:'codeDesc',
                                valueField:'code',  
                                store:getDataDictionaryByName(DataDictionary,'TRADE')
                            }]
                        }]
                    },{
                        region:'center',
                        flex:0.46,
                        layout: 'border',
                        xtype:'container',
                        items:[{
                            region:'north',
                            height:23,
                            xtype:'container',
                            layout : {
                                type : 'table',
                                columns : 3
                            },
                            defaultType : 'textfield',
                            defaults : {
                                labelWidth : 65,
                                labelAlign: 'right',
                                width : 180
                            },
                            items:[{
                                fieldLabel : i18n('i18n.coupon.ruleType'),
                                xtype:'combobox',
                                name : 'ruleType',
                                id:'downruleType',
                                width:220,
                                labelWidth:80,
                                editable:false,
                                queryMode: 'local',
                                displayField: 'codeDesc',
                                valueField: 'code',
                                store:Ext.create('RuleTypeStore'),
                                listeners:{
                                    beforeselect:function(t){
                                        oldValueDown=t.getValue();
                                    },
                                    select:function(t,newValue,oldValue,e){//切换标签页时的操作
                                        var oldValue = oldValueDown;
                                        var newValue = t.getValue();
                                        if(Ext.getCmp("useWalkGoodsLineGrid").store.getCount()===0 && 
                                                Ext.getCmp("useCrossGoodsLineGrid").store.getCount()===0){
                                            setActiveGoodLine("down",t.getValue());
                                        }else if(t.getValue()===GOOD_LINE_LEAVE || t.getValue()===GOOD_LINE_ARRIVED){// 发到货
                                            if(Ext.getCmp("useWalkGoodsLineGrid").store.getCount()>0){
                                                t.setValue(oldValue);
                                                Ext.Msg.show({
                                                    title:'CRM提醒您:',
                                                    width:110+16*15,
                                                    msg:'<div id="message">'+"切换选项后会清空列表数据，是否继续？"+'</div>',
                                                    buttons: Ext.Msg.YESNO,
                                                    icon:Ext.MessageBox.QUESTION,
                                                    callback:function(e){
                                                        if(e=='yes'){
                                                            t.setValue(newValue);
                                                            Ext.getCmp("useWalkGoodsLineGrid").store.removeAll();
                                                            setActiveGoodLine("down",t.getValue());
                                                        }
                                                    }
                                                });
                                            }else if(Ext.getCmp("useCrossGoodsLineGrid").store.getCount()===0){
                                                setActiveGoodLine("down",t.getValue());
                                            }else{
                                                //是否显示复制button
                                                isShowCopyButton();
                                            }
                                        }else if(t.getValue()===GOOD_LINE_AREA){//走货
                                            if(Ext.getCmp("useCrossGoodsLineGrid").store.getCount()>0){
                                                t.setValue(oldValue);
                                                Ext.Msg.show({
                                                    title:'CRM提醒您:',
                                                    width:110+16*15,
                                                    msg:'<div id="message">'+"切换选项后会清空列表数据，是否继续？"+'</div>',
                                                    buttons: Ext.Msg.YESNO,
                                                    icon:Ext.MessageBox.QUESTION,
                                                    callback:function(e){
                                                        if(e=='yes'){
                                                            t.setValue(newValue);
                                                            Ext.getCmp("useCrossGoodsLineGrid").store.removeAll();
                                                            setActiveGoodLine("down",t.getValue());
                                                        }
                                                    }
                                                });
                                            }else{
                                                setActiveGoodLine("down",t.getValue());
                                            }
                                        }
                                    }
                                }
                            },{
                                xtype:'hidden',
                                width:27
                            },{
                                xtype:'button',
                                width:110,
                                id:'copybtn',
                                disabled:true,
                                text:i18n('i18n.coupon.copybtn'),
                                handler:function(){
                                    copyGoodsLine();
                                }
                            }]
                        },{
                            region:'center',
                            layout: 'card',
                            id:'downCradPanel',
                            xtype:'container',
                            margins: '5 5 0 5',
                            items: [{//使用优惠券走货线路
                                xtype:'popupgridpanel',
                                id:'useWalkGoodsLineGrid',
                                selModel:Ext.create('Ext.selection.CheckboxModel'),
                                store:Ext.create('UseWalkGoodsLineStore'),
                                columns: [
                                   { xtype:'rownumberer',width:40,header:i18n('i18n.coupon.rownumberer')},    
                                   { header: '<center>'+i18n('i18n.coupon.beginDeptOrCityName')+'</center>', dataIndex: 'beginDeptOrCityName',sortable:false, flex: 0.5 },
                                   { header: '<center>'+i18n('i18n.coupon.endDeptOrCityName')+'</center>', dataIndex: 'endDeptOrCityName',sortable:false, flex: 0.5 }
                                ],
                                tbar:[{
                                    fieldLabel : i18n('i18n.coupon.BeginDeptOrCity'),
                                    hideLabel:true,
                                    xtype:'combobox',
                                    name : 'downBeginDeptOrCityCombox',
                                    id:'downBeginDeptOrCityCombox',
                                    width:105,
                                    store:Ext.create('WalkGoodDeptStore',{
                                        listeners:{
                                            beforeload:function(store, operation, eOpts){
                                                Ext.apply(operation,{
                                                    params : {
                                                            'deptName':Ext.getCmp("downBeginDeptOrCityCombox").getRawValue()
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
                                    emptyText:i18n('i18n.coupon.inputDeptName'),
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
                                    fieldLabel : i18n('i18n.coupon.go'),
                                    xtype:'combobox',
                                    name : 'downEndDeptOrCityCombox',
                                    id:'downEndDeptOrCityCombox',
                                    width:125,
                                    labelWidth:20,
                                    store:Ext.create('WalkGoodDeptStore',{
                                        listeners:{
                                            beforeload:function(store, operation, eOpts){
                                                Ext.apply(operation,{
                                                    params : {
                                                            'deptName':Ext.getCmp("downEndDeptOrCityCombox").getRawValue()
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
                                    emptyText:i18n('i18n.coupon.inputDeptName'),
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
                                },'->',{
                                    xtype:'button',
                                    text:i18n('i18n.coupon.insert'),
                                    handler:function(){
                                        var grid=Ext.getCmp('useWalkGoodsLineGrid');
                                        addWalkGoodsLine("down",grid);//调用线路区域要求方法，down指的是使用规则部分
                                    }
                                },{
                                    xtype:'button',
                                    text:i18n('i18n.coupon.delete'),
                                    handler:function(){
                                        var grid=Ext.getCmp('useWalkGoodsLineGrid');
                                        deleteRecordsByGrid(grid);
                                    }
                                }]
                            },{//使用优惠券发到货线路
                                xtype:'popupgridpanel',
                                id:'useCrossGoodsLineGrid',
                                selModel:Ext.create('Ext.selection.CheckboxModel'),
                                store:Ext.create('UseCrossGoodsLineStore'),
                                columns: [
                                   { xtype:'rownumberer',width:40,header:i18n('i18n.coupon.rownumberer')},       
                                   { header: '<center>'+i18n('i18n.coupon.beginDeptName')+'</center>', dataIndex: 'beginDeptOrCityName',sortable:false, flex: 1 }
                                ],
                                tbar:[{
                                    fieldLabel : i18n('i18n.coupon.deptName'),
                                    xtype:'combobox',
                                    name : 'useGoodsLineDept',
                                    id:'useGoodsLineDept',
                                    width:230,
                                    labelWidth:40,
                                    store:Ext.create('StartEndGoodDeptStore',{
                                        listeners:{
                                            beforeload:function(store, operation, eOpts){
                                                Ext.apply(operation,{
                                                    params : {
                                                            'deptName':Ext.getCmp("useGoodsLineDept").getRawValue()
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
                                    emptyText:i18n('i18n.coupon.inputDeptName'),
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
                                },'->',{
                                    xtype:'button',
                                    text:i18n('i18n.coupon.insert'),
                                    handler:function(){
                                        var grid=Ext.getCmp('useCrossGoodsLineGrid');
                                        addGoodsLine("down",grid);//调用线路区域要求方法，down指的是使用规则部分
                                    }
                                },{
                                    xtype:'button',
                                    text:i18n('i18n.coupon.delete'),
                                    handler:function(){
                                        var grid=Ext.getCmp('useCrossGoodsLineGrid');
                                        deleteRecordsByGrid(grid);
                                    }
                                }]
                            }]
                        }]
                    }]
                },{
                    region:'south',
                    height:115,
                    xtype:'notitleformpanel',
                    id:'messageForm',
                    layout : {
                        type : 'table',
                        columns : 3
                    },
                    defaults : {
                        labelWidth : 65,
                        labelAlign: 'right'
                    },
                    items:[{
                        xtype     : 'textareafield',
                        name      : 'smsContent',
                        fieldLabel: i18n('i18n.coupon.msgContent'),
                        width:362,
                        height    : 50,
                        maxLength:80,
                        maxLengthText : i18n('i18n.coupon.maxLength')+80,
                        allowBlank:false,
                        listeners:{
                            change:function(t,newValue,oldValue,e){
                                var count=80-t.getValue().length;
                                if(count>=0){
                                    Ext.getCmp("wordCount").setValue("("+count+"/140)");    
                                }
                            }
                        }
                    },{
                        xtype:'displayfield',
                        id:'wordCount',
                        value:"(80/140)"
                    },{
                        xtype     : 'textareafield',
                        name      : 'describe',
                        width:342,
                        allowBlank:false,
                        maxLength : 500,
                        maxLengthText : i18n('i18n.coupon.maxLength')+500,
                        labelWidth : 75,
                        fieldLabel: i18n('i18n.coupon.describe'),
                        height    : 50
                    },{
                        xtype     : 'displayfield',
                        fieldLabel: ' ',
                        labelSeparator:'',
                        value:i18n('i18n.coupon.displayfieldContent')
                    }]
                }]
            }],
            buttons:[{
                text: i18n('i18n.coupon.saveMarketPlanBtn'),
                handler:function(){
                    //调用保存方法  1保存、0启用
                    saveCoupon("1");
                }
            },{ 
                text: i18n('i18n.coupon.saveStartMarketPlanBtn'),
                handler:function(){
                    //调用保存方法 1保存、0启用
                    saveCoupon("0");
                }
            },{ 
                text: i18n('i18n.coupon.reset'),
                handler:function(){
                    //重置表单
                    resetCoupon();
                }
            }]
        }]
    });
});

/**
 * 线路区域要求方法（发到货线路）
 * @param upOrDown有制定规则和使用规则两部分，如果是制定规则调用的话传up，否则就是使用规则
 * @param grid
 * @returns {Boolean}
 */
function addGoodsLine(upOrDown,grid){
    if(grid.store.getCount()>9){
        MessageUtil.showMessage(i18n('i18n.coupon.maxTenItem'));
        return false;
    }
    var record=new GoodsLineModel();
    if(!Ext.isEmpty(upOrDown) && upOrDown==="up"){
        if(Ext.isEmpty(Ext.getCmp("upruleType").getValue())){
            MessageUtil.showMessage(i18n('i18n.coupon.chooseBeginDeptOrCity'));
            return false;
        }
        if(Ext.isEmpty(Ext.getCmp("makeGoodsLineDept").getValue())){
            MessageUtil.showMessage(i18n('i18n.coupon.chooseDept'));
            return false;
        }
        if(Ext.getCmp("upruleType").getValue()===GOOD_LINE_LEAVE ||
           Ext.getCmp("upruleType").getValue()===GOOD_LINE_ARRIVED){
           //把发到货路线名和id设置为发货路线名和id，以便显示，到后台会根据发到货类型设置其相应的值
            if(grid.store.find("beginDeptOrCityId",Ext.getCmp("makeGoodsLineDept").getValue())!=-1){
                MessageUtil.showMessage(i18n('i18n.coupon.noRepeatAdd'));   
                return false;
            }
            record.set("beginDeptOrCityId",Ext.getCmp("makeGoodsLineDept").getValue());
            record.set("beginDeptOrCityName",Ext.getCmp("makeGoodsLineDept").getRawValue());
        }
        Ext.getCmp("makeGoodsLineDept").setValue("");
    }else{
        if(Ext.isEmpty(Ext.getCmp("downruleType").getValue())){
            MessageUtil.showMessage(i18n('i18n.coupon.chooseBeginDeptOrCity'));
            return false;
        }
        if(Ext.isEmpty(Ext.getCmp("useGoodsLineDept").getValue())){
            MessageUtil.showMessage(i18n('i18n.coupon.chooseDept'));
            return false;
        }
        if(Ext.getCmp("downruleType").getValue()===GOOD_LINE_LEAVE ||
           Ext.getCmp("downruleType").getValue()===GOOD_LINE_ARRIVED){
            //把发到货路线名和id设置为发货路线名和id，以便显示，到后台会根据发到货类型设置其相应的值
            if(grid.store.find("beginDeptOrCityId",Ext.getCmp("useGoodsLineDept").getValue())!=-1){
                MessageUtil.showMessage(i18n('i18n.coupon.noRepeatAdd'));   
                return false;
            }
            record.set("beginDeptOrCityId",Ext.getCmp("useGoodsLineDept").getValue());
            record.set("beginDeptOrCityName",Ext.getCmp("useGoodsLineDept").getRawValue());
        }
        Ext.getCmp("useGoodsLineDept").setValue("");
    }
    record.commit();
    grid.store.insert(0,record);
}
/**
 * 线路区域要求方法（走货线路）
 * @param upOrDown有制定规则和使用规则两部分，如果是制定规则调用的话传up，否则就是使用规则
 * @param grid
 * @returns {Boolean}
 */
function addWalkGoodsLine(upOrDown,grid){
    if(grid.store.getCount()>9){
        MessageUtil.showMessage(i18n('i18n.coupon.maxTenItem'));
        return false;
    }
    var record=new GoodsLineModel();
    if(!Ext.isEmpty(upOrDown) && upOrDown==="up"){
        if(Ext.isEmpty(Ext.getCmp("upruleType").getValue())){
            MessageUtil.showMessage(i18n('i18n.coupon.chooseBeginDeptOrCity'));
            return false;
        }
        //出发外场id
        var beginId = Ext.getCmp("upBeginDeptOrCityCombox").getValue();
        //出发外场名称
        var beginName = Ext.getCmp("upBeginDeptOrCityCombox").getRawValue();
        //到达外场id
        var endId = Ext.getCmp("upEndDeptOrCityCombox").getValue();
        //到达外场名称
        var endName = Ext.getCmp("upEndDeptOrCityCombox").getRawValue();
        
        //如果发到货外场都为空
        if(Ext.isEmpty(beginId) && Ext.isEmpty(endId)){
            MessageUtil.showMessage(i18n('i18n.coupon.chooseOutDept'));
            return false;
        }
        
        //是否重复添加
        var isSame = false;
        //遍历是否有相同的线路
        grid.store.each(function(obj){
            if(obj.get("beginDeptOrCityId") == beginId && 
               obj.get("endDeptOrCityId") == endId){
                //相同的线路给出提示
                MessageUtil.showMessage(i18n('i18n.coupon.noRepeatAdd'));   
                isSame=true;
                return false;
            }
        });
        //如果有相同的线路返回
        if(isSame){
            return;
        }
        
        record.set("beginDeptOrCityId",beginId);
        record.set("beginDeptOrCityName",beginName);
        record.set("endDeptOrCityId",endId);
        record.set("endDeptOrCityName",endName);
        Ext.getCmp("upBeginDeptOrCityCombox").setValue("");
        Ext.getCmp("upEndDeptOrCityCombox").setValue("");
    }else{
        if(Ext.isEmpty(Ext.getCmp("downruleType").getValue())){
            MessageUtil.showMessage(i18n('i18n.coupon.chooseBeginDeptOrCity'));
            return false;
        }
        //出发外场id
        var beginId = Ext.getCmp("downBeginDeptOrCityCombox").getValue();
        //出发外场名称
        var beginName = Ext.getCmp("downBeginDeptOrCityCombox").getRawValue();
        //到达外场id
        var endId = Ext.getCmp("downEndDeptOrCityCombox").getValue();
        //到达外场名称
        var endName = Ext.getCmp("downEndDeptOrCityCombox").getRawValue();
        
        //如果发到货外场都为空
        if(Ext.isEmpty(beginId) && Ext.isEmpty(endId)){
            MessageUtil.showMessage(i18n('i18n.coupon.chooseOutDept'));
            return false;
        }
        //是否重复添加
        var isSame = false;
        //遍历是否有相同的线路
        grid.store.each(function(obj){
            if(obj.get("beginDeptOrCityId") == beginId && 
               obj.get("endDeptOrCityId") == endId){
                //相同的线路给出提示
                MessageUtil.showMessage(i18n('i18n.coupon.noRepeatAdd'));   
                isSame=true;
                return false;
            }
        });
        //如果有相同的线路返回
        if(isSame){
            return;
        }
        record.set("beginDeptOrCityId",beginId);
        record.set("beginDeptOrCityName",beginName);
        record.set("endDeptOrCityId",endId);
        record.set("endDeptOrCityName",endName);
        Ext.getCmp("downBeginDeptOrCityCombox").setValue("");
        Ext.getCmp("downEndDeptOrCityCombox").setValue("");
    }
    record.commit();
    grid.store.insert(0,record);
}
/**
 * Grid删除统一方法
 * @param grid grid对象
 * @author 张登
 * @时间 2012-11-15
 */
function deleteRecordsByGrid(grid){
    var records = grid.getSelectionModel().getSelection();
    if(records.length!=0){
        if (records) {
            MessageUtil.showQuestionMes(i18n('i18n.coupon.confirmDelete'), function(e) {
                if (e == 'yes') {
                    grid.store.remove(records);
                }
            });
        }   
    }else{
        MessageUtil.showMessage(i18n('i18n.coupon.selectRecord'));
    }
}

/**
 * 复制线路要求
 * @param grid grid对象
 * @author 张登
 * @时间 2012-11-15
 */
function copyGoodsLine(){
    if(Ext.getCmp("upruleType").getValue()===Ext.getCmp("downruleType").getValue()){
        if(Ext.getCmp("upruleType").getValue()==GOOD_LINE_LEAVE || Ext.getCmp("upruleType").getValue()==GOOD_LINE_ARRIVED){//发到货区域
            if(Ext.getCmp("useCrossGoodsLineGrid").store.getCount()>0){
                Ext.Msg.show({
                    title:'CRM提醒您:',
                    width:110+18*15,
                    msg:'<div id="message">'+"原线路区域要求有数据存在，是否进行覆盖？"+'</div>',
                    buttons: Ext.Msg.YESNO,
                    icon:Ext.MessageBox.QUESTION,
                    callback:function(e){
                        if(e=='yes'){
                            Ext.getCmp("useCrossGoodsLineGrid").store.removeAll();
                            Ext.getCmp("makeCrossGoodsLineGrid").store.each(function(t){
                                Ext.getCmp("useCrossGoodsLineGrid").store.add(t.copy());
                            });
                        }
                    }
                });
            }else{
                Ext.getCmp("makeCrossGoodsLineGrid").store.each(function(t){
                    Ext.getCmp("useCrossGoodsLineGrid").store.add(t.copy());
                });
            }
        }
        if(Ext.getCmp("upruleType").getValue()==GOOD_LINE_AREA){//走货线路
            if(Ext.getCmp("useWalkGoodsLineGrid").store.getCount()>0){
                Ext.Msg.show({
                    title:'CRM提醒您:',
                    width:110+18*15,
                    msg:'<div id="message">'+"原线路区域要求有数据存在，是否进行覆盖？"+'</div>',
                    buttons: Ext.Msg.YESNO,
                    icon:Ext.MessageBox.QUESTION,
                    callback:function(e){
                        if(e=='yes'){
                            Ext.getCmp("useWalkGoodsLineGrid").store.removeAll();
                            Ext.getCmp("makeWalkGoodsLineGrid").store.each(function(t){
                                Ext.getCmp("useWalkGoodsLineGrid").store.add(t.copy());
                            });
                        }
                    }
                });
            }else{
                Ext.getCmp("makeWalkGoodsLineGrid").store.each(function(t){
                    Ext.getCmp("useWalkGoodsLineGrid").store.add(t.copy());
                });
            }
        }
    }else{
        MessageUtil.showMessage(i18n('i18n.coupon.beginDeptOrCitySame'));
    }
}

/**
 * 根据线路区域要求显示对应的列表
 * @param value
 */
function setActiveGoodLine(upOrdwon,value){
    if(upOrdwon==="up"){
        if(value===GOOD_LINE_LEAVE || value===GOOD_LINE_ARRIVED){//如果是3、4激活发到货Grid  2是激活走货线路
            Ext.getCmp("makeCrossGoodsLineGrid").store.removeAll();
            Ext.getCmp("makeWalkGoodsLineGrid").store.removeAll();
            Ext.getCmp("upCradPanel").getLayout().setActiveItem(1);
        }else{
            Ext.getCmp("upCradPanel").getLayout().setActiveItem(0);
        }
    }else{
        if(value===GOOD_LINE_LEAVE || value===GOOD_LINE_ARRIVED){//如果是3、4激活发到货Grid  2是激活走货线路
            Ext.getCmp("useCrossGoodsLineGrid").store.removeAll();
            Ext.getCmp("useWalkGoodsLineGrid").store.removeAll();
            Ext.getCmp("downCradPanel").getLayout().setActiveItem(1);
        }else{
            Ext.getCmp("downCradPanel").getLayout().setActiveItem(0);
        }
    }
    //是否显示复制button
    isShowCopyButton();
}

/**
 * 是否显示复制button
 */
function isShowCopyButton(){
    if(Ext.getCmp("upruleType").getValue()===Ext.getCmp("downruleType").getValue()){
        Ext.getCmp("copybtn").enable();
    }else{
        Ext.getCmp("copybtn").disable();
    }
}

/**
 * 重置表单
 */
function resetCoupon(){
    var marketplanFrom=Ext.getCmp("marketplanFrom").getForm();//营销计划
    marketplanFrom.findField('activityName').reset();
    marketplanFrom.findField('couponInfo').reset();
    marketplanFrom.findField('createRule').reset();
    marketplanFrom.findField('useRule').reset();
    marketplanFrom.findField('planName').setValue("").clearInvalid();
    if(Ext.getCmp("couponType").getValue()===COUPON_SENDHAND){//手动自动
        var ruleCouponHandForm=Ext.getCmp("ruleCouponHandTab").getForm();//手动规则
        ruleCouponHandForm.reset();
    }else{//自动
        var ruleCouponAutoForm=Ext.getCmp("ruleCouponAutoForm").getForm();//手动规则
        ruleCouponAutoForm.reset();
        //返券金额
        if(Ext.getCmp("autoCouponCostGrid").store.getCount()>0){
            Ext.getCmp("autoCouponCostGrid").store.removeAll();
        }       
        Ext.getCmp("costType").setValue("");
        Ext.getCmp("upruleType").setValue("");
        Ext.getCmp("upBeginDeptOrCityCombox").setValue("");
        Ext.getCmp("upEndDeptOrCityCombox").setValue("");
        Ext.getCmp("downEndDeptOrCityCombox").setValue("");
        Ext.getCmp("makeGoodsLineDept").setValue("");
        if(Ext.getCmp("makeCrossGoodsLineGrid").store.getCount()>0){
            Ext.getCmp("makeCrossGoodsLineGrid").store.removeAll();
        }   
        if(Ext.getCmp("makeWalkGoodsLineGrid").store.getCount()>0){
            Ext.getCmp("makeWalkGoodsLineGrid").store.removeAll();
        }
    }
    var couponRuleForm=Ext.getCmp("couponRuleFormPanel").getForm();//使用规则
    couponRuleForm.reset();
    Ext.getCmp("downruleType").setValue("");
    Ext.getCmp("downBeginDeptOrCityCombox").setValue("");
    Ext.getCmp("downEndDeptOrCityCombox").setValue("");
    Ext.getCmp("downEndDeptOrCityCombox").setValue("");
    Ext.getCmp("useGoodsLineDept").setValue("");
    if(Ext.getCmp("useWalkGoodsLineGrid").store.getCount()>0){
        Ext.getCmp("useWalkGoodsLineGrid").store.removeAll();
    }   
    if(Ext.getCmp("useCrossGoodsLineGrid").store.getCount()>0){
        Ext.getCmp("useCrossGoodsLineGrid").store.removeAll();
    }
    var messageForm=Ext.getCmp("messageForm").getForm();//短信和描述
    messageForm.reset();
}
/**
 * 切换手动自动时的提示
 * @returns {Boolean}
 */
function bIsChangeTab(){
    var ruleCouponAutoForm=Ext.getCmp("ruleCouponAutoForm").getForm();//手动规则
    var autoEndTime=ruleCouponAutoForm.findField('autoEndTime').getValue();
    if(!Ext.isEmpty(autoEndTime)){
        return true;
    }
    var makeProductType=ruleCouponAutoForm.findField('makeProductType').getValue();
    if(!Ext.isEmpty(makeProductType)){
        return true;
    }
    var makeOrderSourceType=ruleCouponAutoForm.findField('makeOrderSourceType').getValue();
    if(!Ext.isEmpty(makeOrderSourceType)){
        return true;
    }
    var makeCustomerLevelType=ruleCouponAutoForm.findField('makeCustomerLevelType').getValue();
    if(!Ext.isEmpty(makeCustomerLevelType)){
        return true;
    }
    var makeCustomerTradeType=ruleCouponAutoForm.findField('makeCustomerTradeType').getValue();
    if(!Ext.isEmpty(makeCustomerTradeType)){
        return true;
    }
    //返券金额
    if(Ext.getCmp("autoCouponCostGrid").store.getCount()>0){
        return true;
    }
    //线路区域要求
    if(Ext.getCmp("upruleType").getValue()===GOOD_LINE_LEAVE || Ext.getCmp("upruleType").getValue()===GOOD_LINE_ARRIVED){
        if(Ext.getCmp("makeWalkGoodsLineGrid").store.getCount()>0){
            return true;
        }
    }else{
        if(Ext.getCmp("makeCrossGoodsLineGrid").store.getCount()>0){
            return true;
        }
    };  
    return false;
}

/**
 * 保存优惠券
 */
function saveCoupon(saveBj){
    var params=null;
    var marketplanFrom=Ext.getCmp("marketplanFrom").getForm();//营销计划
    if(!marketplanFrom.isValid()){
        return false;
    }
    var planName=marketplanFrom.findField('planName').getValue();// 营销计划名称
    var couponType=Ext.getCmp("couponType").getValue();//营销计划类型
    //auth 李春雨
    var activityId=marketplanFrom.findField('activityName').getValue();//市场推广活动ID
    //判断市场推广活动id是否为空 如果为空则获取全部抵扣类型的值 否则 则获取推广活动下的抵扣类型
    var discountType= marketplanFrom.findField('couponInfo').getValue();//抵扣类型
    
    var couponValue=null;// 优惠券金额（手动）
    var couponQuantity=null;// 优惠券数量（手动）
    
    var autoBeginTime=null;// 返券期限开始时间(自动)
    var autoEndTime=null;// 返券期限结束时间(自动)
    var makeProductType=null;//产品类型（自动）
    var makeOrderSourceType=null;//订单来源（自动）
    var makeCustomerLevelType=null;//客户级别（自动）
    var makeCustomerTradeType=null;//客户行业（自动）
    var autoCouponCost = new Array();//返券金额（自动）
    var autoGoodsLine = new Array();//线路区域要求（自动）
    if(couponType===COUPON_SENDHAND){//手动自动
        var ruleCouponHandForm=Ext.getCmp("ruleCouponHandTab").getForm();//手动规则
        if(!ruleCouponHandForm.isValid()){
            return false;
        }
        couponValue=ruleCouponHandForm.findField('couponValue').getValue();// 优惠券金额
        couponQuantity=ruleCouponHandForm.findField('couponQuantity').getValue();// 优惠券数量
    }else{//自动
        var ruleCouponAutoForm=Ext.getCmp("ruleCouponAutoForm").getForm();//手动规则
        if(!ruleCouponAutoForm.isValid()){
            return false;
        }
        autoBeginTime=ruleCouponAutoForm.findField('autoBeginTime').getValue();// 返券期限开始时间
        autoEndTime=ruleCouponAutoForm.findField('autoEndTime').getValue();// 返券期限结束时间
//      if(DpUtil.compareTwoDate(autoBeginTime,autoEndTime)<=0){
//          MessageUtil.showMessage(i18n('i18n.coupon.makeEndtimeGTStarttime'));
//          return false;
//      };
        makeProductType=ruleCouponAutoForm.findField('makeProductType').getValue();
        makeOrderSourceType=ruleCouponAutoForm.findField('makeOrderSourceType').getValue();
        makeCustomerLevelType=ruleCouponAutoForm.findField('makeCustomerLevelType').getValue();
        makeCustomerTradeType=ruleCouponAutoForm.findField('makeCustomerTradeType').getValue();
        if( Ext.getCmp("autoCouponCostGrid").store.getCount()===0){
            MessageUtil.showMessage(i18n('i18n.coupon.autoCouponCostIsNotNull'));
            return false;
        };
        var j=1;
        var bj=false;
        //返券金额
        Ext.getCmp("autoCouponCostGrid").store.each(function(record){
            if(Ext.isEmpty(record.get("costDown")) && Ext.isEmpty(record.get("coupoCost"))){
                Ext.getCmp("autoCouponCostGrid").store.remove(record);
            }else if(Ext.isEmpty(record.get("costDown"))){
                bj=true;
                MessageUtil.showMessage("（自动返券）返券金额第"+j+"行中，金额下限不能为空！");
                return false;
            }else if(Ext.isEmpty(record.get("coupoCost"))){
                bj=true;
                MessageUtil.showMessage("（自动返券）返券金额第"+j+"行中，返券金额不能为空！");
                return false;
            }
            record.set("costType",Ext.getCmp("costType").getValue());
            autoCouponCost.push(record.data);
            j++;
        });
        if(bj){
            return false;
        }
        //线路区域要求
        if(Ext.getCmp("upruleType").getValue()===GOOD_LINE_LEAVE || Ext.getCmp("upruleType").getValue()===GOOD_LINE_ARRIVED){
            //发到货
            Ext.getCmp("makeCrossGoodsLineGrid").store.each(function(record){
                record.set("regdemand",Ext.getCmp("upruleType").getValue());
                autoGoodsLine.push(record.data);
            });
        }else{
            //走货
            Ext.getCmp("makeWalkGoodsLineGrid").store.each(function(record){
                record.set("regdemand",Ext.getCmp("upruleType").getValue());
                autoGoodsLine.push(record.data);
            });
        };  
    }
    var couponRuleForm=Ext.getCmp("couponRuleFormPanel").getForm();//使用规则
    if(!couponRuleForm.isValid()){
        return false;
    }
    var costMode=null;// 分级抵扣模式
    var costType=null// 金额类型    默认为“1” 运费，“2”开单金额
    var value=null;//// 使用金额
    var discount=null;// 抵扣金额  选择分级抵扣模式 时 使用
    if(couponRuleForm.findField('costMode').getValue()){//默认选择分级抵扣模式
        costMode="1";
        costType=Ext.getCmp("upcostType").getValue();
        value=Ext.getCmp("upvalue").getValue();
    }else{
        costMode="2";
        costType=Ext.getCmp("downcostType").getValue();
        value=Ext.getCmp("downvalue").getValue();
        discount=couponRuleForm.findField('discount').getValue();
    }
    var costAddedMode=null;
    var costAddedType=null;
    var costAdded=null;
    if(couponRuleForm.findField('costAddedMode').getValue()){// 增值费要求   默认为“0”没选中，“1”选中
        costAddedMode="1";
        costAddedType=couponRuleForm.findField('costAddedType').getValue();
        costAdded=couponRuleForm.findField('costAdded').getValue();
    }else{
        costAddedMode="0";
    }
    var begintime=couponRuleForm.findField('begintime').getValue();
    var endtime=couponRuleForm.findField('endtime').getValue();
//  if(DpUtil.compareTwoDate(begintime,endtime)<=0){
//      MessageUtil.showMessage(i18n('i18n.coupon.useEndtimeGTStarttime'));
//      return false;
//  };
    var useProductType=couponRuleForm.findField('useProductType').getValue();//产品类型（使用）
    var useOrderSourceType=couponRuleForm.findField('useOrderSourceType').getValue();//订单来源（使用）
    var useCustomerLevelType=couponRuleForm.findField('useCustomerLevelType').getValue();//客户级别（使用）
    var useCustomerTradeType=couponRuleForm.findField('useCustomerTradeType').getValue();//客户行业（使用）
    
    var messageForm=Ext.getCmp("messageForm").getForm();//短信和描述
    if(!messageForm.isValid()){
        return false;
    }
    var smsContent=messageForm.findField('smsContent').getValue();
    var describe=messageForm.findField('describe').getValue();  
    
    //线路区域要求
    var goodsLines = new Array();
    if(Ext.getCmp("downruleType").getValue()===GOOD_LINE_LEAVE || Ext.getCmp("downruleType").getValue()===GOOD_LINE_ARRIVED){
        //发到货
        Ext.getCmp("useCrossGoodsLineGrid").store.each(function(record){
            record.set("regdemand",Ext.getCmp("downruleType").getValue());
            goodsLines.push(record.data);
        });
    }else{
        //走货
        Ext.getCmp("useWalkGoodsLineGrid").store.each(function(record){
            record.set("regdemand",Ext.getCmp("downruleType").getValue());
            goodsLines.push(record.data);
        });
    };      
    params={
        'marketPlaneVO':{
            'marketplan':{
                'planName':planName,
                'couponType':couponType,
                //新加字段  市场推广活动ID：activityId 抵扣类型：discountType  auth:李春雨
                'activityId' : activityId,
                'discountType' : discountType
            },
            'ruleCouponHand':{
                'couponValue':couponValue,
                'couponQuantity':couponQuantity
            },
            'ruleCouponAuto':{
                'autoBeginTime':autoBeginTime,
                'autoEndTime':autoEndTime,
                'autoCouponCost':autoCouponCost,
                'createGoodsLine':autoGoodsLine
            },
            'couponRule':{
                'costMode':costMode,
                'costType':costType,
                'value':value,
                'discount':discount,
                'costAddedMode':costAddedMode,
                'costAddedType':costAddedType,
                'costAdded':costAdded,
                'begintime':begintime,
                'endtime':endtime,
                'smsContent':smsContent,
                'describe':describe,
                'goodsLines':goodsLines
            }
        },
        'couponTypeVo':{
            'useProductType':useProductType,
            'useOrderSourceType':useOrderSourceType,
            'useCustomerLevelType':useCustomerLevelType,
            'useCustomerTradeType':useCustomerTradeType,
            'makeProductType':makeProductType,
            'makeOrderSourceType':makeOrderSourceType,
            'makeCustomerLevelType':makeCustomerLevelType,
            'makeCustomerTradeType':makeCustomerTradeType
        },
        'saveBj':saveBj //保存或者启用 saveBj=0时是启用规则 1时是保存规则
    };
    var successFn = function(json){
        resetCoupon();
        //去掉进度条
        if(!Ext.isEmpty(saveBj) && saveBj==="0"){
            Ext.MessageBox.hide();
        }
        MessageUtil.showInfoMes(i18n('i18n.coupon.saveSuccess'));
    };
    var failureFn = function(json){
        //去掉进度条
        if(!Ext.isEmpty(saveBj) && saveBj==="0"){
            Ext.MessageBox.hide();
        }
        MessageUtil.showErrorMes(json.message);
    };
    if(!Ext.isEmpty(saveBj) && saveBj==="0"){
        //启用规则
        MessageUtil.showQuestionMes("是否确认启用规则？", function(e){   
            if (e == 'yes') {
                Ext.MessageBox.show({
                    id:'showMessageBox',
                    title:'请等待',
                    msg: '正在执行......',
                    wait:true
               });
               CouponAddStore.prototype.saveCoupon(params, successFn, failureFn);   
            }
        });
    }else{
        CouponAddStore.prototype.saveCoupon(params, successFn, failureFn);  
    }
    
}
