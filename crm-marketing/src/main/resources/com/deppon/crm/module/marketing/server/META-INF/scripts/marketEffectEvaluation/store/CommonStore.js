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