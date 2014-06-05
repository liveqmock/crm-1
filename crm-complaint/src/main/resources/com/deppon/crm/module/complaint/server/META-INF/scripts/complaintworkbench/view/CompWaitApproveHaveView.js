
/** 
 * 待办工单提交审批弹出框 声明
 */
Ext.define('WaitSubmitApprovalWindow', {
    extend:'PopWindow',x:1
	,'complaint':null //客户工单信息
	,'parent':null   // 父级 弹出框
	,title:i18n('i18n.complaint.submit_approval_title'),width:800,height:450
	//加载参数，用于是否加载相关元素
	,baseInfoLevelPanel:null // 基础层级级联菜单-north-上北
	,processContainerPanel:null // 处理容器-center-中
	,processOtherPanel:null   // 投诉升级专用
    ,layout:'border',modal:true
    ,initComponent: function() {
        var me = this;
    	this.processContainerPanel = Ext.create('WaitProcessContainerPanel',{
			'region':'center'
			,complaint:me.complaint
		});
		var processResultStore = me.processContainerPanel.editableProcessResultPanel
		.editableProcessResultGrid.getStore();
		this.baseInfoLevelPanel = Ext.create('BaseInfoLevelPanel',{
    		'region':'north','processResultStore':processResultStore
    		,complaint:me.complaint
    	})
    	Ext.applyIf(me,{
    		items:[me.baseInfoLevelPanel,me.processContainerPanel]
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
    	var me = this; var params = {};var compTemp = {};
    	{//验证
			var baseHierarchyForm = me.baseInfoLevelPanel.getForm();
			if(!baseHierarchyForm.isValid()){return;}
			
			/* 上报类型为 投诉时 存在投诉级别 并验证非空 */
			if(me.complaint['reporttype']==i18n('i18n.complaint.fpririty_complaint')){
				compTemp['complevel'] = baseHierarchyForm.findField('complevel').getValue();
				compTemp['complainLink'] = baseHierarchyForm.findField('complainLink').getValue();
			}
			//业务项
			compTemp['parabasilevelid'] = baseHierarchyForm.findField('paraBasciLevel').getValue();
			compTemp['busItemName'] = baseHierarchyForm.findField('paraBasciLevel').getRawValue();
			//业务范围
			compTemp['basiclevelid'] = baseHierarchyForm.findField('basciLevel').getValue();
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
		
			
			var recommendation = Ext.getCmp('form_recommendation').getValue();
			if(DpUtil.isEmpty(recommendation)){
				MessageUtil.showMessage(i18n('i18n.comp.msg.print_survey_suggest')/*请输入调查建议*/);
				return;
			}
		    compTemp['recommendation']=recommendation;    	
	    }
		
    	{//处理结果
    		var editableProcessResultPanel = me.processContainerPanel.editableProcessResultPanel;
    		var processResultStore = editableProcessResultPanel.editableProcessResultGrid.getStore();
			if(processResultStore && processResultStore.getCount()==0){/*处理结果最少为一条数据*/
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
	    		resultList.push(record.data);
	    	});
	    	if(!check_result.flag){
	    		MessageUtil.showMessage(check_result.msg);return;
	    	}
	    	
	    	{//通知对象数据
	    		var bulletinList= new Array();
				var employeeStore = me.processContainerPanel.processOtherPanel
				.editableBulletinPanel.editableBulletinGrid.getStore();
				employeeStore.each(function(record){
					bulletinList.push(record.data);
		    	});
	    	}
	    	params['resultList'] = resultList;
    		params['bulletinList'] = bulletinList;
    		params['complaint'] = Ext.apply(me.complaint,compTemp);
    	}
    	
    	{//执行操作
    		processingMask.show();
	    	//执行成功
			var successFn = function(response){
				processingMask.hide();
				MessageUtil.showMessage(i18n('i18n.comp.msg.succeed.submit_approval'));
				me.close();/* 本层 window */			
				Ext.getCmp('ComplaintWorkbenchProcessWindow').close();
				Ext.getCmp('complaintGridId').store.load();
				
			};
			//执行失败
			var failFn = function(response){
				processingMask.hide();
				MessageUtil.showErrorMes(iresponse.message);
			};
			DpUtil.requestJsonAjax('complaint_submitApproval.action',params,successFn,failFn);
    	}
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
 * 处理容器 panel
 */
Ext.define('WaitProcessContainerPanel',{
	extend:'BasicPanel'
	,layout:'border'
	,editableProcessResultPanel:null //可编辑的处理结果 panel-center-中
	,processOtherPanel:null  //处理的其他 panel
	,complaint:null
	,initComponent:function(){
    	var me = this;
    	this.processOtherPanel = Ext.create('WaitProcessOtherPanel',{
    		region:'center',complaint:me.complaint
    	});
    	var bulletinStore = this.processOtherPanel.editableBulletinPanel.editableBulletinGrid.getStore();
    	this.editableProcessResultPanel = Ext.create('EditableProcessResultPanel',{
    		'region':'north',height:130,parent:me
    		,is_loadProcessResult:true
    		,is_loadALLResultStore:true
    		,'bulletinStore':bulletinStore
    		,complaint:me.complaint
    	});
    	Ext.applyIf(me,{
    		items:[
    			me.editableProcessResultPanel
    			,me.processOtherPanel
    		]
    	});
    	this.callParent(arguments);
    }
});


/**
 * 处理的其他 panel
 */
Ext.define('WaitProcessOtherPanel',{
	extend:'BasicPanel'
	,layout:'border'
    ,complaint:null
	,editableBulletinPanel:null  //通知对象
    ,initComponent:function(){
    	var me = this;
		me.editableBulletinPanel = Ext.create('EditableBulletinPanel',{
			'region':'north',height:130,'margin':'2 0 0 0',isAutoLoad:true
			,complaint:me.complaint
		});
    	this.items = this.getItems();
    	this.callParent(arguments);
    }
    ,getItems:function(){
    	var me = this;
    	return [
	        {
	        	xtype:'textareafield','region':'center','margin':'2 0 0 0'
	        	,fieldLabel:i18n('i18n.complaint.comp_recommendation')
	        	,id:'form_recommendation',labelWidth:60,width:400
	        }
	        ,me.editableBulletinPanel
    	];
    }
});