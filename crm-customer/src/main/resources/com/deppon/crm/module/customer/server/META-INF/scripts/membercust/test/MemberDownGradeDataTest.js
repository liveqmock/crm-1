/**
 * 会员降级列表查询 结果 model
 */
Ext.define('MemberDownGradeDataTest',{
	extend:'MemberCustBasicDataTest',
	memberDownGradeStoreTest:null,
	//会员升级列表查询结果store
	getMemberDownGradeCustStore: function() {
		return this.memberDownGradeStoreTest;
	},
	//初始化会员升级列表查询结果store
	initMemberDownGradeCustStore: function(beforeLoadTransactionFn) {
		
	}
});