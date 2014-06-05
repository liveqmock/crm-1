Ext.require([ 'Ext.form.*', 'Ext.data.*', 'Ext.grid.Panel',
		'Ext.layout.container.Column' ]);
/**
 * 修改date对象数据的JSON提交方式
 */
Ext.JSON.encodeDate = function(date) {
	return date.getTime();
};

/**
 * 日期类型数据转换器
 * 
 * @param value
 * @param record
 * @returns
 */
function dateConvert(value, record) {
	if (value != null) {
		var date = new Date(value);
		return date;
	} else {
		return null;
	}
}

/**
 * 设置表格对象中tbar按钮是否显示
 * 
 * @param grid
 *            表格对象
 * @param trueorfalse
 *            是否显示
 */
function setTbarHidden(grid, trueorfalse) {
	if(grid==null){
		return;
	}
	var dockedItems = grid.dockedItems;
	if(dockedItems==null||dockedItems.length<=0){
		return;
	}
	var items = dockedItems.items;
	if(items==null||items.length<=0){
		return;
	}
	Ext.Array.each(items, function(item) {
		if(item.xtype=="toolbar"){
			var buttons = item.items.items;
			if(buttons==null||buttons.length<=0){
				return;
			}
			Ext.Array.each(buttons, function(button) {
				button.setDisabled(!trueorfalse);
			});
			return;
		}
    });
}

/**
 * 改变是否字段的显示值
 * 
 * @param val
 * @returns
 */
function change(val) {
	if(val == null){
		return;
	}
    if (val == true) {
        return '是';
    } else {
        return '否';
    }
}

/**
 * 快速定位
 * 
 * @TODO
 */
function quickLocate() {
	if(Ext.getCmp('quickLocate_name')==null){
		return;
	}
	var quickName = Ext.getCmp('quickLocate_name').getValue();
	if(quickName==null||quickName==''){
		return;
	}
	Ext.Ajax.request({
		url : 'findTreeNodeByCode.action',
		params : {
			'node' : quickName
		},
		success : function(response) {
			var json = Ext.decode(response.responseText);
			if (json.success) {
				if(root!=null){
					root.raw = json.treeNode;
				}
			}
		}
	});
// store.addListener( 'beforeload',function(store, operation, eOpts){
// var queryLocate_name = Ext.getCmp('quickLocate_name');
// if(queryLocate_name!=null){
// var funName = queryLocate_name.getValue();
// if(funName!=null&&funName!=''){
// if(operation.params==null){
// operation.params = {};
// }
// Ext.apply(operation.params,{
// 'function.functionName' : funName
// });
// }
// }
// });
// FunctionTree.expandAll();
};

/**
 * treePanel的节点异步刷新事件
 * 
 * @param parentId
 *            父节点ID
 */
function onRefreshTreeNodes(treeStore,parentId){
	var node = treeStore.getNodeById(parentId); 
	treeStore.load({
		node : node
	});
}

/** **************************************功能区******************************************* */
/**
 * 选中角色列表中的一行或多行移动至另一个角色列表中
 * @param grid 选中的列表
 * @param addStore 增加记录的store
 * @param removeStore 删除记录的store
 * @name select
 */
function select(grid,addStore,removeStroe) {
	var _records = grid.getSelectionModel().getSelection();
	if (_records == null||_records.length<=0) {
		MessageUtil.showMessage(i18n('i18n.authorization.operation_messages'));
		return;
	}
	Ext.each(_records, function(item) {
		addStore.insert(0,item);
	});
	removeStroe.remove(_records);
};

/**
 * 角色列表中的所有记录移动至另一个角色列表中
 * @param grid 选中的列表
 * @param addStore 增加记录的store
 * @param removeStore 删除记录的store
 * @name select
 */
function allSelect(grid,addStore,removeStore) {
	var count = removeStore.getCount();
	if(count==null||count<=0){
		MessageUtil.showMessage(i18n('i18n.authorization.noRecord'));
		return;
	}
	removeStore.each( function(_record) {
		addStore.insert(0,_record);
	},removeStore);
	// 从待选运单列表中移除该记录
	removeStore.removeAll();
};

/**
 * function的Model
 */
Ext.define('FunctionModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id'
	}, {
		name : 'functionCode'
	}, {
		name : 'functionName'
	}, {
		name : 'uri'
	}, {
		type : 'int',
		name : 'functionLevel'
	}, {
		name : 'parentCode'
	}, {
		type : 'boolean',
		name : 'validFlag'
	}, {
		defaultValue : new Date(),
		convert : dateConvert,
		type : 'date',
		name : 'invalidDate'
	}, {
		defaultValue : new Date(),
		convert : dateConvert,
		type : 'date',
		name : 'validDate'
	}, {
		type : 'int',
		name : 'displayOrder'
	}, {
		type : 'boolean',
		name : 'check'
	}, {
		type : 'int',
		name : 'functionType'
	}, {
		name : 'functionDesc'
	}, {
		name : 'functionSeq'
	}, {
		name : 'createUser'
	}, {
		name : 'createDate',
		defaultValue : new Date(),
		convert : dateConvert,
		type : 'number'
	}, {
		name : 'modifyUser'
	}, {
		name : 'modifyDate',
		defaultValue : new Date(),
		convert : dateConvert,
		type : 'number'
	} ]
});

/**
 * treeNode的Model
 */
Ext.define('TreeNodeModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id'
	}, {
		name : 'text'
	}, {
		name : 'parentId'
	}, {
		type : 'boolean',
		name : 'leaf'
	}, {
		name : 'checked'
	}, {
		name : 'entity'
	}, {
		name : 'childChecked',
		type : 'boolean'
	}]
});
/**
 * treeNode的Model
 */
Ext.define('DepartmentModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id'
	}, {
		name : 'deptName'
	}, {
		name : 'principal'
	}, {
		name : 'parentCode'
	}, {
		name : 'deptDesc'
	}]
});

/**
 * role的Model
 */
Ext.define('RoleModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id'
	}, {
		name : 'roleName'
	}, {
		name : 'roleDesc'
	}, {
		type : 'int',
		name : 'status'
	}, {
		defaultValue : new Date(),
		convert : dateConvert,
		type : 'date',
		name : 'invalidDate'
	}, {
		defaultValue : new Date(),
		convert : dateConvert,
		type : 'date',
		name : 'validDate'
	}, {
		name : 'createUser'
	}, {
		name : 'createDate',
		defaultValue : new Date(),
		convert : dateConvert,
		type : 'number'
	}, {
		name : 'modifyUser'
	}, {
		name : 'modifyDate',
		defaultValue : new Date(),
		convert : dateConvert,
		type : 'number'
	} ]
});

/**
 * user的Model
 */
Ext.define('UserModel', {
	extend : 'Ext.data.Model',
	fields : [{
			name : 'id'
		},{
			name: 'empCode'
		},{
			name: 'loginName'
		},{
			defaultValue : '123456',
			name: 'password'
		},{
			defaultValue : new Date(),
			convert : dateConvert,
			type : 'date',
			name: 'lastLogin'
		},{
			type : 'int',
			name: 'status'
		},{
			defaultValue : new Date(),
			convert : dateConvert,
			type : 'date',
			name: 'invalidDate'
		},{
			defaultValue : new Date(),
			convert : dateConvert,
			type : 'date',
			name: 'validDate'
		},{
			name :'createUser'
		},{
			name : 'createDate',
			defaultValue : new Date(),
			convert : dateConvert,
			type : 'number'
		},{
			name : 'modifyUser'
		},{
			name : 'modifyDate',
			defaultValue : new Date(),
			convert : dateConvert,
			type : 'number'
		}]
});

/**
 * employee的Model
 */
Ext.define('EmployeeModel', {
    extend: 'Ext.data.Model',
    fields : [{
			name : 'id'
		},{
			name: 'deptCode'
		},{
			name: 'empCode'
		},{
			name: 'empName'
		},{
			type : 'boolean',
			name: 'gender'
		},{
			defaultValue : new Date(),
			convert : dateConvert,
			type : 'date',
			name: 'birthdate'
		},{
			type : 'boolean',
			name: 'status'
		},{
			defaultValue : new Date(),
			convert : dateConvert,
			type : 'date',
			name: 'inDate'
		},{
			defaultValue : new Date(),
			convert : dateConvert,
			type : 'date',
			name: 'outDate'
		},{
			name: 'offerTel'
		},{
			name: 'offerAddress'
		},{
			name: 'offerZipCode'
		},{
			name: 'offerEmail'
		},{
			name: 'mobileNumber'
		},{
			name: 'homeTel'
		},{
			name: 'homeAddress'
		},{
			name: 'homeZipCode'
		},{
			name: 'personEmail'
		},{
			name: 'workExp'
		},{
			name: 'remark'
		},{
			name : 'createUser'
		},{
			name : 'createDate',
			defaultValue : new Date(),
			convert : dateConvert,
			type : 'date'
		},{
			name:'modifyUser'
		},{
			name : 'modifyDate',
			defaultValue : new Date(),
			convert : dateConvert,
			type : 'date'
		},{
            name : 'position'
        }]
});

//职员store
var EmployeeStore = Ext.create('Ext.data.Store', {
	pageSize : 10,
	model : 'EmployeeModel',
	proxy : {
		type : 'ajax',
		actionMethods : 'POST',
		url : 'findAllEmployee.action',
		reader : {
			type : 'json',
			root : 'employeeList',
			totalProperty : 'totalCount'
		}
	},
   listeners:{
		beforeload:function(store, operation, eOpts){
			Ext.apply(operation,{
				params : {
					'empCode.empCode':Ext.getCmp('deptCode_ComBox').getValue()
					}
				}
			);	
		}
	}
});


/**
 * department的Model
 */
Ext.define('DepartmentModel', {
    extend: 'Ext.data.Model',
    fields : [{
			name : 'id'
		},{
			name: 'deptCode'
		},{
			name: 'deptName'
		},{
			name: 'principal'
		},{
			name: 'principalName'//负责人姓名
		},{
			name: 'phone'
		},{
			name: 'fax'
		},{
			name: 'parentCode'
		},{
			name: 'companyName'
		},{
			name: 'zipCode'
		},{
			name: 'address'
		},{
			name: 'zipCode'
		},{
			type : 'boolean',
			name: 'status'
		},{
			defaultValue : new Date(),
			convert : dateConvert,
			type : 'date',
			name: 'validDate'
		},{
			defaultValue : new Date(),
			convert : dateConvert,
			type : 'date',
			name: 'invalidDate'
		},{
			type: 'int',
			name: 'displayOrder'
		},{
			type: 'int',
			name: 'deptLevel'
		},{
			type : 'boolean',
			name: 'leaf'
		},{
			name: 'deptDesc'
		},{
			name: 'deptSeq'
		},{
			name : 'createUser'
		},{
			name : 'createDate',
			defaultValue : new Date(),
			convert : dateConvert,
			type : 'date'
		},{
			name:'modifyUser'
		},{
			name : 'modifyDate',
			defaultValue : new Date(),
			convert : dateConvert,
			type : 'date'
		}]
});
/*------------------------------------部门树------------------------------------------*/
/**
 * 定义功能树的store
 */
var dept=null;
var DeptTreeStore = Ext.create('Ext.data.TreeStore',{
	autoSync:true,
	proxy : {
		type : 'ajax',
		actionMethods:'POST',
		url : 'loadTree.action'
	},
	root : {
		//把id为0改成103  得到OA组织架构树
		id : '103',
		text : i18n('i18n.organization.rootAndCompanyName'),
		
		expanded : true
	},
	sorters : [ {
		property : 'leaf',
		direction : 'ASC'
	}]
});
var findDeptCode;
var json=null;
/**
 *树的面板
 */
var TreePanle=Ext.create('Ext.tree.Panel', {
	//useArrows : true,
	xtype:'treepanel',
	margin:false,
	height:'100%',
	//autoScroll:true,
	id:'mainTree', 
	cls:'normaltree',
	store: DeptTreeStore,
	rootVisible: true,       
	title: i18n('i18n.authorization.deptTree'),
	layoutConfig : {
		// 展开折叠是否有动画效果
		animate : true
	},
	// 监听事件
    listeners: {
    	itemclick : treeLeftKeyAction,
		scrollershow: function(scroller) {
	    	if (scroller && scroller.scrollEl) {
				scroller.clearManagedListeners(); 
				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
			}
		}
    }
});
/**
 * 树节点的左键单击事件
 */
var dept=null;
function treeLeftKeyAction(node,record,item,index,e){
	if(record.data.id!='root'){
		dept=record;
		Ext.getCmp('UserGrid_pagingToolbar').moveFirst();
		//pageStore.load({params:{deptCode:record.raw.entity.deptCode}});
	}
}