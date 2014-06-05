/**
* 会员编辑界面
* 主窗体								MemberCustEditWindow:L113,
* 主界面panel						MemberCustEditPanel:L144
* 会员基础信息panel					MemberCustBasicInfoPanel：L761
* 联系人信息tap						MemberLinkmanTabPanel:L1682
* 联系人编辑界面win					MemberLinkmanEditWindow
* 联系人基本信息panel				MemberLinkmanForm
* 联系人偏好panel					MemberLinkmanHobby
* 联系人接送货地址偏好编辑界面win		MemberAddressHobbyEditWin
* 帐号信息tap						MemberAccountTabPanel:L1966
* 账号编辑界面win					MemberAccountEditWin
* 支行查询界面win					BankSearchUI
* 接送货地址tap						MemberAddressTabPanel:L2169
* 接送货地址编辑界面win				MemberAddressEditwin
* 修改日志tap						memberCustTabPanel:L2380
* 修改详情win						ChangeRecordWin
*/
var memberCustControl = (CONFIG.get('TEST'))? Ext.create('MemberCustDataTest'):Ext.create('MemberCustData');
(function() {//汉字索引
	var getFirstCharUtiljs = "../scripts/common/common/GetFirstCharUtil.js";
	document.write('<script type="text/javascript" src="' + getFirstCharUtiljs + '"></script>');
})();
MemberCustEditView = function(){};
//因为创建特殊会员，选择部门的id，设一个变量
var isHK = '0';
//全局变量，[{0}税务登记号,{1}身份证号,{2}手机号码,{3}账号联系人是否被修改,{4}地址偏好地址是否被修改,
//			{5}客户类型为个人时主联系人身份证号不可为空,{6}联系人固话和联系人名称校验结果, 
//			{7}联系人固话和联系人名称校验结果数据,{8}手机号码校验结果,{9}证件号码合法性,{10}证件号码合法性结果集] 
//实时校验结果 "success"  请求后台结果,message请求后台后返回消息
//message[1],message[2] 格式 {contactId:'',success:true,message:''}
var RealTimeVerifyResult = {success:[true,true,true,true,true,true,true,true,true,true,true],
							message:['',[],[],'','','','',[],'','',[]]};

//创建会员信息编辑界面
MemberCustEditView.showMemberWin = function(params){
	
	var dictionaryParams = [	// 目标级别i18n('i18n.ChangeContactAffiliatedRelationView.nowLevelCustLevel'),会员等级
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
	      			'CARDTYPECON_AU'
	      			];
	initDataDictionary(dictionaryParams);//数据字典
	//初始化自定义的数据字典--联系人类型
	memberCustControl.initSelfDefineLinkmanType(DataDictionary);
	//显示会员编辑界面
	Ext.create('MemberCustEditWindow',params).show();
};
Ext.define('MemberCustEditWindow',{
	extend:'PopWindow',
	title:i18n('i18n.MemberCustEditView.memberInfoEditUI'),
	layout:'fit',
	width:825,
	height:435,
	member:null,
	updateMemberParent:null,//会员管理，点击维护会员信息，传入的父类
    scatterUpgradeRecord:null,
    viewStatus:null,
    channelType:null,
    custStatus:null,									//会员状态：0正常，1审批中，2作废
	items:null,
    initComponent:function(){
    	var me = this;
    	//初始化store
    	memberCustControl.clearStore();
    	me.items = [Ext.create('MemberCustEditPanel',{'member':me.member,
													  'scatterUpgradeRecord':me.scatterUpgradeRecord,
													  'viewStatus':me.viewStatus,
													  'custStatus':me.custStatus,
													  'channelType':me.channelType,
													  'updateMemberParent':me.updateMemberParent,
													  'parentWindow':me})];
    	me.on('beforeclose',me.beforeCloseEvent);
    	this.callParent();
    },
    //窗体关闭前事件
    beforeCloseEvent:function(){
    	var me = this;
    	var isHK = '0';
    	//清空 全局变量，联系人偏好地址,所有联系人地址
    	RealTimeVerifyResult = {success:[true,true,true,true,true,true,true,true,true,true,true],
							message:['',[],[],'','','','',[],'','',[]]}
    	memberCustControl.getPreferenceAddressStore().removeAll();
    	custLabels=[]
    }
});

Ext.define('MemberCustEditPanel',{
	extend:'BasicPanel',
	memberCustBasicInfo:null,      //会员基础信息
	basicInfoTab:null,
	memberLinkmanInfo:null,  	//联系人信息
	memberAccountInfo:null,  	//账号信息
	memberAddressInfo:null,  	//接送货地址信息
	memberCustInfo:null,  		//固定客户信息
	memberCustLabelInfo:null,	//固定客户客户标签
	updateMemberParent:null,	//会员管理，维护会员信息传入的父类
	memberData:memberCustControl,
	width:825,
	height:435,
	layout:{
        type:'vbox',
        align:'stretch'
    },
	items:null,
	fbar:null,
	member:null,           //对应后台Member对象的json字符串，如果正常创建和实时创建是由父页面传入
	channelType:'normal',      //创建会员渠道：'normal'表示从散客升降列表创建会员,'immediate'表示实时创建会员信息,'special'表示创建特殊会员,
	                           // 'workflow'表示从工作流界面而来
	viewStatus:null,       //new表示新增，update表示修改 view表示查看
	custStatus:null,       //2 作废状态
	scatterUpgradeRecord:null,  //散客升级记录
	parentWindow:null,          //父窗口
	initComponent:function(){
		var me = this;
		me.memberCustBasicInfo = Ext.create('MemberCustBasicInfoPanel',{'memberData':me.memberData,
																		'memberBasicRecord':me.getMemberBasicRecord(),
																		'channelType':me.channelType,
																		'viewStatus':me.viewStatus});
		me.memberLinkmanInfo = Ext.create('MemberLinkmanTabPanel',{'memberData':me.memberData,
																	'parent':me,'viewStatus':me.viewStatus,
																   'memberLinkmanData':me.getMemberLinkmanData()});
		me.memberAccountInfo = Ext.create('MemberAccountTabPanel',{'memberData':me.memberData,'viewStatus':me.viewStatus,
																	'channelType':me.channelType,
																   'memberAccountData':me.getMemberAccountData(),
																   'parentWindow':me.parentWindow});
		me.memberAddressInfo = Ext.create('MemberAddressTabPanel',{'memberData':me.memberData,'viewStatus':me.viewStatus,
																   'memberAddressData':me.getMemberAddressData()});
		me.memberCustInfo = Ext.create('memberCustTabPanel',{'memberData':me.memberData,'viewStatus':me.viewStatus,
																	'memberCustInfoData':me.getMemberCustInfoData()});
		me.memberCustLabelInfo = Ext.create('CustLabelInfoForm',{'memberData':me.memberData,'status':me.viewStatus,'id':'meberCustLabel',
																	'CustType':'MEMBER','title':'客户标签','channelType':me.channelType});
		//创建特殊会员与会员新增布局不同
		if(me.channelType != 'special'){
			initBasicData(me.viewStatus,'MEMBER',me.getMemberBasicRecord(),me.channelType,top.User.deptId);
			me.basicInfoTab = {
			    xtype:'tabcontentpanel',
				title:'基本信息<span style="color:red;">*</span>',
			    items:[me.memberCustBasicInfo]
		   }
			me.items = [
			    		{
			    			xtype:'normaltabpanel',
			    			height:300,
			    			items:[me.basicInfoTab,
			    			       me.memberLinkmanInfo,
			    			       me.memberAccountInfo,
			    			       me.memberAddressInfo,
			    			       me.memberCustLabelInfo,
			    			       me.memberCustInfo
			    			       ]
			    		}];
		}else{
			me.items = [ {
				xtype:'titleformpanel',
				height:300,
				items:[{xtype:'fieldset',
						title:i18n('i18n.MemberCustEditView.basicInfo'),
						items:[me.memberCustBasicInfo]}]
			},
			{
				xtype:'normaltabpanel',
				flex:1,
				items:[
				       me.memberLinkmanInfo,
				       me.memberAccountInfo,
				       me.memberAddressInfo,
				       me.memberCustLabelInfo,
				       me.memberCustInfo
				       //,me.memberAccountInfo	
				       ]
			}];
		}
		
		me.fbar = me.getFbar();
		this.callParent();
		//如果查看状态是view，锁定所有控件
		if(me.viewStatus == 'view'){
			me.lockAllComponents();
		}
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.ScatterCustManagerView.close'),
			id:'cancel_id',
			handler:function(){
				if(me.parentWindow != null){
					me.parentWindow.close();
				}
			}
		}];
	},
	//锁定界面所有控件
	lockAllComponents:function(){
		var me = this;
		//锁定会员基本信息
		var memberBasicBasicForm = me.memberCustBasicInfo.getForm();
		memberBasicBasicForm.getFields().each(function(field){
			field.setReadOnly(true);
		});
		//锁定联系人按钮
		var linkButton = me.memberLinkmanInfo.down('button');
		while(linkButton != null){
			linkButton.setDisabled(true);
			linkButton = linkButton.next('button');
		}
		//锁定账号信息按钮
		var accountButton = me.memberAccountInfo.down('button');
		while(accountButton != null){
			accountButton.setDisabled(true);
			accountButton = accountButton.next('button');
		}
		//锁定接送货地址按钮
		var addressButton = me.memberAddressInfo.down('button');
		while(addressButton != null){
			addressButton.setDisabled(true);
			addressButton = addressButton.next('button');
		}
	},
	//会员界面展示之后加载会员数据
	loadMemberDataAfterRender:function(member){
		var me = this;
		me.member = member;
		var memberBasicRecord = me.getMemberBasicRecord();
		var memberLinkmanData = me.getMemberLinkmanData();
		var memberAccountData = me.getMemberAccountData();
		var memberAddressData = me.getMemberAddressData();
		//:加载tab5(固定客户)的数据
		var memberCustInfoData = me.getMemberCustInfoData();
		
		//加载会员基本信息
		me.memberCustBasicInfo.loadMemberBasicData(memberBasicRecord);
		//加载地址信息
		me.memberAddressInfo.loadMemberAddressData(memberAddressData);
		//加载联系人信息
		me.memberLinkmanInfo.loadLinkmanData(memberLinkmanData);
		//加载账号信息
		me.memberAccountInfo.loadMemberAccountData(memberAccountData);
		//:加载tab5(固定客户)的数据
		me.memberCustInfo.loadCustInfoData(memberCustInfoData);
	},
	//获得会员基本信息的record
	getMemberBasicRecord:function(){
		var  me = this;
		var memberBaiscRecord = null;
		if(me.member != null){
			memberBaiscRecord = Ext.create('MemberCustomerModel',me.member);
		}else{
			memberBaiscRecord = Ext.create('MemberCustomerModel');
		}
		return memberBaiscRecord;
	},
	//获得联系人信息data
	getMemberLinkmanData:function(){
		if(this.member != null){
			return this.member.contactList;
		}
		return null;
	},
	//获得固定客户信息data		@rock
	getMemberCustInfoData:function(){
		if(this.member != null){
			return this.member.logs;
		}
		return null;
	},
	//获得账号信息data
	getMemberAccountData:function(){
		if(this.member != null){
			return this.member.accountList;
		}
		return null;
	},
	//获得地址信息data
	getMemberAddressData:function(){
		if(this.member != null){
			return this.member.shuttleAddressList;
		}
		return null;
	},
	//一个会员有且只有一个主联系人
	validatMainContact:function(){
		var me = this;
		var i = 0;
		me.memberData.getContactStore().each(function(record){
			if(record.get('isMainLinkMan')){
				i++;
			}
		});
		if (i != 1){
			return false;
		}
		return true;
	}
});



/**
 * 会员基础信息
 */
Ext.define('MemberCustBasicInfoPanel',{
	id:'MemberCustBasicInfoPanel01',
	extend:'NoBorderFormPanel',
	items:null,
	memberData:null,
	memberBasicRecord:null,       //MemberCustomerModel类型的record
	channelType:null,            //创建会员渠道：'normal'表示从散客升降列表创建会员,'immediate'表示实时创建会员信息,'special'表示创建特殊会员
	viewStatus:null,             //查看状态，view 查看 update 修改 new 新增
	layout:{
		type:'table',
		columns:6
	},
	defaultType:'dptextfield',
	defaults:{
		labelWidth:70,
		width:240,
		enableKeyEvents:true,
		listeners:null
	},
	initComponent:function(){
		var me = this;
		//初始化部门信息
    	me.on('beforeclose',me.beforeBasicCloseEvent);
		if(me.channelType != 'special'){			
			initDeptAndUserInfo("1");
		}else{
			User.deptCityLocation = '0';
		}
		me.items = me.getItems();
		this.callParent();
		
		me.defaults.listeners = {};
		//加载会员基础数据
		me.loadMemberBasicData();
		
		me.initBusinessComponent();
	},
	//窗口关闭前事件
	beforeBasicCloseEvent:function(){
		isHK = '0';
		RealTimeVerifyResult.success[0] = true;
		RealTimeVerifyResult.message[0] = '';
	},
	//加载数据
	loadMemberBasicData:function(memberBasicRecord){
		var me = this;
		if(memberBasicRecord != null){
			me.memberBasicRecord = memberBasicRecord;
		}
		
		if(me.memberBasicRecord != null){
			//集中结算部门
			if(!Ext.isEmpty(me.memberBasicRecord.get('focusDeptId'))
			&& !Ext.isEmpty(me.memberBasicRecord.get('focusDeptName'))){
				me.memberData.getFocusDeptStore().insert(0,Ext.create('FocusDeptStoreModel',{'id':me.memberBasicRecord.get('focusDeptId'),'deptName':me.memberBasicRecord.get('focusDeptName')}));
			}
			//所属部门
			if(!Ext.isEmpty(me.memberBasicRecord.get('deptId'))
			&& !Ext.isEmpty(me.memberBasicRecord.get('deptName'))){
				me.memberData.getSpecialDeptStore().insert(0,Ext.create('FocusDeptStoreModel',{'id':me.memberBasicRecord.get('deptId'),'deptName':me.memberBasicRecord.get('deptName')}));
			}
			//所属母公司
			if(!Ext.isEmpty(me.memberBasicRecord.get('parentCompanyId'))
			&& !Ext.isEmpty(me.memberBasicRecord.get('parentNumber'))){
				me.getForm().findField('parentCompanyId').store.insert(0,Ext.create('SearchMemberResultModel',{'custId':me.memberBasicRecord.get('parentCompanyId'),'custName':me.memberBasicRecord.get('parentNumber')}));
			}
			me.getForm().loadRecord(me.memberBasicRecord);
		}
	},
	//界面显示初始化控件以及默认值
	initBusinessComponent:function(){
		var me = this;
		//如果是新增则 设定默认值
		if(me.viewStatus == 'new'){
			//"是否允许联系人兑换积分"默认"是"
			me.getForm().findField('isRedeempoints').setValue(false);
			//"是否部门重要客户"默认“否”
			me.getForm().findField('isImportCustor').setValue(false);
			//"是否集中结算"默认为“否”
			me.getForm().findField('isFocusPay').setValue(false);
			//"是否母公司"默认为“是”
			me.getForm().findField('isParentCompany').setValue(true);
			//"临欠额度"默认为1000元
			me.getForm().findField('procRedit').setValue();
			//"所属母公司"不可编辑
			me.getForm().findField('parentCompanyId').setReadOnly(true);
			if(me.channelType != 'special'){
				me.getForm().findField('deptId').setReadOnly(true);
			}
		}else{
			//如果‘是否母公司’为是则‘所属母公司’不可编辑，且值为空
			if(me.getForm().findField('isParentCompany').getValue()){
				me.getForm().findField('parentCompanyId').setValue();
				me.getForm().findField('parentCompanyId').setReadOnly(true);
			}
			//所属部门 在不是创建特殊会员时 不可编辑
			me.getForm().findField('deptId').setReadOnly(true);
		}
		//创建会员信息、实时创建会员信息 "客户等级"可手工选择  处理业务规则
		if(me.channelType == 'special'){
			//创建特殊会员信息时"客户等级"可手工选择
			me.getForm().findField('degree').setReadOnly(false);
			
			//创建会员信息、实时创建会员信息，"是否特殊客户"默认为"否",创建特殊会员信息时默认为"是"
			me.getForm().findField('isSpecial').setValue(true);
		}
		//实时创建会员 是否特殊客户，是否重要客户默认为只读
		else if(me.channelType == 'immediate'){
			me.getForm().findField('isSpecial').setValue(false);
			me.getForm().findField('isSpecial').setReadOnly(true);
			me.getForm().findField('surplusMonthlyStatementMoney').setReadOnly(true);
		}else{
			if(me.viewStatus == 'new'){
				me.getForm().findField('isSpecial').setValue(false);
			}
		}
		//是否集中结算 如果为否则 集中结算部门 不可编辑
		if(!me.getForm().findField('isFocusPay').getValue()){
			me.getForm().findField('focusDeptId').setReadOnly(true);
			me.getForm().findField('focusDeptId').setValue();
		}
		//"客户类型"为个人时"税务登记号"，"公司性质"不可编辑且都制空
		if('PERSONAL' == me.getForm().findField('custType').getValue()){
			me.getForm().findField('taxregNumber').setReadOnly(true);
			me.getForm().findField('companyProperty').setReadOnly(true);
			me.getForm().findField('taxregNumber').setValue();
			me.getForm().findField('companyProperty').setValue();
		}
	},
	getItems:function(){
		var me = this;
		var operateType = null;
		if(me.viewStatus == 'view'){
			operateType = 'VIEW';
		}else if(me.viewStatus == 'update'){
			operateType = 'UPDATE';
		}
		return [{
			fieldLabel:i18n('i18n.MemberCustEditView.custNo'),
			readOnly:true,
			name:'custNumber'
		},{xtype:'displayfield',width:12},{
			fieldLabel:i18n('i18n.ScatterUpgradeView.belongdeptName'),
			xtype:'dpcombobox',
			name:'deptId',
			displayField : 'deptName',
			valueField:'id',
			queryMode:'local',
			allowBlank:(me.channelType=='special')?false:true,
			blankText:i18n('i18n.ScatterUpgradeView.belongdeptName'),
			emptyText:i18n('i18n.MemberCustEditView.inputFoureWordsEnter'),
			forceSelection: true,
			store:me.memberData.getSpecialDeptStore(),
			listConfig: {
		     	minWidth :296   //下拉框显示宽度
			},
	        listeners:{
            	specialkey:function(th,e){
            		var successFn = function(json){
            			me.memberData.getSpecialDeptStore().removeAll();
	     			    me.memberData.getSpecialDeptStore().add(json.focusDeptList);
	     			    th.expand();
	     		    };
	     		    var param  = {'deptName':e.target.value};
	     		    param.deptId = top.User.deptId;
	     		    if(me.viewStatus == 'new' && me.channelType == 'special'){
	     		    	DpUtil.keyPress(e, me.memberData.getDeptByName,param,2,successFn);
	     		    }
            	},
            	select:function(combo,records){
            		var isSpecial = me.channelType;
            		if(records.length > 0){
            			if(records[0].get('provinceName').indexOf('香港')!=-1){
            				isHK = '1';
            				User.deptCityLocation = "1";
            			}else{
            				isHK = '0';
            				User.deptCityLocation = "0";
            			}
            			// 赋值集中结算部门 name
            			combo.up('form').getForm().findField('deptName').setValue(records[0].get('deptName'));
            			Ext.getCmp("fieldcontainerObj").remove(combo.up('form').getForm().findField('taxregNumber'));
            			Ext.getCmp("fieldcontainerObj").insert(0,{
            				xtye:'textfield',
// 根据后台传过来的数据，判断是香港还是大陆地区
            				fieldLabel:User.deptCityLocation == "1"?'商业登记号':i18n('i18n.ScatterCustManagerView.taxId'),
            						name:'taxregNumber'
            			});
            			}
            		}
	        }
		
		},{xtype:'displayfield',width:12},
			{
			fieldLabel:i18n('i18n.MemberCustEditView.custLevel'),
			name:'degree',
			xtype:'dpcombobox',
			readOnly:true,
			allowBlank:false,
			blankText:i18n('i18n.MemberCustEditView.m_noGrade'),
			store:me.memberData.getMemberGradeStore(),
			displayField:'codeDesc',
			valueField:'code',
			queryMode:'local'
		}
		,{xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12},{
			fieldLabel:i18n('i18n.PotentialCustManagerView.customerName'),
			allowBlank:false,
			name:'custName',
			maxLength:80,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80),
			blankText:i18n('i18n.PotentialCustEditView.message_custName')
		},{xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12}
		,{
			fieldLabel:i18n('i18n.MemberCustEditView.custIndustry'),
			xtype:'dpcombobox',
			queryMode:'local',
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code',
			allowBlank:false,
			name:'tradeId',
			blankText:i18n('i18n.ChangeContactAffiliatedRelationView.regex_tradeId'),
			store:me.memberData.getTradeStore()
		}
		,{xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12}
		,{
			fieldLabel:i18n('i18n.MemberCustEditView.custType'),
			xtype:'dpcombobox',
			id:'custType_id',
			name:'custType',
			allowBlank:false,
			blankText:i18n('i18n.MemberCustEditView.custTypeAlert'),
			queryMode:'local',
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code',
			store:me.memberData.getCustomerTypeStore()
		}
		,{xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12},
		{	xtype:'fieldcontainer',
			colspan:6,
			id:'fieldcontainerObj',
			layout:{
				type:'table',
				columns:6
			},
			defaultType:'dptextfield',
			defaults:{
				labelWidth:70,
				//width:195,
				width:240,
				enableKeyEvents:true,
				listeners:null
			},
			items:[
				{
//				根据后台传过来的数据，判断是香港还是大陆地区
				fieldLabel:me.returnObj()?'商业登记号':i18n('i18n.ScatterCustManagerView.taxId'),
				name:'taxregNumber'
			},{xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12},{
				fieldLabel:i18n('i18n.ScatterCustManagerView.companyProperties'),
				xtype:'dpcombobox',
				blankText:i18n('i18n.MemberCustEditView.companyPropertyAlert'),
				queryMode:'local',
	            forceSelection:true,
				displayField:'codeDesc',
				valueField:'code',
				name:'companyProperty',
				store:me.memberData.getCompNatureStore()
			},{xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12},{
				fieldLabel:i18n('i18n.ScatterCustManagerView.custAttribute'),
				xtype:'dpcombobox',
				allowBlank:false,
				blankText:i18n('i18n.MemberCustEditView.m_propertyNotNull'),
				queryMode:'local',
	            forceSelection:true,
				displayField:'codeDesc',
				valueField:'code',
				name:'custNature',
				store:me.memberData.getCustomerNatureStore()
			},{xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12}]
		},
		{
			xtype:'fieldcontainer',
			layout:{
				type:'hbox'
			},
			defaults:{
				hideLabel:true,
				enableKeyEvents:true,
				allowBlank:false
			},
			items:[{xtype:'displayfield',value:i18n('i18n.MemberCustEditView.province'),width:75},
				   {
					xtype:'areaaddresscombox',
					id:'member_cityId',
					name:'cityId',
					flex:1,
					width:110,
					operateType:operateType,
					readOnly:('view' == me.viewStatus)?true:false,
					cls:'readonly',
					selectedValue:me.memberBasicRecord.get('cityId'),
					tabPanel:['provincePanel','cityPanel'],
					blankText:i18n('i18n.MemberCustEditView.cityAlert'),
					emptyText:i18n('i18n.MemberCustEditView.cityAlert')
				   }
				]
		}
		,{xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12},{
			fieldLabel:i18n('i18n.MemberCustEditView.registAddress'),
			colspan:3,
			width:492,
			allowBlank:false,
			maxLength:300,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',300),
			name:'registAddress'
		},{xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12},{
			fieldLabel:i18n('i18n.MemberCustEditView.isSpecial'),
			xtype:'dpcombobox',
			name:'isSpecial',
			queryMode:'local',
            forceSelection:true,
			displayField:'name',
			valueField:'value',
			store:me.memberData.getBooleanStore(),
			readOnly:true,
			allowBlank:false,
			emptyText:i18n('i18n.MemberCustEditView.isOrNot'),
			blankText:i18n('i18n.MemberCustEditView.isSpecialAlert')
		},
			{xtype:'displayfield',width:12},{
			fieldLabel:i18n('i18n.MemberCustEditView.isImportCustor'),
			name:'isImportCustor',
			xtype:'dpcombobox',
			queryMode:'local',
            forceSelection:true,
			displayField:'name',
			valueField:'value',
			store:me.memberData.getBooleanStore(),
			allowBlank:false,
			emptyText:i18n('i18n.MemberCustEditView.isOrNot'),
			blankText:i18n('i18n.MemberCustEditView.isImportCustorAlert')
		},{xtype:'displayfield',width:12},{
			fieldLabel:i18n('i18n.MemberCustEditView.custPotentialType'),
			xtype:'dpcombobox',
			queryMode:'local',
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code',
			name:'custPotentialType',
			emptyText:i18n('i18n.MemberCustEditView.highOrLow'),
			store:me.memberData.getCustPotentialStore(),
			listeners:{
				change:function(field, newValue) {if (Ext.isEmpty(newValue)) {field.setValue(null);}}
			}
		},{xtype:'displayfield',width:12},{
			fieldLabel:i18n('i18n.MemberCustEditView.isAcceptMarket'),
			xtype:'dpcombobox',
			queryMode:'local',
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code',
			name:'isAcceptMarket',
			emptyText:i18n('i18n.MemberCustEditView.acceptOrNot'),
			store:me.memberData.getMarketInfoStore()
		},{xtype:'displayfield',width:12},{
			fieldLabel:i18n('i18n.MemberCustEditView.brandWorth'),
			maxLength:20,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20),
			name:'brandWorth'
		},{xtype:'displayfield',width:12},{
			fieldLabel:i18n('i18n.MemberCustEditView.channelSource'),
			xtype:'dpcombobox',
			queryMode:'local',
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code',
			name:'channelSource',
			store:me.memberData.getPreferenceChannelStore(),
			listeners:{
				change:function(field, newValue) {if (Ext.isEmpty(newValue)) {field.setValue(null);}}
			}
		},{xtype:'displayfield',width:12},{
			fieldLabel:i18n('i18n.MemberCustEditView.channel'),
			xtype:'dpcombobox',
			queryMode:'local',
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code',
			name:'channel',
			store:me.memberData.getPreferenceChannelStore(),
			listeners:{
				change:function(field, newValue) {if (Ext.isEmpty(newValue)) {field.setValue(null);}}
			}
		},{xtype:'displayfield',width:12},{
			fieldLabel:i18n('i18n.MemberCustEditView.preferenceService'),
			xtype:'dpcombobox',
			queryMode:'local',
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code',
			name:'preferenceService',
			store:me.memberData.getPreferenceServiceStore(),
			listeners:{
				change:function(field, newValue) {if (Ext.isEmpty(newValue)) {field.setValue(null);}}
			}
		},{xtype:'displayfield',width:12},{
			fieldLabel:i18n('i18n.ScatterCustManagerView.companySize'),
			xtype:'dpcombobox',
			queryMode:'local',
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code',
			name:'companyScop',
			store:me.memberData.getFirmSizeStore(),
			listeners:{
				change:function(field, newValue) {if (Ext.isEmpty(newValue)) {field.setValue(null);}}
			}
		},{xtype:'displayfield',width:12},{
			fieldLabel:i18n('i18n.MemberCustEditView.lastYearProfit'),
			xtype:'dpnumberfield',
			hideTrigger:true,
			maxLength:10,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',10),
			name:'lastYearProfit'
		},{xtype:'displayfield',width:12},{
			fieldLabel:i18n('i18n.MemberCustEditView.lastYearIncome'),
			xtype:'dpnumberfield',
			hideTrigger:true,
			maxLength:10,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',10),
			name:'lastYearIncome'
		},
			{xtype:'displayfield',width:12},{
			fieldLabel:i18n('i18n.MemberCustEditView.billTitle'),
			maxLength:40,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',40),
			name:'billTitle'
		},
//		},
		{xtype:'displayfield',width:12},{
			fieldLabel:i18n('i18n.MemberCustEditView.registerFund'),
			xtype:'dpnumberfield',
			hideTrigger:true,
			maxLength:10,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',10),
			name:'registerFund'
		},{xtype:'displayfield',width:12},{
			fieldLabel:i18n('i18n.MemberCustEditView.procRedit'),
			xtype:'dpnumberfield',
			hideTrigger:true,
			allowBlank:true,
			maxLength:10,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',10),
			blankText:i18n('i18n.MemberCustEditView.procReditAlert'),
			name:'procRedit'
		},
			{xtype:'displayfield',width:12},{
			fieldLabel:i18n('i18n.ScatterCustManagerView.remark'),
			name:'remark',
			colspan:4,
			maxLength:200,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',200),
			width:240
		},{
			hidden:true,
			name:'parentNumber' //设置隐藏域，用于存储所属母公司名称
		},{
			hidden:true,
			name:'focusDeptName' //集中结算部门name
		},{
			hidden:true,
			name:'deptName'
		}
		,{
		hidden:true,
		name:'provinceId'
	   }
	   ,{hidden:true,
        name: 'isRedeempoints'
	},{hidden:true,
        name: 'isFocusPay'
	},{hidden:true,
        name: 'focusDeptId'
	},{hidden:true,
        name: 'isParentCompany'
	},{hidden:true,
		xtype:'dpcombo',
        name: 'parentCompanyId'
	},{hidden:true,
        name: 'recommendCust'
    },{hidden:true,
        name: 'surplusMonthlyStatementMoney'
    }
	   ];
	},
	//数据字典必选择  不可为空
	getDateComboSelect:function(fieldLabel,name,store,readOnly,blankText){
		var me = this;
		return DpUtil.createCombo(fieldLabel,name,store,false,
			readOnly,70,180,false,null);
	},
	//数据字典 可为空
	getDateComboSelect:function(fieldLabel,name,store,readOnly,blankText){
		var me = this;
		return DpUtil.createCombo(fieldLabel,name,store,true,
			readOnly,70,180,true,true);
	},
	//税务登记号/商业登记号  根据需要来判断返回对象
	returnObj:function(){
		var me = this;
		if(!Ext.isEmpty(me.memberBasicRecord.data.bussinessDept)){
			return User.deptCityLocation == "1"  
				|| me.memberBasicRecord.data.bussinessDept.province.name.indexOf('香港') != -1
		}else{
			return User.deptCityLocation == "1"
		}
	},
	//处理城市 id
	manageModel4City:function(memberModel){
		var me = this;
		if(!Ext.isEmpty(memberModel)){
			var city = memberModel.get('cityId');
			if(!Ext.isEmpty(city)){
				var pca = city.split(i18n('i18n.PotentialCustManagerView.searchEndTime'));
				if(pca.length > 1){
					memberModel.set('cityId',pca[1]);
					memberModel.set('provinceId',pca[0]);
				}
			}
			return memberModel;
		}
		return memberModel;
	}
});

/**
 * 联系人信息
 */
Ext.define('MemberLinkmanTabPanel',{
	extend:'TabContentPanel',
	title:'联系人信息<span style="color:red;">*</span>',
	layout:{
		type:'vbox',
        align:'stretch'
	},
	items:null,
	parent:null,
	memberData:null,
	memberLinkmanData:null,     //加载联系人数据
	initComponent:function(){
		var me = this;
		me.items = me.getItems();		
		this.callParent();
		
		//加载联系人数据
		me.loadLinkmanData();
		
	},
	//加载联系人信息
	loadLinkmanData:function(memberLinkmanData){
		var me = this;
		if(memberLinkmanData != null){
			me.memberLinkmanData = memberLinkmanData;
		}
		
		me.memberData.getContactStore().removeAll();
		if(me.memberLinkmanData != null){
			me.memberData.getContactStore().loadData(me.memberLinkmanData);
		}
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'normalbuttonpanel',
			items:[{xtype:'popleftbuttonpanel',
					items:[
							{xtype:'button',text:i18n('i18n.PotentialCustManagerView.add')},
					       {xtype:'button',text:i18n('i18n.PotentialCustManagerView.update')},
					       {xtype:'button',text:i18n('i18n.PotentialCustManagerView.delete')}]
					},{xtype:'middlebuttonpanel'}
			      ]
		 },{xtype:'popupgridpanel',
		    flex:1,
		    store:me.memberData.getContactStore(),
			columns:[{text:i18n('i18n.MemberCustEditView.contactNo'),dataIndex:'number'},
			         {text:i18n('i18n.PotentialCustManagerView.contactName'),dataIndex:'name'},
			         {text:i18n('i18n.MemberCustEditView.contactType'),dataIndex:'linkmanType',renderer:me.renderLinkmanType},
			         {text:i18n('i18n.MemberCustEditView.gender'),width:60,dataIndex:'sex',renderer:function(value){
			        	 return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.GENDER);
			         }},
			         {text:i18n('i18n.MemberCustEditView.post'),dataIndex:'duty'},
			         {text:i18n('i18n.MemberCustEditView.mobileNo'),dataIndex:'mobilePhone'},
			         {text:i18n('i18n.MemberCustEditView.telephoneNo'),dataIndex:'telPhone'},
			         {text:i18n('i18n.MemberCustEditView.isMainContact'),dataIndex:'isMainLinkMan',renderer:DpUtil.changeBooleanToDescrip}],
		    listeners:{
			   scope:me,
			   itemdblclick : function(grid,record){
			   		me.getContactWin(me.memberData,record,'view',me.parent);
			   }
			}}
		 ];
	},
	//获得联系人窗体
	getContactWin:function(memberData,record,viewStatus,parent){
		Ext.create('MemberLinkmanEditWindow',{'memberData':memberData,
				   							   'contactRecord':record,
				   							   'parent':parent,
											   'viewStatus':viewStatus}).show();
	},
	//删除该联系人关联的账号 财务联系人信息
	delIsAccountLinkman:function(contactRecord){},
	delContactRealTimeVerifyResult:function(selected){},
	//显示联系人类型
	renderLinkmanType:function(linkmanTypeString){
		var linkmanTypeValue = '';
		var linkmanTypeArray = linkmanTypeString.split(',');
		if(linkmanTypeArray != null){
			for(var i = 0; i < linkmanTypeArray.length; i++){
				if(linkmanTypeArray[i] == 'true' || linkmanTypeArray[i] == '1'){
					if(i != 0 && !DpUtil.isEmpty(linkmanTypeValue)){
						linkmanTypeValue += ',';
					}
					linkmanTypeValue +=  DpUtil.changeDictionaryCodeToDescrip(i,DataDictionary.LINKMANTYPE);
				}
			}
		}
		return linkmanTypeValue;
	}
});

/**
 * 帐号信息
 */
Ext.define('MemberAccountTabPanel',{
	extend:'TabContentPanel',
	title:i18n('i18n.MemberCustEditView.accountInfo'),
	layout:{
		type:'vbox',
        align:'stretch'
	},
	items:null,
	memberData:null,
	memberAccountData:null,    //账号信息data
	viewStatus:null,
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
		
		//加载账号信息
		me.loadMemberAccountData();
	},
	//加载账号信息
	loadMemberAccountData:function(memberAccountData){
		var me = this;
		if(memberAccountData != null){
			me.memberAccountData = memberAccountData;
		}
		
		me.memberData.getAccountStore().removeAll();
		if(me.memberAccountData != null){
			me.memberData.getAccountStore().loadData(me.memberAccountData);
		}
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'normalbuttonpanel',
			items:[{
						xtype:'popleftbuttonpanel',
						items:[{xtype:'button',text:i18n('i18n.PotentialCustManagerView.add'),
							//设置在查看模式下或者非收银员的操作的时候，四个按键为不可操作
							disabled:(('view' == me.viewStatus)||
									(!isPermission('/customer/accountRecognizedABtn.action')
									&&('update' == me.viewStatus))||
									(!isPermission('/customer/accountRecognizedDBtn.action')
											&&('new' == me.viewStatus))
									)?true:false,
						    scope:me,
						    id:'memberAccountTab_add_id'},
					       {xtype:'button',text:i18n('i18n.PotentialCustManagerView.update'),
							disabled:(('view' == me.viewStatus)||
									(!isPermission('/customer/accountRecognizedUBtn.action')
									&&('update' == me.viewStatus))||
									(!isPermission('/customer/accountRecognizedDBtn.action')
											&&('new' == me.viewStatus))
									)?true:false,
						    scope:me,
						    id:'memberAccountTab_update_id'},
					       {xtype:'button',text:i18n('i18n.PotentialCustManagerView.delete'),
							disabled:(('view' == me.viewStatus)||
									(!isPermission('/customer/accountRecognizedDBtn.action')
									&&('update' == me.viewStatus))||
									(!isPermission('/customer/accountRecognizedDBtn.action')
											&&('new' == me.viewStatus))
									)?true:false,
						    scope:me,
						    id:'memberAccountTab_del_id'},
						    {xtype:'button',text:i18n('i18n.ScatterCustManagerView.infoScatterAccount'),
						    	hidden:'normal'== me.channelType ? false:true,
								disabled:(('view' == me.viewStatus)||
										(!isPermission('/customer/accountRecognizedDBtn.action')
										&&('update' == me.viewStatus))||
										(!isPermission('/customer/accountRecognizedDBtn.action')
												&&('new' == me.viewStatus))
										)?true:false
						    }]
				    },{xtype:'middlebuttonpanel'}
				  ]
		 },{xtype:'popupgridpanel',
		    flex:1,
		    store:me.memberData.getAccountStore(),
			columns:[{text:i18n('i18n.MemberCustEditView.openBank'),dataIndex:'bank'},
			         {text:i18n('i18n.MemberCustEditView.openName'),dataIndex:'countName'},
			         {text:i18n('i18n.MemberCustEditView.accountNo'),dataIndex:'bankAccount',width:150},
			         {text:i18n('i18n.MemberCustEditView.accountPurpose'),dataIndex:'accountUse',renderer:function(value){
			        	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.ACCOUNT_USE);
			         }},
			         {text:i18n('i18n.MemberCustEditView.accountContactName'),dataIndex:'financeLinkman'},
			         {text:i18n('i18n.MemberCustEditView.contactPhone'),dataIndex:'linkManMobile'},
			         {text:i18n('i18n.MemberCustEditView.contactTel'),dataIndex:'linkManPhone'},
			         {text:i18n('i18n.MemberCustEditView.isAcquiesceAccount'),dataIndex:'isdefaultaccount',renderer:DpUtil.changeBooleanToDescrip},
			         {text:i18n('i18n.MemberCustEditView.CRAccount'),dataIndex:'relation'},
			         {text:i18n('i18n.MemberCustEditView.accountProvince'),dataIndex:'bankProvinceName',hidden:true},
			         {text:i18n('i18n.MemberCustEditView.accountCity'),dataIndex:'bankCityName',hidden:true},
			         {text:i18n('i18n.MemberCustEditView.accountProvinceid'),dataIndex:'bankProvinceId',hidden:true},
			         {text:i18n('i18n.MemberCustEditView.accountCityid'),dataIndex:'bankCityId',hidden:true}],
				 listeners:{
					   scope:me,
					   itemdblclick : function(grid,record){
					   		me.getAccountWin(me.memberData,record,'view');
					   }
					}
		 },{
			 //在账号页面下边增加一个红色提示语句
			xtype:'displayfield',
 			value:'<span style="color:red">提示：账号信息新增/修改/删除操作，只有部门收银员有权限</span>',
 			margin:'2 215 2 0',
 			//当点击查看时隐藏这句话
 			hidden:('view' == me.viewStatus)?true:false
		 }
		 ];
	},
	//获得账号窗体
	getAccountWin:function(memberData,record,viewStatus){
		Ext.create('MemberAccountEditWin',{'memberData':memberData,
			   							   'accountRecord':record,
										   'viewStatus':viewStatus}).show();
	}
});

/**
 * 接送货地址
 */
Ext.define('MemberAddressTabPanel',{
	extend:'TabContentPanel',
	title:i18n('i18n.MemberCustEditView.shuttleAddress'),
	layout:{
		type:'vbox',
        align:'stretch'
	},
	items:null,
	memberData:null,
	memberAddressData:null,    //会员接送货地址data
	viewStatus:null,
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
		
		//加载接送货地址数据
		me.loadMemberAddressData();
	},
	//加载接送货地址信息
	loadMemberAddressData:function(memberAddressData){
		var me = this;
		if(memberAddressData != null){
			me.memberAddressData = memberAddressData;
		}
		
		me.memberData.getShuttleAddressStore().removeAll();
		if(me.memberAddressData != null){
			me.memberData.getShuttleAddressStore().loadData(me.memberAddressData);
		}
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'normalbuttonpanel',
			items:[{xtype:'popleftbuttonpanel',
					items:[{xtype:'button',text:i18n('i18n.PotentialCustManagerView.add'),
							disabled:('view' == me.viewStatus)?true:false},
					       {xtype:'button',text:i18n('i18n.PotentialCustManagerView.update'),
							disabled:('view' == me.viewStatus)?true:false},
					       {xtype:'button',text:i18n('i18n.PotentialCustManagerView.delete'),
							disabled:('view' == me.viewStatus)?true:false}]
				   },{xtype:'middlebuttonpanel'}
				  ]
		 },{xtype:'popupgridpanel',
		    flex:1,
		    store:me.memberData.getShuttleAddressStore(),
			columns:[{text:i18n('i18n.MemberCustEditView.addressType'),flex: 1,dataIndex:'addressType',renderer:function(value){
						return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.ADDRESS_TYPE);
					 }},
			         {text:i18n('i18n.PotentialCustEditView.address'),flex: 1,dataIndex:'address'}],
				 listeners:{
					   scope:me,
					   itemdblclick : function(grid,record){
							var win = me.getAddressWin(me.memberData,record,'view');
							win.show();
							win.down('form').getForm().findField('address').setValue(record.get('address'));

					   }
					}}
		 ];
	},
	//获得接送货地址窗体
	getAddressWin:function(memberData,record,viewStatus){
		var win = Ext.create('MemberAddressEditwin',{'memberData':memberData,
			   							   'shuttleAddressRecord':record,
										   'viewStatus':viewStatus});
		return win;								   
	}
});
/**
 * 固定客户信息tab
 */
Ext.define('memberCustTabPanel',{
	extend:'TabContentPanel',
	title:'修改日志',
	layout:{
		type:'vbox',
        align:'stretch'
	},
	items:null,
	memberCustInfoData:null,			//固定会员信息修改日志grid数据
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
		me.loadCustInfoData(me.memberCustInfoData);
//		：后端完成后，开启数据
	},
	getItems:function(){
 			var me = this;
 			var field = ['code','codeDesc'];
 			// 创建gird的columns
 			var columns = [/*new Ext.grid.RowNumberer(),*/
 			{
 		        header	: i18n('i18n.RecordExchangeRuleManagerView.modiflyUser'),	//修改人
 		        dataIndex: 'modifyUserName',
 		        flex: 1
 		    },{
 	        	header	: i18n('i18n.RecordExchangeRuleManagerView.modiflyDept'),	//修改部门
 	            dataIndex: 'department',
 	            flex: 1
 	        },{
 	        	header	: i18n('i18n.RewardRuleEditView.modified'),					//修改时间
 	            dataIndex: 'modifyDate',
 	            flex: 1
 	        },{
 	        	header	: i18n('i18n.ContractEditView.workFlowNum'),				//工作流号
	        	dataIndex: 'workflowId',
	        	flex: 1
	        }];
 			return [new CustInfoGrid({
 			        	//'store':me.memberData.getCustInfoStore(),
// 			        	'store':memberCustControl.getCustInfoStore(),				//：获取表格信息				
 			        	'store':new CustInfoStore(),	//：获取表格信息		
		        		'columns':columns
 			        })];
 	},
	loadCustInfoData:function(CustInfoData){
		var me = this;
		Ext.isEmpty(CustInfoData)?[]:(me.memberCustInfoData = CustInfoData);
		me.memberData.getCustInfoStore().removeAll();
		if(!Ext.isEmpty(me.memberCustInfoData)){
			var data  = me.memberCustInfoData;
			for(var i in data){
				data[i].department = data[i].department.deptName;
				data[i].modifyDate = DpUtil.renderDate(DpUtil.changeLongToDate(data[i].modifyDate));
			};
			Ext.getCmp('CustInfoGrid').getStore().loadData(data);
		}
	}
});
/**
 * @returns CustInfoGrid 固定会员信息修改日志grid
 */
Ext.define('CustInfoGrid',{
	extend:'PopupGridPanel',
	id:'CustInfoGrid',
	storeId:null,
	height: 262,
	model:'MULTI',			//???
    sortableColumns:false,	//不能排序
    enableColumnHide:false,	//不能隐藏
    enableColumnMove:false,	//不能移动
	getSelModel:function(){
		var me = this;
		return Ext.create('Ext.selection.CheckboxModel',{
				mode:'SINGLE'//单选模式
			});
	},
    initComponent:function(){
		var me = this;
		me.selModel = me.getSelModel();
		me.listeners = {
		   itemdblclick : function(grid,record){
				me.getChangeRecordWin(record);
		   }
		};
		this.callParent();
	},
	//获得查看修改日志记录窗体
	getChangeRecordWin:function(record){
		var me = this;
		Ext.create('ChangeRecordWin',{'y':100,'width':600,'height':400}).show();
		//:获取展示固定客户信息表哥需要的数据：
		var params = {'logId':record.data.id};
		//:获取到record里面的logId；
		var successFn = function(json){
			Ext.getCmp('ChangeRecordGridId').getStore().removeAll();
			var data = json.approveDataList;
			if(!Ext.isEmpty(data)){
				Ext.getCmp('ChangeRecordGridId').getStore().loadData(me.processingData(data));
			};
		};
		var failureFn = function(json){
			if(Ext.isEmpty(json)){
				MessageUtil.showErrorMes('抱歉，响应超时！');
			}
			else{
				MessageUtil.showErrorMes(json.message);
			}
		};
		memberCustControl.searchApproveDataByLogId(params,successFn,failureFn);
	},
	//处理会员维护修改数据，对ID类的数据不进行显示
	processingData:function(alterInfo){
		var me = this;
		var returnValue = [];
		for (var i = 0; i < alterInfo.length; i++){
			var fieldName = ModifyMember.getField(alterInfo[i].fieldName);
			if('KEY' != fieldName && 'LIST' != fieldName && 'OBJ' != fieldName && 'ISSTATUS' != fieldName){
				//修改前后相同的的不显示
				if(alterInfo[i].newValue!=alterInfo[i].oldValue
				&&!(Ext.isEmpty(alterInfo[i].newValue)
				&&Ext.isEmpty(alterInfo[i].oldValue))){
					returnValue.push(alterInfo[i]);
				}
			}
		}
		return returnValue;
	}
});

/**
 * copy of ——工作流审批第一界面修改信息显示面板
 * 固定会员信息修改记录Grid
 */
Ext.define('ChangeRecordGrid',{
	extend:'PopupGridPanel',
	id:'ChangeRecordGridId',
	parentData:null,
	initComponent:function(){
		var me = this;
		me.columns = me.getColumns();
		this.callParent();
	},
	getColumns:function(){
		var me = this;
		return [new Ext.grid.RowNumberer(),
		        {
				flex:1,
				header : i18n('i18n.MyWorkFlowDealEditView.fieldName'),
				dataIndex : 'fieldName',
				renderer : ModifyMember.getField
			},{
				flex:1,
				header : i18n('i18n.MyWorkFlowDealEditView.beforeUpdate'),
				dataIndex : 'oldValue',
				renderer : function(value, metaData, record){
					var name = record.get('fieldName');
					//标志位 "ISTXT"代表直接显示该文本
					if('ISTXT' == ModifyMember.getFieldModify(name,value)){
						return value;
					}else if('ISDATE' == ModifyMember.getFieldModify(name,value)){
						return DpUtil.renderDate(value);
					}else{
						return ModifyMember.getFieldModify(name,value);
					}
				}
			},{
				flex:1,
				header : i18n('i18n.MyWorkFlowDealEditView.afterUpdate'),
				dataIndex : 'newValue',
				renderer : function(value, metaData, record){
					var name = record.get('fieldName');
					if('ISTXT' == ModifyMember.getFieldModify(name,value)){
						return value;
					}else if('ISDATE' == ModifyMember.getFieldModify(name,value)){
						return DpUtil.renderDate(value);
					}else{
						return ModifyMember.getFieldModify(name,value);
					}
				}
			}
			];
	}
});

Ext.define('ChangeRecordWin',{
	extend:'PopWindow',
	title:'修改详情',
//:i18n固定会员信息修改记录
	workflowPanel:null,
	layout:'fit',
	items:null,
	params:null,						//父界面 传递参数
	initComponent:function(){
		this.fbar = this.getFbar();
	
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		return [new ChangeRecordGrid({
			'store':new ChangeRecordStore()			//：获取表格信息
		})];
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.ScatterCustManagerView.close'),
			margin:'12 10 0 0',
			handler:function(){
				me.close();
			}
		}];
	}
});
/**
 * 联系人编辑界面
 */
Ext.define('MemberLinkmanEditWindow',{
	extend:'PopWindow',
	title:i18n('i18n.MemberCustEditView.contactEditUI'),
	linkmanBasicInfo:null,         //联系人基本信息
	linkmanAddressHobbyInfo:null,  //联系人接送货偏好
	parent:null,
	layout:{
		type:'vbox',
        align:'stretch'
	},
	items:null,
	width:825,
	height:500,
	fbar:null,
	memberData:null,
	contactRecord:null,
	viewStatus:null,         //new表示新增 ,update 表示修改,view 表示查看
	initComponent:function(){
		var me = this;
		if(!Ext.isEmpty(me.contactRecord)&&Ext.isEmpty(me.contactRecord.get('id'))){
			me.contactRecord.set('id',MemberCustEditView.getSequence());
		}
		me.linkmanBasicInfo = Ext.create('MemberLinkmanForm',{'viewStatus':me.viewStatus,'memberData':me.memberData,'contactRecord':me.contactRecord,'parent':me});
		me.linkmanAddressHobbyInfo = Ext.create('MemberLinkmanHobby',{'viewStatus':me.viewStatus,'memberData':me.memberData,'contactRecord':me.contactRecord});
		me.items = [{
			xtype:'basicpanel',
			height:230,
			items:[me.linkmanBasicInfo]
		},{
			xtype:'basicpanel',
			flex:1,
			items:[me.linkmanAddressHobbyInfo]
		}];
		me.fbar = me.getFbar();
		
		this.callParent();
		//如果是新增状态清空所有组件值
		if(me.viewStatus == 'new'){
			me.linkmanBasicInfo.getForm().reset();
		}
		me.initBusinessComponent();
		//如果是查看状态锁定所有控件
		if(me.viewStatus == 'view'){
			me.lockAllComponents();
		}
		me.on('beforeClose',function(){
			//当前联系人的 联系id
			var contactId = me.contactRecord.get('id');
			//联系人 编辑界面点击取消按钮 如果是 "个人"客户"主联系人"的"身份证号"为空则身份证校验结果为 提示不可为空
			//if((Ext.isEmpty(me.contactRecord.get('idCard'))||Ext.isEmpty(me.contactRecord.get('cardTypeCon'))) && me.contactRecord.get('isMainLinkMan') && 'PERSONAL' == Ext.getCmp('custType_id').getValue()){
			if(Ext.isEmpty(me.contactRecord.get('idCard')) && me.contactRecord.get('isMainLinkMan') && 'PERSONAL' == Ext.getCmp('custType_id').getValue()){
				RealTimeVerifyResult.success[5] = false;
				RealTimeVerifyResult.message[5] = i18n('i18n.MemberCustEditView.m_idCardNotNull');
			}else{
				RealTimeVerifyResult.success[5] = true;
				RealTimeVerifyResult.message[5] = '';
			}
			// 重置本次证件号码合法性验证结果
			RealTimeVerifyResult.success[9] = true;
			RealTimeVerifyResult.message[9] = '';
			me.resetVerifyResult(contactId,RealTimeVerifyResult.message[10]);
			
			// 重置本次联系人 手机号码验证结果
			RealTimeVerifyResult.success[8] = true;
			RealTimeVerifyResult.message[8] = '';
			me.resetVerifyResult(contactId,RealTimeVerifyResult.message[2]);
			// 重置本次联系人 固话加联系人号码验证结果
			RealTimeVerifyResult.success[6] = true;
			RealTimeVerifyResult.message[6] = '';
			me.resetVerifyResult(contactId,RealTimeVerifyResult.message[7]);
			//重置联系人编码store里面的数据
			Ext.getCmp('contactNum_id').store.removeAll();
		});
	},
	//重置编辑联系人 校验数组结果
	resetVerifyResult:function(contactId,verifyResult){
		var me = this;
		for (var i = 0; i < verifyResult.length; i++){
			if(!Ext.isEmpty(contactId)
			&& contactId == verifyResult[i].contactId){
				verifyResult[i].success = true;
				verifyResult[i].message = '';
			}
		}
	},
	//新增联系人 Id 序列，为了和后台区分 增加"X"开头区别
	getSequence:function(){
		return 'X'+Sequence++;
	},
	//界面业务控件初始化
	initBusinessComponent:function(){
		var me = this;
		
		var linkmanBasicForm = me.linkmanBasicInfo.getForm();
		var mobile = linkmanBasicForm.findField('mobilePhone');
		var phone = linkmanBasicForm.findField('telPhone');
		var linkmanName = linkmanBasicForm.findField('name');
		
		//从散客升级列表而来的联系人信息
		if(me.viewStatus == 'update' && me.contactRecord.get('createSource') == 1){
			//手机号码、固定电话同时存在或者只有手机号码,手机号码不可修改,固定电话可以编辑;
			//有手机号码，联系人姓名可以编辑;只存在固定电话，联系人姓名不可编辑
			//只有固定电话,固定电话栏不可编辑,手机号码可编辑
			if(!DpUtil.isEmpty(mobile.getValue())){
				mobile.setReadOnly(true);
			}else if(!DpUtil.isEmpty(phone.getValue())){
				linkmanName.setReadOnly(true);
				phone.setReadOnly(true);
			}
		}
		//如果从实时创建会员,如果有手机号码，则手机号码不可编辑，固定电话可编辑，如果有固定电话和名称，则固定电话和名称不可编辑，
		//手机号码可编辑
		else if(me.viewStatus == 'update' && me.contactRecord.get('createSource') == 2){
			if(!DpUtil.isEmpty(mobile.getValue())){
				mobile.setReadOnly(true);
			}else if(!DpUtil.isEmpty(phone.getValue()) && !DpUtil.isEmpty(linkmanName.getValue())){
				phone.setReadOnly(true);
				linkmanName.setReadOnly(true);
			}
		}
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.MemberCustEditView.ensure'),
			disabled:('view' == me.viewStatus)?true:false
		},{
			xtype:'button',
			text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
			handler:function(){
				me.close();
			}
		}];
	},
	//锁定联系人基本信息
	lockAllComponents:function(){
		var me = this;
		var linkmanBasicForm = me.linkmanBasicInfo.getForm();
		linkmanBasicForm.getFields().each(function(field){
			field.setReadOnly(true);
		});
	}
});

/**
 * 联系人基本信息
 */
Ext.define('MemberLinkmanForm',{
	extend:'NoBorderFormPanel',
	id:'memberLinkmanForm_id',
	items:null,
	fireTimes:0,  //控制enter事件使用
	parent:null,
	memberData:null,
	viewStatus:null,
	contactRecord:null,
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		
		me.defaults.listeners = {};
		this.callParent();
		
		//加载联系人数据
		me.getForm().loadRecord(me.contactRecord);
	},
	layout:{
		type:'table',
		columns:8
	},
	defaultType:'dptextfield',
	defaults:{
		labelWidth:65,
		width:185,
		enableKeyEvents:true,
		listeners:null
    },
	getItems:function(){
		var me = this;
		var cardTypeStore = me.memberData.getCardTypeConAUStore();;
		if(me.viewStatus == 'view'){
			cardTypeStore = me.memberData.getCardTypeConStore();
		}else if(isPermission('/customer/cardTypeOrther.action')){//拥有大区以上权限
			cardTypeStore = me.memberData.getCardTypeConNotViewStore();
		}
		return [{fieldLabel:i18n('i18n.MemberCustEditView.contactNo'),
			xtype:'dpcombobox',
    	    emptyText:'姓名首字母+联系方式',
    	    allowBlank:false,
    	    id:'contactNum_id',
    	    name:'number',
			regex : /^[a-z]{1,20}[0-9]{7,12}$/,
    	    blankText:'请输入联系人编码，规则为姓名首字母+联系方式(优先选手机号码)',
    	    regexText:'联系人编码由联系人姓名首字母(小写)+手机号码/固定电话组成',
    	    minLength:10,
    	    minLengthText:i18n('i18n.MemberCustEditView.contactNumMustSmall10'),
			maxLength:40,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',40),
			store:me.memberData.getContactNumStore(),
			queryMode:'local',
			displayField:'number',
			valueField:'number',
			listeners:{
				scope:me,
				blur:me.blur2Lower
			}},
    	   {xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12},
           {fieldLabel:i18n('i18n.PotentialCustManagerView.contactName'),
    		allowBlank:false,
			maxLength:80,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80),
	    	blankText:i18n('i18n.MemberCustEditView.pleaseInputContactName'),
    	    name:'name',
    	    id:'contactName_id'
           },
           {xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12},
           	{
				fieldLabel:'证件类型',
				xtype:'dpcombobox',
				id:'cardTypeCon_id',
				name:'cardTypeCon',
				queryMode:'local',
	            forceSelection:true,
				displayField:'codeDesc',
				valueField:'code',
				store:cardTypeStore,
			    listeners:{
			    	change:function(field, newValue) {if (Ext.isEmpty(newValue)) {field.setValue(null);}}
			    }
			},
           {xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12},
           {fieldLabel:i18n('i18n.ScatterCustManagerView.idNumber1'),
    	    name:'idCard',
    	    id:'contactIdCard',
			maxLength:50,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',50),
		    blankText:i18n('i18n.MemberCustEditView.idCardAlert')},
           {xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12},
           {xtype:'displayfield',colspan:8,width:788,hidden:me.viewStatus=='view',value:i18n('i18n.MemberCustEditView.m_cardDiffer')},
           
	       {	xtype:'fieldcontainer',
			colspan:8,
			layout:
				{
				type:'table',
				columns:9
			},
			defaultType:'dptextfield',
			items:[{xtype:'checkboxgroup',
        	fieldLabel:i18n('i18n.MemberCustEditView.contactType'),
        	layout:'column',
        	colspan:6,
        	width:556,
        	vertical: true,
        	defaults:{
				listeners:{
					scope:me,
					render:function(checkBox){
						//加载联系人类型数据
						if(me.contactRecord != null){
							var linkmanTypeString = me.contactRecord.get('linkmanType');
							if(!DpUtil.isEmpty(linkmanTypeString)){
								var linkmanTypeArray = linkmanTypeString.split(',');
								if(linkmanTypeArray[checkBox.inputValue] != null){
									checkBox.setValue(linkmanTypeArray[checkBox.inputValue]);
									if('0' == checkBox.inputValue && '0' == linkmanTypeArray[0]){
										checkBox.setValue(false);
									}
								}
							}
						}
					}
				}
        	},
        	allowBlank:false,
        	blankText:i18n('i18n.MemberCustEditView.contactTypeAlert'),
        	items:[{boxLabel:i18n('i18n.MemberCustEditView.financeContact'),name:'linkmanType',inputValue:'0'},
        	       {boxLabel:i18n('i18n.MemberCustEditView.businessContact'),name:'linkmanType',inputValue:'1'},
        	       {boxLabel:i18n('i18n.MemberCustEditView.deliverContact'),name:'linkmanType',inputValue:'2'},
        	       {boxLabel:i18n('i18n.MemberCustEditView.receiveContact'),name:'linkmanType',inputValue:'3'},
        	       {boxLabel:i18n('i18n.MemberCustEditView.agreementContact'),name:'linkmanType',inputValue:'4'},
        	       {xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12}]},
        	    	{
        	    fieldLabel:i18n('i18n.MemberCustEditView.isMainContact'),
	        	name:'isMainLinkMan',
	        	allowBlank:false,
	        	width:220,
	        	labelWidth:100,
	        	xtype:'dpcombobox',
	        	queryMode:'local',
	            forceSelection:true,
				displayField:'name',
				valueField:'value',
				store:me.memberData.getBooleanStore(),
	        	blankText:i18n('i18n.MemberCustEditView.isMainContactAlert')
        	    }, {xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12}
			    ]
        	},
           {fieldLabel:i18n('i18n.MemberCustEditView.gender'),
        	allowBlank:false,
        	xtype:'dpcombobox',
        	queryMode:'local',
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code',
			store:me.memberData.getGenderStore(),
        	name:'sex',
        	blankText:i18n('i18n.MemberCustEditView.genderAlert')},
           {xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12},
           {fieldLabel:i18n('i18n.MemberCustEditView.telephoneNo'),
			regex : DpUtil.linkWayLimit('T'),
			regexText:i18n('i18n.MemberCustEditView.pleaseInputPhone'),
	        name:'telPhone',
    	    id:'telPhone_id'
    	  },
           {xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12},
           {fieldLabel:i18n('i18n.MemberCustEditView.mobileNo'),
		    name:'mobilePhone',
    	    id:'mobilePhone_id',
			regex : DpUtil.linkWayLimit('M'),
			regexText:i18n('i18n.MemberCustEditView.pleaseInputCorrectMobilePhone'),
			listeners:{
				change:DpUtil.autoChangeMobileLength
			}},
           {xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12},
           {xtype:'displayfield',colspan:2,hidden:me.viewStatus=='view',value:i18n('i18n.MemberCustEditView.m_mobileTelNum')},
           {fieldLabel:i18n('i18n.MemberCustEditView.post'),
			maxLength:40,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',40),
			name:'duty'},
           {xtype:'displayfield',width:12},
           {fieldLabel:i18n('i18n.MemberCustEditView.officeholdingDept'),
			maxLength:40,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',40),
			name:'dutyDept'},
           {xtype:'displayfield',width:12},
           {fieldLabel:i18n('i18n.MemberCustEditView.birthDate'),
            id:'contactBrithday',
			name:'bornDate',
			xtype:'dpdatefield',
			format:'Y-m-d',
			maxValue:new Date()},
           {xtype:'displayfield',width:12},
           {fieldLabel:i18n('i18n.MemberCustEditView.companyWay'),
				name:'gainave'},
           {xtype:'displayfield',width:12},
           {fieldLabel:i18n('i18n.MemberCustEditView.logisticsDecision'),
			name:'decisionRight',
			xtype:'dpcombobox',
			queryMode:'local',
            forceSelection:true,
            displayField:'codeDesc',
			valueField:'code',
			store:me.memberData.getLogistDeciStore()},
           {xtype:'displayfield',width:12},
           {fieldLabel:i18n('i18n.MemberCustEditView.nativeAddress'),
				name:'nativePlace',
				maxLength:20,
				maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20)},
           {xtype:'displayfield',width:12},
           {fieldLabel:i18n('i18n.MemberCustEditView.hobby'),
			maxLength:100,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',100),
				name:'personLove'},
           {xtype:'displayfield',width:12},
           {fieldLabel:i18n('i18n.MemberCustEditView.nation'),
			maxLength:20,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20),
				name:'folk'},
           {xtype:'displayfield',width:12},
           {fieldLabel:'Email',
			maxLength:50,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',50),
				name:'email',
				vtype:'email'},
           {xtype:'displayfield',width:12},
           {fieldLabel:'QQ',
				name:'qqNumber',
				regex : /^[1-9][0-9]{4,19}$/,	
				regexText:i18n('i18n.MemberCustEditView.QQNumMust510AndFristNot1')
           },
           {xtype:'displayfield',width:12},
           {fieldLabel:'MSN',
			maxLength:80,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80),
				name:'msn'},
           {xtype:'displayfield',width:12},
           {fieldLabel:i18n('i18n.MemberCustEditView.wangWang'),
			maxLength:80,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80),
				name:'ww'},
           {xtype:'displayfield',width:12},
           {fieldLabel:i18n('i18n.MemberCustEditView.aLiId'),
			maxLength:80,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80),
				name:'alid'},
           {xtype:'displayfield',width:12},
           {fieldLabel:i18n('i18n.MemberCustEditView.netsCampId'),
			maxLength:80,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80),
				name:'onlineBusinessId'},
           {xtype:'displayfield',width:12},
           {fieldLabel:i18n('i18n.MemberCustEditView.taoBaoId'),
			maxLength:80,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80),
				name:'taobId'},
           {xtype:'displayfield',width:12},
           {fieldLabel:i18n('i18n.MemberCustEditView.kingDeeId'),
			maxLength:80,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80),
				name:'youshangId'},
           {xtype:'displayfield',width:12}
           ];
	},
	//联系人编码 变成小写
	blur2Lower:function(textfield){
		if(!Ext.isEmpty(textfield.getValue())){
			textfield.setValue(textfield.getValue().toLowerCase());
		}
	},
	//新增联系人 Id 序列，为了和后台区分 增加"X"开头区别
	getSequence:function(){
		return 'X'+Sequence++;
	}
});

/**
*联系人偏好
*/
Ext.define('MemberLinkmanHobby',{
	extend:'BasicPanel',
	layout:'fit',
	items:null,
	memberData:null,
	contactRecord:null,   //联系人record
	viewStatus:null,//查看状态
	initComponent:function(){
		var me = this;
		me.items = me.getItems();			
		this.callParent();
		//初始化联系人偏好地址
		me.loadContactPerfectAddress(me.contactRecord);
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'fieldset',
			title:i18n('i18n.MemberCustEditView.shuttleAddressHobby'),
			autoScroll:true,
			layout:{
				type:'vbox',
		        align:'stretch'
			},
			items:[{
				xtype:'normalbuttonpanel',
				items:[{xtype:'popleftbuttonpanel',
						items:[{xtype:'button',text:i18n('i18n.PotentialCustManagerView.add'),
								disabled:('view' == me.viewStatus)?true:false},
						       {xtype:'button',text:i18n('i18n.PotentialCustManagerView.update'),
								disabled:('view' == me.viewStatus)?true:false
								},
						       {xtype:'button',text:i18n('i18n.PotentialCustManagerView.delete'),
								disabled:('view' == me.viewStatus)?true:false
								}]
				        },{xtype:'middlebuttonpanel'}]
			 },{xtype:'popupgridpanel',
				autoScroll:true,
			    height:125,
			    store:me.memberData.getPreferenceAddressStore(),
				columns:[{text:i18n('i18n.MemberCustEditView.addressType'),dataIndex:'addressType',renderer:function(value){
				        	 return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.ADDRESS_TYPE);
				         }},
				         {text:i18n('i18n.PotentialCustEditView.address'),dataIndex:'address'},
				         {
				             text: i18n('i18n.MemberCustEditView.timeHobby'),
				             columns: [{
				                 text:i18n('i18n.RewardRuleManagerView.pointbegintime'),
				                 dataIndex:'startTime',
				                 renderer:me.renderTimeField
				             }, {
				                 text:i18n('i18n.RewardRuleManagerView.pointendtime'),
				                 dataIndex:'endTime',
				                 renderer:me.renderTimeField
				             }]
				         },
				         {text:i18n('i18n.MemberCustEditView.invoiceRequirements'),dataIndex:'billRequest',renderer:function(value){
				        	 return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.BILL_REQUIRE);
				         }},
				         {text:i18n('i18n.MemberCustEditView.parkFee'),dataIndex:'hasStopCost',renderer:DpUtil.changeBooleanToDescrip},
				         {text:i18n('i18n.MemberCustEditView.ortherRequirements'),dataIndex:'otherNeed'},
				         {text:i18n('i18n.MemberCustEditView.deliveryUpstairs'),dataIndex:'isSendToFloor',renderer:DpUtil.changeBooleanToDescrip},
				         {text:i18n('i18n.MemberCustEditView.payWay'),dataIndex:'payType',renderer:function(value){
				        	 return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.PAY_WAY);
				         }}],
				 listeners:{
					   scope:me,
					   itemdblclick : function(grid,record){
					   		me.getAddressHobbyWin(me.memberData,record,'view');
					   }
					}}
			 ]
		}];
	},
	//显示timefield
	renderTimeField:function(value){
	    if(value != null){
			return Ext.Date.format(new Date(value), 'H 点');
		}else{
			return null;
		}
     },
	//得到偏好地址窗体
	getAddressHobbyWin:function(memberData,record,viewStatus){
		Ext.create('MemberAddressHobbyEditWin',{'memberData':memberData,
				   							    'preferenceAddressRecord':record,
											    'viewStatus':viewStatus}).show();
	},
	//加载联系人偏好地址数据
	loadContactPerfectAddress:function(contactRecord){
		var me = this;
		me.memberData.getPreferenceAddressStore().removeAll();
		//初始化联系人偏好地址
		if(me.contactRecord != null &&　me.contactRecord.get('preferenceAddressList')　!= null){
			me.memberData.getPreferenceAddressStore().loadData(me.contactRecord.get('preferenceAddressList'));
		}
	}
});

/**
* 联系人接送货地址偏好编辑界面
*/
Ext.define('MemberAddressHobbyEditWin',{
	extend:'PopWindow',
	layout:'fit',
	items:null,
	fbar:null,
	title:i18n('i18n.MemberCustEditView.shuttleAddressHobbyEdit'),
	width:620,
	height:200,
	memberData:null,
	preferenceAddressRecord:null,  //PreferenceAddressModel类型的record
	viewStatus:null,               //new表示新增，update表示修改,view表示查看
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		me.fbar = me.getFbar();
		this.callParent();
		
		//加载数据
		me.down('form').getForm().loadRecord(me.preferenceAddressRecord);
		//初始化接送货地址信息
		if(!DpUtil.isEmpty(me.preferenceAddressRecord.get('address')) && !DpUtil.isEmpty(me.preferenceAddressRecord.get('address'))){
			me.down('form').getForm().findField('address').setValue(me.preferenceAddressRecord.get('address'));
			if(Ext.isEmpty(me.preferenceAddressRecord.get('id'))){
				me.down('form').getForm().findField('address').setRawValue(me.preferenceAddressRecord.get('address'));
			}
		}
		if(me.viewStatus == 'new'){
			var form = me.down('form').getForm();
			form.reset();
			//停车费和 送货上楼 默认为 否
			form.findField('hasStopCost').setValue(false);
			form.findField('isSendToFloor').setValue(false);
		}else if(me.viewStatus == 'view'){
			me.lockAllComponents();
		}
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'noborderformpanel',
			layout:{
				type:'table',
				columns:3
			},
			defaultType:'dptextfield',
			defaults:{
				labelWidth:70,
				width:194,
				enableKeyEvents:true
		    },
			items:[{
					    fieldLabel:i18n('i18n.MemberCustEditView.shuttleAddress'),
					    xtype:'dpcombobox',
					    blankText:i18n('i18n.MemberCustEditView.shuttleAddressHobbyAlert'),
					    queryMode:'local',
					    forceSelection:true,
					    displayField:'address',
					    valueField:'address',
					    store:me.memberData.getShuttleAddressStore(),
					    allowBlank:false,
					    name:'address'
					},
			       {
					    fieldLabel:i18n('i18n.MemberCustEditView.addressType'),
					    name:'addressType',
					    readOnly:true,
					    xtype:'dpcombobox',
						queryMode:'local',
			            forceSelection:true,
			            displayField:'codeDesc',
						valueField:'code',
						store:me.memberData.getAddressTypeStore()
					},
					{
						fieldLabel:i18n('i18n.MemberCustEditView.ifThePrimaryAddress'),
						name:'isMainAddress',
						xtype:'dpcombobox',
						queryMode:'local',
			            forceSelection:true,
						displayField:'name',
						valueField:'value',
						store:me.memberData.getBooleanStore(),
						allowBlank:false,
						blankText:i18n('i18n.MemberCustEditView.pleaseChooseWhetherNotThePrimaryAddress')
					},
			       {xtype:'fieldcontainer',
			    	layout:{
			    		type:'hbox'
			    	},
			    	defaults:{
						enableKeyEvents:true
				    },
			    	colspan:2,
			    	width:388,
			    	items:[{xtype:'displayfield',value:i18n('i18n.MemberCustEditView.timeHobbyStart'),width:75},
			    	       {
			    	           xtype: 'dpdtimefield',
			    	           name: 'startTime',
			    	           flex:1,
			    	           increment: 60,
			    	           format:'H 点'	
			    	       },
			    	       {xtype:'displayfield',value:i18n('i18n.MemberCustEditView.timeHobbyEnd'),width:75},
			    	       {
			    	           xtype: 'dpdtimefield',
			    	           name: 'endTime',
			    	           flex:1,
			    	           increment: 60,
			    	           format:'H 点'
			    	       }]},
			       {fieldLabel:i18n('i18n.MemberCustEditView.invoiceRequirements'),
			    	name:'billRequest',
				    xtype:'dpcombobox',
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					store:me.memberData.getBillRequireStore()},
			       {fieldLabel:i18n('i18n.MemberCustEditView.parkFee'),
						name:'hasStopCost',
						xtype:'dpcombobox',
						queryMode:'local',
			            forceSelection:true,
						displayField:'name',
						valueField:'value',
						store:me.memberData.getBooleanStore()
					}, {fieldLabel:i18n('i18n.MemberCustEditView.payWay'),
			    	name:'payType',
				    xtype:'dpcombobox',
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code',
					store:me.memberData.getPayWayStore()},
			       {fieldLabel:i18n('i18n.MemberCustEditView.deliveryUpstairs'),
			    	name:'isSendToFloor',
			    	xtype:'dpcombobox',
					queryMode:'local',
		            forceSelection:true,
					displayField:'name',
					valueField:'value',
					store:me.memberData.getBooleanStore()},
			       {fieldLabel:i18n('i18n.MemberCustEditView.ortherRequirements'),colspan:3,width:582,name:'otherNeed',maxLength:10,	
					emptyText:i18n('i18n.MemberCustEditView.m_max10')},
			       //接送货地址id
			       {name:'shuttleAddress',hidden:true},
			       {name:'shuttleAddressId',hidden:true}]
		}];
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.MemberCustEditView.ensure'),
			disabled:('view' == me.viewStatus)?true:false
		},{
			xtype:'button',
			text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
			handler:function(){
				me.close();
			}
		}];
	},
	//锁定偏好基本信息
	lockAllComponents:function(){
		var me = this;
		var addressHobby = me.down('form').getForm();
		addressHobby.getFields().each(function(field){
			field.setReadOnly(true);
		});
	}
});

/**
*账号编辑界面
*/
Ext.define('MemberAccountEditWin',{
	extend:'PopWindow',
	layout:'fit',
	items:null,
	fbar:null,
	title:i18n('i18n.MemberCustEditView.memberAccountEditUI'),
	width:700,
	height:230,
	memberData:null,
	accountRecord:null,    //AccountModel类型的record
	viewStatus:null,       //new表示新增，update表示修改，view表示查看
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		me.fbar = me.getFbar();
		this.callParent();
		me.memberData.getBankNameStore().load();
		//加载数据
		if(me.accountRecord != null){
			//支行名称特殊处理 先把查询结果的 银行和支行名称加入到 对应combo的store中
			var bankModel = Ext.create('BankSearchConditionModel');
			bankModel.set('accountBank',me.accountRecord.get('bank'));//银行名称
			bankModel.set('accountBankId',me.accountRecord.get('bankId'));
			bankModel.set('accountBranch',me.accountRecord.get('subBankname'));//支行名称
			bankModel.set('accountBranchId',me.accountRecord.get('subBanknameId'));
			me.memberData.getBankComboxStore().add(bankModel);
			
			me.down('form').loadRecord(me.accountRecord);
			//加载财务联系人信息
			if(DpUtil.isEmpty(me.down('form').getForm().findField('name').getValue()) &&
			   !DpUtil.isEmpty(me.accountRecord.get('financeLinkman'))){
				var linkmanStore = me.memberData.getContactStore();
				var isFind = false;
				var selectedLinkman = null;
				for(var  i = 0;i < linkmanStore.getCount() && !isFind; i++){
					if(linkmanStore.getAt(i).get('id') == me.accountRecord.get('financeLinkmanId')){
						isFind = true;
						selectedLinkman = linkmanStore.getAt(i);
					}
				}
				me.down('form').getForm().findField('name').select(selectedLinkman);
			}
			if(me.viewStatus == 'new'){
				me.down('form').getForm().reset();
				me.down('form').getForm().findField('id').setValue(me.accountRecord.get('id'));
			}
			//查看状态则锁定所有组件
			if(me.viewStatus == 'view'){
				me.lockAllComponents();
			}
		}
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'noborderformpanel',
			layout:{
				type:'table',
				columns:3
			},
			defaultType:'dptextfield',
			defaults:{
				labelWidth:70,
				width:210,
				enableKeyEvents:true,
				allowBlank:false
		    },
			items:[{
					xtype:'queryCombox',
					fieldLabel:i18n('i18n.MemberCustEditView.branchBankName'),
					name:'subBanknameId',
					blankText:i18n('i18n.MemberCustEditView.branchBankNameAlert'),
					subBankWindow:null,   //弹出的查询窗口
					store:me.memberData.getBankComboxStore(),
					displayField:'accountBranch',
					valueField:'accountBranchId',
					queryMode:'local',
					forceSelection:true,
					queryDelay:2000,
					listeners:{
						'expand':function(subBankCombox){
							if(subBankCombox.subBankWindow == null){
								subBankCombox.subBankWindow = Ext.create('BankSearchUI',{'memberData':me.memberData,'parent':subBankCombox});
							}else{
								 //清空查询界面
								 subBankCombox.subBankWindow.searchConditionPanel.getForm().reset();
								 me.memberData.getBankInfoStore().removeAll();
							}
							subBankCombox.subBankWindow.show();
							var position  = subBankCombox.getPosition();
							position[1] = position[1]+22;
							subBankCombox.subBankWindow.setPosition(position);
						},
						'change':function(subBankCombox){
							if(DpUtil.isEmpty(subBankCombox.getValue())){
								subBankCombox.setValue(null);
								//清空开户省市、以及银行名称
								var basicForm = me.down('form').getForm();
								basicForm.findField('bankProvinceId').setValue(null);
								basicForm.findField('bankCityId').setValue(null);
								basicForm.findField('bankId').setValue(null);
							}
						}
					},
					//支行信息查询页面查询结果赋值
					setValueComeback:function(backRecord){
						me.memberData.getBankComboxStore().add(backRecord);

						var basicForm = this.up('form').getForm();
						//设置支行名称
						this.setValue(backRecord.get('accountBranchId'));
						basicForm.findField('subBankname').setValue(backRecord.get('accountBranch'));
						
						//设置银行名称
						basicForm.findField('bankId').setValue(backRecord.get('accountBankId'));
						basicForm.findField('bank').setValue(backRecord.get('accountBank'));
						
						//设置省份、城市
						basicForm.findField('bankProvinceName').setValue(backRecord.get('province'));
						basicForm.findField('bankCityName').setValue(backRecord.get('city'));
						basicForm.findField('bankProvinceId').setValue(backRecord.get('provinceId'));
						basicForm.findField('bankCityId').setValue(backRecord.get('cityId'));
					}
			   },
			   {
				xtype:'fieldcontainer',
				layout:{
					type:'hbox'
				},
				defaults:{
					xtype:'dptextfield',
					hideLabel:true,
					allowBlank:false,
					readOnly:true
				},
				items:[{xtype:'displayfield',value:i18n('i18n.MemberCustEditView.accountProvinceCityWar'),width:75},
						{name:'bankProvinceName',
						blankText:i18n('i18n.MemberCustEditView.openProvinceAlert'),
						flex:1},
				       {name:'bankCityName',
						blankText:i18n('i18n.MemberCustEditView.openCityAlert'),
						flex:1
						}]
			},{
				xtype:'dpcombobox',
				fieldLabel:i18n('i18n.MemberCustEditView.bankName'),
				name:'bankId',
				blankText:i18n('i18n.MemberCustEditView.pleaseInputBank'),
				hideTrigger:true,
				store:me.memberData.getBankComboxStore(),
				displayField:'accountBank',
				valueField:'accountBankId',
				queryMode:'local',
				readOnly:true
			},{
				fieldLabel:i18n('i18n.MemberCustEditView.whetherTheDefaultAccount'),
				name:'isdefaultaccount',
				blankText:i18n('i18n.MemberCustEditView.isAcquiesceAccountAlert'),
				xtype:'dpcombobox',
				queryMode:'local',
	            forceSelection:true,
				displayField:'name',
				valueField:'value',
				store:me.memberData.getBooleanStore()
			},{
				fieldLabel:i18n('i18n.MemberCustEditView.accountNo'),
				name:'bankAccount',
				regex : /^\d{0,}$/,
				maxLength:80,
				blankText:i18n('i18n.MemberCustEditView.accountNoAlert')
			},{
				fieldLabel:i18n('i18n.MemberCustEditView.nameOfAccountHolder'),
				name:'countName',
				maxLength:80,
				maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80),
				blankText:i18n('i18n.MemberCustEditView.openNameAlert')
			},{
				fieldLabel:i18n('i18n.MemberCustEditView.CRAccount'),
				name:'relation',
				maxLength:50,
				maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',50),
				blankText:i18n('i18n.MemberCustEditView.CRAccountAlert')
			},{
				fieldLabel:i18n('i18n.MemberCustEditView.accountNature'),
				name:'accountNature',
				blankText:i18n('i18n.MemberCustEditView.accountNatureAlert'),
				xtype:'dpcombobox',
				queryMode:'local',
	            forceSelection:true,
				displayField:'codeDesc',
				valueField:'code',
				store:me.memberData.getAccountNatureStore()
			},{
				fieldLabel:i18n('i18n.MemberCustEditView.accountPurpose'),
				name:'accountUse',
				xtype:'dpcombobox',
				queryMode:'local',
	            forceSelection:true,
	            displayField:'codeDesc',
				valueField:'code',
				store:me.memberData.getAccountUseStore(),
				blankText:i18n('i18n.MemberCustEditView.accountUseAlert')
			},{
				fieldLabel:i18n('i18n.MemberCustEditView.financeContact'),
				name:'name',
				blankText:i18n('i18n.MemberCustEditView.financeContactAlert'),
				xtype:'dpcombobox',
	            displayField:'name',
				valueField:'id',
				queryMode:'local',
				store:me.memberData.getContactStore()
			},{
				fieldLabel:i18n('i18n.MemberCustEditView.contactPhone'),
				name:'linkManMobile',
				blankText:i18n('i18n.MemberCustEditView.contactPhoneAlert'),
				readOnly:true,
				allowBlank:true
			},{
				fieldLabel:i18n('i18n.MemberCustEditView.contactTel'),
				name:'linkManPhone',
				blankText:i18n('i18n.MemberCustEditView.contactTelAlert'),
				readOnly:true,
				allowBlank:true
			},{
				name:'subBankname',
				hidden:true              //设置隐藏域，保存支行名称
			},{
				name:'bank',
				hidden:true        //设置隐藏域，保存开户行名称
			},{
				name:'financeLinkmanId',
				allowBlank:true,//新增 会员账号时 没有财务联系人id
				hidden:true               //设置隐藏域，保存财务联系人id
			},{
				name:'financeLinkman',
				hidden:true               //设置隐藏域，保存财务联系人姓名
			},{
				name:'bankProvinceId',
				hidden:true               //设置隐藏域，保存bankProvinceId 银行开户省
			},{
				name:'bankCityId',
				hidden:true               //设置隐藏域，保存bankProvinceId 银行开户省name
			},{
				name:'id',
				allowBlank:true,		//新增 会员账号时 没有财务联系人id
				hidden:true               //设置隐藏域，账户id
			}]
		}];
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.MemberCustEditView.ensure'),
			disabled : ('view' == me.viewStatus) ? true : false,
			scope:me,
			handler:me.commitMemberAccount
		},{
			xtype:'button',
			text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
			handler:function(){
				me.close();
			}
		}];
	},
	//提交时校验会员账号信息
	commitMemberAccount:function(){},
	//选择财务联系人填充对应手机，固话
	selectLinkmanEvent:function(combox,records){
		if(records.length > 0){
			var form = combox.up('form').getForm();
			var mobile = form.findField('linkManMobile');
			mobile.setValue(records[0].get('mobilePhone'));
			
			var phone = form.findField('linkManPhone');
			phone.setValue(records[0].get('telPhone'));
			
			var linkmanName = form.findField('financeLinkman');
			linkmanName.setValue(records[0].get('name'));
			var linkmanId = form.findField('financeLinkmanId');
			linkmanId.setValue(records[0].get('id'));
		}
	},
	//锁定账号基本信息
	lockAllComponents:function(){
		var me = this;
		var accountForm = me.down('form').getForm();
		accountForm.getFields().each(function(field){
			field.setReadOnly(true);
		});
	}
});

/**
 * 支行查询界面
 */
Ext.define('BankSearchUI',{
	extend:'PopWindow',
	items:null,
	searchConditionPanel:null, //查询条件面板
	buttonPanel:null, //按钮面板
	searchResultPanel:null,  //查询结果面板
	title:i18n('i18n.MemberCustEditView.sub-branchInformationQueryInterface'),
	layout:{
		type:'vbox',
		align:'stretch'
	},
	width:650,
	height:400,
	searchBankRecord:null, //查询银行的record
	memberData:null,
	parent:null,       //父控件
    listeners:{
    	beforeclose:function(bankSearchWin){
    		bankSearchWin.hide();
    		return false;
    	}
    },
	initComponent:function(){
		var me = this;
		me.searchConditionPanel = Ext.create('BankSearchConditionPanel',{'parent':me,'memberData':me.memberData});
		me.buttonPanel =  Ext.create('BankButtonPanel',{'parent':me,'memberData':me.memberData});
		me.searchResultPanel =  Ext.create('BankSearchResultGrid',{'memberData':me.memberData,'parent':me.parent});
		
		me.items = [{
			xtype:'basicpanel',
			items:[me.searchConditionPanel],
			height:50
		},
		me.buttonPanel,
		{
			xtype:'basicpanel',
			items:[me.searchResultPanel],
			flex:1
		}];
		this.callParent();
		
		//初始化数据
		me.searchBankRecord = Ext.create('BankSearchConditionModel');
		me.searchConditionPanel.getForm().loadRecord(me.searchBankRecord);
	}
});


/**
 * 支行查询条件面板
 */
Ext.define('BankSearchConditionPanel',{
	extend:'SearchFormPanel',
	memberData:null,
	layout:{
		type:'table',
		columns:4
	},
	items:null,
	defaultType:'dptextfield',
	defaults:{
		labelWidth : 60,
		width : 150
	},
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
		me.memberData.getBankNameStore().load();
	},
	getItems:function(){
		var me = this;
		return [{
				xtype:'fieldcontainer',
				layout:{
					type:'hbox'
				},
				defaults:{
					xtype:'dpcombo',
					hideLabel:true
				},
				width : 235,
				items:[{xtype:'displayfield',value:i18n('i18n.MemberCustEditView.accountProvinceCityWar'),width:75},
						{name:'provinceId',
						blankText:i18n('i18n.MemberCustEditView.openProvinceAlert'),
						store:me.memberData.getBankProvinceStore(),
						displayField:'name',
						valueField:'id',
						flex:1,
						queryMode:'local',
						listeners : {
							select : function(combo,records) {
								if(records.length > 0){
									var form = combo.up('form').getForm();
									form.findField('cityId').setValue();
									me.memberData.getBankCityStore().load({
										params : {
											bankprovince : records[0].get('id')
										}
									});
								}
							}
						}},
				       {name:'cityId',
						blankText:i18n('i18n.MemberCustEditView.openCityAlert'),
						store:me.memberData.getBankCityStore(),
						displayField:'name',
						valueField:'id',
						queryMode:'local',
	            		forceSelection:true,
						flex:1,
						listConfig: {
						    loadMask:false
						}
						}]
			},{
				xtype:'dpcombo',
				name:'accountBank',
				fieldLabel:i18n('i18n.MemberCustEditView.bankName'),
				store:me.memberData.getBankNameStore(),
				displayField:'name',
				valueField:'name',
				allowBlank:false,
				flex:1,
				blankText:i18n('i18n.MemberCustEditView.pleaseInputBank'),
				editable : false
			},
		            {fieldLabel:i18n('i18n.MemberCustEditView.branchBankName'),name:'accountBranch'}
		            ];
	}
});

/**
 * 支行查询按钮面板
 */
Ext.define('BankButtonPanel',{
	extend:'NormalButtonPanel',
	items:null,
	parent:null,   //父窗体 
	memberData:null,   //会员data层接口
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'middlebuttonpanel'
		},{
			xtype:'rightbuttonpanel',
			items:[{
					xtype:'button',
					text:i18n('i18n.PotentialCustManagerView.search'),
					scope:me,
					handler:me.searchBank
				},{
					xtype:'button',
					text:i18n('i18n.MemberCustEditView.reset'),
					scope:me,
					handler:me.resetCondition
				}]
		}];
	},
	//查询支行信息
	searchBank:function(){
		var me = this;
		//更新查询条件
		me.parent.searchConditionPanel.getForm().updateRecord(me.parent.searchBankRecord);
		// 请检查必填项是否填写正确！
		if(!me.parent.searchConditionPanel.getForm().isValid()){
			MessageUtil.showErrorMes(i18n('i18n.MemberCustEditView.accountFormValid'));
			return;
		}
		var params = {};
		params.bankView = me.parent.searchBankRecord.data;
		
		var searchSuccessFn = function(result){
			var bankView = result.bankViewList;
			if(bankView != null && bankView.length > 0){
				me.memberData.getBankInfoStore().loadData(bankView);
			}else{
				MessageUtil.showMessage(i18n('i18n.MemberCustEditView.relevantBankInformationNotFound'));
			}
		};
		
		var failSuccessFn = function(result){
			if(!DpUtil.isEmpty(result.message)){
				MessageUtil.showMessage(result.message);
			}else{
				MessageUtil.showMessage(i18n('i18n.MemberCustEditView.serverExceptionWar'));
			}
		}
		
		me.memberData.searchBankInfo(params,searchSuccessFn,failSuccessFn);
	},
	//处理 支行所在省市
	manageModel4BankCity:function(searchBankModel){
		var me = this;
		if(!Ext.isEmpty(searchBankModel)){
			var cityId = searchBankModel.get('cityId');
			if(!Ext.isEmpty(cityId)){
				var pca = cityId.split(i18n('i18n.PotentialCustManagerView.searchEndTime'));
				if(pca>1){
					searchBankModel.set('cityId',pca[1]);
					searchBankModel.set('provinceId',pca[0]);
				}
			}
			return searchBankModel;
		}
		return searchBankModel;
	},
	//重置查询条件
	resetCondition:function(){
		var me = this;
		me.parent.searchConditionPanel.getForm().reset();
	}
});


/**
 * 支行查询结果grid面板
 */
Ext.define('BankSearchResultGrid',{
	extend:'PopupGridPanel',
	memberData:null,
	columns:[{text:i18n('i18n.MemberCustEditView.belongProvince'),dataIndex:'province'},
	         {text:i18n('i18n.MemberCustEditView.belongCity'),dataIndex:'city'},
	         {text:i18n('i18n.MemberCustEditView.bankAccount'),dataIndex:'accountBank'},
	         {text:i18n('i18n.MemberCustEditView.branchBankName'),dataIndex:'accountBranch',width:250}],
	store:null,
	parent:null,
	initComponent:function(){
		var me = this;
		me.store = me.memberData.getBankInfoStore();
		me.listeners = {
				scope:me,
				itemdblclick:me.doubleClickEvent
		};
		this.callParent();
	},
	//双击grid填充到父页面上
	doubleClickEvent:function(view,record){
		var me = this;
		//查询结果回传
		me.parent.setValueComeback(record);
		me.parent.subBankWindow.hide();
	}
});


/**
* 接送货地址编辑界面
*/
Ext.define('MemberAddressEditwin',{
	extend:'PopWindow',
	title:i18n('i18n.MemberCustEditView.shuttleAddressEditUI'),
	items:null,
	fbar:null,
	width:500,
	height:180,
	layout:'fit',
	memberData:null,
	shuttleAddressRecord:null,    //ShuttleAddressModel类型的record
	viewStatus:null,              //new为新增，update为修改,view表示查看
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		me.fbar = me.getFbar();
		this.callParent();
		
		//加载数据
		if(me.shuttleAddressRecord != null){
			me.down('form').getForm().loadRecord(me.shuttleAddressRecord);
			if(me.viewStatus == 'new'){
				me.down('form').getForm().reset();
			}else if(me.viewStatus == 'update'){
				me.down('form').getForm().findField('address').setValue(me.shuttleAddressRecord.get('address'));
			}else if(me.viewStatus == 'view'){
				me.lockAllComponents();
			}
		}
	},
	getItems:function(){
		var me = this;
		var operateType = null;
		if(me.viewStatus == 'update'){
			operateType = 'UPDATE';
		}else if(me.viewStatus == 'view'){
			operateType = 'VIEW';
		}
		return [{
			xtype:'noborderformpanel',
			layout:{
				type:'table',
				columns:3
			},
			defaultType:'dptextfield',
			defaults:{
				labelWidth:70,
				width:120,
				enableKeyEvents:true
		    },
			items:[{
				fieldLabel:i18n('i18n.MemberCustEditView.addressType'),
				name:'addressType',
				colspan:3,
				width:310,
				xtype:'dpcombobox',
				queryMode:'local',
	            forceSelection:true,
	            displayField:'codeDesc',
				valueField:'code',
				allowBlank:false,
				store:me.memberData.getAddressTypeStore(),
				blankText:i18n('i18n.MemberCustEditView.addressTypeAlert')
			},{
				fieldLabel:i18n('i18n.PotentialCustEditView.address'),
				xtype:'areaaddresscombox',
				name:'area',
				colspan:3,
				width:310,
				operateType:operateType,
				selectedValue:me.shuttleAddressRecord.get('area'),
				tabPanel:['commonPanel','provincePanel','cityPanel','areaPanel'],
				blankText:i18n('i18n.MemberCustEditView.pleaseSelectProviceCityArea'),
				emptyText:i18n('i18n.MemberCustEditView.pleaseSelectProviceCityArea'),
				allowBlank:false,
				listeners:{
					change:function(combobox,newValue,oldValue){
						if(!DpUtil.isEmpty(newValue)){
								var array = combobox.getRawValue().split(i18n('i18n.PotentialCustManagerView.searchEndTime'));	
							if(array.length == 3){
								me.down('form').getForm().findField('provinceName').setValue(array[0]);
								me.down('form').getForm().findField('cityName').setValue(array[1]);
								me.down('form').getForm().findField('areaName').setValue(array[2]);
								me.down('form').getForm().findField('address').setValue(array[0]+'-'+array[1]+'-'+array[2]+'-');
							}
						}
					}
				}
			   },
			{
				xtype:'displayfield',
				width:75
			},{
				hideLabel:true,
				colspan:2,
				width:375,
				name:'address',
				allowBlank:false,
				maxLength:300,
				maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',300),
				blankText:i18n('i18n.MemberCustEditView.detailAddressAlert'),
				emptyText:i18n('i18n.MemberCustEditView.detailAddressAlert')
			},{
				name:'provinceId',
				hidden:true
			},{
				name:'cityId',
				hidden:true
			},{
				name:'provinceName',
				hidden:true
			},{
				name:'cityName',
				hidden:true
			},{
				name:'areaName',
				hidden:true
			}]
		}];
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.MemberCustEditView.ensure'),
			disabled : ('view' == me.viewStatus) ? true : false
		},{
			xtype:'button',
			text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
			handler:function(){
				me.close();
			}
		}];
	},
	//锁定接送货地址基本信息
	lockAllComponents:function(){
		var me = this;
		var addressForm = me.down('form').getForm();
		addressForm.getFields().each(function(field){
			field.setReadOnly(true);
		});
	}
});