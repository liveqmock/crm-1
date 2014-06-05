/**
* 会员升级列表界面
*/
var memberUpgradeDataControl =  (CONFIG.get("TEST"))? new MemberUpgradeDataTest():new MemberUpgradeData();
//var memberUpgradeDataControl =  new MemberUpgradeData();
Ext.define('MemberUpgradePanel',{
	extend:'BasicPanel',
//	title:i18n('i18n.MemberUpgradeView.membershipUpgradeList'),
	memberUpgradeSearchCondition:null,      //查询条件
	memberUpgradeResultInfo:null,  //会员升级客户
	memberUpgradeButtonPanel:null,  //会员按钮面板
	memberUpgradeData:null,
	layout:{
        type:'vbox',
        align:'stretch'
    },
	initComponent:function(){
		var me = this;
		var record = new UpgradeConditionModel();
		me.memberUpgradeSearchCondition = Ext.create('MemberUpgradeSearchForm',{'parent':me,'memberUpgradeData':me.memberUpgradeData,'record':record});
		me.memberUpgradeResultInfo = Ext.create('MemberUpgradeResultGrid',{'parent':me,'memberUpgradeData':me.memberUpgradeData});
		me.memberUpgradeButtonPanel = Ext.create('MemberUpgradeButtonPanel',{'parent':me,'memberUpgradeData':me.memberUpgradeData});
		me.items = [{
			xtype:'basicpanel',
			height:110,
			items:[me.memberUpgradeSearchCondition]
		},{
			xtype:'basicpanel',
			height:36,
			items:[me.memberUpgradeButtonPanel]
		},{
			xtype:'basicpanel',
			flex:1,
			items:[me.memberUpgradeResultInfo]
		}];
		Ext.apply(me,{
			items:me.items
		});
		me.callParent();
		me.memberUpgradeSearchCondition.loadRecord(record);
		//默认部门
		DpUtil.defaultDept(me.memberUpgradeSearchCondition,'dept');
	}
});
/**
 * 散客升级查询条件
 */
Ext.define('MemberUpgradeSearchForm',{
	extend:'SearchFormPanel',
	id:'memberUpgradeSearchFormId',
	layout:{
		type:'table',
		columns:3
	},
	defaultType:'dptextfield',
	border:false,
	record:null,
	parent:null,
	memberUpgradeData:null,
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
		},
			{
			fieldLabel:i18n('i18n.ScatterUpgradeView.statisticalDate'),
			xtype : 'datefield',
			format : 'Y-m',
			name : 'statisticsTime',
	        maxValue: new Date()
		},{
			fieldLabel:i18n('i18n.PotentialCustManagerView.customerName'),
			maxLength:80,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80),
			name:'custName'
		},{
			fieldLabel:i18n('i18n.PotentialCustManagerView.contactName'),
			maxLength:80,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80),
			name:'contactName'
		},{
			fieldLabel:i18n('i18n.PotentialCustManagerView.contactPhone'),
			name:'phone',
			regex : DpUtil.linkWayLimit('M')
		},{
			fieldLabel:i18n('i18n.PotentialCustManagerView.contactTel'),
			name:'tel',
			regexText :i18n('i18n.ManualRewardIntegralEditView.m_maxLength',15),
			regex : DpUtil.linkWayLimit('T')
		},{
			fieldLabel:i18n('i18n.ScatterUpgradeView.nowLevel'),
			xtype : 'combobox',
			name:'nowLevel',
			store:me.memberUpgradeData.getNowGradUpStore(),
			queryMode:'local',
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code'
		},{
			fieldLabel:i18n('i18n.ScatterUpgradeView.targetLevel'),
			xtype : 'combobox',
			name:'targetLevel',
			store:me.memberUpgradeData.getTargetGradUpStore(),
			queryMode:'local',
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code'
		},{
			fieldLabel:i18n('i18n.ScatterCustManagerView.memberNo'),
			name:'custNumber'
		}];
	},
	//增加监听事件
	getDefaultsContainer:function(){
		var me = this;
		return {
			labelWidth : 80,
			width : 235,
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
    		me.parent.memberUpgradeButtonPanel.searchMemberUpgradeList();
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
 * 按钮面板
 */
Ext.define('MemberUpgradeButtonPanel',{
	extend:'NormalButtonPanel',
	parent:null,
	memberUpgradeData:null,
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
					var grid = me.parent.memberUpgradeResultInfo;
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
				handler:me.searchMemberUpgradeList
				}]
		}];
	},
	//会员升级列表查询
	searchMemberUpgradeList:function(){
		var me = this;
		var form = me.parent.memberUpgradeSearchCondition;
		if(!me.validateCondition()){
			MessageUtil.showMessage( i18n('i18n.MemberUpgradeView.message_notAllNull'));
			return;
		}
		if(!form.getForm().isValid()){
			MessageUtil.showMessage( i18n('i18n.MemberUpgradeView.message_checkCondition'));
			return;
		}
		me.memberUpgradeData.getMemberUpGradeCustStore().loadPage(1);
	},
	//校验是否查询条件都为空
	validateCondition:function(){
		var me = this;
		var form = me.parent.memberUpgradeSearchCondition;
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
 * 会员升级结果
 */
Ext.define('MemberUpgradeResultGrid',{
	extend:'SearchGridPanel',
	parent:null,
	memberUpgradeData:null,
	initComponent:function(){
		var me = this;
		me.store = me.memberUpgradeData.initMemberUpGradeCustStore(me.beforeLoadScatterFn);
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
			header : i18n('i18n.ScatterCustManagerView.memberNo'),
			dataIndex:'custNumber'
		},{
			header:i18n('i18n.ScatterUpgradeView.belongdeptName'),
			dataIndex:'belongDeptName'
		},{
			header:i18n('i18n.MemberCustEditView.custNo'),
			dataIndex:'custNumber'
		},{
			header:i18n('i18n.PotentialCustManagerView.customerName'),
			dataIndex:'custName'
		},{
			header:i18n('i18n.PotentialCustManagerView.contactName'),
			dataIndex:'linkName'
		},{
			header:i18n('i18n.MemberCustEditView.mobileNo'),
			dataIndex:'linkPhone'
		},{
			header : i18n('i18n.MemberCustEditView.telephoneNo'),
			dataIndex:'linkTel'
		},{
			header : i18n('i18n.ScatterUpgradeView.nowLevel'),
			dataIndex:'currentLevel',
			renderer  : function(value){
				return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.NOW_GRAD_UP);
			}
		},{
			header : i18n('i18n.ScatterUpgradeView.targetLevel'),
			dataIndex:'targetLevel',
			renderer  : function(value){
				return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.TARGET_GRAD_UP);
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
	beforeLoadScatterFn:function(store, operation, eOpts){
		var searchConditionForm = Ext.getCmp('memberUpgradeSearchFormId');
		if(searchConditionForm!=null){
			searchConditionForm.getForm().updateRecord(searchConditionForm.record);
			//设置请求参数
			var searchCustomerCondition = searchConditionForm.record.data;
			var searchParams = { 
					//所属部门ID
					'memberUpGradeCondition.dept': searchCustomerCondition.dept,
					//统计时间
					'memberUpGradeCondition.statisticsTime':searchCustomerCondition.statisticsTime,
					//客户名称
					'memberUpGradeCondition.custName':searchCustomerCondition.custName,
					//联系人姓名
					'memberUpGradeCondition.contactName':searchCustomerCondition.contactName,
					//联系人手机
					'memberUpGradeCondition.phone':searchCustomerCondition.phone,
					//联系人电话
					'memberUpGradeCondition.tel':searchCustomerCondition.tel,
					//目前级别
					'memberUpGradeCondition.nowLevel':searchCustomerCondition.nowLevel,
					//客户编码
					'memberUpGradeCondition.custNumber':searchCustomerCondition.custNumber,
					//目标级别
					'memberUpGradeCondition.targetLevel':searchCustomerCondition.targetLevel};
			Ext.apply(operation,{
				params : searchParams
				}
			);	
		}
	}
});

Ext.onReady(function(){
	var params =[
      			// 目前级别(会员升级列表)
      			'NOW_GRAD_UP',
      			// 目标级别(会员升级列表)
      			'TARGET_GRAD_UP'];
    initDataDictionary(params);
	Ext.create('Ext.container.Viewport',{
		layout:'fit',
		items:[Ext.create('MemberUpgradePanel',{'memberUpgradeData':memberUpgradeDataControl})]
	});
//	var params = {'dataDictionaryKeys' : [
//	  			          			// 目前级别(会员升级列表)
//	  			          			'NOW_GRAD_UP',
//	  			          			// 目标级别(会员升级列表)
//	  			          			'TARGET_GRAD_UP']};
//	var dictionarySuccess = function(response){
//		DataDictionary = response.dataDictionary;
//		Ext.create('Ext.container.Viewport',{
//			layout:'fit',
//			items:[Ext.create('MemberUpgradePanel',{'memberUpgradeData':memberUpgradeDataControl})]
//		});
//	};
//	var dictionaryFail = function(response){
//		Ext.Msg.alert(i18n('i18n.PotentialCustManagerView.messageTip'),response.message);
//	};
//	memberUpgradeDataControl.getDictionary(params,dictionarySuccess,dictionaryFail);

});

