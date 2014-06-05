DButil = function() {
};

/**
 * 修改date对象数据的JSON提交方式
 */
Ext.JSON.encodeDate = function(date) {
	return date.getTime();
};


/**.
 * <p>
 * 公共方法，通过storeId和model创建STORE<br/>
 * <p>
 * @param  storeId  
 * @param  model   store所用到的model名
 * @param  fields   store所用到的fields
 * @returns store  返回创建的store
 * @author 张斌
 * @时间 2012-3-12
 */
DButil.getStore = function(storeId,model,fields,data) {
	var store = Ext.data.StoreManager.lookup(storeId);
	if(Ext.isEmpty(data)){
		data = [];
	}
	if(!Ext.isEmpty(model)){
		if(store===undefined){
			store = Ext.create('Ext.data.Store', {
				storeId:storeId,
			    model:model,
			    data:data
		     });
		}
	}
	if(!Ext.isEmpty(fields)){
		if(store===undefined){
			store = Ext.create('Ext.data.Store', {
				storeId:storeId,
			    fields:fields,
			    data:data
		     });
		}
	}
	return store;
};
//Ajax请求--json
DButil.requestJsonAjax = function(url,params,successFn,failFn)
{
	Ext.Ajax.request({
		url:url,
		jsonData:params,
		timeout:300000,
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				successFn(result);
			}else{
				failFn(result);
			}
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
			failFn(result);
		}
	});
};

//Ajax请求--文件表单的请求
DButil.requestFileUploadAjax = function(url,form,successFn,failFn)
{
	Ext.Ajax.request({
		url:url,
		form:form,
		timeout:300000,
		isUpload:true,
		success:function(form, action){
			var result = action.result;
			if(result.success){
				successFn(result);
			}else{
				failFn(result);
			}
		},
		failure:function(form, action){
			var result = action.result;
			failFn(result);
		}
	});
};

//Ajax请求--非json字符串
DButil.requestAjax = function(url,params,successFn,failFn)
{
	Ext.Ajax.request({
		url:url,
		params:params,
		timeout:300000,
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				successFn(result);
			}else{
				failFn(result);
			}
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
			failFn(result);
		}
	});
};
//转换long类型为日期
DButil.changeLongToDate = function(value) {
	if (value != null) {
		var date = new Date(value);
		return date;
	} else {
		return null;
	}
};

/**.
 * <p>
 * 将业务字典的code转换成描述codeDesc<br/>
 * <p>
 * @param value  所要转换的值
 * @param dataDictionary 所对应业务字典
 * @author 张斌
 * @时间 2012-3-20
 */
DButil.changeDictionaryCodeToDescrip = function(value, data) {
	var ifChange = false;
	if (value != null && data != null) {
		for ( var i = 0; i < data.length; i++) {
			var code = Ext.isEmpty(data[i].code)?data[i].get('code'):data[i].code;
			if (value == code) {
				ifChange = true;
			     return Ext.isEmpty(data[i].codeDesc)?data[i].get('codeDesc'):data[i].codeDesc;
			}
		}
		if(!ifChange){
			return value;
		}
	} else {
	   return value;
	}
};

/**.
 * <p>
 * 将true转换为“是”，false转换为“否”<br/>
 * <p>
 * @param value  所要转换的值
 * @author 张斌
 * @时间 2012-3-20
 */
DButil.changeTrueAndFalse = function(value) {
	if(value=='true'){
		return i18n('i18n.order.yes');
	}else if(value=='false') {
		return i18n('i18n.order.no');
	}else if (value==true) {
		return i18n('i18n.order.yes');
	} else if(value==false) {
		return i18n('i18n.order.no');
	}else{
		return null;
	}
};



/**.
 * <p>
 * 将日期的格式转换为yy-MM-dd hh:mm,将true/false转换为是/否<br/>
 * <p>
 * @param record 是一个OrderModel
 * @author 张斌
 * @时间 2012-3-23
 */
DButil.changeRecord = function(record){
	var createDate = null;
	var startTime = null;
	var endTime = null;
	if(!Ext.isEmpty(record.get(ORDERNAME.get('createDate')))){
		createDate = record.get(ORDERNAME.get('createDate')).format('yyyy-MM-dd hh:mm');
	}
	if(!Ext.isEmpty(record.get(ORDERNAME.get('consignorCargoTime')))){
		startTime = record.get(ORDERNAME.get('consignorCargoTime')).format('yyyy-MM-dd hh:mm');
	}
	if(!Ext.isEmpty(record.get(ORDERNAME.get('consignorToCargoTime')))){
		endTime = record.get(ORDERNAME.get('consignorToCargoTime')).format('yyyy-MM-dd hh:mm');
	}
	record.set(ORDERNAME.get('consignorIsCargo'),DButil.changeTrueAndFalse(record.get(ORDERNAME.get('consignorIsCargo'))));
	record.set(ORDERNAME.get('createDate'),createDate);
	record.set(ORDERNAME.get('consignorCargoTime'),startTime);
	record.set(ORDERNAME.get('consignorToCargoTime'),endTime);
	return record;
};

/**.
 * <p>
 * JS日期的format方法<br/>
 * <p>
 * @param format 日期格式
 * @author 张斌
 * @时间 2012-3-23
 */
Date.prototype.format = function(format){
	if(Ext.isEmpty(this)){
		return null;
	}
	var o = {
		"M+" : this.getMonth()+1, //month
		"d+" : this.getDate(), //day
		"h+" : this.getHours(), //hour
		"m+" : this.getMinutes(), //minute
		"s+" : this.getSeconds(), //second
		"q+" : Math.floor((this.getMonth()+3)/3), //quarter
		"S" : this.getMilliseconds() //millisecond
	};

	if(/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	};

	for(var k in o) {
		if(new RegExp("("+ k +")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
		}
	};
	return format;
} ;

DButil.initUser=function(){
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
/**.
 * <p>
 * combox如果没有选择则置为空<br/>
 * <p>
 * @author 张斌
 * @时间 2012-6-21
 */
DButil.notSelectSetEmpty = function(combo){
	if(Ext.isEmpty(combo.getValue())){
		combo.setValue("");
    }
}