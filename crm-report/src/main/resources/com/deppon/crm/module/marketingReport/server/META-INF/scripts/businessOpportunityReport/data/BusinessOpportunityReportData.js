/**
 * 商机开发效果评估
 */
BoEvaluationStore = function(){
	
};
/**
 * 初始化登录用户权限信息
 * 
 */
var AssessAuthority={};
function createAssessAuth(){
	Ext.Ajax.request({
		url : '../marketing/createAssessAuth.action',
		async:false,
		success:function(response){
			var result = Ext.decode(response.responseText);
			AssessAuthority=result.assessAuthority;
			return AssessAuthority;
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
			MessageUtil.showMessage(result.message);
		}
	});
};
/**
 * 根据上一级部门名称，查询出下一级子部门列表Store
 */
Ext.define('SelectDeptBySuperiorDeptStore',{
	extend:'Ext.data.Store',
	model:'DeptListModel',
	proxy : {
		type : 'ajax',
		url : '../marketing/searchDeptsByParentId.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'departmentList'
		}
	}
});
/**
 * 经营本部下拉框
 */
Ext.define('ManageDeptStore',{
	extend:'Ext.data.Store',
	model:'DeptListModel',
	proxy : {
		type : 'ajax',
		url : '../marketing/searchmanagerDept.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'departmentList'
		}
	}
});
/**
 * 客户开发查询结果表格Store
 */
Ext.define('CRM.Report.BOStore',{
	extend:'Ext.data.Store',
	model:'CRM.Report.BO',
	pageSize:20,
//	sorters: [{
//        property : 'deptId',
//        direction: 'DESC'
//	}],
	proxy:{
		type:'ajax',
        timeout:300000, 
		url:'../marketingReport/searchBoReport.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'boReportInfoList'
		}
	}
});

/**
 * 开发：导出权限下所有数据到服务器端生成一个Excel报表文件
 */
BoEvaluationStore.prototype.creatBoReportExcel = function(param,successFn,failureFn){
	var url = '../marketingReport/createBoReportExcel.action';
	Ext.Ajax.request({
		url:url,
		jsonData:param,
		//设置超时时间为5分钟
		timeout: 300000,
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				successFn(result);
			}else{
				failureFn(result);
			}
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
			failureFn(result);
		}
	});
};


/**
 * 开发：导出权限下所有数据到服务器端生成一个Excel报表文件
 */
BoEvaluationStore.prototype.creatBoReportExcelByCondition = function(param,successFn,failureFn){
	var url = '../marketingReport/creatDevReportExcelByCondition.action';
	Ext.Ajax.request({
		url:url,
		jsonData:param,
		//设置超时时间为5分钟
		timeout: 300000,
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				successFn(result);
			}else{
				failureFn(result);
			}
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
			failureFn(result);
		}
	});
};