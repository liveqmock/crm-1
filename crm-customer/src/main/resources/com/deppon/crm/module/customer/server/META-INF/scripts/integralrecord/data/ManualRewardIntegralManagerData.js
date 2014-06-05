/**
 * .
 * <p>
 * 判断是否导入测试数据<br/>
 * <p>
 */
(function() {
	var manualRewardIntegralManagerTest = "../scripts/integralrecord/test/ManualRewardIntegralManagerDataTest.js";
	if (CONFIG.get('TEST')) {
		document.write('<script type="text/javascript" src="'+ manualRewardIntegralManagerTest + '"></script>');
	}
})();
//手动积分奖励 自动查询结果 model
Ext.define('HandRewardIntegralModel',{
	extend:'Ext.data.Model',
	fields:[	
	//积分基数
	{name:'integralBasicNumber',type: 'number'},
	//积分奖励时间
	{name:'rewardDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:null},
	{name:'createUser',type:'string'}, 
    {name:'createDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:null}, 
	{name:'createName',type:'string'}, //奖励者
	{name:'deptName',type:'string'}, //部门
	
	//分数
	{name:'score',type: 'number'},
	//积分类型
	{name:'type',type: 'string'},
	//积分类型
	{name:'fraction',type: 'string'},
	
	//奖励规则
	{name:'rewardIntegral'},
	//积分率
	{name:'rewardIntegral.fraction',type: 'string'},
	//id
	{name:'rewardIntegral.id',type: 'string'},
	//奖励积分数值
	{name:'rewardIntegral.pointvalue',type: 'number'},
	//奖励类型
	{name:'rewardIntegral.ruletype',type: 'string'},
	//积分类型
	{name:'rewardIntegral.integType',type: 'string'},
	//奖励名称
	{name:'rewardIntegral.rulename',type: 'string'},
	//会员等级限制
	{name:'rewardIntegral.memlevelrest',type: 'string'},
	//奖励人数限制
	{name:'rewardIntegral.limitperson',type: 'string'},
	//奖励人数限制频率
	{name:'rewardIntegral.limitfrequency',type: 'string'},
	//奖励人数限制频率单位
	{name:'rewardIntegral.limitfreunit',type: 'string'},
	//限制奖励次数
	{name:'rewardIntegral.limittimes',type: 'string'},
	//奖励次数限制频率
	{name:'rewardIntegral.limitrestfreq',type: 'string'},
	//奖励次数限制频率单位
	{name:'rewardIntegral.limittimeunit',type: 'string'},

	//联系人
	{name:'contact'},	
	//id
	{name:'contact.id',type: 'string'},
	// 联系人编码
	{name:'contact.number',type: 'string'},
	// 联系人姓名
	{name:'contact.name',type: 'string'}]
});
//手动积分奖励 自动查询结果 store
Ext.define('HandRewardIntegralStore',{
	extend:'Ext.data.Store',
	model:'HandRewardIntegralModel',
	pageSize:20,
	autoLoad:true,
	proxy:{
		type:'ajax',
		url:'searchHandRewardIntegralList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'handRewardIntegralList',
			totalProperty:'totalCount'
		}
	}
});
//会员联系人查询条件
Ext.define('ContactCondition',{
	extend:'Ext.data.Model',
	fields:[
	        //联系人编码
	        {name:'contactNum',type:'string'},
	        //联系人姓名
	        {name:'contactName',type:'string'},
	        //会员编码
	        {name:'memberNum',type:'string'},
	        //会员名称
	        {name:'memberName',type:'string'}]
});
//会员联系人查询结果
Ext.define('RewardContactModel',{
	extend:'Ext.data.Model',
	fields:[
	        //联系人id
	        {name:'id',type:'string'},
	        //联系人编码
	        {name:'number',type:'string'},
	        //联系人姓名
	        {name:'name',type:'string'}]
});

/**
 * 会员联系人查询结果store
 */
Ext.define('RewardContactStore',{
	extend:'Ext.data.Store',
	model:'RewardContactModel',
	proxy:{
		type:'ajax',
		url:'searchRewardContact.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'contactList'
		}
	}
});
Ext.define('ManualRewardIntegralManagerData', {
	extend:'RewardRuleManagerData',
	handRewardIntegralStore:null,//手动奖励积分 明细表
	contactListStore:null,//手动奖励积分的 联系人查询结果
	rightContactListStore:null,//待手动奖励积分的 联系人
	//手动奖励积分 明细表
	getHandRewardIntegralStore:function(){
		if(this.handRewardIntegralStore == null){
			this.handRewardIntegralStore = Ext.create('HandRewardIntegralStore');
		}
		return this.handRewardIntegralStore;
	},
	//手动奖励积分的 联系人查询结果 初始化
	initContactListStore:function(beforeLoadFn) {
		if(this.contactListStore == null){
			if(beforeLoadFn != null){
				this.contactListStore = Ext.create('RewardContactStore',{
					listeners:{
						beforeload:beforeLoadFn
					}
				});
			}else{
				this.contactListStore = Ext.create('RewardContactStore');
			}
		}
		return this.contactListStore;
	},
	//手动奖励积分的 联系人查询结果
	getContactListStore:function(){
		return this.contactListStore;
	},
	//待手动奖励积分的 联系人
	getRightContactListStore:function(){
		if(this.rightContactListStore == null){
			this.rightContactListStore = Ext.create('RewardContactStore');
		}
		return this.rightContactListStore;
	},
	//添加奖励
	createHandRewardIntegrals:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('createHandRewardIntegrals.action',params,successFn,failFn);
	}
});