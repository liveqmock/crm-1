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
var DurrentUserBelongDept = [];

Ext.define('Grid2GridView',{
	extend:'QueryCombox',   //继承common包中封装的
	alias : 'widget.belongdeptcombox',
	initComponent:function(){
		var me = this;
		me.store = me.getCurrentUserDeptListStore();
		this.callParent();
	}
});
