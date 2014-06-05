

/**
 * 同步日志Model
 */
Ext.define('SynchronizeLogInfoModel',{
	extend:'Ext.data.Model',
	fields:[
	   {name:'id'},
	   //TableName
       {name:'tableName'},
       //KeyWord
       {name:'keyWord'},
	   //HandleType
	   {name:'handleType'},
	   //Status
	   {name:'synStatus'},
	   //同步开始时间
	   {name:'synStartTime'},
	   //同步结束时间
	   {name:'synFinishTime'},
       //SynTime
       {name:'synTime'}
	  ]
});

Ext.define('SynchronizeLogData',{
	synLogResultStore:null,
	getSynLogResultStore:function(){
		return this.synLogResultStore;
	},
	initSynLogResultStore:function(beforeLoadTransactionFn){
		if(this.synLogResultStore == null){
			if(beforeLoadTransactionFn != null){
				this.synLogResultStore =Ext.create('SynchronizeLogListStore',{
					listeners:{
						beforeload:beforeLoadTransactionFn
					}
				});
			}else{
				this.synLogResultStore = Ext.create('SynchronizeLogListStore');
			}
		}
		return this.synLogResultStore;
	},
	//Exception修改
	updateSynLog:function(params,successFn,failFn){
		var actionUrl = '../logmoniting/updateSynchronizeLog.action';
		DpUtil.requestJsonAjax(actionUrl,params,successFn,failFn);
	}
});
/**
 * 同步日志查询结果列表Store(通过searchSynchronizeLogList.action查询)
 */
Ext.define('SynchronizeLogListStore',{
	extend:'Ext.data.Store',
	model:'SynchronizeLogInfoModel',
	pageSize:25,
	proxy:{
		type:'ajax',
		api:{
			read:'searchSynchronizeLogList.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'synchronizeLogList',
			totalProperty:'totalCount'
		}
	}
});