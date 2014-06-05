﻿/**
 * @author rock
 * helpDocManagement:公告管理后台
 */
Ext.onReady(function() {
	Ext.form.Field.prototype.msgTarget='side';
	
	var keys = [
		'NOTICE_TYPE'
			// 'SYSTEM_EXCEPTION',									//系统异常
			// 'SYSTEM_NOTICE',										//系统公告
			// 'SYSTEM_DEPLOYMENT',									//停机部署
			// 'OPERATION_STUDY'									//操作学习
			];
	initDataDictionary(keys);						//初始化业务参数
	
	var oNoticeManagementData = function(){};
	/**
	 * @class:oNoticeManagementData 公告数据Data层
	 * @param:{jP,fnS,fnF}  
	 * 		jP 	:{}	json参数
	 * 		fnS	:Fn	ajax成功回调函数
	 * 		fnF	:Fn	ajax失败回调函数
	 * @exception
	 * @author Rock
	 * 2012-09-22
	 */
	oNoticeManagementData.prototype = {
		/**
		 * @method:新增公告
		 */
		addNotice: function(jP,fnS,fnF){
			var u = 'addNotice.action';				
			DpUtil.requestJsonAjax(u,jP,fnS,fnF);
		},
		/**
		 * @method:批量删除公告
		 */
		delNoticeA: function(jP,fnS,fnF){
			var u = 'delteNoticesByIdList.action';
			DpUtil.requestJsonAjax(u,jP,fnS,fnF);
		},
		topNotice:function(jP,fnS,fnF){
			var u = 'topNotice.action';
			DpUtil.requestJsonAjax(u,jP,fnS,fnF);
		},
		/**
		 * @method:删除单条公告
		 */
		delNoticeB: function(jP,fnS,fnF){
			var u = 'deleteNoticeById.action';
			DpUtil.requestJsonAjax(u,jP,fnS,fnF);
		},
		/**
		 * @method:修改公告/更新公告状态
		 */
		updNotice: function(jP,fnS,fnF){
			var u = 'updateNotice.action';
			DpUtil.requestJsonAjax(u,jP,fnS,fnF);
		},
		/**
		 * @method:暂不发布：批量修改显隐状态
		 */
		changeStatus: function(jP,fnS,fnF){
			var u = 'changeStatus.action';
			DpUtil.requestJsonAjax(u,jP,fnS,fnF);
		},
		// /**
		 // * @method:获取表格model 
		 // */
		// fnGetModel: function(){
			// var model = Ext.define('helpDocGridModel',{
				// extend: 'Ext.data.Model',
				// fields : [
				// {name:'id'}										// 公告id					// 
				// ,{name:'title'}									// 公告标题					// 必填
				// ,{name:'type'}									// 公告类别					// 必填
				// ,{name:'moduleBelong'}							// 所属模块					// 必填
				// ,{name:'content'}								// 公告内容					// 必填
				// ,{name:'active'}								// 是否发布					// 必填	BOOLEN
				// ,{name:'top'}									// 是否置顶					// 必填	BOOLEN
				// ,{name:'modifyDate'}							// 更新时间
				// ,{name:'visits'}								// 访问次数
				// ,{name:'createDate'}							// 创建时间
				// ,{name:'modifyUser'}							// 更新人
				// ,{name:'createUser'}							// 创建人
					// ]									// 最后修改时间
			// });
			// return model;
		// },
		/**
		 * @method:查询公告文档-1: 初始查询出所有的公告文档	后更改为获取表格的store，发送数据使用 beforeload
		 */
		searchNoticeA:function(){			
			Ext.define('searchDocAllStore',{
				extend:'Ext.data.Store',
				model:'noticeDocModel',
				autoLoad:true,
				pageSize:40,
				proxy:{
					type:'ajax',
					url:'searchNotices.action',
					actionMethods:'POST',
					reader:{
						type:'json',
						root:'noticeList'
						,totalProperty : 'totalCount'
					}
				}
			});
			return new searchDocAllStore();
		},
		/**
		 * @method:查询公告文档-2: 根据查询表单的查询条件查询
		 * @param:jP:oCondition
		 * @return: list
		 */
		searchNoticeB:function(jP,fnS,fnF){
			var u = 'searchNoticeB.action';
			DpUtil.requestJsonAjax(u,jP,fnS,fnF);
		},
		/**
		 * @method:查询公告文档-3: 根据公告id查询
		 * @param:jP:id
		 * @return: list
		 */
		searchNoticeC:function(jP,fnS,fnF){
			var u = 'searchNoticeC.action';
			DpUtil.requestJsonAjax(u,jP,fnS,fnF);
		},
		/**
		 * @method:获取所属模块combox的store		// 后修改为
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
		}
	};

	/**
	 * @class: 公告文档的model
	 */
	Ext.define('noticeDocModel',{
		extend: 'Ext.data.Model',
		fields : [
			{name:'id'}										// 公告id					// 
			,{name:'title'}									// 公告标题					// 必填
			,{name:'type'}									// 公告类别					// 必填
			,{name:'moduleBelong'}							// 所属模块					// 必填
			,{name:'content'}								// 公告内容					// 必填
			,{name:'active'}								// 是否发布					// 必填	BOOLEN
			,{name:'top'}									// 是否置顶					// 必填	BOOLEN
			,{name:'modifyDate'}							// 更新时间
			,{name:'visits'}								// 访问次数
			,{name:'createDate'}							// 创建时间
			,{name:'modifyUser'}							// 更新人
			,{name:'createUser'}							// 创建人
			],
	    changeName: function() {			// 事件接口
	        var oldName = this.get('windowNum'),
	            newName = oldName + "add sth";
	        this.set('windowNum', newName);
	    }
	});
	
	var oNoticeManagement	= new oNoticeManagementData()
		,selected			= null;									// 选定的records；
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

	var TFStore = Ext.create('Ext.data.Store', {
	    fields: ['display','value'],
	    data : [
	        {	"value":0,	'display':'未定'	},
	        {	"value":1,	'display':'是'	},
	        {	"value":2,	'display':'否'	}
	    ]
	});

	Ext.define('TopFormPanel',{
		extend:'SearchFormPanel',
		id:'TopFormId',
		items:null,
		height:138,
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
					fieldLabel:'更新人',
					xtype: '',
       	 			name: 'modifyUser',
       	 			id:'modifyUserId'
				}
				,{
					fieldLabel:'创建人',
					xtype: '',
       	 			name: 'createUser',
       	 			id:'createUserId'
				},Ext.create("Ext.form.ComboBox", {
					name : "type",
					id:'typeId',
					fieldLabel:'公告类别'									//'公告类别',
					,triggerAction : "all",
					forceSelection : true,
					editable : true,
					displayField : "codeDesc",
					valueField : "code",
					// queryMode : "remote",
					listConfig: {
	  					loadMask:false
					},
					listeners:{
						change:function(combo){
							if(Ext.isEmpty(combo.getValue())){
								combo.setValue("");
							}
						}
					},
					store : getDataDictionaryByName(DataDictionary,'NOTICE_TYPE')
				}),
				Ext.create("Ext.form.ComboBox", {
					name : "moduleBelong",
					id:'moduleBelongId',
					fieldLabel:i18n('i18n.helpdoc.belongModule')		//'所属模块',
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
						change:function(combo){
							if(Ext.isEmpty(combo.getValue())){
								combo.setValue("");
							}
						}
					},
					store : oNoticeManagement.fnCreatModuBeyd()
				})
				,{
			        xtype: 'datefield',
			        anchor: '100%',
			        fieldLabel: i18n('i18n.helpdoc.from_date')			//'修改时间从',
			        ,name: 'from_date',
			        id:'from_dateId',
			        maxValue: new Date()
			    }, {
			        xtype: 'datefield',
			        anchor: '100%',
			        fieldLabel: '到'	//					//'到',
			        ,name: 'to_date',
			        id:'to_dateId',
			        value: new Date()
			    },{
					fieldLabel: '公告标题'//i18n('i18n.helpdoc.windowNum')	//'公告编号',
					,xtype: '',
       	 			name: 'title',
       	 			id:'titleId'
       	 			,width:750
       	 			,colspan : 3
				}
				,Ext.create("Ext.form.ComboBox", {
					name : "active",
					id:'activeId',
					fieldLabel: i18n('i18n.helpdoc.activeOrNo')				//是否发布
					,triggerAction : "all",
					forceSelection : true,
					editable : true,
					displayField : "display",
					valueField : "value",
					// queryMode : "remote",
					listConfig: {
	  					loadMask:false
					},
					store : TFStore
				})
		        ,Ext.create("Ext.form.ComboBox", {
					name : "top",
					id:'topId',
					fieldLabel: '是否置顶'									//是否置顶
					,triggerAction : "all",
					forceSelection : true,
					editable : true,
					displayField : "display",
					valueField : "value",
					// queryMode : "remote",
					listConfig: {
	  					loadMask:false
					},
					store : TFStore
					,value:'jwiofewijfi'
				})
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
						alert('fuck');
				    	if(event.getKey() == Ext.EventObject.ENTER){
				    			alert('0')
				    		if(!Ext.isEmpty(field.next())){
				    			alert('1')
				    			field.next().focus();
				    		}else{
				    			alert('2')
				    			//me.parentWin.searchButtonPanel.searchEvent();
				    		}
				    	}
				    }
				}
			};
		}
	});
	Ext.define('TopFormPanel2',{				// 新增公告顶部form
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
				Ext.create("Ext.form.ComboBox", {							//TODO:公告类别获取数据字典,并set每个值
					name : "type2",
					id:'typeId2',
					fieldLabel:'公告类别'//i18n('i18n.helpdoc.belongModule')//'所属模块',
					,triggerAction : "all",
					forceSelection : true,
					editable : true,
					displayField : "codeDesc",
					valueField : "code",
					// queryMode : "remote",
					listConfig: {
	  					loadMask:false
					},
					store : getDataDictionaryByName(DataDictionary,'NOTICE_TYPE')
					,allowBlank:false
				}),
				Ext.create("Ext.form.ComboBox", {
					name : "moduleBelong2",
					id:'moduleBelongId2',
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
						change:function(combo){
							if(Ext.isEmpty(combo.getValue())){
								combo.setValue("");
							}
						}
					},
					store : oNoticeManagement.fnCreatModuBeyd(),
					allowBlank:false
				})
				,{
					fieldLabel:i18n('i18n.helpdoc.activeOrNo')						//'是否发布',
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
		                    boxLabel  : i18n('i18n.util.yes')						//'是',
		                    ,name      : 'active2',
		                    inputValue: 't',
		                    checked		:'checked',
		                    id        : 'radio12',
		                    margin:'0 0 0 12'
		                }, {
		                    boxLabel  : i18n('i18n.util.no')						//'否',
		                    ,name      : 'active2',
		                    inputValue: 'f',
		                    id        : 'radio22'
		                }
		            ]
		        }
				,{
					fieldLabel:'是否置顶'
		            ,xtype : 'fieldcontainer',
		            id:'topId2',
		            defaultType: 'radiofield',
		            defaults:{
						labelWidth:0,
						width:50
					},
		            layout: 'hbox',
		            items: [
		                {
		                    boxLabel  : i18n('i18n.util.yes')						//'是',
		                    ,name      : 'top2',
		                    inputValue: 't',
		                    checked		:'checked',
		                    id        : 'radio32',
		                    margin:'0 0 0 12'
		                }, {
		                    boxLabel  : i18n('i18n.util.no')						//'否',
		                    ,name      : 'top2',
		                    inputValue: 'f',
		                    id        : 'radio42'
		                }
		            ]
		        },{
					fieldLabel: '公告标题'//i18n('i18n.helpdoc.windowNum')	//'公告编号',
					,xtype: '',
       	 			name: 'title2',
       	 			id:'titleId2',
       	 			allowBlank:false
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
					fieldLabel: '公告标题'//i18n('i18n.helpdoc.windowNum')	//'公告编号',
					,xtype: '',
       	 			name: 'title',
       	 			id:'titleId3'
				}
				,{
					fieldLabel: '公告类别'//i18n('i18n.helpdoc.windowNum')	//'公告编号',
					,xtype: '',
       	 			name: 'type',
       	 			id:'typeId3'
       	 			,readOnly : true
				},
				// Ext.create("Ext.form.ComboBox", {							//TODO:公告类别获取数据字典,并set每个值
					// name : "type",
					// id:'typeId3',
					// fieldLabel:'公告类别'//i18n('i18n.helpdoc.belongModule')//'所属模块',
					// ,triggerAction : "all",
					// forceSelection : true,
					// editable : true,
					// displayField : "codeDesc",
					// valueField : "code",
					// // queryMode : "remote",
					// listConfig: {
	  					// loadMask:false
					// },
					// store : getDataDictionaryByName(DataDictionary,'NOTICE_TYPE')
				// }),
				{
					fieldLabel: '所属模块'//i18n('i18n.helpdoc.windowNum')	//'公告编号',
					,xtype: '',
       	 			name: 'moduleBelong',
       	 			id:'moduleBelongId3'
       	 			,readOnly : true
				}
				// ,Ext.create("Ext.form.ComboBox", {
					// name : "moduleBelong",
					// id:'moduleBelongId3',
					// fieldLabel:i18n('i18n.helpdoc.belongModule')//'所属模块',
					// ,triggerAction : "all",
					// forceSelection : true,
					// editable : true,
					// displayField : "functionName",
					// valueField : "functionCode",
					// // queryMode : "remote",
					// listConfig: {
	  					// loadMask:false
					// },
					// listeners:{
						// change:function(combo){
							// if(Ext.isEmpty(combo.getValue())){
								// combo.setValue("");
							// }
						// }
					// },
					// store : oNoticeManagement.fnCreatModuBeyd(),
				// })
				,{
					fieldLabel:i18n('i18n.helpdoc.activeOrNo')						//'是否发布',
		            ,xtype : 'fieldcontainer',
		            id:'activeId3',
		            defaultType: 'radiofield',
		            defaults:{
						labelWidth:0,
						width:50
					},
		            layout: 'hbox',
		            items: [
		                {
		                    boxLabel  : i18n('i18n.util.yes')						//'是',
		                    ,name      : 'active3',
		                    inputValue: 't',
		                    checked		:'checked',
		                    id        : 'radio13',
		                    margin:'0 0 0 12'
		                }, {
		                    boxLabel  : i18n('i18n.util.no')						//'否',
		                    ,name      : 'active3',
		                    inputValue: 'f',
		                    id        : 'radio23'
		                }
		            ]
		        }
				,{
					fieldLabel:'是否置顶'
		            ,xtype : 'fieldcontainer',
		            id:'topId3',
		            defaultType: 'radiofield',
		            defaults:{
						labelWidth:0,
						width:50
					},
		            layout: 'hbox',
		            items: [
		                {
		                    boxLabel  : i18n('i18n.util.yes')						//'是',
		                    ,name      : 'top3',
		                    inputValue: 't',
		                    checked		:'checked',
		                    id        : 'radio33',
		                    margin:'0 0 0 12'
		                }, {
		                    boxLabel  : i18n('i18n.util.no')						//'否',
		                    ,name      : 'top3',
		                    inputValue: 'f',
		                    id        : 'radio43'
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
				}
			};
		}
	});
	Ext.define('TopFormPanel4',{
		extend:'SearchFormPanel',
		id:'TopFormPanel4Id',
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
					fieldLabel: '公告标题'//i18n('i18n.helpdoc.windowNum')	//'公告编号',
					,xtype: '',
       	 			name: 'title',
       	 			id:'titleId4'
				}
				,{
					fieldLabel: '公告类别'//i18n('i18n.helpdoc.windowNum')	//'公告编号',
					,xtype: '',
       	 			name: 'type',
       	 			id:'typeId4'
       	 			,readOnly : true
				},
				// Ext.create("Ext.form.ComboBox", {							//TODO:公告类别获取数据字典,并set每个值
					// name : "type",
					// id:'typeId3',
					// fieldLabel:'公告类别'//i18n('i18n.helpdoc.belongModule')//'所属模块',
					// ,triggerAction : "all",
					// forceSelection : true,
					// editable : true,
					// displayField : "codeDesc",
					// valueField : "code",
					// // queryMode : "remote",
					// listConfig: {
	  					// loadMask:false
					// },
					// store : getDataDictionaryByName(DataDictionary,'NOTICE_TYPE')
				// }),
				{
					fieldLabel: '所属模块'//i18n('i18n.helpdoc.windowNum')	//'公告编号',
					,xtype: '',
       	 			name: 'moduleBelong',
       	 			id:'moduleBelongId4'
       	 			,readOnly : true
				}
				// ,Ext.create("Ext.form.ComboBox", {
					// name : "moduleBelong",
					// id:'moduleBelongId3',
					// fieldLabel:i18n('i18n.helpdoc.belongModule')//'所属模块',
					// ,triggerAction : "all",
					// forceSelection : true,
					// editable : true,
					// displayField : "functionName",
					// valueField : "functionCode",
					// // queryMode : "remote",
					// listConfig: {
	  					// loadMask:false
					// },
					// listeners:{
						// change:function(combo){
							// if(Ext.isEmpty(combo.getValue())){
								// combo.setValue("");
							// }
						// }
					// },
					// store : oNoticeManagement.fnCreatModuBeyd(),
				// })
				,{
					fieldLabel:i18n('i18n.helpdoc.activeOrNo')						//'是否发布',
		            ,xtype : 'fieldcontainer',
		            id:'activeId4',
		            defaultType: 'radiofield',
		            defaults:{
						labelWidth:0,
						width:50
					},
		            layout: 'hbox',
		            items: [
		                {
		                    boxLabel  : i18n('i18n.util.yes')						//'是',
		                    ,name      : 'active',
		                    inputValue: 't',
		                    checked		:'checked',
		                    id        : 'radio14',
		                    margin:'0 0 0 12'
		                }, {
		                    boxLabel  : i18n('i18n.util.no')						//'否',
		                    ,name      : 'active',
		                    inputValue: 'f',
		                    id        : 'radio24'
		                }
		            ]
		        }
				,{
					fieldLabel:'是否置顶'
		            ,xtype : 'fieldcontainer',
		            id:'topId4',
		            defaultType: 'radiofield',
		            defaults:{
						labelWidth:0,
						width:50
					},
		            layout: 'hbox',
		            items: [
		                {
		                    boxLabel  : i18n('i18n.util.yes')						//'是',
		                    ,name      : 'top',
		                    inputValue: 't',
		                    checked		:'checked',
		                    id        : 'radio34',
		                    margin:'0 0 0 12'
		                }, {
		                    boxLabel  : i18n('i18n.util.no')						//'否',
		                    ,name      : 'top',
		                    inputValue: 'f',
		                    id        : 'radio44'
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
				}
			};
		}
	});
	var nId;
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
								//titleId2     typeId2     moduleBelongId2     activeId2     topId2     
								var tit = Ext.getCmp('titleId2').getValue(),
									typ = Ext.getCmp('typeId2').getValue(),
									bMo = Ext.getCmp('moduleBelongId2').getRawValue(),
									act	= null,
									top	= null,
									// act	= Ext.getCmp('activeId').getValue(),			radio12	radio22
									radio1	= Ext.getCmp('radio12'),
									radio2	= Ext.getCmp('radio22'),
									radio3	= Ext.getCmp('radio32'),
									radio4	= Ext.getCmp('radio42');
								act = ((radio1.getValue())?true:false);
								top = ((radio3.getValue())?true:false);
								var str = editor.html(),
					                jP = {
					                	notice:{										// TODO:notice
					                		'title'			: tit,
					                		'type'			: typ,
						                	'moduleBelong'	: bMo,
						                	'active'		: act,
						                	'top'			: top,
						                	'content'		: str
					                }},
					                fnS = function(){
					                	labelDom.innerHTML = "<font color='red'>"+i18n("i18n.ladingstation.save.success")+"</font>";						//保存成功!
					                };
					                fnF = function(json){
					                	MessageUtil.showMessage(json.message);
					                };
					            if(str.length==0){
					            	alert('fuck')
					            	MessageUtil.showMessage(i18n('i18n.helpdoc.inputSthPlease'));	//请输入数据
									// labelDom.innerHTML = "<font color='red'>木有任何数据</font>";
									return ;
								};
				                oNoticeManagement.addNotice(jP,fnS,fnF);
							};
							var win = Ext.create('Ext.window.Window', {
								title : i18n('i18n.helpdoc.addAHelpDoc')				//'新增一条公告数据',
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
										Ext.getCmp('btnAdd').setDisabled(false);
										Ext.getCmp('btnEdit').setDisabled(false);
										Ext.getCmp('btnChangeStatus').setDisabled(false);
										Ext.getCmp('btnDele').setDisabled(false);
										Ext.getCmp('btnSerh').setDisabled(false);
									}
								}
							});
							win.show();
							var editor	= KindEditor.create('#editorAddWrp',jO.options),
								labelDom= Ext.getCmp('opeResult').el.dom;
							Ext.getCmp('btnAdd').setDisabled(true);
							Ext.getCmp('btnEdit').setDisabled(true);
							Ext.getCmp('btnChangeStatus').setDisabled(true);
							Ext.getCmp('btnDele').setDisabled(true);
							Ext.getCmp('btnSerh').setDisabled(true);
						}
					}
				},{
					xtype:'button',
					id:'btnChangeStatus',
					text:'暂不发布'//i18n('i18n.util.btn.update')		TODO:暂不发布
					,listeners:{
						'click':function(o){
							o.setDisabled(true);
							var selected = Ext.getCmp('gridId').getSelectionModel().getSelection();
							if(!selected||selected.length==0){
								MessageUtil.showMessage('请选择数据');							//请选择数据
								o.setDisabled(false);
								return;
							}else{
								var S 	= selected,
									aArr= [];
								for(var i in S){
									aArr.push(S[i].get('id'))
								}
							};
							var jP = {'ids':aArr };
							fnS = function(){
								MessageUtil.showMessage('修改成功');
								Ext.getCmp("BBar").moveFirst();
								o.setDisabled(false);
							};
							fnF	= function(){
								MessageUtil.showMessage('修改失败');	
								o.setDisabled(false);
							};
							oNoticeManagement.changeStatus(jP,fnS,fnF);
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
								ctn = r.content;
							}else{
								MessageUtil.showMessage(i18n('i18n.util.msg.data_no_select_one'));//'请在表格中选定一条数据');
								return;
							};
							Ext.getCmp('btnAdd').setDisabled('true');
							Ext.getCmp('btnEdit').setDisabled(true);
							Ext.getCmp('btnChangeStatus').setDisabled(true);
							Ext.getCmp('btnDele').setDisabled(true);
							Ext.getCmp('btnSerh').setDisabled(true);
							var submitEditData = function() {
								if(!Ext.getCmp('TopFormPanel3Id').getForm().isValid()){
									MessageUtil.showMessage(i18n('i18n.helpdoc.inputSthPlease'));	//'请输入数据。
									return false;
								};
								var tit = Ext.getCmp('titleId3').getValue(),
									typ = Ext.getCmp('typeId3').getValue(),
									bMo	= Ext.getCmp('moduleBelongId3').getValue(),
									act	= null,
									top	= null,
									// act	= Ext.getCmp('activeId').getValue(),
									radio1	= Ext.getCmp('radio13'),
									radio2	= Ext.getCmp('radio23'),
									radio3	= Ext.getCmp('radio33'),
									radio4	= Ext.getCmp('radio43');
									(radio1.getValue()?'':radio2.setValue(true));
									(radio2.getValue()?'':radio1.setValue(true));
								act = ((radio1.getValue())?true:false);
								top = ((radio3.getValue())?true:false);
																
								var str	= editor2.html(),
					                jP = {
					                	notice:{
					                		'id'			: nId,
					                		'title'			: tit,
					                		'type'			: typ,
						                	'moduleBelong'	: bMo,
						                	'active'		: act,
						                	'top'			: top,
						                	'content'		: str
					                }},
					                fnS = function(){
					                	labelDom.innerHTML = "<font color='red'>"+i18n("i18n.ladingstation.save.success")+"</font>";						//保存成功!
					                	// win.close();
					                };
					                fnF = function(json){
					                	MessageUtil.showMessage(json.message);
					                };
					            if(str.length==0){
									MessageUtil.showMessage(i18n('i18n.helpdoc.inputSthPlease'));	//请输入数据
									return ;
								};
				                oNoticeManagement.updNotice(jP,fnS,fnF);
							};
							var win = Ext.create('Ext.window.Window', {
								title : i18n('i18n.helpdoc.editAHelpDoc')				//'编辑单条公告信息',
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
										Ext.getCmp("BBar").moveFirst();
										Ext.getCmp('btnAdd').setDisabled(false);
										Ext.getCmp('btnChangeStatus').setDisabled(false);
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
							var record = new noticeDocModel(r);
								nId = record.data.id;
							record.data.type = DpUtil.changeDictionaryCodeToDescrip(record.data.type,DataDictionary.NOTICE_TYPE);
							Ext.getCmp("TopFormPanel3Id").getForm().loadRecord(record);
							
							if(record.data.active){
								Ext.getCmp('radio13').setValue(true);
							}else{
								Ext.getCmp('radio23').setValue(true);
							}
							
							if(record.data.top){
								Ext.getCmp('radio33').setValue(true);
							}else{
								Ext.getCmp('radio43').setValue(true);
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
							var jP = {'ids':aArr };
							fnS = function(){
								MessageUtil.showMessage(i18n('i18n.util.msg.delete_succeed'));	// 删除成功
								Ext.getCmp("BBar").moveFirst();
								o.setDisabled(false);
							};
							fnF	= function(){
								MessageUtil.showMessage(i18n('i18n.util.msg.delete_fail'));		//'删除失败';
								o.setDisabled(false);
							};
							oNoticeManagement.delNoticeA(jP,fnS,fnF);
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
	 * 根据公告相关查询条件：
	 * 		
	 *  					查询出公告列表
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
		
// {name:'id'}										// 公告id					// 
// ,{name:'title'}									// 公告标题					// 必填
// ,{name:'type'}									// 公告类别					// 必填
// ,{name:'moduleBelong'}							// 所属模块					// 必填
// ,{name:'content'}								// 公告内容					// 必填
// ,{name:'active'}								// 是否发布					// 必填	BOOLEN
// ,{name:'top'}									// 是否置顶					// 必填	BOOLEN
// ,{name:'modifyDate'}							// 更新时间
// ,{name:'visits'}								// 访问次数
// ,{name:'createDate'}							// 创建时间
// ,{name:'modifyUser'}							// 更新人
// ,{name:'createUser'}							// 创建人

		return [
			new Ext.grid.RowNumberer(),
			{
				header : '公告标题'// i18n('i18n.helpdoc.helpTitle')	//'标题',
				,sortable : false,
				dataIndex:'title'
				,flex:1
			}
			, {
				header : i18n('i18n.helpdoc.belongModule')			//'所属模块',
				,sortable : false,
				dataIndex:'moduleBelong'
				,width:100
			}, {
				header : '公告类别'									//'公告类别',
				,sortable : false,
				dataIndex:'type'
				,width:100
				,renderer:function(value){
					return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.NOTICE_TYPE);
	  	        }
			},{
				header : i18n('i18n.helpdoc.activeOrNo')			//'是否发布',
				,sortable : false,
				dataIndex:'active'
				,width:60,
				renderer:function(value){
					return value?i18n('i18n.util.yes'):i18n('i18n.util.no');	//是		否
				}
			}, {
				header : '创建人'
				,sortable : false,
				dataIndex:'createUser'
				,width:60
			}, {
				header : '创建时间'									//'创建时间',
				,sortable : false,
				dataIndex:'createDate'
				,width:90
				,renderer:function(value){
					return DpUtil.renderDate(value);
				}
			}, {
				header : '更新时间'									//'更新时间',
				,sortable : false,
				dataIndex:'modifyDate'
				,width:90
				,renderer:function(value){
					return DpUtil.renderDate(value);
				}
			},{
                xtype: 'actioncolumn',
                title : '操作',
                width: 90,
                defaults: {
				    autoScroll: true
				},
                items: [{	// 修改TODO:编辑分离
	                icon: 'images/modify.png',  // Use a URL in the icon config
	                tooltip: 'Edit',
	                handler: function(grid, rowIndex, colIndex) {
                        // var rec = this.parent.getAt(rowIndex);
                        // alert("Sell " + rec.get('company'));


						var r	= grid.panel.store.getAt(rowIndex),
							ctn = r.data.content;
							
						// if(selected&&selected.length==1){
							// r = selected[0].data;
							// ctn = r.content;
						// }else{
							// MessageUtil.showMessage(i18n('i18n.util.msg.data_no_select_one'));//'请在表格中选定一条数据');
							// return;
						// };
						

						
						var submitEditData = function() {
							if(!Ext.getCmp('TopFormPanel4Id').getForm().isValid()){
								MessageUtil.showMessage(i18n('i18n.helpdoc.inputSthPlease'));	//'请输入数据。
								return false;
							};
							var tit = Ext.getCmp('titleId4').getValue(),
								typ = Ext.getCmp('typeId4').getValue(),
								bMo	= Ext.getCmp('moduleBelongId4').getValue(),
								act	= null,
								top	= null,
								// act	= Ext.getCmp('activeId').getValue(),
								radio1	= Ext.getCmp('radio14'),
								radio2	= Ext.getCmp('radio24'),
								radio3	= Ext.getCmp('radio34'),
								radio4	= Ext.getCmp('radio44');
								(radio1.getValue()?'':radio2.setValue(true));
								(radio2.getValue()?'':radio1.setValue(true));
								
								(radio3.getValue()?'':radio4.setValue(true));
								(radio4.getValue()?'':radio3.setValue(true));
								
							act = ((radio1.getValue())?true:false);
							top = ((radio3.getValue())?true:false);
															
							var str	= editor2.html(),
				                jP = {
				                	notice:{
				                		'id'			: nId,
				                		'title'			: tit,
				                		'type'			: typ,
					                	'moduleBelong'	: bMo,
					                	'active'		: act,
					                	'top'			: top,
					                	'content'		: str
				                }},
				                fnS = function(){
				                	labelDom.innerHTML = "<font color='red'>"+i18n("i18n.ladingstation.save.success")+"</font>";						//保存成功!
				                	// win.close();
				                };
				                fnF = function(json){
				                	MessageUtil.showMessage(json.message);
				                };
				            if(str.length==0){
								MessageUtil.showMessage(i18n('i18n.helpdoc.inputSthPlease'));	//请输入数据
								return ;
							};
			                oNoticeManagement.updNotice(jP,fnS,fnF);
						};
						var win = Ext.create('Ext.window.Window', {
							title : i18n('i18n.helpdoc.editAHelpDoc')				//'编辑单条公告信息',
							,width : 820,
							
							//zm14日修改
							modal:true,
							
							height : 468,
							layout : {
								type : 'vbox',
								align : 'stretch'
							},
							items : [
								Ext.create('TopFormPanel4',{
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
									Ext.getCmp("BBar").moveFirst();
									//zm14日修改
									//Ext.getCmp('radio1').setValue(true);
//									Ext.getCmp('btnAdd').setDisabled(false);
//									Ext.getCmp('btnChangeStatus').setDisabled(false);
//									Ext.getCmp('btnEdit').setDisabled(false);
//									Ext.getCmp('btnDele').setDisabled(false);
//									Ext.getCmp('btnSerh').setDisabled(false);
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
						var nId = r.data.id;
						r.data.type=DpUtil.changeDictionaryCodeToDescrip(r.data.type,DataDictionary.NOTICE_TYPE);
						Ext.getCmp("TopFormPanel4Id").getForm().loadRecord(r);
						
						if(r.data.active){
							Ext.getCmp('radio14').setValue(true);
						}else{
							Ext.getCmp('radio24').setValue(true);
						}
						if(r.data.top){
							Ext.getCmp('radio34').setValue(true);
						}else{
							Ext.getCmp('radio44').setValue(true);
						}
					}
	            },{		// 置顶公告		
	                icon: 'images/top.png',
	                tooltip: 'top',
	                handler: function(grid, rowIndex, colIndex) {
	                    var rec = grid.getStore().getAt(rowIndex),
	                    	id	= rec.get('id'),
//	                    	jP = {
//	                    		notice:{
//			                		'id'			: nId,
//				                	'top'			: true
//							}},
	                    	jP = {
	                    		"noticeId":id
	                    	},
		                fnS = function(){
	                    	MessageUtil.showMessage('置顶成功');
	                    	Ext.getCmp("BBar").moveFirst();
		                };
		                fnF = function(json){
		                	MessageUtil.showMessage(json.message);
		                };
		                oNoticeManagement.topNotice(jP,fnS,fnF);
	                }
	            },{		// 不发布		oNoticeManagement.changeStatus
	                icon: 'images/noActive.png',
	                tooltip: 'changeStatus',
	                handler: function(grid, rowIndex, colIndex) {
	                	var rec = grid.getStore().getAt(rowIndex),
	                		id 	= rec.get('id'),
	                		aArr = new Array();
	          
	                	//修改过
	                	aArr[0] = id;
                		var jP = {'ids':aArr };
	                	//var jP={'noticeId':aArr};
                		fnS = function(o){
							MessageUtil.showMessage('修改成功');
							Ext.getCmp("BBar").moveFirst();
							//o.setDisabled(false);
						};
						fnF	= function(o){
							MessageUtil.showMessage('修改失败');		//'删除失败';
							//o.setDisabled(false);
						};
	                	oNoticeManagement.changeStatus(jP,fnS,fnF);
	                }
	            },{		// 删除
	                icon: 'images/delete.png',
	                tooltip: 'Delete',
	                handler: function(grid, rowIndex, colIndex) {
	                	var rec = grid.getStore().getAt(rowIndex),
	                		id 	= rec.get('id'),
	                		aArr = new Array();
	                	
	                	aArr[0] = id;
	                	
                		var jP = {'ids':aArr };
						fnS = function(){
							MessageUtil.showMessage(i18n('i18n.util.msg.delete_succeed'));	// 删除成功
							Ext.getCmp("BBar").moveFirst();
							//o.setDisabled(false);
						};
						fnF	= function(){
							MessageUtil.showMessage(i18n('i18n.util.msg.delete_fail'));		//'删除失败';
							//o.setDisabled(false);
						};
						oNoticeManagement.delNoticeA(jP,fnS,fnF);
	                }
	            }]
			}];
	},
	getStore : function(){
		var store = oNoticeManagement.searchNoticeA();
		store.on('beforeload',function(eventObj, p){
			if (!Ext.getCmp('TopFormId').getForm().isValid()) {
				MessageUtil.showMessage(i18n('i18n.helpdoc.inputSearchCdn'));		// 请完善查询条件
				return false;
			};
			
			var topForm = Ext.getCmp('TopFormId').getForm(),
				activeField = topForm.findField("activeId").getValue(),
				topField 	= topForm.findField("topId").getValue(),

				crt = topForm.findField("createUserId").getValue(),
				tit = topForm.findField("titleId").getValue(),
				typ = topForm.findField("typeId").getValue(),
				fro = topForm.findField("from_dateId").getValue(),
				end = topForm.findField("to_dateId").getValue(),
				mod = topForm.findField("moduleBelongId").getValue(),
				act ,
				top ;
			act = function(){
				if(activeField===1){
					return act = 'true';
				}else if(activeField===2){
					return act ='false';
				}else{
					return act = 'null';
				};
			}();
			top = function(){
				if(topField===1){
					return top ='true';
				}else if(topField===2){
					return top ='false';
				}else{
					return top ='null'
				};
			}();
			console.log('1382:'+act+'+'+top)
			if(fro>=end){
				MessageUtil.showMessage(i18n('i18n.helpdoc.inputSearchTimeErr'));// i18n.helpdoc.inputSearchTimeErr=查询开始时间大于结束时间！
				return;
			};
			if(Ext.isEmpty(tit)&&Ext.isEmpty(typ)&&Ext.isEmpty(fro)&&Ext.isEmpty(end)&&Ext.isEmpty(mod)){
				MessageUtil.showMessage(i18n('i18n.helpdoc.inputSearchCdn'));	//请完善查询条件
				return ;
			};

			var noticeSearchCondition = {
	        	'noticeSearchCondition.createUser'		: crt,
	        	'noticeSearchCondition.title'			: tit,
	        	'noticeSearchCondition.type'			: typ,
	        	'noticeSearchCondition.startTime'		: fro,
	        	'noticeSearchCondition.endTime'			: end,
	        	'noticeSearchCondition.moduleBelong'	: mod,
	        	'statusTop'								: top,
	        	'statusActive'							: act
			};
			
			Ext.apply(p, {
				params : noticeSearchCondition						// param为Sentra内部封装的obj
			});
			
			console.log('以下是传给后端的查询参数 p:'+p);
			console.log(p);
		});
		Ext.getCmp('btnSerh').setDisabled(false);
		console.log('beforeload event.')
		return store;
	}
});

	Ext.define('NoticeManagementLayout', {
		extend : 'Ext.container.Viewport',
		title : '公告管理后台'
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
	new NoticeManagementLayout();
});