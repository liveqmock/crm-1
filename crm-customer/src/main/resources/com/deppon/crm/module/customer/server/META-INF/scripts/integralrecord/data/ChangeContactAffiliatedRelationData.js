/**
 * .
 * <p>
 * 判断是否导入测试数据<br/>
 * <p>
 */
(function() {
	var changeContactAffiliatedRelationDataTest = "../scripts/integralrecord/test/ChangeContactAffiliatedRelationDataTest.js";
	if (CONFIG.get('TEST')) {
		document.write('<script type="text/javascript" src="'+changeContactAffiliatedRelationDataTest + '"></script>');
	}
})();
//会员积分 model
Ext.define('MemberIntegralModel',{
	extend:'Ext.data.Model',
	fields:[
		{name:'member',defaultValue:null},//会员
		{name:'currentUsableScore',type: 'number'},//本期可用积分
		{name:'currentToUsesScore',type: 'number'},//本期已用积分
		{name:'currentTotalScore',type: 'number'},//本期累计积分
		{name:'totalScore',type: 'number'},//累计积分
		{name:'totalToUsesScore',type: 'number'},//累计已用积分
		{name:'lastDateScore',type: 'date'}//最后积分时间
]
});
//上传附件model
Ext.define('FileInfoModel',{
	extend:'Ext.data.Model',
	fields:[
			// 来源ID
			{name:'sourceId',type: 'string'},
			// 来源类型
			{name:'sourceType',type: 'string'},
			// 文件名称
			{name:'fileName',type: 'string'},
			// 文件类型
			{name:'fileType',type: 'string'},
			// 保存路径
			{name:'savePath',type: 'string'},
			// 文件大小
			{name:'fileSize',type: 'number'}]
});
//会员联系人Store
Ext.define('ContactStore',{
	extend:'Ext.data.Store',
	model:'ContactModel',
	proxy:{
		type:'memory'
	}
});
//附件
Ext.define('FileInfoStore',{
	extend:'Ext.data.Store',
	model:'FileInfoModel',
	proxy:{
		type:'memory'
	}
});
Ext.define('ChangeContactAffiliatedRelationData', {
	extend:'MemberCustBasicData',
	contactLinkStore:null,//联系人编码查询的 联系人store
	contactCustStore:null,//客户编码查询的 联系人store
	fileInfoStore:null,
	//联系人编码查询的 联系人store
	getContactLinkStore:function(){
		if(this.contactLinkStore == null){
			this.contactLinkStore = Ext.create('ContactStore');
		}
		return this.contactLinkStore;
	},
	//客户编码查询的 联系人store
	getContactCustStore:function(){
		if(this.contactCustStore == null){
			this.contactCustStore = Ext.create('ContactStore');
		}
		return this.contactCustStore;
	},
	//附件store 
	getFileInfoStore:function(){
		if (this.fileInfoStore == null) {
			this.fileInfoStore = Ext.create('FileInfoStore'); 
		}
		return this.fileInfoStore;
	},
	//客户编码查询的 联系人
	searchMember:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('searchMember.action',params,successFn,failFn);
	},
	//合并 
	mergeContact:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('mergeContact.action',params,successFn,failFn);
	},
	//下载附件
	downLoadAttachment:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('../common/downLoad.action',params,successFn,failFn);
	}
	
});