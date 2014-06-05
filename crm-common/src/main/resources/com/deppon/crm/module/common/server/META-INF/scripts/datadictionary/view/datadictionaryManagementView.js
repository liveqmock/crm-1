// clean compile tomcat:run -f war-pom.xml
Ext.onReady(function() {
	
	/*********************定义 数据字典详细 formPanel 添加*************************/
			 Ext.define('adddetailformPanel', {
			 	extend:'Ext.form.Panel',
			   	 layout:{
					type : 'table',
					columns : 2
			    },
			     items: [{
			     	        id:'addfcodetype',
					        name : 'addfcodetype',
					        xtype:'queryCombox',
					        margin : '20 0 0 0',
					        allowBlank :false,       //false则不能为空，默认为true 
		                    blankText : '不能为空',  //当为空时的错误提示信息
			                fieldLabel : '编码类型',
			                store:Ext.create('Ext.data.Store', {
										pageSize : 10,
										model : 'datadictionaryheadModel',
										proxy : {
											type : 'ajax',
											actionMethods : 'POST',
											url : '../common/queryheadall.action',
											reader : {
												type : 'json',
												root : 'datedictionaryHeadList',
												totalProperty : 'totalCount'
											}
										},
									   listeners:{
											beforeload:function(store, operation, eOpts){
												Ext.apply(operation,{
													params : {
														'fcodetype':Ext.getCmp('addfcodetype').getValue()
														}
													}
												);	
											}
										}
									}),
		                    queryMode: 'remote',
		                    displayField: 'codeType',
		                    valueField: 'codeType',
		                    listConfig: {
						     	minWidth :300,   //下拉框显示宽度
						     	getInnerTpl: function() {
				            	 return '{codeType}&nbsp&nbsp&nbsp{codeTypeDesc}';
				                }
				             },
        			        listeners:{
        				       select:function(combo, records, eOpts){
      	                		  Ext.getCmp('addfcodetypedesc').setValue(records[0].data.codeTypeDesc);  //自动带出编码类型描述
      	                 	   }
        			        }
	                     },{
	                     	id:'addfcodetypedesc',
			                name : 'addfcodetypedesc',
			                xtype:'textfield',
			                margin : '20 0 0 0',
			                allowBlank :false,       //false则不能为空，默认为true 
		                    blankText : '不能为空',  //当为空时的错误提示信息
		                    readOnly:true,
			                fieldLabel : '编码类型描述'
			             },{
			                name : 'addfcode',
			                xtype:'textfield',
			                allowBlank :false,       //false则不能为空，默认为true 
		                    blankText : '不能为空',  //当为空时的错误提示信息
			                margin : '20 0 0 0', 
			                fieldLabel : '编码'
			             },{
			             	name : 'addfcodedesc',
			                xtype:'textfield',
			                allowBlank :false,       //false则不能为空，默认为true 
		                    blankText : '不能为空',  //当为空时的错误提示信息
			                margin : '20 0 0 0', 
			                fieldLabel : '编码描述'
			             },{
			             	name : 'addfcodeseq',
			                xtype : 'numberfield',
			                allowBlank :false,       //false则不能为空，默认为true 
		                    blankText : '不能为空',  //当为空时的错误提示信息
			                margin : '20 0 20 0', 
			                fieldLabel : '编码序列',
			                colspan: 2
			             },{
							xtype:'button',
							text:'提交',
							margin : '0 0 20 200', 
							handler:function(){
								     var formdetailadd = this.up('form').getForm();                 //输入集合
								     var fcodetype=formdetailadd.findField('addfcodetype').getValue();   //编码类型
								     var fcodetypedesc=formdetailadd.findField('addfcodetypedesc').getValue();   //编码类型描述
								     var fcode=formdetailadd.findField('addfcode').getValue();           //编码
								     var fcodedesc=formdetailadd.findField('addfcodedesc').getValue();   //编码描述
								     var fcodeseq=formdetailadd.findField('addfcodeseq').getValue();     //编码序列
							
								//       1 null          2''              3''               4 ''
								if(formdetailadd.isValid()){
										Ext.Ajax.request({
										    url: '../common/insertdetail.action',
										    params: {
										        fcodetype:fcodetype,                   //传递4个要添加的参数
										        fcode:fcode,
										        fcodedesc:fcodedesc,
										        fcodeseq:fcodeseq
										    },
										    success: function(response, opts) {
										        var obj = Ext.decode(response.responseText);
										        MessageUtil.showMessage('添加一条记录成功！',function(){ window.location.href='../common/dDetail.action';});
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
						    margin : '0 0 20 50', 
							handler:function(){//重置置空
								 this.up('form').getForm().reset();           //置空
							}
	             }]
			});
	/***********************定义 数据字典详细 formPanel 修改***********************************/
			Ext.define('updatedetailformPanel', {
				id:'updateformPanel',
			 	extend:'Ext.form.Panel',
			   	 layout:{
					type : 'table',
					columns : 2
			    },
			     items: [{
			        id : 'updatefcodetype',
			        name:'codeType',
			        margin : '20 0 20 0', 
			        xtype:'queryCombox',
			        allowBlank :false,       //false则不能为空，默认为true 
                    blankText : '不能为空',  //当为空时的错误提示信息
	                fieldLabel : '编码类型',
	                store:Ext.create('Ext.data.Store', {
								pageSize : 10,
								model : 'datadictionaryheadModel',
								proxy : {
									type : 'ajax',
									actionMethods : 'POST',
									url : '../common/queryheadall.action',
									reader : {
										type : 'json',
										root : 'datedictionaryHeadList',
										totalProperty : 'totalCount'
									}
								},
							   listeners:{
									beforeload:function(store, operation, eOpts){
										Ext.apply(operation,{
											params : {
												'fcodetype':Ext.getCmp('updatefcodetype').getValue()
												}
											}
										);	
									}
								}
							}),
                    queryMode: 'remote',
                    displayField: 'codeType',
                    valueField: 'codeType',
                    listConfig: {
			     	minWidth :330,   //下拉框显示宽度
			     	getInnerTpl: function() {
	            	 return '{codeType}&nbsp&nbsp&nbsp{codeTypeDesc}';
	            }
				    },
        			listeners:{
        				select:function(combo, records, eOpts){
      	                		Ext.getCmp('updatefcodetypedesc').setValue(records[0].data.codeTypeDesc);  //自动带出编码类型描述
      	                	}
        			}
	             },{
	                id : 'updatefcodetypedesc',
	                name : 'codeTypeDesc',
	                margin : '20 0 20 0', 
	                xtype:'textfield',
	                allowBlank :false,       //false则不能为空，默认为true 
                    blankText : '不能为空',  //当为空时的错误提示信息
	                fieldLabel : '编码类型描述'
	             },{
	                name : 'code',
	                xtype:'textfield',
	                allowBlank :false,       //false则不能为空，默认为true 
                    blankText : '不能为空',  //当为空时的错误提示信息
	                margin : '0 0 20 0', 
	                fieldLabel : '编码'
	             },{
	             	name : 'codeDesc',
	                xtype:'textfield',
	                allowBlank :false,       //false则不能为空，默认为true 
                    blankText : '不能为空',  //当为空时的错误提示信息
	                margin : '0 0 20 0', 
	                fieldLabel : '编码描述'
	             },{
	             	name : 'codeSeq',
	                xtype : 'numberfield',
	                allowBlank :false,       //false则不能为空，默认为true 
                    blankText : '不能为空',  //当为空时的错误提示信息
	                margin : '0 0 20 0', 
	                fieldLabel : '编码序列'
	             },{
	             	name : 'language',
	                xtype : 'textfield',
	                allowBlank :false,       //false则不能为空，默认为true 
                    blankText : '不能为空',  //当为空时的错误提示信息
	                margin : '0 0 20 0', 
	                fieldLabel : '语言'
	             },{
					xtype:'button',
					text:'保存',
					margin : '0 0 20 200', 
					handler:function(){
						     var formdetailupdate = this.up('form').getForm();                 //输入集合
						     var fcodetype=formdetailupdate.findField('codeType').getValue();   //编码类型
						     var fcode=formdetailupdate.findField('code').getValue();           //编码
						     var fcodedesc=formdetailupdate.findField('codeDesc').getValue();   //编码描述
						     var fcodeseq=formdetailupdate.findField('codeSeq').getValue();     //编码序列
						     var language=formdetailupdate.findField('language').getValue();     //语言
						//       1 null          2''              3''               4 ''
						if(formdetailupdate.isValid()){
							var record = Ext.getCmp('dataDictionaryGridPanel').getSelectionModel().getSelection();
							 Ext.Ajax.request({
								    url: '../common/updatedetailnew.action',
								    params: {
								        'id':record[0].get('id'),            //传递的参数为所修改的id
								        fcodetype:fcodetype,
								        fcode:fcode,
								        fcodedesc:fcodedesc,
								        fcodeseq:fcodeseq,
								        language:language
								    },
								    success: function(response, opts) {
								        var obj = Ext.decode(response.responseText);
								        if(obj.updatestatus){
								        	 MessageUtil.showMessage('修改一条记录成功！',function(){ window.location.href='../common/dDetail.action';});
								            
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
				    margin : '0 0 20 50', 
					handler:function(){   //取消
				        this.up('window').close();
					}
	             }]
			});
			/***********************定义 数据字典详细formPanel 查看详情***********************************/
			Ext.define('lookupdetailformPanel', {
				id:'lookupdetailformPanel',
			 	extend:'Ext.form.Panel',
			   	layout:{
					type : 'table',
					columns : 2
			    },
			   items:[{
			        name : 'codeType',
			        xtype:'textfield',
			        readOnly : true,
			        margin : '10 0 0 0',
	                fieldLabel : '编码类型'
	             },{
	                name : 'codeTypeDesc',
	                xtype:'textfield',
	                readOnly : true,
	                margin : '10 0 0 0',
	                fieldLabel : '编码类型描述'
	             },{
	                name : 'code',
	                xtype:'textfield',
	                readOnly : true,
	                margin : '10 0 10 0', 
	                fieldLabel : '编码'
	             },{
	             	name : 'codeDesc',
	                xtype:'textfield',
	                readOnly : true,
	                margin : '10 0 10 0', 
	                fieldLabel : '编码描述'
	             },{
	             	name : 'status',
	                xtype : 'textfield',
	                readOnly : true,
	                margin : '5 0 10 0', 
	                fieldLabel : '是否有效'
	             },{
	             	name : 'codeSeq',
	                xtype : 'numberfield',
	                readOnly : true,
	                margin : '5 0 10 0', 
	                fieldLabel : '编码序列'
	             },{
	             	name : 'language',
	                xtype : 'textfield',
	                readOnly : true,
	                margin : '5 0 10 0', 
	                fieldLabel : '语言'
	             },{
	             	name : 'empcode',
	                xtype : 'textfield',
	                readOnly : true,
	                margin : '5 0 10 0', 
	                fieldLabel : '修改人工号'
	             },{
	             	name : 'empname',
	                xtype : 'textfield',
	                readOnly : true,
	                margin : '5 0 10 0', 
	                fieldLabel : '修改人姓名'
	             },{
	             	name : 'modifyDate',
	                xtype : 'textfield',
	                readOnly : true,
	                margin : '5 0 10 0', 
	                fieldLabel : '最后更新时间'
	             },{
					xtype:'button',
					text:'取消',
				    margin : '8 200 5 255',
				    colspan: 2,
					handler:function(){   //取消
				        this.up('window').close();
					}
	             }]
			});
	
	/**********************定义弹窗PopWindow 添加**************************/
	Ext.define('addDetailWin', {
		    extend:'PopWindow',
			width:600,
			title:'新增数据字典详细',//'新增',
			height:245
			});
			
	/**********************定义弹窗PopWindow 修改**************************/
	Ext.define('updateDetailWin', {
		    id:'updateDetailWin',
		    extend:'PopWindow',
			width:600,
			title:'修改',
			height:245,
			listeners:{
				beforeshow:function(){   //在展示窗口前，自动加载数据
					var record = Ext.getCmp('dataDictionaryGridPanel').getSelectionModel().getSelection();
					var formdetailupdate = Ext.getCmp('updateformPanel').getForm(); //获得当前form
					formdetailupdate.loadRecord(record[0]);                         //加载相关Model列数据
				}
			}
			});
			
	/**********************定义弹窗PopWindow 查看详情**************************/
	Ext.define('LookupDetailWin', {
		    id:'LookupDetailWin',
		    extend:'PopWindow',
			width:600,
			title:'查看详情',
			height:280,
			listeners:{
				beforeshow:function(){   //在展示窗口前，自动加载数据
					var record = Ext.getCmp('dataDictionaryGridPanel').getSelectionModel().getSelection();
					var formdetailview = Ext.getCmp('lookupdetailformPanel').getForm(); //获得当前form
					formdetailview.loadRecord(record[0]);  
					//转换日期格式为 Y-m-d H:i:s
					var ctd=function(value){
						var dt=new Date(value);
			            return Ext.util.Format.date(dt,'Y-m-d H:i:s')
			        }
			        formdetailview.findField('modifyDate').setValue(ctd(record[0].get('modifyDate')));
			        formdetailview.findField('status').setValue(record[0].get('status')==true?'有效':'失效'); 
				}
			}
			
			});
	/*****************1定义表单输入面板**************************/
	
	Ext.define('dataDictionaryformPanel', {
				extend : 'Ext.form.Panel',
				autoScroll : true,
				layout : {
					type : 'table',
					columns : 1
				},
				height : 138,
				initComponent : function() {
					var me = this;
					me.items = [
					{
					    xtype:'fieldset',          //表单面板的布局
					    title: '数据字典详细',
					    margin : '10 10 10 10',
					    layout : {
									type : 'table',
									columns : 2
								},
						width:796,
						height : 110,
						defaultType : 'textfield',
						defaults : {
							margin : '10 10 10 50'   //表单组建的布局
						},
					    items :[{   id : 'fcodetype',
					                fieldLabel : '编码类型'
					             },{   id : 'fcodetypedesc',
					                   fieldLabel : '编码类型描述'
					             },{
					             	id : 'fcode',
					                fieldLabel : '编码'
					             },{
					             	id : 'fcodedesc',
					             	fieldLabel : '编码描述'
					             }]
					}];
					this.callParent();
				},
				renderTo : Ext.getBody()
			});
	/*****************2定义中间按钮面板**************************/
	 Ext.define('dataDictionaryformAndBtnPanel',{
		extend:'NormalButtonPanel',
//		margin:'0 10 0 0',
		items:[{
			xtype:'leftbuttonpanel',
			defaultType:'button',
			items:[{
				//'新增',
				text:'新增',
				handler:function(){
						var addDetailPopWin=Ext.create('addDetailWin',{
						     items:[Ext.create('adddetailformPanel',{})]
						});
					 addDetailPopWin.show();  
				}
			},{
				//修改',
				text:'修改',
				handler:function(){
					//修改界面form读取信息
					var record = Ext.getCmp('dataDictionaryGridPanel').getSelectionModel().getSelection();
					if(record.length!=1){
						MessageUtil.showMessage('请选择一行','');
						return;
					}
						//只有选择了一行时才能弹出修改窗口
						if(record.length==1){
						  var updateLookupDetailWin=Ext.create('updateDetailWin',{
						      items:[Ext.create('updatedetailformPanel',{})]
						});
					     updateLookupDetailWin.show();  
						}
				}
			},{
				//删除',
				text:'删除',
				handler:function(){
					var record = Ext.getCmp('dataDictionaryGridPanel').getSelectionModel().getSelection();
					if(record.length!=1){
						MessageUtil.showMessage('请选择一行','');
						return;
					}
					var chooseFn = function(e) {
								if (e == 'yes') {
					                Ext.Ajax.request({
								    url: '../common/deldetail.action',
								    params: {
								        'id':record[0].get('id')           //传递的参数为所删除的id
								    },
								    success: function(response, opts) {
								        var obj = Ext.decode(response.responseText);
								        if(obj.updatestatus){              //判断删除状态
								        	 MessageUtil.showMessage('删除一条记录成功！',function(){window.location.href='../common/dDetail.action';});
								        }else{
								        	 MessageUtil.showMessage('删除失败！','');
								        }
								        console.dir(obj);
								    },
								    failure: function(response, opts) {
								    	var obj = Ext.decode(response.responseText);
								    	MessageUtil.showMessage('连接失败','');
								        console.log('异常码：' + response.status);
								    }
								})} else {
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
				   var record = Ext.getCmp('dataDictionaryGridPanel').getSelectionModel().getSelection();
					if(record.length!=1){
						MessageUtil.showMessage('请选择一行','');
						return;
					}
					var param = {
							'id':record[0].get('id')
					};
						//只有选择了一行时才能弹出修改窗口
						if(record.length==1){
						  var LookupDetailPopWin=Ext.create('LookupDetailWin',{
						   items:[Ext.create('lookupdetailformPanel',{})]
						});
					     LookupDetailPopWin.show();  
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
			        Ext.getCmp('dataDictionaryGridPanel').getStore().load(); // 加载数据
					}
			},{
				//'重置',
				xtype:'button',
				text:'重置',
				handler:function(){
					Ext.getCmp('dataDictionaryformPanel').getForm().reset();
				}
			}]
		}],
		renderTo : Ext.getBody()
	});
	/*****************3定义grid表格面板**************************/
	 
	Ext.define('dataDictionaryGridPanel', {
		extend : 'SearchGridPanel',
		height:585,
		loadMask : true,             //显示加载提示
		initComponent : function() {
			var me = this;
			me.columns = me.getColumns();
			me.store = me.getWRStore();
			me.bbar = me.getBbar();
			this.callParent();
			me.store.on('beforeload', function(store, operation, eOpts) {
						var fcodetype = Ext.getCmp('fcodetype').getValue();
						var fcodetypedesc = Ext.getCmp('fcodetypedesc').getValue();   //四个参数 的传递
						var fcode = Ext.getCmp('fcode').getValue();
						var fcodedesc = Ext.getCmp('fcodedesc').getValue();
						Ext.apply(operation, {
									params : {
										fcodetype : fcodetype,
										fcodetypedesc:fcodetypedesc,
										fcode : fcode,
										fcodedesc:fcodedesc
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
										var pageSize = Ext.getCmp('dataDictionaryGridPanel').store.pageSize;
										var newPageSize = parseInt(_field.value);
										if (pageSize != newPageSize) {
											Ext.getCmp('dataDictionaryGridPanel').store.pageSize = newPageSize;
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
						
					},{
						text : '编码类型',
						dataIndex : 'codeType',
						width:210
					},{
						text : '编码类型描述',
						dataIndex : 'codeTypeDesc',
						width:210
					}, {
						text : '编码',
						dataIndex : 'code',
						width:160
					}, {
						text : '编码描述',
						dataIndex : 'codeDesc',
						width:143
					}, {
						text : '是否有效',
						dataIndex : 'status',
						width:65,
						renderer:function(value){
			            return value==true?'有效':'失效'
			            }
					}, {
						text : '编码序列',
						dataIndex : 'codeSeq',
						width:65
					}, {
						text : '语言',
						dataIndex : 'language'
					}, {
						text : '修改人工号',
						dataIndex : 'empcode'
					}, {
						text : '修改人姓名',
						dataIndex : 'empname'
					}, {
						
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
			return new Ext.create('datadictionaryManagementStore', {
				         id:'datadictionaryManagementStore'
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
	           Ext.create('dataDictionaryformPanel', {
			   id : 'dataDictionaryformPanel'
			   }), 
			   Ext.create('dataDictionaryformAndBtnPanel', {
			   id : 'dataDictionaryformAndBtnPanel'
			   }),
			   Ext.create('dataDictionaryGridPanel', {
			   id : 'dataDictionaryGridPanel',
			  //添加双击事件
				listeners: {
		    	 itemdblclick: {
		             fn: function(){  
		            	 var sm = Ext.getCmp('dataDictionaryGridPanel').getSelectionModel(); 
		            	 if(sm.getSelection().length > 0){ 
		 					 var record =sm.getSelection()[0];
		 					  var LookupDetailPopWin=Ext.create('LookupDetailWin',{
						       items:[Ext.create('lookupdetailformPanel',{})]
						      });
		 					 LookupDetailPopWin.show();  
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