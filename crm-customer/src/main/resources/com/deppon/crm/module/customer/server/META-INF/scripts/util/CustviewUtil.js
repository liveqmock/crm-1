CustviewUtil = function(){};
CustviewUtil.openSimpleCustview=function(custNumber){
  var url="../custview/simpleMemberViewIndex.action?custNumber="+custNumber;
  Ext.create('Ext.window.Window', {
      title: '简版客户360视图',
      height: 730,
      resizable:false,
      model:true,
      width: 810,
      html: '<iframe id="'+id+'frame" src="'+url+'" style="height:100%;width:100%;" frameborder="0"></iframe>'
  }).show();
};
/**
 * 给工单模块提供调用客户详情页面
 * @param {} custId
 */
CustviewUtil.openMemberWindow=function(custId){
  	var url="../customer/viewMemberDetail.action?custId="+custId;
	Ext.create('PopWindow', {
	      title: '客户详情',
	      closeAction:'destroy',
	      height: 600,
	      id:'memberDetailIdForComp',
	      modal:true,
	      width: 820,
	      cls:'openMemberWindow',
	      html: '<iframe src="'+url+'" style="height:100%;width:100%;" frameborder="0"></iframe>'
  	}).show();
};