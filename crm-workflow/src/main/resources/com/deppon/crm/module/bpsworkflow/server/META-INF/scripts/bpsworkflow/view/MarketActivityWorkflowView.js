/**
 * 市场推广活动工作流View
 */

Ext.define('MarketActivityDetailForm',{
    extend:'PopTitleFormPanel',
    frame:true,
    cls:'reanOnlyPanel',
    margin:'3 0 3 0',
    flex : 1,
    items:[{
        xtype:'container',
        defaultType:'displayfield',
        width:910,
        defaults:{
            width:900,
            readOnly : true
        },
        layout:{
            type:'table',
            columns:1
        },
        items:[{
            xtype:'basicfiledset', //        '基本信息',
            title:i18n('i18n.MarketActivityWorkFlow.basicInfo'),//活动基本信息
            height : 350,
            autoScroll : true,
//          columnWidth: 0.5,
            defaultType: 'displayfield',
            padding : '0 0 20 0',
            layout:{
                type:'table',
                columns:4    //列数
            },
            defaults:{
                width:190,
                lableWidth:80,
                readOnly : true
            },
            
            items :[{
                name:'activityCategory', //活动类别
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.activityCategory')
            },{
                name:'activityType',//活动类型
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.activityType')
            },{
                name:'activityName',//活动名称
                fieldLabel: i18n('i18n.MarketActivityWorkFlow.activityName')
            },{
                name: 'activityCode',//活动编码
                fieldLabel: i18n('i18n.MarketActivityWorkFlow.activityCode')
            },{
                name : 'proposerName',//申请人姓名
                fieldLabel: i18n('i18n.MarketActivityWorkFlow.proposerName')
            },{
                name:'proposerCode',// 申请人工号
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.proposerCode')
            },{
                name:'startTime',// 活动开始时间
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.startTime')
            },{
                name:'endTime',// 活动结束时间
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.endTime')
            },{
                name:'topic',// 活动主题
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.topic'),
                colspan:4,
                width : 800
            
            },{
                name:'content',// 活动内容
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.content'),
                colspan:4,
                width : 800
            },{
                name:'slogan',// 对外宣传语
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.slogan'),
                colspan:4,
                width : 800
            },{
                name:'target1',// 第一月目标
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.target1'),
                colspan:2,
                width:370
            },{
                name:'target2',// 第二月目标
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.target2')
            },{
                name:'target3',// 第三月目标
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.target3')
            },{
                name:'contactsName',//对接人
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.contactsName')
            },{
                name:'contactsTel',//对接人电话
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.contactsTel')
            },{
                name:'applyReason',//申请事由
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.applyReason'),
                colspan:2,
                width:370
            }]
        },{ 
            xtype:'basicfiledset',
            title: i18n('i18n.MarketActivityWorkFlow.materialApply'),//物料申请
            height:200,
            defaultType: 'displayfield',      
            layout:{
                type:'table',
                columns:3
            },
            defaults:{
                width:280,
                margin:'0 12 0 0',
                readOnly : true
            },
            items :[{
                xtype: 'grid',
                autoScroll : true,
                height:180,
                width : 720,
                id : 'materialGridPanelId',
                store : Ext.create('ActivityOptionDataStore',{id : 'materialDataStoreId'}),
                columns: [
               { xtype:'rownumberer',width:40,header:i18n('i18n.MarketActivityWorkFlow.orderNumber')},   //序号 
               //物品名称
               { header: '<center>'+i18n('i18n.MarketActivityWorkFlow.materialName')+'</center>', dataIndex: 'type',sortable:false, flex: 0.5 },
               //数量
               { header: '<center>'+i18n('i18n.MarketActivityWorkFlow.materialNum')+'</center>', dataIndex: 'value',sortable:false, flex: 0.5 }
                        ]
            }]
        },{
            xtype:'basicfiledset',
            title: i18n('i18n.MarketActivityWorkFlow.ActivityConstraint'),//活动参与条件
            autoScroll : true,
            defaultType: 'displayfield',
            height:300,
            layout:{
                type:'table',
                columns:4
            },
            defaults:{
                width:180,
                readOnly : true
            },
            items :[{
                fieldLabel : i18n('i18n.MarketActivityWorkFlow.custTrade'),//客户行业
                name : 'custTrade',
                colspan:4,
                width : 800
            },{
                name:'secondTrade',
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.secondTrade'),//二级行业
                colspan:4,
                width : 800
            },{
                name:'productType',
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.productType'),//产品类型
                colspan:4,
                width : 800
            },{
                name:'itemNames',
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.itemNames'),//开单品名
                colspan:4,
                width : 800
            },{
                fieldLabel : i18n('i18n.MarketActivityWorkFlow.orderSource'),//订单来源
                name : 'orderSource',
                colspan:4,
                width : 800
            },{
                name:'minBillAmt',
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.minBillAmt')//开单金额
            },{
                name:'minCargoWeight',
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.minCargoWeight')//货物重量
            },{
                name:'lineRequest',
                width:220,
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.lineRequest')//线路要求
            },{
                name:'minCargoVolume',
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.minCargoVolume')//货物体积
            },{
                xtype: 'grid',
                height:120,
                colspan:2,
                width:350,
                autoScroll : true,
                id:'deptGridPanelId',
                store:Ext.create('DeptStore'),
                columns: [{ 
                    xtype:'rownumberer',
                    width:40,
                    header:i18n('i18n.MarketActivityWorkFlow.orderNumber')//序号
                },{ 
                    header: '<center>'+i18n('i18n.MarketActivityWorkFlow.deptName')+'</center>', //开展部门
                    dataIndex: 'deptName',
                    sortable:false, 
                    flex: 0.5 
               }]
            },{
                xtype: 'grid',
                height:120,
                width : 350,
                autoScroll : true,
                colspan:2,
                id : 'lineGridPanelId',
                store:Ext.create('UseWalkGoodsLineStore'),
                columns: [
                   { xtype:'rownumberer',width:40,header:i18n('i18n.MarketActivityWorkFlow.orderNumber')},//序号
                   //出发区域
                   { header: '<center>'+i18n('i18n.MarketActivityWorkFlow.leavedDeptName')+'</center>', dataIndex: 'leavedDeptName',sortable:false, flex: 0.5 },
                   //到达区域
                   { header: '<center>'+i18n('i18n.MarketActivityWorkFlow.arriveDeptName')+'</center>', dataIndex: 'arriveDeptName',sortable:false, flex: 0.5 }
                ]
            }]
        },{
            xtype:'basicfiledset',
            title: i18n('i18n.MarketActivityWorkFlow.preferInfo'),//折扣信息
            height:300,
            defaultType: 'displayfield',      
            layout:{
                type:'table',
                columns:4
            },
            defaults:{
                margin:'0 25 0 0',
                readOnly : true
            },
            items :[{
                name:'preferType',
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.preferType'),//优惠方式
                width:390,
                colspan:2
            },{
                name:'workFlowNum',
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.workFlowNum'),//折扣工作流号
                width:390,
                colspan:2
            },{
                name:'createRule',
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.createRule'),//生成条件
                colspan:4,
                width : 800
            },{
                name:'useRule',
                fieldLabel:i18n('i18n.MarketActivityWorkFlow.useRule'),//使用条件
                colspan:4,
                width : 800
            },{
                xtype: 'grid',
                colspan:4,
                height:180,
                width : 720,
                margin : '5 5 5 50',
                autoScroll : true,
                id:'couponGridPanelId',
                store : Ext.create('ActivityOptionDataStore',{id : 'preferentialDataStoreId'}),
                columns: [
                   { xtype:'rownumberer',width:40,header:i18n('i18n.MarketActivityWorkFlow.orderNumber')},//序号
                   //优惠产品
                   { 
                        header: '<center>'+i18n('i18n.MarketActivityWorkFlow.preferProduct')+'</center>', 
                        dataIndex: 'type',
                        sortable:false, 
                        flex: 0.5 ,
                        renderer:function(value){
                            return rendererDictionary(value,DataDictionary.DISCOUNT_PRODUCTS);
                        }
                   },
                   //折扣比例
                   { header: '<center>'+i18n('i18n.MarketActivityWorkFlow.ratio')+'(%)'+'</center>', dataIndex: 'value',sortable:false, flex: 0.5 }
                ]
            }]
        },{
            xtype:'basicfiledset',
            title: i18n('i18n.MarketActivityWorkFlow.clentBase'),//客户群
            defaultType: 'displayfield',
            height:200,
            layout:{
                type:'table',
                columns:1
            },
            defaults:{
                width:860,
                lableWidth:80,
                readOnly : true
            },
            items :[{
                xtype: 'grid',
                id : 'clientBasePanelId',
                height:180,
                autoScroll : true,
                width:720,
                columns: [
               { xtype:'rownumberer',width:40,header:i18n('i18n.MarketActivityWorkFlow.orderNumber')},//序号
               //客户群名称
               { header: '<center>'+i18n('i18n.MarketActivityWorkFlow.clentBaseName')+'</center>', dataIndex: 'clientBaseName',sortable:false, flex: 0.5 },
               //客户群状态
               { header: '<center>'+i18n('i18n.MarketActivityWorkFlow.clientBaseStatus')+'</center>', 
                 dataIndex: 'clientBaseStatus',
                 sortable:false, 
                 flex: 0.5, 
                 renderer:function(value){
                    return rendererDictionary(value,DataDictionary.CUST_CONDITION_STATUS);
                 }
                },
                //创建人
               { header: '<center>'+i18n('i18n.MarketActivityWorkFlow.createUserName')+'</center>', dataIndex: 'createUserName',sortable:false, flex: 0.5 },
               //创建时间
               { header: '<center>'+i18n('i18n.MarketActivityWorkFlow.createDate')+'</center>', 
                 dataIndex: 'createDate',
                 sortable:false, 
                 flex: 0.5,
                 renderer : function(value,metaData,record){
                     return DButil.renderDate(value);
                 }
               },
               //
               { header: '<center>'+i18n('i18n.MarketActivityWorkFlow.createDeptName')+'</center>', dataIndex: 'deptName',sortable:false, flex: 0.5 }]
            }]
        },{
            xtype:'basicfiledset',
            title: i18n('i18n.MarketActivityWorkFlow.accessory'),//附件
            defaultType: 'displayfield',
            height:200,
            layout:{
                type:'table',
                columns:1
            },
            defaults:{
                width:860,
                lableWidth:80,
                readOnly : true
            },
            items :[{
                id : 'accessoryPanelId',
                xtype: 'grid',
                height:180,
                autoScroll : true,
                store : Ext.create('FileInfoStore'),
                
                columns: [{ 
                    header: i18n('i18n.MarketActivityWorkFlow.fileName'),//文件名
                    dataIndex: 'fileName',
                    flex : 1
                },{ 
                    header: i18n('i18n.MarketActivityWorkFlow.uploadDate'), //上传时间
                    dataIndex: 'createDate' ,
                    flex : 1,
                    renderer : function(value,metaData,record){
                        return DButil.renderDate(value);
                    }
                },{ 
                    header: i18n('i18n.MarketActivityWorkFlow.uploadUser'), //上传人
                    dataIndex: 'createUser' ,
                    flex : 1
                },{
                    header : i18n('i18n.MarketActivityWorkFlow.operate'),//操作
                    name : 'operator',
                    renderer:function(value,metaData,record ,rowIndex ,colIndex ,store ,view ){
                        return '<a onclick="file_download('+rowIndex+');" href="javascript:void(0);">下载</a>';
                    }
                }
                ]
            }]
        }  
        ]}  
        ],
        getRenderFun: function(t){
             var mask=new Ext.LoadMask(Ext.getBody(), {msg:"loading..." });
             mask.show();
             var param = {busino:getUrlVars('busino'),processDefName:getUrlVars('processDefName')};
                var successFn = function(json){
                    var ci=json.marketActivity;
                    if(!ci){
                        MessageUtil.showErrorMes(i18n('i18n.workflow.workflowNotExist'));
                        mask.hide();
                        return;
                    }
                    var marketActivity = new ActivityMainModel();
                    marketActivity.set(json.marketActivity);
                    var form=t.getForm();
//                  //处理空值
//                  var fv=form.getValues(),i,v;
//                  for (i in fv) {
//                      v = fv[i];
//                      if (Ext.isString(v)&&!Ext.String.trim(v)) {
//                         form.findField(i).setValue(i18n('i18n.workflow.none')); 
//                      }
//                      }
                    form.loadRecord(marketActivity);
                    //开始时间
                    form.findField('startTime').setValue(DpUtil.renderDate(json.marketActivity.startTime));
                    //结束时间
                    form.findField('endTime').setValue(DpUtil.renderDate(json.marketActivity.endTime));
                    var arrayData = new Array();
                    //活动类别
                    arrayData = marketActivity.get('activityCategory');
                    var temp = dictionaryToValue(arrayData,DataDictionary.ACTIVITY_CATEGORY);
                    form.findField('activityCategory').setValue(temp);
                    //活动类型
                    arrayData = marketActivity.get('activityType');
                    temp = dictionaryToValue(arrayData,DataDictionary.ACTIVITY_TYPE);
                    form.findField('activityType').setValue(temp);
                    //优惠方式
                    arrayData = marketActivity.get('preferType');
                    temp = dictionaryToValue(arrayData,DataDictionary.PREFERENTIAL_WAY);
                    form.findField('preferType').setValue(temp);
                    //一级行业
                    arrayData = marketActivity.get('custTrade');
                    temp = dictionaryToValue(arrayData,DataDictionary.TRADE);
                    form.findField('custTrade').setValue(temp);
                    //二级行业
                    var secondTrade = form.findField('secondTrade')
                    var successFn = function(json){
                        secondTrade.forceSelection=false;//二级行业
                        secondTrade.setValue(json.secondTrade.toString());
                        secondTrade.forceSelection=true;//二级行业
                    }
                    var failureFn = function(json){
                        MessageUtil.showErrorMes(json.message);
                        return false;
                    }
                    workflowData.searchSecondTradesToString({'conditionId' :json.marketActivity.id,'conditionType' : "ACTIVITY"},successFn,failureFn) 
//                    arrayData = marketActivity.get('secondTrade');
//                    temp = dictionaryToValue(arrayData,DataDictionary.SECOND_TRADE);
//                    form.findField('secondTrade').setValue(temp);
                    //产品类型
                    arrayData = marketActivity.get('productType');
                    temp = dictionaryToValue(arrayData,DataDictionary.CLIENTBASE_PRODUCT_TYPE);
                    form.findField('productType').setValue(temp);
                    //订单来源
                    arrayData = marketActivity.get('orderSource');
                    temp = dictionaryToValue(arrayData,'order');
                    form.findField('orderSource').setValue(temp);
                    //线路要求
                    arrayData = marketActivity.get('lineRequest');
                    temp = dictionaryToValue(arrayData,DataDictionary.LINE_TYPE);
                    form.findField('lineRequest').setValue(temp);
                    //开单金额 minBillAmt maxBillAmt
                    form.findField('minBillAmt').setValue(Ext.isEmpty(marketActivity.get('minBillAmt')) ? '' :marketActivity.get('minBillAmt')+'-'+marketActivity.get('maxBillAmt'));
                    //货物重量minCargoWeight maxCargoWeight
                    form.findField('minCargoWeight').setValue(Ext.isEmpty(marketActivity.get('minCargoWeight')) ? '' :marketActivity.get('minCargoWeight')+'-'+marketActivity.get('maxCargoWeight'));
                    //货物体积 minCargoVolume maxCargoVolume
                    form.findField('minCargoVolume').setValue(Ext.isEmpty(marketActivity.get('minCargoVolume')) ? '' :marketActivity.get('minCargoVolume')+'-'+marketActivity.get('maxCargoVolume'));
                    //物料
                    Ext.getCmp('materialGridPanelId').getStore().loadData(json.marketActivity.materialInfo);
                    //加载线路数据到前台
                    Ext.getCmp('lineGridPanelId').getStore().loadData(json.marketActivity.lineDepts);
                    var couponGrid = Ext.getCmp('couponGridPanelId');
                    if(json.marketActivity.couponInfo.length!=0){
                        //优惠券
                        //抵扣类型  优惠券数量
                        couponGrid.columns[1].setText(i18n('i18n.MarketActivityWorkFlow.discountType'));
                        couponGrid.columns[2].setText(i18n('i18n.MarketActivityWorkFlow.couponNum'));
                        couponGrid.ownerCt.down('displayfield[name=workFlowNum]').hide();
                        couponGrid.ownerCt.down('displayfield[name=createRule]').show();
                        couponGrid.ownerCt.down('displayfield[name=useRule]').show();
                        couponGrid.getStore().loadData(json.marketActivity.couponInfo);
                    }else{
                        //折扣
                        //优惠产品 折扣比例 
                        couponGrid.columns[1].setText(i18n('i18n.MarketActivityWorkFlow.preferProduct'));
                        couponGrid.columns[2].setText(i18n('i18n.MarketActivityWorkFlow.ratio'))
                        couponGrid.ownerCt.down('displayfield[name=workFlowNum]').show();
                        couponGrid.ownerCt.down('displayfield[name=createRule]').hide();
                        couponGrid.ownerCt.down('displayfield[name=useRule]').hide();
                        couponGrid.getStore().loadData(json.marketActivity.discountInfo);
                        if(!Ext.isEmpty(json.marketActivity.workFlowNum)){
                            couponGrid.ownerCt.down('displayfield[name=workFlowNum]').setValue(json.marketActivity.workFlowNum);
                        }
                    }
                    //加载部门数据到前台
                    Ext.getCmp('deptGridPanelId').getStore().loadData(json.marketActivity.activityDepts);
                    //客户群s
                    Ext.getCmp('clientBasePanelId').getStore().loadData(json.marketActivity.clientBase);
                    //上传附件
                    Ext.getCmp('accessoryPanelId').getStore().loadData(json.marketActivity.files);
                    mask.hide();
                };
                var failFn = function(json){
                    mask.hide();
                    MessageUtil.showErrorMes(i18n('i18n.workflow.getWorkflowDetailFail'));
                };
            workflowData.findMarketActivityByWorkNo(param,successFn,failFn);
        }
        
});

//多选数据字典中的值转换成描述
function dictionaryToValue(arrayData,dictionnary){
    if(Ext.isEmpty(arrayData)){
        return '';
    }
    var temp = '';
    if(typeof(arrayData) == 'string'){
        temp = rendererDictionary(arrayData,dictionnary);
    }else if(dictionnary=='order'){
        for(var i = 0 ; i < arrayData.length ; i++){
            temp += orderValue(arrayData[i]);
            temp += (i==arrayData.length-1 ? '':',');
        }
    }else{
        for(var i = 0 ; i < arrayData.length ; i++){
            temp += rendererDictionary(arrayData[i],dictionnary);
            temp += (i==arrayData.length-1 ? '':',');
        }
    }
    return temp;
}

//多选订单来源中的值转换成描述
function orderValue(value){
    if (!Ext.isEmpty(value) && !Ext.isEmpty(getOrderResourcesStore().data.items)) {
        for ( var i = 0; i < getOrderResourcesStore().data.items.length; i++) {
            if (value == getOrderResourcesStore().data.items[i].get('code')) {
                 return getOrderResourcesStore().data.items[i].get('codeDesc');
            }
        }
    } else {
       return '';
    }
}

//下载文件
function file_download(rowIndex){
    //是否要下载此文件？
    MessageUtil.showQuestionMes(i18n('i18n.MarketActivityWorkFlow.seleteSuccess'), function(e){ 
        var store = Ext.getCmp('accessoryPanelId').getStore();
        var fileInfoModel = new FileInfoModel();
        fileInfoModel.set(store.getAt(rowIndex).data);
        if (e == 'yes') {
            window.location.href="../common/downLoad.action?fileName="+fileInfoModel.get('fileName')+"&inputPath="+fileInfoModel.get('savePath');
        }
    });
}