var labelData = (CONFIG.get('TEST')) ? Ext.create('ContractBasicDataTest') : Ext.create('LabelData');
//全局变量 控制按钮选中没选中
var temp = null;
var currentUser = null;
var deleteLabelId = null;

Ext.define('LabelForm',{
		extend:'BasicFormPanel',
		id:'labelform',
		title : i18n('i18n.label.custLabelManager'),
		initComponent:function(){
			var me = this;
			me.items = me.getItems();
			this.callParent();
		},
		layout : {
			type : 'table',columns : 4//3
		},
		defaultType :'dptextfield',
		defaults : {
				labelWidth:70,
				width:250
		},
		getItems:function(){
			var me = this;
			return [{
					fieldLabel:i18n('i18n.label.custLabelName'),
					maxLength :6,
					name:'labelName',
					margin:'15 0 15 0'
				},{
					xtype : 'button',
					scope:me,
					margin:'5 0 5 5',
					id:'addButton_id',
					text:i18n('i18n.label.addButton'),
					width:70,
					handler:function(t){
						var form = this.getForm();
						t.setDisabled(true);
						//新增标签
						addCustLabel(form);
					}
				},{
					xtype : 'button',
					scope:me,
					margin:'5 5 5 5',
					text:i18n('i18n.label.deleteButton'), 
					width:70,
					id:'deleteButton_id',
					handler:function(t) {
						t.setDisabled(true);
						//删除标签
						deleteCustLabel();
			}
				
		},
				{
					xtype : 'button',
					scope:me,
					margin:'5 5 5 5',
					text:'修改', 
					width:70,
					id:'updateButton_id',
					handler:function(t) {
						if(validateUpdateCustLabel()){
							var updateLablesWin = Ext.create('UpdateLablesWin',
									{lableName:temp});
							updateLablesWin.show();
						}
			}
		}
]
	}
});


Ext.onReady(function() {
	//初始化页面
	var commonWin = 	Ext.create('Ext.container.Viewport', {
			layout : 'fit',
			cls:'labelPanel',
			items : [
				{
					xtype:'panel',border:false,
					items:[
						Ext.create('LabelForm'),
						{	
							xtype:'panel',border:false,
							margin:'0 20',
							html:'<div style="height:1px; width:100%; background-color:#4F4F4F;"></div>'
						},
						{
							xtype:'panel',
							id:'btnList',
							border:false,
							layout:'column',
							columnWidth:0.15
							}
					]
				}]
		});
	initBasicData();
	});

/**
 * 修改标签
 */
Ext.define('UpdateLablesWin',{
	extend:'PopWindow',
	id:'updateLablesWin_id',
	items:null,
	width:330,
	y:200,//距离界面顶层 距离
	height:160,
	lableId:null,//标签ID
	lableName:null,//标签名字
	labelDeptId:null,//标签部门id
	recode:null,
	title:i18n('i18n.label.updateCustLabelName'),
	layout : {
			type : 'table',columns : 3
	},
	initComponent:function(){
		var me = this;
		if(1 == 1){
			me.items =[{
				xtype:'panel',
				border:false,
				colspan:3,
				margin:'5 0 8 -11',
				width:330,
				html:'<div style="height:1px; width:628px; background-color:darkGray;"></div>'
			},{
		        xtype: 'displayfield',
		        id:'oldLable_id',
		        fieldLabel: i18n('i18n.label.oldCustLabelName'),
		        labelWidth:70,
		        colspan:3
			},{
				xtype: 'dptextfield',
				fieldLabel:i18n('i18n.label.updateCustLabelName'),
				maxLength :6,
				labelWidth:70,
				id:'updateLabel_id',
		        colspan:3
				
			}];
		}
	    me.buttons = me.getButtons();
		this.callParent();
		if(!Ext.isEmpty(Ext.getCmp('oldLable_id'))){
			Ext.getCmp('oldLable_id').setValue(me.lableName);
		}
	},
	getButtons:function(){
		var me = this;
		return[{
		   xtype : 'button',
			scope:me,
			id:'savaLableButtonId',
			colspan:2,
			text:i18n('i18n.PotentialCustEditView.save'), 
			width:70,
			handler:function(t) {
				updateCustLabel(t);
			}
	   },{
		   xtype : 'button',
			scope:me,
			margin:'5 0 5 5',
			text:i18n('i18n.PotentialCustEditView.cancel'),
			width:70,
			handler:function(){
				Ext.getCmp(deleteLabelId).setDisabled(false);
				Ext.getCmp('updateLablesWin_id').close();
			}
	   }];
	}
});