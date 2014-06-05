/**
 * 二级行业model
 */
Ext.define('SecondTradeModel',{
    extend:'Ext.data.Model',
    fields:[{
        // 父数据ID
        name : 'parentId',
        type : 'string'
    },{
        //代码类型
        name : 'codeType',
        type : 'string'
    },{
        //代码
        name:'code',
        type:'string'
    },{
        //代码类型描述
        name:'codeDesc',
        type:'string'
    },{
        //状态
        name:'status',
        type:'boolean'
    },{
        //代码序列
        name:'codeSeq',
        type:'int'
    },{
        //语言
        name:'language',
        type:'string'
    },{
        //失效时间
        name:'invaliTime',
        type:'Date',
        defalut : null,
        convert : DButil.changeLongToDate
    }]
}),

/**
 * 活动查询实体
 */
Ext.define('MarketActivityVO',{
    extend:'Ext.data.Model',
    fields:[{
        //id
        name : 'id',
        type : 'string'
    },{
        //创建时间
        name : 'createDate',
        type:'Date',
        defalut : null,
        convert : DButil.changeLongToDate
    },{
        //市场推广活动编码
        name:'activityCode',
        type:'string'
    },{
        //市场推广活动名称
        name:'activityName',
        type:'string'
    },{
        //活动类型
        name:'activityType',
        type:'string'
    },{
        //活动类别
        name:'activityCategory',
        type:'string'
    },{
        //活动开始时间
        name:'startTime',
        type:'Date',
        defalut : null,
        convert : DButil.changeLongToDate
    },{
        //活动结束时间
        name:'endTime',
        type:'Date',
        defalut : null,
        convert : DButil.changeLongToDate
    },{
        //活动状态
        name:'activityStatus',
        type:'string'
    },{
        //活动创建部门
        name:'deptId',
        type:'string'
    },{
        //创建人姓名
        name:'createUserName',
        type:'string'
    },{
        //客户群列表
        name:'clientBases',
        type:'string'
    },{
        //创建人
        name :'createUser',
        type:'string'
    }]
});


/**
 * 申请物品信息model
 */
Ext.define('ActivityOptionModel',{
    extend:'Ext.data.Model',
    fields:[{
        // 营销活动ID
        name:'activityId',
        type:'string'
    },{
        // 物料类型
        name:'infoType',
        type:'string'
    },{
        // 字段类型
        name:'type',
        type:'string'
    },{
        // 字段值
        name:'value',
        type:'string'
    }]
});


/**
 * 开展部门model
 */
Ext.define('ActivityDeptModel',{
    extend:'Ext.data.Model',
    fields:[{
        //市场推广活动ID
        name:'activityId',
        type:'string'
    },{
        //部门标杆编码
        name:'deptStandardCode',
        type:'string'
    }]
});

/**
 * 线路区域列表model
 */
Ext.define('LineDeptModel',{
    extend:'Ext.data.Model',
    fields:[{
        // 对应业务表ID
        name:'conditionId',
        type:'string'
    },{
        // 对应业务类型
        name:'conditionType',
        type:'string'
    },{
        // 出发部门名称
        name:'leavedDeptName',
        type:'string'
    },{
        // 出发部门标杆编码
        name:'leaveDeptCode',
        type:'string'
    },{
        // 到达部门名称
        name:'arriveDeptName',
        type:'string'
    },{
        // 到达部门标杆编码
        name:'arriveDeptCode',
        type:'string'
    }]
});

/**
 * 客户群列表
 */
Ext.define('ClientBaseModel',{
    extend:'Ext.data.Model',
    fields:[{
        // 客户数量
        name:'clientNumber',
        type:'string'
    },{
        // 客户群名称
        name:'clientBaseName',
        type:'string'
    },{
        // 客户创建起始时间
        name:'clientCreateStartTime',
        type:'Date',
        defalut : null,
        convert : DButil.changeLongToDate
    },{
        // 客户创建结束时间
        name:'clientCreateEndTime',
        type:'Date',
        defalut : null,
        convert : DButil.changeLongToDate
    },{
        // 线路类型
        name:'lineType',
        type:'string'
    },{
        // 客户群状态
        name:'clientBaseStatus',
        type:'string'
    },{
        // 一级行业
        name:'trades',
        type:'Array'
    },{
        // 二级行业
        name:'secondTrades',
        type:'string'
    },{
        // 客户类型
        name:'clientTypes',
        type:'string'
    },{
        // 货量潜力
        name:'clientlatents',
        type:'string'
    },{
        // 客户等级
        name:'clientGrades',
        type:'string'
    },{
        // 客户来源
        name:'clientSources',
        type:'string'
    },{
        // 客户属性
        name:'clientPropertys',
        type:'string'
    },{
        // 提货方式
        name:'takeMethods',
        type:'string'
    },{
        // 合作意向
        name:'cooperateWills',
        type:'string'
    }]
});

/**
 * 推广活动主Model
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
        //活动开始时间
        name : 'startTime',
        type:'Date',
        defalut : null,
        convert : DButil.changeLongToDate
    },{
        //结束时间
        name : 'endTime',
        type:'Date',
        defalut : null,
        convert : DButil.changeLongToDate
    },{
        //创建时间
        name : 'createDate',
        type:'Date',
        defalut : null,
        convert : DButil.changeLongToDate
    },{
        //创建人
        name : 'createUser',
        type : 'string'
    },{
        //修改时间
        name : 'modifyDate',
        type:'Date',
        defalut : null,
        convert : DButil.changeLongToDate
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

/**
 * 文件上传model
 */
Ext.define('FileInfoModel', {
    extend : 'Ext.data.Model',
    fields : [ {
        //id
        name : 'id',
        type : 'String'
    },{
        //来源ID
        name : 'sourceId',
        type : 'string'
    },{
        //来源类型
        name : 'sourceType',
        type : 'string'
    },{
        //文件名称
        name : 'fileName',
        type : 'string'
    },{
        // 文件类型
        name : 'fileType',
        type : 'string'
    },{
        //保存路径
        name : 'savePath',
        type : 'string'
    },{
        //文件大小
        name : 'fileSize',
        type : 'long'
    },{
        //创建部门
        name : 'createDept',
        type : 'string'
    },{
        //创建日期
        name : 'createDate',
        type:'Date',
        defalut : null,
        convert : DButil.changeLongToDate
    },{
        //创建人
        name : 'createUser',
        type : 'string'
    },{
        //创建人姓名
        name : 'createName',
        type : 'string'
    },{
        //修改日期
        name : 'modifyDate',
        type:'Date',
        defalut : null,
        convert : DButil.changeLongToDate
    },{
        //修改人
        name : 'modifyUser',
        type : 'string'
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
        // 部门名称
        name : 'deptName',
        type : 'string'
    },{
        // 标杆编码
        name : 'standardCode',
        type : 'string'
    }]
});

/**
 * 部门树Model
 */
Ext.define('DeptTreeModel', {
    extend : 'Ext.data.Model',
    fields : [{
        // id
        name : 'id',
        type : 'string'
    },{
        // 部门名称
        name : 'text',
        type : 'string'
    },{
        // 标杆编码
        name : 'deptStandardCode',
        type : 'string'
    },{
        //是否可选择
        name : 'canSelect'
    }]
});
/**
 * 市场推广活动关联客户群
 */
Ext.define('ActivityClientBase',{
    extend : 'Ext.data.Model',
    fields : [{
        // 客户群ID
        name : 'clientBaseId',
        type : 'string'
    },{
        // 市场推广活动ID
        name : 'activityId',
        type : 'string'
    },{
        // 客户群名称
        name : 'clientBaseName',
        type : 'string'
    },{
        // 客户群状态
        name : 'clientBaseStatus',
        type : 'string'
    },{
        //客户群数量
        name : 'clientNum'
    },{
        //客户群归属部门ID
        name : 'deptId'
    },{
        //客户群部门名称
        name : 'deptName'
    },{
        //回访计划开始时间
        name : 'planStartTime',
        type:'Date',
        defalut : null,
        convert : DButil.changeLongToDate
    },{
        //回访计划结束时间
        name : 'planEndTime',
        type:'Date',
        defalut : null,
        convert : DButil.changeLongToDate
    },{
        //创建人
        name : 'createUserName'
    },{
        //创建时间
        name : 'createDate'
    },{
        name : 'id'
    }]
});

/**
 * 联系人model
 */
Ext.define('UserInfo',{
    extend : 'Ext.data.Model',
    fields : [{
        // 用户Id
        name : 'userId',
        type : 'string'
    },{
        // 人员姓名
        name : 'empName',
        type : 'string'
    },{
        //手机号
        name : 'mobilePhone',
        type : 'string'
    },{
        //工号
        name : 'empCode',
        type : 'string'
    }]
});
