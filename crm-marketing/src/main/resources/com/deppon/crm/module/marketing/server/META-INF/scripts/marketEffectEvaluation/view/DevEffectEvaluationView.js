/**
 * 客户开发效果评估页面
 */

//定义全局变量，用以存放上一级部门信息
var devSuperDeptName = '上一级部门名称';
var devSuperDeptId = '上一级部门Id';
//定义一个全局变量，用以保存查询条件的查询结束时间
var devEndDate = null;
//定义一个全局变量，用以保存查询条件的第一个查询部门ID
var devFirstDeptId = '';
//定义一个全局变量，用以保存查询条件的第二个查询部门ID
var devSecondDeptId = '';
//定义一个全局变量，用以保存查询条件的客户来源
var custSourceForWhole = null;
//定义一个全局变量，用以保存查询条件的业务类型
var businessTypeForWhole = null;
var parentId=null;
var parentName=null;
Ext.onReady(function(){
	//初始化部门用户等信息
	initDeptAndUserInfo();
	
	//客户开发效果评估操作按钮面板
	Ext.define('DevEffectEvaluationBtnPanel',{
		extend:'NormalButtonPanel',
		border:false,
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();		
		},
		getItems:function(){
			var me = this;
			return [
			   {
				xtype:'leftbuttonpanel',
				width:450,
				items:[
				  {//返回上一级
					  xtype:'button',
					  id:'returnBtn',
					  text:i18n('i18n.marketEffectEvaluation.returnLastLevel'),
					  disabled:true,
					  handler:function(t){
//						  var devEffectEvaluationListGrid = Ext.getCmp("devEffectEvaluationListGrid");
//						  Ext.getCmp("deptNameColumn").setText(superDeptName);
						  clickToNextLevel(parentName,parentId);
//						  parentId=
//						  parentName=
					  }
				  },{//导出当前查询结果
					  xtype:'button',
					  text:i18n('i18n.marketEffectEvaluation.exportCurrentResult'),
					  handler:function(btn){
						  btn.disable();
						  //判断是否可以进行导出操作
						  if( Ext.getCmp('devEffectEvaluationListGrid').store.data.length == 0){
							  btn.enable();
							  MessageUtil.showErrorMes('查询结果列表中没有数据，请查询后再进行导出！');
							  return false;
						  }
						  //进度条
						  Ext.MessageBox.show({
								title:'导出当前查询结果',
							    msg:'数据导出中，请稍候！',
							    wait:true
						  });
						  
						  var successFn = function(json){
							  if(json.importSuccess){
								 window.location.href = "../marketing/exportMarketEffectReportExcel.action?fileName="
									 +json.fileName;
								 btn.enable();
								 //隐藏进度条
								 Ext.MessageBox.hide();
								 MessageUtil.showInfoMes('导出成功！');
							  }else{
								  btn.enable();
								  //隐藏进度条
								  Ext.MessageBox.hide();
								  MessageUtil.showInfoMes('文件导出失败！');
							  }
						  }
						  var param = {
								//当前查询条件
								'endDate':devEndDate,
								'firstDeptId':devFirstDeptId,
								'secondDeptId':devSecondDeptId,
								'custSource':custSourceForWhole,
								'businessType':businessTypeForWhole
						  };
						 var failureFn=function(){
							btn.enable();
							//隐藏进度条
							Ext.MessageBox.hide();
							MessageUtil.showErrorMes('导出失败');
						 }
						 DevEffectEvaluationStore.prototype.creatDevReportExcelByCondition(param,successFn,failureFn);	
					  }
				  },{//导出权限下全部数据
					  xtype:'button',
					  text:i18n('i18n.marketEffectEvaluation.exportAll'),
					  handler:function(btn){
						  //导出权限下全部数据功能实现区
						  //进度条
						  Ext.MessageBox.show({
								title:'导出权限下全部数据',
							    msg:'数据导出中，请稍候！',
							    wait:true
						  });
						  
						  var successFn = function(json){
							  if(json.importSuccess){
								 window.location.href = "../marketing/exportMarketEffectReportExcel.action?fileName="
										 +json.fileName;
								 btn.enable();
								 //隐藏进度条
								 Ext.MessageBox.hide();
								 MessageUtil.showInfoMes('导出成功！');	  
							  }
							  else{
								 btn.enable();
								 //隐藏进度条
								 Ext.MessageBox.hide();
								 MessageUtil.showInfoMes('文件未导出成功！');
							  }
								 
						  }
						  var devEffectEvaluationQueryForm = Ext.getCmp("devEffectEvaluationQueryPanel").getForm();
						  var param = {
								'endDate':devEffectEvaluationQueryForm.findField('endDate').getValue(),
								'custSource':devEffectEvaluationQueryForm.findField('custSource').getValue(),
								'businessType':devEffectEvaluationQueryForm.findField('businessType').getValue()
						  };
						 var failureFn=function(){
							btn.enable();
							//隐藏进度条
							Ext.MessageBox.hide();
							MessageUtil.showErrorMes('导出失败');
						 }
						 DevEffectEvaluationStore.prototype.creatDevMarketEffectReportExcel(param,successFn,failureFn);						  
					  }
				  }
				]
			   },{
				 xtype:'middlebuttonpanel' 
			   },{
				xtype : 'rightbuttonpanel',
				items : [{//查询
				    text:i18n('i18n.marketEffectEvaluation.search'),
				    xtype:'button',
				    id:'btnForDevelopSearch',
				    handler:function(btn){
				    	btn.disable();
				    	var devEffectEvaluationQueryForm = Ext.getCmp("devEffectEvaluationQueryPanel").getForm();
				    	//点击查询按钮时为全局变量devEndDate赋值
				    	devEndDate = devEffectEvaluationQueryForm.findField('endDate').getValue();
				    	//点击查询按钮时为全局变量custSourceForWhole赋值
				    	custSourceForWhole = devEffectEvaluationQueryForm.findField('custSource').getValue();
				    	//点击查询按钮时为全局变量businessTypeForWhole赋值
				    	businessTypeForWhole = devEffectEvaluationQueryForm.findField('businessType').getValue();
				    	//取出查询表单中各个条件的value
				    	var manageDept = devEffectEvaluationQueryForm.findField('manageDept').getValue();
						var businessDept = devEffectEvaluationQueryForm.findField('businessDept').getValue();
						var bigDept = devEffectEvaluationQueryForm.findField('bigDept').getValue();
						var smallDept = devEffectEvaluationQueryForm.findField('smallDept').getValue();
						var dept = devEffectEvaluationQueryForm.findField('dept').getValue()
						
						var devEffectEvaluationListGrid = Ext.getCmp("devEffectEvaluationListGrid");
						
						//查询条件细化到营业部
						if(!Ext.isEmpty(dept) && !Ext.isEmpty(smallDept)){
				    		devFirstDeptId = dept;
				    		devSecondDeptId = smallDept;
				    		//上一级部门ID
				    		devSuperDeptId = smallDept;
				    		//上一级部门名称
				    		devSuperDeptName = devEffectEvaluationQueryForm.findField('smallDept').getRawValue();
				    		Ext.getCmp("deptNameColumn").setText(devSuperDeptName);
				    	}
						//查询条件细化到小区
						else if(Ext.isEmpty(dept) && !Ext.isEmpty(smallDept) && !Ext.isEmpty(bigDept)){
							devFirstDeptId = smallDept;
							devSecondDeptId = '';
							
							//上一级部门ID
				    		devSuperDeptId = smallDept;
				    		//上一级部门名称
				    		devSuperDeptName = devEffectEvaluationQueryForm.findField('smallDept').getRawValue();
				    		Ext.getCmp("deptNameColumn").setText(devSuperDeptName);
						}
						//查询条件细化到大区
						else if(Ext.isEmpty(dept) && Ext.isEmpty(smallDept) && !Ext.isEmpty(bigDept)&& 
								!Ext.isEmpty(businessDept)){
							devFirstDeptId = bigDept;
							devSecondDeptId = '';
							
							//上一级部门ID
				    		devSuperDeptId = bigDept;
				    		//上一级部门名称
				    		devSuperDeptName = devEffectEvaluationQueryForm.findField('bigDept').getRawValue();
				    		Ext.getCmp("deptNameColumn").setText(devSuperDeptName);
						}
						//查询条件细化到事业部
						else if(Ext.isEmpty(dept) && Ext.isEmpty(smallDept) && Ext.isEmpty(bigDept)
								&& !Ext.isEmpty(businessDept) && !Ext.isEmpty(manageDept)){
							devFirstDeptId = businessDept;
							devSecondDeptId = '';
							
							//上一级部门ID
				    		devSuperDeptId = businessDept;
				    		//上一级部门名称
				    		devSuperDeptName = devEffectEvaluationQueryForm.findField('businessDept').getRawValue();
				    		Ext.getCmp("deptNameColumn").setText(devSuperDeptName);
						}
						//查询条件细化到经营本部
						else if(Ext.isEmpty(dept) && Ext.isEmpty(smallDept) && Ext.isEmpty(bigDept)
								&& Ext.isEmpty(businessDept) && !Ext.isEmpty(manageDept)){
							devFirstDeptId = manageDept;
							devSecondDeptId = '';
							
							//上一级部门ID
				    		devSuperDeptId = manageDept;
				    		//上一级部门名称
				    		devSuperDeptName = devEffectEvaluationQueryForm.findField('manageDept').getRawValue();
				    		Ext.getCmp("deptNameColumn").setText(devSuperDeptName);
						}
						
						//无任何查询条件
						else if(Ext.isEmpty(dept) && Ext.isEmpty(smallDept) && Ext.isEmpty(bigDept)
								&& Ext.isEmpty(businessDept) && Ext.isEmpty(manageDept)){
							devFirstDeptId = '';
							devSecondDeptId = '';
							
							//上一级部门ID
				    		devSuperDeptId = '';
				    		//上一级部门名称
				    		devSuperDeptName = '';
				    		Ext.getCmp("deptNameColumn").setText('经营本部');
						}
						else{
							MessageUtil.showErrorMes('查询条件无效！');
							btn.enable();
							return false;
						}
						//查询按钮功能实现区
				    	Ext.getCmp('devEffectEvaluationListGrid').store.loadPage(1);
				  }
				 }]
			}];
		}
	});
	
	/**
	 * 客户开发效果评估查询结果表格
	 */
	Ext.define('DevEffectEvaluationListGrid',{
		extend:'SearchGridPanel',   
		columns:null,
		store:null,
		defaults:{
			align:'center'
		},
		listeners:{
			//点击列排序时，总计行始终出现在表格最后一行
			sortchange:function(){
				var replaceModel;
				var count = this.store.getCount()-1;
				for(var i = 0;i <= count;i++){
					if(this.store.getAt(i).get("deptName").indexOf("总计") > 0){
						//获取总计行
						replaceModel = this.store.getAt(i);
						//移除排序后的总计行
						this.store.removeAt(i);
						//将移除的总计行添加到表格最后一列
						this.store.add(replaceModel);
					}
				}
			}
		},
		initComponent:function(){             
			var me = this;
			var store = Ext.create('DevEffectEvaluationListStore');
			store.on('beforeLoad',function(store,operation,e){
				
				//封装查询条件传递给action
				var searchParams = {
					'firstDeptId':devFirstDeptId,
					'secondDeptId':devSecondDeptId,
					'endDate':devEndDate,
					'custSource':custSourceForWhole,
					'businessType':businessTypeForWhole
				};
				Ext.apply(operation,{
					params : searchParams
				});
			});
			store.on('load',function(store,records){
				if(records.length!=0){
					parentId=records[0].get("parentId");
					parentName=records[0].get("parentName");
					if(!Ext.isEmpty(records[0].get("deptName"))){
						//当登录用户拥有全国权限时，查询结果出现“经营本部”时“返回上一级”按钮禁用
						if((AssessAuthority.assessAuthority==='400000147') && (records[0].get("deptName").indexOf("经营本部")>0)){
							Ext.getCmp("returnBtn").disable();
						}
						//当登录用户拥有某个经营本部权限时，查询结果出现“事业部”时“返回上一级”按钮禁用
						else if((AssessAuthority.assessAuthority==='400000127') && (records[0].get("deptName").indexOf("事业部")>0)){
							Ext.getCmp("returnBtn").disable();
						}
						//当登录用户拥有某个事业部查询权限时，查询结果出现“大区”时“返回上一级”按钮禁用
						else if((AssessAuthority.assessAuthority==='400000146') && (records[0].get("deptName").indexOf("大区")>0)){
							Ext.getCmp("returnBtn").disable();
						}
						//当登录用户拥有某个大区查询权限时，查询结果出现“营业区”时“返回上一级”按钮禁用
						else if((AssessAuthority.assessAuthority==='400000145') && (records[0].get("deptName").indexOf("营业区")>0)){
							Ext.getCmp("returnBtn").disable();
						}
						//当登录用户拥有某个营业区查询权限时，查询结果出现“营业部”时“返回上一级”按钮禁用
						else if((AssessAuthority.assessAuthority==='400000126') && (records[0].get("deptName").indexOf("营业部")>0)){
							Ext.getCmp("returnBtn").disable();
						}
						else{
							Ext.getCmp("returnBtn").enable();
						}
					}
					
					var successFn = function(json){
						 var m=new DevEffectEvaluationListModel(json.custDevAssess);
						 store.add(m);
						 //启用查询按钮
						 Ext.getCmp('btnForDevelopSearch').enable();
				    }
				    var param = {
			    		'firstDeptId':devFirstDeptId,
						'secondDeptId':devSecondDeptId,
						'endDate':devEndDate,
						'custSource':custSourceForWhole,
						'businessType':businessTypeForWhole
				    };
				    var failureFn=function(){
				    	Ext.getCmp('btnForDevelopSearch').enable();
				    }
				    
				    DevEffectEvaluationStore.prototype.searchSumCustDevByAuth(param,successFn,failureFn);
				}else{
					Ext.getCmp("returnBtn").disable();
					//启用查询按钮
					Ext.getCmp('btnForDevelopSearch').enable();
				}
			});
			me.store = store;
			me.columns = [{//经营本部   
				header : i18n('i18n.marketEffectEvaluation.manageDept'),
				width:220,
				sortable:true,
				id:'deptNameColumn',
				dataIndex:'deptName',
				renderer:function(val,metaData,record){
					if(!Ext.isEmpty(val)){
						if(val.indexOf("总计")!== -1){
							return	'<span style="color:#ff0000">'+val+'</span>';
						}
						else if(val.indexOf("营业部")!== -1){
							return val;
						}
						else{
							return '<a href=javascript:clickToNextLevel("'+val+'","'+record.get('deptId')+'")>'+val+'</a>';
						}
					}				
				}
			},
//			{//本月新增潜客数量
//				header : i18n('i18n.marketEffectEvaluation.addPotenCustNumHeader'), 
//				columns: [{//会展
//	                header:i18n('i18n.marketEffectEvaluation.exposend'),
//	                width:80,
//	                align:'right',
//	                sortable:true,
//	                dataIndex: 'exposend'
//	            }, {//黄页
//	                header: i18n('i18n.marketEffectEvaluation.yellowPages'),
//	                width:80,
//	                align:'right',
//	                sortable:true,
//	                dataIndex: 'yellowPages'
//	            },{//派单
//	                header:i18n('i18n.marketEffectEvaluation.dispatchList'),
//	                width:80,
//	                align:'right',
//	                sortable:true,
//	                dataIndex: 'dispatchList'
//	            }, {//阿里巴巴
//	                header: i18n('i18n.marketEffectEvaluation.alibaba'),
//	                width:100,
//	                align:'right',
//	                sortable:true,
//	                dataIndex: 'alibaba'
//	            },{//陌生来电
//	                header:i18n('i18n.marketEffectEvaluation.strangerCalls'),
//	                width:100,
//	                align:'right',
//	                sortable:true,
//	                dataIndex: 'strangerCalls'
//	            }, {//订单潜客
//	                header: i18n('i18n.marketEffectEvaluation.orderCust'),
//	                width:100,
//	                align:'right',
//	                sortable:true,
//	                dataIndex: 'orderCust'
//	            }, {//到达散客
//	                header: i18n('i18n.marketEffectEvaluation.arriveCust'),
//	                width:100,
//	                align:'right',
//	                sortable:true,
//	                dataIndex: 'arriveCust'
//	            }]
//			},
			{//客户数量CRM二期新增报表字段
				header : '客户数量', 
				columns: [{//新增潜客数
	                header:'新增潜客数',
	                width:120,
	                align:'right',
	                sortable:true,
	                dataIndex: 'newCreateNum'
	            }, {//转化为散客数
	                header: '转化为散客数',
	                width:120,
	                align:'right',
	                sortable:true,
	                dataIndex: 'turnToScNum'
	            },{//转化为固定客户数
	                header:'转化为固定客户数',
	                width:150,
	                align:'right',
	                sortable:true,
	                dataIndex: 'turnToRcNum'
	            }]
			},
			{//本月新增潜客计划内回访
				header : i18n('i18n.marketEffectEvaluation.addPotenCustPlanVisitHeader'), 
				columns: [{//计划回访数
	                header:i18n('i18n.marketEffectEvaluation.planVisitNum'),
	                width:100,
	                align:'right',
	                sortable:true,
	                dataIndex: 'planVisitNum'
	            }, {//实际回访数
	                header: i18n('i18n.marketEffectEvaluation.actualVisitNum'),
	                width:100,
	                align:'right',
	                sortable:true,
	                dataIndex: 'actualVisitNum'
	            },{//计划内回访率
	                header:i18n('i18n.marketEffectEvaluation.planVisitRate'),
	                width:100,
	                align:'right',
	                sortable:true,
	                dataIndex: 'planVisitRate'
	            }]
			},
//			{//本月新增潜客回访
//				header : i18n('i18n.marketEffectEvaluation.addPotenCustVisitHeader'), 
//				columns: [{//新增数量
//	                header:i18n('i18n.marketEffectEvaluation.nowPotentialNum'),
//	                width:100,
//	                align:'right',
//	                sortable:true,
//	                dataIndex: 'nowPotentialNum'
//	            }, {//新增潜客回访数
//	                header: i18n('i18n.marketEffectEvaluation.nowPotantialVisitNum'),
//	                width:120,
//	                align:'right',
//	                sortable:true,
//	                dataIndex: 'nowPotentialVisitNum'
//	            },{//本月新增潜客回访率
//	                header:i18n('i18n.marketEffectEvaluation.nowPotantialVisitRate'),
//	                width:150,
//	                align:'right',
//	                sortable:true,
//	                dataIndex: 'nowPotentialVisitRate'
//	            }]
//			},
//			{//潜客开发
//				header : i18n('i18n.marketEffectEvaluation.potenCustDevHeader'), 
//				columns: [{//近三个月回访潜客数
//	                header:i18n('i18n.marketEffectEvaluation.beforePotentialNum'),
//	                width:150,
//	                align:'right',
//	                sortable:true,
//	                dataIndex: 'beforePotentialNum'
//	            }, {//近三个月回访潜客本月发货数
//	                header: i18n('i18n.marketEffectEvaluation.nowPotentialDeleviryNum'),
//	                width:210,
//	                align:'right',
//	                sortable:true,
//	                dataIndex: 'nowPotentialDeleviryNum'
//	            },{//潜客开发成功率
//	                header:i18n('i18n.marketEffectEvaluation.potentialDevSuccessRate'),
//	                width:120,
//	                align:'right',
//	                sortable:true,
//	                dataIndex: 'potentialDevSuccessRate'
//	            }]
//			},
			{//阶段转化率与潜客开发CRM二期新增报表字段
				header : '阶段转化率与潜客开发', 
				columns: [{//当前需求确认阶段客户数
	                header:'当前需求确认阶段客户数',
	                width:200,
	                align:'right',
	                sortable:true,
	                dataIndex: 'nowIdentifyNeedsCustNum'
	            }, {//需求确认阶段转化客户数
	                header: '需求确认阶段转化客户数',
	                width:200,
	                align:'right',
	                sortable:true,
	                dataIndex: 'identifyNeedsOutCustNum'
	            },{//需求确认阶段转化率
	                header:'需求确认阶段转化率',
	                width:200,
	                align:'right',
	                sortable:true,
	                dataIndex: 'identifyNeedsCustRate'
	            },{//当前意向洽谈阶段客户数
	                header:'当前意向洽谈阶段客户数',
	                width:200,
	                align:'right',
	                sortable:true,
	                dataIndex: 'nowIntentionDiscussCustNum'
	            }, {//意向洽谈阶段转化客户数
	                header: '意向洽谈阶段转化客户数',
	                width:200,
	                align:'right',
	                sortable:true,
	                dataIndex: 'intentionDiscussOutCustNum'
	            },{//意向洽谈阶段转化率
	                header:'意向洽谈阶段转化率',
	                width:200,
	                align:'right',
	                sortable:true,
	                dataIndex: 'intentionDiscussCustRate'
	            },{//当前持续培养阶段客户数
	                header:'当前持续培养阶段客户数',
	                width:200,
	                align:'right',
	                sortable:true,
	                dataIndex: 'nowContinuingTrainingCustNum'
	            }, {//持续培养阶段转化客户数
	                header: '持续培养阶段转化客户数',
	                width:200,
	                align:'right',
	                sortable:true,
	                dataIndex: 'continuingTrainingOutCustNum'
	            },{//持续培养阶段转化率
	                header:'持续培养阶段转化率',
	                width:200,
	                align:'right',
	                sortable:true,
	                dataIndex: 'continuingTrainingCustRate'
	            },{//近三个月计划内回访潜客数
	                header:'近三个月计划内回访潜客数',
	                width:200,
	                align:'right',
	                sortable:true,
	                dataIndex: 'beforePotentialNum'
	            }, {//近三个月计划内回访潜客发货数
	                header: '近三个月计划内回访潜客发货数',
	                width:200,
	                align:'right',
	                sortable:true,
	                dataIndex: 'nowPotentialDeleviryNum'
	            },{//开发成功率
	                header:'开发成功率',
	                width:120,
	                align:'right',
	                sortable:true,
	                dataIndex: 'potentialDevSuccessRate'
	            },{//开发成功客户零担发货金额
	                header:'开发成功客户零担发货金额',
	                width:200,
	                align:'right',
	                sortable:true,
	                dataIndex: 'lttDelCustAmount'
	            },{//开发成功客户快递发货金额
	                header:'开发成功客户快递发货金额',
	                width:200,
	                align:'right',
	                sortable:true,
	                dataIndex: 'expDelCustAmount'
	            }
	            
	            ]
			}
			];
			me.dockedItems=[{
				xtype:'pagingtoolbar',
				cls:'pagingtoolbar',
				store:store,
				dock:'bottom',
				displayInfo : true,
				autoScroll : true,
				items:[{//每页显示
					text: i18n('i18n.PotentialCustManagerView.SearchResultGrid.page_count'),
					xtype: 'tbtext'
				},Ext.create('Ext.form.ComboBox', {
	               width:window.screen.availWidth*0.0391,
	               triggerAction:'all',
	               forceSelection: true,
	               value:'20',
	               editable:false,
	               name:'comboItem',
	               displayField:'value',
	               valueField:'value',
	               queryMode:'local',
	               store : Ext.create('Ext.data.Store',{
	                   fields : ['value'],
	                   data   : [
	                       {'value':'5'},
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
	           }),{//条
					text: i18n('i18n.PotentialCustManagerView.SearchResultGrid.number'),
					xtype: 'tbtext'
	           }]
			}];
			this.callParent();
		}
	});

	
	/*
	 * 新建一个viewport，用于显示客户开发效果评估界面
	 * 肖红叶
	 */
	var viewport=Ext.create('Ext.Viewport',{
		layout : 'border',
		items:[
	        {//营销效果评估页面中查询条件输入面板form
	        	xtype:'container',
				region:'north',
				layout:'fit',
				items:[Ext.create('EffectEvaluationQueryPanel',{
					id:'devEffectEvaluationQueryPanel',
					pageOfType:'DEV'
				})]
			},{
				xtype:'container',
				region:'center',
				layout:'border',
				items:[
					{//按钮面板
						xtype:'container',
						region:'north',
					    layout:'fit',
					    items:[Ext.create('DevEffectEvaluationBtnPanel',{id:'devEffectEvaluationBtnPanel'})]
					},{//客户开发效果评估查询结果表格
						xtype:'container',
						region:'center',
			    	    layout:'fit',
			    	    items:[ Ext.create('DevEffectEvaluationListGrid',{id:'devEffectEvaluationListGrid'})]
			        }
			   ]
			}
	    ]
	});
	
	viewport.setAutoScroll(false);	
	viewport.doLayout();
});

/**
 * 定义一个函数，当单击表格中除总计行外的其他记录时，进入当前行统计信息的详细信息列表
 * @param 部门名称
 * @param 部门Id
 */
function clickToNextLevel(deptName,deptId){
//	Ext.getCmp("returnBtn").enable();
	devFirstDeptId=deptId;
	devSecondDeptId="";
//	parentId=parentId;
//	parentName=parentName;
	if(Ext.isEmpty(deptId)){//当deptId为空的时候 查询经营本部
		Ext.getCmp("deptNameColumn").setText("经营本部");
	}else{
		Ext.getCmp("deptNameColumn").setText(deptName);
	}
	Ext.getCmp('devEffectEvaluationListGrid').store.loadPage(1);
}