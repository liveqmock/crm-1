//客户工单查询 结果 model
Ext.define('ComplaintSearchResultStoreTest',{
	extend:'Ext.data.Store',
	model:'ComplaintResultModel',
	autoLoad:true,
	proxy:{
		type:'memory',
		data:[
			{bill:'1',dealbill:'hpf',recomcode:'12',reporttype:'hpf',compman:'12',pririty:'hpf',complevel:'12',prostatus:'hpf',eedbacktime:'12',timelimit:'hpf',timelimit:'12',recomcode:'hpf',dealstatus:'fssf'}
		    ,{bill:'1',dealbill:'hpf',recomcode:'12',reporttype:'hpf',compman:'12',pririty:'hpf',complevel:'12',prostatus:'hpf',eedbacktime:'12',timelimit:'hpf',timelimit:'12',recomcode:'hpf',dealstatus:'fssf'}
		    ,{bill:'1',dealbill:'hpf',recomcode:'12',reporttype:'hpf',compman:'12',pririty:'hpf',complevel:'12',prostatus:'hpf',eedbacktime:'12',timelimit:'hpf',timelimit:'12',recomcode:'hpf',dealstatus:'fssf'}
		    ,{bill:'1',dealbill:'hpf',recomcode:'12',reporttype:'hpf',compman:'12',pririty:'hpf',complevel:'12',prostatus:'hpf',eedbacktime:'12',timelimit:'hpf',timelimit:'12',recomcode:'hpf',dealstatus:'fssf'}
		    ,{bill:'1',dealbill:'hpf',recomcode:'12',reporttype:'hpf',compman:'12',pririty:'hpf',complevel:'12',prostatus:'hpf',eedbacktime:'12',timelimit:'hpf',timelimit:'12',recomcode:'hpf',dealstatus:'fssf'}
		    ,{bill:'1',dealbill:'hpf',recomcode:'12',reporttype:'hpf',compman:'12',pririty:'hpf',complevel:'12',prostatus:'hpf',eedbacktime:'12',timelimit:'hpf',timelimit:'12',recomcode:'hpf',dealstatus:'fssf'}
		    ,{bill:'1',dealbill:'hpf',recomcode:'12',reporttype:'hpf',compman:'12',pririty:'hpf',complevel:'12',prostatus:'hpf',eedbacktime:'12',timelimit:'hpf',timelimit:'12',recomcode:'hpf',dealstatus:'fssf'}
		    ,{bill:'1',dealbill:'hpf',recomcode:'12',reporttype:'hpf',compman:'12',pririty:'hpf',complevel:'12',prostatus:'hpf',eedbacktime:'12',timelimit:'hpf',timelimit:'12',recomcode:'hpf',dealstatus:'fssf'}
		    ,{bill:'1',dealbill:'hpf',recomcode:'12',reporttype:'hpf',compman:'12',pririty:'hpf',complevel:'12',prostatus:'hpf',eedbacktime:'12',timelimit:'hpf',timelimit:'12',recomcode:'hpf',dealstatus:'fssf'}
		    ,{bill:'1',dealbill:'hpf',recomcode:'12',reporttype:'hpf',compman:'12',pririty:'hpf',complevel:'12',prostatus:'hpf',eedbacktime:'12',timelimit:'hpf',timelimit:'12',recomcode:'hpf',dealstatus:'fssf'}
		    ,{bill:'1',dealbill:'hpf',recomcode:'12',reporttype:'hpf',compman:'12',pririty:'hpf',complevel:'12',prostatus:'hpf',eedbacktime:'12',timelimit:'hpf',timelimit:'12',recomcode:'hpf',dealstatus:'fssf'}
		    ,{bill:'1',dealbill:'hpf',recomcode:'12',reporttype:'hpf',compman:'12',pririty:'hpf',complevel:'12',prostatus:'hpf',eedbacktime:'12',timelimit:'hpf',timelimit:'12',recomcode:'hpf',dealstatus:'fssf'}
		    ,{bill:'1',dealbill:'hpf',recomcode:'12',reporttype:'hpf',compman:'12',pririty:'hpf',complevel:'12',prostatus:'hpf',eedbacktime:'12',timelimit:'hpf',timelimit:'12',recomcode:'hpf',dealstatus:'fssf'}
		    ,{bill:'1',dealbill:'hpf',recomcode:'12',reporttype:'hpf',compman:'12',pririty:'hpf',complevel:'12',prostatus:'hpf',eedbacktime:'12',timelimit:'hpf',timelimit:'12',recomcode:'hpf',dealstatus:'fssf'}
		    ,{bill:'1',dealbill:'hpf',recomcode:'12',reporttype:'hpf',compman:'12',pririty:'hpf',complevel:'12',prostatus:'hpf',eedbacktime:'12',timelimit:'hpf',timelimit:'12',recomcode:'hpf',dealstatus:'fssf'}
		    
		]
	}
});
Ext.define('ComplaintDataTest', {
	complaintStoreTest:null
	//客户工单查询结果store
	,getComplaintStore: function() {
		return this.complaintStoreTest;
	}
	//客户工单查询结果 初始化store
	,initComplaintSearchStore: function(beforeLoadTransactionFn) {
		if(this.complaintStoreTest == null){
			this.complaintStoreTest = Ext.create('ComplaintSearchResultStoreTest');
		}
		return this.complaintStoreTest;
	}
});
