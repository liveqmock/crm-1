/**
 * 用户授权表单
 */
var AuthorizeForm = Ext.create('Ext.form.FormPanel',{
	frame : true,
	//autoScroll : true,
	height:90,
	id:'authorizeForm',
	items : [{
        xtype:'fieldset',
        title: i18n('i18n.authorization.user.userform'),
        frame:true,
        height:70,
        bodyStyle:'padding:5px 10px 0',
        defaultType: 'textfield',
        fieldDefaults: {
            msgTarget: 'side',
            labelAlign:'left'
        },
        defaults: {
        	margin:'5 10 5 10',
            anchor: '100%',
            labelWidth:60
        },
    	layout:'column',
    	//collapsible: true,
        items :[{
			name : 'id',
			hidden : true
		},{
		        xtype: 'combo',
		        readOnly : true,
		        cls:'readonly',
		        columnWidth:0.3,
		        fieldLabel: i18n('i18n.authorization.user.empCode'),
		        store: EmployeeStore,
				displayField: 'empCode',
				valueField: 'empCode',
				name: 'empCode.empCode',
				queryParam:'empCode.empCode',
				minChars:1,
		        typeAhead: false,
		        hideTrigger:false,
		        listConfig: {
		            getInnerTpl: function() {
		                 return '{empCode}';
		            }
		        },
		        pageSize: 10
			},{
				name: 'loginName',
				readOnly : true,
				columnWidth:0.3,
		        fieldLabel: i18n('i18n.authorization.user.loginName'),
		        xtype : 'textfield'
			},{
				name: 'status',
				fieldLabel: i18n('i18n.authorization.user.status'),
				xtype:'combo',
				columnWidth:0.3,
				readOnly : true,
				cls:'readonly',
			    store: Ext.create('Ext.data.Store', {
						    fields: ['name', 'value'],
						    data : [
						        {'name':i18n('i18n.authorization.user.valid'), 'value':1},
						        {'name':i18n('i18n.authorization.user.invalid'), 'value':0}
						    ]
						}),
			    queryMode: 'local',
			    displayField: 'name',
			    valueField: 'value'
			},{
		        xtype: 'container',
		        columnWidth:0.1,
		        items : [Ext.create('Ext.Button', {
					text : i18n('i18n.authorization.save'),
					width:60,
					handler : function(butn) {
			        	var form = AuthorizeForm.getForm();

			        	if (form.isValid()) {
							/*修改人：张斌
							修改时间：2013-7-3 14:10
							修改内容：增加对是否进行修改的判断，并将部门数据的是否修改传给后台使用*/
							var updateRole = false;
							if(AuthorizeRoleGridInitStore.getCount()!=AuthorizeRoleGridStore.getCount()){//个数不一样，肯定修改了
								updateRole = true;
							}else{//个数一样，当出事加载的一个角色，在要提交的store中没有，则表明已经修改了
								for(var i = 0;i<AuthorizeRoleGridInitStore.data.items.length;i++){
									var isHaveEquel = false;//是否存在，不存在作说明做了修改
									for(var j = 0;j<AuthorizeRoleGridStore.data.items.length;j++){
                                        if(AuthorizeRoleGridStore.data.items[j].get('id')!=AuthorizeRoleGridInitStore.data.items[i].get('id')){
											
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
							if(!updateRole&&!updateDept){
								MessageUtil.showMessage(i18n('i18n.authorization.noUpdateDelete'));
								return;
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
			        		var _roles = new Array();
			        		AuthorizeRoleGridStore.each( function(_role) {
			        			_roles.push(_role.get('id'));
			        		},AuthorizeRoleGridStore);
							var array = {userId:new_record.get('id'),chooesDepts:_depts,chooesRemoveDepts:_depts_remove,chooesRoles:_roles};
							butn.setDisabled(true);
				        	Ext.Ajax.request({
								  url : 'saveAuthorize.action',
								  jsonData:array,
								  /**
								   * 修改人：张斌
								   * 修改时间：2013-7-2 14:00
								   * 修改内容：success方法中需要判断返回的success参数才能判断是否执行成功
								   */
								  success : function(response) {
									    butn.setDisabled(false);
										var json = Ext.decode(response.responseText);
										if(Ext.isEmpty(json)){//如果message时空，则是请求超时
											MessageUtil.showMessage(i18n('i18n.authorization.timeOut'));
											return;
										}
										if(json.success){
											MessageUtil.showInfoMes(json.message);
											AuthorizeEditWindow.hide();
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
				        	}
					}        
				})]
			
        }]
	}]
});