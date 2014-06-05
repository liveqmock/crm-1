/**
 * 首页
 */
var noticeGridPanel;
Ext.onReady(function(){
	var keys = [
	    		'NOTICE_TYPE'
	    	];
	    initDataDictionary(keys);
    /************************显示公告grid*******************************/
	noticeStore.load();
	Ext.define('NoticeGridPanel', {
		extend: 'InnerGridPanel',
		store:noticeStore,
	    columns: [
	        {
	        	header:i18n('i18n.notice.noticeTitle'),// '标题', 
	        	id:'title',
	        	dataIndex: 'title',
	        	flex:1 
	        	,sortable : false
	        },{
	        	header:i18n('i18n.notice.lastUpdateTime'),// '最后更新时间', 
	        	id:'lastUpdateTime',
	        	dataIndex: 'modifyDate', 
	        	flex:.4,
				renderer:function(value){
					return renderDate(value);
				},
	        	sortable : false 
	        }
	    ]
	});	
	noticeGridPanel = Ext.create('NoticeGridPanel',{});
	
	noticeGridPanel.on("itemdblclick",gripClick,this);//点击gridPanel显示公告详情弹窗
	
	function gripClick( me, record){
		newNotice(record.data.id);		
	}	

/***********************以上为新添加公告相关部分***********************************************************************/
	
	initDeptAndUserInfo();	
	
	//菜单Panel
	var treePanel = Ext.create('Ext.tree.TreePanel', {
		id:'treeID',
		height :800,
		store : indexTreeStore,
		rootVisible : false,
		region : 'west',
		singleExpand:true,
		collapsible : true,
		width : 173,
		cls :'lefttreepanel',
		minWidth : 173,
		maxWidth : 173,
		split : true,
		listeners : {
			itemclick : function(node, record,item,index,e,eOpts){
				if (record.get("leaf")) {
					var child = node.getSelectionModel().getSelection()[0];
					clickTabpanel(record.data.id,record.data.text,child.raw.entity.uri);
				}
			},
			itemexpand:function(){
				return false;
			}
		}
	});
	
	var problemStore=Ext.create('ProblemStore');
	problemStore.load();
	//问题列表
	Ext.define('ProblemGrid', {
		extend: 'PopupGridPanel',
		cls:'homepgrid',
		height:350,
		store:problemStore,
		border:true,
		columns: [
			{xtype: 'rownumberer', width: 40,text:'序号',align:'center',sortable:false},
			{ header: '所属模块', dataIndex: 'title', flex:0.2,sortable:false },
			{ header: '描述', dataIndex: 'description', flex:0.7,sortable:false },
			{ header: '操作', dataIndex: 'weburl', width:100,renderer:function(value,r,index){
				return '<a href=javascript:show("'+value+'")>查看详情</a>' ; 
			}}
		]
	});
	
	
	var tabPanel = Ext.create('Ext.tab.Panel', {
		border : false,
		id:'tabpanelIndex',
		cls:'tab_content',
		xtype:'tabpanel',
		region:'center',
		items:[{
			title : '首页',
			id:'index001001',
			cls:'homepagestyle',
			bodyStyle:'padding:10px',
			defaults: {
				layout:'table',
				column:'1'
			},
			items:[{
					xtype:'panel',
					height:70,
					width:'100%',
					cls:'loginmes',
					border:true,
					html:'<div style="height:40px; width:100%;">'+
   		 			'<img width="43px" height="35px" src="../images/login/welcome.png" style="float:left"/>'+
   		 			'<div style="padding-top:10px;padding-left:35px;">'+
   		 				'您好,'+'<a style="color:red;">'+(Ext.isEmpty(User.empName)==true?'':User.empName)+'</a>'+',CRM欢迎您!'+
   		 				'&nbsp;&nbsp;&nbsp;您上次登录时间：'+'<a style="color:red;">'+(Ext.isEmpty(User.lastLogin)==true?'':Ext.Date.format(new Date(User.lastLogin), 'Y-m-d H:m:s'))+'</a>'+
   		 			'</div>'+
   		 		 '</div>'
				},
				{
					xtype:'basicpanel',
					height:35,
					html:'<span style="padding: 3px 20px 15px 20px; line-height: 20px; height:50px;" class="titlestyle_sec">公告栏</span>'+
		    		'<a href="javascript:;" id="grid2Notice" onClick="moreNotice()" style="float:right;margin-right:20px;text-decoration:none;color:#369" target="_black">更多>></a>',
				},
				{
					xtype:'container',
					layout:'fit',
					height:250,
					items : noticeGridPanel
				},
				{height:35,border:false},
				{
					xtype:'fieldset',
					height:250,
					width:'100%',
					cls:'titlestyle',
					title:'<div id="title">功能简介</div>',
					border:true,
					html:'<div style="height:40px; width:100%;">'+
			      '<ul class="ehome_e6_cn_ul">'+
			       ' <table width="100%" style="background-color:#f4f4f7;margin-left:50px;">'+
			          '<tr>'+
			            '<td align="center"><div>'+
			               ' <ul>'+
			                  '<li id="hb_1" onmousedown="xgz:ZoverLi(1);" align="center" class="fovertab"><span style="cursor:pointer">客户管理</span></li>'+
			                  '<li id="hb_2" onmousedown="xgz:ZoverLi(2);" align="center" class="formaltab"><span style="cursor:pointer">营销管理</span></li>'+
			                  '<li id="hb_3" onmousedown="xgz:ZoverLi(3);" align="center" class="formaltab"><span style="cursor:pointer">订单管理</span></li>'+
			                  '<li id="hb_4" onmousedown="xgz:ZoverLi(4);" align="center" class="formaltab"><span style="cursor:pointer">工单管理</span></li>'+
			                  '<li id="hb_5" onmousedown="xgz:ZoverLi(5);" align="center" class="formaltab"><span style="cursor:pointer">理赔管理</span></li>'+
			                '</ul>'+
			              '</div></td>'+
			          '</tr>'+
			        '</table>'+
			        
			        '<table width="100%" class="dis" id="hbc_01" >'+
			        '<tr><td cls="tdstyle"><table style="border:0px" width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td ><p class="direcmes">'+
					'&nbsp;&nbsp;&nbsp;&nbsp;在本模块，您可以进行潜散客信息、固定客户信息的创建、查询和修改等操作，以保证客户信息在CRM系统中的有效性和准确性。通过客户360°视图功能，可全方位获取固定客户的基本信息、业务信息、财务信息和合同信息等信息。'+
			        '功能点：散客管理 客户360°视图 固定客户管理 工作流管理 合同管理'+
			        '<a href="#" class="opertlink" onclick = "setActive('+"'01002'"+')">点击进行操作..</a>'+
					'</p></td> </tr></table></td></tr>'+
			        '</table>'+
			        
			        '<table width="100%" class="undis" id="hbc_02"  >'+
			       ' <tr><td><table style="border:0px" width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td ><p class="direcmes">'+
					'&nbsp;&nbsp;&nbsp;&nbsp;在本模块，您可通过开发/维护计划管理制定并指派营销任务，由经理或营业员制定开发/维护日程，通过上门营销、电话营销等形式与客户接触，'+
			       '促使客户与我司发生业务往来。您也可通过五公里地图、发货周期表和到货周期表，自行对客户进行开发维护营销。'+
			       '功能点：开发/维护计划管理 开发/维护日程新增 开发/维护日程管理 监控开发/维护计划 回访录入查询 五公里地图 客户分组管理 发货周期表 到货周期表'+
			       '<a href="#" class="opertlink" onclick = "setActive('+"'01005'"+')">点击进行操作..</a>'+
					'</p></td> </tr></table></td></tr>'+
			        '</table>'+
			       
			        '<table width="100%" class="undis" id="hbc_03" >'+
			        '<tr><td><table style="border:0px" width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td ><p class="direcmes">'+
					'&nbsp;&nbsp;&nbsp;&nbsp;在本模块，您可以对订单从生成到结束的整个订单操作环节进行监控和管理，还可以在此功能中实现绑定联系人和订单号。'+
			        '功能点：创建订单 订单分配 受理订单 订运单关联 绑定联系人编码'+
			        '<a href="#" class="opertlink" onclick = "setActive('+"'01003'"+')">点击进行操作..</a>'+
					'</p></td></tr></table></td></tr>'+
			        '</table>'+
			        
			        '<table width="100%" class="undis" id="hbc_04" >'+
			        '<tr><td><table style="border:0px" width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td ><p class="direcmes">'+
					'&nbsp;&nbsp;&nbsp;&nbsp;在本模块，您可以对客户提出的不满进行上报、处理分配、相关部门反馈结果查询，最后回访核实客户对公司处理的满意度。'+
			        '功能点：工单上报 上报退回处理 工单处理 待办工单 部门工单管理 客户工单查询 工单回访管理 处理结果基础资料'+
			        '<a href="#" class="opertlink" onclick = "setActive('+"'01006'"+')">点击进行操作..</a>'+
					'</p></td> </tr></table></td></tr>'+
			        '</table>'+
			        
			        '<table  width="100%" class="undis" id="hbc_05"  >'+
			        '<tr><td><table style="border:0px" width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td ><p class="direcmes">'+
					'&nbsp;&nbsp;&nbsp;&nbsp;在本模块，您可以对客户提出的索赔申请，进行该理赔单的上报、处理等管理过程。另外，理赔研究组可通过大区设置功能，给不同的大区设置对应的理赔专员。'+
			        '功能点：理赔创建 理赔查询 理赔监控 在线理赔 大区设置'+
			        '<a href="#" class="opertlink" onclick = "setActive('+"'01004'"+')">点击进行操作..</a>'+
					'</p></td> </tr></table></td></tr>'+
			        '</table>   '+
			        '</td>'+
			        '</tr>'+
			        '</table>'+
			      '</ul>'+
		   		 	'</div>'
				}
			]
		}]
	});
	/**
	 * 显示帮助Window
	 */
	Ext.define('ProblemWindow',{
		extend:'PopWindow',
		alias : 'widget.basicwindow',
		width:820,
		height:700,
		modal:true,
		layout:'fit'
	});
	var vie = Ext.create(
	'Ext.container.Viewport',{
		autoDestroy : true,
		layout : 'border',
		items : [treePanel,tabPanel]
	});
	/**右下角小弹窗**/
	//
	var newNoticeParam={};
	var newNoticeSuccessFn=function(json){
		if(Ext.isEmpty(json.noticece)){
			return;
		}
		var imgAddr = "";
		var imgDesc = "";
		if(!Ext.isEmpty(json.noticece.imageDescribe)){
			imgDesc = json.noticece.imageDescribe;
		}
		if(!Ext.isEmpty(json.noticece.imageAddr)){
			imgAddr = json.noticece.imageAddr;
			imgAddr = imgAddr.replace('/>',' width="120" height="100"/>');
		}else{
			if(json.noticece.content.length<84){
				imgAddr = json.noticece.content;
			}else{
				var str = json.noticece.content.substring(0,84);
				for ( var i = 74; i < str.length; i++) {
					if(str.charAt(i)=='&'){
						imgAddr = str.substring(0,i);
						break;
					}else{
						imgAddr = str;
					}
				}
				imgAddr = imgAddr+'<br />......';
			}
			
		}
		var img = json.noticece.content
		var newNoticeWin = Ext.create('Ext.window.Window',{
			id:'newWinId',
			title:'最新公告',
			width:250,
			x:vie.getWidth()-250,
			y:vie.getHeight()-200,	
			height:200,
			html:'<a href=javascript:newNotice("'+json.noticece.id+'")>'+
			'<h1 align="center"  style="font-size:15px;">'+json.noticece.title+'</h1><br />'+imgAddr+'<p align="center" style="font-size:11px;">'+imgDesc+'</p></a>'
		}).show();
		
		//展示30秒后 隐藏
		setTimeout(function(){
			newNoticeWin.hide();
		},30000);
	};
	var newNoticeFailFn = function(json){
		if(Ext.isEmpty(json)){
			
		}else{
			
		}
	};
	
	searchIndexNewNotice(newNoticeParam,newNoticeSuccessFn,newNoticeFailFn);
});

/**
 * 最新公告详情弹窗
 */


function newNotice(noticeId){
	var param = {
			'noticeId':noticeId	
		};
		var successFn= function(json){
			var param = {
                	notice:{										//
                		'title'			: json.noticece.title,
                		'type'			: DpUtil.changeDictionaryCodeToDescrip(json.noticece.type,DataDictionary.NOTICE_TYPE),
	                	'content'		: json.noticece.content,
	                	'imageAddr'  	: json.noticece.imageAddr,
						'imageDescribe' : json.noticece.imageDescribe,
						'fileInfoList'	: json.noticece.fileInfoList,
						'createDate'	: DpUtil.renderDate(json.noticece.createDate),
						'modifyDate'	: DpUtil.renderDate(json.noticece.modifyDate),
						'visits'		: json.noticece.visits,
						'createUser'	: json.noticece.createUser
                	}
			};
			//弹窗
			 window.showModalDialog ('../common/noticePriviewWin.action', 
					 param.notice, 'dialogWidth:1000px;dialogHeight:700px'); //这句要写成一行
			
			//更新访问次数
			var url = '../common/updateNoticeVisitsById.action';
			Ext.Ajax.request({
			    url: url,
			    params:{
			    	'noticeId':json.noticece.id
			    }
			});
			
		};
		var failureFn = function(json){
			if(Ext.isEmpty(json)){
				MessageUtil.showErrorMes("访问超时");
			}else{
				MessageUtil.showErrorMes(json.message);
			}
		};
		findNoticeById(param,successFn,failureFn);
}
//跳转问题window
function show(weburl){
	Ext.create('ProblemWindow',{
		id:'problemWindowId',
		width:900,
		height:700,
		title:'CRM为您解答',
		html:'<iframe src="'+weburl+'" style="height:100%;width:100%;" frameborder="0"></iframe>'
	}).show();
}

function g(o){
	return document.getElementById(o);
} 
function ZoverLi(n)
{ 
	for(var i=1;i<=5;i++){
		g('hb_'+i).className='formaltab';
		g('hbc_0'+i).className='undis';
	}
	g('hbc_0'+n).className='dis';
	g('hb_'+n).className='fovertab'; 
} 
function getAppName() {
	var str = (window.location.pathname).split("/");
	return str[1];
}

function showUsualFunctionWindow(){
	if(Ext.isEmpty(Ext.getCmp('usualFunctionWindow'))){
		new UsualFunctionWindow().show();
	}else{
		Ext.getCmp('usualFunctionWindow').show();
	}
}
//点击树打开一个页面
function clickTabpanel(id,title,url){
    var tabs = Ext.getCmp('tabpanelIndex');
    if (tabs) {
        var n = tabs.getComponent(id);
        //判断是否已经打开该面板
        if (!n) {   
            n = tabs.add({
                'id': id,
                'title': title,
                closable: true,  
                layout: 'fit',
                html: '<iframe id="'+id+'" src="'+ '/'+ getAppName()+url+'" style="height:100%;width:100%;" frameborder="0"></iframe>'
            });
        }
        tabs.setActiveTab(n);
    }
}

function createMessageData(messageList){
	var messageData  = new Array();
	for(var i = 0;i<messageList.length;i++){
		//如果DATA不为空，则说明是点击这条代办之后，删除代办信息中这个条代办
		//如果DATA为空，则说明是定时刷新代办
		if(messageList[i].tasktype == 'MESSAGE'){
			continue;
		}
		var id  = messageList[i].id;
		var name = null;
		var count = 0;
		var imageUrl = null;
		if(Ext.isEmpty(messageList[i].taskcount)||messageList[i].taskcount==0){
			continue;
		}else{
			name = top.changeDataDictionaryDesc(messageList[i].tasktype);
			count = messageList[i].taskcount;
			if(messageList[i].tasktype=='ORDER_UNACCEPT'){
				 imageUrl = '../images/login/accorder.png';
			}else if(messageList[i].tasktype=='COMPLAINT_TASK'){
				imageUrl = '../images/login/noaccpet.png';
			}else if(messageList[i].tasktype=='COMPLAINT_WORKBENCH'){
				imageUrl = '../images/login/complaint.png';
			}
		}
		if(!Ext.isEmpty(imageUrl)){
			var data = {'id':id,'name':name,'count':count
					,'imageUrl':imageUrl,'tasktype':messageList[i].tasktype,'taskcount':messageList[i].taskcount};
			messageData.push(data);
		}
	}
	return messageData;
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
} ;
function setActive(functionId){
	var tabs = top.Ext.getCmp('Menu');
	//跳转相应的TAB
	tabs.setActiveTab(functionId);
}

/**
 * 描述：显示更多公告的方法
 * 时间：2012.10.13
 */
function moreNotice(){
	//获取标签页
	var tabs = Ext.getCmp('tabpanelIndex');//获取tabPanel
	if (tabs) {
		//判断是否已经打开该面板
		var n = tabs.getComponent("moreNotice");
		if (!n) {   
			n = tabs.add({
				'id': "moreNotice",
				'title':i18n('i18n.notice.moreNotice'),// '更多公告',
				closable:true,
				layout: 'fit',
				border:false,
				html: '<iframe src="../common/moreNotice.action" style="height:100%;width:100%;" frameborder="0"></iframe>'
			});
		}
	  tabs.setActiveTab(n);
	}
}

/**
 * 公告附件下载
 * @param fileName
 * @param filePath
 */
function downLoadFile(fileName,filePath){
	window.location.href="../common/downLoad.action?fileName="+fileName+"&inputPath="+filePath;
}

/**
 * 公告类别反转
 * zouming
 */
function changeDictionaryCodeToDescrip(value, dataDictionary) {
	if (value != null && dataDictionary != null) {
		for ( var i = 0; i < dataDictionary.length; i++) {
			if (value == dataDictionary[i].codeDesc) {
				return dataDictionary[i].code;
			}
		}
	} else {
		return null;
	}
}
/**
 * 时间转换
 * zouming
 */
function renderDate(value) {
	if(value != null){
		return Ext.Date.format(new Date(value), 'Y-m-d G:m:s ');
	}else{
		return null;
	}
}
