/**
*部门工单管理共用Data层
*/
//判断是否导入测试数据
(function() {
	var complaintTaskDataTest = "../scripts/complaintreport/test/ComplaintTaskDataTest.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + complaintTaskDataTest + '"></script>');
	}
})();
//部门工单管理查询条件
Ext.define('SearchComplaintTaskConditionModel',{
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
//部门工单管理model
Ext.define('ComplaintTaskModel',{
	extend:'Ext.data.Model',
	fields:[
	     {name:'complaintId',type:'number'},
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
	     // 处理状态
	     {name:'prostatus',type:'string'},
	      //投诉级别
	     {name:'complevel',type:'string'},
	     // 上报时间
	     {name:'reporttime',type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null},
	     //反馈时限
	     {name:'feedtimelimit',type:'number'},
	     //处理时限/小时
	     {name:'processtimelimit',type:'number'},
	     //反馈部门
	     {name:'taskpartment',type:'string'},
	     //任务部门处理结果ID
	     {name:'resultId',type:'number'},
	     //任务属性
	     {name:'taskproperties',type:'string'},
	     //	//延时申请状态（0：申请延时 1：申请同意 2：申请拒绝）
	     {name:'delay',type:'string'},
	     {name:'stat',type:'string'},
	     {name:'isBack',type:'string'},
	     {name:'deptType',type:'string'},
	     //业务类型
	     {name:'businessModel',type:'string'}
	   ]
});
/**
 * 部门工单管理查询结果 store
 */
Ext.define('ComplaintTaskResultStore',{
	extend:'Ext.data.Store',
	id:'ComplaintTaskResultStore',
	model:'ComplaintTaskModel',
	pageSize:100,
	autoLoad:true,
	proxy:{
		type:'ajax',
		api:{
			read:'searchTaskComplaints.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'taskResultList',
			totalProperty:'totalCount'
		}
	}
});

Ext.define('ComplaintTaskData', {
	complaintTaskSearchResultStore:null,
	//部门工单管理查询结果 store
	getReportReturnPorcessSearchStore: function() {
		return this.complaintTaskSearchResultStore;
	},
	//部门工单管理查询结果 store 初始化
	initComplaintTaskSearchStore: function(beforeLoadTransactionFn) {
		if(this.complaintTaskSearchResultStore == null){
			if(beforeLoadTransactionFn != null){
				this.complaintTaskSearchResultStore = Ext.create('ComplaintTaskResultStore',{
					listeners:{
						beforeload:beforeLoadTransactionFn
					}
				});
			}else{
				this.complaintTaskSearchResultStore = Ext.create('ComplaintTaskResultStore');
			}
		}
		return this.complaintTaskSearchResultStore;
	}
});
