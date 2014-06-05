/**
*工单回访管理共用Data层
*/
//判断是否导入测试数据
(function() {
	var complaintVerifyTaskDataTest = "../scripts/complaintverify/test/ComplaintVerifyTaskDataTest.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + complaintVerifyTaskDataTest + '"></script>');
	}
})();
//工单回访管理查询条件
Ext.define('complaintVerifyTaskConditionModel',{
	extend:'Ext.data.Model',
	fields:[
         // 部门id
         {name:'resultId'},
	     // 工单Id
	     {name:'fid'},
	     //退回原因 
	     {name:'feedbackContent'},
	     //反馈登记码
	     {name:'resolveCode'},	     
	     //延时申请
	     {name:'applyCode'}
	 ]
});
//工单回访管理model
Ext.define('ComplaintVerifyTaskModel',{
	extend:'Ext.data.Model',
	fields:[
	     {name:'fid',type:'number'},
	     // 凭证号
	     {name:'bill',type:'string'},
	     //处理编号
	     {name:'dealbill',type:'string'},
	     // 重复工单码
	     {name:'rebindno',type:'string'},
	     {name:'recomcode',type:'string'},	     
	     // 上报类型
	     {name:'reporttype',type:'string'},
	     // 来电人
	     {name:'compman',type:'string'},
	      // 优先级别
	     {name:'pririty',type:'string'},
	     //投诉级别
	     {name:'complevel',type:'string'},
	     // 处理状态
	     {name:'prostatus',type:'string'},
	     // 解决状态
	     {name:'dealstatus',type:'string'},
	     //是否超时
		 {name:'ifovertime',type:'string'},
		 //是否到期
		  {name:'iflimittime',type:'string'},
		  //处理人
		  {name:'dealman',type:'string'},
		  //处理时间
		  {name:'dealtime',type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null},
		  //客户满意度
		  {name:'cussatisfaction',type:'string'},
		     // 联系电话
		     {name:'tel',type:'string'},
		     //客户等级
		     {name:'custlevel',type:'string'},
		     //业务类型
		     {name:'businessModel',type:'string'}
	   ]
});

//退回原因/反馈记录
Ext.define('FeedBackReasionModel',{
	extend:'Ext.data.Model',
	fields:[
	     {name:'wailbillcontent',type:'string'}
	   ]
});

/**
 * 工单回访管理查询结果 store
 */
Ext.define('ComplaintVerifyTaskResultStore',{
	extend:'Ext.data.Store',
	id:'ComplaintVerifyTaskResultStore',
	model:'ComplaintVerifyTaskModel',
	pageSize:15,
	autoLoad:true,
	proxy:{
		type:'ajax',
		api:{
			read:'searchVerifyComplaints.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'complaintList',
			totalProperty:'totalCount'
		}
	}
});



Ext.define('ComplaintVerifyTaskData', {
	complaintVerifyTaskSearchResultStore:null,
	//工单回访管理查询结果 store
	getComplaintVerifyTaskSearchStore: function() {
		return this.complaintVerifyTaskSearchResultStore;
	},
	//工单回访管理查询结果 store 初始化
	initComplaintVerifyTaskSearchStore: function(beforeLoadTransactionFn) {
		if(this.complaintVerifyTaskSearchResultStore == null){
			if(beforeLoadTransactionFn != null){
				this.complaintVerifyTaskSearchResultStore = Ext.create('ComplaintVerifyTaskResultStore',{
					listeners:{
						beforeload:beforeLoadTransactionFn
					}
				});
			}else{
				this.complaintVerifyTaskSearchResultStore = Ext.create('ComplaintVerifyTaskResultStore');
			}
		}
		return this.complaintVerifyTaskSearchResultStore;
	}
});
