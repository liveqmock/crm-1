var DataDictionary={};
/**
 * 初始化数据字典
 * @param keys 需要初始化数据字典的name
 * @author 张登
 * @时间 2012-3-29
 */
function initDataDictionary(keys){
	if (!Ext.isEmpty(keys)) {
		Ext.Ajax.request({
			url:'../common/queryAllDataDictionaryByKeys.action',
			async:false,
			jsonData:{'dataDictionaryKeys':keys},
			success:function(response){
				var result = Ext.decode(response.responseText);
				for (var i = 0; i < keys.length; i++) {
					DataDictionary[keys[i]]=result.dataDictionary[keys[i]];
				}
				//DataDictionary=result.dataDictionary;
			},
			failure:function(response){
				var result = Ext.decode(response.responseText);
//				Ext.MessageBox.alert("提示",result.message);
				MessageUtil.showMessage(result.message);
			}
		});
	};
};


/**
 * 根据数据字典名称获取对应的store,如果没有则返回[],不影响整个页面的渲染
 * @param data
 * @param name
 * @param id 如果想要通过store id 查询store的话就传id,否则可以不用传
 * @returns
 * @author 张登
 * @时间 2012-3-29
 */
function getDataDictionaryByName(data,name,id){
	if(!Ext.isEmpty(data)){
		if(!Ext.isEmpty(data[name])){
			var json={
				fields:['code','codeDesc'],
			    data : data[name]
			};
			if(!Ext.isEmpty(id)){
				json["id"]=id;
			}
			return Ext.create('Ext.data.Store',json);
		}
	}
	return [];
};

/**
 * 将业务字典的code转换成描述codeDesc<br/>使用方法rendererDictionary(value,DataDictionary.PLAN_STATUS);
 * @param value  所要转换的值
 * @param dataDictionary 所对应业务字典
 * @author 张登
 * @时间 2012-3-20
 */
function rendererDictionary(value, dataDictionary) {
	if (!Ext.isEmpty(value) && !Ext.isEmpty(dataDictionary)) {
		for ( var i = 0; i < dataDictionary.length; i++) {
			if (value == dataDictionary[i].code) {
			     return dataDictionary[i].codeDesc;
			}
		}
	} else {
	   return null;
	}
};

/**
 * 初始化部门用户信息
 * @author 张登
 * @return User.loginName(登录帐号),User.userId(用户Id),User.empCode(用户编号),User.empName(用户名称)
 * @return User.deptId(部门Id),User.deptCode(部门编号),User.deptName(部门名称)
 * @return User.roleids(用户所拥有的角色信息ID集合),User.deptids(用户所拥有的部门信息ID集合),注：此两项为数组
 * @时间 2012-3-29
 */
var User={};
function initDeptAndUserInfo(isHongKong){
	var url=null;
	if(Ext.isEmpty(isHongKong)){
		url='../common/queryUserInfo.action';
	}else{
		url='../common/queryUserInfo.action?isHongKong='+isHongKong;
	}
	Ext.Ajax.request({
		url : url,
		async:false,
		success:function(response){
			var result = Ext.decode(response.responseText);
			User=result.user;
			return User;
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
//			Ext.MessageBox.alert("提示",result.message);
			MessageUtil.showMessage(result.message);
		}
	});
};

