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
//	        		UserForm.getForm().findField("loginName").setValue(field.getValue());
                    UserForm.getForm().findField("empCode.position").setValue(value[0].get('position'));
	        	}
	        },
	        pageSize: 10
		},{
//            name : 'loginName',
			name: 'empCode.position',
			allowBlank : false,
//            displayField: 'empCode',
			columnWidth:0.3,
			readOnly:true,
//			blankText : i18n('i18n.authorization.user.LoginNameNullException'),
//	        fieldLabel: i18n('i18n.authorization.user.userName')
            fieldLabel: i18n('i18n.authorization.user.positionName')
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

		    valueField: 'value',
		    listeners : {
	        	select : function(field, value, options ){
	        		
	        	}
	        },
		}
		,{
	        xtype: 'container',
	        columnWidth:0.08,
	        items : [Ext.create('Ext.Button', {
				text : i18n('i18n.authorization.save'),
				width:70,
				handler : function(butn) {
					var DepartmentChooesTree = Ext.getCmp('departmentChooesTreePanel').getDepartmentChooesTree();//部门树
					var form = UserForm.getForm();//用户基本信息表单
					if(userFormUrl=='updateUser.action'){
						/*修改人：张斌
						修改时间：2013-7-3 14:10
						修改内容：增加对是否进行修改的判断，并将部门数据的是否修改传给后台使用*/
						var selection = UserGrid.getSelectionModel().getSelection()[0];
						var updateStatus = selection.get('status')==form.findField('status').getValue()?false:true;
						var updateRole = false;
						if(UserRoleGridInitStore.getCount()!=UserRoleGridStore.getCount()){//个数不一样，肯定修改了
							updateRole = true;
						}else{//个数一样，当出事加载的一个角色，在要提交的store中没有，则表明已经修改了
							for(var i = 0;i<UserRoleGridInitStore.data.items.length;i++){
								var isHaveEquel = false;//是否存在，不存在作说明做了修改
								for(var j = 0;j<UserRoleGridStore.data.items.length;j++){
                                    if(UserRoleGridStore.data.items[j].get('id')!=UserRoleGridInitStore.data.items[i].get('id')){
										
									}else{
										isHaveEquel = true;
										continue;
									}
								}
								if(!isHaveEquel){//初始化的再提交的里面不存在
									updateRole = true;
									break;
								}
							}
						}
						var updateDept = DepartmentChooesTree.getStore().getUpdatedRecords().length>0?true:false;//判断部门树是否修改
						if(!updateRole&&!updateDept&&!updateStatus){
							MessageUtil.showMessage(i18n('i18n.authorization.noUpdateDelete'));
							return;
						}
					}
					var _depts = new Array();
					var _depts_remove = new Array();
					var updateDept = DepartmentChooesTree.getStore().getUpdatedRecords();
					for(var i=0;i<updateDept.length;i++){
						var status = null;
						if(!updateDept[i].isLeaf()&&(updateDept[i].childNodes==null||updateDept[i].childNodes.length<=0)){
							status=true;
						}
						var dept = {
								'id' : updateDept[i].raw.entity.id,
								'status' : status
									
						};
						if(updateDept[i].get('checked')){
							_depts.push(dept);
						}else{
							_depts_remove.push(dept);
						}
					}
	        		var new_record = form.getRecord();
	        		form.updateRecord(new_record);
	                var empCodeValue = form.findField("empCode.empCode").getValue();
            		var empCode = {'empCode':empCodeValue};
	        		new_record.set('empCode',empCode);
	        		//设置默认密码
	        		new_record.set('password','123456');	
	        		new_record.set('loginName',form.findField("empCode.empCode").getValue());
	        		//将选中的角色构成一个数组
	        		var _roles = new Array();
	        		UserRoleGridStore.each( function(_role) {
	        			_roles.push(_role.get('id'));
	        		},UserRoleGridStore);
					//传递的参数值
					var array = {user:new_record.data,chooesRoles:_roles,chooseDepts:_depts,chooesRemoveDepts:_depts_remove};
					//如果表单通过验证就提交
//					if (form.isValid()&&checkInput()) { 
                    if (form.isValid()) { 
						butn.setDisabled(true);
						Ext.Ajax.request({
							url : userFormUrl,
							jsonData:array,
							success : function(response) {
								butn.setDisabled(false);
								var json = Ext.decode(response.responseText);
								if(Ext.isEmpty(json)){//如果message时空，则是请求超时
									MessageUtil.showMessage(i18n('i18n.authorization.timeOut'));
									return;
								}
								UserStore.load();
								if(json.success){
									MessageUtil.showInfoMes(json.message);
									UserEditWindow.hide();
								}else{
									MessageUtil.showMessage(json.message);
								}
							},
							failure : function(response) {
								butn.setDisabled(false);
								var json = Ext.decode(response.responseText);
								if(Ext.isEmpty(json)){//如果message时空，则是请求超时
					        		MessageUtil.showMessage(i18n('i18n.authorization.timeOut'));
								}else{
									MessageUtil.showMessage(json.message);
								}
								
							}
						});
					}else{
						MessageUtil.showMessage(i18n('i18n.authorization.InputErrException'));
					}
				}
			})]
		}
		]
	}]
});

function checkInput(){
	var empCode =  UserForm.getForm().findField("empCode.empCode").getValue();
	var loginName = UserForm.getForm().findField('loginName').value;
	if(loginName!=empCode){
		if('sysadmin'===empCode){
			return true;
		}
		MessageUtil.showMessage(i18n('i18n.authorization.LoginNameNotEqEmpCode'));
		return false;
	}else{
		return true;
	}
}

