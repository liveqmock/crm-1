//工单处理 界面
var processComplaintDataControl =  (CONFIG.get("TEST"))? new ComplaintDataTest():new ProcessComplaintData();

/**
 * 加载处理
 * @param {} me
 */
function loadProcessFn(me,is_lock){
	var complaintGrid = me.parent.complaintGrid;
	var selection = complaintGrid.getSelectionModel().getSelection();
	if(DpUtil.isEmpty(selection) || selection.length==0){
		MessageUtil.showMessage(i18n('i18n.complaint.msg_data_select'));return;
	}
	var compalint = selection[0].data;
	compalint['resultList'] = null;//清除resultList
	
	var processComplaintWin_show = function(){
		// 调用客户工单详情 弹出框
		Ext.create('ProcessComplaintWindow',{
			id:'processComplaintWindowId'
			,'complaint':compalint
			,parent:me.parent  // 本页面弹出框的父级
			,x:0,y:0
		}).show();
	};
	
	if(is_lock && is_lock===true){
		processingMask.show();
		DpUtil.requestJsonAjax('compProcess_lockById.action',{
			'complaint':compalint
		},function(response){
			var comp = response.complaint;
			selection[0].data = comp;
			processingMask.hide();
			processComplaintWin_show();
		},function(){processingMask.hide();});
	}else{processComplaintWin_show();}
}
/**
 *  工单处理 BasicPanel
 */
Ext.define('ComplaintBasicPanel',{
	extend:'BasicPanel'
	,complaintProcessPanel:null
	,reportType:null       // 上报类型
	,is_complaint_or_exception :(isPermission('/common/complaintProcess.action') || isPermission('/common/exceptionProcess.action'))
	,complaintProcessCountPanel:null  //处理工单之统计count
	,complaintPanel:null  // 工单模块
	,layout:'border'
	,initComponent:function(){
		var me = this;
		{//初始化上报类型
			if(
				isPermission('/common/complaintProcess.action')/*投诉处理(经理)*/
				|| isPermission('/complaint/insertComplaint.action') /*接入投诉(处理员)*/
			){
				me.reportType = i18n('i18n.complaint.fpririty_complaint');
			}
			if(
				isPermission('/common/exceptionProcess.action')/*异常处理(经理)*/
				|| isPermission('/complaint/insertException.action')/*接入异常(处理员)*/
			){
				me.reportType = i18n('i18n.complaint.fpririty_exception');
			}
			
			{//加载处理工单统计
				me.complaintProcessCountPanel = Ext.create('BasicPanel',{
					height:25,'region':'north'
					,items:[
						{'xtype':'label',text:i18n('i18n.complaint.process_wait_number')}
						,{'xtype':'label',text:'',id:'complaintProcessCountLableId'}
						,{
							xtype:'button',text:i18n('i18n.ComplaintProcessView.transfer'),handler:function(){
								var complaintGrid = me.complaintPanel.complaintGrid;
								var selection = complaintGrid.getSelectionModel().getSelection();
								if(DpUtil.isEmpty(selection) || selection.length==0){
									MessageUtil.showMessage(i18n('i18n.complaint.msg_data_select'));return;
								}
								var record = selection[0];
								var url='/crm-complaint/ssoURLRequest?app=hollycrm&url=hollycc/call/out/call_out_crm.jsp?'+
								'complaintId='+record.data.fid+'&phone='+record.data.tel;
								var url1='/ssoURLRequest?app=depponcc&url=hollycc/call/out/call_out_crm.jsp?'+
								'complaintId='+record.data.fid+'&phone='+record.data.tel
								parent.clickTabpanel('phoneCallout',i18n('i18n.ComplaintProcessView.phoneTransfer'),url1);
							}
						}
					]
				});
				
				window.setInterval(function(){me.loadProcessComplaintCount();}, 3*60*1000);
				me.loadProcessComplaintCount();
			}
			
			me.complaintPanel = Ext.create('ComplaintPanel',{
				'region':'center','reportType':me.reportType
				,'is_complaint_or_exception':me.is_complaint_or_exception
				,'complaintData':processComplaintDataControl
			});
			Ext.applyIf(me,{
	    		items:[me.complaintProcessCountPanel,me.complaintPanel]
	    	});
			me.callParent(arguments);
		}
	}
	//声明：加载处理工单统计
	,loadProcessComplaintCount:function(){
		var me = this;
		DpUtil.requestJsonAjax('count_pendingComplaint.action',{
			'complaintSearchCondition':{reportType:me.reportType}
		},function(response){
			var count_pendingComplaint = response.searchResultMap['count'];
			Ext.getCmp('complaintProcessCountLableId').setText('('+count_pendingComplaint+')',true);
		},function(){});
	}
});

/**
 * 客户工单查询总 panel
 */
Ext.define('ComplaintPanel',{
	extend:'BasicPanel'
	,complaintProcessPanel:null
	,reportType:null       // 上报类型
	,complaintConditionForm:null//查询条件
	,omplaintProcessBtnPanel:null//工单处理 按钮
	,is_complaint_or_exception :null
	,complaintGrid:null  //查询结果
	,complaintData:null//数据Data
	,layout:'border'
	,initComponent:function(){
		var me = this;
		if(me.is_complaint_or_exception){//经理(投诉 or 异常)
			me.complaintGrid = Ext.create('ComplaintGrid',{
				'region':'center','complaintData':me.complaintData
				,store:me.complaintData.initProcessComplaintStore(function(store, operation, eOpts){
					var searchConditionForm = me.complaintConditionForm;
					if(searchConditionForm!=null){
						searchConditionForm.getForm().updateRecord(searchConditionForm.record);
						//设置请求参数
						var searchParams = {
							'complaintSearchCondition.reportType':me.reportType
						};
						var searchCustCondition = searchConditionForm.record.data;
						if(DpUtil.isEmpty(searchCustCondition.fieldName) || DpUtil.isEmpty(searchCustCondition.fieldValue)){
							Ext.apply(operation,{params:searchParams});	return;
						}
						searchParams['complaintSearchCondition.'+searchCustCondition.fieldName]
							= searchCustCondition.fieldValue;
						Ext.apply(operation,{params:searchParams});	
					}
				})
			});
			me.complaintConditionForm = Ext.create('ComplaintConditionForm',{
				parent:me,'region':'north'
				,'complaintData':me.complaintData,margin:'5 0 0 0'
				,is_complaint_or_exception:me.is_complaint_or_exception
			});
			me.items = [me.complaintConditionForm,me.complaintGrid];
		}else{//普通处理员(投诉 or 异常)
			me.complaintGrid = Ext.create('ComplaintGrid',{
				'region':'center',showPager:false,store:Ext.create('ComplaintProcessStore')
			});
			me.omplaintProcessBtnPanel = Ext.create('ComplaintProcessBtnPanel',{
				parent:me,reportType:me.reportType,'region':'north'
			});
			me.items = [me.omplaintProcessBtnPanel,me.complaintGrid];
		}
		me.callParent(arguments);
	}
});

/**
 * 工单处理 相关按钮 panel
 */
Ext.define('ComplaintProcessBtnPanel',{
	extend:'NormalButtonPanel'
	,parent:null  // 父级总panel
	,reportType:null  //上报类型
	,initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent(arguments);
	}
	,getItems:function(){
		var me = this;
		return [
			{
				xtype:'leftbuttonpanel',
				items:[
						{
							xtype:'button',text:i18n('i18n.complaint.insert_complaint'),scope:me
							,hidden:!isPermission('/complaint/insertComplaint.action')
							,handler:me.loadCompListFn
						}
						,{
							scope:me,xtype:'button',text:i18n('i18n.complaint.insert_exception')
							,hidden:!isPermission('/complaint/insertException.action')
							,handler:me.loadCompListFn
						}
						,{scope:me,xtype:'button',text:i18n('i18n.complaint.start_process'),handler:function(){
							loadProcessFn(this);
						}}
				]
			},
			{xtype:'middlebuttonpanel'}
		];
	}	
	,loadCompListFn:function(reportType){
		//reportType 这里指的是btn本身，reportType没改名字担心其它地方用到，这里说明下
		reportType.disable();
		setTimeout(function(){
			reportType.blur();
			reportType.enable();
		},2000);
		var me = this;
		me.parent.complaintGrid.store.load({params:{'reportType':me.reportType}});
	}
});

/**
 * 查询条件
 */
Ext.define('ComplaintConditionForm',{
	extend:'FormAndBtnPanel'
	,parent : null  //父级
	,is_complaint_or_exception:false //是否为经理处理(投诉处理or异常处理)
	,'record':new SearchComplaintConditionModel()
	,complaintData:null
	,initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent(arguments);
	}
	/*客户工单查询  事件*/
	,searchComplaintEvent:function(){
		var me = this;
		var fieldValue = Ext.getCmp('fieldValue').getValue();
		if((DpUtil.isEmpty(Ext.getCmp('fieldName').getValue()) && !DpUtil.isEmpty(fieldValue))
		  || (!DpUtil.isEmpty(Ext.getCmp('fieldName').getValue()) && DpUtil.isEmpty(fieldValue))		
		){
			MessageUtil.showMessage(i18n('i18n.ComplaintProcessView.searchCoditionNotComPleaseInputAll'));
			return;
		}
		me.parent.complaintGrid.store.loadPage(1);
		
	}
	,getItems:function(){
		var me = this;
		var items = [
			{
				xtype:'combobox',name:'fieldName',id:'fieldName'
				//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
				,store:getDataDictionaryByName(DataDictionary,'COMPLAINT_FIELD')
				,queryMode: 'local',displayField:'codeDesc',valueField:'code'
			}
			,{xtype: 'displayfield',width:5}
			,{name:'fieldValue',id:'fieldValue'}
			
			,{
				xtype:'button',text:i18n('i18n.complaint.search'),margin:'-5 0 0 5',width:60
				,scope:me,handler:me.searchComplaintEvent
			}
		];
		if(me.is_complaint_or_exception===true){
			items[items.length] = {
				xtype:'button',text:i18n('i18n.complaint.start_process'),margin:'-5 0 0 5'
				,width:80,scope:me
				,handler:function(){loadProcessFn(me,true);}
			};
		}
		return [
			{
				xtype:'noborderformpanel',layout:{type:'table',columns:5},
				defaultType:'textfield',defaults:{width:150},items:items
			}
		];
	}
});


/**
 * 客户工单查询结果
 */
Ext.define('ComplaintGrid',{
	extend:'SearchGridPanel',autoScroll:true
	,showPager:true // 显示分页
	,complaintData:null//数据Data
	,initComponent:function(){
		var me = this;
		me.columns = me.getColumns();
		if(me.showPager && me.showPager===true){
			me.dockedItems = me.getMyDockedItems();
		}
		this.callParent();
		me.on('cellclick', function(thiz, row, col, record) {
			var fieldName=thiz.getHeaderCt().getHeaderAtIndex(col).dataIndex;
			if(fieldName=='recomcode' && !DpUtil.isEmpty(record.get('recomcode'))){
				//重复工单码、加载重复工单码查询
				Ext.create('RepeatComplaintWindow',{'complaint':record.data}).show();
			}
			if (fieldName == 'compman') {//显示来电人信息
				show_comp_detailsWin({'me':me,'complaint':record.data});/* 调用工单详情-弹出框 */
			}
		});
	}
	,selModel:new Ext.selection.CheckboxModel({mode:'single'})
	,getColumns:function(){
		var me = this;
		return [
			{header:i18n('i18n.ComplaintProcessView.insertNumber'),width:60,dataIndex:'numberOfLock'}
			,{
				header:i18n('i18n.complaint.access.expireTime'),width:60,dataIndex:'lockingTime'
				,renderer:function(value) {
					if(value != null){return Ext.Date.format(new Date(value),'H:i');
					}else{return null;}
				}
			}			
			,{
				header:i18n('i18n.complaint.comp_bill'),width:70,dataIndex:'bill'
				,renderer:function(value,obj,record){
					var sameBill = record.get('sameBill');
					if(sameBill && sameBill > 0){
						return '<font color="red">'+value+'</font>';
					}
					return value;
				}
			}
			,{header:i18n('i18n.complaint.comp_dealbill'),width:90,dataIndex:'dealbill'}
			,{header:i18n('i18n.complaint.comp_recomcode'),width:70,dataIndex:'recomcode',
				renderer:function(value){
				 	return '<a href="javascript:void(0);">'+value+'</a>';
				 }	
			}
			,{
				header:i18n('i18n.complaint.comp_reportType'),width:60,dataIndex:'reporttype'
				,renderer:function(value){
					return rendererDictionary(value,DataDictionary.REPORT_TYPE);
				}
			}
			,{header:i18n('i18n.complaint.comp_complaincust'),width:60,dataIndex:'complaincust'}
			,{header:i18n('i18n.complaint.comp_compman'),width:55,dataIndex:'compman',
				 renderer:function(value){
				 	return '<a href="javascript:void(0);">'+value+'</a>';
				 }	
			}
			,{
				header:i18n('i18n.complaint.comp_pririty'),width:70,dataIndex:'pririty'
				,renderer:function(value){
					return rendererDictionary(value,DataDictionary.PRIORITY_RATING);
				}
			}
			,{
				header:i18n('i18n.complaint.comp_prostatus'),width:70,dataIndex:'prostatus'
				,renderer:function(value){
					return rendererDictionary(value,DataDictionary.COMPLAINT_PROCESS_STATUS);
				}
			}
			,{header:i18n('i18n.complaint.comp_reporter'),width:55,dataIndex:'reporter'}
			,{
				header:i18n('i18n.complaint.comp_reporttime'),width:115,dataIndex:'reporttime'
				,renderer:function(value) {
					if(value != null){return Ext.Date.format(new Date(value),'Y-m-d H:i');
					}else{return null;}
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
			,store : me.store
			,dock : 'bottom'
			,displayInfo : true
		}];
	}
});

Ext.onReady(function(){
	//页面需要加载的数据字典数组
	var keys=[
	    'COMPLAINT_FIELD' /* 客户工单查询条件 */
		,'TASKPROPERTIES_TYPE'	/* 任务属性 */
		,'COMPLAINT_LEVEL'	/* 投诉级别 */
		,'REPORT_TYPE' /*上报类型*/
		,'COMPLAINT_PROCESS_STATUS' /*工单模块处理状态*/
		,'PRIORITY_RATING'  /*优先级别*/
		,'WORK_SINGLE_SOURCE'/*工单来源*/
	    ,'EXPECTED_TIME_LIMIT'/*期望时限*/		
	    ,'PROCESS_TYPE'/*处理类型*/		
		,'COMPLAINT_RECORD_TYPE'//工单模块反馈类型,
		,'CALLER_TYPE'/*来电人类型*/
         // 客户类型
      	,'CUSTOMER_TYPE'
  		// 目标级别i18n('i18n.DutyAllocationSearchView.theCurrentLevelCustomerLevel'),会员等级
  		,'MEMBER_GRADE'				
  	];
	//初始数据字典
	initDataDictionary(keys);
	viewport = Ext.create('Ext.container.Viewport',{
		layout:'border','autoScroll':true,items:[Ext.create('ComplaintBasicPanel',{region:'center'})]
	});
	viewport.setAutoScroll(false);
	viewport.doLayout();
});
var viewport = null;/* 视图容器 */

