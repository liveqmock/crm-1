	/**
	 * 
	 * @description ：日志查询所用的相关model
	 * @author 华龙
	 * @version 1.0
	 * @date 2012-12-28 13:49
	 * 
	 */

//日志模型
Ext.define('QueryLogModel',{
	extend:'Ext.data.Model',
	fields:[
	    {	name:'id',type:'string'},//0.日志ID
		{	name:'instanceId',type:'string'},//1.实例名称
		{	name:'triggerName',type:'string'},//2.触发器名称
		{	name:'triggerGroup',type:'string'},//3.触发器分组
		{	name:'jobName',type:'string'},//4.任务名称
		{	name:'jobGroup',type:'string'},//5.任务分组
		{	name:'firedTime',type:'date',convert: DpUtil.changeLongToDate,defaultValue:null},//6.开始时间
		{	name:'jobAction',type:'string'},//7.任务状态
		{	name:'errorMessage',type:'string'}//8.异常信息
	]
});