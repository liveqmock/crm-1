/**
 * 工单责任查询页面
 */
var viewport = null;
initDeptAndUserInfo();

var user = top.User;

/**
 * 疑似重复客户查询面板
 */
Ext.define('SearchRepeatCustFormPanel',{
	extend:'SearchFormPanel',
	id:'searchRepeatCustFormPanelId',
	border:0,
	layout:'column',
	padding:'5 5 0 0',
	defaults:{
		labelWidth:75,
		labelAlign:'right',
		columnWidth:0.33
	},
	defaultType:'dptextfield',
	initComponent:function(){	
		var me = this;
		Ext.applyIf(this,{
			items:this.getItems()
		});
		this.callParent(arguments);
		
		//所属部门设置默认值
		var dept = me.getForm().findField('searchCondition.deptId');
		
		dept.store.add(Ext.create('CurrentUserDeptListModel',{
			'deptId':user.deptId,'deptName':user.deptName
		}));
		dept.setValue(user.deptId);
	},
	getItems:function(){
		var me  = this;
		return [
			{
				xtype:'belongdeptcombox',name:'searchCondition.deptId',
				functionName:'FunctionRepeatCust',
				allowBlank:false,maxLength : 50,
				fieldLabel:i18n('i18n.custrepeat.deptName')/*所属部门*/
			}
			,{
				fieldLabel:i18n('i18n.custrepeat.custCode')/*客户编码*/,name:'searchCondition.custCode',
				maxLength : 20
			}
			,{
				fieldLabel:i18n('i18n.custrepeat.custName')/*客户名称*/,name:'searchCondition.custName',
				maxLength : 100
			}
			,{
				fieldLabel:i18n('i18n.custrepeat.telephone')/*固定电话*/,name:'searchCondition.telephone',
				maxLength : 13
			}
		];
	}
});

/**
 * 疑似重复的主客户列表
 */
Ext.define('MainRepeatCustGrid',{
	extend:'SearchGridPanel',
	id:'mainRepeatCustGridId',
	defaults:{align:'center'},
	autoScroll:true,
	selModel:new Ext.selection.CheckboxModel({mode:'single'}),
	initComponent:function(){ 
		var me = this;
		me.store = Ext.create('MainCustRepeatListStore',{
			'listeners':{
				beforeload:function(store,operation,e){		
					var form = Ext.getCmp('searchRepeatCustFormPanelId').getForm();
					Ext.apply(operation,{
						params : form.getValues()
					});
				}
			}
		});
		me.columns = [
		    {//序号
		    	xtype:'rownumberer',width:40,
				header:i18n('i18n.custrepeat.serial_number')
		    },{//客户名称
				header:i18n('i18n.custrepeat.custName'),
				dataIndex:'custName',width:110
		    },{ //客户编码
				header :i18n('i18n.custrepeat.custCode'),
				dataIndex:'custCode',
				width:120
		    },{//固定电话
				header :i18n('i18n.custrepeat.telephone'),
				dataIndex:'telephone',width:100
		    },{//有效合同信息
				header :i18n('i18n.custrepeat.validContract'),
				dataIndex:'isExistValidContract',
				width:100,renderer:function(value){
					return value=='0'?'无':'有';
				}
		    },{//历史合同信息
				header :i18n('i18n.custrepeat.historyContract'),
				dataIndex:'isExistHistoryContract',
				width:100,renderer:function(value){
					return value=='0'?'无':'有';
				}
		    },{//近三个月发货金额
				header :i18n('i18n.custrepeat.deliveryMoneyTotal'),
				dataIndex:'deliveryMoneyTotal',width:120
		   	},
		   	{//所属部门
				header :i18n('i18n.custrepeat.deptName'),
				dataIndex:'deptName',width:150
		    },
		    {//所属小区
				header :i18n('i18n.custrepeat.smallRegionDeptName'),
				dataIndex:'smallRegionDeptName',width:150
		    },
		    {//所属大区
				header :i18n('i18n.custrepeat.bigRegionDeptName'),
				dataIndex:'bigRegionDeptName',width:150
		    },
		    {//所属事业部
				header :i18n('i18n.custrepeat.bussDeptName'),
				dataIndex:'bussDeptName',width:150
		    }
	    ];
		//me.dockedItems= Ext.create('Ext.ux.PageComboResizer');
		//双击表格记录，弹出工单责任详情窗口
		me.on('itemdblclick',function(th,record,item,index,e,eOpts){
			
		});
		
		me.dockedItems = me.getMyDockedItems();
		this.callParent(arguments);
   },
   //分页条
	getMyDockedItems :function(){ 
		var me = this;
		return [{
			xtype : 'pagingtoolbar'
			,plugins:[Ext.create('Ext.ux.PageComboResizer')]
			,store : me.store
			,dock : 'bottom'
			,displayInfo : true
		}];
	}
});

Ext.define('RepeatCustLookBtnPanel',{
	extend:'NormalButtonPanel'
	,initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent(arguments);
		
		if(Ext.Array.contains(User.roleids,i18n('i18n.custrepeat.role.roleMember'))){
			Ext.getCmp('btn_disposeId').show();
			Ext.getCmp('btn_manualDisposeId').show();
		}
	}
	,getItems:function(){
		var me = this;
		return [
			{
				xtype:'leftbuttonpanel',
				items:[
					{
						xtype:'button',scope:me,id:'btn_disposeId',
						text:i18n('i18n.custrepeat.btn_dispose'),
						hidden:true,
						handler:function(btn){me.event_dispose(btn);}
					},
					{
						xtype:'button',scope:me,id:'btn_manualDisposeId',
						text:i18n('i18n.custrepeat.btn_manualDispose'),
						hidden:true,
						handler:function(btn){me.event_manualDispose(btn);}
					},
					{
						xtype:'button',scope:me,
						text:i18n('i18n.custrepeat.btn_queryDetails'),
						handler:function(btn){me.event_queryDetails(btn);}}
				]
			}
			,{xtype:'middlebuttonpanel'}
			,{xtype:'rightbuttonpanel',items:{
				xtype:'button',scope:me,
				text:i18n('i18n.custrepeat.btn_query'),
				handler:function(btn){me.searchEvent(btn);}
			}}
		];
	}
	/*疑似重复客户查询  事件*/
	,searchEvent:function(btn){
		var form = Ext.getCmp('searchRepeatCustFormPanelId').getForm();
		if(form.isValid()==false){return;}
		Ext.getCmp('mainRepeatCustGridId').store.loadPage(1);
	}
	/**
	 * 处理
	 * @param {} btn
	 */
	,event_dispose:function(btn){
		var selection = Ext.getCmp('mainRepeatCustGridId').getSelectionModel().getSelection();
		if(DpUtil.isEmpty(selection) || selection.length != 1){
			MessageUtil.showMessage(i18n('i18n.custrepeat.system.leastOneForOperate'));return;
		}
		
		var form = Ext.getCmp('searchRepeatCustFormPanelId').getForm();
		var currentDept = user.deptId; // 当前处理人所在部门
		var selectDept = selection[0].get('deptId'); // 所选客户所在部门
		
		//所选客户所在部门不是当前处理人部门
		if(currentDept != selectDept){
			MessageUtil.showMessage(i18n('i18n.custrepeat.msg.notCurrentDept'));return;
		}
		
		var win = Ext.getCmp('custRepeatDetailsWindowId');
		if(Ext.isEmpty(win)){
			win = Ext.create('CustRepeatDetailsWindow',{
				title:i18n('i18n.custrepeat.win_rcDispose')/*疑似重复客户处理*/
			});
		}
		win.show();
		Ext.getCmp('groupRepeatCustGridId').getStore().loadPage(1);
	}
	/**
	 * 手动处理
	 * @param {} btn
	 */
	,event_manualDispose:function(btn){
		var win = Ext.getCmp('custRepeatDetailsWindowId');
		if(Ext.isEmpty(win)){
			win = Ext.create('CustRepeatDetailsWindow',{
				title:i18n('i18n.custrepeat.win_rcManualDispose')/*疑似重复客户手动处理*/
			});
		}
		win.show();
	}
	/**
	 * 查询详情
	 * @param {} btn
	 */
	,event_queryDetails:function(btn){
		var selection = Ext.getCmp('mainRepeatCustGridId').getSelectionModel().getSelection();
		if(DpUtil.isEmpty(selection) || selection.length != 1){
			MessageUtil.showMessage(i18n('i18n.custrepeat.system.leastOneForSearch'));return;
		}
		var win = Ext.getCmp('custRepeatDetailsWindowId');
		if(Ext.isEmpty(win)){
			win = Ext.create('CustRepeatDetailsWindow',{
				title:i18n('i18n.custrepeat.win_rcDeDetails')/*疑似重复客户详情*/
			});
		}
		win.setReadOnly();
		Ext.getCmp('groupRepeatCustGridId').getStore().loadPage(1);
		win.show();
	}
});

Ext.onReady(function(){	
	//页面需要加载的数据字典数组
	var keys=[
		'RECKON_WAY',// 结款方式
		'PRIVILEGE_TYPE', // 优惠类型
		'INVOICE_MARK',//发票标记BIxiangm
		'EXPRIVILEGE_TYPE',//快递优惠类型
		'CUSTOMER_TYPE',// 客户类别
		'CUST_TYPE',//客户类型
		'MEMBER_GRADE',//客户等级
		'TRADE',// 客户行业
		'CONTACT_STATUS',// 合同状态
		'CONTRACT_WORKFLOW_STATUS',//合同工作流状态
		'WORKFLOWTYPE',//合同工作流类型
		'CONTRACT_ANNEX'//合同附件
  	];
	//初始数据字典
	initDataDictionary(keys);
	var dutyId =null;
	//初始化提示信息条
	Ext.QuickTips.init();
	
	
	/*
	 * 新建一个viewport，用于显示工单责任查询界面
	 * 肖红叶
	 */
	viewport=Ext.create('Ext.Viewport',{
		layout :{type: 'vbox',align : 'stretch'},
		items:[
			Ext.create('SearchRepeatCustFormPanel'),
			Ext.create('RepeatCustLookBtnPanel'),
			Ext.create('MainRepeatCustGrid',{'flex':'1'})
	    ]
	});
	viewport.setAutoScroll(false);	
	viewport.doLayout();
});

