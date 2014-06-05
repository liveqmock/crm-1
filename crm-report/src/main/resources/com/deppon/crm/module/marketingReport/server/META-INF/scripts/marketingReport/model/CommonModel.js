/**
 * 部门列表Model
 */
Ext.define('DeptListModel', {
	extend : 'Ext.data.Model',
	fields : [{
		// 部门名称
		name : 'deptName'
	},{
		// 部门ID
		name : 'id'
	}]
});
/**
 * 日报表统计Model
 */
Ext.define('WarnLostCustDailyModel', {
	extend : 'Ext.data.Model',
	fields : [{
		//预警后流失的客户
		name : 'lostAfterWarnCustCount'
	},{
		// 零担客户预警后流失数量
		name : 'lTTlostAfterWarnCustCount'
	},{
		// 快递客户预警后流失数量
		name : 'expresslostAfterWarnCustCount'
	},{
		// 预警客户流失率
		name : 'lostAfterWarnPre'
	},{
		// 零担客户预警后流失率
		name : 'lTTlostAfterWarnCustPercentage'
	},{
		// 快递客户预警后流失率
		name : 'expresslostAfterWarnCustPercentage'
	},{
		// 回访流失客户总数
		name : 'returnVisitWarnLostCount'
	},{
		// 流失预静名单中的回访率
		name : 'warnLostRTPercentage'
	},{
		// 预警中快递客户回访率
		name : 'expressWarnLostRTPercentage'
	},{
		// 名单中零担客户的回访数量
		name : 'lTTReturnVisitWarnLostCount'
	},{
		// 名单中快递客户的回访数量
		name : 'erpressReturnVisitWarnLostCount'
	},{
		//预警中零担客户的回访率
		name : 'lTTWarnLostRTPercentage'
	},{
		//本部标杆编码
		name:'cadreName'
	},{
		//事业部标杆编码
		name:'careerDeptName'
	},{
		//大区标杆编码
		name:'bigAreaName'
	},{
		//小区标杆编码
		name:'areaName'
	},{
		//部门名称
		name:'deptName'
	},{
		//部门ID
		name:'deptId'
	},{
		//流失预警名单中快递客户数量
		name:'expressWarnLostCount'
	},{
		//流失预警名单中零担客户数量
		name:'lTTWarnLostCount'
	},{
		//流失预警名单中客户总数
		name:'warnLostCount'
	}]
});
/**
 * 月报表Model
 */
Ext.define('WarnLostCustMonthlyModel', {
	extend : 'Ext.data.Model',
	fields : [{
		// 流失客户（上上月发货 近两月未发货）
		name : 'lostCust'
	},{
		// 上上月有发货
		name : 'totalCustMonthBeforeLastSend'
	},{
		//零担上上月有发货
		name:'lTTCustMonthBeforeLastSend'
	},{
		//快递上上月有发货
		name:'expressCustMonthBeforeLastSend'
	},{
		//客户流失率
		name:'lostPercentage'
	},{
		//零担流失客户
		name:'lTTLostCust'
	},{
		//快递流失客户
		name:'expressLostCust'
	},{
		//零担客户流失率
		name:'lTTLostPercentage'
	},{
		//快递客户流失率
		name:'expressLostPercentage'
	},{
		//本部标杆编码
		name:'cadreName'
	},{
		//事业部标杆编码
		name:'careerDeptName'
	},{
		//大区标杆编码
		name:'bigAreaName'
	},{
		//小区标杆编码
		name:'areaName'
	},{
		//部门名称
		name:'deptName'
	},{
		//部门ID
		name:'deptId'
	},{
		//流失预警名单中快递客户数量
		name:'expressWarnLostCount'
	},{
		//流失预警名单中零担客户数量
		name:'lTTWarnLostCount'
	},{
		//流失预警名单中客户总数
		name:'warnLostCount'
	}]
});
/**
 * 日报表明细Model
 */
Ext.define('WarnLostCustModel', {
	extend : 'Ext.data.Model',
	fields : [{
		// 经营本部
		name : 'cadreStandardName'
	},{
		// 事业部
		name : 'careerDeptStandardName'
	},{
		// 大区
		name : 'bigAreaStandardName'
	},{
		// 营业区
		name : 'areaStandardName'
	},{
		// 所属部门
		name : 'deptStandardName'
	},{
		// 客户编码
		name : 'custNumber'
	},{
		// 客户等级
		name : 'custLevelDesc'
	},{
		// 客户名称
		name : 'custName'
	},{
		// 客户类别
		name : 'custCategoryDesc'
	},{
		// 预警状态
		name : 'warnStatusDesc'
	},{
		// 预发货时限
		name : 'preDeliverytendTime'
	},{
        //预发货开始时间
        name : 'preDeliverytBiegnTime'
    },{
		// 是否为季节性
		name : 'isSeasonDesc'
	},{
		// 标记为季节性客户的次数
		name : 'seasonTime'
	},{
		// 预警时间
		name : 'createTime'
	},{
		// 平均发货时间间隔（天）
		name : 'intervalNendTime'
	},{
		// 最近一次发货时间
		name : 'lastsendTime'
	},{
		// 最近一次发货金额（元）
		name : 'lastsendMoney'
	},{
		// 流失原因
		name : 'lostCause'
	},{
		// 回访次数
		name : 'returnTimes'
	},{
		// 流失原因备注
		name : 'lostCustRemark'
	},{
		// 预警信息变更时间
		name : 'warnStatusChangeTime'
	},{
		// 预警信息变更人
		name : 'warnStatusChangeUser'
	},{
		// 近90天总发货金额（元）
		name : 'suminRecent90day'
	}]
});