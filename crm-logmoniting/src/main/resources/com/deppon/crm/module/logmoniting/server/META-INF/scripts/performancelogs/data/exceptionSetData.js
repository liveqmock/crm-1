/**
 * 异常信息查询条件的model
 */
Ext.define('SearchExceptionSetModel',{
	extend:'Ext.data.Model',
	fields:[
       //模块名称
       {name:'moduleName'},
       //异常编码
       {name:'errorCode'},
	   //异常信息
	   {name:'exceptionInfo'}
	   
       ]
});

/**
 * 异常信息查询结果列表的Model
 */
Ext.define('ExceptionResultModel',{
	extend:'Ext.data.Model',
	fields:[
	   {name:'id'},
       //模块名称
       {name:'moduleName'},
       //异常编码
       {name:'errorCode'},
	   //异常信息
	   {name:'exceptionInfo'},
	   //创建人
	   {name:'createUser'},
       //创建时间
       {name:'createTime'}
	  ]
});

Ext.define('ExceptionBasicData',{
	exceptionResultStore:null,
	getExceptionResultStore:function(){
		return this.exceptionResultStore;
	},
	initExceptionResultStore:function(beforeLoadTransactionFn){
		if(this.exceptionResultStore == null){
			if(beforeLoadTransactionFn != null){
				this.exceptionResultStore =Ext.create('ExceptionResultListStore',{
					listeners:{
						beforeload:beforeLoadTransactionFn
					}
				});
			}else{
				this.exceptionResultStore = Ext.create('ExceptionResultListStore');
			}
		}
		return this.exceptionResultStore;
	},
	//Exception新增
	addExceptionErrorCode:function(params,successFn,failFn){
		var actionUrl = '../logmoniting/addExceptionErrorCode.action';
		DpUtil.requestJsonAjax(actionUrl,params,successFn,failFn);
	},
	//Exception删除
	delExceptionErrorCode:function(params,successFn,failFn){
		var actionUrl = '../logmoniting/delExceptionErrorCode.action';
		DpUtil.requestJsonAjax(actionUrl,params,successFn,failFn);
	},
	//Exception修改
	updateExceptionErrorCode:function(params,successFn,failFn){
		var actionUrl = '../logmoniting/updateExceptionErrorCode.action';
		DpUtil.requestJsonAjax(actionUrl,params,successFn,failFn);
	}
});
/**
 * 异常查询结果列表Store(通过searchExceptionList.action查询)
 */
Ext.define('ExceptionResultListStore',{
	extend:'Ext.data.Store',
	model:'ExceptionResultModel',
	pageSize:25,
	proxy:{
		type:'ajax',
		api:{
			read:'searchExceptionList.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'exceptionResultList',
			totalProperty:'totalCount'
		}
	}
});