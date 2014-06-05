/** 
 * 查询详情弹出框 声明
 */
Ext.define('ComplaintDetailsWindow', {
    extend: 'PopWindow'
    ,y:5,x:1
    ,id:'complaintDetailsWindowId'
	,'complaint':null //客户工单信息
	,complaintBasicFormPanel:null/* 显示基本信息  formPanel */
	,surveySuggestGrid:null   /* 调查建议*/
	,baseInfoLevelPanel:null	/*业务类型、业务范围、投诉级别*/
	,complaintFourPanel:null   //第四块
	,processResultGrid:null/*处理结果 grid */
	,processRecordGrid:null/*处理记录记录 grid */
	,width:820,height:785
    ,layout: {align:'stretch',type:'vbox'},modal:true
	,title:i18n('i18n.complaint.comp_title_details')
    ,initComponent: function() {
    	
        var me = this;
      	this.complaintBasicFormPanel = Ext.create('ComplaintBasicFormPanel',{
			'complaint':me.complaint
		});
		this.surveySuggestGrid = new Ext.create('SurveySuggestGrid',{'complaint':me.complaint});
	
		this.baseInfoLevelPanel = Ext.create('BaseInfoLevelPanel',{
			isEditable:false,'complaint':me.complaint,padding:'0 0 0 5',forceSelectionStatus:false
		});
		this.complaintFourPanel = Ext.create('ComplaintFourPanel');
		
		{// 通知对象显示
			var searchParams = {
				complaintSearchCondition:{'fid':me.complaint.fid}
			};
			//执行成功
			var successFn = function(response){
				var bulletinValue = "";
				var bulletinList = response.bulletinList;
				for(var i=0;i<bulletinList.length;i++){
					bulletinValue +=  bulletinList[i].bulletinname + ',';
				}
				bulletinValue = bulletinValue.substring(0,bulletinValue.length-1);
				Ext.getCmp('bulletinId').setValue(bulletinValue);
			}
			
			//执行失败
			var failFn = function(response){
				MessageUtil.showErrorMes(response.message);
			}
			DpUtil.requestJsonAjax('searchBulletinListByCompId.action',searchParams,successFn,failFn);
			//最终反馈
			Ext.getCmp('feedback').setValue(me.complaint['feedback']);
		}
		
		this.processResultGrid = Ext.create('ProcessResultGrid',{
			'complaint':me.complaint
		});
		this.processRecordGrid = Ext.create('ProcessRecordGrid',{
			'complaint':me.complaint
		});
        Ext.applyIf(me,{
        	buttons:[
				{text:i18n('i18n.complaint.btn_print'),handler:function(){
					var url='/crm/complaint/complaintDetailIndex.action?'+
									'complaintId='+me.complaint.fid;
					var splashWin = window.top.open(url,'x','');

				}}
				,{text:i18n('i18n.complaintReport.btn_close'),handler:function(){me.destroy();}}
			]
			,items:[
				me.complaintBasicFormPanel,me.surveySuggestGrid,me.baseInfoLevelPanel
				,me.processResultGrid,me.processRecordGrid,me.complaintFourPanel
			]
        });
        me.callParent(arguments);
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
 * 第四显示块 声明
 */
Ext.define('ComplaintFourPanel',{
	extend:'BasicPanel'
	,layout: {align:'middle',type:'hbox'}
	,defaults:{readOnly:true}
	,items: [
        {xtype:'textareafield',fieldLabel:i18n('i18n.complaint.comp_bulletin'),id:'bulletinId',width:700,height:50,labelWidth:75}
        ,{xtype:'checkboxfield',id:'feedback',margin:'0 0 0 5',boxLabel:i18n('i18n.complaint.comp_feedback'),width:80}
    ]
});

/**
 * 隐藏最终反馈第四个panel
 */
Ext.define('ComplaintHiddenFourPanel',{
	extend:'BasicPanel'
	,layout: {align:'middle',type:'hbox'}
	,defaults:{readOnly:true}
	,items: [
        {xtype:'textareafield',fieldLabel:i18n('i18n.complaint.comp_bulletin'),width:785,labelWidth:75,height:50,border:true,id:'bulletinId'}
    ]
});

/* 弹出详情 */
function show_comp_detailsWin(opt){
	processingMask.show();
	Ext.create('ComplaintDetailsWindow',{
		'parent':opt.me,'complaint':opt.complaint
		,listeners:{
			show:function(){processingMask.hide();}
		}
	}).show();
}


/**
 * 客户工单之 调查建议列表
 */
Ext.define('SurveySuggestGrid',{
	extend:'PopupGridPanel'
	,height:120,title:i18n('i18n.complaint.comp_recommendation')
	,complaint:null  //工单数据
	,initComponent:function(){
		var me = this;
		me.store = Ext.create('SurveySuggestStore',{
			autoLoad:true,
			listeners:{
				beforeload:function(store, operation, eOpts){
					var searchParams = {'complaintId':me.complaint.fid};
					Ext.apply(operation,{params : searchParams});
				}
			}
		});
		me.columns = me.getColumns();
		this.callParent(arguments);
	}
	,getColumns:function(){
		var me = this;
		return [
			{xtype:'rownumberer',header:i18n('i18n.complaint.serial_number'),width:40},
			{header:i18n('i18n.comp.work_order.suggestion')/*内容*/,flex:1,dataIndex:'suggestion',renderer:function(val){
				return '<span data-qtip="'+val+'">'+val+'</span>';
			}}
			,{header:i18n('i18n.comp.work_order.operatorName')/*操作人*/,width:100,dataIndex:'operatorName'}
			,{
				header:i18n('i18n.comp.work_order.operatorTime')/*操作时间*/,width:130,dataIndex:'operatorTime'
				,renderer:function(value) {
					if(value != null){return Ext.Date.format(new Date(value),'Y-m-d H:i');
					}else{return null;}
				}
			}
		];
	}
});