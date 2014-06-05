
Ext.define('MemberManagerDataTest', {
	extend:'MemberCustBasicDataTest',
	memberSearchStoreTest:null,
	//会员查询结果test store
	getMemberSearchStore: function() {
		return this.memberSearchStoreTest;
	},
	//会员查询结果test store 初始化
	initMemberSearchStore: function(beforeLoadTransactionFn) {
		if(this.memberSearchStoreTest == null){
			
		}
		return this.memberSearchStoreTest;
	}
	
});
