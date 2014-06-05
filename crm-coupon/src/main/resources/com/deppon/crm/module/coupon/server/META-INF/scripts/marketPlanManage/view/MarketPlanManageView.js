/**
 * 营销计划管理界面
 * 肖红叶
 * 2012-11
 */
var marketPlanID=null;
var ruleCouponHandId=null;
var ruleCouponAutoId=null;
var couponRuleId=null;
var upregdemand=null;
var downregdemand=null;
var bIsEdit=false;
var oldValueUp=null;
var oldValueDown=null;
//市场推广活动id
var markActivityId = null;
var canEdit = true;
//数据字段转化为数组
var discountProducts = new Array();
Ext.onReady(function(){
    //初始化提示信息条
    Ext.QuickTips.init();
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
        //优惠券来源
        'COUPON_RESOURCE',
        //抵扣类型 = 优惠产品
        'DISCOUNT_PRODUCTS'
    ];

    //初始化业务参数
    initDataDictionary(keys);
    //初始化订单来源
    initOrderSources();
    //产品类型的store，快递权限用户和零担权限用户显示的产品类型列表不同
    var productTypeForSelectPageStore = null;
    //如果具有查看零担产品类型的权限
    if(isPermission('/coupon/selectLogisticsProductType.action')){
        productTypeForSelectPageStore = getDataDictionaryByName(DataDictionary,'FOSS_PRODUCT_TYPE');
    }//如果具有查看快递产品类型的权限
    else if(isPermission('/coupon/selectExpressProductType.action')){
        productTypeForSelectPageStore = getDataDictionaryByName(DataDictionary,'EXPRESS_PRODUCT_TYPE');
    }
    //数据字段转化为数组
    getDataDictionaryByName(DataDictionary,'DISCOUNT_PRODUCTS').each(function(record){
        discountProducts.push(record.data);
    });
    //操作按钮面板
    Ext.define('MarketPlanButtonPanel',{
        extend:'NormalButtonPanel', 
        items:null,
        width:600,
        initComponent:function(){
            this.items = this.getItems();
            this.callParent();
        },
        getItems:function(){
            var me = this;
            return [{
                xtype:'leftbuttonpanel',
                items:[{//查看详情
                    xtype:'button',
                    text:i18n('i18n.coupon.searchDetail'),
                    handler:function(t){
                        t.disable();
                        bIsEdit=true;//为了控制复制新建后可编辑
                        updateOrdetailCoupon("0",t);
                    }
                },{//编辑
                    xtype:'button',
                    text:i18n('i18n.coupon.edit'),
                    handler:function(t){
                        t.disable();
                        updateOrdetailCoupon("1",t);
                    }
                },{//短信发券by xiaohongye
                      text:i18n('i18n.coupon.sendCouponByMsg'),
                      xtype:'button',
                      handler:function(){
                          //获取选中的营销计划记录
                          var selection=Ext.getCmp('marketPlanQueryResultGridId').getSelectionModel().
                          getSelection();
                          if (selection.length == 0) {
                            MessageUtil.showMessage(i18n('i18n.coupon.selectOneRecordToSendMsg'));
                            return false;
                          }
                          if (selection.length != 1) {
                            MessageUtil.showMessage(i18n('i18n.coupon.onlySelectOneRecordToSendMsg'));
                            return false;
                          }
                          
                          //判断优惠券类型是否为手动发券,只有当优惠券类型为手动发券时才可进行短信发券
                          if(selection[0].get("marketStatus")!='20'){
                            MessageUtil.showMessage(i18n('i18n.coupon.marketPlanStatusUnuse'));
                            return false;
                          }
                          
                          //判断优惠券类型是否为手动发券,只有当优惠券类型为手动发券时才可进行短信发券
                          if(selection[0].get("couponType")=='AUTOCOUPON'){
                            MessageUtil.showMessage(i18n('i18n.coupon.autoCouponCanNotSendMsg'));
                            return false;
                          }                     
                          else if(selection[0].get("couponType")=='HANDCOUPON'){
                              //为短信发券页面的优惠券基本信息营销计划编码赋值
                              Ext.getCmp('couponInfoPanelId').getForm().findField('planNumber').
                              setValue(selection[0].get("planNumber"));
                              var planNumber =Ext.getCmp('couponInfoPanelId').getForm().findField('planNumber').
                              getValue();
                                if(!Ext.isEmpty(planNumber)){
                                    var successFn = function(json){
                                        var couponBasicInfo=json.couponBasicInfo;
                                        Ext.getCmp('couponInfoPanelId').getForm().findField('planName').
                                        setValue(couponBasicInfo.planName),
                                        Ext.getCmp('couponInfoPanelId').getForm().findField('sendedNum').
                                        setValue(couponBasicInfo.sendedNum),
                                        Ext.getCmp('couponInfoPanelId').getForm().findField('sms').
                                        setValue(couponBasicInfo.sms),
                                        Ext.getCmp('couponInfoPanelId').getForm().findField('balance').
                                        setValue(couponBasicInfo.balance),
                                        Ext.getCmp('couponInfoPanelId').getForm().findField('couponQuantity').
                                        setValue(couponBasicInfo.couponQuantity);
                                        
                                        //初始化短信发券页面基本信息
                                        Ext.getCmp("telephoneListGridId").store.removeAll();//清除手机号列表中的所有数据
                                        getTelephoneCount()
                                        handleTelephone = null;
                                        telephoneLists = new Array();
                                        Ext.getCmp('imputTelephone').setValue('');
                                        sendCouponByMessageWindow.show();
                                    }
                                    var param = {
                                        'marketPlanID':planNumber
                                    };
                                    var failureFn=function(json){
                                        MessageUtil.showErrorMes(json.message);
                                    }
                                    MarketPlanMangeStore.prototype.searchSendCouponVOByMPI(param,successFn,
                                            failureFn);
                                }
                                else{
                                    MessageUtil.showMessage(i18n('i18n.coupon.marketPlanNumberIsNull'));
                                }   
                         }
                         else{
                             MessageUtil.showMessage(i18n('i18n.coupon.onlyHandCouponCanSendMsg'));
                             return false;
                         }
                      }
                  }]
               },{
                 xtype:'middlebuttonpanel' 
               },{
                xtype : 'rightbuttonpanel',
                items : [{//查询
                    xtype : 'button',
                    text : i18n('i18n.coupon.query'),
                    handler:function(){
                        if(!(Ext.getCmp('marketPlanForm').getForm().isValid())){
                            return false;
                        }
                        //查询按钮功能实现区
                        Ext.getCmp("marketPlanQueryResultGridId").store.loadPage(1);
                    }
                 },{//重置
                    text:i18n('i18n.coupon.reset'),
                    xtype:'button',
                    handler:function(){
                      //重置按钮,清空查询条件form
                        var marketPlanForm = Ext.getCmp("marketPlanForm").getForm();
                        marketPlanForm.reset();
                      //如果具有查看零担产品类型的权限
                        if(isPermission('/coupon/selectLogisticsProductType.action')){
                            marketPlanForm.findField('businesstype').setValue(i18n('i18n.marketplan.logistic'));
                        }//如果具有查看快递产品类型的权限
                        else if(isPermission('/coupon/selectExpressProductType.action')){
                            marketPlanForm.findField('businesstype').setValue(i18n('i18n.marketplan.express'));
                         
                        }
                    }
                 }]
            }];
        }
    });
    
    /**
     * 营销计划管理查询结果表格
     */
    Ext.define('MarketPlanQueryResultGrid',{
        extend:'SearchGridPanel',
        defaults:{
            align:'center'
        },
        autoScroll:true,
        selModel:Ext.create('Ext.selection.CheckboxModel',{mode:'SIGLE',id:'selModelId'}),
        initComponent:function(){ 
            var me = this;
            
            var store = Ext.create('MarketPlanManageListStore',{id:'marketPlanManageListStoreId'});
            me.store = store;
            store.on('beforeload',function(developPlaneGirdStore,operation,e){
                //判断界面校验是否通过
                if(!Ext.getCmp('marketPlanForm').getForm().isValid()){
                    MessageUtil.showMessage(i18n('i18n.coupon.message_warn'));
                    return false;
                }
                //营销计划查询最大范围为3个月!
                var marketPlanForm = Ext.getCmp("marketPlanForm");
                var createBeginTime = marketPlanForm.getForm().findField('createBeginTime').getValue();
                var createEndTime = marketPlanForm.getForm().findField('createEndTime').getValue();
                var createTimeDaysByMonth = CouponUtil.compareTwoDateByMonth(createBeginTime,createEndTime,3);
                if(createTimeDaysByMonth){
                    MessageUtil.showErrorMes(i18n('i18n.coupon.searchMarketPlanDateIsLong'));
                    return false;
                }
              //将表单查询条件传递到后台去
                var searchParams = {
                    'marketplan.planNumber':marketPlanForm.getForm().findField('planNumber').getValue(),
                    'marketplan.planName':marketPlanForm.getForm().findField('planName').getValue(),
                    'marketplan.couponType':marketPlanForm.getForm().findField('couponType').getValue(),
                    'marketplan.marketStatus':marketPlanForm.getForm().findField('marketStatus').getValue(),
                    'marketplan.createBeginTime':marketPlanForm.getForm().findField('createBeginTime').getValue(),
                    'marketplan.createEndTime':marketPlanForm.getForm().findField('createEndTime').getValue()
                };
                Ext.apply(operation,{
                    params : searchParams
                });
            });
            me.columns = [
                {//序号
                    xtype:'rownumberer',
                    header:i18n('i18n.coupon.rownumberer'),
                    width:40
                },{//营销计划编码
                    header:i18n('i18n.coupon.planNumber'),
                    width:110,
                    dataIndex:'planNumber'
                },{ //营销计划名称
                    header :i18n('i18n.coupon.planName'),
                    dataIndex:'planName',
                    width:150,
                    renderer:function(value){
                        if(!Ext.isEmpty(value)){
                            return '<span data-qtip="'+value+'">'+value+'</span>';
                        }
                    }
                },{//优惠券类型
                    header :i18n('i18n.coupon.couponType'),
                    dataIndex:'couponType',
                    width:80,
                    renderer:function(val){
                        if(val===COUPON_SENDHAND){
                            return '手动';
                        }else if(val===COUPON_SENDAUTO){
                            return '自动';
                        }else{
                            return '';
                        }
                    }
                },{//活动开始时间
                    header :i18n('i18n.coupon.activityBeginTime'),
                    renderer : DpUtil.renderDate,
                    dataIndex:'activityBeginTime',
                    width:100
                },{//活动结束时间
                    header :i18n('i18n.coupon.activityEndTime'),
                    renderer : DpUtil.renderDate,
                    dataIndex:'activityEndTime',
                    width:100
                },{//启用状态
                    header :i18n('i18n.coupon.marketStatus'),
                    dataIndex:'marketStatus',
                    width:90,
                    renderer:function(val){
                        if(val===MARKETPLAN_NOUSE){
                            return '未启用';
                        }else if(val===MARKETPLAN_USING){
                            return '已启用';
                        }else if(val===MARKETPLAN_STOP){
                            return '已终止';
                        }else if(val===MARKETPLAN_FINISH){
                            return '已结束';
                        }else{
                            return '';
                        }
                    }
               },{//发券总数
                    header :i18n('i18n.coupon.couponSendTotal'),
                    dataIndex:'couponSendTotal',
                    width:80
                },{//未发送数量
                    header :i18n('i18n.coupon.couponNoSendCount'),
                    dataIndex:'couponNoSendCount',
                    width:80
                },{//已发送数量
                    header :i18n('i18n.coupon.couponSendCount'),
                    width:80,
                    dataIndex:'couponSendCount'
                },{ //已使用数量
                    header :i18n('i18n.coupon.couponUseCount'),
                    dataIndex:'couponUseCount',
                    width:80
                },{//已过期数量
                    header :i18n('i18n.coupon.couponOverdueCount'),
                    dataIndex:'couponOverdueCount',
                    width:80
                },{//优惠券生效时间
                    header :i18n('i18n.coupon.couponAutoBeginTime'),
                    renderer : DpUtil.renderDate,
                    dataIndex:'autoBeginTime',
                    width:120
                },{//优惠券失效时间
                    header :i18n('i18n.coupon.couponAutoEndTime'),
                    renderer : DpUtil.renderDate,
                    dataIndex:'autoEndTime',
                    width:120
                },{//创建时间
                    header :i18n('i18n.coupon.createBeginTime'),
                    renderer : DpUtil.renderDate,
                    dataIndex:'createDate',
                    width:80
               },{//最近修改人
                    header :i18n('i18n.coupon.modifyUser'),
                    dataIndex:'modifyUserName',
                    width:80
                }
            ];
            me.dockedItems=[{
                xtype:'pagingtoolbar',
                cls:'pagingtoolbar',
                store:store,
                dock:'bottom',
                displayInfo : true,
                autoScroll : true,
                items:[{//每页显示
                    text: i18n('i18n.coupon.performPerPage'),
                    xtype: 'tbtext'
                },Ext.create('Ext.form.ComboBox', {
                   width:window.screen.availWidth*0.0391,
                   triggerAction:'all',
                   forceSelection: true,
                   value:'20',
                   editable:false,
                   name:'comboItem',
                   displayField:'value',
                   valueField:'value',
                   queryMode:'local',
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
               }),{//条
                    text: i18n('i18n.coupon.items'),
                    xtype: 'tbtext'
               }]
            }];
            //添加双击事件
            me.on('itemdblclick',function(th,record,item,index,e,eOpts){
                bIsEdit=true;//为了控制复制新建后可编辑
                updateOrdetailCoupon("0","");
            });
            this.callParent();
        }
    });
    
    /**
     * 单击短信发券按钮，弹出的短信发券窗口
     * xiaohongye
    */
    Ext.define('SendCouponByMessageWindow',{
        extend:'PopWindow',
        title:i18n('i18n.coupon.sendCouponByMsg'),
        alias : 'widget.basicwindow',
        width:620,
        height:700,
        modal:true,
        layout:'fit',
        closeAction:'hide',
        items:[Ext.create('SendCouponByMsgPanel',{'id':'sendCouponByMsgPanelId'})],
        listeners:{
            hide:function(){
                document.body.scrollLeft=0;
                document.body.scrollTop=0;
                document.getElementsByTagName("html")[0].style.overflowY="hidden";
                document.getElementsByTagName("html")[0].style.overflowX="hidden";
                viewport.doLayout();
            }
        },
        buttons:[{//发送 by xiaohongye
            xtype:'button',
            text:i18n('i18n.coupon.sendMessage'),
            handler:function(){
                //验证手机号列表中的数据是否符合发送要求
                if( Ext.getCmp('telephoneListGridId').store.data.length < 1){
                    MessageUtil.showErrorMes(i18n('i18n.coupon.inputSubmitTelephone'));
                    return false;
                }               
                var canNotSubmit  = false;
                Ext.getCmp('telephoneListGridId').store.each(function(record){
                    if(record.data.validity === '0'){
                        MessageUtil.showErrorMes(i18n('i18n.coupon.CanNotSumbitByInvalidTel'));
                        canNotSubmit = true;
                        return false;
                    }
                });         
                if(canNotSubmit){
                    return false;
                }
                
                if( Ext.getCmp('telephoneListGridId').store.data.length > 1000){
                    MessageUtil.showErrorMes(i18n('i18n.coupon.CanNotSumbitByExceedNumber'));
                    return false;
                }               
                var balance = parseInt(Ext.getCmp('balance').getValue());
                if( Ext.getCmp('telephoneListGridId').store.data.length > balance){
                    MessageUtil.showErrorMes(i18n('i18n.coupon.CanNotSumbitByExceedRemainNumber'));
                    return false;
                }
                
                telephoneLists = new Array();
                //将手机号表格中的数据放入到列表数组中
                Ext.getCmp('telephoneListGridId').store.each(function(record){
                    telephoneLists.push(record.data);
                });
                
                //用户确认是否进行短信发券
                MessageUtil.showQuestionMes(i18n('i18n.coupon.sureToSendMsg'), function(e) {
                    if (e == 'yes') {
                        //执行发送操作
                        var successFn = function(json){
                            MessageUtil.showInfoMes(i18n('i18n.coupon.sendCouponByMsgIsSuccess'));      
                            //初始化短信发券页面基本信息
                            Ext.getCmp('telephoneListGridId').store.removeAll();
                            handleTelephone = null;
                            telephoneLists = new Array();
                            //获得手机号码列表中记录条数，显示到提示信息中
                            getTelephoneCount();
                            //刷新短信发券界面的优惠券基本信息
                            var refreshSuccessFn = function(json){
                                var couponBasicInfo=json.couponBasicInfo;
                                Ext.getCmp('couponInfoPanelId').getForm().findField('sendedNum').
                                setValue(couponBasicInfo.sendedNum),
                                Ext.getCmp('couponInfoPanelId').getForm().findField('sms').
                                setValue(couponBasicInfo.sms),
                                Ext.getCmp('couponInfoPanelId').getForm().findField('balance').
                                setValue(couponBasicInfo.balance),
                                Ext.getCmp('couponInfoPanelId').getForm().findField('couponQuantity').
                                setValue(couponBasicInfo.couponQuantity);           
                            }
                            var paramMeter = {
                                'marketPlanID':Ext.getCmp('couponInfoPanelId').getForm().findField('planNumber').
                                getValue()
                            };
                            var refreshFailureFn=function(json){
                                MessageUtil.showErrorMes(json.message);
                            }
                            MarketPlanMangeStore.prototype.searchSendCouponVOByMPI(paramMeter,refreshSuccessFn,
                                    refreshFailureFn);              
                        }
                        
                        var failureFn = function(json){
                            MessageUtil.showErrorMes(json.message);
                        };
                        
                        var param = {
                                'couponBasicInfo':{
                                    'planNumber':Ext.getCmp('couponInfoPanelId').getForm().findField('planNumber').getValue()
                                },
                                'couponCellphoneList':telephoneLists
                        };
                        MarketPlanMangeStore.prototype.sendCouponByCellphones(param, successFn, failureFn);
                    }
                }); 
            }
        },{//取消
            xtype:'button',
            text:i18n('i18n.coupon.cancel'),
            handler:function(){
                //初始化短信发券页面基本信息
                Ext.getCmp('couponInfoPanelId').getForm().reset();
                Ext.getCmp("telephoneListGridId").store.removeAll();//清除手机号列表中的所有数据
                Ext.getCmp('sendCouponByMessageWindowId').close();
            }
        }]
    });
    
    var sendCouponByMessageWindow = Ext.getCmp("sendCouponByMessageWindowId");//获取发送短信窗口
    if(!sendCouponByMessageWindow){
        sendCouponByMessageWindow = Ext.create('SendCouponByMessageWindow',{id:'sendCouponByMessageWindowId'});
    }

    /**
     * 显示优惠券明细
     */
    Ext.define('DetailCouponPopWindow',{
        extend:'PopWindow',
        alias : 'widget.basicwindow',
        width:825,
        height:770,
        modal:true,
//        layout:'fit',
        closeAction:'hide',
        autoScroll : true,
        items:[{//最大层panel
            autoScroll : true,
            xtype:'basicformpanel',
            layout: 'border',
            height : 750,
            items: [{//上面的营销计划基本信息
                region: 'north',
                id:'marketplanFrom',
                xtype:'form',
                border: false,
//                height:27,//设置第一行form高度
//                layout : {
//                    type : 'table',
//                    columns : 4
//                },
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
                    labelWidth : 75,
                    xtype:'combobox',
                    id:'couponType',
                    name : 'couponType',
                    value:'AUTOCOUPON',
                    editable:false,
                    queryMode: 'local',
                    width:180,
                    cls:'readonly',
                    readOnly:true,
                    displayField: 'codeDesc',
                    valueField: 'code',
                    store:Ext.create('CouponTypeStore')
                },{
                    fieldLabel : i18n('i18n.coupon.planNumber'),
                    name : 'planNumber',
                    columnWidth:.3,
                    cls:'readonly',
                    width:190,
                    readOnly:true,
                    maxLength : 80,
                    maxLengthText : i18n('i18n.coupon.maxLength')+80
                },{//营销计划名称，长度为80个字符
                    fieldLabel : i18n('i18n.coupon.planName'),
                    columnWidth:.3,
                    name : 'planName',
                    width:255,
                    allowBlank:false,
                    maxLength : 80,
                    maxLengthText : i18n('i18n.coupon.maxLength')+80
                },{
                    fieldLabel : i18n('i18n.coupon.marketStatus'),
                    columnWidth:.2,
                    xtype:'combobox',
                    name : 'marketStatus',
                    width:160,
                    cls:'readonly',
                    readOnly:true,
                    labelWidth:65,
                    editable:false,
                    queryMode: 'local',
                    displayField: 'name',
                    valueField: 'id',
                    store:Ext.create('Ext.data.Store', {
                        fields: ['id', 'name'],
                        data : [
                            {"id":"10", "name":"未启用"},
                            {"id":"20", "name":"已启用"},
                            {"id":"30", "name":"已终止"},
                            {"id":"40", "name":"已结束"}
                        ]
                    })
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
                    colspan:2,
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
                        }
                    }
                },{
                    xtype:'combobox',
                    columnWidth:.49999,
                    fieldLabel : i18n('i18n.coupon.marketActivity'),//市场推广活动
                    name : 'activityName',
                    id : 'activityNameId',
                    canReset : false,
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
                                }
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
                                combo.canReset = true;
                                combo.setValue("");
                                combo.ownerCt.getForm().findField('createRule').reset();
                                combo.ownerCt.getForm().findField('useRule').reset();
                                couponInfo.getStore().loadData(discountProducts);
                                if(combo.canReset){
                                    couponInfo.setValue("");
                                }
                            }
                        },
                        select : function(combo,records){
                            if(Ext.isEmpty(combo.getValue())){
                                return false;
                            }
                            combo.canReset = true;
                            markActivityId = records[0].get('id');
                            var activityForm = combo.ownerCt.getForm();
                            var couponInfo = activityForm.findField('couponInfo');
                            couponInfo.reset();
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
                        fieldLabel :i18n('i18n.coupon.useCouponValue'),
                        name : 'couponValue',
                        allowBlank:false,
                        regex: /^[0-9]*[1-9][0-9]*$/,
                        regexText: i18n('i18n.coupon.onlyNumber'),
                        maxLength : 20,
                        maxLengthText : i18n('i18n.coupon.maxLength')+20
                    },{
                        fieldLabel :i18n('i18n.coupon.couponQuantity'),
                        name : 'couponQuantity',
//                      regex: /^[0-9]*[1-9][0-9]*$/,
                        xtype:'numberfield',
                        regexText: i18n('i18n.coupon.onlyNumber'),
                        allowBlank:false,
                        minValue:1,
                        maxValue:100000
//                      maxLength : 6,
//                      maxLengthText : i18n('i18n.coupon.maxLength')+6
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
                                width:205,
                                fieldLabel : i18n('i18n.coupon.autoBeginTimeByPlanManager'),
                                editable:false,
                                minValue:new Date(),
                                format: 'Y-m-d H:i'
                            },{
                                xtype : 'datetimefield',
                                name: 'autoEndTime',
//                              value:new Date((new Date).getFullYear(),(new Date).getMonth(),(new Date).getDate(),23,59,59),
//                              defaultTime:[23,59,59],
                                allowBlank: false,
                                minValue:new Date(),
                                width:185,
                                fieldLabel : i18n('i18n.coupon.go'),
                                editable:false,
                                format: 'Y-m-d H:i'
//                              defaultTime:[0,0,0],
                            },{
                                fieldLabel : i18n('i18n.coupon.makeProductType'),
                                xtype:'combobox',
                                name : 'makeProductType',
                                colspan:2,
                                width:390,
                                editable:false,
                                queryMode: 'local',
                                multiSelect:true,
                                allowBlank:false,
                                blankText:i18n('i18n.coupon.productTypeCanNotNull'),
                                displayField:'codeDesc',
                                valueField:'code',  
                                store:productTypeForSelectPageStore
                            },{
                                fieldLabel : i18n('i18n.coupon.makeOrderSourceType'),
                                xtype:'combobox',
                                name : 'makeOrderSourceType',
                                colspan:2,
                                width:390,
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
                                width:390,
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
                                width:390,
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
                                    listeners:{
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
                                   allowBlank: false
                               }},
                               { header: '<center>'+i18n('i18n.coupon.autoCouponCost')+'</center>', dataIndex: 'coupoCost',sortable:false, flex: 0.5 ,editor: {
                                   xtype: 'numberfield',
                                   minValue:1,
                                   regex: /^[0-9]*[1-9][0-9]*$/,
                                   regexText: i18n('i18n.coupon.onlyNumber'),
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
                                id:'costAddBtn',
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
                                id:'costDelBtn',
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
                                fieldLabel : i18n('i18n.coupon.ruleType'),
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
                                            setActiveGoodLine("up",t.getValue(),null);
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
                                                            setActiveGoodLine("up",t.getValue(),null);
                                                        }
                                                    }
                                                });
                                            }else if(Ext.getCmp("makeCrossGoodsLineGrid").store.getCount()===0){
                                                setActiveGoodLine("up",t.getValue(),null);
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
                                                setActiveGoodLine("up",t.getValue(),null);
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
                            items: [{//走货线路时的Grid
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
                                    id:'makeWalkGoodsLineAddBtn',
                                    handler:function(){
                                        var grid=Ext.getCmp('makeWalkGoodsLineGrid');
                                        addWalkGoodsLine("up",grid);//调用线路区域要求方法,up指的是自动返券制定规则部分
                                    }
                                },{
                                    xtype:'button',
                                    text:i18n('i18n.coupon.delete'),
                                    id:'makeWalkGoodsLineDelBtn',
                                    handler:function(){
                                        var grid=Ext.getCmp('makeWalkGoodsLineGrid');
                                        deleteRecordsByGrid(grid);
                                    }
                                }]
                            },{//发到货线路Grid
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
                                },'->',{
                                    xtype:'button',
                                    text:i18n('i18n.coupon.insert'),
                                    id:'makeCrossGoodsLineAddBtn',
                                    handler:function(){
                                        var grid=Ext.getCmp('makeCrossGoodsLineGrid');
                                        addGoodsLine("up",grid);//调用走货线路区域要求方法,up指的是自动返券制定规则部分
                                    }
                                },{
                                    xtype:'button',
                                    id:'makeCrossGoodsLineDelBtn',
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
                height: 345,
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
                            bodyPadding:0,
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
                                id:'upcostMode',
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
                                regex: /^[0-9]*[1-9][0-9]*$/,
                                regexText: i18n('i18n.coupon.onlyNumber'),
                                maxLength : 20,
                                maxLengthText : i18n('i18n.coupon.maxLength')+20
                            },{
                                xtype:'label',
                                colspan:2,
                                text:' 元'
                            },{
                                fieldLabel: '&nbsp;',
                                labelSeparator:'',
                                xtype:'radio',
                                width:75,
                                id:'downcostMode',
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
                                regex: /^[0-9]*[1-9][0-9]*$/,
                                regexText: i18n('i18n.coupon.onlyNumber'),
                                disabled:true,
                                maxLength : 20,
                                maxLengthText : i18n('i18n.coupon.maxLength')+20
                            },{
                                fieldLabel : i18n('i18n.coupon.discount'),
                                name : 'discount',
                                labelWidth:30,
                                width:90,
                                allowBlank:false,
                                regex: /^[0-9]*[1-9][0-9]*$/,
                                regexText: i18n('i18n.coupon.onlyNumber'),
                                disabled:true,
                                maxLength : 20,
                                maxLengthText : i18n('i18n.coupon.maxLength')+20
                            },{
                                xtype:'label',
                                margin : '0 0 0 0',
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
                                regex: /^[0-9]*[1-9][0-9]*$/,
                                regexText: i18n('i18n.coupon.onlyNumber'),
                                disabled:true,
                                maxLength : 20,
                                maxLengthText : i18n('i18n.coupon.maxLength')+20
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
                            },{
                                xtype : 'datetimefield',
                                name: 'endtime',
//                              value:new Date((new Date).getFullYear(),(new Date).getMonth(),(new Date).getDate(),23,59,59),
//                              defaultTime:[23,59,59],
                                allowBlank: false,
                                width:215,
                                allowBlank:false,
                                colspan:3,
                                minValue:new Date(),
                                fieldLabel : i18n('i18n.coupon.go'),
                                editable:false,
                                format: 'Y-m-d H:i'
                            },{
                                fieldLabel : i18n('i18n.coupon.makeProductType'),
                                xtype:'combobox',
                                name : 'useProductType',
                                width:405,
                                colspan:5,
                                editable:false,
                                queryMode: 'local',
                                multiSelect:true,
                                displayField:'codeDesc',
                                allowBlank:false,
                                blankText:i18n('i18n.coupon.productTypeCanNotNull'),
                                valueField:'code',  
                                store:productTypeForSelectPageStore
                            },{
                                fieldLabel : i18n('i18n.coupon.makeOrderSourceType'),
                                xtype:'combobox',
                                name : 'useOrderSourceType',
                                colspan:5,
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
                                name : 'useCustomerLevelType',
                                colspan:5,
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
                                name : 'useCustomerTradeType',
                                colspan:5,
                                width:405,
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
                                margin : '0 0 12 0',
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
                                    select:function(t){
                                        var oldValue = oldValueDown;
                                        var newValue = t.getValue();
                                        if(Ext.getCmp("useWalkGoodsLineGrid").store.getCount()===0 && 
                                                Ext.getCmp("useCrossGoodsLineGrid").store.getCount()===0){
                                            setActiveGoodLine("down",t.getValue(),null);
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
                                                            setActiveGoodLine("down",t.getValue(),null);
                                                        }
                                                    }
                                                });
                                            }else if(Ext.getCmp("useCrossGoodsLineGrid").store.getCount()===0){
                                                setActiveGoodLine("down",t.getValue(),null);
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
                                                            setActiveGoodLine("down",t.getValue(),null);
                                                        }
                                                    }
                                                });
                                            }else{
                                                setActiveGoodLine("down",t.getValue(),null);
                                            }
                                        }
                                    }
                                }
                            },{
                                xtype:'hidden',
                                width:20
                            },{
                                xtype:'button',
                                width:110,
                                margin : '0 0 12 -5',
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
                            margins: '0 0 0 0',
                            items: [{//走货线路
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
                                    width:100,
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
                                    id:'useWalkGoodsLineAddBtn',
                                    text:i18n('i18n.coupon.insert'),
                                    handler:function(){
                                        var grid=Ext.getCmp('useWalkGoodsLineGrid');
                                        addWalkGoodsLine("down",grid);//调用线路区域要求方法，down指的是使用规则部分
                                    }
                                },{
                                    xtype:'button',
                                    id:'useWalkGoodsLineDelBtn',
                                    text:i18n('i18n.coupon.delete'),
                                    handler:function(){
                                        var grid=Ext.getCmp('useWalkGoodsLineGrid');
                                        deleteRecordsByGrid(grid);
                                    }
                                }]
                            },{//发到货线路Grid
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
                                    width:225,
                                    labelWidth:35,
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
                                },
                                '->',{
                                    xtype:'button',
                                    text:i18n('i18n.coupon.insert'),
                                    id:'useCrossGoodsLineAddBtn',
                                    handler:function(){
                                        var grid=Ext.getCmp('useCrossGoodsLineGrid');
                                        addGoodsLine("down",grid);//调用线路区域要求方法，down指的是使用规则部分
                                    }
                                },{
                                    xtype:'button',
                                    id:'useCrossGoodsLineDelBtn',
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
                    height:85,
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
                        width:357,
                        height    : 41,
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
                        width:330,
                        allowBlank:false,
                        maxLength : 500,
                        maxLengthText : i18n('i18n.coupon.maxLength')+500,
                        labelWidth : 75,
                        fieldLabel: i18n('i18n.coupon.describe'),
                        height    : 41
                    },{
                        xtype     : 'displayfield',
                        fieldLabel: ' ',
                        labelSeparator:'',
                        value:i18n('i18n.coupon.displayfieldContent')
                    }]
                }]
            }],
            buttons:[{//保存
                text: i18n('i18n.coupon.saveMarketPlanBtn'),
                id:'saveMarketPlanBtn',
                hidden:true,
                handler:function(t){
                    //配合快递优惠券所做的修改，判断当前登陆用户是否有权限对该优惠券进行操作；
                    var errorText = i18n('i18n.coupon.saveMarketPlanBtn');
                    isPermissionToOperateCoupon(errorText);
                    if(isNotPermissionToOperateCoupon){
                        isNotPermissionToOperateCoupon = false;
                        return false;
                    }
                    t.disable();
                    //调用保存方法
                    saveCoupon("save","1",t);
                }
            },{ //启用
                text: i18n('i18n.coupon.saveStartMarketPlanBtn'),
                hidden:true,
                id:'saveStartMarketPlanBtn',
                handler:function(t){
                    //配合快递优惠券所做的修改，判断当前登陆用户是否有权限对该优惠券进行操作；
                    var errorText = '启用';
                    isPermissionToOperateCoupon(errorText);
                    if(isNotPermissionToOperateCoupon){
                        isNotPermissionToOperateCoupon = false;
                        return false;
                    }
                    t.disable();
                    //调用保存方法 1保存、0启用
                    saveCoupon("save","0",t);
                }
            },{//编辑
                text: i18n('i18n.coupon.editMarketPlanBtn'),
                id:'updateMarketPlanBtn',
                hidden:true,
                handler:function(t){
                    //配合快递优惠券所做的修改，判断当前登陆用户是否有权限对该优惠券进行操作；
                    var errorText = i18n('i18n.coupon.edit');
                    isPermissionToOperateCoupon(errorText);
                    if(isNotPermissionToOperateCoupon){
                        isNotPermissionToOperateCoupon = false;
                        return false;
                    }
                    if(t.getText()===i18n('i18n.coupon.saveMarketPlanBtn')){
                        t.disable();
                        //调用修改方法
                        saveCoupon("update","1",t);
                    }
                    detailOrEdit("1",upregdemand,downregdemand);
                    t.setText(i18n('i18n.coupon.saveMarketPlanBtn'));
                    Ext.getCmp("startMarketPlanBtn").show();
                    
                    //auth：李春雨  点击编辑时 判断该市场推广活动是否可用   不可用则清空数据
                    canEditFunc();
                }
            },{ //启用规则
                text: i18n('i18n.coupon.saveStartMarketPlanBtn'),
                id:'startMarketPlanBtn',
                hidden:true,
                handler:function(t){
                    //配合快递优惠券所做的修改，判断当前登陆用户是否有权限对该优惠券进行操作；
                    var errorText = '启用';
                    isPermissionToOperateCoupon(errorText);
                    if(isNotPermissionToOperateCoupon){
                        isNotPermissionToOperateCoupon = false;
                        return false;
                    }
                    t.disable();
                    saveCoupon("update","0",t);
                }
            },{ //删除规则
                text: i18n('i18n.coupon.deleteMarketPlanBtn'),
                hidden:true,
                id:'deleteMarketPlanBtn',
                handler:function(t){
                    //配合快递优惠券所做的修改，判断当前登陆用户是否有权限对该优惠券进行操作；
                    var errorText = i18n('i18n.coupon.delete');
                    isPermissionToOperateCoupon(errorText);
                    if(isNotPermissionToOperateCoupon){
                        isNotPermissionToOperateCoupon = false;
                        return false;
                    }
                    t.disable();
                    if(!Ext.isEmpty(marketPlanID)){
                        var successFn = function(json){
                            t.enable();
                            Ext.getCmp("marketPlanQueryResultGridId").store.loadPage(1);
                            Ext.getCmp("detailCouponPopWindowId").close();
                            MessageUtil.showInfoMes(i18n('i18n.coupon.deleteMarketPlanSuccess'));
                        };
                        var failureFn = function(json){
                            t.enable();
                            MessageUtil.showErrorMes(json.message);
                        };
                        var params={
                            'marketPlanID':marketPlanID 
                        };
                        CouponAddStore.prototype.deleteMarketPlan(params, successFn, failureFn);    
                    }else{
                        t.enable();
                        MessageUtil.showMessage(i18n('i18n.coupon.marketIsNull'));
                    }
                }
            },{ //终止规则
                text: i18n('i18n.coupon.stopMarketPlanBtn'),
                hidden:true,
                id:'stopMarketPlanBtn',
                handler:function(t){
                    //配合快递优惠券所做的修改，判断当前登陆用户是否有权限对该优惠券进行操作；
                    var errorText = '终止';
                    isPermissionToOperateCoupon(errorText);
                    if(isNotPermissionToOperateCoupon){
                        isNotPermissionToOperateCoupon = false;
                        return false;
                    }
                    t.disable();
                    if(!Ext.isEmpty(marketPlanID)){
                        var successFn = function(json){
                            t.enable();
                            Ext.getCmp("marketPlanQueryResultGridId").store.loadPage(1);
                            Ext.getCmp("detailCouponPopWindowId").close();
                            MessageUtil.showInfoMes(i18n('i18n.coupon.stopMarketPlanSuccess'));
                        };
                        var failureFn = function(json){
                            t.enable();
                            MessageUtil.showErrorMes(json.message);
                        };
                        var params={
                            'marketPlanID':marketPlanID 
                        };
                        CouponAddStore.prototype.stopMarketPlan(params, successFn, failureFn);  
                    }else{
                        t.enable();
                        MessageUtil.showMessage(i18n('i18n.coupon.marketIsNull'));
                    }
                }
            },{ //复制新建
                text: i18n('i18n.coupon.copyNewBtn'),
                id:'copyNewBtn',
                handler:function(t){
                    //配合快递优惠券所做的修改，判断当前登陆用户是否有权限对该优惠券进行操作；
                    var errorText = i18n('i18n.coupon.copyNewBtn');
                    isPermissionToOperateCoupon(errorText);
                    if(isNotPermissionToOperateCoupon){
                        isNotPermissionToOperateCoupon = false;
                        return false;
                    }
                    t.hide();
                    marketPlanID=null;
                    ruleCouponHandId=null;;
                    ruleCouponAutoId=null;;
                    couponRuleId=null;;
                    if(bIsEdit){
                        detailOrEdit("1",upregdemand,downregdemand);
                    }
                    var marketplanFrom=Ext.getCmp("marketplanFrom").getForm();//营销计划
                    marketplanFrom.findField("planNumber").setValue("");
                    marketplanFrom.findField("marketStatus").setValue(MARKETPLAN_NOUSE);
                    Ext.getCmp("saveMarketPlanBtn").show();//保存
                    Ext.getCmp("saveStartMarketPlanBtn").show();//启用（保存）终止
                    Ext.getCmp("startMarketPlanBtn").hide();//启用
                    Ext.getCmp("updateMarketPlanBtn").hide();//编辑
                    Ext.getCmp("deleteMarketPlanBtn").hide();//删除
                    Ext.getCmp("stopMarketPlanBtn").hide();//终止
                    MessageUtil.showInfoMes(i18n('i18n.coupon.copyNewSuccess'));
                    //auth：李春雨  点击复制新建时 判断该市场推广活动是否可用   不可用则清空数据
                    canEditFunc();
                }
            },{ //关闭
                text: i18n('i18n.coupon.close'),
                handler:function(){
                    //关闭
                    Ext.getCmp("detailCouponPopWindowId").close();
                }
            }]
        }],
        listeners:{
            show : function(){
                //清空验证
                Ext.getCmp("marketplanFrom").getForm().clearInvalid();
            },
            hide:function(){
                document.body.scrollLeft=0;
                document.body.scrollTop=0;
                document.getElementsByTagName("html")[0].style.overflowY="hidden";
                document.getElementsByTagName("html")[0].style.overflowX="hidden";
                viewport.doLayout();
                //auth：李春雨 隐藏时处理数据 是否重置
                Ext.getCmp('activityNameId').canReset = false;
                Ext.getCmp('activityNameId').reset();
                Ext.getCmp("marketplanFrom").getForm().findField('createRule').reset();
                Ext.getCmp("marketplanFrom").getForm().findField('useRule').reset();
            }
        }
    });
    var detailCouponWin=Ext.getCmp("DetailCouponPopWindow");//获取win
    if(!detailCouponWin){
        detailCouponWin=Ext.create('DetailCouponPopWindow',{
            id:'detailCouponPopWindowId',
            listeners:{
                hide:function(){
                    Ext.getCmp("updateMarketPlanBtn").setText(i18n('i18n.coupon.editMarketPlanBtn'));
                    bIsEdit==false;
                    marketPlanID=null;
                    ruleCouponHandId=null;
                    ruleCouponAutoId=null;
                    couponRuleId=null;
                    downregdemand=null;
                    downregdemand=null;
                    resetCoupon();
                }
            }
        });
    }
    
    /**
     * 营销计划管理查询条件输入面板
     */
    Ext.define('MarketPlanFormForSearch',{//营销管理查询form
      extend:'Ext.form.Panel',
      layout:{
          type:'table',
          columns:4
      },
      defaults:{
          labelWidth:80,
          labelAlign:'right',
          width:240,
          margin:'4 5 4 5'
      },
      defaultType:'textfield',
      initComponent:function(){
          var me = this;
          this.items = [{//营销计划编码
                  fieldLabel:i18n('i18n.coupon.planNumber'),
                  name:'planNumber',
                  maxLength : 20,
                  maxLengthText : i18n('i18n.coupon.maxLength')+20
              },{//营销计划名称
                  fieldLabel:i18n('i18n.coupon.planName'),
                  name:'planName',
                  maxLength : 80,
                  maxLengthText : i18n('i18n.coupon.maxLength')+80
              },{//启用状态
                  xtype:'combo',
                  queryMode: 'local',
                  fieldLabel:i18n('i18n.coupon.marketStatus'),
                  name:'marketStatus',
                  value:'05',
                  labelWidth:55,
                  width:160,
                  colspan:2,
                  displayField: 'name',
                  valueField: 'id',
                  store:Ext.create('Ext.data.Store', {
                      fields: ['id', 'name'],
                      data : [
                          {"id":"10", "name":"未启用"},
                          {"id":"20", "name":"已启用"},
                          {"id":"30", "name":"已终止"},
                          {"id":"40", "name":"已结束"},
                          {"id":"05", "name":"全部"}
                      ]
                  }),
                  forceSelection :true,
                  listeners:{
                      change:function(combo){
                          if(Ext.isEmpty(combo.getValue())){
                              combo.setValue("");
                          }
                      }
                  }
              },{//业务类型
                xtype:'combobox',
                name:'businesstype',
                fieldLabel:i18n('i18n.marketplan.bustype'),
                disabled:true
            },{//优惠券来源
                  xtype:'combo',
                  queryMode: 'local',
                  fieldLabel:i18n('i18n.coupon.couponResource'),
                  name:'couponType',
                  displayField: 'codeDesc',
                  valueField: 'code',
                  store:getDataDictionaryByName(DataDictionary,'COUPON_RESOURCE'),
                  value:'HANDCOUPON',
                  forceSelection :true,
                  listeners:{
                      change:function(combo){
                          if(Ext.isEmpty(combo.getValue())){
                              combo.setValue("");
                          }
                      }
                  }
              },{//创建时间
                  xtype:'datefield',
                  fieldLabel:i18n('i18n.coupon.createBeginTime'),
                  format:'Y-m-d',
                  width:160,
                  editable:false,
                  labelWidth:55,
                  value:Ext.Date.add(Ext.Date.add(new Date(),Ext.Date.MONTH,-3),Ext.Date.DAY,1),
                  name:'createBeginTime'
              },{//创建时间到
                  xtype:'datefield',
                  fieldLabel:i18n('i18n.coupon.createEndTime'),
                  labelSeparator:'',
                  format:'Y-m-d',
                  editable:false,
                  labelWidth:5,
                  value : new Date(),
                  name:'createEndTime',
                  width:110
              }       
          ];
          this.callParent();
          //如果具有查看零担产品类型的权限
          if(isPermission('/coupon/selectLogisticsProductType.action')){
             this.getForm().findField('businesstype').setValue(i18n('i18n.marketplan.logistic'));
          }//如果具有查看快递产品类型的权限
          else if(isPermission('/coupon/selectExpressProductType.action')){
             this.getForm().findField('businesstype').setValue(i18n('i18n.marketplan.express'));
             
          }
          var store = this.getForm().findField('couponType').store;
      }
    });
    
    /*
     * 新建一个viewport，用于显示营销计划管理界面
     * 肖红叶
     */
    var viewport=Ext.create('Ext.Viewport',{
        id:'viewportForRender',
        layout:'border',
        items:[{//营销计划管理查询条件输入面板
            xtype : 'basicsearchformpanel',
            region:'north',
            layout:'fit',
            items:[Ext.create('MarketPlanFormForSearch',{id:'marketPlanForm'})]
        },{
            xtype : 'basicpanel',
            region:'center',
            layout:'border',
            items:[
                {//按钮面板
                   xtype : 'basicpanel',
                   region:'north',
                   layout:'fit',
                   items:[Ext.create('MarketPlanButtonPanel',{id:'marketPlanButtonPanelId'})]
                },{//营销计划管理查询结果表格
                   xtype : 'basicpanel',
                   region:'center',
                   items:[ Ext.create('MarketPlanQueryResultGrid',{id:'marketPlanQueryResultGridId'})]
                }   
           ]
        }]
    });
    viewport.setAutoScroll(false);
    viewport.doLayout();

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
//              for(var i=0;i<Ext.getCmp("makeCrossGoodsLineGrid").store.getCount();i++){
//                  Ext.getCmp("useCrossGoodsLineGrid").store.add(Ext.getCmp("makeCrossGoodsLineGrid").store.getAt(i));
//              }
                Ext.getCmp("useCrossGoodsLineGrid").store.removeAll();
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
//              for(var i=0;i<Ext.getCmp("makeWalkGoodsLineGrid").store.getCount();i++){
//                  alert(Ext.getCmp("makeWalkGoodsLineGrid").store.getAt(i));
//                  Ext.getCmp("useWalkGoodsLineGrid").store.add(Ext.getCmp("makeWalkGoodsLineGrid").store.getAt(i));
//              }
                Ext.getCmp("makeWalkGoodsLineGrid").store.removeAll();
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
function setActiveGoodLine(upOrdwon,value,isUpdate){
    if(upOrdwon==="up"){
        if(value===GOOD_LINE_LEAVE || value===GOOD_LINE_ARRIVED){//如果是3、4激活发到货Grid  2是激活走货线路
            if(Ext.isEmpty(isUpdate)){//如果isUpdate是update就不删除了
                Ext.getCmp("makeCrossGoodsLineGrid").store.removeAll();
                Ext.getCmp("makeWalkGoodsLineGrid").store.removeAll();
            }
            Ext.getCmp("upCradPanel").getLayout().setActiveItem(1);
        }else{
            Ext.getCmp("upCradPanel").getLayout().setActiveItem(0);
        }
    }else{
        if(value===GOOD_LINE_LEAVE || value===GOOD_LINE_ARRIVED){//如果是3、4激活发到货Grid  2是激活走货线路
            if(Ext.isEmpty(isUpdate)){//如果isUpdate是update就不删除了
                Ext.getCmp("useCrossGoodsLineGrid").store.removeAll();
                Ext.getCmp("useWalkGoodsLineGrid").store.removeAll();
            }
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
//  marketplanFrom.reset();
    marketplanFrom.findField('planName').setValue("").clearInvalid();
    marketplanFrom.findField('planNumber').setValue("").clearInvalid();
    marketplanFrom.findField('marketStatus').setValue("").clearInvalid();
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
 * 保存优惠券
 */
function saveCoupon(saveOrUpdate,saveBj,btnObj){
    var params=null;
    
    var marketplanFrom=Ext.getCmp("marketplanFrom").getForm();//营销计划
    if(!marketplanFrom.isValid()){
        if(!Ext.isEmpty(btnObj)){//解除按钮
            btnObj.enable();
        }
        return false;
    }
//  var planNumber=marketplanFrom.findField('planNumber').getValue();// 营销计划编码
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
            if(!Ext.isEmpty(btnObj)){//解除按钮
                btnObj.enable();
            }
            return false;
        }
        couponValue=ruleCouponHandForm.findField('couponValue').getValue();// 优惠券金额
        couponQuantity=ruleCouponHandForm.findField('couponQuantity').getValue();// 优惠券数量
    }else{//自动
        var ruleCouponAutoForm=Ext.getCmp("ruleCouponAutoForm").getForm();//手动规则
        if(!ruleCouponAutoForm.isValid()){
            if(!Ext.isEmpty(btnObj)){//解除按钮
                btnObj.enable();
            }
            return false;
        }
        autoBeginTime=ruleCouponAutoForm.findField('autoBeginTime').getValue();// 返券期限开始时间
        autoEndTime=ruleCouponAutoForm.findField('autoEndTime').getValue();// 返券期限结束时间
//      if(DpUtil.compareTwoDate(autoBeginTime,autoEndTime)<=0){
//          if(!Ext.isEmpty(btnObj)){//解除按钮
//              btnObj.enable();
//          }
//          MessageUtil.showMessage(i18n('i18n.coupon.makeEndtimeGTStarttime'));
//          return false;
//      };
        makeProductType=ruleCouponAutoForm.findField('makeProductType').getValue();
        makeOrderSourceType=ruleCouponAutoForm.findField('makeOrderSourceType').getValue();
        makeCustomerLevelType=ruleCouponAutoForm.findField('makeCustomerLevelType').getValue();
        makeCustomerTradeType=ruleCouponAutoForm.findField('makeCustomerTradeType').getValue();
        if( Ext.getCmp("autoCouponCostGrid").store.getCount()===0){
            if(!Ext.isEmpty(btnObj)){//解除按钮
                btnObj.enable();
            }
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
                if(!Ext.isEmpty(btnObj)){//解除按钮
                    btnObj.enable();
                }
                MessageUtil.showMessage("（自动返券）返券金额第"+j+"行中，金额下限不能为空！");
                return false;
            }else if(Ext.isEmpty(record.get("coupoCost"))){
                bj=true;
                if(!Ext.isEmpty(btnObj)){//解除按钮
                    btnObj.enable();
                }
                MessageUtil.showMessage("（自动返券）返券金额第"+j+"行中，返券金额不能为空！");
                return false;
            }
            record.set("costType",Ext.getCmp("costType").getValue());
            autoCouponCost.push(record.data);
            j++;
        });
        if(bj){
            if(!Ext.isEmpty(btnObj)){//解除按钮
                btnObj.enable();
            }
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
        if(!Ext.isEmpty(btnObj)){//解除按钮
            btnObj.enable();
        }
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
//      if(!Ext.isEmpty(btnObj)){//解除按钮
//          btnObj.enable();
//      }
//      MessageUtil.showMessage(i18n('i18n.coupon.useEndtimeGTStarttime'));
//      return false;
//  };
    var useProductType=couponRuleForm.findField('useProductType').getValue();//产品类型（使用）
    var useOrderSourceType=couponRuleForm.findField('useOrderSourceType').getValue();//订单来源（使用）
    var useCustomerLevelType=couponRuleForm.findField('useCustomerLevelType').getValue();//客户级别（使用）
    var useCustomerTradeType=couponRuleForm.findField('useCustomerTradeType').getValue();//客户行业（使用）
    
    var messageForm=Ext.getCmp("messageForm").getForm();//短信和描述
    if(!messageForm.isValid()){
        if(!Ext.isEmpty(btnObj)){//解除按钮
            btnObj.enable();
        }
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
    if(saveOrUpdate==="save"){//如果是保存设置id为null
        marketPlanID=null;
        ruleCouponHandId=null;
        ruleCouponAutoId=null;
        couponRuleId=null;
    }
    params={
        'marketPlaneVO':{
            'marketplan':{
                'id':marketPlanID,
                'planName':planName,
                'couponType':couponType,
                //新加字段  市场推广活动ID：activityId 抵扣类型：discountType  auth:李春雨
                'activityId' : activityId,
                'discountType' : discountType
            },
            'ruleCouponHand':{
                'id':ruleCouponHandId,
                'couponValue':couponValue,
                'couponQuantity':couponQuantity
            },
            'ruleCouponAuto':{
                'id':ruleCouponAutoId,
                'autoBeginTime':autoBeginTime,
                'autoEndTime':autoEndTime,
                'autoCouponCost':autoCouponCost,
                'createGoodsLine':autoGoodsLine
            },
            'couponRule':{
                'id':couponRuleId,
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
    if(!Ext.isEmpty(saveBj) && saveBj==="0"){
        Ext.MessageBox.show({
            id:'showMessageBox',
            title:'请等待',
            msg: '正在执行......',
            wait:true
       });
    }
    if(saveOrUpdate==="save"){//保存
        var successFn = function(json){
            Ext.getCmp("marketPlanQueryResultGridId").store.loadPage(1);
            Ext.getCmp("detailCouponPopWindowId").close();
            if(!Ext.isEmpty(btnObj)){//解除按钮
                btnObj.enable();
            }
            //去掉进度条
            if(!Ext.isEmpty(saveBj) && saveBj==="0"){
                Ext.MessageBox.hide();
            }
            
            MessageUtil.showInfoMes(i18n('i18n.coupon.saveSuccess'));
        };
        var failureFn = function(json){
            if(!Ext.isEmpty(btnObj)){//解除按钮
                btnObj.enable();
            }
            //去掉进度条
            if(!Ext.isEmpty(saveBj) && saveBj==="0"){
                Ext.MessageBox.hide();
            }
            MessageUtil.showErrorMes(json.message);
        };
        CouponAddStore.prototype.saveCoupon(params, successFn, failureFn);  
    }else{//修改
        var successFn = function(json){
            Ext.getCmp("marketPlanQueryResultGridId").store.loadPage(1);
            Ext.getCmp("detailCouponPopWindowId").close();
            if(!Ext.isEmpty(btnObj)){//解除按钮
                btnObj.enable();
            }
            //去掉进度条
            if(!Ext.isEmpty(saveBj) && saveBj==="0"){
                Ext.MessageBox.hide();
            }
            MessageUtil.showInfoMes(i18n('i18n.coupon.saveSuccess'));
        };
        var failureFn = function(json){
            if(!Ext.isEmpty(btnObj)){//解除按钮
                btnObj.enable();
            }
            //去掉进度条
            if(!Ext.isEmpty(saveBj) && saveBj==="0"){
                Ext.MessageBox.hide();
            }
            MessageUtil.showErrorMes(json.message);
        };
        CouponAddStore.prototype.updateMarketPlan(params, successFn, failureFn);    
    }
    
}

/**
 * 查看详情、修改
 * @param id
 * @param updateOrDetail
 * @returns {Boolean}
 */
function updateOrdetailCoupon(bj,btnObj){
    var grid = Ext.getCmp('marketPlanQueryResultGridId');
    var records=grid.getSelectionModel().getSelection();
    //判断是否选中行
    if (records.length == 0) {
        if(!Ext.isEmpty(btnObj)){//解除按钮
            btnObj.enable();
        }
        MessageUtil.showMessage(i18n('i18n.coupon.selectOneRecord'));
        return false;
    }
    if (records.length != 1) {
        if(!Ext.isEmpty(btnObj)){//解除按钮
            btnObj.enable();
        }
        MessageUtil.showMessage(i18n('i18n.coupon.selectOneRecord'));
        return false;
    }
    if(Ext.isEmpty(records[0].get("id"))){
        if(!Ext.isEmpty(btnObj)){//解除按钮
            btnObj.enable();
        }
        MessageUtil.showMessage(i18n('i18n.coupon.chooseNoValid'));
        return false;
    }
    marketPlanID=records[0].get("id");//设置Id
    if(bj==="1"){//修改
        marketStatus=records[0].get("marketStatus");
        if(marketStatus!=MARKETPLAN_NOUSE){
            if(!Ext.isEmpty(btnObj)){//解除按钮
                btnObj.enable();
            }
            MessageUtil.showMessage(i18n('i18n.coupon.onlyNoStartMarketEdit'));
            return false;
        }
        Ext.getCmp("updateMarketPlanBtn").setText(i18n('i18n.coupon.saveMarketPlanBtn'));
    }
    if(bj==="0"){//查看详情
        Ext.getCmp("updateMarketPlanBtn").setText(i18n('i18n.coupon.editMarketPlanBtn'));
    }
    var successFn = function(json){
        //配合快递优惠券新增的代码by肖红叶
        if(!Ext.isEmpty(json.couponTypeVo)){
            //生成规则的产品类型
            var makeProductType = json.couponTypeVo.makeProductType;
            //使用规则的产品类型
            var useProductType = json.couponTypeVo.useProductType;
            if(!Ext.isEmpty(makeProductType) && 
                    Ext.Array.contains(makeProductType,'PACKAGE') && 
                    !isPermission('/coupon/selectExpressProductType.action')
                    ){
                if(!Ext.isEmpty(btnObj)){//解除按钮
                    btnObj.enable();
                }
                if(bj==="1"){
                    MessageUtil.showErrorMes(i18n('i18n.coupon.userHavenotPermission')
                            +i18n('i18n.coupon.edit')+
                            i18n('i18n.coupon.userHavenotPermissionToOperatateCoupon'));
                    return false;
                }
                else if(bj == "0"){
                    convertProductType(makeProductType);
                    if(!Ext.isEmpty(useProductType)){
                        convertProductType(useProductType);
                    }
                }
                
            }
            
            if(!Ext.isEmpty(useProductType) && 
                    Ext.Array.contains(useProductType,'PACKAGE') && 
                    !isPermission('/coupon/selectExpressProductType.action')
                    ){
                if(!Ext.isEmpty(btnObj)){//解除按钮
                    btnObj.enable();
                }
                if(bj==="1"){
                    MessageUtil.showErrorMes(i18n('i18n.coupon.userHavenotPermission')
                            +i18n('i18n.coupon.edit')+
                            i18n('i18n.coupon.userHavenotPermissionToOperatateCoupon'));
                    return false;
                }
                else if(bj == "0"){
                    convertProductType(useProductType);
                    if(!Ext.isEmpty(makeProductType)){
                        convertProductType(makeProductType);
                    }
                }
            }
            
            if(!Ext.isEmpty(makeProductType) && 
                    !Ext.Array.contains(makeProductType,'PACKAGE') && 
                    !isPermission('/coupon/selectLogisticsProductType.action')
                    ){
                if(!Ext.isEmpty(btnObj)){//解除按钮
                    btnObj.enable();
                }
                if(bj==="1"){
                    MessageUtil.showErrorMes(i18n('i18n.coupon.userHavenotPermission')
                            +i18n('i18n.coupon.edit')+
                            i18n('i18n.coupon.userHavenotPermissionToOperatateCoupon'));
                    return false;
                }
                else if(bj == "0"){
                    convertProductType(makeProductType);
                    if(!Ext.isEmpty(useProductType)){
                        convertProductType(useProductType);
                    }
                }
            }
            
            if(!Ext.isEmpty(useProductType) && 
                    !Ext.Array.contains(useProductType,'PACKAGE') && 
                    !isPermission('/coupon/selectLogisticsProductType.action')
                    ){
                if(!Ext.isEmpty(btnObj)){//解除按钮
                    btnObj.enable();
                }
                if(bj==="1"){
                    MessageUtil.showErrorMes(i18n('i18n.coupon.userHavenotPermission')
                            +i18n('i18n.coupon.edit')+
                            i18n('i18n.coupon.userHavenotPermissionToOperatateCoupon'));
                    return false;
                }
                else if(bj == "0"){
                    convertProductType(useProductType);
                    if(!Ext.isEmpty(makeProductType)){
                        convertProductType(makeProductType);
                    }
                }
            }
        }
        var marketplan=json.marketPlaneVO.marketplan;
        var marketStatus=null;
        if(!Ext.isEmpty(marketplan)){//判断营销计划是否为空
            if(!Ext.isEmpty(btnObj)){//解除按钮
                btnObj.enable();
            }
            marketStatus=marketplan.marketStatus;
            var marketplanFrom=Ext.getCmp("marketplanFrom").getForm();//营销计划
            //auth : 李春雨  添加俩字段的设置信息
            //判断活动id是否为空  不为空则显示活动信息下折扣信息
            couponInfo = marketplanFrom.findField('couponInfo');
            var activityRecord = null;
            if(Ext.isEmpty(marketplan.activityId)){
                canEdit = true;
                couponInfo.getStore().loadData(discountProducts);
                couponInfo.setValue(marketplan.discountType);
            }else{
                Ext.getCmp('activityNameId').getStore().load({
                    params : {
                        'marketActivityName':''
                    },
                    callback:function(){
                        activityRecord = Ext.getCmp('activityNameId').getStore().findRecord('id',marketplan.activityId,0,false,true,true);
                        //如果未找到此记录 并且为查看时则按照id查询   
                        if(Ext.isEmpty(activityRecord)){
                            canEdit = false;
                            var param = {
                                'id':marketplan.activityId
                            };
                            var successFn = function(json){
                                marketplanFrom.findField('createRule').setValue(json.marketActivity.createRule);
                                marketplanFrom.findField('useRule').setValue(json.marketActivity.useRule);
                                Ext.getCmp('activityNameId').setRawValue(json.marketActivity.activityName);
                                if(bj==="1"){
                                    canEditFunc();
                                }
                            };
                            var failureFn = function(json){
                                MessageUtil.showErrorMes(json.message);
                                return;
                            };
                            MarketPlanMangeStore.prototype.queryActivityAll(param,successFn,failureFn);
                        }else{
                            canEdit = true;
                            marketplanFrom.findField('createRule').setValue(activityRecord.get('createRule'));
                            marketplanFrom.findField('useRule').setValue(activityRecord.get('useRule'));
                        }
                    }
                });
                couponInfo.getStore().load({
                    params: {
                        'id' : marketplan.activityId
                    },
                    callback:function(){
                        couponInfo.setValue(marketplan.discountType);
                    }
                });
                markActivityId = marketplan.activityId;
            }
            marketplanFrom.findField('activityName').setValue(marketplan.activityId);
            marketplanFrom.loadRecord(new MarketPlanManageListModel(marketplan));
            var couponType=marketplan.couponType;
            if(couponType==="HANDCOUPON"){//手动
                Ext.getCmp("couponTabpanel").getComponent('ruleCouponHandTab').enable();
                Ext.getCmp("couponTabpanel").setActiveTab('ruleCouponHandTab');
                Ext.getCmp("couponTabpanel").getComponent('ruleCouponAutoTab').disable();
                if(!Ext.isEmpty(json.marketPlaneVO.ruleCouponHand)){//手动规则不为空
                    ruleCouponHandId=json.marketPlaneVO.ruleCouponHand.id;
                    var ruleCouponHandForm=Ext.getCmp("ruleCouponHandTab").getForm();//手动规则
                    ruleCouponHandForm.findField('couponValue').setValue(json.marketPlaneVO.ruleCouponHand.couponValue);// 优惠券金额
                    ruleCouponHandForm.findField('couponQuantity').setValue(json.marketPlaneVO.ruleCouponHand.couponQuantity);// 优惠券数量
                }
            }else{//自动
                Ext.getCmp("couponTabpanel").getComponent('ruleCouponAutoTab').enable();
                Ext.getCmp("couponTabpanel").setActiveTab('ruleCouponAutoTab');
                Ext.getCmp("couponTabpanel").getComponent('ruleCouponHandTab').disable();
                if(!Ext.isEmpty(json.marketPlaneVO.ruleCouponAuto)){//自动规则不为空
                    ruleCouponAutoId=json.marketPlaneVO.ruleCouponAuto.id;
                    var ruleCouponAutoForm=Ext.getCmp("ruleCouponAutoForm").getForm();//手动规则
                    ruleCouponAutoForm.findField('autoBeginTime').setValue(DpUtil.changeLongToDate(json.marketPlaneVO.ruleCouponAuto.autoBeginTime));// 返券期限开始时间
                    ruleCouponAutoForm.findField('autoEndTime').setValue(DpUtil.changeLongToDate(json.marketPlaneVO.ruleCouponAuto.autoEndTime));// 返券期限结束时间
                    if(!Ext.isEmpty(json.couponTypeVo.makeProductType)){
                        ruleCouponAutoForm.findField('makeProductType').setValue(json.couponTypeVo.makeProductType);    
                    }
                    if(!Ext.isEmpty(json.couponTypeVo.makeOrderSourceType)){
                        ruleCouponAutoForm.findField('makeOrderSourceType').setValue(json.couponTypeVo.makeOrderSourceType);    
                    }
                    if(!Ext.isEmpty(json.couponTypeVo.makeCustomerLevelType)){
                        ruleCouponAutoForm.findField('makeCustomerLevelType').setValue(json.couponTypeVo.makeCustomerLevelType);    
                    }
                    if(!Ext.isEmpty(json.couponTypeVo.makeCustomerTradeType)){
                        ruleCouponAutoForm.findField('makeCustomerTradeType').setValue(json.couponTypeVo.makeCustomerTradeType);    
                    }
                    var autoCouponCost=json.marketPlaneVO.ruleCouponAuto.autoCouponCost;
                    Ext.getCmp("autoCouponCostGrid").store.removeAll();
                    for(var i=0;i<autoCouponCost.length;i++){
                        if(i===0){//第一次的时候加载费用类型，只加载一次
                            Ext.getCmp("costType").setValue(autoCouponCost[i].costType);
                        }
                        var autoCouponCostModel=new AutoCouponCostModel();
                        autoCouponCostModel.set("costDown",autoCouponCost[i].costDown);
                        autoCouponCostModel.set("coupoCost",autoCouponCost[i].coupoCost);
                        autoCouponCostModel.set("couponAutoId",autoCouponCost[i].couponAutoId);
                        autoCouponCostModel.commit();
                        Ext.getCmp("autoCouponCostGrid").store.add(autoCouponCostModel);
                    }
                    var createGoodsLine=json.marketPlaneVO.ruleCouponAuto.createGoodsLine;
                    Ext.getCmp("makeWalkGoodsLineGrid").store.removeAll();
                    Ext.getCmp("makeCrossGoodsLineGrid").store.removeAll();
                    for(var i=0;i<createGoodsLine.length;i++){
                        if(i===0){//第一次的时候加载费用类型，只加载一次
                            upregdemand=createGoodsLine[i].regdemand;
                            Ext.getCmp("upruleType").setValue(upregdemand);
                        }
                        var goodsLineModel=new GoodsLineModel();
                        goodsLineModel.set("beginDeptOrCityId",createGoodsLine[i].beginDeptOrCityId);
                        goodsLineModel.set("beginDeptOrCityName",createGoodsLine[i].beginDeptOrCityName);
                        goodsLineModel.set("endDeptOrCityId",createGoodsLine[i].endDeptOrCityId);
                        goodsLineModel.set("endDeptOrCityName",createGoodsLine[i].endDeptOrCityName);
                        goodsLineModel.commit();
                        if(upregdemand==="2"){
                            Ext.getCmp("makeWalkGoodsLineGrid").store.add(goodsLineModel);
                        }else{
                            Ext.getCmp("makeCrossGoodsLineGrid").store.add(goodsLineModel);
                        }
                    }
                }
            }
            if(!Ext.isEmpty(json.marketPlaneVO.couponRule)){//使用条件
                couponRuleId=json.marketPlaneVO.couponRule.id;
                var couponRuleForm=Ext.getCmp("couponRuleFormPanel").getForm();//使用规则
                
                var costMode=null;// 分级抵扣模式
                var costType=null// 金额类型    默认为“1” 运费，“2”开单金额
                var value=null;//// 使用金额
                var discount=null;// 抵扣金额  选择分级抵扣模式 时 使用
                if(json.marketPlaneVO.couponRule.costMode==="1"){//默认选择分级抵扣模式
                    Ext.getCmp("upcostMode").setValue(true);
                    Ext.getCmp("upcostType").setValue(json.marketPlaneVO.couponRule.costType);
                    Ext.getCmp("upvalue").setValue(json.marketPlaneVO.couponRule.value);
                }else{
                    Ext.getCmp("downcostMode").setValue(true);
                    Ext.getCmp("downvalue").setValue(json.marketPlaneVO.couponRule.value);
                    Ext.getCmp("downcostType").setValue(json.marketPlaneVO.couponRule.costType);
                    couponRuleForm.findField('discount').setValue(json.marketPlaneVO.couponRule.discount);
                }
                if(json.marketPlaneVO.couponRule.costAddedMode==="1"){// 增值费要求  默认为“0”没选中，“1”选中
                    couponRuleForm.findField('costAddedMode').setValue(true);
                    couponRuleForm.findField('costAddedType').setValue(json.marketPlaneVO.couponRule.costAddedType);
                    couponRuleForm.findField('costAdded').setValue(json.marketPlaneVO.couponRule.costAdded);
                }
                couponRuleForm.findField('begintime').setValue(DpUtil.changeLongToDate(json.marketPlaneVO.couponRule.begintime));
                couponRuleForm.findField('endtime').setValue(DpUtil.changeLongToDate(json.marketPlaneVO.couponRule.endtime));
                
                if(!Ext.isEmpty(json.couponTypeVo.useProductType)){
                    couponRuleForm.findField('useProductType').setValue(json.couponTypeVo.useProductType);  
                }
                if(!Ext.isEmpty(json.couponTypeVo.useOrderSourceType)){
                    couponRuleForm.findField('useOrderSourceType').setValue(json.couponTypeVo.useOrderSourceType);  
                }
                if(!Ext.isEmpty(json.couponTypeVo.useCustomerLevelType)){
                    couponRuleForm.findField('useCustomerLevelType').setValue(json.couponTypeVo.useCustomerLevelType);  
                }
                if(!Ext.isEmpty(json.couponTypeVo.useCustomerTradeType)){
                    couponRuleForm.findField('useCustomerTradeType').setValue(json.couponTypeVo.useCustomerTradeType);  
                }
                var goodsLines=json.marketPlaneVO.couponRule.goodsLines;
                Ext.getCmp("useWalkGoodsLineGrid").store.removeAll();
                Ext.getCmp("useCrossGoodsLineGrid").store.removeAll();
                for(var i=0;i<goodsLines.length;i++){
                    if(i===0){//第一次的时候加载费用类型，只加载一次
                        downregdemand=goodsLines[i].regdemand;
                        Ext.getCmp("downruleType").setValue(downregdemand);
                    }
                    var goodsLineModel=new GoodsLineModel();
                    goodsLineModel.set("beginDeptOrCityId",goodsLines[i].beginDeptOrCityId);
                    goodsLineModel.set("beginDeptOrCityName",goodsLines[i].beginDeptOrCityName);
                    goodsLineModel.set("endDeptOrCityId",goodsLines[i].endDeptOrCityId);
                    goodsLineModel.set("endDeptOrCityName",goodsLines[i].endDeptOrCityName);
                    goodsLineModel.commit();
                    if(downregdemand===GOOD_LINE_AREA){//走货
                        Ext.getCmp("useWalkGoodsLineGrid").store.add(goodsLineModel);
                    }else{
                        Ext.getCmp("useCrossGoodsLineGrid").store.add(goodsLineModel);
                    }
                }
                
                //线路区域要求 ......
                var messageForm=Ext.getCmp("messageForm").getForm();//短信和描述
                var smsContent=messageForm.findField('smsContent').setValue(json.marketPlaneVO.couponRule.smsContent);
                var describe=messageForm.findField('describe').setValue(json.marketPlaneVO.couponRule.describe);
            }
            //bj 是修改还是详情、upregdemand 区域线路要求（自动优惠券） downregdemand 区域线路要求（使用）
            detailOrEdit(bj,upregdemand,downregdemand);
            //查看详情window
            Ext.getCmp("detailCouponPopWindowId").show();
            if(!Ext.isEmpty(upregdemand)){//判断该显示哪个线路区域要求
                setActiveGoodLine("up",upregdemand,"update")
            }
            if(!Ext.isEmpty(downregdemand)){//判断该显示哪个线路区域要求
                setActiveGoodLine("down",downregdemand,"update")
            }
            
            if(!Ext.isEmpty(marketStatus)){
                if(marketStatus===MARKETPLAN_NOUSE){//未启用
                    Ext.getCmp("updateMarketPlanBtn").show();//编辑
                    Ext.getCmp("deleteMarketPlanBtn").show();//删除
                    Ext.getCmp("copyNewBtn").show();//复制新建
                    if(bj==="1"){//修改的时候启用可用
                        Ext.getCmp("startMarketPlanBtn").show();//启用
                    }else{
                        Ext.getCmp("startMarketPlanBtn").hide();//启用    
                    }
                    Ext.getCmp("stopMarketPlanBtn").hide();//终止
                    Ext.getCmp("saveStartMarketPlanBtn").hide();//启用（保存）终止
                    Ext.getCmp("saveMarketPlanBtn").hide();//保存
                }else if(marketStatus===MARKETPLAN_USING){//已启用
                    Ext.getCmp("stopMarketPlanBtn").show();//终止
                    Ext.getCmp("copyNewBtn").show();//复制新建
                    Ext.getCmp("startMarketPlanBtn").hide();//启用
                    Ext.getCmp("updateMarketPlanBtn").hide();//编辑
                    Ext.getCmp("deleteMarketPlanBtn").hide();//删除
                    Ext.getCmp("saveStartMarketPlanBtn").hide();//启用（保存）终止
                    Ext.getCmp("saveMarketPlanBtn").hide();//保存
                }else if(marketStatus===MARKETPLAN_STOP){//已终止
                    Ext.getCmp("copyNewBtn").show();//复制新建
                    Ext.getCmp("startMarketPlanBtn").hide();//启用
                    Ext.getCmp("updateMarketPlanBtn").hide();//编辑
                    Ext.getCmp("deleteMarketPlanBtn").hide();//删除
                    Ext.getCmp("stopMarketPlanBtn").hide();//终止
                    Ext.getCmp("saveStartMarketPlanBtn").hide();//启用（保存）终止
                    Ext.getCmp("saveMarketPlanBtn").hide();//保存
                }else if(marketStatus===MARKETPLAN_FINISH){//已结束
                    Ext.getCmp("copyNewBtn").show();//复制新建
                    Ext.getCmp("startMarketPlanBtn").hide();//启用
                    Ext.getCmp("updateMarketPlanBtn").hide();//编辑
                    Ext.getCmp("deleteMarketPlanBtn").hide();//删除
                    Ext.getCmp("stopMarketPlanBtn").hide();//终止
                    Ext.getCmp("saveStartMarketPlanBtn").hide();//启用（保存）终止
                    Ext.getCmp("saveMarketPlanBtn").hide();//保存
                }   
            }
        }else{
            if(!Ext.isEmpty(btnObj)){//解除按钮
                btnObj.enable();
            }
            MessageUtil.showMessage(i18n('i18n.coupon.marketIsNull'));
        }
    };
    var failureFn = function(json){
        if(!Ext.isEmpty(btnObj)){
            btnObj.enable();
        }
        MessageUtil.showErrorMes(json.message);
    };
    var param={
        'marketPlanId':records[0].get("id")
    };
    CouponAddStore.prototype.searchMarketDetail(param, successFn, failureFn);
};


function detailOrEdit(bj,upregdemand,downregdemand){//
    var marketplanFrom=Ext.getCmp("marketplanFrom").getForm();//营销计划
    var couponRuleForm=Ext.getCmp("couponRuleFormPanel").getForm();//使用规则
    var messageForm=Ext.getCmp("messageForm").getForm();//短信和描述
    
    if(bj==="1"){//修改
        marketplanFrom.findField("planName").enable();
        //修改时启用控件  auth李春雨
        marketplanFrom.findField("activityName").enable();
        marketplanFrom.findField("couponInfo").enable();
        if(Ext.getCmp("couponType").getValue()===COUPON_SENDHAND){//手动自动
            var ruleCouponHandForm=Ext.getCmp("ruleCouponHandTab").getForm();//手动规则
            ruleCouponHandForm.findField("couponValue").enable();
            ruleCouponHandForm.findField("couponQuantity").enable();
        }else{//自动
            var ruleCouponAutoForm=Ext.getCmp("ruleCouponAutoForm").getForm();//手动规则
            ruleCouponAutoForm.findField("autoBeginTime").enable();
            ruleCouponAutoForm.findField("autoEndTime").enable();
            ruleCouponAutoForm.findField("makeProductType").enable();
            ruleCouponAutoForm.findField("makeOrderSourceType").enable();
            ruleCouponAutoForm.findField("makeCustomerLevelType").enable();
            ruleCouponAutoForm.findField("makeCustomerTradeType").enable();
            Ext.getCmp("costType").enable();
            Ext.getCmp("costAddBtn").show();
            Ext.getCmp("costDelBtn").show();
            Ext.getCmp("upruleType").enable();
            //修改都显示增删button，在查看的时候隐藏了的
            Ext.getCmp("makeCrossGoodsLineAddBtn").show();
            Ext.getCmp("makeCrossGoodsLineDelBtn").show();
            Ext.getCmp("makeWalkGoodsLineAddBtn").show();
            Ext.getCmp("makeWalkGoodsLineDelBtn").show();
        }
        if(Ext.getCmp("upcostMode").getValue()){
            Ext.getCmp("upcostType").enable();
            Ext.getCmp('upvalue').enable(); 
        }else{
            Ext.getCmp('downcostType').enable();
            couponRuleForm.findField('downvalue').enable(); 
            couponRuleForm.findField('discount').enable();  
        }
        Ext.getCmp("upcostMode").enable();
        Ext.getCmp("downcostMode").enable();
        Ext.getCmp("upBeginDeptOrCityCombox").enable();
        Ext.getCmp("upEndDeptOrCityCombox").enable();
        Ext.getCmp("downBeginDeptOrCityCombox").enable();
        Ext.getCmp("downEndDeptOrCityCombox").enable();
        Ext.getCmp("makeGoodsLineDept").enable();
        Ext.getCmp("useGoodsLineDept").enable();
        couponRuleForm.findField('costAddedMode').enable();
        if(couponRuleForm.findField('costAddedMode').getValue()){
            couponRuleForm.findField("costAddedType").enable();//增值费类型
            couponRuleForm.findField("costAdded").enable();//增值费金
        }
        couponRuleForm.findField('useProductType').enable();    
        couponRuleForm.findField('useOrderSourceType').enable();    
        couponRuleForm.findField('useCustomerLevelType').enable();  
        couponRuleForm.findField('useCustomerTradeType').enable();  
        couponRuleForm.findField('begintime').enable(); 
        couponRuleForm.findField('endtime').enable();
        Ext.getCmp("copybtn").show();
        Ext.getCmp("downruleType").enable();
        //修改都显示增删button，在查看的时候隐藏了的
        Ext.getCmp("useCrossGoodsLineAddBtn").show();
        Ext.getCmp("useCrossGoodsLineDelBtn").show();
        Ext.getCmp("useWalkGoodsLineAddBtn").show();
        Ext.getCmp("useWalkGoodsLineDelBtn").show();
        messageForm.findField("smsContent").enable();
        messageForm.findField("describe").enable();
    }else{
        marketplanFrom.findField("planName").disable();
        //查看详情时禁用空间  auth李春雨
        marketplanFrom.findField("activityName").disable();
        marketplanFrom.findField("couponInfo").disable();
        if(Ext.getCmp("couponType").getValue()===COUPON_SENDHAND){//手动自动
            var ruleCouponHandForm=Ext.getCmp("ruleCouponHandTab").getForm();//手动规则
            ruleCouponHandForm.findField("couponValue").disable();
            ruleCouponHandForm.findField("couponQuantity").disable();
        }else{//自动
            var ruleCouponAutoForm=Ext.getCmp("ruleCouponAutoForm").getForm();//手动规则
            ruleCouponAutoForm.findField("autoBeginTime").clearInvalid();
            ruleCouponAutoForm.findField("autoEndTime").clearInvalid();
            ruleCouponAutoForm.findField("autoBeginTime").disable();
            ruleCouponAutoForm.findField("autoEndTime").disable();
            ruleCouponAutoForm.findField("makeProductType").disable();
            ruleCouponAutoForm.findField("makeOrderSourceType").disable();
            ruleCouponAutoForm.findField("makeCustomerLevelType").disable();
            ruleCouponAutoForm.findField("makeCustomerTradeType").disable();
            Ext.getCmp("costType").disable();
            Ext.getCmp("costAddBtn").hide();
            Ext.getCmp("costDelBtn").hide();
            Ext.getCmp("upruleType").disable();
            //查看都隐藏增删button，在修改的时候再显示
            Ext.getCmp("makeCrossGoodsLineAddBtn").hide();
            Ext.getCmp("makeCrossGoodsLineDelBtn").hide();
            Ext.getCmp("makeWalkGoodsLineAddBtn").hide();
            Ext.getCmp("makeWalkGoodsLineDelBtn").hide();
        }
        Ext.getCmp("downruleType").disable();
        couponRuleForm.findField('upcostType').disable();
        Ext.getCmp('upvalue').disable();    
        Ext.getCmp("upcostMode").disable();
        Ext.getCmp("downcostMode").disable();
        Ext.getCmp("upBeginDeptOrCityCombox").disable();
        Ext.getCmp("upEndDeptOrCityCombox").disable();
        Ext.getCmp("downBeginDeptOrCityCombox").disable();
        Ext.getCmp("downEndDeptOrCityCombox").disable();
        Ext.getCmp("makeGoodsLineDept").disable();
        Ext.getCmp("useGoodsLineDept").disable();
        Ext.getCmp('downcostType').disable();   
        couponRuleForm.findField('downvalue').disable();    
        couponRuleForm.findField('discount').disable();
        couponRuleForm.findField('costAddedMode').disable();
        couponRuleForm.findField("costAddedType").disable();//增值费类型
        couponRuleForm.findField("costAdded").disable();//增值费金
        couponRuleForm.findField('useProductType').disable();   
        couponRuleForm.findField('useOrderSourceType').disable();   
        couponRuleForm.findField('useCustomerLevelType').disable(); 
        couponRuleForm.findField('useCustomerTradeType').disable(); 
        couponRuleForm.findField("begintime").clearInvalid();
        couponRuleForm.findField("endtime").clearInvalid();
        couponRuleForm.findField('begintime').disable();    
        couponRuleForm.findField('endtime').disable();
        Ext.getCmp("copybtn").hide();
        //查看都隐藏增删button，在修改的时候再显示
        Ext.getCmp("useCrossGoodsLineAddBtn").hide();
        Ext.getCmp("useCrossGoodsLineDelBtn").hide();
        Ext.getCmp("useWalkGoodsLineAddBtn").hide();
        Ext.getCmp("useWalkGoodsLineDelBtn").hide();
        messageForm.findField("smsContent").disable();
        messageForm.findField("describe").disable();
    }
    
}
//配合快递优惠券新增的代码，判断在营销计划查询页面，登陆的用户是否有权限复制新建、保存、终止该营销计划
var isNotPermissionToOperateCoupon = false;
function isPermissionToOperateCoupon(errorText){
    var makeProductType = null;
    var useProductType = null;
    var ruleCouponAutoForm=Ext.getCmp("ruleCouponAutoForm").getForm();//自动
    var couponRuleForm=Ext.getCmp("couponRuleFormPanel").getForm();//使用规则
    makeProductType=ruleCouponAutoForm.findField('makeProductType').getValue();
    useProductType=couponRuleForm.findField('useProductType').getValue();
    
    if(!Ext.isEmpty(makeProductType) && 
            (Ext.Array.contains(makeProductType,'PACKAGE') ||
                    Ext.Array.contains(makeProductType,'经济快递')) && 
            !isPermission('/coupon/selectExpressProductType.action')
            ){
        MessageUtil.showErrorMes(i18n('i18n.coupon.userHavenotPermission')
                +errorText+i18n('i18n.coupon.userHavenotPermissionToOperatateCoupon'));
        isNotPermissionToOperateCoupon = true;
    }
    
    if(!Ext.isEmpty(useProductType) && 
            (Ext.Array.contains(useProductType,'PACKAGE') ||
                    Ext.Array.contains(useProductType,'经济快递')) && 
            !isPermission('/coupon/selectExpressProductType.action')
            ){
        MessageUtil.showErrorMes(i18n('i18n.coupon.userHavenotPermission')
                +errorText+i18n('i18n.coupon.userHavenotPermissionToOperatateCoupon'));
        isNotPermissionToOperateCoupon = true;
    }
    if(!Ext.isEmpty(makeProductType) && 
            !(Ext.Array.contains(makeProductType,'PACKAGE') ||
                    Ext.Array.contains(makeProductType,'经济快递')) && 
            !isPermission('/coupon/selectLogisticsProductType.action')
            ){
        MessageUtil.showErrorMes(i18n('i18n.coupon.userHavenotPermission')
                +errorText+i18n('i18n.coupon.userHavenotPermissionToOperatateCoupon'));
        isNotPermissionToOperateCoupon = true;
    }
    
    if(!Ext.isEmpty(useProductType) && 
            !(Ext.Array.contains(useProductType,'PACKAGE') ||
                    Ext.Array.contains(useProductType,'经济快递')) && 
            !isPermission('/coupon/selectLogisticsProductType.action')
            ){
        MessageUtil.showErrorMes(i18n('i18n.coupon.userHavenotPermission')
                +errorText+i18n('i18n.coupon.userHavenotPermissionToOperatateCoupon'));
        isNotPermissionToOperateCoupon = true;
    }
}

//产品类型数组转化，用以零担优惠券客户和快递优惠券客户相互查看彼此的优惠券
//var newProductType = new Array();
function convertProductType(productTypeArray){
    var length = productTypeArray.length;
    for(var i = 0;i < length; i++){
        if(productTypeArray[i] == 'FLF'){
            productTypeArray[i] = '精准卡航';
        }
        else if(productTypeArray[i] == 'FSF'){
            productTypeArray[i] = '精准城运';
        }
        else if(productTypeArray[i] == 'LRF'){
            productTypeArray[i] = '精准汽运(长途)';
        }
        else if(productTypeArray[i] == 'SRF'){
            productTypeArray[i] = '精准汽运(短途)';
        }
        else if(productTypeArray[i] == 'PLF'){
            productTypeArray[i] = '汽运偏线';
        }
        else if(productTypeArray[i] == 'FV'){
            productTypeArray[i] = '整车';
        }
        else if(productTypeArray[i] == 'AF'){
            productTypeArray[i] = '精准空运';
        }
        else if(productTypeArray[i] == 'PACKAGE'){
            productTypeArray[i] = '经济快递';
        }
        else{
            
        }
    }
}

//auth：李春雨  点击编辑时 判断该市场推广活动是否可用   不可用则清空数据
function canEditFunc(){
    if(!canEdit){
        Ext.getCmp('activityNameId').reset();
        Ext.getCmp("marketplanFrom").getForm().findField('createRule').reset();
        Ext.getCmp("marketplanFrom").getForm().findField('useRule').reset();
        Ext.getCmp("marketplanFrom").getForm().findField('couponInfo').getStore().loadData(discountProducts);
        Ext.getCmp("marketplanFrom").getForm().findField('couponInfo').reset();
    }
}
