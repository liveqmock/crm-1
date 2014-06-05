/**
 * 日志监控查询条件
 */
Ext.define('SearchLogConditionModel',{
	extend:'Ext.data.Model',
	fields:[
       //请求名称
       {name:'requestName'},
	   //统计开始时间
	   {name:'beginDate'},
	   //统计结束时间
       {name:'endDate'},
       //模块名称
       {name:'moduleName'},
       //统计频率
       {name:'statisticalFrequency'}
       ]
});
/**
 * 请求图查询条件
 */
Ext.define('SearchChartConditionModel',{
	extend:'Ext.data.Model',
	fields:[
       //模块名称
       {name:'modulename'},
	   //统计开始时间
	   {name:'beginDate'},
	   //统计结束时间
	   {name:'endDate'},
	   //统计方式
	   {name:'statisticalMethods'},
       //统计频率
       {name:'statisticalFrequency'},
       //统计名次（前多少）
       {name:'level'}]
});
/**
 * Action设置参数
 * */
Ext.define('ActionSetModel',{
	extend:'Ext.data.Model',
	fields:[
	   //ID
	   {name:'id'},
       //模块名称
       {name:'modulename'},
       //Action名称
       {name:'actionName'},
	   //请求次数
	   {name:'baseCount'},
	   //请求时间
	   {name:'baseTime'},
	   //次数浮动值
	   {name:'countFloat'},
       //时间浮动值
       {name:'timeFloat'},
       //ActionURL
       {name:'url'},
       //加入黑名单
       {name:'blackList'},
       //启用预警
       {name:'invalid'},
       //通知人员邮件
       {name:'mailPerson'}]
});
/**
 * 日志监控查询结果Model
 */
Ext.define('ActionResultModel',{
	extend:'Ext.data.Model',
	fields:[
	   {name:'id'},
       //模块名称
       {name:'modulename'},
       //Action名称
       {name:'actionName'},
       //创建时间
       {name:'createTime'},
	   //请求次数
	   {name:'requestCount'},
	   //url
	   {name:'url'},
	   //平均请求时长
	   {name:'avgRequestTime',type:'number'},
	   //请求最长时长
	   {name:'maxRequestTime',type:'number'}]
});
/**
 * 日志监控列表Store
 */
Ext.define('ActionResultListStore',{
	extend:'Ext.data.Store',
	model:'ActionResultModel',
	pageSize:25,
	proxy:{
		type:'ajax',
		api:{
			read:'searchActionList.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'actionResultList',
			totalProperty:'totalCount'
		}
	}
});
Ext.define('LogBasicData',{
	actionResultStore:null,
	getActionResultStore:function(){
		return this.actionResultStore;
	},
	initActionResultStore:function(beforeLoadTransactionFn){
		if(this.actionResultStore == null){
			if(beforeLoadTransactionFn != null){
				this.actionResultStore =Ext.create('ActionResultListStore',{
					listeners:{
						beforeload:beforeLoadTransactionFn
					}
				});
			}else{
				this.actionResultStore = Ext.create('ActionResultListStore');
			}
		}
		return this.actionResultStore;
	},
	
	//Action新增
	setlogAction:function(params,successFn,failFn){
		var actionUrl = '../logmoniting/logSetAction.action';
		DpUtil.requestJsonAjax(actionUrl,params,successFn,failFn);
	},
	//Action修改
	updateAction:function(params,successFn,failFn){
		var actionUrl = '../logmoniting/logUpdateAction.action';
		DpUtil.requestJsonAjax(actionUrl,params,successFn,failFn);
	},
	//修改Action的时候获取Action设置信息
	getActionInfo:function(params,successFn,failFn){
		var actionUrl = '../logmoniting/getActionInfo.action';
		DpUtil.requestJsonAjax(actionUrl,params,successFn,failFn);
	},
	//获取chart图标显示的数据源
	getLogStatistics:function(params,successFn,failFn){
		var actionUrl = '../logmoniting/getLogStatistics.action';
		DpUtil.requestJsonAjax(actionUrl,params,successFn,failFn);
	},
	sarchChartData:function(params,successFn,failFn){
		var actionUrl = '../logmoniting/saerchChartTopData.action';
		DpUtil.requestJsonAjax(actionUrl,params,successFn,failFn);
	}
});
