 Ext.define('KeycustomerStore',{
	pageSize : 20,
	extend:'Ext.data.Store',
	model:'KeycustomerModel',
	autoLoad:false,
	proxy:{
		type:'ajax',
		url:'../keycustomer/searchKeycustList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'keycustomerList',
			totalProperty : 'totalCount'
		}
	}
});
Ext.define('KeyCustWorkflowStore',{
	pageSize : 20,
	extend:'Ext.data.Store',
	model:'KeycustomerWorkflowInfoModel',
	autoLoad:false,
	proxy:{
		type:'ajax',
		url:'../keycustomer/searchKeycustWorkflowList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'keycustWorkflowList',
			totalProperty : 'totalCount'
		}
	}
});