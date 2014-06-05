Ext.define('TaskModel',{
	extend:'Ext.data.Model',
	fields:[{
			name:'jobSchedule.id',type:'string'	//1.触发器ID
		},{
			name:'jobSchedule.triggerName',type:'string'	//1.触发器名称
		},{
			name:'jobSchedule.triggerGroup',type:'string' //2.触发器分组
		},{
			name:'jobSchedule.triggerType',type:'number'	//3.触发器类型
		},{
			name:'jobSchedule.triggerExpression',type:'string'	//4.触发器表达式
		},{
			name:'jobSchedule.jobName',type:'string'	//5.任务名称
		},{
			name:'jobSchedule.jobGroup',type:'string'	//6.任务分组
		},{
			name:'jobSchedule.jobClass',type:'string'	//7.任务类
		},{
			name:'jobSchedule.jobData',type:'string'	//8.任务数据
		},{
			name:'jobSchedule.description',type:'string'	//9.任务描述
		},
		{name:'telephone',type:'string'},
		{name:'trigger.nextFireTime',type:'date',convert:DpUtil.changeLongToDate,defaultValue:null},/* 触发器下次执行时间*/
		{name:'trigger.previousFireTime',type:'date',convert:DpUtil.changeLongToDate,defaultValue:null},/*触发器上次执行时间*/
		{name:'triggerState',type:'number'}, /*触发器状态*/
		{name:'jobWarnning.warnType',type:'string'},/*预警时效*/
		{name:'jobWarnning.failTime',type:'number',defaultValue:1},/*统计时间 单位分钟*/
		{name:'jobWarnning.failCount',type:'number',defaultValue:1},/*统计次数*/
		{name:'jobWarnning.email',type:'string'},/*电子邮件*/
		{name:'jobWarnning.mobile',type:'string'}/*手机号码*/
	]
});

/**
 * 定时器状态集合
 * @type Array
 */
var triggerStateStore = {
		xtype:'store',
		fields: ['key', 'value'],
	    data : [
			{'key':'-1', 'value':'NONE'},
			{'key':'0', 'value':'NORMAL'},
			{'key':'1', 'value':'PAUSED'},
			{'key':'2', 'value':'COMPLETE'},
			{'key':'3', 'value':'ERROR'},
			{'key':'4', 'value':'BLOCKED'}
		],
		getStateValue:function(key){
			for(var i=0;i<this.data.length;i++){
				var temp = this.data[i];
				if(temp.key==key){return temp.value;}
			}
			return '';
		}
};

