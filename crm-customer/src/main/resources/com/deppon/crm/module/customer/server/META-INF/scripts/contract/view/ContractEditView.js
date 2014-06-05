/**
 * 合同主编辑界面win									ContractEditWin
 * 合同编辑主界面panel80								ContractEditView
 * 客户基本信息panel									ContractForm
 * 合同信息与其他信息tap								ContractTabPanel
 * 合同基本信息tap									ContractTabContent
 * 合同基本信息panel									ContractBasicForm
 * 合同优惠折扣详情grid								PreferentialGrid
 * 合同其他信息panel									OtherTabContent
 * 合同绑定部门grid									DeptGrid
 * 合同工作流grid									WorkFlowGrid
 * 合同申请事由panel									ApplyReasonPanel
 * 合同附件panel+grid								ContractAttachment
 * 上传附件win										UploadAttachmentWin
 * 合同修改界面win									ContractUpdateWin
 * 合同上传附件(上传按钮)form							UploadAttachmentForm
 * 合同上传附件grid									ContractAttachment
 */
//加载 合同编辑service
var contractControl = (CONFIG.get('TEST'))? Ext.create('ContractBasicDataTest'):Ext.create('ContractBasicData');
var contractManagerDataControl =  (CONFIG.get("TEST"))? new ContractManagerDataTest():new ContractManagerData();
var contractMonEndData =  new ContractMonthEndData();
(function() {
	var contractEditBusiness  = "../scripts/customer/contract/service/ContractEditService.js?201311271357";
    if(!CONFIG.get('TEST')){
		document.write('<script type="text/javascript" src="' + contractEditBusiness + '"></script>');
	}else{}
})();
//var contractControl = Ext.create('ContractBasicData');
ContractEditEditView = function(){};
//获得本月的最后一天
ContractEditEditView.getMontLastDay = function(endTime){
	if(!Ext.isEmpty(endTime)){
		var date = endTime;
		var m = endTime.getMonth();
		var d = endTime.getDate();
		date.setMonth(m+1)
		date.setDate(0);
		//通过调试的显示月如果当月天数为31天最后得到时间为下个月所以时间因该再减一个月才为显示月的最后一天
		if(d == 31 && !(m == 11 || m == 6)){
			date.setDate(0);
		}
//		Ext.getCmp('contractEndTime_id').setValue(date);
		return date;
	}
	return null;
};
/**
 * 合同编辑主窗口
 */
	Ext.define('ContractEditWin',{
		extend:'PopWindow',
		width:800,
		x:0,
		y:0,//距离界面顶层 距离
		height:775,
		store:null,
		title:i18n('i18n.ContractEditView.contractEditer'),
		status:'NEW',//NEW 新建 UPDAE 更新 VIEW 查看  默认为新增
		contractView:null,//合同contractViewview
		contractViewPanel:null,//合同contractViewview
		member:null,//合同的客户信息
		layout : {
			type : 'fit'
		},
		initComponent:function(){
			var me = this;
			me.contractViewPanel = Ext.create('ContractEditView',{
				'parentWin':me,
				'store':me.store,
				'contractData':contractControl,
				'contractView':me.contractView,
				'member':me.member,
				'status':me.status});
			me.items = [me.contractViewPanel];
			this.callParent();
		}
		});
	
/**
 * 日期校验
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
	         if(end.getValue() < new Date()){
	            end.setMinValue(new Date());
	      	 }
//	      	 var end = field.up('form').getForm().findField(field.endDateFieldName);
//	         var c_month = date.getMonth();//选择时间的月份
//	         var c_date = date.getDate();//选择时间的天数
//	         var temp_date = new Date();
//	         if(c_date==DpUtil.getCurrentMonthDays(c_month)){
//	         	temp_date.setMonth(c_month+1);
//	         	temp_date.setDate(1);
//	         }else{
//	         	temp_date.setDate(c_date+1);
//	         }
//	         end.setMinValue(temp_date);
//	         end.validate();
//	         this.dateRangeMin = temp_date;
//	         if(temp_date < new Date()){
//	            end.setMinValue(new Date());
//	      	 }
	     }
	     return true;
};
Ext.form.field.VTypes.dateRangeText = i18n('i18n.ContractEditView.contractStartDateSmallEndDate');
/**
 * 合同编辑主页面
 */
Ext.define('ContractEditView', {
	extend : 'BasicPanel',
	items : null,
	store:null,
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	custInfo : null,      //客户基本信息
	contractInfo : null,  //合同主体信息
	applyReasonInfo : null,  //申请事由
	fileInfo : null,         //上传附件
	uploadAttachment:null,	//上传附件form
	parentWin:null,   //父窗口
	contractData:null,  //Data层接口
	contractView:null,//合同contractViewview
	member:null,//合同contractViewview
	showFbar:null,//是否显示合同操作按钮 默认为null则显示，查看工作流时为"WF"/"MWF"不显示
	status:null,
	monthEndPanel:null,//客户是否能签订月结的提示框
	
	initComponent : function() {
		var me = this;
		me.custInfo = Ext.create('ContractForm',{
			'contractData':me.contractData,
			'parent':me,
			'custInfoRecord':me.getCustInfoRecord()});
		
		me.monthEndPanel = Ext.create('monthEndPanel',{
			'parent':me,
			'status':me.status,
			'id':'monthEndPanel_id'});
		
		me.contractInfo = Ext.create('ContractTabPanel',{
			'status':me.status,
			'contractData':me.contractData,
			'store':me.store,
			'contractInfoRecord':me.getContractInfoRecord(),
			'preferentialRecord':me.getPreferentialData(),
			'exPreferentialRecord':me.getExPreferentialData(),
			'contractView':me.contractView,
			'deptData':me.getDeptData(),
			'workFlowData':me.getWorkFlowData()});
		me.applyReasonInfo = Ext.create('ApplyReasonPanel',{
			'status':me.status,
			'contractData':me.contractData,
			'application':me.getApplication()});
		me.fileInfo = Ext.create('ContractAttachment',{
			'status':me.status,
			'operateType':'DELAY',
			'contractData':me.contractData,
			'attachData':me.getAttachData()});
		me.uploadAttachment = Ext.create('UploadAttachmentForm',{
			'status':me.status,
			'fileInfo':me.fileInfo,
			'contractData':me.contractData
		});
		me.items = me.getItems();
		me.fbar = me.getFbar();
		this.callParent();
		loadContractViewDataAfterRender(me.contractView,me);//加载数据
		//合同主信息 form
		var formInfo = me.contractInfo.contractTabBasicInfo.contractBasicInfo;
		var contractInfoForm = formInfo.getForm();
		if('UPDATE' == me.status){//解除锁定
			//如果发货金额为空，则填充为0\0\0
			var arrAmount = me.contractView.contract.arrAmount;
			var exArrAmount = me.contractView.contract.exArrAmount;
			formInfo.getForm().findField('arrAmount').setValue((Ext.isEmpty(arrAmount)||0==arrAmount)?'0\\0\\0':arrAmount);
			formInfo.getForm().findField('exArrAmount').setValue((Ext.isEmpty(exArrAmount)||0==exArrAmount)?'0\\0\\0':exArrAmount);
			me.unLockAllComponents();
			
			Ext.getCmp('commonDebtDays_id').setReadOnly(true);//合同月结天数
			Ext.getCmp('custRecord_id').monthDay = me.contractView.contract.debtDays;
			contractInfoForm.findField('beforeContractNum').setReadOnly(true);//原合同编码
			contractInfoForm.findField('contractNum').setReadOnly(true);//合同编码
			contractInfoForm.findField('arrAmount').setReadOnly(true);//近三个月发货金额
			me.custInfo.getForm().findField('custName').setReadOnly(false);//客户名称 
			contractInfoForm.findField('payWay').setReadOnly(true);
			contractInfoForm.findField('preferentialType').setReadOnly(true);
			contractInfoForm.findField('chargeRebate').setReadOnly(true);
			contractInfoForm.findField('agentgathRate').setReadOnly(true);
			contractInfoForm.findField('receivePriceRate').setReadOnly(true);
			contractInfoForm.findField('insuredPriceRate').setReadOnly(true);
			contractInfoForm.findField('exArrAmount').setReadOnly(true);
			contractInfoForm.findField('exPayWay').setReadOnly(true);
			contractInfoForm.findField('exPreferentialType').setReadOnly(true);
			contractInfoForm.findField('exChargeRebate').setReadOnly(true);
			contractInfoForm.findField('exAgentgathRate').setReadOnly(true);
			contractInfoForm.findField('exInsuredPriceRate').setReadOnly(true);
			contractInfoForm.findField('exPriceVersionDate').setReadOnly(true);
			//加载 合同 协议联系人 名称
			var linkMan = contractInfoForm.findField('linkManName');
			if(!Ext.isEmpty(me.member)){
				linkMan.store.loadRecords(me.contact2Agreement(me.member.contactList));
				if(!Ext.isEmpty(me.contractView.contract.contactId)){
					linkMan.setValue(me.contractView.contract.contactId);
				}
			}else{
				if(!Ext.isEmpty(me.contractView.contract.contactId)){
					linkMan.store.add(0,Ext.create('SearchMemberResultModel',{'contactId':me.contractView.contract.contactId,'contactName':me.contractView.contract.linkManName}));
					linkMan.setValue(me.contractView.contract.contactId);
				}
			}
		}else if('VIEW' == me.status){
			me.lockAllComponents();//锁定所有组件
			//加载 合同 客户 名称
			var custName = me.custInfo.getForm().findField('custName');
			custName.setValue(me.contractView.contract.custId);
			custName.setRawValue(me.contractView.custName);
			//加载 合同 协议联系人 名称
			var linkMan = contractInfoForm.findField('linkManName');
			if(!Ext.isEmpty(me.contractView.contract.contactId)){
				linkMan.store.add(0,Ext.create('SearchMemberResultModel',{'contactId':me.contractView.contract.contactId,'contactName':me.contractView.contract.linkManName}));
				linkMan.setValue(me.contractView.contract.contactId);
			}
			//查看详情时取消对输入文本格式的校验及校验提示语
			contractInfoForm.getFields().each(function(field){
				field.clearInvalid();
			});
		}
		if('NEW' == me.status){
//			contractInfoForm.findField('iddisCount').setValue();//是否优惠
//			contractInfoForm.findField('contractStatus').setValue();//合同状态
			contractInfoForm.reset();
			Ext.getCmp('commonDebtDays_id').setReadOnly(true);//合同月结天数
			contractInfoForm.findField('beforeContractNum').setReadOnly(true);//原合同编码
			contractInfoForm.findField('contractNum').setReadOnly(true);//原合同编码
			contractInfoForm.findField('linkManName').store.removeAll();//清空协议联系人下拉框数据
//			contractInfoForm.findField('preferentialType').setReadOnly(false);//优惠类型
			//合同 主信息 部门
//			var deptName = contractInfoForm.findField('deptName');
//			deptName.setValue(top.User.deptName);
//			//合同 主信息 隐含域 部门id
//			var dept = contractInfoForm.findField('deptId');
//			dept.setValue(top.User.deptId);
		}
		if('NEW' == me.status || 'UPDATE' == me.status){
			Ext.getCmp('ifForeignGoods_inForId').setRawValue('否');
			Ext.getCmp('dunningDeptCode_inForId').setValue(null);
			Ext.getCmp('ifForeignGoods_inForId').setReadOnly(true);
			Ext.getCmp('dunningDeptCode_inForId').setReadOnly(true);
			Ext.getCmp('priceVersionDate_id').setReadOnly(true);
		}
		if('VIEW' == me.status){
			var dunningDeptCode = me.contractView.contract.dunningDeptCode;
			var dunningDeptName = me.contractView.contract.dunningDeptName;
			Ext.getCmp('dunningDeptCode_inForId').getStore().add([{'deptCode':dunningDeptCode,'deptName':dunningDeptName}]);
			Ext.getCmp('dunningDeptCode_inForId').setValue(dunningDeptCode);
			Ext.getCmp('ifForeignGoods_inForId').setReadOnly(true);
			Ext.getCmp('dunningDeptCode_inForId').setReadOnly(true);
		}
		
		if (me.showFbar != null && 'VIEW' == me.status){
			//隐藏合同操作按钮
			Ext.getCmp('contractSaveButton').hide();
			Ext.getCmp('contractCancelButton_id').hide();
		}
		//零担优惠信息 为0或1时展示成 空
		dealContractPreferential4Show(me.getPreferentialData(),formInfo);
		//快递优惠信息为0或者1展示成空
		dealContractExPreferential4Show(me.getExPreferentialData(),formInfo);
		//设置合同子公司默认值
		if(me.status == 'NEW'){
			Ext.getCmp('contractSubject_id').setRawValue(Ext.getCmp('contractSubject_id').store.data.items[0].data.contractSubject);
		}
		//添加是否能签订月结显示框
		if(me.status == 'UPDATE') {
			var arrAmount = me.contractInfo.contractInfoRecord.data.arrAmount;
			var exArrAmount = me.contractInfo.contractInfoRecord.data.exArrAmount;
			//如果零担发货金额连续两月达到3000以上且快递发货连续两月达到600以上，或者是未发货
			if (checkCustCanSignLdMontEnd(arrAmount)){// && checkCustCanSignKdMontEnd(exArrAmount)) 
				Ext.getCmp('showNameDisplay').setValue('<span></span>');
				Ext.getCmp('contractSaveButton').setDisabled(false);
			//如果零担发货金额没有连续两月达到3000以上且快递发货没有连续两月达到600以上
//			} else if(!checkCustCanSignLdMontEnd(arrAmount) && !checkCustCanSignKdMontEnd(exArrAmount)){
//				Ext.getCmp('showNameDisplay').setValue('<span style="color:#ff0000;font-size:10px">友情提醒：该客户不满足月结客户条件，不能签订月结合同!</span>');
//				if((me.contractInfo.contractInfoRecord.data.payWay == 'MONTH_END'
//					&&contractInfoForm.findField('ifLingDan').getValue())
//					||(me.contractInfo.contractInfoRecord.data.exPayWay == 'MONTH_END'
//						&&contractInfoForm.findField('ifExpress').getValue())){
//					Ext.getCmp('contractSaveButton').setDisabled(true);
//				}
//			//达到零担月结，未达到快递月结
//			}else if(checkCustCanSignLdMontEnd(arrAmount) && !checkCustCanSignKdMontEnd(exArrAmount)){
//				Ext.getCmp('showNameDisplay').setValue('<span style="color:#ff0000;font-size:10px">友情提醒：该客户不满足快递月结客户条件，不能签快递订月结合同!</span>');
//				if(me.contractInfo.contractInfoRecord.data.exPayWay == 'MONTH_END'
//					&& contractInfoForm.findField('ifExpress').getValue()){
//					Ext.getCmp('contractSaveButton').setDisabled(true);
//				}
			//达到快递月结，未达到零担月结
			}else{
				Ext.getCmp('showNameDisplay').setValue('<span style="color:#ff0000;font-size:10px">友情提醒：该客户不满足零担月结客户条件，不能签零担订月结合同!</span>');
				if(me.contractInfo.contractInfoRecord.data.payWay == 'MONTH_END'
					&&contractInfoForm.findField('ifLingDan').getValue()){
					Ext.getCmp('contractSaveButton').setDisabled(true);
				}
			}
		}
		if(me.status == 'UPDATE'){
			completePriceVersionDate();
		}
	},
	//联系人转换成协议联系人
	contact2Agreement:function(list){
		var contactList = [];
		for(var i in list){
			contactList.push(Ext.create('SearchMemberResultModel',{
				'contactId':list[i].id,
				'contactName':list[i].name,
				'mobileNum':list[i].mobilePhone,
				'telNum':list[i].telPhone
			}));
		}
		return contactList;
	},
	getItems : function(){
		var me = this;
		return [ 
		  {
		    xtype :'basicformpanel',
		    height : 105,
		    items : [ me.custInfo]
		  }
		  ,{
		    xtype :'basicformpanel',
		 	height : 18,
		 	width:600,
		 	padding:'0 10 0 0',
		 	items : [ me.monthEndPanel]
		 }
		  /*,{
			xtype : 'basicpanel',
			height : 120,
			items : [ me.custInfo ]
		}*/, {
			xtype : 'basicpanel',
			height : 435,
			width:800,
			items : [ me.contractInfo]
		},{
			xtype:'basicpanel',
			height : 102,
			layout:{
				type:'hbox',
				align:'stretch'
			},
			items:[{
				xtype : 'basicpanel',
				flex:1,
				items : [me.applyReasonInfo]
			},
			{
				xtype : 'basicpanel',
				width:5
			}
			,{
				xtype : 'basicpanel',
				flex : 1,
				items : [ me.fileInfo ]
			}
			]
		},me.uploadAttachment
		];
	},
	getFbar:function(){
		var me = this;
		return [ {
			xtype : 'button',
			id:'contractSaveButton',
			text : i18n('i18n.MemberCustEditView.submit'),//提交按钮
			scope:me,
			handler:me.saveContract
		}, {
			xtype : 'button',
			id:'contractCancelButton_id',
			text : i18n('i18n.ManualRewardIntegralEditView.b_cance'),
			handler:function(){
				if(me.parentWin != null){
					me.parentWin.close();
				}
			}
		}];
	},
	//校验合同信息 不符合则返回false 符合提交条件则返回true
	validateContractInfo:function(contract,exPreferential,preferential){
		var me = this;
		var success = true;
		var message = '';
		var form = me.contractInfo.down('form').getForm();
		//校验客户信息、合同信息、申请事由
		if(!me.custInfo.getForm().isValid() || 
		   !me.contractInfo.down('form').getForm().isValid() || 
		   !me.applyReasonInfo.getForm().isValid()){
		   	message = i18n('i18n.ContractEditView.m_checkAllRight');
		   	success = false
		   	return {success:success,message:message};
		}
		if('NEW' == me.status){
			if(!form.findField('ifLingDan').getValue()&&!form.findField('ifExpress').getValue()){
				message = '未选择零担合同还是快递合同，不允许提交';
			   	success = false;
			   	return {success:success,message:message};
			}
		}else if('UPDATE' == me.status){
			//校验零担合同折扣信息是否已经修改
			if(preIsNotModified(form,contract,preferential)){
				message = '未修改零担合同信息请勿勾选零担合同';
			   	success = false;
			   	return {success:success,message:message};
			}
			//校验快递合同折扣信息是否已经修改
			else if(exPreIsNotModified(form,contract,exPreferential)){
				message = '未修改快递合同信息请勿勾选快递合同';
			   	success = false;
			   	return {success:success,message:message};
			}
		}
		var payWay = form.findField('payWay').getValue();
		var preferentialType = form.findField('preferentialType').getValue();
		var exPayWay = form.findField('exPayWay').getValue();
		var exPreferentialType = form.findField('exPreferentialType').getValue();
		//如果 结款方式和是否优惠同时分别为 "非月结"和"否 则不可保存合同
		if(('NOT_MONTH_END' == payWay && 'NOT_PREFERENTIAL' == preferentialType
				&& exPayWay== 'NOT_MONTH_END'&& exPreferentialType=='NOT_PREFERENTIAL')
					||(Ext.isEmpty(payWay)&&Ext.isEmpty(preferentialType)
							&&exPayWay== 'NOT_MONTH_END'&& exPreferentialType=='NOT_PREFERENTIAL')
					||(Ext.isEmpty(exPayWay)&&Ext.isEmpty(exPreferentialType)
							&&'NOT_MONTH_END' == payWay && 'NOT_PREFERENTIAL' == preferentialType)
					){
		   	message = i18n('i18n.ContractEditView.m_payWay_preference');
		   	success = false
		   	return {success:success,message:message};
		}
		//如果 结款方式为"月结" 结款日期 不能为空
		var resultDateField = form.findField('resultDate');
		if(('MONTH_END' == payWay || 'MONTH_END'== form.findField('exPayWay').getValue()) 
				&& (Ext.isEmpty(resultDateField.getRawValue())||Ext.isEmpty(resultDateField.getRawValue().trim()))){
		   	message = i18n('i18n.ContractEditView.m_resultDatecannotnull');
//		   	message = '结款方式为"月结"时，结款日期不能为空！';
		   	success = false
		   	return {success:success,message:message};
		}
		//合同开始时间 不能和结束时将相同
		if(Ext.getCmp('contractStartTime_id').getValue().getTime() == Ext.getCmp('contractEndTime_id').getValue().getTime()){
			MessageUtil.showMessage(i18n('i18n.ContractEditView.contractStartDateSmallEndDate'));
		   	message = i18n('i18n.ContractEditView.contractStartDateSmallEndDate');
		   	success = false
		   	return {success:success,message:message};
		}
		
		// 合同截止日期必须为选择月的最后一天
		var eTime = Ext.getCmp('contractEndTime_id').getValue();//合同结束时间
		var mSend = Ext.getCmp('preferentialType_id').getValue();//零担合同优惠类型
		var exPreType = Ext.getCmp('preferentialType_id_Express').getValue();//快递合同优惠类型
		if (!Ext.isEmpty(eTime) && eTime.toLocaleDateString() != 
			ContractEditEditView.getMontLastDay(new Date(eTime)).toLocaleDateString()){
				MessageUtil.showMessage(i18n('i18n.ContractEditView.m_endTimeMustLast'));
				message = i18n('i18n.ContractEditView.m_endTimeMustLast');
				success = false;
			   	return {success:success,message:message};
		}
		// 当修优惠类型是"价格折扣"时，优惠折扣详情必须填写
		if('PRICE_REBATE' == mSend && isPreferentialAllNull(null,form)){
			success = false;
			message = '优惠类型是"价格折扣"时，优惠信息折扣明细不能全部为空！';
			return {success:success,message:message};
		}
		if('PRICE_REBATE' == exPreType && isExPreferentialNull(form)){
			success = false;
			message = '优惠类型是"运费折扣"时，运费折扣信息不能为空！';
			return {success:success,message:message};
		}
		//校验合同附件
		var fillUpload = false;
		//发票标记
		var invoiceType = form.findField('invoiceType').getValue();
		me.contractData.getFileInfoStore().each(function(record){
			if ('INVOICE_TYPE_01'==invoiceType
					&&record.get('fileBusinessType') == 
						DpUtil.changeDictionaryCodeToDescrip('CONTRACTTAX',DataDictionary.CONTRACT_ANNEX)
						&&Ext.isEmpty(record.get('fileName'))) {
				message = record.get('fileBusinessType')+i18n('i18n.ContractEditView.m_fillecannotnull');
			   	success = false;
			}
			if(
				(record.get('fileBusinessType') == DpUtil.changeDictionaryCodeToDescrip('ELECTRONICCONTRACT',DataDictionary.CONTRACT_ANNEX) 
				||record.get('fileBusinessType') == DpUtil.changeDictionaryCodeToDescrip('BUSINESSLICENSE',DataDictionary.CONTRACT_ANNEX)
				)&&Ext.isEmpty(record.get('fileName'))
			){
			   	message = record.get('fileBusinessType')+i18n('i18n.ContractEditView.m_fillecannotnull');
			   	success = false;
			}
		});
		return {success:success,message:message};
	},
	//保存合同信息
	saveContract:function(button){
		var me = this;
		var saveParams = {};
		button.setDisabled(true);
		var contract = me.getContractInfoRecord();
		var exPreferential = me.getExPreferentialData();
		var preferential = me.getPreferentialData();
		var validateRs = me.validateContractInfo(contract,exPreferential,preferential);
		// 提示 结算限额已超过近三月发货金额最高月的两倍，请确定是否提交！
		var form = me.contractInfo.down('form').getForm();
		var amount = form.findField('arrearaMount').getValue();
		var arrAmount = form.findField('arrAmount').getValue();
		var exArrAmount = form.findField('exArrAmount').getValue();
		var payWay = form.findField('payWay').getValue();
		var exPayWay = form.findField('exPayWay').getValue();
		var exPreferentialType = form.findField('exPreferentialType').getValue();
		var preferentialType = form.findField('preferentialType').getValue();
		var afterInvoiceType = form.findField('invoiceType').getValue();
		var exAgentgathRate = form.findField('exAgentgathRate').getValue();
		var exInsuredPriceRate = form.findField('exInsuredPriceRate').getValue();
		//校验合同信息 不符合则返回false 符合提交条件则返回true
		if(!validateRs.success){
			button.setDisabled(false);
			MessageUtil.showMessage(validateRs.message);
			return;
		}
		/*
		 * 若只是零担合同是月结，则只校验零担的近三个月发货金额；
		 * 若只是快递合同是月结，则只校验快递的近三个月发货金额；
		 * 若零担是快递合同都是月结，则将两个业务的近三个月的对应月的发货金额相加之后再进行校验
		 */
		if(dealContractArrMount(contract,exPayWay,payWay,amount,arrAmount,exArrAmount)){
			//结算限额已超过近三月发货金额最高月的两倍提示语
			MessageUtil.showQuestionMes(i18n('i18n.contractEditView.isNotOverMax3AmountNotice'), function(e) {
				if(e == 'yes'){
					me.saveContractFn(saveParams,me,button);
				}else{
					button.setDisabled(false);
				}
			},function(){
				button.setDisabled(false);
			});
		}else if((('MONTH_SEND' == preferentialType&&'NOT_MONTH_END' == payWay
				&&'MONTH_REBATE' == exPreferentialType&&'NOT_MONTH_END' == exPayWay)
				||(Ext.isEmpty(preferentialType)&&Ext.isEmpty(payWay)
						&&'MONTH_REBATE' == exPreferentialType&&'NOT_MONTH_END' == exPayWay)
				||(Ext.isEmpty(exPreferentialType)&&Ext.isEmpty(exPayWay)
						&&'MONTH_SEND' == preferentialType&&'NOT_MONTH_END' == payWay)
			    ||('MONTH_SEND' == preferentialType&&'NOT_MONTH_END' == payWay
						&&'NOT_PREFERENTIAL' == exPreferentialType&&'NOT_MONTH_END' == exPayWay)
				||('NOT_PREFERENTIAL' == preferentialType&&'NOT_MONTH_END' == payWay
						&&'MONTH_REBATE' == exPreferentialType&&'NOT_MONTH_END' == exPayWay))
						&&Ext.isEmpty(exAgentgathRate)
						&&Ext.isEmpty(exInsuredPriceRate)
		){
			var showMessage = i18n('i18n.contractEditView.workflowNotice2');
			if(me.status =='NEW'&&afterInvoiceType == 'INVOICE_TYPE_02')
			{
				 showMessage = i18n('i18n.contractEditView.workflowNotice');
			}
			
			if(me.status != 'NEW'&& contract.data.invoiceType ==afterInvoiceType){//改签
				showMessage = i18n('i18n.contractEditView.workflowNotice');
			}
			//纯月发月送合同，表单提交提示语
			MessageUtil.showQuestionMes(showMessage,function(e){
				if(e == 'yes'){
					me.saveContractFn(saveParams,me,button);					
				}else{
					button.setDisabled(false);
				}
			},function(){
				button.setDisabled(false);
			});
		}else{
			MessageUtil.showQuestionMes(i18n('i18n.contractEditView.workflowNotice2'),function(e){
				if(e == 'yes'){
					me.saveContractFn(saveParams,me,button);					
				}else{
					button.setDisabled(false);
				}
			},function(){
				button.setDisabled(false);
			});
		}
	},
	saveContractFn:function(saveParams,me,button){
		if('NEW' == me.status){
			var conInfoForm = me.contractInfo.contractTabBasicInfo.contractBasicInfo;
			saveParams = me.assembleNewContractData();
			var contract = saveParams.contract;
			var ifLingDan = conInfoForm.getForm().findField('ifLingDan').getValue();
			var ifExpress = conInfoForm.getForm().findField('ifExpress').getValue();
			
			//快递和零担一起签
			if(ifLingDan&&ifExpress){
				contract.contractType = 'EL';
			//只签订快递
			}else if(!ifLingDan&&ifExpress){
				contract.contractType = 'EXPRESS';
			//签订零担
			}else if(ifLingDan&&!ifExpress){
				contract.contractType = 'LTT';
			}
					
			contract.useableArrearAmount = Ext.getCmp('c_applyAmonut_id').getValue();
			if(Ext.isEmpty(contract.useableArrearAmount)){
				contract.useableArrearAmount = 0;
				saveParams.contract = contract;
			}else{				
				saveParams.contract = contract;
			}
			var saveSuccess = function(result){
				if(Ext.isEmpty(result.workFlowNum)){
					MessageUtil.showInfoMes(i18n('i18n.PotentialCustEditView.saveSuccess'));
				}else{
					MessageUtil.showInfoMes(i18n('i18n.ContractEditView.contractSaveSuccessWorkFlow')+result.workFlowNum);
				}
				contractManagerDataControl.getContractStore().loadPage(1);
				me.parentWin.close();
				button.setDisabled(false);
			}
			var saveFail = function(result){
				button.setDisabled(false);
				MessageUtil.showErrorMes(result.message);
				//me.parentWin.close();
			}
			me.contractData.saveContract(saveParams,saveSuccess,saveFail);
		}else if('UPDATE' == me.status){
			//防止重复点击保存按钮
			button.setDisabled(true);
			var conInfoForm = me.contractInfo.contractTabBasicInfo.contractBasicInfo;
			saveParams = me.assembleNewContractData();
			//可使用额度和临欠额度保持一样
			var contract = saveParams.contract;
			var ifLingDan = conInfoForm.getForm().findField('ifLingDan').getValue();
			var ifExpress = conInfoForm.getForm().findField('ifExpress').getValue();
			//快递和零担一起签
			if(ifLingDan&&ifExpress){
				contract.contractType = 'EL';
			//只签订快递
			}else if(!ifLingDan&&ifExpress){
				contract.contractType = 'EXPRESS';
			//签订零担
			}else if(ifLingDan&&!ifExpress){
				contract.contractType = 'LTT';
			}
			
			contract.useableArrearAmount = Ext.getCmp('c_applyAmonut_id').getValue();
			if(Ext.isEmpty(contract.useableArrearAmount)){
				contract.useableArrearAmount = 0;
				saveParams.contract = contract;
			}else{				
				saveParams.contract = contract;
			}
//			saveParams.contract.useableArrearAmount = Ext.getCmp('c_applyAmonut_id').getValue();
			if(Ext.isEmpty(saveParams.fileInfoList)){
				MessageUtil.showMessage(i18n('i18n.ChangeContactAffiliatedRelationView.m_uploadFile'));
				button.setDisabled(false);
				return;
			}
//			saveParams = me.assembleAlterContractData();
//			if(saveParams.addPreferential == null && saveParams.approveDataList.length == 0){
//				MessageUtil.showMessage(i18n('i18n.ChangeContactAffiliatedRelationView.m_uploadFile'));	return;			
//			}
			var saveSuccess = function(result){
				var updFlag = result.updFlag;
				var message = i18n('i18n.ContractEditView.updateSuccessWar');
				if('true'==updFlag){//发票标记改变了 产生工作流
					message = i18n('i18n.ContractEditView.contractUpdateSuccessWorkFlow')+result.workFlowNum+i18n('i18n.ContractManagerView.contractTaxChangeEffectMsg');
				}
				else if(!Ext.isEmpty(result.workFlowNum)){
					message = i18n('i18n.ContractEditView.contractSaveSuccessWorkFlow')+result.workFlowNum;
				}
				MessageUtil.showInfoMes(message);
				contractManagerDataControl.getContractStore().loadPage(1);
				me.parentWin.close();
				button.setDisabled(false);
			}
			var saveFail = function(result){
				MessageUtil.showErrorMes(result.message);
				button.setDisabled(false);
				//me.parentWin.close();
			}
			me.contractData.updateContract(saveParams,saveSuccess,saveFail);
		}else if('MODIFY' == me.status){
			//防止重复点击保存按钮
			//TODO
			button.setDisabled(true);
			var contractId4MonthEnd = me.contractView.contract.id;
			
			saveParams = {};
			saveParams.debtDays = Ext.getCmp('modifyDebtDays_id').getValue();
			saveParams.contractId = contractId4MonthEnd ;
			var saveSuccess = function(result){
				MessageUtil.showInfoMes('修改合同月结天数成功！');
				contractManagerDataControl.getContractStore().loadPage(1);
				me.parentWin.close();
				button.setDisabled(false);
			}
			var saveFail = function(result){
				MessageUtil.showErrorMes(result.message);
				button.setDisabled(false);
			}
			me.contractData.updateSpecialContractMonthEnd(saveParams,saveSuccess,saveFail);
		}
	},
	//锁定界面所有控件
	lockAllComponents:function(){
		var me = this;
		//锁定客户基本信息
		var memberBasicBasicForm = me.custInfo.getForm();
		memberBasicBasicForm.findField('custName').setReadOnly(true);
		//锁定合同主信息
		var contractInfo = me.contractInfo.contractTabBasicInfo.contractBasicInfo.getForm();
		contractInfo.getFields().each(function(field){
			field.setReadOnly(true);
		});
		//申请事由锁定
		me.applyReasonInfo.getForm().findField('application').setReadOnly(true);
		//保存按钮锁定
		Ext.getCmp('contractSaveButton').setDisabled(true);
		//上传附件按钮锁定
		Ext.getCmp('fileUpLoadButton').setDisabled(true);
	},
	//解除锁定
	unLockAllComponents:function(){
		var me = this;
		//解除锁定客户基本信息
		var memberBasicBasicForm = me.custInfo.getForm();
		memberBasicBasicForm.findField('custName').setReadOnly(true);
		//解除锁定合同主信息
		var contractInfo = me.contractInfo.contractTabBasicInfo.contractBasicInfo.getForm();
		contractInfo.getFields().each(function(field){
			field.setReadOnly(false);
		});
		contractInfo.findField('deptName').setReadOnly(true);//锁定合同 主信息 部门
		contractInfo.findField('contractStatus').setReadOnly(true);//锁定合同状态
		contractInfo.findField('linkManMobile').setReadOnly(true);//锁定合同 主信息 协议联系人手机
		contractInfo.findField('linkManPhone').setReadOnly(true);//锁定合同 主信息 协议联系人电话
		//结款方式”选择为“非月结”时，“申请欠款额度”/ 对账日期/开发票日期/结款日期 不可编辑
		if((contractInfo.findField('payWay').getValue() == 'NOT_MONTH_END'
			&& contractInfo.findField('exPayWay').getValue() == 'NOT_MONTH_END')
			||(Ext.isEmpty(contractInfo.findField('payWay').getValue())
					&&contractInfo.findField('exPayWay').getValue() == 'NOT_MONTH_END')
			||(Ext.isEmpty(contractInfo.findField('exPayWay').getValue())
					&&contractInfo.findField('payWay').getValue() == 'NOT_MONTH_END')
			||(Ext.isEmpty(contractInfo.findField('exPayWay').getValue())
					&&Ext.isEmpty(contractInfo.findField('payWay').getValue()))
			){
			contractInfo.findField('arrearaMount').setValue();//欠款额度
			contractInfo.findField('reconDate').setValue(0);//对账日期
			contractInfo.findField('invoicDate').setValue(0);//开发票日期
			contractInfo.findField('resultDate').setValue(0);//结款日期
			contractInfo.findField('arrearaMount').setReadOnly(true);//欠款额度
			contractInfo.findField('resultDate').setReadOnly(true);//结款日期
			contractInfo.findField('arrearaMount').allowBlank=true;//欠款额度
			contractInfo.findField('resultDate').allowBlank=true;//结款日期
			me.doLayout();
		}else{
			contractInfo.findField('arrearaMount').setReadOnly(false);
			contractInfo.findField('resultDate').setReadOnly(false);//结款日期
			contractInfo.findField('arrearaMount').allowBlank=false;//欠款额度
			contractInfo.findField('resultDate').allowBlank=false;//结款日期
			me.doLayout();
		}
		
		//申请事由解锁
		me.applyReasonInfo.getForm().findField('application').setReadOnly(false);
		//保存按钮解锁
		Ext.getCmp('contractSaveButton').setDisabled(false);
		//上传附件按钮解锁
		Ext.getCmp('fileUpLoadButton').setDisabled(false);
	},
	//合同界面展示之后加载 会员数据
	loadContractViewDataAfterRender:function(contractView){
		loadContractViewDataAfterRender(this.contractView,this);
	},
	//获得会员基本信息的record
	getCustInfoRecord:function(){
		return getCustInfoRecord(this.contractView);
	},
	// 优惠信息/折扣信息data
	getPreferentialData:function(){
		return getPreferentialData(this.contractView);
	},
	// 优惠信息/折扣信息data
	getExPreferentialData:function(){
		return getExPreferentialData(this.contractView);
	},
	// load合同基本信息的record
	getContractInfoRecord:function(){
		return getContractInfoRecord(this.contractView);
	},
	// 获得申请事由
	getApplication:function(){
		return getApplication(this.contractView);
	},
	// 获得合同附件Data
	getAttachData:function(){
		return getAttachData(this.contractView,this.status);
	},
	// 获得已绑定部门Data
	getDeptData:function(){
		return getDeptData(this.contractView);
	},
	// 工作流Data
	getWorkFlowData:function(){
		return getWorkFlowData(this.contractView);
	},
	//组装合同数据
	assembleNewContractData:function(){
		var me = this;
		var saveParams = {};
		//客户信息 id
		var custId = me.custInfo.getForm().findField('custId').getValue();
		//客户信息  地址
		var custAddress = me.custInfo.getForm().findField('address').getValue();
		//合同主信息
		var contractInfoForm = me.contractInfo.down('form');
		var contract = contractInfoForm.contractInfoRecord;
		contract.set('id',contractInfoForm.getForm().findField('contract4CommitId').getValue());
		contractInfoForm.getForm().updateRecord(contract);
		var linkManName = contractInfoForm.getForm().findField('linkManName').getRawValue();
		//优惠信息
		var preferentialInfo = me.getPreferentialData();
		contractInfoForm.getForm().updateRecord(preferentialInfo);
		
		contract.data.exPreferential = {};
		contract.data.exArrAmount = contractInfoForm.getForm().findField('exArrAmount').getValue();
		contract.data.exPayWay = contractInfoForm.getForm().findField('exPayWay').getValue();
		contract.data.exPreferentialType = contractInfoForm.getForm().findField('exPreferentialType').getValue();
		contract.data.exPreferential.chargeRebate = chnangNull2One(contractInfoForm.getForm().findField('exChargeRebate').getValue());
		contract.data.exPreferential.agentgathRate = chnangNull2One(contractInfoForm.getForm().findField('exAgentgathRate').getValue());
		contract.data.exPreferential.insuredPriceRate = chnangNull2One(contractInfoForm.getForm().findField('exInsuredPriceRate').getValue());
//		contract.data.exPreferential.receivePriceRate = 1;
		contract.data.exPreferential.deliveryPriceRate = 1;
		
		
		//处理优惠信息 用于提交 折扣为空则传到后台为1（后台接收类型为Double）
		dealContractPreferential4Commit(preferentialInfo);
		//申请事由
		var application = me.applyReasonInfo.getForm().findField('application').getValue();
		
		//附件
		var alterAddAttachList = new Array();//附件 alterAddLinkmanList
		//搜集合同附件修改信息
		me.fileInfo.collectAlterAttachData(alterAddAttachList);
		saveParams.fileInfoList = alterAddAttachList;//新增的附件
		if(!Ext.isEmpty(custId)){
			contract.set('custId',custId);//客户id
		}
		contract.set('linkManAddress',custAddress);
		contract.set('preferential',preferentialInfo.data);//优惠信息
		contract.set('application',application);//申请事由
		contract.set('linkManName',linkManName);
		contract.set('iddisCount',true);//是否折扣，永远为true（由于需求变更界面取消该值的选择）
		saveParams.contract= contract.data;
		
		return saveParams;
	},
	//组装修改合同的信息
	assembleAlterContractData:function(){
		var me = this;
		var params = {};
		var approveDataList = new Array();//存放所有修改字段信息
		var alterContractList = new Array();//客户主信息修改内容
		var alterPreferential = new Array();//优惠修改
		var alterDeletePreferentialList = new Array();//优惠修改 删除
		var alterAddPreferentialList = new Array();//优惠修改 新增
		var alterDeleteList = new Array();//申请事由
		var alterAddAttachList = new Array();//附件 alterAddLinkmanList
		
		
		//搜集 合同 基本信息修改的内容
		me.contractInfo.contractTabBasicInfo.contractBasicInfo.collectAlterContractData(alterContractList);
		//搜集 优惠 修改信息
		me.contractInfo.contractTabBasicInfo.preferentialInfo.collectAlterPreferentialData(alterPreferential,alterDeletePreferentialList,alterAddPreferentialList);
		//搜集合同附件修改信息
		me.fileInfo.collectAlterAttachData(alterAddAttachList);
		
		if(me.contractView.custId != me.custInfo.getForm().findField('custId').getValue()){
			var custIdApproveData = Ext.create('ApproveDataModel');
			custIdApproveData.set('className','Contract');
			custIdApproveData.set('classId',me.contractView.contract.id);
			custIdApproveData.set('fieldName','custId');
			custIdApproveData.set('newValue',me.custInfo.getForm().findField('custId').getValue());
			//优惠信息修改字段
			alterContractList.push(custIdApproveData.data);
		}
		if(me.contractView.contract.application != me.applyReasonInfo.getForm().findField('application').getValue()){
			var applicationApproveData = Ext.create('ApproveDataModel');
			applicationApproveData.set('className','Contract');
			applicationApproveData.set('classId',me.contractView.contract.id);
			applicationApproveData.set('fieldName','application');
			applicationApproveData.set('newValue',me.applyReasonInfo.getForm().findField('application').getValue());
			//优惠信息修改字段
			alterContractList.push(applicationApproveData.data);
		}
		
		//设置合同ID 提交到后台
		params.contractId = me.contractInfo.contractInfoRecord.get('id');
		//优惠信息修改字段
		approveDataList.push(alterPreferential);
		
		//提交到后台
		params.approveDataList = alterContractList;//修改的合同信息
		//新增的优惠信息
		if(alterAddPreferentialList.length>0){
			params.addPreferential = alterAddPreferentialList[0];
		}else{
			params.addPreferential = null;
		}
//		params.addPreferential = alterAddPreferentialList;
//		params.alterDeletePreferentialList = alterDeletePreferentialList;//删除的优惠信息
		new Date().getTime();
		// 判断 附件只能新增一个
		if(alterAddAttachList.length>0){
			params.fileInfo = alterAddAttachList[0];//新增的附件
		}else{
			params.fileInfo = null;//新增的附件
		}
		
		return params;
	}
});

/**
 * 客户能否签订月结的窗口提示框
 */
Ext.define('monthEndPanel',{
	extend:'BasicPanel',
	height:16,
	margin:'0 0 0 0',
	//labelAlign:'left',
	items:[{
        xtype: 'displayfield',
        id:'showNameDisplay',
        value:'<span style="color:#ff0000"></span>',
        width:500,
        fieldLabel: '',
        hideLabel:true
    }]
});
/**
 * 客户基本信息
 */
Ext.define('ContractForm', {
	extend : 'PopTitleFormPanel',
	items : null,
	record : null,
	monthDay:null,
	id:'custRecord_id',
	parent:null,
	contractData:null, //Data层接口
	custInfoRecord:null,//合同信息数据
	initComponent : function() {
		//初始化部门信息
    	initDeptAndUserInfo("1");
		this.items = this.getItems();
		this.callParent();
	},
	setDeptName:function(memberRecord){
		var me = this;
		var deptName = memberRecord.get('deptName');
		var deptId = memberRecord.get('deptId');
		me.parent.contractInfo.contractTabBasicInfo.contractBasicInfo.getForm().findField('deptName').setValue(deptName);
		me.parent.contractInfo.contractTabBasicInfo.contractBasicInfo.getForm().findField('deptId').setValue(deptId);
		
		var param = {'deptId':deptId};
		var successFn=function(json){
			Ext.getCmp('contractSubject_id').getStore().removeAll();
			Ext.getCmp('contractSubject_id').getStore().loadData(json.contractSubjectList);
			if (!Ext.isEmpty(json.contractSubjectList)&&!Ext.isEmpty(json.contractSubjectList[0])) {
				Ext.getCmp('contractSubject_id').setValue(json.contractSubjectList[0].contractSubject);
			}
		}
		var failFn = function(json){
			
		}
		
		contractManagerDataControl.searchContractSubjectList(param,successFn,failFn);
		
	},
	
	getItems : function() {
		var me = this;
		//加载 合同客户信息 数据
		function loadBusinessData(mainLinkmanRecord,memberRecord){
			if(mainLinkmanRecord != null){
				me.getForm().loadRecord(mainLinkmanRecord);
				//处理二级行业字段
				DpUtil.setSecondTradeValue(me.getForm().findField('secondTrade'),mainLinkmanRecord);
			}else{
				//如果没有主联系人，则只带出客户信息,把对应的联系人姓名、手机、固话、身份证号、地址 置空
				memberRecord.set('mobileNum',null);
				memberRecord.set('telNum',null);
				memberRecord.set('idCard',null);
				memberRecord.set('contactName',null);
				memberRecord.set('address',null);
				me.getForm().loadRecord(memberRecord);
				//处理二级行业字段
				DpUtil.setSecondTradeValue(me.getForm().findField('secondTrade'),memberRecord);
			}
		};
		return [ {
			xtype : 'fieldset',
			layout : {
				type : 'table',
				columns : 4
			},
			defaultType : 'dptextfield',
			defaults : {
				labelWidth:70,
				width:180,
				readOnly:true
			},
			title : i18n('i18n.ContractEditView.t_custInfo'),
			items : [ {
				xtype:'membersearchcombox',
				fieldLabel : i18n('i18n.ContractEditView.c_cuntName'),
				name : 'custName',
				searchType:'OWNDEPT',
				readOnly : false,
				allowBlank:false,
				forceSelection:false,
				blankText:i18n('i18n.ContractEditView.m_selectCustName'),
				setValueComeBack:function(memberRecord,resultRecords){
					var custId = memberRecord.get('custId');
					//设置协议人的store
				    var protoContactStore = me.contractData.getProtoContactStore();
				    protoContactStore.removeAll();
				    
					var mainLinkmanRecord = null;
					//只带出对应客户的主联系人信息,同时设置协议联系人的store
					for(var i = 0; i < resultRecords.length; i++){
						if(resultRecords[i].get('custId') == custId){
							if(resultRecords[i].get('isMainLinkMan') == true){
								mainLinkmanRecord = resultRecords[i];
							}
							protoContactStore.insert(0,resultRecords[i]);
						}
					}
					//合同form
					var contractInfo = me.parent.contractInfo.contractTabBasicInfo.contractBasicInfo;
					//重置复选框
					contractInfo.getForm().findField('ifLingDan').setValue(false);
					contractInfo.getForm().findField('payWay').setReadOnly(true);
					contractInfo.getForm().findField('preferentialType').setReadOnly(true);
					contractInfo.getForm().findField('agentgathRate').setReadOnly(true);
					contractInfo.getForm().findField('chargeRebate').setReadOnly(true);
					contractInfo.getForm().findField('receivePriceRate').setReadOnly(true);
					contractInfo.getForm().findField('insuredPriceRate').setReadOnly(true);
					contractInfo.getForm().findField('ifExpress').setValue(false);
					contractInfo.getForm().findField('exPayWay').setReadOnly(true);
					contractInfo.getForm().findField('exPreferentialType').setReadOnly(true);
					contractInfo.getForm().findField('exChargeRebate').setReadOnly(true);
					contractInfo.getForm().findField('exAgentgathRate').setReadOnly(true);
					contractInfo.getForm().findField('exInsuredPriceRate').setReadOnly(true);
					
					// 如果返回结果  已有合同信息   提示是否带出合同信息 
					var acquireSuccess = function(result){
						var contractView = result.contractView;
						/**
						 * @description 增加客户二级行业，以后后边被覆盖
						 * @date 2013-07-23
						 * @author pgj
						 */
						mainLinkmanRecord.set('secondTrade',contractView.secondTrade);
						memberRecord.set('secondTrade',contractView.secondTrade);
						//将传入的contractView源改为当前客户合同
						me.parent.contractInfo.contractTabBasicInfo.contractBasicInfo.contractView = contractView;
						if(!Ext.isEmpty(contractView.contract)&&!Ext.isEmpty(contractView.contract.id)){
								loadContractViewDataAfterRender(contractView,me.parent);
								loadBusinessData(mainLinkmanRecord, memberRecord);
								showDateByBusiness(getContractInfoRecord(contractView),contractInfo,'UPDATE');
								//优惠信息 为0或1时展示成 空
								dealContractPreferential4Show(getPreferentialData(contractView),contractInfo);
								//快递优惠信息为0或者1展示成空
								dealContractExPreferential4Show(getExPreferentialData(contractView),contractInfo);
								var contractBasForm = me.parent.contractInfo.contractTabBasicInfo.contractBasicInfo;
								var linkMan = contractBasForm.getForm().findField('linkManName');
								linkMan.setValue(mainLinkmanRecord.get('contactId'));
								//月结天数
								Ext.getCmp('custRecord_id').monthDay = contractView.contract.debtDays;
								//添加是否能签订月结显示框
								var arrAmount = contractInfo.getForm().findField('arrAmount').getValue();
								var exArrAmount = contractInfo.getForm().findField('exArrAmount').getValue();
								if (checkCustCanSignLdMontEnd(arrAmount)&&checkCustCanSignKdMontEnd(exArrAmount)) {
									Ext.getCmp("showNameDisplay").setValue("<span></span>");
								//零担月结和快递月结都不满足
//								} else if(!checkCustCanSignLdMontEnd(arrAmount)&&!checkCustCanSignKdMontEnd(exArrAmount)){
//									Ext.getCmp("showNameDisplay").setValue("<span style='color:#ff0000;font-size:10px'>友情提醒：该客户不满足月结客户条件，不能签订月结合同!</span>");
//									if((contractInfo.getForm().findField('payWay').getValue() == 'MONTH_END'
//										&&contractInfo.getForm().findField('ifLingDan').getValue())
//										||(contractInfo.getForm().findField('exPayWay').getValue() == 'MONTH_END'
//											&&contractInfo.getForm().findField('ifExpress').getValue())){
//										Ext.getCmp('contractSaveButton').setDisabled(true);
//									}
//									//满足零担月结，不满足快递月结
//								}else if(checkCustCanSignLdMontEnd(arrAmount)&&!checkCustCanSignKdMontEnd(exArrAmount)){
//									Ext.getCmp("showNameDisplay").setValue("<span style='color:#ff0000;font-size:10px'>友情提醒：该客户不满足快递月结客户条件，不能签订快递月结合同!</span>");
//									if(contractInfo.getForm().findField('exPayWay').getValue() == 'MONTH_END'
//										&&contractInfo.getForm().findField('ifExpress').getValue()){
//										Ext.getCmp('contractSaveButton').setDisabled(true);
//									}
									//满足快递月结，不满足零担月结
								}else if(!checkCustCanSignLdMontEnd(arrAmount)){// && checkCustCanSignKdMontEnd(exArrAmount)){
									Ext.getCmp("showNameDisplay").setValue("<span style='color:#ff0000;font-size:10px'>友情提醒：该客户不满足零担月结客户条件，不能签订零担月结合同!</span>");
									if(contractInfo.getForm().findField('payWay').getValue() == 'MONTH_END'
										&&contractInfo.getForm().findField('ifLingDan').getValue()){
										Ext.getCmp('contractSaveButton').setDisabled(true);
									}
								}
								completePriceVersionDate();
								me.setDeptName(memberRecord);
						}else{
							Ext.getCmp("showNameDisplay").setValue("<span></span>");
							loadContractViewDataAfterRender(contractView,me.parent);
							loadBusinessData(mainLinkmanRecord, memberRecord);
							//加载近三月结算限额
							me.parent.contractInfo.contractTabBasicInfo.contractBasicInfo.getForm().findField('arrAmount').setValue(!Ext.isEmpty(contractView.contract)?contractView.contract.arrAmount:'0\0\0');
							//优惠信息 为0或1时展示成 空
							dealContractPreferential4Show(getPreferentialData(contractView),contractInfo);
							//快递优惠信息为0或者1展示成空
							dealContractExPreferential4Show(getExPreferentialData(contractView),contractInfo);
							//初始化默认的合同月结天数
							var viewContractDetailSuccess = function(result){
								var commonMonthEndDay = result.debtDays;
								Ext.getCmp('commonDebtDays_id').setValue(commonMonthEndDay);
							}
							var viewContractDetailFail = function(){
								MessageUtil.showErrorMes('默认合同月结天数初始化失败！');
							}
							var paramss = {};
							contractControl.initMonthEndDay(paramss,viewContractDetailSuccess,viewContractDetailFail);		

							//添加是否能签订月结显示框
							var arrAmount = contractInfo.getForm().findField('arrAmount').getValue();
							var exArrAmount = contractInfo.getForm().findField('exArrAmount').getValue();
//							if (checkCustCanSignLdMontEnd(arrAmount)&&checkCustCanSignKdMontEnd(exArrAmount)) {
//								Ext.getCmp("showNameDisplay").setValue("<span></span>");
//							} else if(!checkCustCanSignLdMontEnd(arrAmount) && !checkCustCanSignKdMontEnd(exArrAmount)){
//								Ext.getCmp("showNameDisplay").setValue("<span style='color:#ff0000;font-size:10px'>友情提醒：该客户不满足月结客户条件，不能签订月结合同!</span>");
//								if((contractInfo.getForm().findField('payWay').getValue() == 'MONTH_END'
//									&& contractInfo.getForm().findField('ifLingDan').getValue())
//									||(contractInfo.getForm().findField('exPayWay').getValue() == 'MONTH_END'
//										&& contractInfo.getForm().findField('ifExpress').getValue())){
//									Ext.getCmp('contractSaveButton').setDisabled(true);
//								}
//							//满足零担月结，不满足快递月结
//							}else if(checkCustCanSignLdMontEnd(arrAmount) && !checkCustCanSignKdMontEnd(exArrAmount)){
//								Ext.getCmp("showNameDisplay").setValue("<span style='color:#ff0000;font-size:10px'>友情提醒：该客户不满足快递月结客户条件，不能签订快递月结合同!</span>");
//								if(contractInfo.getForm().findField('exPayWay').getValue() == 'MONTH_END'
//									&& contractInfo.getForm().findField('ifExpress').getValue()){
//									Ext.getCmp('contractSaveButton').setDisabled(true);
//								}
//							//满足快递月结，不满足零担月结
//							}else if(!checkCustCanSignLdMontEnd(arrAmount)){ //&& checkCustCanSignKdMontEnd(exArrAmount)){
//								Ext.getCmp("showNameDisplay").setValue("<span style='color:#ff0000;font-size:10px'>友情提醒：该客户不满足零担月结客户条件，不能签订零担月结合同!</span>");
//								if(contractInfo.getForm().findField('payWay').getValue() == 'MONTH_END'
//									&& contractInfo.getForm().findField('ifLingDan').getValue()){
//									Ext.getCmp('contractSaveButton').setDisabled(true);
//								}
//							}
							me.setDeptName(memberRecord);
						}
						me.setDeptName(memberRecord);
					};
					// 如果 不能进行 新签操作 则提示并制空合同和客户信息
					var acquireFail = function(result){
						loadContractViewDataAfterRender(null,me.parent);
						loadBusinessData(null, Ext.create('SearchMemberResultModel'));
						//优惠信息 为0或1时展示成 空
						dealContractPreferential4Show(getPreferentialData(null),contractInfo);
						//快递优惠信息为0或者1展示成空
						dealContractExPreferential4Show(getExPreferentialData(contractView),contractInfo);
						MessageUtil.showErrorMes(result.message);
						return;
					}
					//发请求到服务器 通过客户获得客户合同信息 me.parent
					me.contractData.acquireNewContract({'memberId':custId},acquireSuccess,acquireFail);
				}
			}, {
				fieldLabel : i18n('i18n.MemberCustEditView.custNo'),
				name : 'custNum',
				id:'custNumber_id'
			}, {
				xtype:'customertrade',
				width:200,
				cls:'readonly',
				trade_labelWidth:30,
				second_trade_readOnly:true,
				trade_readOnly:true,
				trade_width:115,
				trade_name:'trade',
				trade_fieldname:i18n('i18n.PotentialCustManagerView.industry'),
				second_trade_labelWidth:0,
				second_trade_width:85,
				second_trade_name:'secondTrade',
				second_trade_fieldname:''
			}, {
				fieldLabel : i18n('i18n.MemberCustEditView.custLevel'),
				name : 'custGrade',
				xtype : 'dpcombo',
				width:160,
				forceSelection:true,
				store:me.contractData.getMemberGradeStore(),
				displayField:'codeDesc',
				valueField:'code',
				queryMode:'local'
			}, {
				fieldLabel : i18n('i18n.PotentialCustManagerView.contactName'),
				name : 'contactName'
			}, {
				fieldLabel : i18n('i18n.PotentialCustManagerView.contactPhone'),
				name : 'mobileNum'
			}, {
				fieldLabel : i18n('i18n.PotentialCustManagerView.contactTel'),
				name : 'telNum'
			}, {
				fieldLabel : i18n('i18n.MemberCustEditView.custType'),
				name : 'custType',
				xtype : 'dpcombo',
				width:160,
				forceSelection:true,
				store:me.contractData.getCustomerTypeStore(),
				displayField:'codeDesc',
				valueField:'code',
				queryMode:'local'
			}, {
				fieldLabel:User.deptCityLocation == "1"?'商业登记号':i18n('i18n.ScatterCustManagerView.taxId'),	
				name : 'taxregNum',
				regex:User.deptCityLocation == "1"?DpUtil.linkWayLimit('HKTAX'):DpUtil.linkWayLimit('CNTAX'),
				emptyText:User.deptCityLocation == "1"?'输入时不要输入“-”即12345678000':'请输入合法的税务登记号',
				regexText:User.deptCityLocation == "1"?'商业登记号为：12345678-000，输入时不要输入“-”即12345678000':'请输入合法的税务登记号'
			}, {
				fieldLabel : i18n('i18n.ScatterCustManagerView.idNumber'),
				name : 'idCard'
			}, {
				xtype : 'fieldcontainer',
				layout : {
					type : 'hbox'
				},
				colspan : 2,
				width : 360,
				defaultType : 'dptextfield',
				defaults : {
					labelWidth : 70,
					readOnly:true
				},
				items : [ 
				{
					fieldLabel : i18n('i18n.PotentialCustEditView.city'),
					name : 'cityName',
					width : 180
				}
//				{
//					xtype:'areaaddresscombox',
//					name:'cityId',
//					fieldLabel:i18n('i18n.PotentialCustEditView.city'),
//					width : 130,
//					operateType:'VIEW',
//					selectedValue:null,
//					tabPanel:['provincePanel','cityPanel']
//				}
				, {
					fieldLabel : i18n('i18n.PotentialCustEditView.address'),
					name : 'address',
					labelWidth:30,
					flex : 170
				}, {
					//提交custId到后台
					fieldLabel : i18n('i18n.ScatterUpgradeView.custId'),
					name : 'custId',
					hidden:true
				},{
					//隐藏域
					fieldLabel : i18n('i18n.ContractEditView.cityId'),
					name : 'cityId',
					hidden:true
				} ]
			} ]
		} ];
	},
	// 加载会员基本信息的record
	loadCustInfoData:function(custInfoRecord){
		var me = this;
		me.getForm().loadRecord(custInfoRecord);
		//TODO  me.getForm().findField('cityId').selectedValue = custInfoRecord.get('cityId');
	}
});

/**
 * 合同信息与其他信息
 */
Ext.define('ContractTabPanel', {
	extend : 'NormalTabPanel',
	items : null,
	activeTab : 0,
	store:null,
	contractInfoRecord : null,//合同信息 数据
	preferentialRecord:null,//优惠信息数据
	exPreferentialRecord:null,//优惠信息数据
	deptData : null,//已绑定部门 数据
	workFlowData:null,//工作流 数据
	contractView:null,
	contractData:null,  //Data层接口
	status:null,//合同编辑界面 状态 UPDATE,NEW,VIEW 
	initComponent : function() {
		var me = this;
		this.items = this.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		me.contractTabBasicInfo =  Ext.create('ContractTabContent',{
			'status':me.status,
			'store':me.store,
			'contractView':me.contractView,
			'contractData':me.contractData,
			'contractInfoRecord':me.contractInfoRecord,
			'preferentialRecord':me.preferentialRecord,
			'exPreferentialRecord':me.preferentialRecord});
		me.contractTabOtherInfo = Ext.create('OtherTabContent',{
			'contractData':me.contractData,
			'deptData':me.deptData,
			'workFlowData':me.workFlowData});
		return [me.contractTabBasicInfo,
		        me.contractTabOtherInfo];
	}
//	,
//	// 获得已绑定部门Data
//	getDeptData:function(){
//		var  me = this;
//		var deptData = [];
//		if(!Ext.isEmpty(me.contractInfoRecord.contractDepartList)){
//			deptData = me.contractInfoRecord.contractDepartList;
//		}
//		return deptData;
//	},
//	// 工作流Data
//	getWorkFlowData:function(){
//		var  me = this;
//		var workFlowData = [];
//		if(!Ext.isEmpty(me.contractInfoRecord.contractWorkflowList)){
//			workFlowData = me.contractInfoRecord.contractWorkflowList;
//		}
//		return workFlowData;
//	}
});

/**
 * 合同信息--CoCo
 */
Ext.define('ContractTabContent', {
	extend : 'TabContentPanel',
	title : i18n('i18n.ContractEditView.contractInfo'),
	contractInfoRecord : null,//合同信息 数据
	preferentialRecord:null,//优惠信息数据
	contractBaiscInfo : null,
	contractView:null,
	preferentialInfo : null,
	store:null,
	contractTabContentData:null,
	contractData:null,  //Data层接口
	status:null,//合同编辑界面 状态 UPDATE,NEW,VIEW 
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	initComponent : function() {
		var me = this;
		this.contractBasicInfo = Ext.create('ContractBasicForm',{'store':me.store,'status':me.status,
			'contractData':me.contractData,
			'contractInfoRecord':me.contractInfoRecord,
			'contractView':me.contractView
			});
		this.preferentialInfo = Ext.create('PreferentialGrid',{'status':me.status,
			'contractData':me.contractData,'preferentialRecord':me.preferentialRecord,
			'contractInfoRecord':me.contractInfoRecord});
		this.items = this.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [me.contractBasicInfo
//		        , {
//			xtype : 'basicpanel',
//			height : 50,
//			items : [me.preferentialInfo]
//		} 
		];
	}
});

var cellEditor =  Ext.create('Ext.grid.plugin.CellEditing', {
	clicksToEdit: 1
});

/**
 * 优惠信息
 */
Ext.define('PreferentialGrid', {
	extend : 'PopupGridPanel',
	id:'preferentialGrid_id',
	title:i18n('i18n.ContractEditView.t_iddiscount'),
	contractData:null,
	preferentialRecord:null,
	contractInfoRecord:null,
	status:null,//合同编辑界面 状态 UPDATE,NEW,VIEW 
	columns:null,
	store:null,
	selType: 'cellmodel',
//	plugins:[
//	         	cellEditor
//		    ],
	initComponent:function(){
		var me = this;
		me.columns = me.getColumns();
		me.plugins = [Ext.create('Ext.grid.plugin.CellEditing', {
				clicksToEdit: 1
			})];
		me.store = me.contractData.getPreferentialStore();
		this.callParent();
//		var arrayDate = [];//加载优惠信息 数据 
//		if(me.preferentialRecord!=null){
//			arrayDate[0] = me.preferentialRecord;
//		}
//		me.store.loadData(arrayDate);
	},
	getColumns:function(){
		var me = this;
		return [ 
		{
			text : 'id',
			dataIndex:'id',
			hidden:true
		},{
			text : i18n('i18n.ContractEditView.contractId'),
			dataIndex:'contractId',
			hidden:true
		},{
			text : i18n('i18n.ContractEditView.postageDiscounts'),
			dataIndex:'chargeRebate',
			flex : 1,
			xtype: 'numbercolumn',
			editor:{
				xtype: 'dpnumberfield',
				id:'chargeRebate_id',
				disabled:('VIEW' == me.status ),
				minValue: 0.800,
				maxValue:1,
				decimalPrecision:3,
				hideTrigger:true,
				listeners:{
					focus:function(field){
						//优惠类型"月发月送"运费折扣不可编辑值为1，优惠类型"价格折扣"运费折扣可编辑值
						if('VIEW' != me.status ){
							('MONTH_SEND' == Ext.getCmp('preferentialType_id').getValue())?field.setDisabled(true):'';
							('PRICE_REBATE' == Ext.getCmp('preferentialType_id').getValue())?field.setDisabled(false):'';
						}
					}
				}
			}
		},
//		{
//			xtype:'dpnumberfield',
//			fieldLabel : i18n('i18n.ContractEditView.toAcceptTheGoodsFeeDiscounts'),
////			hidden:true,
//            disabled:true,
//			minValue: 0,
//			maxValue:99999.999,
//			decimalPrecision:3,
//			hideTrigger:true,
//			name : 'receivePriceRate'
//		},
		 {
			text : i18n('i18n.ContractEditView.collectionOfRatesDiscount'),
			dataIndex:'agentgathRate',
			xtype: 'numbercolumn',
			flex : 1,
			editor:{
				xtype: 'dpnumberfield',
				disabled:('VIEW' == me.status)?true:false,
				minValue: 0,
				maxValue:1,
				decimalPrecision:3,
				hideTrigger:true
			}
		}, {
			text : i18n('i18n.ContractEditView.insuredRateDiscount'),
			dataIndex:'insuredPriceRate',
			xtype: 'numbercolumn',
			flex : 1,
			editor:{
				xtype: 'dpnumberfield',
				disabled:('VIEW' == me.status)?true:false,
				minValue: 0,
				maxValue:1,
				decimalPrecision:3,
				hideTrigger:true
			}
		}, {
			text : i18n('i18n.ContractEditView.toAcceptTheGoodsFeeDiscounts'),
			dataIndex:'receivePriceRate',
			xtype: 'numbercolumn',
			flex : 1,
			editor:{
				xtype: 'dpnumberfield',
				disabled:('VIEW' == me.status)?true:false,
//				disabled:true,
				minValue: 0,
				maxValue:99999.999,
				decimalPrecision:3,
				hideTrigger:true
			}
		}, {
			text : i18n('i18n.ContractEditView.deliveryFeeDiscount'),
			dataIndex:'deliveryPriceRate',
			xtype: 'numbercolumn',
			flex : 1,
			editor:{
				xtype: 'dpnumberfield',
//				disabled:('VIEW' == me.status)?true:false,
				disabled:true,
				minValue: 0,
				maxValue:1,
				decimalPrecision:3,
				hideTrigger:true
			}
		} ];
	},
	// 优惠信息/折扣信息data
	loadPreferentialData:function(preferentialData){
		var me = this;
		me.store.loadData(preferentialData);
	},
	//搜集优惠信息修改的信息
	collectAlterPreferentialData:function(alterPreferentialList,alterDeletePreferentialList,alterAddPreferentialList){
		var me = this;
		var preferentialRecord = me.contractData.getPreferentialStore().getUpdatedRecords();
		if(preferentialRecord.length>0){
			//合同基本信息的所有字段
			var fieldsArray = preferentialRecord.fields.items;
			for(var i = 0; i < fieldsArray.length;i++){
				//字段名
				var fieldName = fieldsArray[i].name;
				
				if(preferentialRecord.isModified(fieldName)){
					var basicApproveData = me.getEmptyPreferentialApproveData();
					basicApproveData.set('fieldName',fieldName);
					basicApproveData.set('newValue',preferentialRecord.get(fieldName));
					alterPreferentialList.push(basicApproveData.data);
				}
			}
		}
		//搜集优惠信息 删除的数据
		var deletePreferentialRecords = me.store.getRemovedRecords();
		for(var i = 0; i < deletePreferentialRecords.length; i++){
			alterDeletePreferentialList.push(me.getEmptyPreferentialApproveData(deletePreferentialRecords[i]));
		}
		
		//搜集优惠信息 新增的数据
		var addPreferentialRecords = me.store.getNewRecords();
		for(var i = 0; i < addPreferentialRecords.length; i++){
			alterAddPreferentialList.push(addPreferentialRecords[i].data);
		}
	},
	//创建空的优惠信息的approvedata
	getEmptyPreferentialApproveData:function(){
		var me = this;
		var basicApproveData = Ext.create('ApproveDataModel');
		basicApproveData.set('className','Preferential');
		basicApproveData.set('classId',me.preferentialRecord.get('id'));
		return basicApproveData;
	}
});

/**
 * 合同主体信息
 */
Ext.define('ContractBasicForm', {
	extend : 'BasicFormPanel',
	contractInfoRecord : null,
	store:null,
	contractView:null,
	id:'ContractBasicForm_id',
	contractData:null,  //Data层接口
	layout : {
		type : 'table',
		columns :3
	},
	defaultType : 'dptextfield',
	status:null,//合同编辑界面 状态 UPDATE,NEW,VIEW 
	defaults : {
		labelWidth:100,
		width:240,
		maxLength : 20,
		maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20)
	},
	initComponent : function() {
		var me = this;
		
		me.items = me.getItems();
		this.callParent();
		
		//对账日期
		var reconDate = me.getForm().findField('reconDate');
		me.selectFirst(reconDate);
		//开发票日期
		var invoicDate = me.getForm().findField('invoicDate');
		me.selectFirst(invoicDate);
		//结款日期
		var resultDate = me.getForm().findField('resultDate');
		me.selectFirst(resultDate);
		
		//合同所属子公司
		if(me.status === 'VIEW' || me.status === 'UPDATE'){
			var custCompay = me.getForm().findField('contractSubject');
			custCompay.forceSelection=false;
			custCompay.setRawValue(me.contractInfoRecord.data.contractSubject);
		}
		/**
		 * @author 潘光均
		 * @description 如果是月发月送合同折扣被改成了0.80以下就不让提交了
		 */
		if (me.contractInfoRecord.get('preferentialType')==='MONTH_SEND') {
			me.getForm().findField('chargeRebate').setMinValue(0.001);
			me.getForm().findField('chargeRebate').setMaxValue(0.999);
		}
		
	},
	getItems : function() {
		var me = this;
		return [ {
			fieldLabel : i18n('i18n.Integral.dept'),
			name : 'deptName',
			maxLength : Number.MAX_VALUE,
			readOnly : true
		}, {
			fieldLabel : i18n('i18n.ContractEditView.contractStatus'),
			name : 'contractStatus',
			xtype : 'dpcombo',
			queryMode:'local',
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code',
			store:me.contractData.getContractStateStore(),
			readOnly : true
		}, {
			fieldLabel : i18n('i18n.ContractEditView.oldContractNum'),
			name : 'beforeContractNum',
			readOnly:true
		}, {
			fieldLabel :  i18n('i18n.ContractEditView.c_contractNum'),
			readOnly:true,
			name : 'contractNum'
		}, {
			fieldLabel : i18n('i18n.ContractEditView.c_mainTitle'),
			name : 'contractSubject',
			id:'contractSubject_id',
			allowBlank:false,
			xtype : 'dpcombo',
			emptyText:i18n('i18n.ContractEditView.launchSectorBranch'),
			forceSelection:true,
			listConfig:{
			    loadMask:false
			},
			store:me.store,
			displayField:'contractSubject',
			valueField:'contractSubject',
			queryMode:'local',
			allowBlank:false
		}, {
			fieldLabel : i18n('i18n.ContractEditView.c_cust_fullName'),
			name : 'custCompany',
			allowBlank:false,
			blankText:i18n('i18n.ContractEditView.pleaseEnterYourFullName')
		}, {
			fieldLabel : i18n('i18n.ContractEditView.c_startDate'),
			id:'contractStartTime_id',
			name : 'contractBeginDate',
			xtype : 'dpdatefield',
			endDateFieldName : 'contractendDate',
			vtype : 'dateRange',
			minValue:new Date(),
			allowBlank:false,
			blankText:i18n('i18n.ContractEditView.pleaseInputContractStartDate'),
			format:'Y-m-d',
			listeners:{
				select:function(object){
					checkContractDate();
				}
			}
		}, {
			fieldLabel : i18n('i18n.ContractEditView.c_endDate'),
			id:'contractEndTime_id',
			name : 'contractendDate',
			xtype : 'dpdatefield',
			//startDateFieldName : 'contractBeginDate',
			//vtype : 'dateRange',
			minValue:new Date(),
			allowBlank:false,
			blankText:i18n('i18n.ContractEditView.pleaseInputContractEndDate'),
			format:'Y-m-d',
			listeners:{
				select:function(object){
					checkContractDate();
				}
			}
		}, {
			fieldLabel : i18n('i18n.ContractEditView.c_agreement_contract'),
			name:'linkManName',
			xtype:'dpcombo',
			forceSelection:true,
			store:me.contractData.getProtoContactStore(),
			displayField:'contactName',
			valueField:'contactId',
			queryMode:'local',
			listeners:{
				scope:me,
		    	select:function(combox,selectedRecords){
					if(selectedRecords.length > 0){
						var record = selectedRecords[0];
						var basicForm = me.getForm();
						
						basicForm.findField('linkManMobile').setValue(record.get('mobileNum'));
						basicForm.findField('linkManPhone').setValue(record.get('telNum'));
						basicForm.findField('contactId').setValue(record.get('contactId'));
					}
				},
				expand:function(combox){
					if(combox.store.getCount() == 0){
						MessageUtil.showMessage(i18n('i18n.ContractEditView.pleaseSelectCustNameFrist'));
					}
				},
				change:function(field,newValue){
					var me = this;
					if(Ext.isEmpty(newValue)){
						field.setValue(null);
					}
				}
			},
			allowBlank:false,
			blankText:i18n('i18n.ContractEditView.pleaseSelectTheProtocolContacts')
		}, {
			fieldLabel : i18n('i18n.PotentialCustManagerView.contactPhone'),
			name : 'linkManMobile',
			readOnly:true
		}, {
			fieldLabel : i18n('i18n.PotentialCustManagerView.contactTel'),
			name : 'linkManPhone',
			readOnly:true,
			maxLength : 40,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',40)
		}, {
			fieldLabel : i18n('i18n.ContractEditView.c_applyAmonut'),
			name : 'arrearaMount',
        	regex : /^\d{1,}$/,
        	id:'c_applyAmonut_id',
			regexText:i18n('i18n.ContractEditView.m_c_max10'),
        	maxLength:10,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',10),
			hideTrigger:true,
			readOnly:true
		}, {
			fieldLabel : i18n('i18n.ContractEditView.c_payDate'),
			name : 'resultDate',
			xtype : 'dpcombo',
			queryMode:'local',
            forceSelection:true,
			displayField:'dateDesc',
			valueField:'dateValue',
			store:me.contractData.getContractDateStore()
		},{
			xtype:'dpnumberfield',
			id:'commonDebtDays_id',
			fieldLabel:i18n('i18n.contract.Contract_MonthEnd_Date'),
			name:'debtDays',
			minValue: 0,
			maxLength:10,
			allowBlank:false,
			decimalPrecision:0,
			readOnly:true,
			hideTrigger:true,
			mouseWheelEnabled: false
		},
		{
			id:'ifForeignGoods_inForId',
			fieldLabel:i18n('i18n.contract.isdifferentchangeGood'),
			name:'ifForeignGoods',
			xtype:'dpcombobox',
			queryMode:'local',
//			readOnly:true,
            forceSelection:true,
			displayField:'name',
			valueField:'value',
			store:me.contractData.getBooleanStore()
		},{
			id:'dunningDeptCode_inForId',
			xtype:'queryCombox',
			fieldLabel:'催款部门',
			name:'dunningDeptCode',
			displayField:'deptName',  //控件显示的属性值
			valueField:'deptCode',      //提交的属性值
//			readOnly:true,
			store:null,
			deptName:'null',
			enableKeyEvents:true
			
		},{
			id:'signCompany_id',
			xtype:'queryCombox',
			fieldLabel:i18n('i18n.ContractManagerView.contractTaxSignCompany*'),
			name:'signCompany',
			displayField:'signCompany',  //控件显示的属性值
			valueField:'signCompany',      //提交的属性值
			store:Ext.create('SignCompanyListStore', {
						pageSize:5,
				        listeners:{
				        	beforeload:function(store, operation,eOpts){
				        		Ext.apply(operation,{
										params : {'signCompany':Ext.getCmp('signCompany_id').getRawValue()}
									}
								)
				        	}
					
				        }
			    	}),
			queryParam :'signCompany',
			queryMode:'remote',
			forceSelection:true,
			pageSize:5,
			minChars:0,
			listConfig: {
				loadMask:false,
		     	minWidth :300   //下拉框显示宽度
			},
			signCompany:'null',
			enableKeyEvents:true,
			allowBlank:false,
			listeners:{
				change:function(field, newValue) {if (Ext.isEmpty(newValue)) {field.setValue(null);}}
			}
		},{
			fieldLabel : i18n('i18n.ContractManagerView.contractTaxInvoiceType*'),
			name : 'invoiceType',
			id:'invoiceType_id',
			xtype : 'dpcombo',
			forceSelection:true,
			allowBlank:false,
			store:me.contractData.getInvoiceTypeStore(),
			displayField:'codeDesc',
			valueField:'code',
			queryMode:'local'
		},
		{
            xtype: 'fieldcontainer',
            colspan:3,
            fieldLabel: '<span style="font-weight:900;">零担合同</span>',
            width:720,
            layout:'column',
            defaultType: 'checkboxfield',
            items: [
                {
                    name      : 'ifLingDan',
                    inputValue: 'LINGDAN',
                    id        : 'iflingdan_id',
                    listeners:{
                    	change:function(v){
                    		var me = Ext.getCmp('ContractBasicForm_id');
                    		var contractForm = me.getForm();
                    		me.dealLingContract(contractForm,v);
                    	}
                    }
                },{
                	xtype:'displayfield',
                	value:'<span style="color:green">&nbsp;&nbsp;&nbsp;&nbsp;温馨提示：只有勾选零担合同后才能填写零担合同相应合同信息</span>'
                }
            ]
        },{
			fieldLabel : i18n('i18n.ContractEditView.t_3monthamount'),
			name : 'arrAmount',
			id:'t_3monthamount',
			readOnly:true,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',60),
			maxLength:60
		},{
			fieldLabel : i18n('i18n.ContractEditView.c_payWay'),
			name : 'payWay',
			id:'payWay_id',
			xtype : 'dpcombo',
			forceSelection:true,
			readOnly:true,
			store:me.contractData.getReckonWayStore(),
			displayField:'codeDesc',
			valueField:'code',
			queryMode:'local',
			blankText:i18n('i18n.ContractEditView.pleaseSelectResultsSectionBy'),
			listeners:{
				select:function(field,record){
					//控制不能满足月结的时候,不让提交
					if(Ext.getCmp('payWay_id').getValue() == 'MONTH_END'){
						monthEndPayWayEvent(Ext.getCmp('t_3monthamount').getValue(),me);
//					}else if(me.getForm().findField('ifExpress').getValue()
//							&&'MONTH_END'==me.getForm().findField('exPayWay').getValue()
//							&&!checkCustCanSignKdMontEnd(
//									me.getForm().findField('exArrAmount').getValue())){
//						Ext.getCmp("showNameDisplay").setValue("<span style='color:#ff0000;font-size:10px'>友情提醒：该客户不满足快递月结客户条件，不能签订快递月结合同!</span>");
//						Ext.getCmp('contractSaveButton').setDisabled(true);
					}else{
						Ext.getCmp('contractSaveButton').setDisabled(false);
					}
					payWaySlectEvent(field,record[0],me.status,me,me.contractInfoRecord,'LD')
				}
			}
		},{
			fieldLabel:i18n('i18n.ContractEditView.c_typeFavorable'),
			name:'preferentialType',
			id:'preferentialType_id',
			xtype : 'dpcombo',
			readOnly:true,
			forceSelection:true,
			store:me.contractData.getPrivilegeTypeStore(),
			displayField:'codeDesc',
			valueField:'code',
			queryMode:'local',
			listeners:{
				select:function(field,record){
					preferentialTypeSlectEvent(field,record[0],me.status,me,me.contractInfoRecord)
				}
			}
		},/*{xtype:'displayfield',colspan:3,width:106,value:i18n('i18n.contract.contractPefrentialAndPriceDebate')},*/ {
			xtype:'dpnumberfield',
			fieldLabel : i18n('i18n.ContractEditView.postageDiscounts'),
			minValue: 0.800,
			maxValue:0.9991,
			maxText:i18n('i18n.contract.contractDebateRange'),
			hideTrigger:true,
			decimalPrecision:20,
			readOnly:true,
			maxLength:5,
			maxLengthText:i18n('i18n.contract.contractDebateMaxLength'),
			name : 'chargeRebate'
		}, {
			xtype:'dpnumberfield',
			fieldLabel :i18n('i18n.ContractEditView.collectionOfRatesDiscount'),
			minValue: 0.0001,
			minText:i18n('i18n.contract.contractDebateRangeData'),
			maxValue:0.9991,
			maxText:i18n('i18n.contract.contractDebateRangeData'),
			maxLength:5,
			maxLengthText:i18n('i18n.contract.contractDebateMaxLength'),
			decimalPrecision:20,
			hideTrigger:true,
			readOnly:true,
			name : 'agentgathRate'
		}, {
			xtype:'dpnumberfield',
			fieldLabel : i18n('i18n.ContractEditView.insuredRateDiscount'),
			minValue: 0.0001,
			minText:i18n('i18n.contract.contractDebateRangeData'),
			maxValue:0.9991,
			maxText:i18n('i18n.contract.contractDebateRangeData'),
			maxLength:5,
			maxLengthText:i18n('i18n.contract.contractDebateMaxLength'),
			decimalPrecision:20,
			hideTrigger:true,
			readOnly:true,
			name : 'insuredPriceRate'
		},{
			xtype:'dpnumberfield',
			fieldLabel : i18n('i18n.ContractEditView.toAcceptTheGoodsFeeDiscounts'),
//			hidden:true,
			minValue: 0,
			minText:i18n('i18n.contract.contractReceivePriceRangeData'),
			maxValue:99999.999,
			maxText:i18n('i18n.contract.contractReceivePriceRangeData'),
			decimalPrecision:20,
			regex:/^\d+$|^(\d+)?\.\d{0,3}$/,
			regexText:i18n('i18n.contract.contractDebateMaxLength'),
			hideTrigger:true,
			readOnly:true,
			name : 'receivePriceRate'
		},{
			fieldLabel : i18n('i18n.contract.contractPriceVersion'),
			id:'priceVersionDate_id',
			name : 'priceVersionDate',
			xtype : 'dpdatefield',
			allowBlank:true,
			format:'Y-m-d H:i:s',
			readOnly:true
		},{
			/**
			 * @descripiton ：新增运费折扣最后更新时间
			 * @author 潘光均
			 * @date 2013-3-8
			 */
			fieldLabel : i18n('i18n.contract.contractDebateModityTime'),
			xtype: 'dpdatefield',
//			margin:'-4 0 0 0',
			hidden:me.status!='VIEW',
			editable:false,
			format:'Y-m-d H:i',
			name : 'modifyDate'
		},
//		{
//			//可使用额度，前端不必要显示的
//			xtype:'dpnumberfield',
//			fieldLabel:i18n('i18n.contract.contractUserableAmount'),
//			hidden:true,
//			name:'useableArrearAmount',
//			minValue:0,
//			hideTrigger:true,
//			mouseWheelEnabled: false
//		},
		/**********快递合同**************************/
		{
            xtype: 'fieldcontainer',
            fieldLabel: '<span style="font-weight:900;">快递合同</span>',
            defaultType: 'checkboxfield',
            colspan:3,
            width:720,
            layout:'column',
            items: [
                {
                    name      : 'ifExpress',
                    inputValue: 'EXPRESS',
                    id        : 'ifExpress_id',
                    listeners:{
                    	change:function(v){
                    		var me = Ext.getCmp('ContractBasicForm_id');
                    		var contractForm = me.getForm();
                    		me.dealExpressContract(contractForm,v);
                    	}
                    }
                },{
                	xtype:'displayfield',
                	value:'<span style="color:green">&nbsp;&nbsp;&nbsp;&nbsp;温馨提示：只有勾选快递合同后才能填写快递合同相应合同信息</span>'
                }
            ]
        },
        {
			fieldLabel : i18n('i18n.ContractEditView.t_3monthamount'),
			name : 'exArrAmount',
			id:'t_3monthamount_Express',
			readOnly:true,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',60),
			maxLength:60
		},{
			fieldLabel : i18n('i18n.ContractEditView.c_payWay'),
			name : 'exPayWay',
			id:'payWay_id_Express',
			xtype : 'dpcombo',
			forceSelection:true,
			readOnly:true,
			store:me.contractData.getReckonWayStore(),
			displayField:'codeDesc',
			valueField:'code',
			queryMode:'local',
			blankText:i18n('i18n.ContractEditView.pleaseSelectResultsSectionBy'),
			listeners:{
				select:function(field,record){
					//控制不能满足月结的时候,不让提交
//					if(Ext.getCmp('payWay_id_Express').getValue() == 'MONTH_END'){
//						monthEndExPayWayEvent(Ext.getCmp('t_3monthamount_Express').getValue(),me);
//					}else if(me.getForm().findField('ifLingDan').getValue()
//							&&'MONTH_END'==me.getForm().findField('payWay').getValue()
//							&&!checkCustCanSignKdMontEnd(
//									me.getForm().findField('arrAmount').getValue())){
//						Ext.getCmp("showNameDisplay").setValue("<span style='color:#ff0000;font-size:10px'>友情提醒：该客户不满足零担月结客户条件，不能签订零担月结合同!</span>");
//						Ext.getCmp('contractSaveButton').setDisabled(true);
//					}else{
//						Ext.getCmp('contractSaveButton').setDisabled(false);
//					}
					payWaySlectEvent(field,record[0],me.status,me,me.contractInfoRecord,'KD')
				}
			}
		},{
			fieldLabel:i18n('i18n.ContractEditView.c_typeFavorable'),
			name:'exPreferentialType',
			id:'preferentialType_id_Express',
			xtype : 'dpcombo',
			forceSelection:true,
			store:me.contractData.getExPrivilegeTypeStore(),
			displayField:'codeDesc',
			valueField:'code',
			queryMode:'local',
			readOnly:true,
			listeners:{
				select:function(field,record){
					if(field.getValue()!='PRICE_REBATE'){
						me.getForm().findField('exChargeRebate').setValue();
					}
					preferentialExpTypeSlectEvent(field,record[0],me.status,me,me.contractInfoRecord)
				}
			}
		},/*{xtype:'displayfield',colspan:3,width:106,value:i18n('i18n.contract.contractPefrentialAndPriceDebate')},*/ {
			xtype:'dpnumberfield',
			fieldLabel : i18n('i18n.ContractEditView.postageDiscounts'),
			minValue: 0.001,
			maxValue:0.999,
			maxText:i18n('i18n.contract.contractExDebateRange'),
			hideTrigger:true,
			decimalPrecision:20,
			maxLength:5,
			readOnly:true,
			maxLengthText:i18n('i18n.contract.contractDebateMaxLength'),
			name : 'exChargeRebate'
		}, {
			xtype:'dpnumberfield',
			fieldLabel :i18n('i18n.ContractEditView.excollectionOfRatesDiscount'),
			minValue: 0.5,
			minText:i18n('i18n.contract.excontractDebateRangeData'),
			maxValue:0.999,
			maxText:i18n('i18n.contract.excontractDebateRangeData'),
			maxLength:5,
			maxLengthText:i18n('i18n.contract.contractDebateMaxLength'),
			decimalPrecision:20,
			hideTrigger:true,
			readOnly:true,
			name : 'exAgentgathRate'
		}, {
			xtype:'dpnumberfield',
			fieldLabel : i18n('i18n.ContractEditView.insuredRateDiscount'),
			minValue: 0.25,
			minText:i18n('i18n.contract.excontractInsuredRangeData'),
			maxValue:0.999,
			maxText:i18n('i18n.contract.excontractInsuredRangeData'),
			maxLength:5,
			maxLengthText:i18n('i18n.contract.contractDebateMaxLength'),
			decimalPrecision:20,
			hideTrigger:true,
			readOnly:true,
			name : 'exInsuredPriceRate'
		},
		{
			fieldLabel : i18n('i18n.contract.contractPriceVersion'),
			id:'priceVersionDate_id_Express',
			name : 'exPriceVersionDate',
			xtype : 'dpdatefield',
			allowBlank:true,
			format:'Y-m-d H:i:s',
			readOnly:true
		}, {
			/**
			 * @descripiton ：新增运费折扣最后更新时间
			 * @author 潘光均
			 * @date 2013-3-8
			 */
			fieldLabel : i18n('i18n.contract.contractDebateModityTime'),
			xtype: 'dpdatefield',
			margin:'-4 0 0 0',
			hidden:me.status!='VIEW',
			editable:false,
			colspan:2,
			format:'Y-m-d H:i',
			name:'exModifyDate'
		},
//		//隐藏域 需求变更前字段
//		{
//			xtype:'dpnumberfield',
//			fieldLabel : i18n('i18n.ContractEditView.toAcceptTheGoodsFeeDiscounts'),
//			hidden:true,
//			value:1,
//			decimalPrecision:3,
//			hideTrigger:true,
//			name : 'receivePriceRate'
//		}, 
			{
			xtype:'dpnumberfield',
			fieldLabel : i18n('i18n.ContractEditView.deliveryFeeDiscount'),
			hidden:true,
			value:1,
			decimalPrecision:3,
			hideTrigger:true,
			name : 'deliveryPriceRate'
		},{
			fieldLabel : i18n('i18n.ContractEditView.c_isFavorable'),
			name : 'iddisCount',
			id:'iddisCount_id',
			hidden:true,
			xtype : 'dpcombo',
			queryMode:'local',
            forceSelection:true,
			displayField:'name',
			valueField:'value',
			store:me.contractData.getBooleanStore(),
			blankText:i18n('i18n.ContractEditView.pleaseIsureIsPreferential')
		}, {
			fieldLabel : i18n('i18n.ContractEditView.c_goodName'),
			hidden:true,
			name : 'goodsName'
		}, {
			fieldLabel : i18n('i18n.ContractEditView.c_registerAmonut'),
			name : 'regicapital',
			hidden:true
		} , {
			fieldLabel : i18n('i18n.ContractEditView.c_accountDate'),
			name : 'reconDate',
			xtype : 'dpcombo',
			queryMode:'local',
            forceSelection:true,
			hidden:true,
			displayField:'dateDesc',
			valueField:'dateValue',
			store:me.contractData.getContractDateStore()
		}, {
			fieldLabel : i18n('i18n.ContractEditView.c_billDate'),
			name : 'invoicDate',
			xtype : 'dpcombo',
			queryMode:'local',
            forceSelection:true,
			displayField:'dateDesc',
			valueField:'dateValue',
			store:me.contractData.getContractDateStore(),
			hidden:true
		}, //隐藏域  后台使用
		{
			xtype : 'hiddenfield',
			fieldLabel : i18n('i18n.ManualRewardIntegralEditView.id'),
			maxLength:Number.MAX_VALUE,
			name : 'contactId'
		}, {
			xtype : 'hiddenfield',
			fieldLabel : i18n('i18n.ContractEditView.deptid'),
			maxLength:Number.MAX_VALUE,
			name : 'deptId'
		}, {
			xtype : 'hiddenfield',
			name : 'contract4CommitId'
		}, {
			xtype : 'numberfield',
			hidden:true,
			name : 'versionNumber'
		}];
	},
	//根据按钮是否零担合同而处理相应的可操作控件
	dealLingContract:function(contractForm,v){
		var beforeData={};
		if (!Ext.isEmpty(this.contractView)) {
			beforeData = this.contractView.contract;
		}
		if(v.getValue()){
			//将结款方式、优惠类型置为可操作且不能为空
			contractForm.findField('payWay').setReadOnly(false);
			contractForm.findField('preferentialType').setReadOnly(false);
			contractForm.findField('payWay').allowBlank=false;
			contractForm.findField('preferentialType').allowBlank=false;
			if(beforeData.preferentialType=='PRICE_REBATE'){
				contractForm.findField('agentgathRate').setReadOnly(false);
				contractForm.findField('chargeRebate').setReadOnly(false);
				contractForm.findField('insuredPriceRate').setReadOnly(false);
				contractForm.findField('receivePriceRate').setReadOnly(false);
			}else if(beforeData.preferentialType=='MONTH_SEND'&&beforeData.payWay=='MONTH_END'){
				contractForm.findField('insuredPriceRate').setReadOnly(false);
				contractForm.findField('agentgathRate').setReadOnly(false);
				contractForm.findField('receivePriceRate').setReadOnly(false);
			}
		}else{
			//保存按钮有效
			Ext.getCmp('contractSaveButton').setDisabled(false);
			//把控件设为只读
			contractForm.findField('payWay').setReadOnly(true);
			contractForm.findField('preferentialType').setReadOnly(true);
			contractForm.findField('agentgathRate').setReadOnly(true);
			contractForm.findField('chargeRebate').setReadOnly(true);
			contractForm.findField('insuredPriceRate').setReadOnly(true);
			contractForm.findField('receivePriceRate').setReadOnly(true);
			//将结款方式、优惠类型置为可以为空
			contractForm.findField('payWay').allowBlank=true;
			contractForm.findField('preferentialType').allowBlank=true;
			//把数据还原
			if (Ext.isEmpty(this.contractView)) {
				beforeData = new NullContractModel();
				contractForm.loadRecord(beforeData);
				contractForm.findField('agentgathRate').setValue();
				contractForm.findField('chargeRebate').setValue();
				contractForm.findField('insuredPriceRate').setValue();
				contractForm.findField('receivePriceRate').setValue();
			}else{
				contractForm.findField('payWay').setValue(beforeData.payWay);
				contractForm.findField('preferentialType').setValue(beforeData.preferentialType);
				if(!Ext.isEmpty(beforeData.preferential)){
					contractForm.findField('agentgathRate').setValue(dealPreData(beforeData.preferential.agentgathRate));
					contractForm.findField('chargeRebate').setValue(dealPreData(beforeData.preferential.chargeRebate));
					contractForm.findField('insuredPriceRate').setValue(dealPreData(beforeData.preferential.insuredPriceRate));
					contractForm.findField('receivePriceRate').setValue(beforeData.preferential.receivePriceRate);
				}else{
					contractForm.findField('agentgathRate').setValue();
					contractForm.findField('chargeRebate').setValue();
					contractForm.findField('insuredPriceRate').setValue();
					contractForm.findField('receivePriceRate').setValue();
				}
			}
			if(contractForm.findField('payWay').getValue()=='MONTH_END'){
				//将可使用额度和借款日期值还原且改为可编辑,月结天数还原
				contractForm.findField('arrearaMount').setReadOnly(false);
				contractForm.findField('resultDate').setReadOnly(false);
				contractForm.findField('arrearaMount').setValue(beforeData.arrearaMount);
				contractForm.findField('resultDate').setValue(beforeData.resultDate);
				contractForm.findField('debtDays').setValue(Ext.getCmp('custRecord_id').monthDay);
			}
		}
	},
	//根据按钮是否快递合同而处理相应的可操作控件
	dealExpressContract:function(contractForm,v){
		var beforeData={};
		if (!Ext.isEmpty(this.contractView)) {
			beforeData = this.contractView.contract;
		}
		if(v.getValue()){
			contractForm.findField('exPayWay').setReadOnly(false);
			contractForm.findField('exPreferentialType').setReadOnly(false);
			contractForm.findField('exPayWay').allowBlank=false;
			contractForm.findField('exPreferentialType').allowBlank=false;
			//若是价格折扣，则将运费折扣设置为可编辑状态
			if('PRICE_REBATE' == beforeData.exPreferentialType){
				contractForm.findField('exChargeRebate').setReadOnly(false);
			}
			contractForm.findField('exAgentgathRate').setReadOnly(false);
			contractForm.findField('exInsuredPriceRate').setReadOnly(false);
		}else{
			//把控件设为只读
			contractForm.findField('exPayWay').setReadOnly(true);
			contractForm.findField('exPreferentialType').setReadOnly(true);
			contractForm.findField('exChargeRebate').setReadOnly(true);
			contractForm.findField('exAgentgathRate').setReadOnly(true);
			contractForm.findField('exInsuredPriceRate').setReadOnly(true);
			//将结款方式、优惠类型置为可以为空
			contractForm.findField('exPayWay').allowBlank=true;
			contractForm.findField('exPreferentialType').allowBlank=true;
			//把数据还原
			if (Ext.isEmpty(this.contractView)) {
				beforeData = new NullExContractModel();
				contractForm.loadRecord(beforeData);
				contractForm.findField('exAgentgathRate').setValue();
				contractForm.findField('exChargeRebate').setValue();
				contractForm.findField('exInsuredPriceRate').setValue();
			}else{
				contractForm.findField('exPayWay').setValue(beforeData.exPayWay);
				contractForm.findField('exPreferentialType').setValue(beforeData.exPreferentialType);
				if(!Ext.isEmpty(beforeData.exPreferential)){
					contractForm.findField('exAgentgathRate').setValue(dealPreData(beforeData.exPreferential.agentgathRate));
					contractForm.findField('exChargeRebate').setValue(dealPreData(beforeData.exPreferential.chargeRebate));
					contractForm.findField('exInsuredPriceRate').setValue(dealPreData(beforeData.exPreferential.insuredPriceRate));
				}else{
					contractForm.findField('exAgentgathRate').setValue();
					contractForm.findField('exChargeRebate').setValue();
					contractForm.findField('exInsuredPriceRate').setValue();
				}
			}
			if(contractForm.findField('exPayWay').getValue()=='MONTH_END'){
				//将可使用额度和借款日期改为可编辑
				contractForm.findField('arrearaMount').setReadOnly(false);
				contractForm.findField('resultDate').setReadOnly(false);
				contractForm.findField('arrearaMount').setValue(beforeData.arrearaMount);
				contractForm.findField('resultDate').setValue(beforeData.resultDate);
				contractForm.findField('debtDays').setValue(Ext.getCmp('custRecord_id').monthDay);
			}
		}
	},
	// 获得合同基本信息的record
	loadContractInfoRecord:function(contractInfoRecord){
		var me = this;
		var basicForm = me.getForm();
//		var signCompanyRecord = getSignCompanyRecord(contractView);
		basicForm.findField('signCompany').getStore().add({'signCompany':contractInfoRecord.get('signCompany')});
		basicForm.loadRecord(contractInfoRecord);
		//单独处理合同id
		basicForm.findField('contract4CommitId').setValue(contractInfoRecord.get('id'));
	},
	//默认选择store中第一个
	selectFirst:function(param){
		var me = this;
		param.store.on('load', function(obj) {
			if(param.store.getCount()>0){
				param.setValue(param.store.getAt(0));
			}
		});
	},
	//搜集合同基本信息用于提交
	collectContractBasicData:function(){
		var me = this;
		me.getForm().updateRecord(me.contractInfoRecord);
		return me.memberBasicRecord.data;
	},
	//搜集合同修改的信息
	collectAlterContractData:function(alterContractList){
		var me = this;
		
		//时间类型特殊处理 合同开始时间+合同结束时间
		var contractBeginDate = me.contractInfoRecord.get('contractBeginDate').toLocaleDateString();
		var contractendDate = me.contractInfoRecord.get('contractendDate').toLocaleDateString();
		me.getForm().updateRecord(me.contractInfoRecord);
		
		//合同基本信息的所有字段
		var fieldsArray = me.contractInfoRecord.fields.items;
		for(var i = 0; i < fieldsArray.length;i++){
			//字段名
			var fieldName = fieldsArray[i].name;
			
			if(me.contractInfoRecord.isModified(fieldName)){
				if('contractBeginDate' == fieldName){//合同开始时间：时间类型特殊处理
					if(contractBeginDate!=me.contractInfoRecord.get(fieldName).toLocaleDateString()){
						me.getContractApproveCellData(alterContractList,fieldName,me.contractInfoRecord);
					}
				}else if('contractendDate'==fieldName){//合同结束时间
					if(contractendDate!=me.contractInfoRecord.get(fieldName).toLocaleDateString()){
						me.getContractApproveCellData(alterContractList,fieldName,me.contractInfoRecord);
					}
				}else{
					me.getContractApproveCellData(alterContractList,fieldName,me.contractInfoRecord);
				}
			}
		}
//		return alterContractList;
	},
	//创建空的合同信息的approvedata
	getEmptyContractApproveData:function(){
		var me = this;
		var basicApproveData = Ext.create('ApproveDataModel');
		basicApproveData.set('className','Contract');
		basicApproveData.set('classId',me.contractInfoRecord.get('id'));
		return basicApproveData;
	},
	//创建 合同信息的approvedata cell
	getContractApproveCellData:function(alterContractList,fieldName,contractInfoRecord){
		var me = this;
		var basicApproveData = me.getEmptyContractApproveData();
		basicApproveData.set('fieldName',fieldName);
		basicApproveData.set('newValue',contractInfoRecord.get(fieldName));
		alterContractList.push(basicApproveData.data);
	}
});

/**
 * 其他信息
 */
Ext.define('OtherTabContent', {
	extend : 'TabContentPanel',
	deptData:null,//已绑定部门数据
	workFlowData:null,//工作流数据
	contractData:null,//data层
	title : i18n('i18n.ContractEditView.otherInfo'),
	deptInfo : null,
	workFlowInfo : null,
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	initComponent : function(){
		var me = this;
		me.deptInfo = Ext.create('DeptGrid',{'contractData':me.contractData});
		me.workFlowInfo = Ext.create('WorkFlowGrid',{'contractData':me.contractData});
		me.items = this.getItems();
		this.callParent();
	},
	getItems : function(){
		var me = this;
		return [{
			xtype : 'basicpanel',
			flex : 1,
			layout:{
				type:'vbox',
				align:'stretch'
			},
			items : [{
				xtype:'titlepanel',
				items:[{xtype: 'displayfield',
						value: i18n('i18n.ContractEditView.hasBindingDept'),
						width:100}]
			},{
				xtype:'basicpanel',
				flex:1,
				items:[me.deptInfo]
			}]
		},{
			xtype : 'basicpanel',
			flex : 1,
			layout:{
				type:'vbox',
				align:'stretch'
			},
			items : [{
				xtype:'titlepanel',
				items:[{xtype: 'displayfield',
						value: i18n('i18n.ContractEditView.workFlowInfo'),
						width:100}]
			},{
				xtype:'basicpanel',
				flex:1,
				items:[me.workFlowInfo]
			}]
		}];
	}
	
});

/**
 * 附属部门/已绑定部门
 */
Ext.define('DeptGrid',{
	extend : 'PopupGridPanel',
	contractData:null,//data层
	deptData:null,//已绑定部门数据
	initComponent:function(){
		var me = this;
		me.columns = me.getColumns();
		me.store = me.contractData.getContractDeptStore();
		this.callParent();
//		if(me.deptGridData!=null){
//			me.store.loadData(me.deptGridData);
//		}
	},
	getColumns :function(){ 
		var me = this;
		return [{
			text : i18n('i18n.ManualRewardIntegralManagerView.deptName'),
			dataIndex:'deptName',
			flex : 1
		},{
			text : i18n('i18n.ContractEditView.BindingTime'),
			dataIndex:'boundTime',
			flex : 1,
			width : 130,
			renderer : DpUtil.renderTime
		},{
			text : i18n('i18n.ContractEditView.removeTime'),
			dataIndex:'removeTime',
			flex : 1,
			renderer : DpUtil.renderTime
		},{
			text : '是否归属部门',
			dataIndex:'dept',
			flex : 1,
			renderer : function(value){
				if(value){
					return '是';
				}
				return '否';
			}
		}];
	},
	// 加载已绑定部门 data
	loadDeptData:function(deptData){
		var me = this;
		
		me.store.loadData(deptData);
	}
});

/**
 * 工作流信息
 */
Ext.define('WorkFlowGrid',{
	extend : 'PopupGridPanel',
	contractData:null,//data层
	workFlowData:null,
	initComponent:function(){
		var me = this;
		me.columns = me.getColumns();
		me.store = me.contractData.getWorkflowStore();
		this.callParent();
//		if(me.workFlowGridData!=null){
//			me.store.loadData(me.workFlowGridData);
//		}
	},
	getColumns :function(){ 
		var me = this;
		return [{
		text : i18n('i18n.ManualRewardIntegralManagerView.deptName'),
		dataIndex:'deptName',
		flex : 1
	},{
		text : i18n('i18n.ContractEditView.t_workFlowType'),
		dataIndex:'operatorType',
		flex : 1,
		renderer  : function(value){
			return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.WORKFLOWTYPE);
		}
	},{
		text : i18n('i18n.ContractEditView.OAworkFlowNum'),
		dataIndex:'workflowId',
		flex : 1
	},{
		text : i18n('i18n.ContractEditView.OAApprovalStatus'),
		dataIndex:'approvalState',
		flex : 1,
		renderer  : function(value){
			return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.CONTRACT_WORKFLOW_STATUS);
		}
	},{
		text : i18n('i18n.PotentialCustManagerView.createTime'),
		dataIndex:'createDate',
		width : 130,
		renderer : DpUtil.renderTime
	},{
		text : i18n('i18n.ContractEditView.lastApprovalTime'),
		dataIndex:'modifyDate',
		width : 130,
		renderer : DpUtil.renderTime
	},{
		text : i18n('i18n.ContractEditView.loatApprovalUser'),
		dataIndex:'approvalMan',
		flex : 1
	}];
	},
	// 加载工作流 data
	loadWorkFlowData:function(workFlowData){
		var me = this;
		me.store.loadData(workFlowData);
	}
});

/**
 * 申请事由
 */
Ext.define('ApplyReasonPanel', {
	extend : 'BasicVboxFormPanel',
	contractData:null,//data层
	application:null,//申请事由 数据
	status:null,
	initComponent : function() {
		var me = this;
		me.items = this.getItems();
		this.callParent();
		if (me.status==='ALTERRATE') {
			me.getForm().findField('application').allowBlank=true;
		}
	},
	getItems : function() {
		var me = this;
		return [{
			xtype:'titlepanel',
			items:[{xtype: 'displayfield',
					value: i18n('i18n.ContractEditView.c_applyReason')
					}]
		},{
			xtype:'textareapanel',
			flex:1,
			items:[{xtype : 'textareafield',
					name:'application',
					readOnly:me.status==='ALTERRATE',
				   allowBlank : false,
//				   regex : /^[A-Za-z0-9\u4e00-\u9fa5]{1,200}$/,
				   maxLength:200,
				   maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',200),
//				   regexText:i18n('i18n.ContractEditView.m_char200'),
				   blankText:i18n('i18n.ContractEditView.m_notNull'),
				  emptyText:i18n('i18n.ContractEditView.pleaseInputReasonForApplication')}]
		}];
	},
	// 加载申请事由
	loadApplication:function(application){
		var me = this;
		me.getForm().findField('application').setValue(application);
	}
});


/**
 * 合同附件
 */
Ext.define('ContractAttachment',{
	extend:'BasicVboxPanel',
	contractData:null,//data层
	attachData:null,
	status:null,
	operateType:'DELAY',//IMMEDIATE:附件直接持久化到数据库,DELAY：附件延迟持久化到数据库
	handType:null,							//'UPDATE' 合同修改，'null':否则为新签和查看详情
	initComponent : function() {
		var me = this;
		me.items = me.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [
		{
			xtype:'toppanel',
			items:[
			{
				xtype:'titlepanel',
				flex:1,
				items:[{
					xtype:'displayfield',
					value:i18n('i18n.ContractEditView.c_file')
				}]
			}
			,{
				xtype:'btnpanel',
				items:[{
					xtype:'button',
					hidden:true,
					id:'fileUpLoadButton',
					text:i18n('i18n.ChangeContactAffiliatedRelationView.uploadFile'),
					scope:me,
					handler:me.showUploadAttachWin
				},{
					xtype:'button',
					text:i18n('i18n.PotentialCustManagerView.delete'),
					disabled:('VIEW'==me.status)?true:false,
					scope:me,
					id:'deleteFile_id',
					handler:function(){
						var grid = me.down('grid');
						var store = grid.store;
						selection = grid.getSelectionModel().getSelection();
						//TODO  营业执照和电子档合同附件不准删除
						for(var i = 0;i<selection.length;i++){
							if(!Ext.isEmpty(selection[i].get('id'))){
								MessageUtil.showMessage(i18n('i18n.contractEditView.cannotDel',selection[i].get('fileName')));
								return;
							}
							if(selection[i].get('fileBusinessType') == DpUtil.changeDictionaryCodeToDescrip('ELECTRONICCONTRACT',DataDictionary.CONTRACT_ANNEX) 
								||selection[i].get('fileBusinessType') == DpUtil.changeDictionaryCodeToDescrip('BUSINESSLICENSE',DataDictionary.CONTRACT_ANNEX)
								||selection[i].get('fileBusinessType') == DpUtil.changeDictionaryCodeToDescrip('CHANGEPROTOCOL',DataDictionary.CONTRACT_ANNEX)
								||selection[i].get('fileBusinessType') == DpUtil.changeDictionaryCodeToDescrip('CONTRACTTAX',DataDictionary.CONTRACT_ANNEX)){
								selection[i].set('fileName',null);
							}else{
								store.remove(selection[i]);
							}
						}
					}
				}]
			}]
		}
		,{
			xtype : 'popupgridpanel',//附件grid
			id:'attachPanelId',
			autoScroll:true,
			flex:1,
			store:me.contractData.getFileInfoStore(),
			selModel : Ext.create('Ext.selection.CheckboxModel'),
			columns:[new Ext.grid.RowNumberer(),
			         {
						header:'类型',
						hidden://'UPDATE' == me.handType||
						'VIEW'==me.status?true:false,
						width : 150,
						dataIndex:'fileBusinessType'
			         },{
						header:i18n('i18n.ChangeContactAffiliatedRelationView.fileName'),
						flex : 1,
						dataIndex:'fileName'
			         },{
				         header:i18n('i18n.ChangeContactAffiliatedRelationView.savePath'),
			        	 dataIndex:'savePath',
			        	 hidden:true
			         }]
		}];
	},
	//显示上传附件窗口
	showUploadAttachWin:function(){
		var me = this;
		Ext.create('UploadAttachmentWin',{'operateType':me.operateType,'parent':me,'contractData':me.contractData}).show();
	},
	//新增合同附件 调用
	addAttachData:function(attachData){
		var me = this;
		Ext.getCmp('attachPanelId').store.add(attachData);
	},
	// 加载合同附件Data
	loadAttachData:function(attachData){
		var me = this;
		Ext.getCmp('attachPanelId').store.loadData(attachData);
	},
	//搜集合同附件修改的信息
	collectAlterAttachData:function(alterAddAttachList){
		var me = this;		
		//搜集合同附件 新增的数据
		var addAttachRecords = me.contractData.getFileInfoStore().getNewRecords();
		for(var i = 0; i < addAttachRecords.length; i++){
			alterAddAttachList.push(addAttachRecords[i].data);
		}
	}
});

/**
 * 上传附件弹出框
 */
Ext.define('UploadAttachmentWin',{
	extend:'PopWindow',
	title:i18n('i18n.PotentialCustManagerView.messageTip'),
	layout:{
		type:'vbox',
		align:'stretch'
	},
	operateType:null,
	contractData:null,
	width:400,
	height:150,
	parent:null,
	attachmentForm:null,  //合同附件form
	initComponent:function(){
		var me = this;
		me.items = [
		{
			xtype:'titlepanel',
			items:[{xtype: 'displayfield',
					value: i18n('i18n.ContractEditView.pleaseInputContractAttachment'),
					width:100}]
		},
		{
			xtype:'basicformpanel',
			flex:1,
			layout:{
				type : 'hbox'
			},
			defaults : {
				margins : '0 5 0 0'
			},
			items:[{
				xtype:'filefield',
				name:'file',
				fieldLabel:i18n('i18n.ContractEditView.contractAttachment'),
				labelWidth:60,
				allowBlank: false,
				buttonText:i18n('i18n.ChangeContactAffiliatedRelationView.view'),
				flex:3
			},{
				text:i18n('i18n.ChangeContactAffiliatedRelationView.sourceType'),
				name:'type',
				xtype:'hiddenfield',
				value:'contractAttDir'
			}]
		}];
		me.fbar = me.getFbar();
		this.callParent();
	},
	getFbar:function(){
		var me = this;
		return [{
			text:i18n('i18n.ChangeContactAffiliatedRelationView.button_upload'),
			xtype:'button',
			scope:me,
			handler:me.uploadFile
		},{
			text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
			xtype:'button',
			handler:function(){
				me.close();
			}
		}];
	},
	//文件上传
	uploadFile:function(){
		var me = this;
		var form = me.down('form');
//		var uploadSuccess = function(result){
//			Ext.Msg.alert('温馨提示',result.fileInfoList[0].fileName+i18n('i18n.ContractEditView.uploadSuccessWar'));
//		}
//		var uploadFail = function(result){
//			Ext.Msg.alert('温馨提示',result.message);
//		}
//		me.contractData.uploadFile(form,uploadSuccess,uploadFail);
		if(!form.getForm().isValid()){MessageUtil.showMessage(i18n('i18n.ContractEditView.m_uploadFile'));return;}
		form.submit({
                    url: '../common/fileUpload.action',
                    waitMsg: i18n('i18n.ChangeContactAffiliatedRelationView.uploading'),
                    success: function(form, response) {
                    	var result = response.result;
                    	if(result.success){
                    		var fileInfo = result.fileInfoList[0];
//                        	var contractNoun = me.contractData.changeFileInfo2ContractNoun(fileInfo,new FileInfoModel().data);
                        	me.parent.addAttachData(fileInfo);
                        	MessageUtil.showInfoMes(i18n('i18n.ChangeContactAffiliatedRelationView.file') + fileInfo.fileName + i18n('i18n.ChangeContactAffiliatedRelationView.uploadSeccess'));
                        	me.close();
						}else{
                       		MessageUtil.showErrorMes( result.message);
                       		me.close();
						}
                    },
                 failure:function(form, response){
                 	var result = response.result;
                 	if(!result.success){
                       	MessageUtil.showErrorMes(result.message);
					}
                 }
                });
	}
});
/**
 * 合同修改窗体
 */
Ext.define('ContractUpdateWin',{
	extend:'PopWindow',
	title:'合同修改',
	id:'ContractUpdateWin_id',
	layout:{
		type:'vbox',
		align:'stretch'
	},
	operateType:null,
	contractView:null,  										//合同详细信息"ContractViewModel"
	width:775,
	height:760,
	autoScroll:true,
	parent:null,
	beforeUpdateContract:null,  								//合同修改前信息form
	afterUpdateContract:null,  									//合同修改后信息（待修改信息）form
	applyReasonInfo:null,										//申请事由
	fileInfo:null,  											//合同附件form
	status:null,
	uploadAttachment:null,										//合同上传附件form
	initComponent:function(){
		var me = this;
		var contractRecord = getContractInfoRecord(me.contractView);
		me.beforeUpdateContract = Ext.create('BeforeUpdateContract',{
			'contractData':contractControl,
			'status':me.status,
			'contractRecord':contractRecord
			});
		me.afterUpdateContract = Ext.create('AfterUpdateContract',{
			'contractView':me.contractView,
			'contractData':contractControl,
			'status':me.status,
			'contractRecord':contractRecord
		});
		me.applyReasonInfo = Ext.create('ApplyReasonPanel',{
			'status':me.status,
			'contractData':contractControl,
			'application':[]});
		me.fileInfo = Ext.create('ContractAttachment',{
			'status':(me.status==='ALTERRATE'?'VIEW':'UPDATE'),
			'operateType':'DELAY',
			'handType':'UPDATE',
			'contractData':contractControl,
			'attachData':null});
		me.uploadAttachment = Ext.create('UploadAttachmentForm',{
			'status':me.status,
			'fileInfo':me.fileInfo,
			'contractData':contractControl
		});
		me.items = me.getItems();
		me.fbar = me.getFbar();
		this.callParent();
		//初始化业务规则
		me.initContractViewData4Update(me.contractView,me);
		//加载合同详细信息
		loadContractViewData4Update(me.contractView,me);
		me.fileInfo.addAttachData(me.getAttachData());
		//处理优惠  默认展示为空
		var preferentialRecord = getPreferentialData(me.contractView);
		dealContractPreferential4Show(preferentialRecord,me.beforeUpdateContract);
		dealContractPreferential4Show(preferentialRecord,me.afterUpdateContract);
		//快递优惠信息为0或者1展示成空
		dealContractExPreferential4Show(getExPreferentialData(me.contractView),me.beforeUpdateContract);
		dealContractExPreferential4Show(getExPreferentialData(me.contractView),me.afterUpdateContract);
	},
	getItems:function(){
		var me = this;
		return [me.beforeUpdateContract,
			me.afterUpdateContract,
			{
				xtype:'basicpanel',
				height : 110,
				layout:{
					type:'hbox',
					align:'stretch'
				},
				items:[{
					xtype : 'basicpanel',
					flex:1,
					items : [me.applyReasonInfo]
				},{
					xtype : 'basicpanel',
					width:5
				},{
					xtype : 'basicpanel',
					flex : 1,
					items : [ me.fileInfo ]
				}]
		},me.uploadAttachment];
	},
	getFbar:function(){
		var me = this;
		return [{
			text:'提交',
			xtype:'button',
			scope:me,
			id:'saveButton_id',
			handler:me.commitEvent
		},{
			text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
			xtype:'button',
			handler:function(){
				me.close();
			}
		}];
	},
	commitEvent:function(button){
		//防止重复点击保存按钮
		button.setDisabled(true);
		var me = this;
		var form = me.afterUpdateContract.getForm();
		var beforeForm = me.beforeUpdateContract.getForm();
		var contract = getContractInfoRecord(me.contractView);
		//业务规则校验
		var validateResult = me.validateCommitDate();
		if(!validateResult.success){
			button.setDisabled(false);
			MessageUtil.showMessage(validateResult.message);
			return;
		}
		
		//提示 结算限额已超过近三月发货金额最高月的两倍，请确定是否提交！
		var amount = form.findField('arrearaMount').getValue();//结算限额
		var arrAmount = beforeForm.findField('arrAmount').getValue();//零担三个月发货情况
		var exArrAmount = beforeForm.findField('exArrAmount').getValue();//快递三个月发货情况
		var exPayWay = beforeForm.findField('exPayWay').getValue();//快递结款方式
		var payWay = beforeForm.findField('payWay').getValue();//合同结款方式
		
		if (me.status==='ALTERRATE'|| me.status==='MODIFY' || me.status === 'priceVersionDate') {
			MessageUtil.showQuestionMes('是否确认提交？', function(e) {
				if(e == 'yes'){
					me.commitFn(me,button);
				}else{
					button.setDisabled(false);
				}
			},function(){
				button.setDisabled(false);
			});
		}else{
			if(dealContractArrMount(contract,exPayWay,payWay,amount,arrAmount,exArrAmount)){
				MessageUtil.showQuestionMes( i18n('i18n.ContractEditView.m_isOverMax3Amount'), function(e) {
					if(e == 'yes'){
						me.commitFn(me,button);
					}else{
						button.setDisabled(false);
					}
				},function(){
					button.setDisabled(false);
				});
			}else{
				me.commitFn(me,button);
			}
		}
	},
	commitFn:function(me,button){
		var commitParams = me.assembleContractUpdateData();
		if ('ALTERRATE' === me.status) {
			var contract = commitParams.contract;
			contract.useableArrearAmount = Ext.getCmp('t_arrearaMount_update_id').getValue();
			commitParams.contract = contract;
			var successFn=function(result){
				MessageUtil.showInfoMes('修改合同运费折扣成功！');
				contractManagerDataControl.getContractStore().loadPage(1);
				me.close();
			}
			var failureFn=function(result){
				MessageUtil.showErrorMes(result.message);
				button.setDisabled(false);
			}
			contractControl.modifyMonthSendRate(commitParams,successFn,failureFn);		
			return;
		} 
		if ('MODIFY' === me.status) {
			commitParams = {};
			commitParams.debtDays = Ext.getCmp('modifyDebtDays_id').getValue();
			commitParams.contractId =  me.contractView.contract.id;
			var saveSuccess = function(result){
				MessageUtil.showInfoMes('修改合同月结天数成功！');
				contractManagerDataControl.getContractStore().loadPage(1);
				me.close();
				button.setDisabled(false);
			}
			var saveFail = function(result){
				MessageUtil.showErrorMes(result.message);
				button.setDisabled(false);
			}
			contractControl.updateSpecialContractMonthEnd(commitParams,saveSuccess,saveFail);
			return;
		}
		if('dunningDept' == me.status){
			commitParams = {};
			commitParams.ifForeignGoods = Ext.getCmp('ifForeignGoods_id').getValue(); 
			commitParams.dunningDeptCode = Ext.getCmp('dunningDeptCode_id').getValue();
			commitParams.contractId =  me.contractView.contract.id;
			var saveSuccess = function(result){
				MessageUtil.showInfoMes('催款部门设置成功');
				contractManagerDataControl.getContractStore().loadPage(1);
				me.close();
				button.setDisabled(false);
			}
			var saveFail = function(result){
				MessageUtil.showErrorMes(result.message);
				button.setDisabled(false);
			}
			contractControl.modifyDuningDept(commitParams,saveSuccess,saveFail);
			return;
		   }
		if('priceVersionDate'== me.status){
			commitParams = {};
			commitParams.priceVersionDate = Ext.getCmp('priceVersionDate_UpdateId').getValue(); 
			commitParams.exPriceVersionDate = Ext.getCmp('exPriceVersionDate_UpdateId').getValue(); 
			commitParams.contractId =  me.contractView.contract.id;
			var saveSuccess = function(result){
				MessageUtil.showInfoMes('价格版本时间设置成功');
				contractManagerDataControl.getContractStore().loadPage(1);
				me.close();
				button.setDisabled(false);
			}
			var saveFail = function(result){
				MessageUtil.showErrorMes(result.message);
				button.setDisabled(false);
			}
			contractControl.modifyPriceVersionDate(commitParams,saveSuccess,saveFail);
			return;
		}  
		var exInsuredPriceRate = me.afterUpdateContract.getForm().findField('exInsuredPriceRate').getValue();
		var exAgentgathRate = me.afterUpdateContract.getForm().findField('exAgentgathRate').getValue();
		var showMessage = '修改合同将产生OA审批工作流，是否确定修改？';
		var beforeInvoiceType = me.beforeUpdateContract.getForm().findField('invoiceType').getValue();
		var afterForm = me.afterUpdateContract.getForm();
		var afterInvoiceType = afterForm.findField('invoiceType').getValue();
		if((('NOT_MONTH_END'== me.contractView.contract.payWay 
				&& 'MONTH_SEND' == Ext.getCmp('preferentialType_updateId').getValue()
				&&'NOT_MONTH_END'== me.contractView.contract.exPayWay 
				&& 'MONTH_REBATE' == Ext.getCmp('exPreferentialType_updateId').getValue())
				||('NOT_MONTH_END'== me.contractView.contract.payWay 
						&& 'NOT_PREFERENTIAL' == Ext.getCmp('preferentialType_updateId').getValue()
						&&'NOT_MONTH_END'== me.contractView.contract.exPayWay 
						&& 'MONTH_REBATE' == Ext.getCmp('exPreferentialType_updateId').getValue())
				||('NOT_MONTH_END'== me.contractView.contract.payWay 			
						&& 'MONTH_SEND' == Ext.getCmp('preferentialType_updateId').getValue()
						&&'NOT_MONTH_END'== me.contractView.contract.exPayWay 
						&& 'NOT_PREFERENTIAL' == Ext.getCmp('exPreferentialType_updateId').getValue())
				||
				('NOT_MONTH_END'== me.contractView.contract.payWay 
						&& Ext.isEmpty(me.contractView.contract.payWay)
						&&'NOT_MONTH_END'== me.contractView.contract.exPayWay 
						&& 'MONTH_REBATE' == Ext.getCmp('exPreferentialType_updateId').getValue())
				||
				('NOT_MONTH_END'== me.contractView.contract.payWay 
						&& 'MONTH_SEND' == Ext.getCmp('preferentialType_updateId').getValue()
						&&Ext.isEmpty(me.contractView.contract.exPayWay) 
						&&Ext.isEmpty(Ext.getCmp('exPreferentialType_updateId').getValue()))
				
				||(Ext.isEmpty(me.contractView.contract.payWay)
						&& Ext.isEmpty(Ext.getCmp('preferentialType_updateId').getValue())
						&&'NOT_MONTH_END'== me.contractView.contract.exPayWay 
						&& 'MONTH_REBATE' == Ext.getCmp('exPreferentialType_updateId').getValue())
				&&Ext.isEmpty(exInsuredPriceRate)&&Ext.isEmpty(exAgentgathRate)		
				)&&(beforeInvoiceType == afterInvoiceType)){
				showMessage = '合同将开始生效，请确认是否提交？';
			}
		MessageUtil.showQuestionMes(showMessage,function(e){
				if('yes' == e){
					var commitParams = me.assembleContractUpdateData();
					var saveSuccess = function(result){
						var updFlag = result.updFlag;
						var message = i18n('i18n.PotentialCustEditView.saveSuccess');
						if('true'==updFlag){//发票标记修改了 产生工作流
							message = i18n('i18n.ContractEditView.contractSaveSuccessWorkFlow')+result.workFlowNum+i18n('i18n.ContractManagerView.contractTaxChangeEffectMsg');
						}
						else if(!Ext.isEmpty(result.workFlowNum)){
							message = i18n('i18n.ContractEditView.contractSaveSuccessWorkFlow')+result.workFlowNum;
						}
						MessageUtil.showInfoMes(message);
						contractManagerDataControl.getContractStore().loadPage(1);
						me.close();
						button.setDisabled(false);
					}
					var saveFail = function(result){
						MessageUtil.showErrorMes(result.message);
						button.setDisabled(false);
						}
				  if('MODIFY' != me.status){
						var contract = commitParams.contract;
						contract.useableArrearAmount = Ext.getCmp('t_arrearaMount_update_id').getValue();
						commitParams.contract = contract;
						contractControl.alterContractInfo(commitParams,saveSuccess,saveFail);
				  }
				}else{
					button.setDisabled(false);
				}
		},function(){
				button.setDisabled(false);
		})
	},
	//组装合同修改数据 用于修改时提交
	assembleContractUpdateData:function(){
		var me = this;
		var commitParams = {};
		var contract = getContractInfoRecord(me.contractView);
		var preferentialInfo = getPreferentialData(me.contractView);
		//优惠信息
		me.afterUpdateContract.getForm().updateRecord(preferentialInfo);
		//处理优惠信息 用于提交 折扣为空则传到后台为1（后台接收类型为Double）
		dealContractPreferential4Commit(preferentialInfo);
		//合同主信息
		me.afterUpdateContract.getForm().updateRecord(contract);
		//申请事由
		var application = me.applyReasonInfo.getForm().findField('application').getValue();
		
		//附件信息
		var alterAddAttachList = new Array();
		me.fileInfo.collectAlterAttachData(alterAddAttachList);
		//TODO 通过优惠类型 维护 优惠信息 实体 是否为 null
		//TODO 优惠信息为null 则展示成 发送后台为 1
		contract.set('preferential',preferentialInfo.data);//优惠信息
		contract.set('application',application);//申请事由
		contract.set('fileInfoList',alterAddAttachList);//附件信息
		contract.set('iddisCount',true);//是否折扣，永远为true（由于需求变更界面取消该值的选择）
//		me.contractView.contract = contract.data;
		//快递优惠信息
		contract.data.exPreferential = {};
		contract.data.exArrAmount = me.beforeUpdateContract.getForm().findField('exArrAmount').getValue();
		contract.data.exPayWay = me.beforeUpdateContract.getForm().findField('exPayWay').getValue();
		contract.data.exPreferentialType = me.afterUpdateContract.getForm().findField('exPreferentialType').getValue();
		contract.data.exPreferential.chargeRebate = chnangNull2One(me.afterUpdateContract.getForm().findField('exChargeRebate').getValue());
		contract.data.exPreferential.agentgathRate = chnangNull2One(me.afterUpdateContract.getForm().findField('exAgentgathRate').getValue());
		contract.data.exPreferential.insuredPriceRate = chnangNull2One(me.afterUpdateContract.getForm().findField('exInsuredPriceRate').getValue());
//		contract.data.exPreferential.receivePriceRate = 1;
		contract.data.exPreferential.deliveryPriceRate = 1;
		
		var ifLingDan = me.afterUpdateContract.getForm().findField('ifLingDan').getValue();
		var ifExpress = me.afterUpdateContract.getForm().findField('ifExpress').getValue();
		
		//快递和零担一起改
		if(ifLingDan&&ifExpress){
			contract.data.contractType = 'EL';
		//只改订快递
		}else if(!ifLingDan&&ifExpress){
			contract.data.contractType = 'EXPRESS';
		//只改订零担
		}else if(ifLingDan&&!ifExpress){
			contract.data.contractType = 'LTT';
		}
		
		commitParams.contract= contract.data;
		return commitParams;
	},
	//按照业务规则 初始化 数据
	initContractViewData4Update:function(){
		var me = this;
		var form = me.afterUpdateContract.getForm();
		var contract = getContractInfoRecord(me.contractView);
		var payWay = contract.get('payWay');
		var exPayWay = contract.get('exPayWay');
		var exPreferentialType = contract.get('exPreferentialType');
		var preferentialType = contract.get('preferentialType');
		var priceVersionDate = form.findField('priceVersionDate');
		var exPriceVersionDate = form.findField('exPriceVersionDate');
		//只有当结款方式为“月结”时，修改结算限额才可编辑，否则不可编辑
		if(payWay != 'MONTH_END'&& exPayWay != 'MONTH_END'){
			form.findField('arrearaMount').setReadOnly(true);
		}
		//如果原合同为纯零担，则快递合同不可编辑
		if(Ext.isEmpty(payWay)&&Ext.isEmpty(preferentialType)){
			form.findField('ifLingDan').setReadOnly(true);
		}
		//如果原合同为纯快递，则快零担同不可编辑
		if(Ext.isEmpty(exPayWay)&&Ext.isEmpty(exPreferentialType)){
			form.findField('ifExpress').setReadOnly(true);
		}
		var chargeRebate = form.findField('chargeRebate');//运费折扣
		var agentgathRate = form.findField('agentgathRate');//代收费率折
		var exChargeRebate = form.findField('exChargeRebate');//运费折扣
		var receivePriceRate = form.findField('receivePriceRate');//接货费
		var insuredPriceRate = form.findField('insuredPriceRate');//保价费率折扣
		var ifLingDan = form.findField('ifLingDan');
		var ifExpress = form.findField('ifExpress');
		//锁定优惠类型
		form.findField('preferentialType').setReadOnly(true);
		form.findField('exPreferentialType').setReadOnly(true);
		dealPreferentialType(preferentialType,payWay,chargeRebate,agentgathRate,insuredPriceRate,receivePriceRate,'ALTER');
		form.findField('chargeRebate').setReadOnly(true);
		form.findField('agentgathRate').setReadOnly(true);
		form.findField('insuredPriceRate').setReadOnly(true);
		form.findField('receivePriceRate').setReadOnly(true);
		
		if(me.status !='ALTER'){
			ifLingDan.setReadOnly(true);
			ifExpress.setReadOnly(true);
			chargeRebate.setReadOnly(true);
			agentgathRate.setReadOnly(true);
			receivePriceRate.setReadOnly(true);
			insuredPriceRate.setReadOnly(true);
			exChargeRebate.setReadOnly(true);
			priceVersionDate.setReadOnly(true);
			exPriceVersionDate.setReadOnly(true);
		}
		/**
		 * @description :如果是修改运费折扣就 让运费折扣字段可以修改,其他字段都不能修改
		 * @date 2013-3-13
		 * @author 潘光均
		 */
		if (me.status==='ALTERRATE' && preferentialType=='MONTH_SEND') {
			chargeRebate.setReadOnly(false);
			agentgathRate.setReadOnly(true);
			insuredPriceRate.setReadOnly(true);
			receivePriceRate.setReadOnly(true);
		}
		if(me.status==='ALTERRATE' && exPreferentialType=='MONTH_REBATE'){
			exChargeRebate.setReadOnly(false);
			exChargeRebate.setMinValue(0.001);
		}
		if(me.status ==='priceVersionDate' && payWay === 'MONTH_END'){
			priceVersionDate.setReadOnly(false);
		}
		if(me.status ==='priceVersionDate' && exPayWay === 'MONTH_END'){
			exPriceVersionDate.setReadOnly(false);
		}
	},
	//提交前校验 数据是否符合业务规则
	validateCommitDate:function(){
		var me = this;
		var success = true;
		var message = '';
		var form = me.afterUpdateContract.getForm();
		var contract = getContractInfoRecord(me.contractView);
		if(!form.isValid()){
			return {success:false,message:'请检查数据是否填写完整并填写正确！'};
		}
		//申请事由
		var application = me.applyReasonInfo.getForm().findField('application');
		if(!application.isValid()){
			return {success:false,message:'请检查数据是否填写完整并填写正确！'};
		}
		/**
		 * @description：增加合同修改运费折扣业务校验规则
		 * @date：2013-3-13
		 * @author 潘光均
		 */
		if (me.status==='ALTERRATE') {
			if (form.findField('preferentialType').getValue()!='MONTH_SEND'
				&& form.findField('exPreferentialType').getValue()!='MONTH_REBATE') {
					message = '合同不是月发越送合同,您不可以修改!';
					success = false
					return {success:success,message:message};
			}
			if(Ext.isEmpty(form.findField('chargeRebate').getValue())&&!Ext.isEmpty(form.findField('exChargeRebate').getValue())){
				if(form.findField('exPreferentialType').getValue()=='MONTH_REBATE'&&
						form.findField('exChargeRebate').getValue()<=0||form.findField('exChargeRebate').getValue()>1){
						message = '月发越折优惠的运费折扣必须在(0,1]之间,请重新修改!';
						success = false
						return {success:success,message:message};
					}
			}else if(Ext.isEmpty(form.findField('exChargeRebate').getValue())&&!Ext.isEmpty(form.findField('chargeRebate').getValue())){
				
				if (form.findField('preferentialType').getValue()=='MONTH_SEND'&&
						form.findField('chargeRebate').getValue()<=0||form.findField('chargeRebate').getValue()>1) {
						message = '月发越送优惠的运费折扣必须在(0,1]之间,请重新修改!';
						success = false
						return {success:success,message:message};
					}
			}else if(!Ext.isEmpty(form.findField('chargeRebate').getValue())&&!Ext.isEmpty(form.findField('exChargeRebate').getValue())){
				if (form.findField('preferentialType').getValue()=='MONTH_SEND'&&
						form.findField('chargeRebate').getValue()<=0||form.findField('chargeRebate').getValue()>1) {
						message = '月发越送优惠的运费折扣必须在(0,1]之间,请重新修改!';
						success = false
						return {success:success,message:message};
					}
					if(form.findField('exPreferentialType').getValue()=='MONTH_REBATE'&&
						form.findField('exChargeRebate').getValue()<=0||form.findField('exChargeRebate').getValue()>1){
						message = '月发越折优惠的运费折扣必须在(0,1]之间,请重新修改!';
						success = false
						return {success:success,message:message};
					}
			}
				
		
		}else if('dunningDept' == me.status) {
			var newIfForeignGoods = Ext.getCmp('ifForeignGoods_id').getValue(); 
			var newDunningDeptCode = Ext.getCmp('dunningDeptCode_id').getValue();
			var oldIfForeignGoods = me.contractView.contract.ifForeignGoods;
			var oldDunningDeptCode = me.contractView.contract.dunningDeptCode;
			if(newIfForeignGoods == oldIfForeignGoods && newDunningDeptCode == oldDunningDeptCode) {
				message = '请修改后提交！';
				success = false
				return {success:success,message:message};
			}
			if(newIfForeignGoods == true && Ext.isEmpty(newDunningDeptCode)){
				message = '请选择催款部门！';
				success = false
				return {success:success,message:message};
			}
		}else if('priceVersionDate' == me.status){
			var newPriceVersionDate = Ext.getCmp('priceVersionDate_UpdateId').getValue(); 
			var oldPriceVersionDate = me.contractView.contract.priceVersionDate;
			var date = new Date(oldPriceVersionDate);
			var newExPriceVersionDate = Ext.getCmp('exPriceVersionDate_UpdateId').getValue(); 
			var oldExPriceVersionDate = me.contractView.contract.exPriceVersionDate;
			var exDate = new Date(oldExPriceVersionDate);
			if(Ext.isEmpty(newPriceVersionDate) && contract.get('payWay')=='MONTH_END'){
				message = '月结零担合同价格版本时间不能为空！';
				success = false;
				return {success:success,message:message};
			}
			if(Ext.isEmpty(newExPriceVersionDate) && contract.get('exPayWay')=='MONTH_END'){
				message = '月结快递合同价格版本时间不能为空！';
				success = false;
				return {success:success,message:message};
			}
			if(contract.get('payWay')=='MONTH_END'&&
				contract.get('exPayWay')=='MONTH_END'&&
				newPriceVersionDate.getFullYear() == date.getFullYear() &&
					newPriceVersionDate.getMonth()==date.getMonth()&&
					newPriceVersionDate.getDay() == date.getDay()&&
					newPriceVersionDate.getHours() == date.getHours() &&
					newPriceVersionDate.getMinutes() == date.getMinutes() &&
					newPriceVersionDate.getSeconds() == date.getSeconds() &&
					newExPriceVersionDate.getFullYear() == exDate.getFullYear() &&
					newExPriceVersionDate.getMonth()==exDate.getMonth()&&
					newExPriceVersionDate.getDay() == exDate.getDay()&&
					newExPriceVersionDate.getHours() == exDate.getHours() &&
					newExPriceVersionDate.getMinutes() == exDate.getMinutes() &&
					newExPriceVersionDate.getSeconds() == exDate.getSeconds()
					){
				message = '请修改价格版本时间后再提交！';
				success = false;
				return {success:success,message:message};
			}else if(contract.get('payWay')=='MONTH_END'&&
					contract.get('exPayWay')!='MONTH_END'&&
					newPriceVersionDate.getFullYear() == date.getFullYear() &&
						newPriceVersionDate.getMonth()==date.getMonth()&&
						newPriceVersionDate.getDay() == date.getDay()&&
						newPriceVersionDate.getHours() == date.getHours() &&
						newPriceVersionDate.getMinutes() == date.getMinutes() &&
						newPriceVersionDate.getSeconds() == date.getSeconds()){
					message = '请修改价格版本时间后再提交！';
					success = false;
					return {success:success,message:message};
			}else if(contract.get('payWay')!='MONTH_END'&&
					contract.get('exPayWay')=='MONTH_END'&&
					newExPriceVersionDate.getFullYear() == exDate.getFullYear() &&
					newExPriceVersionDate.getMonth()==exDate.getMonth()&&
					newExPriceVersionDate.getDay() == exDate.getDay()&&
					newExPriceVersionDate.getHours() == exDate.getHours() &&
					newExPriceVersionDate.getMinutes() == exDate.getMinutes() &&
					newExPriceVersionDate.getSeconds() == exDate.getSeconds()){
				message = '请修改价格版本时间后再提交！';
				success = false;
				return {success:success,message:message};
				}
		}else{
			//如果 结款方式和是否优惠同时分别为 "无"和"否 则不可保存合同
			if(('NOT_MONTH_END' == contract.get('payWay') 
					&& 'NOT_PREFERENTIAL' == form.findField('preferentialType').getValue()
					&&contract.get('exPayWay')== 'NOT_MONTH_END'
					&&form.findField('exPreferentialType').getValue()=='NOT_PREFERENTIAL')
						||('NOT_MONTH_END' == contract.get('payWay') 
								&& 'NOT_PREFERENTIAL' == form.findField('preferentialType').getValue()
								&&Ext.isEmpty(contract.get('exPayWay'))&&Ext.isEmpty(contract.get('exPreferentialType')))
						||('NOT_MONTH_END' == contract.get('exPayWay') 
								&& 'NOT_PREFERENTIAL' == form.findField('exPreferentialType').getValue()
								&&Ext.isEmpty(contract.get('payWay'))&&Ext.isEmpty(contract.get('preferentialType')))
						){
				message = i18n('i18n.ContractEditView.m_payWay_preference');
			   	message = '取消合同的全部条款，请走终止流程!';
			   	success = false
			   	return {success:success,message:message};
			}
			var arrountAmount = form.findField('arrearaMount');
			if(('MONTH_END' == me.beforeUpdateContract.getForm().findField('payWay').getValue() 
					||'MONTH_END' == me.beforeUpdateContract.getForm().findField('exPayWay').getValue())
					&& Ext.isEmpty(form.findField('arrearaMount').getValue())){
			   	message = '月结合同，欠款额度不能为空!';
				success = false
				return {success:success,message:message};
			}
			// 当修优惠类型是"价格折扣"时，优惠折扣详情必须填写
			if('PRICE_REBATE' == form.findField('preferentialType').getValue()
					&& isPreferentialAllNull(contract,form)){
				success = false;
				message = '优惠类型是"价格折扣"时，优惠信息折扣明细不能全部为空！';
				return {success:success,message:message};
			}
		    if('PRICE_REBATE' == form.findField('exPreferentialType').getValue() 
					&& isExPreferentialNull(form)){
				success = false;
				message = '优惠类型是"运费折扣"时，运费折扣信息不能为空！';
				return {success:success,message:message};
			}
			//当修改优惠折扣时，附件为必填项，否则为非必填项
//			if(contract.get('preferentialType') != form.findField('preferentialType').getValue()
//					||!me.isNotChangePreferential(contract,form)){
//				if(me.fileInfo.down('grid').store.getCount()<1 && 'MODIFY' != me.status){
//					success = false;
//					message = '当修改优惠折扣时，必须上传附件!';
//				}
//			}
			if (me.status==='ALTER') {
				//税务标记
				var invoiceType = me.afterUpdateContract.getForm().findField('invoiceType').getValue();
				me.fileInfo.down('grid').store.each(function(record){
					if ('INVOICE_TYPE_01'==invoiceType
							&&record.get('fileBusinessType') == 
								DpUtil.changeDictionaryCodeToDescrip('CONTRACTTAX',DataDictionary.CONTRACT_ANNEX)
								&&Ext.isEmpty(record.get('fileName'))) {
						message = record.get('fileBusinessType')+i18n('i18n.ContractEditView.m_fillecannotnull');
					   	success = false;
					}
					if(
						(record.get('fileBusinessType') == DpUtil.changeDictionaryCodeToDescrip('ELECTRONICCONTRACT',DataDictionary.CONTRACT_ANNEX) 
						||record.get('fileBusinessType') == DpUtil.changeDictionaryCodeToDescrip('CHANGEPROTOCOL',DataDictionary.CONTRACT_ANNEX)
						)&&Ext.isEmpty(record.get('fileName'))
					){
					   	message = record.get('fileBusinessType')+i18n('i18n.ContractEditView.m_fillecannotnull');
					   	success = false;
					}
				});
				
				
				
//				var fileData =  me.fileInfo.down('grid').store.data.items;
//				
//				if (Ext.isEmpty(fileData[0].data.fileName)&&Ext.isEmpty(fileData[1].data.fileName)) {
//					success = false;
//					message = '电子版合同和变更协议不能为空!';
//				}else{
//					for (var i = 0; i < fileData.length; i++) {
//						if (Ext.isEmpty(fileData[i].data.fileName)) {
//							success = false;
//							message =fileData[i].data.fileBusinessType+ '不能为空!';
//							break;
//						}
//					}
//				}
			}
			
			//零担和快递合同都没有选中修改、也未修改结算限额和月结天数
			if(!form.findField('ifLingDan').getValue()&&!form.findField('ifExpress').getValue()
					&&contract.get('arrearaMount') ==form.findField('arrearaMount').getValue()
					&& contract.get('debtDays') == form.findField('debtDays').getValue()
					&&form.findField('signCompany').getValue()== contract.get('signCompany')
					&&form.findField('invoiceType').getValue()== contract.get('invoiceType')){
					success = false;
					message = '未修改合同信息不允许保存合同！';
			}
			// 修改零担合同内容没有变动则不允许保存
			else if(contract.get('preferentialType') == form.findField('preferentialType').getValue()
					&& me.isNotChangePreferential('LT',contract,form)
					&& contract.get('arrearaMount') ==form.findField('arrearaMount').getValue()
					&& contract.get('debtDays') == form.findField('debtDays').getValue()){
					success = false;
					message = '未修改零担合同优惠折扣且未修改结算限额、合同月结天数时不允许保存合同！';
			}
			//修改快递合同内容没有变动则不允许保存
			else if(contract.get('exPreferentialType') == form.findField('exPreferentialType').getValue()
					&& me.isNotChangePreferential('EX',contract,form)
					&& contract.get('arrearaMount') ==form.findField('arrearaMount').getValue()
					&& contract.get('debtDays') == form.findField('debtDays').getValue()){
					success = false;
					message = '未修改快递合同优惠折扣且未修改结算限额、合同月结天数时不允许保存合同！';
			}
			//当修改优惠折扣时，优惠信息 不能为0和1
			var comparValue = ['1',1,'0',0];
			if(changePreferential4Show(form.findField('chargeRebate').getValue(),comparValue)){
				success = false;
				message = '<span style="color:red;">运费折扣</span>不能为0或1，无优惠请清空运费折扣！';
			}else if(changePreferential4Show(form.findField('agentgathRate').getValue(),comparValue)){
				success = false;
				message = '<span style="color:red;">代收费率折扣</span>不能为0或1，无优惠请清空代收费率折扣！';
			}else if(changePreferential4Show(form.findField('insuredPriceRate').getValue(),comparValue)){
				success = false;
				message = '<span style="color:red;">保价费率折扣</span>不能为0或1，无优惠请清空保价费率折扣！';
			}
		}
		return {success:success,message:message};
	},
	//判断 优惠折扣详情是否有修改 未修改则返回true，否则返回false
	isNotChangePreferential:function(type,contractRecord,form){
		var preferential = contractRecord.get('preferential');
		var exPreferential = contractRecord.get('exPreferential');
		var chargeRebate = form.findField('chargeRebate').getValue();//零担运费折扣
		var agentgathRate = form.findField('agentgathRate').getValue();//零担代收费率折扣
		var receivePriceRate = form.findField('receivePriceRate').getValue();//接货费
		var insuredPriceRate = form.findField('insuredPriceRate').getValue();//零担保价费率折扣
		var exChargeRebate = form.findField('exChargeRebate').getValue();//快递运费折扣
		var exAgentgathRate = form.findField('exAgentgathRate').getValue();//快递代收费率折扣
		var exInsuredPriceRate = form.findField('exInsuredPriceRate').getValue();//快递保价费率折扣
		if(type=='LT'&&form.findField('ifLingDan').getValue()){
			if(Ext.isEmpty(preferential)){
				return Ext.isEmpty(chargeRebate)
					&&Ext.isEmpty(agentgathRate)
					&&Ext.isEmpty(insuredPriceRate)
					&&Ext.isEmpty(receivePriceRate);
			}else{
				return preferential.chargeRebate == (Ext.isEmpty(chargeRebate)?1:chargeRebate)
						&& preferential.agentgathRate == (Ext.isEmpty(agentgathRate)?1:agentgathRate)
						&& preferential.insuredPriceRate == (Ext.isEmpty(insuredPriceRate)?1:insuredPriceRate)
						&& preferential.receivePriceRate == receivePriceRate;
			}
		}else if(type=='EX'&&form.findField('ifExpress').getValue()){
			if(Ext.isEmpty(exPreferential)){
				return Ext.isEmpty(exChargeRebate)
					&&Ext.isEmpty(exAgentgathRate)
					&&Ext.isEmpty(exInsuredPriceRate);
			}else{
				return exPreferential.chargeRebate == (Ext.isEmpty(exChargeRebate)?1:exChargeRebate)
						&& exPreferential.agentgathRate == (Ext.isEmpty(exAgentgathRate)?1:exAgentgathRate)
						&& exPreferential.insuredPriceRate == (Ext.isEmpty(exInsuredPriceRate)?1:exInsuredPriceRate);
			}
		}else{
			return false;
		}
	},
	// 获得合同附件Data
	getAttachData:function(){
		return getAttachData(this.contractView,this.status);
	}
});
/**
 * 合同上传附件Form--修改
 */
Ext.define('UploadAttachmentForm',{
	extend:'BasicFormPanel',
	flex:1,
	margins : '5 0 0 0',
	layout:{
		type : 'hbox'
	},
	defaults : {
		margins : '0 5 0 0'
	},
	status:null,
	contractData:null,
	fileInfo:null,
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		me.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'filefield',
			name:'file',
			id:'file_id',
			disabled:(me.status == 'VIEW' ||'MODIFY' == me.status||'ALTERRATE'===me.status),
			fieldLabel:i18n('i18n.ContractEditView.contractAttachment'),
			labelWidth:150,
			allowBlank: false,
			buttonText:i18n('i18n.ChangeContactAffiliatedRelationView.view'),
			flex:3
		},{
			xtype:'button',
			disabled:(me.status == 'VIEW' ||'MODIFY' == me.status||'ALTERRATE'===me.status),
			text:'上传',
			id:'updateFile_id',
			scope:me,
			handler:me.updateFile
		},{
			text:i18n('i18n.ChangeContactAffiliatedRelationView.sourceType'),
			name:'type',
			xtype:'hiddenfield',
			value:'contractAttDir'
		}];
	},
	updateFile:function(){
		var me = this;
		var form = me;
		var grid = me.fileInfo.down('grid');
		if(!form.getForm().isValid()){MessageUtil.showMessage(i18n('i18n.ContractEditView.m_uploadFile'));return;}
		form.submit({
                    url: '../common/fileUpload.action',
                    waitMsg: i18n('i18n.ChangeContactAffiliatedRelationView.uploading'),
                    success: function(form, response) {
                    	var result = response.result;
                    	if(result.success){
                    		var fileInfo = result.fileInfoList[0];
                    		me.getForm().findField('file').setValue();
                    		// 新签合同 选择 营业执照和电子档合同则 上传对应附件 否则新增一个空"类型"附件
                    		// 修改合同  新增一个空"类型"附件
                    		var fileInfoRecord = Ext.create('FileInfoModel',fileInfo);
                    		var selection = grid.getSelectionModel().getSelection();
                    		if(selection.length==1 && selection[0].get('fileBusinessType') == DpUtil.changeDictionaryCodeToDescrip('ELECTRONICCONTRACT',DataDictionary.CONTRACT_ANNEX)){
                    			fileInfoRecord.set('fileBusinessType',DpUtil.changeDictionaryCodeToDescrip('ELECTRONICCONTRACT',DataDictionary.CONTRACT_ANNEX));
                    			grid.store.remove(selection[0]);
                    			grid.store.insert(0,fileInfoRecord);
                    		}else if(selection.length==1 && selection[0].get('fileBusinessType') == DpUtil.changeDictionaryCodeToDescrip('BUSINESSLICENSE',DataDictionary.CONTRACT_ANNEX)){
                    			fileInfoRecord.set('fileBusinessType',DpUtil.changeDictionaryCodeToDescrip('BUSINESSLICENSE',DataDictionary.CONTRACT_ANNEX));
                    			grid.store.remove(selection[0]);
                    			grid.store.insert(1,fileInfoRecord);
                    		} else if(selection.length==1 && selection[0].get('fileBusinessType') == DpUtil.changeDictionaryCodeToDescrip('CONTRACTTAX',DataDictionary.CONTRACT_ANNEX)){
                    			fileInfoRecord.set('fileBusinessType',DpUtil.changeDictionaryCodeToDescrip('CONTRACTTAX',DataDictionary.CONTRACT_ANNEX));
                    			grid.store.remove(selection[0]);
                    			grid.store.insert(1,fileInfoRecord);
                    		}else if(selection.length==1 && selection[0].get('fileBusinessType') == DpUtil.changeDictionaryCodeToDescrip('CHANGEPROTOCOL',DataDictionary.CONTRACT_ANNEX)){
                    			fileInfoRecord.set('fileBusinessType',DpUtil.changeDictionaryCodeToDescrip('CHANGEPROTOCOL',DataDictionary.CONTRACT_ANNEX));
                    			grid.store.remove(selection[0]);
                    			grid.store.insert(1,fileInfoRecord);
                    		}
                    		else{
                    			grid.store.add(fileInfoRecord);
                    		}
                        	MessageUtil.showInfoMes(i18n('i18n.ChangeContactAffiliatedRelationView.file') + fileInfo.fileName + i18n('i18n.ChangeContactAffiliatedRelationView.uploadSeccess'));
						}else{
                       		MessageUtil.showErrorMes( result.message);
						}
                    },
                 failure:function(form, response){
                 	var result = response.result;
                 	if(!result.success){
                       	MessageUtil.showErrorMes(result.message);
					}
                 }
                });
	
	}
});
/**
 * 合同修改前信息Form
 */
Ext.define('BeforeUpdateContract',{
	extend:'BasicFormPanel',
	height:285,
	preferentialInfo:null,
	contractRecord:null,
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		me.callParent();
		if ((me.contractRecord.get('preferentialType')==='MONTH_SEND')
				&&(me.status==='ALTER' || me.status==='ALTERRATE'||me.status==='MODIFY')) {
			me.getForm().findField('chargeRebate').clearInvalid();
			me.getForm().findField('chargeRebate').setMinValue(0.001);
		}
		if((me.contractRecord.get('payWay')==='MONTH_END')
				&&me.status==='MODIFY'){
			me.getForm().findField('chargeRebate').clearInvalid();
			me.getForm().findField('chargeRebate').setMinValue(0.001);
		}
	},
	getItems:function(){
		var me = this;
		return [{
				xtype:'fieldset',
				title:i18n('i18n.ContractEditView.t_oldContractBasic'),
				layout : {
					type : 'table',
					columns : 3
				},
				defaultType : 'dptextfield',
				defaults : {
					labelWidth:100,
					width:240,
					readOnly:true
				},
				items:[{
					fieldLabel : i18n('i18n.ContractEditView.oldContractNum'),
					name : 'beforeContractNum'
				},{
					fieldLabel : i18n('i18n.ContractEditView.contractNum'),
					name : 'contractNum'
				},{
					fieldLabel : i18n('i18n.MemberCustEditView.custNo'),
					name : 'custNum'
				}, {
					fieldLabel : i18n('i18n.ContractEditView.customersFullName'),
					name : 'custCompany'
				}, {
					fieldLabel : i18n('i18n.ContractEditView.applicationForLessShallLimit'),
					name : 'arrearaMount'
				}, {
					fieldLabel : i18n('i18n.ContractManagerView.contractTaxSignCompany'),
					name:'signCompany'
				},{
					fieldLabel : i18n('i18n.ContractManagerView.contractTaxInvoiceType'),
					name : 'invoiceType',
					xtype : 'dpcombo',
					colspan:3,
					forceSelection:true,
					store:me.contractData.getInvoiceTypeStore(),
					displayField:'codeDesc',
					valueField:'code',
					queryMode:'local'
					
				},{
					 xtype: 'displayfield',
			         colspan:3,
			         fieldLabel: '<span style="font-weight:900;">零担合同</span>'
		        },{	
					fieldLabel : i18n('i18n.ContractEditView.preferentialType'),
					name : 'preferentialType',
					xtype : 'dpcombo',
					forceSelection:true,
					store:me.contractData.getPrivilegeTypeStore(),
					displayField:'codeDesc',
					valueField:'code',
					queryMode:'local'
				}, {
					fieldLabel : i18n('i18n.ContractEditView.resultsSectionBy'),
					name : 'payWay',
					xtype : 'dpcombo',
					forceSelection:true,
					store:me.contractData.getReckonWayStore(),
					displayField:'codeDesc',
					valueField:'code',
					queryMode:'local'
				}, {
					fieldLabel : i18n('i18n.ContractEditView.t_3monthamount'),
					colspan:2,
					name : 'arrAmount',
					readOnly:true
				},{
					xtype:'dpnumberfield',
					fieldLabel : i18n('i18n.ContractEditView.postageDiscounts'),
					minValue: 0.001,
					maxValue:1,
					maxLength:5,
					maxLengthText:i18n('i18n.contract.contractDebateMaxLength'),
					decimalPrecision:20,
					hideTrigger:true,
					name : 'chargeRebate'
				}, {
					xtype:'dpnumberfield',
					fieldLabel :i18n('i18n.ContractEditView.collectionOfRatesDiscount'),
					minValue: 0,
					maxValue:1,
					decimalPrecision:20,
					maxLength:5,
					maxLengthText:i18n('i18n.contract.contractDebateMaxLength'),
					hideTrigger:true,
					name : 'agentgathRate'
				}, {
					xtype:'dpnumberfield',
					fieldLabel : i18n('i18n.ContractEditView.insuredRateDiscount'),
					minValue: 0,
					maxValue:1,
					decimalPrecision:20,
					maxLength:5,
					maxLengthText:i18n('i18n.contract.contractDebateMaxLength'),
					hideTrigger:true,
					name : 'insuredPriceRate'
				}, {
					xtype:'dpnumberfield',
					fieldLabel : i18n('i18n.ContractEditView.toAcceptTheGoodsFeeDiscounts'),
//					hidden:true,
					minValue: 0,
					maxValue:99999.999,
					decimalPrecision:20,
					regex:/^\d+$|^(\d+)?\.\d{0,3}$/,
					regexText:i18n('i18n.contract.contractDebateMaxLength'),
					colspan:3,
					hideTrigger:true,
					name : 'receivePriceRate'
				},{
		            xtype: 'displayfield',
		            colspan:3,
		            fieldLabel: '<span style="font-weight:900;">快递合同</span>'
		        },{
					fieldLabel:i18n('i18n.ContractEditView.preferentialType'),
					name:'exPreferentialType',
					xtype : 'dpcombo',
					forceSelection:true,
					store:me.contractData.getExPrivilegeTypeStore(),
					displayField:'codeDesc',
					valueField:'code',
					queryMode:'local',
					readOnly:true
				},{
					fieldLabel : i18n('i18n.ContractEditView.resultsSectionBy'),
					name : 'exPayWay',
					xtype : 'dpcombo',
					forceSelection:true,
					readOnly:true,
					store:me.contractData.getReckonWayStore(),
					displayField:'codeDesc',
					valueField:'code',
					queryMode:'local'
				},{
					fieldLabel : i18n('i18n.ContractEditView.t_3monthamount'),
					name : 'exArrAmount',
					readOnly:true,
					maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',60),
					maxLength:60
				},{
					xtype:'dpnumberfield',
					fieldLabel : i18n('i18n.ContractEditView.postageDiscounts'),
					minValue: 0.001,
					maxValue:0.999,
					maxText:i18n('i18n.contract.contractExDebateRange'),
					hideTrigger:true,
					decimalPrecision:20,
					maxLength:5,
					readOnly:true,
					maxLengthText:i18n('i18n.contract.contractDebateMaxLength'),
					name : 'exChargeRebate'
				}, {
					xtype:'dpnumberfield',
					fieldLabel :i18n('i18n.ContractEditView.excollectionOfRatesDiscount'),
					minValue: 0.5,
					minText:i18n('i18n.contract.excontractDebateRangeData'),
					maxValue:0.999,
					maxText:i18n('i18n.contract.excontractDebateRangeData'),
					maxLength:5,
					maxLengthText:i18n('i18n.contract.contractDebateMaxLength'),
					decimalPrecision:20,
					hideTrigger:true,
					readOnly:true,
					name : 'exAgentgathRate'
				}, {
					xtype:'dpnumberfield',
					fieldLabel : i18n('i18n.ContractEditView.insuredRateDiscount'),
					minValue: 0.25,
					minText:i18n('i18n.contract.excontractInsuredRangeData'),
					maxValue:0.999,
					maxText:i18n('i18n.contract.excontractInsuredRangeData'),
					maxLength:5,
					maxLengthText:i18n('i18n.contract.contractDebateMaxLength'),
					decimalPrecision:20,
					hideTrigger:true,
					readOnly:true,
					name : 'exInsuredPriceRate'
				}, {
					xtype:'dpnumberfield',
					fieldLabel : i18n('i18n.ContractEditView.deliveryFeeDiscount'),
					hidden:true,
					minValue: 0,
					maxValue:1,
					decimalPrecision:3,
					hideTrigger:true,
					name : 'deliveryPriceRate'
				}]
			}];
	}
});
/**
 * 合同修改后信息Form
 */
Ext.define('AfterUpdateContract',{
	extend:'BasicFormPanel',
	height:250,
	contractView:null,
	id:'AfterUpdateContract_id',
	preferentialInfo:null,										//优惠信息record
	contractRecord:null,										//合同信息record
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		me.callParent();
		if ((me.contractRecord.get('preferentialType')==='MONTH_SEND')
				&&(me.status==='ALTER' || me.status==='ALTERRATE')) {
			me.getForm().findField('chargeRebate').setMinValue(0.001);
			me.getForm().findField('chargeRebate').setMaxValue(0.999);
		}
		if((me.contractRecord.get('payWay')==='MONTH_END')
				&&me.status==='MODIFY'){
			me.getForm().findField('chargeRebate').setMinValue(0.001);
			me.getForm().findField('chargeRebate').setMaxValue(0.999);
		}
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'fieldset',
			title:i18n('i18n.ContractEditView.t_contractInfo_update'),
			items:[{
				xtype : 'fieldcontainer',
				layout:'column',
				layout : {
					type : 'table',
					columns : 3
				},
				defaultType : 'dpnumberfield',
				defaults : {
					labelWidth:100,
					width:240
				},
				items:[{
					xtype:'dptextfield',
					fieldLabel : i18n('i18n.ContractEditView.t_arrearaMount_update'),
					name : 'arrearaMount',
					id:'t_arrearaMount_update_id',
					regex : /^\d{1,}$/,
					readOnly:(me.status==='ALTER'?false:true),
					regexText:i18n('i18n.ContractEditView.m_c_max10'),
		        	maxLength:10,
					maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',10)
				},{
					xtype:'dpnumberfield',
					fieldLabel:'合同月结天数',
					id:'modifyDebtDays_id',
					name:'debtDays',
					minValue: 0,
					maxLength:10,
					allowBlank:false,
					decimalPrecision:0,
					readOnly:(me.status==='MODIFY'?false:true),
					hideTrigger:true,
					mouseWheelEnabled: false
				},{
					id:'signCompany_id',
					xtype:'queryCombox',
					fieldLabel:i18n('i18n.ContractManagerView.contractTaxSignCompany*'),
					name:'signCompany',
					readOnly:(me.status==='ALTER'?false:true),
					allowBlank:false,
					displayField:'signCompany',  //控件显示的属性值
					valueField:'signCompany',      //提交的属性值
					store:Ext.create('SignCompanyListStore', {
								pageSize:5,
						        listeners:{
						        	beforeload:function(store, operation,eOpts){
						        		Ext.apply(operation,{
												params : {'signCompany':Ext.getCmp('signCompany_id').getRawValue()}
											}
										)
						        	}
							
						        }
					    	}),
					queryParam :'signCompany',
					queryMode:'remote',
					forceSelection:true,
					pageSize:5,
					minChars:0,
					listConfig: {
						loadMask:false,
				     	minWidth :300   //下拉框显示宽度
					},
					signCompany:'null',
					enableKeyEvents:true,
					listeners:{
						change:function(field, newValue) {if (Ext.isEmpty(newValue)) {field.setValue(null);}}
					}
				},{
					fieldLabel : i18n('i18n.ContractManagerView.contractTaxInvoiceType*'),
					name : 'invoiceType',
					id:'invoiceType_id',
					xtype : 'dpcombo',
					forceSelection:true,
					readOnly:(me.status==='ALTER'?false:true),
					allowBlank:false,
					store:me.contractData.getInvoiceTypeStore(),
					displayField:'codeDesc',
					valueField:'code',
					queryMode:'local'
				},{
					id:'ifForeignGoods_id',
					fieldLabel:'是否异地调货',
					name:'ifForeignGoods',
					xtype:'dpcombobox',
					queryMode:'local',
					hidden:me.status=='dunningDept'?false:true,
		            forceSelection:true,
					displayField:'name',
					valueField:'value',
					store:me.contractData.getBooleanStore(),
					allowBlank:false,
					emptyText:i18n('i18n.MemberCustEditView.isOrNot'),
					listeners:{
						select:function(field,record){
							var temp = Ext.getCmp('ifForeignGoods_id').getValue();
							Ext.getCmp('saveButton_id').setDisabled(false);
							if(temp == true){
								Ext.getCmp('dunningDeptCode_id').setDisabled(false);
							}else{
								Ext.getCmp('dunningDeptCode_id').setDisabled(true);
								Ext.getCmp('dunningDeptCode_id').setRawValue(null);
							}
						}
					}
				},{
					id:'dunningDeptCode_id',
					xtype:'queryCombox',
					fieldLabel:'催款部门',
					colspan:3,
					name:'dunningDeptCode',
					displayField:'deptName',  //控件显示的属性值
					valueField:'deptCode',      //提交的属性值
					hidden:me.status=='dunningDept'?false:true,
					store:Ext.create('DunningDeptListStore', {
								pageSize:10,
						        listeners:{
						        	beforeload:function(store, operation,eOpts){
						        		Ext.apply(operation,{
												params : {'deptName':Ext.getCmp('dunningDeptCode_id').getRawValue()}
											}
										)
						        	}
							
						        }
					    	}),
					queryParam :'deptName',
					queryMode:'remote',
					forceSelection:true,
					pageSize:10,
					minChars:0,
					listConfig: {
						loadMask:false,
				     	minWidth :300   //下拉框显示宽度
					},
					deptName:'null',
					enableKeyEvents:true,
					listeners:{
						change:function(field, newValue) {if (Ext.isEmpty(newValue)) {field.setValue(null);}}
					}
				},{
		            xtype: 'fieldcontainer',
		            colspan:3,
		            fieldLabel: '<span style="font-weight:900;">零担合同</span>',
		            width:720,
		            layout:'column',
		            defaultType: 'checkboxfield',
		            items: [
		                {
		                    name      : 'ifLingDan',
		                    inputValue: 'LINGDAN',
		                    id        : 'iflingdan_id',
		                    listeners:{
		                    	change:function(v){
		                    		var afterUpdateContractForm = me.getForm();
		                    		me.dealLingContract(afterUpdateContractForm,v);
		                    	}
		                    }
		                },{
		                	xtype:'displayfield',
		                	value:'<span style="color:green">&nbsp;&nbsp;&nbsp;&nbsp;温馨提示：只有勾选零担合同后才能填写零担合同相应合同信息</span>'
		                }
		            ]
		        },{
					xtype : 'dpcombo',
					fieldLabel : i18n('i18n.ContractEditView.t_iddiscount_update'),
					name : 'preferentialType',
					id:'preferentialType_updateId',
					forceSelection:true,
					readOnly:true,
					store:me.contractData.getPrivilegeTypeStore(),
					displayField:'codeDesc',
					valueField:'code',
					queryMode:'local',
					readOnly:me.status==='ALTERRATE',
					listeners:{
						select:function(field,record){
							preferentialTypeSlectEvent(field,record[0],'ALTER',me,me.contractRecord);
							preSelectEvent(field,me.contractRecord,me);
							if (field.getValue()==='MONTH_SEND'&&'MONTH_SEND'===me.contractRecord.get('preferentialType')) {
								if (!Ext.isEmpty(me.contractRecord)&&!Ext.isEmpty(me.contractRecord.get('preferential'))) {
									me.getForm().findField('chargeRebate').setMinValue(0.001);
									me.getForm().findField('chargeRebate').setMaxValue(0.999);
									me.getForm().findField('chargeRebate').setValue(dealPreData(me.contractRecord.get('preferential').chargeRebate));
								}
							}
						}
					}
				},{
					fieldLabel : i18n('i18n.ContractEditView.postageDiscounts'),
					minValue: 0.8,
					maxValue:0.999,
					decimalPrecision:20,
					maxLength:5,
					maxLengthText:i18n('i18n.contract.contractDebateMaxLength'),
					hideTrigger:true,
					readOnly:true,
					name : 'chargeRebate'
				}, {
					fieldLabel :i18n('i18n.ContractEditView.collectionOfRatesDiscount'),
					minValue: 0.001,
					minText:i18n('i18n.ContractEditView.m_input_scope'),
					maxValue:0.999,
					maxText:i18n('i18n.ContractEditView.m_input_scope'),
					decimalPrecision:20,
					maxLength:5,
					maxLengthText:i18n('i18n.contract.contractDebateMaxLength'),
					readOnly:true,
					hideTrigger:true,
					name : 'agentgathRate'
				}, {
					fieldLabel : i18n('i18n.ContractEditView.insuredRateDiscount'),
					minValue: 0.001,
					minText:i18n('i18n.ContractEditView.m_input_scope'),
					maxValue:0.999,
					maxText:i18n('i18n.ContractEditView.m_input_scope'),
					decimalPrecision:20,
					maxLength:5,
					maxLengthText:i18n('i18n.contract.contractDebateMaxLength'),
					readOnly:true,
					hideTrigger:true,
					name : 'insuredPriceRate'
				},  {
					fieldLabel : i18n('i18n.ContractEditView.toAcceptTheGoodsFeeDiscounts'),
					minValue: 0,
					minText:i18n('i18n.contract.contractReceivePriceRangeData'),
					maxValue:99999.999,
					maxText:i18n('i18n.contract.contractReceivePriceRangeData'),
					decimalPrecision:20,
					regex:/^\d+$|^(\d+)?\.\d{0,3}$/,
					regexText:i18n('i18n.contract.contractDebateMaxLength'),
					readOnly:true,
					hideTrigger:true,
					name : 'receivePriceRate'
				}, {
					fieldLabel : '价格版本',
					id:'priceVersionDate_UpdateId',
					name : 'priceVersionDate',
					xtype : 'datetimefield',
					allowBlank:true,
					colspan:2,
					invalidText :i18n('i18n.contract.Contract_PriceVersionDate_invlidText'),
					readOnly:(me.status==='priceVersionDate'?false:true),
					cls:(me.status==='priceVersionDate'?null:'readonly'),
					format:'Y-m-d H:i:s'
				},{
		            xtype: 'fieldcontainer',
		            colspan:3,
		            fieldLabel: '<span style="font-weight:900;">快递合同</span>',
		            width:720,
		            layout:'column',
		            defaultType: 'checkboxfield',
		            items: [
		                {
		                    name      : 'ifExpress',
		                    inputValue: 'EXPRESS',
		                    id        : 'ifExpress_id',
		                    listeners:{
		                    	change:function(v){
		                    		var afterUpdateContractForm = me.getForm();
		                    		me.dealExpressContract(afterUpdateContractForm,v);
		                    	}
		                    }
		                },{
		                	xtype:'displayfield',
		                	value:'<span style="color:green">&nbsp;&nbsp;&nbsp;&nbsp;温馨提示：只有勾选快递合同后才能填写快递合同相应合同信息</span>'
		                }
		            ]
		        },{
					xtype : 'dpcombo',
					fieldLabel : i18n('i18n.ContractEditView.t_iddiscount_update'),
					name : 'exPreferentialType',
					id:'exPreferentialType_updateId',
					forceSelection:true,
					store:me.contractData.getExPrivilegeTypeStore(),
					displayField:'codeDesc',
					readOnly:true,
					valueField:'code',
					queryMode:'local',
					readOnly:me.status==='ALTERRATE',
					listeners:{
						select:function(field,record){
							if(field.getValue()=='PRICE_REBATE'){
								me.getForm().findField('exChargeRebate').setReadOnly(false);
							}else{
								me.getForm().findField('exChargeRebate').setReadOnly(true);
							}
							if (field.getValue()==='MONTH_REBATE'&&'MONTH_REBATE'===me.contractRecord.get('exPreferentialType')) {
								if (!Ext.isEmpty(me.contractRecord)&&!Ext.isEmpty(me.contractRecord.get('exPreferential'))) {
									me.getForm().findField('exChargeRebate').setMinValue(0.001);
									me.getForm().findField('exChargeRebate').setMaxValue(0.999);
									me.getForm().findField('exChargeRebate').setValue(dealPreData(me.contractRecord.get('exPreferential').chargeRebate));
								}
							}else{
								me.getForm().findField('exChargeRebate').reset();
							}
						}
					}
				},{
					fieldLabel : i18n('i18n.ContractEditView.postageDiscounts'),
					minValue: 0.001,
					maxValue:0.999,
					decimalPrecision:20,
					maxLength:5,
					readOnly:true,
					maxLengthText:i18n('i18n.contract.contractDebateMaxLength'),
					hideTrigger:true,
					name : 'exChargeRebate'
				}, {
					fieldLabel :i18n('i18n.ContractEditView.excollectionOfRatesDiscount'),
					minValue: 0.5,
					minText:i18n('i18n.contract.excontractDebateRangeData'),
					maxValue:0.999,
					maxText:i18n('i18n.contract.excontractDebateRangeData'),
					decimalPrecision:20,
					maxLength:5,
					readOnly:true,
					maxLengthText:i18n('i18n.contract.contractDebateMaxLength'),
					hideTrigger:true,
					name : 'exAgentgathRate'
				}, {
					fieldLabel : i18n('i18n.ContractEditView.insuredRateDiscount'),
					minValue: 0.25,
					minText:i18n('i18n.contract.excontractInsuredRangeData'),
					maxValue:0.999,
					maxText:i18n('i18n.contract.excontractInsuredRangeData'),
					decimalPrecision:20,
					maxLength:5,
					readOnly:true,
					maxLengthText:i18n('i18n.contract.contractDebateMaxLength'),
					hideTrigger:true,
					name : 'exInsuredPriceRate'
				}, {
					fieldLabel : '价格版本',
					id:'exPriceVersionDate_UpdateId',
					name : 'exPriceVersionDate',
					xtype : 'datetimefield',
					allowBlank:true,
					cls:'readonly',
					colspan:2,
					invalidText :i18n('i18n.contract.Contract_PriceVersionDate_invlidText'),
					readOnly:(me.status==='priceVersionDate'?false:true),
					cls:(me.status==='priceVersionDate'?null:'readonly'),
					format:'Y-m-d H:i:s'
				},  
//				{
//					fieldLabel : i18n('i18n.ContractEditView.toAcceptTheGoodsFeeDiscounts'),
//					hidden:true,
//					value:1,
//					decimalPrecision:3,
//					hideTrigger:true,
//					name : 'receivePriceRate'
//				},
				{
					//可使用额度，前端不必要显示的
					xtype:'dpnumberfield',
					fieldLabel:'可使用额度',
					hidden:true,
					name:'useableArrearAmount',
					minValue:0,
					hideTrigger:true,
					mouseWheelEnabled: false
				}, {
					fieldLabel : i18n('i18n.ContractEditView.deliveryFeeDiscount'),
					hidden:true,
					value:1,
					decimalPrecision:3,
					hideTrigger:true,
					name : 'deliveryPriceRate'
				}]
			}]
		}];
	},
	//根据按钮是否零担合同而处理相应的可操作控件
	dealLingContract:function(afterUpdateContractForm,v){
		var beforeData = this.contractView.contract;
		if(v.getValue()){
			//将优惠类型置为可操作且不能为空
			afterUpdateContractForm.findField('preferentialType').setReadOnly(false);
			afterUpdateContractForm.findField('preferentialType').allowBlank=false;
			if(beforeData.preferentialType=='PRICE_REBATE'){
				afterUpdateContractForm.findField('agentgathRate').setReadOnly(false);
				afterUpdateContractForm.findField('chargeRebate').setReadOnly(false);
				afterUpdateContractForm.findField('insuredPriceRate').setReadOnly(false);
				afterUpdateContractForm.findField('receivePriceRate').setReadOnly(false);
			}else if(beforeData.preferentialType=='MONTH_SEND'&&beforeData.payWay=='MONTH_END'){
				afterUpdateContractForm.findField('insuredPriceRate').setReadOnly(false);
				afterUpdateContractForm.findField('agentgathRate').setReadOnly(false);
				afterUpdateContractForm.findField('receivePriceRate').setReadOnly(false);
			}
		}else{
			//把控件设为只读
			afterUpdateContractForm.findField('preferentialType').setReadOnly(true);
			afterUpdateContractForm.findField('agentgathRate').setReadOnly(true);
			afterUpdateContractForm.findField('chargeRebate').setReadOnly(true);
			afterUpdateContractForm.findField('insuredPriceRate').setReadOnly(true);
			afterUpdateContractForm.findField('receivePriceRate').setReadOnly(true);
			//将结款方式、优惠类型置为可以为空
			afterUpdateContractForm.findField('preferentialType').allowBlank=true;
			//把数据还原
			afterUpdateContractForm.findField('preferentialType').setValue(beforeData.preferentialType);
			if(!Ext.isEmpty(beforeData.preferential)){
				afterUpdateContractForm.findField('agentgathRate').setValue(dealPreData(beforeData.preferential.agentgathRate));
				afterUpdateContractForm.findField('chargeRebate').setValue(dealPreData(beforeData.preferential.chargeRebate));
				afterUpdateContractForm.findField('insuredPriceRate').setValue(dealPreData(beforeData.preferential.insuredPriceRate));
				afterUpdateContractForm.findField('receivePriceRate').setValue(beforeData.preferential.receivePriceRate);
			}else{
				afterUpdateContractForm.findField('agentgathRate').setValue();
				afterUpdateContractForm.findField('chargeRebate').setValue();
				afterUpdateContractForm.findField('insuredPriceRate').setValue();
				afterUpdateContractForm.findField('receivePriceRate').setValue();
			}
		}
	},
	//根据按钮是否快递合同而处理相应的可操作控件
	dealExpressContract:function(afterUpdateContractForm,v){
		var beforeData = this.contractView.contract;
		if(v.getValue()){
			afterUpdateContractForm.findField('exPreferentialType').setReadOnly(false);
			afterUpdateContractForm.findField('exPreferentialType').allowBlank=false;
			// 若是价格折扣，则将运费折扣设置为可编辑状态
			if('PRICE_REBATE' == beforeData.exPreferentialType){
				afterUpdateContractForm.findField('exChargeRebate').setReadOnly(false);
			}
			afterUpdateContractForm.findField('exAgentgathRate').setReadOnly(false);
			afterUpdateContractForm.findField('exInsuredPriceRate').setReadOnly(false);
		}else{
			//把控件设为只读
			afterUpdateContractForm.findField('exPreferentialType').setReadOnly(true);
			afterUpdateContractForm.findField('exChargeRebate').setReadOnly(true);
			afterUpdateContractForm.findField('exAgentgathRate').setReadOnly(true);
			afterUpdateContractForm.findField('exInsuredPriceRate').setReadOnly(true);
			//优惠类型置为可以为空
			afterUpdateContractForm.findField('exPreferentialType').allowBlank=true;
			//把数据还原
			afterUpdateContractForm.findField('exPreferentialType').setValue(beforeData.exPreferentialType);
			if(!Ext.isEmpty(beforeData.exPreferential)){
				afterUpdateContractForm.findField('exAgentgathRate').setValue(dealPreData(beforeData.exPreferential.agentgathRate));
				afterUpdateContractForm.findField('exChargeRebate').setValue(dealPreData(beforeData.exPreferential.chargeRebate));
				afterUpdateContractForm.findField('exInsuredPriceRate').setValue(dealPreData(beforeData.exPreferential.insuredPriceRate));
			}else{
				afterUpdateContractForm.findField('exAgentgathRate').setValue();
				afterUpdateContractForm.findField('exChargeRebate').setValue();
				afterUpdateContractForm.findField('exInsuredPriceRate').setValue();
			}
		}
	}
});
