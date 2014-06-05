/**
*会员共用DataTest层
*/
//会员，联系人查询结果store
Ext.define('SearchMemberResultStoreTest',{
	extend:'Ext.data.Store',
	model:'SearchMemberResultModel',
	autoLoad:true,
	proxy:{
		type:'memory',
		data:[{id:'1',custNum:'1',custName:'1',contactNum:'1',contactName:'1',mobileNum:'1',telNum:'1',address:'1',remark:'1'}
		,{id:'2',custNum:'1',custName:'1',contactNum:'1',contactName:'1',mobileNum:'1',telNum:'1',address:'1',remark:'1'}
		,{id:'3',custNum:'1',custName:'1',contactNum:'1',contactName:'1',mobileNum:'1',telNum:'1',address:'1',remark:'1'}
		,{id:'4',custNum:'1',custName:'1',contactNum:'1',contactName:'1',mobileNum:'1',telNum:'1',address:'1',remark:'1'}
		,{id:'5',custNum:'1',custName:'1',contactNum:'1',contactName:'1',mobileNum:'1',telNum:'1',address:'1',remark:'1'}
		,{id:'6',custNum:'1',custName:'1',contactNum:'1',contactName:'1',mobileNum:'1',telNum:'1',address:'1',remark:'1'}
		,{id:'7',custNum:'1',custName:'1',contactNum:'1',contactName:'1',mobileNum:'1',telNum:'1',address:'1',remark:'1'}
		]
	}
});
Ext.define('MemberCustBasicDataTest',{
	//会员，联系人查询store声明
	searchMemberResultStore:null,
	//会员，联系人查询store
	getSearchMemberResultStore: function() {
		return this.searchMemberResultStore;
	},
	//初始化会员，联系人查询store
	initSearchMemberResultStore: function(beforeLoadTransactionFn) {
		if(this.searchMemberResultStore == null){
			this.scatterUpGradeCustStoreTest = Ext.create('SearchMemberResultStoreTest');
		}
		return this.searchMemberResultStore;
	}
});
