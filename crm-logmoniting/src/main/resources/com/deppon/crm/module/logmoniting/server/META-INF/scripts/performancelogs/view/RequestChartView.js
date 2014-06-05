var actionData =  Ext.create('LogBasicData');
//请求图面板
Ext.define('ChartManagerPanel',{
	extend:'BasicPanel',
	valiType:'TOPCHART',
	searchCondition:null, // 查询条件
	searchResult:null, // 查询结果
	actionDescPanel:null,//Action说明
	autoScroll:true,
	layout:{
		type:'vbox',
		align:'stretch'
	},
	initComponent:function(){
		var me = this;
		me.searchCondition = Ext.create('ChartSearchForm');
		me.searchResult = Ext.create('SearchResultGrid');
		me.actionDescPanel = Ext.create('ActionDesc');
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
				height:140,
				items:[me.actionDescPanel]
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
Ext.define('ChartSearchForm',{
	extend:'SearchFormPanel',
	id:'searchPanel_id',
	items:null,
	parent:null,
	record:null,
	layout:'fit',
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
		return[
		{
			xtype:'textfield',
			fieldLabel:i18n('i18n.Logcontrol.modulename'),
			name:'modulename'
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
			xtype:'fieldcontainer',
			fieldLabel:i18n('i18n.Logcontrol.countWay'),
			defaultType: 'radiofield',
			layout: 'column',
			items:[
				 {
				    boxLabel  : i18n('i18n.Logcontrol.requestTime'),
				    name      : 'statisticalMethods',
				    inputValue: 'requestTime',
				    id        : 'requestTime_id',
				    checked:true
				 }, {
                    boxLabel  : i18n('i18n.Logcontrol.requestNumber'),
                    name      : 'statisticalMethods',
                    inputValue: 'requestNumber',
                    id        : 'requestNumber_id'
	             }
		         ]
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
            },{
            	fieldLabel:i18n('i18n.Logcontrol.countNumber'),
            	name:'level',
            	xtype:'combo',
            	store:{fields:['abbr','name'],
            			data:[
            			   {'abbr':'10','name':'TOP10'}
                   	       ]
            		
            	},
            	queryMode:'local',
            	value:'10',
            	forceSelection:true,
            	displayField:'name',
    			valueField:'abbr'
            }];
	}
});
/**
 * 操作按钮面板
 */
Ext.define('LogManagerButtonPanel',{
	extend:'NormalButtonPanel',
	valiType:null,
	parent:null,
	items:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'leftbuttonpanel',
			width:430,
			height:26
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
					me.searchTopChart();
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
	//数据源查询方法
	searchTopChart:function(){
		var me = this;
		if(!validateSearchCondition(me.valiType)){
			return;
		}
		//每次查询之前先清除掉之前的图表
		if(!Ext.isEmpty(Ext.getCmp('topchartid'))){
			Ext.getCmp('SearchResultGrid_id').remove(Ext.getCmp('topchartid'));
		}
		var searchForm = me.parent.down('form').getForm();
		var record = Ext.create('SearchChartConditionModel'); 
		searchForm.updateRecord(record);
		record.set('beginDate',searchForm.findField('beginDate').getRawValue());
		record.set('endDate',searchForm.findField('endDate').getRawValue());
		var statisticalMethods = record.data.statisticalMethods;
		var frequency = record.data.statisticalFrequency;
		
		var successFn = function(result){
			var topChartDataList = result.topChartDataList;
			
			if(Ext.isEmpty(topChartDataList)){
				MessageUtil.showErrorMes(i18n('i18n.Logcontrol.responseIsNull'));
				me.parent.actionDescPanel.getForm().reset();
				return;
			}else if(topChartDataList.length < 20){
				MessageUtil.showErrorMes(i18n('i18n.Logcontrol.lessThanTenNotice'));
				me.parent.actionDescPanel.getForm().reset();
				return;
			}
			var listLength = topChartDataList.length;
			var count=-1;
			chartData = [];
			if('month' == frequency){
				for(var i=0;i<12;i++){
					for(var j=0;j<topChartDataList[1].length;j+=2){
						if(topChartDataList[1][j].month-1 == i){
							count = j ;
							break;
						}
					}
					me.dealChartData(frequency,statisticalMethods,topChartDataList,count,i);
				count = -1;
				}
			}else if('day' == frequency){
				for(var i=1;i<=31;i++){
					for(var j=0;j<topChartDataList[1].length;j+=2){
						if(topChartDataList[1][j].day == i){
							count = j;
							break;
						}
					}
					me.dealChartData(frequency,statisticalMethods,topChartDataList,count,i);
				count = -1;
				}
			}else if('hour' == frequency){
				for(var i=0;i<=23;i++){
					for(var j=0;j<topChartDataList[1].length;j+=2){
						if(topChartDataList[1][j].hour == i){
							count = j;
							break;
						}
					}
					me.dealChartData(frequency,statisticalMethods,topChartDataList,count,i);
				count = -1;
				}
			}
			me.monthlyTimeLineStore = Ext.create('Ext.data.JsonStore', {
		        fields: ['name', 'action1','action2','action3','action4','action5','action6','action7','action8','action9','action10'],
		        data: chartData
		    });
			me.showLineChartData(statisticalMethods,frequency);
		}
		var failFn = function(result){
			if(!Ext.isEmpty(result)&&!Ext.isEmpty(result.message)){
				MessageUtil.showErrorMes(result.message);
			}else{
				MessageUtil.showErrorMes(i18n('i18n.Logcontrol.requestFailed'));
			}
		}
		params={'logInfoCondition':record.data};
		actionData.sarchChartData(params,successFn,failFn);
	},
	//图表数据处理
	dealChartData:function(frequency,statisticalMethods,topChartDataList,count,i){
		var me = this;
		if(Ext.isEmpty(topChartDataList)){
			MessageUtil.showErrorMes(i18n('i18n.Logcontrol.responseIsNull'));
			return;
		}
		 action1 = topChartDataList[0];
		 action2 = topChartDataList[2];
		 action3 = topChartDataList[4];
		 action4 = topChartDataList[6];
		 action5 = topChartDataList[8];
		 action6 = topChartDataList[10];
		 action7 = topChartDataList[12];
		 action8 = topChartDataList[14];
		 action9 = topChartDataList[16];
		 action10 = topChartDataList[18];
		
		if(count != -1){
			chartData.push({
				name: frequency=='month'?Ext.Date.monthNames[i % 12]:i,
				action1:statisticalMethods=='requestNumber'?topChartDataList[1][count].requestCount:topChartDataList[1][count].avgRequestTime,
				action2:statisticalMethods=='requestNumber'?topChartDataList[3][count].requestCount:topChartDataList[3][count].avgRequestTime,
				action3:statisticalMethods=='requestNumber'?topChartDataList[5][count].requestCount:topChartDataList[5][count].avgRequestTime,
				action4:statisticalMethods=='requestNumber'?topChartDataList[7][count].requestCount:topChartDataList[7][count].avgRequestTime,
				action5:statisticalMethods=='requestNumber'?topChartDataList[9][count].requestCount:topChartDataList[9][count].avgRequestTime,
				action6:statisticalMethods=='requestNumber'?topChartDataList[11][count].requestCount:topChartDataList[11][count].avgRequestTime,
				action7:statisticalMethods=='requestNumber'?topChartDataList[13][count].requestCount:topChartDataList[13][count].avgRequestTime,
				action8:statisticalMethods=='requestNumber'?topChartDataList[15][count].requestCount:topChartDataList[15][count].avgRequestTime,
				action9:statisticalMethods=='requestNumber'?topChartDataList[17][count].requestCount:topChartDataList[17][count].avgRequestTime,
				action10:statisticalMethods=='requestNumber'?topChartDataList[19][count].requestCount:topChartDataList[19][count].avgRequestTime
			});
		}else{
			chartData.push({
				name: frequency=='month'?Ext.Date.monthNames[i % 12]:i,
				action1:0,
				action2:0,
				action3:0,
				action4:0,
				action5:0,
				action6:0,
				action7:0,
				action8:0,
				action9:0,
				action10:0
			});
		}
	},
	//展示图表
	showLineChartData:function(statisticalMethods,frequency){
		var me = this;
		if('hour'==frequency){	
			viewTitle='Hour of th Day';
		}else if('day'==frequency){
			viewTitle='Day of the Month';
		}else if('month'==frequency){
			viewTitle='Month of the Year';
		}
		var actionForm = me.parent.actionDescPanel.getForm();
		actionForm.findField('action1').setValue(action1);
		actionForm.findField('action2').setValue(action2);
		actionForm.findField('action3').setValue(action3);
		actionForm.findField('action4').setValue(action4);
		actionForm.findField('action5').setValue(action5);
		actionForm.findField('action6').setValue(action6);
		actionForm.findField('action7').setValue(action7);
		actionForm.findField('action8').setValue(action8);
		actionForm.findField('action9').setValue(action9);
		actionForm.findField('action10').setValue(action10);
		Ext.getCmp('SearchResultGrid_id').add(
				Ext.create('Ext.chart.Chart', {
		            xtype: 'chart',
		            style: 'background:#fff',
		            id:'topchartid',
		            animate: true,
		            store: me.monthlyTimeLineStore,
		            shadow: true,
		            theme: 'Category1',
		            legend: {
		                position: 'right'
		            },
		            axes: [{
		                type: 'Numeric',
		                minimum: 0,
		                position: 'left',
		                fields: ['action1','action2','action3','action4','action5','action6','action7','action8','action9','action10'],
		                title: statisticalMethods == 'requestNumber'?'Number of Action Requests':'Time of Action Requests',
		                minorTickSteps: 1,
		                grid: {
		                    odd: {
		                        opacity: 1,
		                        fill: '#ddd',
		                        stroke: '#bbb',
		                        'stroke-width': 0.5
		                    }
		                }
		            }, {
		                type: 'Category',
		                position: 'bottom',
		                fields: ['name'],
		                title: viewTitle
		            }],
		            series: [{
		                type: 'line',
		                highlight: {
		                    size: 7,
		                    radius: 7
		                },
		                axis: 'left',
		                xField: 'name',
		                yField: 'action1',
		                markerConfig: {
		                    type: 'cross',
		                    size: 3,
		                    radius: 3,
		                    'stroke-width': 0
		                }
		            }, {
		                type: 'line',
		                highlight: {
		                    size: 7,
		                    radius: 7
		                },
		                axis: 'left',
		                smooth: true,
		                xField: 'name',
		                yField: 'action2',
		                markerConfig: {
		                    type: 'circle',
		                    size: 3,
		                    radius: 3,
		                    'stroke-width': 0
		                }
		            }, {
		                type: 'line',
		                highlight: {
		                    size: 7,
		                    radius: 7
		                },
		                axis: 'left',
		                smooth: true,
		                xField: 'name',
		                yField: 'action3',
		                markerConfig: {
		                    type: 'circle',
		                    size: 3,
		                    radius: 3,
		                    'stroke-width': 0
		                }
		            },
		            {
		                type: 'line',
		                highlight: {
		                    size: 7,
		                    radius: 7
		                },
		                axis: 'left',
		                smooth: true,
		                xField: 'name',
		                yField: 'action4',
		                markerConfig: {
		                    type: 'circle',
		                    size: 3,
		                    radius: 3,
		                    'stroke-width': 0
		                }
		            }, {
		                type: 'line',
		                highlight: {
		                    size: 7,
		                    radius: 7
		                },
		                axis: 'left',
		                smooth: true,
		                xField: 'name',
		                yField: 'action5',
		                markerConfig: {
		                    type: 'circle',
		                    size: 3,
		                    radius: 3,
		                    'stroke-width': 0
		                },
		            }, {
		                type: 'line',
		                highlight: {
		                    size: 7,
		                    radius: 7
		                },
		                axis: 'left',
		                smooth: true,
		                xField: 'name',
		                yField: 'action6',
		                markerConfig: {
		                    type: 'circle',
		                    size: 3,
		                    radius: 3,
		                    'stroke-width': 0
		                }
		            },{
		                type: 'line',
		                highlight: {
		                    size: 7,
		                    radius: 7
		                },
		                axis: 'left',
		                smooth: true,
		                xField: 'name',
		                yField: 'action7',
		                markerConfig: {
		                    type: 'circle',
		                    size: 3,
		                    radius: 3,
		                    'stroke-width': 0
		                }
		            },{
		                type: 'line',
		                highlight: {
		                    size: 7,
		                    radius: 7
		                },
		                axis: 'left',
		                smooth: true,
		                xField: 'name',
		                yField: 'action8',
		                markerConfig: {
		                    type: 'circle',
		                    size: 3,
		                    radius: 3,
		                    'stroke-width': 0
		                }
		            },{
		                type: 'line',
		                highlight: {
		                    size: 7,
		                    radius: 7
		                },
		                axis: 'left',
		                smooth: true,
		                xField: 'name',
		                yField: 'action9',
		                markerConfig: {
		                    type: 'circle',
		                    size: 3,
		                    radius: 3,
		                    'stroke-width': 0
		                }
		            },{
		                type: 'line',
		                highlight: {
		                    size: 7,
		                    radius: 7
		                },
		                axis: 'left',
		                smooth: true,
		                xField: 'name',
		                yField: 'action10',
		                markerConfig: {
		                    type: 'circle',
		                    size: 3,
		                    radius: 3,
		                    'stroke-width': 0
		                }
		            }
		            ]
		        })
				);
	}
});
Ext.define('SearchResultGrid',{
	extend:'BasicPanel',
	id:'SearchResultGrid_id',
	title:i18n('i18n.Logcontrol.chartPlayArea'),
	store:null,
	items:null,
	autoScroll:true,
	parent:null
});
Ext.define('ActionDesc',{
	extend:'SearchFormPanel',
	title:i18n('i18n.Logcontrol.ActionDescribe'),
	layout:{
		type:'table',
		columns:3
	},
	defaults:{
		 xtype:'textfield',
		 emptyText:i18n('i18n.Logcontrol.pressBtnGetRs'),
		 readOnly:true
	},
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return[
		       {	
		    	   fieldLabel:'action1',
		    	   name:'action1'
		       },{
		    	   fieldLabel:'action2',
		    	   name:'action2'
		       },{
		    	   fieldLabel:'action3',
		    	   name:'action3'
		       },{
		    	   fieldLabel:'action4',
		    	   name:'action4'
		       },{
		    	   fieldLabel:'action5',
		    	   name:'action5'
		       },{
		    	   fieldLabel:'action6',
		    	   name:'action6'
		       },{
		    	   fieldLabel:'action7',
		    	   name:'action7'
		       },{
		    	   fieldLabel:'action8',
		    	   name:'action8'
		       },{
		    	   fieldLabel:'action9',
		    	   name:'action9'
		       },{
		    	   fieldLabel:'action10',
		    	   name:'action10'
		       }
		        ]
	}
});
Ext.onReady(function(){
	Ext.create('Ext.Viewport', {
		layout : {
			type : 'fit'
		},
		items : [ Ext.create('ChartManagerPanel') ]
	});
});