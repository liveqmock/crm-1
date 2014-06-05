
var openOrderViewWindow=function(orderNumber){
  	var url="../order/orderDetail.action?orderNumber="+orderNumber;
	var orderWin=Ext.create('PopWindow', {
	      title: '订单详情页',
	      closeAction:'destroy',
	      id:'orderDetailView',
	      height: 600,
	      modal:true,
	      width: 820,
	      cls:'openMemberWindow',
	      buttons:[
				{text:i18n('i18n.complaintReport.btn_close'),handler:function(){orderWin.close();}}
			],
	      html: '<iframe src="'+url+'" style="height:100%;width:100%;" frameborder="0"></iframe>'
  	}).show();
};