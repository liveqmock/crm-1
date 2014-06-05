//工单上报列表查询 结果 model
Ext.define('ComplaintReportListStoreTest',{
	extend:'Ext.data.Store',
	model:'ComplaintReportListModel',
	autoLoad:true,
	proxy:{
		type:'memory',
		data:[{belongDeptId:'1',belongDeptName:'1',custNumber:'1',custName:'1',linkName:'1',linkPhone:'1',linkTel:'1',currentLevel:'1',targetLevel:'1'}
		      ,{belongDeptId:'2',belongDeptName:'1',custNumber:'1',custName:'1',linkName:'1',linkPhone:'1',linkTel:'1',currentLevel:'1',targetLevel:'1'}
		      ,{belongDeptId:'3',belongDeptName:'1',custNumber:'1',custName:'1',linkName:'1',linkPhone:'1',linkTel:'1',currentLevel:'1',targetLevel:'1'}
		      ,{belongDeptId:'4',belongDeptName:'1',custNumber:'1',custName:'1',linkName:'1',linkPhone:'1',linkTel:'1',currentLevel:'1',targetLevel:'1'}
		      ,{belongDeptId:'5',belongDeptName:'1',custNumber:'1',custName:'1',linkName:'1',linkPhone:'1',linkTel:'1',currentLevel:'1',targetLevel:'1'}
		      ,{belongDeptId:'6',belongDeptName:'1',custNumber:'1',custName:'1',linkName:'1',linkPhone:'1',linkTel:'1',currentLevel:'1',targetLevel:'1'}
		      ,{belongDeptId:'7',belongDeptName:'1',custNumber:'1',custName:'1',linkName:'1',linkPhone:'1',linkTel:'1',currentLevel:'1',targetLevel:'1'}
		      ,{belongDeptId:'8',belongDeptName:'1',custNumber:'1',custName:'1',linkName:'1',linkPhone:'1',linkTel:'1',currentLevel:'1',targetLevel:'1'}
		]
	}
});
Ext.define('ComplaintReportDataTest', {
	complaintReportStoreTest:null,
	//工单上报列表查询结果store
	getComplaintReportStore: function() {
		return this.complaintReportStoreTest;
	},
	//初始化工单上报列表查询结果store
	initComplaintReportStore: function(beforeLoadTransactionFn) {
		if(this.complaintReportStoreTest == null){
			this.complaintReportStoreTest = Ext.create('ComplaintReportListStoreTest');
		}
		return this.complaintReportStoreTest;
	}
});
