/**
*会员共用Data层
*/
//判断是否导入测试数据
(function() {
	var complaintDataTest = "../scripts/complaint/complaintsearch/test/ComplainSearchDataTest.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + complaintDataTest + '"></script>');
	}
})(); 

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
 * 客户工单查询结果 store
 */
Ext.define('ComplaintSearchResultStore',{
	extend:'Ext.data.Store'
	,model:'ComplaintModel'
	,pageSize:10
//	,autoLoad:true
	,proxy:{
		type:'ajax'
		,api:{read:'searchComplaintList.action'}
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'complaintList'
			,totalProperty:'totalCount'
		}
	}
});

Ext.define('ComplaintData', {
	complaintSearchResultStore:null
	//客户工单查询结果 store
	,getComplaintSearchStore: function() {
		return this.complaintSearchResultStore;
	}
	//客户工单查询结果  store 初始化
	,initComplaintSearchStore: function(beforeLoadTransactionFn) {
		if(this.complaintSearchResultStore == null){
			if(beforeLoadTransactionFn != null){
				this.complaintSearchResultStore = Ext.create('ComplaintSearchResultStore',{
					listeners:{
						beforeload:beforeLoadTransactionFn
						,load:function(store,records,successful){
							if(!store || store.getCount()==0){
								MessageUtil.showMessage(i18n('i18n.complaint.msg.no_select_data'));
							}
						}
					}
				});
			}else{
				this.complaintSearchResultStore = Ext.create('ComplaintSearchResultStore');
			}
		}
		return this.complaintSearchResultStore;
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

