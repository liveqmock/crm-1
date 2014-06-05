/**
*会员共用Data层
*/
//判断是否导入测试数据
(function() {
	var contractBasicDataTest = "../scripts/membercust/test/ContractBasicDataTest.js";
    if(CONFIG.get('TEST'))
	{
		document.write('<script type="text/javascript" src="' + contractBasicDataTest + '"></script>');
	}
})();

//获得当前用户
var CurrentUser = {};
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
//工作流model
Ext.define('WorkflowModel',{
	extend:'Ext.data.Model',
	fields:[
		//部门名称
		{name:'deptName',  type: 'string'},
		//工作流号
		{name:'workflowId',  type: 'string'},
		//工作流类型
		{name:'operatorType',  type: 'string'},
		//OA审批状态  1审批中  2已同意  3未同意
		{name:'approvalState',  type: 'string'},
	    //创建时间
		{name:'createDate',  type: 'date',convert: DpUtil.changeLongToDate,defaultValue:new Date()},
		//最后审批时间
		{name:'modifyDate',  type: 'date',convert: DpUtil.changeLongToDate,defaultValue:new Date()},
		//最后审批人
		{name:'approvalMan',  type: 'string'},
		//合同部门ID
		{name:'contractDeptId',  type: 'string'}]
});
//会员+合同 model
Ext.define('ContractViewModel',{
	extend:'Ext.data.Model',
	fields:[
	        //客户ID
	        {name : 'custId',type: 'string'},
	        //客户名称',
			{name : 'custName',type: 'string'},
			//客户编码',
			{name : 'custNum',type: 'string'},
			//行业',
			{name : 'trade',type: 'string'},
			//二级行业',
			{name : 'secondTrade',type: 'string'},
			//客户等级',
			{name : 'custGrade',type: 'string'},
			//联系人姓名',
			{name : 'contactName',type: 'string'},
			//联系人手机',
			{name : 'mobileNum',type: 'string'},
			//联系人电话',
			{name : 'telNum',type: 'string'},
			//客户类型',
			{name : 'custType',type: 'string'},
			//税务登记号',
			{name : 'taxregNum',type: 'string'},
			//身份证号',
			{name : 'idCard',type: 'string'},
			//城市Id
			{name : 'cityId',type: 'number'},
			//城市name
			{name : 'city',type: 'string'},
			//'地址',
			{name : 'address',type: 'string'},
			//合同
			{name : 'contract'}]
});
//合同信息 model
Ext.define('ContractModel',{
	extend:'Ext.data.Model',
	fields:[
	      //合同id
	      {name:'id',  type: 'string'},
	      //合同类型
	      {name:'contractType',type:'string'},
	      //零担合同付款方式
	      {name:'payWay',  type: 'string'},
	      //快递合同付款方式
	      {name:'exPayWay',  type: 'string'},
	      //申请欠款额度
	      {name:'arrearaMount',  type: 'number'},
	      //联系人姓名
	      {name:'linkManName',  type: 'string'},
	      // 联系人固话
	      {name:'linkManPhone',  type: 'string'},
	      // 联系人手机
	      {name:'linkManMobile',  type: 'string'},
	      // 联系人详细地址
	      {name:'linkManAddress',  type: 'string'},
	      // 对账日期
	      {name:'reconDate',  type: 'string'},
	      // 开发票日期
	      {name:'invoicDate',  type: 'string'},
	      // 结款日期
	      {name:'resultDate',  type: 'string'},
	      // 合同起始日期
	      {name:'contractBeginDate',  type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null},
	      // 合同到期日期
	      {name:'contractendDate',  type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null},
	      // 申请事由
	      {name:'application',  type: 'string'},
	      // 客户ID
	      {name:'custId',  type: 'string'},
	      // 部门ID
	      {name:'deptId',  type: 'string'},
	      // 部门name
	      {name:'deptName',  type: 'string'},
	      // 是否折扣
	      {name:'iddisCount',  type: 'boolean'},
	      // 合同状态
	      {name:'contractStatus',  type: 'string'},
	      // 合同主体
	      {name:'contractSubject',  type: 'string'},
	      // 注册资金
	      {name:'regicapital',  type: 'string'},
	      //原合同编号
	      {name:'beforeContractNum',  type: 'string'},
	      //合同编号
	      {name:'contractNum',  type: 'string'},
	      //走货名称
	      {name:'goodsName',  type: 'string'},
	      //客户全称
	      {name:'custCompany',  type: 'string'},
	      //零担近三个月发货金额
	      {name:'exArrAmount',  type: 'string'},
	      //快递近三个月发货金额
	      {name:'arrAmount',  type: 'string'},
	      //联系人ID
	      {name:'contactId',  type: 'string'},
	      //零担合同优惠类型
	      {name:'preferentialType', type:'String'},
	      //快递合同优惠类型
	      {name:'exPreferentialType', type:'String'},
		  // 合同状态  1已生效 2已作废
		  {name:'contractStatus', type:'String'},
		  {name:'contractWorkflowList',defaultValue:null},
		  {name:'contractDepartList',defaultValue:null},
	      {name:'createUser',type:'string'}, 
	      {name:'createDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:null}, 
	      {name:'modifyDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:null},
	      {name:'modifyUser',type:'string'},
	      //合同月结天数
	      {name:'debtDays',type:'number'},
	      //可使用额度
	      {name:'useableArrearAmount',type:'number'},
	      //是否异地调货
	      {name:'ifForeignGoods',type:'boolean'},
	      //催款部门(标杆编码)
	      {name:'dunningDeptCode',type:'string'},
	      //催款部门
	      {name:'dunningDeptName',type:'string'},
	      //签署合同公司
	      {name:'signCompany',type:'string'},
	      //税务标记
	      {name:'invoiceType',type:'string'},
	      //价格折扣版本
	      {name:'priceVersionDate',  type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null},
	      //快递折扣版本
	      {name:'exPriceVersionDate',  type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null}
	      ]
});


//合同信息 model
Ext.define('NullContractModel',{
	extend:'Ext.data.Model',
	fields:[
	      //零担合同付款方式
	      {name:'payWay',  type: 'string'},
	      //零担合同优惠类型
	      {name:'preferentialType', type:'String'}
	      ]
});
//快递合同信息 model
Ext.define('NullExContractModel',{
	extend:'Ext.data.Model',
	fields:[
	        //零担合同付款方式
	        {name:'exPayWay',  type: 'string'},
	        //零担合同优惠类型
	        {name:'exPreferentialType', type:'String'}
	        ]
});

 //合同月结天数Model
 
Ext.define('ContractMonthEndModel',{
	extend:'Ext.data.Model',
	fields:[{name:'defaultDebtDays',type:'number'},
	        {name:'contractDebtDayName',type:'string'},
	        {name:'modifyUser',type:'string'},
	        {name:'status',type:'string'},
	        {name:'id',type:'string'},
	        {name:'modifyDate',type:'date',convert: DpUtil.changeLongToDate,defaultValue:null}]
});
/**
 * 合同月结天数Store
 */
Ext.define('ContractMonthEndStore',{
	extend:'Ext.data.Store',
	model:'ContractMonthEndModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		api:{
			read:'searchAllCommonContractDeptDays.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'contractMonthendDaysList'
		}
	}
});
/**
 * 合同信息Store
 */
Ext.define('ContractStore',{
	extend:'Ext.data.Store',
	model:'ContractModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		api:{
			read:'searchContractList.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'contractList',
			totalProperty:'totalCount'
		}
	}
});
//合同绑定部门 model
Ext.define('ContractBondDeptModel',{
	extend:'Ext.data.Model',
	fields:[
			//部门ID
			{name:'deptId',  type: 'string'},
			//部门name
			{name:'deptName',  type: 'string'},
			//合同绑定时间
			{name:'boundTime',  type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null},
			//合同解绑时间
			{name:'removeTime',  type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null},
			//部门name，后台属性是 isDept，从json中查看 接收的字段是dept
			{name:'dept',  type: 'boolean'}]
});
//合同对应部门 model
Ext.define('ContractDeptModel',{
	extend:'Ext.data.Model',
	fields:[
			//合同主体ID
			{name:'contractId',  type: 'string'},
			//部门ID
			{name:'deptId',  type: 'string'},
			//部门name
			{name:'deptName',  type: 'string'},
			//合同绑定时间
			{name:'boundTime',  type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null},
			//合同解绑时间
			{name:'removeTime',  type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null},
			//工作流号
			{name:'workflowId',  type: 'string'},
			//OA审批状态
			{name:'approvalState',  type: 'string'},
			//审批人
			{name:'approvalMan',  type: 'string'},
			//工作流类型
			{name:'workflowType',  type: 'string'},
			//状态 1生效 2失效
			{name:'state',  type: 'boolean'},
			//是否归属部门
			{name:'isDept',  type: 'boolean'},
			// 合同版本号
			{name:'versionNumber', type:'number'},
			//创建时间
			{name:'createDate',  type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null},
			//最后审批时间 
			{name:'modifyDate',  type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null}
			]
});
/**
 * 合同 工作流Store
 */
Ext.define('WorkflowStore',{
	extend:'Ext.data.Store',
	model:'WorkflowModel',
	proxy:{
		type:'memory'
	}
});
/**
 * 合同绑定部门Store
 */
Ext.define('ContractDeptStore',{
	extend:'Ext.data.Store',
	model:'ContractBondDeptModel',
	proxy:{
		type:'memory'
	}
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
			// 文件类型
			{name:'fileBusinessType',type: 'string'},
			// 保存路径
			{name:'savePath',type: 'string'},
			// 文件大小
			{name:'fileSize',type: 'number'}]
});
//合同附件信息 model
Ext.define('ContractNounModel',{
	extend:'Ext.data.Model',
	fields:[
			{name:'id',  type: 'string'},
			// 合同名称
			{name:'contractName',  type: 'string'},
			// 保存路径
			{name:'savePath',  type: 'string'},
			// 文件名称
			{name:'fileName',  type: 'string'},
			// 合同ID
			{name:'contractId',  type: 'string'},
			//合同部门关系ID
			{name:'contractDeptId',  type: 'string'}]
});
/**
 * 合同附件信息 Store
 */
Ext.define('FileInfoStore',{
	extend:'Ext.data.Store',
	model:'FileInfoModel',
	proxy:{
		type:'memory'
	}
});
//优惠信息 model
Ext.define('PreferentialModel',{
	extend:'Ext.data.Model',
	fields:[
			//ID
			{name:'id',  type: 'string'},
			//对应合同ID
			{name:'contractId',  type: 'string'},
			//最后更新时间
			{name:'modifyDate',  type: 'date',convert: DpUtil.changeLongToDate},
			//运费折扣费率
			{name:'chargeRebate',  type: 'number',defaultValue:1},
			//代收货款费率
			{name:'agentgathRate',  type: 'number',defaultValue:1},
			//保价费率
			{name:'insuredPriceRate',  type: 'number',defaultValue:1},
			//接货费率
			{name:'receivePriceRate'},
			//送货费率
			{name:'deliveryPriceRate',  type: 'number',defaultValue:1}]
});
/**
 * 优惠信息 Store
 */
Ext.define('PreferentialStore',{
	extend:'Ext.data.Store',
	model:'PreferentialModel',
	proxy:{
		type:'memory'
	}
});

//所属子公司的model
Ext.define('ContractSubjectModel',{
	extend:'Ext.data.Model',
	fields:[
			//ID
			{name:'id',  type: 'string'},
			//对应子公司名字
			{name:'contractSubject',  type: 'string'}]
});
/**
 * 所属子公司的store
 */
Ext.define('ContractSubjectStore',{
	extend:'Ext.data.Store',
	model:'ContractSubjectModel',
//	autoLoad:true,
	proxy:{
		type:'ajax',
		api:{
			read:'searchContractSubjectList.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'contractSubjectList'
		}
	}
});

/**
 * 是否Model
 */
Ext.define('BooleanModel',{
	extend:'Ext.data.Model',
	fields:['name',{name:'value',type:'boolean'}]
});

/**
 * 是否控件的store
 */
Ext.define('BooleanStore',{
	extend:'Ext.data.Store',
	model:'BooleanModel',
	data:[{'name':'是','value':true},
	      {'name':'否','value':false}]
});

/**
 * 协议联系人的store
 */
Ext.define('ProtoContactStore',{
	extend:'Ext.data.Store',
	model:'SearchMemberResultModel',
	proxy:{
		type:'memory'
	}
});

/**
* 当前登陆用户部门 model
*/
Ext.define('CurrentUserDeptModel',{
	extend:'Ext.data.Model',
	fields:['deptId','deptName']
});

Ext.define('CurrentUserDeptStore', {
	extend : 'Ext.data.Store',
	model : 'CurrentUserDeptModel'
});
//对账日期，开发票日期，结款日期 data
//0时为空，用于和后端映射
var ContractDate = [];
for (var i = 0; i < 31; i++) {
	var contractDateCell ={};
	if(i != 0){
		contractDateCell={dateValue:i,dateDesc:i};
	}else{
		contractDateCell={dateValue:0,dateDesc:' '};
	}
	ContractDate.push(contractDateCell);
}
/**
* 对账日期，开发票日期，结款日期 store
*/
Ext.define('ContractDateStore',{
	extend:'Ext.data.Store',
	fields:['dateValue','dateDesc'],
	proxy:{
		type:'memory'
	}
});
Ext.define('ContractBasicData', {
	//结款方式
	reckonWayStore : null,
	//发票标记
	invoiceTypeStore:null,
	//优惠类型
	privilegeTypeStore : null,
	//快递优惠类型
	exPrivilegeTypeStore:null,
	//客户类型
	customerTypeStore:null,
	//客户等级
	memberGradeStore:null,
	// 客户行业
	tradeStore:null,
	//合同
	contractStore : null,
	//合同月结天数
	contractMonthEndStore : null,
	//合同部门
	contractDeptStore : null,
	//合同附件
	fileInfoStore : null,
	//合同优惠
	preferentialStore : null,
	//工作流store
	workflowStore:null,
	booleanStore:null,
	//合同状态store
	contractStateStore:null,
	//协议联系人store
	protoContactStore:null,
	//对账日期，开发票日期，结款日期 data
	contractDateStore:null,
	//是否控件
	getBooleanStore:function(){
		if(this.booleanStore == null){
			this.booleanStore = Ext.create('BooleanStore');
		}
		return this.booleanStore;
	},
	//合同状态store
	getContractStateStore:function(){
		if(this.contractStateStore == null){
			this.contractStateStore = getDataDictionaryByName(DataDictionary,'CONTACT_STATUS'); 
		}
		return this.contractStateStore;
	},
	//所属子公司
	getContractSubjectStore:function(){
		if(this.ContractSubjectStore == null){
			this.ContractSubjectStore = Ext.create('ContractSubjectStore');
		}
		return this.ContractSubjectStore;
	}
	,
	//结款方式
	getReckonWayStore : function() {
		if (this.reckonWayStore == null) {
			this.reckonWayStore = getDataDictionaryByName(DataDictionary,'RECKON_WAY'); 
		}
		return this.reckonWayStore;
	},
	//发票标记
	getInvoiceTypeStore : function() {
		if (this.invoiceTypeStore == null) {
			this.invoiceTypeStore = getDataDictionaryByName(DataDictionary,'INVOICE_MARK'); 
		}
		return this.invoiceTypeStore;
	},
	//优惠类型
	getPrivilegeTypeStore : function() {
		if (this.privilegeTypeStore == null) {
			this.privilegeTypeStore = getDataDictionaryByName(DataDictionary,'PRIVILEGE_TYPE'); 
		}
		return this.privilegeTypeStore;
	},
	//快递优惠类型
	getExPrivilegeTypeStore : function() {
		if (this.exPrivilegeTypeStore == null) {
			this.exPrivilegeTypeStore = getDataDictionaryByName(DataDictionary,'EXPRIVILEGE_TYPE'); 
		}
		return this.exPrivilegeTypeStore;
	},
	//客户类型
	getCustomerTypeStore : function() {
		if (this.customerTypeStore == null) {
			this.customerTypeStore = getDataDictionaryByName(DataDictionary,'CUSTOMER_TYPE'); 
		}
		return this.customerTypeStore;
	},
	//客户等级
	getMemberGradeStore : function() {
		if (this.memberGradeStore == null) {
			this.memberGradeStore = getDataDictionaryByName(DataDictionary,'MEMBER_GRADE'); 
		}
		return this.memberGradeStore;
	},
	//协议联系人store
	getProtoContactStore:function(){
		if (this.protoContactStore == null) {
			this.protoContactStore = Ext.create('ProtoContactStore');
		}
		return this.protoContactStore;
	},
	// 客户行业
	getTradeStore : function() {
		if (this.tradeStore == null) {
			this.tradeStore = getDataDictionaryByName(DataDictionary,'TRADE'); 
		}
		return this.tradeStore;
	},
	//合同优惠store
	getPreferentialStore:function(){
		if (this.preferentialStore == null) {
			this.preferentialStore = Ext.create('PreferentialStore'); 
		}
		return this.preferentialStore;
	},
	//合同附件store 
	getFileInfoStore:function(){
		if (this.fileInfoStore == null) {
			this.fileInfoStore = Ext.create('FileInfoStore'); 
		}
		return this.fileInfoStore;
	},
	//合同对应部门store 
	getContractDeptStore:function(){
		if (this.contractDeptStore == null) {
			this.contractDeptStore = Ext.create('ContractDeptStore'); 
		}
		return this.contractDeptStore;
	},
	//合同工作流store 
	getWorkflowStore:function(){
		if (this.workflowStore == null) {
			this.workflowStore = Ext.create('WorkflowStore'); 
		}
		return this.workflowStore;
	},
	//保存合同信息
	saveContract:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('saveContract.action',params,successFn,failFn);
	},
	//改签合同信息
	updateContract:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('updateContract.action',params,successFn,failFn);
	},
	//修改合同月结天数（特殊）
	updateSpecialContractMonthEnd:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('updateSpecialContractMonthEnd.action',params,successFn,failFn);
	},
	//修改合同月结天数（通用）
	updateCommonContractMonthEnd:function(params,successFn,failFn){
		DpUtil.requestAjax('updateCommonContractMonthEnd.action',params,successFn,failFn);
	},
	//初始化合同月结天数（通用）
	initMonthEndDay:function(params,successFn,failFn){
		DpUtil.requestAjax('initMonthEndDay.action',params,successFn,failFn);
	},
	//修改合同信息
	alterContractInfo:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('alterContractInfo.action',params,successFn,failFn);
	},
	/**
	 * @description:修改合同运费折扣信息
	 * @date 2013-3-13
	 * @author 潘光均
	 */
	modifyMonthSendRate:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('modifyMonthSendRate.action',params,successFn,failFn);
	},
	//催款部门设置
	modifyDuningDept:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('modifyDuningDept.action',params,successFn,failFn);
	},
	//催款部门设置
	modifyPriceVersionDate:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('modifyPriceVersionDate.action',params,successFn,failFn);
	},
	//新签通过会员获得合同
	acquireNewContract:function(params,successFn,failFn){
		DpUtil.requestJsonAjax('acquireNewContract.action',params,successFn,failFn);
	},
	//对账日期，开发票日期，结款日期 data
	getContractDateStore:function(){
		if (this.contractDateStore == null) {
			this.contractDateStore = Ext.create('ContractDateStore',{
				data:ContractDate
			}); 
		}
		return this.contractDateStore;
	},
	//文件上传
	uploadFile:function(form,successFn,failFn){
		DpUtil.requestFileUploadAjax('../common/upload1.action',form,successFn,failFn);
	},
	//上传文件转换成 合同附件 
	changeFileInfo2ContractNoun :function(fileInfo,contractNoun){
		if(fileInfo != null && contractNoun != null){
			contractNoun.contractId = fileInfo.sourceId;
			contractNoun.savePath = fileInfo.savePath;
			contractNoun.fileName = fileInfo.fileName;
		}
		return contractNoun;
	},
	//合同附件转化成上传文件 
	changeContractNoun2FileInfo :function(fileInfo,contractNoun){
		if(fileInfo != null && contractNoun != null){
			fileInfo.sourceId = contractNoun.contractId;
			fileInfo.savePath = contractNoun.savePath;
			fileInfo.fileName = contractNoun.fileName;
			fileInfo.sourceType = 'CONTRACT';
		}
		return fileInfo;
	},
	//获得当前登录用户
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
	},
	//判断是否超过近三个月的发货金额最高月的二倍
	isNotOverMax3Amount:function(params,successFn,failFn){
		DpUtil.requestJsonAjaxSync('isNotOverMax3Amount.action',params,successFn,failFn);
	}
});
Ext.define('ContractMonthEndData',{
	contractMonthEndStore:null,
	//合同月结天数列表查询结果store
	getContractMonthEndStore:function(){
		return this.contractMonthEndStore;
	},
	initContractMonthEndStore:function(beforeLoadFn) {
		if(this.contractMonthEndStore == null){
			if(beforeLoadFn != null){
				this.contractMonthEndStore = Ext.create('ContractMonthEndStore',{
					listeners:{
						beforeload:beforeLoadFn
					}
				});
			}else{
				this.contractMonthEndStore = Ext.create('ContractMonthEndStore');
			}
		}
		return this.contractMonthEndStore;
	}
});

//催款部门
Ext.define('DunningDeptListModel',{
	extend:'Ext.data.Model',
	fields:['deptCode','deptName']
});

//税务信息
Ext.define('SignCompanyModel',{
	extend:'Ext.data.Model',
	fields:['signCompany']
});
Ext.define('DunningDeptListStore',{
	extend:'Ext.data.Store',
	autoLoad :true,
	model : 'DunningDeptListModel',
    proxy:{
		type:'ajax',
		api:{
			read:'../customer/searchAllDepartments.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'currentUserDeptList',
			totalProperty:'total'
		}
	}
});
Ext.define('SignCompanyListStore',{
	extend:'Ext.data.Store',
	autoLoad :true,
	model : 'SignCompanyModel',
    proxy:{
		type:'ajax',
		api:{
			read:'../customer/searchAllSignCompany.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'signCompanyList',
			totalProperty:'total'
		}
	}
});

