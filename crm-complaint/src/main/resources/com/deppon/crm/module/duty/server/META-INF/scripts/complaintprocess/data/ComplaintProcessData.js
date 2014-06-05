
//客户工单查询条件
Ext.define('SearchComplaintConditionModel',{
	extend:'Ext.data.Model'
	,fields:[
         // 客户工单查询名
         {name:'fieldName',type:'string'}
	     // 客户工单查询名
	     ,{name:'fieldValue',type:'string'}
	]
});
/**
 * 工单处理查询结果 store
 */
Ext.define('ProcessComplaintStore',{
	extend:'Ext.data.Store'
	,model:'ComplaintModel'
	,pageSize:10
	,autoLoad:true
	,proxy:{
		type:'ajax'
		,api:{read:'searchProcessComplaintList.action'}
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'complaintList'
			,totalProperty:'totalCount'
		}
	}
});

Ext.define('ProcessComplaintData', {
	processComplaintStore:null
	//客户工单查询结果 store
	,getProcessComplaintStore: function() {
		return this.processComplaintStore;
	}
	//客户工单查询结果  store 初始化
	,initProcessComplaintStore: function(beforeLoadTransactionFn) {
		if(this.complaintSearchResultStore == null){
			if(beforeLoadTransactionFn != null){
				this.processComplaintStore = Ext.create('ProcessComplaintStore',{
					listeners:{
						beforeload:beforeLoadTransactionFn
					}
				});
			}else{
				this.processComplaintStore = Ext.create('ProcessComplaintStore');
			}
		}
		return this.processComplaintStore;
	}
});


/**
 * 退回原因的查询结果
 * 这里做的是查询前填写参数
 */
Ext.define('FeedbackReasonData', {
	feedbackReasonStore:null
	//退回原因查询结果 store
	,getFeedbackReasonStore: function() {
		return this.feedbackReasonStore;
	}
	//退回原因查询结果  store 初始化
	,initFeedbackResonSearchStore: function(beforeLoadTransactionFn) {
		if(this.feedbackReasonStore == null){
			if(beforeLoadTransactionFn != null){
				this.feedbackReasonStore = Ext.create('feedbackReasonStore',{
					listeners:{
						beforeload:beforeLoadTransactionFn
					}
				});
			}else{
				this.feedbackReasonStore = Ext.create('feedbackReasonStore');
			}
		}
		return this.complaintSearchResultStore;
	}
});

