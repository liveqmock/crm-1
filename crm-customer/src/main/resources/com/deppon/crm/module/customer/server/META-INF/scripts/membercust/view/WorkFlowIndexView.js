/**
* 我的工作流 管理界面
*/
//全局变量  处理的：APPROVED approved 申请的：APPLIED applied
var SerachType = 'APPLIED';
Ext.onReady(function(){
	var params = ['WORKFLOW_NAME',// 工作流名称
      			  'WORKFLOW_STATE'// 流程状态
	              ];
	initDataDictionary(params);
	//显示会员修改 内容描述
	new ModifyMemberControl().getModifyMember();
	Ext.create('Ext.container.Viewport',{
		layout:'fit',
//		autoScroll:true,
		items:[Ext.create('WorkFlowManagerPanel',{'serachType':SerachType,'workFlowManagerData':workFlowManagerDataControl})]
	});
});