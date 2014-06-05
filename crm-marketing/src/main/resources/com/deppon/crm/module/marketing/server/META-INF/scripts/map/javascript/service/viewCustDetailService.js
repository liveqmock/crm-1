/**
*查看客户详情
*/
var memberManagerData = new MemberManagerData();
var SCData = new ScatterCustSearchData();
var potentialCustSearchData = new PotentialCustSearchData();
function viewCustDetail(custId,custType){
	if(!clickToShowCustDetailInfoWindow){
//		return false;
	}
	else if(Ext.isEmpty(custId)||Ext.isEmpty(custType)){
		MessageUtil.showErrorMes('传入的客户类型或者客户ID为空')
//	}else if(custType == 'MEMBER'){
//		clickToShowCustDetailInfoWindow = false;
//		viewMembercust(custId,custType)
//	}else if(custType == 'PC_CUSTOMER'){
//		clickToShowCustDetailInfoWindow = false;
//		viewPCCust(custId,custType)
//	}else if(custType == 'SC_CUSTOMER'){
//		clickToShowCustDetailInfoWindow = false;
//		viewSCCust(custId,"SC_CUSTOMER")
//	}
//	setTimeout(function(){
//		clickToShowCustDetailInfoWindow = true;
//	},2000);
	}else {
		clickToShowCustDetailInfoWindow = false;
		CustviewUtil.openMemberWindow(custId);
	}
	setTimeout(function(){
		clickToShowCustDetailInfoWindow = true;
	},2000);
};
/**
 * 查看固定客户详情
 * @param custId
 * @param custType
 */
function viewMembercust(custId,custType){
	var searchByIdSuccess = function(response){
		var memberInfo = response.member;
		var custStatus = null;
		Ext.create('MemberCustEditWindow',{'member':memberInfo,
											'y':30,//距离页面最顶端 像素距离
					   					   'viewStatus':'view',
					   					   'custStatus':custStatus}).show();
	};
	var searchByIdFail = function(response){
		MessageUtil.showErrorMes( response.message);
	};
	var params = {'searchCustCondition':{'memberId':custId}};
	memberManagerData.acquireMemberById(params,searchByIdSuccess,searchByIdFail);
};
/**
 * 查看散客详情
 * @param custId
 * @param custType
 */
function viewSCCust(custId,custType){
	var searchByIdSuccess = function(response){
		var scatterCustRecord = Ext.create('ScatterCustModel',response.scatterCust);
		var scatterDetail = Ext.create('ScatterCustDetailWindow',{
			'viewStatus':'view',
			'scatterCustRecord':scatterCustRecord
			});
		scatterDetail.show();
		//省市区有change事件  地址特殊处理
		scatterDetail.down('basicformpanel').getForm().findField('address').setValue(scatterCustRecord.get('address'));
		scatterDetail.scatterCustAccountPanel.down('grid').store.loadData(scatterCustRecord.get('accounts'));
	};
	var searchByIdFail = function(response){
		MessageUtil.showErrorMes( response.message);
	};
	var params = {'scatterCustId':custId};
	SCData.serachScatterById(params,searchByIdSuccess,searchByIdFail);
};
/**
 * 查看潜客详情
 * @param custId
 * @param custType
 */
function viewPCCust(custId,custType){
	var searchByIdSuccess = function(response){
		var potentialCust = Ext.create('PotentialCustModel',response.potentialCust);
		var win = Ext.create('PotentialCustDetailWindow', {
			potentialCustRecord : potentialCust,
			viewStatus:'view'
		});
		win.show();
	};
	var searchByIdFail = function(response){
		MessageUtil.showErrorMes( response.message);
	};
	var params = {'potentialCustId':custId};
	potentialCustSearchData.serachPotentialById(params,searchByIdSuccess,searchByIdFail);
};