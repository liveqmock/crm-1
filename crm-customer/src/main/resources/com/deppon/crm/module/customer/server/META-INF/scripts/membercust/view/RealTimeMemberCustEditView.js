RealTimeCreateMemberEdit = function(){};
//保存按钮和城市按钮是否可用
RealTimeCreateMemberEdit.dealDisDisabled = function(flag){
	Ext.getCmp('save_id').setDisabled(flag);
	Ext.getCmp('member_cityId').setDisabled(flag);
};
Ext.define('RealTimeCreateMemberEditView',{
	extend:'BasicPanel',
	width:807,
	height:650,
	layout:{
		type:'vbox',
		align:'stretch'
	},
	items:null,
	validateMemberPanel:null,
	memberEditPanel:null,
	initComponent:function(){
		var me = this;
		me.validateMemberPanel = Ext.create('RealTimeMemberCustEditPanel',{'realTimeMemberData':memberCustControl,
																		   'parent':me});
		me.memberEditPanel = Ext.create('MemberCustEditPanel',{'viewStatus':'update','channelType':'immediate'});
		me.items = [
		{
			xtype:'basicpanel',
			items:[me.validateMemberPanel],
			height:160
		}
		,
		{
			xtype:'basicpanel',
			items:[me.memberEditPanel],
			//height:450,
			flex:1
		}
		];
		this.callParent();
		RealTimeCreateMemberEdit.dealDisDisabled(true);
	}
});



Ext.define('RealTimeMemberCustEditPanel',{
	extend:'TitleFormPanel',
	realTimeMemberData:null,
	parent:null,
	layout : {
		type : 'hbox',
		align : 'stretch'
	},
	initComponent:function(){
		var me = this;
		me.items=me.getItems();
		this.callParent();
		//增加联系方式默认值为手机号码
		Ext.getCmp('linkWay_id').setValue('mobile');
	},
	getItems:function(){
		var me = this;
		return [
			{
				xtype:'wholepanel',
				width:305,
				items:[{
					xtype:'titlepanel',
					items:[{xtype: 'displayfield',
							value: i18n('i18n.RealTimeMemberCustEditView.eligibilityCheckCondition'),
							width:90}]
				},{
					xtype:'bottompanel',
					layout:{
						type:'table',
						columns:3,
		                hideLabel: true
				    },
				    defaults:{
						enableKeyEvents:true,
						listeners:{
							scope : me,
							keypress : me.keypressEvent
						}
				    },
				    items:[{
						xtype: 'displayfield',
						value: i18n('i18n.PotentialCustManagerView.contactName'),
						width:100
					},{
						xtype: 'displayfield',
						value:'',
						width:5
					},{
						//联系人姓名输入框
						xtype:'dptextfield',
						name:'contactName',
						width:175,
						maxLength:80,
						maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80)
			        }, {
						//联系方式 选择框
						name : 'linkWay',
						id:'linkWay_id',
						xtype : 'combobox',
						store:me.realTimeMemberData.getLinkWayStore(),
						queryMode:'local',
			            forceSelection:true,
						displayField:'linkDesc',
						valueField:'linkValue',
						width:100,
						listeners:{
							scope:me,
							keypress : me.keypressEvent,
							select:me.selectEvent
						}
					},{
						xtype: 'displayfield',
						value:'',
						width:5
					},{
						//手机、固话 电话
						xtype:'dptextfield',
						name:'linkWayNum',
						width:175,
						emptyText:'请输入联系号码！',
						regex : DpUtil.linkWayLimit('M'),
						listeners:{
							scope:me,
							keypress : me.keypressEvent,
							change:me.linkWayNumChange
						}
			        },{
			        	xtype:'basicpanel',
			        	colspan : 3,
						layout : {
							type : 'hbox',
							pack : 'end',
							align : 'top'
						},
			        	items:[{
							xtype:'button',
							id:'checkButton',
							text:i18n('i18n.RealTimeMemberCustEditView.check'),
							scope:me,
							handler:me.acquireImplementMemberView
			        	}]
			        }]
				}]
			},{
				xtype:'panel',
				border:false,
				width:10
			},
			{
				xtype:'wholepanel',
				layout:{
					type:'vbox',
					align:'stretch'
				},
				flex:2,
				items:[{
					xtype:'titlepanel',
					items:[{xtype: 'displayfield',
							value: i18n('i18n.RealTimeMemberCustEditView.eligibilityVerificationResults'),
							width:90}]
				},{
					xtype:'bottompanel',
					layout:{
						type:'table',
						columns:4
					},
				    items:[{
						xtype:'innergrid',
						autoScroll:true,
						colspan:2,
						width:343,
						height:80,
						store: me.realTimeMemberData.getWayBillInfoStore(),
						columns:[
						         new Ext.grid.RowNumberer(),
						        {
									header:i18n('i18n.Integral.waybillNumber'),
									dataIndex : 'wayBillNumber'
								},{
									header:i18n('i18n.RealTimeMemberCustEditView.amount'),
									dataIndex : 'money'
								},{
									header:i18n('i18n.RealTimeMemberCustEditView.coefficient'),
									dataIndex : 'ratio'
								}]
					},{
						xtype: 'displayfield',
						value:'',
						width:5
					},{
//						xtype:'resultfield',
						xtype:'panel',
						id:'realTime_qualified_id',
						name:'qualified',
						readOnly:true,
						hideLabel:true,
						rowspan:3,
						width:113,
						height:110
					}
					,{
						xtype: 'displayfield',
						value:'',
						colspan:3,
						width:355,
						height:2
					}
					,{
						xtype:'dpnumberfield',
						name:'totalMoney',
						fieldLabel:i18n('i18n.RealTimeMemberCustEditView.total'),
						labelSeparator:'',
						readOnly:true,
						hideTrigger: true,
						minValue: 0,
						labelWidth:30,
						colspan:2,
						width:343
					},{
						xtype: 'displayfield',
						value:'',
						width:5
					}]
				}]
			}
		];
	},
	selectEvent:function(combox,records){
		if(records.length > 0){
			var linkNum = combox.up('form').getForm().findField('linkWayNum');
			if('mobile' == combox.getValue()){
				linkNum.regex = DpUtil.linkWayLimit('M');
    	    	linkNum.regexText = i18n('i18n.RealTimeMemberCustEditView.pleaseInputElevenPhone');
    	    	combox.up('form').doLayout();
    	    	if(!Ext.isEmpty(linkNum.getValue())){
    	    		DpUtil.autoChangeMobileLength(linkNum,linkNum.getValue());
    	    	}
			}else if('tel' == combox.getValue()){
				linkNum.regex = DpUtil.linkWayLimit('T');
    	    	linkNum.regexText = i18n('i18n.RealTimeMemberCustEditView.pleaseInputCorrectPhoneMoreSeven');
    	    	combox.up('form').doLayout();
			}
		}
	},
	//监听按键事件
	keypressEvent:function(field,event){
		var me = this;
		if(event.getKey() == Ext.EventObject.ENTER){
    		me.checkEvent();
    	}
	},
	//联系号码"change"事件
	linkWayNumChange:function(field,newValue){
		if ('mobile' == Ext.getCmp('linkWay_id').getValue()){
			if(!Ext.isEmpty(field.getValue())){
    	    	DpUtil.autoChangeMobileLength(field,field.getValue());
    	    }
		}
	},
	//检验 用户是否符合 创建会员条件
	acquireImplementMemberView:function(button){
		var me = this;
		me.checkEvent();
	},
	//校验单号客户是否符合创建会员事件
	checkEvent:function(){
		var me = this;
		Ext.getCmp('checkButton').setDisabled(true);
		var form = me.getForm();
		var field = form.findField('linkWayNum');
		var newValue = field.getValue();
		var linkWay = form.findField('linkWay').getValue();//联系方式
		var linkWayNum = newValue;//联系号码
		var contactName = form.findField('contactName').getValue();//联系人名称
		//必须选择 类型 手机或者电话
		if(DpUtil.isEmpty(linkWay)||DpUtil.isEmpty(linkWayNum)||''==linkWay||''==linkWayNum){
			MessageUtil.showMessage(i18n('i18n.RealTimeMemberCustEditView.contactNumbersNotBeEmpty'));
			Ext.getCmp('checkButton').setDisabled(false);
			return;
		}
		//选择为固定电话时必须填写联系人姓名
		if(('tel' == linkWay && DpUtil.isEmpty(contactName))
				||('tel' == linkWay && ''==contactName)){
			MessageUtil.showMessage(i18n('i18n.RealTimeMemberCustEditView.inputPhoneMustInputContactName'));
			Ext.getCmp('checkButton').setDisabled(false);
			return;
		}
		//联系电话格式是否正确
		if(!form.isValid()){
			MessageUtil.showMessage(i18n('i18n.RealTimeMemberCustEditView.pleaseCheckContactNumNameIsCorrect'));
			Ext.getCmp('checkButton').setDisabled(false);
			return;
		}
		//检验会员是否存在成功
		var checkSuccess=function(response){
			//会员不存在，存在有效的散客，允许创建
			if(response.ischecked){
				//校验运单信息，看客户是否满足变为固定客户的条件
				//校验会员 成功
				var checkSuccess=function(response){
					Ext.getCmp('checkButton').setDisabled(false);
					var implementMemberView = response.implementMemberView;
					//校验按钮可用
					if(implementMemberView){
						//运单信息加载
						me.realTimeMemberData.getWayBillInfoStore().loadData(implementMemberView.qualificationView.wayBillList);
						//合计
						form.findField('totalMoney').setValue(implementMemberView.qualificationView.totalMoney);
						//显示 是否通过检验
						var qualified = implementMemberView.qualificationView.qualified;
						if(qualified){
//							form.findField('qualified').setValue(i18n('i18n.RealTimeMemberCustEditView.meetThe'));
							//实时校验会员 样式修改
							me.dealPictureCls('verifyerror','verifyok');
							
							//标识联系人数据来源于实时创建会员
							if(implementMemberView.member != null &&  implementMemberView.member.contactList.length > 0){
								implementMemberView.member.contactList[0].createSource = 2;
							}
							RealTimeCreateMemberEdit.dealDisDisabled(false);
							//加载会员数据
							me.parent.memberEditPanel.loadMemberDataAfterRender(implementMemberView.member);
							me.parent.memberEditPanel.memberCustBasicInfo.initBusinessComponent();
							//解除客户标签锁定
							me.parent.memberEditPanel.unLockCustLabels();
						}else{
//							form.findField('qualified').setValue(i18n('i18n.RealTimeMemberCustEditView.doesNotMeetThe'));
							//实时校验会员 样式修改
							me.dealPictureCls('verifyok','verifyerror');
							//锁定会员数据
							me.parent.memberEditPanel.lockAllComponents();
							RealTimeCreateMemberEdit.dealDisDisabled(true);
						}
					}
				};
				//校验会员 失败
				var checkFailure=function(response){
					Ext.getCmp('checkButton').setDisabled(false);
					//实时校验会员 样式修改
					me.dealPictureCls('verifyok','verifyerror');
					MessageUtil.showErrorMes( response.message);
					//锁定会员数据
					me.parent.memberEditPanel.lockAllComponents();
					RealTimeCreateMemberEdit.dealDisDisabled(true);
					//运单信息加载
					me.realTimeMemberData.getWayBillInfoStore().loadData([]);
					//合计
					form.findField('totalMoney').setValue();
//					form.findField('qualified').setValue(i18n('i18n.RealTimeMemberCustEditView.doesNotMeetThe'));
				}
				var linkWay = form.findField('linkWay').getValue();
				var linkWayNum = form.findField('linkWayNum').getValue();
				if(linkWayNum==null){linkWayNum='';}
				var contactName = form.findField('contactName').getValue();
				if(contactName==null){contactName='';}
				var searchParams = {
						'contactName':contactName,
						'linkWay':linkWay,
						'linkWayNum':linkWayNum
				};
				//校验运单信息，看客户是否满足变为固定客户的条件
				me.realTimeMemberData.acquireImplementMemberView(searchParams,checkSuccess,checkFailure);
				
				
			}
			//不允许创建
			if(!response.ischecked){
				//实时校验会员 样式修改
				me.dealPictureCls('verifyok','verifyerror');
				MessageUtil.showErrorMes(i18n('i18n.RealTimeMemberCustEditView.theMemberAlreadyExists'));
				//锁定会员数据
				me.parent.memberEditPanel.lockAllComponents();
				RealTimeCreateMemberEdit.dealDisDisabled(true);
				Ext.getCmp('checkButton').setDisabled(false);
				return;
			}
		};
		//检验会员是否存在失败
		var checkFailure=function(response){
			//实时校验会员 样式修改
			me.dealPictureCls('verifyok','verifyerror');
			MessageUtil.showErrorMes(response.message);
			//锁定会员数据
			me.parent.memberEditPanel.lockAllComponents();
			RealTimeCreateMemberEdit.dealDisDisabled(true);
			Ext.getCmp('checkButton').setDisabled(false);
			return false;
		}
		if(contactName==null){contactName='';}
		var searchParams = {
				'contactName':contactName,
				'linkWay':linkWay,
				'linkWayNum':linkWayNum
		};
		me.realTimeMemberData.checkIsExistMember(searchParams,checkSuccess,checkFailure);
	},
	//实时校验会员 样式修改
	dealPictureCls:function(removeCls,addCls){
		var me = this;
//		Ext.getCmp('realTime_qualified_id').removeClsWithUI(removeCls);
		Ext.getCmp('realTime_qualified_id').removeCls(removeCls);
		Ext.getCmp('realTime_qualified_id').addCls(addCls);
	}
});


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
          			//潜力类型
          			'CARGO_POTENTIAL',
          			// 上一年公司规模
          			'FIRM_SIZE',
          			//客户来源
          			'CUST_SOURCE',
          			// 信用等级
          			'CREDIT_GRADE',
          			// 地址类型
          			'ADDRESS_TYPE',
          			// 联系人类型 已取消
          			// 物流决定权
          			'LOGIST_DECI',
          			// 付款方式
          			'PAY_WAY',
          			// 偏好渠道
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
	      			'CUST_TYPE',
	      			'OPERATE_TYPE'];
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
		id:'realTimeViewPort_id',
		autoScroll:true,
		items:[Ext.create('RealTimeCreateMemberEditView')]
	});
});

