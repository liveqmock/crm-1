var complaintReportDataControl = (CONFIG.get("TEST"))
		? new ComplaintReportDataTest()
		: new ComplaintReportData();

var userId=null;
var userName=null;
var selectfid=null;
var currentRecord=null;
var isBillGetCustomerInfo=false;
var canSearch=true;
Ext.define('complaintReportPanel', {
			extend : 'BasicPanel',
			parent : null,
			autoScroll : true,
			complaintReportConditionForm : null,// 查询条件
			complaintReportResultGrid : null, // 查询结果
			complaintReportData : null,// 数据Data
			complaintData:null,
			processRecordPanel:null,//处理结果
			isHiddenProcessRecordPanel:true,//是否隐藏处理结果grid
			operate:null,
			layout : {
				type : 'vbox',
				align : 'stretch'
			},
			items : null,
			initComponent : function() {
				var me = this;
				var record = new ComplaintReportModel();
				me.complaintReportConditionForm = Ext.create(
						'ComplaintReportConditionForm', {
							'parent' : me,
							'record' : record,
							'complaintReportData' : me.complaintReportData
							,height:225
						});
				var gridCls='searchresultgrid';
				if(me.operate=='returnProcess'){
					gridCls="popup_grid";
				}						
				me.complaintReportResultGrid = Ext.create(
						'ComplaintReportResultGrid', {
							id:'ComplaintReportResultGridId',
							'parent' : me,
							'cls':gridCls,							
							'complaintReportData' : me.complaintReportData
						});
				
						
			   me.complaintReportResultGrid.on('cellclick', function(thiz, row, col, record) {
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
				var items= [
					me.complaintReportResultGrid,
					me.complaintReportConditionForm
				];
				//若为上报退回 则显示退回记录
				if(me.operate=='returnProcess'){
					me.processRecordPanel = Ext.create('ProcessRecordPanel',{
						'complaint':me.complaintData,
						id:'ProcessRecordPanelId'
					});
					items.push(me.processRecordPanel);
				}
				
				items.push(
					{
						xtype:'poprightbuttonpanel',items:[
							{
								xtype:'button',
								text:i18n('i18n.complaint.submit'),
								scope:me,
								handler: me.btnSubmitComplaint
							},{
								xtype:'button',
								text:i18n('i18n.complaintReport.btn_reset'),
								scope:me,
								handler:me.resetComplaint
							},{
								xtype:'button',text:i18n('i18n.complaintReport.btn_close'),id:'btnCloseId',
								hidden:!(me.operate=='returnProcess'),
								scope:me,handler:function(){win.hide();}
							}
					]
				});
				me.items = items;
				this.callParent();
				if(me.complaintData!=null){//用于弹窗显示已有数据
					var paramsData = new ComplaintReportModel(me.complaintData);
					me.complaintReportConditionForm.loadRecord(paramsData)
					
					me.complaintReportConditionForm.passReceiverMemberId = me.complaintData.relatcusid;
					me.complaintReportConditionForm.passShipperMemberId = me.complaintData.complainid;
					//设置最终反馈的值
					if(me.complaintData.feedback=='1'){
						Ext.getCmp('chkFeedBackId').setValue(true);
					}else{
						Ext.getCmp('chkFeedBackId').setValue(false);
					}
					//Ext.getCmp("compmanId").setValue("SHIPMAN");
				}else{
					me.complaintReportConditionForm.loadRecord(record);
					
					me.complaintReportConditionForm.passReceiverMemberId = record.get('relatcusid');
					me.complaintReportConditionForm.passShipperMemberId = record.get('complainid');
				}
			},
			//提交按钮事件
			btnSubmitComplaint:function(){
				processingMask.show();//显示进度条信息
				var me=this;
				
				var billValue=Ext.getCmp('billId').getValue();
				if(null!=billValue && ""!=billValue){
					var companType=Ext.getCmp('compmanId').getValue();
					if(Ext.isEmpty(companType)){
						processingMask.hide();//隐藏进度条信息
						MessageUtil.showMessage(i18n('i18n.complaintReport.choise_compan_Type'));
						return;	
					}
				}
				
				//空的Model对象
				var record = new ComplaintReportModel();
				if(Ext.getCmp('complaintReportConditionFormId').getForm().isValid()){
					//校验业务类型是否为空
					if(Ext.isEmpty(Ext.getCmp('ReportFormBusinessType').getValue())){
						MessageUtil.showMessage(i18n('i18n.complaint.error.choseBusinessType'));
						processingMask.hide()
						return;
					}
					//更新form表单最新数据
	            	Ext.getCmp('complaintReportConditionFormId').getForm().updateRecord(record);
	        		//设置请求参数
	        		var params = {};
	        		var feedBack=Ext.getCmp('chkFeedBackId').getValue();
	        		if(feedBack){
	        			record.data.feedback='1';
	        		}else{
	        			record.data.feedback='0';
	        		}
	        		params.complaint = record.data;
	        		params.fid=selectfid;
	        		//工单上报添加服务编码
	        		if(typeof(serviceId)!='undefined' && !DpUtil.isEmpty(serviceId)){
						params.complaint.serviceId=serviceId;	        			
	        		}
	        		//CC 上报来源
	        		if(typeof(channel)!='undefined' && !DpUtil.isEmpty(channel)){
						params.complaint.channel = channel;	        			
	        		}
	        		var submitSuccessFn = function(response){
	        			processingMask.hide();//隐藏进度条信息
		               	if(response && response.success){
							if(me.operate=="returnProcess"){
								//Ext.getCmp('complaintReportWindowId').close();
								win.hide();
								// 调用客户工单详情 弹出框
								MessageUtil.showInfoMes(i18n('i18n.complaintReport.save_success'));
								Ext.getCmp('ReportReturnProcessResultGridId').store.loadPage(1);
			           		}else{
			           			MessageUtil.showInfoMes(i18n('i18n.complaintReport.save_success'));
			          			//清空数据
								me.resetComplaint();
			           		}
		           		}
	                };
					 var submitFailFn = function(response){
					 	processingMask.hide();//隐藏进度条信息
					     MessageUtil.showErrorMes(response.message,function(){
//					    		if(null!=currentRecord){
//										chooseSelModel.deselect(currentRecord);						
//								}
					    	}
					    );
					 };
					//调用后台进行数据提交
					 me.complaintReportData.submitComplaint(params,submitSuccessFn,submitFailFn,me.operate);
			 	}else{
			 		processingMask.hide();//隐藏进度条信息
			 	}
			},
			//重置查询条件
			resetComplaint:function(){
				var me = this;
				Ext.getCmp('reportTypeId').setReadOnly(false);
				Ext.getCmp('reporttypeComplaint').setValue(false);
				Ext.getCmp('reporttypeUnusual').setValue(false);				
				Ext.getCmp('ComplaintReportResultGridId').store.removeAll();
				Ext.getCmp('ReportFormBusinessType').setReadOnly(false);
				Ext.getCmp('ReportFormBusinessType').removeCls('readonly');
				var fid=Ext.getCmp('fromFid').getValue();
		        Ext.getCmp('complaintReportConditionFormId').getForm().reset();
				Ext.getCmp('fromFid').setValue(fid);
				
				var complaintReportConditionForm = Ext.getCmp('complaintReportConditionFormId').getForm();
//				complaintReportConditionForm.getFields().each(function(field){
//					if(field.getName()!='fid'){
//						field.setValue(null);
//					}
//				});
//		        Ext.getCmp("reporttime").setValue(new Date());
		        Ext.getCmp("reportName").setValue(userName);//用户name
				Ext.getCmp("reportId").setValue(userId);//用户Id
//				Ext.getCmp("compmanId").setValue("SHIPMAN");
			 	Ext.getCmp("tilimitcycle").setValue("MINUTE");
			 	Ext.getCmp("pririty").setValue("NORMAL");
		 		Ext.getCmp("workSingleSource").setValue("TELEPHONE");
				selectfid=null;
				currentRecord=null;
				isBillGetCustomerInfo=false;
				me.complaintReportConditionForm.receiverMember=null;
				me.complaintReportConditionForm.shipperMember=null;
				me.complaintReportConditionForm.passReceiverMemberId=null;
				me.complaintReportConditionForm.passShipperMemberId=null;
			}
		});

/**
 * 查询条件
 */
Ext.define('ComplaintReportConditionForm', {
			name : 'complaintReportConditionFormId',
			id : 'complaintReportConditionFormId',
			extend : 'TitleFormPanel',
			items : null,
			receiverMember:null,
			shipperMember:null,
			passReceiverMemberId:null,
			passShipperMemberId:null,			
			initComponent : function() {
				this.items = this.getItems();
				this.callParent();
			},
			getItems : function() {
				var me = this;
				return [{
						xtype:'basicfiledset',
							title : i18n('i18n.complaintReport.title'),
							layout : {
								type : 'table',
								columns : 6
							},
							defaultType : 'textfield',
							defaults : {
								labelWidth : 65,
								width : 160
							},
							items : [{
										fieldLabel : i18n('i18n.complaint.comp_bill'),
										name:'bill',
										id:'billId',
										maxLength:20,
										enableKeyEvents:true,
										listeners:{
											scope:me,
											keypress:me.enterBillEvent
										}
									}, {
										xtype : 'button',
										algin : 'left',
										labelWidth : 10,
										width : 45,
										margin:'0 0 5 5',
										text : 'GO',
										handler:function(){
											var billValue=Ext.getCmp('billId').getValue();
											if(!DpUtil.isEmpty(billValue) && isNaN(billValue.substr(0,1))){
												openOrderViewWindow(billValue);
											}else{
												MessageUtil.showMessage(i18n('i18n.complaintReport.validate.empty_complaintBillAndMustOrder'));
											}
										}
									}, {
										fieldLabel : i18n('i18n.complaint.comp_tel')+'<span style="color:red;">*</span>',
										name:'tel',
										id:'telId',
										allowBlank : false,
										maxLength:40,
										width : 195,
										blankText : i18n('i18n.complaintReport.validate.empty_tel'),
										enableKeyEvents:true,
										listeners:{
											scope:me,
											keypress:me.enterPhoneEvent
										}
									}, {
										fieldLabel : i18n('i18n.complaint.comp_compman')+'<span style="color:red;">*</span>',
										xtype: 'combobox',
										width : 150,
										name:'sendorreceive',
										id:'compmanId',
										mode:           'local',
							            triggerAction:  'all',
							            forceSelection:true,
							            //editable:false,//不能编辑
										displayField:'codeDesc',
									    valueField:'code',
										//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
										store:getDataDictionaryByName(DataDictionary,'CALLER_TYPE'),
										listeners:{
											scope:me,
											select:me.changeCompTypeEvent,
											change:function(combo){
												if(Ext.isEmpty(combo.getValue())){
													combo.setValue("");
												}
										    }
										}
									}, {
										labelWidth : 10,
										algin : 'left',
										name:'compman',
										id:'compmanOutId',
										maxLength:20,
										allowBlank : false,
										blankText : i18n('i18n.complaintReport.validate.empty_compman'),
										width : 60
									},
									{
				     					xtype:'radiogroup',
				     					fieldLabel:i18n('i18n.complaint.comp_reportType')+'<span style="color:red;">*</span>',
				     					allowBlank : false,
				     					blankText : i18n('i18n.complaintReport.validate.empty_reportType'),
				     					id:'reportTypeId',
//				     					labelWidth:70,
				     					width : 165,
				     					colspan:2,
				     					vertical:true,        //控制复选框按列进行显示         
				     					items:[{
				     						xtype : 'radio',
				     						boxLabel : i18n('i18n.complaintReport.complaint'),
				     						id:'reporttypeComplaint',
				     						name:'reporttype',
				     						inputValue:'COMPLAINT'
				     					}, {
				     						xtype : 'radio',
				     						boxLabel : i18n('i18n.complaintReport.unusual'),
				     						id:'reporttypeUnusual',
				     						name:'reporttype',
				     						inputValue:'UNUSUAL'
				     					}],
				     					listeners:{
											scope:me,
											change:function(t){
												if(null!=currentRecord){
														chooseSelModel.deselect(currentRecord);						
												}
										    }
										}
				                     },
				                     {
										fieldLabel : i18n('i18n.complaint.comp_complaincust'),
										name:'complaincust',
										id:'complaincust',
										readOnly:true
									}, {
										xtype : 'button',
										algin : 'center',
										labelWidth : 10,
										width : 45,
										margin:'0 0 5 5',
										text : 'GO',
										handler:function(){
											if(DpUtil.isEmpty(me.passShipperMemberId) || '0' == me.passShipperMemberId){
												return;
											}
											processingMask.show();//显示进度条信息
											CustviewUtil.openMemberWindow(me.passShipperMemberId);
							   				processingMask.hide();//显示进度条信息
										}
									}, {
										fieldLabel : i18n('i18n.complaint.comp_custLevel'),
										name:'custlevel',
										width : 195,
										id:'custlevel',
										readOnly:true,
										cls:'readonly',
										xtype : 'combobox',
										mode:           'local',
							            triggerAction:  'all',
							            forceSelection:true,
							            editable:false,//不能编辑
										displayField:'codeDesc',
									    valueField:'code',
										//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
										store:getDataDictionaryByName(DataDictionary,'MEMBER_GRADE')																
									}, {
										fieldLabel : i18n('i18n.complaint.comp_custType'),
										name:'custtype',
										id:'custtype',
										width : 210,
										colspan : 2,
										readOnly:true,
										cls:'readonly',
										xtype : 'combobox',
										mode:           'local',
							            triggerAction:  'all',
							            forceSelection:true,
							            editable:false,//不能编辑
										displayField:'codeDesc',
									    valueField:'code',
										//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
										store:getDataDictionaryByName(DataDictionary,'CUSTOMER_TYPE')										
									}, {
										fieldLabel : i18n('i18n.complaint.comp_complaintSource')+'<span style="color:red;">*</span>',
										name:'complaintsource',
										id:'workSingleSource',
										allowBlank : false,
										blankText : i18n('i18n.complaintReport.validate.empty_complaintsource'),
										width : 155,
										colspan : 2,
										xtype : 'combobox',
										mode:           'local',
							            triggerAction:  'all',
							            forceSelection:true,
							            editable:false,//不能编辑
										displayField:'codeDesc',
									    valueField:'code',
										//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
										store:getDataDictionaryByName(DataDictionary,'WORK_SINGLE_SOURCE')										
									},{
										fieldLabel : i18n('i18n.complaint.comp_relatcus'),
										name:'relatcus',
										id:'relatcus',
										readOnly:true
									}, {
										xtype : 'button',
										algin : 'left',
										labelWidth : 10,
										width : 45,
										margin:'0 0 5 5',
										text : 'GO',
										handler:function(){
											if(DpUtil.isEmpty(me.passReceiverMemberId) || '0' == me.passShipperMemberId){
												return;
											}
											processingMask.show();//显示进度条信息
											CustviewUtil.openMemberWindow(me.passReceiverMemberId);
							   				processingMask.hide();//显示进度条信息
										}
										
									}, {
										fieldLabel : i18n('i18n.complaint.comp_custLevel'),
										name:'relatcuslevel',
										id:'relatcusLevel',
										width : 195,										
										readOnly:true,
										cls:'readonly',
										xtype : 'combobox',
										mode:           'local',
							            triggerAction:  'all',
							            forceSelection:true,
							            editable:false,//不能编辑
										displayField:'codeDesc',
									    valueField:'code',
										//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
										store:getDataDictionaryByName(DataDictionary,'MEMBER_GRADE')												
									}, {
										xtype:'numberfield',
										hideTrigger:true,
										allowDecimals:true,
										fieldLabel : i18n('i18n.complaint.comp_timeLimit'),
										width : 150,
										maxLength:10,
//										allowBlank : false,
//										blankText : i18n('i18n.complaintReport.validate.empty_timelimit'),										
										name:'timelimit'
									}, {
										xtype : 'combobox',
										width : 60,
										mode:           'local',
							            triggerAction:  'all',
							            name:'tilimitcycle',
							            id:'tilimitcycle',
							            forceSelection:true,
							            allowBlank : false,
										blankText : i18n('i18n.complaintReport.validate.empty_tilimitcycle'),
							            editable:false,//不能编辑
										displayField:'codeDesc',
									    valueField:'code',
										//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
										store:getDataDictionaryByName(DataDictionary,'EXPECTED_TIME_LIMIT')
									}, {
										fieldLabel : i18n('i18n.complaint.comp_pririty')+'<span style="color:red;">*</span>',
										name:'pririty',
										id:'pririty',
										xtype : 'combobox',
										allowBlank : false,
										blankText : i18n('i18n.complaintReport.validate.empty_pririty'),
//										labelWidth : 70,
										width : 155,
										colspan : 2,
										mode:           'local',
							            triggerAction:  'all',
							            forceSelection:true,
							            editable:false,//不能编辑
										displayField:'codeDesc',
									    valueField:'code',
										//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
										store:getDataDictionaryByName(DataDictionary,'PRIORITY_RATING')													
									}, {
										fieldLabel : i18n('i18n.complaint.comp_reportContent')+'<span style="color:red;">*</span>',
										name:'reportcontent',
										xtype : 'textarea',
										maxLength:400,
										allowBlank : false,
										blankText : i18n('i18n.complaintReport.validate.empty_reportcontent'),
										width : 405,
										colspan : 3
									}, {
										fieldLabel : i18n('i18n.complaint.comp_custRequire')+'<span style="color:red;">*</span>',
										name:'custrequire',
										xtype : 'textarea',
										allowBlank : false,
										maxLength:200,
										blankText : i18n('i18n.complaintReport.validate.empty_custrequire'),
										width : 365,
										colspan : 4
									},
									{
										fieldLabel : i18n('i18n.complaint.comp_recomcode'),
										name:'recomcode',
										id:'recomcode',
										width : 210,//******
										colspan : 2,//******										
										readOnly:true
									},{
										xtype:'combo',
										name:'businessModel',
										id:'ReportFormBusinessType',
										displayField:'codeDesc',
									    valueField:'code',
									    queryMode:'local',
									    fieldLabel:i18n('i18n.complaint.businessType'),
									    editable:false,
									    width:195,
									    store:getDataDictionaryByName(DataDictionary,'COMPLAINT_BUSINESS_MODEL')
									},{
										fieldLabel : i18n('i18n.complaint.comp_reporter'),
										name:'reporter',
										colspan : 2,
										width : 210,										
										readOnly:true,
										id:'reportName'
									},{ 
							             xtype: 'checkbox',
							             fieldLabel: i18n('i18n.complaint.comp_feedback'),
							             id:'chkFeedBackId'
									}
									,{
				                     	fieldLabel : '来电客户Id',
				                     	xtype: 'hiddenfield',
										name:'complainid',
										id:'complainid',
										hidden:true
				                     }, {
										fieldLabel : '相关客户Id*',
										xtype: 'hiddenfield',
										name:'relatcusid',
										id:'relatcusid',
										hidden:true
									},{
										fieldLabel : '相关客户类型*',
										xtype: 'hiddenfield',
										id:'relatCustType',
										name:'relatcustype',
										hidden:true
									},{
										fieldLabel : '上报人ID',
										xtype: 'hiddenfield',
										id:'reportId',
										name:'reporterid',
										hidden:true
									},{
										fieldLabel : '重复工单绑定码',
										xtype: 'hiddenfield',
										id:'rebindno',
										name:'rebindno',
										hidden:true
									},{
										fieldLabel : '工单号',
										xtype: 'hiddenfield',
										name:'fid',
										id:'fromFid',
										hidden:true									
									},{
										fieldLabel : '参数联系电话',
										xtype: 'hiddenfield',
										id:'paramPhone',
										hidden:true	
									},{
										fieldLabel : '参数凭证号',
										xtype: 'hiddenfield',
										id:'paramBill',
										hidden:true
									}, {
										fieldLabel : i18n('i18n.complaint.comp_reporttime'),
										id:'reporttime',
										name:'reporttime',
										width : 200,
//										colspan : 2,										
										xtype : 'datetimefield',
            							format: 'Y-m-d H:i',
										hidden:true
									}]

						}];
			},
			//Enter键按下时凭证号查询
			enterBillEvent:function(field,event){
				if(event.getKey() == Ext.EventObject.ENTER){
					if(!canSearch){
						MessageUtil.showInfoMes(i18n('i18n.complaint.waitSeconds'));
						return ;
					}
					//清空重复工单码,选中的那条fid清空
					if(null!=currentRecord){
						chooseSelModel.deselect(currentRecord);						
					}
					Ext.getCmp('ReportFormBusinessType').setReadOnly(false);
					Ext.getCmp('ReportFormBusinessType').removeCls('readonly');
					Ext.getCmp('ReportFormBusinessType').setValue('');
					if(Ext.String.trim(field.getValue())!=""){
						var me = this;
						Ext.getCmp('paramPhone').setValue("");
		  				Ext.getCmp('paramBill').setValue(field.getValue());
		  				me.complaintReportData.getComplaintReportSearchStore().load();
						
						var params = {};
						var searchModel = Ext.create('SearchComplaintReportConditionModel');
						searchModel.set('bill',field.getValue());
//						searchModel.set('start',0);
//						searchModel.set('limit',5);
						params.complaintSearchCondition = searchModel.data;
						
						// 查询成功回调函数
						var searchSuccess = function(response) {
							isBillGetCustomerInfo=true;
							var complaintList=response.complaintList;
							var totalCount=response.totalCount;
							me.shipperMember=response.shipperMember;
							me.receiverMember=response.receiverMember;
							
							if(!Ext.isEmpty(response.shipperMember)){
								me.passShipperMemberId=response.shipperMember.id;
							}
							if(!Ext.isEmpty(response.receiverMember)){
								me.passReceiverMemberId=response.receiverMember.id;
							}
//							me.complaintReportData.getComplaintReportSearchStore().loadData(complaintList);
//							me.complaintReportData.getComplaintReportSearchStore().loadPage(totalCount);
							if(me.shipperMember!=null){//发货人
								Ext.getCmp("complainid").setValue(me.shipperMember.id);
								Ext.getCmp("complaincust").setValue(me.shipperMember.custName);
								Ext.getCmp("custlevel").setValue(me.shipperMember.degree);
								Ext.getCmp("custtype").setValue(me.shipperMember.custType);
							}else{
								Ext.getCmp("complainid").setValue("");
								Ext.getCmp("complaincust").setValue("");
								Ext.getCmp("custlevel").setValue("");
								Ext.getCmp("custtype").setValue("");
							}
							if(me.receiverMember!=null){//收货人
								Ext.getCmp("relatcusid").setValue(me.receiverMember.id);
								Ext.getCmp("relatcus").setValue(me.receiverMember.custName);
								Ext.getCmp("relatcusLevel").setValue(me.receiverMember.degree);
								Ext.getCmp("relatCustType").setValue(me.receiverMember.custType);
							}else{
								Ext.getCmp("relatcusid").setValue("");
								Ext.getCmp("relatcus").setValue("");
								Ext.getCmp("relatcusLevel").setValue("");
								Ext.getCmp("relatCustType").setValue("");
							}
							if(!Ext.isEmpty(response.businessModel)){
								Ext.getCmp('ReportFormBusinessType').setReadOnly(true);
								Ext.getCmp('ReportFormBusinessType').addCls('readonly');
								Ext.getCmp('ReportFormBusinessType').setValue(response.businessModel);
							}
						};
						// 查询失败调函数
						var searchFail = function(response) {
							MessageUtil.showErrorMes(response.message);
						};
						
						//查询之前清空
						var compReport = Ext.getCmp('complaintReportConditionFormId');
						compReport.passReceiverMemberId = null;
						compReport.passShipperMemberId = null;
						
						var searchMembersByOWNumUrl='searchMembersByOWNum.action';
						DpUtil.requestJsonAjax(searchMembersByOWNumUrl,params,searchSuccess,searchFail);
					}
					canSearch=false;
					setTimeout(function(){
						canSearch=true;
					},5000);
				}
			},
			//Enter键按下时手机号查询
			enterPhoneEvent:function(field,event){
				if(event.getKey() == Ext.EventObject.ENTER){
					if(Ext.String.trim(field.getValue())!=""){
						var me = this;
						Ext.getCmp('paramPhone').setValue(field.getValue());
		  				Ext.getCmp('paramBill').setValue("");
		  				me.complaintReportData.getComplaintReportSearchStore().load();
		  				
						var params = {};
						var searchModel = Ext.create('SearchComplaintReportConditionModel');
						searchModel.set('phone',field.getValue());
//						searchModel.set('start',0);
//						searchModel.set('limit',5);
						params.complaintSearchCondition = searchModel.data;
						
						// 查询成功回调函数
						var searchSuccess = function(response) {
							//判断是否是快递;
							var businessType='';
							if(Ext.isEmpty(businessType)){
								Ext.getCmp('ReportFormBusinessType').setReadOnly(false);
								Ext.getCmp('ReportFormBusinessType').removeCls('readonly');
							}else{
								if(businessType=='EXPRESS'){
									Ext.getCmp('ReportFormBusinessType').setValue('EXPRESS');
								}else{
									Ext.getCmp('ReportFormBusinessType').setValue('UNEXPRESS');
								}
								Ext.getCmp('ReportFormBusinessType').setReadOnly(true);
								Ext.getCmp('ReportFormBusinessType').addCls('readonly');
							}
							isBillGetCustomerInfo=false;
							
							//重构 联系电话 回车查询 GO 按钮数据
							if(!Ext.isEmpty(response.shipperMember)){
								me.passShipperMemberId = response.shipperMember.id;
							}else{
								me.passShipperMemberId = null;
							}
							if(!Ext.isEmpty(response.receiverMember)){
								me.passReceiverMemberId = response.receiverMember.id;
							}else{
								me.passReceiverMemberId = null;
							}
							
							if(response.shipperMember!=null){//发货人
								Ext.getCmp("complainid").setValue(response.shipperMember.id);
								Ext.getCmp("complaincust").setValue(response.shipperMember.custName);
								Ext.getCmp("custlevel").setValue(response.shipperMember.degree);
								Ext.getCmp("custtype").setValue(response.shipperMember.custType);
							}else{
								Ext.getCmp("complainid").setValue("");
								Ext.getCmp("complaincust").setValue("");
								Ext.getCmp("custlevel").setValue("");
								Ext.getCmp("custtype").setValue("");
							}
							if(response.receiverMember!=null){//收货人
								Ext.getCmp("relatcusid").setValue(response.receiverMember.id);
								Ext.getCmp("relatcus").setValue(response.receiverMember.custName);
								Ext.getCmp("relatcusLevel").setValue(response.receiverMember.degree);
								Ext.getCmp("relatCustType").setValue(response.receiverMember.custType);
							}else{
								Ext.getCmp("relatcusid").setValue("");
								Ext.getCmp("relatcus").setValue("");
								Ext.getCmp("relatcusLevel").setValue("");
								Ext.getCmp("relatCustType").setValue("");
							}
						};
						// 查询失败调函数
						var searchFail = function(response) {
							MessageUtil.showErrorMes(response.message);
						};
						//带出客户信息
						var searchMembersByOWNumUrl='getContractByPhone.action';
						
						DpUtil.requestJsonAjax(searchMembersByOWNumUrl,params,searchSuccess,searchFail);
					}
				}
			},
			//来电人类型改变时的值（ 发货人与收货人交换）
			changeCompTypeEvent:function(field,event){
				var me = this;
				var basicForm = me.getForm();
				if(isBillGetCustomerInfo){
					if(field.getValue() == 'SHIPMAN'){//发货人
						if(!Ext.isEmpty(me.shipperMember)){
							me.passShipperMemberId = me.shipperMember.id;
						}
						if(!Ext.isEmpty(me.receiverMember)){
							me.passReceiverMemberId = me.receiverMember.id;
						}
						if(null!=me.shipperMember){
							Ext.getCmp("complainid").setValue(me.shipperMember.id);
							Ext.getCmp("complaincust").setValue(me.shipperMember.custName);
							Ext.getCmp("custlevel").setValue(me.shipperMember.degree);
							Ext.getCmp("custtype").setValue(me.shipperMember.custType);
						}else{
							Ext.getCmp("complainid").setValue("");
						    Ext.getCmp("complaincust").setValue("");
							Ext.getCmp("custlevel").setValue("");
							Ext.getCmp("custtype").setValue("");
						}
						if(null!=me.receiverMember){
							Ext.getCmp("relatcusid").setValue(me.receiverMember.id);
							Ext.getCmp("relatcus").setValue(me.receiverMember.custName);
							Ext.getCmp("relatcusLevel").setValue(me.receiverMember.degree);
							Ext.getCmp("relatCustType").setValue(me.receiverMember.custType);					
						}else{
							Ext.getCmp("relatcusid").setValue("");
							Ext.getCmp("relatcus").setValue("");
							Ext.getCmp("relatcusLevel").setValue("");
							Ext.getCmp("relatCustType").setValue("");
						}
					}else if(field.getValue() == 'RECEIVEMAN'){//收货人
						if(!Ext.isEmpty(me.shipperMember)){
							me.passShipperMemberId = me.shipperMember.id;
						}
						if(!Ext.isEmpty(me.receiverMember)){
							me.passReceiverMemberId = me.receiverMember.id;
						}
										
						if(null!=me.receiverMember){
							Ext.getCmp("complainid").setValue(me.receiverMember.id);
							Ext.getCmp("complaincust").setValue(me.receiverMember.custName);
							Ext.getCmp("custlevel").setValue(me.receiverMember.degree);
							Ext.getCmp("custtype").setValue(me.receiverMember.custType);
						}else{
							Ext.getCmp("complainid").setValue("");
						    Ext.getCmp("complaincust").setValue("");
							Ext.getCmp("custlevel").setValue("");
							Ext.getCmp("custtype").setValue("");
						}
						if(null!=me.shipperMember){
							Ext.getCmp("relatcusid").setValue(me.shipperMember.id);
							Ext.getCmp("relatcus").setValue(me.shipperMember.custName);
							Ext.getCmp("relatcusLevel").setValue(me.shipperMember.degree);
							Ext.getCmp("relatCustType").setValue(me.shipperMember.custType);					
						}else{
							Ext.getCmp("relatcusid").setValue("");
							Ext.getCmp("relatcus").setValue("");
							Ext.getCmp("relatcusLevel").setValue("");
							Ext.getCmp("relatCustType").setValue("");
						}
					}
				}
			}
		});
		
			var chooseSelModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE',id:'chooseSelModelId',checkOnly:true,allowDeselect:true,
							listeners : {
								select : function(t,record) {
									var reportType=Ext.getCmp('reportTypeId').getValue().reporttype;
									if(DpUtil.isEmpty(reportType)){
										MessageUtil.showInfoMes(i18n('i18n.complaintReport.validate.empty_reportType'));
										t.deselect(record);
										return;
									}
									//如果类型不相等，不能绑定
									if(null==record.data.reporttype || reportType!=record.data.reporttype){
										MessageUtil.showInfoMes(i18n('i18n.complaintReport.validate.different_reporttype'));
										t.deselect(record);
										return;
									}
									currentRecord=record;
									
									var me = this;
									var params = {};
									var searchModel = Ext.create('ComplaintReportModel');
									selectfid=record.data.fid;
									searchModel.set('bill',Ext.getCmp('billId').getValue());
									searchModel.set('fid',record.data.fid);
									
									searchModel.set('reporttype',reportType);
									
									if(null==record.data.rebindno || ""==record.data.rebindno){//为空生成新的值
										params.complaint = searchModel.data;
										// 查询成功回调函数
										var searchSuccess = function(response) {
											//赋值重复工单
											Ext.getCmp("rebindno").setValue(response.repeatCode);
											Ext.getCmp('recomcode').setValue(response.repeatCode);	
										};
										// 查询失败调函数
										var searchFail = function(response) {
											MessageUtil.showErrorMes(response.message,function(){
												t.deselect(record);
											});
										};
										var getRepeatedCodeUrl='getRepeatedCode.action';
										DpUtil.requestJsonAjax(getRepeatedCodeUrl,params,searchSuccess,searchFail);			
									}else{//如果不为空直接赋值
										Ext.getCmp('recomcode').setValue(record.data.rebindno);	
										Ext.getCmp('rebindno').setValue(record.data.rebindno);
									}
								},
								deselect:function(t,record){
									selectfid="";
									Ext.getCmp("rebindno").setValue("");
									Ext.getCmp('recomcode').setValue("");
									currentRecord=null;
								}
							}});
			
			/**
			 * 工单上报查询结果
			 */
			Ext.define('ComplaintReportResultGrid', {
				extend : 'Ext.grid.Panel',
				parent : null,
				height:165,
				complaintReportData : null,// 数据Data
				selModel : chooseSelModel,
				initComponent : function() {
					
					var me = this;
					me.store = me.complaintReportData
							.initComplaintReportSearchStore(me.beforeLoadScatterFn);
					me.dockedItems = me.getMyDockedItems();
					me.columns = me.getColumns();
					this.callParent();
				},
				getColumns : function() {
					var me = this;
					return [
							{xtype:'rownumberer',header:i18n('i18n.complaint.serial_number'),width:40},
							{
								header : i18n('i18n.complaint.businessType'),
								dataIndex : 'businessModel',
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
								header : i18n('i18n.complaint.comp_compman'),
								dataIndex : 'compman',
								renderer:function(value){
				  					return '<a href="javascript:void(0);">'+value+'</a>';
				  				}
							}, {
								header : i18n('i18n.complaint.comp_pririty'),
								dataIndex : 'pririty',
								renderer:function(value){
				  					return rendererDictionary(value,DataDictionary.PRIORITY_RATING);
				  				}
							}, {
								header : i18n('i18n.complaint.bulletin.tel'),
								dataIndex : 'tel'
							}, {
								header : i18n('i18n.complaint.comp_prostatus'),
								dataIndex : 'prostatus',
								renderer:function(value){
				  					return rendererDictionary(value,DataDictionary.COMPLAINT_PROCESS_STATUS);
				  				}
							}, {
								header : i18n('i18n.complaint.comp_dealstatus'),
								dataIndex : 'dealstatus',
								renderer:function(value){
				  					return rendererDictionary(value,DataDictionary.RESOLVE_CASE);
				  				}
							}, {
								header : i18n('i18n.complaint.comp_reporter'),
								dataIndex : 'reporter'
							}, {
								header : i18n('i18n.complaint.comp_reporttime'),
								width:120,
								dataIndex : 'reporttime',
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
								store : me.store,
								dock : 'bottom',
								displayInfo : true
							}];
				},
				// beforeLoad方法
				beforeLoadScatterFn : function(store, operation, eOpts) {
					var searchConditionForm = Ext.getCmp('complaintReportConditionFormId');
					
					if(Ext.isEmpty(searchConditionForm)){return false;}
					searchConditionForm.getForm().updateRecord(searchConditionForm.record);
					
					// 设置请求参数
					var searchCustCondition = searchConditionForm.record.data;
					
					var paramPhone = Ext.getCmp('paramPhone').getValue();
					var paramBill = Ext.getCmp('paramBill').getValue();
					var fromFid = Ext.getCmp('fromFid').getValue();
					if(//查询条件必须存在一个
						Ext.isEmpty(paramPhone) && Ext.isEmpty(paramBill)
					){
						MessageUtil.showErrorMes(i18n('i18n.complaint.bill_phone_allIsNull'));
						return false;
					}
					var searchParams = {
						//电话号码
	  					'complaintSearchCondition.phone':paramPhone,
	  					'complaintSearchCondition.bill':paramBill,
	  					'complaintSearchCondition.fid':fromFid
					};
					Ext.apply(operation, {
						params : searchParams
					});
				}
			});
