SchedulerData = function(){
};
/**
 * 返回查询STORE
 */
SchedulerData.prototype.getSchedulerSearchStore = function(){
	Ext.define('SchedulerSearchStore',{
		pageSize : 10,
		extend:'Ext.data.Store',
		model:'SchedulerModel',
		autoLoad:true,
		storeId:'schedulerSearchStore',
		proxy:{
			type:'ajax',
			url:'../scheduler/schedulerSearch.action',
			actionMethods:'POST',
			timeout:100000,
			reader:{
				type:'json',
				root:'schedulerList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new SchedulerSearchStore();
};