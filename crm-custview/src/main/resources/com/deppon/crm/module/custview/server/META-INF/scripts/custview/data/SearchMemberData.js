/**
*会员共用Data层
*/
//判断是否导入测试数据
(function() {
	var searchMemberDataTest = "../scripts/membercust/test/SearchMemberDataTest.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + searchMemberDataTest + '"></script>');
	}
})();
/**
* 数据字典模型model
*/
Ext.define('SearchMemberDataDictionaryModel',{
	extend:'Ext.data.Model',
	fields:['code','codeDesc']
});
/**
 * 数据字典数据声明
 */
var SearchMemberDataDictionary = {};
////目标级别 目前级别 客户等级 会员等级
//SearchMemberDataDictionary.MEMBER_GRADE=[];
//目标级别 目前级别 客户等级 会员等级
Ext.define('SearchMemberMemberGradeStore', {
	extend : 'Ext.data.Store',
	model : 'SearchMemberDataDictionaryModel',
	data : null
});

//会员联系人查询条件
Ext.define('SearchMemberConditionModel',{
	extend:'Ext.data.Model',
	fields:[
       // 会员所属部门ID
       {name:'deptId'},
	     // 会员客户编码
	     {name:'custNumber'},
	     // 客户名称（企业或个人）
	     {name:'custName'},
	     // 客户等级
	     {name:'custGrad'},
	     // 联系人编码
	     {name:'linkManNumber'},
	     // 联系人姓名
	     {name:'linkManName'},
	     // 手机号码(联系人手机号)
	     {name:'mobile'},
	     //固定电话(联系人固定电话)
	     {name:'telePhone'},
	   //税务登记号
	     {name:'taxregNumber'},
	     //身份证号码
	     {name:'idCard'}]
});
//会员联系人查询结果
Ext.define('SearchMemberResultModel',{
	extend:'Ext.data.Model',
	fields:[
	       /**会员管理 查询结果*/
	        //客户id
	        {name:'custId',type:'string'},
	        //联系人id
	        {name:'contactId',type:'string'},
	        //客户编码
	        {name:'custNum',type:'string'},
	        //客户名称
	        {name:'custName',type:'string'},
	        //客户等级 
	        {name:'custGrade',type:'string'},
	        //用于前台使用，用于分组
	        {name:'custGroup',type:'string'},
	    	// 是否主联系人1为是，0为否
	        {name:'isMainLinkMan',type:'boolean'},
	        //联系人编码
	        {name:'contactNum',type:'string'},
	        //联系人姓名
	        {name:'contactName',type:'string'},
	        //手机号码
	        {name:'mobileNum',type:'string'},
	        //固定电话
	        {name:'telNum',type:'string'},
	        //所属部门 会员管理 查询结果
	        {name:'deptId',type:'string'},
	        //所属部门 会员管理 查询结果
	        {name:'deptName',type:'string'},
	        /**弹出框 会员、联系人 查询结果*/
	        //地址
	        {name:'address',type:'string'},
	        //备注
	        {name:'remark',type:'string'},
	        //工作流状态 1为审批中
	        {name:'status'},
	        /**弹出框 选中 会员、联系人 所需数据*/
	        //行业
	        {name:'trade',type:'string'},
	        //客户类型
	        {name:'custType',type:'string'},
	        //税务登记号
	        {name:'taxregNum',type:'string'},
	        //身份证
	        {name:'idCard',type:'string'},
	        //城市ID
	        {name:'cityId',type:'string'},
	        //城市
	        {name:'cityName',type:'string'}]
});

/**
 * F7 会员联系人查询结果store
 */
Ext.define('MemberSearchStore',{
	extend:'Ext.data.Store',
	model:'SearchMemberResultModel',
	proxy:{
		type:'ajax',
		api:{
			// read:'../customer/searchMemberInfoList.action'
			read:'../customer/searchMemberInfoListFor360.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'memberResultList'
		}
	},
	groupers:[{
		property : 'custGroup',
        direction: 'ASC'
	},{
        property : 'custId',
        direction: 'ASC'
	}]
});
Ext.define('SearchMemberData', {
	id:'SearchMemberDataId',
	searchMemberGradeStore:null,
	searchMemberResultStore:null,
	loginUserDeptListStore:null,
	// 目标级别 目前级别 客户等级 会员等级
	getMemberGradeStore : function() {
		if (this.searchMemberGradeStore == null) {
			this.searchMemberGradeStore = getDataDictionaryByName(DataDictionary,'MEMBER_GRADE'); 
//			this.searchMemberGradeStore = Ext.create('SearchMemberMemberGradeStore', {
//				data : SearchMemberDataDictionary.MEMBER_GRADE
//			});
		}
		return this.searchMemberGradeStore;
	},
	//获取数据字典
	getDictionary:function(params,successFn,failFn){
//		var dictionaryUrl = '../common/queryAllDataDictionaryByKeys.action';
//		DpUtil.requestJsonAjax(dictionaryUrl,params,successFn,failFn);
		Ext.Ajax.request({
			url:'../common/queryAllDataDictionaryByKeys.action',
			async:false,
			jsonData:params,
			success:function(response){
				var result = Ext.decode(response.responseText);
				if(result.success){
					successFn(result);
				}else{
					failFn(result);
				}
			},
			failure:function(response){
				var result = Ext.decode(response.responseText);
				failFn(result);
			}
		});
	},
	//F7会员，联系人查询store
	getSearchMemberResultStore: function() {
		return this.searchMemberResultStore;
	},
	//F7初始化会员，联系人查询store
	initSearchMemberResultStore: function(beforeLoadTransactionFn) {
		if(this.searchMemberResultStore == null){
			if(beforeLoadTransactionFn != null){
				this.searchMemberResultStore = Ext.create('MemberSearchStore',{
					listeners:{
						beforeload:beforeLoadTransactionFn
					}
				});
			}else{
				this.searchMemberResultStore = Ext.create('MemberSearchStore');
			}
		}
		return this.searchMemberResultStore;
	}
});
