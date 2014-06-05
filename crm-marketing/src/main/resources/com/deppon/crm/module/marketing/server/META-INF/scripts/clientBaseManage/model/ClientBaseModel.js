/** 
 *客户群列表Model
 */
Ext.define('ClientBaseInfoModel', {
	extend : 'Ext.data.Model',
	fields : [{// 客户群编号
		name : 'id',
		type : 'string'
	},{// 客户群名称
		name : 'clientBaseName',
		type : 'string'
	},{// 客户群状态
		name : 'clientBaseStatus',
		type : 'string'
	},{// 创建人
		name : 'createUser',
		type : 'string'
	},{// 创建人id
		name : 'createUserId',
		type : 'string'
	},{// 创建时间
		name : 'createDate',
		type : 'Date',
		defaultValue:null,
		convert: DButil.changeLongToDate
	},{// 创建部门
		name : 'clientDeptId',
		type : 'string'
	},{// 创建部门Name
		name : 'clientDeptName',
		type : 'string'
	},{// 创建部门负责人Id
		name : 'principalId',
		type : 'string'
	},{//一级行业
		name : 'trades',
		type : 'Array'
	},
	{//二级级行业
		name : 'secondTrades',
		type : 'Array'
	},
	{//客户类型
		name : 'clientTypes',
		type : 'Array'
	},
	{//货量潜力
		name : 'clientlatents',
		type : 'Array'
	},
	{//客户等级
		name : 'clientGrades',
		type : 'Array'
	},
	{//客户来源
		name : 'clientSources',
		type : 'Array'
	},
	{//客户属性
		name : 'clientPropertys',
		type : 'Array'
	},
	{//提货方式
		name : 'takeMethods',
		type : 'Array'
	},
	{//合作意向
		name : 'cooperateWills',
		type : 'Array'
	},
	{//产品类型
		name:"productTypes",
		type : 'Array'
	},
	{//部门线路
		name : 'lineDept'
	//	type : 'Array'
	},
	{//客户开始创建时间
		name : 'clientCreateStartTime',
		type: 'Date',
		defaultValue:null,
		convert: DButil.changeLongToDate
	},
	{//客户结束创建时间
		name : 'clientCreateEndTime',
		type: 'Date',
		defaultValue:null,
		convert: DButil.changeLongToDate
	},{//线路类型
		name :'lineType'
	
	}]
});

/**
 * 二级行业model
 */
Ext.define('SecondTradeModel',{
    extend:'Ext.data.Model',
    fields:[{
        // 父数据ID
        name : 'parentId',
        type : 'string'
    },{
        //代码类型
        name : 'codeType',
        type : 'string'
    },{
        //代码
        name:'code',
        type:'string'
    },{
        //代码类型描述
        name:'codeDesc',
        type:'string'
    },{
        //状态
        name:'status',
        type:'boolean'
    },{
        //代码序列
        name:'codeSeq',
        type:'int'
    },{
        //语言
        name:'language',
        type:'string'
    },{
        //失效时间
        name:'invaliTime',
        type:'Date',
        defalut : null,
        convert : DButil.changeLongToDate
    }]
});
/**
 * 线路区域列表model
 */
Ext.define('LineDeptModel',{
    extend:'Ext.data.Model',
    fields:[{
        // 对应业务表ID
        name:'conditionId',
        type:'string'
    },{
        // 对应业务类型
        name:'conditionType',
        type:'string'
    },{
        // 出发部门名称
        name:'leavedDeptName',
        type:'string'
    },{
        // 出发部门标杆编码
        name:'leaveDeptCode',
        type:'string'
    },{
        // 到达部门名称
        name:'arriveDeptName',
        type:'string'
    },{
        // 到达部门标杆编码
        name:'arriveDeptCode',
        type:'string'
    }]
});
/**
 * 获取部门Model
 */
Ext.define('DeptModel', {
    extend : 'Ext.data.Model',
    fields : [{
        // id
        name : 'id',
        type : 'string'
    },{
        // 姓名
        name : 'deptName',
        type : 'string'
    },{
        // 标杆编码
        name : 'standardCode',
        type : 'string'
    }]
});
/**
 * 部门树Model
 */
Ext.define('LineAreaTreeModel', {
    extend : 'Ext.data.Model',
    fields : [{
        // id
        name : 'id',
        type : 'string'
    },{
        // 部门名称
        name : 'text',
        type : 'string'
    },{
        // 标杆编码
        name : 'deptStandardCode',
        type : 'string'
    },{
        //是否可选择
        name : 'canSelect'
    }]
});
