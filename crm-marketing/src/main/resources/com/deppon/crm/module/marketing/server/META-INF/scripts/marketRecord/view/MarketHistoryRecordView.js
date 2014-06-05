/*
 * 单击营销记录按钮，弹出的营销历史记录页面
 * 肖红叶
 * 2012-11
 */

//定义全局变量客户id和客户类型，用以查询营销历史记录
var custIDForMarketRecord = null;
var custTypeForMarketRecord = null;
var marketRecordData =  new MarketRecordData();//初始化计划新增修改data
//查看详情时的问卷id列表
var questionnaireIdListForWhole = null;
//查看详情或者问卷预览时的问题id列表
var questionIdListForWhole = null;
var keys=[
      	//合作意向'
      	'COOPERATION_INTENTION',
      	//问卷适用范围
      	'SURVEY_RANGE',
      	//计划类别
      	'PLAN_TYPE'
      ];
      //初始化业务参数
      initDataDictionary(keys);
/*
 * 点击营销记录按钮，查询出的客户需求结果表格
 */
Ext.define('CustomerDemandResultGrid',{
    extend:'PopupGridPanel',
    defaults:{
        align:'center'
    },
    autoScroll:true,
    initComponent:function(){ 
        var me = this;
        var store =  Ext.create('SearchCustomerDemandGridStore',{'id':'customerDemandGridStoreId'});
        
        store.on('beforeload',function(store,operation,e){
            var searchParams = {
                    'customerCallVO.contactType':custTypeForMarketRecord,
                    'customerCallVO.id':custIDForMarketRecord
            };
            
            Ext.apply(operation,{
                params : searchParams
            });
        }); 
        
        me.store = store;
        me.columns = [
            {//序号
                xtype:'rownumberer',
                header:i18n('i18n.Cycle.rownum'),
                width:50
            },{//联系人姓名
                header:i18n('i18n.developSchedule.linkManName'),
                width:80,
                dataIndex:'custLinkManName'
            },{ //需求分类
                header :i18n('i18n.returnVisit.opinionType'),
                dataIndex:'opinionType',
                renderer:function(value){
                    return rendererDictionary(value,DataDictionary.CUSTOMER_IDEA);
                },
                width:80
            },{//需求及问题
                header :i18n('i18n.returnVisit.problem'),
                dataIndex:'problem',
                width:80
            },{//解决办法
                header :i18n('i18n.marketRecord.demandResolve'),
                dataIndex:'solution',
                flex:1
            },{//计划主题
                header :i18n('i18n.returnVisit.theme'),
                dataIndex:'topic',
                width:100
            },{//计划类别
		    	header :i18n('i18n.returnVisit.planType'),
				dataIndex:'projectType',
				renderer:function(value){
					return rendererDictionary(value,DataDictionary.PLAN_TYPE);
				},
				width:100
            },{//营销方式
                header :i18n('i18n.marketRecord.marketWay'),
                dataIndex:'marketingMethod',
                renderer:function(value){
                    return rendererDictionary(value,DataDictionary.DEVELOP_WAY);
                },
                width:120
           },{//营销人
                header :i18n('i18n.marketRecord.marketName'),
                dataIndex:'marketingPerson',
                width:80
            },{//营销时间
                header :i18n('i18n.marketRecord.marketTime'),
                dataIndex:'marketingDate',
                renderer : DButil.renderDate,
                width:90
            }
        ];
        me.dockedItems=[{
            xtype:'pagingtoolbar',
            cls:'pagingtoolbar',
            store:store,
            dock:'bottom',
            displayInfo : true,
            autoScroll : true,
            items:[{
                text: '每页',
                xtype: 'tbtext'
            },Ext.create('Ext.form.ComboBox', {
               width:          window.screen.availWidth*0.0391,
               triggerAction:  'all',
               forceSelection: true,
               value:'20',
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
    }];
    this.callParent();
   }
});

/**
 * 客户需求查询结果表格的Panel
 */
Ext.define('CustomerDemandResultGridPanel',{
    extend:'BasicVboxPanel',
    items : null,
    initComponent : function() {
        this.items = this.getItems();
        this.callParent();
    },
    getItems : function() {
        var me = this;
        return [
        {
        xtype:'toppanel',   
        items:[
           {
            xtype:'titlepanel',  
            flex:1,
            items:[
                   {
                    xtype:'displayfield',
                    value:i18n('i18n.PotentialCustEditView.custNeed')
            }
            ]
            }
        ] 
        },{
        xtype:'basicpanel',     
        flex:1,
        items:[Ext.create('CustomerDemandResultGrid',{'id':'customerDemandResultGridId'})]
        }
      ];
    }
});

/*
 * 点击营销记录按钮，查询出的走货潜力结果表格
 */
Ext.define('SendGoodsPontentialResultGrid',{
    extend:'PopupGridPanel', 
    region:'center',
    autoScroll:true,
    defaults:{
        align:'center'
    },
    initComponent:function(){             
        var me = this;
        var store =  Ext.create('SearchSendGoodsPontentialStore',{'id':'sendGoodsPontentialStoreId'});
        //在store加载之前传递参数进行数据筛选
        store.on('beforeload',function(store,operation,e){
            var searchParams = {
                'customerCallVO.id':custIDForMarketRecord,
                'customerCallVO.contactType':custTypeForMarketRecord                    
            };
            
            Ext.apply(operation,{
                params : searchParams
            });
        }); 
        
        me.store = store;
        me.columns = [
          {//序号
            xtype:'rownumberer',
            header:i18n('i18n.Cycle.rownum'),
            width:50,
            align:'center'
          },{    //联系人姓名
            header :i18n('i18n.developSchedule.linkManName'),
            dataIndex:'custlinkManName',
            width:80
          },{   //始发城市 
            header :i18n('i18n.returnVisit.comeFromCity'),
            dataIndex:'comeFromCity',
            width:80
          },{    //到达城市
            header :i18n('i18n.returnVisit.comeToCity'),
            dataIndex:'comeToCity',
            width:80
          },{//货量潜力
            header :i18n('i18n.returnVisit.volumePotential'),
            width:120,
            dataIndex:'volumePotential',
            renderer:function(value){
                return rendererDictionary(value,DataDictionary.CARGO_POTENTIAL);
            }
            
          },{//合作物流公司
            header :i18n('i18n.returnVisit.companyId'),
            dataIndex:'companyId',
            width:100,
            renderer:function(value){
                return rendererDictionary(value,DataDictionary.COOPERATION_LOGISTICS);
            }
          },{//合作意向
			header : i18n('i18n.developSchedule.cooperatePurpose'),
			dataIndex : 'cooperate',
			align : 'center',
			width : 90,
			sortable : true,
			renderer: function(value){
				return rendererDictionary(value,DataDictionary.COOPERATION_INTENTION); 
			}
		},{//适合我司承运
			header : i18n('i18n.businessOpportunity.accord'),
			dataIndex : 'accord',
			align : 'center',
			width : 90,
			sortable : true,
			renderer: function(value){
				if(value=='1'){
					return i18n('i18n.ChangeContactAffiliatedRelationView.yes');
				}else if(value=='0'){
					return i18n('i18n.ChangeContactAffiliatedRelationView.no');
				}else{
					return value;
				}
			}
		},{//是否已有线路
            header :i18n('i18n.marketRecord.isExistRoad'),
            dataIndex:'alreadyHaveLine',
            renderer:function(val){
                //如果返回值为YES，则设置显示值为是
                if(val=='YES'){
                    return '是';
                }else if(val == 'NO'){//如果返回值为NO，则设置显示值为是否
                    return '否';
                }else{
                    return '';
                }
            },
            width:100
          },{//备注
            header :i18n('i18n.returnVisit.remark'),
            dataIndex:'remark',
            width:150
          },{    //营销方式
            header :i18n('i18n.marketRecord.marketWay'),
            dataIndex:'marketingMethod',
            renderer:function(value){
                return rendererDictionary(value,DataDictionary.DEVELOP_WAY);
            },
            width:120
          },{//营销人
            header :i18n('i18n.marketRecord.marketName'),
            dataIndex:'marketingPerson',
            width:80
          },{//营销时间
            header :i18n('i18n.marketRecord.marketTime'),
            dataIndex:'marketingDate',
            renderer : DButil.renderDate,
            width:100
          }
        ];
        me.dockedItems=[{
            xtype:'pagingtoolbar',
            cls:'pagingtoolbar',
            store:store,
            dock:'bottom',
            displayInfo : true,
            autoScroll : true,
            items:[{
                text: '每页',
                xtype: 'tbtext'
            },Ext.create('Ext.form.ComboBox', {
               width:          window.screen.availWidth*0.0391,
               triggerAction:  'all',
               forceSelection: true,
               value:'20',
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
        }];
        this.callParent();
    }
});

/**
 * 走货潜力录入表格的Panel
 */
Ext.define('SendGoodsPontentialResultGridPanel',{
    extend:'BasicVboxPanel',
    items : null,
    initComponent : function() {
        this.items = this.getItems();
        this.callParent();
    },
    getItems : function() {
        var me = this;
        return [
         {
            xtype:'toppanel',   
            items:[
               {
                xtype:'titlepanel',  
                flex:1,
                items:[
                   {
                    xtype:'displayfield',
                    value:i18n('i18n.returnVisit.sendGoodsPontential')
                 }]
                }
            ] 
         },{
            xtype:'basicpanel',     
            flex:1,
            items:[Ext.create('SendGoodsPontentialResultGrid',{'id':'sendGoodsPontentialResultGridId'})]
         }
      ];
    }
}); 

/**
 * 营销历史记录查询页面整体布局panel
 */
Ext.define('MarketHistoryResultPanelOfReturnVisit', {
    extend : 'BasicPanel',
    layout : 'border',
    items :[
      {//客户需求查询结果面板
        xtype : 'basicpanel',
        region:'north',
        layout:'fit',
        items:[Ext.create('CustomerDemandResultGridPanel',{id:'customerDemandResultGridPanelId'})],
        height:260
      },{//走货潜力查询结果面板
        xtype : 'basicpanel',
        layout:'fit',
        region:'center',
        items:[Ext.create('SendGoodsPontentialResultGridPanel',{id:'sendGoodsPontentialResultGridPanelId'})]
      }
    ]
});

/*
 * 点击营销记录按钮，查询出的问卷结果表格
 * 肖红叶
 * 20140324
 */
Ext.define('QuestionnaireResultGridForMarketRecord',{
    extend:'PopupGridPanel', 
    region:'center',
    autoScroll:true,
    defaults:{
        align:'center'
    },
    initComponent:function(){             
        var me = this;
        var store =  Ext.create('QuestionnaireResultStore');
        //在store加载之前传递参数进行数据筛选
        store.on('beforeload',function(store,operation,e){
            var searchParams = {
                'custId':custIDForMarketRecord,
            };
            Ext.apply(operation,{
                params : searchParams
            });
        }); 
        
        me.store = store;
        me.columns = [
          {//序号
            xtype:'rownumberer',
            header:i18n('i18n.Cycle.rownum'),
            width:50,
            align:'center'
          },{    //联系人姓名
            header :i18n('i18n.developSchedule.linkManName'),
            dataIndex:'linkManName',
            width:80
          },{   //问卷编码 
            header :'问卷编码',
            dataIndex:'questionnaireCode',
            width:100
          },{    //问卷名称
            header :'问卷名称',
            dataIndex:'questionnaireName',
            width:120
          },{//问卷描述
            header :'问卷描述',
            width:150,
            dataIndex:'desc'
            
          },{//计划主题
            header :'计划主题',
            dataIndex:'planTopic',
            width:120
          },{ //营销方式
            header :i18n('i18n.marketRecord.marketWay'),
            dataIndex:'marketingWay',
            renderer:function(value){
                return rendererDictionary(value,DataDictionary.DEVELOP_WAY);
            },
            width:120
          },{//营销人
            header :i18n('i18n.marketRecord.marketName'),
            dataIndex:'executorName',
            width:80
          },{//营销时间
            header :i18n('i18n.marketRecord.marketTime'),
            dataIndex:'marketingDate',
            renderer : DButil.renderDate,
            width:100
          }
        ];
        //添加双击事件
        //加载问卷答案详情 盛诗庆
        me.on('itemdblclick',function(th,record,item,index,e,eOpts){
        		var questionnaireDetailPopWindow = Ext.getCmp("questionnaireDetailPopWindowId");//获取win
        		if(!questionnaireDetailPopWindow){
        			questionnaireDetailPopWindow = Ext.create('QuestionnaireDetailPopWindow',{id:'questionnaireDetailPopWindowId'});
        		}
        		//获得问卷基本信息面板
        		var questionnaireDetailForm = Ext.getCmp('questionnaireDetailFormId').getForm();
        		//重置查看详情页面
        		questionnaireDetailForm.reset();
        		var model = new QuestionnaireInfoModel(record.data);
        		questionnaireDetailForm.loadRecord(model);
        		Ext.getCmp('questionListGridId').store.load({params:{'visitId':record.get('visitId')}});
//        		//查询问卷详情成功回调函数
//        		function successFn(json){
//        			var questions = json.questionList;
//        			var store = Ext.getCmp('questionListGridId').store;
//        			var storeForAdd = new QuestionInfoListStore();
//        			for(var i = 0;i < questions.length;i++){
//        				var question = new QuestionInfoModel(questions[i]);
//        				storeForAdd.add(question);
//        			}
//        			store = storeForAdd;
//        		}
//        		//查询问卷失败回调函数
//        		function failureFn(json){
//        			MessageUtil.showErrorMes(json.message);
//        		}
//        		marketRecordData.viewQuestionnaireByVisitId({'visitId':record.get('visitId'),'start':0,'limit':1},successFn,failureFn);
        		questionnaireDetailPopWindow.show();
        });
        me.dockedItems=[{
            xtype:'pagingtoolbar',
            cls:'pagingtoolbar',
            store:store,
            dock:'bottom',
            displayInfo : true,
            autoScroll : true,
            items:[{
                text: '每页',
                xtype: 'tbtext'
            },Ext.create('Ext.form.ComboBox', {
               width:          window.screen.availWidth*0.0391,
               triggerAction:  'all',
               forceSelection: true,
               value:'20',
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
        }];
        this.callParent();
    }
});

/**
 * 营销历史记录查询结果整体布局panel，包含回访结果和问卷结果两个tab
 * 肖红叶
 * 20140324
 */	
Ext.define('MarketHistoryResultPanel', {
    extend:'NormalTabPanel',
    activeTab:0,
	items:[{//回访结果页签
		xtype:'tabcontentpanel',
		title:'回访结果',
		layout:'fit',
		items:[Ext.create('MarketHistoryResultPanelOfReturnVisit')]
	},{//问卷结果页签
		xtype:'tabcontentpanel',
		title:'问卷结果',
		layout:'fit',
		items:[Ext.create('QuestionnaireResultGridForMarketRecord',{
			id:'questionnaireResultGridForMarketRecordId'
		})]
	}]
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
		var store = Ext.create('QuestionAndAnswerInfoListStore');
		me.store = store;
		me.columns = [{//问卷id 
			header : '问题列表',
			flex:1,
			dataIndex:'id',
			renderer:function(val,metaData,record,row,col,store){
				//判断是不是在渲染表格记录的第一行
				var timeFirst = true;
				//问题类型转化
				var questionType = optionInfoConvert(record.get('questionType'));
				if(!Ext.isEmpty(record)){
					//单选题与多选题的显示样式转化
					if(record.get('questionType') != 'QUESTION_ANSWER'){
						//记录有多少个选项
						var length = 0;
						if(!Ext.isEmpty(record.optionsStore)){
							length = record.optionsStore.data.length;
						}
						var tableString = '<div align = "left"><table>';
						for(var i = 0;i < length;i++){
							var optionsData = record.optionsStore.data.items[i].data;
							var isSelected = optionsData.isSelected;//是否选中
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
							var answer = Ext.isEmpty(optionsData.answer) ? '' : optionsData.answer;
							//根据问题类型显示适当的表单符号
							var optionDisplay = optionDisplayConvert(record.get('questionType'),isSelected);
							if(optionsData.optionDes === '其他'){
								if(Ext.isEmpty(answer)){
									
								}else{
									setTimeout(function(){
										var text = Ext.create('Ext.form.field.Text',{
											width:550,
											listeners : {
												change : function (text){
													if(Ext.isEmpty(text.getValue())){
														text.setValue("");
													}
													if(text.getValue()!=""){
													Ext.QuickTips.init();  
										            Ext.QuickTips.register({ 
										                target : text.el, 
										                text : text.getRawValue()
										            	}) 
													}else{
														Ext.QuickTips.destroy();  
													}	
												}
											},
											value:null,
											readOnly:true,
											cls:'readonly',
											renderTo:record.get('id')+'ElseAnswer'
										});   
										text.setValue(answer);
									},1000);
								}
								tableString = tableString + '<tr><td height="25">&nbsp;&nbsp;'+optionDisplay+'&nbsp;&nbsp;'+optionSeq+'&nbsp;&nbsp;'+showString
								+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+record.get('id')+'ElseAnswer" style="float: right"></div></td></tr>';
								
								
//								tableString = tableString + '<tr><td height="25">&nbsp;&nbsp;'+optionDisplay+'&nbsp;&nbsp;'+optionSeq+'&nbsp;&nbsp;'+optionsData.optionDes
//								+'&nbsp;&nbsp;<textarea maxlength="500" style="width:550px; height:20px; border:solid 1px #000;">'+ answer+'</textarea></td></tr>';
							}else{
								tableString = tableString + '<tr><td height="25">&nbsp;&nbsp;'+optionDisplay+'&nbsp;&nbsp;'+optionSeq+'&nbsp;&nbsp;'+showString
								+'&nbsp;&nbsp;</td></tr>';
							}
							
						}
						tableString = tableString + '</table></div>';
					}
					else{//简答题的显示方式
						var answer  = Ext.isEmpty(record.optionsStore.data.items[0].data.answer) ? '' : record.optionsStore.data.items[0].data.answer;
						setTimeout(function(){
							Ext.create('Ext.form.field.TextArea',{
								width:650,
								value:answer,
								readOnly:true,
								height:100,
								cls:'readonly',
								renderTo:record.get('id')+'Answer'
							});   		        		
						},1000);
						var tableString = '<div align = "left"><table>';
						tableString = tableString + '<tr><td height="125">&nbsp;&nbsp;<div id="'+record.get('id')+'Answer"></div></td></tr></table></div>';
//						var tableString = '<div align = "left">&nbsp;&nbsp;&nbsp;&nbsp;<textarea style="width:650px; height:100px; border:solid 1px #000;" readonly="readonly">' + answer + '</textarea></div>';
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
					'<br><p><b>{0}、{1}({2})</b></p><br>'+tableString+'<br>',
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
	    	fieldLabel : '问卷编号',
			name:'questionnaireCode'
	    },{//问卷名称
			xtype:'readonlytextfield',
	    	fieldLabel : '问卷名称',
			name:'questionnaireName'
	    },{//适用范围
			xtype:'combobox',
			fieldLabel:'适用范围',
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
	    	fieldLabel:'生效时间',
	    	format:'Y-m-d',
			name:'effectiveTime',
			//设置起始时间的初始值为当前月份的第一天
			readOnly:true,
			cls:'readonly'
		},{//失效时间
	    	fieldLabel:'失效时间',
	    	format:'Y-m-d',
			name:'invalidTime',
			readOnly:true,
			cls:'readonly'
		},{//采用次数
			xtype:'readonlytextfield',
	    	fieldLabel : '采用次数',
			name:'useTimes',
			value:'0'
	    },{//问卷描述
        	xtype:'readonlytextarea',
            width:700,
            name : 'desc',
            fieldLabel: '问卷描述',
            height:50,
            colspan : 3
        }];
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
	title:'问卷详情',
	closeAction:'hide',
	items:[Ext.create('QuestionnaireDetailForm',{
		id:'questionnaireDetailFormId',
		region:'north',
		height:120
	}),Ext.create('QuestionListGrid',{
		id:'questionListGridId',
		region:'center'
	})],
	buttons:[{
		text:'关闭',
		handler:function(){
			this.up('window').hide();
		}
	}]
});