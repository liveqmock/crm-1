MakePlanView = function(){
	
};
//加载数据字典
var keys = [
//地图标记
	'PLAN_TYPE'
];
//初始化数据字典
initDataDictionary(keys);
/**
 * 单击“制定计划”按钮，弹出的制定计划页面
 */
/**---------------------------------单击“制定计划”按钮，制定计划需要使用的model----------------------------------------------------------------*/
Ext.define('SearchConditionModel', {
	extend : 'Ext.data.Model',
	fields : [
	// id
	{
		name : 'id'
	},
	// 客户名称
	{
		name : 'custName'
	},
	// 联系人姓名
	{
		name : 'linkManName'
	},
	// 联系人手机
	{
		name : 'linkManMobile'
	},
	// 联系人电话
	{
		name : 'linkManPhone'
	},
	// 联系人职位
	{
		name : 'post'
	},
	// 客户属性
	{
		name : 'custProperty'
	},
	// 合作意向
	{
		name : 'coopIntention'
	},
	// 商机状态
	{
		name : 'bussinesState'
	},
	// 客户来源
	{
		name : 'custbase'
	},
	// 货量潜力
	{
		name : 'volumePotential'
	}, {
		name : 'custId'// 客户Id
	},
	// 客户类型
	{
		name : 'custType'
	},
	// 行业
	{
		name : 'trade'
	},
	// 开发状态
	{
		name : 'developState'
	},
	{
		name : 'lastVistiTime',
		type : 'Date',
		defaultValue : null,
		convert : DButil.changeLongToDate
	},
	// 回访次数
	{
		name : 'visitNum'
	},
	// 日程时间
	{
		name : 'programmeTime',
		type : 'Date',
		defaultValue : null,
		convert : DButil.changeLongToDate
	},
	// 创建时间
	{
		name : 'createDate',
		type : 'Date',
		defaultValue : null,
		convert : DButil.changeLongToDate
	},{
		name : 'beginTime',
		type : 'Date',
		defaultValue : null,
		convert : DButil.changeLongToDate
	}, {
		name : 'overTime',
		type : 'Date',
		defaultValue : null,
		convert : DButil.changeLongToDate
	},
	// 发货量
	{
		name : 'beginShipWeight'
	}, {
		name : 'overShipWeight'
	},
	// 发货票数
	{
		name : 'beginShipVotes',
		type : 'int'
	}, {
		name : 'overShipVotes',
		type : 'int'
	},
	// 发货金额
	{
		name : 'beginShipAmount'
	}, {
		name : 'overShipAmount'
	},
	// 到达货量
	{
		name : 'beginArrivalWeight'
	}, {
		name : 'overArrivalWeight'
	},
	// 到达票数
	{
		name : 'beginArrivalVotes',
		type : 'int'
	}, {
		name : 'overArrivalVotes',
		type : 'int'
	},
	// 到达金额
	{
		name : 'beginArrivalAmount'
	}, {
		name : 'overArrivalAmount'
	} ,{
		name : 'memberLevel'//会员等级
	},{
		name : 'memberId'//会员Id
	},{
		name : 'unfinishedPlanNum'//未完成计划数量
	},{
		name : 'prehuMan'//维护人
	},{
		name : 'contactId'//联系人Id
	},{
		// 未完成计划名称
		name : 'unfinishedPlanName',
		type : 'string'
	},{
		// 未完成计划名称(维护日程新增用-->后台封装到改字段了)
		name : 'ftopic',
		type : 'string'
	},{
		name : 'memberNo'// 会员编码
	},{
		name : 'idNumber'// 身份证号
	},{
		name : 'companySize'// 公司规模
	},{
		name : 'companyNature'// 公司性质
	},{
		name : 'taxregistNo'// 税务登记号
	},{
		name : 'cityName'//城市名称
	},{
		name : 'address'// 地址
	},{
		name : 'leaDeptName'// 到达部门名称
	},{
		name : 'arrDeptName'// 到达部门名称
	},{
		name : 'remark'// 备注
	},{
		name : 'recentcoop'// 最近合作物流公司
	},{
		name : 'recentGoods'// 走货情况(最近合作物流公司)
	},{
		name : 'custNeed'// 客户需求
	},{
		name : 'marketRemark'// 营销备注
	}]
});
/**---------------------------------单击“制定计划”按钮，制定计划需要使用的store----------------------------------------------------------------*/
Ext.define('DeliverySearchCustbyIdStore',{
	extend:'Ext.data.Store',
	model:'SearchConditionModel'
});

/**--------------------- 返回可以制定计划的客户信息列表和不能制定计划的客户数量----------------------------------------------------------*/
MakePlanView.prototype.searchCustListToCreatePlan = function(param,successFn,failureFn){
	var url = '../marketing/searchCustListToCreatePlan.action';
	DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**---------------------------------单击“制定计划”按钮，制定计划需要使用的store----------------------------------------------------------------*/
Ext.define('DeliverySearchConditionStore',{
	extend:'Ext.data.Store',
	model:'SearchConditionModel',
	pageSize:10,
	proxy:{
		type:'ajax',
		url:'../marketing/searchDevelopPopDataList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'customerInfoList',
			totalProperty : 'totalCount'
		}
	}
});

/**---------------------------------单击“制定计划”按钮，制定计划需要使用的“计划信息”PANEL----------------------------------------------------------------*/
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
                editable:false,
                format: 'Y-m-d',
                minValue:Ext.Date.add(new Date(), Ext.Date.DAY, 1),
                value:Ext.Date.add(new Date(), Ext.Date.DAY, 1),
                allowBlank: false
            }, {
                width:140,
                xtype: 'datefield',
                name: 'endTime',
                minValue:Ext.Date.add(new Date(), Ext.Date.DAY, 1),
                fieldLabel: i18n('i18n.CommonView.to'),
                editable:false,
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
                xtype:'combo',
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
                maxLength :20,
				maxLengthText : i18n('i18n.developPlan.maxLength')+20,
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

/**---------------------------------单击“制定计划”按钮，制定计划需要使用的“移动按钮”----------------------------------------------------------------*/
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
/**---------------------------------单击“制定计划”按钮，制定计划需要使用的“按钮”面板，包括制定计划、重置、关闭三个按钮----------------------------------------------------------------*/
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
			    	var planFromPanel = Ext.getCmp("planFromPanel").getForm();
					var store=Ext.getCmp('maintainChooseCustomerGridId').store;
					var custList=new Array();
					var contactIds=new Array();
					if(store.getCount()===0){
						MessageUtil.showErrorMes(i18n('i18n.visualMarket.selectCustToMakePlan'));
						return false;
					}
					
					//判断界面校验是否通过
					if(!Ext.getCmp('planFromPanel').getForm().isValid()){
						return false;
					}
					
					var beginTime = planFromPanel.findField('beginTime').getValue();//开始时间
    			    var endTime = planFromPanel.findField('endTime').getValue();//开始时间
					if(DButil.compareTwoDate(beginTime,endTime) <= 0){
						MessageUtil.showErrorMes(i18n('i18n.visualMarket.errorPlanCreateTime'));
						return false;
					}
					
					if(store.getCount()!=0){
						for(var i=0;i<store.getCount();i++){//获取已经选择的客户id
							custList[i]=store.getAt(i).data.memberId;
							contactIds[i]=store.getAt(i).data.contactId;
						}
					}
					var successFn = function(json){
			    		Ext.getCmp('maintainChooseCustomerGridId').store.removeAll();
			    		t.enable();
			    		Ext.getCmp("deliveryMainTainPopWindow").hide();
			    		MessageUtil.showInfoMes(i18n('i18n.developPlan.saveDevelopPlanSuccess'));
			    	};
			    	var failureFn = function(json){
			    		t.enable();
			    		if(Ext.isEmpty(json)){
			    			MessageUtil.showErrorMes(i18n('i18n.visualMarket.exceedTimeOfSavePlan'));
			    		}
			    		else{
			    			MessageUtil.showErrorMes(json.message);
			    		}
			    	};

			    	var deptId=Ext.getCmp('QueryCustomerFormId').getForm().findField('deptName').getValue();
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
					//计划信息panel
			    	var planFromPanel = Ext.getCmp("planFromPanel").getForm();
			    	//执行人清空
			    	planFromPanel.findField('execuserid').reset();
			    	//维护主题清空
			    	planFromPanel.findField('topic').reset();
			    	//维护描述清空
			    	planFromPanel.findField('activedesc').reset();
			    	//维护时限重置
			    	planFromPanel.findField('beginTime').reset();
			    	planFromPanel.findField('endTime').reset();
				}
			},{
				xtype : 'button',
				text : i18n('i18n.DevelopManageView.close'),
				width : 70,
				handler : function(){
					Ext.getCmp("deliveryMainTainPopWindow").hide();
				}
			}]
		}];
	}
});
/**---------------------------------单击“制定计划”按钮，弹出的制定维护计划主panel----------------------------------------------------------------*/
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
				'searchCustomerCondition.custIds':custInfoForMakePlan,
				'searchCustomerCondition.type':'3',
				'searchCustomerCondition.deptId':deptIdForMakePlan
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
				beforePageText:i18n('i18n.visualMarket.page'),
				dock:'bottom',
				cls:'pagingtoolbar',
				displayInfo : true,
				autoScroll : true,
				items:[{
					text: i18n('i18n.authorization.roleGrid.page_count'),
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
					text: i18n('i18n.authorization.roleGrid.number'),
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
		
		//设置items
		me.items = me.getItems();
		
		
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
							items:[Ext.create('ButtonMaintainPanelRole')]
						}
					]
				},{
					region:'east',
					xtype:'basicpanel',
					layout:'fit',
					width:210,
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
				Ext.create('RightMaintainDownButtonPanel')
				]//border布局下面制定开发计划按钮
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
  			}, {
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

/**---------------------------------单击“制定计划”按钮，弹出的制定维护计划window----------------------------------------------------------------*/
Ext.define('DeliveryMainTainPopWindow',{
	extend:'PopWindow',
	alias : 'widget.basicwindow',
	cls:'popup',
	width:820,
	height:500,
	modal:true,
	layout:'fit',
	title:i18n('i18n.visualMarket.makeMaintainPlan'),
	closeAction:'hide',
	items:[Ext.create('CustomerMaintainPlanPanel')],
	listeners:{
		hide:function(){
			document.body.scrollLeft=0;
			document.body.scrollTop=0;
			document.getElementsByTagName("html")[0].style.overflowY="hidden";
			document.getElementsByTagName("html")[0].style.overflowX="hidden";
		}
	}
});

/**---------------------------------单击“制定计划”按钮，弹出的制定开发计划的计划信息panel----------------------------------------------------------------*/
Ext.define('SavePlanPanel',{
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
				width : 200
			},
			items:[{
                name: 'beginTime',
                xtype: 'datefield',
                width:195,
                fieldLabel: i18n('i18n.plan.beginTime'),
                editable: false,
                minValue :Ext.Date.add(new Date(), Ext.Date.DAY, 1),
                format: 'Y-m-d',
                value:Ext.Date.add(new Date(), Ext.Date.DAY, 1),
                allowBlank: false
            }, {
                width:140,
                xtype: 'datefield',
                name: 'endTime',
                fieldLabel: i18n('i18n.PotentialCustManagerView.searchEndTime'),
                editable:false,
                labelWidth: 10,
                format: 'Y-m-d',
                labelSeparator:'', 
                minValue :Ext.Date.add(new Date(), Ext.Date.DAY, 1),
                value:Ext.Date.add(new Date(), Ext.Date.DAY, 1),
                allowBlank: false
            },{
                width:210,
                name : 'execdeptid',
                fieldLabel: i18n('i18n.developPlan.executeDept'),
                disabled:true,
                allowBlank: false
            },{
            	width:220,
                xtype: 'combo',
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
                width:340,
                name : 'topic',
                fieldLabel: i18n('i18n.developPlan.developTheme'),
                colspan : 2,
                maxLength : 20,
				maxLengthText : i18n('i18n.developPlan.maxLength')+20,
                allowBlank: false
            },{
                width:210,
                name : 'activedesc',
                fieldLabel: i18n('i18n.plan.activedesc'),
                maxLength : 40,
				maxLengthText : i18n('i18n.developPlan.maxLength')+40
            },{
            	xtype: 'combo',
                fieldLabel:i18n('i18n.developSchedule.planeType'),
                name:'projectType',
                id:'projectType',
                store:getDataDictionaryByName(DataDictionary,'PLAN_TYPE'),
                queryMode: 'local',
                displayField:'codeDesc',
                valueField:'code',
                emptyText : '',
                width:220,
                allowBlank: false,
                forceSelection :true,
                listeners:{
                    change:DButil.comboSelsct
                }
            },{
            	xtype:'hiddenfield',
            	name:'id'
            }]		
		}];
	}
});

/**---------------------------------单击“制定计划”按钮，弹出的制定开发计划移动按钮window----------------------------------------------------------------*/
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

/**---------------------------------单击“制定计划”按钮，弹出的制定开发计划按钮panel----------------------------------------------------------------*/
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
					handler : function(t){
				    	var planFromPanel = Ext.getCmp("developPlanFromPanel").getForm();
						var store=Ext.getCmp('ChooseCustomerGridId').store;
						if(store.getCount()===0){
							MessageUtil.showErrorMes(i18n('i18n.visualMarket.selectCustToMakePlan'));
							return false;
						}
						if(!Ext.getCmp('developPlanFromPanel').getForm().isValid()){
							return false;
						}
						var beginTime = planFromPanel.findField('beginTime').getValue();//开始时间
	    			    var endTime = planFromPanel.findField('endTime').getValue();//开始时间
						if(DButil.compareTwoDate(beginTime,endTime) <= 0){
							MessageUtil.showErrorMes(i18n('i18n.visualMarket.errorPlanCreateTime'));
							return false;
						}
						if(store.getCount()!=0){
							var custList=new Array();
							var contactIds = new Array();
							for(var i=0;i<store.getCount();i++){//获取已经选择的客户id
								custList[i]=store.getAt(i).data.memberId;
								contactIds[i]=store.getAt(i).data.contactId;
							}
						}
						var successFn = function(json){
							t.enable();
				    		MessageUtil.showInfoMes(i18n('i18n.developPlan.saveDevelopPlanSuccess'));
				    		//清除已选表格的数据
				    		Ext.getCmp('ChooseCustomerGridId').store.removeAll();
				    		Ext.getCmp("deliveryDevelopPopWindow").hide();
				    	};
				    	var failureFn = function(json){
				    		t.enable();
				    		if(Ext.isEmpty(json)){
				    			MessageUtil.showErrorMes(i18n('i18n.visualMarket.exceedTimeOfSavePlan'));
				    		}
				    		else{
				    			MessageUtil.showErrorMes(json.message);
				    		}
				    	};

				    	var deptId=Ext.getCmp('QueryCustomerFormId').getForm().findField('deptName').getValue();
				    	var param = {
				    		'developPlan.beginTime':planFromPanel.findField('beginTime').getValue(),//开始时间
		    			    'developPlan.endTime':planFromPanel.findField('endTime').getValue(),//结束时间
		    			    'developPlan.topic':planFromPanel.findField('topic').getValue(),
		    			    'developPlan.execdeptid':deptId,//执行部门
		    			    'developPlan.activedesc':planFromPanel.findField('activedesc').getValue(),
		    			    'developPlan.execuserid':planFromPanel.findField('execuserid').getValue(),//执行人员
		    			    'developPlan.id':planFromPanel.findField('id').getValue(),
		    			    'developPlan.projectType':planFromPanel.findField('projectType').getValue(),//计划类别
		    			    'developPlan.planType':DEVELOP_TYPE,
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
//						计划信息panel
						var planFromPanel = Ext.getCmp("developPlanFromPanel").getForm();
						//执行人
						planFromPanel.findField('execuserid').reset();
						//计划主题
						planFromPanel.findField('topic').reset();
						//维护描述
						planFromPanel.findField('activedesc').reset();
						//开发时限重置
						planFromPanel.findField('beginTime').reset();
						planFromPanel.findField('endTime').reset();
						//重置计划类别
						planFromPanel.findField('projectType').reset();
					}
				},{
					xtype : 'button',
					text : i18n('i18n.DevelopManageView.close'),
					width : 70,
					handler : function(){
						Ext.getCmp("deliveryDevelopPopWindow").hide();
					}
				}]
			}];
		}
	});

/**---------------------------------单击“制定计划”按钮，弹出的制定开发计划主panel----------------------------------------------------------------*/
Ext.define('CustomerDevelopPlanPanel',{
		extend:'BasicPanel',
		searchLeftResult:null, //查询客户列表（左边Grid）
		searchRightResult:null, //已选择客户列表（右边Grid）
		downPlanformPanel:null,//底部开发计划formPanel
		items:null,
		layout:'border',
		initComponent:function(){
			var me = this;
//			//查询客户列表store
			var store=Ext.create('DeliverySearchConditionStore',{id:'deliverySearchConditionStore'});
			store.on('beforeload',function(th,operation,e){
				var searchParams = {
					'searchCustomerCondition.custIds':custInfoForMakePlan,
					'searchCustomerCondition.type':'3',
					'searchCustomerCondition.deptId':deptIdForMakePlan
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
				autoScroll:true,
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
					beforePageText:i18n('i18n.visualMarket.page'),
					dock:'bottom',
					cls:'pagingtoolbar',
					displayInfo : true,
					autoScroll : true,
					items:[{
						text: i18n('i18n.authorization.roleGrid.page_count'),
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
						text: i18n('i18n.authorization.roleGrid.number'),
						xtype: 'tbtext'
		           }]
				}]
			});
			
			var chooseSelModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SIMPLE',id:'chooseSelModelId'});
			//查询已选择客户列表store
			var chooseStore=Ext.create('DeliverySearchCustbyIdStore',{id:'deliverySearchCustbyIdStore'});
			
			var searchRightGrid=Ext.create('PopupGridPanel',{
				id:'ChooseCustomerGridId',
				autoScroll:true,
				store:chooseStore,
				columns:me.getColumns(),
				listeners: {
			    	scrollershow: function(scroller) {
			    		if (scroller && scroller.scrollEl) {
			    				scroller.clearManagedListeners(); 
			    				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
			    		}
			    	}
			    },
				selModel:chooseSelModel,
				viewConfig:{//可拖动插件
					forceFit:true
				}
			});
			
			me.searchRightResult =  searchRightGrid;
			me.downPlanformPanel=Ext.create('SavePlanPanel',{id:'developPlanFromPanel'});//制定计划
		//设置items
			me.items = me.getItems();
			
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
								xtype:'container',
								layout:'fit',
								items:[me.searchLeftResult]
							},{
								region:'east',
								xtype:'container',
								layout:'fit',
								items:[Ext.create('ButtonPanelRole')]
							}
						]
					},{
						region:'east',
						xtype:'container',
						layout:'fit',
						width:210,
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
				dataIndex : 'custName',
				width:80
			}, {
				header : i18n('i18n.PotentialCustManagerView.contactName'),
				dataIndex : 'linkManName',
				width:80
			}, {
				header : i18n('i18n.PotentialCustManagerView.contactPhone'),
				dataIndex : 'linkManMobile',
				width:90
			}, {
				header : i18n('i18n.PotentialCustManagerView.contactTel'),
				dataIndex : 'linkManPhone',
				width:80
			}, {
				header : i18n('i18n.PotentialCustManagerView.position'),
				dataIndex : 'post',
				width:80
			},{
				header : i18n('i18n.PotentialCustManagerView.coopIntention'),
				dataIndex : 'coopIntention',
				width:80,
				renderer:function(value){
					return DButil.rendererDictionary(value,DataDictionary.COOPERATION_INTENTION);
				}
			}, {
				header : i18n('i18n.PotentialCustManagerView.goodsPotential'),
				dataIndex : 'volumePotential',
				width:80,
				renderer:function(value){
					return DButil.rendererDictionary(value,DataDictionary.CARGO_POTENTIAL);
				}
			}, {
				header : i18n('i18n.PotentialCustManagerView.bizStatus'),
				dataIndex : 'bussinesState',
				renderer:function(value){
					return DButil.rendererDictionary(value,DataDictionary.BUSINESS_STATUS);
				},
				width:80
			}, {
				header : i18n('i18n.developPlan.createStartTime'),
				dataIndex : 'createDate',
	            renderer : function(value){
	            	if(!Ext.isEmpty(value)){
	            		return DButil.renderDate(value);
	            	}
	            },
				width:80
			}]
		}
	});

/**---------------------------------单击“制定计划”按钮，弹出的制定开发计划window----------------------------------------------------------------*/
Ext.define('DeliveryDevelopPopWindow',{
	extend:'PopWindow',
	alias : 'widget.basicwindow',
	width:820,
	height:500,
	title:i18n('i18n.visualMarket.makeDevelopPlan'),
	modal:true,
	layout:'fit',
	closeAction:'hide',
	items:[Ext.create('CustomerDevelopPlanPanel')]
});