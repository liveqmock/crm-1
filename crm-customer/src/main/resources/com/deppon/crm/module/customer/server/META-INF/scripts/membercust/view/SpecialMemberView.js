/**
* 创建特殊会员界面
*/


Ext.onReady(function(){
	var params = [  // 目标级别i18n('i18n.ChangeContactAffiliatedRelationView.nowLevelCustLevel'),会员等级
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
          			'CUST_POTENTIAL',
          			'CARGO_POTENTIAL',
          			//客户来源
          			'CUST_SOURCE',
          			// 上一年公司规模
          			'FIRM_SIZE',
          			// 信用等级
          			'CREDIT_GRADE',
          			// 地址类型
          			'ADDRESS_TYPE',
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
          			//客户类别
          			'CUST_CATEGORY',
          			//性别
          			'GENDER',
	      			//是否接受营销信息
	      			'MARKETINFO',
	      			//联系人证件类型
	      			'CARDTYPECON',
	      			//税务登记号证件类型
	      			'CARDTYPECUST',
	      			//证件类型新增和修改(大区总以上权限)
	      			'CARDTYPECON_NOTVIEW',
	      			//证件类型新增和修改
	      			'CARDTYPECON_AU',
	      			//客户性质（潜散客固客
      				'CUST_TYPE'
      			];
      			
  	initDataDictionary(params);//数据字典
  //初始化提示
    Ext.QuickTips.init();
    Ext.tip.QuickTipManager.init();
	//初始化自定义的数据字典--联系人类型
	memberCustControl.initSelfDefineLinkmanType(DataDictionary);
	//显示会员修改 内容描述
	new ModifyMemberControl().getModifyMember();
	Ext.create('Ext.container.Viewport',{
//			layout:'fit',
		autoScroll:true,
		items:[Ext.create('MemberCustEditPanel',{
			'viewStatus':'new','channelType':'special',
			'width':805,'height':620
			})]
	});
});


