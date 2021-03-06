/**.
 * <p>
 * 监控维护页面
 * <p>
 * @param value
 * @author 张登
 * @时间 2012-3-26
 */
var monitorManageData =  new MonitorManageData();//初始化监控计划data
Ext.onReady(function(){
	var keys=[
		// 计划状态
		'PLAN_STATUS',
		//客户类型
  	    'CUST_TYPE',
  	    //客户属性
		'CUSTOMER_NATURE',
  	    //日程状态
  	    'SCHEDULE_STATUS',
  	    //会员等级
  	    'MEMBER_GRADE',
  	    //计划类别标识零担还是快递 盛诗庆
	    'PLAN_TYPE'
	];
  	//初始化业务参数
  	initDataDictionary(keys);
  	var store=Ext.create('DeptStore');
  	var departId=null;//部门Id
	/**
  	 * 客户开发计划表单
  	 */
  	Ext.define('MonitorPlanForm',{
  		extend:'SearchFormPanel',
  		id:'customerDevelopPlaneForm',//*******修改日期控件布局
  		planeType:null,
  		layout : {
  			type : 'table',
  			columns : 6
  		},
  		items:null,
  		defaults: {
  		    	margin:'4 5 4 5',
  		    	labelWidth:65,
  		    	width : 250,
  		    	labelAlign: 'right'
  		},
  		defaultType:'combobox',
  		getItems:function(){
  			var me = this;
  			return [{
  				fieldLabel:i18n('i18n.developPlan.executeDept'),
  				name:'executeDept',
  				xtype:'combo',
  				store:Ext.create('DeptStore',{
  					listeners:{
  						beforeload:function(store, operation, eOpts){
  							Ext.apply(operation,{
  								params : {
  									'deptName':Ext.getCmp("SearchConditionFormId").getForm().findField("executeDept").getRawValue(),
  									'isMonitor':true
  									}
  								}
  							);	
  						}
  					}
  				}),
  				colspan:2,
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
  					change:DButil.comboSelsct,
  					select:function(th,records){
  						Ext.getCmp("SearchConditionFormId").getForm().findField("execuserid").store.load({params:{executeDept:th.value}});
  					}
  				}
  			},{
  				xtype:'textfield',
  				fieldLabel:i18n('i18n.Maintainp.topic'),
  				id:'topicId',
  				name:'topic',
  				maxLength :50,
				maxLengthText : i18n('i18n.developPlan.maxLength')+50,
  				colspan:2
  			},{
  				fieldLabel:i18n('i18n.MonitorPlanDetail.planStatus'),
  				id:'planStatusId',
  				name:'planStatus',
  				colspan:2,
  				width:250,
  				store:getDataDictionaryByName(DataDictionary,'PLAN_STATUS'),
  				queryMode: 'local',
  				displayField:'codeDesc',
  				valueField:'code',
  				forceSelection :true,
				listeners:{
					change:DButil.comboSelsct
				}
  			},{
  	            xtype:          'combo',
  	          	fieldLabel: i18n('i18n.developPlan.executePersion'),
  	            name:'execuserid',
  				store:Ext.create('UserStore',{id:'queryUserStoreId'}),
  				queryMode: 'local',
  				triggerAction : 'all',
  				displayField:'empName',
  				valueField:'id',
  				colspan:2,
  				//剔出朦层
  				listConfig: {
  	  				  loadMask:false
  				},
  				forceSelection :true,
  				listeners:{
  					change:DButil.comboSelsct
  				}
  	        },{
  				xtype : 'datefield',
  				name:'planStartDate',
  				id:'planStartDate',
  				editable : false,
  				fieldLabel : i18n('i18n.Maintainp.startEnd'),
  				format : 'Y-m-d',
  				colspan:2,
  				allowBlank:false
  			},{
  				xtype : 'datefield',
  				name:'planOverDate',
  				editable : false,
  				id:'planOverDate',
  				fieldLabel : i18n('i18n.CommonView.to'),
  				format : 'Y-m-d',
  				allowBlank:false,
  				colspan:2
  			},/**
  	         * 在计划监控里面添加计划类别查询条件
  	         * auth:盛诗庆
  	         * date:2014-03-25
  	         */
  	        {
  	            fieldLabel: i18n('i18n.returnVisit.planType'),
  	            name : 'projectType',
  	            xtype : 'combo',
  	            store:getDataDictionaryByName(DataDictionary,'PLAN_TYPE'),
  	            queryMode:'local',
  	            displayField:'codeDesc',
  	            valueField:'code',
  	            forceSelection :true,
  	            colspan:2,
  	            listeners:{
  	                change:DButil.comboSelsct
  	            }
  	        }];
  		},
  		initComponent:function()
  		{
  			
  			var me = this;
  			me.items = me.getItems();
  			this.callParent();
  		}
  	});
		  	
  	/**
  	 * 查询按钮
  	 */
  	Ext.define('RightSearchButtonPanel',{
  		extend:'NormalButtonPanel', 
  		items:null,
  		region:'south',
//  		width:400,
  		height:38,
  		initComponent:function(){
  			this.items = this.getItems();
  			this.callParent();
  		},
  		getItems:function(){
  			var me = this;
  			return [{
  				xtype:'middlebuttonpanel' 
  			},{
  				xtype:'rightbuttonpanel',  
  				items : [{
  					xtype : 'button',
  					text : i18n('i18n.PotentialCustManagerView.search'),
  					width : 70,
  					handler : function(){
  						var startDate = Ext.getCmp("SearchConditionFormId").getForm().findField("planStartDate").getValue();
						var endDate = Ext.getCmp("SearchConditionFormId").getForm().findField("planOverDate").getValue();
						if(!Ext.isEmpty(startDate)){
							if(!Ext.isEmpty(endDate)){
								var days = DButil.compareTwoDate(startDate,endDate);
								if(days<0){
									MessageUtil.showInfoMes(i18n('i18n.developPlan.startDateBGEndDate'));
									return;
								}else if(days>90){
									MessageUtil.showInfoMes(i18n('i18n.developPlan.queryDateLimit'));
									return;
								}
								if(Ext.isEmpty(Ext.getCmp("SearchConditionFormId").getForm().findField("executeDept").getValue())){
									var successFn = function(json){
									    if(!Ext.isEmpty(json)){
									    	if(parseInt(json.totalCount)>800){
									    		MessageUtil.showInfoMes(i18n('i18n.MonitorView.canNotExceed'));
									    		return false;
									    	}
									    	//load数据
					  						Ext.getCmp('monitorPlanGridId').store.loadPage(1);   	
									    }
									};
									var failureFn = function(json){
										MessageUtil.showErrorMes(json.message);
									};
									MonitorManageData.prototype.searchDeptCount(null, successFn, failureFn);
								}else{
									//load数据
			  						Ext.getCmp('monitorPlanGridId').store.loadPage(1);
								}
							}else{
								MessageUtil.showInfoMes("开发时限结束日期不能为空~");
								return;
							}
						}else{
							MessageUtil.showInfoMes("开发时限起始日期不能为空~");
							return;
						}
  						
  					}
  				}]
  			}];
  		}
  	});
	  	
  	/**.
  	 * <p>
  	 * 监控计划Panel
  	 * <p>
  	 * @author 张登
  	 * @时间 2012-3-26
  	 */
  	Ext.define('MonitorPlanPanel',{
  		extend:'BasicPanel',
  		searchCondition:null, //查询条件From
  		searchUpResult:null, //查询监控计划列表（上边Grid）
  		searchDownResult:null, //监控计划详细列表（下边Grid）
  		items:null,
  		layout:'border',
  		initComponent:function(){
  			var me = this;
  			
  			//查询条件From
  			me.searchCondition = Ext.create('MonitorPlanForm',{id:'SearchConditionFormId'});
  			
  			//设置起始结束时间
  			var date = new Date();
  			date.setDate(new Date().getDate()-15);
  			Ext.getCmp("SearchConditionFormId").getForm().findField("planStartDate").setValue(date);
  			//date.setDate(new Date().getDate()+15);
  			Ext.getCmp("SearchConditionFormId").getForm().findField("planOverDate").setValue(new Date().getDate()+15);
  			//查询客户列表store
  			var monitorPlanStore=Ext.create('MonitorPlanStore',{id:'monitorPlanStoreId'});
  			monitorPlanStore.on('beforeload',function(store,operation,e){
  				var searchScatterForm = Ext.getCmp("SearchConditionFormId");
  				//设置请求参数
  				var searchParams = { 
  					// 部门ID
  					'monitorPlanQueryCondition.execdeptId':searchScatterForm.getForm().findField('executeDept').getValue(),
  				    // 计划主题
  					'monitorPlanQueryCondition.topic':searchScatterForm.getForm().findField('topic').getValue(),
  					'monitorPlanQueryCondition.planType':MAINTAIN_TYPE,//维护
  					
  				    // 开发状态
  					'monitorPlanQueryCondition.planStatus':searchScatterForm.getForm().findField('planStatus').getValue(),
  				    // 执行人员ID
  					'monitorPlanQueryCondition.execuserId':searchScatterForm.getForm().findField('execuserid').getValue(),
  				    // 开发时间（起始）
  					'monitorPlanQueryCondition.planStartDate':searchScatterForm.getForm().findField('planStartDate').getValue(),
  					'monitorPlanQueryCondition.planOverDate':searchScatterForm.getForm().findField('planOverDate').getValue(),
					//计划类别 标识零担还是快递 盛诗庆
					'monitorPlanQueryCondition.projectType':searchScatterForm.getForm().findField('projectType').getValue()
  				};
  				Ext.apply(operation,{
  					params : searchParams
  				});
  			});
  			
  			//监控计划
  			var monitorPlanSelModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SIMPLE',id:'monitorPlanSelModelId'});
  			var monitorPlanGrid=Ext.create('SearchGridPanel',{
  				id:'monitorPlanGridId',
  				store:monitorPlanStore,
  				columns:me.getMonitorPlanColumns(),
  				selModel:monitorPlanSelModel,
  				viewConfig:{
  					forceFit:true
  				}
  			});
			me.searchUpResult=monitorPlanGrid;
			//添加双击事件
  			monitorPlanGrid.on('itemdblclick',function(th,record){
  				departId=record.get("departId");
  				Ext.getCmp("monitorPlanDetailGridId").store.loadPage(1);
			});
  			
  			//监控计划明细
  			var monitorPlanDetailSelModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SIMPLE',id:'monitorPlanDetailSelModelId'});
  			//查询已选择客户列表store
  			var monitorPlanDetailStore=Ext.create('MonitorPlanDetailStore',{id:'monitorPlanDetailStoreId'});
  			monitorPlanDetailStore.on('beforeload',function(store,operation,e){
  				var searchScatterForm = Ext.getCmp("SearchConditionFormId");
  				//设置请求参数
  				var searchParams = { 
  					// 部门ID
  					'monitorPlanQueryCondition.execdeptId':departId,
  				    // 计划主题
  					'monitorPlanQueryCondition.topic':searchScatterForm.getForm().findField('topic').getValue(),
  				    // 开发状态
  					'monitorPlanQueryCondition.planStatus':searchScatterForm.getForm().findField('planStatus').getValue(),
  					'monitorPlanQueryCondition.planType':MAINTAIN_TYPE,//维护
  				    // 执行人员ID
  					'monitorPlanQueryCondition.execuserId':searchScatterForm.getForm().findField('execuserid').getValue(),
  				    // 开发时间（起始）
  					'monitorPlanQueryCondition.planStartDate':searchScatterForm.getForm().findField('planStartDate').getValue(),
  					'monitorPlanQueryCondition.planOverDate':searchScatterForm.getForm().findField('planOverDate').getValue(),
  					//计划类别 标识零担还是快递 盛诗庆
  					'monitorPlanQueryCondition.projectType':searchScatterForm.getForm().findField('projectType').getValue(),
//  					//计划类型  标识开发计划还是维护计划 盛诗庆
//  					'monitorPlanQueryCondition.planType':searchScatterForm.getForm().findField('planType').getValue(),
  				};
  				Ext.apply(operation,{
  					params : searchParams
  				});
  			});
  		    
  			//查询结果Grid
  			me.searchDownResult =  Ext.create('SearchGridPanel',{
  				id:'monitorPlanDetailGridId',
  				store:monitorPlanDetailStore,
  				title:i18n('i18n.Maintainp.Desc'),
  				columns:me.getMonitorPlanDetailColumns(),
  				selModel:monitorPlanDetailSelModel,
  				viewConfig:{
  					forceFit:true
  				},
  				dockedItems:[{
  					xtype:'pagingtoolbar',
  					store:monitorPlanDetailStore,
  					dock:'bottom',
  					displayInfo : true,
  					autoScroll : true
  				}]
  			});
  			Ext.getCmp("SearchConditionFormId").getForm().findField("execuserid").store.load();//加载执行人DetailPanel
  			//设置items
  			me.items = me.getItems();
  			this.callParent();
  		},
  		getItems:function(){//整体布局
  			var me = this;
  			return [{
  				region:'north',
  				xtype:'basicpanel',
  				height:150,
  				layout:'border',
  				items:[{
  					region:'center',
  					xtype:'basicpanel',
  					layout:'fit',
  					items:[me.searchCondition]
  				},
  				Ext.create('RightSearchButtonPanel')]//border布局下面查询按钮
  			},{
  				region:'center',
  				xtype:'basicpanel',
  				layout:'border',
  				items:[{
  					region:'center',
  					xtype:'basicpanel',
  					layout:'border',
  					items:[{
  						region:'center',
  						xtype:'basicpanel',
  						layout:'fit',
  						flex:1,
  						items:[me.searchUpResult]//监控计划
  					},{
  						region:'south',
  						xtype:'basicpanel',
  						title:'',
  						layout:'fit',
  						flex:1,
  						items:[me.searchDownResult]//监控计划明细
  					}]
  				}]
  			}];
  		},
  		getMonitorPlanColumns:function(){//监控计划列
  			return [{
  				xtype:'rownumberer',
  				width:40,
  				align:'center',
  				header:i18n('i18n.Cycle.rownum')
  			},{
  				header : i18n('i18n.MonitorPlan.departName'),
  				width:260,
  				dataIndex : 'departName'
  			},/* {
  				header : i18n('i18n.MonitorPlan.execusername'),
  				dataIndex : 'execusername'
  			},*/ {
  				header : i18n('i18n.MonitorPlan.completionRate'),
  				dataIndex : 'completionRate'
  			}, {
  				header : i18n('i18n.MonitorPlan.total'),
  				dataIndex : 'total',
  				width:70,
  			}, {
  				header : i18n('i18n.MonitorPlan.noExecuteNum'),
  				dataIndex : 'noExecute'
  			}, {
  				header : i18n('i18n.MonitorPlan.execute'),
  				dataIndex : 'execute'
  			}, {
  				header : i18n('i18n.MonitorPlan.finished'),
  				dataIndex : 'finished'
  			}
  			, {
  				header : i18n('i18n.MonitorPlan.overdue'),
  				dataIndex : 'overdue'
  			}]
  		},
  		getMonitorPlanDetailColumns:function(){//监控计划明细列
  			return [{
  				xtype:'rownumberer',
  				width:40,
  				align:'center',
  				header:i18n('i18n.Cycle.rownum')
  			},{
  				header : i18n('i18n.MonitorPlan.departName'),
  				dataIndex : 'departName',
  				width:70,
  			},{
  				header : i18n('i18n.Maintainp.topic'),
  				dataIndex : 'topic'	
  			},{
  				header : i18n('i18n.developSchedule.planeType'),
  				dataIndex : 'projectType',
  				renderer : function(value){
                    if(value==null||value==''){
                        return i18n('i18n.developSchedule.noMeaning');
                    }else{
                        return DButil.rendererDictionary(value,DataDictionary.PLAN_TYPE);
                    }
                },
  			},{
  				header : i18n('i18n.MonitorPlanDetail.creatorName'),
  				dataIndex : 'creatorName',
  				width:75,
  			}, {
  				header : i18n('i18n.MonitorPlanDetail.planeTimeLimit'),
  				dataIndex : 'planeTimeLimit'
  			}, {
  				header : i18n('i18n.MonitorPlanDetail.customerName'),
  				dataIndex : 'customerName',
  				width:75,
  			}, {
  				header : i18n('i18n.developSchedule.linkManName'),
  				dataIndex : 'linkManName',
  				width:75,
  			}, {
  				header : i18n('i18n.Maintainp.customerLevel'),
  				dataIndex : 'memberLevel',
  				renderer:function(value){
  					return rendererDictionary(value,DataDictionary.MEMBER_GRADE);
  				},
  				width:70,
  			}
//  			, {
//  				header : i18n('i18n.MonitorPlanDetail.customerType'),
//  				dataIndex : 'customerType',
//  				renderer:function(value){
//  					return rendererDictionary(value,DataDictionary.CUST_TYPE);
//  				}
//  			}
//  			, {
//  				header : i18n('i18n.MonitorPlanDetail.customerProperty'),
//  				dataIndex : 'customerProperty',
//  				renderer:function(value){
//  					return rendererDictionary(value,DataDictionary.CUSTOMER_NATURE);
//  				}
//  			}
  			,{
  				header : i18n('i18n.MonitorPlanDetail.planStatus'),
  				dataIndex : 'planStatus',
  				renderer:function(value){
  					return rendererDictionary(value,DataDictionary.PLAN_STATUS);
  				},
  				width:70,
  			},{
  				header : i18n('i18n.MonitorPlanDetail.scheStatus'),
  				dataIndex : 'scheStatus',
  				renderer:function(value){
  					return rendererDictionary(value,DataDictionary.SCHEDULE_STATUS);
  				}
  			},{
  				header : i18n('i18n.developPlan.executePersion'),
  				dataIndex : 'execuserName'
  			}
//  			,{
//  				header : i18n('i18n.Maintainp.planDesc'),
//  				dataIndex : 'planDesc'
//  			}
  			]
  		}
  	});
	
	/**
	 *将界面显示到viewport中 
	 */
	Ext.create('Ext.Viewport',{
		layout : 'fit',
		items:[Ext.create('MonitorPlanPanel')]
	});
});

