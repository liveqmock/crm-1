/**.
 * <p>
 * 订单模块中要用到的DATA<br/>
 * <p>
 * @author 张斌
 * @时间 2012-3-8
 */

/**.
 * <p>
 * 判断是否导入测试数据<br/>
 * <p>
 * @author 张斌
 * @时间 2012-3-8
 */
(function() {
	var recompenseDataTest = "../scripts/recompense/test/RecompenseDataTestN.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + recompenseDataTest + '"></script>');
	}
})();

RecompenseDataN = function(){
};
/**.
 * <p>
 * 通过运单号或差错编号获取运单信息<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-3-30
 */
RecompenseDataN.prototype.getWaybillByNum = function(param,successFn,failureFn)
{
  var url = '../recompense/findWayBillByNumAndType.action';
  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 删除附件<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-5-15
 */
RecompenseDataN.prototype.deleteFileInfo = function(param,successFn,failureFn)
{
  var url = '../common/deleteFileInfo.action';
  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 查询事业部<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-5-15
 */
RecompenseDataN.prototype.queryAllBusiness = function(param,successFn,failureFn)
{
  var url = '../recompense/queryAllBusiness.action';
  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 查询当前登录用户所属事业部<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-5-15
 */
RecompenseDataN.prototype.initBus = function(param,successFn,failureFn)
{
  var url = '../recompense/initBus.action';
  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 根据条件查询客户历史理赔统计信息<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-5-11
 */
RecompenseDataN.prototype.searchHistroyRecompenseAmount = function(param,successFn,failureFn)
{
  var url = '../recompense/searchHistroyRecompenseAmount.action';
  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 根据条件查询客户历史理赔信息<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-5-11
 */
RecompenseDataN.prototype.searchHistroyRecompense = function(param,successFn,failureFn)
{
  var url = '../recompense/searchHistroyRecompense.action';
  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 通过客户ID获取客户信息<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-4-06
 */
RecompenseDataN.prototype.getCustInfo = function(param,successFn,failureFn)
{
  var url = '../recompense/findCustById.action';
  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 根据大区名称查询大区<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-4-06
 */
RecompenseDataN.prototype.searchAreas = function(param,successFn,failureFn)
{
  var url = '../recompense/searchAreas.action';
  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 工作流操作（那些只需要转一个ID就OK的）<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-4-17
 */
RecompenseDataN.prototype.performAction = function(param,successFn,failureFn)
{
  var url = '../recompense/performAction.action';
  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 初步处理保存/处理修改<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-4-18
 */
RecompenseDataN.prototype.saveRecompenseProcessInfo = function(param,successFn,failureFn)
{
  var url = '../recompense/saveRecompenseProcessInfo.action';
  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 理赔上报提交<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-4-06
 */
RecompenseDataN.prototype.createRecompense = function(param,successFn,failureFn)
{
  var url = '../recompense/createRecompense.action';
  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 通过在线理赔的ID获取理赔对象<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-4-06
 */
RecompenseDataN.prototype.handleOnlineApply = function(param,successFn,failureFn)
{
  var url = '../recompense/handleOnlineApply.action';
  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 修改理赔<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-4-06
 */
RecompenseDataN.prototype.updateRecompense = function(param,successFn,failureFn)
{
  var url = '../recompense/updateRecompense.action';
  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 金额确认<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-4-19
 */
RecompenseDataN.prototype.amountRecognized = function(param,successFn,failureFn)
{
  var url = '../recompense/amountRecognized.action';
  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 取消初步处理<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-4-19
 */
RecompenseDataN.prototype.cancelProcess = function(param,successFn,failureFn)
{
  var url = '../recompense/cancelProcess.action';
  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 根据ID查询该理赔所有相关系信息<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-4-14
 */
RecompenseDataN.prototype.searchRecompenseById = function(param,successFn,failureFn)
{
  var url = '../recompense/searchRecompenseById.action';
  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 根据ID查询该理赔工作流相关系信息<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-4-14
 */
RecompenseDataN.prototype.searchRecompenseWorkflowById = function(param,successFn,failureFn)
{
  var url = '../recompense/searchRecompenseWorkflowById.action';
  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 获取根据工号获取职员信息<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-4-11
 */
RecompenseDataN.prototype.searchEmployeeByCode = function(param,successFn,failureFn)
{
  var url = '../recompense/searchEmployeesByEmpCode.action';
  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 提交跟进信息<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-4-16
 */
RecompenseDataN.prototype.sendMessage = function(param,successFn,failureFn)
{
  var url = '../recompense/sendRecompenseMessage.action';
  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * combox获取测试用store。里面所有combox共用这个store<br/>
 * </p>
 * @returns states 一个data.store
 * @author  张斌
 * @时间    2012-03-27
 */
RecompenseDataN.prototype.getTestStore= function(){
	var states = Ext.create('Ext.data.Store', {
	    fields: ['code', 'codeDesc'],
	    data : [
	        {"code":"AL", "codeDesc":"Alabama"},
	        {"code":"AK", "codeDesc":"Alaska"},
	        {"code":"AZ", "codeDesc":"Arizona"}
	        //...
	    ]
	});
	return states;
};

/**.
 * <p>
 * 理赔查询store<br/>
 * </p>
 * @returns states 一个data.store
 * @author  潘光均
 * @时间    2012-03-27
 */
RecompenseDataN.prototype.getRecompenseSearchStore=function(){
	if(!Ext.isEmpty(Ext.data.StoreManager.lookup('recompenseSearchStore'))){
		return Ext.data.StoreManager.lookup('recompenseSearchStore');
	}
	Ext.define('RecompenseSearchStore',{
		pageSize : 10,
		extend:'Ext.data.Store',
		model:'RecompenseApp',
		autoLoad:true,
		storeId:'recompenseSearchStore',
		proxy:{
			type:'ajax',
			url:'../recompense/searchRecompense.action',
			actionMethods:'POST',
			reader:{
				type:'json',
				root:'recompenseAppList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new RecompenseSearchStore();
};
/**.
 * <p>
 * 理赔监控查询store<br/>
 * </p>
 * @returns  一个data.store
 * @author  张斌
 * @时间    2012-05-07
 */
RecompenseDataN.prototype.getMonitorRecompenseSearchStore=function(){
	if(!Ext.isEmpty(Ext.data.StoreManager.lookup('monitoringRecompenseSearchStore'))){
		return Ext.data.StoreManager.lookup('monitoringRecompenseSearchStore');
	}
	Ext.define('MonitorRecompenseSearchStore',{
		pageSize : 10,
		extend:'Ext.data.Store',
		model:'RecompenseApp',
		storeId:'monitoringRecompenseSearchStore',
		proxy:{
			type:'ajax',
			url:'../recompense/searchMonitorRecompense.action',
			actionMethods:'POST',
			reader:{
				type:'json',
				root:'recompenseAppList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new MonitorRecompenseSearchStore();
};
/**.
 * <p>
 * 客户历史理赔记录查询store<br/>
 * </p>
 * @returns  一个data.store
 * @author  张斌
 * @时间    2012-05-07
 */
RecompenseDataN.prototype.getHistroyRecompenseSearchStore=function(){
	if(!Ext.isEmpty(Ext.data.StoreManager.lookup('histroyRecompenseSearchStore'))){
		return Ext.data.StoreManager.lookup('histroyRecompenseSearchStore');
	}
	Ext.define('HistroyRecompenseSearchStore',{
		extend:'Ext.data.Store',
		model:'RecompenseApp',
		storeId:'histroyRecompenseSearchStore'
	});
	return new HistroyRecompenseSearchStore();
};
/**.
 * <p>
 * 上报部门store<br/>
 * </p>
 * @returns states 一个data.store
 * @author  潘光均
 * @时间    2012-03-27
 */
RecompenseDataN.prototype.getReportDeptStore=function(){
	Ext.define('ReportDeptStore',{
		pageSize : 10,
		extend:'Ext.data.Store',
		model:'Department',
		storeId:'reportDeptStore',
		proxy:{
			type:'ajax',
			url:'../recompense/searchReportDeptList.action',
			actionMethods:'POST',
			reader:{
				type:'json',
				root:'deptList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new ReportDeptStore();
};
/**.
 * <p>
 * 理赔查询store<br/>
 * </p>
 * @returns states 一个data.store
 * @author  潘光均
 * @时间    2012-03-27
 */
RecompenseDataN.prototype.getRegionStore=function(){
	Ext.define('RegionStore',{
		pageSize : 10,
		extend:'Ext.data.Store',
		model:'Department',
		storeId:'regionStore',
		proxy:{
			type:'ajax',
			url:'../recompense/searchAreaDeptList.action',
			actionMethods:'POST',
			reader:{
				type:'json',
				root:'deptList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new RegionStore();
};

/**.
 * <p>通过部门名称查询部门 </p>
 * @returns states 一个data.store
 * @author  潘光均
 * @时间    2012-03-27
 */
RecompenseDataN.prototype.getDeptByName=function(param,successFn,failureFn){
	var url = '../recompense/searchDeptByName.action';
	  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
}
/**.
 * <p>通过部门名称查询大区 </p>
 * @returns states 一个data.store
 * @author  潘光均
 * @时间    2012-03-27
 */
RecompenseDataN.prototype.getRegionByName=function(param,successFn,failureFn){
	var url = '../recompense/searchAreas.action';
	DpUtil.requestJsonAjax(url,param,successFn,failureFn);
}
/**.
 * <p>查询当前用户说管辖大区</p>
 * @author  张斌
 * @时间    2012-06-16
 */
RecompenseDataN.prototype.searchUserBlongArea=function(param,successFn,failureFn){
	var url = '../recompense/searchUserBlongArea.action';
	DpUtil.requestJsonAjax(url,param,successFn,failureFn);
}
/**.
 * <p>查询该理赔的差错信息</p>
 * @author  张斌
 * @时间    2012-06-29
 */
RecompenseDataN.prototype.searchRecompenseOaAccidentInfo = function( params, successFn, failureFn ){
	var url = '../recompense/searchRecompenseOaAccidentInfo.action';
	DpUtil.requestJsonAjax(url,params,successFn,failureFn);
};
/**.
 * <p>催促办理 </p>
  * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author  潘光均
 * @时间    2012-03-27
 */
RecompenseDataN.prototype.urgeProcess=function(param,successFn,failureFn){
	var url = '../recompense/urgeProcess.action';
	  DpUtil.requestJsonAjax(url,param,successFn,failureFn);
}
//查询支行信息
RecompenseDataN.prototype.searchBankInfo = function(params,successFn,failFn){
	DpUtil.requestJsonAjax('../common/searchBankInfoByBankView.action',params,successFn,failFn);
},

//查询支行信息
RecompenseDataN.prototype.updateLoadFile = function(form,successFn,failFn){
	var url = '../common/fileUpload.action?type=recompenceAttDir';
	form.submit({
        url:url,
        waitMsg: i18n('i18n.recompense.uploadFileLading'),
        success: function(form, response) {
        	var result = response.result;
        	if(result.success){
        		successFn(result);
			}else{
				failFn(result);
			}
        },
        failure:function(form, response){
        	var result = response.result;
        	if(result.success){
        		successFn(result);
			}else{
				failFn(result);
			}
        }
    });
};
/**
 * @author  Rock
 */
RecompenseDataN.prototype.getStore = function() {
	Ext.define('AcceptanceStore', {
		extend : 'Ext.data.Store',
		pageSize : 15,
		model : 'OnlineApplyModel',
		autoLoad : true,
		storeId : 'acceptanceStoreID',
		proxy : {
			type : 'ajax',
			url : '../recompense/searchOnlineApplyByWaybillNum.action',
			actionMethods : 'POST',
			reader : {
				type : 'json',
				root : 'onlineApplyList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new AcceptanceStore();
};

RecompenseDataN.prototype.Refuse = function( params, successFn, failureFn ){
	var url = '../recompense/refuseOnlineApply.action';
	DpUtil.requestJsonAjax(url,params,successFn,failureFn);
};

RecompenseDataN.prototype.recompensePrint = function(){
	var url = '../recompense/recompensePrint.action';
	return url;
};
RecompenseDataN.prototype.OnlineApplyPrintPage = function(){
	var url = '../recompense/OnlineApplyPrintPage.action';
	return url;
};

/**
 * 查询在线理赔信息
 */
RecompenseDataN.prototype.OnlineApplyPrintData = function( params, successFn, failureFn ){
	var url = '../recompense/onlineApplyPrintDt.action';
	DpUtil.requestJsonAjax(url,params,successFn,failureFn);
};

/**
 * 
 * <p>
 * Description:付款记录 Store<br />
 * </p>
 * @title PayRecordStore
 * @author 侯斌飞
 * @version 0.1 2012-12-31
 */
Ext.define('PayRecordStore',{
	extend:'Ext.data.Store'
	,model:'PayRecordModel'
	,pageSize:10
	,proxy:{
		type:'ajax'
		,api:{read:'searchPayRecordList.action'}
		,actionMethods:'POST'
		,reader:{
			type:'json',root:'payRecordList'
		}
	}
});

/**.
 * <p>
 * 付款申请store<br/>
 * </p>
 * @returns 
 * @author  邹明
 * @时间    2013-1-5
 */
RecompenseDataN.prototype.paymentApply = function(params, successFn, failureFn){
	var url = "../recompense/paymentApply.action";
	DpUtil.requestJsonAjax(url,params,successFn,failureFn);
};

/**
 * <p>
 *	根据收银员工号查询收银员帐号信息store<br/>
 * </p>
 * @author zouming
 * @时间    2013-1-7
 */
RecompenseDataN.prototype.queryPaymentByEmployeeCode = function(params, successFn, failureFn){
	var url = "../recompense/queryPaymentByEmployeeCode.action";
	DpUtil.requestJsonAjax(url,params,successFn,failureFn);
};

/**
 * <p>
 *	在线理赔监控催促办理<br/>
 * </p>
 * @author zouming
 * @时间 2013-11-13
 */
RecompenseDataN.prototype.pressDoForOnline = function(params, successFn, failureFn){
	var url = "../recompense/pressDoForOnline.action";
	DpUtil.requestJsonAjax(url,params,successFn,failureFn);
};

/**
 * <p>
 *	在线理赔监控详情<br/>
 * </p>
 * @author zouming
 * @时间 2013-11-13
 */
RecompenseDataN.prototype.lookUpOnlineApplyDetail = function(params, successFn, failureFn){
	var url = "../recompense/lookUpOnlineApplyDetail.action";
	DpUtil.requestJsonAjax(url,params,successFn,failureFn);
};