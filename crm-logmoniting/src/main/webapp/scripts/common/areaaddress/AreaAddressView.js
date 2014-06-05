	/**
     * 截取名字
     */
   var id = null;
    function getButtonName(value){
		var array = value.split("-");
		//此处只显示前面4个字符
		return array[array.length-1].substr(0,4);
    }
   Ext.onReady(function(){
   	//定义省市区页签
     Ext.define('AreaTabPanel', {
    	extend:'Ext.tab.Panel',
		activeTab : 0,
		//默认显示4个页签，分别为常用、省份、城市、区域
		tabPanel:['commonPanel','provincePanel','cityPanel','areaPanel'],
		getId:function(){
			return id+'AreaTabPanel';
		},
		getItems:function(){
			var me = this;
			//常用页签
			var common = {
					title : '常用',
					bodyPadding: 5,
					id:id+'CommonPanel',
					width:400,
					height:175,
					layout: {
			            type: 'table',
			            columns: 5
			        },
					xtype:'panel',
					//默认激活时候，给该页签加载数据到面板上，绘制面板
					listeners:{
						activate:function(){
							if(Ext.isEmpty(Ext.data.StoreManager.lookup(id+'CommonStore'))){
								new commonStore({'id':id+'CommonStore','tabPanel':me.tabPanel})
							}
							if(Ext.data.StoreManager.lookup(id+'CommonStore').data.length==0){
								Ext.data.StoreManager.lookup(id+'CommonStore').load();
							}
						}
					}
				};
			//省份页签	
			var province = {
					title : '省份',
					width:400,
					id:id+'ProvincePanel',
					height:175,
					layout: {
			            type: 'table',
			            columns: 1
			        },
			        defaults:{
						width:400
			        },
					xtype:'panel' ,
					items:[{
					    xtype:'panel',
					    layout: {
				            type: 'table',
				            columns: 5
				        },
				        defaults:{
				        	width:26, 
				        	height: 41
				        },
				        items:[{
				        	html:'A-G',
							rowspan : 2
				        },{
				        	id:id+'ProvincePanel_A_G',
							layout: {
					            type: 'table',
					            columns: 5
					        },
					        width:360
				        }]
					},{
						xtype:'panel',
					    layout: {
				            type: 'table',
				            columns: 5
				        },
				        defaults:{
				        	width:26, 
							height: 41
				        },
				        items:[{
				        	html:'H-K',
							rowspan : 2
				        },{
				        	id:id+'ProvincePanel_H_K',
							layout: {
					            type: 'table',
					            columns: 5
					        },
					        width:360
				        }]
					},{
						xtype:'panel',
					    layout: {
				            type: 'table',
				            columns: 5
				        },
				        defaults:{
				        	width:26, 
							height:  41
				        },
				        items:[{
				        	html:'L-S',
							rowspan : 2
				        },{
				        	id:id+'ProvincePanel_L_S',
							layout: {
					            type: 'table',
					            columns: 5
					        },
					        width:360
				        }]
					},{
						xtype:'panel',
					    layout: {
				            type: 'table',
				            columns: 5
				        },
				        defaults:{
				        	width:26, 
							height: 41
				        },
				        items:[{
				        	html:'T-Z',
							rowspan : 2
				        },{
				        	id:id+'ProvincePanel_T_Z',
							layout: {
					            type: 'table',
					            columns: 5
					        },
					        width:360
				        }]
					}],
					//默认激活时候，给该页签加载数据到面板上，绘制面板
					 listeners:{
						activate:function(){
							if(Ext.isEmpty(Ext.data.StoreManager.lookup(id+'ProvinceStore'))){
								new provinceStore({'id':id+'ProvinceStore','tabPanel':me.tabPanel})
							}
							if(Ext.data.StoreManager.lookup(id+'ProvinceStore').data.length==0){
								Ext.data.StoreManager.lookup(id+'ProvinceStore').load();
							}
						}
					} 
				};
			//城市页签	
			var city = {
					title : '城市',
					id:id+'CityPanel',
					xtype:'panel',
					width:400,
					height:175,
					bodyPadding: 5,
					layout: {
			            type: 'table',
			            columns: 5
					}
				};
			//区域页签	
			var area = {
					title : '区域',
					id:id+'AreaPanel',
					xtype:'panel',
					width:400,
					height:175,
					bodyPadding: 5,
					layout: {
			            type: 'table',
			            columns: 5
			        }
				};
			//此处主要是根据外面传递进来的tabPanel参数，来控制显示面板个数，如只显示省份等	
			var array = new Array();
			for(var i=0;i<me.tabPanel.length;i++){
				if(me.tabPanel[i]=='commonPanel'){
					array.push(common);
				}
				if(me.tabPanel[i]=='provincePanel'){
					array.push(province);
				}
				if(me.tabPanel[i]=='cityPanel'){
					array.push(city);
				}
				if(me.tabPanel[i]=='areaPanel'){
					array.push(area);
				}
			}
			return array;
		},
	 initComponent:function(){
	    	var me = this;
	    	me.items = me.getItems();
	    	me.id = me.getId();
	    	this.callParent();
	   }
	});
	//弹出省市区页签的窗口
    Ext.define('AreaTabWindow',{
    	extend:'Ext.window.Window',
    	width : 400,
		height : 235,
		cls:'provincepanel',
		tabPanel:['commonPanel','provincePanel','cityPanel','areaPanel'],
		resizable:false,
		draggable:false,
		closeAction : 'hidden',
		getItems:function(){
			var me = this;
			return [new AreaTabPanel({'tabPanel':me.tabPanel})];
		},
		getId:function(){
			return id+'AreaTabWindow';
		},
		initComponent:function(){
	    	var me = this;
	    	me.items = me.getItems();
	    	me.id = me.getId();
	    	this.callParent();
	   }
    });
    //
	Ext.define('AreaAddressCombox',{
		extend : 'Ext.form.ComboBox',
		alias : 'widget.areaaddresscombox',
		tabPanel:['commonPanel','provincePanel','cityPanel','areaPanel'],
		width : 220,
		fieldLabel : '省市区',
		labelWidth : 40,
		labelSeparator:'',//剔除“：”
		readOnly:true,
		queryMode : 'remote',
		queryParam : 'param',// 指定向后台传参数的变量名称。Extjs4默认为query;
		emptyText : '请输入城市名(中文/拼音)',
		//新增参数 ，判断是查询还是新增
		operateType:null,
		selectedValue:null,//改字段主要是针对查询和修改时，传递到
		triggerAction : 'all',
		minChars : 1,//输入1个字节，就开始模糊查询
		hideTrigger : true,
		forceSelection : false,
		name : 'param',
		displayField : 'name',
		typeAhead : false,
		test:null,
		valueField : 'id',
		getStore:function(){
			var me = this;
			return new searchCityByNameStore({'id':this.id+'SearchCityByNameStore'});;
		},
		initComponent:function(){
	    	var me = this;
	    	me.store = me.getStore();
	    	for(var i=0;i<me.tabPanel.length;i++){
	    		if(me.tabPanel[i]=='cityPanel'){
	    			me.setReadOnly(false);
	    		}
	    	}
	    	if(me.operateType=='VIEW'){
	    		me.setDisabled(true);
	    	}
	    	this.callParent();
	   },
		listeners : {
			//点击下拉框时弹出省市区面板
			'focus' : function(th) {
				id = th.id;
				//如果该WINDOW存在，则显示。不存在就创建一个显示
				if(Ext.isEmpty(Ext.getCmp(id+'AreaTabWindow'))){
					new AreaTabWindow({'tabPanel':th.tabPanel}).show();
				}else{
					Ext.getCmp(id+'AreaTabWindow').show();
				}
				var position  = th.getPosition();
				position[1] = position[1]+22;
				Ext.getCmp(id+'AreaTabWindow').setPosition(position);
			},
			//下拉空输入查询时，关闭省市区面板
			'change' : function(field, newValue, oldValue, eOpts) {
					// 如果清空数据，则将panel收缩
				if (newValue == '') {
					if(Ext.getCmp(id+'AreaTabWindow')){
						Ext.getCmp(id+'AreaTabWindow').close();
					}
				}
			},
			//选择城市时，会自动弹出区域面板，供选择
			'select':function(combo,records,eOpts){
				if(Ext.isEmpty(Ext.getCmp(id+'AreaPanel'))){
					return ;
				}
				Ext.getCmp(id+'AreaTabWindow').show();
				//设置自动切换页签
				Ext.getCmp(id+'AreaTabPanel').setActiveTab(id+'AreaPanel');
	    		//每次都需要将上次界面清空
	    		Ext.getCmp(id+'AreaPanel').removeAll(true);
	    		if(Ext.isEmpty(Ext.data.StoreManager.lookup(id+'AreaStore'))){
	    			new areaStore({'id':id+'AreaStore'});
	    		}
	    		Ext.data.StoreManager.lookup(id+'AreaStore').load({params:{'param':records[0].data.id}});
			},
			//改方法主要是在渲染完后，针对查询和修改时，需要将值填充到下拉空面板中
			render:function(){
				var me=this;
				if(me.selectedValue!=null && me.selectedValue!=''){
					if(me.operateType=='VIEW'||me.operateType=='UPDATE'){
						id = me.id;
						if(me.tabPanel[me.tabPanel.length-1]=='provincePanel'){
								Ext.Ajax.request({
								url:'../common/searchProvinceById.action',
								async:false,
								params:{'param':me.selectedValue},
								success:function(response){
									var result = Ext.decode(response.responseText);
									me.setValue(me.selectedValue);
									if(result.provinceList.length>0){
										me.setRawValue(result.provinceList[0].name);
									}
								},
								failure:function(response){
									var result = Ext.decode(response.responseText);
//									Ext.MessageBox.alert("提示",result.message);
									MessageUtil.showMessage(result.message);
								}
							});	
						}else if(me.tabPanel[me.tabPanel.length-1]=='cityPanel'){
							Ext.Ajax.request({
								url:'../common/searchCityById.action',
								async:false,
								params:{'param':me.selectedValue},
								success:function(response){
									var result = Ext.decode(response.responseText);
//									me.setValue(me.selectedValue);
//									if(result.cityList.length>0){
//										me.setRawValue(result.cityList[0].name);
//									}
									//修改者 李学兴 
									var store = Ext.data.StoreManager.lookup(this.id+'SearchCityByNameStore');
									store.insert(0,Ext.create('cityModel',result.cityList[0]));
									me.setValue(result.cityList[0].id);
								},
								failure:function(response){
									var result = Ext.decode(response.responseText);
//									Ext.MessageBox.alert("提示",result.message);
									MessageUtil.showMessage(result.message);
								}
							});	
						}else{
								Ext.Ajax.request({
								url:'../common/searchAreaById.action',
								async:false,
								params:{'param':me.selectedValue},
								success:function(response){
									var result = Ext.decode(response.responseText);
//									me.setValue(me.selectedValue);
//									if(result.areaList.length>0){
//										me.setRawValue(result.areaList[0].name);
//									}
									//修改者 李学兴 
									var store = Ext.data.StoreManager.lookup(this.id+'SearchCityByNameStore');
									store.insert(0,Ext.create('areaModel',result.areaList[0]));
									me.setValue(result.areaList[0].id);
								},
								failure:function(response){
									var result = Ext.decode(response.responseText);
//									Ext.MessageBox.alert("提示",result.message);
									MessageUtil.showMessage(result.message);
								}
							});	
						}
					}
				}
			}
		}
	});
 }); 
	
	

 