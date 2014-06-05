/**
*会员共用Data层
*/
//判断是否导入测试数据
(function() {
	var memberCustBasicDataTest = "../scripts/membercust/test/MemberCustBasicDataTest.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + memberCustBasicDataTest + '"></script>');
	}
})();

/**
* 数据字典模型model
*/
Ext.define('DataDictionaryModel',{
	extend:'Ext.data.Model',
	fields:['code','codeDesc']
});
/**
 * 数据字典数据声明
 */
var DataDictionary = {};
//自定义
DataDictionary.LINKMANTYPE = [{'code':'0','codeDesc':'财务联系人'},
                     {'code':'1','codeDesc':'业务联系人'},
                     {'code':'2','codeDesc':'发货联系人'},
                     {'code':'3','codeDesc':'收货联系人'},
                     {'code':'4','codeDesc':'协议联系人'}];
/**
* 当前登陆用户 可切换部门 model
*/
Ext.define('CurrentUserDeptListModel',{
	extend:'Ext.data.Model',
	fields:['deptId','deptName']
});
var currentUserDeptList = [];
/**
* 当前登陆用户 可切换部门 store
*/
Ext.define('CurrentUserDeptListStore', {
	extend : 'Ext.data.Store',
	model : 'CurrentUserDeptListModel',
	data : null
});
//会员归属部门变更model
Ext.define('ChangeMemberDeptModel',{
	extend:'Ext.data.Model',
	fields:[//会员id
			{name:'memberId',type:'string'},
			//会员编码
			{name:'memberNumber',type:'string'},
			//所属部门id
			{name:'fromDeptId',type:'string'},
			//所属部门姓名
			{name:'fromDeptName',type:'string'},
			//变更部门id
			{name:'toDeptId',type:'string'},
			//变更部门名称
			{name:'toDeptName',type:'string'},
			//申请原因
			{name:'reason',type:'string'},
			//工作流id
			{name:'workFlowId',type:'number',defaultValue:-1}]
});
//升级列表查询条件model
Ext.define('UpgradeConditionModel',{
	extend: 'Ext.data.Model',
	fields : [
	         //所属部门ID
			{name:'dept'},
			//统计时间
			{name:'statisticsTime'},
			//客户名称
			{name:'custName'},
			//联系人姓名
			{name:'contactName'},
			//联系人手机
			{name:'phone'},
			//联系人电话
			{name:'tel'},
			//目前级别
			{name:'nowLevel'},
			//目标级别
			{name:'targetLevel'}
			]
});
//散客升级列表查询结果model
Ext.define('UpGradeListModel',{
	extend:'Ext.data.Model',
	fields:[
	        //ID
			{name:'id'},
	        // 状态
	        {name:'status'},
	        // 所属部门ID
	        {name:'belongDeptId'},
	        /**查询结果显示内容*/
	        //所属部门
	        {name:'belongdeptName'},
	        //客户名称
	        {name:'custName'},
	        //联系人姓名
	        {name:'contactName'},
	        //手机
	        {name:'contactPhone'},
	        //电话
	        {name:'contactTel'},
	        // 目前级别--为散客
	        {name:'currentlevel'},
	        //目标级别
	        {name:'targetLevel'},
	        // 备注信息
	        {name:'remark'},
	        //创建时间
			{name:'createDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:null}, 
			 // 修改时间
			{name:'modifyDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:null}]
});
//联系人偏好地址model
Ext.define('PreferenceAddressModel',{
	extend:'Ext.data.Model',
	fields:[
	        {name:'id',type:'string'},
	    	// 接送货地址ID
	        {name:'shuttleAddress',type:'string'},
	        {name:'shuttleAddressId',type:'string'},
	    	// 地址类型
	    	{name:'addressType',type:'string'},
	    	// 地址
	    	{name:'address',type:'string'},
	    	// 偏好起始时间
	    	{name:'startTime',type:'date',convert: DpUtil.changeLongToDate,defaultValue:new Date()},
	    	// 偏好截止时间
	    	{name:'endTime',type:'date',convert: DpUtil.changeLongToDate,defaultValue:new Date()},
	    	// 发票要求
	    	{name:'billRequest',type:'string'},
	    	// 停车费用
	    	{name:'hasStopCost',type:'boolean'},
	    	// 付款方式
	    	{name:'payType',type:'string'},
	    	// 是否送货上楼 1（true）为是，（false）0为否
	    	{name:'isSendToFloor',type:'boolean'},
	    	// 其它要求
	    	{name:'otherNeed',type:'string'}, 
	    	{name:'createUser',type:'string'}, 
			{name:'createDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:new Date()}, 
			{name:'modifyDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:new Date()},
			{name:'modifyUser',type:'string'},
			//是否为主地址
			{name:'isMainAddress',type:'boolean'},
			//
			{name:'',type:'string'}
	     ],
		 validations:[{type: 'presence',  field: 'addressType'},
		              {type: 'presence',  field: 'address'}]
});
//帐号Model
Ext.define('AccountModel',{
	extend:'Ext.data.Model',
	fields:[
	        {name:'id',type:'string'},
	        // 开户行ID
	        {name:'bankId',type:'string'},
	        // 开户行
	        {name:'bank',type:'string'},
	        // 支行名称ID
	        {name:'subBanknameId',type:'string'},
	        // 支行名称
	        {name:'subBankname',type:'string'},
	        // 是否默认账户 1为是，0为否
	        {name:'isdefaultaccount',type:'boolean'},
	        // 银行账号
	        {name:'bankAccount',type:'string'},
	        // 开户姓名
	        {name:'countName',type:'string'},
	        // 账号与客户关系
	        {name:'relation',type:'string'},
	        // 开户省份
	        {name:'bankProvinceId',type:'string'},
	        // 开户省
	        {name:'bankProvinceName',type:'string'},
	        // 开户城市
	        {name:'bankCityId',type:'string'},
	        // 开户市
	        {name:'bankCityName',type:'string'},
	        // 账户性质----建立数据字典----
	        {name:'accountNature',type:'string'},
	        // 账户用途----建立数据字典-----
	        {name:'accountUse',type:'string'},
	    	// 联系手机
	        {name:'linkManMobile',type:'string'},
	    	//联系人固话
	        {name:'linkManPhone',type:'string'},
	        // 财务联系人姓名ID
	        {name:'financeLinkmanId',type:'string'},
	        // 财务联系人姓名
	        {name:'financeLinkman',type:'string'},
	        {name:'createUser',type:'string'},
			{name:'createDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:new Date()}, 
			{name:'modifyDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:new Date()},
			{name:'modifyUser',type:'string'}
	        ]
//	        ,//取消必填限制
//	   	 validations:[{type: 'presence',  field: 'bankId'},
//		              {type: 'presence',  field: 'bank'},
//		              {type: 'presence',  field: 'subBanknameId'},
//		              {type: 'presence',  field: 'subBankname'},
//		              {type: 'presence',  field: 'isdefaultaccount'},
//		              {type: 'presence',  field: 'bankAccount'},
//		              {type: 'presence',  field: 'countName'},
//		              {type: 'presence',  field: 'relation'},
//		              {type: 'presence',  field: 'bankProvinceId'},
//		              {type: 'presence',  field: 'accountNature'},
//		              {type: 'presence',  field: 'accountUse'},
//		              {type: 'presence',  field: 'bankCityId'},
//		              {type: 'presence',  field: 'financeLinkman'}
//		              ]
});
//接送货地址Model
Ext.define('ShuttleAddressModel',{
	extend:'Ext.data.Model',
	fields:[
			{name:'id',type:'string'},
			// 地址类型
			{name:'addressType',type:'string'},
			// 详细地址
			{name:'address',type:'string'},
			// 省份Id
			{name:'province',type:'string'},
			// 省份name
			{name:'provinceName',type:'string'},
			// 城市Id
			{name:'city',type:'string'},
			// 城市name
			{name:'cityName',type:'string'},
			// 区县Id
			{name:'area',type:'string'},
			// 区县name
			{name:'areaName',type:'string'},
			{name:'createUser',type:'string'},
			{name:'createDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:new Date()}, 
			{name:'modifyDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:new Date()},
			{name:'modifyUser',type:'string'}
	        ],
   	 validations:[{type: 'presence',  field: 'addressType'},
	              {type: 'presence',  field: 'address'},
	              {type: 'presence',  field: 'province'},
	              {type: 'presence',  field: 'city'},
	              {type: 'presence',  field: 'area'}]
});
//会员联系人Model
Ext.define('ContactModel',{
	extend:'Ext.data.Model',
	fields:[
	        {name:'id',type:'string'},
	        // 联系人编码
	        {name:'number',type:'string'}, 
	        // 联系人姓名
	        {name:'name',type:'string'},
	        //证件类型
	        {name:'cardTypeCon',type:'string'},
	        // 身份证号
	        {name:'idCard',type:'string'},
	        // 联系人类型
	        {name:'linkmanType',type:'string'},
	        // 是否主联系人
	        {name:'isMainLinkMan',type:'boolean'},
	        // 性别 
	        {name:'sex',type:'string'},
	        // 固定电话
	        {name:'telPhone',type:'string'},
	        // 手机号码
	        {name:'mobilePhone',type:'string'},
	        // 职务
	        {name:'duty',type:'string'},
	        // 任职部门
	        {name:'dutyDept',type:'string'},
	        // 出生日期
	        {name:'bornDate',type:'date',convert: DpUtil.changeLongToDate},
	        // 获知公司途径
	        {name:'gainave',type:'string'},
	        // 物流决定权 
	        {name:'decisionRight',type:'string'},
	        // 籍贯
	        {name:'nativePlace',type:'string'},
	        // 个人爱好
	        {name:'personLove',type:'string'},
	        // 民族
	        {name:'folk',type:'string'},
	        // Email地址
	        {name:'email',type:'string'},
	        // QQ号码
	        {name:'qqNumber',type:'string'},
	        // MSN
	        {name:'msn',type:'string'},
	        // 旺旺号
	        {name:'ww',type:'string'},
	        // 阿里ID
	        {name:'alid',type:'string'},
	        // 网营ID
	        {name:'onlineBusinessId',type:'string'},
	        // 淘宝ID
	        {name:'taobId',type:'string'},
	        // 金蝶友商ID
	        {name:'youshangId',type:'string'},
	        // 关联的联系人id
	        {name:'linkManId',type:'string'},
	        {name:'createUser',type:'string'},
			{name:'createDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:new Date()}, 
			{name:'modifyDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:new Date()},
			{name:'modifyUser',type:'string'},
			{name:'createSource',type:'int',defaultValue:0}  //前端使用，1表示从散客升级列表而来,2表示从实时创建会员而来
			//preferenceAddressList 字段用于存储联系人地址偏好设置
			//alterLinkmanData  字段用于存储联系人和偏好地址修改的信息
			//delAddressHobbyData 字段用于存储修改联系人时删除的偏好地址信息
	        ],
	 validations:[{type: 'presence',  field: 'number'},
	              {type: 'presence',  field: 'name'},
//	              {type: 'presence',  field: 'isMainLinkMan'},
//	              {type: 'presence',  field: 'idCard'},//需求变更联系人身份证号只有个人用户的主联系人才为必填
	              {type: 'presence',  field: 'linkmanType'},
	              {type: 'presence',  field: 'sex'}]
});
//会员model
Ext.define('MemberCustomerModel',{
	extend:'Ext.data.Model',
	fields:[
	     {name:'id',type:'string'},
	     // 客户ID
	     {name:'custId',type:'string'},
	     // 主要联系人ID
	     {name:'contactId',type:'string'},
	     // 客户编码
	     {name:'custNumber',type:'string'},
	     // 所属部门
	     {name:'deptId',type:'string'},
	     // 所属部门名称
	     {name:'deptName',type:'string'},
	     // 客户等级
	     {name:'degree',type:'string'},
	     // 客户姓名
	     {name:'custName',type:'string'},
	     // 客户行业
	     {name:'tradeId',type:'string'},
	     // 客户类型
	     {name:'custType',type:'string'},
         //证件类型
         //{name:'cardTypeCust',type:'string'},
	     // 企业税务登记号
	     {name:'taxregNumber',type:'string'},
	     // 公司性质
	     {name:'companyProperty',type:'string'},
	     // 客户属性
	     {name:'custNature',type:'string'},
	     // 所在省份ID
	     {name:'provinceId',type:'string'},
	     // 所在省份
	     {name:'province',type:'string'},
	     // 所在城市ID
	     {name:'cityId',type:'string'},
	     // 所在城市
	     {name:'city',type:'string'},
	     // 客户地址
	     {name:'registAddress',type:'string'},
	     // 是否特殊客户 1为特殊客户，0为一般客户
	     {name:'isSpecial',type:'boolean'},
	     // 是否允许联系人兑换积分1为是，0为否
	     {name:'isRedeempoints',type:'boolean'},
	     // 是否部门重要客户1为是，0为否
	     {name:'isImportCustor',type:'boolean'},
	     // 客户潜力类型
	     {name:'custPotentialType',type:'string'},
	     // 是否接受德邦营销信息
	     {name:'isAcceptMarket',type:'boolean'},
	     // 品牌价值
	     {name:'brandWorth',type:'string'},
	     // 来源渠道
	     {name:'channelSource',type:'string'},
	     // 偏好渠道
	     {name:'channel',type:'string'},
	     // 偏好服务
	     {name:'preferenceService',type:'string'},
	     // 上一年公司规模
	     {name:'companyScop',type:'string'},
	     // 上一年公司利润
	     {name:'lastYearProfit',type:'number'},
	     // 上一年财务公司收入
	     {name:'lastYearIncome',type:'number'},
	     // 是否集中结算1为是，0为否
	     {name:'isFocusPay',type:'boolean'},
	     // 集中结算部门
	     {name:'focusDeptId',type:'string'},
	     // 集中结算部门 名称
	     {name:'focusDeptName',type:'string'},
	     // 发票抬头
	     {name:'billTitle',type:'string'},
	     // 是否母公司1为是，0为否
	     {name:'isParentCompany',type:'boolean'},
	     // 客户所属母公司(客户)名称ID
	     {name:'parentCompanyId',type:'string'},
	     // 客户所属母公司(客户)名称
	     {name:'parentNumber',type:'string'},
	     // 注册资金
	     {name:'registerFund',type:'number'},
	     // 临欠额度
	     {name:'procRedit',type:'number'},
	     // 推荐人
	     {name:'recommendCust',type:'string'},
	     // 备注
	     {name:'remark',type:'string'}, 
	     {name:'createUser',type:'string'}, 
		 {name:'createDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:new Date()}, 
		 {name:'modifyDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:new Date()},
		 {name:'modifyUser',type:'string'},
		 //可使用月结额度
		 {name:'surplusMonthlyStatementMoney',type:'number'}
	   ],
	   //客户标签
		 hasMany:{
				model:'CustLabelModel',
				name:'custLabels'
			}
});
//实时创建会员校验后返回结果model
Ext.define('WayBillInfoModel',{
	extend:'Ext.data.Model',
	fields:[
			//序号
			{name:'id'},
			//运单号
			{name:'wayBillNumber'},
			//金额  = 预付金额 + 到达金额 - 劳务费 - 代收货款
			{name:'money',type:'number'},
			//系数  出发为1，到达为 2/3
			{name:'ratio',type:'number'}]
});
//审批数据model
Ext.define('ApproveDataModel',{
	extend:'Ext.data.Model',
	fields:[
	        {name:'id',type:'string'},
			// 实体类名
			{name:'className',type:'string'},
			// 实体类ID
			{name:'classId',type:'string'},
			// 字段名
			{name:'fieldName',type:'string'},
			// 新值
			{name:'newValue',type:'string'},
			// 旧值
			{name:'oldValue',type:'string'},
			// 工作流id
			{name:'workFlowId',type:'string'},
			// 操作类型 1为新增，2为修改,3为删除，
			{name:'handleType',type:'int'}]
});
//实时创建会员校验后返回结果model
Ext.define('QualificationViewModel',{
	extend:'Ext.data.Model',
	fields:[
	        //id
	        {name:'id'},
			//运单信息
			{name:'wayBillList'},
			//合计金额
			{name:'totalMoney',type:'number'},
			//合格
			{name:'qualified',type: 'boolean'},
			//客户级别
			{name:'custLevel'}]
});
//实时创建会员校验后返回结果model
Ext.define('ImplementMemberViewModel',{
	extend:'Ext.data.Model',
	fields:[
	         //id
	         {name:'id'},
		     // 资格校验结果
		     {name:'qualificationView'},
		     // 会员信息
		     {name:'member'}]
});
//开户省，市，名称 model
Ext.define('BankModel',{
	extend:'Ext.data.Model',
	fields:['id', 'name','ID', 'NAME']
});
/**
 * 开户省 store
 */
Ext.define('BankProvinceStore',{
	extend:'Ext.data.Store',
	model:'BankModel',
	autoLoad :true,
	proxy:{
		type:'ajax',
		url:'searchBankProvince.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'list'
		}
	}
});
/**
 * 开户市 store
 */
Ext.define('BankCityStore',{
	extend:'Ext.data.Store',
	model:'BankModel',
	proxy:{
		type:'ajax',
		url:'searchBankCityByProvinceId.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'list'
		}
	}
});
/**
 * 开户名称	 store
 */
Ext.define('BankNameStore',{
	extend:'Ext.data.Store',
	model:'BankModel',
//	autoLoad :true,
	proxy:{
		type:'ajax',
		url:'searchBankName.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'list'
		}
	}
});
/**
 * 实时创建会员检验成功后store
 */
Ext.define('ImplementMemberViewStore',{
	extend:'Ext.data.Store',
	model:'ImplementMemberViewModel',
	proxy:{
		type:'ajax',
		api:{
			read:''
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'implementMemberView'
		}
	}
});
/**
 * 会员store
 */
Ext.define('MemberCustomerStore',{
	extend:'Ext.data.Store',
	model:'MemberCustomerModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		api:{
			read:''
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'',
			totalProperty:'totalCount'
		}
	}
});
/**
 * 运单store
 */
Ext.define('WayBillInfoStore',{
	extend:'Ext.data.Store',
	model:'WayBillInfoModel',
	data : []
});
/**
 * 集中结算部门model
 */
Ext.define('FocusDeptStoreModel',{
	extend:'Ext.data.Model',
	fields:['id','deptName','provinceName']
});

/**
 * 集中结算部门store
 */
Ext.define('FocusDeptStore',{
	extend:'Ext.data.Store',
	model:'FocusDeptStoreModel',
	data : []
});
/**
 * 创建特殊会员所属部门store
 */
Ext.define('SpecialDeptStore',{
	extend:'Ext.data.Store',
	model:'FocusDeptStoreModel',
	data : []
});
Ext.define('MemberCustBasicData', {
	// 目标级别 目前级别 客户等级 会员等级
	memberGradeStore : null,
	// 客户行业
	tradeStore : null,
	// 客户属性
	customerNatureStore : null,
	// 公司性质
	compNatureStore : null,
	// 客户类型
	customerTypeStore : null,
	// 客户潜力类型
	cargoPotentialStore : null,
	custPotentialStore : null,
	// 上一年公司规模
	firmSizeStore : null,
	// 信用等级
	creditGradeStore : null,
	// 地址类型
	addressTypeStore : null,
	// 联系人类型 已取消
	// 物流决定权
	logistDeciStore : null,
	// 付款方式
	payWayStore : null,
	// 来源渠道 偏好渠道
	preferenceChannelStore : null,
	// 偏好服务
	preferenceServiceStore : null,
	// 发票要求
	billRequireStore : null,
	// 账户性质
	accountNatureStore : null,
	// 账户用途
	accountUseStore : null,
	// 性别
	genderStore : null,
	//自定义数据字典-联系人类型
    linkmanTypeData:DataDictionary.LINKMANTYPE,
     linkWayStore:null,
     //当前用户 权限 可切换部门 store
     currentUserDeptListStore:null,
     //创建特殊会员 所属部门
     specialDeptStore:null,
     //结算部门
     focusDeptStore:null,
     //运单store
     wayBillInfoStore:null,
     //目标级别（降级）
     targetGradDownStore:null,
     //目标级别（升级）
     targetGradUpStore:null,
     //目前级别（升级）
     nowGradUpStore:null,
     //是否接受营销记录
     marketInfoStore:null,
     //证件类型
     cardTypeCustStore:null,
     //证件类型 联系人
     cardTypeConStore:null,
     //证件类型 联系人 新增和修改时
     cardTypeConNotViewStore:null,
	// 目标级别 目前级别 客户等级 会员等级
	getMemberGradeStore : function() {
		if (this.memberGradeStore == null) {
			this.memberGradeStore = getDataDictionaryByName(DataDictionary,'MEMBER_GRADE');
//			this.memberGradeStore = Ext.create('MemberGradeStore', {
//				data : DataDictionary.MEMBER_GRADE
//			});
		}
		return this.memberGradeStore;
	},
	// 客户行业
	getTradeStore : function() {
		if (this.tradeStore == null) {
			this.tradeStore = getDataDictionaryByName(DataDictionary,'TRADE');
//			this.tradeStore = Ext.create('TradeStore', {
//				data : DataDictionary.TRADE
//			});
		}
		return this.tradeStore;
	},
	// 客户属性
	getCustomerNatureStore : function() {
		if (this.customerNatureStore == null) {
			this.customerNatureStore = getDataDictionaryByName(DataDictionary,'CUSTOMER_NATURE');
//			this.customerNatureStore = Ext.create('CustomerNatureStore', {
//				data : DataDictionary.CUSTOMER_NATURE
//			});
		}
		return this.customerNatureStore;
	},
	// 公司性质
	getCompNatureStore : function() {
		if (this.compNatureStore == null) {
			this.compNatureStore = getDataDictionaryByName(DataDictionary,'COMP_NATURE');
//			this.compNatureStore = Ext.create('CompNatureStore', {
//				data : DataDictionary.COMP_NATURE
//			});
		}
		return this.compNatureStore;
	},
	// 客户类型
	getCustomerTypeStore : function() {
		if (this.customerTypeStore == null) {
			this.customerTypeStore = getDataDictionaryByName(DataDictionary,'CUSTOMER_TYPE');
//			this.customerTypeStore = Ext.create('CustomerTypeStore', {
//				data : DataDictionary.CUSTOMER_TYPE
//			});
		}
		return this.customerTypeStore;
	},
	// 货量潜力类型
	getCargoPotentialStore : function() {
		if (this.cargoPotentialStore == null) {
			this.cargoPotentialStore = getDataDictionaryByName(DataDictionary,'CARGO_POTENTIAL');
//			this.cargoPotentialStore = Ext.create('CargoPotentialStore', {
//				data : DataDictionary.CARGO_POTENTIAL
//			});
		}
		return this.cargoPotentialStore;
	},
	// 客户潜力类型
	getCustPotentialStore : function() {
		if (this.custPotentialStore == null) {
			this.custPotentialStore = getDataDictionaryByName(DataDictionary,'CUST_POTENTIAL');
//			this.custPotentialStore = Ext.create('CustPotentialStore', {
//				data : DataDictionary.CUST_POTENTIAL
//			});
		}
		return this.custPotentialStore;
	},
	// 上一年公司规模
	getFirmSizeStore : function() {
		if (this.firmSizeStore == null) {
			this.firmSizeStore = getDataDictionaryByName(DataDictionary,'FIRM_SIZE');
//			this.firmSizeStore = Ext.create('FirmSizeStore', {
//				data : DataDictionary.FIRM_SIZE
//			});
		}
		return this.firmSizeStore;
	},
	// 信用等级
	getCreditGradeStore : function() {
		if (this.creditGradeStore == null) {
			this.creditGradeStore = getDataDictionaryByName(DataDictionary,'CREDIT_GRADE');
//			this.creditGradeStore = Ext.create('CreditGradeStore', {
//				data : DataDictionary.CREDIT_GRADE
//			});
		}
		return this.creditGradeStore;
	},
	// 地址类型
	getAddressTypeStore : function() {
		if (this.addressTypeStore == null) {
			this.addressTypeStore = getDataDictionaryByName(DataDictionary,'ADDRESS_TYPE');
//			this.addressTypeStore = Ext.create('AddressTypeStore', {
//				data : DataDictionary.ADDRESS_TYPE
//			});
		}
		return this.addressTypeStore;
	},
	// 物流决定权
	getLogistDeciStore : function() {
		if (this.logistDeciStore == null) {
			this.logistDeciStore = getDataDictionaryByName(DataDictionary,'LOGIST_DECI');
//			this.logistDeciStore = Ext.create('LogistDeciStore', {
//				data : DataDictionary.LOGIST_DECI
//			});
		}
		return this.logistDeciStore;
	},
	// 付款方式
	getPayWayStore : function() {
		if (this.payWayStore == null) {
			this.payWayStore = getDataDictionaryByName(DataDictionary,'PAY_WAY');
//			this.payWayStore = Ext.create('PayWayStore', {
//				data : DataDictionary.PAY_WAY
//			});
		}
		return this.payWayStore;
	},
	// 来源渠道 偏好渠道
	getPreferenceChannelStore : function() {
		if (this.preferenceChannelStore == null) {
			this.preferenceChannelStore = getDataDictionaryByName(DataDictionary,'PREFERENCE_CHANNEL');
//			this.preferenceChannelStore = Ext.create('PreferenceChannelStore',{
//						data : DataDictionary.PREFERENCE_CHANNEL
//					});
		}
		return this.preferenceChannelStore;
	},
	// 偏好服务
	getPreferenceServiceStore : function() {
		if (this.preferenceServiceStore == null) {
			this.preferenceServiceStore = getDataDictionaryByName(DataDictionary,'PREFERENCE_SERVICE');
//			this.preferenceServiceStore = Ext.create('PreferenceServiceStore',{
//						data : DataDictionary.PREFERENCE_SERVICE
//					});
		}
		return this.preferenceServiceStore;
	},
	// 发票要求
	getBillRequireStore : function() {
		if (this.billRequireStore == null) {
			this.billRequireStore = getDataDictionaryByName(DataDictionary,'BILL_REQUIRE');
//			this.billRequireStore = Ext.create('BillRequireStore', {
//				data : DataDictionary.BILL_REQUIRE
//			});
		}
		return this.billRequireStore;
	},
	// 账户性质
	getAccountNatureStore : function() {
		if (this.accountNatureStore == null) {
			this.accountNatureStore = getDataDictionaryByName(DataDictionary,'ACCOUNT_NATURE');
//			this.accountNatureStore = Ext.create('AccountNatureStore', {
//				data : DataDictionary.ACCOUNT_NATURE
//			});
		}
		return this.accountNatureStore;
	},
	// 账户用途
	getAccountUseStore : function() {
		if (this.accountUseStore == null) {
			this.accountUseStore = getDataDictionaryByName(DataDictionary,'ACCOUNT_USE');
//			this.accountUseStore = Ext.create('AccountUseStore', {
//				data : DataDictionary.ACCOUNT_USE
//			});
		}
		return this.accountUseStore;
	},
	// 性别
	getGenderStore : function() {
		if (this.genderStore == null) {
			this.genderStore = getDataDictionaryByName(DataDictionary,'GENDER');
//			this.genderStore = Ext.create('GenderStore', {
//				data : DataDictionary.GENDER
//			});
		}
		return this.genderStore;
	},
	//初始化自定义数据字典--联系人类型
	initSelfDefineLinkmanType:function(dictionary){
		if(dictionary != null){
			dictionary.LINKMANTYPE = this.linkmanTypeData;
		}
	},
	//联系方式store
	getLinkWayStore: function() {
		if(this.linkWayStore == null){
			this.linkWayStore = Ext.create('Ext.data.Store',{
				fields:['linkValue','linkDesc'],
				data : [{linkValue:'mobile',linkDesc:'手机号码'},
				        {linkValue:'tel',linkDesc:'固定电话'}]
			});
		}
		return this.linkWayStore;
	},
	//当前用户权限可切换部门 
	getCurrentUserDeptListStore:function(){
		if(this.currentUserDeptListStore == null){
			this.currentUserDeptListStore = Ext.create('CurrentUserDeptListStore',{
				data:currentUserDeptList
			});
		}
		return this.currentUserDeptListStore;
	},
	//获取运单信息
	getWayBillInfoStore:function(){
		if(this.wayBillInfoStore == null){
			this.wayBillInfoStore = Ext.create('WayBillInfoStore');
		}
		return this.wayBillInfoStore;
	},
	//获取数据字典
	getDictionary:function(params,successFn,failFn){
		var dictionaryUrl = '../common/queryAllDataDictionaryByKeys.action';
		DpUtil.requestJsonAjax(dictionaryUrl,params,successFn,failFn);
	},
	//获取 部门信息
	getDeptList:function(params,successFn,failFn){
		var deptListUrl = '../common/acquireCurrentUserDeptList.action';
		DpUtil.requestJsonAjax(deptListUrl,params,successFn,failFn);
	},
	// 目前级别(会员升级列表)
	getNowGradUpStore : function() {
		if (this.nowGradUpStore == null) {
			this.nowGradUpStore = getDataDictionaryByName(DataDictionary,'NOW_GRAD_UP');
//			this.nowGradUpStore = Ext.create('NowGradUpStore', {
//				data : DataDictionary.NOW_GRAD_UP
//			});
		}
		return this.nowGradUpStore;
	},
	// 目标级别(会员升级列表)
	getTargetGradUpStore : function() {
		if (this.targetGradUpStore == null) {
			this.targetGradUpStore = getDataDictionaryByName(DataDictionary,'TARGET_GRAD_UP');
//			this.targetGradUpStore = Ext.create('TargetGradUpStore', {
//				data : DataDictionary.TARGET_GRAD_UP
//			});
		}
		return this.targetGradUpStore;
	},
	// 目标级别(会员降级列表)
	getTargetGradDownStore : function() {
		if (this.targetGradDownStore == null) {
			this.targetGradDownStore = getDataDictionaryByName(DataDictionary,'TARGET_GRAD_DOWN');
//			this.targetGradDownStore = Ext.create('TargetGradDownStore', {
//				data : DataDictionary.TARGET_GRAD_DOWN
//			});
		}
		return this.targetGradDownStore;
	},
	//是否接受营销信息
	getMarketInfoStore : function() {
		if (this.marketInfoStore == null) {
			this.marketInfoStore = getDataDictionaryByName(DataDictionary,'MARKETINFO');
		}
		return this.marketInfoStore;
	},
	//证件类型 税务登记号
	getCardTypeStore : function() {
		if (this.cardTypeCustStore == null) {
			this.cardTypeCustStore = getDataDictionaryByName(DataDictionary,'CARDTYPECUST');
		}
		return this.cardTypeCustStore;
	},
	//证件类型 个人联系人（大区总以上权限）
	getCardTypeConStore : function() {
		if (this.cardTypeConStore == null) {
			this.cardTypeConStore = getDataDictionaryByName(DataDictionary,'CARDTYPECON');
		}
		return this.cardTypeConStore;
	},
	//证件类型 个人联系人
	getCardTypeConNotViewStore : function() {
		if (this.cardTypeConNotViewStore == null) {
			this.cardTypeConNotViewStore = getDataDictionaryByName(DataDictionary,'CARDTYPECON_NOTVIEW');
		}
		return this.cardTypeConNotViewStore;
	},
	//证件类型 个人联系人
	cardTypeConAUStore:null,
	//证件类型 个人联系人
	getCardTypeConAUStore : function() {
		if (this.cardTypeConAUStore == null) {
			this.cardTypeConAUStore = getDataDictionaryByName(DataDictionary,'CARDTYPECON_AU');
		}
		return this.cardTypeConAUStore;
	},
	// 集中结算部门
	getFocusDeptStore : function() {
		if (this.focusDeptStore == null) {
			this.focusDeptStore = Ext.create('FocusDeptStore');
		}
		return this.focusDeptStore;
	},
	//创建特殊会员所属部门
	getSpecialDeptStore : function() {
		if (this.specialDeptStore == null) {
			this.specialDeptStore = Ext.create('SpecialDeptStore');
		}
		return this.specialDeptStore;
	},
	//获取 部门信息
	getDeptByName:function(params,successFn,failFn){
	  	DpUtil.requestJsonAjax('../organization/acquireDeptByName.action',params,successFn,failFn);
	},
	bankProvinceStore:null,
	//开户省
	getBankProvinceStore:function(){
		if(this.bankProvinceStore == null){
			this.bankProvinceStore = Ext.create('BankProvinceStore');
		}
		return this.bankProvinceStore;
	},
	bankCityStore:null,
	//开户市区
	getBankCityStore:function(){
		if(this.bankCityStore == null){
			this.bankCityStore = Ext.create('BankCityStore');
		}
		return this.bankCityStore;
	},
	bankNameStore:null,
	//开户行名称
	getBankNameStore:function(params,successFn,failFn){
		if(this.bankNameStore == null){
			this.bankNameStore = Ext.create('BankNameStore');
		}
		return this.bankNameStore;
	},
	contactNumStore:null,
	//联系人编码store
	getContactNumStore : function() {
		if (this.contactNumStore == null) {
			this.contactNumStore = Ext.create('Ext.data.Store',{
				fields:['number','number'],
				data : []
			});
		}
		return this.contactNumStore;
	}
});
