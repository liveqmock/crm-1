/**
 * 客户工单之 通知对象可编辑列表
 */
Ext.define('AppDivDutyBulletinGrid',{
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
Ext.define('AppDivDutyInforToBtnPanel',{
	extend:'TopPanel',items : null,
	initComponent : function() {
		this.items = this.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [{
			xtype:'titlepanel',flex:1,
			items:[{xtype:'displayfield',value:i18n('i18n.DutyAllocationOperationView.infoemUser')}]
		},{
			xtype:'btnpanel',  
			defaultType:'button',
			items:[{//增加
				text:i18n('i18n.duty.add'),handler:function(){
					//增加按钮
					var me = this;
					var store = Ext.getCmp('AppDivDutyBulletinGrid').getStore();
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
					var grid = Ext.getCmp('AppDivDutyBulletinGrid');
					var selectionList = grid.getSelectionModel().getSelection();
					if(Ext.isEmpty(selectionList)){
						selectionList.push(grid.getStore().getAt(grid.getStore().getCount()-1));//删除最后一个
					}
					
					var check_result = {'flag':true};
					for(var i=0;i<selectionList.length;i++){
						var record = selectionList[i];
						if(//不允许删除
							!Ext.isEmpty(record.get('id')) /* 表示暂存 和 以划分的 */
						){check_result={'flag':false,'msg':i18n('i18n.DutyApprovalDivideView.haveOldDataPaeaseReSelect')};break;}
					
						grid.getStore().remove(record);
					}
					if(check_result.flag == false){
						MessageUtil.showInfoMes(check_result.msg);return;
					}
		      	}
			}]
		}]; 
	}
});

/**
 * 责任划分结果增加、删除按钮Panel
 */
Ext.define('AppDivDutyResultAddAndDelPanel',{
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
				xtype:'displayfield',value:i18n('i18n.Duty.DutyApproval.DutyAllocationResult')
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
					var grid = Ext.getCmp('DutyEditableResultGrid');
					var selection = grid.getSelectionModel().getSelection();
					var store = grid.getStore();
					if(selection.length==0){//未选则的 删除最后一个
						selection[0] = store.getAt(store.getCount()-1);
					}
					var dutyAddResultWindowsId = Ext.getCmp('dutyAddResultWindowsId');
					var check_result = {'flag':true};
					Ext.each(selection,function(record){
						if(//不允许删除
							!Ext.isEmpty(record.get('id')) /* 表示暂存 和 以划分的 */
						){check_result={'flag':false,'msg':i18n('i18n.DutyApprovalDivideView.haveOldDataPaeaseReSelect')};return false;}
						
						//删除通知
						grid.deleteBulletinByPerName(Ext.clone(record));
						store.remove(record);
					});
					if(check_result.flag == false){
						MessageUtil.showInfoMes(check_result.msg);return;
					}
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
Ext.define('AppDivDutyDividePanel', {
	extend : 'BasicFormPanel',
	layout: {align: 'stretch',type: 'vbox'},
	items :[
       {//审批意见
			xtype:'textarea',labelWidth:75,
			allowBlank:false,maxLength:300,
			regex : new RegExp('^[^"]{0,}$'),
			regexText:i18n('i18n.Duty.pleaseNotInputDoubleYin'),
			emptyText:i18n('i18n.DutySearch.maxLength300'),
			blankText:i18n('i18n.Duty.DutyApproval.NullApprovalOpinion'),
			fieldLabel:i18n('i18n.Duty.DutyApproval.ApprovalOpinion'),
			height:70,name:'callCenDescision'
		},
		{xtype: 'hiddenfield',name: 'feedbackId'},
    	{xtype: 'hiddenfield',name: 'detailId'},
    	{xtype: 'hiddenfield',name: 'dutyId'},
       {//处理经过
			xtype:'textarea',
			labelWidth:75,
			maxLength:300,
			allowBlank:false,
			regexText:i18n('i18n.Duty.pleaseNotInputDoubleYin'),
			regex : new RegExp('^[^"]{0,}$'),
			emptyText:i18n('i18n.DutyAllocationOperationView.treatmentAfterIsNull'),
			fieldLabel:i18n('i18n.DutyAllocationOperationView.treatmentAfter'),height:70,
			name:'processPass'
       },
	 	Ext.create('AppDivDutyResultAddAndDelPanel',{//责任划分结果添加、删除按钮
			id:'AppDivDutyResultAddAndDelPanel'
		}),
		Ext.create('DutyEditableResultGrid',{
	    	id:'DutyEditableResultGrid',flex:1
	    }),
	    Ext.create('AppDivDutyInforToBtnPanel',{//通知对象操作按钮
	    	id:'AppDivDutyInforToBtnPanel'
	    }),
	    Ext.create('AppDivDutyBulletinGrid',{/*通知对象列表*/
  			id:'AppDivDutyBulletinGrid',flex:1
	 	})
	]
});		


/**
 * 单击责任划分按钮，弹出的责任划分操作窗口
 * xiaohongye
*/
Ext.define('AppDivDutyDivideWindow',{
	extend:'PopWindow',
	title:i18n('i18n.DutyAllocationOperationView.workOrderDivisionResponsibilities'),
	width:800,
	height:500,
	modal:true,
	layout:'fit',
	closeAction:'hide',
	dutyDetail:null,/*责任详情数据*/
	businessClose:false,/*是否为业务关闭 默认不是*/
	items:[Ext.create('AppDivDutyDividePanel',{'id':'AppDivDutyDividePanel'})],
	listeners:{
		hide:function(){
			document.body.scrollLeft=0;
			document.body.scrollTop=0;
			document.getElementsByTagName("html")[0].style.overflowY="hidden";
			document.getElementsByTagName("html")[0].style.overflowX="hidden";
			
			Ext.getCmp('AppDivDutyDividePanel').getForm().reset();
			Ext.getCmp('DutyEditableResultGrid').getStore().removeAll();
			Ext.getCmp('AppDivDutyBulletinGrid').getStore().removeAll();
			
			//刷新审批页面
			Ext.getCmp('dutyApprovalResultGridId').getStore().loadPage(1);
		}
	},
	initComponent:function(){
		var me = this;
		me.buttons = me.getButtons();
		me.callParent(arguments);
	},
	getParams:function(){
		var me = this;
		var form = Ext.getCmp('AppDivDutyDividePanel').getForm();
		//处理结果
		var resultStore = Ext.getCmp('DutyEditableResultGrid').getStore();
		var dutyResultList = new Array();
		resultStore.each(function(record){
			dutyResultList.push(Ext.clone(record.data));
		});
		
		//通知对象
		var bulletinStore = Ext.getCmp('AppDivDutyBulletinGrid').getStore();
		//通知对象-新增的
		var dutyInformUserList = new Array();
		bulletinStore.each(function(record){
			dutyInformUserList.push(Ext.clone(record.data));
		});
		
		return {
			dutyFeedback:{
				//责任反馈ID
    			'feedbackId':form.findField('feedbackId').getValue(),
    			//责任划分结果ID
		    	'detailId':form.findField('detailId').getValue(),
		    	//质检员审批意见
		    	'callCenDescision':form.findField('callCenDescision').getValue(),
		    	//责任编号
		    	'dutyId':form.findField('dutyId').getValue()
			},
			dealProcess:{
				//责任编号
		    	'dutyId':form.findField('dutyId').getValue(),
		    	'treatProcess':form.findField('processPass').getValue()
			},
			'approvalType':'INSPECTOR_AGREE',
			dutyResultList:dutyResultList,
			dutyInformUserList:dutyInformUserList
		};
	},
	getButtons:function(){
		var me = this;
		return [
			{xtype:'button',text:i18n('i18n.duty.submit'),handler:function(btn){
				var form = Ext.getCmp('AppDivDutyDividePanel').getForm();
				if(!form.isValid()){return;}
				
				var resultStore = Ext.getCmp('DutyEditableResultGrid').getStore();
				if(resultStore.getCount()==0){
					MessageUtil.showMessage(i18n('i18n.DutyAllocationOperationView.processingResultsCanNotEmpty'));return;
				}
				var bulletinStore = Ext.getCmp('AppDivDutyBulletinGrid').getStore();
				if(bulletinStore.getCount()==0){
					MessageUtil.showMessage(i18n('i18n.DutyAllocationOperationView.informUserCanNotEmpty'));return;
				}
				
				var param = me.getParams();
				var successFn = function(response){
					btn.enable();
					MessageUtil.showInfoMes(i18n('i18n.DutyApprovalDivideView.dutyResultUpdateSuccess'),function(){
						me.close();Ext.getCmp("dutyApprovalOperationWindowId").close();
						//刷新审批页面
						Ext.getCmp('dutyApprovalResultGridId').getStore().loadPage(1);
					});
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
				DutyApprovalStore.prototype.dutyApprovalOperation(param, successFn, failureFn);
			}},
			{xtype:'button',text:i18n('i18n.Duty.DutyApproval.Close'),handler:function(){me.close();}}
		];
	},
	dutyDivideLook:function(parm){
		var me = this;
		var params = {'searchDutyCondition.id':parm.dutyId};
		//执行成功
		var successFn = function(response){
			if(Ext.isEmpty(response.dutyDetail)){
				MessageUtil.showErrorMes(i18n('i18n.DutyAllocationOperationView.dataNotExist'));return;
			}
			var dutyDetail = response.dutyDetail;
			Ext.getCmp('AppDivDutyDivideWindow').dutyDetail = Ext.clone(response.dutyDetail);
			me.dutyDetail = Ext.clone(response.dutyDetail);
			
			
			var form = Ext.getCmp('AppDivDutyDividePanel').getForm();
			form.findField('dutyId').setValue(parm.dutyId);
			form.findField('feedbackId').setValue(parm.feedbackId);
			form.findField('detailId').setValue(parm.detailId);
	
//			DutyAllocationStore.prototype.searchNewProcessPass({
//				'searchDutyCondition.id':dutyDetail.id
//			},function(response){
//				if(!Ext.isEmpty(response.dealProcess)){
//					Ext.getCmp('dutyProcessPassId').setValue(response.dealProcess['treatProcess']);
//				}
//			},function(){});
			
			/*处理结果集合加载*/
			var resultGrid = Ext.getCmp('DutyEditableResultGrid');
			resultGrid.getStore().load({
				params:params,callback:function(records, operation, success){
					if(success == false){return;}
					resultGrid.oldResultList = new Array();
					Ext.each(records,function(record){
						resultGrid.oldResultList.push(record.data);
					});
					resultGrid.getStore().removeAll();
				}
			});
			
//			/*通知对象集合加载*/
//			Ext.getCmp('AppDivDutyBulletinGrid').getStore().load({
//				params:params
//			});
		}
		//执行失败
		var failFn = function(response){
			if(Ext.isEmpty(response.message)){return;}
			MessageUtil.showErrorMes(response.message);
		}
		ActionFunction.duty_searchDutyById(params,successFn,failFn);
	}
});

