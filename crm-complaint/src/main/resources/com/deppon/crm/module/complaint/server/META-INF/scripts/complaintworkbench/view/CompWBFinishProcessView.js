
/** 
 * 待办工单-完成处理弹出框 声明
 */
Ext.define('CompWBFinishProcessWindow', {
    extend:'PopWindow',x:1
    ,width:800,height:560
    ,old_width:null,old_height:null
	,'complaint':null //客户工单信息
	,title:i18n('i18n.complaint.finish_process_title')
	,finishProcessBtnPanel:null  // 处理按钮 panel
	,finishProcessContainerPanel:null // 处理容器
	,finishProcess_northPanel:null   // 顶层 border-north
	,action_url:'returnedFinishComplaintProcess.action'  //提交action
    ,layout:'border',modal:true
    ,initComponent: function() {
        var me = this;
		me.finishProcessContainerPanel = Ext.create('CompWBFinishProcessContainerPanel',{
			complaint:me.complaint,'region':'center','margin':'2 0 0 0',parent:me
		});
		me.finishProcess_northPanel = Ext.create('CompWBFinishProcess_northPanel',{region:'north','complaint':me.complaint,parent:me});
		Ext.applyIf(me,{
    		items:[me.finishProcess_northPanel,me.finishProcessContainerPanel]
    		,buttons:me.getButtons()
    	});
    	me.old_width = me.width;me.old_height=me.height;
        me.callParent(arguments);
    }
    ,getButtons:function(){
    	var me = this;
    	return [
	    	{text:i18n('i18n.complaint.submit'),scope:me,handler:me.finishProcessEvent}
	    	,{text:i18n('i18n.complaintReport.btn_close'),listeners:{scope:me,click:function(){me.close();}}}
    	];
    }
    ,finishProcessEvent:function(){
    	var me = this;var params = {};var compTemp = {};
    	function operate(finalFeedback){
    		processingMask.show();
	    	compTemp['feedback'] = finalFeedback?1:0;
	    	params['complaint'] = Ext.apply(me.complaint,compTemp);
			//执行成功
			var successFn = function(response){
				processingMask.hide();
				MessageUtil.showInfoMes(i18n('i18n.comp.msg.succeed.finish_process'));
				me.myClose();
			};
			
			//执行失败
			var failFn = function(response){
				processingMask.hide();
				MessageUtil.showErrorMes(response.message);
			};
			DpUtil.requestJsonAjax(me.action_url,params,successFn,failFn);
    	}
    	
    	//调查建议 or 反馈结果 surveySuggest,feedbackResult
    	var northForm = me.finishProcess_northPanel.getForm();
    	//最终反馈
    	var finalFeedback= Ext.getCmp('finalFeedback_checkbox').getValue();
    	if(finalFeedback){//是最终反馈
			if(DpUtil.isEmpty(Ext.getCmp('feedbackResult_textareafield').getValue())){
				northForm.isValid();return;
			}
			//反馈结果
			compTemp['feedbackrecode'] = northForm.findField('feedbackResult').getValue();
			operate(finalFeedback);//执行操作
			return;
		}
		/*******************不是最终反馈 begin ******************************************/
		//调查建议
		if(DpUtil.isEmpty(Ext.getCmp('surveySuggest_textareafield').getValue())){
				northForm.isValid();return;
		}
		//调查建议
		compTemp['recommendation'] = northForm.findField('surveySuggest').getValue();
		//级联下拉列表 formPanel
    	var baseHierarchyForm = me.finishProcessContainerPanel.baseInfoLevelPanel.getForm();
    	if(!baseHierarchyForm.isValid()){return;}
    	
		/* 上报类型为 投诉时 存在投诉级别 和业务环节并验证非空 */
		if(me.complaint['reporttype']==i18n('i18n.complaint.fpririty_complaint')){
			compTemp['complevel'] = baseHierarchyForm.findField('complevel').getValue();
			compTemp['complainLink'] = baseHierarchyForm.findField('complainLink').getValue();
		}
		//业务项
		if(Ext.isEmpty(baseHierarchyForm.findField('paraBasciLevel').getValue())){
			compTemp['parabasilevelid'] = 0;
		}
		else{
			compTemp['parabasilevelid'] = baseHierarchyForm.findField('paraBasciLevel').getValue();
		}
		
		
		compTemp['busItemName'] = baseHierarchyForm.findField('paraBasciLevel').getRawValue();
		//业务范围
		if(Ext.isEmpty(baseHierarchyForm.findField('basciLevel').getValue())){
			compTemp['busItemName'] = 0;
		}
		else{
			compTemp['basiclevelid'] = baseHierarchyForm.findField('basciLevel').getValue();
		}
		compTemp['busScopeName'] = baseHierarchyForm.findField('basciLevel').getRawValue();
		//业务类型id
		compTemp['busTypeId'] = baseHierarchyForm.findField('basicBusinessType').getValue();
		//业务类型名称
		compTemp['busTypeName'] = baseHierarchyForm.findField('basicBusinessType').getRawValue();
		//业务场景id
		compTemp['busSceneId'] = baseHierarchyForm.findField('busScene').getValue();
		//业务场景名称
		compTemp['busSceneName'] = baseHierarchyForm.findField('busScene').getRawValue();
		//场景原因id
		compTemp['sceneRessonId'] = baseHierarchyForm.findField('sceneResson').getValue();
		//场景原因名称
		compTemp['sceneRessonName'] = baseHierarchyForm.findField('sceneResson').getRawValue();
		
    	{//处理结果
    		var editableProcessResultPanel = me.finishProcessContainerPanel.editableProcessResultPanel;
			var processResultStore = editableProcessResultPanel.editableProcessResultGrid.getStore();
			
			var is_allReturn = (function(){/*是否全部退回*/
				var prostatus = me.complaint['prostatus'];
				return (
					prostatus===i18n('i18n.complaint.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_PROCESS_ALL')
					|| prostatus===i18n('i18n.complaint.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_APPROVAL_ALL')
					|| prostatus===i18n('i18n.complaint.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE_ALL')
				);
			})();
			
			/*此工单是全部退回，则处理结果必须填写*/
			if(is_allReturn && processResultStore.getCount()<=0){
				MessageUtil.showMessage(i18n('i18n.complaint.msg_processResult_data'));
				return;
			}
			
			var resultList= new Array();
			var check_result = {'flag':true};
	    	processResultStore.each(function(record){
	    		var result = record.data;
	    		if(DpUtil.isEmpty(result.dealmatters)){
	    			check_result = {
	    				'flag':false,'msg':editableProcessResultPanel.msg_dealmatters_isNull
	    			};return false;
	    		}
	    		if(DpUtil.isEmpty(result.ftaskDeptName)){
	    			check_result = {
	    				'flag':false,'msg':editableProcessResultPanel.msg_ftaskDeptName_isNull
	    			};return false;
	    		}
	    		if(result.processtimelimit<=0 || result.feedtimelimit<=0){
	    			check_result = {
	    				'flag':false,'msg':editableProcessResultPanel.msg_processAndFeed_no_0
	    			};return false;
	    		}
	    		if((result.processtimelimit*60)<=result.feedtimelimit){
	    			check_result = {
	    				'flag':false,'msg':editableProcessResultPanel.msg_no_process_big_feedtime
	    			};return false;
	    		}
//	    		if(Ext.isEmpty(record.data.personUserId)){//如果初始化为空的时候把taskpartmentid赋到这个字段上
//	    			record.set("personUserId",record.data.taskpartmentid);
//	    		}
	    		resultList.push(record.data);
	    	});
	    	if(!check_result.flag){
	    		MessageUtil.showMessage(check_result.msg);return;
	    	}
    	}
    	
    	var bulletinList= new Array();//通知对象数据
		var employeeStore = me.finishProcessContainerPanel.editableBulletinPanel.editableBulletinGrid.getStore();
		employeeStore.each(function(record){
			bulletinList.push(record.data);
    	});
    	params['resultList'] = resultList;
    	params['bulletinList'] = bulletinList;
    	/*******************不是最终反馈 end ******************************************/
    	operate(finalFeedback);//执行操作
    }
});

/**
 * 处理容器 panel
 */
Ext.define('CompWBFinishProcessContainerPanel',{
	extend:'BasicPanel'
	,'complaint':null //客户工单信息
	,parent:null // 父 级
	,baseInfoLevelPanel:null // 基础层级级联菜单
	,editableProcessResultPanel:null //可编辑的处理结果 panel
	,finishProcess_fivePanel:null  //完成处理 第五 panel之(最终反馈、解决情况)
	,processRecordPanel:null   // 处理记录
	,editableBulletinPanel:null   // 通知对象
	,layout: {type:'vbox',align:'stretch'}
	,initComponent:function(){
    	var me = this;
    	//第一
    	this.editableBulletinPanel = Ext.create('EditableBulletinPanel',{
    		height:150,'margin':'2 0 0 0',complaint:me.complaint,isAutoLoad:true
    	});
    	//第二
    	var bulletinStore= me.editableBulletinPanel.editableBulletinGrid.getStore();
    	//可编辑处理结果 初始化
    	this.editableProcessResultPanel = Ext.create('EditableProcessResultPanel',{
    		height:130,parent:me,'margin':'2 0 0 0',complaint:me.complaint
    		,'bulletinStore':bulletinStore,is_loadProcessResult:true
    	});
    	//第三
    	//基础层级级联查询 初始化
    	var processResultStore = me.editableProcessResultPanel
		.editableProcessResultGrid.getStore();
		this.baseInfoLevelPanel = Ext.create('BaseInfoLevelPanel',{
			'complaint':me.complaint,parent:me
    		,'region':'north','processResultStore':processResultStore
    	});
    	
    	//完成处理 第五 panel之(最终反馈、解决情况) 初始化
    	this.finishProcess_fivePanel = Ext.create('FinishProcess_fivePanel',{
    		//处理最终反馈事件
    		'checkbox_changeEvent':function(field,checked){
    			var meWindow = me.parent;
		    	var checked_true=function(){
		    		me.editableProcessResultPanel.hide();
		    		me.baseInfoLevelPanel.hide();
		    		me.editableBulletinPanel.hide();
		    		Ext.getCmp('surveySuggest_textareafield').hide();
		    		Ext.getCmp('feedbackResult_textareafield').show();
		    		meWindow.setWidth(390);meWindow.setHeight(200);
		    		
		    	};
		    	var checked_false=function(){
		    		me.editableProcessResultPanel.show();
		    		me.baseInfoLevelPanel.show();
		    		me.editableBulletinPanel.show();
		    		Ext.getCmp('surveySuggest_textareafield').show();
		    		Ext.getCmp('feedbackResult_textareafield').hide();
		    		meWindow.setWidth(meWindow.old_width);meWindow.setHeight(meWindow.old_height);
		    	}
		    	if(checked){checked_true();
		    	}else{checked_false();}
		    }
    	});
    	
    	Ext.applyIf(me,{
    		items:[
    			me.baseInfoLevelPanel
    			,me.editableProcessResultPanel
    			,me.editableBulletinPanel
    			,me.finishProcess_fivePanel
    		]
    	});
    	this.callParent(arguments);
    }
    /* 添加滚动条 修改 begin */
	,'closeAction':'destroy'
	,listeners:{
		destroy:function(){
			if(viewport && viewport!=null){
				document.body.scrollLeft=0;
				document.body.scrollTop=0;
				document.getElementsByTagName("html")[0].style.overflowY="hidden";
				document.getElementsByTagName("html")[0].style.overflowX="hidden";
				viewport.doLayout();
			}
		},
		show:function(){
			if(viewport && viewport!=null){
				document.getElementsByTagName("html")[0].style.overflowY="auto";
				document.getElementsByTagName("html")[0].style.overflowX="hidden";
				viewport.doLayout();
			}
		}
	}
	/* 添加滚动条 修改 end */
});

/**
 * 最终反馈 panel
 */
Ext.define('CompWBFinishProcess_fivePanel',{
	extend:'BasicPanel'
	,complaint:null // 工单数据
	,parent:null,layout: {align:'middle',type:'hbox',pack:'start'}
    ,initComponent:function(){
    	var me = this;
    	Ext.applyIf(me,{
    		items:[
    			{
	    			xtype:'checkboxfield',id:'finalFeedback_checkbox'
	    			,margin:'0 20 0 0',boxLabel:i18n('i18n.complaint.comp_feedback'),width:100
	    			,listeners:{scope:me,change:me.checkbox_changeEvent}
    			}
    		]
    	});
    	this.callParent(arguments);
    	if(!DpUtil.isEmpty(me.complaint)){
			Ext.getCmp('finalFeedback_checkbox').setValue(me.complaint['feedback']);
		}
    }
});

/**
 * 调查建议-反馈结果 panel( border-north )
 */
Ext.define('CompWBFinishProcess_northPanel',{
	extend:'NoTitleFormPanel'
	,complaint:null // 工单数据
	,layout: {align:'middle',pack:'start',type:'hbox'}
	,defaultType:'textareafield'
	,defaults:{width:350,labelWidth:70,height:60}
    ,initComponent:function(){
    	var me = this;
    	Ext.applyIf(me,{
    		items:[
	    		{
	    			fieldLabel:i18n('i18n.complaint.comp_recommendation'),width:700
	    			,allowBlank:false
	    			,blankText:i18n('i18n.comp.msg.print_survey_suggest')
	    			,name:'surveySuggest',id:'surveySuggest_textareafield'
	    		}
		        ,{
		        	fieldLabel:i18n('i18n.complaint.comp_feedbackResult'),hidden:true
		        	,allowBlank:false
					,blankText:i18n('i18n.comp.msg.select_feedback_result')
		        	,name:'feedbackResult',id:'feedbackResult_textareafield'
		        }
		    ]
    	});
    	this.callParent(arguments);
    	Ext.getCmp('feedbackResult_textareafield').setValue(me.complaint['feedbackrecode']);
    }
});