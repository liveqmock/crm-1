/**
* 会员降列表界面
*/
var memberDownGradeDataControl = (CONFIG.get("TEST"))? new MemberDownGradeDataTest():new MemberDownGradeData();
//var memberDownGradeDataControl = new MemberDownGradeData();
Ext.define('MemberDownGradePanel',{
	extend:'BasicPanel',
	memberDownGradeSearchCondition:null,      //查询条件
	memberDownGradeResultInfo:null,  //会员降级客户
	memberDownGradeButtonPanel:null,  //会员按钮面板
	memberDownGradeData:null,//数据Data
	
	layout:{
        type:'vbox',
        align:'stretch'
    },
	items:null,
	initComponent:function(){
		var me = this;
		var record = new UpgradeConditionModel();
		me.memberDownGradeSearchCondition = Ext.create('MemberDownGradeSearchForm',{'memberDownGradeData':me.memberDownGradeData,'record':record,'parent':me});
		me.memberDownGradeResultInfo = Ext.create('MemberDownGradeResultGrid',{'memberDownGradeData':me.memberDownGradeData,'parent':me});
		me.memberDownGradeButtonPanel = Ext.create('MemberDownGradeButtonPanel',{'memberDownGradeData':me.memberDownGradeData,'memberDownGradeSearchForm':me.memberDownGradeSearchCondition,
																			   'parent':me});
		me.items = [{
			xtype:'basicpanel',
			height:110,
			items:[me.memberDownGradeSearchCondition]
		},{
			xtype:'basicpanel',
			height:36,
			items:[me.memberDownGradeButtonPanel]
		},{
			xtype:'basicpanel',
			flex:1,
			items:[me.memberDownGradeResultInfo]
		}];
		this.callParent();
		record.set('statisticsTime',new Date());
		me.memberDownGradeSearchCondition.loadRecord(record);
		//默认部门
		DpUtil.defaultDept(me.memberDownGradeSearchCondition,'dept');
	}
});

/**
 * 操作按钮面板
 */
Ext.define('MemberDownGradeButtonPanel',{
	extend:'NormalButtonPanel',
	memberDownGradeSearchForm:null,
	memberDownGradeData:null,   //data层接口
	parent:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{

			xtype:'leftbuttonpanel',
			width:150,
			items:[{
				xtype:'button',
				text:'客户360视图',
				scope:me,
				handler:function(){
					var me = this;
					var grid = me.parent.memberDownGradeResultInfo;
					var selection=grid.getSelectionModel().getSelection();
					if (selection.length != 1) {
						MessageUtil.showMessage( i18n('i18n.PotentialCustManagerView.operation_message'));
						return;
					}
					CustviewUtil.openSimpleCustview(selection[0].get('custNumber'));
				}
			}]
		},{
			xtype:'middlebuttonpanel'
		},{
			xtype:'rightbuttonpanel',
			width:150,
			items:[{
				xtype:'button',
				text:i18n('i18n.PotentialCustManagerView.search'),
				scope:me,
				handler:me.searchMemberDownGradeList
				}]
		}];
	},
	//会员降级列表查询
	searchMemberDownGradeList:function(){
		var me = this;
		var form = me.memberDownGradeSearchForm;
		if(!me.validateCondition()){
			MessageUtil.showMessage(i18n('i18n.MemberUpgradeView.message_notAllNull'));
			return;
		}
		if(!form.getForm().isValid()){
			MessageUtil.showMessage(i18n('i18n.MemberUpgradeView.message_checkCondition'));
			return;
		}
		me.memberDownGradeData.getMemberDownGradeCustStore().loadPage(1);
	},
	//校验是否查询条件都为空
	validateCondition:function(){
		var me = this;
		var form = me.memberDownGradeSearchForm;
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
 * 会员降级查询条件
 */
Ext.define('MemberDownGradeSearchForm',{
	extend:'SearchFormPanel',
	id:'memberDownGradeSearchFormId',
	items:null,
	layout:{
		type:'table',
		columns:3
	},
	defaultType:'dptextfield',
	border:false,
	record:null,
	memberDownGradeData:null,
	parent:null,
	initComponent:function(){
		var me = this;
		me.defaults = me.getDefaultsContainer();
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'belongdeptcombox',
			functionName :'MemberFunction',
			fieldLabel:i18n('i18n.ScatterUpgradeView.belongdeptName')
		},{
			fieldLabel:i18n('i18n.ScatterUpgradeView.statisticalDate'),
			xtype : 'datefield',
			format : 'Y',
			name : 'statisticsTime',
	        maxValue: new Date(),
	        allowBlank:false
		},{
			fieldLabel:i18n('i18n.PotentialCustManagerView.customerName'),
			maxLength:10,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',10),
			name:'custName'
		},{
			fieldLabel:i18n('i18n.PotentialCustManagerView.contactName'),
			maxLength:10,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',10),
			name:'contactName'
		},{
			fieldLabel:i18n('i18n.PotentialCustManagerView.contactPhone'),
			name:'phone',
			regex : DpUtil.linkWayLimit('M')
		},{
			fieldLabel:i18n('i18n.PotentialCustManagerView.contactTel'),
			name:'tel',
			regexText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',15),
			regex : DpUtil.linkWayLimit('T')
		},{
			fieldLabel:i18n('i18n.ScatterUpgradeView.nowLevel'),
			xtype : 'combobox',
			name:'nowLevel',
			store:me.memberDownGradeData.getMemberGradeStore(),
			queryMode:'local',
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code'
		},{
			fieldLabel:i18n('i18n.ScatterUpgradeView.targetLevel'),
			xtype : 'combobox',
			name:'targetLevel',
			store:me.memberDownGradeData.getTargetGradDownStore(),
			queryMode:'local',
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code'
		}];
	},
	//增加监听事件
	getDefaultsContainer:function(){
		var me = this;
		return {
			labelWidth:80,
			width:235,
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
    		me.parent.memberDownGradeButtonPanel.searchMemberDownGradeList;
    	}
	},
	//监听change事件
	changeEvent:function(field,newValue){
		var me = this;
		//如果是数据字典或所属部门组件 则显示只选
		if(('belongdeptcombox' == field.getXType() || 'combobox' == field.getXType()) 
			&& Ext.isEmpty(newValue)){
			field.setValue(null);
		}else{
			if(field.getName()=='phone'){
				DpUtil.autoChangeMobileLength(field,newValue);
			}
		}
	}
});

/**
 * 会员降级结果
 */
Ext.define('MemberDownGradeResultGrid',{
	extend:'SearchGridPanel',
	parent:null,
	memberDownGradeData:null,
	initComponent:function(){
		var me = this;
		me.store=me.memberDownGradeData.initMemberDownGradeCustStore(me.beforeLoadMemberFn);
		me.columns = me.getColumns();
		me.dockedItems = me.getMyDockedItems();
		this.callParent();
	},
	getColumns:function(){
		var me = this;
		return [{
			header:i18n('i18n.ScatterUpgradeView.belongdeptId'),
			hidden : true,
			dataIndex:'belongDeptId'
		},{
			header:i18n('i18n.ScatterUpgradeView.belongdeptName'),
			dataIndex:'belongDeptName'
		},{
			header:i18n('i18n.MemberCustEditView.custNo'),
			dataIndex : 'custNumber'
		},{
			header:i18n('i18n.PotentialCustManagerView.customerName'),
			dataIndex : 'custName'
		},{
			header:i18n('i18n.PotentialCustManagerView.contactName'),
			dataIndex : 'linkName'
		},{
			header:i18n('i18n.MemberCustEditView.mobileNo'),
			dataIndex : 'linkPhone'
		},{
			header : i18n('i18n.MemberCustEditView.telephoneNo'),
			dataIndex : 'linkTel'
		},{
			header : i18n('i18n.ScatterUpgradeView.nowLevel'),
			dataIndex : 'currentLevel',
			renderer  : function(value){
				return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.MEMBER_GRADE);
			}
		}, {
			header : i18n('i18n.ScatterUpgradeView.targetLevel'),
			dataIndex : 'targetLevel',
			renderer  : function(value){
				return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.TARGET_GRAD_DOWN);
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
	},
	//beforeLoad方法
	beforeLoadMemberFn:function(store, operation, eOpts){
		var memberDownGradeSearchForm = Ext.getCmp('memberDownGradeSearchFormId');
		if(memberDownGradeSearchForm!=null){
			memberDownGradeSearchForm.getForm().updateRecord(memberDownGradeSearchForm.record);
			//设置请求参数
			var searchCustomerCondition = memberDownGradeSearchForm.record.data;
			var searchParams = { 
					//所属部门ID
					'memberDownGradeCondition.dept': searchCustomerCondition.dept,
					//统计时间
					'memberDownGradeCondition.statisticsTime':searchCustomerCondition.statisticsTime,
					//客户名称
					'memberDownGradeCondition.custName':searchCustomerCondition.custName,
					//联系人姓名
					'memberDownGradeCondition.contactName':searchCustomerCondition.contactName,
					//联系人手机
					'memberDownGradeCondition.phone':searchCustomerCondition.phone,
					//联系人电话
					'memberDownGradeCondition.tel':searchCustomerCondition.tel,
					//目前级别
					'memberDownGradeCondition.nowLevel':searchCustomerCondition.nowLevel,
					//目标级别
					'memberDownGradeCondition.targetLevel':searchCustomerCondition.targetLevel};
			Ext.apply(operation,{
				params : searchParams
				}
			);	
		}
	}
});

Ext.onReady(function(){
	var params = [  // 目前级别,客户等级,会员等级,目标级别
          			'MEMBER_GRADE',
	          		//目标级别(会员降级列表)
	          		'TARGET_GRAD_DOWN'];
	initDataDictionary(params);//数据字典
	Ext.create('Ext.container.Viewport',{
		layout:'fit',
		items:[Ext.create('MemberDownGradePanel',{'memberDownGradeData':memberDownGradeDataControl})]
	});
//	var params = {'dataDictionaryKeys' : [
//	  			          			// 目前级别,客户等级,会员等级,目标级别
//	  			          			'MEMBER_GRADE',
//		  			          		//目标级别(会员降级列表)
//		  			          		'TARGET_GRAD_DOWN']};
//	var dictionarySuccess = function(response){
//		DataDictionary = response.dataDictionary;
//		Ext.create('Ext.container.Viewport',{
//			layout:'fit',
//			items:[Ext.create('MemberDownGradePanel',{'memberDownGradeData':memberDownGradeDataControl})]
//		});
//	};
//	var dictionaryFail = function(response){
//		Ext.Msg.alert(i18n('i18n.PotentialCustManagerView.messageTip'),response.message);
//	};
//	memberDownGradeDataControl.getDictionary(params,dictionarySuccess,dictionaryFail);
});