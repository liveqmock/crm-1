DButil = function() {
};
//	Ext.define('BasicPanel',{
//		extend:'Ext.panel.Panel',
//		alias : 'widget.basicpanel',
//		border:false
//		});
//	
//	Ext.define('BasicFormPanel',{
//		extend:'Ext.form.Panel',
//		alias : 'widget.basicformpanel',
//		border:false
//	});
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
            for (var k in this.items) {   
                this.items[k].checked = this.items[k].inputValue == v;   
            }   
        }   
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
DButil.showMessage = function(meaage) {
	 Ext.MessageBox.alert(i18n('i18n.order.alert'),meaage);
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
 * 获取业务字典，并将数据存入STORE<br/>
 * <p>
 * @param param  订单创建提交的数据
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-3-11
 */
DButil.getBusinessDictionary = function(param,successFn,failureFn){
	/**
	 * 得到数据字典数据
	 */
	var url = '../common/queryAllDataDictionaryByKeys.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 设置元素为readOnly<br/>
 * <p>
 * @param readOnlyIdList  设置为readOnly的元素ID数组
 * @author 张斌
 * @时间 2012-3-15
 */
DButil.setReadOnly = function(readOnlyIdList){
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
DButil.setHiddenAndDestroy = function(hiddenIdList){
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
DButil.setHidden = function(hiddenIdList){
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
DButil.setDestroy = function(destoryIdList){
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
DButil.setDisabled =function(disabledIdList){
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
DButil.clearListeners =function(clearIdList){
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
DButil.keyPress = function(e,id,getData,param,limit,successFn) {
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
//			DButil.showMessage(json.message);
			MessageUtil.showErrorMes(json.message);(json.message);
		};
		getData(param,id,successFn,failureFn);
	}
};
DButil.updateOrderModel = function(oldSelection,newData) {
	if (Ext.isEmpty(oldSelection)||Ext.isEmpty(newData)) {
		return;
	}
	oldSelection.set(ORDERNAME.get('id'),newData.get(ORDERNAME.get('id')));
	oldSelection.set(ORDERNAME.get('startStationName'),newData.get(ORDERNAME.get('startStationName')));
	oldSelection.set(ORDERNAME.get('createDate'),newData.get(ORDERNAME.get('createDate')));
	oldSelection.set(ORDERNAME.get('createUser'),newData.get(ORDERNAME.get('createUser')));
	oldSelection.set(ORDERNAME.get('modifyDate'),newData.get(ORDERNAME.get('modifyDate')));
	oldSelection.set(ORDERNAME.get('modifyUser'),newData.get(ORDERNAME.get('modifyUser')));
	oldSelection.set(ORDERNAME.get('hastenCount'),newData.get(ORDERNAME.get('hastenCount')));
	oldSelection.set(ORDERNAME.get('lastHastenTime'),newData.get(ORDERNAME.get('lastHastenTime')));
	oldSelection.set(ORDERNAME.get('contactManId'),newData.get(ORDERNAME.get('contactManId')));
	oldSelection.set(ORDERNAME.get('goodsName'),newData.get(ORDERNAME.get('goodsName')));
	oldSelection.set(ORDERNAME.get('goodsTotalNumber'),newData.get(ORDERNAME.get('goodsTotalNumber')));	
	oldSelection.set(ORDERNAME.get('goodsTotalVolume'),newData.get(ORDERNAME.get('goodsTotalVolume')));
	oldSelection.set(ORDERNAME.get('goodsTotalWeight'),newData.get(ORDERNAME.get('goodsTotalWeight')));
	oldSelection.set(ORDERNAME.get('goodsPackagingMaterials'),newData.get(ORDERNAME.get('goodsPackagingMaterials')));
	oldSelection.set(ORDERNAME.get('goodsType'),newData.get(ORDERNAME.get('goodsType')));
	oldSelection.set(ORDERNAME.get('insuerAmount'),newData.get(ORDERNAME.get('insuerAmount')));
	oldSelection.set(ORDERNAME.get('sendDay'),newData.get(ORDERNAME.get('sendDay')));
	oldSelection.set(ORDERNAME.get('codAmount'),newData.get(ORDERNAME.get('codAmount')));
	oldSelection.set(ORDERNAME.get('signDocuments'),newData.get(ORDERNAME.get('signDocuments')));
	oldSelection.set(ORDERNAME.get('modeOfTransportation'),newData.get(ORDERNAME.get('modeOfTransportation')));
	oldSelection.set(ORDERNAME.get('deliveryWay'),newData.get(ORDERNAME.get('deliveryWay')));
	oldSelection.set(ORDERNAME.get('paymentWay'),newData.get(ORDERNAME.get('paymentWay')));
	oldSelection.set(ORDERNAME.get('textMessagesNotice'),newData.get(ORDERNAME.get('textMessagesNotice')));
	oldSelection.set(ORDERNAME.get('storageAndTransportationMatters'),newData.get(ORDERNAME.get('storageAndTransportationMatters')));
	oldSelection.set(ORDERNAME.get('receiverCustId'),newData.get(ORDERNAME.get('receiverCustId')));
	oldSelection.set(ORDERNAME.get('receivingName'),newData.get(ORDERNAME.get('receivingName')));
	oldSelection.set(ORDERNAME.get('receivingCustName'),newData.get(ORDERNAME.get('receivingCustName')));
	oldSelection.set(ORDERNAME.get('receivingCustCode'),newData.get(ORDERNAME.get('receivingCustCode')));
	oldSelection.set(ORDERNAME.get('receivingProvince'),newData.get(ORDERNAME.get('receivingProvince')));
	oldSelection.set(ORDERNAME.get('receivingCity'),newData.get(ORDERNAME.get('receivingCity')));
	oldSelection.set(ORDERNAME.get('receivingCounty'),newData.get(ORDERNAME.get('receivingCounty')));
	oldSelection.set(ORDERNAME.get('receivingMobilePhone'),newData.get(ORDERNAME.get('receivingMobilePhone')));
	oldSelection.set(ORDERNAME.get('receivingPhone'),newData.get(ORDERNAME.get('receivingPhone')));
	oldSelection.set(ORDERNAME.get('receivingAddress'),newData.get(ORDERNAME.get('receivingAddress')));
	oldSelection.set(ORDERNAME.get('receivingToPoint'),newData.get(ORDERNAME.get('receivingToPoint')));
	oldSelection.set(ORDERNAME.get('shipperId'),newData.get(ORDERNAME.get('shipperId')));
	oldSelection.set(ORDERNAME.get('consignorName'),newData.get(ORDERNAME.get('consignorName')));
	oldSelection.set(ORDERNAME.get('consignorCustName'),newData.get(ORDERNAME.get('consignorCustName')));
	oldSelection.set(ORDERNAME.get('consignorIsCargo'),newData.get(ORDERNAME.get('consignorIsCargo')));
	oldSelection.set(ORDERNAME.get('consignorCargoTime'),newData.get(ORDERNAME.get('consignorCargoTime')));
	oldSelection.set(ORDERNAME.get('consignorToCargoTime'),newData.get(ORDERNAME.get('consignorToCargoTime')));
	oldSelection.set(ORDERNAME.get('consignorCustCode'),newData.get(ORDERNAME.get('consignorCustCode')));
	oldSelection.set(ORDERNAME.get('consignorProvince'),newData.get(ORDERNAME.get('consignorProvince')));
	oldSelection.set(ORDERNAME.get('consignorCity'),newData.get(ORDERNAME.get('consignorCity')));
	oldSelection.set(ORDERNAME.get('consignorCounty'),newData.get(ORDERNAME.get('consignorCounty')));
	oldSelection.set(ORDERNAME.get('consignorMobilePhone'),newData.get(ORDERNAME.get('consignorMobilePhone')));
	oldSelection.set(ORDERNAME.get('consignorPhone'),newData.get(ORDERNAME.get('consignorPhone')));
	oldSelection.set(ORDERNAME.get('consignorAddress'),newData.get(ORDERNAME.get('consignorAddress')));
	oldSelection.set(ORDERNAME.get('consignorComeFromPoint'),newData.get(ORDERNAME.get('consignorComeFromPoint')));
	oldSelection.set(ORDERNAME.get('orderNumber'),newData.get(ORDERNAME.get('orderNumber')));
	oldSelection.set(ORDERNAME.get('waybillNum'),newData.get(ORDERNAME.get('waybillNum')));
	oldSelection.set(ORDERNAME.get('channelNumber'),newData.get(ORDERNAME.get('channelNumber')));
	oldSelection.set(ORDERNAME.get('resource'),newData.get(ORDERNAME.get('resource')));
	oldSelection.set(ORDERNAME.get('acceptDept'),newData.get(ORDERNAME.get('acceptDept')));
	oldSelection.set(ORDERNAME.get('acceptDeptName'),newData.get(ORDERNAME.get('acceptDeptName')));
 	Ext.getCmp('orderGrid').getStore().on('update',function(s,record){
 		if(record.get(ORDERNAME.get('orderStatus'))=='WAIT_ACCEPT'){
 			var cells =  Ext.getCmp('orderGrid').getView().getNodes()[record.index].children;
 			for(var i= 0;i<cells.length;i++){
            	  cells[i].style.backgroundColor='#FFFFcd';
               }
 		}else if(record.get(ORDERNAME.get('orderStatus'))=='ACCEPT'){
 			var cells =  Ext.getCmp('orderGrid').getView().getNodes()[record.index].children;
 			for(var i= 0;i<cells.length;i++){
            	  cells[i].style.backgroundColor='#ffcdcd';
               }
 		}else if(record.get(ORDERNAME.get('orderStatus'))=='GOBACK'){
 			var cells =  Ext.getCmp('orderGrid').getView().getNodes()[record.index].children;
 			for(var i= 0;i<cells.length;i++){
            	  cells[i].style.backgroundColor='#c7edcc';
               }
 		} else{
 			var cells =  Ext.getCmp('orderGrid').getView().getNodes()[record.index].children;
 			for(var i= 0;i<cells.length;i++){
            	  cells[i].style.backgroundColor='#ffffff';
               }
 		}
	});
	oldSelection.set(ORDERNAME.get('orderStatus'),newData.get(ORDERNAME.get('orderStatus')));
	Ext.getCmp('orderGrid').getStore().removeListener('update');
	oldSelection.commit();
};
/**.
 * <p>
 * 普通操作<br/>
 * <p>
 * @param commonFunction 操作的方法
 * @param isFeedbackInfo 是否需要填写反馈信息
 * @author 张斌
 * @时间 2012-3-21
 */
DButil.commonOperator = function(commonFunction,isFeedbackInfo,isUpdate,btn) {
	//判断是否选择了一条数据
	var selection = Ext.getCmp('orderGrid').getSelectionModel( ).getSelection()[0];
    if(Ext.getCmp('orderGrid').getSelectionModel( ).getSelection().length!=1){
//    	DButil.showMessage(i18n('i18n.order.pleaseSelectOne'));
    	MessageUtil.showMessage(i18n('i18n.order.pleaseSelectOne'));
			return;
		}
		var successFn = function(json){
			btn.setDisabled(false);
			MessageUtil.showInfoMes(json.message);
			if(isUpdate){
				DButil.updateOrderModel(selection,new OrderModel(json.orderView.order));
				Ext.getCmp('orderGrid').getStore().loadPage(1);
			}else{
				Ext.getCmp('orderGrid').getStore().remove(selection);
				Ext.getCmp('orderGrid').getStore().loadPage(1);
			}
			Ext.data.StoreManager.lookup('orderOperationLogStore').loadData(json.orderView.order.operationLogs);
			DButil.refreshView(new OrderModel(json.orderView.order));
//			DButil.showMessage(json.message);
		};
		var failureFn = function(json){
			btn.setDisabled(false);
//			DButil.showMessage(json.message);
			MessageUtil.showMessage(json.message);
		};
		var orderId = selection.data.id;
		if(isFeedbackInfo == true){
			var feedbackInfo = Ext.getCmp(ORDERNAME.get('returnInfo')).getRawValue();
			if(Ext.getCmp(ORDERNAME.get('returnInfo')).getValue()=='USER_DEFINED'){
				feedbackInfo = Ext.getCmp('otherReason').getValue();
			}
			if(Ext.isEmpty(feedbackInfo)){
//				DButil.showMessage(i18n('i18n.order.plaeasSelectFeedbackInfo'));
				MessageUtil.showMessage(i18n('i18n.order.plaeasSelectFeedbackInfo'));
				return;
			}
			var param = {'orderView':{'orderId':orderId,'feedbackInfo':feedbackInfo}};
		}else{
			var param = {'orderView':{'orderId':orderId}};
		}
		btn.setDisabled(true);
		commonFunction(param,successFn,failureFn);
};
/**.
 * <p>
 * 讲电话号码全部转化xxx-xxxxxxxx-xxx的格式<br/>
 * <p>
 * @param replacePhone 操作的方法
 * @author 张斌
 * @时间 2012-3-23
 */
DButil.replacePhone = function(phone){
	phone.replace('/','-');
	phone.replace('\\','-');
	return phone;
};
/**.
 * <p>
 * 将日期的格式转换为yy-MM-dd hh:mm,将true/false转换为是/否<br/>
 * <p>
 * @param record 是一个OrderModel
 * @author 张斌
 * @时间 2012-3-23
 */
DButil.changeRecord = function(record,dataDictionary){
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
	record.set(ORDERNAME.get('modeOfTransportation'),DButil.changeDictionaryCodeToDescrip(record.get(ORDERNAME.get('modeOfTransportation')),dataDictionary))
	
	var totalWeight=0//总重量
	var totalVolume=0;//总体积
	var goodsNumber=0//总件数
	if(!Ext.isEmpty(record.get("totalWeight"))){
		totalWeight=record.get("totalWeight");
	}
	if(!Ext.isEmpty(record.get("totalVolume"))){
		totalVolume=record.get("totalVolume");
	}
	if(!Ext.isEmpty(record.get("goodsNumber"))){
		goodsNumber=record.get("goodsNumber");
	}
	record.set(ORDERNAME.get('goodsTotalNumber'),totalWeight+"/"+totalVolume+"/"+goodsNumber)
	return record;
};
/**.
 * <p>
 * 刷新受理/分配中的订单详情（受理，打回，催单等）<br/>
 * <p>
 * @param json json中含有orderView
 * @author 张斌
 * @时间 2012-3-23
 */
DButil.refreshView = function(record,dataDictionary){
	if(!Ext.isEmpty(record.data)){
		record = new OrderModelView(record.data);
		var changeRecord = DButil.changeRecord(record,dataDictionary);
		Ext.getCmp('basicInfoForm').getForm().loadRecord(changeRecord);
		Ext.getCmp('sendGoodForm').getForm().loadRecord(changeRecord);
		Ext.getCmp('receiveGoodForm').getForm().loadRecord(changeRecord);
	}
};
/**.
 * <p>
 * 数组中是否有空值<br/>
 * <p>
 * @param array 数组
 * @author 张斌
 * @时间 2012-3-24
 */
DButil.isHaveEmpty = function(array){
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
 * 定时刷新gird<br/>
 * <p>
 * @param time 分钟数
 * @param grid Ext grid列表
 * @author 张斌
 * @时间 2012-3-31
 */
DButil.refreshGird = function(){
	Ext.getCmp('orderGrid').getStore().load();
	setTimeout("DButil.refreshGird()",180*1000);
};

/**.
 * <p>
 * 接货起始日期和终止日期是否符合校验（起始日期小于终止日期，并且大于等于当天日期）<br/>
 * <p>
 * @param startData 起始日期
 * @param endDate   终止日期
 * @author 张斌
 * @时间 2012-5-5
 */
DButil.isValidStartDataAndEndDate = function(startDate,endDate){
	if(Ext.isEmpty(startDate)||Ext.isEmpty(endDate)){
		return true;
	}
	var startDateTime = startDate.getTime();
	var endDateTime = endDate.getTime();
	var date = new Date();
	var newDate = new Date(date.getFullYear(),date.getMonth(),date.getDate(),0,0,0,0);
	if(startDateTime<=endDateTime&&startDateTime>=newDate.getTime()){
		return true;
	}else{
		return false;
	}
	
};
/**.
 * <p>
 * 接货起始日期和终止日期是否符合校验（起始日期大于默认时间）<br/>
 * <p>
 * @param startDate 起始日期
 * @param defaultDate   默认日期
 * @author 吕崇新
 * @时间 2013-12-24
 */
DButil.ValidStartData = function(startDate,defaultDate){
	if(Ext.isEmpty(startDate)||Ext.isEmpty(defaultDate)){
		return true;
	}
	var startDateTime = startDate.getTime();
	defaultDate.setUTCMilliseconds(0);
	defaultDate.setSeconds(0);
	var defaultDateTime=defaultDate.getTime();
	if(startDateTime>=defaultDateTime){	
		return true;
	}else{
		return false;
	}
	
};
/**.
 * <p>
 * 接货起始日期和终止日期是否符合校验（起始日期与终止日期相差大于等于2.5小时）<br/>
 * <p>
 * @param startData 起始日期
 * @param endDate   终止日期
 * @author 吕崇新
 * @时间 2013-12-24
 */
DButil.ValidStartDataAndEndDate = function(startDate,endDate){
	var startDateTime = startDate.getTime();
	var endDateTime = Ext.Date.add(endDate,Ext.Date.MINUTE,-150).getTime();
	if(startDateTime<=endDateTime){
		return true;
	}else{
		return false;
	}
}
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
