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
					handler : function() {
			        	var form = AuthorizeForm.getForm();
			        	if (form.isValid()) { 
			        		var new_record = form.getRecord();
			        		var _roles = new Array();
			        		AuthorizeRoleGridStore.each( function(_role) {
			        			_roles.push(_role.get('id'));
			        		},AuthorizeRoleGridStore);
			        		var _depts = new Array();
			        		var nodes = DepartmentChooesTree.getChecked();
							form.updateRecord(new_record);
							Ext.each(nodes, function(node) {
								var status = null;
								if(!node.isLeaf()&&(node.childNodes==null||node.childNodes.length<=0)){
									status=true;
								}
								var dept = {
										'id' : node.raw.entity.id,
										'status' : status
											
								};
								_depts.push(dept);
							});
							var array = {userId:new_record.get('id'),chooesDepts:_depts,chooesRoles:_roles};
				        	Ext.Ajax.request({
								  url : 'saveAuthorize.action',
								  jsonData:array,
								  success : function(response) {
										var json = Ext.decode(response.responseText);
//										Ext.MessageBox.alert(i18n('i18n.authorization.message'),json.message,function(){
//											AuthorizeEditWindow.hide();
//										});
										MessageUtil.showInfoMes(json.message,function(){
											AuthorizeEditWindow.hide();
										});
								   },
								   failure : function(response) {
										var json = Ext.decode(response.responseText);
//										Ext.MessageBox.alert(i18n('i18n.authorization.message'),json.message);
										MessageUtil.showMessage(json.message);
								   }
							   });
				        	}
					}        
				})]
			
        }]
	}]
});