/**
 * 禁用或启用方法
 */
function startEndZ(fn, flag) {
	var fnOne = {
		'id' : fn.data.id,
		'validFlag' : fn.data.validFlag,
		'parentCode':{'functionCode':fn.data.parentCode.functionCode}
	};
	var array = {
		'flag' : flag,
		'function' : fnOne
	};
	Ext.Ajax.request({
		url : 'updateFunction.action',
		jsonData : array,
		success : function(response) {
			FunctionPageElementStore.load({
				params : {
					'parentCode.functionCode' : fn.data.parentCode.functionCode
				}
			});
			var json = Ext.decode(response.responseText);
//			Ext.MessageBox.alert(i18n('i18n.authorization.message'),
//					json.message);
			MessageUtil.showMessage(json.message);
			Ext.getCmp('item_Start').setDisabled(true);
			Ext.getCmp('item_End').setDisabled(true);
		},
		failure : function(response) {
			var json = Ext.decode(response.responseText);
//			Ext.MessageBox.alert(i18n('i18n.authorization.message'),
//					json.message);
			MessageUtil.showMessage(json.message);
		}
	});

}

/**
 * 模块功能页面元素的权限信息
 */
var FunctionPageElementGridPanel = Ext.create('SearchGridPanel',{
	id : "functionPageElementGrid",
	title : i18n('i18n.authorization.Function.pageElement'),
	sortableColumns:false,
    enableColumnHide:false,
    enableColumnMove:false,
    height:'100%',
    //autoScroll : true,
	stripeRows : true, // 交替行效果
	viewConfig : {
		autoFill : true,
		forceFit : true
	},
	selType : "rowmodel", // 选择类型设置为：行选择
	store:FunctionPageElementStore,
	columns : [{
			id : 'id',
			hidden : true,
			dataIndex : 'id'
		},{
			text : i18n('i18n.authorization.Function.pageElementName'),
			width:80,
			dataIndex : 'functionName'
		},{
			text : i18n('i18n.authorization.Function.validFlag'),
			width:65,
			dataIndex : 'validFlag',
			renderer : change
		},{
			text : i18n('i18n.authorization.Function.check'),
			width:90,
			dataIndex : 'check',
			renderer : change
		},{
			text : i18n('i18n.authorization.Function.displayOrder'),
			width:60,
			dataIndex : 'displayOrder'
		},{
			text : i18n('i18n.authorization.Function.pageElementURI'),
			width:175,
			dataIndex : 'uri'
				
		},{
			text : i18n('i18n.authorization.Function.functionDesc'),
			width:90,
			dataIndex : 'functionDesc'	
	
	} ],
	listeners : {
		itemclick : function() {
			var sm = FunctionPageElementGridPanel
					.getSelectionModel();
			if (sm.selected.items[0].get('validFlag') == true) {
				Ext.getCmp('item_Start').setDisabled(true);
				Ext.getCmp('item_End').setDisabled(false);
			} else {
				Ext.getCmp('item_Start').setDisabled(false);
				Ext.getCmp('item_End').setDisabled(true);
			}
		}
	},
	tbar : [{
			text : i18n('i18n.authorization.start'),
			disabled : true,
			id : 'item_Start',
			hidden:!isPermission('/authorization/updateFunction.action'),
			handler : function() {
				var flag = 1;
				var sm = FunctionPageElementGridPanel.getSelectionModel();
				if (sm.selected.items.length == 0) {
//					Ext.MessageBox.alert(i18n('info.module.authorization.noSelect'));
					MessageUtil.showMessage(i18n('info.module.authorization.noSelect'));
				} else {
					sm.selected.items[0].set('validFlag',true);
					startEndZ(sm.selected.items[0], flag);
				}
	
			}
		},{
			text : i18n('i18n.authorization.end'),
			disabled : true,
			id : 'item_End',
			hidden:!isPermission('/authorization/updateFunction.action'),
			handler : function() {
				var flag = 5;
				var sm = FunctionPageElementGridPanel.getSelectionModel();
				if (sm.selected.items.length == 0) {
//					Ext.MessageBox.alert(i18n('info.module.authorization.noSelect'));
					MessageUtil.showMessage(i18n('info.module.authorization.noSelect'));
	
				} else {
					sm.selected.items[0].set('validFlag',false);
					startEndZ(sm.selected.items[0], flag);
				}
	
			}
		} ]
});