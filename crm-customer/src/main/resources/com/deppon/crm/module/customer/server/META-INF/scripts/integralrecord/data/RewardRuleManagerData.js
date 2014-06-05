/**
 * .
 * <p>
 * 判断是否导入测试数据<br/>
 * <p>
 */
(function() {
	var rewardRuleManagerTest = "../scripts/integralrecord/test/RewardRuleManagerDataTest.js";
	if (CONFIG.get('TEST')) {
		document.write('<script type="text/javascript" src="'+ rewardRuleManagerTest + '"></script>');
	}
})();
//积分奖励规则 model
Ext.define('RewardIntegRuleModel',{
	extend:'Ext.data.Model',
	fields:[
		//积分率
		{name:'fraction',type: 'string', defaultValue: null},
		//奖励积id
		{name:'id',type: 'string'},
		//奖励积分数值
		{name:'pointvalue',type: 'number'},
		//奖励类型
		{name:'ruletype',type: 'string'},
		//积分类型
		{name:'integType',type: 'string'},
		//奖励名称
		{name:'rulename',type: 'string'},
		//会员等级限制
		{name:'memlevelrest',type: 'string'},
		//奖励人数限制 wei
		{name:'limitperson',type: 'string'},
		//奖励人数限制频率
		{name:'limitfrequency',type: 'string'},
		//奖励人数限制频率单位
		{name:'limitfreunit',type: 'string'},
		//限制奖励次数
		{name:'limittimes',type: 'string'},
		//奖励次数限制频率
		{name:'limitrestfreq',type: 'string'},
		//奖励次数限制频率单位
		{name:'limittimeunit',type: 'string'},
		//积分生效日期
		{name:'pointbegintime',type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null},
		//积分结束日期
		{name:'pointendtime',type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null},
		//描述
		{name:'pointdesc',type: 'string'},

		{name:'cname',type:'string'}, 
	    {name:'createDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:null}, 
	    {name:'modifyDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:null},
	    {name:'mname',type:'string'}]
});
//积分奖励规则 store
Ext.define('RewardIntegRuleStore',{
	extend:'Ext.data.Store',
	model:'RewardIntegRuleModel',
	pageSize:20,
//	autoLoad:true,
	proxy:{
		type:'ajax',
		api:{
			read:'searchRewardIntegRuleList.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'rewardIntegRuleList',
			totalProperty:'totalCount'
		}
	}
});
Ext.define('RewardRuleManagerData', {
	rewardIntegRuleStore:null,//奖励规则store
	ruletypeStore:null,
	integTypeStore:null,
	// 积分类型
	getIntegTypeStore : function() {
		if (this.integTypeStore == null) {
			this.integTypeStore = getDataDictionaryByName(DataDictionary,'CUST_RULETYPE'); 
		}
		return this.integTypeStore;
	},
	// 奖励类型
	getRuletypeStore : function() {
		if (this.ruletypeStore == null) {
			this.ruletypeStore = getDataDictionaryByName(DataDictionary,'AWARD_TYPE'); 
		}
		return this.ruletypeStore;
	},
	getRewardIntegRuleStore:function(){
		if(this.rewardIntegRuleStore == null){
			this.rewardIntegRuleStore = Ext.create('RewardIntegRuleStore');
		}
		return this.rewardIntegRuleStore;
	},
	//新增奖励规则
	saveRewardIntegRule:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('saveRewardIntegRule.action',params,successFn,failFn);
	},
	//修改奖励规则
	updateRewardIntegRule:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('updateRewardIntegRule.action',params,successFn,failFn);
	},
	//积分奖励
	integralReward:function(params,successFn,failFn){
	}
});