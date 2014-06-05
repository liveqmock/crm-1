/**
 * <p>
 * 人员选择器
 * </p>
 * @author  张登
 * @date    2012-05-11
 * 默认调用方法为 Ext.create('Ext.lookup.EmployeeLookup',{id:'employeelookupId',name:'planeName',width:300})
 * 初始化人员信息为Ext.getCmp("EmployeelookupId").initData(User); 其中User为当前登录用户
 * 如果想获取文本框的内容方法：Ext.getCmp("EmployeelookupId").getValue();
 * 获取对应的ID方法：Ext.getCmp("EmployeelookupId").getValueId()
 * 获取对应的UserID方法：Ext.getCmp("EmployeelookupId").getUserId()
 * 如果有自定义需求可以create EmployeeLookUpWindow 来进行弹出框,需要实现select方法
 */

/**
 * 查询人员form
 */
Ext.define('EmployeeQueryForm',{
	extend:'Ext.form.Panel',
	layout : {
		type : 'table',
		columns : 5
	},
	defaultType : 'textfield',
	defaults : {
		labelWidth : 40,
		margin:'5px 5px 5px 5px'
	},
	items:[{
		fieldLabel : '姓名',
		width:160,
		name:'empName',
		listeners : {   
            specialkey : function(th, e) {   
                if (e.getKey() == Ext.EventObject.ENTER) {   
                	Ext.getCmp("EmployeeLookUpGridId").store.loadPage(1);
                } 
            }
        }
	},{
		fieldLabel : '工号',
		width:110,
		name:'empCode',
		listeners : {   
            specialkey : function(th, e) {   
                if (e.getKey() == Ext.EventObject.ENTER) {   
                	Ext.getCmp("EmployeeLookUpGridId").store.loadPage(1);
                }   
            }   
        }
	},{
		fieldLabel : '部门',
		width:180,
		name:'deptName',
		listeners : {   
            specialkey : function(th, e) {   
                if (e.getKey() == Ext.EventObject.ENTER) {   
                	Ext.getCmp("EmployeeLookUpGridId").store.loadPage(1);
                }   
            }   
        }
	},{
		xtype:'hiddenfield',
		width:8
	},{
		xtype:'button',
		width:60,
		text:'查询',
		handler : function(){
			Ext.getCmp("EmployeeLookUpGridId").store.loadPage(1);
		}
	}]
});

Ext.define('EmployeeLookUpMode', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'empId'},
        {name: 'userId'},
        {name: 'empName'},
        {name: 'empCode'},
        {name: 'deptName'},
        {name: 'mobilePhone'},
        {name: 'position'}
    ]
});

/**
 * 声明人员选择器store
 */
Ext.define('EmployeeLookUpStore',{
	model: 'EmployeeLookUpMode',
	extend : 'Ext.data.Store',
	pageSize : 10,
//	autoLoad: true,
    proxy: {
        type: 'ajax',
        url : '../common/searchEmpByCondition.action',
		actionMethods : 'post',
        reader: {
            type: 'json',
            root: 'users',
			totalProperty : 'totalCount'
        }
    }
});

/**
 * 声明grid
 */
Ext.define('EmployeeLookUpGrid',{
	extend:'Ext.grid.Panel',
	border: false,
    columns: [
        { header: '工号',  dataIndex: 'empCode', flex: 0.2 },
        { header: '姓名', dataIndex: 'empName', flex: 0.4 },
        { header: '部门名称', dataIndex: 'deptName', flex: 0.4 },
        { header: '手机', dataIndex: 'mobilePhone', width:110 },
        { header: '职位', dataIndex: 'position', width:140 }
    ]
});

/**
 * 声明window
 */
Ext.define('EmployeeLookUpWindow',{
	extend:'Ext.window.Window',
    height: 350,
    width: 600,
    modal:true,
    ischeckbox:false,
    layout: 'border',
    initComponent: function(){
    	var me=this;
    	var store=Ext.create('EmployeeLookUpStore');
    	store.on('beforeload',function(store,operation,e){
    		var EmployeeQueryForm = Ext.getCmp("EmployeeQueryFormId");
    		var searchParams = { 
    			'searchEmpAndDeptCondition.empCode':EmployeeQueryForm.getForm().findField('empCode').getValue(),
    			'searchEmpAndDeptCondition.empName':EmployeeQueryForm.getForm().findField('empName').getValue(),
    			'searchEmpAndDeptCondition.deptName':EmployeeQueryForm.getForm().findField('deptName').getValue()
    		};
    		Ext.apply(operation,{
				params : searchParams
			});
    	});
    	this.items=[
    	    Ext.create('EmployeeQueryForm',{
    	    	id:'EmployeeQueryFormId',
    	    	region:'north'
    	    }),
    	    Ext.create('EmployeeLookUpGrid',{
    	    	id:'EmployeeLookUpGridId',
    	    	store:store,
    	    	selModel:this.ischeckbox==true?Ext.create('Ext.selection.CheckboxModel',{mode:'SIMPLE'}):null,
    	    	region:'center',
    	    	listeners:{
    	        	itemdblclick:function(th,record,item,index,e,eop){
    	            	me.fireEvent('select', me,record);
    	        	}
    	        },
  				dockedItems:[{
  					xtype:'pagingtoolbar',
  					store:store,
  					dock:'bottom',
  					displayInfo : true,
  					autoScroll : true
  				}]
    	    })
    	]
        this.callParent(arguments);
    }
});

/**
 * <p>
 * 人员选择器
 * </p>
 * @author  张登
 * @date    2012-05-11
 * 由于订单那块的用户id都使用的是userId，所以把userId加进来，新增get/setUserId方法
 * 初始化值时调用initData();
 */
Ext.define('Ext.ux.form.SearchField', {
    extend: 'Ext.form.field.Trigger',
    alias: 'widget.employeelookup',
    alternateClassName: ['Ext.lookup.EmployeeLookup'],
    trigger1Cls: Ext.baseCSSPrefix + 'form-clear-trigger',
    trigger2Cls: Ext.baseCSSPrefix + 'form-search-trigger',
    valueId:'',
    userId:'',
    emptyText:'请输入......',
    initComponent: function(){
        this.callParent(arguments);
        this.on('specialkey', function(f, e){
            if(e.getKey() == e.ENTER){
                this.onTrigger2Click();
            }
        }, this);
    },
    afterRender: function(){
        this.callParent();
        this.triggerEl.item(0).setDisplayed('none');  
    },
    getValueId:function(){
    	var me = this;
    	return me.valueId;
    },
    setValueId:function(val){
    	var me = this;
    	me.valueId=val;
    	return me;
    },
    getUserId:function(){
    	var me = this;
    	return me.userId;
    },
    setUserId:function(val){
    	var me = this;
    	me.userId=val;
    	return me;
    },
    onTrigger1Click : function(){
        var me = this;
        me.setValue('');
        me.setUserId('');
        me.valueId='';
        me.userId='';
        me.triggerEl.item(0).setDisplayed('none');
        me.doComponentLayout();
    },
    initData:function(val){
    	var me = this;
    	if(!Ext.isEmpty(val)){
    		if(!Ext.isEmpty(val.empName)){
    			me.setValue(val.empName);
    		}
    		if(!Ext.isEmpty(val.userId)){
    			me.setValueId(val.userId);
    			me.setUserId(val.userId);
    		}
    	}
    },
    onTrigger2Click : function(){
    	var me = this;
    	Ext.create('EmployeeLookUpWindow',{
    		listeners:{
    			select:function(win,record){
    				me.setValue(record.get("empName"));
	        		me.setValueId(record.get("empId"));
	        		me.setUserId(record.get("userId"));
	        		me.fireEvent('select', me,record);
	        		win.close();
    			}
    		}
    	}).show();
        me.triggerEl.item(0).setDisplayed('block');
        me.doComponentLayout();
    }
});
