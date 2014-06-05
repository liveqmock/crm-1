﻿/**
 * @author rock
 * helpDocManagement:帮助管理后台
 */

// var oHelpDocManagement	= (CONFIG.get("TEST")) ? new oHelpDocManagementTest() : new oHelpDocManagementData();

// data层获取data测试数据
/**
 * 	(function() {
		var sUrl = "../oHelpDocManagementTest.js";				//TODO:
		if (CONFIG.get('TEST')) {
			document.write('<script type="text/javascript" class="oHelpDocManagementTest" src="' + sUrl + '"></script>');
		}
	})();
* */

Ext.onReady(function() {
	Ext.form.Field.prototype.msgTarget='side';
	var oHelpDocManagementData = function(){};
	/**
	 * @class:oHelpDocManagementData 帮助数据Data层
	 * @param:{jP,fnS,fnF}  
	 * 		jP 	:{}	json参数
	 * 		fnS	:Fn	ajax成功回调函数
	 * 		fnF	:Fn	ajax失败回调函数
	 * @exception
	 * @author Rock
	 * 2012-09-22
	 */
	oHelpDocManagementData.prototype = {
		/**
		 * @method:新增帮助文档 
		 */
		fnAddDoc: function(jP,fnS,fnF){
			var u = 'addDoc.action';
			DpUtil.requestJsonAjax(u,jP,fnS,fnF);
		},
		/**
		 * @method:删除帮助文档 
		 */
		fnDelDoc: function(jP,fnS,fnF){
			var u = 'delDoc.action';
			DpUtil.requestJsonAjax(u,jP,fnS,fnF);
		},
		/**
		 * @method:修改帮助文档 
		 */
		fnUpdDoc: function(jP,fnS,fnF){
			var u = 'updDoc.action';
			DpUtil.requestJsonAjax(u,jP,fnS,fnF);
		},
		/**
		 * @method:获取表格model 
		 */
		fnGetModel: function(){
			var model = Ext.define('helpDocGridModel',{
				extend: 'Ext.data.Model',
				fields : [
					{name:'active'}						// 是否显示
					,{name:'belongMenu'}				// 所属菜单
					,{name:'belongModule'}				// 所属模块
					,{name:'createDate'}
					,{name:'createUser'}
					,{name:'helpContent'}				// 帮助内容
					,{name:'helpTitle'}					// 帮助标题
					,{name:'id'}
					,{name:'modifyDate'}
					,{name:'modifyUser'}
					,{name:'windowNum'}					// 弹窗编号
					]									// 最后修改时间
			});
			return model;
		},
		/**
		 * @method:查询帮助文档-1: 初始查询出所有的帮助文档	后更改为获取表格的store，发送数据使用 beforeload
		 */
		fnSearchDocAll:function(){
			
			Ext.define('helpDocGridModel',{
				extend: 'Ext.data.Model',
				fields : [
					{name:'active'}						// 是否显示
					,{name:'belongMenu'}				// 所属菜单
					,{name:'belongModule'}				// 所属模块
					,{name:'createDate'}
					,{name:'createUser'}
					,{name:'helpContent'}				// 帮助内容
					,{name:'helpTitle'}					// 帮助标题
					,{name:'id'}
					,{name:'modifyDate'}
					,{name:'modifyUser'}
					,{name:'windowNum'}					// 弹窗编号
					]									// 最后修改时间
			});
/**
 * 搜索实体：
	  active
	  belongMenu
	  belongModule
	  windowNum
	  startDate
	  endDate
	  helpTitle
	  start
	  limit 
 */
			Ext.define('searchDocAllStore',{
				extend:'Ext.data.Store',
				model:'helpDocGridModel',
				autoLoad:true,
				pageSize:40,
				proxy:{
					type:'ajax',
					url:'searchDoc.action',
					actionMethods:'POST',
					reader:{
						type:'json',
						root:'helpDocList'
						,totalProperty : 'totalCount'
					}
				}
			});
			return new searchDocAllStore();
		},
		/**
		 * @method:查询帮助文档-2: 根据查询表单的查询条件查询
		 * @param:jP:oCondition
		 */
		fnSearchDocByCdtn:function(jP,fnS,fnF){
			var u = 'searchDoc.action';
			DpUtil.requestJsonAjax(u,jP,fnS,fnF);
		},
		/**
		 * @method:查询帮助文档-3 
		 */
		fnSearchDocByNum:function(jP,fnS,fnF){
			var u = 'searchDocById.action';
			DpUtil.requestJsonAjax(u,jP,fnS,fnF);
		},
		/**
		 * @method:获取combox的所属模块		// 后修改为
		 * 										select:function(th){
		 * 											Ext.getCmp("belongMenuId").store.load({params:{'functionCode':th.getValue()}} );
		 * 										}，
		 * 									// 需要在这之前：	1.后者使用store	store : Ext.create('srchMenuStore')，		；
		 * 														2.然后前者在select事件触发 ，并将参数load进去。
		 */
		fnCreatModuBeyd:function(jP,fnS,fnF){
			var u = 'srchModu.action';
			Ext.define('moduBeydModel',{
				extend: 'Ext.data.Model',
				fields : [
					{name:'functionName'}
					,{name:'functionCode'}
					]
			});
			Ext.define('srchModuStore',{
				extend:'Ext.data.Store',
				model:'moduBeydModel',
				autoLoad:false,
				proxy:{
					type:'ajax',
					url:'../authorization/srchModu.action',					//	"/authorization"	————>	"../authorization"
					actionMethods:'POST',
					reader:{
						type:'json',
						root:'functionList'
						// ,totalProperty : 'totalCount'
					}
				}
			});
			return new srchModuStore();
		},
		/**
		 * @method:获取combox的所属导航			//后修改为	使用AMD模式直接定义:	Ext.define('srchMenuStore',{
		 */
		fnCreatMenuBeyd:function(jP,fnS,fnF){
			var u = '../authorization/srchMenu.action';
			Ext.define('menuBeydModel',{
				extend: 'Ext.data.Model',
				fields : [	{name:'functionName'}	]
			});
			Ext.define('srchMenuStore',{
				storeId: 'menuBeydStoreId',
				extend:'Ext.data.Store',
				model:'menuBeydModel',
				autoLoad:false,
				proxy:{
					type:'ajax',
					url:u,
					actionMethods:'POST',
					reader:{
						type:'json',
						root:'functionList'
						// ,totalProperty : 'totalCount'
					}
				}
			});
			return new srchMenuStore();
		}
	};

	/**
	 * @class: "修改 "弹窗上部form的model
	 */
	Ext.define('TopFormPanelModel', {
	    extend: 'Ext.data.Model',
	    fields: [
	        {name: 'windowNum', 	type: 'string'},
	        {name: 'belongModule',	type: 'string' },
	        {name: 'belongMenu',	type: 'string' },
	        {name: 'activeId',		type: 'string' },
	        {name: 'helpTitle',		type: 'string' }
	    ],
	    changeName: function() {				// 事件接口
	        var oldName = this.get('windowNum'),
	            newName = oldName + "add sth";
	        this.set('windowNum', newName);
	    }
	});
/**
 * @class:所属导航model
 */
	Ext.define('menuBeydModel',{
		extend: 'Ext.data.Model',
		fields : [
			{name:'functionName'}
			,{name:'functionCode'}
			]
	});

/**
 *  @class:所属导航store
 */
	Ext.define('srchMenuStore',{
		storeId: 'menuBeydStoreId',
		extend:'Ext.data.Store',
		model:'menuBeydModel',
		// autoLoad:true,
		proxy:{
			type:'ajax',
			url:'../authorization/srchMenu.action',
			actionMethods:'POST',
			reader:{
				type:'json',
				root:'functionList'
				// ,totalProperty : 'totalCount'
			}
		}
	});
	//TODO:data层完毕.
	
	var oHelpDocManagement	= new oHelpDocManagementData(),
		selected			= null;									// 选定的records；
		
/**
 * @param:	编辑器参数
 * @see：	http://www.kindsoft.net/docs/index.html
 */
	var jO = {
		options:{
			pasteType : 0,
			height :"300px",
			width:'100%',
			id:'editorId',
			resizeType:0,
			minChangeSize:10,
			pasteType:1,
			afterChange:null,	//编辑器内容发生改变后执行的回调函数
			items:[
				"source",				//HTML代码
				"preview", 				//预览
				"undo", 				//后退
				"redo", 				//前进
				"cut", 					//剪切
				"copy", 				//复制
				"paste", 				//粘贴
				"wordpaste", 			//从Word粘贴
				"insertorderedlist",	//编号
				"insertunorderedlist",	//项目符号
				"subscript",			//下标
				"superscript", 			//上标
				"formatblock",			//段落
				"fontname",				//字体
				"fontsize",				//文字大小
				"hr",					//插入横线
				"link",					//超级链接
				"unlink", 				//取消超级链接
				"fullscreen",			//全屏显示
				"lineheight",			//行距
				"quickformat",			//一键排版
				"template"				//插入模板
			]
			/**
			 * @method：编辑器失去焦点做长度校验。 
			 */
			,afterBlur :function(){
				var l = this.html().length;
				if(l>=1000){
					MessageUtil.showMessage(i18n('i18n.helpdoc.inputSthPerfect'));	//数据过长，请节制。
					return false;
				};
			}
			,fontSizeTable:14
	}};
	
	
	Ext.define('TopFormPanel',{
		extend:'SearchFormPanel',
		id:'TopFormId',
		items:null,
		height:90,
		initComponent:function(){
			this.items = this.getItems();
			this.defaults = this.getDefaultsContainer();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [{
				// xtype:'basicfiledset',
				layout:{
					type:'table',
					columns:3
				},
				margin:'5',
				defaultType:'textfield',
				defaults:{
					labelWidth:100,
					width:250
				},
				items:[
				{
					fieldLabel:i18n('i18n.helpdoc.windowNum')	//'帮助编号',
					,xtype: '',
       	 			name: 'windowNum',
       	 			id:'windowNumId'
				}
				// ,{
					// fieldLabel:'帮助标题',
					// xtype: '',
       	 			// name: 'helpTitle',
       	 			// id:'helpTitleId'
				// }
				,{
			        xtype: 'datefield',
			        anchor: '100%',
			        fieldLabel: i18n('i18n.helpdoc.from_date')	//'修改时间从',
			        ,name: 'from_date',
			        id:'from_dateId',
			        maxValue: new Date()
			    }, {
			        xtype: 'datefield',
			        anchor: '100%',
			        fieldLabel: i18n('i18n.helpdoc.to_date')	//'到',
			        ,name: 'to_date',
			        id:'to_dateId',
			        value: new Date()
			    },
				Ext.create("Ext.form.ComboBox", {
					name : "belongModule",
					id:'belongModuleId',
					fieldLabel:i18n('i18n.helpdoc.belongModule')//'所属模块',
					,triggerAction : "all",
					forceSelection : true,
					editable : true,
					displayField : "functionName",
					valueField : "functionCode",
					// queryMode : "remote",
					listConfig: {
	  					loadMask:false
					},
					listeners:{
						select:function(th){
							Ext.getCmp("belongMenuId").store.load({params:{'functionCode':th.getValue()}} );
						},
						change:function(combo){
							if(Ext.isEmpty(combo.getValue())){
								combo.setValue("");
							}
						}
					},
					store : oHelpDocManagement.fnCreatModuBeyd(),
				})
				,Ext.create("Ext.form.ComboBox", {
					name : "belongMenu",
					id:'belongMenuId',
					fieldLabel:i18n('i18n.helpdoc.belongMenu')//'所属导航',
					,triggerAction : "all",
					forceSelection : true,
					editable : true,
					displayField : "functionName",
					valueField : "functionName",
					listConfig: {
	  					loadMask:false
					},
					queryMode : "local",
					// store : Ext.create('srchMenuStore',{'functionCode':Ext.getCmp("belongModuleId").getValue()})
					store : Ext.create('srchMenuStore'),
					listeners:{
						'expand':function(){
							if(Ext.isEmpty(Ext.getCmp('belongModuleId').getValue())){
								MessageUtil.showMessage(i18n('i18n.helpdoc.selectModuleFirst'), function(btn, text){ });
							}									//请先选择所属模块，然后再选择所属导航。
						},
						change:function(combo){
							if(Ext.isEmpty(combo.getValue())){
								combo.setValue("");
							}
						}
					}
				})
				,{
					fieldLabel:i18n('i18n.helpdoc.activeOrNo')						//'是否发布',
		            ,xtype : 'fieldcontainer',
		            id:'activeId',
		            defaultType: 'radiofield',
		            defaults:{
						labelWidth:0,
						width:50
					},
		            layout: 'hbox',
		            items: [
		                {
		                    boxLabel  : i18n('i18n.util.yes')						//'是',
		                    ,name      : 'size',
		                    inputValue: 't',
		                    checked		:'checked',
		                    id        : 'radio1',
		                    margin:'0 0 0 12'
		                }, {
		                    boxLabel  : i18n('i18n.util.no')						//'否',
		                    ,name      : 'size',
		                    inputValue: 'f',
		                    id        : 'radio2'
		                }
		            ]
		        }
				]
			}];
		},
		getDefaultsContainer:function(){
			var me = this;
			return {
				labelAlign: 'right',
				labelWidth : 100,
				width : 250,
				enableKeyEvents:true,
				listeners:{
					keypress:function(field,event){									// TODO:form的keyCode13触发
				    	if(event.getKey() == Ext.EventObject.ENTER){
				    		if(!Ext.isEmpty(field.next())){
				    			field.next().focus();
				    		}else{
				    			//me.parentWin.searchButtonPanel.searchEvent();
				    		}
				    	}
				    }
				}
			};
		}
	});
	Ext.define('TopFormPanel2',{
		extend:'SearchFormPanel',
		id:'TopFormPanel2Id',
		items:null,
		height:90,
		initComponent:function(){
			this.items = this.getItems();
			this.defaults = this.getDefaultsContainer();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [{
				// xtype:'basicfiledset',
				layout:{
					type:'table',
					columns:3
				},
				margin:'5',
				defaultType:'textfield',
				defaults:{
					labelWidth:100,
					width:250
				},
				items:[
				{
					fieldLabel:i18n('i18n.helpdoc.windowNum')				//'帮助编号',
					,xtype: '',
       	 			name: 'windowNum2',
       	 			id:'numberId2',
       	 			allowBlank:false
				}
				,Ext.create("Ext.form.ComboBox", {
					name : "belongModule2",
					id:'belongModuleId2',
					fieldLabel:i18n('i18n.helpdoc.belongModule')			//'所属模块',
					,triggerAction : "all",
					forceSelection : true,
					editable : false,
					allowBlank:false,
					displayField : "functionName",
					valueField : "functionCode",
					listConfig: {
	  					loadMask:false
					},
					listeners:{
						select:function(th){
							Ext.getCmp("belongMenuId2").store.load({params:{'functionCode':th.getValue()}} );
						}
					},
					store : oHelpDocManagement.fnCreatModuBeyd(),
				})
				,Ext.create("Ext.form.ComboBox", {
					name : "belongMenu2",
					id:'belongMenuId2',
					fieldLabel:i18n('i18n.helpdoc.belongMenu')				//'所属导航',
					,triggerAction : "all",
					forceSelection : true,
					editable : false,
					allowBlank:false,
					displayField : "functionName",
					valueField : "functionName",
					listConfig: {
	  					loadMask:false
					},
					queryMode : "local",
					store : Ext.create('srchMenuStore'),
					listeners:{
						'expand':function(){
							if(Ext.isEmpty(Ext.getCmp('belongModuleId2').getValue())){
								MessageUtil.showMessage(i18n('i18n.helpdoc.selectModuleFirst'), function(btn, text){ });
							}									//请先选择所属模块，然后再选择所属导航。
						}
					}
				})
				,{
					fieldLabel:i18n('i18n.helpdoc.activeOrNo')			//'是否发布',
		            ,xtype : 'fieldcontainer',
		            id:'activeId2',
		            defaultType: 'radiofield',
		            defaults:{
						labelWidth:0,
						width:50
					},
		            layout: 'hbox',
		            items: [
		                {
		                    boxLabel  : i18n('i18n.util.yes')			//'是',
		                    ,name      : 'size',
		                    inputValue: 't',
		                    checked	:'checked',
		                    id        : 'radio21',
		                    margin:'0 0 0 12'
		                }, {
		                    boxLabel  : i18n('i18n.util.no')			//'否',
		                    ,name      : 'size',
		                    inputValue: 'f',
		                    id        : 'radio22'
		                }
		            ]
		        }
		        ,{
					fieldLabel:i18n('i18n.helpdoc.helpTitle')			//'帮助标题'
					,xtype: '',
       	 			name: 'helpTitle2',
       	 			id:'helpTitleId2'
       	 			,style:{
       	 				position:'absolute',
       	 				marginTop:"-12px"
       	 			}
       	 			,width:500
				}
				]
			}];
		},
		getDefaultsContainer:function(){
			var me = this;
			return {
				labelAlign: 'right',
				labelWidth : 100,
				width : 250,
				enableKeyEvents:true,
				listeners:{
				}
			};
		}
	});

	Ext.define('TopFormPanel3',{
		extend:'SearchFormPanel',
		id:'TopFormPanel3Id',
		items:null,
		height:90,
		initComponent:function(){
			this.items = this.getItems();
			this.defaults = this.getDefaultsContainer();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [{
				// xtype:'basicfiledset',
				layout:{
					type:'table',
					columns:3
				},
				margin:'5',
				defaultType:'textfield',
				defaults:{
					labelWidth:100,
					width:250
				},
				items:[
				{
					fieldLabel:	i18n('i18n.helpdoc.windowNum')		//'帮助编号'
					,xtype: '',
       	 			name: 'windowNum',
       	 			id:'numberId3',
       	 			readOnly:true,
       	 			cls:'readonly'

				}
				,{
					fieldLabel:i18n('i18n.helpdoc.belongModule')	//'所属模块',
       	 			,name: 'belongModule',
       	 			id:'belongModuleId3',
       	 			readOnly:true,
       	 			cls:'readonly'
				}
				,{
					fieldLabel:i18n('i18n.helpdoc.belongMenu')		//'所属导航',
					,xtype: '',
					name : "belongMenu",
       	 			id:'belongMenuId3',
       	 			readOnly:true,
       	 			cls:'readonly'
				}
				,{
					fieldLabel:i18n('i18n.helpdoc.activeOrNo')		//'是否发布',
		            ,xtype : 'fieldcontainer',
		            id:'activeId3',
		            name:'active',
		            defaultType: 'radiofield',
		            defaults:{
						labelWidth:0,
						width:50
					},
		            layout: 'hbox',
		            items: [{
		                    boxLabel  : i18n('i18n.util.yes')		//'是',
		                    ,name      : 'size',
		                    inputValue: 't',
		                    // checked	:'checked',
		                    id        : 'radio31',
		                    margin:'0 0 0 12'
		                }, {
		                    boxLabel  : i18n('i18n.util.no')		//'否',
		                    ,name      : 'size',
		                    inputValue: 'f',
		                    id        : 'radio32'
		                }]
		        },
		        {
					fieldLabel:i18n('i18n.helpdoc.helpTitle'),		//'帮助标题',
					xtype: '',
       	 			name: 'helpTitle',
       	 			id:'helpTitleId3'
       	 			,style:{
       	 				position:'absolute',
       	 				marginTop:'-12px'
       	 			}
       	 			,width:500
				}
				]
			}];
		},
		getDefaultsContainer:function(){
			var me = this;
			return {
				labelAlign: 'right',
				labelWidth : 100,
				width : 250,
				enableKeyEvents:true,
				listeners:{
				}
			};
		}
	});
Ext.define('MidButnPanel',{
	id:'MidButnPanelId',
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
			items:[
				{
					xtype:'button',
					id:'btnAdd',
					text:i18n('i18n.util.btn.add')							// '新增',
					,listeners:{
						'click':function(){
							var submitAddData = function() {
								if(!Ext.getCmp('TopFormPanel2Id').getForm().isValid()){
									MessageUtil.showMessage(i18n('i18n.helpdoc.inputSthPlease'));		//请输入数据
									return false;
								};
								var tit = Ext.getCmp('helpTitleId2').getValue(),
									bMo = Ext.getCmp('belongModuleId2').getRawValue(),
									bMe	= Ext.getCmp('belongMenuId2').getValue(),
									num	= Ext.getCmp('numberId2').getValue(),
									act	= null,
									// act	= Ext.getCmp('activeId').getValue(),
									radio1	= Ext.getCmp('radio21'),
									radio2	= Ext.getCmp('radio22');
								act = ((radio1.getValue())?true:false);
								var str = editor.html(),
					                jP = {
					                	helpDoc:{
					                		'helpContent'	: str,
					                		'helpTitle'		: tit,
						                	'belongModule'	: bMo,
						                	'belongMenu'	: bMe,
						                	'windowNum'		: num,
						                	'active'		: act
					                }},
					                fnS = function(){
					                	labelDom.innerHTML = "<font color='red'>"+i18n("i18n.ladingstation.save.success")+"</font>";						//保存成功!
					                };
					                fnF = function(json){
					                	MessageUtil.showMessage(json.message);
					                };
					            if(!num||str.length==0){
					            	MessageUtil.showMessage(i18n('i18n.helpdoc.inputSthPlease'));	//请输入数据
									// labelDom.innerHTML = "<font color='red'>木有任何数据</font>";
									return ;
								};
				                oHelpDocManagement.fnAddDoc(jP,fnS,fnF);
							};
							var win = Ext.create('Ext.window.Window', {
								title : i18n('i18n.helpdoc.addAHelpDoc')				//'新增一条帮助数据',
								,width : 820,
								height : 468,											// 是否销毁window视窗结构：closeAction:'hide',
								layout : {
									type : 'vbox',
									align : 'stretch'
								},
								items : [
									Ext.create('TopFormPanel2',{
										width:'100%'
									}),{
											id : 'editorAWrp',
											xtype : 'textarea',
											padding:'10 0 0 0',
											html:'<div id="editorAddWrp" style="position:relative;"></div>'
									}],
								buttons : [{
									id : 'opeResult',
									xtype : 'label',
									text : i18n('i18n.helpdoc.statusText')				//'————状态文本域————',
									,margin:'5 10 0 0'
								}, {
									text : i18n('i18n.util.btn.submit')					//'提交',
									,margin:'5 10 0 0'
									,handler : submitAddData
								}, {
									text : i18n('i18n.util.btn.close')					//'关闭',
									,handler : function(){
										win.close();
									}
									,margin:'5 10 0 0'
								}]
								, listeners:{
									'close':function(){
										Ext.getCmp("BBar").moveFirst();
										Ext.getCmp('btnEdit').setDisabled(false);
										Ext.getCmp('btnDele').setDisabled(false);
										Ext.getCmp('btnSerh').setDisabled(false);
									}
								}
							});
							win.show();
							var editor	= KindEditor.create('#editorAddWrp',jO.options),
								labelDom= Ext.getCmp('opeResult').el.dom;
							Ext.getCmp('btnEdit').setDisabled(true);
							Ext.getCmp('btnDele').setDisabled(true);
							Ext.getCmp('btnSerh').setDisabled(true);
						}
					}
				},{
					xtype:'button',
					id:'btnEdit',
					text:i18n('i18n.util.btn.update')		//'修改',
					,listeners:{
						'click':function(){
							var r,ctn;
							var selected = Ext.getCmp('gridId').getSelectionModel().getSelection();
							if(selected&&selected.length==1){
								r = selected[0].data;
								ctn = r.helpContent;
							}else{
								MessageUtil.showMessage(i18n('i18n.util.msg.data_no_select_one'));//'请在表格中选定一条数据');
								return;
							};
							Ext.getCmp('btnAdd').setDisabled('true');
							Ext.getCmp('btnEdit').setDisabled(true);
							Ext.getCmp('btnDele').setDisabled(true);
							Ext.getCmp('btnSerh').setDisabled(true);
							var submitEditData = function() {
								if(!Ext.getCmp('TopFormPanel3Id').getForm().isValid()){
									MessageUtil.showMessage(i18n('i18n.helpdoc.inputSthPlease'));	//'请输入数据。
									return false;
								};
								var tit = Ext.getCmp('helpTitleId3').getValue(),
									bMo = Ext.getCmp('belongModuleId3').getRawValue(),
									bMe	= Ext.getCmp('belongMenuId3').getValue(),
									num	= Ext.getCmp('numberId3').getValue(),
									act	= null,
									// act	= Ext.getCmp('activeId').getValue(),
									radio1	= Ext.getCmp('radio31'),
									radio2	= Ext.getCmp('radio32');
									(radio1.getValue()?'':radio2.setValue(true));
									(radio2.getValue()?'':radio1.setValue(true));
								act = ((radio1.getValue())?true:false);
								var str	= editor2.html(),
					                jP = {
					                	helpDoc:{
					                		'id'			: nId,
					                		'helpContent'	: str,
					                		'helpTitle'		: tit,
						                	'belongModule'	: bMo,
						                	'belongMenu'	: bMe,
						                	'windowNum'		: num,
						                	'active'		: act
					                }},
					                fnS = function(){
					                	labelDom.innerHTML = "<font color='red'>"+i18n("i18n.ladingstation.save.success")+"</font>";						//保存成功!
					                	// win.close();
					                };
					                fnF = function(json){
					                	MessageUtil.showMessage(json.message);
					                };
					            if(!num||str.length==0){
									MessageUtil.showMessage(i18n('i18n.helpdoc.inputSthPlease'));	//请输入数据
									return ;
								};
				                oHelpDocManagement.fnUpdDoc(jP,fnS,fnF);
							};
							var win = Ext.create('Ext.window.Window', {
								title : i18n('i18n.helpdoc.editAHelpDoc')				//'编辑单条帮助信息',
								,width : 820,
								height : 468,
								layout : {
									type : 'vbox',
									align : 'stretch'
								},
								items : [
									Ext.create('TopFormPanel3',{
										width:'100%'
									}),{
											id : 'editorUWrp',
											xtype : 'textarea',
											padding:'10 0 0 0',
											html:'<div id="editorUpdtWrp" style="position:relative;"></div>'
									}],
								buttons : [{
									id : 'resultField',
									xtype : 'label',
									text : i18n('i18n.helpdoc.statusText')		//'这是状态文本域',
									,html : ''
									,margin:'5 10 0 0'
								}, {
									text : i18n('i18n.util.btn.submit')					//'提交',
									,handler : submitEditData
									,margin:'5 10 0 0'
								}, {
									text : i18n('i18n.util.btn.close')					//'关闭',
									,handler : function(){
										win.close();
									}
									,margin:'5 10 0 0'
								}],
								listeners:{
									'close':function(){
										Ext.getCmp('radio1').setValue(true);
										Ext.getCmp("BBar").moveFirst();
										Ext.getCmp('btnAdd').setDisabled(false);
										Ext.getCmp('btnEdit').setDisabled(false);
										Ext.getCmp('btnDele').setDisabled(false);
										Ext.getCmp('btnSerh').setDisabled(false);
									}
								}
							});
							win.show();
							/**
							 * @method：编辑器失去焦点做长度校验。 
							 */
							var editor2	= KindEditor.create('#editorUpdtWrp',jO.options),
								labelDom= Ext.getCmp('resultField').el.dom;
							editor2.html(ctn);
							var record = new TopFormPanelModel(r),
								nId = record.data.id;
							Ext.getCmp("TopFormPanel3Id").getForm().loadRecord(record);
							if(r.active){
								Ext.getCmp('radio31').setValue(true);
							}else{
								Ext.getCmp('radio32').setValue(true);
							}
						}
					}
				},{
					xtype:'button',
					text:i18n('i18n.util.btn.delete')						//'删除',
					,id:'btnDele',
					listeners:{
						'click':function(o){
							o.setDisabled(true);
							var selected = Ext.getCmp('gridId').getSelectionModel().getSelection();
							if(!selected||selected.length==0){
								MessageUtil.showMessage(i18n('i18n.util.msg.data_no_select_one'));	//请选择一条数据
								o.setDisabled(false);
								return;
							}else{
								var S 	= selected,
									aArr= [];
								for(var i in S){
									aArr.push(S[i].get('id'))
								}
							};
							var jP = {'helpIds':aArr };
							fnS = function(){
								MessageUtil.showMessage(i18n('i18n.util.msg.delete_succeed'));	// 删除成功
								Ext.getCmp("BBar").moveFirst();
								o.setDisabled(false);
							};
							fnF	= function(){
								MessageUtil.showMessage(i18n('i18n.util.msg.delete_fail'));		//'删除失败';
								o.setDisabled(false);
							};
							oHelpDocManagement.fnDelDoc(jP,fnS,fnF);
						}
					}
				}
			]
		},{
			xtype:'middlebuttonpanel'
		},{
			xtype:'rightbuttonpanel',
			items:[{
				xtype:'button',
				text:i18n('i18n.util.btn.search')					//'查询',
				,id:'btnSerh',
				// TODO:i18n
				handler: function() {
					Ext.getCmp("BBar").moveFirst();
				    console.log('点击了查询!')
	            }
			}]
		}];
	},
	createEditor:function(){
	}
	/**
	 * 根据帮助相关查询条件：
	 * 		
	 *  					查询出帮助列表
	 * searchDoc.action
	 */

});
Ext.define('BtnGridPanel',{
	extend:'SearchGridPanel',
	id:'gridId',
	store:null,
	columns:null,
	bbar:null,
	flex:1,
	selModel : Ext.create('Ext.selection.CheckboxModel'),
	// listeners : {
		// select : function(n, k, m, o) {
			// selected = this.getSelectionModel().getSelection();
		// }
	// },
	getBBar : function() {
		var j = this;
		return Ext.create("Ext.toolbar.Paging", {
			id : "BBar",
			store : j.store,
			displayMsg : i18n("i18n.helpdoc.displayMsg"),
			// 共<font color=\"green\"> {2} </font>条记录,当前页记录索引<font color=\"green\"> {0} - {1}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp
			displayInfo : true,
			items : ["-", {
				text : i18n("i18n.util.pager_prefixText")				//'每页'
				,xtype : "tbtext"
			}, Ext.create("Ext.form.ComboBox", {
				width : 50,
				value : "40",
				triggerAction : "all",
				forceSelection : true,
				editable : false,
				name : "comboItem",
				displayField : "value",
				valueField : "value",
				queryMode : "local",
				store : Ext.create("Ext.data.Store", {
					fields : ["value"],
					data : [{
						value : "40"
					}, {
						value : "100"
					}]
				}),
				listeners : {
					select : {
						scope : this,
						fn : function(m, l) {
							var k = Ext.getCmp("gridId").store.pageSize;
							var n = parseInt(m.value);
							if(k != n) {
								Ext.getCmp("gridId").store.pageSize = n;
								Ext.getCmp("BBar").moveFirst()
							}
						}
					}
				}
			}), {
				text : i18n('i18n.helpdoc.ones')			//'条'
				,xtype : "tbtext"
			}]
		})
	},
	initComponent:function(){
		var me = this;
		me.store = me.getStore();
		me.columns = me.getColumns();
		me.bbar = me.getBBar();
		this.callParent();
	},
	getColumns:function(){
		//帮助(弹窗)编号、帮助标题、帮助内容、是否显示、所属模块、所属菜单、弹窗编号、是否发布、最后修改时间
		//windowNum helpTitle	helpContent	active	belongModule	belongMenu	endDate	
		return [
			new Ext.grid.RowNumberer(),
			{
				header : i18n('i18n.helpdoc.helpTitle')				//'帮助标题',
				,sortable : false,
				dataIndex:'helpTitle'
				,flex:1
				,renderer : function(j) {
					return j
				}
			}
			, {
				header : i18n('i18n.helpdoc.activeOrNo')			//'是否显示',
				,sortable : false,
				dataIndex:'active'
				,width:60,
				renderer:function(value){
					return value?i18n('i18n.util.yes'):i18n('i18n.util.no');	//是		否
				}
			}, {
				header : i18n('i18n.helpdoc.belongModule')			//'所属模块',
				,sortable : false,
				dataIndex:'belongModule'
				,width:100
			}, {
				header : i18n('i18n.helpdoc.belongMenu')			//'所属导航
				,sortable : false,
				dataIndex:'belongMenu'
				,width:100
			}, {
				header : i18n('i18n.helpdoc.windowNum')				//'帮助编号',
				,sortable : false,
				dataIndex:'windowNum'
				,width:120
			}, {
				header : i18n('i18n.province.modifyDate')			//'最后修改时间',
				,sortable : false,
				dataIndex:'modifyDate'
				,width:100
				,renderer:function(value){
					return DpUtil.renderDate(value);
				}
			}];
	},
	getStore : function(){
		var store = oHelpDocManagement.fnSearchDocAll();
		store.on('beforeload',function(eventObj, p){
			if (!Ext.getCmp('TopFormId').getForm().isValid()) {
				MessageUtil.showMessage(i18n('i18n.helpdoc.inputSearchCdn'));		// 请完善查询条件
				return false;
			};
			var topForm = Ext.getCmp('TopFormId').getForm(),
				bMo = topForm.findField("belongModuleId").getRawValue(),
				bMe = topForm.findField("belongMenuId").getRawValue(),
				num = topForm.findField("windowNumId").getValue(),
				act = null,
				fro = topForm.findField("from_dateId").getValue(),
				end = topForm.findField("to_dateId").getValue(),
				
				radio1	= Ext.getCmp('radio1'),
				radio2	= Ext.getCmp('radio2');
			act = ((radio1.getValue())?function(){
				radio1.setValue(true);
				return true;
			}():function(){
				radio2.setValue(true);
				return false;
			}());
			if(fro>=end){
				MessageUtil.showMessage(i18n('i18n.helpdoc.inputSearchTimeErr'));// i18n.helpdoc.inputSearchTimeErr=查询开始时间大于结束时间！
				return;
			};
			if(Ext.isEmpty(bMo)&&Ext.isEmpty(fro)&&Ext.isEmpty(bMe)&&Ext.isEmpty(num)&&Ext.isEmpty(act)){
				MessageUtil.showMessage(i18n('i18n.helpdoc.inputSearchCdn'));	//请完善查询条件
				return ;
			};
			var helpDocSearchCondition = {
	        	'helpDocSearchCondition.active'			: act,
	        	'helpDocSearchCondition.belongMenu'		: bMe,
	        	'helpDocSearchCondition.belongModule'	: bMo,
	        	'helpDocSearchCondition.windowNum'		: num,
	        	'helpDocSearchCondition.startDate'		: fro,
	        	'helpDocSearchCondition.endDate'		: end
	        	// ,helpTitle	:''
	        	// ,'start'		: '',
	        	// ,'limit'		: ''
			};
			Ext.apply(p, {
				params : helpDocSearchCondition						// param为Sentra内部封装的obj
			});
			console.log('p:'+p);
			console.log(p);
		});
		Ext.getCmp('btnSerh').setDisabled(false);
		console.log('beforeload event.')
		return store;
	}
});

	Ext.define('HelpDocManagementLayout', {
		extend : 'Ext.container.Viewport',
		title : i18n('i18n.helpdoc.helpDocAdmin')					//'帮助管理后台'
		,layout : {
			type : 'vbox',
			align : 'stretch'
		},
		items : [
			Ext.create('TopFormPanel',{}),
			Ext.create('MidButnPanel',{}),
			Ext.create('BtnGridPanel',{}),
		]
	});
	new HelpDocManagementLayout();
});