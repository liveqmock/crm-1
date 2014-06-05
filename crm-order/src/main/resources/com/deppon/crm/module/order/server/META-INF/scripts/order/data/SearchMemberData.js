/**
* 数据字典模型model
*/
Ext.define('SearchMemberDataDictionaryModel',{
	extend:'Ext.data.Model',
	fields:['code','codeDesc']
});
SearchMemberData = function(){
};
/**
 * 数据字典数据声明
 */
var SearchMemberDataDictionary = {};
//目标级别 目前级别 客户等级 会员等级
SearchMemberDataDictionary.MEMBER_GRADE=[];
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
			read:'../customer/searchMember4OrderList.action'
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
	searchMemberGradeStore:null,
	searchMemberResultStore:null,
	loginUserDeptListStore:null,
	// 目标级别 目前级别 客户等级 会员等级
	getMemberGradeStore : function() {
		if (this.searchMemberGradeStore == null) {
			this.searchMemberGradeStore = Ext.create('SearchMemberMemberGradeStore', {
				data : SearchMemberDataDictionary.MEMBER_GRADE
			});
		}
		return this.searchMemberGradeStore;
	},
	//获取数据字典
	getDictionary:function(params,successFn,failFn){
		var dictionaryUrl = '../common/queryAllDataDictionaryByKeys.action';
		DButil.requestJsonAjax(dictionaryUrl,params,successFn,failFn);
	},
	//F7会员，联系人查询store
	getSearchMemberResultStore: function() {
		return this.searchMemberResultStore;
	},
	//F7初始化会员，联系人查询store
	initSearchMemberResultStore: function(beforeLoadTransactionFn,storeId) {
		var store = Ext.data.StoreManager.lookup(storeId);
		if(Ext.isEmpty(store)){
			if(beforeLoadTransactionFn != null){
				store = Ext.create('MemberSearchStore',{
					storeId:storeId,
					listeners:{
						beforeload:beforeLoadTransactionFn
					}
				});
				this.searchMemberResultStore = store;
			}else{
				store = Ext.create('MemberSearchStore',{'storeId':storeId});
				this.searchMemberResultStore = store;
			}
		}
		return store;
	}
});
/**.
 * <p>
 * 根据联系人信息查询联系人偏好地址<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-6-16
 */
SearchMemberData.prototype.searchAddressInfoList = function(param,successFn,failureFn){
	var url = '../customer/searchAddressInfoList.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * 派车车队store 订单新增
 */
Ext.define('AddOrderVehicleTeamPointStore',{
	pageSize : 5,
	extend:'Ext.data.Store',
	model:'PointModel',
	proxy:{
		type:'ajax',
		url:'../order/searchBussinessDepts.action',
		actionMethods:'POST',
		timeout:100000,
		reader:{
			type:'json',
			root:'orderSearchView.bussinessDepts',
			totalProperty : 'totalCount'
		}
	}
});