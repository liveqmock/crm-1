/**
 *投诉工单的退回原因model 
 */
Ext.define('FeedbackReasonModel',{
	extend:'Ext.data.Model',
	fields:[
	     {name:'fid',type:'number'}/*编号*/
	     ,{name:'complaintid',type:'number'}/*关联的投诉单号*/
	     ,{name:'wailbillnunber',type:'string'}/*凭单号*/
	     ,{name:'wailbillcontent',type:'string'}/*解决情况（内容）*/
	     ,{name:'recordman',type:'string'}/*反馈人*/
	     ,{name:'recordpartmentname',type:'string'}/*组织（部门）*/
	     ,{name:'recordtime',type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null}/*时间*/  
	     ,{name:'recordtype',type:'string'}/*类型*/
	   ]
});

/**
 *  投诉工单的退回原因的store
 */
Ext.define('FeedbackReasonStore',{
	extend:'Ext.data.Store'
	,model:'FeedbackReasonModel'
	,autoLoad:true
	,proxy:{
		type:'ajax'
		,url:'searchFeedbackReasonByComplaintId.action'
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'feedbackList'
		}
	}
});

/**
 * 投诉工单的处理结果store,沿用ComplaintBasicData.js中的ProcessResultModel
 */
Ext.define('ComplaintProcessResultStore',{
	extend:'Ext.data.Store'
	,model:'ProcessResultModel'
	,autoLoad:true
	,proxy:{
		type:'ajax'
		,url:'searchProcessResultListByCompId.action'
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'searchResultList'
		}
	}
});


/**
 * 处理结果相关组 声明
 */
Ext.define('ComplaintProcessResultGrid',{
	extend:'SearchGridPanel'
	,id : 'processResultGridId'
	,store:null
	,complaint:null
	,width:870
	,initComponent:function(){
		var me = this;
		me.store=Ext.create('ComplaintProcessResultStore');		
		
		me.initProcessResultStore();
		
		me.columns = me.getColumns();

		this.callParent();
	}

	
	//查找传参并且查出处理结果
	,initProcessResultStore:function(){
		var me = this;
    	this.store.on('beforeload',function(th,operation){
    		var complaintFid=me.complaint.fid;
    		var param = {'complaintSearchCondition.fid':complaintFid};
    		Ext.apply(operation,{params : param});
    	});
	}
	
	,getColumns:function(){
		var me = this;
		return [

			{header:i18n('i18n.complaint.comp_id'),hidden:true,dataIndex:'fid'}
			,{header:i18n('i18n.complaint.process_result.dealmatters'),align:'center',width:70,dataIndex:'dealmatters'}
			,{header:i18n('i18n.complaint.process_result.task_department'),align:'center',width:70,dataIndex:'taskpartmentid'}			
			,{header:i18n('i18n.complaint.process_result.task_attribute'),align:'center',width:80,dataIndex:'taskproperties'}
			,{header:i18n('i18n.complaint.process_result.feedtimeLimit'),align:'center',width:80,dataIndex:'feedtimelimit'}
			,{header:i18n('i18n.complaint.process_result.processtimeLimit'),align:'center',width:80,dataIndex:'processtimelimit'}
		        	        
		];
	}

});


/**
 * 处理结果相关组 panel声明
 */

Ext.define('ProcessResultGridPanel',{
	extend:'BasicVboxPanel'
	,items : null
	,complaint:null
	,layout:'border' 
	//,margins:'10 0 0 0'
	/**
	,layout: {
        type: 'vbox',
        pack: 'start',  
        align: 'stretchmax'  
	 }	
	 **/
	,processResultGrid:null  //处理结果列表
	,baseInfoLevelPanel:null //业务范围、业务类型和投诉级别组成的panel
	,suggestionPanel:null  //suggestion panel
	,initComponent : function() {
		var me = this;

		this.baseInfoLevelPanel = Ext.create('BaseInfoLevelPanel',{
								isEditable:false,'complaint':me.complaint
								,region:'north',forceSelectionStatus:false
							});		
		
		this.processResultGrid = Ext.create('ProcessResultPanel',{
			'complaint':me.complaint,region:'center'
		});

		
		this.suggestionPanel = Ext.create('SuggestionPanel',{
			'complaint':me.complaint ,region:'south'
			,padding:'5 0'
		});
		
		this.items = this.getItems();
		this.callParent(arguments);
	}
	,getItems : function() {
		var me = this;
		return [me.baseInfoLevelPanel,me.processResultGrid,me.suggestionPanel];

	}
});


/**
 * 基础层级模块（只读）
 */

Ext.define('BaseHierarchyPanelRO',{
	extend:'TitleFormPanel',border:true
	,complaint:null
    ,initComponent : function() {
		var me = this;

		this.items = this.getItems();
		this.callParent(arguments);
		
		Ext.getCmp('form_paraBasciLevel').setValue(me.complaint.parabasilevelid);
		Ext.getCmp('form_basciLevel').setValue(me.complaint.basiclevelid);
		Ext.getCmp('form_complevel').setValue(me.complaint.complevel);
		
	}
	,getItems:function(){
		var me = this;
		return [{ 
				xtype:'basicfiledset',
				title:'',

				layout:{
					type:'table',
					columns:3
				},
				defaultType:'textfield',
				items:[
    			{
		        	fieldLabel:i18n('i18n.BaseHierarchyView.bascilevelname'),id:'form_paraBasciLevel',xtype:'textfield'
		        }
		        ,{
		        	fieldLabel:i18n('i18n.BaseHierarchyView.basciType'),id:'form_basciLevel',xtype:'textfield'
		        }
		        ,{
		        	fieldLabel:i18n('i18n.BaseHierarchyView.complevel'),id:'form_complevel',xtype:'textfield'
		        }]
				
			}];
	}

});

/**
 * 通知对象、调查建议panel
 */
Ext.define('SuggestionPanel',{
	extend:'BasicPanel'
	,complaint:null
	,initComponent : function() {
		var me=this;
		this.items = this.getItems();
		this.callParent(arguments);

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
				Ext.getCmp('notifyObject').setValue(bulletinValue);
			}
			
			//执行失败
			var failFn = function(response){
				MessageUtil.showErrorMes(response.message);
			}
			
			DpUtil.requestJsonAjax('searchBulletinListByCompId.action',searchParams,successFn,failFn);
		}			
		Ext.getCmp('suggestion').setValue(me.complaint.recommendation);
	}
	,getItems : function() {
		var me = this;
		return [{
				layout : {type : 'table',columns : 1},
				border:false,
				items : [
		               {
		            	   fieldLabel:i18n('i18n.complaint.comp_bulletin'),id:'notifyObject',xtype:'textfield',readOnly:true,labelWidth:70,width:780
		               }
		               ,{
		            	   fieldLabel:i18n('i18n.complaint.comp_recommendation'),id:'suggestion',xtype:'textfield',readOnly:true,labelWidth:70,width:780,padding:'5 0 0 0'	
		               }]
			    }];
	}
});


/**
 * 退回原因相关组Grid声明
 */
Ext.define('WithdrawReasonGrid',{
	extend:'PopupGridPanel'
	,store:null
	,complaint:null
	,initComponent:function(){
		var me = this;
		me.store=Ext.create('FeedbackReasonStore');		
		
		me.initFeedbackReasonSearchStore();
		
		me.columns = me.getColumns();
		this.callParent();
	}
	
	//查找传参并且查出feedbackreason
	,initFeedbackReasonSearchStore:function(){
		var me = this;
    	this.store.on('beforeload',function(th,operation){
    		var complaintFid=me.complaint.fid;
    		var param = {'complaintId':complaintFid};
    		Ext.apply(operation,{params : param});
    	});
	}
	
	,getColumns:function(){
		var me = this;
		return [

			{header:i18n('i18n.complaint.comp_id'),hidden:true,dataIndex:'fid'}
			,{header:i18n('i18n.complaint.comp_bill'),align:'center',width:150,dataIndex:'wailbillnunber'}
			,{
				header:i18n('i18n.complaint.comp_reportType'),align:'center',width:100,dataIndex:'recordtype'
				,renderer:function(value) {
					return rendererDictionary(value,DataDictionary.COMPLAINT_RECORD_TYPE);
				}
			}			
			,{header:i18n('i18n.complaint.comp_dealstatus'),align:'center',width:150,dataIndex:'wailbillcontent'}
			,{header:i18n('i18n.complaint.process_record.recordman'),align:'center',width:80,dataIndex:'recordman'}
			,{header:i18n('i18n.complaint.process_record.recordpartmentname'),align:'center',width:150,dataIndex:'recordpartmentname'}
			,{header:i18n('i18n.complaint.process_record.recordtime'),align:'center',width:150,dataIndex:'recordtime'
				,renderer:function(value) {
					if(value != null){return Ext.Date.format(new Date(value),'Y-m-d H:i');
					}else{return null;}
				}
			}

		        	        
		];
	}

});


/**
 * 退回上报人原因Grid声明
 */
Ext.define('ReturnToSubmittorReasonGrid',{
	extend:'PopupGridPanel'
	,store:null
	,complaint:null
	,initComponent:function(){
		var me = this;
		me.store=Ext.create('FeedbackReasonStore');		
		
		me.initFeedbackReasonSearchStore();
		
		me.columns = me.getColumns();
		this.callParent();
	}
	
	//查找传参并且查出feedbackreason
	,initFeedbackReasonSearchStore:function(){
		var me = this;
    	this.store.on('beforeload',function(th,operation){
    		var complaintFid=me.complaint.fid;
    		var param = {'complaintId':complaintFid};
    		Ext.apply(operation,{params : param});
    	});
	}
	
	,getColumns:function(){
		var me = this;
		return [

			{header:i18n('i18n.complaint.comp_id'),hidden:true,dataIndex:'fid'}
			,{header:i18n('i18n.complaint.comp_bill'),align:'center',width:80,dataIndex:'wailbillnunber'}
			,{
				header:i18n('i18n.complaint.comp_reportType'),align:'center',width:80,dataIndex:'recordtype'
				,renderer:function(value) {
					return rendererDictionary(value,DataDictionary.COMPLAINT_RECORD_TYPE);
				}
			}			
			,{header:i18n('i18n.complaint.comp_dealstatus'),align:'center',width:80,dataIndex:'wailbillcontent'}
			,{header:i18n('i18n.complaint.process_record.recordman'),align:'center',width:80,dataIndex:'recordman'}
			,{header:i18n('i18n.complaint.process_record.recordpartmentname'),align:'center',width:80,dataIndex:'recordpartmentname'}
			,{header:i18n('i18n.complaint.process_record.recordtime'),align:'center',flex:1,dataIndex:'recordtime'
				,renderer:function(value) {
					if(value != null){return Ext.Date.format(new Date(value),'Y-m-d H:i');
					}else{return null;}
				}
			}

		        	        
		];
	}

});


/**
 * 处理结果相关组 panel声明
 */

Ext.define('WithdrawReasonGridPanel',{
	extend:'BasicVboxPanel'
	,items : null
	,complaint:null
	//,height:120
	,withdrawReasonGrid:null //处理结果列表
	,initComponent : function() {
		var me = this;
		this.withdrawReasonGrid = Ext.create('WithdrawReasonGrid',{
			'complaint':me.complaint
		});
		this.items = this.getItems();
		this.callParent(arguments);
	}
	,getItems : function() {
		var me = this;
		return [
		{
			xtype:'toppanel',   
			items:[{
				xtype:'titlepanel',flex:1,
				items:[{xtype:'displayfield',value:i18n('i18n.complaint.withdraw.reason')}]
			}]
		}
		,{
			xtype:'basicpanel',flex:1
			,items:[me.withdrawReasonGrid]
		}
	
	  ];
	}
});

/**
 *延时申请审批的model 
 */
Ext.define('PostponeRequestModel',{
	extend:'Ext.data.Model',
	fields:[
	      {name:'fid',type:'number'}/*编号*/
	     ,{name:'dealman',type:'string'}/*提交人*/
	     ,{name:'appdelay',type:'number'}/*申请延时*/
	     ,{name:'ftaskDeptName',type:'string'}/*任务部门*/
	     ,{name:'postponeReason',type:'string'}/*延时原因*/

	   ]
});


/**
 *  延时申请审批的store
 */
Ext.define('PostponeRequestStore',{
	extend:'Ext.data.Store'
	,model:'PostponeRequestModel'
	,autoLoad:true
	,proxy:{
		type:'ajax'
		,url:'searchPostponeResult.action'
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'postponeResultList'
		}
	}
});


/**
 * 延时申请审批Grid声明
 */
Ext.define('PostponeRequestGrid',{
	extend:'PopupGridPanel'
	//,id : 'withDrawGridId'
	,store:null
	,complaint:null
	,initComponent:function(){
		var me = this;
		me.store=Ext.create('PostponeRequestStore');		
		
		me.initPostponeRequestStore();
		
		me.columns = me.getColumns();
		//this.loadShowControl();//动态加载相关控件
		this.callParent();
	}
	,loadShowControl:function(){
		var me = this;
		if(me.loadCondition && me.loadCondition.showPager){
			me.dockedItems = me.getMyDockedItems();
			
		}
		if(me.loadCondition && me.loadCondition.showCheckbox){
			me.selModel = new Ext.selection.CheckboxModel({mode:'single'});
		}
	}
	
	//查找传参并且查出feedbackreason
	,initPostponeRequestStore:function(){
		var me = this;
    	this.store.on('beforeload',function(th,operation){
    		var complaintFid=me.complaint.fid;
    		var param = {'resultSearchCondition.complainid':complaintFid};
    		Ext.apply(operation,{params : param});
    	});
	}
	
	,getColumns:function(){
		var me = this;
		return [
			{header:i18n('i18n.complaint.comp_id'),hidden:true,dataIndex:'fid'}
			,{header:i18n('i18n.complaint.submittor'),align:'center',width:100,dataIndex:'dealman'}
			,{header:i18n('i18n.complaintTask.btnApplyDelay'),align:'center',width:100,dataIndex:'appdelay'
				,renderer:function(value){
					return rendererDictionary(value,DataDictionary.DELAY_APPLY);
				}
			}			
			,{header:i18n('i18n.complaint.process_result.task_department'),align:'center',width:120,dataIndex:'ftaskDeptName'}
			,{header:i18n('i18n.complaint.postpone.reason'),align:'center',flex:1,dataIndex:'postponeReason'}
		];
	}

});

/**
 * 延时申请审批Panel声明
 */
Ext.define('PostponeRequestGridPanel',{
	extend:'BasicVboxPanel'
	,selModel : null //选中的selectModel
	,selType:'rowmodel'   //行模型选择		
	,items : null
	,complaint:null
	,postponeRequestGrid:null //延时申请列表
	,width:400,height:350
	,layout: {
        type: 'vbox',
        pack: 'start',  
        align: 'stretchmax'  
	}
	,loadCondition:{
		showPager:true // 显示分页
	    ,showCheckbox:true // 显示checkbox
	}  
	,initComponent : function() {
		var me = this;
		this.postponeRequestGrid = Ext.create('PostponeRequestGrid',{
			'complaint':me.complaint
			,flex:4
		});
		this.items = this.getItems();
		this.callParent(arguments);
	}
	,getItems : function() {
		var me = this;
		return [
		{
			xtype:'toppanel',   
			items:[{
				xtype:'titlepanel',flex:1,
				items:[{xtype:'displayfield',value:i18n('i18n.complaint.postponeRequest.approval')}]
			}]
		}
		,{
			xtype:'basicpanel',flex:1
			,items:[me.postponeRequestGrid]
		}
	
	  ];
	}
});


/**
 *  查询待办投诉工单的store
 */
Ext.define('WaitComplaintStore',{
	extend:'Ext.data.Store'
	,model:'ComplaintModel'
	,autoLoad:true
	,pageSize:25
	,proxy:{
		type:'ajax'
		,api:{read:'searchWaitComplaints.action'}
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'waitComplaints'
			,totalProperty:'totalCount'
		}
	}
});

