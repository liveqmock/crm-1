//奖励管理
var manualRewardIntegralManagerDataControl= (CONFIG.get("TEST"))? new ManualRewardIntegralManagerDataTest():new ManualRewardIntegralManagerData();

Ext.define('ManualRewardIntegralManagerPanel',{
	extend:'BasicPanel',
	manualRewardIntegralManagerResultGrid:null,  //默认显示结果
	manualRewardIntegralManagerButtonPanel:null,//查询按钮面板
	manualRewardIntegralManagerData:null,//数据Data
	layout:{
        type:'vbox',
        align:'stretch'
    },
	initComponent:function(){
		var me = this;
		me.manualRewardIntegralManagerResultGrid = Ext.create('ManualRewardIntegralManagerResultGrid',{'manualRewardIntegralManagerData':me.manualRewardIntegralManagerData});
		me.manualRewardIntegralManagerButtonPanel = Ext.create('ManualRewardIntegralManagerButtonPanel',{'manualRewardIntegralManagerData':me.manualRewardIntegralManagerData});
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'basicpanel',
			height:38,
			items:[me.manualRewardIntegralManagerButtonPanel]		
			},{
			xtype:'basicpanel',
			flex:1,
			items:[me.manualRewardIntegralManagerResultGrid]
		}];
	}
});

/**
 * 手动奖励积分操作按钮面板
 */
Ext.define('ManualRewardIntegralManagerButtonPanel',{
	extend:'LeftAlignedPanel',
	contractManagerConditionForm:null,
	manualRewardIntegralManagerData:null,   //data层接口
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
				scope:me,
				handler:me.addRecord
			}];
	},
	//新增
	addRecord:function(){
		var me = this;
		Ext.create('ManualRewardIntegralEditWin',{'parent':me}).show();
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
		var grid = me.parent.contractManagerResultGrid;
		return grid.getSelectionModel().getSelection();
	},
	//新增或删除后 重新加载 页面数据
	reloadDate:function(addRecordDate,updateRecord){
		var me = this;
		var addRecord = Ext.create('RewardIntegRuleModel',addRecordDate);
		var grid = me.rewardRuleManagerResultGrid;
		//如果为修改操作则先删除修改前的数据
		if(!Ext.isEmpty(updateRecord)){
			grid.store.remove(updateRecord);
		}
		//数据加载到第一行
		grid.store.insert(0,addRecord);
		//如果该页分页已满则删除查询结果中最后一条数据
		var count = grid.store.getCount();
		if(count>=20){
			grid.store.remove(grid.store.getAt(count-1));
		}
		//如果没有新增和修改数据  则为删除操作，重新加载数据
		if(Ext.isEmpty(addRecordDate) && Ext.isEmpty(updateRecord)){
			grid.store.load();
		}
	}
});

/**
 * 手动奖励积分默认显示数据结果
 */
Ext.define('ManualRewardIntegralManagerResultGrid',{
	extend:'SearchGridPanel',
	id:'ManualRewardIntegralManagerResultGrid_id',
	manualRewardIntegralManagerData:null,//数据Data
	initComponent:function(){
		var me = this;
		me.store = me.manualRewardIntegralManagerData.getHandRewardIntegralStore();
		me.dockedItems = me.getMyDockedItems();
		me.selModel = Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE'});
		me.columns = me.getColumns();
		this.callParent();
	},
	getColumns:function(){
		var me = this;
		return [Ext.create('Ext.grid.RowNumberer'),{
			header:i18n('i18n.ManualRewardIntegralEditView.id'),
			dataIndex:'contact.id',
			hidden:true
		},{
			header:i18n('i18n.Integral.contactNumber'),
			dataIndex:'contact.number'
		},{
			header:i18n('i18n.Integral.contatct'),
			dataIndex:'contact.name'
		},{
			header:i18n('i18n.ManualRewardIntegralManagerView.rewardId'),
			dataIndex:'rewardIntegral.id',
			hidden:true
		},{
			header:i18n('i18n.ManualRewardIntegralEditView.rulename'),
			dataIndex:'rewardIntegral.rulename'
		},{
			header:i18n('i18n.ManualRewardIntegralEditView.pointvalue'),
			dataIndex:'rewardIntegral.pointvalue'
		},{
			header:i18n('i18n.ManualRewardIntegralEditView.integType'),
			dataIndex:'rewardIntegral.integType',
			renderer : function(value) {
				return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.CUST_RULETYPE);
			}
		},{
			header:i18n('i18n.ManualRewardIntegralManagerView.integralBasicNumber'),
			dataIndex:'integralBasicNumber'
		},{
			header:i18n('i18n.Integral.recordRate'),
			dataIndex:'rewardIntegral.fraction'
		},{
			header:i18n('i18n.ManualRewardIntegralManagerView.deptName'),
			dataIndex:'deptName'
		},{
			header:i18n('i18n.PotentialCustManagerView.searchStartTime'),
			dataIndex:'createDate',
			renderer : function(value) {
				return DpUtil.renderDate(value);
			}
		},{
			header :i18n('i18n.ManualRewardIntegralManagerView.createName'),
			dataIndex:'createName'
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
	}
});
Ext.onReady(function() {
	var params = ['CUST_RULETYPE',// 积分类型
				'AWARD_TYPE'//奖励类型
				];
	initDataDictionary(params);//数据字典
	Ext.create('Ext.container.Viewport', {
		layout : 'fit',
//		autoScroll:true,
		items : [ Ext.create('ManualRewardIntegralManagerPanel', {
			'manualRewardIntegralManagerData' : manualRewardIntegralManagerDataControl
		}) ]
	});
});