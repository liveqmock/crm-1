/**
 * 营销活动报表Model
 *  @author 吕崇新
 */
Ext.define('ClientBaseDetailInfoModel', {
	extend : 'Ext.data.Model',
	fields : [{// 客户群报表编号
		name : 'id',
		type : 'string'
	},{// 客户群名称
		name : 'clientBaseName',
		type : 'string'
	},{// 客户群ID
		name : 'clientBaseId',
		type : 'string'
	},{// 市场推广活动ID
		name : 'activityId',
		type : 'string'
	},{// 市场推广活动名称
		name : 'activityName',
		type : 'string'
	},{//归属部门ID
		name : 'deptId',
		type : 'string'
	},{//归属部门名称
		name : 'deptName',
		type : 'string'
	},{//客户编码
		name : 'clientNum',
		type : 'string'
	},{// 客户名称
		name : 'clientName',
		type : 'string'
	},{// 客户类型
		name : 'clientType',
		type : 'string'
	},{//客户等级
		name : 'clientLevel',
		type : 'string'
	},
	{//客户行业
		name : 'clientTrade',
		type : 'string'
	},
	{//客户类型
		name : 'clientTypes',
		type : 'string'
	},
	{//精准卡航发货金额
		name : 'airCardAmount',
		type : 'string'
	},
	{//经济快递发货金额
		name : 'expressAmount',
		type : 'string'
	},
	{//精准汽运（长）发货金额
		name : 'longAmount',
		type : 'string'
	},
	{//精准汽运（短）发货金额
		name : 'shortAmount',
		type : 'string'
	},
	{//精准空运发货金额
		name : 'airAmount',
		type : 'string'
	},
	{//精准城运发货金额
		name : 'cityRanAmount',
		type : 'string'
	},
	{//汽运偏线发货金额
		name:"partialLineAmount",
		type : 'string'
	}]
});