/**
 * 保存页码的model
 */
Ext.define('PageSizeModel',{
    extend:'Ext.data.Model',
    fields:[{
        // 页码
        name:'value',
        type:'string'
    }]
});
/**
 * 数据字典Model
 */
Ext.define('DataDictionaryModel', {
    extend : 'Ext.data.Model',
    fields : [ {
        // code
        name : 'code',
        type : 'string'
    }, {
        // 详细描述
        name : 'codeDesc',
        type : 'string'
    } ]
});

/**
 * 客户需求grid的model
 */
Ext.define('CustomerDemandModel',{
    extend:'Ext.data.Model',
    fields:[{
        // 需求分类
        name:'opinionType',
        type:'string'
    },{
        // 需求及问题
        name:'problem',
        type:'string'
    },{
        // 需求问题解决办法
        name:'solution',
        type:'string'
    }]
});


/**
 * 走货潜力gird的model
 */
Ext.define('SendGoodsPontentialGridModel',{
    extend:'Ext.data.Model',
    fields:[{
        // 始发城市
        name:'comeFromCity',
        type:'string'
    },{
        // 到达城市
        name:'comeToCity',
        type:'string'
    },{
        // 货量潜力
        name:'volumePotential',
        type:'string'
    },{
        // 合作物流公司
        name:'companyId',
        type:'string'
    },{
        //是否已有路线
        name:'alreadyHaveLine',
        type:'string'
    },{
        // 备注
        name:'remark',
        type:'string'
    },{
		//合作意向
		name:'cooperate'
	},{
		//适合我司承运
		name:'accord'
	}]
});


/**
 * 客户基本信息form的model
 */
Ext.define('CustomerBasicInfoModel',{
    extend:'Ext.data.Model',
    fields:[{
        // 联系人手机
        name:'contactMobile',
        type:'string'
    },{
        // 联系人电话
        name:'contactPhone',
        type:'string'
    },{
        // 联系人姓名
        name:'custLinkManName',
        type:'string'
    },{
        // 联系人ID
        name:'custLinkManId',
        type:'string'
    },{
        // 客户名称
        name:'custName',
        type:'string'
    },{
        //职位
        name:'contactJob',
        type:'string'
    },{
        // 行业
        name:'contactTrade',
        type:'string'
    },{
        //商机状态
        name:'busStatus',
        type:'string'
    },{
        // 地址
        name:'contactAddress',
        type:'string'
    },{
        // 城市名称
        name:'contactCity',
        type:'string'
    },{
        // 城市id
        name:'contactCityId',
        type:'string'
    },{
        // 客户类型
        name:'contactType',
        type:'string'
    },{
        // 跟踪日程时间
        name:'scheduleDate',
        type: 'Date',
        defaultValue:null,
        convert: DButil.changeLongToDate
    }
    ]
});


/**
 * 营销历史记录查询中客户需求grid的model
 */
Ext.define('SearchCustomerDemandGridModel',{
    extend:'Ext.data.Model',
    fields:[{
        // 需求分类
        name:'opinionType',
        type:'string'
    },{
        // 需求及问题
        name:'problem',
        type:'string'
    },{
        // 需求问题解决办法
        name:'solution',
        type:'string'
    },{
        // 联系人姓名
        name:'custLinkManName',
        type:'string'
    },{
        // 计划主题
        name:'topic',
        type:'string'
    },{//计划类别
    	name : 'projectType',
    	type : 'string'
    	
    },{
        // 营销方式
        name:'marketingMethod',
        type:'string'
    },{
        // 营销人
        name:'marketingPerson',
        type:'string'
    },{
        // 营销时间
        name:'marketingDate',
        type: 'Date',
        defaultValue:null,
        convert: DButil.changeLongToDate
    }]
});


/**
 * 营销历史记录查询中走货潜力gird的model
 */
Ext.define('SearchSendGoodsPontentialModel',{
    extend:'Ext.data.Model',
    fields:[{
        // 始发城市
        name:'comeFromCity',
        type:'string'
    },{
        // 到达城市
        name:'comeToCity',
        type:'string'
    },{
        // 货量潜力
        name:'volumePotential',
        type:'string'
    },{
        // 合作物流公司
        name:'companyId',
        type:'string'
    },{
        //是否已有路线
        name:'alreadyHaveLine',
        type:'string'
    },{
        // 备注
        name:'remark',
        type:'string'
    },{
        // 联系人姓名
        name:'custlinkManName',
        type:'string'
    },{
        // 营销方式
        name:'marketingMethod',
        type:'string'
    },{
        // 营销人
        name:'marketingPerson',
        type:'string'
    },{
        // 营销时间
        name:'marketingDate',
        type: 'Date',
        defaultValue:null,
        convert: DButil.changeLongToDate
    },{
		//合作意向
		name:'cooperate'
	},{
		//适合我司承运
		name:'accord'
	}]
});

/**
 * 营销历史记录查询中问卷结果gird的model
 * 肖红叶
 * 20140324
 */
Ext.define('QuestionnaireResultModel',{
	extend : 'Ext.data.Model',
	fields : [{// 问卷编号
		name : 'questionnaireCode',
		type : 'string'
	},{// 问卷id
		name : 'questionnaireId',
		type : 'string'
	},{// 问卷名称
		name : 'questionnaireName',
		type : 'string'
	},{// 问卷描述
		name : 'desc',
		type : 'string'
	},{// 适用范围
		name : 'useRange',
		type : 'string'
	},{// 问卷状态
		name : 'status',
		type : 'string'
	},{// 采用此数据
		name : 'useTimes',
		type : 'string'
	},{// 最后修改人
		name : 'modifyUser',
		type : 'string'
	},{//生效时间
		name : 'effectiveTime',
		type: 'Date',
		defaultValue:null,
		convert: DButil.changeLongToDate
	},{//失效时间
		name : 'invalidTime',
		type: 'Date',
		defaultValue:null,
		convert: DButil.changeLongToDate
	},{//创建时间
		name : 'createDate',
		type: 'Date',
		defaultValue:null,
		convert: DButil.changeLongToDate
	},{//计划主题
		name : 'planTopic',
		type : 'string'
	},{//回访id
		name : 'visitId',
		type : 'string'
	},{//联系人姓名
		name : 'linkManName',
		type : 'string'
	},{//营销方式
		name : 'marketingWay',
		type : 'string'
	},{//营销人姓名
		name : 'executorName',
		type : 'string'
	},{//营销时间
		name : 'marketingDate',
		type: 'Date',
		defaultValue:null,
		convert: DButil.changeLongToDate
	}]
});