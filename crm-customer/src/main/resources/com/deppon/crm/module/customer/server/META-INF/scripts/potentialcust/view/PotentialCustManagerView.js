//设置Data层对象
var PotentialCustSearchDataControl =  (CONFIG.get("TEST"))? new PotentialCustSearchDataTest():new PotentialCustSearchData();
//var PotentialCustSearchDataControl =  new PotentialCustSearchData();
/**
* 潜在客户管理界面
*/
Ext.define('PotentialCustManagerPanel',{
	extend:'BasicPanel',
	tbar:null,
	autoScroll:true,
	searchCondition:null, //查询条件
	searchResult:null, //查询结果
	potentialCust:null,//潜客data
	buttonBar:null,
	items:null,
	layout:{
        type:'vbox',
        align:'stretch'
    },
	initComponent:function(){
		var me = this;
		//设置tbar
		var record = new PotentialCustConditionModel();
		me.searchCondition = new SearchConditionForm({'potentialCust':me.potentialCust,'record':record,'parent':me});
		//me.searchCondition.loadRecord(record);
		//默认部门
		DpUtil.defaultDept(me.searchCondition,'deptId');
		
		me.searchResult =  new SearchResultGrid({'potentialCust':me.potentialCust,'parent':me});
		
		me.formPanel = Ext.create('Ext.form.Panel', {
			id:'wrapForm'+'khgl-qkgl-01',					// 'wrapForm'+'khgl-qkgl-01'
			border : false,
			frame : true,
			items : [{
				id : 'id1'+'khgl-qkgl-01',				// 'id1' + 'khgl-qkgl-01'
				name : 'first',
				height:24
			}, {
				id : 'id2'+'khgl-qkgl-01',				// 'id2' +'khgl-qkgl-01'
				name : 'last'
				,height:120						// height:160
			}]
		});
		
		me.buttonBar = me.getButtonBar();
		//设置items
		me.items = [{
				xtype:'basicpanel',
				height:160,
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
				height:120,
				items:[me.formPanel]
		}];
		this.callParent();
	},
	getButtonBar:function(){
		var me = this;
		return new PotentialManageButtonPanel({'parent':me});
	},
	// 新增潜在客户
	addNewPotential:function(){
		var newPotentialCust = Ext.create('PotentialCustModel');
		//设置当前登录用户所在部门所在城市
		newPotentialCust.set('city',CurrentCity.id);
		newPotentialCust.set('cityName',CurrentCity.name);
		var potentialCustStore = this.potentialCust.getPotentialCustStore();
//		potentialCustStore.insert(0,newPotentialCust);
		var editPotentialWin = Ext.create('PotentialCustEditWindow',{'viewStatus':'add',
																	 'potentialCustRecord':newPotentialCust,
																	 'potentialCustManagerView':this});
		editPotentialWin.show();
		
		Ext.create('Depon.Lib.oDocHelper', {
			win:editPotentialWin,
			helpDoc:{				// 帮助实体：
				windowNum:'khgl-qkgl-edit-01'	// TODO:帮助文档的ID	belongModule(首字母缩写)+belongMenu（首字母缩写）+windowNum(弹窗编号)+首编时间戳
				,active:true			// 记录操作员操作，是否选择了”隐藏帮助“；
			},
			sHeight : '140px'
		});
	},
	// 新增/修改 潜客后重新加载潜在客户数据
	reloadPotentialCust:function(record,updateRecord){
		var me = this;
		var store = Ext.getCmp('searchPotentialCustResultGrid').store;
		if(!Ext.isEmpty(record)){
			record.set('createUser',top.User.empName);
			store.insert(store.getCount(),record);
		}else if(!Ext.isEmpty(updateRecord)){
			updateRecord.set('modifyUser',top.User.empName);
			updateRecord.set('modifyDate',new Date());
		}
		Ext.getCmp('searchPotentialCustConditionForm').searchPotentialCustomerList();
//		// 新增/修改 潜客后重新加载潜在客户数据成功回调函数
//		var reloadPotentialCustSuccess=function(response){
//			var grid = Ext.getCmp('searchPotentialCustResultGrid');
//				// 把从服务器获得数据插入到查询结的第一行
//				 grid.store.insert(0,response.potentialCust);
//		};
//		// 新增/修改 潜客后重新加载潜在客户数据失败回调函数
//		var reloadPotentialCustSuccessFail = function(response){
//		        MessageUtil.showMessage(response.message);
//		};
//		var potentialCustStore = me.potentialCust.getPotentialCustStore();
//		var grid = Ext.getCmp('searchPotentialCustResultGrid');
//		//删除新增/修改 的数据
//		potentialCustStore.remove(record);
//		if(DpUtil.isEmpty(record.get('id'))){
//			return;
//		}
//		//如果该页分页已满则删除查询结果中最后一条数据
//		var count = grid.store.getCount();
//		if(count>=20){
//			potentialCustStore.remove(grid.store.getAt(count-1));
//		}
//		//从服务器通过id获得新增的数据
//		var params={};
//		params.potentialCustId = record.get('id');
//		me.potentialCust.serachPotentialById(params,reloadPotentialCustSuccess,reloadPotentialCustSuccessFail);
		
	},
	//取消保存潜在客户后删除对应的record
	cancelSavePotentialFn:function(record){
		var potentialCustStore = this.potentialCust.getPotentialCustStore();
		potentialCustStore.remove(record);
	},
	//批量导入
	batchImportPotential:function(){
//		Ext.create('ExcelImportUI',{
//			'potentialCust':this.potentialCust
//		}).show();
		Ext.create('UploadAttachmentWin',{'potentialCust':this.potentialCust}).show();
	},
	//回访录入
	addNewVisit:function(){
		
	},
	//excel导出
	exportPotientialToExcel:function(){
		
	}
});
/**
 * 查询条件
 */
Ext.define('SearchConditionForm',{
	extend:'SearchFormPanel',
	id:'searchPotentialCustConditionForm',
	parent:null,
	record:null,
	layout:{
		type:'table',
		columns:3
	},
	defaultType:'dptextfield',
	potentialCust:null,// 潜客data
	initComponent:function(){
		var me = this;
		potentialCust = me.potentialCust;
		me.defaults = me.getDefaultsContainer();
		var date = new Date();
		date.setDate(date.getDate()-30);
		me.items=[{
				xtype:'belongdeptcombox',
				width:250,
				allowBlank:false,
				functionName :'PotentialCustomerFunction',
				name:'deptId',
				fieldLabel:i18n('i18n.ScatterUpgradeView.belongdeptName')
			},  {
				fieldLabel:i18n('i18n.PotentialCustManagerView.customerName'),
				name:'customerName',
				maxLength:20,
				width:250,
				maxLengthText :i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20)
			},{
				fieldLabel:i18n('i18n.PotentialCustManagerView.contactName'),
				name:'contactName',
				maxLength:20,
				width:250,
				maxLengthText :i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20)
			},{
				fieldLabel:i18n('i18n.PotentialCustManagerView.contactPhone'),
				name:'contactPhone',
				width:250,
				regex : DpUtil.linkWayLimit('M')
			},{
				fieldLabel:i18n('i18n.PotentialCustManagerView.contactTel'),
				name:'contactTel',
				width:250,
				regex : DpUtil.linkWayLimit('T')
			},{
				fieldLabel:i18n('i18n.PotentialCustManagerView.coopIntention'),
				name:'coopIntention',
				xtype:'combobox',
				width:250,
				store:me.potentialCust.getCoopertationIntensionStore(),
				queryMode:'local',
                forceSelection:true,
				displayField:'codeDesc',
				valueField:'code'
			},{
				fieldLabel:i18n('i18n.PotentialCustManagerView.custSource'),
				name:'custSource',
				width:250,
				xtype:'combobox',
				store:me.potentialCust.getCustSourceStore(),
				queryMode:'local',
                forceSelection:true,
				displayField:'codeDesc',
				valueField:'code'
			},{
				fieldLabel:i18n('i18n.PotentialCustManagerView.bizStatus'),
				name:'bizStatus',
				width:250,
				xtype:'combobox',
				store:me.potentialCust.getBusinessOpportunityStore(),
				queryMode:'local',
                forceSelection:true,
				displayField:'codeDesc',
				valueField:'code'
			},{
				fieldLabel:i18n('i18n.PotentialCustManagerView.goodsPotential'),
				name:'goodsPotential',
				width:250,
				xtype:'combobox',
				store:me.potentialCust.getGoodsPotentialStore(),
				queryMode:'local',
                forceSelection:true,
				displayField:'codeDesc',
				valueField:'code'
			},{
				fieldLabel:i18n('i18n.PotentialCustManagerView.position'),
				name:'position',
				width:250,
				maxLength:20,
				maxLengthText :i18n('i18n.ManualRewardIntegralEditView.m_maxLength',20)
			},
//			{
//				fieldLabel:i18n('i18n.PotentialCustManagerView.searchStartTime'),
//				xtype:'datefield',
//				format : 'Y-m-d',
//				name:'searchStartTime',
//		        maxValue: new Date()
//			},{
//				xtype: 'fieldcontainer',
//	            collapsible: true,
//	            layout:'column',
//	            defaults: {
//	                hideLabel: true
//	            },
//	            items: [ 
//	                {xtype: 'displayfield',width : 70, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
//					{xtype: 'datefield',width : 130,format : 'Y-m-d',name : 'searchEndTime',maxValue : new Date()}]
//			}
			{
//				fieldLabel:i18n('i18n.PotentialCustManagerView.industry'),
//				name:'industry',
				xtype:'customertrade',
				width:250,
				trade_labelWidth:30,
				trade_width:137,
				userType:'SEARCH',
				trade_margin:'-5 0 0 0',
				trade_name:'industry',
				second_trade_margin:'-5 0 0 0',
				trade_fieldname:i18n('i18n.PotentialCustManagerView.industry'),
				second_trade_labelWidth:60,
				second_trade_width:103,
				second_trade_name:'secondTrade',
				second_trade_forceSelect:false,
				trade_forceSelect:false,
				second_trade_fieldname:''
//				store:me.potentialCust.getIndustryStore(),
//				queryMode:'local',
//                forceSelection:true,
//				displayField:'codeDesc',
//				valueField:'code'
			},{
				xtype : 'fieldcontainer',
				colspan : 1,
				border : 0,
				width:250,
				layout : 'column',
				defaultType : 'datefield',
				defaults : {
					enableKeyEvents:true,
					allowBlank:false,
					editable:false,
					listeners:{
						scope : me,
						keypress : me.keypressEvent,
						change : me.changeEvent
					}
				},
				items : [ {
					fieldLabel : i18n('i18n.PotentialCustManagerView.searchStartTime'),labelWidth : 55,width : 150,format : 'Y-m-d',maxValue : new Date(),
					name : 'searchStartTime',value:date
				},
				{xtype: 'displayfield',width : 10, value: i18n('i18n.PotentialCustManagerView.searchEndTime')},
				{xtype: 'datefield',width : 90,format : 'Y-m-d',name : 'searchEndTime',maxValue : new Date(),value: new Date()} ]

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
						width : 250,
						name : 'businessType',
						value:'LESS_THAN_TRUCKLOAD',
						store : me.potentialCust.getBusinessTypeStore(),
						queryMode : 'local',
						forceSelection : true,
						editable:false,
						displayField : 'codeDesc',
						valueField : 'code'
					}]
		}];
		this.callParent();
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
    		me.searchPotentialCustomerList();
    	}
	},
	//监听change事件
	changeEvent:function(field,newValue){
		var me = this;
		if('combobox' == field.getXType() && Ext.isEmpty(newValue)){
			field.setValue(null);
		}
	},
	// 时间组件相对校验
	checkDataOpposite : function() {
		var me = this;
		var form = this.getForm();
		var objPre = form.findField('searchStartTime');
		var objNext = form.findField('searchEndTime');

		if ((DpUtil.isEmpty(objPre.getValue())
				&& DpUtil.isEmpty(objNext.getValue()))) {
			return true;
		}
		if(!DpUtil.isEmpty(objPre.getValue())&& !DpUtil.isEmpty(objNext.getValue())){
			if(objPre.getValue() <= objNext.getValue()){
				//同一年判断
				if(objNext.getValue().getFullYear()-objPre.getValue().getFullYear() == 0){
					var monthsPre = objNext.getValue().getMonth()-objPre.getValue().getMonth();
					if(monthsPre <= 3 && monthsPre >= 0){
						if(monthsPre == 3){
							if(objNext.getValue().getDate()-objPre.getValue().getDate() > 0){
								return false;
							}else{
								return true;
							}
						}
						return true;
					}
				}
				//相隔一年判断
				if(objNext.getValue().getFullYear()-objPre.getValue().getFullYear() == 1){
					var monthsNext = objNext.getValue().getMonth()-objPre.getValue().getMonth()+12;
					if(monthsNext>=0 && monthsNext<=3){
						if(monthsNext == 3){
							if(objNext.getValue().getDate()-objPre.getValue().getDate() > 0){
								return false;
							}else{
								return true;
							}
						}
						return true;
					}
				}
//				if((objNext.getValue().getTime()-objPre.getValue().getTime())<=1000*3600*24*90){
//					return true;
//				}
			}
		}
		return false;
	},
	//通过月份得到当月天数
	getCurrentDay:function(month){
		var currentMonth = month+1;
		if (currentMonth == 1 || currentMonth == 3 || currentMonth == 5
				|| currentMonth == 7 || currentMonth == 8 || currentMonth == 10
				|| currentMonth == 12){
			return 31;
		}else if(currentMonth == 2){
			return 28;
		}else{
			return 30;
		}
	},
	//潜客查询
	searchPotentialCustomerList:function(me){
		if(Ext.isEmpty(me)){
			me = this;
		}
		Ext.getCmp('searchPotentialCustResultGrid').store.loadPage(1);
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
 * 操作按钮面板
 */
Ext.define('PotentialManageButtonPanel',{
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
			width:400,
			items:[{
				text : i18n('i18n.PotentialCustManagerView.add'),
				hidden:!isPermission('/customer/PotentialABtn.action'),
				xtype : 'button',
				scope : me.parent,
				handler : me.parent.addNewPotential
			},  {
				text:i18n('i18n.PotentialCustManagerView.update'),
				hidden:!isPermission('/customer/PotentialUBtn.action'),
				xtype:'button',
				scope:me.parent.searchResult,
				handler:me.parent.searchResult.updatePotential
			},{
				text:i18n('i18n.PotentialCustManagerView.delete'),
				hidden:!isPermission('/customer/PotentialDBtn.action'),
				xtype:'button',
				scope:me.parent.searchResult,
				handler:me.parent.searchResult.delPotential
			},{
				text : i18n('i18n.PotentialCustManagerView.viewDetails'),
				hidden:!isPermission('/customer/PotentialVBtn.action'),
				xtype:'button',
				scope:me.parent.searchResult,
				handler:function(){
					var me = this;
					var grid = Ext.getCmp('searchPotentialCustResultGrid');
					selection=grid.getSelectionModel().getSelection();
					if (selection.length != 1) {
						MessageUtil.showMessage( i18n('i18n.PotentialCustManagerView.operation_message'));
						return;
					}
					var params = {};
					params.potentialCustId = selection[0].getId();
					var searchSuccessFn=function(result){
						var potentialCustModel = new PotentialCustModel(result.potentialCust);
						me.parent.searchResult.lookPotentialCustDetail(grid,potentialCustModel,'view');
					}
					var searchFail=function(result){
						if(!Ext.isEmpty(result.message)) {
							MessageUtil.showErrorMes(result.message);
						} 
					}
					me.potentialCust.serachPotentialById(params,searchSuccessFn,searchFail);
				}
			}, {
				text : i18n('i18n.PotentialCustManagerView.batchImport'),
				hidden:!isPermission('/customer/PotentialMassImportBtn.action'),
				xtype : 'button',
				scope : me.parent,
				handler : me.parent.batchImportPotential
			}, {
				text : i18n('i18n.PotentialCustManagerView.downLoadTemp'),
				hidden:!isPermission('/customer/PotentialDowloadBtn.action'),
				xtype : 'button',
				scope : me,
				hidden : true,
				handler : me.downloadTemplate 
			}, {
				text : i18n('i18n.PotentialCustManagerView.visitLog'),
				xtype : 'button',
				scope : me.parent,
				hidden : true,
				handler : me.parent.addNewVisit
			}]
		},{
			xtype:'middlebuttonpanel'
		},{
			xtype:'rightbuttonpanel',
			width:150,
			items:[{
				xtype : 'button',
				text : i18n('i18n.PotentialCustManagerView.search'),
				hidden:!isPermission('/customer/PotentialSBtn.action'),
				width : 70,
				scope:me.parent.searchCondition,
				handler : function() {
						if(!Ext.getCmp('searchPotentialCustConditionForm').getForm().isValid()){
							MessageUtil.showMessage( i18n('i18n.PotentialCustManagerView.message_warn'));
							return;
						}
						if(!Ext.getCmp('searchPotentialCustConditionForm').validateCondition()){
							MessageUtil.showMessage( i18n('i18n.PotentialCustManagerView.message_notNull'));
							return;
						}
						var checkDataInOneYearResult = DpUtil.checkDataInOneYear(
								Ext.getCmp('searchPotentialCustConditionForm').getForm().findField('searchStartTime'),
								Ext.getCmp('searchPotentialCustConditionForm').getForm().findField('searchEndTime'));
						if(!checkDataInOneYearResult.success){
							MessageUtil.showMessage( checkDataInOneYearResult.message);
							return;
						}
						me.parent.searchCondition.searchPotentialCustomerList();
				}}]
		}];
	},
	//下载模板
	downloadTemplate:function(){
		var me = this;
		me.parent.potentialCust.downloadTemplate(window);
	}
});
/**
 * 查询结果
 */
Ext.define('SearchResultGrid',{
	extend:'BasicPanel',
	potentialCust:null,//潜客data
	store:null,
	items:null,
	autoScroll:true,
	parent : null,
	initComponent:function(){
		var me = this;
		var potentialCustModel = new PotentialCustModel();
		me.store = me.potentialCust.initialPotentialCustStore(me.beforeLoadPotentialFn);
		me.items = [  {
			xtype : 'searchgridpanel',
			id:'searchPotentialCustResultGrid',
			store:me.store,
			autoScroll :true,
			selModel : Ext.create('Ext.selection.CheckboxModel'),
			columns : [ Ext.create('Ext.grid.RowNumberer',{width:45}),{
				header : 'id',
				dataIndex : 'id',
				hidden:true
			},{
				header : i18n('i18n.PotentialCustManagerView.coopIntention'),
				dataIndex : 'coopIntention',
				width:60,
				renderer  : function(value){
					return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.COOPERATION_INTENTION);
				}
			},{
				header : i18n('i18n.ScatterCustManagerView.businessType'),
				width:70,
				dataIndex : 'businessType',
				renderer : function(value){
					return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.BUSINESS_TYPE);
				}
			},{
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
				dataIndex : 'linkManPhone'
			}, {
				header:i18n('i18n.PotentialCustManagerView.position'),
				dataIndex:'post'
			},{
				header : i18n('i18n.PotentialCustManagerView.goodsPotential'),
				dataIndex : 'volumePotential',
				renderer : function(value){
					return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.CARGO_POTENTIAL);
				}
			}, {
				header : i18n('i18n.PotentialCustManagerView.bizStatus'),
				dataIndex : 'bussinesState',
				renderer : function(value){
					return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.BUSINESS_STATUS);
				}
			},{
				header:i18n('i18n.PotentialCustManagerView.industry'),
				dataIndex:'trade',
				renderer : function(value){
					return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.TRADE);
				}
			} ,{
				header:i18n('i18n.PotentialCustManagerView.custSource'),
				dataIndex:'custbase',
				renderer : function(value){
					return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.CUST_SOURCE);
				}
			},{
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
				hidden : true
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
			   itemdblclick : function(grid,record,viewStatus){
				   var params = {};
					params.potentialCustId = record.getId();
					var searchSuccessFn=function(result){
						var potentialCustModel = new PotentialCustModel(result.potentialCust);
						var win = Ext.create('PotentialCustDetailWindow', {
							potentialCustRecord : potentialCustModel,viewStatus:'view'
						});
						win.show();
					}
					var searchFail=function(result){
						if(!Ext.isEmpty(result.message)) {
							MessageUtil.showErrorMes(result.message);
						} 
					}
					me.potentialCust.serachPotentialById(params,searchSuccessFn,searchFail);
				   
					
			}
		   }
		} ];
		this.callParent();
	},
	//beforeLoad 方法
	beforeLoadPotentialFn:function(store, operation, eOpts){
		var searchPotentialForm = Ext.getCmp("searchPotentialCustConditionForm");
		if(searchPotentialForm!=null){
			searchPotentialForm.getForm().updateRecord(searchPotentialForm.record);
			//设置请求参数
			var potentialCustomerCondition = searchPotentialForm.record.data;
			var searchParams = {'potentialCustomerCondition.customerName':potentialCustomerCondition.customerName,
					'potentialCustomerCondition.contactName':potentialCustomerCondition.contactName,
					'potentialCustomerCondition.deptId':potentialCustomerCondition.deptId,
					'potentialCustomerCondition.contactPhone':potentialCustomerCondition.contactPhone,
					'potentialCustomerCondition.contactTel':potentialCustomerCondition.contactTel,
					'potentialCustomerCondition.industry':potentialCustomerCondition.industry,
					'potentialCustomerCondition.secondTrade':potentialCustomerCondition.secondTrade,
					'potentialCustomerCondition.coopIntention':potentialCustomerCondition.coopIntention,
					'potentialCustomerCondition.custSource':potentialCustomerCondition.custSource,
					'potentialCustomerCondition.bizStatus':potentialCustomerCondition.bizStatus,
					'potentialCustomerCondition.goodsPotential':potentialCustomerCondition.goodsPotential,
					'potentialCustomerCondition.searchStartTime':potentialCustomerCondition.searchStartTime,
	 				'potentialCustomerCondition.searchEndTime':potentialCustomerCondition.searchEndTime,
					'potentialCustomerCondition.position':potentialCustomerCondition.position,
					'potentialCustomerCondition.businessType':potentialCustomerCondition.businessType};
			Ext.apply(operation,{
				params : searchParams
				}
			);	
		}
	},
	//修改潜在客户
	updatePotential:function(){
		var me = this;
		var grid = Ext.getCmp('searchPotentialCustResultGrid');;
		selection=grid.getSelectionModel().getSelection();

		if (selection.length != 1) {
			MessageUtil.showMessage( i18n('i18n.PotentialCustManagerView.operation_message'));
			return;
		}else{
			var params = {};
			params.potentialCustId = selection[0].getId();
			var searchSuccessFn=function(result){
				var potentialCustModel = new PotentialCustModel(result.potentialCust);
				var editPotentialWin = Ext.create('PotentialCustEditWindow',{'viewStatus':'update',
					 'potentialCustRecord':potentialCustModel,
					 'potentialCustManagerView':me.parent});
					editPotentialWin.show();
					Ext.create('Depon.Lib.oDocHelper', {
					win:editPotentialWin,
					helpDoc:{				// 帮助实体：
					windowNum:'khgl-qkgl-edit-01'	// TODO:帮助文档的ID	belongModule(首字母缩写)+belongMenu（首字母缩写）+windowNum(弹窗编号)+首编时间戳
					,active:true			// 记录操作员操作，是否选择了”隐藏帮助“；
					},
					sHeight : '140px'
					});
			}
			var searchFail=function(result){
				if(!Ext.isEmpty(result.message)) {
					MessageUtil.showErrorMes(result.message);
				} 
			}
			
			me.potentialCust.serachPotentialById(params,searchSuccessFn,searchFail);
		}
		
	},
	//删除潜在客户
	delPotential:function(){
		var me = this;
		var grid = Ext.getCmp('searchPotentialCustResultGrid');
		selection=grid.getSelectionModel().getSelection();
		if (selection.length == 0) {
			MessageUtil.showMessage( i18n('i18n.PotentialCustManagerView.operation_messages'));
			return;
		}
		MessageUtil.showQuestionMes(i18n('i18n.PotentialCustManagerView.confirm_message'), function(e) {
			if (e == 'yes') {
				var potentialCustIds = new Array();
				for (var j = 0; j < selection.length; j++) {
					potentialCustIds.push(selection[j].get('id'))
				}
				//成功回调函数
				var delSuccess = function(response){
					var grid = Ext.getCmp('searchPotentialCustResultGrid');
					var form = Ext.getCmp('searchPotentialCustConditionForm');
		        	 MessageUtil.showInfoMes(i18n('i18n.PotentialCustManagerView.deleteSuccess'));
		        	 Ext.getCmp('searchPotentialCustResultGrid').store.loadPage(1);
				};
				//失败回调函数
				var delFail = function(response) {
					MessageUtil.showErrorMes( response.message);
				};
				var params = {};
				params.potentialCustIds = potentialCustIds;
				me.potentialCust.deletePotentialCustomer(params,delSuccess,delFail);
			}
		});
	},
	//查看详情details
	lookPotentialCustDetail:function(grid,record,viewStatus){
		var win = Ext.create('PotentialCustDetailWindow', {
				potentialCustRecord : record,viewStatus:viewStatus
			});
		win.show();
	}
});
/**
 * 批量导入界面 操作按钮面板
 */
Ext.define('MassImportButtonPanel',{
	extend:'PopButtonPanel',
	parent:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'popleftbuttonpanel',
//			width:300,
			items:[{
		    	xtype:'button',
		    	scope:me.parent,
		    	text:i18n('i18n.PotentialCustManagerView.validatDate'),
		    	handler:me.parent.validateCustData
		    },{
		    	xtype:'button',
		    	scope:me.parent,
		    	text:i18n('i18n.PotentialCustManagerView.removeInvalidData'),
		    	handler:me.parent.removeInvalidData
		    }]
		},{
			xtype:'middlebuttonpanel'
		}];
	}
});

/**
 * 批量导入界面 结果
 */
Ext.define('MassImportResultGrid',{
	extend:'PopupGridPanel',
	id:'massImportResultGridId',//增加id，方便直接点击保存是没有数据也提示 保存成功
	parent:null,
	initComponent:function(){
		var me = this;
		me.store=me.parent.potentialCust.getImportCustStore();
		me.columns = me.getColumns();
		this.callParent();
	},
	getColumns:function(){
		var me = this;
		return [{text:i18n('i18n.PotentialCustManagerView.importFailReason'),dataIndex:'message'},
		         {text:i18n('i18n.PotentialCustManagerView.custName'),dataIndex:'custName'},
		         {text:i18n('i18n.PotentialCustManagerView.industry'),dataIndex:'trade'},
		         {text:i18n('i18n.PotentialCustManagerView.custSource'),dataIndex:'custbase'},
		         {text:i18n('i18n.PotentialCustManagerView.contactName'),dataIndex:'linkManName'},
		         {text:i18n('i18n.PotentialCustManagerView.contactPhone'),dataIndex:'linkManMobile'},
		         {text:i18n('i18n.PotentialCustManagerView.contactTel'),dataIndex:'linkManPhone'},
		         {text:i18n('i18n.PotentialCustManagerView.position'),dataIndex:'post'},
		         {text:i18n('i18n.PotentialCustEditView.companyName'),dataIndex:'recentcoop'},
		         {text:i18n('i18n.PotentialCustManagerView.coopIntention'),dataIndex:'coopIntention'},
		         {text:i18n('i18n.PotentialCustManagerView.goodsPotential'),dataIndex:'volumePotential'},
		         {text:i18n('i18n.PotentialCustEditView.custNeed'),dataIndex:'custNeed'}];
	}
});
//文件导入的页面
Ext.define('ExcelImportUI',{
	extend:'PopWindow',
	title:i18n('i18n.PotentialCustManagerView.fileImport'),
	layout:'fit',
	buttonBar:null,//操作按钮
	massImportResultGrid:null,//导入结果
	width:700,
	height:400,
	layout:{
        type:'vbox',
        align:'stretch'
    },
	potentialCust:null,
	initComponent:function(){
		var me = this;
		me.buttonBar = Ext.create('MassImportButtonPanel',{'parent':me});
		me.massImportResultGrid = Ext.create('MassImportResultGrid',{'parent':me});
		me.fbar=me.getFbar();
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
				xtype:'basicformpanel',
				height:30,
				items:[{
				    xtype:'filefield',
				    fieldLabel:i18n('i18n.PotentialCustManagerView.filePosition'),
				    buttonText:i18n('i18n.PotentialCustManagerView.fileSelect'),
				    name:'fileName',
				    id:'fileId',
				    labelWidth:65,
					listeners : {
						scope:me,
						change : me.changeFile
					}
			    }]
			},{
				xtype:'basicpanel',
				height:36,
				items:[me.buttonBar]
			},{
				xtype:'basicpanel',
				flex:1,
				items:[me.massImportResultGrid]
		}];
	},
	getFbar:function(){
		var me = this;
		return [{
	    	xtype:'button',
	    	scope:me,
	    	text:i18n('i18n.PotentialCustEditView.save'),
	    	handler:me.saveImportData
	    }];
	},
	//切换文件
	changeFile:function(file, value){
		if(!DpUtil.isEmpty(value)){
			var me = this;
			//清空导入的列表
			me.potentialCust.getImportCustStore().removeAll();
			
			var pattern = /^.+?\.(xls|xlsx)$/;
//			var grid = file.up('basicformpanel').next('grid');
			var grid = Ext.getCmp('massImportResultGridId');
			//是否在IE中运行
			var isIE = true;
			var oXL;
			var oWB;
			try{
				oXL = new ActiveXObject("Excel.application");
				oWB = oXL.Workbooks.open(value);
			}catch(error){
				isIE = false;
				MessageUtil.showMessage( i18n('i18n.PotentialCustManagerView.mustBeIe'));
			}
			if(isIE){
					if (pattern.test(value)) {
						//得到激活的Sheet
						var oSheet = oWB.ActiveSheet;
						var rows=oSheet.usedrange.rows.count;//得到行数
						var columns=oSheet.usedrange.columns.count;//得到列数
						//批量导入的数据不超过100行
						if(rows > 100){
							MessageUtil.showMessage( i18n('i18n.PotentialCustManagerView.importFailMoreDate'));
						}else{
							//校验导入的Excel的表头与模板的表头是否相同，相同再进行导入
							var isSameHeader = me.validateExcelHead(grid,oSheet,columns);
							if(isSameHeader){
								var count = 0;
								for(var i = 2;i <= rows; i++){
									var importCustDataModel = this.createImportCustDataModelFromExcel(grid,oSheet,i,columns);
									importCustDataModel.set('rowNum',++count);
									me.potentialCust.getImportCustStore().insert(0,importCustDataModel);
								}
							}else{
								MessageUtil.showMessage( i18n('i18n.PotentialCustManagerView.importFailModelWrong'));
							}
						}
					}else {
						MessageUtil.showMessage( i18n('i18n.PotentialCustManagerView.selectExcel'));
					}
					//终止
					oXL.Quit();	
			}
		}
	},
	//创建导入的Model
	createImportCustDataModelFromExcel:function(grid,excel,row,columnLength){
		var importCustDataModel = new ImportCustDataModel();
		for(var j = 1;j <= columnLength; j++){
			this.setValueToModel(grid,excel,importCustDataModel,row,j);
		}
		return importCustDataModel;
	},
	//根据列号以及对应的头字段设置对应model的值
	setValueToModel:function(grid,excel,importCustDataModel,rowIndex,columnIndex){
		var me = this;
		var gridColumns = grid.columns;
		var gridText = gridColumns[columnIndex].text;
		
		var cellValue = excel.Cells(rowIndex,columnIndex).value;
		if(!DpUtil.isEmpty(cellValue)){
			if(gridText == i18n('i18n.PotentialCustManagerView.custName')){
				importCustDataModel.set('custName',cellValue);
			}else if(gridText == i18n('i18n.PotentialCustManagerView.industry')){
				importCustDataModel.set('trade',cellValue);
			}else if(gridText == i18n('i18n.PotentialCustManagerView.custSource')){
				importCustDataModel.set('custbase',cellValue);
			}else if(gridText == i18n('i18n.PotentialCustManagerView.contactName')){
				importCustDataModel.set('linkManName',cellValue);
			}else if(gridText == i18n('i18n.PotentialCustManagerView.contactPhone')){
				importCustDataModel.set('linkManMobile',cellValue.toString());
			}else if(gridText == i18n('i18n.PotentialCustManagerView.contactTel')){
				importCustDataModel.set('linkManPhone',cellValue.toString());
			}else if(gridText == i18n('i18n.PotentialCustManagerView.position')){
				importCustDataModel.set('post',cellValue);
			}else if(gridText == i18n('i18n.PotentialCustManagerView.companyName')){
				importCustDataModel.set('recentcoop',cellValue);
			}else if(gridText == i18n('i18n.PotentialCustManagerView.coopIntention')){
				importCustDataModel.set('coopIntention',cellValue);
			}else if(gridText == i18n('i18n.PotentialCustManagerView.goodsPotential')){
				importCustDataModel.set('volumePotential',cellValue);
			}else if(gridText == i18n('i18n.PotentialCustEditView.custNeed')){
				importCustDataModel.set('custNeed',cellValue);
			}
		}
	},
	//校验excel的表头
	validateExcelHead:function(grid,excel,columnSize){
		var gridColumns = grid.columns;
		if(gridColumns.length-1 > columnSize){
			return false;
		}else{
			for(var i = 1; i < gridColumns.length; i++){
				var columnName = gridColumns[i].text;
				var excelName = excel.Cells(1,i).value;
				if(columnName != excelName){
					return false;
				}
			}
			return true;
		}
	},
	//校验导入数据
	validateCustData:function(){
		var me = this;
		var importStore = me.potentialCust.getImportCustStore();
		var isValidatePast = true;
		for ( var i = 0; i < importStore.data.length; i++) {
			for ( var j = i+1; j < importStore.data.length; j++) {
				var mobile1 = importStore.getAt(i).get('linkManMobile');
				var phone1 = importStore.getAt(i).get('linkManPhone');
				var linkmanName1 = importStore.getAt(i).get('linkManName');
				var custName1 = importStore.getAt(i).get('custName');
				var custbase1 = importStore.getAt(i).get('custbase');
				var trade1 = importStore.getAt(i).get('trade');
				var post1 = importStore.getAt(i).get('post');
				var recentcoop1 = importStore.getAt(i).get('recentcoop');
				var coopIntention1 = importStore.getAt(i).get('coopIntention');
				var volumePotential1 = importStore.getAt(i).get('volumePotential');
				var custNeed1 = importStore.getAt(i).get('custNeed');
				
				var mobile2 = importStore.getAt(j).get('linkManMobile');
				var phone2 = importStore.getAt(j).get('linkManPhone');
				var linkmanName2 = importStore.getAt(j).get('linkManName');
				var custName2 = importStore.getAt(j).get('custName');
				var custbase2 = importStore.getAt(j).get('custbase');
				var trade2 = importStore.getAt(j).get('trade');
				var post2= importStore.getAt(j).get('post');
				var recentcoop2 = importStore.getAt(j).get('recentcoop');
				var coopIntention2 = importStore.getAt(j).get('coopIntention');
				var volumePotential2 = importStore.getAt(j).get('volumePotential');
				var custNeed2 = importStore.getAt(j).get('custNeed');
				
				if (mobile1==mobile2&&phone1==phone2&&linkmanName1==linkmanName2&&
						custName1==custName2&&custbase1==custbase2&&trade1==trade2&&
						post1==post2&&recentcoop1==recentcoop2&&coopIntention1==coopIntention2&&
						volumePotential1==volumePotential2&&custNeed1==custNeed2) {
					importStore.getAt(i).set('message',i18n('i18n.PotentialCustEditView.twoMessageSame',[j]));
					importStore.getAt(j).set('message',i18n('i18n.PotentialCustEditView.twoMessageSame',[i]));
					isValidatePast = false;
				}
			}
		}
		if (isValidatePast==false) {
			return isValidatePast;
		}
		importStore.each(function(record){
			var mobile = record.get('linkManMobile');
			var phone = record.get('linkManPhone');
			var linkmanName = record.get('linkManName');
			var custName = record.get('custName');
			if(DpUtil.isEmpty(linkmanName) || DpUtil.isEmpty(custName)){
				record.set('validatePast',false);
				record.set('message',i18n('i18n.PotentialCustManagerView.message_nameBothTrue'));
				isValidatePast = false;
			}else if(DpUtil.isEmpty(mobile) && DpUtil.isEmpty(phone)){
				record.set('validatePast',false);
				record.set('message',i18n('i18n.PotentialCustEditView.message_phoneBothTrue'));
				isValidatePast = false;
			}else if(!DpUtil.isEmpty(phone)&&/^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/.test(phone)){
				record.set('message',i18n('i18n.PotentialCustManagerView.phoneNotRegul'));
				isValidatePast = false;
				//校验手机号码
			}else if(!DpUtil.isEmpty(mobile) && (mobile.length != 11 || !(/^1[3|5|8][0-9]\d{4,8}$/.test(mobile)))){
				record.set('validatePast',false);
				record.set('message',i18n('i18n.PotentialCustEditView.message_phoneMustLength'));
				isValidatePast = false;
			}
		});
		return isValidatePast;
	},
	//移除无效数据
	removeInvalidData:function(){
		var me = this;
		var importStore = me.potentialCust.getImportCustStore();
		importStore.each(function(record){
			if(!record.get('validatePast')){
				importStore.remove(record);
			}
		});
	},
	//保存导入数据
	saveImportData:function(btn){
		var me = this;
		//请先选择文件
		var grid = Ext.getCmp('massImportResultGridId');
		if(grid.store.data.length<=0){
			MessageUtil.showMessage(i18n('i18n.PotentialCustManagerView.message_selectFile'));
			return;
		}
		//首先校验导入数据
		if(me.validateCustData()){
			var params = me.assembleBatchData();
			var successImportFn = function(result){
				//导入成功的客户数据移除，导入失败仍显示到界面上，并显示导入失败原因
				var importDataList = result.batchImportCustData;
				var importStore = me.potentialCust.getImportCustStore();
				for(var i = 0; i < importDataList.length; i++){
					var record = importStore.findRecord('rowNum',importDataList[i].rowNum);
					if(!importDataList[i].importSuccess){
						record.set('message',importDataList[i].message);
						record.set('importSuccess',importDataList[i].importSuccess);
					}else{
						importStore.remove(record);
					}
				}
				var showMsg = i18n('i18n.PotentialCustManagerView.importPotentialCustSuccess');
				if(importStore.getCount() > 0){
					showMsg = i18n('i18n.PotentialCustManagerView.importFailDateShow');
				}
				MessageUtil.showMessage(showMsg);
				//清空file文件框中的内容
				Ext.getCmp('fileId').reset();
			};
			var failImportFn = function(result){
				MessageUtil.showMessage(result.message);
				//清空file文件框中的内容
				Ext.getCmp('fileId').reset();
			};
			me.potentialCust.batchImportPotentialCust(params,successImportFn,failImportFn);
		}else{
			MessageUtil.showMessage(i18n('i18n.PotentialCustManagerView.importFailValidateFalil'));
		}
	},
	//组装提交后台的数据
	assembleBatchData:function(){
		var me = this;
		var params = {};
		var tempArray = new Array();
		me.potentialCust.getImportCustStore().each(function(record){
			var tempObject = {};
			tempObject.rowNum = record.get('rowNum');
			var tempRecord = record.copy();
			//转换数据字典
			me.changeRecordDictionary(tempRecord);
			tempObject.potentialCustomer = tempRecord.data;
			tempArray.push(tempObject);
		});
		params.batchImportCustData = tempArray;
		return params;
	},
	//转换数据字典
	changeRecordDictionary:function(record){
		var me = this;
		if(!DpUtil.isEmpty(record.get('trade'))){
			var dictionaryValue = DpUtil.changeDictionaryDescipToCode(me.potentialCust.getIndustryStore(),
					                                             record.get('trade'));
			record.set('trade',dictionaryValue);
		}
			
		if(!DpUtil.isEmpty(record.get('custbase'))){
			var dictionaryValue = DpUtil.changeDictionaryDescipToCode(me.potentialCust.getCustSourceStore(),
																 record.get('custbase'));
			record.set('custbase',dictionaryValue);
		}
		
	    if(!DpUtil.isEmpty(record.get('recentcoop'))){
			var dictionaryValue = DpUtil.changeDictionaryDescipToCode(me.potentialCust.getCoopertationCompanyStore(),
																 record.get('recentcoop'));
			record.set('recentcoop',dictionaryValue);
		}
	    
	    if(!DpUtil.isEmpty(record.get('coopIntention'))){
			var dictionaryValue = DpUtil.changeDictionaryDescipToCode(me.potentialCust.getCoopertationIntensionStore(),
																 record.get('coopIntention'));
			record.set('coopIntention',dictionaryValue);
		}
	    
	    if(!DpUtil.isEmpty(record.get('volumePotential'))){
			var dictionaryValue = DpUtil.changeDictionaryDescipToCode(me.potentialCust.getGoodsPotentialStore(),
																 record.get('volumePotential'));
			record.set('volumePotential',dictionaryValue);
		}
	}
});

/**
 * 上传附件弹出框
 */
Ext.define('UploadAttachmentWin',{
	extend:'PopWindow',
	title:i18n('i18n.PotentialCustManagerView.messageTip'),
	layout:{
		type:'vbox',
		align:'stretch'
	},
	width:530,
	height:150,
	potentialCust:null,//potentialCust data层
	initComponent:function(){
		var me = this;
		me.items = [
		{
			xtype:'titlepanel',
			defaultType:'displayfield',
			layout:'column',
			items:[{value: i18n('i18n.PotentialCustManagerView.pleaseInputDiverAttachment')},
			       {width:100},
			       {value:i18n('i18n.PotentialCustManagerView.pressHereDown')
						}]
		},
		{
			xtype:'basicformpanel',
			flex:1,
			layout:{
				type : 'hbox'
			},
			defaults : {
				margins : '0 5 0 0'
			},
			items:[{
				xtype:'filefield',
				name:'file',
				fieldLabel:'导入文件',
				labelWidth:60,
				buttonText:i18n('i18n.ChangeContactAffiliatedRelationView.view'),
				flex:3
			},{
				text:i18n('i18n.ContractEditView.contractId'),
				name:'sourceId',
				xtype:'hiddenfield'
			},{
				text:i18n('i18n.ChangeContactAffiliatedRelationView.sourceType'),
				name:'type',
				xtype:'hiddenfield',
				value:'EXCEL'
			}]
		}];
		me.fbar = me.getFbar();
		this.callParent();
	},
	getFbar:function(){
		var me = this;
		return [{
			text:i18n('i18n.ChangeContactAffiliatedRelationView.button_upload'),
			xtype:'button',
			scope:me,
			handler:me.uploadFile
		},{
			text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
			xtype:'button',
			handler:function(){
				me.close();
			}
		}];
	},
	//文件上传
	uploadFile:function(){
		var me = this;
		var form = me.down('form');
		form.submit({
                    url: 'importPentent.action',
                    waitMsg: i18n('i18n.PotentialCustManagerView.diverImportingPleaseWait'),
                    success: function(form, response) {
                    	var result = response.result;
                    	if(result.success){
                    		var message = result.message;
                    		var warnWin = null;
//                    		warnWin = Ext.create('PopWindow',{
//                    			title:i18n('i18n.PotentialCustManagerView.theEndOfImport'),
//                    			layout:{
//							        type:'vbox',
//							        align:'stretch'
//							    },
//                    			itmes:[{
//                    				xtype:'displayfield',
//                    				value:'<span style="color:red;"></span>'
//                    			},{
//                    				xtype:'PopupGridPanel',
//                    				store:me.potentialCust.getImportWarnStore,
//                    				columns:[ Ext.create('Ext.grid.RowNumberer'),{
//										header : '错误信息',
//										dataIndex : 'warnCode'
//									}]
//                    			}],
//                    			fbar:[{}]
//                    		});
                    		me.close();
                    		warnWin = Ext.create('Ext.window.Window',{
                    			title:i18n('i18n.PotentialCustManagerView.theEndOfImport'),
                    			width:305,
                    			height:150,
                    			cls:'warningwin',
                    			layout:'fit',
                    			items:[{
	                    				xtype : 'textareafield',
	                    				value:message,
										readOnly : true
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
								        { xtype: 'button', text:'确定',
							    		handler:function(){warnWin.close();}
								        }]
								}]
                    		});
                    		warnWin.show();
//                    		warnWin.down('grid').store.loadData([]);
//                        	Ext.Msg.alert(i18n('i18n.PotentialCustManagerView.theEndOfImport'), message);
                        	
						}else{
                       		MessageUtil.showMessage( result.message);
                       		me.close();
						}
                    },
                 failure:function(form, response){
                 	var result = response.result;
                 	if(!result.success){
                       	MessageUtil.showMessage( result.message);
					}
                 }
                });
	}
});
Ext.onReady(function(){
	//获得当前登录用户所在部门所在城市 成功方法
	var successFn = function(response){
		CurrentCity = response.currentCity;
	};
	//获得当前登录用户所在部门所在城市  失败方法
	var failFn = function(response){
		MessageUtil.showMessage(response.message);
	};
	PotentialCustSearchDataControl.acquireDeptCity({},successFn,failFn);
	Ext.create('Ext.Viewport',{
		layout : {
			type : 'fit'
		},
		items:[Ext.create('PotentialCustManagerPanel',{'potentialCust':PotentialCustSearchDataControl})]
	
		});
		
	Ext.create('Depon.Lib.oDocHelper', {
		helpDoc:{						// 帮助实体：
			windowNum:'khgl-qkgl-01'	// TODO:帮助文档的ID	belongModule(首字母缩写)+belongMenu（首字母缩写）+windowNum(弹窗编号)+首编时间戳
			,active:true				// 记录操作员操作，是否选择了”隐藏帮助“；
		}
	});
});