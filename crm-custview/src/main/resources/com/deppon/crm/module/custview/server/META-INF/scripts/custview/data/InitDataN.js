var DataDictionary={};
var User = {};
/**
 * 初始化数据字典
 * @param keys 需要初始化数据字典的name
 * @author 张登
 * @时间 2012-3-29
 */
function initDataDictionary(keys){
	Ext.Ajax.request({
		url:'../common/queryAllDataDictionaryByKeys.action',
		async:false,
		jsonData:{'dataDictionaryKeys':keys},
		success:function(response){
			var result = Ext.decode(response.responseText);
			for (var i = 0; i < keys.length; i++) {
				DataDictionary[keys[i]]=result.dataDictionary[keys[i]];
			}
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
			Ext.MessageBox.alert("提示",result.message);
		}
	});
}

/**
 * 根据数据字典名称获取对应的store,如果没有则返回[],不影响整个页面的渲染
 * @param data
 * @param name
 * @returns
 * @author 张登
 * @时间 2012-3-29
 */
function getDataDictionaryByName(data,name){
	if(!Ext.isEmpty(data)){
		if(!Ext.isEmpty(data[name])){
			return Ext.create('Ext.data.Store', {
				fields:['code','codeDesc'],
			    data : data[name]
			});
		}
	}
	return [];
}

/**
 * 获取登录用户信息
 * @returns
 * @author 张登
 * @时间 2012-4-11
 */
function initUser(){
	Ext.Ajax.request({
		url : '../common/queryUserInfo.action',
		async:false,
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				User = result.user;
			}else{
				User = {};
			}
		}
	});
}