
Ext.JSON.encodeDate = function(d) {
	return d.getTime();
};
 
String.prototype.replaceAll = function (AFindText,ARepText){
	raRegExp = new RegExp(AFindText,"g");
	return this.replace(raRegExp,ARepText);
};

DpUtil = function(){};

//判断是否为空
DpUtil.isEmpty = function(str){  
	 if((str === undefined) || (str == null) || (str.length == 0)) {
		 return true;  
	 }
	 else {
		 return false;  
	 }
};

//Ajax请求--json
DpUtil.requestJsonAjax = function(url,params,successFn,failFn)
{
	Ext.Ajax.request({
		url:url,
		jsonData:params,
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
DpUtil.requestFileUploadAjax = function(url,form,successFn,failFn)
{
	Ext.Ajax.request({
		url:url,
		form:form,
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
DpUtil.requestAjax = function(url,params,successFn,failFn)
{
	Ext.Ajax.request({
		url:url,
		params:params,
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

//Ajax请求--通用请求
DpUtil.ajaxRequest = function(options){
	options = Ext.apply({
        method : 'POST',
        success : function(response) {
            var result = Ext.decode(response.responseText);
            if(options.successFn && result.success){
            	options.successFn(result);
            }else if(options.failFn){
            	options.failFn(result);
            }
        },
        failure : function(response) {
        	if(options.failFn){
        		var result = Ext.decode(response.responseText);
            	options.failFn(result);
        	}
        }
    },options || {});
	Ext.Ajax.request(options);
};

/**
 * Ajax请求，限制action失效时间--非json字符串
 * xiaohongye
 * 20131209
 */
DpUtil.requestTimeLimitAjax = function(url,params,timeOutLimit,successFn,failFn){
	Ext.Ajax.request({
		url:url,
		params:params,
		//设置超时时间
		timeout: timeOutLimit,
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
DpUtil.changeLongToDate = function(value) {
	if (value != null) {
		var date = new Date(value);
		return date;
	} else {
		return null;
	}
};

DpUtil.renderDate = function(value) {
	if(value != null){
		return Ext.Date.format(new Date(value), 'Y-m-d');
	}else{
		return null;
	}
};

//数据字典中描述转换成值
DpUtil.changeDictionaryDescipToCode = function(store,descrip){
	var record = store.findRecord('codeDesc',descrip);
	if(!DpUtil.isEmpty(record)){
		return record.get('code');
	}else{
		return "";
	}
};

//数据字典值转换成描述
DpUtil.changeDictionaryCodeToDescrip = function(value, dataDictionary) {
	if (value != null && dataDictionary != null) {
		for ( var i = 0; i < dataDictionary.length; i++) {
			if (value == dataDictionary[i].code) {
				return dataDictionary[i].codeDesc;
			}
		}
	} else {
		return null;
	}
};

//boolean值转换成描述
DpUtil.changeBooleanToDescrip = function(value){
	if(value == false){
		return '否';
	}else if(value == true){
		return '是';
	}
	return null;
};

DpUtil.changeNullToEmptyString = function(value){
	if(value == null){
		return '';
	}else{
		return value
	}
};

//用于比较修改字段
DpUtil.compareRecordFieldIsChange = function(oldValue,newValue){
	if(oldValue == newValue){
		return false;
	}else if(DpUtil.changeNullToEmptyString(oldValue) == DpUtil.changeNullToEmptyString(newValue)){
		return false;
	}else{
		return true;
	}
};
//比较两个时间大小
DpUtil.compareTwoDate = function(startDate,endDate){
  var d, s, t, t2;
  var MinMilli = 1000 * 60;
  var HrMilli = MinMilli * 60;
  var DyMilli = HrMilli * 24;
  d = new Date();
  t = Date.parse(startDate);
  t2= Date.parse(endDate); 
  s = Math.round((t2-t)/ DyMilli);
  return s;
};


//定义等待信息
var processingMask = null;
Ext.onReady(function(){
	processingMask = new Ext.LoadMask(Ext.getBody(), {
        msg: '正在处理，请稍候.....',
        removeMask: true 
    });
});
