/**
 * 工单责任查询后现实的详情页面
 */

/**
 * 工单责任查询详情页面整体布局panel
 */
Ext.define('DutySearchDetailPanel', {
	extend : 'BasicPanel',
	layout : 'border',
	items :[
	  {//工单详情基本工单信息
		xtype:'container',
		region:'north',
		layout:'fit',
		items:[Ext.create('DutyComplaintDetailBasicInfoPanel',{'id':'DutyComplaintDetailBasicInfoPanelId'})]
	  },{
		xtype:'container',
		layout:'border',
		region:'center',
		items:[
		  {//处理经过列表
			xtype:'container',//container可以去除panel的边框
			layout:'fit',
			region:'north',
			height:120,
			items:[Ext.create('DutyFeedbackDealProcedureGrid',{'id':'DutyFeedbackDealProcedureGridId'})]	  
		  },{//责任划分结果列表
			xtype : 'container',
			layout:'fit',
			region:'center',
			items:[Ext.create('DutyAllocationResultDeptGrid',{'id':'DutyAllocationResultDeptGridId'})]
		  },{//通知对象
			xtype:'container',//container可以去除panel的边框
			layout:'fit',
			region:'south',
			items:[Ext.create('DutyFeedbackInfoToPanel',{'id':'DutyFeedbackInfoToPanelId'})]	
		  }      
		]
	  },{//责任反馈记录列表
  	    xtype : 'container',
		layout:'fit',
		region:'south',
		height:120,
		items:[Ext.create('DutyFeedbackRecordListGridView',{'id':'DutyFeedbackRecordListGridId'})]
       }
	]
});	


/**
 * 单击查看详情按钮，弹出的工单责任详情窗口
 * xiaohongye
*/
Ext.define('DutySearchDetailWindow',{
	extend:'PopWindow',
	title:i18n('i18n.DutySearchDetailView.dutyDetails'),
	alias : 'widget.basicwindow',
	width:820,
	height:750,
	modal:true,
	layout:'fit',
	closeAction:'hide',
	items:[Ext.create('DutySearchDetailPanel',{'id':'dutySearchDetailPanelId'})],
	listeners:{
		hide:function(){
			document.body.scrollLeft=0;
			document.body.scrollTop=0;
			document.getElementsByTagName("html")[0].style.overflowY="hidden";
			document.getElementsByTagName("html")[0].style.overflowX="hidden";
		}
	},
	dockedItems: [{
	    xtype: 'toolbar',
	    dock: 'bottom',
	    ui: 'footer',
	    items: [{//查看工单详情
			xtype:'button',
			text:i18n('i18n.Duty.DutyApproval.SearchComplaintDetail'),
			handler:function(btn){
				var selectRecords = Ext.getCmp('dutyQueryResultGridId').getSelectionModel().getSelection();
				show_comp_detailsWin(selectRecords[0].get('complaintId'));
		    }
		},
		'->',
		{//关闭
			xtype:'button',
			text:i18n('i18n.Duty.DutyApproval.Close'),
			handler:function(){
				dutySearchDetailWindow.close();
			}
		}
		//去掉“查看附件按钮”，这里不需要
		/*,{//查看附件
			xtype:'button',
			text:i18n('i18n.Duty.DutyApproval.AttachmentDetail'),
			handler:function(){
				var dutyAttachmentListWindow = Ext.getCmp("dutyAttachmentListWindowId");//获取工单详情窗口
				if(!dutyAttachmentListWindow){
					dutyAttachmentListWindow = Ext.create('DutyAttachmentListWindow',{id:'dutyAttachmentListWindowId'});
				}
				dutyAttachmentListWindow.show();
			}
		}*/]
	}]
});

var dutySearchDetailWindow = Ext.getCmp("dutySearchDetailWindowId");
if(!dutySearchDetailWindow){
	dutySearchDetailWindow = Ext.create('DutySearchDetailWindow',{id:'dutySearchDetailWindowId'});
}

