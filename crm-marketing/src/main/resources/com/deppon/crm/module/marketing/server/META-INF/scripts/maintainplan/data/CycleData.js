/**
 * 客户维护
 */
CycleData = function(){
	
};

Ext.define('CycleListStore', {
	extend: 'Ext.data.Store',
	model : 'CustomerDetailGroupModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		url:'../marketing/queryCycleList.action',
		timeout:60000,
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'cycleList',
			totalProperty : 'totalCount'
		}
	}
});

/**
 * 发到货周期表中查询金额明细表格的store
 * 肖红叶
 * 20131127
 */
Ext.define('CycleAmountDetailListStore', {
	extend: 'Ext.data.Store',
	model : 'CustomerAmountDetailModel',
	proxy:{
		type:'ajax',
		url:'../marketing/searchAmountDetailByMonthDay.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'custGroupDayDetailList'
		}
	}
});

/**.
 * 查询发到货周期表数据
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 毛建强
 * @时间 2012-4-19
 */
CycleData.prototype.queryCycleListHeader = function(param,successFn,failureFn){
	var url = '../marketing/queryCycleListHeader.action';
	DButil.requestAjax(url,param,successFn,failureFn);
}

/**.
 * 获得发到货周期表金额明细数据的动态表头
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 肖红叶
 * @时间 2013-11-27
 */
CycleData.prototype.queryAmountDetailHeader = function(param,successFn,failureFn){
	var url = '../marketing/queryAmountDetailHeader.action';
	DButil.requestAjax(url,param,successFn,failureFn);
}


/**
 * 获取客户分组
 */
Ext.define('CustomerGroupModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		// id
		name : 'id',
		type : 'string'
	}, {
		// 组名
		name : 'groupName',
		type : 'string'
	} ]
});

/**
 * 部门store
 */
Ext.define('CustomerGroupStore', {
	extend : 'Ext.data.Store',
	model : 'CustomerGroupModel',
	autoLoad:true,
	proxy : {
		type : 'ajax',
		url : '../marketing/searchCustomerGroupList.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'group'
		}
	}
});


/**
 * 回访查询Model
 */
Ext.define('CycleReturnVisitModel', {
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
		//客户意见
		name : 'needType',
		type : 'string'
	}, {
		//具体开发方式
		name : 'way',
		type : 'string'
	}, {
		//维护时间
		name : 'schedule',
		type: 'Date',
		defaultValue:null,
		convert: DButil.changeLongToDate
	} ,{
		//执行人
		name : 'userName',
		type : 'string'
	} ]
});
/**
 * 回访查询Store
 */
Ext.define('CycleReturnVisitStore', {
	extend: 'Ext.data.Store',
	model : 'CycleReturnVisitModel',
	proxy:{
		type:'ajax',
		url:'../marketing/searchReturnVisitRecords.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'returnList'
		}
	}
});


/**
 * 线路分析Model
 */
Ext.define('CycleCircuitAnalysisModel', {
	extend : 'Ext.data.Model',
	fields : [{
		//城市
		name : 'city',
		type : 'string'
	}, {
		//上月 发到货金额
		name : 'lastMonthAmount',
		type : 'double'
	}, {
		//上月同期金额
		name : 'lastMonthSameTimeAmount',
		type : 'double'
	}, {
		//当月同期金额
		name : 'currentMonthSameTimeAmount',
		type : 'double'
	}, {
		//上月货量
		name : 'lastMonthvolume',
		type : 'double'
	}, {
		//当月货量
		name : 'currentMonthvolume',
		type : 'double'
	}, {
		//上月票数
		name : 'lastMonthVotes',
		type: 'int'
	} ,{
		//当月票数
		name : 'currentMonthvotes',
		type : 'int'
	} ,{
		//上月卡航票数
		name : 'lastMonthTruckVotes',
		type : 'int'
	} ,{
		//当月卡航票数
		name : 'currentMonthTruckVotes',
		type : 'int'
	} ,{
		//上月城际票数
		name : 'lastMonthCityVotes',
		type : 'int'
	} ,{
		//当月城际票数
		name : 'currentMonthCityVotes',
		type : 'int'
	} ,{
		// 上月精准汽运（长）票数
		name : 'lastMonthlongTruckVotes',
		type : 'int'
	} ,{
		/// 当月精准汽运（长）票数
		name : 'currentMonthlongTruckVotes',
		type : 'int'
	} ,{
		//上月精准汽运（短）票数
		name : 'lastMonthshortTruckVotes',
		type : 'int'
	} ,{
		//当月精准汽运（短）票数
		name : 'currentMonthshortTruckVotes',
		type : 'int'
	},{
		//上月空运票数
		name : 'lastAirTransVotes',
		type : 'int'
	},{
		//当月空运票数
		name : 'currentAirTransVotes',
		type : 'int'
	},{
		//上月经济快递票数
		name : 'lastEconomyExpressVotes',
		type : 'int'
	},{
		//本月经济快递票数
		name : 'currentEconomyExpressVotes',
		type : 'int'
	} ]
});

/**
 * 线路分析Store
 */
Ext.define('CycleCircuitAnalysisStore', {
	extend: 'Ext.data.Store',
	model : 'CycleCircuitAnalysisModel',
	proxy:{
		type:'ajax',
		url:'../marketing/searPathAnalysisInfos.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'pathAnalysisList'
		}
	}
});

/**.
 * <p>
 * 根据条件查询客户列表
 * <p>
 * @author 张登
 * @时间 2012-3-26
 */
Ext.define('DeliverySearchConditionStore',{
	extend:'Ext.data.Store',
	model:'SearchConditionModel',
	pageSize:10,
	proxy:{
		type:'ajax',
		url:'../marketing/searchDevelopPopDataList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'customerInfoList',
			totalProperty : 'totalCount'
		}
	}
});

/**.
 * <p>
 * 根据计划id查询关联客户
 * <p>
 * @author 张登
 * @时间 2012-3-26
 */
Ext.define('DeliverySearchCustbyIdStore',{
	extend:'Ext.data.Store',
	model:'SearchConditionModel'
});

Ext.define('DeliveryScheDuleStore',{
	extend:'Ext.data.Store',
	model:'ScheDuleModel',
	pageSize:10,
	proxy:{
		type:'ajax',
		url:'../marketing/searchSchedulePopDataList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'scheduleList',
			totalProperty : 'totalCount'
		}
	}
});


/**.
 * 获取当前登录部门
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 毛建强
 * @时间 2012-4-19
 */
CycleData.prototype.searchCurrentDept = function(param,successFn,failureFn){
	var url = '../marketing/searchCurrentDept.action';
	DButil.requestAjax(url,param,successFn,failureFn);
}