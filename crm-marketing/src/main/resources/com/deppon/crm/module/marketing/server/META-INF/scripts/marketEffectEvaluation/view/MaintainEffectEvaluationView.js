/**
 * 客户维护效果评估页面
 */

//定义全局变量，用以存放上一级部门信息
var maintainSuperDeptName = '上一级部门名称';
var maintainSuperDeptId = '上一级部门Id';
//定义一个全局变量，用以保存查询条件的查询结束时间
var maintainEndDate = null;
//定义一个全局变量，用以保存查询条件的第一个查询部门ID
var maintainFirstDeptId = '';
//定义一个全局变量，用以保存查询条件的第二个查询部门ID
var maintainSecondDeptId = '';
var parentId=null;
var parentName=null;
Ext.onReady(function(){
	//客户维护效果评估操作按钮面板
	Ext.define('MaintainEffectEvaluationBtnPanel',{
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
					  disabled:true,
					  text:i18n('i18n.marketEffectEvaluation.returnLastLevel'),
					  handler:function(){
						  clickToNextLevel(parentName,parentId);
					  }
				  },{//导出当前查询结果
					  xtype:'button',
					  text:i18n('i18n.marketEffectEvaluation.exportCurrentResult'),
					  handler:function(btn){
						  btn.disable();
						  //判断是否可以进行导出操作
						  if( Ext.getCmp('maintainEffectEvaluationListGrid').store.data.length == 0){
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
								 MessageUtil.showInfoMes('文件未导出成功！');
							  }
						  }
						  var param = {
								//当前查询条件
								'endDate':maintainEndDate,
								'firstDeptId':maintainFirstDeptId,
								'secondDeptId':maintainSecondDeptId
						  };
						 var failureFn=function(){
							btn.enable();
							//隐藏进度条
							Ext.MessageBox.hide();
							MessageUtil.showErrorMes('导出失败');
						 }
						 MaintainEffectEvaluationStore.prototype.creatMaintainReportExcelByCondition(param,successFn,failureFn);	
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
						  var maintainEffectEvaluationQueryForm = Ext.getCmp("maintainEffectEvaluationQueryPanel").getForm();
						  var param = {
								'endDate':maintainEffectEvaluationQueryForm.findField('endDate').getValue()
						  };
						 var failureFn=function(){
							btn.enable();
							//隐藏进度条
							Ext.MessageBox.hide();
							MessageUtil.showErrorMes('导出失败');
						 }
						 MaintainEffectEvaluationStore.prototype.creatMaintainMarketEffectReportExcel(param,successFn,failureFn);						  
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
				    id:'btnForMaintainSearch',
				    handler:function(btn){
				    	btn.disable();
						//查询按钮功能实现区
	  					var maintainEffectEvaluationQueryForm = Ext.getCmp("maintainEffectEvaluationQueryPanel").getForm();
	  					//为全局变量maintainEndDate赋值
	  					maintainEndDate = maintainEffectEvaluationQueryForm.findField('endDate').getValue();
	  					//取出查询表单中各个条件的value
	  					var manageDept = maintainEffectEvaluationQueryForm.findField('manageDept').getValue();
	  					var businessDept = maintainEffectEvaluationQueryForm.findField('businessDept').getValue();
	  					var bigDept = maintainEffectEvaluationQueryForm.findField('bigDept').getValue();
	  					var smallDept = maintainEffectEvaluationQueryForm.findField('smallDept').getValue();
	  					var dept = maintainEffectEvaluationQueryForm.findField('dept').getValue();
	  					//查询条件细化到营业部
	  					if(!Ext.isEmpty(dept) && !Ext.isEmpty(smallDept)){
	  						maintainFirstDeptId = dept;
	  						maintainSecondDeptId = smallDept;
	  						
	  						//上一级部门ID
	  						maintainSuperDeptId = smallDept;
				    		//上一级部门名称
	  						maintainSuperDeptName = maintainEffectEvaluationQueryForm.findField('smallDept').getRawValue();
	  						Ext.getCmp("maintainDeptNameColumn").setText(maintainSuperDeptName);
				    	}
	  					//查询条件细化到小区
						else if(Ext.isEmpty(dept) && !Ext.isEmpty(smallDept)){
							maintainFirstDeptId = smallDept;
							maintainSecondDeptId = '';
							
							//上一级部门ID
	  						maintainSuperDeptId = smallDept;
				    		//上一级部门名称
	  						maintainSuperDeptName = maintainEffectEvaluationQueryForm.findField('smallDept').getRawValue();
	  						Ext.getCmp("maintainDeptNameColumn").setText(maintainSuperDeptName);
						}
	  					//查询条件细化到大区
						else if(Ext.isEmpty(dept) && Ext.isEmpty(smallDept) && !Ext.isEmpty(bigDept)){
							maintainFirstDeptId = bigDept;
							maintainSecondDeptId = '';
							
							//上一级部门ID
	  						maintainSuperDeptId = bigDept;
				    		//上一级部门名称
	  						maintainSuperDeptName = maintainEffectEvaluationQueryForm.findField('bigDept').getRawValue();
	  						Ext.getCmp("maintainDeptNameColumn").setText(maintainSuperDeptName);
						}
	  					//查询条件细化到事业部
						else if(Ext.isEmpty(dept) && Ext.isEmpty(smallDept) && Ext.isEmpty(bigDept)
								&& !Ext.isEmpty(businessDept)){
							maintainFirstDeptId = businessDept;
							maintainSecondDeptId = '';
							
							//上一级部门ID
	  						maintainSuperDeptId = businessDept;
				    		//上一级部门名称
	  						maintainSuperDeptName = maintainEffectEvaluationQueryForm.findField('businessDept').getRawValue();
	  						Ext.getCmp("maintainDeptNameColumn").setText(maintainSuperDeptName);
						}
	  					//查询条件细化到经营本部
						else if(Ext.isEmpty(dept) && Ext.isEmpty(smallDept) && Ext.isEmpty(bigDept)
								&& Ext.isEmpty(businessDept) && !Ext.isEmpty(manageDept)){
							maintainFirstDeptId = manageDept;
							maintainSecondDeptId = '';
							//上一级部门ID
	  						maintainSuperDeptId = manageDept;
				    		//上一级部门名称
	  						maintainSuperDeptName = maintainEffectEvaluationQueryForm.findField('manageDept').getRawValue();
	  						Ext.getCmp("maintainDeptNameColumn").setText(maintainSuperDeptName);
						}
	  					//无任何查询条件
						else if(Ext.isEmpty(dept) && Ext.isEmpty(smallDept) && Ext.isEmpty(bigDept)
								&& Ext.isEmpty(businessDept) && Ext.isEmpty(manageDept)){
							maintainFirstDeptId = '';
							maintainSecondDeptId = '';
							
							//上一级部门ID
	  						maintainSuperDeptId = '';
				    		//上一级部门名称
	  						maintainSuperDeptName = '';
	  						Ext.getCmp("maintainDeptNameColumn").setText('经营本部');
						}
	  					//查询条件无效
						else{
							MessageUtil.showErrorMes('查询条件无效！');
							btn.enable();
							return false;
						}
	  					Ext.getCmp('maintainEffectEvaluationListGrid').store.loadPage(1);
				  }
				}]
			}];
		}
	});
	
	/**
	 * 客户维护效果评估查询结果表格
	 */
	Ext.define('MaintainEffectEvaluationListGrid',{
		extend:'SearchGridPanel',   
		columns:null,
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
			var store = Ext.create('MaintainEffectEvaluationListStore');
			
			store.on('beforeLoad',function(store,operation,e){				
				//封装查询条件传递给action
				var searchParams = {
						'firstDeptId':maintainFirstDeptId,
						'secondDeptId':maintainSecondDeptId,
						'endDate':maintainEndDate
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
						 var m=new MaintainEffectEvaluationListModel(json.custMatAssess);
						 store.add(m);
						 Ext.getCmp('btnForMaintainSearch').enable();
				    }
				    var param = {
			    		'firstDeptId':maintainFirstDeptId,
						'secondDeptId':maintainSecondDeptId,
						'endDate':maintainEndDate
				    };
				    var failureFn=function(){
				    	Ext.getCmp('btnForMaintainSearch').enable();
				    }
				    MaintainEffectEvaluationStore.prototype.searchSumCustMatByAuth(param,successFn,failureFn);	
				}else{
					//启用查询按钮
					Ext.getCmp('btnForMaintainSearch').enable();
				}
			});
			me.store = store;
			me.columns = [{//经营本部   
				header : i18n('i18n.marketEffectEvaluation.manageDept'),
				width:220,
				sortable:true,
				id:'maintainDeptNameColumn',
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
			},{//上月投诉理赔客户数
                header:i18n('i18n.marketEffectEvaluation.recompenseCustNum'),
                width:150,
                align:'right',
                dataIndex: 'recompenseCustNum'
            },{
                //上月上报零担投诉理赔的客户数auth:李春雨
                header:i18n('i18n.marketEffectEvaluation.lttCompCustNum'),
                width:210,
                align:'right',
                dataIndex: 'lttCompCustNum'
            },{
                //上月上报快递投诉理赔的客户数
                header:i18n('i18n.marketEffectEvaluation.expCompCustNum'),
                width:210,
                align:'right',
                dataIndex: 'expCompCustNum'
            },{//上月投诉理赔本月回访数
                header: i18n('i18n.marketEffectEvaluation.nowVisitNum'),
                width:180,
                sortable:true,
                align:'right',
                dataIndex: 'nowVisitNum'
            },{
                //上月上报零担投诉理赔的客户本月回访数
                header: i18n('i18n.marketEffectEvaluation.lttVisitNum'),
                width:260,
                sortable:true,
                align:'right',
                dataIndex: 'lttVisitNum'
            },{
                //上月上报快递投诉理赔的客户本月回访数
                header: i18n('i18n.marketEffectEvaluation.expVisitNum'),
                width:260,
                sortable:true,
                align:'right',
                dataIndex: 'expVisitNum'
            },{//投诉理赔回访率
                header:i18n('i18n.marketEffectEvaluation.nowVisitRate'),
                width:130,
                sortable:true,
                align:'right',
                dataIndex: 'nowVisitRate'
            },{
                //零担投诉理赔客户回访率
                header:i18n('i18n.marketEffectEvaluation.lttVisitRate'),
                width:170,
                sortable:true,
                align:'right',
                dataIndex: 'lttVisitRate'
            },{
                //快递投诉理赔客户回访率
                header:i18n('i18n.marketEffectEvaluation.expVisitRate'),
                width:170,
                sortable:true,
                align:'right',
                dataIndex: 'expVisitRate'
            },{//上上月发货固定客户数
				header : i18n('i18n.marketEffectEvaluation.memberDeleviryNum'), 
				width:160,
				sortable:true,
				align:'right',
	            dataIndex: 'memberDeleviryNum'
            },{//上月流失客户数
                header:i18n('i18n.marketEffectEvaluation.lostCustNum'),
                width:130,
                sortable:true,
                align:'right',
                dataIndex: 'lostCustNum'
            }, {//客户流失率
                header: i18n('i18n.marketEffectEvaluation.lostCustRate'),
                width:100,
                align:'right',
                sortable:true,
                dataIndex: 'lostCustRate'
            },{//本月回访流失客户数
                header:i18n('i18n.marketEffectEvaluation.lostCustVistitNum'),
                width:160,
                align:'right',
                sortable:true,
                dataIndex: 'lostCustVisitNum'
            }, {//流失回访率
                header: i18n('i18n.marketEffectEvaluation.lostVisitRate'),
                width:100,
                align:'right',
                sortable:true,
                dataIndex: 'lostVisitRate'
            },{//挽回客户数
                header:i18n('i18n.marketEffectEvaluation.saveCustNum'),
                width:120,
                align:'right',
                sortable:true,
                dataIndex: 'saveCustNum'
            },{//客户挽回率
                header:i18n('i18n.marketEffectEvaluation.saveCustRate'),
                width:120,
                align:'right',
                sortable:true,
                dataIndex: 'saveCustRate'
            }];
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
	 * 新建一个viewport，用于显示客户维护效果评估界面
	 * 肖红叶
	 */
	var viewport=Ext.create('Ext.Viewport',{
		layout : 'border',
		items:[
	        {//营销效果评估页面中查询条件输入面板form
	        	xtype:'container',
				region:'north',
				layout:'fit',
				items:[Ext.create('EffectEvaluationQueryPanel',{id:'maintainEffectEvaluationQueryPanel'})]
			},{
				xtype:'container',
				region:'center',
				layout:'border',
				items:[
					{//按钮面板
						xtype:'container',
						region:'north',
					    layout:'fit',
					    items:[Ext.create('MaintainEffectEvaluationBtnPanel',{id:'maintainEffectEvaluationBtnPanel'})]
					},{//客户维护效果评估查询结果表格
						xtype:'container',
						region:'center',
			    	    layout:'fit',
			    	    items:[ Ext.create('MaintainEffectEvaluationListGrid',{id:'maintainEffectEvaluationListGrid'})]
			        },	
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
	Ext.getCmp("returnBtn").enable();
	maintainFirstDeptId=deptId;
	maintainSecondDeptId="";
//	parentId=parentId;
//	parentName=parentName;
	if(Ext.isEmpty(deptId)){//当deptId为空的时候 查询经营本部
		Ext.getCmp("maintainDeptNameColumn").setText("经营本部");
	}else{
		Ext.getCmp("maintainDeptNameColumn").setText(deptName);
	}
	Ext.getCmp('maintainEffectEvaluationListGrid').store.loadPage(1);
}