﻿﻿/**
 * @author rock
 * helpDocManagement:公告管理后台
 */
Ext.onReady(function() {
	Ext.form.Field.prototype.msgTarget='side';
	Ext.QuickTips.init();
	var keys = [
		'NOTICE_TYPE'
	];
	initDataDictionary(keys);						//初始化业务参数
	initDeptAndUserInfo();
	var addOrModify;
	//var oNoticeManagement	= (CONFIG.get("TEST"))? new oNoticeManagementData():new oNoticeManagementDataTest(),	// data层对应对象
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
				,"image"				//图片	
				,'forecolor'			//	文字颜色
				,'hilitecolor'			//	文字背景
				,'bold'					//	粗体
				,'italic'				//	斜体
				,'underline'			//	下划线
				,'justifyleft'
				,'justifycenter'
				,'justifyright'
			]
			/**
			 * @method：编辑器失去焦点做长度校验。 
			 */
			,afterBlur :function(){
				var l = Ext.util.Format.stripTags(this.html()).length;
				//TODO 
				if(l>=4500){
					MessageUtil.showMessage(i18n('i18n.helpdoc.inputSthPerfect'));	//数据过长，请节制。
					return false;
				};
			}
			,fontSizeTable:14
	}};
	
	var imageUrl;
	var attachmentSize=0;
	/**
	 * 上传附件Form
	 */
	Ext.define('UploadAttachmentForm',{
		extend:'BasicFormPanel',
		frame:true,
		border:false,
		flex:1,
		margins : '5 0 0 0',
		layout:{
			type : 'hbox'
		},
		defaults : {
			margins : '0 5 0 0'
		},
		status:null,
		contractData:null,
		fileInfo:null,
		initComponent:function(){
			var me = this;
			me.items = me.getItems();
			me.callParent();
		},
		getItems:function(){
			var me = this;
			return [{
				xtype:'filefield',
				name:'file',
				disabled:(me.status == 'VIEW'),
				fieldLabel:'图片上传',//附件上传
				labelWidth:60,
//				emptyText:'至少添加一条附件信息',
				buttonText:'浏览',//'浏览',//i18n('i18n.ChangeContactAffiliatedRelationView.view'),
				flex:3,
				listeners:{
					//如果加入文件，则上传按钮设置为可点击，否则不可点击
					'change':function(me,value,eOpts){
						if(value!=null&&""!=value){
							Ext.getCmp('fileBtnId').setDisabled(false);
						}
					}
				}
			},{
				xtype:'button',
				id:'fileBtnId',
				disabled:(me.status == 'VIEW'),
				text:'上传',//'上传',
				scope:me,
				disabled:true,
				handler:me.updateFile
			},{
				name:'type',
				xtype:'hiddenfield',
				value:'noticeAttDir'
			},{
				name:'imageOrAttachment',
				xtype:'hiddenfield',
				value:'attachment'
			},{
				name:'attachmentSize',
				xtype:'hiddenfield'
			},{
				name:'maxSizeLimit',
				xtype:'hiddenfield'
			}];
		},
		updateFile:function(){
			var me = this;
			var form = me;
			
			if(!Ext.isEmpty(Ext.getCmp('editorImageWinId'))){
				var addr = new Array();
				addr = form.getForm().findField('file').getValue().split('.');
				
				var count = addr.length;
				//var typeArray = ['jpg','JPG','jpeg','JPEG','gif','GIF','png','PNG'];
				if(addr[count-1]!='jpg'&&addr[count-1]!='JPG'&&
						addr[count-1]!='jpeg'&&addr[count-1]!='JPEG'&&
						addr[count-1]!='gif'&&addr[count-1]!='GIF'&&
						addr[count-1]!='png'&&addr[count-1]!='PNG'&&
						addr[count-1]!='dmp'&&addr[count-1]!='DMP'){
					MessageUtil.showErrorMes('请上传正确的图片格式！');
					return;
				}
        		//动态设置
        		me.getForm().findField('imageOrAttachment').setValue('image');
        		me.getForm().findField('maxSizeLimit').setValue('20M');
			}else{
				var addr = new Array();
				addr = form.getForm().findField('file').getValue().split('.');
				var count = addr.length;
				//var typeArray = ['jpg','JPG','jpeg','JPEG','gif','GIF','png','PNG'];
				if(addr[count-1]!='jpg'&&addr[count-1]!='JPG'&&
						addr[count-1]!='jpeg'&&addr[count-1]!='JPEG'&&
						addr[count-1]!='gif'&&addr[count-1]!='GIF'&&
						addr[count-1]!='png'&&addr[count-1]!='PNG'&&
						addr[count-1]!='dmp'&&addr[count-1]!='DMP'&&
						addr[count-1]!='txt'&&addr[count-1]!='TXT'&&
						addr[count-1]!='pdf'&&addr[count-1]!='PDF'&&
						addr[count-1]!='doc'&&addr[count-1]!='DOC'&&
						addr[count-1]!='docx'&&addr[count-1]!='DOCX'&&
						addr[count-1]!='xls'&&addr[count-1]!='XLS'&&
						addr[count-1]!='xlsx'&&addr[count-1]!='XLSX'&&
						addr[count-1]!='rar'&&addr[count-1]!='RAR'){
					MessageUtil.showErrorMes('请上传正确的文件格式！');
					return;
				}
				
				me.getForm().findField('maxSizeLimit').setValue('10000M');
				me.getForm().findField('attachmentSize').setValue(attachmentSize);
			}
			form.submit({
	                    url: '../common/fileUpload.action',
	                    waitMsg: '上传中',//'文件上传中',
	                    success: function(form, response) {
	                    	//上传成功后上传按钮设置为不可点击
                    		Ext.getCmp('fileBtnId').setDisabled(true);
                    		
	                    	var result = response.result;
	                    	if(result.success){
	                    		var fileInfo = result.fileInfoList[0];
	                    		attachmentSize+=fileInfo.fileSize;
	                    		me.getForm().findField('file').setValue();
	                    		if(!Ext.isEmpty(Ext.getCmp('editorImageWinId'))){
	                    			imageUrl = "../common/downLoad.action?fileName="+fileInfo.fileName+"&inputPath="+fileInfo.savePath;
		                        	
		                    		var html01='<img src="'+imageUrl+'" width="300" height="300"/>';
		                    		var html='<img src="'+imageUrl+'"/>';
		                    		if(!Ext.isEmpty(Ext.getCmp('describId'))){
		                    			Ext.getCmp('image01Id').setValue(html01);
		                    			Ext.getCmp('imageId').setValue(html);
		                    		}else{
		                    			//动态添加元素
			                    		Ext.getCmp('editorImageWinId').add([{
			                    			xtype:'displayfield',
			                    			id:'image01Id',
//			                    			border:false,
			                    			value:html01
			                    		},{
			                    		    xtype:'textfield',
			                    		    id:'describId',
			                    		    allowBlank:false,
			                    		    blankText:'请填写图片说明',
			                    			margin:'10 0 0 0',
			                    			fieldLabel:'图片说明',
			                    			labelWidth:65,
			                    			width:470
			                    		},{
			                    			xtype:'hiddenfield',
			                    			id:'imageId',
			                    			value:html
			                    		}]);
		                    		}		               	
	                    		}else{
//	                    			me.getForm().findField('imageOrAttachment').setValue('attachment');
	                    			if(addOrModify=='add'){
	                    				var win = Ext.getCmp('addNoticeWindowId');
	                    				win.setHeight(win.getHeight()+22);
	                    				win.add([{
											xtype:'displayfield',
											value:'<a href=javascript:>'+result.fileInfoList[0].fileName+'</a>'
										}]);
		                    			fileInfoList.push(result.fileInfoList[0]);
		                    			
		                    			//当附件等于10条时，关闭上传附件窗口，上传附件按钮置灰
		                    			if(fileInfoList.length>=10){
		                    				me.up('window').close();
	                    					Ext.getCmp('addNoticeFileBtn').setDisabled(true);
	                    				}else{
	                    					Ext.getCmp('addNoticeFileBtn').setDisabled(false);
	                    				}
	                    			}
	                    			if(addOrModify=='modify'){
	                    				var win = Ext.getCmp('modifyNoticeWindowId');
	                    				win.setHeight(win.getHeight()+22);
	                    				win.add([{
											xtype:'displayfield',
											value:'<a href=javascript:>'+result.fileInfoList[0].fileName+'</a>'
										}]);
	                    				fileInfoList01.push(result.fileInfoList[0]);
	                    				
	                    				//当附件等于10条时，关闭上传附件窗口，上传附件按钮置灰
	                    				if((fileInfoList01Length+fileInfoList01.length)>=10){
	                    					me.up('window').close();
	                    					Ext.getCmp('modifyNoticeFileBtn').setDisabled(true);
	                    				}else{
	                    					Ext.getCmp('modifyNoticeFileBtn').setDisabled(false);
	                    				}
	                    			}
	                    			if(addOrModify=='edit'){
	                    				var win = Ext.getCmp('editNoticeWindowId');
	                    				win.setHeight(win.getHeight()+22);
	                    				win.add([{
											xtype:'displayfield',
											value:'<a href=javascript:>'+result.fileInfoList[0].fileName+'</a>'
										}]);
	                    				fileInfoList01.push(result.fileInfoList[0]);	             
	                    				
	                    				//当附件等于10条时，关闭上传附件窗口，上传附件按钮置灰
	                    				if((fileInfoList01Length+fileInfoList01.length)>=10){
	                    					me.up('window').close();
	                    					Ext.getCmp('editNoticeFileBtn').setDisabled(true);
	                    				}else{
	                    					Ext.getCmp('editNoticeFileBtn').setDisabled(false);
	                    				}
	                    			}
	                    		}
	                    		MessageUtil.showInfoMes('文件' +
	                        			fileInfo.fileName + 
	                        			'上传成功');
							}else{
	                       		MessageUtil.showErrorMes( result.message);
							}
	                    },
	                 failure:function(form, response){
	                	Ext.getCmp('fileBtnId').setDisabled(true);
	                 	var result = response.result;
	                 	if(!result.success){
	                       	MessageUtil.showErrorMes(result.message);
						}
	                 }
	                });
		
		}
	});
	
	var editorImageWin;
	var desAddress;
	var imageAddr,imageDescribe;
	var fileInfoList = new Array();
	var fileInfoList01 = new Array();
	var fileInfoList01Length = 0;
	
	/**
	 * 图片插件
	 */
	KindEditor.plugin('image', function(K) {
        var editor = this, name = 'image';
        // 点击图标时执行
        editor.clickToolbar(name, function() {
        		editorImageWin = Ext.create('PopWindow',{
        			id:'editorImageWinId',
            		title:'上传图片',
//            		closeAction:'hide',
            		width:500,
            		height:500,
            		items:[
            		       Ext.create('UploadAttachmentForm',{id:'imageUploadFormId'}),
            		       {
            			xtype:'displayfield',
            			value:'<span style="color:red">您好，如果上传图片，必须填写图片说明。并且一个公告只能上传一张图片，继续上传则覆盖之前的图片。</span>'
            		}],
            		buttons:[{
            			text:'确定',
            			handler:function(){
            				if(!Ext.getCmp('describId').getValue()){
            					return;
            				}
            				imageAddr = Ext.getCmp('imageId').getValue();
            				imageDescribe = Ext.getCmp('describId').getValue();
            				desAddress = Ext.getCmp('describId').getValue() + "|" + Ext.getCmp('imageId').getValue();
            	        	editorImageWin.close();
            			}
            		},{
            			text:'取消',
            			handler:function(){
            	        	editorImageWin.close();
            			}
            		}]
            	});
        		
        		Ext.getCmp('imageUploadFormId').getForm().findField('file').fieldLabel='图片上传';
        		Ext.getCmp('imageUploadFormId').getForm().findField('file').doComponentLayout();
        		
        		editorImageWin.show();
        });
	});
	

	/**
	 * 文字大小的插件
	 */
	
	//改变文字大小时候拆分数组的方法
	function _each(obj, fn) {
		for (var i = 0, len = obj.length; i < len; i++) {
			if (fn.call(obj[i], i, obj[i]) === false) {
				break;
			}
		}
	}
	//文字大小的插件
	KindEditor.plugin('fontsize', function(K) {
        var editor = this, name = 'fontsize';
        // 点击图标时执行
        editor.clickToolbar(name, function() {
        	/***/
        	var menu = editor.createMenu({
        	        name:'fontsize',
        	        width:150
        	});
        	_each(['9px', '10px', '12px', '14px', '16px', '18px', '24px', '32px'], function(i, val) {
        	        menu.addItem({
        	        	title : '<span style="font-size:' + val + ';" unselectable="on">' + val + '</span>',
        				height : val+15,
        				click : function() {
        					editor.exec('fontsize', val).hideMenu();
        				}
        	        });
        	});
        });
	});
	/****/
	var TFStore = Ext.create('Ext.data.Store', {
	    fields: ['display','value'],
	    data : [
	        {	"value":0,	'display':i18n('i18n.notice.radioUndecided')	},//'未定'
	        {	"value":1,	'display':i18n('i18n.util.yes')	},//'是'
	        {	"value":2,	'display':i18n('i18n.util.no')	} //'否'
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
					fieldLabel:i18n('i18n.notice.UpdateUser'),//更新人',
					xtype: '',
       	 			name: 'modifyUser',
       	 			id:'modifyUserId'
				}
				,{
					fieldLabel:i18n('i18n.big_small_city.createUName'),//'创建人',
					xtype: '',
       	 			name: 'createUser',
       	 			id:'createUserId'
				},Ext.create("Ext.form.ComboBox", {
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
			        fieldLabel:i18n('i18n.helpdoc.to_date'),// '到'	//					//'到',
			        name: 'to_date',
			        id:'to_dateId',
			        value: new Date()
			    },{
					fieldLabel:i18n('i18n.notice.noticeTitle'),// '公告标题'//i18n('i18n.helpdoc.windowNum')	//'公告编号',
					xtype: '',
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
					fieldLabel:i18n('i18n.notice.topOrNo'), //'是否置顶'									//是否置顶
					triggerAction : "all",
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
					keypress:function(field,event){									// form的keyCode13触发
				    	if(event.getKey() == Ext.EventObject.ENTER){
				    			alert('0')
				    		if(!Ext.isEmpty(field.next())){
				    			alert('1')
				    			field.next().focus();
				    		}else{
				    			alert('2')
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
				Ext.create("Ext.form.ComboBox", {							//公告类别获取数据字典,并set每个值
					name : "type2",
					id:'typeId2',
					fieldLabel:i18n('i18n.notice.noticeType'),//'公告类别'
					triggerAction : "all",
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
					name : "moduleBelong",
					id:'moduleBelongId2',
					fieldLabel:i18n('i18n.helpdoc.belongModule'),//'所属模块',
					triggerAction : "all",
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
					fieldLabel:i18n('i18n.helpdoc.activeOrNo'),						//'是否发布',
		            xtype : 'fieldcontainer',
		            id:'activeId2',
		            defaultType: 'radiofield',
		            defaults:{
						labelWidth:0,
						width:50
					},
		            layout: 'hbox',
		            items: [
		                {
		                    boxLabel  : i18n('i18n.util.yes'),						//'是',
		                    name      : 'active2',
		                    inputValue: 't',
		                    checked		:'checked',
		                    id        : 'radio12',
		                    margin:'0 0 0 12'
		                }, {
		                    boxLabel  : i18n('i18n.util.no'),						//'否',
		                    name      : 'active2',
		                    inputValue: 'f',
		                    id        : 'radio22'
		                }
		            ]
		        }
				,{
					fieldLabel:i18n('i18n.notice.topOrNo'),//'是否置顶'
		            xtype : 'fieldcontainer',
		            id:'topId2',
		            defaultType: 'radiofield',
		            defaults:{
						labelWidth:0,
						width:50
					},
		            layout: 'hbox',
		            items: [
		                {
		                    boxLabel  : i18n('i18n.util.yes'),						//'是',
		                    name      : 'top2',
		                    inputValue: 't',
		                    checked		:'checked',
		                    id        : 'radio32',
		                    margin:'0 0 0 12'
		                }, {
		                    boxLabel  : i18n('i18n.util.no'),						//'否',
		                    name      : 'top2',
		                    inputValue: 'f',
		                    id        : 'radio42'
		                }
		            ]
		        },{
					fieldLabel: i18n('i18n.notice.noticeTitle'),//'公告标题'//i18n('i18n.helpdoc.windowNum')	//'公告编号',
					xtype: '',
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
					fieldLabel: i18n('i18n.notice.noticeTitle'),//'公告标题'//i18n('i18n.helpdoc.windowNum')	//'公告编号',
					xtype: '',
       	 			name: 'title',
       	 			id:'titleId3'
				}
				,Ext.create("Ext.form.ComboBox",{
					fieldLabel: i18n('i18n.notice.noticeType'),//'公告类别'//i18n('i18n.helpdoc.windowNum')	//'公告编号',
					xtype: '',
       	 			name: 'type',
       	 			id:'typeId3',
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
				}),
				Ext.create("Ext.form.ComboBox", {
					name : "moduleBelong",
					id:'moduleBelongId3',
					fieldLabel:i18n('i18n.helpdoc.belongModule'),//'所属模块',
					triggerAction : "all",
					// forceSelection : true,
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
					fieldLabel:i18n('i18n.helpdoc.activeOrNo'),						//'是否发布',
		            xtype : 'fieldcontainer',
		            id:'activeId3',
		            defaultType: 'radiofield',
		            defaults:{
						labelWidth:0,
						width:50
					},
		            layout: 'hbox',
		            items: [
		                {
		                    boxLabel  : i18n('i18n.util.yes'),						//'是',
		                    name      : 'active3',
		                    inputValue: 't',
		                    checked		:'checked',
		                    id        : 'radio13',
		                    margin:'0 0 0 12'
		                }, {
		                    boxLabel  : i18n('i18n.util.no'),						//'否',
		                    name      : 'active3',
		                    inputValue: 'f',
		                    id        : 'radio23'
		                }
		            ]
		        }
				,{
					fieldLabel:i18n('i18n.notice.topOrNo'),//'是否置顶'
		            xtype : 'fieldcontainer',
		            id:'topId3',
		            defaultType: 'radiofield',
		            defaults:{
						labelWidth:0,
						width:50
					},
		            layout: 'hbox',
		            items: [
		                {
		                    boxLabel  : i18n('i18n.util.yes'),						//'是',
		                    name      : 'top3',
		                    inputValue: 't',
		                    checked		:'checked',
		                    id        : 'radio33',
		                    margin:'0 0 0 12'
		                }, {
		                    boxLabel  : i18n('i18n.util.no'),						//'否',
		                    name      : 'top3',
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
					fieldLabel: i18n('i18n.notice.noticeTitle'),//'公告标题'//i18n('i18n.helpdoc.windowNum')	//'公告编号',
					xtype: '',
       	 			name: 'title',
       	 			id:'titleId4'
				}
				,Ext.create("Ext.form.ComboBox",{
					fieldLabel:i18n('i18n.notice.noticeType'),// '公告类别'//i18n('i18n.helpdoc.windowNum')	//'公告编号',
					xtype: '',
       	 			name: 'type',
       	 			id:'typeId4',
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
				}),
				Ext.create("Ext.form.ComboBox", {
					name : "moduleBelong",
					id:'moduleBelongId4',
					fieldLabel:i18n('i18n.helpdoc.belongModule'),//'所属模块',
					triggerAction : "all",
					// forceSelection : true,
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
					fieldLabel:i18n('i18n.helpdoc.activeOrNo'),						//'是否发布',
		            xtype : 'fieldcontainer',
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
					fieldLabel:i18n('i18n.notice.topOrNo')//'是否置顶'
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

	//上传附件model
	Ext.define('FileInfoModel',{
		extend:'Ext.data.Model',
		fields:[
				// 来源ID
				{name:'sourceId',type: 'string'},
				// 来源类型
				{name:'sourceType',type: 'string'},
				// 文件名称
				{name:'fileName',type: 'string'},
				// 文件类型
				{name:'fileType',type: 'string'},
				// 文件类型
				{name:'fileBusinessType',type: 'string'},
				// 保存路径
				{name:'savePath',type: 'string'},
				// 文件大小
				{name:'fileSize',type: 'number'}]
	});
	/**
	 * 附件信息 Store
	 */
	Ext.define('FileInfoStore',{
		extend:'Ext.data.Store',
		model:'FileInfoModel'
//		proxy:{
//			type:'memory'
//		}
	});

	Ext.define('FileInfoData', {
		
		//附件
		fileInfoStore : null,

		//附件store 
		getFileInfoStore:function(){
			if (this.fileInfoStore == null) {
				this.fileInfoStore = Ext.create('FileInfoStore'); 
			}
			return this.fileInfoStore;
		},
		//文件上传
		uploadFile:function(form,successFn,failFn){
			DpUtil.requestFileUploadAjax('../common/upload1.action',form,successFn,failFn);
		}
	});
	var fileData=Ext.create('FileInfoData',{});
	Ext.define('AnnexGrid',{
		extend:'SearchGridPanel',
		width:616,
		store:fileData.getFileInfoStore(),
		columns:[
			{text:'序号'	,	xtype:'rownumberer'		,	width:35	},//序号
			{text:'附件名称'		,	dataIndex:'fileName'	,	width:528.5	},//'附件名称'
			{
				text:'操作',
				renderer:function(){
					return '<a href=javascript:>'+'删除'+'</a>';//删除
				},
				width:50	
			}
		],
		listeners:{
			cellclick:function(me,td,cellIndex,record,tr,rowIndex){
				if(cellIndex==2){
					//如果点击某行的删除，则删除这条记录
					me.store.remove(record);
				}
			}
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
							addOrModify = 'add';
							
							//新增操作里面的预览方法
							var doPreview = function(){
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
				                param = {
				                	notice:{										// notice
				                		'title'			: Ext.isEmpty(tit)?'':tit,
				                		'type'			: Ext.isEmpty(typ)?'':DpUtil.changeDictionaryCodeToDescrip(typ,DataDictionary.NOTICE_TYPE),
					                	'content'		: Ext.isEmpty(str)?'':str,
					                	'imageAddr'  	: Ext.isEmpty(imageAddr)?'':imageAddr,
										'imageDescribe' : Ext.isEmpty(imageDescribe)?'':imageDescribe,
										'fileInfoList'	: fileInfoList,
										'createDate'	: DpUtil.renderDate(new Date()),
										'modifyDate'	: DpUtil.renderDate(new Date()),
										'visits'		: '0',
										'createUser'	: User.empName
				                }};
			                	var iWidth=1000; //弹出窗口的宽度;
								var iHeight=800; //弹出窗口的高度;
								var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
								var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
				                window.showModalDialog ('../common/noticePriviewWin.action', 
				                		param.notice, 'dialogWidth:1000px;dialogHeight:700px'); //这句要写成一行
							};
							
							var uploadAnnex = function(){
								var win = Ext.create('PopWindow',{
									id:'idid',
									width:750,
									layout:'fit',
									title:'上传附件',
									items:Ext.create('UploadAttachmentForm',{id:'imageUploadFormId'}),
									buttons:[{
										text:'取消',
										handler:function(){
											win.close();
										}
									}]
								});
								Ext.getCmp('imageUploadFormId').getForm().findField('file').fieldLabel='附件上传';
				        		Ext.getCmp('imageUploadFormId').getForm().findField('file').doComponentLayout();
								win.show();
							};
							var submitAddData = function() {
								
								Ext.getCmp('btnSubmit').setDisabled(true);
								if(!Ext.getCmp('TopFormPanel2Id').getForm().isValid()){
									MessageUtil.showMessage(i18n('i18n.helpdoc.inputSthPlease'));		//请输入数据
									Ext.getCmp('btnSubmit').setDisabled(false);
									return false;
								};
								
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
//								desAddress,
					                param = {
					                	notice:{										// :notice
					                		'title'				: tit,
					                		'type'				: typ,
						                	'moduleBelong'		: bMo,
						                	'active'			: act,
						                	'top'				: top,
						                	'content'			: str,
						                	'fileInfoList'		: fileInfoList,
						                	'imageAddr'			: imageAddr,
						                	'imageDescribe'		: imageDescribe
					                }},
					                fnS = function(){
//					                	labelDom.innerHTML = "<font color='red'>"+i18n("i18n.ladingstation.save.success")+"</font>";						//保存成功!
					                	MessageUtil.showInfoMes(i18n('i18n.notice.submitSuccess'),function(e){
					                			win.close();
					                	});
					                	
										imageAddr='';
					                	imageDescribe='';
					                	fileInfoList = new Array();
					                	Ext.getCmp('btnSubmit').setDisabled(false);
					                };
					                fnF = function(json){
					                	if(!Ext.isEmpty(json)){
					                		MessageUtil.showMessage(json.message);
					                	}else{
					                		MessageUtil.showMessage(i18n('i18n.notice.requireTimeOut'));
					                	}
					                	Ext.getCmp('btnSubmit').setDisabled(false);
					                };
					            if(str.length==0){
					            	MessageUtil.showMessage(i18n('i18n.helpdoc.inputSthPlease'));	//请输入数据
									return ;
								};
				                oNoticeManagement.addNotice(param,fnS,fnF);
				                Ext.getCmp('btnSubmit').setDisabled(true);
							};
							var win = Ext.create('PopWindow', {
								id:'addNoticeWindowId',
								title : i18n('i18n.helpdoc.addNotice')				//'新增一条公告数据',
								,width : 820,
								height:500,
								items : [
								    Ext.create('TopFormPanel2',{width:'100%'})
								,{
									id : 'editorAWrp',
									width:790,
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
									text : '添加附件'					//添加附件,
									,id:'addNoticeFileBtn'
									,margin:'5 10 0 0'
									,handler : uploadAnnex
								}, {
									text:'预览'
									,margin:'5 10 0 0'
									,handler : doPreview
								}, {
									text : i18n('i18n.util.btn.submit')					//'提交',
									,id:'btnSubmit'
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
										imageAddr='';
					                	imageDescribe='';
					                	fileInfoList01Length = 0;
					                	fileInfoList = new Array();
										Ext.getCmp("BBar").moveFirst();
										Ext.getCmp('btnAdd').setDisabled(false);
										Ext.getCmp('btnEdit').setDisabled(false);
										Ext.getCmp('btnChangeStatus').setDisabled(false);
										Ext.getCmp('btnDele').setDisabled(false);
										Ext.getCmp('btnSerh').setDisabled(false);
									},
									'beforeshow':function(){
										attachmentSize = 0 ;
										Ext.getCmp('addNoticeWindowId').setHeight(500);
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
					text:i18n('i18n.notice.temporaryNotActive')//'暂不发布
					,listeners:{
						'click':function(o){
							o.setDisabled(true);
							var selected = Ext.getCmp('gridId').getSelectionModel().getSelection();
							if(!selected||selected.length==0){
								MessageUtil.showMessage(i18n('i18n.util.msg.data_no_select'));							//请选择数据
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
								MessageUtil.showInfoMes(i18n('i18n.ladingstation.modify.success'));//'修改成功'
								Ext.getCmp("BBar").moveFirst();
								o.setDisabled(false);
							};
							fnF	= function(){
								MessageUtil.showMessage(i18n('i18n.notice.modifyFailed'));	//'修改失败'
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
							addOrModify = 'modify';
							var r,ctn;
							var selected = Ext.getCmp('gridId').getSelectionModel().getSelection();
							if(selected&&selected.length==1){
								r = selected[0].data;
								ctn = r.content;
							}else{
								MessageUtil.showMessage(i18n('i18n.util.msg.data_no_select_one'));//'请在表格中选定一条数据');
								return;
							};
							Ext.getCmp('btnAdd').setDisabled(true);
							Ext.getCmp('btnEdit').setDisabled(true);
							Ext.getCmp('btnChangeStatus').setDisabled(true);
							Ext.getCmp('btnDele').setDisabled(true);
							Ext.getCmp('btnSerh').setDisabled(true);
							
							/**
							 * 修改按钮里面的预览方法
							 */
							var doPreview = function(){
								var tit = Ext.getCmp('titleId3').getValue(),
									typ = Ext.getCmp('typeId3').getValue(),
									bMo = Ext.getCmp('moduleBelongId3').getRawValue(),
									act	= null,
									top	= null,
									radio1	= Ext.getCmp('radio13'),
									radio2	= Ext.getCmp('radio23'),
									radio3	= Ext.getCmp('radio33'),
									radio4	= Ext.getCmp('radio43');
									(radio1.getValue()?'':radio2.setValue(true));
									(radio2.getValue()?'':radio1.setValue(true));
								act = ((radio1.getValue())?true:false);
								top = ((radio3.getValue())?true:false);
								
								var fileInfoList02 = new Array();
								for ( var i = 0; i < fileInfoList01.length; i++) {
									fileInfoList02.push(fileInfoList01[i]);
								}
								for ( var i = 0; i < fileInfoList.length; i++) {
									fileInfoList02.push(fileInfoList[i]);
								}
								var str	= editor2.html(),
					                param = {
					                	notice:{
					                		'title'			: Ext.isEmpty(tit)?'':tit,
			                				'type'			: Ext.isEmpty(typ)?'':DpUtil.changeDictionaryCodeToDescrip(typ,DataDictionary.NOTICE_TYPE),
						                	'content'		: Ext.isEmpty(str)?'':str,
						                	'imageAddr'  	: Ext.isEmpty(imageAddr)?'':imageAddr,
											'imageDescribe' : Ext.isEmpty(imageDescribe)?'':imageDescribe,
											'fileInfoList'	: fileInfoList02,
											'createDate'	: Ext.isEmpty(r.createDate)?DpUtil.renderDate(new Date()):DpUtil.renderDate(r.createDate),
											'modifyDate'	: Ext.isEmpty(r.modifyDate)?DpUtil.renderDate(new Date()):DpUtil.renderDate(r.modifyDate),
											'visits'		: Ext.isEmpty(r.visits)?'0':r.visits,
											'createUser'	: Ext.isEmpty(r.createUser)?User.empName:r.createUser
					                	}
									};
				                	
									/**
									 * 预览显示方式设置以及传参
									 */
					                var iWidth=1000; //弹出窗口的宽度;
									var iHeight=800; //弹出窗口的高度;
									var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
									var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
									window.showModalDialog ('../common/noticePriviewWin.action', 
					                		param.notice, 'dialogWidth:1000px;dialogHeight:700px'); //这句要写成一行
							};
							
							/**
							 * 附件上传方法
							 */
							var uploadAnnex = function(){
								var win = Ext.create('PopWindow',{
									id:'idid',
									width:750,
									layout:'fit',
									title:'上传附件',
									items:Ext.create('UploadAttachmentForm',{id:'imageUploadFormId'}),
									buttons:[{
										text:'取消',
										handler:function(){
											win.close();
										}
									}]
								});
								Ext.getCmp('imageUploadFormId').getForm().findField('file').fieldLabel='附件上传';
				        		Ext.getCmp('imageUploadFormId').getForm().findField('file').doComponentLayout();
								win.show();
							}
							
							var submitEditData = function() {
								if(!Ext.getCmp('TopFormPanel3Id').getForm().isValid()){
									MessageUtil.showMessage(i18n('i18n.helpdoc.inputSthPlease'));	//'请输入数据。
									return false;
								};
								var tit = Ext.getCmp('titleId3').getValue(),
									typ = Ext.getCmp('typeId3').getValue(),
									bMo = Ext.getCmp('moduleBelongId3').getRawValue(),
									act	= null,
									top	= null,
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
						                	'content'		: str,
						                	'fileInfoList'	: fileInfoList01,
						                	'imageAddr'		: imageAddr,
						                	'imageDescribe'	: imageDescribe
					                }},
					                fnS = function(){
					                	//labelDom.innerHTML = "<font color='red'>"+i18n("i18n.ladingstation.save.success")+"</font>";						//保存成功!
										MessageUtil.showInfoMes(i18n('i18n.ladingstation.save.success'),function(e){
				                			win.close();
										});
					                	imageAddr='';
					                	imageDescribe='';
					                	fileInfoList = new Array();
					                };
					                fnF = function(json){
					                	if(Ext.isEmpty(json)){
					                		MessageUtil.showMessage(i18n("i18n.notice.requireTimeOut"));
					                	}else{
					                		MessageUtil.showMessage(json.message);
					                	}
					                };
					            if(str.length==0){
									MessageUtil.showMessage(i18n('i18n.helpdoc.inputSthPlease'));	//请输入数据
									return ;
								};
				                oNoticeManagement.updNotice(jP,fnS,fnF);
							};
							var win = Ext.create('PopWindow', {
								id:'modifyNoticeWindowId',
								title : i18n('i18n.helpdoc.editNotice')				//'编辑单条公告信息',
								,width : 820,
								height : 500,
								items : [
									Ext.create('TopFormPanel3',{
										width:'100%'
									}),{
										id : 'editorUWrp',
										width:790,
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
									text : '添加附件'					//'添加附件',
									,id:'modifyNoticeFileBtn'
									,margin:'5 10 0 0'
									,handler : uploadAnnex
								}, {
									text:'预览'
									,margin:'5 10 0 0'
									,handler : doPreview
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
										imageAddr='';
					                	imageDescribe='';
					                	fileInfoList01Length = 0;
					                	fileInfoList01 = new Array();
					                	fileInfoList = new Array();
										Ext.getCmp("BBar").moveFirst();
										Ext.getCmp('btnAdd').setDisabled(false);
										Ext.getCmp('btnChangeStatus').setDisabled(false);
										Ext.getCmp('btnEdit').setDisabled(false);
										Ext.getCmp('btnDele').setDisabled(false);
										Ext.getCmp('btnSerh').setDisabled(false);
									},
									'beforeshow':function(){
										attachmentSize = 0 ;
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
							var record = new NoticeGridModel(r);
								nId = record.data.id;
							
							Ext.getCmp("TopFormPanel3Id").getForm().loadRecord(record);
							
							/****点击编辑的时候获取公告信息*****/
							var param = {
								'noticeId':record.data.id	
							};
							var successFn= function(json){
								var r = Ext.create('NoticeGridModel',json.noticece);
								
								fileInfoList = json.noticece.fileInfoList;
								imageAddr = json.noticece.imageAddr;
								imageDescribe = json.noticece.imageDescribe;
								Ext.getCmp("modifyNoticeWindowId").setHeight(500);
								
								//修改的时候取得已有附件的条数
								fileInfoList01Length = json.noticece.fileInfoList.length;
								
								//修改的时候取得已有附件的条数，如果大于等于10则添加附件按钮不能点击
								if(json.noticece.fileInfoList.length>=10){
									Ext.getCmp('modifyNoticeFileBtn').setDisabled(true);
								}else{
									Ext.getCmp('modifyNoticeFileBtn').setDisabled(false);
								}
								
								//动态添加附件的显示
								for ( var i = 0; i < json.noticece.fileInfoList.length; i++) {
									attachmentSize +=   json.noticece.fileInfoList[i].fileSize;
									var fileName	=	json.noticece.fileInfoList[i].fileName;
									var inputPath	=	json.noticece.fileInfoList[i].savePath;
									Ext.getCmp("modifyNoticeWindowId").setHeight(Ext.getCmp("modifyNoticeWindowId").getHeight()+22);
									Ext.getCmp("modifyNoticeWindowId").add([{
										xtype:'displayfield',
										value:'<a href=javascript:downLoadFile("'+fileName+'","'+inputPath+'")>'+json.noticece.fileInfoList[i].fileName+'</a>',
									}]);
								}
							};
							var failureFn = function(json){
								if(Ext.isEmpty(json)){
									MessageUtil.showErrorMes("访问超时");
								}else{
									MessageUtil.showErrorMes(json.message);
								}
							};
							findNoticeById(param,successFn,failureFn);
							
							/******/
							
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
								MessageUtil.showInfoMes(i18n('i18n.util.msg.delete_succeed'));	// 删除成功
								Ext.getCmp("BBar").moveFirst();
								o.setDisabled(false);
							};
							fnF	= function(){
								MessageUtil.showMessage(i18n('i18n.util.msg.delete_fail'));		//'删除失败';
								o.setDisabled(false);
							};
							oNoticeManagement.delNotice(jP,fnS,fnF);
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
				// :i18n
				handler: function() {
					Ext.getCmp("BBar").moveFirst();
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
	getBBar : function() {
		var j = this;
		return Ext.create("Ext.toolbar.Paging", {
			id : "BBar",
			store : j.store,
			displayMsg : i18n("i18n.helpdoc.displayMsg"),
			displayInfo : true,
			items : ["-", {
				text : i18n("i18n.util.pager_prefixText")				//'每页'
				,xtype : "tbtext"
			}, Ext.create("Ext.form.ComboBox", {
				width : 50,
				value : "30",
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
		
		return [
			new Ext.grid.RowNumberer(),
			{
				header : i18n('i18n.notice.noticeTitle'),//'公告标题'// i18n('i18n.helpdoc.helpTitle')	//'标题',
				sortable : false,
				dataIndex:'title'
				,flex:1
				,renderer:function(val){
					return '<span data-qtip="'+ val +'">'+val+'</span>';
				}
			}
			, {
				header : i18n('i18n.helpdoc.belongModule'),			//'所属模块',
				sortable : false,
				dataIndex:'moduleBelong'
				,width:100
			}, {
				header :i18n('i18n.notice.noticeType'),// '公告类别'									//'公告类别',
				sortable : false,
				dataIndex:'type'
				,width:100
				,renderer:function(value){
					return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.NOTICE_TYPE);
	  	        }
			},{
				header : i18n('i18n.helpdoc.activeOrNo'),			//'是否发布',
				sortable : false,
				dataIndex:'active'
				,width:60,
				renderer:function(value){
					return value?i18n('i18n.util.yes'):i18n('i18n.util.no');	//是		否
				}
			}, {
				header : i18n('i18n.big_small_city.createUName')//'创建人'
				,sortable : false,
				dataIndex:'createUser'
				,width:60
			}, {
				header : i18n('i18n.big_small_city.createTime'),//'创建时间'									//'创建时间',
				sortable : false,
				dataIndex:'createDate'
				,width:90
				,renderer:function(value){
					return DpUtil.renderDate(value);
				}
			}, {
				header :i18n('i18n.notice.UpdateDate'),// '更新时间'									//'更新时间',
				sortable : false,
				dataIndex:'modifyDate'
				,width:90
				,renderer:function(value){
					return DpUtil.renderDate(value);
				}
			},{
                xtype: 'actioncolumn',
                title : i18n('i18n.notice.operation'),//'操作',
                width: 90,
                defaults: {
				    autoScroll: true
				},
                items: [{	// 修改TODO:编辑分离src/main/webapp/images/common/add.png
	                icon: '../images/common/common/edit.png',  // Use a URL in the icon config
	                tooltip: i18n('i18n.notice.editNotice'),//'编辑公告',
	                handler: function(grid, rowIndex, colIndex) {

	                	
						var r	= grid.panel.store.getAt(rowIndex),
							ctn = r.data.content;
							
						/**
						 * 编辑图标里面的预览方法	
						 */
						var doPreview = function(){
							addOrModify='edit';
							var tit = Ext.getCmp('titleId4').getValue(),
								typ = Ext.getCmp('typeId4').getValue(),
								bMo = Ext.getCmp('moduleBelongId4').getRawValue(),
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
							
							var fileInfoList02 = new Array();
							for ( var i = 0; i < fileInfoList01.length; i++) {
								fileInfoList02.push(fileInfoList01[i]);
							}
							for ( var i = 0; i < fileInfoList.length; i++) {
								fileInfoList02.push(fileInfoList[i]);
							}
							
							var str	= editor2.html(),
				                windowParam = {
				                	notice:{
				                		'title'			: Ext.isEmpty(tit)?'':tit,
				                		'type'			: Ext.isEmpty(typ)?'':DpUtil.changeDictionaryCodeToDescrip(typ,DataDictionary.NOTICE_TYPE),
					                	'content'		: Ext.isEmpty(str)?'':str,
					                	'imageAddr'  	: Ext.isEmpty(imageAddr)?'':imageAddr,
										'imageDescribe' : Ext.isEmpty(imageDescribe)?'':imageDescribe,
										'fileInfoList'	: fileInfoList02,
										'createDate'	: Ext.isEmpty(r.data.createDate)?DpUtil.renderDate(new Date()):DpUtil.renderDate(r.data.createDate),
										'modifyDate'	: Ext.isEmpty(r.data.modifyDate)?DpUtil.renderDate(new Date()):DpUtil.renderDate(r.data.modifyDate),
										'visits'		: Ext.isEmpty(r.data.visits)?'0':r.data.visits,
										'createUser'	: Ext.isEmpty(r.data.createUser)?User.empName:r.data.createUser
				                }};
							/**
							 * 预览显示方式设置以及传参
							 */
			                var iWidth=1000; //弹出窗口的宽度;
							var iHeight=800; //弹出窗口的高度;
							var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
							var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
							window.showModalDialog ('../common/noticePriviewWin.action', 
									windowParam.notice, 'dialogWidth:1000px;dialogHeight:700px'); //这句要写成一行
					            
						};
						var uploadAnnex = function(){
							var win = Ext.create('PopWindow',{
								width:750,
								layout:'fit',
								title:'上传附件',
								items:Ext.create('UploadAttachmentForm',{id:'imageUploadFormId'}),
								buttons:[{
									text:'取消',
									handler:function(){
										win.close();
									}
								}]
							});
							Ext.getCmp('imageUploadFormId').getForm().findField('file').fieldLabel='附件上传';
			        		Ext.getCmp('imageUploadFormId').getForm().findField('file').doComponentLayout();
							win.show();
						};
						var submitEditData = function() {
							if(!Ext.getCmp('TopFormPanel4Id').getForm().isValid()){
								MessageUtil.showMessage(i18n('i18n.helpdoc.inputSthPlease'));	//'请输入数据。
								return false;
							};
							var tit = Ext.getCmp('titleId4').getValue(),
								typ = Ext.getCmp('typeId4').getValue(),
								bMo = Ext.getCmp('moduleBelongId4').getRawValue(),
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
				                param = {
				                	notice:{
				                		'id'			: nId,
				                		'title'			: tit,
				                		'type'			: typ,
					                	'moduleBelong'	: bMo,
					                	'active'		: act,
					                	'top'			: top,
					                	'content'		: str,
					                	'imageAddr'  	: imageAddr,
										'imageDescribe' : imageDescribe,
										'fileInfoList'	: fileInfoList01
				                }},
				                fnS = function(){
				                	//labelDom.innerHTML = "<font color='red'>"+i18n("i18n.ladingstation.save.success")+"</font>";//保存成功!
				                	MessageUtil.showInfoMes(i18n('i18n.ladingstation.save.success'),function(e){
			                			win.close();
									});
									imageAddr = "";
									imageDescribe = "";
				                };
				                fnF = function(json){
				                	if(Ext.isEmpty(json)){
				                		MessageUtil.showMessage(i18n("i18n.notice.requireTimeOut"));
				                	}else{
				                		MessageUtil.showMessage(json.message);
				                	}
				                };
				            if(str.length==0){
								MessageUtil.showMessage(i18n('i18n.helpdoc.inputSthPlease'));	//请输入数据
								return ;
							};
			                oNoticeManagement.updNotice(param,fnS,fnF);
						};
						var win = Ext.create('PopWindow', {
							id:'editNoticeWindowId',
							title : i18n('i18n.helpdoc.editNotice')				//'编辑单条公告信息',
							,width : 820,
							height:500,
							//zm14日修改
							modal:true,
							items : [
								Ext.create('TopFormPanel4',{
									width:'100%'
								}),{
										id : 'editorUWrp',
										width:790,
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
								text : '添加附件'					//'添加附件',
								,id:'editNoticeFileBtn'
								,margin:'5 10 0 0'
								,handler : uploadAnnex
							}, {									//预览
								text:'预览'
								,margin:'5 10 0 0'
								,handler : doPreview
							},{
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
									imageAddr='';
				                	imageDescribe='';
				                	fileInfoList = new Array();
				                	fileInfoList01 = new Array();
				                	fileInfoList01Length = 0;
									Ext.getCmp("BBar").moveFirst();
									//zm14日修改
									//Ext.getCmp('radio1').setValue(true);
//									Ext.getCmp('btnAdd').setDisabled(false);
//									Ext.getCmp('btnChangeStatus').setDisabled(false);
//									Ext.getCmp('btnEdit').setDisabled(false);
//									Ext.getCmp('btnDele').setDisabled(false);
//									Ext.getCmp('btnSerh').setDisabled(false);
								},
								'beforeshow':function(){
									attachmentSize = 0;
								}
							}
						});
						/***/
						/****点击编辑图标的时候获取公告信息*****/
						var param = {
							'noticeId':r.data.id	
						};
						var successFn= function(json){
							var r = Ext.create('NoticeGridModel',json.noticece);							
							fileInfoList = json.noticece.fileInfoList;
							imageAddr = json.noticece.imageAddr;
							imageDescribe = json.noticece.imageDescribe;
							Ext.getCmp("editNoticeWindowId").setHeight(500);
							
							//修改的时候取得已有附件的条数
							fileInfoList01Length = json.noticece.fileInfoList.length;
							
							//修改的时候取得已有附件的条数，如果大于等于10则添加附件按钮不能点击
							if(json.noticece.fileInfoList.length>=10){
								Ext.getCmp('editNoticeFileBtn').setDisabled(true);
							}else{
								Ext.getCmp('editNoticeFileBtn').setDisabled(false);
							}
							
							//动态显示附件
							for ( var i = 0; i < json.noticece.fileInfoList.length; i++) {
								var fileName	=	json.noticece.fileInfoList[i].fileName;
								var inputPath	=	json.noticece.fileInfoList[i].savePath;
								attachmentSize += 	json.noticece.fileInfoList[i].fileSize;
								Ext.getCmp("editNoticeWindowId").setHeight(Ext.getCmp("editNoticeWindowId").getHeight()+22);
								Ext.getCmp("editNoticeWindowId").add([{
									xtype:'displayfield',
									value:'<a href=javascript:downLoadFile("'+fileName+'","'+inputPath+'")>'+json.noticece.fileInfoList[i].fileName+'</a>',
								}]);
							}
						};
						var failureFn = function(json){
							if(Ext.isEmpty(json)){
								MessageUtil.showErrorMes("访问超时");
							}else{
								MessageUtil.showErrorMes(json.message);
							}
						};
						findNoticeById(param,successFn,failureFn);
						win.show();
						/*****/
						
						/**
						 * @method：编辑器失去焦点做长度校验。 
						 */
						var editor2	= KindEditor.create('#editorUpdtWrp',jO.options),
							labelDom= Ext.getCmp('resultField').el.dom;
						editor2.html(ctn);
						var nId = r.data.id;

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
	                icon: '../images/common/common/moveTop.png',
	                tooltip: i18n('i18n.notice.topNotice'),//置顶公告
	                handler: function(grid, rowIndex, colIndex) {
	                    var rec = grid.getStore().getAt(rowIndex),
	                    	id	= rec.get('id'),
	                    	jP = {
	                    		"noticeId":id
	                    	},
		                fnS = function(){
	                    	MessageUtil.showInfoMes(i18n('i18n.notice.topSucceed'));//'置顶成功'
	                    	Ext.getCmp("BBar").moveFirst();
		                };
		                fnF = function(json){
		                	if(Ext.isEmpty(json)){
		                		MessageUtil.showMessage(i18n("i18n.notice.requireTimeOut"));
		                	}else{
		                		MessageUtil.showMessage(json.message);
		                	}
		                };
		                oNoticeManagement.topNotice(jP,fnS,fnF);
	                }
	            },{		// 不发布		oNoticeManagement.changeStatus
	                icon: '../images/common/common/changeStatus.png',
	                tooltip: i18n('i18n.notice.modifyToNotActive'),//'修改为不发布',
	                handler: function(grid, rowIndex, colIndex) {
	                	var rec = grid.getStore().getAt(rowIndex),
	                		id 	= rec.get('id'),
	                		aArr = new Array();
	          
	                	aArr[0] = id;
                		var jP = {'ids':aArr };
                		fnS = function(o){
							MessageUtil.showInfoMes(i18n('i18n.ladingstation.modify.success'));//修改成功
							Ext.getCmp("BBar").moveFirst();
							//o.setDisabled(false);
						};
						fnF	= function(o){
							MessageUtil.showMessage(i18n('i18n.notice.modifyFailed'));		//'修改失败';
							//o.setDisabled(false);
						};
	                	oNoticeManagement.changeStatus(jP,fnS,fnF);
	                }
	            },{		// 删除
	                icon: '../images/common/common/delete.png',
	                tooltip: i18n('i18n.notice.deleteNotice'),///'删除公告',
	                handler: function(grid, rowIndex, colIndex) {
	                	var rec = grid.getStore().getAt(rowIndex),
	                		id 	= rec.get('id'),
	                		aArr = new Array();
	                	
	                	aArr[0] = id;
	                	
                		var jP = {'ids':aArr };
						fnS = function(){
							MessageUtil.showInfoMes(i18n('i18n.util.msg.delete_succeed'));	// 删除成功
							Ext.getCmp("BBar").moveFirst();
							//o.setDisabled(false);
						};
						fnF	= function(){
							MessageUtil.showMessage(i18n('i18n.util.msg.delete_fail'));		//'删除失败';
							//o.setDisabled(false);
						};
						oNoticeManagement.delNotice(jP,fnS,fnF);
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

				mdf = topForm.findField("modifyUserId").getValue(),
				crt = topForm.findField("createUserId").getValue(),
				tit = topForm.findField("titleId").getValue(),
				typ = topForm.findField("typeId").getValue(),
				fro = topForm.findField("from_dateId").getValue(),
				end = topForm.findField("to_dateId").getValue(),
				mod = topForm.findField("moduleBelongId").getRawValue(),
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
			if(fro>=end){
				MessageUtil.showMessage(i18n('i18n.helpdoc.inputSearchTimeErr'));// i18n.helpdoc.inputSearchTimeErr=查询开始时间大于结束时间！
				return;
			};
			if(Ext.isEmpty(tit)&&Ext.isEmpty(typ)&&Ext.isEmpty(fro)&&Ext.isEmpty(end)&&Ext.isEmpty(mod)){
				MessageUtil.showMessage(i18n('i18n.helpdoc.inputSearchCdn'));	//请完善查询条件
				return ;
			};

			var noticeSearchCondition = {
	        	'noticeSearchCondition.modifyUserId'	: mdf,
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
		});
		Ext.getCmp('btnSerh').setDisabled(false);
		return store;
	}
});

	Ext.define('NoticeManagementLayout', {
		extend : 'Ext.container.Viewport',
		title :i18n('18n.notice.noticeManagementBackground') //'公告管理后台'
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

/**
 * 公告附件下载
 * @param fileName
 * @param filePath
 */
function downLoadFile(fileName,filePath){
	window.location.href="../common/downLoad.action?fileName="+fileName+"&inputPath="+filePath;
}