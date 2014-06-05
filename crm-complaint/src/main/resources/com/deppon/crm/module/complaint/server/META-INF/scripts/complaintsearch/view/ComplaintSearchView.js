//客户工单查询 界面
var complaintDataControl =  (CONFIG.get("TEST"))? new ComplaintDataTest():new ComplaintData();
/**
 * 日期校验开始日期，结束日期
 */
Ext.form.field.VTypes.dateRange = function(val,field){
		 var date = field.parseDate(val);	
	     if (!date) {
	         return false;
	     }
	     if (field.startDateFieldName && (!this.dateRangeMax || (date.getTime() != this.dateRangeMax.getTime()))) {
	         var start = field.up('form').getForm().findField(field.startDateFieldName);
	         start.setMaxValue(date);
	         start.validate();
	         this.dateRangeMax = date;
	     }
	     else if (field.endDateFieldName && (!this.dateRangeMin || (date.getTime() != this.dateRangeMin.getTime()))) {
	         var end = field.up('form').getForm().findField(field.endDateFieldName);
	         end.setMinValue(date);
	         end.validate();
	         this.dateRangeMin = date;
	     }
	     return true;
};

/**
 *  工单查询 BasicPanel
 */
Ext.define('ComplaintBasicPanel',{
	extend:'BasicPanel'
	,complaintConditionForm:null//查询条件
	,complaintPanel:null  // 工单模块
	,layout:'border'
	,initComponent:function(){
		var me = this;
		me.complaintConditionForm = Ext.create('ComplaintConditionForm',{
			'record':new SearchComplaintConditionModel(),'region':'north'
			,'parent':me
		});
		me.complaintPanel = Ext.create('ComplaintPanel',{
			'region':'center','parent':me,'complaintData':me.complaintData
		});
		Ext.applyIf(me,{
			'items':[me.complaintConditionForm,me.complaintPanel]
		});
		me.callParent(arguments);
	}
});

/**
 * 客户工单查询总 panel
 */
Ext.define('ComplaintPanel',{
	extend:'BasicPanel'
	,parent:null // 父级
	,complaintResultGrid:null  //查询结果
	,complaintLookButtonPanel:null//查询按钮面板
	,layout:'border'
	,initComponent:function(){
		var me = this;
		me.complaintLookButtonPanel = Ext.create('ComplaintLookBtnPanel',{
			'region':'north',parent:me
		});
		
		me.complaintResultGrid = Ext.create('ComplaintResultGrid',{
			'region':'center',parent:me.parent,'complaintData':complaintDataControl
		});
		Ext.applyIf(me,{
			'items':[me.complaintLookButtonPanel,me.complaintResultGrid]
		});
		me.callParent(arguments);
	}
});
	
/**
 * 查看按钮 panel
 */
Ext.define('ComplaintLookBtnPanel',{
	extend:'NormalButtonPanel'
	,parent:null   //父级
	,initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent(arguments);
	}
	,getItems:function(){
		var me = this;
		return [{
			xtype:'leftbuttonpanel',
			items:{xtype:'button',text:i18n('i18n.complaint.look_up'),scope:me,handler:me.lookEvent}
			}
			,{xtype:'middlebuttonpanel'}
			,{xtype:'rightbuttonpanel',items:[{
				xtype:'button',text:i18n('i18n.complaint.search'),
				scope:me,
				handler:me.searchComplaintEvent
			}]}
		];
	}
	/* 查询条件，日期格式检查，检查失败时提示消息并范围false */
	, checkSearchDate:function(startTime, endTime, errMsg){
		if (DpUtil.isEmpty(startTime) && DpUtil.isEmpty(endTime)){ 
			// 起始结束为空时，无需检查日期格式
			return true;
		}
		if(!Ext.isDate(startTime) || !Ext.isDate(endTime)){
			MessageUtil.showMessage(errMsg);return false;
		}
		return true;
	}
	/*客户工单查询  事件*/
	,searchComplaintEvent:function(){
		var me = this;
		var searchForm = me.parent.parent.complaintConditionForm.getForm();
		if(DpUtil.isEmpty(searchForm)){return;}
		if(DpUtil.isEmpty(searchForm.findField('fieldValue').getValue())){
			//判断上报时间
			var reportTimeStart = searchForm.findField('reportTimeStart').getValue();
			var reportTimeEnd = searchForm.findField('reportTimeEnd').getValue();

			if((DpUtil.isEmpty(reportTimeStart) && !DpUtil.isEmpty(reportTimeEnd)) || (!DpUtil.isEmpty(reportTimeStart) && DpUtil.isEmpty(reportTimeEnd))){
				MessageUtil.showMessage(i18n('i18n.complaint.msg_reporttime_scope'));return;
			}
			
			if(!me.checkSearchDate(reportTimeStart, reportTimeEnd, i18n('i18n.complaint.msg_reporttime_scope'))){
				return ;
			}
			
			var num = DpUtil.compareTwoDate(reportTimeStart,reportTimeEnd);
			if(0 > num){
				MessageUtil.showMessage(i18n('i18n.complaint.comp_reporttime_scope')+":"+i18n('i18n.complaint.msg_reporttime_compare'));return;
			}
			if(num>31){
				// 上报时间超过31天
				MessageUtil.showErrorMes(i18n('i18n.complaint.comp_reporttime_scope')+":"+i18n('i18n.comp.comp_search.msg.date_scope'));return;
			}
			
			//判断处理时间
			var processBeginTime = searchForm.findField('processBeginTime').getValue();
			var processEndTime = searchForm.findField('processEndTime').getValue();
			
			if((DpUtil.isEmpty(processBeginTime) && !DpUtil.isEmpty(processEndTime)) || (!DpUtil.isEmpty(processBeginTime) && DpUtil.isEmpty(processEndTime))){
				MessageUtil.showMessage(i18n('i18n.complaint.msg_processtime_scope'));return;
			}
			
			if(!me.checkSearchDate(processBeginTime, processEndTime, i18n('i18n.complaint.msg_processtime_scope'))){
				return ;
			}
			
			num = DpUtil.compareTwoDate(processBeginTime,processEndTime);
			if(0 > num){
				MessageUtil.showMessage(i18n('i18n.complaintVerifyTask.processTime')+":"+i18n('i18n.complaint.msg_reporttime_compare'));return;
			}
			if(num>31){
				// 处理时间超过31天
				MessageUtil.showErrorMes(i18n('i18n.complaintVerifyTask.processTime')+":"+i18n('i18n.comp.comp_search.msg.date_scope'));return;
			}

			
			//判断回访时间
			var visitorTimeStart = searchForm.findField('visitorTimeStart').getValue();
			var visitorTimeEnd = searchForm.findField('visitorTimeEnd').getValue();
			
			if((DpUtil.isEmpty(visitorTimeStart) && !DpUtil.isEmpty(visitorTimeEnd)) || (!DpUtil.isEmpty(visitorTimeStart) && DpUtil.isEmpty(visitorTimeEnd))){
				MessageUtil.showMessage(i18n('i18n.complaint.msg_visitortime_scope'));return;
			}
			
			if(!me.checkSearchDate(visitorTimeStart, visitorTimeEnd, i18n('i18n.complaint.msg_visitortime_scope'))){
				return ;
			}

			num = DpUtil.compareTwoDate(visitorTimeStart,visitorTimeEnd);
			if(0 > num){
				MessageUtil.showMessage(i18n('i18n.ScatterCustManagerView.visitTime')+":"+i18n('i18n.complaint.msg_reporttime_compare'));return;
			}
			if(num>31){
				// 回访时间超过31天
				MessageUtil.showErrorMes(i18n('i18n.ScatterCustManagerView.visitTime')+":"+i18n('i18n.comp.comp_search.msg.date_scope'));return;
			}

			
			if(/* 三类时间 至少要有一组填写*/
				DpUtil.isEmpty(reportTimeStart) && DpUtil.isEmpty(reportTimeEnd)
				&& DpUtil.isEmpty(processBeginTime) && DpUtil.isEmpty(processEndTime)
				&& DpUtil.isEmpty(visitorTimeStart) && DpUtil.isEmpty(visitorTimeEnd)
			){
				MessageUtil.showMessage(i18n('i18n.comp.msg_select_all_time')/*上报时间、处理时间、回访时间 至少填写一个*/);return;
			}
		}else{
			if(DpUtil.isEmpty(searchForm.findField('fieldName').getValue())){
				MessageUtil.showMessage('请选择【'+searchForm.findField('fieldValue').getRawValue()+"】类别");return;
			}
		}
		//判断工单部门
		var departmentNameValue = searchForm.findField('departmentName').getValue()
		if(!Ext.isEmpty(departmentNameValue)){
			if(!/^\d+$/.test(departmentNameValue)){
				MessageUtil.showErrorMes('工单部门输入有误！');
				return;
			}
		}
		me.parent.complaintResultGrid.store.loadPage(1);
	}
	,lookEvent:function(){
		var me = this;
		var complaintResultGridId = me.parent.complaintResultGrid;
		var selection = complaintResultGridId.getSelectionModel().getSelection();
		if(DpUtil.isEmpty(selection) || selection.length==0){
			MessageUtil.showMessage(i18n('i18n.complaint.msg_data_select'));return;
		}
		show_comp_detailsWin(selection[0].data.fid);/* 调用工单详情-弹出框 */
	}
});

/**
 * 查询条件
 */
Ext.define('ComplaintConditionForm',{
	extend:'SearchFormPanel'
	,defaultType:'textfield'
	,defaults:{labelWidth:80}
	,layout:{type:'table',columns:6}
	,parent:null
	,departmentStore:null
	,initComponent:function(){
		var me = this;
		me.departmentStore=Ext.create('DepartmentStore'
				,{ autoLoad:false
				  ,listeners:{
					beforeload:function(store, operation, eOpts){
						Ext.apply(operation,{
							params : {
										'deptName':Ext.getCmp('departmentName').getRawValue()
									 }
						});	
					}
				  }		
				});			
		
		me.items = me.getItems();
		Ext.apply(me.defaults,{
			enableKeyEvents:true,listeners:{scope:me,'keypress':me.keypressEvent}
		});			
		
		this.callParent(arguments);
		var start = new Date((new Date).getFullYear(),(new Date).getMonth(),(new Date).getDate(),0,0,0)-(30*24*60*60*1000);
		
		//设置默认值		
	    me.getForm().findField("reportTimeStart").setValue(new Date(start));
	    Ext.getCmp('complaintSearchBusinessType').getStore().add({'code':'','codeDesc':'全部'});
	    Ext.getCmp('complaintSearchBusinessType').setValue('');
	}
	//监听按键事件
	,keypressEvent:function(field,event){
		var me = this;
		if(event.getKey() == Ext.EventObject.ENTER){
    		me.parent.complaintPanel.complaintLookButtonPanel.searchComplaintEvent();
    	}
	}
	,getItems:function(){
		var me = this;
		return [
			/*1*/
			{xtype:'displayfield',value:i18n('i18n.complaint.comp_reporttime_scope')+":",width:90}/*工单上报时间*/
			,{
				xtype:'datetimefield',
				name:'reportTimeStart',
				width:128,
				format:'Y-m-d H:i',
//				endDateFieldName : 'reportTimeEnd',
//				vtype : 'dateRange',
				defaultTime:[0,0,0],
				listeners:{
		    		expand:function(t){
		    			if(Ext.isEmpty(t.getValue())){
		    			var nowDateLong=new Date();
		    			nowDateLong.setDate(new Date().getDate()-30);
		    			t.setValue(new Date(nowDateLong.getFullYear(),nowDateLong.getMonth(),nowDateLong.getDate(),0,0));
		    			}
		    		}
		    	}
			},
			{xtype:'displayfield',value:i18n('i18n.complaint.to')}
			,{
				xtype:'datetimefield',
				name:'reportTimeEnd',
				width:128,
				startDateFieldName : 'reportTimeStart',
//				vtype :'dateRange',
				format:'Y-m-d H:i',
				value:new Date((new Date).getFullYear(),(new Date).getMonth(),(new Date).getDate(),23,59,0)
				,defaultTime:[23,59,59],
				listeners:{
		    		expand:function(t){
		    			if(Ext.isEmpty(t.getValue())){
		    			var nowDateLong=new Date();
		    			t.setValue(new Date(nowDateLong.getFullYear(),nowDateLong.getMonth(),nowDateLong.getDate(),23,59,0));
		    			}
		    		}
		    	}
			}
			,{
				xtype:'combobox',name:'reportType',
				fieldLabel:i18n('i18n.complaint.comp_reportType'/*上报类型*/)
				,store:getDataDictionaryByName(DataDictionary,'REPORT_TYPE')
				,queryMode: 'local',displayField:'codeDesc',valueField:'code'
			}
			,{name:'compman',fieldLabel:i18n('i18n.complaint.comp_compman')/*上报人*/,width:200}
			/*2*/
			,{xtype:'displayfield',value:i18n('i18n.complaintVerifyTask.processTime')+":",width:90},/*处理时间*/
			{
					xtype:'datetimefield',
					name:'processBeginTime',
//							endDateFieldName : 'processEndTime',
//					vtype : 'dateRange',
					width:128,
					format: 'Y-m-d H:i',
					defaultTime:[0,0,0],
					listeners:{
			    		expand:function(t){
			    			if(Ext.isEmpty(t.getValue())){
			    			var nowDateLong=new Date();
			    			nowDateLong.setDate(new Date().getDate()-30);
			    			t.setValue(new Date(nowDateLong.getFullYear(),nowDateLong.getMonth(),nowDateLong.getDate(),0,0));
			    			}
			    		}
			    	}
			 },
			{xtype:'displayfield',value:i18n('i18n.complaint.to')},
			{
					xtype:'datetimefield',
					name:'processEndTime',
					startDateFieldName : 'processBeginTime',
//					vtype : 'dateRange',
					width:128,
					format: 'Y-m-d H:i',
					defaultTime:[23,59,59],
					listeners:{
			    		expand:function(t){
			    			if(Ext.isEmpty(t.getValue())){
			    			var nowDateLong=new Date();
			    			t.setValue(new Date(nowDateLong.getFullYear(),nowDateLong.getMonth(),nowDateLong.getDate(),23,59,0));
			    			}
			    		}
			    	}
		   }			 
			,{
				xtype:'combobox',name:'dealstatus'
				,fieldLabel:i18n('i18n.complaint.comp_dealstatus')/*解决情况*/
				,store:getDataDictionaryByName(DataDictionary,'RESOLVE_CASE')
				,queryMode: 'local',displayField:'codeDesc',valueField:'code'
			}
			,{
				xtype:'combobox',fieldLabel:i18n('i18n.complaint.comp_prostatus')/*处理状态*/
				,name:'prostatus'
				,store:copy_combobox_store(getDataDictionaryByName(DataDictionary,'COMPLAINT_PROCESS_STATUS'))
				,queryMode: 'local',displayField:'codeDesc',valueField:'code',width:200
			}
			/*3*/
			,{xtype:'displayfield',value:i18n('i18n.ScatterCustManagerView.visitTime')+":",width:90},/*回访时间*/
			{
					xtype:'datetimefield',
					name:'visitorTimeStart',
//							endDateFieldName : 'visitorTimeEnd',
					vtype : 'dateRange',
					width:128,
					format: 'Y-m-d H:i',
					defaultTime:[0,0,0],
					listeners:{
			    		expand:function(t){
			    			if(Ext.isEmpty(t.getValue())){
			    			var nowDateLong=new Date();
			    			nowDateLong.setDate(new Date().getDate()-30);
			    			t.setValue(new Date(nowDateLong.getFullYear(),nowDateLong.getMonth(),nowDateLong.getDate(),0,0));
			    			}
			    		}
			    	}
			 },
			{xtype:'displayfield',value:i18n('i18n.complaint.to')},
			{
					xtype:'datetimefield',
					name:'visitorTimeEnd',
					startDateFieldName : 'visitorTimeStart',
//					vtype : 'dateRange',
					width:128,
					format: 'Y-m-d H:i',
					defaultTime:[23,59,59],
					listeners:{
			    		expand:function(t){
			    			if(Ext.isEmpty(t.getValue())){
			    			var nowDateLong=new Date();
			    			t.setValue(new Date(nowDateLong.getFullYear(),nowDateLong.getMonth(),nowDateLong.getDate(),23,59,0));
			    			}
			    		}
			    	}
			}
			,{name:'phone',fieldLabel:i18n('i18n.complaint.comp_tel')}/*联系电话*/
			,{
				fieldLabel:i18n('i18n.complaint.process_record.custsatisfy')/*客户满意度*/
				,xtype:'combobox',mode:'local',name:'custsatisfy',queryMode: 'local'
				,displayField:'codeDesc',valueField:'code'
				//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
				,store:getDataDictionaryByName(DataDictionary,'SATISFACTION_DEGREE')
				,width:200
			}
			/*4*/
			,{
				xtype:'combobox',name:'fieldName',width:85,value:"bill" // 默认选中“凭证号”
				//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
				,store:getDataDictionaryByName(DataDictionary,'COMPLAINT_FIELD')
				,queryMode: 'local',displayField:'codeDesc',valueField:'code'
			}
			,{name:'fieldValue',width:105}
			,{name:"reporter",fieldLabel:i18n('i18n.complaint.comp_reporter'),colspan:2,width:125,labelWidth:40}
			,{name:'dealman',fieldLabel:i18n('i18n.complaint.comp_dealman')}
			,{name:'visitor',fieldLabel:i18n('i18n.ScatterCustManagerView.visitor')/*回访人*/,width:200}
			
			/*5*/
			,{xtype:'displayfield',value:'工单部门:',width:90}
			,{
				xtype:'combobox'/*处理状态*/
				,queryParam:'deptId'
				,id:'departmentName'
				,width:235
				,colspan:3
				,store:me.departmentStore
				,displayField:'deptName'
				,valueField:'id'
				,minChars:1
				,typeAhead: false
				,hideTrigger:false
			}
			,{
				xtype:'combo',
				name:'complaintSearchBusinessType',
				id:'complaintSearchBusinessType',
				displayField:'codeDesc',
			    valueField:'code',
			    queryMode:'local',
			    fieldLabel:i18n('i18n.complaint.businessType'),
			    editable:false,
			    store:getDataDictionaryByName(DataDictionary,'COMPLAINT_BUSINESS_MODEL')
			}
		];
	}
});


/**
 * 客户工单查询结果
 */
Ext.define('ComplaintResultGrid',{
	extend:'SearchGridPanel',autoScroll:true
	,parent:null
	,complaintData:null//数据Data
	,initComponent:function(){
		var me = this;
		me.store = me.complaintData.initComplaintSearchStore(function(store, operation, eOpts){
			var searchForm = me.parent.complaintConditionForm.getForm();
			if(searchForm!=null){
				var searchParams = {};
				if(!DpUtil.isEmpty(searchForm.findField('fieldName').getValue())
					&&!DpUtil.isEmpty(searchForm.findField('fieldValue').getValue())){
					searchParams['complaintSearchCondition.'+searchForm.findField('fieldName').getValue()]
					= searchForm.findField('fieldValue').getValue();
				}else{
					searchParams = {
						'complaintSearchCondition.reportTimeStart':searchForm.findField('reportTimeStart').getValue()
						,'complaintSearchCondition.reportTimeEnd':searchForm.findField('reportTimeEnd').getValue()
						,'complaintSearchCondition.reportType':searchForm.findField('reportType').getValue()
						,'complaintSearchCondition.compman':searchForm.findField('compman').getValue()
						,'complaintSearchCondition.phone':searchForm.findField('phone').getValue()
						,'complaintSearchCondition.dealstatus':searchForm.findField('dealstatus').getValue()
						,'complaintSearchCondition.prostatus':searchForm.findField('prostatus').getValue()
						,'complaintSearchCondition.dealman':searchForm.findField('dealman').getValue()
						,'complaintSearchCondition.custsatisfy':searchForm.findField('custsatisfy').getValue()
						,'complaintSearchCondition.processBeginTime':searchForm.findField('processBeginTime').getValue()
						,'complaintSearchCondition.processEndTime':searchForm.findField('processEndTime').getValue()
						,'complaintSearchCondition.visitorTimeStart':searchForm.findField('visitorTimeStart').getValue()
						,'complaintSearchCondition.visitorTimeEnd':searchForm.findField('visitorTimeEnd').getValue()
						,'complaintSearchCondition.visitor':searchForm.findField('visitor').getValue()
						,'complaintSearchCondition.reporter':searchForm.findField('reporter').getValue()
						,'complaintSearchCondition.deptId':searchForm.findField('departmentName').getValue()
						,'complaintSearchCondition.businessModel':searchForm.findField('complaintSearchBusinessType').getValue()
					};
				}
				Ext.apply(operation,{params : searchParams});	
			}
		});
		
		me.dockedItems = me.getMyDockedItems();
		me.columns = me.getColumns();
		me.callParent(arguments);
		/*监听重复工单码集合*/
		me.on('cellclick', function(thiz, row, col, record) {
			var fieldName=thiz.getHeaderCt().getHeaderAtIndex(col).dataIndex;
			if(fieldName=='recomcode' && !DpUtil.isEmpty(record.get('recomcode'))){
				Ext.create('RepeatComplaintWindow',{'complaint':record.data}).show();
			}
			if (fieldName == 'compman' && !Ext.isEmpty(record.get('fid'))) {//显示来电人信息
				show_comp_detailsWin(record.get('fid'));/* 调用工单详情-弹出框 */record.data.fid
			}
		});
	}
	,selModel : new Ext.selection.CheckboxModel({mode:'single'})
	,getColumns:function(){
		var me = this;
		return [
			{xtype:'rownumberer',header:i18n('i18n.complaint.serial_number'),width:35},
			{header:i18n('i18n.complaint.businessType'),dataIndex:'businessModel',
				renderer:function(value){
					return rendererDictionary(value,DataDictionary.COMPLAINT_BUSINESS_MODEL);
				}
			},
			{header:i18n('i18n.complaint.comp_bill'),width:85,dataIndex:'bill'}
			,{header:i18n('i18n.complaint.comp_reporttime_scope'),width:115,dataIndex:'reporttime',renderer:function(value){
				return DpUtil.renderDateFormat(value, 'Y-m-d H:i');
			}}
			,{header:i18n('i18n.complaint.comp_dealbill'),width:110,dataIndex:'dealbill'}
			,{
				header:i18n('i18n.complaint.comp_recomcode'),width:80,dataIndex:'recomcode'
				,renderer:function(value){return '<a href="javascript:;">'+value+'</a>';}
			}
			,{
				header:i18n('i18n.complaint.comp_reportType'),width:60,dataIndex:'reporttype'
				,renderer:function(value){
					return rendererDictionary(value,DataDictionary.REPORT_TYPE);
				}
			}
			,{header:i18n('i18n.complaint.comp_compman'),width:55,dataIndex:'compman'
				,renderer:function(value){return '<a href="javascript:;">'+value+'</a>';}
			}
			,{
				header:i18n('i18n.complaint.comp_pririty'),width:60,dataIndex:'pririty'
				,renderer:function(value){
					return rendererDictionary(value,DataDictionary.PRIORITY_RATING);
				}
			}
			,{
				header:i18n('i18n.complaint.comp_complevel'),width:60,dataIndex:'complevel'
				,renderer:function(value){
					return rendererDictionary(value,DataDictionary.COMPLAINT_LEVEL);
				}
			}
			,{
				header:i18n('i18n.complaint.comp_prostatus'),width:60,dataIndex:'prostatus'
				,renderer:function(value){
					return rendererDictionary(value,DataDictionary.COMPLAINT_PROCESS_STATUS);
				}
			}
			,{
				header:i18n('i18n.complaint.comp_dealstatus'),width:60,dataIndex:'dealstatus'
				,renderer:function(value){
					return rendererDictionary(value,DataDictionary.RESOLVE_CASE);
				}
			}
		];
	}
		
	//分页条
	,getMyDockedItems :function(){ 
		var me = this;
		return [{
			xtype : 'pagingtoolbar'
			,plugins:[Ext.create('Ext.ux.PageComboResizer')]
			,store : me.store,dock : 'bottom',displayInfo : true
		}];
	}
});

var viewport = null;/* 视图容器 */
Ext.onReady(function(){
	//页面需要加载的数据字典数组
	var keys=[
	    'COMPLAINT_FIELD' /*客户工单查询条件*/
		,'REPORT_TYPE' /*上报类型*/
		,'COMPLAINT_PROCESS_STATUS' /*工单模块处理状态*/
		,'RESOLVE_CASE'     /*解决情况*/
		,'EXPECTED_TIME_LIMIT'//期望时限
	    ,'WORK_SINGLE_SOURCE'/*工单来源*/
	    ,'PRIORITY_RATING'/*优先级别*/
		,'COMPLAINT_LEVEL'	/* 投诉级别 */
		,'COMPLAINT_LINK'	/* 业务环节 */
		,'COMPLAINT_RECORD_TYPE'/*工单模块反馈类型*/
		,'SATISFACTION_DEGREE'/*客户满意度*/
		,'CALLER_TYPE'/*来电人类型*/
         // 客户类型
      	,'CUSTOMER_TYPE'
  		// 目标级别',目前级别,客户等级',会员等级
  		,'MEMBER_GRADE',
  		 // 业务类型
      	'COMPLAINT_BUSINESS_MODEL'
  	];
	//初始数据字典
	initDataDictionary(keys);
	
	viewport = Ext.create('Ext.container.Viewport',{
		layout:'border','autoScroll':true,items:[Ext.create('ComplaintBasicPanel',{region:'center'})]
	});
	viewport.setAutoScroll(false);
	viewport.doLayout();
	
});


/**
 * 拷贝一个combobox 的store
 * @param {} default_store
 */
function copy_combobox_store(default_store){
	Array.prototype.del=function(n) {
	　if(n<0 || n>this.length-1){return this;}
	　else{return this.slice(0,n).concat(this.slice(n+1,this.length));}
	};
	var new_store = Ext.create('Ext.data.Store',{
		 fields: ['codeDesc', 'code']
	});
	var new_data = [];
	var is_exist = function(obj){
		for(var i=0;i<new_data.length;i++){
			var temp = new_data[i];
			if(temp['codeDesc']==obj['codeDesc']){
				new_data[new_data.length] = {
					'codeDesc':temp['codeDesc'],
					'code':temp['code']+','+obj['code']
				};
				new_data = new_data.del(i);
				return true;
			}
		}
		return false;
	};
	
	default_store.each(function(record){
		var temp_data = record.data;
		if(!is_exist(temp_data)){
			new_data[new_data.length] = {
				'codeDesc':temp_data['codeDesc'],
				'code':temp_data['code']
			};
		}
	});
	new_store.loadData(new_data);
	return new_store;
}