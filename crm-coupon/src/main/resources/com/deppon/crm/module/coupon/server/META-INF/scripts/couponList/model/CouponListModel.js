/**
 * 优惠券查询结果列表的model
 */
Ext.define('CouponQueryListModel',{
	extend:'Ext.data.Model',
	fields:[{
		// 营销计划名称
		name:'planName',
		type:'string'
	},{
		//优惠券编码
		name:'couponNumber',
		type:'string'
	},{
		// 优惠券金额
		name:'useCouponValue',
		type:'string'
	},{
		//优惠券状态
		name:'status',
		type:'string'
	},{
		// 生效日期
		name:'begintime',
		convert: CouponUtil.changeLongToDate,
		type:'Date'
	},{
		//失效日期
		name:'endtime',
		convert: CouponUtil.changeLongToDate,
		type:'Date'
	},{
		// 金额要求
		name:'value',
		type:'string'
	},{
		//来源运单号
		name:'sourceWBNumber',
		type:'string'
	},{
		// 来源运单金额
		name:'sourceWBValue',
		type:'string'
	},{
		//使用运单号
		name:'useWBNumber',
		type:'string'
	},{
		// 使用运单金额
		name:'useWBValue',
		type:'string'
	},{
		//发送手机号码
		name:'sendtelPhone',
		type:'string'
	},{
		// 发送时间
		name:'sendTime',
		convert: CouponUtil.changeLongToDate,
		type:'Date'
	},{
		//使用手机号码
		name:'usetelPhone',
		type:'string'
	},{
		// 使用时间
		name:'useTime',
		convert: CouponUtil.changeLongToDate,
		type:'Date'
	},{
		// 优惠券归属部门id
		name:'underDept',
		type:'string'
	},{
		// 营销计划Id
		name:'marketPlanId',
		type:'string'
	},{
		// 优惠券归属部门名称
		name:'underDeptName',
		type:'string'
	}]
});
/**
 * 营销计划管理-根据营销计划名称查询营销计划列表的model
 */
Ext.define('CouponQueryMarketPlanListModel',{
	extend:'Ext.data.Model',
	fields:[{
		// 营销计划名称
		name:'planName',
		type:'string'
	},{
		//优惠券编码
		name:'planNumber',
		type:'string'
	},{
		//优惠券类型
		name:'couponType',
		type:'string'
	},{
		// 活动开始时间
		name:'activityBeginTime',
		convert: CouponUtil.changeLongToDate,
		type:'Date'
	},{
		// 活动结束时间
		name:'activityEndTime',
		convert: CouponUtil.changeLongToDate,
		type:'Date'
	},{
		// 启用状态
		name:'marketStatus',
		type:'string'
	}]
});
/**
 * 获取部门Model
 */
Ext.define('DeptModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		// 部门id
		name : 'id',
		type : 'string'
	}, {
		// 部门名称
		name : 'deptName',
		type : 'string'
	}, {
		// 标杆编码
		name : 'standardCode',
		type : 'string'
	}
	]
});

/**
 * 优惠券状态Model
 */
Ext.define('StatusModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		// 状态id
		name : 'code',
		type : 'string'
	}, {
		// 状态名称
		name : 'codeDesc',
		type : 'string'
	} ]
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
		// 线路区域要求 	(0、1、2、3)空、走货线路、发货区域、到达区域
		name : 'regdemand'
	}]
});
