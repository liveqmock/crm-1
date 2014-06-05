var reportReturnProcessDataControl = (CONFIG.get("TEST"))
		? new ReportReturnPorcessDataTest()
		: new ReportReturnPorcessData();
		
var win;		
var viewport = null;/* 视图容器 */
Ext.onReady(function() {

Ext.define('reportReturnProcessPanel', {
			extend : 'BasicPanel',
			parent : null,
		//	reportReturnProcessConditionForm : null,// 查询条件
			reportReturnProcessResultGrid : null, // 查询结果
			reportReturnProcessData : null,// 数据Data

			layout : {
				type : 'vbox',
				align : 'stretch'
			},
			items : null,
			initComponent : function() {
				var me = this;
				var record = new ReportReturnProcessModel();
//				me.reportReturnProcessConditionForm = Ext.create(
//						'ReportReturnProcessConditionForm', {
//							'parent' : me,
//							'record' : record,
//							'reportReturnProcessData' : me.reportReturnProcessData
//						});
				me.reportReturnProcessResultGrid = Ext.create(
						'ReportReturnProcessResultGrid', {
							'parent' : me,
							'reportReturnProcessData' : me.reportReturnProcessData
						});
						
				me.reportReturnProcessResultGrid.on('cellclick', function(thiz, row, col, record) {
							var fieldName=thiz.getHeaderCt().getHeaderAtIndex(col).dataIndex;
			   				if(fieldName=="recomcode"){//重复工单码
			   					var complaintRecord = record.data;
					   		    if(!DpUtil.isEmpty(complaintRecord.recomcode)){//加载重复工单码查询
									//查询重复工单 点击事件
									Ext.create('RepeatComplaintWindow',{
										'complaint':complaintRecord
									}).show();
								}
			   				}
							if (fieldName == "compman") {//显示来电人信息
								show_comp_detailsWin(record.get('fid'));/* 调用工单详情-弹出框 */
							}
				});						
						
				me.items = [{
							xtype : 'basicpanel',
							flex : 1,
							items : [me.reportReturnProcessResultGrid]
						}
//						,
//						{
//							xtype : 'basicpanel',
//							items : [me.reportReturnProcessConditionForm]
//						}
						];
				this.callParent();
//				me.reportReturnProcessConditionForm.loadRecord(record);
			}
		});

/**
 * 查询条件
 */
Ext.define('ReportReturnProcessConditionForm', {
			name : 'reportReturnProcessConditionFormId',
			id : 'reportReturnProcessConditionFormId',
			extend : 'TitleFormPanel',
			items : null,
			receiverMember:null,
			shipperMember:null,
			initComponent : function() {
				this.items = this.getItems();
				this.callParent();
			},
			getItems : function() {}
		});

			//页面需要加载的数据字典数组
			var keys=[
				  //上报类型
			    'REPORT_TYPE',
			    //优先级别
			    'PRIORITY_RATING',
			    //工单模块处理状态
			    'COMPLAINT_PROCESS_STATUS',
			   //解决情况
			    'RESOLVE_CASE',
			     //来电 人类型
			    'CALLER_TYPE',
			    //期望时限    
			    'EXPECTED_TIME_LIMIT',
			    //工单来源
			    'WORK_SINGLE_SOURCE',
			    'COMPLAINT_RECORD_TYPE',//工单模块反馈类型
			    //客户满意度
			    'SATISFACTION_DEGREE',
      			// 客户类型
      			'CUSTOMER_TYPE',
  			     // 目标级别',目前级别,客户等级',会员等级
  			     'MEMBER_GRADE',
  			     // 业务类型
      			'COMPLAINT_BUSINESS_MODEL'
		  	];
			//初始数据字典
			initDataDictionary(keys);		
			
			/**
		     * 工单上报弹出框
		     */
		    Ext.define('ComplaintReportWindow', {
		    	extend: 'PopWindow',
				modal : true,
				height:630,
				autoScroll:true,
				x:0,
				y:0,
    			width:820,
    			closeAction:'hide',
    			layout : {
					type : 'vbox',
					align : 'stretch'
				},
    			items:null,
				complaintReportPanel:null,/* 显示基本信息  formPanel */
				complaintData:null,
				otherComplint:null,
				//加载参数，用于是否加载相关元素
				loadCondition:{
					
				},
			    initComponent: function() {
			        var me = this;
			        
			      	this.complaintReportPanel = Ext.create('complaintReportPanel', {
			      		parent:me,
			      					complaintData:me.complaintData,
									'complaintReportData' : complaintReportDataControl,
									'isHiddenProcessRecordPanel':false,
									'operate':'returnProcess'
								})
					this.items = this.getItems();
			        me.callParent(arguments);
			    }
				,getItems:function(){
					var me = this;
					return [{
  								flex:1,
  								xtype:'basicpanel',
  								items:[me.complaintReportPanel]
  							}
					];
				},
				listeners:{
					hide:function(){
						document.body.scrollLeft=0;
						document.body.scrollTop=0;
						document.getElementsByTagName("html")[0].style.overflowY="hidden";
						document.getElementsByTagName("html")[0].style.overflowX="hidden";
						viewport.doLayout();
					}
				}
			});
			win=Ext.getCmp("ComplaintReportWindowId");//获取win
			if(!win){
				win=Ext.create('ComplaintReportWindow',{id:'ComplaintReportWindowId'});
			}
		

			var chooseSelModelReturn=Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE',id:'chooseSelModelReturnId',allowDeselect:true});
			
			/**
			 * 工单上报查询结果
			 */
			Ext.define('ReportReturnProcessResultGrid', {
				extend : 'SearchGridPanel',
				parent : null,
				id:'ReportReturnProcessResultGridId',
				reportReturnProcessData : null,// 数据Data
				selModel : chooseSelModelReturn,
				initComponent : function() {
					
					var me = this;
					me.store = me.reportReturnProcessData
							.initReportReturnProcessSearchStore(me.beforeLoadScatterFn);
					me.dockedItems = me.getMyDockedItems();
					me.columns = me.getColumns();
					this.callParent();
				},
				getColumns : function() {
					var me = this;
					return [
							{xtype:'rownumberer',header:i18n('i18n.complaint.serial_number'),width:40},
							{
								header : i18n('i18n.complaint.comp_compman'),
								dataIndex : 'compman',
								renderer:function(value){
				  					return '<a href="javascript:void(0);">'+value+'</a>';
				  				}
							},{dataIndex:'businessModel',header:i18n('i18n.complaint.businessType'),
								renderer:function(value){
									return rendererDictionary(value,DataDictionary.COMPLAINT_BUSINESS_MODEL);
								}
							},{
								header : i18n('i18n.complaint.comp_bill'),
								dataIndex : 'bill'
							}, {
								header : i18n('i18n.complaint.comp_dealbill'),
								dataIndex : 'dealbill'
							}, {
								header : i18n('i18n.complaint.comp_recomcode'),
								dataIndex : 'recomcode',
								renderer:function(value){
				  					return '<a href="javascript:void(0);">'+value+'</a>';
				  				}
							}, {
								header : i18n('i18n.complaint.comp_reportType'),
								dataIndex : 'reporttype',
								renderer:function(value){
				  					return rendererDictionary(value,DataDictionary.REPORT_TYPE);
				  				}
							}, {
								header : i18n('i18n.complaint.comp_complaincust'),
								dataIndex : 'complaincust'
							}, {
								header : i18n('i18n.complaint.comp_pririty'),
								dataIndex : 'pririty',
								renderer:function(value){
				  					return rendererDictionary(value,DataDictionary.PRIORITY_RATING);
				  				}
							}, {
								header : i18n('i18n.complaint.comp_tel'),
								dataIndex : 'tel'
							}, {
								header : i18n('i18n.complaint.comp_prostatus'),
								dataIndex : 'prostatus',
								renderer:function(value){
				  					return rendererDictionary(value,DataDictionary.COMPLAINT_PROCESS_STATUS);
				  				}
							}, {
								header : i18n('i18n.complaintReportReturnProcess.dealStatus'),
								dataIndex : 'dealstatus',
								renderer:function(value){
				  					return rendererDictionary(value,DataDictionary.RESOLVE_CASE);
				  				}
							}, {
								header : i18n('i18n.complaintReportReturnProcess.returnman'),
								dataIndex : 'returnman'
							}, {
								header : i18n('i18n.complaintReportReturnProcess.returntime'),
								dataIndex : 'returntime',
								renderer:function(value) {
									if(value != null){
										return Ext.Date.format(new Date(value), 'Y-m-d H:i');
									}else{
										return null;
									}
								}
							}];
				},
				// 分页条
				getMyDockedItems : function() {
					var me = this;
					return [{
								xtype : 'pagingtoolbar',
								plugins : [Ext.create('Ext.ux.PageComboResizer')],
								store : me.store,
								dock : 'bottom',
								displayInfo : true
							}];
				},
				// beforeLoad方法
				beforeLoadScatterFn : function(store, operation, eOpts) {
					var searchConditionForm = Ext.getCmp('reportReturnProcessConditionFormId');
					if (searchConditionForm != null) {
						searchConditionForm.getForm()
								.updateRecord(searchConditionForm.record);
						// 设置请求参数
						var searchCustCondition = searchConditionForm.record.data;
						var searchParams = {
						};
						Ext.apply(operation, {
									params : searchParams
								});
					}
				}
			});
			
			Ext.define('LeftModifyButtonPanel',
				{	
				extend:'NormalButtonPanel', 
				items:null,
				initComponent:function(){
					this.items = this.getItems();	
					this.callParent();	
				},
				getItems:function(){
					var me = this;	
					return [{
						xtype:'leftbuttonpanel',
						items:[{xtype:'button',text:i18n('i18n.comp.btn.update'),handler:function(obj){
							processingMask.show();//显示进度条信息
							
							var sm = Ext.getCmp('ReportReturnProcessResultGridId').getSelectionModel();
							if (sm.getSelection().length > 0) {
								this.action = 'update';
								var record = sm.getSelection()[0];
								
								//执行成功
								var successFn = function(response){
									processingMask.hide();//隐藏进度条信息
									var complaintData = {
										complaint:response.complaint, //客户工单信息
										otherComplint:response.otherComplint
									};
									win.show();
									document.getElementsByTagName("html")[0].style.overflowY="auto";
									document.getElementsByTagName("html")[0].style.overflowX="auto";
									viewport.doLayout();
									
									Ext.getCmp('ComplaintReportResultGridId').store.removeAll();	            			
		      						Ext.getCmp('complaintReportConditionFormId').getForm().reset();
							        Ext.getCmp("reporttime").setValue(new Date());
							        Ext.getCmp("reportName").setValue(userName);//用户name
									Ext.getCmp("reportId").setValue(userId);//用户Id		      	
									var paramsData = new ComplaintReportModel(complaintData.complaint);
									var compReportFrom = Ext.getCmp('complaintReportConditionFormId');
									compReportFrom.getForm().loadRecord(paramsData)
									
									//设置 GO 按钮造作
									compReportFrom.passReceiverMemberId = paramsData.get('relatcusid');
									compReportFrom.passShipperMemberId = paramsData.get('complainid');
					
									
									//设置最终反馈的值
									if(complaintData.complaint.feedback=='1'){
										Ext.getCmp('chkFeedBackId').setValue(true);
									}else{
										Ext.getCmp('chkFeedBackId').setValue(false);
									}
									selectfid=null;
									currentRecord=null;
									//重置处理记录
									Ext.getCmp("ProcessRecordPanelId").processRecordGrid.store.removeAll();
									Ext.getCmp("ProcessRecordPanelId").processRecordGrid.store.load({params:{'complaintSearchCondition.fid':complaintData.complaint.fid}});//加载已经的处理记录
								}
								
								//执行失败
								var failFn = function(){
									processingMask.hide();//隐藏进度条信息
									MessageUtil.showErrorMes(i18n('i18n.alert_message_dataNotExist'));
								}
								var params={};
								params.fid=record.data.fid;
								DpUtil.requestJsonAjax('searchComplaintAndOther.action',params,successFn,failFn);
								
							} else {
								processingMask.hide();//隐藏进度条信息
								MessageUtil.showMessage(i18n('i18n.complaintReportReturnProcess.noRecord'));
							}
						}}]
					}]
			}});
			
			viewport =Ext.create('Ext.container.Viewport', {
						layout : 'border',autoScroll:true,
						items : [
						Ext.create('LeftModifyButtonPanel',{region:'north'})
						,Ext.create('reportReturnProcessPanel', {region:'center',
										'reportReturnProcessData' : reportReturnProcessDataControl
									})
					   ]
							
		    });
		    //页面底部加
			viewport.setAutoScroll(false);
			viewport.doLayout();
});
