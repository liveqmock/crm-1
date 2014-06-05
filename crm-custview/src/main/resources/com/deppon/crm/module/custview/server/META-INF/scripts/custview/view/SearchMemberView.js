//设置Data层对象
var searchMemberDataControl =  (CONFIG.get("TEST"))? new MemberViewDataTest():new SearchMemberData();
//var searchMemberDataControl =  new SearchMemberData();

SearchMemberView = function(){};

/**
 * 使用说明：xtype:'membersearchcombox' 使用该会员查询界面控件
 * 如果不仅仅需要会员的信息，可以在定义该控件增加一个属性方法setValueComeBack,传入的参数是SearchMemberResultModel类型的model
 */
/**
 * 会员查询控件
 */
Ext.define('MemberSearchCombox',{
	extend:'QueryCombox',   //继承common包中封装的
	alias : 'widget.membersearchcombox',
	fieldLabel:i18n('i18n.SearchMember.memberName'),
	name:null,
	searchWindow:null,   //弹出的查询窗口
	store:null,
	displayField:'custName',
	valueField:'custId',
	queryMode:'local',
	queryDelay:2000,
	forceSelection:true,
	enableKeyEvents:true,
	initComponent:function(){
		var me = this;
		me.addListener('change',me.changeFn);
		me.addListener('expand',me.expandFn);
		me.addListener('keypress',me.keypressFn);
		this.callParent();
	},
	//会员查询combobox的change事件
	changeFn:function(memberSearchCombox){
		if(Ext.isEmpty(memberSearchCombox.getValue())){
			memberSearchCombox.setValue(null);
		}
	},
	//会员查询combobox的expand事件
	expandFn:function(memberSearchCombox){
		this.showSearchMemberWinFn(memberSearchCombox);
	},
	//会员查询combobox的keypress事件
	keypressFn:function(memberSearchCombox,e){
		if(e.getKey() == Ext.EventObject.ENTER){
			this.showSearchMemberWinFn(memberSearchCombox);
		}
	},
	//设置会员查询结果到父界面,memberResult为当前选中的联系人记录，resultRecords是查询所有的会员联系人信息
	setValueComeBack:function(memberResult,resultRecords){
	},
	//显示会员查询窗口
	showSearchMemberWinFn:function(memberSearchCombox){
		if(memberSearchCombox.searchWindow == null){
			SearchMemberView.showSearchMemberWin(memberSearchCombox);
		}else{
			//把当前下拉框中的值带入打开的查询界面
			var custName = memberSearchCombox.getRawValue();
			if(Ext.isEmpty(custName)){
				custName = memberSearchCombox.getValue();
			}
			memberSearchCombox.searchWindow.loadCustName(custName);
			memberSearchCombox.searchWindow.show();
		}
		//清空历史数据
		memberSearchCombox.store.removeAll();
	}
});

/**
 * 获得会员查询界面
 */
SearchMemberView.showSearchMemberWin = function(parentCombox){
	var params =[// 客户等级
  			     'MEMBER_GRADE'];
     initDataDictionary(params);
	//加载界面
	if(parentCombox.searchWindow == null && DataDictionary.MEMBER_GRADE.length!=0){
		var searchMemberWin = Ext.create('SearchMemberWin',{'searchMemberData':searchMemberDataControl,
															'parent':parentCombox});
		parentCombox.searchWindow = searchMemberWin;
		searchMemberWin.show();
	}
};

/**
* 查询会员共用界面
*/
Ext.define('SearchMemberWin',{
	id:'searchMemberWinId',
	extend:'PopWindow',
	width:790,
	height:350,
	title:i18n('i18n.SearchMember.memberInfo'),
	y:50,
	parent:null,
	searchConditionForm:null,//查询条件
	searchResultGrid:null,  //查询结果
	searchButtonPanel:null,//查询按钮面板
	selectButtonPanel:null,//确定按钮面板
	searchMemberData:null,//数据Data
	layout:{
        type:'vbox',
        align:'stretch'
    },
    listeners:{
    	beforeclose:function(memberSearchWin){
    		memberSearchWin.hide();
    		return false;
    	}
    },
	items:null,
	initComponent:function(){
		var me = this;
		var record = new SearchMemberConditionModel();
		me.searchConditionForm = Ext.create('SearchConditionForm',{'parent':me.parent,'record':record,'parentWin':me});
		me.searchResultGrid = Ext.create('SearchResultGrid',{'searchMemberData':me.searchMemberData,'searchConditionForm':me.searchConditionForm});
		me.searchButtonPanel = Ext.create('SearchButtonPanel',{'searchConditionForm':me.searchConditionForm,'searchMemberData':me.searchMemberData});
		me.items = me.getItems();
		me.fbar = me.getFbar();
		//设置会员查询combobox的store
		me.parent.store = me.searchMemberData.getSearchMemberResultStore();
		this.callParent();
		
		//父控件的名称带入查询会员界面
		record.set('custName',me.parent.getValue());
		me.searchConditionForm.loadRecord(record);
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.SearchMember.determine'),
			scope:me,
			handler:me.setMemberValueBack
		},{
			xtype:'button',
			text:i18n('i18n.memberView.cancel'),
			handler:function(){
				me.hide();
			}
		}];
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'basicpanel',
			height:80,
			items:[me.searchConditionForm]
		},
		me.searchButtonPanel,
		{
			xtype:'basicpanel',
			flex:1,
			items:[me.searchResultGrid]
		}
		];
	},
	//把选中的会员信息返回
	setMemberValueBack:function(){
		var me = this;
		var selection=me.searchResultGrid.getSelectionModel().getSelection();
		if(selection.length != 1){
			MessageUtil.showMessage(i18n('i18n.SearchMember.pleaseSelectMemberToOprator'))
		}else{
			//调用父类的查询结果回调函数
			me.parent.setValueComeBack(selection[0],me.searchMemberData.getSearchMemberResultStore().getRange());
//			me.parent.setValue(selection[0].get('custId'));
			me.hide();
		}
	},
	//加载数据
	loadCustName:function(custName){
		var me = this;
		//清空查询条件
		me.searchConditionForm.getForm().reset();
		me.searchConditionForm.getForm().updateRecord(me.searchConditionForm.record);
		
		if(!Ext.isEmpty(custName)){
			var searchRecord = me.searchConditionForm.record;
			searchRecord.set('custName',custName);
			me.searchConditionForm.loadRecord(searchRecord);
		}
	}
});

/**
 * 查询重置按钮面板
 */
Ext.define('SearchButtonPanel',{
	extend:'NormalButtonPanel',
	id:'SearchMemberView_SearchButtonPanel_id',
	items:null,
	searchConditionForm:null,
	searchMemberData:null,
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
					text:i18n('i18n.memberView.search'),
					scope:me,
					handler:me.searchEvent
				},{
					xtype:'button',
					text:i18n('i18n.memberView.reset'),
					scope:me,
					handler:me.resetEvent
				}]
		}];
	},
	//查询会员
	searchEvent:function(){
		var me = this;
		if(me.beforeSearch(Ext.getCmp('SearchMemberView_SearchButtonPanel_id'))){
			me.searchMemberData.getSearchMemberResultStore().loadPage(1);
		}else{
			me.searchMemberData.getSearchMemberResultStore().removeAll();
		}
	},
	//查询前 条件校验
	beforeSearch:function(me){
		if(Ext.isEmpty(me)){
			me = this;
		}
		if(!me.validateCondition()){
			MessageUtil.showMessage(i18n('i18n.SearchMember.searchConditionNotNull'));
			return false;
		}
		if(!me.searchConditionForm.getForm().isValid()){
			MessageUtil.showMessage(i18n('i18n.SearchMember.pleaseCheckConditionIsRight'));
			return false;
		}
		return true;
	},
	//重置查询条件
	resetEvent:function(){
		var me = this;
		me.searchConditionForm.getForm().reset();
	},
	//校验是否查询条件都为空
	validateCondition:function(){
		var me = this;
		var flag = false;
		me.searchConditionForm.getForm().getFields().each(function(field){
			if(!(Ext.isEmpty(field.getValue()))){
				flag = true;
			}
		});
		return flag;
	}
});
/**
 * 查询条件
 */
Ext.define('SearchConditionForm',{
	extend:'SearchFormPanel',
	id:'searchConditionFormId',
	parentWin:null,
	layout:{
		type:'table',
		columns:4
	},
	defaultType:'textfield',
	initComponent:function(){
		var me = this;
		me.defaults = me.getDefaultsContainer();
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [
		{
			fieldLabel:i18n('i18n.memberView.customerCode'),
			maxLength:40,
//			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',40),
			name:'custNumber'
		},{
			fieldLabel:i18n('i18n.memberView.customerName'),
			maxLength:80,
//			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80),
			name:'custName'
		},{
			fieldLabel:i18n('i18n.memberView.contactName'),
			maxLength:80,
//			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80),
			name:'linkManName'
		},{
			fieldLabel:i18n('i18n.memberView.contactCoding'),
			maxLength:40,
//			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',40),
			name:'linkManNumber'
		},{
			fieldLabel:i18n('i18n.memberView.mobileNumber'),
			regex : DpUtil.linkWayLimit('M'),
//			regexText:i18n('i18n.MemberCustEditView.pleaseInputCorrectMobilePhone'),
			name:'mobile'
		},{
			fieldLabel:i18n('i18n.memberView.telephoneNumber'),
			regex : DpUtil.linkWayLimit('T'),
//			regexText:i18n('i18n.MemberManagerView.pleaseEnterTheCorrectPhoneNumber'),
			name:'telePhone'
		},{
			fieldLabel:i18n('i18n.SearchMember.IDCards'),
			maxLength:50,
//			regexText:i18n('i18n.MemberCustEditView.cardNum15118LastxX'),
			name:'idCard'
		},{
			fieldLabel:MemberViewData.prototype.registrationLableShow(/*hbf 登记号 显示处理 */),
			name:'taxregNumber'
		}];
	},
	getDefaultsContainer:function(){
		var me = this;
		return {
			labelAlign: 'right',
			labelWidth : 65,
			width : 185,
			enableKeyEvents:true,
			listeners:{
				keypress:function(field,event){
			    	if(event.getKey() == Ext.EventObject.ENTER){
			    		if(!Ext.isEmpty(field.next())){
			    			field.next().focus();
			    		}else{
			    			me.parentWin.searchButtonPanel.searchEvent();
			    		}
			    	}
			    }
			}
		};
	}
});

/**
 * 查询结果
 */
Ext.define('SearchResultGrid',{
	extend:'PopupGridPanel',
	id:'SearchResultGridId',
	searchConditionForm:null,
	searchMemberData:null,//数据Data
//	selModel : Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE'}),
	initComponent:function(){
		var me = this;
		me.store = me.searchMemberData.initSearchMemberResultStore(me.beforeLoadScatterFn);
		me.columns = me.getColumns();
		this.callParent();
	},
	getColumns:function(){
		var me = this;
		return [{
			header:i18n('i18n.SearchMember.custGrou'),
			dataIndex:'custGroup'
		},{
			header : i18n('i18n.SearchMember.custId'),
			hidden:true,
			dataIndex:'custId'
		},{
			header : i18n('i18n.SearchMember.custNum'),
			dataIndex:'custNum',
			hidden:true
		},{
			header : i18n('i18n.SearchMember.custNum'),
			dataIndex:'custName',
			hidden:true 
		},{
			header : i18n('i18n.SearchMember.custNum'),
			dataIndex:'contactId',
			hidden:true 
		},{
			header : i18n('i18n.SearchMember.isMainLinkMan'),
			dataIndex:'isMainLinkMan',
			xtype:'booleancolumn',
			trueText: i18n('i18n.memberView.yes'),
	        falseText: i18n('i18n.memberView.no')
		},{
			header : i18n('i18n.SearchMember.contactNum'),
			dataIndex:'contactNum'
		},{
			header : i18n('i18n.SearchMember.contactName'),
			dataIndex:'contactName'
		},{
			header : i18n('i18n.SearchMember.movePhone'),
			dataIndex:'mobileNum'
		},{
			header : i18n('i18n.memberView.telephoneNumber'),
			dataIndex:'telNum'
		}];
	},
	features: [{
       ftype: 'groupingsummary',
       groupHeaderTpl: '{name} ({rows.length})',
       hideGroupedHeader: true,
       enableGroupingMenu: false
    }],
    listeners: {
        celldblclick: {
            fn: function(){ 
            	Ext.getCmp('searchMemberWinId').setMemberValueBack();
            }
        }
    },
	//beforeLoad方法
	beforeLoadScatterFn:function(store, operation, eOpts){
		var searchConditionForm = Ext.getCmp('searchConditionFormId');
		if(searchConditionForm!=null ){
			searchConditionForm.getForm().updateRecord(searchConditionForm.record);
			//设置请求参数
			var searchCustCondition = searchConditionForm.record.data;
			var searchParams = {
						// 会员客户编码
					'searchCustCondition.custNumber':DpUtil.trimString(searchCustCondition.custNumber),
						// 客户名称（企业或个人）
					'searchCustCondition.custName':DpUtil.trimString(searchCustCondition.custName),
						// 联系人编码
					'searchCustCondition.linkManNumber':DpUtil.trimString(searchCustCondition.linkManNumber),
						// 联系人姓名
					'searchCustCondition.linkManName':DpUtil.trimString(searchCustCondition.linkManName),
						// 手机号码(联系人手机号)
					'searchCustCondition.mobile':DpUtil.trimString(searchCustCondition.mobile),
						//固定电话(联系人固定电话)
					'searchCustCondition.telePhone':DpUtil.trimString(searchCustCondition.telePhone),
						//身份证号码
					'searchCustCondition.idCard':DpUtil.trimString(searchCustCondition.idCard),
					//税务登记号
					'searchCustCondition.taxregNumber':DpUtil.trimString(searchCustCondition.taxregNumber)
			};
			Ext.apply(operation,{
				params : searchParams
				}
			);	
		}
	}
});


