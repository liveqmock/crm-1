/**
* @description 部门信息model
* @author 盛诗庆
* @date 2013-12-13
* @revision none
**/
Ext.define('DeptInfoModel',{
	extend : 'Ext.data.Model',
	fields : [{
		name : 'deptId'			//部门id
	},{
		name : 'deptName'	//部门名称
	},{
		name : 'regionId'	//部门所辖营销区域id
	},{
		name : 'color'		//划分区域颜色
	}]
});
/**
 * @description 部门model
 * @author 盛诗庆
 * @date 2013-12-16
 * @revision none
 */
Ext.define('DeptModel',{
	extend : 'Ext.data.Model',
	fields : [{
		name : 'id'				//部门id
	},{
		name : 'deptName'		//部门名称
	},{
		name : 'standardCode'	//标准编码
	}]
})