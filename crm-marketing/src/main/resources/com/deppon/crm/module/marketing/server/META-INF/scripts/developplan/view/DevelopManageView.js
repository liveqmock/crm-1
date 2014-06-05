var customerDevelopPlane =  new CustomerDevelopPlaneData();//初始化计划新增修改data

/**
 * 修改表单重置时潜客可以添加发货量等数据的BUG
 * auth：李春雨
 * date：2014-01-15
 * ---------------------------
 */
function judgeCustomerOperator(){
    var searchScatterForm = Ext.getCmp("SearchConditionFormId");
    if(searchScatterForm.getForm().findField('custType').getValue()=='PC_CUSTOMER'){
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
Ext.onReady(function(){
    var bisYd=null;//是否异动
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
        'PLAN_STATUS',
        //公司规模
        'FIRM_SIZE',
        //公司性质
        'COMP_NATURE',
        //客户属性
        'CUSTOMER_NATURE',
        //客户性质
        'CUSTOMER_TYPE',
        /**
         * 增加业务类别和计划类别数据字典在开发计划新增页面
         * auth:李春雨
         * date:2014-01-13
         */
        'BUSINESS_TYPE',
        'PLAN_TYPE'
    ];
    //初始化业务参数
    initDataDictionary(keys);
    initDeptAndUserInfo();
    //操作按钮面板
    Ext.define('ButtonPanel',{
        extend:'NormalButtonPanel', 
        items:null,
        region:'south',
        width:600,
        initComponent:function(){
            this.items = this.getItems();
            this.callParent();
        },
        getItems:function(){
            var me = this;
            return [{
                /**
                 * 增加增删改查四个按钮的权限控制
                 * date:2014-02-18
                 * auth:李春雨
                 */
                xtype:'leftbuttonpanel',
                hidden:!isPermission('/marketing/planManageSBtn.action'),
                items:[{xtype:'button',text:i18n('i18n.DevelopManageView.find'),
                	id : 'findButtonId',
                    handler:function(){
                        var grid = Ext.getCmp('customerDevelopPlaneGird');
                        var selection=grid.getSelectionModel().getSelection();
                        //判断是否选中行
                        //判断是否选中行
                        if (selection.length == 0) {
                            MessageUtil.showMessage(i18n('i18n.developPlan.choice'));
                            return false;
                        }
                        if (selection.length != 1) {
                            MessageUtil.showMessage(i18n('i18n.developPlan.chooseOne'));
                            return false;
                        }
                        var successFn = function(json){
                            var createPlanModel=new CreatePlanModel(json.plan);
                            /**
                             * @author 盛诗庆
                             * @description 获取计划信息
                             * @date 2014-01-20
                             */
                            Ext.getCmp("findButtonId").planInfo = createPlanModel.data;
                            var planFromPanel = Ext.getCmp("DetailPanel").getForm();
                            planFromPanel.loadRecord(createPlanModel);
                            var fields = planFromPanel.getFields().items;
                            for(var i = 0; i < fields.length; i++){  
                                fields[i].setReadOnly(true);  
                            }
                            //设置执行部门执行人
                            planFromPanel.findField("execdeptid").setRawValue(selection[0].get("execdeptName"));
                            planFromPanel.findField("execuserid").setRawValue(selection[0].get("execdeptUserName"));
                            planFromPanel.findField("execuserid").disable(); 
                            planFromPanel.findField("beginTime").clearInvalid();
                            planFromPanel.findField("endTime").clearInvalid();
                        };
                        var failureFn = function(json){
                            MessageUtil.showErrorMes(json.message);
                        };
                        customerDevelopPlane.searchPlanById({'planId':selection[0].get("id")}, successFn, failureFn);
                        Ext.getCmp("detailGridId").store.load({params:{'planId':selection[0].get("id")},callback : function(records){
                        	Ext.getCmp("findButtonId").printData =  records;
                        }});//加载已经选择的客户
                       
                        detailWin.show();
                        document.getElementsByTagName("html")[0].style.overflowY="auto";
                        document.getElementsByTagName("html")[0].style.overflowX="auto";
                        viewport.doLayout();
                    }}, {
                    text:i18n('i18n.developPlan.add'),
                    hidden:!isPermission('/marketing/planManageABtn.action'),
                    xtype:'button',
                    handler:function(){
                        Ext.getCmp("SearchConditionFormId").getForm().reset();//重置查询条件
                        judgeCustomerOperator();
                        Ext.getCmp("developPlanFromPanel").getForm().reset();//重置表单
                        Ext.getCmp("searchResultGridId").store.each(function(r){
                            Ext.getCmp("searchResultGridId").getSelectionModel().deselect(r);
                        });
                        Ext.getCmp("ChooseCustomerGridId").store.each(function(r){
                            Ext.getCmp("ChooseCustomerGridId").getSelectionModel().deselect(r);
                        });
                        bisYd=null;//设置是否异动为空
                        Ext.getCmp("developPlanFromPanel").getForm().findField("beginTime").enable();
                        Ext.getCmp("developPlanFromPanel").getForm().findField("endTime").enable();
                        Ext.getCmp("developPlanFromPanel").getForm().findField("topic").enable();
                        Ext.getCmp("developPlanFromPanel").getForm().findField("activedesc").enable();
                        Ext.getCmp("searchResultGridId").store.removeAll();
                        Ext.getCmp("ChooseCustomerGridId").store.removeAll();
                        win.show();
                        
                        Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").setValue(User.deptId);
                        Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").setRawValue(User.deptName);
                        var deptModel=new DeptModel();
                        deptModel.set("id",User.deptId);
                        deptModel.set("deptName",User.deptName);
                        Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").store.removeAll();
                        Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").store.add(deptModel);
                        Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").setValue(User.deptId);
                        Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").oldValue=User.deptId;
                        Ext.getCmp("developPlanFromPanel").getForm().findField("execdeptid").setValue(User.deptName);                       
                        document.getElementsByTagName("html")[0].style.overflowY="auto";
                        document.getElementsByTagName("html")[0].style.overflowX="auto";
                        viewport.doLayout();
                    }
                },{
                    text:i18n('i18n.developPlan.update'),
                    hidden:!isPermission('/marketing/planManageUBtn.action'),
                    xtype:'button',
                    handler:function(){
                        var grid = Ext.getCmp('customerDevelopPlaneGird');
                        var selection=grid.getSelectionModel().getSelection();
                        //判断是否选中行
                        //判断是否选中行
                        if (selection.length == 0) {
                            MessageUtil.showMessage(i18n('i18n.developPlan.choice'));
                            return false;
                        }
                        if (selection.length != 1) {
                            MessageUtil.showMessage(i18n('i18n.developPlan.chooseOne'));
                            return false;
                        }
                        if(selection[0].get("status")==40){//已过期
                            MessageUtil.showMessage(i18n('i18n.developPlan.updatewarning'));
                            return false;
                        }
//                      if(selection[0].get("status")==20){//执行中
//                          MessageUtil.showMessage(i18n('i18n.developPlan.updatewarninging'));
//                          return false;
//                      }
                        if(selection[0].get("status")==30){//已完成
                            MessageUtil.showMessage(i18n('i18n.developPlan.updatewarningover'));
                            return false;
                        }
                        Ext.getCmp("searchResultGridId").store.each(function(r){
                            Ext.getCmp("searchResultGridId").getSelectionModel().deselect(r);
                        });
                        Ext.getCmp("ChooseCustomerGridId").store.each(function(r){
                            Ext.getCmp("ChooseCustomerGridId").getSelectionModel().deselect(r);
                        });
                        
                        var successFn = function(json){
                            Ext.getCmp("SearchConditionFormId").getForm().reset();//重置查询条件
                            judgeCustomerOperator();
                            Ext.getCmp("ChooseCustomerGridId").store.load({params:{'planId':selection[0].get("id")}});//加载已经选择的客户
                            win.show();
                            document.getElementsByTagName("html")[0].style.overflowY="auto";
                            document.getElementsByTagName("html")[0].style.overflowX="auto";
                            viewport.doLayout();
                            Ext.getCmp("searchResultGridId").store.removeAll();
                            
                            Ext.getCmp("developPlanFromPanel").getForm().reset();//重置表单
                            var createPlanModel=new CreatePlanModel(json.plan);
                            var developPlanFromPanel = Ext.getCmp("developPlanFromPanel").getForm();
                            developPlanFromPanel.loadRecord(createPlanModel);//加载计划信息
                            var deptModel=new DeptModel();
                            deptModel.set("id",json.plan.execdeptid);
                            deptModel.set("deptName",selection[0].get("execdeptName"));
                            //初始化设置部门信息
                            Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").store.removeAll();
                            Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").store.add(deptModel);
                            Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").setValue(json.plan.execdeptid);
                            Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").oldValue=json.plan.execdeptid;
                            //设置下面执行部门名称
                            Ext.getCmp("developPlanFromPanel").getForm().findField("execdeptid").setValue(selection[0].get("execdeptName"));
                            //根据部门查询执行人
                            Ext.getCmp("developPlanFromPanel").getForm().findField("execuserid").store.load({params:{executeDept:json.plan.execdeptid}});
                            if(json.bisYd==="1"){//离职或异动
                                bisYd="1";//设置是异动
                                Ext.getCmp("developPlanFromPanel").getForm().findField("beginTime").clearInvalid();
                                Ext.getCmp("developPlanFromPanel").getForm().findField("endTime").clearInvalid();
                                Ext.getCmp("developPlanFromPanel").getForm().findField("beginTime").disable();
                                Ext.getCmp("developPlanFromPanel").getForm().findField("endTime").disable();
                                Ext.getCmp("developPlanFromPanel").getForm().findField("topic").disable();
                                Ext.getCmp("developPlanFromPanel").getForm().findField("activedesc").disable();
                                Ext.getCmp("developPlanFromPanel").getForm().findField("execuserid").setValue("");
                            }else{
                                bisYd=null;//设置是否异动为空
                                Ext.getCmp("developPlanFromPanel").getForm().findField("execuserid").setValue(json.plan.execuserid);
                                Ext.getCmp("developPlanFromPanel").getForm().findField("beginTime").enable();
                                Ext.getCmp("developPlanFromPanel").getForm().findField("endTime").enable();
                                Ext.getCmp("developPlanFromPanel").getForm().findField("topic").enable();
                                Ext.getCmp("developPlanFromPanel").getForm().findField("activedesc").enable();
                            }
                        };
                        var failureFn = function(json){
                            MessageUtil.showErrorMes(json.message);
                        };
                        var developPlanFromPanel = Ext.getCmp("developPlanFromPanel").getForm();
                        customerDevelopPlane.isOutOrLeave({'planId':selection[0].get("id")}, successFn, failureFn);
                    }
                },{
                    text:i18n('i18n.developPlan.delete'),
                    hidden:!isPermission('/marketing/planManageDBtn.action'),
                    xtype:'button',
                    handler:function(){
                        var me = this;
                        var grid;
                        var store;
                        if(Ext.getCmp('customerDevelopPlaneGird')!=null){
                            grid = Ext.getCmp('customerDevelopPlaneGird');
                            //获取store中数据
                            store = grid.getStore();
                            selection=grid.getSelectionModel().getSelection();
                            //判断是否选中行
                            if (selection.length == 0) {
                                MessageUtil.showMessage(i18n('i18n.developPlan.choice'));
                                return false;
                            }
                            var bj=false;
                            for (var j = 0; j < selection.length; j++) {
                                if(selection[j].get("status")==40 || selection[j].get("status")==20 || selection[j].get("status")==30){
                                    bj=true;
                                    MessageUtil.showMessage(i18n('i18n.developPlan.deletewarning'));
                                    return false;
                                }
                                if(bj){
                                    break;
                                }
                            }
                            //删除弹出提示，是否删除
                            MessageUtil.showQuestionMes(i18n('i18n.developPlan.isdelete'), function(e){ 
                                if (e == 'yes') {
                                    var planeIds = new Array();
                                    for (var j = 0; j < selection.length; j++) {
                                        //当为false时，提示不能删除
                                        if(selection[j].get('running')>0){
                                            MessageUtil.showErrorMes(i18n('i18n.developPlan.errorPlaneState'));
                                            return false;
                                        }
                                        planeIds.push(selection[j].get('id'))
                                    }
                                    //保存成功回调函数
                                    var delSuccessFn = function(result){
                                        //删除grid中数据
                                        for (var j = 0; j < selection.length; j++) {
                                            store.remove(selection[j]);
                                        }
                                        grid.getView().refresh();
                                        MessageUtil.showInfoMes(i18n('i18n.developPlan.deleteSuccess'));
                                    };
                                    //保存失败回调函数
                                    var delFailFn = function(result){
                                        MessageUtil.showErrorMes(result.message);
                                    };
                                    var params={'planeIds':planeIds};
                                    DevelopManageData.prototype.deletePlan(params,delSuccessFn,delFailFn);
                                }
                            });
                        }
                    }
                }]
            },{
                xtype:'middlebuttonpanel' 
            },{
                xtype : 'rightbuttonpanel',
                items : [{
                    xtype : 'button',
                    text : i18n('i18n.developPlan.search'),
                    handler:function(){
                        var startDate = Ext.getCmp("customerDevelopPlaneForm").getForm().findField("createStartTime").getValue();
                        var endDate = Ext.getCmp("customerDevelopPlaneForm").getForm().findField("createEndTime").getValue();
                        if(!Ext.isEmpty(startDate)){
                            if(!Ext.isEmpty(endDate)){
                                var days = DButil.compareTwoDate(startDate,endDate);
                                if(days<0){
                                    MessageUtil.showInfoMes(i18n('i18n.developPlan.startDateBGEndDate'));
                                    return;
                                }else if(days>90){
                                    MessageUtil.showInfoMes(i18n('i18n.developPlan.queryDateLimit'));
                                    return;
                                }
                                if(Ext.isEmpty(Ext.getCmp("customerDevelopPlaneForm").getForm().findField("executeDept").getValue())){
                                    var successFn = function(json){
                                        if(!Ext.isEmpty(json)){
                                            if(parseInt(json.totalCount)>800){
                                                MessageUtil.showInfoMes(i18n('i18n.developPlan.deptLimit'));
                                                return false;
                                            }
                                            Ext.data.StoreManager.lookup('developPlaneGirdStore').loadPage(1);      
                                        }
                                    };
                                    var failureFn = function(json){
                                        MessageUtil.showErrorMes(json.message);
                                    };
                                    customerDevelopPlane.searchDeptCount(null, successFn, failureFn);
                                }else{
                                    Ext.data.StoreManager.lookup('developPlaneGirdStore').loadPage(1);
                                }
                            }else{
                                MessageUtil.showInfoMes("开发时限结束日期不能为空~");
                                return;
                            }
                        }else{
                            MessageUtil.showInfoMes("开发时限起始日期不能为空~");
                            return;
                        }
                    }
                }]
            }];
        }
    });
    /**
     *定义整个界面布局 
     */
    Ext.define('CustomerDevelopPlane',{
        extend:'BasicPanel',
        id:'customerDevelopPlane',
        layout:'border',
        items:null,
        buttonBar:null,
        getColumnsItems:function(){
        return [{
                xtype:'rownumberer',
                width:40,
                header:i18n('i18n.Cycle.rownum')
            },{
                header : i18n('i18n.developPlan.developTheme'),
                dataIndex : 'topic',
                width:190
            },{
                /**
                 * 增加计划类别显示字段
                 * auth：李春雨
                 * date：2014-01-16
                 */
                header : i18n('i18n.returnVisit.planType'),
                dataIndex : 'projectType',
                renderer : function(value){
                    if(value==null||value==''){
                        return i18n('i18n.developSchedule.noMeaning');
                    }else{
                        return DButil.rendererDictionary(value,DataDictionary.PLAN_TYPE);
                    }
                },
                width:85
            },{
                header : i18n('i18n.plan.beginTime'),
                dataIndex : 'timelimit',
                width:160
            },{
                header:i18n('i18n.MaintainpManageView.planStatus'),
                dataIndex : 'status',
                renderer:function(value){
                    return DButil.rendererDictionary(value,DataDictionary.PLAN_STATUS);
                },
                width:65
            },{
                header : i18n('i18n.developPlan.executeDept'),
                dataIndex : 'execdeptName',
                width:130
            },{
                header : i18n('i18n.developPlan.executePersion'),
                dataIndex : 'execdeptUserName',
                width:75
            },{
                header : i18n('i18n.developPlan.creator'),
                dataIndex : 'createUserName',
                width:85
            },{
                header : i18n('i18n.developPlan.createTime'),
                dataIndex : 'createDate',
                renderer : DButil.renderDate,
                width:80
            },{
                header : i18n('i18n.developPlan.lastUpdator'),
                dataIndex : 'modifyUserName',
                hidden:true
            },{
                header : i18n('i18n.developPlan.lastUpdateTime'),
                dataIndex : 'modifyDate',
                renderer : DButil.renderDate,
                width:85
            }];
        },
        initComponent:function(){
            var me = this;
            var developPlaneGirdStore =  Ext.create('DevelopPlaneGirdStore',{'id':'developPlaneGirdStore'});
            developPlaneGirdStore.on('beforeload',function(developPlaneGirdStore,operation,e){
                //判断界面校验是否通过
                if(!Ext.getCmp('customerDevelopPlaneForm').getForm().isValid()){
                    MessageUtil.showMessage(i18n('i18n.developPlan.validate'));
                    return false;
                }
                
                //将表单查询条件传递到后台去
                var searchDevelopPlaneForm = Ext.getCmp("customerDevelopPlaneForm");
                
                //获取对应控件的值，去除空格
                var planeName = Ext.String.trim(searchDevelopPlaneForm.getForm().findField('planeName').getValue());
                var planeDraft =  searchDevelopPlaneForm.getForm().findField('planeDraft').getValue();
                var planeType =  Ext.String.trim(searchDevelopPlaneForm.getForm().findField('planeType').getValue());
                var executorId=searchDevelopPlaneForm.getForm().findField("execuserid").getValue();
                var executeDeptId=searchDevelopPlaneForm.getForm().findField("executeDept").getValue();
                var searchParams = {
                'planCondition.planName':searchDevelopPlaneForm.getForm().findField('planeName').getValue(),
                'planCondition.planMaker':searchDevelopPlaneForm.getForm().findField('planeDraft').getValue(),
                'planCondition.executor':Ext.isEmpty(executorId)==true?null:executorId,
                'planCondition.executeDept':Ext.isEmpty(executeDeptId)==true?null:executeDeptId,
                'planCondition.planStartDate':searchDevelopPlaneForm.getForm().findField('createStartTime').getValue(),
                'planCondition.planOverDate':searchDevelopPlaneForm.getForm().findField('createEndTime').getValue(),
                'planCondition.type':searchDevelopPlaneForm.getForm().findField('planeType').getValue(),
                //查询条件增加计划类别 auth:李春雨 date:2014-01-16
                'planCondition.projectType':searchDevelopPlaneForm.getForm().findField('projectType').getValue()
                };
                Ext.apply(operation,{
                    params : searchParams
                });
            }); 
            
            var customerDevelopPlaneGird = Ext.create('CustomerDevelopPlaneGird',{'id':'customerDevelopPlaneGird','columns':me.getColumnsItems(),'store':developPlaneGirdStore});
            //添加双击事件
            //修改双击时间
            //加载数据到打印页面 盛诗庆
            customerDevelopPlaneGird.on('itemdblclick',function(th,record,item,index,e,eOpts){
            	var successFn = function(json){
                    var createPlanModel=new CreatePlanModel(json.plan);
                    /**
                     * @author 盛诗庆
                     * @description 获取计划信息
                     * @date 2014-01-20
                     */
                    Ext.getCmp("findButtonId").planInfo = createPlanModel.data;
                    var planFromPanel = Ext.getCmp("DetailPanel").getForm();
                    planFromPanel.loadRecord(createPlanModel);
                    var fields = planFromPanel.getFields().items;
                    for(var i = 0; i < fields.length; i++){  
                        fields[i].setReadOnly(true);  
                    }
                    //设置执行部门执行人
                    planFromPanel.findField("execdeptid").setRawValue(record.get("execdeptName"));
                    planFromPanel.findField("execuserid").setRawValue(record.get("execdeptUserName"));
                    planFromPanel.findField("execuserid").disable(); 
                    planFromPanel.findField("beginTime").clearInvalid();
                    planFromPanel.findField("endTime").clearInvalid();
                };
                var failureFn = function(json){
                    MessageUtil.showErrorMes(json.message);
                };
                customerDevelopPlane.searchPlanById({'planId':record.get("id")}, successFn, failureFn);
                Ext.getCmp("detailGridId").store.load({params:{'planId':record.get("id")},callback : function(records){
                	Ext.getCmp("findButtonId").printData =  records;
                }});//加载已经选择的客户
                detailWin.show();
                document.getElementsByTagName("html")[0].style.overflowY="auto";
                document.getElementsByTagName("html")[0].style.overflowX="auto";
                viewport.doLayout();
            });
            //界面布局模块
            me.items =[{
                xtype:'basicpanel',
                height:132,
                region:'north',
                layout:'border',
                items:[{
                    region:'center',
                    xtype:'basicpanel',
                    layout:'fit',
                    items:[Ext.create('CustomerDevelopPlaneForm',{
                                    'id':'customerDevelopPlaneForm',
                                    'planeType':DEVELOP_TYPE})]
                },
                Ext.create('ButtonPanel')]
            },{
                xtype:'basicpanel',
                region:'center',
                layout:'fit',
                items:[customerDevelopPlaneGird]
            }];
            var date = new Date();
            date.setDate(new Date().getDate()-45);
            Ext.getCmp("customerDevelopPlaneForm").getForm().findField("createStartTime").setValue(date);
            Ext.getCmp("customerDevelopPlaneForm").getForm().findField("createEndTime").setValue(new Date().getDate()+44);
//          Ext.getCmp("customerDevelopPlaneForm").getForm().findField("execuserid").store.load();//加载执行人DetailPanel
            this.callParent();
        }
    });
            
    /**
     * 查询按钮
     */
    Ext.define('RightSearchButtonPanel',{
        extend:'NormalButtonPanel', 
        items:null,
        region:'south',
        width:400,
        initComponent:function(){
            this.items = this.getItems();
            this.callParent();
        },
        getItems:function(){
            var me = this;
            return [{
                xtype:'middlebuttonpanel' 
            },{
                xtype:'rightbuttonpanel',  
                items : [{
                    xtype : 'button',
                    text : i18n('i18n.PotentialCustManagerView.search'),
                    width : 70,
                    handler : function(){
                        //判断界面校验是否通过
                        if(!Ext.getCmp('SearchConditionFormId').getForm().isValid()){
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
                    width : 70,
                    handler : function(){
                        //处理重置时客户类型为潜客禁用输入框问题
                        var typeCob = Ext.getCmp("SearchConditionFormId").getForm().findField("custType");
                        if(typeCob){
                            //设置默认类型为潜客，且触发select事件
                            typeCob.setValue(POTENTIAL_CUSTOMER).fireEvent("select",typeCob);
                        }
                        Ext.getCmp("SearchConditionFormId").getForm().reset();
                        
                        var deptModel=new DeptModel();
                        deptModel.set("id",User.deptId);
                        deptModel.set("deptName",User.deptName);
                        Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").store.removeAll();
                        Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").store.add(deptModel);
                        Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").setValue(User.deptId);
                        Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").oldValue=User.deptId;
                        Ext.getCmp("developPlanFromPanel").getForm().findField("execdeptid").setValue(User.deptName);
                        Ext.getCmp("developPlanFromPanel").getForm().findField("execuserid").store.load({params:{executeDept:User.deptId}});
                    }
                }]
            }];
        }
    });
        
    /**
     * 制定开发计划按钮
     */
    Ext.define('RightDownButtonPanel',{
        extend:'PopButtonPanel', 
        items:null,
        region:'south',
        width:500,
        initComponent:function(){
            this.items = this.getItems();
            this.callParent();
        },
        getItems:function(){
            var me = this;
            return [{
                xtype:'middlebuttonpanel' 
            },{
                xtype:'poprightbuttonpanel',  
                width:500,
                items : [{
                    xtype : 'button',
                    text : i18n('i18n.developPlan.savePlan'),
                    width : 115,
                    scope:me.searchCondition,
                    handler : function(t){
                        var store=Ext.getCmp('ChooseCustomerGridId').store;
                        var custList=new Array();
                        if(store.getCount()!=0){
                            for(var i=0;i<store.getCount();i++){//获取已经选择的客户id
                                custList[i]=store.getAt(i).data.id;
                            }
                        }else{
                            MessageUtil.showMessage(i18n("i18n.developPlan.choiceCustFirst"));
                            return false;
                        }
                        //判断界面校验是否通过
                        if(!Ext.getCmp('developPlanFromPanel').getForm().isValid()){
//                          MessageUtil.showInfoMes(i18n('i18n.DevelopManageView.nouFull'));
                            return false;
                        }
                        var successFn = function(json){
                            t.enable();
                            win.hide();
                            Ext.data.StoreManager.lookup('developPlaneGirdStore').loadPage(1);
                            MessageUtil.showInfoMes(i18n('i18n.developPlan.saveDevelopPlanSuccess'));
                        };
                        var failureFn = function(json){
                            t.enable();
                            MessageUtil.showErrorMes(json.message);
                        };
                        var planFromPanel = Ext.getCmp("developPlanFromPanel").getForm();
                        var planFromPanel1 = Ext.getCmp("SearchConditionFormId").getForm();
                        var param = {
                            'developPlan.beginTime':planFromPanel.findField('beginTime').getValue(),//开始时间
                            'developPlan.endTime':planFromPanel.findField('endTime').getValue(),//结束时间
                            'developPlan.topic':planFromPanel.findField('topic').getValue(),
                            'developPlan.execdeptid':planFromPanel1.findField('execdeptid').getValue(),//执行部门
                            'developPlan.activedesc':planFromPanel.findField('activedesc').getValue(),
                            'developPlan.execuserid':planFromPanel.findField('execuserid').getValue(),//执行人员
                            'developPlan.id':planFromPanel.findField('id').getValue(),
                            'developPlan.planType':DEVELOP_TYPE,
                            'developPlan.projectType':planFromPanel.findField('projectType').getValue(),
                            'bisYd':bisYd,
                            'custList':custList
                        };
                        t.disable();
                        customerDevelopPlane.savePlan(param, successFn, failureFn);
                    }
                },{
                    xtype : 'button',
                    text : i18n('i18n.developPlan.reset'),
                    width : 70,
                    handler : function(){
                        //-----修改重置后新增计划BUG auth:李春雨
                        var planFromPanel = Ext.getCmp("developPlanFromPanel");
                        var id = planFromPanel.getForm().findField('id').getValue();
                        //-----
                        Ext.getCmp("developPlanFromPanel").getForm().reset();
                        var deptName=Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").getRawValue();
                        var deptId=Ext.getCmp("SearchConditionFormId").getForm().findField("execdeptid").getValue();
                        Ext.getCmp("developPlanFromPanel").getForm().findField("execdeptid").setValue(deptName);
                        planFromPanel.getForm().findField('id').setValue(id);
                    }
                },{
                    xtype : 'button',
                    text : i18n('i18n.DevelopManageView.close'),
                    width : 70,
                    handler : function(){
                        win.hide();
                    }
                }]
            }];
        }
    });
        
    /**
     * 移动按钮
     */
    Ext.define('ButtonPanelRole',{
        extend :'PopRightButtonPanel',
        width : '100%',
        buttonAlign : 'center',
        layout : 'column',
        items : [{
            columnWidth : 1,
            xtype : 'container',
            style : 'padding-top:60px;border:none',
            width : '100%',
            items : [{
                xtype : 'button',
                text : '&gt;',
                width : 39,
                // 添加所选客户
                handler : function(){
                    //得到所选客户
                    var selection=Ext.getCmp("searchResultGridId").getSelectionModel().getSelection();
                    //已选择客户store
                    var chooseStore=Ext.getCmp("ChooseCustomerGridId").store;
                    for(var i=0;i<selection.length;i++){//遍历所选客户
                        if(!Ext.isEmpty(chooseStore.getById(selection[i].get("id")))){//判断是否有重复
                            MessageUtil.showErrorMes(selection[i].get("custName")+i18n('i18n.developPlan.exist'));
                            return false;
                        }else{
                            Ext.getCmp("searchResultGridId").getSelectionModel().deselect(selection[i]);
                            Ext.getCmp("searchResultGridId").store.remove(selection[i]);
                            //添加到已选择客户store里
                            chooseStore.insert(i,selection[i]);
                        }
                    }
                }
            }]
        },{
            columnWidth : 1,
            width : '100%',
            xtype : 'container',
            style : 'padding-top:60px;border:none',
            items : [{
                xtype : 'button',
                text : '&lt;',
                width : 39,
                //移除所选客户
                handler : function(){
                    //得到已选客户
                    var selection=Ext.getCmp("ChooseCustomerGridId").getSelectionModel().getSelection();
                    //待选择客户store
                    var store=Ext.getCmp("searchResultGridId").store;
                    for(var j=0;j<selection.length;j++){//遍历所选客户
                        if(!Ext.isEmpty(store.getById(selection[j].get("id")))){//判断是否有重复
                            Ext.getCmp("ChooseCustomerGridId").getSelectionModel().deselect(selection[j]);
                            Ext.getCmp("ChooseCustomerGridId").store.remove(selection[j]);
                        }else{
                            Ext.getCmp("ChooseCustomerGridId").getSelectionModel().deselect(selection[j]);
                            Ext.getCmp("ChooseCustomerGridId").store.remove(selection[j]);
                            //添加到已选择客户store里
                            store.insert(j,selection[j]);
                        }
                    }
                    if(Ext.getCmp("ChooseCustomerGridId").store.getCount()==0){
                        Ext.getCmp("ChooseCustomerGridId").getSelectionModel().deselectAll();
                    }
                }
            }]
        }]
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
        searchRightResult:null, //已选择客户列表（右边Grid）
        downPlanformPanel:null,//底部开发计划formPanel
        items:null,
        layout:'border',
        initComponent:function(){
            var me = this;
            
            //查询条件From
            me.searchCondition = Ext.create('SearchConditionForm',{id:'SearchConditionFormId'});
            
//              //设置起始结束时间
//              var date = new Date();
//              me.searchCondition.getForm().findField("overTime").setValue(date);
//              date.setDate(new Date().getDate()-30);
//              me.searchCondition.getForm().findField("beginTime").setValue(date);
            
            //查询客户列表store
            var store=Ext.create('SearchCustomerStore',{id:'SearchCustomerStoreId'});
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
                    'customerVo.custNature':searchScatterForm.getForm().findField('custProperty').getValue(),
                    //合作意向
                    'customerVo.coopIntention':searchScatterForm.getForm().findField('coopIntention').getValue(),
                    //商机状态
                    'customerVo.bussinesState':searchScatterForm.getForm().findField('bussinesState').getValue(),
                    //客户来源
                    'customerVo.custbase':searchScatterForm.getForm().findField('custbase').getValue(),
                    //货量潜力
                    'customerVo.volumePotential':searchScatterForm.getForm().findField('volumePotential').getValue(),
                    'customerVo.deptId':searchScatterForm.getForm().findField('execdeptid').getValue(),
                    //客户类型
                    'customerVo.custType':searchScatterForm.getForm().findField('custType').getValue(),
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
                     /**
                     * 增加查询条件 业务类型
                     * auth:李春雨
                     * date:2014-01-13
                     */
                    'customerVo.businessType':searchScatterForm.getForm().findField('businessType').getValue()
                };
                Ext.apply(operation,{
                    params : searchParams
                });
            });
            var selModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SIMPLE',id:'selModelId'});
            //查询结果Grid
            me.searchLeftResult =  Ext.create('PopupGridPanel',{
                id:'searchResultGridId',
                store:store,
                columns:me.getColumns(),
                selModel:selModel,
                viewConfig:{//可拖动插件
                    forceFit:true
                },
                listeners: {
                    scrollershow: function(scroller) {
                        if (scroller && scroller.scrollEl) {
                                scroller.clearManagedListeners(); 
                                scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
                        }
                    }
                },
                dockedItems:[{
                    xtype:'pagingtoolbar',
                    cls:'pagingtoolbar',
                    store:store,
                    dock:'bottom',
                    displayInfo : true,
                    autoScroll : true,
                    items:[{
                        text: '每页',
                        xtype: 'tbtext'
                    },Ext.create('Ext.form.ComboBox', {
                       width:          window.screen.availWidth*0.0391,
                       triggerAction:  'all',
                       forceSelection: true,
                       value:'10',
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
            
            var chooseSelModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SIMPLE',id:'chooseSelModelId'});
            //查询已选择客户列表store
            var chooseStore=Ext.create('SearchCustomerbyPlanIdStore',{id:'ChooseCustomerStoreId'});
            
            var searchRightGrid=Ext.create('PopupGridPanel',{
                id:'ChooseCustomerGridId',
                store:chooseStore,
                listeners: {
                    scrollershow: function(scroller) {
                        if (scroller && scroller.scrollEl) {
                                scroller.clearManagedListeners(); 
                                scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
                        }
                    }
                },
                columns:me.getColumns(),
                selModel:chooseSelModel,
                viewConfig:{//可拖动插件
                    forceFit:true
                }
            });
            
            me.searchRightResult =  searchRightGrid;
            me.downPlanformPanel=Ext.create('SavePlanPanel',{id:'developPlanFromPanel'});//制定计划
            Ext.getCmp("developPlanFromPanel").getForm().findField("execuserid").store.load();//加载执行人DetailPanel
            
            store.on('load',function(store,records){
                var girdcount=0;
                store.each(function(record){
                    if(!Ext.isEmpty(chooseStore.getById(record.get("id")))){
                        var cells =  Ext.getCmp('searchResultGridId').getView().getNodes()[girdcount].children;
                        for(var i= 0;i<cells.length;i++){
                            cells[i].style.backgroundColor='#FF9797';
                        };
                    };  
                    girdcount=girdcount+1;
                });
            });
            
            //设置items
            me.items = me.getItems();
            this.callParent();
        },
        getItems:function(){//整体布局
            var me = this;
            return [{
                region:'north',
                xtype:'basicpanel',
                height:240,
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
                        layout:'border',
                        flex:1,
                        items:[{
                                region:'center',
                                xtype:'basicpanel',
                                layout:'fit',
                                items:[me.searchLeftResult]
                            },{
                                region:'east',
                                xtype:'basicpanel',
                                layout:'fit',
                                items:[Ext.create('ButtonPanelRole')] 
                            }
                        ]
                    },{
                        region:'east',
                        xtype:'basicpanel',
                        layout:'fit',
//                          flex:1,
                        width:220,
                        items:[me.searchRightResult]
                    }]
                },{
                    region:'south',
                    xtype:'basicpanel',
                    layout:'border',
                    height:130,
                    items:[{
                        region:'center',
                        xtype:'basicpanel',
                        layout:'fit',
                        items:[me.downPlanformPanel]
                    },
                    Ext.create('RightDownButtonPanel')]//border布局下面制定开发计划按钮
                }]
            }];
        },
        getColumns:function(){//查询结果列
            return [{
                header : i18n('i18n.PotentialCustManagerView.customerName'),
                dataIndex : 'custName'
            }, {
                header : '最后回访时间',
                dataIndex : 'lastVistiTime',
                renderer : function(value){
                    if(!Ext.isEmpty(value)){
                        return DButil.renderDate(value);
                    }
                }
            }, {
                header : i18n('i18n.PotentialCustManagerView.contactName'),
                dataIndex : 'linkManName'
            }, {
                header : i18n('i18n.PotentialCustManagerView.contactPhone'),
                dataIndex : 'linkManMobile'
            }, {
                header : i18n('i18n.PotentialCustManagerView.contactTel'),
                dataIndex : 'linkManPhone'
            }, {
                header : i18n('i18n.PotentialCustManagerView.position'),
                dataIndex : 'post'
            },{
                header : i18n('i18n.PotentialCustManagerView.coopIntention'),
                dataIndex : 'coopIntention',
                renderer:function(value){
                    return DButil.rendererDictionary(value,DataDictionary.COOPERATION_INTENTION);
                }
            }, {
                header : i18n('i18n.PotentialCustManagerView.goodsPotential'),
                dataIndex : 'volumePotential',
                renderer:function(value){
                    return DButil.rendererDictionary(value,DataDictionary.CARGO_POTENTIAL);
                }
            }, {
                header : i18n('i18n.PotentialCustManagerView.bizStatus'),
                dataIndex : 'bussinesState',
                renderer:function(value){
                    return DButil.rendererDictionary(value,DataDictionary.BUSINESS_STATUS);
                }
            }, {
                header : i18n('i18n.developPlan.createStartTime'),
                dataIndex : 'createDate',
                renderer : function(value){
                    if(!Ext.isEmpty(value)){
                        return DButil.renderDate(value);
                    }
                }
            }]
        }
    });
    
    /**
     * 显示制定、修改计划
     */
    Ext.define('CreateDevelopPopWindow',{
        extend:'PopWindow',
        alias : 'widget.basicwindow',
        width:820,
        height:700,
        modal:true,
        layout:'fit',
        title:i18n('i18n.developPlan.developPlaneAdd'),
        closeAction:'hide',
        items:[Ext.create('CustomerDevelopPlanePanel',{'id':'customerDevelopPlanePanel'})],
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
    
    /**.
     * <p>
     * 开发计划详细
     * <p>
     * @author 张登
     * @时间 2012-3-26
     */
    Ext.define('DetailPanel',{
        extend:'BasicPanel',
        searchCondition:null, //查询条件From
        searchResult:null, //查询客户列表（左边Grid）
        items:null,
        layout:'border',
        initComponent:function(){
            var me = this;
            //查询条件From
            me.searchCondition = Ext.create('SavePlanPanel',{id:'DetailPanel'});
            //查询已选择客户列表store
            var detailStore=Ext.create('SearchCustomerbyPlanIdStore',{id:'detailStoreId'});
            me.searchResult=Ext.create('PopupGridPanel',{
                id:'detailGridId',
                store:detailStore,
                columns:me.getColumns(),
                viewConfig:{
                    forceFit:true
                }
            });
            //设置items
            me.items = me.getItems();
            //加载明细执行人
//              Ext.getCmp("DetailPanel").getForm().findField("execuserid").store.load();
            this.callParent();
        },
        getItems:function(){//整体布局
            var me = this;
            return [{
                region:'north',
                xtype:'basicpanel',
                height:90,
                layout:'fit',
                items:[me.searchCondition]
            },{
                region:'center',
                xtype:'basicpanel',
                layout:'fit',
                items:[me.searchResult]
            }];
        },
        getColumns:function(){//查询结果列
            return [{
                xtype:'rownumberer',
                width:40,
                header:i18n('i18n.Cycle.rownum')
            },{
                header : 'id',
                dataIndex : 'id',
                hidden:true
            }, {
                header : i18n('i18n.PotentialCustManagerView.customerName'),
                dataIndex : 'custName'
            }, {
                header : i18n('i18n.PotentialCustManagerView.contactName'),
                dataIndex : 'linkManName'
            }, {
                header : i18n('i18n.PotentialCustManagerView.contactPhone'),
                dataIndex : 'linkManMobile'
            }, {
                header : i18n('i18n.PotentialCustManagerView.contactTel'),
                dataIndex : 'linkManPhone'
            }, {
                header : i18n('i18n.PotentialCustManagerView.position'),
                dataIndex : 'post'
            },{
                header : i18n('i18n.PotentialCustManagerView.coopIntention'),
                dataIndex : 'coopIntention',
                renderer:function(value){
                    return rendererDictionary(value,DataDictionary.COOPERATION_INTENTION);
                }
            }, {
                header : i18n('i18n.PotentialCustManagerView.goodsPotential'),
                dataIndex : 'volumePotential',
                renderer:function(value){
                    return rendererDictionary(value,DataDictionary.CARGO_POTENTIAL);
                }
            }, {
                header : i18n('i18n.PotentialCustManagerView.bizStatus'),
                dataIndex : 'bussinesState',
                renderer:function(value){
                    return rendererDictionary(value,DataDictionary.BUSINESS_STATUS);
                }
            }, {
                header : i18n('i18n.PotentialCustManagerView.searchStartTime'),
                dataIndex : 'createDate',
                renderer : DButil.renderDate
            }];
        }
    });
    
    /**
     * 显示明细
     * @revision 盛诗庆，新增打印按钮
     */
    Ext.define('DetailDevelopPopWindow',{
        extend:'PopWindow',
        alias : 'widget.basicwindow',
        width:820,
        height:500,
        modal:true,
        layout:'fit',
        closeAction:'hide',
        items:[Ext.create('DetailPanel',{'id':'DetailPanelId'})],
        listeners:{
            hide:function(){
                document.body.scrollLeft=0;
                document.body.scrollTop=0;
                document.getElementsByTagName("html")[0].style.overflowY="hidden";
                document.getElementsByTagName("html")[0].style.overflowX="hidden";
                viewport.doLayout();
            }
        },
        buttons:[{ xtype:'button',//打印计划按钮 盛诗庆
            text:i18n('i18n.DevelopManageView.printPlan'),
            handler:function(){
            	window.planInfo = Ext.getCmp('findButtonId').planInfo;
            	window.printData = Ext.getCmp('findButtonId').printData;
            	window.open('../marketing/printPlan.action','printPlanPage', 'top=0,left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,channelmode=yes,fullscreen=yes,titlebar=no');
               }},{
            xtype:'button',
            text:i18n('i18n.DevelopManageView.close'),
            handler:function(){
                Ext.getCmp("DetailDevelopPopWindowId").close();
            }
        }]
    });
    var detailWin=Ext.getCmp("DetailDevelopPopWindowId");//获取win
    if(!detailWin){
        detailWin=Ext.create('DetailDevelopPopWindow',{id:'DetailDevelopPopWindowId'});
    }
    
    /**
     *将界面显示到viewport中 
     */
    var viewport=Ext.create('Ext.Viewport',{
        layout : 'border',
        autoScroll:true,
        items:[Ext.create('CustomerDevelopPlane',{region:'center'})]
    });
    viewport.setAutoScroll(false);
    viewport.doLayout();
});