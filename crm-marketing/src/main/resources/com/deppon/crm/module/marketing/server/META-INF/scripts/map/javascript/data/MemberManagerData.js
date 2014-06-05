/**
*会员共用Data层
*/
//判断是否导入测试数据
(function() {
	var memberManagerDataTest = "../scripts/membercust/test/MemberManagerDataTest.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + memberManagerDataTest + '"></script>');
	}
})();
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
	        {name:'custId'},
	        //客户编码
	        {name:'custNum'},
	        //客户名称
	        {name:'custName'},
	    	// 是否主联系人1为是，0为否
	        {name:'isMainLinkMan',type:'boolean'},
	        //客户等级 
	        {name:'custGrade'},
	        //用于前台使用，用于分组
	        {name:'custGroup'},
	        //联系人编码
	        {name:'contactNum'},
	        //联系人姓名
	        {name:'contactName'},
	        //手机号码
	        {name:'mobileNum'},
	        //固定电话
	        {name:'telNum'},
	        //所属部门 会员管理 查询结果
	        {name:'deptId'},
	        //所属部门 会员管理 查询结果
	        {name:'deptName'},
	        //工作流状态 1为审批中
	        {name:'status'},
	        //版本号
	        {name:'versionNumber'},
	        /**弹出框 会员、联系人 查询结果*/
	        //地址
	        {name:'address'},
	        //备注
	        {name:'remark'},
	        /**弹出框 选中 会员、联系人 所需数据*/
	        //行业
	        {name:'trade'},
	        //客户类型
	        {name:'custType'},
	        //税务登记号
	        {name:'taxregNum'},
	        //身份证
	        {name:'idCard'},
	        //城市ID
	        {name:'cityId'},
	        //城市
	        {name:'cityName'}]
});
/**
 * 会员查询结果 store
 */
Ext.define('MemberSearchResultStore',{
	extend:'Ext.data.Store',
	model:'SearchMemberResultModel',
	pageSize:15,
	proxy:{
		type:'ajax',
		api:{
			read:'searchMemberList.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'memberResultList',
			totalProperty:'totalCount'
		}
	},
	remoteSort: true,
	groupers:[{
        		property : 'custGroup'
        	},{
	            property : 'custId'
        	}]
});
Ext.define('MemberManagerData', {
	extend:'MemberCustBasicData',
	memberSearchResultStore:null,
	//会员查询结果 store
	getMemberSearchStore: function() {
		return this.memberSearchResultStore;
	},
	//会员查询结果 store 初始化
	initMemberSearchStore: function(beforeLoadTransactionFn) {
		if(this.memberSearchResultStore == null){
			if(beforeLoadTransactionFn != null){
				this.memberSearchResultStore = Ext.create('MemberSearchResultStore',{
					listeners:{
						beforeload:beforeLoadTransactionFn
					}
				});
			}else{
				this.memberSearchResultStore = Ext.create('MemberSearchResultStore');
			}
		}
		return this.memberSearchResultStore;
	},
	//通过id查询 会员信息详情查看
	acquireMemberById:function(params,successFn,failFn){
		var acquireMemberByIdUrl='../customer/acquireMemberById.action';
		DpUtil.requestJsonAjax(acquireMemberByIdUrl,params,successFn,failFn);
	},
	//通过id查询 会员维护信息
	changeMemberDept:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('changeMemberDept.action',params,successFn,failFn);
	},
});
