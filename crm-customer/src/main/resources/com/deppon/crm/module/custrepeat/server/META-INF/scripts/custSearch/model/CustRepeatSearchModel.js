/**
 * 疑似重复客户 数据模型
 */
Ext.define('CustRepeatModel',{
	extend:'Ext.data.Model',
	fields:[
		// 客户id
        {name:'custId',type:'string'},
		// 客户名称
		{name:'custName',type:'string'},
		// 银行账号
		{name:'bankAccount',type:'string'},
		// 固定电话
		{name:'telephone',type:'string'},
		// 客户编码
		{name:'custCode',type:'string'},
		// 主客户ID
		{name:'mainCustId',type:'string'},
		// 近三个月的发货总金额
		{name:'deliveryMoneyTotal',type:'string'},
		// 所在部门的ID
		{name:'deptId',type:'string'},
		// 所属部门名称
		{name:'deptName',type:'string'},
		// 所属小区的ID
		{name:'smallRegionDeptId',type:'string'},
		// 所属小区名称
		{name:'smallRegionDeptName',type:'string'},
		// 所属大区的Id
		{name:'bigRegionDeptId',type:'string'},
		// 所属大区名称
		{name:'bigRegionDeptName',type:'string'},
		// 所属事业部的Id
		{name:'bussDeptId',type:'string'},
		// 所属事业部名称
		{name:'bussDeptName',type:'string'},
		// 是否合同
		{name:'isContractCust',type:'string'},
		// 是否存在有效的合同信息
		{name:'isExistValidContract',type:'string'},
		// 是否存在历史的合同信息
		{name:'isExistHistoryContract',type:'string'},
		// 是否主客户
		{name:'isMainCust',type:'string'},
		// 联系人姓名
		{name:'linkMainName',type:'string'},
		//客户等级
		{name:'custLevel',type:'string'},
		//客户类型
		{name:'custType',type:'string'},
		//是否为大客户
		{name:'isBigcustomer',type:'string'},
		//疑似重复状态
		{name:'repeatStatus',type:'string'},
		//客户类别
		{name:'custGroup',type:'string'},
		//生成时间
		{name:'createtime',type: 'date',convert:DpUtil.changeLongToDate,defaultValue:null},
		//客户创建时间
		{name:'custCreateTime',type: 'date',convert:DpUtil.changeLongToDate,defaultValue:null},
		//是否为新加客户
		{name:'isAdd',type:'string'}
	]
});