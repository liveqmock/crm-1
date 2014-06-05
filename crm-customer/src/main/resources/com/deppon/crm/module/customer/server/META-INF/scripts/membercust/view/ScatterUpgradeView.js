//设置Data层对象
var scatterUpgradeDataControl =  (CONFIG.get("TEST"))? new ScatterUpgradeDataTest():new ScatterUpgradeData();
//var scatterUpgradeDataControl =  new ScatterUpgradeData();
/**
* 散客升级列表界面
*/
Ext.define('ScatterUpgradePanel',{
	extend:'BasicPanel',
	scatterUpgradeSearchCondition:null,      //查询条件
	scatterUpgradeResultInfo:null,  //散客升级客户
	scatterUpgradeButtonPanel:null,  //散客按钮面板
	scatterUpgradeData:null,//数据Data
	layout:{
        type:'vbox',
        align:'stretch'
    },
	items:null,
	initComponent:function(){
		var me = this;
		var record = new UpgradeConditionModel();
		me.scatterUpgradeSearchCondition = Ext.create('ScatterUpgradeSearchForm',{'scatterUpgradeData':me.scatterUpgradeData,'record':record,'parent':me});
		me.scatterUpgradeResultInfo = Ext.create('ScatterUpgradeResultGrid',{'scatterUpgradeData':me.scatterUpgradeData,'parent':me});
		me.scatterUpgradeButtonPanel = Ext.create('ScatterUpgradeButtonPanel',{'scatterUpgradeData':me.scatterUpgradeData,
																			   'parent':me});
		me.items = [{
			xtype:'basicpanel',
			height:110,
			items:[me.scatterUpgradeSearchCondition]
		},{
			xtype:'basicpanel',
			height:36,
			items:[me.scatterUpgradeButtonPanel]
		},{
			xtype:'basicpanel',
			flex:1,
			items:[me.scatterUpgradeResultInfo]
		}];
		this.callParent();
		me.scatterUpgradeSearchCondition.loadRecord(record);
		//目前级别
		var nowLevel = me.scatterUpgradeSearchCondition.getForm().findField('nowLevel');
		me.selectFirst(nowLevel);
		//统计时间
		var statisticsTime = me.scatterUpgradeSearchCondition.getForm().findField('statisticsTime');
		me.selectFirst(statisticsTime);
		//默认部门
		DpUtil.defaultDept(me.scatterUpgradeSearchCondition,'dept');
	},
	//默认选择store中第一个
	selectFirst:function(param){
		var me = this;
		param.store.on('load', function(obj) {
			if(param.store.getCount()>0){
				param.setValue(param.store.getAt(0));
			}
		});
	}
});

/**
 * 操作按钮面板
 */
Ext.define('ScatterUpgradeButtonPanel',{
	extend:'NormalButtonPanel',
	scatterUpgradeData:null,   //data层接口
	parent:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'leftbuttonpanel',
			width:210,
			items:[{xtype:'button',text:i18n('i18n.ScatterUpgradeView.addNew'),
					scope:me,
					hidden:!isPermission('/customer/scatterCreateMemberABtn.action'),
					handler:me.showCreateMemberWin},
			       {xtype:'button',
					text:i18n('i18n.ScatterCustManagerView.remark'),
					scope:me.parent.scatterUpgradeResultInfo,
					handler:me.parent.scatterUpgradeResultInfo.addRemark
					}
//					,{xtype:'button',
//					text:'删除',
//					scope:me,
//					handler:me.deleteScatterUpgrade
//					}
					]
		},{
			xtype:'middlebuttonpanel'
		},{
			xtype:'rightbuttonpanel',
			width:150,
			items:[{
				xtype:'button',
				text:i18n('i18n.PotentialCustManagerView.search'),
				hidden:!isPermission('/customer/ScatterUpgradeSBtn.action'),
				scope:me.parent.scatterUpgradeSearchCondition,
				handler:me.parent.scatterUpgradeSearchCondition.searchScatterUpgradeList
				}]
		}];
	},
	//升级会员
	showCreateMemberWin:function(){
		var me = this;
		
		var selection=me.parent.scatterUpgradeResultInfo.getSelectionModel().getSelection();
		if (selection.length != 1) {
			MessageUtil.showMessage( i18n('i18n.ScatterUpgradeView.pleaseSelectOneRecordToCreateMember'));
		}else{
			var params = {};
			params.upGradeCust = selection[0].data;
			
			//查询会员信息成功回调函数
			var searchMemberSucssFn = function(result){
				var member = result.member;
				if(member.deptId!==selection[0].data.belongDeptId){
					MessageUtil.showMessage(i18n('i18n.ScatterUpgradeView.userOnlyCreateDeptMember'));
					return;
				}
				//设置后台传入的会员对应的联系人是从散客升级列表而来
				if(member.contactList.length > 0){
					member.contactList[0].createSource = 1;
				}
//				var accountList = member.accountList;

				// 会员联系人 增加 id
			//	var tempContactId = MemberCustEditView.getSequence();
			//	member.contactList[0].id=tempContactId;
				// 账户accountList循环加上 账号id和联系人id
//				for(var i in member.accountList){
//					member.accountList[i].id=MemberCustEditView.getSequenceAddress();
//					member.accountList[i].financeLinkmanId=null;
//					member.accountList[i].financeLinkman = null;
//					member.accountList[i].linkManMobile = null;
//					member.accountList[i].linkmanPhone = null;
//				}
//				//先将accountList的值去走，并在wiondow显示出来之前将其清空掉
//				member.accountList=accountList;
								
				var memberCustEditWindow = Ext.create('MemberCustEditWindow',{'member':member,
					id:'menberCustWindow4Up',
				   'scatterUpgradeRecord':selection[0],
				   'updateMemberParent':me.parent,
				   'y':30,//距离页面最顶端 像素距离
				   'channelType':'normal',
				   'viewStatus':'update',
				   'memberPage':'scatterUpgrade'
				});	
				memberCustEditWindow.show();
				
//				if(!DpUtil.isEmpty(member.accountList)){
//				   MessageUtil.showQuestionMes(i18n('i18n.ScatterUpgradeView.accountFriendlyNotice1')+ member.accountList.length
//						   + i18n('i18n.ScatterUpgradeView.accountFriendlyNotice2'), function(e) {
//						if (e == 'yes') {
//							var scatterCustAccountWin = Ext.create('ScatterCustAccountWin',{'scatterAccountData':member.accountList,
//																						'memberCustEditWindow':memberCustEditWindow});
//							//设置在窗口弹出的时候自动显示账号tab
//							memberCustEditWindow.down('tabpanel').setActiveTab(memberCustEditWindow.down('basicpanel').memberAccountInfo);
//							scatterCustAccountWin.show();
//						}
//					});
//				}
			};
			
			//查询会员信息失败回调函数 
			var searchMemberFailFn = function(result){
				if(DpUtil.isEmpty(result.message)){
					result.message = i18n('i18n.ScatterUpgradeView.pleaseAccordingToRecordSearchMemberInfoFailure');
				}
				MessageUtil.showMessage(result.message);
			};
			
			//查询会员信息
			me.scatterUpgradeData.searchMemberFromScatter(params,searchMemberSucssFn,searchMemberFailFn);
		}		
	},
	//删除散客升级列表数据
	deleteScatterUpgrade:function(button){
		var me = this;
		var grid = me.parent.scatterUpgradeResultInfo;
		var selection=grid.getSelectionModel().getSelection();
		if (selection.length <= 0) {
			MessageUtil.showMessage(i18n('i18n.ManualRewardIntegralEditView.m_selectOnlyOne'));
		}else{
			var params = {};
			var scatterUpgradeIds = "";
			for(var i in selection){
				if(i == (selection.length-1)){
					scatterUpgradeIds = scatterUpgradeIds+"'"+selection[i].get('id')+"'";
				}else{
					scatterUpgradeIds = scatterUpgradeIds+"'"+selection[i].get('id')+"',";
				}
			}
			params.upGradeCustId = scatterUpgradeIds;
			//查询会员信息成功回调函数
			var delSucssFn = function(result){
				MessageUtil.showMessage(i18n('i18n.RecordExchangeRuleManagerView.deleteSuccess '));
				grid.store.loadPage(1);
			};
			//查询会员信息失败回调函数 
			var delFailFn = function(result){
				MessageUtil.showMessage(result.message);
			};
			//查询会员信息
			me.scatterUpgradeData.deleteScatterUpgrade(params,delSucssFn,delFailFn);
		}
	}
});

/**
 * 散客升级查询条件
 */
Ext.define('ScatterUpgradeSearchForm',{
	extend:'SearchFormPanel',
	id:'scatterUpgradeSearchFormId',
	layout:{
		type:'table',
		columns:3
	},
	defaultType:'dptextfield',
	border:false,
	record:null,
	scatterUpgradeData:null,
	parent:null,
	initComponent:function(){
		var me = this;
		me.defaults = me.getDefaultsContainer();
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'belongdeptcombox',
			functionName :'MemberFunction',
			fieldLabel:i18n('i18n.ScatterUpgradeView.belongdeptName')
		},{
			fieldLabel:i18n('i18n.ScatterUpgradeView.statisticalDate'),
			xtype : 'combobox',
			name : 'statisticsTime',
			store:me.scatterUpgradeData.getStatisticsTimeStore(),
			queryMode:'local',
            forceSelection:true,
            allowBlank:false,
			displayField:'dateDesc',
			valueField:'dateValue',
			enableKeyEvents:true
		},
		{
			fieldLabel:i18n('i18n.PotentialCustManagerView.customerName'),
			maxLength:80,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80),
			name:'custName'
		}
		,{
			fieldLabel:i18n('i18n.PotentialCustManagerView.contactName'),
			maxLength:80,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80),
			name:'contactName'
		},{
			fieldLabel:i18n('i18n.PotentialCustManagerView.contactPhone'),
			name:'phone',
			regex : DpUtil.linkWayLimit('M')
		},{
			fieldLabel:i18n('i18n.PotentialCustManagerView.contactTel'),
			name:'tel',
			regex : DpUtil.linkWayLimit('T')
		},{
			fieldLabel:i18n('i18n.ScatterUpgradeView.nowLevel'),
			xtype : 'combobox',
			name:'nowLevel',
			store:me.scatterUpgradeData.getScatterGradeStore(),
			queryMode:'local',
			readOnly:true,
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code'
		},{
			fieldLabel:i18n('i18n.ScatterUpgradeView.targetLevel'),
			xtype : 'combobox',
			name:'targetLevel',
			store:me.scatterUpgradeData.getMemberGradeStore(),
			queryMode:'local',
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code'
		},{
			fieldLabel:i18n('i18n.ScatterCustManagerView.memberNo'),
			name:'custNumber'
		}];
	},
	//增加监听事件
	getDefaultsContainer:function(){
		var me = this;
		return {
			labelWidth : 80,
			width : 235,
			enableKeyEvents:true,
			listeners:{
				scope : me,
				keypress : me.keypressEvent,
				change : me.changeEvent
			}
		};
	},
	//监听按键事件
	keypressEvent:function(field,event){
		var me = this;
		if(event.getKey() == Ext.EventObject.ENTER){
    		me.searchScatterUpgradeList();
    	}
	},
	//监听change事件
	changeEvent:function(field,newValue){
		var me = this;
		//如果是数据字典或所属部门组件 则显示只选
		if(('belongdeptcombox' == field.getXType() || 'combobox' == field.getXType()) 
			&& Ext.isEmpty(newValue)){
			field.setValue(null);
		}else{
			if(field.getName()=='phone'){
				DpUtil.autoChangeMobileLength(field,newValue);
			}
		}
	},
	//散客升级列表查询
	searchScatterUpgradeList:function(){
		var me = this;
		if(!me.validateCondition()){
			MessageUtil.showErrorMes(i18n('i18n.MemberUpgradeView.message_notAllNull'));
			return;
		}
		if(!me.getForm().isValid()){
			MessageUtil.showErrorMes(i18n('i18n.MyWorkFlowDealManagerView.m_changeAllRight'));
		return;
		}
		me.scatterUpgradeData.getScatterUpGradeCustStore().loadPage(1);
		me.scatterUpgradeData.getScatterUpGradeCustStore().on('load',function(s,rs){
			if(s.getCount()<=0){
				MessageUtil.showErrorMes(i18n('i18n.ScatterUpgradeView.notFoundAnyScatter'));
			}
		});
	},
	//校验是否查询条件都为空
	validateCondition:function(){
		var me = this;
		var flag = false;
		me.getForm().getFields().each(function(field){
			if(!(DpUtil.isEmpty(field.getValue()))){
				flag = true;
			}
		});
		return flag;
	}
});

/**
 * 散客升级结果
 */
Ext.define('ScatterUpgradeResultGrid',{
	extend:'SearchGridPanel',
	parent:null,
	scatterUpgradeData:null,
	initComponent:function(){
		var me = this;
		me.store=me.scatterUpgradeData.initScatterUpGradeCustStore(me.beforeLoadScatterFn);
//		me.selModel = Ext.create('Ext.selection.CheckboxModel',{'mode':'SINGLE'});//只允许单选
		me.columns = me.getColumns();
		me.dockedItems = me.getMyDockedItems();
		me.listeners = me.getMyListeners();
		this.callParent();
	},
	getColumns:function(){
		var me = this;
		return [{
			header : i18n('i18n.ScatterUpgradeView.belongDeptId'),
			hidden : true,
			dataIndex : 'belongDeptId'
		}, {
			header : i18n('i18n.ScatterUpgradeView.fitId'),
			hidden : true,
			dataIndex : 'fitId'
		},  {
			header : i18n('i18n.ScatterUpgradeView.custId'),
			hidden : true,
			dataIndex : 'custId'
		},  {
			header : i18n('i18n.ScatterUpgradeView.belongdeptName'),
			dataIndex : 'belongdeptName'
		},{
			header : i18n('i18n.ScatterCustManagerView.memberNo'),
			dataIndex:'custNumber'
		},  {
			header : i18n('i18n.PotentialCustManagerView.customerName'),
			dataIndex : 'custName'
		}, {
			header : i18n('i18n.PotentialCustManagerView.contactName'),
			dataIndex : 'contactName'
		}, {
			header : i18n('i18n.MemberUpgradeView.linkPhone'),
			dataIndex : 'contactPhone'
		}, {
			header : i18n('i18n.ScatterUpgradeView.phone'),
			dataIndex : 'contactTel'
		}, {
			header : i18n('i18n.ScatterUpgradeView.nowLevel'),
			dataIndex : 'currentlevel',
			hidden:true,
			renderer  : function(value){
				return DpUtil.changeDictionaryCodeToDescrip(value,ScatterGradeStoreData);
			}
		}, {
			header : i18n('i18n.ScatterUpgradeView.targetLevel'),
			dataIndex : 'targetLevel',
			renderer  : function(value){
				return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.MEMBER_GRADE);
			}
		}, {
			header : i18n('i18n.ScatterUpgradeView.remarkInfo'),
			dataIndex : 'remark'
		}, {
			header : i18n('i18n.PotentialCustManagerView.createTime'),
			dataIndex : 'createDate',
			renderer : DpUtil.renderDate
		}, {
			header : i18n('i18n.RewardRuleEditView.modified'),
			dataIndex : 'modifyDate',
			renderer : DpUtil.renderDate
		}];
	},
	//分页条
	getMyDockedItems :function(){ 
		var me = this;
		return [ {
			xtype : 'pagingtoolbar',
			store : me.store,
			dock : 'bottom',
			displayInfo : true
		} ];
	},
	//双击事件
	getMyListeners :function(){ 
	   var me = this;
	   return {
		   scope:me,
		   itemdblclick : function(){
		   }
		   };
   },
	addRemark:function(){
		var me = this;
		selection=me.getSelectionModel().getSelection();
		if (selection.length != 1) {
			MessageUtil.showMessage( i18n('i18n.PotentialCustManagerView.operation_message'));
			return;
		}
		warnWin = Ext.create('Ext.window.Window',{
			title:i18n('i18n.ScatterUpgradeView.inputRemark'),
			width:305,
			height:150,
			cls:'warningwin',
			layout:'fit',
			items:[{
    				xtype : 'textareafield',
    				id:'remark_textareafield_id',
    				value:selection[0].get('remark')
			}],
		    dockedItems: [{
			    xtype: 'toolbar',
			    dock: 'bottom',
			    ui: 'footer',
			    layout: {
	                pack: 'center'
	            },
//						            defaults: {minWidth: minButtonWidth},
			    items: [
			        { xtype: 'component', flex: 1 },
			        { xtype: 'button', text:i18n('i18n.MemberCustEditView.ensure'),
		    			handler:function(){
		    				var text = Ext.getCmp('remark_textareafield_id').getValue();
			    			if(!Ext.isEmpty(text)){
			    				if (text.length>100) {
				        			MessageUtil.showMessage(i18n('i18n.ScatterUpgradeView.remarksSmall100'));
				        			return;
				        		}
			    				selection[0].set('remark',text);
				        		//增加备注成功回调函数
								var addRemarkSuccess = function(response){
					        		//通过id重新查询成功回调函数
									var searchByIdSuccess = function(response){
										me.store.remove(selection[0]);
										me.store.insert(0,response.upGradeCust);
									};
					        		//通过id重新查询失败调函数
									var searchByIdFail = function(response){
										MessageUtil.showMessage( response.message);
									};
									var params = {};
									params.upGradeCustId = selection[0].getId();
						        	me.scatterUpgradeData.searchScatterUpgradeById(params,searchByIdSuccess,searchByIdFail);
								};
								//增加备注失败回调函数
								var addRemarkFail = function(response) {
									MessageUtil.showMessage( response.message);
								};
								var params = {};
								params.upGradeCustId = selection[0].get('id');
								params.remark = selection[0].get('remark');
				        		me.scatterUpgradeData.addRemark2UpgradeScatter(params,addRemarkSuccess,addRemarkFail);
			    				warnWin.close();
			    			}else{
			    				MessageUtil.showMessage(i18n('i18n.PotentialCustManagerView.remarkCannotNull'));
			    			}
		    			}
			        },
			        { xtype: 'button', text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
		    		handler:function(){warnWin.close();}
			        }]
			}]
		});
		warnWin.show();
//        Ext.MessageBox.prompt(i18n('i18n.ScatterUpgradeView.inputAlert'), i18n('i18n.ScatterUpgradeView.inputRemark'), function(btn,text){
//        	if(btn == 'ok'){
//        		if (Ext.isEmpty(text)) {
//        			MessageUtil.showMessage(i18n('i18n.PotentialCustManagerView.remarkCannotNull'));
//					return;
//				}
//        		if (text.length>100) {
//        			MessageUtil.showMessage(i18n('i18n.ScatterUpgradeView.remarksSmall100'));
//        			return;
//        		}
//        		//增加备注信息
//        		selection[0].set('remark',text);
//        		//增加备注成功回调函数
//				var addRemarkSuccess = function(response){
//	        		//通过id重新查询成功回调函数
//					var searchByIdSuccess = function(response){
//						me.store.remove(selection[0]);
//						me.store.insert(0,response.upGradeCust);
//					};
//	        		//通过id重新查询失败调函数
//					var searchByIdFail = function(response){
//						MessageUtil.showMessage( response.message);
//					};
//					var params = {};
//					params.upGradeCustId = selection[0].getId();
//		        	me.scatterUpgradeData.searchScatterUpgradeById(params,searchByIdSuccess,searchByIdFail);
//				};
//				//增加备注失败回调函数
//				var addRemarkFail = function(response) {
//					MessageUtil.showMessage( response.message);
//				};
//				var params = {};
//				params.upGradeCustId = selection[0].get('id');
//				params.remark = selection[0].get('remark');
//        		me.scatterUpgradeData.addRemark2UpgradeScatter(params,addRemarkSuccess,addRemarkFail);
//        	}
//        	else{}
//        },me,1,selection[0].get('remark'));
    },
	//beforeLoad方法
	beforeLoadScatterFn:function(store, operation, eOpts){
		var scatterUpgradeSearchForm = Ext.getCmp('scatterUpgradeSearchFormId');
		if(scatterUpgradeSearchForm!=null){
			scatterUpgradeSearchForm.getForm().updateRecord(scatterUpgradeSearchForm.record);
			//设置请求参数
			var searchCustomerCondition = scatterUpgradeSearchForm.record.data;
			var searchParams = { 
					//所属部门ID
					'scatterUpGradeCondition.dept': searchCustomerCondition.dept,
					//统计时间
					'scatterUpGradeCondition.statisticsTime':searchCustomerCondition.statisticsTime,
					//客户名称
					'scatterUpGradeCondition.custName':searchCustomerCondition.custName,
					//联系人姓名
					'scatterUpGradeCondition.contactName':searchCustomerCondition.contactName,
					//联系人手机
					'scatterUpGradeCondition.phone':searchCustomerCondition.phone,
					//联系人电话
					'scatterUpGradeCondition.tel':searchCustomerCondition.tel,
					//目前级别
					'scatterUpGradeCondition.nowLevel':searchCustomerCondition.nowLevel,
					//目前级别
					'scatterUpGradeCondition.custNumber':searchCustomerCondition.custNumber,
					//目标级别
					'scatterUpGradeCondition.targetLevel':searchCustomerCondition.targetLevel};
			Ext.apply(operation,{
				params : searchParams
				}
			);	
		}
	}
});


Ext.onReady(function(){
	var params = [
      			// 目标级别i18n('i18n.ChangeContactAffiliatedRelationView.nowLevelCustLevel'),会员等级
      			'MEMBER_GRADE',
      			// 客户行业
      			'TRADE',
      			// 客户属性
      			'CUSTOMER_NATURE',
      			//潜客来源
      			'CUST_SOURCE',
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
      			//客户类别
      			'CUST_CATEGORY',
      			//账户性质
      			'ACCOUNT_NATURE',
      			//账户用途
      			'ACCOUNT_USE',
      			//性别
      			'GENDER',
      			//是否接受营销信息
      			'MARKETINFO',
      			//是否接受营销信息
      			'CUST_TYPE',
      			//联系人证件类型
      			'CARDTYPECON',
      			//税务登记号证件类型
      			'CARDTYPECUST',
      			//证件类型新增和修改(大区总以上权限)
      			'CARDTYPECON_NOTVIEW',
      			//证件类型新增和修改
      			'CARDTYPECON_AU'];
	initDataDictionary(params);
	//初始化提示
    Ext.QuickTips.init();
    Ext.tip.QuickTipManager.init();
	//初始化自定义的数据字典--联系人类型
	scatterUpgradeDataControl.initSelfDefineLinkmanType(DataDictionary);		
	Ext.create('Ext.container.Viewport',{
		layout:'fit',
//			autoScroll:true,
		items:[Ext.create('ScatterUpgradePanel',{'scatterUpgradeData':scatterUpgradeDataControl})]
	});
});


