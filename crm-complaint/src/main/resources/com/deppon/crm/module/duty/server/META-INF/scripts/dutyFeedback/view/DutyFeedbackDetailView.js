/**
 * 工单责任反馈详情页面整体布局panel
 */
Ext.define('DutyFeedbackDetailPanel', {
	extend : 'BasicPanel',
	layout : 'border',
	items :[
	  {//工单详情基本工单信息
		xtype:'container',
		region:'north',
		layout:'fit',
		items:[Ext.create('DutyComplaintDetailBasicInfoPanel',{id:'DutyComplaintDetailBasicInfoPanelId'})]
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
			items:[Ext.create('DutyFeedbackDealProcedureGrid',{id:'DutyFeedbackDealProcedureGridId'})]	  
		  },{//责任划分结果列表
			xtype : 'container',
			layout:'fit',
			region:'center',
			items:[Ext.create('DutyAllocationResultDeptGrid',{title:i18n('i18n.Duty.DutyApproval.DutyAllocationResult'),id:'DutyAllocationResultDeptGridId'})]//责任划分结果
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
		items:[Ext.create('DutyFeedbackRecordListGrid',{id:'DutyFeedbackRecordListGridId'})]
       }
	]
});	


/**
 * 单击查看工单详情按钮，弹出的工单详情窗口
 * xiaohongye
*/
Ext.define('DutyFeedbackDetailWindow',{
	extend:'PopWindow',
	title:i18n('i18n.DutyFeedbackDetailView.theWorkOrdersResponsibilitiesFeedbackDetails'),//工单责任反馈详情
	width:820,
	height:750,
	modal:true,
	layout:'fit',
	closeAction:'hide',
	items:[Ext.create('DutyFeedbackDetailPanel',{'id':'dutyFeedbackDetailPanelId'})],
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
		    xtype: 'toolbar',dock: 'bottom',ui: 'footer', items: [
		    {
				xtype:'button',text:i18n('i18n.Duty.DutyApproval.SearchComplaintDetail'),//查看工单详情
				handler:function(btn){
					var selectRecords = Ext.getCmp('dutyFeedBackResultGridId').getSelectionModel().getSelection();
					show_comp_detailsWin(selectRecords[0].get('complaintid'));
			    }
			},
			'->',
			{//责任认领
				xtype:'button',id:'feedback_dutyClaimBtn',
				text:i18n('i18n.DutyFeedbackDetailView.responsibilityUnclaimed'),//责任认领
				handler:function(){
					var grid = Ext.getCmp('dutyFeedBackResultGridId');
					var records=grid.getSelectionModel().getSelection();
					//判断是否选中行
					if (records.length == 0) {return;}
					
					var param = {searchDutyCondition:{
						'id':records[0].get('dutyId'),
						'dutyResultId':records[0].get('dutyResultId')
					}};
					var successFn = function(response){
						MessageUtil.showInfoMes(i18n('i18n.DutyFeedbackDetailView.responsibilityClaimSuccess'));//责任认领成功
						grid.getStore().load();
						me.close();
					};
					var failureFn = function(response){
						if(!Ext.isEmpty(response.message)){
							MessageUtil.showMessage(response.message);
						}
					};
					//用户确认是否进行责任认领
				    MessageUtil.showQuestionMes(i18n('i18n.DutyFeedbackDetailView.youConfirmTheWorkOrderNumber'), function(e) {
				    	//您确认该工单是贵部原因产生的？
	            		if (e == 'yes') {
	            			DutyFeedbackStore.prototype.dutyClaim(param,successFn,failureFn);
	            		}
				    });	
				}
			},{//责任反馈
				xtype:'button',id:'feedback_feedbackBtn',
				text:i18n('i18n.DutyFeedbackDetailView.responsibilityForFeedback'),//责任反馈
				handler:function(){
					var dutyFeedbackOperationWindow = Ext.getCmp("dutyFeedbackOperationWindowId");//获取工单详情窗口
					if(!dutyFeedbackOperationWindow){
						dutyFeedbackOperationWindow = Ext.create('DutyFeedbackOperationWindow',{id:'dutyFeedbackOperationWindowId'});
					}
					dutyFeedbackOperationWindow.show();
					Ext.getCmp('dutyFeedbackContextPanelId').getForm().findField('dutyFeedbackContent').setValue('');
					Ext.getCmp('dutyFeedbackFileListGridId').getStore().remove();
					Ext.getCmp('dutyFeedbackEmpDeptPanelId').getForm().findField('dealName').setValue('');
					
				}
			},{//关闭
				xtype:'button',text:i18n('i18n.Duty.DutyApproval.Close'),
				handler:function(){me.close();}
			}]
		}];
	}
});


