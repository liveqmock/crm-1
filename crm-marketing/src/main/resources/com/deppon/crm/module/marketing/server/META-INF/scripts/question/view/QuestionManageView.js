/**
 * @description 问题管理前端界面view
 * @author 盛诗庆
 * @date 2014-03-06
 * @revision none
 */ 
//创建查询结果grid
Ext.onReady(function(){  
	var keys=[
		'SURVEY_RANGE',
		'SURVEY_STATUS',
		'QUESTION_TYPE'
    ];
	//初始化业务参数
	initDataDictionary(keys);
    initDeptAndUserInfo();
    //操作按钮面板
    Ext.define('ButtonPanel',{
        extend:'NormalButtonPanel', 
        items:null,
        region:'south',
        width:600,
        initComponent:function(){
            this.items = this.getItems();
            this.callParent();
        },
        getItems:function(){
            var me = this;
            return [{
                xtype:'leftbuttonpanel',
                items:[{//查看问题详情按钮
                	xtype:'button',
                	id : 'viewQuestionBtnId',
                	disabled : true,
                	text:i18n('i18n.questionManage.searchButton.obtainDetail'),
                	hidden:!isPermission('/marketing/questionManageSBtn.action'),
                    handler:function(){
                        var grid = Ext.getCmp('questionGirdId');
                        var selection=grid.getSelectionModel().getSelection();
                        //仅限于选择一行进行查看详情操作
                        if (selection.length != 1) {
                            MessageUtil.showErrorMes(i18n('i18n.questionManage.message.chooseOne'));
                            return false;
                        };
                        //创建一个window
                        var detailPopwin = Ext.getCmp('questionDetailPopWindowId');
                        if(!detailPopwin){
                        	detailPopwin = Ext.create('QuestionDetailPopWindow',{id : 'questionDetailPopWindowId'});
                        };
                        var form = Ext.getCmp('detailPanelId').getForm();
                        form.loadRecord(selection[0]);
                        Ext.getCmp('questionnaireInfoListGridId').getStore().removeAll();
                        var optionStore = Ext.getCmp('optionGridId').getStore();
                        optionStore.loadData(selection[0].data.options);
                        //获得问卷id数组
						var idArray = new Array();
						for(var i = 0;i < selection.length;i++){
							  idArray.push(selection[i].get("id"));
						 }
						var param = {
							'questionIds':idArray,
						};
						 var successFn = function(json){
							 // 加载引用该问题的问卷信息
							 Ext.getCmp('questionnaireInfoListGridId').getStore().loadData(json.questionnaireList);
						  }
						  var failureFn=function(json){
							  
						  }
						  QuestionStore.prototype.searchQuestionnaireByQuestionId(param,successFn,failureFn);
						  detailPopwin.show();                
                    }},{//新增问题按钮
		                    text:i18n('i18n.questionManage.searchButton.addQuestion'),
		                    xtype:'button',
		                    hidden:!isPermission('/marketing/questionManagABtn.action'),
		                    handler : function(){
		                    	var optionAddWindow = Ext.getCmp('questionAddPopWindowId'); 
		                    	if(!optionAddWindow){
		                    		optionAddWindow = Ext.create('QuestionModifyPopWindow',{id : 'questionAddPopWindowId'});
		                         };
		                         optionAddWindow.setTitle(i18n('i18n.questionManage.title.addQuestion'));//设置window标题
		                         optionAddWindow.flag = "add";//创建的window为新增窗口
		                         //重置window中grid和form
		                         //获取modifyPanel
		                         optionAddWindow.down('panel').getForm().findField('questionTitle').reset();
		                         optionAddWindow.down('panel').getForm().findField('useRange').reset();
		                         optionAddWindow.down('panel').getForm().findField('questionType').reset();
		                         optionAddWindow.down('panel').getForm().findField('questionContent').reset();
		                         optionAddWindow.down('panel').getForm().findField('id').reset();
		                         Ext.getCmp('elseOption').reset();
		                         Ext.getCmp('optionAddGridId').getStore().removeAll();
		                         //启用按钮
		                         optionAddWindow.down('grid').enable();
			                	 Ext.getCmp('addQuestionOptionBtn').enable();
			                	 Ext.getCmp('elseOption').enable();
			                	 //允许修改标题
			                	 optionAddWindow.down('panel').getForm().findField('questionTitle').setReadOnly(false);
			                	 //向store中默认添加两条数据
			                	 var panelModel = Ext.getCmp('optionAddGridId').store.model.modelName;
			                	 var store = Ext.getCmp('optionAddGridId').getStore();
			                	 store.add(Ext.create(panelModel));
			                	 store.add(Ext.create(panelModel));
		                         optionAddWindow.show();
		                    }          
                },{//修改问题按钮
                    text:i18n('i18n.questionManage.searchButton.modifyQuestion'),
                    xtype:'button',
                    disabled : true,
                    id : 'modifyQuestionBtnId',
                    hidden:!isPermission('/marketing/questionManagUBtn.action'),
                    handler : function(){
                    	var optionModifyWindow = Ext.getCmp('questionAddPopWindowId'); 
                    	if(!optionModifyWindow){
                    		optionModifyWindow = Ext.create('QuestionModifyPopWindow',{id : 'questionAddPopWindowId'});
                         };
                         optionModifyWindow.setTitle(i18n('i18n.questionManage.title.updateQuestion'));//设置window标题
                         optionModifyWindow.flag = "update";//创建的window为修改窗口
                         //获取所选择的记录
                         var grid = Ext.getCmp('questionGirdId');
                         var selection=grid.getSelectionModel().getSelection();
                         //被引用问题不允许修改
                         if(selection[0].get('frequency')>0){
                        	 MessageUtil.showErrorMes(i18n('i18n.questionManage.errorMessage.questionOccupiedUpdate'));
                        	 return false;
                         }
                         //仅限于选择一行进行查看详情操作
                         if (selection.length != 1) {
                             MessageUtil.showErrorMes(i18n('i18n.questionManage.message.chooseOne'));
                             return false;
                         }; 
                         //重置window中grid和form
                         optionModifyWindow.down('panel').getForm().findField('questionTitle').reset();
                         optionModifyWindow.down('panel').getForm().findField('useRange').reset();
                         optionModifyWindow.down('panel').getForm().findField('questionType').reset();
                         optionModifyWindow.down('panel').getForm().findField('questionContent').reset();
                         Ext.getCmp('elseOption').reset();
                         optionModifyWindow.down('grid').getStore().removeAll();
                         //加载选中问题的数据
                         var form = optionModifyWindow.down('panel').getForm();
                         form.loadRecord(selection[0]);
                         if(selection[0].data.questionType == 'QUESTION_ANSWER'){//如果是简答题，禁用选项面板
                        	 	//清空grid数据并禁用
                        	 	optionModifyWindow.down('grid').disable();
		                		//禁用按钮
		                		Ext.getCmp('deleteQuestionOptionBtn').disable();
		                		Ext.getCmp('addQuestionOptionBtn').disable();
		                		//清空选型并禁用选择框
		                		Ext.getCmp('elseOption').reset();
		                		Ext.getCmp('elseOption').disable();
                         }else{
                        	//获取问题是否允许其他
                             var elseOption = selection[0].data.elseOption;
                             if(elseOption == '1'){//允许其他
                            	 Ext.getCmp('elseOption').setValue(true);
                             }
                             var optionStore = optionModifyWindow.down('grid').getStore();
                             optionStore.loadData(selection[0].data.options);
                             //遍历删除store其他选项，描述为其他的都删除
                             optionStore.each(function(record){
                            	 if(record.get('optionDes') == i18n('i18n.questionManage.searchPanel.elseOption')){
                            		 optionStore.remove(record);
                            	 }
                             })
                             //启用按钮
                             optionModifyWindow.down('grid').enable();
                             if(Ext.getCmp('elseOption') && optionStore.getCount() <= 5){
          	                	 Ext.getCmp('addQuestionOptionBtn').enable();
                             }else if(optionStore.getCount() <=6){
                            	 Ext.getCmp('addQuestionOptionBtn').enable();
                             }
    	                	 Ext.getCmp('elseOption').enable();
                         }
                         optionModifyWindow.show();
                    }       
                },{//删除问题按钮
                    text:i18n('i18n.questionManage.searchButton.deleteQuestion'),
                    xtype:'button',
                    disabled : true,
                    id : 'deleteQuestionBtnId',
                    hidden:!isPermission('/marketing/questionManageDBtn.action'),
                    handler:function(btn){
                    	var grid = Ext.getCmp('questionGirdId');
					  //获得选中记录
					  var selection=grid.getSelectionModel().getSelection();
					  //被引用问题不允许删除
	                  if(selection[0].get('frequency')>0){
	                   MessageUtil.showErrorMes(i18n('i18n.questionManage.errorMessage.questionOccupiedDelete'));
	                   return false;
	                  }
					  //一次只允许删除一个问题
					  if(selection.length > 1){
						  MessageUtil.showErrorMes('i18n.questionManage.message.chooseOne');
					  }
						  MessageUtil.showQuestionMes(i18n('i18n.questionManage.message.areYouSureToDeleteThisQuestion'), function(e) {
								if (e == 'yes') {
									  //禁用删除按钮
									  btn.disable();
									  //获得问题查询结果列表
									  
									  //获得问卷id数组
									  var idArray = new Array();
									  for(var i = 0;i < selection.length;i++){
										  idArray.push(selection[i].get("id"));
									  }
									  var successFn = function(json){
										  //启用删除按钮
										  btn.enable();
										  if(json.questionnaireList != null && json.questionnaireList != ''){//问题被引用，弹出窗口显示引用的问卷详情
											  MessageUtil.showErrorMes(i18n('i18n.questionManage.message.questionIsOccupiedDelete'));
										  }else{
											  MessageUtil.showInfoMes(i18n('i18n.questionnaireManage.successToDelete'));
											  Ext.getCmp('questionGirdId').store.loadPage(1);//刷新列表
										  }										  
									  }
									  var param = {
											  'questionIds':idArray,
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
									  QuestionStore.prototype.deleteQuestion(param,successFn,failureFn);
								}
						  });
					  }
                }]
            },{
                xtype:'middlebuttonpanel' 
            },{
                xtype : 'rightbuttonpanel',
                items : [{//查询按钮
                    xtype : 'button',
                    text : i18n('i18n.questionManage.searchButton.searchQuestion'),
                    handler : function(){
                    	var form = Ext.getCmp('questionSearchFormId');
                    	if(!(form.getForm().isValid())){
                    		return false;
                    	}
                    	var createStartDate = form.getForm().findField('createStartTime').getValue();
                    	var createEndDate = form.getForm().findField('createEndTime').getValue();
                    	if(dateRangeNotValid(createStartDate,createEndDate,89)){
				    		MessageUtil.showErrorMes(i18n('i18n.questionnaireManage.wrongCreateTimeRange'));
							return false;
				    	}
                    	Ext.data.StoreManager.lookup('questionGridStoreId').loadPage(1);
                    }
                },{//重置按钮
                	xtype : 'button',
                	text : i18n('i18n.questionnaireManage.reset'),
                	handler : function(){
                		var form = Ext.getCmp('questionSearchFormId').getForm().reset();
                	}
                }],   
            }];
        }
    });
    /**
     *定义整个界面布局 
     */
    Ext.define('QuestionManagePanel',{
        extend:'BasicPanel',
        layout:'border',
        items:null,
        buttonBar:null,
        initComponent:function(){
            var me = this;
            //界面布局模块
            me.items =[{
                xtype:'basicpanel',
                height:115,
                region:'north',
                layout:'border',
                items:[{
                    region:'center',
                    xtype:'basicpanel',
                    layout:'fit',
                    items:[Ext.create('QuestionSearchForm')]
                },
                Ext.create('ButtonPanel')]
            },{
                xtype:'basicpanel',
                region:'center',
                layout:'fit',
                items:[Ext.create('QuestionGird')]
            }];
            //添加双击事件
			Ext.getCmp('questionGirdId').on('itemdblclick',function(th,record,item,index,e,eOpts){
				 var grid = Ext.getCmp('questionGirdId');
                 var detailPopwin = Ext.getCmp('questionDetailPopWindowId');
                 if(!detailPopwin){
                 	detailPopwin = Ext.create('QuestionDetailPopWindow',{id : 'questionDetailPopWindowId'});
                 };
                 var form = Ext.getCmp('detailPanelId').getForm();
                 form.loadRecord(record);
                 var optionStore = Ext.getCmp('optionGridId').getStore();
                 optionStore.loadData(record.data.options);
                 //获得问卷id数组
				var idArray = new Array();
				idArray.push(record.get("id"));
				var param = {
						  'questionIds':idArray,
				  };
				var successFn = function(json){
					 // 加载引用该问题的问卷信息
					 Ext.getCmp('questionnaireInfoListGridId').getStore().loadData(json.questionnaireList);
			  }
				var failureFn=function(json){
					  
				  }
				  QuestionStore.prototype.searchQuestionnaireByQuestionId(param,successFn,failureFn);
                 detailPopwin.show();       
			});
            this.callParent();
        }
    });
	/**
	 * @description 问题选项panel
	 * @author 盛诗庆
	 * @date 2014-03-07
	 * @revision none
	 */
	Ext.define('OptionGridPanel',{
		extend:'BasicVboxPanel',
		items : null,
		initComponent : function() {
			this.items = this.getItems();
			this.callParent();
		},
		getItems : function() {
			var me = this;
			return [{
				xtype:'toppanel',   
				items:[{
					xtype:'titlepanel',  
					flex:0.5,
					items:[{//选项
						xtype:'displayfield',
						value:i18n('i18n.questionManage.resultGrid.optionListTitle')
				       }
			        ]
					},{
						xtype : 'titlepanel',
						flex : 1,
						items :[{//是否允许填空
							xtype : 'checkboxfield',
							id : 'elseOption',
							boxLabel :'<span style="color:red;">' + i18n('i18n.questionManage.message.elseOption') + '</span>',
							listeners : {
								change : function(th,newValue,oldValue){
									//获取store中选项数量
									var count = Ext.getCmp('optionAddGridId').getStore().getCount();
									if(oldValue && count <= 1){//取消选中
										Ext.getCmp('deleteQuestionOptionBtn').disable();
									}else if(newValue && count > 1){
										Ext.getCmp('deleteQuestionOptionBtn').enable();
									}
								}
							}
				        }]
					},{
						xtype:'btnpanel',  
						defaultType:'button',
						flex : 0.5,
						items:[
							{//增加
								text:i18n('i18n.questionManage.searchButton.addQuestion'),
								id:'addQuestionOptionBtn',
								disabled : true,
								handler:function(){	
									var me = this;
									var panelStore = Ext.getCmp('optionAddGridId').getStore();
									var elseOption = Ext.getCmp('elseOption').getValue();
									if(elseOption && panelStore.data.length>=5){
										MessageUtil.showErrorMes(i18n('i18n.questionManage.searchPanel.lessThanFive'));
										return false;
									}else if(!elseOption && panelStore.data.length>=6 ){
										MessageUtil.showErrorMes(i18n('i18n.questionManage.searchPanel.lessThanSix'));
										return false;
									}
				               	 	var panelModel = Ext.getCmp('optionAddGridId').store.model.modelName;
				               	 	panelStore.add(Ext.create(panelModel));
				               	 	var count = panelStore.getCount();
				               	 	if(elseOption && count >= 5){
			            				me.disable();
			            			}else if (count >= 6){
			            				me.disable();
			            			}
								}
							},{//删除
								text:i18n('i18n.questionManage.searchButton.deleteQuestion'),
								id:'deleteQuestionOptionBtn',
								disabled : true,
								handler:function(){
									var me = this;
									var addBtn = Ext.getCmp('addQuestionOptionBtn');
									var selection = Ext.getCmp('optionAddGridId').getSelectionModel().getSelection()[0];;
						            var panelStore = Ext.getCmp('optionAddGridId').getStore();
						            if (selection) {
						            	MessageUtil.showQuestionMes(i18n('i18n.developSchedule.isdelete'), function(e) {
						            		if (e == 'yes') {
						            			addBtn.enable();
						            			panelStore.remove(selection);
						            			var count = panelStore.getCount();
						            			var elseOption = Ext.getCmp('elseOption');
						            			if(elseOption && count <= 1){
						            				me.disable();
						            			}else if (count <= 2){
						            				me.disable();
						            			}
						            		}
						            	});
						            }
						            else{
						            	MessageUtil.showErrorMes(i18n('i18n.questionManage.message.chooseOne'));
										return false;
						            }
								}
							}
					  ]
					}
				] 
			}
		  ];
		}
	});
	/**
	 * @description 查询页面列表grid,问题管理界面查询结果
	 * @author 盛诗庆
	 * @date 2014-03-06
	 * @revision none
	 */
	Ext.define('QuestionGird', {
	    extend:'SearchGridPanel',
	    id:'questionGirdId',
	    cls:'market',
	    columnLines:true,
	    plugins:null,
	    selModel : Ext.create('Ext.selection.CheckboxModel',{
			mode:'SINGLE',
			allowDeselect:true,
			listeners:{
				select : function(th,record){
					controlBtn(record);
				},
				deselect : function(th,record){
					controlBtn(null);
				}
			}
	    }),
	    columns: [{//序号
	    	xtype:'rownumberer',
			width:40,
			align:'center',
			header:i18n('i18n.questionManage.resultGrid.sequence')
	    },{//问题标题
	         dataIndex: 'questionTitle',
	         flex:1,
	         renderer:function(val){
	        	 if(!Ext.isEmpty(val)){
	        		 return '<a title="'+val+'">'+val+'</a>';
	        	 }
	         },
	         header:i18n('i18n.questionManage.resultGrid.questionTitle')
	    },{//适用范围
	    	header:i18n('i18n.questionManage.searchPanel.questionRange'),
	    	width:80,
	    	dataIndex:'useRange',
	    	renderer : function(value){
                if(value==null||value==''){
                    return i18n('i18n.developSchedule.noMeaning');
                }else{
                    return DButil.rendererDictionary(value,DataDictionary.SURVEY_RANGE);
                }
            },
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
		     dataIndex: 'frequency',
		     width:80,
		     header:i18n('i18n.questionManage.resultGrid.frequency')
	    },{//创建人
	        dataIndex: 'createUser',
	        width:100,
	        header:i18n('i18n.questionManage.resultGrid.creatorName')
	    },{//创建时间
	        dataIndex: 'createDate',
	        width:120,
	        renderer : DButil.renderDate,
	        header:i18n('i18n.questionManage.resultGrid.creatorDate')
	    },{//最后修改人
	        dataIndex: 'modifyUser',
	        width:100,
	        header:i18n('i18n.questionManage.resultGrid.modifierName')
	    },{//最后修改时间
	        dataIndex: 'modifyDate',
	        width:120,
	        renderer : DButil.renderDate,
	        header:i18n('i18n.questionManage.resultGrid.modifyDate')
	    }],
	    viewConfig: { 
	        forceFit:true 
	    }, 
	    initComponent:function(){
	        var me = this;
	        var store = Ext.create('QuestionGridStore',{id : 'questionGridStoreId'})
	        me.store = store;
  			store.on('beforeload',function(store,operation,e){
  				var questionSearchForm = Ext.getCmp("questionSearchFormId");
  				//问题标题
  				var questionTitle = questionSearchForm.getForm().findField('questionTitle').getValue();
  				//设置请求参数
  				var searchParams = { 
  	  					//问题标题
  	  					'questionSearchCondition.questionTitle':Ext.String.trim(questionTitle),
  	  					//问题适用范围
  	  					'questionSearchCondition.useRange':questionSearchForm.getForm().findField('useRange').getValue(),
  	  					//问题类型
  	  					'questionSearchCondition.questionType':questionSearchForm.getForm().findField('questionType').getValue(),
  	  					//问题创建事件
  	  					'questionSearchCondition.createStartDate':questionSearchForm.getForm().findField('createStartTime').getValue(),
  	  					'questionSearchCondition.createEndDate':questionSearchForm.getForm().findField('createEndTime').getValue(),
  	  				};
  				Ext.apply(operation,{
  					params : searchParams
  				});
  			});
	        this.dockedItems=[{
	            xtype:'pagingtoolbar',
	            store:me.store,
	            dock:'bottom',
	            displayInfo : true,
	            autoScroll : true,
	            items:[{
	                text: '每页',
	                xtype: 'tbtext'
	            },Ext.create('Ext.form.ComboBox', {
	               width:          window.screen.availWidth*0.0391,
	               value:          '20',
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
	                            var pageSize = me.store.pageSize;
	                            var newPageSize = parseInt(_field.value);
	                            if(pageSize!=newPageSize){
	                                me.store.pageSize = newPageSize;
	                                this.up('pagingtoolbar').moveFirst();
	                            }
	                        }
	                    }
	               }
	           }),{
	                text: '条',
	                xtype: 'tbtext'
	           }]
	        }];
	        this.callParent();
	    }
	});
	/**
	* @description 问题查询面板
	* @author 盛诗庆
	* @date 2014-03-06
	* @revision none
	*/

	Ext.define('QuestionSearchForm',{
	    extend:'SearchFormPanel',
	    id : 'questionSearchFormId',
	    planeType:null,
	    layout : {
	        type : 'table',
	        columns : 6
	    },
	    items:null,
	    defaults: {
            margin:'0 5 4 5',
            labelWidth:65,
            width : 250,
            labelAlign: 'right'
	    },
	    defaultType:'combobox',
	    getItems:function(){
	        var me = this;
	        return [{//问题标题
	            xtype:'textfield',
	            fieldLabel:i18n('i18n.questionManage.searchPanel.questionTitle'),
	            id:'questionTitle',
	            name:'questionTitle',
	            maxLength:100,
	            maxLengthText:i18n('i18n.developPlan.maxLength') + 100,
	            colspan:2
	        },{//适用范围
	            fieldLabel:i18n('i18n.questionManage.searchPanel.questionRange'),
	            id:'useRange',
	            name:'useRange',
	            queryMode: 'local',
	            //disabled:true,
	            store:getDataDictionaryByName(DataDictionary,'SURVEY_RANGE'),
	            displayField:'codeDesc',
	            valueField:'code',
	            forceSelection :true,
	            listeners:{
	                change:DButil.comboSelsct
	            },
	            colspan:2,
	            triggerAction : 'all',
	            //剔出朦层
	            listConfig: {
	                  loadMask:false
	            },
	            listeners:{
	                change:DButil.comboSelsct
	            }
	        },{//问题类型
	            fieldLabel:i18n('i18n.questionManage.searchPanel.questionType'),
	            name:'questionType',
	            store:getDataDictionaryByName(DataDictionary,'QUESTION_TYPE'),
	            displayField:'codeDesc',
	            valueField:'code',
	            forceSelection :true,
	            listeners:{
	                change:DButil.comboSelsct
	            },
	            colspan:2,
	            triggerAction : 'all',
	            //剔出朦层
	            listConfig: {
	                  loadMask:false
	            },
	        },{//创建时间
	            xtype : 'datefield',
	            name:'createStartTime',
	            id:'createStartTime',
	            fieldLabel : i18n('i18n.questionManage.resultGrid.creatorDate'),
	            format : 'Y-m-d',
	            maxValue:new Date(),
	            value : Ext.Date.add(new Date(),Ext.Date.DAY,-89),
	            colspan:2
	        },{//创建时间
	            xtype : 'datefield',
	            name:'createEndTime',
	            id:'createEndTime',
	            maxValue:new Date(),
	            fieldLabel : i18n('i18n.CommonView.to'),
	            format : 'Y-m-d',
	            value : new Date(),
	            colspan:2
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
	 * @description 问题详情panel
	 * @author 盛诗庆
	 * @date 2014-03-06
	 * @revision none
	 */
		Ext.define('DetailPanel',{
	  		extend:'SearchFormPanel',
	  		layout : {
	  	        type : 'table',
	  	        columns : 6
	  	    },
	  		defaults: {
	            margin:'0 5 4 5',
	            labelWidth:65,
	            width : 250,
	            labelAlign: 'right'
	  		},
	  		items : [{//问题标题
	            xtype:'readonlytextfield',
	            fieldLabel:i18n('i18n.questionManage.searchPanel.questionTitle'),
	            name:'questionTitle',
	            colspan:6,
	            width : 770
	        },{//适用范围
	  			xtype : 'combo',
	  			fieldLabel : i18n('i18n.questionManage.searchPanel.questionRange'),
	  			name : 'useRange',
	  			readOnly : true,
	  			colspan : 2,
	  			queryMode: 'local',
	            store:getDataDictionaryByName(DataDictionary,'SURVEY_RANGE'),
	            displayField:'codeDesc',
	            valueField:'code',
	            forceSelection :true,
	            cls:'readonly',
	            listeners:{
	                change:DButil.comboSelsct
	            },
	            triggerAction : 'all',
	            //剔出朦层
	            listConfig: {
	            				loadMask:false
	            			},
			},{//采用次数
  				xtype : 'textfield',
  				fieldLabel : i18n('i18n.questionManage.resultGrid.frequency'),
  				name : 'frequency',
  				readOnly : true,
  				colspan : 2
			},{//问题类型
  				xtype : 'combo',
  	  			fieldLabel : i18n('i18n.questionManage.searchPanel.questionType'),
  	  			name : 'questionType',
  	  			readOnly : true,
  	  			cls:'readonly',
  	  			colspan : 2,
	  	  		store:getDataDictionaryByName(DataDictionary,'QUESTION_TYPE'),
	            displayField:'codeDesc',
	            valueField:'code',
	            triggerAction : 'all',
	            forceSelection :true,
	            //剔出朦层
	            listConfig: {
	                  loadMask:false
	            },
	            listeners:{
	                change:DButil.comboSelsct
	            } 
  	  			
  			},{//创建人
  				xtype : 'textfield',
  				fieldLabel : i18n('i18n.questionManage.resultGrid.creatorName'),
  				name : 'createUser',
  				readOnly : true,
  				colspan : 2
  			},{//创建时间
  				xtype : 'datefield',
  				fieldLabel : i18n('i18n.questionManage.resultGrid.creatorDate'),
  				name : 'createDate',
  				readOnly : true,
  				colspan : 4,
  				format : 'Y-m-d',
  				cls:'readonly'
  			},{//问题说明
  				xtype : 'textarea',
  				fieldLabel : i18n('i18n.questionManage.searchPanel.questionDes'),
  				readOnly : true,
  				name : 'questionContent',	
  				colspan : 6,
  				width : 770
  			}],
	  		initComponent:function(){
	  			this.callParent();
	  		},
		});
		/**
		 * @description 选项结果grid
		 * @author 盛诗庆
		 * @date 2014-03-07
		 * @revision none
		 */
		Ext.define('OptionGrid',{
			extend:'PopupGridPanel', 
			title:i18n('i18n.questionManage.resultGrid.optionListTitle'),
			height:150,
			store:Ext.create('OptionsLocalStore'),
			columnLines:true,
			defaults:{
				align:'center'
			},
			initComponent:function(){             
				var me = this;
				me.columns = [
	              {//序号
					xtype:'rownumberer',
					header:i18n('i18n.questionManage.resultGrid.sequence'),
					width:50,
					align:'center',
	              },{//选项顺序
	            	  width : 100,
	            	  header:i18n('i18n.questionManage.resultGrid.optionCode'),
	            	  dataIndex : 'optionSeq',
	            	  renderer: function(value){
					        if (value === 1) {
					            return 'A';
					        }else if(value === 2){
					        	return 'B';
					        }else if(value === 3){
					        	return 'C';
					        }else if(value === 4){
					        	return 'D';
					        }else if(value === 5){
					        	return 'E';
					        }else{
					        	return 'F';
					        }
					    }
	              },{//选项
					header :i18n('i18n.questionManage.resultGrid.option'),
					flex:0.15,
					dataIndex:'optionDes',
					renderer:function(val){
			        	 if(!Ext.isEmpty(val)){
			        		 return '<a title="'+val+'">'+val+'</a>';
			        	 }
			        }
	              }
				];
				this.callParent();
			}
		});
		/**
		 * @description 选项增加grid
		 * @author 盛诗庆
		 * @date 2014-03-12
		 * @revision none
		 */
		Ext.define('OptionAddGrid',{
			extend:'PopupGridPanel', 
			region:'center',
			height:150,
			store:Ext.create('OptionsLocalStore'),
			columnLines:true,
			plugins:Ext.create('Ext.grid.plugin.CellEditing', {  
			  	clicksToEdit : 1
			}),
			defaults:{
				align:'center'
			},
			selModel : Ext.create('Ext.selection.CheckboxModel',{
				mode:'SINGLE',
				allowDeselect:true,
				listeners:{
					select : function(th,record){
						var grid = Ext.getCmp('optionAddGridId');
						var count = grid.store.getCount();
						var elseOption = Ext.getCmp('elseOption').getValue();
						if(elseOption && count > 1){//启用选项删除按钮
							Ext.getCmp('deleteQuestionOptionBtn').enable();
						}else if(count > 2){
							Ext.getCmp('deleteQuestionOptionBtn').enable();
						}
					},
					deselect : function(th,record){
						Ext.getCmp('deleteQuestionOptionBtn').disable();
					}
				}
		    }),
			initComponent:function(){             
				var me = this;
				me.columns = [
	              {//序号
					xtype:'rownumberer',
					header:i18n('i18n.questionManage.resultGrid.sequence'),
					width:40,
					align:'center',
	              },{//选项
					header :i18n('i18n.questionManage.resultGrid.option'),
					flex:1,
					dataIndex:'optionDes',
					editor : {
						xtype : 'textfield',
						maxLength : 100,
						maxLengthText : i18n('i18n.developPlan.maxLength') + 100,
					},
					renderer:function(val){
			        	 if(!Ext.isEmpty(val)){
			        		 return '<a title="'+val+'">'+val+'</a>';
			        	 }
			         }
	              }
				];
				this.callParent();
			}
		});
		/**
		 * @description 查看问题详情界面弹出窗口
		 * @author 盛诗庆
		 * @date 2014-03-06
		 * @revision none
		 */
		Ext.define('QuestionDetailPopWindow',{
			extend:'PopWindow',
			alias : 'widget.questionDetailPopWindow',
			width:820,
			height:600,
			modal:true,
			title : i18n('i18n.questionManage.title.questionDetail'),
			layout:'border',
			closeAction:'hide',
			getItems:function(){
				return[
			       	Ext.create('DetailPanel',{'id':'detailPanelId','region' : 'north'}),
				    Ext.create('OptionGrid',{'id' : 'optionGridId','region' : 'center'}),
				    Ext.create('QuestionnaireInfoListGrid',{'id' : 'questionnaireInfoListGridId','region' : 'south',height : 200}),
				]
			},
			listeners:{
				hide:function(){
					document.body.scrollLeft=0;
					document.body.scrollTop=0;
					document.getElementsByTagName("html")[0].style.overflowY="hidden";
					document.getElementsByTagName("html")[0].style.overflowX="hidden";
					viewport.doLayout();
				}
			},
			buttons:[{
				xtype:'button',
				text:i18n('i18n.questionManage.message.close'),
				handler:function(){
					Ext.getCmp("questionDetailPopWindowId").close();
				}
			}],
			initComponent : function(){
				this.items = this.getItems();
				this.callParent();
			}
		});	
		/**
		*@description 问题修改/新增panel
		*@author 盛诗庆
		*@date 2013-03-07
		*@revision none
		*/
		Ext.define('ModifyPanel',{
	  		extend:'SearchFormPanel',
	  		layout : {
	  	        type : 'table',
	  	        columns : 4
	  	    },
	  	    height : 140,
	  		defaults: {
//	            margin:'0 5 4 5',
	            labelWidth:65,
	            width : 260,
	            labelAlign: 'right'
	  		},
	  		items : [{//标题
  				xtype : 'textfield',
  				fieldLabel : i18n('i18n.questionManage.searchPanel.questionTitle'),
  				name : 'questionTitle',
  				width : 522,
  				colspan : 4,
  				allowBlank : false,//不允许为空
  				blankText : i18n('i18n.questionManage.message.questionTitleCannotBeNull'),
  				maxLength : 100,//最长一百个字符
  				maxLengthText : i18n('i18n.developPlan.maxLength') + 100
  			},{//适用范围
  				xtype : 'combo',
  				fieldLabel : i18n('i18n.questionManage.searchPanel.questionRange'),
  				name : 'useRange',
  				store:getDataDictionaryByName(DataDictionary,'SURVEY_RANGE'),
	            displayField:'codeDesc',
	            valueField:'code',
	            value : 'VISIT',
	            allowBlank : false,//不允许为空
  				blankText : i18n('i18n.questionManage.message.useRangeCannotBeNull'),
	            colspan : 2,
	            forceSelection :true,
	            triggerAction : 'all',
	            //剔出朦层
	            listConfig: {
	                  loadMask:false
	            },
	            listeners:{
	                change:DButil.comboSelsct
	            }, 
  			},{//问题类型
  				xtype : 'combo',
  				fieldLabel : i18n('i18n.questionManage.searchPanel.questionType'),
  				name : 'questionType',
  				colspan : 2,
  				allowBlank : false,
  				store:getDataDictionaryByName(DataDictionary,'QUESTION_TYPE'),
	            displayField:'codeDesc',
	            valueField:'code',
	            forceSelection :true,
	            value : 'RADIO',
	            listeners:{
	                change:DButil.comboSelsct,
	                select : function(t){
	                	var grid = Ext.getCmp('optionAddGridId');
	                	if(t.getValue() == 'QUESTION_ANSWER'){//简答，禁用选项面板
	                		//清空grid数据并禁用
	                		grid.store.removeAll();
	                		grid.disable();
	                		//禁用按钮
	                		Ext.getCmp('deleteQuestionOptionBtn').disable();
	                		Ext.getCmp('addQuestionOptionBtn').disable();
	                		//清空选型并禁用选择框
	                		Ext.getCmp('elseOption').reset();
	                		Ext.getCmp('elseOption').disable();
	                	}else{
	                		if(grid.store.getCount() == 0){//单选多选记录默认为2
	                			//向store中默认添加两条数据
			                	 var panelModel = grid.store.model.modelName;
			                	 var store = grid.getStore();
			                	 store.add(Ext.create(panelModel));
			                	 store.add(Ext.create(panelModel));
	                		}else if(grid.store.getCount() == 1){
	                			//向store中默认添加一条数据
			                	 var panelModel = grid.store.model.modelName;
			                	 var store = grid.getStore();
			                	 store.add(Ext.create(panelModel));
	                		}
	                		grid.enable();
	                		//启用按钮
	                		Ext.getCmp('deleteQuestionOptionBtn').enable();
	                		Ext.getCmp('addQuestionOptionBtn').enable();
	                		//启用选择框
	                		Ext.getCmp('elseOption').enable();
	                	}
	                }
	            },
	            triggerAction : 'all',
	            //剔出朦层
	            listConfig: {
	                  loadMask:false
	            },
  			},{//问题说明
	  			xtype : 'textarea',
	  			fieldLabel : i18n('i18n.questionManage.searchPanel.questionDes'),
	  			name : 'questionContent',
	  			colspan : 4,
	  			width : 522,
	  			maxLength : 300,
	  			maxLengthText : i18n('i18n.developPlan.maxLength') + 300,
	         },{//隐藏域，保存问题id
	        	 xtype : 'hidden',
	        	 name : 'id'
	         }],
	  		initComponent:function(){
	  			this.callParent();
	  		},
		});
		
		/**
		 * @description 问题修改/新增界面window
		 * @author 盛诗庆
		 * @date 2013-03-07
		 * @revision none
		 */
		Ext.define('QuestionModifyPopWindow',{
			extend:'PopWindow',
			flag : null,//标记窗口是修改还是新增
			alias : 'widget.questionModifyPopWindow',
			width:600,
			height:400,
			modal:true,
			layout:'border',
			closeAction:'hide',
			items:[Ext.create('ModifyPanel',{
				'region' : 'north'
			}),{
				xtype:'container',
				region:'center',
				layout:'border',
				items:[Ext.create('OptionGridPanel',{
					'region' : 'north',
					height:25
				}),Ext.create('OptionAddGrid',{
					'store':Ext.create('OptionsRemoteStore'),
					'id':'optionAddGridId',
					region:'center'
				})]
			}],
			listeners:{
				hide:function(){
					document.body.scrollLeft=0;
					document.body.scrollTop=0;
					document.getElementsByTagName("html")[0].style.overflowY="hidden";
					document.getElementsByTagName("html")[0].style.overflowX="hidden";
					viewport.doLayout();
				}
			},
			buttons:[{//保存
					xtype:'button',
					text:i18n('i18n.questionManage.message.save'),
					handler:function(btn){
						var me = this;
						var canSave = true;
						//获取容器内的panel和grid
						var form = this.up('window').down('panel').getForm();
						var grid = this.up('window').down('grid');
						if(!form.isValid()){
				    		return false;
				    	};
				    	//问题标题
					    var questionTitle = form.findField('questionTitle').getValue();
					    if(Ext.isEmpty(Ext.String.trim(questionTitle))){
					    	MessageUtil.showErrorMes(i18n('i18n.questionManage.message.questionTitleCannotBeNull'));
					    	return false;
					    }
					    
						var panelStore = grid.getStore();
						//是否允许填空
						var elseOption = Ext.getCmp('elseOption').getValue();
						//问题类型
						var questionType = form.findField('questionType').getValue();
						if(questionType == 'QUESTION_ANSWER'){//问题是简答题
							
						}else{//单选多选，校验选项是否正确
							if(elseOption && panelStore.data.length>5 || panelStore.data.length < 1){
								MessageUtil.showErrorMes(i18n('i18n.questionManage.searchPanel.lessThanFive'));
								btn.enable();
								return false;
							}else if(!elseOption && (panelStore.data.length>6 || panelStore.data.length < 2)){
								MessageUtil.showErrorMes(i18n('i18n.questionManage.searchPanel.lessThanSix'));
								btn.enable();
								return false;
							}
							//获取问题详情
						    var options = new Array();
						    var optionSeq = 1;
						    //计数器
						    var lineNumber = 0;
						    grid.store.each(function(record){
						    	var optionDes = record.data.optionDes;
						    	if(optionDes!=null){
						    		//去除空格
							    	optionDes = optionDes.replaceAll(' ','');
							    	optionDes = optionDes.replaceAll('　','');
						    	}
						    	
						    	lineNumber = lineNumber + 1;
						    	//选项内容不能为空
						    	if(Ext.isEmpty(optionDes) || optionDes == ''){
						    		btn.enable();
						    		MessageUtil.showErrorMes('第' + lineNumber + '个选项描述不能为空');
						    		canSave = false;
						    		return false;
						    	}else if(optionDes == i18n('i18n.questionManage.searchPanel.elseOption')){
						    		btn.enable();
						    		MessageUtil.showErrorMes('第' + lineNumber + '个选项描述不能为其他');
						    		canSave = false;
						    		return false;
						    	}else{
						    		record.set('optionSeq',optionSeq);
							    	optionSeq = optionSeq +1;
							    	options.push(record.data)
						    	}
						    });
						}
						btn.disable();
					    var elseOption = null;
					    if(Ext.getCmp('elseOption').getValue()){
					    	elseOption = 1;
					    }else{
					    	elseOption = 0;
					    }
					    
					    var param = {'questionDetail':{
					    	'options':options,
					    	'id' : form.findField('id').getValue(),
					    	'questionTitle':Ext.String.trim(questionTitle),
					    	'useRange':form.findField('useRange').getValue(),
					    	'questionType':form.findField('questionType').getValue(),
					    	'questionContent':form.findField('questionContent').getValue(),
					    	'elseOption':elseOption
					    	}
					    };
						var successFn = function(json){
								if(me.up('window').flag == "add"){
									MessageUtil.showInfoMes(i18n('i18n.questionManage.message.createQuestionSuccess'));
									me.up('window').close();
								}else{
									if(json.questionnaireList != null){
										MessageUtil.showErrorMes(i18n('i18n.questionManage.errorMessage.questionOccupiedUpdate'));
										me.up('window').close();
									}else{
										MessageUtil.showInfoMes(i18n('i18n.questionManage.message.modifyQuestionSuccess'));
										me.up('window').close();
									}
								}
								btn.enable();
								//刷新列表
								Ext.getCmp('questionGirdId').store.loadPage(1);
							}
						var failureFn = function(json){
								if(Ext.isEmpty(json)){
									if(me.up('window').flag == "add"){
										MessageUtil.showErrorMes(i18n('i18n.questionManage.errorMessage.createQuestionFailure'));
										me.up('window').close();
									}else{
										MessageUtil.showErrorMes(i18n('i18n.questionManage.errorMessage.modifyQuestionFailure'));
										me.up('window').close();
								}}else{
									MessageUtil.showErrorMes(json.message);
								}
								btn.enable();
					    };
					    if(canSave){
						    if(me.up('window').flag == "add"){
						    	QuestionStore.prototype.createQuestion(param, successFn, failureFn);
						    }else{
						    	QuestionStore.prototype.modifyQuestion(param, successFn, failureFn);
						    }
					    }else{
					    	btn.enable();
					    	return false;
					    }
	    			}
			},{
				xtype:'button',
				text:i18n('i18n.questionManage.message.close'),
				handler:function(){
					this.up('window').close();
				}
			}]
		});	
		/**
		 * 问卷详情grid
		 * 2014-3-18
		 * 盛诗庆
		 */
		Ext.define('QuestionnaireInfoListGrid',{
			extend:'PopupGridPanel',   
			columns:null,
			store:null,
			title : i18n('i18n.questionManage.title.relateQuestionnaire'),
			defaults:{
				align:'center'
			},
			listeners:{
				
			},
			initComponent:function(){             
				var me = this;
				var store = Ext.create('QuestionnaireStore');
				me.store = store;
				me.columns = [{//序号
					xtype:'rownumberer',
					width:40,
					align:'center',
					header:i18n('i18n.questionnaireManage.rownumberer')
				},{//问卷编号 
					header : i18n('i18n.questionnaireManage.questionnaireCode'),
					width:100,
					dataIndex:'questionnaireCode',
					renderer:function(val,metaData,record){
						return val;
					}
				},{//问卷名称
					header : i18n('i18n.questionnaireManage.questionnaireName'),
					width:150,
					dataIndex:'questionnaireName',
					renderer:function(val,metaData,record){
						return val;				
					}
				},{//问卷描述
					header : i18n('i18n.questionnaireManage.questionnaireDesc'),
					width:150,
					dataIndex:'desc',
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
				},{//适用范围 
					header : i18n('i18n.questionManage.searchPanel.questionRange'),
					width:100,
					dataIndex:'useRange',
					renderer:function(value){
						return rendererDictionary(value,DataDictionary.SURVEY_RANGE);
					}
				},{//问卷状态
					header : i18n('i18n.questionnaireManage.questionnaireStatus'),
					width:100,
					dataIndex:'status',
					renderer:function(value){
						return rendererDictionary(value,DataDictionary.SURVEY_STATUS);
					}
				},{//生效时间
					header : i18n('i18n.questionnaireManage.effectiveTime'),
					dataIndex : 'effectiveTime',
					align : 'center',
					renderer : DButil.renderDate
				},{//失效时间
					header : i18n('i18n.questionnaireManage.invalidTime'),
					dataIndex : 'invalidTime',
					align : 'center',
					renderer : DButil.renderDate
				},{//采用次数
					header : i18n('i18n.questionManage.resultGrid.frequency'),
					width:100,
					dataIndex:'useTimes',
					renderer:function(val,metaData,record){
						return val+i18n('i18n.questionnaireManage.times');			
					}
				},{//创建时间
					header : i18n('i18n.questionManage.searchPanel.beginOfCreatDate'),
					dataIndex : 'createDate',
					align : 'center',
					renderer : DButil.renderDate
				},{//创建人
					header : i18n('i18n.questionManage.resultGrid.creatorName'),
					width:100,
					dataIndex:'createUser',
					renderer:function(val,metaData,record){
						return val;			
					}
				},{//最后修改时间
					header : i18n('i18n.questionManage.resultGrid.modifyDate'),
					dataIndex : 'modifyDate',
					align : 'center',
					renderer : DButil.renderDate
				},{//最后修改人
					header : i18n('i18n.questionManage.resultGrid.modifierName'),
					width:100,
					dataIndex:'modifyUser',
					renderer:function(val,metaData,record){
						return val;			
					}
				}];
				this.callParent();
			}
		});
		
    /**
     *将界面显示到viewport中 
     */
    var viewport=Ext.create('Ext.Viewport',{
        layout : 'border',
        autoScroll:true,
        items:[Ext.create('QuestionManagePanel',{region:'center'})]
    });
    viewport.setAutoScroll(false);
    viewport.doLayout();
});
/**
 * 校验时间范围是否合法
 * 肖红叶
 * 20140311
 * @param dateOne
 * @param dateTwo
 */
function dateRangeNotValid(dateStart,dateEnd,number){
	var result = false;
	if(!Ext.isEmpty(dateStart) && !Ext.isEmpty(dateEnd)){
		var createCompareTime = Ext.Date.add(new Date(dateStart), Ext.Date.DAY, number);
		if(DButil.compareTwoDate(dateStart,dateEnd) < 0){
			result = true;
		}else if(DButil.compareTwoDate(createCompareTime,dateEnd) > 0){
			result = true;
		}
	}
	return result;
}
/**
 * 禁用、启用查看详情、修改、删除、查看问卷统计结果四个按钮
 * record : 选中或取消选中的记录
 * 盛诗庆
 * 20140402
 */
function controlBtn(record){
	if(Ext.isEmpty(record)){//如果没有记录被选中，禁用删除，查看详情，修改按钮
		Ext.getCmp('modifyQuestionBtnId').disable();
		Ext.getCmp('viewQuestionBtnId').disable();
		Ext.getCmp('deleteQuestionBtnId').disable();
	}else{
		Ext.getCmp('modifyQuestionBtnId').enable();
		Ext.getCmp('viewQuestionBtnId').enable();
		Ext.getCmp('deleteQuestionBtnId').enable();
	}	
}