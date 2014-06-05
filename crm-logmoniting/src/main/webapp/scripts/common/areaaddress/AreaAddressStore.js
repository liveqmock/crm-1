Ext.define('cityModel',{
	extend:'Ext.data.Model',
	fields:[{
			name:'id',
			type:'string'
		},{
			name:'name',
			type:'string'
		},{
			name:'provinceName',
			type:'string'
		}]
});

Ext.define('areaModel',{
	extend:'Ext.data.Model',
	fields:[{
			name:'id',
			type:'string'
		},{
			name:'name',
			type:'string'
		},{
			name:'cityName',
			type:'string'
		}]
});

Ext.define('provinceModel',{
	extend:'Ext.data.Model',
	fields:[{
			name:'id',
			type:'string'
		},{
			name:'name',
			type:'string'
		}]
});
//下拉框的store,因为下拉框是根据名称去查询具体城市的
Ext.define('searchCityByNameStore', {
		extend:'Ext.data.Store',
		model : 'cityModel',
		autoLoad : false,
		proxy : {
			type : 'ajax',
			url : '../common/searchCitiesByName.action',
			actionMethods : 'post',// 否则可能会出现中文乱码
			reader : {
				type : 'json',
				root : 'cityList'
			}
		},
		listeners:{
			load:function(store,record){
				Ext.getCmp(id+'AreaTabWindow').close();
			}
		}
	});
//城市面板store,  城市面板是根据所选省份带出来的	
Ext.define('searchCityByProvince', {
	extend:'Ext.data.Store',
	model : 'cityModel',
	tabPanel : null,
	autoLoad : false,
	proxy : {
		type : 'ajax',
		url : '../common/searchCityByProvince.action',
		actionMethods : 'post',// 否则可能会出现中文乱码
		reader : {
			type : 'json',
			root : 'cityList'
		}
	},
	listeners:{
		load:function(store,record){
			var me = this;
			//动态将后台查询的数据，绘制到面板上
			for(var i=0;i<record.length;i++){
				Ext.getCmp(id+"CityPanel").add({
					//将城市id赋值给stateId
					stateId:record[i].data.id,
	            	xtype:'button',
	            	width:70,
	            	text:getButtonName(record[i].data.name),
	            	handler : function(button) {
	            		//设置自动切换页签
	            		Ext.getCmp(id+'AreaTabPanel').setActiveTab(id+'AreaPanel');
	            		//每次都需要将上次界面清空
	            		if(Ext.isEmpty(Ext.data.StoreManager.lookup(id+'AreaStore'))){
	            			new areaStore({'storeId':id+'AreaStore','tabPanel':me.tabPanel});
	            		}
	            		if(Ext.isEmpty(Ext.getCmp(id+'AreaPanel'))){
	            			Ext.data.StoreManager.lookup(id+'SearchCityByNameStore').loadRecords(Ext.data.StoreManager.lookup(id+'SearchCityByProvince').data.items);
		            		Ext.getCmp(id).setValue(button.getStateId());
		            		if('cityPanel' == me.tabPanel[me.tabPanel.length-1]){
		            			Ext.getCmp(id+'AreaTabWindow').close();
		            		}
	            			return;
	    				}
	    				//点击具体城市需要自动切换到其对应的区域上，需要将区域面板清空，重新绘制具体区域
	            		Ext.getCmp(id+'AreaPanel').removeAll();
	            		//根据所点击的城市id，查询具体区域，在查询时，会在区域的load事件中绘制区域面板
	            		Ext.data.StoreManager.lookup(id+'AreaStore').load({params:{'param':button.getStateId()}});
	            		//同时需要将下拉框的值清空，将城市面板所有数据给下拉框store，同时让下拉框显示当前所选城市的名称
	            		Ext.data.StoreManager.lookup(id+'SearchCityByNameStore').removeAll();
	            		Ext.data.StoreManager.lookup(id+'SearchCityByNameStore').loadRecords(Ext.data.StoreManager.lookup(id+'SearchCityByProvince').data.items);
	            		Ext.getCmp(id).setValue(button.getStateId());
	            		if('cityPanel' == me.tabPanel[me.tabPanel.length-1]){
	            			Ext.getCmp(id+'AreaTabWindow').close();
	            		}
	            	}
	            });
			}
		}
	}
});
//常用城市面板的store
Ext.define('commonStore', {
	extend:'Ext.data.Store',
	model : 'cityModel',
	tabPanel : null,
	autoLoad:false,
	proxy : {
		type : 'ajax',
		url : '../common/searchCommonCities.action',
		reader : {
			type : 'json',
			root : 'cityList'
		}
	} ,
	listeners:{
		load:function(store,record){
	        var me = this;
			for(var i=0;i<record.length;i++){
				Ext.getCmp(id+"CommonPanel").add({
					stateId:record[i].data.id,
	            	xtype:'button',
	            	width:70,
	            	text:getButtonName(record[i].data.name),
	            	handler : function(button) {
	            		//设置自动切换页签
	            		Ext.getCmp(id+'AreaTabPanel').setActiveTab(id+'AreaPanel');
	            		//每次都需要将上次界面清空
	            		if(Ext.isEmpty(Ext.data.StoreManager.lookup(id+'AreaStore'))){
	            			new areaStore({'storeId':id+'AreaStore'});
	            		}
	            		if(Ext.isEmpty(Ext.getCmp(id+'AreaPanel'))){
	            			Ext.data.StoreManager.lookup(id+'SearchCityByNameStore').loadRecords(Ext.data.StoreManager.lookup(id+'CommonStore').data.items);
	            			Ext.getCmp(id).setValue(button.getStateId()); 
	            			return;
	    				}
	            		Ext.getCmp(id+'AreaPanel').removeAll();
	            		Ext.data.StoreManager.lookup(id+'AreaStore').load({params:{'param':button.getStateId()}});
	            		Ext.data.StoreManager.lookup(id+'SearchCityByNameStore').removeAll();
	            		Ext.data.StoreManager.lookup(id+'SearchCityByNameStore').loadRecords(Ext.data.StoreManager.lookup(id+'CommonStore').data.items);
	            		Ext.getCmp(id).setValue(button.getStateId()); 
	            		if('commonPanel' == me.tabPanel[me.tabPanel.length-1]){
	            			Ext.getCmp(id+'AreaTabWindow').close();
	            		}
	            	}
	            });
			}
		}
	}
});	
//区域面板的store
Ext.define('areaStore', {
	extend:'Ext.data.Store',
	model : 'areaModel',
	tabPanel : null,
	autoLoad : false,
	proxy : {
		type : 'ajax',
		url : '../common/searchAreaByCity.action',
		actionMethods : 'post',// 否则可能会出现中文乱码
		reader : {
			type : 'json',
			root : 'areaList'
		}
	},
	listeners:{
		load:function(store,record){
			for(var i=0;i<record.length;i++){
				Ext.getCmp(id+'AreaPanel').add({
					stateId:record[i].data.id,
	            	xtype:'button',
	            	width:70,
	            	text:getButtonName(record[i].data.name),
	            	handler : function(button) {
						Ext.data.StoreManager.lookup(id+'SearchCityByNameStore').removeAll();
	            		Ext.data.StoreManager.lookup(id+'SearchCityByNameStore').loadRecords(Ext.data.StoreManager.lookup(id+'AreaStore').data.items);
	            		Ext.getCmp(id).setValue(button.getStateId()); 
	            		Ext.getCmp(id+'AreaTabWindow').close();
					}
				});
			}
		}
	}
});
//省份面板的store
Ext.define('provinceStore', {
	extend:'Ext.data.Store',
	model : 'provinceModel',
	tabPanel:null,
	autoLoad : false,
	proxy : {
		type : 'ajax',
		url : '../common/searchAllProvinceList.action',
		actionMethods : 'post',// 否则可能会出现中文乱码
		reader : {
			type : 'json',
			root : 'provinceList'
		}
	},
	listeners:{
		load:function(store,record){
			var me = this;
			for(var i=0;i<record.length;i++){
				if(i<=8){
					Ext.getCmp(id+"ProvincePanel_A_G").add({
//						id:record[i].data.id/* +','+record[i].data.province.name */,
						stateId:record[i].data.id,
		            	xtype:'button',
		            	width:70,
		            	text:getButtonName(record[i].data.name),
		            	handler : function(button) {
		            		//设置自动切换页签
		            		Ext.getCmp(id+'AreaTabPanel').setActiveTab(id+'CityPanel');
		            		//每次都需要将上次界面清空
		            		if(Ext.isEmpty(Ext.data.StoreManager.lookup(id+'SearchCityByProvince'))){
		            			new searchCityByProvince({'storeId':id+'SearchCityByProvince','tabPanel':me.tabPanel});
		            		}
		            		if(Ext.isEmpty(Ext.getCmp(id+'CityPanel'))){
		            			Ext.data.StoreManager.lookup(id+'SearchCityByNameStore').loadRecords(Ext.data.StoreManager.lookup(id+'ProvinceStore').data.items);
			            		Ext.getCmp(id).setValue(button.getStateId()); 
		            			return;
		    				}
		            		Ext.getCmp(id+'CityPanel').removeAll();
		            		Ext.data.StoreManager.lookup(id+'SearchCityByProvince').load({params:{'param':button.getStateId()}});
		            		Ext.data.StoreManager.lookup(id+'SearchCityByNameStore').removeAll();
		            		Ext.data.StoreManager.lookup(id+'SearchCityByNameStore').loadRecords(Ext.data.StoreManager.lookup(id+'ProvinceStore').data.items);
		            		Ext.getCmp(id).setValue(button.getStateId());  
		            		if('provincePanel' == me.tabPanel[me.tabPanel.length-1]){
		            			Ext.getCmp(id+'AreaTabWindow').close();
		            		}
		            	}
		            });
				}else if(i<=17){
					Ext.getCmp(id+"ProvincePanel_H_K").add({
//						id:record[i].data.id/* +','+record[i].data.province.name */,
						stateId:record[i].data.id,
		            	xtype:'button',
		            	width:70,
		            	text:getButtonName(record[i].data.name),
		            	handler : function(button) {
		            		//设置自动切换页签
		            		Ext.getCmp(id+'AreaTabPanel').setActiveTab(id+'CityPanel');
		            		//每次都需要将上次界面清空
		            		if(Ext.isEmpty(Ext.data.StoreManager.lookup(id+'SearchCityByProvince'))){
		            			new searchCityByProvince({'storeId':id+'SearchCityByProvince','tabPanel':me.tabPanel});
		            		}
		            		if(Ext.isEmpty(Ext.getCmp(id+'CityPanel'))){
		            			Ext.data.StoreManager.lookup(id+'SearchCityByNameStore').loadRecords(Ext.data.StoreManager.lookup(id+'ProvinceStore').data.items);
			            		Ext.getCmp(id).setValue(button.getStateId()); 
		            			return;
		    				}
		            		Ext.getCmp(id+'CityPanel').removeAll();
		            		Ext.data.StoreManager.lookup(id+'SearchCityByProvince').load({params:{'param':button.getStateId()}});
		            		Ext.data.StoreManager.lookup(id+'SearchCityByNameStore').removeAll();
		            		Ext.data.StoreManager.lookup(id+'SearchCityByNameStore').loadRecords(Ext.data.StoreManager.lookup(id+'ProvinceStore').data.items);
		            		Ext.getCmp(id).setValue(button.getStateId());  
		            		if('provincePanel' == me.tabPanel[me.tabPanel.length-1]){
		            			Ext.getCmp(id+'AreaTabWindow').close();
		            		}
		            	}
		            });
				}else if(i<=26){
					Ext.getCmp(id+"ProvincePanel_L_S").add({
//						id:record[i].data.id/* +','+record[i].data.province.name */,
						stateId:record[i].data.id,
		            	xtype:'button',
		            	width:70,
		            	text:getButtonName(record[i].data.name),
		            	handler : function(button) {
		            		//设置自动切换页签
		            		Ext.getCmp(id+'AreaTabPanel').setActiveTab(id+'CityPanel');
		            		//每次都需要将上次界面清空
		            		if(Ext.isEmpty(Ext.data.StoreManager.lookup(id+'SearchCityByProvince'))){
		            			new searchCityByProvince({'storeId':id+'SearchCityByProvince','tabPanel':me.tabPanel});
		            		}
		            		if(Ext.isEmpty(Ext.getCmp(id+'CityPanel'))){
		            			Ext.data.StoreManager.lookup(id+'SearchCityByNameStore').loadRecords(Ext.data.StoreManager.lookup(id+'ProvinceStore').data.items);
			            		Ext.getCmp(id).setValue(button.getStateId()); 
		            			return;
		    				}
		            		Ext.getCmp(id+'CityPanel').removeAll();
		            		Ext.data.StoreManager.lookup(id+'SearchCityByProvince').load({params:{'param':button.getStateId()}});
		            		Ext.data.StoreManager.lookup(id+'SearchCityByNameStore').removeAll();
		            		Ext.data.StoreManager.lookup(id+'SearchCityByNameStore').loadRecords(Ext.data.StoreManager.lookup(id+'ProvinceStore').data.items);
		            		Ext.getCmp(id).setValue(button.getStateId());  
		            		if('provincePanel' == me.tabPanel[me.tabPanel.length-1]){
		            			Ext.getCmp(id+'AreaTabWindow').close();
		            		}
		            	}
		            });
				}else{
					Ext.getCmp(id+"ProvincePanel_T_Z").add({
//						id:record[i].data.id/* +','+record[i].data.province.name */,
						stateId:record[i].data.id,
		            	xtype:'button',
		            	width:70,
		            	text:getButtonName(record[i].data.name),
		            	handler : function(button) {
		            		//设置自动切换页签
		            		Ext.getCmp(id+'AreaTabPanel').setActiveTab(id+'CityPanel');
		            		//每次都需要将上次界面清空
		            		if(Ext.isEmpty(Ext.data.StoreManager.lookup(id+'SearchCityByProvince'))){
		            			new searchCityByProvince({'storeId':id+'SearchCityByProvince','tabPanel':me.tabPanel});
		            		}
		            		if(Ext.isEmpty(Ext.getCmp(id+'CityPanel'))){
		            			Ext.data.StoreManager.lookup(id+'SearchCityByNameStore').loadRecords(Ext.data.StoreManager.lookup(id+'ProvinceStore').data.items);
			            		Ext.getCmp(id).setValue(button.getStateId()); 
		            			return;
		    				}
		            		Ext.getCmp(id+'CityPanel').removeAll();
		            		Ext.data.StoreManager.lookup(id+'SearchCityByProvince').load({params:{'param':button.getStateId()}});
		            		Ext.data.StoreManager.lookup(id+'SearchCityByNameStore').removeAll();
		            		Ext.data.StoreManager.lookup(id+'SearchCityByNameStore').loadRecords(Ext.data.StoreManager.lookup(id+'ProvinceStore').data.items);
		            		Ext.getCmp(id).setValue(button.getStateId());  
		            		//如果已经选择到 目标显示的最后选型则 关闭窗体
		            		if('provincePanel' == me.tabPanel[me.tabPanel.length-1]){
		            			Ext.getCmp(id+'AreaTabWindow').close();
		            		}
		            	}
		            });
				}
			}
		}
	}
});
//修改为在前台render方法中采用ajax同步请求
//Ext.define('SearchCityByIdStore', {
//		extend:'Ext.data.Store',
//		model : 'cityModel',
//		autoLoad : false,
//		param:null,
//		proxy : {
//			type : 'ajax',
//			url : '../common/searchCityById.action',
//			actionMethods : 'post',// 否则可能会出现中文乱码
//			reader : {
//				type : 'json',
//				root : 'cityList'
//			}
//		}
//	});
//Ext.define('SearchProvinceByIdStore', {
//		extend:'Ext.data.Store',
//		model : 'provinceModel',
//		autoLoad : false,
//		proxy : {
//			type : 'ajax',
//			url : '../common/searchProvinceById.action',
//			actionMethods : 'post',// 否则可能会出现中文乱码
//			reader : {
//				type : 'json',
//				root : 'provinceList'
//			}
//		}
//	});	
//Ext.define('SearchAreaByIdStore', {
//		extend:'Ext.data.Store',
//		model : 'areaModel',
//		autoLoad : false,
//		proxy : {
//			type : 'ajax',
//			url : '../common/searchAreaById.action',
//			actionMethods : 'post',// 否则可能会出现中文乱码
//			reader : {
//				type : 'json',
//				root : 'areaList'
//			}
//		}
//	});
