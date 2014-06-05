
/**
 * 区县容器 panel
 */
Ext.define('AreaContainerPanel',{
	extend:'TabContentPanel'
	,layout:'border'
	,padding:'10'
	,title:i18n('i18n.area.title.tab')/*区县信息维护*/
	,areaSearchPanel:null  //区县查询条件panel
	,areaCenterPanel:null  //区县中间布局快
	,initComponent:function(){
		var me = this;
		me.areaCenterPanel = Ext.create('AreaCenterPanel',{
			'region':'center'
		});
		me.areaSearchPanel = Ext.create('AreaSearchPanel',{
			'region':'north','parent':me
		});
		Ext.applyIf(me,{
			items:[me.areaSearchPanel,me.areaCenterPanel]
		});
		me.callParent(arguments);
	}
});

/**
 * 区县查询条件 panel
 */
Ext.define('AreaSearchPanel',{
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
	/*区县查询  事件*/
	,searchAreaEvent:function(){
		var me = this;
		if(DpUtil.isEmpty(Ext.getCmp('areaName').getValue())){
//			Ext.MessageBox.alert(i18n('i18n.util.msg.title')/*提示*/,i18n('i18n.area.msg.name_not_null')/*请填写区县名称*/);return;
			MessageUtil.showMessage(i18n('i18n.area.msg.name_not_null')/*请填写区县名称*/);return;
		}
		me.parent.areaCenterPanel.areaGridPanel.getStore().loadPage(1);
	}
	,getItems:function(){
		var me = this;
		return [
			{
				fieldLabel:i18n('i18n.area.name')/*区县名称*/
				,xtype:'textfield',name:'areaName',id:'areaName'
			}
			,{
				text:i18n('i18n.util.btn.search')/*查询*/,margin:'-5 0 0 5',width:60
				,xtype:'button',scope:me,handler:me.searchAreaEvent
			}
		];
	}
});


/**
 * 区县显示中间部分 panel
 */
Ext.define('AreaCenterPanel',{
	extend:'BasicPanel'
	,layout:'border'
	,areaOperatePanel:null  //区县增加、删除、修改 panel
	,areaGridPanel:null  //区县列表 panel
	,initComponent:function(){
		var me = this;
		me.areaOperatePanel = Ext.create('AreaOperatePanel',{
			'region':'north','parent':me
		});
		me.areaGridPanel = Ext.create('AreaGridPanel',{
			'region':'center'
		});
		Ext.applyIf(me,{
			items:[me.areaOperatePanel,me.areaGridPanel]
		});
		me.callParent(arguments);
	}
});

/**
 * 区县增加、删除、修改 panel
 */
Ext.define('AreaOperatePanel',{
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
						,xtype:'button',scope:me,handler:me.areaAdd_event
					}
					,{
						text:i18n('i18n.util.btn.update')/*修改*/
						,xtype:'button',scope:me,handler:me.areaUpdate_event
					}
					,{
						text:i18n('i18n.util.btn.delete')/*删除*/
						,scope:me,xtype:'button',handler:me.areaDelete_event
					}
				]
			},
			{xtype:'middlebuttonpanel'}
		];
	}
	//新增
	,areaAdd_event:function(){
		var me = this;
		areaWindow.setTitle(i18n('i18n.area.win_title.add')/*新增区县信息*/);
		areaWindow.show();
		areaWindow.load_data({'parent':me.parent,'areaModel':null});
	}
	//修改
	,areaUpdate_event:function(){
		var me = this;
		var selection = me.parent.areaGridPanel.getSelectionModel().getSelection();
		if(DpUtil.isEmpty(selection) || selection.length==0 || selection.length>1){
//			Ext.MessageBox.alert(i18n('i18n.util.msg.title')/*提示*/,i18n('i18n.util.msg.data_no_select_one'))/*请选择一条数据*/;return;
			MessageUtil.showMessage(i18n('i18n.util.msg.data_no_select_one'))/*请选择一条数据*/;return;
		}
		areaWindow.setTitle(i18n('i18n.area.win_title.update')/*修改区县信息*/);
		areaWindow.show();
		areaWindow.load_data({'parent':me.parent,'areaModel':selection[0]});
	}
	//删除
	,areaDelete_event:function(){
		var me = this;
		var selection = me.parent.areaGridPanel.getSelectionModel().getSelection();
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
			me.parent.areaGridPanel.getStore().loadPage(1);	
		}
		
		//执行失败
		var failFn = function(response){
//			processingMask.hide();Ext.MessageBox.alert(i18n('i18n.util.msg.title')/*提示*/,i18n('i18n.util.msg.delete_fail')/*删除失败*/);
			processingMask.hide();MessageUtil.showMessage(i18n('i18n.util.msg.delete_fail')/*删除失败*/);
		}
		DpUtil.requestJsonAjax('deleteAreaList.action',{'areaList':list},successFn,failFn);
	}
});

/**
 * 区县显示列表 gridpanel
 */
Ext.define('AreaGridPanel',{
	extend:'SearchGridPanel'
	,initComponent:function(){
		var me = this;
		Ext.applyIf(me,{
			'store':Ext.create('AreaStore',{
				listeners:{
					beforeload:function(store, operation, eOpts){
						var searchParams = {
							'searchCondition.name':Ext.getCmp('areaName').getValue()
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
			,{header:i18n('i18n.area.name')/*区县名称*/,width:70,dataIndex:'name'}
			,{header:i18n('i18n.area.number')/*区划代码*/,width:70,dataIndex:'number'}
			,{
				header:i18n('i18n.area.isCityLevel')/*县级市*/,width:100,dataIndex:'isCityLevel'
				,renderer:function(value) {
					return (value === '1')?i18n('i18n.util.yes')/*是*/:i18n('i18n.util.no')/*否*/;
				}
			}
			,{header:i18n('i18n.area.city')/*所属城市*/,width:100,dataIndex:'cityName'}
			,{header:i18n('i18n.area.lastModifyName')/*最后修改人*/,width:100,dataIndex:'lastModifyName'}
			,{
				header:i18n('i18n.area.modifyDate')/*最后修改时间*/,width:150,dataIndex:'modifyDate'
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
 * 区县保存或修改 弹出框 声明
 */
Ext.define('AreaWindow', {
    extend:'PopWindow'
	,width:460,height:180
    ,layout:'fit',modal:true
    ,areaFormPanel:null // 区县 form
    ,initComponent: function() {
        var me = this;
    	me.areaFormPanel = Ext.create('AreaFormPanel',{
    		'provListStore':provinceListStore
    		,'cityListStore':Ext.create('CityListStore',{autoLoad:false})
    	});
    	me.areaFormPanel.getForm().findField('provinceId').on('select',function(combo){
    		var provId = combo.getValue();
    		if(DpUtil.isEmpty(provId)){/*若为修改则添加主键*/
//				Ext.MessageBox.alert(i18n('i18n.util.msg.title')/*提示*/,i18n('i18n.area.msg.province_no_select')/*请选择所属省份*/);return;
    			MessageUtil.showMessage(i18n('i18n.area.msg.province_no_select')/*请选择所属省份*/);return;
			}
			me.areaFormPanel.getForm().findField('cityId').setValue('');
			load_cityList(provId,function(cityList){/* 加载城市集合  */
				me.areaFormPanel.cityListStore.loadData(cityList);
			});
    	});
    	if(!DpUtil.isEmpty(me.areaModel)){
    		me.areaFormPanel.getForm().loadRecord(me.areaModel);
    	}
    	Ext.applyIf(me,{
    		items:[me.areaFormPanel]
    		,buttons:me.getButtons()
    	});
        me.callParent(arguments);
    }
    ,load_data:function(opt){
    	var me = this;
    	Ext.apply(me,opt);
    	if(DpUtil.isEmpty(me.areaModel)){
    		me.areaFormPanel.getForm().loadRecord(new AreaModel());
    	}else{
    		me.areaFormPanel.getForm().loadRecord(me.areaModel);
    		load_cityList(me.areaModel.get('provinceId'),function(cityList){/* 初次加载城市集合  */
				me.areaFormPanel.cityListStore.loadData(cityList);
				me.areaFormPanel.getForm().findField('cityId').setValue(me.areaModel.get('cityId'));
			});
    	}
    }
    ,my_hide:function(flag){
    	var me = this;
    	me.hide();
    	if(flag && flag===true){
    		me.parent.areaGridPanel.getStore().loadPage(1);
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
		var searchForm = me.areaFormPanel.getForm();
		if(DpUtil.isEmpty(searchForm)){return;}
		var name = searchForm.findField('name').getValue();
		var number = searchForm.findField('number').getValue();
		var status = searchForm.findField('status').getValue();
		var isCityLevel = searchForm.findField('isCityLevel').getValue();
		var cityId = searchForm.findField('cityId').getValue();
    	if(DpUtil.isEmpty(name) || DpUtil.isEmpty(number) || DpUtil.isEmpty(status)
    	|| DpUtil.isEmpty(isCityLevel) || DpUtil.isEmpty(cityId)){
//			Ext.MessageBox.alert(i18n('i18n.util.msg.title')/*提示*/,i18n('i18n.area.msg.areaInfo_not_null')/*请填写区县信息*/);return;
    		MessageUtil.showMessage(i18n('i18n.area.msg.areaInfo_not_null')/*请填写区县信息*/);return;
		}
		processingMask.show();
		var params = {
			'area':{
				'name':name,'number':number,'status':status,'isCityLevel':isCityLevel,'cityId':cityId
			}
		}
		if(!DpUtil.isEmpty(me.areaModel)){/*若为修改则添加主键*/
			params.area['fid'] = me.areaModel.get('fid');
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
		DpUtil.requestJsonAjax('saveOrUpdateArea.action',params,successFn,failFn);
    }
});

/**
 * 区县 from
 */
Ext.define('AreaFormPanel', {
	extend: 'NoTitleFormPanel'
   	,layout: {columns:2,type: 'table'}
    ,defaultType:'textfield',defaults:{labelWidth:80,width:200}
    ,initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            items: [
                {fieldLabel:i18n('i18n.area.name')/*区县名称*/,name:'name'}
                ,{fieldLabel:i18n('i18n.area.number')/*区划代码*/,name:'number'}
                ,{
                	fieldLabel:i18n('i18n.area.status')/*状态*/,name:'status'
                	,xtype:'combobox',forceSelection:true
                	,store:Ext.create('Ext.data.Store', {
					    fields:['code', 'codeDesc'],
					    data:[
					    	{"code":"1", "codeDesc":i18n('i18n.util.valid')/*有效*/}
					    	,{"code":"0", "codeDesc":i18n('i18n.util.invalid')/*无效*/}
					    ]
					})
					,queryMode:'local',displayField:'codeDesc',valueField:'code'
                }
                ,{
                	fieldLabel:i18n('i18n.area.isCityLevel')/*县级市*/,name:'isCityLevel'
                	,xtype:'combobox',forceSelection:true
                	,store:Ext.create('Ext.data.Store', {
					    fields:['code', 'codeDesc'],
					    data:[
					        {"code":"1", "codeDesc":i18n('i18n.util.yes')/*是*/},
					        {"code":"0", "codeDesc":i18n('i18n.util.no')/*否*/}
					    ]
					})
					,queryMode:'local',displayField:'codeDesc',valueField:'code'
                }
                ,{
                	fieldLabel:i18n('i18n.area.province')/*所属省份*/,name:'provinceId',store:me.provListStore
                	,xtype:'combobox',queryMode:'local',displayField:'name',valueField:'fid'
                	,forceSelection:true
                }
                ,{
                	fieldLabel:i18n('i18n.area.city')/*所属城市*/,name:'cityId',store:me.cityListStore
                	,xtype:'combobox',queryMode:'local',displayField:'name',valueField:'fid'
                	,forceSelection:true
                }
            ]
        });
        me.callParent(arguments);
        
    }
});
/**
 * 加载城市集合
 * @param {number} provId 省份id
 * @param {function} callBack 回调函数
 */
function load_cityList(provId,callBack){
	processingMask.show();
	var params = {'parameterMap':{'provId':provId+''}};
	//执行成功
	var successFn = function(response){
		callBack(response.cityList);processingMask.hide();
	}
	//执行失败
	var failFn = function(response){
		processingMask.hide();
//		Ext.MessageBox.alert(i18n('i18n.util.msg.title')/*提示*/,response.message);
		MessageUtil.showMessage(response.message);
	}
	DpUtil.requestJsonAjax('searchCityListById.action',params,successFn,failFn);
}
var areaWindow = null;/*区县弹出框*/
/* 省份 集合 store 若不存在，则创建 */
if(provinceListStore==null || !provinceListStore){provinceListStore = Ext.create('ProvinceListStore',{autoLoad:true});}

Ext.onReady(function(){
	if(areaWindow==null || !areaWindow){areaWindow = Ext.create('AreaWindow');}
});