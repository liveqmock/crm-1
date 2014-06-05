//角色store
var RoleStore = Ext.create('Ext.data.Store', {
	pageSize : 20,
	model : 'RoleModel',
	remoteSort : true,
	autoLoad : true,
	proxy : {
		type : 'ajax',
		actionMethods : 'POST',
		url : 'findAllRole.action',
		reader : {
			type : 'json',
			root : 'roleList',
			totalProperty : 'totalCount'
		}
	},
	listeners: {
		beforeload: function(store, operation, eOpts){
			var query_role_form = Ext.getCmp("query_role_form");
			if(query_role_form!=null){
				Ext.apply(operation,{
					params : {
							'role.roleName' : query_role_form.getForm().findField('roleName').getValue()
							//,'role.status' : status
						}
					}
				);	
			}
		}
    }
});
/**
 * 角色列表
 */
var RoleGrid = Ext.create('SearchGridPanel',{
	title : i18n('i18n.authorization.role.roleGrid'),
    sortableColumns:false,
    enableColumnHide:false,
    enableColumnMove:false,
    height:'100%',
    id:'roleGrid',
    border:true,
    //增加滚动条
    autoScroll : true,
	stripeRows : true, // 交替行效果
	viewConfig : {
		autoFill : true,
		forceFit : true
	},
	selType : "rowmodel", // 选择类型设置为：行选择
	store : RoleStore,
	columns : [{
			text : i18n('i18n.authorization.role.roleName'),
			width:window.screen.availWidth*0.2485,
			dataIndex : 'roleName'
		},{
			text : i18n('i18n.authorization.role.roleDesc'),
			width:window.screen.availWidth*0.4971,
			dataIndex : 'roleDesc'
		}],
		tbar : [{
				text : i18n('i18n.authorization.add'),
				hidden:!isPermission('/authorization/saveRole.action'),
				handler : function() {
					roleFormUrl = 'saveRole.action';
					var role = new RoleModel();
					RoleForm.loadRecord(role);
					RoleForm.getForm().reset();
					var rootNode = FunctionChooesTree.getRootNode();
					onRefreshTreeNodes(FunctionTreeStore,rootNode.get("id"));
					RoleFormWindow.show();
				}
			}, {
				text : i18n('i18n.authorization.update'),
				hidden:!isPermission('/authorization/updateRole.action'),
				handler : function() {
					var selection = RoleGrid.getSelectionModel().getSelection();
					if (selection.length != 1) {
						MessageUtil.showMessage(i18n('i18n.authorization.operation_message'));
						return;
					} else {
						roleFormUrl = 'updateRole.action';
						var record = selection[0];
						RoleForm.loadRecord(record);
						var rootNode = FunctionChooesTree.getRootNode();
						onRefreshTreeNodes(FunctionTreeStore,rootNode.get("id"));
						RoleFormWindow.show();
					}
				}
			}, {
				text : i18n('i18n.authorization.delete'),
				hidden:!isPermission('/authorization/deleteRole.action'),
				handler : function() {
					var selection = RoleGrid.getSelectionModel().getSelection();
					if (selection.length == 0) {
						MessageUtil.showMessage(i18n('i18n.authorization.operation_message'));
						return;
					}
					if (selection.length>1) {
						MessageUtil.showMessage(i18n('i18n.authorization.operation_message'));
						return;
					}
					var msg=':';
					msg=msg+selection[0].get('roleName')+'!';
					MessageUtil.showQuestionMes(
							i18n('i18n.authorization.confirm_message')+msg,
						function(e) {
							if (e == 'yes') {
								Ext.Ajax.request({
									url : 'deleteRole.action',
									params : {
										'roleId' : selection[0].get('id')
									},
									success : function(response) {
										var json = Ext.decode(response.responseText);
										MessageUtil.showMessage(json.message);
										if(json.success){
											RoleStore.load();					
										}
									},
									failure : function(response) {
										var json = Ext.decode(response.responseText);
										MessageUtil.showMessage(json.message);
									}
								});
							}
						});
			   }
			}, {
				/**
				 * 修改内容：添加查看详情按钮，点击查看详情
				 * 做选中一条校验
				 * 然后将数据传给查看详情window
				 * 将window弹出，显示内容
				 * 时间：2013-07-01 9:34
				 * 修改人：张斌
				 */
				text : i18n('i18n.authorization.searchInfoAll'),//查看详情
				handler : function() {
					var selection = RoleGrid.getSelectionModel().getSelection();//获取选中的一个角色
					if (selection.length != 1) {//如果没有选中一条，则提示“请选择一条记录！”
						MessageUtil.showMessage(i18n('i18n.authorization.operation_message'));
						return;
					}
					var showWindow = Ext.getCmp('searchInfoWindow');
					if(Ext.isEmpty(showWindow)){
						showWindow = Ext.create('CRM.Role.SearchInfoWindow',{
							id:'searchInfoWindow'
						});
					};
					showWindow.roleInfo = selection[0];
					showWindow.show();
					showWindow.getFunctionChooesTree().expandPath(expandPath,'id','.');

				}
			} ],
			bbar : Ext.create('Ext.toolbar.Paging', {
				id : 'RoleGrid_pagingToolbar',
				store : RoleStore,
				displayMsg : i18n('i18n.authorization.roleGrid.displayMsg'),
				displayInfo : true,
				items:[
		            '-',{
							text: i18n('i18n.authorization.RoleGrid.page_count'),
							xtype: 'tbtext'
						},Ext.create('Ext.form.ComboBox', {
		                   width:          window.screen.availWidth*0.0391,
		                   value:          '20',
		                   triggerAction:  'all',
		                   forceSelection: true,
		                   editable:       false,
		                   name:           'comboItem',
		                   displayField:   'value',
		                   valueField:     'value',
		                   queryMode:      'local',
		                   store : Ext.create('Ext.data.Store',{
		                       fields : ['value'],
		                       data   : [
		                           {'value':'10'},
		                           {'value':'15'},
		                           {'value':'20'},
		                           {'value':'25'},
		                           {'value':'40'},
		                           {'value':'100'}
		                       ]
		                   }),
		                   listeners:{
								select : {
									scope : this,
						            fn: function(_field,_value){
						            	var pageSize = RoleStore.pageSize;
						            	var newPageSize = parseInt(_field.value);
						            	if(pageSize!=newPageSize){
						            		RoleStore.pageSize = newPageSize;
						            		Ext.getCmp('RoleGrid_pagingToolbar').moveFirst();
						            	}
						            }
						        }
		                   }
		               }),{
							text: i18n('i18n.authorization.RoleGrid.number'),
							xtype: 'tbtext'
		               }]
			})
	});
//-------------------------增加查看详情功能------------------------
var expandPath = '.0.01';//展开路径
/**
 * 修改内容：增加查看详情window，里面查看角色的名称描述以及该角色所拥有的权限
 * 时间：2013-07-01 10:00
 * 修改人：张斌
 */
Ext.define('CRM.Role.SearchInfoWindow', {
	roleInfo:null,//选中的角色的信息
	extend:'PopWindow',
	closable : true,
	modal : true,
	resizable:false,
	//closeAction : 'hide',//只有将window关闭的时候销毁，才能创建一个干净的treeStore.
	//如果只是hide,那么就得用
	/*var node = treeStore.getNodeById('0'); 
	treeStore.load({
		node : node
	});*/
	//在beforeshow设置好参数后，让其load一次根节点
	//但是如果加上自动展开，就没有办法实现了(showWindow.getFunctionChooesTree().expandPath(expandPath,'id','.');)
	//只能重新让其创建一个新的store
	layout : 'border',
	title : i18n('i18n.authorization.searchInfoAll'),
	width :820,
	height :530,
	listeners:{
		 //在显示之前加载数据
		 //1、加载角色的名称描述2、加载该角色所拥有的权限
    	 beforeshow:function(me){
    		 me.getRoleForm().getForm().loadRecord(me.roleInfo);//该角色所拥有的权限
    		 me.getFunctionChooesTree().getFunctionTreeStore().roleId = me.roleInfo.get('id');//设置角色ID，传到功能树的store中
    	 },
    	//在隐藏之前清楚数据
		 //1、将from做reset操作2、将权限树形表的数据清除
    	 beforehide:function(me){
    		 me.getRoleForm().getForm().reset();
    		 me.roleInfo = null;//角色信息是空
    	 }
    },
	//角色基本信息form
    roleForm:null,
    getRoleForm : function(){
    	if(Ext.isEmpty(this.roleForm)){
    		this.roleForm = Ext.create('CRM.Role.RoleForm');
    	}
    	return this.roleForm;
    },
    //角色所拥有的权限树形表
    functionChooesTree:null,
    getFunctionChooesTree : function(){
    	if(Ext.isEmpty(this.functionChooesTree)){
    		this.functionChooesTree = Ext.create('CRM.Role.FunctionChooesTree');
    	}
    	return this.functionChooesTree;
    },
    //在构造函数里面组装window中的元素
	initComponent : function() {
		var me= this;
		me.items  =  [ {
    		width :'100%',
    		height :80,
    		margin:'0 0 5 0',
    		region : 'north',
    		border : false,
    		items : [ me.getRoleForm() ]
    	}, {
    		region : "center", // 中间布局
    		border : false,
    		items : [ {
    			width :788,
    			height :450,
    			border : false,
    			items : [me.getFunctionChooesTree()]
    		} ]
    	}];
		//me.getFunctionChooesTree().roleId = me.roleInfo.get('id');//在渲染页面的时候，还没有获取了roleInfo，是create完成之后才会将roleInfo传给window
		this.callParent();
	}
});
/**
 * 修改内容：角色基本信息from,查看角色的名称和描述
 * 时间：2013-07-01 10:00
 * 修改人：张斌
 */
Ext.define('CRM.Role.RoleForm',{
	extend:'Ext.form.FormPanel',
	frame : true,
	initComponent : function() {
		var me= this;
		me.items =[{
	        xtype:'fieldset',
	        layout: {
	        	type: 'table',
	        	columns: 4
	        },
	        title: i18n('i18n.authorization.role.roleform'),//角色详细信息
	        frame:true,
	        height:60,
	        //删除收缩按钮
	        //collapsible: true,
	        defaults: {
	        	margin:'5 10 5 10',
	            anchor: '55%',
	            labelWidth:60
	        },
	        items :[{
				name : 'id',
				hidden : true
			},{
				name: 'roleName',
				colspan: 1,
				readOnly:true,
				cls:'readonly',
				xtype : 'textfield',
		        fieldLabel: i18n('i18n.authorization.role.roleName')//角色名称
			},{
				name: 'roleDesc',
				colspan: 3,
				readOnly:true,
				cls:'readonly',
				width:500,
		        fieldLabel: i18n('i18n.authorization.role.roleDesc'),//描述
		        xtype : 'textfield'
	        }]
		}];
		this.callParent();
	}
});
/**
 * 修改内容：定义功能树的store
 * 时间：2013-07-01 10:45
 * 修改人：张斌
 */
Ext.define('CRM.Role.FunctionTreeStore',{
	roleId:null,//角色ID
	extend:'Ext.data.TreeStore',
	model : 'TreeNodeModel',
	proxy : {
		type : 'ajax',
		actionMethods : 'POST',
		url:'searchInfoFunctionChooesTree.action'//URL
	},
	root : {
		id : '0',
		text : i18n('i18n.authorization.Function.app'),
		//expanded : true,
		checked:null//跟节点不显示复选框
	},
	listeners : {
		'beforeload': function(store, operation, eOpts){
			if(Ext.isEmpty(store.roleId)){//如果roleId是空，则不执行查询
				return;
			}
			if(operation.params==null){
				operation.params = {};
			}
			Ext.apply(operation.params,{
				'roleId' : store.roleId
			});
		}
	}
});
/**
 * 修改内容：定义功能树
 * 时间：2013-07-01 10:50
 * 修改人：张斌
 */
Ext.define('CRM.Role.FunctionChooesTree', {
	extend:'Ext.tree.Panel',
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
    multiSelect: true,
	// 监听事件
    listeners: {
		scrollershow: function(scroller) {
	    	if (scroller && scroller.scrollEl) {
				scroller.clearManagedListeners(); 
				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
			}
		}
	},
	//功能树的store
	functionTreeStore:null,
	getFunctionTreeStore:function(){
		if(Ext.isEmpty(this.functionTreeStore)){
    		this.functionTreeStore = Ext.create('CRM.Role.FunctionTreeStore');
    	}
    	return this.functionTreeStore;
	},
	initComponent : function() {
		var me= this;
		me.columns = [{
	        xtype: 'treecolumn',
	        text: i18n('i18n.authorization.Function.functionName'),//功能名称
	        width:230,
			dataIndex : 'text'
	    },{
	    	text: i18n('i18n.authorization.Function.URI'),//功能入口URI
			dataIndex : 'entity',
			renderer : function(value){
				if(value==null){//如果是空，不显示null，显示空字符串
					return '';
				}
				return value.uri;
			},
			width:250
	    },{
			text : i18n('i18n.authorization.role.roleDesc'),//描述
			width:250,
			dataIndex : 'entity',
			renderer : function(value){
				if(value==null){//如果是空，不显示null，显示空字符串
					return '';
				}
				return value.functionDesc;
			}
		} ];
		me.store = me.getFunctionTreeStore();//获取store
		this.callParent();
	}
});