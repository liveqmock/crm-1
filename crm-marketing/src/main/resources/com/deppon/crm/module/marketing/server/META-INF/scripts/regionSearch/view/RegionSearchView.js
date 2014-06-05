/**
 * @description 区域查询界面
 * @author 盛诗庆
 * @date 2013-12-24
 * @revision 1.修订combo的change方法，解决输入内容不合法查询上一次输入bug
 */
//当前下来列表中选中部门ID
var deptId = null;
//部门的区域ID
var regionId = null;
//用户所属部门ID
var parentDeptId = null;
//用户所属部门区域ID
var parentRegionId = null;
//用户所查部门子部门区域显示颜色
var childRegionColor = '#373c64';
//用户查询区域颜色
var regionColor = '#EEB4B4';
//查询部门及其子部门区域id集合
var regionIdArray = new Array();
//地图加载30秒提示
var showMapTimeOut ;
Ext.onReady(function(){
	//初始化用户和部门信息
	initDeptAndUserInfo();
	//定义一个查询panel，包括一个下拉列表和一个查询按钮
	Ext.define('RegionSearchSearchPanel',{
		extend:'SearchFormPanel',
		items:null,
		border:0,
		layout : {
			type : 'table',
			columns : 3
		},
		defaults:{
			labelWidth:60,
			labelAlign:'right',
			width:200,
			margin:'0 20 20 0'
		},
		defaultType:'textfield',
		initComponent:function(){			
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return[{
                fieldLabel: '部门',
                labelWidth:40,
		    	width:300,
                xtype:'combo',
                name:'deptInfo',
                store:Ext.create('DeptStore',{
                    listeners:{
                        beforeload:function(store, operation, eOpts){
                            Ext.apply(operation,{
                                params : {
                                	'deptName':Ext.getCmp("regionSearchSearchPanelId").getForm().findField("deptInfo").getRawValue()
                                    }
                                }
                            );  
                        }
                    }
                }),
                displayField:'deptName',
                valueField:'id',
                forceSelection :true,
                emptyText:'请输入部门关键字',
                pageSize: 10,
                minChars:2,
                queryDelay:800,
                listConfig: {
                    minWidth :300,
                    getInnerTpl: function() {
                         return '{deptName}';
                    }
                },
                listeners:{
                    change:function(field,newValue){
                    	field.lastSelection = '';	//将最后选择内容清空
                    	if(Ext.isEmpty(newValue)){
                    		field.setValue(null);
                    	}
                    }
                }
            },{
				//查询按钮
				text:i18n('i18n.regionSearch.searchPanel.searchRegionButton'),
				id:'searchRegion',
			    xtype:'button',
			    width:100,
			    minChars:2,
			    handler:function(btn){
			    	//点击该按钮后禁用该按钮,避免查询结果没有出来就提交第二次
			    	btn.disable();
			    	//获得部门列表
			    	var deptInfo = Ext.getCmp("regionSearchSearchPanelId").getForm().findField("deptInfo");
			    	//获取部门id
			    	deptId = deptInfo.getValue();
			    	//如果id为空，报部门编码为空
			    	if(deptId === null && deptId !== ''){
			    		MessageUtil.showErrorMes(i18n('i18n.regionSearch.searchPanel.deptIdIsNotExist'));
			    		btn.enable();
			    	}else{
		    			var deptInfoStore = Ext.create('DeptInfoStore');
		    			var param = {'deptId' : deptId};
		    			RegionSearchStore.prototype.searchRegionsByDeptId(param,retSuccessFn,retFailFn);
		    			btn.enable();
			    	}
			    }
			}];
		}
	});

	Ext.define('RegionSearchMap',{
		extend : 'Ext.panel.Panel',
		border : false,
		html : '<div id="crmMapId" style="width:100%;height:100%"></div>',
		initComponent:function(){
			this.callParent();
		}	
	});
	var viewport = null;
	viewport = Ext.create('Ext.Viewport',{
		layout : 'border',
		items : [{
			xtype:'container',
			region:'north',
			layout:'fit',
			items:[Ext.create('RegionSearchSearchPanel',{id:'regionSearchSearchPanelId'})]
		},{
			xtype:'container',
			region:'center',
			layout:'border',
			items:[
				{//区域查询地图
					xtype:'container',
					region:'center',
		    	    layout:'fit',
		    	    items:[ Ext.create('RegionSearchMap',{id:'RegionSearchMapId'})]
		        }	
		   ]
		}]
	});
	//初始化地图
	var gmap = new CRM_GMap( 'crmMapId',
		{
			center : "上海市", 
			zoom : 12, 
			mapType:"DPMAP_NORMAL_MAP"
		},
		function(map) {
			clearTimeout(showMapTimeOut);
			initRegionSearch();
			 var fun =function(data){
				 if(data.type == 'ADD'||data.type=='UPD'){
					 saveRegionPartition(deptId,data.id);
				 }else if(data.type == 'DEL'){
					 deleteRegionPartition(deptId);
				 }else if(data.type == 'CEL'){
					//获得区域划分的panel
					var regionPanel = Ext.getCmp("regionSearchSearchPanelId").getForm();
					//获得部门列表的combobox
					var deptInfo = regionPanel.findField("deptInfo");
					// 如果部门ID不为空,且区域ID不为空则启用部门列表,启用删除按钮,禁用划分按钮
					 if( deptId!=null && regionId != null ){
						 deptInfo.enable();
					// 如果部门ID不为空,且区域ID为空则启用部门列表,禁用删除按钮,启用划分按钮
					 }else if(deptId !=null && regionId == null ){
						 deptInfo.enable();
					 }
				 }
			 }
			 window.poly =  gmap.PolygonFeature(map,{callBackFun:fun});
		}
	);
	showMapTimeOut = setTimeout(function(){
		MessageUtil.showErrorMes(i18n('i18n.regionPartition.showErrorMessage.mapOnloadIsTimeOut'));
	},30000);
});	
function initRegionSearch(){	
	//获得区域划分的panel
	var regionPanel = Ext.getCmp("regionSearchSearchPanelId").getForm();
	//获得部门列表的combobox
	var deptInfo = regionPanel.findField("deptInfo");
	var store = deptInfo.store;
	store.removeAll();
	var model = new DeptModel();
	model.set('id',User.deptId);
	model.set('deptName',User.deptName);
	store.add(model);
	var deptId = new String(User.deptId);
	deptInfo.setValue(deptId.toString());
};
/**
 * @description 根据区域id在地图中显示该区域
 * @param regionId 区域id
 * @param color 区域要显示的颜色
 * @param isLocate 是否定位到该区域
 */
function showRegionById(regionId,color,isLocate){
	poly.showUnModifyPolygonById({
		'id' : regionId,
		'color' : color,
		'isLocate' :isLocate
	});
};
var retSuccessFn =  function retSuccessFn(result){
	//清除当前地图中所有区域
	Ext.each(regionIdArray,function(item){
		poly.clearMapPolygonById(item);
	})
	
	//循环显示
	Ext.each(result.deptList,function( item ){
		//获得区域ID
		var regionId = item.regionId;
		//如果区域ID不为空
		if( regionId !== null && regionId !==''){
			//将区域id加入集合
			regionIdArray.push(regionId);
			//在地图中显示z子部门区域
			if(item.color === '1')
			{
				showRegionById(regionId,childRegionColor,false);
			}
			else
			{
				showRegionById(regionId,regionColor,true);
			}
		}
		
	});
	
};
var retFailFn = function retFailFn(){
	MessageUtil.showErrorMes(i18n('i18n.regionSearch.searchPanel.regionIdIsNotExist'));
};