/**
 * 功能：奖励积分规则管理 list界面
 * @author 李学兴 
 */
var rewardRuleManagerDataControl= (CONFIG.get("TEST"))? new RewardRuleManagerDataTest():new RewardRuleManagerData();

Ext.define('RewardRuleManagerPanel',{
	extend:'BasicPanel',
	/*extend:'Ext.panel.Panel',*/
	rewardRuleManagerResultGrid:null,  //默认显示结果
	rewardRuleManagerButtonPanel:null,//查询按钮面板
	rewardRuleManagerData:null,//数据Data
	layout:{
        type:'vbox',
        align:'stretch'
    },
	initComponent:function(){
		var me = this;
		me.rewardRuleManagerButtonPanel = Ext.create('RewardRuleManagerButtonPanel',{'parent':me,'rewardRuleManagerData':me.rewardRuleManagerData});
		me.rewardRuleManagerResultGrid = Ext.create('RewardRuleManagerResultGrid',{'parent':me,'rewardRuleManagerData':me.rewardRuleManagerData});
		me.items = me.getItems();
		this.callParent();
		//加载奖励积分规则数据
		me.rewardRuleManagerResultGrid.store.load();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'basicpanel',
			height:38,
			items:[me.rewardRuleManagerButtonPanel]		
			},{
			xtype:'basicpanel',
			flex:1,
			items:[me.rewardRuleManagerResultGrid]
		}];
	},
	reloadDate:function(addRecordDate,updateRecord){
		var me = this;
		var addRecord = Ext.create('RewardIntegRuleModel',addRecordDate);
			addRecord.set('cname',top.User.empName);
		var grid = me.rewardRuleManagerResultGrid;
		//如果是修改则删除修改的源数据
		if(!Ext.isEmpty(updateRecord)){
			updateRecord.set('mname',top.User.empName);
		}else{
			//数据加载到最后一行
			var count = grid.store.getCount();
			grid.store.insert(count,addRecord);
		}
	}
});

/**
 * 奖励管理操作按钮面板
 */
Ext.define('RewardRuleManagerButtonPanel',{
	extend:'LeftAlignedPanel',
	contractManagerConditionForm:null,
	rewardRuleManagerData:null,   //data层接口
	parent:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
				xtype:'button',
				text:i18n('i18n.PotentialCustManagerView.add'),
				hidden:!isPermission('/customer/RewardRuleOperateABtn.action'),
				scope:me,
				handler:me.addRecord
			},{
				xtype:'button',
				text:i18n('i18n.PotentialCustManagerView.viewDetails'),
				scope:me,
				handler:me.viewRecord
			},{
				xtype:'button',
				text:i18n('i18n.PotentialCustManagerView.update'),
				hidden:!isPermission('/customer/RewardRuleOperateUBtn.action'),
				scope:me,
				handler:me.updateRecord
			},{
				xtype:'button',
				text:i18n('i18n.RewardRuleManagerView.integralReward'),
				scope:me,
				handler:me.integralReward
			}
//			,{
//				xtype:'button',
//				text:i18n('i18n.RewardRuleManagerView.awardTypeManager'),
//				scope:me,
//				handler:null
//			}
			];
	},
	//新增
	addRecord:function(){
		var me = this;
		var record = Ext.create('RewardIntegRuleModel');
		record.set('fraction',0);
		Ext.create('RewardRuleEditWin',{'parent':me.parent,'record':record}).show();
	},
	//修改
	updateRecord:function(){
		var me = this;
		if(me.selectDate('ONE')){
			var record = me.selectedRecord()[0];
			var revardRuleWin = Ext.create('RewardRuleEditWin',{'parent':me.parent,operatorStatus:'UPDATE','record':record});
			revardRuleWin.show();
		}
	},
	//查看详情
	viewRecord:function(){
		var me = this;
		if(me.selectDate('ONE')){
			var record = me.selectedRecord()[0];
			me.viewRewardRuleWin(null,record);
		}
	},
	//弹出奖励规则编辑界面
	viewRewardRuleWin:function(grid,record){
		var revardRuleWin = Ext.create('RewardRuleEditWin',{operatorStatus:'VIEW','record':record});
		revardRuleWin.rewardRuleEditPanel.rewardRuleEditInfoPanel.getForm().updateRecord(record);
		revardRuleWin.show();
	},
	//积分奖励
	integralReward:function(){
		//跳转到积分奖励 clickTabpanel方法是 common包下 login模块 showWorkSpace.js中
		parent.clickTabpanel('85','手动奖励积分','/customer/manualRewardIntegralManagerIndex.action');
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
		var grid = me.parent.rewardRuleManagerResultGrid;
		return grid.getSelectionModel().getSelection();
	}
});

/**
 * 积分规则管理默认显示数据结果
 */
Ext.define('RewardRuleManagerResultGrid',{
	extend:'SearchGridPanel',
	parent:null,
	rewardRuleManagerData:null,//数据Data
	initComponent:function(){
		var me = this;
		me.store = me.rewardRuleManagerData.getRewardIntegRuleStore();
		me.dockedItems = me.getMyDockedItems();
		me.listeners = me.getMyListeners();
		me.selModel = Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE'});
		me.columns = me.getColumns();
		this.callParent();
	},
	getColumns:function(){
		var me = this;
		return [Ext.create('Ext.grid.RowNumberer'),{
			header:i18n('i18n.RewardRuleManagerView.rewardRuleNumber'),
			dataIndex:'id'
		},{
			header:i18n('i18n.RewardRuleManagerView.ruletype'),
			dataIndex:'ruletype',
			renderer : function(value) {
				return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.AWARD_TYPE);
			}
		},{
			header:i18n('i18n.RewardRuleManagerView.rulename'),
			dataIndex:'rulename'
		},{
			header:i18n('i18n.RewardRuleManagerView.integType'),
			dataIndex:'integType',
			renderer : function(value) {
				return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.CUST_RULETYPE);
			}
		},{
			header:i18n('i18n.RewardRuleManagerView.pointvalue'),
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
			header:i18n('i18n.Integral.awardRecordDesc'),
			dataIndex:'pointdesc'
		},{
			header : i18n('i18n.PotentialCustManagerView.createTime'),
			dataIndex:'createDate',
			renderer : function(value) {
				return DpUtil.renderDate(value);
			}
		},{
			header : i18n('i18n.PotentialCustManagerView.creator'),
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
	},
	//事件，双击
	getMyListeners:function(){
		var me = this;
		return {
			itemdblclick:me.parent.rewardRuleManagerButtonPanel.viewRewardRuleWin
		};
	}
});
/**
 * 奖励规则 win
 */
Ext.define('RewardRuleWin',{
	extend:'PopWindow',
	title:i18n('i18n.RewardRuleManagerView.rewardRule'),
	width:600,
	height:200,
	layout:'fit',
	contractData:null,
	record:null,
	contractAttachment:null,
	initComponent:function(){
		var me = this;
		me.items = [me.contractAttachment];//合同附件panel
		me.fbar = me.getFbar();
		this.callParent();
	},
	getFbar:function(){
		var me = this;
		return[{
	    	xtype:'button',
	    	scope:me,
	    	text:i18n('i18n.MemberCustEditView.submit'),
	    	handler:me.submitBound
	    },{
	    	xtype:'button',
	    	text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
	    	scope:me,
	    	handler:me.cancelBound
	    }];
	},
	submitBound:function(){}
});
Ext.onReady(function() {
	var params = ['CUST_RULETYPE',// 积分类型
				'AWARD_TYPE'//奖励类型
				];
	initDataDictionary(params);//数据字典
	
	Ext.create('Ext.container.Viewport', {
		layout : 'fit',
//		autoScroll:true,
		items : [ Ext.create('RewardRuleManagerPanel', {
			'rewardRuleManagerData' : rewardRuleManagerDataControl
		}) ]
	});
});

