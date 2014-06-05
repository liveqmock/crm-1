/**
 * .
 * <p>
 * 判断是否导入测试数据<br/>
 * <p>
 */
(function() {
	var boundContactNumDataTest = "../scripts/integralrecord/test/BoundContactNumDataTest.js";
	if (CONFIG.get('TEST')) {
		document.write('<script type="text/javascript" src="'+boundContactNumDataTest + '"></script>');
	}
})();
BoundContactNumData = function(){
};
//网上信息搜索model
Ext.define('WebInfoModel',{
	extend:'Ext.data.Model',
	fields:[
		//id
		{name:'id',type: 'string'},
		// 订单号
		{name:'orderNumber',type: 'string'},
		// 订单来源
		{name:'resource',type: 'string'},
		// 客户id
		{name:'channelCustId',type: 'string'},
		// 发货客户名称
		{name:'shipperName',type: 'string'},
		// 发货联系人名称
		{name:'contactName',type: 'string'},
		// 联系人手机
		{name:'contactMobile',type: 'string'},
		// 联系人电话
		{name:'contactPhone',type: 'string'},
		// 联系地址
		{name:'contactAddress',type: 'string'}]
});
//联系人信息搜索model
Ext.define('ContactViewModel',{
	extend:'Ext.data.Model',
	fields:[
		//客户姓名
		{name:'custName',type: 'string'},	
		//客户等级
		{name:'degree',type: 'string'},
        // 联系人id
        {name:'contact'}, 
        // 联系人编码
        {name:'contact.number',type:'string'}, 
        // 联系人姓名
        {name:'contact.name',type:'string'},
        // 固定电话
        {name:'contact.telPhone',type:'string'},
        // 手机号码
        {name:'contact.mobilePhone',type:'string'},
        // 阿里ID
        {name:'contact.alid',type:'string'},
        // 网营ID
        {name:'contact.onlineBusinessId',type:'string'},
        // 淘宝ID
        {name:'contact.taobId',type:'string'},
        //金蝶友商ID
        {name:'contact.youshangId',type:'string'}
        ]
});


//联系人信息搜索model
Ext.define('RegisterInfoModel',{
	extend:'Ext.data.Model',
	fields:[
		//订单来源
		{name:'custsource',type: 'string'},
		//用户ID
		{name:'userName',type: 'string'},	
		//姓名
		{name:'realName',type: 'string'},
        // 手机
        {name:'telephone'}, 
        // 固话
        {name:'fixedPhone',type:'string'}, 
        // 地址
        {name:'address',type:'string'}
    ]
});

//用户信息列表
Ext.define('RegisterInfoStore',{
	extend:'Ext.data.Store',
	model:'RegisterInfoModel',
	proxy:{
		type:'ajax',
		url:'boundInfos.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'listRegisterInfo'
		}
	}
});

//网上信息搜索store
Ext.define('WebInfoStore',{
	extend:'Ext.data.Store',
	model:'WebInfoModel',
	proxy:{
		type:'ajax',
		url:'searchWebInfo.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'webInfoList'
		}
	}
});
//联系人信息搜索store
Ext.define('ContactInfoStore',{
	extend:'Ext.data.Store',
	model:'ContactViewModel',
	proxy:{
		type:'ajax',
		url:'searchContactInfo.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'contactInfoList'
		}
	}
});
Ext.define('BoundContactNumData', {
	webInfoStore:null,//网上信息搜索store
	contactInfoStore:null,//联系人信息搜索store
	//网上信息搜索store
	getWebInfoStore:function(){
		if(this.webInfoStore == null){
			this.webInfoStore = Ext.create('WebInfoStore');
		}
		return this.webInfoStore;
	},
	//联系人信息搜索store
	getContactInfoStore:function(){
		if(this.contactInfoStore == null){
			this.contactInfoStore = Ext.create('ContactInfoStore');
		}
		return this.contactInfoStore;
	},
	//发送验证短信
	sendCheckMessage:function(params,successFn,failFn){
		DButil.requestJsonAjax('sendCheckMessage.action',params,successFn,failFn);
	},
	//绑定联系人编码
	boundContactNum:function(params,successFn,failFn){
		DButil.requestJsonAjax('boundContactNum.action',params,successFn,failFn);
	}
});
//绑定联系人查询官网信息
BoundContactNumData.prototype.searchWebInfo = function(param,successFn,failureFn){
	var url ='searchWebInfo.action';
	 DButil.requestJsonAjax(url,param,successFn,failureFn);
}


/**.
 * <p>
 * 绑定联系人编码-查询用户
 * <p>
 * @author 张登
 * @时间 2012-3-26
 */
Ext.define('BoundInfosStore',{
	extend:'Ext.data.Store',
	model:'WebInfoModel',
	proxy:{
		type:'ajax',
		url:'../order/boundInfos.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'listRegisterInfo'
		}
	}
});

/**.
 * <p>
 * 联系人编码解除绑定
 * <p>
 * @returns unboundContactNumber
 * @author 张登
 * @时间 2012-3-11
 */
BoundContactNumData.prototype.unboundContactNumber = function(param,successFn,failureFn){
	var url = '../order/unboundContactNumber.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};


/**.
 * <p>
 * 联系人编码解除绑定 查询联系人<br/>
 * <p>
 * @returns queryContactViewByNum
 * @author 张登
 * @时间 2012-3-11
 */
BoundContactNumData.prototype.queryContactViewByNum = function(param,successFn,failureFn){
	var url = '../order/queryContactViewByNum.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};