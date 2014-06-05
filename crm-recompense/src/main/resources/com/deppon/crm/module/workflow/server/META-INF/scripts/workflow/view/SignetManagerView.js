/**
 * 印章管理员设置
 */
Ext.onReady(function() {
	/**
	 *  顶部的查询表单
	 */
	Ext.define('SignetManagerSearchForm',{			
		extend:'SearchFormPanel',
		height : 40,
		items:[{
				layout:{
					type:'table',
					columns:3
				},
				defaultType:'textfield',
				defaults:{
					margin : '0 0 10 10',
					labelWidth:30,
					width:250
				},items:[
                    Ext.create('Ext.lookup.EmployeeLookup',{
                    	id:'UserSearch',
                    	name:'UserSearch',
                    	fieldLabel: i18n('i18n.workflow.employee'),
                    	emptyText:'',
                    	width:200,
                     	labelWidth:60,
                    	editable:false,
                    	listeners:{
    						change:function(th){
    	  	            		Ext.getCmp('areaSearch').setDisabled(true);
    	  	            		if(Ext.isEmpty(th.getValue())){
    	  	            			Ext.getCmp('areaSearch').setDisabled(false);
    	  	            		}
    	  	            	},
    				        select:function(){
    				        	Ext.getCmp('areaSearch').setDisabled(false);
    				        }
    					}
                    })    
			,{
				xtype:'btnpanel',
				width:70,
				items:[{
					xtype:'button',
					id:'areaSearch',
					text:i18n('i18n.workflow.search') 
					,handler:function(){
						var empId = Ext.getCmp('UserSearch').getValueId();
						if(!Ext.isEmpty(empId)){
							empId = empId.trim()
						}
						 var param  = {'empId':empId};
						 Ext.getCmp('SignetManagerSearchGrid').getStore().load({params:param});
					}
					}]
			}]}]
	});
	
	/**
	 * 中间按钮
	 */
	Ext.define('OpButtonPanel',{
		extend:'NormalButtonPanel', 
		items:[{
				xtype:'leftbuttonpanel', 
				items:[{ xtype:'button',
					  id :'add',
					  text:i18n('i18n.workflow.add'),
					  handler:function(){
						 if(Ext.isEmpty(Ext.getCmp('DeptPopupWindow'))){
								 Ext.create('DeptPopupWindow').show();
							}else{
								Ext.getCmp('DeptPopupWindow').show();
							}
							Ext.getCmp('DeptPopupForm').getForm().reset();
					 }},{
					  xtype:'button',
					  id :'delete',
					  text:i18n('i18n.workflow.delete'),	
					  handler:function(){
							var selections = Ext.getCmp('SignetManagerSearchGrid').getSelectionModel().getSelection(),
							successFn = function(json){
								MessageUtil.showInfoMes(i18n('i18n.workflow.deleteSuccess'));
								Ext.getCmp('SignetManagerSearchGrid').getStore().load();
							},
							failureFn = function(json){
									MessageUtil.showErrorMes(i18n('i18n.workflow.serviceMoreTime'));
							};
							if(selections.length>=1){
								var chsedId = selections[0].get('id');
								var params = {'id':chsedId};
								MessageUtil.showQuestionMes(i18n('i18n.workflow.sureDelete'),function(e){
									if(e == 'yes'){
										SignetManagerData.deleteSignetManager( params, successFn, failureFn );
									}
								});
							}else{
								MessageUtil.showMessage(i18n('i18n.workflow.pleaseSelectOneLine'))
							};
					  }
			         }]
				},{
				xtype:'middlebuttonpanel' 
		}]
	});
	
	var store = Ext.create('SignetManagerStore',{autoLoad:true});
	
	/**
	 * 底部Grid
	 */
	Ext.define('SignetManagerSearchGrid',{		
		extend:'SearchGridPanel',
		id:'SignetManagerSearchGrid',
		flex:1,
		store:store,
		columns:[{
			xtype : 'rownumberer',
			width : 40,
			text : i18n('i18n.workflow.num')
			},{
				header :i18n('i18n.workflow.empNo'),	
				dataIndex: 'empCode',
				flex:1
			},{
				header : i18n('i18n.workflow.userName'),
				dataIndex: 'empName',	
				flex:1
			}, {
				header :i18n('i18n.workflow.deptName'), 
				dataIndex: 'deptName',
				flex:2
		}],
		stateful: true,
		selType : 'checkboxmodel',
		viewConfig:{
				forceFit:true
		},
		dockedItems:[{
				xtype:'pagingtoolbar',
				store:store,
				dock:'bottom',
				displayInfo : true,
				autoScroll : true
		}]
	});
	
	/**
	 * 弹出的新增窗口里的表单
	 */
	Ext.define('DeptPopupForm',{	
		extend:'NoTitleFormPanel',		
		id:'DeptPopupForm',
		items:[{
				layout:{
					type:'table',
					columns:2
				},
				defaults:{
					labelWidth:60,
					width:200
				},
				items:[{
	            	colspan: 1,
	  				id:'deptId',
	  				name: 'deptId',
	  		        fieldLabel:i18n('i18n.workflow.deptName'), 
	  		        xtype:'combo',
	  	            store: DpUtil.getStore('deptId',null,['id','deptName'],[]),
	  	            queryMode: 'local',
	  	            forceSelection: true,
	  	            editable:false,
	  	            allowBlank:false,
	  	            valueField:'id',
	  	            displayField: 'deptName'
                 }, 
                 Ext.create('Ext.lookup.EmployeeLookup',{
                  	id:'popupFormDeptNameId',
                 	name:'userId',
                 	fieldLabel: i18n('i18n.workflow.employee'),
                 	emptyText:'',
                 	width:200,
                 	labelWidth:60,
                 	editable:false,
                 	allowBlank:false,
                 	listeners:{
 						change:function(th){
 	  	            		Ext.getCmp('areaSearch').setDisabled(true);
 	  	            		if(Ext.isEmpty(th.getValue())){
 	  	            			Ext.getCmp('areaSearch').setDisabled(false);
 	  	            		}
 	  	            	},
 				        select:function(){
 				        	Ext.getCmp('areaSearch').setDisabled(false);
 				        }
 					}
                 })]		
			}],
		listeners:{
			'beforerender':function(){
				var successFn = function(json){
		        	Ext.data.StoreManager.lookup('deptId').add(json.deptList);
			    };
		        var filureFn = function(json){
		        	MessageUtil.showErrorMes(json.message);
		        };
		        //获取大区数据
		        SignetManagerData.queryBizAndHubCenterDept(null,successFn,filureFn);
			}
		}
	});
	
	/**
	 * 弹出的新增窗口
	 */
	Ext.define('DeptPopupWindow',{				
		extend:'PopWindow',
		title:i18n('i18n.workflow.addTitle'),
		id:'DeptPopupWindow',
		fbar:[{
				xtype:'button',
				text:i18n('i18n.workflow.determine'),	// 确定
				handler:function(){
					if(!Ext.getCmp('DeptPopupForm').getForm().isValid()){
						return;
					};
					var deptId = Ext.getCmp('deptId').getValue(),
						empId = Ext.getCmp('popupFormDeptNameId').getValueId(),
						params = {
								'signetManager':{
									'empId':empId,
								 	'deptId':deptId
								 	} 
							 	},
						fnSuccess = function(json){
							if(json.opCode===0){
								 MessageUtil.showInfoMes(i18n('i18n.workflow.addSuccess'));	
							}else if(json.opCode===1){
								 MessageUtil.showErrorMes(i18n('i18n.workflow.addEmpExist'));	
							}else if(json.opCode===2){
								 MessageUtil.showErrorMes(i18n('i18n.workflow.addDepExist'));	
							}
						   
							Ext.getCmp('SignetManagerSearchGrid').getStore().load();
						},
						fnFailure = function(json){
							MessageUtil.showErrorMes(i18n('i18n.workflow.addFailure'));	
						};
					SignetManagerData.addSignetManager(params,fnSuccess,fnFailure);
					Ext.getCmp('DeptPopupWindow').hide();
				}
			},{
				xtype:'button',
				text:i18n('i18n.workflow.cancel'),	// 取消
				handler:function(){
					Ext.getCmp('DeptPopupWindow').hide();
				}
		}],
		items:[Ext.create('DeptPopupForm')],
		closeAction:'hide',
		layout: 'fit'
	});

	/**
	 * viewport
	 */
	Ext.create('Ext.container.Viewport', {
				layout : {
					type : 'vbox',
					align : 'stretch'
				},
				items : [
					Ext.create('SignetManagerSearchForm'),
					Ext.create('OpButtonPanel'),
					Ext.create('SignetManagerSearchGrid')
				]
			});
});
