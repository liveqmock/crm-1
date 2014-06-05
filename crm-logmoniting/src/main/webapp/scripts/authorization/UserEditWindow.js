//未分配角色选择store
var RoleChooesGridStore = Ext.create('Ext.data.Store', {
	pageSize : 20,
	model : 'RoleModel',
	remoteSort : true,
	autoLoad : false,
	proxy : {
		type : 'ajax',
		actionMethods : 'POST',
		url : 'findLeftRoles.action',
		reader : {
			type : 'json',
			root : 'roleList',
			totalProperty : 'totalCount'
		}
	}
});
/**
 * 未分配角色选择列表
 */
var RoleChooesGrid = Ext.create('Ext.grid.GridPanel', {
	title : i18n('i18n.authorization.authChooseRoleGrid'),
    sortableColumns:false,
    enableColumnHide:false,
    enableColumnMove:false,
    height :365,
    listeners: {
    	scrollershow: function(scroller) {
    		if (scroller && scroller.scrollEl) {
    				scroller.clearManagedListeners(); 
    				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
    		}
    	}
    },
	width : 365,    
	autoScroll : true,
	stripeRows : true, // 交替行效果
	selType : "rowmodel", // 选择类型设置为：行选择
	store : RoleChooesGridStore,
	selModel :  Ext.create("Ext.selection.CheckboxModel"),
	// 列模板
	columns : [{
		text : i18n('i18n.authorization.role.roleName'),
		width:120,
		dataIndex : 'roleName'
	}, {
		text : i18n('i18n.authorization.role.roleDesc'),
		width:200,
		dataIndex : 'roleDesc'
	} ]
});

// 已分配角色store
var UserRoleGridStore = Ext.create('Ext.data.Store', {
	pageSize : 20,
	model : 'RoleModel',
	remoteSort : true,
	autoLoad : false,
	proxy : {
		type : 'ajax',
		actionMethods : 'POST',
		url : 'findAuthedRoles.action',
		reader : {
			type : 'json',
			root : 'roleList',
			totalProperty : 'totalCount'
		}
	}
});

/**
 * 已分配角色选择列表
 */
var UserRoleGrid = Ext.create('Ext.grid.GridPanel', {
	title : i18n('i18n.authorization.authRoleGrid'),
	sortableColumns:false,
    enableColumnHide:false,
    enableColumnMove:false,
    height :365,
    width : 365, 
    autoScroll : true,
	stripeRows : true, // 交替行效果
	selType : "rowmodel", // 选择类型设置为：行选择
	store : UserRoleGridStore,
    listeners: {
    	scrollershow: function(scroller) {
    		if (scroller && scroller.scrollEl) {
    				scroller.clearManagedListeners(); 
    				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
    		}
    	}
    },
	selModel : Ext.create("Ext.selection.CheckboxModel"),
	// 列模板
	columns : [{
		text : i18n('i18n.authorization.role.roleName'),
		width:120,
		dataIndex : 'roleName'
	}, {
		text : i18n('i18n.authorization.role.roleDesc'),
		width:200,
		dataIndex : 'roleDesc'
	} ]
});

/**
 * 移动按钮
 */
var buttonPanelRole = Ext.create('Ext.panel.Panel',{
	height :365,
	width : 50,
	buttonAlign : 'center',
	layout : 'column',
	items : [{
			columnWidth : 1,
			height : 0,
			xtype : 'container',
			style : 'padding-top:60px;border:none',
			width : '100%',
			hide:true
		},{
			columnWidth : 1,
			xtype : 'container',
			style : 'padding-top:60px;border:none',
			width : '100%',
			items : [ {
				xtype : 'button',
				text : '&gt;&gt;',
				width : '80%',
				// 添加所有角色
				handler : function(){
					RoleChooesGrid.view.refresh();
					allSelect(RoleChooesGrid,UserRoleGridStore,RoleChooesGridStore);
				}
			} ]
		},
		{
			columnWidth : 1,
			width : '100%',
			xtype : 'container',
			style : 'padding-top:20px;border:none',
			items : [ {
				xtype : 'button',
				text : '&nbsp;&gt;&nbsp;',
				width : '80%',
				// 添加角色
				handler : function(){
					RoleChooesGrid.view.refresh();
					select(RoleChooesGrid,UserRoleGridStore,RoleChooesGridStore);
				}
			} ]
		},
		{
			columnWidth : 1,
			width : '100%',
			xtype : 'container',
			border:false,
			style : 'padding-top:20px;border:none',
			items : [ {
				xtype : 'button',
				text : '&nbsp;&lt;&nbsp;',
				width : '80%',
				// 删除选中角色
				handler : function(){
					RoleChooesGrid.view.refresh();
					select(UserRoleGrid,RoleChooesGridStore,UserRoleGridStore);
				}
			} ]
		},
		{
			columnWidth : 1,
			border:false,
			width : '100%',
			xtype : 'container',
			style : 'padding-top:20px;border:none',
			items : [ {
				xtype : 'button',
				text : '&lt;&lt;',
				width : '80%',
				// 增加所有角色
				handler : function(){
					RoleChooesGrid.view.refresh();
					allSelect(UserRoleGrid,RoleChooesGridStore,UserRoleGridStore);
				}
			} ]
		} ]
});

//未分配部门选择store
var DeptChooesGridStore = Ext.create('Ext.data.Store', {
	pageSize : 20,
	model : 'DepartmentModel',
	remoteSort : true,
	autoLoad : false,
	proxy : {
		type : 'ajax',
		actionMethods : 'POST',
		url : 'findLeftDepts.action',
		reader : {
			type : 'json',
			root : 'deptList',
			totalProperty : 'totalCount'
		}
	},
	listeners: {
		beforeload: function(store, operation, eOpts){
			var deptName = Ext.getCmp('dept.name.search');
			var user= UserGrid.getSelectionModel().getSelection()[0];
			var userId = null;
			if(!Ext.isEmpty(user)){
				userId = user.get('id');
			}
			//var empCode = Ext.getCmp('EditDeptCode_ComBox');
			var params = {
					'deptName' : deptName.getValue(),
					'userId':userId
			}
			if(deptName!=null){
				Ext.apply(operation,{
					params:params
				});
			}
		}
	}
});
/**
 * 未分配部门选择列表
 */

var DeptChooesGrid = Ext.create('Ext.grid.GridPanel', {
	title : i18n('i18n.authorization.authChooseUserGrid'),
	sortableColumns:false,
    enableColumnHide:false,
    enableColumnMove:false,
    height :390,
	width : 365,
	autoScroll : true,
	stripeRows : true, // 交替行效果
	selType : "rowmodel", // 选择类型设置为：行选择
	store : DeptChooesGridStore,
    listeners: {
    	scrollershow: function(scroller) {
    		if (scroller && scroller.scrollEl) {
    				scroller.clearManagedListeners(); 
    				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
    		}
    	}
    },
	selModel : Ext.create("Ext.selection.CheckboxModel"),
	// 列模板
	columns : [{
		text : i18n('i18n.organization.organization.deptName'),
		width:100,
		dataIndex : 'deptName'
	}, {
		text : i18n('i18n.organization.organization.principal'),
		width:65,
		dataIndex : 'principal'
	}, {
		text : i18n('i18n.organization.organization.companyName'),
		width:150,
		dataIndex : 'companyName'
	}],
	tbar: [{
		xtype : 'textfield',
		id:'dept.name.search',
		labelAlign : 'left',
		labelWidth : 60,
		fieldLabel : i18n('i18n.organization.organization.deptName'),
		name : 'deptName'
	},{
		xtype : 'label',
		width : 10
	},{
		text:i18n('i18n.authorization.find'),
		handler:function(){
			DeptChooesGridStore.load();
			UserDeptGridStore.load();
		}
	}]
});

// 已分配部门store
var UserDeptGridStore = Ext.create('Ext.data.Store', {
	pageSize : 20,
	model : 'DepartmentModel',
	remoteSort : true,
	autoLoad : false,
	proxy : {
		type : 'ajax',
		actionMethods : 'POST',
		url : 'findAuthedDepts.action',
		reader : {
			type : 'json',
			root : 'deptList',
			totalProperty : 'totalCount'
		}
	},
	listeners: {
		beforeload: function(store, operation, eOpts){
			var user= UserGrid.getSelectionModel().getSelection()[0];
			//var empCode = Ext.getCmp('EditDeptCode_ComBox');
			var params = {
					'userId':user.get('id')
			}
			Ext.apply(operation,{
				params:params
			});
		}
	}
});
/**
 * 已分配部门选择列表
 */

var UserDeptGrid = Ext.create('Ext.grid.GridPanel', {
	title : i18n('i18n.authorization.authDeptGrid'),
	sortableColumns:false,
    enableColumnHide:false,
    enableColumnMove:false,
    height :390,
	width : 365,
	autoScroll : true,
	stripeRows : true, // 交替行效果
	selType : "rowmodel", // 选择类型设置为：行选择
	store : UserDeptGridStore,
    listeners: {
    	scrollershow: function(scroller) {
    		if (scroller && scroller.scrollEl) {
    				scroller.clearManagedListeners(); 
    				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
    		}
    	}
    },
	selModel : Ext.create("Ext.selection.CheckboxModel"),
	// 列模板
	columns : [{
		text : i18n('i18n.organization.organization.deptName'),
		width:100,
		dataIndex : 'deptName'
	}, {
		text : i18n('i18n.organization.organization.principal'),
		width:80,
		dataIndex : 'principal'
	}, {
		text : i18n('i18n.organization.organization.companyName'),
		width:150,
		dataIndex : 'companyName'
	}]
});

/**
 * 移动按钮
 */
var buttonPanelDept = Ext.create('Ext.panel.Panel',{
	height :390,
	width :50,
	buttonAlign : 'center',
	layout : 'column',
	items : [{
			columnWidth : 1,
			height : 0,
			xtype : 'container',
			style : 'padding-top:60px;border:none',
			width : '100%',
			hide:true
		},{
			columnWidth : 1,
			xtype : 'container',
			style : 'padding-top:60px;border:none',
			width : '100%',
			items : [ {
				xtype : 'button',
				text : '&gt;&gt;',
				width : '80%',
				// 添加所有部门
				handler : function(){
					allSelect(DeptChooesGrid,UserDeptGridStore,DeptChooesGridStore);	
				}
			} ]
		},{
			columnWidth : 1,
			width : '100%',
			xtype : 'container',
			style : 'padding-top:20px;border:none',
			items : [ {
				xtype : 'button',
				text : '&nbsp;&gt;&nbsp;',
				width : '80%',
				// 添加部门
				handler : function(){
					select(DeptChooesGrid,UserDeptGridStore,DeptChooesGridStore);
				}
			} ]
		},{
			columnWidth : 1,
			width : '100%',
			xtype : 'container',
			style : 'padding-top:20px;border:none',
			items : [ {
				xtype : 'button',
				text : '&nbsp;&lt;&nbsp;',
				width : '80%',
				// 删除选中部门
				handler : function(){
					select(UserDeptGrid,DeptChooesGridStore,UserDeptGridStore);	
				}
			} ]
		},{
			columnWidth : 1,
			width : '100%',
			xtype : 'container',
			style : 'padding-top:20px;border:none',
			items : [ {
				xtype : 'button',
				text : '&lt;&lt;',
				width : '80%',
				// 删除所有部门
				handler : function(){
					allSelect(UserDeptGrid,DeptChooesGridStore,UserDeptGridStore);
				}
			} ]
		} ]
});

var UserTab = Ext.createWidget('tabpanel', {
	activeTab : 0,
	plain : true,
	id:'tab',
	items : [ {
		title : i18n('i18n.authorization.authTabNameRole'),
		layout : 'hbox',
		items : [ RoleChooesGrid, buttonPanelRole, UserRoleGrid ]
	}, {
		title : i18n('i18n.authorization.authTabNameDept'),
		layout : 'hbox',
		listeners:{
			activate:function(th,e){
				DeptChooesGridStore.load();
				UserDeptGridStore.load();
			}
		},
		items : [ DeptChooesGrid, buttonPanelDept, UserDeptGrid ]
	}]
});

/**
 * 用户编辑窗口
 */
var UserEditWindow = Ext.create('PopWindow', {
	closable : true,
	modal : true,
	resizable:false,
	closeAction : 'hide',
	layout : 'border',
	title : i18n('i18n.authorization.window_title'),
	height:570,
    width:820,
    listeners:{
    	'beforehide':function(){
    		UserTab.setActiveTab(0);
    	}
    },
	items : [ {
		id:'outForm',
		region : 'north',
		border:false,
		items : [ UserForm ]
	}, {
		id:'outTab',
		border:false,
		region : "center", // 中间布局
		items : [ UserTab ]
	} ]
	,fbar:[{
	       text : i18n('i18n.authorization.save'),
	       handler : function() { 
			var form = UserForm.getForm();
       		var new_record = form.getRecord();
       		form.updateRecord(new_record);
            var empCodeValue = form.findField("empCode.empCode").getValue();
       		var empCode = {'empCode':empCodeValue};
       		new_record.set('empCode',empCode);
       		//if(null!=new_record.get('password')&&new_record.get('password')=='123456'){
       		//	new_record.set('password',hex_md5('123456'));		        			
       		//}
       		//设置默认密码
       		new_record.set('password','123456');	
       		new_record.set('loginName',form.findField("empCode.empCode").getValue());
       		//将选中的角色构成一个数组
       		var _roles = new Array();
       		UserRoleGridStore.each( function(_role) {
       			_roles.push(_role.get('id'));
       		},UserRoleGridStore);
       		//将选中的部门构成一个数组
       		var _userDept = new Array();
				UserDeptGridStore.each(function(_dept) {
					_userDept.push(_dept.get('id'));
				});
				//传递的参数值
				var array = {user:new_record.data,chooesRoles:_roles,chooseDepts:_userDept};
				//如果表单通过验证就提交
				if (form.isValid()) {
					if(!checkInput()){
						return;
					}
					Ext.Ajax.request({
						url : userFormUrl,
						jsonData:array,
						success : function(response) {
							UserStore.load();
							var json = Ext.decode(response.responseText);
							if(json.isException){
//								Ext.MessageBox.alert(i18n('i18n.authorization.message'),json.message);
								MessageUtil.showMessage(json.message);
							}else{
//								Ext.MessageBox.alert(i18n('i18n.authorization.message'),json.message,function(optional){
//									UserEditWindow.hide();
//								});
								MessageUtil.showInfoMes(json.message,function(optional){
									UserEditWindow.hide();
								});
							}
						},
						failure : function(response) {
							var json = Ext.decode(response.responseText);
//							Ext.MessageBox.alert(i18n('i18n.authorization.message'),json.message);
							MessageUtil.showMessage(json.message);
						}
					});
				}else{
//					Ext.MessageBox.alert(i18n('i18n.authorization.message'),i18n('i18n.authorization.InputErrException'));
					MessageUtil.showMessage(i18n('i18n.authorization.InputErrException'));
				}
	       }}
	]
});