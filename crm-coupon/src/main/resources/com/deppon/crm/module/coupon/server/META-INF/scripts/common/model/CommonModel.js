/**
 * 数据字典Model
 */
Ext.define('DeptModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		// id
		name : 'id',
		type : 'string'
	}, {
		// 姓名
		name : 'deptName',
		type : 'string'
	} ]
});