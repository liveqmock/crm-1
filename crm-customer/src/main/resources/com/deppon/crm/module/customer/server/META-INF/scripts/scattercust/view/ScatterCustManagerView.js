//设置Data层对象
var scatterCustDataControl =  (CONFIG.get("TEST"))? new ScatterCustSearchDataTest():new ScatterCustSearchData();
// var scatterCustDataControl = new ScatterCustSearchData();
/*
 * 散客管理主界面ScatterCust
 */
//var oldScRecode = null;
Ext.define('ScatterCustManagerPanel',{
	extend:'BasicPanel',
	autoScroll:true,
	searchCondition:null, //查询条件
	searchResult:null, //查询结果
	scatterCust:null,//散客data
//	title:i18n('i18n.ScatterCustManagerView.individualManagement'),
	layout:{
        type:'vbox',
        align:'stretch'
    },
	initComponent:function(){
		var me = this;
		var record = new ScatterCustConditionModel();
		me.searchCondition = new SearchConditionForm({'scatterCust':me.scatterCust,'record':record,'parent':me});
		me.searchCondition.loadRecord(record);
		//默认部门
		me.searchCondition.getForm().reset();
		DpUtil.defaultDept(me.searchCondition,'deptId');
		//设置默认查询时间间隔为三个月
		var date = new Date();
		me.searchCondition.getForm().findField("overTime").setValue(date);
		 
		date.setMonth(date.getMonth()-3);
		me.searchCondition.getForm().findField("beginTime").setValue(date);
		
		me.searchResult =  new SearchResultGrid({'scatterCust':me.scatterCust,'parent':me});
		me.buttonBar = me.getButtonBar();
		
		me.formPanel = Ext.create('Ext.form.Panel', {
			id:'wrapForm'+'khgl-skgl-01',					// 'wrapForm'+'khgl-qkgl-01'
			border : false,
			frame : true,
			//height:140,
			items : [{
				id : 'id1'+'khgl-skgl-01',				// 'id1' + 
				//'khgl-qkgl-01'
				name : 'first',
				height:24
			}, {
				id : 'id2'+'khgl-skgl-01',				// 'id2' +'khgl-qkgl-01'
				name : 'last'
				,height:100						// height:160
			}]
		});
		//设置items
		me.items = me.getItems();
		this.callParent();
	},
	getButtonBar:function(){
		var me = this;
		return new ScatterCustManagerButtonPanel({'parent':me});
	},
	getItems:function(){
		var me = this;
		return [{
				xtype:'basicpanel',
				height:230,
				items:[me.searchCondition]
			},{
				xtype:'basicpanel',
				height:36,
				items:[me.buttonBar]
			},{
				xtype:'basicpanel',
				flex:1,
				items:[me.searchResult]
			},{
				xtype:'basicpanel',
				height:100,
				items:[me.formPanel]
		}];
	},
	// 修改 散客后重新加载潜在客户数据
	reloadScatterCust:function(record){
		var me = this;
		var store = me.scatterCust.getScatterCustStore();
		var reloadScatterCustSuccess=function(response){
			store.insert(0,response.scatterCust);
		};
		var reloadScatterCustSuccessFail = function(response){
		        MessageUtil.showMessage(response.message);
		};
		//删除修改 的数据
		store.remove(record);
		if(Ext.isEmpty(record)){
			return ;
		}
		if(DpUtil.isEmpty(record.get('id'))){
			return;
		}
		//如果该页分页已满则删除查询结果中最后一条数据
		var count = store.getCount();
		if(count>=20){
			store.remove(store.getAt(count-1));
		}
		//从服务器通过id获得新增的数据
		var params={};
		params.scatterCustId = record.get('id');
		me.scatterCust.serachScatterById(params,reloadScatterCustSuccess,reloadScatterCustSuccessFail);
		
	}
});
/**
 * 操作按钮面板
 */
Ext.define('ScatterCustManagerButtonPanel',{
	extend:'NormalButtonPanel',
	parent:null,
	items:null,
	parent:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'leftbuttonpanel',
			width:430,
			items:[ {
				text : i18n('i18n.PotentialCustManagerView.viewDetails'),
				hidden:!isPermission('/customer/ScatterVBtn.action'),
				xtype:'button',
				id:'scatterCustViewInfo',
				scope:me.parent.searchResult,
				handler:function(){
					var me =  this;
					var grid = Ext.getCmp('searchScatterCustResultGrid');
					selection=grid.getSelectionModel().getSelection();
					if (selection.length != 1) {
						MessageUtil.showMessage( i18n('i18n.PotentialCustManagerView.operation_message'));
						return;
					}
					var params = {};
					params.scatterCustId = selection[0].getId();
					var searchSuccessFn=function(result){
						var scatterCustModel = new ScatterCustModel(result.scatterCust);
						me.parent.searchResult.lookScatterCustDetail(grid,scatterCustModel);
					}
					var searchFail=function(result){
						if(!Ext.isEmpty(result.message)) {
							MessageUtil.showErrorMes(result.message);
						} 
					}
					me.parent.scatterCust.serachScatterById(params,searchSuccessFn,searchFail);
					
				}
			}, {
				text:i18n('i18n.PotentialCustManagerView.add'),
				hidden:!isPermission('/customer/ScatterABtn.action'),
				xtype:'button',
				scope:me,
				 handler:me.addScatterCust
			}, {
				text:i18n('i18n.PotentialCustManagerView.update'),
				hidden:!isPermission('/customer/ScatterUBtn.action'),
				id:'scatterCustUpdate',
				xtype:'button',
				scope:me.parent.searchResult,
				handler:me.parent.searchResult.updateScatterCust
			}, {
				text:i18n('i18n.PotentialCustManagerView.delete'),
				hidden:!isPermission('/customer/ScatterDBtn.action'),
				id:'scatterCustDelete',
				xtype:'button',
				scope:me.parent.searchResult,
				handler:me.parent.searchResult.delScatterCust
			}, {
				text:i18n('i18n.PotentialCustManagerView.potentialChangeToTemp'),
				hidden:!isPermission('/customer/ScatterCBtn.action'),
				xtype:'button',
				id:'scatterCustChangeTo',
				scope:me,
				handler:me.changeToTempCust
			}, {
				text : i18n('i18n.PotentialCustManagerView.visitLog'),
				xtype : 'button',
				scope : me.parent,
				hidden : true,
				handler:me.parent.searchResult.addNewVisit
			}  ]
		},{
			xtype:'middlebuttonpanel'
		},{
			xtype:'rightbuttonpanel',
			width:220,
			items:[{
				xtype : 'button',
				text : i18n('i18n.PotentialCustManagerView.search'),
				hidden:!isPermission('/customer/ScatterSBtn.action'),
				width : 70,
				scope:me.parent.searchCondition,
				handler : me.parent.searchCondition.searchScatterCustomerList
			},{
				xtype : 'button',
				text : i18n('i18n.MemberCustEditView.reset'),
				// hidden:!isPermission('/customer/ScatterSBtn.action'),
				width : 70,
				scope:me.parent.searchCondition,
			    handler :function(){
			    	var beginTime = Ext.getCmp('searchScatterCustConditionForm').getForm().findField('beginTime').getValue();
			    	var overTime = Ext.getCmp('searchScatterCustConditionForm').getForm().findField('overTime').getValue();    	
			    	me.parent.searchCondition.getForm().reset();
			    	DpUtil.defaultDept(me.parent.searchCondition,'deptId');
			    	var dateTime = new Date();
			    	Ext.getCmp('searchScatterCustConditionForm').getForm().findField('overTime').setValue(dateTime);
			    	dateTime.setMonth(dateTime.getMonth()-3);
			    	Ext.getCmp('searchScatterCustConditionForm').getForm().findField('beginTime').setValue(dateTime);
			    	}
			}]
		}];
	},
	// 新增散客
	addScatterCust:function(){
		var me = this;
		var record = Ext.create('ScatterCustModel');
		record.set('deptId',top.User.deptId);
		record.set('deptName',top.User.deptName);
		//散客类型默认为临代散客，不可编辑
		record.set('scatterCustType','FOSS_SCATTER');
			var editScatterWin = Ext.create('ScatterCustEditWindow',
					{'viewStatus':'add',
					 'scatterCustRecord':record,
					 'scatterCustManagerView':me.parent});
			editScatterWin.show();
	/*		Ext.create('Depon.Lib.oDocHelper', {
				win:editScatterWin,
				helpDoc:{
					windowNum:'khgl-skgl-edit-01'
					,active:true
				},
				sHeight : '140px'
			});*/
	},
	//转为临代散客
	changeToTempCust:function(){
		var me = this;
		var grid = Ext.getCmp('searchScatterCustResultGrid');
		selection=grid.getSelectionModel().getSelection();
		if (selection.length != 1) {
			MessageUtil.showMessage( i18n('i18n.PotentialCustManagerView.operation_message'));
			return;
		}
		MessageUtil.showQuestionMes(i18n('i18n.ScatterUpgradeView.areUSureToChange'),function(rs){
			if(rs == 'yes'){
				var scatterCustId = selection[0].get('id');
				//成功回调函数
				var dealSuccess = function(res){
					var form = Ext.getCmp('searchScatterCustConditionForm');
					MessageUtil.showInfoMes(i18n('i18n.BoundContactNumView.operateSuccess'));
					me.parent.scatterCust.getScatterCustStore().loadPage(1);
				}
				//失败回调函数
				var dealFail = function(response) {
					MessageUtil.showErrorMes( response.message);
				};
				var params = {};
				params.scatterCustId = scatterCustId;
				me.parent.scatterCust.changeToTempCust(params,dealSuccess,dealFail);
			}
		});
	}
});
/**
 * 查询条件
 */
Ext.define('SearchConditionForm',{
	extend:'SearchFormPanel',
	id:'searchScatterCustConditionForm',
	items:null,
	parent:null,
	record:null,
	scatterCust:null,//散客data
	layout:'fit',
	layout : {
		type : 'table',
		columns : 12
	},
	defaultType : 'dptextfield',
	initComponent:function(){
		var me = this;
		scatterCust = me.scatterCust;
		me.defaults = me.getDefaultsContainer();
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return[{
			xtype : 'fieldcontainer',
			colspan : 12,
			border : 0,
			width : 780,
			layout : 'column',
			defaultType : 'dptextfield',
			defaults : {
				width:260,
				enableKeyEvents:true,
				listeners:{
					scope : me,
					keypress : me.keypressEvent
				}
			},
			items : [{
				xtype:'belongdeptcombox',
				name:'deptId',
				allowBlank:false,
				functionName :'ScatterCustomerFunction',
				fieldLabel:i18n('i18n.ScatterUpgradeView.belongdeptName')
			},
			{
				fieldLabel : i18n('i18n.ScatterCustManagerView.scatterCustNumber'),
				name : 'scatterNum'/*,
				maxLength:18,
				minLength:18,
				minLengthText:i18n('i18n.ScatterCustManagerView.PleaseInfoRightNum'),
				maxLengthText:i18n('i18n.ScatterCustManagerView.PleaseInfoRightNum'),
				listeners:{
					blur:function(field){
						var value = field.getValue();
						if(!Ext.isEmpty(value) && value.length != 18){
							MessageUtil.showMessage(i18n('i18n.ScatterCustManagerView.PleaseInfoRightNum'));		
						}
					}
				}*/
			},
			{
				fieldLabel : i18n('i18n.PotentialCustManagerView.customerName'),
				name : 'custName',
				maxLength:20,
				maxLengthText :i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20)
			}]
		},
		{
			xtype : 'fieldcontainer',
			colspan : 12,
			border : 0,
			width : 780,
			layout : 'column',
			defaultType : 'dptextfield',
			defaults : {
				width:260,
				enableKeyEvents:true,
				listeners:{
					scope : me,
					keypress : me.keypressEvent
				}
			},
			items : [
				{
					fieldLabel : i18n('i18n.PotentialCustManagerView.contactName'),
					name : 'contacterName',
					labelWidth:65,
					maxLength:20,
					maxLengthText :i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20)
				}, {
					fieldLabel : i18n('i18n.PotentialCustManagerView.contactPhone'),
					name : 'contacterMobile',
					regexText:i18n('i18n.ScatterCustManagerView.message_rightString'),
					regex : DpUtil.linkWayLimit('M'),
					listeners:{
						change:function(field,newValue,oldValue){
							if(!Ext.isEmpty(newValue)){
								DpUtil.autoChangeMobileLength(field,newValue);
							}
						}
					}
				}, {
					fieldLabel : i18n('i18n.PotentialCustManagerView.contactTel'),
					name : 'contacterPhone',
					regexText:i18n('i18n.ScatterCustManagerView.message_rightString'),
					regex : DpUtil.linkWayLimit('T')
				}
		]
		},
		{
			xtype : 'fieldcontainer',
			colspan : 12,
			border : 0,
			width : 780,
			layout : 'column',
			defaultType : 'dptextfield',
			defaults : {
				width:260,
				enableKeyEvents:true,
				listeners:{
					scope : me,
					change : me.changeEvent,
					keypress : me.keypressEvent
				}
			},
			items : [
				{
					fieldLabel : i18n('i18n.ScatterCustManagerView.custAttribute'),
					xtype : 'combo',
					labelWidth:65,
					width:195,
					// name : 'custNature',
					name:'custAttribute',
					store:me.scatterCust.getCustPropertyStore(),
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code'
				},{
					xtype:'customertrade',
					width:390,
//					margin:'0 0 0 10',
					height:22,
					trade_labelWidth:45,
					trade_width:175,
					userType:'SEARCH',
					trade_margin:'-10 0 0 10',
					second_trade_margin:'-10 0 0 0',
					trade_name:'industry',
					trade_fieldname:i18n('i18n.PotentialCustManagerView.industry'),
					second_trade_labelWidth:65,
					second_trade_width:195,
					second_trade_name:'secondTrade',
					second_trade_forceSelect:false,
					trade_forceSelect:false
//					second_trade_fieldname:''
				},{
					fieldLabel : i18n('i18n.ScatterCustManagerView.scatterCustType'),
					xtype : 'combo',
					name : 'scatterCustType',
					store:me.scatterCust.getScatterCustTypeStore(),
					queryMode:'local',
					labelWidth:65,
					width:195,
		    		forceSelection:true,
					displayField:'codeDesc',
					valueField:'code'
				}
		]
		},
		{
			xtype : 'fieldcontainer',
			colspan : 12,
			border : 0,
			width : 780,
			layout : 'column',
			defaultType : 'dptextfield',
			defaults : {
				width:260,
				enableKeyEvents:true,
				listeners:{
					scope : me,
					change : me.changeEvent,
					keypress : me.keypressEvent
				}
			},
			items : [
				{
					fieldLabel : i18n('i18n.PotentialCustManagerView.goodsPotential'),
					labelWidth:65,
					width:195,
					name : 'volumePotential',
					xtype : 'combobox',
					store:me.scatterCust.getGoodsPotentialStore(),
					queryMode:'local',
		            forceSelection:true,
					displayField:'codeDesc',
					valueField:'code'
				}, {
					fieldLabel : i18n('i18n.PotentialCustManagerView.searchStartTime'),
					xtype : 'datefield',
					format : 'Y-m-d',
					labelWidth:65,
					width:195,
					name : 'beginTime',
					maxValue : new Date()
				}, {
					fieldLabel : i18n('i18n.MemberCustEditView.timeHobbyEnd'),
					xtype: 'datefield',
					format : 'Y-m-d',
					labelWidth:65,
					width:195,
					name : 'overTime',
					maxValue : new Date()
				},{
					fieldLabel:i18n('i18n.member.potenSource'),
					name:'potenSource',
					xtype:'combo',
					width:195,
					labelWidth:65,
					queryMode:'local',
					displayField:'codeDesc',
					valueField:'code',
					store:me.scatterCust.getCustSourceStore()
				}
			]
		},{
			fieldLabel : i18n('i18n.PotentialCustManagerView.bizStatus'),
			colspan : 3,
			xtype : 'combo',
			name : 'businessStatus',
			store:me.scatterCust.getBusinessOpportunityStore(),
			queryMode:'local',
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code'
		}, {
			xtype : 'fieldcontainer',
			colspan : 3,
			border : 0,
			layout : 'column',
			defaultType : 'numberfield',
			defaults : {
				hideTrigger: true,
				mouseWheelEnabled: false,
				minValue: 0,
				enableKeyEvents:true,
				listeners:{
					scope : me,
					keypress : me.keypressEvent
				}
			},
			items : [ {
				fieldLabel : i18n('i18n.ScatterCustManagerView.beginShipWeight'),
				labelWidth : 65,
				width : 120,
				name : 'beginShipWeight',
				regexText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',10),
				regex : /^\d{0,10}$/
			},
			{xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
			{
				fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
				name : 'overShipWeight',
                hideLabel: true,
				width : 65,
				labelWidth : 10,
				regexText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',10),
				regex : /^\d{0,10}$/
			} ]
		}, {
			xtype : 'fieldcontainer',
			colspan : 3,
			border : 0,
			layout : 'column',
			defaultType : 'numberfield',
			defaults : {
				hideTrigger: true,
				mouseWheelEnabled: false,
				minValue: 0,
				enableKeyEvents:true,
				listeners:{
					scope : me,
					keypress : me.keypressEvent
				}
			},
			items : [ {
				fieldLabel : i18n('i18n.ScatterCustManagerView.beginShipVotes'),
				name : 'beginShipVotes',
				labelWidth : 65,
				width : 120,
				regexText:i18n('i18n.ScatterCustManagerView.message_rightString'),
				regex : /^\d{0,10}$/
			}, 
			{xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
			{
				fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
				name : 'overShipVotes',
                hideLabel: true,
				width : 65,
				labelWidth : 10,
				regex : /^\d{0,10}$/,
				regexText:i18n('i18n.ScatterCustManagerView.message_rightString')
			} ]
		}, {
			xtype : 'fieldcontainer',
			colspan : 3,
			border : 0,
			layout : 'column',
			defaultType : 'numberfield',
				defaults : {
					hideTrigger: true,
					mouseWheelEnabled: false,
					minValue: 0,
					enableKeyEvents:true,
					listeners:{
						scope : me,
						keypress : me.keypressEvent
					}
				},
				items : [ {
					fieldLabel : i18n('i18n.ScatterCustManagerView.beginShipAmount'),
					name : 'beginShipAmount',
					labelWidth : 65,
					width : 120,
					regexText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',10),
					regex : /^\d{0,10}$/
				}, 
				{xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
				{
					fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
					name : 'overShipAmount',
                    hideLabel: true,
					width : 65,
					labelWidth : 10,
					regexText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',10),
					regex : /^\d{0,10}$/
				} ]
			}, {
				fieldLabel : i18n('i18n.PotentialCustManagerView.coopIntention'),
				colspan : 3,
				xtype : 'combo',
				name : 'coopIntention',
				store:me.scatterCust.getCoopertationIntensionStore(),
				queryMode:'local',
                forceSelection:true,
				displayField:'codeDesc',
				valueField:'code'
			},
			{
				xtype : 'fieldcontainer',
				colspan : 3,
				border : 0,
				layout : 'column',
				defaultType : 'numberfield',
				defaults : {
					hideTrigger: true,
					mouseWheelEnabled: false,
					minValue: 0,
					enableKeyEvents:true,
					listeners:{
						scope : me,
						keypress : me.keypressEvent
					}
				},
				items : [ {
					fieldLabel : i18n('i18n.ScatterCustManagerView.beginArrivalWeight'),
					name : 'beginArrivalWeight',
					labelWidth : 65,
					width : 120,
					regexText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',10),
					regex : /^\d{0,10}$/
				}, 
				{xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
				{
					fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
					name : 'overArrivalWeight',
                    hideLabel: true,
					width : 65,
					labelWidth : 10,
					regexText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',10),
					regex : /^\d{0,10}$/
				} ]
			}, {
				xtype : 'fieldcontainer',
				colspan : 3,
				border : 0,
				layout : 'column',
				defaultType : 'numberfield',
				defaults : {
					hideTrigger: true,
					mouseWheelEnabled: false,
					minValue: 0,
					enableKeyEvents:true,
					listeners:{
						scope : me,
						keypress : me.keypressEvent
					}
				},
				items : [ {
					fieldLabel : i18n('i18n.ScatterCustManagerView.beginArrivalVotes'),
					name : 'beginArrivalVotes',
					labelWidth : 65,
					width : 120,
					regexText:i18n('i18n.ScatterCustManagerView.message_rightString'),
					regex : /^\d{0,10}$/
				}, 
				{xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
				{
					fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
					name : 'overArrivalVotes',
                    hideLabel: true,
					width : 65,
					labelWidth : 10,
					regexText:i18n('i18n.ScatterCustManagerView.message_rightString'),
					regex : /^\d{0,10}$/
				} ]
			}, {
				xtype : 'fieldcontainer',
				colspan : 6,
				border : 0,
				layout : 'column',
				defaultType : 'numberfield',
				defaults : {
					hideTrigger: true,
					mouseWheelEnabled: false,
					minValue: 0,
					enableKeyEvents:true,
					listeners:{
						scope : me,
						keypress : me.keypressEvent
					}
				},
				items : [ {
					fieldLabel : i18n('i18n.ScatterCustManagerView.beginArrivalAmount'),
					name : 'beginArrivalAmount',
					labelWidth : 65,
					width : 120,
					regexText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',10),
					regex : /^\d{0,10}$/
				},
				{xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
				{
					fieldLabel : i18n('i18n.PotentialCustManagerView.searchEndTime'),
					name : 'overArrivalAmount',
                    hideLabel: true,
					width : 65,
					labelWidth : 10,
					regexText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',10),
					regex : /^\d{0,10}$/
				} ]
			},
			{
			xtype : 'fieldcontainer',
			colspan : 12,
			border : 0,
			width : 780,
			layout : 'column',
			defaultType : 'dptextfield',
			defaults : {
				width : 260,
				enableKeyEvents : true,
				listeners : {
					scope : me,
					change : me.changeEvent,
					keypress : me.keypressEvent
				}
			},
			items : [{
						fieldLabel : i18n('i18n.ScatterCustManagerView.businessType'),
						xtype : 'combo',
						labelWidth : 65,
						width : 195,
						name : 'businessType',
						value:'LESS_THAN_TRUCKLOAD',
						store : me.scatterCust.getBusinessTypeStore(),
						queryMode : 'local',
						forceSelection : true,
						editable:false,
						displayField : 'codeDesc',
						valueField : 'code'
					}]
		}
		];
	},
	//增加监听事件
	getDefaultsContainer:function(){
		var me = this;
		return {
			labelWidth:65,
			width:195,
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
    		me.searchScatterCustomerList();
    	}
	},
	//监听change事件
	changeEvent:function(field,newValue){
		var me = this;
		//如果是数据字典则显
		if('combobox' == field.getXType() && Ext.isEmpty(newValue)){
			field.setValue(null);
		}
	},
	// 时间组件相对校验 开始值不得大于截止值符合逻辑返回true
	checkDataOpposite : function(objPre, objNext) {
		if ((DpUtil.isEmpty(objPre.getValue())
				&& DpUtil.isEmpty(objNext.getValue()))) {
			return true;
		}
		if(!DpUtil.isEmpty(objPre.getValue())&& !DpUtil.isEmpty(objNext.getValue())){
			if(objPre.getValue() <= objNext.getValue()){
				if((objNext.getValue().getTime()-objPre.getValue().getTime())<=1000*3600*24*90){
					return true;
				}
			}
		}
		return false;
	},
	//numberfield组件相对校验 开始值不得大于截止值 符合逻辑返回true
	checkNumberOpposite : function(objPre, objNext) {
		var success = true;
		var message = i18n('i18n.ScatterCustManagerView.message_checkNumberZ');
		if(!DpUtil.isEmpty(objNext.getValue()) && objNext.getValue() <= 0){
			success = false;
			message = i18n('i18n.ScatterCustManagerView.message_checkNumberO');
		}else if (!DpUtil.isEmpty(objPre.getValue())&&!DpUtil.isEmpty(objNext.getValue())) {
			if(objPre.getValue() > objNext.getValue() || objNext.getValue() <=0){
				success = false;
				message = i18n('i18n.ScatterCustManagerView.message_checkNumberO');
			}
		}
		return {success:success,message:message};
	},
	//散客查询
	searchScatterCustomerList:function(){
		var me = this;
		//查询条件不可全部为空
		if(!me.validateCondition()){
			MessageUtil.showErrorMes(i18n('i18n.PotentialCustManagerView.message_notNull'));
			return;
		}
		var checkDataInOneYearResult = DpUtil.checkDataInOneYear(me.getForm().findField('beginTime'),me.getForm().findField('overTime'));
		if(!checkDataInOneYearResult.success){
			MessageUtil.showMessage( checkDataInOneYearResult.message);
			return;
		}
		//发货量
		var shipWeight = me.checkNumberOpposite(me.getForm().findField("beginShipWeight"),me.getForm().findField("overShipWeight"));
		if(!shipWeight.success){
			MessageUtil.showMessage(shipWeight.message);
			return;
		}
		//发货票数
		var shipVotes = me.checkNumberOpposite(me.getForm().findField("beginShipVotes"),me.getForm().findField("overShipVotes"));
		if(!shipVotes.success){
			MessageUtil.showMessage(shipVotes.message);
			return;
		}
		//发货金额
		var shipAmount = me.checkNumberOpposite(me.getForm().findField("beginShipAmount"),me.getForm().findField("overShipAmount"));
		if(!shipAmount.success){
			MessageUtil.showMessage(shipAmount.message);
			return;
		}
		//到达货量
		var arrivalWeight = me.checkNumberOpposite(me.getForm().findField("beginArrivalWeight"),me.getForm().findField("overArrivalWeight"));
		if(!arrivalWeight.success){
			MessageUtil.showMessage(arrivalWeight.message);
			return;
		}
		// 到达票数
		var arrivalVotes = me.checkNumberOpposite(me.getForm().findField("beginArrivalVotes"),me.getForm().findField("overArrivalVotes"));
		if(!arrivalVotes.success){
			MessageUtil.showErrorMes(arrivalVotes.message);
			return;
		}
		//到达金额
		var arrivalAmount = me.checkNumberOpposite(me.getForm().findField("beginArrivalAmount"),me.getForm().findField("overArrivalAmount"));
		if(!arrivalAmount.success){
			MessageUtil.showMessage(arrivalAmount.message);
			return;
		}
		if(!me.getForm().isValid()){
			MessageUtil.showMessage(i18n('i18n.PotentialCustManagerView.message_warn'));
			return;
		}
		me.scatterCust.getScatterCustStore().loadPage(1);
	},
	//校验是否查询条件都为空
	validateCondition:function(){
		var me = this;
		var flag = false;
		me.getForm().getFields().each(function(field){
			if(!(DpUtil.isEmpty(field.getValue()))&&field.getValue()!=i18n('i18n.PotentialCustManagerView.searchEndTime')){
				flag = true;
			}
		});
		return flag;
	}
});

/**
 * 查询结果
 */
Ext.define('SearchResultGrid',{
	extend:'BasicPanel',
	scatterCust:null,//散客data
	store:null,
	items:null,
	autoScroll:true,
	parent : null,
	initComponent:function(){
		var me = this;
//		var ScatterCustModel = new ScatterCustModel();
		me.store = me.scatterCust.initialScatterCustStore(me.beforeLoadScatterFn);
		me.items = me.getItems();
		this.callParent();

		me.scatterCust.getScatterCustStore().on('load',function(s,records){   
	        var girdcount=0;   
	        if(s.getCount()<= 0){
	        	MessageUtil.showMessage(i18n('i18n.PotentialCustManagerView.nothingHaveBeenFoundMsg'));
	        }
	        s.each(function(r){
	        	//TODO 增加背景颜色
	        	if('FOSS_SCATTER' == r.get('scatterCustType') ||
	        		(!Ext.isEmpty(r.get('memberId'))&& 1 == r.get('isCancel'))){
	           	   //给单元格涂色
	           	   	var cells = me.down('grid').getView().getNodes()[girdcount].children;
 	    			for(var i= 0;i<cells.length;i++){
 	    				cells[i].style.backgroundColor=(1 == r.get('isCancel'))?'#FF8F59':'#ffaad5';
 	    			}
	            }
 	    		girdcount++;
	        });   
		});  
	},
	getItems:function(){
		var me = this;
		return [ {
			xtype : 'searchgridpanel',
			id:'searchScatterCustResultGrid',
			store:me.store,
			selModel:Ext.create('Ext.selection.CheckboxModel',{
				mode:'SINGLE'
			}),
			emptyText:'没有查询到的信息',
			autoScroll :true,
			columns : [ Ext.create('Ext.grid.RowNumberer',{
				width:45,
				text:i18n('i18n.PotentialCustManagerView.potentialxuhao')
					}),
			            
			 {
				header : 'id',
				dataIndex : 'id',
				hidden:true
			}, {
				header : i18n('i18n.ScatterCustManagerView.scatterCustType'),
				width:70,
				dataIndex : 'scatterCustType',
				renderer : function(value){
					return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.SCATTERCUST_TYPE);
				}
			}, {
				header : i18n('i18n.ScatterCustManagerView.businessType'),
				width:70,
				dataIndex : 'businessType',
				renderer : function(value){
					return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.BUSINESS_TYPE);
				}
			}, {
				header : i18n('i18n.ScatterCustManagerView.scatterCustNumber'),
				width:140,
				dataIndex : 'scatterNum'
			}, {
				header : i18n('i18n.PotentialCustManagerView.customerName'),
				dataIndex : 'custName'
			}, {
				header : i18n('i18n.PotentialCustManagerView.contactName'),
				dataIndex : 'linkManName'
			}, {
				header : i18n('i18n.PotentialCustManagerView.contactPhone'),
				dataIndex : 'linkManMobile'
			}, {
				header : i18n('i18n.PotentialCustManagerView.contactTel'),
				width:130,
				dataIndex : 'linkManPhone'
			}, 
			 {
				header : i18n('i18n.MemberCustEditView.procRedit'),
				dataIndex : 'velocityAmount'
			},
			{
				header:i18n('i18n.PotentialCustManagerView.position'),
				hidden:true,
				dataIndex:'post'
			},
			{
				header:i18n('i18n.PotentialCustManagerView.position'),
				hidden:true,
				dataIndex:'finOver'
			}, {
				header : i18n('i18n.PotentialCustManagerView.bizStatus'),
				dataIndex : 'bussinesState',
				renderer : function(value){
					return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.BUSINESS_STATUS);
				}
			},  {
				header : i18n('i18n.PotentialCustManagerView.coopIntention'),
				dataIndex : 'coopIntention',
				width:65,
				renderer : function(value){
					return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.COOPERATION_INTENTION);
				}
			}, {
				header : i18n('i18n.PotentialCustManagerView.goodsPotential'),
				dataIndex : 'volumePotential',
				renderer : function(value){
					return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.CARGO_POTENTIAL);
				}
			},{
				header:i18n('i18n.PotentialCustManagerView.industry'),
				dataIndex:'trade',
				renderer : function(value){
					return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.TRADE);
				}
			}, {
				header : i18n('i18n.PotentialCustManagerView.developState'),
				dataIndex : 'developState',
				hidden : true,
				renderer : function(value){
					return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.DEVELOP_STATUS);
				}
			}, {
				header : i18n('i18n.PotentialCustManagerView.finalreviTime'),
				dataIndex : 'finalreviTime',
				hidden : true,
				renderer : function(value) {
					return DpUtil.renderDate(value);
				}
			}, {
				header : i18n('i18n.PotentialCustManagerView.reviTimes'),
				dataIndex : 'reviTimes',
				hidden : true,
				renderer : function(value) {}
			}, {
				header : i18n('i18n.PotentialCustManagerView.programmeTime'),
				dataIndex : 'programmeTime',
				hidden : true,
				renderer : function(value) {
					return DpUtil.renderDate(value);
				}
			}, {
				header : i18n('i18n.PotentialCustManagerView.creator'),
				dataIndex : 'createUser'
			}, {
				header : i18n('i18n.PotentialCustManagerView.createTime'),
				dataIndex : 'createDate',
				renderer : function(value) {
					return DpUtil.renderDate(value);
				}
			}, {
				header : i18n('i18n.PotentialCustManagerView.lastUpdate'),
				dataIndex : 'modifyUser'
			}, {
				header : i18n('i18n.PotentialCustManagerView.lastUpdateTime'),
				dataIndex : 'modifyDate',
				renderer : function(value) {
					return DpUtil.renderDate(value);
				}
			} ],
			dockedItems : [ {
				xtype : 'pagingtoolbar',
				store : me.store,
				dock : 'bottom',
				displayInfo : true
			} ],
		   listeners : {
			   scope:me,
			   itemdblclick : function(grid,record){
					var params = {};
					params.scatterCustId = record.getId();
					var searchSuccessFn=function(result){
						var scatterCustModel = new ScatterCustModel(result.scatterCust);
						me.parent.searchResult.lookScatterCustDetail(grid,scatterCustModel);
					}
					var searchFail=function(result){
						if(!Ext.isEmpty(result.message)) {
							MessageUtil.showErrorMes(result.message);
						} 
					}
					me.parent.scatterCust.serachScatterById(params,searchSuccessFn,searchFail);
			   }
		   }
		} ];
	},
	//修改散客
	updateScatterCust:function(){
		var me = this;
		var grid = Ext.getCmp('searchScatterCustResultGrid');
		var aBtn = isPermission('/customer/accountRecognizedABtn.action');
		var uBtn = isPermission('/customer/accountRecognizedUBtn.action');
		var dBtn = isPermission('/customer/accountRecognizedDBtn.action');
		var isP = aBtn&&uBtn&&dBtn;
		selection=grid.getSelectionModel().getSelection();
		if (selection.length != 1) {
			MessageUtil.showMessage( i18n('i18n.PotentialCustManagerView.operation_message'));
			return;
		}else{
			var isLock = selection[0].get('lock');
			var isCancel = selection[0].get('isCancel');
			var isFinOver = selection[0].get('finOver');
			if(isCancel && !isP){
				MessageUtil.showErrorMes('该用户已经作废，只有收银员可以修改！');
				return;
			}else if(isLock && !Ext.isEmpty(selection[0].get("scatterOperatorLogs")[0].workflowId)){
				var workFlowId = selection[0].get("scatterOperatorLogs")[0].workflowId;
				MessageUtil.showErrorMes('该散客在审批中，工作流号:'+workFlowId+',审批未完成不能修改散客信息！');
				return;
			}else if(isFinOver){
				MessageUtil.showErrorMes('该散客财务状态已完结，不能修改散客信息！');
				return;
			}else{
				var params = {};
//				oldScRecode = selection[0];
				params.scatterCustId = selection[0].getId();
				var searchSuccessFn=function(result){
					var scatterCustModel = new ScatterCustModel(result.scatterCust);
					var editScatterWin = Ext.create('ScatterCustEditWindow',{'viewStatus':'update',
						 'scatterCustRecord':scatterCustModel,
						 'scatterCustManagerView':me.parent});
					editScatterWin.show();
					//省市区有change事件  地址特殊处理
					editScatterWin.down('basicformpanel').getForm().findField('address').setValue(scatterCustModel.get('address'));
				}
				var searchFail=function(result){
					if(!Ext.isEmpty(result.message)) {
						MessageUtil.showErrorMes(result.message);
					} 
				}
				me.parent.scatterCust.serachScatterById(params,searchSuccessFn,searchFail);
				
				
		/*		Ext.create('Depon.Lib.oDocHelper', {
					win:editScatterWin,
					helpDoc:{
						windowNum:'khgl-skgl-edit-01'
							,active:true
					},
					sHeight : '140px'
				});*/

			}
		}
	},
	//删除散客
	delScatterCust:function(){
		var me = this;
		var grid = Ext.getCmp('searchScatterCustResultGrid');
		selection=grid.getSelectionModel().getSelection();
		if (selection.length != 1) {
			MessageUtil.showMessage( i18n('i18n.PotentialCustManagerView.operation_message'));
			return;
		}
		MessageUtil.showQuestionMes(i18n('i18n.PotentialCustManagerView.confirm_message'), function(e) {
			if (e == 'yes') {
				var scatterCustIds = new Array();
				for (var j = 0; j < selection.length; j++) {
					scatterCustIds.push(selection[j].get('id'))
				}
				//成功回调函数
				var delSuccess = function(response){
					var form = Ext.getCmp('searchScatterCustConditionForm');
		        	 MessageUtil.showInfoMes(i18n('i18n.PotentialCustManagerView.deleteSuccess'));
		        	 me.scatterCust.getScatterCustStore().loadPage(1);
				};
				//失败回调函数
				var delFail = function(response) {
					MessageUtil.showErrorMes( response.message);
				};
				var params = {};
				params.scatterCustIds = scatterCustIds;
				me.scatterCust.deleteScatterCustomer(params,delSuccess,delFail);
			}
		});
	},
	//查看详情details
	lookScatterCustDetail:function(grid,record){
		var me = this;
		selection=grid.getSelectionModel().getSelection();
		if (selection.length != 1) {
			MessageUtil.showMessage( i18n('i18n.PotentialCustManagerView.operation_message'));
			return;
		}
		var scatterDetail = Ext.create('ScatterCustDetailWindow',{
			'scatterCustManagerView':me.parent,
			'viewStatus':'view',
			'scatterCustRecord':record});
		scatterDetail.show();
		//省市区有change事件  地址特殊处理
		scatterDetail.down('basicformpanel').getForm().findField('address').setValue(record.get('address'));
		scatterDetail.scatterCustAccountPanel.down('grid').store.loadData(record.get('accounts'));
	},
	//回访录入
	addNewVisit:function(grid,record){
		var me = this;
		var grid = Ext.getCmp('searchScatterCustResultGrid');
		selection=grid.getSelectionModel().getSelection();
		if (selection.length != 1) {
			MessageUtil.showMessage( i18n('i18n.PotentialCustManagerView.operation_message'));
			return;
		}
	},
	//beforeLoad方法
	beforeLoadScatterFn:function(store, operation, eOpts){
		var searchScatterForm = Ext.getCmp("searchScatterCustConditionForm");
		if(searchScatterForm!=null){
			searchScatterForm.getForm().updateRecord(searchScatterForm.record);
			//设置请求参数
			var searchCustomerCondition = searchScatterForm.record.data;
			var searchParams = { //客户名称
					'scatterCustomerCondition.custName':searchCustomerCondition.custName,
					'scatterCustomerCondition.deptId':searchCustomerCondition.deptId,
					//散客编码
					'scatterCustomerCondition.scatterNum':searchCustomerCondition.scatterNum,
					//散客类型
					'scatterCustomerCondition.scatterCustType':searchCustomerCondition.scatterCustType,
					// 行业
					'scatterCustomerCondition.industry':searchCustomerCondition.industry,
					// 二級行业
					'scatterCustomerCondition.secondTrade':searchCustomerCondition.secondTrade,
					// 二級行业
					'scatterCustomerCondition.potenSource':searchCustomerCondition.potenSource,
					//合作意向
					'scatterCustomerCondition.coopIntention':searchCustomerCondition.coopIntention,
					//客户属性  出发客户 到达客户
					'scatterCustomerCondition.custAttribute':searchCustomerCondition.custAttribute,
					//联系人姓名
					'scatterCustomerCondition.contacterName':searchCustomerCondition.contacterName,
					//联系人手机
					'scatterCustomerCondition.contacterMobile':searchCustomerCondition.contacterMobile,
					//联系人电话
//					'scatterCustomerCondition.zoneCode':searchCustomerCondition.zoneCode,
					'scatterCustomerCondition.contacterPhone':searchCustomerCondition.contacterPhone,
					//职位
					'scatterCustomerCondition.position':searchCustomerCondition.position,
					//货量潜力
					'scatterCustomerCondition.volumePotential':searchCustomerCondition.volumePotential,
					//创建时间
					'scatterCustomerCondition.beginTime':searchCustomerCondition.beginTime,
					'scatterCustomerCondition.overTime':searchCustomerCondition.overTime,
					//商机状态
					'scatterCustomerCondition.businessStatus':searchCustomerCondition.businessStatus,
					//发货量
					'scatterCustomerCondition.beginShipWeight':searchCustomerCondition.beginShipWeight,
					'scatterCustomerCondition.overShipWeight':searchCustomerCondition.overShipWeight,
					//发货票数
					'scatterCustomerCondition.beginShipVotes':searchCustomerCondition.beginShipVotes,
					'scatterCustomerCondition.overShipVotes':searchCustomerCondition.overShipVotes,
					//发货金额
					'scatterCustomerCondition.beginShipAmount':searchCustomerCondition.beginShipAmount,
					'scatterCustomerCondition.overShipAmount':searchCustomerCondition.overShipAmount,
					//到达货量
					'scatterCustomerCondition.beginArrivalWeight':searchCustomerCondition.beginArrivalWeight,
					'scatterCustomerCondition.overArrivalWeight':searchCustomerCondition.overArrivalWeight,
					//到达票数
					'scatterCustomerCondition.beginArrivalVotes':searchCustomerCondition.beginArrivalVotes,
					'scatterCustomerCondition.overArrivalVotes':searchCustomerCondition.overArrivalVotes,
					//到达金额
					'scatterCustomerCondition.beginArrivalAmount':searchCustomerCondition.beginArrivalAmount,
					'scatterCustomerCondition.overArrivalAmount':searchCustomerCondition.overArrivalAmount,
					'scatterCustomerCondition.businessType':searchCustomerCondition.businessType
					};
			Ext.apply(operation,{
				params : searchParams
				}
			);	
		}
	}
});

Ext.onReady(function() {
	var params =[
		// 行业
		'TRADE',
		//业务类别
		'BUSINESS_TYPE',
		//二级行业
		'SECOND_TRADE',
		// 客户来源
		'CUST_SOURCE',
		// 商机状态
		'BUSINESS_STATUS',
		// 合作物流
		'COOPERATION_LOGISTICS',
		// 合作意向
		'COOPERATION_INTENTION',
		// 货量潜力
		'CARGO_POTENTIAL',
		// 开发状态
		'DEVELOP_STATUS',
		//公司规模
		'FIRM_SIZE',
		//公司性质
		'COMP_NATURE',
		//客户属性
		'CUSTOMER_NATURE',
		//客户性质
		'CUSTOMER_TYPE',
		//散客类型
		'SCATTERCUST_TYPE',
		//散客 账号性质
		'ACCOUNT_NATURE_SC'];
    initDataDictionary(params);

	Ext.create('Ext.Viewport', {
		layout : {
			type : 'fit'
		},
		items : [ Ext.create('ScatterCustManagerPanel', {
			'scatterCust' : scatterCustDataControl
		}) ]

	});
	
	Ext.create('Depon.Lib.oDocHelper', {
		helpDoc:{				// 帮助实体：
			windowNum:'khgl-skgl-01'	// TODO:帮助文档的ID
										// belongModule(首字母缩写)+belongMenu（首字母缩写）+windowNum(弹窗编号)+首编时间戳
			,active:true			// 记录操作员操作，是否选择了”隐藏帮助“；
		}
	});
});


