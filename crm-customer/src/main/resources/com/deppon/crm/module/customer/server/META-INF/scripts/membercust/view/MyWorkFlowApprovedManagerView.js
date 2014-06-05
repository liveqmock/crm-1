/**
* 我已处理的工作流 管理界面
* date层用我的工作流date层
*/
//全局变量  处理的：APPROVED approved 申请的：APPLIED applied
var SerachType = 'APPROVED';
Ext.onReady(function(){
	var params = ['WORKFLOW_NAME',// 工作流名称
      			  'WORKFLOW_STATE'// 流程状态
	              ];
	initDataDictionary(params);
	//显示会员修改 内容描述
	new ModifyMemberControl().getModifyMember();
	Ext.create('Ext.container.Viewport',{
		layout:'fit',
		items:[Ext.create('WorkFlowManagerPanel',{'serachType':SerachType,'workFlowManagerData':workFlowManagerDataControl})]
	});
});


