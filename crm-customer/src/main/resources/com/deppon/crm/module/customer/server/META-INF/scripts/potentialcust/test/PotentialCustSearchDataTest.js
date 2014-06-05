/**
 * 存储客户Store
 */
Ext.define('PotentialCustTestStore', {
	extend: 'Ext.data.Store',
	pageSize : 20,
	model : 'PotentialCustModel',
	autoLoad:true,
	proxy:{
		 type:'memory',
		 data : [ {id : '1',custName : '22',trade : '11',custbase : '11',memberNo : '11',linkManName : '11',linkManMobile : '11',linkManPhone : '11',post : '1',bussinesState : '1', city : '1',address : '1',recentcoop : '1',coopIntention : '1',volumePotential : '1',recentGoods : '1',custNeed : '1',reviTimes : '1',finalreviTime : '1',developState : '1',creator : '1',createTime : '1',lastUpdateTime : '1',lastUpdate : '1',infoState : '1'
			},{id : '2',custName : '22',trade : '11',custbase : '11',memberNo : '11',linkManName : '11',linkManMobile : '11',linkManPhone : '11',post : '1',bussinesState : '1', city : '1',address : '1',recentcoop : '1',coopIntention : '1',volumePotential : '1',recentGoods : '1',custNeed : '1',reviTimes : '1',finalreviTime : '1',developState : '1',creator : '1',createTime : '1',lastUpdateTime : '1',lastUpdate : '1',infoState : '1'
			},{id : '3',custName : '22',trade : '11',custbase : '11',memberNo : '11',linkManName : '11',linkManMobile : '11',linkManPhone : '11',post : '1',bussinesState : '1', city : '1',address : '1',recentcoop : '1',coopIntention : '1',volumePotential : '1',recentGoods : '1',custNeed : '1',reviTimes : '1',finalreviTime : '1',developState : '1',creator : '1',createTime : '1',lastUpdateTime : '1',lastUpdate : '1',infoState : '1'
			},{id : '4',custName : '22',trade : '11',custbase : '11',memberNo : '11',linkManName : '11',linkManMobile : '11',linkManPhone : '11',post : '1',bussinesState : '1', city : '1',address : '1',recentcoop : '1',coopIntention : '1',volumePotential : '1',recentGoods : '1',custNeed : '1',reviTimes : '1',finalreviTime : '1',developState : '1',creator : '1',createTime : '1',lastUpdateTime : '1',lastUpdate : '1',infoState : '1'
			},{id : '5',custName : '22',trade : '11',custbase : '11',memberNo : '11',linkManName : '11',linkManMobile : '11',linkManPhone : '11',post : '1',bussinesState : '1', city : '1',address : '1',recentcoop : '1',coopIntention : '1',volumePotential : '1',recentGoods : '1',custNeed : '1',reviTimes : '1',finalreviTime : '1',developState : '1',creator : '1',createTime : '1',lastUpdateTime : '1',lastUpdate : '1',infoState : '1'
			},{id : '6',custName : '22',trade : '11',custbase : '11',memberNo : '11',linkManName : '11',linkManMobile : '11',linkManPhone : '11',post : '1',bussinesState : '1', city : '1',address : '1',recentcoop : '1',coopIntention : '1',volumePotential : '1',recentGoods : '1',custNeed : '1',reviTimes : '1',finalreviTime : '1',developState : '1',creator : '1',createTime : '1',lastUpdateTime : '1',lastUpdate : '1',infoState : '1'
			},{id : '7',custName : '22',trade : '11',custbase : '11',memberNo : '11',linkManName : '11',linkManMobile : '11',linkManPhone : '11',post : '1',bussinesState : '1', city : '1',address : '1',recentcoop : '1',coopIntention : '1',volumePotential : '1',recentGoods : '1',custNeed : '1',reviTimes : '1',finalreviTime : '1',developState : '1',creator : '1',createTime : '1',lastUpdateTime : '1',lastUpdate : '1',infoState : '1'
			},{id : '8',custName : '22',trade : '11',custbase : '11',memberNo : '11',linkManName : '11',linkManMobile : '11',linkManPhone : '11',post : '1',bussinesState : '1', city : '1',address : '1',recentcoop : '1',coopIntention : '1',volumePotential : '1',recentGoods : '1',custNeed : '1',reviTimes : '1',finalreviTime : '1',developState : '1',creator : '1',createTime : '1',lastUpdateTime : '1',lastUpdate : '1',infoState : '1'
			},{id : '9',custName : '22',trade : '11',custbase : '11',memberNo : '11',linkManName : '11',linkManMobile : '11',linkManPhone : '11',post : '1',bussinesState : '1', city : '1',address : '1',recentcoop : '1',coopIntention : '1',volumePotential : '1',recentGoods : '1',custNeed : '1',reviTimes : '1',finalreviTime : '1',developState : '1',creator : '1',createTime : '1',lastUpdateTime : '1',lastUpdate : '1',infoState : '1'
			},{id : '10',custName : '22',trade : '11',custbase : '11',memberNo : '11',linkManName : '11',linkManMobile : '11',linkManPhone : '11',post : '1',bussinesState : '1', city : '1',address : '1',recentcoop : '1',coopIntention : '1',volumePotential : '1',recentGoods : '1',custNeed : '1',reviTimes : '1',finalreviTime : '1',developState : '1',creator : '1',createTime : '1',lastUpdateTime : '1',lastUpdate : '1',infoState : '1'
			},{id : '11',custName : '22',trade : '11',custbase : '11',memberNo : '11',linkManName : '11',linkManMobile : '11',linkManPhone : '11',post : '1',bussinesState : '1', city : '1',address : '1',recentcoop : '1',coopIntention : '1',volumePotential : '1',recentGoods : '1',custNeed : '1',reviTimes : '1',finalreviTime : '1',developState : '1',creator : '1',createTime : '1',lastUpdateTime : '1',lastUpdate : '1',infoState : '1'
			},{id : '12',custName : '22',trade : '11',custbase : '11',memberNo : '11',linkManName : '11',linkManMobile : '11',linkManPhone : '11',post : '1',bussinesState : '1', city : '1',address : '1',recentcoop : '1',coopIntention : '1',volumePotential : '1',recentGoods : '1',custNeed : '1',reviTimes : '1',finalreviTime : '1',developState : '1',creator : '1',createTime : '1',lastUpdateTime : '1',lastUpdate : '1',infoState : '1'
			},{id : '13',custName : '22',trade : '11',custbase : '11',memberNo : '11',linkManName : '11',linkManMobile : '11',linkManPhone : '11',post : '1',bussinesState : '1', city : '1',address : '1',recentcoop : '1',coopIntention : '1',volumePotential : '1',recentGoods : '1',custNeed : '1',reviTimes : '1',finalreviTime : '1',developState : '1',creator : '1',createTime : '1',lastUpdateTime : '1',lastUpdate : '1',infoState : '1'
			}]
		}
});
Ext.define('PotentialCustSearchDataTest',{
	extend:'PotentialCustBasicDataTest',
	searchPotentialCustomerList:function(params,successFn,failFn){
		return new PotentialCustTestStore();
	},
	deletePotentialCustomer:function(params,successFn,failFn){
		return {'success':true,message:''} ;
	}
});