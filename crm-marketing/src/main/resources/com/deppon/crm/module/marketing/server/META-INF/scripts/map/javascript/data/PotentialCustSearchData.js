/**
*潜在客户Data层
*/

//判断是否导入测试数据
(function() {
	var potentialCustSearchDataTest = "../scripts/potentialcust/test/PotentialCustSearchDataTest.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + potentialCustSearchDataTest + '"></script>');
	}
})();
//潜客查询条件
Ext.define('PotentialCustConditionModel',{
	extend: 'Ext.data.Model',
	fields : [
	   //所属部门
	 	 {name:'deptId'},
		//客户名称
		{name:'customerName'}
		// 联系人姓名
		,{name:'contactName'}
		// 联系人手机
		,{name:'contactPhone'}
		// 联系人电话
		,{name:'contactTel'}
		// 行业
		,{name:'industry'}
		// 合作意向
		,{name:'coopIntention'}
		// 客户来源
		,{name:'custSource'}
		// 商机状态
		,{name:'bizStatus'}
		// 货量潜力
		,{name:'goodsPotential'}
		// 创建开始时间
		,{name:'searchStartTime',type:'date',convert: DpUtil.changeLongToDate,defaultValue:(new Date().setDate(new Date().getDate()-30))}
		// 创建结束时间
		,{name:'searchEndTime',type:'date',convert: DpUtil.changeLongToDate,defaultValue:new Date()}
		// 职位
		,{name:'position'}]
});

/**
* 批量导入的Model
*/
Ext.define('ImportCustDataModel',{
	extend:'PotentialCustModel',
	fields:['rowNum','message',{name:'importSuccess',type:'boolean'},
	        {name:'validatePast',type:'boolean',defaultValue:true}]
});

/**
* 批量导入的Store
*/
Ext.define('ImportCustDataStore',{
	extend:'Ext.data.Store',
	model:'ImportCustDataModel',
	proxy:{
		type:'memory'
	}
});


Ext.define('PotentialCustSearchData',{
	extend:'PotentialCustBasicData',
	importCustStore:null,
	searchPotentialCustomerList:function(params,successFn,failFn){
//		var searchPotentialCustomerUrl='searchPotentialCustomerList.action';
//		DpUtil.requestJsonAjax(searchPotentialCustomerUrl,params,successFn,failFn);
		return this.getPotentialCustStore();
	},
	deletePotentialCustomer:function(params,successFn,failFn){
		var deletePotentialCustomerUrl='deletePotentialCustomer.action';
		DpUtil.requestJsonAjax(deletePotentialCustomerUrl,params,successFn,failFn);
	},
	serachPotentialById:function(params,successFn,failFn){
		var serachPotentialByIdUrl='../customer/searchPotentialCustomerById.action';
		DpUtil.requestJsonAjax(serachPotentialByIdUrl,params,successFn,failFn);
	},
	getImportCustStore:function(){
		if(this.importCustStore == null){
			this.importCustStore = Ext.create('ImportCustDataStore');
		}
		return this.importCustStore;
	},
	batchImportPotentialCust:function(params,saveSuccessFn,saveFailFn){
		var batchUrl = 'batchImportPotentialCust.action';
		DpUtil.requestJsonAjax(batchUrl,params,saveSuccessFn,saveFailFn);
	},
	//下载 潜客批量导入模板
	downloadTemplate:function(window){
		window.open('../common/templateDownLoad.action?propKey=PotentialCust');
	}
});