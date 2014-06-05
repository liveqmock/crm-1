//----------------------------------------------------------开发阶段管理--------------------------------------------------------------------------
/**
 * .
 * <p>
 * 客户修改window<br/>
 * </p>
 * 
 * @returns CRM.BO.DevelopmentStageView.Window 客户修改window 的EXT对象
 * @author 张斌
 * @时间 2014-03-11
 */
Ext.define('CRM.BO.DevelopmentStageView.Window',{
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
			text:i18n('i18n.memberView.cancel'),//取消
			handler:function(){
				me.hide();
			}
		}];
	},
	getItems:function(){
		var me = this;
		me.custInfoPanel = Ext.create('CRM.BO.DevelopmentStageView.CustInfoPanel');
		return [me.custInfoPanel];
	},
	//重置客户信息
	resetCust:function(){
		var me = this;
		me.custInfoPanel.custInfoForm.getForm().reset();
		me.custInfoPanel.businessInfoForm.getForm().reset();
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
				me.custInfoPanel.businessInfoForm.getForm().findField('coopIntention').setValue(DpUtil.rendererDictionary(member.memberExtend.coopIntention,DataDictionary.COOPERATION_INTENTION));
				me.custInfoPanel.businessInfoForm.getForm().findField('volumePotential').setValue(DpUtil.rendererDictionary(member.memberExtend.volumePotential,DataDictionary.CARGO_POTENTIAL));
				me.custInfoPanel.custInfoForm.getForm().findField('developmentPhase').setValue(DpUtil.rendererDictionary(member.memberExtend.developmentPhase,DataDictionary.DEVELOP_STAGE));
				if(member.memberExtend.isContinueMark=='1'){//继续营销
					me.custInfoPanel.businessInfoForm.getForm().findField('isContinueMark').setValue(i18n('i18n.memberView.yes'));
				}else if(member.memberExtend.isContinueMark=='0'){
					me.custInfoPanel.businessInfoForm.getForm().findField('isContinueMark').setValue(i18n('i18n.memberView.no'));
				}
				me.custInfoPanel.businessInfoForm.getForm().findField('accordMark').setValue(member.memberExtend.accordMark);
				if(member.memberExtend.isAccord=='1'){//适合我司承运
					me.custInfoPanel.businessInfoForm.getForm().findField('isAccord').setValue(i18n('i18n.memberView.yes'));
				}else if(member.memberExtend.isAccord=='0'){
					me.custInfoPanel.businessInfoForm.getForm().findField('isAccord').setValue(i18n('i18n.memberView.no'));
				}
				me.custInfoPanel.businessInfoForm.getForm().findField('recentGoods').setValue(member.memberExtend.recentGoods);
				me.custInfoPanel.businessInfoForm.getForm().findField('custNeed').setValue(member.memberExtend.custNeed);
				me.custInfoPanel.businessInfoForm.getForm().findField('otherRecentCoop').setValue(member.memberExtend.otherRecentCoop);
				if(!Ext.isEmpty(member.memberExtend.recentCoop)){
					var recentCoopArray = member.memberExtend.recentCoop.split(',');
					var recentCoopName = '';
					//获取合作物流公司名称
					for(var  i= 0;i<recentCoopArray.length;i++){
						if(i==0){
							recentCoopName = DpUtil.rendererDictionary(recentCoopArray[i],DataDictionary.COOPERATION_LOGISTICS);
			    		}else{
			    			recentCoopName = recentCoopName+','+DpUtil.rendererDictionary(recentCoopArray[i],DataDictionary.COOPERATION_LOGISTICS);
			    		}
					}
					me.custInfoPanel.businessInfoForm.getForm().findField('recentCoop').setValue(recentCoopName);
				}
			}
			
		}
	}
});
/**.
 * <p>
 * 客户业务信息修改FORM</br>
 * </p>
 * @returns CRM.BO.DevelopmentStageView.CustInfoPanel 客户业务信息修改FORM
 * @author  张斌
 * @时间    2014-03-21
 */
Ext.define('CRM.BO.DevelopmentStageView.CustInfoPanel', {
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
    	me.custInfoForm = Ext.create('CRM.BO.DevelopmentStageView.CustInfoForm');
    	me.businessInfoForm = Ext.create('CRM.BO.DevelopmentStageView.BusinessInfoForm');
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
 * @returns CRM.BO.DevelopmentStageView.CustInfoForm 商机基础信息FORM
 * @author 张斌
 * @时间 2014-03-12
 */
Ext.define('CRM.BO.DevelopmentStageView.CustInfoForm', {
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
 * @returns CRM.BO.DevelopmentStageView.BusinessInfoForm 客户业务信息FORM
 * @author 张斌
 * @时间 2014-03-12
 */
Ext.define('CRM.BO.DevelopmentStageView.BusinessInfoForm', {
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
			return  [{xtype: 'basicfiledset',
				    title: i18n('i18n.ScatterCustManagerView.scatterBusinessInfo'),//业务信息
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
 	                    colspan: 2,
				    	name: 'volumePotential',
				    	fieldLabel: i18n('i18n.developSchedule.sendGoodsPontential')//货量潜力
				    },{
 	                    colspan: 2,
				    	name: 'coopIntention',
				    	fieldLabel: i18n('i18n.PotentialCustManagerView.coopIntention')//合作意向
				    },{
		                colspan: 2,
		                fieldLabel:i18n('i18n.developPlan.continueMark'),//是否营销
		                name:'isContinueMark'
				    },{
		                colspan: 2,
		                fieldLabel:i18n('i18n.businessOpportunity.accord'),//适合我司承运
		                name:'isAccord'

				    },{
				    	name: 'accordMark',
				    	fieldLabel: i18n('i18n.returnVisit.remark'),//备注
				    	colspan: 4,
	                    width:460
				    },{
				    	name: 'recentGoods',
				    	xtype:'textarea',
				    	fieldLabel: i18n('i18n.businessOpportunity.recentShipmentStatus'),//最近走货情况
				    	colspan: 3,
	                    width:345,
	                    height:100
				    },{
				    	name: 'custNeed',
				    	xtype:'textarea',
				    	fieldLabel: i18n('i18n.PotentialCustEditView.custNeed'),//客户需求
				    	colspan: 3,
				    	width:345,
	                    height:100
				    },{
 	                    colspan: 4,
 	                    width:460,
 	                    fieldLabel:     i18n('i18n.returnVisit.companyId'),//合作物流公司
 	                    name:'recentCoop'
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