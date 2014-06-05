/**
 * 待办工单处理页面
 */

//reporttype为“投诉”（COMPLAINT）时，能够使用“完成处理”按钮的状态 
var statusForFinishProcessComp=[
	 'DEPT_RETURNED_PROCESS'/*部分部门退回，从处理过来的*/
	,'DEPT_RETURNED_APPROVAL'/*部分部门退回，从审批过来的*/
	,'DEPT_RETURNED_UPGRADE'/*部分部门退回，从升级过来的*/
	,'DEPT_RETURNED_PROCESS_ALL'/*所有部门退回，从处理过来的*/
	,'DEPT_RETURNED_APPROVAL_ALL'/*所有部门退回，从审批过来的*/
	,'DEPT_RETURNED_UPGRADE_ALL'/*所有部门退回，从升级过来的*/
	,'APPROVAL_RETURNED'/*投诉审批退回*/
	,'UPGRADED'/*投诉已升级*/
	,'UPGRADED_RETURNED'/*投诉升级退回*/
	,'WAIT_APPROVAL'/*投诉待审批*/

];

//reporttype为“投诉”（COMPLAINT）时，能够使用“提交审批”按钮的状态
var statusForSubmitComp=[
	'DEPT_RETURNED_PROCESS_ALL'/*所有部门退回，从处理过来的*/
	,'DEPT_RETURNED_APPROVAL_ALL'/*所有部门退回，从审批过来的*/
	//,'DEPT_RETURNED_UPGRADE_ALL'/*所有部门退回，从升级过来的*/
	,'APPROVAL_RETURNED'/*投诉审批退回*/
	,'UPGRADED_RETURNED'/*投诉升级退回*/
];

//reporttype为“投诉”（COMPLAINT）时，能够使用"投诉升级"按钮的状态
var statusForComplaintUpgradeComp=[
	'DEPT_RETURNED_PROCESS_ALL'/*所有部门退回，从处理过来的*/
	,'DEPT_RETURNED_APPROVAL_ALL'/*所有部门退回，从审批过来的*/
	//,'DEPT_RETURNED_UPGRADE_ALL'/*所有部门退回，从升级过来的*/
	,'APPROVAL_RETURNED'/*投诉审批退回*/
	,'UPGRADED_RETURNED'/*投诉升级退回*/
];

//reporttype为“投诉”（COMPLAINT）时，能够使用“退回提交人”按钮的状态
var statusForReturnSubmittorComp=[ 
	'DEPT_RETURNED_UPGRADE_ALL'/*所有部门退回，从升级过来的*/
	,'UPGRADED'/*投诉已升级*/
	,'WAIT_APPROVAL'/*投诉待审批*/
];

//reporttype为“投诉”（COMPLAINT）时，能够使用“退回上报人”按钮的状态
var statusForReporterComp=[
	'DEPT_RETURNED_PROCESS_ALL'/*所有部门退回，从处理过来的*/
	,'DEPT_RETURNED_APPROVAL_ALL'/*所有部门退回，从审批过来的*/
	,'APPROVAL_RETURNED'/*投诉审批退回*/
	,'UPGRADED_RETURNED'/*投诉升级退回*/
];


//reporttype为“投诉”（COMPLAINT）时，能够显示“退单原因”列表的状态
var statusForWithdrawComp=[
	'APPROVAL_RETURNED'/*投诉审批退回*/
	,'UPGRADED_RETURNED'/*投诉升级退回*/
	,'DEPT_RETURNED_PROCESS_ALL'/*所有部门退回，从处理过来的*/
	,'DEPT_RETURNED_APPROVAL_ALL'/*所有部门退回，从审批过来的*/
	,'DEPT_RETURNED_UPGRADE_ALL'/*所有部门退回，从升级过来的*/
	,'DEPT_RETURNED_PROCESS'/*部分部门退回，从处理过来的*/
	,'DEPT_RETURNED_APPROVAL'/*部分部门退回，从审批过来的*/
	,'DEPT_RETURNED_UPGRADE'/*部分部门退回，从升级过来的*/
];

//reporttype为“投诉”（COMPLAINT）时，不能！！！！！显示“延时申请审批”按钮的状态
var statusForNotpostponeApprovalComp=[
	'DEPT_RETURNED_PROCESS'/*部分部门退回，从处理过来的*/
	,'DEPT_RETURNED_APPROVAL'/*部分部门退回，从审批过来的*/
	,'DEPT_RETURNED_UPGRADE'/*部分部门退回，从升级过来的*/
];


//reporttype为“异常”（UNUSUAL）时，能够使用“退回上报人”按钮的状态
var statusForWithdrawUnusual=[
	'DEPT_RETURNED_PROCESS_ALL'/*所有部门退回，从处理过来的*/
];

//reporttype为“异常”（UNUSUAL）时，能够使用“完成处理”按钮的状态
var statusForFinishCompleteUnusual=[
	'DEPT_RETURNED_PROCESS_ALL'/*所有部门退回，从处理过来的*/
	,'DEPT_RETURNED_PROCESS'/*部分部门退回，从处理过来的*/
];

/**
 * 根据单据状态过滤按钮
 * @param complaintStatus
 * @param statusArray
 */
function validateButtonByStatus(complaintStatus, statusArray){
	if (!Ext.isEmpty(complaintStatus) && !Ext.isEmpty(statusArray)) {
		for ( var i = 0; i < statusArray.length; i++) {
			if (complaintStatus == statusArray[i]) {
			     return true;
			}
		}
	}
   	return false;
}


/**
 * 判断是否可以显示“提交审批”按钮
 */
function validateSubmit(complaintStatus, statusArray,reporttype){
	if(reporttype=='COMPLAINT'){
		//如果是经理，不需要“提交审批”按钮
		if(isPermission('/is_manager')){
			return false;
		}else{
			//如果不是经理则做状态判断
			return validateButtonByStatus(complaintStatus, statusArray);
		}
	}
	//异常不需要提交审批
	return false;
}

/**
 * 根据reporttype和单据prostatus判断按钮是否显示
 */
function validateByReporttypeStatus(reportType,complaintStatus,complaintStatusArray,unusualComplaintStatusArray){
	if(reportType=='COMPLAINT'){
		return validateButtonByStatus(complaintStatus,complaintStatusArray);
	}else if (reportType=='UNUSUAL'){
		return validateButtonByStatus(complaintStatus,unusualComplaintStatusArray);
	}
	
	return false;
}

/** 
 * 待办工单处理 弹出框声明
 */
Ext.define('ComplaintWorkbenchProcessWindow', {
    extend:'PopWindow'
	,'complaint':null//客户工单信息,业务数据
	,ComplaintWorkbenchBtnPanel:null     // 按钮组
	,withdrawReasonPanel: null     //退回原因相关组
	,processResultPanel:null    //处理结果组
	,postponeButtonFlag:null  //标志位，判断延时审批按钮是否该存在
	,processCompleteBttnFlag:null //标志位，判断完成处理按钮是否该存在
	,width:820,height:610
	,id:'ComplaintWorkbenchProcessWindow'
	,layout: {
	        type: 'vbox',
	        pack: 'start',  
	        align: 'stretchmax'  
     }	  
    ,modal:true
	,title:i18n('i18n.complaint.waitOrder.process.title')
    ,initComponent: function() {
    	
    	//画出工单信息页面。
        var me = this;

        //工单内容panel
        
	      	me.complaintBasicFormPanel = Ext.create('ComplaintBasicFormPanel',{
	      		 flex: 3
	      		,complaint:me.complaint
	      	});			
	
	      	
	   //根据状态决定是否显示退单原因
	  	if(validateByReporttypeStatus(me.complaint.reporttype,me.complaint.prostatus,statusForWithdrawComp,statusForWithdrawComp)){      
        //退单原因的grid
			me.withdrawReasonPanel = Ext.create('WithdrawReasonGridPanel',{
			    id:'WithdrawReasonGridPanelId'
				,'complaint':me.complaint
				,flex: 3
			});
					
	  	}else{
	  		
		//处理结果组
			me.processResultPanel = Ext.create('ProcessResultGridPanel',{
			    id:'processResultGridPanelId'
				,'complaint':me.complaint
				,flex: 3
				//,padding:'0 0 0 20'
			});		
	  	}				
			
		//调用布局
		this.items = this.getItems();
		this.fbar=this.generateButtons();
        me.callParent(arguments);
        
//       me.complaintBasicFormPanel.getForm().loadRecord(new ComplaintModel(me.complaint));
    }
	,getItems:function(){
		var me = this;
			
		var panels=[me.complaintBasicFormPanel];
		
		if(me.withdrawReasonPanel!=null){
			panels.push(me.withdrawReasonPanel);
		}
		
		if(me.processResultPanel!=null){
			panels.push(me.processResultPanel);
		}
		
		
		return panels;
	}
    ,submitApprovalEvent:function(){
    	//提交审批按钮
    	var me = this;
    	Ext.create('WaitSubmitApprovalWindow',{
			complaint:me.complaint,height:500,title:i18n('i18n.complaint.btn.submit_approval')
		}).show();

    }
    ,complaintUpgradeEvent:function(){
    	var me = this;
    	 Ext.create('ComplaintWorkbenchUpgradeWindow',{
			submitBtnId:'complaintUpgrade_btnId'
			,is_submitApproval:false
			,complaint:me.complaint,title:i18n('i18n.complaint.btn.complaint_upgrade')
		}).show();
		
    }
    ,finishProcessEvent:function(){
    	var me_this = this;
		var compWBFinishProcessWindow = Ext.create('CompWBFinishProcessWindow',{
    		id:'finishProcessWindowId',complaint:me_this.complaint,parent:me_this
			,myClose:function(){
				this.close();//关闭本窗口
				me_this.closeWindow();//关闭父级
				Ext.getCmp('complaintGridId').store.loadPage(1);
			}
		}).show();
		compWBFinishProcessWindow.setWidth(compWBFinishProcessWindow.old_width);compWBFinishProcessWindow.setHeight(compWBFinishProcessWindow.old_height);
    }
    ,closeWindow:function(){
    	Ext.getCmp('ComplaintWorkbenchProcessWindow').close();
    }
    ,postponeButtonDecision:function(){   //判断延时审批按钮是否可用
    	var me=this;	
    	
    	//如果状态在列表中，则不显示该按钮，并且返回。
    	if(validateByReporttypeStatus(me.complaint.reporttype,me.complaint.prostatus,statusForNotpostponeApprovalComp,null)){
    		me.postponeButtonFlag=false;
    		return;
    	}
    	
   
    	//查找当前投诉是否有还未审批的延时申请，要是有，则直接显示延时审批按钮
    	Ext.Ajax.request({
			  url : 'isPostponeAppButtonEnable.action',
			  jsonData:{complaint:me.complaint},
			  async:false,
			  success : function(response){
				  var result = Ext.decode(response.responseText);
				  
					if(result.postponeButtonFlag){
						me.postponeButtonFlag=true;
					}else{
						me.postponeButtonFlag=false;
					}
				}   	

		   });    	
		
    }
    ,processCompleteBttnDecision:function(){   //判断“完成处理”按钮是否可用
    	var me=this;	
    	me.processCompleteBttnFlag=false;
    	var currentStatus=me.complaint.prostatus;
    	var reportType=me.complaint.reporttype;
    	//如果状态在列表中，则显示该按钮，并且返回。
    	if(validateByReporttypeStatus(reportType,currentStatus,statusForFinishProcessComp,statusForFinishCompleteUnusual)){
    		me.processCompleteBttnFlag=true;
    		return;
    	}
    	
    	//若是投诉的“反馈已解决”或是“反馈未解决”状态，则需要再检查一遍是否有退回的result，要是有，则显示完成处理按钮，否则不用
    	if((currentStatus=='FEEDBACK_UNRESOLVE'||currentStatus=='FEEDBACK_RESOLVED')&&reportType=='COMPLAINT'){
	    	
	    	Ext.Ajax.request({
				  url : 'isProcessCompleteButtonEnable.action',
				  jsonData:{complaint:me.complaint},
				  async:false,
				  success : function(response){
					  var result = Ext.decode(response.responseText);
					  
						if(result.processCompleteBttnFlag){
							me.processCompleteBttnFlag=true;
						}else{
							me.processCompleteBttnFlag=false;
						}
					}   	
	
			   });  
    	}
		
    }    
    
    ,generateButtons:function(){
    	var me = this;
    	var returnButtons=[];//所有按钮的集合
    	var reporttype=me.complaint.reporttype;
    	var status=me.complaint.prostatus;
	    	//退回上报人按钮筛选
	    	if(validateByReporttypeStatus(reporttype,status,statusForReporterComp,statusForWithdrawUnusual)){
	    		returnButtons.push(
	    				{
	    		        	xtype:'button',text:i18n('i18n.complaint.btn.return_reportPeople')
	    		        		,handler:function(){
	    		        			Ext.create('ReturnToReporterWorkbenchWindow',{complaint:me.complaint}).show();
	    		        		}
	    		        });
	    	}
    		
	    	
	    	//退回提交人按钮筛选
	    	if(validateByReporttypeStatus(reporttype,status,statusForReturnSubmittorComp,null)){
	    		returnButtons.push(
	        	    	{
	        	    		xtype:'button',text:i18n('i18n.complaint.returnToSubmittor')
	        	    		,handler:function(){
	        	    			Ext.create('ReturnToSubmittorWindow',{complaint:me.complaint}).show();
	        	    		}
	        	    	});   		        
	    		
	    	}	    	

	    	//提交审批按钮筛选
	    	if(validateSubmit(status,statusForSubmitComp,reporttype)){
	    		returnButtons.push({xtype:'button',text:i18n('i18n.complaint.btn.submit_approval'),scope:me,handler:me.submitApprovalEvent});   		        		
	    	}	    	
	    	
	    	
    		//投诉升级按钮筛选
    		if(validateByReporttypeStatus(reporttype,status,statusForComplaintUpgradeComp,null)){
    			returnButtons.push({xtype:'button',text:i18n('i18n.complaint.btn.complaint_upgrade'),scope:me,handler:me.complaintUpgradeEvent});
    		}
    		
	      	//判断延时审批按钮是否有用
	      	me.postponeButtonDecision();
    		
	    	//延时申请审批按钮筛选
	    	if(me.postponeButtonFlag){
	    		returnButtons.push(
	    					{xtype:'button',text:i18n('i18n.complaint.postponeRequest.approval')
	    		        		,handler:function(){
	    		        				Ext.create('postponeRquestWindow',{
	    		        							complaint:me.complaint
	    		        							,id:'postponeRquestWindowId'
	    		        				}).show();  
	    		        		}
	    					});
	    	}
	    	
			//完成处理按钮筛选
	    	me.processCompleteBttnDecision();
			if(me.processCompleteBttnFlag){
	    		returnButtons.push({xtype:'button',text:i18n('i18n.complaint.btn.finish_process'),scope:me,handler:me.finishProcessEvent});
			}      	
    	
    		//最后增加一个关闭按钮
    		returnButtons.push({xtype:'button',text:i18n('i18n.complaintReport.btn_close'),handler:me.closeWindow});
    	
	    	var returnList=returnButtons;
    		
    		
	    	return returnList;   	
    }
	
});




/** 
 * 退回上报人弹出框 声明
 */
Ext.define('ReturnToReporterWorkbenchWindow', {
    extend:'PopWindow'
    ,id:'ReturnToReporterWorkbenchWindowId'
	,width:600,height:340
	,layout: 'border'
	,modal:true
	,title:i18n('i18n.complaint.btn.return_reportPeople')
	,complaint:null  //投诉基本信息
	,feedbackReasonPanel:null//退回信息
	,getItems:function(){
		var me=this;
		me.feedbackReasonPanel = Ext.create('ReturnToSubmittorReasonGrid',{
		    id:'feedbackReasonPanel'
			,'complaint':me.complaint
			,region: 'center'
		});
		return [
            {
                xtype:'textareafield',region:'north',labelWidth:60,height:50,
	        	fieldLabel:i18n('i18n.comp.comp_process.return_cause'),id:'returnToReporter_feedbackreason'
            },me.feedbackReasonPanel
        ];
	}
	,initComponent:function(){
		this.items = this.getItems();
		this.fbar=this.getFbar();
		this.callParent(arguments);
    }
	,submitEvent:function(){
		processingMask.show();
		var me = this;
		var feedbackreason = Ext.getCmp('returnToReporter_feedbackreason').getValue()
		if(DpUtil.isEmpty(feedbackreason)){
			MessageUtil.showMessage(i18n('i18n.complaintTask.empty_returnCause'));
			processingMask.hide();
			return;
		}
		
		//执行成功
		var successFn = function(response){
			processingMask.hide();
			// 调用客户工单详情 弹出框
			MessageUtil.showMessage(i18n('i18n.comp.msg.succeed.return_report'));
			Ext.getCmp('ReturnToReporterWorkbenchWindowId').close();
			Ext.getCmp('ComplaintWorkbenchProcessWindow').close();
			Ext.getCmp('complaintGridId').store.loadPage(1);
			
		}
		
		//执行失败
		var failFn = function(){
			processingMask.hide();
			MessageUtil.showErrorMes(i18n('errror.common.unknow'));
		}
		DpUtil.requestJsonAjax('returnReportor.action',{
			parameterMap:{compId:me.complaint.fid+'',feedbackreason:feedbackreason}
		},successFn,failFn);
	}
	//按钮组
	,getFbar:function(){
		var me=this;
		return[{
		        	text:i18n('i18n.complaint.submit'),xtype:'button',scope:me,handler:me.submitEvent
		        }
		        ,{
		        	text:i18n('i18n.ScatterCustManagerView.close'),xtype:'button',scope:me,handler:function(){
		        			Ext.getCmp('ReturnToReporterWorkbenchWindowId').close();
						}
		         }
		      ];
	}
});


/** 
 * 退回提交人弹出框 声明
 */
Ext.define('ReturnToSubmittorWindow', {
    extend:'PopWindow'
    ,id:'returnToSubmittorWindowId'
	,width:530,height:300
	,windowId:'ComplaintWorkbenchProcessWindow'
	,layout: {
        type: 'vbox',
        pack: 'start',  
        align: 'stretchmax'  
	}
	,modal:true
	,title:i18n('i18n.complaint.returnToSubmittor')
	,complaint:null  //投诉基本信息
	,feedbackReasonPanel:null//退回信息
	,getItems:function(){
		var me=this;
		me.feedbackReasonPanel = Ext.create('WithdrawReasonGrid',{
		    id:'feedbackReasonPanel'
			,'complaint':me.complaint
			,width: 500
			,height:150
		});
		return [
		        {xtype:'textareafield',labelWidth:60,fieldLabel:i18n('i18n.comp.comp_process.return_cause'),id:'returnToSubmittor_feedbackreason'}
		        ,me.feedbackReasonPanel
		        ];
		
	}
	,initComponent:function(){
		var me = this;		
		this.items = this.getItems();
		this.fbar=this.getFbar();
		this.callParent(arguments);
    }
	,getFbar:function(){
		var me=this;
		 return [ {text:i18n('i18n.complaint.submit'),xtype:'button',scope:me,handler:me.submitEvent}
		 		 ,{ text:i18n('i18n.ScatterCustManagerView.close'),xtype:'button'
		 		   ,handler:function(){Ext.getCmp('returnToSubmittorWindowId').close();}
		          }];
	}
	,submitEvent:function(){
		var me = this;
		var feedbackreason = Ext.getCmp('returnToSubmittor_feedbackreason').getValue()
		if(DpUtil.isEmpty(feedbackreason)){
			MessageUtil.showMessage(i18n('i18n.complaintTask.empty_returnCause'));return;
		}
		
		//执行成功
		var successFn = function(response){
			// 调用客户工单详情 弹出框
			MessageUtil.showMessage(i18n('i18n.complaint.returnToSubmittor.message.success'));
			Ext.getCmp('returnToSubmittorWindowId').close();
			Ext.getCmp(me.windowId).close();
			Ext.getCmp('complaintGridId').store.loadPage(1);
		}
		
		//执行失败
		var failFn = function(){
			MessageUtil.showErrorMes(i18n('i18n.alert.message_title'),i18n('errror.common.unknow'));
		}
		
		//包装参数:
		var params = {
				complaint:me.complaint,
				feedbackReason:feedbackreason
			};
		
		DpUtil.requestJsonAjax('returnSubmitor.action',params,successFn,failFn);
	}
});

/** 
 * 延时申请审批弹出框 声明
 */
Ext.define('postponeRquestWindow', {
    extend:'PopWindow'
	,width:600,height:400
	,layout:'border',modal:true
	,title:i18n('i18n.complaint.postponeRequest.approval')
	,complaint:null  //投诉基本信息
	,postponeRequestGridPanel:null //延时申请审批的panel
	,returnCause:null   //退回原因
	,initComponent:function(){
		var me = this;
		
		//初始化选中延时请求的selectModel
		var postponeSelModel=Ext.create('Ext.selection.CheckboxModel',{id:'postponeSelModelId',checkOnly:true,allowDeselect:true});			
		
		me.postponeRequestGridPanel = Ext.create('PostponeRequestGrid',{
		    selModel:postponeSelModel  //设置selectmodel
			,'complaint':me.complaint
			,'region':'center'
		});
		me.returnCause = Ext.create('Ext.form.field.TextArea',{
			'region':'south',height:100,fieldLabel:i18n('i18n.comp.comp_process.return_cause')
			,labelAlign:'top',labelStyle:"text-align:left"
		});
		Ext.applyIf(this, {
		    buttons:[
				 {text:i18n('i18n.complaint.agree'),scope:me,handler:me.agreeEvent}
				,{text:i18n('i18n.complaint.refuse'),scope:me,handler:me.refuseEvent}
				,{text:i18n('i18n.complaintReport.btn_close'),scope:me,handler:me.closeEvent}
			]
			,items:[me.postponeRequestGridPanel,me.returnCause]
		});
		me.callParent(arguments);
    }
	,agreeEvent:function(){
		processingMask.show();
		var me = this;
		var selectedOrders = me.postponeRequestGridPanel.getSelectionModel().getSelection();
		
		if(DpUtil.isEmpty(selectedOrders)){
			MessageUtil.showMessage(i18n('i18n.comp.msg.no_select_one'));
			processingMask.hide();
			return;
		}
		
		//执行成功
		var successFn = function(response){
			MessageUtil.showMessage(i18n('i18n.complaint.operate.agree.postpone.message.success'));
			Ext.getCmp('postponeRquestWindowId').close();
			Ext.getCmp('ComplaintWorkbenchProcessWindow').close();
			Ext.getCmp('complaintGridId').store.loadPage(1);
			processingMask.hide();
		}
		
		//执行失败
		var failFn = function(){
			MessageUtil.showErrorMes(i18n('errror.common.unknow'));
			processingMask.hide();
		}
		
		
		//包装参数:	
		var selectedArray = new Array();
		Ext.each(selectedOrders, function(record) {
			selectedArray.push(record.data);
		});
		
		var params = {complaint:me.complaint,resultsToApprove:selectedArray};
		
		DpUtil.requestJsonAjax('agreeWithPostponeRequests.action',params,successFn,failFn);
	}
	,refuseEvent:function(){
		processingMask.show();
		var me = this;
		var selectedOrders = me.postponeRequestGridPanel.getSelectionModel().getSelection();
		
		if(DpUtil.isEmpty(selectedOrders)){
			MessageUtil.showMessage(i18n('i18n.comp.msg.no_select_one'));
			processingMask.hide();
			return;
		}
		if(DpUtil.isEmpty(me.returnCause.getValue())){
			MessageUtil.showMessage(i18n('i18n.complaintTask.empty_returnCause'));
			processingMask.hide();
			return;
		}
		//执行成功
		var successFn = function(response){
			// 调用客户工单详情 弹出框
			MessageUtil.showMessage(i18n('i18n.complaint.operate.refuse.postpone.message.success'));
			Ext.getCmp('postponeRquestWindowId').close();
			Ext.getCmp('ComplaintWorkbenchProcessWindow').close();
			Ext.getCmp('complaintGridId').store.loadPage(1);
			processingMask.hide();
		}
		
		//执行失败
		var failFn = function(){
			MessageUtil.showErrorMes(i18n('errror.common.unknow'));
			processingMask.hide();
		}
		
		//包装参数:	
		var selectedArray = new Array();
		Ext.each(selectedOrders, function(record) {
			selectedArray.push(record.data);
		});
		
		
		var params = {
			complaint:me.complaint,
			returnReason:me.returnCause.getValue(),
			resultsToApprove:selectedArray
		};
		DpUtil.requestJsonAjax('refusePostponeRequests.action',params,successFn,failFn);
	}
	,closeEvent:function(){
		Ext.getCmp('postponeRquestWindowId').close();
	}
});
