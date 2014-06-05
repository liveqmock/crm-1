var map;
//当前定位的数据
var currentRecord=null;
//当前表格选中数据
var exitsPoint = [];
var datas = null;
/**
 * 初始化部门用户信息
 * @author 张登
 * @return User.loginName(登录帐号),User.userId(用户Id),User.empCode(用户编号),User.empName(用户名称)
 * @return User.deptId(部门Id),User.deptCode(部门编号),User.deptName(部门名称)
 * @return User.roleids(用户所拥有的角色信息ID集合),User.deptids(用户所拥有的部门信息ID集合),注：此两项为数组
 * @时间 2012-3-29
 */
var User={};
function initDeptAndUserInfo(){
	Ext.Ajax.request({
		url : '../common/queryUserInfo.action',
		async:false,
		success:function(response){
			var result = Ext.decode(response.responseText);
			User=result.user;
			return User;
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
			Ext.MessageBox.alert(i18n('i18n.PotentialCustManagerView.message'),result.message);
		}
	});
};
Ext.onReady(function(){

var custInfo = [];
//加载数据字典
var keys = [
	//客户类别
	'CUSTOMER_CATEGORY',
	//行业
	'TRADE',
	//二级行业
	'SECOND_TRADE',
	//会员等级
	'MEMBER_GRADE',
	//客户类别
	'CUST_TYPE',
	// 商机状态
	'BUSINESS_STATUS',
	// 合作物流
	'COOPERATION_LOGISTICS',
	// 合作意向
	'COOPERATION_INTENTION',
	// 货量潜力
	'CARGO_POTENTIAL',
	//公司性质
	'COMP_NATURE',
	//客户属性
	'CUSTOMER_NATURE',
	//客户性质
	'CUSTOMER_TYPE'
];
//初始化业务参数
initDataDictionary(keys);
initDeptAndUserInfo();
/**
 * 定位坐标等操作按钮区域
 */
Ext.define('ManyBtnTest',{
	extend:'BasicVboxPanel',	  
	items:null,
	region:'south',
	height:100,
	initComponent:function(){
		this.items = this.getItems();  
		this.callParent();
	},
	getItems:function(){//定位标注     
		return [{
				xtype:'basichboxcenterpanel',  
				items:[{
	 	          	xtype:'button',
	 	          	width:95,
	 	         	text: i18n('i18n.fiveKilometreMap.Positioning'),
	 	         	handler:function(){
	 	         		//每次定位坐标时，先清除之前的信息
	 	         		Ext.getCmp('mGrid').store.removeAll(); 
	 	         		var mapGrid = Ext.getCmp('mapGrid');
	 	         		var selection = mapGrid.getSelectionModel().getSelection();
						//判断是否选中行
						if(selection.length==0){
							MessageUtil.showMessage(i18n('i18n.developPlan.choice'));
							return false;
						};
						//只能选择1条数据来定位坐标
						if(selection.length>1){
							MessageUtil.showMessage(i18n('i18n.fiveKilometreMap.choiceOne'));
							return false;
						}
						currentRecord = selection[0];
						// 点击定位标注按钮时，调用地图接口，重新查询地址信息
//						if((currentRecord.data.lng!=null&&currentRecord.data.lat!=null && currentRecord.data.lng!=''&&currentRecord.data.lat!='')){
//					    	point = new BMap.Point(currentRecord.data.lng,currentRecord.data.lat);
//						}else 
					    if(currentRecord.data.linkmanPreferAddress!=null && currentRecord.data.linkmanPreferAddress!=''){
							if(currentRecord.data.city!=null&&currentRecord.data.city!=''){
								 map.clearOverlays();
								 localSearchByCity(map,currentRecord.data.linkmanPreferAddress,currentRecord.data.city,currentRecord.data.customerType);
							}else{
								map.clearOverlays();
								localSearchByMap(map,currentRecord.data.linkmanPreferAddress,currentRecord.data.customerType);
							}
						}else{
							MessageUtil.showMessage(i18n('i18n.fiveKilometreMap.userWarning'));
						}
					   
					}
				},{
	 	    	    xtype:'button',
	 	    	    width:95,
	 	         	text: i18n('i18n.fiveKilometreMap.savePoint'),
	 	         	handler:function(){
	 	         		
	 	         		
						var mapGrid = Ext.getCmp('mapGrid');
						var selection=mapGrid.getSelectionModel().getSelection();
						//判断是否选中行
						if (selection.length == 0) {
							MessageUtil.showMessage(i18n('i18n.developPlan.choice'));
							return false;
						}
						if (selection.length > 1){
							// 仅能保存1条数据
							MessageUtil.showMessage(i18n('i18n.fiveKilometreMap.choiceOne'));
							return false;
						}
						if(Ext.getCmp("mGrid").store.getCount()===1){//判断右边可选地址列表只有一条数据
							var record=Ext.getCmp("mGrid").store.getAt(0);
							var pointRecord = record.data.point;
							var lng = pointRecord.split(",")[0];
							var lat = pointRecord.split(",")[1];
							point = new BMap.Point(lng,lat);
						}
						//保存成功回调函数
						var delSuccessFn = function(result){
							MessageUtil.showInfoMes(i18n('i18n.developSchedule.updateSuccess'));
						};
						//保存失败回调函数
						var delFailFn = function(result){
							MessageUtil.showErrorMes(result.message)
						};
						var params={
							'location.customerId':selection[0].get('customerId'),
							'location.customerType':selection[0].get('customerType'),
							'location.lat':point.lat,
							'location.lng':point.lng,
							'location.linkmanId':selection[0].get('linkmanId')
						};
						mapData.prototype.markCustomerLocation(params,delSuccessFn,delFailFn);
					}
				}]
			},{
				xtype:'basichboxcenterpanel',  
				items:[{
	 	          	xtype:'button',
	 	          	width:95,
	 	         	text: i18n('i18n.fiveKilometreMap.makePlan'),
	 	         	hidden:!isPermission('marketing.map.mapView.btnSavePlan'),
	 	         	handler:function(){
	 	         		var mapGrid = Ext.getCmp("mapGrid");
						var selection = mapGrid.getSelectionModel().getSelection();
						if(selection.length == 0){
							MessageUtil.showMessage(i18n('i18n.developPlan.choice'));
							return false;
						}
						custInfo = [];
						if(selection[0].data.customerType=='PC_CUSTOMER'||selection[0].data.customerType=='SC_CUSTOMER'){
							//潜散客
							for(var i = 0;i<selection.length;i++){
								custInfo.push(selection[i].data.customerId);
							}
							Ext.getCmp('searchResultGridId').store.load();
							//清除已选表格的数据
							Ext.getCmp('ChooseCustomerGridId').store.removeAll();
							deliveryDevelopPopWindow.show();
							
							var deptName=Ext.getCmp('mapCustomerForm').getForm().findField('deptId').getRawValue();
							var deptId=Ext.getCmp('mapCustomerForm').getForm().findField('deptId').getValue();
							
							Ext.getCmp("developPlanFromPanel").getForm().findField("execdeptid").setValue(deptName);
							Ext.getCmp("developPlanFromPanel").getForm().findField("execuserid").store.load({params:{executeDept:deptId}});
							
						 }else{
						 	//	会员
						 	for(var i = 0;i<selection.length;i++){
								custInfo.push(selection[i].data.linkmanId);
							}
							Ext.getCmp('searchMaintainResultGridId').store.load();
							//清除已选表格的数据
							Ext.getCmp('maintainChooseCustomerGridId').store.removeAll();
							deliveryMainTainPopWindow.show();
							
							var deptName=Ext.getCmp('mapCustomerForm').getForm().findField('deptId').getRawValue();
							var deptId=Ext.getCmp('mapCustomerForm').getForm().findField('deptId').getValue();
							
							Ext.getCmp("planFromPanel").getForm().findField("execdeptid").setValue(deptName);
							Ext.getCmp("planFromPanel").getForm().findField("execuserid").store.load({params:{executeDept:deptId}});
							
						 }
						document.getElementsByTagName("html")[0].style.overflowY="auto";
						document.getElementsByTagName("html")[0].style.overflowX="auto";
						viewport.doLayout();
	 	         	}
				},{
					xtype:'panel',
					border:false,
					width:95
				}
//				,{
//	 	    	    xtype:'button',
//	 	    	    width:95,
//	 	         	text:i18n('i18n.fiveKilometreMap.search360View'),
//	 	         	handler:function(){
//						alert(i18n('i18n.fiveKilometreMap.search360View'));
////						http://localhost:8080/crm-custview/custview/memberViewIndex.action
//						var url = 'http://localhost:8080/crm-custview/custview/memberViewIndex.action';
//						window.open(url);
////TODO:1,open的url；2、open需要在target：parent						
//					}
//				}
				]
			}]
	}
});

Ext.define('RightButtonDemoPanel',{
	extend:'PopButtonPanel', 
	items:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'middlebuttonpanel' 
		},{
			xtype:'poprightbuttonpanel', 
			width:60,
			items:[{ 
				xtype:'button',
				text:i18n('i18n.fiveKilometreMap.search'),
				handler:function(){
					if(Ext.getCmp('mapCustomerForm').getForm().isValid()){
						Ext.getCmp('mapGrid').store.loadPage(1);
					}else{
						MessageUtil.showMessage(i18n('i18n.mapView.pleaseInsureSearchCorrect')); 
						return false;
					}
					
				}
			}]
		}];
	}
});

/**
 * 查询信息表单
 */
Ext.define('MapCustomerForm',{
	extend:'SearchFormPanel',
	region : 'west',
	width:220,
	layout:'border',
	padding:'5px',
	items:null,
	getItems:function(){
		return [{
			xtype:'basicpanel',
			region:'center',
			layout:'vbox',
			defaults:{
				labelWidth:75,
				width:200
			},
			items:[{
				fieldLabel : i18n('i18n.PotentialCustManagerView.deptName'),
				name:'deptId',
				id:'deptId',
				xtype:'combo',
				store:Ext.create('DeptStore',{
					listeners:{
						beforeload:function(store, operation, eOpts){
							Ext.apply(operation,{
								params : {
									'deptName':Ext.getCmp("mapCustomerForm").getForm().findField("deptId").getRawValue()
									}
								}
							);	
						}
					}
				}),
//				colspan:3,
				allowBlank :false,
				triggerAction : 'all',
				displayField:'deptName',
				valueField:'id',
				forceSelection :true,
				hideTrigger:false,
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
					change:DButil.comboSelsct
				}
			}
//			{
//				xtype:'combo',
//				fieldLabel:i18n('i18n.fiveKilometreMap.deptId'),
//				name:'deptId',
//				id:'deptId',
//				store:Ext.create('DeptStore',{
//					listeners:{
//						load:function(){
//							Ext.getCmp("deptId").setValue(User.deptId);
//						}
//					}
//				}),
//				queryMode:'local',
//				displayField:'deptName',
//				valueField:'id',   
//				forceSelection :true,
//				allowBlank:false,
//				listeners:{
//					change:DButil.comboSelsct
//				}
//			}
			,{
				xtype:'combo',
				name:'customerType',
				id:'customerType',
				allowBlank:false,
				fieldLabel:i18n('i18n.fiveKilometreMap.customerType'),
				queryMode:'local',
				store:getDataDictionaryByName(DataDictionary,'CUSTOMER_CATEGORY'),
				displayField:'codeDesc',
				valueField:'code',
				value:'MEMBER',
				forceSelection :true,
				listeners:{
					change:DButil.comboSelsct,
					select:function(t){
						if(t.getValue()=='MEMBER'){
							Ext.getCmp("degree").enable();
						}else{
							Ext.getCmp("degree").disable();
						}
					}
				}
			},{ 
				xtype:'combo',
				name:'degree',
				id:'degree',
				margin:'0 0 0 0',
				fieldLabel:i18n('i18n.fiveKilometreMap.degree'),
				queryModel:'local',
				store:getDataDictionaryByName(DataDictionary,'MEMBER_GRADE'),
				displayField:'codeDesc',
				valueField:'code',
				forceSelection :true,
				listeners:{
					change:DButil.comboSelsct
				}
			  },
//			  {
//				xtype:'combo',
//				name:'customerIndustry',
//				id:'customerIndustry',
//				fieldLabel:i18n('i18n.fiveKilometreMap.customerIndustry')+'xxhh',
//				queryModel:'local',
//				store:getDataDictionaryByName(DataDictionary,'TRADE'),
//				displayField:'codeDesc',
//				valueField:'code',
//				forceSelection :true,
//				listeners:{
//					change:DButil.comboSelsct
//				}
//			},
		    {//二级行业
				xtype:'customertrade',
				userType:'SEARCH',
				layout:{
					type:'table',
					columns:1
				},
				margin:'0 0 0 0',
				fieldLabel:i18n('i18n.PotentialCustManagerView.industry'),
				trade_margin:'0 0 5 0',
//				second_trade_margin:'0 0 5 0',
				trade_labelWidth:65,
				trade_width:190,
				trade_name:'trade',
				trade_fieldname:i18n('i18n.PotentialCustManagerView.firstTrade'),
				second_trade_labelWidth:65,
				second_trade_width:190,
				second_trade_name:'secondTrade',
				second_trade_fieldname:i18n('i18n.PotentialCustManagerView.secondTrade')
			},
			{
				xtype:'textfield',
				name:'customerName',
				id:'customerName',
				fieldLabel:i18n('i18n.fiveKilometreMap.customerName'),
				maxLength : 20,
				maxLengthText : i18n('i18n.developPlan.maxLength')+20
			},{
				xtype:'textfield',
				name:'linkmanName',
				id:'linkmanName',
				fieldLabel:i18n('i18n.fiveKilometreMap.linkmanName'),
				maxLength : 20,
				maxLengthText : i18n('i18n.developPlan.maxLength')+20
			},{
				xtype:'textfield',
				name:'linkmanMobile',
				id:'linkmanMobile',
				minLength:8,
				maxLength:11,
				fieldLabel:i18n('i18n.fiveKilometreMap.linkmanMobile'),
				regexText:i18n('i18n.ScatterCustManagerView.message_rightString'),
				//配合香港开点：手机为8位数字或是首位为1的11位数字
				regex : /(^1\d{10}$)|(^\d{8}$)/
			},{
				xtype:'textfield',
				name:'linkmanPhone',
				id:'linkmanPhone',
				fieldLabel:i18n('i18n.fiveKilometreMap.linkmanPhone'),
				maxLength : 20,
				maxLengthText : i18n('i18n.developPlan.maxLength')+20
//				regexText:i18n('i18n.ScatterCustManagerView.message_rightString'),
//				regex : /^\d{1,15}$/
			},Ext.create('RightButtonDemoPanel')
			]},Ext.create('ManyBtnTest')
		]
	},initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
//		Ext.getCmp("deptId").store.load();
	}
});

var sel = Ext.create('Ext.selection.CheckboxModel',{
		mode:'MULTI',
		ignoreRightMouseSelection:true,//忽略右键
		//checkOnly :true,//只有选择checkbox时，才会选中
		listeners:{
			'selectionchange':function(th,records,opts){
				//全选
				if(records.length ==Ext.getCmp('mapGrid').store.data.length){
				//每次勾选新的，先清除之前的信息
	 	         Ext.getCmp('mGrid').store.removeAll(); 
	 	         var mapGrid = Ext.getCmp('mapGrid');
					exitsPoint = [];
					var nullPoint = [];
					for(var i = 0;i<records.length;i++){
						//查询有x和y轴坐标的旗子
						if(records[i].get('lat')!=''&&records[i].get('lng')!=''&&
									records[i].get('lat')!=null&&records[i].get('lng')!=null){   
							exitsPoint.push(records[i]);
						}else{
							nullPoint.push(records[i]);
						}
					};
					showAllMark(exitsPoint);
				}
				datas = exitsPoint;
			},
			'select':function(th,record){
				//每次勾选新的，先清除之前的信息
	 	         Ext.getCmp('mGrid').store.removeAll(); 
	 	         var mapGrid = Ext.getCmp('mapGrid');
				if(record.get('lat')==''||record.get('lng')==''||
					record.get('lat')==null||record.get('lng')==''){   
					point.lat = '';
					point.lng = '';
//		           	MessageUtil.showInfoMes(i18n('i18n.mapView.userNoPointPonts'));
		           	return false;
	            }else{
	            	var isExist = false;
	            	for(var i = 0;i<exitsPoint.length;i++){
	            		if(record.get('lng')==exitsPoint[i].data.lng && record.get('lat')==exitsPoint[i].data.lat){
	            			flag = true;
	            		}
	            	}
	            	if(!isExist){
	            		exitsPoint.push(record);
	            	}
					point.lat = record.get('lat');
					point.lng = record.get('lng');
	            	showAllMark(exitsPoint);
	            }
			}
			,'deselect':function(th,record){
				for ( var i=0 ; i < exitsPoint.length ; ++i ){
					if ( record.data.lat == exitsPoint[i].data.lat && record.data.lng == exitsPoint[i].data.lng ){
					     if ( i > exitsPoint.length/2 ){
					        for ( var j=i ; j < exitsPoint.length-1 ; ++j ){
					        	exitsPoint[j] = exitsPoint[j+1];
					        }
					        	exitsPoint.pop();                   
					        }else{
					        	 for ( var j=i ; j > 0 ; --j ){
					                exitsPoint[j] = exitsPoint[j-1];
					             }
					        	 exitsPoint.shift();
					            }   
					            break;               
					        }
			   }
				if(exitsPoint.length>0){
					showAllMark(exitsPoint);
				}else{
					map.clearOverlays();
				}
			}
		}	
	});

/**
 *地图下面打印按钮 
 */
Ext.define('LeftButtonDemoPanel',{
	extend:'PopButtonPanel', 
	region:'south',
	items:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'popleftbuttonpanel',
			width:400,//定义宽度
			defaultType:'button',
			items:[{
				text:i18n('i18n.fiveKilometreMap.print'),
				handler:function(){
					if(!Ext.isEmpty(exitsPoint)){
						var url		= '../marketing/print.action';
						datas.bool	= true;
						window.open(url,'namePrint');
					}else{
						MessageUtil.showMessage(i18n('i18n.mapView.noPrintUserInfo'));
					}
				}
			},
//			{
//				text:i18n('i18n.fiveKilometreMap.printSet'),
//				handler:function(){
//					alert(i18n('i18n.fiveKilometreMap.printSet'));
//				}
//			},
			{
				text:i18n('i18n.fiveKilometreMap.printView'),
				handler:function(){
					if(!Ext.isEmpty(exitsPoint)){
						var url		= '../marketing/print.action';
						datas.bool	= false;
						window.open(url,'namePrint');
					}else{
						MessageUtil.showMessage(i18n('i18n.mapView.noPrintPreViewUserInfo'));
					}
				}
			}]
		},{
			xtype:'middlebuttonpanel'
		}]
	}
});

/**
 * 地图区域，包含打印按钮
 */
Ext.define('MapForm',{
	extend:'BasicPanel',
	region:'center',
	item:null,
	layout:'border',
	getItems:function(){
		return [{
			xtype:'basicpanel',
			region:'center',
		    html :"<div id='map' style='width:100%;height:100%'></div>",
			listeners : {
 				     afterrender: function() {
 			         map = new BMap.Map('map');
 			         point = new BMap.Point(116.404, 39.915);// 创建点坐标
 			         map.centerAndZoom(point,15);// 初始化地图,设置中心点坐标和地图级别。
 			         map.addControl(new BMap.NavigationControl());
 			         map.enableScrollWheelZoom();                  // 启用滚轮放大缩小。
 			         map.enableKeyboard();					 // 启用键盘操作。
 			    }
		    }
		},Ext.create('LeftButtonDemoPanel')];
	},initComponent:function()
		{
			var me = this;
			me.items = me.getItems();
			this.callParent();
		}
});

/**
 * 地图信息列表grid
 */
Ext.define('MapGrid',{
	extend:'SearchGridPanel',
	region:'south',
	height:180,
	columns:null,
	columnLines:true,
	viewConfig : {
		forceFit : true
   	},
	selModel:sel,
	getColumns:function(){
		return [{
				header : i18n('i18n.fiveKilometreMap.customerName'),
				dataIndex : 'customerName',
				align : 'center',
				width : 95,
				sortable : true
			}, {
				header :i18n('i18n.fiveKilometreMap.linkmanName'),
				dataIndex : 'linkmanName',
				align : 'center',
				width : 90,
				sortable : true
			},{
				header:i18n('i18n.fiveKilometreMap.city'),
				dataIndex:'city',
				align : 'center',
				width : 85
			}, {
				header : i18n('i18n.fiveKilometreMap.goodaddress'),
				dataIndex : 'linkmanPreferAddress',
				align : 'center',
				width : 140,
				sortable : true
			}, {
				header :i18n('i18n.fiveKilometreMap.linkmanMobile'),
				dataIndex : 'linkmanMobile',
				align : 'center',
				width : 85,
				sortable : true
			}, {
				header : i18n('i18n.fiveKilometreMap.linkmanPhone'),
				dataIndex : 'linkmanPhone',
				sortable : true,
				align : 'center',
				width : 85
			},{
				header : i18n('i18n.fiveKilometreMap.customerType'),
				dataIndex : 'customerType',
				align : 'center',
				width : 95,
				sortable : true,
				renderer:function(value){
					// CUST_TYPE:潜散客 客户类别， 		MEMBER_GRADE：会员客户等级			
					var name = DButil.rendererDictionary(value,DataDictionary.CUST_TYPE);
					if (name == null){
						name = DButil.rendererDictionary(value,DataDictionary.MEMBER_GRADE);
					}
					return name;
				}
			}, {
				header : i18n('i18n.PotentialCustManagerView.firstTrade'),
				dataIndex : 'industry',
				align : 'center',
				width : 88,
				sortable : true,
				renderer:function(value){
					return DButil.rendererDictionary(value,DataDictionary.TRADE);
				}
			}, {//二级行业
				header : i18n('i18n.PotentialCustManagerView.secondTrade'),
				dataIndex : 'secondTrade',
				align : 'center',
				width : 88,
				sortable : true,
				renderer:function(value){
					return DButil.rendererDictionary(value,DataDictionary.SECOND_TRADE);
				}
			}];
			
	},
	initComponent:function(){
		var me = this;
		me.columns = me.getColumns();
		//添加分页
		me.dockedItems=[{
			xtype:'pagingtoolbar',
			store:me.store,
			dock:'bottom',
			displayInfo : true,
			autoScroll : true
		}];
		//添加查询条件
		me.store.on('beforeLoad',function(th,operation,e){
			map.clearOverlays();
			//将表单查询条件传递到后台去
			var searchCustomerForm = Ext.getCmp("mapCustomerForm");
			//获取二级行业
			var firstTrade = searchCustomerForm.down('form').getForm().findField('trade').getValue();
			var secondTrade = searchCustomerForm.down('form').getForm().findField('secondTrade').getValue();
			var searchParams = {
				'condition.deptId':searchCustomerForm.getForm().findField('deptId').getValue(),
				'condition.customerType':searchCustomerForm.getForm().findField('customerType').getValue(),
				'condition.customerName':searchCustomerForm.getForm().findField('customerName').getValue(),
				//二级行业
				'condition.customerIndustry':firstTrade,
				'condition.secondTrade':secondTrade,
				'condition.linkmanName':searchCustomerForm.getForm().findField('linkmanName').getValue(),
				'condition.linkmanMobile':searchCustomerForm.getForm().findField('linkmanMobile').getValue(),
				'condition.linkmanPhone':searchCustomerForm.getForm().findField('linkmanPhone').getValue(),
				'condition.degree':searchCustomerForm.getForm().findField('degree').getValue()
			};
			Ext.apply(operation,{
				params : searchParams
			});	
		});
		me.store.on('load',function(s,records){   
	        var girdcount=0;   
	        s.each(function(r){   
	        	if(r.get('lat')==''||r.get('lng')==''||r.get('lat')==null||r.get('lng')==''){
	           	   //给单元格涂色
	           	   	var cells = me.getView().getNodes()[girdcount].children;
 	    			for(var i= 0;i<cells.length;i++){
 	    				cells[i].style.backgroundColor='#B8B8DC';
 	    			}
	            }
 	    		girdcount++;
	        });   
		});   
		//给表格添加单击事件
		me.on('itemclick',function(th,record,item,rowindex){
			//默认收缩面板
			Ext.getCmp("showAddress").collapse();
		});
		this.callParent();
		
	}
});

/**
 * 隐藏地图地址列表
 */
Ext.define('MapListGrid',{
	extend:'PopupGridPanel',
	store:null,
	columns:null,
	columnLines:true,
	getColumns:function(){
		return [ Ext.create('Ext.grid.RowNumberer'),
			{
				header : i18n('i18n.fiveKilometreMap.address'),
				dataIndex : 'address',
				align : 'center',
				renderer:function(value,metaData,record){
				    //获取坐标传参
					var point = record.data.point;
					var lng = point.split(",")[0];
					var lat = point.split(",")[1];
					return '<a href="javascript:showMark('+lng+','+lat+')">'+value+'</a>';
				},
				width:220,
				sortable : true
			},{
				header : i18n('i18n.fiveKilometreMap.point'),
				dataIndex : 'point',
				width:10,
				align : 'center',
				hidden:true,
				sortable : true
			}];
		},
	initComponent:function(){
		var me = this;
		me.columns = me.getColumns();
		me.store = Ext.create('MapAddressListStore',{'id':'mapAddressListStore'});
		this.callParent();
	}
});

/**
 * 隐藏页面中的翻页，上一页，下一页
 */
Ext.define('OperBtnPanelTest',{
	extend:'BasicHboxCenterPanel', 
	items:null,
	region:'south',
	initComponent:function(){
		this.items = this.getItems();  
		this.callParent();
	},
	getItems:function(){
		return [{   
			id:'pre',
	 	    xtype:'button',
	 	    text: i18n('i18n.fiveKilometreMap.pre')
		},{
			id:'next',
	 	   	xtype:'button',
	 	   	text: i18n('i18n.fiveKilometreMap.next')
		}]
	}
});

/**
 * 隐藏地图地址布局panel
 */
Ext.define('MapListPanel',{
	extend:'BasicPanel',
	region : 'east',
	split:true,
	collapsible : true,//允许展开和收缩  
	width:250,
	layout:'border',
	items:[{
		xtype:'basicpanel',
		layout:'fit',
		region:'center',
		items:[Ext.create('MapListGrid',{'id':'mGrid'})]
		},Ext.create('OperBtnPanelTest')
	]
});

/**
 *地图中间布局，包含地图区域和列表区域
 */
Ext.define('MapPanel',{
	extend : 'BasicPanel',
	region : 'center',
	layout:'border',
	items:[Ext.create('MapForm',{'id':'mapForm'}),Ext.create('MapGrid',{'id':'mapGrid','store':Ext.create('MapCustomerGridStore',{'id':'mapCustomerGridStore'})})]
});


/**
 * 整体布局panel
 */
Ext.define('MapView', {
	extend : 'BasicPanel',
	layout : 'border',
	items : [Ext.create('MapCustomerForm',{'id':'mapCustomerForm'}),Ext.create('MapPanel',{'id':'mapPanel'}),Ext.create('MapListPanel',{'id':'showAddress'})]
});

/**
 *将界面显示到viewport中 
 */
var viewport=Ext.create('Ext.Viewport',{
	layout : 'border',
	autoScroll:true,
	items:[Ext.create('MapView',{region:'center','id':'mapView'})]
});
viewport.setAutoScroll(false);
viewport.doLayout();


//默认收缩面板
Ext.getCmp("showAddress").collapse();
//初始化默认部门
var deptModel=new DeptModel();
deptModel.set("id",User.deptId);
deptModel.set("deptName",User.deptName);
Ext.getCmp("mapCustomerForm").getForm().findField("deptId").store.add(deptModel);
Ext.getCmp("mapCustomerForm").getForm().findField("deptId").setValue(User.deptId);

/*=============制定开发计划弹出面板===================*/
	/**
  	 * 移动按钮
  	 */
  	Ext.define('ButtonPanelRole',{
  		extend :'PopRightButtonPanel',
  		width : '100%',
  		buttonAlign : 'center',
  		layout : 'column',
  		items : [{
  			columnWidth : 1,
  			xtype : 'container',
  			style : 'padding-top:60px;border:none',
  			width : '100%',
  			items : [{
  				xtype : 'button',
  				text : '&gt;',
  				width : 39,
  				// 添加所选客户
  				handler : function(){
  					//得到所选客户
  					var selection=Ext.getCmp("searchResultGridId").getSelectionModel().getSelection();
  					//已选择客户store
  					var chooseStore=Ext.getCmp("ChooseCustomerGridId").store;
  					for(var i=0;i<selection.length;i++){//遍历所选客户
    				    	if(!Ext.isEmpty(chooseStore.getById(selection[i].get("id")))){//判断是否有重复
    						    MessageUtil.showInfoMes(selection[i].get("custName")+i18n('i18n.developPlan.exist'));
    						    return false;
    					    }else{
    					    	Ext.getCmp("searchResultGridId").getSelectionModel().deselect(selection[i]);
    					    	Ext.getCmp("searchResultGridId").store.remove(selection[i]);
    					    	//添加到已选择客户store里
    					    	chooseStore.insert(i,selection[i]);
    					    }
  					}
  				}
  			}]
  		},{
  			columnWidth : 1,
  			width : '100%',
  			xtype : 'container',
  			style : 'padding-top:60px;border:none',
  			items : [{
  				xtype : 'button',
  				text : '&lt;',
  				width : 39,
  				//移除所选客户
  				handler : function(){
  					//得到已选客户
  					var selection=Ext.getCmp("ChooseCustomerGridId").getSelectionModel().getSelection();
  					//待选择客户store
  					var store=Ext.getCmp("searchResultGridId").store;
  					for(var j=0;j<selection.length;j++){//遍历所选客户
  						if(!Ext.isEmpty(store.getById(selection[j].get("id")))){//判断是否有重复
						    Ext.getCmp("ChooseCustomerGridId").getSelectionModel().deselect(selection[j]);
	  						Ext.getCmp("ChooseCustomerGridId").store.remove(selection[j]);
					    }else{
					    	Ext.getCmp("ChooseCustomerGridId").getSelectionModel().deselect(selection[j]);
	  						Ext.getCmp("ChooseCustomerGridId").store.remove(selection[j]);
	  						//添加到已选择客户store里
	  						store.insert(j,selection[j]);
					    }
  					}
  					if(Ext.getCmp("ChooseCustomerGridId").store.getCount()==0){
  						Ext.getCmp("ChooseCustomerGridId").getSelectionModel().deselectAll();
  					}
  				}
  			}]
  		}]
  	});

  	/**
  	 * 制定开发计划按钮
  	 */
  	Ext.define('RightDevelopDownButtonPanel',{
  		extend:'PopButtonPanel', 
  		items:null,
  		region:'south',
  		width:500,
  		initComponent:function(){
  			this.items = this.getItems();
  			this.callParent();
  		},
  		getItems:function(){
  			var me = this;
  			return [{
  				xtype:'middlebuttonpanel' 
  			},{
  				xtype:'poprightbuttonpanel',  
  				width : 500,
  				items : [{
  					xtype : 'button',
  					text : i18n('i18n.developPlan.savePlan'),
  					width : 115,
  					scope:me.searchCondition,
  					handler : function(){
  						var store=Ext.getCmp('ChooseCustomerGridId').store;
  						if(store.getCount()!=0){
  							var custList=new Array();
  							for(var i=0;i<store.getCount();i++){//获取已经选择的客户id
  								custList[i]=store.getAt(i).data.id;
  							}
  						}
  						var successFn = function(json){
  				    		MessageUtil.showInfoMes(i18n('i18n.developPlan.saveDevelopPlanSuccess'));
  				    		//清除已选表格的数据
							Ext.getCmp('ChooseCustomerGridId').store.removeAll();
  				    		deliveryDevelopPopWindow.hide();
  				    	};
  				    	var failureFn = function(json){
  				    		MessageUtil.showErrorMes(json.message);
  				    	};
  				    	var planFromPanel = Ext.getCmp("developPlanFromPanel").getForm();
  				    	var deptId=Ext.getCmp('mapCustomerForm').getForm().findField('deptId').getValue();
  				    	var param = {
  				    		'developPlan.beginTime':planFromPanel.findField('beginTime').getValue(),//开始时间
  		    			    'developPlan.endTime':planFromPanel.findField('endTime').getValue(),//结束时间
  		    			    'developPlan.topic':planFromPanel.findField('topic').getValue(),
  		    			    'developPlan.execdeptid':deptId,//执行部门
  		    			    'developPlan.activedesc':planFromPanel.findField('activedesc').getValue(),
  		    			    'developPlan.execuserid':planFromPanel.findField('execuserid').getValue(),//执行人员
  		    			    'developPlan.id':planFromPanel.findField('id').getValue(),
  		    			    'developPlan.planType':DEVELOP_TYPE,
  		    			    'custList':custList
  		    			};
	  					CustomerDevelopPlaneData.prototype.savePlan(param, successFn, failureFn);
  					}
  				},{
  					xtype : 'button',
  					text : i18n('i18n.developPlan.reset'),
  					width : 70,
  					handler : function(){
  						Ext.getCmp("developPlanFromPanel").getForm().reset();
  					}
  				},{
  					xtype : 'button',
  					text : i18n('i18n.DevelopManageView.close'),
  					width : 70,
  					handler : function(){
  						deliveryDevelopPopWindow.hide();
  					}
  				}]
  			}];
  		}
  	});


  	/**.
  	 * <p>
  	 * 开发计划新增、修改主Panel
  	 * <p>
  	 * @author 张登
  	 * @时间 2012-3-26
  	 */
  	Ext.define('CustomerDevelopPlanPanel',{
  		extend:'BasicPanel',
  		searchLeftResult:null, //查询客户列表（左边Grid）
  		searchRightResult:null, //已选择客户列表（右边Grid）
  		downPlanformPanel:null,//底部开发计划formPanel
  		items:null,
  		layout:'border',
  		initComponent:function(){
  			var me = this;
//  			//查询客户列表store
  			var store=Ext.create('DeliverySearchConditionStore',{id:'deliverySearchConditionStore'});
  			store.on('beforeload',function(th,operation,e){
				var searchParams = {
					'searchCustomerCondition.custIds':custInfo,
					'searchCustomerCondition.type':'2'
				};
				Ext.apply(operation,{
					params : searchParams
				});	
			});
  			var selModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SIMPLE',id:'selModelId'});
  			//查询结果Grid
  			me.searchLeftResult =  Ext.create('PopupGridPanel',{
  				id:'searchResultGridId',
  				store:store,
  				columns:me.getColumns(),
  				selModel:selModel,
  			    listeners: {
  			    	scrollershow: function(scroller) {
  			    		if (scroller && scroller.scrollEl) {
  			    				scroller.clearManagedListeners(); 
  			    				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
  			    		}
  			    	}
  			    },
  				viewConfig:{//可拖动插件
  					forceFit:true
  				},
  				dockedItems:[{
  					xtype:'pagingtoolbar',
  					store:store,
  					dock:'bottom',
  					cls:'pagingtoolbar',
  					displayInfo : true,
  					autoScroll : true,
  					items:[{
  						text: '每页',
  						xtype: 'tbtext'
  					},Ext.create('Ext.form.ComboBox', {
  		               width:          window.screen.availWidth*0.0391,
  		               value:          '10',
  		               triggerAction:  'all',
  		               forceSelection: true,
  		               editable:       false,
  		               name:           'comboItem',
  		               displayField:   'value',
  		               valueField:     'value',
  		               queryMode:      'local',
  		               store : Ext.create('Ext.data.Store',{
  		                   fields : ['value'],
  		                   data   : [
  		                       {'value':'10'},
  		                       {'value':'15'},
  		                       {'value':'20'},
  		                       {'value':'25'},
  		                       {'value':'40'},
  		                       {'value':'100'}
  		                   ]
  		               }),
  		               listeners:{
  							select : {
  					            fn: function(_field,_value){
  					            	var pageSize = store.pageSize;
  					            	var newPageSize = parseInt(_field.value);
  					            	if(pageSize!=newPageSize){
  					            		store.pageSize = newPageSize;
  					            		this.up('pagingtoolbar').moveFirst();
  					            	}
  					            }
  					        }
  		               }
  		           }),{
  						text: '条',
  						xtype: 'tbtext'
  		           }]
  				}]
  			});
  			
  			var chooseSelModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SIMPLE',id:'chooseSelModelId'});
  			//查询已选择客户列表store
  			var chooseStore=Ext.create('DeliverySearchCustbyIdStore',{id:'deliverySearchCustbyIdStore'});
  			
  			var searchRightGrid=Ext.create('PopupGridPanel',{
  				id:'ChooseCustomerGridId',
  				store:chooseStore,
  			    listeners: {
  			    	scrollershow: function(scroller) {
  			    		if (scroller && scroller.scrollEl) {
  			    				scroller.clearManagedListeners(); 
  			    				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
  			    		}
  			    	}
  			    },
  				columns:me.getColumns(),
  				selModel:chooseSelModel,
  				viewConfig:{//可拖动插件
  					forceFit:true
  				}
  			});
  			
  			me.searchRightResult =  searchRightGrid;
			me.downPlanformPanel=Ext.create('SavePlanPanel',{id:'developPlanFromPanel'});//制定计划
			//保存计划管理加载执行人
			Ext.getCmp("developPlanFromPanel").getForm().findField("execuserid").store.load();//加载执行人DetailPanel
  			//设置items
  			me.items = me.getItems();
  			
  			store.on('load',function(store,records){
  				var girdcount=0;
		        store.each(function(record){
		            if(!Ext.isEmpty(chooseStore.getById(record.get("id")))){
						var cells =  Ext.getCmp('searchResultGridId').getView().getNodes()[girdcount].children;
						for(var i= 0;i<cells.length;i++){
							cells[i].style.backgroundColor='#FF9797';
						};
		 			};	
		 			girdcount=girdcount+1;
		        });
  			});
  			
  			this.callParent();
  		},
  		getItems:function(){//整体布局
  			var me = this;
  			return [{
  					region:'center',
  					xtype:'basicpanel',
  					layout:'border',
  					items:[{
  						region:'center',
  						xtype:'basicpanel',
  						layout:'border',
  						flex:1,
  						items:[{
  								region:'center',
  								xtype:'basicpanel',
  								layout:'fit',
  								items:[me.searchLeftResult]
  							},{
  								region:'east',
  								xtype:'basicpanel',
  								layout:'fit',
  								items:[Ext.create('ButtonPanelRole')]
  							}
  						]
  					},{
  						region:'east',
  						xtype:'basicpanel',
  						layout:'fit',
//  						flex:1,
  						width:220,
  						items:[me.searchRightResult]
  					}]
  				},{
  					region:'south',
  					xtype:'basicpanel',
  					layout:'border',
  					height:130,
  					items:[{
  						region:'center',
  						xtype:'basicpanel',
  						layout:'fit',
  						items:[me.downPlanformPanel]
  					},
  					Ext.create('RightDevelopDownButtonPanel')]//border布局下面制定开发计划按钮
  				}]
  		}
  		,getColumns:function(){//查询结果列
  			return [{
  				header : i18n('i18n.PotentialCustManagerView.customerName'),
  				dataIndex : 'custName'
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.contactName'),
  				dataIndex : 'linkManName'
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.contactPhone'),
  				dataIndex : 'linkManMobile'
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.contactTel'),
  				dataIndex : 'linkManPhone'
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.position'),
  				dataIndex : 'post'
  			},{
  				header : i18n('i18n.PotentialCustManagerView.coopIntention'),
  				dataIndex : 'coopIntention',
				renderer:function(value){
					return DButil.rendererDictionary(value,DataDictionary.COOPERATION_INTENTION);
				}
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.goodsPotential'),
  				dataIndex : 'volumePotential',
				renderer:function(value){
					return DButil.rendererDictionary(value,DataDictionary.CARGO_POTENTIAL);
				}
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.bizStatus'),
  				dataIndex : 'bussinesState',
				renderer:function(value){
					return DButil.rendererDictionary(value,DataDictionary.BUSINESS_STATUS);
				}
  			}, {
  				header : i18n('i18n.developPlan.createStartTime'),
  				dataIndex : 'createDate',
 	            renderer : function(value){
 	            	if(!Ext.isEmpty(value)){
 	            		return DButil.renderDate(value);
 	            	}
	            }
  			}]
  		}
  	});
 	
/**
 * 显示制定、修改计划
 */
Ext.define('DeliveryDevelopPopWindow',{
	extend:'PopWindow',
	alias : 'widget.basicwindow',
	width:820,
	height:500,
	title:i18n('i18n.Cycle.makePlan'),
	modal:true,
	layout:'fit',
	closeAction:'hide',
	items:[Ext.create('CustomerDevelopPlanPanel')],
	listeners:{
		hide:function(){
			document.body.scrollLeft=0;
			document.body.scrollTop=0;
			document.getElementsByTagName("html")[0].style.overflowY="hidden";
			document.getElementsByTagName("html")[0].style.overflowX="hidden";
			viewport.doLayout();
		}
	}
});

var deliveryDevelopPopWindow=Ext.getCmp("deliveryDevelopPopWindow");//获取win
if(!deliveryDevelopPopWindow){
	deliveryDevelopPopWindow=Ext.create('DeliveryDevelopPopWindow',{id:'deliveryDevelopPopWindow'});
}
/*=============制定维护计划弹出面板===================*/
	
/**
 * 移动按钮
 */
Ext.define('ButtonMaintainPanelRole',{
	extend :'PopRightButtonPanel',
	width : '100%',
	buttonAlign : 'center',
	layout : 'column',
	items : [{
		columnWidth : 1,
		xtype : 'container',
		style : 'padding-top:60px;border:none',
		width : '100%',
		items : [{
			xtype : 'button',
			text : '&gt;',
			width : 39,
			// 添加所选客户
			handler : function(){
				//得到所选客户
				var selection=Ext.getCmp("searchMaintainResultGridId").getSelectionModel().getSelection();
				//已选择客户store
				var chooseStore=Ext.getCmp("maintainChooseCustomerGridId").store;
				for(var i=0;i<selection.length;i++){//遍历所选客户
				    	if(chooseStore.find("contactId",selection[i].get("contactId"))!=-1){//判断是否有重复
					    MessageUtil.showInfoMes(selection[i].get("linkManName")+i18n('i18n.developPlan.exist'));
					    return false;
				    }else{
				    	Ext.getCmp("searchMaintainResultGridId").getSelectionModel().deselect(selection[i]);
				    	Ext.getCmp("searchMaintainResultGridId").store.remove(selection[i]);
				    	//添加到已选择客户store里
				    	chooseStore.add(selection[i]);
				    }
				}
			}
		}]
	},{
		columnWidth : 1,
		width : '100%',
		xtype : 'container',
		style : 'padding-top:60px;border:none',
		items : [{
			xtype : 'button',
			text : '&lt;',
			width : 39,
			//移除所选客户
			handler : function(){
				//得到已选客户
				var selection=Ext.getCmp("maintainChooseCustomerGridId").getSelectionModel().getSelection();
				//待选择客户store
				var store=Ext.getCmp("searchMaintainResultGridId").store;
				for(var j=0;j<selection.length;j++){//遍历所选客户
					if(store.find("contactId",selection[j].get("contactId"))!=-1){//判断是否有重复
				    Ext.getCmp("maintainChooseCustomerGridId").getSelectionModel().deselect(selection[j]);
					Ext.getCmp("maintainChooseCustomerGridId").store.remove(selection[j]);
			    }else{
			    	Ext.getCmp("maintainChooseCustomerGridId").getSelectionModel().deselect(selection[j]);
					Ext.getCmp("maintainChooseCustomerGridId").store.remove(selection[j]);
					//添加到已选择客户store里
					store.insert(j,selection[j]);
			    }
				}
				if(Ext.getCmp("maintainChooseCustomerGridId").store.getCount()==0){
					Ext.getCmp("maintainChooseCustomerGridId").getSelectionModel().deselectAll();
				}
			}
		}]
	}]
});
Ext.define('RightMaintainDownButtonPanel',{
	extend:'PopButtonPanel', 
	items:null,
	region:'south',
	width:500,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'middlebuttonpanel' 
		},{
			xtype:'poprightbuttonpanel', 
			width:500,
			items : [{
				xtype : 'button',
				text : i18n('i18n.developPlan.savePlan'),
				width:115,
				scope:me.searchCondition,
				handler : function(t){
				//判断界面校验是否通过
					if(!Ext.getCmp('planFromPanel').getForm().isValid()){
//							MessageUtil.showInfoMes(i18n('i18n.DevelopManageView.pleaseCondition'));
						return false;
					}
					var store=Ext.getCmp('maintainChooseCustomerGridId').store;
					var custList=new Array();
					var contactIds=new Array();
					if(store.getCount()!=0){
						for(var i=0;i<store.getCount();i++){//获取已经选择的客户id
							custList[i]=store.getAt(i).data.memberId;
							contactIds[i]=store.getAt(i).data.contactId;
						}
					}
					var successFn = function(json){
			    		Ext.getCmp('maintainChooseCustomerGridId').store.removeAll();
			    		t.enable();
			    		deliveryMainTainPopWindow.hide();
			    		MessageUtil.showInfoMes(i18n('i18n.developPlan.saveDevelopPlanSuccess'));
			    	};
			    	var failureFn = function(json){
			    		t.enable();
			    		MessageUtil.showErrorMes(json.message);
			    	};
			    	var planFromPanel = Ext.getCmp("planFromPanel").getForm();
			    	var deptId=Ext.getCmp('mapCustomerForm').getForm().findField('deptId').getValue();
			    	var param = {
			    		'developPlan.beginTime':planFromPanel.findField('beginTime').getValue(),//开始时间
	    			    'developPlan.endTime':planFromPanel.findField('endTime').getValue(),//结束时间
	    			    'developPlan.topic':planFromPanel.findField('topic').getValue(),
	    			    'developPlan.execdeptid':deptId,//执行部门
	    			    'developPlan.activedesc':planFromPanel.findField('activedesc').getValue(),
	    			    'developPlan.execuserid':planFromPanel.findField('execuserid').getValue(),//执行人员
	    			    'developPlan.id':planFromPanel.findField('id').getValue(),
	    			    'developPlan.planType':MAINTAIN_TYPE,
	    			    'custList':custList,
	    			    'contactIds':contactIds
	    			};
			    	t.disable();
			    	CustomerDevelopPlaneData.prototype.savePlan(param, successFn, failureFn);
				}
			},{
				xtype : 'button',
				text : i18n('i18n.developPlan.reset'),
				width : 70,
				handler : function(){
					Ext.getCmp("planFromPanel").getForm().reset();
				}
			},{
				xtype : 'button',
				text : i18n('i18n.DevelopManageView.close'),
				width : 70,
				handler : function(){
					deliveryMainTainPopWindow.hide();
				}
			}]
		}];
	}
});
  
Ext.define('MaintainSavePlanPanel',{
	extend:'TitleFormPanel',  
	items:null,
	initComponent:function(){
		this.items = this.getItems(); 
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'basicfiledset', 
			title:i18n('i18n.plan.gridPaneltitle'),
			layout:{
				type:'table',
				columns:4
			},
			defaultType:'textfield',
			defaults:{
				labelWidth : 65,
		        labelAlign: 'right',
				width : 190
			},
			items:[{
                name: 'beginTime',
                xtype     : 'datefield',
                width:195,
                fieldLabel: i18n('i18n.Maintainp.startEnd'),
                editable:       false,
                format: 'Y-m-d',
                value:Ext.Date.add(new Date(), Ext.Date.DAY, 1),
                allowBlank: false
            }, {
                width:140,
                xtype     : 'datefield',
                name: 'endTime',
                fieldLabel: i18n('i18n.CommonView.to'),
                editable:       false,
                labelWidth: 10,
                format: 'Y-m-d',
                value:Ext.Date.add(new Date(), Ext.Date.DAY, 1),
                allowBlank: false
            },{
                width:210,
                name : 'execdeptid',
                fieldLabel: i18n('i18n.developPlan.executeDept'),
                disabled:true,
                allowBlank: false
            },{
            	width:210,
                xtype:          'combo',
              	fieldLabel: i18n('i18n.developPlan.executePersion'),
                name:'execuserid',
                store:Ext.create('UserStore'),
    			queryMode: 'local',
    			triggerAction : 'all',
    			displayField:'empName',
    			valueField:'id',
    			//剔出朦层
    			listConfig: {
      		        loadMask:false
    			},
    			forceSelection :true,
    			listeners:{
    				change:DButil.comboSelsct
    			},
                allowBlank: false
            },{
                width:337,
                name : 'topic',
                fieldLabel: i18n('i18n.Maintainp.topic'),
                maxLength :30,
				maxLengthText : i18n('i18n.developPlan.maxLength')+30,
                colspan : 2,
                allowBlank: false
            },{
                width:430,
                name : 'activedesc',
                maxLength :20,
				maxLengthText : i18n('i18n.developPlan.maxLength')+20,
                fieldLabel: i18n('i18n.Maintainp.planDesc'),
                colspan : 2
            },{
            	xtype:'hiddenfield',
            	name:'id'
            }]		
		}];
	}
});


/**.
 * <p>
 * 开发计划新增、修改主Panel
 * <p>
 * @author 张登
 * @时间 2012-3-26
 */
Ext.define('CustomerMaintainPlanPanel',{
	extend:'BasicPanel',
	searchLeftResult:null, //查询客户列表（左边Grid）
	searchRightResult:null, //已选择客户列表（右边Grid）
	downPlanformPanel:null,//底部开发计划formPanel
	items:null,
	layout:'border',
	initComponent:function(){
		var me = this;
//  			//查询客户列表store
		var store=Ext.create('DeliverySearchConditionStore',{id:'maintainSearchConditionStore'});
		store.on('beforeload',function(th,operation,e){
			var searchParams = {
				'searchCustomerCondition.contactIds':custInfo,
				'searchCustomerCondition.type':'3'
			};
			Ext.apply(operation,{
				params : searchParams
			});	
		});
		var selModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SIMPLE'});
		//查询结果Grid
		me.searchLeftResult =  Ext.create('PopupGridPanel',{
			id:'searchMaintainResultGridId',
			store:store,
			columns:me.getColumns(),
			selModel:selModel,
			viewConfig:{//可拖动插件
				forceFit:true
			},
		    listeners: {
		    	scrollershow: function(scroller) {
		    		if (scroller && scroller.scrollEl) {
		    				scroller.clearManagedListeners(); 
		    				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
		    		}
		    	}
		    },
			dockedItems:[{
				xtype:'pagingtoolbar',
				store:store,
				dock:'bottom',
				cls:'pagingtoolbar',
				displayInfo : true,
				autoScroll : true,
				items:[{
					text: '每页',
					xtype: 'tbtext'
				},Ext.create('Ext.form.ComboBox', {
	               width:          window.screen.availWidth*0.0391,
	               value:          '10',
	               triggerAction:  'all',
	               forceSelection: true,
	               editable:       false,
	               name:           'comboItem',
	               displayField:   'value',
	               valueField:     'value',
	               queryMode:      'local',
	               store : Ext.create('Ext.data.Store',{
	                   fields : ['value'],
	                   data   : [
	                       {'value':'10'},
	                       {'value':'15'},
	                       {'value':'20'},
	                       {'value':'25'},
	                       {'value':'40'},
	                       {'value':'100'}
	                   ]
	               }),
	               listeners:{
						select : {
				            fn: function(_field,_value){
				            	var pageSize = store.pageSize;
				            	var newPageSize = parseInt(_field.value);
				            	if(pageSize!=newPageSize){
				            		store.pageSize = newPageSize;
				            		this.up('pagingtoolbar').moveFirst();
				            	}
				            }
				        }
	               }
	           }),{
					text: '条',
					xtype: 'tbtext'
	           }]
			}]
		});
		
		var chooseSelModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SIMPLE'});
		//查询已选择客户列表store
		var chooseStore=Ext.create('DeliverySearchCustbyIdStore',{id:'maintainSearchCustbyIdStore'});
		
		var searchRightGrid=Ext.create('PopupGridPanel',{
			id:'maintainChooseCustomerGridId',
			store:chooseStore,
		    listeners: {
		    	scrollershow: function(scroller) {
		    		if (scroller && scroller.scrollEl) {
		    				scroller.clearManagedListeners(); 
		    				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
		    		}
		    	}
		    },
			columns:me.getColumns(),
			selModel:chooseSelModel,
			viewConfig:{//可拖动插件
				forceFit:true
			}
		});
		
		me.searchRightResult =  searchRightGrid;
		me.downPlanformPanel=Ext.create('MaintainSavePlanPanel',{id:'planFromPanel'});//制定计划
		
		//保存计划管理加载执行人
		Ext.getCmp("planFromPanel").getForm().findField("execuserid").store.load();//加载执行人DetailPanel
		//设置items
		me.items = me.getItems();
		
		store.on('load',function(store,records){
			var girdcount=0;
	        store.each(function(record){
	            if(chooseStore.find("contactId",record.get("contactId"))!=-1){
					var cells =  Ext.getCmp('searchMaintainResultGridId').getView().getNodes()[girdcount].children;
					for(var i= 0;i<cells.length;i++){
						cells[i].style.backgroundColor='#FF9797';
					};
	 			};	
	 			girdcount=girdcount+1;
	        });
		});
		
		
		this.callParent();
		
		//打开管理页面加载本部门的用户
//			Ext.getCmp("customerDevelopPlaneForm").getForm().findField("execuserid").store.load();//加载执行人DetailPanel;
	},
	getItems:function(){//整体布局
		var me = this;
		return [{
				region:'center',
				xtype:'basicpanel',
				layout:'border',
				items:[{
					region:'center',
					xtype:'basicpanel',
					layout:'border',
					flex:1,
					items:[{
							region:'center',
							xtype:'basicpanel',
							layout:'fit',
							items:[me.searchLeftResult]
						},{
							region:'east',
							xtype:'basicpanel',
							layout:'fit',
							items:[Ext.create('ButtonMaintainPanelRole')]
						}
					]
				},{
					region:'east',
					xtype:'basicpanel',
					layout:'fit',
//					flex:1,
					width:220,
					items:[me.searchRightResult]
				}]
			},{
				region:'south',
				xtype:'basicpanel',
				layout:'border',
				height:130,
				items:[{
					region:'center',
					xtype:'basicpanel',
					layout:'fit',
					items:[me.downPlanformPanel]
				},
				Ext.create('RightMaintainDownButtonPanel')]//border布局下面制定开发计划按钮
			}]
	}
	,getColumns:function(){//查询结果列
		return [{
  				header : i18n('i18n.PotentialCustManagerView.customerName'),
  				dataIndex : 'custName'
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.contactName'),
  				dataIndex : 'linkManName'
  			}, {
  				header : i18n('i18n.ArrivalCycleView.memberLevel'),
  				dataIndex : 'memberLevel',
  				renderer:function(value){
  					return rendererDictionary(value,DataDictionary.MEMBER_GRADE);
  				}
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.contactPhone'),
  				dataIndex : 'linkManMobile'
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.contactTel'),
  				dataIndex : 'linkManPhone'
  			}, /*{
  				header : i18n('i18n.ArrivalCycleView.planName'),
  				dataIndex : 'unfinishedPlanName'
  			},*/ {
  				header : i18n('i18n.MonitorPlan.noExecute'),
  				dataIndex : 'unfinishedPlanNum'
  			},{
  				header : i18n('i18n.ArrivalCycleView.maintainMan'),
  				dataIndex : 'prehuMan'
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.reviTimes'),
  				dataIndex : 'visitNum'
  			}, {
  				header : i18n('i18n.PotentialCustManagerView.finalreviTime'),
  				dataIndex : 'lastVistiTime',
 	            renderer : function(value){
 	            	if(!Ext.isEmpty(value)){
 	            		return DButil.renderDate(value);
 	            	}
	            }
  			}]
	}
});
  		  
/**
 * 显示制定、修改计划
 */
Ext.define('DeliveryMainTainPopWindow',{
	extend:'PopWindow',
	alias : 'widget.basicwindow',
	cls:'popup',
	width:820,
	height:500,
	modal:true,
	layout:'fit',
	title:i18n('i18n.Cycle.makePlan'),
	closeAction:'hide',
	items:[Ext.create('CustomerMaintainPlanPanel')],
	listeners:{
		hide:function(){
			document.body.scrollLeft=0;
			document.body.scrollTop=0;
			document.getElementsByTagName("html")[0].style.overflowY="hidden";
			document.getElementsByTagName("html")[0].style.overflowX="hidden";
			viewport.doLayout();
		}
	}
});

var deliveryMainTainPopWindow=Ext.getCmp("deliveryMainTainPopWindow");//获取win
if(!deliveryMainTainPopWindow){
	deliveryMainTainPopWindow=Ext.create('DeliveryMainTainPopWindow',{id:'deliveryMainTainPopWindow'});
}

});