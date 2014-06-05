var attachmentSize = 0;//文件叠加大小

/**
 * 责任反馈内容输入panel
 */
Ext.define('DutyFeedbackContextPanel',{
	extend:'BasicFormPanel', 
	items:null,border:0,
	layout:{type:'table',columns:1},
	initComponent:function(){			
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [
		    {//责任反馈
				xtype:'textarea',width:660,labelWidth:60,
				maxLength:300,
				regex : new RegExp('^[^"]{0,}$'),
				regexText:i18n('i18n.Duty.pleaseNotInputDoubleYin'),
				emptyText:i18n('i18n.DutySearch.maxLength300'),
				allowBlank:false,blankText:i18n('i18n.DutyEditableResultView.notNull'),//不能为空
				fieldLabel:'<span style="color:#FF0000">*</span>'+i18n('i18n.DutyFeedbackDetailView.responsibilityForFeedback'),//责任反馈
				height:70,name:'dutyFeedbackContent'
			}
	    ];
	}
});

/**
 * 上传附件panel
 */
Ext.define('DutyFeedbackUploadFilePanel',{
	extend:'BasicFormPanel', 
	items:null,
	border:0,
	layout:{type:'hbox'},
	initComponent:function(){			
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [
			{
				xtype:'filefield',name:'file',id:'file',flex:0.6,
				fieldLabel:i18n('i18n.DutyFeedbackOperationView.attachmentUpload'),//附件上传
				labelWidth:65,buttonText:i18n('i18n.DutyFeedbackOperationView.browse')//浏览
			},
			{xtype:'button',id:'fileBtnId',margin:'0 0 5 5',scope:me,handler:me.updateFile,text:i18n('i18n.DutyFeedbackOperationView.upload')},//上传
			{name:'type',xtype:'hiddenfield',value:'recompenceAttDir'},
			{name:'imageOrAttachment',xtype:'hiddenfield',value:'attachment'},
			{name:'attachmentSize',xtype:'hiddenfield'},
			{name:'maxSizeLimit',xtype:'hiddenfield',value:'30M'}
		];
	},
	updateFile:function(){
		var me = this;
		
		if(Ext.isEmpty(Ext.getCmp('file').getValue())){
			MessageUtil.showInfoMes(i18n('i18n.DutyFeedbackOperationView.pleaseSelectUpLoadFile'));return;//请选择上传的文件
		}
		
		var grid = Ext.getCmp('dutyFeedbackFileListGridId');
		Ext.getCmp('fileBtnId').setDisabled(true);
		
		me.submit({
            url:'../common/fileUpload.action',
            waitMsg:i18n('i18n.DutyFeedbackOperationView.loading'),//i18n('i18n.DutyFeedbackOperationView.loading'),
            success:function(form,response) {
            	Ext.getCmp('fileBtnId').setDisabled(false);
            	var result = response.result;
            	if(!result.success){
            		MessageUtil.showErrorMes(result.message);return;
				}
				var fileInfo = result.fileInfoList[0];
        		attachmentSize += fileInfo.fileSize;
        		me.getForm().findField('attachmentSize').setValue(attachmentSize);
        		me.getForm().findField('file').setValue('');
        		var fileInfoRecord = Ext.create('FeedAttachModel',{
        			'attachName':fileInfo.fileName,
        			'attachPath':fileInfo.savePath,
        			'fileSize':fileInfo.fileSize
        		});//创建选择的上传附件记录
        		
        		grid.getStore().add(fileInfoRecord);
        		if(grid.getStore().getCount()==10){
					Ext.getCmp('fileBtnId').setDisabled(true);
				}
            	MessageUtil.showInfoMes(i18n('i18n.DutyFeedbackOperationView.attachmentUpload') + fileInfo.fileName + i18n('i18n.DutyFeedbackOperationView.upLoadSuccess'));//附件上传//上传成功
           },
           failure:function(form, response){
            	Ext.getCmp('fileBtnId').setDisabled(false);
             	var result = response.result;
             	if(!result.success){
                   	MessageUtil.showErrorMes(result.message);
				}
           }
       });
	}
});

/**
 * 上传附件列表
 */
Ext.define('DutyFeedbackFileListGrid',{
	extend:'PopupGridPanel',
	defaults:{align:'center'},
	autoScroll:true,
	initComponent:function(){ 
		var me = this;
		if(Ext.isEmpty(me.selModel)){
			me.selModel = new Ext.selection.CheckboxModel();
		}
		me.store = Ext.create('FeedAttachListStore');
		
		me.columns = [
		    {xtype:'rownumberer',header:i18n('i18n.Duty.resultDutyPanel.rownumberer'),width:40},//序号
		    {header:i18n('i18n.DutyFeedbackOperationView.accessoriesName'),dataIndex:'attachName',width:120},//附件名称
		    {
		    	header :i18n('i18n.DutyFeedbackOperationView.accessoriesDescription'),dataIndex:'describe',flex:1,editor:{//附件说明
		    		xtype:'textfield',allowBlank:false,maxLength:50,blankText:i18n('i18n.DutyEditableResultView.notNull')//不能为空
		    	}
		    },
		    {
		    	header :i18n('i18n.DutyFeedbackOperationView.op'),width:100,align:'center',renderer:function(){//操作
		    		return '<a href="javascript:;" onclick="deleteAttach()">'+i18n('i18n.duty.delete')+'<a>';//删除
		    	}
		    }
	    ];
   	 	this.callParent();
   },
   plugins:Ext.create('Ext.grid.plugin.CellEditing',{clicksToEdit:1})
});
   
///**
// * 区域统计员所在部门选择panel
// */
//Ext.define('DutyFeedbackEmpDeptPanel',{
//	extend:'BasicFormPanel', 
//	items:null,
//	border:0,
//	layout:{type:'table',columns:1},
//	actuaryDepartmentStore:null,/*统计员所在部门*/
//	initComponent:function(){
//		if(Ext.isEmpty(this.actuaryDepartmentStore)){
//			this.actuaryDepartmentStore = Ext.create('ActuaryDepartmentStore');
//			this.actuaryDepartmentStore.load();
//		}
//		this.items = this.getItems();
//		this.callParent();
//	},
//	getItems:function(){
//		var me = this;
//		return [
//			{/*任务部门*/
//				xtype:'combobox',queryMode:'local',listConfig: {loadMask:false},
//				width:660,labelWidth:160,forceSelection:true,
//				fieldLabel:i18n('i18n.DutyFeedbackOperationView.pleaseSelectRegionalEnumeratorsDepartment'),//请选择区域统计员所在部门
//				name:'dealName',id:'actuaryDealNameId',store:me.actuaryDepartmentStore
//				,displayField:'deptName',valueField:'id'
//			}
//	    ];
//	}
//});

/**
 * 单击”责任反馈“按钮出现的操作页面整体布局panel
 */
Ext.define('DutyFeedbackOperationPanel', {
	extend : 'BasicPanel',
	layout : 'border',
	items :[
	  {//责任反馈输入框
		xtype:'container',
		region:'north',
		layout:'fit',
		items:[Ext.create('DutyFeedbackContextPanel',{id:'dutyFeedbackContextPanelId'})]
	  },{
		xtype:'container',
		layout:'border',
		region:'center',
		items:[{//上传附件
			xtype:'container',//container可以去除panel的边框
			layout:'fit',
			region:'north',
			items:[Ext.create('DutyFeedbackUploadFilePanel',{id:'dutyFeedbackUploadFilePanelId'})]	  
		  },{//上传附件列表
			xtype : 'container',
			layout:'fit',
			region:'center',
			items:[Ext.create('DutyFeedbackFileListGrid',{id:'dutyFeedbackFileListGridId'})]
		  }]
	  }
	]
});	


/**
 * 单击查看工单详情按钮，弹出的工单详情窗口
 * xiaohongye
*/
Ext.define('DutyFeedbackOperationWindow',{
	extend:'PopWindow',
	title:i18n('i18n.DutyFeedbackOperationView.responsibilityWorkOrderFeedback'),//工单责任反馈
	width:700,
	height:400,
	modal:true,
	layout:'fit',
	closeAction:'hide',
	items:[Ext.create('DutyFeedbackOperationPanel',{'id':'dutyFeedbackOperationPanelId'})],
	listeners:{
		hide:function(){
			document.body.scrollLeft=0;
			document.body.scrollTop=0;
			document.getElementsByTagName("html")[0].style.overflowY="hidden";
			document.getElementsByTagName("html")[0].style.overflowX="hidden";
			Ext.getCmp('dutyFeedbackFileListGridId').getStore().removeAll();
			Ext.getCmp('dutyFeedbackOperationPanelId').down('textarea').reset();
			Ext.getCmp('dutyFeedbackUploadFilePanelId').getForm().reset();
			Ext.getCmp('fileBtnId').setDisabled(false);
			Ext.getCmp('dutyFreedbackSubmitButton').setDisabled(false);
		}
	},
	initComponent:function(){
		this.dockedItems = this.getMyDockedItems();
		this.callParent();
	},
	getMyDockedItems:function(){
		var me = this;
		return [{
		    xtype: 'toolbar',dock: 'bottom',ui:'footer',items: [
				'->',
				{//提交
					xtype:'button',text:i18n('i18n.duty.submit'),//提交
					id:'dutyFreedbackSubmitButton',
					handler:function(){
						var me_button=this;
						var grid = Ext.getCmp('dutyFeedBackResultGridId');
						var records=grid.getSelectionModel().getSelection();
						//判断是否选中行
						if (records.length == 0) {return;}
						var feedbackContextForm = Ext.getCmp('dutyFeedbackContextPanelId').getForm();
						
						if(!feedbackContextForm.isValid()){return;}
						
						var feedAttachStore = Ext.getCmp('dutyFeedbackFileListGridId').getStore();
						var feedAttachList= new Array();
						var check_result = {'flag':true};
						feedAttachStore.each(function(record){
							if(Ext.isEmpty(record.get('describe'))){
								check_result = {'flag':false,'msg':i18n('i18n.DutyFeedbackOperationView.annexDescribesNotEmpty')};//附件描述不能为空
								return false;
							}
							feedAttachList.push(record.data);
						});
						if(check_result.flag == false){
							MessageUtil.showInfoMes(check_result.msg);return;
						}
						me_button.setDisabled(true);
						var param = {
							searchDutyCondition:{
								'id':records[0].get('dutyId'),
								'dutyFeedback':feedbackContextForm.findField('dutyFeedbackContent').getValue(),
								'dutyResultId':records[0].get('dutyResultId')
							},
							feedAttachList:feedAttachList
						};
						var successFn = function(response){
							MessageUtil.showInfoMes(i18n('i18n.DutyFeedbackOperationView.responsibilityFeedbackSuccess'));
							grid.getStore().load();
							me.close();
							Ext.getCmp("dutyFeedbackDetailWindowId").close();/*关闭详情*/
						};
						var failureFn = function(response){
							if(!Ext.isEmpty(response)){
								MessageUtil.showMessage(response.message);
							}
						};
						DutyFeedbackStore.prototype.dutyFeedback(param,successFn,failureFn);
					}
				},
				{xtype:'button',text:i18n('i18n.duty.cancel'),handler:function(){me.close();}//取消
			}]
		}];
	} 
});

//删除附件
function deleteAttach(){
	var grid = Ext.getCmp('dutyFeedbackFileListGridId');
	var records=grid.getSelectionModel().getSelection();
	if(records.length!=0){
		for ( var i = 0; i < records.length; i++) {
			attachmentSize -= records[i].data.fileSize;
		}
		Ext.getCmp('dutyFeedbackUploadFilePanelId').getForm().findField('attachmentSize').setValue(attachmentSize);
		grid.getStore().remove(records);
	}
	Ext.getCmp('fileBtnId').setDisabled(false);
}

