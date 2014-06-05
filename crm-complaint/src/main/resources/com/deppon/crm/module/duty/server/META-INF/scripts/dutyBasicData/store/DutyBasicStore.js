
/**
 * 基础资料设置页面 - 列表 store
 */
Ext.define('BasicInfoSettingStore',{
	extend:'Ext.data.Store',
	model:'BasicInfoSettingModel'
});



/**
 * 基础信息,业务项 Store
 */
Ext.define('BusinessItemsStore',{
	extend:'Ext.data.Store',
	model:'BusinessItemsModel',
	autoLoad:false,
	proxy:{
		type:'ajax'
		,api:{read:'searchBasicInfoList.action'}
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'basicInfoList'
		}
	}
});

/**
 * 基础类型 Store
 */
Ext.define('BasicInfoResultStore',{
	extend:'Ext.data.Store',
	model:'BasicInfoResultModel',
	pageSize:10,
	autoLoad:true,
	proxy:{
		type:'ajax'
		,api:{read:'searchBasicInfoPager.action'}
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'basicInfoList'
			,totalProperty:'totalCount'
		}
	}
});

/**
 * 查询业务范围集合- store
 */
Ext.define('DutyBusScopeStore',{
	extend:'Ext.data.Store',
	model:'DutyBasicLevelModel',
	autoLoad:false,
	proxy:{
		type:'ajax'
		,api:{read:'searchBusScopeByParentId.action'}
		,actionMethods:'POST'
		,reader:{
			type:'json',root:'basicLevelList'
		}
	}
});






/**
 * 上报类型
 * @type String
 */
ReportType = {
	'complaint':'COMPLAINT',
	'unusual':'UNUSUAL'
};




/**
 * 奖惩类型
 * @type 
 */
ReworpusType = {
	list:[
		{code:'nothing',codeDesc:'无'},
		{code:'reward',codeDesc:'奖励'},
		{code:'punish',codeDesc:'负激励'}
	],
	getReworpusTypeName:function(code){
		if(Ext.isEmpty(code)){return '';}
		for(var i=0;i<this.list.length;i++){
			var temp = this.list[i];
			if(temp.code==code){return temp.codeDesc;}
		}
	}
};

var ActionFunction = {
	/**
	 * 基础数据集合 - 删除
	 * @param {Object} params 以逗号隔开
	 * @param {Function} successFn
	 * @param {Function} failFn
	 */
	'basalDataList_deleteByIdArray':function(params,successFn,failFn){
		var url = '../duty/deleteBusItemByIds.action';
		DutyUtil.requestJsonAjax(url,params,successFn,failFn);
	},
	
	/**
	 * 基础数据 - 保存集合
	 * @param {Object} params
	 * @param {Function} successFn
	 * @param {Function} failFn
	 */
	'basalData_saveList':function(params,successFn,failFn){
		var url = '../duty/saveBusItemList.action';
		DutyUtil.requestJsonAjax(url,params,successFn,failFn);
	},
	/**
	 * 基础信息分页集合 - 删除业务范围、业务类型
	 * @param {Object} params
	 * @param {Function} successFn
	 * @param {Function} failFn
	 */
	'basicInfo_deleteListBusTypeByIds':function(params,successFn,failFn){
		var url = '../duty/deleteBusTypeByIds.action';
		DutyUtil.requestJsonAjax(url,params,successFn,failFn);
	},
	/**
	 * 基础信息分页集合 - 查询是否存在数据
	 * @param {Object} params
	 * @param {Function} successFn
	 * @param {Function} failFn
	 */
	'basicInfo_searchBasicInfoCount':function(params,successFn,failFn){
		var url = '../duty/searchBasicInfoCount.action';
		DutyUtil.requestJsonAjax(url,params,successFn,failFn);
	},
	/**
	 * 基础信息分页集合 - 查询详情数据
	 * @param {Object} params
	 * @param {Function} successFn
	 * @param {Function} failFn
	 */
	'basicInfo_searchBasicInfoDetails':function(params,successFn,failFn){
		var url = '../duty/searchBasicInfoCount.action';
		DutyUtil.requestAjax(url,params,successFn,failFn);
	},
	/**
	 * 基础资料：基础资料设置页面-保存
	 * @param {Object} params
	 * @param {Function} successFn
	 * @param {Function} failFn
	 */
	'basicBusScopeVO_saveBasicBusScopeVO':function(params,successFn,failFn){
		var url = '../duty/saveBasicBusScopeVO.action';
		DutyUtil.requestJsonAjax(url,params,successFn,failFn);
	},
	/**
	 * 基础资料：基础资料设置页面-业务类型删除
	 * @param {Object} params
	 * @param {Function} successFn
	 * @param {Function} failFn
	 */
	'basicBusType_deleteBusTypeByIds':function(params,successFn,failFn){
		var url = '../duty/deleteBusTypeByIdsInBusScopeVO.action';
		DutyUtil.requestJsonAjax(url,params,successFn,failFn);
	},
	/**
	 * 基础资料：基础资料设置页面-业务类型查询
	 * @param {Object} params
	 * @param {Function} successFn
	 * @param {Function} failFn
	 */
	'basicInfo_searchBusTypeList':function(params,successFn,failFn){
		var url = '../duty/searchBasicBusScopeVOSimple.action';
		DutyUtil.requestAjax(url,params,successFn,failFn);
	},
	/**
	 * 处理结果 - 删除集合
	 * @param {Object} params
	 * @param {Function} successFn
	 * @param {Function} failFn
	 */
	'result_deleteByList':function(params,successFn,failFn){
		var url = '../duty/searchBasicBusScopeVOSimple.action';
		DutyUtil.requestAjax(url,params,successFn,failFn);
	}
};