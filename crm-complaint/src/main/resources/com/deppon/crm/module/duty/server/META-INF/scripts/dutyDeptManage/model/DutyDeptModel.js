//工单特殊责任部门model
Ext.define('DutyDeptModel',{
	extend:'Ext.data.Model',
	fields:[
		{name:'deptId'},  //部门ID
		{name:'standardCode'}, //部门编码
		{name:'deptName'} //部门名称
	]
});