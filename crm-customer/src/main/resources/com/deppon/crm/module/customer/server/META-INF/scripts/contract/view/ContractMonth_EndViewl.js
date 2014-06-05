var contractControl = (CONFIG.get('TEST'))? Ext.create('ContractBasicDataTest'):Ext.create('ContractBasicData');
var contractManagerDataControl =  (CONFIG.get("TEST"))? new ContractManagerDataTest():new ContractManagerData();
var contractMonEndData =  new ContractMonthEndData();
Ext.define('ContractMonth_EndViewWin',{
	extend:'BasicPanel',
	parent:null,
	contractMonthEndResultGrid:null,//合同月结天数查询结果
	contractMonthEndButtonPanel:null,//合同月结天数按钮面板
	contractMonthEndData:null,//数据Data
	layout:{
	    type:'vbox',
	    align:'stretch'
	},
	initComponent:function(){
		var me = this;
		me.contractMonthEndResultGrid = Ext.create('contractMonthEndResultGrid',{'parent':me,'contractMonthEndData':me.contractMonthEndData});
		me.contractMonthEndButtonPanel = Ext.create('contractMonthEndButtonPanel',{'parent':me,'contractMonthEndData':me.contractMonthEndData});
		me.items = [{
			xtype:'basicpanel',
			height:36,
			items:[me.contractMonthEndButtonPanel]
		},{
			xtype:'basicpanel',
			flex:1,
			items:[me.contractMonthEndResultGrid]
		}];
		this.callParent();
	}
});

Ext.define('contractMonthEndButtonPanel',{
	extend:'NormalButtonPanel',
	contractMonthEndData:null,
	parent:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return[{
			xtype:'leftbuttonpanel',
			width:200,
			defaultType:'button',
			defaults:{
				scope:me,
				margins : '0 5 0 0'
			},
			items:[{
				text:'修改',
				name:'updateMonthEndBtn',
				handler:me.updateMonthEndDays
			}]
		},{
			xtype:'middlebuttonpanel'
		},{
			xtype:'rightbuttonpanel',
			width:200,
			defaultType:'button',
			defaults:{
				scope:me,
				margins : '0 5 0 0'
			},
			items:[{
				text:'查询',
				name:'searchMonthEndBtn',
				handler:me.updateSearchEndMonthDays
				}]
			}];
	},
	//查询
	updateSearchEndMonthDays:function(){
		var me = this;
		me.contractMonthEndData.getContractMonthEndStore().loadPage(1);
	},
	//修改
	updateMonthEndDays:function(button){
		var me = this;
		var successFn = function(response){	
		selecttion = me.parent.contractMonthEndResultGrid.getSelectionModel().getSelection();
		if(selecttion.length == 1){
			var record=selecttion[0];
			Ext.create('modifyMonthEndDaysWin',{
				width:300,
				height:120,
				monthEndDays:record.get('defaultDebtDays'),
				status:record.get('status'),
				monthEndId:record.get('id')
			}).show();
		}else{
			MessageUtil.showErrorMes('请选择一条数据再进行操作！');
			return;	
			}
		};
		var failFn = function(response){
			MessageUtil.showErrorMes(response.message);
		};
		var params={};
		contractManagerDataControl.monthEndBtnIsAble(params,successFn,failFn);
	}
});
Ext.define('modifyMonthEndDaysWin',{
	extend:'PopWindow',
	title:'修改合同月结天数',
	contractMonthEndData:null,//数据Data
	monthEndDays:null,
	status:null,
	monthEndId:null,
	parent:null,
	layout:{
        type:'vbox',
        align:'stretch'
    },
    initComponent:function(){
    	var me = this;
    	me.items = me.getItems();
		me.fbar = me.getFbar();
		this.callParent();
    },
    	getItems:function(){
    		var me = this;
    		return[{
    			xtype:'dpnumberfield',
    			id:'deptDays_id',
    			value:me.monthEndDays,
    			minValue: 0,
    			decimalPrecision:0,
    			allowBlank:false,
    			name:'deptDays',
    			maxLength:10,
    			hideTrigger:true,
				mouseWheelEnabled: false,
    			fieldLabel:'合同月结天数'
    		}]
    	},
    	getFbar:function(){
    		var me = this;
    		return[{
    			xtype:'button',
    			scope:me,
    			text:'确定',
    			handler:me.commitResult
    		},{
    			xtype:'button',
    			scope:me,
    			text:'取消',
    			handler:function(){
    				me.close();
    			}
    		}]
    	},
    	commitResult:function(button){
    		var me = this;
    		if(Ext.isEmpty(Ext.getCmp('deptDays_id').getValue())){
    			MessageUtil.showErrorMes('月结天数不能为空');
    			return;
    		}
    		var params = {};
    		params.debtDays = Ext.getCmp('deptDays_id').getValue();
    		params.contractId = me.monthEndId;
    		button.setDisabled(true);
    		var saveSuccess = function(result){
    			MessageUtil.showInfoMes('修改合同月结天数成功！');
    			me.close();
    			contractMonEndData.getContractMonthEndStore().loadPage(1);
				button.setDisabled(false);
    		}
    		var saveFail = function(result){
				MessageUtil.showErrorMes('修改合同月结天数失败！');
				button.setDisabled(false);
			}
    		contractControl.updateCommonContractMonthEnd(params,saveSuccess,saveFail);
    	}
    
});
Ext.define('contractMonthEndResultGrid',{
	extend:'SearchGridPanel',
	contractMonthEndData:null,//数据Data
	initComponent:function(){
		var me = this;
		me.store = me.contractMonthEndData.initContractMonthEndStore();
		me.selModel = Ext.create('Ext.selection.CheckboxModel',{
			mode:'SINGLE'
		});
		me.columns = me.getColumns();
		this.callParent();
	
	},
	getColumns:function(){
		var me = this;
		return [{
			header:'名称',
			dataIndex:'contractDebtDayName',
			width:200
		},{
			header:'合同月结天数',
			dataIndex:'defaultDebtDays',
			width:200
		},{
			header:'最后修改人',
			dataIndex:'modifyUser',
			width:200
		},{
			header:'最后修改时间',
			dataIndex:'modifyDate',
			width:200,
			renderer : DpUtil.renderTime
		}]
	}
});
	Ext.onReady(function() {
	var commonWin = 	Ext.create('Ext.container.Viewport', {
			layout : 'fit',
			items : [ Ext.create('ContractMonth_EndViewWin', {
				'contractMonthEndData' : contractMonEndData
			}) ]
		});
	
	});

