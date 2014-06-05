//配合简版客户360增加的js文件
CustviewUtil = function(){};
CustviewUtil.openSimpleCustview=function(custNumber){
	var url="../custview/simpleMemberViewIndex.action?custId="+custNumber;
	Ext.create('Ext.window.Window', {
	    title: i18n('i18n.scheduleManagement.simpleCust360View'),
	    height: 730,
	    resizable:false,
	    model:true,
	    width: 810,
	    html: '<iframe id="'+id+'frame" src="'+url+'" style="height:100%;width:100%;" frameborder="0"></iframe>'
	}).show();
};
/**
 * 潜散客详情
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