var actionData =  Ext.create('LogBasicData');
//请求图面板
Ext.define('ChartManagerPanel',{
	extend:'BasicPanel',
	valiType:'TOPCHART',
	searchCondition:null, // 查询条件
	searchResult:null, // 查询结果
	autoScroll:true,
	layout:{
		type:'vbox',
		align:'stretch'
	},
	initComponent:function(){
		var me = this;
		me.searchCondition = Ext.create('ChartSearchForm');
		me.searchResult = Ext.create('SearchResultGrid');
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
			fieldLabel:'模块名称',
			name:'moduleName'
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
			xtype:'fieldcontainer',
			fieldLabel:'统计方式',
			defaultType: 'radiofield',
			layout: 'column',
			items:[
				 {
				    boxLabel  : '请求时间',
				    name      : 'statisticalMethods',
				    inputValue: 'requestTime',
				    id        : 'requestTime_id',
				    checked:true
				 }, {
                    boxLabel  : '请求次数',
                    name      : 'statisticalMethods',
                    inputValue: 'requestNumber',
                    id        : 'requestNumber_id'
	             }
		         ]
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
            },{
            	fieldLabel:'统计条数',
            	name:'level',
            	xtype:'combo',
            	store:{fields:['abbr','name'],
            			data:[
            			   {'abbr':'10','name':'TOP10'},
                   	       {'abbr':'15','name':'TOP15'},
                	       {'abbr':'20','name':'TOP20'}]
            		
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
			items:[{
				text:'查看详情',
				id:'lookDetailButton_id',
				xtype:'button',
				handler:function(){
					MessageUtil.showInfoMes('点我做啥？我是查看详情按钮');
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
					me.searchTopChart();
				}
			},{
				//
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
		var searchForm = me.parent.down('form').getForm();
		var record = Ext.create('SearchChartConditionModel'); 
		searchForm.updateRecord(record);
		var successFn = function(result){
			alert('通了！');
			me.showLineChartData();
		}
		var failFn = function(result){
			if(!Ext.isEmpty(result.message)){
				MessageUtil.showErrorMes(result.message);
			}else{
				MessageUtil.showErrorMes('请求失败');
			}
		}
		params={'logInfoCondition':record.data};
		actionData.sarchChartData(params,successFn,failFn);
	},
	showLineChartData:function(){
		store1.loadData(generateData());
		var chart = Ext.create('Ext.chart.Chart', {
            xtype: 'chart',
            style: 'background:#fff',
            animate: true,
            store: store1,
            shadow: true,
            theme: 'Category1',
            legend: {
                position: 'right'
            },
            axes: [{
                type: 'Numeric',
                minimum: 0,
                position: 'left',
                fields: ['LoginAction', 'SearchAction'],
                title: 'Number of Action',
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
                title: 'Month of the Year'
            }],
            series: [{
                type: 'line',
                highlight: {
                    size: 7,
                    radius: 7
                },
                axis: 'left',
                xField: 'name',
                yField: 'LoginAction',
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
                yField: 'SearchAction',
                markerConfig: {
                    type: 'circle',
                    size: 3,
                    radius: 3,
                    'stroke-width': 0
                }
            }
            ]
        });
		
		//****
		   var win = Ext.create('Ext.Window', {
		        width: 800,
		        height: 600,
		        minHeight: 400,
		        minWidth: 550,
		        hidden: false,
		        maximizable: true,
		        title: 'Line Chart',
		        layout: 'fit',
		        tbar: [{
		            text: 'Save Chart',
		            handler: function() {
		                Ext.MessageBox.confirm('Confirm Download', 'Would you like to download the chart as an image?', function(choice){
		                    if(choice == 'yes'){
		                        chart.save({
		                            type: 'image/png'
		                        });
		                    }
		                });
		            }
		        }, {
		            text: 'Reload Data',
		            handler: function() {
		                store1.loadData(generateData());
		            }
		        }],
		        items: chart
		    });
		   win.show();
	}
});
Ext.define('SearchResultGrid',{
	extend:'BasicPanel',
	title:'数据展示区',
	store:null,
	items:null,
	autoScroll:true,
	parent:null
});
Ext.onReady(function(){
	Ext.create('Ext.Viewport', {
		layout : {
			type : 'fit'
		},
		items : [ Ext.create('ChartManagerPanel') ]
	});
});