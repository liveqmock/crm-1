/**
 * 营销计划管理查询结果列表的store
 */

CouponListStore = function(){
};

Ext.define('CouponQueryListStore',{
	extend:'Ext.data.Store',
	model:'CouponQueryListModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		url:'../coupon/searchCouponByCondition.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'couponResults',
			totalProperty : 'totalCount'
		}
	}
});
/**
 * 营销计划管理-根据营销计划名称查询营销计划列表的store
 * 钟琼 2012-11-29
 */
Ext.define('CouponQueryMarketPlanListStore',{
	extend:'Ext.data.Store',
	model:'CouponQueryMarketPlanListModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		url:'../coupon/searchMarketPlan.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'listMarketplan',
			totalProperty : 'totalCount'
		}
	}
});

/**
 * 部门store
 */
Ext.define('DeptStore', {
	extend : 'Ext.data.Store',
	model : 'DeptModel',
	pageSize : 10,
	proxy : {
		type : 'ajax',
		url : '../coupon/searchDeptByDeptName.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'executeDeptList',
			totalProperty : 'totalCount'
		}
	}
});
/**
 * 线路区域要求
 */
Ext.define('RuleTypeStore',{
	extend:'Ext.data.Store',
	model:'CommonDictionaryModel',
	data:[
		{"code":"2", "codeDesc":"走货线路"},
		{"code":"3", "codeDesc":"发货区域"},
		{"code":"4", "codeDesc":"到货区域"}
	]
});
/**
 * 新增营销计划-线路区域要求（使用）-发到货区域
 */
Ext.define('UseCrossGoodsLineStore',{
	extend:'Ext.data.Store',
	model:'GoodsLineModel'
});

/**
 * 新增营销计划-线路区域要求（使用）-走货线路
 */
Ext.define('UseWalkGoodsLineStore',{
	extend:'Ext.data.Store',
	model:'GoodsLineModel'
});

/**
 * 优惠券状态store
 */
Ext.define('StatusStore', {
	extend : 'Ext.data.Store',
	model : 'StatusModel',
	data:[
		{
			code:'00',
			codeDesc:'全部'
		},{
	    	code:'10',
	    	codeDesc:'未发送'
		},{
			code:'20',
	    	codeDesc:'已发送'
		},{
			code:'30',
	    	codeDesc:'已使用'
		},{
			code:'40',
	    	codeDesc:'已过期'
		}]
});

/**
 * Description: 优惠券查询：导出全部，返回一个Excel报表文件
 * @author 肖红叶
 * @date 2012-11
 * @return
 */
CouponListStore.prototype.creatCouponReportExcel = function(param,successFn,failureFn){
	var url = '../coupon/creatCouponReportExcel.action';
	Ext.Ajax.request({
		url:url,
		params:param,
		//设置超时时间为8分钟
		timeout: 480000,
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				successFn(result);
			}else{
				failureFn(result);
			}
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
			failureFn(result);
		}
	});
};

/*
 * excel文件导出成功后删除服务器上的excel文件
 */
CouponListStore.prototype.deleteCouponReportExcel = function(param,successFn,failureFn){
	var url = '../coupon/deleteCouponReportExcel.action';
	CouponUtil.requestAjax(url,param,successFn,failureFn);
};


/**
 * Description: 优惠券查询：重发短信
 * @author 肖红叶
 * @date 2012-11
 * @return
 */
CouponListStore.prototype.reSendCouponMsg = function(param,successFn,failureFn){
	var url = '../coupon/reSendCouponMsg.action';
	CouponUtil.requestAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 查询优惠券规则
 * <p>
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-6
 */
CouponListStore.prototype.searchCouponRuleByMarketPlanId = function(param,successFn,failureFn){
	var url = '../coupon/searchCouponRuleByMarketPlanId.action';
	CouponUtil.requestAjax(url,param,successFn,failureFn);
};