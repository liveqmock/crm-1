(function() {
	var MemberViewDataTest = "../scripts/custview/test/memberViewDataTest.js";
	if (CONFIG.get('TEST')) {
		document.write('<script type="text/javascript" class="MemberViewDataTest" src="'
						+ MemberViewDataTest + '"></script>');
	}
})();

MemberViewData = function() {};

initDeptAndUserInfo('1');/* 验证是否为香港地区  */

/**
 * 获取登记号的显示名称
 * 
 */
MemberViewData.prototype.registrationLableShow = function(){
	var tax = i18n('i18n.memberView.taxRegistrationNumber');/*内陆-税务登记号*/
	var business = i18n('i18n.memberView.businessRegistrationNumber');/*香港-商业登记号*/
	if(Ext.isEmpty(User.deptCityLocation)){
		return tax;
	}
	
	if(User.deptCityLocation=='1'){return business}
	
	return tax;
};

/**.
 * <p>
 * 通过客户编码获取订单信息<br/>
 * <p>
 * @param  params  
 * @param  successFn   
 * @param  failureFn   
 * @author 张斌
 * @时间 2012-4-25
 */
MemberViewData.prototype.searchOrderListByCustNum = function(){
	Ext.define('OrderInforGridStoreId',{
		pageSize : 10,
		extend:'Ext.data.Store',
		model:'OrderModel',
		autoLoad:false,
		proxy:{
			type:'ajax',
			url:'../custview/searchOrderListByCustNum.action',
			actionMethods:'POST',
			reader:{
				type:'json',
				root:'orderList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new OrderInforGridStoreId();
};
	
/**.
 * <p>
 * 通过客户编码获取维护信息<br/>
 * <p>
 * @param  params  
 * @param  successFn   
 * @param  failureFn   
 * @author 张斌
 * @时间 2012-4-26
 */
MemberViewData.prototype.searchIntegratedCustDevViewByCustNum = function(params,successFn,failureFn){
	var url = '../custview/searchIntegratedCustDevViewByCustNum.action';	
	DpUtil.requestJsonAjax(url,params,successFn,failureFn);
};
/**.
 * <p>
 * 通过客户编码合同信息<br/>
 * <p>
 * @param  params  
 * @param  successFn   
 * @param  failureFn   
 * @author 张斌
 * @时间 2012-4-26
 */
MemberViewData.prototype.searchContractViewByCustNum = function(params,successFn,failureFn){
	var url = '../custview/searchContractViewByCustNum.action';	
	DpUtil.requestJsonAjax(url,params,successFn,failureFn);
};
/**.
 * <p>
 * 通过客户编码财务信息<br/>
 * <p>
 * @param  params  
 * @param  successFn   
 * @param  failureFn   
 * @author 张斌
 * @时间 2012-4-26
 */
MemberViewData.prototype.searchAccountViewByCustNum = function(params,successFn,failureFn){
	var url = '../custview/searchAccountViewByCustNum.action';	
	DpUtil.requestJsonAjax(url,params,successFn,failureFn);
};
/**.
 * <p>
 * 通过客户编码联系人信息<br/>
 * <p>
 * @param  params  
 * @param  successFn   
 * @param  failureFn   
 * @author 张斌
 * @时间 2012-4-26
 */
MemberViewData.prototype.searchContactViewByCustNum = function(params,successFn,failureFn){
	var url = '../custview/searchContactViewByCustNum.action';	
	DpUtil.requestJsonAjax(url,params,successFn,failureFn);
};
/**.
 * <p>
 * 通过客户编码获取投诉理赔信息<br/>
 * <p>
 * @param  params  
 * @param  successFn   
 * @param  failureFn   
 * @author 张斌
 * @时间 2012-4-26
 */
MemberViewData.prototype.searchComplaintRecompenseViewByCustNum = function(params,successFn,failureFn){
	var url = '../custview/searchComplaintRecompenseViewByCustNum.action';	
	DpUtil.requestJsonAjax(url,params,successFn,failureFn);
};
/**.
 * <p>
 * 通过客户编码获取综合信息<br/>
 * <p>
 * @param  params  
 * @param  successFn   
 * @param  failureFn   
 * @author 王迪荟
 * @时间 2012-4-27
 */
MemberViewData.prototype.searchMemberIntegratedInfoViewByCustNum = function(params,successFn,failureFn){
	var url = '../custview/searchMemberIntegratedInfoViewByCustNum.action';	
	DpUtil.requestJsonAjax(url,params,successFn,failureFn);
};
/**.
 * <p>
 * 通过客户编码获取积分信息<br/>
 * <p>
 * @param  params  
 * @param  successFn   
 * @param  failureFn   
 * @author 王迪荟
 * @时间 2012-4-27
 */
MemberViewData.prototype.searchMemberPointsViewByCustNum = function(params,successFn,failureFn){
	var url = '../custview/searchMemberPointsViewByCustNum.action';	
	DpUtil.requestJsonAjax(url,params,successFn,failureFn);
};
/**.
 * <p>
 * 通过合同ID获取合同信息<br/>
 * <p>
 * @param  params  
 * @param  successFn   
 * @param  failureFn   
 * @author 张斌
 * @时间 2012-4-27
 */
MemberViewData.prototype.searchContractByContractId = function(params,successFn,failureFn){
	var url = '../custview/searchContractByContractId.action';	
	DpUtil.requestJsonAjax(url,params,successFn,failureFn);
};
/**.
 * <p>
 * 通过联系人ID获取联系人信息<br/>
 * <p>
 * @param  params  
 * @param  successFn   
 * @param  failureFn   
 * @author 张斌
 * @时间 2012-4-27
 */
MemberViewData.prototype.searchContactByContactId = function(params,successFn,failureFn){
	var url = '../custview/searchContactByContactId.action';	
	DpUtil.requestJsonAjax(url,params,successFn,failureFn);
};
/**.
 * <p>
 * 通过客户ID获取客户基本信息<br/>
 * <p>
 * @param  params  
 * @param  successFn   
 * @param  failureFn   
 * @author 张斌
 * @时间 2012-4-27
 */
MemberViewData.prototype.searchMemberViewByCustNum = function(params,successFn,failureFn){
	var url = '../custview/searchMemberViewByCustNum.action';	
	DpUtil.requestJsonAjax(url,params,successFn,failureFn);
};
/**.
 * <p>
 * 通过客户ID获取运营决策信息<br/>
 * <p>
 * @param  params  
 * @param  successFn   
 * @param  failureFn   
 * @author 张斌
 * @时间 2012-4-27
 */
MemberViewData.prototype.searchOperatingDecision = function(params,successFn,failureFn){
	var url = '../custview/searchOperatingDecision.action';	
	DpUtil.requestJsonAjax(url,params,successFn,failureFn);
};
/**.
 * <p>
 * 通过财务ID获取财务信息<br/>
 * <p>
 * @param  params  
 * @param  successFn   
 * @param  failureFn   
 * @author 张斌
 * @时间 2012-4-27
 */
MemberViewData.prototype.searchAccountViewById = function(params,successFn,failureFn){
	var url = '../custview/searchAccountViewById.action';	
	DpUtil.requestJsonAjax(url,params,successFn,failureFn);
};

//-------------------------------------------------------------------------------------------------------------------------
MemberViewData.prototype.printMemberView = function(){
	return url = '../custview/printMemberView.action';	
};


MemberViewData.prototype.findMemberInfoFast = function(params,successFn,failureFn){
	var url ='../custview/findMemberInfoFast.action';	
	DpUtil.requestJsonAjax(url,params,successFn,failureFn);
};
MemberViewData.prototype.searchMemberntegrateInfo = function(params,successFn,failureFn){
	var url ='../custview/searchMemberntegrateInfo.action';	
	DpUtil.requestJsonAjax(url,params,successFn,failureFn);
};


/**
 * 客户变更详细信息Store
 */
Ext.define('CustChangeHistoryDetStore',{
	extend:'Ext.data.Store',
	model:'CustChangeHistoryDetModel',
	pageSize:10,
	proxy:{
		type:'ajax',
		url:'../custview/searchApproveDataList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'approveDataList',
			totalProperty:'totalCount'
		}
	}
});

/**
 * 交叉映射Store
 * 修改为按外部系统ID查询，即传erpId
 */
Ext.define('crm.custview.CustCrossMappingStore',{
	extend:'Ext.data.Store',
	model:'Crm.custview.CrossMappingModel',
	proxy:{
		type:'ajax',
		url:'../custview/searchCMappingByErpId.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'crossMappingList',
			totalProperty:'totalCount'
		}
	}
});

/**
 * 查询运单信息
 */
MemberViewData.prototype.searchWaybillListByCustNum = function(){
	Ext.define('Crm.custview.WaybillInforGridStore',{
		pageSize : 20,
		extend:'Ext.data.Store',
		model:'Crm.custview.WaybillInforGridModel',
		autoLoad:false,
		proxy:{
			type:'ajax',
			url:'searchWaybillListByCustNum.action',
			actionMethods:'POST',
			reader:{
				type:'json',
				root:'waybillInfoList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new Crm.custview.WaybillInforGridStore();
}
/**
 * 营销活动Store
 */
Ext.define('Crm.custview.mkActivityStore',{
	extend:'Ext.data.Store',
	model:'Crm.custview.mkActivityModel',
	proxy:{
		type:'ajax',
		url:'searchWaybillListByCustNum.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'mkActivityList'
		}
	}
});

/**
 * 查询客户信用信息
 */
MemberViewData.prototype.searchCustCredit = function(params,successFn,failureFn){
	var url ='../custview/searchCustCredit.action';	
	DpUtil.requestJsonAjax(url,params,successFn,failureFn);
}

/**
 * 查询客户变更历史详细信息
 * @param {} params
 * @param {} successFn
 * @param {} failureFn
 */	
MemberViewData.prototype.searchApproveDataList = function(params,successFn,failureFn){
	var url = '../custview/searchApproveDataList.action';
	DpUtil.requestJsonAjax(url,params,successFn,failureFn);
}

/**
 * 流失预警Store
 */
Ext.define('Crm.custview.LoseWarningStore',{
	extend:'Ext.data.Store',
	model:'Crm.custview.LoseWarningModel',
	proxy:{
		type:'ajax',
		url:'../custview/searchWarnLostCustList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'warnLostCustList',
			totalProperty:'totalCount'
		}
	}
});
/**
 * 获取商机数据
 * @return {}
 */
MemberViewData.prototype.getBusinessOpportunityStore = function(){
	Ext.define('Crm.custview.BoStore',{
		pageSize : 20,
		extend:'Ext.data.Store',
		model:'Crm.custview.boModel',
		proxy:{
			type:'ajax',
			url:'searchBusinessOpportunityList.action',
			actionMethods:'POST',
			timeout:100000,
			reader:{
				type:'json',
				root:'businessOpportunityList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new Crm.custview.BoStore();
};
