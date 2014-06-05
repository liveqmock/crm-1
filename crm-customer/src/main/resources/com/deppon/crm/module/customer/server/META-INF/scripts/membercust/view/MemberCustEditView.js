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
//var memberCustControl = Ext.create('MemberCustData');
(function() {//汉字索引
	var getFirstCharUtiljs = "../scripts/common/common/GetFirstCharUtil.js";
	document.write('<script type="text/javascript" src="' + getFirstCharUtiljs + '"></script>');
})();
MemberCustEditView = function(){};
//因为创建特殊会员，选择部门的id，设一个变量
var isHK = '0';
//提交联系人信息到后台转换联系人类型
MemberCustEditView.changeLinkmanTypeForSubmit = function(contactArray,contactRecords){
	if(contactRecords != null){
		for(var i = 0; i < contactRecords.length;i++){
			var linkmanTypeItem = contactRecords[i].get('linkmanType');
			if(!DpUtil.isEmpty(linkmanTypeItem)){
				linkmanTypeItem = linkmanTypeItem.replaceAll('true','1').replaceAll('false','0');
				contactRecords[i].data.linkmanType = linkmanTypeItem;
				contactArray.push(contactRecords[i].data);
			}
		}
	}
};
//全局变量，[{0}税务登记号,{1}身份证号,{2}手机号码,{3}账号联系人是否被修改,{4}地址偏好地址是否被修改,
//			{5}客户类型为个人时主联系人身份证号不可为空,{6}联系人固话和联系人名称校验结果, 
//			{7}联系人固话和联系人名称校验结果数据,{8}手机号码校验结果,{9}证件号码合法性,{10}证件号码合法性结果集] 
//实时校验结果 "success"  请求后台结果,message请求后台后返回消息
//message[1],message[2] 格式 {contactId:'',success:true,message:''}
var RealTimeVerifyResult = {success:[true,true,true,true,true,true,true,true,true,true,true],
							message:['',[],[],'','','','',[],'','',[]]};
var Sequence = 1;//标记临时 会员联系人 id:"X"+序列
var SequenceAccount = 1;//标记临时 会员地址 id:"X"+序列
var SequenceAddress = 1;//标记临时 会员账户 id:"X"+序列
//新增联系人 Id 序列，为了和后台区分 增加"P"开头区别
MemberCustEditView.getSequence = function(){
	return 'X'+Sequence++;
};     
//新增账户 Id 序列，为了和后台区分 增加"P"开头区别
MemberCustEditView.getSequenceAccount = function(){
	return 'P'+SequenceAccount++;
};     
//新增地址Id 序列，为了和后台区分 增加"P"开头区别
MemberCustEditView.getSequenceAddress = function(){
	return 'A'+SequenceAddress++;
};
function returnNumData(nameArr,linkNum){
	for(var i in nameArr){
		var linkNum = linkNum.replace(/\/|-/g,'');
		nameArr[i] = {'number':nameArr[i]+linkNum,'number':nameArr[i]+linkNum};
	}
	return nameArr;
};
MemberCustEditView.validateIdNumberIsLegal = function(contactRecord,list){
	var cardTypeCon = Ext.getCmp('cardTypeCon_id').getValue();
	var idCardNum = Ext.getCmp('contactIdCard').getValue();
	var isBothNotNull = !Ext.isEmpty(idCardNum)&&!Ext.isEmpty(cardTypeCon);
	if(idCardNum == contactRecord.get('idCard') && cardTypeCon == contactRecord.get('cardTypeCon')){
		//证件号码未修改则把身份证号验证结果清空
		RealTimeVerifyResult.success[5] = true;
		RealTimeVerifyResult.message[5] = '';
		RealTimeVerifyResult.success[9] = true;
		RealTimeVerifyResult.message[9] = '';
		MemberCustEditView.addIdCardContact_cardNum(RealTimeVerifyResult.message[10],contactRecord.get('id'),true,'');
	}
//	if(isBothNotNull){
//		RealTimeVerifyResult.success[5] = true;
//		RealTimeVerifyResult.message[5] = '';
//	}
	if(!((isBothNotNull && idCardNum != contactRecord.get('idCard'))||
	(isBothNotNull && cardTypeCon  != contactRecord.get('cardTypeCon')))){
		return;
	}
	var validateSuccessFn = function(result){
		//不存在 "exist" 为true
		if(result.exist){
			RealTimeVerifyResult.success[9] = true;
			RealTimeVerifyResult.message[9] = '';
			MemberCustEditView.addIdCardContact_cardNum(RealTimeVerifyResult.message[10],contactRecord.get('id'),true,'');
		}else{
			RealTimeVerifyResult.success[9] = false;
			RealTimeVerifyResult.message[9] = '证件号码已存在！';
			MemberCustEditView.addIdCardContact_cardNum(RealTimeVerifyResult.message[10],contactRecord.get('id'),false,'证件号码已存在！');
			return;
		}
	};
	var validateFailFn = function(result){
		if(DpUtil.isEmpty(result.message)){
			result.message = '证件号码不规范，请核实！';
		}
		RealTimeVerifyResult.success[9] = false;
		RealTimeVerifyResult.message[9] = result.message;
		MemberCustEditView.addIdCardContact_cardNum(RealTimeVerifyResult.message[10],contactRecord.get('id'),false,result.message);
		return;
	};
	var params = {};
	params.idNumber = idCardNum;
	params.cardType = cardTypeCon;
	memberCustControl.validateIdNumberIsLegal(params,validateSuccessFn,validateFailFn);
};
//全局变量 增加 实时校验身份证号 是否存在RealTimeVerifyResult.message[1];
MemberCustEditView.addIdCardContact_cardNum = function(list,contactId,success,message){
	if(list.length > 0){
		var flag = true;//标志 所操作联系人的 身份证号是否已被修改，没被修改则为 true
		for(var i = 0; i < list.length;i++){
			if(!Ext.isEmpty(contactId)
			&& contactId == list[i].contactId){
				list[i].success = success;
				list[i].message = message;
				flag = false;
			}
		}
		if(flag){
			list.push({contactId:contactId,success:success,message:message});
		}
	}else{
		list.push({contactId:contactId,success:success,message:message});
	}
};
//实时校验税务登记号是否重复
MemberCustEditView.checkTaxNumber = function(idCardNum,cardTypeCon){
	var validateSuccessFn = function(result){
		//不存在 "exist" 为true
		if(result.exist){
			RealTimeVerifyResult.success[0] = true;
			RealTimeVerifyResult.message[0] = '';
		}else{
			RealTimeVerifyResult.success[0] = false;
			RealTimeVerifyResult.message[0] = i18n('i18n.MemberCustEditView.m_taxIsExsit');
			MessageUtil.showMessage(i18n('i18n.MemberCustEditView.m_taxIsExsit'));
			return;
		}
		//				RealTimeVerifyResult.success[0] = true;
	};
	var validateFailFn = function(result){
		if(DpUtil.isEmpty(result.message)){
			result.message = i18n('i18n.MemberCustEditView.anExceptionBackgroundAppearsWar');
			RealTimeVerifyResult.success[0] = false;
			RealTimeVerifyResult.message[0] = result.message;
		}
		RealTimeVerifyResult.success[0] = false;
		RealTimeVerifyResult.message[0] = result.message;
		MessageUtil.showMessage(result.message);
		return;
	};
	var params = {};
	params.taxNum = idCardNum;
	params.cardType = cardTypeCon;
	memberCustControl.validateTaxNumberIsExist(params,validateSuccessFn,validateFailFn);
}
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
//          			'CUST_POTENTIAL',
          			'COOPERATION_INTENTION',
//          			'CARGO_POTENTIAL',
          			// 上一年公司规模
          			'FIRM_SIZE',
          			// 信用等级
          			'CREDIT_GRADE',
          			// 地址类型
          			'ADDRESS_TYPE',
          			// 联系人类型 已取消
          			//货量强力
          			'CARGO_POTENTIAL',
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
//通过联系人身份证号修改联系人生日
MemberCustEditView.changeBirthDay = function(){
		//联系人身份证号
		var idCard=Ext.getCmp('contactIdCard').getValue();
		//生日
		var birthday=Ext.getCmp('contactBrithday');
		if(idCard.length==15){
			var yeara = idCard.substring(4,8);
			var montha = idCard.substring(8,10);
			var daya = idCard.substring(10,12);
			var date = new Date();
			date.setYear(yeara);
			date.setMonth(montha);
			date.setDate(daya);
			birthday.setValue(date);
		}else if(idCard.length==18){
			var yeara = idCard.substring(6,10);
			var montha = idCard.substring(10,12);
			var daya = idCard.substring(12,14);
			var date = new Date();
			date.setYear(yeara);
			if(montha<0 || montha>12){
				montha = 12;
			}
			date.setMonth(montha-1);
			if(daya<0 || daya>31){
				daya = 31;
			}
			date.setDate(daya);
			birthday.setValue(date);
		}
};
Ext.define('MemberCustEditWindow',{
	extend:'PopWindow',
	title:i18n('i18n.MemberCustEditView.memberInfoEditUI'),
	layout:'fit',
	width:825,
	height:500,
	member:null,
	currentCity:null,
	updateMemberParent:null,//会员管理，点击维护会员信息，传入的父类
    scatterUpgradeRecord:null,
    viewStatus:null,
    channelType:null,
    custStatus:null,									//会员状态：0正常，1审批中，2作废
	items:null,
	memberPage:null,
    initComponent:function(){
    	var me = this;
    	//初始化store
    	memberCustControl.clearStore();
    	me.items = [Ext.create('MemberCustEditPanel',{'member':me.member,
    													'currentCity':me.currentCity,
													  'scatterUpgradeRecord':me.scatterUpgradeRecord,
													  'viewStatus':me.viewStatus,
													  'custStatus':me.custStatus,
													  'channelType':me.channelType,
													  'memberPage':me.memberPage,
													  'updateMemberParent':me.updateMemberParent,
													  'parentWindow':me})];
    	me.on('beforeclose',me.beforeCloseEvent);
    	this.callParent();
    	//已作废客户 并且是收银员账号操作则 仅可以修改 会员账号信息
    	if('2' == me.custStatus && 'view' == me.viewStatus){
    		var aBtn = isPermission('/customer/accountRecognizedABtn.action');
    		var uBtn = isPermission('/customer/accountRecognizedUBtn.action');
    		var dBtn = isPermission('/customer/accountRecognizedDBtn.action');
    		var isP = aBtn||uBtn||dBtn;
    		if(!isP){return;}
    		Ext.getCmp('save_id').setDisabled(!isP);//保存按钮可用
    		Ext.getCmp('memberAccountTab_add_id').setDisabled(!aBtn);//保存按钮可用
    		Ext.getCmp('memberAccountTab_update_id').setDisabled(!uBtn);//保存按钮可用
    		Ext.getCmp('memberAccountTab_del_id').setDisabled(!dBtn);//保存按钮可用
    	}
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
    	var memberWin = Ext.getCmp('member_cityIdAreaTabWindow');
    	if(!Ext.isEmpty(memberWin)){
    		memberWin.close();
		}
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
	memberCreditInfo:null,
	updateMemberParent:null,	//会员管理，维护会员信息传入的父类
	memberPage:null,
	memberData:memberCustControl,
	currentCity:null,
	width:825,
	height:500,
	layout:{
        type:'vbox',
        align:'stretch'
    },
	items:null,
	fbar:null,
	member:null,           //对应后台Member对象的json字符串，如果正常创建和实时创建是由父页面传入
	channelType:'normal',      //创建会员渠道：'normal'表示从散客升降列表创建会员,'immediate'表示实时创建会员信息,'special'表示创建特殊会员,addPOtential手动创建潜客
	                           // 'workflow'表示从工作流界面而来
	viewStatus:null,       //new表示新增，update表示修改 view表示查看
	custStatus:null,       //2 作废状态
//	viewType:null,			//scatterUpgrade表示是散客升级
	scatterUpgradeRecord:null,  //散客升级记录
	parentWindow:null,          //父窗口
	initComponent:function(){
		var me = this;
		me.memberCustBasicInfo = Ext.create('MemberCustBasicInfoPanel',{'memberData':me.memberData,
																		'currentCity':me.currentCity,
																		'memberBasicRecord':me.getMemberBasicRecord(),
																		'channelType':me.channelType,
																		'viewStatus':me.viewStatus});
		me.memberLinkmanInfo = Ext.create('MemberLinkmanTabPanel',{'memberData':me.memberData,
																	'parent':me,'viewStatus':me.viewStatus,
																   'memberLinkmanData':me.getMemberLinkmanData()});
		//信用信息页签		
																  
		me.memberCreditInfo = Ext.create('Crm.customer.CreditInformation',{'viewStatus':me.viewStatus,'custId':(me.viewStatus!='view')?null:me.member.id});
																   
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
			    			height:400,
			    			items:[me.basicInfoTab,
			    			       me.memberLinkmanInfo,
			    			       me.memberCreditInfo,
			    			       me.memberAccountInfo,
			    			       me.memberAddressInfo,
			    			       me.memberCustLabelInfo,
			    			       me.memberCustInfo
			    			       ]
			    		}];
		}else{
			me.items = [ {
				xtype:'titleformpanel',
				height:340,
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
		
		//针对创建特殊会员不用显示“取消”按钮
		if(me.channelType == 'special' || me.channelType == 'immediate' || me.channelType == 'workflow' ){
			Ext.getCmp('cancel_id').setVisible(false);
			if(me.channelType == 'workflow'){
				Ext.getCmp('save_id').setVisible(false);
			}
		}
		
		//如果查看状态是view，锁定所有控件
		if(me.viewStatus == 'view'){
			me.lockAllComponents();
			Ext.getCmp('save_id').setDisabled(true);
		}
		
		//如果是从实时创建会员而来，锁定控件,保存按钮不可用
		if(me.channelType == 'immediate'){
			me.lockAllComponents();
			Ext.getCmp('save_id').setDisabled(true);
		}
		//散客升级可用【已作废】
		/*if(me.memberPage == 'scatterUpgrade'){
			me.lockAllComponents();
			Ext.getCmp('save_id').setDisabled(false);
		}*/
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.PotentialCustEditView.save'),
			id:'save_id',
			scope:me,
			handler:me.saveMemberCust
		},{
			xtype:'button',
			text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
			id:'cancel_id',
			handler:function(){
				if(me.parentWindow != null){
					me.parentWindow.close();
				}else{
					if(!Ext.isEmpty(window.parent)){
						if(!Ext.isEmpty(window.parent.Ext.getCmp('memberDetailIdForComp'))){
							window.parent.Ext.getCmp('memberDetailIdForComp').close();
						}
					}
				};
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
	//解除锁定
	unLockAllComponents:function(){
		var me = this;
		//解除锁定会员基本信息
		var memberBasicBasicForm = me.memberCustBasicInfo.getForm();
		memberBasicBasicForm.getFields().each(function(field){
			field.setReadOnly(false);
		});
		//实时创建会员，客户等级不可修改，是否特殊客户不可修改且默认值为i18n('i18n.ChangeContactAffiliatedRelationView.no')
		if(me.channelType == 'immediate'){
			me.memberCustBasicInfo.getForm().findField('degree').setReadOnly(true);
			me.memberCustBasicInfo.getForm().findField('isSpecial').setValue(false);
			me.memberCustBasicInfo.getForm().findField('isSpecial').setReadOnly(true);
			me.memberCustBasicInfo.getForm().findField('finOver').setReadOnly(true);
			me.memberCustBasicInfo.getForm().findField('isKeyCustomer').setReadOnly(true);
			me.memberCustBasicInfo.getForm().findField('custGroup').setReadOnly(true);
		}
		if(me.channelType != 'special'){
			me.memberCustBasicInfo.getForm().findField('deptId').setReadOnly(true);
		}
		//客户编码 永远 不可编辑 
		me.memberCustBasicInfo.getForm().findField('custNumber').setReadOnly(true);
		//解除锁定联系人按钮
		var linkButton = me.memberLinkmanInfo.down('button');
		while(linkButton != null){
			linkButton.setDisabled(false);
			linkButton = linkButton.next('button');
		}
		//解除锁定账号信息按钮
		var accountButton = me.memberAccountInfo.down('button');
		while(accountButton != null){
			var aBtn = isPermission('/customer/accountRecognizedABtn.action');
			var uBtn = isPermission('/customer/accountRecognizedUBtn.action');
			var dBtn = isPermission('/customer/accountRecognizedDBtn.action');
//			accountButton.setDisabled(false);
			Ext.getCmp('memberAccountTab_add_id').setDisabled(!aBtn);
			Ext.getCmp('memberAccountTab_update_id').setDisabled(!aBtn);
			Ext.getCmp('memberAccountTab_del_id').setDisabled(!aBtn);
			accountButton = accountButton.next('button');
		}
		//解除锁定接送货地址按钮
		var addressButton = me.memberAddressInfo.down('button');
		while(addressButton != null){
			addressButton.setDisabled(false);
			addressButton = addressButton.next('button');
		}
		me.unLockCustLabels();
	},
	//解锁客户标签
	unLockCustLabels:function(){
		var me = this;
		//解锁待选客户标签信息
		var waitCustLabelList = me.memberCustLabelInfo.items.items[0].items.items;
		for(var i=0;i<waitCustLabelList.length;i++){
			waitCustLabelList[i].setDisabled(false);
		}
		//解锁已选客户标签信息
		var selectedCustLabelList = me.memberCustLabelInfo.items.items[2].items.items;
		for(var i=0;i<selectedCustLabelList.length;i++){
			selectedCustLabelList[i].setDisabled(false);
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
		//加载联系人偏好地址
//		me.memberLinkmanInfo.loadContactPerfectAddress(memberLinkmanData);
		//加载账号信息
		me.memberAccountInfo.loadMemberAccountData(memberAccountData);
		//:加载tab5(固定客户)的数据
		me.memberCustInfo.loadCustInfoData(memberCustInfoData);
		//解除锁定控件
		me.unLockAllComponents();
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
	},
	//保存会员信息
	saveMemberCust:function(button){
		button.setDisabled(true);
		var me = this;
		//保存时判断 会员时候 有主联系人
		if (!me.validatMainContact()){
			button.setDisabled(false);
			MessageUtil.showMessage(i18n('i18n.member.MainContactCanExistOnlyOne'));
			return;
		}
		//判断客户省市传到后台的是否是id
		if(Ext.getCmp('member_cityId').getValue() == Ext.getCmp('member_cityId').getRawValue()){
			button.setDisabled(false);
			MessageUtil.showMessage(i18n('i18n.customer.m_reSlectCity'));
			return;
		}
		//TODO 校验新增的联系人 联系人编码是否有重复
		//TODO 校验新增的联系人 固话+联系人名称是否有重复
		//TODO 校验新增的联系人 手机号码是否有重复
		//保存时校验 个人客户主联系人的身份证号是否已存在
		var validateResult = me.validateMainContactIdNumber();
		if(!validateResult.success){
			button.setDisabled(false);
			MessageUtil.showMessage(validateResult.message);
			return;
		}
		//保存时校验客户联系人性别
		//固定客户的联系人性别不能为空
		var sexNullFlag = true;
		me.memberData.getContactStore().each(function(record){
			if(Ext.isEmpty(record.get('sex')) 
					&& 'RC_CUSTOMER'==Ext.getCmp('MemberEdit_custGroup_id').getValue()){
				button.setDisabled(false);
				MessageUtil.showMessage(i18n('i18n.MemberCustEditView.m_SexNotNull'));
				sexNullFlag = false;
			}
			
		});
		if(!sexNullFlag){
			return;
		}
		me.checkAddressIsModify();// 联系人偏好地址中 地址 是否已被修改
		me.checkContactIsModify();// 校验账号信息中联系人信息 是否已被修改
		//校验 试试校验结果的全局变量 是否全部通过检验
		var validateRealTimeVerifyResult = me.validateRealTimeVerifyResult( RealTimeVerifyResult.success,RealTimeVerifyResult.message);
		if(!validateRealTimeVerifyResult.success){
			button.setDisabled(false);
			MessageUtil.showMessage(validateRealTimeVerifyResult.message);
			return;
		}
		//校验会员信息
		var validateRs = me.validateAllMemberInfo();
		if(validateRs.success){
			if(me.viewStatus == 'new'){
				var memberInfo = me.assembleNewMemberData();
				memberInfo.member.custLabels = custLabels;
				var saveMemberSuccessFn = function(result){
					button.setDisabled(false);
					Ext.getCmp('member_cityId').setDisabled(false);
					var custNumber = result.member.custNumber;
					MessageUtil.showInfoMes(i18n('i18n.MemberCustEditView.saveSuccess') + custNumber + i18n('i18n.MemberCustEditView.saveRemember'));
					//针对创建特殊会员不用关闭窗口
					if(me.channelType == 'normal'){
						me.parentWindow.close();
						var store = null;
						try{
							//会员信息维护
							store = me.updateMemberParent.memberManagerResultGrid.store;
						}catch(e){
							//散客升级列表
							store = me.updateMemberParent.scatterUpgradeResultInfo.store;
						}
						if(store != null){
							store.load();
						}
					}else if(me.channelType == 'addPotential'){
						me.parentWindow.close();
					}else{
						//锁定所有控件和保存按钮
						me.lockAllComponents();
						button.setDisabled(true);
						//省市区控件不让可用
						Ext.getCmp('member_cityId').setDisabled(true);
					} 
				};
				var saveMemberFailFn = function(result){
					button.setDisabled(false);
					if(DpUtil.isEmpty(result.message)){
						result.message = i18n('i18n.PotentialCustEditView.saveFailed');
					}
					MessageUtil.showErrorMes(result.message);
				};
				//根据会员创建渠道保存会员信息
				if(me.channelType == 'special'){
					var saveSuccessFn = function(result){
						button.setDisabled(false);
//							var custNumber = result.member.custNumber;
						var workFlowNum = result.workFlowNum;
						if(!Ext.isEmpty(workFlowNum)){
							MessageUtil.showInfoMes(i18n('i18n.MemberCustEditView.saveSuccessWorkFlowNum') + workFlowNum + i18n('i18n.MemberCustEditView.beforeApprovalNotMembersModifyOperatingWar'));
							//锁定所有控件和保存按钮
							me.lockAllComponents();
							button.setDisabled(true);
						}
					};
						MessageUtil.showQuestionMes(i18n('i18n.contractEditView.createOrModfyMemberNotice'),function(e){
							if(e == 'yes'){
								me.memberData.saveSpecialMemberData(memberInfo,saveSuccessFn,saveMemberFailFn);
							}else{
								button.setDisabled(false);
							}
						},function(){
							button.setDisabled(false);
						});
				}else if(me.channelType == 'addPotential'){//新增潜客
					me.memberData.savePotentialData(memberInfo,saveMemberSuccessFn,saveMemberFailFn);
				}/*else if(me.memberPage == 'scatterUpgrade'){//散客升级【已作废】
					me.memberData.upgradeScatterData(memberInfo,saveMemberSuccessFn,saveMemberFailFn);
				}*/else{
					me.memberData.saveMemberData(memberInfo,saveMemberSuccessFn,saveMemberFailFn);
				}
			}else if(me.viewStatus == 'update'||('2' == me.custStatus && 'view' == me.viewStatus)){
				var alterMemberInfo = me.assembleAlterMemberData();
				var isTradeEmpty = false;
				var isSecondTradeEmpty = false;
				if(Ext.isEmpty(me.member.tradeId)||me.member.tradeId ==""){
					isTradeEmpty = true;
				}
				if(Ext.isEmpty(me.member.secondTrade)||me.member.secondTrade ==""){
					isSecondTradeEmpty = true;
				}
				var afterTradeID = me.memberCustBasicInfo.getForm().findField('tradeId').getValue();
				var afterSecondTrade = me.memberCustBasicInfo.getForm().findField('secondTrade').getValue();
				if(!isTradeEmpty&&(Ext.isEmpty(afterTradeID)||afterTradeID=="")){
					MessageUtil.showInfoMes(i18n('i18n.member.selectTrade'),function(){
						button.setDisabled(false);
					});
					return;
				}
				if(!isSecondTradeEmpty&&(Ext.isEmpty(afterSecondTrade)||afterSecondTrade=="")){
					MessageUtil.showInfoMes(i18n('i18n.member.selectSecondTrade'),function(){
						button.setDisabled(false);
					});
					return;
				}
				//修改时 把memeber对象传到后台
				var member = me.assembleNewMemberData().member;
				member.contactList = me.memberLinkmanInfo.collectLinkman4MemberSubmit();
				member.custLabels = custLabels;
				alterMemberInfo.member =  member;
				var alterMemberSuccessFn = function(result){
					button.setDisabled(false);
					var workFlowNum = result.workFlowNum;
					//散客升级
					if(me.memberPage == 'scatterUpgrade'){
						if(Ext.isEmpty(workFlowNum)){
							MessageUtil.showInfoMes(i18n('i18n.ScatterUpgradeView.scatterUpgradeSuccess'));
						}else{
							MessageUtil.showInfoMes(i18n('i18n.ScatterUpgradeView.existWorkflowNum')+workFlowNum+i18n('i18n.ScatterUpgradeView.becomeEffect'));
						}
					}else if(me.channelType == 'immediate'){
						if(Ext.isEmpty(workFlowNum)){
							MessageUtil.showInfoMes(i18n('i18n.ScatterUpgradeView.scatterUpgradeSuccess'));
						}else{
							MessageUtil.showInfoMes(i18n('i18n.ScatterUpgradeView.existWorkflowNum')+workFlowNum+i18n('i18n.ScatterUpgradeView.becomeEffect'));
						}
					}else if(Ext.isEmpty(workFlowNum)){
						MessageUtil.showInfoMes(i18n('i18n.MemberCustEditView.updateMemberSuccessWar'));
					}else{
						MessageUtil.showInfoMes(i18n('i18n.MemberCustEditView.updateMemberSuccessWorkFlowNum')+workFlowNum+i18n('i18n.MemberCustEditView.beforeApprovalNotMembersModifyOperatingWar'));
					}
					//针对创建特殊会员不用关闭窗口
					if(me.channelType == 'normal'){
						me.parentWindow.close();
						//散客升级的时候没有此store
						if(me.memberPage != 'scatterUpgrade'){
							me.updateMemberParent.memberManagerResultGrid.store.load();
						}
					}
					if(me.parentWindow){
						me.parentWindow.close();
						me.updateMemberParent.memberManagerResultGrid.store.load();
					}else{
						me.lockAllComponents();
						button.setDisabled(true);
					}
////						me.updateMemberParent.memberManagerResultGrid.store.load();
//					me.parentWindow.close();
//					me.updateMemberParent.memberManagerResultGrid.store.load();
					
				};
				var alterMemberFailFn = function(result){
					button.setDisabled(false);
					if(DpUtil.isEmpty(result.message != null)){
						result.message = i18n('i18n.MemberCustEditView.updateMemberFailureWar');
					}
					MessageUtil.showErrorMes(result.message);
				};
					MessageUtil.showQuestionMes(i18n('i18n.contractEditView.createOrModfyMemberNotice'),function(e){
						if(e == 'yes'){
							me.memberData.alterMemberData(alterMemberInfo,alterMemberSuccessFn,alterMemberFailFn);
						}else{
							button.setDisabled(false);
						}
					},function(){
							button.setDisabled(false);
					});
			}
			//清空 账号信息银行和支行store中 数据
			me.memberData.getBankComboxStore().removeAll();
		}else if(!DpUtil.isEmpty(validateRs.message)){
			button.setDisabled(false);
			MessageUtil.showMessage(validateRs.message);
		}
		
	},
	//TODO 校验新增的联系人 联系人编码是否有重复
	validateNewContactNum:function(){
		var me = this;
		var flag = true;
		var message = '';
		me.memberData.getContactStore().each(function(record){
			me.memberData.getContactStore().each(function(record){
				
			})
		});
		return {success:flag,message:message};
	},
	//TODO 校验新增的联系人 固话+联系人名称是否有重复
	validateNewContactTelAndName:function(){
		
	},
	//TODO 校验新增的联系人 手机号码是否有重复
	validateNewContactPhone:function(){
		
	},
	//保存时校验全局变量是否全部通过,通过校验 success为true，不通过为false
	validateRealTimeVerifyResult:function(RealTimeVerifySuccess,RealTimeVerifyMessage){
		var me = this;
		var flag = true;
		var message = '';
//		var RealTimeVerifySuccess = RealTimeVerifyResult.success;
//		var RealTimeVerifyMessage = RealTimeVerifyResult.message;
		for(var i = 0;i < RealTimeVerifySuccess.length;i++){
			if(i == 1){
				message = '';
				flag = true;
			}
			if(i == 2 || i == 7 || i == 10){
				//手机号，身份证号 返回值为list特殊处理
				var checkMobileOrIdCard = RealTimeVerifyMessage[i];
				for (var j = 0; j < checkMobileOrIdCard.length; j++){
					if(!checkMobileOrIdCard[j].success){
//						MessageUtil.showMessage(checkMobileOrIdCard[j].message);
//						return;
						message = checkMobileOrIdCard[j].message;
						flag = false;
						return {success:flag,message:message};
					}
				}
			}else{
				if(!RealTimeVerifySuccess[i]){
//					MessageUtil.showMessage(RealTimeVerifyMessage[i]);
//					return;
					message = RealTimeVerifyMessage[i];
					flag = false;
					return {success:flag,message:message};
				}
			}
		}
		return {success:flag,message:message};
	},
	//保存时校验身份证 结果,通过校验 success为true，不通过为false
	validateMainContactIdNumber:function(){
		var me = this;
		var flag = true;
		var message = '';
		var custType = me.memberCustBasicInfo.getForm().findField('custType').getValue();
		var custGroup = Ext.getCmp('MemberEdit_custGroup_id').getValue();
		me.memberData.getContactStore().each(function(record){
			if(record.get('isMainLinkMan')){
				var result = null;
				//新增的主联系人
				if(-1 != record.get('id').indexOf('X')){
					result = me.validateIdNumber(record.get('cardTypeCon'),record.get('idCard'),custType,record.get('isMainLinkMan'));
				}
				//非新增的主联系人
				else{
					//客户类别为个人，主联系人证件不能为空
					if('RC_CUSTOMER' == custGroup &&'PERSONAL' == custType 
							&& (Ext.isEmpty(record.get('idCard')) || Ext.isEmpty(record.get('cardTypeCon')))){
						flag = false;
						message = i18n('i18n.MemberCustEditView.m_idCardNotNull');
						//如果 主联系人 身份证号是被修改 验证身份证号是否重复
					}else if(record.isModified('isMainLinkMan')||record.isModified('cardTypeCon')||record.isModified('idCard')){
						result = me.validateIdNumber(record.get('cardTypeCon'),record.get('idCard'),custType,record.get('isMainLinkMan'));
					}
					if('PERSONAL' == custType&&me.memberCustBasicInfo.memberBasicRecord.get('custType')=='ENTERPRISE'){
						result = me.validateIdNumber(record.get('cardTypeCon'),record.get('idCard'),custType,record.get('isMainLinkMan'));
					}
				}
				if(result != null && !result.success){
					message = result.message;
					flag = false;
				}
			}
		});
		return {success:flag,message:message};
	},
	//身份证号校验,通过校验 success为true，不通过为false
	validateIdNumber:function(cardTypeCon,idNumber,custType,isMainContact){
		var me = this;
		var flag = true;
		var message = '';
		var validateSuccessFn = function(result){
			if(!result.exist){
//				MessageUtil.showMessage(i18n('i18n.MemberCustEditView.m_contactIsExist'));
				message = i18n('i18n.MemberCustEditView.m_contactIsExist');
				flag = false;
			}else{
				message = '';
				flag = true;
			}
			RealTimeVerifyResult.success[5] = flag;
			RealTimeVerifyResult.message[5] = message;
			return {success:flag,message:message};
		};
		var validateFailFn = function(result){
			if(DpUtil.isEmpty(result.message)){
				result.message = i18n('i18n.MemberCustEditView.anExceptionBackgroundAppearsWar');
			}
//			MessageUtil.showMessage(result.message);
			message = result.message;
			flag = false;
			RealTimeVerifyResult.success[5] = flag;
			RealTimeVerifyResult.message[5] = message;
			return {success:flag,message:message};
		};
		var params = {};
		params.idNumber = idNumber;
		params.custType = custType;
		params.cardType = cardTypeCon;
		params.isMainContact = isMainContact;
		//只有客户类型为 "个人"且"是否主联系人"选择"是"时才进行身份证验证,选择企业时默认通过校验
		if('PERSONAL' == custType && isMainContact&&!Ext.isEmpty(idNumber)){
			//税务登记号 校验结果
			RealTimeVerifyResult.success[0] = true;
			RealTimeVerifyResult.message[0] = '';
			
			me.memberData.validateIdNumberIsExist(params,validateSuccessFn,validateFailFn);
		}else{
			RealTimeVerifyResult.success[5] = true;
			RealTimeVerifyResult.message[5] = '';
		}
		return {success:flag,message:message};
	},
	// 校验账号信息中联系人信息 是否已被修改
	checkContactIsModify:function(){
		var me = this;
		var contactStore = me.memberData.getContactStore();//联系人store
		var accountStore = me.memberData.getAccountStore();//账户信息store
		var i = 0;
		accountStore.each(function(accountRecord){
			contactStore.each(function(contactRecord){
//				if(contactRecord.get('name') == accountRecord.get('financeLinkman')
//				&& contactRecord.get('mobilePhone') == accountRecord.get('linkManMobile')
//				&& contactRecord.get('telPhone') == accountRecord.get('linkManPhone')){
				if(contactRecord.get('id') == accountRecord.get('financeLinkmanId')){
					i++;
				}
			})
		});
		if(i != accountStore.getCount()){
			RealTimeVerifyResult.success[3] = false;
			RealTimeVerifyResult.message[3] = i18n('i18n.MemberCustEditView.m_contactIsUpdate');
		}else{
			RealTimeVerifyResult.success[3] = true;
			RealTimeVerifyResult.message[3] = '';
		}

	},
	// 联系人偏好地址中 地址 是否已被修改
	checkAddressIsModify:function(){
		var me = this;
		var shuttleStore = me.memberData.getShuttleAddressStore();//接送货地址store
		var contactStore = me.memberData.getContactStore();//联系人store
		var i = 0;//联系人store 得到联系人偏好地址数目
		var j = 0;//得到联系人 偏好地址 存在 会员接送货地址 的数目
		//联系人store 得到联系人偏好地址数目
		contactStore.each(function(contactRecord){
			var preferAddress = contactRecord.get('preferenceAddressList');
			if(!Ext.isEmpty(preferAddress)){
				i = i + preferAddress.length;
				//得到联系人 偏好地址 存在 会员接送货地址 的数目
				for (var k = 0; k < preferAddress.length; k++){
					shuttleStore.each(function(shuttleRecord){
						if(shuttleRecord.get('address') == preferAddress[k].address
						&& shuttleRecord.get('addressType') == preferAddress[k].addressType){
							j++;
						}
					})
				};
			}
		})
		//判断联系人偏好地址 和联系人偏好地址存在于 接送货地址中是否相等
		if (i != j){
			RealTimeVerifyResult.success[4] = false;
			RealTimeVerifyResult.message[4] = i18n('i18n.MemberCustEditView.m_addressIsUpdate');
		}else{
			RealTimeVerifyResult.success[4] = true;
			RealTimeVerifyResult.message[4] = '';
		}
	},
	//校验会员所有信息
	validateAllMemberInfo:function(){
		var me = this;
		var success = true;
		var message = '';
		//校验会员基础信息
		var validateMemberBasicRs = me.memberCustBasicInfo.validateMemberBasicInfo();
		if(!validateMemberBasicRs.success){
			success = false;
			message = validateMemberBasicRs.message;
		}else{
			//校验联系人信息
			var validateLinkmanRs = me.memberLinkmanInfo.validateLinkman();
			if(!validateLinkmanRs.success){
				success = false;
				message = validateLinkmanRs.message;
			}else{
				//校验账号信息
				var validateAccountRs = me.memberAccountInfo.validateAccount();
				if(!validateAccountRs.success){
					success = false;
					message = validateAccountRs.message;
				}else{
					//校验接送货地址
					var validateAddressRs = me.memberAddressInfo.validateAddress();
					if(!validateAddressRs.success){
						success = false;
						message = validateAddressRs.message;
					}
				}
			}
		}
		return {'success':success,'message':message};
	},
	//组装会员数据
	assembleNewMemberData:function(){
		var me = this;
		var params = {};		
		//会员基本信息
		params.member = me.memberCustBasicInfo.collectMemberBasicData();
		//联系人信息
		params.member.contactList = me.memberLinkmanInfo.collectLinkmanForMemberSubmit();
		//账号信息
		params.member.accountList = me.memberAccountInfo.collectMemberAccountsForSubmit();
		//地址信息
		params.member.shuttleAddressList = me.memberAddressInfo.collectShuttleAddressForSubmit();
		//会员修改时 会员修改日志不传到后台
		params.member.logs = [];
		//散客升级数据
		if(me.scatterUpgradeRecord != null){
			params.upGradeCust = me.scatterUpgradeRecord.data;
		}
		return params;
	},
	//组装修改会员的信息
	assembleAlterMemberData:function(){
		var me = this;
		var params = {};
		var alterMemberList = new Array();
		var alterDeleteList = new Array();
		var alterAddLinkmanList = new Array();
		var alterAddAddressHobbyList = new Array();
		var alterAddAddressList = new Array();
		var alterAddAccountList = new Array();
		
		//搜集会员基本信息修改的内容
		me.memberCustBasicInfo.collectAlterMemberData(alterMemberList);
		//搜集联系人修改信息
		me.memberLinkmanInfo.collectAllLinkmanAlterData(alterMemberList,alterDeleteList,alterAddLinkmanList,alterAddAddressHobbyList);
		//搜集账号修改信息
		me.memberAccountInfo.collectAllAcountAlterData(alterMemberList,alterDeleteList,alterAddAccountList);
		//搜集接送货地址
		me.memberAddressInfo.collectAllAddressAlterData(alterMemberList,alterDeleteList,alterAddAddressList);
		//设置会员相关数据修改approvedata
		params.alterMemberApproveData = alterMemberList;
		//设置会员相关删除数据
		params.alterDeleteMemberData = alterDeleteList;
		//设置会员ID
		params.memberId = me.member.id;
		
		//设置新增联系人信息
		params.alterAddLinkmanData = alterAddLinkmanList;
		//设置新增地址偏好信息
		params.alterAddAddressHobbyData = alterAddAddressHobbyList;
		//设置新增接送货地址信息
		params.alterAddAddressData = alterAddAddressList;
		//设置新增账号信息
		params.alterAddAccountData = alterAddAccountList;
		return params;
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
	currentCity:null,
	memberBasicRecord:null,       //MemberCustomerModel类型的record
	channelType:null,            //创建会员渠道：'normal'表示从散客升降列表创建会员,'immediate'表示实时创建会员信息,'special'表示创建特殊会员
	viewStatus:null,             //查看状态，view 查看 update 修改 new 新增
	layout:{
		type:'table',
		columns:3
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
		
		me.defaults.listeners = {
				scope:me,
				keypress:me.enterKeyEvent
			};
		
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
			if(me.channelType == 'immediate'){
				Ext.getCmp('member_cityId').selectedValue = me.memberBasicRecord.get('cityId');
				Ext.getCmp('member_cityId').makeSelectValueForLoadData();
			}
			
			//处理二级行业字段
			DpUtil.setSecondTradeValue(me.getForm().findField('secondTrade'),me.memberBasicRecord);
//			//初始化全局变量isHK的值，因为在修改固定客户实时校验香港地区的商业登记号的时候，需要传入部门id一起校验
//			isHK = me.memberBasicRecord.get('deptId');
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
			//"是否为大客户" 默认为否
			me.getForm().findField('isKeyCustomer').setValue(false);
			//"是否集中结算"默认为“否”
			me.getForm().findField('isFocusPay').setValue(false);
			//"是否母公司"默认为“是”
			me.getForm().findField('isParentCompany').setValue(true);
			//"临欠额度"默认为1000元
			me.getForm().findField('procRedit').setValue();
			//"所属母公司"不可编辑
			me.getForm().findField('parentCompanyId').setReadOnly(true);
//			//潜客的客户来源限制
//			var sourceStore = me.getForm().findField('channelSource').store;
//			sourceStore.removeAt(sourceStore.find('code','ARRIVE_CUSTOMER'));
//			sourceStore.removeAt(sourceStore.find('code','ORDER_CUSTOMER'));
//			sourceStore.removeAt(sourceStore.find('code','HALL'));
//			sourceStore.removeAt(sourceStore.find('code','CALLCENTER'));
//			sourceStore.removeAt(sourceStore.find('code','ONLINEHALL'));
//			sourceStore.removeAt(sourceStore.find('code','E_BUSINESS'));	
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
			//客户来源不可编辑
			me.getForm().findField('channelSource').setReadOnly(true);
		}
		//创建会员信息、实时创建会员信息 "客户等级"可手工选择  处理业务规则
		if(me.channelType == 'special'){
			//创建特殊会员信息时"客户等级"可手工选择
			me.getForm().findField('degree').setReadOnly(false);
			
			//创建会员信息、实时创建会员信息，"是否特殊客户"默认为"否",创建特殊会员信息时默认为"是"
			me.getForm().findField('isSpecial').setValue(true);
			
			//手动创建时 客户性质默认为潜客
			me.getForm().findField('custGroup').setValue('RC_CUSTOMER');
		}
		//实时创建会员 是否特殊客户，是否重要客户默认为只读
		else if(me.channelType == 'immediate'){
			me.getForm().findField('isSpecial').setValue(false);
			me.getForm().findField('isSpecial').setReadOnly(true);
			me.getForm().findField('surplusMonthlyStatementMoney').setReadOnly(true);
			//实时创建会员不进行验证也显示默认部门(不做了)
//			me.memberData.getSpecialDeptStore().insert(0,Ext.create('FocusDeptStoreModel',{'id':top.User.deptId,'deptName':top.User.deptName}));
//			me.getForm().findField('deptId').setValue(top.User.deptId);
		}else if(me.channelType == 'addPotential'){//创建潜客
			//登陆部门默认为潜客所属部门
			me.memberData.getSpecialDeptStore().insert(0,Ext.create('FocusDeptStoreModel',{'id':top.User.deptId,'deptName':top.User.deptName}));
			me.getForm().findField('deptId').setValue(top.User.deptId);
			//手动创建时 客户性质默认为潜客
//			me.getForm().findField('custGroup').setValue('PC_CUSTOMER');
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
			//me.getForm().findField('cardTypeCust').setReadOnly(true);
			me.getForm().findField('taxregNumber').setValue();
			me.getForm().findField('companyProperty').setValue();
			//me.getForm().findField('cardTypeCust').setValue();
		}
		//固定客户必填，而潜散客非必填的处理
		if('RC_CUSTOMER'!=me.getForm().findField('custGroup').getValue()){
			me.getForm().findField('degree').fieldLabel = i18n('i18n.MemberCustEditView.custLevel');
			me.getForm().findField('degree').allowBlank = true;
			me.getForm().findField('taxregNumber').fieldLabel = me.returnObj()?'商业登记号':i18n('i18n.ScatterCustManagerView.taxId');
			me.getForm().findField('companyProperty').fieldLabel = i18n('i18n.ScatterCustManagerView.companyProperties');
//			if('PC_CUSTOMER'==me.getForm().findField('custGroup').getValue()){
			me.getForm().findField('procRedit').setMaxValue('3000');
//			}
		}
		if('1'==me.getForm().findField('isKeyCustomer').getValue()){
			me.getForm().findField('custNumberVIP').setVisible(true);
			me.getForm().findField('custNumber').setWidth(214);
		}else{
			me.getForm().findField('custNumberVIP').setVisible(false);
			me.getForm().findField('custNumber').setWidth(240);
		}
	},
	getItems:function(){
		var me = this;
		var operateType = null;
		if(me.viewStatus == 'view'){
			operateType = 'VIEW';
		}else if(me.viewStatus == 'update'){
			operateType = 'UPDATE';
		}else if(me.channelType == 'addPotential'){
			operateType = 'UPDATE';
		}
		return [
				{//省市
					xtype:'fieldcontainer',
					colspan:1,
					layout:{
						type:'hbox'
					},
					items:[ 
					        {
					    xtype:'dptextfield',
						fieldLabel:i18n('i18n.MemberCustEditView.custNo'),
						readOnly:true,
						name:'custNumber',
						labelWidth : 70,
						width : 240
				        },
				        {
						xtype:'displayfield',
						name:'custNumberVIP',
						value:'<span><img src="../images/customer/VIP.png"></span>',
						width:30,
						hidden:true
				        }]
				},
		       {
			fieldLabel:i18n('i18n.MemberCustEditView.custGroup'),
			id:'MemberEdit_custGroup_id',
			name:'custGroup',
			xtype:'dpcombobox',
			forceSelection:true,
			readOnly:true,
			allowBlank:false,
			blankText:i18n('i18n.MemberCustEditView.m_noCustGroup'),
			store:me.memberData.getCustGroupStore(),
			displayField:'codeDesc',
			valueField:'code',
			queryMode:'local',
			listeners:{
				change:DpUtil.changeCombText
			}
		},{
			fieldLabel:i18n('i18n.MemberCustEditView.isKeyCustomer'),
			xtype:'dpcombobox',
			name:'isKeyCustomer',
			queryMode:'local',
            forceSelection:true,
			displayField:'name',
			valueField:'value',
			store:me.memberData.getBooleanStore(),
			readOnly:true,
			allowBlank:false,
			emptyText:i18n('i18n.MemberCustEditView.isOrNot'),
			blankText:i18n('i18n.MemberCustEditView.isKeyCustomerAlert')
		},{
			fieldLabel:i18n('i18n.ScatterUpgradeView.belongdeptName'),
			xtype:'dpcombobox',
			name:'deptId',
			displayField : 'deptName',
			valueField:'id',
//			maxLength:80,
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
				//重新选择部门之后清除已选标签和已加载标签
            			clearLabels();
            			//加载待选标签
            			initBasicData(me.viewStatus,'MEMBER',me.memberBasicRecord,me.channelType,records[0].get('id'));

// var validateSuccessFn = function(result){
            			Ext.getCmp("fieldcontainerObj").remove(combo.up('form').getForm().findField('taxregNumber'));
            			Ext.getCmp("fieldcontainerObj").insert(0,{
            				xtye:'textfield',
// 根据后台传过来的数据，判断是香港还是大陆地区
            				fieldLabel:User.deptCityLocation == "1"?'商业登记号':i18n('i18n.ScatterCustManagerView.taxId'),
            						// hideLabel :true,
            						// width:125,
            						name:'taxregNumber',
            						regex:User.deptCityLocation == "1"?DpUtil.linkWayLimit('HKTAX'):DpUtil.linkWayLimit('CNTAX'),
            								emptyText:User.deptCityLocation == "1"?'输入时不要输入“-”即12345678000':'请输入合法的税务登记号',
            										regexText:User.deptCityLocation == "1"?'商业登记号为：12345678-000，输入时不要输入“-”即12345678000':'请输入合法的税务登记号',
            											listeners:{
            												scope:me,
            												blur:me.blurTaxregNumber
            											}
            			});
            			}
// ;
// var validateFailFn = function(result){
// if(DpUtil.isEmpty(result.message)){
// result.message = '查询部门归属地失败';
// }
// MessageUtil.showMessage(result.message);
// return;
// }
// var params = {};
// params.deptId = records[0].get('id');
// me.memberData.chenckIsCnOrHK(params,validateSuccessFn,validateFailFn);
            		}
	        }
		
		},
//			,me.getDateComboSelect(i18n('i18n.MemberCustEditView.custLevel'),'degree',me.memberData.getMemberGradeStore(),i18n('i18n.MemberCustEditView.m_noGrade'))
			{
			fieldLabel:i18n('i18n.MemberCustEditView.custLevel')+'<span style="color:red;">*</span>',
			name:'degree',
			xtype:'dpcombobox',
			forceSelection:true,
			readOnly:true,
			allowBlank:false,
			blankText:i18n('i18n.MemberCustEditView.m_noGrade'),
			store:me.memberData.getMemberGradeStore(),
			displayField:'codeDesc',
			valueField:'code',
			queryMode:'local',
			listeners:{
				change:DpUtil.changeCombText
			}
		},{
			fieldLabel:i18n('i18n.PotentialCustManagerView.customerName')+'<span style="color:red;">*</span>',
			allowBlank:false,
			name:'custName',
			maxLength:80,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80),
			blankText:i18n('i18n.PotentialCustEditView.message_custName')
		},{name:'custCategory',
			xtype:'dpcombobox',
			fieldLabel:i18n('i18n.member.custCategory')+'<span style="color:red;">*</span>',
			queryMode:'local',
			displayField:'codeDesc',
			valueField:'code',
			forceSelection:true,
			editable:false,
			allowBlank:false,
			store:me.memberData.getCustCategoryStore()
		},{
			fieldLabel:i18n('i18n.ScatterCustManagerView.custAttribute')+'<span style="color:red;">*</span>',
			xtype:'dpcombobox',
			allowBlank:false,
			blankText:i18n('i18n.MemberCustEditView.m_propertyNotNull'),
			queryMode:'local',
            forceSelection:true,
            editable:false,
			displayField:'codeDesc',
			valueField:'code',
			name:'custNature',
			store:me.memberData.getCustomerNatureStore()
		},{
			fieldLabel:i18n('i18n.MemberCustEditView.custType')+'<span style="color:red;">*</span>',
			xtype:'dpcombobox',
			id:'custType_id',
			listeners:{
				scope:me,
				keypress:me.enterKeyEvent,
				select:me.selectCustTypeEvent
			},
			name:'custType',
			editable:false,
			allowBlank:false,
			blankText:i18n('i18n.MemberCustEditView.custTypeAlert'),
			queryMode:'local',
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code',
			store:me.memberData.getCustomerTypeStore()
		},{//省市
			xtype:'fieldcontainer',
			layout:{
				type:'hbox'
			},
			defaults:{
				hideLabel:true,
				enableKeyEvents:true,
				listeners:{
					scope:me,
					keypress:me.enterKeyEvent
				},
				allowBlank:false
			},
			items:[{
				xtype:'displayfield',
				value:i18n('i18n.MemberCustEditView.province')+'<span style="color:red;">*</span>',
				width:75
				},
				   {
					xtype:'areaaddresscombox',
					id:'member_cityId',
					name:'cityId',
					flex:1,
					readOnlyCls:'readonly',
					disabledCls:'readonly',
					width:110,
					forceSelection:true,
					operateType:operateType,
					disabled:('view' == me.viewStatus)?true:false,
					selectedValue:me.memberBasicRecord.get('cityId'),
					tabPanel:['provincePanel','cityPanel'],
					blankText:i18n('i18n.MemberCustEditView.cityAlert'),
					emptyText:i18n('i18n.MemberCustEditView.cityAlert'),
					//提供给实时创建加载数据时使用
					makeSelectValueForLoadData:function(){
						var me=this;
						if(me.selectedValue!=null && me.selectedValue!=''){
							if(me.operateType=='VIEW'||me.operateType=='UPDATE'){
								id = me.id;
								if(me.tabPanel[me.tabPanel.length-1]=='provincePanel'){
										Ext.Ajax.request({
										url:'../common/searchProvinceById.action',
										async:false,
										params:{'param':me.selectedValue},
										success:function(response){
											var result = Ext.decode(response.responseText);
											me.setValue(me.selectedValue);
											if(result.provinceList.length>0){
												me.setRawValue(result.provinceList[0].name);
											}
										},
										failure:function(response){
											var result = Ext.decode(response.responseText);
//											Ext.MessageBox.alert("提示",result.message);
											MessageUtil.showMessage(result.message);
										}
									});	
								}else if(me.tabPanel[me.tabPanel.length-1]=='cityPanel'){
									Ext.Ajax.request({
										url:'../common/searchCityById.action',
										async:false,
										params:{'param':me.selectedValue},
										success:function(response){
											var result = Ext.decode(response.responseText);
//											me.setValue(me.selectedValue);
//											if(result.cityList.length>0){
//												me.setRawValue(result.cityList[0].name);
//											}
											//修改者 李学兴 
											var store = Ext.data.StoreManager.lookup(this.id+'SearchCityByNameStore');
											store.insert(0,Ext.create('cityModel',result.cityList[0]));
											me.setValue(result.cityList[0].id);
										},
										failure:function(response){
											var result = Ext.decode(response.responseText);
//											Ext.MessageBox.alert("提示",result.message);
											MessageUtil.showMessage(result.message);
										}
									});	
								}else{
										Ext.Ajax.request({
										url:'../common/searchAreaById.action',
										async:false,
										params:{'param':me.selectedValue},
										success:function(response){
											var result = Ext.decode(response.responseText);
//											me.setValue(me.selectedValue);
//											if(result.areaList.length>0){
//												me.setRawValue(result.areaList[0].name);
//											}
											//修改者 李学兴 
											var store = Ext.data.StoreManager.lookup(this.id+'SearchCityByNameStore');
											store.insert(0,Ext.create('areaModel',result.areaList[0]));
											me.setValue(result.areaList[0].id);
										},
										failure:function(response){
											var result = Ext.decode(response.responseText);
//											Ext.MessageBox.alert("提示",result.message);
											MessageUtil.showMessage(result.message);
										}
									});	
								}
							}
						}
					},
					listeners:{
						change:function(combobox,newValue,oldValue){
							if(!DpUtil.isEmpty(newValue)){
									var cityRawValue = combobox.getRawValue();	
								if(!DpUtil.isEmpty(cityRawValue)&&me.viewStatus == 'new'){
									me.getForm().findField('registAddress').setValue(cityRawValue+'-');
								}
							}
						},
						render:function(){
//							var me = this;
//							me.setValue(me.selectedValue);
						}
					}
				   }
				]
		},
		{//详细地址
			fieldLabel:i18n('i18n.MemberCustEditView.registAddress')+'<span style="color:red;">*</span>',
			colspan:2,
			width:492,
			allowBlank:false,
			maxLength:100,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',100),
			name:'registAddress'
		},{	xtype:'fieldcontainer',
			colspan:2,
			id:'fieldcontainerObj',
			layout:{
				type:'table',
				columns:3
			},
			defaultType:'dptextfield',
			defaults:{
				labelWidth:80,
				width:250,
				enableKeyEvents:true,
				listeners:null
			},
			items:[
				{
//				根据后台传过来的数据，判断是香港还是大陆地区
				fieldLabel:me.returnObj()?'商业登记号'+'<span style="color:red;">*</span>':i18n('i18n.ScatterCustManagerView.taxId')+'<span style="color:red;">*</span>',
				margin:'0 0 0 -10',
				name:'taxregNumber',
				regex:me.returnObj()?DpUtil.linkWayLimit('HKTAX'):DpUtil.linkWayLimit('CNTAX'),
				emptyText:me.returnObj()?'输入时不要输入“-”即12345678000':'请输入合法的税务登记号',
				regexText:me.returnObj()?'商业登记号为：12345678-000，输入时不要输入“-”即12345678000':'请输入合法的税务登记号',
				maxLength:20,
				maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20),
				listeners:{
					scope:me,
					blur:me.blurTaxregNumber
				}
			},{
				fieldLabel:i18n('i18n.ScatterCustManagerView.companyProperties')+'<span style="color:red;">*</span>',
				xtype:'dpcombobox',
				blankText:i18n('i18n.MemberCustEditView.companyPropertyAlert'),
				queryMode:'local',
				margin:'0 0 0 -10',
	            forceSelection:true,
				displayField:'codeDesc',
				valueField:'code',
				name:'companyProperty',
				store:me.memberData.getCompNatureStore(),
				listeners:{
					change:function(field, newValue) {if (Ext.isEmpty(newValue)) {field.setValue(null);}}
				}
			}]
		},
		{
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
		{
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
		},{
			fieldLabel:i18n('i18n.MemberCustEditView.custPotentialType'),//潜力类型，货量潜力
			xtype:'dpcombobox',
			queryMode:'local',
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code',
			name:'custPotentialType',
//			emptyText:i18n('i18n.MemberCustEditView.highOrLow'),
			store:me.memberData.getCustPotentialStore(),
			listeners:{
				change:function(field, newValue) {if (Ext.isEmpty(newValue)) {field.setValue(null);}}
			}
		},{
			fieldLabel:i18n('i18n.MemberCustEditView.isAcceptMarket'),
			xtype:'dpcombobox',
			queryMode:'local',
            forceSelection:true,
            editable:false,
			displayField:'codeDesc',
			valueField:'code',
			name:'isAcceptMarket',
			emptyText:i18n('i18n.MemberCustEditView.acceptOrNot'),
			store:me.memberData.getMarketInfoStore()
		},{
			fieldLabel:i18n('i18n.MemberCustEditView.brandWorth'),
			maxLength:20,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20),
			name:'brandWorth'
		},{
			fieldLabel:i18n('i18n.PotentialCustManagerView.custSource')+'<span style="color:red;">*</span>',
			xtype:'dpcombobox',
			queryMode:'local',
            forceSelection:true,
            allowBlank:false,
			displayField:'codeDesc',
			valueField:'code',
			name:'channelSource',
			store:me.memberData.getCustSourceStore(),
			listeners:{
				change:function(field, newValue) {if (Ext.isEmpty(newValue)) {field.setValue(null);}}
			}
		},{
			fieldLabel:i18n('i18n.MemberCustEditView.channel'),//偏好渠道
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
		},{
			fieldLabel:i18n('i18n.MemberCustEditView.preferenceService'),//偏好服务
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
		},{
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
		},{
			fieldLabel:i18n('i18n.MemberCustEditView.lastYearProfit'),
			xtype:'dpnumberfield',
			hideTrigger:true,
			maxLength:10,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',10),
			name:'lastYearProfit'
		},{
			fieldLabel:i18n('i18n.MemberCustEditView.lastYearIncome'),
			xtype:'dpnumberfield',
			hideTrigger:true,
			maxLength:10,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',10),
			name:'lastYearIncome'
		},
		{
			fieldLabel:i18n('i18n.MemberCustEditView.billTitle'),
			maxLength:40,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',40),
			name:'billTitle'
		},
		{
			fieldLabel:i18n('i18n.MemberCustEditView.registerFund'),
			xtype:'dpnumberfield',
			hideTrigger:true,
			maxLength:10,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',10),
			name:'registerFund'
		},{
			fieldLabel:i18n('i18n.MemberCustEditView.procRedit'),
			xtype:'dpnumberfield',
			hideTrigger:true,
			allowBlank:true,
			maxLength:10,
			minValue:0,
			decimalPrecision:0,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',10),
			blankText:i18n('i18n.MemberCustEditView.procReditAlert'),
			name:'procRedit',
			listeners:{
				isRC_CUstomer:function(ths) 
					{
						var k  = me.memberData;
					}
			}
		},
		/*{name:'potenSource',
			xtype:'dpcombobox',
			fieldLabel:i18n('i18n.member.potenSource'),
			queryMode:'local',
			displayField:'codeDesc',
			valueField:'code',
			readOnly:true,
			store:me.memberData.getCustSourceStore()
		},*/{
			xtype:'customertrade',
			width:500,
			colspan:2,
			editable:false,
			viewStatus:me.viewStatus,
			forceSelection:true,
			trade_labelWidth:70,
			trade_margin:'0 5 0 0',
			trade_width:240,
			trade_name:'tradeId',
			trade_fieldname:i18n('i18n.MemberCustEditView.custIndustry'),
			second_trade_labelWidth:75,
			second_trade_width:245,
			second_trade_name:'secondTrade',
			second_trade_fieldname:i18n('i18n.secondTrade.secondTrade')
		},{
			fieldLabel:'财务冻结',//i18n('i18n.ScatterCustManagerView.remark'),
			name:'finOver',
			colspan:3,
			readOnly:true,
			xtype:'dpcombobox',
			queryMode:'local',
			forceSelection:true,
			displayField:'name',
			valueField:'value',
			store:me.memberData.getBooleanStore()
		},{
			fieldLabel:i18n('i18n.ScatterCustManagerView.remark'),
			margin:'5 0 0 0',
			name:'remark',
			colspan:3,
			maxLength:200,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',200),
			width:730
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
	
	//实时验证税务登记号
	validateTaxIsExsist:function(taxfield){
		var me = this;
		var taxNum = taxfield.getValue();
		if(!DpUtil.isEmpty(taxNum)){
			//判断 税务登记号是否被修改了，被修改才做校验
			me.getForm().updateRecord(me.memberBasicRecord);
			if(!me.memberBasicRecord.isModified('taxregNumber') || !taxfield.isValid()){
				RealTimeVerifyResult.success[0] = true;
				RealTimeVerifyResult.message[0] = '';
				return;
			}
			var validateSuccessFn = function(result){
				//不存在 "exist" 为true
				if(result.exist){
					RealTimeVerifyResult.success[0] = true;
					RealTimeVerifyResult.message[0] = '';
				}else{
					RealTimeVerifyResult.success[0] = false;
					RealTimeVerifyResult.message[0] = i18n('i18n.MemberCustEditView.m_taxIsExsit');
					MessageUtil.showMessage(RealTimeVerifyResult.message[0]);
					return;
				}
//				RealTimeVerifyResult.success[0] = true;
			};
			var validateFailFn = function(result){
				if(DpUtil.isEmpty(result.message)){
					result.message = i18n('i18n.MemberCustEditView.anExceptionBackgroundAppearsWar');
					RealTimeVerifyResult.success[0] = false;
					RealTimeVerifyResult.message[0] = result.message;
				}
				RealTimeVerifyResult.success[0] = false;
				RealTimeVerifyResult.message[0] = result.message;
				MessageUtil.showMessage(result.message);
				return;
			};
			var params = {};
			params.taxNum = taxNum;
			me.memberData.validateTaxNumberIsExist(params,validateSuccessFn,validateFailFn);
		}
	},
	//实时校验商务登记号是否存在	
	validateHKTaxIsExist:function(taxfield,records){
		var me = this;
		var taxNum = taxfield.getValue();
		var location = '1';
		//判断 商业登记号是否被修改了，被修改才做校验
		me.getForm().updateRecord(me.memberBasicRecord);
		if(!me.memberBasicRecord.isModified('taxregNumber') || !taxfield.isValid()){
			RealTimeVerifyResult.success[0] = true;
			RealTimeVerifyResult.message[0] = '';
			return;
		}
		var validateSuccessFn = function(result){
			//不存在 "exist" 为true
			if(result.exist){
				RealTimeVerifyResult.success[0] = true;
				RealTimeVerifyResult.message[0] = '';
			}else{
				RealTimeVerifyResult.success[0] = false;
				RealTimeVerifyResult.message[0] = '商业登记号已经存在!';
				MessageUtil.showMessage(RealTimeVerifyResult.message[0]);
				return;
			}
//			RealTimeVerifyResult.success[0] = true;
		};
		var validateFailFn = function(result){
			if(DpUtil.isEmpty(result.message)){
				result.message = i18n('i18n.MemberCustEditView.anExceptionBackgroundAppearsWar');
				RealTimeVerifyResult.success[0] = false;
				RealTimeVerifyResult.message[0] = result.message;
			}
			RealTimeVerifyResult.success[0] = false;
			RealTimeVerifyResult.message[0] = result.message;
			MessageUtil.showMessage(result.message);
			return;
		};
		var params = {};
//		params.locaction = deptId;
//		params.taxNum = taxNum;
		params={'location':location,'taxNum':taxNum};
		me.memberData.validateHKTaxNumberIsExist(params,validateSuccessFn,validateFailFn);
	},
	//判断是否需要实时验证税务登记号/商务注册号的合法性
//	blurTaxregNumber:function(taxfield){
//		var me = this;
//		var taxfield = Ext.getCmp("fieldcontainerObj").items.items[0];
//		if(0 != taxfield.value.length && "0" == User.deptCityLocation){
//			me.validateTaxIsExsist(taxfield);
//		}
//		else if(0 != taxfield.value.length && "1" == User.deptCityLocation){
//			me.validateHKTaxIsExist(taxfield);
//		}
//	},
	blurTaxregNumber:function(){
		var me = this;
		
		var isSpecial=me.channelType;
		var taxfield = Ext.getCmp("fieldcontainerObj").items.items[0];
		var a = null;
		//如果是创建特殊会员
		if ("special"==isSpecial) {
			a = isHK;
		}else{
			a = User.deptCityLocation;
		}
		//税务登记号验证
		if(0 != taxfield.value.length && "0" == a){
			me.validateTaxIsExsist(taxfield);
		}
		//商业登记号验证
		else if(0 != taxfield.value.length && "1" == a ){
			me.validateHKTaxIsExist(taxfield);
		}else{
		//重新初始化全局变量
			RealTimeVerifyResult.success[0] = true;
			RealTimeVerifyResult.message[0] = '';
		}
	},
	//Enter键按下时焦点自动移到下个控件
	enterKeyEvent:function(field,event){
		var me = this;
		if(event.getKey() == Ext.EventObject.ENTER){
			if(field.next() != null){
				if(field.fieldLabel == 'province' || field.fieldLabel == i18n('i18n.MemberCustEditView.registAddress')){
					field.next().focus();
				}else if(field.fieldLabel == i18n('i18n.ScatterCustManagerView.custAttribute')){
					me.getForm().findField('province').focus();
				}else if(field.next().next() != null){
					field.next().next().focus();
				}
			}else if(field.fieldLabel == 'city'){
				me.getForm().findField('registAddress').focus();
			}
		}
	},
	//客户类型改变事件
	selectCustTypeEvent:function(combox,records){
		var me = this;
		//当客户类型为"个人"时"企业税务登记号"、"公司性质"不可编辑且校验主联系人身份证号是否唯一,且清空填写内容
		if(records.length > 0 && records[0].get('codeDesc') == i18n('i18n.MemberCustEditView.personal')){
			//清空税务登记号校验结果
			RealTimeVerifyResult.success[0] = true;
			RealTimeVerifyResult.message[0] = '';
			me.getForm().findField('taxregNumber').setReadOnly(true);
			me.getForm().findField('companyProperty').setReadOnly(true);
			//me.getForm().findField('cardTypeCust').setReadOnly(true);
			me.getForm().findField('taxregNumber').setValue();
			me.getForm().findField('companyProperty').setValue();
			//me.getForm().findField('cardTypeCust').setValue();
			
			me.memberData.getContactStore().each(function(record){
				if(record.get('isMainLinkMan')){
					me.validateIdNumber(record.get('cardTypeCon'),record.get('idCard'),combox.getValue(),record.get('isMainLinkMan'));
				}
			});
		}else{
			//清空身份证校验结果
			RealTimeVerifyResult.success[5] = true;
			RealTimeVerifyResult.message[5] = '';
			me.getForm().findField('taxregNumber').setReadOnly(false);
			me.getForm().findField('companyProperty').setReadOnly(false);
			//me.getForm().findField('cardTypeCust').setReadOnly(false);
		}
	},
	//身份证号校验
	validateIdNumber:function(cardTypeCon,idNumber,custType,isMainContact){
		var me = this;
		if(Ext.isEmpty(idNumber)||Ext.isEmpty(cardTypeCon)){
//			MessageUtil.showMessage(i18n('i18n.MemberCustEditView.m_idCardNotNull'));
			RealTimeVerifyResult.success[5] = false;
			RealTimeVerifyResult.message[5] = i18n('i18n.MemberCustEditView.m_idCardNotNull');
			return;
		}
		var validateSuccessFn = function(result){
			if(!result.exist){
				RealTimeVerifyResult.success[5] = false;
				RealTimeVerifyResult.message[5] = i18n('i18n.MemberCustEditView.m_idCardIsExsit');
//				MessageUtil.showMessage(i18n('i18n.MemberCustEditView.m_idCardIsExsit'));
				return;
			}
		};
		var validateFailFn = function(result){
			if(DpUtil.isEmpty(result.message)){
				result.message = i18n('i18n.MemberCustEditView.anExceptionBackgroundAppearsWar');
				RealTimeVerifyResult.success[5] = false;
				RealTimeVerifyResult.message[5] = result.message;
			}
//			MessageUtil.showMessage(result.message);
			return;
		};
		var params = {};
		params.idNumber = idNumber;
		params.custType = custType;
		params.cardType = cardTypeCon;  
		params.isMainContact = isMainContact;
		//只有客户类型为 "个人"且"是否主联系人"选择"是"时才进行身份证验证
		if('PERSONAL' == custType && isMainContact&&!Ext.isEmpty(idNumber)){
			me.memberData.validateIdNumberIsExist(params,validateSuccessFn,validateFailFn);
		}
	},
	// 校验账号信息中联系人信息 是否已被修改 
	checkContactIsModify:function(){
		var me = this;
		var contactStore = me.memberData.getContactStore();//联系人store
		var accountStore = me.memberData.getAccountStore();//账户信息store
		var i = 0;
		accountStore.each(function(accountRecord){
			contactStore.each(function(contactRecord){
				if(contactRecord.get('name') == accountRecord.get('financeLinkman')
				&& contactRecord.get('mobilePhone') == accountRecord.get('linkManMobile')
				&& Contactrecord.get('telPhone') == accountRecord.get('linkManPhone')){
					i++;
				}
			})
		});
		if(i != accountStore.getCount()){
			RealTimeVerifyResult.success[3] = false;
			RealTimeVerifyResult.message[3] = i18n('i18n.MemberCustEditView.m_contactIsUpdate');
		}else{
			RealTimeVerifyResult.success[3] = true;
			RealTimeVerifyResult.message[3] = '';
		}

	},
	//选择是否为母公司事件
	selectIsParentCompanyEvent:function(combox,records){
		var me = this;
		//如果“是否母公司”的值为“否”，则"客户所属母公司"可编辑，否则不可编辑，且清空所属母公司
		if(combox.getValue() == true){
			me.getForm().findField('parentCompanyId').setReadOnly(true);
			me.getForm().findField('parentCompanyId').setValue();
		}else{
			me.getForm().findField('parentCompanyId').setReadOnly(false);
		}
	},
	//选择是否为集中结算
	selectIsFocusPay:function(combox,records){
		var me = this;
		//如果“是否为集中结算”的值为“是”，则"集中结算部门"可编辑，否则不可编辑，且清空集中结算部门
		if(combox.getValue() == false){
			me.getForm().findField('focusDeptId').setReadOnly(true);
			me.getForm().findField('focusDeptId').setValue();
		}else{
			me.getForm().findField('focusDeptId').setReadOnly(false);
		}
	},
	//所属母公司change事件
	changeParentCompanyId : function(combox,record){
		var me = this;
		var parentCompanyNumber = combox.getRawValue();
		me.getForm().findField('parentNumber').setValue(parentCompanyNumber);
	},
	//校验会员基础信息是否完整
	validateMemberBasicInfo:function(){
		var me = this;
		var success = true;
		
		var message = '';
		if(me.getForm().isValid()){
			//客户性质为固定客户 时才必填
			var custGroup = me.getForm().findField('custGroup').getValue();
			if('RC_CUSTOMER'==custGroup){
				//当客户类型为"企业客户"时"企业税务登记号"和"公司性质"必填
				var custType = me.getForm().findField('custType');
				var taxNumber = me.getForm().findField('taxregNumber');
				var companyProperty = me.getForm().findField('companyProperty');
				if(custType.getValue() == 'ENTERPRISE' && (DpUtil.isEmpty(taxNumber.getValue()) || DpUtil.isEmpty(companyProperty.getValue())) ){
//					message = i18n('i18n.MemberCustEditView.m_must_taxAndCustNature');
					message = me.returnObj()?'客户类型为企业客户时，必须填写企业商业登记号和公司性质!':i18n('i18n.MemberCustEditView.m_must_taxAndCustNature');
					success = false;
				}else{
					//如果“是否集中结算”的值为“是”，集中结算部门则必填
					var isFocusPay = me.getForm().findField('isFocusPay');
					var focusDept = me.getForm().findField('focusDeptId');
					if(isFocusPay.getValue() == true && DpUtil.isEmpty(focusDept.getValue())){
						message = i18n('i18n.MemberCustEditView.m_must_focusDept');
						success = false;
					}else{
						//如果“是否母公司”的值为“否”，则"客户所属母公司"必填
						var isParentCompany = me.getForm().findField('isParentCompany');
						var parentCompany = me.getForm().findField('parentCompanyId');
						if(isParentCompany.getValue() == 0 && DpUtil.isEmpty(parentCompany)){
							message = i18n('i18n.MemberCustEditView.m_msut_mainCompany');
							success = false;
						}
					}
				}
			}
			
		}else{
			message = i18n('i18n.MemberCustEditView.m_must_fillForm');
			success = false;
		}
		return {'success':success,'message':message};
	},
	//搜集会员基本信息用于提交
	collectMemberBasicData:function(){
		var me = this;
		me.getForm().updateRecord(me.memberBasicRecord);
		//处理 会员 省市 id问题
		me.manageModel4City(me.memberBasicRecord);
		return me.memberBasicRecord.data;
	},
	//搜集会员修改的信息
	collectAlterMemberData:function(alterMemberList){
		var me = this;
		me.getForm().updateRecord(me.memberBasicRecord);
		//处理 会员 省市 id问题
		me.manageModel4City(me.memberBasicRecord);
		//会员基本信息的所有字段
		var fieldsArray = me.memberBasicRecord.fields.items;
		for(var i = 0; i < fieldsArray.length;i++){
			//字段名
			var fieldName = fieldsArray[i].name;
			
			if(me.memberBasicRecord.isModified(fieldName)){
				var basicApproveData = me.getEmptyMemberApproveData();
				basicApproveData.set('fieldName',fieldName);
				basicApproveData.set('newValue',me.memberBasicRecord.get(fieldName));
				alterMemberList.push(basicApproveData.data);
			}
		}
		
	},
	//创建空的会员信息的approvedata
	getEmptyMemberApproveData:function(){
		var me = this;
		var basicApproveData = Ext.create('ApproveDataModel');
		basicApproveData.set('className','Member');
		basicApproveData.set('classId',me.memberBasicRecord.get('id'));
		return basicApproveData;
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
							{xtype:'button',text:i18n('i18n.PotentialCustManagerView.add'),
						    scope:me,
						    handler:me.showCreateMemberLinkman},
					       {xtype:'button',text:i18n('i18n.PotentialCustManagerView.update'),
						    scope:me,
						    handler:me.showUpdateMemberLinkman},
					       {xtype:'button',text:i18n('i18n.PotentialCustManagerView.delete'),
						    scope:me,
						    handler:me.delteMemberLinkman}]
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
	//新增联系人
	showCreateMemberLinkman:function(){
		var me = this;
		//新增联系人时需要先选择"客户类型"
		if(Ext.isEmpty(me.parent.memberCustBasicInfo.getForm().findField('custType').getValue())){
			MessageUtil.showMessage(i18n('i18n.MemberCustEditView.m_custTypeMust'));
			return;
		}  
		var contactModel = Ext.create('ContactModel');
		me.getContactWin(me.memberData,contactModel,'new',me);
//		Ext.create('MemberLinkmanEditWindow',{'memberData':me.memberData,
//											  'contactRecord':contactModel,
//											  'parent':me,
//											  'viewStatus':'new'}).show();
	},
	//修改联系人
	showUpdateMemberLinkman:function(){
		var me = this;
		//修改联系人时需要先选择"客户类型"
		if(Ext.isEmpty(me.parent.memberCustBasicInfo.getForm().findField('custType').getValue())){
			MessageUtil.showMessage(i18n('i18n.MemberCustEditView.m_custTypeMust'));
			return;
		} 
		var grid = me.down('grid');
		var selectModel = grid.getSelectionModel();
		var selections = selectModel.getSelection();
		if(selections.length != 1){
			MessageUtil.showMessage(i18n('i18n.MemberCustEditView.pleaseOneRecordToUpdate'));
		}else{
			var selected = selections[0];
			me.getContactWin(me.memberData,selected,'update',me);
		}
	},
	//获得联系人窗体
	getContactWin:function(memberData,record,viewStatus,parent){
		Ext.create('MemberLinkmanEditWindow',{'memberData':memberData,
				   							   'contactRecord':record,
				   							   'parent':parent,
											   'viewStatus':viewStatus}).show();
	},
	//删除联系人
	delteMemberLinkman:function(){
		var me = this;
		var grid = me.down('grid');
		var selectModel = grid.getSelectionModel();
		var selections = selectModel.getSelection();
		if(selections.length != 1){
			MessageUtil.showMessage(i18n('i18n.MemberCustEditView.pleaseOneRecordToDelete'));
		}else{
			var selected = selections[0];
			//联系人信息来源于客户升级列表,不可删除
			var createSource = selected.get('createSource');
			if(createSource == 1 || createSource == 2){
				var tipMsg = (createSource == 1)? i18n('i18n.MemberCustEditView.FITUpgradeList'):i18n('i18n.MemberCustEditView.real-timeCreateMembershipInformation');
				MessageUtil.showMessage(i18n('i18n.MemberCustEditView.m_linkManSource') + tipMsg + i18n('i18n.MemberCustEditView.canNotDeleteWar'));
			}else{
				//财务联系人不允许删除
				var delIsAccountLinkmanResult = me.delIsAccountLinkman(selected);
				if(!delIsAccountLinkmanResult.success){
					MessageUtil.showMessage(delIsAccountLinkmanResult.message);
					return;
				}
				MessageUtil.showQuestionMes(i18n('i18n.PotentialCustManagerView.confirm_message'),function(btn){
			        	   if(btn == 'yes'){
				        	   me.memberData.getContactStore().remove(selected);
				        	   me.delContactRealTimeVerifyResult(selected);
			        	   }
			       });
			}
		}
	},
	//删除该联系人关联的账号 财务联系人信息
	delIsAccountLinkman:function(contactRecord){
		var me = this;
		var success = true;
		var message = '';
		me.memberData.getAccountStore().each(function(record){
			if(contactRecord.get('id')==record.get('financeLinkmanId')){
				success = false;
				accountNum = record.get('bankAccount');
//				message = i18n('i18n.MemberCustEditView.contactLinkAccount',accountNum);
				message = '该联系人关联了账号<span style="color:red;">'+accountNum+'</span>，若要删除，请先修改该账号关联的财务联系人'
				return {success:success,message:message};
			}
		});
		return {success:success,message:message};
	},
	delContactRealTimeVerifyResult:function(selected){
		var me = this;
		var contactIdCardArray = [];//RealTimeVerifyResult.message[1]//身份证号
		var contactMobileArray = [];//RealTimeVerifyResult.message[2]//手机号码
		var idCardArray = RealTimeVerifyResult.message[1];
		var mobileArray = RealTimeVerifyResult.message[2];
		var telAndNameArray = RealTimeVerifyResult.message[7];
		var cardNum = RealTimeVerifyResult.message[10];
		for(var i = 0; i < idCardArray.length;i++){
			if(selected.get('id') != idCardArray[i].contactId){
				contactIdCardArray.push(idCardArray[i]);
			}
		}
		for(var j = 0; j < mobileArray.length;j++){
			if(selected.get('id') != mobileArray[j].contactId){
				contactMobileArray.push(idCardArray[j]);
			}
		}
		for (var k = 0; k < telAndNameArray.length; k++){
			if(selected.get('id') == telAndNameArray[k].contactId){
				telAndNameArray[k].message = '';
				telAndNameArray[k].success = true;
			}
		}
		for (var m = 0; m < cardNum.length; m++){
			if(selected.get('id') == cardNum[m].contactId){
				cardNum[m].message = '';
				cardNum[m].success = true;
			}
		}
		RealTimeVerifyResult.message[1] = contactIdCardArray;
		RealTimeVerifyResult.message[2] = contactMobileArray;
		//清除 客户类型为"个人"的主联系人身份证号 校验信息
		if(selected.get('isMainLinkMan') && 'PERSONAL' == me.parent.memberCustBasicInfo.getForm().findField('custType').getValue() ){
			RealTimeVerifyResult.success[5] = true;
			RealTimeVerifyResult.message[5] = '';
		}
	},
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
	},
	//搜集联系人的信息用于会员的提交,对应后台类型为List<Contact>
	collectLinkmanForMemberSubmit:function(){
		var me = this;
		var contacts = new Array();
		//转换联系人类型
		MemberCustEditView.changeLinkmanTypeForSubmit(contacts,me.memberData.getContactStore().getRange());
		return contacts;
	},
	//搜集联系人的信息用于会员的提交,对应后台类型为List<Contact>
	collectLinkman4MemberSubmit:function(){
		var me = this;
		var contacts = new Array();
		//转换联系人类型
		MemberCustEditView.changeLinkmanTypeForSubmit(contacts,me.memberData.getContactStore().data.items);
		return contacts;
	},
	//校验联系人信息
	validateLinkman:function(){
		var me = this;
		var success = true;
		var message = '';
		//至少有一条联系人信息
		var contactStore = me.memberData.getContactStore();
		if(contactStore.getCount() == 0){
			success = false;
			message = i18n('i18n.MemberCustEditView.m_mustContactNotNull');
		}else{
			var records = contactStore.getRange();
			for(var i = 0; i < records.length && success; i++){
				if(!records[i].isValid()){
					success = false;
					message = i18n('i18n.MemberCustEditView.contactInformationToFillInIncomplete');
					return {'success':success,'message':message};
				}
			}
		}
		return {'success':success,'message':message};
	},
	//搜集所有联系人修改的信息
	collectAllLinkmanAlterData:function(alterMemberList,alterDeleteList,alterAddLinkmanList,alterAddAddressHobbyList){
		var me = this;
		var linkmanStore = me.memberData.getContactStore();
		//搜集联系人主信息修改数据以及偏好地址删除数据
		for(var i = 0; i < linkmanStore.getCount(); i++){
			var eachLinkAlterData = linkmanStore.getAt(i).get('alterLinkmanData');
			if(eachLinkAlterData != null && eachLinkAlterData.length > 0){
				for(var j = 0; j < eachLinkAlterData.length; j++){
					var linkAlterData = eachLinkAlterData[j];
					if('linkmanType' == linkAlterData.fieldName && 'Contact' == linkAlterData.className && !Ext.isEmpty(linkAlterData.newValue)){
					    //转换联系人类型为 0或1，后台数据审批 对接
						linkAlterData.newValue = linkAlterData.newValue.replaceAll('true','1').replaceAll('false','0')
						alterMemberList.push(linkAlterData);
					}else{
						alterMemberList.push(linkAlterData);
					}
				}
			}
			
			var eachDelteAlterData = linkmanStore.getAt(i).get('delAddressHobbyData');
			if(eachDelteAlterData != null && eachDelteAlterData.length > 0){
				for(var j = 0; j < eachDelteAlterData.length; j++){
					alterDeleteList.push(eachDelteAlterData[j].data);
				}
			}
			
			var eachAddAlterData = linkmanStore.getAt(i).get('addAddressHobbyData');
			
			//联系人id
			var linkManId = linkmanStore.getAt(i).get('id');
			
			if(eachAddAlterData != null && eachAddAlterData.length > 0){
				for(var j = 0; j < eachAddAlterData.length; j++){
					//设置新增的偏好地址关联的联系人id
					eachAddAlterData[j].linkManId =linkManId ;
					alterAddAddressHobbyList.push(eachAddAlterData[j]);
				}
			}
		}
		//搜集联系人修改时删除的数据
		var deleteLinkmanRecords = me.memberData.getContactStore().getRemovedRecords();
		for(var i = 0; i < deleteLinkmanRecords.length; i++){
			alterDeleteList.push(me.getEmptyMemberLinkBasicApproveData(deleteLinkmanRecords[i]).data);
		}
		
		//搜集新增的联系人信息
		var addLinkmanRecords = me.memberData.getContactStore().getNewRecords();
		//转换联系人类型
		MemberCustEditView.changeLinkmanTypeForSubmit(alterAddLinkmanList,addLinkmanRecords);
	},
	//获取空的联系人修改的approveData
	getEmptyMemberLinkBasicApproveData:function(deleteContactRecord){
		var me = this;
		var basicApproveData = Ext.create('ApproveDataModel');
		basicApproveData.set('className','Contact');
		basicApproveData.set('classId',deleteContactRecord.get('id'));
		return basicApproveData;
	}
});

/*===================================================================
	 * 描述：新增信用信息页签
	 * 日期：2014-04-28
	 * 作者：邹明
	 * ==================================================================*/
	/*===============================================================================
	 * 	信用信息页签formPanel
	 ================================================================================*/
	//客户信用MODEL
	Ext.define('Crm.customer.CustCreditModel',{
		extend:'Ext.data.Model',
		fields:[{
			name:'overdraftCreditAmount'	//临欠信用额度
		},{
			name:'overdrftRecivableAmount'	//临欠当期应收金额
		},{
			name:'overdraftCreditBalanceAmount'	//临欠信用额度余额
		},{
			name:'monthCreditAmount'	//月结信用额度
		},{
			name:'lttRecivableAmount'	//月结当期应收余额
		},{
			name:'monthCreditBalanceAmount'	//月结信用额度余额
		},{
			name:'totalReceivableAmount'	//应收总额
		},{
			name:'creditTimes'	//信用预警次数
		},{
			name:'lastWarnTime'	//最后一次预警时间
		},{
			name:'isPoorCredit'	//是否信用较差客户
		}]
	});
	
	Ext.define('Crm.customer.CreditInformationForm',{
		extend:'TitleFormPanel',
		items:null,
		initComponent:function(){
			var me = this;
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			return [{
				xtype:'basicfiledset',
				title:i18n('i18n.customer.creditInfo'),//'信用信息',
				layout:{
					type:'table',
					columns:3
				},
				defaults:{
					readOnly : true,
					cls : "readonly",
					labelWidth : 120,
					width : 210
				},
				defaultType:'textfield',
				items:[{
					fieldLabel:i18n('i18n.customer.overdraftCreditAmount')//'临欠信用额度'
					,name:'overdraftCreditAmount'
				},{
					fieldLabel:i18n('i18n.customer.overdrftRecivableAmount')//'临欠当期应收金额'
					,name:'overdrftRecivableAmount'
				},{
					fieldLabel:i18n('i18n.customer.overdraftCreditBalanceAmount')//'临欠信用额度余额'
					,name:'overdraftCreditBalanceAmount'
				},{
					fieldLabel:i18n('i18n.customer.monthCreditAmount')//'月结信用额度'
					,name:'monthCreditAmount'
				},{
					fieldLabel:i18n('i18n.customer.lttRecivableAmount')//'月结当期应收余额'
					,name:'lttRecivableAmount'
				},{
					fieldLabel:i18n('i18n.customer.monthCreditBalanceAmount')//'月结信用额度余额'
					,name:'monthCreditBalanceAmount'
				},{
					fieldLabel:i18n('i18n.customer.totalReceivableAmount')//'应收总额'
					,name:'totalReceivableAmount'
				},{
					fieldLabel:i18n('i18n.customer.creditTimes')//'信用预警次数'
					,name:'creditTimes'
				},{
					fieldLabel:i18n('i18n.customer.lastWarnTime')//'最后一次预警时间'
					,name:'lastWarnTime'
				},{
					fieldLabel:i18n('i18n.customer.isPoorCredit')//'是否信用较差客户'
					,name:'isPoorCredit'
				}]
			}]
		}
	});
	
	Ext.define('Crm.customer.CreditInformation',{
		extend : "TabContentPanel",
		title : i18n('i18n.customer.creditInfo'),//'信用信息',
		height : 500,
		autoScroll : true,
		items:null,
		viewStatus:null,
		custId:null,
		CreditInformationForm:null,
		getCreditInformationForm:function(CreditInformationForm){
			return this.CreditInformationForm;
		},
		listeners : {
			activate : function(l, m) {
				if(Ext.isEmpty(l.custId)){
					return;
				}
				var param = {
					custId : l.custId
				};
				var successFn = function(json) {
					if(Ext.isEmpty(json)){
						return;
					}
					if(Ext.isEmpty(json.custCredit)){
						return;
					}
					if(json.custCredit.isPoorCredit=='1'){
						json.custCredit.isPoorCredit=i18n('i18n.ChangeContactAffiliatedRelationView.yes');
					}else if(json.custCredit.isPoorCredit=='0'){
						json.custCredit.isPoorCredit=i18n('i18n.ChangeContactAffiliatedRelationView.no');
					}
					var custCreditModel = new Crm.customer.CustCreditModel(json.custCredit);
					custCreditModel.set('lastWarnTime',(
						Ext.isEmpty(custCreditModel.get('lastWarnTime')))?null:Ext.Date.format(new Date(custCreditModel.get('lastWarnTime')),'Y-m-d H:i:s'));
					l.getCreditInformationForm().getForm().loadRecord(custCreditModel);
				};
				var failureFn = function(json) {
					if(!Ext.isEmpty(json)){
						MessageUtil.showErrorMes(json.message)
					}
				};
				memberCustControl.searchCustCredit(param, successFn, failureFn);
			}
		},
		initComponent : function() {
			this.CreditInformationForm = new Crm.customer.CreditInformationForm();
			this.items = this.getItems(this.CreditInformationForm);
			this.callParent();
			if(this.viewStatus!='view'){
				this.hide();
			}
		},
		getItems : function(item) {
			return [item]
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
	channelType:null,
	parentWindow:null,
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
		
		//加载账号信息
//		if(Ext.isEmpty(me.channelType) || me.channelType != 'normal'){
		me.loadMemberAccountData();
//		}
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
						    id:'memberAccountTab_add_id',
						    handler:me.showCreateAccountWin},
					       {xtype:'button',text:i18n('i18n.PotentialCustManagerView.update'),
							disabled:(('view' == me.viewStatus)||
									(!isPermission('/customer/accountRecognizedUBtn.action')
									&&('update' == me.viewStatus))||
									(!isPermission('/customer/accountRecognizedDBtn.action')
											&&('new' == me.viewStatus))
									)?true:false,
						    scope:me,
						    id:'memberAccountTab_update_id',
						    handler:me.showUpdateAccountWin},
					       {xtype:'button',text:i18n('i18n.PotentialCustManagerView.delete'),
							disabled:(('view' == me.viewStatus)||
									(!isPermission('/customer/accountRecognizedDBtn.action')
									&&('update' == me.viewStatus))||
									(!isPermission('/customer/accountRecognizedDBtn.action')
											&&('new' == me.viewStatus))
									)?true:false,
						    scope:me,
						    id:'memberAccountTab_del_id',
						    handler:me.deleteAccount},
						    {xtype:'button',text:i18n('i18n.ScatterCustManagerView.infoScatterAccount'),
						    	hidden:true,
								disabled:(('view' == me.viewStatus)||
										(!isPermission('/customer/accountRecognizedDBtn.action')
										&&('update' == me.viewStatus))||
										(!isPermission('/customer/accountRecognizedDBtn.action')
												&&('new' == me.viewStatus))
										)?true:false,
							    scope:me,
							    handler:me.importAccount
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
	//新增账号信息
	showCreateAccountWin:function(){
		var me = this;
		var newAccountRecord = Ext.create('AccountModel');
		newAccountRecord.set('id',MemberCustEditView.getSequenceAddress());
		me.getAccountWin(me.memberData,newAccountRecord,'new');
//		Ext.create('MemberAccountEditWin',{'memberData':me.memberData,
//										   'accountRecord':newAccountRecord,
//										   'viewStatus':'new'}).show();
	},
	//修改账号信息
	showUpdateAccountWin:function(){
		var me = this;
		var grid = me.down('grid');
		var selectModel = grid.getSelectionModel();
		var selections = selectModel.getSelection();
		if(selections.length != 1){
			MessageUtil.showMessage(i18n('i18n.MemberCustEditView.pleaseOneRecordToUpdate'));
		}else{
			var selected = selections[0];
			me.getAccountWin(me.memberData,selected,'update');
//			Ext.create('MemberAccountEditWin',{'memberData':me.memberData,
//				   							   'accountRecord':selected,
//											   'viewStatus':'update'}).show();
		}
	},
	//获得账号窗体
	getAccountWin:function(memberData,record,viewStatus){
		Ext.create('MemberAccountEditWin',{'memberData':memberData,
			   							   'accountRecord':record,
										   'viewStatus':viewStatus}).show();
	},
	//删除账号信息
	deleteAccount:function(){
		var me = this;
		var grid = me.down('grid');
		var selectModel = grid.getSelectionModel();
		var selections = selectModel.getSelection();
		if(selections.length != 1){
			MessageUtil.showMessage(i18n('i18n.MemberCustEditView.pleaseOneRecordToDelete'));
		}else{
			var selected = selections[0];
			  MessageUtil.showQuestionMes(i18n('i18n.PotentialCustManagerView.confirm_message'),function(btn){
			        	   if(btn == 'yes'){
			        	   me.memberData.getAccountStore().remove(selected);
		        	   }
		       });
		}
	},
	//导入散客账号
	importAccount:function(){
		var me = this;
		if(!Ext.isEmpty(me.memberAccountData)){
			var scatterCustAccountWin = Ext.create('ScatterCustAccountWin',{'scatterAccountData':me.memberAccountData,
																			'memberCustEditWindow':me.parentWindow});
			scatterCustAccountWin.show();			
		}else{
			MessageUtil.showErrorMes(i18n('i18n.ScatterUpgradeView.noAvaliableAccount'));
			return;
		}
    },
	//搜集账号信息用于会员信息的提交,返回的类型对应后台的List<Account>
	collectMemberAccountsForSubmit:function(){
		var me = this;
		var accountArray = new Array();
		me.memberData.getAccountStore().each(function(record){
			accountArray.push(record.data);
		});
		return accountArray;
	},
	//校验账号信息
	validateAccount:function(){
		var me = this;
		var success = true;
		var message = '';
		var accountStore = me.memberData.getAccountStore();
		if(accountStore.getCount() != 0){
			var records = accountStore.getRange();
			for(var i = 0; i < records.length && success; i++){
				if(!records[i].isValid()){
					success = false;
					message = i18n('i18n.MemberCustEditView.accountInformationFillIncompleteWar');
					return {'success':success,'message':message};
				}
			}
		}
		return {'success':success,'message':message};
	},
	//搜集账号所有修改的信息
	collectAllAcountAlterData:function(alterContactList,alterDeleteList,alterAddAccountList){
		var me = this;
		//所有账号修改的record
		var updatedAccountRecords = me.memberData.getAccountStore().getUpdatedRecords();
		
		if(updatedAccountRecords.length > 0){
			var fieldsArray = updatedAccountRecords[0].fields.items;
			for(var j = 0; j < updatedAccountRecords.length; j++){
				for(var i = 0; i < fieldsArray.length;i++){
					//字段名
					var fieldName = fieldsArray[i].name;
					
					if(updatedAccountRecords[j].isModified(fieldName)){
						var basicApproveData = me.getEmptyAccountApproveData(updatedAccountRecords[j]);
						basicApproveData.set('fieldName',fieldName);
						basicApproveData.set('newValue',updatedAccountRecords[j].get(fieldName));
						alterContactList.push(basicApproveData.data);
					}
				}
			}
		}
		
		//搜集账号修改时删除的数据
		var deleteAccountRecords = me.memberData.getAccountStore().getRemovedRecords();
		for(var i = 0; i < deleteAccountRecords.length; i++){
			alterDeleteList.push(me.getEmptyAccountApproveData(deleteAccountRecords[i]).data);
		}
		
		//搜集新增的账号数据
		var addAccountRecords = me.memberData.getAccountStore().getNewRecords();
		for(var i = 0; i < addAccountRecords.length; i++){
			alterAddAccountList.push(addAccountRecords[i].data);
		}
	},
	//获取空的账号修改的approveData
	getEmptyAccountApproveData:function(accountRecord){
		var me = this;
		var basicApproveData = Ext.create('ApproveDataModel');
		basicApproveData.set('className','Account');
		basicApproveData.set('classId',accountRecord.get('id'));
		return basicApproveData;
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
							disabled:('view' == me.viewStatus)?true:false,
						    scope:me,
						    handler:me.showCreateMemberAddr},
					       {xtype:'button',text:i18n('i18n.PotentialCustManagerView.update'),
							disabled:('view' == me.viewStatus)?true:false,
						    scope:me,
						    handler:me.showUpdateMemberAddr},
					       {xtype:'button',text:i18n('i18n.PotentialCustManagerView.delete'),
							disabled:('view' == me.viewStatus)?true:false,
						    scope:me,
						    handler:me.deleteMemberAddress}]
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
	//校验接送货地址
	validateAddress:function(){
		var me = this;
		var success = true;
		var message = '';
		var addressStore = me.memberData.getShuttleAddressStore();
		if(addressStore.getCount() != 0){
			var records = addressStore.getRange();
			for(var i = 0; i < records.length && success; i++){
				if(!records[i].isValid()){
					success = false;
					message = i18n('i18n.MemberCustEditView.accessShippingAddressIncomplete');
					return {'success':success,'message':message};
				}
			}
		}
		return {'success':success,'message':message};
	},
	//新增接送货地址
	showCreateMemberAddr:function(){
		var me = this;
		var addressModel = Ext.create('ShuttleAddressModel');
		addressModel.set('id',MemberCustEditView.getSequenceAccount());
		me.getAddressWin(me.memberData,addressModel,'new').show();
//		Ext.create('MemberAddressEditwin',{'memberData':me.memberData,
//										   'shuttleAddressRecord':addressModel,
//										   'viewStatus':'new'}).show();
	},
	//修改接送货地址
	showUpdateMemberAddr:function(){
		var me = this;
		var grid = me.down('grid');
		var selectModel = grid.getSelectionModel();
		var selections = selectModel.getSelection();
		if(selections.length != 1){
			MessageUtil.showMessage(i18n('i18n.MemberCustEditView.pleaseOneRecordToUpdate'));
		}else{
			var selected = selections[0];
			var win = me.getAddressWin(me.memberData,selected,'update');
//			Ext.create('MemberAddressEditwin',{'memberData':me.memberData,
//				   							   'shuttleAddressRecord':selected,
//											   'viewStatus':'update'});
			win.show()
			win.down('form').getForm().findField('address').setValue(selected.get('address'));
		}
	},
	//获得接送货地址窗体
	getAddressWin:function(memberData,record,viewStatus){
		var win = Ext.create('MemberAddressEditwin',{'memberData':memberData,
			   							   'shuttleAddressRecord':record,
										   'viewStatus':viewStatus});
		return win;								   
	},
	//删除接送货地址
	deleteMemberAddress:function(){
		var me = this;
		var grid = me.down('grid');
		var selectModel = grid.getSelectionModel();
		var selections = selectModel.getSelection();
		if(selections.length != 1){
			MessageUtil.showMessage(i18n('i18n.MemberCustEditView.pleaseOneRecordToDelete'));
		}else{
			var selected = selections[0];
			MessageUtil.showQuestionMes(i18n('i18n.PotentialCustManagerView.confirm_message'),function(btn){
			        	   if(btn == 'yes'){
			        	   	var delIsPreferenceAddressRs = me.delIsPreferenceAddress(selected)
			        	   	if(!delIsPreferenceAddressRs.success){
			        	   		MessageUtil.showMessage(delIsPreferenceAddressRs.message);
			        	   		return;
			        	   	}
			        	   me.memberData.getShuttleAddressStore().remove(selected);
			        	   
			        	//接送货地址有对应偏好设置，则对应的偏好设置必须删除
//			   			var contactStore = me.memberData.getContactStore();
//			   			var address = selected.get('address');
//			   			var addressType = selected.get('addressType');
//			   			for(var i = 0; i < contactStore.getCount(); i++){
//			   				var addressHobbyArray = contactStore.getAt(i).get('preferenceAddressList');
//			   				if(!DpUtil.isEmpty(addressHobbyArray)){
//			   					for(var j = 0; j < addressHobbyArray.length; j++){
//			   						if(addressHobbyArray[i].address == address &&
//			   						   addressHobbyArray[i].addressType == addressType){
//			   							addressHobbyArray.pop(addressHobbyArray[i]);
//			   						}
//					   			}
//			   					contactStore.getAt(i).set('preferenceAddressList',addressHobbyArray);
//			   				}
//			   			}
			   			
		        	   }
		     });
		}
	},
	//删除该联系人关联的账号 财务联系人信息
	delIsPreferenceAddress:function(shuttleAddressRecord){
		var me = this;
		var success = true;
		var message = '';
		var contactStore = me.memberData.getContactStore();
		var address = shuttleAddressRecord.get('address');
		var addressType = shuttleAddressRecord.get('addressType');
		for(var i = 0; i < contactStore.getCount(); i++){
			var addressHobbyArray = contactStore.getAt(i).get('preferenceAddressList');
			if(!DpUtil.isEmpty(addressHobbyArray)){
				for(var j = 0; j < addressHobbyArray.length; j++){
//					if(addressHobbyArray[i].address == address &&
//					   addressHobbyArray[i].addressType == addressType){
					if(addressHobbyArray[j].shuttleAddressId == shuttleAddressRecord.get('id')){
						success = false;
//						message = i18n('i18n.MemberCustEditView.addressLinkContact',contactStore.getAt(i).get('name'));
						message = '该接送货地址关联了联系人<span style="color:red;">'+contactStore.getAt(i).get('name')+'</span>的偏好地址，若要删除，请先修改联系人中的偏好地址'
						return {success:success,message:message};
					}
	   			}
//				contactStore.getAt(i).set('preferenceAddressList',addressHobbyArray);
			}
		}
		return {success:success,message:message};
	},
	//搜集接送货地址用于会员提交，对应于后台类型为List<ShuttleAddress>
	collectShuttleAddressForSubmit:function(){
		var me = this;
		var shuttleAddress = new Array();
		me.memberData.getShuttleAddressStore().each(function(record){
			shuttleAddress.push(record.data);
		});
		return shuttleAddress;
	},
	//搜集接送货地址修改的approvedata
	collectAllAddressAlterData:function(alterContactList,alterDeleteList,alterAddAddressList){
		var me = this;
		//所有账号修改的record
		var updatedAddressRecords = me.memberData.getShuttleAddressStore().getUpdatedRecords();
		
		if(updatedAddressRecords.length > 0){
			var fieldsArray = updatedAddressRecords[0].fields.items;
			for(var j = 0; j < updatedAddressRecords.length; j++){
				for(var i = 0; i < fieldsArray.length;i++){
					//字段名
					var fieldName = fieldsArray[i].name;
					
					if(updatedAddressRecords[j].isModified(fieldName)){
						var basicApproveData = me.getEmptyAddressApproveData(updatedAddressRecords[j]);
						basicApproveData.set('fieldName',fieldName);
						basicApproveData.set('newValue',updatedAddressRecords[j].get(fieldName));
						alterContactList.push(basicApproveData.data);
					}
				}
			}
		}
		
		//搜集接送货地址修改时删除的数据
		var deleteAddressRecords = me.memberData.getShuttleAddressStore().getRemovedRecords();
		for(var i = 0; i < deleteAddressRecords.length; i++){
			alterDeleteList.push(me.getEmptyAddressApproveData(deleteAddressRecords[i]).data);
		}
		
		//搜集新增的接送货地址
		var addAddressRecords = me.memberData.getShuttleAddressStore().getNewRecords();
		for(var i = 0; i < addAddressRecords.length; i++){
			alterAddAddressList.push(addAddressRecords[i].data);
		}
	},
	//获取空的接送货地址修改的approveData
	getEmptyAddressApproveData:function(addressRecord){
		var me = this;
		var basicApproveData = Ext.create('ApproveDataModel');
		basicApproveData.set('className','ShuttleAddress');
		basicApproveData.set('classId',addressRecord.get('id'));
		return basicApproveData;
	}
});
/**
 * 固定客户信息tab
 */
Ext.define('memberCustTabPanel',{
	extend:'TabContentPanel',
	title:'修改日志',//:————i18n('i18n.IntegralRuleEdit.custInfo'),	//i18n.IntegralRuleEdit.custInfo固定客户信息
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
 	        	header	: i18n('i18n.RecordExchangeRuleManagerView.modiflyType'),	//操作类型
 	            dataIndex: 'operateType',
 	            flex: 1
 	            ,renderer:function(value){
			        	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.OPERATE_TYPE);
			         }
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
 			        	//'storeId':'CustInfoStoreId',
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
/*    getBBar:function(){
    	var me = this;
    	return Ext.create('Ext.toolbar.Paging', {
			id : 'pagingToolbar',
			store : me.store,
			displayMsg : i18n('i18n.order.displayMsg'),
			displayInfo : true,
		});
    },*/
    initComponent:function(){
		var me = this;
//		me.bbar = me.getBBar();
		me.selModel = me.getSelModel();
		// 增加store的beforeload方法
/*		Ext.data.StoreManager.lookup(me.storeId).on('beforeload',function(NoAllocationOrderStore,operation,e){
			var searchParams=null;
			if(IsSearch){
				top.Condition = null;
			}
  			Ext.apply(operation,{
//				params : searchParams
			});
  		});*/
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
			MessageUtil.showErrorMes(json.message);
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
//		me.store = me.parentData.getExamineRecordStore();
		//:store
		this.callParent();
	},
	getColumns:function(){
		var me = this;
		return [new Ext.grid.RowNumberer(),
//		        {
//				flex:1,
//				header : i18n('i18n.MyWorkFlowDealEditView.fieldName'),				//字段名
//				dataIndex : 'fieldName'
//			},{
//				flex:1,
//				header : i18n('i18n.MyWorkFlowDealEditView.beforeUpdate'),			//修改前
//				dataIndex : 'oldValue'
//			},{
//				flex:1,
//				header : i18n('i18n.MyWorkFlowDealEditView.afterUpdate'),			//修改后
//				dataIndex : 'newValue'
//			}
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
/*		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.PotentialCustEditView.save'),
			id:'save_id',
			scope:me,
			handler:me.saveMemberCust
		},{
			xtype:'button',
			text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
			id:'cancel_id',
			handler:function(){
				if(me.parentWindow != null){
					me.parentWindow.close();
				}
			}
		}];*/
	
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
	height:520,
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
			//证件类型默认为 二代身份证
			//me.linkmanBasicInfo.getForm().findField('cardTypeCon').setValue('SECOND_GENERATION_IDCARD');
		}
		me.initBusinessComponent();
		//如果是查看状态锁定所有控件
		if(me.viewStatus == 'view'){
			me.lockAllComponents();
		}
		me.on('beforeClose',function(){
			//当前联系人的 联系id
			var contactId = me.contactRecord.get('id');
			var custGroup = Ext.getCmp('MemberEdit_custGroup_id').getValue();
			//联系人 编辑界面点击取消按钮 如果是 "个人"客户"主联系人"的"身份证号"为空则身份证校验结果为 提示不可为空
			//if((Ext.isEmpty(me.contactRecord.get('idCard'))||Ext.isEmpty(me.contactRecord.get('cardTypeCon'))) && me.contactRecord.get('isMainLinkMan') && 'PERSONAL' == Ext.getCmp('custType_id').getValue()){
			if(Ext.isEmpty(me.contactRecord.get('idCard')) && me.contactRecord.get('isMainLinkMan') && 'PERSONAL' == Ext.getCmp('custType_id').getValue()
					&&'RC_CUSTOMER'==custGroup){
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
		
		//如果为潜散客，性别不必填，固定客户必填
		var custGroup = Ext.getCmp('MemberEdit_custGroup_id').getValue();
		if('RC_CUSTOMER'==custGroup){
			linkmanBasicForm.findField('sex').allowBlank = false;
		}else{
			linkmanBasicForm.findField('sexFlag').setValue();
			linkmanBasicForm.findField('sex').allowBlank = true;
		}
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.MemberCustEditView.ensure'),
			disabled:('view' == me.viewStatus)?true:false,
			scope:me,
			handler:me.commitMemberLinkman
		},{
			xtype:'button',
			text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
			handler:function(){
				me.close();
			}
		}];
	},
	//提交联系人信息
	commitMemberLinkman:function(){
		var me = this;
//		var idCardValue = me.linkmanBasicInfo.getForm().findField('idCard');
//		if(Ext.isEmpty(idCardValue.getValue()) || !idCardValue.isValid()){
//			Ext.Msg.alert('温馨提醒',i18n('i18n.MemberCustEditView.m_mustContact'));
//			return;
//		}
		//提交校验联系人基本信息
		var validateLinkmanRs = me.validateLinkman();
		if(!validateLinkmanRs.success){
			if(!DpUtil.isEmpty(validateLinkmanRs.message)){
				MessageUtil.showMessage(validateLinkmanRs.message);
				return;
			}
		}
		//校验联系人编码是否唯一
		var validateLinkManNumberCanUseRs = me.validateLinkManNumberCanUse(Ext.getCmp('contactNum_id').getValue());
		if(!validateLinkManNumberCanUseRs.success){
			MessageUtil.showMessage(validateLinkManNumberCanUseRs.message);
			return;
		}
		
		var cardTypeCon_con = Ext.getCmp('cardTypeCon_id').getValue();
		var idCardNum_con = Ext.getCmp('contactIdCard').getValue();
		var cardTypeCon_isNull = !Ext.isEmpty(idCardNum_con)&&Ext.isEmpty(cardTypeCon_con);
		var idCardNum_isNull = Ext.isEmpty(idCardNum_con)&&!Ext.isEmpty(cardTypeCon_con);
		//校验证件号码合法性
		if(cardTypeCon_isNull || idCardNum_isNull){
			MessageUtil.showMessage('证件类型和证件号码或者都填写或者都不填写！');
			return;
		}else{
			//校验证件号码合法性
			MemberCustEditView.validateIdNumberIsLegal(me.contactRecord);
			if(Ext.isEmpty(cardTypeCon_con) && Ext.isEmpty(idCardNum_con)){
				RealTimeVerifyResult.success[9] = true;
				RealTimeVerifyResult.message[9] = '';
			}else if(!RealTimeVerifyResult.success[9]){
				MessageUtil.showMessage(RealTimeVerifyResult.message[9]);
				return;
			}
		}
		var isMainLinkMan = me.linkmanBasicInfo.getForm().findField('isMainLinkMan').getValue();//是否主联系人
		//潜散客证件号不必填
		var custGroup = Ext.getCmp('MemberEdit_custGroup_id').getValue();
		if(isMainLinkMan && Ext.isEmpty(idCardNum_con)&& 'PERSONAL' == Ext.getCmp('custType_id').getValue()
				&&'RC_CUSTOMER'==custGroup){
			RealTimeVerifyResult.success[5] = false;
			RealTimeVerifyResult.message[5] = i18n('i18n.MemberCustEditView.m_mustContact');
			MessageUtil.showMessage(RealTimeVerifyResult.message[5]);
			return;
		}
		//校验个人客户的主联系人身份证号是否重复
		if((isMainLinkMan && !RealTimeVerifyResult.success[5])
		||(!RealTimeVerifyResult.success[5])){
			MessageUtil.showMessage(RealTimeVerifyResult.message[5]);
			return;
		}
		//校验手机号是否重复
		if(!RealTimeVerifyResult.success[8]){
			MessageUtil.showMessage(RealTimeVerifyResult.message[8]);
			return;
		}
		//校验固号加联系人姓名是否重复
		if(!RealTimeVerifyResult.success[6]){
			MessageUtil.showMessage(RealTimeVerifyResult.message[6]);
			return;
		}
		
		//提交校验联系人基本信息
		var validateLinkmanRs = me.validateLinkman();
		//判断 身份证号是否被修改了，被修改才做联系人生日修改
//		me.linkmanBasicInfo.getForm().updateRecord(me.contactRecord);
		if(Ext.getCmp('contactIdCard').getValue() != me.contactRecord.get('idCard')){
			//实时更新联系人生日
			MemberCustEditView.changeBirthDay();
		}
		var titleDescrip = (me.viewStatus == 'new')? i18n('i18n.PotentialCustManagerView.add'):i18n('i18n.PotentialCustManagerView.update');
	    MessageUtil.showQuestionMes(i18n('i18n.MemberCustEditView.sureYouWantTo')+ titleDescrip +'这条联系人信息?',function(btn){
		        	   if(btn == 'yes'){
	        			//更新联系人基本数据
		       			me.linkmanBasicInfo.getForm().updateRecord(me.contactRecord);
		       			//更新联系人地址偏好数据
		       			me.contactRecord.set('preferenceAddressList',me.linkmanAddressHobbyInfo.collectLinkmanAddressHobbyForSubmit());
		       			
		       			if(me.viewStatus == 'new'){
		       				me.memberData.getContactStore().insert(0,me.contactRecord);
		       			}else if(me.viewStatus == 'update'){
		       				//phantom为false表示修改该联系人
		       				if(!me.contactRecord.phantom){
		       					var alterContactList = new Array();
			       				var alterDeleteList = new Array();
			       				var alterAddList = new Array();
			       				//搜集联系人基本数据修改approvedata
			       				me.linkmanBasicInfo.collectLinkmanBasicAlterData(alterContactList);
			       				//搜集联系人偏好地址数据修改的approvedata
			       				me.linkmanAddressHobbyInfo.collectLinkmanAddrHobbyAlterData(alterContactList,alterDeleteList,alterAddList);
			       				me.contactRecord.set('alterLinkmanData',alterContactList);
			       				me.contactRecord.set('delAddressHobbyData',alterDeleteList);
			       				me.contactRecord.set('addAddressHobbyData',alterAddList);
		       				}
		       				//修改该联系人关联的账号 财务联系人信息
		       				me.updateAccountLinkman(me.contactRecord);
		       			}
		       			me.close();
	        	   }
	       });
	},
	//修改该联系人关联的账号 财务联系人信息
	updateAccountLinkman:function(contactRecord){
		var me = this;
		me.memberData.getAccountStore().each(function(record){
			if(contactRecord.get('id')==record.get('financeLinkmanId')){
				record.set('linkManMobile',contactRecord.get('mobilePhone'));
				record.set('linkManPhone',contactRecord.get('telPhone'));
				record.set('financeLinkman',contactRecord.get('name'));
//				record.set('financeLinkmanId',contactRecord.get('id'));
			}
		});
	},
	//校验联系人编码 是否有重复
	validateLinkManNumberCanUse:function(linkManNumber){
		var me = this;
		var success = true;
		var message= '';
		var validateSuccessFn = function(result){
			if(!result.exist){
				success = false;
				message = i18n('i18n.MemberCustEditView.haveSameContactNumNotAddContactPleaseUpdateContactNum');
				return {success:false,message:message};
			}
		};
		var validateFailFn = function(result){
			if(DpUtil.isEmpty(result.message)){
				result.message = i18n('i18n.MemberCustEditView.anExceptionBackgroundAppearsWar');
			}
//			MessageUtil.showMessage(result.message);
			success = false;
			message = result.message;
			return {success:false,message:result.message};
		};
		var params = {};
		params.linkManNumber = linkManNumber;
		if (me.contactRecord.get('number') != linkManNumber){
			me.memberData.validateLinkManNumberCanUse(params,validateSuccessFn,validateFailFn);
		}
		return {success:success,message:message};
	},
	//校验联系人信息
	validateLinkman:function(){
		var me = this;
		var success = true;
		var message = '';
		//校验联系人基本信息
		var validateLinkmanBasicRs = me.linkmanBasicInfo.validateLinkmanInfo();
		if(!validateLinkmanBasicRs.success){
			success = false;
			message = validateLinkmanBasicRs.message;
		}else{
			//校验联系人偏好地址信息
			var validateAddressRs = me.linkmanAddressHobbyInfo.validateLinkmanAddressHobby();
			if(!validateAddressRs.success){
				success = false;
				message = validateAddressRs.message;
			}
		}
		return {'success':success,'message':message};
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
		
		me.defaults.listeners = {
				scope:me,
				keypress:me.enterKeyEvent
			};
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
			regex : /^[a-z]{2,80}[0-9]{7,40}$/,
    	    blankText:'请输入联系人编码，规则为姓名首字母+联系方式(优先选手机号码)',
    	    regexText:'联系人编码由联系人姓名首字母(小写)+手机号码/固定电话组成',
    	    minLength:9,
    	    minLengthText:i18n('i18n.MemberCustEditView.contactNumMustSmall10'),
			maxLength:120,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',120),
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
    		minLength:2,
    		maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_minLength',2),
			maxLength:80,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80),
	    	blankText:i18n('i18n.MemberCustEditView.pleaseInputContactName'),
    	    name:'name',
    	    id:'contactName_id',
    	    listeners:{
				scope:me,
				blur:me.blurContactName
			}},
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
			    	scope:me,
			    	select:me.selectcardTypeCon,
			    	change:function(field, newValue) {if (Ext.isEmpty(newValue)) {field.setValue(null);}}
			    }
			},
           {xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12},
           {fieldLabel:i18n('i18n.ScatterCustManagerView.idNumber'),
    	    name:'idCard',
    	    id:'contactIdCard',
//    	    vtype:'alphanum',
			maxLength:50,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',50),
//			minLength:15,
//			regex : /^([\d]{15}|[\d]{18}|[\d]{17}X)$/,
//			regexText:i18n('i18n.MemberCustEditView.cardNum15118LastxX'),
		    blankText:i18n('i18n.MemberCustEditView.idCardAlert'),
			listeners:{
				scope:me,
				blur:me.validateIdNumberIsExsist
			}},
			/**
			 * 许华龙 修改 文本提示优化
			 */
			{xtype:'displayfield',html:'<span data-qtip="当客户类型为个人时，主联系人证件号码必填，其他情况证件号码不是必填项"><img src="../images/customer/tip.png"></span>',width:14	
	        },
//          {fieldLabel:i18n('i18n.MemberCustEditView.isMainContact'),
//        	name:'isMainLinkMan',
//        	allowBlank:false,
//        	xtype:'dpcombobox',
//        	queryMode:'local',
//            forceSelection:true,
//			displayField:'name',
//			valueField:'value',
//			store:me.memberData.getBooleanStore(),
//        	blankText:i18n('i18n.MemberCustEditView.isMainContactAlert'),
//		    listeners:{
//		    	scope:me,
//		    	select:me.selectIsMainLinkMan
//		    }},
//	       {xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12},
           
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
//        	columns:6,
        	layout:'column',
        	colspan:6,
        	width:556,
        	vertical: true,
        	defaults:{
				listeners:{
					scope:me,
					specialkey:me.enterKeyEvent,
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
	        	editable:false,
	        	queryMode:'local',
	            forceSelection:true,
				displayField:'name',
				valueField:'value',
				store:me.memberData.getBooleanStore(),
	        	blankText:i18n('i18n.MemberCustEditView.isMainContactAlert'),
			    listeners:{
			    	scope:me,
			    	select:me.selectIsMainLinkMan
			    }}, {xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12}
			    ]
        	},
//           {xtype:'checkboxgroup',
//        	fieldLabel:i18n('i18n.MemberCustEditView.contactType'),
//        	columns:6,
//        	colspan:7,
//        	width:622,
//        	vertical: true,
//        	defaults:{
//				listeners:{
//					scope:me,
//					specialkey:me.enterKeyEvent,
//					render:function(checkBox){
//						//加载联系人类型数据
//						if(me.contactRecord != null){
//							var linkmanTypeString = me.contactRecord.get('linkmanType');
//							if(!DpUtil.isEmpty(linkmanTypeString)){
//								var linkmanTypeArray = linkmanTypeString.split(',');
//								if(linkmanTypeArray[checkBox.inputValue] != null){
//									checkBox.setValue(linkmanTypeArray[checkBox.inputValue]);
//									if('0' == checkBox.inputValue && '0' == linkmanTypeArray[0]){
//										checkBox.setValue(false);
//									}
//								}
//							}
//						}
//					}
//				}
//        	},
//        	allowBlank:false,
//        	blankText:i18n('i18n.MemberCustEditView.contactTypeAlert'),
//        	items:[{boxLabel:i18n('i18n.MemberCustEditView.financeContact'),name:'linkmanType',inputValue:'0'},
//        	       {boxLabel:i18n('i18n.MemberCustEditView.businessContact'),name:'linkmanType',inputValue:'1'},
//        	       {boxLabel:i18n('i18n.MemberCustEditView.deliverContact'),name:'linkmanType',inputValue:'2'},
//        	       {boxLabel:i18n('i18n.MemberCustEditView.receiveContact'),name:'linkmanType',inputValue:'3'},
//        	       {boxLabel:i18n('i18n.MemberCustEditView.agreementContact'),name:'linkmanType',inputValue:'4'},
//        	       {xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12}]},
//           {xtype:'displayfield',width:12},
           {fieldLabel:i18n('i18n.MemberCustEditView.gender'),
        	xtype:'dpcombobox',
        	queryMode:'local',
			displayField:'codeDesc',
			valueField:'code',
			store:me.memberData.getGenderStore(),
        	name:'sex',
        	forceSelection:true,
        	blankText:i18n('i18n.MemberCustEditView.genderAlert'),
        	listeners:{
				change:function(field, newValue) {if (Ext.isEmpty(newValue)) {field.setValue(null);}}
			}
        	},
           {name:'sexFlag',xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12},
           {fieldLabel:i18n('i18n.MemberCustEditView.telephoneNo'),
			regex : DpUtil.linkWayLimit('T'),
			regexText:i18n('i18n.MemberCustEditView.pleaseInputPhone'),
	        name:'telPhone',
    	    id:'telPhone_id',
    	    listeners:{
				scope:me,
				blur:me.blurTelPhone
			}},
          {xtype:'displayfield',value:'<span style="color:red;">*</span>',width:12},
           {fieldLabel:i18n('i18n.MemberCustEditView.mobileNo'),
		    name:'mobilePhone',
    	    id:'mobilePhone_id',
			regex : DpUtil.linkWayLimit('M'),
			regexText:i18n('i18n.MemberCustEditView.pleaseInputCorrectMobilePhone'),
			listeners:{
				scope:me,
				blur:me.validateMobileIsExsist,
				change:DpUtil.autoChangeMobileLength
			}},
           {xtype:'displayfield',html:'<span data-qtip="手机和固话号码必填其一"><img src="../images/customer/tip.png"></span>',width:12},
//           {xtype:'displayfield',colspan:2,hidden:true,value:i18n('i18n.MemberCustEditView.m_mobileTelNum')},
           {fieldLabel:i18n('i18n.MemberCustEditView.ffax'),
			maxLength:80,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80),
			name:'ffax'},
           {xtype:'displayfield',width:12},
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
			editable:false,
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
           {xtype:'displayfield',width:12}
           ];
	},
	//联系人编码 变成小写
	blur2Lower:function(textfield){
		if(!Ext.isEmpty(textfield.getValue())){
			textfield.setValue(textfield.getValue().toLowerCase());
		}
	},
	//"是否主联系人"选择"是"并且客户类型是"个人"是判断身份证号
	selectIsMainLinkMan:function(combox,records){
		var me = this;
		if(records.length > 0){
			var idNumber = me.getForm().findField('idCard').getValue();
			var custType = me.parent.parent.parent.memberCustBasicInfo.getForm().findField('custType').getValue();//客户类型
			var cardTypeCon = Ext.getCmp('cardTypeCon_id').getValue();//证件类型
			if(combox.getValue()){
				me.validateIdNumber(cardTypeCon,idNumber,custType,combox.getValue(),me);
			}else{
				RealTimeVerifyResult.success[5] = true;
				RealTimeVerifyResult.message[5] = '';
			}
		}
	},
	//选择证件类型时 发请求检验 证件号码是否唯一
	selectcardTypeCon:function(combox,records){
		var me = this;
		if(records.length > 0){
			var idNumberType = me.getForm().findField('cardTypeCon_id');
			var idNumber = me.getForm().findField('idCard');
			idNumber.setValue(null);
			if('OTHER'== idNumberType.getValue()){
				idNumber.emptyText='仅限外籍人员证件';
				idNumber.regex = /^.{1,}$/;
			}else {
				idNumber.emptyText=' ';
				if('SECOND_GENERATION_IDCARD'== idNumberType.getValue()){
					//选择为 身份证 时增加 正则校验
					idNumber.regex = DpUtil.linkWayLimit('I');
	    	    	idNumber.regexText = i18n('i18n.MemberCustEditView.cardNum15118LastxX');
				}else{
					idNumber.regex = /^.{1,}$/;
					idNumber.setValue(null);
				}
			}
			idNumber.applyEmptyText();
			idNumber.doComponentLayout();
			//校验证件号码合法性
			MemberCustEditView.validateIdNumberIsLegal(me.contactRecord);
			if(RealTimeVerifyResult.success[9]){
				me.validateIdNumberIsExsist(idNumber);	
			}
			if('SECOND_GENERATION_IDCARD'!= idNumberType.getValue()){
				idNumber.clearInvalid();
			}
		}
	},
	//验证身份证号是否唯一
	validateIdNumberIsExsist:function(idNumberField){
		var me = this;
		//判断 手机号码是否 修改后格式正确
		if(!idNumberField.isValid()){
			return;
		}
		//校验证件号码合法性
		MemberCustEditView.validateIdNumberIsLegal(me.contactRecord);
		if(!RealTimeVerifyResult.success[9]){
			return;	
		}
		//判断 身份证号是否被修改了，被修改才做联系人生日修改
//		me.getForm().updateRecord(me.contactRecord);
		//if(!me.contactRecord.isModified('idCard')){
		if(idNumberField.getValue() != me.contactRecord.get('idCard')){
			//实时更新联系人生日
			MemberCustEditView.changeBirthDay();
		}
		var idNumber = idNumberField.getValue();
		var custType = Ext.getCmp('custType_id').getValue();//客户类型
		var isMainContact = me.getForm().findField('isMainLinkMan').getValue();//是否主联系人
		var cardTypeCon = me.getForm().findField('cardTypeCon').getValue();//证件类型
		if(Ext.isEmpty(isMainContact)){
			me.getForm().findField('isMainLinkMan').setValue(false);
			isMainContact = false;
		}
		//证件类型为空则默认证件类型为 "第二代身份证"
		if(Ext.isEmpty(cardTypeCon)){
			me.getForm().findField('cardTypeCon').setValue('SECOND_GENERATION_IDCARD');
			cardTypeCon = 'SECOND_GENERATION_IDCARD';
		}
		me.validateIdNumber(cardTypeCon,idNumber,custType,isMainContact,me);
	},
	//身份证号校验
	validateIdNumber:function(cardTypeCon,idNumber,custType,isMainContact,me){
		if(Ext.isEmpty(me)){
			me = this;
		}
		//如果是 新增的 id则 赋予一个临时Id
		if(Ext.isEmpty(me.contactRecord.get('id'))){
			me.contactRecord.set('id',MemberCustEditView.getSequence());
		}
		//判断 身份证号是否被修改了，被修改才做校验
//		me.getForm().updateRecord(me.contactRecord);
//		if(!me.contactRecord.isModified('idCard')){
		if(cardTypeCon == me.contactRecord.get('cardTypeCon') && idNumber == me.contactRecord.get('idCard') && !isMainContact){
			RealTimeVerifyResult.success[5] = true;
			RealTimeVerifyResult.message[5] = '';
			return;
		}
		if(cardTypeCon == me.contactRecord.get('cardTypeCon') && idNumber == me.contactRecord.get('idCard') && isMainContact && me.contactRecord.get('isMainLinkMan')){
			RealTimeVerifyResult.success[5] = true;
			RealTimeVerifyResult.message[5] = '';
			return;
		}
		var validateSuccessFn = function(result){
			me.addIdCardContact(me.contactRecord.get('id'),true,'',me);
			if(!result.exist){
				MessageUtil.showMessage(i18n('i18n.MemberCustEditView.m_contactIsExist'));
				RealTimeVerifyResult.success[5] = false;
				RealTimeVerifyResult.message[5] = i18n('i18n.MemberCustEditView.m_contactIsExist');
				return;
			}
			RealTimeVerifyResult.success[5] = true;
			RealTimeVerifyResult.message[5] = '';
		};
		var validateFailFn = function(result){
			if(DpUtil.isEmpty(result.message)){
				result.message = i18n('i18n.MemberCustEditView.anExceptionBackgroundAppearsWar');
				me.addIdCardContact(me.contactRecord.get('id'),false,result.message,me);
			}
			me.addIdCardContact(me.contactRecord.get('id'),false,result.message,me);
			RealTimeVerifyResult.success[5] = false;
			RealTimeVerifyResult.message[5] = result.message;
//			MessageUtil.showMessage(result.message);
//				MessageUtil.showMessage(result.message);
		};
		var params = {};
		params.idNumber = idNumber;
		params.custType = custType;
		params.cardType = cardTypeCon;
		params.isMainContact = isMainContact;
		var custGroup = Ext.getCmp('MemberEdit_custGroup_id').getValue();
		//只有客户类型为 "个人"且"是否主联系人"选择"是"时才进行身份证验证
		if('PERSONAL' == custType && isMainContact){
			if(!Ext.isEmpty(idNumber)){
				me.memberData.validateIdNumberIsExist(params,validateSuccessFn,validateFailFn);
			}else if('RC_CUSTOMER'==custGroup){
//				MessageUtil.showMessage(i18n('i18n.MemberCustEditView.m_idCardNotNull'));
				RealTimeVerifyResult.success[5] = false;
				RealTimeVerifyResult.message[5] = i18n('i18n.MemberCustEditView.m_idCardNotNull');
				return;
			}else{
				RealTimeVerifyResult.success[5] = true;
				RealTimeVerifyResult.message[5] ='';
				return;
			}
		}
	},
	//全局变量 增加 实时校验身份证号 是否存在
	addIdCardContact:function(contactId,success,message,me){
		var idCardList = RealTimeVerifyResult.message[1];
		if(idCardList.length > 0){
			var flag = true;//标志 所操作联系人的 身份证号是否已被修改，没被修改则为 true
			for(var i = 0; i < idCardList.length;i++){
				if(!Ext.isEmpty(contactId)
				&& contactId == idCardList[i].contactId){
					idCardList[i].success = success;
					idCardList[i].message = message;
					flag = false;
				}
			}
			if(flag){
				idCardList.push({contactId:contactId,success:success,message:message});
			}
		}else{
			idCardList.push({contactId:contactId,success:success,message:message});
		}
	},
	//失焦事件
	blurEventFunction:function(field,validateFn){
		var me = this;
		//判断 联系人电话号码是否 修改后格式正确
		if(!field.isValid()){
			return;
		}
		var fieldValue = field.getValue();
		if(!DpUtil.isEmpty(fieldValue)){
			//如果是 新增的 id则 赋予一个临时Id
			if(Ext.isEmpty(me.contactRecord.get('id'))){
				me.contactRecord.set('id',MemberCustEditView.getSequence());
			}
			validateFn(fieldValue,me);
		}
	},
	//发请求校验联系人姓名加联系人固话是否重复
	validateTelAndNameFunction:function(me,telNum,contactName){
		var validateSuccessFn = function(result){
//			if(!result.exist){
//				result.message = i18n('i18n.MemberCustEditView.anExceptionBackgroundAppearsWar');
//			}
			RealTimeVerifyResult.success[6] = true;
			RealTimeVerifyResult.message[6] = '';
			me.addTelAndNameContact(me.contactRecord.get('id'),true,'');
		};
		var validateFailFn = function(result){
			if(DpUtil.isEmpty(result.message)){
				result.message = i18n('i18n.MemberCustEditView.anExceptionBackgroundAppearsWar');
				//全局变量赋值 
//					me.addMobilContact(me.contactRecord.get('id'),false,result.message);
			}
			MessageUtil.showMessage(result.message);
			RealTimeVerifyResult.success[6] = false;
			RealTimeVerifyResult.message[6] = result.message;
			me.addTelAndNameContact(me.contactRecord.get('id'),false,result.message);
		};
		var params = {};
		params.telNum = telNum;
		params.contactName = contactName;
		me.memberData.validateTelAndNameIsExist(params,validateSuccessFn,validateFailFn);
	},
	//全局变量 增加 实时校验固定电话加联系人姓名 是否存在
	addTelAndNameContact:function(contactId,success,message){
		var me = this;
		var telAndNameContactList = RealTimeVerifyResult.message[7];
		if(telAndNameContactList.length > 0){
			var flag = true;//标志 所操作联系人的 姓名加联系人固话 是否已被修改，没被修改则为 true
			for(var i = 0; i < telAndNameContactList.length;i++){
				if(!Ext.isEmpty(contactId)
				&& contactId == telAndNameContactList[i].contactId){
					telAndNameContactList[i].success = success;
					telAndNameContactList[i].message = message;
					flag = false;
				}
			}
			if(flag){
				telAndNameContactList.push({contactId:contactId,success:success,message:message});
			}
		}else{
			telAndNameContactList.push({contactId:contactId,success:success,message:message});
		}
	},
	// 联系人 姓名 失焦事件
	blurContactName:function(contactNameField){
		var me = this;
		me.blurEventFunction(contactNameField,function(fieldValue,me){
			var telNum = Ext.getCmp('telPhone_id').getValue();
			var contactName = Ext.getCmp('contactName_id').getValue();
			var mobilePhone = Ext.getCmp('mobilePhone_id').getValue();
//			var contactName = fieldValue;
			//判断 固话或联系人名称是否被修改了，被修改才做校验
			if (Ext.isEmpty(contactName) ||(telNum == me.contactRecord.get('telPhone') 
					&& contactName == me.contactRecord.get('name'))){
				RealTimeVerifyResult.success[6] = true;
				RealTimeVerifyResult.message[6] = '';
				return;
			}
			var nameArr = GetFirstCharUtil.getFirstChars(contactName);
			//联系人姓名修改 有手机，无手机
			if(!Ext.isEmpty(mobilePhone)){
				if(contactName != me.contactRecord.get('name')){
				Ext.getCmp('contactNum_id').store.loadData(returnNumData(nameArr,mobilePhone));}
			}else{
				Ext.getCmp('contactNum_id').store.loadData(returnNumData(nameArr,telNum));
			}
			if(Ext.isEmpty(telNum)){
				RealTimeVerifyResult.success[6] = true;
				RealTimeVerifyResult.message[6] = '';
				return;
			}
			me.validateTelAndNameFunction(me,telNum,contactName);
		});
	},
	// 联系人电话失去焦点事件
	blurTelPhone:function(telField){
		var me = this;
		// 调用联系人名称 失焦事件
		me.blurContactName(telField);
//		me.blurEventFunction(contactNameField,function(fieldValue,me){
//			var tel = Ext.getCmp('telPhone_id').getValue()
//			//判断 手机号码是否被修改了，被修改才做校验
//			if ((!Ext.isEmpty(tel) || !Ext.isEmpty(fieldValue))
//					|| (tel == me.contactRecord.get('telPhone') 
//					&& fieldValue == me.contactRecord.get('name'))){
//				return;
//			}
//			me.validateTelAndNameFunction(me,telNum,contactName);
//		});
	},
	//验证手机号码是否唯一
	validateMobileIsExsist:function(mobileField){
		var me = this;
		var mobile = mobileField.getValue();
		//判断 手机号码是否 修改后格式正确
		if(!mobileField.isValid()){
			return;
		}
		//判断 手机号码是否被修改了，被修改才做校验
		if(mobile == me.contactRecord.get('mobilePhone')){
			RealTimeVerifyResult.success[8] = true;
			RealTimeVerifyResult.message[8] = '';
			return;
		}
		//通过联系人姓名名称手写字母 加上手机号码 重新更换 联系人编码 的可选编码内容
		var nameArr = GetFirstCharUtil.getFirstChars(Ext.getCmp('contactName_id').getValue());
		Ext.getCmp('contactNum_id').store.loadData(returnNumData(nameArr,mobile));
		if(!DpUtil.isEmpty(mobile)){
			//如果是 新增的 id则 赋予一个临时Id
			if(Ext.isEmpty(me.contactRecord.get('id'))){
				me.contactRecord.set('id',MemberCustEditView.getSequence());
			}
			var validateSuccessFn = function(result){
				RealTimeVerifyResult.success[8] = true;
				RealTimeVerifyResult.message[8] = '';
				me.addMobilContact(me.contactRecord.get('id'),true,'');
			};
			var validateFailFn = function(result){
				if(DpUtil.isEmpty(result.message)){
					result.message = i18n('i18n.MemberCustEditView.anExceptionBackgroundAppearsWar');
					//全局变量赋值 
//					me.addMobilContact(me.contactRecord.get('id'),false,result.message);
				}
				MessageUtil.showMessage(result.message);
				RealTimeVerifyResult.success[8] = false;
				RealTimeVerifyResult.message[8] = result.message;
				me.addMobilContact(me.contactRecord.get('id'),false,result.message);
			};
			var params = {};
			params.mobile = mobile;
			me.memberData.validateMobileIsExist(params,validateSuccessFn,validateFailFn);
		}
	},
	//全局变量 增加 实时校验手机 是否存在
	addMobilContact:function(contactId,success,message){
		var me = this;
		var mobilContactList = RealTimeVerifyResult.message[2];
		if(mobilContactList.length > 0){
			var flag = true;//标志 所操作联系人的 手机号 是否已被修改，没被修改则为 true
			for(var i = 0; i < mobilContactList.length;i++){
				if(!Ext.isEmpty(contactId)
				&& contactId == mobilContactList[i].contactId){
					mobilContactList[i].success = success;
					mobilContactList[i].message = message;
					flag = false;
				}
			}
			if(flag){
				mobilContactList.push({contactId:contactId,success:success,message:message});
			}
		}else{
			mobilContactList.push({contactId:contactId,success:success,message:message});
		}
	},
	//新增联系人 Id 序列，为了和后台区分 增加"X"开头区别
	getSequence:function(){
		return 'X'+Sequence++;
	},
	//Enter键按下时焦点自动移到下个控件
	enterKeyEvent:function(field,event){
		var me = this;
		if(event.getKey() == Ext.EventObject.ENTER){
			if(field.next() != null  && field.fieldLabel != i18n('i18n.MemberCustEditView.isMainContact')){
				if(field.fieldLabel == i18n('i18n.ScatterCustManagerView.idNumber')){
					me.getForm().findField('linkmanType').focus();
				}else if(field.name == 'linkmanType'){
					field.next().focus();
				}else if(field.next().next() != null){
					field.next().next().focus();
				}
			}else if(field.boxLabel == i18n('i18n.MemberCustEditView.agreementContact')){
				me.getForm().findField('isMainLinkMan').focus();
			}else if(field.fieldLabel == i18n('i18n.MemberCustEditView.isMainContact')){
				++me.fireTimes;
				if(me.fireTimes > 1){
					field.next().next().focus();
				}
			}
		}
	},
	//提交时校验联系人信息
	validateLinkmanInfo:function(){
		var me = this;
		var success = true;
		var message = '';
		if(me.getForm().isValid()){
			//联系人身份证号
			var idCard=Ext.getCmp('contactIdCard').getValue();
			var idCardField=Ext.getCmp('contactIdCard');
			//证件类型
			var cardTypeCon = Ext.getCmp('cardTypeCon_id').getValue();
			//生日
			var birthday=Ext.getCmp('contactBrithday').getRawValue();
			// 只有选择 身份证 时 才进行检验出生日期,  校验身份证号 是否 符合验证格式 idCard
			if ('SECOND_GENERATION_IDCARD' == cardTypeCon && idCardField.isValid() && idCard.length==18) {
				if (!(idCard.substring(6,8)=='20')&&!(idCard.substring(6,8)=='19')) {
					message = i18n('i18n.MemberCustEditView.m_idCardIvnconformity');
					success = false;
					return {'success':success,'message':message};
				}
				if (parseInt(idCard.substring(10,12))>12||parseInt(idCard.substring(10,12))<0) {
					message = i18n('i18n.MemberCustEditView.m_idCardIvnconformity');
					success = false;
					return {'success':success,'message':message};
				}
				if (parseInt(idCard.substring(12,14))>31||parseInt(idCard.substring(12,14))<0) {
					message = i18n('i18n.MemberCustEditView.m_idCardIvnconformity');
					success = false;
					return {'success':success,'message':message};
				}
				if (!Ext.isEmpty(birthday)) {
					var yeara = birthday.split(i18n('i18n.PotentialCustManagerView.searchEndTime'))[0];
					var montha = birthday.split(i18n('i18n.PotentialCustManagerView.searchEndTime'))[1];
					var daya = birthday.split(i18n('i18n.PotentialCustManagerView.searchEndTime'))[2];
					if (idCard.substring(6,10)!=yeara||idCard.substring(10,12)!=montha||idCard.substring(12,14)!=daya) {
						message = i18n('i18n.MemberCustEditView.m_idCardBirthDate');
						success = false;
						return {'success':success,'message':message};
					}
				}
			}else if('SECOND_GENERATION_IDCARD' == cardTypeCon && idCardField.isValid() && idCard.length==15){
				//年 idCard.substring(7,9) 月idCard.substring(9,11) 日idCard.substring(11,13)
				if (parseInt(idCard.substring(8,10))>12||parseInt(idCard.substring(8,10))<0) {
					message = i18n('i18n.MemberCustEditView.m_idCardIvnconformity');
					success = false;
					return {'success':success,'message':message};
				}
				if (parseInt(idCard.substring(10,12))>31||parseInt(idCard.substring(10,12))<0) {
					message = i18n('i18n.MemberCustEditView.m_idCardIvnconformity');
					success = false;
					return {'success':success,'message':message};
				}
				if (!Ext.isEmpty(birthday)) {
					var yeara = birthday.split(i18n('i18n.PotentialCustManagerView.searchEndTime'))[0];
					var montha = birthday.split(i18n('i18n.PotentialCustManagerView.searchEndTime'))[1];
					var daya = birthday.split(i18n('i18n.PotentialCustManagerView.searchEndTime'))[2];
					if (idCard.substring(6,8)!=yeara||idCard.substring(8,10)!=montha||idCard.substring(10,12)!=daya) {
						message = i18n('i18n.MemberCustEditView.m_idCardBirthDate');
						success = false;
						return {'success':success,'message':message};
					}
				}
			}
			//判断手机号码与固定电话是否必填其一
			var phone = me.getForm().findField('telPhone');
			var mobile = me.getForm().findField('mobilePhone');
			var linkmanName = me.getForm().findField('name');
			if(DpUtil.isEmpty(phone.getValue()) && DpUtil.isEmpty(mobile.getValue())){
				message = i18n('i18n.MemberCustEditView.m_mustLinkWay');
				success = false;
			}else{
				//校验联系人编码规则
				var linkmanNumber = me.getForm().findField('number');
				var mobileInLinkmanNumber = linkmanNumber.getValue().substring(linkmanName.getValue().length,linkmanNumber.getValue().length);
				if(!DpUtil.isEmpty(mobile.getValue()) 
					&& linkmanNumber.getValue().indexOf(mobile.getValue()) != me.getForm().findField('name').getValue().length
					||mobileInLinkmanNumber != mobile.getValue() && !DpUtil.isEmpty(mobile.getValue()) ){
		    		message = '存在手机号码，联系人编码规则为：联系人姓名首字母(小写)+手机号码!';
					success = false;
		    	}
				else if(Ext.isEmpty(mobile.getValue()) && !Ext.isEmpty(phone.getValue()) && 
		    			 linkmanNumber.getValue().indexOf(phone.getValue().replace(/\/|-/g,'')) != me.getForm().findField('name').getValue().length){
		    		message = '只存在固定电话，联系人编码规则为：联系人姓名首字母(小写)+固定电话!';
					success = false;
		    	}
		    	else{
		    		//一个客户的所有联系人信息手机号码唯一，如果不存在手机号码，姓名+固定电话唯一,同时联系人编码必须唯一
		    		var linkmans = me.memberData.getContactStore().getRange();
		    		for(var i = 0;i < linkmans.length; i++){
		    			if(!DpUtil.isEmpty(mobile.getValue()) && 
		    			   linkmans[i] != me.contactRecord &&
		    			   linkmans[i].get('mobilePhone') == mobile.getValue()){
		    				message = i18n('i18n.MemberCustEditView.haveSameContactPhonePleaseUpdateContactPhone');
							success = false;
							return {'success':success,'message':message};
		    			}else if(DpUtil.isEmpty(mobile.getValue()) && 
		    					 !DpUtil.isEmpty(phone.getValue()) &&
		    					 linkmans[i] != me.contactRecord &&
		    					 linkmans[i].get('telPhone') == phone.getValue() &&
		    					 linkmans[i].get('name') == linkmanName.getValue()){
		    				message = i18n('i18n.MemberCustEditView.haveSamePhoneAndNamePleaseUpdatePhoneAndName');
							success = false;
							return {'success':success,'message':message};
		    			}else if(linkmans[i] != me.contactRecord &&
		    					 linkmans[i].get('number') == linkmanNumber.getValue()){
		    				message = i18n('i18n.MemberCustEditView.haveSameContactNumNotAddContactPleaseUpdateContactNum');
							success = false;
							return {'success':success,'message':message};
		    			}
		    		}
		    		
		    		//一个客户有且只有一个主联系人
					if(me.getForm().findField('isMainLinkMan').getValue() == true){
						var recordIndex = me.memberData.getContactStore().find('isMainLinkMan',true);
						if(recordIndex != -1 && me.contactRecord != me.memberData.getContactStore().getAt(recordIndex)){
							message = i18n('i18n.MemberCustEditView.aCustomerOnlyHaveMainContact');
							success = false;
						}
					}
		    	}
			}
		}else{
			success = false;
			message = i18n('i18n.RecordEditView.m_checkAllRight');
		}
		return {'success':success,'message':message};
	},
	//搜集联系人修改的信息
	collectLinkmanBasicAlterData:function(alterContactList){
		var me = this;
		me.getForm().updateRecord(me.contactRecord);
		
		//会员基本信息的所有字段
		var fieldsArray = me.contactRecord.fields.items;
		for(var i = 0; i < fieldsArray.length;i++){
			//字段名
			var fieldName = fieldsArray[i].name;
			
			if(me.contactRecord.isModified(fieldName)){
				var basicApproveData = me.getEmptyMemberLinkBasicApproveData();
				basicApproveData.set('fieldName',fieldName);
				basicApproveData.set('newValue',me.contactRecord.get(fieldName));
				alterContactList.push(basicApproveData.data);
			}
		}
	},
	//获取空的联系人修改的approveData
	getEmptyMemberLinkBasicApproveData:function(){
		var me = this;
		var basicApproveData = Ext.create('ApproveDataModel');
		basicApproveData.set('className','Contact');
		basicApproveData.set('classId',me.contactRecord.get('id'));
		return basicApproveData;
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
		// 初始化联系人偏好地址
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
								disabled:('view' == me.viewStatus)?true:false,
								scope:me,
								handler:me.showCreateAddressHobby},
						       {xtype:'button',text:i18n('i18n.PotentialCustManagerView.update'),
								disabled:('view' == me.viewStatus)?true:false,
								scope:me,
								handler:me.showUpdateAddressHobby},
						       {xtype:'button',text:i18n('i18n.PotentialCustManagerView.delete'),
								disabled:('view' == me.viewStatus)?true:false,
								scope:me,
								handler:me.deleteAddressHobby}]
				        },{xtype:'middlebuttonpanel'}]
			 },{xtype:'popupgridpanel',
				autoScroll:true,
			    height:125,
			    store:me.memberData.getPreferenceAddressStore(),
				columns:[{text:i18n('i18n.MemberCustEditView.addressType'),dataIndex:'addressType',
							renderer:function(value,obg,record){
								var shuttleStore = me.memberData.getShuttleAddressStore();//接送货地址store
								/**
								 * @author chenaichun
								 * @description 根据选择的联系人偏好地址去比较接送货地址列表，如果接送货地址修改了
								 * 就将对应偏好地址的地址和地址类型标红
								 */
								var is = false;
								shuttleStore.each(function(shuttleRecord){
								 if(shuttleRecord.get('id') == record.get('shuttleAddressId')){
								 	if(shuttleRecord.get('addressType') != record.get('addressType')){
											is = true;
											return;
										}
								 }
								});
								if(is){
									return '<span style="color:red">'+DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.ADDRESS_TYPE)+'</span>';
								}else{
								return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.ADDRESS_TYPE);
								}
				      	   }
				         },
				         {text:i18n('i18n.PotentialCustEditView.address'),dataIndex:'address',
				         	renderer:function(value,obg,record){
								var shuttleStore = me.memberData.getShuttleAddressStore();//接送货地址store
								/**
								 * @author chenaichun
								 * @description 根据选择的联系人偏好地址去比较接送货地址列表，如果接送货地址修改了
								 * 就将对应偏好地址的地址和地址类型标红
								 */
								var is = false;
								shuttleStore.each(function(shuttleRecord){
								 if(shuttleRecord.get('id') == record.get('shuttleAddressId')){
								 	if(shuttleRecord.get('address') != record.get('address')
										){
											is = true;
											return;
										}
								 }
								});
								if(is){
									return '<span style="color:red">'+value+'</span>';
								}else{
									return value;
								}
				      	   }
				         },
				         {text:'',dataIndex:'shuttleAddressId',hidden:true},
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
	//创建地址偏好
	showCreateAddressHobby:function(){
		var me = this;
		var addressHobbyModel = Ext.create('PreferenceAddressModel');
		addressHobbyModel.set('linkManId',me.contactRecord.get('id'));
		me.getAddressHobbyWin(me.memberData,addressHobbyModel,'new');
//		Ext.create('MemberAddressHobbyEditWin',{'memberData':me.memberData,
//												'preferenceAddressRecord':addressHobbyModel,
//												'viewStatus':'new'}).show();
	},
	//修改地址偏好
	showUpdateAddressHobby:function(){
		var me = this;
		var grid = me.down('grid');
		var selectModel = grid.getSelectionModel();
		var selections = selectModel.getSelection();
		if(selections.length != 1){
			MessageUtil.showMessage(i18n('i18n.MemberCustEditView.pleaseOneRecordToUpdate'));
		}else{
			var selected = selections[0];
			me.getAddressHobbyWin(me.memberData,selected,'update');
//			Ext.create('MemberAddressHobbyEditWin',{'memberData':me.memberData,
//					   							    'preferenceAddressRecord':selected,
//												    'viewStatus':'update'}).show();
		}
	},
	//得到偏好地址窗体
	getAddressHobbyWin:function(memberData,record,viewStatus){
		Ext.create('MemberAddressHobbyEditWin',{'memberData':memberData,
				   							    'preferenceAddressRecord':record,
											    'viewStatus':viewStatus}).show();
	},
	//删除地址偏好
	deleteAddressHobby:function(){
		var me = this;
		var grid = me.down('grid');
		var selectModel = grid.getSelectionModel();
		var selections = selectModel.getSelection();
		if(selections.length != 1){
			MessageUtil.showMessage(i18n('i18n.MemberCustEditView.pleaseOneRecordToDelete'));
		}else{
			var selected = selections[0];
			  MessageUtil.showQuestionMes('是否确认要删除这条联系人偏好地址?', function(btn) {
		        	   if(btn == 'yes'){
			        	   me.memberData.getPreferenceAddressStore().remove(selected);
		        	   }
		       });
		}
	},
	//加载联系人偏好地址数据
	loadContactPerfectAddress:function(contactRecord){
		var me = this;
		me.memberData.getPreferenceAddressStore().removeAll();
		//初始化联系人偏好地址
		if(me.contactRecord != null &&　me.contactRecord.get('preferenceAddressList')　!= null){
			me.memberData.getPreferenceAddressStore().loadData(me.contactRecord.get('preferenceAddressList'));
		}
	},
	//搜集联系人地址偏好信息用于提交
	collectLinkmanAddressHobbyForSubmit:function(){
		var me = this;
		var linkmanAddressArray = new Array();
		me.memberData.getPreferenceAddressStore().each(function(record){
			linkmanAddressArray.push(record.data);
		});
		return linkmanAddressArray;
	},
	//搜集联系人地址偏好修改数据
	collectLinkmanAddrHobbyAlterData:function(alterContactList,alterDeleteList,alterAddList){
		var me = this;
		//所有地址偏好修改的record
		var updateAddressHobbyRecords = me.memberData.getPreferenceAddressStore().getUpdatedRecords();
		
		if(updateAddressHobbyRecords.length > 0){
			var fieldsArray = updateAddressHobbyRecords[0].fields.items;
			for(var j = 0; j < updateAddressHobbyRecords.length; j++){
				for(var i = 0; i < fieldsArray.length;i++){
					//字段名
					var fieldName = fieldsArray[i].name;
					
					if(updateAddressHobbyRecords[j].isModified(fieldName)){
						var basicApproveData = me.getEmptyAddressHobbyApproveData(updateAddressHobbyRecords[j]);
						basicApproveData.set('fieldName',fieldName);
						basicApproveData.set('newValue',updateAddressHobbyRecords[j].get(fieldName));
						alterContactList.push(basicApproveData.data);
					}
				}
			}
		}
		
		//所有删除的偏好地址
		var deleteAddressHobbyRecords = me.memberData.getPreferenceAddressStore().getRemovedRecords();
		for(var i = 0; i < deleteAddressHobbyRecords.length; i++){
			alterDeleteList.push(me.getEmptyAddressHobbyApproveData(deleteAddressHobbyRecords[i]));
		}
		//所有新增的偏好地址
		var addAddressHobbyRecords = me.memberData.getPreferenceAddressStore().getNewRecords();
		for(var i = 0; i < addAddressHobbyRecords.length; i++){
			alterAddList.push(addAddressHobbyRecords[i].data);
		}
	},
	//获取空的联系人地址偏好修改的approveData
	getEmptyAddressHobbyApproveData:function(addressHobbyRecord){
		var me = this;
		var basicApproveData = Ext.create('ApproveDataModel');
		basicApproveData.set('className','PreferenceAddress');
		basicApproveData.set('classId',addressHobbyRecord.get('id'));
		return basicApproveData;
	},
	//校验联系人偏好地址
	validateLinkmanAddressHobby:function(){			
		var me = this;
		var success = true;
		var message = '';
		var flag = 0;
		var preferenceAddressStore = me.memberData.getPreferenceAddressStore();
		if(preferenceAddressStore.getCount() != 0){
			var records = preferenceAddressStore.getRange();
			for(var i = 0; i < records.length && success; i++){
				if(!records[i].isValid()){
					success = false;
					message = i18n('i18n.MemberCustEditView.contactPreferenceAddressAncomplete');
				}
				if(records[i].get('isMainAddress')){
					flag++;
				}
			}
		}
		if (preferenceAddressStore.getCount() > 0 && flag != 1){
			success = false;
			message = i18n('i18n.MemberCustEditView.HaveAndOnlyoneHobbyAddress');
		}
		return {'success':success,'message':message};
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
		if(!DpUtil.isEmpty(me.preferenceAddressRecord.get('shuttleAddressId')) && !DpUtil.isEmpty(me.preferenceAddressRecord.get('address'))){
			me.down('form').getForm().findField('address').setValue(me.preferenceAddressRecord.get('shuttleAddressId'));
			if(Ext.isEmpty(me.preferenceAddressRecord.get('id'))){
				me.down('form').getForm().findField('address').setRawValue(me.preferenceAddressRecord.get('address'));
				me.down('form').getForm().findField('addressType').setValue(me.preferenceAddressRecord.get('addressType'));
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
		}else if(me.viewStatus == 'update'){
			var shuttleStore = me.memberData.getShuttleAddressStore();//接送货地址store
			/**
			 * @author chenaichun
			 * @description 根据选择的联系人偏好地址去比较接送货地址列表，如果接送货地址修改了
			 * 就将对应偏好地址的地址和地址类型清空，让用户去手动选择。
			 */
			shuttleStore.each(function(shuttleRecord){
			 if(shuttleRecord.get('id') == me.preferenceAddressRecord.get('shuttleAddressId')){
			 	if(shuttleRecord.get('address') != me.preferenceAddressRecord.get('address')
					|| shuttleRecord.get('addressType') != me.preferenceAddressRecord.get('addressType')){
						me.down('form').getForm().findField('address').clearValue();
						me.down('form').getForm().findField('addressType').clearValue();
					}
			 }
			});
						
			
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
				enableKeyEvents:true,
				listeners:{
					scope:me,
					keypress:me.enterKeyEvent
				}
		    },
			items:[{
					    fieldLabel:i18n('i18n.MemberCustEditView.shuttleAddress'),
					    xtype:'dpcombobox',
					    blankText:i18n('i18n.MemberCustEditView.shuttleAddressHobbyAlert'),
					    queryMode:'local',
					    editable:false,
					    forceSelection:true,
					    displayField:'address',
					    valueField:'id',
//					    valueField:'address',
					    store:me.memberData.getShuttleAddressStore(),
					    allowBlank:false,
//					    name:'shuttleAddress',
					    name:'address',
					    listeners:{
					    	scope:me,
					    	keypress:me.enterKeyEvent,
					    	select:me.selectAddressEvent,
					    	expand:me.expandAddressEvent
					    }
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
			    	items:[{xtype:'displayfield',value:i18n('i18n.MemberCustEditView.timeHobbyStart'),forceSelection:true,width:75},
			    	       {
			    	           xtype: 'dpdtimefield',
			    	           name: 'startTime',
			    	           flex:1,
			    	           increment: 60,
			    	           format:'H 点'	,
			    	           listeners:{
									scope:me,
									keypress:me.enterKeyEvent,
									select:me.selectStartTimeEvent
							   }
			    	       },
			    	       {xtype:'displayfield',value:i18n('i18n.MemberCustEditView.timeHobbyEnd'),forceSelection:true,width:75},
			    	       {
			    	           xtype: 'dpdtimefield',
			    	           name: 'endTime',
			    	           flex:1,
			    	           increment: 60,
			    	           format:'H 点',
			    	           listeners:{
									scope:me,
									keypress:me.enterKeyEvent,
									select:me.selectEndTimeEvent
							   }
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
			disabled:('view' == me.viewStatus)?true:false,
			scope:me,
			handler:me.commitLinkmanAddressHobby
		},{
			xtype:'button',
			text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
			handler:function(){
				me.close();
			}
		}];
	},
	//Enter键按下时焦点自动移到下个控件
	enterKeyEvent:function(field,event){
		var me = this;
		var form = me.down('form');
		if(event.getKey() == Ext.EventObject.ENTER){
			if(field.fieldLabel == i18n('i18n.MemberCustEditView.shuttleAddress')){
				form.getForm().findField('startTime').focus();
			}else if(field.name == 'startTime'){
				form.getForm().findField('endTime').focus();
			}else if(field.name == 'endTime'){
				form.getForm().findField('billRequest').focus();
			}else{
				if(field.next() != null){
					field.next().focus();
				}else{
					me.down('button').focus();
				}
			}
		}
	},
	//选择接送货地址自动填充地址类型及地址id
	selectAddressEvent:function(combox,records){
		if(records.length > 0){
			var addressTypeCombo = combox.next('combobox');
			addressTypeCombo.setValue(records[0].get('addressType'));
			combox.up('form').getForm().findField('shuttleAddressId').setValue(records[0].get('id'));
			combox.up('form').getForm().findField('shuttleAddress').setValue(records[0].get('shuttleAddress'));
		}
	},
	//接送货地址combox伸展时校验
	expandAddressEvent:function(combox){
		if(combox.getStore().getCount() == 0){
			MessageUtil.showMessage(i18n('i18n.MemberCustEditView.plesaeAddAddressContactWar'));
		}
	},
	//开始时间必须早于结束时间
	selectStartTimeEvent:function(timefield,value){
		var endTimeField = timefield.next('timefield');
		if(!DpUtil.isEmpty(endTimeField.getValue()) && value >= endTimeField.getValue()){
			MessageUtil.showMessage(i18n('i18n.MemberCustEditView.theTheStartTimeMustBeLaterThanEndTimePreferenceWar'));
			timefield.setValue(null);
		}
	},
	//结束时间必须晚于开始时间
	selectEndTimeEvent:function(timefield,value){
		var startTimeField = timefield.prev('timefield');
		if(!DpUtil.isEmpty(startTimeField.getValue()) && value <= startTimeField.getValue()){
			MessageUtil.showMessage(i18n('i18n.MemberCustEditView.theEndTimePreferenceMustBeLaterThanTheStartTimeWar'));
			timefield.setValue(null);
		}
	},
	//提交联系人偏好地址
	commitLinkmanAddressHobby:function(){
		var me = this;
		var validateRs = me.validateLinkmanAddressHobby();
		if(validateRs.success){
			//更新数据
			me.down('form').getForm().updateRecord(me.preferenceAddressRecord);
			me.preferenceAddressRecord.set('address',me.down('form').getForm().findField('address').getRawValue());
			if(me.viewStatus == 'new'){
				me.memberData.getPreferenceAddressStore().insert(0,me.preferenceAddressRecord);
			}
			me.close();
		}else if(!DpUtil.isEmpty(validateRs.message)){
			MessageUtil.showMessage(validateRs.message);
		}
	},
	//校验联系人的偏好地址
	validateLinkmanAddressHobby:function(){
		var me = this;
		var success = true;
		var message = '';//更新数据
//		me.down('form').getForm().updateRecord(me.preferenceAddressRecord);
		if(me.down('form').getForm().isValid()){
			//联系人偏好主地址只能有一个
			if(me.down('form').getForm().findField('isMainAddress').getValue() == true){
				var recordIndex = me.memberData.getPreferenceAddressStore().find('isMainAddress',true);
				if(recordIndex != -1 && me.memberData.getPreferenceAddressStore().getAt(recordIndex) != me.preferenceAddressRecord){
					success = false;
					message = i18n('i18n.MemberCustEditView.oneAddressOnlyHaveOneAddress');
					return {'success':success,'message':message};
				}
			}
			
			//联系人针对同一个接送货地址（接送货类型和地址一样）只能有一个偏好设置
			var addresses = me.memberData.getPreferenceAddressStore().getRange();
			var shuttleAddress = me.preferenceAddressRecord.get('address');//接送货地址
			var addressType = me.preferenceAddressRecord.get('addressType');//地址类型
			shuttleAddress = me.down('form').getForm().findField('address').rawValue;
			addressType = me.down('form').getForm().findField('addressType').getValue();
			
		    for(var i = 0; i < addresses.length;i++){
		    	if(
		    	   me.preferenceAddressRecord != addresses[i] &&
		    	   addresses[i].get('address') == shuttleAddress &&
		    	   addresses[i].get('addressType') == addressType
		    	   ){
		    		success = false;
					message = i18n('i18n.MemberCustEditView.oneContactOnlyAddressPleaseUpdate');
					return {'success':success,'message':message};
		    	}
		    }
		}else{
			success = false;
		}
		return {'success':success,'message':message};
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
	width:750,
	height:235,
	memberData:null,
	accountRecord:null,    //AccountModel类型的record
	viewStatus:null,       //new表示新增，update表示修改，view表示查看
	listeners : {
		//清除上次残留数据一般在beforeshow和beforehide事件里面
		beforehide : function(window) {//在关闭window窗口前先将已经存在window里面的form的信息清空掉
			window.memberData.getBankInfoStore().removeAll();
		}
	},
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
//			me.down('form').getForm().findField('subBanknameId').setVaule(me.accountRecord.get('subBanknameId'));
//			me.down('form').getForm().findField('subBanknameId').setVaule(me.accountRecord.get('subBankname'));
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
				labelWidth:90,
				width:225,
				enableKeyEvents:true,
				listeners:{
					scope:me,
					keypress:me.enterKeyEvent
				},
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
							this.showBankWin(subBankCombox);
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
					//监听combo的trigger点击事件，比expend好用，修复bug CRM-586 新增银行账号，点击支行名称放大镜，后按Esc键，再次新点击支行放大镜无反应
					onTriggerClick:function(){
						var subBankCombox = me.down('combo');
						this.showBankWin(subBankCombox);
					},
					showBankWin:function(subBankCombox){
						if(subBankCombox.subBankWindow != null){
							subBankCombox.subBankWindow.close();
						}
						me.memberData.getBankInfoStore().removeAll();
						subBankCombox.subBankWindow = Ext.create('BankSearchUI',{'memberData':me.memberData,'parent':subBankCombox});
						subBankCombox.subBankWindow.show();
						var position  = subBankCombox.getPosition();
						position[1] = position[1]+22;
						subBankCombox.subBankWindow.setPosition(position);
					},
					//支行信息查询页面查询结果赋值
					setValueComeback:function(backRecord){
//						me.memberData.getBankComboxStore().removeAll();
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
				labelWidth:90,
				margin:'0 0 0 20',
				width:206,
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
//				xtype:'dpnumberfield',
//				hideTrigger:true,
//				allowDecimals:false,
//				decimalPrecision:0,
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
	            forceSelection:true,
				valueField:'id',
				queryMode:'local',
				editable:false,
				store:me.memberData.getContactStore(),
				listeners:{
					scope:me,
					keypress:me.enterKeyEvent,
			    	select:me.selectLinkmanEvent
//			    	change:DpUtil.changeCombText
				}
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
			xtype:'displayfield',
			margin:'0 90 0 0',
			value:i18n('i18n.MemberCustEditView.accountHelpDoc')
		},{
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
	//Enter键按下时焦点自动移到下个控件
	enterKeyEvent:function(field,event){
		var me = this;
		if(event.getKey() == Ext.EventObject.ENTER){
			if(field.next() != null){
				field.next().focus();
			}else{
				me.down('button').focus();
			}
		}
	},
	//提交时校验会员账号信息
	commitMemberAccount:function(){
		var me = this;
		var flag = 0;
		var form = me.down('form').getForm();
		if(form.isValid()){
			//校验账号是否重复
			me.memberData.getAccountStore().each(function(r){
				if(form.findField('id').getValue()!=r.get('id')&&
					form.findField('bankAccount').getValue()==r.get('bankAccount')){
					flag++;
				}
			});
			if (flag > 0){
				MessageUtil.showMessage(i18n('i18n.MemberCustEditView.accountRepeat'));
				return;
			}
			var updateAccount = function(){
       			//更新表单数据
       			me.down('form').getForm().updateRecord(me.accountRecord);
       			me.accountRecord.set('linkManMobile',me.down('form').getForm().findField('linkManMobile').getValue());
       			console.log(me.down('form').getForm().findField('linkManMobile').getValue());
       			console.log(me.accountRecord.get('linkManMobile'));
       			//插入到账号store
       			if(me.viewStatus == 'new'){
       				me.memberData.getAccountStore().insert(0,me.accountRecord);
       			}
       			me.close();
			}
			if(form.findField('accountNature').getValue()=='PUBLIC_ACCOUNT' && form.findField('countName').getValue().length <= 7){
				MessageUtil.showQuestionMes(i18n('i18n.MemberCustEditView.beSureIsCompAccount'),function(e){
					if(e=='yes'){
						updateAccount();
					}
				});
			}else if(form.findField('accountNature').getValue()=='PRIVATE_ACCOUNT' && form.findField('countName').getValue().length > 4){
				MessageUtil.showQuestionMes(i18n('i18n.MemberCustEditView.beSureIsPersonalAccount'),function(e){
					if(e=='yes'){
						updateAccount();
					}
				});
			}else{
				var titleDescrip = (me.viewStatus == 'new')? i18n('i18n.PotentialCustManagerView.add'):i18n('i18n.PotentialCustManagerView.update');
				 MessageUtil.showQuestionMes(i18n('i18n.MemberCustEditView.sureYouWantTo')+ titleDescrip +'这条账号信息?', function(btn) {
			        	   if(btn == 'yes'){
			        		   updateAccount();
			        	   }
			       });
			}
		}else{
			MessageUtil.showMessage(i18n('i18n.MemberCustEditView.accountFormValid'));
			return;
		}
	},
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
	closeAction:'destroy',
	width:650,
	height:400,
	searchBankRecord:null, //查询银行的record
	memberData:null,
	parent:null,       //父控件
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
//		            {fieldLabel:i18n('i18n.MemberCustEditView.bankAccount'),name:'accountBank'},
		            {fieldLabel:i18n('i18n.MemberCustEditView.branchBankName'),name:'accountBranch'}
//		            ,{fieldLabel:i18n('i18n.MemberCustEditView.provinceis'),hidden:true,name:'provinceId'}
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
		me.on('beforeclose',function(){
			var selectWin = Ext.getCmp('adressselectAreaTabWindow');
			if(!Ext.isEmpty(selectWin)){
				selectWin.close();
			}
		});
		this.callParent();
		
		//加载数据
		if(me.shuttleAddressRecord != null){
			me.down('form').getForm().loadRecord(me.shuttleAddressRecord);
//			//设置省市区的信息
//			me.down('form').getForm().findField('province').setValue(me.shuttleAddressRecord.get('provinceName'));
//			me.down('form').getForm().findField('city').setValue(me.shuttleAddressRecord.get('cityName'));
//			me.down('form').getForm().findField('area').setValue(me.shuttleAddressRecord.get('areaName'));
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
				enableKeyEvents:true,
				listeners:{
					scope:me,
					keypress:me.enterKeyEvent
				}
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
				id:'adressselect',
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
				maxLength:100,
				maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',100),
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
			disabled : ('view' == me.viewStatus) ? true : false,
			scope:me,
			handler:me.commitMemberAddress
		},{
			xtype:'button',
			text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
			handler:function(){
				me.close();
			}
		}];
	},
	//Enter键按下时焦点自动移到下个控件
	enterKeyEvent:function(field,event){
		var me = this;
		var form = me.down('form');
		if(event.getKey() == Ext.EventObject.ENTER){
			if(field.name == 'addressType'){
				form.getForm().findField('address').focus();
			}else{
				if(field.next() != null){
					field.next().focus();
				}else{
					me.down('button').focus();
				}
			}
		}
	},
	//提交时验证地址信息
	commitMemberAddress:function(){
		var me = this;
		var validateRs = me.validateMemberAddress();
		var titleDescrip = (me.viewStatus == 'new')? i18n('i18n.PotentialCustManagerView.add'):i18n('i18n.PotentialCustManagerView.update');
		//判断接送货地址省市区传到后台的是否是id
		var areaField = me.down('form').getForm().findField('area');
		if(areaField.getValue() == areaField.getRawValue()){
			MessageUtil.showMessage(i18n('i18n.customer.m_reSlectAddress'));
			return;
		}
		if(validateRs.success){		
			  MessageUtil.showQuestionMes(i18n('i18n.MemberCustEditView.sureYouWantTo')+ titleDescrip +'这条接送货地址?', function(btn) {
					if (btn == 'yes') {
			       			//更新数据
			       			me.down('form').getForm().updateRecord(me.shuttleAddressRecord);
			       			me.manageModel4Area(me.shuttleAddressRecord);
			       			if(me.viewStatus == 'new'){
			       				me.memberData.getShuttleAddressStore().insert(0,me.shuttleAddressRecord);
			       			}
			       			/**
			       			 * @author chenaichun
			       			 * @date 2013-11-5
			       			 * @description 修改接送货地址的时候不更新对应偏好地址，需要手动去修改，便于联系人偏好地址修改时数据库更新所有新数据
			       			 * 因此注释掉下面这个方法
			       			 */
//			       			me.updateLinkmanPreferenceAddress(me.shuttleAddressRecord);
			       			me.close();
		        	   }
		       });
		}else if(!DpUtil.isEmpty(validateRs.message)){
			MessageUtil.showMessage(validateRs.message);
		}
	},
	
	//修改该接送货地址关联的联系人偏好地址信息
	updateLinkmanPreferenceAddress:function(shuttleAddressRecord){
		var me = this;
		me.memberData.getContactStore().each(function(record){
			var preferAddress = record.get('preferenceAddressList');
				for(var i = 0;i < preferAddress.length;i++){
					if(preferAddress[i].shuttleAddressId == shuttleAddressRecord.get('id')){
						preferAddress[i].address = shuttleAddressRecord.get('address');
//						preferAddress[i].shuttleAddressId = shuttleAddressRecord.get('id');
						preferAddress[i].addressType = shuttleAddressRecord.get('addressType');
					}
				}
		});
	},
	//处理 接送货地址 省市区
	manageModel4Area:function(shuttleAddressModel){
		var me = this;
		if(!Ext.isEmpty(shuttleAddressModel)){
			var area = shuttleAddressModel.get('area');
			if(!Ext.isEmpty(area)){
				var pca = area.split(i18n('i18n.PotentialCustManagerView.searchEndTime'));
				if(pca.length >2){
					shuttleAddressModel.set('area',pca[2]);
					shuttleAddressModel.set('city',pca[1]);
					shuttleAddressModel.set('province',pca[0]);
				}
			}
			return shuttleAddressModel;
		}
		return shuttleAddressModel;
	},
	//校验接送货地址信息
	validateMemberAddress:function(){
		var me = this;
		var success = true;
		var message = '';
		var basicForm = me.down('form').getForm();
		
		if(basicForm.isValid()){
			var area = basicForm.findField('area').getValue();	
			if(!Ext.isEmpty(area)){
				var pca = area.split(i18n('i18n.PotentialCustManagerView.searchEndTime'));
				if(pca.length > 2){
					//校验：通过地址类型和地址组合判断唯一性
					var addressRecords = me.memberData.getShuttleAddressStore().getRange();
					var addressType = basicForm.findField('addressType').getValue();
		//			var province = basicForm.findField('province').getValue();
		//			var city = basicForm.findField('city').getValue();
					area = pca[2];
					var address = basicForm.findField('address').getValue();
					
					for(var i = 0; i < addressRecords.length;i++){
						if(me.shuttleAddressRecord != addressRecords[i] && 
						   (addressRecords[i].get('addressType') == addressType 
		//				   &&(addressRecords[i].get('province') == province) 
		//				   &&(addressRecords[i].get('city') == city)
						   ) && 
						   (addressRecords[i].get('area') == area) &&
						   (addressRecords[i].get('address') == address)){
							success = false;
							message = i18n('i18n.MemberCustEditView.thereSameShippingAddressInformationPleaseRe-entry');
						}
					}
				}else{
					success = false;
					message = i18n('i18n.MemberCustEditView.addressOfProvincesMunicipalitiesMustChooseToDistrict');
				}
			}else{
				success = false;
				message = i18n('i18n.MemberCustEditView.addressOfProvincesMunicipalitiesMustChooseToDistrict');
			}
		}else{
			success = false;
			message = i18n('i18n.MemberCustEditView.pleaseCheckWhetherTheRequiredCompletedWar');
		}
		return {'success':success,'message':message};
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
