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
//						Ext.MessageBox.alert(i18n('i18n.authorization.message'),i18n('i18n.authorization.operation_message'));
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
//						Ext.MessageBox.alert(i18n('i18n.authorization.message'),i18n('i18n.authorization.operation_message'));
						MessageUtil.showMessage(i18n('i18n.authorization.operation_message'));
						return;
					}
					if (selection.length>1) {
//						Ext.MessageBox.alert(i18n('i18n.authorization.message'),i18n('i18n.authorization.operation_message'));
						MessageUtil.showMessage(i18n('i18n.authorization.operation_message'));
						return;
					}
					var msg=':';
					msg=msg+selection[0].get('roleName')+'!';
					Ext.MessageBox.confirm(i18n('i18n.authorization.confirm')
							,i18n('i18n.authorization.confirm_message')+msg,
						function(e) {
							if (e == 'yes') {
								Ext.Ajax.request({
									url : 'deleteRole.action',
									params : {
										'roleId' : selection[0].get('id')
									},
									success : function(response) {
										var json = Ext.decode(response.responseText);
//										Ext.MessageBox.alert(i18n('i18n.authorization.message'),json.message);
										MessageUtil.showMessage(json.message);
										if(json.success){
											RoleStore.load();					
										}
									},
									failure : function(response) {
										var json = Ext.decode(response.responseText);
//										Ext.MessageBox.alert(i18n('i18n.authorization.message'),json.message);
										MessageUtil.showMessage(json.message);
									}
								});
							}
						});
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