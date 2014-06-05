/**
 * @description 用户管理主页
 */
Ext.onReady(function() {
	document.title = i18n('i18n.authorization.user_page_title');
//	document.getElementById('message_waiting').innerHTML = i18n('i18n.authorization.message_waiting');
//	setTimeout(function() {
//			Ext.get('loading').remove();
//			Ext.get('loading-mask').fadeOut({
//				remove : true
//			});
//		}, 1000);
	Ext.QuickTips.init();
	initDeptAndUserInfo();//获取当前登录用户信息
	Ext.create('Ext.Viewport',{
		layout : {
			type : 'border'
		},
		height:700,
	    width:250,
		items : [ {
	    	region : "west",
//	        baseCls:'x-plain',
	    	border:true,
	        width:200,
	        split:true,
	        margin:'5 5 0 0',
	    	//autoScroll:true,
	    	layout : 'accordion',
	        items:[TreePanle]
	    },{
	        split : true,
	        region : "center",
	        baseCls:'x-plain',
	        margin:'5 3 0 0',
	        layout:'border',
	        items:[{
	        	region : 'north',
	    		baseCls:'x-plain',
	    		items:[{	
	    			xtype : 'form',
	    			//autoScroll:true,
	    			frame:true,
	    			height:100,
	    			defaults: {
    			    	margin:'4 5 4 5',
    			        anchor: '100%'
    			    },
					id : 'query_user_form',
					items : [{
				        xtype:'fieldset',
				        columnWidth: 0.5,
				        height:80,
				        title: i18n('i18n.authorization.user.findcase'),
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
					        xtype: 'combo',
					        fieldLabel: i18n('i18n.authorization.user.empNumber'),
					        store: EmployeeStore,
					        columnWidth:.5,
					        id:'deptCode_ComBox',
							displayField: 'empCode',
							valueField: 'empCode',
							name: 'empCode.empCode',
							queryParam:'empCode.empCode',
							minChars:1,
					        typeAhead: false,
					        hideTrigger:false,
					        //regex: /^[0-9]{0,6}$/,
							regexText:i18n('i18n.authorization.EmpCodeLongException'),
					        listConfig: {
					        	minWidth : window.screen.availWidth*0.1953,
					            getInnerTpl: function() {
					                 return '{empCode}&nbsp&nbsp&nbsp{empName}';
					            }
					        },
					        pageSize: 10
						},{
							name: 'empCode.empName',
							columnWidth:.5,
							//regex:  /^[u4e00-u9fa5]{0,}$/,
							//regexText:i18n('i18n.authorization.EmpNameLongException'),
					        fieldLabel: i18n('i18n.authorization.user.empName'),
					        xtype : 'textfield'
						},{
							name: 'status',
							columnWidth:.5,
							fieldLabel: i18n('i18n.authorization.user.status'),
							xtype:'combo',
						    store: Ext.create('Ext.data.Store', {
							    fields: ['name', 'value'],
							    data : [
							            {'name':i18n('i18n.authorization.user.valid'), 'value':1},//生效
							            {'name':i18n('i18n.authorization.user.invalid'), 'value':0}//失效
							    ]
							}),
						    queryMode: 'local',
						    displayField: 'name',
						    valueField: 'value'
						},{
							border : false,
							columnWidth:.5,
							xtype : 'container',
							items : [{
								width :70,
								xtype : 'button', 
								//margin : '5 10 5 260',
								text : i18n('i18n.authorization.find'),
								handler : function() {
									Ext.getCmp('UserGrid_pagingToolbar').moveFirst();
								}
							}]
						}]
				    }]}]
	        }, {
	        	region : "center",
    			baseCls:'x-plain',
    			 margin:'5 0 0 0',
    			items:[UserGrid]
	        }]
		}]
	});
});