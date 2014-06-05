/**
 * 存储客户Store
 */
Ext.define('ScatterCustTestStore', {
	extend: 'Ext.data.Store',
	pageSize : 20,
	model : 'ScatterCustModel',
	autoLoad:true,
	proxy:{
		 type:'memory',
		 data : [ {id : '1',custName : '22',linkManName : '11',linkManMobile : '11',linkManPhone : '11',volumePotential : '11',bussinesState : '11',developState : '11',finalreviTime : '11',reviTimes : '11',programmeTime : '11',createUser : '1',createDate : '1', modifyUser : '1',modifyDate : '1'
			},{id : '2',custName : '22',linkManName : '11',linkManMobile : '11',linkManPhone : '11',volumePotential : '11',bussinesState : '11',developState : '11',finalreviTime : '11',reviTimes : '11',programmeTime : '11',createUser : '1',createDate : '1', modifyUser : '1',modifyDate : '1'
			},{id : '3',custName : '22',linkManName : '11',linkManMobile : '11',linkManPhone : '11',volumePotential : '11',bussinesState : '11',developState : '11',finalreviTime : '11',reviTimes : '11',programmeTime : '11',createUser : '1',createDate : '1', modifyUser : '1',modifyDate : '1'
			},{id : '4',custName : '22',linkManName : '11',linkManMobile : '11',linkManPhone : '11',volumePotential : '11',bussinesState : '11',developState : '11',finalreviTime : '11',reviTimes : '11',programmeTime : '11',createUser : '1',createDate : '1', modifyUser : '1',modifyDate : '1'
			},{id : '5',custName : '22',linkManName : '11',linkManMobile : '11',linkManPhone : '11',volumePotential : '11',bussinesState : '11',developState : '11',finalreviTime : '11',reviTimes : '11',programmeTime : '11',createUser : '1',createDate : '1', modifyUser : '1',modifyDate : '1'
			},{id : '6',custName : '22',linkManName : '11',linkManMobile : '11',linkManPhone : '11',volumePotential : '11',bussinesState : '11',developState : '11',finalreviTime : '11',reviTimes : '11',programmeTime : '11',createUser : '1',createDate : '1', modifyUser : '1',modifyDate : '1'
			},{id : '7',custName : '22',linkManName : '11',linkManMobile : '11',linkManPhone : '11',volumePotential : '11',bussinesState : '11',developState : '11',finalreviTime : '11',reviTimes : '11',programmeTime : '11',createUser : '1',createDate : '1', modifyUser : '1',modifyDate : '1'
			},{id : '8',custName : '22',linkManName : '11',linkManMobile : '11',linkManPhone : '11',volumePotential : '11',bussinesState : '11',developState : '11',finalreviTime : '11',reviTimes : '11',programmeTime : '11',createUser : '1',createDate : '1', modifyUser : '1',modifyDate : '1'
			},{id : '9',custName : '22',linkManName : '11',linkManMobile : '11',linkManPhone : '11',volumePotential : '11',bussinesState : '11',developState : '11',finalreviTime : '11',reviTimes : '11',programmeTime : '11',createUser : '1',createDate : '1', modifyUser : '1',modifyDate : '1'
			},{id : '10',custName : '22',linkManName : '11',linkManMobile : '11',linkManPhone : '11',volumePotential : '11',bussinesState : '11',developState : '11',finalreviTime : '11',reviTimes : '11',programmeTime : '11',createUser : '1',createDate : '1', modifyUser : '1',modifyDate : '1'
			},{id : '11',custName : '22',linkManName : '11',linkManMobile : '11',linkManPhone : '11',volumePotential : '11',bussinesState : '11',developState : '11',finalreviTime : '11',reviTimes : '11',programmeTime : '11',createUser : '1',createDate : '1', modifyUser : '1',modifyDate : '1'
			},{id : '12',custName : '22',linkManName : '11',linkManMobile : '11',linkManPhone : '11',volumePotential : '11',bussinesState : '11',developState : '11',finalreviTime : '11',reviTimes : '11',programmeTime : '11',createUser : '1',createDate : '1', modifyUser : '1',modifyDate : '1'
			},{id : '13',custName : '22',linkManName : '11',linkManMobile : '11',linkManPhone : '11',volumePotential : '11',bussinesState : '11',developState : '11',finalreviTime : '11',reviTimes : '11',programmeTime : '11',createUser : '1',createDate : '1', modifyUser : '1',modifyDate : '1'
			},{id : '14',custName : '22',linkManName : '11',linkManMobile : '11',linkManPhone : '11',volumePotential : '11',bussinesState : '11',developState : '11',finalreviTime : '11',reviTimes : '11',programmeTime : '11',createUser : '1',createDate : '1', modifyUser : '1',modifyDate : '1'
			},{id : '15',custName : '22',linkManName : '11',linkManMobile : '11',linkManPhone : '11',volumePotential : '11',bussinesState : '11',developState : '11',finalreviTime : '11',reviTimes : '11',programmeTime : '11',createUser : '1',createDate : '1', modifyUser : '1',modifyDate : '1'
			}]
		}
});
Ext.define('ScatterCustSearchDataTest',{
	extend:'ScatterCustBasicDataTest',
	scatterCustStore:null,
	//散客Store
	getScatterCustStore:function(){
		return this.scatterCustStore;
	},
	//初始化散客Store
	initialScatterCustStore:function(beforeLoadScatterFn){
		this.scatterCustStore = new ScatterCustTestStore();
		return this.scatterCustStore;
	},
	//查询
	searchScatterCustomerList:function(params,successFn,failFn){
		return {'success':true,message:''};
	},
	//删除
	deleteScatterCustomer:function(params,successFn,failFn){
		return {'success':true,message:''} ;
	}
});