/**.
 * <p>
 * 任务调度监控MODEL<br/>
 * <p>
 * @returns {OrderModel}
 */
Ext.define('SchedulerModel', {
    extend: 'Ext.data.Model',
    fields : [{
    	    //唯一标识
			name : 'id'
		},{
			//报表完成时间
			name : 'reportTime',
			 type: 'Date',
			 defaultValue:null,
			 convert: DButil.changeLongToDate
		},{
			//表名
			name: 'tableName'
	   },{
    	    //业务时间
			name : 'buzDate',
			 type: 'Date',
			 defaultValue:null,
			 convert: DButil.changeLongToDate
		},{
			name : 'crmTime',// CRM执行开始时间,
			type: 'Date',
			defaultValue:null,
			convert: DButil.changeLongToDate
		},{
			name : 'crmEndTime',// CRM执行结束时间
			type: 'Date',
			defaultValue:null,
			convert: DButil.changeLongToDate
		},{
			// 状态：0 or null crm未处理，1crm执行成功，2crm执行异常
			name: 'state'
	   },]
});

