//----------------------------------------------------------开发阶段管理--------------------------------------------------------------------------
/**
 * .
 * <p>
 * 客户修改window<br/>
 * </p>
 * 
 * @returns CRM.BO.DevelopmentStageUpdate.Window 客户修改window 的EXT对象
 * @author 张斌
 * @时间 2014-03-11
 */
Ext.define('CRM.BO.DevelopmentStageUpdate.Window',{
	extend:'PopWindow',
	width:790,
	height:450,
	title:i18n('i18n.businessOpportunity.custUpdate'),//客户业务信息修改界面
	parent:null,//他的上级元素
	cust:null,//客户
	custInfoPanel:null,//界面元素
	closeAction:'hide',
	member:null,//客户信息
	layout:{
       type:'vbox',
       align:'stretch'
   },
   listeners:{
   	beforehide:function(win){
   		win.resetCust();
   	},
   	beforeshow:function(win){
   		win.loadCust(win.member);
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
				me.updateCust(btn);

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
		me.custInfoPanel = Ext.create('CRM.BO.DevelopmentStageUpdate.CustInfoPanel');
		return [me.custInfoPanel];
	},
	//修改商机信息
	updateCust:function(btn){
		var me= this;
		var memberExtend = new CRM.ME.MemberExtend(me.member.memberExtend);
		me.custInfoPanel.businessInfoForm.getForm().updateRecord(memberExtend);
		memberExtend.set('custId',me.member.id);//设置客户ID
		if(!me.custInfoPanel.businessInfoForm.getForm().isValid()){
			return;
		}
		//组装合作物流公司
    	var recentCoopArray = me.custInfoPanel.businessInfoForm.getForm().findField('recentCoop').getValue();
    	var recentCoop = '';
    	for(var i=0;i<recentCoopArray.length;i++){
    		if(i==0){
    			recentCoop = recentCoopArray[i];
    		}else{
    			recentCoop = recentCoop+','+recentCoopArray[i];
    		}
    	}
    	memberExtend.set('recentCoop',recentCoop);
		var param = {'memberExtend':memberExtend.data};
		var successFn = function(json){
		    btn.enable();
			MessageUtil.showInfoMes(i18n('i18n.businessOpportunity.updateCustBusInfoSuccess'));//修改客户业务信息成功
			me.parent.searchCust();
			me.hide();
		};
		var failureFn = function(json){
		    btn.enable();
		    MessageUtil.showErrorMes(json.message);
		};
		btn.disable();
		memberExtendData.updateMemberExtend(param,successFn,failureFn);
	},
	//重置客户信息
	resetCust:function(){
		var me = this;
		me.custInfoPanel.custInfoForm.getForm().reset();
		me.custInfoPanel.businessInfoForm.getForm().reset();
		me.custInfoPanel.businessInfoForm.getForm().findField('accordMark').allowBlank = true;
		me.custInfoPanel.businessInfoForm.getForm().findField('otherRecentCoop').allowBlank = true;
		me.custInfoPanel.businessInfoForm.getForm().findField('coopIntention').setEditable(true);//合作意向
		me.custInfoPanel.businessInfoForm.getForm().findField('coopIntention').setReadOnly(false);//合作意向
		me.custInfoPanel.businessInfoForm.getForm().findField('coopIntention').removeCls('readonly');
		me.custInfoPanel.businessInfoForm.getForm().findField('volumePotential').setEditable(true);//货量潜力
		me.custInfoPanel.businessInfoForm.getForm().findField('isAccord').setEditable(true);//适合我司承运
		me.custInfoPanel.businessInfoForm.getForm().findField('isAccord').setReadOnly(false);//适合我司承运
		me.custInfoPanel.businessInfoForm.getForm().findField('isAccord').removeCls('readonly');
		me.member = null;
	},
	//加载客户信息
	loadCust:function(member){
		var me = this;
		if(Ext.isEmpty(member)){
			
		}else{
			me.custInfoPanel.custInfoForm.getForm().findField('custNumber').setValue(member.custNumber);
			me.custInfoPanel.custInfoForm.getForm().findField('deptName').setValue(member.deptName);
			me.custInfoPanel.custInfoForm.getForm().findField('custName').setValue(member.custName);
			me.custInfoPanel.custInfoForm.getForm().findField('custGroup').setValue(DpUtil.rendererDictionary(member.custGroup,DataDictionary.CUST_TYPE));
			me.custInfoPanel.custInfoForm.getForm().findField('tradeId').setValue(DpUtil.rendererDictionary(member.tradeId,DataDictionary.TRADE));
			me.custInfoPanel.custInfoForm.getForm().findField('secondTrade').setValue(DpUtil.rendererDictionary(member.secondTrade,DataDictionary.SECOND_TRADE));
			me.custInfoPanel.custInfoForm.getForm().findField('potenSource').setValue(DpUtil.rendererDictionary(member.potenSource,DataDictionary.CUST_SOURCE));
			if(!Ext.isEmpty(member.mainContact)){
				me.custInfoPanel.custInfoForm.getForm().findField('linkManName').setValue(member.mainContact.name);
				me.custInfoPanel.custInfoForm.getForm().findField('linkManMobeilPhone').setValue(member.mainContact.mobilePhone);
				me.custInfoPanel.custInfoForm.getForm().findField('linkManTelePhone').setValue(member.mainContact.telPhone);
			}
			if(!Ext.isEmpty(member.memberExtend)){
				//7、	合作意向：一旦填充该字段，并保存成功，则不可删除，只能修改；若合作意向填充为高，并保存成功，则该字段不能编辑；
				me.custInfoPanel.businessInfoForm.getForm().findField('coopIntention').setValue(member.memberExtend.coopIntention);
				if(!Ext.isEmpty(member.memberExtend.coopIntention)){
					me.custInfoPanel.businessInfoForm.getForm().findField('coopIntention').setEditable(false);
				}
				if(!Ext.isEmpty(member.memberExtend.coopIntention)&&member.memberExtend.coopIntention=='HIGH'){
					me.custInfoPanel.businessInfoForm.getForm().findField('coopIntention').setReadOnly(true);//合作意向
					me.custInfoPanel.businessInfoForm.getForm().findField('coopIntention').addCls('readonly');
				}
				//6、	货量潜力：一旦填充该字段，并保存成功，则不可删除，只能修改；
				me.custInfoPanel.businessInfoForm.getForm().findField('volumePotential').setValue(member.memberExtend.volumePotential);
				if(!Ext.isEmpty(member.memberExtend.volumePotential)){
					me.custInfoPanel.businessInfoForm.getForm().findField('volumePotential').setEditable(false);
				}
				me.custInfoPanel.custInfoForm.getForm().findField('developmentPhase').setValue(DpUtil.rendererDictionary(member.memberExtend.developmentPhase,DataDictionary.DEVELOP_STAGE));
				me.custInfoPanel.businessInfoForm.getForm().findField('isContinueMark').setValue(member.memberExtend.isContinueMark);
				me.custInfoPanel.businessInfoForm.getForm().findField('accordMark').setValue(member.memberExtend.accordMark);
				//8、	适合我司承运：一旦填充该字段，并保存成功，则不可删除，只能修改；若填充该字段为是，并保存成功，则该字段不能编辑；若该字段填充为否，必须填充备注字段，才可保存成功；
				me.custInfoPanel.businessInfoForm.getForm().findField('isAccord').setValue(member.memberExtend.isAccord);
				if(!Ext.isEmpty(member.memberExtend.isAccord)){
					me.custInfoPanel.businessInfoForm.getForm().findField('isAccord').setEditable(false);
				}
				if(!Ext.isEmpty(member.memberExtend.isAccord)&&member.memberExtend.isAccord=='1'){
					me.custInfoPanel.businessInfoForm.getForm().findField('isAccord').setReadOnly(true);
					me.custInfoPanel.businessInfoForm.getForm().findField('isAccord').addCls('readonly');
				}
				if(!Ext.isEmpty(member.memberExtend.isAccord)&&member.memberExtend.isAccord=='0'){
					me.custInfoPanel.businessInfoForm.getForm().findField('accordMark').allowBlank = false;
				}
				me.custInfoPanel.businessInfoForm.getForm().findField('recentGoods').setValue(member.memberExtend.recentGoods);
				me.custInfoPanel.businessInfoForm.getForm().findField('custNeed').setValue(member.memberExtend.custNeed);
				me.custInfoPanel.businessInfoForm.getForm().findField('otherRecentCoop').setValue(member.memberExtend.otherRecentCoop);
				if(!Ext.isEmpty(member.memberExtend.recentCoop)){
					var recentCoopArray = member.memberExtend.recentCoop.split(',');
					me.custInfoPanel.businessInfoForm.getForm().findField('recentCoop').setValue(recentCoopArray);
					for(var  i= 0;i<recentCoopArray.length;i++){
						if(recentCoopArray[i]=='OTHER'){
							me.custInfoPanel.businessInfoForm.getForm().findField('otherRecentCoop').allowBlank = false;
						}
					}
				}
			}
			
		}
	}
});
/**.
 * <p>
 * 客户业务信息修改FORM</br>
 * </p>
 * @returns CRM.BO.DevelopmentStageUpdate.CustInfoPanel 客户业务信息修改FORM
 * @author  张斌
 * @时间    2014-03-21
 */
Ext.define('CRM.BO.DevelopmentStageUpdate.CustInfoPanel', {
	extend:'BasicFormPanel',
	height:495,
	autoScroll : true,
	border:false,
    layout:{
        type:'vbox',
        align:'stretch'
    },
	custInfoForm:null,//客户信息form
	businessInfoForm:null,//业务信息form
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
    	return  [me.custInfoForm,me.businessInfoForm];
    },
    initComponent:function(){
    	var me = this;
    	me.custInfoForm = Ext.create('CRM.BO.DevelopmentStageUpdate.CustInfoForm');
    	me.businessInfoForm = Ext.create('CRM.BO.DevelopmentStageUpdate.BusinessInfoForm');
    	me.items = me.getItems();
    	this.callParent();
    }

});
/**
 * .
 * <p>
 * 客户基础信息FORM</br>
 * </p>
 * 
 * @returns CRM.BO.DevelopmentStageUpdate.CustInfoForm 商机基础信息FORM
 * @author 张斌
 * @时间 2014-03-12
 */
Ext.define('CRM.BO.DevelopmentStageUpdate.CustInfoForm', {
		extend:'TitleFormPanel',
		height:115,
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
				    title: i18n('i18n.SearchMember.memberInfo'),//客户信息
				    defaults:{
				    	labelSeparator:'',
						labelWidth:80,
						width:180,
				        xtype: 'textfield',
				        readOnly:true
				    },
				    layout:{
				    	type:'table',
				    	columns:4
				    },
				    items:[{
				    	name: 'custNumber',
				    	fieldLabel: i18n('i18n.businessOpportunity.custNumber')//客户编号
				    },{
				    	name: 'deptName',
				    	fieldLabel: i18n('i18n.ScatterUpgradeView.belongdeptName')//所属部门
				    },{
				    	name: 'custName',
				    	fieldLabel: i18n('i18n.businessOpportunity.customerName')//客户名称
				    },{
				    	name: 'custGroup',
				    	fieldLabel: i18n('i18n.PotentialCustManagerView.custType')//客户类型
				    },{
				    	name: 'tradeId',
				    	fieldLabel: i18n('i18n.MemberCustEditView.custIndustry')//客户行业
				    },{
				    	name: 'secondTrade',
				    	fieldLabel: i18n('i18n.PotentialCustManagerView.secondTrade')//二级行业
				    },{
				    	name: 'potenSource',
				    	fieldLabel: i18n('i18n.PotentialCustManagerView.potentialCustSource')//潜客来源
				    },{
				    	name: 'developmentPhase',
				    	fieldLabel: i18n('i18n.businessOpportunity.KFstep')//开发阶段
				    },{
				    	name: 'linkManName',
				    	fieldLabel: i18n('i18n.developSchedule.linkManName')//联系人姓名
				    },{
				    	name: 'linkManMobeilPhone',
				    	fieldLabel: i18n('i18n.developSchedule.linkManMobeilPhone')//联系人手机
				    },{
				    	name: 'linkManTelePhone',
				    	fieldLabel: i18n('i18n.developSchedule.linkManTelePhone')//联系人电话
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
 * 客户业务信息FORM</br>
 * </p>
 * 
 * @returns CRM.BO.DevelopmentStageUpdate.BusinessInfoForm 客户业务信息FORM
 * @author 张斌
 * @时间 2014-03-12
 */
Ext.define('CRM.BO.DevelopmentStageUpdate.BusinessInfoForm', {
		extend:'TitleFormPanel',
		height:250,
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
			return  [{xtype: 'basicfiledset',
				    title: i18n('i18n.ScatterCustManagerView.scatterBusinessInfo'),//业务信息
				    defaults:{
				    	labelSeparator:'',
						labelWidth:100,
						width:230,
				        xtype: 'textfield'
				    },
				    layout:{
				    	type:'table',
				    	columns:6
				    },
				    items:[{
				    	xtype:          'combo',
 	                    mode:           'local',
 	                    triggerAction:  'all',
 	                    //editable:false,
 	                    colspan: 2,
 	                    forceSelection: true,
 	                    displayField:   'codeDesc',
 	                    valueField:     'code',
 	                    queryMode: 'local',
 	                    store:getDataDictionaryByName(DataDictionary,'CARGO_POTENTIAL'),
				    	name: 'volumePotential',
				    	fieldLabel: i18n('i18n.developSchedule.sendGoodsPontential'),//货量潜力
				    	listeners:{
				    		change:function(com){
				    			if(Ext.isEmpty(com.getRawValue())){
				    				com.setValue('');
				    			}
				    		}
				    	}
				    },{
				    	xtype:          'combo',
 	                    mode:           'local',
 	                    colspan: 2,
 	                    triggerAction:  'all',
 	                    //editable:false,
 	                    forceSelection: true,
 	                    displayField:   'codeDesc',
 	                    valueField:     'code',
 	                    queryMode: 'local',
 	                    store:getDataDictionaryByName(DataDictionary,'COOPERATION_INTENTION'),
				    	name: 'coopIntention',
				    	fieldLabel: i18n('i18n.PotentialCustManagerView.coopIntention'),//合作意向
				    	listeners:{
				    		change:function(com){
				    			if(Ext.isEmpty(com.getRawValue())){
				    				com.setValue('');
				    			}
				    		}
				    	}
				    },{
				    	xtype:          'combo',
		                mode:           'local',
		                colspan: 2,
		                triggerAction:  'all',
		                fieldLabel:i18n('i18n.developPlan.continueMark'),//是否营销
		                name:'isContinueMark',
						displayField:'name',
						valueField:'value',
						forceSelection :true,
						editable:false,
		                store:Ext.create('Ext.data.Store', {
		                    fields : ['name', 'value'],
		                    data   : [
		                        {value : '0',   name: i18n('i18n.memberView.no')},
		                        {value : '1',  name:i18n('i18n.memberView.yes')}
		                    ]
		                })
				    },{
				    	xtype:          'combo',
		                mode:           'local',
		                colspan: 2,
		                triggerAction:  'all',
		                fieldLabel:i18n('i18n.businessOpportunity.accord'),//适合我司承运
		                name:'isAccord',
						displayField:'name',
						valueField:'value',
						forceSelection :true,
						//editable:false,
		                store:Ext.create('Ext.data.Store', {
		                    fields : ['name', 'value'],
		                    data   : [
		                        {value : '0',   name: i18n('i18n.memberView.no')},
		                        {value : '1',  name:i18n('i18n.memberView.yes')}
		                    ]
		                }),
		                listeners:{
 	                    	select:function(combo,records,eOpts){
                    			if(records[0].get('value')=='0'){
                    				combo.up('form').getForm().findField('accordMark').allowBlank = false;
                    			}else{
                    				combo.up('form').getForm().findField('accordMark').allowBlank = true;
                    			}
 	                    	},
 	                    	change:function(com){
				    			if(Ext.isEmpty(com.getRawValue())){
				    				com.setValue('');
				    				com.up('form').getForm().findField('accordMark').allowBlank = true;
				    			}
				    		}
 	                    }
				    },{
				    	name: 'accordMark',
				    	fieldLabel: i18n('i18n.returnVisit.remark'),//备注
				    	maxLength:50,
				    	colspan: 4,
				    	maxLength:200,
	                    width:460
				    },{
				    	name: 'recentGoods',
				    	xtype:'textarea',
				    	fieldLabel: i18n('i18n.businessOpportunity.recentShipmentStatus'),//最近走货情况
				    	maxLength:200,
				    	colspan: 3,
	                    width:345,
	                    height:100
				    },{
				    	name: 'custNeed',
				    	xtype:'textarea',
				    	fieldLabel: i18n('i18n.PotentialCustEditView.custNeed'),//客户需求
				    	maxLength:200,
				    	colspan: 3,
				    	width:345,
	                    height:100
				    },{
 	                    xtype:          'combo',
 	                    mode:           'local',
 	                    colspan: 4,
 	                    width:460,
 	                    triggerAction:  'all',
 	                    editable:false,
 	                    multiSelect:true,
 	                    forceSelection: true,
 	                    fieldLabel:     i18n('i18n.returnVisit.companyId'),//合作物流公司
 	                    name:'recentCoop',
 	                    displayField:   'codeDesc',
 	                    valueField:     'code',
 	                    queryMode: 'local',
 	                    store:getDataDictionaryByName(DataDictionary,'COOPERATION_LOGISTICS'),
 	                   listeners:{
 	                    	select:function(combo,records,eOpts){
 	                    		var isOther = false;
 	                    		for(var i =0;i<records.length;i++){
 	                    			if(records[i].get('code')=='OTHER'){
 	                    				isOther = true;
 	                    				break;
 	                    			}
 	                    		}
 	                    		if(isOther){
 	                    			combo.up('form').getForm().findField('otherRecentCoop').allowBlank = false;
 	                    		}else{
 	                    			combo.up('form').getForm().findField('otherRecentCoop').allowBlank = true;
 	                    		}
 	                    	}
 	                    }
 	                },{
				    	name: 'otherRecentCoop',
				    	fieldLabel: i18n('i18n.returnVisit.remark'),//备注
				    	maxLength:100,
				    	labelWidth:50,
				    	colspan: 2
				    }]
				}]
		},
		initComponent:function(){
			var me = this;
		    me.items = me.getItems();
			this.callParent();
	  }
});