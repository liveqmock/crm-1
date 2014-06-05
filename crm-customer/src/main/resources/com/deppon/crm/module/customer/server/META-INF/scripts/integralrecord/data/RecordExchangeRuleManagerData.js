/**
 * .
 * <p>
 * 判断是否导入测试数据<br/>
 * <p>
 */
(function() {
	var recordExchangeRuleManagerDataTest = "../scripts/integralrecord/test/RecordExchangeRuleManagerDataTest.js";
	if (CONFIG.get('TEST')) {
		document.write('<script type="text/javascript" src="'+recordExchangeRuleManagerDataTest + '"></script>');
	}
})();
//审批数据model
Ext.define('GiftModle',{
	extend:'Ext.data.Model',
	fields:[
		//id
		{name:'id',type: 'string'},
		//礼品名称
		{name:'giftName',type: 'string'},
		//所需积分
		{name:'needPoints',type: 'number'},
		//库存数量/发放数量
		{name:'inventNumber',type: 'number'},
		//礼品价值
		{name:'giftValue',type: 'number'},
		//实际价值
		{name:'realValue',type: 'number'},
		//礼品描述
		{name:'giftDesc',type: 'string'},
		//礼品编码
		{name:'giftNumber',type: 'string'},	
		//兑换起始时间
		{name:'beginTime',type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null},
		//兑换截止时间
		{name:'endTime',type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null},
		//是否启动兑换
		{name:'isStart',type: 'boolean'},
		//兑换区域
		{name:'department',defaultValue:null},

		{name:'cname',type:'string'}, 
	    {name:'createDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:null}, 
	    {name:'mname',type:'string'},
	    {name:'modifyDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:null}]
});
//
Ext.define('GiftStore',{
	extend:'Ext.data.Store',
	model:'GiftModle',
	pageSize:20,
	autoLoad:true,
	proxy:{
		type:'ajax',
		api:{
			read:'searchGiftList.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'giftList',
			totalProperty:'totalCount'
		}
	}
});
//审批数据model
Ext.define('AllBizDeptModle',{
	extend:'Ext.data.Model',
	fields:['id','deptCode','deptName']
});
//事业部区域
Ext.define('AllBizDeptStore',{
	extend:'Ext.data.Store',
	model:'AllBizDeptModle',
	autoLoad:true,
	proxy:{
		type:'ajax',
		url:'../organization/queryAllBusiness.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'bizDeptList'
		}
	}
});
Ext.define('RecordExchangeRuleManagerData', {
	giftStore:null,
	allBizDeptStore:null,//事业部
	getGiftStore:function(){
		if(this.giftStore == null){
			this.giftStore = Ext.create('GiftStore');
		}
		return this.giftStore;
	},
	//事业部区域
	getAllBizDeptStore:function(params,successFn,failFn){
		if(this.allBizDeptStore == null){
			this.allBizDeptStore = Ext.create('AllBizDeptStore');
			this.allBizDeptStore.on('load',function(deptStore){
				deptStore.insert(0,Ext.create('AllBizDeptModle',{
					'id':'','deptCode':'','deptName':'全国'
					}));
			});
		}
		return this.allBizDeptStore;
	},
	//新增规则
	saveExchangeRule:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('saveRecordExchangeRule.action',params,successFn,failFn);
	},
	//修改规则
	updateExchangeRule:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('updateRecordExchangeRule.action',params,successFn,failFn);
	},
	//删除规则
	deleteExchangeRule:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('deleteRecordExchangeRule.action',params,successFn,failFn);
	},
	//启用兑换设置
	startExchangeSet:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('startExchangeSet.action',params,successFn,failFn);
	}
});