/**
 * 开发计划名称Model
 */
Ext.define('DevelopNameModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		// id
		name : 'id',
		type : 'string'
	}, {
		// 开发主题
		name : 'topic',
		type : 'string'
	} ]
});
/**
 * 获取执行人Model
 */
Ext.define('UserModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		// id
		name : 'id',
		type : 'string'
	}, {
		// 姓名
		name : 'empName',
		type : 'string'
	} ]
});
/**
 * 获取部门Model
 * 增加标杆编码 author xuhualong
 */
Ext.define('DeptModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		// id
		name : 'id',
		type : 'string'
	}, {
		// 姓名
		name : 'deptName',
		type : 'string'  
	} , {
		// 标杆编码
		name : 'standardCode',
		type : 'string'  
	}]
});
/**
 * 开发计划查询列表Model
 */
Ext.define('DevelopPlaneGirdModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		// 执行部门
		name : 'execdeptName',
		type : 'string'
	}, {
		// 计划名称或计划主题
		name : 'topic',
		type : 'string'
	}, {
		// 计划时限
		name : 'timelimit',
		type : 'string'
	}, {
		// 执行人员
		name : 'execdeptUserName',
		type : 'string'
	}, {
		// 创建人
		name : 'createUserName',
		type : 'string'
	}, {
		// 创建时间
		name : 'createDate',
		type : 'Date',
		defaultValue : null,
		convert : DButil.changeLongToDate
	}, {
		// 最后修改人
		name : 'modifyUserName',
		type : 'string'
	}, {
		// 最后修改时间
		name : 'modifyDate',
		type : 'Date',
		defaultValue : null,
		convert : DButil.changeLongToDate
	}, {
		// id
		name : 'id',
		type : 'string'
	}, {
		// 日程状态
		name : 'running',
		type : 'int'
	} , {
		// 日程状态
		name : 'status',
		type : 'string'
	},{
		name : 'planType'// 计划类型
	},{
        //计划类别
        name : 'projectType',
        type : 'string'
    },{
        //问卷id 盛诗庆0324
        name : 'surveyId',
        type : 'string'
    },{
        //问卷名称 盛诗庆20140324
        name : 'surveyName',
        type : 'string'
    }]
});
/**
 * 日程管理查询结果列表Model
 */
Ext.define('DevelopScheduleGirdModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		// 合作意向
		name : 'cooperatePurpose',
		type : 'string'
	},{
		name: 'custType'
	}, {
		// 客户名称
		name : 'custName',
		type : 'string'
	}, {
		//客户编码,配合简版360增加的字段
		name:'custNumber',
		type:'string'
	}, {
		// 已制定计划
		name : 'planeDraft',
		type : 'string'
	}, {
		// 联系人
		name : 'linkManName',
		type : 'string'
	}, {
		// 联系人手机
		name : 'linkManMobeilPhone',
		type : 'string'
	}, {
		// 联系人电话
		name : 'linkManTelePhone',
		type : 'string'
	}, {
		// 发货潜力
		name : 'sendGoodsPontential',
		type : 'string'
	}, {
		// 日程日期
		name : 'scheduleDate',
		type : 'Date',
		defaultValue : null,
		convert : DButil.changeLongToDate
	}, {
		// id
		name : 'id',
		type : 'string'
	}, {
		// 开发阶段
		name : 'businessStatus',
		type : 'string'
	}, {
		// 商机状态 盛诗庆新增20140403
		name : 'businessOpportunityStatus',
		type : 'string'
	},{
		// 重复客户所在部门 盛诗庆新增20140403
		name : 'repeatCustDeptName',
		type : 'string'
	}, {
		// 计划id
		name : 'planId',
		type : 'string'
	} ,{
		//未完成计划数量
		name : 'unfinishedPlanNum',
		type : 'int'
	},{
		// 是否疑似重复 0不是1是
		name : 'repeatCust',
		type : 'string'
	},{
		//商机状态
		name : 'businessOpportunityStatus'
	},{
		//计划名称
		name:'planeName',
		type:'string'
	},{
		//维护时限
		name:'dateLimit',
		type:'string'
	},{
		// 回访次数
		name : 'visitNum',
		type : 'int'
	},{
		// 职位
		name : 'position',
		type : 'string'
	},{
		// 最后回访时间
		name : 'lastVisitDate',
		type : 'Date',
		defaultValue : null,
		convert : DButil.changeLongToDate
	},{
		name:'memberLevel'//会员等级
	},{
		name:'executePersion'
	},{
		name:'scheduleStatus'//日程状态
	},{
		name:'linkManId'//联系人ID
	},{
		name:'memberId'//会员ID
	},{
		name:'psCustomerId'//潜散客ID
	},{
		name:'marketRemark'//营销备注
	},{
		name:'questionnaireId'//问卷id
	},{
		name:'questionnaireName'//问卷名称
	},{
		name:'questionnaireCode'//问卷编码
	},{
		name:'projectType'//计划类别
	}]
	
});

/**
 * <p>
 * 开发日程新增Model
 * <p>
 * @revision 盛诗庆 新增业务类别
 * @author 张登
 * @时间 2012-3-27
 */
Ext.define('ScheDuleModel', {
	extend : 'Ext.data.Model',
	fields : [// id
	{
		name : 'id'
	}, {
		// 业务类别
		name : 'businessType',
		type : 'string'
	}, {
		name : 'planId'// 客户Id
	}, {
		name : 'custId'// 客户Id
	}, {
		//客户编码,配合简版360增加的字段
		name:'custNumber',
		type:'string'
	},{
		name : 'scheduleId'// 日程Id
	}, {
		name : 'planTopic'// 计划名称
	}, {
		name : 'coopIntetion'// 合作意向
	}, {
		name : 'custName'// 客户名称
	}, {
		name : 'contactId'// 联系人Id
	}, {
		name : 'contactName'// 联系人名称
	}, {
		// 是否疑似重复 0不是1是
		name : 'repeatCust',
		type : 'string'
	},{
		//疑似重复客户所在部门
		name : 'repeatCustDeptName',
		type : 'string'
	},{
		//商机状态
		name : 'businessOpportunityStatus'
	},{
		name : 'contactMobile'// 联系人手机
	}, {
		name : 'contactTel'// 联系人电话
	}, {
		name : 'goodsPotential'// 发货潜力
	}, {
		name : 'scheduleDate',// 日程时间
		type : 'Date',
		defaultValue : null,
		convert : DButil.changeLongToDate
	},{
		name : 'visitNum'//回访次数
	},{
		name : 'lastVisitDate'//最后回访时间
	},{
		name : 'position'//职位
	},{
		name : 'businessStatus'//开发阶段
	},{
		name : 'unfinishedPlanNum'//未完成计划数量
	},{
		name : 'unfinishedPlanName'//未完成计划名称
	},{
		name : 'executeUserName'//执行人员
	},{
		name : 'memberLevel'//会员等级
	},
	// 客户类型
	{
		name : 'custType'
	}]
});

/**
 * <p>
 * 开发计划新增修改查询条件Model(同时可用于开发日程新增、修改页面)
 * <p>
 * 
 * @author 张登
 * @时间 2012-3-27
 */
Ext.define('SearchConditionModel', {
	extend : 'Ext.data.Model',
	fields : [
	// id
	{
		name : 'id'
	},{//业务类别，标识客户是零担，快递，快递和零担
		name : 'businessType'
	},{
		//是否是疑似重复客户 0不是1是
		name : 'repeatCust'
	},{
		// 商机状态 盛诗庆新增20140403
		name : 'businessOpportunityStatus',
		type : 'string'
	},{
		//疑似重复客户所在部门
		name : 'repeatCustDeptName',
		type : 'string'
	},
	// 客户名称
	{
		name : 'custName'
	},
	// 联系人姓名
	{
		name : 'linkManName'
	},
	// 联系人手机
	{
		name : 'linkManMobile'
	},
	// 联系人电话
	{
		name : 'linkManPhone'
	},
	// 联系人职位
	{
		name : 'post'
	},
	// 客户属性 标识出发客户，到达客户
	{
		name : 'custProperty'
	},
	// 合作意向
	{
		name : 'coopIntention'
	},
	// 客户来源
	{
		name : 'custbase'
	},
	// 货量潜力
	{
		name : 'volumePotential'
	}, {
		name : 'custId'// 客户Id
	},
	// 客户类型 标识潜客，散客，固定客户
	{
		name : 'custType'
	},
	// 行业
	{
		name : 'trade'
	},
    //二级行业
    {
        name : 'secondTrade'
    },
	// 开发状态 可能已经废弃，为了不影响以前代码，没有删除
	{
		name : 'developState'
	},{// 开发状态  需求确认、意向洽谈、持续培养、开始发货
		name : 'developmentPhase'
	},
	{
		name : 'lastVistiTime',
		type : 'Date',
		defaultValue : null,
		convert : DButil.changeLongToDate
	},
	// 回访次数
	{
		name : 'visitNum'
	},
	// 日程时间
	{
		name : 'programmeTime',
		type : 'Date',
		defaultValue : null,
		convert : DButil.changeLongToDate
	},
	// 创建时间
	{
		name : 'createDate',
		type : 'Date',
		defaultValue : null,
		convert : DButil.changeLongToDate
	},{
		name : 'beginTime',
		type : 'Date',
		defaultValue : null,
		convert : DButil.changeLongToDate
	}, {
		name : 'overTime',
		type : 'Date',
		defaultValue : null,
		convert : DButil.changeLongToDate
	},
	// 发货量
	{
		name : 'beginShipWeight'
	}, {
		name : 'overShipWeight'
	},
	// 发货票数
	{
		name : 'beginShipVotes',
		type : 'int'
	}, {
		name : 'overShipVotes',
		type : 'int'
	},
	// 发货金额
	{
		name : 'beginShipAmount'
	}, {
		name : 'overShipAmount'
	},
	// 到达货量
	{
		name : 'beginArrivalWeight'
	}, {
		name : 'overArrivalWeight'
	},
	// 到达票数
	{
		name : 'beginArrivalVotes',
		type : 'int'
	}, {
		name : 'overArrivalVotes',
		type : 'int'
	},
	// 到达金额
	{
		name : 'beginArrivalAmount'
	}, {
		name : 'overArrivalAmount'
	} ,{
		name : 'memberLevel'//会员等级
	},{
		name : 'memberId'//会员Id
	},{
		name : 'unfinishedPlanNum'//未完成计划数量
	},{
		name : 'prehuMan'//维护人
	},{
		name : 'contactId'//联系人Id
	},{
		// 未完成计划名称
		name : 'unfinishedPlanName',
		type : 'string'
	},{
		// 未完成计划名称(维护日程新增用-->后台封装到改字段了)
		name : 'ftopic',
		type : 'string'
	},{
		name : 'memberNo'// 会员编码
	},{
		name : 'idNumber'// 身份证号
	},{
		name : 'companySize'// 公司规模
	},{
		name : 'companyNature'// 公司性质
	},{
		name : 'taxregistNo'// 税务登记号
	},{
		name : 'cityName'//城市名称
	},{
		name : 'address'// 地址
	},{
		name : 'leaDeptName'// 到达部门名称
	},{
		name : 'arrDeptName'// 到达部门名称
	},{
		name : 'remark'// 备注
	},{
		name : 'recentcoop'// 最近合作物流公司
	},{
		name : 'recentGoods'// 走货情况(最近合作物流公司)
	},{
		name : 'custNeed'// 客户需求
	},{
		name : 'marketRemark'// 营销备注
	}]
});

/**
 * <p>
 * 开发计划新增查询Model
 * <p>
 * 
 * @author 张登
 * @时间 2012-3-27
 */
Ext.define('CreatePlanModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id'
	}, {
		name : 'beginTime',// 开始时间
		type : 'Date',
		defaultValue : null,
		convert : DButil.changeLongToDate
	}, {
		name : 'endTime',// 结束时间
		type : 'Date',
		defaultValue : null,
		convert : DButil.changeLongToDate
	}, {
		name : 'topic'// 开发维护主题
	}, {
		name : 'surveyId'//问卷id
	}, {
		name : 'execdeptid'// 执行部门
	}, {
		name : 'activedesc'// 开发维护活动描述
	}, {
		name : 'execuserid'// 执行人员
	}, {
		name : 'way'// 开发维护方式
	} ]
});

/**
 * <p>
 * 监控计划Model
 * <p>
 * 
 * @author 张登
 * @时间 2012-3-27
 */
Ext.define('MonitorPlanModel', {
	extend : 'Ext.data.Model',
	fields : [{
		name : 'departId'// 部门Id
	}, {
		name : 'departName'// 部门名称
	}, /*{
		name : 'execusername'// 执行人
	}, */{
		name : 'completionRate'// 计划完成率
	}, {
		name : 'total'// 计划总数
	}, {
		name : 'execute'// 执行中计划数量
	}, {
		name : 'noExecute'// 未执行计划数量
	}, {
		name : 'finished'// 已完成计划数量
	}, {
		name : 'overdue'// 已过期计划数量
	} ]
});

/**
 * <p>
 * 监控计划明细Model
 * <p>
 * 
 * @author 张登
 * @时间 2012-3-27
 */
Ext.define('MonitorPlanDetailModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'departName'// 部门名称
	}, {
		name : 'topic'// 开发主题
	}, {
		name : 'creatorName'// 计划制定者
	}, {
		name : 'planeTimeLimit'// 计划时限
	}, {
		name : 'customerName'// 客户名称
	}, {
		name : 'customerType'// 客户类型
	}, {
		name : 'customerProperty'// 客户属性
	}, {
		name : 'projectType'// 计划类别，表示零担还是快递
	}, {
		name : 'execuserName'// 执行人员
	}, {
		name : 'planStatus'// 计划状态
	}, {
		name : 'scheStatus'// 日程状态
	}, {
		name : 'planDesc'// 开发详情
	}, {
		name : 'linkManName'// 联系人姓名
	} , {
		name : 'memberLevel'// 会员等级
	} 
    /**
     * 修改开发计划监控 增加计划类别字段
     * auth : 李春雨
     * data : 2014-01-09
     */
     ,{
        name : 'projectType'//计划类别
     }
    ]
});


/**
 * 创建客户分组Model
 */
Ext.define('CustomerGroupModel', {
	extend : 'Ext.data.Model',
	fields : [
        {name: 'id'},
        {name: 'deptId'},// 部门id
        {name: 'groupName'},// 组名
        {name: 'count'}// 客户计数
	]
});

/**
 * 创建客户分组明细Model
 */
var types = Ext.data.Types; //定义类型
Ext.define('CustomerDetailGroupModel', {
	extend : 'Ext.data.Model',
	fields : [
        {name: 'id'},
        {name: 'deptId'},// 部门id
        {name: 'deptName'},// 部门名称
        {name: 'custNumber'},// 客户编码
        {name: 'custName'},// 客户名称
        {name: 'userId'},// 维护人id
        {name: 'empName'},// 维护人姓名
        {name: 'groupId'},// 组id
        {name: 'custId'},// 客户id
        {
        	name: 'queryDate',// 查询日期
        	type:'Date',
        	defaultValue : null,
			convert : DButil.changeLongToDate
        },
        {name: 'fcAnalyseType'},// 发到货标志
        {name: 'monthAmount',type:types.FLOAT},// 当月金额
        {name: 'sendGoodCycle'},// 上月发货周期
        {name: 'monthAmount',type:types.FLOAT},// 当月金额
        {name: 'month1Amount',type:types.FLOAT},// 上月金额
        {name: 'month2Amount',type:types.FLOAT},// 上上月金额
        {name: 'groupName'},// 组名
        {name: 'isMarkColor'},// 页面颜色表示,"1" 标识  "0"不标识
        {name: 'count',type:'int'}// 客户计数
	]
});

Ext.define('CustomerAmountDetailModel', {
	extend : 'Ext.data.Model',
	fields : [
        {name: 'id'},
        {name: 'deptId'},// 部门id
        {name: 'deptName'},// 部门名称
        {name: 'custNumber'},// 客户编码
        {name: 'custName'},// 客户名称
        {name: 'userId'},// 维护人id
        {name: 'empName'},// 维护人姓名
        {name: 'groupId'},// 组id
        {name: 'custId'},// 客户id
        {
        	name: 'queryDate',// 查询日期
        	type:'Date',
        	defaultValue : null,
			convert : DButil.changeLongToDate
        },
        {name:'queryMonthCycle'},//查询月发到货周期
        {name: 'fcAnalyseType'},// 发到货标志
        {name: 'monthAmount',type:types.FLOAT},// 当月金额
        {name: 'sendGoodCycle'},// 上月发货周期
        {name: 'monthAmount',type:types.FLOAT},// 当月金额
        {name: 'month1Amount',type:types.FLOAT},// 上月金额
        {name: 'month2Amount',type:types.FLOAT},// 上上月金额
        {name: 'groupName'},// 组名
        {name: 'isMarkColor'},// 页面颜色表示,"1" 标识  "0"不标识
        {name: 'count',type:'int'},// 客户计数
         {name: 'days.day1',type:'int'},// 1号
        {name: 'days.day2',type:'int'},// 2号
        {name: 'days.day3',type:'int'},// 3号
        {name: 'days.day4',type:'int'},// 4号
        {name: 'days.day5',type:'int'},// 5号
        {name: 'days.day6',type:'int'},// 6号
        {name: 'days.day7',type:'int'},// 7号
        {name: 'days.day8',type:'int'},// 8号
        {name: 'days.day9',type:'int'},// 9号
        {name: 'days.day10',type:'int'},// 10号
        {name: 'days.day11',type:'int'},// 11号
        {name: 'days.day12',type:'int'},// 12号
        {name: 'days.day13',type:'int'},// 13号
        {name: 'days.day14',type:'int'},// 14号
        {name: 'days.day15',type:'int'},// 15号
        {name: 'days.day16',type:'int'},// 16号
        {name: 'days.day17',type:'int'},// 17号
        {name: 'days.day18',type:'int'},// 18号
        {name: 'days.day19',type:'int'},// 19号
        {name: 'days.day20',type:'int'},// 20号
        {name: 'days.day21',type:'int'},// 21号
        {name: 'days.day22',type:'int'},// 22号
        {name: 'days.day23',type:'int'},// 23号
        {name: 'days.day24',type:'int'},// 24号
        {name: 'days.day25',type:'int'},// 25号
        {name: 'days.day26',type:'int'},// 26号
        {name: 'days.day27',type:'int'},// 27号
        {name: 'days.day28',type:'int'},// 28号
        {name: 'days.day29',type:'int'},// 29号
        {name: 'days.day30',type:'int'},// 30号
        {name: 'days.day31',type:'int'}// 31号
	]
});



/**
 * 创建客户分组明细Model
 */
Ext.define('OperatorCustomerModel', {
	extend : 'Ext.data.Model',
	fields : [
      {name: 'deptId'},// 部门id
      {name: 'userId'},// 维护人id
      {name: 'custId'}// 客户id
	]
});
/**
*@author 盛诗庆
*@description 待打印的计划model
*@date 2014-01-20
*@revision none
*/
Ext.define('PrintPlanModel',{
	extend : 'Ext.data.Model',
	fields : [
	          {name : 'custName'},//客户名称
	          {name : 'linkManName'},// 联系人姓名
	          {name : 'linkManMobile'},// 联系人手机
	          {name : 'linkManPhone'}, // 联系人电话
	          {name : 'address'}//联系人地址
	   ]
})