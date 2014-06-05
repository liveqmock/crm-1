/*
 * 客户来电录入页面
 * 2012-11 by肖红叶
 */
Ext.onReady(function(){
    var keys=[
        // 发货潜力
        'CARGO_POTENTIAL',
        //合作物流
        'COOPERATION_LOGISTICS',
        //需求分类
        'CUSTOMER_IDEA',
        // 行业
        'TRADE',
        //二级行业
        'SECOND_TRADE',
        // 商机状态
        'BUSINESS_STATUS',
        //业务类别 auth:李春雨
        'CUST_CATEGORY',
        //合作意向
        'COOPERATION_INTENTION'
    ];
    
    //初始化业务参数
    initDataDictionary(keys);
    //初始化部门用户等信息
    initDeptAndUserInfo();  
    
    //必填字段提示红色*
    var required='<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';
        
    var fChange=false;//标识联系人手机文本框值是否改变
    var phoneChange=false;//标识联系人固话文本框值是否改变
    var nameChange=false;//标识联系人姓名文本框值是否改变
    
    var city = null;//定义一个全局变量，用以保存城市信息 
    var cityId = null;//定义一个全局变量，用以保存城市Id信息
    
    //与页面同步取得城市信息和客户id
    Ext.Ajax.request({
        url:'../marketing/queryDepartmentWithCurrentUser.action',
        async:false,
        success:function(response){
            var json = Ext.decode(response.responseText);
            if(!Ext.isEmpty(json)){
                city=json.city;
                cityId=json.cityId;
            }
        }
    });

    /**
     *客户来电录入界面中客户基本信息录入form
     */
    Ext.define('CustomerBasicInfoFormPanel',{
        extend:'TitleFormPanel', 
        items:null,
        initComponent:function(){           
            this.items = this.getItems();
            this.callParent();
            
        },
        getItems:function(){
            var me = this;
            return [
                    {
                        xtype:'basicfiledset',
                        title:i18n('i18n.marketRecord.customerBasicInfo'),
                        layout:{
                            type:'table',
                            columns:3
                    },
                        defaults:{
                            labelWidth:80,
                            labelAlign:'right',
                            width:240,
                            margin:'4 5 4 5'
                    },
                        defaultType:'textfield',
                        items:[
                           {//联系人手机
                                xtype:'textfield',
                                fieldLabel:i18n('i18n.returnVisit.linkManMobile'),
                                name:'contactMobile',
                                id:'contactMobile',
                                regex:/(^1\d{10}$)|(^\d{8}$)/,
                                regexText:i18n('i18n.marketRecord.marketRecordErrorTelephone'),
                                listeners:{
                                    change:function(t,newValue,oldValue){
                                        if(newValue==oldValue){//如果值未改变
                                            fChange=false;
                                        }else{
                                            fChange=true;
                                        }
                                    },
                                    blur:function(t){//失去焦点时触发
                                        if(t.isValid()){//判断文本框是否有效
                                            if(fChange){
                                                Ext.getCmp("customerBasicInfoFormPanelId").down('form').getForm().findField('secondTrade').setReadOnly(false);
                                                Ext.getCmp("customerBasicInfoFormPanelId").down('form').getForm().findField('contactTrade').setReadOnly(false);
//                                              Ext.getCmp("customerBasicInfoFormPanelId").down('form').userType = 'SEARCH';
                                                var successFn = function(json){
                                                    var customerCallVO=json.customerCallVO;
                                                    if(!Ext.isEmpty(customerCallVO)){//判断客户信息是否为空
                                                        Ext.getCmp('businessType').disable();
                                                        if(customerCallVO.contactType == 'RC_CUSTOMER'){
                                                            MessageUtil.showQuestionMes(i18n('i18n.marketRecord.marketRecordNeedCustomerInfo'), function(e) {
                                                                if (e == 'yes') {
                                                                    var customerBasicInfoModel=new CustomerBasicInfoModel(customerCallVO);
                                                                    if(!Ext.isEmpty(customerBasicInfoModel.get('busStatus'))){
                                                                        customerBasicInfoModel.set('busStatus',rendererDictionary(customerBasicInfoModel.
                                                                                get('busStatus'),DataDictionary.BUSINESS_STATUS));
                                                                    }
                                                                    
                                                                    Ext.getCmp("customerBasicInfoFormPanelId").loadRecord(customerBasicInfoModel);
                                                                    phoneChange=false;
                                                                    nameChange=false;
                                                                    Ext.getCmp('contactCity').setValue(city);
//                                                                  //获取二级行业
                                                                    if(!Ext.isEmpty(customerCallVO.secondTrade)){
                                                                        var customerBasicInfoForm = Ext.getCmp("customerBasicInfoFormPanelId");
                                                                        var codeDesc = DButil.rendererDictionary(customerCallVO.secondTrade,DataDictionary.SECOND_TRADE);
                                                                        var secondTradeRecord = new DataDictionaryModel();
                                                                        secondTradeRecord.set("code",customerCallVO.secondTrade);
                                                                        secondTradeRecord.set("codeDesc",codeDesc);
                                                                        customerBasicInfoForm.down('form').getForm().findField('secondTrade').store.removeAll();
                                                                        customerBasicInfoForm.down('form').getForm().findField('secondTrade').store.add(secondTradeRecord);
                                                                        customerBasicInfoForm.down('form').getForm().findField('secondTrade').setValue(customerCallVO.secondTrade);
                                                                    }
                                                                    Ext.getCmp("customerBasicInfoFormPanelId").down('form').getForm().findField('secondTrade').setReadOnly(true);
                                                                    Ext.getCmp("customerBasicInfoFormPanelId").down('form').getForm().findField('contactTrade').setReadOnly(true);
                                                                }else{
                                                                    Ext.getCmp('businessType').enable();
                                                                    Ext.getCmp("customerBasicInfoFormPanelId").getForm().reset();
                                                                    Ext.getCmp('contactCity').setValue(city);
//                                                                  Ext.getCmp("customerBasicInfoFormPanelId").down('form').userType = 'OTHER';
                                                                }
                                                            });
                                                        }
                                                        else {
                                                            MessageUtil.showQuestionMes(i18n('i18n.marketRecord.marketRecordNeedPotentialCustomerInfo'), function(e) {
                                                                if (e == 'yes') {
                                                                    var customerBasicInfoModel=new CustomerBasicInfoModel(customerCallVO);
                                                                    
                                                                    if(!Ext.isEmpty(customerBasicInfoModel.get('busStatus'))){
                                                                        customerBasicInfoModel.set('busStatus',rendererDictionary(customerBasicInfoModel.
                                                                                get('busStatus'),DataDictionary.BUSINESS_STATUS));
                                                                    }
                                                                    Ext.getCmp("customerBasicInfoFormPanelId").loadRecord(customerBasicInfoModel);
                                                                    phoneChange=false;
                                                                    nameChange=false;
                                                                    Ext.getCmp('contactCity').setValue(city);
                                                                    //获取二级行业
                                                                    if(!Ext.isEmpty(customerCallVO.secondTrade)){
                                                                        var customerBasicInfoForm = Ext.getCmp("customerBasicInfoFormPanelId");
                                                                        var codeDesc = DButil.rendererDictionary(customerCallVO.secondTrade,DataDictionary.SECOND_TRADE);
                                                                        var secondTradeRecord = new DataDictionaryModel();
                                                                        secondTradeRecord.set("code",customerCallVO.secondTrade);
                                                                        secondTradeRecord.set("codeDesc",codeDesc);
                                                                        customerBasicInfoForm.down('form').getForm().findField('secondTrade').store.removeAll();
                                                                        customerBasicInfoForm.down('form').getForm().findField('secondTrade').store.add(secondTradeRecord);
                                                                        customerBasicInfoForm.down('form').getForm().findField('secondTrade').setValue(customerCallVO.secondTrade);
                                                                    }
                                                                    Ext.getCmp("customerBasicInfoFormPanelId").down('form').getForm().findField('secondTrade').setReadOnly(true);
                                                                    Ext.getCmp("customerBasicInfoFormPanelId").down('form').getForm().findField('contactTrade').setReadOnly(true);
                                                                }else{
                                                                    Ext.getCmp('businessType').enable();
                                                                    Ext.getCmp("customerBasicInfoFormPanelId").getForm().reset();
                                                                    Ext.getCmp('contactCity').setValue(city);
//                                                                  Ext.getCmp("customerBasicInfoFormPanelId").down('form').userType = 'OTHER';
                                                                }
                                                            });
                                                        }                                                       
                                                    }
                                                    //输入手机号码，非潜散客和固定客户，清空表格其他信息
                                                    else{
                                                        //当文本框的内容更改，且系统判断修改后的手机号并非潜散客和固定客户时，清空表格相关信息
                                                        if(!Ext.isEmpty(Ext.getCmp('id').getValue())){
                                                            Ext.getCmp('contactPhone').setValue('');
                                                            Ext.getCmp('custLinkManName').setValue('');
                                                            clearCustomerInfo();//调用清空表单信息的函数
                                                        }
//                                                      Ext.getCmp("customerBasicInfoFormPanelId").down('form').userType = 'OTHER';
                                                    }
                                                }
                                                var param = {'mobile':Ext.String.trim(t.getValue())};
                                                var failureFn=function(){
//                                                  Ext.getCmp("customerBasicInfoFormPanelId").down('form').userType = 'OTHER';
                                                }
                                                MarketRecordData.prototype.queryCustomerByMobile(param,successFn,failureFn);
                                                
                                                fChange=false;//设置默认
                                            }
                                        }
                                    }
                                }
                            },
                            {//联系人电话
                                fieldLabel:i18n('i18n.fiveKilometreMap.linkmanPhone'),
                                name:'contactPhone',
                                id:'contactPhone',  
                                maxLength:40,
                                maxLengthText : i18n('i18n.developPlan.maxLength')+40,
                                minLength:7,
                                minLengthText : i18n('i18n.developPlan.minLength')+7,
                                regex:/^\d{3}[\d\*-\/]{4,37}$/,
                                regexText:i18n('i18n.marketRecord.marketRecordErrorMobile'),
                                listeners:{
                                        //文本框失去焦点触发事件
                                        change:function(t,newValue,oldValue){
                                            if(newValue==oldValue){//如果值未改变
                                                phoneChange=false;
                                            }else{
                                                phoneChange=true;
                                            }
                                        },
                                        blur:function(t){
                                            //失去焦点时触发
                                            if(t.isValid()){//判断文本框是否有效
                                                if(phoneChange){
                                                    if(!Ext.isEmpty(Ext.getCmp('custLinkManName').getValue)){
                                                        Ext.getCmp("customerBasicInfoFormPanelId").down('form').getForm().findField('secondTrade').setReadOnly(false);
                                                        Ext.getCmp("customerBasicInfoFormPanelId").down('form').getForm().findField('contactTrade').setReadOnly(false);
//                                                      Ext.getCmp("customerBasicInfoFormPanelId").down('form').userType = 'SEARCH';
                                                        var successFn = function(json){
                                                            var customerCallVO=json.customerCallVO;
                                                            if(!Ext.isEmpty(customerCallVO)){//判断客户信息是否为空
                                                                Ext.getCmp('businessType').disable();
                                                                if(customerCallVO.contactType == 'RC_CUSTOMER'){
                                                                    MessageUtil.showQuestionMes(i18n('i18n.marketRecord.marketRecordNeedCustomerInfo'), function(e) {
                                                                        if (e == 'yes') {
                                                                            var customerBasicInfoModel=new CustomerBasicInfoModel(customerCallVO);
                                                                            if(!Ext.isEmpty(customerBasicInfoModel.get('busStatus'))){
                                                                                customerBasicInfoModel.set('busStatus',rendererDictionary(customerBasicInfoModel.
                                                                                        get('busStatus'),DataDictionary.BUSINESS_STATUS));
                                                                            }
                                                                            Ext.getCmp("customerBasicInfoFormPanelId").loadRecord(customerBasicInfoModel);
                                                                            fChange=false;
                                                                            nameChange=false;
                                                                            
                                                                            Ext.getCmp('contactCity').setValue(city);
                                                                            //获取二级行业
                                                                            if(!Ext.isEmpty(customerCallVO.secondTrade)){
                                                                                var customerBasicInfoForm = Ext.getCmp("customerBasicInfoFormPanelId");
                                                                                var codeDesc = DButil.rendererDictionary(customerCallVO.secondTrade,DataDictionary.SECOND_TRADE);
                                                                                var secondTradeRecord = new DataDictionaryModel();
                                                                                secondTradeRecord.set("code",customerCallVO.secondTrade);
                                                                                secondTradeRecord.set("codeDesc",codeDesc);
                                                                                customerBasicInfoForm.down('form').getForm().findField('secondTrade').store.removeAll();
                                                                                customerBasicInfoForm.down('form').getForm().findField('secondTrade').store.add(secondTradeRecord);
                                                                                customerBasicInfoForm.down('form').getForm().findField('secondTrade').setValue(customerCallVO.secondTrade);
                                                                            }
                                                                            Ext.getCmp("customerBasicInfoFormPanelId").down('form').getForm().findField('secondTrade').setReadOnly(true);
                                                                            Ext.getCmp("customerBasicInfoFormPanelId").down('form').getForm().findField('contactTrade').setReadOnly(true);
                                                                        }else{
                                                                            Ext.getCmp('businessType').enable();
                                                                            Ext.getCmp("customerBasicInfoFormPanelId").getForm().reset();
                                                                            Ext.getCmp('contactCity').setValue(city);
//                                                                          Ext.getCmp("customerBasicInfoFormPanelId").down('form').userType = 'OTHER';
                                                                        }
                                                                    });                                                     
                                                                }
                                                                else {
                                                                    MessageUtil.showQuestionMes(i18n('i18n.marketRecord.marketRecordNeedPotentialCustomerInfo'), function(e) {
                                                                        if (e == 'yes') {
                                                                            var customerBasicInfoModel=new CustomerBasicInfoModel(customerCallVO);
                                                                            if(!Ext.isEmpty(customerBasicInfoModel.get('busStatus'))){
                                                                                customerBasicInfoModel.set('busStatus',rendererDictionary(customerBasicInfoModel.
                                                                                        get('busStatus'),DataDictionary.BUSINESS_STATUS));
                                                                            }
                                                                            Ext.getCmp("customerBasicInfoFormPanelId").loadRecord(customerBasicInfoModel);
                                                                            fChange=false;
                                                                            nameChange=false;
                                                                            Ext.getCmp('contactCity').setValue(city);
                                                                            //获取二级行业
                                                                            if(!Ext.isEmpty(customerCallVO.secondTrade)){
                                                                                var customerBasicInfoForm = Ext.getCmp("customerBasicInfoFormPanelId");
                                                                                var codeDesc = DButil.rendererDictionary(customerCallVO.secondTrade,DataDictionary.SECOND_TRADE);
                                                                                var secondTradeRecord = new DataDictionaryModel();
                                                                                secondTradeRecord.set("code",customerCallVO.secondTrade);
                                                                                secondTradeRecord.set("codeDesc",codeDesc);
                                                                                customerBasicInfoForm.down('form').getForm().findField('secondTrade').store.removeAll();
                                                                                customerBasicInfoForm.down('form').getForm().findField('secondTrade').store.add(secondTradeRecord);
                                                                                customerBasicInfoForm.down('form').getForm().findField('secondTrade').setValue(customerCallVO.secondTrade);
                                                                            }
                                                                            Ext.getCmp("customerBasicInfoFormPanelId").down('form').getForm().findField('secondTrade').setReadOnly(true);
                                                                            Ext.getCmp("customerBasicInfoFormPanelId").down('form').getForm().findField('contactTrade').setReadOnly(true);
                                                                        }else{
                                                                            Ext.getCmp('businessType').enable();
                                                                            Ext.getCmp("customerBasicInfoFormPanelId").getForm().reset();
                                                                            Ext.getCmp('contactCity').setValue(city);
//                                                                          Ext.getCmp("customerBasicInfoFormPanelId").down('form').userType = 'OTHER';
                                                                        }
                                                                    });
                                                                }
                                                                
                                                            }
                                                            //当判定输入的固话非潜散客和固定客户，清空表格其他信息
                                                            else{
                                                                //当文本框的内容更改，且系统判断修改后的固话并非潜散客和固定客户时，清空表格相关信息
                                                                if(!Ext.isEmpty(Ext.getCmp('id').getValue())){
                                                                    Ext.getCmp('contactMobile').setValue('');
                                                                    Ext.getCmp('custLinkManName').setValue('');
                                                                    clearCustomerInfo();//调用清空表单信息的函数
                                                                }
//                                                              Ext.getCmp("customerBasicInfoFormPanelId").down('form').userType = 'OTHER';
                                                            }
                                                        }
                                                        var param = {
                                                                        'contactPhone':Ext.String.trim(t.getValue()),
                                                                        'custLinkName':Ext.String.trim(Ext.getCmp('custLinkManName').getValue())                                                        
                                                        };
                                                        var failureFn=function(json){
//                                                          Ext.getCmp("customerBasicInfoFormPanelId").down('form').userType = 'OTHER';
                                                            MessageUtil.showErrorMes(json.message);
                                                        }
                                                        MarketRecordData.prototype.queryCustomerByPhoneDeptIdName(param,successFn,failureFn);
                                                    }                                               
                                                    
                                                    phoneChange=false;//设置默认
                                                }
                                            }
                                        }
                                    }
                                },
                                {//联系人姓名
                                    fieldLabel:required+i18n('i18n.returnVisit.linkName'),
                                    name:'custLinkManName',
                                    allowBlank:false,
                                    maxLength:20,
                                    maxLengthText : i18n('i18n.developPlan.maxLength')+20,
                                    id:'custLinkManName',
                                    listeners:{
                                        //文本框失去焦点触发事件
                                        change:function(t,newValue,oldValue){
                                            if(newValue==oldValue){//如果值未改变
                                                nameChange=false;
                                            }else{
                                                nameChange=true;
                                            }
                                        },
                                        blur:function(t){
                                            //失去焦点时触发
                                            if(t.isValid()){//判断文本框是否有效
                                                if(nameChange){
                                                    if(!Ext.isEmpty(Ext.getCmp('contactPhone').getValue)){
                                                        Ext.getCmp("customerBasicInfoFormPanelId").down('form').getForm().findField('secondTrade').setReadOnly(false);
                                                        Ext.getCmp("customerBasicInfoFormPanelId").down('form').getForm().findField('contactTrade').setReadOnly(false);
//                                                      Ext.getCmp("customerBasicInfoFormPanelId").down('form').userType = 'SEARCH';
                                                        var successFn = function(json){
                                                            var customerCallVO=json.customerCallVO;
                                                            if(!Ext.isEmpty(customerCallVO)){//判断客户信息是否为空
                                                                Ext.getCmp('businessType').disable();
                                                                if(customerCallVO.contactType == 'RC_CUSTOMER'){
                                                                    MessageUtil.showQuestionMes(i18n('i18n.marketRecord.marketRecordNeedCustomerInfo'), function(e) {
                                                                        if (e == 'yes') {
                                                                            var customerBasicInfoModel=new CustomerBasicInfoModel(customerCallVO);
                                                                            if(!Ext.isEmpty(customerBasicInfoModel.get('busStatus'))){
                                                                                customerBasicInfoModel.set('busStatus',rendererDictionary(customerBasicInfoModel.
                                                                                        get('busStatus'),DataDictionary.BUSINESS_STATUS));
                                                                            }
                                                                            Ext.getCmp("customerBasicInfoFormPanelId").loadRecord(customerBasicInfoModel);
                                                                            fChange=false;
                                                                            phoneChange=false;
                                                                            Ext.getCmp('contactCity').setValue(city);
                                                                            //获取二级行业
                                                                            if(!Ext.isEmpty(customerCallVO.secondTrade)){
                                                                                var customerBasicInfoForm = Ext.getCmp("customerBasicInfoFormPanelId");
                                                                                var codeDesc = DButil.rendererDictionary(customerCallVO.secondTrade,DataDictionary.SECOND_TRADE);
                                                                                var secondTradeRecord = new DataDictionaryModel();
                                                                                secondTradeRecord.set("code",customerCallVO.secondTrade);
                                                                                secondTradeRecord.set("codeDesc",codeDesc);
                                                                                customerBasicInfoForm.down('form').getForm().findField('secondTrade').store.removeAll();
                                                                                customerBasicInfoForm.down('form').getForm().findField('secondTrade').store.add(secondTradeRecord);
                                                                                customerBasicInfoForm.down('form').getForm().findField('secondTrade').setValue(customerCallVO.secondTrade);
                                                                            }
                                                                            Ext.getCmp("customerBasicInfoFormPanelId").down('form').getForm().findField('secondTrade').setReadOnly(true);
                                                                            Ext.getCmp("customerBasicInfoFormPanelId").down('form').getForm().findField('contactTrade').setReadOnly(true);
                                                                        }else{
                                                                            Ext.getCmp('businessType').enable();
                                                                            Ext.getCmp("customerBasicInfoFormPanelId").getForm().reset();
                                                                            Ext.getCmp('contactCity').setValue(city);
//                                                                          Ext.getCmp("customerBasicInfoFormPanelId").down('form').userType = 'OTHER';
                                                                        }
                                                                    });                                                     
                                                                }
                                                                else {
                                                                    MessageUtil.showQuestionMes(i18n('i18n.marketRecord.marketRecordNeedPotentialCustomerInfo'), function(e) {
                                                                        if (e == 'yes') {
                                                                            var customerBasicInfoModel=new CustomerBasicInfoModel(customerCallVO);
                                                                            if(!Ext.isEmpty(customerBasicInfoModel.get('busStatus'))){
                                                                                customerBasicInfoModel.set('busStatus',rendererDictionary(customerBasicInfoModel.
                                                                                        get('busStatus'),DataDictionary.BUSINESS_STATUS));
                                                                            }
                                                                            Ext.getCmp("customerBasicInfoFormPanelId").loadRecord(customerBasicInfoModel);
                                                                            fChange=false;
                                                                            phoneChange=false;
                                                                            Ext.getCmp('contactCity').setValue(city);
                                                                            //获取二级行业
                                                                            if(!Ext.isEmpty(customerCallVO.secondTrade)){
                                                                                var customerBasicInfoForm = Ext.getCmp("customerBasicInfoFormPanelId");
                                                                                var codeDesc = DButil.rendererDictionary(customerCallVO.secondTrade,DataDictionary.SECOND_TRADE);
                                                                                var secondTradeRecord = new DataDictionaryModel();
                                                                                secondTradeRecord.set("code",customerCallVO.secondTrade);
                                                                                secondTradeRecord.set("codeDesc",codeDesc);
                                                                                customerBasicInfoForm.down('form').getForm().findField('secondTrade').store.removeAll();
                                                                                customerBasicInfoForm.down('form').getForm().findField('secondTrade').store.add(secondTradeRecord);
                                                                                customerBasicInfoForm.down('form').getForm().findField('secondTrade').setValue(customerCallVO.secondTrade);
                                                                            }
                                                                            Ext.getCmp("customerBasicInfoFormPanelId").down('form').getForm().findField('secondTrade').setReadOnly(true);
                                                                            Ext.getCmp("customerBasicInfoFormPanelId").down('form').getForm().findField('contactTrade').setReadOnly(true);
                                                                        }else{
                                                                            Ext.getCmp('businessType').enable();
                                                                            Ext.getCmp("customerBasicInfoFormPanelId").getForm().reset();
                                                                            Ext.getCmp('contactCity').setValue(city);
//                                                                          Ext.getCmp("customerBasicInfoFormPanelId").down('form').userType = 'OTHER';
                                                                        }
                                                                    });
                                                                }
                                                                
                                                            }
                                                            else{
                                                                //当文本框的内容更改，且系统判断修改后的联系人姓名并非潜散客和固定客户时，清空表格相关信息
                                                                if(!Ext.isEmpty(Ext.getCmp('id').getValue())){
                                                                    Ext.getCmp('contactMobile').setValue('');
                                                                    Ext.getCmp('contactPhone').setValue('');
                                                                    clearCustomerInfo();//调用清空表单信息的函数
                                                                }
                                                                Ext.getCmp('custName').setValue(t.getValue());
//                                                              Ext.getCmp("customerBasicInfoFormPanelId").down('form').userType = 'OTHER';
                                                            }
                                                        }
                                                        var param = {
                                                                        'contactPhone':Ext.String.trim(Ext.getCmp('contactPhone').getValue()),
                                                                        'custLinkName':Ext.String.trim(t.getValue())                                                        
                                                        };
                                                        var failureFn=function(json){
//                                                          Ext.getCmp("customerBasicInfoFormPanelId").down('form').userType = 'OTHER';
                                                            MessageUtil.showErrorMes(json.message);
                                                        }
                                                        MarketRecordData.prototype.queryCustomerByPhoneDeptIdName(param,successFn,failureFn);
                                                    }                                               
                                                    else{
                                                        Ext.getCmp('custName').setValue(t.getValue());
                                                    }
                                                    nameChange=false;//设置默认
                                                }
                                            }
                                        }
                                    }
                                },//第二行记录       
                                {//客户名称
                                    fieldLabel:required+i18n('i18n.customerGroup.custName'),
                                    name:'custName',
                                    maxLength:20,
                                    maxLengthText : i18n('i18n.developPlan.maxLength')+20,
                                    allowBlank:false,
                                    id:'custName'
                                },
                                {//职位
                                    fieldLabel:i18n('i18n.developSchedule.position'),
                                    name:'contactJob',
                                    maxLength:20,
                                    maxLengthText : i18n('i18n.developPlan.maxLength')+20,
                                    id:'contactJob'
                                },
                                {//商机状态
                                    fieldLabel:i18n('i18n.PotentialCustManagerView.bizStatus'), 
                                    name:'busStatus',
                                    id:'busStatus',
                                    readOnly:true
                                },//第三行记录
                                
//                              {//行业
//                                  xtype:'combo',
//                                  queryMode: 'local',
//                                  fieldLabel:i18n('i18n.PotentialCustManagerView.industry'),
//                                  name:'contactTrade',
//                                  id:'contactTrade',
//                                  editable:false,
//                                  store:getDataDictionaryByName(DataDictionary,'TRADE'),
//                                  displayField:'codeDesc',
//                                  valueField:'code'
//                              },
                                {//二级行业
                                    xtype:'customertrade',
                                    userType:'OTHER',
                                    border:0,
                                    second_trade_auto_expand:false,
                                    colspan:2,
                                    fieldLabel:i18n('i18n.PotentialCustManagerView.industry'),
                                    width:500,
                                    trade_labelWidth:80,
                                    trade_width:240,
                                    trade_name:'contactTrade',
                                    trade_fieldname:i18n('i18n.PotentialCustManagerView.firstTrade'),
                                    second_trade_labelWidth:95,
                                    second_trade_width:255,
                                    second_trade_name:'secondTrade',
                                    second_trade_fieldname:i18n('i18n.PotentialCustManagerView.secondTrade')
                                },
                                {//城市
                                    fieldLabel:i18n('i18n.PotentialCustEditView.city'),
                                    name:'contactCity',
                                    id:'contactCity',
                                    value:Ext.isEmpty(city)?'':city,
                                    readOnly:true,
                                    cls:'readonly'
                                },
                                
                                //第四行记录
                                {//地址
                                    fieldLabel:i18n('i18n.PotentialCustEditView.address'),
                                    name:'contactAddress',
                                    id:'contactAddress',
                                    width:495,
                                    maxLength:100,
                                    maxLengthText : i18n('i18n.developPlan.maxLength')+100,
                                    colspan:2,                                  
                                },
                                 /**
                                 * auth:李春雨
                                 * date:2014-01-11
                                 * 增加业务类别选择框
                                 */
                                {
                                    //业务类别
                                    fieldLabel : required+i18n('i18n.PotentialCustManagerView.businessType'),
                                    name : 'businessType',
                                    id: 'businessType',
                                    xtype : 'combo',
                                    colspan : 3,
                                    store:getDataDictionaryByName(DataDictionary,'CUST_CATEGORY'),
                                    queryMode:'local',
                                    displayField:'codeDesc',
                                    valueField:'code',
                                    allowBlank:false,
                                    editable:false,
                                    value : 'LTT',
                                    forceSelection :true,
                                    listeners:{
                                        change:DButil.comboSelsct
                                    }
                                },
                                {//隐藏的客户编码信息
                                    xtype:'hiddenfield',
                                    fieldLabel:i18n('i18n.customerGroup.custId'),
                                    name:'id',
                                    id:'id'                                 
                                },{//隐藏的联系人ID
                                    xtype:'hiddenfield',
                                    fieldLabel:i18n('i18n.ReturnVisitAdd.inkmanId'),
                                    name:'custLinkManId',
                                    id:'custLinkManId'                                  
                                },
                                {//隐藏框存放客户类型
                                    xtype:'hiddenfield',
                                    fieldLabel:i18n('i18n.PotentialCustManagerView.custType'),
                                    name:'contactType',
                                    id:'contactType'
                                }
                            ]
                    }
              ];
        }
    });
    
    /**
     * 客户需求录入表格中要使用的需求分类combo
     */
    Ext.define('CustomerDemandCombobox', {
        extend:'Ext.form.field.ComboBox',
        typeAhead: true,
        queryMode: 'local',
        store: getDataDictionaryByName(DataDictionary,'CUSTOMER_IDEA'),
        valueField: 'code',
        editable:'false',
        displayField: 'codeDesc',
        forceSelection :true,
        listeners:{
            change:DButil.comboSelsct
        }
    });  
    //意见类型
    var customerDemandCombobox = Ext.create('CustomerDemandCombobox');
    
    //客户需求行编辑器
    var customerDemandRowEditing = Ext.create('Ext.grid.plugin.CellEditing', {  
        //设置单击一次鼠标，即可进行内容编辑
        clicksToEdit : 1
    }) ;
    
    /*
     * 客户需求录入表格
     */
    Ext.define('CustomerDemandAddGrid',{
        extend:'PopupGridPanel',
        defaults:{
            align:'center'
        },
        store :Ext.create('CustomerDemandStore',{'id':'customerDemandStoreId'}),
        columnLines:true,
        plugins:customerDemandRowEditing,
        initComponent:function(){             
            var me = this;
            me.columns = [
              {//序号
                xtype:'rownumberer',
                header:i18n('i18n.Cycle.rownum'),
                width:50,
                align:'center'
              },{//需求分类
                header :i18n('i18n.returnVisit.opinionType'),
                dataIndex:'demandType',
                flex:0.2,
                dataIndex:'opinionType',
                align:'center',
                field:customerDemandCombobox,
                renderer: Ext.util.Format.comboRenderer(customerDemandCombobox)
              },{//需求及问题
                header :i18n('i18n.returnVisit.problem'),
                flex:0.3,
                dataIndex:'problem',
                editor:{
                    xtype:'textfield'
                }
              },{//需求问题解决办法
                header :i18n('i18n.returnVisit.solution'),
                flex:0.5,
                dataIndex:'solution',
                editor:{
                    xtype:'textfield'
                }
              }
            ];
            this.callParent();
        }
    });
    
    /**
     * 客户需求录入表格的Panel
     */
    Ext.define('CustomerDemandAddGridPanel',{
        extend:'BasicVboxPanel',
        items : null,
        initComponent : function() {
            this.items = this.getItems();
            this.callParent();
        },
        getItems : function() {
            var me = this;
            return [
                    {
                        xtype:'toppanel',   
                        items:[
                               {
                                    xtype:'titlepanel',  
                                    flex:1,
                                    items:[
                                           {//客户需求
                                            xtype:'displayfield',
                                            value:required+i18n('i18n.PotentialCustEditView.custNeed')
                                           }
                                    ]
                                },{
                                    xtype:'btnpanel',  
                                    defaultType:'button',
                                    items:[
                                       {//增加
                                            text:i18n('i18n.returnVisit.add'),
                                            id:'CustomerDemandAddBtn',
                                            handler:function(){
                                                //客户需求增加按钮                                              
                                                    var panelStore = Ext.getCmp('customerDemandAddGridId').getStore();
                                                    if(panelStore.data.length>=10){
                                                        MessageUtil.showErrorMes(i18n('i18n.returnVisit.lessThenTen'));
                                                        return false;
                                                    }
                                                    var panelModel = Ext.getCmp('customerDemandAddGridId').store.model.modelName;
                                                    panelStore.add(Ext.create(panelModel));                                                                                         
                                            }
                                        },{//删除
                                            text:i18n('i18n.returnVisit.delete'),
                                            id:'CustomerDemandDeleteBtn',
                                            handler:function(){
                                                //客户需求删除按钮
                                                var selection = Ext.getCmp('customerDemandAddGridId').getSelectionModel().getSelection()[0];;
                                                var panelStore = Ext.getCmp('customerDemandAddGridId').getStore();
                                                if (selection) {
                                                    MessageUtil.showQuestionMes(i18n('i18n.developSchedule.isdelete'), function(e) {
                                                        if (e == 'yes') {
                                                            panelStore.remove(selection);
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    ]
                                }
                        ] 
                    },{
                        xtype:'basicpanel',     
                        flex:1,
                        items:[Ext.create('CustomerDemandAddGrid',{'id':'customerDemandAddGridId'})]
                    }
          ];
        }
    });
    
    
    /**
     * 货量潜力combo
     */
    Ext.define('VolumePotentialCombo', {
        extend:'Ext.form.field.ComboBox',
        typeAhead: true,
        queryMode: 'local',
        store: getDataDictionaryByName(DataDictionary,'CARGO_POTENTIAL'),
        valueField: 'code',
        displayField: 'codeDesc',
        forceSelection :true,
        listeners:{
            change:DButil.comboSelsct
        }
    });  

    /**
     * 合作物流公司combo
     */
    Ext.define('CompanyTogetherCombo', {
        extend:'Ext.form.field.ComboBox',
        typeAhead: true,
        queryMode: 'local',
        store: getDataDictionaryByName(DataDictionary,'COOPERATION_LOGISTICS'),
        valueField: 'code',
        displayField: 'codeDesc',
        forceSelection :true,
        listeners:{
            change:DButil.comboSelsct
        }
    }); 
    
    /**
     * 是否已有线路 store
     */
    Ext.define('IsHaveLineCombo', {
        extend:'Ext.form.field.ComboBox',
        typeAhead: true,
        queryMode: 'local',
        store:Ext.create('Ext.data.Store',{
            fields:['code','codeDesc'],
            data:[
              {code:'YES',codeDesc:'是'},
              {code:'NO',codeDesc:'否'}
            ]
        }),
        valueField: 'code',
        displayField: 'codeDesc',
        forceSelection :true,
        listeners:{
            change:DButil.comboSelsct
        }
    }); 
    
    //货量潜力下拉框
    var volumePotentialCombo = Ext.create('VolumePotentialCombo');
    
    //合作物流下拉框
    var companyTogetherCombo = Ext.create('CompanyTogetherCombo');
    
    //是否已有线路下拉框
    var isHaveLineCombo = Ext.create('IsHaveLineCombo');
    
    //走货潜力行编辑器
    var SendGoodsPotentialRowEditing = Ext.create('Ext.grid.plugin.CellEditing', {  
        //设置单击一次鼠标，即可进行内容编辑
        clicksToEdit : 1
    }) ;
    
    
    //走货潜力录入表格
    Ext.define('SendGoodsPontentialAddGrid',{
        extend:'PopupGridPanel', 
        region:'center',
        height:150,
        store:Ext.create('SendGoodsPontentialGridStore'),
        columnLines:true,
        plugins:SendGoodsPotentialRowEditing ,
        defaults:{
            align:'center'
        },
        initComponent:function(){             
            var me = this;
            me.columns = [
              {//序号
                xtype:'rownumberer',
                header:i18n('i18n.Cycle.rownum'),
                width:50,
                align:'center'
              },{//始发城市
                header :i18n('i18n.returnVisit.comeFromCity'),
                flex:0.15,
                dataIndex:'comeFromCity',
                editor:{
                    xtype:'textfield'
                }
              },{//到达城市
                header :i18n('i18n.returnVisit.comeToCity'),
                flex:0.15,
                dataIndex:'comeToCity',
                editor:{
                    xtype:'textfield'
                }
              },{//货量潜力
                header :i18n('i18n.returnVisit.volumePotential'),
                flex:0.15,
                dataIndex:'volumePotential',
                field:volumePotentialCombo,
                renderer: Ext.util.Format.comboRenderer(volumePotentialCombo)
              },{//合作物流公司
                header :i18n('i18n.returnVisit.companyId'),
                flex:0.15,
                dataIndex:'companyId',
                field:companyTogetherCombo,
                renderer: Ext.util.Format.comboRenderer(companyTogetherCombo)
              },{//是否已有线路
                header :i18n('i18n.marketRecord.isExistRoad'),
                flex:0.15,
                dataIndex:'alreadyHaveLine',
                field:isHaveLineCombo,
                renderer: Ext.util.Format.comboRenderer(isHaveLineCombo)
              },{//合作意向
      			header : i18n('i18n.developSchedule.cooperatePurpose'),
    			dataIndex : 'cooperate',
    			align : 'center',
    			width : 90,
    			sortable : true,
    			editor:{
    				xtype:'combo',
    				typeAhead: true,
    				allowBlank:false,
    			    triggerAction: 'all',
    			    queryMode: 'local',
    			    store:getDataDictionaryByName(DataDictionary,'COOPERATION_INTENTION'),
    			    valueField: 'code',
    			    displayField: 'codeDesc',
    				forceSelection :true
    		    },
    			renderer: function(value){
    				return DButil.rendererDictionary(value,DataDictionary.COOPERATION_INTENTION); 
    			}
    		},{//适合我司承运
    			header : i18n('i18n.businessOpportunity.accord'),
    			dataIndex : 'accord',
    			align : 'center',
    			width : 90,
    			sortable : true,
    			editor:{
    				    xtype:'combo',
    					typeAhead: true,
    					allowBlank:false,
    				    triggerAction: 'all',
    				    queryMode: 'local',
    				    store:Ext.create('Ext.data.Store',{
    				    	fields:['code','codeDesc'],
    				    	data:[
    				    	  {code:'1',codeDesc:'是'},
    				    	  {code:'0',codeDesc:'否'}
    				    	]
    				    }),
    				    valueField: 'code',
    				    displayField: 'codeDesc',
    					forceSelection :true
    			},
    			renderer: function(value){
    				if(value=='1'){
    					return i18n('i18n.ChangeContactAffiliatedRelationView.yes');
    				}else if(value=='0'){
    					return i18n('i18n.ChangeContactAffiliatedRelationView.no');
    				}else{
    					return value;
    				}
    			}
    		},{//备注
                header :i18n('i18n.returnVisit.remark'),
                flex:0.25,
                dataIndex:'remark',
                editor:{
                    xtype:'textfield'
                }
              }
            ];
            this.callParent();
        }
    });
    
    /**
     * 走货潜力录入表格的Panel
     */
    Ext.define('SendGoodsPontentialAddGridPanel',{
        extend:'BasicVboxPanel',
        items : null,
        initComponent : function() {
            this.items = this.getItems();
            this.callParent();
        },
        getItems : function() {
            var me = this;
            return [
              {
                xtype:'toppanel',   
                items:[
                   {
                    xtype:'titlepanel',  
                    flex:1,
                    items:[
                       {//走货潜力
                        xtype:'displayfield',
                        value:i18n('i18n.returnVisit.sendGoodsPontential')
                    }]
                    },{
                        xtype:'btnpanel',  
                        defaultType:'button',
                        items:[
                           {//增加
                                text:i18n('i18n.returnVisit.add'),
                                id:'sendGoodsPontentialAddBtn',
                                handler:function(){
                                    //走货潜力增加按钮
                                    var panelStore = Ext.getCmp('sendGoodsPontentialAddGridId').getStore();
                                    var panelModel = Ext.getCmp('sendGoodsPontentialAddGridId').store.model.modelName;
                                    panelStore.add(Ext.create(panelModel));
                                }
                            },{//删除
                                text:i18n('i18n.returnVisit.delete'),
                                id:'sendGoodsPontentialDeleteBtn',
                                handler:function(){
                                    //走货潜力删除按钮
                                    var selection = Ext.getCmp('sendGoodsPontentialAddGridId').getSelectionModel().getSelection()[0];;
                                    var panelStore = Ext.getCmp('sendGoodsPontentialAddGridId').getStore();
                                    if (selection) {
                                        MessageUtil.showQuestionMes(i18n('i18n.developSchedule.isdelete'), function(e) {
                                            if (e == 'yes') {
                                                panelStore.remove(selection);
                                            }
                                        });
                                    }
                                }
                            }
                      ]
                    }
                ] 
            },{
                xtype:'basicpanel',     
                flex:1,
                items:[Ext.create('SendGoodsPontentialAddGrid',{'id':'sendGoodsPontentialAddGridId'})]
            }
          ];
        }
    });     
    
    /*
     * 跟踪日程制定form
     */
    Ext.define('MakeScheduleAddFormPanel',{
        extend:'TitleFormPanel', 
        items:null,
        initComponent:function(){
            this.items = this.getItems();
            this.callParent();
        },
        getItems:function(){
            var me = this;
            return [
                {
                xtype:'basicfiledset',
                title:i18n('i18n.returnVisit.scheduleSave'),
                layout:{
                    type:'table',
                    columns:3
                },
                defaults:{
                    labelWidth:80,
                    labelAlign:'right',
                    width:240,
                    margin:'4 5 4 5'
                },
                defaultType:'textfield',
                items:[//第一行记录
                   {//制定跟踪日程
                    xtype:'combo',
                    fieldLabel:i18n('i18n.developPlan.makeschedule'),
                    name:'isMakeSchedule',
                    id:'isMakeSchedule',
                    queryMode: 'local',
                    displayField:'value',
                    valueField:'name',
                    forceSelection :true,
                    editable:false,
                    value:'1',
                    store:Ext.create('Ext.data.Store', {
                        fields : ['name', 'value'],
                        data   : [
                            {name : '0',   value: '否'},
                            {name : '1',  value:'是'}
                        ]
                    }),
                    listeners:{
                        select:function(t){
                            if(t.getValue()=='1'){
                                Ext.getCmp("scheduleDate").enable();
                            }else{
                                Ext.getCmp("scheduleDate").setValue("");
                                Ext.getCmp("scheduleDate").disable();
                            }
                        }
                    }
                },{//跟踪时间
                    xtype:'datefield',
                    fieldLabel:i18n('i18n.returnVisit.schedule'),
                    name:'scheduleDate',
                    id:'scheduleDate',
                    disabled:true,
                    editable:false,
                    format:'Y-m-d',
                    minValue : new Date()
                },{//执行部门
                    fieldLabel:i18n('i18n.developPlan.executeDept'),
                    name:'makeDepartment',
                    id:'makeDepartment',
                    readOnly:true,
                    cls:'readonly',
                    value:User.deptName
                },//第二行记录
                {//执行人
                    fieldLabel:i18n('i18n.developPlan.executePersion'),
                    name:'makeName',
                    id:'makeName',
                    readOnly:true,
                    cls:'readonly',
                    value:User.empName
                }
                ]
             }
         ];
        }
    });
    
    /**
     * 页面提交、重置按钮Panel
     */
    Ext.define('SubmitResetBtnPanel',{
        extend:'BasicVboxPanel',
        items : null,
        initComponent : function() {
            this.items = this.getItems();
            this.callParent();
        },
        getItems : function() {
            var me = this;
            return [
            {
                xtype:'toppanel',   
                items:[
                       {
                        xtype:'btnpanel',  
                        defaultType:'button',
                        items:[
                           {//提交
                                text:i18n('i18n.marketRecord.submit'),
                                id:'submitBtn',
                                handler:function(btn){                                  
                                    btn.disable();
                                    //客户基本信息form非空校验
                                    var customerBasicInfoFormPanel = Ext.getCmp('customerBasicInfoFormPanelId').getForm();
                                    var contactMobile = Ext.getCmp('contactMobile').getValue();
                                    var contactPhone = Ext.getCmp('contactPhone').getValue();
                                    if(Ext.isEmpty(contactMobile) && Ext.isEmpty(contactPhone)){
                                        btn.enable();
                                        MessageUtil.showMessage(i18n('i18n.marketRecord.marketRecordNoTelAndMobile'));
                                        return false;                                           
                                    }
                                    
                                    var custLinkManName = Ext.getCmp('custLinkManName').getValue();
                                    if(Ext.isEmpty(custLinkManName)){
                                        btn.enable();
                                        MessageUtil.showMessage(i18n('i18n.marketRecord.marketRecordNoLinkName'));
                                        return false;                                           
                                    }
                                    
                                    //判断客户基本信息校验是否通过
                                    if(!customerBasicInfoFormPanel.isValid()){
                                        btn.enable();
                                        MessageUtil.showMessage(i18n('i18n.customerCallIn.validate'));
                                        return false;
                                    }
                                    
                                    //跟踪数据是否需要修改后再提交
                                    var canNotSubmit = false;
                                    //设置变量，记录行号
                                    var lineNumber = 1;
                            
                                    //获取客户需求录入表格
                                    var customerDemandAddGrid = Ext.getCmp('customerDemandAddGridId');
                                    var customerDemandAddGridStore = customerDemandAddGrid.store;
                                    
                                    //删除空数据
                                    customerDemandAddGridStore.each(function(record){                                       
                                        if(Ext.isEmpty(record.data.opinionType) && Ext.isEmpty(record.data.problem) && Ext.isEmpty(record.data.solution)){
                                            customerDemandAddGridStore.remove(record);
                                        }
                                        else if(Ext.isEmpty(record.data.opinionType) || Ext.isEmpty(record.data.problem) || Ext.isEmpty(record.data.solution)){
                                            btn.enable();
                                            MessageUtil.showMessage(i18n('i18n.marketRecord.marketRecordNeedNotNullPotentialCustomerInfo'));
                                            canNotSubmit = true;
                                            return false;
                                        }
                                        else{
                                            if(record.data.problem.length>200){
                                                btn.enable();
                                                MessageUtil.showInfoMes("第"+lineNumber+"行中“需求及问题”输入字符过长，最多输入200个字符！");
                                                canNotSubmit=true;
                                                return false;
                                            }
                                            if(record.data.solution.length>200){
                                                btn.enable();
                                                MessageUtil.showInfoMes("第"+lineNumber+"行中“需求问题解决办法”输入字符过长，最多输入200个字符！");
                                                canNotSubmit=true;
                                                return false;
                                            }
                                            lineNumber++;
                                        }
                                    });
                                    
                                    
                                    //校验客户需求，至少添加一条客户需求
                                    if(customerDemandAddGridStore.data.length==0){
                                        btn.enable();
                                        MessageUtil.showMessage(i18n('i18n.marketRecord.marketRecordNoCustomerDemand'));
                                        return false;                                                                                   
                                    }
                                                                            
                                    //获取走货潜力录入表格
                                    var sendGoodsPontentialAddGrid = Ext.getCmp('sendGoodsPontentialAddGridId');
                                    var sendGoodsPontentialAddGridStore = sendGoodsPontentialAddGrid.store;
                                    
                                    //设置变量，记录行号
                                    var lineNumberTwo = 1;
                                    //删除空数据
                                    sendGoodsPontentialAddGridStore.each(function(record){                                      
                                        if(Ext.isEmpty(record.data.comeFromCity) && Ext.isEmpty(record.data.comeToCity) && Ext.isEmpty(record.data.volumePotential)
                                                && Ext.isEmpty(record.data.companyId) && Ext.isEmpty(record.data.alreadyHaveLine)
                                                     && Ext.isEmpty(record.data.remark)){
                                            sendGoodsPontentialAddGridStore.remove(record);
                                        }else{
                                            if(record.data.comeFromCity.length>8){
                                                btn.enable();
                                                MessageUtil.showInfoMes("第"+lineNumberTwo+"行中“始发城市”输入字符过长，最多输入8个字符！");
                                                canNotSubmit=true;
                                                return false;
                                            }
                                            if(record.data.comeToCity.length>8){
                                                btn.enable();
                                                MessageUtil.showInfoMes("第"+lineNumberTwo+"行中“到达城市”输入字符过长，最多输入8个字符！");
                                                canNotSubmit=true;
                                                return false;
                                            }
                                            if(record.data.remark.length>200){
                                                btn.enable();
                                                MessageUtil.showInfoMes("第"+lineNumberTwo+"行中“备注”输入字符过长，最多输入200个字符！");
                                                canNotSubmit=true;
                                                return false;
                                            }
                                            lineNumberTwo++;
                                        }
                                    });
                                    
                                    if(canNotSubmit){
                                        return false;
                                    }
                                    
                                    
                                    //执行保存操作
                                    var successFn = function(json){
                                        clearCustomerInfo();
                                        btn.enable();
                                        MessageUtil.showInfoMes(i18n('i18n.PotentialCustEditView.saveSuccess'));
                                        
                                        //清空客户基本信息
                                        Ext.getCmp('customerBasicInfoFormPanelId').getForm().reset();
                                        
                                        //清空客户需求表格
                                        Ext.getCmp('customerDemandAddGridId').store.removeAll();
                                        
                                        //清空走货潜力表格
                                        Ext.getCmp('sendGoodsPontentialAddGridId').store.removeAll();
                                        
                                        //清空跟踪日程制定
                                        Ext.getCmp('makeScheduleAddFormPanelId').getForm().reset();
                                        Ext.getCmp('makeScheduleAddFormPanelId').getForm().findField('scheduleDate').disable();
                                    }
                                    
                                    var failureFn = function(json){
                                        btn.enable();
                                        MessageUtil.showErrorMes(json.message);
                                    };
                                    
                                    //客户需求列表
                                    var customerDemands = new Array();
                                    //走货潜力列表
                                    var sendGoodsPontentials = new Array();
                                    //将客户需求录入表格中的数据放入到需求列表数组中
                                    customerDemandAddGrid.store.each(function(record){
                                        customerDemands.push(record.data);
                                    });
                                    //将走货潜力录入表格中的数据放入到走货潜力列表数组中
                                    sendGoodsPontentialAddGrid.store.each(function(record){
                                        sendGoodsPontentials.push(record.data);
                                    });
                                    var customerBasicInfoForm = Ext.getCmp("customerBasicInfoFormPanelId");
                                    //获取二级行业
                                    var firstTrade = customerBasicInfoForm.down('form').getForm().findField('contactTrade').getValue();
                                    var secondTrade = customerBasicInfoForm.down('form').getForm().findField('secondTrade').getValue();
                                    var param = {
                                            'customerCallVO':{
                                                //客户基本信息
                                                'contactMobile':Ext.getCmp('contactMobile').getValue(),
                                                'contactPhone':Ext.getCmp('contactPhone').getValue(),
                                                'custLinkManName':Ext.getCmp('custLinkManName').getValue(),
                                                'custLinkManId':Ext.getCmp('custLinkManId').getValue(),
                                                'custName':Ext.getCmp('custName').getValue(),
                                                'contactJob':Ext.getCmp('contactJob').getValue(),
                                                //二级行业
                                                'contactTrade':firstTrade,
                                                'secondTrade':secondTrade,
                                                'busStatus':Ext.getCmp('busStatus').getValue(),
                                                'contactAddress':Ext.getCmp('contactAddress').getValue(),
                                                'contactCity':Ext.getCmp('contactCity').getValue(),
                                                'contactCityId':cityId,
                                                'contactType':Ext.getCmp('contactType').getValue(),
                                                'id':Ext.getCmp('id').getValue(),
                                                //业务类别 auth:李春雨
                                                'businessType':Ext.getCmp('businessType').getValue(),
                                                //跟踪日程制定信息
                                                'scheduleDate':Ext.isEmpty(Ext.getCmp('scheduleDate').getValue())==true?Ext.getCmp('scheduleDate').getValue():Ext.getCmp('scheduleDate').getValue().getTime()
                                            },
                                            'volPotentialList':sendGoodsPontentials,
                                            //客户意见
                                            'customerDemandList':customerDemands
                                    };
                                    MarketRecordData.prototype.saveMarketRecord(param, successFn, failureFn);
                                }
                            },{//重置
                                text:i18n('i18n.marketRecord.reset'),
                                id:'resetBtn',
                                handler:function(){
                                    //页面重置按钮
                                    //重置客户基本信息
                                    Ext.getCmp('customerBasicInfoFormPanelId').getForm().reset();                                   
                                    clearCustomerInfo();
                                    //重置客户需求表格
                                    Ext.getCmp('customerDemandAddGridId').store.removeAll();
                                    
                                    //重置走货潜力表格
                                    Ext.getCmp('sendGoodsPontentialAddGridId').store.removeAll();
                                    
                                    //重置跟踪日程制定
                                    Ext.getCmp('makeScheduleAddFormPanelId').getForm().reset();
                                    Ext.getCmp('makeScheduleAddFormPanelId').getForm().findField('scheduleDate').disable();
                                    Ext.getCmp('submitBtn').enable();
                                }
                         }
                        ]
                   }
                  ] 
            }
          ];
        }
    });

    /**
     * 客户来电录入整体布局panel
     */
    Ext.define('CustomerCallAddPanel', {
        extend : 'BasicPanel',
        layout : 'border',
        items :[
            {//客户基本信息录入面板
                xtype : 'basicpanel',
                region:'north',
                layout:'fit',
                items:[Ext.create('CustomerBasicInfoFormPanel',{id:'customerBasicInfoFormPanelId'})],
                height:160
            },{//客户需求录入grid
                xtype : 'basicpanel',
                region:'center',
                layout:'fit',
                items:[Ext.create('CustomerDemandAddGridPanel',{id:'customerDemandAddGridPanelId'})]
            },{
                xtype : 'basicpanel',
                region:'south',
                layout:'border',
                height:360,
                items:[
                   {//走货潜力录入表格
                       xtype : 'basicpanel',
                       region:'north',
                       layout:'fit',
                       items:[ Ext.create('SendGoodsPontentialAddGridPanel',{id:'sendGoodsPontentialAddGridPanelId'})],
                       height:230
                   },{//跟踪日程制定面板
                       xtype : 'basicpanel',
                       region:'center',
                       layout:'fit',
                       items:[Ext.create('MakeScheduleAddFormPanel',{id:'makeScheduleAddFormPanelId'})]
                   },{//提交重置按钮
                       xtype : 'basicpanel',
                       region:'south',
                       layout:'fit',
                       items:[Ext.create('SubmitResetBtnPanel',{id:'submitResetBtnPanelId'})],
                       height:30
                   }    
               ]
            }
        ]
    });
    
    /*
     * 新建一个viewport，用于显示客户来电录入整体布局panel
     * 肖红叶
     */
    var viewport=Ext.create('Ext.Viewport',{
        layout : 'border',
        autoScroll:true,
        items:[
           Ext.create('CustomerCallAddPanel',{region:'center'})
        ]
    });
    
    viewport.setAutoScroll(false);  
    viewport.doLayout();    
});

//创建一个函数，用于清空客户基本信息
function clearCustomerInfo(){
    Ext.getCmp('custLinkManId').setValue('');
    Ext.getCmp('custName').setValue('');
    Ext.getCmp('contactJob').setValue('');
    var customerBasicInfoForm = Ext.getCmp("customerBasicInfoFormPanelId");
    customerBasicInfoForm.down('form').getForm().findField('contactTrade').setValue('');
    customerBasicInfoForm.down('form').getForm().findField('secondTrade').setValue('');
    Ext.getCmp('busStatus').setValue('');
    Ext.getCmp('contactAddress').setValue('');
    Ext.getCmp('contactType').setValue('');
    Ext.getCmp('id').setValue('');
    //重置选择框 auth:李春雨
    Ext.getCmp('businessType').reset();
    Ext.getCmp('businessType').enable();
    Ext.getCmp("customerBasicInfoFormPanelId").down('form').getForm().findField('secondTrade').setReadOnly(false);
    Ext.getCmp("customerBasicInfoFormPanelId").down('form').getForm().findField('contactTrade').setReadOnly(false);
}