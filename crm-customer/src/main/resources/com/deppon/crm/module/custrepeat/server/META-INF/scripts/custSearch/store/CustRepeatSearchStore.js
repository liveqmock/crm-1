/**
 * 查询疑似重复列表集合-只查主客户
 */
Ext.define('MainCustRepeatListStore',{
	extend:'Ext.data.Store',
	model:'CustRepeatModel',
	pageSize:20,
	autoLoad:false,
	proxy:{
		type:'ajax',
		url:'../custrepeat/searchMainRepeatCustList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'repeatCustList',
			totalProperty : 'totalCount'
		}
	}
});

/**
 * 查询疑似重复列表-根据主客户ID
 */
Ext.define('SearchGroupRepeatCustListStore',{
	extend:'Ext.data.Store',
	model:'CustRepeatModel',
	pageSize:20,
	autoLoad:false,
	proxy:{
		type:'ajax',
		url:'../custrepeat/searchGroupRepeatCustList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'repeatCustList'
		}
	}
});

/**
 * 查询客户信息
 */
Ext.define('SearchCustomerListStore',{
	extend:'Ext.data.Store',
	model:'CustRepeatModel',
	pageSize:20,
	autoLoad:false,
	proxy:{
		type:'ajax',
		url:'../custrepeat/searchCustomerList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'repeatCustList'
		}
	}
});

