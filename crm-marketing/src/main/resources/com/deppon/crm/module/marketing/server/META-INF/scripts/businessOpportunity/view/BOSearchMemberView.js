//设置Data层对象
var searchMemberDataControlBO =  (CONFIG.get("TEST"))? new MemberViewDataTest():new BOSearchMemberData();
//var searchMemberDataControlBO =  new BOSearchMemberData();

SearchMemberView = function(){};

/**
 * 使用说明：xtype:'bomembersearchcombox' 使用该会员查询界面控件
 * 如果不仅仅需要会员的信息，可以在定义该控件增加一个属性方法setValueComeBack,传入的参数是BOSearchMemberResultModel类型的model
 */
/**
 * 会员查询控件
 */
Ext.define('BOMemberSearchCombox',{
	extend:'QueryCombox',   //继承common包中封装的
	alias : 'widget.bomembersearchcombox',
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
		this.showBOSearchMemberWinFn(memberSearchCombox);
	},
	//会员查询combobox的keypress事件
	keypressFn:function(memberSearchCombox,e){
		if(e.getKey() == Ext.EventObject.ENTER){
			this.showBOSearchMemberWinFn(memberSearchCombox);
		}
	},
	//设置会员查询结果到父界面,memberResult为当前选中的联系人记录，resultRecords是查询所有的会员联系人信息
	setValueComeBack:function(memberResult,resultRecords){
	},
	//显示会员查询窗口
	showBOSearchMemberWinFn:function(memberSearchCombox){
/*		if(memberSearchCombox.searchWindow == null){
			SearchMemberView.showBOSearchMemberWin(memberSearchCombox);
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
		memberSearchCombox.store.removeAll();*/
		
		
		
		
		var win=Ext.getCmp("bosearchMemberWinId");//获取win
		if(!win){
			win= Ext.create('BOSearchMemberWin',{
				'searchMemberData':searchMemberDataControlBO,
				'parent':memberSearchCombox
			});
		}
		else{
			win.destroy();
			win.searchResultGrid.destroy();
			win= Ext.create('BOSearchMemberWin',{
				'searchMemberData':searchMemberDataControlBO,
				'parent':memberSearchCombox
			});
		}
		memberSearchCombox.searchWindow = win;
		//把当前下拉框中的值带入打开的查询界面
		var custName = memberSearchCombox.getRawValue();
		if(Ext.isEmpty(custName)){
			custName = memberSearchCombox.getValue();
		}
		memberSearchCombox.searchWindow.loadCustName(custName);
		//清空历史数据
		memberSearchCombox.store.removeAll();
		win.show();
	}
});

/**
 * 获得会员查询界面
 */
SearchMemberView.showBOSearchMemberWin = function(parentCombox){
	var params =[// 客户等级
  			     'MEMBER_GRADE'];
     initDataDictionary(params);
	//加载界面
	if(parentCombox.searchWindow == null && DataDictionary.MEMBER_GRADE.length!=0){
		var searchMemberWin = Ext.create('BOSearchMemberWin',{'searchMemberData':searchMemberDataControlBO,
															'parent':parentCombox});
		parentCombox.searchWindow = searchMemberWin;
		searchMemberWin.show();
	}
};

/**
* 查询会员共用界面
*/
Ext.define('BOSearchMemberWin',{
	id:'bosearchMemberWinId',
	extend:'PopWindow',
	width:790,
	height:430,
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
//    		memberSearchWin.destroy();
    		return false;
    	}
    },
	items:null,
	initComponent:function(){
		var me = this;
		var record = new BOSearchMemberConditionModel();
		me.searchConditionForm = Ext.create('BOSearchConditionForm',{'parent':me.parent,'record':record,'parentWin':me});
		me.searchResultGrid = Ext.create('BOSearchResultGrid',{'searchMemberData':me.searchMemberData,'searchConditionForm':me.searchConditionForm});
		me.searchButtonPanel = Ext.create('BOSearchButtonPanel',{'searchConditionForm':me.searchConditionForm,'searchMemberData':me.searchMemberData});
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
//				me.destroy();
			}
		}];
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'basicpanel',
			height:100,
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
Ext.define('BOSearchButtonPanel',{
	extend:'NormalButtonPanel',
	id:'SearchMemberView_BOSearchButtonPanel_id',
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
		if(me.searchMemberData.getSearchMemberResultStore().data.length > 0){
			me.searchMemberData.getSearchMemberResultStore().removeAll();
		}
		if(me.beforeSearch(Ext.getCmp('SearchMemberView_BOSearchButtonPanel_id'))){
			me.searchMemberData.getSearchMemberResultStore().loadPage(1);
		}
//		else{
//			if(me.searchMemberData.getSearchMemberResultStore().data.length > 0){
//				me.searchMemberData.getSearchMemberResultStore().removeAll();
//			}
//		}
	},
	//查询前 条件校验
	beforeSearch:function(me){
		if(Ext.isEmpty(me)){
			me = this;
			return false;
		}
		if(!me.validateCondition()){
			MessageUtil.showMessage(i18n('i18n.SearchMember.searchConditionNotNull'));
			return false;
		}
        //客户编码、手机号码、固定电话+联系人姓名为唯一查询条件、其他查询条件必须在存在选择部门下才可以进行查询
        //客户编码 custNumber auth:李春雨
        var custNumber = me.searchConditionForm.getForm().findField('custNumber');
        //手机号码  mobile
        var mobile = me.searchConditionForm.getForm().findField('mobile');
        //固定电话 phone
        var phone = me.searchConditionForm.getForm().findField('phone');
        //联系人姓名 linkmanName
        var linkmanName = me.searchConditionForm.getForm().findField('linkmanName');
        //部门deptId
        var deptId = me.searchConditionForm.getForm().findField('deptId');
        if(Ext.isEmpty(custNumber.getValue())
        &&Ext.isEmpty(mobile.getValue())
        &&(Ext.isEmpty(phone.getValue())||Ext.isEmpty(linkmanName.getValue()))
        &&Ext.isEmpty(deptId.getValue())){
            MessageUtil.showMessage(i18n('i18n.businessOpportView.deptNeedSearch'));
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
Ext.define('BOSearchConditionForm',{
	extend:'SearchFormPanel',
	id:'bosearchConditionFormId',
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
		var all = {code:'',codeDesc:'全部'};
		var intention = getDataDictionaryByName(DataDictionary,'COOPERATION_INTENTION');//合作意向
		intention.add(all);
		var developStage = getDataDictionaryByName(DataDictionary,'DEVELOP_STAGE');//开发阶段
		developStage.add(all);
		var custCategory = getDataDictionaryByName(DataDictionary,'CUST_CATEGORY');//业务类型
		custCategory.add(all);
		custCategory.each(function(record){
			if(record.get('code')=='EXPRESS'){//去除快递
				custCategory.remove(record);
			}
		});
		var custSource = getDataDictionaryByName(DataDictionary,'CUST_SOURCE');//业务类型
		custSource.add(all);
		return [
		{  
			xtype:'bodeptlookup',
			fieldLabel:i18n('i18n.ScatterUpgradeView.belongdeptName'),//所属部门
			name:'deptId'
		},{
			fieldLabel:i18n('i18n.memberView.customerName'),//客户名称
			maxLength:170,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',170),
			name:'custName'
		},{  
			fieldLabel:i18n('i18n.SearchMember.custNum'),//客户编号
			maxLength:40,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',40),
			name:'custNumber'
		},{
			fieldLabel:i18n('i18n.developSchedule.linkManName'),//联系人名称
			maxLength:40,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',40),
			name:'linkmanName'
		},{
			fieldLabel:i18n('i18n.memberView.mobileNumber'),//联系人手机
			regex:/(^1\d{10}$)|(^\d{8}$)/,
		    regexText:i18n('i18n.marketRecord.marketRecordErrorTelephone'),
			name:'mobile'
		},{
			fieldLabel:i18n('i18n.memberView.telephoneNumber'),//联系人电话
			regex:/^\d{3}[\d\*-\/]{4,37}$/,
			regexText:i18n('i18n.marketRecord.marketRecordErrorMobile'),
			name:'phone'
		},{
              xtype:          'combo',
              mode:           'local',
              value:'',
              triggerAction:  'all',
              editable:false,
              forceSelection: true,
              fieldLabel:     i18n('i18n.PotentialCustManagerView.custSource'),//客户来源
              name:'custResource',
              displayField:   'codeDesc',
              valueField:     'code',
              queryMode: 'local',
              store:custSource
          },{
              xtype:          'combo',
              mode:           'local',
              value:'',
              triggerAction:  'all',
              editable:false,
              forceSelection: true,
              fieldLabel:     i18n('i18n.developSchedule.cooperatePurpose'),//合作意向
              name:'purpose',
              displayField:   'codeDesc',
              valueField:     'code',
              queryMode: 'local',
              store:intention
          },{
              xtype:          'combo',
              mode:           'local',
              value:'',
              triggerAction:  'all',
              editable:false,
              forceSelection: true,
              fieldLabel:     i18n('i18n.businessOpportunity.KFstep'),//开发阶段
              name:'developStage',
              displayField:   'codeDesc',
              valueField:     'code',
              queryMode: 'local',
              store:developStage
          },{
        	  xtype:'bocustomertrade',
        	  width:350,
        	  trade_labelWidth:67,
        	  trade_width:200,
        	  height:22,
        	  colspan:2,
        	  trade_name:'firstTrade',
        	  trade_fieldname:i18n('i18n.MemberCustEditView.custIndustry'),//客户行业
        	  second_trade_labelWidth:10,
        	  second_trade_width:140,
        	  second_trade_name:'secondTrade',
        	  second_trade_fieldname:'-'
          },{
              xtype:          'combo',
              mode:           'local',
              value:'',
              triggerAction:  'all',
              editable:false,
              forceSelection: true,
              fieldLabel:     i18n('i18n.businessOpportunity.custCategory'),//业务类型
              name:'custCategory',
              displayField:   'codeDesc',
              valueField:     'code',
              queryMode: 'local',
              store:custCategory
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
Ext.define('BOSearchResultGrid',{
	extend:'PopupGridPanel',
	id:'BOSearchResultGridId',
	searchConditionForm:null,
	searchMemberData:null,//数据Data
//	selModel : Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE'}),
	initComponent:function(){
		var me = this;
		me.store = me.searchMemberData.initSearchMemberResultStore(me.beforeLoadScatterFn);
		me.columns = me.getColumns();
		this.callParent();
	},
	defaults:{
		align:'center'
	},
	autoScroll:true,
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
			width:100,
//			renderer:function(val){
//				if(val===true){
//					return '是';
//				}else{
//					return '否';
//				}
//			}
			xtype:'booleancolumn',
			trueText: i18n('i18n.memberView.yes'),
	        falseText: i18n('i18n.memberView.no')
		},{
			header : i18n('i18n.SearchMember.contactNum'),
			dataIndex:'contactNum',
			width:150
		},{
			header : i18n('i18n.SearchMember.contactName'),
			dataIndex:'contactName',
			width:100
		},{
			header : i18n('i18n.SearchMember.movePhone'),
			dataIndex:'mobileNum',
			width:100
		},{
			header : i18n('i18n.memberView.telephoneNumber'),
			dataIndex:'telNum',
			width:150
		},{
			header : i18n('i18n.ScatterUpgradeView.belongdeptName'),//所属部门
			dataIndex:'deptName',
			flex:1
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
            	Ext.getCmp('bosearchMemberWinId').setMemberValueBack();
            }
        }
    },
	//beforeLoad方法
	beforeLoadScatterFn:function(store, operation, eOpts){
		var searchConditionForm = Ext.getCmp('bosearchConditionFormId');
		if(searchConditionForm!=null ){
			searchConditionForm.getForm().updateRecord(searchConditionForm.record);
			searchConditionForm.record.set('deptId',searchConditionForm.getForm().findField('deptId').getValueId());
			//设置请求参数
			var boCustomerCondition = searchConditionForm.record.data;
			var searchParams = {
						// 会员客户编码
					'searchCustCondition.custNumber':DButil.trimString(boCustomerCondition.custNumber),
						// 客户名称（企业或个人）
					'searchCustCondition.custName':DButil.trimString(boCustomerCondition.custName),
						// 联系人姓名
					'searchCustCondition.linkmanName':DButil.trimString(boCustomerCondition.linkmanName),
						// 手机号码(联系人手机号)
					'searchCustCondition.mobile':DButil.trimString(boCustomerCondition.mobile),
					// 所属部门
					'searchCustCondition.deptId':DButil.trimString(boCustomerCondition.deptId),
					// 合作意向
					'searchCustCondition.purpose':DButil.trimString(boCustomerCondition.purpose),
					// 开发阶段
					'searchCustCondition.developStage':DButil.trimString(boCustomerCondition.developStage),
					// 业务类型
					'searchCustCondition.custCategory':DButil.trimString(boCustomerCondition.custCategory),
					// 客户来源
					'searchCustCondition.custResource':DButil.trimString(boCustomerCondition.custResource),
					// 一级行业
					'searchCustCondition.firstTrade':DButil.trimString(boCustomerCondition.firstTrade),
					// 二级行业
					'searchCustCondition.secondTrade':DButil.trimString(boCustomerCondition.secondTrade),
						//固定电话(联系人固定电话)
					'searchCustCondition.phone':DButil.trimString(boCustomerCondition.phone)
			};
			Ext.apply(operation,{
				params : searchParams
				}
			);	
		}
	}
});


