//合同新签操作
/**
 * 加载合同客户信息数据
 */
/**
 * 获得合同客户信息数据
 */
function getCustInfoRecord(contractView){
	var custInfoRecord = null;
	if(contractView != null){
		custInfoRecord = Ext.create('ContractViewModel',contractView);
	}else{
		custInfoRecord = Ext.create('ContractViewModel');
	}
	return custInfoRecord;
};
function getSignCompanyRecord(contractView){
	var signCompanyInfo = null;
	if(contractView != null){
		signCompanyInfo = Ext.create('SignCompanyModel',contractView.contract.signCompany);
	}else{
		signCompanyInfo = Ext.create('SignCompanyModel');
	}
	return signCompanyInfo;
}
/**
 * 加载合同基本信息数据
 */
/**
 * 获得合同基本信息数据
 */
function getContractInfoRecord(contractView){
	var contractInfoRecord = null;
	if(contractView != null){
		contractInfoRecord = Ext.create('ContractModel',contractView.contract);
	}else{
		contractInfoRecord = Ext.create('ContractModel');
	}
	return contractInfoRecord;
};
/**
 * 加载合同优惠信息数据
 */
/**
 * 获得合同优惠信息数据
 */
function getPreferentialData(contractView){
	var preferentialRecord = null;
	if(contractView != null){
		preferentialRecord = Ext.create('PreferentialModel',contractView.contract.preferential);
	}else{
		preferentialRecord = Ext.create('PreferentialModel');
	}
	return preferentialRecord;
};
/**
 * 获得合同快递优惠信息数据
 */
function getExPreferentialData(contractView){
	var preferentialRecord = null;
	if(contractView != null){
		preferentialRecord = Ext.create('PreferentialModel',contractView.contract.exPreferential);
	}else{
		preferentialRecord = Ext.create('PreferentialModel');
	}
	return preferentialRecord;
};
/**
 * 加载合同申请事由数据
 */
/**
 * 获得合同申请事由数据
 */
function getApplication(contractView){
	var application = '';
	if(contractView != null){
		application = contractView.contract.application;
	}
	return application;
};
/**
 * 加载合同附件数据
 */
/**
 * 获得合同附件数据
 */
function getAttachData(contractView,status){
	//只有查看合同信息时 才显示 合同附件信息
	var attachData = [];
	if(contractView != null ){
		if('VIEW' == status){
			attachData = contractView.contract.fileInfoList;
		}else if('NEW' == status || 'UPDATE' == status){
			attachData = [Ext.create('FileInfoModel',{'fileBusinessType': DpUtil.changeDictionaryCodeToDescrip('BUSINESSLICENSE',DataDictionary.CONTRACT_ANNEX),
							'sourceId': '','sourceType': '','fileName': '','fileType': '','savePath': '','fileSize': ''}),
			              Ext.create('FileInfoModel',{'fileBusinessType': DpUtil.changeDictionaryCodeToDescrip('ELECTRONICCONTRACT',DataDictionary.CONTRACT_ANNEX),
			            	  'sourceId': '','sourceType': '','fileName': '','fileType': '','savePath': '','fileSize': ''}),
			            	  //添加一般纳税人资质证明、税务登记证复印件、银行账号 CONTRACTTAX
			              Ext.create('FileInfoModel',{'fileBusinessType': DpUtil.changeDictionaryCodeToDescrip('CONTRACTTAX',DataDictionary.CONTRACT_ANNEX),
							'sourceId': '','sourceType': '','fileName': '','fileType': '','savePath': '','fileSize': ''})
			              ];
		}else if('ALTER' == status){
			attachData = [Ext.create('FileInfoModel',{'fileBusinessType':DpUtil.changeDictionaryCodeToDescrip('CHANGEPROTOCOL',DataDictionary.CONTRACT_ANNEX) ,
				'sourceId': '','sourceType': '','fileName': '','fileType': '','savePath': '','fileSize': ''}),
	            Ext.create('FileInfoModel',{'fileBusinessType': DpUtil.changeDictionaryCodeToDescrip('ELECTRONICCONTRACT',DataDictionary.CONTRACT_ANNEX),
	          	  	'sourceId': '','sourceType': '','fileName': '','fileType': '','savePath': '','fileSize': ''}),
	          	  	 //添加一般纳税人资质证明、税务登记证复印件、银行账号 CONTRACTTAX
			     Ext.create('FileInfoModel',{'fileBusinessType': DpUtil.changeDictionaryCodeToDescrip('CONTRACTTAX',DataDictionary.CONTRACT_ANNEX),
					'sourceId': '','sourceType': '','fileName': '','fileType': '','savePath': '','fileSize': ''})
	            ];
		}
	}else if('NEW' == status || 'UPDATE' == status){
		attachData = [Ext.create('FileInfoModel',{'fileBusinessType':DpUtil.changeDictionaryCodeToDescrip('BUSINESSLICENSE',DataDictionary.CONTRACT_ANNEX) ,
							'sourceId': '','sourceType': '','fileName': '','fileType': '','savePath': '','fileSize': ''}),
		              Ext.create('FileInfoModel',{'fileBusinessType': DpUtil.changeDictionaryCodeToDescrip('ELECTRONICCONTRACT',DataDictionary.CONTRACT_ANNEX),
		            	  	'sourceId': '','sourceType': '','fileName': '','fileType': '','savePath': '','fileSize': ''}),
		               //添加一般纳税人资质证明、税务登记证复印件、银行账号 CONTRACTTAX
			          Ext.create('FileInfoModel',{'fileBusinessType': DpUtil.changeDictionaryCodeToDescrip('CONTRACTTAX',DataDictionary.CONTRACT_ANNEX),
							'sourceId': '','sourceType': '','fileName': '','fileType': '','savePath': '','fileSize': ''})
		              ];
	}
	return attachData;
};
/**
 * 加载合同已绑定部门数据
 */
/**
 * 获得合同已绑定部门数据
 */
function getDeptData(contractView){
	var returnDeptData = [];
	if(contractView != null){
		var deptData = contractView.contract.contractDepartList;
		if(Ext.isEmpty(deptData)){
			deptData =[];
		}
		returnDeptData = deptData;
	}
	return returnDeptData;
};
/**
 * 加载合同工作流信息数据
 */
/**
 * 获得合同工作流信息数据
 */
function getWorkFlowData(contractView){
	var returnWorkFlowData = [];
	if(contractView != null){
		var workFlowData = contractView.contract.contractWorkflowList;
		if(Ext.isEmpty(workFlowData)){
			workFlowData =[];
		}
		for(var i = 0;i < workFlowData.length;i++){
			//如果没用工作流号则不显示
			if(!Ext.isEmpty(workFlowData[i].workflowId)){
				returnWorkFlowData.push(workFlowData[i]);
			}
		}
	}
	return returnWorkFlowData;
}
/**
 * 加载合同全部信息数据(新签合同是调用)
 */
function loadContractViewDataAfterRender(contractView,contractEditViewPanel){
	var me = contractEditViewPanel;
	//客户信息
	me.custInfo.loadCustInfoData(getCustInfoRecord(contractView));
	//合同主信息
	var contractRecord = getContractInfoRecord(contractView);
	if(Ext.isEmpty(contractRecord.get('deptId'))){contractRecord.set('deptId',top.User.deptId);}
	if(Ext.isEmpty(contractRecord.get('deptName'))){contractRecord.set('deptName',top.User.deptName);}
//	//向签署合同子公司里面添加一条数据
//	var signCompanyRecord = getSignCompanyRecord(contractView);
////	var sign = contractRecord.get('signCompany');
//	Ext.getCmp('signCompany_id').getStore().add(signCompanyRecord);
	me.contractInfo.contractTabBasicInfo.contractBasicInfo.loadContractInfoRecord(contractRecord);
	me.contractInfo.contractTabBasicInfo.contractBasicInfo.loadRecord(getPreferentialData(contractView));
	//快递合同初始化值
	var contractBasicForm = me.contractInfo.contractTabBasicInfo.contractBasicInfo.getForm();
	if(!Ext.isEmpty(contractView)&&!Ext.isEmpty(contractView.contract)){
		var exPreferentialData = contractView.contract.exPreferential;
		contractBasicForm.findField('exArrAmount').setValue(contractView.contract.exArrAmount);
		contractBasicForm.findField('exPayWay').setValue(contractView.contract.exPayWay);
		contractBasicForm.findField('exPreferentialType').setValue(contractView.contract.exPreferentialType);
		contractBasicForm.findField('exPriceVersionDate').setValue(contractRecord.data.exPriceVersionDate);
		contractBasicForm.findField('exModifyDate').setValue(getExPreferentialData(contractView).data.modifyDate);
		
		if(!Ext.isEmpty(exPreferentialData)){
			contractBasicForm.findField('exChargeRebate').setValue(exPreferentialData.chargeRebate);
			contractBasicForm.findField('exAgentgathRate').setValue(exPreferentialData.agentgathRate);
			contractBasicForm.findField('exInsuredPriceRate').setValue(exPreferentialData.insuredPriceRate);
		}
	}
	
	//优惠信息
//	me.contractInfo.contractTabBasicInfo.preferentialInfo.loadPreferentialData(getPreferentialData(contractView));
	//申请事由
	me.applyReasonInfo.loadApplication(getApplication(contractView));
	//上传附件
	me.fileInfo.loadAttachData(getAttachData(contractView,me.status));
	//绑定合同
	me.contractInfo.contractTabOtherInfo.deptInfo.loadDeptData(getDeptData(contractView));
	//工作流
	me.contractInfo.contractTabOtherInfo.workFlowInfo.loadWorkFlowData(getWorkFlowData(contractView));
};
/**
 * 加载合同全部信息数据(修改合同时调用)
 */
function loadContractViewData4Update(contractView,contractUpdateWin){
	var me = contractUpdateWin;
	//合同修改前数据
	var beforeForm = me.beforeUpdateContract.getForm();
	me.beforeUpdateContract.loadRecord(getCustInfoRecord(contractView));//客户基本信息
	me.beforeUpdateContract.loadRecord(getContractInfoRecord(contractView));//合同基本信息
	me.beforeUpdateContract.loadRecord(getPreferentialData(contractView));//优惠信息
	//快递优惠
	beforeForm.findField('exChargeRebate').setValue(getExPreferentialData(contractView).get('chargeRebate'));
	beforeForm.findField('exAgentgathRate').setValue(getExPreferentialData(contractView).get('agentgathRate'));
	beforeForm.findField('exInsuredPriceRate').setValue(getExPreferentialData(contractView).get('insuredPriceRate'));
	//合同待修改数据
	var afterForm = me.afterUpdateContract.getForm();
	afterForm.findField('signCompany').getStore().add({'signCompany':contractView.contract.signCompany});
	me.afterUpdateContract.loadRecord(getContractInfoRecord(contractView));
	me.afterUpdateContract.loadRecord(getPreferentialData(contractView));//优惠信息
	//快递优惠
	afterForm.findField('exChargeRebate').setValue(getExPreferentialData(contractView).get('chargeRebate'));
	afterForm.findField('exAgentgathRate').setValue(getExPreferentialData(contractView).get('agentgathRate'));
	afterForm.findField('exInsuredPriceRate').setValue(getExPreferentialData(contractView).get('insuredPriceRate'));
	//申请事由
	me.applyReasonInfo.loadApplication(getApplication(contractView));
	//上传附件
	me.fileInfo.down('grid').store.loadData([]);
};
/**
 * 获得合同全部数据用于提交
 */
//合同修改操作
/**
 * 初始化 合同修改信息 数据
 */
function initContractViewData4Update(contractView,contractUpdateWin){
	var me = contractUpdateWin;
	//合同修改前数据
	var beforeForm = me.beforeUpdateContract.getForm();
	me.beforeUpdateContract.loadRecord(getCustInfoRecord(contractView));//客户基本信息
	me.beforeUpdateContract.loadRecord(getContractInfoRecord(contractView));//合同基本信息
	me.beforeUpdateContract.loadRecord(getPreferentialData(contractView));//优惠信息
	//快递优惠
	beforeForm.findField('exChargeRebate').setValue(getExPreferentialData(contractView).get('chargeRebate'));
	beforeForm.findField('exAgentgathRate').setValue(getExPreferentialData(contractView).get('agentgathRate'));
	beforeForm.findField('exInsuredPriceRate').setValue(getExPreferentialData(contractView).get('insuredPriceRate'));
	//合同待修改数据
	var afterForm = me.afterUpdateContract.getForm();
	me.afterUpdateContract.loadRecord(getContractInfoRecord(contractView));
	me.afterUpdateContract.loadRecord(getPreferentialData(contractView));//优惠信息
	//快递优惠
	afterForm.findField('exChargeRebate').setValue(getExPreferentialData(contractView).get('chargeRebate'));
	afterForm.findField('exAgentgathRate').setValue(getExPreferentialData(contractView).get('agentgathRate'));
	afterForm.findField('exInsuredPriceRate').setValue(getExPreferentialData(contractView).get('insuredPriceRate'));
	//申请事由
	me.applyReasonInfo.loadApplication(getApplication(contractView));
	//上传附件
	me.fileInfo.down('grid').store.loadData([]);
};
/**
 * 合同"优惠类型"修改事件
 * field,
 * newValue,
 * handType:'CHANGE':改签，'UPDATE':修改
 * contractRecord,
 * contractInfoForm
 */
function preferentialTypeSlectEvent(field,record,handType,contractInfoForm,contractRecord){
	//月发月送享受增值折扣的前提是月结，即月结+月发月送+增值折扣必须同时享受；
	//月结+月发月送+增值折扣时：保价费率折扣、代收费率折扣可编辑，运费折扣、接货费折扣、送货费折扣不可编辑；
	var payWay = null;
	var form = contractInfoForm.getForm();
	var chargeRebate = form.findField('chargeRebate');//运费折扣
	var agentgathRate = form.findField('agentgathRate');//代收费率折
	var insuredPriceRate = form.findField('insuredPriceRate');//保价费率折扣
	var receivePriceRate = form.findField('receivePriceRate');//接货费 
	if('ALTER' == handType){//合同编辑界面 状态 UPDATE,NEW,VIEW,ALTER
		payWay = contractRecord.get('payWay');
	}else{
		payWay = form.findField('payWay').getValue();
	}
	if (record.get('code')!='MONTH_SEND'){
		form.findField('chargeRebate').setMinValue(0.800);
		form.findField('chargeRebate').setMaxValue(0.999);
	}
	dealPreferentialType(record.get('code'),payWay,chargeRebate,agentgathRate,insuredPriceRate,receivePriceRate);
};
//具体根据业务规则处理 优惠信息 【动态】
function dealPreferentialType(preferentialType,payWay,chargeRebate,agentgathRate,insuredPriceRate,receivePriceRate){
	var newValue = preferentialType;
	//优惠类型为无，或结款方式为无且优惠类型为月发月送时，折扣明细表不显示，否则就显示；
	if('NOT_PREFERENTIAL' == newValue || ('MONTH_SEND' == newValue && 'NOT_MONTH_END' == payWay)){
		setPreferential(true,chargeRebate,agentgathRate,insuredPriceRate,receivePriceRate);
	}else{
		setPreferential(false,chargeRebate,agentgathRate,insuredPriceRate,receivePriceRate);
	}
	
	//优惠类型为价格折扣时：运费折扣、保价费率折扣、代收费率折扣可编辑，接货费折扣和送货费折扣不可编辑，运费折扣大于等于8.5折；
	if('PRICE_REBATE' == newValue){
		setPreferential(false,chargeRebate,agentgathRate,insuredPriceRate,receivePriceRate);
	}
	//月结+月发月送+增值折扣时：保价费率折扣、代收费率折扣可编辑，运费折扣、接货费折扣、送货费折扣不可编辑；
	if('MONTH_SEND' == newValue && 'MONTH_END' == payWay){
		setPreferential(false,chargeRebate,agentgathRate,insuredPriceRate,receivePriceRate);
		chargeRebate.setReadOnly(true);
	}
};
//具体根据业务规则处理 优惠信息 【动态】处理折扣信息
function setPreferential(flag,chargeRebate,agentgathRate,insuredPriceRate,receivePriceRate){
	chargeRebate.setValue();
	agentgathRate.setValue();
	insuredPriceRate.setValue();
	receivePriceRate.setValue();
	afterShowSetPreferential(flag,chargeRebate,agentgathRate,insuredPriceRate,receivePriceRate,null);
};
//具体根据业务规则处理 优惠信息 【静态】
function afterShowDealPreferentialType(preferentialType,payWay,chargeRebate,agentgathRate,insuredPriceRate,receivePriceRate,operate){
	var newValue = preferentialType;
	//优惠类型为无，或结款方式为无且优惠类型为月发月送时，折扣明细表不显示，否则就显示；
	if('NOT_PREFERENTIAL' == newValue || ('MONTH_SEND' == newValue && 'NOT_MONTH_END' == payWay)){
		afterShowSetPreferential(true,chargeRebate,agentgathRate,insuredPriceRate,receivePriceRate,operate);
	}else{
		afterShowSetPreferential(false,chargeRebate,agentgathRate,insuredPriceRate,receivePriceRate,operate);
	}
	//优惠类型为价格折扣时：运费折扣、保价费率折扣、代收费率折扣可编辑，接货费折扣和送货费折扣不可编辑，运费折扣大于等于8.5折；
	if('PRICE_REBATE' == newValue){
		afterShowSetPreferential(false,chargeRebate,agentgathRate,insuredPriceRate,receivePriceRate,operate);
	}
	//月结+月发月送+增值折扣时：保价费率折扣、代收费率折扣可编辑，运费折扣、接货费折扣、送货费折扣不可编辑；
	if('MONTH_SEND' == newValue && 'MONTH_END' == payWay){
		afterShowSetPreferential(false,chargeRebate,agentgathRate,insuredPriceRate,receivePriceRate,operate);
		chargeRebate.setReadOnly(true);
	}
};
//具体根据业务规则处理 优惠信息 【静态】处理折扣信息 flag:true:不可编辑，false：可编辑
function afterShowSetPreferential(flag,chargeRebate,agentgathRate,insuredPriceRate,receivePriceRate,operate){
	if('UPDATE'==operate || 'ALTER'==operate){
		chargeRebate.setReadOnly(true);
		agentgathRate.setReadOnly(true);
		insuredPriceRate.setReadOnly(true);
		receivePriceRate.setReadOnly(true);
	}else{
		chargeRebate.setReadOnly(flag);
		agentgathRate.setReadOnly(flag);
		insuredPriceRate.setReadOnly(flag);
		receivePriceRate.setReadOnly(flag);
	}
};
/**
 * 加载数据时 处理结款方式，优惠类型和折扣信息
 * contractRecord,
 * contractInfoForm
 */
function showDateByBusiness(contractRecord,contractInfoForm,operate){
	var payWay = contractRecord.get('payWay');
	afterShowPayWaySlectEventBody(payWay,contractInfoForm,operate);
};
/**
 * 合同"结款方式"修改事件
 * field,
 * newValue,
 * handType,
 * contractRecord,
 * contractInfoForm
 */
function payWaySlectEvent(field,record,handType,contractInfoForm,contractRecord,contractType){
	var monthEndDays = null;
	
	var viewContractDetailSuccess = function(result){
		//如果是改签，并且存在合同的
		if('UPDATE' == handType && !Ext.isEmpty(contractRecord.get('beforeContractNum'))
				&& ('MONTH_END' == contractRecord.data.payWay || 'MONTH_END' == contractRecord.data.exPayWay)){//合同编辑界面 状态 UPDATE,NEW,VIEW,ALTER
			monthEndDays = contractRecord.data.debtDays;
		}else{
			monthEndDays = result.debtDays;
		}		
		var payWay = record.get('code');//付款方式
		if('LD'==contractType){
			//走零担分支
			payWaySlectEventBody(payWay,monthEndDays,contractInfoForm);
		}else{
			//走快递分支
			payWayExpSelectEventBody(payWay,monthEndDays,contractInfoForm);
		}
	}
	var viewContractDetailFail = function(){
		MessageUtil.showErrorMes('默认合同月结天数初始化失败！');
	}
	var paramss = {};
	contractControl.initMonthEndDay(paramss,viewContractDetailSuccess,viewContractDetailFail);
//	var payWay = record.get('code');//付款方式
//	payWaySlectEventBody(payWay,monthEndDays,contractInfoForm);
};
/****************快递合同******************/
function payWayExpSelectEventBody(payWayExp,monthEndDays,contractInfoForm){
	var payWay = contractInfoForm.getForm().findField('payWay').getValue();
	
	if('MONTH_END' != payWayExp && 'MONTH_END' != payWay){
		Ext.getCmp('commonDebtDays_id').setValue(0);
		contractInfoForm.getForm().findField('arrearaMount').allowBlank = true;
		//如果快递和零担都是月结，则将结算限额和结款日期都设置为只读
		contractInfoForm.getForm().findField('arrearaMount').setReadOnly(true);
		contractInfoForm.getForm().findField('resultDate').setReadOnly(true);
		contractInfoForm.getForm().findField('arrearaMount').setValue();
		contractInfoForm.getForm().findField('resultDate').setValue();
		contractInfoForm.getForm().findField('resultDate').allowBlank = true;
	}else{
		contractInfoForm.getForm().findField('arrearaMount').setValue();
		Ext.getCmp('commonDebtDays_id').setValue(monthEndDays);
		contractInfoForm.getForm().findField('arrearaMount').allowBlank = false;
		contractInfoForm.getForm().findField('arrearaMount').setReadOnly(false);
		contractInfoForm.getForm().findField('resultDate').setReadOnly(false);
		contractInfoForm.getForm().findField('resultDate').allowBlank = false;
	}
}
//快递合同只在价格折扣的时候可以编辑运费折扣
function preferentialExpTypeSlectEvent(field,record,handType,contractInfoForm,contractRecord){
	var form = contractInfoForm.getForm();
	var chargeRebate_Exp = form.findField('exChargeRebate');
	var preferentialType_Exp = form.findField('exPreferentialType').getValue();
	if (record.get('code')!='PRICE_REBATE'){
		chargeRebate_Exp.setReadOnly(true);
	}else{
		chargeRebate_Exp.setReadOnly(false);
		chargeRebate_Exp.reset();
	}
}

/****************零担合同******************/

//【动态】
function payWaySlectEventBody(payWay,monthEndDays,contractInfoForm){
	var form = contractInfoForm.getForm();
	var chargeRebate = form.findField('chargeRebate');//运费折扣
	var agentgathRate = form.findField('agentgathRate');//代收费率折
	var insuredPriceRate = form.findField('insuredPriceRate');//保价费率折扣
	var receivePriceRate = form.findField('receivePriceRate');//接货费
	var preferentialType = form.findField('preferentialType').getValue();//优惠类型
	var arrearaMount = form.findField('arrearaMount');//结算限额
	var resultDate = form.findField('resultDate');//结款日期
	var reconDate = form.findField('reconDate');// 对账日期
	var invoicDate = form.findField('invoicDate');// 开发票日期
	var parWayExp = form.findField('exPayWay').getValue();//快递合同结款方式
	//结款方式为月结时，结算限额和结款日期为必填项，否则非必填不可编辑； 
	if('MONTH_END' != payWay && 'MONTH_END' !=parWayExp){
		dealPayWay(true,arrearaMount,resultDate,reconDate,invoicDate);//必填
		arrearaMount.setValue();
		resultDate.setValue();
		Ext.getCmp('commonDebtDays_id').setValue(0);
	}else{
		dealPayWay(false,arrearaMount,resultDate,reconDate,invoicDate);//必填
		Ext.getCmp('commonDebtDays_id').setValue(monthEndDays);
		arrearaMount.setValue();
	}
	dealPreferentialType(preferentialType,payWay,chargeRebate,agentgathRate,insuredPriceRate,receivePriceRate);
};
//【静态】
function afterShowPayWaySlectEventBody(payWay,contractInfoForm,operate){
	var form = contractInfoForm.getForm();
	var chargeRebate = form.findField('chargeRebate');//运费折扣
	var agentgathRate = form.findField('agentgathRate');//代收费率折
	var insuredPriceRate = form.findField('insuredPriceRate');//保价费率折扣
	var receivePriceRate = form.findField('receivePriceRate');//接货费
	var preferentialType = form.findField('preferentialType').getValue();//优惠类型
	var arrearaMount = form.findField('arrearaMount');//结算限额
	var resultDate = form.findField('resultDate');//结款日期
	var reconDate = form.findField('reconDate');// 对账日期
	var invoicDate = form.findField('invoicDate');// 开发票日期
	var parWayExp = form.findField('exPayWay').getValue();//快递合同结款方式
	//结款方式为月结时，结算限额和结款日期为必填项，否则非必填不可编辑； 
	if('MONTH_END' != payWay && 'MONTH_END' !=parWayExp){
		afterShowdealPayWay(true,arrearaMount,resultDate,reconDate,invoicDate);//必填
		arrearaMount.setValue();
		resultDate.setValue();
	}else{
		afterShowdealPayWay(false,arrearaMount,resultDate,reconDate,invoicDate);//必填
	}
	afterShowDealPreferentialType(preferentialType,payWay,chargeRebate,agentgathRate,insuredPriceRate,receivePriceRate,operate);
};
/**
 * "结款方式" 【动态】加载业务数据规则 渲染界面
 */
function dealPayWay(flag,arrearaMount,resultDate,reconDate,invoicDate){
	arrearaMount.allowBlank=flag;
	arrearaMount.doComponentLayout();
	afterShowdealPayWay(flag,arrearaMount,resultDate,reconDate,invoicDate)
	if(!flag){arrearaMount.clearInvalid();}
	resultDate.allowBlank=flag;
	resultDate.doComponentLayout();
};
/**
 * "结款方式" 【静态】加载业务数据规则 渲染界面
 */
function afterShowdealPayWay(flag,arrearaMount,resultDate,reconDate,invoicDate){
	arrearaMount.setReadOnly(flag);
	resultDate.setReadOnly(flag);
	reconDate.setValue(0);
	invoicDate.setValue(0);
};
/**
 * 处理优惠信息 用于界面展示
 * preferentialRecord,
 * contractInfoForm,
 */
function dealContractPreferential4Show(preferentialRecord,contractInfoForm){
	var form = contractInfoForm.getForm();
	var chargeRebate = form.findField('chargeRebate');//运费折扣
	var agentgathRate = form.findField('agentgathRate');//代收费率折
	var insuredPriceRate = form.findField('insuredPriceRate');//保价费率折扣
	var comparValue = ['1',1,'0',0];
	//如果是 0或者1 则展示充空
	if(changePreferential4Show(preferentialRecord.get('chargeRebate'),comparValue)){
		chargeRebate.setValue();
	};
	if(changePreferential4Show(preferentialRecord.get('agentgathRate'),comparValue)){
		agentgathRate.setValue();
	};
	if(changePreferential4Show(preferentialRecord.get('insuredPriceRate'),comparValue)){
		insuredPriceRate.setValue();
	};
};
/**
 * 处理快递优惠信息 用于界面展示
 * preferentialRecord,
 * contractInfoForm,
 */
function dealContractExPreferential4Show(preferentialRecord,contractInfoForm){
	var form = contractInfoForm.getForm();
	var chargeRebate = form.findField('exChargeRebate');//运费折扣
	var agentgathRate = form.findField('exAgentgathRate');//代收费率折
	var insuredPriceRate = form.findField('exInsuredPriceRate');//保价费率折扣
	var comparValue = ['1',1,'0',0];
	//如果是 0或者1 则展示充空
	if(changePreferential4Show(preferentialRecord.get('chargeRebate'),comparValue)){
		chargeRebate.setValue();
	};
	if(changePreferential4Show(preferentialRecord.get('agentgathRate'),comparValue)){
		agentgathRate.setValue();
	};
	if(changePreferential4Show(preferentialRecord.get('insuredPriceRate'),comparValue)){
		insuredPriceRate.setValue();
	};
};
function changePreferential4Show(fromValue,comparValue){
	var flag = false;
	for(var i = 0;i<comparValue.length;i++){
		if(fromValue == comparValue[i]){
			flag = true;
		}
	}
	return flag;
}
/**
 * 处理优惠信息 用于提交 折扣为空则传到后台为1（后台接收类型为Double）
 * preferentialRecord
 */
function dealContractPreferential4Commit(preferentialRecord){
	if(Ext.isEmpty(preferentialRecord.get('chargeRebate'))
			|| 0 == preferentialRecord.get('chargeRebate')){
		preferentialRecord.set('chargeRebate',1)//运费折扣费率
	};
	if(Ext.isEmpty(preferentialRecord.get('agentgathRate'))
			|| 0 == preferentialRecord.get('agentgathRate')){
		preferentialRecord.set('agentgathRate',1)//代收货款费率
	};
	if(Ext.isEmpty(preferentialRecord.get('insuredPriceRate'))
			|| 0 == preferentialRecord.get('insuredPriceRate')){
		preferentialRecord.set('insuredPriceRate',1)//保价费率
	};
//	preferentialRecord.set('receivePriceRate',1);//接货费率
	preferentialRecord.set('deliveryPriceRate',1);//送货费率
};
/**
 * 通过记录判断 终止按钮是显示终止还是删除
 * 合同状态是无效且工作流状态是不同意则返回 删除 否则返回 终止
 * record选中记录
 */
function judgeInvalidOrDel(record) {
	var workflowLog = workflowLogResult(record);
	if ('2' != record.get('contractStatus')) {
		return '终止';
	} else if ('3' != workflowLog[2]) {
		return '终止';
	} else if ('insert' != workflowLog[1] && 'changeSign' != workflowLog[1]
			&& 'update' != workflowLog[1]) {
		return '终止';
	} else if ('MONTH_END' == record.get('payWay')
			|| 'PRICE_REBATE' == record.get('preferentialType')
			|| 'MONTH_END' == record.get('exPayWay')
			|| 'PRICE_REBATE' == record.get('exPreferentialType')) {
		return '删除';
	}
	return '终止';
};
/**
 * 通过记录判断 最后操作 类型及操作工作流结果
 * record选中记录
 * returnArr[合同状态（ 0审批中1生效 2失效3待生效），
 * 				操作类型（insert：新签，changeSign：新签，update修改），
 * 				工作流结果(1：审批中，2：同意，3:不同意)]
 */
function workflowLogResult(record){
	var returnArr = ['','',''];
	var workflowLog = record.get('contractWorkflowList');
	if(!Ext.isEmpty(workflowLog)){
		returnArr[0]=record.get('contractStatus');
		if(workflowLog.length>0){
			returnArr[1]=workflowLog[0].operatorType;
			returnArr[2]=workflowLog[0].approvalState;
		}
	}
	return returnArr;
}
//if(workflowLog.length>1){
//	if(new Date(workflowLog[1].createDate)>new Date(workflowLog[0].modifyDate)){
//		returnArr[1]=workflowLog[1].operatorType;
//		returnArr[2]=workflowLog[1].approvalState;
//	}else{
//		returnArr[1]=workflowLog[0].operatorType;
//		returnArr[2]=workflowLog[0].approvalState;
//	}
//}else{
//	returnArr[1]=workflowLog[0].operatorType;
//	returnArr[2]=workflowLog[0].approvalState;
//}
/**
 * 通过记录判断 最后操作 类型及操作工作流结果
 * arrearaMount结算限额
 * return true：未超过，false：超过
 */
//前端校验
//校验结果标准：能够满足近两个月连续发货金额都大于等于3000元/月，
//且申请的结算额度未超过近三月发货金额最高月的两倍，校验结果为“满足”，否则校验结果为“不满足”
//如有修改结算限额，则校验填写的额度是否超过近三月发货金额最高月的两倍，
//超过则提示“结算限额已超过近三月发货金额最高月的两倍，请确定是否提交！”
//arrAmount:近三月发货金额(小月到大月),arrearaMount:结算限额，返回true为不超过false为超过

function isNotOverMax3Amount(arrAmount,arrearaMount){
	var flag = true;
	var arr3Amount = arrAmount.split('\\');
	arrearaMount = Ext.isEmpty(arrearaMount)?0:arrearaMount;
//	if(arr3Amount[1]>=3000&&arr3Amount[2]>=3000){
//		flag = arrearaMount<=2*getMaxIn3Num(arr3Amount[0],arr3Amount[1],arr3Amount[2]);
//	}else{
//		flag = true;
//	}
	return arrearaMount<=2*getMaxIn3Num(arr3Amount[0],arr3Amount[1],arr3Amount[2]);;
};
/**
 * 若只是零担合同是月结，则只校验零担的近三个月发货金额；
 * 若只是快递合同是月结，则只校验快递的近三个月发货金额；
 * 若零担和快递合同都是月结，则将两个业务的近三个月的对应月的发货金额相加之后再进行校验
 * @author 唐亮
 * @param contract
 * @param exPayWay
 * @param payWay
 * @param amount
 * @param arrAmount
 * @param exArrAmount
 * @returns {Boolean}
 */
function dealContractArrMount(contract,exPayWay,payWay,amount,arrAmount,exArrAmount){
	if('MONTH_END'!=exPayWay && 'MONTH_END'==payWay){
		return contract.get('arrearaMount') !=amount && !Ext.isEmpty(amount)
		&& !isNotOverMax3Amount((Ext.isEmpty(arrAmount)||0==arrAmount)?'0\\0\\0':arrAmount,amount);
	}else if('MONTH_END'==exPayWay && 'MONTH_END'!=payWay){
		return contract.get('arrearaMount') !=amount && !Ext.isEmpty(amount)
		&& !isNotOverMax3Amount((Ext.isEmpty(exArrAmount)||0==exArrAmount)?'0\\0\\0':exArrAmount,amount);
	}else if('MONTH_END'==exPayWay && 'MONTH_END'==payWay && !Ext.isEmpty(amount)
			&&contract.get('arrearaMount') !=amount){
		var exArr3Amount = ((Ext.isEmpty(exArrAmount)||0==exArrAmount)?'0\\0\\0':exArrAmount).split('\\');
		var arr3Amount = ((Ext.isEmpty(arrAmount)||0==arrAmount)?'0\\0\\0':arrAmount).split('\\');
		/**
		 * 应该分别取零担和快递的近三个月最高金额，然后再加起来判断
		 */
		var LTTmaxIn3Num=getMaxIn3Num(parseFloat(arr3Amount[0]),parseFloat(arr3Amount[1]),parseFloat(arr3Amount[2]));
		var EXmaxIn3Num=getMaxIn3Num(parseFloat(exArr3Amount[0]),parseFloat(exArr3Amount[1]),parseFloat(exArr3Amount[2]));
		arrearaMount = Ext.isEmpty(amount)?0:amount;
		return arrearaMount>2*(LTTmaxIn3Num+EXmaxIn3Num);
	}
};
//获得三个数字中最大的数字
function getMaxIn3Num(num1,num2,num3){
	 num1=parseFloat(num1);
	 num2=parseFloat(num2);
	 num3=parseFloat(num3);
	var maxNum = num1>num2?num1:num2;
	return maxNum>num3?maxNum:num3;
}
//调用后台
//如有修改结算限额，则校验填写的额度是否超过近三月发货金额最高月的两倍，
//超过则提示“结算限额已超过近三月发货金额最高月的两倍，请确定是否提交！”
function isNotOverMax3AmountBackstage(memberId, arrearaMount) {
	var flag = true;
	// 合同 修改 功能
	var fnSuccess = function(response) {
		flag = response.checked;
	};
	var fnFail = function(response) {
		MessageUtil.showErrorMes(response.message);
	}
	contractControl.isNotOverMax3Amount({
		'memberId' : memberId,
		'amount' : arrearaMount
	}, fnSuccess, fnFail);
	return flag;
};
/**
 * 验证该客户是否能签订零担月结
 * @param arrAmount
 * @returns boolean flag
 */
function checkCustCanSignLdMontEnd(arrAmount){
	var flag = true;
	var arrAmount = (Ext.isEmpty(arrAmount)||0==arrAmount)?'0\\0\\0':arrAmount;
	var arry = arrAmount.split('\\'); 
	if(parseFloat(arry[0]) ==0 && parseFloat(arry[1]) ==0 && parseFloat(arry[2]) ==0) {
		flag = true;
	} else if((parseFloat(arry[0])>= 3000&& parseFloat(arry[1])>= 3000) || 
		(parseFloat(arry[1])>= 3000&& parseFloat(arry[2])>= 3000)) {
		flag = true;
	} else {
		flag = false;
	}
	return flag;
};
/**
 * 验证该客户是否能签订快递月结
 * @param arrAmount
 * @returns boolean flag
 */
function checkCustCanSignKdMontEnd(exArrAmount){
	var flag = true;
	var exArrAmount = (Ext.isEmpty(exArrAmount)||0==exArrAmount)?'0\\0\\0':exArrAmount;
	var arry = exArrAmount.split('\\'); 
	if(parseFloat(arry[0]) ==0 && parseFloat(arry[1]) ==0 && parseFloat(arry[2]) ==0) {
		flag = true;
	} else if((parseFloat(arry[0])>= 600&& parseFloat(arry[1])>= 600) || 
		(parseFloat(arry[1])>= 600&& parseFloat(arry[2])>= 600)) {
		flag = true;
	} else {
		flag = false;
	}
	return flag;
};
/**
 * 点击月结的时候 同时判断该客户是否能签订零担月结，并
 * @param arrAmount
 */
function monthEndPayWayEvent(arrAmount,me) {
	var contractBasForm = me.getForm();
	var exArrAmount = contractBasForm.findField('exArrAmount').getValue();
	if(Ext.isEmpty(arrAmount) && Ext.isEmpty(exArrAmount)){
		Ext.getCmp("showNameDisplay").setValue("<span'></span>");
	} else{
		if(checkCustCanSignLdMontEnd(arrAmount)){
				//&& checkCustCanSignKdMontEnd(exArrAmount)){
			Ext.getCmp("showNameDisplay").setValue("<span'></span>");
//		}else if(!checkCustCanSignLdMontEnd(arrAmount)
//				&& !checkCustCanSignKdMontEnd(exArrAmount)
//				&& contractBasForm.findField('ifExpress').getValue()){
//			Ext.getCmp("showNameDisplay").setValue("<span style='color:#ff0000;font-size:10px'>友情提醒：该客户不满足月结客户条件，不能签订月结合同!</span>");
//			Ext.getCmp('contractSaveButton').setDisabled(true);
		}else if(!checkCustCanSignLdMontEnd(arrAmount)){
			Ext.getCmp("showNameDisplay").setValue("<span style='color:#ff0000;font-size:10px'>友情提醒：该客户不满足零担月结客户条件，不能签订零担月结合同!</span>");
			Ext.getCmp('contractSaveButton').setDisabled(true);
//		}else if(checkCustCanSignLdMontEnd(arrAmount)
//				&&!checkCustCanSignKdMontEnd(exArrAmount)
//				&&contractBasForm.findField('ifExpress').getValue()
//				&&'MONTH_END' ==contractBasForm.findField('exPayWay').getValue()){
//			Ext.getCmp("showNameDisplay").setValue("<span style='color:#ff0000;font-size:10px'>友情提醒：该客户不满足快递月结客户条件，不能签订快递月结合同!</span>");
//			Ext.getCmp('contractSaveButton').setDisabled(true);
		}
	}
};
/**
 * 点击月结的时候 同时判断该客户是否能签快递月结，并
 * @param arrAmount
 */
function monthEndExPayWayEvent(exArrAmount,me) {
	var contractBasForm = me.getForm();
	var arrAmount = contractBasForm.findField('arrAmount').getValue();
	if(Ext.isEmpty(exArrAmount) && Ext.isEmpty(arrAmount)){
		Ext.getCmp("showNameDisplay").setValue("<span'></span>");
	} else{
		if(checkCustCanSignKdMontEnd(exArrAmount)
				&&checkCustCanSignLdMontEnd(arrAmount)){
			Ext.getCmp("showNameDisplay").setValue("<span'></span>");
		}else if(!checkCustCanSignKdMontEnd(exArrAmount)
				&&!checkCustCanSignLdMontEnd(arrAmount)
				&&contractBasForm.findField('ifLingDan').getValue()){
			Ext.getCmp("showNameDisplay").setValue("<span style='color:#ff0000;font-size:10px'>友情提醒：该客户不满足月结客户条件，不能签订月结合同!</span>");
			Ext.getCmp('contractSaveButton').setDisabled(true);
		}else if(!checkCustCanSignKdMontEnd(exArrAmount)){
			Ext.getCmp("showNameDisplay").setValue("<span style='color:#ff0000;font-size:10px'>友情提醒：该客户不满足快递月结客户条件，不能签订快递月结合同!</span>");
			Ext.getCmp('contractSaveButton').setDisabled(true);
		}else if(checkCustCanSignKdMontEnd(exArrAmount)
				&&!checkCustCanSignLdMontEnd(arrAmount)
				&&contractBasForm.findField('ifLingDan').getValue()
				&&'MONTH_END' ==contractBasForm.findField('payWay').getValue()){
			Ext.getCmp("showNameDisplay").setValue("<span style='color:#ff0000;font-size:10px'>友情提醒：该客户不满足零担月结客户条件，不能签订零担月结合同!</span>");
			Ext.getCmp('contractSaveButton').setDisabled(true);
		}
	}
};
//检验合同的起始时间
function checkContractDateIsRight(params,successFn,failFn){
	var checkContrcactDateUrl = "isAllowCreatContractDate.action";
	DpUtil.requestJsonAjax(checkContrcactDateUrl,params,successFn,failFn);
};
	//检验合同的起始时间
	function checkContractDate(){
		var contractEndDate = Ext.getCmp('contractEndTime_id').getValue();
		var contractBeginDate = Ext.getCmp('contractStartTime_id').getValue();
		var custNumber = Ext.getCmp('custNumber_id').getValue();
		if(!Ext.isEmpty(contractEndDate) && !Ext.isEmpty(contractBeginDate) && !Ext.isEmpty(custNumber)) {
			var saveSuccess = function(result){
				if(result.checked == false){
					var date = DpUtil.changeLongToDate(result.contractEndDate);
					var old = date.getFullYear()+'' + (date.getMonth()+1) + date.getDate();//选择的时间
					var now =  contractEndDate.getFullYear()+''+ (contractEndDate.getMonth()+1)+ contractEndDate.getDate();//数据库时间
					if(!(now == old)){
						MessageUtil.showInfoMes(i18n('i18n.contract.custmerHasEffectContract_DateIsError'));
						Ext.getCmp('contractEndTime_id').setValue(DpUtil.changeLongToDate(result.contractEndDate));
					}
			}
			}
			var saveFail = function(result){
				MessageUtil.showErrorMes(result.message);	
			}
			var saveParams = {};
			saveParams.contractEndDate = contractEndDate;
			saveParams.contractBeginDate = contractBeginDate;
			saveParams.custNumber = custNumber;
			checkContractDateIsRight(saveParams,saveSuccess,saveFail);
			
		}
	};

/**
* 为续签的时候提供前端显示为空 
*/
function completePriceVersionDate(){
	var payWay = Ext.getCmp('payWay_id').getValue();//零担结款方式:'MONTH_END'
	var exPayWay = Ext.getCmp('payWay_id_Express').getValue();//快递结款方式:'MONTH_END'
	var priceVersionDate = Ext.getCmp('priceVersionDate_id');
	var exPriceVersionDate = Ext.getCmp('priceVersionDate_id_Express');
	if(payWay == 'MONTH_END'){
		priceVersionDate.setValue(null);
	}
	if(exPayWay == 'MONTH_END'){
		exPriceVersionDate.setValue(null);
	}
};
/**
 * 判断 优惠折扣详情是否都是空
 * contractRecord合同record，form合同基本信息
 * form，同时包括 运费折扣 代收费率折扣 保价费率折扣
 * return true：全部为空，false：不是全部为空
 */
function isPreferentialAllNull(contractRecord,form){
	var chargeRebate = form.findField('chargeRebate').getValue();//运费折扣
	var agentgathRate = form.findField('agentgathRate').getValue();//代收费率折扣
	var insuredPriceRate = form.findField('insuredPriceRate').getValue();//保价费率折扣
	var receivePriceRate = form.findField('receivePriceRate').getValue();//接货费 
	return Ext.isEmpty(chargeRebate)
		&&Ext.isEmpty(agentgathRate)
		&&Ext.isEmpty(insuredPriceRate)
		&&Ext.isEmpty(receivePriceRate);
}
/**
 * 判断 快递优惠信息之运费折扣是否为空
 * @param form
 */
function isExPreferentialNull(form){
	var exChargeRebate = form.findField('exChargeRebate').getValue();//运费折扣
	return Ext.isEmpty(exChargeRebate);
}
/**
 * 处理合同修改的时候，优惠类型修改的事件
 * @param field
 * @param contract
 */
function preSelectEvent(field,contract,me){
	var form = me.getForm();
	var preType = field.getValue();
	var payWay = contract.get('payWay');
	if(preType=='MONTH_SEND'&&payWay=='MONTH_END'){
			form.findField('chargeRebate').setReadOnly(true);
	}
}
/**
 * 处理后来传到前台的折扣信息，如果为1则转为null
 * @param value
 * @returns
 */
function dealPreData(value){
	if('1'==value || 1== value || '0'==value || 0 == value){
		return null;
	}else{
		return value;
	}
}
/**
 * 将传入的数据从空设为1
 * @param value
 */
function chnangNull2One(value){
	if(Ext.isEmpty(value) || 0 == value || '0' == value){
		return 1;
	}else{
		return value;
	}
}
/**
 * 校验零担合同的结款方式和优惠信息是否已经修改
 * @param contract
 * @param preferential
 * @param form
 */
function preIsNotModified(form,contract,preferential){
	if(form.findField('ifLingDan').getValue()
			&&(form.findField('payWay').getValue()==contract.data.payWay)
			&&(form.findField('preferentialType').getValue()==contract.data.preferentialType)
			&&(form.findField('chargeRebate').getValue()
					==dealPreData(preferential.data.chargeRebate))
			&&(form.findField('agentgathRate').getValue()
					==dealPreData(preferential.data.agentgathRate))
			&&(form.findField('insuredPriceRate').getValue()
					==dealPreData(preferential.data.insuredPriceRate))
			&&(form.findField('receivePriceRate').getValue()
				==preferential.data.receivePriceRate)){
		return true;
	}else{
		return false;
	}
};
/**
 * 校验快递合同的结款方式和优惠信息是否已经修改
 * @param form
 * @param contract
 * @param exPreferential
 * @returns {Boolean}
 */
function exPreIsNotModified(form,contract,exPreferential){
	if(form.findField('ifExpress').getValue()
			&&(form.findField('exPayWay').getValue()==contract.data.exPayWay)
			&&(form.findField('exPreferentialType').getValue()==contract.data.exPreferentialType)
			&&(form.findField('exChargeRebate').getValue()
					==dealPreData(exPreferential.data.chargeRebate))
			&&(form.findField('exAgentgathRate').getValue()
					==dealPreData(exPreferential.data.agentgathRate))
			&&(form.findField('exInsuredPriceRate').getValue()
					==dealPreData(exPreferential.data.insuredPriceRate))){
		return true;
	}else{
		return false;
	}
};