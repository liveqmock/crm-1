
/**
 * 客户工单之 处理记录列表
 */
Ext.define('DutyEditableResultGrid',{
	extend:'PopupGridPanel',
	initComponent:function(){
		var me = this;
		if(!Ext.isEmpty(me.store)){me.store.removeAll(false);}
		if(Ext.isEmpty(me.selModel)){
			me.selModel = new Ext.selection.CheckboxModel();
		}
		//责任处理结果 Store
		me.store = Ext.create('DutyResultStore');
		me.columns = me.getColumns();
		me.callParent(arguments);
		me.on('itemdblclick', function(view, record) {
			if(!Ext.isEmpty(record.get('id')) /* 表示 以划分的 */){return;}
			
			var dutyAddResultWindowsId = Ext.getCmp('dutyAddResultWindowsId');
			if(Ext.isEmpty(dutyAddResultWindowsId)){
				dutyAddResultWindowsId = Ext.create('DutyAddResultWindows',{id:'dutyAddResultWindowsId'});
			}
			
			dutyAddResultWindowsId.setTitle(i18n('i18n.DutyEditableResultView.updateDoResult'));
			dutyAddResultWindowsId.show();
			dutyAddResultWindowsId.isUpdate = true;
			var formPanel = Ext.getCmp('dutyAddResultPanelId').getForm();
			formPanel.loadRecord(new DutyResultModel({
				'busAspectId':record.get('busAspectId'),
				'dealLanguage':record.get('dealLanguage'),
				'divideType':record.get('divideType'),
				'reworpusType':record.get('reworpusType'),
				'dutyDeadLine':record.get('dutyDeadLine'),
				'businessLink':record.get('businessLink')
			}));
			
			{//加载业务范围 、加载业务类型
				var busScope = formPanel.findField('busScopeId');
				busScope.getStore().load(function(){
					busScope.setValue(record.get('busScopeId'));
					//加载业务类型
					var busType = formPanel.findField('busTypeId');
					busType.getStore().load(function(){
						busType.setValue(record.get('busTypeId'));
					});
				});
			}
			
			{//加载部门或个人
				var dealName = formPanel.findField('dealName');
				var empName = formPanel.findField('empName');
				if(record.get('divideType')==='department'){
					//部门
					dealName.show();dealName.allowBlank=false;
					empName.hide();empName.allowBlank=true;
					dealName.getStore().load({
						params:{
							'deptName':record.get('dutyPerName')
						},
						callback:function(){
							dealName.setValue(record.get('dutyPerId'));
						}
					});
				}else{//个人
					dealName.hide();dealName.allowBlank=true;
					empName.show();empName.allowBlank=false;
					empName.setValue(record.get('dutyPerName'));
					empName.record = new EmployeeLookUpMode();
					Ext.getCmp('AppDivDutyBulletinGrid').getStore().each(function(inforUser){
						if(inforUser.get('virtualCode')==record.get('virtualCode')){
							empName.record.set('empId',record.get('dutyPerId'));
							empName.record.set('empCode',inforUser.get('userNo'));
							empName.record.set('empName',inforUser.get('userName'));
							empName.record.set('position',inforUser.get('userPosition'));
							empName.record.set('mobileNumber',inforUser.get('userContact'));
							empName.record.set('deptName',inforUser.get('deptName'));
						}
					});
				}
			}
			
		});
	}
	,getColumns:function(){
		var me = this;
		return [
			{xtype:'rownumberer',header:i18n('i18n.duty.serial_number'/*序号*/),width:40},
			{header:i18n('i18n.duty.result.busItem'/*业务项*/),width:100,dataIndex:'busAspect'},
			{header:i18n('i18n.duty.result.busScope'/*业务范围*/),width:100,dataIndex:'busScope'},
			{header:i18n('i18n.duty.result.busType'/*业务类型*/),width:100,dataIndex:'busType'},
			{
				header:i18n('i18n.duty.result.businessLink'/*业务环节*/),width:100,dataIndex:'businessLink',
				renderer:function(value){
					return rendererDictionary(value,DataDictionary.DUTY_COMPLAINT_LINK);
				}
			},
			{header:i18n('i18n.duty.result.deallan'/*处理语言*/),width:140,dataIndex:'dealLanguage',renderer:function(value) {
				if(!Ext.isEmpty(value)){
					var val = '<span data-qtip="'+value+'">'+value+'</span>';
					return val;
				}
			}},
			{
				header:i18n('i18n.duty.result.dealType'/*类型*/),width:80,dataIndex:'divideType',
				renderer:function(value){
					return rendererDictionary(value,DataDictionary.PROCESS_TYPE);
				}
			},
			{header:i18n('i18n.duty.result.taskDeptName'/*任务部门*/),width:160,dataIndex:'dutyPerName'},
			{
				header:i18n('i18n.duty.result.reworpusType'/*奖罚类型*/),width:100,dataIndex:'reworpusType',
				renderer:function(value){
					return ReworpusType.getReworpusTypeName(value);
				}
			},
			{
				header:i18n('i18n.duty.result.feedtimeLimit'/*反馈时限*/),width:140,dataIndex:'dutyDeadLine',
				renderer:DutyUtil.renderDateTime
			},
			{
				header:i18n('i18n.duty.result.surveyResult'/*调查结果*/),width:110,dataIndex:'surveyResult',
				renderer:function(value){
					return DutyUtil.rendererDictionary(value,DataDictionary.DUTY_SURVEY_RESULT);
				}
			}
		];
	},
	/**
	 * 判断个人或部门是否存在
	 * @param {Object} parm = {divideType:i18n('i18n.DutyEditableResultView.divideType'),perId:i18n('i18n.DutyEditableResultView.deptOrPersonalId')}
	 */
	isExistDeptEmp:function(parm){
		var result = {flag:false,msg:''};
		//判断新添的是否存在于已经反馈过的部门或个人
		Ext.each(this.oldResultList,function(data){
			data.old_dutyPerId = data.dutyPerId;
			if(
				data.divideType === parm.divideType /*划分类型匹配*/ &&
				data.old_dutyPerId === parm.perId  /*ID相同*/
			){
				result = {flag:true,msg:i18n('i18n.DutyEditableResultView.deptOrUserAlreadyFeekback')};
				return false;/*表示已存在当前部门或个人*/
			}
		});
		
		//判断新添的个人或部门是否存在
		this.getStore().each(function(record){
			if(
				record.get('divideType') === parm.divideType /*划分类型匹配*/ &&
				record.get('old_dutyPerId') === parm.perId  /*ID相同*/
			){
				result = {flag:true,msg:i18n('i18n.DutyEditableResultView.deptUserHavePaeaseReSelect')};
				return false;/*表示已存在当前部门或个人*/
			}
		});
		return result;
	},
	/**
	 * 删除原部门的通知对象 或原个人通知对象
	 * @param {Model} reusultModel
	 */
	deleteBulletinByPerName:function(reusultModel){
		var bulletinStore = Ext.getCmp('AppDivDutyBulletinGrid').getStore();/*处理对象*/
		bulletinStore.each(function(record){
			if(Ext.isEmpty(record.get('id')) /*在审批环节,质检员操作*/){
				if(record.get('virtualCode')===reusultModel.get('virtualCode')){
					bulletinStore.remove(record);
				}
			}
		});
	}
});

/**
 *工单责任划分经理查询条件输入面板form
 */
Ext.define('DutyAddResultPanel',{
	extend:'SearchFormPanel', 
	items:null,
	border:0,
	layout:'column',
	busItemStore:null,//业务环节
	defaults:{
		labelWidth:60,labelAlign:'right',
		allowBlank:false,blankText:i18n('i18n.DutyEditableResultView.notNull'),
		margin:'0 5 5 0',columnWidth:.33
	},
	defaultType:'textfield',
	initComponent:function(){			
		var me = this;
		me.busItemStore = Ext.create('BusinessItemsStoreOnly');
		this.items = this.getItems();
		this.callParent();
	},
	loadBusItemStore:function(reportType){
		var me = this;
		me.busItemStore.load({params:{
			'basicSearchCondition.reportType':reportType
		}});
	},
	getItems:function(){
		var me = this;
		return [
		    {
		    	xtype:'combobox',name:'busAspectId',forceSelection:true,store:me.busItemStore
		    	,fieldLabel:i18n('i18n.duty.result.busItem'/*业务项*/),
                listConfig: {loadMask:false},queryMode:'local',displayField:'busItemName',valueField:'busItemId'
                ,listeners:{scope:me,select:function(combo,records,eOpts){
					var store = me.getForm().findField('busScopeId').getStore();
					//移除业务类型
					me.getForm().findField('busTypeId').getStore().removeAll();
					//移除业务范围
					store.removeAll();
					
					store.load();
				}}
		    },
		    {
	    		xtype:'combobox',name:'busScopeId',forceSelection:true,
	    		fieldLabel:i18n('i18n.duty.result.busScope'/*业务范围*/),
				listConfig: {loadMask:false},store:Ext.create('DutyBusScopeStoreOnly',{
					listeners:{
						beforeload:function(store, operation, eOpts){
							var busAspect = me.getForm().findField('busAspectId');
							Ext.apply(operation,{params:{
								'basicSearchCondition.id':busAspect.getValue()
							}});	
						}
					}
				})
                ,queryMode:'local',displayField:'basciLevelName',valueField:'id'
                ,listeners:{scope:me,select:function(combo,records,eOpts){
						var store = me.getForm().findField('busTypeId').getStore();
						store.removeAll(); store.load();
					}
				}
			},
			{
				xtype:'combobox',name:'busTypeId',forceSelection:true,
				listConfig: {loadMask:false},store:Ext.create('DutyBusTypeStore',{
					listeners:{
						beforeload:function(store, operation, eOpts){
							var busScope = me.getForm().findField('busScopeId');
							Ext.apply(operation,{params:{
								'basicSearchCondition.id':busScope.getValue()
							}});
						}
					}
				}),
				fieldLabel:i18n('i18n.duty.result.busType'/*业务类型*/),
                queryMode:'local',displayField:'busType',valueField:'fid'
                ,listeners:{scope:me,select:function(combo,records,eOpts){
	                	var form = me.getForm();
	                	form.findField('dealLanguage').setValue(records[0].get('deallan'));
		                form.findField('reworpusType').setValue(records[0].get('reworpusType'));
		                //设置反馈时限
		                DutyAllocationStore.prototype.serarchFeedbackTime(
		                	{'searchDutyCondition.value':records[0].get('feedbacklimit')}
		                	,function(response){
		                		if(Ext.isEmpty(response.feedbackTime)){
		                	        form.findField('dutyDeadLine').reset();
		                		}else{
		                			var date = new Date(parseInt(response.feedbackTime));
			                		form.findField('dutyDeadLine').setValue(date);
		                		}
		                	},function(){}
		                );
					}
				}
			},
			{
				fieldLabel:i18n('i18n.duty.result.deallan'/*处理语言*/),
				columnWidth:.99,
				name:'dealLanguage',
				maxLength:300,
				regexText:i18n('i18n.Duty.pleaseNotInputDoubleYin'),
				regex : new RegExp('^[^"]{0,}$'),
				emptyText:i18n('i18n.DutySearch.maxLength300')
			},
			{
				fieldLabel:i18n('i18n.duty.result.reworpusType'/*奖罚类型*/),
				xtype:'combobox',name:'reworpusType',forceSelection:true,
	            queryMode:'local',displayField:'codeDesc',valueField:'code',store:{
	            	xtype:'store',fields:['code','codeDesc'],
	            	data:ReworpusType.list
	            }
			},
			{
				fieldLabel:i18n('i18n.duty.result.feedtimeLimit'/*反馈时限*/),
				xtype:'datetimefield',name:'dutyDeadLine',format:'Y-m-d H:i',
				minValue:new Date()
			},
			{
				xtype:'combobox',name:'businessLink',forceSelection:true
                ,fieldLabel:i18n('i18n.duty.result.businessLink'/*业务环节*/),
                store:getDataDictionaryByName(DataDictionary,'DUTY_COMPLAINT_LINK')
                ,queryMode:'local',displayField:'codeDesc',valueField:'code',value:'RECEIVE_LINK'//‘责任家默认值收货环节’
			},
			{
				xtype:'combobox',name:'divideType',forceSelection:true
                ,fieldLabel:i18n('i18n.duty.result.dealType'/*类型*/),value:'department',
                store:getDataDictionaryByName(DataDictionary,'PROCESS_TYPE')
                ,queryMode:'local',displayField:'codeDesc',valueField:'code'
				,listeners:{scope:me,select:function(combo,records,eOpts){
					var dealName = me.getForm().findField('dealName');
					var empName = me.getForm().findField('empName');
					if(combo.getValue()==='department'){
						dealName.show();dealName.allowBlank = false;
						empName.hide();empName.allowBlank = true;
					}else{
						dealName.hide();dealName.allowBlank = true;
						empName.show();empName.allowBlank = false;
					}
				}}
			},
			{/*任务部门*/
				xtype:'combobox',queryParam:'deptName',forceSelection:true,
				name:'dealName',store:Ext.create('DepartmentStore',{
					autoLoad:false,listeners:{
						beforeload:function(store, operation, eOpts){
							var deptName = me.getForm().findField('dealName').getRawValue().trim();
							if(!Ext.isEmpty(deptName)){
								Ext.apply(operation,{
									params : {'deptName':deptName}
								});	
							}
						}
					}
				})
				,displayField:'deptName',valueField:'id'
				,minChars:1,typeAhead: false,hideTrigger:false
			},
			{    
				xtype:'employeelookup'
				,name:'empName'
				,allowBlank:true,hidden:true
				,editable:false
				,listeners:{
					select:function(com,record){
						com.record = record;
					}
				}
			}
	    ];
	}
});

/**
 * 添加处理结果弹出框
 */
Ext.define('DutyAddResultWindows',{
	extend:'PopWindow',
	title:i18n('i18n.DutyEditableResultView.addingProcessingResults'),
	width:700,height:200,
	isUpdate:false,/*默认是新增 false*/
	modal:true,layout:'fit',closeAction:'hide',
	items:Ext.create('DutyAddResultPanel',{id:'dutyAddResultPanelId'}),
	initComponent:function(){
		var me = this;
		me.buttons = me.getButtons();
		this.callParent(arguments);
	},
	listeners:{
		show:function(){
			var formPanel = Ext.getCmp('dutyAddResultPanelId');
			//加载业务环节
			formPanel.loadBusItemStore(Ext.getCmp('AppDivDutyDivideWindow').dutyDetail.reportType);
		},
		close:function(){
			var gridStore = Ext.getCmp('DutyEditableResultGrid').getStore();
			if(gridStore.getCount()==10){//为了设置新增按钮不可用
				Ext.getCmp('resultAddBtnId').setDisabled(true);
			}
			//初始化表单弹出框
			var formPanel = Ext.getCmp('dutyAddResultPanelId').getForm();
			formPanel.reset();
			formPanel.findField('busScopeId').getStore().removeAll();//业务范围数据清除
			formPanel.findField('busTypeId').getStore().removeAll();//业务类型数据清除
			var dealName = formPanel.findField('dealName');
			var empName = formPanel.findField('empName');
			dealName.show(); dealName.allowBlank = false;
			empName.hide(); empName.allowBlank = true;
		}
	},
	loadEmployeeById:function(reusultModel,record){
		var form = Ext.getCmp('dutyAddResultPanelId').getForm();
		var bulletinStore = Ext.getCmp('AppDivDutyBulletinGrid').getStore();/*处理对象*/
		bulletinStore.add({
			'virtualCode':reusultModel.get('virtualCode'),
			//'deptId':record.get('empId'),
			'userNo':record.get('empCode'),
			'userName':record.get('empName'),
			'userPosition':record.get('position'),
			'userContact':record.get('mobilePhone'),
			'deptName':record.get('deptName')
		});
	},
	loadEmployeeByDepartment:function(reusultModel){
		var me = this;
		var bulletinStore = Ext.getCmp('AppDivDutyBulletinGrid').getStore();/*处理对象*/
		var params = {'parameterMap':{'deptId':reusultModel.get('dutyPerId')}};
		//执行成功
		var successFn = function(response){
			for(var i=0;i<response.employeeList.length;i++){
				var employee = response.employeeList[i];
					bulletinStore.add({
						'virtualCode':reusultModel.get('virtualCode'),
						//'deptId':employee['id'],
						'userNo':employee['empCode'],
						'userName':employee['empName'],
						'userPosition':employee['position'],
						'userContact':employee['mobileNumber'],
						'deptName':reusultModel.get('dutyPerName')
						
					});
			}
		}
		//执行失败
		var failFn = function(response){
			MessageUtil.showErrorMes(response.message);
		}
		DpUtil.requestJsonAjax('../complaint/searchEmployeeListByDeptId.action',params,successFn,failFn);
	},
	getButtons:function(){
		var me = this;
		return  [
			{
				xtype:'button',
				text:i18n('i18n.duty.submit')/*提交*/,
				handler:function(){
					
					var form = Ext.getCmp('dutyAddResultPanelId').getForm();
					if(!form.isValid()){return;}
					
					var empName = form.findField('empName');
					var resultGrid = Ext.getCmp('DutyEditableResultGrid');
					var reusultModel = null;
					if(me.isUpdate && me.isUpdate==true){//修改
						reusultModel = resultGrid.getSelectionModel().getSelection()[0];
					}else{//添加
						reusultModel = new DutyResultModel();
					}
					//先来逻辑判断
					var dealName = form.findField('dealName');//责任人
					var divideType = form.findField('divideType').getValue();
					var dutyPerId = null;//责任人ID
					if(divideType === 'department'){
						dutyPerId = dealName.getValue();
					}else{
						dutyPerId = empName.record.get('empId');
					}
					if(!(divideType===reusultModel.get('old_dealType')&&dutyPerId===reusultModel.get('old_dutyPerId'))){//若是新增old_dealType是空，肯定回去校验，如果是修改则跟原来的进行比较
						//修改过了部门或个人
						//判断当前部门或个人是否存在
						var result = resultGrid.isExistDeptEmp({'divideType':divideType,'perId':dutyPerId});
						if(result.flag == true){
							MessageUtil.showMessage(result.msg);return;
						}
					}
					 //后进行数据加载
					{//赋值的
						reusultModel.set('busAspectId',form.findField('busAspectId').getValue());
						reusultModel.set('busAspect',form.findField('busAspectId').getRawValue());
						reusultModel.set('busScopeId',form.findField('busScopeId').getValue());
						reusultModel.set('busScope',form.findField('busScopeId').getRawValue());
						reusultModel.set('busTypeId',form.findField('busTypeId').getValue());
						reusultModel.set('busType',form.findField('busTypeId').getRawValue());
						reusultModel.set('dealLanguage',form.findField('dealLanguage').getValue());
						reusultModel.set('divideType',divideType);
						reusultModel.set('reworpusType',form.findField('reworpusType').getValue());
						reusultModel.set('dutyDeadLine',form.findField('dutyDeadLine').getValue());
						reusultModel.set('businessLink',form.findField('businessLink').getValue());
						reusultModel.set('surveyResult','DUTY_SURVEYING');//新增的是调查中
						if(reusultModel.get('divideType') === 'department'){
							var dealName = form.findField('dealName');
							reusultModel.set('dutyPerId',dealName.getValue());
							reusultModel.set('dutyPerName',dealName.getRawValue().trim());
						}else{
							reusultModel.set('dutyPerId',empName.record.get('empId'));
							reusultModel.set('dutyPerName',empName.getValue());
						}
					}
					
					var loadEmpOrDept = function(){/*加载带出部门过个人*/
						//添加虚拟 code 让 result 和 通知对象关联
						reusultModel.set('virtualCode',Math.uuidFast());
						if(reusultModel.get('divideType') === 'department'){
							me.loadEmployeeByDepartment(reusultModel);
						}else{
							me.loadEmployeeById(reusultModel,empName.record);
						}
						//最后赋值.新部门和旧部门统一
						reusultModel.set('old_dealType',divideType);
						reusultModel.set('old_dutyPerId',dutyPerId);
					};
					
					//保存添加的划分部门至集合
					var saveReusultToGrid = function(){
						if(me.isUpdate && me.isUpdate==true){//修改
							if(!(divideType===reusultModel.get('old_dealType')&&dutyPerId===reusultModel.get('old_dutyPerId'))){
								resultGrid.deleteBulletinByPerName(reusultModel);//删除通知对象
								loadEmpOrDept();//加载通知对象
							}
						}else{//添加
							loadEmpOrDept();
							resultGrid.getStore().add(reusultModel);
						}
						me.close();
					};
					//若为事业部
					if(dutyApprovalRole === i18n('i18n.Duty.DutyApproval.BusCallerRoleId')){ //事业部质检员
						//验证添加的部门或个人是否在同一个事业部（与反馈部门）
						DutyUtil.requestJsonAjax('../duty/isSameDepartmentByDeptIds.action',{'result':{
							'divideType':reusultModel.get('divideType'),
							'dutyPerId':reusultModel.get('dutyPerId')
						}},function(response){
							saveReusultToGrid();
						},function(response){
							MessageUtil.showErrorMes(response.message);
						});
					}else{
						saveReusultToGrid();
					}
				}
			},
			{
				xtype:'button',text:i18n('i18n.duty.cancel')/*取消*/,
				handler:function(){me.close();}
			}
		];
	}
});

