//用户的权限部门
var authCharacter = null;
//选中部门ID
var deptId = null;
//选中部门的区域ID
var regionId = null;
//var recordId = null;
//用户所属部门ID
var parentDeptId = null;
//用户所属部门区域ID
var parentRegionId = null;
//用户所属部门区域显示颜色
// 	#BFEFFF#CD1076
var parentColor = '#EEB4B4';
//用户所划区域颜色
//#4169E1#A6A6A6
var deptColor = '#A6A6A6';
//选中区域的颜色
var selectColor = '#373c64';
//权限部门经营本部
var HEADQUARTERS = 'HEADQUARTERS';
//事业部
var DIVISION = 'DIVISION';
//大区
var BIG_REGION = 'BIG_REGION';
//小区
var SMALL_REGION = 'SMALL_REGION';
//营业部
var SALES_DEPARTMENT = 'SALES_DEPARTMENT';
//地图加载30秒提示
var showMapTimeOut ;
Ext.onReady(function(){
	Ext.define('RegionPartitionSearchPanel',{
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
				xtype:'combobox',
		    	labelSeparator:':',
		    	//部门
		    	fieldLabel:i18n('i18n.regionPartition.searchPanel.deptInfo'),
		    	labelWidth:40,
		    	width:300,
		    	queryMode: 'local',
		    	store:Ext.create('DeptInfoStore'),
		    	displayField:'deptName',
		    	valueField:'deptId',
		    	disabled : true,
		    	forceSelection:true,
		    	//剔出朦层
    			listConfig: {
      		        loadMask:false
    			},
				name:'deptInfo',
				listeners:{
					select:function(me,records){
						//列表切换前的部门ID
						var beforeRegionId = regionId;
						//选中部门ID
						deptId = records[0].get("deptId");
						//选中区域ID
						regionId = records[0].get("regionId");
						//删除区域按钮
						var resetRegion = Ext.getCmp("resetRegion");
						//划范围按钮
						var regionPartition = Ext.getCmp("regionPartition");
						//设置地图划范围状态为空闲
						poly.setPolygonStatus('FREE');
						//禁用1秒种
						me.disable();
						resetRegion.disable();
						regionPartition.disable();
						setTimeout(function(){
							me.enable();							
							//如果部门ID不为空且区域ID为空,则该部门没有划分过区域
							if( deptId != null && regionId == null ){
								//删除区域按钮不可用
								resetRegion.disable();
								//划范围按钮可用
								regionPartition.enable();
							//如果部门ID不为空且区域ID也不为空,则该部门已经划分过区域
							}else if( deptId != null && regionId != null ){
								//删除区域按钮可用
								resetRegion.enable();
								//划范围按钮不可用
								regionPartition.disable();
							//如果部门ID为空且区域ID为空则该部门信息有误
							}else if( deptId == null && regionId == null ){
								//删除区域按钮不可用
								resetRegion.disable();
								//划范围按钮不可用
								regionPartition.disable();
								//提示错误信息:部门信息异常
								MessageUtil.showErrorMes(i18n('i18n.regionPartition.showErrorMessage.deptIsNull'));
							}
						},1000);
						
						//如果前部门区域ID不为空,则将地图上的部门区域切换为不可编辑和不选中模式
						if( beforeRegionId != null ){
							//清楚该区域在地图中的显示
							poly.clearMapPolygonById(beforeRegionId);
							//重新加载该部门区域为不选中模式
							showUnModifyDeptRegionById(beforeRegionId);
						}
						//如果现区域ID不为空则重新加载该区域为选中可编辑模式
						if( regionId != null ){
							//清除该区域在地图中的显示
							poly.clearMapPolygonById(regionId);
							//重新加载该部门区域为选中可编辑模式
							showModifyDeptRegionById(regionId);
						}
					}
				}
			},{
				//删除
				text:i18n('i18n.regionPartition.searchPanel.deleteRegionButton'),
				id:'resetRegion',
			    xtype:'button',
			    disabled : true,
			    width:100,
			    handler:function(btn){
			    	//点击该按钮后禁用该按钮
			    	btn.disable();
			    	//获得部门列表
			    	var deptInfo = Ext.getCmp("regionPartitionSearchPanelId").getForm().findField("deptInfo");
			    	//禁用部门列表
			    	deptInfo.disable();
			    	//如果部门ID和区域ID不为空
			    	if(deptId != null && regionId != null ){
			    		MessageUtil.showQuestionMes(i18n('i18n.regionPartition.showMessage.chooseDeleteRegion'),function(e){
			    			if(e=='yes'){
			    				//在地图中删除该区域
					    		poly.deletePolygonById(regionId);
			    			}else{
			    				//设置部门列表可用
			    				deptInfo.enable();
			    				//设置删除按钮可用
			    				btn.enable();
			    			}
			    		},function(){
			    			//设置部门列表可用
		    				deptInfo.enable();
		    				//设置删除按钮可用
		    				btn.enable();
			    		});
			    	
			    	//如果部门ID不为空,区域ID为空,则该区域还未划分
			    	}else if(deptId != null && regionId == null){
			    		deptInfo.enable();
			    		//提示错误信息:区域还未划分
			    		MessageUtil.showErrorMes(i18n('i18n.regionPartition.showErrorMessage.regionNotPartition'));
			    	//如果部门ID为空,则还未选中部门
			    	}else if( deptId == null ){
			    		deptInfo.enable();
			    		//提示错误信息:还未选择区域
			    		MessageUtil.showErrorMes(i18n('i18n.regionPartition.showErrorMessage.regionNotSelected'));
			    	}
				}
			},{
				//划分区域
				text:i18n('i18n.regionPartition.searchPanel.regionPartitionButton'),
				id:'regionPartition',
			    xtype:'button',
			    disabled : true,
			    width:100,
			    handler:function(btn){
			    	//禁用划范围按钮
			    	btn.disable();
			    	//获得部门列表
			    	var deptInfo = Ext.getCmp("regionPartitionSearchPanelId").getForm().findField("deptInfo");
			    	//禁用部门列表
			    	deptInfo.disable();
			    	//如果部门ID和区域ID为空
			    	if( deptId != null && regionId == null ){
			    		//启用划范围
			    		poly.startDrawPolygon();
			    	//如果部门ID为空区域ID不为空
			    	}else if( deptId != null && regionId != null ){
			    		deptInfo.enable();
			    		//提示错误信息:该区域已划分
			    		MessageUtil.showErrorMes(i18n('i18n.regionPartition.showErrorMessage.regionPartitioned'));
			    	//如果部门ID为空
			    	}else if( deptId == null ){
			    		deptInfo.enable();
			    		//提示错误信息:请先选择部门
			    		MessageUtil.showErrorMes(i18n('i18n.regionPartition.showErrorMessage.deptIsNull'));
			    	}
				}
			}];
		}
	});

	Ext.define('RegionPartitionMap',{
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
			items:[Ext.create('RegionPartitionSearchPanel',{id:'regionPartitionSearchPanelId'})]
		},{
			xtype:'container',
			region:'center',
			layout:'border',
			items:[
				{//区域划分地图
					xtype:'container',
					region:'center',
		    	    layout:'fit',
		    	    items:[ Ext.create('RegionPartitionMap',{id:'RegionPartitionMapId'})]
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
			initRegionPartition();
			 var fun =function(data){
				 if(data.type == 'ADD'||data.type=='UPD'){
					 saveRegionPartition(deptId,data.id);
				 }else if(data.type == 'DEL'){
					 deleteRegionPartition(deptId);
				 }else if(data.type == 'CEL'){
					//获得区域划分的panel
					var regionPanel = Ext.getCmp("regionPartitionSearchPanelId").getForm();
					//获得部门列表的combobox
					var deptInfo = regionPanel.findField("deptInfo");
					//获得删除区域按钮 
					var resetRegion = Ext.getCmp("resetRegion");
					//获得区域划分按钮 
					var regionPartition = Ext.getCmp("regionPartition");
					// 如果部门ID不为空,且区域ID不为空则启用部门列表,启用删除按钮,禁用划分按钮
					 if( deptId!=null && regionId != null ){
						 deptInfo.enable();
						 resetRegion.enable();
						 regionPartition.disable();
					// 如果部门ID不为空,且区域ID为空则启用部门列表,禁用删除按钮,启用划分按钮
					 }else if(deptId !=null && regionId == null ){
						 deptInfo.enable();
						 resetRegion.disable();
						 regionPartition.enable();
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
function initRegionPartition(){	
	//获得区域划分的panel
	var regionPanel = Ext.getCmp("regionPartitionSearchPanelId").getForm();
	//获得部门列表的combobox
	var deptInfo = regionPanel.findField("deptInfo");
	//获得删除区域按钮 
	var resetRegion = Ext.getCmp("resetRegion");
	//获得区域划分按钮 
	var regionPartition = Ext.getCmp("regionPartition");
	/**
	 * ajax请求成功函数
	 */
	var retSuccessFn =  function retSuccessFn(result){
//		//解析返回对象
//		var result = Ext.decode(result.responseText);
		//获得部门列表的store
		var store = deptInfo.store;
		//获得权限部门标识
		authCharacter = result.authCharacter;
		//获得本部门ID
		parentDeptId = result.deptId;
		//获得本部门区域ID
		parentRegionId = result.regionId;
//		//禁用删除区域按钮
//		resetRegion.disable();
//		//禁用划范围按钮
//		regionPartition.disable();
		//清空部门列表
		store.removeAll();
		//循环查询到的部门信息
		Ext.each(result.deptList,function(item){
			//创建部门MODEL
			var model = new DeptInfoModel();
			//设置部门ID
			model.set('deptId',item.deptId);
			//设置部门名称
			model.set('deptName',item.deptName);
			//设置区域ID
			model.set('regionId',item.regionId);
			//将部门添加到部门combox的store
			store.add(model);
		});
		//如果登陆用户的权限部门标示不是经营本部,事业部,大区,小区或者营业部
		if( authCharacter != HEADQUARTERS && authCharacter != DIVISION && authCharacter != BIG_REGION
				&& authCharacter != SMALL_REGION && authCharacter != SALES_DEPARTMENT){
			//禁用部门列表
			deptInfo.disable();
			//禁用删除区域按钮
			resetRegion.disable();
			//禁用划范围按钮
			regionPartition.disable();
			//提示错误信息:没有相应权限
			MessageUtil.showErrorMes(i18n('i18n.regionPartition.showErrorMessage.noAuthToRegionPartition'));
			return;
		//如果用户的权限部门标识为事业部,大区,小区,营业部,且本部门区域ID为空
		}else if( (authCharacter == DIVISION || authCharacter == BIG_REGION || 
				authCharacter == SMALL_REGION || authCharacter == SALES_DEPARTMENT)
				&& parentRegionId == null ){
			//禁用部门列表
			deptInfo.disable();
			//禁用删除区域按钮
			resetRegion.disable();
			//禁用划范围按钮
			regionPartition.disable();
			//提示错误信息:上级区域没有划分
			MessageUtil.showErrorMes(i18n('i18n.regionPartition.showErrorMessage.parentRegionIsNull'));
			return;
		}
		
		//如果部门权限不为经营本部且不为空
		if( authCharacter != 'HEADQUARTERS' && authCharacter != ''){
			//显示本部门划分的区域
			showParentRegionById(parentRegionId);			
		}
		//循环部门列表
		Ext.each(result.deptList,function( item ){
			//获得区域ID
			var regionId = item.regionId;
			//如果区域ID不为空
			if( regionId != null && regionId !=''){
				//在地图中显示不可编辑的区域
				showUnModifyDeptRegionById(regionId);
			}
		});
		//延迟两秒启用部门列表
		setTimeout(function(){
			//启用部门列表
			deptInfo.enable();
		},2000);
	};
	var retFailFn = function retFailFn(){
		resetRegion.disable();
		regionPartition.disable();
		deptInfo.disable();
	};
	RegionPartitionStore.prototype.initRegionPartition(null,retSuccessFn,retFailFn);
};
function deleteRegionPartition(deptId){
	var reSuccessFn = function(){		
		var resetRegion = Ext.getCmp("resetRegion");
		var regionPartition = Ext.getCmp("regionPartition");
		resetRegion.disable();
		regionPartition.enable();
		var deptInfo = Ext.getCmp("regionPartitionSearchPanelId").getForm().findField("deptInfo");
    	deptInfo.enable();
		updateRegionInfoToDeptInfo(deptId,null);
		//设置地图划范围状态为空闲
		poly.setPolygonStatus('FREE');
		//删除成功
		MessageUtil.showInfoMes(i18n('i18n.regionPartition.showErrorMessage.deleteSuccess'));
		
	};
	var reFailFn = function(){		
		var resetRegion = Ext.getCmp("resetRegion");
		var regionPartition = Ext.getCmp("regionPartition");
		resetRegion.enable();
		regionPartition.disable();
		var deptInfo = Ext.getCmp("regionPartitionSearchPanelId").getForm().findField("deptInfo");
    	deptInfo.enable();
    	//设置地图划范围状态为空闲
		poly.setPolygonStatus('FREE');
    	//删除失败
		MessageUtil.showErrorMes(i18n('i18n.regionPartition.showErrorMessage.deleteFail'));		
	};
	var param = { 'deptId' : deptId };
	RegionPartitionStore.prototype.deleteRegionPartition(param,reSuccessFn,reFailFn);
};
function saveRegionPartition(deptId,regionId){
	var reSuccessFn = function(){
		var resetRegion = Ext.getCmp("resetRegion");
		var regionPartition = Ext.getCmp("regionPartition");
		resetRegion.enable();
		regionPartition.disable();
		var deptInfo = Ext.getCmp("regionPartitionSearchPanelId").getForm().findField("deptInfo");
    	deptInfo.enable();
		updateRegionInfoToDeptInfo(deptId,regionId);
		//保存成功
		MessageUtil.showInfoMes(i18n('i18n.regionPartition.showErrorMessage.saveSuccess'));
	};
	var reFailFn = function(){
		var resetRegion = Ext.getCmp("resetRegion");
		var regionPartition = Ext.getCmp("regionPartition");
		resetRegion.disable();
		regionPartition.enable();
		var deptInfo = Ext.getCmp("regionPartitionSearchPanelId").getForm().findField("deptInfo");
    	deptInfo.enable();
    	//保存失败
		MessageUtil.showErrorMes(i18n('i18n.regionPartition.showErrorMessage.saveFail'));		
	};
	var param = { 'deptId' : deptId , 'regionId' : regionId };
	RegionPartitionStore.prototype.saveRegionPartition(param,reSuccessFn,reFailFn);
};
function showParentRegionById(regId){
	poly.showUnModifyPolygonById({
		'id' : regId,
		'color' : parentColor,
		'isLocate' :true
	});
};
function showUnModifyDeptRegionById(regId){
	poly.showUnModifyPolygonById({
		'id' : regId,
		'color' : deptColor,
		'isLocate' :false
	});
};
function showModifyDeptRegionById(regId){
	poly.showModifyPolygonById({
		'id' : regId,
		'color' : selectColor,
		'isLocate' :true
	});
};
function cancelSaveRegion(){
	
}
//更新部门列表中的区域ID 
function updateRegionInfoToDeptInfo(inDeptId,inRegionId){	
	var store = Ext.getCmp("regionPartitionSearchPanelId").getForm().findField("deptInfo").store;
	store.each(function( item ){
		if(item.get('deptId')==inDeptId){
			item.set('regionId',inRegionId);
			regionId = inRegionId;
		}
	},this);
};