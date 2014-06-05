/**
 * 省份查询结果 Model
 */
Ext.define('ProvinceModel',{
	extend:'Ext.data.Model'
	,fields:[
	     {name:'fid',type:'number'} /*主键*/
         ,{name:'name',type:'string'} /*省份*/
         ,{name:'number',type:'string'} /*编码*/
         ,{name:'pinyin',type:'string'} /*拼音*/
         ,{name:'pyjm',type:'string'} /*拼音简称*/
         ,{name:'modifyUser',type:'string'} /*最后修改人*/
         ,{name:'lastModifyName',type:'string'} /*最后修改人名称*/
         ,{name:'modifyDate',type: 'date',convert:DpUtil.changeLongToDate,defaultValue:null} /*最后修改时间*/
	 ]
});

/**
 * 分页-省份查询结果 store
 */
Ext.define('ProvinceStore',{
	extend:'Ext.data.Store'
	,model:'ProvinceModel'
	,pageSize:20
	,autoLoad:true
	,proxy:{
		type:'ajax'
		,api:{read:'searchProvinceList_pager.action'}
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'provinceList'
			,totalProperty:'totalCount'
		}
	}
});


/**
 * 省份查询集合结果 store
 */
Ext.define('ProvinceListStore',{
	extend:'Ext.data.Store'
	,model:'ProvinceModel'
	,proxy:{
		type:'ajax'
		,api:{read:'searchProvinceList.action'}
		,actionMethods:'POST'
		,reader:{type:'json',root:'provinceList'}
	}
});


/**
 * 城市查询结果 Model
 */
Ext.define('CityModel',{
	extend:'Ext.data.Model'
	,fields:[
	     {name:'fid',type:'number'} /*主键*/
         ,{name:'name',type:'string'} /*城市名称*/
         ,{name:'number',type:'string'} /*编码-行政区划代码*/
         ,{name:'pinyin',type:'string'} /*拼音*/
         ,{name:'pyjm',type:'string'} /*拼音简称*/
         ,{name:'cityNumber',type:'string'} /*城市区号*/
         ,{name:'isDirCity',type:'number',defaultValue:0} /*是否直辖市*/
         ,{name:'province'} /*省份 对象*/
         ,{name:'provinceName',type:'string'} /*省份 名称*/
         ,{name:'provinceId',type:'number'} /*省份  ID*/
         ,{name:'modifyUser',type:'string'} /*最后修改人ID*/
         ,{name:'lastModifyName',type:'string'} /*最后修改人*/
         ,{name:'modifyDate',type: 'date',convert:DpUtil.changeLongToDate,defaultValue:null} /*最后修改时间*/
	 ]
});

/**
 * 分页-城市查询结果 store
 */
Ext.define('CityStore',{
	extend:'Ext.data.Store'
	,model:'CityModel'
	,pageSize:20
	,autoLoad:true
	,proxy:{
		type:'ajax'
		,api:{read:'searchCityList_pager.action'}
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'cityList'
			,totalProperty:'totalCount'
		}
	}
});

/**
 * 城市集合查询结果 store
 */
Ext.define('CityListStore',{
	extend:'Ext.data.Store'
	,model:'CityModel'
	,proxy:{
		type:'ajax'
		,api:{read:'searchCityListById.action'}
		,actionMethods:'POST'
		,reader:{type:'json',root:'cityList'}
	}
});


/**
 * 区县查询结果 Model
 */
Ext.define('AreaModel',{
	extend:'Ext.data.Model'
	,fields:[
	     {name:'fid',type:'number'} /*主键*/
         ,{name:'status',type:'string',defaultValue:'1'} /*状态*/
         ,{name:'name',type:'string'} /*区县名称*/
         ,{name:'number',type:'string'} /*编码-行政区划代码*/
         ,{name:'isCityLevel',type:'string',defaultValue:'0'} /*是否县级市*/
         ,{name:'cityName',type:'string'} /*所属城市名称*/
         ,{name:'cityId',type:'number'} /*所属城市ID*/
         ,{name:'provinceId',type:'number'} /*所属省份ID*/
         ,{name:'createUser',type:'string'} /*创建人*/
         ,{name:'createDate',type: 'date',convert:DpUtil.changeLongToDate,defaultValue:null} /*创建时间*/
         ,{name:'lastModifyName',type:'string'} /*最后修改人*/
         ,{name:'modifyDate',type: 'date',convert:DpUtil.changeLongToDate,defaultValue:null} /*最后修改时间*/
	 ]
});

/**
 * 区县查询结果 store
 */
Ext.define('AreaStore',{
	extend:'Ext.data.Store'
	,model:'AreaModel'
	,pageSize:20
	,autoLoad:true
	,proxy:{
		type:'ajax'
		,api:{read:'searchAreaList_pager.action'}
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'areaList'
			,totalProperty:'totalCount'
		}
	}
});
