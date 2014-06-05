/** 
 * 投诉升级弹出框 声明
 */
Ext.define('ComplaintWorkbenchUpgradeWindow',{
    extend:'PopWindow'
	,'complaint':null //客户工单信息
	,'parent':null   // 父级 弹出框
	,title:i18n('i18n.complaint.btn.complaint_upgrade'),width:750
	,form_recommendation:null // 调查建议对象
    ,layout:'fit',modal:true
    ,initComponent: function() {
        var me = this;
    	this.form_recommendation = Ext.create('Ext.form.field.TextArea',{
    		fieldLabel:i18n('i18n.complaint.comp_recommendation'),labelWidth:60,width:400
    	});
    	Ext.applyIf(me,{
    		items:[me.form_recommendation]
    		//,buttons:me.getButtons()
    	});
    	this.fbar=this.getButtons();
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
    	processingMask.show();
    	var me = this;
		var recommendation = me.form_recommendation.getValue();
    	if(DpUtil.isEmpty(recommendation)){
    		MessageUtil.showMessage(i18n('i18n.complaint.validate.solvecontent'));
    		processingMask.hide();
    		return;
		}
    	me.complaint['recommendation']=recommendation;
    	var params = {
			complaint:me.complaint
		}
		
		//执行成功
		var successFn = function(response){
    		
    		MessageUtil.showMessage(i18n('i18n.comp.msg.succeed.comp_upgrade'));
    		me.close();/* 本层 window */
			Ext.getCmp('ComplaintWorkbenchProcessWindow').close();
			processingMask.hide();
			Ext.getCmp('complaintGridId').store.loadPage(1);
		}
		
		//执行失败
		var failFn = function(response){		
			MessageUtil.showErrorMes(response.message);
			processingMask.hide();
		}    	
		
		DpUtil.requestJsonAjax('complaint_upgrade.action',params,successFn,failFn);
    }
});



/**
 * 投诉升级的处理结果&调查建议panel
 */
Ext.define('UpgradeContainerPanel',{
	extend:'Ext.panel.Panel'
	,layout:'border'
	//,is_editableBulletin:null    //是否加载可编辑的通知对象
	,editableProcessResultPanel:null //可编辑的处理结果 panel-center-中
	,processOtherPanel:null  //处理的其他 panel
	,complaint:null
	,initComponent:function(){
    	var me = this;
    	this.processOtherPanel = Ext.create('ProcessOtherPanel',{
    		region:'center',complaint:me.complaint
    	});
    	this.editableProcessResultPanel = Ext.create('EditableProcessResultPanel',{
    		'region':'north',height:130,parent:me,'is_loadProcessResult':true
    		,'bulletinStore':me.processOtherPanel.editableBulletinPanel.editableBulletinGrid.getStore()
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
 * 投诉升级的操作按钮声明
 */
Ext.define('ComplaintWorkbenchUpgradeBtnPanel',{
	extend:'Ext.panel.Panel'
	,parent:null  //父容器
	,submitBtnId:null  // 提交按钮Id
	,layout: {align:'middle',pack:'end',type:'hbox'}
	,height:40
    ,initComponent:function(){
    	var me = this;
    	Ext.applyIf(me,{
    		items:[
	    		{xtype:'button',margin:'0 5',text:i18n('i18n.complaint.submit'),id:me.submitBtnId,scope:me,handler:me.submitApproval}
		        ,{
		        	xtype:'button',margin:'0 5',text:i18n('i18n.complaintReport.btn_close'),enableKeyEvents:true
		        	,listeners:{scope:me,click:function(){this.parent.close();}}
		        }
		    ]
    	});
    	this.callParent(arguments);
    }
	,submitApproval:function(){
		
		//最终提交的map
		var parameterMap = {};
		var me=this;
    	var resultList= new Array();//处理结果
    	
    	//通知对象
		var resultStore=me.parent.processContainerPanel.editableProcessResultPanel.editableProcessResultGrid.getStore();
		

		resultStore.each(function(record){
			resultList.push(record.data);
    	});		
		me.parent.processContainerPanel.complaint.resultList=null;
		parameterMap['resultList'] = Ext.isEmpty(resultList)?createdList:Ext.encode(resultList);	
    	var complaint = me.parent.processContainerPanel.complaint;//Ext.encode(me.parent.processContainerPanel.complaint); 
    		
    		//Ext.isEmpty(me.complaint)?me.complaint:Ext.encode(me.complaint);
    	
    	
		//执行成功
		var successFn = function(response){
			MessageUtil.showMessage(i18n('i18n.comp.msg.succeed.comp_upgrade'));
			Ext.getCmp('complaintWorkbenchUpgradeWindow').close();
			Ext.getCmp('ComplaintWorkbenchProcessWindow').close();
			Ext.getCmp('complaintGridId').store.loadPage(1);
		}
		
		//执行失败
		var failFn = function(response){
			MessageUtil.showMessage(response.message);
		}    	
    	
    	
    	//提交
    	DpUtil.requestJsonAjax('complaint_upgrade.action',{'parameterMap':parameterMap,'complaint':complaint},successFn,failFn);
	}
	
});
