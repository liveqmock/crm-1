/**
 * 树节点全部展开
 */
function openAll() {
	//先全部折叠
	closeAll();
	//在全部展开
	FunctionTree.expandAll();
};

/**
 * 树节点全部折叠
 */
function closeAll() {
	FunctionTree.collapseAll();
};

/**
 * 刷新树的节点
 */
function reFresh() {
	var record = FunctionTree.getSelectionModel().getSelection()[0];
	if(record.raw.entity != null){
		var functionCode = record.raw.entity.functionCode;
		onRefreshTreeNodes(FunctionTreeStore,functionCode);
	}
}
/**
* 删除树节点方法
 */
function deleteFunction() {
	var selection = FunctionTree.getSelectionModel().getSelection();
	if (selection == null || selection.length <= 0) {
		MessageUtil.showMessage(i18n('i18n.authorization.operation_message'));
//		Ext.MessageBox.alert(i18n('i18n.authorization.message'),
//				i18n('i18n.authorization.operation_message'));
		return;
	}
	var record = selection[0];
	// 如果父节点为空，那么当前节点就是功能树的根节点，不可以做修改
	if (record.raw.entity.parentCode == null) {
		return;
	}
	var record = FunctionTree.getSelectionModel().getSelection()[0];
	var id = record.raw.entity.id;
	Ext.Ajax.request({
		url : 'deleteFunction.action',
		params : {
			'function.id' : id
		},
		success : function(response) {
			var json = Ext.decode(response.responseText);
			if (json.success) {
//				Ext.MessageBox.alert(i18n('i18n.authorization.message'),
//						i18n('info.module.authorization.deleteSuccess'));
				MessageUtil.showMessage(i18n('info.module.authorization.deleteSuccess'));
				FunctionTree.store.remove(record);
			} else {
//				Ext.MessageBox.alert(i18n('i18n.authorization.message'),
//						i18n('info.module.authorization.deleteFailed'));
				MessageUtil.showMessage(i18n('info.module.authorization.deleteFailed'));
			}
		},
		failure : function(response) {
			var json = Ext.decode(response.responseText);
			if (json.success) {
//				Ext.MessageBox.alert(i18n('i18n.authorization.message'),
//						i18n('info.module.authorization.deleteSuccess'));
				MessageUtil.showMessage(i18n('info.module.authorization.deleteSuccess'));
				FunctionTree.store.remove(record);
			} else {
//				Ext.MessageBox.alert(i18n('i18n.authorization.message'),
//						i18n('info.module.authorization.deleteFailed'));
				MessageUtil.showMessage(i18n('info.module.authorization.deleteFailed'));
			}
		}
	});

}

/**
 * 设置表单里面的表单控件的编辑状态
 */
function setFormEditAble(from, trueorfalse) {
	var fields = from.getForm().getFields();
	fields.each(function(field) {
		field.setReadOnly(!trueorfalse);
	});
}

/**
 * 从功能树中加载数据到form表单中
 */
function loadFormDataFromTree(FunctionForm, record) {
	// 如果父节点为空，那么当前节点就是功能树的根节点，不可以做修改
	if (record.raw.entity.parentCode == null) {
		return;
	}
	var functionModel = Ext.ModelManager.create(record.raw.entity,
			'FunctionModel');
	FunctionForm.loadRecord(functionModel);
	if (record.raw.entity.parentCode != null) {
		var functionRecord = Ext.ModelManager.create(
				record.raw.entity.parentCode, 'FunctionModel');
		FunctionForm.getForm().findField("parentCode.functionCode").select(
				functionRecord);
	}
	setFormEditAble(FunctionForm, false);
}

// /////////////////////////树的右键菜单/////////////////////////////////////
var FunctionTreeRightMenu = new Ext.menu.Menu({
	frame : true,
	id : 'FunctionTreeRightMenu',
	width :  window.screen.availWidth*0.0781,
	items : [ {
		text : i18n('i18n.authorization.refresh'),
		width :  window.screen.availWidth*0.0313,
		iconCls : 'deppon_menu_refresh',
		handler : function() {
			var record = FunctionTree.getSelectionModel().getSelection()[0];
			if (record.raw.entity != null) {
				var functionCode = record.raw.entity.functionCode;
				onRefreshTreeNodes(FunctionTreeStore, functionCode);
			}
		}
	} ]
});

// //////////////////////树的事件////////////////////////////////
/**
 * 树的节点左键单击事件
 * 
 * @param {}
 *            view
 * @param {}
 *            _node 节点
 */
function treeLeftKeyAction(node, record, item, index, e) {
	e.preventDefault();// 阻止浏览器默认行为处理事件
	if(record.data.root==true){
		Ext.getCmp('toStartFunction_Button').setDisabled(true);
		Ext.getCmp('toEndFunction_Button').setDisabled(true);
		FunctionForm.getForm().reset();
		return;
	}
	loadFormDataFromTree(FunctionForm, record);
	// 删除模块功能页面元素的权限信息列表
	Ext.data.StoreManager.lookup('FunctionPageElementStore').removeAll();
	if (record.raw.entity.validFlag == true) {
		Ext.getCmp('toStartFunction_Button').setDisabled(true);
		Ext.getCmp('toEndFunction_Button').setDisabled(false);
	} else {
		Ext.getCmp('toEndFunction_Button').setDisabled(true);
		Ext.getCmp('toStartFunction_Button').setDisabled(false);
	}
	Ext.getCmp('item_Start').setDisabled(true);
	Ext.getCmp('item_End').setDisabled(true);
	// 只有是模块功能的时候,才进行模块功能下的页面元素功能项的加载
	if (record.raw.entity.functionType == 3) {
		FunctionPageElementStore.load({
			params : {
				'parentCode.functionCode' : record.raw.entity.functionCode
			}
		});
	}
}

/**
 * 树节点的右键点击事件
 * 
 * @param node
 * @param record
 * @param item
 * @param index
 * @param e
 */
function treeRightKeyAction(node, record, item, index, e) {
	e.preventDefault();// 阻止浏览器默认行为处理事件
	FunctionTreeRightMenu.showAt(e.getXY());
}

/**
 * 定义功能树的store
 */
var FunctionTreeStore = Ext.create('Ext.data.TreeStore', {
	proxy : {
		type : 'ajax',
		actionMethods : 'POST',
		url : 'loadManagerFunctionTree.action'
	},
	root : {
		id : '0',
		text : i18n('i18n.authorization.Function.app'),
		expanded : true
	},
	sorters : [ {
		property : 'leaf',
		direction : 'ASC'
	} ]
});

/**
 * 定义功能树的面板
 */
var FunctionTree = Ext.create("Ext.tree.Panel", {
	store : FunctionTreeStore,
	useArrows : true,
	height:'95%',
	//autoScroll : true, // 自动添加滚动条
	border : false,
	title:i18n('i18n.authorization.functionTree'),
	id : "FunctionTree",
	cls:'normaltree',
	layoutConfig : {
		// 展开折叠是否有动画效果
		animate : true
	},
	// 监听事件
	listeners : {
		itemclick : treeLeftKeyAction,
		itemcontextmenu : treeRightKeyAction
	},
	tbar:[{
		text : i18n('i18n.authorization.expendAll'),//"全部展开",
		handler : openAll,
		scope : this
	},'-', {
		text : i18n('i18n.authorization.closeAll'),//"全部折叠",
		handler : closeAll,
		scope : this
	} ,'-', {
		text : i18n('i18n.authorization.refresh'),//"全部折叠",
		handler : reFresh,
		scope : this
	}]
	
});