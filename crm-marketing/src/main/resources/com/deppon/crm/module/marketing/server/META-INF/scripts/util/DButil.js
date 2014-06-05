DButil = function() {
};
/** 
 * @author 毛建强
 * @return 获取当前月份的月初
 */
DButil.getMonthStartDate =function(){
	var currentDate = new Date();
	currentDate.setDate(currentDate.getDate());
	currentDate.setMonth(currentDate.getMonth()-1, currentDate.getDate());
	currentDate.setHours(0);
	currentDate.setMinutes(0);
	currentDate.setSeconds(0);
	currentDate.setMilliseconds(0);
	return currentDate;
};

/**.
 * <p>
 * 格式化Date
 * <p>
 * @param value
 * @author 张登
 * @时间 2012-3-26
 */
DButil.renderDate = function(value) {
	if(!Ext.isEmpty(value)){
		return Ext.Date.format(new Date(value), 'Y-m-d');
	}else{
		return null;
	}
};

/**.
 * <p>
 * 格式化Date，显示时分秒
 * <p>
 * @param value
 * @author 肖红叶
 * @时间 2012-3-26
 */
DButil.renderDateDetail = function(value) {
	if(!Ext.isEmpty(value)){
		return Ext.Date.format(new Date(value), 'Y-m-d H:i:s A');
	}else{
		return null;
	}
};

/**.
 * <p>
 * 公共方法，显示MESSAGE信息<br/>
 * <p>
 * @param  meaage  要现实的信息
 * @author 张登
 * @时间 2012-3-26
 */
DButil.showMessage = function(meaage) {
	 Ext.MessageBox.alert(i18n('i18n.PotentialCustManagerView.message'),meaage);
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
 * @author 张登
 * @时间 2012-3-20
 */
DButil.rendererDictionary = function(value, dataDictionary) {
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

/**.
 * <p>
 * Ajax请求，参数为json格式的
 * <p>
 * @author 张登
 * @时间 2012-3-26
 */
DButil.requestJsonAjax = function(url,params,successFn,failFn)
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
/**.
 * <p>
 * Ajax请求，文件表单的请求
 * <p>
 * @author 张登
 * @时间 2012-3-26
 */
DButil.requestFileUploadAjax = function(url,form,successFn,failFn)
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
/**.
 * <p>
 * Ajax请求，字符串
 * <p>
 * @author 张登
 * @时间 2012-3-26
 */
DButil.requestAjax = function(url,params,successFn,failFn)
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


/**.
 * <p>
 * submit提交方式
 * <p>
 * @author 张登
 * @时间 2012-3-26
 */
/*DButil.requestAjax = function(fromPanel){
	if(fromPanel.form.isValid()){
  		btn.disable();  
  		fromPanel.form.doAction('submit',{
      		url:'',
      		method:'POST',
      		success:function(form,action){
      			showMessage(action.result.meaage);
      		},
      		failure:function(form,action){
      			showMessage(action.result.meaage);
         	}
     	});
 	}
};*/

//比较两个日期差额
DButil.compareTwoDate = function(startDate,endDate){
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

//比较一个日期范围是否在另外一个日期范围之内
DButil.compareTwoDateRange = function(startDate,endDate,compareStartDate,compareEndDate){
	var result = false;
	if(DButil.compareTwoDate(compareStartDate,startDate) >= 0 &&
			DButil.compareTwoDate(endDate,compareEndDate) >= 0){
		result = true;
	}
	return result;
}

/**
 *将grid中值转化为combo中值
 */
Ext.util.Format.comboRenderer = function (combo) {
    return function (value) {
        var record = combo.findRecord(combo.valueField, value);
        return record ? record.get(combo.displayField): combo.valueNotFoundText;
    };
};

/**
 * comboBox的change事件重写，使其达到可以删除，且只能选择下拉框里面的内容
 */
DButil.comboSelsct = function(combo){
		if(Ext.isEmpty(combo.getValue())){
			combo.setValue("");
	}
};
/**
 * 客户查询校验正则表达式
 */
DButil.linkWayLimit = function(regText){
	var mobil = /^1\d{10}$/;
	var tel = /^\d{3}[\d\*-/]{4,17}$/;
	var idCard = /^([\d]{15}|[\d]{18}|[\d]{17}X)$/;
	var defaultValue =  /^\d{11}$/;
	switch (regText) {
		case 'M':return mobil;break;
		case 'T':return tel;break;
		case 'I':return tel;break;
		default:return defaultValue;break;
　　}
}
/**
 * 
 */
DButil.trimString = function(value) {
	if(!Ext.isEmpty(value)){
		return value.trim();
	}
	return value;
};