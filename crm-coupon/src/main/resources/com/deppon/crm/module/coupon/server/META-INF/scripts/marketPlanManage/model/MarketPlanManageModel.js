/**
 * 营销计划管理查询结果列表的model
 */
Ext.define('MarketPlanManageListModel',{
	extend:'Ext.data.Model',
	fields:[{
		name:'id'
	},{
		// 营销计划编码
		name:'planNumber'
	},{
		//营销计划名称
		name:'planName'
	},{
		// 优惠券类型
		name:'couponType'
	},{
		//活动开始时间
		name:'activityBeginTime',
		convert: CouponUtil.changeLongToDate,
		type:'Date'
	},{
		// 活动结束时间
		name:'activityEndTime',
		convert: CouponUtil.changeLongToDate,
		type:'Date'
	},{
		//启用状态
		name:'marketStatus'
	},{
		// 发券总数
		name:'couponSendTotal'
	},{
		//未发送数量
		name:'couponNoSendCount'
	},{
		// 已发送数量
		name:'couponSendCount'
	},{
		//已使用数量
		name:'couponUseCount'
	},{
		// 已过期数量
		name:'couponOverdueCount'
	},{
		//优惠券生效时间
		name:'autoBeginTime',
		convert: CouponUtil.changeLongToDate,
		type:'Date'
	},{
		// 优惠券失效时间
		name:'autoEndTime',
		convert: CouponUtil.changeLongToDate,
		type:'Date'
	},{
		//创建时间
		name:'createDate',
		convert: CouponUtil.changeLongToDate,
		type:'Date'
	},{
		// 最近修改人
		name:'modifyUserName'
	}]
});

/**
 * 批量导入手机号获得手机号列表的model
 */
Ext.define('BatchDealTelephoneModel',{
	extend:'Ext.data.Model',
	fields:[
	  {
		// 手机号码
		name:'cellphone',
		type:'string'
	  },{
		// 是否有效
		name:'validity',
		type:'string'
	  },{
		// 发送状态
		name:'sendStatus',
		type:'string'
	  }
	]
});
