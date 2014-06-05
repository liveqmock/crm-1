/**
*上报退回处理共用Data层
*/
//判断是否导入测试数据
(function() {
	var reportReturnProcessDataTest = "../scripts/complaintreport/test/ReportReturnPorcessDataTest.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + reportReturnProcessDataTest + '"></script>');
	}
})();
//上报退回处理查询条件
Ext.define('SearchComplaintReportConditionModel',{
	extend:'Ext.data.Model',
	fields:[
         // 处理编号
         {name:'dealbill'},
	     // 工单Id
	     {name:'fid',type:'number'},
	     // 凭证号
	     {name:'bill'},
	     // 电话
	     {name:'phone'},
	     {name:'start',type:'int'},
	     {name:'limit',type:'int'}
	 ]
});
//上报退回处理model
Ext.define('ReportReturnProcessModel',{
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
	     //投诉客户ID
	     {name:'complainid',type:'number'},
	     // 来电客户
	     {name:'complaincust',type:'string'},
	     // 客户等级
	     {name:'custlevel',type:'string'},
	     //客户类型
	     {name:'custtype',type:'string'}, 
	      //工单来源

	     //相关客户ID
	     {name:'relatcusid',type:'number'},
	     //相关客户
	     {name:'relatcus',type:'string'},
	     //相关客户等级
	     
	     //期望时限
	     {name:'timelimit',type:'number'},
	     //期望时限周期
	     {name:'tilimitcycle',type:'string'},
	     // 来电人
	     {name:'compman',type:'string'},
	     // 优先级别
	     {name:'pririty',type:'string'},
	     //上报内容
	     {name:'reportcontent',type:'string'},
	     //客户要求
	     {name:'custrequire',type:'string'},
	     //重复工单绑定码,即重复投拆绑定
	     {name:'rebindno',type:'string'},
	     // 联系电话
	     {name:'tel',type:'string'},
	     // 处理状态
	     {name:'prostatus',type:'string'},
	     // 解决状态
	     {name:'dealstatus',type:'string'},
	     // 上报人
	     {name:'reporter',type:'string'},
	     //上报人id
	     {name:'reporterid',type:'number'},
	     // 上报时间
	     {name:'reporttime',type: 'date',convert: DpUtil.changeLongToDate,defaultValue:new Date()},
	     //最终反馈
	     {name:'feedback',type:'string'},
	     //退回人
	     {name:'returnman',type:'string'},
	     //业务类型
	      {name:'businessModel',type:'string'},
	     //退回时间
	     {name:'returntime',type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null}
	   ]
});
/**
 * 上报退回处理查询结果 store
 */
Ext.define('ReportReturnProcessResultStore',{
	extend:'Ext.data.Store',
	id:'ReportReturnProcessResultStore',
	model:'ReportReturnProcessModel',
	pageSize:15,
	autoLoad:true,
	proxy:{
		type:'ajax',
		api:{
			read:'searchReturnedComplaints.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'complaintList',
			totalProperty:'totalCount'
		}
	}
});

Ext.define('ReportReturnPorcessData', {
	complaintReportSearchResultStore:null,
	//上报退回处理查询结果 store
	getReportReturnPorcessSearchStore: function() {
		return this.complaintReportSearchResultStore;
	},
	//上报退回处理查询结果 store 初始化
	initReportReturnProcessSearchStore: function(beforeLoadTransactionFn) {
		if(this.complaintReportSearchResultStore == null){
			if(beforeLoadTransactionFn != null){
				this.complaintReportSearchResultStore = Ext.create('ReportReturnProcessResultStore',{
					listeners:{
						beforeload:beforeLoadTransactionFn
					}
				});
			}else{
				this.complaintReportSearchResultStore = Ext.create('ReportReturnProcessResultStore');
			}
		}
		return this.complaintReportSearchResultStore;
	}
});
