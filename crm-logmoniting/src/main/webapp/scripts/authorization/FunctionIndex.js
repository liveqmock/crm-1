/**
 * 树节点全部展开
 */
function openAll() {
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
 * 新增功能按钮单击事件
 */
function toAddFunction() {
	//更新模块功能页面元素的权限信息列表
	Ext.data.StoreManager.lookup('FunctionPageElementStore').removeAll();
	//设置表格对象中tbar按钮不显示
	setTbarHidden(FunctionPageElementGridPanel, false);
	var functionModel = new FunctionModel();
	FunctionForm.loadRecord(functionModel);
	FunctionForm.getForm().reset();
	setFormEditAble(FunctionForm,true);
};

/**
 * 修改按钮单击事件
 */
function toUpdateFunction() {
	var selection = FunctionTree.getSelectionModel().getSelection();
	if (selection == null || selection.length <= 0) {
//		Ext.MessageBox.alert(i18n('i18n.authorization.message'),i18n('i18n.authorization.operation_message'));
		MessageUtil.showMessage(i18n('i18n.authorization.operation_message'));
		return;
	}
	var record = selection[0];
	//如果父节点为空，那么当前节点就是功能树的根节点，不可以做修改
	if(record.raw.entity.parentCode==null){
		return;
	}
	//判断被选择的记录的类型是不是模块功能
	if(record.raw.entity.functionType==3){
		//设置表格对象中tbar按钮显示
		setTbarHidden(FunctionPageElementGridPanel, true);
	}
	loadFormDataFromTree(FunctionForm, record);
	setFormEditAble(FunctionForm,true);
}

/**
 * @description 权限管理主页
 */
Ext.onReady(function() {
	document.title = i18n('i18n.authorization.function_page_title');
//	document.getElementById('message_waiting').innerHTML = i18n('i18n.authorization.message_waiting');
//	setTimeout(function() {
//			Ext.get('loading').remove();
//			Ext.get('loading-mask').fadeOut({
//				remove : true
//			});
//		}, 1000);
	Ext.QuickTips.init();
	//设置表单不可编辑
	setFormEditAble(FunctionForm,false);
	//加载功能树根节点的数据

	Ext.create('Ext.container.Viewport', {
		id : "functionIndexId",
		title : i18n('i18n.organization.page_title',null),
		layout : 'border',
		height:window.screen.availHeight*0.7486,
		width:250,
		items : [ {
			region : "west",
			width:250,
			margin:'5 5 0 0',
	    	border:true,
	    	//autoScroll:true,
	    	//layout : 'accordion',
			items : [ FunctionTree ]
		}, {
			region : "center", // 中间布局
	        baseCls:'x-plain',
	        layout:'border',
	        margin:'5 3 0 0',
	        items:[{
	        	region : 'north',
	    		baseCls:'x-plain',
	    		items:[FunctionForm]
	        }, {
	        	region : "center",
	        	margin:'5 0 0 0',
    			baseCls:'x-plain',
    			items:[FunctionPageElementGridPanel]
	        }]
		}]
	});
});