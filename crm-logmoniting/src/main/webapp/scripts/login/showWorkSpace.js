var tabPanel = Ext.create('Ext.tab.Panel', {
	border : false,
	id:'tabpanel',
	plugins: Ext.create('Ext.ux.TabCloseMenu',{closeTabText:'关闭当前',closeOthersTabsText:'关闭其它',closeAllTabsText:'关闭所有'}),
	cls:'tab_content',
	xtype:'tabpanel',
	region:'center'
});
var ActiveID = parent.window.tabPanelTitle.activeTab.id;
var bj=!Ext.isEmpty(top.JumpData);//点击连接不存在
Ext.onReady(function() {
	var autoWidth=0;
	var helpWin;
	var tree = new Ext.tree.Panel({
		rootVisible : true,
		cls :'lefttreepanel',
		useArrows: true,
		minWidth : 171,
		maxWidth : 171,	
		width : 171,
		id:'treeID',
		flex:0.6,
		region:'center',
		root : {
			id : parent.window.tabPanelTitle.activeTab.id,
			text:parent.window.tabPanelTitle.activeTab.title,
			expanded : true
		},
		listeners:{
			itemclick:function(th,record,item,index,e,eop){
				if(!record.data.leaf && !record.data.root){
					if(!record.get('expanded')){
						th.expand(record);
					}else{
						th.collapse(record);
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
		store : Ext.create('Ext.data.TreeStore', {
			proxy : {
				type : 'ajax',
				actionMethods : 'POST',
				url : 'loadTree.action',
				reader : {
					type : 'json',
					root : 'functions'
				}
			},
			listeners:{
				load:function(th,node,records){
					autoWidth=autoWidth+records.length;
					var h=(parseInt(autoWidth)*31)+65;
					if(document.body.clientHeight-h<=223){
						Ext.getCmp("commonPanel").setHeight(223);
					}else{
						Ext.getCmp("commonPanel").setHeight(document.body.clientHeight-h);
					}
					if(bj==false){
						var obj = tabPanel.getComponent(records[0].data.id+'tab');
						if(!obj){
							if(records[0].data.leaf){
								clickTabpanel(records[0].data.id,records[0].data.text,records[0].raw.entity.uri);
								bj=true;
							}else{
								tree.expandPath('/'+records[0].data.parentId+'/'+records[0].data.id+'');
							}							
						}
					}
				}
			}
		})
	});
	var indexTreeStore = Ext.create('Ext.data.TreeStore', {
		proxy : {
			type : 'ajax',
			actionMethods : 'POST',
			params:{'node':parent.window.tabPanelTitle.activeTab.id},
			url : '../authorization/loadUsualFunctionTree.action',
			reader : {
				type : 'json'
			}
		}
	});
	
	var commonTree = Ext.create('Ext.tree.TreePanel', {
		id:'treeID2',
		store : indexTreeStore,
		rootVisible : false,
		region : 'center',
		singleExpand:true,
		collapsible : true,
		width : 171,
		cls :'lefttreepanel',
		minWidth : 171,
		maxWidth : 171,
		listeners : {
			itemclick : function(node, record,item,index,e,eOpts){
				if (record.get("leaf")) {
					var child = node.getSelectionModel().getSelection()[0];
					clickTabpanel(child.raw.entity.functionCode,record.data.text,child.raw.entity.uri);
				}
			},
			itemexpand:function(){
				return false;
			}
		}
	});
	
	// 给tree添加鼠标点击事件
	tree.on("itemclick", treeClickAction, this);
	Ext.create(
	'Ext.container.Viewport',{
		autoDestroy : true,
		layout : 'border',
		items : [{
			layout : 'border',
			region:'west',
			id:'treelayoutID',
			split : true,
			minWidth : 171,
			maxWidth : 171,	
			collapsible : true,
			width:171,
			border:false,
			items:[tree,{
				layout:'border',
				region:'south',
				border:false,
				id:'commonPanel',
				items:[commonTree,{
					xtype:'button',
					cls:'helpbtn',
					height:33,
					region:'south',
					text:'<a style="color:#373c64;font-size:14px;font-weight:bold;">查看帮助文档</a>',
					handler:function(){
						Ext.create('HelpWindow',{
							id:'helpWindowId',
							width:785,
							height:590,
							cls:'helpwin',
							title:'帮助文档',
							html:'<iframe id="'+tabPanel.activeTab.id+'" src="../login/help.action?htmlName='+tabPanel.activeTab.id+'" style="height:100%;width:100%;" frameborder="0"></iframe>'
						}).show();
					}
				}]
			}]
		},tabPanel]
	});
	toDo();
	
	/**
	 * 显示帮助Window
	 */
	Ext.define('HelpWindow',{
		extend:'PopWindow',
		alias : 'widget.basicwindow',
		width:820,
		height:700,
		modal:true,
		layout:'fit'
	});
//	var helpWin=Ext.getCmp("helpWindowId");//获取win
//	if(!helpWin){
//		helpWin=
//	}
	
	if(!Ext.isEmpty(parent.window.tabPanelTitle.activeTab.id)){
		parent.Ext.getDom(parent.window.tabPanelTitle.activeTab.id).style.display="block";	
	}
});
function toDo(){
	if(!Ext.isEmpty(top.JumpData)){
		var jumpData = top.JumpData;
		var innerTabs = Ext.getCmp('tabpanel');
		if (innerTabs) {
	        var n = innerTabs.getComponent(jumpData.functionId+'tab');
	        innerTabs.remove(n);
	        //判断是否已经打开该面板   
	            n = innerTabs.add({
	                id: jumpData.functionId+'tab',
	                title: jumpData.title,
	                closable: true,  
	                layout: 'fit',
	                html: '<iframe id="'+id+'frame" src="'+ '/'+ getAppName()+jumpData.url+'" style="height:100%;width:100%;" frameborder="0"></iframe>'
	            });
	        innerTabs.setActiveTab(n);
	    }
		top.JumpData = null;
	}
}
/**
 * 得到应用名
 * @returns
 */
function getAppName() {
	var str = (window.location.pathname).split("/");
	return str[1];
}


	
////////////////////////树的事件////////////////////////////////

function treeClickAction(node, record, item, index, e) {
	e.preventDefault();// 阻止浏览器默认行为处理事件
	var child = node.getSelectionModel().getSelection()[0];
	if(!Ext.isEmpty(child)){
		if (child.data.leaf) {
			clickTabpanel(child.data.id,child.data.text,child.raw.entity.uri);
		}
	}
}
	
//加载子系统树的节点
function loadTree(subSystemNodes) {
	var index = 1;
	Ext.each(subSystemNodes,function(subSystem){
		if(index>5){
			index=1;
		}
		var tree = new Ext.tree.Panel({
			rootVisible : false,
			title : subSystem.functionName,
			useArrows: true,
			region:'center',
			root : {
				id : subSystem.functionCode,
				expanded : true
			},
			store : Ext.create('Ext.data.TreeStore', {
				proxy : {
					type : 'ajax',
					actionMethods : 'POST',
					params:{'node':parent.window.tabPanelTitle.activeTab.id},
					url : 'loadTree.action',
					reader : {
						type : 'json',
						root : 'functions'
					}
				}
			})
		});
		// 给tree添加鼠标点击事件
		tree.on("itemclick", treeClickAction, this);
		// 将树挂到左边的菜单上
		leftTreePanel.add(tree).doLayout();
	});
}
	
//点击树打开一个页面
function clickTabpanel(id,title,url,isClose){
    var tabs = Ext.getCmp('tabpanel');
    if (tabs) {
    	top.orderClickSource = 'click_normal';/* 是 订单来源正常点击 */
        var n = tabs.getComponent(id+'tab');
        //判断是否已经打开该面板
        if (!n) {   
            n = tabs.add({
                id: id+'tab',
                title: title,
                closable: true,  
                layout: 'fit',
                html: '<iframe id="'+id+'frame" src="'+ '/'+ getAppName()+url+'" style="height:100%;width:100%;" frameborder="0"></iframe>'
            });
        }
        tabs.setActiveTab(n);
    }
}