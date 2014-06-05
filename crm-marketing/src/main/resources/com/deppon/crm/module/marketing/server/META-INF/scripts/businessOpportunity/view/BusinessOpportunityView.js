/**.
 * <p>
 * 商机信息查看相关界面元素<br/>
 * <p>
 */

/**
  * .
  * <p>
  * 商机查看window<br/>
  * </p>
  * 
  * @returns CRM.BO.BusinessOpportunityView.Window 商机查看window 的EXT对象
  * @author 张斌
  * @时间 2014-03-11
  */
Ext.define('CRM.BO.BusinessOpportunityView.Window',{
	extend:'PopWindow',
	width:790,
	height:500,
	title:i18n('i18n.businessOpportunity.boViewWin'),//商机信息查看界面
	parent:null,//他的上级元素
	businessOpportunity:null,//差u型您出的JSON数据
	closeAction:'hide',
	businessOpportunityViewTab:null,//新增商家的FORM
	layout:{
        type:'vbox',
        align:'stretch'
    },
    listeners:{
    	beforehide:function(win){
    		win.resetBo();
    	},
    	beforeshow:function(win){
    		win.loadBo();
    		win.loadLinkMan();
    		win.loadScheduleSearchCondition();
    		win.loadMarketHistoryResult();
    	},
    },
	items:null,
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		me.fbar = me.getFbar();
		this.callParent();
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.memberView.cancel'),//取消
			handler:function(){
				me.hide();

			}
		}];
	},
	getItems:function(){
		var me = this;
		me.businessOpportunityViewTab = Ext.create('CRM.BO.BusinessOpportunityView.Tab');
		return [me.businessOpportunityViewTab];
	},
	//重置商机信息
	resetBo:function(){
		var me = this;
		me.businessOpportunityViewTab.setActiveTab(0);
		me.businessOpportunityViewTab.boInfoPanel.getForm().reset();//重置商机信息
		me.businessOpportunityViewTab.linkManInfo.linkManGrid.getStore().removeAll();//清除联系人信息
		me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().reset();
		me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createStartTime').setValue(null);
		me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createStartTime').setMinValue(null);
		me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createStartTime').setMaxValue(null);
		me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createEndTime').setValue(null);
		me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createEndTime').setMinValue(null);
		me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createEndTime').setMaxValue(null);
        //清空store中的数据 auth ：李春雨
        me.businessOpportunityViewTab.schedulePanel.scheduleGrid.getStore().removeAll();
		me.businessOpportunityViewTab.marketHistoryResultPanel.marketHistoryResultPanelOfReturnVisit.customerDemandResultGrid.getStore().removeAll();//客户需求
		me.businessOpportunityViewTab.marketHistoryResultPanel.marketHistoryResultPanelOfReturnVisit.sendGoodsPontentialResultGrid.getStore().removeAll();//走货潜力
		me.businessOpportunityViewTab.marketHistoryResultPanel.questionnaireResultGridForMarketRecord.getStore().removeAll();//问卷
	},
	//加载商机信息
	loadBo:function(){
		var me = this;
		var form = me.businessOpportunityViewTab.boInfoPanel.getForm();
		var businessOpportunityModel = new CRM.BO.BusinessOpportunity(me.businessOpportunity);//构建上级model
		businessOpportunityModel.set('boStep'
				,DButil.rendererDictionary(businessOpportunityModel.get('boStep'),DataDictionary.BUSINESS_OPPORTUNITY_STEP));//商机阶段
		businessOpportunityModel.set('boStatus'
				,DButil.rendererDictionary(businessOpportunityModel.get('boStatus'),DataDictionary.BUSINESS_OPPORTUNITY_STATUS));//商机状态
		var isBOConfirm = businessOpportunityModel.get('isBOConfirm')//确认商机
		if(!Ext.isEmpty(isBOConfirm)){
			if(isBOConfirm==1){
				businessOpportunityModel.set('isBOConfirm',i18n('i18n.ChangeContactAffiliatedRelationView.yes'));
			}else if(isBOConfirm==0){
				businessOpportunityModel.set('isBOConfirm',i18n('i18n.ChangeContactAffiliatedRelationView.no'));
			}else{
				businessOpportunityModel.set('isBOConfirm','');
			}
		};
		var isBidProject = businessOpportunityModel.get('isBidProject')//是否招投标
		if(!Ext.isEmpty(isBidProject)){
			if(isBidProject==1){
				businessOpportunityModel.set('isBidProject',i18n('i18n.ChangeContactAffiliatedRelationView.yes'));
			}else if(isBidProject==0){
				businessOpportunityModel.set('isBidProject',i18n('i18n.ChangeContactAffiliatedRelationView.no'));
			}else{
				businessOpportunityModel.set('isBidProject','');
			}
		};
		form.loadRecord(businessOpportunityModel);//form中加载数据
		form.findField('customer.custName').setValue(businessOpportunityModel.get('customer').custName);//客户名称
		form.findField('customer.mainLinkmanName').setValue(businessOpportunityModel.get('customer').mainLinkmanName);//联系人名称
		form.findField('customer.mainLinkmanMobile').setValue(businessOpportunityModel.get('customer').mainLinkmanMobile);//联系人手机
		form.findField('customer.custNumber').setValue(businessOpportunityModel.get('customer').custNumber);//客户编码
		form.findField('customer.custSecondType').setValue(
				DButil.rendererDictionary(businessOpportunityModel.get('customer').custSecondType,DataDictionary.SECOND_TRADE));//二级行业
		form.findField('customer.custFirstType').setValue(
				DButil.rendererDictionary(businessOpportunityModel.get('customer').custFirstType,DataDictionary.TRADE));//一级行业
		form.findField('customer.mainLinkmanPhone').setValue(businessOpportunityModel.get('customer').mainLinkmanPhone);//联系人电话
		form.findField('operator.empName').setValue(businessOpportunityModel.get('operator').empName);//商机责任人
	},
	//加载客户联系人信息
	loadLinkMan:function(){
		var me = this;
		var param = {'searchCustCondition':{'memberId':me.businessOpportunity.customer.custId}};//根据客户ID查询所有联系人信息
		var successFn = function(json){
			if(!Ext.isEmpty(json.member)){
				me.businessOpportunityViewTab.linkManInfo.linkManGrid.getStore().add(json.member.contactList);//加载联系人
			}
		};
		var failureFn = function(json){
		    MessageUtil.showErrorMes(json.message);
		};
		businessOpportunityData.searchLinkMan(param,successFn,failureFn);
	},
	//加载商机日程查询数据
	loadScheduleSearchCondition:function(){
		var me = this;
		var minValue = DpUtil.changeLongToDate(me.businessOpportunity.createTime);
		var maxValue = new Date(minValue.getFullYear(),minValue.getMonth()+6,minValue.getDate());
		me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createStartTime').setValue(minValue);
		me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createStartTime').setMinValue(minValue);
		me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createStartTime').setMaxValue(maxValue);
		me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createEndTime').setValue(maxValue);
		me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createEndTime').setMinValue(minValue);
		me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createEndTime').setMaxValue(maxValue);
	},
	//加载营销记录查询数据
	loadMarketHistoryResult:function(){
		var me = this;
		me.businessOpportunityViewTab.marketHistoryResultPanel.marketHistoryResultPanelOfReturnVisit.customerDemandResultGrid.getStore().loadPage(1);
		me.businessOpportunityViewTab.marketHistoryResultPanel.marketHistoryResultPanelOfReturnVisit.sendGoodsPontentialResultGrid.getStore().loadPage(1);
		me.businessOpportunityViewTab.marketHistoryResultPanel.questionnaireResultGridForMarketRecord.getStore().loadPage(1);//问卷
	}
});
/**
 * .
 * <p>
 * 商机查看tab<br/>
 * </p>
 * 
 * @returns CRM.BO.BusinessOpportunityView.Tab 商机查看tab 的EXT对象
 * @author 张斌
 * @时间 2014-03-11
 */
Ext.define('CRM.BO.BusinessOpportunityView.Tab', {
	extend: 'NormalTabPanel',
	width:790,
	height:415,
	autoScroll:true,
    activeTab: 0,
    boInfoPanel:null,//商机信息Panel
    schedulePanel:null,//商机日程信息
    linkManInfo:null,//联系人信息
    marketHistoryResultPanel:null,//回访历史记录
    //获取相关panle
	getItems:function(){
		var me = this;
		me.boInfoPanel = Ext.create('CRM.BO.BusinessOpportunityView.BoInfoPanel');
		me.schedulePanel = Ext.create('CRM.BO.BusinessOpportunityView.SchedulePanel');
		me.linkManInfo = Ext.create('CRM.BO.BusinessOpportunityCommon.LinkManPanel',{name : 'linkManPanel'});
		me.marketHistoryResultPanel = Ext.create('CRM.BO.BusinessOpportunityCommon.MarketHistoryResultPanel');
		return [me.boInfoPanel,me.schedulePanel,me.marketHistoryResultPanel,me.linkManInfo];
	},
    //修改高度，使滚动条可以每次出现
    tabchange:function(tabPanel,newCard,oldCard){
            if(newCard.name == 'linkManPanel'){
                newCard.setHeight(380);
            }
    },
    initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
	}
});
/**.
 * <p>
 * 商机信息FORM</br>
 * </p>
 * @returns CRM.BO.BusinessOpportunityView.BoInfoPanel 商机信息FORM
 * @author  张斌
 * @时间    2014-03-12
 */
Ext.define('CRM.BO.BusinessOpportunityView.BoInfoPanel', {
	extend:'BasicFormPanel',
	autoScroll : true,
	border:false,
    layout:{
        type:'vbox',
        align:'stretch'
    },
	title:i18n('i18n.businessOpportunity.boInfo'),
	basicInfoForm:null,//商机基础信息form
	boYeInfoForm:null,//上级业务信息form
    /**.
    * <p>
    * 获取ITEMS属性
    * </br>
    * </p>
    * @author  张斌
    * @时间    2014-03-13
    */
    getItems:function(){
    	var me = this;
    	return  [me.basicInfoForm,me.boYeInfoForm];
    },
    initComponent:function(){
    	var me = this;
    	me.basicInfoForm = Ext.create('CRM.BO.BusinessOpportunityView.BasicInfoForm');
    	me.boYeInfoForm = Ext.create('CRM.BO.BusinessOpportunityView.BoYeInfoForm');
    	me.items = me.getItems();
    	this.callParent();
    },

});
/**
 * .
 * <p>
 * 商机基础信息FORM</br>
 * </p>
 * 
 * @returns CRM.BO.BusinessOpportunityView.BasicInfoForm 商机基础信息FORM
 * @author 张斌
 * @时间 2014-03-12
 */
Ext.define('CRM.BO.BusinessOpportunityView.BasicInfoForm', {
		extend:'TitleFormPanel',
		height:200,
		items:null,
		/**
		 * .
		 * <p>
		 * 获取ITEMS属性 </br>
		 * </p>
		 * 
		 * @author 张斌
		 * @时间 2012-03-12
		 */
		getItems:function(){
			var me = this;
			return  [{xtype: 'basicfiledset',
				    title: i18n('i18n.businessOpportunity.boBasicInfo'),
				    defaults:{
				    	labelSeparator:'',
						labelWidth:100,
						width:230,
				        xtype: 'textfield',
				        readOnly:true
				    },
				    layout:{
				    	type:'table',
				    	columns:3
				    },
				    items:[{
				    	name: 'boName',
				    	fieldLabel: i18n('i18n.businessOpportunity.boName')//商机名称
				    },{
				    	name: 'boNumber',
				    	fieldLabel: i18n('i18n.businessOpportunity.boNumber')//商机编码
				    },{
				    	name: 'customer.custName',
				    	fieldLabel: i18n('i18n.businessOpportunity.customerName')//客户名称
				    },{
				    	name: 'customer.mainLinkmanName',
				    	fieldLabel: i18n('i18n.businessOpportunity.contactName')//联系人名称
				    },{
				    	name: 'customer.mainLinkmanMobile',
				    	fieldLabel: i18n('i18n.businessOpportunity.contactMobile')//联系人手机
				    },{
				    	name: 'customer.custNumber',
				    	fieldLabel: i18n('i18n.businessOpportunity.customerNum')//客户编码
				    },{
		            	 xtype:'container',
		            	 width:230,
		            	 layout:{
		         			type:'hbox'
		         		},
		            	 items:[{
		            		    name: 'expectDeliveryAmount',
		            		    width:215,
		            		    labelSeparator:'',
		            		    xtype: 'textfield',
		            		    readOnly:true,
						    	fieldLabel: i18n('i18n.businessOpportunity.expectDeliveryAmount')//预计发货金额
				             }, {
				                 xtype: 'label',
				                 text: i18n('i18n.visualMarket.yuan'),
				                 width:10,
				                 margins: '0 0 0 2'
				             }]
		             },{
				    	name: 'operator.empName',
				    	fieldLabel: i18n('i18n.businessOpportunity.operator')//商机责任人
				    },{
				    	name: 'customer.mainLinkmanPhone',
				    	fieldLabel: i18n('i18n.businessOpportunity.contactPhone')//联系人电话
				    },{
				    	xtype:'dpdatefield',//有readonly样式的Date组件
				    	name: 'createTime',
				    	format:'Y-m-d',
				    	fieldLabel: i18n('i18n.questionManage.searchPanel.beginOfCreatDate')//创建时间
				    },{
				    	xtype:'dpdatefield',//有readonly样式的Date组件
				    	name: 'expectSuccessDate',
				    	format:'Y-m-d',
				    	fieldLabel: i18n('i18n.businessOpportunity.expectSuccessDate')//预计成功时间
				    },{
		            	 xtype:'container',
		            	 width:230,
		            	 layout:{
		         			type:'hbox'
		         		},
		            	 items:[{
		            		    name: 'expectSuccessOdds',
		            		    width:215,
		            		    xtype: 'textfield',
		            		    labelSeparator:'',
		            		    readOnly:true,
						    	fieldLabel: i18n('i18n.businessOpportunity.expectSuccessOdds')//预计成功几率
				             }, {
				                 xtype: 'label',
				                 text: '%',
				                 width:10,
				                 margins: '0 0 0 2'
				             }]
		             },{
				    	name: 'boStep',
				    	fieldLabel: i18n('i18n.businessOpportunity.boStep')//商机阶段
				    },{
		            	 xtype:'container',
		            	 width:230,
		            	 layout:{
		         			type:'hbox'
		         		},
		            	 items:[{
		            		    name: 'customer.custFirstType',
		            		    width:160,
		            		    readOnly:true,
		            		    labelSeparator:'',
		            		    xtype: 'textfield',
						    	fieldLabel: i18n('i18n.MemberCustEditView.custIndustry')//客户行业
				             }, {
				            	 name: 'customer.custSecondType',
			            		 width:70,
			            		 readOnly:true,
			            		 labelSeparator:'',
			            		 xtype: 'textfield',
			            		 labelWidth:10,
							     fieldLabel: '-'
				             }]
		             },{
				    	name: 'boStatus',
				    	fieldLabel: i18n('i18n.businessOpportunity.boStatus')//商机状态
				    },{
				    	name: 'boDesc',
				    	fieldLabel: i18n('i18n.businessOpportunity.boDesc'),//商机描述
				    	colspan: 3,
	                    width:700
				    }]
				}]
		},
		initComponent:function(){
			var me = this;
		    me.items = me.getItems();
			this.callParent();
	  }
});

/**
 * .
 * <p>
 * 商机业务信息FORM</br>
 * </p>
 * 
 * @returns CRM.BO.BusinessOpportunityView.BoYeInfoForm 商机业务信息FORM
 * @author 张斌
 * @时间 2014-03-12
 */
Ext.define('CRM.BO.BusinessOpportunityView.BoYeInfoForm', {
		extend:'TitleFormPanel',
		height:175,
		items:null,
		/**
		 * .
		 * <p>
		 * 获取ITEMS属性 </br>
		 * </p>
		 * 
		 * @author 张斌
		 * @时间 2012-03-12
		 */
		getItems:function(){
			var me = this;
			return  [{xtype: 'basicfiledset',
				    title: i18n('i18n.businessOpportunity.boYeInfo'),
				    defaults:{
				    	labelSeparator:'',
						labelWidth:100,
						width:230,
						readOnly:true,
				        xtype: 'textfield'
				    },
				    layout:{
				    	type:'table',
				    	columns:2
				    },
				    items:[{
				    	name: 'isBOConfirm',
				    	fieldLabel: i18n('i18n.businessOpportunity.isBOConfirm')//=确认商机
				    },{
				    	name: 'isBidProject',
				    	fieldLabel: i18n('i18n.businessOpportunity.isBidProject')//=招投标项目
				    },{
				    	name: 'customerDemandDesc',
				    	fieldLabel: i18n('i18n.businessOpportunity.customerDemandDesc'),//=客户需求简介
				    	colspan: 2,
	                    width:700
				    },{
				    	name: 'solutionDesc',
				    	fieldLabel: i18n('i18n.businessOpportunity.solutionDesc'),//=解决方案简述
				    	colspan: 2,
	                    width:700
				    },{
				    	name: 'competitiveInfo',
				    	fieldLabel: i18n('i18n.businessOpportunity.competitiveInfo'),//=竞争情况信息
				    	colspan: 2,
	                    width:700
				    },{
				    	name: 'closeReasonDesc',
				    	fieldLabel: i18n('i18n.businessOpportunity.closeResion'),//关闭原因
				    	colspan: 2,
	                    width:700
				    }]
				}]
		},
		initComponent:function(){
			var me = this;
		    me.items = me.getItems();
			this.callParent();
	  }
});

/**.
 * <p>
 * 商机日程信息panel</br>
 * </p>
 * @returns CRM.BO.BusinessOpportunityView.SchedulePanel 商机日程信息panel
 * @author  张斌
 * @时间    2014-03-19
 */
Ext.define('CRM.BO.BusinessOpportunityView.SchedulePanel', {
	extend:'Ext.panel.Panel',
	autoScroll : true,
	border:false,
    layout:{
        type:'vbox',
        align:'stretch'
    },
	title:i18n('i18n.businessOpportunity.boscheduleInfo'),//商机日程信息
	scheduleSearchForm:null,//商机日程查询FORM
	scheduleGrid:null,//商机日程表格
    /**.
    * <p>
    * 获取ITEMS属性
    * </br>
    * </p>
    * @author  张斌
    * @时间    2014-03-13
    */
    getItems:function(){
    	var me = this;
    	return  [me.scheduleSearchForm,me.scheduleGrid];
    },
    initComponent:function(){
    	var me = this;
    	me.scheduleSearchForm = Ext.create('CRM.BO.BusinessOpportunityView.ScheduleSearchForm');
    	me.scheduleGrid = Ext.create('CRM.BO.BusinessOpportunityView.ScheduleGrid');
    	me.items = me.getItems();
    	this.callParent();
    }
});

/**
 * .
 * <p>
 * 商机日程查询FORM<br/>
 * </p>
 * @returns CRM.BO.BusinessOpportunitySearch.SearchForm 商机日程查询FORM 的EXT对象
 * @author 张斌
 * @时间 2014-03-7
 */
Ext.define('CRM.BO.BusinessOpportunityView.ScheduleSearchForm', {
		extend:'SearchFormPanel',
		height:110,
		 defaultType: 'textfield',
         layout:{
			type:'table',
			columns:2
		 },
		defaults:{
			enableKeyEvents:true,
			labelSeparator:'',
			labelWidth:100,
			width:230,
		},
		/**
		 * .
		 * <p>
		 * 获取FORM ITEMS属性 </br>
		 * </p>
		 * @author 张斌
		 * @时间 2014-03-07
		 */
		getItems:function(){
			var me = this;
			var all = {code:'',codeDesc:i18n('i18n.businessOpportunity.all')};
 			var scheduleStatus = getDataDictionaryByName(DataDictionary,'SCHEDULE_STATUS');
 			scheduleStatus.add(all);
 			scheduleStatus.each(function(record){
 				if(record.get('code')=='10'){//取掉已指派
 					scheduleStatus.remove(record);
 				}
 			});
			return [{
		                xtype: 'textfield',
	                    name: 'marketRemark',
	                    maxLength:30,
	                    fieldLabel: i18n('i18n.businessOpportunity.scheduleMain')//日程主题
	                },{
	                    xtype:          'combo',
	                    mode:           'local',
	                    value:'',
	                    triggerAction:  'all',
	                    editable:false,
	                    forceSelection: true,
	                    fieldLabel:     i18n('i18n.businessOpportunity.scheduleStatus'),//日程状态
	                    name:'scheduleStatus',
	                    displayField:   'codeDesc',
	                    valueField:     'code',
	                    queryMode: 'local',
	                    store:scheduleStatus
	                },{
		                xtype     : 'datefield',
	                    name      : 'createStartTime',
		                fieldLabel: i18n('i18n.businessOpportunity.scheduleDate'),//日程时间
		                editable:false,
		                format: 'Y-m-d',
		            }, {
		                xtype     : 'datefield',
	                    name:      'createEndTime',
		                fieldLabel: i18n('i18n.questionManage.searchPanel.endOfCreatDate'),
		                editable:       false,
		                format: 'Y-m-d',
		                labelSeparator:''
		            }];
		},
		initComponent:function()
		{
			var me = this;
			me.items = me.getItems();
			me.fbar = [{
				xtype:'button',
				text:i18n('i18n.developPlan.search'),//查询
				scope:me,
				handler:function(){
					var me = this;
 	    			if(me.getForm().isValid()){
 	   		    		var beginTime = me.getForm().findField('createStartTime').getValue();
 	   		        	var endTime =  me.getForm().findField('createEndTime').getValue();
 	   		        	if(((endTime.getTime()-beginTime.getTime())/(24*60*60*1000))<0){
 	   		        	    MessageUtil.showMessage(i18n('i18n.businessOpportunity.notbeginmoreend'));
 	   		        		return;
 	   		        	}else{
 	   		        	    me.up().down('grid').getStore().loadPage(1);
 	   		        	}
 	   		    	}
				}
			}]
			this.callParent();
		}
});

/**
 * .
 * <p>
 * 商机日程查询GIRD<br/>
 * </p>
 * 
 * @returns CRM.BO.BusinessOpportunityView.ScheduleGrid 商机日程查询GIRD 的EXT对象
 * @author 张斌
 * @时间 2014-03-19
 */
Ext.define('CRM.BO.BusinessOpportunityView.ScheduleGrid',{
	extend:'SearchGridPanel',
	height:265,
	model:'SINGLE',
	pageSize : 5000,
	listeners: {
    	scrollershow: function(scroller) {
    		if (scroller && scroller.scrollEl) {
    				scroller.clearManagedListeners(); 
    				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
    		}
    	}
    },
   sortableColumns:true,//不能排序
   enableColumnHide:false,//不能隐藏
   enableColumnMove:false,//不能移动
	getSelModel:function(){
		var me = this;
		return Ext.create('Ext.selection.CheckboxModel',{
				mode:me.model
			});
	},
 // 创建gird的columns
	 columns : [{
	        xtype: 'rownumberer',
	        width: 35,
	        text     : i18n('i18n.Cycle.rownum')//序号
	    },{
	        text     : i18n('i18n.businessOpportunity.scheduleMain'),//日程主题
	        dataIndex: 'marketRemark'
	    },{
	        text     : i18n('i18n.businessOpportunity.customerName'),//客户名称
	        dataIndex: 'custName'
	    },{
	        text     : i18n('i18n.businessOpportunity.contactName'),//联系人名称
	        dataIndex: 'linkManName'
	    },{
	        text     : i18n('i18n.businessOpportunity.contactMobile'),//联系人手机
	        dataIndex: 'linkManMobeilPhone'
	    },{
	        text     : i18n('i18n.businessOpportunity.contactPhone'),//联系人电话
	        dataIndex: 'linkManTelePhone'
	    },{
	        text     : i18n('i18n.businessOpportunity.scheduleDate'),//日程时间
	        width:90,
	        dataIndex: 'scheduleDate',
	        renderer : function(value){
	           	var date = new Date(value);
	           	return date.format('yyyy-MM-dd');
           }
	    },{
	        text     : i18n('i18n.businessOpportunity.scheduleStatus'),//日程状态
	        dataIndex: 'scheduleStatus',
	        renderer : function(value){
	        	return DpUtil.rendererDictionary(value,DataDictionary.SCHEDULE_STATUS); 
           }
	    }],
    initComponent:function(){
		var me = this;
		me.store = businessOpportunityData.getScheduleStore();
		me.selModel = me.getSelModel();
		// 增加store的beforeload方法
		me.store.on('beforeload',function(boStore,operation,e){
			var form = me.up().down('form').getForm();
			var marketRemark = Ext.String.trim(form.findField('marketRemark').getValue());//日程主题
			var scheduleStatus = form.findField('scheduleStatus').getValue();// 日程状态
			var createStartTime = form.findField('createStartTime').getValue();// 开始时间
			var createEndTime = form.findField('createEndTime').getValue();// 结束时间
			var searchParams = {'scheduleVO.marketRemark':marketRemark
					,'scheduleVO.scheduleStatus':scheduleStatus
					,'scheduleVO.createStartTime':createStartTime
					,'scheduleVO.createEndTime':createEndTime
					,'scheduleVO.comeFrom':'BUSINESS'
					,'scheduleVO.comeFromId':me.up('window').businessOpportunity.id
					,'scheduleVO.scheduleType':'dev'//商机日程类型默认为'dev'
            };
  			Ext.apply(operation,{
				params : searchParams
			});
  		});
		this.callParent();
	}
});