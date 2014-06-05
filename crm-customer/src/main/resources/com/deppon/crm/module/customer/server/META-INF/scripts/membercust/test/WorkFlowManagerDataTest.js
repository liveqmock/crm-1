
Ext.define('WorkFlowManagerDataTest',{
	extend:'MemberCustBasicDataTest',
	workFlowManagerStoreTest:null,
	//会员升级列表查询结果store
	getWorkFlowManagerStore: function() {
		return this.memberUpgradeStore;
	},
	//初始化会员升级列表查询结果store
	initWorkFlowManagerStore: function(beforeLoadTransactionFn) {
		if(this.workFlowManagerStoreTest == null){
//			this.workFlowManagerStoreTest = Ext.create('MemberUpgradeListStore');
		}
		return this.workFlowManagerStoreTest;
	}
});