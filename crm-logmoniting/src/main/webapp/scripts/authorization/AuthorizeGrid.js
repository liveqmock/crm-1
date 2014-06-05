var AuthorizeStore = Ext.create('Ext.data.Store', {
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
 * 授权用户列表
 */
var AuthorizeGrid = Ext.create('SearchGridPanel',{
	title : i18n('i18n.authorization.user.userGrid'),
    sortableColumns:false,
    enableColumnHide:false,
    enableColumnMove:false,
    height:'100%',
//    height:680,
    id:'authorizeGrid',
    autoScroll : true,
	defaults: {   
		 flex : 1
},  
	stripeRows : true, // 交替行效果
	// viewConfig : {
	// autoFill : true,
	// forceFit : true
	// },
	selType : "rowmodel", // 选择类型设置为：行选择
	store : AuthorizeStore,
	columns : [{
			id : 'id',
			hidden : true,
			dataIndex : 'id'
		},{
			text : i18n('i18n.authorization.user.empCode'),
			width:65,
			renderer : function(value){
				if(value==null){
					return '';
				}
				return value.empCode;
			},
			dataIndex : 'empCode'
		},{
			text : i18n('i18n.authorization.employee.name'),
			width:80,
			dataIndex : 'empCode',
			renderer : function(value){
				if(value==null){
					return '';
				}
				return value.empName;
			}
		},{
			text : i18n('i18n.authorization.user.loginName'),
			width:55,
			dataIndex : 'loginName'
		},{
			text : i18n('i18n.authorization.user.status'),
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
			width:68,
			dataIndex : 'status'
		},{
			text : i18n('i18n.authorization.user.lastLogin'),
			xtype: 'datecolumn',
			format : 'Y-m-d H:i:s',
			width:118,
			dataIndex : 'lastLogin'
		},{
			text : i18n('i18n.authorization.user.validDate'),
			xtype: 'datecolumn',
			format : 'Y-m-d H:i:s',
			width:110,
			dataIndex : 'validDate'
		},{
			text : i18n('i18n.authorization.user.invalidDate'),
			xtype: 'datecolumn',
			format : 'Y-m-d H:i:s',
			width:110,
			dataIndex : 'invalidDate'
		}],
		tbar : [{
			text : i18n('i18n.authorization.autorize'),
			hidden:!isPermission('/authorization/saveAuthorize.action'),
			handler : function() {
				var selection = AuthorizeGrid.getSelectionModel().getSelection();
				if (selection.length != 1) {
//					Ext.MessageBox.alert(i18n('i18n.authorization.message'),i18n('i18n.authorization.operation_message'));
					MessageUtil.showMessage(i18n('i18n.authorization.operation_message'));
					return;
				} else {
					var record = selection[0];
					AuthorizeForm.loadRecord(record);
					if(record.get('empCode') != null){
						var empRecord = Ext.ModelManager.create(record.get('empCode'), 'EmployeeModel');
						AuthorizeForm.getForm().findField("empCode.empCode").select(empRecord);
					}
					RoleChooesGridStore.load();
					AuthorizeRoleGridStore.load();
					var rootNode = DepartmentChooesTree.getRootNode();
					//刷新
					onRefreshTreeNodes(DepartmentTreeStore,rootNode.get("id"));
					AuthorizeEditWindow.show();
				}
			}
		}],
		bbar : Ext.create('Ext.toolbar.Paging', {
			id : 'UserGrid_pagingToolbar',
			store : AuthorizeStore,
			displayMsg : i18n('i18n.authorization.roleGrid.displayMsgNew'),
			displayInfo : true,
			items:['-',{
					text: i18n('i18n.authorization.roleGrid.page_count'),
					xtype: 'tbtext'
				},Ext.create('Ext.form.ComboBox', {
                   width:          window.screen.availWidth*0.039,
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
				            	var pageSize = AuthorizeStore.pageSize;
				            	var newPageSize = parseInt(_field.value);
				            	if(pageSize!=newPageSize){
				            		AuthorizeStore.pageSize = newPageSize;
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