DpUtil = function() {
};
/**.
 * <p>
 * 覆盖Ext.form.RadioGroup的setValue方法<br/>
 * item.getRawValue全为false
 * <p>
 * @author 张斌
 * @时间 2012-3-25
 */
Ext.override(Ext.form.RadioGroup, {   
    setValue: function(v){
        if (this.rendered)    
            this.items.each(function(item){   
                item.setValue(item.inputValue == v);   
            });   
        else {
            for (var k = 0;k<this.items.items.length;k++) {   
                this.items.items[k].setValue(this.items.items[k].inputValue == v);   
            }   
        }   
    }   
}); 
/**
 * 修改date对象数据的JSON提交方式
 */
Ext.JSON.encodeDate = function(date) {
	return date.getTime();
};
/**.
 * <p>
 * 公共方法，显示MESSAGE信息<br/>
 * <p>
 * @param  meaage  要现实的信息
 * @author 张斌
 * @时间 2012-3-11
 */
DpUtil.showMessage = function(meaage) {
	 top.Ext.MessageBox.alert(i18n('i18n.recompense.alertCRM'),meaage);
};
/**.
 * <p>
 * 刷新理赔详情界面<br/>
 * <p>
 * @author 张斌
 * @时间 2012-4-18
 */
DpUtil.refreshRecompenseInfo = function() {
	var succseeFn = function(json){
		Ext.getCmp('detailBtnPanel').destroy( );
		Ext.getCmp('recompenseDetailsUI').add(new DetailBtnPanel({'actionIds':json.actionIds,'recompenseId':json.app.id,'status':json.app.status,'recompenseMethod':json.app.recompenseMethod,'reportDept':json.app.reportDept,'deptId':json.app.deptId}));
	    Ext.getCmp('recompenseDetailsUI').record = json.app;
		Ext.getCmp('recompenseDetailsUI').workFlowList = json.oaWorkflowList;
		Ext.getCmp('recompenseDetailsUI').oaAccidentInfoList = json.oaAccidentInfoList;
		Ext.getCmp('recompenseDetailsUI').loadData();
	};
   var failureFn = function(json){
	   if(Ext.isEmpty(json)){
		   MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
		}else{
			MessageUtil.showErrorMes(json.message);
		}
	};
	var param = {'recompenseId':Ext.getCmp('recompenseDetailsUI').record.id};
	recompenseData.searchRecompenseById(param,succseeFn,failureFn);
};
/**.
 * <p>
 * 修改理赔<br/>
 * <p>
 * @author 张斌
 * @时间 2012-4-17
 */
DpUtil.showUpdateRecompenseWindow = function(){
	if(Ext.isEmpty(Ext.getCmp('updateRecompenseWindow'))){
		new UpdateRecompenseWindow().show();
	}else{
		Ext.getCmp('updateRecompenseWindow').show();
	}
}
/**.
 * <p>
 * 删除理赔<br/>
 * <p>
 * @author 张斌
 * @时间 2012-4-17
 */
DpUtil.showDeleteRecompenseWindow = function(actiobId){
	MessageUtil.showQuestionMes(i18n('i18n.recompense.isDeleteThisRecompnese'),
		function(e){
			if (e == 'yes') {
				var id = Ext.getCmp('recompenseDetailsUI').record.id;
			    var workflowId = Ext.getCmp('recompenseDetailsUI').record.workflowId;
			    var recompenseMethod = Ext.getCmp('recompenseDetailsUI').record.recompenseMethod;
			    var param  = {'actionId':actiobId,'recompenseView':{'app':{'id':id,'workflowId':workflowId,'recompenseMethod':recompenseMethod}}};
			  //禁用掉按钮区域防止一直点击
		    	Ext.getCmp('detailBtnPanel').setDisabled(true);
			    var successFn = function(json){
			    	Ext.getCmp('detailBtnPanel').setDisabled(false);
			    	MessageUtil.showInfoMes(json.message);
			    	Ext.getCmp('recompenseDetailsUI').hide();
			    };
			    var failureFn = function(json){
			    	Ext.getCmp('detailBtnPanel').setDisabled(false);
			    	if(Ext.isEmpty(json)){
			    		MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
					}else{
						MessageUtil.showErrorMes(json.message);
					}
			    };
			    recompenseData.performAction(param,successFn,failureFn);
			}else{
				return ;
			}
	});
}
/**.
 * <p>
 * 打印按钮<br/>
 * <p>
 * @author 张斌
 * @时间 2012-5-5
 */
/*var printButton =  Ext.create('Ext.Button',{
    text:i18n('i18n.recompense.btnPrint'),
    handler: function() {
    	var recompenseData	= (CONFIG.get("TEST")) ? new RecompenseDataTestN(): new RecompenseDataN(),
    		url 			= recompenseData.recompensePrint(),
    		printDatas		= printDatas;
    	window.open(url,'recompensePrint','');
    }
});*/
Ext.define('printButton',{
	extend:'Ext.button.Button',
	text:i18n('i18n.recompense.btnPrint'),
	 handler: function() {
	    	var recompenseData	= (CONFIG.get("TEST")) ? new RecompenseDataTestN(): new RecompenseDataN(),
	    		url 			= recompenseData.recompensePrint(),
	    		printDatas		= printDatas;
	    	window.open(url,'recompensePrint','');
	   }
})

/**.
 * <p>
 * 初步处理保存按钮及操作<br/>
 * <p>
 * @author 张斌
 * @时间 2012-4-18
 */
Ext.define('processRecompenseSave',{
	extend:'Ext.button.Button',
    text:i18n('i18n.recompense.processRecompenseSave'),
    handler: function() {
    	//判断入部门之后是否等于实际理赔金额
    	if(!DpUtil.isDeptChargeEquelRealAmount()){
    		MessageUtil.showErrorMes(i18n('i18n.recompense.deptChargeEquelRealAmount'));
    		return ;
    	}
    	var recalledComModel = new RecalledComModel();
		//追偿信息
		Ext.getCmp('recalledComPanel').getForm().updateRecord(recalledComModel);
		var recalledComModelDeptName = Ext.getCmp('recoveryDeptId').getValue();
		if(Ext.isEmpty(recalledComModelDeptName)){
			recalledComModel.set(CONFIGNAME.get('deptId'),'');
			recalledComModel.set(CONFIGNAME.get('deptName'),'');
		}else{
			recalledComModel.set(CONFIGNAME.get('deptId'),Ext.getCmp('recoveryDeptId').getValueId());
			recalledComModel.set(CONFIGNAME.get('deptName'),Ext.getCmp('recoveryDeptId').getValue());
		}
		var app  = new RecompenseApp(Ext.getCmp('recompenseDetailsUI').record);
		app.set(CONFIGNAME.get('recalledCom'),recalledComModel.data);
		Ext.getCmp('amountPanel').getForm().updateRecord(app);
		//清除冗余数据
		app.set(CONFIGNAME.get('deptChargeList'),null);
		app.set(CONFIGNAME.get('responsibleDeptList'),null);
		app.set(CONFIGNAME.get('awardItemList'),null);
		app.set(CONFIGNAME.get('messageReminderList'),null);
		if(!Ext.getCmp('amountPanel').getForm().isValid()){
			Ext.getCmp('recompenseDetailPanel').setActiveTab(1);
			return;
		}
		//入部门部门必有一条数据
		if(Ext.getCmp('deptChargeGird').getStore().getCount()==0){
			MessageUtil.showMessage(i18n('i18n.recompense.haveOneDeptChargeGird'));
			return;
		}
		//责任部门必有一条数据
		if(Ext.getCmp('responsibleDeptGird').getStore().getCount()==0){
			MessageUtil.showMessage(i18n('i18n.recompense.haveOneResponsibleDeptGird'));
			return;
		}
		//短信是必填的
        if(Ext.getCmp('messageReminderGird').getStore().getCount()==0){
        	MessageUtil.showMessage(i18n('i18n.recompense.haveOneMessageReminderGird'));
        	return;
        }
        var costExplain = Ext.getCmp(CONFIGNAME.get('costExplain')).getValue();
        //设置费用说明
        app.set(CONFIGNAME.get('costExplain'),costExplain);
		//入部门费用
		var deptChargeAddModelList = Ext.getCmp('deptChargeGird').getStore().getNewRecords( );			
		var deptChargeDelModelList = Ext.getCmp('deptChargeGird').getStore().getRemovedRecords( ) ;
		var deptChargeUpdModelList = Ext.getCmp('deptChargeGird').getStore().getUpdatedRecords( );
		var deptChargeAddList = DpUtil.changeModelListToDataList(deptChargeAddModelList);
		var deptChargeDelList = DpUtil.changeModelListToDataList(deptChargeDelModelList);
		var deptChargeUpdList = DpUtil.changeModelListToDataList(deptChargeUpdModelList);
		//责任部门 
		var dutyDeptAddModelList = Ext.getCmp('responsibleDeptGird').getStore().getNewRecords( );
		var dutyDeptUpdModelList = Ext.getCmp('responsibleDeptGird').getStore().getUpdatedRecords( );
		var dutyDeptDelModelList = Ext.getCmp('responsibleDeptGird').getStore().getRemovedRecords( ) ;
		var dutyDeptAddList = DpUtil.changeModelListToDataList(dutyDeptAddModelList);
		var dutyDeptDelList = DpUtil.changeModelListToDataList(dutyDeptDelModelList);
		var dutyDeptUpdList = DpUtil.changeModelListToDataList(dutyDeptUpdModelList);
		//消息系统
		var msgReminderAddModelList = Ext.getCmp('messageReminderGird').getStore().getNewRecords( );
		var msgReminderUpdModelList = Ext.getCmp('messageReminderGird').getStore().getUpdatedRecords( );
		var msgReminderDelModelList = Ext.getCmp('messageReminderGird').getStore().getRemovedRecords( ) ;
		var msgReminderAddList = DpUtil.changeModelListToDataList(msgReminderAddModelList);
		var msgReminderUpdList = DpUtil.changeModelListToDataList(msgReminderUpdModelList);
		var msgReminderDelList = DpUtil.changeModelListToDataList(msgReminderDelModelList);
		//奖罚明细
		var awardItemAddModelList = Ext.getCmp('awardItemGird').getStore().getNewRecords( );
		var awardItemUpdModelList = Ext.getCmp('awardItemGird').getStore().getUpdatedRecords( );
		var awardItemDelModelList = Ext.getCmp('awardItemGird').getStore().getRemovedRecords( ) ;
		var awardItemAddList = DpUtil.changeModelListToDataList(awardItemAddModelList);
		var awardItemUpdList = DpUtil.changeModelListToDataList(awardItemUpdModelList);
		var awardItemDelList = DpUtil.changeModelListToDataList(awardItemDelModelList);
		var params = {'recompenseView':{'deptChargeAddList':deptChargeAddList,'deptChargeDelList':deptChargeDelList,
			'deptChargeUpdList':deptChargeUpdList,'dutyDeptAddList':dutyDeptAddList,'dutyDeptDelList':dutyDeptDelList,
			'dutyDeptUpdList':dutyDeptUpdList,'msgReminderAddList':msgReminderAddList,'msgReminderUpdList':msgReminderUpdList,
			'msgReminderDelList':msgReminderDelList,'awardItemAddList':awardItemAddList,'awardItemUpdList':awardItemUpdList,
			'awardItemDelList':awardItemDelList,'app':app.data}};
		var successFn = function(json){
			Ext.getCmp('detailBtnPanel').setDisabled(false);
			MessageUtil.showInfoMes(json.message);
			DpUtil.refreshRecompenseInfo();
			DpUtil.closeButton();
		}
		var failureFn = function(json){
			Ext.getCmp('detailBtnPanel').setDisabled(false);
			if(Ext.isEmpty(json)){
				MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
			}else{
				MessageUtil.showErrorMes(json.message);
			}
		}
		//提交时金额校验
		if( app.data.normalAmount>0 && app.data.realAmount>0){
			//禁用掉按钮区域防止一直点击
			Ext.getCmp('detailBtnPanel').setDisabled(true);
			recompenseData.saveRecompenseProcessInfo(params,successFn,failureFn);
   	   	}else{
   	   		MessageUtil.showErrorMes(i18n('i18n.recompense.processAmountCheck'));
   	   	}
    }
});

/**.
 * <p>
 * 审批提交操作按钮<br/>
 * <p>
 * @author 张斌
 * @时间 2012-4-18
 */
Ext.define('commitRecompenseSave',{
	extend:'Ext.button.Button',
    text:i18n('i18n.recompense.beforeCommitUpdate'),
    handler: function() {
    	if(!DpUtil.isDeptChargeEquelRealAmount()){
    		MessageUtil.showErrorMes(i18n('i18n.recompense.deptChargeEquelRealAmount'));
    		return ;
    	}
    	var recalledComModel = new RecalledComModel();
		//追偿信息
		Ext.getCmp('recalledComPanel').getForm().updateRecord(recalledComModel);
		var recalledComModelDeptName = Ext.getCmp('recoveryDeptId').getValue();
		if(Ext.isEmpty(recalledComModelDeptName)){
			recalledComModel.set(CONFIGNAME.get('deptId'),'');
			recalledComModel.set(CONFIGNAME.get('deptName'),'');
		}else{
			recalledComModel.set(CONFIGNAME.get('deptId'),Ext.getCmp('recoveryDeptId').getValueId());
			recalledComModel.set(CONFIGNAME.get('deptName'),Ext.getCmp('recoveryDeptId').getValue());
		}
		var app  = new RecompenseApp(Ext.getCmp('recompenseDetailsUI').record);
		app.set(CONFIGNAME.get('recalledCom'),recalledComModel.data);
		Ext.getCmp('amountPanel').getForm().updateRecord(app);
		//清除冗余数据
		app.set(CONFIGNAME.get('deptChargeList'),null);
		app.set(CONFIGNAME.get('responsibleDeptList'),null);
		app.set(CONFIGNAME.get('awardItemList'),null);
		app.set(CONFIGNAME.get('messageReminderList'),null);
		if(!Ext.getCmp('amountPanel').getForm().isValid()){
			return;
		}
		//入部门费用
		if(Ext.getCmp('deptChargeGird').getStore().getCount()==0){
			MessageUtil.showErrorMes(i18n('i18n.recompense.haveOneDeptChargeGird'));
			return;
		}
/*		//消息系统
        if(Ext.getCmp('messageReminderGird').getStore().getCount()==0){
        	DpUtil.showMessage(i18n('i18n.recompense.haveOneMessageReminderGird'));
        	return;
        }*/
        //责任部门
        if(Ext.getCmp('responsibleDeptGird').getStore().getCount()==0){
        	MessageUtil.showMessage(i18n('i18n.recompense.haveOneResponsibleDeptGird'));
        	return;
        }
        var costExplain = Ext.getCmp(CONFIGNAME.get('costExplain')).getValue();
        if(Ext.isEmpty(costExplain)){
        	MessageUtil.showMessage(i18n('i18n.recompense.haveCostExplain'));
        	return;
        }
      //禁用掉按钮区域防止一直点击
//    	Ext.getCmp('detailBtnPanel').setDisabled(true);
        //设置费用说明
        app.set(CONFIGNAME.get('costExplain'),costExplain);
		//入部门费用
		var deptChargeAddModelList = Ext.getCmp('deptChargeGird').getStore().getNewRecords( );			
		var deptChargeDelModelList = Ext.getCmp('deptChargeGird').getStore().getRemovedRecords( ) ;
		var deptChargeUpdModelList = Ext.getCmp('deptChargeGird').getStore().getUpdatedRecords( );
		var deptChargeAddList = DpUtil.changeModelListToDataList(deptChargeAddModelList);
		var deptChargeDelList = DpUtil.changeModelListToDataList(deptChargeDelModelList);
		var deptChargeUpdList = DpUtil.changeModelListToDataList(deptChargeUpdModelList);
		//责任部门 
		var dutyDeptAddModelList = Ext.getCmp('responsibleDeptGird').getStore().getNewRecords( );
		var dutyDeptUpdModelList = Ext.getCmp('responsibleDeptGird').getStore().getUpdatedRecords( );
		var dutyDeptDelModelList = Ext.getCmp('responsibleDeptGird').getStore().getRemovedRecords( ) ;
		var dutyDeptAddList = DpUtil.changeModelListToDataList(dutyDeptAddModelList);
		var dutyDeptDelList = DpUtil.changeModelListToDataList(dutyDeptDelModelList);
		var dutyDeptUpdList = DpUtil.changeModelListToDataList(dutyDeptUpdModelList);
		//消息系统
		var msgReminderAddModelList = Ext.getCmp('messageReminderGird').getStore().getNewRecords( );
		var msgReminderUpdModelList = Ext.getCmp('messageReminderGird').getStore().getUpdatedRecords( );
		var msgReminderDelModelList = Ext.getCmp('messageReminderGird').getStore().getRemovedRecords( ) ;
		var msgReminderAddList = DpUtil.changeModelListToDataList(msgReminderAddModelList);
		var msgReminderUpdList = DpUtil.changeModelListToDataList(msgReminderUpdModelList);
		var msgReminderDelList = DpUtil.changeModelListToDataList(msgReminderDelModelList);
		//奖罚明细
		var awardItemAddModelList = Ext.getCmp('awardItemGird').getStore().getNewRecords( );
		var awardItemUpdModelList = Ext.getCmp('awardItemGird').getStore().getUpdatedRecords( );
		var awardItemDelModelList = Ext.getCmp('awardItemGird').getStore().getRemovedRecords( ) ;
		var awardItemAddList = DpUtil.changeModelListToDataList(awardItemAddModelList);
		var awardItemUpdList = DpUtil.changeModelListToDataList(awardItemUpdModelList);
		var awardItemDelList = DpUtil.changeModelListToDataList(awardItemDelModelList);
		
		var params = {'actionId':'370','recompenseView':{'deptChargeAddList':deptChargeAddList,'deptChargeDelList':deptChargeDelList,
			'deptChargeUpdList':deptChargeUpdList,'dutyDeptAddList':dutyDeptAddList,'dutyDeptDelList':dutyDeptDelList,
			'dutyDeptUpdList':dutyDeptUpdList,'msgReminderAddList':msgReminderAddList,'msgReminderUpdList':msgReminderUpdList,
			'msgReminderDelList':msgReminderDelList,'awardItemAddList':awardItemAddList,'awardItemUpdList':awardItemUpdList,
			'awardItemDelList':awardItemDelList,'app':app.data}};
		var successFn = function(json){
			Ext.getCmp('detailBtnPanel').setDisabled(false);
			MessageUtil.showInfoMes(json.message);
			DpUtil.refreshRecompenseInfo();
			DpUtil.closeButton();
		}
		var failureFn = function(json){
			Ext.getCmp('detailBtnPanel').setDisabled(false);
			if(Ext.isEmpty(json)){
				MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
			}else{
				MessageUtil.showErrorMes(json.message);
			}
		}
		/**
		 * 表单提交优化
		 */
		var transType =Ext.getCmp('recompenseSearchGridPanel').getSelectionModel().getSelection()[0].get('waybill.transType');
		var message = '';
		if(transType=='TRANS_EXPRESS'){
			message='快递理赔金额低于200（含）提交审批将直接审批完成，200元以上提交审批将产生审批工作流。是否确定提交？'
		}else{
			message='零担理赔金额低于1000（含）提交审批将直接审批完成，1000元以上提交审批将产生审批工作流。是否确定提交？'
		}
		MessageUtil.showQuestionMes(message,function(e){
			if (e == 'yes') {
				Ext.getCmp('detailBtnPanel').setDisabled(true);
				recompenseData.performAction(params,successFn,failureFn);
			}else{
				return;
			}
		});
		
    }
});
/**.
 * <p>
 * 多陪处理按钮<br/>
 * <p>
 * @author 张斌
 * @时间 2012-6-15
 */
Ext.define('overPayRecompenseCommit',{
	extend:'Ext.button.Button',
    text:i18n('i18n.reconpense.recompensOverPaySave'),
    handler: function() {
    	if(!DpUtil.isDeptChargeEquelRealAmount()){
    		MessageUtil.showErrorMes(i18n('i18n.recompense.deptChargeEquelRealAmount'));
    		return ;
    	}
    	var recalledComModel = new RecalledComModel();
		//追偿信息
		Ext.getCmp('recalledComPanel').getForm().updateRecord(recalledComModel);
		var recalledComModelDeptName = Ext.getCmp('recoveryDeptId').getValue();
		if(Ext.isEmpty(recalledComModelDeptName)){
			recalledComModel.set(CONFIGNAME.get('deptId'),'');
			recalledComModel.set(CONFIGNAME.get('deptName'),'');
		}else{
			recalledComModel.set(CONFIGNAME.get('deptId'),Ext.getCmp('recoveryDeptId').getValueId());
			recalledComModel.set(CONFIGNAME.get('deptName'),Ext.getCmp('recoveryDeptId').getValue());
		}
		var app  = new RecompenseApp(Ext.getCmp('recompenseDetailsUI').record);
		app.set(CONFIGNAME.get('recalledCom'),recalledComModel.data);
		Ext.getCmp('amountPanel').getForm().updateRecord(app);
		//清除冗余数据
		app.set(CONFIGNAME.get('deptChargeList'),null);
		app.set(CONFIGNAME.get('responsibleDeptList'),null);
		app.set(CONFIGNAME.get('awardItemList'),null);
		app.set(CONFIGNAME.get('messageReminderList'),null);
		if(!Ext.getCmp('amountPanel').getForm().isValid()){
			return;
		}
		//入部门费用
		if(Ext.getCmp('deptChargeGird').getStore().getCount()==0){
			MessageUtil.showErrorMes(i18n('i18n.recompense.haveOneDeptChargeGird'));
			return;
		}
        //责任部门
        if(Ext.getCmp('responsibleDeptGird').getStore().getCount()==0){
        	MessageUtil.showMessage(i18n('i18n.recompense.haveOneResponsibleDeptGird'));
        	return;
        }
        var costExplain = Ext.getCmp(CONFIGNAME.get('costExplain')).getValue();
        if(Ext.isEmpty(costExplain)){
        	MessageUtil.showMessage(i18n('i18n.recompense.haveCostExplain'));
        	return;
        }
        //禁用掉按钮区域防止一直点击
    	Ext.getCmp('detailBtnPanel').setDisabled(true);
        //设置费用说明
        app.set(CONFIGNAME.get('costExplain'),costExplain);
		//入部门费用
		var deptChargeAddModelList = Ext.getCmp('deptChargeGird').getStore().getNewRecords( );			
		var deptChargeDelModelList = Ext.getCmp('deptChargeGird').getStore().getRemovedRecords( ) ;
		var deptChargeUpdModelList = Ext.getCmp('deptChargeGird').getStore().getUpdatedRecords( );
		var deptChargeAddList = DpUtil.changeModelListToDataList(deptChargeAddModelList);
		var deptChargeDelList = DpUtil.changeModelListToDataList(deptChargeDelModelList);
		var deptChargeUpdList = DpUtil.changeModelListToDataList(deptChargeUpdModelList);
		//责任部门 
		var dutyDeptAddModelList = Ext.getCmp('responsibleDeptGird').getStore().getNewRecords( );
		var dutyDeptUpdModelList = Ext.getCmp('responsibleDeptGird').getStore().getUpdatedRecords( );
		var dutyDeptDelModelList = Ext.getCmp('responsibleDeptGird').getStore().getRemovedRecords( ) ;
		var dutyDeptAddList = DpUtil.changeModelListToDataList(dutyDeptAddModelList);
		var dutyDeptDelList = DpUtil.changeModelListToDataList(dutyDeptDelModelList);
		var dutyDeptUpdList = DpUtil.changeModelListToDataList(dutyDeptUpdModelList);
		//消息系统
		var msgReminderAddModelList = Ext.getCmp('messageReminderGird').getStore().getNewRecords( );
		var msgReminderUpdModelList = Ext.getCmp('messageReminderGird').getStore().getUpdatedRecords( );
		var msgReminderDelModelList = Ext.getCmp('messageReminderGird').getStore().getRemovedRecords( ) ;
		var msgReminderAddList = DpUtil.changeModelListToDataList(msgReminderAddModelList);
		var msgReminderUpdList = DpUtil.changeModelListToDataList(msgReminderUpdModelList);
		var msgReminderDelList = DpUtil.changeModelListToDataList(msgReminderDelModelList);
		//奖罚明细
		var awardItemAddModelList = Ext.getCmp('awardItemGird').getStore().getNewRecords( );
		var awardItemUpdModelList = Ext.getCmp('awardItemGird').getStore().getUpdatedRecords( );
		var awardItemDelModelList = Ext.getCmp('awardItemGird').getStore().getRemovedRecords( ) ;
		var awardItemAddList = DpUtil.changeModelListToDataList(awardItemAddModelList);
		var awardItemUpdList = DpUtil.changeModelListToDataList(awardItemUpdModelList);
		var awardItemDelList = DpUtil.changeModelListToDataList(awardItemDelModelList);
		
		var params = {'actionId':'380','recompenseView':{'deptChargeAddList':deptChargeAddList,'deptChargeDelList':deptChargeDelList,
			'deptChargeUpdList':deptChargeUpdList,'dutyDeptAddList':dutyDeptAddList,'dutyDeptDelList':dutyDeptDelList,
			'dutyDeptUpdList':dutyDeptUpdList,'msgReminderAddList':msgReminderAddList,'msgReminderUpdList':msgReminderUpdList,
			'msgReminderDelList':msgReminderDelList,'awardItemAddList':awardItemAddList,'awardItemUpdList':awardItemUpdList,
			'awardItemDelList':awardItemDelList,'app':app.data}};
		var successFn = function(json){
			Ext.getCmp('detailBtnPanel').setDisabled(false);
			MessageUtil.showInfoMes(json.message);
			DpUtil.refreshRecompenseInfo();
			DpUtil.closeButton();
		}
		var failureFn = function(json){
			Ext.getCmp('detailBtnPanel').setDisabled(false);
			if(Ext.isEmpty(json)){
				MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
			}else{
				MessageUtil.showErrorMes(json.message);
			}
		}
		recompenseData.performAction(params,successFn,failureFn);
    }
});
/**.
 * <p>
 * 初步处理保存按钮及操作(快速理赔)<br/>
 * <p>
 * @author 张斌
 * @时间 2012-4-18
 */
Ext.define('processRecompenseSaveFast',{
	extend:'Ext.button.Button',
    text:i18n('i18n.recompense.processRecompenseSaveFast'),
    handler: function() {
    	//判断入部门之后是否等于实际理赔金额
    	if(!DpUtil.isDeptChargeEquelRealAmount()){
    		MessageUtil.showErrorMes(i18n('i18n.recompense.deptChargeEquelRealAmount'));
    		return ;
    	}
    	var recalledComModel = new RecalledComModel();
		//追偿信息
		Ext.getCmp('recalledComPanel').getForm().updateRecord(recalledComModel);
		var recalledComModelDeptName = Ext.getCmp('recoveryDeptId').getValue();
		if(Ext.isEmpty(recalledComModelDeptName)){
			recalledComModel.set(CONFIGNAME.get('deptId'),'');
			recalledComModel.set(CONFIGNAME.get('deptName'),'');
		}else{
			recalledComModel.set(CONFIGNAME.get('deptId'),Ext.getCmp('recoveryDeptId').getValueId());
			recalledComModel.set(CONFIGNAME.get('deptName'),Ext.getCmp('recoveryDeptId').getValue());
		}
		var app  = new RecompenseApp(Ext.getCmp('recompenseDetailsUI').record);
		app.set(CONFIGNAME.get('recalledCom'),recalledComModel.data);
		Ext.getCmp('amountPanel').getForm().updateRecord(app);
		if(app.get(CONFIGNAME.get('normalAmount'))>1000){
			MessageUtil.showMessage(i18n('i18n.recompense.fastSmall1000'));
			return;
		}
		//清除冗余数据
		app.set(CONFIGNAME.get('deptChargeList'),null);
		app.set(CONFIGNAME.get('responsibleDeptList'),null);
		app.set(CONFIGNAME.get('awardItemList'),null);
		app.set(CONFIGNAME.get('messageReminderList'),null);
		if(!Ext.getCmp('amountPanel').getForm().isValid()){
			Ext.getCmp('recompenseDetailPanel').setActiveTab(1);
			return;
		}
		//入部门部门必有一条数据
		if(Ext.getCmp('deptChargeGird').getStore().getCount()==0){
			MessageUtil.showMessage(i18n('i18n.recompense.haveOneDeptChargeGird'));
			return;
		}
		//责任部门必有一条数据
		if(Ext.getCmp('responsibleDeptGird').getStore().getCount()==0){
			MessageUtil.showMessage(i18n('i18n.recompense.haveOneResponsibleDeptGird'));
			return;
		}
		//不是必填
/*        if(Ext.getCmp('messageReminderGird').getStore().getCount()==0){
        	DpUtil.showMessage(i18n('i18n.recompense.haveOneMessageReminderGird'));
        	return;
        }*/
      //禁用掉按钮区域防止一直点击
    	Ext.getCmp('detailBtnPanel').setDisabled(true);
        var costExplain = Ext.getCmp(CONFIGNAME.get('costExplain')).getValue();
        //设置费用说明
        app.set(CONFIGNAME.get('costExplain'),costExplain);
		//入部门费用
		var deptChargeAddModelList = Ext.getCmp('deptChargeGird').getStore().getNewRecords( );			
		var deptChargeDelModelList = Ext.getCmp('deptChargeGird').getStore().getRemovedRecords( ) ;
		var deptChargeUpdModelList = Ext.getCmp('deptChargeGird').getStore().getUpdatedRecords( );
		var deptChargeAddList = DpUtil.changeModelListToDataList(deptChargeAddModelList);
		var deptChargeDelList = DpUtil.changeModelListToDataList(deptChargeDelModelList);
		var deptChargeUpdList = DpUtil.changeModelListToDataList(deptChargeUpdModelList);
		//责任部门 
		var dutyDeptAddModelList = Ext.getCmp('responsibleDeptGird').getStore().getNewRecords( );
		var dutyDeptUpdModelList = Ext.getCmp('responsibleDeptGird').getStore().getUpdatedRecords( );
		var dutyDeptDelModelList = Ext.getCmp('responsibleDeptGird').getStore().getRemovedRecords( ) ;
		var dutyDeptAddList = DpUtil.changeModelListToDataList(dutyDeptAddModelList);
		var dutyDeptDelList = DpUtil.changeModelListToDataList(dutyDeptDelModelList);
		var dutyDeptUpdList = DpUtil.changeModelListToDataList(dutyDeptUpdModelList);
		//消息系统
		var msgReminderAddModelList = Ext.getCmp('messageReminderGird').getStore().getNewRecords( );
		var msgReminderUpdModelList = Ext.getCmp('messageReminderGird').getStore().getUpdatedRecords( );
		var msgReminderDelModelList = Ext.getCmp('messageReminderGird').getStore().getRemovedRecords( ) ;
		var msgReminderAddList = DpUtil.changeModelListToDataList(msgReminderAddModelList);
		var msgReminderUpdList = DpUtil.changeModelListToDataList(msgReminderUpdModelList);
		var msgReminderDelList = DpUtil.changeModelListToDataList(msgReminderDelModelList);
		//奖罚明细
		var awardItemAddModelList = Ext.getCmp('awardItemGird').getStore().getNewRecords( );
		var awardItemUpdModelList = Ext.getCmp('awardItemGird').getStore().getUpdatedRecords( );
		var awardItemDelModelList = Ext.getCmp('awardItemGird').getStore().getRemovedRecords( ) ;
		var awardItemAddList = DpUtil.changeModelListToDataList(awardItemAddModelList);
		var awardItemUpdList = DpUtil.changeModelListToDataList(awardItemUpdModelList);
		var awardItemDelList = DpUtil.changeModelListToDataList(awardItemDelModelList);
		var params = {'actionId':320,'recompenseView':{'deptChargeAddList':deptChargeAddList,'deptChargeDelList':deptChargeDelList,
			'deptChargeUpdList':deptChargeUpdList,'dutyDeptAddList':dutyDeptAddList,'dutyDeptDelList':dutyDeptDelList,
			'dutyDeptUpdList':dutyDeptUpdList,'msgReminderAddList':msgReminderAddList,'msgReminderUpdList':msgReminderUpdList,
			'msgReminderDelList':msgReminderDelList,'awardItemAddList':awardItemAddList,'awardItemUpdList':awardItemUpdList,
			'awardItemDelList':awardItemDelList,'app':app.data}};
		var successFn = function(json){
			Ext.getCmp('detailBtnPanel').setDisabled(false);
			MessageUtil.showInfoMes(json.message);
			DpUtil.refreshRecompenseInfo();
			DpUtil.closeButton();
		}
		var failureFn = function(json){
			Ext.getCmp('detailBtnPanel').setDisabled(false);
			if(Ext.isEmpty(json)){
				MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
			}else{
				MessageUtil.showErrorMes(json.message);
			}
		}
		recompenseData.performAction(params,successFn,failureFn);
    }
});



/**.
 * <p>
 * 设置到初步处理以前的状态<br/>
 * <p>
 * @author 张斌
 * @时间 2012-4-18
 */
DpUtil.closeButton = function(){
	Ext.getCmp('messageReminderDelete').setDisabled(true);
	Ext.getCmp('messageReminderAdd').setDisabled(true);
	Ext.getCmp('responsibleDeptDelete').setDisabled(true);
	Ext.getCmp('responsibleDeptUpdate').setDisabled(true);
	Ext.getCmp('responsibleDeptAdd').setDisabled(true);
	Ext.getCmp('awardItemAdd').setDisabled(true);
	Ext.getCmp('awardItemUpdate').setDisabled(true);
	Ext.getCmp('awardItemDelete').setDisabled(true);
	Ext.getCmp('deptChargeDelete').setDisabled(true);
	Ext.getCmp('deptChargeUpdate').setDisabled(true);
	Ext.getCmp('deptChargeAdd').setDisabled(true);
	var status = Ext.getCmp('recompenseDetailsUI').record.status;
	if(status!='AmountConfirmed'){
		Ext.getCmp(CONFIGNAME.get('normalAmount')).setReadOnly(true);
		Ext.getCmp(CONFIGNAME.get('realAmount')).setReadOnly(true);
		Ext.getCmp(CONFIGNAME.get('recoveryTime')).setReadOnly(true);
		Ext.getCmp(CONFIGNAME.get('normalAmount')).addCls('readonly');
		Ext.getCmp(CONFIGNAME.get('realAmount')).addCls('readonly');
		Ext.getCmp(CONFIGNAME.get('recoveryTime')).addCls('readonly');
	}
	Ext.getCmp('recoveryDeptId').setReadOnly(true);
	Ext.getCmp(CONFIGNAME.get('suspendedAmount')).setReadOnly(true);
	Ext.getCmp(CONFIGNAME.get('compensateBackAmount')).setReadOnly(true);
	Ext.getCmp(CONFIGNAME.get('compensateBackTime')).setReadOnly(true);
	Ext.getCmp(CONFIGNAME.get('returnDeductions')).setReadOnly(true);
	Ext.getCmp(CONFIGNAME.get('assessmentReward')).setReadOnly(true);
	//Ext.getCmp(CONFIGNAME.get('costExplain')).setReadOnly(true);
	Ext.getCmp('recoveryDeptId').addCls('readonly');
	Ext.getCmp(CONFIGNAME.get('suspendedAmount')).addCls('readonly');
	Ext.getCmp(CONFIGNAME.get('compensateBackAmount')).addCls('readonly');
	Ext.getCmp(CONFIGNAME.get('compensateBackTime')).addCls('readonly');
	Ext.getCmp(CONFIGNAME.get('returnDeductions')).addCls('readonly');
	Ext.getCmp(CONFIGNAME.get('assessmentReward')).addCls('readonly');
}
/**.
 * <p>
 * 将金额信息，理赔处理按钮，追偿信息设置为可用<br/>
 * <p>
 * @author 张斌
 * @时间 2012-4-18
 */
DpUtil.openButton = function(){
	Ext.getCmp('messageReminderDelete').setDisabled(false);
	Ext.getCmp('messageReminderAdd').setDisabled(false);
	Ext.getCmp('responsibleDeptDelete').setDisabled(false);
	Ext.getCmp('responsibleDeptUpdate').setDisabled(false);
	Ext.getCmp('responsibleDeptAdd').setDisabled(false);
	Ext.getCmp('awardItemAdd').setDisabled(false);
	Ext.getCmp('awardItemUpdate').setDisabled(false);
	Ext.getCmp('awardItemDelete').setDisabled(false);
	Ext.getCmp('deptChargeDelete').setDisabled(false);
	Ext.getCmp('deptChargeUpdate').setDisabled(false);
	Ext.getCmp('deptChargeAdd').setDisabled(false);
	var status = Ext.getCmp('recompenseDetailsUI').record.status;
	if(status!='AmountConfirmed'){
		Ext.getCmp(CONFIGNAME.get('normalAmount')).setReadOnly(false);
		Ext.getCmp(CONFIGNAME.get('realAmount')).setReadOnly(false);
		Ext.getCmp(CONFIGNAME.get('recoveryTime')).setReadOnly(false);
		Ext.getCmp(CONFIGNAME.get('normalAmount')).removeCls('readonly');
		Ext.getCmp(CONFIGNAME.get('realAmount')).removeCls('readonly');
		Ext.getCmp(CONFIGNAME.get('recoveryTime')).removeCls('readonly');
	}
	Ext.getCmp('recoveryDeptId').setReadOnly(false);
	Ext.getCmp(CONFIGNAME.get('suspendedAmount')).setReadOnly(false);
	Ext.getCmp(CONFIGNAME.get('compensateBackAmount')).setReadOnly(false);
	Ext.getCmp(CONFIGNAME.get('compensateBackTime')).setReadOnly(false);
	Ext.getCmp(CONFIGNAME.get('returnDeductions')).setReadOnly(false);
	Ext.getCmp(CONFIGNAME.get('assessmentReward')).setReadOnly(false);
	Ext.getCmp('recoveryDeptId').removeCls('readonly');
	Ext.getCmp(CONFIGNAME.get('suspendedAmount')).removeCls('readonly');
	Ext.getCmp(CONFIGNAME.get('compensateBackAmount')).removeCls('readonly');
	Ext.getCmp(CONFIGNAME.get('compensateBackTime')).removeCls('readonly');
	Ext.getCmp(CONFIGNAME.get('returnDeductions')).removeCls('readonly');
	Ext.getCmp(CONFIGNAME.get('assessmentReward')).removeCls('readonly');
}
/**.
 * <p>
 * 初步处理<br/>
 * <p>
 * @author 张斌
 * @时间 2012-4-18
 */
DpUtil.processRecompense = function(me){
	var searchDetal = Ext.getCmp('recompenseDetailsUI');
	if((searchDetal.record.status=='Submited'||searchDetal.record.status=='DocConfirmed')&&searchDetal.record.recompenseMethod=='normal'){
		Ext.getCmp('deptChargeGird').store.loadData(Ext.getCmp('recompenseDetailsUI').deptChargeGirdData);
		Ext.getCmp('responsibleDeptGird').store.loadData(Ext.getCmp('recompenseDetailsUI').responsibleDeptGirdData);
		Ext.getCmp('awardItemGird').store.loadData(Ext.getCmp('recompenseDetailsUI').awardItemGirdData);
		Ext.getCmp('messageReminderGird').store.loadData(Ext.getCmp('recompenseDetailsUI').messageReminderGirdData);
	}
	DpUtil.openButton();
	Ext.getCmp(CONFIGNAME.get('realAmount')).setReadOnly(true);
	Ext.getCmp(CONFIGNAME.get('realAmount')).addCls('readonly');
	Ext.getCmp('detailBtnPanelPoprightbuttonpanel').add(new processRecompenseSave());
	me.hide();
}
/**.
 * <p>
 * 快速理赔初步处理<br/>
 * <p>
 * @author 张斌
 * @时间 2012-4-18
 */
DpUtil.processRecompenseFast = function(me){
	DpUtil.openButton();
	/*Ext.getCmp('deptChargeDelete').setDisabled(false);
	Ext.getCmp('deptChargeUpdate').setDisabled(false);
	Ext.getCmp('deptChargeAdd').setDisabled(false);*/
	Ext.getCmp(CONFIGNAME.get('realAmount')).setReadOnly(true);
	Ext.getCmp(CONFIGNAME.get('realAmount')).addCls('readonly');
	//快速理赔初步处理与正常理赔一样
	Ext.getCmp('detailBtnPanelPoprightbuttonpanel').add(new processRecompenseSaveFast());
	me.hide();
}
/**.
 * <p>
 * 金额确认<br/>
 * <p>
 * @author 张斌
 * @时间 2012-4-19
 */
DpUtil.amountRecognized = function(){
	MessageUtil.showQuestionMes(i18n('i18n.recompense.pleaseInsureAmountCustomor'),
		function(e){
			if (e == 'yes') {
				var id = Ext.getCmp('recompenseDetailsUI').record.id;
				 var param  = {'recompenseId':id};
				 var successFn = function(json){
					 MessageUtil.showInfoMes(json.message);
					 DpUtil.refreshRecompenseInfo();
				 }
				 var failureFn = function(json){
					 if(Ext.isEmpty(json)){
						    MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
						}else{
							MessageUtil.showErrorMes(json.message);
						}
				 }
				 recompenseData.amountRecognized(param,successFn,failureFn);
			}else{
				return ;
			}
	});
}
/**.
 * <p>
 * 拒绝在线理赔<br/>
 * <p>
 * @author 张斌
 * @时间 2012-4-19
 */
DpUtil.refuseOnlineRecompense = function(){
	var id = Ext.getCmp('recompenseDetailsUI').record.id;
    var workflowId = Ext.getCmp('recompenseDetailsUI').record.workflowId;
    var recompenseMethod = Ext.getCmp('recompenseDetailsUI').record.recompenseMethod;
    MessageUtil.showQuestionMes(i18n('i18n.recompense.onlinepaymentRefuse'),
    function(e){
    	if (e == 'yes') {
    	var param  = {'actionId':'240','recompenseView':{'app':{'id':id,'workflowId':workflowId,'recompenseMethod':recompenseMethod}}};
    	var successFn = function(json){
    		MessageUtil.showInfoMes(json.message);
 	   		Ext.getCmp('recompenseDetailsUI').hide();
    	} ;
    	var failureFn = function(json){
    	if(Ext.isEmpty(json)){
    		MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
		}else{
			MessageUtil.showErrorMes(json.message);
		}
    };
    recompenseData.performAction(param,successFn,failureFn);
    	}
    });

    
    
    
    
}
/**.
 * <p>
 * 取消初步处理<br/>
 * <p>
 * @author 张斌
 * @时间 2012-4-19
 */
DpUtil.cancelProcess = function(){
	MessageUtil.showQuestionMes(i18n('i18n.recompense.confirmProcess'),
			function(e){
				if (e == 'yes') {
					var id = Ext.getCmp('recompenseDetailsUI').record.id;
					 var param  = {'recompenseId':id};
					 var successFn = function(json){
						 MessageUtil.showInfoMes(json.message);
						 DpUtil.refreshRecompenseInfo();
					 };
					 var failureFn = function(json){
						 if(Ext.isEmpty(json)){
							    MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
							}else{
								MessageUtil.showErrorMes(json.message);
							}
					 };
					 recompenseData.cancelProcess(param,successFn,failureFn);
			}
	});
}
/**.
 * <p>
 * 多赔<br/>
 * <p>
 * @author 张斌
 * @时间 2012-4-19
 */
DpUtil.overPay = function(actionId){
	if(Ext.isEmpty(Ext.getCmp('paymentBillWindow'))){
		new PaymentBillWindow({'actionId':actionId}).show();
	}else{
		Ext.getCmp('paymentBillWindow').show();
		Ext.getCmp('paymentBillWindow').actionId = actionId;
	}
	var normalAmount = Ext.getCmp('recompenseDetailsUI').record.normalAmount;
	var payBillWaybillNumber = Ext.getCmp('recompenseDetailsUI').record.waybill.waybillNumber;
	Ext.getCmp('paymentBillAmount').setValue(normalAmount);
	Ext.getCmp('payBillWaybillNumber').setValue(payBillWaybillNumber);
}
/**.
 * <p>
 * 付款<br/>
 * <p>
 * @author 张斌
 * @时间 2012-4-19
 *//*
DpUtil.payMent = function(actionId){
	alert('在修改');
	if(Ext.isEmpty(Ext.getCmp('paymentWindow'))){
		new PaymentWindow({'actionId':actionId}).show();
	}else{
		Ext.getCmp('paymentWindow').show();
		Ext.getCmp('paymentWindow').actionId = actionId;
	}
	var recompenseApp = new RecompenseApp(Ext.getCmp('recompenseDetailsUI').record);
	var waybill = new WaybillModel(recompenseApp.get(CONFIGNAME.get('waybill')));
	recompenseApp.set(CONFIGNAME.get('recompenseMethod'),DpUtil.changeDictionaryCodeToDescrip(recompenseApp.get(CONFIGNAME.get('recompenseMethod')),DataDictionary.RECOMPENSE_WAY));
	recompenseApp.set(CONFIGNAME.get('recompenseType'),DpUtil.changeDictionaryCodeToDescrip(recompenseApp.get(CONFIGNAME.get('recompenseType')),DataDictionary.RECOMPENSE_TYPE));
	Ext.getCmp('payMentApplication').getForm().loadRecord(recompenseApp);
	//设置付款金额为理赔金额
	Ext.getCmp(CONFIGNAME.get('paymentAmount')).setValue(recompenseApp.get(CONFIGNAME.get('normalAmount')));
	if(!Ext.isEmpty(recompenseApp.get(CONFIGNAME.get('customer')))){
		Ext.getCmp('payMentDegree').setValue(DpUtil.changeDictionaryCodeToDescrip(recompenseApp.get(CONFIGNAME.get('customer')).degree,'MEMBER_GRADE'));
		Ext.getCmp('payMentCustNumber').setValue(recompenseApp.get(CONFIGNAME.get('customer')).custNumber);
	}
	Ext.getCmp('payMentApplication').getForm().loadRecord(waybill);
}*/
/**.
 * <p>
 * 提交付款时账号比对<br/>
 * <p>
 * @author 邹明
 * @时间 2013-01-11
 */
DpUtil.accountCompare =function(th,id,workflowId,recompenseMethod,model){
	var form = Ext.getCmp('paymenApplyFormId').getForm();
	//提交理赔付款信息
//	if(!form.isValid()){
//		return;
//	}
//	//按员工工号查询帐号信息并比对
//	var params = {
//			'employeeNum':User.empCode
//	};
//	var sucFn = function(json){
//		if(form.findField('paymentMode').getValue()=="REMIT"/*i18n('i18n.payment.remittance')"汇款"*/||
//				form.findField('paymentMode').getValue()=="汇款"||
//				form.findField('paymentMode').getValue()==i18n('i18n.payment.remitAfterStrikeABalance')/*"冲账后汇款"*/){
//			for ( var i = 0; i < json.paymentList.length; i++) {
//				if(form.findField('account').getValue() == json.paymentList[i].account){
//					MessageUtil.showInfoMes(i18n('i18n.payment.notinsertcustomeraccount')/*'您好，请输入客户的账号。'*/);
//					return;
//				}else{
					if(!form.isValid()){
						MessageUtil.showMessage('您好，请填写完整的信息！');
						return;
					}
					if(model.data.recompenseType == 'unbilled'||
							model.data.recompenseType == '未开单理赔'){
						var choseFn =function (c){
							if(c=='yes'){
								MessageUtil.showQuestionMes(i18n('i18n.payment.finSureSubmmit'),function(e){/*"提交后将在费用报销系统生成报销单与工作流，是否确认提交？"*/
									if(e=='yes'){
											DpUtil.payment(form,th,id,workflowId,recompenseMethod,model.data.recompenseType);
									}else{
										return;
									}
								});
							}else{
								return;
							}
						}
						var message = '';
						if(Ext.getCmp('openName').getValue().length<=7&&Ext.getCmp('accountPropId').getValue()=='PUBLIC_ACCOUNT'){
							message='请确认账户是否是公司账户后再提交';
							MessageUtil.showQuestionMes(message,choseFn);
							return;
						}else if(Ext.getCmp('openName').getValue().length>4&&Ext.getCmp('accountPropId').getValue()=='PRIVATE_ACCOUNT'){
							message='请确认账户为个人账户再提交';
							MessageUtil.showQuestionMes(message,choseFn);
							return;
						}else{
							MessageUtil.showQuestionMes(i18n('i18n.payment.finSureSubmmit'),function(e){/*"提交后将在费用报销系统生成报销单与工作流，是否确认提交？"*/
								if(e=='yes'){
										DpUtil.payment(form,th,id,workflowId,recompenseMethod,model.data.recompenseType);
								}else{
									return;
								}
							});
						}
					}else{
						var choseFn =function (c){
							if(c=='yes'){
							 	MessageUtil.showQuestionMes(i18n('i18n.payment.fossSureSubmmit'),function(e){/*"提交后将在FOSS中生成应付单，是否确认提交？"*/
								  	if(e=='yes'){
									  		DpUtil.payment(form,th,id,workflowId,recompenseMethod,model.data.recompenseType);
								  	}else{
									  	return;
								  	}
							 	});
							}else{
								return;
							}
						}
						var message = '';
						if(Ext.getCmp('openName').getValue().length<=7&&Ext.getCmp('accountPropId').getValue()=='PUBLIC_ACCOUNT'){
							message='请确认账户是否是公司账户后再提交';
							MessageUtil.showQuestionMes(message,choseFn);
							return;
						}else if(Ext.getCmp('openName').getValue().length>4&&Ext.getCmp('accountPropId').getValue()=='PRIVATE_ACCOUNT'){
							message='请确认账户为个人账户再提交';
							MessageUtil.showQuestionMes(message,choseFn);
							return;
						}else{
							MessageUtil.showQuestionMes(i18n('i18n.payment.fossSureSubmmit'),function(e){/*"提交后将在FOSS中生成应付单，是否确认提交？"*/
							  	if(e=='yes'){
								  		DpUtil.payment(form,th,id,workflowId,recompenseMethod,model.data.recompenseType);
							  	}else{
								  	return;
							  	}
						 	});
						}
				 	}
//				}
//			}
//		}
//	};
//	var faiFn = function(json){
//		if(Ext.isEmpty(json)){
//			MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
//		}else{
//			MessageUtil.showErrorMes(json.message);
//		}
//	 };
//	 recompenseData.queryPaymentByEmployeeCode(params, sucFn, faiFn);
}
/**
 * @author zouming
 * <p>
 * 提交付款
 * </p>
 * @Date 2012-01-11
 * @param th
 * @param id
 * @param workflowId
 * @param recompenseMethod
 * @param model
 */
DpUtil.payment	= function(form,th,id,workflowId,recompenseMethod,recompenseType){
	var paymentMode = form.findField('paymentMode').getValue();
	var accountProp = form.findField('accountProp').getValue();
	var bankId = form.findField('openBank01').getValue();
	var branchId = form.findField('branchName').getValue();
	var	provinceId = form.findField('province').getValue();
	var cityId = form.findField('city').getValue();
	if(paymentMode=='汇款'){
		paymentMode='REMIT';
	}
	if(accountProp=='收银员卡账户'){
		accountProp = 'BACKUP_ACCOUNT';
	}
	if(accountProp == '个人账户'){
		accountProp = 'PRIVATE_ACCOUNT';
	}
	if(paymentMode=="CASH"||paymentMode=="CASH_AFTER_STRIKE_A_BALANCE"||paymentMode=='ALL_STRIKE_A_BALANCE'){
		bankId = form.findField('bankId').getValue();
		branchId = form.findField('branchId').getValue();
		provinceId = form.findField('bankProviceId').getValue();
		cityId = form.findField('bankCityId').getValue();
	}
	var bankName,branchName,provinceName,cityName;
	if(recompenseMethod=='online'||recompenseMethod==i18n('i18n.recompense.onlineRecompense')){
		bankId = null;
		branchId = null;
		provinceId = null;
		cityId = null;
		
		bankName = form.findField('bankName').getValue();
		branchName = form.findField('branchName').getValue();
		provinceName = form.findField('province').getValue();
		cityName = form.findField('city').getValue();
	}
	var param  = {'actionId':th.id,
	  		'recompenseView':{
	  			'app':{
	  				'id':id,
	  				'workflowId':workflowId,
	  				'recompenseMethod':recompenseMethod,
	  				'payment':{
					  'paymentMode' : paymentMode,
					  'accountProp' : accountProp,
					  'bank' 		: {
						  'id' : bankId,
						  'name':bankName
					  },
					  'branch' 		: {
						  'id' : branchId,
						  'name':branchName
					  },
					  'bankProvice'	:{
						  'id': provinceId,
						  'name':provinceName
					  },
					  'bankCity' 	:{
						 'id': cityId,
						 'name':cityName
					  },
					  'bankProviceName':provinceName,
					  'bankCityName':cityName,
					  'bankName':bankName,
					  'branchName':branchName,
					  'openName' 	: form.findField('openName').getValue(),
					  'account' 	: form.findField('account').getValue(),
					  'mobile'		: form.findField('mobile').getValue(),
					  'recompenseId': id
						 }
			  		}
			 }
		};
		var successFn = function(json){
			th.setDisabled(false);
			if(th.id==420){
				if(recompenseType=='unbilled'||recompenseType=='未开单理赔'){
				
			   MessageUtil.showInfoMes(i18n('i18n.recompense.unillpayMentSuccessInfo'));
				}else{
				  MessageUtil.showInfoMes(i18n('i18n.recompense.payMentSuccessInfo'));

				};
			   paymentApplyWin.close();
			   DpUtil.refreshRecompenseInfo();
			}else{
			   MessageUtil.showInfoMes(json.message);
			   DpUtil.refreshRecompenseInfo();
			}
		} ;
		var failureFn = function(json){
		   th.setDisabled(false);
		   if(Ext.isEmpty(json)){
			   	MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
			}else{
				MessageUtil.showErrorMes(json.message);
			}
		};
		th.setDisabled(false);
		recompenseData.performAction(param,successFn,failureFn);
}
/**.
 * <p>
 * 获取BUTTON按钮<br/>
 * <p>
 * @author 张斌
 * @时间 2012-4-17
 */
DpUtil.getActionButton =function(actions,recompenseId,status,recompenseMethod,deptId,areaId,confirmDeptId){
	Ext.getCmp(CONFIGNAME.get('costExplain')).setReadOnly(true);
	Ext.getCmp(CONFIGNAME.get('costExplain')).addCls('readonly');
	var buttonArray = new Array();
	//跟进按钮
	var messageInfo = Ext.create('Ext.Button',{
	    text:i18n('i18n.recompense.btnFellow'),
	    handler: function() {
	    	if(Ext.isEmpty(Ext.getCmp('messageSendWindow'))){
				new MessageSendWindow().show();
			}else{
				Ext.getCmp('messageSendWindow').show();
			}
			var messageList = Ext.getCmp('recompenseDetailsUI').record.messageList;
			Ext.getCmp('messageSendGird').getStore().loadData(messageList);
	    }
	});
	buttonArray.push(messageInfo);
	if(recompenseMethod=='online'){
		var printOnlineApplyButton =  Ext.create('Ext.Button',{			// 理赔协议打印功能键
		    text:i18n('i18n.recompense.printOnlineApplyButton'),
		    handler: function() {
		    	var recompenseData	= (CONFIG.get("TEST")) ? new RecompenseDataTestN(): new RecompenseDataN(),
		        	url 			= recompenseData.OnlineApplyPrintPage(),
		        	params			= {'recompenseId':recompenseId},
		        	succ			= function(json){
		    		window.parentData 	= json.onlineApplyPrintData ;
		    		},
			    	fail 			= function(json){
			        	if(Ext.isEmpty(json)){
			        		MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime')	);
						}else{
							MessageUtil.showErrorMes(json.message)	};
			        };
			    recompenseData.OnlineApplyPrintData( params,succ,fail );
	        	window.open(url,'recompensePrint','');
		    }
		});
		buttonArray.push(printOnlineApplyButton);
	}
	if(status=='Approved'||status=='PayFailed'||status=='Paid'||status=='InPayment'){
		buttonArray.push(new printButton());
	}
	//初步处理，资料确认状态
	if(status=='Handled'||status=='DocConfirmed'){
		var isHavePermision = false;
		if(Ext.isEmpty(Areas)){
			isHavePermision = false;
		}else{
			for(var i=0;i<Areas.length;i++){
				var blongDeptId = Areas[i].dept.id;
				if(blongDeptId==areaId){
					isHavePermision = true;
					break;
				}
			}
		}
		if(isHavePermision){
			if(recompenseMethod=='normal'){
				//初步处理按钮
				var processRecompense = Ext.create('Ext.Button',{
				    text:i18n('i18n.recompense.processRecompense'),
				    hidden:!isPermission('/recompense/processRecompense.action'),
				    handler: function() {
				    	var me = this;
				    	DpUtil.processRecompense(me);
				    }
				});
				buttonArray.push(processRecompense);
			}
		}
	}
	if(status=='Handled'&&recompenseMethod=='normal'&&confirmDeptId==User.deptId){
		
	
		//金额确认按钮
		var amountRecognized = Ext.create('Ext.Button',{
		    text:i18n('i18n.recompense.amountRecognized'),
		    hidden:!isPermission('/recompense/amountRecognized.action'),
		    handler: function() {
		    	DpUtil.amountRecognized();
		    }
		});
		buttonArray.push(amountRecognized);
		
			
		
	}
	if(status=='Handled'&&recompenseMethod=='normal'){
			var isHavePermision = false;
			if(Ext.isEmpty(Areas)){
				isHavePermision = false;
			}else{
				for(var i=0;i<Areas.length;i++){
					var blongDeptId = Areas[i].dept.id;
					if(blongDeptId==areaId){
						isHavePermision = true;
						break;
					}
				}
			}
			if(isHavePermision){	
				//取消初步处理
				var cancelProcess = Ext.create('Ext.Button',{
				    text:i18n('i18n.recompense.cancelProcess'),
				    hidden:!isPermission('/recompense/cancleRecompense.action'),
				    handler: function() {
				    	DpUtil.cancelProcess();
				    }
				});
				buttonArray.push(cancelProcess);
			}
	}
	if(Ext.isEmpty(actions)){
		//取消按钮
		var cancelBtn = Ext.create('Ext.Button',{
		    text:i18n('i18n.recompense.close'),
		    handler: function() {
		    	Ext.getCmp('recompenseDetailsUI').hide();
		    }
		});
		buttonArray.push(cancelBtn);
		return buttonArray;
	}
	for(var i = 0;i<actions.length;i++){
		var text = 'i18n.recompense.'+actions[i];
		var name = i18n(text);
		if(Ext.isEmpty(name)){
			continue;
		}
		//理赔退回按钮
		if(actions[i]=='440'){
			var button =Ext.create('Ext.Button',{
				id:actions[i],
			    text: name,
			    handler: function() {
			    	var chooseFn = function(e){
			    		if(e=='yes'){
				    		var btn = Ext.getCmp('440');
				    		btn.setDisabled(true);
							function disableButton(){
					    		if(btn){
					    			btn.setDisabled(false);
					    		}
					    		clearTimeout(task);
					    	}
					    	var task= setTimeout(disableButton, 2000);
					    	DpUtil.recompenseBack(recompenseId,recompenseMethod);
			    		}else{
			    			
			    		}
			    	}
			    	var beforeHideFn = function(){
			    		
			    	}
			    	var message = i18n('i18n.recompense.recompense.sure');
			    	MessageUtil.showQuestionMes(message,chooseFn,beforeHideFn);
			    }
			});
			buttonArray.push(button);
		}else if(actions[i]=='220'){
			var button =Ext.create('Ext.Button',{
				id:actions[i],
			    text: name,
			    handler: function() {
			    	DpUtil.showUpdateRecompenseWindow();
			    }
			});
			buttonArray.push(button);
		}else if(actions[i]=='320'){
			var button =Ext.create('Ext.Button',{
				id:actions[i],
			    text: name,
			    handler: function() {
			    	var me = this;
			    	DpUtil.processRecompenseFast(me);
			    }
			});
			buttonArray.push(button);
		}else if(actions[i]=='260'){
			var button =Ext.create('Ext.Button',{
				id:actions[i],
				text:i18n('i18n.recompense.doOnlineRecompense'),
			    handler: function(th) {
			    	var me = this;
			    	if(me.getText()==i18n('i18n.recompense.doOnlineRecompense')){
			    		Ext.getCmp('deptChargeAdd').setDisabled(false);
			    		Ext.getCmp('deptChargeUpdate').setDisabled(false);
			    		Ext.getCmp('deptChargeDelete').setDisabled(false);
			    		me.setText(i18n('i18n.recompense.260'));
			    		return;
			    	}
			    	if(me.getText()==i18n('i18n.recompense.260')){
			    		
			    		var deptChargeAddModelList = Ext.getCmp('deptChargeGird').getStore().getNewRecords( );			
			    		var deptChargeDelModelList = Ext.getCmp('deptChargeGird').getStore().getRemovedRecords( ) ;
			    		var deptChargeUpdModelList = Ext.getCmp('deptChargeGird').getStore().getUpdatedRecords( );
			    		var deptChargeAddList = DpUtil.changeModelListToDataList(deptChargeAddModelList);
			    		var deptChargeDelList = DpUtil.changeModelListToDataList(deptChargeDelModelList);
			    		var deptChargeUpdList = DpUtil.changeModelListToDataList(deptChargeUpdModelList);
			    		
			    		/**************************/
			    		  var id = Ext.getCmp('recompenseDetailsUI').record.id;
					       var workflowId = Ext.getCmp('recompenseDetailsUI').record.workflowId;
					       var recompenseMethod = Ext.getCmp('recompenseDetailsUI').record.recompenseMethod;
					       var paramTongGuo  = {
					    		   'actionId':th.id,
					    		   'recompenseView':{
					    			   'app':{
					    				   'id':id,
					    				   'workflowId':workflowId,
					    				   'recompenseMethod':recompenseMethod
					    				},
							       'deptChargeAddList':deptChargeAddList,
							       'deptChargeDelList':deptChargeDelList,
							       'deptChargeUpdList':deptChargeUpdList
					    		   	}
					       };
					       var successFnTongGuo = function(json){
					    	   th.setDisabled(false);
					    	   Ext.getCmp('deptChargeAdd').setDisabled(true);
					    	   Ext.getCmp('deptChargeUpdate').setDisabled(true);
					    	   Ext.getCmp('deptChargeDelete').setDisabled(true);
				    		   MessageUtil.showInfoMes(json.message);
					    	   DpUtil.refreshRecompenseInfo();
					       } ;
					       var failureFnTongGuo = function(json){
					    	   th.setDisabled(false);
					    	   if(Ext.isEmpty(json)){
					    		   MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
								}else{
									MessageUtil.showErrorMes(json.message);
								}
					       };

			    		/***************************/
			    		//判断入部门之后是否等于实际理赔金额
			        	if(!DpUtil.isDeptChargeEquelRealAmount()){
			        		MessageUtil.showErrorMes(i18n('i18n.recompense.deptChargeEquelRealAmount'));
			        		return ;
			        	}
			    		MessageUtil.showQuestionMes(i18n('i18n.recompense.surePass'),function(e){
			    			if(e=='yes'){
			    				th.setDisabled(true);
			    				recompenseData.performAction(paramTongGuo,successFnTongGuo,failureFnTongGuo);
			    			}
			    		});			    		 
			    	}
			    }
			});
			buttonArray.push(button);
		}else if(actions[i]=='230'){
			var button =Ext.create('Ext.Button',{
				id:actions[i],
			    text: name,
			    handler: function() {
			    	DpUtil.showDeleteRecompenseWindow(this.id);
			    }
			});
			buttonArray.push(button);
		}else if(actions[i]=='350'){
			var button =Ext.create('Ext.Button',{
				id:actions[i],
			    text: name,
			    handler: function() {
			    	DpUtil.overPay(this.id);
			    }
			});
			buttonArray.push(button);
		}/*else if(actions[i]=='420'){
			var button =Ext.create('Ext.Button',{
				id:actions[i],
			    text: name,
			    handler: function() {
			    	DpUtil.payMent(this.id);
			    }
			});
			buttonArray.push(button);
		}*/else if(actions[i]=='370'){
			var button =Ext.create('Ext.Button',{
				id:actions[i],
			    text: name,
			    handler: function() {
			    	DpUtil.openButton();
			    	Ext.getCmp('detailBtnPanelPoprightbuttonpanel').add(new commitRecompenseSave());
			    	this.hide();
			    }
			});
			buttonArray.push(button);
		}else if(actions[i]=='380'){
			var button =Ext.create('Ext.Button',{
				id:actions[i],
			    text: name,
			    handler: function() {
			    	Ext.getCmp('deptChargeDelete').setDisabled(false);
			    	Ext.getCmp('deptChargeUpdate').setDisabled(false);
			    	Ext.getCmp('deptChargeAdd').setDisabled(false);
			    	Ext.getCmp('responsibleDeptDelete').setDisabled(false);
			    	Ext.getCmp('responsibleDeptUpdate').setDisabled(false);
			    	Ext.getCmp('responsibleDeptAdd').setDisabled(false);
			    	Ext.getCmp(CONFIGNAME.get('realAmount')).setReadOnly(false);
			    	Ext.getCmp('detailBtnPanelPoprightbuttonpanel').add(new overPayRecompenseCommit());
			    	this.hide();
			    }
			});
			buttonArray.push(button);
		}else if(actions[i]=='240'){
			var button =Ext.create('Ext.Button',{
				id:actions[i],
			    text: name,
			    handler: function() {
			    	    DpUtil.refuseOnlineRecompense();
			    }
			});
			buttonArray.push(button);
		}else if(actions[i]=='9010'){
			Ext.getCmp(CONFIGNAME.get('costExplain')).setReadOnly(false);
			Ext.getCmp(CONFIGNAME.get('costExplain')).removeCls('readonly');
			var button =Ext.create('Ext.Button',{
				id:actions[i],
			    text: name,
			    handler: function() {
			    	MessageUtil.showQuestionMes(i18n('i18n.recompense.confirmFreePay'),
						function(e){
							if (e == 'yes') {
								var id = Ext.getCmp('recompenseDetailsUI').record.id;
							       var workflowId = Ext.getCmp('recompenseDetailsUI').record.workflowId;
							       var recompenseMethod = Ext.getCmp('recompenseDetailsUI').record.recompenseMethod;
							       var costExplain = Ext.getCmp(CONFIGNAME.get('costExplain')).getValue();
							       if(Ext.isEmpty(costExplain)){
							    	   MessageUtil.showInfoMes(i18n('i18n.recompense.costExplainNotNull'));
							    	   return;
							       }
							       var param  = {'actionId':'9010','recompenseView':{'app':{'id':id,'workflowId':workflowId,'recompenseMethod':recompenseMethod,'costExplain':costExplain}}};
							       var successFn = function(json){
							    	   MessageUtil.showMessage(json.message);
							    	   DpUtil.refreshRecompenseInfo();
							       } ;
							       var failureFn = function(json){
							    	   if(Ext.isEmpty(json)){
							    		   MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
										}else{
											MessageUtil.showErrorMes(json.message);
										}
							       };
							       recompenseData.performAction(param,successFn,failureFn);
							}
					});
			    }
			});
			buttonArray.push(button);
		}else if(actions[i]=='270'){
			var button =Ext.create('Ext.Button',{
				id:actions[i],
			    text: name,
			    handler: function() {
			    	if(Ext.isEmpty(Ext.getCmp('returnBackResionWindow'))){
			    		new ReturnBackResionWindow().show();
			    	}else{
			    		Ext.getCmp('returnBackResionWindow').show();
			    	}
			    }
			});
			buttonArray.push(button);
		}else if(actions[i]=='310'){
			var button =Ext.create('Ext.Button',{
				id:actions[i],
			    text: name,
			    handler: function(th) {
			    	MessageUtil.showQuestionMes(i18n('i18n.recompense.confirm310'),
			    			function(e){
			    				if (e == 'yes') {
			    					 var id = Ext.getCmp('recompenseDetailsUI').record.id;
			  				       var workflowId = Ext.getCmp('recompenseDetailsUI').record.workflowId;
			  				       var recompenseMethod = Ext.getCmp('recompenseDetailsUI').record.recompenseMethod;
			  				       var param  = {'actionId':'310','recompenseView':{'app':{'id':id,'workflowId':workflowId,'recompenseMethod':recompenseMethod}}};
			  				       var successFn = function(json){
			  				    	   th.setDisabled(false);
			  				    	   MessageUtil.showInfoMes(json.message);
			  					       DpUtil.refreshRecompenseInfo();
			  				       };
			  				       var failureFn = function(json){
			  				    	   th.setDisabled(false);
			  				    	   if(Ext.isEmpty(json)){
			  				    		   MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
			  							}else{
			  								MessageUtil.showErrorMes(json.message);
			  							}
			  				       };
			  				       th.setDisabled(true);
			  				       recompenseData.performAction(param,successFn,failureFn);
			    				}
			    	});
			    }
			});
			buttonArray.push(button);
		}else if(actions[i]=='410'){//付款退回
			var button =Ext.create('Ext.Button',{
				id:actions[i],
			    text: name,
			    handler: function(th) {
			    	MessageUtil.showQuestionMes(i18n('i18n.payment.pleaseSureReturnPayment'),
			    			function(e){
			    				if (e == 'yes') {
			    					 var id = Ext.getCmp('recompenseDetailsUI').record.id;
			  				       var workflowId = Ext.getCmp('recompenseDetailsUI').record.workflowId;
			  				       var recompenseMethod = Ext.getCmp('recompenseDetailsUI').record.recompenseMethod;
			  				       var param  = {'actionId':'410','recompenseView':{'app':{'id':id,'workflowId':workflowId,'recompenseMethod':recompenseMethod}}};
			  				       var successFn = function(json){
			  				    	   th.setDisabled(false);
			  				    	   MessageUtil.showInfoMes(json.message);
			  					       DpUtil.refreshRecompenseInfo();
			  				       };
			  				       var failureFn = function(json){
			  				    	   th.setDisabled(false);
			  				    	   if(Ext.isEmpty(json)){
			  				    		   MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
			  							}else{
			  								MessageUtil.showErrorMes(json.message);
			  							}
			  				       };
			  				       th.setDisabled(true);
			  				       recompenseData.performAction(param,successFn,failureFn);
			    				}
			    	});
			    }
			});
			buttonArray.push(button);
		}else if(actions[i]=='420'){
			var button =Ext.create('Ext.Button',{//付款申请
				id:actions[i],
			    text: name,
			    handler: function(th) {
			    	if(!Ext.isEmpty(Ext.getCmp('account'))&&!Ext.isEmpty(Ext.getCmp('account').getStore())){
						   Ext.getCmp('account').getStore().removeAll();
					   }
			       var id = Ext.getCmp('recompenseDetailsUI').record.id;
			       var workflowId = Ext.getCmp('recompenseDetailsUI').record.workflowId;
			       var recompenseMethod = Ext.getCmp('recompenseDetailsUI').record.recompenseMethod;
			       var model = Ext.create('RecompenseApp',Ext.getCmp('recompenseDetailsUI').record);
//			      if(Ext.isEmpty(paymentApplyWin)&&name=="付款申请"){
			    	  paymentApplyWin = Ext.create('PopWindow',{
			    		  title:i18n('i18n.payment.recompensePayment'),
			    		  closeAction:'hide',
			    		  width:580,
			    		  items:Ext.create('PaymenApplyForm',{id:'paymenApplyFormId'}),
			    		  buttons:[{
			    			  text:i18n('i18n.payment.submmitPeymentApply'),//提交付款申请
			    			  handler:function(){
			    				  DpUtil.accountCompare(th,id,workflowId,recompenseMethod,model);
			    			  }
			    		  },{
			    			  text:i18n('i18n.recompense.cancel'),//'取消',
			    			  handler:function(){
			    				  paymentApplyWin.hide();
			    			  }
			    		  }]
			    	  });
			    	  
			    	  //如果为在线理赔
			    	  if(recompenseMethod=='online'){
			    		  var param = {
			    				  'recompenseId':id
			    		  };
			    		  var successFunction = function(json){
			    			  var form = Ext.getCmp('paymenApplyFormId').getForm();
			    			  
			    			  /**
			    			   * form先读取详情界面带出的值
			    			   */
			    			  model.data.recompenseType = 
				    			  DpUtil.changeDictionaryCodeToDescrip(model.data.recompenseType,DataDictionary.RECOMPENSE_TYPE);
				    		  model.data.recompenseMethod = 
				    			  DpUtil.changeDictionaryCodeToDescrip(model.data.recompenseMethod,DataDictionary.RECOMPENSE_WAY);
				    		  form.loadRecord(model);
				    		  
				    		  /**
				    		   * 读取请求返回的值
				    		   */
			    			  form.findField('paymentMode').setValue("汇款");
				    		  form.findField('paymentMode').setReadOnly(true);
				    		 // form.findField('accountProp').setReadOnly(true);
				    		  
				    		  Ext.getCmp('accountPropId').getStore().removeAll();
								Ext.getCmp('accountPropId').getStore().add({
									'code':'PUBLIC_ACCOUNT','codeDesc':i18n('i18n.payment.outToPublicAccount')},
									{'code':'PRIVATE_ACCOUNT','codeDesc':i18n('i18n.payment.outToPrivateAccount')});
							Ext.getCmp('accountPropId').setValue(i18n('i18n.payment.outToPrivateAccount'));
				    		  
				    		  form.findField('bankName').forceSelection = false;
				    		  form.findField('bankName').doComponentLayout();				    		  
				    		  form.findField('bankName').setReadOnly(true);
				    		  form.findField('bankName').setValue(json.onlineApplyPrintData.bankName);
				    		  
				    		  form.findField('province').setReadOnly(true);
				    		  if(json.onlineApplyPrintData.branchName!==null){
				    			  var pValue = json.onlineApplyPrintData.branchName.split(',')[0];
				    			  if(pValue !== null ){
				    				  pValue = pValue.split('-')[0]
				    				  form.findField('province').setValue(pValue);
				    			  }
				    		  }
				    		  
				    		  form.findField('city').setReadOnly(true);
				    		  form.findField('city').setValue(json.onlineApplyPrintData.city);
				    		  
				    		  form.findField('branchName').forceSelection = false;
				    		  form.findField('branchName').doComponentLayout();	
				    		  form.findField('branchName').setReadOnly(true);
				    		  if(json.onlineApplyPrintData.branchName!=null){
				    			  var branchName = json.onlineApplyPrintData.branchName.split(',');
				    			  if(branchName.length>=2){
				    				  form.findField('branchName').setValue(branchName[1]);
				    			  }
				    		  }
				    		  
				    		  form.findField('openName').setReadOnly(true);
				    		  form.findField('openName').setValue(json.onlineApplyPrintData.openName);
				    		  
				    		  form.findField('account').setReadOnly(true);
				    		  form.findField('account').setValue(json.onlineApplyPrintData.account);
				    		  
				    		  form.findField('mobile').setReadOnly(true);
				    		  form.findField('mobile').setValue(json.onlineApplyPrintData.mobile);
				    		  
				    		  /**
				    		   * 去除了设置付款方式的代码，在这里没用。
				    		   */
				    		  
				    		  //
				    		  if(!Ext.isEmpty(model.data.customer)){
				    			  form.findField('custNumber').setValue(model.data.customer.custNumber);
					    		  form.findField('custName').setValue(model.data.customer.custName);
				    		  }else{
					    		  //1为发货方（出发）
				    		  	  if(model.data.claimParty=='1'&&!Ext.isEmpty(model.data.waybill)){
				    		  	  	form.findField('custNumber').setValue(model.data.waybill.leaveCustomerId);
				    		  	  }
				    		  	  //2为收货方（到达）
				    		  	  if(model.data.claimParty=='2'&&!Ext.isEmpty(model.data.waybill)){
				    		  	  	form.findField('custNumber').setValue(model.data.waybill.arriveCustomerId);
				    		  	  }
				    		  }
				    		  paymentApplyWin.show();
			    		  };
			    		  var failFunction = function(json){
			    			  if(Ext.isEmpty(json)){
			    					MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
			    				}else{
			    					MessageUtil.showErrorMes(json.message);
			    				}
			    		  };
			    		  RecompenseDataN.prototype.OnlineApplyPrintData( param, successFunction, failFunction )
			    		  
			    	  }else {//if(custDrowMoneyType=='现金')
			    		  var form = Ext.getCmp('paymenApplyFormId').getForm();
			    		  form.findField('paymentMode').setReadOnly(false);
			    		  form.findField('accountProp').setReadOnly(false);
			    		  form.findField('bankName').setReadOnly(false);
			    		  form.findField('branchName').setReadOnly(false);
			    		  form.findField('openName').setReadOnly(false);
			    		  form.findField('account').setReadOnly(false);
			    		  form.findField('mobile').setReadOnly(false);
			    		 
			    		  //
			    		  if(form.findField('accountProp').getStore().data.length!=2){
			    			  form.findField('accountProp').getStore().removeAll(true);
			    			  form.findField('accountProp').getStore().add(
						    		  	{'code':'PRIVATE_ACCOUNT','codeDesc':'个人账户'},
						    		  		{'code':'PUBLIC_ACCOUNT','codeDesc':'公司账户'});	
			    		  }
			    		  		    		 
			    		
			    		  if(model.data.recompenseType == 'unbilled'|| 
			    				  	model.data.recompenseType == '未开单理赔'){
			    			 var paymentMode =  Ext.getCmp('paymenApplyFormId').getForm().findField('paymentMode');
			    			 if(paymentMode.getStore().data.length!=2){
			    				 paymentMode.getStore().removeAll(true);
			    				 paymentMode.getStore().add({'code':'REMIT','codeDesc':i18n('i18n.payment.remittance')/*'汇款'*/},
 					 					{'code':'CASH','codeDesc':i18n('i18n.payment.cash')/*'现金'*/}); 
			    			 }
			    		  }else{
			    		  	 var paymentMode = form.findField('paymentMode');
			    			 if(paymentMode.getStore().data.length!=5){
			    			 	 paymentMode.getStore().removeAll(true);
			    				 paymentMode.getStore().add(
			    				 
			    				 	/**
			    				 	 * BUG117修改
			    				 	 */	
			    				 		{'code':'REMIT','codeDesc':i18n('i18n.payment.remittance')/*'汇款'*/},
 					 					{'code':'CASH','codeDesc':i18n('i18n.payment.cash')/*'现金'*/},
 					 					{'code':'ALL_STRIKE_A_BALANCE','codeDesc':i18n('i18n.payment.AllStrikeABalance')/*'全额冲账'*/},
 					 					{'code':'CASH_AFTER_STRIKE_A_BALANCE','codeDesc':i18n('i18n.payment.cashAfterStrikeABalance')/*'冲账后付现'*/},
 					 					{'code':'REMIT_AFTER_STRIKE_A_BALANCE','codeDesc':i18n('i18n.payment.remitAfterStrikeABalance')/*'冲账后汇款'*/}
 					 					); 
			    			 }	
			    		  }
			    		  model.data.recompenseType = 
			    			  DpUtil.changeDictionaryCodeToDescrip(model.data.recompenseType,DataDictionary.RECOMPENSE_TYPE);
			    		  model.data.recompenseMethod = 
			    			  DpUtil.changeDictionaryCodeToDescrip(model.data.recompenseMethod,DataDictionary.RECOMPENSE_WAY);
			    		  form.loadRecord(model);
			    		  
			    		  //TODO:若无客户编码，则后台生成散客编码，显示为出发或则到达客户编码
			    		  if(!Ext.isEmpty(model.data.customer)){
			    			  form.findField('custNumber').setValue(model.data.customer.custNumber);
				    		  form.findField('custName').setValue(model.data.customer.custName);
			    		  }else{
	    		  			  //1为发货方（出发）
			    		  	  if(model.data.claimParty=='1'&&!Ext.isEmpty(model.data.waybill)){
			    		  	  	form.findField('custNumber').setValue(model.data.waybill.leaveCustomerId);
			    		  	  }
			    		  	  //2为收货方（到达）
			    		  	  if(model.data.claimParty=='2'&&!Ext.isEmpty(model.data.waybill)){
			    		  	  	form.findField('custNumber').setValue(model.data.waybill.arriveCustomerId);
			    		  	  }
			    		  }
			    		  paymentApplyWin.show();
			    	  }	    
			    }
			});
			buttonArray.push(button);
		}else{
			var button =Ext.create('Ext.Button',{
				id:actions[i],
			    text: name,
			    handler: function(th) {
			       var id = Ext.getCmp('recompenseDetailsUI').record.id;
			       var workflowId = Ext.getCmp('recompenseDetailsUI').record.workflowId;
			       var recompenseMethod = Ext.getCmp('recompenseDetailsUI').record.recompenseMethod;
			       var param  = {'actionId':th.id,'recompenseView':{'app':{'id':id,'workflowId':workflowId,'recompenseMethod':recompenseMethod}}};
			       var successFn = function(json){
			    	   th.setDisabled(false);
			    	   if(th.id==420){
			    		   MessageUtil.showInfoMes(i18n('i18n.recompense.payMentSuccessInfo'));
				    	   DpUtil.refreshRecompenseInfo();
			    	   }else{
			    		   MessageUtil.showInfoMes(json.message);
				    	   DpUtil.refreshRecompenseInfo();
			    	   }
			       } ;
			       var failureFn = function(json){
			    	   th.setDisabled(false);
			    	   if(Ext.isEmpty(json)){
			    		   MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
						}else{
							MessageUtil.showErrorMes(json.message);
						}
			       };
			       if(th.id==420){//付款申请
				   		MessageUtil.showQuestionMes(
				   				'提交付款申请将在ERP中生成理赔单，请核实理赔金额，是否确定提交？',function(e){
				   			if (e == 'yes') {
				   				th.setDisabled(true);
						    	recompenseData.performAction(param,successFn,failureFn);
				   			}else{
				   				return;
				   			}
				   		});
			       }else if(th.id==390){//多赔处理退回
				   		MessageUtil.showQuestionMes(
				   				'是否退回该理赔？',function(e){
				   			if (e == 'yes') {
				   				th.setDisabled(true);
						    	recompenseData.performAction(param,successFn,failureFn);
				   			}else{
				   				return;
				   			}
				   		});
			       }else{
			    	   	th.setDisabled(true);
			    	   	recompenseData.performAction(param,successFn,failureFn);
			       }
			       
			    }
			});
			buttonArray.push(button);
		}
	}
	//取消按钮
	var cancelBtn = Ext.create('Ext.Button',{
	    text:i18n('i18n.recompense.close'),
	    handler: function() {
	    	Ext.getCmp('recompenseDetailsUI').hide();
	    }
	});
	buttonArray.push(cancelBtn);
	return buttonArray;
};
/**.
 * <p>
 * 校验入部门费用之后是否等于实际金额<br/>
 * <p>
 * @author 张斌
 * @时间 2012-3-12
 */
DpUtil.isDeptChargeEquelRealAmount = function() {
	var deptCharge = 0;
	Ext.getCmp('deptChargeGird').getStore().each(function(record){
		deptCharge = deptCharge + record.get(CONFIGNAME.get('amount'));
	});
	deptCharge = deptCharge.toFixed(2);//四舍五入保留两位小数
	var realAmount = Ext.getCmp(CONFIGNAME.get('realAmount')).getValue();
	if(realAmount!=deptCharge){
		return false;
	}else{
		return true;
	}
};
/**.
 * <p>
 * 公共方法，通过storeId和model创建STORE<br/>
 * <p>
 * @param  storeId  
 * @param  model   store所用到的model名
 * @param  fields   store所用到的fields
 * @returns store  返回创建的store
 * @author 张斌
 * @时间 2012-3-12
 */
DpUtil.getStore = function(storeId,model,fields,data) {
	var store = Ext.data.StoreManager.lookup(storeId);
	if(Ext.isEmpty(data)){
		data = [];
	}
	if(!Ext.isEmpty(model)){
		if(store===undefined){
			store = Ext.create('Ext.data.Store', {
				storeId:storeId,
			    model:model,
			    data:data
		     });
		}
	}
	if(!Ext.isEmpty(fields)){
		if(store===undefined){
			store = Ext.create('Ext.data.Store', {
				storeId:storeId,
			    fields:fields,
			    data:data
		     });
		}
	}
	return store;
};
//Ajax请求--json
DpUtil.requestJsonAjax = function(url,params,successFn,failFn)
{
	Ext.Ajax.request({
		url:url,
		jsonData:params,
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				successFn(result);
			}else{
				failFn(result);
			}
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
			failFn(result);
		}
	});
};

//Ajax请求--文件表单的请求
DpUtil.requestFileUploadAjax = function(url,form,successFn,failFn)
{
	Ext.Ajax.request({
		url:url,
		form:form,
		isUpload:true,
		success:function(form, action){
			var result = action.result;
			if(result.success){
				successFn(result);
			}else{
				failFn(result);
			}
		},
		failure:function(form, action){
			var result = action.result;
			failFn(result);
		}
	});
};

//Ajax请求--非json字符串
DpUtil.requestAjax = function(url,params,successFn,failFn)
{
	Ext.Ajax.request({
		url:url,
		params:params,
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				successFn(result);
			}else{
				failFn(result);
			}
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
			failFn(result);
		}
	});
};
//转换long类型为日期
DpUtil.changeLongToDate = function(value) {
	if (value != null) {
		var date = new Date(value);
		return date;
	} else {
		return null;
	}
};

/**.
 * <p>
 * 获取业务字典，并将数据存入STORE<br/>
 * <p>
 * @param param  订单创建提交的数据
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-3-11
 */
DpUtil.getBusinessDictionary = function(param,successFn,failureFn){
	/**
	 * 得到数据字典数据
	 */
	var url = '../common/queryAllDataDictionaryByKeys.action';
	DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 设置元素为readOnly<br/>
 * <p>
 * @param readOnlyIdList  设置为readOnly的元素ID数组
 * @author 张斌
 * @时间 2012-3-15
 */
DpUtil.setReadOnly = function(readOnlyIdList){
     for(var i=0;i<readOnlyIdList.length;i++){
    	 Ext.getCmp(readOnlyIdList[i]).setReadOnly(true);
    	 Ext.getCmp(readOnlyIdList[i]).addCls('readonly');
     }
};

/**.
 * <p>
 * 设置元素为隐藏并且销毁，使其不在校验<br/>
 * <p>
 * @param hiddenIdList  设置为readOnly的元素ID数组
 * @author 张斌
 * @时间 2012-3-15
 */
DpUtil.setHiddenAndDestroy = function(hiddenIdList){
     for(var i=0;i<hiddenIdList.length;i++){
    	 Ext.getCmp(hiddenIdList[i]).hide();
    	 Ext.getCmp(hiddenIdList[i]).destroy( );
     }
};
/**.
 * <p>
 * 设置元素为隐藏<br/>
 * <p>
 * @param hiddenIdList  设置为readOnly的元素ID数组
 * @author 张斌
 * @时间 2012-3-15
 */
DpUtil.setHidden = function(hiddenIdList){
     for(var i=0;i<hiddenIdList.length;i++){
    	 Ext.getCmp(hiddenIdList[i]).hide();
     }
};
/**.
 * <p>
 * 设置元素为销毁<br/>
 * <p>
 * @param destoryIdList  设置为destory的元素ID数组
 * @author 张斌
 * @时间 2012-3-21
 */
DpUtil.setDestroy = function(destoryIdList){
     for(var i=0;i<destoryIdList.length;i++){
    	 Ext.getCmp(destoryIdList[i]).destroy();
     }
};
/**.
 * <p>
 * 设置元素为不可用<br/>
 * <p>
 * @param disabledIdList  设置为Disabled的元素ID数组
 * @author 张斌
 * @时间 2012-3-21
 */
DpUtil.setDisabled =function(disabledIdList){
	for(var i=0;i<disabledIdList.length;i++){
   	 Ext.getCmp(disabledIdList[i]).setDisabled(true);
    }
};
/**.
 * <p>
 * 清楚事件<br/>
 * <p>
 * @param clearIdList  设置为清楚时间的元素ID数组
 * @author 张斌
 * @时间 2012-3-22
 */
DpUtil.clearListeners =function(clearIdList){
	for(var i=0;i<clearIdList.length;i++){
   	 Ext.getCmp(clearIdList[i]).clearListeners( );
    }
};
/**.
 * <p>
 * 将业务字典的code转换成描述codeDesc<br/>
 * <p>
 * @param value  所要转换的值
 * @param dataDictionary 所对应业务字典
 * @author 张斌
 * @时间 2012-3-20
 */
DpUtil.changeDictionaryCodeToDescrip = function(value, dataDictionary) {
	if (value != null && dataDictionary != null) {
		for ( var i = 0; i < dataDictionary.length; i++) {
			if (value == dataDictionary[i].code) {
			     return dataDictionary[i].codeDesc;
			}
		}
	} else {
	   return null;
	}
};

/**.
 * <p>
 * 将true转换为“是”，false转换为“否”<br/>
 * <p>
 * @param value  所要转换的值
 * @author 张斌
 * @时间 2012-3-20
 */
DpUtil.changeTrueAndFalse = function(value) {
	if(value=='true'){
		return i18n('i18n.recompense.yes');
	}else if(value=='false') {
		return i18n('i18n.recompense.no');
	}else if (value==true) {
		return i18n('i18n.recompense.yes');
	} else if(value==false) {
		return i18n('i18n.recompense.no');
	}else{
		return null;
	}
};
/**.
 * <p>
 * 按回车之后查询的方法<br/>
 * <p>
 * @param e 按回车键时，EXT自带参数
 * @param id 该元素的ID，与storeId一致
 * @param getData所调用的方法
 * @param paramName ACTION参数的名字
 * @param limit 限制必须输入几个字之后才执行getData
 * @author 张斌
 * @时间 2012-3-20
 */
DpUtil.keyPress = function(e,getData,param,limit,successFn) {
	var paramValue = e.target.value;
	if(Ext.isEmpty(paramValue)){
		return ;
	}
	if(paramValue.length<limit){
		return ;
	}
	//按回车并且输入的字符长度大于limit
	if (e.getKey() == Ext.EventObject.ENTER && paramValue!="" &&paramValue.length>=limit) 
	{
		var failureFn = function(json){
			if(Ext.isEmpty(json)){
				MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
			}else{
				MessageUtil.showErrorMes(json.message);
			}
		};
		getData(param,successFn,failureFn);
	}
};


/**.
 * <p>
 * 讲电话号码全部转化xxx-xxxxxxxx-xxx的格式<br/>
 * <p>
 * @param replacePhone 操作的方法
 * @author 张斌
 * @时间 2012-3-23
 */
DpUtil.replacePhone = function(phone){
	phone.replace('/','-');
	phone.replace('\\','-');
	return phone;
};


/**.
 * <p>
 * 数组中是否有空值<br/>
 * <p>
 * @param array 数组
 * @author 张斌
 * @时间 2012-3-24
 */
DpUtil.isHaveEmpty = function(array){
	var boolen = false;
	for(var i = 0;i<array.length;i++){
		if(Ext.isEmpty(array[i])){
			boolen = true;
			return boolen;
		}
	}
	return boolen;
};
/**.
 * <p>
 * JS日期的format方法<br/>
 * <p>
 * @param format 日期格式
 * @author 张斌
 * @时间 2012-3-23
 */
Date.prototype.format = function(format){
	if(Ext.isEmpty(this)||this.getTime()==0||this.toString().indexOf('GMT')==-1){
		return null;
	}
	var o = {
		"M+" : this.getMonth()+1, //month
		"d+" : this.getDate(), //day
		"h+" : this.getHours(), //hour
		"m+" : this.getMinutes(), //minute
		"s+" : this.getSeconds(), //second
		"q+" : Math.floor((this.getMonth()+3)/3), //quarter
		"S" : this.getMilliseconds() //millisecond
	};

	if(/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	};

	for(var k in o) {
		if(new RegExp("("+ k +")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
		}
	};
	return format;
} ;

/**.
 * <p>
 * 根据传的参数生成查询条件<br/>
 * <p>
 * @param 查询条件model
 * @author 潘光均
 * @时间 2012-4-13
 */
DpUtil.produceSearchParams=function(record){
	var reportDept = record.get(CONFIGNAME.get('reportDept'));
	if(!Ext.isEmpty(reportDept)){
		reportDept = reportDept.trim();
	}
	var belongArea = record.get(CONFIGNAME.get('belongArea'));
	if(!Ext.isEmpty(belongArea)){
		belongArea = belongArea.trim();
	}
	var custNum = record.get(CONFIGNAME.get('conCustNum'));
	if(!Ext.isEmpty(custNum)){
		custNum = custNum.trim();
	}
	var custName = record.get(CONFIGNAME.get('custName'));
	if(!Ext.isEmpty(custName)){
		custName = custName.trim();
	}
	var waybillNum = record.get(CONFIGNAME.get('conWaybillNum'));
	if(!Ext.isEmpty(waybillNum)){
		waybillNum = waybillNum.trim();
	}
	var recompenseAmount=record.get('recompenseAmount');
	if(!Ext.isEmpty(recompenseAmount)){
		recompenseAmount = recompenseAmount.trim();
	}
	var recompenseState=record.get(CONFIGNAME.get('recompenseState'));
	if(recompenseState=='ALL'){
		recompenseState=''
	}
	var realAmount=record.get(CONFIGNAME.get('realAmount'));
	if(realAmount=='ALL'){
		realAmount=''
	}
	var recompenseAmount=record.get(CONFIGNAME.get('recompenseAmount'));
	if(recompenseAmount=='ALL'){
		recompenseAmount=''
	}
	var searchParams = {
			'recomSearchCondition.approveStartTime' :record.get(CONFIGNAME.get('approveTime')),
			'recomSearchCondition.approveEndTime' : record.get(CONFIGNAME.get('approveEndTime')),
			'recomSearchCondition.reportDept' : reportDept,
			'recomSearchCondition.reportStartTime' : record.get(CONFIGNAME.get('conReportStartTime')),
			'recomSearchCondition.reportEndTime' : record.get(CONFIGNAME.get('reportEndTime')),
			'recomSearchCondition.recompenseMethod' : record.get(CONFIGNAME.get('conRecompenseMethod')),
			'recomSearchCondition.recompenseType' : record.get(CONFIGNAME.get('recompenseType')),
			'recomSearchCondition.belongArea' : belongArea,
			'recomSearchCondition.recompenseState' : recompenseState,
			'recomSearchCondition.waybillNum' : waybillNum,
			'recomSearchCondition.custNum' : custNum,
			'recomSearchCondition.custName' : custName,
			'recomSearchCondition.customerLevel' : record.get(CONFIGNAME.get('conCustomerLevel')),
			'recomSearchCondition.initSearch' : INITSEARCH,
			'recomSearchCondition.beginDuration' : record.get('beginDuration'),
			'recomSearchCondition.endDuration' : record.get('endDuration'),
			'recomSearchCondition.recompenseAmount' : recompenseAmount,
			'recomSearchCondition.realAmount' : realAmount,
			'recomSearchCondition.transType' : record.get('transType')
			
		};
	INITSEARCH = false;
		return searchParams;
};

/**.
 * <p>
 * 根据传的参数生成查询条件<br/>
 * <p>
 * @param modelList 要转换的Modellist
 * @returns  dataList
 * @author 张斌
 * @时间 2012-4-16
 */
DpUtil.changeModelListToDataList = function(modelList){
	var dataList = new Array();
	for(var i = 0;i<modelList.length;i++){
		dataList.push(modelList[i].data);
	}
	return dataList;
};
/**.
 * <p>
 * 校验一个字符是否在一个数组中<br/>
 * <p>
 * @param pushList  说有数据都会push到这个list中
 * @author 张斌
 * @时间 2012-4-20
 */
DpUtil.listPushOtherList=function(pushList,list){
	for ( var i = 0; i < list.length; i++) {
		pushList.push(list[i])
	}
};
/**.
 * <p>
 * 校验一个字符是否在一个数组中<br/>
 * <p>
 * @param 查询条件model
 * @author 潘光均
 * @时间 2012-4-13
 */
DpUtil.isArrayContaintChar=function(char,array){
	for ( var i = 0; i < array.length; i++) {
		if (array[i]==char) {
			return true;
		}
		return false;
	}
};
/**
 * <p>
 * 理赔退回请求
 * </p>
 * @param recompenseId,recompenseMethod
 * @author 杨伟
 * @lastUpdateTime 2013-12-16
 */
DpUtil.recompenseBack = function(recompenseId,recompenseMethod){
	var url = '../recompense/recompenseBack.action';
	var params = {'app':{'id':recompenseId},'recompenseMethod':recompenseMethod}
	var successFn = function(json){
		MessageUtil.showInfoMes(i18n('i18n.recompense.recompenseBack.success'));
		DpUtil.refreshRecompenseInfo();
	}
	var failFn = function(json){
		if(Ext.isEmpty(json)){
			MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
		}else{
			MessageUtil.showErrorMes(json.message);
		}
	}
	DpUtil.requestJsonAjax(url,params,successFn,failFn);
};
/**
 * @功能：为js中的STRING加上trim方法
 * @作者： 张斌
 * @创建时间：2012-02-20
 */
String.prototype.trim= function(){  
    // 用正则表达式将前后空格  
    // 用空字符串替代。  
    return this.replace(/(^\s*)|(\s*$)/g, "");  
};

//取消悬浮框5秒消失
Ext.apply(Ext.tip.QuickTip.prototype,{dismissDelay:0});