/**
 * 合同查询主panel						ContractManagerPanel
 * 合同查询条件panel						ContractManagerConditionForm
 * 合同查询按钮panel						ContractManagerButtonPanel
 * 合同查询结果panel						ContractManagerResultGrid
 */ 
//合同管理 查询 界面
var contractManagerDataControl =  (CONFIG.get("TEST"))? new ContractManagerDataTest():new ContractManagerData();
//var contractManagerDataControl =  new ContractManagerData();
var contractControl = (CONFIG.get('TEST'))? Ext.create('ContractBasicDataTest'):Ext.create('ContractBasicData');
Ext.define('ContractManagerPanel',{
	extend:'BasicPanel',
	parent:null,
	contractManagerConditionForm:null,//查询条件
	contractManagerResultGrid:null,  //查询结果
	contractManagerButtonPanel:null,//查询按钮面板
	contractData:null,//数据Data
	store:null,
	layout:{
        type:'vbox',
        align:'stretch'
    },
	initComponent:function(){
		var me = this;
		var record = new ContractConditionModel();
		me.contractManagerConditionForm = Ext.create('ContractManagerConditionForm',{'parent':me,'record':record,'contractData':me.contractData});
		me.contractManagerButtonPanel = Ext.create('ContractManagerButtonPanel',{'store':me.store,'parent':me,'contractData':me.contractData,
		'contractManagerConditionForm':me.contractManagerConditionForm,'monthEndDay':me.monthEndDay});
		me.contractManagerResultGrid = Ext.create('ContractManagerResultGrid',{'parent':me,'contractData':me.contractData});
		me.formPanel = Ext.create('Ext.form.Panel', {
			id:'wrapForm'+'htgl-htgl-01',					// 'wrapForm'+'khgl-qkgl-01'
			border : false,
			frame : true,
			//height:140,
			items : [{
				id : 'id1'+'htgl-htgl-01',				// 'id1' + 'khgl-qkgl-01'
				name : 'first',
				height:20
			}, {
				id : 'id2'+'htgl-htgl-01',				// 'id2' +'khgl-qkgl-01'
				name : 'last'
				,height:60						// height:160
			}]
		});
		me.items = [{
			xtype:'basicpanel',
			height:80,
			items:[me.contractManagerConditionForm]
		},{
			xtype:'basicpanel',
			height:36,
			items:[me.contractManagerButtonPanel]
		},{
			xtype:'basicpanel',
			flex:1,
			items:[me.contractManagerResultGrid]
		},{
			xtype:'basicpanel',
			height:80,
			items:[me.formPanel]
		}];
		this.callParent();
		me.contractManagerConditionForm.loadRecord(record);
		//默认部门
		DpUtil.defaultDept(me.contractManagerConditionForm,'deptId');
	}
});

/**
 * 查询重置按钮面板
 */
Ext.define('ContractManagerButtonPanel',{
	extend:'NormalButtonPanel',
	contractManagerConditionForm:null,
	contractData:null,   //data层接口
	parent:null,
	store:null,
	monthEndDay:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'leftbuttonpanel',
			width:560,
			defaultType:'button',
			defaults:{
				scope:me,
				disabled:true,
				margins : '0 5 0 0'
			},
			items:[{
				disabled:false,
				text:i18n('i18n.ContractManagerView.add'),
				hidden:!isPermission('/customer/ContractABtn.action'),
				handler:me.addRecord
			},{
				id:'updateBtn_id',
				text:i18n('i18n.ContractManagerView.update'),
				hidden:!isPermission('/customer/ContractUBtn.action'),
				handler:me.updateRecord
			},{
				id:'invalidBtn_id',
				text:i18n('i18n.ContractManagerView.invalid'),
				hidden:!isPermission('/customer/ContractDBtn.action'),
				handler:me.invalidContract
			},{
				id:'bondBtn_id',
				text:i18n('i18n.ContractManagerView.bindingContract'),
				hidden:!isPermission('/customer/ContractBindBtn.action'),
				handler:me.boundContract
			},{
				id:'changeBondBtn_id',
				text:i18n('i18n.ContractManagerView.unbind'),
				hidden:!isPermission('/customer/ContractUnBindBtn.action'),
				handler:me.relieveContract
			},{
				id:'changeDeptBtn_id',
				text:i18n('i18n.ContractManagerView.attributableToChange'),
				hidden:!isPermission('/customer/ContractBelongChangeBtn.action'),
				handler:me.belongDeptChange
			},{
				id:'viewDetailBtn_id',
				text:i18n('i18n.PotentialCustManagerView.viewDetails'),
				hidden:!isPermission('/customer/ContractVBtn.action'),
				handler:me.viewContractDetail
			},{
				id:'changeContractMonthEnd_id',
				text:'修改合同月结天数',
				hidden:!isPermission('/customer/ContractMBtn.action'),
				handler:me.modifyMonthEndDays		
			},{
				id:'changeContractMonthSendRate_id',
				text:'修改运费折扣',
				hidden:!isPermission('/customer/ContractMRateBtn.action'),
				handler:me.modifyMonthSendRate		
			},{
				id:'DunningDeptManager_id',
				text:'催款部门设置',
				hidden:!isPermission('/customer/DunningDeptManager.action'),
				handler:me.dunningDeptManager
			},{
				id:'PriceVersionDateManager_id',
				text:'价格版本设置',
				hidden:!isPermission('/customer/PriceVersionDateManager.action'),
				handler:me.priceVersionDateManager
			}
			//需求变更取消合 同导出功能
//			,{
//				xtype:'button',
//				text:i18n('i18n.ContractManagerView.export'),
//				scope:me,
//				handler:me.expordContract
//			}
			]
		},{
			xtype:'middlebuttonpanel'
		},{
			xtype:'rightbuttonpanel',
			width:130,
			items:[{
				xtype:'button',
				text:i18n('i18n.PotentialCustManagerView.search'),
				hidden:!isPermission('/customer/ContractSBtn.action'),
				scope:me,
				handler:me.searchContractList
				}]
		}];
	},
	//新增
	addRecord:function(){
		var me = this;
		if(me.selectedRecord().length == 1){
			// 选择数据  则打开 新签界面并代出数据
			var params={};
			var record=me.selectedRecord()[0];
			if(!me.cannotOperate(record)){return;}
			var operate = i18n('i18n.ContractManagerView.update');
			params.contractId = record.get('id');
			if(!me.contractIsAuditing(params,operate,'changeSign')){return;}
			//合同 改签 功能
			var viewContractDetailSuccess = function(response){
				var contractView = response.contractView;
				var member = response.member;
				var contractEditWin = Ext.create('ContractEditWin',{
					'contractView':contractView,
					'member':member,
					'store':me.store,
					'status':'UPDATE'});
				contractEditWin.show();
				//Ext.getCmp('contractSaveButton').setDisabled(false);
				//新签界面打开后通过结款方式，优惠类型和折扣显示数据
				//合同主信息 form
				var formInfo = contractEditWin.contractViewPanel.contractInfo.contractTabBasicInfo.contractBasicInfo;
				showDateByBusiness(getContractInfoRecord(contractView),formInfo,'UPDATE');
			};
			var viewContractDetailFail = function(response){
				MessageUtil.showErrorMes(response.message);
			}
			params.handType = 'insert';
			me.contractData.viewUpdateContractDetail(params,viewContractDetailSuccess,viewContractDetailFail);
		}else{
			// 未选择数据 则 打开"新签"界面
			Ext.create('ContractEditWin',{'status':'NEW','store':me.store}).show();
			Ext.getCmp('contractSaveButton').setDisabled(false);
			var viewContractDetailSuccess = function(result){
				var commonMonthEndDay = result.debtDays;
				Ext.getCmp('commonDebtDays_id').setValue(commonMonthEndDay);
				Ext.getCmp('custRecord_id').monthDay = commonMonthEndDay;
			}
			var viewContractDetailFail = function(){
				MessageUtil.showErrorMes('默认合同月结天数初始化失败！');
			}
			var paramss = {};
			contractControl.initMonthEndDay(paramss,viewContractDetailSuccess,viewContractDetailFail);
		}
	},
	//修改运费折扣
	modifyMonthSendRate:function(){
		var me = this;
		if(me.selectDate('ONE')){
			var params={};
			var record=me.selectedRecord()[0];
			var operate = i18n('i18n.ContractManagerView.update');
			params.contractId = record.get('id');
			var contractStatus = record.get('contractStatus');
			if(!me.contractIsAuditing(params,operate,'update')){return;}
			//修改运费折扣 功能
			var contractDetailUpdateSuccess = function(response){
				var contractView = response.contractView;
				var member = response.member;
				Ext.create('ContractUpdateWin',{
					'contractView':contractView,
					'status':'ALTERRATE'
				}).show();
			};
			var viewContractDetailFail = function(response){
				MessageUtil.showErrorMes(response.message);
			}
			me.contractData.viewUpdateMonthSendDetail(params,contractDetailUpdateSuccess,viewContractDetailFail);
		}
	},
	//修改月结天数
	modifyMonthEndDays:function(){
		var me = this;
		var params={};
		if(me.selectDate('ONE')){
			var record=me.selectedRecord()[0];
			var operate = i18n('i18n.ContractManagerView.update');
			params.contractId = record.get('id');
			var contractStatus = record.get('contractStatus');
			var payWay = record.get('payWay');
			var exPayWay = record.get('exPayWay');
			if('2' == contractStatus){
				MessageUtil.showErrorMes('该合同由于已作废,不能进行月结天数更改！');
				return;
			}else if('0' == contractStatus ){
				MessageUtil.showErrorMes('该合同由于存在审批中的工作流，不能进行月结天数更改！');
				return;
			}else if( 'MONTH_END' != payWay && 'MONTH_END' != exPayWay){
				MessageUtil.showErrorMes('该合同由为非月结，不能进行月结天数更改！');
				return;
			}else{
					//合同 修改 功能
					var contractDetailUpdateSuccess = function(response){
						var contractView = response.contractView;
						var member = response.member;
						var modifyMonthEndWin = Ext.create('ContractUpdateWin',{
							'contractView':contractView,
							'status':'MODIFY'
						});				
						modifyMonthEndWin.afterUpdateContract.getForm().findField('preferentialType').setReadOnly(true);
						modifyMonthEndWin.afterUpdateContract.getForm().findField('preferentialType').allowBlank = true;
						modifyMonthEndWin.applyReasonInfo.getForm().findField('application').setReadOnly(true);
						modifyMonthEndWin.applyReasonInfo.getForm().findField('application').allowBlank = true;
						modifyMonthEndWin.afterUpdateContract.getForm().findField('chargeRebate').setReadOnly(true);
						modifyMonthEndWin.afterUpdateContract.getForm().findField('chargeRebate').setMinValue(0.001);
						modifyMonthEndWin.afterUpdateContract.getForm().findField('chargeRebate').clearInvalid();
						modifyMonthEndWin.afterUpdateContract.getForm().findField('agentgathRate').setReadOnly(true);
						modifyMonthEndWin.afterUpdateContract.getForm().findField('insuredPriceRate').setReadOnly(true);
						modifyMonthEndWin.show();
					};
					var viewContractDetailFail = function(response){
						MessageUtil.showErrorMes(response.message);
					}
					// 为方便理解业务规则（除审批不同意工作流外其他都走修改流程）
					viewSuccess = contractDetailUpdateSuccess;
					params.handType = 'updateMonthEndDay';
					me.contractData.viewUpdateContractDetail(params,viewSuccess,viewContractDetailFail);
				};
	}
	},
	priceVersionDateManager:function(){
		var me = this;
		var params={};
		if(me.selectDate('ONE')){
			var record=me.selectedRecord()[0];
			var operate = i18n('i18n.ContractManagerView.update');
			params.contractId = record.get('id');
			var contractStatus = record.get('contractStatus');
			var payWay = record.get('payWay');
			var exPayWay = record.get('exPayWay');
			if('2' == contractStatus){
				MessageUtil.showErrorMes('该合同由于已作废,不能进行价格版本时间修改！');
				return;
			}else if('0' == contractStatus ){
				MessageUtil.showErrorMes('该合同由于存在审批中的工作流，不能进行价格版本时间修改！');
				return;
			}else if('3' == contractStatus) {
				MessageUtil.showErrorMes('该合同为待生效合同，不能进行价格版本时间修改！');
				return ;
			}else if( 'MONTH_END' != payWay && 'MONTH_END' != exPayWay){
				MessageUtil.showErrorMes('该合同由为非月结，不能进行价格版本时间修改！');
				return;
			} else{
					//合同 修改 功能
					var contractDetailUpdateSuccess = function(response){
						var contractView = response.contractView;
						var member = response.member;
						var modifyMonthEndWin = Ext.create('ContractUpdateWin',{
							'contractView':contractView,
							'status':'priceVersionDate'
						});				
						modifyMonthEndWin.afterUpdateContract.getForm().findField('preferentialType').setReadOnly(true);
						modifyMonthEndWin.afterUpdateContract.getForm().findField('preferentialType').allowBlank = true;
						modifyMonthEndWin.applyReasonInfo.getForm().findField('application').setReadOnly(true);
						modifyMonthEndWin.applyReasonInfo.getForm().findField('application').allowBlank = true;
						modifyMonthEndWin.afterUpdateContract.getForm().findField('chargeRebate').setReadOnly(true);
						modifyMonthEndWin.afterUpdateContract.getForm().findField('chargeRebate').setMinValue(0.001);
						modifyMonthEndWin.afterUpdateContract.getForm().findField('chargeRebate').clearInvalid();
						modifyMonthEndWin.afterUpdateContract.getForm().findField('agentgathRate').setReadOnly(true);
						modifyMonthEndWin.afterUpdateContract.getForm().findField('insuredPriceRate').setReadOnly(true);
						modifyMonthEndWin.show();
						Ext.getCmp('file_id').setDisabled(true);
						Ext.getCmp('updateFile_id').setDisabled(true);
						Ext.getCmp('deleteFile_id').setDisabled(true);
					};
					var viewContractDetailFail = function(response){
						MessageUtil.showErrorMes(response.message);
					}
					// 为方便理解业务规则（除审批不同意工作流外其他都走修改流程）
					viewSuccess = contractDetailUpdateSuccess;
					me.contractData.viewContractDetail(params,viewSuccess,viewContractDetailFail);
				};
	}
	},
	//催款部门
	dunningDeptManager:function(){
		var me = this;
		if(me.selectDate('ONE')){
			var selection=me.selectedRecord();
			var record=me.selectedRecord()[0];
			var contractStatus = record.get('contractStatus');
			if('2' == contractStatus){
				MessageUtil.showErrorMes('该合同由于已作废,不能设置催款部门！');
				return;
			}else if('0' == contractStatus ){
				MessageUtil.showErrorMes('该合同由于存在审批中的工作流，不能设置催款部门！');
				return;
			} else if('3' == contractStatus) {
				MessageUtil.showErrorMes('该合同为待生效合同，不能设置催款部门！');
				return ;
			} else {
					var params={};
					params.contractId = record.get('id');
					var dunningDeptManagerSuccess = function(response){
						var contractView = response.contractView;
						var ifForeignGoods = response.contractView.contract.ifForeignGoods;
						var dunningDeptCode = response.contractView.contract.dunningDeptCode;
						var dunningDeptName = response.contractView.contract.dunningDeptName;
						var member = response.member;
						var dunningDeptManagerWin = Ext.create('ContractUpdateWin',{
							'contractView':contractView,
							'status':'dunningDept'
						});		
						dunningDeptManagerWin.afterUpdateContract.getForm().findField('preferentialType').setReadOnly(true);
						dunningDeptManagerWin.afterUpdateContract.getForm().findField('preferentialType').allowBlank = true;
						dunningDeptManagerWin.applyReasonInfo.getForm().findField('application').setReadOnly(true);
						dunningDeptManagerWin.applyReasonInfo.getForm().findField('application').allowBlank = true;
						dunningDeptManagerWin.afterUpdateContract.getForm().findField('chargeRebate').setReadOnly(true);
						dunningDeptManagerWin.afterUpdateContract.getForm().findField('chargeRebate').setMinValue(0.001);
						dunningDeptManagerWin.afterUpdateContract.getForm().findField('chargeRebate').clearInvalid();
						dunningDeptManagerWin.afterUpdateContract.getForm().findField('agentgathRate').setReadOnly(true);
						dunningDeptManagerWin.afterUpdateContract.getForm().findField('insuredPriceRate').setReadOnly(true);
						dunningDeptManagerWin.show();
						Ext.getCmp('file_id').setDisabled(true);
						Ext.getCmp('updateFile_id').setDisabled(true);
						Ext.getCmp('deleteFile_id').setDisabled(true);
						if(ifForeignGoods == false){
							Ext.getCmp('dunningDeptCode_id').setDisabled(true);
						}else{
							Ext.getCmp('dunningDeptCode_id').getStore().add([{'deptCode':dunningDeptCode,'deptName':dunningDeptName}]);
							Ext.getCmp('dunningDeptCode_id').setValue(dunningDeptCode);
						}
					};
			var viewDunningDeptManagerFail = function(response){
				MessageUtil.showErrorMes(response.message);
			}
			// 为方便理解业务规则（除审批不同意工作流外其他都走修改流程）
			viewSuccess = dunningDeptManagerSuccess;
			me.contractData.viewContractDetail(params,viewSuccess,viewDunningDeptManagerFail);
		 }
		}
	},
	//修改
	updateRecord:function(){
		var me = this;
		if(me.selectDate('ONE')){
			var params={};
			var record=me.selectedRecord()[0];
			var operate = i18n('i18n.ContractManagerView.update');
			params.contractId = record.get('id');
			var contractStatus = record.get('contractStatus');
			if(!me.contractIsAuditing(params,operate,'update')){return;}
			//合同 新签 功能
			var viewContractDetailSuccess = function(response){
				var contractView = response.contractView;
				var member = response.member;
				var contractEditWin = Ext.create('ContractEditWin',{
					'contractView':contractView,
					'member':member,
					'status':'UPDATE'}).show();
				Ext.getCmp('contractSaveButton').setDisabled(false);
				//新签界面打开后通过结款方式，优惠类型和折扣显示数据
				//合同主信息 form
				var formInfo = contractEditWin.contractViewPanel.contractInfo.contractTabBasicInfo.contractBasicInfo;
				/**
				 * 唐亮留坑
				 */
				showDateByBusiness(getContractInfoRecord(contractView),formInfo,'UPDATE');
			};
			//合同 修改 功能
			var contractDetailUpdateSuccess = function(response){
				var contractView = response.contractView;
				var member = response.member;
				Ext.create('ContractUpdateWin',{
					'contractView':contractView,
					'status':'ALTER'
				}).show();
			};
			var viewContractDetailFail = function(response){
				MessageUtil.showErrorMes(response.message);
			}
			var returnArr = workflowLogResult(record);
			 // returnArr[合同状态（ 0审批中1生效 2失效3待生效），
			//			操作类型（insert：新签，changeSign：新签，update修改），
			//			工作流结果(1：审批中，2：同意，3:不同意)]
			if('2' == contractStatus && ('insert' == returnArr[1] || 'changeSign' == returnArr[1] )
					&& '3' == returnArr[2]){
				// 合同是新签审批不同意 则 调用 新签界面 进行修改
				viewSuccess = viewContractDetailSuccess;
			}else if('2' == contractStatus && 'update' == returnArr[1] && '3' == returnArr[2]){
				// 合同是修改审批不同意 则 调用 修改界面
				viewSuccess = contractDetailUpdateSuccess;
			}else{
				// 为方便理解业务规则（除审批不同意工作流外其他都走修改流程）
				viewSuccess = contractDetailUpdateSuccess;
			}
			params.handType = 'update';
			me.contractData.viewUpdateContractDetail(params,viewSuccess,viewContractDetailFail);
		}
	},
	//查询方法
	searchContractList:function(){
		var me = this;
		var searchConditionForm = Ext.getCmp('contractManagerConditionFormId');
		var contractStatus = searchConditionForm.getForm().findField('contractStatus').getValue();
		if (!Ext.isEmpty(contractStatus)&&contractStatus!='0'
			&&contractStatus!='1'&&contractStatus!='2'&&contractStatus!='3') {
				MessageUtil.showErrorMes('合同状态不正确');
				return;
		} 
		searchConditionForm.isNotInitSearch = '1';
		var searchStarttime = Ext.getCmp('searchStartTime_id').getValue();
		var searchEndtime = Ext.getCmp('searchEndTime_id').getValue();
		//当用户输入的生效时间晚于终止时间时，弹出如下错误提示
		if(!Ext.isEmpty(searchStarttime)&&!Ext.isEmpty(searchEndtime)&&
				searchStarttime > searchEndtime){
			MessageUtil.showErrorMes("生效起始时间不得晚于终止时间！");
			return false;
		}
		//合同查询不强制要求必须输入查询条件
		me.contractData.getContractStore().loadPage(1);
	},
	//查看合同详情
	viewContractDetail:function(){
		var me = this;
		if(me.selectDate('ONE')){
			var selection=me.selectedRecord();
			me.viewContractInfo(me.contractData,selection[0],'VIEW');
		}
	},
	//获得合同编辑界面
	viewContractInfo:function(contractData,record,status){
		var params={};
		params.contractId = record.get('id');
		var viewContractDetailSuccess = function(result){
			var contractView = result.contractView;
			Ext.create('ContractEditWin',{
				'contractView':contractView,
				'status':status}).show();
		};
		var viewContractDetailFail = function(result){
			MessageUtil.showErrorMes(result.message);
		}
		contractData.viewContractDetail(params,viewContractDetailSuccess,viewContractDetailFail);
	},
	//作废合同
	invalidContract:function(button){
		var me = this;
		var params={};
		var contractIds=[];
		var record=me.selectedRecord()[0];
		contractIds.push(record.get('id'));
		params.contractIds = contractIds;
		var msg = i18n('i18n.ContractManagerView.insureInvalid')+button.getText()+'？';
		if('终止' == button.getText()){
			if(!me.cannotOperate(record)){return;}
			params.handType = 'STOP';
			msg = i18n('i18n.contractEditView.endContractNotice');
		}else if('删除' == button.getText()){
			params.handType = 'DEL';
		}
		MessageUtil.showQuestionMes( msg, function(e) {
			if (e == 'yes') {
				var invalidContractSuccess = function(result){
					if(Ext.isEmpty(result.workFlowNum)){
						MessageUtil.showInfoMes(i18n('i18n.ContractManagerView.operatorSuccess'));
						//删除后重新load数据
						me.contractData.getContractStore().loadPage(1);
					}else{
						MessageUtil.showInfoMes(i18n('i18n.ContractManagerView.opareatorSuccessWorkFlowNum')+result.workFlowNum);
					}
				};
				var invalidContractFail = function(result){
					MessageUtil.showErrorMes(result.message);
				}
				//执行 终止或删除操作
				me.contractData.invalidContract(params,invalidContractSuccess,invalidContractFail);
			}
		});
	},
	// 绑定合同
	boundContract:function(){
		var me = this;
		if(me.selectDate('ONE')){
			var params={};
			var record=me.selectedRecord()[0];
			if(!me.cannotOperate(record)){return;}
			//归属部门 不可以进行绑定操作
			if(top.User.deptId == record.get('deptId')){
				MessageUtil.showMessage(i18n('i18n.ContractManagerView.attributableToChangeNotrBinding'));
				return;
			}
			var operate = i18n('i18n.ContractManagerView.bindingContract');
			params.contractId = record.get('id');
			params.fileInfoList = [];
			var boundContractSuccess = function(result){
				if(result.workFlowNum == null){
					MessageUtil.showInfoMes(i18n('i18n.ContractManagerView.operatorSuccess'));
				}else{
					MessageUtil.showInfoMes(i18n('i18n.ContractManagerView.opareatorSuccessWorkFlowNum')+result.workFlowNum);
				}
			};
			var boundContractFail = function(result){
				MessageUtil.showErrorMes(result.message);
				return;
			}
			MessageUtil.showQuestionMes (i18n('i18n.contractEditView.boundContractNotice'), function(e) {
				if (e == 'yes') {
					me.contractData.boundContract(params,boundContractSuccess,boundContractFail);
				}
			});
		}
	},
	//解除绑定
	relieveContract:function(){
		var me = this;
		if(me.selectDate('ONE')){
			var record=me.selectedRecord()[0];
			if(!me.cannotOperate(record)){return;}
			MessageUtil.showQuestionMes (i18n('i18n.contractEditView.cancelBoundContractNotice'), function(e) {
				if (e == 'yes') {
						var params={};
						var operate = i18n('i18n.ContractManagerView.unbind');
						params.contract = record.data;
//						if(!me.contractIsAuditing(params,operate)){return;}
						params.deptId = record.get('deptId');
						var relieveContractSuccess = function(result){
							MessageUtil.showInfoMes(i18n('i18n.BoundContactNumView.operateSuccess'));
						};
						var relieveContractFail = function(result){
							MessageUtil.showErrorMes(result.message);
						}
						me.contractData.relieveContract(params,relieveContractSuccess,relieveContractFail);
					}
			});
		}
	},
	// 合同归属部门变更
	belongDeptChange:function(){
		var me = this;
		if(me.selectDate('ONE')){
			var record=me.selectedRecord()[0];
			if(!me.cannotOperate(record)){return;}
			//归属部门不能进行归属变更操作
			if(top.User.deptId == record.get('deptId')){
				MessageUtil.showMessage(i18n('i18n.ContractManagerView.attributableDeptHasNowDeptConNotChange'));
				return;
			}
			var params = {};
			var operate = i18n('i18n.ContractManagerView.attributableToChange');
			params.contractId = record.get('id');
			if(!me.contractIsAuditing(params,operate,'changeBelongDept')){return;}
			me.showBelongDeptChangeWin(record);
		}
	},
	showBelongDeptChangeWin:function(record){
		var me = this;
		var belongDeptChange = Ext.create('BelongDeptChangeWin',{'operateType':'DELAY','contractData':me.contractData,'record':record});
		var form = belongDeptChange.down('form').getForm();
		form.findField('beforeChangeDeptName').setValue(record.get('deptName'));//变更前部门
		form.findField('changedDeptName').setValue(top.User.deptName);//变更后部门
		form.findField('changedDeptId').setValue(top.User.deptId);//变更后部门
		Ext.getCmp('attachPanelId').store.removeAll();
		belongDeptChange.show();
	},
	//导出
	expordContract:function(){
		var me = this;
		if(!me.contractManagerConditionForm.getForm().isValid()){
			MessageUtil.showMessage(i18n('i18n.ContractManagerView.mustIputCreateTimeSub'));
			return;
		}
		if(!me.checkDataOpposite()){
			MessageUtil.showMessage(i18n('i18n.ContractManagerView.createTimeLimit'));
			return;
		}
		var searchConditionForm = Ext.getCmp('contractManagerConditionFormId');
		searchConditionForm.getForm().updateRecord(searchConditionForm.record);
		//设置请求参数
		var searchCustCondition = searchConditionForm.record.data;
		var contractCondition = {
			//部门Id
			'deptId':DpUtil.trimString(searchCustCondition.deptId),
			//客户编码
			'custNumber':DpUtil.trimString(searchCustCondition.custNumber),
			// 客户名称
			'custCompany':DpUtil.trimString(searchCustCondition.custCompany),
			//合同编号
			'contractNum':DpUtil.trimString(searchCustCondition.contractNum),
			//协议联系人
			'contactName':DpUtil.trimString(searchCustCondition.contactName),
			//创建起始时间
			'contractBeginDate':searchCustCondition.contractBeginDate,
			//创建结束时间
			'contractendDate':searchCustCondition.contractendDate
		}
		var expordContractSuccess = function(response){
//				currentUserDeptList = response.currentUserDeptList;
			};
		var expordContractFail = function(result){
			MessageUtil.showErrorMes(result.message);
		}
		me.contractData.expordContract(window,contractCondition);
	},
	selectDate:function(sum){
		var me = this;
		var selection=me.selectedRecord();
		if ('ONE'==sum && selection.length != 1) {
			MessageUtil.showMessage( i18n('i18n.ManualRewardIntegralEditView.m_selectOnlyOne'));
			return false;
		}
		if ('MANY'==sum && selection.length == 0) {
			MessageUtil.showMessage( i18n('i18n.ManualRewardIntegralEditView.m_selectOne'));
			return false;
		}
		return true;
	},
	selectedRecord:function(){
		var me = this;
		var grid = me.parent.contractManagerResultGrid;
		return grid.getSelectionModel().getSelection();
	},
	//时间相对值校验 ->时间相隔不能相差一年
	checkDataOpposite:function(){
		var me = this;
		var flag = false;
		var form = me.contractManagerConditionForm;
		var objPre = form.getForm().findField('contractBeginDate');
		var objNext = form.getForm().findField('contractendDate');

		if(!Ext.isEmpty(objPre.getValue())&&!Ext.isEmpty(objNext.getValue())){
			if(objPre.getValue() <= objNext.getValue()){
				if((objNext.getValue().getTime()-objPre.getValue().getTime())<=1000*3600*24*365){
					return true;
				}
			}
		}else if(Ext.isEmpty(objPre.getValue())&&Ext.isEmpty(objNext.getValue())){
			return true;
		}
		return flag;
	},
	//校验是否查询条件都为空
	validateCondition:function(){
		var me = this;
		var form = me.contractManagerConditionForm;
		var flag = false;
		form.getForm().getFields().each(function(field){
			if(i18n('i18n.Integral.dept') == field.fieldLabel){
				if(!Ext.isEmpty(field.getRawValue())){flag = true;}
			}
			if(!(DpUtil.isEmpty(field.getValue()))&&field.getValue()!=i18n('i18n.PotentialCustManagerView.searchEndTime')
			&& 'contractBeginDate' != field.name && 'contractendDate' != field.name){
				flag = true;
			}
		});
		return flag;
	},
	//判断 所选合同 是否在审批中  审批中则返回 true，否则返回 false
	contractIsAuditing:function(params,operate,handType){
		var me = this;
		var success = true;
		var message = '';
		params.handType = handType;
		var checkSuccess = function(response){
			success = response.checked;
			if(!success){message = i18n('i18n.ContractManagerView.contractFailNotOperate',operate);}
		};
		var checklFail = function(response){
			success = false;
			message = response.message;
		}
		me.contractData.contractIsAuditing(params,checkSuccess,checklFail);
		if(!success){
			MessageUtil.showErrorMes(message);
			return false;
		}else{
			return true;
		}
	},
	//作废合同不可做任何操作
	cannotOperate:function(record,operateType){
		if(record.get('contractStatus')==2){
			MessageUtil.showMessage('已终止或作废的合同不可进行此操作！');
			return false;
		}
		return true;
	}
});
/**
 * 查询条件
 */
Ext.define('ContractManagerConditionForm',{
	extend:'SearchFormPanel',
	id:'contractManagerConditionFormId',
	record:null,
	parent:null,
	isNotInitSearch:'0',
	contractData:null,
	layout:{
		type:'table',
		columns:4
	},
	defaultType:'dptextfield',
	initComponent:function(){
		var me = this;
		me.defaults = me.getDefaultsContainer();
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'belongdeptcombox',
			fieldLabel:i18n('i18n.Integral.dept'),
			functionName:'ContractFunction',
			forceSelection:true,
			name:'deptId'
		},{
			fieldLabel:i18n('i18n.ContractEditView.customersFullName'),
			maxLength :20,	
			maxLengthText :i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20),
			name:'custCompany'
		},{
			fieldLabel:i18n('i18n.MemberCustEditView.custNo'),
			maxLength :40,	
			maxLengthText :i18n('i18n.ManualRewardIntegralEditView.m_maxLength',40),
			name:'custNumber'
		},{
			fieldLabel:i18n('i18n.ContractEditView.contractNum'),
			maxLength :20,	
			maxLengthText :i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20),
			name:'contractNum'
		},{
			fieldLabel:i18n('i18n.MemberCustEditView.agreementContact'),
			maxLength :20,	
			maxLengthText :i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20),
			name:'contactName'
		},
		{
			xtype : 'fieldcontainer',
			colspan : 2,
			border : 0,
			width : 400,
			layout : 'column',
			defaultType : 'datefield',
			defaults : {
				enableKeyEvents:true,
				listeners:{
					scope : me,
					keypress : me.keypressEvent
				}
			},
			items : [ {
				fieldLabel : '生效时间',labelWidth : 65,width : 200,format : 'Y-m-d',
				id:'searchStartTime_id',name : 'contractBeginDate'
			}, 
			{xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
			{xtype: 'datefield',width : 130,format : 'Y-m-d',
				id:'searchEndTime_id',name : 'contractendDate'} ]
		},{
			xtype:'combo',
			fieldLabel:i18n('i18n.ContractEditView.contractStatus'),
			store:me.contractData.getContractStateStore(),
			queryMode:'local',
//            forceSelection:true,
            value:'1',
            maxLength:20,
			displayField:'codeDesc',
			name:'contractStatus',
			valueField:'code'
		}];
	},
	//增加监听事件
	getDefaultsContainer:function(){
		var me = this;
		return {
			labelWidth:65,
			width:195,
			enableKeyEvents:true,
			listeners:{
				scope : me,
				keypress : me.keypressEvent,
				change : me.changeEvent
			}
		};
	},
	//监听按键事件
	keypressEvent:function(field,event){
		var me = this;
		if(event.getKey() == Ext.EventObject.ENTER){
    		me.parent.contractManagerButtonPanel.searchContractList();
    	}
	},
	//监听change事件
	changeEvent:function(field,newValue){
		var me = this;
		//如果是数据字典或所属部门组件 则显示只选
		if(('belongdeptcombox' == field.getXType() || 'combobox' == field.getXType()) 
			&& Ext.isEmpty(newValue)){
			field.setValue(null);
		}
	}
});

/**
 * 合同查询结果
 */
Ext.define('ContractManagerResultGrid',{
	extend:'SearchGridPanel',
	searchConditionForm:null,
	contractData:null,//数据Data
	initComponent:function(){
		var me = this;
		me.store = me.contractData.initContractStore(me.beforeLoadFn);
		me.dockedItems = me.getMyDockedItems();
		me.listeners = me.getMyListeners();
		me.selModel = Ext.create('Ext.selection.CheckboxModel', {
	        listeners: {
	            selectionchange: function(sm, selections) {
	            	var flag = (selections.length != 1);
	            	Ext.getCmp('updateBtn_id').setDisabled(flag);//"修改"按钮
	            	Ext.getCmp('invalidBtn_id').setDisabled(flag);//"终止"按钮
	            	Ext.getCmp('bondBtn_id').setDisabled(flag);//"绑定合同"
	            	Ext.getCmp('changeBondBtn_id').setDisabled(flag);//"解除绑定"
	            	Ext.getCmp('changeDeptBtn_id').setDisabled(flag);//"归属变更"
	            	Ext.getCmp('viewDetailBtn_id').setDisabled(flag);//"查看详情"
	            	Ext.getCmp('changeContractMonthSendRate_id').setDisabled(flag);//"修改运费折扣"
	            	Ext.getCmp('changeContractMonthEnd_id').setDisabled(flag);//"修改合同月结天数"	
	            	Ext.getCmp('DunningDeptManager_id').setDisabled(flag);//"催款部门设置"	
	            	Ext.getCmp('PriceVersionDateManager_id').setDisabled(flag);//"价格版本设置"	
        			Ext.getCmp('invalidBtn_id').setText('终止');
	            	if(selections.length == 1){
	            		Ext.getCmp('invalidBtn_id').setText(judgeInvalidOrDel(selections[0]));
            		}
	            }
	        }
	    });
		me.columns = me.getColumns();
		this.callParent();
		//判断标志位如果是初始化查询，那就初始化查询
		if(Ext.getCmp('contractManagerConditionFormId').isNotInitSearch ==='0'){
			me.store.loadPage(1);
		}
		me.store.on('load',function(s,records){   
	        var girdcount=0;   
	        s.each(function(r){   
           	   //给单元格涂色
           	   	var cells = me.getView().getNodes()[girdcount].children;
    			for(var i= 0;i<cells.length;i++){
    				if(2 == r.get('contractStatus')){
    					cells[i].style.backgroundColor='#FFEEDD';
    					//判断标志位如果是初始化查询，那就初始化颜色
 	    			}else if(Ext.getCmp('contractManagerConditionFormId').isNotInitSearch ==='0'){
 	    				cells[i].style.backgroundColor='#FF0000';
 	    			}
	            }
 	    		girdcount++;
	        });   
		});  
	},
	getColumns:function(){
		var me = this;
		return [{
			header:i18n('i18n.ContractManagerView.contractid'),
			hidden:true,
			dataIndex:'id'
		},{
			header:i18n('i18n.ContractEditView.contractNum'),
			dataIndex:'contractNum'
		},{
			header:i18n('i18n.ContractManagerView.attributableDept'),
			width:150,
			dataIndex:'deptName'
		},{
			header:i18n('i18n.ContractEditView.customersFullName'),
			dataIndex:'custCompany'
		},{
			header:'零担优惠类型',//i18n('i18n.ContractEditView.preferentialType'),
			dataIndex:'preferentialType',
			renderer : function(value) {
				return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.PRIVILEGE_TYPE);
			}
		},{
			header:'零担结款方式',//i18n('i18n.ContractEditView.resultsSectionBy'),
			dataIndex:'payWay',
			renderer : function(value) {
				return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.RECKON_WAY);
			}
		},{
			header:'快递优惠类型',//i18n('i18n.ContractEditView.preferentialType'),
			dataIndex:'exPreferentialType',
			renderer : function(value) {
				return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.EXPRIVILEGE_TYPE);
			}
		},{
			header:'快递结款方式',//i18n('i18n.ContractEditView.resultsSectionBy'),
			dataIndex:'exPayWay',
			renderer : function(value) {
				return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.RECKON_WAY);
			}
		},{
			header:i18n('i18n.ContractManagerView.arrearsAmount'),
			dataIndex:'arrearaMount'
		},{
			header : i18n('i18n.ContractEditView.contractStatus'),
			dataIndex:'contractStatus',
			renderer :  
			function(value) {
				return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.CONTACT_STATUS);
			}
		},{
			text:i18n('i18n.ContractEditView.contractWFStatus'),
			dataIndex:'contractWorkflowList',
			renderer:function(value){
				if(!Ext.isEmpty(value) && !Ext.isEmpty(value[0].approvalState)){
					return DpUtil.changeDictionaryCodeToDescrip(value[0].approvalState,DataDictionary.CONTRACT_WORKFLOW_STATUS);
				}
				return null;
			}
		},{
			header:i18n('i18n.IntegralRuleEdit.validateDate'),
			dataIndex:'contractBeginDate',
			width : 135,
			renderer : DpUtil.renderTime
		},{
			header:i18n('i18n.ContractManagerView.expirationDate'),
			dataIndex:'contractendDate',
			width : 135,
			renderer : DpUtil.renderTime
		},{
			header : i18n('i18n.MemberCustEditView.agreementContact'),
			dataIndex:'linkManName'
		},{
			header : i18n('i18n.PotentialCustManagerView.contactPhone'),
			dataIndex:'linkManMobile'
		},{
			header : i18n('i18n.PotentialCustManagerView.contactTel'),
			dataIndex:'linkManPhone'
		},{
			header : i18n('i18n.PotentialCustManagerView.creator'),
			dataIndex:'createUser'
		},{
			header : i18n('i18n.PotentialCustManagerView.createTime'),
			dataIndex:'createDate',
			width : 135,
			renderer : DpUtil.renderTime
		},{
			header : i18n('i18n.PotentialCustManagerView.lastUpdate'),
			dataIndex:'modifyUser'
		},{
			header : i18n('i18n.PotentialCustManagerView.lastUpdateTime'),
			dataIndex:'modifyDate',
			width : 135,
			renderer : DpUtil.renderTime
		}];
	},
	//分页条
	getMyDockedItems :function(){ 
		var me = this;
		return [ {
			xtype : 'pagingtoolbar',
			store : me.store,
			dock : 'bottom',
			displayInfo : true
		} ];
	},
	//双击查看合同详情
	getMyListeners:function(){
		var me = this;
		return {
			itemdblclick:function(grid,record){
				me.parent.contractManagerButtonPanel.viewContractInfo(me.contractData,record,'VIEW');
			}
		};
	},
	//beforeLoad方法
	beforeLoadFn:function(store, operation, eOpts){
		var searchConditionForm = Ext.getCmp('contractManagerConditionFormId');
		if(searchConditionForm!=null){
			searchConditionForm.getForm().updateRecord(searchConditionForm.record);
			//设置请求参数
			var searchParams ={};
			var searchCustCondition = searchConditionForm.record.data;
			if (!Ext.isEmpty(DpUtil.trimString(searchCustCondition.contractNum))) {
				searchParams = {
						//合同编号
						'contractCondition.contractNum':DpUtil.trimString(searchCustCondition.contractNum),
						'contractCondition.initSearch':searchConditionForm.isNotInitSearch
				}
			}else if (!Ext.isEmpty(DpUtil.trimString(searchCustCondition.custNumber))) {
				searchParams = {
				//客户编码
						'contractCondition.custNumber':DpUtil.trimString(searchCustCondition.custNumber),
						'contractCondition.initSearch':searchConditionForm.isNotInitSearch
				}
			}else{
				searchParams = {
						//部门Id
						'contractCondition.deptId':DpUtil.trimString(searchCustCondition.deptId),
						//客户编码
						'contractCondition.custNumber':DpUtil.trimString(searchCustCondition.custNumber),
						// 客户名称
						'contractCondition.custCompany':DpUtil.trimString(searchCustCondition.custCompany),
						//合同编号
						'contractCondition.contractNum':DpUtil.trimString(searchCustCondition.contractNum),
						//协议联系人
						'contractCondition.contactName':DpUtil.trimString(searchCustCondition.contactName),
						//创建起始时间
						'contractCondition.contractBeginDate':searchCustCondition.contractBeginDate,
						//创建结束时间
						'contractCondition.contractendDate':searchCustCondition.contractendDate,
						//合同状态
						'contractCondition.contractStatus':searchCustCondition.contractStatus,
						'contractCondition.initSearch':searchConditionForm.isNotInitSearch
				}
			}
			Ext.apply(operation,{
				params : searchParams
				}
			);	
		}
	},
	//改变grid字体颜色
	getViewConfig:function(){
		var me = this;
		return {
            //行背景色间隔显示
            getRowClass : function(record, rowIndex, rp, ds){
                if(2 == record.data.contractStatus)
                {
                    return 'background: red';//红色
                }
            }
		}
	}
});
/**
 * 绑定合同 win
 */
Ext.define('BoundContractWin',{
	extend:'PopWindow',
	title:i18n('i18n.ContractManagerView.bindingContract'),
	width:600,
	height:200,
	layout:'fit',
	contractData:null,
	record:null,
	contractAttachment:null,
	initComponent:function(){
		var me = this;
		// 附件界面
		me.contractAttachment = Ext.create('ContractAttachment',{'operateType':'DELAY','contractData':me.contractData,'record':me.record})
		me.items = [me.contractAttachment];//合同附件panel
		me.fbar = me.getFbar();
		this.callParent();
	},
	getFbar:function(){
		var me = this;
		return[{
	    	xtype:'button',
	    	scope:me,
	    	text:i18n('i18n.MemberCustEditView.submit'),
	    	handler:me.submitBound
	    },{
	    	xtype:'button',
	    	text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
	    	scope:me,
	    	handler:me.cancelBound
	    }];
	},
	submitBound:function(button){
		var me = this;
		var params = {};
		var alterAddAttachList = new Array();
		//防止重复点击保存按钮
		button.setDisabled(true);
		//搜集合同附件修改的信息
		me.contractAttachment.collectAlterAttachData(alterAddAttachList);
		var contractId = me.record.get('id');
		params.contractId = contractId;//合同id
		if(alterAddAttachList.length <= 0){
			button.setDisabled(false);
			MessageUtil.showMessage(i18n('i18n.ContractManagerView.contractBindingNeedUploadReLoad'));
			return;
		}
		for (var i = 0; i < alterAddAttachList.length; i++){
			alterAddAttachList[i].sourceId = contractId;//合同附件信息
		}
		params.fileInfoList = alterAddAttachList;
		var boundContractSuccess = function(result){
			button.setDisabled(false);
			if(result.workFlowNum == null){
				MessageUtil.showInfoMes(i18n('i18n.ContractManagerView.operatorSuccess'));
			}else{
				MessageUtil.showInfoMes(i18n('i18n.ContractManagerView.opareatorSuccessWorkFlowNum')+result.workFlowNum);
			}
			me.close();
		};
		var boundContractFail = function(result){
			button.setDisabled(false);
			MessageUtil.showErrorMes(result.message);
			return;
		}
		me.contractData.boundContract(params,boundContractSuccess,boundContractFail);
	},
	cancelBound:function(){
		var me = this;
		me.close();
	}
});
/**
 * 合同归属部门变更 win
 */
Ext.define('BelongDeptChangeWin',{
	extend:'PopWindow',
	title:i18n('i18n.ContractManagerView.attributableToChange'),
	contractData:null,
	operateType:null,
	contractAttachment:null,//附件界面
	record:null,
	width:600,
	height:120,
	record:null,
	layout:{
        type:'vbox',
        align:'stretch'
    },
	initComponent:function(){
		var me = this;
		me.contractAttachment = Ext.create('ContractAttachment',{'operateType':me.operateType,'contractData':me.contractData,'record':me.record,width:300,height:100})
		me.items = me.getItems();
		me.fbar = me.getFbar();
		this.callParent();
	},
	getItems:function(){
		var me = this;// 附件界面
		return [{
			xtype:'basicformpanel',
			layout:'column',
			height:30,
			defaultType: 'dptextfield',
		    items: [{
		        fieldLabel: i18n('i18n.ContractManagerView.beforeCangeDept'),
		        name: 'beforeChangeDeptName',
		        readOnly:true
		    },{
				name:'changedDeptName',
				fieldLabel:i18n('i18n.ContractManagerView.afterChangeDept'),
		        readOnly:true
			},{
				fieldLabel:i18n('i18n.ContractManagerView.afterChangeDeptId'),
				xtype:'hiddenfield',
				name:'changedDeptId',
		        readOnly:true
			}]
		}];
	},
	getFbar:function(){
		var me = this;
		return[{
	    	xtype:'button',
	    	scope:me,
	    	text:i18n('i18n.MemberCustEditView.submit'),
	    	handler:me.changeDeptSubmit
	    },{
	    	xtype:'button',
	    	text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
	    	scope:me,
	    	handler:me.changeDeptCancel
	    }];
	},
	changeDeptSubmit:function(button){
		button.setDisabled(true);
		var me = this;
		var form = me.down('form').getForm();
		var params={};
		var deptId = form.findField('changedDeptId').getValue();//变更后部门id
		var contractId = me.record.get('id');
		params.deptId = deptId;//部门id
		params.contractId = contractId;//合同id
		params.fileInfoList = [];
		var belongDeptChangeSuccess = function(result){
			button.setDisabled(false);
			if(result.workFlowNum == null){
				MessageUtil.showInfoMes(i18n('i18n.ContractManagerView.operatorSuccess'));
			}else{
				MessageUtil.showInfoMes(i18n('i18n.ContractManagerView.opareatorSuccessWorkFlowNum')+result.workFlowNum);
			}
			me.close();
		};
		var belongDeptChangeFail = function(result){
			button.setDisabled(false);
			MessageUtil.showErrorMes(result.message);
			return;
		}
		MessageUtil.showQuestionMes (i18n('i18n.contractEditView.changeBelongDeptNotice'), function(e) {
			if (e == 'yes') {
				me.contractData.belongDeptChange(params,belongDeptChangeSuccess,belongDeptChangeFail);
			}else{
				button.setDisabled(false);
			}
		},function(){
			button.setDisabled(false);
		});
	},
	changeDeptCancel:function(){
		var me = this;
		me.close();
	}
});
Ext.define('InitSearchMemberFieldContainer', {
	extend:'Ext.form.FieldContainer',
	fieldLabel:'searchMemberFieldText',//文本提示
	winTitle:i18n('i18n.ContractEditView.contractInfo'),
	winWidth:860,
	winHeight:350,
	contractData:null,
	name:'searchMemberField',
	initComponent:function(){
		var me = this;
		
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [
		{xtype: 'displayfield',width : 10, value: me.fieldLabel},
		{
			xtype:'dptextfield',
			name : me.name,
            hideLabel: true,
			width : 65,
			labelWidth : 10
		}];
	}
});
Ext.onReady(function() {
	var params = ['RECKON_WAY',// 结款方式
	              'PRIVILEGE_TYPE', // 优惠类型
	              'INVOICE_MARK',//发票标记BIxiangm
	              'EXPRIVILEGE_TYPE',//快递优惠类型
          		  'CUSTOMER_TYPE',// 客户类型
          		  'MEMBER_GRADE',//客户等级
          		  'TRADE',// 客户行业
          		  'CONTACT_STATUS',// 合同状态
          		  'CONTRACT_WORKFLOW_STATUS',//合同工作流状态
          		  'WORKFLOWTYPE',//合同工作流类型
          		  'CONTRACT_ANNEX'//合同附件
				];
	initDataDictionary(params);//数据字典
	var store= Ext.create('ContractSubjectStore').load();
	Ext.create('Ext.container.Viewport', {
		layout : 'fit',
		items : [ Ext.create('ContractManagerPanel', {
			'contractData' : contractManagerDataControl,
			'store':store
		}) ]
	});
	Ext.create('Depon.Lib.oDocHelper', {
		helpDoc:{				// 帮助实体：
			windowNum:'htgl-htgl-01'	// TODO:帮助文档的ID	belongModule(首字母缩写)+belongMenu（首字母缩写）+windowNum(弹窗编号)+首编时间戳
			,active:true			// 记录操作员操作，是否选择了”隐藏帮助“；
		}
	});
});

