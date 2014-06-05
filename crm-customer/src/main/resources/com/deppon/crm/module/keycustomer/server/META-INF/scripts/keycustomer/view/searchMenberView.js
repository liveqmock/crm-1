/**
 * 数据字典模型model
 */
Ext.define('SearchMemberDataDictionaryModel', {
			extend : 'Ext.data.Model',
			fields : ['code', 'codeDesc']
		});
/**
 * 会员选择器, store,model和view都定义在这一个JS中
 */
Ext.define('SearchMemberMemberGradeStore', {
			extend : 'Ext.data.Store',
			model : 'SearchMemberDataDictionaryModel',
			data : null
		});

// 会员联系人查询条件
Ext.define('SearchMemberConditionModel', {
			extend : 'Ext.data.Model',
			fields : [
					// 会员所属部门ID
					{
				name : 'deptId'
			},
					// 会员客户编码
					{
						name : 'custNumber'
					},
					// 客户名称（企业或个人）
					{
						name : 'custName'
					},
					// 客户等级
					{
						name : 'custGrad'
					},
					// 潜客來源
					{
						name : 'potenSource'
					},
					// 联系人编码
					{
						name : 'linkManNumber'
					},
					// 联系人姓名
					{
						name : 'linkManName'
					},
					// 手机号码(联系人手机号)
					{
						name : 'mobile'
					},
					// 固定电话(联系人固定电话)
					{
						name : 'telePhone'
					},
					// 身份证号码
					{
						name : 'idCard'
					}]
		});
// 会员联系人查询结果
Ext.define('SearchMemberResultModel', {
			extend : 'Ext.data.Model',
			fields : [
					/** 会员管理 查询结果 */
					// 客户id
					{
				name : 'custId',
				type : 'string'
			},
					// 联系人id
					{
						name : 'contactId',
						type : 'string'
					},
					// 客户编码
					{
						name : 'custNum',
						type : 'string'
					},
					// 客户名称
					{
						name : 'custName',
						type : 'string'
					},
					// 客户等级
					{
						name : 'custGrade',
						type : 'string'
					},
					// 用于前台使用，用于分组
					{
						name : 'custGroup',
						type : 'string'
					},
					// 是否主联系人1为是，0为否
					{
						name : 'isMainLinkMan',
						type : 'boolean'
					},
					// 联系人编码
					{
						name : 'contactNum',
						type : 'string'
					},
					// 联系人姓名
					{
						name : 'contactName',
						type : 'string'
					},
					// 手机号码
					{
						name : 'mobileNum',
						type : 'string'
					},
					// 固定电话
					{
						name : 'telNum',
						type : 'string'
					},
					// 所属部门 会员管理 查询结果
					{
						name : 'deptId',
						type : 'string'
					},
					// 所属部门 会员管理 查询结果
					{
						name : 'deptName',
						type : 'string'
					},
					// 版本号
					{
						name : 'versionNumber'
					},
					/** 弹出框 会员、联系人 查询结果 */
					// 地址
					{
						name : 'address',
						type : 'string'
					},
					// 备注
					{
						name : 'remark',
						type : 'string'
					},
					// 工作流状态 1为审批中
					{
						name : 'status'
					},
					/** 弹出框 选中 会员、联系人 所需数据 */
					// 行业
					{
						name : 'trade',
						type : 'string'
					},
					// 行业
					{
						name : 'seconTrade',
						type : 'string'
					},
					// 客户类型
					{
						name : 'custType',
						type : 'string'
					},
					// 税务登记号
					{
						name : 'taxregNum',
						type : 'string'
					},
					// 身份证
					{
						name : 'idCard',
						type : 'string'
					},
					// 城市ID
					{
						name : 'cityId',
						type : 'string'
					},
					// 城市
					{
						name : 'cityName',
						type : 'string'
					}]
		});

/**
 * F7 会员联系人查询结果store
 */
Ext.define('MemberSearchStore', {
			extend : 'Ext.data.Store',
			model : 'SearchMemberResultModel',
			groupers : [{
						property : 'custGroup',
						direction : 'ASC'
					}, {
						property : 'custId',
						direction : 'ASC'
					}]
		});
Ext.define('SearchMemberData', {
	searchMemberGradeStore : null,
	searchMemberResultStore : null,
	loginUserDeptListStore : null,
	// 目标级别 目前级别 客户等级 会员等级
	getMemberGradeStore : function() {
		if (this.searchMemberGradeStore == null) {
			this.searchMemberGradeStore = getDataDictionaryByName(
					DataDictionary, 'MEMBER_GRADE');
		}
		return this.searchMemberGradeStore;
	},
	// 获取数据字典
	getDictionary : function(params, successFn, failFn) {
		Ext.Ajax.request({
					url : '../common/queryAllDataDictionaryByKeys.action',
					async : false,
					jsonData : params,
					success : function(response) {
						var result = Ext.decode(response.responseText);
						if (result.success) {
							successFn(result);
						} else {
							failFn(result);
						}
					},
					failure : function(response) {
						var result = Ext.decode(response.responseText);
						failFn(result);
					}
				});
	},
	// F7会员，联系人查询store
	getSearchMemberResultStore : function() {
		return this.searchMemberResultStore;
	},
	// F7初始化会员，联系人查询store
	initSearchMemberResultStore : function(beforeLoadTransactionFn, searchType) {
		if (this.searchMemberResultStore == null) {
//			var url = '../customer/searchMemberList.action';
//			if ('OWNDEPT' == searchType) {// 只查询本部门会员信息 (ps:谁说只会查询本部门客户的,会查询权限部门客户,这不是乱注释嘛)
//				url = '../customer/searchMemberInfoListWithAuth.action';
//			}
			var url = '../keycustomer/searchMemberInfoListWithAuth.action';
			if (beforeLoadTransactionFn != null) {
				this.searchMemberResultStore = Ext.create('MemberSearchStore',
						{
							proxy : {
								type : 'ajax',
								url : url,
								actionMethods : 'POST',
								reader : {
									type : 'json',
									root : 'memberResultList'
								}
							},
							listeners : {
								beforeload : beforeLoadTransactionFn
							}
						});
			} else {
				this.searchMemberResultStore = Ext.create('MemberSearchStore',
						{
							proxy : {
								type : 'ajax',
								url : url,
								actionMethods : 'POST',
								reader : {
									type : 'json',
									root : 'memberResultList'
								}
							}
						});
			}
		}
		return this.searchMemberResultStore;
	}
});
/**
 * 会员查询控件
 */
Ext.define('MemberSearchCombox', {
			extend : 'QueryCombox', // 继承common包中封装的
			alias : 'widget.membersearchcombox',
			name : null,
			searchWindow : null, // 弹出的查询窗口
			searchType : null, // 查询类型：OWNDEPT：本部门会员
			store : null,
			showType : null, // 展示字段对应的查询form的name,用于show之后重新放入值
			displayField : 'custName',
			valueField : 'custId',
			queryMode : 'local',
			queryDelay : 2000,
			editable : false,
			forceSelection : true,
			enableKeyEvents : true,
			initComponent : function() {
				var me = this;
				me.addListener('change', me.changeFn);
				me.addListener('expand', me.expandFn);
				me.addListener('keypress', me.keypressFn);
				this.callParent();
			},
			// 会员查询combobox的change事件
			changeFn : function(memberSearchCombox) {
				// if (Ext.isEmpty(memberSearchCombox.getValue())) {
				// memberSearchCombox.setValue(null);
				// }
			},
			// 会员查询combobox的expand事件
			expandFn : function(memberSearchCombox) {
				this.showSearchMemberWinFn(memberSearchCombox);
			},
			// 会员查询combobox的keypress事件
			keypressFn : function(memberSearchCombox, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
					this.showSearchMemberWinFn(memberSearchCombox);
				}
			},
			// 设置会员查询结果到父界面,memberResult为当前选中的联系人记录，resultRecords是查询所有的会员联系人信息
			setValueComeBack : function(memberResult, resultRecords) {
			},
			// 显示会员查询窗口
			showSearchMemberWinFn : function(memberSearchCombox) {
				if (memberSearchCombox.searchWindow == null) {
					showSearchMemberWin(memberSearchCombox, this.searchType);
				} else {
					// 把当前下拉框中的值带入打开的查询界面
					var value = memberSearchCombox.getRawValue();
					// if (Ext.isEmpty(custName)) {
					// custName = memberSearchCombox.getValue();
					// }
					memberSearchCombox.searchWindow.searchConditionForm
							.getForm().reset();
					memberSearchCombox.searchWindow.searchConditionForm
							.getForm().findField(memberSearchCombox.showType)
							.setValue(value);
					memberSearchCombox.searchWindow.show();
					// memberSearchCombox.setRawValue(value);
				}
				// 清空历史数据
				memberSearchCombox.store.removeAll();
			}
		});
/**
 * 获得会员查询界面
 */
showSearchMemberWin = function(parentCombox, searchType) {
	// 加载界面
	if (parentCombox.searchWindow == null
			&& DataDictionary.MEMBER_GRADE.length != 0) {
		var searchMemberWin = Ext.create('SearchMemberWin', {
					'searchMemberData' : new SearchMemberData(),
					'searchType' : searchType,
					'parent' : parentCombox
				});
		parentCombox.searchWindow = searchMemberWin;
		searchMemberWin.show();
		parentCombox.setValue();
	}
};
/**
 * 查询会员共用界面
 */
Ext.define('SearchMemberWin', {
	extend : 'PopWindow',
	width : 790,
	height : 350,
	title : i18n('i18n.SearchMemberView.custInfo'),
	parent : null,
	searchConditionForm : null,// 查询条件
	searchResultGrid : null, // 查询结果
	searchButtonPanel : null,// 查询按钮面板
	selectButtonPanel : null,// 确定按钮面板
	searchMemberData : null,// 数据Data
	searchType : null, // 查询类型：OWNDEPT：本部门会员
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	listeners : {
		beforeclose : function(memberSearchWin) {
			memberSearchWin.hide();
			return false;
		}
	},
	items : null,
	initComponent : function() {
		var me = this;
		var record = new SearchMemberConditionModel();
		me.searchConditionForm = Ext.create('SearchConditionForm', {
					'parent' : me.parent,
					'record' : record,
					'parentWin' : me
				});
		me.searchResultGrid = Ext.create('SearchResultGrid', {
					'searchType' : me.searchType,
					'searchMemberData' : me.searchMemberData,
					'searchConditionForm' : me.searchConditionForm
				});
		me.searchButtonPanel = Ext.create('SearchButtonPanel', {
					'searchConditionForm' : me.searchConditionForm,
					'searchMemberData' : me.searchMemberData
				});
		me.items = me.getItems();
		me.fbar = me.getFbar();
		// 设置会员查询combobox的store
		me.parent.store = me.searchMemberData.getSearchMemberResultStore();
		this.callParent();

		// 父控件的名称带入查询会员界面
		record.set('custName', me.parent.getValue());
		me.searchConditionForm.loadRecord(record);
	},
	getFbar : function() {
		var me = this;
		return [{
					xtype : 'button',
					text : i18n('i18n.ManualRewardIntegralEditView.b_ensure'),
					scope : me,
					handler : me.setMemberValueBack
				}, {
					xtype : 'button',
					text : i18n('i18n.ManualRewardIntegralEditView.b_cance'),
					handler : function() {
						me.hide();
					}
				}];
	},
	getItems : function() {
		var me = this;
		return [{
					xtype : 'basicpanel',
					height : 80,
					items : [me.searchConditionForm]
				}, me.searchButtonPanel, {
					xtype : 'basicpanel',
					flex : 1,
					items : [me.searchResultGrid]
				}];
	},
	// 把选中的会员信息返回
	setMemberValueBack : function() {
		var me = this;
		var selection = me.searchResultGrid.getSelectionModel().getSelection();
		if (selection.length != 1) {
			MessageUtil
					.showMessage(i18n('i18n.SearchMemberView.pleaseSelectCustInfoOp'))
		} else {
			// 调用父类的查询结果回调函数
			me.parent
					.setValueComeBack(selection[0], me.searchMemberData
									.getSearchMemberResultStore().getRange(),
							me.parent);
			me.hide();
		}
	}
});
/**
 * 查询条件
 */
Ext.define('SearchConditionForm', {
	extend : 'SearchFormPanel',
	id : 'searchConditionFormId',
	parentWin : null,
	layout : {
		type : 'table',
		columns : 4
	},
	defaultType : 'dptextfield',
	initComponent : function() {
		var me = this;
		me.defaults = me.getDefaultsContainer();
		me.items = me.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [{
			fieldLabel : i18n('i18n.MemberCustEditView.custNo'),
			maxLength : 40,
			maxLengthText : i18n(
					'i18n.ManualRewardIntegralEditView.m_maxLength', 40),
			name : 'custNumber'
		}, {
			fieldLabel : i18n('i18n.PotentialCustManagerView.customerName'),
			maxLength : 80,
			maxLengthText : i18n(
					'i18n.ManualRewardIntegralEditView.m_maxLength', 80),
			name : 'custName'
		}, {
			fieldLabel : i18n('i18n.PotentialCustManagerView.contactName'),
			maxLength : 80,
			maxLengthText : i18n(
					'i18n.ManualRewardIntegralEditView.m_maxLength', 80),
			name : 'linkManName'
		}, {
			fieldLabel : i18n('i18n.MemberCustEditView.contactNo'),
			maxLength : 40,
			maxLengthText : i18n(
					'i18n.ManualRewardIntegralEditView.m_maxLength', 40),
			name : 'linkManNumber'
		}, {
			fieldLabel : i18n('i18n.MemberCustEditView.mobileNo'),
			regex : linkWayLimit('M'),
			regexText : i18n('i18n.MemberCustEditView.pleaseInputCorrectMobilePhone'),
			name : 'mobile'
		}, {
			fieldLabel : i18n('i18n.SearchMemberView.phoneNumber'),
			regex : linkWayLimit('T'),
			regexText : i18n('i18n.MemberManagerView.pleaseEnterTheCorrectPhoneNumber'),
			name : 'telePhone'
		}, {
			fieldLabel : i18n('i18n.ScatterCustManagerView.idNumber'),
			hidden : true,
			maxLength : 18,
			minLength : 15,
			regex : linkWayLimit('I'),
			regexText : i18n('i18n.MemberCustEditView.cardNum15118LastxX'),
			name : 'idCard'
		}];
	},
	getDefaultsContainer : function() {
		var me = this;
		return {
			labelAlign : 'right',
			labelWidth : 65,
			width : 185,
			enableKeyEvents : true,
			listeners : {
				keypress : function(field, event) {
					if (event.getKey() == Ext.EventObject.ENTER) {
						if (!Ext.isEmpty(field.next())
								&& 'telePhone' != field.name) {
							field.next().focus();
						} else {
							me.parentWin.searchButtonPanel.searchEvent();
						}
					}
				},
				change : function(field, newValue) {
					if (field.getName() == 'mobile') {
						autoChangeMobileLength(field, newValue);
					}
				}
			}
		};
	}
});
linkWayLimit = function(regText) {
	var mobil = /^1\d{10}$/;
	var tel = /^\d{3}[\d\*-/]{4,17}$/;
	var idCard = /^([\d]{15}|[\d]{18}|[\d]{17}X)$/;
	var defaultValue = /^\d{11}$/;
	switch (regText) {
		case 'M' :
			return mobil;
			break;
		case 'T' :
			return tel;
			break;
		case 'I' :
			return tel;
			break;
		default :
			return defaultValue;
			break;
	}
};
/**
 * .
 * <p>
 * 手机号码填写过11位后自动删除多余<br/>
 * <p>
 * 
 * @param field
 *            操作控件 newValue 新值
 * @author 李学兴
 * @时间 2012-7-30
 */
autoChangeMobileLength = function(field, newValue) {
	if (newValue.length > 11) {
		field.setValue(newValue.substring(0, 11));
	}
}
Ext.define('SearchResultGrid', {
	extend : 'PopupGridPanel',
	searchConditionForm : null,
	searchMemberData : null,// 数据Data
	searchType : null,
	// selModel : Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE'}),
	initComponent : function() {
		var me = this;
		me.store = me.searchMemberData.initSearchMemberResultStore(
				me.beforeLoadScatterFn, me.searchType);
		me.columns = me.getColumns();
		this.callParent();
	},
	getColumns : function() {
		var me = this;
		return [{
					header : i18n('i18n.MemberManagerView.customerGrouping'),
					dataIndex : 'custGroup'
				}, {
					header : i18n('i18n.ChangeContactAffiliatedRelationView.custId'),
					hidden : true,
					dataIndex : 'custId'
				}, {
					header : i18n('i18n.MemberCustEditView.custNo'),
					dataIndex : 'custNum',
					hidden : true
				}, {
					header : i18n('i18n.PotentialCustManagerView.customerName'),
					dataIndex : 'custName',
					hidden : true
				}, {
					header : i18n('i18n.ManualRewardIntegralEditView.id'),
					dataIndex : 'contactId',
					hidden : true
				}, {
					header : i18n('i18n.MemberCustEditView.isMainContact'),
					dataIndex : 'isMainLinkMan',
					xtype : 'booleancolumn',
					trueText : i18n('i18n.ChangeContactAffiliatedRelationView.yes'),
					falseText : i18n('i18n.ChangeContactAffiliatedRelationView.no'),
					flex : 1
				}, {
					header : i18n('i18n.MemberCustEditView.contactNo'),
					dataIndex : 'contactNum',
					width : 150
				}, {
					header : i18n('i18n.ManualRewardIntegralEditView.name'),
					dataIndex : 'contactName',
					flex : 1
				}, {
					header : i18n('i18n.SearchMemberView.mobileTelephone'),
					dataIndex : 'mobileNum',
					flex : 1
				}, {
					header : i18n('i18n.MemberCustEditView.telephoneNo'),
					dataIndex : 'telNum',
					flex : 1
				}, {
					header : i18n('i18n.PotentialCustEditView.address'),
					dataIndex : 'address',
					flex : 1
				}, {
					header : i18n('i18n.ScatterCustManagerView.remark'),
					dataIndex : 'remark',
					flex : 1
				}];
	},
	features : [{
		ftype : 'groupingsummary',
		groupHeaderTpl : '<div style="background-color:{color}">{name} ({rows.length})</div>',
		hideGroupedHeader : true,
		enableGroupingMenu : false
	}],
	// beforeLoad方法
	beforeLoadScatterFn : function(store, operation, eOpts) {
		var searchConditionForm = Ext.getCmp('searchConditionFormId');
		if (searchConditionForm != null) {
			searchConditionForm.getForm()
					.updateRecord(searchConditionForm.record);
			// 设置请求参数
			var searchCustCondition = searchConditionForm.record.data;
			var searchParams = {
				// 会员客户编码
				'searchCustCondition.custNumber' : trimString(searchCustCondition.custNumber),
				// 客户名称（企业或个人）
				'searchCustCondition.custName' : trimString(searchCustCondition.custName),
				// 联系人编码
				'searchCustCondition.linkManNumber' : trimString(searchCustCondition.linkManNumber),
				// 联系人姓名
				'searchCustCondition.linkManName' : trimString(searchCustCondition.linkManName),
				// 手机号码(联系人手机号)
				'searchCustCondition.mobile' : trimString(searchCustCondition.mobile),
				// 固定电话(联系人固定电话)
				'searchCustCondition.telePhone' : trimString(searchCustCondition.telePhone),
				// 身份证号码
				'searchCustCondition.idCard' : trimString(searchCustCondition.idCard),
				//客户部门ID 此条件为特殊业务,其他人复制可不要这个条件
				'searchCustCondition.deptId' : trimString(User.deptId),
				//客户性质（潜散客、固客）
				'searchCustCondition.custGroup' : trimString('RC_CUSTOMER')
			};
			Ext.apply(operation, {
						params : searchParams
					});
		}
	}
});
/**
 * 查询重置按钮面板
 */
Ext.define('SearchButtonPanel', {
	extend : 'NormalButtonPanel',
	id : 'SearchMemberView_SearchButtonPanel_id',
	items : null,
	searchConditionForm : null,
	searchMemberData : null,
	initComponent : function() {
		this.items = this.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [{
					xtype : 'middlebuttonpanel'
				}, {
					xtype : 'rightbuttonpanel',
					items : [{
								xtype : 'button',
								text : i18n('i18n.PotentialCustManagerView.search'),
								scope : me,
								handler : me.searchEvent
							}, {
								xtype : 'button',
								text : i18n('i18n.MemberCustEditView.reset'),
								scope : me,
								handler : me.resetEvent
							}]
				}];
	},
	// 查询会员
	searchEvent : function() {
		var me = this;
		var serchButton = Ext.getCmp('SearchMemberView_SearchButtonPanel_id');
		if (serchButton.beforeSearch(serchButton)) {
			me.searchMemberData.getSearchMemberResultStore().loadPage(1);
		} else {
			me.searchMemberData.getSearchMemberResultStore().removeAll();
		}
	},
	// 查询前 条件校验
	beforeSearch : function(me) {
		if (Ext.isEmpty(me)) {
			me = this;
		}
		if (!me.validateCondition()) {
			MessageUtil
					.showMessage(i18n('i18n.MemberUpgradeView.message_notAllNull'));
			return false;
		}
		if (!me.searchConditionForm.getForm().isValid()) {
			MessageUtil
					.showMessage(i18n('i18n.MyWorkFlowDealManagerView.m_changeAllRight'));
			return false;
		}
		return true;
	},
	// 重置查询条件
	resetEvent : function() {
		var me = this;
		me.searchConditionForm.getForm().reset();
	},
	// 校验是否查询条件都为空
	validateCondition : function() {
		var me = this;
		var flag = false;
		me.searchConditionForm.getForm().getFields().each(function(field) {
					if (!(Ext.isEmpty(field.getValue()))) {
						flag = true;
					}
				});
		return flag;
	}
});
trimString = function(value) {
	if (!Ext.isEmpty(value)) {
		return value.trim();
	}
	return value;
};