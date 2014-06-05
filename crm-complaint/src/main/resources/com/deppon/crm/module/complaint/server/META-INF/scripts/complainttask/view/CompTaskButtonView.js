function CompTaskButtonView(){
	this.complaint = null;
	this.resultData = null;
	var me = this;
	Ext.getCmp('btnApplyDelay').show();
	Ext.getCmp('btnApplyDelay').on('click',function(){
		me.ShowdelayAppWin();
	});
	Ext.getCmp('btnReturnToProcess').show();
	Ext.getCmp('btnReturnToProcess').on('click',function(){
		me.ShowReturnToProcessWin();
	});
	Ext.getCmp('btnFeedbackRegister').show();
	Ext.getCmp('btnFeedbackRegister').on('click',function(){
		me.ShowBackRegisterWin();
	});
	
};
CompTaskButtonView.prototype.ShowReturnToProcessWin = function(){
	var me = this;
	Ext.create('ReturnToProcessWindow',{
		complaint :me.complaint,depId :me.resultData.resultId
	}).show();
};
CompTaskButtonView.prototype.ShowBackRegisterWin = function(){
	var me = this;
	 var resultId = me.resultData.resultId; 
	 if (DpUtil.validateFeedbackOverTime(resultId)){
		 Ext.create('feedbackRegisterWindow',{
		 complaint:me.complaint,depId : me.resultData.resultId}).show();
	 }
};
CompTaskButtonView.prototype.ShowdelayAppWin = function(){
	var me = this;
	Ext.create('delayApplicationWindow',{
	complaint:me.complaint,depId : me.resultData.resultId}).show();
}



CompTaskButtonView.prototype.initButForTask = function(params){
	var complaint = this.complaint = params.complaint;
	var resultData = this.resultData = params.resultData;
	if("VERIFIED"==complaint.prostatus || "FEEDBACK_EXPIRED"==complaint.prostatus
    	|| "REVISEIT"==complaint.prostatus){
    	Ext.getCmp('btnApplyDelay').hide();
    	Ext.getCmp('btnReturnToProcess').hide();
    	Ext.getCmp('btnFeedbackRegister').hide();
    }else{
    	//工单上报类型为异常时，申请延时功能按钮不可用
		var reportType=complaint.reporttype;
		if(reportType=="UNUSUAL" || "1"==resultData.delay || "2"==resultData.delay){
			Ext.getCmp('btnApplyDelay').hide();
		}
		//任务部门属性A为责任人，不可以做反馈登记
		if("duty_people"==resultData.taskproperties){
			Ext.getCmp('btnFeedbackRegister').hide();
		}
		
		// 是否隐藏退回处理人按钮，已经退回过
		if(null!=resultData.isBack && "1"==resultData.isBack){
					Ext.getCmp('btnReturnToProcess').hide();
		}else{
			//在反馈已解决或反馈未解决的状态下，退回处理人按钮不可用。或做过延时申请且（delay与stat为空即没作过处理）
			if("FEEDBACK_RESOLVED"==complaint.prostatus || "FEEDBACK_UNRESOLVE"==complaint.prostatus
				|| (!(""==resultData.delay && ""==resultData.stat) && 
				     (
					  'COMPLAINT_APPLY_DELAY_TO_APP'==complaint.prostatus || 'COMPLAINT_APPLY_DELAY_TO_PROSS'==complaint.prostatus
					  || 'COMPLAINT_APPLY_DELAY_TO_UPGR'==complaint.prostatus
					 )
				   )
			   ){
				Ext.getCmp('btnReturnToProcess').hide();	
			}
		}
    }
    //任务部门不为出发部门时，申请延时、退回处理人功能按钮不可用
	var deptType = resultData.deptType;
	if("1" == deptType) {
		Ext.getCmp('btnReturnToProcess').hide();
		Ext.getCmp('btnApplyDelay').hide();
	}
};



