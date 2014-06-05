//角色表单URI
var roleFormUrl = 'saveRole.action';
/**
 * 角色表单
 */
var RoleForm = Ext.create('Ext.form.FormPanel',{
	//autoScroll:true,
	frame : true,
	items : [{
        xtype:'fieldset',
        layout: {
        	type: 'table',
        	columns: 4
        },
        title: i18n('i18n.authorization.role.roleform'),
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
			allowBlank : false,
			xtype : 'textfield',
			//验证输入的字符串不能大于64个字符
			maxLength:64,
			//大于64个字符的提示信息
			maxLengthText: i18n('i18n.authorization.RoleNameLongException'),
			blankText : i18n('i18n.authorization.RoleNameNullException'),
	        fieldLabel: i18n('i18n.authorization.role.roleName')
		},{
			name: 'roleDesc',
			colspan: 3,
			width:500,
//			height: 45,
			//验证输入的字符串不能大于256个字符
			maxLength:256,
			//大于256个字符的提示信息
			maxLengthText: i18n('i18n.authorization.RoleDescLongException'),
	        fieldLabel: i18n('i18n.authorization.role.roleDesc'),
//	        xtype : 'textarea'
	        xtype : 'textfield'
        }]
	}]
});