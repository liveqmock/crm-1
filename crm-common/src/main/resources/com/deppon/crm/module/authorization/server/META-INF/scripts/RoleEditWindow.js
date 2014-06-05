/**
 * 定义功能选择树的store
 */
var FunctionTreeStore = Ext.create('Ext.data.TreeStore',{
	model : 'TreeNodeModel',
	proxy : {
		type : 'ajax',
		actionMethods : 'POST',
		url:'loadFunctionChooesTree.action'
	},
	root : {
		id : '0',
		text : i18n('i18n.authorization.Function.app'),
		expanded : true,
		checked:null
	},
	listeners : {
		'beforeload': function(store, operation, eOpts){
			var role = RoleForm.getForm().getRecord();
			if(role == null){
				return;
			}
			if(undefined === role.raw){
				return;
			}
			if(operation.params==null){
				operation.params = {};
			}
			Ext.apply(operation.params,{
				'roleId' : role.raw.id
			});
		},
		'load':function(store, operation, eOpts){
			if(Ext.getCmp('reset_button')==null){
				return;
			}
			Ext.getCmp('reset_button').setDisabled(false);
		}
	}
});

///////////////////////////树的右键菜单/////////////////////////////////////
var FunctionChooesTreeRightMenu = Ext.create('Ext.menu.Menu',{
	border : false,
	id : 'FunctionChooesTreeRightMenu',
	//width : 70,
	items : [{
		text : i18n('i18n.authorization.refresh'),
		//width : 60,
		iconCls : 'deppon_menu_refresh',
		handler : function() {
			var record = FunctionChooesTree.getSelectionModel().getSelection()[0];
			if(record.raw.entity != null){
				var functionCode = record.raw.entity.functionCode;
				onRefreshTreeNodes(FunctionTreeStore,functionCode);
			}
		}
	}]
});

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
	FunctionChooesTreeRightMenu.showAt(e.getXY());
}

var FunctionChooesTree = Ext.create('Ext.tree.Panel', {
	title : i18n('i18n.authorization.Function.list'),
    sortableColumns:false,
    enableColumnHide:false,
    enableColumnMove:false,
    width :788,
	height :360,
	cls:'normaltree',
	border : true,
	//删除收缩按钮
    //collapsible: true,
    useArrows: true,
    autoScroll : true,
    store: FunctionTreeStore,
    multiSelect: true,
    //singleExpand: true,
    columns: [{
        xtype: 'treecolumn',
        text: i18n('i18n.authorization.Function.functionName'),
        width:230,
		dataIndex : 'text'
    },{
    	text: i18n('i18n.authorization.Function.URI'),
		dataIndex : 'entity',
		renderer : function(value){
			if(value==null){
				return '';
			}
			return value.uri;
		},
		width:250
    },{
		text : i18n('i18n.authorization.role.roleDesc'),
		width:250,
		dataIndex : 'entity',
		renderer : function(value){
			if(value==null){
				return '';
			}
			return value.functionDesc;
		}
	} ],
	itemcollapse:function(node, state){
		var flag = true;//默认全部选中
		for (var i = 0; i < node.childNodes.length; i++) {
			var childNode = node.childNodes[i];
			if(!childNode.get("checked")){
				flag = false;
			}
		}
		node.allChildrenNodeChecked = flag;
    },
	// 监听事件
    listeners: {
    	itemcontextmenu : treeRightKeyAction,
		checkchange : function(node, checked) {
			var me = this;
			if (checked == true) {
				var pNode = node.parentNode;
				if(pNode!=null){
					// 选择所有的父节点
					for (; pNode.data.id != FunctionChooesTree.store.tree.root.data.id; pNode = pNode.parentNode) {
						if (pNode.data.checked == true)
							break;
						pNode.set("checked", true);
						//修改：ztjie 修改时间：2012-06-08 修改内容：增加判断当前节点下的子节点是
						//否全部选中的设置
						FunctionChooesTree.itemcollapse(pNode, null);		
					}				
				}
				selectChildFunction(node, true);
				//修改：ztjie 修改时间：2012-06-08 修改内容：增加当前节点选中的时候，
				//那么其所有子节点是选中的
				node.allChildrenNodeChecked = true;
			} else {
				selectChildFunction(node, false);
				// 判断父节点下是否还有子节点是选中状态
				deSelectParentFunction(node);
			}
		},
		//修改：ztjie 修改时间：2012-06-08 修改内容：增加收缩的时候，判断判断当前节点下的子节点是
		//否全部选中的设置
		itemcollapse : function(node, state) {
			var flag = true;//默认全部选中
			for (var i = 0; i < node.childNodes.length; i++) {
				var childNode = node.childNodes[i];
				if(!childNode.get("checked")){
					flag = false;
				}
			}
			node.allChildrenNodeChecked = flag;		
		},
		itemexpand : function(node, state) {
			if(node.modified.checked==null){
				return;
			}
			if((typeof node.allChildrenNodeChecked)!='undefined'&&!node.allChildrenNodeChecked){
				return;
			}
			//得到更改后的选中判断值
			var checked = !node.modified.checked;

			/* ***** 选中时将子节点全部选中****** */
			var parentNode = node;
			if (parentNode.hasChildNodes()) {
				for (var i = 0; i < parentNode.childNodes.length; i++) {
					var childNode = parentNode.childNodes[i];
					childNode.set("checked", checked);
					selectChildFunction(childNode, checked);
				}
			}
		},
		scrollershow: function(scroller) {
	    	if (scroller && scroller.scrollEl) {
				scroller.clearManagedListeners(); 
				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
			}
		}
	}
});

/**
 * 当所有子节点没有选中时候，取消选择父节点
 */
var deSelectParentFunction = function(node) {
	if (node.data.id == FunctionChooesTree.store.tree.root.data.id)
		return;
	var parentNode = node.parentNode;
	if (parentNode.hasChildNodes()) {
		for (var i = 0; i < parentNode.childNodes.length; i++) {
			var childNode = parentNode.childNodes[i];
			if (childNode.data.checked == true) {
				return;
			}
		}
	}
	if(parentNode.data.id != FunctionChooesTree.store.tree.root.data.id){
		parentNode.set("checked", false);		
	}
	deSelectParentFunction(parentNode);
};

/**
 * 父节点选中时，选择所有子节点
 */
var selectChildFunction = function(node, checked) {
	Ext.Array.each(node.childNodes, function(childNode) {
		childNode.set("checked", checked);
		selectChildFunction(childNode, checked);
	});
};


/**
 * 角色编辑窗口
 */
var flag=null;
var RoleFormWindow = Ext.create('PopWindow', {
	closable : true,
	modal : true,
	resizable:false,
	closeAction : 'hide',
	layout : 'border',
	title : i18n('i18n.authorization.window_title'),
	width :820,
	height :530,
	listeners:{
    	 beforeshow:function(){
    		 FunctionChooesTree.setVisible(false);
    		 FunctionChooesTree.setVisible(true);
    		 FunctionChooesTree.collapseAll();
    		 FunctionChooesTree.expandAll();
    	 }
    },
	items : [ {
		width :'100%',
		height :80,
		margin:'0 0 5 0',
		region : 'north',
		border : false,
		items : [ RoleForm ]
	}, {
		region : "center", // 中间布局
		border : false,
		items : [ {
//			title: i18n('i18n.authorization.rolefunction'),
			width :788,
			height :450,
			border : false,
			items : [ FunctionChooesTree ]
		} ]
	}],
	fbar:[{
			text : i18n('i18n.authorization.save'),
			handler : function() {
	        	var form = RoleForm.getForm();
	        	if (form.isValid()) { 
	        		var new_record = form.getRecord();
	        		//var oldRoleName=new_record.data.roleName;
	        		form.updateRecord(new_record);
	        		var _funs = new Array();
	        		var nodes = FunctionChooesTree.getChecked();
					Ext.each(nodes, function(node) {
						var validFlag = false;
						if(!node.isLeaf()&&(node.childNodes==null||node.childNodes.length<=0)){
							validFlag = true;
						}
						var fun = {
								'id' : node.raw.entity.id,
								'validFlag' : validFlag
						};
						_funs.push(fun);
					});
	        		var array = {role:new_record.data,chooesFunctions:_funs};//,oldRoleName:oldRoleName};
		        	Ext.Ajax.request({
						  url : roleFormUrl,
						  jsonData:array,
						  success : function(response) {
								var json = Ext.decode(response.responseText);
								RoleStore.load();
								if(json.isException){
									MessageUtil.showMessage(json.message);
								}else{
									MessageUtil.showInfoMes(json.message);
									RoleFormWindow.hide();
								}
							
						   },
						   failure : function(response) {
								var json = Ext.decode(response.responseText);
								MessageUtil.showMessage(json.message);
						   }
					   });
		        	}
			}
		}, {
			text : i18n('i18n.authorization.reset'),
			id:'reset_button',
			handler : function(button,even) {
				button.setDisabled(true);
				if(roleFormUrl=='saveRole.action'){
					var role = new RoleModel();
					RoleForm.loadRecord(role);
					RoleForm.getForm().reset();
				}else if(roleFormUrl=='updateRole.action'){
					var selection = RoleGrid.getSelectionModel().getSelection();
					var record = selection[0];
					RoleForm.loadRecord(record);
				}
				var rootNode = FunctionChooesTree.getRootNode();
				onRefreshTreeNodes(FunctionTreeStore,rootNode.get("id"));
				
				
			}
		}]
});
