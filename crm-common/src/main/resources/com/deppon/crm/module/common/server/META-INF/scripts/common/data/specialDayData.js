
var SpecialDayData = function(){};
/**
 * 按照ID数组删除特殊日期信息
 */
SpecialDayData.prototype.deleteSpecialDayByIdArrayList = function(param,fnSuccess,fnFail){
	var url = '../common/deleteSpecialDayByIdArrayList.action';
	DpUtil.requestAjax(url,param,fnSuccess,fnFail);
}
/**
 * 按照ID获取特殊日期信息
 */
SpecialDayData.prototype.getSpecialDayById = function(param,fnSuccess,fnFail){
	var url = '../common/getSpecialDayById.action';
	DpUtil.requestAjax(url,param,fnSuccess,fnFail);
}
/**
 * 增加
 */
SpecialDayData.prototype.submitSpecialDay = function(param,fnSuccess,fnFail){
	var url = '../common/submitSpecialDay.action';
	DpUtil.requestAjax(url,param,fnSuccess,fnFail);
}

/**
 * 修改
 */
SpecialDayData.prototype.updateSpecialDay = function(param,fnSuccess,fnFail){
	var url = '../common/updateSpecialDay.action';
	DpUtil.requestAjax(url,param,fnSuccess,fnFail);
}

//工作日维护表格store
var specialDayStore = Ext.create('Ext.data.Store',{
	model:'SpecialDayModel',
	autoLoad:true,
	proxy:{
		url:'../common/searchSpecialDayByCondition.action',
		type:'ajax',
		actionMethods:'post',
		reader:{
			type:'json',
			root:'specialDayList'
		}
	}
});