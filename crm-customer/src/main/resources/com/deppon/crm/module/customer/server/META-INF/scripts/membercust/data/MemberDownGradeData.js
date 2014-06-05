/**
*会员降级Data层
*/

//判断是否导入测试数据
(function() {
	var memberDownGradeDataTest = "../scripts/membercust/test/MemberDownGradeDataTest.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + memberDownGradeDataTest + '"></script>');
	}
})();
/**
 * 会员降级列表查询 结果 model
 */
Ext.define('MemberDownGradeListModel',{
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
//会员降级列表查询 结果 model
Ext.define('MemberDownGradeListStore',{
	extend:'Ext.data.Store',
	model:'MemberDownGradeListModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		api:{
			read:'searchDownMemberList.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'memberDemotionList',
			totalProperty:'totalCount'
		}
	}
});
Ext.define('MemberDownGradeData',{
	extend:'MemberCustBasicData',
	memberDownGradeStore:null,
	//会员升级列表查询结果store
	getMemberDownGradeCustStore: function() {
		return this.memberDownGradeStore;
	},
	//初始化会员升级列表查询结果store
	initMemberDownGradeCustStore: function(beforeLoadTransactionFn) {
		if(this.memberDownGradeStore == null){
			if(beforeLoadTransactionFn != null){
				this.memberDownGradeStore = Ext.create('MemberDownGradeListStore',{
					listeners:{
						beforeload:beforeLoadTransactionFn
					}
				});
			}else{
				this.memberDownGradeStore = Ext.create('MemberDownGradeListStore');
			}
		}
		return this.memberDownGradeStore;
	}
});