/**
 * 回访
 */
var ReturnVisitData  = function(){

};

/**
 * 发货潜力gird的model
 */
Ext.define('SendGoodsPontentialModel',{
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
		// 备注
		name:'remark',
		type:'string'
	},{
		//是否已有路线
		name:'alreadyHaveLine',
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
 * 发货潜力gird的Store
 */
Ext.define('SendGoodsPontentialStore',{
	extend:'Ext.data.Store',
	model:'SendGoodsPontentialModel',
	data:null/*[
	{'comeFromCity':'上海','comeToCity':'西安','volumePotential':'UNDER_SIX_HUNDRED','companyId':'HUAYU','remark':'测试数据'},
	{'comeFromCity':'广州','comeToCity':'上海','volumePotential':'SIX_HUNDRED-THREE_THOUSAND','companyId':'JIAJI','remark':'测试数据'}
	]*/
});


/**
 * 客户意见gird的model
 */
Ext.define('CustomerOpinionModel',{
	extend:'Ext.data.Model',
	fields:[{
		// 始发城市
		name:'opinionType',
		type:'string'
	},{
		// 需求及问题
		name:'problem',
		type:'string'
	},{
		// 解决办法
		name:'solution',
		type:'string'
	}]
});

/**
 *客户意见gird的Store
 */
Ext.define('CustomerOpinionStore',{
	extend:'Ext.data.Store',
	model:'CustomerOpinionModel',
	data:null/*[
	{'opinionType':'EFFICIENCY','problem':'需求不明确啊','solution':'和需求确认'},
	{'opinionType':'SERVICE','problem':'资金风险要把握啊','solution':'设计思路要明确'}
	]*/
});

/**
 * 回访保存
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 毛建强
 * @时间 2012-4-6
 */
ReturnVisitData.prototype.saveReturnVisit = function(param,successFn,failureFn){
	var url = '../marketing/saveReturnVisit.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};


/**
 * 回访查询Model
 */
Ext.define('ReturnVisitViewModel', {
	extend : 'Ext.data.Model',
	fields : [{
		//联系人
		name : 'linkName',
		type : 'string'
	}, {
		//联系人手机
		name : 'linkManMobile',
		type : 'string'
	}, {
		//联系人电话
		name : 'linkManPhone',
		type : 'string'
	}, {
		//开发主题
		name : 'theme',
		type : 'string'
	}, {
		//客户名称
		name : 'memberName',
		type : 'string'
	}, {
		//回访部门
		name : 'departName',
		type : 'string'
	}, {
		//客户意见
		name : 'needType',
		type : 'string'
	}, {
		//回访方式
		name : 'way',
		type : 'string'
	}, {
		//回访时间
		name : 'executorTime',
		type: 'Date',
		defaultValue:null,
		convert: DButil.changeLongToDate
	}, {
		//需求及问题
		name : 'problem',
		type : 'string'
	}, {
		//解决方法
		name : 'solution',
		type : 'string'
	}, {
		//id
		name : 'id',
		type : 'string'
	},{
		name : 'userName',//回访人
		type : 'string'
	}, {//会员id
		name : 'memberId',
		type : 'string'
	},{//潜散客ID
		name : 'psCustomerId',
		type : 'string'
	},{//问卷id
		name : 'surveyId',
		type : 'string'
	}]
});

/**
 * 回访查询列表store
 */
Ext.define('ReturnVisitViewStore', {
	extend : 'Ext.data.Store',
	model : 'ReturnVisitViewModel',
	pageSize : 20,
	proxy : {
		type : 'ajax',
		url : '../marketing/searchReturnVisitList.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'returnVisitList',
			totalProperty : 'totalCount'
		}
	}
});

/**
 * 初始化数据模型
 */
Ext.define('InitDataModel',{
	extend:'Ext.data.Model',
	fields:[{
		//开发时间
		name:'executorTime',
		type:'date',
		defaultValue : null,
		convert : DButil.changeLongToDate
	},{
		//客户名称
		name:'psCustomerName',
		type:'string'
	},{
		//开发依据
		name:'accordingDesc',
		type:'string'
	},{
		//联系人电话
		name:'linkManPhone',
		type:'string'
	},{
		//开发方式
		name:'wayDesc',
		type:'string'
	},{
		//联系人姓名
		name:'linkName',
		type:'string'
	},{
		//联系人手机
		name:'linkManMobile',
		type:'string'
	},{
		//开发主题
		name:'theme',
		type:'string'
	},{
		//客户id
		name:'psCustomerId',
		type:'string'
	},{
		//开发依据id
		name:'according',
		type:'string'
	},{
		//维护方式id
		name:'way',
		type:'string'
	},{
		//联系人id
		name:'linkManId',
		type:'string'
	},{
		//开发主体id
		name:'planId',
		type:'string'
	},{
		//日程id
		name:'scheduleId',
		type:'string'
	},{
		//执行部门名称
		name:'departName',
		type:'string'
	},{
		//执行人员名称
		name:'userName',
		type:'string'
	},{
		//执行人员id
		name:'executorId',
		type:'string'
	},{
		//执行部门id
		name:'executeDeptId',
		type:'string'
	},{
		//客户ID
		name:'memberId',
		type:'string'
	}]
});

/**
 * 双击查看回访明细(回访查询页面)
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 毛建强
 * @时间 2012-4-18
 */
ReturnVisitData.prototype.queryRetureVisitDetailed = function(param,successFn,failureFn){
	var url = '../marketing/searchReturnVisit.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};

/**
 * 查看客户回访记录
 */
Ext.define('ReturnVisitLogStore', {
	extend : 'Ext.data.Store',
	model : 'ReturnVisitViewModel',
	pageSize : 20,
	proxy : {
		type : 'ajax',
		url : '../marketing/searchReturnVisitLog.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'returnVisitList',
			totalProperty : 'totalCount'
		}
	}
});

/**
 * 初始化客户的预警信息
 * 肖红叶
 * 20140325
 */
Ext.define('LostWarnCustInfoModel',{
	extend:'Ext.data.Model',
	fields:[{//预发货开始时间
		name:'preSendBeginTime',
		type:'date',
		defaultValue : null,
		convert : DButil.changeLongToDate
	},{//预发货结束时间
		name:'preSendEndTime',
		type:'date',
		defaultValue : null,
		convert : DButil.changeLongToDate
	},{//最近一次发货时间
		name:'lastSendTime',
		type:'date',
		defaultValue : null,
		convert : DButil.changeLongToDate
	},{//预警状态
		name:'warnStatus',
		type:'string'
	},{//流失原因
		name:'lostCause',
		type:'string'
	},{//流失原因备注
		name:'lostCauseRemark',
		type:'string'
	},{//是否季节性客户
		name:'isseason',
		type:'string'
	},{//回访id
		name:'rVid',
		type:'int'
	},{//客户编码
		name:'custNumber',
		type:'string'
	},{//当次预警时间
		name:'warnTime',
		type:'date',
		defaultValue : null,
		convert : DButil.changeLongToDate
	}]
});

/**
 * 根据客户id查询流失预警客户信息
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 肖红叶
 * @时间 2014-3-25
 */
ReturnVisitData.prototype.searchWarnLostCustInfo = function(param,successFn,failureFn){
	var url = '../marketing/searchWarnLostCustInfo.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};
   