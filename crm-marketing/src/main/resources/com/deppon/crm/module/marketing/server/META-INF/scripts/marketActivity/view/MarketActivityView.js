var viewport = null;
//action 对象
var activityRecordData = new ActivityRecordData();
//活动的id 初始化时获取，在创建时再传回到后台
var marketActivityId = null;
//用来判断是增加(add)、修改(update)、查看(look)
var operatorForMarket = null;
//客户群的实体变量
var clientBase = null;
//树结点数据
var treeData = null;
//线路树数据
var treeLineData = null;
//蒙层
var myMask = null;
var myTreeMask = null;
//当前用户部门负责人
var Principal = {};
//部门性质
var DeptCharacter = {};
var canChange = true;
//必填字段提示红色*
var required='<span style="color:red;font-weight:bold">*</span>';
//重写时间空间 默认为当前时间的23点59分59秒
Ext.override(Ext.form.field.DateTime, {
    onExpand: function() {
        var me = this,
            value = me.getValue();
        me.picker.setValue(value instanceof Date ? value : new Date());
        if (me.setDefaultTime && Ext.isEmpty(value)){
            me.defaultTime = [23, 59, 59];
        }else if (me.setDefaultTime){
            me.defaultTime = [value.getHours(), value.getMinutes(), value.getSeconds()];
        };
        // 设置时间 by zpj
        me.setTime();
    }
})

//定义活动赠改查的主体弹出window
Ext.define('MarketActivityWindow',{
    extend : 'PopWindow',
    closeAction : 'hide',
    id : 'marketActivityWindowId',
    width : 820,
    height : 720,
    layout: 'border',
    getItems : function(){
        var me = this;
        return [{
            //上面的formpanel
            region: 'north',
            items : [Ext.create('ActivityFormPanel')]
        },{
            //5个标签页
            region: 'center',
            xtype: 'normaltabpanel', 
            activeTab: 0,      
            items: [{
                title: i18n('i18n.MarketActivityManagerView.materialInfo'),//物料申请信息
                xtype : 'tabcontentpanel',
                margin : '5 5 5 5',
                items : [
                    Ext.create('MaterialGridPanel')
                ]
            },{
                title: required+i18n('i18n.MarketActivityManagerView.joinCondition'),//活动参与条件
                layout : 'border',
                xtype : 'tabcontentpanel',
                name : 'jionCondition',
                items : [{
                    region: 'north',
                    items : [Ext.create('ActivityJoinConditionPanel')]
                },{
                    region: 'center',
                    layout:{
                        type:'hbox',
                        align:'stretch'
                    },
                    items : [Ext.create('DeptTreeForActivity'),Ext.create('DeptGridPanel')]
                }],
                listeners : {
                    activate : function(tab){
                        if(operatorForMarket == 'add'){
                            Ext.getCmp('deptTreeForActivityId').show();
                        }else{
                            Ext.getCmp('deptTreeForActivityId').hide();
                        }
                        if(treeData==null && operatorForMarket == 'add'){
                            loadTreeDate();
                        }
                    }
                }
            },{
                title: required+i18n('i18n.MarketActivityManagerView.discountInfo'),//i18n('i18n.MarketActivityManagerView.discountInfo')
                xtype : 'tabcontentpanel',
                layout : 'border',
                items : [{
                    region: 'center',
                    layout:{
                        type:'hbox',
                        align:'stretch'
                    },
                    items : [Ext.create('PreferentialInformationPanel'),Ext.create('CouponGridPanel')]
                },{
                    region: 'south',
                    items : [Ext.create('UseConditionFormPanel')]
                }]
            },{
                title: required+i18n('i18n.MarketActivityManagerView.filterClient'),//筛选客户群
                xtype : 'tabcontentpanel',
                items : [Ext.create('ClientBasePanel')]
            },{
                title: i18n('i18n.MarketActivityManagerView.files'),//i18n('i18n.MarketActivityManagerView.files')
                xtype : 'tabcontentpanel',
                items : [Ext.create('FileFormPanel'),Ext.create('AccessoryPanel')]
            }],
            listeners : {
                tabchange : function(tab,newCard,oldCard){
                    //设置部门grid的高度 解决滚动条问题
                    if(newCard.name == 'jionCondition'){
                        Ext.getCmp('deptGridPanelId').setHeight(290);
                    }
                    if(newCard.name != 'jionCondition' && !Ext.isEmpty(myMask)){
                        myMask.hide();
                    }
                    if(newCard.name == 'jionCondition' && !Ext.isEmpty(myMask)){
                        myMask.show();
                    }
                }
            }
        }];
    },
    getButtons : function(){
        var me = this;
        return [{
            text : i18n('i18n.MarketActivityManagerView.save'),//保存
            name : 'savebutton',
            handler : function(){
                var me = this;
                //如果是新增的操作
                if(operatorForMarket == 'add'){
                    if(!Ext.getCmp('deptTreeForActivityId').view.isDisabled()){
                        //1.获取所有form验证所填入值是否合法
                        var activityForm = Ext.getCmp('activityFormPanelId').getForm();
                        var useConditionForm = Ext.getCmp('useConditionFormPanelId').getForm();
                        var activityJoinConditionForm = Ext.getCmp('activityJoinConditionPanelId').getForm();
                        activityForm.findField('contactsName').setRawValue(Ext.String.trim(activityForm.findField('contactsName').getRawValue()));
                        //form下空间去空格
                        eachTextFieldTrim(activityForm);
                        eachTextFieldTrim(useConditionForm);
                        eachTextFieldTrim(activityJoinConditionForm);
                        //验证是否合法
                        if(!activityForm.isValid()){
                            return false;
                        };
                        if(!useConditionForm.isValid()){
                            Ext.getCmp('marketActivityWindowId').down('tabpanel').setActiveTab(2);
                            return false;
                        };
                        if(!activityJoinConditionForm.isValid()){
                            Ext.getCmp('marketActivityWindowId').down('tabpanel').setActiveTab(1);
                            return false;
                        };
                        //验证时限间隔是否超两年
                        if(DButil.compareTwoDate(activityForm.findField('startTime').getValue(),activityForm.findField('endTime').getValue())/365 > 2){
                            MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.towYears'));//活动时限最多两年！
                            return;
                        }
                        //是否所有填入值符合规则
                        //部门信息不允许为空
                        if( Ext.getCmp('deptGridPanelId').getStore().getCount()< 1 ){
                            MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.beforeAdd'));//请先添加部门数据!
                            Ext.getCmp('marketActivityWindowId').down('tabpanel').setActiveTab(1);
                            return;
                        }
                        var deptCodes = new Array();
                            Ext.getCmp('deptGridPanelId').getStore().each(function(record){
                            deptCodes.push(record.get('deptStandardCode'));
                        });
                        //2.1 开始时间不能大于结束时间
                        var startTime = activityForm.getValues().startTime;
                        var endTime = activityForm.getValues().endTime;
                        if(startTime>endTime){
                            MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.startMoreThanEnd'));//开始时间不能大于结束时间
                            return;
                        }
                        var marketActivityModel = new ActivityMainModel();
                        //更新form中数据到实体中
                        activityForm.updateRecord(marketActivityModel);
                        useConditionForm.updateRecord(marketActivityModel);
                        //开单金额、货物重量、货物体积不可只有一个值，并且前值不能大于后值
                        var minBillAmt = activityJoinConditionForm.findField('minBillAmt').getValue();
                        var maxBillAmt = activityJoinConditionForm.findField('maxBillAmt').getValue();
                        var minCargoWeight = activityJoinConditionForm.findField('minCargoWeight').getValue();
                        var maxCargoWeight = activityJoinConditionForm.findField('maxCargoWeight').getValue();
                        var minCargoVolume = activityJoinConditionForm.findField('minCargoVolume').getValue();
                        var maxCargoVolume = activityJoinConditionForm.findField('maxCargoVolume').getValue();
                        //值不可只有一个为空
                        if(Ext.isEmpty(minBillAmt)&&!Ext.isEmpty(maxBillAmt)||Ext.isEmpty(maxBillAmt)&&!Ext.isEmpty(minBillAmt)){
                            MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.billAmtNotIntact'));//开单金额请填写完整!
                            return;
                        }
                        if(!Ext.isEmpty(maxBillAmt)&&!Ext.isEmpty(minBillAmt)&&minBillAmt>maxBillAmt){
                            MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.billAmtStartMoreEnd'));//开单金额开始值不能大于结束值!
                            return
                        }
                        if(Ext.isEmpty(minCargoWeight)&&!Ext.isEmpty(maxCargoWeight)||Ext.isEmpty(maxCargoWeight)&&!Ext.isEmpty(minCargoWeight)){
                            MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.cargoWeightNotIntact'));//货物重量请填写完整!
                            return;
                        }
                        if(!Ext.isEmpty(minCargoWeight)&&!Ext.isEmpty(maxCargoWeight)&&minCargoWeight>maxCargoWeight){
                            MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.cargoWeightStartMoreEnd'));//货物重量开始值不能大于结束值!
                            return
                        }
                        if(Ext.isEmpty(minCargoVolume)&&!Ext.isEmpty(maxCargoVolume)||Ext.isEmpty(maxCargoVolume)&&!Ext.isEmpty(minCargoVolume)){
                            MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.cargoVolumeNotIntact'));//货物体积请填写完整!
                            return;
                        }
                        if(!Ext.isEmpty(minCargoVolume)&&!Ext.isEmpty(maxCargoVolume)&&minCargoVolume>maxCargoVolume){
                            MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.cargoVolumeStartMoreEnd'));//货物体积开始值不能大于结束值!
                            return
                        }
                        activityJoinConditionForm.updateRecord(marketActivityModel);
                        marketActivityModel.set('id',marketActivityId);
                        //物料
                        var materialInfo = new Array();
                        //折扣
                        var discountInfo = new Array();
                        //优惠券
                        var couponInfo = new Array();
                        //客户群
                        var clientInfo = new Array();
                        //如果选择折扣则设置折扣 否者是优惠券
                        var preferentialInformation = Ext.getCmp('preferentialInformationPanelId');
                        if(preferentialInformation.down('combo[name=preferType]').getValue() == 'DISCOUNT'){
                            //如果是区域并为折扣 则工作流号必填
                            if(activityForm.findField('activityType').getValue() != 'NATIONWIDE'){
                                var workFlowNum = preferentialInformation.down('textfield[name=workFlowNum]').getValue();
                                if(Ext.isEmpty(workFlowNum)){
                                    MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.writeWorkFlowNum'));//请填写工作流号！
                                    return;
                                }
                                marketActivityModel.set('workFlowNum',workFlowNum);
                            }
                            //请添加至少一条折扣信息！
                            if(preferentialInformation.getStore().count() < 1 ){
                                MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.discountInfoLessOne'));//请添加至少一条折扣信息！
                                Ext.getCmp('marketActivityWindowId').down('tabpanel').setActiveTab(2);
                                return ;
                            }
                            preferentialInformation.getStore().each(function(record){
                               discountInfo.push(record.data);
                            });
                            marketActivityModel.set('preferType','DISCOUNT');
                        }else{
                            //请添加至少一条优惠券信息！
                            if(Ext.getCmp('couponGridPanelId').getStore().count() < 1 ){
                                MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.couponInfoLessOne'));//请添加至少一条优惠券信息！
                                Ext.getCmp('marketActivityWindowId').down('tabpanel').setActiveTab(2);
                                return ;
                            }
                            Ext.getCmp('couponGridPanelId').getStore().each(function(record){
                                couponInfo.push(record.data);
                            });
                            marketActivityModel.set('preferType','COUPON');
                        };
                        //判断客户群列表是否为空
                        var clientBasePanel= Ext.getCmp('clientBasePanelId');
                        if(clientBasePanel.getStore().count() < 1){
                            MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.clientBaseIsNull'));//客户群列表不能为空！
                            Ext.getCmp('marketActivityWindowId').down('tabpanel').setActiveTab(3);
                            return ;
                        }
                        clientBasePanel.getStore().each(function(record){
                            clientInfo.push(record.data);
                        });
                        //附件不允许为空
//                        if(Ext.getCmp('accessoryPanelId').getStore().getCount()<= 0 ){
//                            MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.accessoryCantNull'));//请添加至少一条优惠券信息！
//                            Ext.getCmp('marketActivityWindowId').down('tabpanel').setActiveTab(4);
//                            return;
//                        }
                        Ext.getCmp('materialGridPanelId').getStore().each(function(record){
                            materialInfo.push(record.data);
                        });
                        marketActivityModel.set('materialInfo',materialInfo);
                        marketActivityModel.set('discountInfo',discountInfo);
                        marketActivityModel.set('couponInfo',couponInfo);
                        marketActivityModel.set('clientBase',clientInfo);
                        var lineRequest = null;
                        var lineGrid = null;
                        if(!Ext.isEmpty(Ext.getCmp('lineWindowId'))){
                            //保存线路信息
                            lineRequest = Ext.getCmp('lineGridRequireId').getValue();
                            lineGrid = new Array();
                            if(lineRequest != 'LEAVE_ARRIVE'){
                                Ext.getCmp('lineTreeGridPanelId').getStore().each(function(record){
                                    lineGrid.push(record.data);
                                });
                            }else{
                                 Ext.getCmp('lineGridPanelId').getStore().each(function(record){
                                     lineGrid.push(record.data);
                                 });
                            }
                        }
                        var param = { 
                            'deptCodes' : deptCodes,
                            'id' : marketActivityId,
                            'marketActivity':marketActivityModel.data,
                            'lineRequest' : lineRequest,
                            'lineDepts' : lineGrid
                        };
                        
                        var successFn= function(json){
                            myMask.destroy();
                            myMask = null;
                            if(!Ext.isEmpty(json.workFlowNum)){
                                MessageUtil.showInfoMes(i18n('i18n.MarketActivityManagerView.saveSuccess')+'<br>您的工作流号码为：'+ json.workFlowNum);//保存成功
                            }else{
                                MessageUtil.showInfoMes(i18n('i18n.MarketActivityManagerView.saveSuccess'));//保存成功
                            }
                            //重新加载查询数据
                            Ext.getCmp('marketActivityGridPanelId').getStore().loadPage(1);
                            Ext.getCmp('marketActivityWindowId').close();
                        }
                        
                        var failureFn= function(json){
                            myMask.destroy();
                            myMask = null;
                            MessageUtil.showErrorMes(json.message);
                            return;
                        }
                        myMask = new Ext.LoadMask(Ext.getBody(), {msg:'保存中请稍后。。。'});//保存中请稍后。。。
                        myMask.show();
                        me.disable();
                        setTimeout("Ext.getCmp('marketActivityWindowId').down('button[name=savebutton]').enable()",3000);
                        activityRecordData.saveMarketActivityAndDepts(param,successFn,failureFn);
                    }
              }
              //如果是修改 则只允许修改客户群信息
              if(operatorForMarket == 'update'){
                //客户群
                var clientInfo = new Array();
                var clientBasePanel= Ext.getCmp('clientBasePanelId');
                //判断客户群列表是否为空
                if(clientBasePanel.getStore().count() < 1){
                    MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.clientBaseIsNull'));//客户群列表不能为空！
                    return ;
                }
                clientBasePanel.getStore().each(function(record){
                    clientInfo.push(record.data);
                });
                var param = { 
                    'activityClientBaseList' : clientInfo,
                    'id' : marketActivityId
                };
                var successFn= function(json){
                    me.enable();
                    MessageUtil.showInfoMes(i18n('i18n.MarketActivityManagerView.updateSuccess'));//修改成功
                    //修改完刷新此页面
                    Ext.getCmp('marketActivityGridPanelId').getStore().loadPage(Ext.getCmp('marketActivityGridPanelId').down('toolbar').getPageData().currentPage);
                    Ext.getCmp('marketActivityWindowId').close();
                };
                var failureFn= function(json){
                    me.enable();
                    MessageUtil.showErrorMes(json.message);
                    return;
                }
                
                //修改客户群请求
                activityRecordData.updateMarketActivityClient(param,successFn,failureFn);
              }
            }
        }];
    },
    listeners:{
        show : function(){
            Ext.getCmp('marketActivityWindowId').down('tabpanel').setActiveTab(0);
            Ext.getCmp('activityFormPanelId').getForm().clearInvalid();
            Ext.getCmp('useConditionFormPanelId').getForm().clearInvalid();
            Ext.getCmp('useConditionFormPanelId').getForm().clearInvalid();
        },
        hide:function(){
            document.body.scrollLeft=0;
            document.body.scrollTop=0;
            document.getElementsByTagName("html")[0].style.overflowY="hidden";
            document.getElementsByTagName("html")[0].style.overflowX="hidden";
            viewport.doLayout();
            if(!Ext.isEmpty(myMask)){
                myMask.hide();
            }
            Ext.getCmp('activityFormPanelId').getForm().reset();
            Ext.getCmp('useConditionFormPanelId').getForm().reset();
            Ext.getCmp('activityJoinConditionPanelId').getForm().reset();
            Ext.getCmp('preferentialInformationPanelId').getStore().removeAll();
            Ext.getCmp('couponGridPanelId').getStore().removeAll();
            Ext.getCmp('materialGridPanelId').getStore().removeAll();
            Ext.QuickTips.destroy();  
            //清空部门store
            Ext.getCmp('deptGridPanelId').getStore().removeAll();
            changeTitle(Ext.getCmp('deptGridPanelId'),0,i18n('i18n.MarketActivityManagerView.deptName'));
            //判断窗口是否创建
            var lineWindow = Ext.getCmp('lineWindowId');
            if(!Ext.isEmpty(lineWindow)){
                 //清空线路
                Ext.getCmp('lineTreeGridPanelId').getStore().removeAll();
                Ext.getCmp('lineGridPanelId').getStore().removeAll();         
                //线路添加框清空
                Ext.getCmp("downBeginDeptOrCity").reset();
                Ext.getCmp("downEndDeptOrCity").reset();
            }
            //清空客户群store
            Ext.getCmp('clientBasePanelId').getStore().removeAll();
            //清空文件列表store
            Ext.getCmp('accessoryPanelId').getStore().removeAll();
            //清空客户二级行业store
            Ext.getCmp('activityJoinConditionPanelId').down('combo[name=secondTrade]').getStore().removeAll();
            //物料申请的添加框清空
            Ext.getCmp('materialGridPanelId').down('textfield[name=materialNum]').reset();
            Ext.getCmp('materialGridPanelId').down('textfield[name=materialName]').reset();
            //优惠劵与折扣添加框清空
            cleanPreferentialValue();
             //优惠方式设置为优惠券
            Ext.getCmp('preferentialInformationPanelId').down('combo[name=preferType]').setValue('COUPON');
            setActivityPreferential('COUPON');
            //筛选客户群添加框清空
            Ext.getCmp("clientNameCombox").reset();
            //文件添加框清空
            Ext.getCmp('fileformPanelId').getForm().reset();
            //隐藏时按钮置灰
            Ext.getCmp('materialGridPanelId').down('button[name=delBtn]').disable();
            Ext.getCmp('materialGridPanelId').down('button[name=addBtn]').disable();
            Ext.getCmp('couponGridPanelId').down('button[name=delBtn]').disable();
            Ext.getCmp('couponGridPanelId').down('button[name=addBtn]').disable();
            Ext.getCmp('preferentialInformationPanelId').down('button[name=delBtn]').disable();
            Ext.getCmp('preferentialInformationPanelId').down('button[name=addBtn]').disable();
            Ext.getCmp('clientBasePanelId').down('button[name=delBtn]').disable();
            Ext.getCmp('clientBasePanelId').down('button[name=lookBtn]').disable();
        }
    },
    initComponent:function(){
        var me =this;
        me.items = me.getItems();
        me.buttons = me.getButtons();
        this.callParent();
    } 
});


//客户群下发window
Ext.define('IssuedClientWindow',{
    extend : 'PopWindow',
    closeAction : 'hide',
    id : 'issuedClientWindowId',
    width : 550,
    height : 200,
    getItems : function(){
        var me = this;
        return [Ext.create('IssuedFormPanel')];
    },
    getButtons : function(){
        return [{
            text : i18n('i18n.MarketActivityManagerView.issuedClient'),//下发客户群
            name : 'issuedButton',
            handler : function(btn){
                var form = Ext.getCmp('issuedFormPanelId').getForm();
                var formValues = form.getValues();
                if(!Ext.getCmp('issuedFormPanelId').getForm().isValid()){
                    return;
                }
                if(formValues.planStartTime > formValues.planEndTime){
                    MessageUtil.showMessage(i18n('i18n.MarketActivityManagerView.startMoreThanEnd'));//开始时间不能大于结束时间
                    return;
                }
                var param = {
                    'client.id' : formValues.clientId,
                    'client.clientBaseId' : formValues.clientName,
                    'client.planStartTime' : formValues.planStartTime,
                    'client.planEndTime' : formValues.planEndTime,
                    'id' : marketActivityId
                };
                
                var successFn= function(json){
                    MessageUtil.showInfoMes(i18n('i18n.MarketActivityManagerView.issuedSuccess'));//下发成功
                    Ext.getCmp('issuedClientWindowId').close();
                };
                
                var failureFn= function(json){
                    MessageUtil.showErrorMes(json.message);
                    return;
                }
                
                //是否下发客户群？
                MessageUtil.showQuestionMes(i18n('i18n.MarketActivityManagerView.sureIssued'), function(e){ 
                    if (e == 'yes') {
                        //客户群下发请求
                        activityRecordData.issuedClient(param,successFn,failureFn);
                    }
                });
            }
        },{
            text : i18n('i18n.MarketActivityManagerView.cancel'),//取消
            name : 'cancelButton',
            handler : function(){
                Ext.getCmp('issuedClientWindowId').close();
            }
        }];
    },
    listeners:{
            hide:function(){
                Ext.getCmp('issuedFormPanelId').getForm().reset();
            }
    },
    initComponent:function(){
        var me =this;
        me.items = me.getItems();
        me.buttons = me.getButtons();
        this.callParent();
    } 
});


//线路window
Ext.define('LineWindow',{
    extend : 'PopWindow',
    closeAction : 'hide',
    id : 'lineWindowId',
    layout : 'border',
    width : 800,
    height : 500,
    getItems : function(){
        var me = this;
        return [{
            region: 'west',
            items : [Ext.create('LineTreeForActivity')]
        },{
            region: 'center',
            layout:{
                type:'vbox',
                align:'stretch'
            },
            items : [Ext.create('LineTreeGridPanel'),Ext.create('LineGridPanel')]
        }];
    },
    getButtons : function(){
        return [{
            text : i18n('i18n.MarketActivityManagerView.closeWindow'),
            handler : function(btn){
                Ext.getCmp('lineWindowId').close();
            }
        }];
    },
    listeners:{
        show : function(){
            if(!Ext.isEmpty(myTreeMask)){
                myTreeMask.show();
            }
            var btn = Ext.getCmp('activityJoinConditionPanelId').down('button[name=lineRequest]');
            if(operatorForMarket == 'add' && btn.isAdd){
                Ext.getCmp('lineTreeRequireId').setValue('LEAVE_ARRIVE');
                setActivityLine('LEAVE_ARRIVE');
                Ext.getCmp('lineGridPanelId').child().enable();
                btn.isAdd = false;
            }else if(operatorForMarket == 'add' && !btn.isAdd){
                
            }else{
                Ext.getCmp('lineTreeRequireId').setValue(lineRequest);
                Ext.getCmp('lineGridRequireId').setValue(lineRequest);
                Ext.getCmp('lineGridPanelId').child().disable();
                Ext.getCmp('lineTreeForActivityId').hide();
                //加载线路数据到前台
                if(lineRequest == 'LEAVE_ARRIVE'){
                     Ext.getCmp('lineGridPanelId').getStore().loadData(lineDate);
                }else{
                    Ext.getCmp('lineTreeGridPanelId').getStore().loadData(lineDate);
                }
                setActivityLine(lineRequest);
                Ext.getCmp('lineTreeForActivityId').hide();
            }
        },
        hide : function(){
            if(!Ext.isEmpty(myTreeMask)){
                myTreeMask.hide();
            }
        }
    },
    initComponent:function(){
        var me =this;
        me.items = me.getItems();
        me.buttons = me.getButtons();
        this.callParent();
    } 
});

Ext.onReady(function(){
    var keys=[
        //活动类别
        'ACTIVITY_CATEGORY',
        //活动类型
        'ACTIVITY_TYPE',
        //活动线路区域要求
        'LINE_TYPE',
        // 行业
        'TRADE',
        //二级行业
        'SECOND_TRADE',
        //费用类型
        'COUPON_COST_TYPE',
        //优惠产品=抵扣类型
        'DISCOUNT_PRODUCTS',
        //产品类型
        'CLIENTBASE_PRODUCT_TYPE',
        //活动状态
        'ACTIVITY_STATUS',
        //优惠方式
        'PREFERENTIAL_WAY',
        //客户群状态
        'CUST_CONDITION_STATUS',
        //客户类型
        'CUST_TYPE',
        //客户等级
        'MEMBER_GRADE',
        //货量潜力
        'CARGO_POTENTIAL',
        //客户来源
        'CUST_SOURCE',
        //客户属性
        'CUSTOMER_NATURE',
        //提货方式
        'CLIENTBASE_PICKUPGOODS',
        //合作意向
        'COOPERATION_INTENTION'
    ];
    //初始化业务参数
    initDataDictionary(keys);
    initDeptAndUserInfo();
    //初始化订单来源
    initOrderSources();
    //获取部门负责人信息
    intiPrincipal();
    //获取部门性质
    gainUserDeptCharacter();
    //显示主页面
    viewport=Ext.create('Ext.container.Viewport', { // 创建viewport
        layout : 'border',
        autoScroll:true,
        items:[Ext.create('MarketActivitySearchCondition',{
            region:'north'
        }),{
            xtype:'container',
            region:'center',
            layout:'border',
            items:[Ext.create('OperationButtonPanel',{region:'north'}),Ext.create('MarketActivityGridPanel',{region:'center'})]
        }]
    });
    viewport.setAutoScroll(false);
    viewport.doLayout();
});


//使控件中的按钮 文本框等disable
function disp_disable(isdisp){
    var form = null;
    if(isdisp){
        form = Ext.getCmp('activityFormPanelId').getForm()
        eachDisable(form,true);
        form = Ext.getCmp('activityJoinConditionPanelId').getForm();
        eachDisable(form,true);
        form = Ext.getCmp('useConditionFormPanelId').getForm();
        eachDisable(form,true);
        Ext.getCmp('materialGridPanelId').child().disable();
        //优惠方式tbar禁用
        Ext.getCmp('preferentialInformationPanelId').down('textfield[name=value]').disable();
        Ext.getCmp('preferentialInformationPanelId').down('combo[name=type]').disable();
        Ext.getCmp('preferentialInformationPanelId').down('combo[name=preferType]').disable();
        Ext.getCmp('preferentialInformationPanelId').down('textfield[name=workFlowNum]').disable();
        Ext.getCmp('couponGridPanelId').child().disable();
        Ext.getCmp('clientBasePanelId').down('button[name=delBtn]').disable();
        Ext.getCmp('clientBasePanelId').down('button[name=lookBtn]').disable();
        Ext.getCmp('clientBasePanelId').down('button[name = add]').disable();
        Ext.getCmp('clientBasePanelId').down('combo[name = clientName]').disable();
        Ext.getCmp('marketActivityWindowId').down('button[name=savebutton]').disable();
        Ext.getCmp('fileformPanelId').disable();
        //禁止开单品名 + - 号按钮
        Ext.getCmp('activityJoinConditionPanelId').down('button[name=itemNamesAdd]').disable();
        Ext.getCmp('activityJoinConditionPanelId').down('button[name=itemNamesMinus]').disable();
    }else{
        form = Ext.getCmp('activityFormPanelId').getForm()
        eachDisable(form,false);
        form = Ext.getCmp('activityJoinConditionPanelId').getForm();
        eachDisable(form,false);
        form = Ext.getCmp('useConditionFormPanelId').getForm();
        eachDisable(form,false);
        Ext.getCmp('materialGridPanelId').child().enable();
        //优惠方式tbar禁用
        Ext.getCmp('preferentialInformationPanelId').down('combo[name=preferType]').enable();
        Ext.getCmp('couponGridPanelId').child().enable();
        Ext.getCmp('clientBasePanelId').down('combo[name = clientName]').enable();
        Ext.getCmp('marketActivityWindowId').down('button[name=savebutton]').enable();
        Ext.getCmp('fileformPanelId').enable();
        //恢复开单品名 + - 号按钮
        Ext.getCmp('activityJoinConditionPanelId').down('button[name=itemNamesAdd]').enable();
        Ext.getCmp('activityJoinConditionPanelId').down('button[name=itemNamesMinus]').enable();
    }
};

//设置form下的所有组件为disable
function eachDisable(form,isdisp){
    var i = 0;
    var length = form.getFields().length;
    if(isdisp){
        for(i=0;i<length;i++){
            form.getFields().items[i].disable();
        }
    }else{
        for(i=0;i<length;i++){
            form.getFields().items[i].enable();
        }
    }
}

//设置form下的所有文本组件去空格
function eachTextFieldTrim(form){
    var i = 0;
    var xtype = null;
    for(i=0;i<form.getFields().length;i++){
        xtype = form.getFields().items[i].xtype;
        if(xtype != 'textfield' && xtype != 'textareafield' && xtype != 'textarea')
            continue;
        form.getFields().items[i].setValue(Ext.String.trim(form.getFields().items[i].getValue()))
    }
}

//计算文件大小
function countFileSize(fileInput) {    
    var filePath = fileInput.value;    
    var fileExt = filePath.substring(filePath.lastIndexOf("."))    
            .toLowerCase();    
    //火狐  
    if (fileInput.files && fileInput.files[0]) {    
        return fileInput.files[0].size/1024;  
    } else {    
        var agent = window.navigator.userAgent;   
        var isIE7 = agent.indexOf('MSIE 7.0') != -1;  
        var isIE8 = agent.indexOf('MSIE 8.0') != -1;   
        //IE7和IE8获得文件路径  
        if (isIE7 || isIE8) {   
            fileInput.select();    
            var url = document.selection.createRange().text;    
            try {    
                var fso = new ActiveXObject("Scripting.FileSystemObject");    
            } catch (e) {    
                alert('如果你用的是ie 请将安全级别调低！');    
            }    
            var ie_size = fso.GetFile(url).size;  
        }else{  
            //ie6  
            var oFileChecker = document.getElementById("fileChecker");  
            oFileChecker.src = filePath;  
            oFileChecker.onreadystatechange = function ()  
            {  
                if (oFileChecker.readyState == "complete")  
                {  
                    return oFileChecker.fileSize; 
                }  
            }  
        }  
        return ie_size;
    }    
}

//删除上传文件
function file_delete(rowIndex){
    //是否要删除已上传的文件？
    MessageUtil.showQuestionMes(i18n('i18n.MarketActivityManagerView.deleteFilesSure'), function(e){ 
        var store = Ext.getCmp('accessoryPanelId').getStore();
        var fileInfoModel = new FileInfoModel();
        fileInfoModel.set(store.getAt(rowIndex).data);
        if (e == 'yes') {
            var successFn= function(json){
                store.remove(store.getAt(rowIndex));
                MessageUtil.showInfoMes(i18n('i18n.MarketActivityManagerView.deleteSuccess'));  //删除成功
            };
            
            var failureFn= function(json){
                MessageUtil.showErrorMes(json.message);
                return;
            }
            
            param = {
                'fileInfo' : fileInfoModel.data
            };
            
            activityRecordData.deleteFileInfo(param,successFn,failureFn);
        }
           
    });
}

//下载文件
function file_download(rowIndex){
//    //是否要下载此文件？
//    MessageUtil.showQuestionMes(i18n('i18n.MarketActivityManagerView.seleteSuccess'), function(e){ 
        var store = Ext.getCmp('accessoryPanelId').getStore();
        var fileInfoModel = new FileInfoModel();
        fileInfoModel.set(store.getAt(rowIndex).data);
//        if (e == 'yes') {
            window.location.href="../common/downLoad.action?fileName="+fileInfoModel.get('fileName')+"&inputPath="+fileInfoModel.get('savePath');
//        }
//    });
}

//控件浮框
function viewQuickTips(combo){
    if(Ext.isEmpty(combo.getValue())){
        combo.setValue("");
    }
    if(combo.getValue()!="" && combo.isValid()){
        Ext.QuickTips.init();  
        Ext.QuickTips.register({
            target : combo.el, 
            text : combo.getRawValue()
        }) 
    }else{
        if(Ext.QuickTips.isEnabled()){
            Ext.QuickTips.unregister(combo.el);
        }
    }
}

//初始化部门负责人信息
function intiPrincipal(){
     Ext.Ajax.request({
        url : '../marketing/intiPrincipal.action',
        async:false,
        success:function(response){
            var result = Ext.decode(response.responseText);
            if(result.success){
                Principal = result.principal;
            }else{
                Principal = {};
            }
        }
    });
}

//加载部门树数据
function loadTreeDate(){
    var deptTree = Ext.getCmp("deptTreeForActivityId");
    myMask = new Ext.LoadMask(deptTree, {msg:i18n('i18n.MarketActivityManagerView.deptdataLoad')});//加载部门数据中请稍候。。。
    myMask.show();
    var successFn= function(json){
        treeData=json.deptTree;
        deptTree.store.proxy.data=treeData;
        deptTree.store.load();
        myMask.destroy();
        myMask = null;
        var lineWindow = Ext.getCmp('lineWindowId');
        if(!Ext.isEmpty(lineWindow)){
            if(lineWindow.isVisible()){
                lineWindow.show();
            }
        }
    };
    
    var failureFn= function(json){
        canChange = true;
        myMask.destroy();
        myMask = null;
        MessageUtil.showErrorMes(json.message);
        return;
    }
    activityRecordData.loadMarket({},successFn,failureFn)
}

//加载线路树数据
function loadLineTreeDate(){
    var lineTree = Ext.getCmp('lineTreeForActivityId');
    myTreeMask = new Ext.LoadMask(lineTree, {msg:i18n('i18n.MarketActivityManagerView.deptdataLoad')});//加载部门数据中请稍候。。。
    myTreeMask.show();
    var successFn= function(json){
        treeLineData=json.deptTree;
        lineTree.store.proxy.data=treeLineData
        lineTree.store.load();
        myTreeMask.destroy();
        myTreeMask = null;
    };
    
    var failureFn= function(json){
        myTreeMask.destroy();
        myTreeMask = null;
        MessageUtil.showErrorMes(json.message);
        return;
    }
    activityRecordData.loadDeliverOrArriveTree({},successFn,failureFn)
}

//获取部门性质
function gainUserDeptCharacter(){
    Ext.Ajax.request({
        url : '../marketing/gainUserDeptCharacter.action',
        async:false,
        success:function(response){
            var result = Ext.decode(response.responseText);
            if(result.success){
                DeptCharacter = result.userDeptCharacterType;
            }else{
                DeptCharacter = {};
            }
        }
    });
}