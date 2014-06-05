Ext.require([
	'Ext.panel.*',
	'Ext.form.*',
	'Ext.grid.*',
	'Ext.toolbar.Paging',
	'Ext.util.*',
	'Ext.data.*',
	'Ext.layout.container.Column',
	'Ext.Ajax.*'
]);
Ext.JSON.encodeDate = function(d) {
	return d.getTime();
};

/*---------------------------------gridView的相关功能及元素-----------------------------------*/
/**
 * EXTJS 4.0.7中的TreeStore的load方法BUG的重写实现
 */
Ext.override(Ext.data.TreeStore, {
	load: function(options) {
        options = options || {};
        options.params = options.params || {};
        var me = this,
            node = options.node || me.tree.getRootNode(),
            root;
        if (!node) {
            node = me.setRootNode({
                expanded: true
            });
        }
        if (me.clearOnLoad) {
            //BUG
            //node.removeAll(true);
            node.removeAll(false);
        }
        Ext.applyIf(options, {
            node: node
        });
        options.params[me.nodeParam] = node ? node.getId() : 'root';

        if (node) {
            node.set('loading', true);
        }
        return me.callParent([options]);
    }
});
/**
 * 将选中的员工信息添加到cardPanle中
 */

function changeNullToString(vl){
	if(vl==null){
		return '';
	}else{
		return vl.deptName;
	}
}

function loadFormDataFromDird(record){
	formEmployee.form.setValues(record.data);
	var _deptName = record.data.deptId.deptName;
	formEmployee.form.findField("deptId").setValue(_deptName);
}
/**
 * 员工信息MODEL
 */
var modelEmployee=Ext.define('Employees', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'string'},
        {name: 'deptId',  type: 'Object'},
        {name: 'empCode', type: 'string'},
        {name: 'empName',  type: 'string'},
        {name: 'gender',  type: 'string',convert: changeGenderToString},
        {name: 'position',  type: 'string'},
        {name: 'birthdate',  type: 'Date',convert: changeLongToDate,defaultValue:new Date()},
        {name: 'status',  type: 'string',convert:changeStatusToString},
        {name: 'inDate',type:'Date',convert: changeLongToDate,defaultValue:new Date()},
        {name: 'outDate',  type: 'Date',convert: changeLongToDate,defaultValue:new Date()},
        {name: 'offerTel',  type: 'string'},
        {name: 'offerAddress',  type: 'string'},
        {name: 'offerZipCode',  type: 'string'},
        {name: 'offerEmail',  type: 'string'},
        {name: 'mobileNumber',  type: 'string'},
        {name: 'homeTel',  type: 'string'},
        {name: 'homeAddress',  type: 'string'},
        {name: 'homeZipCode',  type: 'string'},
        {name: 'personEmail',  type: 'string'},
        {name: 'workExp',  type: 'string'},
        {name: 'remark',  type: 'string'}, 
        {name: 'createUser',  type: 'string'},
        {name: 'createDate',  type: 'Date',convert: changeLongToDate,defaultValue:new Date()},
        {name: 'modifyUser',  type: 'string'},
        {name: 'modofyDate',  type: 'Date',convert: changeLongToDate,defaultValue:new Date()}
    ]
});
/**
 * 员工信息Store
 */
var pageStore = Ext.create('Ext.data.Store', {
	pageSize : 10,
	model : 'Employees',
	proxy : {
		type : 'ajax',
		actionMethods :'post',
		url :"findAllEmployee.action",
		reader : {
			type : 'json',
			root : 'employeeList',
			totalProperty : 'totalCount'
		}
	},
   listeners:{
	   // 在pageStore加载前，向其传参
       beforeload: function(store, operation, eOpts){
    	   if(DepartmentTreePanle!=null){
    		   var sm = DepartmentTreePanle.getSelectionModel();
    		   if(sm.getSelection().length > 0){ 
					var dept =sm.getSelection()[0];
					if(dept!=null&&dept.data.id!='root'){
						Ext.apply(operation,{
							params : {
								'employee.deptId.id':dept.raw.entity.id
							}
						});
					}
    		   }
    	   }
    	   if(saveArray!=null){
				Ext.apply(operation,{
					params : {
						'deptName':saveArray.employee.deptName,
						'employee.empCode':saveArray.employee.empCode,
						'employee.empName':saveArray.employee.empName,
						'employee.mobileNumber':saveArray.employee.mobileNumber,
						'employee.position':saveArray.employee.position
					}
				});	
				
			}
		}
   }
});	
/**
 * 员工信息表
 */
var gridEmployee=Ext.create('SearchGridPanel', {
    title: i18n('i18n.organization.girdEmployee'),
    store: pageStore,
    id:'gridEmployee',
    //autoScroll : true,
    defaults: {   
		 flex : 1	 
    },  // 自动添加滚动条
    sortableColumns:false,
    enableColumnHide:false,
    enableColumnMove:false,
    height:'100%',
    columns: [             
        {header: i18n('i18n.organization.Employee.EMPCODE'),width:65, dataIndex: 'empCode'},
        {header: i18n('i18n.organization.Department.deptName'),width:130, renderer : changeNullToString,dataIndex: 'deptId'},
        {header: i18n('i18n.organization.Employee.EMPNAME'), width:68,dataIndex: 'empName'},
        {header: i18n('i18n.organization.Employee.GENDER'),width:50,dataIndex: 'gender'},
        {header: i18n('i18n.organization.Employee.MOBILENO'),width:100, dataIndex: 'mobileNumber'},
        {header: i18n('i18n.organization.Employee.OTEL'),width:100, dataIndex: 'offerTel'},
        {header: i18n('i18n.organization.Employee.POSITION'),dataIndex: 'position'}
     ],
     listeners: {
    	 // 添加员工信息一行的双击事件
    	 itemdblclick: {
             fn: function(){  
            	 var sm = gridEmployee.getSelectionModel(); 
            	 if(sm.getSelection().length > 0){ 
 					 var record =sm.getSelection()[0];
 					 loadFormDataFromDird(record);
 					 windowEmployee.show();
 				 } else {
// 					 top.Ext.Msg.alert(i18n('i18n.organization.message'),i18n('i18n.organization.notSelectGird'));
 					 MessageUtil.showMessage(i18n('i18n.organization.notSelectGird'));
 				 }
             }
			 
         },
         scrollershow: function(scroller) {
         	if (scroller && scroller.scrollEl) {
     			scroller.clearManagedListeners(); 
     			scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
     		}
     	}
    },
    tbar: [{ // 查看员工所有信息按钮
	    	xtype: 'button',
			text: i18n('i18n.organization.viewAll'),
			handler : function(){
				var sm = gridEmployee.getSelectionModel();
				if(sm.getSelection().length > 0){ 
					var record =sm.getSelection()[0];
					loadFormDataFromDird(record);
					windowEmployee.show();
				} else {
					MessageUtil.showMessage(i18n('i18n.organization.notSelectGird'));
				}
			}
	    }
    ],
 	bbar : Ext.create('Ext.toolbar.Paging', {
 		id : 'empGrid_pagingToolbar',
		store : pageStore,
		displayInfo : true,
		displayMsg : i18n('i18n.organization.gridEmployee.displayMsg'),
		items:[{
			text: i18n('i18n.organization.everyPage'),
			xtype: 'tbtext'
		},Ext.create('Ext.form.ComboBox', {
			width:          window.screen.availWidth*0.0391,
			value:          '10',
			triggerAction:  'all',
			forceSelection: true,
			editable:       false,
			name:           'comboItem',
			displayField:   'value',
			valueField:     'value',
			queryMode:      'local',
			store : Ext.create('Ext.data.Store',{
				fields : ['value'],
				data   : [
					{'value':'10'},
					{'value':'15'},
					{'value':'25'},
					{'value':'40'},
					{'value':'100'}
				]
			}),
			listeners:{
			select : {scope : this,
			    fn: function(_field,_value){
			        var pageSize = pageStore.pageSize;
			    	var newPageSize = parseInt(_field.value);
			    	if(pageSize!=newPageSize){		    				          
			    		pageStore.pageSize = newPageSize;		    				
			    		Ext.getCmp('empGrid_pagingToolbar').moveFirst();	    				           
			            	}
			            }
			        }
							}
			}),{
				text: i18n('i18n.organization.tiao'),
				xtype: 'tbtext'
			}]
		})
});
/**
 * 将员工的性别转换成汉字
 */
function changeGenderToString(value){
	if(value==true){
	   return value=i18n('i18n.organization.male');
	}else if(value==false){
	   return value=i18n('i18n.organization.fmale');
	}
}
/**
 * 将员工的状态转换成汉字
 */
function changeStatusToString(value){
	if(value==true){
	   return value=i18n('i18n.organization.onJob');
	}else if(value==false){
	   return value=i18n('i18n.organization.outJob');
	}
}
/**
 * 将数据转换成DATE,在gridEmployee中用到
 */
function changeToDate(value) {
	if (value != null) {
		var date = new Date(value);
		return Ext.Date.format(date, 'Y-m-d');
	} else {
		return null;
	}
};
/**
 * 将数据转换成DATE,在modelEmployee中用到
 */
function changeLongToDate(value){
	if (value != null) {
		var date = new Date(value);
		return date;
	} else {
		return null;
	}
}	
/*------------------------------------树的相关功能级元素-----------------------------------------------------------*/
/**
 * 定义功能树的store
 */
var DepartmentTreeStore = Ext.create('Ext.data.TreeStore',{
	autoSync:true,
	proxy : {
		type : 'ajax',
		url : 'loadTree.action'
	},
	root : {
		//把id为0改成103  得到OA组织架构树
		id : '103',
		text : i18n('i18n.organization.rootAndCompanyName'),
		expanded : true
	},
	sorters : [ {
		property : 'leaf',
		direction : 'ASC'
	} ],  
	listeners:{
		   beforeload: function(store, operation, eOpts){
				if(seqAll!=null){
					Ext.apply(operation.params,{
							'seqAll':seqAll
						}
					);	
				}
			}
	
		
	   }
});
var findDeptCode;
var seqAll=null;
var json=null;
/**
 * 树的面板
 */
var DepartmentTreePanle=Ext.create('Ext.tree.Panel', {
	xtype:'treepanel',
	//autoScroll:true,
	split:true,
	margin:false,  
	height:'100%',
	border : false,
	id:'mainTree', 
	cls:'normaltree',
	store: DepartmentTreeStore,
	rootVisible: true,       
	title: i18n('i18n.organization.deptTree'),
	layoutConfig : {
		// 展开折叠是否有动画效果
		animate : true
	},
	// 监听事件
    listeners: {
    	itemclick : treeLeftKeyAction,
    	itemdblclick:treeDbLeftKeyAction,
        scrollershow: function(scroller) {
        	if (scroller && scroller.scrollEl) {
     			scroller.clearManagedListeners(); 
     			scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
     		}
     	}
    },
	tbar: [{
			xtype : 'textfield',
			id:'tree.name.search',
			fieldLabel : i18n('i18n.organization.name'),
			labelPad : 4,
			labelWidth : 40,
			name : 'businessDept.deptName',
			width:140
		},{
			xtype : 'label',
			width : 3
		},{
			text:i18n('i18n.organization.find'),
			handler:function(){
				seqAll=null;
				var text = Ext.getCmp('tree.name.search').getValue();
				var array = {deptName:text};
				Ext.Ajax.request({
					url :"querySeq.action",
					jsonData:array,
					success : function(response) {    								 
						json = Ext.decode(response.responseText);
						seqAll=json.seqAll;		            								     						
						DepartmentTreeStore.load();		            								 
					}
				});
			}
		},{
			text:i18n('i18n.organization.viewDeptAll'),
			handler:function(){
				var sm = DepartmentTreePanle.getSelectionModel();
				if(sm.getSelection().length > 0){ 
					var dept =sm.getSelection()[0];
					if(dept!=null&&dept.data.id!='root'){
						loadFormDataFromTree(dept);
						windowDept.show();
					}else {
//						top.Ext.Msg.alert(i18n('i18n.organization.message'),i18n('i18n.organization.notSelectTree'));
						MessageUtil.showMessage(i18n('i18n.organization.notSelectTree'));
					}
				}
			}
		}]
});
/**
 * 树节点的左键单击事件
 */

function treeLeftKeyAction(node,record,item,index,e){
	if(record.data.id!='root'){
		selectForm.form.findField("deptName").setValue(record.raw.entity.deptName);
		saveArray=null;
		Ext.getCmp('empGrid_pagingToolbar').moveFirst();
	}
} 
/**
 * 树节点的左键双击事件
 */
function treeDbLeftKeyAction(node,record,item,index,e){
	if(record.data.id!='root'){
		loadFormDataFromTree(record);
		windowDept.show();
	}
} 
/**
 * 将员工的状态转换成汉字
 */
function changeStatusDeptToString(value){
	if(value==true){
	   return value=i18n('i18n.organization.work');
	}else if(value==false){
	   return value=i18n('i18n.organization.unWork');
	}
}




/**
 * 从tree中加载数据到form表单中
 */
function loadFormDataFromTree(record){
	if(record.data.id!='root'){
		record.raw.entity.invalidDate=changeLongToDate(record.raw.entity.invalidDate);
		record.raw.entity.validDate=changeLongToDate(record.raw.entity.validDate);
		record.raw.entity.status=changeStatusDeptToString(record.raw.entity.status);
		formDept.form.setValues(record.raw.entity);
		var _parentName = record.raw.entity.parentCode.deptName;
		if(_parentName==null){
			_parentName=i18n('i18n.organization.rootAndCompanyName');
		}
		formDept.form.findField("parentDeptName").setValue(_parentName);
	}
	
}
/*-----------------------------------SelectForm---------------------------------------*/
// 存储查询表单数据，在Store加载前，传给其参数
var saveArray=null;
/**
 * 查询表单
 */
var selectForm=Ext.create('Ext.form.Panel', {
    //autoScroll:true,
    frame:true,
    height:150,
    defaults: {
    	margin:'4 5 4 5',
        anchor: '100%'
    },
    items: [{
	    xtype:'fieldset',
	    title: i18n('i18n.organization.findMsg'),
	    fieldDefaults : {
			msgTarget : 'side',
			labelAlign : 'left'
		},
		defaultType : 'textfield',
		layout:'column',
		defaults : {
			columnWidth:.47,
			margin : '5 10 5 10',
			anchor : '100%',
			labelWidth:window.screen.availWidth*0.0469
		},
//	    collapsible: true,
	    items :[ {
	    	fieldLabel:  i18n('i18n.organization.Department.deptName'),
	        name: 'deptName',
	        //验证输入的字符串不能大于128个字符
			maxLength:128,
			//大于128个字符的提示信息
			maxLengthText: i18n('i18n.organization.deptNameValidate'),
	        xtype: 'textfield'
	    },{
	        fieldLabel:  i18n('i18n.organization.Employee.EMPCODE'),
	        name: 'empCode',
	        //通过正则表示式验证
			regex:/^[0-9]*$/,
			//正则验证不通过的提示信息
			regexText:i18n('i18n.organization.empCodeValidate'),
	        xtype: 'textfield'
	        
	    },{
	        fieldLabel: i18n('i18n.organization.Employee.EMPNAME'),
	        name: 'empName',
	        //验证输入的字符串不能大于20个字符
			maxLength:20,
			//大于20个字符的提示信息
			maxLengthText: i18n('i18n.organization.empNameValidate'),
	        xtype: 'textfield'
	    },{
	        fieldLabel: i18n('i18n.organization.Employee.POSITION'),
	        name: 'position',
	        //验证输入的字符串不能大于64个字符
			maxLength:64,
			//大于64个字符的提示信息
			maxLengthText: i18n('i18n.organization.positionValidate'),
	        xtype: 'textfield'
	    },{
	        fieldLabel: i18n('i18n.organization.Employee.MOBILENO'),
	        name: 'mobileNumber',
	        //通过正则表示式验证
			regex:/^[0-9]*$/,
			//正则验证不通过的提示信息
			regexText:i18n('i18n.organization.mobileNumberValidate'),
	        xtype: 'textfield'  
	    },{
	        columnWidth:.17,
	        width:120,
	    	autoWidth : true,
	    	xtype:'button',
	        text:i18n('i18n.organization.find'),
	        handler: function(){
	        	var form = this.up('form').getForm();
	        	saveArray = {employee:form.getValues()};
	        	if(chekDate(saveArray.employee.birthdate,saveArray.employee.createDate)&&chekDate(saveArray.employee.inDate,saveArray.employee.outDate))
	        	{
	        		Ext.getCmp('empGrid_pagingToolbar').moveFirst();
	        	}					
	        	
	        }
	    },{
	    	columnWidth:.17,
	    	xtype:'button',
	    	width:120,
	    	text:i18n('i18n.organization.reset'),
	        handler: function(){
	        	this.up('form').getForm().reset();
	        }
	    },{
	    	xtype:'label',
	    	width:window.screen.availWidth*0.0781
	    }]
	}]
});
/**
 * 判断起始日期是否大于终止日期
 */
var chekDate=function(fromDate,toDate){
	if(fromDate!=null&&toDate!=null&&fromDate!=""&&toDate!=""){
		if(fromDate>=toDate){
//			top.Ext.Msg.alert(i18n('i18n.organization.fromDatetoDate')); 
			MessageUtil.showMessage(i18n('i18n.organization.fromDatetoDate')); 
			return false;
		}
	}
	return true;
};
/*---------------------------------部门弹窗-----------------------------------*/
/**
 * 部门信息查询form表单
 */
var formDept=top.Ext.create('Ext.form.Panel', {
    autoScroll:true,
    border:false,
    height:285,
    defaults: {
    	columnWidth:.47,
    	margin:'5 10 5 10',
        anchor: '100%'
    },
    layout:'column',
    items:[{
	        fieldLabel: i18n('i18n.organization.Department.id'),
	        name: 'id',
	        hidden:true,
	        readOnly:true
	    },{
			name: 'deptCode',
	        fieldLabel: i18n('i18n.organization.Department.deptCode'),
	        xtype : 'textfield',
	        readOnly:true
		},{
			name: 'deptName',
	        fieldLabel: i18n('i18n.organization.Department.deptName'),
	        xtype : 'textfield',
	        readOnly:true
		},{
	        xtype: 'textfield',
	        fieldLabel: i18n('i18n.organization.Department.principal'),
			name: 'principal',
			readOnly:true
			
		},{
			name: 'phone',
	        fieldLabel: i18n('i18n.organization.Department.phone'),
	        xtype : 'textfield',
	        readOnly:true
		},{
			name: 'fax',
	        fieldLabel: i18n('i18n.organization.Department.fax'),
	        xtype : 'textfield',
	        readOnly:true
		},{
	        xtype: 'textfield',
	        fieldLabel: i18n('i18n.organization.Department.parentName'),
	        name:'parentDeptName',
	        readOnly:true
		},{
	        xtype: 'textfield',
	        fieldLabel: i18n('i18n.organization.Department.companyName'),
			name: 'companyName',
			readOnly:true
			
		},{
			name: 'zipCode',
	        fieldLabel: i18n('i18n.organization.Department.zipCode'),
	        xtype : 'textfield',
	        readOnly:true
		},{
			name: 'address',
	        fieldLabel: i18n('i18n.organization.Department.address'),
	        xtype : 'textfield',
	        width: window.screen.availWidth*0.2344,
	        readOnly:true
	        	
		},{
			name: 'status',
			fieldLabel: i18n('i18n.organization.Department.status'),
			xtype:'textfield',
			readOnly:true
		},{
			name: 'validDate',
			fieldLabel: i18n('i18n.organization.Department.validDate'),
			xtype:'datefield',
			format : 'Y-m-d',
			readOnly:true
		},{
			name: 'invalidDate',
			fieldLabel: i18n('i18n.organization.Department.invalidDate'),
			xtype:'datefield',
			format : 'Y-m-d',
			readOnly:true
		},{
			name: 'displayOrder',
	        fieldLabel: i18n('i18n.organization.Department.displayOrder'),
	        xtype : 'textfield',
	        hidden:true,
	        readOnly:true
		},{
			name: 'deptDesc',
	        fieldLabel: i18n('i18n.organization.Department.deptDesc'),
	        xtype : 'textarea',
	        columnWidth:.94,
	        readOnly:true
		}]
	    
});
/**
 * 部门信息查询窗口
 */
var windowDept = top.Ext.create('PopWindow', {
	closeAction : 'hide',
    title: i18n('i18n.organization.deptMsg'),
    resizable:false,
    height:380,
    width:730,
    modal:true,
    items: [formDept],
    listeners : {
    	beforehide : function(win, Opts){
    		formDept.getForm().reset();
    	}
    },
    buttons: [{
    	text:i18n('i18n.organization.close'),
        handler: function(){
        	windowDept.hide();
        }
    }]
});
/*---------------------------------部门弹窗-----------------------------------*/
/**
 * 员工信息查询form表单
 */
var formEmployee=top.Ext.create('Ext.form.Panel', {
    autoScroll:true,
    border:false,
    height:465,
    defaults: {
    	columnWidth:.5,
    	margin:'5 10 5 10',
        anchor: '100%'
    },
    layout:'column',
    items:[{
        fieldLabel:i18n('i18n.organization.Department.id'),
        name: 'id',
        hidden:true,
        readOnly:true
    },{
        fieldLabel:  i18n('i18n.organization.Employee.EMPCODE'),
        name: 'empCode',
        xtype: 'textfield',
        readOnly:true     
    },{
        fieldLabel: i18n('i18n.organization.Employee.EMPNAME'),
        name: 'empName',
        xtype: 'textfield',
        readOnly:true
    },{
        fieldLabel: i18n('i18n.organization.Employee.GENDER'),
        name: 'gender',
		xtype:'textfield',
		readOnly:true
    },{
    	fieldLabel:  i18n('i18n.organization.Employee.deptId'),
        name: 'deptId',
        xtype: 'textfield',
        readOnly:true
    },{
        fieldLabel: i18n('i18n.organization.Employee.POSITION'),
        name: 'position',
        xtype: 'textfield',
        readOnly:true
        
    },{
    	xtype: 'datefield',
	    name: 'birthdate',
	    fieldLabel: i18n('i18n.organization.Employee.DATE'),
	    format: 'Y-m-d',
	    readOnly:true
	      
    },{
    	name: 'status',
		fieldLabel: i18n('i18n.organization.Employee.STATUS'),
		xtype:'textfield',
		readOnly:true
	  
    },{
        xtype: 'datefield',
        name: 'inDate',
        fieldLabel: i18n('i18n.organization.Employee.INDATE'),
        format: 'Y-m-d',
        readOnly:true
    }, {
    	xtype: 'datefield',
        name: 'outDate',
        fieldLabel: i18n('i18n.organization.Employee.OUTDATE'),
        format: 'Y-m-d',
        readOnly:true
	}, {
		fieldLabel : i18n('i18n.organization.Employee.OTEL'),
		name : 'offerTel',
		xtype: 'textfield',
		readOnly:true
		
	},{
        fieldLabel: i18n('i18n.organization.Employee.OADDRESS'),
        name: 'offerAddress',
        xtype: 'textfield',
        readOnly:true
        
    },{
        fieldLabel: i18n('i18n.organization.Employee.OZIPCODE'),
        name: 'offerZipCode',
        xtype: 'textfield',
        readOnly:true
        
    },{
        fieldLabel: i18n('i18n.organization.Employee.OEMAIL'),
        name: 'offerEmail',
        xtype: 'textfield',
        readOnly:true
        
    },{
        fieldLabel: i18n('i18n.organization.Employee.MOBILENO'),
        name: 'mobileNumber',
        xtype: 'textfield',
        	readOnly:true
        
    },{
        fieldLabel: i18n('i18n.organization.Employee.HTEL'),
        name: 'homeTel',
        xtype: 'textfield',
        readOnly:true
        
    },{
        fieldLabel: i18n('i18n.organization.Employee.HADDRESS'),
        name: 'homeAddress',
        xtype: 'textfield',
        readOnly:true
        
    },{
        fieldLabel: i18n('i18n.organization.Employee.HZIPCODE'),
        name: 'homeZipCode',
        xtype: 'textfield',
        readOnly:true
        
    },{
        fieldLabel: i18n('i18n.organization.Employee.PEMAIL'),
        name: 'personEmail',
        xtype: 'textfield',
        readOnly:true
        
    },{
        fieldLabel: i18n('i18n.organization.Employee.WORKEXP'),
        name: 'workExp',
        xtype: 'textarea', 
        columnWidth:1,
        readOnly:true
        
    },{
        fieldLabel: i18n('i18n.organization.Employee.REMARK'),
        name: 'remark',
        xtype: 'textarea',
        columnWidth:1,
       readOnly:true 
    }]
});
/**
 * 员工信息查询窗口
 */
var windowEmployee = top.Ext.create('PopWindow', {
	fbar:null,
	closeAction : 'hide',
    title: i18n('i18n.organization.employeeMsg'),
    resizable:false,
    height:550,
    width:750,
	buttons: [{
		text:i18n('i18n.organization.close'),
	    handler: function(){
	    	windowEmployee.hide();
	    }
	}],
    items: [formEmployee],
    modal :true,
    listeners : {
    	beforehide : function(win, Opts){
    		formEmployee.getForm().reset();
    	}
    }
});
/*---------------------------------启动项-------------------------------------------*/
/**
 * 启动加载的页面元素及布局
 */
Ext.onReady(function() {
	document.title = i18n('i18n.organization.page_title');
	
//	document.getElementById('message_waiting').innerHTML = i18n('i18n.organization.message_waiting');
//	setTimeout(function() {
//		Ext.get('loading').remove();
//		Ext.get('loading-mask').fadeOut({
//			remove : true
//		});
//	}, 950);
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = "side";
	Ext.create('Ext.Viewport', {
		layout:'border',
		defaults: {   
			anchor: '100%'
		},
		items: [{
			region : 'west',
			width:290,
			split:true,
			autoScroll:true,
			//layout : 'accordion',
			margin:'5 5 0 0',
			items:[DepartmentTreePanle]
		},{
			region : "center",
			baseCls:'x-plain',
			margin:'5 3 0 0',
			layout:'border',
			items:[{
				region : 'north',
				baseCls:'x-plain',
				items:[selectForm]
			}, {
				region : "center",
				margin:'5 0 0 0',
				baseCls:'x-plain',
				items:[gridEmployee]
			}]
		}]
	});
});