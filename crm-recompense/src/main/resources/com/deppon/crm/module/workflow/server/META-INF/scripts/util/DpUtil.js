DpUtil = function() {
};
/**.
 * <p>
 * 覆盖Ext.form.RadioGroup的setValue方法<br/>
 * item.getRawValue全为false
 * <p>
 * @author 张斌
 * @时间 2012-3-25
 */
Ext.override(Ext.form.RadioGroup, {   
    setValue: function(v){
        if (this.rendered)    
            this.items.each(function(item){   
                item.setValue(item.inputValue == v);   
            });   
        else {
            for (var k = 0;k<this.items.items.length;k++) {   
                this.items.items[k].setValue(this.items.items[k].inputValue == v);   
            }   
        }   
    }   
}); 
/**.
 * <p>
 * 覆盖Ext.form.CheckboxGroup的setValue方法<br/>
 * item.getRawValue全为false
 * <p>
 * @author 张斌
 * @时间 2012-3-25
 */
Ext.override(Ext.form.CheckboxGroup, {   
    setValue: function(value){
    	var me = this;
        me.batchChanges(function() {
            me.eachBox(function(cb) {
                var inputValue = cb.inputValue,
                    cbValue = false;
                if (value) {
                    if (Ext.isArray(value)) {
                        cbValue = Ext.Array.contains(value,cb.inputValue);
                    } else {           
                        cbValue = value[name];
                    }
                }
                cb.setValue(cbValue);
            });
        });
        return me;  
    }   
}); 
/**
 * 修改date对象数据的JSON提交方式
 */
Ext.JSON.encodeDate = function(date) {
	return date.getTime();
};
/**.
 * <p>
 * 公共方法，显示MESSAGE信息<br/>
 * <p>
 * @param  meaage  要现实的信息
 * @author 张斌
 * @时间 2012-3-11
 */
DpUtil.showMessage = function(meaage) {
	 top.Ext.MessageBox.alert(i18n('i18n.memberView.alert'),meaage);
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
DpUtil.getStore = function(storeId,model,fields,data) {
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


/**.
 * <p>
 * 设置元素为readOnly<br/>
 * <p>
 * @param readOnlyIdList  设置为readOnly的元素ID数组
 * @author 张斌
 * @时间 2012-3-15
 */
DpUtil.setReadOnly = function(readOnlyIdList){
     for(var i=0;i<readOnlyIdList.length;i++){
    	 Ext.getCmp(readOnlyIdList[i]).setReadOnly(true);
     }
};

/**.
 * <p>
 * 设置元素为隐藏并且销毁，使其不在校验<br/>
 * <p>
 * @param hiddenIdList  设置为readOnly的元素ID数组
 * @author 张斌
 * @时间 2012-3-15
 */
DpUtil.setHiddenAndDestroy = function(hiddenIdList){
     for(var i=0;i<hiddenIdList.length;i++){
    	 Ext.getCmp(hiddenIdList[i]).hide();
    	 Ext.getCmp(hiddenIdList[i]).destroy( );
     }
};
/**.
 * <p>
 * 设置元素为隐藏<br/>
 * <p>
 * @param hiddenIdList  设置为readOnly的元素ID数组
 * @author 张斌
 * @时间 2012-3-15
 */
DpUtil.setHidden = function(hiddenIdList){
     for(var i=0;i<hiddenIdList.length;i++){
    	 Ext.getCmp(hiddenIdList[i]).hide();
     }
};
/**.
 * <p>
 * 设置元素为销毁<br/>
 * <p>
 * @param destoryIdList  设置为destory的元素ID数组
 * @author 张斌
 * @时间 2012-3-21
 */
DpUtil.setDestroy = function(destoryIdList){
     for(var i=0;i<destoryIdList.length;i++){
    	 Ext.getCmp(destoryIdList[i]).destroy();
     }
};
/**.
 * <p>
 * 设置元素为不可用<br/>
 * <p>
 * @param disabledIdList  设置为Disabled的元素ID数组
 * @author 张斌
 * @时间 2012-3-21
 */
DpUtil.setDisabled =function(disabledIdList){
	for(var i=0;i<disabledIdList.length;i++){
   	 Ext.getCmp(disabledIdList[i]).setDisabled(true);
    }
};
/**.
 * <p>
 * 清楚事件<br/>
 * <p>
 * @param clearIdList  设置为清楚时间的元素ID数组
 * @author 张斌
 * @时间 2012-3-22
 */
DpUtil.clearListeners =function(clearIdList){
	for(var i=0;i<clearIdList.length;i++){
   	 Ext.getCmp(clearIdList[i]).clearListeners( );
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

/**.
 * <p>
 * 将true转换为“是”，false转换为“否”<br/>
 * <p>
 * @param value  所要转换的值
 * @author 张斌
 * @时间 2012-3-20
 */
DpUtil.changeTrueAndFalse = function(value) {
	if(value=='true'){
		return i18n('i18n.memberView.yes');
	}else if(value=='false') {
		return i18n('i18n.memberView.no');
	}else if (value==true) {
		return i18n('i18n.memberView.yes');
	} else if(value==false) {
		return i18n('i18n.memberView.no');
	}else{
		return null;
	}
};
/**
 * @author Rock
 * i18n.memberView.useful = 有效
 * i18n.memberView.unUseful = 无效
 * i18n.memberView.approvalIn = 审批中
 */
DpUtil.verifIsEffective = function(custStatus){
	var ret = null;
	switch(custStatus)
	{
   	case '0':
   	ret = i18n('i18n.memberView.useful');
   	break;
   	case '1':
   	ret = i18n('i18n.memberView.approvalIn');
   	break;
   	case '2':
   	ret = i18n('i18n.memberView.unUseful');
   	break;
   	// default:
   	// ret = i18n('i18n.memberView.unUseful');
   };
   	return ret; 
};
/**.
 * <p>
 * 按回车之后查询的方法<br/>
 * <p>
 * @param e 按回车键时，EXT自带参数
 * @param id 该元素的ID，与storeId一致
 * @param getData所调用的方法
 * @param paramName ACTION参数的名字
 * @param limit 限制必须输入几个字之后才执行getData
 * @author 张斌
 * @时间 2012-3-20
 */
DpUtil.keyPress = function(e,getData,param,limit,successFn) {
	var paramValue = e.target.value;
	if(Ext.isEmpty(paramValue)){
		return ;
	}
	if(paramValue.length<limit){
		return ;
	}
	//按回车并且输入的字符长度大于limit
	if (e.getKey() == Ext.EventObject.ENTER && paramValue!="" &&paramValue.length>=limit) 
	{
		var failureFn = function(json){
			DpUtil.showMessage(json.message);
		};
		getData(param,successFn,failureFn);
	}
};

/**.
 * <p>
 * 讲电话号码全部转化xxx-xxxxxxxx-xxx的格式<br/>
 * <p>
 * @param replacePhone 操作的方法
 * @author 张斌
 * @时间 2012-3-23
 */
DpUtil.replacePhone = function(phone){
	phone.replace('/','-');
	phone.replace('\\','-');
	return phone;
};

/**.
 * <p>
 * 数组中是否有空值<br/>
 * <p>
 * @param array 数组
 * @author 张斌
 * @时间 2012-3-24
 */
DpUtil.isHaveEmpty = function(array){
	var boolen = false;
	for(var i = 0;i<array.length;i++){
		if(Ext.isEmpty(array[i])){
			boolen = true;
			return boolen;
		}
	}
	return boolen;
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
	if(Ext.isEmpty(this)||this.getTime()==0||this.toString().indexOf('GMT')==-1){
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


/**.
 * <p>
 * 根据传的参数生成查询条件<br/>
 * <p>
 * @param modelList 要转换的Modellist
 * @returns  dataList
 * @author 张斌
 * @时间 2012-4-16
 */
DpUtil.changeModelListToDataList = function(modelList){
	var dataList = new Array();
	for(var i = 0;i<modelList.length;i++){
		dataList.push(modelList[i].data);
	}
	return dataList;
};
/**.
 * <p>
 * 校验一个字符是否在一个数组中<br/>
 * <p>
 * @param 查询条件model
 * @author 潘光均
 * @时间 2012-4-13
 */
DpUtil.isArrayContaintChar=function(char,array){
	for ( var i = 0; i < array.length; i++) {
		if (array[i]==char) {
			return true;
		}
		return false;
	}
};
/**.
 * <p>
 * 校验一个字符是否在一个数组中<br/>
 * <p>
 * @param startDate  起始时间
 * @param endDate    结束时间
 * @param limit      最大时间差
 * @author 张斌
 * @时间 2012-4-26
 */
DpUtil.isFindOutDeadline=function(startDate,endDate,limit){
	var startDateTime = startDate.getTime();
	var endDateTime = endDate.getTime();
	var realDate = (endDateTime-startDateTime)/(24*3600*1000)
	if(realDate>limit||realDate<0){
		return true;
	}else{
		return false;
	}
};
/**
 * @功能：为js中的STRING加上trim方法
 * @作者： 张斌
 * @创建时间：2012-02-20
 */
String.prototype.trim= function(){  
    // 用正则表达式将前后空格  
    // 用空字符串替代。  
    return this.replace(/(^\s*)|(\s*$)/g, "");  
};

DpUtil.trimString = function(value) {
	if(!Ext.isEmpty(value)){
		return value.trim();
	}
	return value;
};
/**
 * 
 * @功能：将毫秒转为秒
 * @作者： 唐亮
 * @创建时间：2
 */
DpUtil.changeMillisecondToSecond = function(value){
	if(!Ext.isEmpty(value)){
		return parseFloat(value)/1000;
	}
	return value;
};
/**.
 * <p>
 * 时间格式化 年月日时分秒<br/>
 * <p>
 * @param value 后台查询的date数据;
 * @author 唐亮
 * @时间 2013-07-19
 */
DpUtil.renderTime = function(value) {
	if(!Ext.isEmpty(value)){
		return Ext.Date.format(new Date(value), 'Y-m-d H:i:s');
	}else{
		return null;
	}
};

DpUtil.renderDate = function(value) {
	if(!Ext.isEmpty(value)){
		return Ext.Date.format(new Date(value), 'Y-m-d');
	}else{
		return null;
	}
};
DpUtil.linkWayLimit = function(regText){
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