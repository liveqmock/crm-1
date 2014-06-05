/**
 * 
 * <p>
 * Description:可视化营销model<br />
 * </p>
 * @title VisualMarketingModel.js
 * @author roy
 * @version 0.1 2013-4-20
 */



Ext.define('CRM.marketing.CustomerMarketInfoModel', {
	extend : 'Ext.data.Model',
	fields : [{
		//固定客户编码
		name : 'custNumber',
		type : 'string'
	}, {
		//固定客户id
		name : 'custId',
		type : 'string'
	}, {
		//潜散客id
		name : 'psCustId',
		type : 'string'
	}, {
		//客户名称
		name : 'custName',
		type : 'string'
	}, {
		//客户名称
		name : 'memberName',
		type : 'string'
	}, {
		//主联系人编码（固定客户）
		name : 'linkManNumber',
		type : 'string'
	}, {
		//主联系人姓名(固定客户)
		name : 'mainLinkManName',
		type : 'string'
	}, {
		//联系人姓名（潜散客）
		name : 'linkManName',
		type : 'string'
	}, {
		//手机
		name : 'mobile',
		type: 'string'
		
	}, {
		//固定电话
		name : 'telephone',
		type : 'string'
	}, {
		//地址（固定客户：主联系人的主偏好地址；潜散客：客户地址）
		name : 'address',
		type : 'string'
	}, {
		//地址经度
		name : 'longitude',
		type : 'string'
	},{
		//地址纬度
		name : 'latitude',
		type : 'string'
	}, {//月均收入（固定客户）
		name : 'aveMonthlyIncome',
		type : 'string'
	},{//当月货量
		name : 'monthlyGoodsNum',
		type : 'string'
	},{//交易时间（固定客户）
		name : 'dealTime',
		type: 'Date',
		defaultValue:null,
		convert: DButil.changeLongToDate
	},{//回访时间
		name : 'returnVisitTime',
		type: 'Date',
		defaultValue:null,
		convert: DButil.changeLongToDate
	},{//创建时间
		name : 'createTime',
		type: 'Date',
		defaultValue:null,
		convert: DButil.changeLongToDate
	},{//回访状态
		name : 'returnVisitStatus',
		type : 'string'
	},{//归属部门
		name : 'deptId',
		type : 'string'
	},{//部门网点id(部门服务区坐标编号)
		name : 'depCoordinate',
		type : 'string'
	},{//客户类型(固定客户：MEMBER;潜客;散客)
		name : 'custType',
		type : 'string'
	},{//部门网点id(部门服务区坐标编号)
		name : 'depCoordinate',
		type : 'string'
	},{//固定客户客户等级
		name : 'custDegree',
		type : 'string'
	},{//上月发货周期
		name : 'sendGoodsCycle',
		type : 'string'
	},{//固定客户主联系人ID
		name : 'linkManId',
		type : 'string'
	}]
});	




	





   