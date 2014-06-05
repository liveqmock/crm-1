/**
 * @class:Depon.Lib.oDocHelper帮助组件
 * @author 		rock
 * @version:	0.0.1
 * @deprecated	
 * @param		{} overrides
 * @return 		elementClass
 * @exception:	可能抛出的异常包括:
 * 
 * @see			Depon.Lib.CEditor
 * 
 * @example:Depon.Lib.oDocHelper
 * helper1 = Ext.create('Depon.Lib.oDocHelper', {})
 * 
 */
// TODO：i18n、组件渲染、用户权限（ 是否操作员）、用户习惯管理（显隐状态:需要放开（oThis.getButton(opts);））、帮组文档在弹窗中展示（可拖拽）。
Ext.ns('Depon.Lib');
Ext.ns('Depon.Lib.oDocHelper.editor');
Depon.Lib.oDocHelper.oEditor = null;
Ext.ns('Depon.Lib.CEditor');

// TODO:Editor组件分离：多组件并存，以便于后台编辑功能使用

(function(Ext){
	var OButton,
		CEditor,
		oEditor = Depon.Lib.oDocHelper.oEditor,
		btnShow, btnEdit, btnSave, 
		opts;
/**
 * @class			:button
 * 新建				:OButton.create()
 * 设置按钮属性		:OButton1.setText('按钮文本');	
 * 操作按钮属性		:OButton1.outPutTxt()
 */
	OButton = {
		// renderTo:'',
		create: function(text,renderTo){
			renderTo=renderTo||this.renderTo;
			var oButton = Ext.create('Ext.Button', {
				text : text ,
				margin:'0 12 0 0',
				renderTo: renderTo
			});
			oButton.setText		= function(s){ oButton.text = s; };			// 按钮文本赋值接口TODO:按钮文本渲染
			// oButton.setRenderTo = function(s){ oButton.renderTo = s; };
		　　// oButton.outPutTxt	= function(){ alert(oButton.sound); };
		　　return oButton;
	　　}
　　};
/**
 * @class:Depon.Lib.oDocHelper帮助组件正文
 * 
 */
	Depon.Lib.oDocHelper = Ext.extend(Ext.util.Observable, {
		constructor : function(config) {
			opts = Ext.apply(this.defaults,config);
			var $this = this;
			this.actionFirstHtml(opts);
			Depon.Lib.oDocHelper.superclass.constructor.call(this,config);	// 调用父类的构造器来完成构造过程
			/**
			 * @author:预留自定义事件接口 接口
			 */
			// constructor 内做如下定义
			// this.coderName = config.name||"no name";	
			// this.addEvents('create','exception','others..');
			// this.listeners = config.listeners;
			// 自定义事件调用示例：
			// Ext.create({
				// name:'Rock',
				// listeners:{
					// 'create':function(){
						// this.eventBySelf();
					// }
				// }
			// });
			// this.fireEvent('create'/* define this*/);

			// 原型测试接口
			// this.init()
		},
		actionFirstHtml:function(opts){
			var u	= opts.sUrlSearch,
				jP	= {'windowNum': opts.helpDoc.windowNum},							// windowNum 对应帮助文档 number
				fnS	= null,
				fnF	= null,
				oThis = this;
			fnS = function(obj){
				if(obj.helpDoc){
					opts.helpDoc = obj.helpDoc;				// 帮助文档object 
					console.log("获取文档成功");
				}
				oThis.showSelf(opts);
			};
			fnF = function(){
				console.log("获取文档失败");
			};
			oThis.sendRequest( opts.sUrlSearch, jP, fnS, fnF );
		},
		showSelf:function(opts) {
			var oThis = this,
				sNum = opts.helpDoc.windowNum;
			if(opts.win){
				this.setPanelForWin(opts.win,sNum);
			};
			Ext.get('wrapForm'+sNum).setStyle('height',"30px");
			
			// TODO:用末尾结构做组件渲染
				// TODO:window,已经实现。其他结构尚需在看
			var tH = "<p id='wrapper"+sNum+"' style='height:24px;text-align:center;color:#060;'><span style='float:left;line-height:24px;text-indent:1em;font-weight:600;' id='helpTitle"+sNum+"'>\u8BF4\u660E\uFF1A</span><span id='btnCtn"+ sNum +"' style='height:24px;width：140px;display:block; margin:0 auto;'></span>",		//\u8BF4\u660E\uFF1A	说明
				ttH	= Ext.DomHelper.createTemplate(tH);
			ttH.overwrite('id1'+sNum );

			Ext.onReady(function(){
				if( opts.helpDoc&&opts.helpDoc.active&&opts.helpDoc.helpContent){
					var bH	= '<div id="tWrp'+sNum+'" style="padding:10px 10px 10px 20px;line-height:1.6;color:#060;">{HtmlTemplete}</div></p>',
					tbH = Ext.DomHelper.createTemplate(bH);
					Ext.get('wrapForm'+sNum).setStyle('height',opts.sHeight);
					tbH.overwrite('id2'+sNum ,{HtmlTemplete:opts.helpDoc.helpContent } );
					// oThis.getButton(opts);
				}else{
					console.log('\u5F00\u53D1\u5458,\u8BF7\u4F20\u5165\u6B63\u786E\u7684\u5E2E\u52A9\u7F16\u7801\uFF01');
				}
			});
		},
		getButton:function(opts){
			var bChanged 	= opts.changed,				// true :操作员是否编辑过XXX
				bShow 		= opts.show,				// true :用户展示习惯TODO
				bStatus 	= opts.helpDoc.active,		// true :操作员编辑的展示字段
				height		= null,
				oThis		= this;
			iHeight = Ext.getDom('wrapForm'+sNum).style.height;
			btnShow = OButton.create('\u67E5\u770B\u5E2E\u52A9','btnCtn');				// 查看帮助
			btnShow.on('click',function(){
				// oThis.fnShowHide(['helpTitle','tWrp'],true);
				oThis.fnShowHide(['id2'],true);
				Ext.get('wrapForm'+sNum).setStyle('height',iHeight )
				this.hide();
				btnHide.show();
				btnEdit.show();
			});
			btnHide = OButton.create('\u9690\u85CF\u5E2E\u52A9','btnCtn');				//隐藏帮助
			btnHide.on('click',function(){
				oThis.fnShowHide(['id2'],false);
				Ext.get('wrapForm'+sNum).setStyle('height',"30px")
				this.hide();
				btnShow.show();
				btnEdit.hide();
			});
			btnEdit = OButton.create('\u7F16\u8F91','btnCtn');							//编辑
			//TODO:根据客户初始参数{}	获取客户按钮状态
			(bChanged||(bShow&&bStatus))?function(){
				btnHide.show();
				btnShow.hide();
				btnEdit.show();
			}():function(){
				btnShow.show();
				btnEdit.hide();
				btnHide.hide();
			}();
			
			btnEdit.on('click',function(){
				oThis.creatEditor(opts,'#id2');
				btnSave.show();
			});
			// btnEdit.on('click',function(){
				// oThis.createWindow(opts)
			// });
			btnSave = OButton.create('\u4FDD\u5B58','btnCtn');							//保存
			btnSave.hide();
			btnSave.on('click',function(){
				oThis.fnSave(opts);
				btnSave.hide();
				oThis.showSelf(opts)
			});
		},
		/**
		 * @:预留弹窗接口 
		 */
		//TODO:cookie记录window位置等信息
		// createWindow:function(obj){
			// function submitData() {
				// // var contentData = Ext.getCmp('content1').getValue();
				// var labelDom = Ext.getCmp('opeResult').el.dom;
				// if (!html) {
					// labelDom.innerHTML = "<font color='red'>木有任何数据</font>";
					// return;
				// };
				// Ext.Ajax.request({
					// url : 'receiver.jsp', // 文件接收url，使用jsp
					// params : {
						// 'shtml' : html
					// },
					// success : function() {
						// labelDom.innerHTML = "<font color='green'>提交成功</font>";
					// }
				// })
			// }
			// var win = Ext.create('Ext.window.Window', {
				// title : '配合Extjs',
				// width : 692,
				// height : 464,
				// layout : 'fit',
				// items : [{
					// id : 'content1',
					// xtype : 'textarea'
				// }],
				// buttons : [{
					// id : 'opeResult',
					// xtype : 'label',
					// text : '这是状态文本域',
					// html : ''
				// }, {
					// text : 'Ajax提交',
					// handler : submitData
				// }]
			// });
			// win.show();
			// this.creatEditor(obj);
		// },
		creatEditor:function(opts,renderId){
			if(oEditor&&(oEditor.editorId == opts.options.id)){
				oEditor.remove();
			};
			
			var options = opts.options,
				str = opts.helpHtml,
				editor;
			editor = KindEditor.create(renderId,options);		// TODO:布局basic
			editor.sync();										// 同步数据
			editor.html(str);
			oEditor = editor;
		},
		fnSave: function(opts){
			var htm,jP,fnS,fnF,oThis=this;
			//TODO:销毁editor的dom节点
			if (oEditor.isEmpty()) {
				alert('请完善内容。');
				return;
			};
			opts.helpDoc.helpContent = oEditor.html();
			jP = {
            	helpDoc:opts.helpDoc
            	};
			fnS= function(){
				alert('编辑保存数据成功！');
				//TODO:i18n
				opts.changed = true;
				oEditor.remove();
			};
			fnF=function(){
				alert('fuck，It was failure。');
				//TODO:i18n
			};
			oThis.sendRequest( opts.sUrlEditIt, jP, fnS, fnF );
		}
		/**
		 * 自定义事件接口 
		 */
		// eventBySelf: function(){
			// var coderName = "Rock";
			// var html = "<p>帅的一塌糊涂的人：{coderName}</p>";
			// var tpl = Ext.DomHelper.createTemplate(html);
			// tpl.insertFirst(Ext.get('id1'),this);
		// }
		,defaults:{
			// 帮助实体：
			// helpDoc:''
			helpDoc:{
				windowNum:'wjiefmwei',	// 帮助文档的ID	belongModule(首字母缩写)+belongMenu（首字母缩写）+windowNum(弹窗编号)+首编时间戳
				helpTitle:'',			// TODO:帮助文档标题		需更改帮助文档生成template
				helpContent:'<ol style="padding: 0 12px 12px;margin: 10px;background: #fff;">说明： <li>未开单理赔，只能正常理赔； </li><li>只有理赔金额不超过1000元时，才能使用快速理赔； </li><li>在填写索赔清单时，务必先填写货物托运清单，否则新增索赔清单时将选择不到货物。 </li><li>如果您尚未开通转账功能，请点击转“账功能申请”； </li><li>如果您在处理理赔时不知下一操作人是谁，请查看“理赔处理流程”； </li><li>收银员点击付款申请时，如果页面提示“在ERP中找不到理赔单”，请参考解决办法“XXX”； </li></ol>',
				// 帮助文档内容
				belongModule:'',		// 所属模块
				belongMenu:'',			// 所属导航
				windowNum:'',			// 弹窗编号
				active:true				// 新增、编辑时时传入		用于记录操作员操作，是否选择了”隐藏帮助“；
			}
			,oThis:null
			,name:''
			,show:true			// TODO:用于记录用户习惯，是否选择了”隐藏帮助“；
			,changed:true		// TODO:true(展示):false,		用于记录操作员操作，是否编辑了帮助文档；
			
			,templateHtml:'<ol style="padding: 0 12px 12px;margin: 10px;background: #fff;">说明： <li>未开单理赔，只能正常理赔； </li><li>只有理赔金额不超过1000元时，才能使用快速理赔； </li><li>在填写索赔清单时，务必先填写货物托运清单，否则新增索赔清单时将选择不到货物。 </li><li>如果您尚未开通转账功能，请点击转“账功能申请”； </li><li>如果您在处理理赔时不知下一操作人是谁，请查看“理赔处理流程”； </li><li>收银员点击付款申请时，如果页面提示“在ERP中找不到理赔单”，请参考解决办法“XXX”； </li></ol>'
			
			// XXX:页面内action是否固定
			,sUrlAdding:'addDoc.action'		// 带来好运的url(用来发送请求并新增编辑数据)——增
			,sUrlDelete:'delDoc.action'		// 带来好运的url(用来发送请求并删除单条数据)——删
			// TODO:action层有问题，要根据number获取到当前文档，然后来修改XXX
			,sUrlEditIt:'updDoc.action'		// 带来好运的url(用来发送请求并保存编辑数据)——改
			,sUrlSearch:'../common/searchDocByNum.action'	// 带来好运的url(用来发送请求并获取初始数据)——查
			
			,hasDoc:null		// 是否有帮助文档，初始如果有domId的话，需要发送初始化帮助文档的请求后确定
						
			,ajaxSuccess: null
			/**
			 *  editor编辑器配置项
			 */
			, options: {
				// cssData : 'body {font-size: 14px;}',
				// cssPath : '/css/index.css',
				// filterMode : true
				pasteType : 0,
				height : 280,
				id:'editor1',
				resizeType:0,
				minChangeSize:10,
				pasteType:1,
				afterBlur:function(){},		//编辑器失去焦点后后执行的回调函数：按照编辑器内文档是否改变来重置编辑器是否编辑的状态
				items:[
					"source",				//HTML代码
					"preview", 				//预览
					"undo", 				//后退
					"redo", 				//前进
					"cut", 					//剪切
					"copy", 				//复制
					"paste", 				//粘贴
					"wordpaste", 			//从Word粘贴
					"insertorderedlist",	//编号
					"insertunorderedlist",	//项目符号
					"subscript",			//下标
					"superscript", 			//上标
					"formatblock",			//段落
					"fontname",				//字体
					"bold"	,				//字体粗细
					"fontsize",				//文字大小
					"hr",					//插入横线
					"link",					//超级链接
					"unlink", 				//取消超级链接
					"fullscreen",			//全屏显示
					"lineheight",			//行距
					"quickformat",			//一键排版
					"template"				//插入模板
				]
				// ,afterCreate:function(){ }
				,afterBlur :function(){
					var l = this.html().length;
					if(l>=1000){
						alert('数据过长，请节制（1000字符内）。')
						MessageUtil.showMessage(i18n('i18n.helpdoc.inputSthPerfect'));	//数据过长，请节制。
						return false;
					};
				}
				,fontSizeTable:14
				,filterMode:false			//允许输入任何代码，不过滤标签
			}
			// end of editor编辑器配置项
			
		}
	});
	
	/**
	 * @method:公共方法属性定义
	 */
	Ext.apply(Depon.Lib.oDocHelper.prototype,{
		/**
		 * @method
         * @param {Object} overrides
         * @return null
		 */
		init : function(){
			alert('fuck')
		},
		sendRequest:function(u,p,s,f){
			Ext.Ajax.request({
				url : u, 						// 文件接收
				jsonData:p						// jsonParam : {}, 
				, success:function(response) {
					var result = Ext.decode(response.responseText);
					// TODO:增加显示提交状态label：labelDom.innerHTML = "<font color='green'>提交成功</font>";
					if (result.success) {
						s(result);
					} else {
						f(result);
					}
				}, failure:function(response) {
					var result = Ext.decode(response.responseText);
					f(result);
				}
			});
		}
		/**
		 * @method:为window结构提供dom铺垫
		 *  @param {win} 
		 */
		,setPanelForWin: function(win,n){
			var dom = win.el.dom,
				oldSty = dom.style;
			// win.y=0;win.doLayout();
			
			var position = win.getPosition(),
				x = position[0],
				y = (position[1]-parseInt(opts.sHeight))>0?(position[1]-parseInt(opts.sHeight)):0;
			win.setPosition(x,y);

			dom.style.height = (parseInt(oldSty.height) + parseInt(opts.sHeight)).toString() + 'px' ;
			console.log(dom.style.height);
			// dom.offsetTop = dom.offsetTop - 100;
			// dom.style.
			//TODO:if( opts.helpDoc&&opts.helpDoc.active){
			var shtm = '<div id="wrapForm'+n+'" style="height:'+ (parseInt(opts.sHeight)+30).toString() +'px;border:1px #ccc solid;margin-top:60px;border-radius:5px;background:#eee;color:#060;"><div id="id1'+n+'" style="height:24px;"></div><div id="id2'+n+'" style="height:160px;"></div></div>',
				div = document.createElement('div');
			div.innerHTML = shtm;
			dom.appendChild(div);
		},
		/**
		 * 以下按钮显隐封装：fnShowHide
		 * fnShowHide(['id2'],false)
		 */
		// TODO:按钮分离
		fnShowHide : function(aId,b){
			for(var i in aId){
				function setDisplay(){
					var ii = i;
					if(b){
						Ext.get(aId[ii]).setStyle("display","block");
					}else{
						Ext.get(aId[ii]).setStyle("display","none");
					};
				};
				return setDisplay()
			};
			// Ext.getCmp('relayTimeShow').setValue('');
		},
		fnEditorChange: function(){
			
		}
	});

	/**
	 * 以下为按钮(btnShow、btnEdit、btnSave)事件触发:
	 * Ext.EventManager.on() :
	 * ( String/HTMLElement el, String eventName, Function handler, [Object scope], [Object options] )
	 */
})(Ext);