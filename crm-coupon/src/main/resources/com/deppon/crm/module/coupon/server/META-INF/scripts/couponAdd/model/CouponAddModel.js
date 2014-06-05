/**
 * 线路区域要求Model
 */
Ext.define('GoodsLineModel', {
    extend : 'Ext.data.Model',
    fields : [{
        // 出发外场、发货区域 ID
        name : 'beginDeptOrCityId'
    },{
        // 出发外场、发货区域 名称
        name : 'beginDeptOrCityName'
    },{
        // 到达外场、到货区域 ID
        name : 'endDeptOrCityId'
    },{
        // 到达外场、到货区域 名称
        name : 'endDeptOrCityName'
    },{
        // 线路区域要求   (0、1、2、3)空、走货线路、发货区域、到达区域
        name : 'regdemand'
    }]
});

/**
 * 新增营销计划-发券规则-自动返券金额
 */
Ext.define('AutoCouponCostModel', {
    extend : 'Ext.data.Model',
    fields : [{
        // 自动返券ID
        name : 'couponAutoId'
    },{
        // 费用类型
        name : 'costType'
    },{
        // 金额下限
        name : 'costDown'
    },{
        // 返券金额
        name : 'coupoCost'
    }]
});

/**
 * 优惠券类型
 */
Ext.define('CommonDictionaryModel', {
    extend : 'Ext.data.Model',
    fields : [{
        // code
        name : 'code'
    },{
        // 描述
        name : 'codeDesc'
    }]
});

/**
 * 获取部门Model
 */
Ext.define('DeptModel', {
    extend : 'Ext.data.Model',
    fields : [{
        // id
        name : 'id',
        type : 'string'
    },{
        // 姓名
        name : 'deptName',
        type : 'string'
    },{
        // 标杆编码
        name : 'standardCode',
        type : 'string'
    }]
});



/**
 * 抵扣类型Model
 * auth：李春雨
 */
Ext.define('ActivityOptionModel', {
    extend : 'Ext.data.Model',
    fields : [ {
        //ID
        name : 'id',
        type : 'String'
    },{
        //营销活动ID
        name : 'activityId',
        type : 'string'
    }, {
        //物料类型
        name : 'infoType',
        type : 'string'
    },{
        //活动类型
        name : 'type',
        type : 'string'
    },{
        //活动类别
        name : 'value',
        type : 'string'
    },{
        //活动类型描述
        name : 'codeDesc',
        type : 'string'
    },{
        //活动类型描述
        name : 'code',
        type : 'string'
    }]
});

/**
 * 推广活动主Model
 * auth：李春雨
 */
Ext.define('ActivityMainModel', {
    extend : 'Ext.data.Model',
    fields : [ {
        //ID
        name : 'id',
        type : 'String'
    },{
        // code
        name : 'activityCode',
        type : 'string'
    }, {
        //市场推广活动名称
        name : 'activityName',
        type : 'string'
    },{
        //活动类型
        name : 'activityType',
        type : 'string'
    },{
        //活动类别
        name : 'activityCategory',
        type : 'string'
    },{
        //申请人姓名
        name : 'proposerName',
        type : 'string'
    },{
        //申请人工号
        name : 'proposerCode',
        type : 'string'
    },{
        //创建人
        name : 'createUser',
        type : 'string'
    },{
        //修改人
        name : 'modifyUser',
        type : 'string'
    },{
        //活动主题
        name : 'topic',
        type : 'string'
    },{
        //活动内容
        name : 'content',
        type : 'string'
    },{
        //对外宣传语
        name : 'slogan',
        type : 'string'
    },{
        //申请事由
        name : 'applyReason',
        type : 'string'
    },{
        //第一个月目标
        name : 'target1',
        type : 'string'
    },{
        //第二个月目标
        name : 'target2',
        type : 'string'
    },{
        //第三个月目标
        name : 'target3',
        type : 'string'
    },{
        //对接人
        name : 'contactsId',
        type : 'string'
    },{
        //对接人姓名
        name : 'contactsName',
        type : 'string'
    },{
        //对接人联系电话
        name : 'contactsTel',
        type : 'string'
    },{
        //客户一级行业行业
        name : 'custTrade',
        type : 'Array'
    },{
        //客户二级行业
        name : 'secondTrade',
        type : 'Array'
    },{
        //产品类型
        name : 'productType',
        type : 'Array'
    },{
        //开单品名
        name : 'itemNames',
        type : 'string'
    },{
        //开单金额起始值
        name : 'minBillAmt',
        type : 'string'
    },{
        //开单金额结束值
        name : 'maxBillAmt',
        type : 'string'
    },{
        //活动类型
        name : 'activityType',
        type : 'string'
    },{
        //货物重量起始值
        name : 'minCargoWeight',
        type : 'string'
    },{
        //货物重量结束值
        name : 'maxCargoWeight',
        type : 'string'
    },{
        //货物体积起始值
        name : 'minCargoVolume',
        type : 'string'
    },{
        //货物体积结束值
        name : 'maxCargoVolume',
        type : 'string'
    },{
        //订单来源
        name : 'orderSource',
        type : 'Array'
    },{
        //开展部门数量
        name : 'deptNum',
        type : 'string'
    },{
        //线路区域要求
        name : 'lineRequest',
        type : 'string'
    },{
        //优惠类型
        name : 'preferType',
        type : 'string'
    },{
        //折扣工作流号
        name : 'workFlowNum',
        type : 'string'
    },{
        //优惠券生成条件
        name : 'createRule',
        type : 'string'
    },{
        //优惠券使用条件
        name : 'useRule',
        type : 'string'
    },{
        //市场推广活动状态
        name : 'activityStatus',
        type : 'string'
    },{
        //申请物品信息
        name : 'materialInfo',
        type : 'Array'
    },{
        //折扣信息
        name : 'discountInfo',
        type : 'Array'
    },{
        //优惠券信息
        name : 'couponInfo',
        type : 'Array'
    }]
});