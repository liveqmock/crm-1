﻿/**
 * @author rock
 * 超级信息页面添加帮助
 */
Ext.onReady(function() {
	Ext.form.Field.prototype.msgTarget='side';

	Ext.define('SendSuperHtmlForm', {
		extend : 'Ext.form.Panel',
		height : 280,
		items : null,
		fbar : null,
		border:false,
		margin:'20 0 0 10',
		initComponent : function() {
			this.items = this.getItems();
			this.callParent();
		},
		getItems : function() {
			var me = this;
			return [{
				title : '',
				layout : {
					type : "table",
					columns : 2
				},
				border:false,
				defaultType : "textfield",
				defaults : {
					labelWidth : 90,
					width : 780,
				},
				items : [
				{
					name : 'SuperHtml',
					id : 'SuperHtml',
					allowBlank : false,
					height : 240,
					xtype : 'textarea'
					,fieldLabel : i18n('i18n.util.btn.writeSt')				//=请输入超级消息
					,maxLength:300
					,minLength:10
				}]
			}]
		},
		buttons : [{
			text : i18n('i18n.ladingstation.button.reset'),					//'重置',
			margin:'0 12 0 0',
			handler : function() {
				this.up('form').getForm().reset();
			}
		}, {
			text : i18n('i18n.util.btn.submit'),							//'提交',
			formBind : true, 												//only enabled once the form is valid
			disabled : true,
			id:'submitBtn',
			margin:'0 36 0 0',
			handler : function() {
				Ext.getCmp("submitBtn").setDisabled(true);
				var form = this.up('form').getForm();
				if (form.isValid()) {
					var sHtml = Ext.getCmp('SuperHtml').getValue(),
						params = {superMsg:sHtml};
					Ext.Ajax.request({
						url : 'addSuperMessage.action',
						params:	params,
						success:function(response){
							var result = Ext.decode(response.responseText);
							if(result.success){
								MessageUtil.showMessage(i18n('i18n.util.sendSupperMsgS'));
								Ext.getCmp("submitBtn").setDisabled(false);
								return ;
							}else{
								MessageUtil.showMessage(result.message);
							}
						}
					});
				}
			}
		}]
	});

	// var formPanel = Ext.create('Ext.form.Panel', {
		// id:'wrapForm20120928-5',//wrapForm20120928-5
		// border : false,
		// frame : true,
		// items : [{
			// id : 'id120120928-5',
			// name : 'first',
			// height:24
		// }, {
			// id : 'id220120928-5',
			// name : 'last'
			// ,height:300
		// }]
	// });

	Ext.define('SendSuperHtmlLayout', {
		extend : 'Ext.container.Viewport',
		title : i18n('i18n.util.btn.sendSupperMsg'), 						//'发送超级消息'
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		items : [
			new SendSuperHtmlForm()
			// ,formPanel
			]
	});
	new SendSuperHtmlLayout();

//	Ext.create('Depon.Lib.oDocHelper', {
//		helpDoc:{															// 帮助实体：
//			windowNum:'20120928-5'	// TODO:帮助文档的ID	belongModule(首字母缩写)+belongMenu（首字母缩写）+windowNum(弹窗编号)+首编时间戳
//			,active:true			// 记录操作员操作，是否选择了”隐藏帮助“；
//		},
//		sHeight : '160px'
//	});
});