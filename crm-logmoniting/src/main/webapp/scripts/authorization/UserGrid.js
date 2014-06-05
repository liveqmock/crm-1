var UserStore = Ext.create('Ext.data.Store', {
	pageSize : 20,
	model : 'UserModel',
	remoteSort : true,
	autoLoad : true,
	proxy : {
		type : 'ajax',
		actionMethods : 'POST',
		url : 'findAllUser.action',
		reader : {
			type : 'json',
			root : 'userList',
			totalProperty : 'totalCount'
		}
	},
	listeners: {
		beforeload: function(store, operation, eOpts){
			var query_user_form = Ext.getCmp("query_user_form");
			if(query_user_form!=null){
				var empCode = null;
				if(Ext.isEmpty(query_user_form.getForm().findField("empCode.empCode").getValue())){
					empCode = query_user_form.getForm().findField("empCode.empCode").getRawValue();
				}else{
					empCode = query_user_form.getForm().findField("empCode.empCode").getValue();
				}
				Ext.apply(operation,{
					params : {
						'user.empCode.empCode':empCode,
						'user.empCode.empName' : query_user_form.getForm().findField('empCode.empName').getValue(),
						'user.status' : query_user_form.getForm().findField('status').getValue()
					}
				});	
			}
		 if(dept!=null&&dept.data.id!='root'){
	    			Ext.apply(operation,{
						params : {
							'deptId':dept.raw.entity.id
							}
						});
	    			dept=null;
	    	   }
		}
    }
});

/**
 * 用户列表
 */
var UserGrid = Ext.create('SearchGridPanel',{
	title : i18n('i18n.authorization.user.userGrid'),
    sortableColumns:false,
    enableColumnHide:false,
    enableColumnMove:false,
    //autoScroll:true,
    id:'userGrid',
    height:'100%',
	defaults: {   
		 flex : 1
}, 
	stripeRows : true, // 交替行效果
	viewConfig : {
		autoFill : true,
		forceFit : true
	},
	selType : "rowmodel", // 选择类型设置为：行选择
	store : UserStore,
	// 列模板
	columns : [{
			id : 'id',
			hidden : true,
			dataIndex : 'id'
		},{
			text : i18n('i18n.authorization.user.empCode'),
			dataIndex : 'empCode',
			width:65,
			renderer : function(value){
				if(value==null){
					return '';
				}
				return value.empCode;
			}
		},{
			text : i18n('i18n.authorization.user.empName'),
			dataIndex : 'empCode',
			width:80,
			renderer : function(value){
				if(value==null){
					return '';
				}
				return value.empName;
			}
		},{
			text : i18n('i18n.authorization.user.loginName'),
			dataIndex : 'loginName',
			width:55
		},{
			text : i18n('i18n.authorization.user.status'),
			width:68,
			renderer : function(val) {
				if(val == null){
					return;
				}
			    if (val == 1) {
			        return i18n('i18n.authorization.user.valid');
			    } else {
			        return i18n('i18n.authorization.user.invalid');
			    }
			},
			dataIndex : 'status'
		},{
			text : i18n('i18n.authorization.user.lastLogin'),
			xtype: 'datecolumn',
			width:118,
			format : 'Y-m-d H:i:s',
			dataIndex : 'lastLogin'
		},{
			text : i18n('i18n.authorization.user.validDate'),
			xtype: 'datecolumn',
			width:110,
			format : 'Y-m-d H:i:s',
			dataIndex : 'validDate'
		},{
			text : i18n('i18n.authorization.user.invalidDate'),
			xtype: 'datecolumn',
			format : 'Y-m-d H:i:s',
			width:110,
			dataIndex : 'invalidDate'
		}],
		tbar : [{
				text : i18n('i18n.authorization.add'),
				hidden:!isPermission('/authorization/saveUser.action'),
				handler : function() {
					userFormUrl = 'saveUser.action';
					var user = new UserModel();
					UserDeptGridStore.removeAll();
					UserForm.loadRecord(user);
					UserForm.getForm().findField('empCode.empCode').setReadOnly(false);
					UserForm.getForm().reset();
					UserRoleGridStore.removeAll();
            		RoleChooesGridStore.load();
            		DeptChooesGridStore.load();
            		UserEditWindow.show();
					
				}
			}, {
				text : i18n('i18n.authorization.update'),
				hidden:!isPermission('/authorization/updateUser.action'),
				handler : function() {
					var selection = UserGrid.getSelectionModel().getSelection();
					if (selection.length != 1) {
//						Ext.MessageBox.alert(i18n('i18n.authorization.message'),i18n('i18n.authorization.operation_message'));
						MessageUtil.showMessage(i18n('i18n.authorization.operation_message'));
						return;
					} else {
						userFormUrl = 'updateUser.action';
						var record = selection[0];
						UserForm.getForm().findField('empCode.empCode').setReadOnly(true);
						UserForm.loadRecord(record);
	            		if(record.raw.empCode.empName != null){
	            			var empRecord = Ext.ModelManager.create(record.raw.empCode, 'EmployeeModel');
	            			UserForm.getForm().findField("empCode.empCode").select(empRecord);
	            		}
	            		var user= UserGrid.getSelectionModel().getSelection()[0];
	            		//用户未分配的角色 
	            		RoleChooesGridStore.load({
	            			params : {
	            				userId:user.get('id')	            				
	            			}
        				});
	            		//用户已经分配的角色
	            		UserRoleGridStore.load({
	            			params : {
	            				userId:user.get('id')	            				
	            			}
	            		});
	            		//用户未分配的部门
	            		DeptChooesGridStore.load({
	            			params : {
	            				userId:user.get('id')	            				
	            			}
	            		});
	            		//用户已经分配的部门
	            		UserDeptGridStore.load({
	            			params : {
	            				userId:user.get('id')	            				
	            			}
	            		});
						UserEditWindow.show();
					}
				}
			},{
					text : i18n('i18n.authorization.initalPassword'),
					hidden:!isPermission('/authorization/updatePassword.action'),
					handler : function() {
						userFormUrl = 'updatePassword.action';
						var selection = UserGrid.getSelectionModel().getSelection();
						if (selection.length != 1) {
//							Ext.MessageBox.alert(i18n('i18n.authorization.message'),i18n('i18n.authorization.operation_message'));
							MessageUtil.showMessage(i18n('i18n.authorization.operation_message'));
							return;
						}
						var record = selection[0];
						InitalPasswordForm.loadRecord(record);
	            		InitalPasswordForm.getForm().findField('password').setValue(null);
	            		InitalPasswordForm.getForm().findField('rePassword').setValue('');
	            		if(record.raw.empCode.empName != null){
	            			var empRecord = Ext.ModelManager.create(record.raw.empCode, 'EmployeeModel');
	            			InitalPasswordForm.getForm().findField("empCode.empCode").select(empRecord);
	            		}
							InitalPasswordWindow.show();
				}
			} ],
			bbar : Ext.create('Ext.toolbar.Paging', {
				id : 'UserGrid_pagingToolbar',
				store : UserStore,
				displayMsg : i18n('i18n.authorization.roleGrid.displayMsgNew'),
				displayInfo : true,
				items:[
		            '-',{
							text: i18n('i18n.authorization.roleGrid.page_count'),
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
						            	var pageSize = UserStore.pageSize;
						            	var newPageSize = parseInt(_field.value);
						            	if(pageSize!=newPageSize){
						            		UserStore.pageSize = newPageSize;
						            		Ext.getCmp('UserGrid_pagingToolbar').moveFirst();
						            	}
						            }
						        }
		                   }
		               }),{
							text: i18n('i18n.authorization.roleGrid.number'),
							xtype: 'tbtext'
		               }]
			})
	});