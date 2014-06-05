//获得当前用户
var CurrentUser = {};

/***********************************************************************/	
BackFreightData = function(){};
/**.
 * <p>
 * 通过运单号运单信息<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author zouming
 * @时间 2012-11-03
 */
BackFreightData.prototype.searchWaybillByNum = function(param,successFn,failureFn){
  var url = '../backfreight/searchWaybillByNum.action';
  DpUtil.requestAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 提交新增退运费信息<br/>
 * <p>
 * @param param  补救信息参数
 * @param successFn 新增成功后的方法
 * @param  failureFn新增失败后所回调方法
 * @author zouming
 * @时间 2012-11-03
 */
BackFreightData.prototype.submitBackFreight = function(param,successFn,failureFn){
	var url='../backfreight/submitBackFreight.action';
	DpUtil.requestJsonAjax(url,param,successFn,failureFn);
}

/**.
 * <p>
 * 根据退运费Id查询退运费信息<br/>
 * <p>
 * @param param  退运费Id
 * @param successFn 查询成功后的方法
 * @param  failureFn查询失败后所回调方法
 * @author zouming
 * @时间 2012-11-03
 */
BackFreightData.prototype.findBackFreightById = function(param,successFn,failureFn){
	var url='../backfreight/findBackFreightById.action';
	DpUtil.requestAjax(url,param,successFn,failureFn);
}

/**
*日期格式转换
*zouming
*/
BackFreightData.prototype.formatDate =function(value){
	if(value!=null){
		return Ext.Date.format(new Date(value),'Y-m-d H:i:s');
	}else{
		return null;
	}
}

/**
 * 是否Model
 */
Ext.define('BooleanModel',{
	extend:'Ext.data.Model',
	fields:['name',{name:'value',type:'boolean'}]
});

/**
 * 是否控件的store
 */
Ext.define('BooleanStore',{
	extend:'Ext.data.Store',
	model:'BooleanModel',
	data:[{'name':'是','value':true},
	      {'name':'否','value':false}]
});


/**
 * 附件信息 Store
 */
Ext.define('FileInfoStore',{
	extend:'Ext.data.Store',
	model:'FileInfoModel'
//	proxy:{
//		type:'memory'
//	}
});

Ext.define('FileInfoData', {
	
	//附件
	fileInfoStore : null,

	//附件store 
	getFileInfoStore:function(){
		if (this.fileInfoStore == null) {
			this.fileInfoStore = Ext.create('FileInfoStore'); 
		}
		return this.fileInfoStore;
	},
	//文件上传
	uploadFile:function(form,successFn,failFn){
		DpUtil.requestFileUploadAjax('../common/upload1.action',form,successFn,failFn);
	}
});


/****退运费查询界面gridStore*******/
//var queryBackFreightStore = Ext.create('Ext.data.Store',{
	Ext.define('QueryBackFreightStore',{
							extend:'Ext.data.Store',
							model:'QueryBackFreightModel',
							pageSize: 30,
							proxy:{
								type:'ajax',
								url:'../backfreight/searchBackFreightByCondition.action',
								actionMethods:'POST',
								reader:{
									type:'json',
									root:'backFreightList',
									totalProperty:'totalCount'
								}
							}
});

//描述：grid底部选择显示行数的Store
var valueStore = Ext.create("Ext.data.Store", {
					model :'GridComboModel',
					data : [{
						value:'10'
					},{
						value:'20'
					},{
						value:'30'
					}, {
						value:'40'
					}, {
						value:'50'
					}]
				});


//财务部门store
Ext.define('FinanceDeptStore',{
	extend:'Ext.data.Store',
	model:'FinanceDeptModel',
	proxy:{
		type:'ajax',
		url:'../servicerecovery/searchFinanceDept.action',
		actionMethods:'post',
		reader:{
			type:'json',
			root:'financeDeptList'
		}
	}
});



