// require 类似java import
Ext.require([ '*' ]);
/**
 * 5分钟发一个空请求给服务器，让session不超时
 */ 
//setInterval(function() {
//	Ext.Ajax.request({
//		url : 'keepSessionOnLine.action'
//	});
//}, 5 * 1000 * 60);
var COUNT = 0;
var COUNTB = 0;
var step = 0;
var stepCount = 0;
var MessageList = [];
var MessageListOrder = [];
var FlashType = 0;
var tb = Ext.create('Ext.toolbar.Toolbar',{
	width:150,
	id:'tb',
	items:[
    ]
});
var tbOrder = Ext.create('Ext.toolbar.Toolbar',{
	width:150,
	id:'tbOrder',
	items:[
    ]
});
var JumpData = null;//跳转页面需要的数据
var Condition = null;//查询条件
var DataDictionary  = null;//业务字典
var bFlash = false;				// 用于判断是否闪烁的变量。

/**.
 * <p>
 * 代办事宜的全局变量<br/>
 * </p>
 * @author  张斌
 * @时间    2012-05-04
 * </p>
 */
var MESSAGE = (function() {
    var constants = {
    		//待办事宜跳转信息列表
    		'ORDER_UNACCEPT':{'parentFunctionId':'01003','functionId':'01003003',url:'/order/processAndReturnOrder.action','title':i18n('i18n.login.processOrder')},//受理订单（待受理）
    		'ORDER_UNASSIGN':{'parentFunctionId':'01003','functionId':'01003001',url:'/order/allocationOrder.action','title':i18n('i18n.login.allocationOrder')},//（待分配）
    		'ORDER_ACCEPTED':{'parentFunctionId':'01003','functionId':'01003003',url:'/order/processAndReturnOrder.action','title':i18n('i18n.login.processOrder')},//（已受理）
    		'ORDER_GOBACK':{'parentFunctionId':'01003','functionId':'01003003',url:'/order/processAndReturnOrder.action','title':i18n('i18n.login.processOrder')},//（已退回）
    		'COMPLAINT_REPORT_RETURN':{'parentFunctionId':'01006','functionId':'01006002',url:'/complaint/reportReturnProcessIndex.action','title':i18n('i18n.login.waitProcess.returnComplaint')},//（上报退回处理）
    		'COMPLAINT_WORKBENCH':{'parentFunctionId':'01006','functionId':'01006008',url:'/complaint/complaintWorkbenchIndex.action','title':i18n('i18n.login.waitProcess.waitProcessComplaint')},//（待办工单）
    		'COMPLAINT_TASK':{'parentFunctionId':'01006','functionId':'01006005',url:'/complaint/complaintTaskIndex.action','title':i18n('i18n.login.waitProcess.taskComplaint')},//（部门工单管理）
    		'COMPLAINT_VERIFY':{'parentFunctionId':'01006','functionId':'01006008',url:'/complaint/complaintVerifyTaskIndex.action','title':i18n('i18n.login.waitProcess.verifytTaskComplaint')}//（工单回访管理）
        };
        return {
           get: function(name) { return constants[name]; }
       };
   })();

/**.
 * <p>
 * 首页tabPanel标签页
 * </p>
 * @author  张登
 * @时间    2012-03-19
 */

var tabPanelTitle = Ext.create('Ext.tab.Panel', {
	region : 'center',
	id :"Menu",
	activeTab :0,
	height : 25,
	items : [{
		title : i18n('i18n.login.fristPage'),					// tab头部，
		id:'showIndex',
		html: '<iframe id="showIndexFrame" src="showIndex.action?20121106.15.37" style="height:100%;width:100%;" frameborder="0"></iframe>'
	}]
});

/**
 * 初始化
 */
Ext.onReady(function() {
	Ext.QuickTips.init();						// 提示类。
	document.title = i18n('i18n.login.page_title');
	
	/*
	document.getElementById('message_waiting').innerHTML = i18n('i18n.login.message_waiting');
	setTimeout(function() {
		Ext.get('loading').remove();
		Ext.get('loading-mask').fadeOut({
			remove : true
		});
	}, 1000);*/
	initDeptAndUserInfo();						//初始化用户
	initMessageDataDictionary();
	refreshMessage(tb);							//初始刷新 获取非订单代办事宜
	//refreshOrderMessage(tbOrder);				//初始刷新 获取订单代办事宜
	/**.
	 * <p>
	 * 动态加载标签页
	 * </p>
	 * @author  张登
	 * @时间    2012-03-19
	 */	
	Ext.Ajax.request({
		url : 'loadSubSystemByTab.action',
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				var subSystemNodes = Ext.decode(response.responseText).subSystemNodes;		// XXX:???
				Ext.each(subSystemNodes,function(subSystem){
					//动态读取
					initTabpanel(subSystem.functionCode,subSystem.functionName,subSystem.uri);
				});
				/**.
				 * <p>
				 * 首页Viewport布局
				 * </p>
				 * @author  张登
				 * @时间    2012-03-19
				 */
				Ext.create('Ext.container.Viewport',{
					layout : 'anchor',
					autoScroll:true,
					cls:'whole',
					align:'center',
					items:[{
						width:1007,
						height:925,
						cls:'banner',
						layout:'border',
						items : [{
							region : 'north',
							xtype : 'panel',
							height :20,
							cls:'logininfor',
							layout: {
								type: 'hbox',
								//padding:'5',
								pack:'end',
								align:'middle'
							},
							//defaults:{margins:'0 5 0 0'},
							items:[
							    {id:'user',xtype:'label',text:i18n('i18n.login.currentUser')+
							    	(Ext.isEmpty(User.loginName)==true?'':User.loginName)},
							    {xtype:'label',text:'',width:20},
							    {id:'deptName',xtype:'label',text:'部门:'+(Ext.isEmpty(User.deptName)==true?'':User.deptName)},
							    {xtype:'label',text:'',width:20},
							    {id:'userName',xtype:'label',text:'姓名:'+(Ext.isEmpty(User.empName)==true?'':User.empName)},
							    tbOrder,
							    tb,
//								{id:'accountManage',xtype:'button',text:' | 账户管理 ',handler:function(){
//								}},
								{id:'exit',xtype:'button',text:'|&nbsp;&nbsp;&nbsp;'+'<span style="color:red">'+i18n('i18n.login.back')+'</a>',handler:function(){
									window.location.href="logout.action";
								}}]
						} ,
						tabPanelTitle,//标签页
						{
							xtype:'panel',
							cls:'foot',
							region : 'south',
							height : 23,
							items: [
								new Ext.Toolbar.TextItem({
									text:i18n("i18n.login.deppon2012")
								})
							]
						}]
			        }]
					
				});
			}
		}
	});
});

/**.
 * <p>
 * 动态加载tabPanel
 * </p>
 * @author  张登
 * @时间    2012-03-19
 */
function initTabpanel(id,text,url){
    var tabs = Ext.getCmp('Menu');
    if (tabs) {
        var n = tabs.getComponent(id+'mainTab');
        //判断是否已经打开该面板
        if (!n) {   
            n = tabs.add({
                id: id,
                title: text,
                layout: 'fit',
                html: '<iframe id="'+id+'mainFrame" src="showWorkSpace.action?node='+id+'" style="height:100%;width:100%;" frameborder="0"></iframe>'
//                html: '<iframe id="'+id+'" src="http://www.baidu.com" style="height:100%;width:100%;" frameborder="0"></iframe>'
            });
        }
//      tabs.setActiveTab(n);
    }
}
/**.
 * <p>
 * 构建MENU
 * </p>
 * @author  张斌
 * @时间    2012-05-03
 */
function createMenuList(data){
	var menuList = new Array();
	var newMessageList = new Array();
	COUNT = 0;
	for(var i = 0;i<MessageList.length;i++){
		//如果DATA不为空，则说明是点击这条代办之后，删除代办信息中这个条代办
		//如果DATA为空，则说明是定时刷新代办
		if(!Ext.isEmpty(data)&&data.id==MessageList[i].id&&data.tasktype!='MESSAGE'){
			continue;
		}
		newMessageList.push(MessageList[i]);
		var text = null;
			if(Ext.isEmpty(MessageList[i].taskcount)||MessageList[i].taskcount==0){
				continue;
			}else{
				var text = changeDataDictionaryDesc(MessageList[i].tasktype);
				text = text+'('+'<span style="color:red; font-size:12px;">'+MessageList[i].taskcount+'</span>'+')';
				COUNT = COUNT+MessageList[i].taskcount;
			}
		var menuObj = {
			'text':'<span style=" font-size:12px;">'+text+'</span>',
			data:MessageList[i],
			handler:function(th){
				menuAction(th.data);
			}};
		menuList.push(menuObj);
	}
	MessageList = newMessageList;
	return menuList;
}
function createMenuOrderList(data){
	var menuListOrder = new Array();
	var newMessageListOrder = new Array();
	COUNTB = 0;
	for(var i = 0;i<MessageListOrder.length;i++){
		//如果DATA不为空，则说明是点击这条代办之后，删除代办信息中这个条代办
		//如果DATA为空，则说明是定时刷新代办
		if(!Ext.isEmpty(data)&&data.id==MessageListOrder[i].id&&data.tasktype!='MESSAGE'){
			continue;
		}
		newMessageListOrder.push(MessageListOrder[i]);
		var text = null;
			if(Ext.isEmpty(MessageListOrder[i].taskcount)||MessageListOrder[i].taskcount==0){
				continue;
			}else{
				var text = changeDataDictionaryDesc(MessageListOrder[i].tasktype);
				text = text+'('+'<span style="color:red; font-size:12px;">'+MessageListOrder[i].taskcount+'</span>'+')';
				COUNTB = COUNTB+MessageListOrder[i].taskcount;
			}
		var menuObjOrder = {
			'text':'<span style=" font-size:12px;">'+text+'</span>',
			data:MessageListOrder[i],
			handler:function(th){
				/* 设置为订单相关待办点击来源 */
				top.orderClickSource = 'click_business_backlog';
				menuActionO(th.data);
			}};
		menuListOrder.push(menuObjOrder);
	};
	MessageListOrder = newMessageListOrder;

	//待办事宜数量 		taskcount
	//闪烁读秒数			stepCount
	//title闪烁类型  		FlashType
	//经过处理的信息条数	COUNT(非订单)，COUNTB（订单）
	//Message实体		Message.java
	//TODO:闪烁优化
	if(COUNTB!=0&&stepCount==0){
		FlashType = 0;
		flash_title();
	}else if(COUNTB==0){
		//终止闪烁，将值设置为多少客户关系系统
		FlashType = 2; 
		flash_title();
	}else if(COUNTB!=0&&stepCount!=0){
		//终止闪烁，将值设置为多少条
		FlashType = 1;
		flash_title();
	}
	return menuListOrder;
}
var messageStore = Ext.create('Ext.data.Store', {
	pageSize:5,
    storeId:'messageStore',
    fields:['id', 'tasktype', 'ishaveinfo','createDate'],
    data:[],
    autoLoad:false,
    listeners:{
    	beforeload:function(thisStore,operation,e){
    		// (Ext.data.Store store, Ext.data.Operation operation, Object eOpts)
    		// This Store
    		// The Ext.data.Operation object that will be passed to the Proxy to load the Store
    		// The options object passed to Ext.util.Observable.addListener.
  			Ext.apply(operation,{
				params : {'userID':User.userId,'deptID':User.deptId}
			});
    	}
    },
	proxy:{
		type:'ajax',
		url:'searchOnlyMessage.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'msgInfoList',
			totalProperty : 'msgInfoCount'
		}
	}
});

var messageGrid = Ext.create('Ext.grid.Panel', {
	cls:'popup_grid',
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    store:messageStore,
    columns: [{
		xtype : 'rownumberer',
		width : 40,
		text : i18n('i18n.login.num')
		},
		{ header: 'id',  dataIndex: 'id' ,hidden:true},//id
        { header: i18n('i18n.login.messageComFrom'),  dataIndex: 'tasktype', flex:1,renderer:function(value){
        	return changeDataDictionaryDesc(value);
        } },//消息类型
        { 
        	header: i18n('i18n.login.messageContain'), dataIndex: 'ishaveinfo', flex:3,
        	renderer:function(val){
        		return '<span data-qtip="'+ val +'">'+val+'</span>';
        	}        
        },//消息内容
        { header: i18n('i18n.login.createDate'),  dataIndex: 'createDate', flex:1,renderer:function(value){
        	return new Date(value).format('yyyy-MM-dd hh:mm:ss');
        } }//消息类型
    ],
    height: 235,
    width: 720,
    bbar:Ext.create('Ext.toolbar.Paging', {
		id : 'pagingToolbar',
		store : messageStore,
		displayMsg : i18n('i18n.login.displayMsg'),
		displayInfo : true,
		items:[
            '-',{
					text: i18n('i18n.login.page_count'),
					xtype: 'tbtext'
				},Ext.create('Ext.form.ComboBox', {
                   width:          50,
                   value:          '5',
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
                           {'value':'5'},
                           {'value':'10'},
                           {'value':'15'},
                           {'value':'20'},
                           {'value':'25'},
                           {'value':'40'},
                           {'value':'100'}
                       ]
                   }),
                   listeners:{
						select : {
							scope : this,
				            fn: function(_field,_value){
				            	var pageSize = messageStore.pageSize;
				            	var newPageSize = parseInt(_field.value);
				            	if(pageSize!=newPageSize){
				            		messageStore.pageSize = newPageSize;
				            		Ext.getCmp('pagingToolbar').moveFirst();
				            	}
				            }
				        }
                   }
               }),{
					text: i18n('i18n.login.pageNumber'),
					xtype: 'tbtext'
               }]
	})
});
//消息提醒窗口
var messageWindow = Ext.create('Ext.window.Window',{
	cls:'popup',
	height:320,
	width:750,
	resizable :false,
	modal:true,
	closeAction:'hide',
	title:i18n('i18n.login.agencyAlert'),
	items: [messageGrid],
	listeners:{
		beforehide:function(){
			refreshMessage(tb);							//关闭window（待办事项）刷新一下代办事宜
		}
	},
	fbar:[{
		xtype:'button',
		text:i18n('i18n.login.insureReadMessage'),
        handler:function(){
        	confirmReadMessages();
		}//确认这些消息已读
	},{
		xtype:'button',
		text:i18n('i18n.login.close'),
		handler:function(){
			messageWindow.hide();
		}//'关闭'
	}]
});



/**.
 * <p>
 * 点击非订单MENU后触发事件，刷新代办数据，重新组建menu
 * </p>
 * @author  张斌
 * @时间    2012-05-03
 */
function menuAction(data){
	var menuList = createMenuList(data);
	//更新代办
	var textBar = {
			//xtype:'splitbutton',
            text:i18n('i18n.login.toDo')+'('+'<span style="color:red;font-weight:bold">'+COUNT+'</span>'+')',
            iconCls: 'bmenu',
            //iconCls: 'blist',
            menu:{
            	xtype:'menu',
            	autoScroll:true,
            	maxHeight:150,
            	listeners:{
            		mouseleave:function(th,e){
            			th.hide();
            		}
            	},
            	items:[]
            }};
	tb.removeAll();
	tb.add(textBar);
	tb.items.items[0].menu.removeAll();
	tb.items.items[0].menu.add(menuList);
	if(data.tasktype!='MESSAGE'){
		//获取该代办类型相应数据
		JumpData = MESSAGE.get(data.tasktype);
		var jumpID = JumpData.parentFunctionId;
		//获取查询条件
		Condition = data.conditions;
		for(var i=0;i<this.frames.length;i++){
			if(this.frames[i].ActiveID==jumpID&&this.frames[i].name!=='showIndexFrame'){
				this.frames[i].toDo();
			}
		}
		var tabs = Ext.getCmp('Menu');
		//跳转相应的TAB
		tabs.setActiveTab(jumpID);
		if(!Ext.isEmpty(this.frames[0].getMessage)){
			this.frames[0].getMessage();
		}
		//跳转之后，发送删除该代办的请求
		Ext.Ajax.request({
			url : 'deleteMessage.action',
			jsonData:{'messageId':data.id},
			success:function(response){
				var result = Ext.decode(response.responseText);
				if(result.success){
					return ;
				}else{
//						top.Ext.MessageBox.alert(i18n('i18n.login.alert'),result.message);
					MessageUtil.showMessage(result.message);
				}
			}
		});
	}else{
		messageWindow.show();
//			top.Ext.MessageBox.alert("sssssssss");
//			Ext.getDom("showIndex").style='display:none';
		Ext.getCmp('pagingToolbar').moveFirst();
	}
}
/**.
 * <p>
 * 点击订单相关MENU后触发事件，刷新代办数据，重新组建menu
 * </p>
 * @author  张斌
 * @时间    2012-05-03
 */
function menuActionO(data){
	var menuListOrder = createMenuOrderList(data);
	//更新代办
	var textBar = {
			//xtype:'splitbutton',
            text:i18n('i18n.login.toDoSo')+'('+'<span style="color:red;font-weight:bold">'+COUNTB+'</span>'+')',
            iconCls: 'bmenu',
            //iconCls: 'blist',
            menu:{
            	xtype:'menu',
            	autoScroll:true,
            	maxHeight:150,
            	listeners:{
            		mouseleave:function(th,e){
            			th.hide();
            		}
            	},
            	items:[]
            }};
	tbOrder.removeAll();
	tbOrder.add(textBar);
	tbOrder.items.items[0].menu.removeAll();
	tbOrder.items.items[0].menu.add(menuListOrder);
		if(data.tasktype!='MESSAGE'){
			//获取该代办类型相应数据
			JumpData = MESSAGE.get(data.tasktype);
			var jumpID = JumpData.parentFunctionId;
			//获取查询条件
			Condition = data.conditions;
			for(var i=0;i<this.frames.length;i++){
				if(this.frames[i].ActiveID==jumpID&&this.frames[i].name!=='showIndexFrame'){
					this.frames[i].toDo();
				}
			}
			var tabs = Ext.getCmp('Menu');
			//跳转相应的TAB
			tabs.setActiveTab(jumpID);
			if(!Ext.isEmpty(this.frames[0].getMessage)){
				this.frames[0].getMessage();
			}
			//跳转之后，发送删除该代办的请求
			/**.
			 * <p>
			 * 跳转之后，订单代办不去自动删除。因为自动删除与后台定时任务删除造成了代办表的锁，然后连接溢出
			 * </p>
			 * @author  张斌
			 * @时间    2013-06-16
			 */
			/*Ext.Ajax.request({
				url : 'deleteMessage.action',
				jsonData:{'messageId':data.id},
				success:function(response){
					var result = Ext.decode(response.responseText);
					if(result.success){
						return ;
					}else{
//						top.Ext.MessageBox.alert(i18n('i18n.login.alert'),result.message);
						MessageUtil.showMessage(result.message);
					}
				}
			});*/
		}else{
			messageWindow.show();
			Ext.getCmp('pagingToolbar').moveFirst();
		}			
}

/**.
 * @author  Rock
 * @时间    2012-09-17
 */
function uniquelize(arr){
	var i,aArr=[],aResult=[];
	for( i=0; i<arr.length; i++ ){
		var stack = arr[i].tasktype;
		if( aArr.indexOf(stack)==-1 ){
			aArr.push(stack);
			aResult.push(arr[i])
		}
	}
	return aResult;
}

/**.
 * <p>
 * 每3分钟刷新一下代办事宜
 * </p>
 * @author  张斌
 * @时间    2012-05-03
 */
function refreshMessage(){
	/**.
	 * <p>
	 * 加载代办事宜
	 * </p>
	 * @author  张斌
	 * @时间    2012-05-03
	 */	
	Ext.Ajax.request({
		url : 'searchAllMessage.action',
		jsonData:{'userID':User.userId,'deptID':User.deptId},
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				MessageList = Ext.decode(response.responseText).exceptOrderList;
				var messageList = new Array();
				for(var i=0;i<MessageList.length;i++){
					if(!Ext.isEmpty(MessageList[i].deptId)&&MessageList[i].deptId==User.deptId){
						messageList.push(MessageList[i]);
					}else if(!Ext.isEmpty(MessageList[i].userid)&&MessageList[i].userid==User.userId){
						messageList.push(MessageList[i]);
					}
				}
				MessageList = messageList;								// 归根结底的待办信息list
				var menuList = createMenuList();						// menuList,就是menu下拉菜单的每一条
				//更新代办
				var textBar = {
						//xtype:'splitbutton',
						text:i18n('i18n.login.toDo')+'('+'<span style="color:red;font-weight:bold">'+COUNT+'</span>'+')',
			            iconCls: 'bmenu', 
			            menu: {
			            	xtype:'menu',
			            	autoScroll:true,
			            	maxHeight:150,
			            	listeners:{
			            		mouseleave:function(th,e){
			            			th.hide();
			            		}
			            	},
			            	items:[]
			            }};
				tb.removeAll();
				tb.add(textBar);
				tb.items.items[0].menu.removeAll();
				tb.items.items[0].menu.add(menuList);
			}else{
				MessageUtil.showMessage(result.message);
				window.location.href="logout.action";					// 跳出登陆
			}
			
			var orderresult = Ext.decode(response.responseText);
			if(orderresult.success){
				MessageListOrder = Ext.decode(response.responseText).orderList;
				var messageListOrder = new Array();
				for(var i=0;i<MessageListOrder.length;i++){
					if(!Ext.isEmpty(MessageListOrder[i].deptId)&&MessageListOrder[i].deptId==User.deptId){
						messageListOrder.push(MessageListOrder[i])
					}else if(!Ext.isEmpty(MessageListOrder[i].userid)&&MessageListOrder[i].userid==User.userId){
						messageListOrder.push(MessageListOrder[i])
					}
				}
				MessageListOrder = messageListOrder;					// 归根结底的订单待办信息list
				var menuListOrder = createMenuOrderList();				// menuListOrder,就是order下拉菜单的每一条
				//更新代办
				var  textBarOrder = {
						//xtype:'splitbutton',
						text:i18n('i18n.login.toDoSo')+'('+'<span style="color:red;font-weight:bold">'+COUNTB+'</span>'+')',
			            iconCls: 'bmenu', 
			            menu: {
			            	xtype:'menu',
			            	autoScroll:true,
			            	maxHeight:150,
			            	listeners:{
			            		mouseleave:function(th,e){
			            			th.hide();
			            		}
			            	},
			            	items:[]
			            }};
				tbOrder.removeAll();
				tbOrder.add(textBarOrder);
				tbOrder.items.items[0].menu.removeAll();
				tbOrder.items.items[0].menu.add(menuListOrder);
			}else{
				MessageUtil.showMessage(orderresult.message);
				window.location.href="logout.action";					// 跳出登陆
			}
			
		}
	});
	//每3分钟刷新一下代办事宜
	setTimeout("refreshMessage()",3*60*1000);
}
function refreshOrderMessage(){
	/**.
	 * <p>
	 * 加载代办事宜
	 * </p>
	 * @author  张斌
	 * @时间    2012-05-03
	 */	
	/*
	Ext.Ajax.request({
		url : 'searchOrderMessage.action',
		jsonData:{'userID':User.userId,'deptID':User.deptId},
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				MessageListOrder = Ext.decode(response.responseText).orderList;
				var messageListOrder = new Array();
				for(var i=0;i<MessageListOrder.length;i++){
					if(!Ext.isEmpty(MessageListOrder[i].deptId)&&MessageListOrder[i].deptId==User.deptId){
						messageListOrder.push(MessageListOrder[i])
					}else if(!Ext.isEmpty(MessageListOrder[i].userid)&&MessageListOrder[i].userid==User.userId){
						messageListOrder.push(MessageListOrder[i])
					}
				}
				MessageListOrder = messageListOrder;					// 归根结底的订单待办信息list
				var menuListOrder = createMenuOrderList();				// menuListOrder,就是order下拉菜单的每一条
				//更新代办
				var  textBarOrder = {
						//xtype:'splitbutton',
						text:i18n('i18n.login.toDoSo')+'('+'<span style="color:red;font-weight:bold">'+COUNTB+'</span>'+')',
			            iconCls: 'bmenu', 
			            menu: {
			            	xtype:'menu',
			            	autoScroll:true,
			            	maxHeight:150,
			            	listeners:{
			            		mouseleave:function(th,e){
			            			th.hide();
			            		}
			            	},
			            	items:[]
			            }};
				tbOrder.removeAll();
				tbOrder.add(textBarOrder);
				tbOrder.items.items[0].menu.removeAll();
				tbOrder.items.items[0].menu.add(menuListOrder);
			}else{
				MessageUtil.showMessage(result.message);
				window.location.href="logout.action";					// 跳出登陆
			}
			
		}
	});
	//每3分钟刷新一下代办事宜
//	setTimeout("refreshOrderMessage()",3*60*1000);
	 */
}
/**
 * 初始化待办事项数据字典
 * @author 张斌
 * @时间 2012-5-04
 */
function initMessageDataDictionary(){
	Ext.Ajax.request({
		url:'../common/queryAllDataDictionaryByKeys.action',
		async:false,
		jsonData:{'dataDictionaryKeys':['TASK_TYPE']},
		success:function(response){
			var result = Ext.decode(response.responseText);
			DataDictionary=result.dataDictionary.TASK_TYPE;
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
//			Ext.MessageBox.alert(i18n('i18n.login.alert'),result.message);
			MessageUtil.showMessage(result.message);
		}
	});
}
/**
 * 初始化待办事项数据字典
 * @author 张斌
 * @时间 2012-5-04
 */
function changeDataDictionaryDesc(text){
	if (text != null && DataDictionary!= null) {
		for ( var j = 0; j < DataDictionary.length; j++) {
			if (text ==DataDictionary[j].code) {
				return DataDictionary[j].codeDesc;
			}
		}
	} else {
	   return null;
	}
}
function confirmReadMessages(){
	var selections = messageGrid.getSelectionModel().getSelection();
	if(selections.length==0){
//		top.Ext.MessageBox.alert(i18n('i18n.login.alert'),i18n('i18n.login.pleaseSelectOne'));
		MessageUtil.showMessage(i18n('i18n.login.pleaseSelectOne'));
		return;
	}
	var ids = new Array();
	for(var i = 0;i<selections.length;i++){
		ids.push(selections[i].get('id'));
	}
//	top.Ext.MessageBox.confirm(i18n('i18n.login.alert')
	MessageUtil.showQuestionMes(i18n('i18n.login.isInsureReadMessage'),
		function(e){
			if (e == 'yes') {
				Ext.Ajax.request({
					url : 'deleteMessages.action',
					jsonData:{'ids':ids},
					success:function(response){
						var result = Ext.decode(response.responseText);
						if(result.success){
							Ext.getCmp('pagingToolbar').doRefresh( );
							return ;
						}else{
//							top.Ext.MessageBox.alert(i18n('i18n.login.alert'),result.message);
							MessageUtil.showMessage(result.message);
						}
					}
				});
			}
	}); 
	
}
function flash_title(){
	/**
	 * 增加title提示闪烁前置条件，全部是订单相关的待办事项。
	 */
    if(FlashType == 0){
		  var titleString = '【'+i18n('i18n.login.youHaveWhatToDoSo')+COUNTB+i18n('i18n.login.pageNumber')+'】';
			//i18n.login.youHaveWhatToDo=您有待办事项
			//i18n.login.youHaveWhatToDoSo=您有订单相关待办事项
			//i18n.login.pageNumber=条
		  if(stepCount!=60){
			  step++;
			  stepCount++;
			  if (step==3) {step=1}
			  if (step==1) {document.title=titleString}
			  if (step==2) {document.title='【　　　　　　          】'}
			  setTimeout("flash_title()",400);
		  }else{
			  document.title=titleString;
			  stepCount = 0;
			  step = 0;
			  return;
		  }
    }else if(FlashType == 1){
    		stepCount = 0;
    		step = 0;
    		document.title = '【'+i18n('i18n.login.youHaveWhatToDoSo')+COUNTB+i18n('i18n.login.pageNumber')+'】';
    		return;
    }else if(FlashType == 2){
    	var titleString = i18n('i18n.login.page_title');
    	document.title=titleString;
		stepCount = 0;
		step = 0;
		return;
    }
}

/**.
 * <p>
 * JS日期的format方法<br/>
 * <p>
 * @param format 日期格式
 * @author 张斌
 * @时间 2012-3-23
 */
Date.prototype.format = function(format){
	if(Ext.isEmpty(this)||this.getTime()==0||this.toString().indexOf('GMT')==-1){
		return null;
	}
	var o = {
		"M+" : this.getMonth()+1, //month
		"d+" : this.getDate(), //day
		"h+" : this.getHours(), //hour
		"m+" : this.getMinutes(), //minute
		"s+" : this.getSeconds(), //second
		"q+" : Math.floor((this.getMonth()+3)/3), //quarter
		"S" : this.getMilliseconds() //millisecond
	};

	if(/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	};

	for(var k in o) {
		if(new RegExp("("+ k +")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
		}
	};
	return format;
}