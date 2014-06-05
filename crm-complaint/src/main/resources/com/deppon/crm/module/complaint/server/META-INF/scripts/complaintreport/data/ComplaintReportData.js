/**
*工单上报共用Data层
*/
//判断是否导入测试数据
(function() {
	var complaintReportDataTest = "../scripts/complaintreport/test/ComplaintReportDataTest.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + complaintReportDataTest + '"></script>');
	}
})();
//工单上报查询条件
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
//工单上报model
Ext.define('ComplaintReportModel',{
	extend:'Ext.data.Model',
	fields:[
	     {name:'fid',type:'number'},
	     // 凭证号
	     {name:'bill',type:'string'},
	     //业务类型
	     {name:'businessModel',type:'string'},
	     //处理编号
	     {name:'dealbill',type:'string'},
	     // 重复工单码
	     {name:'rebindno',type:'string'},
	     {name:'recomcode',type:'string'},
	     // 上报类型
	     {name:'reporttype',type:'string'},
	     //投诉客户ID
	     {name:'complainid',type:'string'},
	     // 来电客户
	     {name:'complaincust',type:'string'},
	     // 客户等级
	     {name:'custlevel',type:'string'},
	     //客户类型
	     {name:'custtype',type:'string'}, 
	      //工单来源
	     {name:'complaintsource',type:'string'},
	     //相关客户ID
	     {name:'relatcusid',type:'string'},
	     //相关客户
	     {name:'relatcus',type:'string'},
	     //相关客户等级
	     {name:'relatcuslevel',type:'string'},
	     //相关客户类型
	     {name:'relatcustype',type:'string'},
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
	     //0：发货人，1收货人
	     {name:'sendorreceive',type:'string'}
	   ]
});
/**
 * 工单上报查询结果 store
 */
Ext.define('ComplaintReportResultStore',{
	extend:'Ext.data.Store',
	id:'ComplaintReportResultStore',
	model:'ComplaintReportModel',
	pageSize:4,
	proxy:{
		type:'ajax',
		api:{
			read:'searchReportedComplaints.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'complaintList',
			totalProperty:'totalCount'
		}
	}
});

Ext.define('ComplaintReportData', {
	complaintReportSearchResultStore:null,
	//工单上报查询结果 store
	getComplaintReportSearchStore: function() {
		return this.complaintReportSearchResultStore;
	},
	//工单上报查询结果 store 初始化
	initComplaintReportSearchStore: function(beforeLoadTransactionFn) {
		if(this.complaintReportSearchResultStore == null){
			if(beforeLoadTransactionFn != null){
				this.complaintReportSearchResultStore = Ext.create('ComplaintReportResultStore',{
					listeners:{
						beforeload:beforeLoadTransactionFn
					}
				});
			}else{
				this.complaintReportSearchResultStore = Ext.create('ComplaintReportResultStore');
			}
		}
		return this.complaintReportSearchResultStore;
	},
	//提交工单
	submitComplaint:function(params,successFn,failFn,operate){
		var submitComplaintUrl='submitComplaint.action';
		if(operate=='returnProcess'){
			submitComplaintUrl='submitReturnComplaint.action';
		}
		DpUtil.requestJsonAjax(submitComplaintUrl,params,successFn,failFn);
	},
	//查询工单详细
	searchComplaintAndOther:function(params,successFn,failFn){
		var searchComplaintAndOther='searchComplaintAndOther.action';
		DpUtil.requestJsonAjax(searchComplaintAndOther,params,successFn,failFn);
	}
});
