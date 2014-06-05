/**
 * 客户工单之 通知对象可编辑列表
 */
Ext.define('DutyBulletinGrid',{
	extend:'PopupGridPanel',
	'columns':[
		{xtype:'rownumberer',header:i18n('i18n.duty.serial_number'),width:40},
		{header:i18n('i18n.duty.bulletin.name')/*姓名*/,width:140,dataIndex:'userName'},
		{header:i18n('i18n.duty.bulletin.position')/*职位*/,width:140,dataIndex:'userPosition'},
		{header:i18n('i18n.duty.bulletin.jobId')/*工号*/,width:140,dataIndex:'userNo'},
		{header:i18n('i18n.duty.bulletin.tel')/*联系方式*/,width:140,dataIndex:'userContact'},
		{header:i18n('i18n.duty.bulletin.deptName')/*部门*/,flex:1,dataIndex:'deptName'}
	]
	,store:Ext.create('DutyBulletinStore')
	,initComponent : function() {
		var me = this;
		if(Ext.isEmpty(me.selModel)){
			me.selModel = new Ext.selection.CheckboxModel();
		}
		if(!Ext.isEmpty(me.store)){me.store.removeAll(false);}
		this.callParent(arguments);
	}
});



/**
 * 通知对象增加、删除按钮Panel
 */
Ext.define('DutyDivideInforToBtnPanel',{
	extend:'TopPanel',items : null,
	initComponent : function() {
		this.items = this.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [{
			xtype:'titlepanel',flex:1,
			items:[{xtype:'displayfield',value:i18n('i18n.DutyAllocationOperationView.infoemUser')}]//通知对象
		},{
			xtype:'btnpanel',  
			defaultType:'button',
			items:[{//增加
				text:i18n('i18n.duty.add'),handler:function(){
					//增加按钮
					var me = this;
					var store = Ext.getCmp('dutyBulletinGridId').getStore();
					var removeSameBulletin = function(my_record){
						store.each(function(record){
							if(record.get('userName') == my_record.get('empName')){
								store.remove(record);
							}
						});
					}
					
					Ext.create('EmployeeLookUpWindow',{
						title:i18n('i18n.duty.Employee.win_title'/*员工选择*/),
						listeners:{
			    			select:function(win,record){
			    				var divideType = 'employee';
			    				//移除相同的通知对象
			    				removeSameBulletin(record);
		    					store.add({
									//'id':record.get('empId'),???为什么要设置它的ID呢？？
									'userNo':record.get('empCode'),
									'userName':record.get('empName'),
									'userPosition':record.get('position'),
									'userContact':record.get('mobilePhone'),
									'deptName':record.get('deptName')
								});
				        		win.close();
			    			}
			    		}
					}).show();
				}
			   },{//删除，质检组修改责任划分时不显示
				text:i18n('i18n.duty.delete'),
				handler:function(){
					//删除按钮
					var me = this;
					var grid = Ext.getCmp('dutyBulletinGridId');
					var selectionList = grid.getSelectionModel().getSelection();
					if(Ext.isEmpty(selectionList)){
						//MessageUtil.showMessage(i18n('i18n.DutyAllocationOperationView.pleaseSelectTodeleteData'));return;//请选择要删除的数据
						selectionList.push(grid.getStore().getAt(grid.getStore().getCount()-1));//删除最后一个
					}
					var panelStore = grid.getStore();
					for(var i=0;i<selectionList.length;i++){
						panelStore.remove(selectionList[i]);
					}
		      	}
			}]
		}]; 
	}
});

/**
 * 责任划分结果增加、删除按钮Panel
 */
Ext.define('DutyDivideAddAndDelPanel',{
	extend:'TopPanel',
	items : null,
	initComponent : function() {
		this.items = this.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [{
			xtype:'titlepanel',  
			flex:1,
			items:[{//责任划分结果
				xtype:'displayfield',value:i18n('i18n.Duty.DutyApproval.DutyAllocationResult')//责任划分结果
		    }]
		},{
			xtype:'btnpanel',  
			defaultType:'button',
			items:[{//增加
				text:i18n('i18n.duty.add'),id:'resultAddBtnId',
				handler:function(){
					//增加按钮
					var dutyAddResultWindowsId = Ext.getCmp('dutyAddResultWindowsId');
					if(Ext.isEmpty(dutyAddResultWindowsId)){
						dutyAddResultWindowsId = Ext.create('DutyAddResultWindows',{id:'dutyAddResultWindowsId'});
					}
					dutyAddResultWindowsId.show();
					dutyAddResultWindowsId.isUpdate = false;
				}
			   },
			   {//删除，质检组修改责任划分时不显示
				text:i18n('i18n.duty.delete'),handler:function(){
					//删除按钮
					var grid = Ext.getCmp('dutyEditableResultGridId');
					var selection = grid.getSelectionModel().getSelection();
					var store = grid.getStore();
					if(selection.length==0){//未选则的 删除最后一个
						selection[0] = store.getAt(store.getCount()-1);
					}
					var dutyAddResultWindowsId = Ext.getCmp('dutyAddResultWindowsId');
					
					Ext.each(selection,function(record){
						//删除通知
						grid.deleteBulletinByPerName(Ext.clone(record));
						store.remove(record);
					});
					if(store.getCount()< 10){//小于10 设置新增按钮可用
						Ext.getCmp('resultAddBtnId').setDisabled(false);
					}
		      	}
			}]
		}]; 
	}
});

/**
 * 工单责任划分页面整体布局panel
 */
Ext.define('DutyAllocationOperationPanel', {
	extend : 'BasicPanel',
	layout : 'border',
	items :[
	  {
		xtype:'container',
		region:'north',
		height:280,
		layout:'border',
		items:[
			   Ext.create('DutyComplaintDetailBasicInfoPanel',{
					id:'dutyComplaintDetailBasicInfoPanelAllocation',
					region:'center'
			   }),
		       {//处理经过
	    	     xtype : 'basicpanel',
				 layout:'fit',margin:'0 5 0 0',
				 region:'south',
				 items:[
				 		{//处理经过
							xtype:'textarea',
							width:750,labelWidth:75,
							allowBlank:false,
							maxLength:300,//最大长度300
							regex : new RegExp('^[^"]{0,}$'),
							emptyText:i18n('i18n.DutyAllocationOperationView.treatmentAfterIsNull'),
							regexText:i18n('i18n.Duty.pleaseNotInputDoubleYin'),
							fieldLabel:i18n('i18n.DutyAllocationOperationView.treatmentAfter'),height:70,
							id:'dutyProcessPassId'
						}
					]
		       }
		]
	  },
	  {
		xtype : 'container',
		layout:'border',
		region:'center',
		items:[
			Ext.create('DutyDivideAddAndDelPanel',{//责任划分结果添加、删除按钮
				id:'dutyDivideAddAndDelPanelId',region:'north'
			}),
		    Ext.create('DutyDivideInforToBtnPanel',{
		    	id:'dutyDivideInforToBtnPanelId',region:'south'
		    }),
		    Ext.create('DutyEditableResultGrid',{
		    	id:'dutyEditableResultGridId',region:'center'
		    })  
		  ]
	 },
	 Ext.create('DutyBulletinGrid',{/*通知对象列表*/
	  		id:'dutyBulletinGridId',region:'south',height:100
	 })
	]
});		


/**
 * 单击责任划分按钮，弹出的责任划分操作窗口
 * xiaohongye
*/
Ext.define('DutyAllocationOperationWindow',{
	extend:'PopWindow',
	title:i18n('i18n.DutyAllocationOperationView.workOrderDivisionResponsibilities'),//工单责任划分
	width:800,
	height:750,
	modal:true,
	layout:'fit',
	closeAction:'hide',
	dutyDetail:null,/*责任详情数据*/
	businessClose:false,/*是否为业务关闭 默认不是*/
	isManager:false,/* 默认不是经理 */
	items:[Ext.create('DutyAllocationOperationPanel',{'id':'dutyAllocationOperationPanelId'})],
	listeners:{
		hide:function(){
			document.body.scrollLeft=0;
			document.body.scrollTop=0;
			document.getElementsByTagName("html")[0].style.overflowY="hidden";
			document.getElementsByTagName("html")[0].style.overflowX="hidden";
		},
		close:function(){
			Ext.getCmp('dutyAllocationResultGridId').getStore().load();
			Ext.getCmp('temporaryBtnId').hide();/*经理隐藏暂存按钮*/
			Ext.getCmp('dutyComplaintDetailBasicInfoPanelAllocation').getForm().reset();
			Ext.getCmp('dutyProcessPassId').reset();
			Ext.getCmp('dutyEditableResultGridId').getStore().removeAll();
			Ext.getCmp('dutyBulletinGridId').getStore().removeAll();
			Ext.getCmp('dutyEditableResultGridId').getStore().getNewRecords( ).length = 0;
			Ext.getCmp('dutyEditableResultGridId').getStore().getUpdatedRecords( ).length = 0;
			Ext.getCmp('dutyEditableResultGridId').getStore().getRemovedRecords( ).length = 0;
			Ext.getCmp('dutyBulletinGridId').getStore().getNewRecords( ).length = 0;
			Ext.getCmp('dutyBulletinGridId').getStore().getUpdatedRecords( ).length = 0;
			Ext.getCmp('dutyBulletinGridId').getStore().getRemovedRecords( ).length = 0;
		}
	},
	initComponent:function(){
		var me = this;
		me.buttons = me.getButtons();
		me.callParent(arguments);
	},
	getParams:function(){
		var me = this;
		{//处理结果
			var resultStore = Ext.getCmp('dutyEditableResultGridId').getStore();
			var addResultList = new Array();/*新增的数据*/
			Ext.each(resultStore.getNewRecords(),function(record){
				addResultList.push(Ext.clone(record.data));
			});
			var updateResultList = new Array();/*修改的数据*/
			Ext.each(resultStore.getUpdatedRecords(),function(record){
				updateResultList.push(Ext.clone(record.data));
			});
			var deleteResultList = new Array();/*删除的数据*/
			Ext.each(resultStore.getRemovedRecords(),function(record){
				deleteResultList.push(Ext.clone(record.data));
			});
		}
		
		{//通知对象
			var bulletinStore = Ext.getCmp('dutyBulletinGridId').getStore();
			//通知对象-新增的
			var addInformUserList = new Array();
			Ext.each(bulletinStore.getNewRecords(),function(record){
				addInformUserList.push(Ext.clone(record.data));
			});
			//通知对象-修改的
			var updateInformUserList = new Array();
			Ext.each(bulletinStore.getUpdatedRecords(),function(record){
				updateInformUserList.push(Ext.clone(record.data));
			});
			//通知对象-删除的
			var deleteInformUserList = new Array();
			Ext.each(bulletinStore.getRemovedRecords(),function(record){
				deleteInformUserList.push(Ext.clone(record.data));
			});
		}
		
		return {
			dutyDetail:Ext.clone(me.dutyDetail),/*责任详情*/
			addResultList:addResultList,
			updateResultList:updateResultList,
			deleteResultList:deleteResultList,
			
			addInformUserList:addInformUserList,
			updateInformUserList:updateInformUserList,
			deleteInformUserList:deleteInformUserList
		};
	},
	getButtons:function(){
		var me = this;
		return [
			{xtype:'button',text:i18n('i18n.Duty.DutyApproval.SearchComplaintDetail'),handler:function(btn){//查看工单详情				
				var selectRecords = Ext.getCmp('dutyAllocationResultGridId').getSelectionModel().getSelection();
				show_comp_detailsWin(selectRecords[0].get('complaintid'));
			}},
			{xtype:'button',text:i18n('i18n.DutyAllocationOperationView.sureNoDuty'),handler:function(btn){//确认无责
				//处理经过
				var dutyProcessPassField = Ext.getCmp('dutyProcessPassId');
				var dutyProcessPass = Ext.getCmp('dutyProcessPassId').getValue();
				if(!dutyProcessPassField.isValid()){
					return;
				}
				var param = {
					dutyDetail:Ext.clone(me.dutyDetail)/*责任详情*/
				};
				param.dutyDetail['processPass'] = dutyProcessPass;
				
				var successFn = function(response){
					btn.enable();
					MessageUtil.showInfoMes(i18n('i18n.DutyAllocationOperationView.sureNoDutySuccess'));me.close();//确认无责成功
					if(!Ext.isEmpty(Ext.getCmp('dutyAllocationQueryPanelId'))){
						Ext.getCmp('dutyAllocationQueryPanelId').getForm().getFields().each(function(item){
							if(item.name!='totalDutyNum'&&item.name!='expressDutyNum'&&item.name!='unExpressDutyNum'&&item.name!='unreceiveDutyNum'){
								item.reset();
							}
							
						});
					}
				};
				var failureFn = function(response){
					MessageUtil.showMessage(i18n('i18n.DutyAllocationOperationView.sureNoDutyFailure'));btn.enable();//确认无责失败
				};
				btn.disable();
				DutyAllocationStore.prototype.confirmNotDuty(param,successFn,failureFn);
			}},
			{xtype:'button',text:i18n('i18n.DutyAllocationOperationView.temporary'),id:'temporaryBtnId',hidden:true,handler:function(btn){//暂存
				//处理经过
				var dutyProcessPass = Ext.getCmp('dutyProcessPassId').getValue();
				var dutyProcessPassField = Ext.getCmp('dutyProcessPassId');
				if(!dutyProcessPassField.isValid()){
					return;
				}
				var param = me.getParams();
				param.dutyDetail['processPass'] = dutyProcessPass;
				var successFn = function(response){
					btn.enable();
					if(!Ext.isEmpty(Ext.getCmp('dutyAllocationQueryPanelId'))){
						Ext.getCmp('dutyAllocationQueryPanelId').getForm().getFields().each(function(item){
							if(item.name!='totalDutyNum'&&item.name!='expressDutyNum'&&item.name!='unExpressDutyNum'&&item.name!='unreceiveDutyNum'){
								item.reset();
							}
							
						});
					}
					MessageUtil.showInfoMes(i18n('i18n.DutyAllocationOperationView.temporarySuccess'));me.close();//暂存成功
				};
				var failureFn = function(response){
					btn.enable();
					MessageUtil.showMessage(i18n('i18n.DutyAllocationOperationView.temporaryFailure'));//暂存失败
				};
				btn.disable();
				DutyAllocationStore.prototype.temporarySave(param,successFn,failureFn);
			}},
			{xtype:'button',text:i18n('i18n.duty.submit'),handler:function(btn){//提交
				//处理经过
				var dutyProcessPass = Ext.getCmp('dutyProcessPassId').getValue();
				var dutyProcessPassField = Ext.getCmp('dutyProcessPassId');
				if(!dutyProcessPassField.isValid()){
					return;
				}
				var resultStore = Ext.getCmp('dutyEditableResultGridId').getStore();
				if(resultStore.getCount()==0){
					MessageUtil.showMessage(i18n('i18n.DutyAllocationOperationView.processingResultsCanNotEmpty'));return;//处理结果不能为空
				}
				var bulletinStore = Ext.getCmp('dutyBulletinGridId').getStore();
				if(bulletinStore.getCount()==0){
					MessageUtil.showMessage(i18n('i18n.DutyAllocationOperationView.informUserCanNotEmpty'));return;//通知对象不能为空
				}
				
				var param = me.getParams();
				param.dutyDetail['processPass'] = dutyProcessPass;
				var successFn = function(response){
					btn.enable();
					MessageUtil.showInfoMes(i18n('i18n.DutyAllocationOperationView.commitSuccess'));me.close();//提交成功
					if(!Ext.isEmpty(Ext.getCmp('dutyAllocationQueryPanelId'))){
						Ext.getCmp('dutyAllocationQueryPanelId').getForm().getFields().each(function(item){
							if(item.name!='totalDutyNum'&&item.name!='expressDutyNum'&&item.name!='unExpressDutyNum'&&item.name!='unreceiveDutyNum'){
								item.reset();
							}
							
						});
					}
				};
				var failureFn = function(response){
					btn.enable();
					if(!Ext.isEmpty(response)){
						MessageUtil.showMessage(response.message);//提示错误信息
					}else{
						MessageUtil.showMessage(i18n('i18n.DutyAllocationOperationView.commitError'));//提交出错
					}
					
				};
				btn.disable();
				DutyAllocationStore.prototype.submitDutyDivide(param,successFn,failureFn);
				
			}},
			{xtype:'button',text:i18n('i18n.Duty.DutyApproval.Close'),handler:function(){me.close();}}
		];
	},
	dutyDivideLook:function(dutyModel,callBack){
		var me = this;
		var dutyDetailsForm = Ext.getCmp('dutyComplaintDetailBasicInfoPanelAllocation').getForm();
			
		var params = {'searchDutyCondition.id':dutyModel.get('id')};
		//执行成功
		var successFn = function(response){
			if(Ext.isEmpty(response.dutyDetail)){
				MessageUtil.showErrorMes(i18n('i18n.DutyAllocationOperationView.dataNotExist'));return;//数据不存在
			}
			var dutyDetail = response.dutyDetail;
			me.dutyDetail = Ext.clone(response.dutyDetail);
			Ext.getCmp('dutyAllocationOperationWindowId').dutyDetail =  Ext.clone(response.dutyDetail);
			dutyDetailsForm.loadRecord(new DutySearchDetailModel({
				'voucherNumber':dutyDetail.voucherNumber,
				'tel':dutyDetail.tel,
				'caller':dutyDetail.caller,
				'sendOrReceive':dutyDetail.sendOrReceive,
				'reportType':dutyDetail.reportType,
				'complaintCust':dutyDetail.complaintCust,
				'custLevel':dutyDetail.custLevel,
				'custType':dutyDetail.custType,
				'complaintSource':dutyDetail.complaintSource,
				'relatCus':dutyDetail.relatCus,
				'relatCusLevel':dutyDetail.relatCusLevel,
				'timeLimit':dutyDetail.timeLimit,
				'pririty':dutyDetail.pririty,
				'rebindNo':dutyDetail.rebindNo,
				'dealNumber':dutyDetail.dealNumber,
				'reportTime':dutyDetail.reportTime,
				'reporter':dutyDetail.reporter,
				'reportContent':dutyDetail.reportContent,
				'custRequire':dutyDetail.custRequire,
				'businessModel':dutyDetail.businessModel
			}));
			
			
			if(dutyDetail.status=='TEMPORARY'){/*设置处理经过 - 暂存*/
				Ext.getCmp('dutyProcessPassId').setValue(dutyDetail.processPass);
			}else{
				DutyAllocationStore.prototype.searchNewProcessPass({
					'searchDutyCondition.id':dutyDetail.id
				},function(response){
					if(!Ext.isEmpty(response.dealProcess)){
						Ext.getCmp('dutyProcessPassId').setValue(response.dealProcess['treatProcess']);
					}
				},function(){});
			}
			
			/*处理结果集合加载*/
			Ext.getCmp('dutyEditableResultGridId').getStore().load({
				params:params
			});
			
			/*通知对象集合加载*/
			Ext.getCmp('dutyBulletinGridId').getStore().load({
				params:params
			});
		}
		//执行失败
		var failFn = function(response){
			if(Ext.isEmpty(response.message)){return;}
			MessageUtil.showErrorMes(response.message);
		}
		ActionFunction.duty_searchDutyById(params,successFn,failFn);
	}
});

