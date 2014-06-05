
/**
 * 客户工单查询结果 store
 */
Ext.define('CityStore',{
	extend:'Ext.data.Store'
	,fields:[
         {name:'fId',type:'number'} /*主键*/
         ,{name:'name',type:'string'} /*城市名称*/
         ,{name:'createUserId',type:'number'} /*创建人id*/
         ,{name:'createUserName',type:'string'} /*创建人*/
         ,{name:'createTime',type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null} /*创建时间*/
         ,{name:'lastModifyUserId',type:'number'} /*修改人id*/
         ,{name:'lastModifyUserName',type:'string'} /*修改人*/
         ,{name:'lastUpdateTime',type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null} /*修改时间*/
	]
	,pageSize:20
	,autoLoad:true
	,proxy:{
		type:'ajax'
		,api:{read:'searchCityList.action'}
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'cityList'
			,totalProperty:'totalCount'
		}
	}
});
