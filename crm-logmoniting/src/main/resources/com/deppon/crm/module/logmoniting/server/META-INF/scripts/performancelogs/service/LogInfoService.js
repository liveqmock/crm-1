/**
 * 将时间转为为响应的格式
 */
var changeDateFormat = function(timeType){
	var me = this;
	var beginDateField = Ext.getCmp('beginCountDate_id');
	var endDateField = Ext.getCmp('endCountDate_id');
	if('HOUR'==timeType){
		beginDateField.format='Y-m-d H:00:00';
		endDateField.format='Y-m-d H:00:00';
	}else if('DAY'==timeType){
		beginDateField.format='Y-m-d 00:00:00';
		endDateField.format='Y-m-d 00:00:00';
	}else if('MONTH'==timeType){
		beginDateField.format='Y-m-1 00:00:00';
		endDateField.format='Y-m-1 00:00:00';
	}
	//时间重置为空
	beginDateField.reset();
	endDateField.reset();
};
/**
 * 校验action列表查询条件是否合法
 */
var validateSearchCondition = function(valiType){
	var me = this;
	var searchForm = Ext.getCmp('searchPanel_id').getForm();
	if('TOPCHART'==valiType){
		var record = Ext.create('SearchChartConditionModel');
	}else{
		var record = Ext.create('SearchLogConditionModel');
	}
	searchForm.updateRecord(record);
	if(!searchForm.isValid()){
		MessageUtil.showErrorMes('请校验数据的合法性再查询');
		return false;
	}
	var nowDate = new Date();
	var nowYear = nowDate.getFullYear();
	var nowMonth = nowDate.getMonth()+1;
//	var nowDay = nowDate.getDate();
//	var nowHour = nowDate.getHours();
	var beginDateValue = searchForm.findField('beginDate').getValue();
	var endDateValue = searchForm.findField('endDate').getValue();
	if((!Ext.isEmpty(beginDateValue)&&Ext.isEmpty(endDateValue))
			||(Ext.isEmpty(beginDateValue)&&!Ext.isEmpty(endDateValue))){
		MessageUtil.showErrorMes('开始和结束时间必须全空或者全不为空');
		return false;
	}
	if(!Ext.isEmpty(beginDateValue)&&!Ext.isEmpty(endDateValue)){
		var beginDate = new Date(beginDateValue);
		var endDate = new Date(endDateValue);
		if(beginDate >= endDate){
			MessageUtil.showErrorMes('开始时间不得大于等于结束时间');
			return false;
		}
		var beginYear = beginDate.getFullYear();
		var beginMonth = beginDate.getMonth()+1;
		var beginDay = beginDate.getDate();
		var beginHour = beginDate.getHours();
		var endYear = endDate.getFullYear();
		var endMonth = endDate.getMonth()+1;
		var endDay = endDate.getDate();
		var endHour = endDate.getHours();
		var rate = record.data.statisticalFrequency;
		if(beginDate > nowDate || endDate > nowDate){
			MessageUtil.showErrorMes('开始和结束时间不能大于当前');
			return false;
		}
		if('hour'==rate){
			if(beginDate >= endDate){
				MessageUtil.showErrorMes('统计频率为小时，开始小时不能大于等于结束小时');
				return false;
			}
			if((beginYear != endYear)||(beginMonth != endMonth) || (endDay-beginDay)>1){
				MessageUtil.showErrorMes('统计频率为小时,结束时间和开始时间差值不能大于一天！');
				return false;
			}else if(beginDay !=endDay){
				if(endHour != 0){
					MessageUtil.showErrorMes('统计频率为小时,不在同一天时,其开始时间和结束时间都必须为0点！');
					return false;
				}
			}
		}else if('day'==rate){
			if((beginYear != endYear)||(beginMonth > endMonth)||((beginMonth != endMonth-1)&&(beginMonth < endMonth))){
				MessageUtil.showErrorMes('统计频率为天，开始时间和结束时间必须在同年且前后者月份相差不能大于1');
				return false;
			}else if((beginMonth == endMonth-1)&&(endDay !=1)){
				MessageUtil.showErrorMes('统计频率为天，结束时间为同年次月，结束时间最大为次月 1日');
				return false;
			}
		}else if('month'==rate){
			if(beginYear > endYear){
				MessageUtil.showErrorMes('开始和结束时间不能大于当前');
				return false;
			}
			if(endYear - beginYear > 1){
				MessageUtil.showErrorMes('统计频率为月,时间相差不能超过一年');
				return false;
			}
			if((endYear != beginYear) && endMonth != 1){
				MessageUtil.showErrorMes('统计频率为月,不在同一年的时候,起结束时间的月结必须为1月份!');
				return false;
			}
			if((beginYear == nowYear)&&((beginMonth >= nowMonth)||(endMonth > nowMonth))){
				MessageUtil.showErrorMes('统计频率为月，时间只能选择本年本月以前');
				return false;
			}
		}
	}
	return true;
};