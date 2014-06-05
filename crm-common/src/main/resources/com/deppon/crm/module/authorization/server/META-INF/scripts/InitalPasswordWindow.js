var InitalPasswordForm = Ext.create('Ext.form.FormPanel',{
	frame : true,
	//autoScroll : true,
	defaults:{
		labelWidth:60,
		//width:100可以用这个来设置整体的宽度
		fieldStyle:'width:150px'
	},
	items : [{
			name : 'id',
			hidden : true
		},{
			name: 'empCode.empCode',
			xtype: 'combo',
			readOnly:true,
	        allowBlank : false,
	        blankText : i18n('i18n.authorization.user.EmpNameNullException'),
	        fieldLabel: i18n('i18n.authorization.employee.name'),
	        store: EmployeeStore,
	        //显示的字段
			displayField: 'empName',
			//提交时的字段
			valueField: 'empCode',
			//查询依据的字段
			queryParam:'empCode.empName',
			minChars:1,
	        typeAhead: false,
	        hideTrigger:false,
	        listConfig: {
	            getInnerTpl: function() {
	                 return '{empName}';
	            }
	        }
		}, {
			xtype:'textfield',
			name: 'password',
			inputType: "password",
	        fieldLabel: i18n('i18n.authorization.user.password')
		}, {
			xtype:'textfield',
			name: 'rePassword',
			inputType: "password",
	        fieldLabel: i18n('i18n.authorization.user.rePassword')
		}
	],
	buttonAlign : 'center',
	buttons : [{
		text : i18n('i18n.authorization.save'),
		width :70,
		handler : function() {
			var form = InitalPasswordForm.getForm();
    		var record = form.getRecord();
    		form.updateRecord(record);
    		var _user={
    			'id':record.get('id'),
    			'password':record.get('password')//hex_md5(record.get('password'))
    		};
			//传递的参数值
			var array = {user:_user};
			//如果表单通过验证就提交
			if (form.isValid()&&checkValidate()) { 
				Ext.Ajax.request({
					url : userFormUrl,
					jsonData:array,
					success : function(response) {
						UserStore.load();
						var json = Ext.decode(response.responseText);
						MessageUtil.showInfoMes(json.message);
						InitalPasswordWindow.hide();
					},
					failure : function(response) {
						var json = Ext.decode(response.responseText);
						MessageUtil.showMessage(json.message);
					}
				});
			}
		}
	}]
});

var InitalPasswordWindow = Ext.create('Ext.window.Window', {
	width: 237,
    height: 154,
	closable : true,
	modal : true,
	resizable:false,
	closeAction : 'hide',
	title : i18n('i18n.authorization.user.InitalPasswordWindow'),
	items : [ InitalPasswordForm]
});


function checkValidate(){
	 var empCode = InitalPasswordForm.getForm().findField("empCode.empCode").getValue();
	 var password = InitalPasswordForm.getForm().findField("password").getValue();
	 var repassword = InitalPasswordForm.getForm().findField("rePassword").getValue();
	 if(password==null||password==''){
		 MessageUtil.showMessage(i18n('i18n.authorization.InputErrException'));
		 return;
	 }
	 if(repassword==null||repassword==''){
		 MessageUtil.showMessage(i18n('i18n.authorization.InputErrException'));
		 return;
	 }
	 if (password!=repassword) {
		 MessageUtil.showMessage(i18n('i18n.authorization.PasswordNotMatchException'));
		 return false;
	}
	 if(empCode!=null&&password!=null&&password!=''){
		 return true;
	 }else{
		 MessageUtil.showMessage(i18n('i18n.authorization.InputErrException'));
		 return false;
	 }
}