/**
 * 疑似重复客户查询面板
 */
Ext.define('SearchCustomerFormPanel',{
	extend:'SearchFormPanel',
	id:'searchCustomerFormPanelId',
	border:0,
	layout:'column',
	padding:'5 5 0 0',
	defaults:{
		labelWidth:75,
		labelAlign:'right'
	},
	defaultType:'dptextfield',
	initComponent:function(){		
		Ext.applyIf(this,{
			items:this.getItems()
		});
		this.callParent(arguments);
	},
	getItems:function(){
		var me  = this;
		return [
			{fieldLabel:i18n('i18n.custrepeat.custCode')/*客户编码*/,columnWidth:0.5,name:'searchCondition.custCode'}
			,{
				xtype:'button',margin:'0 0 0 5',
				text:i18n('i18n.custrepeat.btn_query'),
				handler:function(btn){me.searchEvent(btn);}
			}
		];
	}
	/*疑似重复客户查询  事件*/
	,searchEvent:function(btn){
		var form = Ext.getCmp('searchCustomerFormPanelId').getForm();
		var custCode = form.findField('searchCondition.custCode').getValue();
		if(Ext.isEmpty(custCode)){
			MessageUtil.showMessage(i18n('i18n.custrepeat.system.custCode_isNull'));return;
		}
		Ext.getCmp('customerGridId').store.loadPage(1);
	}
});

/**
 * 一组疑似重复客户
 */
Ext.define('CustomerGrid',{
	extend:'SearchGridPanel',
	id:'customerGridId',
	defaults:{align:'center'},
	autoScroll:true,
	initComponent:function(){ 
		var me = this;
		if(DpUtil.isEmpty(me.selModel)){
			me.selModel = new Ext.selection.CheckboxModel({mode:'single'});
		}
		me.store = Ext.create('SearchCustomerListStore',{
			'listeners':{
				beforeload:function(store,operation,e){		
					var form = Ext.getCmp('searchCustomerFormPanelId').getForm();
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
		    },{//所属部门
				header :i18n('i18n.custrepeat.deptName'),
				dataIndex:'deptName',flex:1
		    },{//客户优先级
				header :i18n('i18n.custrepeat.custPriorityLevel'),
				dataIndex:'custGroup',width:150,renderer:function(value,metaData,record){
					if(record.get('isBigcustomer') == 1){
						return '大客户';
					}
					if(record.get('isContractCust') > 0){
						return '合同客户';
					}
					var html = rendererDictionary(value,DataDictionary.CUST_TYPE);
					if(value === 'RC_CUSTOMER'){//固定客户
						var custLevel = rendererDictionary(record.get('custLevel'),DataDictionary.MEMBER_GRADE);
						html = html + '('+custLevel+')';
					}
					return html;
				}
		    }
	    ];
		this.callParent(arguments);
   }
   ,listeners:{
	   itemdblclick:function(view,record){
   			var flag = false;
   			var store = Ext.getCmp('groupRepeatCustGridId').getStore();
   			store.each(function(rcd){
   				if(rcd.get('custId') == record.get('custId')){
   					flag = true;return false;
   				}
   			});
   			if(flag === true){
   				MessageUtil.showMessage(i18n('i18n.custrepeat.system.custIsExist'));return;
   			}
   			store.add(record);
   			Ext.getCmp('addCustomerWinId').close();
   		}
   }
});


/**
 * 疑似重复客户详情window
 */
Ext.define('CustomerSearchWindow', {
    extend: 'PopWindow'
   //,y:5,x:1,
    ,width:600,height:400
    ,id:'addCustomerWinId'
	,title:i18n('i18n.custrepeat.newAddCust')
	,layout :{type: 'vbox',align : 'stretch'}
	,bbar:[
		'->',{
			xtype:'button',text:i18n('i18n.custrepeat.btn_close'),handler:function(btn){
				Ext.getCmp('addCustomerWinId').close();
		}}
	]
    ,initComponent: function() {
    	var me  = this;
        Ext.applyIf(me,{
        	items:[
        		Ext.create('SearchCustomerFormPanel'),
        		Ext.create('CustomerGrid',{'flex':1})
        	]
        });
        me.callParent(arguments);
    }
    /* 添加滚动条 修改 begin */
	,'closeAction':'destroy'
	,listeners:{
		destroy:function(){
			if(viewport && viewport!=null){
				document.body.scrollLeft=0;
				document.body.scrollTop=0;
				document.getElementsByTagName("html")[0].style.overflowY="hidden";
				document.getElementsByTagName("html")[0].style.overflowX="hidden";
				viewport.doLayout();
			}
		},
		beforeclose:function(){
			Ext.getCmp('customerGridId').getStore().removeAll();
		},
		show:function(win){
			//创建回调函数调用
			if(win.myCallBack){win.myCallBack();}
			if(viewport && viewport!=null){
				document.getElementsByTagName("html")[0].style.overflowY="auto";
				document.getElementsByTagName("html")[0].style.overflowX="hidden";
				viewport.doLayout();
			}
		}
	}
	/* 添加滚动条 修改 end */
});