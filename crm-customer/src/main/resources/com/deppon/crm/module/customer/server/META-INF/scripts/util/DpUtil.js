/**
* 增加业务规则 Util 导入
*/
(function() {
	var businessUtil = "../scripts/customer/util/BusinessUtil.js";
	var businessUtilTest = "../scripts/util/BusinessUtil.js";
    if(!CONFIG.get('TEST')){
		document.write('<script type="text/javascript" src="' + businessUtil + '"></script>');
	}else{
		document.write('<script type="text/javascript" src="' + businessUtilTest + '"></script>');
	}
})();
Ext.JSON.encodeDate = function(d) {
	return d.getTime();
};
 
String.prototype.replaceAll = function (AFindText,ARepText){
	raRegExp = new RegExp(AFindText,"g");
	return this.replace(raRegExp,ARepText);
};

DpUtil = function(){};

/**
 * author：李盛
 * 功能：判断是否为空
 * 参数：str：预判断参数
 * mender：李学兴
 * 功能：使用Ext自己的判断为空函数
 */
DpUtil.isEmpty = function(str){  
//	 if((str === undefined) || (str == null) || (str.length == 0)) {
//		 return true;  
//	 }
//	 else {
//		 return false;  
//	 }
	return Ext.isEmpty(str);
};

/**
 * author：李盛
 * 功能：Ajax请求--json
 * 参数：url：请求url,params：请求参数,successFn：请求成功回调函数,failFn：请求失败回调函数
 * mender：李学兴
 * 增加参数 timeout：修改请求时间,默认为600000十分钟
 */
DpUtil.requestJsonAjax = function(url,params,successFn,failFn,timeout)
{
	Ext.Ajax.request({
		url:url,
		jsonData:params,
		timeout:(Ext.isEmpty(timeout))?600000:timeout,
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
 * author：李盛
 * 功能：Ajax请求--json,同步请求
 * 参数：url：请求url,params：请求参数,successFn：请求成功回调函数,failFn：请求失败回调函数
 * timeout：修改请求时间,默认为600000十分钟
 */
DpUtil.requestJsonAjaxSync = function(url,params,successFn,failFn,timeout)
{
	Ext.Ajax.request({
		url:url,
		async:false,
		jsonData:params,
		timeout:(Ext.isEmpty(timeout))?600000:timeout,
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
	if(!Ext.isEmpty(value)){
		return Ext.Date.format(new Date(value), 'Y-m-d');
	}else{
		return null;
	}
};
/**.
 * <p>
 * 时间格式化 年月日时分秒<br/>
 * <p>
 * @param value 后台查询的date数据;
 * @author 李学兴
 * @时间 2012-08-28
 */
DpUtil.renderTime = function(value) {
	if(!Ext.isEmpty(value)){
		return Ext.Date.format(new Date(value), 'Y-m-d H:i:s');
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
		return i18n('i18n.ChangeContactAffiliatedRelationView.no');
	}else if(value == true){
		return i18n('i18n.ChangeContactAffiliatedRelationView.yes');
	}
	return null;
};
//boolean值转换成描述
DpUtil.changeIntToDescrip = function(value){
	if(value == 0){
		return i18n('i18n.ChangeContactAffiliatedRelationView.no');
	}else if(value == 1){
		return i18n('i18n.ChangeContactAffiliatedRelationView.yes');
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

DpUtil.showMessage = function(meaage) {
	 top.Ext.MessageBox.alert(i18n('i18n.PotentialCustManagerView.message'),meaage);
};
/**
 * 确认框
 */
DpUtil.showConfimMessageBox=function(msg,message,yesFn,noFn){
	Ext.MessageBox.confirm(msg,message,function(e) {
		if (e == 'yes') {
			yesFn;
		}else{
			noFn;
		}
	});
};

/**.
 * <p>
 * 公共方法，通过storeId和model创建STORE<br/>
 * <p>
 * @param  storeId  
 * @param  model   store所用到的model名
 * @param  fields   store所用到的fields
 * @returns store  返回创建的store
 * @author 潘光均
 * @时间 2012-3-12
 */
DpUtil.createStore = function(storeId,model,fields,data) {
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

/**
 * 将long转化为日期
 */
DpUtil.changeLongToDate = function(value) {
	if (!Ext.isEmpty(value)) {
		var date = new Date(value);
		return date;
	} else {
		return null;
	}
};
/**
 * 字符串去空格
 */
DpUtil.trimString = function(value) {
	if(!Ext.isEmpty(value)){
		return value.trim();
	}
	return value;
};
/**.
 * <p>
 * 得到一个数据字典combo组件<br/>
 * <p>
 * @param me 作用范围，null为this(即默认值)，否则表示重定向 作用范围
 * @param enterKeyEvent 按键事件，null为不处理，true，为加载方法内默认，其他地方为重写方法
 * @param enterKeyEvent 按键事件，null为不处理，true，为加载方法内默认，其他地方为重写方法
 * @param selectIsFocusPay 选择事件，null为不处理，true，为加载方法内默认，其他地方为重写方法
 * @param fieldLabel,name,store,allowBlank,readOnly,labelWidth,width,blankText,changEvent
 * @author 李学兴
 * @时间 2012-6-12
 */
DpUtil.createDateCombo = function(fieldLabel,name,store,allowBlank,readOnly,labelWidth,width,blankText,changEvent) {
	var fn = function(){};
	var chang = function(field, newValue) {if (Ext.isEmpty(newValue)) {field.setValue(null);}};
	var combo = Ext.create('Ext.form.ComboBox', {
		fieldLabel:fieldLabel,
		xtype:'combobox',
		queryMode:'local',
        forceSelection:true,//必须为选择内容
		displayField:'codeDesc',
		valueField:'code',
		allowBlank:allowBlank,//是否可为空
		readOnly:readOnly,//只读
		name:name,
		labelWidth:labelWidth,//描述宽度
		width:width,//宽度
		store:store,//数据date
		blankText:blankText,//空白显示内容
		listeners:{
			change:(changEvent == null)?fn:((changEvent == true)?chang:changEvent)
		}
	});
	return combo;
};
/**.
 * <p>
 * 有readonly样式的combo组件<br/>
 * <p>
 * @param 
 * @author 李学兴
 * @时间 2012-6-16
 */
Ext.define('DpComboBox',{
	extend:'Ext.form.ComboBox',
	alias : ['widget.dpcombobox', 'widget.dpcombo'],
	//扩展setReadOnly 方法
	setReadOnly:function(readOnly){
		this.callParent(arguments);
		if(readOnly){
			this.addCls('readonly');
		}else{
			this.removeCls('readonly');
		}
	}
});
/**.
 * <p>
 * 有readonly样式的numb组件<br/>
 * <p>
 * @param 
 * @author 李学兴
 * @时间 2012-6-16
 */
Ext.define('DpNumberField',{
	extend:'Ext.form.Number',
	alias : ['widget.dpnumberfield', 'widget.dpnumber'],
	//扩展setReadOnly 方法
	setReadOnly:function(readOnly){
		this.callParent(arguments);
		if(readOnly){
			this.addCls('readonly');
		}else{
			this.removeCls('readonly');
		}
	}
});
/**.
 * <p>
 * 有readonly样式的Date组件<br/>
 * <p>
 * @param 
 * @author 李学兴
 * @时间 2012-6-16
 */
Ext.define('DpDateField',{
	extend:'Ext.form.Date', 
	alias : ['widget.dpdatefield','widget.dpdate'],
	//扩展setReadOnly 方法
	setReadOnly:function(readOnly){
		this.callParent(arguments);
		if(readOnly){
			this.addCls('readonly');
		}else{
			this.removeCls('readonly');
		}
	}
});
/**.
 * <p>
 * 有readonly样式的timefield组件<br/>
 * <p>
 * @param 
 * @author 李学兴
 * @时间 2012-6-16
 */
Ext.define('DpTimeField',{
	extend:'Ext.form.Time', 
	alias : 'widget.dpdtimefield',
	//扩展setReadOnly 方法
	setReadOnly:function(readOnly){
		this.callParent(arguments);
		if(readOnly){
			this.addCls('readonly');
		}else{
			this.removeCls('readonly');
		}
	}
});
/**.
 * <p>
 * 重写Ext form Text<br/>
 * <p>
 * @param 
 * @author 李学兴
 * @时间 2012-6-16
 */
Ext.define('DpTextField',{
	extend:'Ext.form.Text', 
	alias : 'widget.dptextfield',
	//扩展getValue() 方法 去掉前后空格  用于提交
	getValue:function(){
        var me = this,
            val = me.rawToValue(me.processRawValue(me.getRawValue()));
        me.value = val;
        return Ext.isEmpty(val)?val:val.trim();//return val;
    },
    //扩展getRawValue() 方法 去掉前后空格  用于表单验证
    getRawValue: function() {
        var me = this,
            val = (me.inputEl ? me.inputEl.getValue() : Ext.value(me.rawValue, ''));
        me.rawValue = val;
        return Ext.isEmpty(val)?val:val.trim();//return val;
    }
});
/**.
 * <p>
 * 通过月份得到当月天数<br/>
 * <p>
 * @param month 传入的 new Date().getMonth();
 * @author 李学兴
 * @时间 2012-7-05
 */
DpUtil.getCurrentMonthDays = function(month){
	var currentMonth = month+1;
	switch (currentMonth) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:return 31;break;
		case 2:return 28;break;
		default:return 30;break;
　　}
};
/**
 * 二级行业设置
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
 * 
 */
DpUtil.changeCombText=function(ths,newValue,oldValue,obj){
	if (Ext.isEmpty(newValue)) {
		ths.setValue(null);
	}
}