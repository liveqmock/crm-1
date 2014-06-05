//设置Data层对象
var searchMemberDataControl =  (CONFIG.get("TEST"))? new SearchMemberDataTest():new SearchMemberData();
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
	extend:'QueryCombox',   					//继承common包中封装的
	alias : 'widget.membersearchcombox',
	fieldLabel:i18n('i18n.Integral.menberName'),
	name:null,
	searchWindow:null,   						//弹出的查询窗口
	searchType:null,							//查询类型：OWNDEPT：本部门会员
	store:null,
	displayField:'custName',
	valueField:'custId',
	queryMode:'local',
	queryDelay:2000,
	editable:false,
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
		if(DpUtil.isEmpty(memberSearchCombox.getValue())){
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
			SearchMemberView.showSearchMemberWin(memberSearchCombox,this.searchType);
		}else{
			//把当前下拉框中的值带入打开的查询界面
			var custName = memberSearchCombox.getRawValue();
			if(DpUtil.isEmpty(custName)){
				custName = memberSearchCombox.getValue();
			}
			memberSearchCombox.searchWindow.loadCustName(custName);
			memberSearchCombox.searchWindow.show();
			memberSearchCombox.setValue();
		}
		//清空历史数据
		memberSearchCombox.store.removeAll();
	}
});

/**
 * 获得会员查询界面
 */
SearchMemberView.showSearchMemberWin = function(parentCombox,searchType){
	var params =[// 客户等级
  			     'MEMBER_GRADE'];
     initDataDictionary(params);
	//加载界面
	if(parentCombox.searchWindow == null && DataDictionary.MEMBER_GRADE.length!=0){
		var searchMemberWin = Ext.create('SearchMemberWin',{'searchMemberData':searchMemberDataControl,
															'searchType':searchType,
															'parent':parentCombox});
		parentCombox.searchWindow = searchMemberWin;
		searchMemberWin.show();
		parentCombox.setValue();
	}
};

/**
* 查询会员共用界面
*/
Ext.define('SearchMemberWin',{
	extend:'PopWindow',
	width:790,
	height:350,
	title:i18n('i18n.SearchMemberView.custInfo'),
	parent:null,
	searchConditionForm:null,//查询条件
	searchResultGrid:null,  //查询结果
	searchButtonPanel:null,//查询按钮面板
	selectButtonPanel:null,//确定按钮面板
	searchMemberData:null,//数据Data
	searchType:null,							//查询类型：OWNDEPT：本部门会员
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
		me.searchResultGrid = Ext.create('SearchResultGrid',{'searchType':me.searchType,'searchMemberData':me.searchMemberData,'searchConditionForm':me.searchConditionForm});
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
			text:i18n('i18n.ManualRewardIntegralEditView.b_ensure'),
			scope:me,
			handler:me.setMemberValueBack
		},{
			xtype:'button',
			text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
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
			MessageUtil.showMessage(i18n('i18n.SearchMemberView.pleaseSelectCustInfoOp'))
		}else{
			//调用父类的查询结果回调函数
			me.parent.setValueComeBack(selection[0],me.searchMemberData.getSearchMemberResultStore().getRange());
			me.parent.setValue(selection[0].get('custId'));
			me.hide();
		}
	},
	//加载数据
	loadCustName:function(custName){
		var me = this;
		//清空查询条件
		me.searchConditionForm.getForm().reset();
		me.searchConditionForm.getForm().updateRecord(me.searchConditionForm.record);
		
		if(!DpUtil.isEmpty(custName)){
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
					text:i18n('i18n.PotentialCustManagerView.search'),
					scope:me,
					handler:me.searchEvent
				},{
					xtype:'button',
					text:i18n('i18n.MemberCustEditView.reset'),
					scope:me,
					handler:me.resetEvent
				}]
		}];
	},
	//查询会员
	searchEvent:function(){
		var me = this;
		var serchButton = Ext.getCmp('SearchMemberView_SearchButtonPanel_id');
		if(serchButton.beforeSearch(serchButton)){
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
			MessageUtil.showMessage(i18n('i18n.MemberUpgradeView.message_notAllNull'));
			return false;
		}
		if(!me.searchConditionForm.getForm().isValid()){
			MessageUtil.showMessage(i18n('i18n.MyWorkFlowDealManagerView.m_changeAllRight'));
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
			if(!(DpUtil.isEmpty(field.getValue()))){
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
	defaultType:'dptextfield',
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
			fieldLabel:i18n('i18n.MemberCustEditView.custNo'),
			maxLength:40,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',40),
			name:'custNumber'
		},{
			fieldLabel:i18n('i18n.PotentialCustManagerView.customerName'),
			maxLength:80,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80),
			name:'custName'
		},{
			fieldLabel:i18n('i18n.PotentialCustManagerView.contactName'),
			maxLength:80,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80),
			name:'linkManName'
		},{
			fieldLabel:i18n('i18n.MemberCustEditView.contactNo'),
			maxLength:40,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',40),
			name:'linkManNumber'
		},{
			fieldLabel:i18n('i18n.MemberCustEditView.mobileNo'),
			regex : DpUtil.linkWayLimit('M'),
			regexText:i18n('i18n.MemberCustEditView.pleaseInputCorrectMobilePhone'),
			name:'mobile'
		},{
			fieldLabel:i18n('i18n.SearchMemberView.phoneNumber'),
			regex : DpUtil.linkWayLimit('T'),
			regexText:i18n('i18n.MemberManagerView.pleaseEnterTheCorrectPhoneNumber'),
			name:'telePhone'
		},{
			fieldLabel:i18n('i18n.ScatterCustManagerView.idNumber'),
			hidden:true,
			maxLength:18,
			minLength:15,
			regex : DpUtil.linkWayLimit('I'),
			regexText:i18n('i18n.MemberCustEditView.cardNum15118LastxX'),
			name:'idCard'
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
			    		if(!Ext.isEmpty(field.next()) && 'telePhone' != field.name){
			    			field.next().focus();
			    		}else{
			    			me.parentWin.searchButtonPanel.searchEvent();
			    		}
			    	}
			    },
			    change:function(field,newValue){
			    	if(field.getName()=='mobile'){
						DpUtil.autoChangeMobileLength(field,newValue);
					}
			    }
			}
		};
	}
});
Ext.override(Ext.grid.feature.Grouping,{
	//return matching preppedRecords
	getGroupRows: function(group, records, preppedRecords, fullWidth) {
	    var me = this,
	        children = group.children,
	        rows = group.rows = [],
	        view = me.view;
	    group.viewId = view.id;
	    Ext.Array.each(records, function(record, idx) {
	        if (Ext.Array.indexOf(children, record) != -1) {
	            rows.push(Ext.apply(preppedRecords[idx], {
	                depth: 1
	            }));
	        }
	    });
	    
        if('GOLD' == children[0].get('custGrade')){
        	group.color='#D8D8EB';
        }else if('PLATINUM' == children[0].get('custGrade')){
            group.color='#A6A6D2';
        }else if('DIAMOND' == children[0].get('custGrade')){
            group.color='#7373B9';
        }
        var groupName = group.name.split(',');
        group.name = "<span style='display:inline-block;display:-moz-inline-box; width:200px;'>"+
        				groupName[0] + "</span>"+"<span>"+groupName[1] +groupName[2]+"</span>";
	    delete group.children;
	    group.fullWidth = fullWidth;
	    if (me.collapsedState[view.id + '-gp-' + group.name]) {
	        group.collapsedCls = me.collapsedCls;
	        group.hdCollapsedCls = me.hdCollapsedCls;
	    }
	    return group;
	}
})
/**
 * 查询结果
 */
Ext.define('SearchResultGrid',{
	extend:'PopupGridPanel',
	searchConditionForm:null,
	searchMemberData:null,//数据Data
	searchType:null,
//	selModel : Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE'}),
	initComponent:function(){
		var me = this;
		me.store = me.searchMemberData.initSearchMemberResultStore(me.beforeLoadScatterFn,me.searchType);
		me.columns = me.getColumns();
		this.callParent();
	},
	getColumns:function(){
		var me = this;
		return [{
			header:i18n('i18n.MemberManagerView.customerGrouping'),
			dataIndex:'custGroup'
		},{
			header : i18n('i18n.ChangeContactAffiliatedRelationView.custId'),
			hidden:true,
			dataIndex:'custId'
		},{
			header : i18n('i18n.MemberCustEditView.custNo'),
			dataIndex:'custNum',
			hidden:true
		},{
			header : i18n('i18n.PotentialCustManagerView.customerName'),
			dataIndex:'custName',
			hidden:true 
		},{
			header : i18n('i18n.ManualRewardIntegralEditView.id'),
			dataIndex:'contactId',
			hidden:true 
		},{
			header : i18n('i18n.MemberCustEditView.isMainContact'),
			dataIndex:'isMainLinkMan',
			xtype:'booleancolumn',
			trueText: i18n('i18n.ChangeContactAffiliatedRelationView.yes'),
	        falseText: i18n('i18n.ChangeContactAffiliatedRelationView.no'),
			flex:1
		},{
			header : i18n('i18n.MemberCustEditView.contactNo'),
			dataIndex:'contactNum',
			width:150
		},{
			header : i18n('i18n.ManualRewardIntegralEditView.name'),
			dataIndex:'contactName',
			flex:1
		},{
			header : i18n('i18n.SearchMemberView.mobileTelephone'),
			dataIndex:'mobileNum',
			flex:1
		},{
			header : i18n('i18n.MemberCustEditView.telephoneNo'),
			dataIndex:'telNum',
			flex:1
		},{
			header : i18n('i18n.PotentialCustEditView.address'),
			dataIndex:'address',
			flex:1
		},{
			header : i18n('i18n.ScatterCustManagerView.remark'),
			dataIndex:'remark',
			flex:1
		}];
	},
	features: [{
       ftype: 'groupingsummary',
       groupHeaderTpl: '<div style="background-color:{color}">{name} ({rows.length})</div>',
       hideGroupedHeader: true,
       enableGroupingMenu: false
    }],
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
					'searchCustCondition.idCard':DpUtil.trimString(searchCustCondition.idCard)
			};
			Ext.apply(operation,{
				params : searchParams
				}
			);	
		}
	}
});


