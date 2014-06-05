﻿﻿/**
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

/**.
 * <p>
 * 解决EmptyText不同浏览器的不同效果（现修改为鼠标离开自动清除EmptyText）<br/>
 * <p>
 * @param 
 * @author 张登
 * @时间 2012-6-16
 */
if ( Ext.isGecko || Ext.isWebkit || Ext.isChrome)//判断火狐、IE、谷歌
    Ext.supports.Placeholder = false;
/**.
 * <p>
 * 重写Ext form Text<br/>
 * <p>
 * @param 
 * @author 李学兴
 * @时间 2012-6-16
 */
Ext.define('DpTextField',{
	extend:'Ext.form.Text', 
	alias : 'widget.dptextfield',
	//扩展getValue() 方法 去掉前后空格  用于提交
	getValue:function(){
		var val = this.callParent(arguments);
        return Ext.isEmpty(val)?val:val.trim();//return val;
    },
    //扩展getRawValue() 方法 去掉前后空格  用于表单验证
    getRawValue: function() {
    	var val = this.callParent();
        return Ext.isEmpty(val)?val:val.trim();//return val;
    }
});
/**.
 * <p>
 * 有readonly样式的combo组件<br/>
 * <p>
 * @param 
 * @author 李学兴
 * @时间 2012-6-16
 */
Ext.define('DpComboBox',{
	extend:'Ext.form.ComboBox',
	alias : ['widget.dpcombobox', 'widget.dpcombo'],
	//扩展setReadOnly 方法
	setReadOnly:function(readOnly){
		this.callParent(arguments);
		if(readOnly){
			this.addCls('readonly');
		}else{
			this.removeCls('readonly');
		}
	}
});
/**.
 * <p>
 * 有readonly样式的numb组件<br/>
 * <p>
 * @param 
 * @author 李学兴
 * @时间 2012-6-16
 */
Ext.define('DpNumberField',{
	extend:'Ext.form.Number',
	alias : ['widget.dpnumberfield', 'widget.dpnumber'],
	//扩展setReadOnly 方法
	setReadOnly:function(readOnly){
		this.callParent(arguments);
		if(readOnly){
			this.addCls('readonly');
		}else{
			this.removeCls('readonly');
		}
	}
});
/**.
 * <p>
 * 有readonly样式的Date组件<br/>
 * <p>
 * @param 
 * @author 李学兴
 * @时间 2012-6-16
 */
Ext.define('DpDateField',{
	extend:'Ext.form.Date', 
	alias : ['widget.dpdatefield','widget.dpdate'],
	//扩展setReadOnly 方法
	setReadOnly:function(readOnly){
		this.callParent(arguments);
		if(readOnly){
			this.addCls('readonly');
		}else{
			this.removeCls('readonly');
		}
	}
});
/**
 * 解决session失效后的ajax请求
 * @author 张登
 * @时间 2012-6-1
 */
Ext.Ajax.on('requestcomplete',checkUserSessionStatus, this);    
function checkUserSessionStatus(conn,response,options){    
	if(!Ext.isEmpty(response.getResponseHeader)){
		var sessionStatus = response.getResponseHeader("sessionstatus");  
	    if(typeof(sessionStatus) != "undefined"){
	    	Ext.Msg.show({
	    	    title:'CRM提醒您:',
	    	    width:110+10*15,
	    	    msg:'<div id="message">'+'会话超时，请重新登录!'+'</div>',
	    	    buttons: Ext.Msg.OK,
	    	    icon: Ext.MessageBox.WARNING,
	    	    callback:function(e){
	    	    	if(!Ext.isEmpty(fun)){
	    	    		if(e=='ok'){
	    	    			window.location.href = "../login/logout.action"; 
	    		    	}
	    	    	}
	    	    }
	    	});  
	     }
	}
	if(!Ext.isEmpty(response.responseText)){
		var result = Ext.decode(response.responseText);
		if(!Ext.isEmpty(result.success) && !Ext.isEmpty(result.isException) && !Ext.isEmpty(result.message)){
			if(!result.success && result.isException){
				Ext.Msg.show({
				    title:'CRM提醒您:',
				    buttons: Ext.Msg.OK,
				    width:110+result.message.length*15,
				    msg:'<div id="message">'+result.message+'</div>',
				    icon: Ext.MessageBox.WARNING
				});
			}
		}
	}
};  

/**
 * 改变comboxObj选择的图标。使用方法直接xtype:'queryCombox'就可以了
 * @param comboxObj
 * @returns
 * @author 张登
 * @时间 2012-4-12
 */
Ext.define('QueryCombox',{
	extend:'Ext.form.ComboBox',
	alias : 'widget.queryCombox',
	initComponent:function(){
		var me = this;
		me.addListener('render',me.renderFn);
		this.callParent();
	},
	renderFn:function(comboxObj){
		if(!Ext.isEmpty(comboxObj)){//判断自己和子元素是否存在
			if(!Ext.isEmpty(comboxObj.getEl())){
				if(!Ext.isEmpty(comboxObj.getEl().dom)){
					if(!Ext.isEmpty(comboxObj.getEl().dom.childNodes[1])){
						if(!Ext.isEmpty(comboxObj.getEl().dom.childNodes[1].childNodes[2])){
							if(!Ext.isEmpty(comboxObj.getEl().dom.childNodes[1].childNodes[2].childNodes[0])){
								comboxObj.getEl().dom.childNodes[1].childNodes[2].childNodes[0].style.backgroundImage ="url(../images/common/common/search-trigger.gif)";
							};
						};	
					};	
				};
			};
		};
	},
	//扩展setReadOnly 方法,增加只读样式
	setReadOnly:function(readOnly){
		this.callParent(arguments);
		if(readOnly){
			this.addCls('readonly');
		}else{
			this.removeCls('readonly');
		}
	}
});
/**
 * 解决当弹出框口比当前窗口大，显示、隐藏滚动条的问题
 * @param viewport 容器，isShow显示 
 * 调用方式：在新增的时候写initScroll(viewport,ture),
 * 在window关闭或者是hide事件中调用在新增的时候写initScroll(viewport,false)
 * @returns
 * @author 张登
 * @时间 2012-4-12
 */
function initScroll(d,viewport,isShow){
	if(isShow==true){
		d.getElementsByTagName("html")[0].style.overflowY="auto";
		d.getElementsByTagName("html")[0].style.overflowX="auto";
	}else{
		d.body.scrollLeft=0;
		d.body.scrollTop=0;
		d.getElementsByTagName("html")[0].style.overflowY="hide";
		d.getElementsByTagName("html")[0].style.overflowX="hide";
	}
	viewport.doLayout();
}

//一般的容器(无样式)，用于按钮容器继承
Ext.define('BasicPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.basicpanel',
	border : false,
	autoScroll : true,
	layout : 'fit'
});

// --------------------------------------------------- //
// --------- 无边框的表单容器 ------------- //
// -------------------------------------------------- //
Ext.define('BasicFormPanel', {
	extend : 'Ext.form.Panel',
	border : false,
	alias : 'widget.basicformpanel',
	layout : 'fit'
});
//--------------------------------------------------- //
//--- vbox的普通panel  2012-04-13  14:30新增  bdd------ //
//-------------------------------------------------- //
Ext.define('BasicVboxPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.basicvboxpanel',
	border : false,
	autoScroll : true,
	layout:{
		type:'vbox',
		align:'stretch'
	},
	cls:'basicvboxpanel'
});
//--------------------------------------------------- //
//--- vbox的普通panel  2012-04-14 09:52新增  bdd------ //
//-------------------------------------------------- //
Ext.define('BasicVboxCenterPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.basicvboxcenterpanel',
	border : false,
	autoScroll : true,
	layout:{
		type:'vbox',
		align:'center',
		padding:'5'
	},
	defaults:{margins:'0 0 5 0'}
});
//--------------------------------------------------- //
//--- hbox的普通panel  2012-04-1 416:20新增  bdd------ //
//-------------------------------------------------- //
Ext.define('BasicHboxCenterPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.basichboxcenterpanel',
	border : false,
	autoScroll : true,
	 layout: {
              	type: 'hbox',
                padding:'5',
                pack:'center'
             },
	defaults:{margins:'0 5 5 0'}
});
//--------------------------------------------------- //
//--- vbox的form.panel  2012-04-13  14:30新增  bdd------ //
//-------------------------------------------------- //
Ext.define('BasicVboxFormPanel', {
	extend : 'Ext.form.Panel',
	alias : 'widget.basicvboxformpanel',
	border : false,
	autoScroll : true,
	layout:{
		type:'vbox',
		align:'stretch'
	}
});
//--------------------------------------------------- //
//--- hbox的普通panel  2012-04-13  14:30新增  bdd------ //
//-------------------------------------------------- //
Ext.define('BasicHboxPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.basichboxpanel',
	border : false,
	autoScroll : true,
	layout:{
		type:'hbox',
		align:'stretch'
	}
});
//--------------------------------------------------- //
//--- hbox的form.panel  2012-04-13  14:30新增  bdd------ //
//-------------------------------------------------- //
Ext.define('BasicHboxFormPanel', {
	extend : 'Ext.form.Panel',
	alias : 'widget.basichboxformpanel',
	border : false,
	autoScroll : true,
	layout:{
		type:'hbox',
		align:'stretch'
	}
});
//--------------------------------------------------- //
//--- vbox的普通panel  2012-04-14 09:52新增  bdd------ //
//-------------------------------------------------- //
Ext.define('BasicVboxCenterPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.basicvboxcenterpanel',
	border : false,
	autoScroll : true,
	layout:{
		type:'vbox',
		align:'center',
		padding:'5'
	},
	defaults:{margins:'0 0 5 0'}
});
//--------------------------------------------------- //
//--- hbox的普通panel  2012-04-14 416:20新增  bdd------ //
//-------------------------------------------------- //
Ext.define('BasicHboxCenterPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.basichboxcenterpanel',
	border : false,
	height:30,
	autoScroll : true,
	 layout: {
            	type: 'hbox',
              padding:'2.5',
              pack:'center'
           },
	defaults:{margins:'0 5 0 0'}
});

//内容左对齐的容器
Ext.define('LeftAlignedPanel', {
	extend : 'BasicPanel',
	alias : 'widget.leftalignedpanel',
	layout:{
		type:'hbox',
		pack:'start',
        align:'middle'
	},
	defaults : {
		margins : '0 5 15 0'
	}
});

//--------------------------------------------- //
//----------只读的文本控件--------------- //
//------------------------------------------- //

Ext.define('ReadOnlyTextField', {
	extend : 'Ext.form.field.Text',
	alias : 'widget.readonlytextfield',
	readOnly:true,
	cls:'readonly'
});

Ext.define('ReadOnlyTextArea', {
	extend : 'Ext.form.field.TextArea',
	alias : 'widget.readonlytextarea',
	readOnly:true,
	cls:'readonly'
});

//--------------------------------------------- //
//----------左对齐的displayfield--------------- //
//------------------------------------------- //

Ext.define('BasicleftDispField',{
	extend:'Ext.form.field.Display',
	alias : 'widget.basicleftdispfield',
	width:90,
	cls:'leftdisplayfield'
});

// --------------------------------------------- //
// -- -----------表单控件----------------- //
// ------------------------------------------- //
// 样式：无标题(无边框+无背景色)
// 适用于:所有多页签里简单的查询组合中，
// 用于填放查询条件的容器,和FormAndBtnPanel配套使用.
Ext.define('NoBorderFormPanel', {
	extend : 'BasicFormPanel',
	alias : 'widget.noborderformpanel',
	cls : 'noborderform'
});

// 样式：无标题的表单(无边框+无背景色)
// 适用于：弹出框及非弹出框中用于文本录入的表单
Ext.define('NoTitleFormPanel', {
	extend : 'BasicFormPanel',
	alias : 'widget.notitleformpanel',
	cls : 'nomal_feildset'
});

// 样式：有标题表单(有边框+有背景色)
// 适用于：非弹出框中有标题的表单
Ext.define('TitleFormPanel', {
	extend : 'BasicFormPanel',
	alias : 'widget.titleformpanel',
	cls : 'form_fieldset'
});

// 样式：有标题表单(默认的fieldset的样式，有边框且title嵌套在边框中，无背景色)
// 适用于：弹出框中有标题的表单
Ext.define('PopTitleFormPanel', {
	extend : 'Ext.form.Panel',
	alias : 'widget.poptitleformpanel',
	cls : 'popup_form_feildset'
});

// 样式：无标题+有背景色+有边框
// 适用于：弹出框及非弹出框中查询条件的录入
Ext.define('SearchFormPanel', {
	extend : 'BasicFormPanel',
	alias : 'widget.basicsearchformpanel',
	cls : 'formpanel'
});

//无标题有背景色的formpanel  04-14 bdd修改  之前为:
Ext.define('NotitleBGroundFormPanel', {
	extend : 'BasicPanel',
	alias : 'widget.notitlebgroundformpanel',
	cls:'notitlebgroundformpanel'
});
//无标题有背景色的formpanel(用于普通页面)
//(适用于panel里只有一行控件，包括查询条件及查询按钮)
Ext.define('SimpBGroundSearchPanel', {
	extend : 'BasicPanel',
	alias : 'widget.simpbgroundsearchpanel',
	cls:'simpbgroundsearchpanel'
});
//无标题无背景色的formpanel(用于多页签或则填充框中)
//(适用于panel里只有一行控件，包括查询条件及查询按钮)
Ext.define('SimpSearchPanel', {
	extend : 'BasicPanel',
	alias : 'widget.simpsearchpanel',
	cls:'simpsearchpanel'
});
// fieldSet
Ext.define('BasicFieldSet', {
	extend : 'Ext.form.FieldSet',
	alias : 'widget.basicfiledset',
	border : false
});
// --------------------------------------------- //
// -- -----------表格控件----------------- //
// -------------------------------------------- //
// (非弹出框)表格控件
Ext.define('SearchGridPanel', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.searchgridpanel',
	listeners: {
    	scrollershow: function(scroller) {
    		if (scroller && scroller.scrollEl) {
				scroller.clearManagedListeners(); 
				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
    		}
    	}
    },
	cls : 'searchresultgrid'
});

// (弹出框)表格
// 适用于：1.弹出框中所有表格. 2.弹出框及非弹出框中的多页签中
// 3.当普通页面中，需要在该表格周围填充很多其他控件时候使用。
Ext.define('PopupGridPanel', {
	extend : 'Ext.grid.Panel',
	listeners: {
    	scrollershow: function(scroller) {
    		if (scroller && scroller.scrollEl) {
				scroller.clearManagedListeners(); 
				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
    		}
    	}
    },
	alias : 'widget.popupgridpanel',
	cls : 'popup_grid'
});

//(弹出框)表格
//跟PopupGridPanel使用场景一样，只是当多个PopupGridPanel组合作为某个Panel的子内容时使用
Ext.define('PopupInnerGridPanel', {
	extend : 'Ext.grid.Panel',
	listeners: {
    	scrollershow: function(scroller) {
    		if (scroller && scroller.scrollEl) {
				scroller.clearManagedListeners(); 
				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
    		}
    	}
    },
	alias : 'widget.innerpopupgridpanel',
	cls : 'popupinner_grid'
});

// ------------------------------------------------------ //
// -- -------表单+按钮 组合查询容器-------- //
// ----------------------------------------------------- //
// 样式：无边框的容器
// 用途：该容器适用于多页签中，表单和按钮的简单组合查询。
// 它里面填充少数的查询条件和一个查询按钮;
Ext.define('FormAndBtnPanel', {
	extend : 'Ext.form.Panel',
	border : false,
	alias : 'widget.formandbtnpanel',
	cls : 'formandbtnpanel'
})
// ------------------------------------------------------ //
// -- ------弹出框中的按钮控件-------------- //
// ---------------------------------------------------- //
// 普通的按钮容器
Ext.define('PopButtonPanel', {
	extend : 'BasicPanel',
	alias : 'widget.popbuttonpanel',
	layout : {
		type : 'hbox'
	},
	height : 36,
	cls : 'poptool_button'
});
// 左边按钮面板
Ext.define('PopLeftButtonPanel', {
	extend : 'BasicPanel',
	cls : 'poptoolsbarleft',
	alias : 'widget.popleftbuttonpanel',
	width : 300,
	layout : {
		type : 'hbox',
		pack : 'start',
		padding : '5',
		align : 'middle'
	},
	defaults : {
		margins : '0 5 0 0'
	}
});
// 右边按钮面板
Ext.define('PopRightButtonPanel', {
	extend : 'BasicPanel',
	alias : 'widget.poprightbuttonpanel',
	width : 200,
	cls : 'poptoolsbarright',
	layout : {
		type : 'hbox',
		padding : '5',
		pack : 'end',
		align : 'middle'
	},
	defaults : {
		margins : '0 5 0 0'
	}
});
// ---------------------------------------------//
// ------------非弹出框 按钮---------------------//
// --------------------------------------------//

// 主按钮面板
Ext.define('NormalButtonPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.normalbuttonpanel',
	layout : {
		type : 'hbox'
	},
	height : 36,
	cls : 'toolsbar'
});
// 左边按钮面板
Ext.define('LeftButtonPanel', {
	extend : 'BasicPanel',
	cls : 'toolsbarleft',
	alias : 'widget.leftbuttonpanel',
	width : 300,
	layout : {
		type : 'hbox',
		pack : 'start',
		padding : '5',
		align : 'middle'
	},
	defaults : {
		margins : '0 5 0 0'
	}
});
// 右边按钮面板
Ext.define('RightButtonPanel', {
	extend : 'BasicPanel',
	alias : 'widget.rightbuttonpanel',
	width : 200,
	cls : 'toolsbarright',
	layout : {
		type : 'hbox',
		padding : '5',
		pack : 'end',
		align : 'middle'
	},
	defaults : {
		margins : '0 5 0 0'
	}
});
// 中间按钮面板
Ext.define('MiddleButtonPanel', {
	extend : 'BasicPanel',
	alias : 'widget.middlebuttonpanel',
	flex : 1
});

// ---------------------------------------------//
// -----------------弹出框控件-----------//
// --------------------------------------------//
Ext.define('PopWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.basicwindow',
	modal : true,
	constrainHeader: true,
	cls : 'popup'
});
// ---------------------------------------------//
// ---------------多页签容器-------------//
// --------------------------------------------//
// 页签总容器
Ext.define('NormalTabPanel', {
	extend : 'Ext.tab.Panel',
	alias : 'widget.normaltabpanel',
	cls : 'tab_panel'
});
// 各个具体的子页签
Ext.define('TabContentPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.tabcontentpanel',
	cls : 'tab_panel_content'
});

// ---------------------------------------------//
// -----实时创建会员模块的(校验信息)控件样式------//
// --------------------------------------------//
// 总panel
Ext.define('VerifyPanel', {
	extend : 'BasicFormPanel',
	border : false,
	alias : 'widget.verifypanel',
	cls : 'verifypanel'
});
// 左边及右边的单独panel
Ext.define('WholePanel', {
	extend : 'BasicPanel',
	alias : 'widget.wholepanel',
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	cls : 'wholepanel'
});
// 存放title的panel
Ext.define('TitlePanel', {
	extend : 'BasicPanel',
	alias : 'widget.titlepanel',
	height : 25,
	cls : 'titlepanel'
});
// 展示具体内容的panel
Ext.define('BottomPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.bottompanel',
	flex : 1,
	cls : 'bottompanel'
});
// 展示校验结果的panel
Ext.define('ResultField', {
	extend : 'Ext.form.TextField',
	alias : 'widget.resultfield',
	cls : 'resultfield'
});
//
Ext.define('InnerGridPanel', {
	extend : 'Ext.grid.Panel',
	listeners: {
    	scrollershow: function(scroller) {
    		if (scroller && scroller.scrollEl) {
				scroller.clearManagedListeners(); 
				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
    		}
    	}
    },
	alias : 'widget.innergrid',
	cls : 'inner_grid'
});

// 包括一个标题容器和一个表格容器的panel
Ext.define('TitleGridPanel', {
	extend : 'Ext.grid.Panel',
	listeners: {
    	scrollershow: function(scroller) {
    		if (scroller && scroller.scrollEl) {
				scroller.clearManagedListeners(); 
				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
    		}
    	}
    },
	alias : 'widget.titlegridpanel',
	cls : 'popup_grid'
});

// -----------针对有标题有按钮的表格样式---------
//标题
Ext.define('TitleDisplayField',{
	extend:'Ext.form.field.Display',
	alias : 'widget.titledisplayfield',
	width:90,
	cls:'titledisplayfield'
});

//存放textareafield的panel
Ext.define('TextAreaPanel', {
	extend : 'BasicPanel',
	alias : 'widget.textareapanel',
	cls:'textareapanel'
});

//上方的panel用于存放标题或者按钮
Ext.define('TopPanel', {
	extend : 'BasicPanel',
	alias : 'widget.toppanel',
	layout:{
		type:'hbox',
		align:'stretch'
	},
	height : 25
});
//用于存放按钮
Ext.define('BtnPanel', {
	extend : 'BasicPanel',
	alias : 'widget.btnpanel',
	flex:1,
	layout:{
		type:'hbox',
		pack : 'end'
	},
	defaults : {
		margins : '0 5 0 0'
	}
});
//有表单有操作按钮的表单容器(适用于 订单处理 模块)
Ext.define('OperBtnFormPanel', {
	extend : 'BasicPanel',
	alias : 'widget.operbtnformpanel',
	cls:'operbtnformpanel'
});

//操作按钮横向布局(比如：上一页 下一页两个按钮的样式)
Ext.define('OperBtnPanel', {
	extend : 'BasicPanel',
	alias : 'widget.operbtnpanel',
	cls:'operbtnpanel'
});
//dateTimeField日期转换成中文
if(Ext.Date){
    Ext.Date.monthNames = [
       "一月",
       "二月",
       "三月",
       "四月", 
       "五月",
       "六月",
       "七月",
       "八月",
       "九月",
       "十月",
       "十一月",
       "十二月"
    ];

    Ext.Date.dayNames = [
       "日",
       "一",
       "二",
       "三",
       "四",
       "五",
       "六"
    ];

    Ext.Date.formatCodes.a = "(this.getHours() < 12 ? '上午' : '下午')";
    Ext.Date.formatCodes.A = "(this.getHours() < 12 ? '上午' : '下午')";
}

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
					alert('\u5F00\u53D1\u5458,\u8BF7\u4F20\u5165\u6B63\u786E\u7684\u5E2E\u52A9\u7F16\u7801\uFF01');
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
			// alert('fuck')
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

/**
 * liyi
 * 解决dpap升级date NaN转换问题
 */
Ext.apply(Ext.data.Field.prototype,{defaultValue:null});

/**
 * 解决dpap升级分页start NaN问题
 */
Ext.override(Ext.toolbar.Paging, { 
	onLoad : function(){
        var me = this,
            pageData,
            currPage,
            pageCount,
            afterText;  

        if (!me.rendered) {
            return;
        }

        pageData = me.getPageData();
        currPage = pageData.currentPage;
        pageCount = pageData.pageCount;
        afterText = Ext.String.format(me.afterPageText, isNaN(pageCount) ? 1 : pageCount);

        me.child('#afterTextItem').setText(afterText);
        me.child('#inputItem').setValue(currPage);
        me.child('#first').setDisabled(currPage === 1);
        me.child('#prev').setDisabled(currPage === 1);
        me.child('#next').setDisabled(isNaN(pageCount)||currPage === pageCount);
        me.child('#last').setDisabled(isNaN(pageCount)||currPage === pageCount);
        me.child('#refresh').enable();
        me.updateInfo();
        me.fireEvent('change', me, pageData);
    }});
    
//取消悬浮框5秒消失
Ext.apply(Ext.tip.QuickTip.prototype,{dismissDelay:0});
if(Ext.toolbar.Paging){
	Ext.apply(Ext.toolbar.Paging.prototype,{beforePageText:'第'});    	
}