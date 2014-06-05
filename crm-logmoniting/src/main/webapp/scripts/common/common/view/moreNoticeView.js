var required = "<span style='color:red'>*</span>";
		Ext.onReady(function(){			
			
			var keys = [
			    		'NOTICE_TYPE'
			    	];
			    	initDataDictionary(keys);
			
 /******************************顶部topForm********************************************/
			Ext.define('TopFormPanel',{//更多公告界面顶部的formPanel
				extend:'SearchFormPanel',
				height:90,
				layout:{
					type:'table',
					columns:3
				},
				defaultType:'textfield',
				defaults:{
					labelWidth:100,
					width:250
				},
				items:[{
					fieldLabel:i18n('i18n.notice.noticeTitle'),//公告标题
					name:'title'
				},{
					fieldLabel:i18n('i18n.notice.noticeActiveUser'),//发布人
					name:'createUser'
				},Ext.create("Ext.form.ComboBox", {
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
					// store : oNoticeManagement.fnCreatModuBeyd()
					store : mokuaiStore
				}),Ext.create("Ext.form.ComboBox", {
					name : "type",
					id:'typeId',
					fieldLabel:i18n('i18n.notice.noticeType'),//'公告类别'									//'公告类别',
					triggerAction : "all",
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
				}),{
					name:'startTime',
					xtype:'datefield',
					fieldLabel:i18n('i18n.notice.lastUpdateTime'),//'最后更新时间',
					editable:false,
					maxValue: new Date()
				},{
					name:'endTime',
					xtype:'datefield',
					fieldLabel:'-',
					labelSeparator:'',
					labelWidth:30,
					width:180,
					editable:false,
					maxValue: new Date()
				}]
			});
/***********************************************************************************/
			Ext.define('PopWinForm',{//弹窗界面的formPanel
				extend:'SearchFormPanel',
				autoScroll:true,
				layout:{
					type:'table',
					columns:5
				},
				defaultType:'displayfield',
				items:[{
					colspan:5,
					xtype:'panel',
					border:false,
                    layout: {
                        type: 'hbox',
                        pack:'center',
                        align:'middle'
                    },
                    items:[{
                        xtype:'displayfield'
                    },{
                        xtype:'displayfield',
                        name:'title',
                        cls:'tit'
                    },{
                        xtype:'displayfield'
                    }]
                },{
					colspan:5,
					xtype:'label',
					html:'<hr style="height:1px " />',
				},{
					fieldLabel:i18n('i18n.notice.noticeActiveUser'),//发布人
					labelWidth:48,
					cls:'details',
					name:'createUser'
				},{
					fieldLabel:i18n('i18n.notice.noticeType'),//'公告类别',
					name:'type',
					cls:'details',
					labelWidth:60
				},{
					name:'createDate',
					labelWidth:60,
					cls:'details',
					fieldLabel:'发布时间'//i18n('')//'最后更新时间',
				},{
					name:'modifyDate',
					labelWidth:88,
					cls:'details',
					fieldLabel:i18n('i18n.notice.lastUpdateTime')//'最后更新时间',
				},{
					name:'visits',
					labelWidth:60,
					cls:'details',
					fieldLabel:'查阅人数'
				},{
					colspan:5
				},{
					name:'content',
					//fieldLabel:'内容',
					labelWidth:0,
					cls:'content',
					colspan:1000
				}]
			});
/************************中间部分 按钮Panel****************************************/
			Ext.define('ButtonDemoPanel',{
				extend:'NormalButtonPanel', //--第一步,定义一个主panel,继承NormalButtonPanel
				items:null,
				initComponent:function(){
					this.items = this.getItems();
					this.callParent();
				},
				getItems:function(){
					var me = this;
					return [{
						xtype:'leftbuttonpanel', //--第二步,定义一个位于左边的按钮容器,继承leftbuttonpanel
						items:[{
							xtype:'button',
							name:'createNoticeBtn',
							text:i18n('i18n.notice.SeeDetails'),//'查看详情',
							handler:function(){
								var rsm = gridPanel.getSelectionModel();
//								var record;
								var count= rsm.getCount();
							if(count>0){//如果选择了一条记录，以弹窗显示详情，如果没有选择，则不执行
								
								var param = {
									'noticeId':rsm.getSelection()[0].data.id	
								};
								var successFn= function(json){
									var param = {
						                	notice:{										// TODO:notice
						                		'title'			: json.noticece.title,
						                		'type'			: DpUtil.changeDictionaryCodeToDescrip(json.noticece.type,DataDictionary.NOTICE_TYPE),
							                	'content'		: json.noticece.content,
							                	'imageAddr'  	: json.noticece.imageAddr,
												'imageDescribe' : json.noticece.imageDescribe,
												'fileInfoList'	: json.noticece.fileInfoList,
												'createDate'	: DpUtil.renderDate(json.noticece.createDate),
												'modifyDate'	: DpUtil.renderDate(json.noticece.modifyDate),
												'visits'		: json.noticece.visits,
												'createUser'	: json.noticece.createUser
						                	}
									};
									 window.showModalDialog ('../common/noticePriviewWin.action', 
											 param.notice, 'dialogWidth:1000px;dialogHeight:700px'); //这句要写成一行
									
									//更新访问次数
									var url = '../common/updateNoticeVisitsById.action';
									Ext.Ajax.request({
									    url: url,
									    params:{
									    	'noticeId':json.noticece.id
									    }
									});
									
								};
								var failureFn = function(json){
									if(Ext.isEmpty(json)){
										MessageUtil.showErrorMes("访问超时");
									}else{
										MessageUtil.showErrorMes(json.message);
									}
								};
								findNoticeById(param,successFn,failureFn);	
//										
								}	
								}
							}]
						},{
							xtype:'middlebuttonpanel' //--第四步,,定义一个位于中间的空容器，用于填充								中间空白部分,继承middlebuttonpanel
						},{
							xtype:'rightbuttonpanel', //--第五步,定义一个位于右边的按钮容器,继承								rightbuttonpanel
							items:[{
								xtype:'button',    //--第六步,向右部的按钮容器中，添加具体的按钮
								name:'serachNoticeBtn',
								text:i18n('i18n.util.btn.search'),//'查询',
								handler:function(){
									Ext.getCmp("BBar").moveFirst()
								}
							}]
						}]
			}
				});
/***********************底部gridPanel**************************************************************************************/
		
			var topFormPanel = Ext.create('TopFormPanel',{});
			gridStore.on('beforeload',function(gridStore,operation){
				var startTime = topFormPanel.getForm().findField("startTime").getValue();
				var endTime = topFormPanel.getForm().findField("endTime").getValue();

				if(endTime>=startTime){//最后更新时间的起始时间小于结束时间才能执行查询
					Ext.apply(operation,{	
						params : {
							'noticeSearchCondition.title'			:topFormPanel.getForm().findField("title").getValue(),
							'noticeSearchCondition.createUser'		:topFormPanel.getForm().findField("createUser").getValue(),
							'noticeSearchCondition.moduleBelong'	:topFormPanel.getForm().findField("moduleBelong").getRawValue(),
							'noticeSearchCondition.type'			:topFormPanel.getForm().findField("type").getValue(),
							'noticeSearchCondition.startTime'		:topFormPanel.getForm().findField("startTime").getValue(),
							'noticeSearchCondition.endTime'			:topFormPanel.getForm().findField("endTime").getValue()
						}
					});
				}else{//最后更新时间的起始时间大于结束时间择不能查询，并弹出提示框
					MessageUtil.showMessage(i18n('i18n.helpdoc.inputSearchTimeErr'));// i18n.helpdoc.inputSearchTimeErr=查询开始时间大于结束时间！
					return;
				}				
			});
			
			gridStore.load();
			var gridPanel = Ext.create('SearchGridPanel',{
				id:'gridPanel',
				autoScroll:true,
				store:	gridStore,
				selModel:	Ext.create('Ext.selection.CheckboxModel',{
					mode:'SINGLE'
				}),
				columns:[
				//序号        
				{	text:i18n('i18n.util.serial_number'),			xtype	 :'rownumberer'		,width:40	},
				
				//公告标题
				{	text:i18n('i18n.notice.noticeTitle'),			dataIndex:'title'			,width:140	},
				
				//所属模块
				{	text:i18n('i18n.helpdoc.belongModule'),			dataIndex:'moduleBelong'	,flex:1	},
				
				//公告类别
				{	
					text:i18n('i18n.notice.noticeType'),			dataIndex:'type'			,flex:1	,
					renderer:function(value){
						return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.NOTICE_TYPE);
					}
				},
				
				//发布人
				{	text:i18n('i18n.notice.noticeActiveUser'),		dataIndex:'createUser'		,flex:1	},
				
				{//发布时间	
					text:i18n('i18n.notice.activeTime'),			dataIndex:'createDate'		,flex:1,
					renderer:function(value){
						return renderDate(value);
					}
				},
				{//最后更新时间	
					text:i18n('i18n.notice.lastUpdateTime'),		dataIndex:'modifyDate'		,flex:1,
					renderer:function(value){
						return renderDate(value);
					}
				}
				],
				bbar:Ext.create("Ext.toolbar.Paging", {
						id : "BBar",
						store : gridStore,
						displayMsg : i18n("i18n.helpdoc.displayMsg"),
						displayInfo : true,
						items : ["-", {
							text : i18n("i18n.util.pager_prefixText")				//'每页'
							,xtype : "tbtext"
						}, Ext.create("Ext.form.ComboBox", {
							id:'showNum',
							width : 50,
							value:'30',
							editable:false,
							triggerAction : "all",
							forceSelection : true,
							editable : false,
							name : "comboItem",
							displayField : "value",
							valueField : "value",
							queryMode : "local",
							store :valueStore,
							listeners : {
								select : {
									scope : this,
									fn : function(m, l) {
										var k = Ext.getCmp("gridPanel").store.pageSize;
										var n = parseInt(m.value);
										if(k != n) {
											Ext.getCmp("gridPanel").store.pageSize = n;
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
			});
/**************************更多公告页面的viewPort******************************************************************************************/
			Ext.create('Ext.container.Viewport', {
				layout :'border',
				items:[{
					region:'north',
					border: false,
					items:topFormPanel
				},{
					region:'center',
					layout :'border',
					border: false,
					items:[{
						border: false,
						region:'north',
						items:Ext.create('ButtonDemoPanel',{})
					},{
						layout:'fit',
						border: false,
						region:'center',
						items:gridPanel
					}]

				}]
			});
			/**
			 * 公告类别反转
			 * zouming
			 */
			function changeDictionaryCodeToDescrip(value, dataDictionary) {
				if (value != null && dataDictionary != null) {
					for ( var i = 0; i < dataDictionary.length; i++) {
						if (value == dataDictionary[i].codeDesc) {
							return dataDictionary[i].code;
						}
					}
				} else {
					return null;
				}
			};
			/**
			 * 时间转换
			 * zouming
			 */
			function renderDate(value) {
				if(value != null){
					return Ext.Date.format(new Date(value), 'Y-m-d G:m:s ');
				}else{
					return null;
				}
			};
		});