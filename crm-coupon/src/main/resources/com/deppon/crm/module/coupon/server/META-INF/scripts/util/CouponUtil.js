CouponUtil = function() {
};

/**.
 * <p>
 * 格式化Date
 * <p>
 * @param value
 * @author 张登
 * @时间 2012-3-26
 */
CouponUtil.renderDate = function(value) {
	if(!Ext.isEmpty(value)){
		return Ext.Date.format(new Date(value), 'Y-m-d');
	}else{
		return null;
	}
};

CouponUtil.renderDateDetail = function(value) {
	if(!Ext.isEmpty(value)){
		return Ext.Date.format(new Date(value), 'Y-m-d H:i:s');
	}else{
		return null;
	}
};

/**.
 * <p>
 * 转换long类型为日期
 * <p>
 * @param value
 * @author 张登
 * @时间 2012-3-26
 */
CouponUtil.changeLongToDate = function(value) {
	if (value != null) {
		var date = new Date(value);
		return date;
	} else {
		return null;
	}
};

/**
 * 比较两个日期差额
 */

CouponUtil.compareTwoDate = function(startDate,endDate){
  var days, t, t2;
  var MinMilli = 1000 * 60;
  var HrMilli = MinMilli * 60;
  var DyMilli = HrMilli * 24;
  t = Date.parse(startDate);
  t2= Date.parse(endDate); 
  days = Math.round((t2-t)/ DyMilli);
  return days;
};

/**
 * 比较两个日期相差的月数
 */

CouponUtil.compareTwoDateByMonth = function(startDate,endDate,months){
  var result,compareDate,t1,t2,t;
  result = false;
  if(!Ext.isEmpty(startDate)&&!Ext.isEmpty(endDate)){
	  compareDate = Ext.Date.add(startDate,Ext.Date.MONTH,months);
	  t1 = Date.parse(compareDate);
	  t2 = Date.parse(endDate);
	  t = t2-t1;
	  if(t >= 0){
		 result = true; 
	  }
	  else{
		  result = false;
	  } 
  }
  return result;
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
CouponUtil.rendererDictionary = function(value, dataDictionary) {
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
CouponUtil.requestJsonAjax = function(url,params,successFn,failFn)
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
CouponUtil.requestFileUploadAjax = function(url,form,successFn,failFn)
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
CouponUtil.requestAjax = function(url,params,successFn,failFn)
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