/*
 * 大区设置
 * @author Rock
 */
var RegionalSettingsDt = (CONFIG.get("TEST"))? new RegionalSettingsDataTest() : new RegionalSettingsData();
Ext.onReady(function() {
	Ext.form.Field.prototype.msgTarget='side';
	Ext.define('RegionalSettingsSearchForm',{			// 顶部的查询表单
		extend:'SearchFormPanel',
		height : 40,
		items:null,
		fbar:null,
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [{
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
                    	id:'RegionalSetSearch',
                    	name:'RegionalSetSearch',
                    	fieldLabel: i18n('i18n.recompense.employee'),
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
				         /*{
					xtype:'employeelookup',
					id:'RegionalSetSearch',
					fieldLabel: i18n('i18n.recompense.employee'),
					name:'RegionalSetSearch',
					emptyText:'',
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
					}*/
	            	/*colspan: 1,
	  				id:'RegionalSetSearch',
	  				name:'RegionalSetSearch',
	  		        fieldLabel: i18n('i18n.recompense.employee'), //i18n('i18n.recompense.searchByUserName'),	// i18n('i18n.RegionalSettings.pleaseInputUserNameToSearch')
	  		        xtype:'combo',
	  	            store: DpUtil.getStore('RegionalSetSearch',null,['id','empCode','empName'],[]),
	  	            queryMode: 'local',
	  	            valueField:'id',
	  	            displayField: 'empCode',
		  	         listConfig: {
	  		        	minWidth :200,
	  		            getInnerTpl: function() {
	  		            	 return '{empCode}&nbsp&nbsp&nbsp{empName}';
	  		            }
	  		        },
	  	            listeners:{
		            	specialkey:function(th,e){
		            	var successFn = function(json){
		         		    Ext.data.StoreManager.lookup('RegionalSetSearch').removeAll();
		         		   var user = {'id':json.users[0].id,'empCode':json.users[0].empCode.empCode,'empName':json.users[0].empCode.empName};
		     			    Ext.data.StoreManager.lookup('RegionalSetSearch').add(user);
		     			    Ext.getCmp('RegionalSetSearch').expand();
		     		    };
		     		    var param  = {'empCode':e.target.value};
		     		    DpUtil.keyPress(e,RegionalSettingsDt.searchEmployeeByCode,param,6,successFn);
		            	},
		            	change:function(th){
	  	            		Ext.getCmp('areaSearch').setDisabled(true);
	  	            		if(Ext.isEmpty(th.getValue())){
	  	            			Ext.getCmp('areaSearch').setDisabled(false);
	  	            		}
	  	            	},
				        select:function(){
				        	Ext.getCmp('areaSearch').setDisabled(false);
				        },
				        collapse:function(th){
				        	th.store.removeAll();
				        }
			        }*/
                 //}
			,{
				xtype:'btnpanel',
				width:70,
				items:[{
					xtype:'button',
					id:'areaSearch',
					text:i18n('i18n.recompense.search') // ||i18n('i18n.recompense.search')
					,handler:function(){
						me.fnSendRequest();
					}
					}]
			}]}
					       ];
		}
		,fnSendRequest:function(){
 		    Ext.getCmp('RegionalSettingsSearchGrid').getStore().load();
		}
	});
	Ext.define('RegionalSettingsSearchGrid',{		// 中部表格
		extend:'SearchGridPanel',
		id:'RegionalSettingsSearchGrid',
//		height: 432,								// height: 432,
		flex:1,
		store:null,
		columns:null,
		stateful: true,
		selType : 'checkboxmodel',					// 选择模式
		initComponent:function(){
			this.columns = [ {
				xtype : 'rownumberer',
				width : 40,
				text : i18n('i18n.recompense.num')
				},{
					header : i18n('i18n.recompense.recompenserMemberEmpcode'),	// ||i18n('i18n.recompense.custName'),
					dataIndex: CONFIGNAME.get('RegionalSett_user'),	// user
					flex:1,
					renderer : function(user) {
						if (!Ext.isEmpty(user)) {
							if(Ext.isEmpty(user.empCode)){
								return user.loginName;
							}else{
								return user.empCode.empCode;
							}
						}else{
							return null;
						}
					}
				},{
					header : i18n('i18n.recompense.userName'),	// ||i18n('i18n.recompense.custName'),
					dataIndex: CONFIGNAME.get('RegionalSett_user'),	// user
					flex:1,
					renderer : function(user) {
						if (!Ext.isEmpty(user)) {
							if(Ext.isEmpty(user.empCode)){
								return null;
							}else{
								return user.empCode.empName;
							}
							
						}else{
							return null;
						}
					}
				}, {
					header :i18n('i18n.recompense.deptName'), // i18n('i18n.RegionalSettings.belongsToLargeArea'),
					dataIndex: CONFIGNAME.get('RegionalSett_dept'),	// dept
					renderer : function(dapt) {
						if (dapt && typeof(dapt)!="undefined" ) {
							return dapt.deptName
						}
					},
					flex:2
				} ];
			this.store = new RegionalSettingsDt.getStore();
			this.store.on('beforeload',function(store,operation,obj){
				var userId = Ext.getCmp('RegionalSetSearch').getUserId();
				if(!Ext.isEmpty(userId)){
					userId = userId.trim()
				}
				var param  = {'userId':userId};
				Ext.apply(operation,{
					params : param
				});
			});
			this.callParent();
		}
	});
	Ext.define('deptPopupForm',{	// 无标题表单容器	弹出window里面的表单
		extend:'NoTitleFormPanel',		
		id:'deptPopupForm',
		items:null,
		initComponent:function(){
			this.items = this.getItems();
			var successFn = function(json){
	        	Ext.data.StoreManager.lookup(CONFIGNAME.get('RegionalSett_user')).add(json.deptList);
		    };
	        var filureFn = function(json){
	        	MessageUtil.showErrorMes(json.message);
	        };
	        //获取大区数据
	        RegionalSettingsDt.searchAreas(null,successFn,filureFn);
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [{
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
	  				id:CONFIGNAME.get('RegionalSett_user'),
	  				name: CONFIGNAME.get('RegionalSett_user'),
	  		        fieldLabel:i18n('i18n.recompense.belongArea'), // 所属大区
	  		        xtype:'combo',
	  	            store: DpUtil.getStore(CONFIGNAME.get('RegionalSett_user'),null,['id','deptName'],[]),
	  	            queryMode: 'local',
	  	            forceSelection: true,
	  	            allowBlank:false,
	  	            valueField:'id',
	  	            displayField: 'deptName'
                 }, 
                 Ext.create('Ext.lookup.EmployeeLookup',{
                  	id:'popupFormDeptNameId',
                 	name:CONFIGNAME.get('userId'),
                 	fieldLabel: i18n('i18n.recompense.employee'),
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
                 }) 
                 /*{
         			fieldLabel:i18n('i18n.recompense.employee'),		// 职员
        			xtype: 'combo',
    				maxLength:100,
    				queryMode:'local',
    				id:'popupFormDeptNameId',
    				name:CONFIGNAME.get('userId'),
    				displayField:'empName',
    				valueField:'id',
    				store:DpUtil.getStore('popupFormDeptNameId',null,['id','empCode','empName'],[]),
    				allowBlank:false,
    				forceSelection: true,
    				listConfig: {
    		        	minWidth :200,
    		            getInnerTpl: function() {
    		            	 return '{empCode}&nbsp&nbsp&nbsp{empName}';
    		            }
    		        },
    				listeners:{
    	            	specialkey:function(th,e){	// 按照职员名称查询大区
    		            	var successFn = function(json){
    		         		    Ext.data.StoreManager.lookup('popupFormDeptNameId').removeAll();
    		         		    var user = {'id':json.users[0].id,'empCode':json.users[0].empCode.empCode,'empName':json.users[0].empCode.empName};
    		     			    Ext.data.StoreManager.lookup('popupFormDeptNameId').add(user);
    		     			    Ext.getCmp('popupFormDeptNameId').expand();
    		     		    };
    		     		    var param  = {'empCode':e.target.value};
    	            		DpUtil.keyPress(e,RegionalSettingsDt.searchEmployeeByCode,param,6,successFn);
    	            	}
    				}
        		}*/]		
			}];
		}
	});
	
	Ext.define('deptPopupWindow',{					// 大区设置	userId	roleId	deptId
		extend:'PopWindow',
		title:i18n('i18n.recompense.createDept'),
		id:'deptPopupWindow',
		fbar:null,
		items:null,
		closeAction:'hide',
		layout: 'fit',
		flag : null,
		initComponent: function(){
			this.fbar = this.getFbar();
			this.items = this.getItems();
			this.callParent();
		},
		getItems: function(){
			return [new deptPopupForm()];
		},
		addDeptInfo: function(){
			var me = this;
			if(!Ext.getCmp('deptPopupForm').getForm().isValid()){
				return;
			};
			// String userId, String roleId, String deptId
			var dept = Ext.getCmp(CONFIGNAME.get('RegionalSett_user')).getValue(),
				user = Ext.getCmp('popupFormDeptNameId').getUserId(),
				params = { 	'userId':user,
							'roleId':null,
						 	'deptId':dept},
				fnSuccess = function(json){
				    //MessageUtil.showInfoMes(i18n('i18n.recompense.addSuccess'));
				    MessageUtil.showInfoMes(i18n('i18n.recompense.addSuccess'));	//i18n('i18n.RegionalSettings.addSuccessWar'))
					Ext.getCmp('RegionalSettingsSearchGrid').getStore().load();
				},
				fnFailure = function(json){
					MessageUtil.showErrorMes(i18n('i18n.recompense.addFailure'));	//i18n('i18n.RegionalSettings.addFailureWar'))
				};
			RegionalSettingsDt.insertUserRoleDepartment(params,fnSuccess,fnFailure);
			me.hide();
		},
		getFbar:function(){
			var me = this;
			return [{
				xtype:'button',
				text:i18n('i18n.recompense.determine'),	// 确定
				handler:function(){
					if(me.flag=='add'){
						me.addDeptInfo();
					}
				}
			},{
				xtype:'button',
				text:i18n('i18n.recompense.cancel'),	// 取消
				handler:function(){
					me.hide();
				}
			}];
		}
//		,saveInfo:function(){
//			var me= this;
//			if(Ext.getCmp('deptPopupWindow').getForm().isValid()){
//				var succseeFn = function(json){
//					DpUtil.showMessage(json.message);
//					me.hide();
//					Ext.getCmp(id:'popupFormDeptNameId').getStore().removeAll(true);
//					Ext.getCmp('messageGird').getStore().removeAll(true);
//					Ext.getCmp('messageGird').getStore().add(json.messageList);
//					Ext.getCmp('messageSendGird').getStore().add(json.messageList);
//					Ext.getCmp('RegionalSettingsSearchGrid').getStore().load();
//				}
//	            var failureFn = function(json){
//					DpUtil.showMessage(json.message);
//				}
//	            var params = {'msg':messageModel.data};
//	            RegionalSettingsDt.insertUserRoleDepartment(params,succseeFn,failureFn);
//			}
//		}
	});


		
	Ext.define('RegionalSettings',{						// 布局容器
		extend:'Ext.container.Viewport',
		title : i18n('i18n.recompense.createDept'),		//i18n('i18n.recompense.createDept'),
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		items : [ new RegionalSettingsSearchForm(),
		          new OpButtonPanel(),
		          new RegionalSettingsSearchGrid()
		          ]
	});
	new RegionalSettings();
});
/**.
 * <p>
 * 操作按钮BUTTON PANEL</br>
 * </p>
 * @author  张斌
 * @时间    2012-05-03
 */
Ext.define('OpButtonPanel',{
	extend:'NormalButtonPanel', 
	items:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'leftbuttonpanel', 
			items:[{ xtype:'button',
				  id :'add',
				  text:i18n('i18n.recompense.add'),		//i18n('i18n.recompense.add'),
				  handler:function(){
					  me.fnAdd();
				 }},{
				  xtype:'button',
				  id :'delete',
				  text:i18n('i18n.recompense.delete'),	// i18n('i18n.recompense.delete'),
				  handler:function(){
					  me.fnDelete();
				  }
		         }]
			},{
			xtype:'middlebuttonpanel' 
			}]
	},
	fnAdd:function(){					// 增加一条大区设置	???
		if(Ext.isEmpty(Ext.getCmp('deptPopupWindow'))){
			new deptPopupWindow().show();
		}else{
			Ext.getCmp('deptPopupWindow').show();
		}
		Ext.getCmp('deptPopupWindow').flag = 'add';
		Ext.getCmp('deptPopupForm').getForm().reset();
//		Ext.getCmp('deptPopupForm').getStore().removeAll();
	},
	fnDelete:function(){				// 删除所选记录
		var selections = Ext.getCmp('RegionalSettingsSearchGrid').getSelectionModel().getSelection(),
			chsedId = '',
			successFn = function(json){
				MessageUtil.showInfoMes(json.message);
				Ext.getCmp('RegionalSettingsSearchGrid').getStore().load();
			},
			failureFn = function(json){
				if(Ext.isEmpty(json)){
					//MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
					MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
				}else{
					MessageUtil.showErrorMes(json.message);
				}
			};
		if(selections.length>=1){
			chsedId = selections[0].get('id');
			var params = {'id':chsedId};
			MessageUtil.showQuestionMes(i18n('i18n.recompense.isDeleteThese'),function(e){
				if(e == 'yes'){
					RegionalSettingsDt.deleteUserRoleDeptRelationById( params, successFn, failureFn );
				}
			});
		}else{
			MessageUtil.showMessage(i18n('i18n.recompense.pleaseSelectOneLine'))
		};
	}
});