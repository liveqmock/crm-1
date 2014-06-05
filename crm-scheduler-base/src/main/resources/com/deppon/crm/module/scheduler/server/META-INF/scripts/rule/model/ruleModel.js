/**
 * 
 * <p>
 * Description:规则管理 model<br />
 * </p>
 * @title PlanningModel
 * @author 侯斌飞
 * @version 0.1 2012-12-26
 */
Ext.define('PlanningModel',{
	extend:'Ext.data.Model'
	,fields:[
	     {name:'id',type:'string'},/*编号*/
	     {name:'instanceId',type:'string'},/*实例组*/
	     {name:'scopeType',type:'string'},/*类型*/
	     {name:'scopeName',type:'string'},/*目标*/
	     {name:'accessRule',type:'string'}/*规则*/
	   ]
});