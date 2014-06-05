// 设置Data层对象
var searchMemberData =  (ORDERCONFIG.get("TEST"))? new OrderDataTest():new SearchMemberData();
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
	fieldLabel:i18n('i18n.SearchMember.memberName'),//@TOEnglish:memberName
	name:null,
	searchWindow:null,   //弹出的查询窗口
	store:null,
	displayField:'custName',
	valueField:'custName',
	queryMode:'local',
	//forceSelection: true,
	enableKeyEvents:true,
	initComponent:function(){
		var me = this;
		me.addListener('change',me.changeFn);
		me.addListener('expand',me.expandFn);
		me.addListener('keypress',me.keypressFn);
		this.callParent();
	},
	//会员查询combobox的change事件
	changeFn:function(memberSearchCombox,newValue,oldValue){
		if(!Ext.isEmpty(oldValue)&&!Ext.isEmpty(memberSearchCombox.memberRecord)){
			memberSearchCombox.setValueNull(memberSearchCombox);
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
	setValueComeBack:function(memberRecord,addressRecord){
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
		Ext.data.StoreManager.lookup(memberSearchCombox.id+'Store').removeAll();
	}
});

/**
 * 获得会员查询界面
 */
SearchMemberView.showSearchMemberWin = function(parentCombox){
	var win  = Ext.getCmp(parentCombox.id+'Win');
	if(Ext.isEmpty(win)){
		var searchMemberWin = Ext.create('SearchMemberWin',{
			'searchMemberData':searchMemberData,
			'parent':parentCombox,
			'id':parentCombox.id+'Win'
		});
		parentCombox.searchWindow = searchMemberWin;
		searchMemberWin.show();
	}else{
		win.show();
	}
};

/**
* 查询会员共用界面
*/
Ext.define('SearchMemberWin',{
	extend:'PopWindow',
	width:815,
	height:550,
	title:i18n('i18n.SearchMember.memberInfo'),//@TOEnglish:memberInfo
	parent:null,
	closeAction:'hide',
	searchConditionForm:null,//查询条件
	searchResultGrid:null,  //查询结果
	addressGrid:null,//联系人地址表
	searchButtonPanel:null,//查询按钮面板
	selectButtonPanel:null,//确定按钮面板
	searchMemberData:null,//数据Data
	layout:{
        type:'vbox',
        align:'stretch'
    },
    listeners:{
    	beforeclose:function(memberSearchWin){
    		Ext.data.StoreManager.lookup('addressGirdStore'+memberSearchWin.parent.id).removeAll();
    		Ext.data.StoreManager.lookup(memberSearchWin.parent.id+'Store').removeAll();
    		memberSearchWin.hide();
    		return false;
    	},
	    beforehide:function(memberSearchWin){
			Ext.data.StoreManager.lookup('addressGirdStore'+memberSearchWin.parent.id).removeAll();
			Ext.data.StoreManager.lookup(memberSearchWin.parent.id+'Store').removeAll();
		}
    },
	items:null,
	initComponent:function(){
		var me = this;
		var record = new SearchMemberConditionModel();
		me.searchConditionForm = Ext.create('SearchConditionForm',{'parent':me.parent,'record':record,'parentWin':me});
		me.searchResultGrid = Ext.create('SearchResultGrid',{'parent':me.parent,'searchMemberData':me.searchMemberData,'id':me.parent.id+'SearchResultGrid',
			'searchConditionForm':me.searchConditionForm,'selModel' : Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE'}),
			'features': [{
			       ftype: 'groupingsummary',
			       groupHeaderTpl: '{name} ({rows.length})',
			       hideGroupedHeader: true,
			       enableGroupingMenu: false
			    }]});
		me.searchButtonPanel = Ext.create('SearchButtonPanel',{'searchConditionForm':me.searchConditionForm,'parent':me.parent,
			'searchMemberData':me.searchMemberData});
		me.addressGrid = Ext.create('AddressGird',{'parent':me.parent,'selModel' : Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE'})});
		me.items = me.getItems();
		me.fbar = me.getFbar();
		//设置会员查询combobox的store
		me.parent.store = me.searchMemberData.getSearchMemberResultStore();
		this.callParent();
		//父控件的名称带入查询会员界面
		record.set('custName',me.parent.getValue());
		me.searchConditionForm.loadRecord(record);
//		me.parent.setValue('');
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
			text:i18n('i18n.order.cancel'),
			handler:function(){
				me.addressGrid.doLayout();
				Ext.data.StoreManager.lookup('addressGirdStore'+me.parent.id).removeAll();
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
		},
		{
			xtype:'basicpanel',
			autoScroll:false,
			flex:1,
			items:[me.addressGrid]
		}
		];
	},
	//把选中的会员信息返回
	setMemberValueBack:function(){
		var me = this;
		var selection = me.searchResultGrid.getSelectionModel().getSelection();
		var selectionAddress = me.addressGrid.getSelectionModel().getSelection();
		if(selection.length != 1){
			MessageUtil.showMessage(i18n('i18n.SearchMember.pleaseSelectMemberToOprator'));//@TOEnglish:pleaseSelectMemberToOprator
		}else{
			//调用父类的查询结果回调函数
			me.parent.setValueComeBack(selection[0],selectionAddress[0]);
			me.parent.setValue(selection[0].get('custName'));
			me.parent.memberRecord = selection[0];
			me.parent.addressRecord = selectionAddress[0];
			me.hide();
		}
	},
	//加载数据
	loadCustName:function(custName){
		var me = this;
		//清空查询条件
		me.parent.setValue('');
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
					text:i18n('i18n.order.search'),
					scope:me,
					handler:me.searchEvent
				},{
					xtype:'button',
					text:i18n('i18n.order.reset'),
					scope:me,
					handler:me.resetEvent
				}]
		}];
	},
	//查询会员
	searchEvent:function(){
		var me = this;
		Ext.data.StoreManager.lookup('addressGirdStore'+me.parent.id).removeAll();
		if(!me.validateCondition()){
			MessageUtil.showMessage(i18n('i18n.SearchMember.searchConditionNotNull'));//@TOEnglish:searchConditionNotNull
			return;
		}
		if(!me.searchConditionForm.getForm().isValid()){
			MessageUtil.showMessage(i18n('i18n.SearchMember.pleaseCheckConditionIsRight'));//@TOEnglish:pleaseCheckConditionIsRight
		return;
		}
		Ext.data.StoreManager.lookup(me.parent.id+'Store').removeAll();
		Ext.data.StoreManager.lookup(me.parent.id+'Store').loadPage(1);
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
		var conditionArray = me.searchConditionForm.getForm().getFields();//.each(function(field){
		for ( var i = 0; i < conditionArray.items.length-2; i++) {
			if(!Ext.isEmpty(conditionArray.items[i].getValue())){
				flag = true;
			}
		}
		return flag;
	}
});
/**
 * 查询条件
 */
Ext.define('SearchConditionForm',{
	extend:'SearchFormPanel',
	parentWin:null,
	layout:{
		type:'table',
		columns:5
	},
	defaultType:'textfield',
	initComponent:function(){
		var me = this;
		me.defaults = me.getDefaultsContainer();
		me.items = me.getItems();
		me.id = me.parent.id+'searchConditionFormId';
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [
		{
			fieldLabel:i18n('i18n.SearchMember.custNum'),//@TOEnglish:custNum
			name:'custNumber'
		},{
			fieldLabel:i18n('i18n.order.custName'),//@TOEnglish:custName
			name:'custName'
		},{
			fieldLabel:i18n('i18n.order.connectMan'),//@TOEnglish:shippingContact
			name:'linkManName'
		},{
			fieldLabel:i18n('i18n.SearchMember.contactNum'),//@TOEnglish:contactNum
			name:'linkManNumber',
			colspan:2
		},{
			fieldLabel:i18n('i18n.order.mobilePhoneNum'),
			regex : /^1\d{10}$|^\d{8}$/,/*hbf*/
			regexText:i18n('i18n.order.correctMobilePhone'),
			name:'mobile'
		},{
			fieldLabel:i18n('i18n.order.phoneNum'),//@TOEnglish:phone
			regex : /^\d{3}[\d\*-\/]{4,17}$/,
			regexText:i18n('i18n.order.phoneNumRegex'),
			name:'telePhone'
		},{
			fieldLabel:i18n('i18n.SearchMember.IDCards'),//@TOEnglish:IDCards
			regex:/^([\d]{15}|[\d]{18}|[\d]{17}X)$/,
			regexText:i18n('i18n.order.IDCardsRegex'),
			name:'idCard'
		},{
			xtype:'checkbox',
			checked:true,
			name:'deptIds'
		},{
			xtype:'displayfield',
			fieldLabel:i18n('i18n.order.isOrNotMySelfCustomer'),
			labelSeparator:'',
			labelWidth:90,
			margin:'0 0 8 -170'
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
	searchConditionForm:null,
	searchMemberData:null,//数据Data
	initComponent:function(){
		var me = this;
		me.store = me.searchMemberData.initSearchMemberResultStore(null,me.parent.id+'Store');
		me.columns = me.getColumns();
		me.store.on('beforeload',function(store, operation, eOpts){
			var searchConditionForm = Ext.getCmp(me.parent.id+'searchConditionFormId');
			if(searchConditionForm!=null){
				searchConditionForm.getForm().updateRecord(searchConditionForm.record);
				//设置请求参数
				var searchCustCondition = searchConditionForm.record.data;
				var deptIds=new Array();
				if(searchConditionForm.getForm().findField('deptIds').getValue()==true){
					deptIds.push(User.deptId);
				}
				var searchParams = {
							// 会员客户编码
						'searchCustCondition.custNumber':(searchCustCondition.custNumber).trim(),
							// 客户名称（企业或个人）
						'searchCustCondition.custName':(searchCustCondition.custName).trim(),
							// 联系人编码
						'searchCustCondition.linkManNumber':(searchCustCondition.linkManNumber).trim(),
							// 联系人姓名
						'searchCustCondition.linkManName':(searchCustCondition.linkManName).trim(),
							// 手机号码(联系人手机号)
						'searchCustCondition.mobile':(searchCustCondition.mobile).trim(),
							//固定电话(联系人固定电话)
						'searchCustCondition.telePhone':(searchCustCondition.telePhone).trim(),
							//身份证号码
						'searchCustCondition.idCard':(searchCustCondition.idCard).trim(),
							//
						'searchCustCondition.deptIds':deptIds,
						//客户类型(固定客户)
						'searchCustCondition.custGroup':'RC_CUSTOMER'
						
				};
				Ext.apply(operation,{
					params : searchParams
					}
				);	
			}
		});
		this.callParent();
	},
	listeners:{
		select:function(th,record){
			var me = this;
			var contactId = record.get('contactId');
			var param = {'contactId':contactId,'addressType':Ext.getCmp(me.parent.id).addressType};
			var successFn = function(json){
				Ext.data.StoreManager.lookup('addressGirdStore'+me.parent.id).removeAll();
				if(!Ext.isEmpty(json.preferenceAddressList)){
					Ext.data.StoreManager.lookup('addressGirdStore'+me.parent.id).loadData(json.preferenceAddressList);
				}
			};
			var failureFn = function(json){
				if(Ext.isEmpty(json)){
					MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
				}else{
					MessageUtil.showErrorMes(json.message);
				}
			};
			searchMemberData.searchAddressInfoList(param,successFn,failureFn);
		},
    	scrollershow: function(scroller) {
    		if (scroller && scroller.scrollEl) {
    				scroller.clearManagedListeners(); 
    				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
    		}
    	}

	},
	getColumns:function(){
		var me = this;
		return [{
			header:i18n('i18n.SearchMember.custGroup'),//@TOEnglish:custGroup
			dataIndex:'custGroup'
		},{
			header : i18n('i18n.SearchMember.custId'),//@TOEnglish:custId
			hidden:true,
			dataIndex:'custId'
		},{
			header : i18n('i18n.SearchMember.custNum'),//@TOEnglish:custNum
			dataIndex:'custNum',
			hidden:true
		},{
			header : i18n('i18n.order.custName'),//@TOEnglish:custName
			dataIndex:'custName',
			hidden:true 
		},{
			header : i18n('i18n.SearchMember.contactId'),//@TOEnglish:contactId
			dataIndex:'contactId',
			hidden:true 
		},{
			header : i18n('i18n.SearchMember.isMainLinkMan'),//@TOEnglish:isMainLinkMan
			dataIndex:'isMainLinkMan',
			xtype:'booleancolumn',
			trueText: i18n('i18n.order.yes'),
	        falseText: i18n('i18n.order.no')
		},{
			header : i18n('i18n.SearchMember.contactNum'),//@TOEnglish:contactNum
			dataIndex:'contactNum'
		},{
			header : i18n('i18n.SearchMember.contactName'),//@TOEnglish:contactName
			dataIndex:'contactName'
		},{
			header : i18n('i18n.SearchMember.movePhone'),//@TOEnglish:movePhone
			dataIndex:'mobileNum'
		},{
			header : i18n('i18n.order.phoneNum'),
			dataIndex:'telNum'
		}];
	}
});
/**.
* <p>
* 地址Grid</br>
* </p>
* @retuens {RecompenseListGird}
* @author  张斌
* @时间    2012-04-11
*/
Ext.define('AddressGird',{
	extend:'PopupGridPanel',
	 //选择框
	//selModel : Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE'}),
	//id:'addressGird',
	style:'margin-top:10px',
	//height:190,
	//width:825,
	//autoScroll:false,
	listeners: {
    	scrollershow: function(scroller) {
    		if (scroller && scroller.scrollEl) {
    				scroller.clearManagedListeners(); 
    				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
    		}
    	}
    },
    sortableColumns:false,
    enableColumnHide:false,
    enableColumnMove:false,
    getColumns:function(){
    	var me = this;
    	return [
                { header: i18n("i18n.order.addressType"),dataIndex:'addressType',flex:2,
                	renderer:function(value){
                		return DButil.changeDictionaryCodeToDescrip(value,DataDictionary.ADDRESS_TYPE);
                	}},
                { header: i18n("i18n.order.iaMainAddress"),dataIndex:'isMainAddress',flex:2,
                		renderer:function(value){
                			return DButil.changeTrueAndFalse(value);
                    	}},
    		    { header: i18n("i18n.order.detailedAddress"),dataIndex:'address',flex:6}
    		];
    },
	initComponent:function()
	{
		var me = this;
		me.columns = me.getColumns();
		me.store = DButil.getStore('addressGirdStore'+me.parent.id,null,['addressType','address','isMainAddress'],[]);
		this.callParent();
	}
});

