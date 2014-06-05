//查询条件全局变量，防止点击查询后再次修改查询条件造成刷新不一致
var searchParamsForWhole = null;
//记录在执行修改操作时是否修改过问题列表，YES表示操作过，NO表示未操作过
var haveOperateList = i18n('i18n.questionnaireManage.no');
//单击查看详情按钮问卷详情弹出DETAILMODE = DETAIL;单击问卷预览按钮问卷详情弹出 DETAILMODE = PREVIEW;
var detailMode = null;
//查看详情或者问卷预览时的问题id列表
var questionIdListForWhole = null;
//查看详情时的问卷id列表
var questionnaireIdListForWhole = null;
Ext.onReady(function(){
	//初始化提示条
    Ext.QuickTips.init();
	//加载数据字典
	var keys = [
	    //问卷状态
	    'SURVEY_STATUS',
	    //适用范围
	    'SURVEY_RANGE',
	    //问题类型
	    'QUESTION_TYPE'
	];
	//初始化数据字典
	initDataDictionary(keys);
	//初始化用户和部门信息
	initDeptAndUserInfo();
	/**
	 * 问卷管理查询主页面的按钮面板
	 * 2014-3-6
	 * 肖红叶
	 */
	Ext.define('QuestionnaireBtnPanel',{
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
				items:[{//查看详情
					  xtype:'button',
					  text:i18n('i18n.questionnaireManage.detail'),
					  hidden:!isPermission('/marketing/questionnaireManagementSelBtn.action'),
					  disabled:true,
					  id:'detailBtnId',
					  handler:function(btn){
						  var questionnaireDetailPopWindow = Ext.getCmp("questionnaireDetailPopWindowId");//获取win
							if(!questionnaireDetailPopWindow){
								questionnaireDetailPopWindow = Ext.create('QuestionnaireDetailPopWindow',{id:'questionnaireDetailPopWindowId'});
							}
						  //获得问卷查询结果列表
						  var grid = Ext.getCmp('questionnaireInfoListGridId');
						  //获得所选问卷记录
						  var selection = grid.getSelectionModel().getSelection();
						  //判断勾选的记录条数是否为一条
						  if(Ext.isEmpty(selection) || selection.length != 1){
							  MessageUtil.showErrorMes(i18n('i18n.scheduleManagement.selectOneRecord'));
						  }
						  //获得问卷基本信息面板
						  var questionnaireDetailForm = Ext.getCmp('questionnaireDetailFormId').getForm();
						  //重置查看详情页面
						  questionnaireDetailForm.reset();
						  Ext.getCmp('questionListGridId').store.removeAll();
						  questionnaireDetailPopWindow.show();
						  //加载问卷基本信息
						  questionnaireDetailForm.loadRecord(selection[0]);
						  //获得问卷id数组
						  var idArray = new Array();
						  idArray.push(selection[0].get("id"));
						  //为全局变量问卷id数组和问题id列表赋值
						  questionnaireIdListForWhole = idArray;
						  questionIdListForWhole = null;
						  //加载查看详情中的问题信息列表
						  Ext.getCmp('questionListGridId').store.load();
					  }
				  },{//新增
					  xtype:'button',
					  text:i18n('i18n.questionnaireManage.add'),
					  hidden:!isPermission('/marketing/questionnaireManagementAddBtn.action'),
					  handler:function(btn){
						  //弹出新增窗口
						  qustionnaireAddAndUpdPopWindow.show();
						  //重置新增窗口中的组件
						  resetAddAndUpdQuestionnairePanel(i18n('i18n.questionnaireManage.add'));
					  }
				  },{//修改
					  xtype:'button',
					  text:i18n('i18n.questionnaireManage.update'),
					  hidden:!isPermission('/marketing/questionnaireManagementUpdBtn.action'),
					  disabled:true,
					  id:'updateBtnId',
					  handler:function(btn){
						  //获得问卷查询结果列表
						  var grid = Ext.getCmp('questionnaireInfoListGridId');
						  //获得所选问卷记录
						  var selection = grid.getSelectionModel().getSelection();
						  //判断勾选的记录条数是否为一条
						  if(Ext.isEmpty(selection) || selection.length != 1){
							  MessageUtil.showErrorMes(i18n('i18n.scheduleManagement.selectOneRecord'));
						  }
						  //弹出修改窗口
						  qustionnaireAddAndUpdPopWindow.show();
						  //重置修改窗口中的组件
						  resetAddAndUpdQuestionnairePanel(i18n('i18n.questionnaireManage.update'));
						  
						  
					  }
				  },{//删除
					  xtype:'button',
					  text:i18n('i18n.questionnaireManage.delete'),
					  hidden:!isPermission('/marketing/questionnaireManagementDelBtn.action'),
					  disabled:true,
					  id:'deleteBtnId',
					  handler:function(btn){
						  //获得问卷查询结果列表
						  var grid = Ext.getCmp('questionnaireInfoListGridId');
						  //获得选中记录
						  var selection=grid.getSelectionModel().getSelection();
						  //判断勾选的记录条数是否为一条
						  if(Ext.isEmpty(selection) || selection.length != 1){
							  MessageUtil.showErrorMes(i18n('i18n.scheduleManagement.selectOneRecord'));
						  }
						  MessageUtil.showQuestionMes(i18n('i18n.questionnaireManage.sureToDeleteOrNot'), function(e) {
							if (e == 'yes') {
								  //禁用删除按钮
								  btn.disable();
								  //获得问卷id数组
								  var idArray = new Array();
								  idArray.push(selection[0].get("id"));
								  //获得选中记录的问卷状态
								  var questionnaireStatus = selection[0].get("status");
								  var successFn = function(json){
									  //启用删除按钮
									  btn.enable();
									  //删除成功
									  MessageUtil.showInfoMes(i18n('i18n.questionnaireManage.successToDelete'));
									  //刷新列表
									  Ext.getCmp('questionnaireInfoListGridId').store.loadPage(1);
								  }
								  var param = {
									  'questionnaireIds':idArray,
									  'questionnaireStatus':questionnaireStatus
								  };
								  var failureFn=function(json){
									  //启用删除按钮
									  btn.enable();
									  if(Ext.isEmpty(json)){
										  MessageUtil.showErrorMes(i18n('i18n.questionnaireManage.failToDelete'));
									  }
									  else{
										  MessageUtil.showErrorMes(json.message);  
									  }
								  }
								  QuestionnaireStore.prototype.deleteQuestionnaire(param,successFn,failureFn);
							}
						  });  
					  }
				  },{//查看问卷统计结果
					  xtype:'button',
					  text:i18n('i18n.questionnaireManage.viewStatistics'),
					  hidden:!isPermission('/marketing/questionnaireManagementSelBtn.action'),
					  disabled:true,
					  id:'statisticBtnId',
					  handler:function(t){
						  //获得问卷查询结果列表
						  var grid = Ext.getCmp('questionnaireInfoListGridId');
						  //获得所选问卷记录
						  var selection = grid.getSelectionModel().getSelection();
						  //判断勾选的记录条数是否为一条
						  if(Ext.isEmpty(selection) || selection.length != 1){
							  MessageUtil.showErrorMes(i18n('i18n.scheduleManagement.selectOneRecord'));
						  }
						  //获得查看问卷统计结果基本信息面板
						  var questionnaireStatisForm = Ext.getCmp('questionnaireStatisFormId').getForm();
						  //重置查看详情页面
						  questionnaireStatisForm.reset();
						  Ext.getCmp('questionListGridId').store.removeAll();
						  questionnaireStatisPopWindow.show();
						  //加载问卷基本信息
						  questionnaireStatisForm.loadRecord(selection[0]);
						  //设置回访开始时间
						  var startVisitTime =questionnaireStatisForm.findField('startVisitTime');
						  startVisitTime.setValue(selection[0].get('effectiveTime'));
						  startVisitTime.setMaxValue(selection[0].get('invalidTime'));
						  startVisitTime.setMinValue(selection[0].get('effectiveTime'));
						  //清除回访开始时间的无效提示
						  startVisitTime.clearInvalid();
						  //设置回访结束时间
						  var endVisitTime =questionnaireStatisForm.findField('endVisitTime');
						  endVisitTime.setValue(selection[0].get('invalidTime'));
						  endVisitTime.setMaxValue(selection[0].get('invalidTime'));
						  endVisitTime.setMinValue(selection[0].get('effectiveTime'));
						  //清除回访结束时间的无效提示
						  endVisitTime.clearInvalid();
						  //获得问卷id数组
						  var idArray = new Array();
						  idArray.push(selection[0].get("id"));
						  //为全局变量问卷id数组和问题id数组赋初值
						  questionnaireIdListForWhole = idArray;
						  questionIdListForWhole = null;
						  //获得回访开始时间
						  var staticStartDate =questionnaireStatisForm.findField('startVisitTime').getValue();
						  //获得回访结束时间
						  var staticEndDate =questionnaireStatisForm.findField('endVisitTime').getValue();
						  //加载查看详情中的问题信息列表
						  Ext.getCmp('statisticsQuestionListGridId').store.load({
							  params:{
								  //问卷id列表
								  'questionnaireIds':questionnaireIdListForWhole,
								  'staticStartDate':staticStartDate,
								  'staticEndDate':staticEndDate
							  }
						  });
					  }
				  }]
			   },{
				 xtype:'middlebuttonpanel' 
			   },{
				xtype : 'rightbuttonpanel',
				items : [{//查询
				    text:i18n('i18n.questionnaireManage.select'),
				    hidden:!isPermission('/marketing/questionnaireManagementSelBtn.action'),
				    xtype:'button',
				    handler:function(btn){
				    	//获得查询条件输入form
				    	var form = Ext.getCmp('questionnaireQueryPanelId').getForm();
				    	//开始创建时间
				    	var createStartDate = form.findField('createStartDate').getValue();
				    	//结束创建时间
				    	var createEndDate = form.findField('createEndDate').getValue();
				    	//开始有效时间
				    	var validStartDate = form.findField('validStartDate').getValue();
				    	//结束有效时间
				    	var validEndDate = form.findField('validEndDate').getValue();
				    	//创建时间范围与有效时间范围不能同时为空
				    	if(!((!(Ext.isEmpty(createStartDate)) && !(Ext.isEmpty(createEndDate))) ||
				    			(!(Ext.isEmpty(validStartDate)) && !(Ext.isEmpty(validEndDate))))){
				    		MessageUtil.showErrorMes(i18n('i18n.questionnaireManage.queryDateWrong'));
							return false;
				    	}
				    	//判断时间范围是否填写完整
				    	if(dateRangeNotWhole(createStartDate,createEndDate) || 
				    			dateRangeNotWhole(createEndDate,createStartDate)){
				    		MessageUtil.showErrorMes(i18n('i18n.questionnaireManage.wholeCreateTimeRange'));
							return false;
				    	}
				    	if(dateRangeNotWhole(validStartDate,validEndDate) || 
				    			dateRangeNotWhole(validEndDate,validStartDate)){
				    		MessageUtil.showErrorMes(i18n('i18n.questionnaireManage.wholeValidTimeRange'));
							return false;
				    	}
				    	//判断时间范围是否合法且时间范围间隔不超过90天
				    	if(dateRangeNotValid(createStartDate,createEndDate,89,'DAY')){
				    		MessageUtil.showErrorMes(i18n('i18n.questionnaireManage.wrongCreateTimeRange'));
							return false;
				    	}
				    	if(dateRangeNotValid(validStartDate,validEndDate,89,'DAY')){
				    		MessageUtil.showErrorMes(i18n('i18n.questionnaireManage.wrongValidTimeRange'));
							return false;
				    	}
				    	//查询条件面板存在校验不通过的条件，则不允许触发请求
				    	if(!form.isValid()){
				    		return false;
				    	}
				    	//为查询时的查询参数赋值
				    	searchParamsForWhole = null;
						searchParamsForWhole = queryConditionParam(form);
				    	Ext.getCmp('questionnaireInfoListGridId').store.loadPage(1);
				    }
				 },{//重置
					 xtype:'button',
					 text:i18n('i18n.questionnaireManage.reset'),
					 handler:function(){
						 //重置查询条件
						 Ext.getCmp('questionnaireQueryPanelId').getForm().reset();
					 }
				 }]
			   }];
		}
	});
	
	
	
	/**
	 * 新建一个viewport，用于问卷管理主界面
	 * 2014-3-6
	 * 肖红叶
	 */
	var viewport=Ext.create('Ext.Viewport',{
		layout : 'border',
		items:[
	        {//问卷管理页面中查询条件输入面板form
	        	xtype:'container',
				region:'north',
				layout:'fit',
				items:[Ext.create('QuestionnaireQueryPanel',{id:'questionnaireQueryPanelId'})]
			},{
				xtype:'container',
				region:'center',
				layout:'border',
				items:[
					{//问卷管理按钮面板
						xtype:'container',
						region:'north',
					    layout:'fit',
					    items:[Ext.create('QuestionnaireBtnPanel')]
					},{//问卷管理查询结果表格
						xtype:'container',
						region:'center',
			    	    layout:'fit',
			    	    items:[ Ext.create('QuestionnaireInfoListGrid',{
			    	    	id:'questionnaireInfoListGridId',
			    	    	selModel : Ext.create('Ext.selection.CheckboxModel',{
			    	    		mode:'SINGLE',
			    	    		allowDeselect:true,
			    	    		listeners:{
			    	    			select:function(th,record,index){
			    	    				if(Ext.isEmpty(record) || Ext.isEmpty(record.data) || 
			    	    						Ext.isEmpty(record.data.id)){
			    	    					//禁用查看详情、修改、删除、查看问卷统计结果四个按钮
			    	    					controlBtn(0,record.data.status);
			    	    				}
			    	    				else{
			    	    					//启用查看详情、查看问卷统计结果按钮,控制修改、删除两个按钮
			    	    					controlBtn(1,record.data.status);
			    	    				}
			    	    			},
			    	    			deselect:function(th,record,index){
			    	    				//禁用查看详情、修改、删除、查看问卷统计结果四个按钮
			    	    				controlBtn(0,record.data.status);
			    	    			}
			    	    		}
			    	    	})
			    	    })]
			        }
			   ]
			}
	    ]
	});
	
	viewport.setAutoScroll(false);	
	viewport.doLayout();
	
/**--------------------------问卷新增与修改window 开始---------------------------------*/
	/**
	 * 问卷新增修改查询条件Form
	 * 肖红叶
	 * 20140307
	 */
    Ext.define('SearchConditionForm',{
		extend:'SearchFormPanel', 
		items:null,
		border:0,
		layout:{
			type:'table',
			columns:3
		},
		defaults:{
			labelWidth:60,
			labelAlign:'right',
			width:200,
			margin:'0 5 5 0'
		},
		defaultType:'datefield',
		initComponent:function(){			
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [{//问题标题
				xtype:'textfield',
		    	fieldLabel : i18n('i18n.questionManage.searchPanel.questionTitle'),
				name:'questionTitle',
				maxLength : 100,
				maxLengthText : i18n('i18n.questionManage.message.questionTitleExceed')
		    },{//适用范围
		    	xtype:'combobox',
		    	fieldLabel:i18n('i18n.questionManage.searchPanel.questionRange'),
		    	//剔出朦层
    			listConfig: {
      		        loadMask:false
    			},
		    	name:'useRange',
				queryModel:'local',
				store:getDataDictionaryByName(DataDictionary,'SURVEY_RANGE'),
				displayField:'codeDesc',
				valueField:'code',
				forceSelection :true,
				listeners:{
					change:DButil.comboSelsct
				}
			},{//问题类型
		    	xtype:'combobox',
		    	fieldLabel:i18n('i18n.questionManage.searchPanel.questionType'),
		    	//剔出朦层
    			listConfig: {
      		        loadMask:false
    			},
		    	name:'questionType',
				queryModel:'local',
				store:getDataDictionaryByName(DataDictionary,'QUESTION_TYPE'),
				displayField:'codeDesc',
				valueField:'code',
				forceSelection :true,
				listeners:{
					change:DButil.comboSelsct
				}
			},{//开始创建时间
		    	fieldLabel:i18n('i18n.questionManage.searchPanel.beginOfCreatDate'),
		    	format:'Y-m-d',
				name:'createStartTime',
				//设置起始时间的初始值为当前月份的第一天
				value : Ext.Date.add(new Date(),Ext.Date.DAY,-89),
				maxValue:new Date()
			},{//结束创建时间
		    	fieldLabel:i18n('i18n.questionManage.searchPanel.endOfCreatDate'),
		    	format:'Y-m-d',
		    	maxValue:new Date(),
		    	//为结束时间赋初值
		    	value:new Date(),
				name:'createEndTime'
			}];
		}	
	});
    
    /**
	 * 问卷管理新增修改页面查询、重置按钮面板
	 * 2014-3-6
	 * 肖红叶
	 */
	Ext.define('QuestionnaireAddAndUpadBtnPanel',{
		extend:'NormalButtonPanel',
		border:false,
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();		
		},
		getItems:function(){
			var me = this;
			return [{
				 xtype:'middlebuttonpanel' 
			   },{
				xtype : 'rightbuttonpanel',
				items : [{//查询
				    text:i18n('i18n.questionnaireManage.select'),
				    xtype:'button',
				    handler:function(btn){
				    	//获得查询条件的form
				    	var searchConditionForm = Ext.getCmp("searchConditionFormId").getForm();
				    	//查询条件面板存在校验不通过的条件，则不允许触发请求
				    	if(!searchConditionForm.isValid()){
				    		return false;
				    	}
				    	var createStartDate = searchConditionForm.findField('createStartTime').getValue();
                    	var createEndDate = searchConditionForm.findField('createEndTime').getValue();
                    	if(dateRangeNotValid(createStartDate,createEndDate,89,'DAY')){
				    		MessageUtil.showErrorMes(i18n('i18n.questionnaireManage.wrongCreateTimeRange'));
							return false;
				    	}
				    	Ext.getCmp('searchResultGridId').store.loadPage(1);
				    }
				 },{//重置
					    text:i18n('i18n.questionnaireManage.reset'),
					    xtype:'button',
					    handler:function(btn){
					    	Ext.getCmp('searchConditionFormId').getForm().reset();
					    }
				 }]
			   }];
		}
	});
	
	/**
  	 * 问卷管理新增修改页面问题移动按钮
  	 * 肖红叶
  	 * 20140307
  	 */
  	Ext.define('MoveRecordsBtnPanel',{
  		extend :'PopRightButtonPanel',
  		width : '100%',
  		buttonAlign : 'center',
  		layout : 'column',
  		items : [{// 添加所选问题
  			columnWidth : 1,
  			xtype : 'container',
  			style : 'padding-top:60px;border:none',
  			width : '100%',
  			items : [{
  				xtype : 'button',
  				text : '&gt;',
  				width : 39,
  				// 添加所选问题
  				handler : function(){
  					//获得选中的问题
  					var selection=Ext.getCmp("searchResultGridId").getSelectionModel().getSelection();
  					//获得已选问题列表的store
  					var chooseStore=Ext.getCmp("searchRightGridId").store;
  					//获得选中问题的条数
  					var length = selection.length;
  					if(length > 0){
  						//是否操作过已选问题列表变量设值为YES
  						haveOperateList = i18n('i18n.questionnaireManage.yes');
  					}
  					for(var i = 0;i < selection.length;i++){//遍历所选问题
				    	if(!Ext.isEmpty(chooseStore.getById(selection[i].get("id")))){//判断是否有重复
						    MessageUtil.showErrorMes(i18n('i18n.questionnaireManage.repeatQuestionOne')+selection[i].get("questionTitle")
						    		+i18n('i18n.questionnaireManage.repeatQuestionTwo'));
						    return false;
					    }else{
					    	//添加到已选择问题store里
					    	if(chooseStore.data.length < 20){
					    		Ext.getCmp("searchResultGridId").getSelectionModel().deselect(selection[i]);
						    	Ext.getCmp("searchResultGridId").store.remove(selection[i]);
					    		chooseStore.insert(i,selection[i]);
					    	}else{
					    		MessageUtil.showErrorMes(i18n('i18n.questionnaireManage.canNotExceedTwenty'));
							    return false;
					    	}
					    }
				    	//刷新已选择问题列表中的问题编号
	  					refreshQuestionnaireSeq(chooseStore);
  					}
  					//刷新已选择问题列表中的问题编号
  					refreshQuestionnaireSeq(chooseStore);
  					
  				}
  			}]
  		},{//移除所选问题
  			columnWidth : 1,
  			width : '100%',
  			xtype : 'container',
  			style : 'padding-top:60px;border:none',
  			items : [{
  				xtype : 'button',
  				text : '&lt;',
  				width : 39,
  				//移除所选问题
  				handler : function(){
  					//得到已选问题列表中的问题
  					var selection=Ext.getCmp("searchRightGridId").getSelectionModel().getSelection();
  					//待选择问题store
  					var store=Ext.getCmp("searchResultGridId").store;
  					if(selection.length > 0){
  						//是否操作过已选问题列表变量设值为YES
  						haveOperateList = i18n('i18n.questionnaireManage.yes');
  					}
  					for(var j = 0;j < selection.length;j++){//遍历所选问题
  						if(!Ext.isEmpty(store.getById(selection[j].get("id")))){//判断是否有重复
						    Ext.getCmp("searchRightGridId").getSelectionModel().deselect(selection[j]);
	  						Ext.getCmp("searchRightGridId").store.remove(selection[j]);
					    }else{
					    	Ext.getCmp("searchRightGridId").getSelectionModel().deselect(selection[j]);
	  						Ext.getCmp("searchRightGridId").store.remove(selection[j]);
	  						//添加到已选择客户store里
	  						store.insert(j,selection[j]);
					    }
  					}
  					if(Ext.getCmp("searchRightGridId").store.getCount()==0){
  						Ext.getCmp("searchRightGridId").getSelectionModel().deselectAll();
  					}
  					//获得已选择问题列表的store
  					var chooseStore = Ext.getCmp("searchRightGridId").store;
  					//刷新已选择问题列表中的问题编号
  					refreshQuestionnaireSeq(chooseStore);
  				}
  			}]
  		}]
  	});
    
    /**
     * 问卷管理新增修改底部formpanel
     * @author  肖红叶
     * @date    2012-03-13
     */
    Ext.define('SaveQuestionnairePanel',{
        extend:'TitleFormPanel',  
        items:null,
        initComponent:function(){
            this.items = this.getItems(); 
            this.callParent();
        },
        getItems:function(){
            var me = this;
            return [{//问卷信息
                xtype:'basicfiledset', 
                title:i18n('i18n.questionnaireManage.questionnaireInfo'),
                layout:{
                    type:'table',
                    columns:2
                },
                defaultType:'datefield',
                defaults:{
                    labelWidth : 65,
                    labelAlign: 'right',
                    width : 250
                },
                items:[{//问卷名称
                	xtype:'textfield',
                    name : 'questionnaireName',
                    fieldLabel:i18n('i18n.questionnaireManage.questionnaireName'),
                    allowBlank: false,
                    maxLength : 20,
                    maxLengthText : i18n('i18n.developPlan.maxLength')+20
                },{//适用范围
    		    	xtype:'combobox',
    		    	fieldLabel:i18n('i18n.questionManage.searchPanel.questionRange'),
    		    	//剔出朦层
        			listConfig: {
          		        loadMask:false
        			},
    		    	name:'useRange',
    				queryModel:'local',
    				store:getDataDictionaryByName(DataDictionary,'SURVEY_RANGE'),
    				displayField:'codeDesc',
    				valueField:'code',
    				value : 'VISIT',
    				forceSelection :true,
    				listeners:{
    					change:DButil.comboSelsct
    				}
    			},{//生效时间
    		    	fieldLabel:i18n('i18n.questionnaireManage.effectiveTime'),
    		    	format:'Y-m-d',
    				name:'effectiveTime',
    		    	minValue:Ext.Date.add(new Date(),Ext.Date.DAY,1),
    				//设置起始时间的初始值为当前日期的第二天
    				value:Ext.Date.add(new Date(),Ext.Date.DAY,1)
    			},{//失效时间
    		    	fieldLabel:i18n('i18n.questionnaireManage.invalidTime'),
    		    	format:'Y-m-d',
    		    	minValue:Ext.Date.add(new Date(),Ext.Date.DAY,2),
    		    	//为结束时间赋初值
    		    	value:Ext.Date.add(new Date(),Ext.Date.DAY,30),
    				name:'invalidTime'
    			},{//问卷描述
                	xtype:'textarea',
                	maxLength:500,
                    width:700,
                    name : 'desc',
                    fieldLabel: i18n('i18n.questionnaireManage.questionnaireDesc'),
                    height:50,
                    colspan : 2,
                    maxLength : 300,
                    maxLengthText : i18n('i18n.developPlan.maxLength')+300
                },{//隐藏域，保存问卷id
                	xtype:'hiddenfield',
                	name:'id'
                },{//隐藏域，保存问卷状态
                	xtype:'hiddenfield',
                	name:'status'
                }]      
            }];
        }
    });
    
    /**
  	 * 问卷管理预览、生成问卷、重置、关闭按钮
  	 */
  	Ext.define('DownButtonPanel',{
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
  				items : [{//问卷预览
  					xtype : 'button',
  					text : i18n('i18n.questionnaireManage.preSee'),
  					handler : function(btn){
  						//获得已选问题列表的store
  						var chooseStore = Ext.getCmp('searchRightGridId').store;
  						if(chooseStore.data.length > 20 || chooseStore.data.length < 2){
  							MessageUtil.showErrorMes(i18n('i18n.questionnaireManage.wrongQuestionNum'));
  							return false;
  						}
  						//获得问卷基本信息输入form
  						var saveQuestionnairePanel = Ext.getCmp('saveQuestionnairePanelId').getForm();
  						if(!saveQuestionnairePanel.isValid()){
  							return false;
  						}
  						//重置问卷预览页面
  						//获得问卷基本信息面板
						var questionnaireDetailForm = Ext.getCmp('questionnaireDetailFormId').getForm();
						questionnaireDetailForm.reset();
//						Ext.getCmp('questionListGridId').store.removeAll();
						detailMode = 'preview';
  						questionnaireDetailPopWindow.show();
						
						questionnaireDetailForm.findField('questionnaireName').setValue(
								saveQuestionnairePanel.findField('questionnaireName').getValue());
						questionnaireDetailForm.findField('useRange').setValue(
								saveQuestionnairePanel.findField('useRange').getValue());
						questionnaireDetailForm.findField('effectiveTime').setValue(
								saveQuestionnairePanel.findField('effectiveTime').getValue());
						questionnaireDetailForm.findField('invalidTime').setValue(
								saveQuestionnairePanel.findField('invalidTime').getValue());
						questionnaireDetailForm.findField('desc').setValue(
								saveQuestionnairePanel.findField('desc').getValue());
						//问题id列表
						questionIdListForWhole = new Array();
						//问卷id列表
						questionnaireIdListForWhole = null;
					    Ext.getCmp('searchRightGridId').store.each(function(record){
					    	questionIdListForWhole.push(record.data.id);
					    });
					    Ext.getCmp('questionListGridId').store.removeAll();
						Ext.getCmp('questionListGridId').store.load();
						
  					}
  				},{//生成问卷
  					xtype : 'button',
  					text : i18n('i18n.questionnaireManage.createQuestionnaire'),
  					handler : function(btn){
  						//获得已选问题列表的store
  						var chooseStore = Ext.getCmp('searchRightGridId').store;
  						if(chooseStore.data.length > 20 || chooseStore.data.length < 2){
  							MessageUtil.showErrorMes(i18n('i18n.questionnaireManage.wrongQuestionNum'));
  							return false;
  						}
  						//获得问卷基本信息输入form
  						var saveQuestionnairePanel = Ext.getCmp('saveQuestionnairePanelId').getForm();
  						if(!saveQuestionnairePanel.isValid()){
  							return false;
  						}
  						MessageUtil.showQuestionMes(i18n('i18n.questionnaireManage.sureToCreateQuestionnaire'), function(e) {
							if (e == 'yes') {
								//禁用生成问卷按钮
		  						btn.disable();
		  						//执行生成问卷操作
								var successFn = function(json){
									//启用生成问卷按钮
									btn.enable();
									//问卷id为空时为新增问卷
								    if(Ext.isEmpty(saveQuestionnairePanel.findField('id').getValue())){
								    	MessageUtil.showInfoMes(i18n('i18n.questionnaireManage.successToCreateQuestionnaire'));
								    	//刷新问卷查询结果列表
								    	Ext.getCmp('questionnaireInfoListGridId').store.loadPage(1);
								    	//重置新增页面
										resetAddAndUpdQuestionnairePanel(i18n('i18n.questionnaireManage.add'));
										qustionnaireAddAndUpdPopWindow.hide();
								    }
								    else{
								    	MessageUtil.showInfoMes(i18n('i18n.questionnaireManage.successToUpdateQuestionnaire'));
								    	//关闭修改页面
								    	qustionnaireAddAndUpdPopWindow.hide();
								    	//刷新问卷查询结果列表
								    	Ext.getCmp('questionnaireInfoListGridId').store.loadPage(1);
								    }
									
								}
								
								var failureFn = function(json){
									//启用生成问卷按钮
									btn.enable();
									if(Ext.isEmpty(json)){
										MessageUtil.showErrorMes(i18n('i18n.questionnaireManage.failToCreateQuestionnaire'));
									}else{
										MessageUtil.showErrorMes(json.message);
									}
							    };
								
								//问题信息列表
							    var questionMapperList = null;
							    if(haveOperateList === i18n('i18n.questionnaireManage.yes')){
							    	questionMapperList = new Array();
								    Ext.getCmp('searchRightGridId').store.each(function(record){
								    	var questionMapperModel=new QuestionMapperModel();
								    	questionMapperModel.set("questionId",record.data.id);
								    	questionMapperModel.set("questionSeq",record.data.questionSeq);
								    	questionMapperList.push(questionMapperModel.data);
								    });
							    }
							    var param = {
							    	//问卷基本信息实体
						    		'questionnaireInfo':{
						    			'questionnaireName':saveQuestionnairePanel.findField('questionnaireName').getValue(),
								    	'effectiveTime':saveQuestionnairePanel.findField('effectiveTime').getValue(),
								    	'invalidTime':saveQuestionnairePanel.findField('invalidTime').getValue(),
								    	'useRange':saveQuestionnairePanel.findField('useRange').getValue(),
								    	'desc':saveQuestionnairePanel.findField('desc').getValue(),
								    	'id':saveQuestionnairePanel.findField('id').getValue(),
								    	'status':saveQuestionnairePanel.findField('status').getValue()
						    		},
						    		//问题信息列表
						    		'questionMapperList':questionMapperList,
					    			//是否操作过问题列表，主要在问卷更新时使用
					    			'haveOperateList':haveOperateList
							    };
							    //问卷id为空时为新增问卷
							    if(Ext.isEmpty(saveQuestionnairePanel.findField('id').getValue())){
							    	QuestionnaireStore.prototype.createQuestionnaire(param, successFn, failureFn);
							    }
							    //问卷id不为空时为修改问卷
							    else{
							    	QuestionnaireStore.prototype.updateQuestionnaire(param, successFn, failureFn);
							    }
							}
  						});
  					}
  				},{//重置
  					xtype : 'button',
  					text : i18n('i18n.questionnaireManage.reset'),
  					width : 70,
  					handler : function(){
  						//获得问卷基本信息输入form
  						var saveQuestionnairePanel = Ext.getCmp('saveQuestionnairePanelId').getForm();
  						saveQuestionnairePanel.reset();
						//问卷id为空时为新增问卷
//					    if(Ext.isEmpty(saveQuestionnairePanel.findField('id').getValue())){
//					    	//重置新增窗口中的组件
//							//resetAddAndUpdQuestionnairePanel(i18n('i18n.questionnaireManage.add'));
//					    }
//					    else{
//					    	//重置修改窗口中的组件
//							//resetAddAndUpdQuestionnairePanel(i18n('i18n.questionnaireManage.update'));
//					    }
  					}
  				},{//关闭
  					xtype : 'button',
  					text : i18n('i18n.questionnaireManage.close'),
  					width : 70,
  					handler : function(){
  						//关闭页面
				    	qustionnaireAddAndUpdPopWindow.hide();
  					}
  				}]
  			}];
  		}
  	});
    
  	/**.
  	 * 问卷新增、修改主Panel
  	 * 肖红叶
  	 * 20130307
  	 */
  	Ext.define('QustionnaireAddAndUpdPlanePanel',{
  		extend:'BasicPanel',
  		searchCondition:null, //查询条件From
  		searchLeftResult:null, //待选问题列表（左边Grid）
  		searchRightResult:null, //已选问题列表（右边Grid）
  		downQuestionnaireformPanel:null,//底部问卷formPanel
  		items:null,
  		layout:'border',
  		initComponent:function(){
  			var me = this;
  			//查询条件From
  			me.searchCondition = Ext.create('SearchConditionForm',{id:'searchConditionFormId'});  			
  			//查询问题列表store
  			var store=Ext.create('QuestionGridStore');
  			store.on('beforeload',function(store,operation,e){
  				var searchConditionForm = Ext.getCmp("searchConditionFormId").getForm();
  				//设置请求参数
  				var searchParams = { 
  	  					//问题标题
  	  					'questionSearchCondition.questionTitle':searchConditionForm.findField('questionTitle').getValue(),
  	  					//问题适用范围
  	  					'questionSearchCondition.useRange':searchConditionForm.findField('useRange').getValue(),
  	  					//问题类型
  	  					'questionSearchCondition.questionType':searchConditionForm.findField('questionType').getValue(),
  	  					//问题创建时间
  	  					'questionSearchCondition.createStartDate':searchConditionForm.findField('createStartTime').getValue(),
  	  					'questionSearchCondition.createEndDate':searchConditionForm.findField('createEndTime').getValue(),
  	  				};
  				Ext.apply(operation,{
  					params : searchParams
  				});
  			});
  			var selModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SIMPLE'});
  			//待选问题列表Grid
  			me.searchLeftResult =  Ext.create('TitleGridPanel',{
  				title:i18n('i18n.questionnaireManage.waitToBeSelected'),
  				id:'searchResultGridId',
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
  					cls:'pagingtoolbar',
  					store:store,
  					dock:'bottom',
  					displayInfo : true,
  					autoScroll : true,
  					items:[{
  						text: i18n('i18n.authorization.roleGrid.page_count'),
  						xtype: 'tbtext'
  					},Ext.create('Ext.form.ComboBox', {
  		               width:          window.screen.availWidth*0.0391,
  		               triggerAction:  'all',
  		               forceSelection: true,
  		               value:'10',
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
  			//已选择问题列表selModel
  			var chooseSelModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SIMPLE'});
  			//已选择问题列表store
  			var chooseStore=Ext.create('ChooseQuestionGridStore');
  			//已选择问题列表grid
  			var searchRightGrid=Ext.create('TitleGridPanel',{
  				title:i18n('i18n.questionnaireManage.selectedQuestionList'),
  				id:'searchRightGridId',
  				store:chooseStore,
  				viewConfig: {
  					//声明需要使用的拖拽插件
  			        plugins: {
  			            ptype: 'gridviewdragdrop',
  			            dragText: i18n('i18n.questionnaireManage.dragQuestionToSort')
  			        },
  			        //可拖动插件
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
  				columns:[{//序号
  	  				width:40,
  	  				dataIndex:'questionSeq',
  	  				header:i18n('i18n.questionnaireManage.rownumberer')
  	  			},{//问题标题
  	  				header : i18n('i18n.questionManage.searchPanel.questionTitle'),
  	  				width:150,
  	  				dataIndex : 'questionTitle',
	  	  			renderer: function (val, meta, record) {
		  	  			if(!Ext.isEmpty(val)){
							var content = val;
							if(val.length >= 10){
								val=val.substr(0,9)+'...';
							}else{
								val= val;
							} 
							return '<a title="'+content+'">'+val+'</a>';
						}
					}
  	  			},{//问题类型
	  	  	    	header:i18n('i18n.questionManage.searchPanel.questionType'),
	  	            dataIndex: 'questionType',
	  	            width:80,
	  	            renderer : function(value){
	  	                if(value==null||value==''){
	  	                    return i18n('i18n.developSchedule.noMeaning');
	  	                }else{
	  	                    return DButil.rendererDictionary(value,DataDictionary.QUESTION_TYPE);
	  	                }
	  	            },
  	  		  },{//采用次数
  	  				header : i18n('i18n.questionManage.resultGrid.frequency'),
  	  				width:80,
  	  				dataIndex : 'frequency'
  	  			},{//创建人
  	  				header : i18n('i18n.questionManage.resultGrid.creatorName'),
  	  				width:80,
  	  				dataIndex : 'createUser'
  	  			},{//创建时间
  	  				header : i18n('i18n.questionManage.resultGrid.creatorDate'),
  	  				width:100,
  	  				dataIndex : 'createDate',
  	 	            renderer : function(value){
  	 	            	if(!Ext.isEmpty(value)){
  	 	            		return DButil.renderDate(value);
  	 	            	}
  		            }
  	  			}, {//最后修改人
  	  				header : i18n('i18n.questionManage.resultGrid.modifierName'),
  	  				width:100,
  	  				dataIndex : 'modifyUser'
  	  			},{//最后修改时间
  	  				header : i18n('i18n.questionManage.resultGrid.modifyDate'),
  	  				dataIndex : 'modifyDate',
  	  				width:100,
  	 	            renderer : function(value){
  	 	            	if(!Ext.isEmpty(value)){
  	 	            		return DButil.renderDate(value);
  	 	            	}
  		            }
  	  			}],
  				selModel:chooseSelModel,
  				dockedItems:[{
  					xtype:'displayfield',
  					dock:'bottom',
  					displayInfo : true,
  					autoScroll : true,
  					value:i18n('i18n.questionnaireManage.warnTipsToDrag')
  				}]
  			});
  			//在拖拽操作完成后，调用drop方法，完成序号的重排
			Ext.getCmp('searchRightGridId').getView().addListener('drop',function(node,data,model){
				var store = Ext.getCmp('searchRightGridId').store;
				//刷新已选择问题列表中的问题编号
				refreshQuestionnaireSeq(store);
			});
  			me.searchRightResult =  searchRightGrid;
  			me.downQuestionnaireformPanel = Ext.create('SaveQuestionnairePanel',{id:'saveQuestionnairePanelId'});//制定计划
//  			Ext.getCmp("developPlanFromPanel").getForm().findField("execuserid").store.load();//加载执行人DetailPanel
  			
//  			store.on('load',function(store,records){
//  				var girdcount=0;
//		        store.each(function(record){
//		            if(!Ext.isEmpty(chooseStore.getById(record.get("id")))){
//						var cells =  Ext.getCmp('searchResultGridId').getView().getNodes()[girdcount].children;
//						for(var i= 0;i<cells.length;i++){
//							cells[i].style.backgroundColor='#FF9797';
//						};
//		 			};	
//		 			girdcount=girdcount+1;
//		        });
//  			});
  			
  			//设置items
  			me.items = me.getItems();
  			this.callParent();
  		},
  		getItems:function(){//整体布局
  			var me = this;
  			return [{
  				region:'north',
  				xtype:'basicpanel',
  				height:110,
  				layout:'border',
  				items:[{
  					region:'center',
  					xtype:'basicpanel',
  					layout:'fit',
  					items:[me.searchCondition]
  				},Ext.create('QuestionnaireAddAndUpadBtnPanel',{
  					region:'south',
  					height:40
  				})]//border布局下面查询按钮
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
  								items:[Ext.create('MoveRecordsBtnPanel')] 
  							}
  						]
  					},{
  						region:'east',
  						xtype:'basicpanel',
  						layout:'fit',
  						width:220,
  						items:[me.searchRightResult]
  					}]
  				},{
  					region:'south',
  					xtype:'basicpanel',
  					layout:'border',
  					height:180,
  					items:[{
  						region:'center',
  						xtype:'basicpanel',
  						layout:'fit',
  						items:[me.downQuestionnaireformPanel]
  					},
  					Ext.create('DownButtonPanel')]//border布局下面问卷预览、生成问卷、重置、关闭按钮
  				}]
  			}];
  		},
  		getColumns:function(){//查询结果列
  			return [{//问题标题
  				header : i18n('i18n.questionManage.searchPanel.questionTitle'),
  				width:150,
  				dataIndex : 'questionTitle'
  			},{//问题类型
  		    	header:i18n('i18n.questionManage.searchPanel.questionType'),
  	            dataIndex: 'questionType',
  	            width:80,
  	            renderer : function(value){
  	                if(value==null||value==''){
  	                    return i18n('i18n.developSchedule.noMeaning');
  	                }else{
  	                    return DButil.rendererDictionary(value,DataDictionary.QUESTION_TYPE);
  	                }
  	            },
  	        },{//采用次数
  				header : i18n('i18n.questionManage.resultGrid.frequency'),
  				width:80,
  				dataIndex : 'frequency'
  			},{//创建人
  				header : i18n('i18n.questionManage.resultGrid.creatorName'),
  				width:80,
  				dataIndex : 'createUser'
  			},{//创建时间
  				header : i18n('i18n.questionManage.resultGrid.creatorDate'),
  				width:100,
  				dataIndex : 'createDate',
 	            renderer : function(value){
 	            	if(!Ext.isEmpty(value)){
 	            		return DButil.renderDate(value);
 	            	}
	            }
  			}, {//最后修改人
  				header : i18n('i18n.questionManage.resultGrid.modifierName'),
  				width:100,
  				dataIndex : 'modifyUser'
  			},{//最后修改时间
  				header : i18n('i18n.questionManage.resultGrid.modifyDate'),
  				dataIndex : 'modifyDate',
  				width:100,
 	            renderer : function(value){
 	            	if(!Ext.isEmpty(value)){
 	            		return DButil.renderDate(value);
 	            	}
	            }
  			}]
  		}
  	});
  	
	/**
	 * 问卷管理新增、修改显示的window
	 * 肖红叶
	 * 20140307
	 */
	Ext.define('QustionnaireAddAndUpdPopWindow',{
		extend:'PopWindow',
		alias : 'widget.basicwindow',
		width:820,
		height:700,
		modal:true,
		layout:'fit',
		title:i18n('i18n.questionnaireManage.questionnaireAddTitle'),
		closeAction:'hide',
		items:[Ext.create('QustionnaireAddAndUpdPlanePanel')],
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
	var qustionnaireAddAndUpdPopWindow = Ext.getCmp("qustionnaireAddAndUpdPopWindowId");//获取win
	if(!qustionnaireAddAndUpdPopWindow){
		qustionnaireAddAndUpdPopWindow = Ext.create('QustionnaireAddAndUpdPopWindow',{id:'qustionnaireAddAndUpdPopWindowId'});
	}
	/**--------------------------问卷新增与修改window 结束---------------------------------*/
	
	/**--------------------------问卷查看详情window 开始------------------------------------*/
	/**
	 * 问卷查看详情显示Form
	 * 肖红叶
	 * 20140307
	 */
    Ext.define('QuestionnaireDetailForm',{
		extend:'NoTitleFormPanel', 
		items:null,
		border:0,
		layout:{
			type:'table',
			columns:3
		},
		defaults:{
			labelWidth:60,
			labelAlign:'right',
			width:200,
			margin:'0 5 5 0'
		},
		defaultType:'datefield',
		initComponent:function(){			
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [{//问卷编号
				xtype:'readonlytextfield',
		    	fieldLabel : i18n('i18n.questionnaireManage.questionnaireCode'),
				name:'questionnaireCode'
		    },{//问卷名称
				xtype:'readonlytextfield',
		    	fieldLabel : i18n('i18n.questionnaireManage.questionnaireName'),
				name:'questionnaireName'
		    },{//适用范围
				xtype:'combobox',
				fieldLabel:i18n('i18n.questionManage.searchPanel.questionRange'),
				name:'useRange',
				cls:'readonly',
				typeAhead: true,
			    triggerAction: 'all',
			    queryMode: 'local',
			    store: getDataDictionaryByName(DataDictionary,'SURVEY_RANGE'),
			    valueField: 'code',
			    displayField: 'codeDesc',
				forceSelection :true,
                allowBlank: false,
				listeners:{
					change:DButil.comboSelsct
				},
				readOnly:true
			},{//生效时间
		    	fieldLabel:i18n('i18n.questionnaireManage.effectiveTime'),
		    	format:'Y-m-d',
				name:'effectiveTime',
				//设置起始时间的初始值为当前月份的第一天
				readOnly:true,
				cls:'readonly'
			},{//失效时间
		    	fieldLabel:i18n('i18n.questionnaireManage.invalidTime'),
		    	format:'Y-m-d',
				name:'invalidTime',
				readOnly:true,
				cls:'readonly'
			},{//采用次数
				xtype:'readonlytextfield',
		    	fieldLabel : i18n('i18n.questionManage.resultGrid.frequency'),
				name:'useTimes',
				value:'0'
		    },{//问卷描述
            	xtype:'readonlytextarea',
                width:700,
                name : 'desc',
                fieldLabel: i18n('i18n.questionnaireManage.questionnaireDesc'),
                height:50,
                colspan : 3
            }];
		}	
	});
    
    /**
	 * 问卷管理查看详情问题列表
	 * 2014-3-6
	 * 肖红叶
	 */
	Ext.define('QuestionListGrid',{
		extend:'PopupGridPanel',   
		columns:null,
		store:null,
		defaults:{
			align:'center'
		},
		initComponent:function(){             
			var me = this;
			var store = Ext.create('QuestionInfoListStore');
			store.on('beforeLoad',function(store,operation,e){
			    var param = {
			    	//问题id列表
		    		'questionIdList':questionIdListForWhole,
		    		//问卷id列表
		    		'questionnaireIds':questionnaireIdListForWhole
			    };
				Ext.apply(operation,{
					params : param
				});
			});
			me.store = store;
			me.columns = [{//问卷id 
				header : i18n('i18n.questionnaireManage.questionList'),
				flex:1,
				dataIndex:'id',
				renderer:function(val,metaData,record){
					//判断是不是在渲染表格记录的第一行
					var timeFirst = true;
					//问题类型转化
					var questionType = optionInfoConvert(record.get('questionType'));
					//根据问题类型显示适当的表单符号
					var optionDisplay = optionDisplayConvert(record.get('questionType'));
					//问卷预览时设置问题序号
					if(detailMode == i18n('i18n.questionnaireManage.questionnairePreview') || timeFirst){
						var totalLength = record.store.data.length;
						for(var i = 0;i < totalLength;i++){
							var data = record.store.data.items[i].data;
							data.questionSeq = i+1;
						}
						timeFirst = false;
					}
					if(!Ext.isEmpty(record)){
						//单选题与多选题的显示样式转化
						if(record.get('questionType') != i18n('i18n.questionnaireManage.questionTypeOfAnswer')){
							//记录有多少个选项
							var length = 0;
							if(!Ext.isEmpty(record.optionsStore) && !Ext.isEmpty(record.optionsStore.data)){
								length = record.optionsStore.data.length;
							}
							var tableString = '<div align = "left"><table>';
							for(var i = 0;i < length;i++){
								var optionsData = record.optionsStore.data.items[i].data;
								var optionSeq = optionCodeConvert(optionsData.optionSeq);
								var optionDes = optionsData.optionDes;
								var j = optionDes.length;
								var showString = "";
								while(j > 50){
									showString=showString+optionDes.substring(0,50)+'<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
									optionDes = optionDes.substring(50,j);
									j =j - 50;
								}
								showString = showString + optionDes;
								tableString =tableString + '<tr><td height="25">&nbsp;&nbsp;'+optionDisplay+'&nbsp;&nbsp;'+optionSeq+'&nbsp;&nbsp;'+showString
								+'</td></tr>';
							}
							tableString = tableString + '</table></div>';
						}
						else{//简答题的显示方式
							var tableString = '<br><div align = "left">&nbsp;&nbsp;&nbsp;&nbsp;<textarea style="width:550px; height:50px; border:solid 1px #000;" readonly="readonly"></textarea></div>';
						}
					}
					//格式化结果显示
					var questionTitle =record.get('questionTitle');
					var k = questionTitle.length;
					var title = "";//问题标题临时变量
					while(k > 50){
						title=title+questionTitle.substring(0,50)+'<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
						questionTitle = questionTitle.substring(50,k);
						k =k - 50;
					}
					title = title + questionTitle;
					return	Ext.String.format(
						'<br><p><b>{0}、{1}({2})</b></p><br><br>'+tableString+'<br><br>',
						//传入的参数列表
						record.get('questionSeq'),
						title,
						questionType
					);
				}
			}];
			this.callParent();
		}
	});
    
    /**
	 * 问卷管理查看详情显示的window
	 * 肖红叶
	 * 20140307
	 */
	Ext.define('QuestionnaireDetailPopWindow',{
		extend:'PopWindow',
		alias : 'widget.basicwindow',
		width:820,
		height:700,
		modal:true,
		layout:'border',
		title:i18n('i18n.questionnaireManage.questionnaireDetail'),
		closeAction:'hide',
		items:[Ext.create('QuestionnaireDetailForm',{
			id:'questionnaireDetailFormId',
			region:'north',
			height:120
		}),Ext.create('QuestionListGrid',{
			id:'questionListGridId',
			region:'center'
		})],
		buttons:[{//关闭
			text:i18n('i18n.questionnaireManage.close'),
			handler:function(){
				questionnaireDetailPopWindow.hide();
			}
		}],
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
	var questionnaireDetailPopWindow = Ext.getCmp("questionnaireDetailPopWindowId");//获取win
	if(!questionnaireDetailPopWindow){
		questionnaireDetailPopWindow = Ext.create('QuestionnaireDetailPopWindow',{id:'questionnaireDetailPopWindowId'});
	}
	/**--------------------------问卷查看详情window 结束------------------------------------*/
	
	/**--------------------------问卷查看问卷统计结果window 开始------------------------------------*/
	/**
	 * 问卷查看问卷统计结果显示Form
	 * 肖红叶
	 * 20140307
	 */
    Ext.define('QuestionnaireStatisForm',{
		extend:'SearchFormPanel', 
		items:null,
		border:0,
		layout:{
			type:'table',
			columns:3
		},
		defaults:{
			labelWidth:60,
			labelAlign:'right',
			width:200,
			margin:'0 5 5 0'
		},
		defaultType:'datefield',
		initComponent:function(){			
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [{//问卷编号
				xtype:'readonlytextfield',
		    	fieldLabel : i18n('i18n.questionnaireManage.questionnaireCode'),
				name:'questionnaireCode'
		    },{//问卷名称
				xtype:'readonlytextfield',
		    	fieldLabel : i18n('i18n.questionnaireManage.questionnaireName'),
				name:'questionnaireName'
		    },{//适用范围
				xtype:'combobox',
				fieldLabel:i18n('i18n.questionManage.searchPanel.questionRange'),
				name:'useRange',
				cls:'readonly',
				typeAhead: true,
			    triggerAction: 'all',
			    queryMode: 'local',
			    store: getDataDictionaryByName(DataDictionary,'SURVEY_RANGE'),
			    valueField: 'code',
			    displayField: 'codeDesc'
			},{//生效时间
		    	fieldLabel:i18n('i18n.questionnaireManage.effectiveTime'),
		    	format:'Y-m-d',
				name:'effectiveTime',
				readOnly:true,
				cls:'readonly'
			},{//失效时间
		    	fieldLabel:i18n('i18n.questionnaireManage.invalidTime'),
		    	format:'Y-m-d',
				name:'invalidTime',
				readOnly:true,
				cls:'readonly'
			},{//采用次数
				xtype:'readonlytextfield',
		    	fieldLabel : i18n('i18n.questionManage.resultGrid.frequency'),
				name:'useTimes'
		    },{//问卷描述
            	xtype:'readonlytextarea',
                width:700,
                name : 'desc',
                fieldLabel: i18n('i18n.questionnaireManage.questionnaireDesc'),
                height:50,
                colspan : 3
            },{//开始回访时间
		    	fieldLabel:i18n('i18n.questionnaireManage.returnVisitDate'),
		    	format:'Y-m-d',
				name:'startVisitTime'
			},{//结束回访时间
		    	fieldLabel:i18n('i18n.questionManage.searchPanel.endOfCreatDate'),
		    	format:'Y-m-d',
				name:'endVisitTime'
			},{//隐藏域，保存问卷id
				xtype:'hiddenfield',
				name:'id'
			}];
		}	
	});
    
    /**
	 * 问卷管理查看统计结果页面查询、重置按钮面板
	 * 2014-3-6
	 * 肖红叶
	 */
	Ext.define('QuestionnaireStatisBtnPanel',{
		extend:'NormalButtonPanel',
		border:false,
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();		
		},
		getItems:function(){
			var me = this;
			return [{
				 xtype:'middlebuttonpanel' 
			   },{
				xtype : 'rightbuttonpanel',
				items : [{//查询
				    text:i18n('i18n.questionnaireManage.select'),
				    xtype:'button',
				    handler:function(btn){
				    	//获得问卷id数组
				    	var questionnaireStatisForm = Ext.getCmp('questionnaireStatisFormId').getForm();
				    	var idArray = new Array();
				    	idArray.push(questionnaireStatisForm.findField("id").getValue());
				    	questionnaireIdListForWhole = idArray;
				    	//加载查看详情中的问题信息列表
						Ext.getCmp('statisticsQuestionListGridId').store.removeAll();
						//获得回访开始时间
						var staticStartDate =questionnaireStatisForm.findField('startVisitTime').getValue();
					    //获得回访结束时间
					    var staticEndDate =questionnaireStatisForm.findField('endVisitTime').getValue();
						//加载查看详情中的问题信息列表
						Ext.getCmp('statisticsQuestionListGridId').store.load({
							params:{
								//问卷id列表
								'questionnaireIds':questionnaireIdListForWhole,
								'staticStartDate':staticStartDate,
								'staticEndDate':staticEndDate
							}
						});
				    }
				 },{//重置
					    text:i18n('i18n.questionnaireManage.reset'),
					    xtype:'button',
					    handler:function(btn){
					    	var questionnaireStatisForm = Ext.getCmp('questionnaireStatisFormId').getForm();
					    	var startVisitTime = questionnaireStatisForm.findField('startVisitTime');
					    	startVisitTime.setValue(startVisitTime.minValue);
					    	var endVisitTime = questionnaireStatisForm.findField('endVisitTime');
					    	endVisitTime.setValue(endVisitTime.maxValue);
					    }
				 }]
			   }];
		}
	});
	
	/**
	 * 查看问卷统计结果问题列表
	 * 2014-3-21
	 * 肖红叶
	 */
	Ext.define('StatisticsQuestionListGrid',{
		extend:'PopupGridPanel',   
		columns:null,
		store:null,
		defaults:{
			align:'center'
		},
		initComponent:function(){             
			var me = this;
			var store = Ext.create('StatisticsQuestionInfoListStore');
			store.on('load',function(store){
				store.each(function(record){
					//受访人数不等于零时显示饼图
					if(record.data.respondents > 0){
						var questionnaireId = record.data.id;
						var pieId = 'pie' + questionnaireId;
						if(!Ext.isEmpty(document.getElementById(pieId))
								&& !Ext.isEmpty(record.optionsStore)){
							var length = record.optionsStore.data.length;
							record.optionsStore.each(function(optionRecord){
								if(optionRecord.data.percentage == 0){
									record.optionsStore.remove(optionRecord);
								}
							});
							
							if(record.optionsStore.data.length > 0){
								Ext.create('ProportionPieChart',{
									store:record.optionsStore,
									renderTo:pieId
								});
							}
						}
					}
				});
			});
			me.store = store;
			me.columns = [{//问卷id 
				header : '',
				flex:1,
				dataIndex:'id',
				renderer:function(val,metaData,record){
					//问题类型转化
					var questionType = optionInfoConvert(record.get('questionType'));
					//根据问题类型显示适当的表单符号
					var optionDisplay = optionDisplayConvert(record.get('questionType'));
					//问题对应饼图的id
					var pieId = 'pie'+record.get('id');
					if(!Ext.isEmpty(record)){
						//单选题与多选题的显示样式转化
						if(record.get('questionType') != i18n('i18n.questionnaireManage.questionTypeOfAnswer')){
							//记录有多少个选项
							var length = 0;
							if(!Ext.isEmpty(record.optionsStore) && !Ext.isEmpty(record.optionsStore.data)){
								length = record.optionsStore.data.length;
							}
							var tableString = '<div align = "left"><table border="1" cellspacing="0" bordercolor="#333">';
							tableString = tableString + '<tr><td align="center" bgcolor="#373C64" height="25" style="color:#ffffff"><b>';
							tableString = tableString +i18n('i18n.questionnaireManage.questionOption')+'</b></td><td align="center" bgcolor="#373C64" style="color:#ffffff"><b>';
							tableString = tableString +i18n('i18n.questionnaireManage.replySituation')+'</b></td>';
							tableString = tableString + '<td width="100" rowspan="' +(length+1)+'"><div id="'+pieId+'"></div></td></tr>';
							for(var i = 0;i < length;i++){
								var optionsData = record.optionsStore.data.items[i].data
								var optionSeq = optionCodeConvert(optionsData.optionSeq);
								tableString = tableString + '<tr><td height="25"  width="300">&nbsp;&nbsp;'+optionSeq+'&nbsp;&nbsp;&nbsp;&nbsp;'+optionDisplay;
								var optionDes = optionsData.optionDes;
								var j = optionDes.length;
								while(j > 20){
									tableString=tableString+optionDes.substring(0,20)+'<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
									optionDes = optionDes.substring(20,j);
									j =j - 20;
								}
								tableString = tableString + optionDes;
								tableString=tableString+'</td><td width="100">'+((optionsData.percentage) * 100).toFixed(2) + '%</td></tr>';
							}
							tableString = tableString + '<tr><td height="25" width="300" colspan="3"><b>受访人数：'+record.get('respondents')+'人</b></td></tr></table></div>';
						}
					}
					//格式化结果显示
					var questionTitle =record.get('questionTitle');
					var k = questionTitle.length;
					var title = "";//问题标题临时变量
					while(k > 50){
						title=title+questionTitle.substring(0,50)+'<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
						questionTitle = questionTitle.substring(50,k);
						k =k - 50;
					}
					title = title + questionTitle;
					//格式化结果显示
					return	Ext.String.format(
						'<br><p><b>{0}、{1}({2})</b></p><br><br>'+tableString+'<br><br>',
						//传入的参数列表
						record.get('questionSeq'),
						title,
						questionType
					);
				}
			}];
			this.callParent();
		}
	});
	/**
	 * 问卷管理查看统计结果显示的饼图
	 * 肖红叶
	 * 20140307
	 */
	Ext.define('ProportionPieChart', {
		extend:'Ext.chart.Chart',
	    width: 100,
	    height: 100,
	    animate: true,
	    store: null,
	    theme: 'Base:gradients',
	    series: [{
	        type: 'pie',
	        angleField: 'percentage',
	        showInLegend: true,
	        tips: {
	            trackMouse: true,
	            width: 140,
	            height: 28,
	            renderer: function(storeItem, item) {
	            	//根据问题选项编号转换英文显示
	            	var result = optionCodeConvert(storeItem.get('optionSeq'));
	                this.setTitle(result + ': ' + (storeItem.get('percentage') * 100).toFixed(2) + '%');
	            }
	        },
	        highlight: {
	            segment: {
	                margin: 20
	            }
	        },
	        label: {
	            field: 'optionSeq',
	            display: 'rotate',
	            contrast: true,
	            font: '12px Arial',
	            renderer:function(val){
	            	//根据问题选项编号转换英文显示
	            	var result = optionCodeConvert(val);
	            	return result;
	            }
	        }
	    }]
	});
    /**
	 * 问卷管理查看统计结果显示的window
	 * 肖红叶
	 * 20140307
	 */
	Ext.define('QuestionnaireStatisPopWindow',{
		extend:'PopWindow',
		alias : 'widget.basicwindow',
		width:820,
		height:700,
		modal:true,
		layout:'border',
		title:i18n('i18n.questionnaireManage.questionnaireStatisticsResult'),
		closeAction:'hide',
		items:[Ext.create('StatisticsQuestionListGrid',{
				id:'statisticsQuestionListGridId',
				region:'center'
			}),{
			xtype:'container',
			region:'north',
			height:200,
			layout:'border',
			items:[Ext.create('QuestionnaireStatisBtnPanel',{
				region:'south',
				height:50
			}),Ext.create('QuestionnaireStatisForm',{
				id:'questionnaireStatisFormId',
				region:'center'
			})]
		}],
		buttons:[{//导出统计结果
			text:i18n('i18n.questionnaireManage.exportStatisticsResult'),
			handler:function(btn){
				//判断是否可以进行导出操作
				if( Ext.getCmp('statisticsQuestionListGridId').store.data.length == 0){
					MessageUtil.showErrorMes(i18n('i18n.questionnaireManage.errorExportOperation'));
					return false;
				}
				//获得查看问卷统计结果基本信息面板
				var questionnaireStatisForm = Ext.getCmp('questionnaireStatisFormId').getForm();
				//问卷id
				var questionnaireId = questionnaireStatisForm.findField('id').getValue();
				//开始回访时间
				var staticStartDate = questionnaireStatisForm.findField('startVisitTime').getValue();
				//结束回访时间
				var staticEndDate = questionnaireStatisForm.findField('endVisitTime').getValue();
				//导出权限下全部数据功能实现区
				  //进度条
				  Ext.MessageBox.show({
						title:'导出问卷统计信息',
					    msg:'数据导出中，请稍候！',
					    wait:true
				  });
				  
				  var successFn = function(json){
					  if(json.canExport == '1'){
						 window.location.href = "../marketing/exportStaticsByQuestionnaireId.action?fileName="
							 +'staticQuestionnaire';
						 btn.enable();
						 //隐藏进度条
						 Ext.MessageBox.hide();
						 MessageUtil.showInfoMes('导出成功！');
					  }
					  else{
						 btn.enable();
						 //隐藏进度条
						 Ext.MessageBox.hide();
						 MessageUtil.showErrorMes('文件未导出成功！');
					  }
						 
				  }
				  var param = {
						'questionnaireId':questionnaireId,
						'staticStartDate':staticStartDate,
						'staticEndDate' : staticEndDate
				  };
				 var failureFn=function(){
					btn.enable();
					//隐藏进度条
					Ext.MessageBox.hide();
					MessageUtil.showErrorMes('导出失败');
				 }
				 QuestionnaireStore.prototype.createStaticsExcel(param,successFn,failureFn);
			}
		},{//导出详细结果
			text:i18n('i18n.questionnaireManage.exportDetailResult'),
			handler:function(btn){
				//判断是否可以进行导出操作
				if( Ext.getCmp('statisticsQuestionListGridId').store.data.length == 0){
					MessageUtil.showErrorMes(i18n('i18n.questionnaireManage.errorExportOperation'));
					return false;
				}
				//获得查看问卷统计结果基本信息面板
				var questionnaireStatisForm = Ext.getCmp('questionnaireStatisFormId').getForm();
				//问卷id
				var questionnaireId = questionnaireStatisForm.findField('id').getValue();
				//开始回访时间
				var staticStartDate = questionnaireStatisForm.findField('startVisitTime').getValue();
				//结束回访时间
				var staticEndDate = questionnaireStatisForm.findField('endVisitTime').getValue();
				//导出权限下全部数据功能实现区
				  //进度条
				  Ext.MessageBox.show({
						title:'导出问卷回访详情',
					    msg:'数据导出中，请稍候！',
					    wait:true
				  });
				  var successFn = function(json){
					  if(json.canExport == '1'){
						 window.location.href = "../marketing/exportQuestionnaireDetailExcel.action?fileName="
							 +'detailQuestionnaire';
						 btn.enable();
						 //隐藏进度条
						 Ext.MessageBox.hide();
						 MessageUtil.showInfoMes('导出成功！');
					  }
					  else{
						 btn.enable();
						 //隐藏进度条
						 Ext.MessageBox.hide();
						 MessageUtil.showErrorMes('文件未导出成功！');
					  }
				  }
				  var param = {
						'questionnaireId':questionnaireId,
						'staticStartDate':staticStartDate,
						'staticEndDate' : staticEndDate
				  };
				 var failureFn=function(){
					btn.enable();
					//隐藏进度条
					Ext.MessageBox.hide();
					MessageUtil.showErrorMes('导出失败');
				 }
				 QuestionnaireStore.prototype.createQuestionnaireDetailExcel(param,successFn,failureFn);
			}
		},{//关闭
			text:i18n('i18n.questionnaireManage.close'),
			handler:function(){
				questionnaireStatisPopWindow.hide();
			}
		}],
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
	var questionnaireStatisPopWindow = Ext.getCmp("questionnaireStatisPopWindowId");//获取win
	if(!questionnaireStatisPopWindow){
		questionnaireStatisPopWindow = Ext.create('QuestionnaireStatisPopWindow',{id:'questionnaireStatisPopWindowId'});
	}
	/**--------------------------问卷查看问卷统计结果window 结束------------------------------------*/
	
	/**
	 * 禁用、启用查看详情、修改、删除、查看问卷统计结果四个按钮
	 * order=1,启用；order=0,禁用；
	 * 肖红叶
	 * 20140307
	 */
	function controlBtn(order,status){
		if(order == 1){
			//根据问卷状态判断是否可以启用修改、删除按钮
			if(questionnaireStatusNotOperate(status)){
				Ext.getCmp('updateBtnId').disable();
				Ext.getCmp('deleteBtnId').disable();
			}
			else{
				Ext.getCmp('deleteBtnId').enable();
				Ext.getCmp('updateBtnId').enable();
			}
			//启用查看详情、查看问卷统计结果两个按钮
			Ext.getCmp('statisticBtnId').enable();
			Ext.getCmp('detailBtnId').enable();
		}
		else{
			//禁用查看详情、修改、删除、查看问卷统计结果四个按钮
			Ext.getCmp('detailBtnId').disable();
			Ext.getCmp('updateBtnId').disable();
			Ext.getCmp('statisticBtnId').disable();
			Ext.getCmp('deleteBtnId').disable();
		}
	}



	/**
	 * 校验问卷状态是否可以删除或者修改
	 * 肖红叶
	 * 20140311
	 * @param status
	 */
	function questionnaireStatusNotOperate(status){
		//问卷的状态为使用中或者已过期，则不允许修改删除
		var result = false;
		if(Ext.isEmpty(status) || status === i18n('i18n.questionnaireManage.using') || 
				status === i18n('i18n.questionnaireManage.overdue')){
			result = true;
		}
		return result;
	}
	
	/**
	 * 重置新增问卷和修改问卷的操作面板
	 * 肖红叶
	 * 20140311
	 */
	function resetAddAndUpdQuestionnairePanel(titleStr){
		Ext.getCmp('searchConditionFormId').getForm().reset();
		Ext.getCmp('searchResultGridId').store.removeAll();
		Ext.getCmp('searchRightGridId').store.removeAll();
		Ext.getCmp('saveQuestionnairePanelId').getForm().reset();
		//是否操作过已选问题列表变量设值为NO
		haveOperateList = i18n('i18n.questionnaireManage.no');
		//设置新增或修改窗口的标题
		qustionnaireAddAndUpdPopWindow.setTitle(i18n('i18n.questionnaireManage.questionnaire')+titleStr);
		if(titleStr === i18n('i18n.questionnaireManage.add')){
			//问卷名称可输入
		    var saveQuestionnairePanel = Ext.getCmp('saveQuestionnairePanelId').getForm();
		    saveQuestionnairePanel.findField('questionnaireName').enable();
		    //设置生效时间的最小可选值
		    var effectiveTime = saveQuestionnairePanel.findField('effectiveTime');
		    effectiveTime.setMinValue(Ext.Date.add(new Date(),Ext.Date.DAY,1)); 
		    //清除生效时间的无效提示
		    effectiveTime.clearInvalid();	
		}
		else if(titleStr === i18n('i18n.questionnaireManage.update')){
			  //获得问卷查询结果列表
			  var grid = Ext.getCmp('questionnaireInfoListGridId');
			  //获得所选问卷记录
			  var selection = grid.getSelectionModel().getSelection();
			  //获得已选问题列表
			  var searchRightGrid = Ext.getCmp('searchRightGridId');
			  //获得问卷id数组
			  var idArray = new Array();
			  idArray.push(selection[0].get("id"));
			  searchRightGrid.store.load({params:{'questionnaireIds':idArray}});
			  //获得问卷修改界面中问卷基本信息panel
			  var saveQuestionnairePanel = Ext.getCmp('saveQuestionnairePanelId').getForm();
			  //加载问卷修改界面中问卷基本信息
			  saveQuestionnairePanel.loadRecord(selection[0]);
			  //设置问卷名称不可修改
			  saveQuestionnairePanel.findField('questionnaireName').disable();
			  //设置生效时间的最小可选值
			  var effectiveTime = saveQuestionnairePanel.findField('effectiveTime');
			  if(DButil.compareTwoDate(new Date(),effectiveTime.getValue()) <= 0){
				  effectiveTime.setMinValue(effectiveTime.getValue()); 
			  }
			  else{
				  effectiveTime.setMinValue(Ext.Date.add(new Date(),Ext.Date.DAY,1)); 
			  }
			  //清除生效时间的无效提示
			  effectiveTime.clearInvalid();
		}
	}

	/**
	 * 刷新已选择问题列表中的问题编号
	 * 肖红叶
	 * 20140312
	 */
	function refreshQuestionnaireSeq(chooseStore){
		for(var i = 0;i < chooseStore.data.length;i++){
			chooseStore.getAt(i).set("questionSeq",i+1); 
			chooseStore.getAt(i).commit();
		}
	}
});