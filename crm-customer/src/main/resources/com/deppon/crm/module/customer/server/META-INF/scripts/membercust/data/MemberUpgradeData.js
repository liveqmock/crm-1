/**
*会员升级Data层
*/

//判断是否导入测试数据
(function() {
	var memberUpgradeDataTest = "../scripts/membercust/test/MemberUpgradeDataTest.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + memberUpgradeDataTest + '"></script>');
	}
})();
/**
 * 会员升级列表查询 结果 model
 */
Ext.define('MemberUpgradeListModel',{
	extend:'Ext.data.Model',
	fields:[//所属部门id
			{name:'belongDeptId'},
			//所属部门name
			{name:'belongDeptName'},
			//客户编号
			{name:'custNumber'},
			//客户名称
			{name:'custName'},
			//主联系人姓名
			{name:'linkName'},
			//主联系人手机
			{name:'linkPhone'},
			//主联系人电话
			{name:'linkTel'},
			//当前等级
			{name:'currentLevel'},
			//目标等级
			{name:'targetLevel'}]
});
//会员升级列表查询 结果 model
Ext.define('MemberUpgradeListStore',{
	extend:'Ext.data.Store',
	model:'MemberUpgradeListModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		api:{
			read:'searchUpMemberList.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'memberUpgradeList',
			totalProperty:'totalCount'
		}
	}
});

Ext.define('MemberUpgradeData',{
	extend:'MemberCustBasicData',
	memberUpgradeStore:null,
	//会员升级列表查询结果store
	getMemberUpGradeCustStore: function() {
		return this.memberUpgradeStore;
	},
	//初始化会员升级列表查询结果store
	initMemberUpGradeCustStore: function(beforeLoadTransactionFn) {
		if(this.memberUpgradeStore == null){
			if(beforeLoadTransactionFn != null){
				this.memberUpgradeStore = Ext.create('MemberUpgradeListStore',{
					listeners:{
						beforeload:beforeLoadTransactionFn
					}
				});
			}else{
				this.memberUpgradeStore = Ext.create('MemberUpgradeListStore');
			}
		}
		return this.memberUpgradeStore;
	}
});