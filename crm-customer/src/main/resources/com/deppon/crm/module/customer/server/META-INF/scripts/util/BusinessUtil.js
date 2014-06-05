
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
 * 潜散客相对时间校验，时间间隔不得大于一年<br/>
 * <p>
 * @param objPre起始时间
 * @param objNext截止时间
 * @author 李学兴
 * @时间 2012-6-14
 */
DpUtil.checkDataInOneYear = function(objPre,objNext) {
	var success = true;//校验结果，通过则为true，反之为false
	var message = '';//检验结果提示消息，通过时为"",否者为具体提示信息
	if (DpUtil.isEmpty(objPre.getValue())|| DpUtil.isEmpty(objNext.getValue())) {
		var success = false;
		var message = i18n('i18n.DpUtil.m_notAllDateNull');
	}else if(!DpUtil.isEmpty(objPre.getValue())&& !DpUtil.isEmpty(objNext.getValue())){
		if(objPre.getValue() > objNext.getValue()){
			var success = false;
			var message = i18n('i18n.DpUtil.d_startNotBigger');
		}else{
			var date = objNext.getValue().setFullYear(objNext.getValue().getFullYear()-1);
			if(objPre.getValue().getTime()<date){
				var success = false;
				var message = i18n('i18n.DpUtil.d_intervalBigger');
			}
			//相隔一年判断
//			if(objNext.getValue().getFullYear()-objPre.getValue().getFullYear() <= 1){
//				if(objNext.getValue().getFullYear()-objPre.getValue().getFullYear() == 1){
//					var monthsNext = objNext.getValue().getMonth()-objPre.getValue().getMonth()+12;
//					if(monthsNext<=12){
//						if(monthsNext == 12){
//							if(objNext.getValue().getDate()>objPre.getValue().getDate()){
//								var success = false;
//								var message = i18n('i18n.DpUtil.d_intervalBigger');
//							}
//						}
//					}else{
//						var success = false;
//						var message = i18n('i18n.DpUtil.d_intervalBigger');
//					}
//				}
//			}else{
//				var success = false;
//				var message = i18n('i18n.DpUtil.d_intervalBigger');
//			}
		}
	}
	return {success:success,message:message};
}
/**.
 * <p>
 * 获得指定间隔时间的date<br/>
 * <p>
 * @param year年
 * @param month月
 * @param day日
 * @author 李学兴
 * @时间 2012-6-19
 */
DpUtil.checkDataInOneYear1 = function(year,month,day) {
	var data = new Date();
	data.setYear(new Date().getYear()+year);
	data.setYear(new Date().getMonth()+month);
	data.setYear(new Date().getDay()+day);
	if ((DpUtil.isEmpty(objPre.getValue())
			|| DpUtil.isEmpty(objNext.getValue()))) {
		var success = false;
		var message = i18n('i18n.DpUtil.d_notAllDateNull');
	}else if(!DpUtil.isEmpty(objPre.getValue())&& !DpUtil.isEmpty(objNext.getValue())){
		if(objPre.getValue() > objNext.getValue()){
			var success = false;
			var message = i18n('i18n.DpUtil.d_startNotBigger');
		}else{
			//相隔一年判断
			if(objNext.getValue().getFullYear()-objPre.getValue().getFullYear() <= 1){
				var monthsNext = objNext.getValue().getMonth()-objPre.getValue().getMonth()+12;
				if(monthsNext>=0 && monthsNext<=12){
					if(monthsNext == 12){
						if(objNext.getValue().getDate()>objPre.getValue().getDate()){
							var success = false;
							var message = i18n('i18n.DpUtil.d_intervalBigger');
						}
					}
				}
			}
		}
	}
}

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
	var tel = /^\d{3}[\d\-/]{4,37}$/;
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
/**.
 * <p>
 * 所属部门加载默认为登陆用户所在默认组织<br/>
 * <p>
 * @param form 传入的操作form;deptName 传入的操作控件name
 * @author 李学兴
 * @时间 2012-7-10
 * top.User.deptId：当前登陆用户所在部门id，top.User.deptName：当前登陆用户所在部门name
 */
DpUtil.defaultDept = function(form,fieldName){
	var dept = form.getForm().findField(fieldName);
	dept.store.add(Ext.create('CurrentUserDeptListModel',{'deptId':top.User.deptId,'deptName':top.User.deptName}));
	dept.setValue(top.User.deptId);
}

/**.
 * <p>
 * 手机号码填写过11位后自动删除多余<br/>
 * <p>
 * @param field 操作控件
 * 			newValue 新值
 * @author 李学兴
 * @时间 2012-7-30
 */
DpUtil.autoChangeMobileLength = function(field,newValue){
	if (newValue.length > 11){
		field.setValue(newValue.substring(0, 11));
	}
}
/**.
 * <p>
 * 电话号码填写过20位后自动删除多余<br/>
 * <p>
 * @param field 操作控件
 * 			newValue 新值
 * @author 李学兴
 * @时间 2012-7-30
 */
DpUtil.autoChangeTelLength = function(field,newValue){
	if (newValue.length > 20){
		field.setValue(newValue.substring(0, 20));
	}
}
/**.
 * <p>
 * 得到全局的特殊字符串<br/>
 * <p>
 * @param regText 返回特殊值标志"redSpan" 红星
 * @author 李学兴
 * @时间 2012-11-20
 */
DpUtil.getSpecialStr = function(regText){
	switch (regText) {
		case 'redSpan':return '<span style="color:red">*</span>';break;
		default:return '';break;
　　}
}
