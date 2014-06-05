//会员升级列表查询 结果 model
Ext.define('MemberUpgradeListStoreTest',{
	extend:'Ext.data.Store',
	model:'MemberUpgradeListModel',
	autoLoad:true,
	proxy:{
		type:'memory',
		data:[{belongDeptId:'1',belongDeptName:'1',custNumber:'1',custName:'1',linkName:'1',linkPhone:'1',linkTel:'1',currentLevel:'1',targetLevel:'1'}
		      ,{belongDeptId:'2',belongDeptName:'1',custNumber:'1',custName:'1',linkName:'1',linkPhone:'1',linkTel:'1',currentLevel:'1',targetLevel:'1'}
		      ,{belongDeptId:'3',belongDeptName:'1',custNumber:'1',custName:'1',linkName:'1',linkPhone:'1',linkTel:'1',currentLevel:'1',targetLevel:'1'}
		      ,{belongDeptId:'4',belongDeptName:'1',custNumber:'1',custName:'1',linkName:'1',linkPhone:'1',linkTel:'1',currentLevel:'1',targetLevel:'1'}
		      ,{belongDeptId:'5',belongDeptName:'1',custNumber:'1',custName:'1',linkName:'1',linkPhone:'1',linkTel:'1',currentLevel:'1',targetLevel:'1'}
		      ,{belongDeptId:'6',belongDeptName:'1',custNumber:'1',custName:'1',linkName:'1',linkPhone:'1',linkTel:'1',currentLevel:'1',targetLevel:'1'}
		      ,{belongDeptId:'7',belongDeptName:'1',custNumber:'1',custName:'1',linkName:'1',linkPhone:'1',linkTel:'1',currentLevel:'1',targetLevel:'1'}
		      ,{belongDeptId:'8',belongDeptName:'1',custNumber:'1',custName:'1',linkName:'1',linkPhone:'1',linkTel:'1',currentLevel:'1',targetLevel:'1'}
		]
	}
});
Ext.define('MemberUpgradeDataTest', {
	extend:'MemberCustBasicDataTest',
	memberUpgradeStoreTest:null,
	//会员升级列表查询结果store
	getMemberUpGradeCustStore: function() {
		return this.memberUpgradeStoreTest;
	},
	//初始化会员升级列表查询结果store
	initMemberUpGradeCustStore: function(beforeLoadTransactionFn) {
		if(this.memberUpgradeStoreTest == null){
			this.memberUpgradeStoreTest = Ext.create('MemberUpgradeListStoreTest');
		}
		return this.memberUpgradeStoreTest;
	}
});
