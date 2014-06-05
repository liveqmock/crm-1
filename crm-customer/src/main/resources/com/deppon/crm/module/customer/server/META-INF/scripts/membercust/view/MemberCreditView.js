var custCreditData =  Ext.create('CustCreditManageData');
//信用较差客户配置信息校验结果
var verifyResult ={'isValid':true,'message':null}; 
/**
 * 信用较差客户信息配置界面
 */
Ext.define('MemberCreditManageWin',{
	extend:'BasicPanel',
	custCreditData:null,//数据源
	mainPanel:null,//主数据展示面板
	helpDocPanel:null,//提示面板
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		//展示数据
		me.custCreditData.getCustCreditManageStore().loadPage(1);
		this.callParent();
	},
	getItems:function(){
		var me = this;
		me.mainPanel = Ext.create('mainGrid',{
			'custCreditData':me.custCreditData
		});
		me.helpDocPanel = Ext.create('HelpDocPanel');
		return[me.mainPanel,me.helpDocPanel];
	}
});
/**
 * 主数据展示页面
 */
Ext.define('mainGrid',{
	extend:'SearchGridPanel',
	height:298,
	custCreditData:null,//数据Data
	cellEditing : null,
	initComponent:function(){
		var me = this;
		me.cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
			clicksToEdit : 2
		});
		me.store = me.custCreditData.initCustCreditManageStore();
		me.columns = me.getColumns();
		me.plugins = [ me.cellEditing ];
		me.fbar = me.getFbar();
		this.callParent();
	},
	getColumns:function(){
		var me = this;
		return [
		   {
			header:i18n('i18n.PotentialCustManagerView.potentialxuhao'),
			dataIndex:'rankNumber',
			width:45  
		   },{
			header:i18n('i18n.customer.custCredit.filterRegular'),
			dataIndex:'actValue',
			renderer:function(value,metaData,record,rowIndex){
				var tmpHtml = {
					'1':'临时欠款超过{value}天未还的临时欠款客户',
					'2':'按照月结合同规定时间后{value}天未还款的月结客户',
					'3':'逾期{value}天未还款的客户',
					'4':'最长一笔临欠达到{value}天（含）的临时欠款客户',
					'5':'距离规定结款日期前{value}天未还款的月结客户',
					'6':'最长还款周期（月结天数）到期前{value}天未还款的月结客户',
					'7':'月结信用额度使用率达到 {value}%的客户',
					'8':'营业部临时欠款总额度使用率达到{value}%'
				};
				var tmp = new Ext.Template(tmpHtml[(rowIndex+1)+'']);
				
				return tmp.apply({'value':value});
			},
			flex:1,
			editor : {
				xtype : 'numberfield',
				decimalPrecision : 2,
				hideTrigger : true,
				allowBlank : false
			}
		}]
	},
	getFbar:function(){
		var me = this;
		return[{
			name:'commitBtn',
			id:'commitBtn_id',
			xtype:'button',
			scope:me,
			hidden:!isPermission('/customer/CustCreditSBtn.action'),
			text:i18n('i18n.ManualRewardIntegralEditView.b_save'),
			handler:function(button){
				button.setDisabled(true);
				me.saveCustCredit(button);
			}
		}]
	},
	//保存信用信息
	saveCustCredit:function(button){
		var me = this;
		var record = me.store.data;
		//执行数据校验
		me.checkData(record);
		if(!verifyResult.isValid){
			MessageUtil.showMessage(verifyResult.message);
			verifyResult ={'isValid':true,'message':null};
			button.setDisabled(false);
			return;
		}
		//封装数据结构，为传入后台做准备
		var custCreditConfig = Ext.create('CustCreditModel');
		custCreditConfig.set('maxOverdraftDay',record.getAt(0).get('actValue'));
		custCreditConfig.set('monthEndOvertake',record.getAt(1).get('actValue'));
		custCreditConfig.set('overdueMonth',record.getAt(2).get('actValue'));
		custCreditConfig.set('earliestDay',record.getAt(3).get('actValue'));
		custCreditConfig.set('beforePaymentdateDay',record.getAt(4).get('actValue'));
		custCreditConfig.set('beforeMonthPaymentDay',record.getAt(5).get('actValue'));
		custCreditConfig.set('usedcreditrate',record.getAt(6).get('actValue'));
		custCreditConfig.set('deptUsedrate',record.getAt(7).get('actValue'));
		var param = {'custCreditConfig':custCreditConfig.data};
		var successFn = function(result){
			if(!Ext.isEmpty(result)&&!Ext.isEmpty(result.message)){
				MessageUtil.showInfoMes(result.message);
			}else{
				MessageUtil.showInfoMes(i18n('i18n.RecordExchangeRuleManagerView.saveSuccessWar'));
			}
			button.setDisabled(false);
		}
		var failFn = function(result){
			if(!Ext.isEmpty(result)&&!Ext.isEmpty(result.message)){
				MessageUtil.showErrorMes(result.message);
			}else{
				MessageUtil.showErrorMes(i18n('i18n.RecordExchangeRuleManagerView.saveFailureWar'));
			}
			button.setDisabled(false);
		} 
		me.custCreditData.saveCustCredit(param,successFn,failFn);
	},
	//校验数据合法性
	checkData:function(record){
		//四位以内正整数
		var regOne =/^[1-9]\d{0,3}$/;
		//[0-100],最多两位小数
		var regTwo = /^([\d]{1,2}|100|[\d]{1,2}\.[\d]{0,2}|100\.[\d]{0,2})$/;
		//四位以内非负整数
		var regThree = /^\d{0,4}$/;
		//第一行只能为四位以内正整数
		if(!regOne.test(record.get(0).get('actValue'))){
			verifyResult.isValid = false;
			verifyResult.message = i18n('i18n.customer.custCredit.firstSixLineException');
			return;
		}
		//第二行四位以内非负整数
		if(!regThree.test(record.get(1).get('actValue'))){
			verifyResult.isValid = false;
			verifyResult.message = i18n('i18n.customer.custCredit.secondLineException');
			return;
		}
		//第三、四、五、六行只能输入四位以内正整数
		for(var i = 2;i < record.length-2;i++){
			if(!regOne.test(record.getAt(i).get('actValue'))){
				verifyResult.isValid = false;
				verifyResult.message = i18n('i18n.customer.custCredit.firstSixLineException');
				return;
			}
		}
		//第七、八行只能[0-100],最多两位小数
		for(var i = record.length-2;i < record.length;i++){
			if(!regTwo.test(record.getAt(i).get('actValue'))){
				verifyResult.isValid = false;
				verifyResult.message = i18n('i18n.customer.custCredit.lastTwoLineException');
				return;
			}
		}
	}
});
/**
 * 提示面板
 */
Ext.define('HelpDocPanel',{
	extend:'BasicPanel',
	height:200,
	padding:'5 0 5 0',
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return[{
			html:i18n('i18n.customer.custCredit.creditManageHelpDoc')
		}];
	}
});
Ext.onReady(function() {
var commonWin = 	Ext.create('Ext.container.Viewport', {
		items : [ Ext.create('MemberCreditManageWin',{
			'custCreditData':custCreditData
		})]
	});
});

