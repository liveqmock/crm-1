/**
*会员新增Data
*/

//判断是否导入测试数据
(function() {
	var memberDataTest = "../scripts/membercust/test/MemberCustDataTest.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + memberDataTest + '"></script>');
	}
})();


/**
 * 固定客户Model
 */
Ext.define('CustInfoModel',{
	extend:'Ext.data.Model',
	fields:['modifyUserName','department','modifyDate','workflowId'	]
/*	        {name: 'modifyUserName',	type: 'string'	},
	        {name: 'department',		type: 'string'	},
	        {name: 'modifyDate',		type: 'string'	},
	        {name: 'workflowId',		type: 'string'	}
*/	
});

/**
 * 加载tab5（固定客户）的数据store
 */
Ext.define('CustInfoStore',{
	extend:'Ext.data.Store',
	storeId:'CustInfoStoreId',
	model:'CustInfoModel',
	data:[]
});
/**
 * 固定客户展示表哥Model
 */
Ext.define('ChangeRecordModel',{
	extend:'Ext.data.Model',
	fields:[ 'fieldName','oldValue','newValue'	]
});

/**
 * 加载tab5（固定客户展示表哥）的数据store
 */
Ext.define('ChangeRecordStore',{
	extend:'Ext.data.Store',
	model:'ChangeRecordModel',
	data:[],
	storeId:'ChangeRecordStoreId'
});
///////////////////////////TODO:
/*
Ext.define('CustInfoStore',{
	extend:'Ext.data.Store',
//	storeId:'CustInfoStoreId',
	fields:['modifyUserName', 'department', 'modifyDate','workflowId'],
	data:{'items':[{ 'modifyUserName': 'Lisa',  "department":"lisa@simpsons.com",  "modifyDate":"555-111-1224",  "workflowId":"555-111-1224"  },
	               { 'modifyUserName': 'Lisa',  "department":"lisa@simpsons.com",  "modifyDate":"555-111-1224",  "workflowId":"555-111-1224"  },
	               { 'modifyUserName': 'Lisa',  "department":"lisa@simpsons.com",  "modifyDate":"555-111-1224",  "workflowId":"555-111-1224"  },
	               { 'modifyUserName': 'Lisa',  "department":"lisa@simpsons.com",  "modifyDate":"555-111-1224",  "workflowId":"555-111-1224"  },
	               { 'modifyUserName': 'Lisa',  "department":"lisa@simpsons.com",  "modifyDate":"555-111-1224",  "workflowId":"555-111-1224"  }
	               ]},
   proxy: {
	   type: 'memory',
	   reader: {
		   type: 'json',
		   root: 'items'
	   }
   },
   autoLoad:true
});
*/
/**
 * 是否Model
 */
Ext.define('BooleanModel',{
	extend:'Ext.data.Model',
	fields:['name',{name:'value',type:'boolean'}]
});

/**
 * 是否控件的store
 */
Ext.define('BooleanStore',{
	extend:'Ext.data.Store',
	model:'BooleanModel',
	data:[{'name':'是','value':true},
	      {'name':'否','value':false}]
});

/**
 * 银行查询条件的Model
 */
Ext.define('BankSearchConditionModel',{
	extend:'Ext.data.Model',
	fields:['province','city','accountBank','accountBranch',
	        'provinceId','cityId','accountBankId','accountBranchId']
});

/**
 * 银行store
 */
Ext.define('BankInfoStore',{
	extend:'Ext.data.Store',
	model:'BankSearchConditionModel',
	proxy:{
		type:'memory'
	}
});


/**
 * 会员联系人Store
 */
Ext.define('ContactStore',{
	extend:'Ext.data.Store',
	model:'ContactModel',
	proxy:{
		type:'memory'
	}
});

/**
 * 接送货地址Store
 */
Ext.define('ShuttleAddressStore',{
	extend:'Ext.data.Store',
	model:'ShuttleAddressModel',
	proxy:{
		type:'memory'
	}
});

/**
 * 联系人偏好地址Store
 */
Ext.define('PreferenceAddressStore',{
	extend:'Ext.data.Store',
	model:'PreferenceAddressModel',
	proxy:{
		type:'memory'
	}
});


/**
 * 帐号Store
 */
Ext.define('AccountStore',{
	extend:'Ext.data.Store',
	model:'AccountModel',
	proxy:{
		type:'memory'
	}
});

Ext.define('MemberCustData',{
	extend:'MemberCustBasicData',
	booleanStore:null,
	// 联系人偏好地址Store
	preferenceAddressStore : null,
	// 帐号Store
	accountStore : null,
	// 接送货地址Store
	shuttleAddressStore : null,
	// 会员联系人Store
	contactStore : null,
	// 固定客户store
	CustInfoStore:null,
	// 固定客户展示表哥store
	ChangeRecordStore:null,
	//支行store
	bankInfoStore:null,
	//支行combobox的store
	bankComboxStore:null,
	getBooleanStore:function(){
		if(this.booleanStore == null){
			this.booleanStore = Ext.create('BooleanStore');
		}
		return this.booleanStore;
	},
	// 联系人偏好地址Store
	getPreferenceAddressStore : function() {
		if (this.preferenceAddressStore == null) {
			this.preferenceAddressStore = Ext.create('PreferenceAddressStore');
		}
		return this.preferenceAddressStore;
	},
	// 帐号Store
	getAccountStore : function() {
		if (this.accountStore == null) {
			this.accountStore = Ext.create('AccountStore');
		}
		return this.accountStore;
	},
	//接送货地址Store
	getShuttleAddressStore : function() {
		if (this.shuttleAddressStore == null) {
			this.shuttleAddressStore = Ext.create('ShuttleAddressStore');
		}
		return this.shuttleAddressStore;
	},
	//会员联系人Store
	getContactStore : function() {
		if (this.contactStore == null) {
			this.contactStore = Ext.create('ContactStore');
		}
		return this.contactStore;
	},
	clearStore:function(){
		this.preferenceAddressStore = Ext.create('PreferenceAddressStore');
		this.accountStore = Ext.create('AccountStore');
		this.shuttleAddressStore = Ext.create('ShuttleAddressStore');
		this.contactStore = Ext.create('ContactStore');
		this.bankInfoStore = Ext.create('BankInfoStore');
		this.CustInfoStore = Ext.create('CustInfoStore');
		this.ChangeRecordStore = Ext.create('ChangeRecordStore');
		this.bankComboxStore = Ext.create('BankInfoStore');
	},
	//查询支行信息
	searchBankInfo:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('../common/searchBankInfoByBankView.action',params,successFn,failFn);
	},
	//支行的store
	getBankInfoStore:function(){
		if(this.bankInfoStore == null){
			this.bankInfoStore = Ext.create('BankInfoStore');
		}
		return this.bankInfoStore;
	},
	//获得固定客户的store
	getCustInfoStore:function(){
		if(this.CustInfoStore == null){
			this.CustInfoStore = Ext.create('CustInfoStore');
		}
		return this.CustInfoStore;
	},
	//获得固定客户展示grid的store
	getChangeRecordStore:function(){
		if(this.ChangeRecordStore == null){
			this.ChangeRecordStore = Ext.create('ChangeRecordStore');
		}
		return this.ChangeRecordStore;
	},
	//发送——获得固定客户展示grid数据request
	searchApproveDataByLogId:function(params,successFn,failureFn){
		DpUtil.requestJsonAjax('../customer/searchApproveDataByLogId.action',params,successFn,failureFn)
	},
	//支行的combox的store
	getBankComboxStore:function(){
		if(this.bankComboxStore == null){
			this.bankComboxStore = Ext.create('BankInfoStore');
		}
		return this.bankComboxStore;
	},
	//通过部门Id实时查询该部门为香港还是大陆的营业网点
	chenckIsCnOrHK:function(params,successFn,failFn){
		DpUtil.requestJsonAjaxSync('chenckIsCnOrHK.action',params,successFn,failFn);
	}
});
