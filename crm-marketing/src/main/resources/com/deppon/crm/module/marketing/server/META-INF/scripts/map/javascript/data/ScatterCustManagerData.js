/**
*散客Data层
*/

//判断是否导入测试数据
(function() {
	var scatterCustManagerDataTest = "../scripts/scattercust/test/ScatterCustManagerDataTest.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + scatterCustManagerDataTest + '"></script>');
	}
})();
//散客查询条件
Ext.define('ScatterCustConditionModel',{
	extend: 'Ext.data.Model',
	fields : [
	      //所属部门
	      {name:'deptId'},
	      //散客编码
	      {name:'scatterNum'},
         //客户名称
        {name:'custName'},
        //行业
        {name:'industry'},
        //散客类型
        {name:'scatterCustType'},
        //合作意向
        {name:'coopIntention'},
        //客户属性  出发客户 到达客户
        {name:'custAttribute'},
        //联系人姓名
        {name:'contacterName'},
        //联系人手机
        {name:'contacterMobile'},
        //联系人电话
//        {name:'zoneCode'},
        {name:'contacterPhone'},
        //职位
      //  {name:'position'},
        //货量潜力
        {name:'volumePotential'},
        //创建时间
        {name:'beginTime',type:'date',convert: DpUtil.changeLongToDate,defaultValue:(new Date().setDate(new Date().getDate()-30))},
        {name:'overTime',type:'date',convert: DpUtil.changeLongToDate,defaultValue:new Date()},
        //商机状态
        {name:'businessStatus'},
        //发货量
        {name:'beginShipWeight'},
        {name:'overShipWeight'},
        //发货票数
        {name:'beginShipVotes',type:'int'},
        {name:'overShipVotes',type:'int'},
        //发货金额
        {name:'beginShipAmount'},
        {name:'overShipAmount'},
        //到达货量
        {name:'beginArrivalWeight'},
        {name:'overArrivalWeight'},
        //到达票数
        {name:'beginArrivalVotes',type:'int'},
        {name:'overArrivalVotes',type:'int'},
        //到达金额
        {name:'beginArrivalAmount'},
        {name:'overArrivalAmount'}]
});

Ext.define('ScatterCustSearchData',{
	extend:'ScatterCustBasicData',
	deleteScatterCustomer:function(params,successFn,failFn){
		var deleteScatterCustomerUrl='deleteScatterCustById.action';
		DpUtil.requestJsonAjax(deleteScatterCustomerUrl,params,successFn,failFn);
	},
	//转为临代散客
	changeToTempCust:function(params,saveSuccessFn,saveFailFn){
		DpUtil.requestJsonAjax('changeToTempCust.action',params,saveSuccessFn,saveFailFn);
	},
	serachScatterById:function(params,successFn,failFn){
		var serachScatterByIdUrl='../customer/searchScatterCustById.action';
		DpUtil.requestJsonAjax(serachScatterByIdUrl,params,successFn,failFn);
	}
});