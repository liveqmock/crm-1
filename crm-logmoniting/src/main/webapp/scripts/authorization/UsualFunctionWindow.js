/**
 * usualFunction的Model
 */
Ext.define('UsualFunctionModel', {
	extend : 'Ext.data.Model',
	border:false,
	fields : [ {
		name : 'fId',
		type:'int'
	}, {
		type : 'int',
		name : 'noteid'
	}, {
		name : 'noteurl'
	}, {
		name : 'notename'
	}, {
		name : 'userid',
		type : 'int'
	} ]
});

// 已分配菜单store
var UsualFunctionGridStore = Ext.create('Ext.data.Store', {
	pageSize : 20,
	model : 'UsualFunctionModel',
	remoteSort : true,
	border:false,
	autoLoad : true,
	proxy : {
		type : 'ajax',
		actionMethods : 'POST',
		url : '../authorization/findAuthedUsualFunction.action',
		reader : {
			type : 'json',
			root : 'chooesUsualFunctons',
			totalProperty : 'totalCount'
		}
	}
});

/**
 * 已分配菜单选择列表
 */
var UsualFunctionGrid = Ext.create('Ext.grid.GridPanel', {
	title : i18n('i18n.usualFunctionWindow.existsMenu'),
	cls:'funtitle',
	sortableColumns:false,
    enableColumnHide:false,
    enableColumnMove:false,
    height :390,
	width : 230,  
	border:false,
    autoScroll : true,
	stripeRows : true, // 交替行效果
	selType : "rowmodel", // 选择类型设置为：行选择
	store : UsualFunctionGridStore,
	selModel : Ext.create("Ext.selection.CheckboxModel"),
	// 列模板
	columns : [{
		text : i18n('i18n.authorization.role.roleName'),
		flex:1,
		dataIndex : 'notename'
	} ]
});

/**
 * 移动按钮
 */
var buttonPanelRole = Ext.create('Ext.panel.Panel',{
	height :390,
	border:false,
	width : 50,
	buttonAlign : 'center',
	layout : 'column',
	items : [{
			columnWidth : 1,
			height : 0,
			xtype : 'container',
			style : 'padding-top:120px;border:none',
			width : '100%',
			hide:true
		},
		{
			columnWidth : 1,
			width : '100%',
			xtype : 'container',
			style : 'padding-top:20px;padding-left:3px;border:none',
			items : [ {
				xtype : 'button',
				text : '&nbsp;&gt;&nbsp;',
				width : '80%',
				// 添加菜单
				handler : function(){
					selectItem(FunctionTree,UsualFunctionGridStore);
				}
			} ]
		},
		{
			columnWidth : 1,
			width : '100%',
			xtype : 'container',
			style : 'padding-top:20px;padding-left:3px;border:none',
			items : [ {
				xtype : 'button',
				text : '&nbsp;&lt;&nbsp;',
				width : '80%',
				// 删除选中角色
				handler : function(){
					deSelectItem(UsualFunctionGrid,UsualFunctionGridStore);
				}
			} ]
		}
	]
});

/** **************************************功能区******************************************* */
/**
 * 选中角色列表中的一行或多行移动至另一个角色列表中
 * @param grid 选中的列表
 * @param addStore 增加记录的store
 * @name selectItem
 */
function selectItem(grid,addStore) {
	var _records = grid.getSelectionModel().getSelection();
	if (_records == null||_records.length<=0) {
		MessageUtil.showMessage(i18n('i18n.authorization.operation_messages'));
		return;
	}
	
	var record =_records[0];
	// 如果父节点为空，那么当前节点就是功能树的根节点，不可以做修改
	if (record.raw.entity.parentCode == null) {
		return;
	}	
	if(record.get("leaf")==false){//不是叶子
		MessageUtil.showMessage(i18n('i18n.usualFunctionWindow.donotAddDirectory'));
		return;
	}
	if(addStore.data.length>=5){
		MessageUtil.showMessage(i18n('i18n.usualFunctionWindow.haveFullMenu'));
		return;
	}
	
	var id = record.raw.entity.id;
	var functionName=record.raw.entity.functionName;
	var uri=record.raw.entity.uri;
	
	var usualFunctionModel=new UsualFunctionModel();
	usualFunctionModel.data.noteid=id;
	usualFunctionModel.data.notename=functionName;
	usualFunctionModel.data.noteurl=uri
	
	//判断grid中是否已经有此菜单
	var isExistsFunction=false;
	addStore.each(function(record){
	    var notename=record.data.notename;
	    if(notename==functionName){
	    	isExistsFunction=true;
	    	MessageUtil.showMessage(i18n('i18n.usualFunctionWindow.haveThisMenu'));
	    	return false;
	    }
	});
	if(!isExistsFunction){//如果不存在则添加
		addStore.insert(0,usualFunctionModel);
	}
};
/**
 * 去除选择菜单
 * @param {} grid
 * @param {} deleteStore
 */
function deSelectItem(grid,deleteStore){
	var _records = grid.getSelectionModel().getSelection();
	if (_records == null||_records.length<=0) {
		MessageUtil.showMessage(i18n('i18n.authorization.operation_messages'));
		return;
	}
	Ext.each(_records, function(item) {
		deleteStore.remove(item);
	});
}

var UsualFunctionTab = Ext.create('Ext.panel.Panel', {
	plain : true,
	id:'tab',
	border:false,
	items : [ {
		layout : 'border',
		height:390,
		border:false,
		items : [ {
			region : "west",
			width:235,
			border:true,
			cls:'funtitle',
	    	//layout : 'accordion',
			items : [ FunctionTree ]
		}, {
			region : "center", // 中间布局
			border:false,
	    	items:[buttonPanelRole]
	    }, {
	       	region : "east",
	       	width:230,
	       	border:true,
   			items:[UsualFunctionGrid]
		}]
	}]
});

/**
 * 常用菜单编辑窗口
 */
var UsualFunctionWindow = Ext.define('UsualFunctionWindow',{
	id:'usualFunctionWindow',
	extend:'Ext.window.Window',
	closable : true,
	modal : true,
	resizable:false,
	closeAction : 'hide',
	cls:'popup',
	layout : 'border',
	title : i18n('i18n.usualFunctionWindow.usualFunctionModel'),
	height:475,
    width:548,
    border:false,
    initComponent:function(){
    	var me = this;
		this.items = this.getItems();
		me.fbar =  me.getFbar();
		this.callParent();
	},
	getItems:function(){
		return [{
			region : "center", // 中间布局
			border:false,
			items : [ UsualFunctionTab ]
		} ];
	},
	getFbar:function(){
		return [ {
		   	text:i18n('i18n.usualFunctionWindow.ok'),handler:function(){
		   		var chooesUsualFunctons=new Array();
				 	UsualFunctionGrid.getStore().each(function(record){
				    	chooesUsualFunctons.push(record.data);
				    });
				    
				    var params={};
				    params.chooesUsualFunctons=chooesUsualFunctons;
					//调用后台进行数据提交
			    	Ext.Ajax.request({
						url : '../authorization/saveUsualFunction.action',
						jsonData:params,
						success : function(response) {
		 					Ext.getCmp("usualFunctionWindow").close();
		 					MessageUtil.showMessage(i18n('i18n.usualFunctionWindow.saveSuccess'),function(){
		 						Ext.getCmp('treeID').getStore().load();
		 					});
						},
						failure : function(response) {
							MessageUtil.showMessage(i18n('i18n.usualFunctionWindow.saveFailure'));
						}
					});
			    }
			}
			,{text:i18n('i18n.usualFunctionWindow.cancel'),handler:function(){
				Ext.getCmp("usualFunctionWindow").close();
			}}]
	}
});