/**
 * F7 会员联系人查询结果storeTest
 */
Ext.define('MemberSearchStoreTest',{
	extend:'Ext.data.Store',
	model:'SearchMemberResultModel'
});

Ext.define('SearchMemberDataTest', {
	extend:'MemberCustBasicDataTest',
	searchMemberResultStoreTest:null,
	//F7会员，联系人查询store
	getSearchMemberResultStore: function() {
		return this.searchMemberResultStoreTest;
	},
	//F7初始化会员，联系人查询store
	initSearchMemberResultStore: function(beforeLoadTransactionFn) {
		if(this.searchMemberResultStoreTest == null){
			this.searchMemberResultStoreTest = Ext.create('MemberSearchStoreTest');
		}
		return this.searchMemberResultStoreTest;
	}
});
