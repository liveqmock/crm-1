//标记线路部门grid是否修改
var isChange=0;
var myMask=null; 
/**
  * .
  * <p>
  * 客户群主页面<br/>
  * </p>
  * 
  * @author 吕崇新
  * @时间 2014-03-20
  */
Ext.onReady(function(){
	   /**
  *将界面显示到viewport中 
  */
var keys=[
        //产品类型
        'CLIENTBASE_PRODUCT_TYPE',//改用订单运输方式 FOSS_PRODUCT_TYPE
        //客户类型
        'CUST_TYPE',
        //活动线路区域要求
        'LINE_TYPE',
        // 行业
        'TRADE',
        //二级行业
        'SECOND_TRADE',
        //客户等级
        'MEMBER_GRADE',
        //货量潜力
        'CARGO_POTENTIAL',
        //活动状态
        'ACTIVITY_STATUS',
        //客户来源
        'CUST_SOURCE',
        //客户属性
        'CUSTOMER_NATURE',
        //提货方式
        'CLIENTBASE_PICKUPGOODS',
        //合作意向
        'COOPERATION_INTENTION',
        //客户群状态
        'CUST_CONDITION_STATUS'
    ];
    //初始化业务参数
    initDataDictionary(keys);
    //初始化用户和部门信息
	initDeptAndUserInfo();

 Ext.define('searchConditionPanel',{
		extend:'SearchFormPanel',
		id:'searchCustcoditionPanelId',
		items:null,
		border:0,
		layout:{
			type : 'table',
			columns : 3
		},
		defaults:{
			labelWidth:60,
			labelAlign:'right',
			margin:'20 5 20 5'
		},
		initComponent:function(){			
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return[{//客户群名称
				xtype:'textfield',
		    	fieldLabel : i18n('i18n.ClientBaseManegerView.clentBaseName'),
		    	labelWidth:70,
		    	maxLength:40,
		    	width:290,
				name:'cilentBaseName',
				listeners:{
					blur:function(field){
						field.setValue(field.getValue().trim());	
					}
				}
				
				
		    },{//创建时间
				fieldLabel: i18n('i18n.ClientBaseManegerView.createTime'),
	            width:210,
	            xtype:'datefield',
	            format: 'Y-m-d',
	            editable:false,
	            maxValue:new Date(),
	            name:'createStartTime'
				
			},{//到
				fieldLabel: i18n('i18n.ClientBaseManegerView.endTime'),
				labelWidth:30,
	            width:210,
	            xtype:'datefield',
	            maxValue:new Date(),
	            format: 'Y-m-d',
	            editable:false,
	            name:'createEndTime'
			}]
		}
	});

	Ext.define('ConditionBtnPanel',{
		extend:'NormalButtonPanel',
		border:false,
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();		
		},
		getItems:function(){
			var me = this;
			return [{
				xtype:'leftbuttonpanel',
				width:450,
				items:[
				  {//查看详情
					  xtype:'button',
					  text: i18n('i18n.ClientBaseManegerView.detailView'),
					  disabled:true,
					  id:'detailBtnId',
					  handler:function(btn){
						  //获得客户群查询结果列表
					    var grid = Ext.getCmp('ClientBaseInfoListGridId');
					    var selection = grid.getSelectionModel().getSelection();
						if(selection.length != 1){
	                        MessageUtil.showMessage(i18n('i18n.ClientBaseManeger.message.onlyChoiceOne'));//请选择一条操作记录!
	                        return;
	                    }
						var data = selection[0].internalId;
						var successFn = function(json){
							var clientBaseModifyAndDetailWindow = Ext.getCmp('clientBaseModifyAndDetailWindowId');//获取win
						  	if(Ext.isEmpty(clientBaseModifyAndDetailWindow)){
						  		clientBaseModifyAndDetailWindow = Ext.create('ClientBaseModifyAndDetailWindow',{id : 'clientBaseModifyAndDetailWindowId'});
						  	}
						  	clientBaseModifyAndDetailWindow.show();
						  	clientBaseModifyAndDetailWindow.flag="detail";
						  	var lineType=json.clientBase.lineType
						  	choiceLineDept(lineType,'detail');
		                    clientBaseModifyAndDetailWindow.setTitle(i18n('i18n.ClientBaseManegerView.detailView'));
		                    //隐藏按钮并跟换显示
		                   	Ext.getCmp('clientBaseModifyAndDetailWindowId').down('button[name=save]').hide();
		                   	Ext.getCmp('clientBaseModifyAndDetailWindowId').down('button[name=reset]').hide();
		                   	Ext.getCmp('clientBaseModifyAndDetailWindowId').down('button[name=cancel]').setText(i18n('i18n.ClientBaseManegerView.cloes'));
		                   
							var custConditionModel = new ClientBaseInfoModel();
		                     custConditionModel.set(json.clientBase);
		                     // 加载二级行业数据
							  secondTrade = Ext.getCmp('ClientBaseModifyAndDetailUpPanelId').down('combo[name=secondTrades]');
		                      secondTrade.reset();
//		                      secondTrade.getStore().load({  
//		                          params: {  
//		                              'tradesList' : json.clientBase.trades  
//		                          }  
//		                      }); 
//		                     secondTrade.setValue(json.clientBase.secondTrades)
		                      var successFn = function(json){
		                      		secondTrade.forceSelection=false;
							        secondTrade.setValue(json.secondTrade.toString())
									secondTrade.forceSelection=true;
		                      }
		                      var failureFn = function(json){
								MessageUtil.showErrorMes(json.message);
								return false;
							}

		                     ClientBaseStore.prototype.searchSecondTradesToString({'conditionId' :json.clientBase.id,'conditionType' : "CUSTOMER_BASE"},successFn,failureFn) 
							 Ext.getCmp('ClientBaseModifyAndDetailUpPanelId').getForm().loadRecord(custConditionModel)
							 //判断加载
							 if('LEAVE_ARRIVE'==json.clientBase.lineType){
							 	Ext.getCmp('clientBaseLineGridId').getStore().loadData(json.clientBase.lineDept)
							 }else{
							 	Ext.getCmp('clientBaseLineTreeGridId').getStore().loadData(json.clientBase.lineDept)
							 }
							 
						}
						var failureFn = function(json){
							MessageUtil.showErrorMes(json.message);
							return false;
						}
						 ClientBaseStore.prototype.searchClientBaseById({'clientBase.id':data},successFn,failureFn)

					  }
				  },{//新增
					  xtype:'button',
					  text:i18n('i18n.ClientBaseManegerView.add'),
					  handler:function(btn){
					  	var clientBaseModifyAndDetailWindow = Ext.getCmp('clientBaseModifyAndDetailWindowId');
					  	if(Ext.isEmpty(clientBaseModifyAndDetailWindow)){
					  		clientBaseModifyAndDetailWindow = Ext.create('ClientBaseModifyAndDetailWindow',{id : 'clientBaseModifyAndDetailWindowId'});
					  	}
						clientBaseModifyAndDetailWindow.setTitle(i18n('i18n.ClientBaseManegerView.addClientBase'));
						clientBaseModifyAndDetailWindow.show();
						//禁用客户等级
						Ext.getCmp('clientGradesId').disable();
						 loadLineDeptTree(null);//异步加载线路部门树
						clientBaseModifyAndDetailWindow.flag='add';
					  }
				  },{//修改
					  xtype:'button',
					  text:i18n('i18n.ClientBaseManegerView.modify'),
					  disabled:true,
					  id:'updateBtnId',
					  handler:function(btn){
						var grid = Ext.getCmp('ClientBaseInfoListGridId');
					    var selection = grid.getSelectionModel().getSelection();
						if(selection.length != 1){
	                        MessageUtil.showMessage(i18n('i18n.ClientBaseManeger.message.onlyChoiceOne'));//请选择一条操作记录!
	                        return false;
	                    }
						var data = selection[0].internalId;
						var successFn = function(json){
							var clientBaseModifyAndDetailWindow = Ext.getCmp('clientBaseModifyAndDetailWindowId');//获取win
						  	if(Ext.isEmpty(clientBaseModifyAndDetailWindow)){
						  		clientBaseModifyAndDetailWindow = Ext.create('ClientBaseModifyAndDetailWindow',{id : 'clientBaseModifyAndDetailWindowId'});
						  	}			
		                    clientBaseModifyAndDetailWindow.setTitle(i18n('i18n.ClientBaseManegerView.modifyClientBase'));
		                    clientBaseModifyAndDetailWindow.flag='modify';
		                    clientBaseModifyAndDetailWindow.show();
		                    isChange=0;//标记是否修改
		                    loadLineDeptTree(json);//异步加载线路部门树
		                    var lineType=json.clientBase.lineType
							choiceLineDept(lineType,'modify');
							var custConditionModel = new ClientBaseInfoModel();
		                     custConditionModel.set(json.clientBase);
							// 加载二级行业数据
							  secondTrade = Ext.getCmp('ClientBaseModifyAndDetailUpPanelId').down('combo[name=secondTrades]');
		                      secondTrade.reset();
		                      secondTrade.getStore().load({  
		                          params: {  
		                              'tradesList' : json.clientBase.trades  
		                          }  
		                      }); 
		                      secondTrade.setValue(json.clientBase.secondTrade)
		                      //判断是否禁用客户等级
		                      if(Ext.Array.contains(json.clientBase.clientTypes,'RC_CUSTOMER')){
									 Ext.getCmp('clientGradesId').enable();
							  }else{
									Ext.getCmp('clientGradesId').disable();
							  }
		                      Ext.getCmp('ClientBaseModifyAndDetailUpPanelId').getForm().loadRecord(custConditionModel)
		                      //给window的 统一部门线路属性赋值
		                      Ext.getCmp('clientBaseModifyAndDetailWindowId').lineType=lineType;
		                      if('LEAVE_ARRIVE'==lineType){
							 	Ext.getCmp('clientBaseLineGridId').getStore().loadData(json.clientBase.lineDept)
							  }else{
							 	Ext.getCmp('clientBaseLineTreeGridId').getStore().loadData(json.clientBase.lineDept)
							 	//初始化树方法
							 	initTree(json)
							  }
							}
						var failureFn = function(json){
							MessageUtil.showErrorMes(json.message);
							return false ;
						}
						 ClientBaseStore.prototype.searchClientBaseById({'clientBase.id':data},successFn,failureFn)
					  }
				  },{//删除
					  xtype:'button',
					  text:i18n('i18n.ClientBaseManegerView.delete'),
					  disabled:true,
					  id:'deleteBtnId',
					  handler:function(btn){//您确定要删除吗？
						  MessageUtil.showQuestionMes(i18n('i18n.ClientBaseManeger.message.conformDelete'), function(e) {
								if (e == 'yes') {
									  //禁用删除按钮
									  btn.disable();
									  //获得问卷查询结果列表
									  var grid = Ext.getCmp('ClientBaseInfoListGridId');
									  //获得选中记录
									  var selection=grid.getSelectionModel().getSelection();
									  var data = selection[0].internalId;
									  var successFn = function(json){
										  //启用删除按钮
										  btn.enable();
										  //删除成功
										  MessageUtil.showInfoMes(i18n('i18n.ClientBaseManeger.message.DeleteSuccess'));
										  //刷新列表
										  Ext.getCmp('ClientBaseInfoListGridId').store.loadPage(1);
									  }
									  var param = {
											  'clientBase.id':data
											
									  };
									  var failureFn=function(json){
										  //启用删除按钮
										  btn.enable();
										  if(Ext.isEmpty(json)){
											  MessageUtil.showErrorMes(i18n('i18n.ClientBaseManeger.message.sorryDeleteDefeat'));//抱歉，删除失败！
										  }
										  else{
											  MessageUtil.showErrorMes(json.message);  
										  }
									  }
									  ClientBaseStore.prototype.deleteClientBaseById(param,successFn,failureFn);
								}
						  });  
					  }
				  }]
			   },{
				 xtype:'middlebuttonpanel' 
			   },{
				xtype : 'rightbuttonpanel',
				items : [{//查询
				    text:i18n('i18n.ClientBaseManegerView.search'),
				    xtype:'button',
				    handler:function(btn){
				    	//验证查询条件
				    	if(Ext.getCmp('searchCustcoditionPanelId').getForm().isValid()){
          					Ext.getCmp('ClientBaseInfoListGridId').store.loadPage(1);;
                        };
				    }
				 },{
					 xtype:'button',
					 text:i18n('i18n.ClientBaseManegerView.reset'),
					 handler:function(){
						 //重置查询条件
						 Ext.getCmp('searchCustcoditionPanelId').getForm().reset();
					 }
				 }]
			   }];
		}
	});

/**
 * 查询显示客户群grid
 */

	Ext.define('ClientBaseInfoListGrid',{
		extend:'SearchGridPanel',
		id:'ClientBaseInfoListGridId',
		columns:null,
		store:null,
		defaults:{
			align:'center'
		},
		selModel : Ext.create('Ext.selection.CheckboxModel',{
			mode:'SINGLE',
			allowDeselect:true,
			listeners:{
				select:function(th,record,index){
					//查询详情启用
					Ext.getCmp('detailBtnId').enable();
					if(User.deptId==record.data.clientDeptId&&record.data.clientBaseStatus=='UNUSED'){
						if(User.empId==record.data.createUserId||User.empCode==record.data.principalId){
							Ext.getCmp('updateBtnId').enable();
							Ext.getCmp('deleteBtnId').enable();
						}
					}				
				},
				deselect:function(th,record,index){
					Ext.getCmp('detailBtnId').disable();
					Ext.getCmp('updateBtnId').disable();
					Ext.getCmp('deleteBtnId').disable();
				}
			}
		}),
		initComponent:function(){             
			var me = this;
			var store = Ext.create('clientBaseInfoListStore');
			store.on('beforeload',function(store,operation,e){
				//获得查询条件输入form
				    	var form = Ext.getCmp('searchCustcoditionPanelId').getForm();
				    	//客户群名称
				    	var cilentBaseName = form.findField('cilentBaseName').getValue().trim();
				    	//创建开始时间
				    	var createStartTime = form.findField('createStartTime').getValue();
				    	//创建结束时间
				    	var createEndTime = form.findField('createEndTime').getValue();
						if(!Ext.isEmpty(createStartTime)^!Ext.isEmpty(createEndTime)){
						MessageUtil.showMessage(i18n('i18n.ClientBaseManeger.clientBaseTimeOneNull'))//客户创建的起始时间或结束时间不能为空！
						return false;
						}
						if(DButil.compareTwoDate(createStartTime,createEndTime)<0){
							MessageUtil.showMessage(i18n('i18n.ClientBaseManeger.startMoreThanEndTime'))//开始时间不能大于结束时间！
							return false;
						}
				    	searchParams = {
				    			'clientBase.clientBaseName':cilentBaseName,
				    			'clientBase.clientCreateStartTime':createStartTime,
				    			'clientBase.clientCreateEndTime':createEndTime
				    		};
				Ext.apply(operation,{
					params :searchParams
				});
			});
			me.store = store;
			me.columns = [{//序号
				xtype:'rownumberer',
				width:40,
				align:'center',
				header:i18n('i18n.ClientBaseManegerView.number')
			},{//客户群名称 
				header :i18n('i18n.ClientBaseManegerView.clentBaseName'),
				flex: 1,
				dataIndex:'clientBaseName',
				renderer:function(val,metaData,record){
					if(!Ext.isEmpty(val)){
						var content = val;
						if(val.length >= 21){
							val=val.substr(0,20)+'...';
						}else{
							val= val;
						} 
						return '<a title="'+content+'">'+val+'</a>';
					}			
				}
			},{//客户群状态
				header :i18n('i18n.ClientBaseManegerView.clentBaseStatus'),
				flex: 1,
				dataIndex:'clientBaseStatus',
				renderer:function(val,metaData,record){
					 if(val==='UNUSED'){
	                            return i18n('i18n.ClientBaseManegerView.clentBaseStatusUnused');//未使用
	                        }else if(val==='USEING'){
	                            return i18n('i18n.ClientBaseManegerView.clentBaseStatusUseing');//使用中
	                        }else if(val==='ISSUED_IN'){
	                            return i18n('i18n.ClientBaseManegerView.clentBaseStatusIssued_in');//下发中
	                        }else if(val==='ISSUED_COMPLETE'){
	                            return i18n('i18n.ClientBaseManegerView.clentBaseStatusIssued_complete');//已下发
	                        }else{
	                            return '';
	                        }				
				}
			},{//创建人
				header : i18n('i18n.ClientBaseManegerView.createUser'),
				flex: 1,
				dataIndex:'createUser',
				renderer:function(val,metaData,record){
					if(!Ext.isEmpty(val)){
						var content = val;
						if(val.length >= 21){
							val=val.substr(0,20)+'...';
						}else{
							val= val;
						} 
						return '<a title="'+content+'">'+val+'</a>';
					}			
				}
			},{//创建时间
				header : i18n('i18n.ClientBaseManegerView.createTime'),
				dataIndex : 'createDate',
				flex: 1,
				format : 'Y-m-d',
				align : 'center',
				renderer : DButil.renderDate
			
			},{//创建部门
				header : i18n('i18n.ClientBaseManegerView.createDept'),
				flex: 1,
				dataIndex:'clientDeptName'
				
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
	           }),{//条
					text: i18n('i18n.PotentialCustManagerView.SearchResultGrid.number'),
					xtype: 'tbtext'
	           }]
			}];
			this.callParent();
		}
	});

var viewport=Ext.create('Ext.Viewport',{
	     layout : 'border',
	     autoScroll:true,
	     items:[Ext.create('searchConditionPanel',{
	         region:'north'
	     }),{
	         xtype:'container',
	         region:'center',
	         layout:'border',
	         items:[Ext.create('ConditionBtnPanel',{
	             region:'north'
	         }),Ext.create('ClientBaseInfoListGrid',{
	             region:'center'
	         })
	         ]
	     }]
	 });
 
 viewport.setAutoScroll(false);
 viewport.doLayout();
 Ext.getCmp('ClientBaseInfoListGridId').store.loadPage(1);//初始化加载
});

/**
 * 校验时间范围是否合法
 * lvchongxin
 * @param dateStart
 * @param dateEnd
 */
function dateRangeNotValid(dateStart,dateEnd){
	var result = false;
	if(!Ext.isEmpty(dateStart) && !Ext.isEmpty(dateEnd)){
		if(DButil.compareTwoDate(dateStart,dateEnd) < 0){
			result = true;
		}
	}
	return result;
}
/**
 * 重置控件
 * lvchongxin
 * 
 */
function resetClientBaseWindow(){
	Ext.getCmp('secondTradesId').forceSelection=true;//二级行业
	//重置多选信息控件
	Ext.getCmp('ClientBaseModifyAndDetailUpPanelId').getForm().reset();
	var DetailUpPanel=Ext.getCmp('ClientBaseModifyAndDetailUpPanelId')
	eachReadOnly(DetailUpPanel,13,false)
	if(myMask!=null){
			myMask.hide();
	}
	//重置线路标题
	resetTitle();
	//重置二级行业
	Ext.getCmp('secondTradesId').store.removeAll();
	//重置客户等级
	Ext.getCmp('clientGradesId').enable();
	//重置线路grid
	Ext.getCmp('clientBaseLineGridId').store.removeAll();
	//重置线路grid Tbar
	Ext.getCmp('lineGridSelectId').up().enable();
	//重置两个combox
	Ext.getCmp('downBeginDeptOrCityCombox').reset()
	Ext.getCmp('downEndDeptOrCityCombox').reset()
	//重置发到货区域树
	Ext.getCmp('clientBaseLineTreeGridId').enable()
	Ext.getCmp('lineTreeForClientBaseId').enable()
	Ext.getCmp('clientBaseLineTreeGridId').store.removeAll();
	//获取到树并清空所有的勾选项
	Ext.getCmp('lineTreeForClientBaseId').getRootNode( ).cascadeBy(function(node){
		node.set('checked', false);
	})
	Ext.getCmp('lineTreeForClientBaseId').collapseAll();
	Ext.getCmp('lineGridSelectId').setValue('LEAVE_ARRIVE');
	Ext.QuickTips.destroy();  //销毁
}
/**
 * 切换线路类型并禁用控件
 * @param lineType
 * @param flag
 */
function choiceLineDept(lineType,flag){
		if(lineType=='LEAVE_ARRIVE'||null==lineType){
		  	Ext.getCmp('lineTreeForClientBaseId').hide();
            Ext.getCmp('clientBaseLineTreeGridId').hide();
            Ext.getCmp('clientBaseLineGridId').show();
            Ext.getCmp('lineGridSelectId').setValue(lineType);
            if(flag=='detail'){
            	//禁用window组件
            	var DetailUpPanel=Ext.getCmp('ClientBaseModifyAndDetailUpPanelId')
				eachReadOnly(DetailUpPanel,13,true)
            	Ext.getCmp('lineGridSelectId').up().disable();
            }
		  	}else{
			  	Ext.getCmp('clientBaseLineGridId').hide();
	            Ext.getCmp('lineTreeForClientBaseId').show();
	            Ext.getCmp('clientBaseLineTreeGridId').show();
	            Ext.getCmp('lineTreeSelectId').setValue(lineType);
	            if(lineType=='ARRIVE'){
	            		Ext.getCmp('clientBaseLineTreeGridId').columns[0].hide();
	            		Ext.getCmp('clientBaseLineTreeGridId').columns[1].show();
	            	}else{
	            		Ext.getCmp('clientBaseLineTreeGridId').columns[1].hide();
	            		Ext.getCmp('clientBaseLineTreeGridId').columns[0].show()
	            	}
	            if(flag=='detail'){
	            	//禁用window组件
	            	var DetailUpPanel=Ext.getCmp('ClientBaseModifyAndDetailUpPanelId')
					eachReadOnly(DetailUpPanel,13,true)
	            	Ext.getCmp('lineTreeForClientBaseId').setVisible(false)
	           	}
			}
}




//设置form下的所有组件为ReadOnly
function eachReadOnly(form,length,isdisp){
    var i = 0;
    if(isdisp){
        for(i=0;i<length;i++){
            form.getForm().getFields().items[i].setReadOnly(true);
             form.getForm().getFields().items[i].addCls('readonly');
        }
    }else{
        for(i=0;i<length;i++){
            form.getForm().getFields().items[i].setReadOnly(false);
             form.getForm().getFields().items[i].removeCls('readonly');
        }
    }
}
function initTree(jsonTree){
	//勾选树
	Ext.getCmp('lineTreeForClientBaseId').getRootNode( ).cascadeBy(function(node){
 		for(var i=0;i<jsonTree.clientBase.lineDept.length;i++){
 			var arriveDeptCode=jsonTree.clientBase.lineDept[i].arriveDeptCode
	 		var leaveDeptCode=jsonTree.clientBase.lineDept[i].leaveDeptCode
 			var treeId=node.data.deptStandardCode
			if(treeId==arriveDeptCode ||treeId==leaveDeptCode){
				node.set('checked', true);
			}
 		}
	});
	//展开树
	var childNodes = Ext.getCmp('lineTreeForClientBaseId').getRootNode().childNodes
		for(var i=0;i<childNodes.length;i++){
			var path = childNodes[i].getPath();
			Ext.getCmp('lineTreeForClientBaseId').expandPath(path)
		
		}
}



//加载部门数据
function loadLineDeptTree(jsonTree){
	if(null==lineDeptTree && myMask==null){
		myMask = new Ext.LoadMask(Ext.getCmp("lineTreeForClientBaseId"), {msg:i18n('i18n.ClientBaseManeger.message.lineTreeLoading')});//线路部门树数据加载中.....
		myMask.show();
		 var successFn = function(json){
		 	lineDeptTree=json.deptTree;
		 	Ext.getCmp("lineTreeForClientBaseId").store.proxy.data=lineDeptTree
		 	Ext.getCmp("lineTreeForClientBaseId").store.load();
		 	//如果是修改操作
		 	if(null!=jsonTree){
		 		//初始化树
		 		initTree(jsonTree);
		 	}
		 	myMask.destroy()
		 	myMask=null;
		}
		 var failureFn = function(json){
		 	myMask.destroy()
		 	MessageUtil.showErrorMes(i18n('i18n.ClientBaseManeger.message.lineTreeLoading'));//加载线路部门树列表失败	
		}
		var param=null;
		ClientBaseStore.prototype.loadLineDeptTree(param,successFn,failureFn);
	}
}
