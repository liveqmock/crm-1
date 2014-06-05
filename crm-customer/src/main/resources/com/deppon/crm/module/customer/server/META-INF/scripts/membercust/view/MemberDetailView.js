var memberManagerDataControl =  (CONFIG.get("TEST"))? new MemberManagerDataTest():new MemberManagerData();
var memberInfo=null;
Ext.onReady(function(){
	var params =[
//目标级别i18n('i18n.ChangeContactAffiliatedRelationView.nowLevelCustLevel'),会员等级
	'MEMBER_GRADE',
	// 客户行业
	'TRADE',
	// 客户属性
	'CUSTOMER_NATURE',
	// 公司性质
	'COMP_NATURE',
	// 客户类型
	'CUSTOMER_TYPE',
	// 客户潜力类型
	'COOPERATION_INTENTION',
//	'CUST_POTENTIAL',
	// 上一年公司规模
	'FIRM_SIZE',
	//客户来源
	'CUST_SOURCE',
	// 信用等级
	'CREDIT_GRADE',
	// 地址类型
	'ADDRESS_TYPE',
	//潜力类型潜力
	'CARGO_POTENTIAL',
	// 联系人类型 已取消
	// 物流决定权
	'LOGIST_DECI',
	// 付款方式
	'PAY_WAY',
	// 来源渠道',偏好渠道
	'PREFERENCE_CHANNEL',
	// 偏好服务
	'PREFERENCE_SERVICE',
	// 发票要求
	'BILL_REQUIRE',
	//账户性质
	'ACCOUNT_NATURE',
	//账户用途
	'ACCOUNT_USE',
	//性别
	'GENDER',
	//是否接受营销信息
	'MARKETINFO',
	//联系人证件类型
	'CARDTYPECON',
	//客户类别
	'CUST_CATEGORY',
	//税务登记号证件类型
	'CARDTYPECUST',
	//证件类型新增和修改(大区总以上权限)
	'CARDTYPECON_NOTVIEW',
	//证件类型新增和修改
	'CARDTYPECON_AU',
	//客户性质（潜散客固客
	'CUST_TYPE',
	'OPERATE_TYPE'
      			];
    initDataDictionary(params);
  //初始化提示
    Ext.QuickTips.init();
    Ext.tip.QuickTipManager.init();
	//初始化自定义的数据字典--联系人类型
	memberManagerDataControl.initSelfDefineLinkmanType(DataDictionary);
	//显示会员修改 内容描述
	new ModifyMemberControl().getModifyMember();
	var custId=window.location.search.split("=")[1];

	var searchParams = {'searchCustCondition':{'memberId':custId}
	};
	var searchByIdSuccess = function(response){
		memberInfo = response.member;
		Ext.create('Ext.container.Viewport',{
			layout:'fit',
//				autoScroll:true,
			items:[Ext.create('MemberCustEditPanel',
				{'member':memberInfo,
					  'scatterUpgradeRecord':null,
					  'viewStatus':'view',
					  'custStatus':null,
					  'channelType':null,
					  'updateMemberParent':null,
					  'parentWindow':null}	
				
				
			)]
		});
	};
	var searchByIdFail = function(response){
		MessageUtil.showErrorMes( response.message);
	};
	memberManagerDataControl.acquireMemberById(searchParams,searchByIdSuccess,searchByIdFail);
});


