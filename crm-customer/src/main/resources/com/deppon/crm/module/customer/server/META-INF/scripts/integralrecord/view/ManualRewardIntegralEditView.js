//手动奖励积分管理
var manualRewardIntegralEditDataControl= (CONFIG.get("TEST"))? new ManualRewardIntegralManagerDataTest():new ManualRewardIntegralManagerData();
//Ext.onReady(function(){
//	new RewardIntegralNameWin().show();
//});
Ext.define('ManualRewardIntegralEditWin',{
	extend:'PopWindow',
	title:i18n('i18n.ManualRewardIntegralEditView.manualRewardIntegral'),
	manualRewardIntegralEditPanel:null,
	manualRewardIntegralEditData:manualRewardIntegralEditDataControl,
	width:790,
	height:450,
	layout:'fit',
	initComponent:function(){
		var me = this;
		me.manualRewardIntegralEditPanel = Ext.create('ManualRewardIntegralEditPanel',{'manualRewardIntegralEditData':me.manualRewardIntegralEditData});
		me.items = me.getItems();
		me.fbar = me.getFbar();
		this.on('beforeclose',this.beforeCloseEvent);
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [me.manualRewardIntegralEditPanel];
	},
	getFbar:function(){
		var me = this;
		return [ {
			text:i18n('i18n.ManualRewardIntegralEditView.addIntegral'),
			xtype:'button',
				scope:me,
				handler:me.addIntegral
		},{
			text:i18n('i18n.ManualRewardIntegralEditView.button_cancel'),
			xtype:'button',
				scope:me,
				handler:me.cancelEvent
		}];
	},
	//添加积分
	addIntegral:function(button){
		var me = this;
		var form = me.manualRewardIntegralEditPanel.manualRewardIntegralEditRulePanel.getForm();
		var integralBasicNumber = form.findField('integralBasicNumber').getValue();
		var pointvalue = form.findField('pointvalue').getValue();
		//奖励积分基数 为空则不执行添加积分
		if(Ext.isEmpty(integralBasicNumber)){
			MessageUtil.showMessage(i18n('i18n.ManualRewardIntegralEditView.m_selectRewardName'));
			return;
		}
		//验证奖励积分基数输入是否合格
		if(!form.findField('integralBasicNumber').isValid( )){
			MessageUtil.showMessage(i18n('i18n.ManualRewardIntegralEditView.m_onlyNum'));
			return;
		}
		//待添加积分 联系人 为空 为空则不执行添加积分
		if(Ext.getCmp('ChooseCustomerGridId').store.getCount() <= 0){
			MessageUtil.showMessage(i18n('i18n.ManualRewardIntegralEditView.m_selectObj'));
			return;
		}
		button.setDisabled(true);
		var params = {};
		params.integralBasicNumber = me.change2Num(integralBasicNumber);
//		params.pointvalue = me.change2Num(pointvalue);
		params.rewardIntegral = RewardIntegRule;
		params.contactIds = me.getContactIds(Ext.getCmp('ChooseCustomerGridId'));
		var fnFail = function(result){
			button.setDisabled(false);
			MessageUtil.showErrorMes(i18n('i18n.ManualRewardIntegralEditView.m_add_fail')+result.message);
		}
		var fnSuccess = function(result){
			MessageUtil.showInfoMes(i18n('i18n.ManualRewardIntegralEditView.m_add_success'));
			//添加积分成功后，清空已选客户的 grid
			Ext.getCmp('ChooseCustomerGridId').store.removeAll();
			if(!Ext.isEmpty(Ext.getCmp('ManualRewardIntegralManagerResultGrid_id'))){
				Ext.getCmp('ManualRewardIntegralManagerResultGrid_id').store.loadPage(1);
			}
//			me.manualRewardIntegralEditData.getHandRewardIntegralStore().loadPage(1);
			me.close();
			button.setDisabled(false);
		}
		Ext.MessageBox.confirm(i18n('i18n.PotentialCustManagerView.messageTip'), i18n('i18n.ManualRewardIntegralEditView.m_is_add'), function(e) {
			if (e == 'yes') {
				//添加奖励 请求
				me.manualRewardIntegralEditData.createHandRewardIntegrals(params,fnSuccess,fnFail);
			}
		});
	},
	//放弃
	cancelEvent:function(){
		var me = this;
		me.close();
	},
	//转换 字符串成 num 避免后台接收 不报错
	change2Num:function(obj){
		var me = this;
		if(Ext.isEmpty(obj)){
			return null;
		}
		return obj;
	},
	//得到待添加积分的 联系人 id
	getContactIds:function(obj){
		var me = this;
		var contactIds = [];
		if(!Ext.isEmpty(obj)){
			var store = obj.store;
			store.each(function(record){
				contactIds.push(record.get("id"));
			});
		}
		return contactIds;
	},
	//关闭前事件
	beforeCloseEvent:function(){
		//清空store数据
		Ext.getCmp('searchResultGridId').store.removeAll();
		Ext.getCmp('ChooseCustomerGridId').store.removeAll();
	}
});
var RewardIntegRule = {};//积分奖励规则 全局变量

/**
 * 手动奖励积分管理panel
 */
Ext.define('ManualRewardIntegralEditPanel',{
	extend:'BasicPanel',
	manualRewardIntegralEditRulePanel:null,  //积分奖励规则
	manualRewardIntegralObjectPanel:null,//奖励对象
	manualRewardIntegralEditData:null,//数据Data
	width:350,
	height:400,
	layout:{
        type:'hbox',
        align:'stretch'
    },
	initComponent:function(){
		var me = this;
		me.manualRewardIntegralEditRulePanel = Ext.create('ManualRewardIntegralEditRulePanel',{'manualRewardIntegralEditData':me.manualRewardIntegralEditData});
		me.manualRewardIntegralObjectPanel = Ext.create('ManualRewardIntegralEditObjectPanel',{'manualRewardIntegralEditData':me.manualRewardIntegralEditData});
		me.items = me.getItems();
//		me.fbar = me.getFbar();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [
			{
			/*xtype:'middlebuttonpanel'*/
			xtype:'fieldset',
			title : i18n('i18n.ManualRewardIntegralEditView.t_rewardRule'),
			items:[me.manualRewardIntegralEditRulePanel]
		},{
			xtype:'basicpanel',
			width:10
		},{
//			xtype:'poptitleformpanel',
			xtype : 'fieldset',
			title : i18n('i18n.ManualRewardIntegralEditView.t_rewardObj'),
			layout:'fit',
			items:[me.manualRewardIntegralObjectPanel]		
		}
		];
	},
	getFbar:function(){
		var me = this;
		return [ {
			xtype : 'button',
			id:'contractSaveButton',
			text : i18n('i18n.PotentialCustEditView.save'),
			scope:me,
			handler:me.saveContract
		}];
	}
});

/**
 * 积分奖励规则
 */
Ext.define('ManualRewardIntegralEditRulePanel',{
	extend:'NoTitleFormPanel',
	manualRewardIntegralEditData:null,//数据
	width:210,
	height:400,
	layout: {
        type: 'vbox',
        align: 'stretch'
    },
	defaults:{
		readOnly:true,
		labelWidth:85
	},
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
		me.getForm().reset();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'dpcombo',
			fieldLabel:i18n('i18n.ManualRewardIntegralEditView.rulename'),
			readOnly:false,
			editable:false,
			name:'rulename',
			listeners:{
				scope:me,
				expand:me.expandFn
			}
		},{
			xtype:'dpcombo',
			fieldLabel:i18n('i18n.ManualRewardIntegralEditView.integType'),
			name:'integType',
			store:me.manualRewardIntegralEditData.getIntegTypeStore(),
			queryMode:'local',
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code'
		},{
			xtype:'fieldcontainer',
			layout:'column',
			defaults:{
				width:200,
				readOnly:true
			},
			items:[{xtype:'displayfield',value:i18n('i18n.ManualRewardIntegralEditView.integralBasicNumber'),width:90},
			       {
					xtype:'dpnumberfield',
					width:52,
					hideTrigger: true,
			        mouseWheelEnabled: false,
					name:'integralBasicNumber',
					listeners:{
						scope:me,
						'blur':me.calculateEvent,
						'keypress':function(obj,e){
							if(e.getKey() == Ext.EventObject.ENTER){
								me.calculateEvent(obj);
							}
						}
					}
				   },{xtype:'displayfield',value:'X',width:15},{
						xtype:'textfield',
						width:52,
						name:'fraction'
				   }]
		},{
			xtype:'dpnumberfield',
			fieldLabel:i18n('i18n.ManualRewardIntegralEditView.pointvalue'),
			hideTrigger: true,
	        mouseWheelEnabled: false,
			name:'pointvalue'
		}];
	},
	//奖励规则查询combobox的expand事件
	expandFn:function(combox){
		var me = this;
		var rewardIntegralNameWin = Ext.create('RewardIntegralNameWin',{'parent':me});
		rewardIntegralNameWin.manualRewardIntegralEditPanel.store.load();
		rewardIntegralNameWin.show()
	},
	//自动计算积分 时间
	calculateEvent:function(obj){
		var me = this;
		if(!Ext.isEmpty(obj.getValue())){
			me.getForm().findField('pointvalue').setValue(obj.getValue()*me.getForm().findField('fraction').getValue());
		}
	}
});


/**
 * <p>
 * 制定计划formPnale
 * </p>
 * @author  张登
 * @date    2012-03-13
 */
Ext.define('SearchConditionForm',{
	extend:'SearchFormPanel',  
	items:null,
	initComponent:function(){
		this.items = this.getItems(); 
		this.callParent();
		this.loadRecord(Ext.create('ContactCondition'));
	},
	width:450,
	getItems:function(){
		var me = this;
		return [{
			layout:{
				type:'table',
				columns:2
			},
			defaultType:'textfield',
			defaults:{
				labelWidth : 65,
		        labelAlign: 'right',
				width : 200
			},
			items:[{
				fieldLabel:i18n('i18n.Integral.contactNumber'),
				name:'contactNum',
				maxLength : 20,
				maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20)
			},{
				fieldLabel:i18n('i18n.Integral.contatct'),
				name:'contactName',
				maxLength : 20,
				maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20)
			},{
				fieldLabel:i18n('i18n.MemberCustEditView.custNo'),
				name:'custNum',
				maxLength : 20,
				maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20)
			},{
				fieldLabel:i18n('i18n.PotentialCustManagerView.customerName'),
				name:'custName',
				maxLength : 20,
				maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20)
			}]		
		}];
	}
});

/**
 * 查询按钮
 */
Ext.define('RightSearchButtonPanel',{
	extend:'PopButtonPanel', 
	parent:null,
	manualRewardIntegralEditData:null,
	region:'south',
	weight:400,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'middlebuttonpanel' 
		},{
			xtype:'poprightbuttonpanel',  
			items : [{
				xtype : 'button',
				text : i18n('i18n.PotentialCustManagerView.search'),
				width : 70,
				handler : function(){
					if(!me.validateCondition()){
						MessageUtil.showMessage(i18n('i18n.MemberUpgradeView.message_notAllNull'));
						return;
					}
					var form = me.parent.searchCondition;
					if(!form.getForm().isValid()){
						MessageUtil.showMessage(i18n('i18n.MemberUpgradeView.message_checkCondition'));
						return;
					}
					//清空store数据
//					me.manualRewardIntegralEditData.getContactListStore().removeAll();
//					Ext.getCmp('ChooseCustomerGridId').store.removeAll();
					//按条件查询
					var store = me.manualRewardIntegralEditData.getContactListStore();
					store.load();
				}
			}]
		}];
	},
	//校验是否查询条件都为空
	validateCondition:function(){
		var me = this;
		var form = me.parent.searchCondition;
		var flag = false;
		form.getForm().getFields().each(function(field){
			if(!(DpUtil.isEmpty(field.getValue()))){
				flag = true;
			}
		});
		return flag;
	}
	
});

/**
 * 移动按钮
 */
Ext.define('TableMoveBtn',{
	extend:'BasicVboxCenterPanel',
	items:null,
	width:100,
	height:200,
	defaultType:'button', 
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		return [{
 	        text: '>>',
			width:62,
			handler : function(){
				//得到所选客户
				var store=Ext.getCmp("searchResultGridId").store;
				var chooseStore=Ext.getCmp("ChooseCustomerGridId").store;
				store.each(function(record){
					if(chooseStore.find("id",record.get("id"))!=-1){//判断是否有重复
//					    alert(record.get("name")+"重复");
						if(store.getCount( ) > 0){
				    		MessageUtil.showMessage(i18n('i18n.ManualRewardIntegralEditView.m_contactIsExsit'));
						}
					    return false;
				    }else{
				    	store.remove(record);
				    	//添加到已选择客户store里
				    	chooseStore.add(record);
				    }
				});
				//设置可选 数据条数
				var selectValue = '<span style="color:red;">'+chooseStore.getCount()+'</span>';
				Ext.getCmp('currentSelectedMRIEV').setValue(selectValue);
			}
		},{
 	        text: '> ',          //--注意:按钮的宽度需设置	
			width:62,
			handler : function(){
				//得到所选客户
				var selection=Ext.getCmp("searchResultGridId").getSelectionModel().getSelection();
				//已选择客户store
				var chooseStore=Ext.getCmp("ChooseCustomerGridId").store;
				for(var i=0;i<selection.length;i++){//遍历所选客户
			    	if(chooseStore.find("id",selection[i].get("id"))!=-1){//判断是否有重复
//					    alert(selection[i].get("name")+"重复");
			    		MessageUtil.showMessage(i18n('i18n.ManualRewardIntegralEditView.m_isAdded'));
					    return false;
				    }else{
				    	Ext.getCmp("searchResultGridId").store.remove(selection[i]);
				    	//添加到已选择客户store里
				    	chooseStore.insert(i,selection[i]);
				    }
				}
				//设置可选 数据条数
				var selectValue = '<span style="color:red;">'+chooseStore.getCount()+'</span>';
				Ext.getCmp('currentSelectedMRIEV').setValue(selectValue);
			}
		},{
 	        text: '<',
			width:62,
			handler : function(){
				//得到已选客户
				var selection=Ext.getCmp("ChooseCustomerGridId").getSelectionModel().getSelection();
				//待选择客户store
				var store=Ext.getCmp("searchResultGridId").store;
				for(var j=0;j<selection.length;j++){//遍历所选客户
					Ext.getCmp("ChooseCustomerGridId").store.remove(selection[j]);
					//添加到已选择客户store里
					store.insert(j,selection[j]);
				}
				//设置可选 数据条数
				var selectValue = '<span style="color:red;">'+Ext.getCmp("ChooseCustomerGridId").store.getCount()+'</span>';
				Ext.getCmp('currentSelectedMRIEV').setValue(selectValue);
			}
		},{
 	        text: '<<',
			width:62,
			handler : function(){
				var store=Ext.getCmp("ChooseCustomerGridId").store;
				var searchResultGrid=Ext.getCmp("searchResultGridId").store;
				store.each(function(record){
				    store.remove(record);
				    //添加到已选择客户store里
				    searchResultGrid.add(record);
				});
				//设置可选 数据条数
				var selectValue = '<span style="color:red;">'+store.getCount()+'</span>';
				Ext.getCmp('currentSelectedMRIEV').setValue(selectValue);
			}
		},{
			xtype:'fieldcontainer',
			layout:'column',
			defaultType:'displayfield',
			items:[{value:i18n('i18n.ManualRewardIntegralEditView.hadSelect')},
			       {id:'currentSelectedMRIEV',value:'<span style="color:red;">0</span>'},
			       {value:i18n('i18n.ManualRewardIntegralEditView.personPeriod')}]
		},{
			xtype:'fieldcontainer',
			layout:'column',
			hidden:true,
			defaultType:'displayfield',
			items:[{value:i18n('i18n.ManualRewardIntegralEditView.canSelect')},
			       {id:'canSelectMRIEV',value:'<span style="color:red;">0</span>'},
			       {value:i18n('i18n.ManualRewardIntegralEditView.personComma')}]
		}]	
	}
});

/**.
 * <p>
 * 奖励对象Panel
 * <p>
 * @author 张登
 * @时间 2012-4-28
 */
Ext.define('ManualRewardIntegralEditObjectPanel',{
	extend:'BasicPanel',
	searchCondition:null, //查询条件From
	searchButtonPanel:null, ////border布局下面查询按钮
	searchLeftResult:null, //查询客户列表（左边Grid）
	searchRightResult:null, //已选择客户列表（右边Grid）
	tableMoveBtn:null,//操作grid数据按钮
	manualRewardIntegralEditData:null,
	items:null,
	width:490,
	layout:'border',
	initComponent:function(){
		var me = this;
		me.searchCondition = Ext.create('SearchConditionForm',{id:'SearchConditionFormId'});//查询条件From
		me.searchButtonPanel = Ext.create('RightSearchButtonPanel',{'parent':me,'manualRewardIntegralEditData':me.manualRewardIntegralEditData});
		me.searchLeftResult =  me.getSearchLeftResult();//左边Grid
		me.tableMoveBtn = Ext.create('TableMoveBtn',{'parent':me});
		me.searchRightResult= me.getSearchRightResult();//右边Grid
		me.items = me.getItems();
		this.callParent();
		//监听，可选联系人store的load事件，填写当前可选数据 条数
		var store = me.manualRewardIntegralEditData.getContactListStore();
		store.on('load',function(obj){
			//设置可选 数据条数
			var selectValue = '<span style="color:red;">'+obj.getCount()+'</span>';
			Ext.getCmp('canSelectMRIEV').setValue(selectValue);
		});
	},
	getItems:function(){//整体布局
		var me = this;
		return [{
			region:'north',
			xtype:'basicpanel',
			height:110,
			layout:'border',
			items:[{
				region:'center',
				xtype:'basicpanel',
				layout:'fit',
				items:[me.searchCondition]
			},me.searchButtonPanel]
		},{
			region:'center',
			xtype:'basicpanel',
			layout:'border',
			items:[{
				region:'center',
				xtype:'basicpanel',
				layout:'column',
                autoScroll:true,
                defaults: {
                    layout: 'anchor',
                    defaults: {
                        anchor: '100%'
                    }
                },
                items: [{
                    columnWidth: 3.7/9,
                    xtype:'basicpanel',
                    layout:'fit',
                    items:[me.searchLeftResult]
                },{
                    columnWidth: 1.6/9,
                    xtype:'basicpanel',
                    layout:'fit',
                    items:[me.tableMoveBtn]
                },{
                    columnWidth: 3.7/9,
                    xtype:'basicpanel',
                    layout:'fit',
                    items:[me.searchRightResult]
                }]
			}]
		}];
	},
	//表格移动左边grid
	getSearchLeftResult:function(){
		var me = this;
		var searchLeftResult =  Ext.create('PopupGridPanel',{
			id:'searchResultGridId',
			store:me.manualRewardIntegralEditData.initContactListStore(me.beforeLoadFn),
			width:200,
			height:220,
			columns:me.getColumns()
		});
		return searchLeftResult;
	},
	//表格移动右边grid
	getSearchRightResult:function(){
		var me = this;
		var searchRightResult=Ext.create('PopupGridPanel',{
			id:'ChooseCustomerGridId',
			store:me.manualRewardIntegralEditData.getRightContactListStore(),
			width:200,
			height:220,
			columns:me.getColumns()
		});
		return searchRightResult;
	},
	getColumns:function(){//查询结果列
		return [{
			header : i18n('i18n.ManualRewardIntegralEditView.id'),
			dataIndex : 'id',
			hidden:true
		},{
			header : i18n('i18n.Integral.contactNumber'),
			flex:0.4,
			dataIndex : 'number'
		}, {
			header : i18n('i18n.ManualRewardIntegralEditView.name'),
			flex:0.6,
			dataIndex : 'name'
		}]
	},
	// beforeLoad方法
	beforeLoadFn:function(store, operation, eOpts){
		var searchConditionForm = Ext.getCmp('SearchConditionFormId');
		if(searchConditionForm!=null){
			var form = searchConditionForm.getForm();
			var searchParams = {
					// 联系人编码
					'searchCondition.contactNum':form.findField('contactNum').getValue(),
					// 联系人名称
					'searchCondition.contactName':form.findField('contactName').getValue(),
					// 客户编码
					'searchCondition.memberNum':form.findField('custNum').getValue(),
					// 客户名称
					'searchCondition.memberName':form.findField('custName').getValue()};
			Ext.apply(operation,{
				params : searchParams
				}
			);	
		}
	}
});

////选择奖励名称 下拉框	
//Ext.define('RewardIntegralNameCombox',{
//	extend:'QueryCombox',   //继承common包中封装的
//	alias : 'widget.rewardintegralnamecombox',
//	fieldLabel:i18n('i18n.Integral.awardName'),
//	name:null,
//	searchWindow:null,   //弹出的查询窗口
//	store:null,
//	displayField:'custName',
//	valueField:'custId',
//	queryMode:'local',
//	queryDelay:2000,
//	forceSelection:true,
//	enableKeyEvents:true,
//	initComponent:function(){
//		var me = this;
//		me.addListener('change',me.changeFn);
//		me.addListener('expand',me.expandFn);
//		me.addListener('keypress',me.keypressFn);
//		this.callParent();
//	},
//	//奖励规则查询combobox的change事件
//	changeFn:function(memberSearchCombox){
//		if(DpUtil.isEmpty(memberSearchCombox.getValue())){
//			memberSearchCombox.setValue(null);
//		}
//	},
//	//奖励规则查询combobox的expand事件
//	expandFn:function(memberSearchCombox){
//		this.showSearchMemberWinFn(memberSearchCombox);
//	},
//	//奖励规则查询combobox的keypress事件
//	keypressFn:function(memberSearchCombox,e){
//		if(e.getKey() == Ext.EventObject.ENTER){
//			this.showSearchMemberWinFn(memberSearchCombox);
//		}
//	},
//	//设置奖励规则查询结果到父界面,memberResult为当前选中的联系人记录，resultRecords是查询所有的奖励规则联系人信息
//	setValueComeBack:function(memberResult,resultRecords){
//	},
//	//显示奖励规则查询窗口
//	showSearchMemberWinFn:function(memberSearchCombox){
//		if(memberSearchCombox.searchWindow == null){
//			SearchMemberView.showSearchMemberWin(memberSearchCombox);
//		}else{
//			//把当前下拉框中的值带入打开的查询界面
//			var custName = memberSearchCombox.getRawValue();
//			if(DpUtil.isEmpty(custName)){
//				custName = memberSearchCombox.getValue();
//			}
//			memberSearchCombox.searchWindow.loadCustName(custName);
//			memberSearchCombox.searchWindow.show();
//		}
//		//清空历史数据
//		memberSearchCombox.store.removeAll();
//	}
//});

//选择奖励名称 弹出框
Ext.define('RewardIntegralNameWin',{
	extend:'PopWindow',
	title:i18n('i18n.ManualRewardIntegralEditView.rulename'),
	manualRewardIntegralEditPanel:null,
	manualRewardIntegralEditData:manualRewardIntegralEditDataControl,
	parent:null,
	y:0,
	width:820,
	height:450,
	layout:'fit',
	initComponent:function(){
		var me = this;
		me.manualRewardIntegralEditPanel = Ext.create('RewardRuleManagerResultGrid',{'rewardRuleManagerData':me.manualRewardIntegralEditData});
		me.items = me.getItems();
		me.fbar = me.getFbar();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [me.manualRewardIntegralEditPanel];
	},
	getFbar:function(){
		var me = this;
		return [{
			text:i18n('i18n.ManualRewardIntegralEditView.b_ensure'),
			xtype:'button',
			scope:me,
			handler:me.ensureEvent
		},{
			text:i18n('i18n.PotentialCustEditView.cancel'),
			xtype:'button',
			scope:me,
			handler:me.cancelEvent
		}];
	},
	//确定 事件
	ensureEvent:function(){
		var me = this;
		if(me.selectDate('ONE')){
			var record = me.selectedRecord()[0];
			RewardIntegRule = record.data;//全局变量 赋值
			var form = me.parent.getForm();
			var integType = record.get('integType');
			form.findField('rulename').setRawValue(record.get('rulename'));
			form.findField('integType').setValue(integType);
			if('SCORE' == integType){//按分值 
				form.findField('integralBasicNumber').setReadOnly(true);
				form.findField('fraction').setValue(1);
				form.findField('integralBasicNumber').setValue(record.get('pointvalue'));
				form.findField('integralBasicNumber').setReadOnly(true);
				form.findField('pointvalue').setRawValue(record.get('pointvalue'));
			}
			if('RATIO' == integType){//按比例 
				form.findField('fraction').setRawValue(record.get('fraction'));
				form.findField('integralBasicNumber').setReadOnly(false);
	//			form.findField('pointvalue').setRawValue(22);
			}
			me.close();
		}
	},
	//取消 事件
	cancelEvent:function(){
		var me = this;
		me.close();
	},
	selectDate:function(sum){
		var me = this;
		var selection=me.selectedRecord();
		if ('ONE'==sum && selection.length != 1) {
			Ext.MessageBox.alert(i18n('i18n.PotentialCustManagerView.messageTip'), i18n('i18n.ManualRewardIntegralEditView.m_selectOnlyOne'));
			return false;
		}
		if ('MANY'==sum && selection.length == 0) {
			Ext.MessageBox.alert(i18n('i18n.PotentialCustManagerView.messageTip'), i18n('i18n.ManualRewardIntegralEditView.m_selectOne'));
			return false;
		}
		return true;
	},
	selectedRecord:function(){
		var me = this;
		var grid = me.manualRewardIntegralEditPanel;
		return grid.getSelectionModel().getSelection();
	}
});
//积分规则管理默认显示数据结果
Ext.define('RewardRuleManagerResultGrid',{
	extend:'SearchGridPanel',
	rewardRuleManagerData:null,//数据Data
	initComponent:function(){
		var me = this;
		me.store = me.rewardRuleManagerData.getRewardIntegRuleStore();
		me.dockedItems = me.getMyDockedItems();
		me.selModel = Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE'});
		me.columns = me.getColumns();
		this.callParent();
	},
	getColumns:function(){
		var me = this;
		return [{
			header:i18n('i18n.ManualRewardIntegralEditView.rewardId'),
			hidden:true,
			dataIndex:'id'
		},{
			header:i18n('i18n.RewardRuleManagerView.integralRewardType'),
			dataIndex:'ruletype',
			renderer : function(value) {
				return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.AWARD_TYPE);
			}
		},{
			header:i18n('i18n.RewardRuleManagerView.rulename'),
			dataIndex:'rulename'
		},{
			header:i18n('i18n.ManualRewardIntegralEditView.fraction'),
			dataIndex:'fraction'
		},{
			header:i18n('i18n.RewardRuleManagerView.integType'),
			dataIndex:'integType',
			renderer : function(value) {
				return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.CUST_RULETYPE);
			}
		},{
			header:i18n('i18n.ManualRewardIntegralEditView.manualRewardIntegral'),
			dataIndex:'pointvalue'
		},{
			header:i18n('i18n.RewardRuleManagerView.pointbegintime'),
			dataIndex:'pointbegintime',
			renderer : function(value) {
				return DpUtil.renderDate(value);
			}
		},{
			header:i18n('i18n.RewardRuleManagerView.pointendtime'),
			dataIndex:'pointendtime',
			renderer : function(value) {
				return DpUtil.renderDate(value);
			}
		},{
			header:i18n('i18n.RewardRuleManagerView.pointdesc'),
			dataIndex:'pointdesc'
		},{
			header : i18n('i18n.PotentialCustManagerView.createTime'),
			dataIndex:'createDate',
			renderer : function(value) {
				return DpUtil.renderDate(value);
			}
		},{
			header :i18n('i18n.PotentialCustManagerView.creator'),
			dataIndex:'cname'
		},{
			header : i18n('i18n.PotentialCustManagerView.lastUpdate'),
			dataIndex:'mname'
		},{
			header : i18n('i18n.PotentialCustManagerView.lastUpdateTime'),
			dataIndex:'modifyDate',
			renderer : function(value) {
				return DpUtil.renderDate(value);
			}
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
	}
});

