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

DpUtil.renderDateTime = function(value) {
	if(value != null){
		return Ext.Date.format(new Date(value), 'Y-m-d H:i:s');
	}else{
		return null;
	}
};

DpUtil.renderDateFormat = function(value, format) {
	if(value != null){
		return Ext.Date.format(new Date(value), format);
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
  s = (t2-t)/ DyMilli;
  return s;
};

/**
 * 校验反馈登记是否过期（true：未过期，false：过期）
 */
DpUtil.validateFeedbackOverTime = function (resultId){
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
DpUtil.validateAccessExpired = function (complaintId){
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


/**
 * comboBox的change事件重写，使其达到可以删除，且只能选择下拉框里面的内容
 */
DpUtil.comboSelsct = function(combo){
		if(Ext.isEmpty(combo.getValue())){
			combo.setValue("");
	}
};
/**.
 * <p>
 * 通用正则校验表达式<br/>
 * <p>
 * @param linkWay 传入的联系方式;
 * @author 李学兴
 * @时间 2012-7-06
 * mobil：手机验证正则，tel：电话验证正则，idCard：身份证，taxNumber：税务登记号，hkTaxNumber：香港商业登记号，defaultValue：默认的验证正则
 * @modify 唐亮 为配合香港开点，修改手机号码校验规则，新增税务登记号/商业登记号校验规则
 */
DpUtil.linkWayLimit = function(regText){
	var mobil = /(^1\d{10}$)|(^\d{8}$)/;
	var tel = /^\d{3}[\d\*-/]{4,37}$/;
	var idCard = /^([\d]{15}|[\d]{18}|[\d]{17}X)$/;
	var cnTaxNumber = /(^[0-9A-Za-z]{20}$)|(^[0-9A-Za-z]{15}$)|(^[0-9A-Za-z]{17}$)|(^[0-9A-Za-z]{18}$)/;
	var hkTaxNumber = /(^\d{11}$)/;
	var defaultValue =  /^\d{11}$/;
	switch (regText) {
		case 'M':return mobil;break;
		case 'T':return tel;break;
		case 'I':return idCard;break;
		case 'CNTAX':return cnTaxNumber;break;
		case 'HKTAX':return hkTaxNumber;break;
		default:return defaultValue;break;
　　}
}
	//定义等待信息
	var processingMask = null;
    Ext.onReady(function(){
		processingMask = new Ext.LoadMask(Ext.getBody(), {
	        msg: '正在处理，请稍候.....',
	        removeMask: true 
	    });
    });
/**
 * 从客户复过来  二级行业 工单上报界面用到
 */
DpUtil.setSecondTradeValue=function(field,value){
	var codeDesc = DpUtil.changeDictionaryCodeToDescrip(value.get('secondTrade'),DataDictionary.SECOND_TRADE);
	if (Ext.isEmpty(codeDesc)) {
		var params =['SECOND_TRADE'];
		 initDataDictionary(params);
		 codeDesc=DpUtil.changeDictionaryCodeToDescrip(value.get('secondTrade'),DataDictionary.SECOND_TRADE);
	}
	field.store.add({'code':value.get('secondTrade'),'codeDesc':codeDesc});
	field.setValue(value.get('secondTrade'));
}

/**
 * 设置按钮 禁用
 * btn 被禁用的按钮
 * time 被禁用的时间，单位秒
 */
DpUtil.btnDisabledTrue = function(btn,time){
	//按钮存在  && setDisabled 存在 &&　disabled　is false 未被禁用
	//方可有效
	if(btn && btn.setDisabled && btn.disabled === false){
		btn.setDisabled(true);
		//time 不存在 || 数据类型 不是 number ||  time 大于 5 秒
		if(!time || typeof(time) !=='number' ||  time>5){
			time = 1;
		}
		setTimeout(function(){btn.setDisabled(false);},time*1000);
	}
};
