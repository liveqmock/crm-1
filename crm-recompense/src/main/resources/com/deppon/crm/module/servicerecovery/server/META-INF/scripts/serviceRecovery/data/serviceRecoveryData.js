//获得当前用户
var CurrentUser = {};

/***********************************************************************/
ServiceRecoveryData = function(){};
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
ServiceRecoveryData.prototype.searchWaybillByNum = function(param,successFn,failureFn){
  var url = '../servicerecovery/searchWayBillByNum.action';
  DpUtil.requestAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 提交新增服务补救信息<br/>
 * <p>
 * @param param  补救信息参数
 * @param successFn 新增成功后的方法
 * @param  failureFn新增失败后所回调方法
 * @author zouming
 * @时间 2012-11-03
 */
ServiceRecoveryData.prototype.addServiceRecoverySubmit = function(param,successFn,failureFn){
	var url='../servicerecovery/addServiceRecoverySubmit.action';
	DpUtil.requestJsonAjax(url,param,successFn,failureFn);
}

/**
*日期格式转换
*zouming
*/
ServiceRecoveryData.prototype.formatDate =function(value){
	if(value!=null){
		return Ext.Date.format(new Date(value),'Y-m-d H:i:s');
	}else{
		return null;
	}
}

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


Ext.define('FileInfoData', {

	//合同附件
	fileInfoStore : null,

	//合同附件store
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

/****服务补救查询界面gridStore*******/
//var queryServiceRecoveryStore = Ext.create('Ext.data.Store',{
	Ext.define('QueryServiceRecoveryStore',{
							extend:'Ext.data.Store',
							model:'QueryServiceRecoveryModel',
							pageSize: 30,
							proxy:{
								type:'ajax',
								url:'../servicerecovery/searchServiceRecoveryByCondition.action',
								actionMethods:'POST',
								reader:{
									type:'json',
									root:'serviceRecoveriesList',
									totalProperty:'totalCount'
								}
							}
});

//描述：grid底部选择显示行数的Store
var valueStore = Ext.create("Ext.data.Store", {
					model :'GridComboModel',
					data : [{
						value:'10'
					}, {
						value:'20'
					}, {
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
//运输类型combo的model
Ext.define('SearchFormTranTypeStore',{
	extend:'Ext.data.Store',
	fields:['code','codeDesc'],
	data:[{
		code:'汽运',codeDesc:'汽运'
	},{
		code:'空运',codeDesc:'空运'
	},{
		code:'快递',codeDesc:'快递'
	},{
		code:'全部',codeDesc:'全部'
	}]
});

//判断是否导入测试数据
//(function() {
//	var contractBasicDataTest = "../scripts/membercust/test/ContractBasicDataTest.js";
//    if(CONFIG.get('TEST'))
//	{
//		document.write('<script type="text/javascript" src="' + contractBasicDataTest + '"></script>');
//	}
//})();
