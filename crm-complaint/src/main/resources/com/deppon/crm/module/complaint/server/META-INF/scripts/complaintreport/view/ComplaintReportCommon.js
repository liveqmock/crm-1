/**
 *  获取传入参数的 value值
 * @param {} name 参数
 * @return {String}
 */
function queryString(name){
    var reg = new RegExp(name+"=([0-9a-zA-Z%_\u4e00-\u9fa5\\.]+)");
    if(reg.test(location.search)){
          return RegExp.$1;
    }
    return "";
}

//服务编号
var serviceId=decodeURIComponent(queryString('serviceId'));
//CC 来源 SUZHOU or HEFEI
var channel=decodeURIComponent(queryString('channel'));
//联系人
var strContact=decodeURIComponent(queryString('strContact'));
//联系方式
var custPhone=decodeURIComponent(queryString('custPhone'));
//根据呼叫中心电话接入IVR按键为投诉,则上报类型默认投诉不可修改,IVR按键非投诉,可修改
var ivr=decodeURIComponent(queryString('ivr'));
//来电人
var customerName=decodeURIComponent(queryString('customerName'));;

/**
 * 根据电话查询信息
 * @param {} tel
 */
function inquireByPhone(tel){
		//清空重复工单码,选中的那条fid清空
		if(null!=currentRecord){
			chooseSelModel.deselect(currentRecord);						
		}
					
		if(Ext.String.trim(tel)!=""){
			var me = Ext.getCmp('complaintReportConditionFormId');
			Ext.getCmp('paramPhone').setValue(tel);
		  	Ext.getCmp('paramBill').setValue("");			
		  	me.complaintReportData.getComplaintReportSearchStore().load();
						
			var params = {};
			var searchModel = Ext.create('SearchComplaintReportConditionModel');
			searchModel.set('phone',tel);
			params.complaintSearchCondition = searchModel.data;
						
			// 查询成功回调函数
			var searchSuccess = function(response) {
				isBillGetCustomerInfo=false;
				me.passShipperMember=response.shipperMember;
				me.passReceiverMember=response.receiverMember;
				//设置客户信息
				if(response.shipperMember!=null){//发货人
					Ext.getCmp("complainid").setValue(response.shipperMember.id);
					Ext.getCmp("complaincust").setValue(response.shipperMember.custName);
					Ext.getCmp("custlevel").setValue(response.shipperMember.degree);
					Ext.getCmp("custtype").setValue(response.shipperMember.custType);
				}else{
					Ext.getCmp("complainid").setValue("");
					Ext.getCmp("complaincust").setValue("");
					Ext.getCmp("custlevel").setValue("");
					Ext.getCmp("custtype").setValue("");
				}
				//设置相关客户信息
				if(response.receiverMember!=null){//收货人
					Ext.getCmp("relatcusid").setValue(response.receiverMember.id);
					Ext.getCmp("relatcus").setValue(response.receiverMember.custName);
					Ext.getCmp("relatcusLevel").setValue(response.receiverMember.degree);
					Ext.getCmp("relatCustType").setValue(response.receiverMember.custType);
				}else{
					Ext.getCmp("relatcusid").setValue("");
					Ext.getCmp("relatcus").setValue("");
					Ext.getCmp("relatcusLevel").setValue("");
					Ext.getCmp("relatCustType").setValue("");
				}
			};
			// 查询失败调函数
			var searchFail = function(response) {
					MessageUtil.showErrorMes(response.message);
			};
			var searchMembersByOWNumUrl='getContractByPhone.action';
			DpUtil.requestJsonAjax(searchMembersByOWNumUrl,params,searchSuccess,searchFail);
		}
}

Ext.onReady(function() {
			//页面需要加载的数据字典数组
			var keys=[
		        //来电 人类型
			    'CALLER_TYPE',
			    //期望时限    
			    'EXPECTED_TIME_LIMIT',
			    //工单来源
			    'WORK_SINGLE_SOURCE',
			    //优先级别
			    'PRIORITY_RATING',
			     //上报类型
			    'REPORT_TYPE',
			    //工单模块处理状态
			    'COMPLAINT_PROCESS_STATUS',
			   //解决情况
			    'RESOLVE_CASE',
			    'COMPLAINT_RECORD_TYPE',//工单模块反馈类型,
			    //客户满意度
			    'SATISFACTION_DEGREE',
      			// 客户类型
      			'CUSTOMER_TYPE',
  			     // 目标级别',目前级别,客户等级',会员等级
  			     'MEMBER_GRADE',
  			      // 业务类型
      			'COMPLAINT_BUSINESS_MODEL'
		  	];
			//初始数据字典
			initDataDictionary(keys);
			/*   详情弹出框 加 滚动条 begin    */
			viewport = Ext.create('Ext.container.Viewport', {
						layout:'border','autoScroll':true
						,items : [Ext.create('complaintReportPanel', {
									region:'center',
									'complaintReportData' : complaintReportDataControl
								})]
		    });
		    viewport.setAutoScroll(false);
			viewport.doLayout();
			/*   详情弹出框 加 滚动条 end    */
			//设置上报用户
			Ext.Ajax.request({
				url : '../common/queryUserInfo.action',
				success:function(response){
					var result = Ext.decode(response.responseText);
					if(result.success){
						Ext.getCmp("reportName").setValue(result.user.empName);//用户name
						Ext.getCmp("reportId").setValue(result.user.userId);//用户Id
						userId=result.user.userId;
						userName=result.user.empName;
					}
				}
		 	});
		 	//Ext.getCmp("compmanId").setValue("SHIPMAN");
		 	Ext.getCmp("tilimitcycle").setValue("MINUTE");
		 	Ext.getCmp("pririty").setValue("NORMAL");
		 	Ext.getCmp("workSingleSource").setValue("TELEPHONE");
		 	
		 	if(null!=custPhone && ""!=custPhone){
		 		Ext.getCmp('telId').setValue(custPhone);
		 		inquireByPhone(custPhone);
		 	}
//		 	if(null!=strContact && ""!=strContact){
//		 		Ext.getCmp('compmanOutId').setValue(strContact);
//		 	}
		 	//设置默认类型
		 	if(null!=serviceId && ""!=serviceId){
				if(null!=ivr && ""!=ivr && "投诉"==ivr){
			 		Ext.getCmp('reporttypeComplaint').setValue(true);
			 		Ext.getCmp('reportTypeId').setReadOnly(true);
			 	}else{
			 		Ext.getCmp('reporttypeUnusual').setValue(true);
			 	}		 		
		 	}
		 	if(null!=customerName && ""!=customerName){
		 		Ext.getCmp('compmanOutId').setValue(customerName);
		 	}
		 	
});

var viewport = null;/* 视图容器 */