
/**
 * 城市容器 panel
 */
Ext.define('CityContainerPanel',{
	extend:'TabContentPanel'
	,layout:'border'
	,padding:'10'
	,title:i18n('i18n.city.title.tab')/*城市信息维护*/
	,citySearchPanel:null  //城市查询条件panel
	,cityCenterPanel:null  //城市中间布局快
	,initComponent:function(){
		var me = this;
		me.cityCenterPanel = Ext.create('CityCenterPanel',{
			'region':'center'
		});
		me.citySearchPanel = Ext.create('CitySearchPanel',{
			'region':'north','parent':me
		});
		Ext.applyIf(me,{
			items:[me.citySearchPanel,me.cityCenterPanel]
		});
		me.callParent(arguments);
	}
});

/**
 * 城市查询条件 panel
 */
Ext.define('CitySearchPanel',{
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
	/*城市查询  事件*/
	,searchCityEvent:function(){
		var me = this;
		if(DpUtil.isEmpty(Ext.getCmp('cityName').getValue())){
//			Ext.MessageBox.alert(i18n('i18n.util.msg.title')/*提示*/,i18n('i18n.city.msg.name_not_null')/*请填写城市名称*/);return;
			MessageUtil.showMessage(i18n('i18n.city.msg.name_not_null')/*请填写城市名称*/);return;
		}
		me.parent.cityCenterPanel.cityGridPanel.getStore().loadPage(1);
	}
	,getItems:function(){
		var me = this;
		return [
			{
				fieldLabel:i18n('i18n.city.name')/*城市名称*/
				,xtype:'textfield',name:'cityName',id:'cityName'
			}
			,{
				text:i18n('i18n.util.btn.search')/*查询*/
				,xtype:'button',margin:'-5 0 0 5',scope:me,width:60,handler:me.searchCityEvent
			}
		];
	}
});


/**
 * 城市显示中间部分 panel
 */
Ext.define('CityCenterPanel',{
	extend:'BasicPanel'
	,layout:'border'
	,cityOperatePanel:null  //城市增加、删除、修改 panel
	,cityGridPanel:null  //城市列表 panel
	,initComponent:function(){
		var me = this;
		me.cityOperatePanel = Ext.create('CityOperatePanel',{
			'region':'north','parent':me
		});
		me.cityGridPanel = Ext.create('CityGridPanel',{
			'region':'center'
		});
		Ext.applyIf(me,{
			items:[me.cityOperatePanel,me.cityGridPanel]
		});
		me.callParent(arguments);
	}
});

/**
 * 城市增加、删除、修改 panel
 */
Ext.define('CityOperatePanel',{
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
						,xtype:'button',scope:me,handler:me.cityAdd_event
					}
					,{
						text:i18n('i18n.util.btn.update')/*修改*/
						,xtype:'button',scope:me,handler:me.cityUpdate_event
					}
					,{
						text:i18n('i18n.util.btn.delete')/*删除*/
						,scope:me,xtype:'button',handler:me.cityDelete_event
					}
				]
			},
			{xtype:'middlebuttonpanel'}
		];
	}
	//新增
	,cityAdd_event:function(){
		var me = this;
		cityWindow.setTitle(i18n('i18n.city.win_title.add')/*新增城市信息*/);
		cityWindow.show();
		cityWindow.load_data({parent:me.parent,'cityModel':null});
	}
	//修改
	,cityUpdate_event:function(){
		var me = this;
		var selection = me.parent.cityGridPanel.getSelectionModel().getSelection();
		if(DpUtil.isEmpty(selection) || selection.length==0 || selection.length>1){
//			Ext.MessageBox.alert(i18n('i18n.util.msg.title')/*提示*/,i18n('i18n.util.msg.data_no_select_one'))/*请选择一条数据*/;return;
			MessageUtil.showMessage(i18n('i18n.util.msg.data_no_select_one'))/*请选择一条数据*/;return;
		}
		cityWindow.setTitle(i18n('i18n.city.win_title.update')/*修改城市信息*/);
		cityWindow.show();
		cityWindow.load_data({'parent':me.parent,'cityModel':selection[0]});
	}
	//删除
	,cityDelete_event:function(){
		var me = this;
		var selection = me.parent.cityGridPanel.getSelectionModel().getSelection();
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
			me.parent.cityGridPanel.getStore().loadPage(1);	
		}
		
		//执行失败
		var failFn = function(response){
//			processingMask.hide();Ext.MessageBox.alert(i18n('i18n.util.msg.title')/*提示*/,i18n('i18n.util.msg.delete_fail')/*删除失败*/);
			processingMask.hide();MessageUtil.showMessage(i18n('i18n.util.msg.delete_fail')/*删除失败*/);
		}
		DpUtil.requestJsonAjax('deleteCityList.action',{'cityList':list},successFn,failFn);
	}
});

/**
 * 城市显示列表 gridpanel
 */
Ext.define('CityGridPanel',{
	extend:'SearchGridPanel'
	,initComponent:function(){
		var me = this;
		Ext.applyIf(me,{
			'store':Ext.create('CityStore',{
				listeners:{
					beforeload:function(store, operation, eOpts){
						var searchParams = {
							'searchCondition.name':Ext.getCmp('cityName').getValue()
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
			,{header:i18n('i18n.city.name')/*省份名称*/,width:70,dataIndex:'name'}
			,{header:i18n('i18n.city.number')/*区划代码*/,width:170,dataIndex:'number'}
			,{header:i18n('i18n.city.pinyin')/*拼音*/,width:70,dataIndex:'pinyin'}
			,{header:i18n('i18n.city.pyjm')/*拼音简称*/,width:70,dataIndex:'pyjm'}
			
			,{header:i18n('i18n.city.cityNumber')/*区号*/,width:70,dataIndex:'cityNumber'}
			,{
				header:i18n('i18n.city.isDirCity')/*是否直辖市*/,width:70,dataIndex:'isDirCity'
				,renderer:function(value) {
					return (value === 1)?i18n('i18n.util.yes')/*是*/:i18n('i18n.util.no')/*否*/;
				}
			}
			,{header:i18n('i18n.city.province')/*所属省份*/,width:100,dataIndex:'provinceName'}
			,{header:i18n('i18n.city.lastModifyName')/*最后修改人*/,width:100,dataIndex:'lastModifyName'}
			,{
				header:i18n('i18n.city.modifyDate')/*最后修改时间*/,width:150,dataIndex:'modifyDate'
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
 * 城市保存或修改 弹出框 声明
 */
Ext.define('CityWindow', {
    extend:'PopWindow'
	,width:460,height:200
    ,layout:'fit',modal:true
    ,cityFormPanel:null // 城市 form
    ,initComponent: function() {
        var me = this;
    	me.cityFormPanel = Ext.create('CityFormPanel',{'provListStore':provinceListStore});
    	
    	Ext.applyIf(me,{
    		items:[me.cityFormPanel]
    		,buttons:me.getButtons()
    	});
        me.callParent(arguments);
    }
    ,load_data:function(opt){
    	var me = this;
    	Ext.apply(me,opt);
    	if(DpUtil.isEmpty(me.cityModel)){
    		me.cityFormPanel.getForm().loadRecord(new CityModel());
    	}else{me.cityFormPanel.getForm().loadRecord(me.cityModel);}
    }
    ,my_hide:function(flag){
    	var me = this;
    	me.hide();
    	if(flag && flag===true){
    		me.parent.cityGridPanel.getStore().loadPage(1);
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
		var searchForm = me.cityFormPanel.getForm();
		if(DpUtil.isEmpty(searchForm)){return;}
		var name = searchForm.findField('name').getValue();
		var number = searchForm.findField('number').getValue();
		var pinyin = searchForm.findField('pinyin').getValue();
		var pyjm = searchForm.findField('pyjm').getValue();
		var isDirCity = searchForm.findField('isDirCity').getValue();
		var cityNumber = searchForm.findField('cityNumber').getValue();
		var provinceId = searchForm.findField('provinceId').getValue();
    	if(
    		DpUtil.isEmpty(name) || DpUtil.isEmpty(number) || DpUtil.isEmpty(pinyin) || DpUtil.isEmpty(pyjm)
    		|| DpUtil.isEmpty(isDirCity) || DpUtil.isEmpty(cityNumber) || DpUtil.isEmpty(provinceId)
    	){
//			Ext.MessageBox.alert(i18n('i18n.util.msg.title')/*提示*/,i18n('i18n.city.msg.cityInfo_not_null')/*请填写城市信息*/);return;
    		MessageUtil.showMessage(i18n('i18n.city.msg.cityInfo_not_null')/*请填写城市信息*/);return;
		}
		processingMask.show();
		var params = {
			'city':{
				'name':name,'number':number,'pinyin':pinyin,'pyjm':pyjm
				,'isDirCity':isDirCity,'cityNumber':cityNumber,'provinceId':provinceId
			}
		}
		if(!DpUtil.isEmpty(me.cityModel)){/*若为修改则添加主键*/
			params.city['fid'] = me.cityModel.get('fid');
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
		DpUtil.requestJsonAjax('saveOrUpdateCity.action',params,successFn,failFn);
    }
});

/**
 * 城市 from
 */
Ext.define('CityFormPanel', {
	extend: 'NoTitleFormPanel'
   	,layout: {columns:2,type: 'table'}
    ,defaultType:'textfield',defaults:{labelWidth:80,width:200}
    ,provListStore:null
    ,initComponent: function() {
        var me = this;
         
        Ext.applyIf(me, {
            items: [
                {fieldLabel:i18n('i18n.city.name')/*城市名称*/,name:'name'}
                ,{fieldLabel:i18n('i18n.city.number')/*区划代码*/,name:'number'}
                ,{fieldLabel:i18n('i18n.city.pinyin')/*拼音*/,name:'pinyin'}
                ,{fieldLabel:i18n('i18n.city.pyjm')/*拼音简码*/,name:'pyjm'}
                ,{
                	fieldLabel:i18n('i18n.city.isDirCity')/*是否直辖市*/,name:'isDirCity'
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
                ,{fieldLabel:i18n('i18n.city.cityNumber')/*区号*/,name:'cityNumber'}
                ,{
                	fieldLabel:i18n('i18n.city.province')/*所属省份*/,name:'provinceId',store:me.provListStore
                	,xtype:'combobox',queryMode:'local',displayField:'name',valueField:'fid'
                	,forceSelection:true
                }
            ]
        });
        me.callParent(arguments);
    }
});

var cityWindow = null;/*城市弹出框*/
/* 省份 集合 store 若不存在，则创建 */
if(provinceListStore==null || !provinceListStore){provinceListStore = Ext.create('ProvinceListStore',{autoLoad:true});}

Ext.onReady(function(){
	if(cityWindow==null || !cityWindow){cityWindow = Ext.create('CityWindow');}
});