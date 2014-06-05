DutyUtil = function(){};
if(Ext.Date){
    Ext.Date.monthNames = [
       "一月",
       "二月",
       "三月",
       "四月", 
       "五月",
       "六月",
       "七月",
       "八月",
       "九月",
       "十月",
       "十一月",
       "十二月"
    ];

    Ext.Date.dayNames = [
       "日",
       "一",
       "二",
       "三",
       "四",
       "五",
       "六"
    ];

    Ext.Date.formatCodes.a = "(this.getHours() < 12 ? '上午' : '下午')";
    Ext.Date.formatCodes.A = "(this.getHours() < 12 ? '上午' : '下午')";
}

Ext.JSON.encodeDate = function(d) {
	return d.getTime();
};


DutyUtil.renderDateTime = function(value) {
	if(value != null){
		return Ext.Date.format(new Date(value), 'Y-m-d H:i:s');
	}else{
		return null;
	}
};

DutyUtil.renderDateFormat = function(value, format) {
	if(value != null){
		return Ext.Date.format(new Date(value), format);
	}else{
		return null;
	}
};

/**
 * 校验反馈登记是否过期（true：未过期，false：过期）
 */
DutyUtil.validateFeedbackOverTime = function (resultId){
	var params={'resultId':resultId};
	var result=false;
	
	Ext.Ajax.request({
		  url : 'validateFeedbackOverTime.action',
		  jsonData:params,
		  async:false,
		  success : function(response){
					var resp = Ext.decode(response.responseText);
					if(resp.success){
						result=true;
					}else{
						MessageUtil.showErrorMes(resp.message);
						result=false;				
					}			  
			      },
		  failure :function(){
				MessageUtil.showErrorMes(resp.message);
				result=false;			  
		  }

	   }); 	
	
	return result;
}

/**
 * 校验接入是否过期(true表示未过期，false表示过期)
 */
DutyUtil.validateAccessExpired = function (complaintId){
	var params={'complaintId':complaintId};
	var result=false;
	
	Ext.Ajax.request({
		  url : 'validateComplaintAccessExpired.action',
		  jsonData:params,
		  async:false,
		  success : function(response){
					var resp = Ext.decode(response.responseText);
					if(resp.success){
						result=true;
					}else{
						MessageUtil.showErrorMes(resp.message);
						result=false;				
					}			  
			      },
		  failure :function(){
				MessageUtil.showErrorMes(resp.message);
				result=false;			  
		  }

	   }); 	
	
	return result;
}

DutyUtil.requestJsonAjax = function(url,params,successFn,failFn)
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
DutyUtil.requestFileUploadAjax = function(url,form,successFn,failFn)
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
DutyUtil.requestAjax = function(url,params,successFn,failFn)
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

/**
 * comboBox的change事件重写，使其达到可以删除，且只能选择下拉框里面的内容
 */
DutyUtil.comboSelsct = function(combo){
		if(Ext.isEmpty(combo.getValue())){
			combo.setValue("");
	}
};

/**
 * 比较两个日期差额
 */

DutyUtil.compareTwoDate = function(startDate,endDate){
  var days, t, t2;
  var MinMilli = 1000 * 60;
  var HrMilli = MinMilli * 60;
  var DyMilli = HrMilli * 24;
  t = Date.parse(startDate);
  t2= Date.parse(endDate); 
  return (t2-t)/ DyMilli;
};

//定义等待信息
var processingMask = null;
Ext.onReady(function(){
	processingMask = new Ext.LoadMask(Ext.getBody(), {
        msg: '正在处理，请稍候.....',
        removeMask: true 
    });
});

/**.
 * <p>
 * 将业务字典的code转换成描述codeDesc<br/>
 * <p>
 * @param value  所要转换的值
 * @param dataDictionary 所对应业务字典
 * @author 张登
 * @时间 2012-3-20
 */
DutyUtil.rendererDictionary = function(value, dataDictionary) {
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

