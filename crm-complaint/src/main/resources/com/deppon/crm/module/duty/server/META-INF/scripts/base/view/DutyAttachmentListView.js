/**
 * 附件列表
 */
Ext.define('DutyFeedbackAttachmentListGrid',{
	extend:'PopupGridPanel',
	title:i18n('i18n.DutyFeedbackDetailView.listOfAttachments'),
	defaults:{
		align:'center'
	},
	autoScroll:true,
	listeners:{
		'cellclick':function(me, td, cellIndex,record, tr, rowIndex){
			if(cellIndex==1&&!Ext.isEmpty(record.data)){
				window.location.href="../common/downLoad.action?fileName="+record.data.attachName+"&inputPath="+record.data.attachPath;
			}
		}
	},
	initComponent:function(){ 
		var me = this;
		me.store = Ext.create('FeedAttachListStore');
		me.columns = [
		    {xtype:'rownumberer',header:i18n('i18n.Duty.resultDutyPanel.rownumberer'),width:40},
		    {
		    	header:i18n('i18n.DutyFeedbackOperationView.accessoriesName'),
		    	dataIndex:'attachName',
		    	width:120,
		    	renderer: function(value) {
		            return '<a href="javascript:">'+value+'</a>';
		        }
		    },
		    {header :i18n('i18n.DutyFeedbackOperationView.accessoriesDescription'),dataIndex:'describe',flex:1}
	    ];
    this.callParent();
   }
});

/**
 * 单击”查看附件“按钮出现的附件列表页面整体布局panel
 */
Ext.define('DutyAttachmentListPanel', {
	extend : 'BasicPanel',
	layout : 'fit',
	items:[Ext.create('DutyFeedbackAttachmentListGrid',{id:'dutyFeedbackAttachmentListGridId'})]		  
});	


/**
 * 附件列表弹出窗口
 * xiaohongye
*/
Ext.define('DutyAttachmentListWindow',{
	extend:'PopWindow',
	title:i18n('i18n.DutyFeedbackDetailView.listOfAttachments'),
	alias : 'widget.basicwindow',
	width:480,
	height:300,
	modal:true,
	layout:'fit',
	closeAction:'hide',
	items:[Ext.create('DutyAttachmentListPanel',{'id':'dutyAttachmentListPanelId'})],
	listeners:{
		hide:function(){
			document.body.scrollLeft=0;
			document.body.scrollTop=0;
			document.getElementsByTagName("html")[0].style.overflowY="hidden";
			document.getElementsByTagName("html")[0].style.overflowX="hidden";
		}
	},
	initComponent:function(){
			this.dockedItems = this.getMyDockedItems();
			this.callParent();
	},
	getMyDockedItems:function(){
		var me = this;
		return [{
		    xtype: 'toolbar', dock: 'bottom', ui: 'footer',
		    items: [
				'->',{xtype:'button',text:i18n('i18n.Duty.DutyApproval.Close'),handler:function(){me.close();}
			}]
		}];
	}
});

var dutyAttachmentListWindow = Ext.getCmp("dutyAttachmentListWindowId");//获取工单详情窗口
if(!dutyAttachmentListWindow){
	dutyAttachmentListWindow = Ext.create('DutyAttachmentListWindow',{id:'dutyAttachmentListWindowId'});
}

