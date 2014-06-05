// clean compile tomcat:run -f war-pom.xml
Ext.onReady(function() {
	 
	/*********************定义 数据字典头 formPanel 添加*************************/
			 Ext.define('addformPanel', {
			 	extend:'Ext.form.Panel',
			   	 layout:{
					type : 'table',
					columns : 2
			    },
			     items: [
				     {
				        name : 'addfcodetype',
				        xtype:'textfield',
				        allowBlank :false,       //false则不能为空，默认为true 
	                    blankText : '不能为空',  //当为空时的错误提示信息
		                fieldLabel : '编码类型',
		                margin : '20 0 10 0',
		                colspan: 2
		             },{
		                name : 'addfcodetypedesc',
		                xtype:'textfield',
		                allowBlank :false,       //false则不能为空，默认为true 
	                    blankText : '不能为空',  //当为空时的错误提示信息
		                fieldLabel : '编码类型描述',
		                margin : '10 0 10 0',
		                colspan: 2
		             },{
						xtype:'button',
						text:'提交',
						margin : '10 0 10 100', 
						handler:function(){
							      var formadd = this.up('form').getForm();                 //输入集合
							      var addfcodetype = formadd.findField('addfcodetype').getValue();
							      var addfcodetypedesc=formadd.findField('addfcodetypedesc').getValue();   //编码类型描述
							//       1 ''        2''       
							if(formadd.isValid()){       //空值验证
									Ext.Ajax.request({
									    url: '../common/insertHead.action',
									    params: {
									        fcodetype:addfcodetype,                   //传递2个要添加的参数
									        fcodetypedesc:addfcodetypedesc
									    },
									    success: function(response, opts) {
									        var obj = Ext.decode(response.responseText);
									        MessageUtil.showMessage('添加一条记录成功！',function(){ window.location.href='../common/dHead.action';});
									        console.dir(obj);
									    },
									    failure: function(response, opts) {
									    	var fnFail = function(json){
											if(Ext.isEmpty(json)){
									    		MessageUtil.showErrorMes ("超时","")
									    	}else{
									    		MessageUtil.showErrorMes (json.message,"")
									    	}
										}
									    	MessageUtil.showMessage('连接失败','');
									        console.log('异常码：' + response.status);
									    }
									});
							}else{
								MessageUtil.showMessage('所加内容均不能为空','');
							}
						}
		             },{
						xtype:'button',
						text:'重置',
					    margin : '10 0 10 20', 
						handler:function(){//重置置空
						    this.up('form').getForm().reset();           //置空
						}
		             }]
			});
	/***********************定义 数据字典头 formPanel 修改***********************************/
			Ext.define('updateformPanel', {
				id:'updateformPanel',
			 	extend:'Ext.form.Panel',
			   	 layout:{
					type : 'table',
					columns : 2
			    },
			     items: [
				       {
		             	name : 'codeType',
		                xtype:'textfield',
		                allowBlank :false,       //false则不能为空，默认为true 
	                    blankText : '不能为空',  //当为空时的错误提示信息
		                margin : '20 0 10 0', 
		                fieldLabel : '编码描述',
		                colspan: 2
		             },{
		                name : 'codeTypeDesc',
		                xtype:'textfield',
		                allowBlank :false,       //false则不能为空，默认为true 
	                    blankText : '不能为空',  //当为空时的错误提示信息
		                fieldLabel : '编码类型描述',
		                margin : '10 0 10 0',
		                colspan: 2
		             },{
						xtype:'button',
						text:'保存',
						margin : '10 0 10 100', 
						handler:function(){
							     var formupdate = this.up('form').getForm();                 //输入集合
							     var updatefcodetype=formupdate.findField('codeType').getValue();   //编码类型
							     var updatefcodetypedesc=formupdate.findField('codeTypeDesc').getValue();   //编码描述
							//       1 ''          2''        
							if(formupdate.isValid()){
								var record = Ext.getCmp('dHeadGridPanel').getSelectionModel().getSelection();
								 Ext.Ajax.request({
									    url: '../common/updateHead.action',
									    params: {
									        'id':record[0].get('id'),            //传递的参数为所修改的id
									        fcodetype:updatefcodetype,
									        fcodetypedesc:updatefcodetypedesc
									    },
									    success: function(response, opts) {
									        var obj = Ext.decode(response.responseText);
									        if(obj.updatestatus){
									        	 MessageUtil.showMessage('修改一条记录成功！',function(){ window.location.href='../common/dHead.action';});
									        }else{
									        	 MessageUtil.showMessage('修改失败！','');
									        	 this.up('window').close();
									        }
									        console.dir(obj);
									    },
									    failure: function(response, opts) {
									    	var obj = Ext.decode(response.responseText);
									    	MessageUtil.showMessage('连接失败','');
									        console.log('异常码：' + response.status);
									    }
									});
							}else{
								MessageUtil.showMessage('所改内容均不能为空','');
							}
						}
		             },{
						xtype:'button',
						text:'取消',
					    margin : '10 0 10 20', 
						handler:function(){   //取消
					        this.up('window').close();
						}
		             }
			     ]
			});
			/***********************定义 数据字典头formPanel 查看详情***********************************/
			Ext.define('lookupformPanel', {
				id:'lookupformPanel',
			 	extend:'Ext.form.Panel',
			   	layout:{
					type : 'table',
					columns : 2
			    },
			    items:[{
			        name : 'codeType',
			        xtype:'textfield',
			        readOnly : true,
			        margin : '10 0 5 0', 
	                fieldLabel : '编码类型'
	             },{
	                name : 'codeTypeDesc',
	                xtype:'textfield',
	                readOnly : true,
	                margin : '10 0 5 0', 
	                fieldLabel : '编码类型描述'
	             },{
	             	name : 'empcode',
	                xtype : 'textfield',
	                readOnly : true,
	                margin : '10 0 5 0', 
	                fieldLabel : '最后修改人工号'
	             },{
	             	name : 'empname',
	                xtype : 'textfield',
	                readOnly : true,
	                margin : '10 0 5 0', 
	                fieldLabel : '最后修改人姓名'
	             },{
	             	name : 'modifyDate',
	                xtype : 'textfield',
	                readOnly : true,
	                margin : '10 0 10 0', 
	                fieldLabel : '最后更新时间'
	             },{
					xtype:'button',
					text:'取消',
				    margin : '10 0 10 100',
					handler:function(){   //取消
				        this.up('window').close();
					}
	             }]
			});
	
	/**********************定义弹窗PopWindow 添加**************************/
	Ext.define('addHeadWin', {
		    extend:'PopWindow',
			title:'新增数据字典头',//'新增',
			width:345,
			height:195
			});
			
	/**********************定义弹窗PopWindow 修改**************************/
	Ext.define('updateLookHeadWin', {
		    extend:'PopWindow',
			title:'修改数据字典头',
			width:345,
			height:195,
			listeners:{
						beforeshow:function(){   //在展示窗口前，自动加载数据
							var record = Ext.getCmp('dHeadGridPanel').getSelectionModel().getSelection();
							var formupdate = Ext.getCmp('updateformPanel').getForm();                   //获得当前form
					            formupdate.loadRecord(record[0]);                         //加载相关Model列数据
						}
			}
			});
			
	/**********************定义弹窗PopWindow 查看详情**************************/
	Ext.define('LookHeadWin', {
		    id:'LookHeadWin',
		    extend:'PopWindow',
			width:577,
			title:'查看详情',
			height:178,
			listeners:{
				beforeshow:function(){   //在展示窗口前，自动加载数据
					var record = Ext.getCmp('dHeadGridPanel').getSelectionModel().getSelection();
					var formlookup = Ext.getCmp('lookupformPanel').getForm();                   //获得当前form
					//转换日期格式为 Y-m-d H:i:s
					var ctd=function(value){
						var dt=new Date(value);
			            return Ext.util.Format.date(dt,'Y-m-d H:i:s')
			        }
			        //加载前将值赋进去
			        formlookup.loadRecord(record[0]);
			        formlookup.findField('modifyDate').setValue(ctd(record[0].get('modifyDate')));
				}
			}
			});
	/*****************1定义表单输入面板**************************/
	
	Ext.define('dHeadformPanel', {
		        
				extend : 'Ext.form.Panel',
				autoScroll : true,
				layout : {
					type : 'table',
					columns : 1
				},
				initComponent : function() {
					var me = this;
					me.items = [
					{
					    xtype:'fieldset',          //表单面板的布局
					    title: '数据字典头',
					    margin : '10 10 10 10',
					    layout : {
									type : 'table',
									columns : 2
								},
						width:796,
						height : 70,
						defaultType : 'textfield',
						defaults : {
							margin : '10 10 10 50'   //表单组建的布局
						},
					    items :[ {   
					               name : 'fcodetype',
					               fieldLabel : '编码类型'
					           },{   
					               name : 'fcodetypedesc',
					               fieldLabel : '编码类型描述'
					           }]
					}];
					this.callParent();
				},
				renderTo : Ext.getBody()
			});
	/*****************2定义中间按钮面板**************************/
	 Ext.define('dHeadformAndBtnPanel',{
		extend:'NormalButtonPanel',
//		margin:'0 10 0 0',
		items:[{
			xtype:'leftbuttonpanel',
			defaultType:'button',
			items:[{
				//'新增',
				text:'新增',
				handler:function(){
						var addHeadPopWin=Ext.create('addHeadWin',{
						    items:[Ext.create('addformPanel',{})]
						});
					 addHeadPopWin.show();  
				}
			},{
				//修改',
				text:'修改',
				handler:function(){
					//修改界面form读取信息
					var record = Ext.getCmp('dHeadGridPanel').getSelectionModel().getSelection();
					if(record.length!=1){
						MessageUtil.showMessage('请选择一行','');
						return;
					}
						//只有选择了一行时才能弹出修改窗口
						if(record.length==1){
						  var updateLookupHeadWin=Ext.create('updateLookHeadWin',{
						    items:[Ext.create('updateformPanel',{})]
						});
					     updateLookupHeadWin.show();  
						}
				}
			},{
				//删除',
				text:'删除',
				handler:function(){
					var record = Ext.getCmp('dHeadGridPanel').getSelectionModel().getSelection();
					if(record.length!=1){
						MessageUtil.showMessage('请选择一行','');
						return;
					}
					/**
					 * 先查询是否关联
					 */
					
					var chooseFn = function(e) {
						if (e == 'yes') {
							Ext.Ajax.request({
							    url: '../common/querydetailbycodetype.action',    //映射为通过编码类型判断是否有关联
							    params: {
							        fcodetype:record[0].get('codeType')           //传递的参数为关联的codeType
							    },
							    success: function(response, opts) {
							        var obj = Ext.decode(response.responseText);
							        console.dir(obj);
							        if(obj.datedictionaryManagementList.length==0){
							        	 //发送删除请求
							        	Ext.Ajax.request({
										    url: '../common/delHead.action',       //执行删除操作
										    params: {
										        'id':record[0].get('id')           //传递的参数为所删除的id
										    },
										    success: function(res, opts) {
										        var obj2 = Ext.decode(res.responseText);
										        if(obj2.updatestatus){                                //删除成功，跳转到查询页
										        	 MessageUtil.showMessage('删除一条记录成功！',function(){ window.location.href='../common/dHead.action'; });
										        }else{
										        	 MessageUtil.showMessage('删除失败！','');
										        }
										        console.dir(obj2);
										    },
										    failure: function(res, opts) {
										    	MessageUtil.showMessage('连接失败','');
										        console.log('异常码：' + res.status);
										    }
												});                         
							        }else{
									        	MessageUtil.showMessage('哈哈，数据字典【头，详细】关联，请在详细里面删除关联先！','');
									        }
									        
									    },
									    failure: function(response, opts) {
									    	MessageUtil.showMessage('连接失败','');
									        console.log('异常码：' + response.status);
									    }
									});
								
						 } else {
									return;
								}
							}
					 var msg = '亲，确认删除？';
					 MessageUtil.showQuestionMes(msg,chooseFn);
				}
			},{
				
				//查看详情',
				text:'查看详情',
				handler:function(){
				   var record = Ext.getCmp('dHeadGridPanel').getSelectionModel().getSelection();
					if(record.length!=1){
						MessageUtil.showMessage('请选择一行','');
						return;
					}
					var param = {
							'id':record[0].get('id')
					};
						//只有选择了一行时才能弹出修改窗口
						if(record.length==1){
						  var LookHeadPopWin=Ext.create('LookHeadWin',{
						      items:[Ext.create('lookupformPanel',{})]
						  });
					     LookHeadPopWin.show();  
						}
					var fnFail = function(json){
						if(Ext.isEmpty(json)){
//				    		MessageUtil.showErrorMes ("超时","")
				    	}else{
				    		MessageUtil.showErrorMes (json.message,"")
				    	}
					}
				}
			}]
		},{
			xtype:'middlebuttonpanel'
		},{
			xtype:'rightbuttonpanel',
			items:[{
				//'查询',
				xtype:'button',
				text:'查询',
				handler:function(){
			        Ext.getCmp('dHeadGridPanel').getStore().load(); // 加载数据
					}
			},{
				//'重置',
				xtype:'button',
				text:'重置',
				handler:function(){
					Ext.getCmp('dHeadformPanel').getForm().reset();
				}
			}]
		}],
		renderTo : Ext.getBody()
	});
	/*****************3定义grid表格面板  数据字典头**************************/
	 
	Ext.define('dHeadGridPanel', {
		extend : 'SearchGridPanel',
		height:630,
		loadMask : true,             //显示加载提示
		initComponent : function() {
			var me = this;
			me.columns = me.getColumns();
			me.store = me.getWRStore();
			me.bbar = me.getBbar();
			this.callParent();
			me.store.on('beforeload', function(store, operation, eOpts) {
				        var dHeadformPanel = Ext.getCmp('dHeadformPanel').getForm();                 //获得输入参数的form
						var fcodetype = dHeadformPanel.findField('fcodetype').getValue();
						var fcodetypedesc = dHeadformPanel.findField('fcodetypedesc').getValue();   //两个参数 的传递
						Ext.apply(operation, {
									params : {
										fcodetype : fcodetype,
										fcodetypedesc:fcodetypedesc
									}
								});
					});
		},
		getBbar : function() {//Ext.getCmp('dataDictionaryGridPanel').getStore().load(); //加载数据
			var me = this;
			me.selModel = Ext.create('Ext.selection.CheckboxModel');
			return Ext.create('Ext.toolbar.Paging', {
				displayMsg : '共<font color=\"green\"> {2} </font>条记录,当前页记录索引<font color=\"green\"> {0} - {1}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
				displayInfo : true,
				id : 'pagingToolbar',
				store : me.store,
				items : ['-', {
							text : '每页',
							xtype : 'tbtext'
						}, Ext.create('Ext.form.ComboBox', {
							width : 50,
							value : '10',
							triggerAction : 'all',
							forceSelection : true,
							editable : false,
							name : 'comboItem',
							displayField : 'value',
							valueField : 'value',
							queryMode : 'local',
							store : Ext.create('Ext.data.Store', {
										fields : ['value'],
										data : [{
													'value' : '10'
												}, {
													'value' : '15'
												}, {
													'value' : '20'
												}, {
													'value' : '25'
												}, {
													'value' : '40'
												}, {
													'value' : '100'
												}]
									}),
							listeners : {
								select : {
									scope : this,
									fn : function(_field, _value) {
										var pageSize = Ext.getCmp('dHeadGridPanel').store.pageSize;
										var newPageSize = parseInt(_field.value);
										if (pageSize != newPageSize) {
											Ext.getCmp('dHeadGridPanel').store.pageSize = newPageSize;
											Ext.getCmp('pagingToolbar').moveFirst();
										}
									}
								}
							}
						}), {
							text : '条',
							xtype : 'tbtext'
						}]
			})
		},
		getColumns : function() {
			var me = this;
			return [{   
				        text : '序号',
						xtype: 'rownumberer',
						width:40
						
					}, {
						text : '编码类型',
						dataIndex : 'codeType',
						width:210
					},{
						text : '编码类型描述',
						dataIndex : 'codeTypeDesc',
						width:190
					},{
						text : '最后修改人工号',
						dataIndex : 'empcode',
						width:111
					},{
						text : '最后修改人姓名',
						dataIndex : 'empname',
						width:110
					},{
						
						text : '最后更新时间',
						dataIndex : 'modifyDate',
						width:140,
						renderer:function(value){
							var dt=new Date(value);
			                return Ext.util.Format.date(dt,'Y-m-d H:i:s')
			            }
					}]
		},
		getWRStore : function() {
			return new Ext.create('dHeadManagementStore', {
						id:'dHeadManagementStore'
					});
		},
		renderTo : Ext.getBody()    //显示Body
	});
	
	/*************************************最外层全局布局显示*********************************/
    var viewport=Ext.create('Ext.Viewport',{
		layout :{
		   type: 'vbox',
		   align : 'stretch'
		   },
		items:[
	           Ext.create('dHeadformPanel', {
			   id : 'dHeadformPanel'
			   }), 
			   Ext.create('dHeadformAndBtnPanel', {
			   id : 'dHeadformAndBtnPanel'
			   }),
			   Ext.create('dHeadGridPanel', {
			   id : 'dHeadGridPanel',
			    //添加双击事件
				listeners: {
		    	 itemdblclick: {
		             fn: function(){  
		            	 var sm =Ext.getCmp('dHeadGridPanel').getSelectionModel(); 
		            	 if(sm.getSelection().length > 0){ 
		 					 var record =sm.getSelection()[0];
		 					 var LookHeadPopWin=Ext.create('LookHeadWin',{
						         items:[Ext.create('lookupformPanel',{})]
						     });
					     LookHeadPopWin.show(); 
		 				 } else {
		 					 MessageUtil.showMessage('未选中行');
		 				 }
		             }
		    	 } 
		       }
			   })
		]
	});
	viewport.setAutoScroll(false);	
	viewport.doLayout();			
			
});