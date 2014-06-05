/**
*  潜客新增
*/
//Data层对象
var potentialCustControl = (CONFIG.get('TEST'))? Ext.create('PotentialCustDataTest'):Ext.create('PotentialCustData');
//var potentialCustControl = Ext.create('PotentialCustData');
//客户编辑界面
Ext.define('PotentialCustEditWindow',{
	extend:'PopWindow',
	title:i18n('i18n.PotentialCustEditView.potentialCustEditView'),
	fbar:null, 
	potentialCustBasicInfo:null, //客户基本信息
	potentialCustBusinessInfo:null, //客户业务信息
	custLabelInfoForm:null,//标签
	listeners:{
		close:function(){
			custLabels= [];
		}
	},
	items:null,
	width:680,
	height:535,
	layout:'fit',
	viewStatus:null,           //界面查看状态，新增add，修改update，查看view
	potentialCustRecord:null,  //修改或者查看潜在客户时传入的record
	potentialCustData:potentialCustControl,   //Data层接口
	potentialCustManagerView:null,
	initComponent:function(){
		var me = this;
		//设置fbar
		me.fbar = this.getFbar();
		me.potentialCustBasicInfo = Ext.create('PotentialCustBasicInfoForm',{'potentialCustData':me.potentialCustData,'parent':me});
		me.potentialCustBusinessInfo = Ext.create('PotentialCustBusinessInfoForm',{'potentialCustData':me.potentialCustData});

		me.custLabelInfo = Ext.create('CustLabelInfoForm',{'status':me.viewStatus,'custType':'PC_CUSTOMER','custData':me.potentialCustRecord});
		//设置items
		me.items = [{
			xtype:'basicformpanel',
			layout:'border',
			items:[{
				xtype:'basicformpanel',
				region:'north',
				layout:'fit',
				items:[me.potentialCustBasicInfo]
			},{
				xtype:'basicformpanel',
				region:'center',
				layout:'fit',
				height:160,
				items:[me.potentialCustBusinessInfo]
			},{
				xtype:'basicformpanel',
				region:'south',
				layout:'fit',
				height:158,
				items:[me.custLabelInfo]
			}]
		}];
//		this.on('beforeclose',this.beforeCloseEvent);
		initBasicData(me.viewStatus,'PC_CUSTOMER',me.potentialCustRecord,null,top.User.deptId);
		this.callParent();
		me.controlComponentByViewStatus();
	},
	beforeCloseEvent:function(){
		if(this.viewStatus == 'add'){
			//编辑界面关闭时重新加载父页面的数据
			this.potentialCustManagerView.reloadPotentialCust(this.potentialCustRecord,null);
		}else if(this.viewStatus == 'update'){
			//编辑界面关闭时重新加载父页面的数据
			this.potentialCustManagerView.reloadPotentialCust(null,this.potentialCustRecord);
		}
	},
	getFbar:function(){
		var me = this;
		return [{
			text:i18n('i18n.PotentialCustEditView.save'),
			type:'button',
			scope:me,
			handler:me.savePotentialCust
		},{
			text:i18n('i18n.PotentialCustEditView.cancel'),
			type:'button',
			scope:me,
			handler:me.cancelSavePotiental
		}];
	},
	//取消保存潜在客户
	cancelSavePotiental:function(){
		custLabels= [];
		this.close();
	},
	//通过查看状态设置控件
	controlComponentByViewStatus:function(){
		var basicForm = this.down('basicformpanel');
		
		if(this.potentialCustRecord == null){
			MessageUtil.showMessage(i18n('i18n.PotentialCustEditView.incomingCustInfo'));
		}else{
			basicForm.getForm().loadRecord(this.potentialCustRecord);
			//处理二级行业字段
			DpUtil.setSecondTradeValue(basicForm.getForm().findField('secondTrade'),this.potentialCustRecord);
			//查看状态，锁定所有的控件
			if(this.viewStatus == 'view'){					
				this.lockAllComponent();
			}else if('update' == this.viewStatus ){
				//修改信息时  客户名称 联系人名称、手机号码、电话号码 不可修改
				var form = basicForm.getForm();
//				form.findField('custName').setReadOnly(true);
//				form.findField('linkManName').setReadOnly(true);
//				form.findField('linkManMobile').setReadOnly(true);
//				form.findField('linkManPhone').setReadOnly(true);
			}
		}
	},
	//锁定所有控件
	lockAllComponent:function(){
		var basicForm = this.down('basicformpanel');
		var mainForm = basicForm.getForm();
		mainForm.getFields().each(function(field){
			field.setReadOnly(true);
		});
	},
	//所有控件解除锁定
	unLockAllComponent:function(){
		var basicForm = this.down('basicformpanel');
		var mainForm = basicForm.getForm();
		mainForm.getFields().each(function(field){
			field.setReadOnly(false);
		});
	},
	//保存潜客信息
	savePotentialCust:function(button){		
		//校验
		var me = this;
		var validateRs = me.validatePotentialCust();
		if(validateRs && !validateRs.success){
			MessageUtil.showMessage(validateRs.message);
		}else{
			//更新form表单上数据
			var basicForm = this.down('basicformpanel');
			var mainForm = basicForm.getForm();
			var record = me.potentialCustRecord.copy();
			if(this.viewStatus == 'add'){
				//保存新增潜在客户信息
				mainForm.updateRecord(me.potentialCustRecord);
			}else if(this.viewStatus == 'update'){
				//保存修改潜在客户信息
				mainForm.updateRecord(record);;
			}
			//保存成功回调函数
			var saveSuccessFn = function(result){
				MessageUtil.showInfoMes(i18n('i18n.PotentialCustEditView.saveSuccess'));
				if(me.viewStatus == 'add'){
					me.potentialCustRecord = Ext.create('PotentialCustModel',result.potentialCust);
					//编辑界面关闭时重新加载父页面的数据
					me.potentialCustManagerView.reloadPotentialCust(me.potentialCustRecord,null);
				}else if(me.viewStatus == 'update'){
					mainForm.updateRecord(me.potentialCustRecord);
					//重新设置ID
					me.potentialCustRecord.set('id',result.potentialCust.id);
					//编辑界面关闭时重新加载父页面的数据
					me.potentialCustManagerView.reloadPotentialCust(null,me.potentialCustRecord);
				}
				me.close();
			};
			//保存失败回调函数
			var saveFailFn = function(result){
					MessageUtil.showErrorMes(result.message);
					button.setDisabled(false);
			};
			var params = {};
			if(this.viewStatus == 'add'){
				me.potentialCustRecord.data.custLabels = custLabels;
				params.potentialCust = me.potentialCustRecord.data;
				//为空的时候。赋值一个空的数组
				if(me.potentialCustRecord.data.custLabels == '') {
					me.potentialCustRecord.data.custLabels = new Array();
				}
				//保存新增潜在客户信息
				me.potentialCustData.savePotentialCust(params,saveSuccessFn,saveFailFn);
			}else if(this.viewStatus == 'update'){
				record.data.custLabels = custLabels;
				params.potentialCust = record.data;
				//保存修改潜在客户信息
				me.potentialCustData.updatePotentialCust(params,saveSuccessFn,saveFailFn);
			}
			button.setDisabled(true);
		}
	},
	//校验潜客信息
	validatePotentialCust:function(){
		var me = this;
		
		//如果客户名称不为空，联系人名称为空，则联系人名称与客户名称相同
		var potentialCustName = me.potentialCustBasicInfo.down('fieldset').down('textfield[fieldLabel=客户名称]');
		var linkmanName = me.potentialCustBasicInfo.down('fieldset').down('textfield[fieldLabel=联系人姓名]');
		if(!(DpUtil.isEmpty(potentialCustName.getValue()) && DpUtil.isEmpty(linkmanName.getValue()))){
			if(DpUtil.isEmpty(potentialCustName.getValue())){
				potentialCustName.setValue(linkmanName.getValue());
			}else if(DpUtil.isEmpty(linkmanName.getValue())){
				linkmanName.setValue(potentialCustName.getValue());
			}
		}
		
		if(me.potentialCustBasicInfo.getForm().isValid()
				&&me.potentialCustBusinessInfo.getForm().isValid()){
			var linkmanMobile = me.potentialCustBasicInfo.getForm().findField('linkManMobile');
			var linkmanPhone = me.potentialCustBasicInfo.getForm().findField('linkManPhone');
			//联系人手机、联系人电话至少录入一个
			if(linkmanMobile && linkmanPhone &&
			   !(DpUtil.isEmpty(linkmanMobile.getValue()) && DpUtil.isEmpty(linkmanPhone.getValue()))){
			   
				//判断地址长度小于100、走货情况和客户需求长度小于200
				var address = me.potentialCustBasicInfo.down('fieldset').down('textfield[fieldLabel=地址]');
				var transferCondition = me.potentialCustBusinessInfo.down('fieldset')
																	.down('textfield[fieldLabel=走货情况(最近合作物流公司)]');
				var custNeeds = me.potentialCustBusinessInfo.down('fieldset')
															.down('textfield[fieldLabel=客户需求]');
				if(address && !DpUtil.isEmpty(address.getValue()) && address.getValue().length > 100){
					return {success:false,message:i18n('i18n.PotentialCustEditView.message_waiting')};
				}
				else if(transferCondition && !DpUtil.isEmpty(transferCondition.getValue()) && transferCondition.getValue().length > 200){
					return {success:false,message:i18n('i18n.PotentialCustEditView.message_goodsLength')};
				}
				else if(custNeeds && !DpUtil.isEmpty(custNeeds.getValue()) && custNeeds.getValue().length > 200){
					return {success:false,message:i18n('i18n.PotentialCustEditView.message_custNeedLength')};
				}
				else{
					return {success:true,message:''};
				}
			}else{
				return {success:false,message:i18n('i18n.PotentialCustEditView.message_inputLimited')};
			}
		}else{
			if (Ext.isEmpty(me.potentialCustBasicInfo.getForm().findField('custbase').getValue())) {
				return {success:false,message:'客户来源不能为空'};
			}
			if (Ext.isEmpty(me.potentialCustBusinessInfo.getForm().findField('businessType').getValue())) {
				return {success:false,message:'请选择业务类别'};
			}
			if(!me.potentialCustBasicInfo.getForm().findField('address').isValid()){
				return {success:false,message:'地址长度不能超过100个字符！'};
				}
			return {success:false,message:'请注意检查联系人姓名、手机号码信息是否完整！'};
		}
	}
});

//潜客基本信息
Ext.define('PotentialCustBasicInfoForm',{
	extend:'BasicFormPanel',
	defaultType:'dptextfield',
	layout:'fit',
	potentialCustData:null,
	viewStatus:null,
	items:null,
	parent:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	//设置items
	getItems:function(){
		var me = this;
		var items = [{
			xtype:'fieldset',
			title:i18n('i18n.PotentialCustEditView.potentialCustInfo'),
			defaults:{
				labelWidth:70,
				width:200,
				enableKeyEvents:true,
				listeners:{
					scope:me,
					keypress:me.enterKeyEvent,
					change:me.changeEvent
				}
			},
			layout:{
	         	type:'table',    
	         	columns : 6         
	        },
	        defaultType:'dptextfield',
			items:[{
						fieldLabel:i18n('i18n.PotentialCustManagerView.customerName'),
						allowBlank:false,
						blankText:i18n('i18n.PotentialCustEditView.message_custName'),
						listeners:{
							scope:me,
							keypress:me.enterKeyEvent,
							blur:me.potentialCustNameBlurEvent
						},
						maxLength : 20,	
						name:'custName'
					},{
						//xtype:'displayfield',
						html:'<span style="color:red">*</span>',
						width:5
					},{
						fieldLabel:i18n('i18n.PotentialCustManagerView.contactPhone'),
						listeners:{
							scope:me,
							keypress:me.mobileEnterEvent,
							blur:me.validateMobile,
							change:me.mobileChangeEvent
						},
						regex:DpUtil.linkWayLimit('M'),
						name:'linkManMobile'
//					},{
//						//xtype:'displayfield',
//						html:'<span style="color:red">*</span>',
//						width:5
					},{
						fieldLabel:i18n('i18n.PotentialCustManagerView.contactTel'),
						name:'linkManPhone',
						colspan:2,
						regex : DpUtil.linkWayLimit('T'),
						emptyText:i18n('i18n.PotentialCustEditView.likeTelModel')
					},{
						//xtype:'displayfield',
						html:'<span style="color:red">*</span>',
						width:5
					},{
						fieldLabel:i18n('i18n.PotentialCustManagerView.contactName'),
						allowBlank:false,
						blankText:i18n('i18n.PotentialCustEditView.message_conName'),
						listeners:{
							scope:me,
							keypress:me.enterKeyEvent,
							blur:me.potentialLinkmanNameBlurEvent
						},
						maxLength : 20,	
						name:'linkManName'
					},{
						//xtype:'displayfield',
						html:'<span style="color:red">*</span>',
						width:5
					},{
//						colspan:2,
						fieldLabel:i18n('i18n.PotentialCustManagerView.custSource'),
						xtype:'dpcombo',
		                forceSelection:true,
		                allowBlank:false,
						store:me.potentialCustData.getCustSourceStore(),
						queryMode:'local',
						displayField:'codeDesc',
						valueField:'code',
						name:'custbase',
						listeners:{
							scope : me,
							change : function(field,newValue){
								var me = this;
								if(Ext.isEmpty(newValue)){
									field.setValue(null);
								}
							}
						}
					},{
						colspan:3,
//						labelWidth:65,
						fieldLabel:i18n('i18n.PotentialCustManagerView.position'),
						maxLength : 20,	
//						width:195,
						name:'post'
					},{
						colspan:2,
						fieldLabel:i18n('i18n.PotentialCustManagerView.bizStatus'),
						xtype:'dpcombo',
						cls:'readonly',
						store:me.potentialCustData.getBusinessOpportunityStore(),
						queryMode:'local',
		                forceSelection:true,
						displayField:'codeDesc',
						valueField:'code',
						readOnly:true,
						name:'bussinesState'
					},{
						colspan:4,
						xtype:'customertrade',
						width:402,
						height:25,
						border:0,
						trade_labelWidth:70,
						trade_width:199,
						trade_name:'trade',
						trade_fieldname:i18n('i18n.PotentialCustManagerView.industry'),
						second_trade_labelWidth:70,
						second_trade_width:202,
						second_trade_name:'secondTrade',
						second_trade_fieldname:i18n('i18n.secondTrade.secondTrade'),
//						second_trade_margin:'-2 0 0 0',
						listeners:{
							scope : me,
							change : function(field,newValue){
								var me = this;
								if(Ext.isEmpty(newValue)){
									field.setValue(null);
								}
							}
						}
					},{
						colspan:2,
						fieldLabel:i18n('i18n.PotentialCustEditView.city'),
						name:'cityName',
						value:CurrentCity.name,
						readOnly:true
					}
//					me.getCity()
//					{
//						fieldLabel:i18n('i18n.PotentialCustEditView.city'),
//						xtype:'dpcombo',
//						store:me.potentialCustData.getCityStore(),
//						queryMode:'local',
//		                forceSelection:true,
//		            	listConfig: {
//		                 	minWidth :200    //下拉框显示宽度
//		            	},
//		            	pageSize:20,
//						displayField:'cityName',
//						valueField:'id',
//						readOnly:true,
//						name:'city'
//					}
					,{
						fieldLabel:i18n('i18n.PotentialCustEditView.address'),
						colspan:4,
						width:400,
						maxLength :100,	
						maxLengthText :i18n('i18n.ManualRewardIntegralEditView.m_maxLength',100),	
						name:'address'
					},{
						colspan:2,
						//城市id
						xtype:'hiddenfield',
						name:'city'
					}]
		}];
		return items;
	},
	getCity:function(){
		var me = this;
		var returnValue = Ext.create('Ext.form.ComboBox');
		var operateType = 'add';

		if('view'==me.parent.viewStatus){
			operateType = 'VIEW';
		}else if('update'==me.parent.viewStatus){
			operateType = 'UPDATE';
		}else if('add'==me.parent.viewStatus){
			operateType = 'ADD';
		}
		returnValue = new AreaAddressCombox({
			'id':'city',
			'fieldLabel':i18n('i18n.PotentialCustEditView.city'),
			'labelWidth':70,
			'width':200,
			'minWidth' :200,
			'name':'city',
			'operateType':operateType,
			'selectedValue':me.parent.potentialCustRecord.get('city'),
			'tabPanel':['provincePanel','cityPanel']});
		
		return returnValue;
	},
	//change事件
	changeEvent : function(field, newValue) {
		var me = this;
		if (Ext.isEmpty(newValue) && 'dpcombo' == field.getXType()) {
			field.setValue(null);
		}
	},
	//手机号码chang事件
	mobileChangeEvent:function(text,newValue){
		if(newValue.length>11){
			text.setValue(newValue.substring(0,11));
		}
	},
	//Enter键按下时焦点自动移到下个控件
	enterKeyEvent:function(field,event){
		if(event.getKey() == Ext.EventObject.ENTER){
			if(field.fieldLabel == i18n('i18n.PotentialCustManagerView.position')){
				field.next('textfield[fieldLabel=地址]').focus();
			}else if(field.fieldLabel == i18n('i18n.PotentialCustEditView.address')){
				//填完地址，按enter键之后”最近合作物流公司“获得焦点
				var businessFieldset = this.up('basicpanel').next().down('basicformpanel').down('fieldset');
				if(businessFieldset){
					var companyName = businessFieldset.child('textfield[fieldLabel=最近合作物流公司]');
					if(companyName){
						companyName.focus();
					}
				}
			}else{
				field.next().focus();
			}
		}
	},
	//客户名称发生改变,如果联系人名称为空，默认与客户名称相同
	potentialCustNameBlurEvent:function(potentialCustNameText){
		if(!DpUtil.isEmpty(potentialCustNameText.getValue())){
			var linkmanText = potentialCustNameText.next('textfield[fieldLabel=联系人姓名]');
			if(DpUtil.isEmpty(linkmanText.getValue())){
				linkmanText.setValue(potentialCustNameText.getValue());
			}
		}
	},
	//联系人名称发生改变,如果客户名称为空，默认与联系人名称相同
	potentialLinkmanNameBlurEvent:function(potentialLinkmanNameText){
		if(!DpUtil.isEmpty(potentialLinkmanNameText.getValue())){
			var potentialCustNameText = potentialLinkmanNameText.prev('textfield[fieldLabel=客户名称]');
			if(DpUtil.isEmpty(potentialCustNameText.getValue())){
				potentialCustNameText.setValue(potentialLinkmanNameText.getValue());
			}
		}
	},
	//校验手机号码
	validateMobile:function(mobileText){
		var mobile = mobileText.getValue();
		//如果手机号码不为空
		if(!DpUtil.isEmpty(mobile)){
			if(mobile.length != 11 && mobile.length != 8){
				MessageUtil.showMessage(i18n('i18n.PotentialCustEditView.message_phoneMustLength'));
				mobileText.focus();
			}else if(!(/(^1\d{10}$)|(^\d{8}$)/.test(mobile))){
				MessageUtil.showMessage(i18n('i18n.PotentialCustEditView.message_phoneMustNo'));
				mobileText.focus();
			}else{
				mobileText.next().focus();
			}
		}else{
			mobileText.next().focus();
		}
	},
	//手机号码的enter事件
	mobileEnterEvent:function(field,event){
		if(event.getKey() == Ext.EventObject.ENTER){
			//直接调用校验手机号码的方法
			this.validateMobile(field);
		}
	}
	
});

//客户业务信息
Ext.define('PotentialCustBusinessInfoForm',{
	extend:'BasicFormPanel',
	layout:'fit',
	potentialCustData:null,
	items:null,
    initComponent:function(){
	   this.items = this.getItems();
	   this.callParent();
    },
    //设置items
    getItems:function(){
    	var me = this;
	   return [{
			xtype:'fieldset',
			title:i18n('i18n.PotentialCustEditView.recentGoods'),
			defaults:{
				labelWidth:70,
				width:200,
				enableKeyEvents:true,
				listeners:{
					scope:me,
					keypress:me.enterKeyEvent,
					change:me.changeEvent
				}
			},
			layout:{
	         	type:'table',    
	         	columns : 3         
	        },
	        items:[{
				fieldLabel:i18n('i18n.PotentialCustEditView.companyName'),
				xtype:'dpcombo',
				store:me.potentialCustData.getCoopertationCompanyStore(),
				queryMode:'local',
                forceSelection:true,
				displayField:'codeDesc',
				valueField:'code',
				name:'recentcoop',
				listeners:{
					scope : me,
					change : function(field,newValue){
						var me = this;
						if(Ext.isEmpty(newValue)){
							field.setValue(null);
						}
					}
				}
			},{
				fieldLabel:i18n('i18n.PotentialCustManagerView.coopIntention'),
				xtype:'dpcombo',
				store:me.potentialCustData.getCoopertationIntensionStore(),
				queryMode:'local',
                forceSelection:true,
				displayField:'codeDesc',
				valueField:'code',
				name:'coopIntention',
				listeners:{
					scope : me,
					change : function(field,newValue){
						var me = this;
						if(Ext.isEmpty(newValue)){
							field.setValue(null);
						}
					}
				}
			},{
				fieldLabel:i18n('i18n.PotentialCustManagerView.goodsPotential'),
				xtype:'dpcombo',
				store:me.potentialCustData.getGoodsPotentialStore(),
				queryMode:'local',
                forceSelection:true,
				displayField:'codeDesc',
				valueField:'code',
				name:'volumePotential',
				listeners:{
					scope : me,
					change : function(field,newValue){
						var me = this;
						if(Ext.isEmpty(newValue)){
							field.setValue(null);
						}
					}
				}
			},
				{
				fieldLabel:i18n('i18n.ScatterCustManagerView.businessType')+DpUtil.getSpecialStr('redSpan'),
				name : 'businessType',
				colspan:3,
				width:200,
				xtype:'dpcombobox',
				allowBlank:false,
				blankText:i18n('i18n.ScatterCustEditView.businessTypeNull'),
				queryMode:'local',
				forceSelection:true,
				editable:false,
				displayField:'codeDesc',
				valueField:'code',
				store:me.potentialCustData.getBusinessTypeStore()
			},
		    {
				fieldLabel:i18n('i18n.PotentialCustEditView.goodsTrendCondition'),
				xtype:'textareafield',
				maxLength :200,	
				maxLengthText :i18n('i18n.ManualRewardIntegralEditView.m_maxLength',200),
				name:'recentGoods'
			},{
				fieldLabel:i18n('i18n.PotentialCustEditView.custNeed'),
				xtype:'textareafield',
				colspan:2,
				width:400,
				maxLength :200,	
				maxLengthText :i18n('i18n.ManualRewardIntegralEditView.m_maxLength',200),
				name:'custNeed'
			}]
		  }
		];
    },
	//change事件
	changeEvent : function(field, newValue) {
		var me = this;
		if (Ext.isEmpty(newValue) && 'dpcombo' == field.getXType()) {
			field.setValue(null);
		}
	},
    enterKeyEvent:function(field,event){
    	if(event.getKey() == Ext.EventObject.ENTER){
    		if(field.next()){
    			field.next().focus();
    		}
    	}
    }
});


