var viewType = null;
var actionData = new LogBasicData();
/**
 * 日志监控界面Panel
 */
Ext.define('LogManagerPanel',{
	extend:'BasicPanel',
	valiType:'LOGCHART',
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
			fieldLabel:'请求名称',
			name:'requestName'
		},{
			xtype:'datetimefield',
			fieldLabel:'统计开始时间',
			format: 'Y-m-d H:00:00',
			id:'beginCountDate_id',
			name:'beginDate'
		},{
			xtype:'datetimefield',
			fieldLabel:'统计结束时间',
			format: 'Y-m-d H:00:00',
			id:'endCountDate_id',
			name:'endDate'
		},{
			xtype:'textfield',
			fieldLabel:'模块名称',
			name:'moduleName'
		},{
            xtype:'fieldcontainer',
            fieldLabel : '统计频率',
            defaultType: 'radiofield',
            name:'countRate',
            layout: 'column',
            items: [
				 {
				    boxLabel  : '时',
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
                    boxLabel  : '日',
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
                    boxLabel  : '月',
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
				text :'请求设置',
				xtype:'button',
				id:'actionSetButton_id',
				handler:function(button){
					button.setDisabled(true);
					var grid = Ext.getCmp('searchActionResultGrid_id');
					selection = grid.getSelectionModel().getSelection();
					if(selection.length > 1){
						MessageUtil.showErrorMes('不能同时选择多条数据进行修改');
						button.setDisabled(false);
						return;
					}else if(selection.length == 1){
						//修改操作
						me.updateAction(button);
					}else{//若没选中任何数据，则默认为新增
						Ext.create('SetActionWindow',{
							handType:'new'
						}).show();
						button.setDisabled(false);
					}
				}
			}, {
				text:'请求次数图',
				xtype:'button',
				id:'countRequrstBtn_id',
				handler:function(button){
					viewType = 'number';
					me.showChart(button);
				}
			}, {
				text:'请求时长图',
				id:'scatterCustUpdate',
				xtype:'button',
				handler:function(button){
					viewType = 'time';
					me.showChart(button);
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
			MessageUtil.showErrorMes('请选择一条数据');
			button.setDisabled(false);
			return;
		}else if(selection.length == 1){
			me.initChartData(selection,button,record);
		}
	},
	//初始化chart图标展示所需数据
	initChartData:function(selection,button,record){
		var me = this;
		var beginTime = record.data.beginDate;
		var endTime = record.data.endDate;
		var frequency = record.data.statisticalFrequency;
		var logInfoId = selection[0].data.id;
		var url = selection[0].data.url;
		if(Ext.isEmpty(frequency)||Ext.isEmpty(url)){
			MessageUtil.showErrorMes('请求参数不完整，请求失败');
			button.setDisabled(false);
			return;
		}
		var successFn = function(result){
			var logStatisticsList = result.logStatisticsList;
			if(Ext.isEmpty(result.logStatisticsList)){
				MessageUtil.showErrorMes('返回数据为空');
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
							count = logStatisticsList[j].requestCount;
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
							count = logStatisticsList[j].requestCount;
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
							count = logStatisticsList[j].requestCount;
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
			if(!Ext.isEmpty(result.message)){
				MessageUtil.showErrorMes(result.message);
			}else{
				MessageUtil.showErrorMes('获取数据源失败');
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
					record.data.mailPerson = '温馨提示：通知邮件将发至CRM全体开发人员';
				}else{
					record.data.mailPerson = '温馨提示：未加入黑名单则不发送邮件';
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
			if(!Ext.isEmpty(result.message)){
				MessageUtil.showErrorMes(result.message);
			}else{
				MessageUtil.showErrorMes('获取Action信息失败');
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
	        title: '数据展示图',
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
			emptyText:'没有查询到的信息',
			autoScroll :true,
			columns : [Ext.create('Ext.grid.RowNumberer',{
				width:45,
				text:'序号'
					}),
			{
				header:'id',
				dataIndex:'id',
				hidden:true
			},{
				header:'模块名称',
				width:124,
				dataIndex:'modulename'
			},{
				header:'Action名称',
				width:124,
				dataIndex:'actionName'
			},{
				header:'创建时间',
				width:134,
				dataIndex:'createTime',
				renderer:function(value){
					return DpUtil.renderTime(value);
				}
			},{
				header:'请求次数',
				width:121,
				dataIndex:'requestCount'
			},{
				header:'请求平均时长',
				width:121,
				dataIndex:'maxRequestTime'
			},{
				header:'请求最长时长',
				width:121,
				dataIndex:'avgRequestTime'
			}]
		}];
		
	},
	beforeLoadTransactionFn:function(store, operation, eOpts){
		var record = new SearchLogConditionModel();
		var actionListSearchForm = Ext.getCmp('searchPanel_id');//
		if(!Ext.isEmpty(actionListSearchForm)){
			actionListSearchForm.getForm().updateRecord(record);
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