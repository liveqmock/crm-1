/**
 * 查询条件面板 LogSearchForm
 * 查询结果面板 SearchResultGrid
 * 按钮面板 LogManagerButtonPanel
 */
//用于标记是查看请求图还是时长图
var viewType = null;
//data数据源
var actionData = new LogBasicData();
/**
 * 日志监控界面Panel
 */
Ext.define('LogManagerPanel',{
	extend:'BasicPanel',
	valiType:'LOGCHART',//标志位，用于区别是校验日志监控还是请求图
	searchCondition:null, // 查询条件
	searchResult:null, // 查询结果
	actionData:null,//数据data
	autoScroll:true,
	layout:{
		type:'vbox',
		align:'stretch'
	},
	initComponent:function(){
		var me = this;
		me.searchCondition = Ext.create('LogSearchForm');
		me.searchResult = Ext.create('SearchResultGrid',{
			'actionData':me.actionData
		});
		me.buttonBar = me.getButtonBar();
		me.items = me.getItems();
		this.callParent();
	},
	getButtonBar:function(){
		var me = this;
		return new LogManagerButtonPanel({'parent':me,'valiType':me.valiType});
	},
	getItems:function(){
		var me = this;
		return[{
				xtype:'basicpanel',
				height:70,
				items:[me.searchCondition]
			},{
				xtype:'basicpanel',
				height:36,
				items:[me.buttonBar]
			},{
				xtype:'basicpanel',
				flex:1,
				items:[me.searchResult]
			}];
	}
	});
/**
 * 查询条件面板
 */
Ext.define('LogSearchForm',{
	extend:'SearchFormPanel',
	items:null,
	parent:null,
	record:null,
	layout:'fit',
	id:'searchPanel_id',
	layout : {
		type : 'table',
		columns : 3
	},
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return[{
			xtype:'textfield',
			fieldLabel:i18n('i18n.Logcontrol.requestName'),
			name:'requestName'
		},{
			xtype:'datetimefield',
			fieldLabel:i18n('i18n.Logcontrol.countBeginTime'),
			format: 'Y-m-d H:00:00',
			id:'beginCountDate_id',
			name:'beginDate'
		},{
			xtype:'datetimefield',
			fieldLabel:i18n('i18n.Logcontrol.countEndTime'),
			format: 'Y-m-d H:00:00',
			id:'endCountDate_id',
			name:'endDate'
		},{
			xtype:'textfield',
			fieldLabel:i18n('i18n.Logcontrol.modulename'),
			name:'moduleName'
		},{
            xtype:'fieldcontainer',
            fieldLabel : i18n('i18n.Logcontrol.countRate'),
            defaultType: 'radiofield',
            name:'countRate',
            layout: 'column',
            items: [
				 {
				    boxLabel  : i18n('i18n.Logcontrol.hour'),
				    name      : 'statisticalFrequency',
				    inputValue: 'hour',
				    margin:'0 27 0 0',
				    id        : 'hour_id',
				    checked:true,
				    listeners:{
				    	change:function(radio){
				    		if(radio.checked){
				    			changeDateFormat('HOUR')
				    		}
				    	}
				    }
				}, {
                    boxLabel  : i18n('i18n.Logcontrol.day'),
                    name      : 'statisticalFrequency',
                    inputValue: 'day',
                    margin:'0 27 0 0',
                    id        : 'day_id',
                    listeners:{
				    	change:function(radio){
				    		if(radio.checked){
				    			changeDateFormat('DAY')
				    		}
				    	}
				    }
                }, {
                    boxLabel  : i18n('i18n.Logcontrol.month'),
                    name      : 'statisticalFrequency',
                    inputValue: 'month',
                    margin:'0 27 0 0',
                    id        : 'month_id',
                    listeners:{
				    	change:function(radio){
				    		if(radio.checked){
				    			changeDateFormat('MONTH')
				    		}
				    	}
				    }
                }
            ]
            }];
	}
});
/**
 * 操作按钮面板
 */
Ext.define('LogManagerButtonPanel',{
	extend:'NormalButtonPanel',
	parent:null,
	items:null,
	valiType:null,
	monthlyTimePillarStore:null,
	id:'LogManagerButtonPanel_id',
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'leftbuttonpanel',
			width:430,
			items:[ {
				text :i18n('i18n.Logcontrol.requestSet'),
				xtype:'button',
				id:'actionSetButton_id',
				handler:function(button){
					button.setDisabled(true);
					var grid = Ext.getCmp('searchActionResultGrid_id');
					selection = grid.getSelectionModel().getSelection();
					if(selection.length > 1){
						MessageUtil.showErrorMes(i18n('i18n.Logcontrol.onlyUseOneData'));
						button.setDisabled(false);
						return;
					}else if(selection.length == 1){
						//修改操作
						me.updateAction(button);
					}else{
						//若没选中任何数据，则默认为新增
						Ext.create('SetActionWindow',{
							handType:'new'
						}).show();
						button.setDisabled(false);
					}
				}
			}, {
				text:i18n('i18n.Logcontrol.requestNumberChart'),
				xtype:'button',
				id:'countRequrstBtn_id',
				handler:function(button){
					viewType = 'number';
					me.showChart(button);
				}
			}, {
				text:i18n('i18n.Logcontrol.requestTimeChart'),
				id:'scatterCustUpdate',
				xtype:'button',
				handler:function(button){
					viewType = 'time';
					me.showChart(button);
				}
			},{
				text:i18n('i18n.Logcontrol.deleteButton'),
				id:'deleteId',
				xtype:'button',
				disabled:false,
				handler:function(button){
					button.setDisabled(true);
					var grid = Ext.getCmp('searchActionResultGrid_id');
					selection = grid.getSelectionModel().getSelection();
					if(selection.length > 1){
						MessageUtil.showErrorMes(i18n('i18n.Logcontrol.onlyUseOneData'));
						button.setDisabled(false);
						return;
					}else if(selection.length == 1){
						MessageUtil.showQuestionMes(i18n('i18n.Logcontrol.whetherDeleteActionInfo'), function(e) {
							if(e == 'yes'){
								//删除操作
								button.setDisabled(false);
								me.deleteAction(button);
							}else{
								button.setDisabled(false);
							}
						},function(){
							button.setDisabled(false);
						});
						
					}else{
						button.setDisabled(false);
						MessageUtil.showErrorMes(i18n('i18n.Logcontrol.pleaseSelectOne'));
					}
				}
			}]
		},{
			xtype:'middlebuttonpanel'
		},{
			xtype:'rightbuttonpanel',
			width:220,
			items:[{
				xtype : 'button',
				text : i18n('i18n.Logcontrol.search'),
				width : 70,
				handler :function(){
					me.searchActionResult();
				}
			},{
				xtype : 'button',
				text : i18n('i18n.Logcontrol.reset'),
				width : 70,
			    handler :function(){
			    	me.parent.searchCondition.getForm().reset();
			    }
			}]
		}];
	},
	//展示图表
	showChart:function(button){
		var me = this;
		var record = new SearchLogConditionModel();
		Ext.getCmp('searchPanel_id').getForm().updateRecord(record);
		viewFrequency=record.data.statisticalFrequency;
		var grid = Ext.getCmp('searchActionResultGrid_id');
		var selection = grid.getSelectionModel().getSelection();
		button.setDisabled(true);
		if(selection.length  != 1){
			MessageUtil.showErrorMes(i18n('i18n.Logcontrol.pleaseSelectOne'));
			button.setDisabled(false);
			return;
		}else if(selection.length == 1){
			me.initChartData(selection,button,record);
		}
	},
	//初始化chart图标展示所需数据
	initChartData:function(selection,button,record){
		var me = this;
		//时间需要重新取值
		var beginTime = Ext.getCmp('beginCountDate_id').getRawValue();
		var endTime = Ext.getCmp('endCountDate_id').getRawValue();
		var frequency = record.data.statisticalFrequency;
		var logInfoId = selection[0].data.id;
		var url = selection[0].data.url;
		//若url或者统计频率为空，则提示异常
		if(Ext.isEmpty(frequency)||Ext.isEmpty(url)){
			MessageUtil.showErrorMes(i18n('i18n.Logcontrol.requestDataLose'));
			button.setDisabled(false);
			return;
		}
		var successFn = function(result){
			var logStatisticsList = result.logStatisticsList;
			//若返回数据为空，则提示
			if(Ext.isEmpty(result.logStatisticsList)){
				MessageUtil.showErrorMes(i18n('i18n.Logcontrol.responseIsNull'));
				button.setDisabled(false);
				return;
			}
			var chartData = [];
			var listLength = logStatisticsList.length;
			var count = 0;
			if('month'==frequency){
				for(var i=0;i< 12;i++){
					for(var j = 0;j < listLength;j++){
						if(logStatisticsList[j].month-1==i){
							if(viewType == 'number'){
								count = logStatisticsList[j].requestCount;
							}else{
								count = logStatisticsList[j].avgRequestTime;
							}
						}
					}
					chartData.push({
			            name:Ext.Date.monthNames[i % 12],
			            dataSource:count
			        });
					count=0;
				}
			}else if('day'==frequency){
				for(var i=1;i<= 31;i++){
					for(var j = 0;j < listLength;j++){
						if(logStatisticsList[j].day==i){
							if(viewType == 'number'){
								count = logStatisticsList[j].requestCount;
							}else{
								count = logStatisticsList[j].avgRequestTime;
							}
						}
					}
					chartData.push({
			            name:i,
			            dataSource:count
			        });
					count=0;
				}
			}else if('hour'==frequency){
				for(var i=0;i<= 23;i++){
					for(var j = 0;j < listLength;j++){
						if(logStatisticsList[j].hour==i){
							if(viewType == 'number'){
								count = logStatisticsList[j].requestCount;
							}else{
								count = logStatisticsList[j].avgRequestTime;
							}
						}
					}
					chartData.push({
			            name:i,
			            dataSource:count
			        });
					count=0;
				}
			}
			me.monthlyTimePillarStore = Ext.create('Ext.data.JsonStore', {
		        fields: ['name', 'dataSource'],
		        data: chartData
		    });
			me.showTimePillarData();
			button.setDisabled(false);
		}
		var failFn = function(result){
			if(!Ext.isEmpty(result)&&!Ext.isEmpty(result.message)){
				MessageUtil.showErrorMes(result.message);
			}else{
				MessageUtil.showErrorMes(i18n('i18n.Logcontrol.responseIsFail'));
			}
			button.setDisabled(false);
		}
		var params = {};
		params.beginDate = beginTime;
		params.endDate = endTime;
		params.frequency = frequency;
		params.url = url;
		params.logInfoId = logInfoId;
		logActionControl.getLogStatistics(params,successFn,failFn);
	},
	//日志监控数据查询结果
	searchActionResult:function(){
		var me = this;
		if(validateSearchCondition(me.valiType)){
			me.parent.actionData.getActionResultStore().loadPage(1);
		}
	},
	deleteAction:function(){
		var me = this;
		var url = selection[0].data.url;
		var successFn = function(result){
			if(!Ext.isEmpty(result)&&!Ext.isEmpty(result.message)){
				MessageUtil.showErrorMes(result.message);
			}else{
				if(validateSearchCondition(me.valiType)){
					me.parent.actionData.getActionResultStore().loadPage(1);
				}
				MessageUtil.showInfoMes(i18n('i18n.Logcontrol.deleteSucessful'));
			}
			}
		var failFn = function(result){
			if(!Ext.isEmpty(result)&&!Ext.isEmpty(result.message)){
				MessageUtil.showErrorMes(result.message);
			}else{
				MessageUtil.showErrorMes(i18n('i18n.Logcontrol.deletefail'));
			}
		}
		var param = {'url':url};
		logActionControl.deleteActionInfo(param,successFn,failFn);
	},
	//修改Action
	updateAction:function(button){
		var actionId = selection[0].data.id;
		var successFn = function(result){
			if(!Ext.isEmpty(result.basicLoginfo)){
				var record = new ActionSetModel(result.basicLoginfo);
				//修改Action设置
				var actionPanel = Ext.create('SetActionWindow',{
					record:record,
					handType:'update'
				}).show();
				if(record.data.blackList){
					record.data.mailPerson = i18n('i18n.Logcontrol.emailSendNotice1');
				}else{
					record.data.mailPerson = i18n('i18n.Logcontrol.emailSendNotice2');
				}
				var actionForm = actionPanel.down('panel').getForm();
				actionForm.loadRecord(record);
				actionForm.findField('modulename').setReadOnly(true);
				actionForm.findField('actionName').setReadOnly(true);
				actionForm.findField('url').setReadOnly(true);
			}
			button.setDisabled(false);
		}
		var failFn = function(result){
			if(!Ext.isEmpty(result)&&!Ext.isEmpty(result.message)){
				MessageUtil.showErrorMes(result.message);
			}else{
				MessageUtil.showErrorMes(i18n('i18n.Logcontrol.getActionInfoFail'));
			}
			button.setDisabled(false);
		}
		var param = {'actionId':actionId};
		logActionControl.getActionInfo(param,successFn,failFn);
	},
	//****************
	showTimePillarData:function(){
		var me = this;
		var viewTitle;
		//根据不同的统计频率来设置相应的标题
		if('hour'==viewFrequency){
			viewTitle='Hour of th Day';
		}else if('day'==viewFrequency){
			viewTitle='Day of the Month';
		}else if('month'==viewFrequency){
			viewTitle='Month of the Year';
		}
		var chart = Ext.create('Ext.chart.Chart', {
	            id: 'chartCmp',
	            xtype: 'chart',
	            style: 'background:#fff',
	            animate: true,
	            shadow: true,
	            store: me.monthlyTimePillarStore,
	            axes: [{
	                type: 'Numeric',
	                position: 'left',
	                fields: ['dataSource'],
	                label: {
	                    renderer: Ext.util.Format.numberRenderer('0,0')
	                },
	                title: viewType == 'number'?'Number of Action Requests':'Time of Action Requests',
	                grid: true,
	                minimum: 0
	            }, {
	                type: 'Category',
	                position: 'bottom',
	                fields: ['name'],
	                title:viewTitle
	            }],
	            series: [{
	                type: 'column',
	                axis: 'left',
	                highlight: true,
	                tips: {
	                  trackMouse: true,
	                  width: 140,
	                  height: 28,
	                  renderer: function(storeItem, item) {
	                	if(viewType == 'time'){
	                		this.setTitle(storeItem.get('name') + ': ' + storeItem.get('dataSource') + '毫秒');
	                	}else{
	                		this.setTitle(storeItem.get('name') + ': ' + storeItem.get('dataSource') + '次');
	                	}
	                  }
	                },
	                label: {
	                  display: 'insideEnd',
	                  'text-anchor': 'middle',
	                    field: 'dataSource',
	                    renderer: Ext.util.Format.numberRenderer('0'),
	                    orientation: 'vertical',
	                    color: '#333'
	                },
	                xField: 'name',
	                yField: 'dataSource'
	            }]
	        });
	    var win = Ext.create('Ext.Window', {
	        width: 820,
	        height: 745,
	        minHeight: 400,
	        minWidth: 550,
	        hidden: false,
	        modal : true,
	        constrainHeader: true,
	        maximizable: true,
	        title: i18n('i18n.Logcontrol.chartForPlay'),
	        layout: 'fit',
	        items: chart,
	        listeners:{
	        	'beforeclose':function(){
	        		viewType = null;
	        		viewFrequency = null;
	        	}
	        }
	    });
	    win.show();
	}
});
/**
 * Action列表查询结果
 */
Ext.define('SearchResultGrid',{
	extend:'BasicPanel',
	store:null,
	items:null,
	actionData:null,
	autoScroll:true,
	parent:null,
	initComponent:function(){
		var me = this;
		me.store = me.actionData.initActionResultStore(me.beforeLoadTransactionFn);
		me.items = me.getItems();
		me.dockedItems = me.getMyDockedItems();
//		me.listeners = me.getMyListeners();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [ {
			xtype : 'searchgridpanel',
			id:'searchActionResultGrid_id',
			store:me.store,
			selModel:Ext.create('Ext.selection.CheckboxModel'),
			emptyText:i18n('i18n.Logcontrol.responseIsNull'),
			autoScroll :true,
			columns : [Ext.create('Ext.grid.RowNumberer',{
				width:45,
				text:i18n('i18n.Logcontrol.infoId')
					}),
			{
				header:'id',
				dataIndex:'id',
				hidden:true
			},{
				header:i18n('i18n.Logcontrol.modulename'),
				width:124,
				dataIndex:'modulename'
			},{
				header:i18n('i18n.Logcontrol.actionName'),
				width:124,
				dataIndex:'actionName'
			},{
				header:i18n('i18n.Logcontrol.createTime'),
				width:134,
				dataIndex:'createTime',
				renderer:function(value){
					return DpUtil.renderTime(value);
				}
			},{
				header:i18n('i18n.Logcontrol.requestCount'),
				width:121,
				dataIndex:'requestCount'
			},{
				header:i18n('i18n.Logcontrol.requestAvgTime'),
				width:121,
				dataIndex:'avgRequestTime'
			},{
				header:i18n('i18n.Logcontrol.requestMaxTime'),
				width:121,
				dataIndex:'maxRequestTime'
			}]
		}];
		
	},
	beforeLoadTransactionFn:function(store, operation, eOpts){
		var record = new SearchLogConditionModel();
		var actionListSearchForm = Ext.getCmp('searchPanel_id');//
		if(!Ext.isEmpty(actionListSearchForm)){
			actionListSearchForm.getForm().updateRecord(record);
			record.set('beginDate',actionListSearchForm.getForm().findField('beginDate').getRawValue());
			record.set('endDate',actionListSearchForm.getForm().findField('endDate').getRawValue());
			//设置请求参数
			var searchActionListCondition = record.data;
			var searchParams = {
					'logInfoCondition.modulename':searchActionListCondition.moduleName,
					'logInfoCondition.actionName':searchActionListCondition.requestName,
					'logInfoCondition.beginDate':searchActionListCondition.beginDate,
					'logInfoCondition.endDate':searchActionListCondition.endDate,
					'logInfoCondition.statisticalFrequency':searchActionListCondition.statisticalFrequency
					};
			Ext.apply(operation,{
				params : searchParams
				}
			);
		}
	},
	//分页条
	getMyDockedItems :function(){ 
		var me = this;
		return [ {
			xtype : 'pagingtoolbar',
			store : me.store,
			dock : 'bottom',
			displayInfo : true
		} ];
	}
});
Ext.onReady(function(){
	Ext.create('Ext.Viewport', {
		layout : {
			type : 'fit'
		},
		items : [ Ext.create('LogManagerPanel',{'actionData':actionData}) ]
	});
});