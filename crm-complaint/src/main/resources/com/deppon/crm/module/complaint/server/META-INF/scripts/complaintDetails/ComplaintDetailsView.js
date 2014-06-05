/**
 * 查询详情弹出框 声明
 */
Ext.define('ComplaintDetailsPanel', {
	extend : 'BasicPanel',
	'complaint' : null // 客户工单信息
	,complaintBasicFormPanel : null       /* 显示基本信息 formPanel */
	,surveySuggestGrid : null             /* 调查建议 */
	,baseHierarchyPanel : null            /* 业务类型、业务范围、投诉级别 */
	,complaintFourPanel : null            // 第四块
	,processResultGrid : null             /* 处理结果 grid */
	,processRecordGrid : null ,            /* 处理记录记录 grid */
	
	layout : {
		align : 'stretch',
		type : 'vbox'
	},
	initComponent : function() {

		var me = this;
		// 创建一个基本信息Form
		this.complaintBasicFormPanel = Ext.create('ComplaintBasicFormPanel',{
			'complaint' : me.complaint
//			padding : '0 0 0 0'
		});
		
//		// 创建一个调查建议Grid
		this.surveySuggestGrid = Ext.create('SurveySuggestGrid',{
			'complaint' : me.complaint
		});
//
		 this.baseHierarchyPanel = Ext.create('BaseInfoLevelPanel',{
		 isEditable:false,'complaint':me.complaint,
		 padding:'0 0 0 5',
		 forceSelectionStatus:false
		 });
//
//		// 创建一个第四显示块
		this.complaintFourPanel = Ext.create('ComplaintFourPanel');
//
//		// 创建一个处理结果Grid
		this.processResultGrid = Ext.create('ProcessResultGrid',{
			'complaint' : me.complaint
		});
		
		{// 通知对象显示
			var searchParams = {
				complaintSearchCondition : {
					'fid' : me.complaint.fid
				}
			};
			// 执行成功
			var successFn = function(response) {
				var bulletinValue = "";
				var bulletinList = response.bulletinList;
				for ( var i = 0; i < bulletinList.length; i++) {
					bulletinValue += bulletinList[i].bulletinname + ',';
				}
				bulletinValue = bulletinValue.substring(0,
						bulletinValue.length - 1);
				Ext.getCmp('bulletinId').setValue(bulletinValue);
			}

			// 执行失败
			var failFn = function(response) {
				MessageUtil.showErrorMes(response.message);
			}
			DpUtil.requestJsonAjax('searchBulletinListByCompId.action',
					searchParams, successFn, failFn);
			// 最终反馈
			Ext.getCmp('feedback').setValue(me.complaint['feedback']);
		}

//		// this.processResultGrid = Ext.create('ProcessResultGrid',{
//		// 'complaint':me.complaint
//		// });
		 this.processRecordGrid = Ext.create('ProcessRecordGrid',{
		 'complaint':me.complaint
		 });
//		
		
		
		Ext.applyIf(me, {
			items : [ 
			          me.complaintBasicFormPanel ,
			          me.surveySuggestGrid,
					  me.baseHierarchyPanel, 
					  me.processResultGrid,
					  me.processRecordGrid,
					  me.complaintFourPanel 
					  ]
		});
		me.callParent(arguments);
	}
});


var viewport = null;/* 视图容器 */
Ext.onReady(function() {
	var win = window.parent.Ext.getCmp('complaintDetailsWindowId');
	if(win){
		processingMask.show();
		Ext.each(win.query('button[hidden=false]'),function(btn){
			btn.setDisabled(true);
		});
	}
	
	// 页面需要加载的数据字典数组
	var keys = [ 'COMPLAINT_FIELD' /* 客户工单查询条件 */
		, 'REPORT_TYPE' /* 上报类型 */
		, 'COMPLAINT_PROCESS_STATUS' /* 工单模块处理状态 */
		, 'RESOLVE_CASE' /* 解决情况 */
		, 'EXPECTED_TIME_LIMIT'// 期望时限
		, 'WORK_SINGLE_SOURCE'/* 工单来源 */
		, 'PRIORITY_RATING'/* 优先级别 */
		, 'COMPLAINT_LEVEL' /* 投诉级别 */
		, 'COMPLAINT_LINK' /* 业务环节 */
		, 'COMPLAINT_RECORD_TYPE'/* 工单模块反馈类型 */
		, 'SATISFACTION_DEGREE'/* 客户满意度 */
		, 'CALLER_TYPE'/* 来电人类型 */
		// 客户类型
		, 'CUSTOMER_TYPE'
		// 目标级别',目前级别,客户等级',会员等级
		, 'MEMBER_GRADE'
		 // 业务类型
	    ,'COMPLAINT_BUSINESS_MODEL'
	];
	// 初始数据字典
	initDataDictionary(keys);
	//当前弹出框
	
	// 执行成功
	var successFn = function(response) {
		if(Ext.isEmpty(response.complaint)){
			MessageUtil.showErrorMes('该工单不存在',function(){
				if(win){win.close();}
			});
			return;
		}
		//隐藏加载层，显示按钮可用
		processingMask.hide();
		Ext.each(win.query('button[hidden=false]'),function(btn){
			btn.setDisabled(false);
		});
		
		viewport = Ext.create('Ext.container.Viewport', {
			layout : 'fit',
			items : [ Ext.create('ComplaintDetailsPanel', {
				complaint : response.complaint
			}) ]
		});
		viewport.setAutoScroll(false);
		viewport.doLayout();
	};
 
	var compId = window.location.search.split("=")[1];
	if (Ext.isEmpty(compId)) {
		MessageUtil.showErrorMes('该工单不存在',function(){
			if(win){win.close();}
		});
		return;
	}
	// 执行失败
	DpUtil.requestJsonAjax('../complaint/searchComplaint.action', {
		fid : compId
	}, successFn, function(response) {
		MessageUtil.showErrorMes(response.message);
	});
});