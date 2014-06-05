 
/** 
 * 工单处理弹出框 声明
 */ 
Ext.define('ProcessComplaintWindow', {
    extend:'PopWindow'
	,'complaint':null //客户工单信息
	,parent:null  // 父级
	,complaintBasicFormPanel:null/* 显示基本信息  formPanel */
	,processRecordGrid:null /* 操作记录 */
	,width:820,height:435
    ,layout:'border',modal:true
	,title:i18n('i18n.complaint.process_complaint_title')
    ,initComponent: function() {
        var me = this;
      	this.complaintBasicFormPanel = Ext.create('ComplaintBasicFormPanel',{
      		region:'center',complaint:me.complaint
      	});
      	this.processRecordGrid = Ext.create('ProcessRecordGrid',{
			'complaint':me.complaint,region:'south'
		});
		Ext.applyIf(me,{
			items:[me.complaintBasicFormPanel,me.processRecordGrid]
			,buttons:me.getButtons()
		})
        me.callParent(arguments);
    }
    ,myClose:function(isNot_normalClose){
    	var me = this;
    	me.close();
    	if(isNot_normalClose && isNot_normalClose===true){
    		var selection = me.parent.complaintGrid.getSelectionModel().getSelection();
    		me.parent.complaintGrid.getStore().remove(selection[0]);
    	}
    }
    ,getButtons:function(){
    	var me = this;
    	var buttons = new Array();
    	buttons.push({
	    		text:i18n('i18n.complaint.btn.return_reportPeople'),handler:function(){
	    			if(DpUtil.validateAccessExpired(me.complaint.fid)){
		    			Ext.create('ReturnToReporterWindow',{
		    				complaint:me.complaint
		    				,parent:me
	    				}).show();
	    			}
	    		}
	    	});
	    if(me.complaint.reporttype===i18n('i18n.complaint.fpririty_complaint')){
	    	if(!isPermission('/common/complaintProcess.action')){//提交处理 既 投诉经理 无提交审批
	    		buttons.push({text:i18n('i18n.complaint.btn.submit_approval'),scope:me,handler:me.submitApprovalEvent});
	    	}
	    	buttons.push({text:i18n('i18n.complaint.btn.complaint_upgrade'),scope:me,handler:me.complaintUpgradeEvent});
	    	
	    }
	    buttons.push({text:i18n('i18n.complaint.btn.finish_process'),scope:me,handler:me.finishProcessEvent});
	    buttons.push({text:i18n('i18n.complaintReport.btn_close'),scope:me,handler:me.close});
    	return buttons;
    }
    ,submitApprovalEvent:function(){
    	var me = this;
    	
    	if(DpUtil.validateAccessExpired(me.complaint.fid)){
	    	Ext.create('SubmitApprovalWindow',{
				parent:me,complaint:me.complaint
			}).show();
    	}
    }
    ,complaintUpgradeEvent:function(){
    	var me = this;
    	if(DpUtil.validateAccessExpired(me.complaint.fid)){
	    	Ext.create('ComplaintUpgradeWindow',{
				complaint:me.complaint,parent:me
			}).show();
    	}
    }
    ,finishProcessEvent:function(){
    	var me = this;
    	var businessModelName=Ext.getCmp('baseFormBusinessModel').getValue();
    	if(businessModelName=='EXPRESS'){
    		businessModelName='快递'
    	}else if(businessModelName='UNEXPRESS'){
    		businessModelName='零担'
    	}else if(Ext.getCmp('BaseFormComplaintSource').getValue()=='NETWORK'){
    		MessageUtil.showMessage('请选择业务类型',function(){
    			return;
    		})
    	}
    	var message_show='确认该条工单的业务模式为'+businessModelName+'吗？';
    	var startProcess=function(){
    	if(DpUtil.validateAccessExpired(me.complaint.fid)){
    	  var finishProcessWindow = Ext.create('FinishProcessWindow',{
    		id:'finishProcessWindowId',complaint:me.complaint,parent:me,x:0,y:400
		   }).show();
		   finishProcessWindow.setWidth(finishProcessWindow.old_width);finishProcessWindow.setHeight(finishProcessWindow.old_height);
		    }
    	}
    	var chooseFun = function(e){
    		if(e=='yes'){
		 		var changeComplaint=me.complaint;
		 		changeComplaint.businessModel=Ext.getCmp('baseFormBusinessModel').getValue();
		   		DpUtil.requestJsonAjax('changeBusinessModel.action',{'complaint':changeComplaint})
			    startProcess();
    		}else{
    			return;
    		}
    	}
    	if(!Ext.isEmpty(Ext.getCmp('baseFormBusinessModel'))&&
    		!Ext.isEmpty(Ext.getCmp('BaseFormComplaintSource'))){
    			//工单来源为官网 弹出提示
    		if(Ext.getCmp('BaseFormComplaintSource').getValue()=='NETWORK'){
    			MessageUtil.showQuestionMes(message_show,chooseFun);
    		}else{
    			startProcess();
    		}
    	}
    	}
});

/** 
 * 退回上报人弹出框 声明
 */
Ext.define('ReturnToReporterWindow', {
    extend:'PopWindow'
    ,parent:null  //父级
	,width:300,height:200
    ,layout: 'fit',modal:true
	,title:i18n('i18n.comp.win_title.return_reportPeople')
	,complaint:null  //投诉基本信息
    ,items:[{
    	xtype:'textareafield',labelWidth:60
    	,fieldLabel:i18n('i18n.comp.comp_process.return_cause')
    	,id:'returnToReporter_feedbackreason'
    }]
	,initComponent:function(){
		var me = this;
		Ext.applyIf(me, {
		    buttons:[
				{text:i18n('i18n.complaint.submit'),scope:me,handler:me.submitEvent}
				,{text:i18n('i18n.complaintReport.btn_close'),scope:me,handler:function(){me.close();}}
			]
		});
		this.callParent(arguments);
    }
	,submitEvent:function(){
		processingMask.show();
		var me = this;
		var feedbackreason = Ext.getCmp('returnToReporter_feedbackreason').getValue()
		if(DpUtil.isEmpty(feedbackreason)){
			MessageUtil.showMessage(i18n('i18n.comp.comp_process.msg.no_return_cause'));
			processingMask.hide();
			return;
		}
		if(feedbackreason.length>200){
			MessageUtil.showMessage(i18n('i18n.comp.comp_process.msg.feedbackreason_length_xy200'));
			processingMask.hide();
			return;
		}
		
		//提交执行成功
		var submitSuccessFn = function(response){
			processingMask.hide();
			MessageUtil.showInfoMes(i18n('i18n.comp.msg.succeed.return_report'));
			me.close();
			me.parent.myClose(true);/* 底层 window */
		};
		
		//提交执行失败
		var submitFailFn = function(response){
			processingMask.hide();
			MessageUtil.showErrorMes(response.message);
		};
		
		if(DpUtil.validateAccessExpired(me.complaint.fid)){
			DpUtil.requestJsonAjax('returnReportor.action',{
				parameterMap:{compId:me.complaint.fid+'',feedbackreason:feedbackreason}
			},submitSuccessFn,submitFailFn);				
		}else{
			processingMask.hide();
		}
	}
});





/** 
 * 投诉升级弹出框 声明
 */
Ext.define('ComplaintUpgradeWindow', {
    extend:'PopWindow'
	,'complaint':null //客户工单信息
	,'parent':null   // 父级 弹出框
	,title:i18n('i18n.comp.comp_process.win_title.comp_upgrade'),width:750
	,form_recommendation:null // 调查建议对象
    ,layout:'fit',modal:true
    ,initComponent: function() {
        var me = this;
    	this.form_recommendation = Ext.create('Ext.form.field.TextArea',{
    		fieldLabel:i18n('i18n.complaint.comp_recommendation'),labelWidth:60,width:400
    	});
    	Ext.applyIf(me,{
    		items:[me.form_recommendation]
    		,buttons:me.getButtons()
    	});
        me.callParent(arguments);
    }
    ,getButtons:function(){
    	var me = this;
    	return [
    		{text:i18n('i18n.complaint.submit'),listeners:{scope:me,click:me.submit_event}}
    		,{text:i18n('i18n.complaintReport.btn_close'),listeners:{scope:me,click:function(){me.close();}}}
    	];
    }
    ,submit_event:function(){
    	var me = this;
		var recommendation = me.form_recommendation.getValue();
    	if(DpUtil.isEmpty(recommendation)){
			MessageUtil.showMessage(i18n('i18n.complaint.msg_input_fullData'));return;
		}
		processingMask.show();
    	me.complaint['recommendation']=recommendation;
    	var params = {complaint:me.complaint}
    	//执行成功
		var successFn = function(response){
			processingMask.hide();
			MessageUtil.showInfoMes(i18n('i18n.comp.msg.succeed.comp_upgrade'));
			me.close();/* 本层 window */
			me.parent.myClose(true);/* 底层 window */	
		}
		//执行失败
		var failFn = function(response){
			processingMask.hide();
			MessageUtil.showErrorMes(response.message);
		}
		
		if(DpUtil.validateAccessExpired(me.complaint.fid)){
			DpUtil.requestJsonAjax('complaint_upgrade.action',params,successFn,failFn);
		}else{
			processingMask.hide();
		}
    }
});

