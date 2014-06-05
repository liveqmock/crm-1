//行业品名Model
Ext.define('GoodTradeMappingModel',{
	extend:'Ext.data.Model',
	fields:[{
		name:'id'// id
		},{
		name:'articleName'// 待映射品名
		},{name:'delayaccounting'//发货票数占比
		},{name:'firstTradeCode'//一级行业编码
		},{name:'secondTradeCode'//二级行业编码
		},{name:'associatedPersonId'//关联人id
		},{name:'associatedPerson'//关联人
		},{name:'associatedTime'//关联时间
		},{name:'associatedStatus'//关联状态
		},{name:'remark'// 备注
		}
	]
}); 