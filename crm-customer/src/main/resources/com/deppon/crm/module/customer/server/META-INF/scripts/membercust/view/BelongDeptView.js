/**
 * 使用说明：xtype:'belongdeptcombox' 使用 所属部门 面控件
 */
BelongDeptView = function(){};
/**
* 当前登陆用户 可切换部门 model
*/
Ext.define('CurrentUserDeptListModel',{
	extend:'Ext.data.Model',
	fields:['deptId','deptName']
});
Ext.define('CurrentDeptListStore',{
	extend:'Ext.data.Store',
	autoLoad :true,
//	url:'../customer/searchMyDataAuth.action',
	model : 'CurrentUserDeptListModel',
    proxy:{
		type:'ajax',
		api:{
			read:'../customer/searchMyDataAuth.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'currentUserDeptList',
			totalProperty:'total'
		}
	}
});
var DurrentUserBelongDept = [];

/**
 *获得所属部门控件
 */
Ext.define('BelongDeptCombox',{
	extend:'QueryCombox',   //继承common包中封装的
//	extend:'Ext.form.ComboBox',
	alias : 'widget.belongdeptcombox',
	fieldLabel:i18n('i18n.ScatterUpgradeView.belongdeptName'),
	displayField:'deptName',  //控件显示的属性值
	valueField:'deptId',      //提交的属性值
	name:'dept',
	minChars:0,//最小自动智能匹配查询长度
	queryParam :'deptName',
	queryMode:'remote',
	functionName :'default',
	forceSelection:true,
	pageSize:10,
	labelWidth:65,
//	typeAhead: true,	//自动填充
	currentUserDeptStore:null,
	listConfig: {
     	minWidth :330   //下拉框显示宽度
//     	minHeight:300	  //下拉框显示高度
	},
	enableKeyEvents:true,
	initComponent:function(){
		var me = this;
		var functionName = me.functionName;
		me.store = me.getCurrentUserDeptListStore( {'functionName':functionName,'combo':me,'pageSize':me.pageSize});
		this.callParent();
	},
	//当前用户权限可切换部门 
	getCurrentUserDeptListStore:function(searchParams){
		var me = this;
		if(me.currentUserDeptStore == null){
			me.currentUserDeptStore= Ext.create('CurrentDeptListStore'
			, {
				pageSize:searchParams.pageSize,
		        listeners:{
		        	beforeload:function(store, operation,eOpts){
		        		Ext.apply(operation,{
								params : {'functionName':searchParams.functionName,'deptName':searchParams.combo.getRawValue()}
							}
						)
		        	}
			
		        }
	    	}
	    	);
		}
	    return me.currentUserDeptStore;
	},
	listeners:{
		change:function(field, newValue) {if (Ext.isEmpty(newValue)) {field.setValue(null);}}
	}
});
