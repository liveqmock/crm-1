/**
 *校验手机号或联系人加固话是否重复
*/
function validateLinkWay(mobil,contactName,tel){
	if(!Ext.isEmpty(mobil)){
		return validateMobil(mobil);
	}else if(!Ext.isEmpty(contactName)&&!Ext.isEmpty(tel)){
		return validateTel(contactName,tel);
	}
};
//校验手机号是否重复
function validateMobil(mobile,deptId,linkManName){
	var message = '';//校验结果 提示语
	var success = true;//检验结果 true为通过，false为不通过
	var validateSuccessFn = function(result){
		message = result.message;
	};
	var validateFailFn = function(result){
		message = result.message;
		success = false;
		if(DpUtil.isEmpty(message)){message = i18n('i18n.MemberCustEditView.anExceptionBackgroundAppearsWar');}
		MessageUtil.showMessage(message);
	};
	var params = {};
	params.mobile = mobile;
	params.deptId = deptId;
	params.linkManName = linkManName;
	scatterCustControl.validateSCMobileOrTelIsExist(params,validateSuccessFn,validateFailFn);
};
//校验固话是否重复
function validateTel(linkManPhone,linkManName,deptId){
	var message = '';//校验结果 提示语
	var success = true;//检验结果 true为通过，false为不通过
	var validateSuccessFn = function(result){
		message = result.message;
	};
	var validateFailFn = function(result){
		message = result.message;
		success = false;
		if(DpUtil.isEmpty(message)){message = i18n('i18n.MemberCustEditView.anExceptionBackgroundAppearsWar');}
		MessageUtil.showMessage(message);
	};
	var params = {};
	params.phone = linkManPhone;
	params.linkManName = linkManName;
	params.deptId = deptId;
	
	scatterCustControl.validateSCMobileOrTelIsExist(params,validateSuccessFn,validateFailFn);
};
//校验身份证号格式 是否合法
function validaeIdCardInvalid(idCard){
	var message = '';//校验结果 提示语
	var success = true;//检验结果 true为通过，false为不通过
	var validateSuccessFn = function(result){
		message = result.message;
	};
	var validateFailFn = function(result){
		message = result.message;
		success = false;
		if(DpUtil.isEmpty(message)){message = i18n('i18n.scatter.showCardIdIsNotIlleageMessage');}
		MessageUtil.showMessage(message);
	};
	var params = {};
	params.idCard = idCard;
	scatterCustControl.validateTaxOrIdCardIsLegal(params,validateSuccessFn,validateFailFn);
};
//校验税务登记号 是否合法
function validateTaxInvalid(taxNum){
	var message = '';//校验结果 提示语
	var success = true;//检验结果 true为通过，false为不通过
	var validateSuccessFn = function(result){
		message = result.message;
		return {success:success,message:message};
	};
	var validateFailFn = function(result){
		message = result.message;
		success = false;
		if(DpUtil.isEmpty(message)){message = i18n('i18n.membereffect.TaxNumber_ERROR');}
		MessageUtil.showMessage(message);
//		return {success:success,message:message};
	};
	var params = {};
	params.taxregistNo = taxNum;
	scatterCustControl.validateTaxOrIdCardIsLegal(params,validateSuccessFn,validateFailFn);
};
/**
*获得初始值散客账户
*新增账户时如果散客联系人姓名和手机或固话存在则初始化 协议联系人信息
*/
function scatterAccountAdd(basicScatterCustForm,formParent){
	var scatterAccount = Ext.create('ScatterCustAccountModel');
	scatterAccount.set('financeLinkmanName',basicScatterCustForm.findField('linkManName').getValue());//财务联系人姓名
	scatterAccount.set('linkmanMobile',basicScatterCustForm.findField('linkManMobile').getValue());//联系人手机
	scatterAccount.set('linkmanPhone',basicScatterCustForm.findField('linkManPhone').getValue());//联系人固话
	scatterAccount.set('scatterId',formParent.scatterCustRecord.get('id'));//散客id
	return scatterAccount;
};
/**
 * 搜集散客修改的信息
 */
function collectAlterScatterCustData(alterScatterCustList,scatterCustRecord){
	// 处理 散客 城市 id问题
	scatterCustRecord.set('city',Ext.isEmpty(scatterCustRecord.get('city'))?'':scatterCustRecord.get('city').split('-')[1]);
	//会员基本信息的所有字段
	var fieldsArray = scatterCustRecord.fields.items;
	for(var i = 0; i < fieldsArray.length;i++){
		//字段名
		var fieldName = fieldsArray[i].name;
		if(scatterCustRecord.isModified(fieldName)){
			var basicApproveData = getEmptyApproveData(scatterCustRecord,'ScatterCustomer');
			basicApproveData.set('fieldName',fieldName);
			basicApproveData.set('newValue',scatterCustRecord.get(fieldName));
			alterScatterCustList.push(basicApproveData.data);
		}
	}
};
/**
 * 
 * 搜集账号所有修改的信息
 */
function collectAllAcountAlterData(alterAccountList,alterDelAccountList,alterAddAccountList){
	var store = scatterCustControl.getScatterCustAccountStore();
	//所有账号修改的record
	var updatedAccountRecords = store.getUpdatedRecords();
	//搜集账号修改时删除的数据
	var deleteAccountRecords = store.getRemovedRecords();
	//搜集新增的账号数据
	var addAccountRecords = store.getNewRecords();
	
	if(updatedAccountRecords.length > 0){
		var fieldsArray = updatedAccountRecords[0].fields.items;
		for(var j = 0; j < updatedAccountRecords.length; j++){
			for(var i = 0; i < fieldsArray.length;i++){
				//字段名
				var fieldName = fieldsArray[i].name;
				if(updatedAccountRecords[j].isModified(fieldName)){
					var basicApproveData = getEmptyApproveData(updatedAccountRecords[j],'ScatterAccount');
					basicApproveData.set('fieldName',fieldName);
					basicApproveData.set('newValue',updatedAccountRecords[j].get(fieldName));
					alterAccountList.push(basicApproveData.data);
				}
			}
		}
	}
	for(var i = 0; i < deleteAccountRecords.length; i++){
		alterDelAccountList.push(getEmptyApproveData(deleteAccountRecords[i],'ScatterAccount').data);
	}
	for(var i = 0; i < addAccountRecords.length; i++){
		alterAddAccountList.push(addAccountRecords[i].data);
	}
};
/**
 * 获取空的修改的approveData
 */
function getEmptyApproveData(record,className){
	var basicApproveData = Ext.create('ApproveDataModel');
	basicApproveData.set('className',className);
	basicApproveData.set('classId',record.get('id'));
	return basicApproveData;
};
/**
 * 判断散客的标签是否修改了
 * true 没有修改
 * false 修改了
 */
function judgeScLabelsWhetherAlter(oldCustLabel,custLabels) {
	//一个为空 肯定修改过
	if((Ext.isEmpty(oldCustLabel) && !Ext.isEmpty(custLabels)) || 
			(!Ext.isEmpty(oldCustLabel) && Ext.isEmpty(custLabels))){
		return false;
	}
	//长度不等 肯定修改了
	if(!Ext.isEmpty(oldCustLabel) && !Ext.isEmpty(custLabels)){
		if(!(oldCustLabel.length == custLabels.length)) {
			return false;
		}
	}
	if(!Ext.isEmpty(oldCustLabel) && !Ext.isEmpty(custLabels)){
		var old = null;
		var newLabel = null;
		var index = 0;
		for(var i=0;i<oldCustLabel.length;i++){
			old = oldCustLabel[i];
			for(var j=0;i<custLabels.length;j++){
				newLabel = custLabels[i];
				if(old.labelName == newLabel.labelName && old.deptId == newLabel.deptId){
					index = 1;
					break;
				} else{
					index = 0;
					continue;
				}
			}
			if(index == 0){
				break;
			}
		}
		return false;
	}
}