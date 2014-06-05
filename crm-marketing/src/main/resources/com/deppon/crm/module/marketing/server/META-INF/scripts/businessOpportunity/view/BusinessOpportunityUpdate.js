/**.
 * <p>
 * 商机修改相关界面元素<br/>
 * <p>
 */

/**
  * .
  * <p>
  * 商机修改window<br/>
  * </p>
  * 
  * @returns CRM.BO.BusinessOpportunityUpdate.Window 商机修改window 的EXT对象
  * @author 张斌
  * @时间 2014-03-11
  */
Ext.define('CRM.BO.BusinessOpportunityUpdate.Window',{
	extend:'PopWindow',
	width:790,
	height:500,
	title:i18n('i18n.businessOpportunity.boUpdateWin'),//商机信息编辑界面
	parent:null,//他的上级元素
	businessOpportunity:null,//差u型您出的JSON数据
	closeAction:'hide',
	businessOpportunityViewTab:null,//新增商家的FORM
	isMainMan:false,//是否为当前登录人所在部门的负责人
	isOperator:false,//是否为该商机的负责人
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
    	}
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
			text:i18n('i18n.PotentialCustEditView.save'),//保存
			handler:function(btn){
				me.updateBo(btn);

			}
		},{
			xtype:'button',
			text:i18n('i18n.memberView.cancel'),//取消
			handler:function(){
				me.hide();

			}
		}];
	},
	getItems:function(){
		var me = this;
		me.businessOpportunityViewTab = Ext.create('CRM.BO.BusinessOpportunityUpdate.Tab');
		return [me.businessOpportunityViewTab];
	},
	//修改商机信息
	updateBo:function(btn){
		var me= this;
		var businessOpportunityModel = new CRM.BO.BusinessOpportunity();
		businessOpportunityModel.set('id',me.businessOpportunity.id);
		var form = me.businessOpportunityViewTab.boInfoPanel.getForm();
		if(!form.isValid()){
			return;
		}
//		if(me.isMainMan){//当前登录人所在营部负责人，可以修改商机的责任人
			businessOpportunityModel.set('operator',{'id':form.findField('operatorId').getValue()});
//		}
        //修改时传全部信息
//		if(me.isOperator){
			//设置修改之后的值
			businessOpportunityModel.set('expectDeliveryAmount',form.findField('expectDeliveryAmount').getValue());
			businessOpportunityModel.set('expectSuccessOdds',form.findField('expectSuccessOdds').getValue());
			businessOpportunityModel.set('isBOConfirm',form.findField('isBOConfirm').getValue());
			businessOpportunityModel.set('isBidProject',form.findField('isBidProject').getValue());
			businessOpportunityModel.set('expectSuccessDate',form.findField('expectSuccessDate').getValue());
			//商机关闭时不验证时间 auth : 李春雨
            var createTime = form.findField('createTime').getValue();
            if(businessOpportunityModel.get('expectSuccessDate').getTime()<createTime){
				MessageUtil.showMessage(i18n('i18n.businessOpportView.successDatenotSmallCreate'));//预计成功时间不能小于创建时间
				return;
			}
            if(form.findField('expectSuccessDate').getValue() > createTime.setMonth(createTime.getMonth()+6)){
                MessageUtil.showMessage(i18n('i18n.businessOpportView.successTimeMore6'));//预计时间不能超过创建时间6个自然月
                return;
            }
			businessOpportunityModel.set('customerDemandDesc',form.findField('customerDemandDesc').getValue());
			businessOpportunityModel.set('solutionDesc',form.findField('solutionDesc').getValue());
			businessOpportunityModel.set('solutionDesc',form.findField('solutionDesc').getValue());
			businessOpportunityModel.set('competitiveInfo',form.findField('competitiveInfo').getValue());
			businessOpportunityModel.set('boDesc',form.findField('boDesc').getValue());
            if(form.findField('closeBo').getValue()){//关闭商机选择，则要添加关闭商机的数据，并将状态修改为“FAILURE”关闭失败
            	businessOpportunityModel.set('closeReasonDesc',form.findField('closeReason').getRawValue());
            	var closeReasonCodeArray = form.findField('closeReason').getValue();
            	var closeReasonCode = '';
            	for(var i=0;i<closeReasonCodeArray.length;i++){
            		if(i==0){
            			closeReasonCode = closeReasonCodeArray[i];
            		}else{
            			closeReasonCode = closeReasonCode+','+closeReasonCodeArray[i];
            		}
            	}
            	businessOpportunityModel.set('closeReasonCode',closeReasonCode);//xx,xx,xx
            	businessOpportunityModel.set('boStatus','FAILURE');//商机状态
            }
//		};
		btn.disable();
		var param = {'businessOpportunity':businessOpportunityModel.data};
		var successFn = function(json){
			btn.enable();
			MessageUtil.showInfoMes(i18n('i18n.businessOpportunity.updateBoSuccess'));//修改商机成功
			var form = me.parent.up().down('form');
			if(form.getForm().isValid()){
		    		var beginTime = form.getForm().findField('beginTime').getValue();
		        	var endTime =  form.getForm().findField('endTime').getValue();
		        	if(((endTime.getTime()-beginTime.getTime())/(24*60*60*1000))>365){
		        	    MessageUtil.showMessage(i18n('i18n.businessOpportunity.not365'));
		        		return;
		        	}else if(((endTime.getTime()-beginTime.getTime())/(24*60*60*1000))<0){
		        	    MessageUtil.showMessage(i18n('i18n.businessOpportunity.notbeginmoreend'));
		        		return;
		        	}else{
		        		me.parent.up().down('grid').getStore().loadPage(1);
		        	}
		    	}
			me.hide();
		};
		var failureFn = function(json){
			btn.enable();
		    MessageUtil.showErrorMes(json.message);
		};
		businessOpportunityData.updateBo(param,successFn,failureFn);
	},
	//重置商机信息
	//并且全部设置为只读
	resetBo:function(){
		var me = this;
		//回到第一个页签
		me.businessOpportunityViewTab.setActiveTab(0);
		//商机基本信息重置
		me.businessOpportunityViewTab.boInfoPanel.getForm().reset();
		me.businessOpportunityViewTab.boInfoPanel.getForm().getFields().each(function(item){
			item.setReadOnly(true);
			if(item.getName()=='expectDeliveryAmount'
				||item.getName()=='operatorId'||item.getName()=='expectSuccessOdds'
					||item.getName()=='isBOConfirm'||item.getName()=='isBidProject'||item.getName()=='closeReasonCode'){
				item.addCls('readonly');
			}
		});
		me.businessOpportunityViewTab.linkManInfo.linkManGrid.getStore().removeAll();//清除联系人信息
		//商机日程信息清除
		me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createStartTime').setValue(null);
		me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createStartTime').setMinValue(null);
		me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createStartTime').setMaxValue(null);
		me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createEndTime').setValue(null);
		me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createEndTime').setMinValue(null);
		me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createEndTime').setMaxValue(null);
		me.businessOpportunityViewTab.schedulePanel.maxScheduleDate = null;//清楚日程最大时间
        //清空store中的数据  auth ：李春雨
        me.businessOpportunityViewTab.schedulePanel.scheduleGrid.getStore().removeAll();
		//营销记录信息清除
		custIDForMarketRecord = null;
		me.businessOpportunityViewTab.marketHistoryResultPanel.marketHistoryResultPanelOfReturnVisit.customerDemandResultGrid.getStore().removeAll();//客户需求
		me.businessOpportunityViewTab.marketHistoryResultPanel.marketHistoryResultPanelOfReturnVisit.sendGoodsPontentialResultGrid.getStore().removeAll();//走货潜力
		me.businessOpportunityViewTab.marketHistoryResultPanel.questionnaireResultGridForMarketRecord.getStore().removeAll();//问卷
		//新增删除回访按钮可用
		me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getDockedItems()[0].items.items[0].setDisabled(false);
		me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getDockedItems()[0].items.items[1].setDisabled(false);
		me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getDockedItems()[0].items.items[2].setDisabled(false);
	},
	//加载商机信息
	//设置哪些可以编辑
	loadBo:function(){
		var me = this;
		var form = me.businessOpportunityViewTab.boInfoPanel.getForm();
		if(!Ext.isEmpty(me.businessOpportunity)){
			var businessOpportunityModel = new CRM.BO.BusinessOpportunity(me.businessOpportunity);//构建商机model
			businessOpportunityModel.set('boStatus'
					,DButil.rendererDictionary(businessOpportunityModel.get('boStatus'),DataDictionary.BUSINESS_OPPORTUNITY_STATUS));//商机状态
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
			form.findField('operatorId').setValue(businessOpportunityModel.get('operator').id);//商机责任人
		}
		//设置该修改用户可修改的内容
		if(me.isMainMan){//当前登录人所在营部负责人，可以修改商机的责任人
			form.findField('operatorId').setReadOnly(false);
			form.findField('operatorId').removeCls('readonly');
		}
		if(me.isOperator){//该商机的责任人是登录用户，则可以修改。。。
			form.findField('expectDeliveryAmount').setReadOnly(false);
			form.findField('expectDeliveryAmount').removeCls('readonly');
			form.findField('expectSuccessOdds').setReadOnly(false);
			form.findField('expectSuccessOdds').removeCls('readonly');
			form.findField('isBOConfirm').setReadOnly(false);
			form.findField('isBOConfirm').removeCls('readonly');
			form.findField('isBidProject').setReadOnly(false);
			form.findField('isBidProject').removeCls('readonly');
			form.findField('expectSuccessDate').setReadOnly(false);
			form.findField('customerDemandDesc').setReadOnly(false);
			form.findField('solutionDesc').setReadOnly(false);
			form.findField('competitiveInfo').setReadOnly(false);
			form.findField('boDesc').setReadOnly(false);
			form.findField('closeBo').setReadOnly(false);
            //设置如果确认商机为是 则不可修改此记录 auth:李春雨
            var isBOConfirm = form.findField('isBOConfirm');
            if(isBOConfirm.getValue() == 1){
                isBOConfirm.setReadOnly(true);
                isBOConfirm.addCls('readonly');
            };
            //如果客户需求，解决方案，竞争情况不为空，则不可修改为空
            customerDemandDesc = form.findField('customerDemandDesc');
            solutionDesc = form.findField('solutionDesc');
            competitiveInfo = form.findField('competitiveInfo');
            if(!Ext.isEmpty(customerDemandDesc.getValue())){
                customerDemandDesc.allowBlank = false;
            }else{
                customerDemandDesc.allowBlank = true;
            }
            if(!Ext.isEmpty(solutionDesc.getValue())){
                solutionDesc.allowBlank = false;
            }else{
                 solutionDesc.allowBlank = true;
            }
            if(!Ext.isEmpty(competitiveInfo.getValue())){
                competitiveInfo.allowBlank = false;
            }else{
                competitiveInfo.allowBlank = true;
            }
		}
	},
	//加载客户联系人信息
	loadLinkMan:function(){
		var me = this;
		if(!Ext.isEmpty(me.businessOpportunity)){
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
		}
	},
	//加载商机日程查询数据
	loadScheduleSearchCondition:function(){
		var me = this;
		if(!Ext.isEmpty(me.businessOpportunity)){
			var minValue = DpUtil.changeLongToDate(me.businessOpportunity.createTime);
			var maxValue = new Date(minValue.getFullYear(),minValue.getMonth()+6,minValue.getDate());
			me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createStartTime').setValue(minValue);
			me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createStartTime').setMinValue(minValue);
			me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createStartTime').setMaxValue(maxValue);
			me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createEndTime').setValue(maxValue);
			me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createEndTime').setMinValue(minValue);
			me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getForm().findField('createEndTime').setMaxValue(maxValue);
			me.businessOpportunityViewTab.schedulePanel.maxScheduleDate = maxValue;//向下传入可以选择的最大的日程时间
			if(me.isOperator){//只有当前上级的负责人才能修改创建日程
				me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getDockedItems()[0].items.items[0].setDisabled(false);
				me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getDockedItems()[0].items.items[1].setDisabled(false);
				me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getDockedItems()[0].items.items[2].setDisabled(false);
			}else{
				me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getDockedItems()[0].items.items[0].setDisabled(true);
				me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getDockedItems()[0].items.items[1].setDisabled(true);
				me.businessOpportunityViewTab.schedulePanel.scheduleSearchForm.getDockedItems()[0].items.items[2].setDisabled(true);
			}
		}
	},
	//加载营销记录数据
	loadMarketHistoryResult:function(){
		var me = this;
		if(!Ext.isEmpty(me.businessOpportunity)){
			me.businessOpportunityViewTab.marketHistoryResultPanel.marketHistoryResultPanelOfReturnVisit.customerDemandResultGrid.getStore().loadPage(1);
			me.businessOpportunityViewTab.marketHistoryResultPanel.marketHistoryResultPanelOfReturnVisit.sendGoodsPontentialResultGrid.getStore().loadPage(1);
			me.businessOpportunityViewTab.marketHistoryResultPanel.questionnaireResultGridForMarketRecord.getStore().loadPage(1);//问卷
		}
	}
});

/**
 * .
 * <p>
 * 商机查看tab<br/>
 * </p>
 * 
 * @returns CRM.BO.BusinessOpportunityUpdate.Tab 商机查看tab 的EXT对象
 * @author 张斌
 * @时间 2014-03-11
 */
Ext.define('CRM.BO.BusinessOpportunityUpdate.Tab', {
	extend: 'NormalTabPanel',
	width:790,
	height:415,
    activeTab: 0,
    boInfoPanel:null,//商机信息Panel
    schedulePanel:null,//商机日程信息
    linkManInfo:null,//联系人信息
    marketHistoryResultPanel:null,//回访历史记录
    //获取相关panle
	getItems:function(){
		var me = this;
		me.boInfoPanel = Ext.create('CRM.BO.BusinessOpportunityUpdate.BoInfoPanel');
		me.linkManInfo = Ext.create('CRM.BO.BusinessOpportunityCommon.LinkManPanel',{name:'linkManPanel'});
		me.schedulePanel = Ext.create('CRM.BO.BusinessOpportunityUpdate.SchedulePanel');
		me.marketHistoryResultPanel = Ext.create('CRM.BO.BusinessOpportunityCommon.MarketHistoryResultPanel');
		return [me.boInfoPanel,me.schedulePanel,me.marketHistoryResultPanel,me.linkManInfo];
	},
    //修改高度使滚动条每次出现 auth:李春雨
    listeners : {
        tabchange:function(tabPanel,newCard,oldCard){
            if(newCard.name == 'linkManPanel'){
                newCard.setHeight(380);
            }
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
 * @returns CRM.BO.BusinessOpportunityUpdate.BoInfoPanel 商机信息FORM
 * @author  张斌
 * @时间    2014-03-12
 */
Ext.define('CRM.BO.BusinessOpportunityUpdate.BoInfoPanel', {
	extend:'BasicFormPanel',
	autoScroll : true,
	border:false,
    layout:{
        type:'vbox',
        align:'stretch'
    },
	title:i18n('i18n.businessOpportunity.boInfo'),
	basicInfoForm:null,//商机基础信息form
	boYeInfoForm:null,//商机业务信息form
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
    	me.basicInfoForm = Ext.create('CRM.BO.BusinessOpportunityUpdate.BasicInfoForm');
    	me.boYeInfoForm = Ext.create('CRM.BO.BusinessOpportunityUpdate.BoYeInfoForm');
    	me.items = me.getItems();
    	this.callParent();
    }

});
/**
 * .
 * <p>
 * 商机基础信息FORM</br>
 * </p>
 * 
 * @returns CRM.BO.BusinessOpportunityUpdate.BasicInfoForm 商机基础信息FORM
 * @author 张斌
 * @时间 2014-03-12
 */
Ext.define('CRM.BO.BusinessOpportunityUpdate.BasicInfoForm', {
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
			var storeStep = getDataDictionaryByName(DataDictionary,'BUSINESS_OPPORTUNITY_STEP');
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
		            		    xtype: 'numberfield',
			                    name: 'expectDeliveryAmount',
			                    maxValue:9999999999,
			                    minValue:10000,
			                    step:1000,
			                    decimalPrecision:0,
			                    width:215,
			                    readOnly:true,
			                    cls:'readonly',
			                    allowBlank:false,
			                    value:10000,
			                    fieldLabel: i18n('i18n.businessOpportunity.expectDeliveryAmount')//预计发货金额
				             }, {
				                 xtype: 'label',
				                 text: i18n('i18n.visualMarket.yuan'),
				                 width:10,
				                 margins: '0 0 0 2'
				             }]
		             },{
		                 xtype: 'combo',
		                 fieldLabel:i18n('i18n.businessOpportunity.operator'),//商机责任人
		                 name:'operatorId',
		                 cls:'readonly',
		                 store: Ext.create('Ext.data.Store', {
			            		model : 'CRM.BO.EmpModel',
			            		data:EMP
			            	 }),
		                 queryMode: 'local',
		                 triggerAction : 'all',
		                 displayField:'empName',
		                 valueField:'id',
		                 //剔出朦层
		                 listConfig: {
		                     loadMask:false
		                 },
		                 editable:false,
		                 forceSelection :true,
		                 allowBlank: false
		             },{
				    	name: 'customer.mainLinkmanPhone',
				    	fieldLabel: i18n('i18n.businessOpportunity.contactPhone')//联系人电话
				    },{
				    	xtype:'dpdatefield',//有readonly样式的Date组件
				    	name: 'createTime',
				    	format:'Y-m-d',
				    	fieldLabel: i18n('i18n.questionManage.searchPanel.beginOfCreatDate')//创建时间
				    },{
		                xtype     : 'dpdatefield',
	                    name      : 'expectSuccessDate',
	                    value:new Date(),
	                    allowBlank:false,
		                fieldLabel: i18n('i18n.businessOpportunity.expectSuccessDate'),//预计成功时间
		                editable:false,
		                format: 'Y-m-d'
		            },{
		            	 xtype:'container',
		            	 width:230,
		            	 layout:{
		         			type:'hbox'
		         		},
		            	 items:[{
				                xtype: 'numberfield',
			                    name: 'expectSuccessOdds',
			                    maxValue:100,
			                    allowBlank:false,
			                    decimalPrecision:0,
                                negativeText : i18n('i18n.MarketActivityManagerView.valueNotNegative'),//此值不允许为负数
			                    readOnly:true,
			                    cls:'readonly',
			                    minValue:0,
			                    width:215,
			                    value:0,
			                    fieldLabel: i18n('i18n.businessOpportunity.expectSuccessOdds')//预计成功几率
				             }, {
				                 xtype: 'label',
				                 text: '%',
				                 width:10,
				                 margins: '0 0 0 2'
				             }]
		             },{
	 	                    xtype:          'combo',
	 	                    mode:           'local',
	 	                    value:'',
	 	                    triggerAction:  'all',
	 	                    editable:false,
	 	                    allowBlank:false,
	 	                    cls:'readonly',
	 	                    forceSelection: true,
	 	                    fieldLabel:     i18n('i18n.businessOpportunity.boStep'),//商机阶段
	 	                    name:'boStep',
	 	                    displayField:   'codeDesc',
	 	                    valueField:     'code',
	 	                    queryMode: 'local',
	 	                    store:storeStep
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
				    	maxLength:200,
				    	fieldLabel: i18n('i18n.businessOpportunity.boDesc'),//商机描述
				    	colspan: 3,
                        allowBlank : false,
                        blankText:i18n('i18n.businessOpportunity.inputBoDesc'),//请输入商机描述
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
 * @returns CRM.BO.BusinessOpportunityUpdate.BoYeInfoForm 商机业务信息FORM
 * @author 张斌
 * @时间 2014-03-12
 */
Ext.define('CRM.BO.BusinessOpportunityUpdate.BoYeInfoForm', {
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
			var closeResion = getDataDictionaryByName(DataDictionary,'BO_CLOSE_REASON');
            //过滤 到期关闭和休眠关闭 auth:李春雨
//            closeResion.filter("code", /^(?!(?:EXPIRE|DORMANT)$)/);
            var index = closeResion.find( 'code', 'EXPIRE', 0, false,true,true);
            closeResion.removeAt(index);
            index = closeResion.find( 'code', 'DORMANT', 0, false,true,true);
            closeResion.removeAt(index);
			return  [{xtype: 'basicfiledset',
				    title: i18n('i18n.businessOpportunity.boYeInfo'),
				    defaults:{
				    	labelSeparator:'',
						labelWidth:100,
						width:230,
				        xtype: 'textfield',
				        readOnly:true
				    },
				    layout:{
				    	type:'table',
				    	columns:6
				    },
				    items:[{
				    	xtype:          'combo',
 	                    mode:           'local',
 	                    //value:'1',
 	                    triggerAction:  'all',
 	                    editable:false,
 	                    colspan: 3,
 	                    cls:'readonly',
 	                    forceSelection: true,
 	                    displayField:   'codeDesc',
 	                    valueField:     'code',
 	                    queryMode: 'local',
 	                    store:Ext.create('Ext.data.Store', {
 	                    	fields: [
 	                    	        {name: 'code', type: 'int'},
 	                    	        {name: 'codeDesc',  type: 'string'}],
 	                        data : [
	 	                          {code: 1,    codeDesc: i18n('i18n.ChangeContactAffiliatedRelationView.yes')},
	 	                          {code: 0, codeDesc: i18n('i18n.ChangeContactAffiliatedRelationView.no')}]
 	                    }),
				    	name: 'isBOConfirm',
				    	fieldLabel: i18n('i18n.businessOpportunity.isBOConfirm')//=确认商机
				    },{
				    	xtype:          'combo',
 	                    mode:           'local',
 	                    colspan: 3,
 	                    triggerAction:  'all',
 	                    editable:false,
 	                    cls:'readonly',
 	                    forceSelection: true,
 	                    displayField:   'codeDesc',
 	                    valueField:     'code',
 	                    queryMode: 'local',
 	                    store:Ext.create('Ext.data.Store', {
 	                    	fields: [
 	                    	        {name: 'code', type: 'int'},
 	                    	        {name: 'codeDesc',  type: 'string'}],
 	                        data : [
	 	                          {code: 1,    codeDesc: i18n('i18n.ChangeContactAffiliatedRelationView.yes')},
	 	                          {code: 0, codeDesc: i18n('i18n.ChangeContactAffiliatedRelationView.no')}]
 	                    }),
				    	name: 'isBidProject',
				    	fieldLabel: i18n('i18n.businessOpportunity.isBidProject')//=招投标项目
				    },{
				    	name: 'customerDemandDesc',
				    	fieldLabel: i18n('i18n.businessOpportunity.customerDemandDesc'),//=客户需求简介
				    	maxLength:200,
				    	colspan: 6,
	                    width:700
				    },{
				    	name: 'solutionDesc',
				    	fieldLabel: i18n('i18n.businessOpportunity.solutionDesc'),//=解决方案简述
				    	maxLength:200,
				    	colspan: 6,
	                    width:700
				    },{
				    	name: 'competitiveInfo',
				    	fieldLabel: i18n('i18n.businessOpportunity.competitiveInfo'),//=竞争情况信息
				    	maxLength:100,
				    	colspan: 6,
	                    width:700
				    },{
				    	xtype:'checkboxfield',
	                    boxLabel  :i18n('i18n.businessOpportunity.closeBo'), //,'关闭商机'
	                    name      : 'closeBo',
	                    colspan: 1,
	                    //labelWidth:55,
	                    width:70,
	                    inputValue: '1',
	                    listeners:{
	                    	change:function(check,newValue,oldValue){
	                    		var form = check.up('form').getForm();
	                    		if(newValue){//选择关闭商机，关闭原因可选，必选
	                    			form.findField('closeReason').setReadOnly(false);
	                    			form.findField('closeReason').removeCls('readonly');
	                    			form.findField('closeReason').allowBlank = false;
	                    		}else{//取消关闭商机，关闭原因不可选，不必选
	                    			form.findField('closeReason').reset();
	                    			form.findField('closeReason').setReadOnly(true);
	                    			form.findField('closeReason').addCls('readonly');
	                    			form.findField('closeReason').allowBlank = true;
	                    		}
	                    	}
	                    }
	                }, {
 	                    xtype:          'combo',
 	                    mode:           'local',
 	                    colspan: 5,
 	                    width:470,
 	                    labelWidth:55,
 	                    triggerAction:  'all',
 	                    editable:false,
 	                    cls:'readonly',
 	                    multiSelect:true,
 	                    forceSelection: true,
 	                    fieldLabel:     i18n('i18n.businessOpportunity.selectResion'),//关闭原因
 	                    name:'closeReason',
 	                    displayField:   'codeDesc',
 	                    valueField:     'code',
 	                    queryMode: 'local',
 	                    store:closeResion
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
 * @returns CRM.BO.BusinessOpportunityUpdate.SchedulePanel 商机日程信息panel
 * @author  张斌
 * @时间    2014-03-19
 */
Ext.define('CRM.BO.BusinessOpportunityUpdate.SchedulePanel', {
	extend:'Ext.panel.Panel',
	autoScroll : true,
	border:false,
	maxScheduleDate:null,//日程最大时间
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
    	me.scheduleSearchForm = Ext.create('CRM.BO.BusinessOpportunityUpdate.ScheduleSearchForm');
    	me.scheduleGrid = Ext.create('CRM.BO.BusinessOpportunityUpdate.ScheduleGrid');
    	me.items = me.getItems();
    	this.callParent();
    }
});

/**
 * .
 * <p>
 * 商机日程查询FORM<br/>
 * </p>
 * @returns CRM.BO.BusinessOpportunityUpdate.ScheduleSearchForm 商机日程查询FORM 的EXT对象
 * @author 张斌
 * @时间 2014-03-7
 */
Ext.define('CRM.BO.BusinessOpportunityUpdate.ScheduleSearchForm', {
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
			width:230
		},
		//新增商机日程wind
		scheduleAddWin:null,
		getScheduleAddWin:function(){
			var me= this;
			if(Ext.isEmpty(me.scheduleAddWin)){
				me.scheduleAddWin = new CRM.BO.ScheduleAdd.Window({'parent':me});
			}
			return me.scheduleAddWin;
		},
		//新增商机日程wind
		developPopWindow:null,
		getDevelopPopWindow:function(){
			var me= this;
			if(Ext.isEmpty(me.developPopWindow)){
				me.developPopWindow = new CRM.BO.BusinessOpportunity.DevelopPopWindow({'parent':me});
			}
			return me.developPopWindow;
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
		                format: 'Y-m-d'
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
				text:i18n('i18n.questionManage.searchButton.addQuestion'),//新增
				scope:me,
				handler:function(){
					var me = this;
					me.getScheduleAddWin().show();
				}
			},{
				xtype:'button',
				text:i18n('i18n.questionManage.searchButton.deleteQuestion'),//删除
				scope:me,
				handler:function(){
					var me = this;
					var selections = me.up().down('grid').getSelectionModel( ).getSelection( );//获取选中数据
 					if(selections.length==0){
 						MessageUtil.showMessage(i18n('i18n.businessOpportunity.selectOneToDelete'));//至少选择一条
 						return;
 					};
 					var planeIds = new Array();
 					for(var i = 0;i<selections.length;i++){
 						if(selections[i].get('scheduleStatus')=='30'||selections[i].get('scheduleStatus')=='40'){
 							MessageUtil.showMessage(i18n('i18n.businessOpportunity.notDeleteBoSchedule'));//已完成、已超期的商机日程无法进行修改
 							return;
 	 				    }else{
 	 				    	planeIds.push(selections[i].get('id'));
 	 				    }
 					};
 					var successFn = function(json){
 						MessageUtil.showInfoMes(i18n('i18n.businessOpportunity.deleteBoSuccessSchedule'));//删除商机日程成功
 						me.up().down('grid').getStore().loadPage(1);
 					};
 					var failureFn = function(json){
 					    MessageUtil.showErrorMes(json.message);
 					};
 					var param = {'planeIds':planeIds};//构建请求数据
 					businessOpportunityData.deleteBoSchedule(param,successFn,failureFn);
				}
			},{
				xtype:'button',
				text:i18n('i18n.developSchedule.returnVisit'),//回访
				scope:me,
				handler:function(){
					var me = this;
					var selections = me.up().down('grid').getSelectionModel( ).getSelection( );//获取选中数据
 					if(selections.length!=1){
 						MessageUtil.showMessage(i18n('i18n.businessOpportunity.selectOneReturnVisit'));//只能选择一条
 						return;
 					};
 					if(selections[0].get('scheduleStatus')=='30'||selections[0].get('scheduleStatus')=='40'){
						MessageUtil.showMessage(i18n('i18n.businessOpportunity.notRturnVisitBoSchedule'));//已完成、已超期的商机日程无法进行回访
						return;
	 				}
					var businessOpportunity = me.up('window').businessOpportunity;
 					var successFn = function(json){
						    var initData=new InitDataModel(json.returnVisit);
					    	var customerInfoFormPanel = Ext.getCmp("customerInfoFormPanel").getForm();
					    	customerInfoFormPanel.loadRecord(initData);
						 	var ScheduleMakeForm =  Ext.getCmp("scheduleMakeForm").getForm();
						 	ScheduleMakeForm.loadRecord(initData);
					    	Ext.getCmp('scheType').setValue('dev');
					    	me.getDevelopPopWindow().show();
                            Ext.getCmp('ifparent').setValue('0');//设置是否制定跟踪日程为否
                            Ext.getCmp('ifparent').setReadOnly(true);//设置只读 李春雨
                            Ext.getCmp('ifparent').addCls('readonly');
                            Ext.getCmp('schedule').setReadOnly(true);
                            Ext.getCmp('schedule').addCls('readonly');
                            Ext.getCmp('schedule').allowBlank = true;
                            Ext.getCmp('continueMarket').setValue('1');//继续营销默认是
					    };
						var failureFn = function(json){
							MessageUtil.showErrorMes(json.message);
						};
						DevelopScheduleData.prototype.setRetureVisit({
							'returnVisit.scheduleId':selections[0].get("id"),
							'returnVisit.linkManId':businessOpportunity.customer.mainLinkmanId,
							'returnVisit.memberId':businessOpportunity.customer.custId,
							'returnVisit.scheType':'dev'
						}, successFn, failureFn);
						//清空走货潜力表格
						Ext.getCmp('sendGoodsPontentialGrid').store.removeAll();
				    	//清空客户意见表格
				    	Ext.getCmp('customerOpinionGrid').store.removeAll();
				    	//清空跟踪时间和跟踪方式
				    	Ext.getCmp('schedule').setValue(null).enable();
				    	Ext.getCmp("ifparent").setValue('1');
				}
			},{
				xtype:'label',
				width:400
			},{
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
			}];
			this.callParent();
		}
});

/**
 * .
 * <p>
 * 商机日程查询GIRD<br/>
 * </p>
 * 
 * @returns CRM.BO.BusinessOpportunityUpdate.ScheduleGrid 商机日程查询GIRD 的EXT对象
 * @author 张斌
 * @时间 2014-03-19
 */
Ext.define('CRM.BO.BusinessOpportunityUpdate.ScheduleGrid',{
	extend:'SearchGridPanel',
	model:'MULTI',
	height:265,
	pageSize : 5000,
	listeners: {
    	scrollershow: function(scroller) {
    		if (scroller && scroller.scrollEl) {
    				scroller.clearManagedListeners(); 
    				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
    		}
    	},
    	itemdblclick:function(view,record){
    		
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
	        dataIndex: 'marketRemark',
	        editor: {
                xtype: 'textfield',
                allowBlank : false,
                maxLength:30
       	   }
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
           },
           editor: {
               xtype: 'datefield',
               minValue :new Date(),
               maxValue : new Date(new Date().setMonth(new Date().getMonth()+6)),
               editable: false,
               format: 'Y-m-d'
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
			var marketRemark = form.findField('marketRemark').getValue();//日程主题
			var scheduleStatus = form.findField('scheduleStatus').getValue();// 日程状态
			var createStartTime = form.findField('createStartTime').getValue();// 开始时间
			var createEndTime = form.findField('createEndTime').getValue();// 结束时间
			var searchParams = {'scheduleVO.marketRemark':Ext.String.trim(marketRemark)
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
		
		var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
			 clicksToMoveEditor: 2,
             //去除验证提示 auth : 李春雨
             errorSummary : false,
 		 	 autoCancel: false
		});
		rowEditing.on('beforeedit', function(editor, e) {
			var record=editor.record;
			if(record.get('scheduleStatus')=='30'||record.get('scheduleStatus')=='40'){
				MessageUtil.showMessage(i18n('i18n.businessOpportunity.notUpdateBoSchedule'));//已完成、已超期的商机日程无法进行修改
				record.commit();
			}
			if(!me.up('window').isOperator){//只有当前商机的执行人才能修改创建日程
				MessageUtil.showMessage(i18n('i18n.businessOpportunity.haonoAuth'));//您无权修改该日程信息！
				record.commit();
			}
		});
		rowEditing.on('edit',function(th,e,eop){
			var record=th.record;
			var oldScheduleDate = record.get('scheduleDate');
			if((record.get('scheduleDate').getTime()-me.up().maxScheduleDate.getTime())/(24*60*60*1000)>0){
				MessageUtil.showMessage(i18n('i18n.businessOpportunity.moreMaxDate'));//日程时间大于允许值
				me.up().down('grid').getStore().loadPage(1);
	    		return;
			};
			var param = {'schedule':{'id':record.get('id'),'remark':record.get('marketRemark'),'time':record.get('scheduleDate')}};
			var successFn = function(json){
				MessageUtil.showInfoMes(i18n('i18n.businessOpportunity.updateBoSuccessSchedule'));//修改商机日程成功
				me.up().down('grid').getStore().loadPage(1);
			};
			var failureFn = function(json){
			    MessageUtil.showErrorMes(json.message);
			};
			businessOpportunityData.updateBoSchedule(param,successFn,failureFn);
		});
		me.plugins=rowEditing;
		this.callParent();
	}
});

/**
 * .
 * <p>
 * 商机日程新增window<br/>
 * </p>
 * 
 * @returns CRM.BO.ScheduleAdd.Window 商机日程新增window 的EXT对象
 * @author 张斌
 * @时间 2014-03-11
 */
Ext.define('CRM.BO.ScheduleAdd.Window',{
	extend:'PopWindow',
	width:250,
	height:160,
	title:i18n('i18n.businessOpportunity.scheduleAdd'),//商机日程新增界面
	parent:null,//他的上级元素
	closeAction:'hide',
	scheduleForm:null,//商机日程信息新增
	layout:{
       type:'vbox',
       align:'stretch'
   },
   listeners:{
	   	beforehide:function(win){
	   		win.scheduleForm.getForm().reset();
	   		win.scheduleForm.getForm().findField('time').setMaxValue(null);//清除数据
	   	},
	   	beforeshow:function(win){
	   		win.scheduleForm.getForm().findField('time').setMaxValue(win.parent.up().maxScheduleDate);
	   	}
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
			text:i18n('i18n.PotentialCustEditView.save'),//保存
			handler:function(btn){
                 //auth:李春雨 保存日程主题时去空格
                var theme = me.scheduleForm.getForm().findField('remark');
                theme.setValue(Ext.String.trim(theme.getValue()));
				if(me.scheduleForm.getForm().isValid()){
					var remark = me.scheduleForm.getForm().findField('remark').getValue();
					var time = me.scheduleForm.getForm().findField('time').getValue();
					var record=me.parent.up('window').businessOpportunity;
					var param = {'schedule':{'remark':remark,'time':time
						,'custId':record.customer.custId,'comeFormId':record.id
						,'comeForm':'BUSINESS','contactId':record.customer.mainLinkmanId
						,'type':'dev'}};
					var successFn = function(json){
						btn.enable();
						MessageUtil.showInfoMes(i18n('i18n.businessOpportunity.addBoSuccessSchedule'));//新增商机日程成功
						me.parent.up('window').businessOpportunityViewTab.schedulePanel.down('grid').getStore().loadPage(1);
						me.hide();
						
					};
					var failureFn = function(json){
						btn.enable();
					    MessageUtil.showErrorMes(json.message);
					};
					btn.disable();//按钮不可用，防止快速点击
					businessOpportunityData.addBoSchedule(param,successFn,failureFn);
				}
			}
		},{
			xtype:'button',
			text:i18n('i18n.memberView.cancel'),//取消
			handler:function(){
				me.hide();
			}
		}];
	},
	getItems:function(){
		var me = this;
		me.scheduleForm = Ext.create('CRM.BO.ScheduleAdd.ScheduleForm');
		return [me.scheduleForm];
	}
});

/**
 * .
 * <p>
 * 商机日程新增FORM<br/>
 * </p>
 * @returns CRM.BO.ScheduleAdd.ScheduleForm 商机日程新增FORM 的EXT对象
 * @author 张斌
 * @时间 2014-03-21
 */
Ext.define('CRM.BO.ScheduleAdd.ScheduleForm', {
		extend:'SearchFormPanel',
		height:70,
		 defaultType: 'textfield',
         layout:{
			type:'table',
			columns:1
		 },
		defaults:{
			enableKeyEvents:true,
			labelSeparator:'',
			labelWidth:50,
			width:200
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
			return [{
		                xtype: 'textfield',
	                    name: 'remark',
	                    maxLength:30,//最大30
	                    allowBlank:false,
	                    fieldLabel: i18n('i18n.businessOpportunity.scheduleMain')//日程主题
	                },{
		                xtype     : 'datefield',
	                    name      : 'time',
	                    minValue:new Date(),
	                    allowBlank:false,
		                fieldLabel: i18n('i18n.businessOpportunity.scheduleDate'),//日程时间
		                editable:false,
		                format: 'Y-m-d'
		            }];
		},
		initComponent:function()
		{
			var me = this;
			me.items = me.getItems();
			this.callParent();
		}
});


/**
 * .
 * <p>
 * 回访日程界面WIN<br/>
 * </p>
 * @returns CRM.BO.BusinessOpportunity.DevelopPopWindow 回访日程界面win 的EXT对象
 * @author 张斌
 * @时间 2014-03-21
 */
Ext.define('CRM.BO.BusinessOpportunity.DevelopPopWindow',{
	extend:'PopWindow',
	id:'CreateDevelopPopWindowId',
	width:820,
	height:750,
	modal:true,
	layout:'fit',
	closeAction:'hide',
	items:[Ext.create('CustomerReturnVisitPanel')],
	listeners:{
		hide:function(){
			document.body.scrollLeft=0;
			document.body.scrollTop=0;
			document.getElementsByTagName("html")[0].style.overflowY="hidden";
			document.getElementsByTagName("html")[0].style.overflowX="hidden";
		}
	}
});