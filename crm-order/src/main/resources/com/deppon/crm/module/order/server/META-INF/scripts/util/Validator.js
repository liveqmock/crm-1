Validator = function() {
};
/**
 * <p>
 * @功能：校验传入参数是否都为空<br/>
 * </p>
 * @param phone   要校验的电话号码
 * @param mobilePhone 要校验的手机号
 * @作者： 张斌
 * @创建时间：2012-03-14
 */
Validator.isEmptyPhoneAndMobilePhone = function(phone,mobilePhone){
	if((Ext.isEmpty(mobilePhone)&&Ext.isEmpty(phone))){
		return true;
	}else{
		return false;
	}
};
Validator.lengthValidator = function(value,minLen,maxLen) {
	var len = value.length;
	if(len>0&&len<maxLen){
		return true;
	}
	return false;
};

Validator.eqLengthValidator = function(value,length) {
	var len = value.length;
	if(len==length){
		return true;
	}
	return false;
};
/**
 * <p>
 * @功能：校验两个时间差是否大于30天小于0天<br/>
 * </p>
 * @param startDate   起始时间
 * @param endDate 截止时间
 * @作者： 张斌
 * @创建时间：2012-03-20
 */
Validator.isDateMoreThirtyDays = function(startDate,endDate) {
	if(((endDate.getTime()-startDate.getTime())/(24*60*60*1000))>30){
		return 1;
	}else if(((endDate.getTime()-startDate.getTime())/(24*60*60*1000))<0){
		return 2;
	}else{
		return 0;
	}
};


/**
 * <p>
 * 验证是否大于7天或者小于当前时间</br>
 * </p>
 * @author 李春雨
 * @param startDate
 * @param endDate
 * @returns {Number}
 */
Validator.isDateLessSevenDays = function(startDate,endDate) {
	if(((endDate.getTime()-startDate.getTime())/(24*60*60*1000))>7){
		return 1;
	}else if(((endDate.getTime()-startDate.getTime())/(24*60*60*1000))<0){
		return 2;
	}else{
		return 0;
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