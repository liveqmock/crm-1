CommonStore = function(){};
/**
 * 查询类型：处理编号、凭证号、重复工单码
 */
Ext.define('DutySearchTypeStore',{
	extend:'Ext.data.Store',
	model:'ComboModel',
	data : [
        {"code":"dealNumber", "codeDesc":"处理编号"},
        {"code":"voucherNumber", "codeDesc":"凭证号"},
        {"code":"rebindNo", "codeDesc":"重复工单码"}
    ]
});


/**
 * 工单责任查询 - 处理经过 store
 */
Ext.define('DutyDealProcedureStore',{
	extend:'Ext.data.Store',
	model:'DutyDealProcedureModel'
});
/**
 * 工单责任查询 - 责任划分结果 store
 */
Ext.define('DutyAllocationResultDeptStore',{
	extend:'Ext.data.Store',
	model:'DutyAllocationResultDeptModel',
	proxy:{
		type:'ajax',
		url:'../duty/searchDutyResult.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'dutyResult'
		}
	}
});
/**
 * 工单责任查询、工单责任审批 - 责任反馈记录列表的 store
 * xiaohongye
 */
Ext.define('DutyFeedbackRecordListStore',{
	extend:'Ext.data.Store',
	model:'DutyFeedbackRecordListModel',
	proxy:{
		type:'ajax',
		url:'../duty/searchDutyFeedbackByDutyId.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'dutyFeedbackList'
		}
	}
});

/**
 * 责任反馈 附件listStore
 */
Ext.define('FeedAttachListStore',{
	extend:'Ext.data.Store',
	model:'FeedAttachModel',
	pageSize:20,autoLoad:false,
	proxy:{
		type:'ajax'
		,api:{read:'../duty/searchFeedAttachList.action'}
		,actionMethods:'POST'
		,reader:{
			type:'json',root:'feedAttachList'
		}
	}
});

/**
 *  ProcessResultStore
	,searchResultList:null //处理结果数据
 */
Ext.define('DutyProcessResultDetailStore',{
	extend:'Ext.data.Store'
	,model:'DutyProcessResultDetailModel'
});
/**
 *  ProcessResultStore
	,searchResultList:null //调查建议
 */
Ext.define('ComplaintDetailSuggestStore',{
	extend:'Ext.data.Store'
	,model:'ComplaintDetailSuggestModel'
});
/**
 * 工单详情-退回记录
 */
Ext.define('ComplaintDetailOpeRecordStore',{
	extend:'Ext.data.Store',
	model:'ComplaintDetailOpeRecordModel'
});

// 查看详情展开列表
Ext.define('ExpandDutyQueryResultStore',{
	extend:'Ext.data.Store',
	model:'ExpandDutyQueryResultModel',
	pageSize:20,
	autoLoad:false,
	proxy:{
		type:'ajax',
		url:'../duty/queryDutyDetailList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'dutyDetailList'
		}
	}
});

/**
 * 查询工单责任详情 和 相关 数据
 * @param {Object} param
 * @param {Function} successFn
 * @param {Function} failureFn
 */
CommonStore.prototype.searchDutyDetail = function(param,successFn,failureFn){
	var url = '../duty/searchDutyDetail.action';
	DutyUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**
 * 查询工单详情 和 相关 数据
 * @param {Object} param
 * @param {Function} successFn
 * @param {Function} failureFn
 */
CommonStore.prototype.searchCompalaintDetail = function(param,successFn,failureFn){
	var url = '../duty/searchCompalaintDetail.action';
	DutyUtil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * 加载工单责任详情数据
 * @param {Botton} btn 操作按钮对象
 * @param {String} gridId 目标ID
 * @param {Function} callBack 加载成功后 的回调
 * @return {Boolean}
 */
CommonStore.prototype.loadDataToDutyDetail = function(btn,gridId,callBack){
	var grid = Ext.getCmp(gridId);
	var records=grid.getSelectionModel().getSelection();
	//判断是否选中行
	if (records.length == 0) {
		MessageUtil.showMessage('未选中任何记录！');
		return false;
	}
	if (records.length != 1) {
		MessageUtil.showMessage('只能选择一条记录!');
		return false;
	}
	if(Ext.isEmpty(records[0].get("dutyId"))){
		MessageUtil.showMessage('所选择记录无效！');
		return false;
	}
	
	dutyID=records[0].get("dutyId");//设置Id
	var successFn = function(json){
		if(!Ext.isEmpty(btn)){btn.setDisabled(false);}
		//回调
		if(callBack){callBack(json,records[0]);}
		//工单责任详情
		var dutyDetailInfoPanel=Ext.getCmp("DutyComplaintDetailBasicInfoPanelId");
		if(!Ext.isEmpty(dutyDetailInfoPanel)){
			dutyDetailInfoPanel.getForm().reset();
			dutyDetailInfoPanel.getForm().loadRecord(new DutySearchDetailModel(json.dutyDetail));
		}
		
		// 处理经过
		var dutyFeedbackDealProcedureGridId = Ext.getCmp("DutyFeedbackDealProcedureGridId");
		if(!Ext.isEmpty(dutyFeedbackDealProcedureGridId)){
			dutyFeedbackDealProcedureGridId.store.removeAll();
			dutyFeedbackDealProcedureGridId.store.loadData(json.dutyDealProcess);
		}
		// 责任划分结果
		var dutyAllocationResultDeptGridId = Ext.getCmp("DutyAllocationResultDeptGridId");
		if(!Ext.isEmpty(dutyAllocationResultDeptGridId)){
			dutyAllocationResultDeptGridId.store.removeAll();
			dutyAllocationResultDeptGridId.store.loadData(json.dutyResult);
		}
		//通知对象
		var informUser = Ext.getCmp("informUser");
		if(!Ext.isEmpty(informUser)){
			informUser.setValue(json.userNames);
		}
		//责任反馈记录列表
		var dutyFeedbackRecordListGridId = Ext.getCmp("DutyFeedbackRecordListGridId");
		if(!Ext.isEmpty(dutyFeedbackRecordListGridId)){
			dutyFeedbackRecordListGridId.store.removeAll();
			dutyFeedbackRecordListGridId.store.loadData(json.dutyFeedbackList);
		}
	};
	var failureFn = function(json){
		if(!Ext.isEmpty(btn)){btn.setDisabled(false);}
		MessageUtil.showErrorMes(json.message);
	};
	var param={'dutyId':dutyID};
	if(!Ext.isEmpty(btn)){btn.setDisabled(true);}
	CommonStore.prototype.searchDutyDetail(param, successFn, failureFn);
};
/**
 * 加载工单详情数据
 * @param {Botton} btn 操作按钮对象
 * @param {String} gridId 目标ID
 * @param {Function} callBack 加载成功后 的回调
 * @return {Boolean}
 */
CommonStore.prototype.loadDataToComplaintDetail = function(btn,gridId,callBack,compId){
	var grid = Ext.getCmp(gridId);
	var records=grid.getSelectionModel().getSelection();
	var complaintId=records[0].get(Ext.isEmpty(compId)?"complaintId":compId);//设置Id
	var dutyID=records[0].get("dutyId");//设置Id
	if(Ext.isEmpty(dutyID)){
		dutyID=records[0].get("id");
	}
	var successFn = function(json){
		if(!Ext.isEmpty(btn)){btn.setDisabled(false);}
		//回调
		if(callBack){callBack(json,records[0]);}
		//工单责任详情
		var dutyDetailInfoPanel=Ext.getCmp("dutyDetailBasicInfoPanelId");
		if(!Ext.isEmpty(dutyDetailInfoPanel)){
			dutyDetailInfoPanel.getForm().reset();
			dutyDetailInfoPanel.getForm().loadRecord(new DutySearchDetailModel(json.dutyDetail));
		}
		
		// 调查意见
		var complaintDetailSuggestGridId = Ext.getCmp("complaintDetailSuggestGridId");
		if(!Ext.isEmpty(complaintDetailSuggestGridId)){
			complaintDetailSuggestGridId.store.removeAll();
			complaintDetailSuggestGridId.store.loadData(json.workOrderList);
		}
		//符合条件，设置为1级
		if(DpUtil.isEmpty(json.complaint) || DpUtil.isEmpty(json.complaint.parabasilevelid)
				||json.complaint.parabasilevelid==0){
			json.complaint.complevel = 'level_one';
		}
		//业务范围
		var complaintDetailBusinessPanel=Ext.getCmp("complaintDetailBusinessPanelId");
		if(!Ext.isEmpty(complaintDetailBusinessPanel)){
			complaintDetailBusinessPanel.getForm().reset();
			complaintDetailBusinessPanel.getForm().loadRecord(new ComplaintDetailBusinessModel(json.complaint));
		}
		if(json.complaint.reporttype=='UNUSUAL'){//异常
			complaintDetailBusinessPanel.getForm().findField('complainLink').hide();//业务环节
			complaintDetailBusinessPanel.getForm().findField('complevel').hide();//投诉级别
		}else if(json.complaint.reporttype=='COMPLAINT'){//投诉
			complaintDetailBusinessPanel.getForm().findField('complainLink').show();
			complaintDetailBusinessPanel.getForm().findField('complevel').show();
		}
		//业务类型
		//Ext.getCmp('basiclevelid').setValue(json.complaint['basiclevelid']);
		//投诉等级
		//Ext.getCmp('complevel').setValue(json.complaint['complevel']);
		// 处理结果列表
		var complaintDetailDealResultGridId = Ext.getCmp("complaintDetailDealResultGridId");
		if(!Ext.isEmpty(complaintDetailDealResultGridId)){
			complaintDetailDealResultGridId.store.removeAll();
			complaintDetailDealResultGridId.store.loadData(json.searchResultList);
		}
		// 操作记录列表
		var complaintDetailOpeRecordGridId = Ext.getCmp("complaintDetailOpeRecordGridId");
		if(!Ext.isEmpty(complaintDetailOpeRecordGridId)){
			complaintDetailOpeRecordGridId.store.removeAll();
			complaintDetailOpeRecordGridId.store.loadData(json.searchProtList);
		}
		//通知对象
		var informUser = Ext.getCmp("tongZhiDuiXaingId");
		if(!Ext.isEmpty(informUser)){
			informUser.setValue(json.bulletinStr);
		}
		//最终反馈
		Ext.getCmp('zuiZhongFanKuiId').setValue(json.complaint['feedback']);

	};
	var failureFn = function(json){
		if(!Ext.isEmpty(btn)){btn.setDisabled(false);}
		MessageUtil.showErrorMes(json.message);
	};
	var param={'complaintId':complaintId,'dutyId':dutyID};
	if(!Ext.isEmpty(btn)){btn.setDisabled(true);}
	CommonStore.prototype.searchCompalaintDetail(param, successFn, failureFn);
};
/**
 * 查看附件弹出框弹出
 */
var watchFeedbackAttatch = function(feedbackId){
	var dutyAttachmentListForm = Ext.getCmp("dutyFeedbackAttachmentListGridId");
	dutyAttachmentListForm.store.load({params:{'searchDutyCondition.id':feedbackId}});
	dutyAttachmentListWindow.show();
}

/**
 * 查看附件弹出框弹出
 */
var watchDeptName = function(feedbackId){
	    var param = {
	    	'feedbackId':feedbackId/*责任详情*/
		};
		var successFn = function(response){
			if(Ext.isEmpty(response.deptName)){
				response.deptName = '';
			}
			MessageUtil.showInfoMes(response.deptName);
		};
		var failureFn = function(response){
			MessageUtil.showMessage(i18n('i18n.duty.timeOut'));
		};
		DutySearchDetailStore.prototype.searchStatDeptName(param,successFn,failureFn);
}
/**
 * 奖惩类型
 * @type 
 */
ReworpusType = {
	list:[
		{code:'nothing',codeDesc:'无'},
		{code:'reward',codeDesc:'奖励'},
		{code:'punish',codeDesc:'负激励'}
	],
	getReworpusTypeName:function(code){
		if(Ext.isEmpty(code)){return '';}
		for(var i=0;i<this.list.length;i++){
			var temp = this.list[i];
			if(temp.code==code){return temp.codeDesc;}
		}
	}
};