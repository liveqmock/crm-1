/**
*会员共用Data层
*/
//判断是否导入测试数据
(function() {
	var contractManagerDataTest = "../scripts/membercust/test/ContractManagerDataTest.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + contractManagerDataTest + '"></script>');
	}
})();
//合同查询条件 model 创建起始时间默认为本月 第一天
var ContractConditionSearchStartTime = new Date();
var getDate = function(){
	var date = new Date();
	date.setYear(new Date().getFullYear()-1);
	date.setDate(new Date().getDate()+1);
	return date;
};
ContractConditionSearchStartTime.setDate(1);
//合同查询条件 model
Ext.define('ContractConditionModel',{
	extend:'Ext.data.Model',
	fields:[
		//部门Id
		{name:'deptId',  type: 'string'},
		//客户编码
		{name:'custNumber',  type: 'string'},
		// 客户 全称
		{name:'custCompany',  type: 'string'},
		//合同编号
		{name:'contractNum',  type: 'string'},
		//协议联系人
		{name:'contactName',  type: 'string'},
		//合同状态
		{name:'contractStatus',  type: 'string'},
		//创建起始时间
		{name:'searchStartTime', type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null},
		//创建结束时间
		{name:'searchEndTime', type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null},
		//合同起始日期
		{name:'contractBeginDate', type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null},
		//合同结束时间
		{name:'contractendDate', type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null}
	]
});
Ext.define('ContractManagerData', {
	extend:'ContractBasicData',
	contractStore:null,
	//会员升级列表查询结果store
	getContractStore: function() {
		return this.contractStore;
	},
	//初始化会员升级列表查询结果store
	initContractStore: function(beforeLoadFn) {
		if(this.contractStore == null){
			if(beforeLoadFn != null){
				this.contractStore = Ext.create('ContractStore',{
					listeners:{
						beforeload:beforeLoadFn
					}
				});
			}else{
				this.contractStore = Ext.create('ContractStore');
			}
		}
		return this.contractStore;
	},
	//查询客户对于营业部所在子公司
	searchContractSubjectList:function(params,successFn,failFn){
		var viewContractDetailUrl='searchContractSubjectList.action';
		DpUtil.requestJsonAjax(viewContractDetailUrl,params,successFn,failFn);
	},
	//查看合同详情
	viewContractDetail:function(params,successFn,failFn){
		var viewContractDetailUrl='viewContractDetail.action';
		DpUtil.requestJsonAjax(viewContractDetailUrl,params,successFn,failFn);
	},
	//点击修改按钮查看合同详情
	viewUpdateContractDetail:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('viewUpdateContractDetail.action',params,successFn,failFn);
	},
	//点击修改按钮查看月发月送合同详情
	viewUpdateMonthSendDetail:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('viewUpdateMonthSendDetail.action',params,successFn,failFn);
	},
	//作废合同
	invalidContract:function(params,successFn,failFn){
		var invalidContractUrl='invalidContract.action';
		DpUtil.requestJsonAjax(invalidContractUrl,params,successFn,failFn);
	},
	//绑定合同
	boundContract:function(params,successFn,failFn){
		var boundContractUrl='boundContract.action';
		DpUtil.requestJsonAjax(boundContractUrl,params,successFn,failFn);
	},
	//解除绑定
	relieveContract:function(params,successFn,failFn){
		var viewContractDetailUrl='relieveContract.action';
		DpUtil.requestJsonAjax(viewContractDetailUrl,params,successFn,failFn);
	},
	//合同归属部门变更
	belongDeptChange:function(params,successFn,failFn){
		var belongDeptChangeUrl='belongDeptChange.action';
		DpUtil.requestJsonAjax(belongDeptChangeUrl,params,successFn,failFn);
	},
	//加载合同初始化数据
	loadInitContractData:function(params,successFn,failFn){
		var searchInitUrl='searchInitContractList.action';
		DpUtil.requestJsonAjax(searchInitUrl,params,successFn,failFn);
	},
	//导出
	expordContract:function(window,contractCondition){
//		window.open('excelDownload.action?contractCondition='+contractCondition);
		window.location.href = 'excelDownload.action?'+
		//部门Id
		'deptId='+DpUtil.trimString(contractCondition.deptId)+
		//客户编码
		'&custNumber='+DpUtil.trimString(contractCondition.custNumber)+
		// 客户名称
		'&custCompany='+DpUtil.trimString(contractCondition.custCompany)+
		//合同编号
		'&contractNum='+DpUtil.trimString(contractCondition.contractNum)+
		//协议联系人
		'&contactName='+DpUtil.trimString(contractCondition.contactName)+
		//创建起始时间
		'&searchStartTime='+contractCondition.searchStartTime.getTime()+
		//创建结束时间
		'&searchEndTime='+contractCondition.searchEndTime.getTime();
	},
	//校验是否为20:00-次日8:00
	monthEndBtnIsAble:function(params,successFn,failFn){
		DpUtil.requestJsonAjaxSync('monthEndBtnIsAble.action',params,successFn,failFn);
	},
	//校验合同是否是审批中
	contractIsAuditing:function(params,successFn,failFn){
		DpUtil.requestJsonAjaxSync('contractIsAuditing.action',params,successFn,failFn);
	}
});
