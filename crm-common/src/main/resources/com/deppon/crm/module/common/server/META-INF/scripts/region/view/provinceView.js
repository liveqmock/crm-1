
/**
 * 省份容器 panel
 */
Ext.define('ProvinceContainerPanel',{
	extend:'TabContentPanel'
	,layout:'border'
	,padding:'10'
	,title:i18n('i18n.province.title.tab')/*省份信息维护*/
	,provinceSearchPanel:null  //省份查询条件panel
	,provinceCenterPanel:null  //省份中间布局快
	,initComponent:function(){
		var me = this;
		me.provinceCenterPanel = Ext.create('ProvinceCenterPanel',{
			'region':'center'
		});
		me.provinceSearchPanel = Ext.create('ProvinceSearchPanel',{
			'region':'north','parent':me
		});
		Ext.applyIf(me,{
			items:[me.provinceSearchPanel,me.provinceCenterPanel]
		});
		me.callParent(arguments);
	}
});

/**
 * 省份查询条件 panel
 */
Ext.define('ProvinceSearchPanel',{
	extend:'NotitleBGroundFormPanel'
	,layout:{type:'table',columns:2}
	,parent:null  // 父级总panel
	,initComponent:function(){
		var me = this;
		Ext.applyIf(me,{
			'items':me.getItems()
		});
		me.callParent(arguments);
	}
	/*省份查询  事件*/
	,searchProvinceEvent:function(){
		var me = this;
		if(DpUtil.isEmpty(Ext.getCmp('provinceName').getValue())){
//			Ext.MessageBox.alert(i18n('i18n.util.msg.title')/*提示*/,i18n('i18n.province.msg.name_not_null')/*请填写省份名称*/);return;
			MessageUtil.showMessage(i18n('i18n.province.msg.name_not_null')/*请填写省份名称*/);return;
		}
		me.parent.provinceCenterPanel.provinceGridPanel.getStore().loadPage(1);
	}
	,getItems:function(){
		var me = this;
		return [
			{
				fieldLabel:i18n('i18n.province.name')/*省份名称*/
				,xtype:'textfield',name:'provinceName',id:'provinceName'
			}
			,{
				text:i18n('i18n.util.btn.search')/*查询*/
				,xtype:'button',margin:'-5 0 0 5',scope:me,width:60,handler:me.searchProvinceEvent
			}
		];
	}
});


/**
 * 省份显示中间部分 panel
 */
Ext.define('ProvinceCenterPanel',{
	extend:'BasicPanel'
	,layout:'border'
	,provinceOperatePanel:null  //省份增加、删除、修改 panel
	,provinceGridPanel:null  //省份列表 panel
	,initComponent:function(){
		var me = this;
		me.provinceOperatePanel = Ext.create('ProvinceOperatePanel',{
			'region':'north','parent':me
		});
		me.provinceGridPanel = Ext.create('ProvinceGridPanel',{
			'region':'center'
		});
		Ext.applyIf(me,{
			items:[me.provinceOperatePanel,me.provinceGridPanel]
		});
		me.callParent(arguments);
	}
});

/**
 * 省份省份增加、删除、修改 panel
 */
Ext.define('ProvinceOperatePanel',{
	extend:'NormalButtonPanel'
	,parent:null  // 父级总panel
	,initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent(arguments);
	}
	,getItems:function(){
		var me = this;
		return [
			{
				xtype:'leftbuttonpanel',items:[
					{
						text:i18n('i18n.util.btn.add')/*新增*/
						,xtype:'button',scope:me,handler:me.provinceAdd_event
					}
					,{
						text:i18n('i18n.util.btn.update')/*修改*/
						,xtype:'button',scope:me,handler:me.provinceUpdate_event
					}
					,{
						text:i18n('i18n.util.btn.delete')/*删除*/
						,scope:me,xtype:'button',handler:me.provinceDelete_event
					}
				]
			},
			{xtype:'middlebuttonpanel'}
		];
	}
	//新增
	,provinceAdd_event:function(){
		var me = this;
		provinceWindow.setTitle(i18n('i18n.province.win_title.add')/*新增省份信息*/);
		provinceWindow.show();
		provinceWindow.load_data({'parent':me.parent,'provinceModel':null});
	}
	//修改
	,provinceUpdate_event:function(){
		var me = this;
		var selection = me.parent.provinceGridPanel.getSelectionModel().getSelection();
		if(DpUtil.isEmpty(selection) || selection.length==0 || selection.length>1){
//			Ext.MessageBox.alert(i18n('i18n.util.msg.title')/*提示*/,i18n('i18n.util.msg.data_no_select_one'))/*请选择一条数据*/;return;
			MessageUtil.showMessage(i18n('i18n.util.msg.data_no_select_one'))/*请选择一条数据*/;return;
		}
		provinceWindow.setTitle(i18n('i18n.province.win_title.update')/*修改省份信息*/);
		provinceWindow.show();
		provinceWindow.load_data({'parent':me.parent,'provinceModel':selection[0]});
	}
	//删除
	,provinceDelete_event:function(){
		var me = this;
		var selection = me.parent.provinceGridPanel.getSelectionModel().getSelection();
		if(DpUtil.isEmpty(selection) || selection.length==0){
//			Ext.MessageBox.alert(i18n('i18n.util.msg.title')/*提示*/,i18n('i18n.util.msg.data_no_select')/*请选择数据*/);return;
			MessageUtil.showMessage(i18n('i18n.util.msg.data_no_select')/*请选择数据*/);return;
		}
		var list = new Array();
		Ext.each(selection,function(obj){
			list.push(obj.data);
		});
		processingMask.show();
		//执行成功
		var successFn = function(response){
//			processingMask.hide();Ext.MessageBox.alert(i18n('i18n.util.msg.title')/*提示*/,i18n('i18n.util.msg.delete_succeed')/*删除成功*/);
			processingMask.hide();MessageUtil.showMessage(i18n('i18n.util.msg.delete_succeed')/*删除成功*/);
			me.parent.provinceGridPanel.getStore().loadPage(1);	
		}
		
		//执行失败
		var failFn = function(response){
			processingMask.hide();MessageUtil.showMessage(i18n('i18n.util.msg.delete_fail')/*删除失败*/);
		}
		DpUtil.requestJsonAjax('deleteProvinceList.action',{'provinceList':list},successFn,failFn);
	}
});

/**
 * 省份显示列表 gridpanel
 */
Ext.define('ProvinceGridPanel',{
	extend:'SearchGridPanel'
	,initComponent:function(){
		var me = this;
		Ext.applyIf(me,{
			'store':Ext.create('ProvinceStore',{
				listeners:{
					beforeload:function(store, operation, eOpts){
						var searchParams = {
							'searchCondition.name':Ext.getCmp('provinceName').getValue()
						};
						Ext.apply(operation,{params:searchParams});
					}
				}
			})
			,'columns':me.getColumns()
		});
		me.dockedItems = me.getMyDockedItems();
		this.callParent(arguments);
	}
	,selModel:new Ext.selection.CheckboxModel({checkOnly:true})
	,getColumns:function(){
		var me = this;
		return [
			Ext.create('Ext.grid.RowNumberer',{header:i18n('i18n.util.serial_number'),width:40})
			,{header:i18n('i18n.province.name')/*省份名称*/,width:105,dataIndex:'name'}
			,{header:i18n('i18n.province.pinyin')/*拼音*/,width:150,dataIndex:'pinyin'}
			,{header:i18n('i18n.province.pyjm')/*拼音简称*/,width:70,dataIndex:'pyjm'}
			,{header:i18n('i18n.province.number')/*区划代码*/,width:100,dataIndex:'number'}
			,{header:i18n('i18n.province.lastModifyName')/*最后修改人*/,width:100,dataIndex:'lastModifyName'}
			,{
				header:i18n('i18n.province.modifyDate')/*最后修改时间*/,width:150,dataIndex:'modifyDate'
				,renderer:function(value) {
					if(value != null){return Ext.Date.format(new Date(value),'Y-m-d H:i');
					}else{return null;}
				}
			}
		];
	}
	//分页条
	,getMyDockedItems :function(){
		var me = this;
		return [{
			xtype:'pagingtoolbar'
			,plugins:[Ext.create('Ext.ux.PageComboResizer')]
			,store : me.store,dock : 'bottom',displayInfo :true
		}];
	}
});


/** 
 * 省份保存或修改 弹出框 声明
 */
Ext.define('ProvinceWindow', {
    extend:'PopWindow'
	,width:460,height:150
    ,layout:'fit',modal:true
    ,provinceFormPanel:null // 省份form
    ,initComponent: function() {
        var me = this;
    	me.provinceFormPanel = Ext.create('ProvinceFormPanel');
    	
    	Ext.applyIf(me,{
    		items:[me.provinceFormPanel]
    		,buttons:me.getButtons()
    	});
        me.callParent(arguments);
    }
    ,load_data:function(opt){
    	var me = this;
    	Ext.apply(me,opt);
    	if(DpUtil.isEmpty(me.provinceModel)){
    		me.provinceFormPanel.getForm().loadRecord(new ProvinceModel());
    	}else{me.provinceFormPanel.getForm().loadRecord(me.provinceModel);}
    }
    ,my_hide:function(flag){
    	var me = this;
    	me.hide();
    	if(flag && flag===true){
    		me.parent.provinceGridPanel.getStore().loadPage(1);
    	}
    }
    ,getButtons:function(){
    	var me = this;
    	return [
    		{text:i18n('i18n.util.btn.submit')/*提交*/,listeners:{scope:me,click:me.submit_event}}
    		,{text:i18n('i18n.util.btn.close')/*关闭*/,listeners:{scope:me,click:me.my_hide}}
    	];
    }
    ,submit_event:function(){
    	var me = this;
		var searchForm = me.provinceFormPanel.getForm();
		if(DpUtil.isEmpty(searchForm)){return;}
		var name = searchForm.findField('name').getValue();
		var number = searchForm.findField('number').getValue();
		var pinyin = searchForm.findField('pinyin').getValue();
		var pyjm = searchForm.findField('pyjm').getValue();
    	if(DpUtil.isEmpty(name) || DpUtil.isEmpty(number) || DpUtil.isEmpty(pinyin) || DpUtil.isEmpty(pyjm)){
    		MessageUtil.showMessage(i18n('i18n.province.msg.provinceInfo_not_null')/*请填写省份信息*/);return;
		}
		processingMask.show();
		var params = {
			'province':{
				'name':name,'number':number,'pinyin':pinyin,'pyjm':pyjm
			}
		}
		if(!DpUtil.isEmpty(me.provinceModel)){/*若为修改则添加主键*/
			params.province['fid'] = me.provinceModel.get('fid');
		}
    	//执行成功
		var successFn = function(response){
			processingMask.hide();
			me.my_hide(true);
		}
		//执行失败
		var failFn = function(response){
			processingMask.hide();
//			Ext.MessageBox.alert(i18n('i18n.util.msg.title')/*提示*/,response.message);
			MessageUtil.showMessage(response.message);
		}
		DpUtil.requestJsonAjax('saveOrUpdateProvince.action',params,successFn,failFn);
    }
});

/**
 * 省份 from
 */
Ext.define('ProvinceFormPanel', {
	extend: 'NoTitleFormPanel'
   	,layout: {columns:2,type: 'table'}
    ,defaultType:'textfield',defaults:{labelWidth:80,width:200}
    ,initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            items: [
                {fieldLabel:i18n('i18n.province.name')/*省份名称*/,name:'name'},
                {fieldLabel:i18n('i18n.province.number')/*区划代码*/,name:'number'},
                {fieldLabel:i18n('i18n.province.pinyin')/*拼音*/,name:'pinyin'},
                {fieldLabel:i18n('i18n.province.pyjm')/*拼音简码*/,name:'pyjm'}
            ]
        });
        me.callParent(arguments);
    }
});

var provinceWindow = null;/*省份弹出框*/
Ext.onReady(function(){
	if(provinceWindow==null || !provinceWindow){provinceWindow = Ext.create('ProvinceWindow');}
});