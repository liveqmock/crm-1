/**
 * 会员共用Data层
 */
// 判断是否导入测试数据
(function() {
	var contractBasicDataTest = "../scripts/membercust/test/ContractBasicDataTest.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + contractBasicDataTest + '"></script>');
	}
})();

// 获得当前用户
var CurrentUser = {};
// 审批数据model
Ext.define('PrefrentialDealModel',{
	extend:'Ext.data.Model',
	fields:[
	        {name:'id',type:'string'},
			// 方案名称
			{name:'dealName',type:'string'},
			// 创建人
			{name:'createUser',type:'string'},
			// 修改人
			{name:'modifyUser',type:'string'},
			// 方案条目明细
			{name:'dealItems'},
		    // 创建时间
			{name:'createDate',  type: 'date',convert: DpUtil.changeLongToDate},//defaultValue:new Date()},
			// 修改时间
			{name:'modifyDate',  type: 'date',convert: DpUtil.changeLongToDate},//defaultValue:new Date()},
			// 方案生效时间
			{name:'beginTime',type: 'date',convert: DpUtil.changeLongToDate},//defaultValue:new Date()},
			// 方案失效时间
			{name:'endTime',type: 'date',convert: DpUtil.changeLongToDate,defaultValue:new Date(2999,11,31)},
			// 方案编码
			{name:'dealNumber',type:'string'},
			// 方案类型
			{name:'preferType',type:'string'},
			// 操作类型 1为新增，2为修改,3为删除，
			{name:'status',type:'string'}]
});
// 方案详细条目model
Ext.define('PrefrentialDealItemModel',{
	extend:'Ext.data.Model',
	fields:[
	        {name:'id',type:'string'},
			// 方案名称
			{name:'dealName',type:'string'},
			// 创建人
			{name:'createUser',type:'string'},
			// 修改人
			{name:'modifyUser',type:'string'},
		    // 创建时间
			{name:'createDate',  type: 'date',convert: DpUtil.changeLongToDate,defaultValue:new Date()},
			// 修改时间
			{name:'modifyDate',  type: 'date',convert: DpUtil.changeLongToDate,defaultValue:new Date()},
			// 等级
			{name:'degree',  type: 'string'},
			// 最小金额
			{name:'minAmount',  type: 'int'},
			// 最大金额
			{name:'maxAmount',  type: 'int'},
			// 折扣
			{name:'rate',  type: 'number'},
			// 方案id
			{name:'dealId',  type: 'number'},
			// 描述
			{name:'itemDesc',  type: 'string'}]
});
/**
 * 折扣方案Store
 */
Ext.define('PrefrentialDealStore',{
	extend:'Ext.data.Store',
	model:'PrefrentialDealModel',
	pageSize:25,
	actionMethods:'POST',
	autoLoad:true,
	proxy:{
		type:'ajax',
		api:{
			read:'searchAllPrefrentialDeal.action'
		},
		reader:{
			type:'json',
			totalProperty:'totalCount',
			root:'prefrentialDeals'
		}
	}
});
/**
 * 折扣条目Store
 */
Ext.define('PreferentialItemStore',{
	extend:'Ext.data.Store',
	model:'PrefrentialDealItemModel'
});



Ext.define('PrefrentialDealData', {
	// 优惠方案状态store
	prefrentialDealStatusStore:null,
	// 优惠方案store
	prefrentialDealStore:null,
	// 优惠方案条目
	preferentialItemStore:null,
	getPrefrentialDealStatusStore:function(){
		if(this.prefrentialDealStore == null){
			this.prefrentialDealStatusStore = getDataDictionaryByName(DataDictionary,'CONTACT_STATUS'); 
		}
		return this.prefrentialDealStore;
	},
	initPrefrentialDealStore:function(){
		if(this.prefrentialDealStore == null){
			this.prefrentialDealStore = Ext.create('PrefrentialDealStore'); 
		}
		return this.prefrentialDealStore;
	},
	getPrefrentialDealItemStore:function(){
		if(this.preferentialItemStore == null){
			this.preferentialItemStore = Ext.create('PreferentialItemStore'); 
		}
		return this.preferentialItemStore;
	},
	// 保存优惠方案
	savePrefrentialDeal:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('savePrefrentialDeal.action',params,successFn,failFn);
	},
	// 修改优惠方案
	updatePrefrentialDeal:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('savePrefrentialDeal.action',params,successFn,failFn);
	},
	// 删除优惠方案
	deletePrefrentialDeal:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('deletePrefrentialDeal.action',params,successFn,failFn);
	},
	// 激活优惠方案
	activePrefrentialDeal:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('activePrefrentialDeal.action',params,successFn,failFn);
	},
	// 查看优惠方案
	viewPrefrentialDeal:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('viewPrefrentialDeal.action',params,successFn,failFn);
	},
	// 获得当前登录用户
	getCurrentUser:function(){
		Ext.Ajax.request({
			url:'../common/queryUserInfo.action',
			async:false,
			jsonData:{},
			success:function(response){
				var result = Ext.decode(response.responseText);
				if(result.success){
					CurrentUser = result.user;
				}else{
					Ext.Msg.alert('温馨提示',result.message)
				}
			},
			failure:function(response){
				var result = Ext.decode(response.responseText);
				Ext.Msg.alert('温馨提示',result.message)
			}
		});
	}
});
