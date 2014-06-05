var EditEmployeeStore = Ext.create('Ext.data.Store', {
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
					'empCode.empCode':Ext.getCmp('EditDeptCode_ComBox').getValue()
					}
				}
			);	
		
		
			
		}
	}
});
/**
 * 角色表单
 */
var UserForm = Ext.create('Ext.form.FormPanel',{
	//autoScroll : true,
    height:70,
    id:'userForm',
    style : {
		'background-color' : 'rgb(220,231,244)'
	},
	border:false,
	items : [{
        xtype:'fieldset',
        //autoScroll:true,
        title: i18n('i18n.authorization.user.Form'),
        bodyStyle:'padding:5px 10px 0',
        height:60,
        fieldDefaults: {
            msgTarget: 'side',
            labelAlign:'left'
        },
        defaultType: 'textfield',
        defaults: {
        	margin:'5 10 5 10',
            anchor: '100%',
            labelWidth:60
        },
    	layout:'column',
    	//删除收缩按钮
    	//collapsible: true,
		items :[{
			name : 'id',
			hidden : true
		},{
			name: 'empCode.empCode',
			xtype: 'combo',
			columnWidth:0.3,
			id:'EditDeptCode_ComBox',
	        allowBlank : false,
	        blankText : i18n('i18n.authorization.user.EmpCodeNullException'),
	        fieldLabel: i18n('i18n.authorization.user.empCode'),
//			regex: /^[0-9]{6}$/,
			regexText:i18n('i18n.authorization.EmpCodeLongException'),
	        store: EditEmployeeStore,
	        //显示的字段
			displayField: 'empCode',
			//提交时的字段
			valueField: 'empCode',
			//查询依据的字段
			queryParam:'empCode.empCode',
			minChars:1,
	        typeAhead: false,
	        hideTrigger:false,
	        listConfig: {
	        	minWidth :280,
	            getInnerTpl: function() {
	            	 return '{empCode}&nbsp&nbsp&nbsp{empName}';
	            }
	        },
	        listeners : {
	        	select : function(field, value, options ){
	        		UserForm.getForm().findField("loginName").setValue(field.getValue());
	        	}
	        },
	        pageSize: 10
		},{
			name: 'loginName',
			allowBlank : false,
			columnWidth:0.3,
			readOnly:true,
			blankText : i18n('i18n.authorization.user.LoginNameNullException'),
	        fieldLabel: i18n('i18n.authorization.user.userName')
		},{
			name: 'status',
			allowBlank : false,
			editable:false,
			columnWidth:0.3,
			blankText : i18n('i18n.authorization.UserStatusNullException'),
			fieldLabel: i18n('i18n.authorization.user.status'),
			xtype:'combo',
		    store: Ext.create('Ext.data.Store', {
					    fields: ['name', 'value'],
					    data : [
					            {'name':i18n('i18n.authorization.user.valid'), 'value':1},//生效
					            {'name':i18n('i18n.authorization.user.invalid'), 'value':0}//失效
					    ]
					}),
		    queryMode: 'local',
		    displayField: 'name',
		    valueField: 'value'
		}
//		,{
//	        xtype: 'container',
//	        columnWidth:0.08,
//	        items : [Ext.create('Ext.Button', {
//				text : i18n('i18n.authorization.save'),
//				width:70,
//				handler : function() {
//					var form = UserForm.getForm();
//	        		var new_record = form.getRecord();
//	        		form.updateRecord(new_record);
//	                var empCodeValue = form.findField("empCode.empCode").getValue();
//            		var empCode = {'empCode':empCodeValue};
//	        		new_record.set('empCode',empCode);
//	        		//if(null!=new_record.get('password')&&new_record.get('password')=='123456'){
//	        		//	new_record.set('password',hex_md5('123456'));		        			
//	        		//}
//	        		//设置默认密码
//	        		new_record.set('password','123456');	
//	        		new_record.set('loginName',form.findField("empCode.empCode").getValue());
//	        		//将选中的角色构成一个数组
//	        		var _roles = new Array();
//	        		UserRoleGridStore.each( function(_role) {
//	        			_roles.push(_role.get('id'));
//	        		},UserRoleGridStore);
//	        		//将选中的部门构成一个数组
//	        		var _userDept = new Array();
//					UserDeptGridStore.each(function(_dept) {
//						_userDept.push(_dept.get('id'));
//					});
//					//传递的参数值
//					var array = {user:new_record.data,chooesRoles:_roles,chooseDepts:_userDept};
//					//如果表单通过验证就提交
//					if (form.isValid()&&checkInput()) { 
//						Ext.Ajax.request({
//							url : userFormUrl,
//							jsonData:array,
//							success : function(response) {
//								UserStore.load();
//								var json = Ext.decode(response.responseText);
//								if(json.isException){
//									Ext.MessageBox.alert(i18n('i18n.authorization.message'),json.message);
//								}else{
//									Ext.MessageBox.alert(i18n('i18n.authorization.message'),json.message,function(optional){
//										UserEditWindow.hide();
//									});
//								}
//							},
//							failure : function(response) {
//								var json = Ext.decode(response.responseText);
//								Ext.MessageBox.alert(i18n('i18n.authorization.message'),json.message);
//							}
//						});
//					}else{
//						Ext.MessageBox.alert(i18n('i18n.authorization.message'),i18n('i18n.authorization.InputErrException'));
//					}
//				}
//			})]
//		}
		]
	}]
});

function checkInput(){
	var empCode =  UserForm.getForm().findField("empCode.empCode").getValue();
	var loginName = UserForm.getForm().findField('loginName').value;
	if(loginName!=empCode){
//		Ext.MessageBox.alert(i18n('i18n.authorization.message'),i18n('i18n.authorization.LoginNameNotEqEmpCode'));
		if('sysadmin'===empCode){
			return true;
		}
		MessageUtil.showMessage(i18n('i18n.authorization.LoginNameNotEqEmpCode'));
		return false;
	}else{
		return true;
	}
}

