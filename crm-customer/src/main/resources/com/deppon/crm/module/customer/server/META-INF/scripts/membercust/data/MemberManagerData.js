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
	     // 潜客来源
	     {name:'custSource'},
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
	     //身份证号码
	     {name:'idCard'},
	     //是否大客户
	     {name:'isKeyCustomer'},
	     //客户性质（潜散客、固定客户）
	     {name:'custGroup'},
	     //客户类别(零担快递)
	     {name:'custCategory'},
	     //行业
	     {name:'tradeId'},
	     //二级行业
	     {name:'secondTrade'}]
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
	        {name:'cityName'},
	        //客户性质(潜散客固定客户)
	        {name:'custGroupPSR'},
	        //是否大客户
	        {name:'isKeyCustomer'},
	        //客户类别(零担快递)
	        {name:'custCategory'}
	        ]
});
/**
 * 信用较差客户配置信息前端展示Model
 */
Ext.define('CustCreditManageModel',{
	extend:'Ext.data.Model',
	fields:[
	        // 序号
	        {name:'rankNumber'},
	        // 实际值
	        {name:'actValue'}
	       ]
});

/**
 * 信用较差客户配置信息传入后台数据Model
 */
Ext.define('CustCreditModel',{
	extend:'Ext.data.Model',
	fields:[
	        // 临欠最大欠款天数
	        {name:'maxOverdraftDay'},
	        // 月结规定时间超过天数
	        {name:'monthEndOvertake'},
	        // 逾期未还款月份
	        {name:'overdueMonth'},
	        // 最早临欠单欠款天数
	        {name:'earliestDay'},
	        // 距离规定结款日期前
	        {name:'beforePaymentdateDay'},
	        // 最长还款周期（月结天数）到期前  几  天
	        {name:'beforeMonthPaymentDay'},
	        // 信用额度使用大于0%
	        {name:'usedcreditrate'},
	        // 营业部临时欠款额度使用%
	        {name:'deptUsedrate'}
	       ]
});
/**
 * 信用较差客户信息 store
 */
Ext.define('CustCreditManageStore',{
	extend:'Ext.data.Store',
	model:'CustCreditManageModel',
	proxy:{
		type:'ajax',
		api:{
			read:'searchCustCredit.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'custCreditInfo'
		}
	}
});

/**
 * 会员查询结果 store
 */
/**
 * 是否Model
 */
Ext.define('IsKeyCustomerModel',{
	extend:'Ext.data.Model',
	fields:['name',{name:'value',type:'string'}]
});
/**
 * 是否控件的store
 */
Ext.define('IsKeyCustomerStore',{
	extend:'Ext.data.Store',
	model:'IsKeyCustomerModel',
	data:[{'name':'是','value':'1'},
	      {'name':'否','value':'0'}]
});
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
	isKeyCustomerStore:null,
	memberSearchResultStore:null,
	//会员查询结果 store
	getIsKeyCustomerStore:function(){
 		if(this.isKeyCustomerStore == null){
 			this.isKeyCustomerStore = Ext.create('IsKeyCustomerStore');
 		}
 		return this.isKeyCustomerStore;
 	},
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
		var acquireMemberByIdUrl='acquireMemberById.action';
		DpUtil.requestJsonAjax(acquireMemberByIdUrl,params,successFn,failFn);
	},
	//通过id查询 会员信息修改
	acquireMember4UpdateById:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('acquireMember4UpdateById.action',params,successFn,failFn);
	},
	//通过会员id获得作废会员信息 会员修改
	acquireInvalidMemberById:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('acquireInvalidMemberById.action',params,successFn,failFn);
	},
	//通过id查询 会员维护信息
	changeMemberDept:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('changeMemberDept.action',params,successFn,failFn);
	},
	//通过id删除会员信息
	deleteMember:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('deleteMember.action',params,successFn,failFn);
	},
	//获得当前登陆用户所在城市
	acquireDeptCity:function(params,successFn,failFn){
		DpUtil.requestJsonAjaxSync('../common/acquireDeptCity.action',params,successFn,failFn);
	}
});
Ext.define('CustCreditManageData',{
	custCreditManageStore:null,
	getCustCreditManageStore:function(){
		return this.custCreditManageStore;
	},
	initCustCreditManageStore:function(beforeLoadFn){
		if(this.custCreditManageStore == null){
			if(beforeLoadFn != null){
				this.custCreditManageStore = Ext.create('CustCreditManageStore',{
					listeners:{
						beforeload:beforeLoadFn
					}
				});
			}else{
				this.custCreditManageStore = Ext.create('CustCreditManageStore');
			}
		}
		return this.custCreditManageStore;
	},
	saveCustCredit:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('updateCustCredit.action',params,successFn,failFn);
	}
});