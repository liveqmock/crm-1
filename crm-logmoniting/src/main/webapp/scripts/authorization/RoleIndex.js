/**
 * @description 角色管理主页
 */
Ext.onReady(function() {
	document.title = i18n('i18n.authorization.role_page_title');
//	document.getElementById('message_waiting').innerHTML = i18n('i18n.authorization.message_waiting');
//	setTimeout(function() {
//			Ext.get('loading').remove();
//			Ext.get('loading-mask').fadeOut({
//				remove : true
//			});
//		}, 1000);
	Ext.QuickTips.init();
	Ext.create('Ext.Viewport',{
        baseCls:'x-plain',
        layout:'border',
		items : [{
        	region : 'north',
    		baseCls:'x-plain',
			xtype : 'form',
			id : 'query_role_form',
			defaults: {
		    	margin:'0 0 5 0',
		        anchor: '100%'
		    },
			height :70,
			items : [{
		        xtype:'fieldset',
		        title: i18n('i18n.authorization.role.findcase'),
		        height :60,
		        fieldDefaults : {
					msgTarget : 'side',
					labelAlign : 'left'
				},
				defaultType : 'textfield',
				layout:'column',
				defaults : {
					margin : '5 10 5 10',
					anchor : '100%',
					labelWidth:60
				},
				//删除收缩按钮
		        //collapsible: true,
		        items :[{
					name: 'roleName',
			        fieldLabel: i18n('i18n.authorization.role.roleName'),
			        columnWidth:.25,
			        xtype : 'textfield'
				},{
					border : false,
					xtype : 'container',
					width:70,
					items : [{
						xtype : 'button', 
						width:70,
						text : i18n('i18n.authorization.find'),
						handler : function() {
							Ext.getCmp('RoleGrid_pagingToolbar').moveFirst();
						}
					}]
				}]
		    }]
        }, {
        	region : "center",
			baseCls:'x-plain',
			items:[RoleGrid]
        }]
	});
});