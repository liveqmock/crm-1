/**
*散客Data层
*/

//判断是否导入测试数据
(function() {
	var scatterUpgradeDataTest = "../scripts/membercust/test/ScatterUpgradeDataTest.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + scatterUpgradeDataTest + '"></script>');
	}
})();
/**
 * 散客升级列表Store
 */
Ext.define('ScatterUpGradeListStore',{
	extend:'Ext.data.Store',
	model:'UpGradeListModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		api:{
			read:'searchScatterUpgradeList.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'upGradeCustlist',
			totalProperty:'totalCount'
		}
	}
});
//散客升级列表中 目前等级
var ScatterGradeStoreData = [{code:'SCATTER_GRADE',codeDesc:'散客'}];
//散客升级列表中 目前等级
Ext.define('ScatterGradeStore',{
	extend:'Ext.data.Store',
	model:'DataDictionaryModel',
	autoLoad:true,
	proxy:{
		type:'memory',
		data:ScatterGradeStoreData
		}
});
//目前级别(会员升级列表)
DataDictionary.SCATTER_GRADE = [{code:'SCATTER_GRADE',codeDesc:'散客'}];
//统计时间 data（查询范围为六个月）
var myDate = new Date;
var result =[];
var monthDate = '';
var monthDateValue;
var yearDateValue;
var statisticsTimeCell = '';
for (var i = 0; i < 6; i++) {
	monthDateValue=myDate.getMonth()+1-i;
	yearDateValue=myDate.getFullYear();
	if(monthDateValue<1){
		monthDateValue=monthDateValue+12;
		yearDateValue=yearDateValue-1;
	}
	if(monthDateValue<10){
		monthDate = '0'+monthDateValue;
	}else{
		monthDate = ''+monthDateValue;
	}
	statisticsTimeCell={dateValue:yearDateValue+'-'+monthDate,dateDesc:yearDateValue+'-'+monthDate};
	result.push(statisticsTimeCell);
}

/**
* 统计时间model
*/
Ext.define('StatisticsTimeModel',{
	extend:'Ext.data.Model',
	fields:['dateValue','dateDesc']
});
//统计时间store
Ext.define('StatisticsTimeStore', {
	extend : 'Ext.data.Store',
	model : 'StatisticsTimeModel',
	autoLoad:true,
	proxy:{
		type:'memory',
		data:result
	}
});
Ext.define('ScatterUpgradeData',{
	extend:'MemberCustBasicData',
	scatterUpgradeStore:null,
	scatterGradeStore:null,
    //散客升级列表统计时间store
    statisticsTimeStore:null,
	//散客升级列表查询结果store
	getScatterUpGradeCustStore: function() {
		return this.scatterUpGradeCustStore;
	},
	//初始化散客升级列表查询结果store
	initScatterUpGradeCustStore: function(beforeLoadTransactionFn) {
		if(this.scatterUpGradeCustStore == null){
			if(beforeLoadTransactionFn != null){
				this.scatterUpGradeCustStore = Ext.create('ScatterUpGradeListStore',{
					listeners:{
						beforeload:beforeLoadTransactionFn
					}
				});
			}else{
				this.scatterUpGradeCustStore = Ext.create('ScatterUpGradeListStore');
			}
		}
		return this.scatterUpGradeCustStore;
	},
	//通过id查询升级散客信息
	searchScatterUpgradeById:function(params,successFn,failFn){
		var searchScatterUpgradeByIdUrl='searchScatterUpgradeById.action';
		DpUtil.requestJsonAjax(searchScatterUpgradeByIdUrl,params,successFn,failFn);
	},
	//待升级散客增加备注
	addRemark2UpgradeScatter:function(params,successFn,failFn){
		var addRemark2UpgradeScatterUrl='addRemark2UpgradeScatter.action';
		DpUtil.requestJsonAjax(addRemark2UpgradeScatterUrl,params,successFn,failFn);
	},
	//散客升级列表查询条件 目前等级store  SCATTER-GRADE
	getScatterGradeStore : function() {
		if (this.scatterGradeStore == null) {
			this.scatterGradeStore = Ext.create('ScatterGradeStore')
		}
		return this.scatterGradeStore;
	},
	//通过散客升级记录查询会员信息
	searchMemberFromScatter:function(params,successFn,failFn){
		var searchMemberFromScatterUrl='searchMemberFromScatter.action';
		DpUtil.requestJsonAjax(searchMemberFromScatterUrl,params,successFn,failFn);
	},
	//散客升级列表统计时间
	getStatisticsTimeStore:function(){
		if(this.statisticsTimeStore==null){
			this.statisticsTimeStore = Ext.create('StatisticsTimeStore');
		}
		return this.statisticsTimeStore;
	},
	//实时创建会员判断是否是会员
	checkIsExistMember:function(params,successFn,failFn){
		var checkIsExistMemberUrl='checkIsExistMember.action';
		DpUtil.requestJsonAjax(checkIsExistMemberUrl,params,successFn,failFn);
	},
	//实时创建会员校验是否可以创建会员
	acquireImplementMemberView:function(params,successFn,failFn){
		var acquireImplementMemberViewUrl='acquireImplementMemberView.action';
		DpUtil.requestJsonAjax(acquireImplementMemberViewUrl,params,successFn,failFn);
	},
	//删除散客升级类表中散客信息
	deleteScatterUpgrade:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('deleteScatterUpgrade.action',params,successFn,failFn);
	}
});