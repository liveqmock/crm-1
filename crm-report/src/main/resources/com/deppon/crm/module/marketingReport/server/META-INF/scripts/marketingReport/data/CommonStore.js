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
 * 预警客户明细表格store
 */
Ext.define('WarnLostCustStore',{
	extend:'Ext.data.Store',
	pageSize:20,
	model:'WarnLostCustModel',
	proxy : {
		type : 'ajax',
		url : '../marketingReport/queryWarnLostCust.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'warnLostCustList',
			totalProperty : 'totalCount'
		}
	}
});
/**
 * 预警客户回访与流失监控日报表统计store
 */
Ext.define('WarnLostCustDailyStore',{
	extend:'Ext.data.Store',
	pageSize:20,
	model:'WarnLostCustDailyModel',
	proxy : {
		type : 'ajax',
		url : '../marketingReport/queryWarnCustReportDailyBatch.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'warnLostCustDailyList',
			totalProperty : 'totalCount'
		}
	}
});