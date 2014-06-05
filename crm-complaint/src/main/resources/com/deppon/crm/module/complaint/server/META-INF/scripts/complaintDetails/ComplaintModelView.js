
/**
 * 第四显示块 声明
 */
Ext.define('ComplaintFourPanel', {
	extend : 'BasicPanel',
	height : 50,
	layout : {
		align : 'stretch',
		type : 'hbox'
	},
	padding : '2 2',
	defaults : {
		readOnly : true
	},
	initComponent : function() {
		var me = this;
		Ext.applyIf(me, {
			items : [ {
				xtype : 'textareafield',
				fieldLabel : i18n('i18n.complaint.comp_bulletin'),
				id : 'bulletinId',
				flex : 1,
				labelWidth : 75
			}, {
				xtype : 'checkboxfield',
				id : 'feedback',
				margin : '0 0 0 5',
				boxLabel : i18n('i18n.complaint.comp_feedback'),
				width : 80
			} ]
		});

		me.callParent(arguments);
	}
});

/**
 * 隐藏最终反馈第四个panel
 */
Ext.define('ComplaintHiddenFourPanel',{
	extend:'BasicPanel'
	,layout: {align:'middle',type:'hbox'}
	,defaults:{readOnly:true}
	,items: [
        {xtype:'textareafield',fieldLabel:i18n('i18n.complaint.comp_bulletin'),width:785,labelWidth:75,height:50,border:true,id:'bulletinId'}
    ]
});

/**
 * 客户工单之 调查建议列表
 */
Ext.define('SurveySuggestGrid', {
	extend : 'PopupGridPanel',
	height : 120,
	title : i18n('i18n.complaint.comp_recommendation'),
	complaint : null // 工单数据
	,
	initComponent : function() {
		var me = this;
		me.store = Ext.create('SurveySuggestStore', {
			autoLoad : true,
			listeners : {
				beforeload : function(store, operation, eOpts) {
					var searchParams = {
						'complaintId' : me.complaint.fid
					};
					Ext.apply(operation, {
						params : searchParams
					});
				}
			}
		});
		me.columns = me.getColumns();
		this.callParent(arguments);
	},
	getColumns : function() {
		var me = this;
		return [ {
			xtype : 'rownumberer',
			header : i18n('i18n.complaint.serial_number'),
			width : 40
		}, {
			header : i18n('i18n.comp.work_order.suggestion')/* 内容 */,
			flex : 1,
			dataIndex : 'suggestion',
			renderer : function(val) {
				return '<span data-qtip="' + val + '">' + val + '</span>';
			}
		}, {
			header : i18n('i18n.comp.work_order.operatorName')/* 操作人 */,
			width : 100,
			dataIndex : 'operatorName'
		}, {
			header : i18n('i18n.comp.work_order.operatorTime')/* 操作时间 */,
			width : 130,
			dataIndex : 'operatorTime',
			renderer : function(value) {
				if (value != null) {
					return Ext.Date.format(new Date(value), 'Y-m-d H:i');
				} else {
					return null;
				}
			}
		} ];
	}
});